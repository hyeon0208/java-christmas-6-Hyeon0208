package christmas.domain.menu;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {
    APPETIZER(addMenus(new Object[][] {{"양송이수프", 6000}, {"타파스", 5500}, {"시저샐러드", 8000}})),
    MAIN(addMenus(new Object[][] {{"티본스테이크", 55000}, {"바비큐립", 54000}, {"해산물파스타", 35000}, {"크리스마스파스타", 25000}})),
    DESSERT(addMenus(new Object[][] {{"초코케이크", 15000}, {"아이스크림", 5000}})),
    DRINK(addMenus(new Object[][] {{"제로콜라", 3000}, {"레드와인", 60000}, {"샴페인", 25000}}));

    private final List<MenuInfo> menuInfos;

    Menu(List<MenuInfo> menuInfos) {
        this.menuInfos = menuInfos;
    }

    public static boolean contains(String name) {
        return EnumSet.allOf(Menu.class).stream()
                .flatMap(menu -> menu.menuInfos.stream())
                .anyMatch(menuInfo -> menuInfo.nameEquals(name));
    }

    public static int getPriceOf(String name) {
        return EnumSet.allOf(Menu.class).stream()
                .flatMap(menu -> menu.menuInfos.stream())
                .filter(menuInfo -> menuInfo.nameEquals(name))
                .map(MenuInfo::getPrice)
                .findFirst()
                .orElse(0);
    }

    public static boolean isDrinkMenu(String name) {
        return DRINK.menuInfos.stream()
                .map(MenuInfo::getName)
                .anyMatch(drinkName -> drinkName.equals(name));
    }

    public static boolean isDessert(String name) {
        return DESSERT.menuInfos.stream()
                .map(MenuInfo::getName)
                .anyMatch(drinkName -> drinkName.equals(name));
    }

    public static boolean isMain(String name) {
        return MAIN.menuInfos.stream()
                .map(MenuInfo::getName)
                .anyMatch(drinkName -> drinkName.equals(name));
    }

    private static List<MenuInfo> addMenus(Object[][] menus) {
        return Arrays.stream(menus)
                .map(menu -> MenuInfo.from(menu))
                .collect(Collectors.toList());
    }
}
