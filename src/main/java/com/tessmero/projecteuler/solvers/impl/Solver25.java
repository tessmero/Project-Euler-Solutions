/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;


import static com.tessmero.projecteuler.util.BigInts.streamDigits;

import com.tessmero.projecteuler.solvers.LongSolver;
import com.tessmero.projecteuler.util.BigIntFibonacci;


/**
 * What is the index of the first term in the Fibonacci sequence to contain 1000 digits.
 * 
 * @author Oliver
   */
public class Solver25 extends LongSolver{
  
  @Override
  public long doSolution() throws Exception {
    return getFirstIndexWithNDigits( 1000 );
  }

  @Override
  public long doTest() throws Exception {
    return getFirstIndexWithNDigits( 3 );
  }

  @Override
  public long getExpectedTestResult() {
    return 12;
  }
  
  private long getFirstIndexWithNDigits( int numDigits ) {
    BigIntFibonacci fib = new BigIntFibonacci();
    while ( streamDigits( fib.getValue() ).count() < numDigits ) {
      fib.increment();
    }
    return fib.getIndex();
  }
}
