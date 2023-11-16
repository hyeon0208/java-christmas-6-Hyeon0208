package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("총 혜택 금액이 2만원 이상이면 산타 뱃지를 부여한다.")
    @Test
    void giveSantaBadgeTest() {
        // given
        int totalBenefitPrice = 20000;

        // when
        String eventBadge = Badge.giveEventBadge(totalBenefitPrice);

        // then
        assertThat(eventBadge).isEqualTo("산타");
    }

    @DisplayName("총 혜택 금액이 1만원 이상 2만원 미만이면 트리 뱃지를 부여한다.")
    @Test
    void giveTreeBadgeTest() {
        // given
        int totalBenefitPrice = 10000;

        // when
        String eventBadge = Badge.giveEventBadge(totalBenefitPrice);

        // then
        assertThat(eventBadge).isEqualTo("트리");
    }

    @DisplayName("총 혜택 금액이 5천원 이상 1만원 미만이면 별 뱃지를 부여한다.")
    @Test
    void giveStarBadgeTest() {
        // given
        int totalBenefitPrice = 5000;

        // when
        String eventBadge = Badge.giveEventBadge(totalBenefitPrice);

        // then
        assertThat(eventBadge).isEqualTo("별");
    }

    @DisplayName("총 혜택 금액이 5천원 미만이면 아무 뱃지도 부여하지 않는다.")
    @Test
    void giveNoneBadgeTest() {
        // given
        int totalBenefitPrice = 4999;

        // when
        String eventBadge = Badge.giveEventBadge(totalBenefitPrice);

        // then
        assertThat(eventBadge).isEqualTo("없음");
    }
}