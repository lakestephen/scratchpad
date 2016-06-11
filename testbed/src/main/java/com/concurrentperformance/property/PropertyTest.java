package com.concurrentperformance.property;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.Map;

/**
 * TODO comments.
 *
 * @author stephen
 */
public class PropertyTest {

	private final Log log = LogFactory.getLog(this.getClass());

	@Test
	public void doIt() {
		for (Map.Entry<Object, Object> objectObjectEntry : System.getProperties().entrySet()) {

			log.info(objectObjectEntry);
		}
	}
}
