package model;

public class Admin extends newUser
{
	
	Admin()
	{
		super();
	}

	public Admin(String name, String address, String email, String uID, String password) 
	{
		setName(name);
		setAddress(address);
		setEmail(email);
		setUserID(uID);
		setPassword(password);
	}

}
