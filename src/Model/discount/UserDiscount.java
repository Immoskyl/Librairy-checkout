package discount;

import Model.dao.IDAO;
import Model.dao.User;

import static util.Maths.round;

/**
 * Created by immoskyl on 31/10/16.
 */
public class UserDiscount implements IDAO {

    private User user;
    private int pointsModulo;

    public UserDiscount(User user, int level) {
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