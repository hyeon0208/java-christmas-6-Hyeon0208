package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.user.User;
import christmas.domain.user.VisitDate;
import christmas.domain.user.order.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventApplicatorTest {

    @DisplayName("30일에 방문 '바비큐립-1,아이스크림-1,제로콜라-1'를 메뉴를 시킨 유저는 2023원의 주말 혜택만 받을 수 있다.")
    @Test
    void applyEventsTest() {
        // given
        VisitDate visitDate = VisitDate.from("30");
        Order order = Order.from("바비큐립-1,아이스크림-1,제로콜라-1");
        User user = new User(visitDate, order);
        Benefit benefit = EventApplicator.applyEvents(user);

        // when
        int totalBenefitPrice = benefit.getTotalBenefitPrice();
        List<EventInfo> appliedBenefit = benefit.getAppliedBenefit();

        // then
        assertThat(totalBenefitPrice).isEqualTo(2023);
        assertThat(appliedBenefit).extracting(eventInfo -> eventInfo.name())
                .containsExactly("주말 할인");
    }

    @DisplayName("총 주문 금액이 1만원 이상이어야 혜택을 받을 수 있다.")
    @ParameterizedTest
    @CsvSource({"25, 아이스크림-1, 0, 0", "25, 아이스크림-2, 8446, 3"})
    void isMeetConditionsForEventTest(String date, String orderMenu, int expectedPrice, int size) {
        // given
        VisitDate visitDate = VisitDate.from(date);
        Order order = Order.from(orderMenu);
        User user = new User(visitDate, order);
        Benefit benefit = EventApplicator.applyEvents(user);

        // when
        int totalBenefitPrice = benefit.getTotalBenefitPrice();
        List<EventInfo> appliedBenefit = benefit.getAppliedBenefit();
        int benefitCount = appliedBenefit.size();

        // then
        assertThat(totalBenefitPrice).isEqualTo(expectedPrice);
        assertThat(benefitCount).isEqualTo(size);
    }
}