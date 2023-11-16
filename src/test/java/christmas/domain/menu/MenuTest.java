package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTest {

    @DisplayName("메뉴판에 존재 여부를 확인하고 있으면 true 없으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"양송이수프, true", "치킨, false"})
    void containsTest(String menuName, boolean expected) {
        // when
        boolean containsMenuName = Menu.contains(menuName);

        // then
        assertThat(containsMenuName).isEqualTo(expected);
    }

    @DisplayName("메뉴의 가격을 가져온다.")
    @ParameterizedTest
    @CsvSource({"티본스테이크, 55000", "제로콜라, 3000"})
    void getPriceOfTest(String menuName, int expectedPrice) {
        // when
        int priceOfMenuName = Menu.getPriceOf(menuName);

        // then
        assertThat(priceOfMenuName).isEqualTo(expectedPrice);
    }

    @DisplayName("해당 메뉴가 DRINK 카테고리의 메뉴인지 확인한다.")
    @ParameterizedTest
    @CsvSource({"레드와인, true", "아이스크림, false"})
    void isDrinkMenuTest(String menuName, boolean expected) {
        // when
        boolean isDrinkMenu = Menu.isDrinkMenu(menuName);

        // then
        assertThat(isDrinkMenu).isEqualTo(expected);
    }

    @DisplayName("해당 메뉴가 Dessert 카테고리의 메뉴인지 확인한다.")
    @ParameterizedTest
    @CsvSource({"레드와인, false", "아이스크림, true"})
    void isDessertMenuTest(String menuName, boolean expected) {
        // when
        boolean isDessertMenu = Menu.isDessert(menuName);

        // then
        assertThat(isDessertMenu).isEqualTo(expected);
    }

    @DisplayName("해당 메뉴가 Main 카테고리의 메뉴인지 확인한다.")
    @ParameterizedTest
    @CsvSource({"바비큐립, true", "타파스, false"})
    void isMainMenuTest(String menuName, boolean expected) {
        // when
        boolean isMainMenu = Menu.isMain(menuName);

        // then
        assertThat(isMainMenu).isEqualTo(expected);
    }
}