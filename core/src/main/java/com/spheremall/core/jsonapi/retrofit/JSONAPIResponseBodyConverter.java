package com.spheremall.core.jsonapi.retrofit;

import com.spheremall.core.jsonapi.ResourceConverter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * JSON API response body converter.
 *
 * @author jbegic
 */
public class JSONAPIResponseBodyConverter<T> implements Converter<ResponseBody, T> {
	private final Class<?> clazz;
	private final Boolean isCollection;
	private final ResourceConverter parser;

	/**
	 * Creates new JSONAPIResponseBodyConverter.
	 * @param parser {@link ResourceConverter} parser instance
	 * @param clazz {@link Class} class to be handled
	 * @param isCollection {@link Boolean} flag that denotes if processed resource is a single object or collection
	 */
	public JSONAPIResponseBodyConverter(ResourceConverter parser, Class<?> clazz, boolean isCollection) {
		this.clazz = clazz;
		this.isCollection = isCollection;
		this.parser = parser;
	}

	@Override
	public T convert(ResponseBody responseBody) throws IOException {
		if (isCollection) {
			return (T) parser.readDocumentCollection(responseBody.byteStream(), clazz).get();
		} else {
			return (T) parser.readDocument(responseBody.byteStream(), clazz).get();
		}
	}
}
