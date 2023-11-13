package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.user.User;
import christmas.domain.user.VisitDate;
import christmas.domain.user.order.Order;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("21일 방문 '바비큐립-3,아이스크림-1,제로콜라-2'를 메뉴를 시킨 회원에 대한 혜택 정보")
class BenefitTest {
    Benefit benefit;

    @BeforeEach
    void init() {
        VisitDate visitDate = VisitDate.from("21");
        Order order = Order.from("바비큐립-3,아이스크림-1,제로콜라-2");
        User user = new User(visitDate, order);
        benefit = EventApplicator.applyEvents(user);
    }

    @DisplayName("총 30023원 혜택을 받는다.")
    @Test
    void getTotalBenefitPriceTest() {
        // when
        int totalBenefitPrice = benefit.getTotalBenefitPrice();

        // then
        assertThat(totalBenefitPrice).isEqualTo(30023);
    }

    @DisplayName("크리스마스 디데이 할인,평일 할인,증정 이벤트 할인 총 3가지를 받을 수 있다.")
    @Test
    void getAppliedBenefitTest() {
        // when
        List<EventInfo> appliedBenefit = benefit.getAppliedBenefit();

        // then
        assertThat(appliedBenefit).extracting(eventInfo -> eventInfo.name())
                .containsExactly("크리스마스 디데이 할인", "평일 할인", "증정 이벤트")
                .hasSize(3);
    }

    @DisplayName("적용받을 수 있는 혜택이 있다.")
    @Test
    void isAnyAppliedBenefitTest() {
        // when
        boolean anyAppliedBenefit = benefit.isAnyAppliedBenefit();

        // then
        assertThat(anyAppliedBenefit).isTrue();
    }
}