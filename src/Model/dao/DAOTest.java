package Model.dao;

import Model.UserFactory;
import Model.newUser;

/**
 * Created by immoskyl on 05/11/16.
 */
public class DAOTest {

    public static boolean selectUserTest () { //must run createUserTest() before
        DAO dao = DAO.getInstance();
        newUser user = UserFactory.makeNewUser("c", "jambon", "UL campus", "jambon@studentmail.ul.ie", "1", "thing");
        dao.createUser(user);

        return (dao.getUser(Integer.parseInt(user.getUserId())) == user);
    }


    public static boolean selectUserTest (newUser user) {
        DAO dao = DAO.getInstance();
        dao.createUser(user);
        return (dao.getUser(Integer.parseInt(user.getUserId())) == user);
    }

    public static boolean createUserTest () {
        DAO dao = DAO.getInstance();
        newUser user = UserFactory.makeNewUser("c", "jambon", "UL campus", "jambon@studentmail.ul.ie", "1", "thing");
        dao.createUser(user);
        return (selectUserTest(user));
    }

    public static boolean createUserTest (newUser user) {
        DAO dao = DAO.getInstance();
        dao.createUser(user);
        return (selectUserTest(user));
    }

    public static boolean deleteUserTest (newUser user) { //must run createUserTest() before
        DAO dao = DAO.getInstance();
        dao.deleteUser(user);
        return (! selectUserTest(user));
    }


    public static boolean createAndDeleteUserTest () { //doesnt work
        newUser user = UserFactory.makeNewUser("c", "jambon", "UL campus", "jambon@studentmail.ul.ie", "1", "thing");
        return (createUserTest(user) && deleteUserTest(user));
    }

    public static void main(String[] args) {
        //DAOTest.createAndDeleteUserTest();
    }
}