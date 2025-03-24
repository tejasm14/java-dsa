/*
* This utility is use to separate the mixed prod spec file
* */

package testmix;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterCsvBySkusTest {

    public static void main(String[] args) {
        String inputCsvPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\mixed-prod-spec-files-segregate-20-01-2024\\mixed-prod-spec-28-oct.csv"; // Path to your input CSV file
        String outputCsvPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024" +
                "\\mixed-prod-spec-files-segregate-20-01-2024\\output\\wine-cooler-product-specification.csv"; // Path for the output CSV file

        // Add all target SKUs here
        Set<String> targetSkus = new HashSet<>();
        targetSkus.add("201581");
        targetSkus.add("200627");

        try (
                CSVReader csvReader = new CSVReaderBuilder(new FileReader(inputCsvPath))
                        .withCSVParser(new CSVParserBuilder().withSeparator(',').withQuoteChar('"').withEscapeChar('\\').build())
                        .build();
                CSVWriter csvWriter = new CSVWriter(new FileWriter(outputCsvPath))
        ) {
            //START
            /*Reader reader = new FileReader("C:\\Users\\tejas.mohite\\Downloads\\mixed-prod-spec\\mixed-prod-spec-28-oct.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withQuote('"')
                    .withEscape('\\')
                    .parse(reader);

            int rowCount = 0;
            for (CSVRecord record : records) {
                System.out.println("Row " + rowCount + ": " + record);
                rowCount++;
            }
            System.out.println("Total Rows Read: " + rowCount);*/
            //END

            List<String[]> allRows = csvReader.readAll(); // Read all rows at once

            // Get the header row (if present)
            String[] header = allRows.get(0);

            // Prepare a list to hold filtered rows
            List<String[]> filteredRows = new ArrayList<>();
            filteredRows.add(header); // Add header row to output

            // Iterate through rows and filter by target SKUs
            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                // Ensure the row has at least two columns to avoid index out of bounds
                if (row.length > 1 && targetSkus.contains(row[1])) { // Assuming 'SKU' is the second column
                    filteredRows.add(row);
                }
            }

            // Write filtered rows to output CSV
            csvWriter.writeAll(filteredRows);

            System.out.println("Filtered rows written to " + outputCsvPath);

        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }



}
