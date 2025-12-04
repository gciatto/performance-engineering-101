# Debugging and Profiling Exercise

## Prerequisites

- IntelliJ Idea __Ultimate Edition__ (profiling tools are not available in Community Edition)
    - either go with trial (30 days) or use your educational license (free for UniBo students and staff)
- Java 17 or higher

## TODOs

0. Understand the project structure and the code, and in particular the `TopKWordCounter` interface and its tests.

1. Try to run the tests, and fix bugs so that all tests pass for the `SlowTopKWordCounter` class pass.
    ```shell
    export IMPLEMENTATIONS_UNDER_TEST=slow
    ./gradlew test
    ```

  - try to use the debugger and in particular __conditional breakpoints__

2. Listen to the teacher explaining how profiling works in IntelliJ Idea Ultimate Edition

3. Use the profiler to analyze the performance of the `SlowTopKWordCounter` implementation on a large text file (e.g., `inferno.txt` provided in the project)
  + learn how to set up IntelliJ's "Run configuration" to set the `IMPLEMENTATIONS_UNDER_TEST` environment variable to `slow` so that the profiler focuses on the `SlowTopKWordCounter` implementation

4. Identify the bottlenecks in the `SlowTopKWordCounter` implementation and suggest possible optimizations, then implement them in the `OptimizedTopKWordCounter` class

5. Verify that the optimizations improve performance by re-running the profiler on the optimized implementation
