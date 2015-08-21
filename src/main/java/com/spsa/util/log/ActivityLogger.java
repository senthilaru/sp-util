/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spsa.util.log;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * The functionality of this class is similar to the method toString() in a java
 * domain object, but it scan the @Mask
 * annotation and avoid printing the values of it.
 *
 * It scans through the object graph deeper and finds the @Mask annotation and
 * manipulates the values.
 *
 * @author Senthil Arumugam, Samiraj Panneer Selvam
 * @since 1.0.0
 *
 */
public class ActivityLogger {
	private static final String SERIAL_VERSION_UID = "serialVersionUID";
	private static final String ELEMENT_DATA = "elementData";
	private static final String JAVA_LANG = "java.lang";
	private final static String EQAULS = "=";
	private final static String SQ_BRACKET_OPEN = "[";
	private final static String SQ_BRACKET_CLOSE = "]";
	private final static String BRACE_OPEN = "(";
	private final static String BRACE_CLOSE = ")";
	private final static String COMMA = ", ";
	private final static String STRING = "java.lang.String";

	public <T> String toString(T instance) {
		StringBuilder result = new StringBuilder(100);
		result.append(SQ_BRACKET_OPEN);
		Field[] fields = instance.getClass().getDeclaredFields();
		int noOfFields = fields.length;
		for (Field field : fields) {
			noOfFields--;
			field.setAccessible(true);
			if (excludeField(instance, field)) {
				continue;
			}
			try {
				if (!needDeepDive(field)) {
					result.append(field.getName());
					result.append(EQAULS);
					if (field.isAnnotationPresent(Mask.class)) {
						result.append("XX");
					} else {
						result.append(field.get(instance));
					}
					if (noOfFields != 0) {
						result.append(COMMA);
					}
				} else if (Collection.class.isAssignableFrom(field.getType())) {
					Collection<?> collection = (Collection<?>) field.get(instance);
					result.append(field.getName());
					result.append(EQAULS);
					result.append(SQ_BRACKET_OPEN);
					for (Object object : collection) {
						result.append(toString(object));
					}
					result.append(SQ_BRACKET_CLOSE + COMMA);
				} else if (field.getType().isArray()) {
					Object[] collection = (Object[]) field.get(instance);
					result.append(field.getName());
					result.append(EQAULS);
					result.append(BRACE_OPEN);
					int noOfStringFields = collection.length;
					for (Object object : collection) {
						noOfStringFields--;
						if (object == null && noOfStringFields == 0) {
							result.delete(result.length() - 1, result.length());
						} else if (object == null) {
							continue;
						} else if (object.getClass().getCanonicalName().equals(STRING)) {
							result.append(object.toString());
							result.append(COMMA);
						} else {
							result.append(toString(object));
						}
					}
					result.deleteCharAt(result.length() - 1);
					result.append(BRACE_CLOSE);
				} else {
					result.append(toString(field.get(instance)));
				}
			} catch (IllegalAccessException e) {
				System.err.println(e);
				e.printStackTrace();
			}

		}
		result.append(SQ_BRACKET_CLOSE);
		return result.toString();
	}

	/**
	 * It excludes the unnecessary fields in the object graph.
	 *
	 * @param instance
	 * @param field
	 * @return true/false
	 */
	private <T> boolean excludeField(T instance, Field field) {
		if ((instance instanceof Collection<?> && !field.getName().equalsIgnoreCase(ELEMENT_DATA))
				|| field.getName().equalsIgnoreCase(SERIAL_VERSION_UID)) {
			return true;
		}
		return false;
	}

	/**
	 * It checks whether needs to dive deep into the object graph to scan
	 * through the @Mask annotation
	 *
	 * @param field
	 * @return true/false
	 */
	private boolean needDeepDive(Field field) {
		boolean result = true;
		if (field.isAnnotationPresent(Mask.class)) {
			result = false;
		} else if (!field.getType().isArray()
				&& (field.getType().getCanonicalName().startsWith(JAVA_LANG) || field.getType().isPrimitive())) {
			result = false;
		}
		return result;
	}
}
