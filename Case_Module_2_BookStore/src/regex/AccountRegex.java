package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountRegex {
    public static boolean checkUserName(String userName) {
        String regex = "^(?=.*[A-Z])(?=.*\\d).{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    public static boolean checkPassword(String userName) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        String regex = "^0.{8}\\d$|^0.{9}\\d$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean checkEmail(String email) {
        String regex = "^.{8,}[a-zA-Z0-9]@.{2,}[a-zA-Z0-9]\\..{2,}[a-zA-Z]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
