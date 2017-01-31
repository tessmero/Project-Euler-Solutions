package com.tessmero.projecteuler.solvers;

import static com.tessmero.projecteuler.util.Primes.getPrimes;

/**
 * Find the sum of all the primes below two million.
 * 
 * @author Oliver
 */
public class Solver10 extends Solver{

  @Override
  public long doSolution() {
    return getPrimes( 2000000 ).stream().mapToLong(Long::longValue).sum();
  }

  @Override
  public long doTest() {
    return getPrimes( 10 ).stream().mapToLong(Long::longValue).sum();
  }

  @Override
  public long getExpectedTestResult() {
    return 17;
  }
  
}
