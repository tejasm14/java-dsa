package test;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class IdentifySpecialCharactersToFile {
    public static void main(String[] args) {
        String inputFilePath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\csv-special-character";
        String outputFilePath = "path/to/your/output.txt";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), StandardCharsets.UTF_8))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    // Check for replacement character or any special character
                    if (c == '\uFFFD' || (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c))) {
                        writer.write("Line " + lineNumber + ", Position " + (i + 1) + ": Character '" + c +
                                "' (Unicode: \\u" + String.format("%04X", (int) c) + ")\n");
                    }
                }
            }

            System.out.println("Special characters have been identified and written to " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

