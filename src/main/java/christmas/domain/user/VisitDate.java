package christmas.domain.user;

import christmas.constant.ErrorMessage;
import christmas.util.StringConvertor;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitDate {
    private static final int START_DATE = 1;
    private static final int END_DATE = 31;
    private static final int CHRISTMAS_D_DAY = 25;
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private final LocalDate date;

    private VisitDate(LocalDate date) {
        this.date = date;
    }

    public static VisitDate from(String input) {
        int day = StringConvertor.convertStringToInt(input);
        validateRange(day);
        LocalDate visitDate = LocalDate.of(2023, 12, day);
        return new VisitDate(visitDate);
    }

    private static void validateRange(int date) {
        if (date < START_DATE || date > END_DATE) {
            throw new IllegalArgumentException(ErrorMessage.VISIT_DATE_ERROR);
        }
    }

    public boolean isWeekday() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAYS.contains(date.getDayOfMonth());
    }

    public boolean isChristmasDday() {
        if (date.getDayOfMonth() <= CHRISTMAS_D_DAY) {
            return true;
        }
        return false;
    }

    public int getVisitDate() {
        return date.getDayOfMonth();
    }
}
