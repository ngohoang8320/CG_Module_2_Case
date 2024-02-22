package entity;

public class Log {
    private String username;
    private String time;
    private String message;
    private String walletHistory;

    public Log(String username,
               String time,
               String message,
               String walletHistory) {
        this.username = username;
        this.time = time;
        this.message = message;
        this.walletHistory = walletHistory;
    }

    public String getWalletHistory() {
        return walletHistory;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }
}
