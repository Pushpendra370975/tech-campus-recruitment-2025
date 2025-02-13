1. Approaches Considered
Approach 1: Full File Scan Using Streaming

Description: Read the log file line by line, check if the line starts with the specified date, and write matching lines to an output file.
Pros:
Memory Efficient: Processes one line at a time without loading the whole file.
Simple & Easy to Implement: Minimal code complexity.
Cons:
I/O Bound: The entire file is scanned which might be slower if the file is extremely large.
Approach 2: Binary Search on the Log File

Description: If logs are sorted by date, perform a binary search to locate the start of the target date and then sequentially read until the date changes.
Pros:
Potentially Faster: Could skip large portions of the file.
Cons:
Complexity: Handling variable-length records in a text file adds complexity.
Indexing Overhead: Requires either building an index or using advanced file seeking, which may not yield significant benefits for a single query.
Approach 3: Preprocessing and Indexing

Description: Build an index mapping each date to file offsets in a preprocessing step.
Pros:
Optimized for Multiple Queries: Quick lookups after the index is built.
Cons:
Upfront Cost & Complexity: Additional processing is required initially and index maintenance is needed as logs are appended.


2. Final Solution Summary
For this task, Approach 1 (Full File Scan Using Streaming) was chosen. Despite scanning the entire file, using buffered I/O keeps the memory usage minimal and the implementation straightforward. Given that the logs are nearly evenly distributed across days and the requirement is to optimize for speed and resource usage for the first query, this solution is both robust and efficient enough for our needs.

Compilation:

Open a terminal/command prompt in the directory containing the Java file.
Compile the code using:
bash
Copy
Edit
javac LogRetriever.java
Execution:

Run the program with the desired date as an argument (format: YYYY-MM-DD). For example:
bash
Copy
Edit
java LogRetriever 2024-12-01
The program will read through logs.txt and write all log entries for 2024-12-01 to the file:
bash
Copy
Edit
output/output_2024-12-01.txt
Output:

Once finished, the program will print the output file location and the total running time.
