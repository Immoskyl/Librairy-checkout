import java.util.ArrayList;

public class Customer extends newUser implements Observer {

	
	private GamesList watchedGame;
	Customer(){
				
	
	}
	
	Customer(String name, String address, String email, String userId, String password){
		setName(name);
		setAddress(address);
		setEmail(email);
		setUserID(userId);
		setPassword(password);
	}

	public ArrayList<Customer> createCustomer(String name ,String address , String email, String userId, String password, ArrayList<Customer> customer){
		Customer c = new Customer(name.trim() , address.trim() , email.trim() , userId.trim(), password.trim());
		customer.add(c);
		return customer;
	}

	@Override
	public void update(String newDiscount) {

		System.out.println("Email has been sent to " + name + " of change in discount to :" + newDiscount);
	}
	
	public void addWatchGame(GamesList theGame){
		watchedGame = theGame;
	}
	
	public String getGameID(){
		if(watchedGame == null){
			return "0";
		}
		return watchedGame.getID(); 
	}
	
}