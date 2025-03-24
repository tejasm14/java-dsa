package test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MinifyJson {

    public static void main(String[] args) {

        String response = "{\n" +
                "                \"orderNo\": \"7000011512\",\n" +
                "                \"orderType\": \"preBookOrder\",\n" +
                "                \"orderDateTime\": \"2025-02-06 13:21:36\",\n" +
                "                \"orderStatus\": \"pending\",\n" +
                "                \"isCancellable\": true,\n" +
                "                \"createdDate\": \"2025-02-06T13:21:36.000Z\",\n" +
                "                \"items\": [\n" +
                "                    {\n" +
                "                        \"metaData\": {\n" +
                "                            \"remaingingBalance\": 32000,\n" +
                "                            \"isPreBookProduct\": true,\n" +
                "                            \"isPartialPreBook\": true,\n" +
                "                            \"launchDate\": \"2025-02-07 12:29:00\",\n" +
                "                            \"uniqueOrderstatus\": \"pending\"\n" +
                "                        },\n" +
                "                        \"vanNo\": \"225809\",\n" +
                "                        \"quantity\": 1,\n" +
                "                        \"igst\": 305.0847457627117,\n" +
                "                        \"sgst\": 152.54237288135585,\n" +
                "                        \"cgst\": 152.54237288135585,\n" +
                "                        \"vsPrice\": 2000,\n" +
                "                        \"isCancellable\": true,\n" +
                "                        \"isBalancePaymentPaid\": false,\n" +
                "                        \"orderAmount\": 2000,\n" +
                "                        \"orderStatus\": \"willBeProcessed\",\n" +
                "                        \"title\": \"HITACHI AC RAS.G518PCBISF (5 STAR-INV) 1.5TN SPL - SET\",\n" +
                "                        \"imageUrl\": \"http://vsstaging.vijaysales.com/media/catalog/product/2/2/225809-image1_1.jpg\",\n" +
                "                        \"productUrl\": \"https://stage.vijaysales.com/p/P225341/225809/hitachi-yoshi-5400fxl-1-5-ton-5-star-inverter-split-ac-with-copper-condenser-2-way-swing-5-fan-speed-ras-g518pcbisf-2023-model\",\n" +
                "                        \"category\": \"Air Conditioner\",\n" +
                "                        \"createdDate\": \"2025-02-06T13:21:36.000Z\",\n" +
                "                        \"expectedDeliveryDate\": \"2025-02-08T13:21:38.163Z\",\n" +
                "                        \"productDetails\": {\n" +
                "                            \"vanNo\": \"225809\",\n" +
                "                            \"mrp\": \"50000.000000\",\n" +
                "                            \"vsp\": \"34000.000000\",\n" +
                "                            \"isPrebookPartialPayment\": true,\n" +
                "                            \"isPrebookProduct\": true,\n" +
                "                            \"prebookEndDate\": \"2025-01-31 05:43:00\",\n" +
                "                            \"prebookLaunchDate\": \"2025-01-30 05:32:00\",\n" +
                "                            \"isPartialPrebook\": false\n" +
                "                        },\n" +
                "                        \"itemLineNo\": \"zeR2sXbX0n\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"payment\": [\n" +
                "                    {\n" +
                "                        \"method\": \"Prepaid\",\n" +
                "                        \"paymentMode\": \"CARD\",\n" +
                "                        \"billAmount\": 2000\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"shippingAddress\": {\n" +
                "                    \"gstin\": \"\",\n" +
                "                    \"shippingName\": \"Susmita Gawade\",\n" +
                "                    \"shippingMobileNumber\": \"7506684862\",\n" +
                "                    \"shippingAddress1\": \"zxcvbn\",\n" +
                "                    \"shippingAddress2\": \"asdf\",\n" +
                "                    \"shippingPincode\": \"400101\",\n" +
                "                    \"shippingCity\": \"Mumbai\",\n" +
                "                    \"shippingState\": \"Maharashtra\",\n" +
                "                    \"shippingLandmark\": \" \",\n" +
                "                    \"latitude\": \"19.21\",\n" +
                "                    \"longitude\": \"72.87\"\n" +
                "                }\n" +
                "            }";

        System.out.println(minifyJson(response));
    }

    public static String minifyJson(String jsonString) {
        Gson gson = new Gson();
        JsonElement jsonElement = new JsonParser().parse(jsonString);
        return gson.toJson(jsonElement);
    }
}
