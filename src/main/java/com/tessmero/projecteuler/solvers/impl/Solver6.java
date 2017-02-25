package com.tessmero.projecteuler.solvers.impl;

import static java.lang.Math.abs;

import com.tessmero.projecteuler.solvers.LongSolver;

/**
 * Find the difference between the sum of the squares of the first one hundred natural numbers and 
 * the square of the sum.
 * 
 * @author Oliver
 */
public class Solver6 extends LongSolver{

  @Override
  public long doSolution() {
    return getD( 100 );
  }

  @Override
  public long doTest() {
    return getD( 10 );
  }

  @Override
  public long getExpectedTestResult() {
    return 2640;
  }
 
  private long getD( long max ) {
    return abs( getSumOfSquares( max ) - getSquareOfSum( max ) );
  }
  
  private long getSumOfSquares( long max ) {
    long sum = 0;
    for ( long i = 1 ; i <= max ; i++ ) {
      sum += i * i;
    }
    return sum;
  }
  
  private long getSquareOfSum( long max ) {
    long sum = 0;
    for ( long i = 1 ; i <= max ; i++ ) {
      sum += i;
    }
    return sum * sum;
  }
}
