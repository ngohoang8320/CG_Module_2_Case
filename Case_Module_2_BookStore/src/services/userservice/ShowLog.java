package services.userservice;

import entity.Log;
import entity.user.CurrentUser;
import repo.convertdata.MapNoLog;
import view.NewPage;

import java.util.*;

public class ShowLog {
    private Scanner input = new Scanner(System.in);
    private Logging logging = new Logging();
    private MapNoLog mapNoLog = new MapNoLog();


    public void show() {
        NewPage.newPage();
        CurrentUser currentUser = CurrentUser.getInstance();
        List<Log> listLog = logging.readLog();
        List<Log> currentListLog = new ArrayList<>();
        for (Log log : listLog) {
            if (log.getUsername().equals(currentUser.getUserName())) {
                currentListLog.add(log);
            }
        }
        Map<Integer, Log> logMap = mapNoLog.getMap(currentListLog);

        Set<Integer> keyLogSet = logMap.keySet();
        System.out.printf("\u001B[1m\u001B[4m%1sNo.%1s|%8sTime:%10s|%2sBalance fluctuations:%2s|%9sMesage:%9s\u001B[0m\n",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "");
        for (int key : keyLogSet) {
            Log log = logMap.get(key);
            System.out.printf("%2s.%2s|%2s%-21s|%8s%-17s|%3s%s\u001B[0m\n",
                    key,
                    "",
                    "",
                    log.getTime(),
                    "",
                    log.getWalletHistory(),
                    "",
                    log.getMessage());
        }
        System.out.println("\nPress Enter to go back to the previous page.");
        input.nextLine();
        NewPage.newPage();
    }
}
