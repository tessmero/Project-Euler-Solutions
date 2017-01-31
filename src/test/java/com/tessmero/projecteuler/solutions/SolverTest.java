package com.tessmero.projecteuler.solutions;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.slf4j.LoggerFactory.getLogger;

import com.tessmero.projecteuler.solvers.Solver;
import org.junit.Test;
import org.slf4j.Logger;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Test all implementations of Solver.
 * 
 * @author Oliver Tessmer
 */
public class SolverTest {
  private static final Logger logger = getLogger(SolverTest.class);
  
  /**
   * For all Solver implementations: create an instance, call {@link Solver#doTest}, 
   * and assert that the return value matches {@link Solver#getExpectedTestResult}.
   */
  @Test
  public void testAllSolvers() throws Exception {
    System.out.println("doTest");
    for ( Solver instance : getAllSolverImplementations() ) {
      
      logger.info( "testing " + instance.getClass().getSimpleName() );
      
      long expResult = instance.getExpectedTestResult();
      long result = instance.doTest();
      assertEquals( "unexpected test result from " + instance.getClass().getSimpleName(), 
              expResult, result);
    }
  }
  
  private List<Solver> getAllSolverImplementations() throws Exception {
    List<Solver> result = new ArrayList();
    
    File srcDir = new File( "../src/main/java/com/tessmero/projecteuler/solvers" );
    if ( !srcDir.isDirectory() ) {
      throw new Error( format( "could not find solvers source directory at path \"{0}\"", 
              srcDir.getAbsolutePath() ) );
    }
    
    for ( File srcFile : srcDir.listFiles() ) {
      String srcFilename = srcFile.getName();
      
      if ( srcFilename.equals( "Solver.java" ) ) {
        continue;
      }
      if (!( srcFilename.startsWith( "Solver") && srcFilename.endsWith( ".java" ) )) {
        fail( format( "unexpected filename in solutions source directory"
                + "\n\tdirectory: {0}\n\tfilename: {1}", srcDir, srcFilename ) );
      }
      
      String classname = srcFilename.replace( ".java", "" );
      Class clazz = Class.forName("com.tessmero.projecteuler.solvers." + classname);
      
      if ( !Solver.class.isAssignableFrom( clazz ) ) {
        fail( format( "solver implementation \"{0}\" does not extend Solver", srcFilename ) );
      }
      
      Constructor ctor = clazz.getConstructor();
      Object instance = ctor.newInstance();
      result.add( (Solver)instance );
    }
    
    return result;
  }
}
