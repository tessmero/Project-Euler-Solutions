package com.tessmero.projecteuler.solutions;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;
import static util.TestUtil.getAllSolverImplementations;

import com.tessmero.projecteuler.solvers.Solver;
import org.junit.Test;
import org.slf4j.Logger;
import util.BaseTest;

import java.util.Map;

/**
 * Quickly Test all implementations of Solver.
 * 
 * <p>Online problem descriptions tend to provide the solution to a shorter version of the full 
 * problem, which is used for quickly testing. (See 
 * {@link com.tessmero.projecteuler.solvers.Solver#getExpectedTestResult()})
 * 
 * @author Oliver Tessmer
 */
public class FastSolverTest extends BaseTest{
  private static final Logger logger = getLogger(FastSolverTest.class);
  
  /**
   * For all Solver implementations: create an instance, call {@link Solver#doTest}, 
   * and assert that the return value matches {@link Solver#getExpectedTestResult}.
   */
  @Test
  public void testAllSolvers() throws Exception {
    System.out.println("doTest");
    Map<Integer, Solver> allSolvers = getAllSolverImplementations();
    for ( int problemNumber : allSolvers.keySet()) {
      Solver solver = allSolvers.get( problemNumber );
      
      //log pre-test message
      String solverShortName = solver.getClass().getSimpleName();
      logger.info( format( "quick-testing {0}...", solverShortName ) );
      
      //do the test
      String expResult = solver.getExpectedTestResultStr();
      String result = solver.doTestStr();
      String problemUrl = "https://projecteuler.net/problem=" + problemNumber;
      assertEquals( format( "unexpected quick-test result from {0} ({1})", 
              solverShortName, problemUrl ), expResult, result );
      
      //log post-test mesasge
      logger.info( format( 
              "got expected quick-test result ({0}) from {1}: {2}",
              result, solverShortName, problemUrl ) );
    }
  }
}
