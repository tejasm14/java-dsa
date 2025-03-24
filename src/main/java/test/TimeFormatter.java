package test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TimeFormatter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Map for common timezone abbreviations
        Map<String, String> timeZoneMap = new HashMap<>();
        timeZoneMap.put("IST", "Asia/Kolkata"); // Indian Standard Time
        timeZoneMap.put("UTC", "UTC");         // Coordinated Universal Time

        // Get the desired time format from the user
        System.out.println("Enter the time format (e.g., yyyy-MM-dd HH:mm:ss):");
        String timeFormat = scanner.nextLine();

        // Get the desired timezone from the user
        System.out.println("Enter the timezone (e.g., IST, UTC):");
        String timeZoneAbbreviation = scanner.nextLine().toUpperCase();

        try {
            // Resolve timezone abbreviation to ZoneId
            String timeZoneId = timeZoneMap.getOrDefault(timeZoneAbbreviation, timeZoneAbbreviation);
            ZoneId zoneId = ZoneId.of(timeZoneId);

            // Create a formatter using the given format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);

            // Get the current time in the specified timezone
            ZonedDateTime currentTime = ZonedDateTime.now(zoneId);

            // Format the current time and display it
            String formattedTime = currentTime.format(formatter);
            System.out.println("Current time in the specified format and timezone: " + formattedTime);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please check the time format and timezone (e.g., IST, UTC) and try again.");
        }

        scanner.close();
    }
}

