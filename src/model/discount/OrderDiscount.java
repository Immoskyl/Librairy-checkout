package model.discount;

import model.dao.Order;
import model.dao.Product;

import java.util.Map;

/**
 * Created by Romain Roux on 31/10/16.
 * dummy class because I dont have Darran's implementation...
 */
public class OrderDiscount implements IDiscount {

    private Order order;
    private int itemModulo;

    public OrderDiscount(Order order, int itemModulo) {
        this.order = order;
        this.itemModulo = itemModulo;
    }

    public int getItemModulo() {
        return itemModulo;
    }

    public void setItemModulo(int itemModulo) {
        this.itemModulo = itemModulo;
    }


    public double calculateDiscount() {
        double discount = 0.0;
        for (Map.Entry<Product, Integer> entry : order.getList().entrySet()) {
            discount += (double)(entry.getValue()/ itemModulo) * entry.getKey().getPrice();
        }
        return discount;
    }
}
