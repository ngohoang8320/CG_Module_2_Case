package services.userservice.sign;

public class CountSignTime {
    private static int count = 0;

    public CountSignTime() {
        count += 1;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        CountSignTime.count = count;
    }

    public static boolean isAtLimitTimeInput() {
        if (count >= 3) {
            setCount(0);
            return true;
        }
        return false;
    }

    public static void resetCount() {
        setCount(0);
    }
}
