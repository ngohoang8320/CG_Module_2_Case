package services.productservice;

import entity.product.Product;
import repo.convertdata.product.ListProduct;
import repo.getsetdata.product.FormatProductData;
import repo.getsetdata.product.GetSetProduct;
import services.userservice.ShowProductList;
import view.NewPage;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EditProduct {
    private int choice;
    private Scanner input = new Scanner(System.in);
    private GetSetProduct getSetProduct = new GetSetProduct();
    private List<Product> listProduct = (new ListProduct()).getProductList();

    public Map<Integer, Product> edit(Map<Integer, Product> noProductMap) {
        System.out.println("\u001B[3m\n(Choosing by enter the number in the No. column)\u001B[0m");
        System.out.print("What product would you like to edit: ");
        int choseProduct = input.nextInt();//if do not have product at this position
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

        System.out.println("What would you like to edit?");
        System.out.println("1. Name");
        System.out.println("2. Origin price");
        System.out.println("3. Promotional pricing");
        System.out.println("4. Quantity");
        System.out.println("5. All");
        System.out.println("6. Back");

        System.out.println();
        System.out.print("Your choice: ");
        choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                System.out.println();
                productToEdit.setName();
                break;
            case 2:
                System.out.println();
                productToEdit.setOriginPrice();
                break;
            case 3:
                System.out.println();
                productToEdit.setPromotionalPricing();
                break;
            case 4:
                System.out.println();
                productToEdit.setQuantity();
                break;
            case 5:
                System.out.println();
                productToEdit.setName();
                productToEdit.setOriginPrice();
                productToEdit.setPromotionalPricing();
                productToEdit.setQuantity();
                break;
            case 6:
                break;
            default:
                System.err.println("We do not have this feature.");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Please choose features represented of numbers based on the menu.");
                input.nextLine();
                edit(noProductMap);
        }
        NewPage.newPage();
        getSetProduct.clearData();
        for (Product prod : listProduct) {
            String entity = FormatProductData.formatted(prod.getId(),
                    prod.getName(),
                    prod.getOriginPrice(),
                    prod.getPromotionalPricing(),
                    prod.getQuantity());

            getSetProduct.setData(entity);
        }
        noProductMap.put(choseProduct,
                productToEdit);
        ShowProductList.show(noProductMap);
        return noProductMap;
    }
}
