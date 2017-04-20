/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solutions;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;
import static util.TestUtil.getAllSolverImplementations;

import com.tessmero.projecteuler.solvers.Solver;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.test.annotation.IfProfileValue;
import util.BaseTest;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Check that each solver returns the correct solution.
 *
 * <p>Solutions are tested by checking against the source of an unofficial API
 * https://github.com/MainShayne233/project_euler_api/blob/master/app/data/solutions.txt
 *
 * @author Oliver
 */
@IfProfileValue(name = "test-profile", value = "FullSolutionTest")
public class FullSolverTest extends BaseTest{
  private static final Logger logger = getLogger(FullSolverTest.class);

  private static final String correctSolutionsUrl 
      = "https://raw.githubusercontent.com/"
          + "MainShayne233/project_euler_api/master/app/data/solutions.txt";
  
  /**
   * For all Solver implementations: create an instance, call {@link Solver#doSolution()}, and
   * assert that the return value is correct by checking against the source of an unofficial API
   * https://github.com/MainShayne233/project_euler_api/blob/master/app/data/solutions.txt
   * @throws java.lang.Exception if an I/O exception occurs
   */
  @Test
  public void testAllSolvers() throws Exception {
    Map<Integer,String> correctSolutions = getCorrectSolutions();
    Map<Integer, Solver> allSolvers = getAllSolverImplementations();
    for ( int problemNumber : allSolvers.keySet()) {
      Solver solver = allSolvers.get( problemNumber );
      
      //log pre-test message
      String solverShortName = solver.getClass().getSimpleName();
      logger.info( format( "solution-testing {0}...", solverShortName ) );
      
      String expectedSolution = correctSolutions.get( problemNumber );
      String solution = solver.doSolutionStr();
      
      String problemUrl = "https://projecteuler.net/problem=" + problemNumber;
      assertEquals( format( "Solver returned incorrect solution for problem #{0} ({1})", 
              problemNumber, problemUrl ), expectedSolution, solution );
      
      //log post-test mesasge
      logger.info( format( 
              "got correct solution ({0}) from {1}: {2}",
              solution, solverShortName, problemUrl ) );
    }
  }

  private Map<Integer,String> getCorrectSolutions() throws Exception {
    Map<Integer,String> result = new HashMap();
    
    URL url = new URL(correctSolutionsUrl);
    try ( BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())) ) {
      String line;
      while ( (line = in.readLine()) != null ) {
        try {
          
          String[] parts = line.split( " " );
          if ( parts.length == 1 ) {
            continue;
          }
          
          int problemNumber = Integer.parseInt( parts[0] );
          String correctSolution = parts[1];
          result.put( problemNumber, correctSolution );
          
        } catch ( Exception e ) {
          throw new Exception( format( 
              "Exception parsing problem number and correct solution from line \"{0}\"", 
              line ), e );
        }
      }
    }
    
    return result;
  }
}
