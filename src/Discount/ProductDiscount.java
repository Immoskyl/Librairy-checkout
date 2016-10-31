package Discount;

/**
 * Created by immoskyl on 31/10/16.
 */
public abstract class ProductDiscount extends Discount {

    private int priority;


    public abstract double calculateDiscount(double price);
}
