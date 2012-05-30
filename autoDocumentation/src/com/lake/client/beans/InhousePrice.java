package com.lake.client.beans;

import com.lake.documentation.annotation.RelationshipDocumentation;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */

public class InhousePrice {

	CobPrice cobPriceToday;
	CobPrice cobPriceYesterday;
	
	@RelationshipDocumentation("This FID's for CobPriceToday are prefixed with CT_")
	public final CobPrice getCobPriceToday() {
		return cobPriceToday;
	}
	
	@RelationshipDocumentation("FID's are prefixed with CL_")
	public final CobPrice getCobPriceYesterday() {
		return cobPriceYesterday;
	}
}
