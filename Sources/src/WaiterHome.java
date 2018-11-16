import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.mysql.jdbc.Connection;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class WaiterHome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	WaiterHome(String id, Table[] table)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize(id, table);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	/**
	 * Initialize the contents of the frame.
	 */
	
	public void writeStats(int caDay, String date, int nbPerson, String pizzaStats[][] ) { 
		
		int marguerita = Integer.parseInt(pizzaStats[1][0]);
		int veggie = Integer.parseInt(pizzaStats[1][1]);
		int meat = Integer.parseInt(pizzaStats[1][2]);
		int best = Integer.parseInt(pizzaStats[1][3]);
		int chicken = Integer.parseInt(pizzaStats[1][4]);
		int cheese = Integer.parseInt(pizzaStats[1][5]);
		String salesDay = "";
		
		
		try {
        	
			
        	Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
        
        	connexion.createStatement().executeUpdate("UPDATE restaurant.stats_pizza SET margherita = "+marguerita+", veggie="+veggie+", meat="+meat+", best="+best+", chicken="+chicken+", cheese="+cheese+" WHERE day_date = date(now())" );
        	connexion.createStatement().executeUpdate("UPDATE restaurant.stats_sales SET ca_day = "+caDay+", nmb_person="+nbPerson+" WHERE day_date = date(now())");
        
		}

        catch (Exception e){

                System.out.println("echec pilote : "+e);
                System.out.println("TEST CLOSE RESTAURANT bug");

        }
		salesDay += "Restaurant is close"+ "\n" + "Total Sales: " + caDay + "\n"+"Number of persons : " + nbPerson;
		//JOptionPane.showMessageDialog(frame, salesDay);
		
	}
	
	private void initialize(String id, Table[] table) {
		frame = new JFrame();
		frame.setBounds(500,250, 931, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogout = new JButton("Log Out");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				LoginSystem logSys = new LoginSystem(table);
				
				frame.setVisible(false);
			}
		});
		btnLogout.setBounds(35, 396, 208, 79);
		frame.getContentPane().add(btnLogout);
		
		JButton btnAssignTable = new JButton("Assign Table");
		btnAssignTable.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAssignTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					ArrayList list = new ArrayList();
					
					if(table[0].state == true || table[1].state == true || table[2].state == true)
					{
						
						for(int i = 1; i< 4; i++)
						{
							int tmp2 = i;
							String tmp = "" + tmp2;
							list.add(tmp);
						}
					}
					
					if(table[3].state == true || table[4].state == true || table[5].state == true || table[6].state == true)
					{
						
						for(int i = 4; i< 7; i++)
						{
							int tmp2 = i;
							String tmp = "" + tmp2;
							list.add(tmp);
						}
					}
					
					if(table[7].state == true || table[8].state == true || table[9].state == true)
					{
						
						for(int i = 7; i < 10; i++)
						{
							int tmp2 = i;
							String tmp = "" + tmp2;
							list.add(tmp);
						}
					}
					
						
					
					Object[] objectList = list.toArray();
					
					String[] choices = Arrays.copyOf(objectList, objectList.length, String[].class);
						
					String answerPerson = null;
					
					//UIManager.put("OptionPane.minimumSize",new Dimension(500,500)); 
				
					
					answerPerson = (String) JOptionPane.showInputDialog(frame, "Number Of Persons :", null, JOptionPane.QUESTION_MESSAGE, null, choices, null);
					
					int nbTable = checkTable(answerPerson, table);
					
				
					if(nbTable == -1)
					{
						JOptionPane.showMessageDialog(frame, "No Table!");
					}
					else 
					{
						JOptionPane.showMessageDialog(frame, "Table n° " + nbTable + " is Allocated !");
					}
				}
				
				catch(Exception e)
				{
				}
				

				
				
			}
		});
		btnAssignTable.setBounds(35, 115, 411, 57);
		frame.getContentPane().add(btnAssignTable);
		
		JButton btnDisplayTable = new JButton("Display Table");
		btnDisplayTable.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDisplayTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String displayTable = "\n";
				
				for(int i = 0; i < 10; i++)
				{
					displayTable += "   " + "Table n°  " + table[i].number() + "\t" + "  Etat :  " + table[i].state() + "\t" + "Waiter Id :  " + table[i].getId() + "\n\n"; 
				}
				

				
				JTextArea textArea = new JTextArea(6, 25);
			    textArea.setText(displayTable);
			    textArea.setEditable(false);
			    JScrollPane scrollPane = new JScrollPane(textArea);
			    scrollPane.setPreferredSize(new Dimension(278 ,355));
				JOptionPane.showMessageDialog(frame, scrollPane);

				
			}
		});
		btnDisplayTable.setBounds(461, 115, 411, 57);
		frame.getContentPane().add(btnDisplayTable);
		
		JButton btnAddOrder = new JButton("Add Order");
		btnAddOrder.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try
				{
					ArrayList list = new ArrayList();
					
					for(int i = 0; i < 10; i++)
					{
						
						if(table[i].getId().equals(id) && table[i].order() != -1 && table[i].getDelivered() == -1)
						{
							String tmp = "" + table[i].number();
							list.add(tmp);
						}
					}
					
					Object[] objectList = list.toArray();
					
					String[] choices = Arrays.copyOf(objectList, objectList.length, String[].class);
					
					String answerTable = null;
	
					
				    answerTable = (String) JOptionPane.showInputDialog(frame, "Choose Table to Add Order :",null, JOptionPane.QUESTION_MESSAGE, null,choices, null);
				    
				    	
					if(table[Integer.parseInt(answerTable) - 1].order() != -1 && table[Integer.parseInt(answerTable) - 1].stateOrder() == false) 
					{
						Order order = new Order(id, table, answerTable); // called the function order in the class waiter
						frame.setVisible(false);// if the order exist we can add
					}
					
					else if(table[Integer.parseInt(answerTable) - 1].order() != -1 && table[Integer.parseInt(answerTable) - 1].stateOrder() == true) 
					{
						table[Integer.parseInt(answerTable) - 1].changeStateOrder();
						Order order = new Order(id, table, answerTable); // called the function order in the class waiter
						frame.setVisible(false);
					} // if the order exist and if the order has ever been delivered by the kitchen we can add food but we have to change the state of the order to false  
					
					else JOptionPane.showMessageDialog(frame, "No Order at This Table or Order Running!");
				}
				
				catch(Exception p) {}
							
			}
		});
		btnAddOrder.setBounds(461, 208, 411, 57);
		frame.getContentPane().add(btnAddOrder);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try
				{
						ArrayList list = new ArrayList();
					
					for(int i = 0; i < 10; i++)
					{
						if(table[i].state() == false && table[i].order() == -1)
						{
							String tmp = ""+ table[i].number();
							list.add(tmp);
						}
					}
					
					Object[] objectList = list.toArray();
					
					String[] choices = Arrays.copyOf(objectList, objectList.length, String[].class);
						
					String answerTable = null;
					
					
				    answerTable = (String) JOptionPane.showInputDialog(frame, "Choose Table to Order :",null, JOptionPane.QUESTION_MESSAGE, null,choices, null);
					    
					
					if(table[Integer.parseInt(answerTable) - 1].state() == false && table[Integer.parseInt(answerTable) - 1].order() == -1) // if the table is used and if no order is created at the table
					{
						RestaurantSystem.incrementNbOrder(); // auto incrementation of the order
						table[Integer.parseInt(answerTable) - 1].changeStateOrder(); // change the state of the order to false
			    		
						table[Integer.parseInt(answerTable) - 1].orderCreate(RestaurantSystem.getNbOrder()); // give the real number of the order to a table
							
						table[Integer.parseInt(answerTable) - 1].setId(id);
			    		
						Order order = new Order(id, table, answerTable); // called the function order in the class waiter
						frame.setVisible(false);
						
						Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
					
						
						try
						{
							ResultSet resultat = connexion.createStatement().executeQuery("SELECT nb_order FROM commande WHERE nb_order = " + table[Integer.parseInt(answerTable) - 1].order() + " AND day_date = (select date(now()))");
						
							resultat.next();
						
							if(resultat.getInt("nb_order") == table[Integer.parseInt(answerTable) - 1].order())
							{
								connexion.createStatement().executeUpdate("UPDATE commande SET margherita = 0, veggie = 0, meat = 0, best = 0, chicken = 0, cheese = 0, tiramisu = 0, ice_cream = 0, apple_pie = 0, coca = 0, fanta = 0, ice_tea = 0 WHERE nb_order = " + table[Integer.parseInt(answerTable) - 1].order() + " AND day_date = (select date(now()))");

							}
							
							ResultSet resultat2 = connexion.createStatement().executeQuery("SELECT nb_order FROM commande_tmp WHERE nb_order = " + table[Integer.parseInt(answerTable) - 1].order() + " AND day_date = (select date(now()))");
							
							resultat2.next();
						
							if(resultat2.getInt("nb_order") == table[Integer.parseInt(answerTable) - 1].order())
							{
								connexion.createStatement().executeUpdate("UPDATE commande_tmp SET margherita = 0, veggie = 0, meat = 0, best = 0, chicken = 0, cheese = 0, tiramisu = 0, ice_cream = 0, apple_pie = 0, coca = 0, fanta = 0, ice_tea = 0 WHERE nb_order = " + table[Integer.parseInt(answerTable) - 1].order() + " AND day_date = (select date(now()))");

							}
						}
						
						catch(Exception p)
						{
							
						}
						
						
						connexion.createStatement().executeUpdate("INSERT INTO commande (nb_order, day_date, margherita, veggie, meat, best, chicken, cheese, tiramisu, ice_cream, apple_pie, coca, fanta, ice_tea) VALUES (" + table[Integer.parseInt(answerTable) - 1].order() + ",now(),0,0,0,0,0,0,0,0,0,0,0,0)");
						
						
						connexion.createStatement().executeUpdate("INSERT INTO commande_tmp (nb_order, day_date, margherita, veggie, meat, best, chicken, cheese, tiramisu, ice_cream, apple_pie, coca, fanta, ice_tea) VALUES (" + table[Integer.parseInt(answerTable) - 1].order() + ",now(),0,0,0,0,0,0,0,0,0,0,0,0)");
			    		
					}
					else if(table[Integer.parseInt(answerTable) - 1].state() == false && table[Integer.parseInt(answerTable) - 1].order() != -1)
					{
						JOptionPane.showMessageDialog(frame, "Table already ordered!"); // if a table is occupied and the order has been created, do not create an other order
					}
					else JOptionPane.showMessageDialog(frame, "Table is free!");
				}
				
				catch(Exception x) {}
			
					
			}
		});
		btnOrder.setBounds(35, 208, 411, 57);
		frame.getContentPane().add(btnOrder);
		
		JList list = new JList();
		list.setBounds(253, 153, 48, -11);
		frame.getContentPane().add(list);
		
		JButton btnPrintBill = new JButton("Print Bill");
		btnPrintBill.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnPrintBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{					
					ArrayList list = new ArrayList();
					
					for(int i = 0; i < 10; i++)
					{
						
						if(table[i].getId().equals(id) && table[i].stateOrder() == true && table[i].getDelivered() == -1 && table[i].order() != -1)
						{
							String tmp = ""+ table[i].number();
							list.add(tmp);
						}
					}
					
					Object[] objectList = list.toArray();
					
					String[] choices = Arrays.copyOf(objectList, objectList.length, String[].class);
						
					String answerTable = null;
						

				    answerTable = (String) JOptionPane.showInputDialog(frame, "Choose Table to Print Bill :",null, JOptionPane.QUESTION_MESSAGE, null,choices, null);
				    
				    if(answerTable != null)
				    {
				    	int total = 0;
				    	int tva_20 = 0;
				    	int tva_10 = 0;
				    	
				    	String bill = "\n\n\t                 --------------------------\n\t                        PIZZA PINOT\n\t                 --------------------------\n\t"
				    			+ "                       RESTAURANT\n\n\t                85, Boulevard Voltaire\n\t                        75011 PARIS\n"
				    			+ "\t                 TEL : 01.08.68.27.69\n\n\n                TABLE N° " + table[Integer.parseInt(answerTable) - 1].order() + "\n\n";
				    	
			    		Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
						
						ResultSet resultat = connexion.createStatement().executeQuery("SELECT margherita, veggie, meat, best, chicken, cheese, tiramisu, ice_cream, apple_pie, coca, fanta, ice_tea FROM commande WHERE nb_order = " + table[Integer.parseInt(answerTable) - 1].order() + " AND day_date = (select date(now()))");
						
						String[] menu = {"Margherita", "Veggie", "Meat", "Best", "Chicken", "Cheese", "Tiramisu", "Ice_Cream", "Apple_Pie", "Coca", "Fanta", "Ice_Tea"};
						
						resultat.next();
						
						
						for(int i = 0; i < 12; i++)
						{
							if(resultat.getInt(menu[i]) != 0) 
							{
								
								
								ResultSet resultat2 = connexion.createStatement().executeQuery("SELECT price From menu WHERE plate = '" + menu[i] +"'");
								
								resultat2.next();
								
								int price = resultat2.getInt("price") * resultat.getInt(menu[i]);
								
								total += price;
								
								if(menu[i].equals("Margherita") || menu[i].equals("Veggie")|| menu[i].equals("Meat")|| menu[i].equals("Best")|| menu[i].equals("Chicken")
									|| menu[i].equals("Cheese")|| menu[i].equals("Tiramisu")|| menu[i].equals("Ice_Cream")|| menu[i].equals("Apple_Pie")) tva_20 += price;
								
								else tva_10 += price;
								
								bill += "\t " + resultat.getInt(menu[i]) + "   " + menu[i] + "\t\t"+ price + "\n";
							}
							
						}
							
						ResultSet resultat2 = connexion.createStatement().executeQuery("SELECT now()");
						
						resultat2.next();
									    	
						
						
						double TVA_20 = 0.2 * tva_20;
						
						double tva_incl_20 = tva_20 - TVA_20;
						
						double TTC_20 = tva_20;
						
						
						
						double TVA_10 = 0.1 * tva_10;
						
						double tva_incl_10 = tva_10 - TVA_10;
						
						double TTC_10 = tva_10;
						
						double service = 0.14 * total;
						
						java.text.DecimalFormat df = new java.text.DecimalFormat("0.#");
						
						bill += "       -------------------------------------------------------------------------------------\n\t                    Include         TVA         TTC\n"
								+ "               TVA 20%                     " + df.format(tva_incl_20) + "               " + df.format(TVA_20) + "             " + df.format(TTC_20) + "\n"
								+ "               TVA 10%                      " + df.format(tva_incl_10) + "                " + df.format(TVA_10) + "             " + df.format(TTC_10) + "\n"
								+ "               Service 14%              " + df.format(service) + "\n"
								+ "       -------------------------------------------------------------------------------------\n                TOTAL\t\t\t  "
								+ total + "€\n\n                 Date of Payment : " + RestaurantSystem.getDate() + "\n\n\t               THANKS TO VISIT US\n\t"
										+ "                    SEE YOU SOON\n";
						
						
						JTextArea textArea = new JTextArea(6, 25);
					    textArea.setText(bill);
					    textArea.setEditable(false);
					    JScrollPane scrollPane = new JScrollPane(textArea);
					    scrollPane.setPreferredSize(new Dimension(400,680));
					    JOptionPane.showMessageDialog(frame, scrollPane);
					    
					    String waiter = null;
					    
					    if(id.equals("1")) waiter = "waiter_1";
					    else if(id.equals("2"))waiter = "waiter_2";
					    else if(id.equals("3"))waiter = "waiter_3";
					    else if(id.equals("4"))waiter = "waiter_4";
					    
					    ResultSet resultat3= connexion.createStatement().executeQuery("SELECT " + waiter + " FROM stat_waiter WHERE day_date = (select date(now()))");
						
						resultat3.next();
						
												
						int add = resultat3.getInt(""+waiter+"") + total;
						
						String quantity2 = "" + add;
						
						connexion.createStatement().executeUpdate("UPDATE stat_waiter SET "+ waiter + " = " + quantity2 + " WHERE day_date = (select date(now()))");
				 		
						RestaurantSystem.setCaDay(total);
						
						writeStats(RestaurantSystem.getCaDay(),RestaurantSystem.getDate(), RestaurantSystem.getNbPerson(), RestaurantSystem.getPizzaStats2());
					    
					    table[Integer.parseInt(answerTable) - 1].reset();
					       
						
														    	
				    }
				}
				
				catch(Exception p) {}
				
				
	

			}
		});
		btnPrintBill.setBounds(461, 293, 411, 57);
		frame.getContentPane().add(btnPrintBill);
		
		JLabel lblWaiterMenu = new JLabel("Waiter Menu");
		lblWaiterMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaiterMenu.setForeground(Color.BLACK);
		lblWaiterMenu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblWaiterMenu.setBounds(347, 19, 216, 59);
		frame.getContentPane().add(lblWaiterMenu);
		
		JLabel lblId = new JLabel("ID : " + id);
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblId.setBounds(787, 396, 94, 79);
		frame.getContentPane().add(lblId);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 89, 837, 2);
		frame.getContentPane().add(separator);
		
		JButton btnNewButton = new JButton("Check Order");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckOrder checkOrder = new CheckOrder(id, table);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(35, 293, 411, 57);
		frame.getContentPane().add(btnNewButton);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 375, 837, 2);
		frame.getContentPane().add(separator_1);
	}
	
	public int checkTable(String persons, Table[] table) // check if a table can host a number of person
	{
		int i = 0;
		for( i = 0; i < 10; i++) // 10 tables
		{
			
			if(table[i].state == true) 
			{
				if(table[i]. size1 == Integer.parseInt(persons) || table[i]. size2 == Integer.parseInt(persons) || table[i]. size3 == Integer.parseInt(persons) ) // if one of the 3 sizes of a table can host
				{
					table[i].changeState();
					RestaurantSystem.incrementNbPerson(persons);
					return table[i].number();
				}
			}
		}
		return -1;
	}
}
