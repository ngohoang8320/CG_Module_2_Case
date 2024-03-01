package view.owner;

public class OwnerHandlingProductPage {
    private static OwnerHandlingProductPage ownerHandlingProductPage = null;

    public OwnerHandlingProductPage() {
    }

    public static OwnerHandlingProductPage getInstance() {
        if (ownerHandlingProductPage == null) {
            ownerHandlingProductPage = new OwnerHandlingProductPage();
        }
        return ownerHandlingProductPage;
    }

    public void show() {
        System.out.printf("%-4s%-30s\n",
                "1.",
                "Sort product list");
        System.out.printf("%-4s%-30s\n",
                "2.",
                "Edit product");
        System.out.printf("%-4s%-30s\n",
                "3.",
                "Remove Product");
        System.out.printf("%-4s%-30s\n",
                "4.",
                "Search");
        System.out.printf("%-4s%-30s\n",
                "5.",
                "Back");

        System.out.println();
    }
}
