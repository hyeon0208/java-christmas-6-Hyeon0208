package christmas.view;

public class ErrorView {
    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String BLANK = " ";

    public void printErrorMessage(String errorMessage) {
        System.out.println(makeErrorMessage(errorMessage));
    }

    private String makeErrorMessage(String errorMessage) {
        return String.join(BLANK, ERROR_PREFIX, errorMessage);
    }
}
