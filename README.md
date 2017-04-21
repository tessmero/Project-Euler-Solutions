# Project-Euler-Solutions-Java
Solutions to problems on projecteuler.net

[![Build Status](https://travis-ci.org/tessmero/Project-Euler-Solutions-Java.svg?branch=master)](https://travis-ci.org/tessmero/Project-Euler-Solutions-Java)


Clone repository, install dependencies, build and test. 

```bash
$ git clone [.git url]
$ cd Project-Euler-Solutions-Java
$ gradle build
```

The command above will test each problem-solver quickly. The quick-test for each solver generally matches part of the problem's description on projecteuler.net.

The solvers can be fully tested by generating the solution to each problem and checking against an online resource. This requires a few minutes and an internet connection.

```bash
$  gradle cleanTest test -PFullSolutionTests
```

### Related Projects
* [Solutions in C++] (https://github.com/tessmero/Project-Euler-Solutions-Cpp)
