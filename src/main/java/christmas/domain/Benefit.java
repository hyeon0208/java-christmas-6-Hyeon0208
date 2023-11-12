package christmas.domain;

import java.util.List;

public class Benefit {
    private final List<EventInfo> events;

    public Benefit(List<EventInfo> events) {
        this.events = events;
    }

    public int getTotalBenefitPrice() {
        return events.stream()
                .mapToInt(EventInfo::discountPrice)
                .sum();
    }

    public List<EventInfo> getAppliedBenefit() {
        return events;
    }
}
