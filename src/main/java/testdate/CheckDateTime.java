package testdate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class CheckDateTime {

    public static void main(String[] args) {
        System.out.println(getDateStringByTimeZone("IST","yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
    }

    public static String getDateStringByTimeZone(String timeZone, String format) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(timeZone, "Time Zone cannot be null");
        Objects.requireNonNull(format, "Date format cannot be null");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        String utcToDate = dateFormat.format(date);
        return utcToDate;
    }

}
