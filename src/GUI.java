import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings({ "unused", "serial" })
public class GUI extends JFrame {

	private JButton exit;
	private JButton back;
	private JFrame mainFrame;
	private ExitButtonHandler eButtonHandel;
	private BackButtonHandler bButtonHandel;
	private ArrayList<GamesList> games = new ArrayList<GamesList>();
	private ArrayList<newUser> admin = new ArrayList<newUser>();
	private ArrayList<newUser> customer = new ArrayList<newUser>();
	private ArrayList<newUser> staff = new ArrayList<newUser>();
	private newUser aAdmin;
	private GamesList aGame; 
	private newUser aCustomer;
	private newUser aStaff;

	public newUserFactory userFactory = new newUserFactory();

	public void login(){
		startUp();/** the array lists are read in from the file manipulation class */
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}/** sets the panel to mimic the operating system, this is repeated in all the panels */
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame = new JFrame("Please choose");
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		JPanel fiveOptionPanel = new JPanel();
		mainFrame.add(fiveOptionPanel);

		JButton register = new JButton("Register");
		fiveOptionPanel.add(register);
		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				registerPanel();
			}
		});
		JButton userButton = new JButton("Customer");
		fiveOptionPanel.add(userButton);
		userButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				userLogInPanel();
			}
		});
		JButton staffButton = new JButton("Staff");
		fiveOptionPanel.add(staffButton);
		staffButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				staffLogInPanel();
			}
		});
		JButton adminButton = new JButton("Admin");
		fiveOptionPanel.add(adminButton);
		adminButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				adminLogInPanel();
			}
		});
		JButton browseButton = new JButton("Browse");
		fiveOptionPanel.add(browseButton);
		browseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				browsePanel();
			}


		});
		mainFrame.setLayout(new GridLayout(6,0));
		mainFrame.add(register);
		mainFrame.add(userButton);
		mainFrame.add(staffButton);
		mainFrame.add(adminButton);
		mainFrame.add(browseButton);
		mainFrame.setSize(350, 350);
	}

	public void registerPanel(){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		JLabel name = new JLabel ("Name: ", SwingConstants.RIGHT);
		JLabel addr = new JLabel ("Address: ", SwingConstants.RIGHT);
		JLabel email = new JLabel ("E-mail: ", SwingConstants.RIGHT);
		JLabel userID = new JLabel ("User-ID: ", SwingConstants.RIGHT);
		JLabel pass = new JLabel ("Password: ", SwingConstants.RIGHT);
		final JTextField na = new JTextField();
		final JTextField ad = new JTextField();
		final JTextField em = new JTextField();
		final JTextField ui = new JTextField();
		final JTextField pa = new JTextField();
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener (){
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e){
				// calling the factory method
				newUser aUser = null;
				aUser = userFactory.makeNewUser("c", na.getText(),ad.getText(), em.getText(), ui.getText(), pa.getText());
				customer.add(aUser);
			}
		});

		mainFrame.setLayout(new GridLayout(6 , 0));
		mainFrame.setSize(350 , 350);
		mainFrame.add(name);
		mainFrame.add(na);
		mainFrame.add(addr);
		mainFrame.add(ad);
		mainFrame.add(email);
		mainFrame.add(em);
		mainFrame.add(userID);
		mainFrame.add(ui);
		mainFrame.add(pass);
		mainFrame.add(pa);
		mainFrame.add(enter);
		back = new JButton("Back");
		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		mainFrame.add(exit);		
	}

	public void userLogInPanel(){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);

		JLabel iD = new JLabel ("UserID: ", SwingConstants.RIGHT);
		JLabel passw = new JLabel ("Password: ", SwingConstants.RIGHT);
		final JTextField userId = new JTextField();
		final JTextField pass = new JPasswordField();
		JButton enter = new JButton("Enter");
		/** Check to see if the user id and password is in the customers array */
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/** Searching the array list for the id and password*/
				for(int i = 0; i < customer.size(); i++)
				{
					String aUser, aPassword, userName = userId.getText(), password = pass.getText();
					aUser = customer.get(i).getUserId();
					aPassword = customer.get(i).getPassword();
					/** If it is found we move onto the customer options panel specific for this customer */
					if(userName.matches(aUser) && password.matches(aPassword))
					{
						aCustomer = customer.get(i); /** the customer is identified and declared globaly */
						custOptionsPanel();
					}
				}

			}
		});
		/** exit button handeler */
		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		/** Setting up the panel*/
		mainFrame.setLayout(new GridLayout(4, 4));
		mainFrame.add(iD);
		mainFrame.add(userId);
		mainFrame.add(passw);
		mainFrame.add(pass);
		mainFrame.add(enter);
		mainFrame.add(exit);
		mainFrame.setSize(350 , 350);
	}

	public void custOptionsPanel(){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);

		mainFrame.setLayout(new GridLayout(6, 0));
		mainFrame.setSize(350, 350);
		JButton browse = new JButton("Browse");
		mainFrame.add(browse);

		browse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				browsePanel();
			}

		});
		JButton cart = new JButton("Create shopping cart");
		mainFrame.add(cart);
		cart.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				shoppingCart();
			}
		});
		/** exit procedure */
		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		mainFrame.add(exit);
		/** panel formatting */
		mainFrame.pack();
	}

	public void shoppingCart(){
		startUp();/** the array lists are read in from the file manipulation class */
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}/** sets the panel to mimic the operating system, this is repeated in all the panels */
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame = new JFrame("Cart Options");
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		JPanel OptionPanel = new JPanel();
		mainFrame.add(OptionPanel);
		final JTextField name = new JTextField();	

		JButton addC = new JButton("add to cart");
		OptionPanel.add(addC);
		addC.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		JButton removeC = new JButton("remove from cart");
		OptionPanel.add(removeC);
		removeC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JButton buy = new JButton("Buy");
		OptionPanel.add(buy);
		buy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		mainFrame.setLayout(new GridLayout(6,0));
		mainFrame.add(name);
		mainFrame.add(addC);
		mainFrame.add(removeC);
		mainFrame.add(buy);

		mainFrame.setSize(350, 350);
	}

	public void staffLogInPanel(){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);

		JLabel iD = new JLabel ("UserID: ", SwingConstants.RIGHT);
		JLabel passw = new JLabel ("Password: ", SwingConstants.RIGHT);
		final JTextField userId = new JTextField();
		final JTextField pass = new JPasswordField();
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i = 0 ; i < staff.size(); i++){
					String aUser, aPassword, userName = userId.getText(), password = pass.getText();
					aUser = staff.get(i).getUserId();
					aPassword = staff.get(i).getPassword();
					if(userName.matches(aUser) && password.matches(aPassword))
					{
						aStaff = staff.get(i);
						staffOptionsPanel();
					}
				}

			}
		});

		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		mainFrame.setLayout(new GridLayout(4, 4));
		mainFrame.setSize(350,350);
		mainFrame.add(iD);
		mainFrame.add(userId);
		mainFrame.add(passw);
		mainFrame.add(pass);
		mainFrame.add(enter);
		mainFrame.add(exit);
		mainFrame.pack();
	}

	public void staffOptionsPanel(){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		JButton addGame = new JButton("Add a new game");
		mainFrame.add(addGame);
		addGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addGame();
			}
		});

		JButton remove = new JButton("Remove a game");
		mainFrame.add(remove);
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				removeGame();
			}
		});
		mainFrame.setLayout(new GridLayout(5,0));
		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		mainFrame.add(exit);
		mainFrame.setSize(350 , 350);
		mainFrame.pack();

	}

	protected void addGame() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		JLabel name = new JLabel ("Name: ", SwingConstants.RIGHT);
		JLabel price = new JLabel ("Price: ", SwingConstants.RIGHT);
		JLabel gameID = new JLabel ("Game ID: ", SwingConstants.RIGHT);
		JLabel sale = new JLabel ("Sale: ", SwingConstants.RIGHT);
		JLabel discount = new JLabel ("Discount: ", SwingConstants.RIGHT);
		final JTextField na = new JTextField();
		final JTextField pr = new JTextField();
		final JTextField id = new JTextField();
		final JTextField sa = new JTextField();
		final JTextField di = new JTextField();
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener() 
		{


			public void actionPerformed(ActionEvent e) 
			{							
				GamesList newGame = new GamesList(na.getText(), pr.getText(), id.getText(), sa.getText(), di.getText());
				games.add(newGame);
			}

		});

		mainFrame.setLayout(new GridLayout(6,0));
		mainFrame.setSize(500, 500);
		mainFrame.add(name);
		mainFrame.add(na);
		mainFrame.add(price);
		mainFrame.add(pr);
		mainFrame.add(gameID);
		mainFrame.add(id);
		mainFrame.add(sale);
		mainFrame.add(sa);
		mainFrame.add(discount);
		mainFrame.add(di);
		mainFrame.add(enter);
		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		mainFrame.add(exit);
		mainFrame.pack();

	}

	protected void removeGame() {

	}

	public void adminLogInPanel(){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);

		JLabel iD = new JLabel ("UserID: ", SwingConstants.RIGHT);
		JLabel passw = new JLabel ("Password: ", SwingConstants.RIGHT);
		final JTextField userId = new JTextField();
		final JTextField pass = new JPasswordField();
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i = 0; i < admin.size(); i++)
				{
					String aUser, aPassword, userName = userId.getText(), password = pass.getText();
					aUser = admin.get(i).getUserId();
					aPassword = admin.get(i).getPassword();
					if(userName.matches(aUser) && password.matches(aPassword))
					{
						aAdmin = admin.get(i);
						adminOptionsPanel();
					}
				}

			}
		});

		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		mainFrame.setLayout(new GridLayout(4, 4));
		mainFrame.add(iD);
		mainFrame.add(userId);
		mainFrame.add(passw);
		mainFrame.add(pass);
		mainFrame.add(enter);
		mainFrame.add(exit);
		mainFrame.setSize(350 , 350);
		mainFrame.pack();
	}

	public void adminOptionsPanel(){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		JButton addStaff = new JButton("Add a new Staff member");
		mainFrame.add(addStaff);
		addStaff.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addStaff();
			}
		});

		JButton addSale = new JButton("Add a new sale");
		mainFrame.add(addSale);
		addSale.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				adminSale();
			}
		});
		JButton adddis = new JButton("Add a new discount");
		mainFrame.add(adddis);
		adddis.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				adminDis();
			}
		});
		mainFrame.setLayout(new GridLayout(5,0));
		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		mainFrame.add(exit);
		mainFrame.setSize(350, 350);
		mainFrame.pack();	
	}

	protected void adminDis() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		JLabel gameID = new JLabel ("Game ID: ", SwingConstants.RIGHT);
		JLabel discount = new JLabel ("Discount: ", SwingConstants.RIGHT);
		final JTextField gID = new JTextField();
		final JTextField dis = new JTextField();
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				String id = gID.getText();
				for (int i = 0; i < games.size(); i++){
					if (id.equals(games.get(i).getID())){
						games.get(i).setDiscount(dis.getText());
					}
				}
			}
		});
		
		mainFrame.setLayout(new GridLayout(3 , 0));
		mainFrame.setSize(350 , 350);
		mainFrame.add(gameID);
		mainFrame.add(gID);
		mainFrame.add(discount);
		mainFrame.add(dis);
		mainFrame.add(enter);
		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		mainFrame.add(exit);
	}

	protected void adminSale() {

	}

	public void addStaff(){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		JLabel name = new JLabel ("Name: ", SwingConstants.RIGHT);
		JLabel addr = new JLabel ("Address: ", SwingConstants.RIGHT);
		JLabel email = new JLabel ("E-mail: ", SwingConstants.RIGHT);
		JLabel userID = new JLabel ("User-ID: ", SwingConstants.RIGHT);
		JLabel pass = new JLabel ("Password: ", SwingConstants.RIGHT);
		final JTextField na = new JTextField();
		final JTextField ad = new JTextField();
		final JTextField em = new JTextField();
		final JTextField ui = new JTextField();
		final JTextField pa = new JTextField();
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener (){
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e){
				//calling the factory method
				newUser aUser = null;
				aUser = userFactory.makeNewUser("s", na.getText(),ad.getText(), em.getText(), ui.getText(), pa.getText());
				staff.add(aUser);
			}
		});

		mainFrame.setLayout(new GridLayout(6 , 0));
		mainFrame.setSize(350 , 350);
		mainFrame.add(name);
		mainFrame.add(na);
		mainFrame.add(addr);
		mainFrame.add(ad);
		mainFrame.add(email);
		mainFrame.add(em);
		mainFrame.add(userID);
		mainFrame.add(ui);
		mainFrame.add(pass);
		mainFrame.add(pa);
		mainFrame.add(enter);
		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		mainFrame.add(exit);		
	}

	private void browsePanel() 
	{
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		mainFrame.getContentPane().removeAll();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);


		final JTextArea GamesList = new JTextArea("");/** creating a text area where the results will be displayed depending on what options have been picked */

		JButton checkGamesList = new JButton("Browse the game list");
		mainFrame.add(checkGamesList);

		checkGamesList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String list = "";
				for(int i = 0; i < games.size(); i++)
				{
					System.out.println(games.get(i).getName());
					list += games.get(i).getName() + ": " + games.get(i).getPrice() +  ": " + games.get(i).getID() + "  \n";
				}
				GamesList.setText(list);/** calling method in the GamesList class*/
			}

		});

		mainFrame.setLayout(new GridLayout(6 , 0));
		mainFrame.setSize(350 , 350);
		exit = new JButton("Exit");
		eButtonHandel = new ExitButtonHandler();
		exit.addActionListener(eButtonHandel);
		mainFrame.add(exit);
	}

	public void startUp(){
		FileManager manage = new FileManager();
		games = manage.readGames("GamesList.csv");
		customer = manage.readCustomer("Customers.csv", games);
		staff = manage.readStaff("Staff.csv");
		admin = manage.readAdministrator("Administrator.csv");
	}

	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent g)
		{
			closeDown();
			System.exit(0);
		}
	}
	
	public class BackButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent g)
		{
			back.getClass();
		}
	}

	public void closeDown(){
		FileManager manage = new FileManager();
		manage.writeCustomers("Customers.csv", customer);
		manage.writeStaff("Staff.csv", staff);
		manage.writeAdmin("Administrator.csv", admin);
		manage.writeGames("GamesList.csv", games);
	}
}