/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static org.slf4j.LoggerFactory.getLogger;

import com.tessmero.projecteuler.solvers.LongSolver;

import org.slf4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * How many routes are there through a 20×20 grid.
 * 
 * @author Oliver
 */
public class Solver15 extends LongSolver{
  private final Logger logger = getLogger(getClass());

  @Override
  public long doSolution() throws Exception {
    return countRoutes( 20, 20 );
  }

  @Override
  public long doTest() throws Exception {
    return countRoutes( 2, 2 );
  }

  @Override
  public long getExpectedTestResult() {
    return 6;
  }
  
  private long countRoutes( int... gridDims ) {
    if ( gridDims[0] == 0 && gridDims[1] == 0 ) {
      return 0;
    } else if ( gridDims[0] == 0 || gridDims[1] == 0 ) {
      return 1;
    }
    
    Arrays.sort( gridDims );
    long result;
    
    if ( !countsByGridDims.containsKey( gridDims[0] ) ) {
      countsByGridDims.put( gridDims[0], new HashMap() );
    }
    if ( countsByGridDims.get( gridDims[0] ).containsKey( gridDims[1] ) ) {
      result = countsByGridDims.get( gridDims[0] ).get( gridDims[1] );
    } else { 
      result = countRoutes( gridDims[0] - 1, gridDims[1] ) 
              + countRoutes( gridDims[0], gridDims[1] - 1 ) ;
    }
    
    countsByGridDims.get( gridDims[0] ).put( gridDims[1], result );
    return result;
  }
  
  private final Map<Integer,Map<Integer,Long>> countsByGridDims = new HashMap();
  
}
