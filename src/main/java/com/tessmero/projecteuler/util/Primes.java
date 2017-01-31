package com.tessmero.projecteuler.util;

import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides static convenience functions to test for primality and retrieve prime numbers.
 * 
 * @author Oliver
 */
public class Primes {
  
  /**
   * Compute a list of the first n primes.
   * 
   * @param count the desired number of primes
   * @return a list of primes with length = count
   */
  public static List<Long> getPrimes( int count ) {
    List<Long> result = new ArrayList();
    
    if ( count == 0 ) {
      return result;
    }
    
    result.add( 2L );
    for ( long test = 3 ; result.size() < count ; test += 2 ) {
      boolean isPrime = true;
      for (long lowerPrime : result) {
        if (test % lowerPrime == 0) {
          isPrime = false;
          break;
        }
      }
      if ( isPrime ) {
        result.add( test );
      }
    }
    
    return result;
  }
  
  /**
   * Compute the prime factors for a given number.
   * 
   * @param num the number to factorize
   * @return a list of prime factors, some of which may repeat
   */
  public static List<Long> getPrimeFactors( long num ) {
    List<Long> result = new ArrayList();
    
    if ( isPrime( num ) ) {
      result.add( num );
      return result;
    }
    long maxFactor = (long)sqrt( num ) + 1;
    for ( long i = 2 ; i < maxFactor ; i++ ) {
      if ( num % i == 0 && isPrime( i ) ) {
        result.add( i );
        result.addAll( getPrimeFactors( num / i ) );
        return result;
      }
    }
    
    return result;
  }
  
  /**
   * Test whether or not a number is prime.
   * 
   * @param num the number to test for primality
   * @return true if the given number is prime
   */
  public static boolean isPrime( long num ) {
    long maxFactor = (long)sqrt( num );
    for ( long i = 2 ; i < maxFactor ; i++ ) {
      if ( num % i == 0 ) {
        return false;
      }
    }
    return true;
  }
}
