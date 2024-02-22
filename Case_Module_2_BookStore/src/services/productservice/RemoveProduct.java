package services.productservice;

import entity.product.Product;
import repo.convertdata.product.ListProduct;
import repo.getsetdata.product.FormatProductData;
import repo.getsetdata.product.GetSetProduct;
import services.userservice.ShowProductList;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class RemoveProduct {
    private int choice;
    private Scanner input = new Scanner(System.in);
    private GetSetProduct getSetProduct = new GetSetProduct();
    private List<Product> listProduct = (new ListProduct()).getProductList();

    public Map<Integer, Product> remove(Map<Integer, Product> noProductMap) {
        System.out.println("\u001B[3m\n(Choosing by enter the number in the No. column)\u001B[0m");
        System.out.print("What product would you like to remove: ");
        int choseProduct = input.nextInt(); //if do not have product at this position
        input.nextLine();
        System.out.println();

        Product referenceProduct = noProductMap.get(choseProduct);
        Product productToEdit = null;
        for (Product prod : listProduct) {
            if (prod.getId() == referenceProduct.getId()) {
                productToEdit = prod;
                break;
            }
        }

        listProduct.remove(productToEdit);

        getSetProduct.clearData();
        for (Product prod : listProduct) {
            String entity = FormatProductData.formatted(prod.getId(),
                    prod.getName(),
                    prod.getOriginPrice(),
                    prod.getPromotionalPricing(),
                    prod.getQuantity());

            getSetProduct.setData(entity);
        }
        Set<Integer> listKey = noProductMap.keySet();
        for (int key : listKey) {
            if (key >= choseProduct) {
                noProductMap.put(key,
                        noProductMap.get(key + 1));
            }
        }
        noProductMap.remove(noProductMap.size());
        ShowProductList.show(noProductMap);
        return noProductMap;
    }
}
