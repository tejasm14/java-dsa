package com.shaft.itextservice.skudata;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VJSDataconverter {

    public static void main(String[] args) {
        // Input and output file paths
        String inputFile = "D:\\input.csv";
        String outputFile = "D:\\output.csv";

        try {
            // Read the input file
            List<String> rows = readCsv(inputFile);

            // Transform the data
            List<String[]> transformedData = transformData(rows);

            // Write to the output file
            writeCsv(outputFile, transformedData);

            System.out.println("Transformation completed. File saved to: " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads the CSV file into a list of strings
    public static List<String> readCsv(String filePath) throws IOException {
        List<String> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }
        }
        return rows;
    }

    // Transforms the input data into two columns
    public static List<String[]> transformData(List<String> rows) {
        List<String[]> outputData = new ArrayList<>();

        // The first row contains the heading
        String heading = rows.get(0);
        String[] headingStr = heading.split(",");

        // Add the new heading row
        outputData.add(new String[]{"SKU", "heading", "key", "value"});

        // Add the transformed rows
        for (int i = 1; i < rows.size(); i++) {

            String data = rows.get(i);
            String[] dataStr = data.split(",");
            for(int j=0; j<dataStr.length-1; j++) {
                System.out.println("data size"+j+" "+dataStr.length);
                outputData.add(new String[]{dataStr[0], j<8?"MANUFACTURER DETAILS":"DIMENSION WEIGHT DETAILS", headingStr[j], dataStr[j]});
            }
        }

        return outputData;
    }

    // Writes the transformed data to a CSV file
    public static void writeCsv(String filePath, List<String[]> data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }
}
