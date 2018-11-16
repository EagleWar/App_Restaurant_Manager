import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CheckOrder {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	CheckOrder(String id, Table[] table){
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
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.setBounds(500,250, 931, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnOrderToDeliver = new JButton("Order to Deliver");
		btnOrderToDeliver.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnOrderToDeliver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					ArrayList list = new ArrayList();
					
					for(int i = 0; i < 10; i++)
					{
						
					if(table[i].getId().equals(id) && table[i].stateOrder() == true && table[i].getDelivered() == 0)
					{
						String tmp = "" + table[i].number();
						list.add(tmp);
					}
					}
					
					Object[] objectList = list.toArray();
					
					String[] choices = Arrays.copyOf(objectList, objectList.length, String[].class);
					
					String input = null;
			

					
				    input = (String) JOptionPane.showInputDialog(frame, "Choose Table to Deliver : ",null, JOptionPane.QUESTION_MESSAGE, null,choices, null);
				    
				    
				    JOptionPane.showMessageDialog(frame, "Order Table n° " + table[Integer.parseInt(input) - 1].number() + " Delivered !");
				    
				    table[Integer.parseInt(input) - 1].changeStateDelivered();
				    
					}
				
				catch(Exception p) {}   
			   
			}
		});
		btnOrderToDeliver.setBounds(35, 130, 837, 79);
		frame.getContentPane().add(btnOrderToDeliver);
		
		JButton btnOrderRunning = new JButton("Order Running");
		btnOrderRunning.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnOrderRunning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try
				{
					ArrayList list2 = new ArrayList();
					
					for(int i = 0; i < 10; i++)
					{
						
						if(table[i].getId().equals(id) && table[i].stateOrder() == false && table[i].getDelivered() == -1)
						{
							String tmp = "" + table[i].number();
							list2.add(tmp);
						}
					}
					
					Object[] objectList = list2.toArray();
					
					String[] choices = Arrays.copyOf(objectList, objectList.length, String[].class);
					
				    String input = null;
				   
				    
				    input = (String) JOptionPane.showInputDialog(frame, "Choose Table to Check the Order :",null, JOptionPane.QUESTION_MESSAGE, null,choices, null);
				    
				    if(input != null)
				    {
				    	
				    	String order = "\n\t     Order Table : " + input + "\n\n     ----------------------------------------------------------------\n\n";
				    	
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
									order += "      " + menu[i] + "\tX  " + resultat.getInt(menu[i]) + "\n";
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
				
				catch(Exception s) {}
				
			}
		});
		btnOrderRunning.setBounds(35, 256, 837, 79);
		frame.getContentPane().add(btnOrderRunning);
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WaiterHome waiter  = new WaiterHome(id, table);
				frame.setVisible(false);
			}
		});
		btnHome.setBounds(35, 396, 208, 79);
		frame.getContentPane().add(btnHome);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginSystem logSys = new LoginSystem(table);
				frame.setVisible(false);
			}
		});
		btnLogOut.setBounds(664, 396, 208, 79);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblCkeckOrder = new JLabel("Ckeck Order");
		lblCkeckOrder.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCkeckOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblCkeckOrder.setBounds(347, 19, 216, 59);
		frame.getContentPane().add(lblCkeckOrder);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 375, 837, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 89, 837, 2);
		frame.getContentPane().add(separator_1);
	}

}
