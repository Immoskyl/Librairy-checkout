package model.dao;

import java.util.HashMap;

/**
 * Created by Romain Roux on 08/11/16.
 * Dummy implementation because I don't have Darran's implementation...
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
