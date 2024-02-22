package repo.convertdata.product;

import entity.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapNameProduct {
    private Map<String, Product> mapNameProduct = new HashMap<>();

    public MapNameProduct(List<Product> productList) {
        setMapNameProduct(productList);
    }

    public Map<String, Product> getMapNameProduct(List<Product> productList) {
        setMapNameProduct(productList);
        return mapNameProduct;
    }

    public void setMapNameProduct(List<Product> productList) {
        for (Product prod : productList) {
            String productName = prod.getName();
            mapNameProduct.put(productName, prod);
        }
    }
}
