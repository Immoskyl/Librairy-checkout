package model.discount;

import model.Customer;
import model.dao.*;

import java.util.HashMap;

/**
 * Created by Romain Roux on 11/11/16.
 */
public class DiscountTest {

    /**
     * orderDiscountTest
     * creates an Order packaging some Products, applies a discount, anb checks for valid discount computation
     * @return boolean success
     */
    public static boolean orderDiscountTest () {
        HashMap<Product, Integer> list = new HashMap<>();
        list.put(new Product(1,"book thing", "random product", 1.32), 4); //discount 1.32
        list.put(new Product(2,"dvd thing", "random product", 2.05), 3); //discount 0.0
        list.put(new Product(3,"game thing", "random product", 3.20), 1); //discount 0.0
        list.put(new Product(4,"board game thing", "because why not", 2.00), 9); //discount 4.0
        Order order = new Order();
        order.setList(list);

        OrderDiscount orderDiscount = DiscountFactory.createOrderDiscount(order, 4);
        return orderDiscount.calculateDiscount() == 5.32; //1.32 + 4.0 = 5.32
    } //orderDiscountTest()

    /**
     * productDiscountTest
     * creates a Product object, applies a discount, and checks for valid discount computation
     * @return
     */
    public static boolean productDiscountTest () {
       Product p = new Product(4,"board game thing", "raise it to the next level", 40.21);
        ProductDiscount productDiscount = new ProductDiscount(p, 21.1);

        return productDiscount.calculateDiscount() == 31.73; //40.21 - 8.48 = 31.73
    } //productDiscountTest()

    /**
     * userDIscountTest
     * creates a Customer, sets its discount points, applies a discount, and checks for valid discount computation
     * @return boolean success
     */
    public static boolean userDiscountTest() {
        Customer user = new Customer();
        user.setPoints(1000);
        UserDiscount userDiscount = new UserDiscount(user, 429);

        return (userDiscount.calculateDiscount() == 858 // 1000 / 429 = 2; 429*2 = 858
            && user.getPoints() == 142); // 1000 - 858 = 142
    } //userDiscountTest()

    /*test
    public static void main(String[] args) {
        System.out.println("orderDiscount is fine: " + DiscountTest.orderDiscountTest());
        System.out.println("productDiscount is fine: " + DiscountTest.productDiscountTest());
        System.out.println("userDiscount is fine: " + DiscountTest.userDiscountTest());
    }
     */
}
