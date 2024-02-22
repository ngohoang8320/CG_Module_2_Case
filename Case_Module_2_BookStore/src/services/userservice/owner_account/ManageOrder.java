package services.userservice.owner_account;

import controller.owner.OwnerController;
import entity.order.Order;
import entity.product.Product;
import entity.user.CurrentUser;
import repo.convertdata.MapNoOrder;
import repo.getsetdata.product.GetSetOrder;
import view.NewPage;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ManageOrder {
    Scanner input = new Scanner(System.in);
    private CurrentUser currentUser = CurrentUser.getInstance();

    public void show() {
        NewPage.newPage();
        GetSetOrder getSetOrder = new GetSetOrder();
        List<Order> orderList = getSetOrder.getData();
        Map<Integer, Order> mapNoOrder = (new MapNoOrder()).getMap(orderList);

        Set<Integer> keyOrderList = mapNoOrder.keySet();
        System.out.printf("\u001B[1m\u001B[4m%1sNo.%1s|%3sStatement:%2s|%5sPrice:%5s|%5sUsername:%11s|%10sProduct list:%10s\u001B[0m\n",
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

        for (int key : keyOrderList) {
            StringBuilder productList = new StringBuilder();
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            for (Product prod : mapNoOrder.get(key).getProductListInOrder()) {
                productList.append("[Name: "
                                   + prod.getName()
                                   + ", Quantity: "
                                   + prod.getQuantity()
                                   + ", Price: "
                                   + prod.getPromotionalPricing()
                                   + "($) x "
                                   + prod.getQuantity()
                                   + " = "
                                   + decimalFormat.format(prod.getPromotionalPricing() * prod.getQuantity())
                                   + "($)], ");
            }
            System.out.printf("%2s.%2s|%4s%-11s|%4s%-6.2f($)%3s|%5s%-20s|%3s%s\n",
                    key,
                    "",
                    "",
                    mapNoOrder.get(key).isState() ? "Accepted" : "Waiting ",
                    "",
                    mapNoOrder.get(key).getPrice(),
                    "",
                    "",
                    mapNoOrder.get(key).getUsername(),
                    "",
                    productList);
        }
        System.out.println("\nPress Enter to go back to the Owner page.");
        input.nextLine();
        NewPage.newPage();
        OwnerController.ownerController();
    }
}
