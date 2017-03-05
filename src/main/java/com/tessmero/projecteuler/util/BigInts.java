/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.util;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import java.math.BigInteger;
import java.util.stream.IntStream;

/**
 * Provides static convenience functions for using {@link java.math.BigInteger}.
 * 
 * @author Oliver
 */
public class BigInts {
  private static final Logger logger = getLogger(BigInts.class);
  
  public static BigInteger bigInt( long number ) {
    return new BigInteger( String.valueOf( number ) );
  }
  
  public static IntStream streamDigits( BigInteger number ) {
    return number.toString().chars().map( ch -> ch - 48 );
  }
  
  public static long getDigitSum( BigInteger num ) {
    return streamDigits( num ).sum();
  }
  
  public static BigInteger factorial( long num ) {
    return factorial( bigInt( num ) );
  }
  
  /**
   * Get the factorial of a number.
   * 
   * @param num the number to get the factorial of
   * @return the factorial of the given number
   */
  public static BigInteger factorial( BigInteger num ) {
    if ( num.compareTo( ZERO ) == 0 ) {
      return ONE;
    }
    BigInteger result = ONE;
    while ( num.compareTo( ONE ) > 0 ) {
      result = result.multiply( num );
      num = num.subtract( ONE );
    }
    return result;
  }
}
