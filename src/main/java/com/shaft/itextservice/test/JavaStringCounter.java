package com.shaft.itextservice.test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaStringCounter {

    public static void main(String[] args) {
        String jsonString = "{"
                + "\"storeName\": \"jcr:content/root/container/container/container_89532179/ownerinfo-storeName\","
                + "\"ownerName\": \"jcr:content/root/container/container/container_89532179/ownerinfo-ownerName\","
                + "\"phoneNumber\": \"jcr:content/root/container/container/container_89532179/ownerinfo-ownerNumber\","
                + "\"description\": \"jcr:content/root/container/container/container_89532179/ownerinfo-description\","
                + "\"bannerImagePath\": \"jcr:content/root/container/container/container/opuscontainer_254688/image_902477306-fileReference\","
                + "\"journey01Title\": \"jcr:content/root/container/container/swiffyslider/slide1/aboutusteaser_170375-pretitle\","
                + "\"journey01SubTitle\": \"jcr:content/root/container/container/swiffyslider/slide1/aboutusteaser_170375-jcr:description\","
                + "\"journey01Description\": \"jcr:content/root/container/container/swiffyslider/slide1/aboutusteaser_170375-jcr:description\","
                + "\"journey01Image\": \"jcr:content/root/container/container/swiffyslider/slide1/aboutusteaser_170375-fileReference\","
                + "\"journey02Title\": \"jcr:content/root/container/container/swiffyslider/slide2/aboutusteaser_170375-pretitle\","
                + "\"journey02SubTitle\": \"jcr:content/root/container/container/swiffyslider/slide2/aboutusteaser_170375-jcr:description\","
                + "\"journey02Description\": \"jcr:content/root/container/container/swiffyslider/slide2/aboutusteaser_170375-jcr:description\","
                + "\"journey02Image\": \"jcr:content/root/container/container/swiffyslider/slide2/aboutusteaser_170375-fileReference\","
                + "\"journey03Title\": \"jcr:content/root/container/container/swiffyslider/slide3/aboutusteaser_170375-pretitle\","
                + "\"journey03SubTitle\": \"jcr:content/root/container/container/swiffyslider/slide3/aboutusteaser_170375-jcr:description\","
                + "\"journey03Description\": \"jcr:content/root/container/container/swiffyslider/slide3/aboutusteaser_170375-jcr:description\","
                + "\"journey03Image\": \"jcr:content/root/container/container/swiffyslider/slide3/aboutusteaser_170375-fileReference\","
                + "\"journey04Title\": \"jcr:content/root/container/container/swiffyslider/slide4/aboutusteaser_170375-pretitle\","
                + "\"journey04SubTitle\": \"jcr:content/root/container/container/swiffyslider/slide4/aboutusteaser_170375-jcr:description\","
                + "\"journey04Description\": \"jcr:content/root/container/container/swiffyslider/slide4/aboutusteaser_170375-jcr:description\","
                + "\"journey04Image\": \"jcr:content/root/container/container/swiffyslider/slide4/aboutusteaser_170375-fileReference\","
                + "\"journey05Title\": \"jcr:content/root/container/container/swiffyslider/slide5/aboutusteaser_170375-pretitle\","
                + "\"journey05SubTitle\": \"jcr:content/root/container/container/swiffyslider/slide5/aboutusteaser_170375-jcr:description\","
                + "\"journey05Description\": \"jcr:content/root/container/container/swiffyslider/slide5/aboutusteaser_170375-jcr:description\","
                + "\"journey05Image\": \"jcr:content/root/container/container/swiffyslider/slide5/aboutusteaser_170375-fileReference\","
                + "\"howWeMaketitle\": \"jcr:content/root/container/container/teaser_copy-jcr:title\","
                + "\"howWeDescription\": \"jcr:content/root/container/container/teaser_copy-jcr:description\","
                + "\"howWeMakeImage\": \"jcr:content/root/container/container/teaser_copy-fileReference\","
                + "\"productsSectionTitle\": \"jcr:content/root/container/container/opuscontainer_copy_c/opustext_copy_copy_c-text\","
                + "\"productsText\": \"jcr:content/root/container/container/opuscontainer_copy_c/opustext_copy_copy_c-text\","
                + "\"products01-Image\": \"jcr:content/root/container/container/opuscontainer_copy_c/swiffyslider/slider1/opusteaser_copy_copy-fileReference\","
                + "\"products01-Title\": \"jcr:content/root/container/container/opuscontainer_copy_c/swiffyslider/slider1/opusteaser_copy_copy-jcr:title\","
                + "\"products01-Description\": \"jcr:content/root/container/container/opuscontainer_copy_c/swiffyslider/slider1/opusteaser_copy_copy-jcr:description\","
                + "\"products02-Image\": \"jcr:content/root/container/container/opuscontainer_copy_c/swiffyslider/slide2/opusteaser_copy_copy-fileReference\","
                + "\"products02-Title\": \"jcr:content/root/container/container/opuscontainer_copy_c/swiffyslider/slide2/opusteaser_copy_copy-jcr:title\","
                + "\"products02-Description\": \"jcr:content/root/container/container/opuscontainer_copy_c/swiffyslider/slide2/opusteaser_copy_copy-jcr:description\""
                + "}";

        // Parse the JSON object
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();

        // Initialize the counter
        int journeyTitleCount = 0;

        // Regular expression pattern for "journeyXXTitle"
        Pattern pattern = Pattern.compile("journey\\d{2}Title");

        // Iterate through the keys
        Set<String> keys = jsonObject.keySet();
        Iterator itr = keys.iterator();
        while (itr.hasNext()) {
            String key = itr.next().toString();
            Matcher matcher = pattern.matcher(key);
            if (matcher.matches()) {
                journeyTitleCount++;
            }
        }

        // Print the result
        System.out.println("Number of keys matching 'journeyXXTitle': " + journeyTitleCount);
    }

}
