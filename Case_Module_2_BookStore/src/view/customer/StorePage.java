package view.customer;

public class StorePage {
    private static StorePage storePage = null;

    private StorePage() {
    }

    public static StorePage getInstance() {
        if (storePage == null) {
            storePage = new StorePage();
        }
        return storePage;
    }

    public void showStorePage() {
        System.out.printf("%18sEACH BOOK IS A WORLD!\n",
                "");
        System.out.println("Welcome to my universe and hope you get a great adventure!");
        System.out.println();
        System.out.printf("%-4s%-30s\n",
                "1.",
                "Get your own information");
        System.out.printf("%-4s%-30s\n",
                "2.",
                "Edit your information");
        System.out.printf("%-4s%-30s\n",
                "3.",
                "Check wallet balance");
        System.out.printf("%-4s%-30s\n",
                "4.",
                "Deposit");
        System.out.printf("%-4s%-30s\n",
                "5.",
                "Show log");
        System.out.printf("%-4s%-30s\n",
                "6.",
                "Display product list");
        System.out.printf("%-4s%-30s\n",
                "7.",
                "Search product list");
        System.out.printf("%-4s%-30s\n",
                "8.",
                "Show your cart");
        System.out.printf("%-4s%-30s\n",
                "9.",
                "Log out");
        System.out.printf("%-4s%-30s\n",
                "0.",
                "Leave");

        System.out.println();
    }
}
