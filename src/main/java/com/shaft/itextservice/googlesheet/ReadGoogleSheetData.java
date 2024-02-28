/*
 * package com.shaft.itextservice.googlesheet;
 * 
 * import com.google.api.client.auth.oauth2.Credential; import
 * com.google.api.client.extensions.java6.auth.oauth2.
 * AuthorizationCodeInstalledApp; import
 * com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
 * import
 * com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
 * import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
 * import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
 * import com.google.api.client.json.jackson2.JacksonFactory; import
 * com.google.api.client.util.store.FileDataStoreFactory; import
 * com.google.api.services.sheets.v4.Sheets; import
 * com.google.api.services.sheets.v4.SheetsScopes; import
 * com.google.api.services.sheets.v4.model.ValueRange;
 * 
 * import java.io.*; import java.util.Arrays; import java.util.List;
 * 
 * public class ReadGoogleSheetData {
 * 
 * private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
 * 
 * public static void main(String[] args) {
 * 
 * String spreadSheetID = "1QCT6mbjmuwZlAXBw4i_q2kPpbM4mJJIGKOva_4UxUms"; String
 * sheetName = "testing"; String range = "A1:D3"; try {
 * System.out.println(Arrays.deepToString(getData(spreadSheetID, sheetName,
 * range))); } catch (Exception e) { System.out.println("error occurred..."+e);
 * }
 * 
 * }
 * 
 * private static Credential authorize() throws Exception { String
 * credentialLocation = System.getProperty("user.dir") + "/credentials.json";
 * 
 * GoogleClientSecrets clientSecrets =
 * GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new
 * FileReader(credentialLocation));
 * 
 * List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
 * 
 * GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = new
 * GoogleAuthorizationCodeFlow
 * .Builder(GoogleNetHttpTransport.newTrustedTransport(),
 * JacksonFactory.getDefaultInstance(), clientSecrets, scopes)
 * .setDataStoreFactory(new FileDataStoreFactory(new
 * File(System.getProperty("user.dir") + "/GoogleAPIKey")))
 * .setAccessType("offline") .build();
 * 
 * //return new AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, new
 * LocalServerReceiver()).authorize("user"); }
 * 
 * public static String[][] getData(String spreadSheetId, String sheetName,
 * String rangeDataToRead) throws Exception { Sheets sheet = new
 * Sheets(GoogleNetHttpTransport.newTrustedTransport(),
 * JacksonFactory.getDefaultInstance(), authorize());
 * 
 * List<List<Object>> data = sheet.spreadsheets().values() .get(spreadSheetId,
 * sheetName + "!" + rangeDataToRead) .execute().getValues();
 * 
 * return convertToArray(data); }
 * 
 * private static String[][] convertToArray(List<List<Object>> data) {
 * String[][] array = new String[data.size()][];
 * 
 * int i = 0; for (List<Object> row : data) { array[i++] = row.toArray(new
 * String[row.size()]); } return array; }
 * 
 * public static void updateData(String spreadSheetId, String sheetName, String
 * cellLocation, String newValue) throws Exception {
 * 
 * if (cellLocation.contains(":")) { throw new Exception(String.
 * format("Restricting update to single cell only. You are trying to update cells %s"
 * , cellLocation)); }
 * 
 * Sheets sheets = new Sheets(GoogleNetHttpTransport.newTrustedTransport(),
 * JacksonFactory.getDefaultInstance(), authorize());
 * 
 * ValueRange updateValue = new ValueRange()
 * .setValues(Arrays.asList(Arrays.asList(newValue)));
 * 
 * sheets.spreadsheets().values() .update(spreadSheetId, sheetName + "!" +
 * cellLocation, updateValue) .setValueInputOption("RAW") .execute(); } }
 */