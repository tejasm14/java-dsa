package test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncoding {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = URLEncoder.encode("air-purifiers/suitable-for/buy-home-&-office-air-purifiers","UTF-8");
        System.out.println(url);
    }
}
