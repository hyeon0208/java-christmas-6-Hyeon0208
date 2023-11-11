package christmas.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringConvertorTest {

    @DisplayName("문자열을 정수로 변환한다.")
    @Test
    void convertStringToInt() {
        // given
        String price = "03";

        // when
        int convertedPrice = StringConvertor.convertStringToInt(price);

        // then
        assertThat(convertedPrice).isEqualTo(3);
    }

    @DisplayName("쉼표로 구분된 문자열을 배열로 반환한다.")
    @Test
    void splitByComma() {
        // given
        String order = "타파스-1,레드와인-2,아이스크림-1";

        // when
        String[] menus = StringConvertor.splitByComma(order);

        // then
        assertThat(menus).containsExactly("타파스-1", "레드와인-2", "아이스크림-1");
    }

    @DisplayName("하이픈으로 구분된 문자를 바열로 반환한다.")
    @Test
    void splitByHyphen() {
        // given
        String menu = "바비큐립-2";

        // when
        String[] menuInfo = StringConvertor.splitByHyphen(menu);

        // then
        assertThat(menuInfo).containsExactly("바비큐립", "2");
    }
}