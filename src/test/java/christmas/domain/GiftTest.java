package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.Gift;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GiftTest {

    @DisplayName("총 주문 금액이 12만원 이상이면 증점품을 지급할 수 있다.")
    @ParameterizedTest
    @CsvSource({"120000, true", "119999, false"})
    void givableTest(int totalOrderPrice, boolean expected) {
        // when
        boolean givable = Gift.givable(totalOrderPrice);

        // then
        assertThat(givable).isEqualTo(expected);
    }

    @DisplayName("총 주문 금액이 12만원 이상이면 샴페인 1개를 지급한다.")
    @Test
    void giveChampagneOneTest() {
        // given
        int totalOrderPrice = 120000;

        // when
        Gift gift = Gift.findByTotalOrderPrice(totalOrderPrice).get();
        String product = gift.getProduct();
        int quantity = gift.getQuantity();

        // then
        assertThat(product).isEqualTo("샴페인");
        assertThat(quantity).isEqualTo(1);
    }
}