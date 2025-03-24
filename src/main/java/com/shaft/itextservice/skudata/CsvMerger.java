package com.shaft.itextservice.skudata;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class CsvMerger {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\tejas.mohite\\Downloads\\prod-spec\\new-all-files";
        String outputFilePath = "C:\\Users\\tejas.mohite\\Downloads\\prod-spec\\merged-output-1.csv";

        try {
            // Get all CSV files in the folder
            List<Path> csvFiles = Files.list(Paths.get(folderPath))
                    .filter(path -> path.toString().endsWith(".csv"))
                    .collect(Collectors.toList());

            // Initialize writer for the output file
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
                boolean headerWritten = false;

                for (Path csvFile : csvFiles) {
                    try (BufferedReader reader = Files.newBufferedReader(csvFile)) {
                        String line;
                        boolean isFirstLine = true;

                        while ((line = reader.readLine()) != null) {
                            // Write header only once
                            if (isFirstLine) {
                                if (!headerWritten) {
                                    writer.write(line);
                                    writer.newLine();
                                    headerWritten = true;
                                }
                                isFirstLine = false;
                            } else {
                                writer.write(line);
                                writer.newLine();
                            }
                        }
                    }
                }
            }
            System.out.println("CSV files have been merged into " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

