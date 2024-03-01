package services.userservice.customer_account;

import controller.customer.CartPageController;
import controller.customer.CustomerHandlingProductController;
import entity.product.Product;
import entity.user.CurrentUser;
import exception.DoNotHaveEnoughProduct;
import repo.convertdata.product.MapNameProduct;
import repo.getsetdata.product.GetSetCart;
import services.userservice.ShowProductList;
import view.NewPage;

import java.util.*;

public class AddToCard {
    private Scanner input = new Scanner(System.in);

    public void add(Map<Integer, Product> mapNoProduct) {
        System.out.println("\u001B[3m\n(Adding by enter the number in the No. column)\u001B[0m");
        System.out.print("What product would you like to add to cart: ");
        try {
            int choice = input.nextInt();
            input.nextLine();
            if (mapNoProduct.get(choice) == null) {
                System.err.println("\nThere is no product at No. "
                                   + choice
                                   + "\nPlease choose the product base on the following table.");
                input.nextLine();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ix) {
                    throw new RuntimeException(ix);
                }
                NewPage.newPage();
                CartPageController.controller();
            }

            boolean isNOTEnoughQuantity = mapNoProduct.get(choice).getQuantity() <= 0;
            if (isNOTEnoughQuantity) {
                noticeDoNotEnoughProduct(mapNoProduct,
                        mapNoProduct.get(choice));
            } else {
                Map<String, List<Product>> mapUsernameCart = (new GetSetCart()).getData();
                CurrentUser currentUser = CurrentUser.getInstance();
                List<Product> listCart = mapUsernameCart.get(currentUser.getUserName());

                if (listCart != null) {
                    try {
                        if (mapNoProduct.get(choice) == null) {
                            System.err.println("\nThere is no product at No. "
                                               + choice
                                               + "\nPlease choose the product base on the following table.");
                            input.nextLine();
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ix) {
                                throw new RuntimeException(ix);
                            }
                            ShowProductList.show(mapNoProduct);
                            CustomerHandlingProductController.controller(mapNoProduct);
                        }
                        Product cloneProduct = (Product) mapNoProduct.get(choice).clone();
                        Map<String, Product> mapNameProduct = (new MapNameProduct(listCart)).getMapNameProduct(listCart);
                        if (mapNameProduct.get(cloneProduct.getName()) == null) {
                            cloneProduct.setQuantity(1);
                            listCart.add(cloneProduct);
                        } else {
                            /**
                             * If after change, the quantity is greater than "In shop quantity" => Notice to customer
                             */
                            try {
                                boolean isNotEnoughProductToAdd = (mapNameProduct.get(cloneProduct.getName()).getQuantity()
                                                                   + 1) > mapNoProduct.get(choice).getQuantity();
                                if (isNotEnoughProductToAdd) {
                                    throw new DoNotHaveEnoughProduct("Do not have enough product to add to cart.");
                                }
                                cloneProduct.setQuantity(mapNameProduct.get(cloneProduct.getName()).getQuantity() + 1);
                            } catch (DoNotHaveEnoughProduct e) {
                                noticeDoNotEnoughProduct(mapNoProduct,
                                        mapNoProduct.get(choice));
                            }
                            listCart.remove(mapNameProduct.get(cloneProduct.getName()));
                            listCart.add(cloneProduct);
                        }
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                    mapUsernameCart.put(currentUser.getUserName(),
                            listCart);
                } else {
                    List<Product> newListCart = new ArrayList<>();
                    try {
                        Product cloneProduct = (Product) mapNoProduct.get(choice).clone();
                        cloneProduct.setQuantity(1);
                        newListCart.add(cloneProduct);
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                    mapUsernameCart.put(currentUser.getUserName(),
                            newListCart);
                }

                GetSetCart getSetCart = new GetSetCart();
                getSetCart.setData(mapUsernameCart);

                NewPage.newPage();
                ShowProductList.show(mapNoProduct);
                System.out.println();
                CustomerHandlingProductController.controller(mapNoProduct);
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            System.err.println("Wrong format!");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            int choice = wantToTryAgain();

            switch (choice) {
                case 1:
                    NewPage.newPage();
                    ShowProductList.show(mapNoProduct);
                    add(mapNoProduct);
                case 2:
                    NewPage.newPage();
                    ShowProductList.show(mapNoProduct);
                    CustomerHandlingProductController.controller(mapNoProduct);
                default:
                    System.out.println("Do not have this feature.");
            }
        }
    }

    private void noticeDoNotEnoughProduct(Map<Integer, Product> mapNoProduct,
                                          Product choseProduct) {
        NewPage.newPage();
        System.err.println("The " + choseProduct.getName() + "\'s quantity: " + choseProduct.getQuantity());
        System.err.println("There are NOT enough quantity to add this product to cart.");
        input.nextLine();
        NewPage.newPage();
        ShowProductList.show(mapNoProduct);
        CustomerHandlingProductController.controller(mapNoProduct);
    }

    private int wantToTryAgain() {
        int choiceTry;
        System.out.println("Would you like to try again?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println();
        System.out.print("Your choice: ");
        try {
            choiceTry = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException ex) {
            input.nextLine();
            System.err.println("Just choose 1 (for Yes) or 2 (for No).");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ix) {
                throw new RuntimeException(ix);
            }
            return wantToTryAgain();
        }
        return choiceTry;
    }
}
