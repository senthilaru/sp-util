package com.spsa.util.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  Indicates the field will be masked in the activity logger
 * {@link ActivityLogger}
 *
 * @author Senthil Arumugam, Samiraj Panneer Selvam
 * @since 1.0.0
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public @interface Mask {

}
