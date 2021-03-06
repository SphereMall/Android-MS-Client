package com.spheremall.core.jsonapi.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Id;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("articles")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "id")
public class Article {
	@Id
	private String id;

	private String title;

	@Relationship(value = "author", resolve = true, relType = RelType.RELATED)
	private Author author;

	@Relationship(value = "comments", resolve = true)
	private List<Comment> comments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
