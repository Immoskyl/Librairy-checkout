package discount;

import dao.IDAO;
import dao.Product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        double discount = 0.0;
        HashMap<Product, Integer> list = order.getList();
        Iterator it = list.entrySet().iterator();
        Map.Entry entry;
        while (it.hasNext()) {
            entry = (Map.Entry)it.next();

            discount += (double)((Integer) entry.getValue() / itemModulo) * entry.getKey().getPrice();
        }
        return discount;
    }
}
