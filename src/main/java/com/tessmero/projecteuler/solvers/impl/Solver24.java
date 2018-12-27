package com.tessmero.projecteuler.solvers.impl;

import com.tessmero.projecteuler.solvers.Solver;
import java.util.ArrayList;
import java.util.List;

/**
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 *
 * <p>A permutation is an ordered arrangement of objects. For example, 3124 is one possible
 * permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or
 * alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
 *
 * <p>012 021 102 120 201 210
 *
 * @author Oliver
 */
public class Solver24 implements Solver {

  @Override
  public String doSolutionStr() throws Exception {
    return getLexPerm(999999, "0123456789");
  }

  @Override
  public String doTestStr() throws Exception {
    return getLexPerm(1, "012");
  }

  @Override
  public String getExpectedTestResultStr() {
    return "021";
  }

  /**
   * Get one permutation of the given digits.
   *
   * @param index the index of the permutation out of the lexicographically-ordered list of all
   *     possible permutations. If index is 0, the first permutation will be returned.
   * @param digits a string containing the characters (digits) to be permuted
   * @return the permutation
   */
  private String getLexPerm(int index, String digits) {
    return getLexigraphicalPermutations(digits).get(index);
  }

  /**
   * Get all possible permutations of the given digits
   *
   * @param digits a string containing the characters (digits) to be permuted
   * @return all permutations in lexicographic order.
   */
  private List<String> getLexigraphicalPermutations(String digits) {
    List<String> result = new ArrayList();
    permute(digits.toCharArray(), 0, digits.length() - 1, result);
    result.sort(String::compareTo);
    return result;
  }

  /** swap two characters in an array */
  void swap(char[] arr, int a, int b) {
    char temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  /** Generate permutations of a set of characters */
  void permute(char[] arr, int l, int r, List<String> output) {
    if (l == r) {
      output.add(new String(arr));
    } else {
      for (int i = l; i <= r; i++) {
        swap(arr, l, i);
        permute(arr, l + 1, r, output);
        swap(arr, l, i); // backtrack
      }
    }
  }
}
