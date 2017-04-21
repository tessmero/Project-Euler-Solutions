/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static java.util.Arrays.stream;
import static org.slf4j.LoggerFactory.getLogger;

import com.tessmero.projecteuler.solvers.LongSolver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

/**
 * Find the maximum total from top to bottom of the triangle.
 *
 * <p>This solver references logic in {@link com.tessmero.projecteuler.solvers.impl.Solver18}.
 *
 * @author Oliver
 */
public class Solver67 extends LongSolver {
  private final Logger logger = getLogger(getClass());

  private final int[][] smallTriangle = {{3}, {7, 4}, {2, 4, 6}, {8, 5, 9, 3}};

  @Override
  public long doSolution() throws Exception {
    int[][] bigTriangle = parseTriangle(getClass().getResource("/p067_triangle.txt"));
    return Solver18.getMaximumPathSum(bigTriangle);
  }

  @Override
  public long doTest() throws Exception {
    return Solver18.getMaximumPathSum(smallTriangle);
  }

  @Override
  public long getExpectedTestResult() {
    return 23;
  }

  private int[][] parseTriangle(URL url) throws Exception {
    List<int[]> resultList = new ArrayList();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultList.add(stream(line.split(" ")).mapToInt(Integer::parseInt).toArray());
      }
    }

    int numRows = resultList.size();
    int[][] result = new int[numRows][];
    for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
      result[rowIndex] = resultList.get(rowIndex);
    }
    return result;
  }
}
