package com.tessmero.projecteuler.util;

import java.math.BigInteger;

/**
 * Minimal Fibonacci sequence generator.
 *
 * @author Oliver
 */
public class BigIntFibonacci {
  private int index = 2;
  private BigInteger prevValue = BigInteger.ONE;
  private BigInteger currValue = BigInteger.ONE;

  /**
   * Get the value within the the Fibonacci sequence.
   *
   * <p>Return value is increased each time {@link #increment()} is called. The first 4 values will
   * be 1,2,3,5,8.
   *
   * @return the current Fibonacci number.
   */
  public BigInteger getValue() {
    return currValue;
  }

  /**
   * Get the current index/position within the Fibonacci sequence.
   *
   * <p>starts at 0 and increases by one each time {@link #increment()} is called.
   *
   * @return the current index
   */
  public int getIndex() {
    return index;
  }

  /**
   * Advance the member variables along the Fibonacci sequence.
   *
   * <p>increases the current/previous value, and increments the index.
   */
  public void increment() {
    index++;
    BigInteger temp = currValue;
    currValue = currValue.add(prevValue);
    prevValue = temp;
  }
}
