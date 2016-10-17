public class newUserFactory {

	@SuppressWarnings("unused")
	public static newUser makeNewUser(String type, String name ,String address , String email , String userId, String password){
		newUser aUser = null;
		
		if(type.equals("s")){
			return new Staff(name, address, email, userId, password);
		}
		else if(type.equals("c")){
			return new Customer(name, address, email, userId, password);
		}
		else if(type.equals("a")){
			return new Administrator(name, address, email, userId, password);
		}
		return null;
	}
}
