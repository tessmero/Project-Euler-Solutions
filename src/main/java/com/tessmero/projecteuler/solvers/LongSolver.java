/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers;

/**
 * Most widely-applicable type of solver, where the solution can be represented as a {@link
 * java.lang.Long}.
 *
 * @author Oliver
 */
public abstract class LongSolver implements Solver {

  public abstract long doSolution() throws Exception;

  public abstract long doTest() throws Exception;

  public abstract long getExpectedTestResult();

  @Override
  public String doSolutionStr() throws Exception {
    return String.valueOf(doSolution());
  }

  @Override
  public String doTestStr() throws Exception {
    return String.valueOf(doTest());
  }

  @Override
  public String getExpectedTestResultStr() {
    return String.valueOf(getExpectedTestResult());
  }
}
