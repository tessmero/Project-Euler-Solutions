/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ROUND_DOWN;
import static org.slf4j.LoggerFactory.getLogger;

import com.tessmero.projecteuler.solvers.LongSolver;

import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Find the value of d less than 1000 for which 1/d contains the longest recurring cycle in its
 * decimal fraction part.
 *
 * @author Oliver
 */
public class Solver26 extends LongSolver {

  private static final Logger logger = getLogger(Solver26.class);

  private static final int minRecurrences = 5;
  private static final int maxCycleLen = 6;
  private static final int numDigitsToCheck = minRecurrences * maxCycleLen;

  @Override
  public long doSolution() throws Exception {
    return getDWithLongestRecurringCycle(1000);
  }

  @Override
  public long doTest() throws Exception {
    return getDWithLongestRecurringCycle(10);
  }

  @Override
  public long getExpectedTestResult() {
    return 7;
  }

  private int getDWithLongestRecurringCycle(int ceil) {
    int maxCycleLength = 0;
    for (int testD = 2; testD < ceil; testD++) {
      logger.info("testing D = " + testD);
      BigDecimal bd = ONE.divide(new BigDecimal(testD), numDigitsToCheck, ROUND_DOWN);
      int cycleLength = getRecurringCycleLength(bd);
      if (cycleLength > maxCycleLength) {
        maxCycleLength = cycleLength;
      }
    }
    return maxCycleLength;
  }

  private int getRecurringCycleLength(BigDecimal num) {
    int[] digits = num.toString().chars().skip(2).map(ch -> ch - 48).toArray();
    logger.info("got digits: " + Arrays.toString(digits));
    if (digits.length < numDigitsToCheck) {
      return 0;
    }

    loopTestCycleLen:
    for (int testCycleLen = 1; testCycleLen < maxCycleLen; testCycleLen++) {
      for (int recurIndex = 1; recurIndex < minRecurrences; recurIndex++) {
        if (!subArraysMatch(digits, 0, testCycleLen * recurIndex, testCycleLen)) {
          continue loopTestCycleLen;
        }
      }
      return testCycleLen;
    }

    return 0;
  }

  private boolean subArraysMatch(int[] arr, int indexA, int indexB, int len) {
    for (int i = 0; i < len; i++) {
      if (arr[indexA + i] != arr[indexB + i]) {
        return false;
      }
    }
    return true;
  }
}