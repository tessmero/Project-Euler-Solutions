package com.tessmero.projecteuler.solvers.impl;

import com.tessmero.projecteuler.solvers.LongSolver;
import com.tessmero.projecteuler.util.Primes.PrimeSupplier;

/**
 * Find the sum of all the primes below two million.
 *
 * @author Oliver
 */
public class Solver10 extends LongSolver {

  @Override
  public long doSolution() {
    return getSumOfPrimes(2000000L);
  }

  @Override
  public long doTest() {
    return getSumOfPrimes(10);
  }

  @Override
  public long getExpectedTestResult() {
    return 17;
  }

  private long getSumOfPrimes(long ceil) {
    long prime;
    long sum = 0L;
    PrimeSupplier ps = new PrimeSupplier();
    while ((prime = ps.getAsLong()) < ceil) {
      sum += prime;
    }
    return sum;
  }
}
