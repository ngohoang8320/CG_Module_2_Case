package services.userservice.customer_account;

import entity.product.Product;
import entity.user.CurrentUser;
import repo.convertdata.product.MapNoProduct;
import repo.getsetdata.product.GetSetCart;
import services.userservice.ShowProductList;
import view.customer.CartPage;

import java.util.List;
import java.util.Map;

public class ShowCart {
    private double total;
    private GetSetCart getSetCart = new GetSetCart();
    private CurrentUser currentUser = CurrentUser.getInstance();
    private Map<String, List<Product>> mapUsernameCart = getSetCart.getData();
    private List<Product> listCart = mapUsernameCart.get(currentUser.getUserName());

    public double getTotal() {
        total = 0;
        for (Product prod : listCart) {
            total += prod.getPromotionalPricing() * prod.getQuantity();
        }
        return total;
    }

    public boolean show() {
        if (listCart != null && listCart.size() > 0) {
            Map<Integer, Product> mapNoProduct = (new MapNoProduct()).getMap(listCart);
            ShowProductList.show(mapNoProduct);
            total = getTotal();
            System.out.printf("%128s\u001B[7m%s%20s\u001B[0m\n",
                    "",
                    "",
                    "");
            System.out.printf("%128s\u001B[7m%s\u001B[0m\u001B[1mTotal: %-8.2f($)\u001B[7m%s\u001B[0m\n",
                    "",
                    " ",
                    total,
                    " ");
            System.out.printf("%128s\u001B[7m%s%20s\u001B[0m\n",
                    "",
                    "",
                    "");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            CartPage.getInstance().show();

            return false;
        } else {
            System.out.println("your cart is empty.\n");
            return true;
        }
    }
}