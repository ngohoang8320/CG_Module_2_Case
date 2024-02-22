package view.customer;

public class CustomerHandlingProductPage {
    private static CustomerHandlingProductPage customerHandlingProductPage = null;

    private CustomerHandlingProductPage() {
    }

    public static CustomerHandlingProductPage getInstance() {
        if (customerHandlingProductPage == null) {
            customerHandlingProductPage = new CustomerHandlingProductPage();
        }
        return customerHandlingProductPage;
    }

    public void show() {
        System.out.printf("%-4s%-30s\n",
                "1.",
                "Sort product list");
        System.out.printf("%-4s%-30s\n",
                "2.",
                "Add product to cart");
        System.out.printf("%-4s%-30s\n",
                "3.",
                "Back");
        System.out.println();
    }
}
