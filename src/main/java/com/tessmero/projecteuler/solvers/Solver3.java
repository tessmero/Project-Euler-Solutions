package com.tessmero.projecteuler.solvers;

import static com.tessmero.projecteuler.util.Primes.getPrimeFactors;

import java.util.List;

/**
 * What is the largest prime factor of the number 600851475143.
 * 
 * @author Oliver
 */
public class Solver3 extends Solver {

  @Override
  public long doSolution() {
    return getLargestPrimeFactor( 600851475143L );
  }

  @Override
  public long doTest() {
    return getLargestPrimeFactor( 13195 );
  }

  @Override
  public long getExpectedTestResult() {
    return 29;
  }
  
  private long getLargestPrimeFactor( long num ) {
    List<Long> allPrimeFactors = getPrimeFactors( num );
    return allPrimeFactors.stream().max(Long::compareTo).get();
  }
}
