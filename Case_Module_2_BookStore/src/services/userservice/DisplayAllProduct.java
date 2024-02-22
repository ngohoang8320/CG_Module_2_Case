package services.userservice;

import entity.product.Product;
import repo.convertdata.product.ListProduct;
import repo.convertdata.product.MapNoProduct;
import view.NewPage;

import java.util.List;
import java.util.Map;

public class DisplayAllProduct {
    public Map<Integer, Product> display() {
        NewPage.newPage();
        List<Product> listProduct = (new ListProduct()).getProductList();
        Map<Integer, Product> currentProductMap = (new MapNoProduct()).getMap(listProduct);
        ShowProductList.show(currentProductMap);
        return currentProductMap;
    }
}