package christmas.domain.user.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuantityTest {

    @DisplayName("메뉴의 주문 개수가 1개보다 작으면 예외가 발생한다.")
    @Test
    void validateOrderQuantityTest() {
        // given
        String[] orderMenu = new String[]{"티본스테이크", "0"};

        // then
        assertThatThrownBy(() -> Quantity.from(orderMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }
}