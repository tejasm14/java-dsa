package testmix;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterCSVBySKU {

    public static void main(String[] args) {
        // Input CSV file path
        String inputCsvFile = "C:\\Users\\tejas.mohite\\Downloads\\mixed-prod-spec\\mixed-prod-spec-28-oct.csv";

        // Output CSV file path
        String outputCsvFile = "C:\\Users\\tejas.mohite\\Downloads\\mixed-prod-spec\\output\\food-processor1.csv";

        // List of SKUs to filter
        List<String> skusToFilter = Arrays.asList("156150","234824"); // Replace with your input SKUs

        // Filter and create new CSV
        try {
            filterCsvBySku(inputCsvFile, outputCsvFile, skusToFilter);
            System.out.println("Filtered CSV created successfully: " + outputCsvFile);
        } catch (IOException e) {
            System.err.println("Error processing the CSV file: " + e.getMessage());
        }
    }

    public static void filterCsvBySku(String inputCsvFile, String outputCsvFile, List<String> skusToFilter) throws IOException {
        // Normalize SKUs to filter
        List<String> normalizedSkusToFilter = skusToFilter.stream()
                .map(sku -> sku.trim().toLowerCase())
                .collect(Collectors.toList());

        // Open input and output streams using OpenCSV
        try (CSVReader reader = new CSVReader(new FileReader(inputCsvFile));
             CSVWriter writer = new CSVWriter(new FileWriter(outputCsvFile),
                     CSVWriter.DEFAULT_SEPARATOR,
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {

            // Read all rows from the input CSV
            List<String[]> allRows = reader.readAll();

            if (allRows.isEmpty()) {
                throw new IOException("Input CSV file is empty.");
            }

            // Write the header row to the output CSV
            String[] headerRow = allRows.get(0);
            writer.writeNext(headerRow, false);

            // Filter and write rows
            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);

                // Debugging: Log the SKU column value
                if (row.length > 1) {
                    String sku = row[1].trim().toLowerCase();
                    System.out.println("Processing SKU: " + sku);

                    if (normalizedSkusToFilter.contains(sku)) {
                        writer.writeNext(row, false);
                    }
                }
            }
        }
    }
}
