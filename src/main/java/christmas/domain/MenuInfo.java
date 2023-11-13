package christmas.domain;

public class MenuInfo {
    private String name;
    private int price;

    private MenuInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static MenuInfo from(Object[] menuInfo) {
        String menuName = (String) menuInfo[0];
        int menuPrice = (int) menuInfo[1];
        return new MenuInfo(menuName, menuPrice);
    }

    public boolean nameEquals(String otherName) {
        return name.equals(otherName);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
