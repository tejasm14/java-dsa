package testdate;

public class TestSqlValue {

    public static void main(String[] args) {

        String input = "\"25841\",\"223169\",\"ADDITIONAL FEATURES\",\"DRIVER UNIT\",\"3\" (Mid-Range/Treble)\"";

        // Split the string into parts
        String[] parts = input.split("\",\"");

        // Replace double quotes with single quotes for all parts except the last one
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].replace("\"", "'");
        }

        // Handle the last part separately to retain the double quote after "3"
        String lastPart = parts[parts.length - 1];
        if (lastPart.startsWith("'")) {
            lastPart = lastPart.replaceFirst("'", ""); // Remove the single quote at the start
        }
        if (lastPart.endsWith("'")) {
            lastPart = lastPart.substring(0, lastPart.length() - 1) + "\""; // Replace the last single quote with a double quote
        }
        parts[parts.length - 1] = lastPart;

        // Join the parts back together
        String output = String.join("','", parts);

        System.out.println(output);

    }
}
