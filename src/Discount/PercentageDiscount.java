package Discount;

/**
 * Created by immoskyl on 31/10/16.
 */
public class PercentageDiscount implements ProductDiscount {

    private int priority;

    public PercentageDiscount(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double calculateDiscount(double price) {
        return (double)((int)(price * 100.0) / 3)/100.0;
    }
}
