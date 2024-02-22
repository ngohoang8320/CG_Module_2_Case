package repo.convertdata;

import entity.order.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapNoOrder {
    private Map<Integer, Order> mapNoOrder = new HashMap<>();

    public Map<Integer, Order> getMap(List<Order> orderList) {
        setMap(orderList);
        return mapNoOrder;
    }

    private void setMap(List<Order> orderList) {
        mapNoOrder.clear();
        int count = 0;
        for (Order order : orderList) {
            count++;
            mapNoOrder.put(count, order);
        }
    }
}
