package model.discount;

import model.Customer;
import model.dao.Order;
import model.dao.Product;

/**
 * Created by Romain Roux on 11/11/16.
 */
public class DiscountFactory {
    /**
     * createOrderDiscount
     * instanciates an OrderDiscount object
     * @param o
     * @param modulo
     * @return OrderDiscount
     */
    public static OrderDiscount createOrderDiscount (Order o, int modulo) {
       return new OrderDiscount(o, modulo);
   }

    /**
     * createProductDiscount
     * instanciates a ProductDIscount object
     * @param p
     * @param percentage
     * @return UserDiscount
     */
    public static ProductDiscount createProductDiscount (Product p, double percentage) {
       return new ProductDiscount(p, percentage);
    }

    /**
     * createUserDiscount
     * instanciates a ProductDiscount object
     * @param u
     * @param level
     * @return ProductDiscount
     */
    public static UserDiscount createUserDiscount (Customer u, int level) {
       return new UserDiscount(u, level);
    }
}
