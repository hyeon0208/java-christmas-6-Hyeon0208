package christmas.domain.user.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.user.order.Order;
import christmas.domain.user.order.OrderDetail;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("총 주문 개수가 1개보다 작거나 20개보다 클 경우 예외가 발생한다.")
    @Test
    void validateOrderCountTest() {
        // given
        String orderMenus = "티본스테이크-5,바비큐립-10,아이스크림-6";

        // then
        assertThatThrownBy(() -> Order.from(orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료 1개만 주문할 경우 예외가 발생한다.")
    @Test
    void validateOnlyOrderDrinkTest() {
        // given
        String orderMenu = "레드와인-1";

        // then
        assertThatThrownBy(() -> Order.from(orderMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴들을 정확히 일치하여 가져오는지 테스트")
    @Test
    void getMenuInfosTest() {
        // given
        String orderMenu = "해산물파스타-2,레드와인-1,초코케이크-1";
        Order order = Order.from(orderMenu);

        // when
        List<OrderDetail> menuInfos = order.getOrderDetails();

        // then
        assertThat(menuInfos).extracting(OrderDetail::getMenuName)
                .containsExactly("해산물파스타", "레드와인", "초코케이크");

        assertThat(menuInfos).extracting(OrderDetail::getQuantity)
                .containsExactly(2, 1, 1);
    }

    @DisplayName("주문한 메뉴들의 총 가격을 구한다.")
    @Test
    void getTotalOrderPriceTest() {
        // given
        String orderMenu = "크리스마스파스타-1,바비큐립-1,제로콜라-2,아이스크림-1";
        Order order = Order.from(orderMenu);

        // when
        int totalOrderPrice = order.getTotalOrderPrice();
        int expectedTotalOrderPrice = 90000;

        // then
        assertThat(totalOrderPrice).isEqualTo(expectedTotalOrderPrice);
    }
}