package entity.product;

import controller.owner.OwnerController;
import services.productservice.CountAddProductTime;
import view.NewPage;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Product implements Cloneable, Serializable {
    private static Scanner input = new Scanner(System.in);
    private int id;

    private String name;
    private double originPrice;
    private double promotionalPricing;
    private int quantity;
    private String[] review;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public double getPromotionalPricing() {
        return promotionalPricing;
    }

    public void setPromotionalPricing(double promotionalPricing) {
        this.promotionalPricing = promotionalPricing;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String[] getReview() {
        return review;
    }

    public void setReview(String[] review) {
        this.review = review;
    }

    public void setName() {
        System.out.print("Enter product's name: ");
        name = input.nextLine().trim();
    }

    public void setOriginPrice() {
        System.out.print("Enter product's origin price: ");
        try {
            originPrice = input.nextDouble();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
            new CountAddProductTime();
            if (!CountAddProductTime.isAtLimitTimeInput()) {
                System.err.println("Wrong format for the price!");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                NewPage.newPage();
                OwnerController.ownerController();
            }
            setOriginPrice();
        }
    }

    public void setPromotionalPricing() {
        System.out.print("Enter product's promotional price: ");
        try {
            promotionalPricing = input.nextDouble();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
            new CountAddProductTime();
            if (!CountAddProductTime.isAtLimitTimeInput()) {
                System.err.println("Wrong format for the price!");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                NewPage.newPage();
                OwnerController.ownerController();
            }
            setPromotionalPricing();
        }
    }

    public void setQuantity() {
        System.out.print("Enter product's quantity: ");
        try {
            quantity = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
            new CountAddProductTime();
            if (!CountAddProductTime.isAtLimitTimeInput()) {
                System.err.println("Wrong format for the price!");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                NewPage.newPage();
                OwnerController.ownerController();
            }
            setQuantity();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
