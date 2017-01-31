package com.tessmero.projecteuler.solvers;

import static java.lang.Long.max;
import static java.lang.Math.sqrt;

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
    if ( isPrime( num ) ) {
      return num;
    }
    long maxFactor = (long)sqrt( num ) + 1;
    for ( long i = 2 ; i < maxFactor ; i++ ) {
      if ( num % i == 0 && isPrime( i ) ) {
        return max( i, getLargestPrimeFactor( num / i ) );
      }
    }
    return -1;
  }
  
  private boolean isPrime( long num ) {
    long maxFactor = (long)sqrt( num );
    for ( long i = 2 ; i < maxFactor ; i++ ) {
      if ( num % i == 0 ) {
        return false;
      }
    }
    return true;
  }
}
