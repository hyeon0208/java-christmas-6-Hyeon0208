package christmas;

import christmas.controller.PromotionController;
import christmas.handler.InputHandler;
import christmas.view.ErrorView;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputHandler inputHandler = initInputHandler();
        OutputView outputView = new OutputView();
        PromotionController promotionController = new PromotionController(inputHandler, outputView);
        promotionController.run();
    }

    private static InputHandler initInputHandler() {
        InputView inputView = new InputView();
        ErrorView errorView = new ErrorView();
        return new InputHandler(inputView, errorView);
    }
}
