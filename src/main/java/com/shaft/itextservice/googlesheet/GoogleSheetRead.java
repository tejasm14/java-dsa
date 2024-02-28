/*
 * package com.shaft.itextservice.googlesheet;
 * 
 * import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
 * import com.google.api.client.googleapis.auth.oauth2.GoogleCredential; import
 * com.google.api.client.googleapis.javanet.GoogleNetHttpTransport; import
 * com.google.api.client.json.jackson2.JacksonFactory; import
 * com.google.api.services.sheets.v4.Sheets; import
 * com.google.api.services.sheets.v4.SheetsScopes; import
 * com.google.api.services.sheets.v4.model.ValueRange; import
 * com.google.auth.http.HttpCredentialsAdapter; import
 * com.google.auth.oauth2.GoogleCredentials; import
 * com.google.auth.oauth2.ServiceAccountCredentials;
 * 
 * import java.io.*; import java.nio.file.Files; import java.nio.file.Path;
 * import java.nio.file.StandardCopyOption; import
 * java.security.GeneralSecurityException; import java.util.Collections; import
 * java.util.List;
 * 
 * public class GoogleSheetRead {
 * 
 * private static final String APPLICATION_NAME = "Test Google sheet"; private
 * static final String SPREADSHEET_ID =
 * "1QCT6mbjmuwZlAXBw4i_q2kPpbM4mJJIGKOva_4UxUms"; private static final String
 * RANGE = "Sheet1!A2:B15";
 * 
 * private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
 * 
 * public static void main(String[] args) throws IOException,
 * GeneralSecurityException { // Load credentials from a JSON key file (replace
 * 'path/to/credentials.json' with your file path) InputStream inputStream =
 * GoogleSheetRead.class.getClassLoader().getResourceAsStream("credentials.json"
 * ); Path tempFile = Files.createTempFile("credentials", ".json");
 * Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
 * GoogleCredentials credentials = ServiceAccountCredentials .fromStream(new
 * FileInputStream("D:\\TEJAS MOHITE\\Projects-06-06-2022\\Java-practice-git\\java-dsa\\src\\main\\resources\\credentials.json"
 * )) .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
 * 
 * // Build a Sheets service with the provided credentials Sheets sheetsService
 * = new Sheets.Builder( GoogleNetHttpTransport.newTrustedTransport(),
 * JacksonFactory.getDefaultInstance(), new HttpCredentialsAdapter(credentials)
 * ).setApplicationName(APPLICATION_NAME).build();
 * 
 * // Retrieve data from the specified range ValueRange response =
 * sheetsService.spreadsheets().values().get(SPREADSHEET_ID, RANGE).execute();
 * 
 * // Get values from the response List<List<Object>> values =
 * response.getValues(); if (values == null || values.isEmpty()) {
 * System.out.println("No data found."); } else { for (List<Object> row :
 * values) { System.out.println(row); } } } }
 */