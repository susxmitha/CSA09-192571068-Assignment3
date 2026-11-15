# CSA09-192571068-Assignment3 — Programming in Java (CO3)

**Student:** S. Susmitha — Register Number: 192571068  
**Submission format:** Single-student report and repository.  
**Course:** Programming in Java — Assignment 3 (CO3)  
**Language:** Java  
**Repository name required by the assignment:** `CSA09-192571068-Assignment3`

## Overview
This repository is prepared for the individual submission of S Susmitha. It contains seven Java task implementations covering exception handling, resource management, multithreading, synchronization, deadlock prevention, inter-thread communication, and fault-tolerant concurrent processing.

## Folder structure
- `Task01/ATMWithdrawal.java`
- `Task02/StorageDemo.java`
- `Task03/ReportGenerator.java`
- `Task04/ScannerStations.java`
- `Task05/SeatBooking.java`
- `Task06/ConcurrencyProblems.java`
- `Task07/ResultProcessor.java`
- `evidence/` — submitted CMD screenshots
- `raw_outputs/` — transcribed console outputs
- `Report_Charts/` — figures used in the report
- `CO3_Assignment3_Report.docx` — editable report

## Compile and run (Windows CMD)
Run each command from the repository root:

```cmd
javac Task01\ATMWithdrawal.java
java -cp Task01 ATMWithdrawal

javac Task02\StorageDemo.java
java -cp Task02 StorageDemo

cd Task03
javac ReportGenerator.java
java ReportGenerator
cd ..

cd Task04
javac ScannerStations.java
java ScannerStations
cd ..

cd Task05
javac SeatBooking.java
java SeatBooking
cd ..

javac Task06\ConcurrencyProblems.java
java -cp Task06 ConcurrencyProblems

cd Task07
javac ResultProcessor.java
java ResultProcessor
cd ..
```

## Task result summary
1. **Q1:** Demonstrates successful withdrawal, numeric parsing failure, negative amount, insufficient balance, account mismatch, and `finally` execution.
2. **Q2:** Wraps an `IOException` in `SubmissionStorageException`, preserves the cause, and demonstrates validation failures.
3. **Q3:** Produces identical manual-close and try-with-resources reports for valid inputs; a missing input is injected for the safe implementation.
4. **Q4:** Records ten station completion-order trials and demonstrates `Thread`, `Runnable`, `run()`/`start()`, priorities, `isAlive()`, `join()`, and daemon behaviour.
5. **Q5:** Forces a reproducible unsynchronized check-then-book race over ten trials, verifies synchronized method/block correctness, and times 10,000 booking attempts.
6. **Q6:** Uses `wait()`/`notifyAll()` with a capacity-five producer-consumer buffer and uses consistent wallet lock ordering.
7. **Q7:** Processes 10,000 score files with fixed pools of 1, 2, 4, and 8 threads, isolating missing/corrupt-file failures.

## Evidence and interpretation
The report contains the submitted CMD screenshots and transcribed outputs. Timing values are single captured runs unless otherwise stated; they are not presented as statistical averages. Some specification sub-requirements remain identified in the report's evidence audit (notably Q1 uncaught stack-trace/broad-catch demonstration, Q3 manual resource-leak count/suppressed-exception demonstration, Q6 intentionally deadlocking version and `if` versus `while` experiment, and Q7 hand-checked small dataset).

## GitHub submission
The assignment requires a working GitHub repository link on the report cover page and at least one progressive commit per task. This package does not claim that a remote repository has been published or shared. After creating the repository, push this folder, commit each task progressively, and add the actual repository URL to the cover page.
