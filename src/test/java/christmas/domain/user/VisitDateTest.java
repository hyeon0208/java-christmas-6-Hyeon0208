package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @DisplayName("날짜는 1 ~ 31 범위의 날짜만 가질 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    void initDateTest(String date) {
        assertThatThrownBy(() -> VisitDate.from(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("12월의 주말은 false, 평일은 true를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "1, false",
            "2, false",
            "3, true",
            "8, false",
            "9, false",
            "15, false",
            "16, false",
            "22, false",
            "23, false",
            "29, false",
            "30, false",
            "31, true"
    })
    void isWeekdayTest(String date, boolean expected) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        boolean weekday = visitDate.isWeekday();

        // then
        assertThat(weekday).isEqualTo(expected);
    }

    @DisplayName("3, 10, 17, 24, 25, 31일는 특별한 날이다.")
    @ParameterizedTest
    @CsvSource({
            "3, true",
            "4, false",
            "10, true",
            "16, false",
            "17, true",
            "24, true",
            "25, true",
            "31, true",
    })
    void isSpecialDayTest(String date, boolean expected) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        boolean specialDay = visitDate.isSpecialDay();

        // then
        assertThat(specialDay).isEqualTo(expected);
    }

    @DisplayName("크리스마스 D-day는 25일까지이다.")
    @ParameterizedTest
    @CsvSource({"1, true", "25, true", "26, false"})
    void isChristmasDdayTest(String date, boolean expected) {
        // given
        VisitDate visitDate1 = VisitDate.from(date);

        // when
        boolean christmasDday = visitDate1.isChristmasDday();

        // then
        assertThat(christmasDday).isEqualTo(expected);
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