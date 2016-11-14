package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by immoskyl on 08/11/16.
 */
public class Maths {

    public  static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double calcPercentage (double initialValue, double percentage) {
        return round (((initialValue * 100) - (initialValue * percentage)) / 100, 2); //safe way to divide && keeps only 2 digit after coma
    }

    public int hashMD31(String strToHash) {
        int hash = 7;
        for (int i = 0; i < strToHash.length(); i++) {
            hash = hash*31 + strToHash.charAt(i);
        }
        return hash;
    }
}
