package christmas.domain.user.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderPriceTest {

    @DisplayName("주문한 메뉴 가격과 개수를 곱한 주문 금액을 구한다.")
    @ParameterizedTest
    @CsvSource({"크리스마스파스타-2, 50000", "아이스크림-5, 25000"})
    void getOrderPriceTest(String order, int expectedPrice) {
        // given
        String[] orderInfo = order.split("-");
        OrderMenuName orderMenuName = OrderMenuName.from(orderInfo);
        Quantity quantity = Quantity.from(orderInfo);

        // when
        int orderPrice = OrderPrice.of(orderMenuName, quantity).getPrice();

        // then
        assertThat(orderPrice).isEqualTo(expectedPrice);
    }
}