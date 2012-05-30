package com.lake.client.beans;

import com.lake.documentation.annotation.RelationshipDocumentation;
import com.lake.structure.Bean;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class Instrument implements Bean {
	
	private InhousePrice inhousePrice = new InhousePrice();
	
	@RelationshipDocumentation("The inhouse price driven from RMDS pricing service EBOND_FFT / EBOND_LDN (Ueer selectable at startup)")
	public InhousePrice getInhousePrice() {
		return inhousePrice;
	}
	
}
