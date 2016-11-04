package Model;

public class History {
	
	private String name;
	private String game;
	private String price;
	
//	History(String n, String g, Double c)
//	{
//		this.name = n;
//		this.game = g;
//		this.cost = c;
//	}
	
	public History(String n, String g, String p)
	{
		this.name = n;
		this.game = g;
		this.price = p;
	}

	public String getName()
	{
		return name;
	}
	
	public String getGame()
	{
		return game;
	}
	
	public String getPrice()
	{
		return price;
	}
}
