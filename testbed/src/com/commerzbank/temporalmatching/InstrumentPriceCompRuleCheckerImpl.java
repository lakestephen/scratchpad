package com.commerzbank.temporalmatching;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class InstrumentPriceCompRuleCheckerImpl implements
		InstrumentPriceCompRuleChecker {

	private RMDSListner instrument1RMDSRecordListener = new RMDSListner() {
		@Override
		public void recordUpdated(Record record) {
			instrument1RecordUpdated(record);			
		}
	};
	
	private RMDSListner instrument2RMDSRecordListener = new RMDSListner() {
		@Override
		public void recordUpdated(Record record) {
			instrument2RecordUpdated(record);			
		}
	};
	/**
	 * @param rule
	 */
	public InstrumentPriceCompRuleCheckerImpl(InstrumentPriceCompRule rule) {
		// SJL Auto-generated constructor stub
	}

	@Override
	public RMDSListner getInstrument1Listener() {
		return instrument1RMDSRecordListener;
	}
	public void instrument1RecordUpdated(Record record) {
		System.out.println("1 [" + record.getValue("BID") + "]");
	}
	
	@Override
	public RMDSListner getInstrument2Listener() {
		return instrument2RMDSRecordListener;
	}
	public void instrument2RecordUpdated(Record record) {
		System.out.println("2          [" + record.getValue("BID") + "]");
	}

}
