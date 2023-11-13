package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.Event;
import christmas.domain.user.order.Order;
import christmas.domain.user.User;
import christmas.domain.user.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    User user;

    @BeforeEach
    void init() {
        VisitDate visitDate = VisitDate.from("23");
        Order order = Order.from("크리스마스파스타-2,초코케이크-1,레드와인-2");
        user = new User(visitDate, order);
    }

    @DisplayName("크리스마스 디데이 이벤트 적용이 가능한지 확인한다.")
    @Test
    void isChristmasDdayEventApplicableTest() {
        // when
        boolean christmasDdayEventApplicable = Event.CHRISTMAS_D_DAY_EVENT.isApplicable(user);
        int discountPrice = Event.CHRISTMAS_D_DAY_EVENT.calculateDiscount(user);

        // then
        assertThat(christmasDdayEventApplicable).isTrue();
        assertThat(discountPrice).isEqualTo(3200);
    }

    @DisplayName("평일 할인 이벤트 적용이 가능한지 확인한다.")
    @Test
    void isWeekDayEventApplicableTest() {
        // when
        boolean weekendEventApplicable = Event.WEEKDAY_EVENT.isApplicable(user);
        int discountPrice = Event.WEEKDAY_EVENT.calculateDiscount(user);

        // then
        assertThat(weekendEventApplicable).isFalse();
        assertThat(discountPrice).isEqualTo(2023);
    }

    @DisplayName("주말 할인 이벤트 적용이 가능한지 확인한다.")
    @Test
    void isWeekendEventApplicableTest() {
        // when
        boolean weekendEventApplicable = Event.WEEKEND_EVENT.isApplicable(user);
        int discountPrice = Event.WEEKEND_EVENT.calculateDiscount(user);

        // then
        assertThat(weekendEventApplicable).isTrue();
        assertThat(discountPrice).isEqualTo(4046); // 메인 디쉬의 개수가 2개이므로 2023 * 2
    }

    @DisplayName("특별 할인 이벤트 적용이 가능한지 확인한다.")
    @Test
    void isSpecialDayEventApplicableTest() {
        // when
        boolean specialDayEventApplicable = Event.SPECIAL_DAY_EVENT.isApplicable(user);
        int discountPrice = Event.SPECIAL_DAY_EVENT.calculateDiscount(user);

        // then
        assertThat(specialDayEventApplicable).isFalse();
        assertThat(discountPrice).isEqualTo(1000);
    }

    @DisplayName("증정 이벤트 적용이 가능한지 확인한다.")
    @Test
    void isGiftEventApplicableTest() {
        // when
        boolean giftEventApplicable = Event.GIFT_EVENT.isApplicable(user);
        int discountPrice = Event.GIFT_EVENT.calculateDiscount(user);

        // then
        assertThat(giftEventApplicable).isTrue();
        assertThat(discountPrice).isEqualTo(25000);
    }
}