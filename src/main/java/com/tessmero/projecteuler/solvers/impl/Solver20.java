/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static com.tessmero.projecteuler.util.BigInts.factorial;
import static com.tessmero.projecteuler.util.BigInts.getDigitSum;

import com.tessmero.projecteuler.solvers.LongSolver;

/**
 * Find the sum of the digits in the number 100!.
 * 
 * @author Oliver
 */
public class Solver20 extends LongSolver{

  @Override
  public long doSolution() throws Exception {
    return getDigitSum( factorial( 100 ) );
  }

  @Override
  public long doTest() throws Exception {
    return getDigitSum( factorial( 10 ) );
  }

  @Override
  public long getExpectedTestResult() {
    return 27;
  }
  
}
