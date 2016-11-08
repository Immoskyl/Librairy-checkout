package Discount;

import DAO.IDAO;
import DAO.Product;

import java.util.HashMap;
import java.util.List;

/**
 * Created by immoskyl on 31/10/16.
 */
public class OrderDiscount implements IDAO {

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
        HashMap<Product, Integer> list = order.getList();
       for ()

    }
}
