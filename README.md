# Programming in Java – Assignment 3 (CO3)

**Student Name:** S. Susmitha  
**Register Number:** 192571068  
**Course:** Programming in Java  
**Assignment:** Assignment 3 – CO3  
**Programming Language:** Java  
**Repository:** `CSA09-192571068-Assignment3`

---

## 1. Project Overview

This repository contains the implementation of seven Java programming tasks developed as part of Assignment 3 (CO3).

The assignment focuses on exception handling, custom exceptions, resource management, multithreading, synchronization, deadlock prevention, inter-thread communication, and concurrent processing.

Each task demonstrates the application of Java concepts through practical programming scenarios.

## 2. Objectives

- Understand Java exception handling and exception propagation.
- Implement custom checked and unchecked exceptions.
- Apply exception chaining and resource management.
- Explore thread creation, execution, and lifecycle.
- Understand synchronization and race conditions.
- Demonstrate deadlock prevention through consistent lock ordering.
- Implement producer-consumer communication.
- Use ExecutorService, Callable, and concurrent processing techniques.
- Evaluate program execution and performance through experimental outputs.

## 3. Repository Structure

```text
CSA09-192571068-Assignment3/
│
├── README.md
├── .gitignore
│
├── Task01/
│   └── ATMWithdrawal.java
│
├── Task02/
│   └── StorageDemo.java
│
├── Task03/
│   └── ReportGenerator.java
│
├── Task04/
│   └── ScannerStations.java
│
├── Task05/
│   └── SeatBooking.java
│
├── Task06/
│   └── ConcurrencyProblems.java
│
├── Task07/
│   └── ResultProcessor.java
│
├── evidence/
│   ├── Q1/
│   ├── Q2/
│   ├── Q3/
│   ├── Q4/
│   ├── Q5/
│   ├── Q6/
│   └── Q7/
│
├── raw_outputs/
│   ├── Q1.txt
│   ├── Q2.txt
│   ├── Q3.txt
│   ├── Q4.txt
│   ├── Q5.txt
│   ├── Q6.txt
│   └── Q7.txt
│
└── Report_Charts/
    ├── Q5_double_bookings.png
    ├── Q5_sync_timing.png
    └── Q7_pool_performance.png
```

## 4. Task Descriptions and Implementations

### Task 1 – ATM Withdrawal Simulation

**File:** `Task01/ATMWithdrawal.java`

Implements an ATM withdrawal simulation to demonstrate Java exception handling.

**Concepts covered:**
- Exception hierarchy.
- Checked and unchecked exceptions.
- Custom exceptions.
- Try-catch-finally blocks.
- Input validation.
- Multiple failure scenarios.

**Test scenarios:**
- Successful withdrawal.
- Invalid numeric input.
- Negative withdrawal amount.
- Insufficient account balance.
- Account number mismatch.

**Outcome:** Demonstrates exception handling for different transaction failures while ensuring that the `finally` block executes for session cleanup.

### Task 2 – Online Examination Portal Exception Handling

**File:** `Task02/StorageDemo.java`

Simulates answer submission in an online examination portal using custom exceptions and exception chaining.

**Concepts covered:**
- User-defined exceptions.
- Checked and unchecked exceptions.
- Exception propagation.
- Exception chaining.
- `getCause()` and stack traces.
- Validation and error handling.

**Test scenarios:**
- Successful answer submission.
- Storage failure.
- Examination deadline validation.
- Student registration validation.
- File size validation.

**Outcome:** Demonstrates how lower-level storage errors can be wrapped in meaningful application-level exceptions while preserving the original cause.

### Task 3 – Resource Management and Exception Safety

**File:** `Task03/ReportGenerator.java`

Demonstrates file handling and resource management using manual resource closing and try-with-resources.

**Concepts covered:**
- File input and output.
- Resource management.
- Manual resource closing.
- Try-with-resources.
- Exception handling during file operations.
- Resource safety.

**Test scenarios:**
- Manual report generation.
- Try-with-resources report generation.
- Output comparison.
- Missing input file failure.

**Outcome:** Demonstrates the reliability and convenience of try-with-resources for automatically closing resources when exceptions occur.

### Task 4 – Thread Lifecycle and Scanner Stations

**File:** `Task04/ScannerStations.java`

Simulates multiple scanner stations operating concurrently.

**Concepts covered:**
- Thread class.
- Runnable interface.
- Thread lifecycle.
- `start()`, `run()`, `sleep()`, and `join()`.
- `isAlive()`.
- Thread priorities.
- Daemon threads.
- Concurrent execution.

**Experiments:**
- Ten execution-order trials.
- Thread creation using Runnable.
- Difference between `run()` and `start()`.
- Thread priority experiment.
- Daemon thread execution.

**Outcome:** Demonstrates concurrent execution and explains why thread completion order may vary between executions.

### Task 5 – Cinema Seat Booking and Synchronization

**File:** `Task05/SeatBooking.java`

Simulates a cinema ticket booking system to demonstrate race conditions and synchronization.

**Concepts covered:**
- Multithreading.
- Race conditions.
- Critical sections.
- Synchronized methods.
- Synchronized blocks.
- Thread safety.
- Performance comparison.

**Experiments:**
- Unsynchronized booking over ten trials.
- Synchronized method implementation.
- Synchronized block implementation.
- Double-booking comparison.
- Performance comparison over 10,000 booking attempts.

**Outcome:** Demonstrates how synchronization prevents concurrent threads from booking the same available seat.

