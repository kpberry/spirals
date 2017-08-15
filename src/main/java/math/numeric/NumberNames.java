package math.numeric;

/**
 * Created by Kevin on 8/14/2017 for Spirals.
 * <p>
 * Computes the english names of numbers, as well as their lengths.
 */
public class NumberNames {
    private static final String[] small = {
            "zero", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty"
    };

    private static final String[] tens = {
            "ten", "twenty", "thirty", "forty", "fifty",
            "sixty", "seventy", "eighty", "ninety"
    };

    private static final String[] groupings = {
            "", "thousand", "million", "billion", "trillion",
            "quadrillion", "quintillion", "sextillion", "septillion",
            "octillion", "nonillion", "decillion", "undecillion",
            "duodecillion", "tredecillion", "quattuordecillion",
            "quindecillion", "sexdecillion", "septendecillion",
            "octodecillion", "novemdecillion", "vigintillion"
    };

    /**
     * Returns the english name of a number.
     *
     * @param n the number to translate
     * @return the english name of the number
     */
    public static CharSequence getNumberName(long n) {
        if (n == 0) {
            return "zero";
        }
        int count = 0;
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            int mod = (int) (n % 1000);
            if (mod > 0) {
                result.insert(0, under1000(mod) + " " + groupings[count] + " ");
            }
            count++;
            n /= 1000;
        }
        return result.toString().trim();
    }

    private static String under1000(int n) {
        if (n == 0) {
            return "zero";
        }
        assert (n <= 1000);
        String result = "";
        if (n >= 100) {
            result += small[n / 100] + " hundred ";
        }
        if ((n % 100) >= 20) {
            result += tens[((n % 100) / 10) - 1];
            if ((n % 10) != 0) {
                result += "-" + small[n % 10];
            }
        } else if (((n % 10) > 0) || ((n % 100) == 10)) {
            result += small[n % 20];
        }
        return result.trim();
    }

    /**
     * Gets the number of letters in the english name of a number.
     *
     * @param n the number whose name length will be retrieved
     * @return the length of the english name of the number
     */
    public static int getNumberNameLength(long n) {
        return getNumberName(n).length();
    }
}


