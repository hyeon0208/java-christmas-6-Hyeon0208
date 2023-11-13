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
        return events.stream()
                .filter(eventInfo -> eventInfo.discountPrice() > 0)
                .toList();
    }

    public boolean isAnyAppliedBenefit() {
        return events.stream()
                .anyMatch(eventInfo -> eventInfo.discountPrice() > 0);
    }
}