### Task 6 – Deadlock Prevention and Inter-thread Communication

**File:** `Task06/ConcurrencyProblems.java`

Demonstrates safe resource sharing through ordered locking and producer-consumer communication.

**Concepts covered:**
- Deadlock prevention.
- Multiple object locks.
- Consistent lock ordering.
- Inter-thread communication.
- `wait()`.
- `notifyAll()`.
- Producer-consumer problem.
- Bounded buffer.

**Experiments:**
- Concurrent wallet transfers.
- Ordered-lock implementation.
- Producer-consumer communication.
- Buffer capacity verification.
- Token collection and total calculation.

**Outcome:** Demonstrates how consistent lock ordering helps prevent circular-wait deadlocks and how inter-thread communication coordinates producers and consumers.

### Task 7 – Concurrent Fault-Tolerant Result Processor

**File:** `Task07/ResultProcessor.java`

Implements a concurrent result-processing system that processes multiple student score files using Java thread pools.

**Concepts covered:**
- ExecutorService.
- Callable and Future.
- Fixed thread pools.
- Concurrent collections.
- Exception handling in concurrent tasks.
- Fault-tolerant processing.
- Performance evaluation.

**Experiments:**
- Processing 10,000 result files.
- Handling missing and corrupted files.
- Thread pool sizes of 1, 2, 4, and 8.
- Successful and failed task counting.
- Execution-time comparison.

**Outcome:** Demonstrates how thread pools improve processing performance while isolating individual file-processing failures.

## 5. Technologies and Concepts Used

| Category | Technologies / Concepts |
|---|---|
| Programming Language | Java |
| Development Environment | Visual Studio Code / GitHub Codespaces |
| Exception Handling | try, catch, finally, throw, throws |
| Custom Exceptions | User-defined exception classes |
| File Handling | Java I/O |
| Multithreading | Thread, Runnable |
| Synchronization | synchronized methods and blocks |
| Thread Communication | wait(), notifyAll() |
| Concurrency | ExecutorService, Callable, Future |
| Version Control | Git and GitHub |

## 6. How to Compile and Execute

### Prerequisites

- Java Development Kit (JDK) installed.
- Java compiler (`javac`) available.
- Terminal or command-line access.

Verify the Java installation:

```bash
java -version
javac -version
```

### Execute Individual Programs

Run the following commands from the repository root directory.

**Task 1 – ATM Withdrawal**

```bash
javac Task01/ATMWithdrawal.java
java -cp Task01 ATMWithdrawal
```

**Task 2 – Exception Chaining**

```bash
javac Task02/StorageDemo.java
java -cp Task02 StorageDemo
```

**Task 3 – Resource Management**

```bash
cd Task03
javac ReportGenerator.java
java ReportGenerator
cd ..
```

**Task 4 – Thread Lifecycle**

```bash
cd Task04
javac ScannerStations.java
java ScannerStations
cd ..
```

**Task 5 – Cinema Seat Booking**

```bash
cd Task05
javac SeatBooking.java
java SeatBooking
cd ..
```

**Task 6 – Deadlock Prevention and Communication**

```bash
javac Task06/ConcurrencyProblems.java
java -cp Task06 ConcurrencyProblems
```

**Task 7 – Concurrent Result Processor**

```bash
cd Task07
javac ResultProcessor.java
java ResultProcessor
cd ..
```

## 7. Experimental Results

### Task 1
The ATM simulation successfully demonstrates valid and invalid transaction scenarios, including insufficient balance and account mismatch. The session cleanup message is displayed after each transaction.

### Task 2
The exception-chaining experiment demonstrates how an underlying storage exception is preserved as the cause of a higher-level submission exception.

### Task 3
The manual-close and try-with-resources implementations generate identical reports for valid inputs. A missing input file is also tested.

### Task 4
Ten execution trials demonstrate variations in thread completion order. Additional experiments cover Runnable, thread priorities, and daemon threads.

### Task 5
The unsynchronized implementation produces duplicate bookings under concurrent execution. The synchronized method and synchronized block implementations prevent double-booking in the captured tests.

### Task 6
The producer-consumer implementation processes all tokens successfully. The maximum observed buffer size is 5, matching the configured buffer capacity. Wallet transfers preserve the total wallet balance.

### Task 7
The result processor handles 10,000 files, with 9,800 successful results and 200 failures in the captured run.

| Thread Pool Size | Files Processed | Successful | Failed | Execution Time |
|---:|---:|---:|---:|---:|
| 1 | 10,000 | 9,800 | 200 | 6528.966 ms |
| 2 | 10,000 | 9,800 | 200 | 2097.258 ms |
| 4 | 10,000 | 9,800 | 200 | 1059.885 ms |
| 8 | 10,000 | 9,800 | 200 | 815.426 ms |

*Note: These are captured execution measurements from one run, not statistical averages. Execution time may vary depending on the system and runtime environment.*

## 8. Evidence and Output Files

The repository maintains separate folders for execution evidence and output records.

- `evidence/` contains command-line screenshots for each task.
- `raw_outputs/` contains the corresponding recorded console outputs.
- `Report_Charts/` contains graphical representations of selected experimental results.

These files support the implementation and experimental analysis of the seven tasks.

## 9. Conclusion

This assignment demonstrates the practical application of Java exception handling, file resource management, multithreading, synchronization, deadlock prevention, inter-thread communication, and concurrent processing.

The seven implementations provide practical exposure to handling runtime failures, managing shared resources, coordinating multiple threads, and improving application performance through concurrency.

---
