package services.userservice;

import entity.Log;
import repo.getsetdata.product.GetSetLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Logging {
    GetSetLog getSetLog = new GetSetLog();

    public void writeLog(String username,
                         String message,
                         String walletHistory) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String currentTime = localDateTime.format(timeFormatter);
        String currentDate = localDateTime.format(dateFormatter);

        String logFormat = currentDate
                           + ","
                           + currentTime
                           + ","
                           + username
                           + ","
                           + message
                           + ","
                           + walletHistory
                           + "\n";
        getSetLog.setData(logFormat);
    }

    public List<Log> readLog() {
        List<Log> listLog = new ArrayList<>();
        List<String> logStringList = getSetLog.getData();
        for (String lineLog : logStringList) {
            String[] arrayLog = lineLog.split(",");
            Log log = new Log(arrayLog[2],
                    arrayLog[0] + " " + arrayLog[1],
                    arrayLog[3],
                    arrayLog[4]);
            listLog.add(log);
        }
        return listLog;
    }
}
