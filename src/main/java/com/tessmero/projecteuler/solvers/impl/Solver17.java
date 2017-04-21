/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static java.text.MessageFormat.format;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

import com.tessmero.projecteuler.solvers.LongSolver;
import java.util.List;
import java.util.stream.IntStream;
import org.slf4j.Logger;

/**
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many
 * letters would be used.
 *
 * @author Oliver
 */
public class Solver17 extends LongSolver {
  private final Logger logger = getLogger(getClass());

  @Override
  public long doSolution() throws Exception {
    return IntStream.range(1, 1001)
        .mapToObj(number -> getWordForm(number))
        .mapToLong(word -> countLetters(word))
        .sum();
  }

  @Override
  public long doTest() throws Exception {
    return countLetters(getWordForm(342));
  }

  @Override
  public long getExpectedTestResult() {
    return 23;
  }

  String[] smallNumberWords = {
    "",
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine",
    "ten",
    "eleven",
    "twelve",
    "thirteen",
    "",
    "fifteen",
    "",
    "",
    "eighteen"
  };

  String[] tensWords = {
    "",
    "{0}teen",
    "twenty-{0}",
    "thirty-{0}",
    "forty-{0}",
    "fifty-{0}",
    "sixty-{0}",
    "seventy-{0}",
    "eighty-{0}",
    "ninety-{0}"
  };

  private String getWordForm(int number) {
    if (number < smallNumberWords.length && !smallNumberWords[number].isEmpty()) {
      return smallNumberWords[number];

    } else if (number < 100) {
      if (number % 10 == 0) {
        return format(tensWords[number / 10], "").replace("-", "");
      } else {
        return format(tensWords[number / 10], smallNumberWords[number % 10]);
      }

    } else if (number < 1000) {
      if (number % 100 == 0) {
        return smallNumberWords[number / 100] + " hundred";
      } else {
        return smallNumberWords[number / 100] + " hundred and " + getWordForm(number % 100);
      }

    } else if (number == 1000) {
      return "one thousand";
    } else {
      throw new Error("unsupported value: " + number);
    }
  }

  List<Integer> ignorableChars =
      unmodifiableList(" -".chars().mapToObj(ch -> ch).collect(toList()));

  private long countLetters(String word) {
    return word.chars().filter(ch -> !ignorableChars.contains(ch)).count();
  }
}
