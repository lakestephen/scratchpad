package com.concurrentperformance.property;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * TODO comments.
 *
 * @author stephen
 */
public class PropertyTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void doIt() {
		for (Map.Entry<Object, Object> objectObjectEntry : System.getProperties().entrySet()) {

			log.info(objectObjectEntry.toString());
		}
	}
}
