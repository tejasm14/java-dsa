package com.shaft.itextservice.test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

public class HashGenerator {

    public static boolean generateHash(String message, String secretKey, String hashType, StringBuilder hash) {
        boolean result = false;

        try {
            // Convert Secret Key to required format
            byte[] convertedHashKey = new byte[secretKey.length() / 2];
            for (int i = 0; i < secretKey.length() / 2; i++) {
                convertedHashKey[i] = (byte) Integer.parseInt(secretKey.substring(i * 2, i * 2 + 2), 16);
            }

            // Build string from collection
            hash.setLength(0); // Clear the StringBuilder

            // Generate hash
            if (hashType.toUpperCase().equals("SHA256")) {
                Mac sha256Hmac = Mac.getInstance("HmacSHA256");
                SecretKeySpec secretKeySpec = new SecretKeySpec(convertedHashKey, "HmacSHA256");
                sha256Hmac.init(secretKeySpec);
                byte[] hashBytes = sha256Hmac.doFinal(message.toString().getBytes("UTF-8"));
                for (byte b : hashBytes) {
                    hash.append(String.format("%02X", b));
                }
                result = true;
            }

            if (hash.length() == 0) {
                result = false;
            }
        } catch (Exception ex) {
            result = false;
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            String request="ew0KICAgICJtZXJjaGFudF9kYXRhIjogew0KICAgICAgICAibWVyY2hhbnRfaWQiOiAxMDY2MDAsDQogICAgICAgICJtZXJjaGFudF9hY2Nlc3NfY29kZSI6ICJiY2Y0NDFiZS00MTFiLTQ2YTEtYWE4OC1jNmU4NTJhN2Q2OGMiLA0KICAgICAgICAidW5pcXVlX21lcmNoYW50X3R4bl9pZCI6ICJvcmRlcjAwMDE1IiwNCiAgICAgICAgIm1lcmNoYW50X3JldHVybl91cmwiOiAiaHR0cDovL2xvY2FsaG9zdC9waW5lbGFicy9pbmRleC5waHAiDQogICAgfSwNCiAgICAicGF5bWVudF9kYXRhIjogew0KICAgICAgICAiYW1vdW50X2luX3BhaXNhIjogMTEwMDAwMA0KICAgIH0sDQogICAgInVkZl9kYXRhIjogew0KICAgICAgICAidWRmX2ZpZWxkXzEiOiAicGluZWxhYnMiLA0KICAgICAgICAidWRmX2ZpZWxkXzIiOiAiIiwNCiAgICAgICAgInVkZl9maWVsZF8zIjogIiIsDQogICAgICAgICJ1ZGZfZmllbGRfNCI6ICIiDQogICAgfSwNCiAgICAidHhuX2RhdGEiOiB7DQogICAgICAgICJuYXZpZ2F0aW9uX21vZGUiOiAiMiIsDQogICAgICAgICJwYXltZW50X21vZGUiOiAiMSIsDQogICAgICAgICJ0cmFuc2FjdGlvbl90eXBlIjogIjEiLA0KICAgICAgICAidGltZV9zdGFtcCI6IDE1NzU4ODAwMDAwMA0KICAgIH0NCn0=";
            String secretKey = "9A7282D0556544C59AFE8EC92F5C85F6"; // Replace with your actual secret key
            String hashType = "SHA256";

            StringBuilder hash = new StringBuilder();
            if (generateHash(request, secretKey, hashType, hash)) {
                System.out.println("Generated X-VERIFY Hash: " + hash.toString());
            } else {
                System.out.println("Failed to generate X-VERIFY hash.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
