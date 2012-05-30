package com.lake.documentation.extract;

import java.lang.reflect.Method;

import org.junit.Test;

import com.lake.client.beans.Instrument;
import com.lake.documentation.annotation.RelationshipDocumentation;
import com.lake.documentation.structure.BeanMeta;
import com.lake.structure.Bean;

/**
 * SJL comment
 * 
 * @author Stephen Lake
 * 
 */

public class Extract {

	@Test
	public void testExtraction() {
		extract(Instrument.class);
	}
	
	public void extract(Class<? extends Bean> rootClass) {

		BeanMeta bm = new BeanMeta();
	//	bm.setName(rootClass.getName());

		for (Method m : rootClass.getMethods()) {
			
			// Is this method of the passed in class?
			if (m.getDeclaringClass() == rootClass) {
				if (m.isAnnotationPresent(RelationshipDocumentation.class)) {
					RelationshipDocumentation ann = m.getAnnotation(RelationshipDocumentation.class);
					String value = ann.value();
					System.out.println(value);
				}
			}
		}
		
		System.out.println(bm);
	}

}
