package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.dto.MenuInfo;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

class OrderTest {

    @DisplayName("메뉴판에 존재하지 않는 메뉴를 주문하면 예외가 발생한다.")
    @Test
    void validateExistMenuTest() {
        // given
        String orderMenu = "없는메뉴-1";

        // then
        assertThatThrownBy(() -> Order.from(orderMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 개수가 1개보다 작거나 20개보다 클 경우 예외가 발생한다.")
    @Test
    void validateOrderCountTest() {
        // given
        String orderMenus = "티본스테이크-1,바비큐립-1,아이스크림-2";
        StringBuilder orderMenusBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            orderMenusBuilder.append(",").append(orderMenus);
        }

        // when
        String overOrderedMenus = orderMenusBuilder.toString();

        // then
        assertThatThrownBy(() -> Order.from(overOrderedMenus))
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

    @DisplayName("주문한 메뉴를 정확히 일치하여 가져오는지 테스트")
    @Test
    void getMenuInfos() {
        // given
        String orderMenu = "해산물파스타-2,레드와인-1,초코케이크-1";
        Order order = Order.from(orderMenu);

        // when
        List<MenuInfo> menuInfos = order.getMenuInfos();

        // then
        assertThat(menuInfos).extracting(MenuInfo::name)
                .containsExactly("해산물파스타", "레드와인", "초코케이크");

        assertThat(menuInfos).extracting(MenuInfo::price)
                .containsExactly(2, 1, 1);
    }
}