package christmas.validation;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateInputValidatorTest {

    @DisplayName("방문 날짜에 숫자가 아닌 값을 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "!", "ㅎ"})
    void validateIsNumberTest(String input) {
        assertThatThrownBy(() -> VisitDateInputValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문 날짜에 빈 값을 입력할 시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void validateEmpty(String input) {
        assertThatThrownBy(() -> VisitDateInputValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}