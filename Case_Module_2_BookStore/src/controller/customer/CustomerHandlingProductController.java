package controller.customer;

import entity.product.Product;
import services.productservice.SortProduct;
import services.userservice.ShowProductList;
import services.userservice.customer_account.AddToCard;
import view.NewPage;
import view.customer.CustomerHandlingProductPage;

import java.util.Map;
import java.util.Scanner;

public class CustomerHandlingProductController {
    public static void controller(Map<Integer, Product> noProductMap) {
        Scanner input = new Scanner(System.in);

        CustomerHandlingProductPage.getInstance().show();
        System.out.print("Your choice: ");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                SortProduct sortProduct = new SortProduct();
                Map<Integer, Product> sortedProductMap = sortProduct.sort(noProductMap);
                controller(sortedProductMap);
            case 2:
                AddToCard addToCard = new AddToCard();
                addToCard.add(noProductMap);
                controller(noProductMap);
            case 3:
                NewPage.newPage();
                StoreController.storeController();
            default:
                System.err.println("We do not have this feature.");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Please choose features represented of numbers based on the menu.");
                input.nextLine();
                ShowProductList.show(noProductMap);
                controller(noProductMap);
        }
    }
}
