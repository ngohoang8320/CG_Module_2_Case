package repo.getsetdata.product;

public class FormatProductData {
    public static String formatted(int id,
                                   String name,
                                   double originPrice,
                                   double promotionalPricing,
                                   int quantity) {
        String entity = String.format("%d,%s,%.2f,%.2f,%d,%s",
                id,
                name,
                originPrice,
                promotionalPricing,
                quantity,
                "\n");

        return entity;
    }
}
