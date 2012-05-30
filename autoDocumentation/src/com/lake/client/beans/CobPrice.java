package com.lake.client.beans;

import com.lake.documentation.annotation.RelationshipDocumentation;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */

@RelationshipDocumentation("Driven by EBOND_COB service. " +
						"The record key format: <instrumentid>=<loc> where <loc> is the users location (COB = general snap, NYO = New York Snap, SGP = Singapore Snap)." +
						"Each relationship to a CobPrice is bound to a single FID prefix")
public class CobPrice {

	double bid;
	double ask;

	@RelationshipDocumentation("FID = <PREFIX>_BID")
 //   @PropertyMetaData(displayName = "My Bid")
	double getBid() {
		return bid;
	}	

	@RelationshipDocumentation("FID = <PREFIX>_ASK")
	double getAsk() {
		return ask;
	}	
}
