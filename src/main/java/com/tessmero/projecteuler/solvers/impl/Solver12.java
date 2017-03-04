/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import com.tessmero.projecteuler.solvers.LongSolver;
import com.tessmero.projecteuler.util.Primes.PrimeSupplier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongSupplier;
import java.util.stream.LongStream;

/**
 * What is the value of the first triangle number to have over five hundred divisors.
 * 
 * @author Oliver
 */
public class Solver12 extends LongSolver{

  @Override
  public long doSolution() throws Exception {
    return getTriangleNumber( 500 );
  }

  @Override
  public long doTest() throws Exception {
    return getTriangleNumber( 5 );
  }

  @Override
  public long getExpectedTestResult() {
    return 28;
  }
  
  private long getTriangleNumber( int numDivisorsFloor ) {
    return triangleNumbers()
            .filter( num -> countDivisors( num ) > numDivisorsFloor )
            .findFirst().getAsLong();
  }
  
  private int countDivisors( long num ) {
    PrimeSupplier ps = new PrimeSupplier();
    long prime;
    long maxDivisor = num / 2;
    List<Long> divisors = new ArrayList( Arrays.asList( 1L ) );
    while ( (prime = ps.getAsLong()) <= maxDivisor ) {
      for ( long primeMult = prime ; primeMult <= maxDivisor ; primeMult += prime ) {
        if ( num % primeMult == 0 ) {
          if ( !divisors.contains( primeMult ) ) {
            divisors.add( primeMult );
          }
        } else {
          break;
        }
      }
    }    
    return divisors.size() + 1;
  }
  
  private LongStream triangleNumbers() {
    return LongStream.generate( new LongSupplier(){
      private int index = 1;
      private long prev = 0;
      @Override
      public long getAsLong() {
        return (prev = prev + (index++));
      }
    });
  }
}
