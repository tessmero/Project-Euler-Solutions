package com.tessmero.projecteuler.util;

import static java.lang.Math.sqrt;
import static java.text.MessageFormat.format;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongSupplier;
import java.util.stream.LongStream;
import org.slf4j.Logger;

/**
 * Provides static convenience functions to test for primality and retrieve prime numbers.
 *
 * @author Oliver
 */
public class Primes {
  private static final Logger logger = getLogger(Primes.class);

  public static LongStream getPrimesStream() {
    return LongStream.generate(new PrimeSupplier());
  }

  public static class PrimeSupplier implements LongSupplier {

    private final long[] primes = new long[1000000];
    private int index = 0;

    public PrimeSupplier() {
      primes[0] = 2L;
      primes[1] = 3L;
    }

    @Override
    public long getAsLong() {

      if (primes[index] != 0) {
        return primes[index++];
      }

      long test = primes[index - 1] + 2;
      for (; ; test += 2) {
        //logger.debug( format( "testing {0} for primality...", test ) );
        for (long lowerPrime : primes) {
          if (lowerPrime == 0) {
            primes[index++] = test;
            return test;
          } else if (test % lowerPrime == 0) {
            break;
          }
        }
      }
    }
  }

  /**
   * Get a list of prime factors for the given number.
   *
   * @param num the number to factorize
   * @return a list of prime factors. may contain duplicates
   */
  public static List<Long> getPrimeFactors(long num) {
    PrimeSupplier ps = new PrimeSupplier();
    long prime;
    List<Long> result = new ArrayList();
    long maxFactor = num / 2;
    while (num > 1 && (prime = ps.getAsLong()) <= maxFactor) {
      while (num % prime == 0) {
        result.add(prime);
        num = num / prime;
      }
    }
    return result;
  }

  /**
   * Compute a list of the primes below a certain value.
   *
   * @param ceiling the exclusive upper limit for primes to retrieve
   * @return a list of all primes less than ceiling
   */
  public static List<Long> getPrimes(long ceiling) {
    List<Long> result = new ArrayList();

    result.add(2L);
    for (long test = 3; test < ceiling; test += 2) {
      logger.debug(format("testing {0} for primality...", test));
      boolean isPrime = true;
      for (long lowerPrime : result) {
        if (test % lowerPrime == 0) {
          isPrime = false;
          break;
        }
      }
      if (isPrime) {
        result.add(test);
      }
    }

    return result;
  }

  /**
   * Compute a list of the first n primes.
   *
   * @param count the desired number of primes
   * @return a list of primes with length = count
   */
  public static List<Long> getNPrimes(int count) {
    List<Long> result = new ArrayList();

    if (count == 0) {
      return result;
    }

    result.add(2L);
    for (long test = 3; result.size() < count; test += 2) {
      boolean isPrime = true;
      for (long lowerPrime : result) {
        if (test % lowerPrime == 0) {
          isPrime = false;
          break;
        }
      }
      if (isPrime) {
        result.add(test);
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
  public static boolean isPrime(long num) {
    long maxFactor = (long) sqrt(num);
    for (long i = 2; i < maxFactor; i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }
}
