import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LogRetriever {

    // Change this if your log file has a different name or path.
    private static final String INPUT_FILE = "logs.txt";
    private static final String OUTPUT_DIR = "output";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java LogRetriever <YYYY-MM-DD>");
            System.exit(1);
        }

        String targetDate = args[0];

        // Validate date format
        if (!isValidDate(targetDate)) {
            System.err.println("Invalid date format. Please use YYYY-MM-DD.");
            System.exit(1);
        }

        // Ensure output directory exists
        File outDir = new File(OUTPUT_DIR);
        if (!outDir.exists() && !outDir.mkdir()) {
            System.err.println("Failed to create output directory: " + OUTPUT_DIR);
            System.exit(1);
        }

        String outputFilePath = OUTPUT_DIR + File.separator + "output_" + targetDate + ".txt";

        long startTime = System.currentTimeMillis();
        try {
            retrieveLogsForDate(targetDate, INPUT_FILE, outputFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred while processing the log file: " + e.getMessage());
            System.exit(1);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Log retrieval complete. Output saved to: " + outputFilePath);
        System.out.println("Total running time: " + (endTime - startTime) + " ms");
    }

    /**
     * Validates if the provided date string is in the correct format (YYYY-MM-DD).
     *
     * @param dateStr The date string to validate.
     * @return true if valid, false otherwise.
     */
    private static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr); // ISO_LOCAL_DATE format (YYYY-MM-DD)
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Reads the log file line by line and writes all log entries matching the target date to the output file.
     *
     * @param targetDate The date to filter logs by (YYYY-MM-DD).
     * @param inputFile  The input log file path.
     * @param outputFile The output file path.
     * @throws IOException If an I/O error occurs.
     */
    private static void retrieveLogsForDate(String targetDate, String inputFile, String outputFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line is long enough and starts with the target date.
                if (line.length() >= 10 && line.startsWith(targetDate)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}
