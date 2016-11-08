package Discount;

import DAO.IDAO;

/**
 * Created by immoskyl on 31/10/16.
 */
public class UserDiscount implements IDAO {

    private User user;
    private int level;

    public UserDiscount(User user, int level) {
        this.user = user;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double calculateDiscount() {
        int currentPoints = user.getPoints();
        double discount = 0.0;
        while (currentPoints >= level) {
           discount += level;
        }
        user.setPoints(currentPoints);
        return discount;
    }

}
