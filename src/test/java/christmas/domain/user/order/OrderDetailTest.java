package christmas.domain.user.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderDetailTest {

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