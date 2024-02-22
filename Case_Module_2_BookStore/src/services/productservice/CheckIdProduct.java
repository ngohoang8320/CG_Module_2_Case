package services.productservice;

import entity.product.Product;
import repo.convertdata.product.MapIdProduct;

import java.util.Map;

public class CheckIdProduct {
    public static boolean isExist(Integer id) {
        MapIdProduct mapIdProduct = new MapIdProduct();
        Map<Integer, Product> mapProduct = mapIdProduct.getMapIdProduct();
        return mapProduct.get(id) == null
                ? false
                : true;
    }
}
