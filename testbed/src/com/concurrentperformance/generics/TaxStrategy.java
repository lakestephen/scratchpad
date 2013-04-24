package com.concurrentperformance.generics;

public interface TaxStrategy<P extends TaxPayer<P>> {
  public double extortCash(P p);
}


