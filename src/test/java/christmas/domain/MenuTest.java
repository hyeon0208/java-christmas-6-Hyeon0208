package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("메뉴판에 존재 여부를 확인하고 있으면 true 없으면 false를 반환한다.")
    @Test
    void containsTest() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "치킨";

        // when
        boolean containsMenuName1 = Menu.contains(menuName1);
        boolean containsMenuName2 = Menu.contains(menuName2);

        // then
        assertThat(containsMenuName1).isTrue();
        assertThat(containsMenuName2).isFalse();
    }

    @DisplayName("메뉴의 가격을 가져온다.")
    @Test
    void getPriceOfTest() {
        // given
        String menuName1 = "티본스테이크";
        String menuName2 = "제로콜라";

        // when
        int priceOfMenuName1 = Menu.getPriceOf(menuName1);
        int priceOfMenuName2 = Menu.getPriceOf(menuName2);

        // then
        assertThat(priceOfMenuName1).isEqualTo(55000);
        assertThat(priceOfMenuName2).isEqualTo(3000);
    }

    @DisplayName("해당 메뉴가 DRINK 카테고리의 메뉴인지 확인한다.")
    @Test
    void isDrinkMenuTest() {
        // given
        String menuName1 = "레드와인";
        String menuName2 = "아이스크림";

        // when
        boolean isDrinkMenu1 = Menu.isDrinkMenu(menuName1);
        boolean isDrinkMenu2 = Menu.isDrinkMenu(menuName2);

        // then
        assertThat(isDrinkMenu1).isTrue();
        assertThat(isDrinkMenu2).isFalse();
    }

    @DisplayName("해당 메뉴가 Dessert 카테고리의 메뉴인지 확인한다.")
    @Test
    void isDessertTest() {
        // given
        String menuName1 = "레드와인";
        String menuName2 = "아이스크림";

        // when
        boolean isDessertMenu1 = Menu.isDessert(menuName1);
        boolean isDessertMenu2 = Menu.isDessert(menuName2);

        // then
        assertThat(isDessertMenu1).isFalse();
        assertThat(isDessertMenu2).isTrue();
    }

    @DisplayName("해당 메뉴가 Main 카테고리의 메뉴인지 확인한다.")
    @Test
    void isMainTest() {
        // given
        String menuName1 = "바비큐립";
        String menuName2 = "타파스";

        // when
        boolean isMainMenu1 = Menu.isMain(menuName1);
        boolean isMainMenu2 = Menu.isMain(menuName2);

        // then
        assertThat(isMainMenu1).isTrue();
        assertThat(isMainMenu2).isFalse();
    }
}