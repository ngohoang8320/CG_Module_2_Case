package repo.convertdata.product;

import entity.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapNoProduct {
    private Map<Integer, Product> mapNoProduct = new HashMap<>();
    
    public Map<Integer, Product> getMap(List<Product> productList) {
        setMap(productList);
        return mapNoProduct;
    }

    private void setMap(List<Product> productList) {
        mapNoProduct.clear();
        int count = 0;
        for (Product prod : productList) {
            count++;
            mapNoProduct.put(count, prod);
        }
    }
}
