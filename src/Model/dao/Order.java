package Model.dao;

import java.util.HashMap;

/**
 * Created by immoskyl on 08/11/16.
 */
public class Order {

HashMap<Product, Integer> list;

    public HashMap<Product, Integer> getList() {
        return list;
    }

    public void setList(HashMap<Product, Integer> list) {
        this.list = list;
    }
}
