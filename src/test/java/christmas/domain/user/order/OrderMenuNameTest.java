package christmas.domain.user.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuNameTest {

    @DisplayName("메뉴판에 존재하지 않는 메뉴를 주문하면 예외가 발생한다.")
    @Test
    void validateExistMenuTest() {
        // given
        String[] orderMenu = new String[]{"없는 메뉴", "1"};

        // then
        assertThatThrownBy(() -> OrderMenuName.from(orderMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }
}