package christmas.handler;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.view.ErrorView;
import christmas.view.InputView;
import java.util.function.Function;
import java.util.function.Supplier;

public class InputHandler {
    private InputView inputView;
    private ErrorView errorView;

    public InputHandler(InputView inputView, ErrorView errorView) {
        this.inputView = inputView;
        this.errorView = errorView;
    }

    public VisitDate receiveValidatedVisitDate() {
        return receiveValidatedInput(inputView::readDate, VisitDate::from);
    }

    public Order receiveValidatedOrder() {
        return receiveValidatedInput(inputView::readOrder, Order::from);
    }

    private <T> T receiveValidatedInput(Supplier<String> inputView, Function<String, T> conversion) {
        while (true) {
            try {
                String input = inputView.get();
                return conversion.apply(input);
            } catch (IllegalArgumentException exception) {
                errorView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
