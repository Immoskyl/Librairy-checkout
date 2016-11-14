package Model.dao;

import Model.UserFactory;
import Model.newUser;

/**
 * Created by Romain Roux on 05/11/16.
 */
public class DAOTest {

    /**
     * selectUserTest()
     * Checks given user presence in the db
     * @param user
     * @return boolean success
     */
    public static boolean selectUserTest (newUser user) {
        DAO dao = DAO.getInstance();
        dao.createUser(user);
        return (dao.getUser(Integer.parseInt(user.getUserId())) == user);
    } //selectUserTest()

    /**
     * createUserTest()
     * create user in the db and checks for its presence
     * @return boolean success
     */
    public static boolean createUserTest () {
        DAO dao = DAO.getInstance();
        newUser user = UserFactory.makeNewUser("c", "jambon", "UL campus", "jambon@studentmail.ul.ie", "1", "thing");
        dao.createUser(user);
        return (selectUserTest(user));
    } //createUserTest()

    /**
     * createUsertest
     * create given user in the db and checks for its presence
     * @param user
     * @return boolean success
     */
    public static boolean createUserTest (newUser user) {
        DAO dao = DAO.getInstance();
        dao.createUser(user);
        return (selectUserTest(user));
    } //createUserTest()


    /**
     * deleteUserTest
     * deletes a given user from the db, and checks for its non absence
     * @param user
     * @return boolean success
     */
    public static boolean deleteUserTest (newUser user) { //must run createUserTest() before
        DAO dao = DAO.getInstance();
        dao.deleteUser(user);
        return (! selectUserTest(user));
    } //deleteUser()

    /**
     * createAndDeleteUserTest
     * creates then deletes an user in the db, and checks for its absence
     * @return boolean success
     */
    public static boolean createAndDeleteUserTest () {
        newUser user = UserFactory.makeNewUser("c", "jambon", "UL campus", "jambon@studentmail.ul.ie", "1", "thing");
        return (createUserTest(user) && deleteUserTest(user));
    } //createAndDeleteUserTest()

}