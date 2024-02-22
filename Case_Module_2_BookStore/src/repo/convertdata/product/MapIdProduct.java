package repo.convertdata.product;

import entity.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapIdProduct {
    private Map<Integer, Product> mapIdProduct = new HashMap<>();

    public MapIdProduct() {
        setMapIdProduct();
    }

    public Map<Integer, Product> getMapIdProduct() {
        return mapIdProduct;
    }

    private void setMapIdProduct() {
        List<Product> productList = (new ListProduct()).getProductList();
        for (Product prod : productList) {
            int productId = prod.getId();
            mapIdProduct.put(productId, prod);
        }
    }
}
