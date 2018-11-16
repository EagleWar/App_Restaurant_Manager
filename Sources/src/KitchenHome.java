import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.mysql.jdbc.Connection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;

public class KitchenHome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	KitchenHome(String id, Table[] table){
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
	private void initialize(String id, Table[] table) {
		frame = new JFrame();
		frame.setBounds(500,250, 931, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnOrderRunning = new JButton("Order Running");
		btnOrderRunning.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnOrderRunning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					ArrayList list2 = new ArrayList();
					
					for(int i = 0; i < 10; i++)
					{
						
						if(table[i].stateOrder() == false && table[i].getDelivered() == -1)
						{
							String tmp = "" + table[i].number();
							list2.add(tmp);
						}
					}
					
					Object[] objectList = list2.toArray();
					
					String[] choices = Arrays.copyOf(objectList, objectList.length, String[].class);
					
				    String input = null;

				    
				    input = (String) JOptionPane.showInputDialog(frame, "Choose Table to See The Order :",null, JOptionPane.QUESTION_MESSAGE, null,choices, null);
				    
				    if(input != null)
				    {
				    	
				    	 if(input != null)
						    {
						    	
				    		 String order = "\n\t     Order Table N° " + input + "\n\n     ----------------------------------------------------------------\n\n";
						    	
						    	try
						    	{
						    		
						    		Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
									
									ResultSet resultat = connexion.createStatement().executeQuery("SELECT margherita, veggie, meat, best, chicken, cheese, tiramisu, ice_cream, apple_pie, coca, fanta, ice_tea FROM commande_tmp WHERE nb_order = " + table[Integer.parseInt(input) - 1].order() + " AND day_date = (select date(now()))");
									
									String[] menu = {"Margherita", "Veggie", "Meat", "Best", "Chicken", "Cheese", "Tiramisu", "Ice_Cream", "Apple_Pie", "Coca", "Fanta", "Ice_Tea"};
									
									resultat.next();
									
									for(int i = 0; i < 12; i++)
									{
										
										if(resultat.getInt(menu[i]) != 0) 
										{
											order += "      " +  menu[i] + "\tX " + resultat.getInt(menu[i]) + "\n";
										}
			
									}
									
						    	
						    	}
						    	
						    	catch(Exception o)
						    	{}
						    				
								JTextArea textArea = new JTextArea(6, 25);
							    textArea.setText(order);
							    textArea.setEditable(false);
							    JScrollPane scrollPane = new JScrollPane(textArea);
							    scrollPane.setPreferredSize(new Dimension(300,300));
							    JOptionPane.showMessageDialog(frame, scrollPane);
							    								    	
						    }
				    	    								    	
				    }
				}
				
				catch(Exception n)
				{
					
				}


				
			}
		});
		btnOrderRunning.setBounds(35, 256, 837, 79);
		frame.getContentPane().add(btnOrderRunning);
		
		JButton btnFinishAnOrder = new JButton("Finish an Order");
		btnFinishAnOrder.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnFinishAnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList list2 = new ArrayList();
				
				for(int i = 0; i < 10; i++)
				{
					
					if(table[i].stateOrder() == false && table[i].getDelivered() == -1)
					{
						String tmp = "" + table[i].number();
						list2.add(tmp);
					}
				}
				
				Object[] objectList = list2.toArray();
				
				String[] choices = Arrays.copyOf(objectList, objectList.length, String[].class);
				
			    String input = null;
			    
			    
			    input = (String) JOptionPane.showInputDialog(frame, "Choose Table To Finish Order :",null, JOptionPane.QUESTION_MESSAGE, null,choices, null);
			    
			    if(input != null)
			    {
			    	table[Integer.parseInt(input) - 1].changeStateOrder();
			    	table[Integer.parseInt(input) - 1].changeStateDelivered();
			    	
				    JOptionPane.showMessageDialog(frame, "Order Table n° " + table[Integer.parseInt(input) - 1].number() + " Finished !");
				    
					Connection connexion;
					try 
					{
						connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
						connexion.createStatement().executeUpdate("UPDATE commande_tmp SET margherita = 0, veggie = 0, meat = 0, best = 0, chicken = 0, cheese = 0, tiramisu = 0, ice_cream = 0, apple_pie = 0, coca = 0, fanta = 0, ice_tea = 0 WHERE nb_order = " + table[Integer.parseInt(input) - 1].order() + " AND day_date = (select date(now()))");

					} 
					
					catch (Exception e1) {}
				   

				
			    }
			}
		});
		btnFinishAnOrder.setBounds(35, 130, 837, 79);
		frame.getContentPane().add(btnFinishAnOrder);
		
		JLabel lblKitchen = new JLabel("Kitchen");
		lblKitchen.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblKitchen.setHorizontalAlignment(SwingConstants.CENTER);
		lblKitchen.setBounds(347, 19, 216, 59);
		frame.getContentPane().add(lblKitchen);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 375, 837, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 89, 837, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginSystem logSys = new LoginSystem(table);
				frame.setVisible(false);
			}
		});
		btnLogOut.setBounds(664, 396, 208, 79);
		frame.getContentPane().add(btnLogOut);
	}
}
