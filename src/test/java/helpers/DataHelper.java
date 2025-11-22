package helpers;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.util.Locale;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {}

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static String getApprovedCardNumber() {
        return "1111 2222 3333 4444";
    }

    public static String getDeclinedCardNumber() {
        return "5555 6666 7777 8888";
    }

    public static String generateValidMonth() {
        return String.format("%02d", faker.number().numberBetween(1, 12));
    }

    public static String generateValidYear() {
        return String.format("%02d", LocalDate.now().getYear() % 100 + faker.number().numberBetween(1, 3));
    }

    public static String generateHolder() {
        return faker.name().fullName().toUpperCase();
    }

    public static String generateCvc() {
        return String.format("%03d", faker.number().numberBetween(100, 999));
    }

    // Генерация невалидных данных
    public static String generateExpiredMonth() {
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        return String.format("%02d", lastMonth.getMonthValue());
    }

    public static String generateExpiredYear() {
        LocalDate lastYear = LocalDate.now().minusYears(1);
        return String.format("%02d", lastYear.getYear() % 100);
    }

    public static String generateInvalidMonth() {
        return "00";
    }

    public static String generateCyrillicHolder() {
        Faker russianFaker = new Faker(new Locale("ru"));
        return russianFaker.name().fullName();
    }

    public static String generateNumericHolder() {
        return faker.numerify("##########");
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo(
                getApprovedCardNumber(),
                generateValidMonth(),
                generateValidYear(),
                generateHolder(),
                generateCvc()
        );
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(
                getDeclinedCardNumber(),
                generateValidMonth(),
                generateValidYear(),
                generateHolder(),
                generateCvc()
        );
    }

    public static CardInfo getEmptyCardNum() {
        return new CardInfo("", generateValidMonth(), generateValidYear(), generateHolder(), generateCvc());
    }

    public static CardInfo getShortCardNum() {
        return new CardInfo("1111 2222 3333 444", generateValidMonth(), generateValidYear(), generateHolder(), generateCvc());
    }

    public static CardInfo getInvalidCardNum() {
        return new CardInfo("0000 0000 0000 0000", generateValidMonth(), generateValidYear(), generateHolder(), generateCvc());
    }

    public static CardInfo getEmptyMonth() {
        return new CardInfo(getApprovedCardNumber(), "", generateValidYear(), generateHolder(), generateCvc());
    }

    public static CardInfo getInvalidMonth() {
        return new CardInfo(getApprovedCardNumber(), generateInvalidMonth(), generateValidYear(), generateHolder(), generateCvc());
    }

    public static CardInfo getExpiredMonth() {
        return new CardInfo(getApprovedCardNumber(), generateExpiredMonth(), generateValidYear(), generateHolder(), generateCvc());
    }

    public static CardInfo getEmptyYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), "", generateHolder(), generateCvc());
    }

    public static CardInfo getExpiredYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateExpiredYear(), generateHolder(), generateCvc());
    }

    public static CardInfo getEmptyHolder() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), "", generateCvc());
    }

    public static CardInfo getCyrillicHolder() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateCyrillicHolder(), generateCvc());
    }

    public static CardInfo getNumericHolder() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateNumericHolder(), generateCvc());
    }

    public static CardInfo getEmptyCvc() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateHolder(), "");
    }

    public static CardInfo getShortCvc() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateHolder(), "12");
    }
}