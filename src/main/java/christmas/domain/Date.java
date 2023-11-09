package christmas.domain;

import christmas.util.Convertor;

public class Date {
    private final int visitDate;

    private Date(int visitDate) {
        this.visitDate = visitDate;
    }

    public static Date from(String input) {
        int visitDate = Convertor.convertStringToInt(input);
        return new Date(visitDate);
    }

    public int getVisitDate() {
        return visitDate;
    }
}
