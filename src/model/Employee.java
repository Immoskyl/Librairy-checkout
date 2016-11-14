package model;

import java.util.ArrayList;

public class Employee extends newUser{

	Employee(){
		super();
	}

	Employee(String name ,String address , String email , String userId, String password){
		setName(name);
		setAddress(address);
		setEmail(email);
		setUserID(userId);
		setPassword(password);
	}

	public Employee createStaff(String name, String add, String e, String ID, String p) {
		Employee s = new Employee(name.trim() , getAddress().trim() , getEmail().trim() , getUserId().trim(), getPassword().trim());
		return s;
	}

	public GamesList addGame(String name, String price, String ID, String sale, String discount){
		GamesList gL = new GamesList(name.trim(), price.trim(), ID.trim(),  sale.trim(), discount.trim());
		return gL;		
	}
	
	public ArrayList<GamesList> removeGame(String name, String price, String ID, String sale, String discount, ArrayList<GamesList> game){
		GamesList gL = new GamesList(name.trim(), price.trim(), ID.trim(),  sale.trim(), discount.trim());
		game.remove(gL);
		return game;
	}
}
