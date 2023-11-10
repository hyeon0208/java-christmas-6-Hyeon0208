package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateTest {

    @DisplayName("날짜는 1 ~ 31 범위의 날짜만 가질 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    void initDateTest(String date) {
        assertThatThrownBy(() -> Date.from(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("12월의 금요일,토요일은 주말이다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "8", "9", "15", "16", "22", "23", "29", "30"})
    void isWeekdayTest(String date) {
        // given
        Date visitDate = Date.from(date);

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
        Date visitDate = Date.from(date);

        // when
        boolean specialDay = visitDate.isSpecialDay();

        // then
        assertThat(specialDay).isTrue();
    }

    @DisplayName("방문 날짜를 가져온다.")
    @Test
    void getVisitDateTest() {
        // given
        Date date = Date.from("3");

        // when
        int visitDate = date.getVisitDate();
        int expectedVisitDate = 3;

        // then
        assertThat(visitDate).isEqualTo(expectedVisitDate);
    }
}