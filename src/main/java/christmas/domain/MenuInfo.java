package christmas.domain;

public record MenuInfo(String name, int price) {

    public boolean nameEquals(String otherName) {
        return name.equals(otherName);
    }
}
