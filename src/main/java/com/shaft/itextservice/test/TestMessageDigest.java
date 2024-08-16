package com.shaft.itextservice.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TestMessageDigest {

    public static void main(String[] args) {

        String request = "{\n" +
                "    \"merchant_data\": {\n" +
                "        \"merchant_id\": 106600,\n" +
                "        \"merchant_access_code\": \"bcf441be-411b-46a1-aa88-c6e852a7d68c\",\n" +
                "        \"unique_merchant_txn_id\": \"order00009\",\n" +
                "        \"merchant_return_url\": \"http://localhost/pinelabs/index.php\"\n" +
                "    },\n" +
                "    \"payment_data\": {\n" +
                "        \"amount_in_paisa\": 1100000\n" +
                "    },\n" +
                "    \"udf_data\": {\n" +
                "        \"udf_field_1\": \"pinelabs\",\n" +
                "        \"udf_field_2\": \"\",\n" +
                "        \"udf_field_3\": \"\",\n" +
                "        \"udf_field_4\": \"\"\n" +
                "    },\n" +
                "    \"txn_data\": {\n" +
                "        \"navigation_mode\": \"2\",\n" +
                "        \"payment_mode\": \"1\",\n" +
                "        \"transaction_type\": \"1\",\n" +
                "        \"time_stamp\": 157588000000\n" +
                "    }\n" +
                "}";

        System.out.println(encryptString(request));

        try {
            // Convert the JSON string to a Map
            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.readValue(request, Object.class);

            // Convert and encode to Base64
            String base64String = convertToBase64(jsonObject);
            System.out.println("Base64 Encoded String: " + base64String);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String encryptString(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply the SHA-256 algorithm to the input
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            // Convert the byte array into a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Return the hashed string
            return hexString.toString();

        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertToBase64(Object jsonObject) {
        try {
            // Convert the JSON object to a string
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(jsonObject);

            // Encode the string to Base64
            Base64.Encoder encoder = Base64.getEncoder();
            String base64String = encoder.encodeToString(jsonString.getBytes("UTF-8"));

            return base64String;
        } catch (JsonProcessingException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
