import java.util.ArrayList;

public class GamesList implements subject {
	
	private ArrayList<Observer> observers;
	
	private String name;
	private String price;
	private String gameID;
	private String sale;
	private String discount;
	
	GamesList(String n, String p, String ID){
		this.name = n;
		this.price = p;
		this.gameID = ID;
		sale = null;
		discount = null;
		observers = new ArrayList<Observer>();
	}

	public GamesList(String n, String p, String ID, String s, String d) {
		this.name = n;
		this.price = p;
		this.gameID = ID;
		this.sale = s;
		this.discount = d;
		observers = new ArrayList<Observer>();
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}
	
	public String getID() {
		return gameID;
	}

	public String getDiscount() {
		return discount;
	}

	public String getSale() {
		return sale;
	}

	@Override
	public void register(Observer c) {

		observers.add(c);
	}

	@Override
	public void unregister(Observer c) {

		int observerIndex = observers.indexOf(c);
		observers.remove(observerIndex);
	}

	@Override
	public void notifyObservers() {

		for (Observer observer : observers){
			observer.update(discount);
		}
	}

	public void setDiscount(String newDiscount) {
		discount = newDiscount;
		notifyObservers();
		
	}
	
}
