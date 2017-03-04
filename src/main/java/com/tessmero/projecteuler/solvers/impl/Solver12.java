/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static org.slf4j.LoggerFactory.getLogger;

import com.tessmero.projecteuler.solvers.LongSolver;
import org.slf4j.Logger;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;

/**
 * What is the value of the first triangle number to have over five hundred divisors.
 * 
 * @author Oliver
 */
public class Solver12 extends LongSolver{
  private final Logger logger = getLogger(getClass());

  @Override
  public long doSolution() throws Exception {
    return getDivisibleTriangleNumber( 500 );
  }

  @Override
  public long doTest() throws Exception {
    return getDivisibleTriangleNumber( 5 );
  }

  @Override
  public long getExpectedTestResult() {
    return 28;
  }
  
  private long getDivisibleTriangleNumber( int numDivisorsFloor ) {
    return triangleNumbers()
            .filter( num -> countDivisors( num ) > numDivisorsFloor )
            .findFirst().getAsLong();
  }
  
  int countDivisors(long num) {
    long limit = num;
    int numberOfDivisors = 0;

    if (num == 1) {
      return 1;
    }

    for (int div = 1; div < limit; ++div) {
      if (num % div == 0) {
        limit = num / div;
        if (limit != div) {
          numberOfDivisors++;
        }
        numberOfDivisors++;
      }
    }

    return numberOfDivisors;
  }
  
  private LongStream triangleNumbers() {
    return LongStream.generate( new LongSupplier(){
      private int index = 1;
      private long prev = 0;
      @Override
      public long getAsLong() {
        return (prev = prev + (index++));
      }
    });
  }
}
