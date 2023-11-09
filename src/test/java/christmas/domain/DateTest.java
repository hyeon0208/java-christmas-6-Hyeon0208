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