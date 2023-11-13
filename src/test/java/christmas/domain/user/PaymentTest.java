package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.EventApplicator;
import christmas.domain.user.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {

    @DisplayName("총주문 금액에서 혜택받은 가격을 제외한 실제 결제 가격을 가져온다.")
    @Test
    void getActualPaymentPriceTest() {
        // given
        VisitDate visitDate = VisitDate.from("21");
        Order order = Order.from("바비큐립-3,아이스크림-1,제로콜라-2");
        User user = new User(visitDate, order);
        Benefit benefit = EventApplicator.applyEvents(user);
        Payment payment = new Payment(user, benefit);

        // when
        int actualPaymentPrice = payment.getActualPaymentPrice();

        // then
        assertThat(actualPaymentPrice).isEqualTo(142977);
    }
}