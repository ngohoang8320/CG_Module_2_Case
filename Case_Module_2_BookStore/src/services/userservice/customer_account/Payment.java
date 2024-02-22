package services.userservice.customer_account;

import controller.customer.CartPageController;
import entity.order.Order;
import entity.product.Product;
import entity.user.CurrentUser;
import entity.user.Owner;
import entity.user.UserClass;
import repo.convertdata.product.ListProduct;
import repo.convertdata.product.MapNameProduct;
import repo.convertdata.user.MapUsernameUser;
import repo.getsetdata.product.FormatProductData;
import repo.getsetdata.product.GetSetCart;
import repo.getsetdata.product.GetSetOrder;
import repo.getsetdata.product.GetSetProduct;
import repo.getsetdata.user.FormatUserData;
import repo.getsetdata.user.GetSetAccount;
import services.userservice.Logging;
import services.userservice.owner_account.SetOwnerAccount;
import view.NewPage;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Payment {
    private Scanner input = new Scanner(System.in);
    private CurrentUser currentUser = CurrentUser.getInstance();
    private Owner ownerRef = SetOwnerAccount.getInstance();
    private MapUsernameUser mapUsernameUser = new MapUsernameUser();
    private Map<String, UserClass> mapUser = mapUsernameUser.getMapUsernameUser();
    private UserClass userToEdit = mapUser.get(currentUser.getUserName());
    private UserClass owner = mapUser.get(ownerRef.getUserName());
    private GetSetAccount getSetAccount = new GetSetAccount();
    private Logging logging = new Logging();


    public void pay() {
        double price = (new ShowCart()).getTotal();
        boolean isEnoughMoney = currentUser.getMoney() >= price;

        if (isEnoughMoney) {
            currentUser.setMoney(currentUser.getMoney() - price);
            GetSetCart getSetCart = new GetSetCart();
            Map<String, List<Product>> mapUsernameCart = getSetCart.getData();
            List<Product> listCart = mapUsernameCart.get(currentUser.getUserName());
            /**
             *Collapse code above
             */
            if (listCart.size() > 0) {
                Order order = new Order();
                order.setUsername(currentUser.getUserName());
                order.setProductListInOrder(listCart);
                order.setState(false);
                order.setPrice(price);

                GetSetOrder getSetOrder = new GetSetOrder();
                List<Order> orderList = getSetOrder.getData();
                orderList.add(order);
                getSetOrder.setData(orderList);

                List<Product> listProduct = (new ListProduct()).getProductList();
                Map<String, Product> mapNameProduct = (new MapNameProduct(listProduct)).getMapNameProduct(listProduct);
                for (Product prod : listCart) {
                    mapNameProduct.get(prod.getName()).setQuantity(mapNameProduct.get(prod.getName()).getQuantity()
                                                                   - prod.getQuantity());
                }

                /**
                 * save list product after pay to data
                 */
                GetSetProduct getSetProduct = new GetSetProduct();
                getSetProduct.clearData();
                for (Product prod : listProduct) {
                    String entity = FormatProductData.formatted(prod.getId(),
                            prod.getName(),
                            prod.getOriginPrice(),
                            prod.getPromotionalPricing(),
                            prod.getQuantity());

                    getSetProduct.setData(entity);
                }


                listCart.clear();
                mapUsernameCart.put(currentUser.getUserName(),
                        listCart);

                getSetCart.setData(mapUsernameCart);

                userToEdit.setMoney(userToEdit.getMoney() - price);
                owner.setMoney(owner.getMoney() + price);

                writeToLog(price);

                Set<String> listAccount = mapUser.keySet();
                getSetAccount.clearData();
                for (String account : listAccount) {
                    UserClass acc = mapUser.get(account);
                    String entity = FormatUserData.formatted(acc.getUserName(),
                            acc.getPassword(),
                            acc.getPhoneNumber(),
                            acc.getEmail(),
                            acc.getRole(),
                            acc.getMoney());

                    getSetAccount.setData(entity);
                }

                NewPage.newPage();
                System.out.println("Your cart has just paid!\n");
                System.out.println("Press Enter to go back to Store Page.");
                input.nextLine();
                NewPage.newPage();
            } else {
                System.err.println("Your cart is empty!");
                CartPageController.controller();
            }

        } else {
            System.err.println("You do not have enough money!");
        }
    }

    private void writeToLog(double price) {
        String cusMessage = "You've just paid for a " + price + "($) bill.";
        String ownMessage = "You're just receive " + price + "($).";
        String cusFluctuation = "-" + price + "($)";
        String ownFluctuation = "+" + price + "($)";
        logging.writeLog(userToEdit.getUserName(),
                cusMessage,
                cusFluctuation);
        logging.writeLog(owner.getUserName(),
                ownMessage,
                ownFluctuation);
    }
}