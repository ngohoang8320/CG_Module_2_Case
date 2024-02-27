package services.userservice;

import entity.product.Product;

import java.util.Map;
import java.util.Set;

public class ShowProductList {
    public static void show(Map<Integer, Product> productMap) {
        Set<Integer> keyProductSet = productMap.keySet();
        System.out.printf("\u001B[1m\u001B[4m%2sNo.%1s|%16sName:%55s|%2sId:%2s|%9sPrice:%9s|%2sQuantity:%2s\u001B[0m\n",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "");
        for (int key : keyProductSet) {
            Product product = productMap.get(key);
            System.out.printf("%3s.%2s|%2s%-74s|%2s%-5d|%3s%-6.2f$   \u001B[9m(%-6.2f$)\u001B[0m%2s|%5s%-5d\n",
                    key,
                    "",
                    "",
                    product.getName(),
                    "",
                    product.getId(),
                    "",
                    product.getPromotionalPricing(),
                    product.getOriginPrice(),
                    "",
                    "",
                    product.getQuantity());
        }
        System.out.println();
    }
}