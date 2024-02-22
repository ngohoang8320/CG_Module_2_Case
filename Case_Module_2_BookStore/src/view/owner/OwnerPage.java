package view.owner;

public class OwnerPage {
    private static OwnerPage ownerPage = null;

    private OwnerPage() {
    }

    public static OwnerPage getInstance() {
        if (ownerPage == null) {
            ownerPage = new OwnerPage();
        }
        return ownerPage;
    }

    public void showOwnerPage() {
        System.out.printf("%18sWelcome back, manager!\n",
                "");
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
                "Withdraw");
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
                "Add product");
        System.out.printf("\u001B[9m%-4s%-30s\u001B[0m\n",
                "9.",
                "Create a voucher");
        System.out.println();

        System.out.printf("%-4s%-30s\n",
                "15.",
                "Manage order");
        System.out.printf("%-4s%-30s\n",
                "20.",
                "Log out");
        System.out.printf("%-4s%-30s\n",
                "0.",
                "Leave");

        System.out.println();
    }
}
