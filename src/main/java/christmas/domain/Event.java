package christmas.domain;

public enum Event {
    CHRISTMAS_D_DAY_EVENT("크리스마스 디데이 할인", 1000) {
        @Override
        public boolean isApplicable(User user) {
            return user.isVisitChristmasDday();
        }

        @Override
        public int calculateDiscount(User user) {
            return CHRISTMAS_D_DAY_EVENT.discountPrice + (user.getVisitDate() - 1) * 100;
        }
    },
    WEEKDAY_EVENT("평일 할인", 2023) {
        @Override
        public boolean isApplicable(User user) {
            return user.isVisitWeekday();
        }

        @Override
        public int calculateDiscount(User user) {
            int dessertOrderCount = user.getOrderDetails().stream()
                    .filter(orderDetail -> Menu.isDessert(orderDetail.getMenuName()))
                    .mapToInt(OrderDetail::getQuantity)
                    .sum();
            return WEEKDAY_EVENT.discountPrice * dessertOrderCount;
        }
    },
    WEEKEND_EVENT("주말 할인", 2023) {
        @Override
        public boolean isApplicable(User user) {
            return !user.isVisitWeekday();
        }

        @Override
        public int calculateDiscount(User user) {
            int mainOrderCount = user.getOrderDetails().stream()
                    .filter(orderDetail -> Menu.isMain(orderDetail.getMenuName()))
                    .mapToInt(OrderDetail::getQuantity)
                    .sum();
            return WEEKEND_EVENT.discountPrice * mainOrderCount;
        }
    },
    SPECIAL_DAY_EVENT("특별 할인", 1000) {
        @Override
        public boolean isApplicable(User user) {
            return user.isVisitSpecialDay();
        }

        @Override
        public int calculateDiscount(User user) {
            return SPECIAL_DAY_EVENT.discountPrice;
        }
    },
    GIFT_EVENT("증정 이벤트", 25000) {
        @Override
        public boolean isApplicable(User user) {
            return Gift.givable(user.getTotalOrderPrice());
        }

        @Override
        public int calculateDiscount(User user) {
            return GIFT_EVENT.discountPrice;
        }
    };

    private final String name;
    private final int discountPrice;

    Event(String name, int discountPrice) {
        this.name = name;
        this.discountPrice = discountPrice;
    }

    public String getName() {
        return this.name;
    }

    public abstract boolean isApplicable(User user);

    public abstract int calculateDiscount(User user);
}
