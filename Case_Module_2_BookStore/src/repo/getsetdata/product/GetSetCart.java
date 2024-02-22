package repo.getsetdata.product;

import entity.product.Product;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetSetCart {
    private File file = new File("src\\data\\cartUsernameProductListQuantity.bin");

    public Map<String, List<Product>> getData() {
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(file));
            try {
                return (Map<String, List<Product>>) input.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            Map<String, List<Product>> mapUsernameProduct = new HashMap<>();
            return mapUsernameProduct; //When file is empty, an empty map is created and return
        }
    }

    public void setData(Map<String, List<Product>> mapUsernameCart) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file,
                    false));
            try {
                output.writeObject(mapUsernameCart);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
