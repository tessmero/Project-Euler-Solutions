/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import static java.text.MessageFormat.format;
import static org.junit.Assert.fail;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import util.BaseTest.AppConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
@Ignore
/**
 * Base for all test classes, which handles all the necessary one-time, 
 * before-test-suite setup.
 * 
 * <p>http://stackoverflow.com/a/25577873
 * 
 * <p>https://examples.javacodegeeks.com/core-java/junit/spring-junit-test-example/
 * 
 * @author Oliver
 */
public class BaseTest {
  
  private static final boolean alreadySetup = false;
  
  /**
   * Perform before-test-suite setup.
   * 
   * @throws Exception if an I/O exception occurs
   */
  @BeforeClass
  public static void setUpBaseClass() throws Exception {
    if (!alreadySetup) {
      
      //configure logging
      BasicConfigurator.configure();    
      
      assertAllTestClassesExtendBase();
    }
  }
  
  private static void assertAllTestClassesExtendBase() throws Exception {
    List<String> testClassnames 
            = getDescendantClassNames( "com.tessmero.projecteuler.solutions", 
                    new File( "src/test/java/com/tessmero/projecteuler/solutions" ) );
    for ( String className : testClassnames  ) {
      
      Class clazz = Class.forName( className );

      if (!BaseTest.class.isAssignableFrom(clazz)) {
        fail(format("test class {0} does not extend {1}", clazz, BaseTest.class ) );
      }
    }
  }
  
  protected static List<String> getDescendantClassNames( String packageName, File dir ) {
    List<String> result = new ArrayList();
    
    for ( File file : dir.listFiles() ) {
      if ( file.isFile() ) {
        result.add( packageName + "." + file.getName().replace(".java", "") );
      } else {
        result.addAll( getDescendantClassNames( packageName + "." + file.getName(), file ) );
      }
    }
    
    return result;
  }
  
  @Configuration
  public static class AppConfig {
  }

}
