/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static org.slf4j.LoggerFactory.getLogger;

import com.tessmero.projecteuler.solvers.LongSolver;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

/**
 * Which starting number, under one million, produces the longest Collatz sequence.
 *
 * @author Oliver
 */
public class Solver14 extends LongSolver {
  private final Logger logger = getLogger(getClass());

  @Override
  public long doSolution() throws Exception {
    int result = 0;
    int maxLen = 0;
    for (int start = 1; start < 1000000; start++) {
      int len = getCollatzSequenceLength(start);
      if (len > maxLen) {
        maxLen = len;
        result = start;
      }
    }
    return result;
  }

  @Override
  public long doTest() throws Exception {
    return getCollatzSequenceLength(13);
  }

  @Override
  public long getExpectedTestResult() {
    return 10;
  }

  private final Map<Long, Integer> collatzLengthsByStart = new HashMap();

  private int getCollatzSequenceLength(long startNum) {
    if (startNum == 1) {
      return 1;
    } else if (collatzLengthsByStart.containsKey(startNum)) {
      return collatzLengthsByStart.get(startNum);
    }

    int result;
    if (startNum % 2 == 0) {
      result = 1 + getCollatzSequenceLength(startNum / 2);
    } else {
      result = 1 + getCollatzSequenceLength(startNum * 3 + 1);
    }
    collatzLengthsByStart.put(startNum, result);
    return result;
  }
}
