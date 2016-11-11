package Model.discount;

import Model.dao.Order;
import Model.dao.Product;
import Model.dao.User;

/**
 * Created by immoskyl on 11/11/16.
 */
public class DiscountFactory {

   public static OrderDiscount createOrderDiscount (Order o, int modulo) {
       return new OrderDiscount(o, modulo);
   }

   public static ProductDiscount createProductDiscount (Product p, double percentage) {
       return new ProductDiscount(p, percentage);
   }

   public static UserDiscount createUserDiscount (User u, int level) {
       return new UserDiscount(u, level);
   }
}
