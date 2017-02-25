package com.tessmero.projecteuler.solvers.impl;

import com.tessmero.projecteuler.solvers.LongSolver;

/**
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * 
 * @author Oliver
 */
public class Solver1 extends LongSolver {

  @Override
  public long doSolution() {
    return getSumOfMultiples( 1000 );
  }

  @Override
  public long doTest() {
    return getSumOfMultiples( 10 );
  }

  @Override
  public long getExpectedTestResult() {
    return 23;
  }
  
  private long getSumOfMultiples( long ceiling ) {
    long result = 0;
    for ( long i = 1 ; i < ceiling ; i++ ) {
      if ( i % 3 == 0 || i % 5 == 0 ) {
        result += i;
      }
    }
    return result;
  }
}
