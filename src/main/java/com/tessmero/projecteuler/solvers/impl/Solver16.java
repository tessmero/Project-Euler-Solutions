/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static com.tessmero.projecteuler.util.BigInts.bigInt;
import static com.tessmero.projecteuler.util.BigInts.streamDigits;

import com.tessmero.projecteuler.solvers.LongSolver;

import java.math.BigInteger;

/**
 * What is the sum of the digits of the number 2^1000.
 * 
 * @author Oliver
 */
public class Solver16 extends LongSolver{

  @Override
  public long doSolution() throws Exception {
    return getDigitSum( bigInt( 2 ).pow( 1000 ) );
  }

  @Override
  public long doTest() throws Exception {
    return getDigitSum( bigInt( 2 ).pow( 15 ) );
  }

  @Override
  public long getExpectedTestResult() {
    return 26;
  }
  
  private long getDigitSum( BigInteger num ) {
    return streamDigits( num ).sum();
  }
}
