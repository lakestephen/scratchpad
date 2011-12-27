package com.lake.generics;

public interface TaxStrategy<P extends TaxPayer<P>> {
  public double extortCash(P p);
}


