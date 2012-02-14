package com.commerzbank.temporalmatching;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public interface InstrumentPriceCompRuleChecker {

	public abstract RMDSListner getInstrument2Listener();

	public abstract RMDSListner getInstrument1Listener();

}
