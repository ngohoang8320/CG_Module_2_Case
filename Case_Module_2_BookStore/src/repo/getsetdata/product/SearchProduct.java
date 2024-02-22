package repo.getsetdata.product;

import entity.product.Product;
import repo.convertdata.product.ListProduct;
import repo.convertdata.product.MapNoProduct;
import services.userservice.ShowProductList;
import util.LevenshteinDistance;
import view.NewPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SearchProduct {
    private Scanner input = new Scanner(System.in);
    private List<Product> searchedList = new ArrayList<>();

    public Map<Integer, Product> search() {
        NewPage.newPage();
        System.out.print("Enter the name of the product you want to search: ");
        String productName = input.nextLine();
        int limitDistance = 5;
        List<Product> listProduct = (new ListProduct()).getProductList();
        for (Product prod : listProduct) {
            if (LevenshteinDistance.calLevenshteinDistance(prod.getName(),
                    productName) <= limitDistance) {
                searchedList.add(prod);
            }
        }
        Map<Integer, Product> currentProductMap = (new MapNoProduct()).getMap(searchedList);
        NewPage.newPage();
        ShowProductList.show(currentProductMap);
        return currentProductMap;
    }
}
