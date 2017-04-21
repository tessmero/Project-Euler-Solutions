/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static org.slf4j.LoggerFactory.getLogger;

import com.tessmero.projecteuler.solvers.LongSolver;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.slf4j.Logger;

/**
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31
 * Dec 2000).
 *
 * @author Oliver
 */
public class Solver19 extends LongSolver {
  private final Logger logger = getLogger(getClass());

  @Override
  public long doSolution() throws Exception {
    return countSundayTheFirsts(
        new GregorianCalendar(1901, 0, 1), new GregorianCalendar(2000, 11, 31));
  }

  @Override
  public long doTest() throws Exception {
    return doSolution();
  }

  @Override
  public long getExpectedTestResult() {
    return 171;
  }

  private int countSundayTheFirsts(GregorianCalendar startDate, GregorianCalendar endDate) {
    GregorianCalendar date = (GregorianCalendar) startDate.clone();
    int count = 0;
    for (; !date.after(endDate); date.add(DATE, 1)) {
      if (date.get(DAY_OF_MONTH) == 1 && date.get(DAY_OF_WEEK) == Calendar.SUNDAY) {
        count++;
      }
    }
    return count;
  }
}
