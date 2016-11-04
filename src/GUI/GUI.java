package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

import Model.FileManager;
import Model.GamesList;
import Model.UserFactory;
import Model.newUser;
@SuppressWarnings("unused")
public class GUI {

		private JButton exit;
//		private JButton back;
		private JFrame mainFrame;
		private ExitButtonHandler eButtonHandel;
//		private BackButtonHandler bButtonHandel;
		private ArrayList<GamesList> games = new ArrayList<GamesList>();
		private ArrayList<newUser> admin = new ArrayList<newUser>();
		private ArrayList<newUser> customer = new ArrayList<newUser>();
		private ArrayList<newUser> employee = new ArrayList<newUser>();
		private newUser aAdmin;
		private GamesList aGame; 
		private newUser aCustomer;
		private newUser aEmployee;

		public UserFactory userFactory = new UserFactory();

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
			JButton employeeButton = new JButton("Employee");
			fiveOptionPanel.add(employeeButton);
			employeeButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					employeeLogInPanel();
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
			mainFrame.setLayout(new GridLayout(7,0));
			mainFrame.add(register);
			mainFrame.add(userButton);
			mainFrame.add(employeeButton);
			mainFrame.add(adminButton);
			mainFrame.add(browseButton);
			mainFrame.setSize(400, 400);
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
			
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					login();
				}
				
			});

			mainFrame.setLayout(new GridLayout(6 , 0));
			mainFrame.setSize(400, 400);
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
			mainFrame.add(cancel);
//			back = new JButton("Back");
//			exit = new JButton("Exit");
//			eButtonHandel = new ExitButtonHandler();
//			exit.addActionListener(eButtonHandel);
//			mainFrame.add(exit);		
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
			
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					login();
				}
				
			});
			
			/** exit button handeler */
			exit = new JButton("Exit");
			eButtonHandel = new ExitButtonHandler();
			exit.addActionListener(eButtonHandel);
			/** Setting up the panel*/
			mainFrame.setLayout(new GridLayout(3, 0));
			mainFrame.add(iD);
			mainFrame.add(userId);
			mainFrame.add(passw);
			mainFrame.add(pass);
			mainFrame.add(enter);
			mainFrame.add(exit);
			mainFrame.setSize(400, 400);
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
			mainFrame.setSize(400, 400);
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
			JButton removeC = new JButton("Remove from cart");
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

			mainFrame.setSize(400, 400);
		}

		public void employeeLogInPanel(){
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
					for(int i = 0 ; i < employee.size(); i++){
						String aUser, aPassword, userName = userId.getText(), password = pass.getText();
						aUser = employee.get(i).getUserId();
						aPassword = employee.get(i).getPassword();
						if(userName.matches(aUser) && password.matches(aPassword))
						{
							aEmployee = employee.get(i);
							staffOptionsPanel();
						}
					}

				}
			});

			exit = new JButton("Exit");
			eButtonHandel = new ExitButtonHandler();
			exit.addActionListener(eButtonHandel);
			mainFrame.setLayout(new GridLayout(3,2));
			mainFrame.setSize(400, 400);
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

			JButton reviewCustomer = new JButton("View Customer");
			mainFrame.add(reviewCustomer);
			reviewCustomer.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					reviewCustomer();
				}
			});
			
			JButton remove = new JButton("Remove a game");
			mainFrame.add(remove);
			remove.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					removeGame();
				}
			});
			
			JButton modify = new JButton("Modify a game");
			mainFrame.add(modify);
			modify.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					modifyGame();
				}
			});
			
			JButton adddis = new JButton("Add a new discount");
			mainFrame.add(adddis);
			adddis.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					addDis();
				}
			});
			
			JButton removeDis = new JButton("Remove a discount");
			mainFrame.add(removeDis);
			removeDis.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					removeDis();
				}
			});
			
			JButton cancel = new JButton("Log Out");
			mainFrame.add(cancel);
			cancel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					login();
				}
				
			});
			
			mainFrame.setLayout(new GridLayout(5,0));
			exit = new JButton("Exit");
			eButtonHandel = new ExitButtonHandler();
			exit.addActionListener(eButtonHandel);
			mainFrame.add(exit);
			mainFrame.setSize(400, 400);
			mainFrame.pack();

		}
		
		protected void reviewCustomer()
		{
			try
			{
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
			final JTextField na = new JTextField();
			JButton enter = new JButton("Enter");
			enter.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
				
			});
		}

		protected void addGame() 
		{
			try
			{
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
			
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					staffOptionsPanel();
				}
				
			});

			mainFrame.setLayout(new GridLayout(7,2));
			mainFrame.setSize(400, 400);
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
			mainFrame.add(cancel);
