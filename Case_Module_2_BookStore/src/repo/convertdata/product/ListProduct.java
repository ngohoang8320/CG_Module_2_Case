package repo.convertdata.product;

import entity.product.Product;
import repo.getsetdata.GetSetData;
import repo.getsetdata.product.GetSetProduct;

import java.util.ArrayList;
import java.util.List;

public class ListProduct {
    private GetSetData products = new GetSetProduct();
    private List<String> stringProductList = products.getData();
    private List<Product> productsList = new ArrayList<>();

    public ListProduct() {
        setProductList();
    }

    public List<Product> getProductList() {
        return productsList;
    }

    public void setProductList() {
        for (String prod : stringProductList) {
            String[] productInfor = prod.split(",");
            Product product = new Product();

            product.setId(Integer.parseInt(productInfor[0]));
            product.setName(productInfor[1]);
            product.setOriginPrice(Double.parseDouble(productInfor[2]));
            product.setPromotionalPricing(Double.parseDouble(productInfor[3]));
            product.setQuantity(Integer.parseInt(productInfor[4]));

            productsList.add(product);
        }
    }
}
