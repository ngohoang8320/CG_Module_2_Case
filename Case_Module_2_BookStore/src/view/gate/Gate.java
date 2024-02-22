package view.gate;

public class Gate {
    private static Gate gate = null;

    private Gate() {
    }

    public static Gate getInstance() {
        if (gate == null) {
            gate = new Gate();
        }
        return gate;
    }

    public void showGatePage() {
        System.out.printf("%25sWelcome to my BookStore!\n",
                "");
        System.out.println("If you've already had an account, you can Sign in now by choosing number 1.");
        System.out.println();
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        System.out.println("0. Leave");

        System.out.println();
    }
}
