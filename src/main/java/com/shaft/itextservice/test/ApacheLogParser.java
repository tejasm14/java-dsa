package com.shaft.itextservice.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApacheLogParser {
    public static void main(String argv[]) {
        String regex = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"(.+?)\"";
        String ApacheLogSample = "123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] \"GET /java/javaResources.html "
                + "HTTP/1.0\" 200 10450 \"-\" \"Mozilla/4.6 [en] (X11; U; OpenBSD 2.8 i386; Nav)\"";
        Pattern p = Pattern.compile(regex);
        System.out.println("Apache log input line: " + ApacheLogSample);
        Matcher matcher = p.matcher(ApacheLogSample);
        if (matcher.find()) {
            System.out.println("IP Address: " + matcher.group(1));
            System.out.println("UserName: " + matcher.group(3));
            System.out.println("Date/Time: " + matcher.group(4));
            System.out.println("Request: " + matcher.group(5));
            System.out.println("Response: " + matcher.group(6));
            System.out.println("Bytes Sent: " + matcher.group(7));
            if (!matcher.group(8).equals("-"))
                System.out.println("Referer: " + matcher.group(8));
            System.out.println("User-Agent: " + matcher.group(9));
            //this is hello
            //this -1 is -2 hello-3
        }
    }
}
