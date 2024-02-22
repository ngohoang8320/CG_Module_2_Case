package entity.order;

import entity.product.Product;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private String username;
    private List<Product> productListInOrder;
    private boolean state;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Product> getProductListInOrder() {
        return productListInOrder;
    }

    public void setProductListInOrder(List<Product> productListInOrder) {
        this.productListInOrder = productListInOrder;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
