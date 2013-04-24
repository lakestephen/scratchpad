package com.concurrentperformance.generics;

public class EmployeeTaxStrategy implements TaxStrategy<Employee> {

	public double extortCash(Employee p) {
		return 0;
	}
}
