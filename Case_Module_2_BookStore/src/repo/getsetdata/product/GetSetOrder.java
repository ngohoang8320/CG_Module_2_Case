package repo.getsetdata.product;

import entity.order.Order;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GetSetOrder {
    private File file = new File("src\\data\\orders.bin");

    public List<Order> getData() {
        List<Order> orderList = new ArrayList<>();
        if (!Files.exists(file.toPath())) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                return orderList;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                try {
                    orderList = (List<Order>) ois.readObject();
                } catch (EOFException e) {
                    return orderList;
                } finally {
                    ois.close();
                }
            } catch (EOFException e) {
                return orderList;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }

    public void setData(List<Order> orderList) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(orderList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
