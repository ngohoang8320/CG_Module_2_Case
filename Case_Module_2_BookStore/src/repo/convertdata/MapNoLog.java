package repo.convertdata;

import entity.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapNoLog {
    private Map<Integer, Log> mapNoLog = new HashMap<>();

    public Map<Integer, Log> getMap(List<Log> logList) {
        setMap(logList);
        return mapNoLog;
    }

    private void setMap(List<Log> logList) {
        mapNoLog.clear();
        int count = 0;
        for (Log order : logList) {
            count++;
            mapNoLog.put(count,
                    order);
        }
    }
}
