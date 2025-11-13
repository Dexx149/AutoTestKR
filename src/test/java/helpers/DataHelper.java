package helpers;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo("1111 2222 3333 4444", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("5555 6666 7777 8888", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getEmptyCardNum() {
        return new CardInfo("", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getShortCardNum() {
        return new CardInfo("1111 2222 3333 444", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getInvalidCardNum() {
        return new CardInfo("0000 0000 0000 0000", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getEmptyMonth() {
        return new CardInfo("1111 2222 3333 4444", "", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getInvalidMonth() {
        return new CardInfo("1111 2222 3333 4444", "00", "26", "IVAN IVANOV", "123");
    }

    public static CardInfo getExpiredMonth() {
        return new CardInfo("1111 2222 3333 4444", "05", "24", "IVAN IVANOV", "123");
    }

    public static CardInfo getEmptyYear() {
        return new CardInfo("1111 2222 3333 4444", "12", "", "IVAN IVANOV", "123");
    }

    public static CardInfo getExpiredYear() {
        return new CardInfo("1111 2222 3333 4444", "12", "22", "IVAN IVANOV", "123");
    }

    public static CardInfo getEmptyHolder() {
        return new CardInfo("1111 2222 3333 4444", "12", "25", "", "123");
    }

    public static CardInfo getCyrillicHolder() {
        return new CardInfo("1111 2222 3333 4444", "12", "25", "ИВАН ИВАНОВ", "123");
    }

    public static CardInfo getNumericHolder() {
        return new CardInfo("1111 2222 3333 4444", "12", "25", "IVAN123", "123");
    }

    public static CardInfo getEmptyCvc() {
        return new CardInfo("1111 2222 3333 4444", "12", "25", "IVAN IVANOV", "");
    }

    public static CardInfo getShortCvc() {
        return new CardInfo("1111 2222 3333 4444", "12", "25", "IVAN IVANOV", "12");
    }
}
