package services.productservice;

import entity.product.Product;
import repo.convertdata.product.ListProduct;
import repo.convertdata.product.MapNameProduct;

import java.util.List;
import java.util.Map;

public class CheckNameProduct {
    public static boolean isExist(String name) {
        List<Product> productList = (new ListProduct()).getProductList();
        MapNameProduct mapNameProduct = new MapNameProduct(productList);
        Map<String, Product> mapProduct = mapNameProduct.getMapNameProduct(productList);

        return mapProduct.get(name) == null
                ? false
                : true;

        /**
         * Make it more detail by using for loop and .lowercase
         */
    }
}
