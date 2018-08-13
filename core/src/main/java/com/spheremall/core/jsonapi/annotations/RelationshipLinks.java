package com.spheremall.core.jsonapi.annotations;

import com.spheremall.core.jsonapi.Links;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Relationship links annotation.
 *
 * This annotation is used in conjunction with @Relationship annotation, field annotated with this annotation
 * is considered to be {@link Links} related to named relationship object.
 *
 * <pre>
 * <code>
 *     {@literal @}Type("my-type")
 *     public class MyType {
 *     	   {@literal @}Id
 *         private String id;
 *
 *         {@literal @}Relationship("relationship")
 *         private MyRelationship myRelationship;
 *
 *         {@literal @}RelationshipLinks("relationship")
 *         private com.github.jasminb.jsonapi.Links links;
 *
 *     }
 * </code>
 * </pre>
 *
 * @author jbegic
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RelationshipLinks {
	String value();
}
