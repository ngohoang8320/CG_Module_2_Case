package view.customer;

public class CartPage {
    private static CartPage cartPage = null;

    private CartPage() {
    }

    public static CartPage getInstance() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }

    public void show() {
        System.out.printf("%-4s%-30s\n",
                "1.",
                "Edit quantity");
        System.out.printf("%-4s%-30s\n",
                "2.",
                "Remove product");
        System.out.printf("\u001B[9m%-4s%-30s\u001B[0m\n",
                "3.",
                "Use voucher");
        System.out.printf("%-4s%-30s\n",
                "4.",
                "Payment");
        System.out.printf("%-4s%-30s\n",
                "5.",
                "Back");
        System.out.println();
    }
}