package com.lake.generics;

public class EmployeeTaxStrategy implements TaxStrategy<Employee> {

	public double extortCash(Employee p) {
		return 0;
	}
}
