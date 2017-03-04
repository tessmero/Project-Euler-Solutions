/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.util;

import java.math.BigInteger;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

/**
 * Provides static convenience functions for using {@link java.math.BigInteger}.
 * 
 * @author Oliver
 */
public class BigInts {
  
  public static BigInteger bigInt( long number ) {
    return new BigInteger( String.valueOf( number ) );
  }
  
  public static IntStream streamDigits( BigInteger number ) {
    return number.toString().chars().map( ch -> ch - 48 );
  }
}
