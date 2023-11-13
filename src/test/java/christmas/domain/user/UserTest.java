package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.user.order.Order;
import christmas.domain.user.order.OrderDetail;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserTest {
    User user;

    @BeforeEach
    void init() {
        VisitDate visitDate = VisitDate.from("25");
        Order order = Order.from("바비큐립-2,아이스크림-1,제로콜라-2");
        user = new User(visitDate, order);
    }

    @DisplayName("회원의 총 주문 금액을 가져온다.")
    @Test
    void getTotalOrderPriceTest() {
        // when
        int totalOrderPrice = user.getTotalOrderPrice();

        // then
        assertThat(totalOrderPrice).isEqualTo(119000);
    }

    @DisplayName("주문에 대한 주문상세 정보들을 가져온다.")
    @Test
    void getOrderDetailsTest() {
        // when
        List<OrderDetail> orderDetails = user.getOrderDetails();

        // then
        assertThat(orderDetails)
                .extracting(orderDetail -> orderDetail.getMenuName())
                .containsExactly("바비큐립", "아이스크림", "제로콜라");

        assertThat(orderDetails)
                .extracting(orderDetail -> orderDetail.getQuantity())
                .containsExactly(2, 1, 2);

        assertThat(orderDetails)
                .extracting(orderDetail -> orderDetail.getOrderPrice())
                .containsExactly(108000, 5000, 6000);
    }

    @DisplayName("회원의 총주문가격이 인자로 받은 가격보다 크거나 같으면 true, 작으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"120000, false", "119000, true"})
    void isTotalOrderPriceGreaterOrEqualTest(int totalOrderPrice, boolean expected) {
        // when
        boolean orderedGe = user.isTotalOrderPriceGreaterOrEqual(totalOrderPrice);

        // then
        assertThat(orderedGe).isEqualTo(expected);
    }

    @DisplayName("방문날짜인 25이 평일이면 true, 주말이면 false를 반환한다.")
    @Test
    void isVisitWeekdayTest() {
        // when
        boolean visitWeekday = user.isVisitWeekday();
        boolean visitWeekend = !user.isVisitWeekday();

        // then
        assertThat(visitWeekday).isTrue();
        assertThat(visitWeekend).isFalse();
    }

    @DisplayName("방문날짜인 25이 별표가 붙은 특별한 날이면 true, 아니면 false를 반환한다.")
    @Test
    void isVisitSpecialDayTest() {
        // when
        boolean visitSpecialDay = user.isVisitSpecialDay();

        // then
        assertThat(visitSpecialDay).isTrue();
    }

    @DisplayName("방문날짜인 25이 크리스마스 D-day이벤트 기간 안이면 true, 아니면 false를 반환한다.")
    @Test
    void isVisitChristmasDdayTest() {
        // when
        boolean visitChristmasDday = user.isVisitChristmasDday();

        // then
        assertThat(visitChristmasDday).isTrue();
    }
}