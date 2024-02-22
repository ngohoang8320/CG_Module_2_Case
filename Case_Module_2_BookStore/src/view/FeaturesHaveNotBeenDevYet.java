package view;

import java.util.Scanner;

public class FeaturesHaveNotBeenDevYet {
    private static Scanner input = new Scanner(System.in);

    public static void notice() {
        NewPage.newPage();
        System.err.println("This feature has not developed yet!");
        System.err.println("Please come back later... 😊");
        input.nextLine();
    }
}
