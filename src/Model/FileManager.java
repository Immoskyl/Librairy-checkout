package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	
	public ArrayList<History> readHistory(String fileName)
	{
		ArrayList<History> history = new ArrayList <>();
		try
		{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String fileLines;
			String[] parts;
			while(br.ready())
			{
				fileLines = br.readLine();
				parts = fileLines.split(",");
				History aHistory = new History(parts[0], parts[1], parts[2]);
				history.add(aHistory);
			}
			br.close();
		}
		
		catch(IOException e)
		{
			System.out.println("Couldn't read file History");
		}
		return history;
	}
	
	public ArrayList<newUser> readStaff(String Filename)
	{
		ArrayList <newUser> staff = new ArrayList <newUser>();
		try{
	      FileReader fr = new FileReader(Filename);
	      BufferedReader br = new BufferedReader(fr);
	      String fileLines;
	      String[] parts;
	      while(br.ready())
	      {
	        fileLines = br.readLine();
	        parts = fileLines.split(",");
	        Employee aStaff = new Employee(parts[0], parts[1], parts[2], parts[3], parts[4]);
	        staff.add(aStaff);
	      }
	      br.close();
	      } 
	      catch(IOException e)
	     {
	        System.out.println("Couldnt Read file Staff");
	     }
	     return staff;
 	}
	
	public ArrayList<newUser> readCustomer(String Filename, ArrayList<GamesList> games)
	{
		ArrayList<newUser> customer = new ArrayList <newUser>();
		try{
	      FileReader fr = new FileReader(Filename);
	      BufferedReader br = new BufferedReader(fr);
	      String fileLines;
	      String[] parts;
	      while(br.ready())
	      {
	        fileLines = br.readLine();
	        parts = fileLines.split(",");
	        Customer aCustomer = new Customer(parts[0], parts[1], parts[2], parts[3], parts[4]);
	        if (parts[5] != "0")
	        {
	        	for (int i = 0; i < games.size(); i++){
	        		if (games.get(i).getID().equals(parts[5])){
	        			aCustomer.addWatchGame(games.get(i));
	        			//registering observer
	        			games.get(i).register(aCustomer);
	        		}
	        	}
	        }
	        customer.add(aCustomer);
	        
	      }
	      br.close();
	      } 
	      catch(IOException e)
	     {
	        System.out.println("Couldnt Read file Customer");
	     }
	     return customer;
 	}	
	
	@SuppressWarnings("unused")
	public ArrayList<newUser> readAdministrator(String Filename)
	{
		ArrayList <newUser> admin = new ArrayList <newUser>();
		try{
	      FileReader fr = new FileReader(Filename);
	      BufferedReader br = new BufferedReader(fr);
	      String fileLines;
	      String[] parts;
	      while(br.ready())
	      {
	    	//calling factory for admin
	    	UserFactory userFactory = new UserFactory();
	    	newUser aUser = null;
	        fileLines = br.readLine();
	        parts = fileLines.split(",");
	        newUser aAdministrator = new Admin(parts[0], parts[1], parts[2], parts[3], parts[4]);
	        admin.add(aAdministrator);
	      }
	      br.close();
	      } 
	      catch(IOException e)
	     {
	        System.out.println("Couldnt Read file Administrator");
	     }
	     return admin;
 	}
	

	public ArrayList<GamesList> readGames(String Filename)
	{
		ArrayList <GamesList> games = new ArrayList <GamesList>();
		try{
	      FileReader fr = new FileReader(Filename);
	      BufferedReader br = new BufferedReader(fr);
	      String fileLines;
	      String[] parts;
	      while(br.ready())
	      {
	        fileLines = br.readLine();
	        parts = fileLines.split(",");
	        GamesList aGame = new GamesList (parts[0], parts[1], parts[2], parts[3], parts[4]);
	        games.add(aGame);
	      }
	      br.close();
	      } 
	      catch(IOException e)
	     {
	        System.out.println("Couldnt Read file GamesList");
	     }
	     return games;
 	}
	
	public void writeCustomers(String filename, ArrayList<newUser> customer )
	  {
	    try
	    {
	      FileWriter fw = new FileWriter(filename);
	      BufferedWriter bw = new BufferedWriter(fw);
	      for(int i = 0; i < customer.size(); i++){
	 	   bw.write(customer.get(i).getName() + "," + customer.get(i).getAddress() + "," + customer.get(i).getEmail() + "," + customer.get(i).getUserId() + "," + customer.get(i).getPassword() + "," + ((Customer) customer.get(i)).getGameID());
	 	   bw.newLine();
	      }
	      bw.close();
	    }
	    catch(IOException e)
	    {
	      System.out.println("Couldnt Write file Customer");
	    }
	  }
	
	public void writeStaff(String filename, ArrayList<newUser> staff )
	  {
	    try
	    {
	      FileWriter fw = new FileWriter(filename);
	      BufferedWriter bw = new BufferedWriter(fw);
	      for(int i = 0; i < staff.size(); i++){
	 	   bw.write(staff.get(i).getName() + "," + staff.get(i).getAddress() + "," + staff.get(i).getEmail() + "," + staff.get(i).getUserId() + "," + staff.get(i).getPassword());
	 	   bw.newLine();
	      }
	      bw.close();
	    }
	    catch(IOException e)
	    {
	      System.out.println("Couldnt Write file Staff");
	    }
	  }
	
 	public void writeAdmin(String filename, ArrayList<newUser> admin )
	  {
	    try
	    {
	      FileWriter fw = new FileWriter(filename);
	      BufferedWriter bw = new BufferedWriter(fw);
	      for(int i = 0; i < admin.size(); i++){
	 	   bw.write(admin.get(i).getName() + "," + admin.get(i).getAddress() + "," + admin.get(i).getEmail() + "," + admin.get(i).getUserId() + "," + admin.get(i).getPassword());
	 	   bw.newLine();
	      }
	      bw.close();
	    }
	    catch(IOException e)
	    {
	      System.out.println("Couldnt Write file Admin");
	    }
	  }
 	
 	public void writeGames(String filename, ArrayList<GamesList> game )
	  {
	    try
	    {
	      FileWriter fw = new FileWriter(filename);
	      BufferedWriter bw = new BufferedWriter(fw);
	      for(int i = 0; i < game.size(); i++){
	 	   bw.write(game.get(i).getName() + "," + game.get(i).getPrice() + "," + game.get(i).getID() + "," + game.get(i).getSale() + "," + game.get(i).getDiscount());
	 	   bw.newLine();
	      }
	      bw.close();
	    }
	    catch(IOException e)
	    {
	      System.out.println("Couldnt Write file Admin");
	    }
	  }
 	
 	public void writeHistory(String filename, ArrayList<History> history)
 	{
 		try
 		{
 			FileWriter fw = new FileWriter(filename);
 			BufferedWriter bw = new BufferedWriter(fw);
 			for (int i =0; i < history.size(); i++)
 			{
 				bw.write(history.get(i).getName() + "," + history.get(i).getGame() + "," + history.get(i).getPrice());
 				bw.newLine();
 			}
 			bw.close();
 		}
 		catch(IOException e)
 		{
 			System.out.println("Couldnt Write file History");
 		}
 	}
}

