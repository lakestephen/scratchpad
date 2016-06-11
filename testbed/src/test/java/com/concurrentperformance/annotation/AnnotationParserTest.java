package com.concurrentperformance.annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;

/**
 * TODO
 * User: Stephen
 */
public class AnnotationParserTest {

	@Test
	public void testParse() {
		parse(TopologyService.class);
	}

	private void parse(Class<?> type) {

		Annotation[] annotations = type.getAnnotations();
		Annotation[] declaredAnnotations = type.getDeclaredAnnotations();

		System.out.println(annotations);
		System.out.println(declaredAnnotations);

	}
}
