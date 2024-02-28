package com.shaft.itextservice.test;

import java.io.UnsupportedEncodingException;

public class TestEncrypt {
    public static void main(String[] args) {

        String data = "MTI4OjoxMDAwMDo6YTAxM2Y5YjBiZTlkNTM0NjVhODFjZDAzNjM3YTVjYTE6OjlkN2RkNjkwYTM0ZjBlZjI5NGZmZDg2YTU1MWQ3YzBkOjpEdG9JVVdRYThWM29MNk9SM210Snd2UjFGcGZOaUIvTHBDbDFrUVF5Wm1zPQ==";
        try {
            String decodedString = new String(data.getBytes(), "UTF-8");
            System.out.println(decodedString);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
