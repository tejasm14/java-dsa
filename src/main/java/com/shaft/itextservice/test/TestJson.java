package com.shaft.itextservice.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestJson {
	
	public static void main(String[] args) {
		 
		String originalJson =  "{\r\n"
				+ "   \"colorPages\":[\r\n"
				+ "      {\r\n"
				+ "         \"colourCode\":\"RR 3029\",\r\n"
				+ "         \"colourName\":\"Mehrunissa's Saffron\",\r\n"
				+ "         \"colourRedValue\":\"100\",\r\n"
				+ "         \"colourGreenValue\":\"200\",\r\n"
				+ "         \"colourBlueValue\":\"300\",\r\n"
				+ "         \"colourFamily\":\"Red\",\r\n"
				+ "         \"colourFamilyRgbReference\":\"RR 3029\",\r\n"
				+ "         \"availableFinishes\":\"Matte,Sheen,Gloss\",\r\n"
				+ "         \"colourTemperature\":\"10\",\r\n"
				+ "         \"lrv\":\"101\",\r\n"
				+ "         \"mood\":\"Earthy\",\r\n"
				+ "         \"pageNumber\":\"10\",\r\n"
				+ "         \"colourFamilyGradientsRgbReferences\":\"RR 3030,RR 3031,RR 3032,RR 3033\",\r\n"
				+ "         \"similarColourCodes\":\"RR 100,GG 00,ZZ 211\",\r\n"
				+ "         \"goesWellWithColourCodes\":\"RR 100,GG 00,ZZ 211\",\r\n"
				+ "         \"colourQuality\":\"Deep\",\r\n"
				+ "         \"isVisualizerEnabled\":\"Yes\",\r\n"
				+ "         \"isActive\":\"Yes\",\r\n"
				+ "         \"colourShopIn\":\"Sample, Exterior, Interior\",\r\n"
				+ "         \"colourLightDark\":\"Light\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "         \"colourCode\":\"GG 4040\",\r\n"
				+ "         \"colourName\":\"Enchanted Forest\",\r\n"
				+ "         \"colourRedValue\":\"34\",\r\n"
				+ "         \"colourGreenValue\":\"139\",\r\n"
				+ "         \"colourBlueValue\":\"34\",\r\n"
				+ "         \"colourFamily\":\"Green\",\r\n"
				+ "         \"colourFamilyRgbReference\":\"GG 4040\",\r\n"
				+ "         \"availableFinishes\":\"Matte,Sheen,Gloss\",\r\n"
				+ "         \"colourTemperature\":\"25\",\r\n"
				+ "         \"lrv\":\"45\",\r\n"
				+ "         \"mood\":\"Calming\",\r\n"
				+ "         \"pageNumber\":\"15\",\r\n"
				+ "         \"colourFamilyGradientsRgbReferences\":\"GG 4041,GG 4042,GG 4043,GG 4044\",\r\n"
				+ "         \"similarColourCodes\":\"GG 34,GG 139,ZZ 34\",\r\n"
				+ "         \"goesWellWithColourCodes\":\"GG 34,GG 139,ZZ 34\",\r\n"
				+ "         \"colourQuality\":\"Natural\",\r\n"
				+ "         \"isVisualizerEnabled\":\"Yes\",\r\n"
				+ "         \"isActive\":\"Yes\",\r\n"
				+ "         \"colourShopIn\":\"Sample, Exterior, Interior\",\r\n"
				+ "         \"colourLightDark\":\"Dark\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "         \"colourCode\":\"BB 2020\",\r\n"
				+ "         \"colourName\":\"Sapphire Dream\",\r\n"
				+ "         \"colourRedValue\":\"0\",\r\n"
				+ "         \"colourGreenValue\":\"0\",\r\n"
				+ "         \"colourBlueValue\":\"128\",\r\n"
				+ "         \"colourFamily\":\"Blue\",\r\n"
				+ "         \"colourFamilyRgbReference\":\"BB 2020\",\r\n"
				+ "         \"availableFinishes\":\"Matte,Sheen,Gloss\",\r\n"
				+ "         \"colourTemperature\":\"20\",\r\n"
				+ "         \"lrv\":\"5\",\r\n"
				+ "         \"mood\":\"Tranquil\",\r\n"
				+ "         \"pageNumber\":\"20\",\r\n"
				+ "         \"colourFamilyGradientsRgbReferences\":\"BB 2021,BB 2022,BB 2023,BB 2024\",\r\n"
				+ "         \"similarColourCodes\":\"BB 0,GG 0,ZZ 128\",\r\n"
				+ "         \"goesWellWithColourCodes\":\"BB 0,GG 0,ZZ 128\",\r\n"
				+ "         \"colourQuality\":\"Calm\",\r\n"
				+ "         \"isVisualizerEnabled\":\"Yes\",\r\n"
				+ "         \"isActive\":\"Yes\",\r\n"
				+ "         \"colourShopIn\":\"Sample, Exterior, Interior\",\r\n"
				+ "         \"colourLightDark\":\"Dark\"\r\n"
				+ "      },\r\n"
				+ "           {\r\n"
				+ "         \"colourCode\":\"BB 2020\",\r\n"
				+ "         \"colourName\":\"Sapphire Dream\",\r\n"
				+ "         \"colourRedValue\":\"0\",\r\n"
				+ "         \"colourGreenValue\":\"0\",\r\n"
				+ "         \"colourBlueValue\":\"128\",\r\n"
				+ "         \"colourFamily\":\"Blue\",\r\n"
				+ "         \"colourFamilyRgbReference\":\"BB 2020\",\r\n"
				+ "         \"availableFinishes\":\"Matte,Sheen,Gloss\",\r\n"
				+ "         \"colourTemperature\":\"20\",\r\n"
				+ "         \"lrv\":\"5\",\r\n"
				+ "         \"mood\":\"Tranquil\",\r\n"
				+ "         \"pageNumber\":\"20\",\r\n"
				+ "         \"colourFamilyGradientsRgbReferences\":\"BB 2021,BB 2022,BB 2023,BB 2024\",\r\n"
				+ "         \"similarColourCodes\":\"BB 0,GG 0,ZZ 128\",\r\n"
				+ "         \"goesWellWithColourCodes\":\"BB 0,GG 0,ZZ 128\",\r\n"
				+ "         \"colourQuality\":\"Calm\",\r\n"
				+ "         \"isVisualizerEnabled\":\"Yes\",\r\n"
				+ "         \"isActive\":\"Yes\",\r\n"
				+ "         \"colourShopIn\":\"Sample, Exterior, Interior\",\r\n"
				+ "         \"colourLightDark\":\"Dark\"\r\n"
				+ "      }\r\n"
				+ "   ]\r\n"
				+ "}";// Replace with your actual JSON string

	        // Parse the original JSON
        JsonObject originalJsonObject = new Gson().fromJson(originalJson, JsonObject.class);

        // Transform the JSON
        JsonObject transformedJson = transformJson(originalJsonObject);

        // Convert the transformed JSON back to a string
        String transformedJsonString = new Gson().toJson(transformedJson);

        // Print the transformed JSON string
        System.out.println(transformedJsonString);
		
		
		
	}
	
	
	 private static JsonObject transformJson(JsonObject originalJson) {
	        JsonObject transformedJson = new JsonObject();

	        // Iterate through color pages
	        JsonArray colorPages = originalJson.getAsJsonArray("colorPages");
	        for (JsonElement colorPageElement : colorPages) {
	            JsonObject colorPage = colorPageElement.getAsJsonObject();
	            String colorFamily = colorPage.get("colourFamily").getAsString();

	            // Create color family if not exists
	            if (!transformedJson.has(colorFamily)) {
	                transformedJson.add(colorFamily, new JsonArray());
	            }

	            // Add color page to the corresponding color family
	            JsonArray colorFamilyArray = transformedJson.getAsJsonArray(colorFamily);
	            colorFamilyArray.add(colorPage);
	        }

	        return transformedJson;
	    }

}
