package discount;

import dao.IDAO;
import dao.Product;

import static localmaths.MathsUtil.calcPercentage;

/**
 * Created by immoskyl on 31/10/16.
 */
public class ProductDiscount implements IDAO{

    private double percentageDiscount;
    private Product product;

    public ProductDiscount(double percentageDiscount, Product product) {
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
