package model;

public class UserFactory {

	@SuppressWarnings("unused")
	public static newUser makeNewUser(String type, String name, String address, String email, String uID, String password){
		newUser aUser = null;
		
		if(type.equals("a"))
		{
			return new Admin(name, address, email, uID, password);
		}
		
		if(type.equals("e"))
		{
			return new Employee(name, address, email, uID, password);
		}
		
		else if(type.equals("c"))
		{
			return new Customer(name, address, email, uID, password);
		}
		
		return null;		
	}
	
}