//			exit = new JButton("Exit");
//			eButtonHandel = new ExitButtonHandler();
//			exit.addActionListener(eButtonHandel);
//			mainFrame.add(exit);
			mainFrame.pack();

		}

		protected void removeGame() 
		{
			try
			{
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
			
			JLabel name = new JLabel ("Name: ", SwingConstants.LEADING);
			JTextField nameOfGame = new JTextField();
			JButton enter = new JButton("Enter");
			JButton cancel = new JButton("Cancel");
			enter.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{							
					File inputFile = new File("GamesList.csv");
					File tempFile = new File("tempGameList.csv");
					
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new FileReader(inputFile));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					BufferedWriter writer = null;
					try {
						writer = new BufferedWriter(new FileWriter(tempFile));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					String currentLine;
					String gameRemove = nameOfGame.getText();
//					System.out.println(gameRemove);
					
					try {
						while((currentLine = reader.readLine()) != null)
						{
							// trim newline when comparing with lineToRemove 
							String trimmedLine = currentLine.trim();
							if(trimmedLine.equals(gameRemove)) continue;
							writer.write(currentLine + System.getProperty("line.separator"));
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						writer.close();
						reader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					boolean successful = tempFile.renameTo(inputFile); 	
				}

			});
			
			cancel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					staffOptionsPanel();
				}
				
			});
			
			mainFrame.setLayout(new GridLayout(6,0));
			mainFrame.setSize(400, 400);
			mainFrame.add(name);
			mainFrame.add(nameOfGame);
			mainFrame.add(enter);
			mainFrame.add(cancel);
			exit = new JButton("Exit");
			eButtonHandel = new ExitButtonHandler();
			exit.addActionListener(eButtonHandel);
			mainFrame.add(exit);
			mainFrame.pack();
			
		}
		
		protected void modifyGame(){
			
		}
		
		protected void addDis() {
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
			
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					staffOptionsPanel();
				}
				
			});
			
			mainFrame.setLayout(new GridLayout(3 , 0));
			mainFrame.setSize(400, 400);
			mainFrame.add(gameID);
			mainFrame.add(gID);
			mainFrame.add(discount);
			mainFrame.add(dis);
			mainFrame.add(enter);
			mainFrame.add(cancel);
			exit = new JButton("Exit");
			eButtonHandel = new ExitButtonHandler();
			exit.addActionListener(eButtonHandel);
			mainFrame.add(exit);
		}
		
		protected void removeDis(){
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
//			JLabel discount = new JLabel ("Discount: ", SwingConstants.RIGHT);
			final JTextField gID = new JTextField();
//			final JTextField dis = new JTextField();
			JButton enter = new JButton("Enter");
			enter.addActionListener(new ActionListener (){
				public void actionPerformed(ActionEvent e){
					String id = gID.getText();
					for (int i = 0; i < games.size(); i++){
						if (id.equals(games.get(i).getID())){
							games.get(i).setDiscount("0%");
						}
					}
				}
			});
			
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					staffOptionsPanel();
				}
				
			});
			
			mainFrame.setLayout(new GridLayout(3 , 0));
			mainFrame.setSize(400, 400);
			mainFrame.add(gameID);
			mainFrame.add(gID);
//			mainFrame.add(discount);
//			mainFrame.add(dis);
			mainFrame.add(enter);
			mainFrame.add(cancel);
			exit = new JButton("Exit");
			eButtonHandel = new ExitButtonHandler();
			exit.addActionListener(eButtonHandel);
			mainFrame.add(exit);
		}

		public void adminLogInPanel()
		{
			try
			{
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
				public void actionPerformed(ActionEvent e)
				{
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
			mainFrame.setLayout(new GridLayout(3, 0));
			mainFrame.add(iD);
			mainFrame.add(userId);
			mainFrame.add(passw);
			mainFrame.add(pass);
			mainFrame.add(enter);
			mainFrame.add(exit);
			mainFrame.setSize(400, 400);
			mainFrame.pack();
		}

		public void adminOptionsPanel()
		{
			try
			{
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
			addStaff.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					addStaff();
				}
			});
			
			JButton removeStaff = new JButton("Remove a Staff member");
			mainFrame.add(removeStaff);
			removeStaff.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					removeStaff();
				}
			});
			
			JButton checkLateFees = new JButton("Check Customer late fees");
			mainFrame.add(checkLateFees);
			checkLateFees.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					checkLateFees();
				}
			});

			JButton addCustomer = new JButton("Add a new Customer");
			mainFrame.add(addCustomer);
			addCustomer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					addCustomer();
				}
			});
			
			JButton removeCustomer = new JButton("Remove a Customer");
			mainFrame.add(removeCustomer);
			removeCustomer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					removeCustomer();
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
		
		public void addCustomer(){
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
					aUser = userFactory.makeNewUser("c", na.getText(),ad.getText(), em.getText(), ui.getText(), pa.getText());
					customer.add(aUser);
				}
			});

			mainFrame.setLayout(new GridLayout(6, 0));
			mainFrame.setSize(400, 400);
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
		
		public void removeCustomer(){
			
		}
		
		public void checkLateFees()
		{
			
		}
		
		public void removeStaff()
		{
			
		}

		public void addStaff()
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
					employee.add(aUser);
				}
			});

			mainFrame.setLayout(new GridLayout(6 , 0));
			mainFrame.setSize(400, 400);
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
			mainFrame.setSize(400, 400);
//			back = new JButton("Back");
//			bButtonHandel = new BackButtonHandler();
//			back.addActionListener(bButtonHandel);
//			mainFrame.add(back);
			exit = new JButton("Exit");
			eButtonHandel = new ExitButtonHandler();
			exit.addActionListener(eButtonHandel);
			mainFrame.add(exit);
		}

		public void startUp(){
			FileManager manage = new FileManager();
			games = manage.readGames("GamesList.csv");
			customer = manage.readCustomer("Customers.csv", games);
			employee = manage.readStaff("Staff.csv");
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
		
//		public class BackButtonHandler implements ActionListener
//		{
//			public void actionPerformed(ActionEvent g)
//			{
//				back.getClass();
//			}
//		}

		public void closeDown(){
			FileManager manage = new FileManager();
			manage.writeCustomers("Customers.csv", customer);
			manage.writeStaff("Staff.csv", employee);
			manage.writeAdmin("Administrator.csv", admin);
			manage.writeGames("GamesList.csv", games);
		}
}