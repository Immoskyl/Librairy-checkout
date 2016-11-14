package Model.dao;

import Model.UserFactory;
import Model.newUser;

/**
 * Created by immoskyl on 05/11/16.
 */
public class DAOTest {

    public static boolean createUserTest () {
        DAO dao = DAO.getInstance();
        Person person = new Person();
        person.iD = 1;
        person.name = "sarah";
        person.pwd = "enzo";
        person.type = "Employee";
        dao.createUser(person);
        return true;
    }

    public static boolean deleteUserTest () { //doesnt work
        DAO dao = DAO.getInstance();
        Person person = new Person();
        person.iD = 1;
        person.name = "sarah";
        person.pwd = "enzo";
        person.type = "Employee";
        dao.deleteUser(person);
        return true;
    }

    public static boolean createAndDeleteUserTest () { //doesnt work
        if (createUserTest()) {
            if (deleteUserTest()) {
                return true;
            }
        }
        return false;
    }

    public static boolean selectUserTest () { //must run createUserTest() before
        DAO dao = DAO.getInstance();
        newUser user = UserFactory.makeNewUser("c", "jambon", "UL campus", "jambon@studentmail.ul.ie", 1, "thing");
        dao.createUser(user);

        return (dao.getUser(Integer.parseInt(user.getUserId())) == user)

    }

    public static void main(String[] args) {
        //DAOTest.createAndDeleteUserTest();
    }
}