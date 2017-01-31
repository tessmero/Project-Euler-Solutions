package com.tessmero.projecteuler.solvers;

/**
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 * 
 * @author Oliver
 */
public class Solver9 extends Solver{

  @Override
  public long doSolution() {
    return getPythagoreanTripleProduct( 1000 );
  }

  @Override
  public long doTest() {
    return getPythagoreanTripleProduct( 3 + 4 + 5 );
  }

  @Override
  public long getExpectedTestResult() {
    return 3 * 4 * 5;
  }
  
  private long getPythagoreanTripleProduct( int tripleSum ) {
    for ( int tc = 1 ; tc < tripleSum ; tc++ ) {
      for ( int tb = 1 ; tb < tripleSum - tc ; tb++ ) {
        int ta = tripleSum - tb - tc;
        if ( ta * ta + tb * tb == tc * tc ) {
          return ta * tb * tc;
        }
      }
    }
    throw new Error( "could not find any pythagorean triples with sum " + tripleSum );
  }
}
