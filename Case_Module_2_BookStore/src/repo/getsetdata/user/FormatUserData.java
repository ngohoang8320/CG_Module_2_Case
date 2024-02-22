package repo.getsetdata.user;

public class FormatUserData {
    public static String formatted(String username,
                                   String password,
                                   String phoneNumber,
                                   String email,
                                   String role,
                                   double money) {
        String entity = String.format("%s,%s,%s,%s,%s,%s,%s",
                username,
                password,
                phoneNumber,
                email,
                role,
                money,
                "\n");

        return entity;
    }
}