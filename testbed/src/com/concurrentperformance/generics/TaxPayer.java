package com.concurrentperformance.generics;

public abstract class TaxPayer<P extends TaxPayer<P>> {

	TaxStrategy<P> stratergy;
	
	public TaxPayer(TaxStrategy<P> stratergy) {
		this.stratergy = stratergy;
	}	
	
	abstract protected P getDetailedType();
	
	double exhortCash() {
		return stratergy.extortCash(getDetailedType());
	}
}
 