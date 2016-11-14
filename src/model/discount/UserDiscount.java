package model.discount;

import model.Customer;

import static util.Maths.round;

/**
 * Created by Romain Roux on 31/10/16.
 *
 */
public class UserDiscount implements IDiscount {

    private Customer user;
    private int pointsModulo;

    public UserDiscount(Customer user, int level) {
        this.user = user;
        this.pointsModulo = level;
    }

    public int getPointsModulo() {
        return pointsModulo;
    }

    public void setlevel(int level) {
        this.pointsModulo = level;
    }

    public double calculateDiscount() {
        int userPoints = user.getPoints();
        int rawDiscount = userPoints - userPoints % pointsModulo; // rawDiscount >= 0

        user.setPoints(userPoints % pointsModulo);
        return round((double) rawDiscount, 2);
    }

}
