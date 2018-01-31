package com.spheremall.core.jsonapi;

import com.spheremall.core.jsonapi.errors.Error;
import com.spheremall.core.jsonapi.exceptions.DocumentSerializationException;
import com.spheremall.core.jsonapi.models.Article;
import com.spheremall.core.jsonapi.models.Author;
import com.spheremall.core.jsonapi.models.Status;
import com.spheremall.core.jsonapi.models.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Testing functionality of JSON API converter's serialization methods..
 *
 * @author jbegic
 */
public class SerializationTest {
    private ResourceConverter converter;

    @Before
    public void setup() {
        converter = new ResourceConverter(Status.class, User.class, Article.class, Author.class);
        converter.enableSerializationOption(SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES);
    }

    @Test
    public void testFullSerialization() throws DocumentSerializationException {
        User user = createUser();
        JSONAPIDocument<User> document = createDocument(user);

        JSONAPIDocument<User> convertedBack = converter.readDocument(converter.writeDocument(document),
                User.class);

        Assert.assertNotNull(convertedBack);
        Assert.assertNotNull(convertedBack.get());

        User convertedUser = convertedBack.get();
        Assert.assertEquals(user.getId(), convertedUser.getId());
        Assert.assertEquals(user.getName(), convertedUser.getName());
        Assert.assertEquals(user.meta.token, convertedUser.meta.token);
        Assert.assertEquals("link", convertedUser.links.getSelf().getHref());

        Assert.assertNotNull(convertedBack.getMeta());
        Assert.assertNotNull(convertedBack.getLinks());

        Assert.assertEquals("value", convertedBack.getMeta().get("key"));
        Assert.assertEquals("link", convertedBack.getLinks().getSelf().getHref());

    }

    @Test
    public void testWithDisabledOptions() throws DocumentSerializationException {
        ResourceConverter converter = new ResourceConverter(Status.class, User.class);
        converter.disableSerializationOption(SerializationFeature.INCLUDE_META);
        converter.disableSerializationOption(SerializationFeature.INCLUDE_LINKS);


        JSONAPIDocument<User> convertedBack = converter
                .readDocument(converter.writeDocument(createDocument(createUser())), User.class);

        Assert.assertNotNull(convertedBack.get());
        Assert.assertNull(convertedBack.getLinks());
        Assert.assertNull(convertedBack.getMeta());

        Assert.assertNull(convertedBack.get().meta);
        Assert.assertNull(convertedBack.get().links);

    }

    @Test
    public void testCyclicalSerialisation() throws DocumentSerializationException {
        Author author = new Author();
        author.setId("authorid");
        author.setFirstName("John");

        Article article = new Article();
        article.setId("articleid");
        article.setTitle("title");
        article.setAuthor(author);

        author.setArticles(new ArrayList<Article>());
        author.getArticles().add(article);

        byte[] data = converter.writeDocument(new JSONAPIDocument<>(author));

        JSONAPIDocument<Author> authorResource = converter.readDocument(data, Author.class);

        Assert.assertEquals(author.getFirstName(), authorResource.get().getFirstName());
        Assert.assertEquals(author.getArticles().size(), authorResource.get().getArticles().size());
        Assert.assertEquals(article.getTitle(), authorResource.get().getArticles().iterator().next().getTitle());
    }

    @Test
    public void testErrorSerialisation() throws DocumentSerializationException {
        Error error = new Error();
        error.setCode("code");

        JSONAPIDocument<?> document = JSONAPIDocument.createErrorDocument(Collections.singleton(error));

        String serialised = new String(converter.writeDocument(document));

        Assert.assertEquals("{\"errors\":[{\"code\":\"code\"}]}", serialised);

    }

    @Test
    public void testIncludedDataDisabledTroughSettings() throws DocumentSerializationException {
        JSONAPIDocument<User> document = createDocument(createUser());

        SerializationSettings serializationSettings = new SerializationSettings.Builder()
                .excludedRelationships("statuses")
                .build();

        JSONAPIDocument<User> convertedBack = converter.readDocument(
                converter.writeDocument(document, serializationSettings), User.class);

        Status status = convertedBack.get().getStatuses().iterator().next();
        Assert.assertNull(status.getContent());
    }

    /**
     * Covers use-case where global settings are used to disable relationship attribute inclusion but
     * behaviour is changed trouh local settings provided when serialization is executed.
     *
     * @throws DocumentSerializationException
     */
    @Test
    public void testIncludedDataEnabledTroughSettings() throws DocumentSerializationException {
        converter.disableSerializationOption(SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES);
        JSONAPIDocument<User> document = createDocument(createUser());

        SerializationSettings serializationSettings = new SerializationSettings.Builder()
                .includeRelationship("statuses")
                .build();

        JSONAPIDocument<User> convertedBack = converter.readDocument(
                converter.writeDocument(document, serializationSettings), User.class);

        Status status = convertedBack.get().getStatuses().iterator().next();
        Assert.assertNotNull(status.getContent());
    }

    @Test
    public void testOverrideGlobalMetaLinksSettings() throws DocumentSerializationException {
        JSONAPIDocument<User> document = createDocument(createUser());

        SerializationSettings serializationSettings = new SerializationSettings.Builder()
                .serializeLinks(false)
                .serializeMeta(false)
                .build();

        JSONAPIDocument<User> convertedBack = converter.readDocument(
                converter.writeDocument(document, serializationSettings), User.class);

        Assert.assertNull(convertedBack.getMeta());
        Assert.assertNull(convertedBack.getLinks());
        Assert.assertNull(convertedBack.get().getMeta());
        Assert.assertNull(convertedBack.get().links);

        serializationSettings = new SerializationSettings.Builder()
                .serializeLinks(true)
                .serializeMeta(true)
                .build();

        converter.disableSerializationOption(SerializationFeature.INCLUDE_META);
        converter.disableSerializationOption(SerializationFeature.INCLUDE_LINKS);

        convertedBack = converter.readDocument(
                converter.writeDocument(document, serializationSettings), User.class);

        Assert.assertNotNull(convertedBack.getMeta());
        Assert.assertNotNull(convertedBack.getLinks());
        Assert.assertNotNull(convertedBack.get().getMeta());
        Assert.assertNotNull(convertedBack.get().links);
    }

    @Test
    public void testSerializeWithoutId() throws DocumentSerializationException {
        User user = new User();
        user.setName("Name");

        byte[] data = converter.writeDocument(new JSONAPIDocument<>(user));

        Assert.assertTrue(new String(data).contains(user.getName()));
        Assert.assertFalse(new String(data).contains("id"));
    }

    private JSONAPIDocument<User> createDocument(User user) {
        JSONAPIDocument<User> document = new JSONAPIDocument<>(user);

        document.setLinks(user.links);

        Map<String, String> globalMeta = new HashMap<>();
        globalMeta.put("key", "value");
        document.setMeta(globalMeta);

        return document;
    }

    private User createUser() {
        User user = new User();
        user.setId("id");
        user.setName("name");

        user.meta = new User.UserMeta();
        user.meta.token = "token";

        user.setStatuses(new ArrayList<Status>());

        Status status = new Status();
        status.setId("sid");
        status.setContent("content");
        user.getStatuses().add(status);


        Map<String, Link> linkMap = new HashMap<>();
        linkMap.put(JSONAPISpecConstants.SELF, new Link("link"));

        user.links = new Links(linkMap);

        JSONAPIDocument<User> document = new JSONAPIDocument<>(user);

        document.setLinks(user.links);

        Map<String, String> globalMeta = new HashMap<>();
        globalMeta.put("key", "value");
        document.setMeta(globalMeta);

        return user;
    }
}
