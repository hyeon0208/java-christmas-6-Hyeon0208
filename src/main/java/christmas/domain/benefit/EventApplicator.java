package christmas.domain.benefit;

import christmas.domain.user.User;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class EventApplicator {
    private static final int MIN_EVENT_APPLICATION_CONDITIONS = 10000;

    private EventApplicator() {
    }

    public static Benefit applyEvents(User user) {
        List<EventInfo> discounts = EnumSet.allOf(Event.class).stream()
                .filter(event -> isMeetConditionsForEvent(user) && event.isApplicable(user))
                .map(event -> createEventInfo(user, event))
                .collect(Collectors.toList());
        return new Benefit(discounts);
    }

    private static boolean isMeetConditionsForEvent(User user) {
        if (user.isTotalOrderPriceGreaterOrEqual(MIN_EVENT_APPLICATION_CONDITIONS)) {
            return true;
        }
        return false;
    }

    private static EventInfo createEventInfo(User user, Event event) {
        int discount = event.calculateDiscount(user);
        return new EventInfo(event.getName(), discount);
    }
}
