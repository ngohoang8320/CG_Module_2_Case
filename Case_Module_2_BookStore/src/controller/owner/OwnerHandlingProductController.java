package controller.owner;

import entity.product.Product;
import services.productservice.EditProduct;
import services.productservice.RemoveProduct;
import services.productservice.SortProduct;
import view.NewPage;
import view.owner.OwnerHandlingProductPage;

import java.util.Map;
import java.util.Scanner;

public class OwnerHandlingProductController {
    public static void controller(Map<Integer, Product> noProductMap) {
        Scanner input = new Scanner(System.in);
        OwnerHandlingProductPage.getInstance().show();
        System.out.print("Your choice: ");
        int choice = input.nextInt(); //Add try catch
        input.nextLine();
        switch (choice) {
            case 1:
                SortProduct sortProduct = new SortProduct();
                Map<Integer, Product> sortedProductMap = sortProduct.sort(noProductMap);
                controller(sortedProductMap);
            case 2:
                EditProduct editProduct = new EditProduct();
                Map<Integer, Product> afterEditProductMap = editProduct.edit(noProductMap);
                controller(afterEditProductMap);
            case 3:
                RemoveProduct removeProduct = new RemoveProduct();
                Map<Integer, Product> afterRemoveProductMap = removeProduct.remove(noProductMap);
                controller(afterRemoveProductMap);
            case 4:
                NewPage.newPage();
                OwnerController.ownerController();
            default:
                System.err.println("We do not have this feature.");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Please choose features represented of numbers based on the menu.");
                input.nextLine();
        }
    }
}
