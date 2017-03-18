/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tessmero.projecteuler.solvers.impl;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import com.tessmero.projecteuler.solvers.LongSolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * What is the total of all the name scores in the file.
 * 
 * <p>If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of 
 * a and b are called amicable numbers.
 * 
 * @author Oliver
   */
public class Solver22 extends LongSolver{
  
  List<String> allNames;
  
  public Solver22() throws IOException {
    allNames = parseNames( getClass().getResource("/p022_names.txt") );
    allNames.sort( String::compareTo );
  }
  
  @Override
  public long doSolution() throws Exception {
    return allNames.stream()
            .mapToLong( name -> getNameScore( name ) )
            .sum();
  }

  @Override
  public long doTest() throws Exception {
    return getNameScore("COLIN");
  }

  @Override
  public long getExpectedTestResult() {
    return 49714;
  }
  
  private long getNameScore( String name ) {
    return (allNames.indexOf( name ) + 1) 
            * name.chars().map( letter -> letter - 64 ).sum();
  }
  
  private List<String> parseNames( URL source ) throws IOException {
    String content;
    try ( BufferedReader br = new BufferedReader( new InputStreamReader( source.openStream() ) ) ) {
      content = br.readLine();
    }
    return stream( content.split( "," ) )
            .map( quotedName -> quotedName.substring( 1, quotedName.length() - 1 ) )
            .collect( toList() );
  }
}
