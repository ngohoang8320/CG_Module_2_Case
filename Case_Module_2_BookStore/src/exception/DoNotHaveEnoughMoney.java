package exception;

public class DoNotHaveEnoughMoney extends RuntimeException {
    public DoNotHaveEnoughMoney(String ex) {
        super(ex);
    }
}
