package solidbeans.com.handla.util;

/**
 * Modulo 10 check for credit cards, social security numbers etc.
 *
 * Algorithm form  http://en.wikipedia.org/wiki/Luhn_algorithm
 */
public class LuhnChecker {

    public boolean isValid(String number) {
        String digitsOnly = removeNonDigits(number);
        try {
            return !isEmpty(digitsOnly) && lastDigitMatchesChecksum(digitsOnly);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public int getCheckDigit(String number) {
        String digitsOnly = removeNonDigits(number);
        if(digitsOnly == null || digitsOnly.length() < 2) {
            throw new IllegalArgumentException();
        }
        int sum = luhnSum(digitsOnly);
        return (10 - (sum % 10)) % 10;
    }

    private boolean lastDigitMatchesChecksum(String digitsOnly) {
        int lastIndex = digitsOnly.length() - 1;
        int lastDigit = Integer.parseInt(digitsOnly.substring(lastIndex));
        String numberWithoutLastDigit = digitsOnly.substring(0, lastIndex);
        int checkDigit = getCheckDigit(numberWithoutLastDigit);
        return lastDigit == checkDigit;
    }

    private int luhnSum(String digits) {
        int sum = 0;
        int temp;
        boolean timesTwo = true;
        for(int i = digits.length(); i > 0; i--) {
            temp = Integer.parseInt(digits.substring(i - 1, i));
            if(timesTwo) {
                temp *= 2;
                if(temp > 9) {
                    sum -= 9;
                }
            }
            sum += temp;
            timesTwo = !timesTwo;
        }
        return sum;
    }

    private String removeNonDigits(String number) {
        return number.replaceAll("\\D", "");
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}

