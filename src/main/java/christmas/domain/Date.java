package christmas.domain;

import christmas.util.Convertor;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {
    private static final int START_DATE = 1;
    private static final int END_DATE = 31;
    private final LocalDate date;

    private Date(LocalDate date) {
        this.date = date;
    }
    public static Date from(String input) {
        int day = Convertor.convertStringToInt(input);
        validateRange(day);
        LocalDate visitDate = LocalDate.of(2023, 12, day);
        return new Date(visitDate);
    }

    private static void validateRange(int date) {
        if (date < START_DATE || date > END_DATE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public boolean isWeekday() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    public int getVisitDate() {
        return date.getDayOfMonth();
    }
}
