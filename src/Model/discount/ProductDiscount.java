package Model.discount;

import Model.dao.Product;

import static util.Maths.calcPercentage;

/**
 * Created by immoskyl on 31/10/16.
 */
public class ProductDiscount implements IDiscount {

    private double percentageDiscount;
    private Product product;

    public ProductDiscount(Product product, double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
        this.product = product;
    }

    public double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public double calculateDiscount() {
        return calcPercentage(product.getPrice(), percentageDiscount); //keeps only 2 digit after coma
    }
}
