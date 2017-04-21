package com.tessmero.projecteuler.solvers.impl;

import com.tessmero.projecteuler.solvers.LongSolver;
import com.tessmero.projecteuler.util.Fibonacci;

/**
 * By considering the terms in the Fibonacci sequence whose values do not exceed four million, find
 * the sum of the even-valued terms.
 *
 * @author Oliver
 */
public class Solver02 extends LongSolver {

  @Override
  public long doSolution() {
    return getEvenFibSum(4000000);
  }

  @Override
  public long doTest() {
    return getEvenFibSum(89);
  }

  @Override
  public long getExpectedTestResult() {
    return 2 + 8 + 34;
  }

  private long getEvenFibSum(long max) {
    long result = 0;

    long val;
    Fibonacci fib = new Fibonacci();

    while ((val = fib.getValue()) <= max) {
      if (val % 2 == 0) {
        result += val;
      }
      fib.increment();
    }

    return result;
  }
}
