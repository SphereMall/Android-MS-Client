package com.spheremall.core.jsonapi.annotations;

import com.spheremall.core.jsonapi.RelType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to configure relationship field in JSON API resources.
 *
 * @author jbegic
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Relationship {
	String value();
	boolean resolve() default false;
	boolean serialise() default true;
	RelType relType() default RelType.SELF;
	
	/**
	 * BaseResource path, used to generate <code>self</code> link.
	 */
	String path() default "";
	
	/**
	 * BaseResource path, used to generate <code>related</code> link.
	 */
	String relatedPath() default "";
}
