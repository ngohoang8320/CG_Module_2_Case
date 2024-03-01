package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateConfirmCode {
    public String create() {
        List<Integer> range = new ArrayList<>();
        for (int i = 48; i <= 57; i++) {
            range.add(i);
        }
        for (int i = 65; i <= 90; i++) {
            range.add(i);
        }
        for (int i = 97; i <= 122; i++) {
            range.add(i);
        }
        Random random = new Random();
        String confirmCode = "";
        int len = range.size();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(len);
            confirmCode += (char) range.get(index).intValue();
        }
        return confirmCode;
    }
}
