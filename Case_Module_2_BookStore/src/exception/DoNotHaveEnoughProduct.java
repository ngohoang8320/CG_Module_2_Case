package exception;

public class DoNotHaveEnoughProduct extends RuntimeException {
    public DoNotHaveEnoughProduct(String message) {
        super(message);
    }
}
