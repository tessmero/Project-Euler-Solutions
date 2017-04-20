/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static org.slf4j.LoggerFactory.getLogger;

import com.tessmero.projecteuler.solvers.LongSolver;

import org.slf4j.Logger;

/**
 * Find the maximum total from top to bottom of the triangle.
 * 
 * @author Oliver
 */
public class Solver18 extends LongSolver{
  private final Logger logger = getLogger(getClass());

  private final int[][] bigTriangle = {
    {75},
    {95, 64},
    {17, 47, 82},
    {18, 35, 87, 10},
    {20,  4, 82, 47, 65},
    {19,  1, 23, 75,  3, 34},
    {88,  2, 77, 73,  7, 63, 67},
    {99, 65,  4, 28,  6, 16, 70, 92},
    {41, 41, 26, 56, 83, 40, 80, 70, 33},
    {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
    {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
    {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
    {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
    {63, 66,  4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
    {04, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60,  4, 23}
  };
  
  private final int[][] smallTriangle = {
    {3},
    {7, 4},
    {2, 4, 6},
    {8, 5, 9, 3}
  };
  
  @Override
  public long doSolution() throws Exception {
    return getMaximumPathSum( bigTriangle );
  }

  @Override
  public long doTest() throws Exception {
    return getMaximumPathSum( smallTriangle );
  }

  @Override
  public long getExpectedTestResult() {
    return 23;
  }
  
  /**
   * Get the maximum-path sum by traversing downwards from the top to the bottom of the number 
   * triangle.
   * 
   * @param triangle the triangle of numbers, where the first row has one number, and each 
   *        subsequent row has one more element than the previous row.
   * @return The maximum sum reached by traversing the triangle from top to bottom
   */
  public static int getMaximumPathSum( int[][] triangle ) {
    int numRows = triangle.length;
    
    //starting in the bottom row, compute maximum path sum for every starting point on the triangle
    int[] maxPathsLastRow = triangle[numRows - 1];
    for ( int rowIndex = numRows - 2 ; rowIndex >= 0 ; rowIndex-- ) {
      int[] triangleRow = triangle[rowIndex];
      int numCols = triangleRow.length;
      int[] maxPathsRow = new int[numCols];
      for ( int colIndex = 0 ; colIndex < numCols ; colIndex++ ) {
        maxPathsRow[colIndex] = triangleRow[colIndex] 
              + Math.max( maxPathsLastRow[colIndex], maxPathsLastRow[colIndex + 1] );
      }
      maxPathsLastRow = maxPathsRow;
    }
    
    return maxPathsLastRow[0];
  }
}
