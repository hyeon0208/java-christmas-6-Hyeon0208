package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.user.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @DisplayName("날짜는 1 ~ 31 범위의 날짜만 가질 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    void initDateTest(String date) {
        assertThatThrownBy(() -> VisitDate.from(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("12월의 금요일,토요일은 주말이다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "8", "9", "15", "16", "22", "23", "29", "30"})
    void isWeekdayTest(String date) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        boolean weekday = visitDate.isWeekday();

        // then
        assertThat(weekday).isFalse();
    }

    @DisplayName("3, 10, 17, 24, 25, 31일는 특별한 날이다.")
    @ParameterizedTest
    @ValueSource(strings = {"3", "10", "17", "24", "25", "31"})
    void isSpecialDayTest(String date) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        boolean specialDay = visitDate.isSpecialDay();

        // then
        assertThat(specialDay).isTrue();
    }

    @DisplayName("크리스마스 D-day는 25일까지이다.")
    @Test
    void isChristmasDdayTest() {
        // given
        VisitDate visitDate1 = VisitDate.from("25");
        VisitDate visitDate2 = VisitDate.from("26");

        // when
        boolean christmasDday1 = visitDate1.isChristmasDday();
        boolean christmasDday2 = visitDate2.isChristmasDday();

        // then
        assertThat(christmasDday1).isTrue();
        assertThat(christmasDday2).isFalse();
    }

    @DisplayName("방문 날짜를 가져온다.")
    @Test
    void getVisitDateTest() {
        // given
        VisitDate date = VisitDate.from("3");

        // when
        int visitDate = date.getVisitDate();
        int expectedVisitDate = 3;

        // then
        assertThat(visitDate).isEqualTo(expectedVisitDate);
    }
}