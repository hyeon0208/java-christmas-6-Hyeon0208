package christmas.domain.user.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderDetailTest {

    @DisplayName("주문 상세 정보를 가져온다.")
    @ParameterizedTest
    @CsvSource({"바비큐립-2, 바비큐립, 2, 108000", "해산물파스타-5, 해산물파스타, 5, 175000"})
    void getOrderPriceTest(String orderInfo, String expectedMenuName, int expectedQuantity, int expectedPrice) {
        // given
        OrderDetail orderDetail = OrderDetail.from(orderInfo);

        // when
        String menuName = orderDetail.getMenuName();
        int quantity = orderDetail.getQuantity();
        int orderPrice = orderDetail.getOrderPrice();

        // then
        assertThat(menuName).isEqualTo(expectedMenuName);
        assertThat(quantity).isEqualTo(expectedQuantity);
        assertThat(orderPrice).isEqualTo(expectedPrice);
    }
}