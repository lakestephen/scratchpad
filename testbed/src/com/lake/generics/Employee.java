package com.lake.generics;

public class Employee extends TaxPayer<Employee> {

	public Employee(TaxStrategy<Employee> strategy) {
		super(strategy);
	}

	@Override
	protected Employee getDetailedType() {
		return this;
	}
}
