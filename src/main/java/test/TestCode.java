package test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestCode {
    public static void main(String[] args) {

        Map<String,String> value = new HashMap<>();
        Map m = Collections.synchronizedMap(new HashMap());

        System.out.println(oneDecimalValue(4 + 0.0)/2);
    }

    public static double oneDecimalValue(double value){
        String formattedValue = String.format("%.1f", value);
        return Double.parseDouble(formattedValue);
    }
}
