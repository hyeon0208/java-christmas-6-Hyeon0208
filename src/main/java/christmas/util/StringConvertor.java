package christmas.util;

public class StringConvertor {
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";

    private StringConvertor() {
    }

    public static int convertStringToInt(String value) {
        return Integer.parseInt(value);
    }

    public static String[] splitByComma(String value) {
        return value.split(COMMA);
    }

    public static String[] splitByHyphen(String value) {
        return value.split(HYPHEN);
    }
}
