package services.productservice;

public class CountAddProductTime {
    private static int count = 0;

    public CountAddProductTime() {
        count += 1;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        CountAddProductTime.count = count;
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
