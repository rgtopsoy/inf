package com.rgtopsoy.salestaxes.logger;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author R. Gursoy Topsoy
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
@Inherited
public @interface InjectLogger {
	
}
