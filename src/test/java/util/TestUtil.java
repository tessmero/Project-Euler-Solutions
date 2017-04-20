/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.toMap;
import static org.junit.Assert.fail;
import static org.slf4j.LoggerFactory.getLogger;

import com.tessmero.projecteuler.solvers.Solver;

import org.slf4j.Logger;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides a static convenience method for listing all implementations of
 * {@link com.tessmero.projecteuler.solvers.Solver} present in the source.
 *
 * @author Oliver
 */
public class TestUtil {
  private static final Logger logger = getLogger(TestUtil.class);

  private static final String[] solverImplPackagePath 
          = { "com", "tessmero", "projecteuler", "solvers", "impl" };
  private static final String solverSourceDirPath 
          = "src/main/java/" + String.join( "/", solverImplPackagePath );
  
  /**
   * Get a list of instances for each implementation of
   * {@link com.tessmero.projecteuler.solvers.Solver} present in the source.
   *
   * <p>Also asserts that that the source files under {@link com.tessmero.projecteuler.solvers} 
   * follow the expected conventions for solvers.
   *
   * @return a map where keys are problem numbers and values are
   * {@link com.tessmero.projecteuler.solvers.Solver} instances
   * @throws Exception if source files are malformed or an I/O exception occurs
   */
  public static Map<Integer, Solver> getAllSolverImplementations() throws Exception {
    File srcDir = getSolverSourceDir();

    //iterate through src files in the solver directory
    Map result = Arrays.stream(srcDir.listFiles())
        .map( srcFile -> srcFile.getName() )
            
        //check filename conventions 
        .peek(filename -> assertSolverSourceFilenameConventions( filename ) )
            
        //and get a problem number and solver instance for each file
        .collect( toMap( 
            name -> parseProblemNumber( name ), 
            name -> getSolverInstance( name ),
            (v1,v2) -> { 
              throw new RuntimeException(format(
                    "Duplicate key for solvers {0} and {1}", v1, v2)); 
            }, 
            TreeMap::new ) 
        );
    
    return result;
  }
  
  private static File getSolverSourceDir() {
    File srcDir = new File(solverSourceDirPath);
    if (!srcDir.isDirectory()) {
      throw new Error(format("could not find solvers source directory at path \"{0}\"",
              srcDir.getAbsolutePath()));
    }
    return srcDir;
  }
  
  private static void assertSolverSourceFilenameConventions(String srcFilename) {
    if (!(srcFilename.startsWith("Solver") && srcFilename.endsWith(".java"))) {
      fail(format("unexpected filename in solutions source directory"
              + "\n\tdirectory: {0}\n\tfilename: {1}", solverSourceDirPath, srcFilename));
    }
  }

  private static int parseProblemNumber(String srcFilename) {

    String strProblemNumber = srcFilename.replace("Solver", "").replace(".java", "");
    int problemNumber = -1;
    try {
      problemNumber = Integer.parseInt(strProblemNumber);
    } catch (Exception e) {
      fail(format(
              "Could not parse integer from problem number \"{0}\" from source filename \"{1}\"",
              strProblemNumber, srcFilename));
    }
    
    return problemNumber;
  }
  
  private static Solver getSolverInstance(String srcFilename) {
    try {
      String shortName = srcFilename.replace(".java", "");
      String className = String.join( ".", solverImplPackagePath) + "." + shortName;
      Class clazz = Class.forName( className );

      if (!Solver.class.isAssignableFrom(clazz)) {
        fail(format("solver implementation \"{0}\" does not extend Solver", srcFilename));
      }
      Constructor ctor = clazz.getConstructor();
      Object instance = ctor.newInstance();

      return (Solver) instance;
      
    } catch ( Exception e ) {
      fail( format( 
          "Malformed solver source file \"{0}\". Exception thrown in creating an instance:\n{1}", 
          srcFilename, e ) );
      return null;
    }
  }
}
