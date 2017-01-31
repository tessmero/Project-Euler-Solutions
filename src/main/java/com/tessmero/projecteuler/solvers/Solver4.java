package com.tessmero.projecteuler.solvers;

/**
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * 
 * @author Oliver
 */
public class Solver4 extends Solver{

  @Override
  public long doSolution() {
    return getLargestPalindromeProduct( 100, 999 );
  }

  @Override
  public long doTest() {
    return getLargestPalindromeProduct( 10, 99 );
  }

  @Override
  public long getExpectedTestResult() {
    return 9009;
  }
  
  private long getLargestPalindromeProduct( long minFactor, long maxFactor ) {
    long factorA;
    long factorB;
    long product;
    
    for ( factorA = maxFactor ; factorA >= minFactor ; factorA-- ) {
      for ( factorB = factorA ; factorB >= minFactor ; factorB-- ) {
        product = factorA * factorB;
        if ( isPalindrome( product ) ) {
          return product;
        }
      }
    }
    
    return -1;
  }
  
  private boolean isPalindrome( long number ) {
    String str = String.valueOf( number );
    int len = str.length();
    for ( int i = 0 ; i < len / 2 ; i++ ) {
      if ( str.charAt(i) != str.charAt( len - i - 1 ) ) {
        return false;
      }       
    }
    return true;
  }
}
