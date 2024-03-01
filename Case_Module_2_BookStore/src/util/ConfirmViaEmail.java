package util;

import java.util.Scanner;

public class ConfirmViaEmail {
    private static CreateConfirmCode createConfirmCode = new CreateConfirmCode();
    private static SendEmail sendEmail = new SendEmail();
    private static Scanner input = new Scanner(System.in);

    public static boolean confirm(String email) {
        String confirmCode = createConfirmCode.create();
        sendEmail.sendEmail(email,
                confirmCode);
        int count = 0;
        if (enterConfirmCode(confirmCode,
                count)) return true;
        return false;
    }

    private static boolean enterConfirmCode(String confirmCode,
                                            int count) {
        System.out.println("\nEnter the confirm code has been sent to your email.");
        System.out.print("Confirm code: ");
        String inputConfirmCode = input.nextLine();
        if (inputConfirmCode.equals(confirmCode)) {
            return true;
        } else {
            count++;
            if (count < 3) {
                System.err.println("\nThe confirm code is not true.\nPlease try again! (" + (3 - count) + " / 3)");
                enterConfirmCode(confirmCode,
                        count);
            }
        }
        return false;
    }
}
