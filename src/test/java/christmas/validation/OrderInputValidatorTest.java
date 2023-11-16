package christmas.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.validation.OrderInputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderInputValidatorTest {

    @DisplayName("빈 값을 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void validateEmptyTest(String input) {
        assertThatThrownBy(() -> OrderInputValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴들을 쉼표로 구분하지 않으면 예외가 발생한다.")
    @Test
    void validateCommaTest() {
        // given
        String orderMenus = "티본스테이크-1 바비큐립-1 아이스크림-2";

        // then
        assertThatThrownBy(() -> OrderInputValidator.validate(orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("알맞은 주문 형식을 입력하지 않으면 예외가 발생한다.")
    @Test
    void validateOrderMenuFormatTest() {
        // given
        String orderMenus = "티본스테이크1,아이스크림2";

        // then
        assertThatThrownBy(() -> OrderInputValidator.validate(orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴의 개수에 숫자가 아닌 값을 입력하면 예외가 발생한다.")
    @Test
    void validateOrderCountIsNumberTest() {
        // given
        String orderMenus = "티본스테이크-1개,아이스크림-2개";

        // then
        assertThatThrownBy(() -> OrderInputValidator.validate(orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복되는 메뉴를 주문하면 예외가 발생한다.")
    @Test
    void validateDuplicateOrderTest() {
        // given
        String orderMenu = "레드와인-1,레드와인-2";

        // then
        assertThatThrownBy(() -> OrderInputValidator.validate(orderMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }
}