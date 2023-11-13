package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.user.order.OrderDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderDetailTest {

    @DisplayName("메뉴판에 존재하지 않는 메뉴를 주문하면 예외가 발생한다.")
    @Test
    void validateExistMenuTest() {
        // given
        String orderMenu = "없는메뉴-1";

        // then
        assertThatThrownBy(() -> OrderDetail.of(orderMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 주문 개수가 1개보다 작으면 예외가 발생한다.")
    @Test
    void validateOrderQuantityTest() {
        // given
        String orderMenu = "티본스테이크-0";

        // then
        assertThatThrownBy(() -> OrderDetail.of(orderMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴 가격과 개수를 곱한 주문 금액을 구한다.")
    @Test
    void getOrderPriceTest() {
        // given
        String orderMenu = "크리스마스파스타-2";

        // when
        OrderDetail orderDetail = OrderDetail.of(orderMenu);
        int orderPrice = orderDetail.getOrderPrice();
        int expectedOrderPrice = 50000;

        // then
        assertThat(orderPrice).isEqualTo(expectedOrderPrice);
    }
}