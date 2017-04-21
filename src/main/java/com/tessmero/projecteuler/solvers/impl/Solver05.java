package com.tessmero.projecteuler.solvers.impl;

import com.tessmero.projecteuler.solvers.LongSolver;

/**
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20.
 *
 * @author Oliver
 */
public class Solver05 extends LongSolver {

  @Override
  public long doSolution() {
    return getSmallestProduct(20);
  }

  @Override
  public long doTest() {
    return getSmallestProduct(10);
  }

  @Override
  public long getExpectedTestResult() {
    return 2520;
  }

  private long getSmallestProduct(long largestFactor) {
    for (long test = largestFactor; ; test += largestFactor) {
      if (isEvenlyDivisible(largestFactor, test)) {
        return test;
      }
    }
  }

  private boolean isEvenlyDivisible(long largestFactor, long test) {
    for (int factor = 2; factor <= largestFactor; factor++) {
      if (test % factor != 0) {
        return false;
      }
    }
    return true;
  }
}
