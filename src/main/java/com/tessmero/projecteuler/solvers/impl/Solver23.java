package com.tessmero.projecteuler.solvers.impl;

import com.tessmero.projecteuler.solvers.LongSolver;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

/**
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant
 * numbers.
 *
 * <p>A number n is called deficient if the sum of its proper divisors is less than n and it is
 * called abundant if this sum exceeds n.
 *
 * <p>As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be
 * written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that
 * all integers greater than 28123 can be written as the sum of two abundant numbers. However, this
 * upper limit cannot be reduced any further by analysis even though it is known that the greatest
 * number that cannot be expressed as the sum of two abundant numbers is less than this limit.
 *
 * @author Oliver
 */
public class Solver23 extends LongSolver {

  public Solver23() {}

  @Override
  public long doSolution() throws Exception {
    return getAnswerSum(28123);
  }

  @Override
  public long doTest() throws Exception {
    return getAnswerSum(24);
  }

  /** Expect the sum of the natural numbers BELOW 24. */
  @Override
  public long getExpectedTestResult() {
    return LongStream.range(1, 24).sum();
  }

  /**
   * Compute the sum of all the positive integers, not exceeding the given maximum, which CANNOT be
   * written as the sum of two abundant numbers
   *
   * <p>As stated in the problem description, if max >= 28123, the result should be the solution to
   * the problem.
   *
   * @param max
   * @return
   */
  private long getAnswerSum(long max) {
    List<Long> abundantNumbers = listAbundantNumbers(max);

    // get a set of all unique integers which are sums of two abundant numbers
    Set<Long> pairSums = new HashSet();
    for (long a : abundantNumbers) {
      for (long b : abundantNumbers) {
        pairSums.add(a + b);
      }
    }

    long result = 0;
    for (long i = 1; i <= max; i++) {
      if (!pairSums.contains(i)) {
        result += i;
      }
    }

    return result;
  }

  /** Get a list of all abundant numbers not exceeding the given maximum. */
  private List<Long> listAbundantNumbers(long max) {
    List<Long> result = new ArrayList();
    for (long i = 1; i < max; i++) {
      if (isAbundant(i)) {
        result.add(i);
      }
    }
    return result;
  }

  private boolean isAbundant(long num) {
    long max = (long) Math.sqrt(num);
    Set<Long> properDivisors = new HashSet();
    properDivisors.add((long) 1);
    for (long i = 2; i <= max; i++) {
      if (num % i == 0) {
        properDivisors.add(i);
        properDivisors.add(num / i);
      }
    }

    long sum = properDivisors.stream().mapToLong(l -> l).sum();
    return sum > num;
  }
}
