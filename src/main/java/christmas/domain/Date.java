package christmas.domain;

import christmas.util.Convertor;

public class Date {
    private static final int START_DATE = 1;
    private static final int END_DATE = 31;
    private final int visitDate;

    private Date(int visitDate) {
        this.visitDate = visitDate;
    }

    public static Date from(String input) {
        int visitDate = Convertor.convertStringToInt(input);
        validateRange(visitDate);
        return new Date(visitDate);
    }

    private static void validateRange(int date) {
        if (date < START_DATE || date > END_DATE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public int getVisitDate() {
        return visitDate;
    }
}
