package solidbeans.com.handla.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

@SuppressWarnings({"WeakerAccess", "unused"})
public class RandomStuff {
    public static Random random = new Random();

    public static final String[] firstNames = new String[]{
            "Per", "Johan", "Erik", "Anna", "Lisa", "Maria", "Gustav",
            "Karl", "Magnus", "Maja", "Oscar", "Alice", "William", "Julia",
            "Lucas", "Ester", "Elias", "Wilma", "Alexander", "Ella", "Hugo",
            "Elsa", "Oliver", "Emma", "Theo", "Alva", "Liam", "Olivia", "Leo"
    };

    public static final String[] lastNames = new String[]{
            "Johansson", "Andersson", "Karlsson", "Nilsson", "Eriksson", "Larsson",
            "Olsson", "Persson", "Svensson", "Gustafsson", "Pettersson", "Jonsson",
            "Jansson", "Hansson", "Bengtsson", "Jönsson", "Lindberg", "Jakobsson",
            "Magnusson", "Olofsson", "Lindström", "Lindgren", "Lindqvist", "Axelsson",
            "Lundberg", "Berg", "Bergström", "Lundgren", "Mattsson", "Lundqvist", "Lind",
            "Berglund", "Fredriksson", "Henriksson"
    };


    public static final String[] cityNames = new String[]{
            "Stockholm", "Göteborg", "Malmö", "Uppsala", "Västerås", "Örebro",
            "Linköping", "Helsingborg", "Jönköping", "Norrköping", "Lund", "Umeå",
            "Gävle", "Borås", "Eskilstuna", "Södertälje", "Karlstad", "Täby",
            "Växjö", "Halmstad"
    };

    public static final String[] LOWER_CASE_LETTERS = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z"};

    public static final String[] UPPER_CASE_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static final String[] DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static final String[] addressBeginnigs = new String[]{
            "Kungs", "Drottning", "Stor", "Vasa", "Sture", "Narva", "Strand", "Hamn", "Kyrko"};

    public static final String[] streetSynonyms = new String[]{
            "gatan", "vägen", "torget", "avenyn", "gränd", "plan", "esplanaden"};

    public static String firstName() {
        return randomPick(firstNames);
    }

    public static String lastName() {
        return randomPick(lastNames);
    }

    public static String firstAndLastName() {
        return firstName() + " " + lastName();
    }

    public static String cityName() {
        return randomPick(cityNames);
    }

    public static Date dateAtMostOneYearBack() {
        Calendar randomDate = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        int year = randomDate.get(YEAR);
        randomDate.set(YEAR, year);
        randomDate.set(MONTH, random.nextInt(12));
        randomDate.set(DAY_OF_MONTH, random.nextInt(28) + 1);
        if (randomDate.before(now)) {
            return randomDate.getTime();
        }
        randomDate.set(YEAR, year - 1);
        return randomDate.getTime();
    }

    @SuppressLint("SimpleDateFormat")
    public static String isoFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String personNumber() {
        StringBuilder orgNo = new StringBuilder(11);
        orgNo.append(randomDigits(2));
        orgNo.append(random.nextInt(12) + 1);
        orgNo.append(random.nextInt(27) + 1);
        orgNo.append('-');
        orgNo.append(randomDigits(3));
        orgNo.append(new LuhnChecker().getCheckDigit(orgNo.toString()));
        return orgNo.toString();
    }

    public static String orgNumber() {
        StringBuilder orgNo = new StringBuilder(11);
        orgNo.append("55");
        orgNo.append(randomDigits(4));
        orgNo.append('-');
        orgNo.append(randomDigits(3));
        orgNo.append(new LuhnChecker().getCheckDigit(orgNo.toString()));
        return orgNo.toString();
    }

    public static String address() {
        return randomPick(addressBeginnigs) + randomPick(streetSynonyms) + " " + randomDigits(2);
    }

    public static String zipCode() {
        return randomDigits(3) + " " + randomDigits(2);
    }

    public static String telephoneNumber() {
        return "0" + randomDigits(random.nextInt(3) + 1) + "-" + randomDigits(random.nextInt(3) + 5);
    }

    public static String mobileNumber() {
        return "07" + randomDigits(2) + "-" + randomDigits(2) + " " + randomDigits(2) + " " + randomDigits(2);
    }

    /**
     * @return a pseudo ip-address that on the format:
     * 0-255.0-255.0-255.0-255
     */
    public static String ipAddress() {
        StringBuilder reply = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (i != 0) {
                reply.append('.');
            }
            reply.append(random.nextInt(256));
        }
        return reply.toString();
    }

    public static <T> T randomPick(T[] array) {
        return array[random.nextInt(array.length)];
    }

    public static <T> T chooseBetweenTwo(T[] two, int percentForFirst) {
        return truePercentOfTheTimes(percentForFirst) ? two[0] : two[1];
    }

    public static <T> T chooseBetweenTwo(List<T> two, int percentForFirst) {
        return truePercentOfTheTimes(percentForFirst) ? two.get(0) : two.get(1);
    }

    public static boolean truePercentOfTheTimes(int percent) {
        int betweenOneAndOneHundred = random.nextInt(100) + 1;
        return betweenOneAndOneHundred < percent;
    }

    public static <T> T randomPick(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    public static <T> List<T> randomPick(List<T> list, int howMany) {
        if (howMany < 1 || list == null) return Collections.emptyList();
        if (list.size() <= howMany) return list;

        Set<T> randomList = new HashSet<>(howMany);
        while (randomList.size() < howMany) {
            randomList.add(list.get(random.nextInt(list.size())));
        }
        return new ArrayList<>(randomList);
    }

    public static String randomDigits(int size) {
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < size; i++) {
            digits.append(random.nextInt(10));
        }
        return digits.toString();
    }


    public static String randomLowercaseLetters(int size) {
        StringBuilder letters = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            letters.append(randomLowercaseLetter());
        }
        return letters.toString();
    }

    public static String randomAlphaNumeric(int size) {
        String[] alphaNumeric = new String[LOWER_CASE_LETTERS.length + UPPER_CASE_LETTERS.length + DIGITS.length];
        System.arraycopy(LOWER_CASE_LETTERS, 0, alphaNumeric, 0, LOWER_CASE_LETTERS.length);
        System.arraycopy(UPPER_CASE_LETTERS, 0, alphaNumeric, LOWER_CASE_LETTERS.length, UPPER_CASE_LETTERS.length);
        System.arraycopy(DIGITS, 0, alphaNumeric, LOWER_CASE_LETTERS.length + UPPER_CASE_LETTERS.length, DIGITS.length);

        StringBuilder result = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            result.append(randomPick(alphaNumeric));
        }
        return result.toString();
    }

    /**
     * @return one letter [a-z]
     */
    public static String randomLowercaseLetter() {
        return randomPick(LOWER_CASE_LETTERS);
    }

    /**
     * Generates a string with characters between
     * !(33) and ~(126) in the ascii-table.
     */
    public static String randomCharacters(int length) {
        StringBuilder reply = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
//            int between32And126 = random.nextInt(93) + 33;
//            char c = (char)between32And126;
            reply.append(randomLowercaseLetter());
        }
        return reply.toString();
    }

    public static long days(int noOfDays) {
        return hours(24) * noOfDays;
    }

    public static long hours(int noOfHours) {
        return minutes(60) * noOfHours;
    }

    public static long minutes(int noOfMinutes) {
        return seconds(60) * noOfMinutes;
    }

    public static long seconds(int noOfSeconds) {
        return 1000 * noOfSeconds;
    }
}

