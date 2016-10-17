public class Administrator extends newUser{
	
	Administrator(){
		super();
	}

	public Administrator(String name ,String address , String email, String userId, String password){
		setName(name);
		setAddress(address);
		setEmail(email);
		setUserID(userId);
		setPassword(password);
	}
}