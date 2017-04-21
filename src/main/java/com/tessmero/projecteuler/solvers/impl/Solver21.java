/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static java.lang.Math.sqrt;
import static java.util.stream.LongStream.range;

import com.tessmero.projecteuler.solvers.LongSolver;

/**
 * Evaluate the sum of all the amicable numbers under 10000.
 *
 * <p>If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b
 * are called amicable numbers.
 *
 * @author Oliver
 */
public class Solver21 extends LongSolver {

  @Override
  public long doSolution() throws Exception {
    long ceil = 10000;
    long[] dvalues = range(0, ceil).map(num -> getD(num)).toArray();

    return range(0, ceil)
        .filter(
            num -> {
              long dval = dvalues[(int) num];
              return dval < ceil && dval != num && dvalues[(int) dval] == num;
            })
        .sum();
  }

  @Override
  public long doTest() throws Exception {
    return getD(284);
  }

  @Override
  public long getExpectedTestResult() {
    return 220;
  }

  private long getD(long num) {
    if (num == 0) {
      return 0;
    }

    long result = 1;

    long ceil = (long) sqrt(num);
    if (num % ceil == 0) {
      result += ceil;
    }

    for (long div = 2; div < ceil; div++) {
      if (num % div == 0) {
        result += div;
        result += num / div;
      }
    }

    return result;
  }
}
