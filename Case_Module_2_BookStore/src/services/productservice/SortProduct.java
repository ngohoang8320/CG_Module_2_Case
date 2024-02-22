package services.productservice;

import entity.product.Product;
import repo.convertdata.product.MapNoProduct;
import services.userservice.ShowProductList;
import view.NewPage;

import java.util.*;

public class SortProduct {
    public Map<Integer, Product> sort(Map<Integer, Product> productMap) {
        NewPage.newPage();
        List<Product> productList = new ArrayList<>(productMap.values());
        Comparator<Product> priceComparator = Comparator.comparingDouble(Product::getPromotionalPricing);
        Collections.sort(productList,
                priceComparator);
        Map<Integer, Product> sortedProductMap = (new MapNoProduct()).getMap(productList);
        ShowProductList.show(sortedProductMap);
        System.out.println();
        return sortedProductMap;
    }
}
