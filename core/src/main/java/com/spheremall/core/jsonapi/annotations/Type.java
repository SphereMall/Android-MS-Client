package com.spheremall.core.jsonapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used for declaring type as a JSON API resource.
 *
 * @author jbegic
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Type {
	/**
	 * BaseResource type name.
	 */
	String value();
	
	/**
	 * BaseResource path, used to generate <code>self</code> link.
	 */
	String path() default "";
}
