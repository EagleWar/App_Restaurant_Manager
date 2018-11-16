import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Connection;

import javax.swing.ImageIcon;
import java.awt.Font;

public class Order {

	private JFrame frame;
	private int block = 1;

	/**
	 * Launch the application.
	 */
	Order(String id, Table[] table, String answerTable){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize(id, table, answerTable);
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
	private void initialize(String id, Table[] table, String answerTable) {
		frame = new JFrame();
		frame.setBounds(500,250, 931, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnMargherita = new JButton("Margherita 11\u20AC");
		btnMargherita.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnMargherita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Margherita :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Margherita", quantity);
					RestaurantSystem.setPizzaStats("marguerita", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
				
				
			}
		});
		btnMargherita.setBounds(35, 102, 216, 48);
		frame.getContentPane().add(btnMargherita);
		
		JButton btnVeggie = new JButton("Veggie 11\u20AC");
		btnVeggie.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnVeggie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Veggie :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Veggie", quantity);
					RestaurantSystem.setPizzaStats("veggie", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnVeggie.setBounds(347, 102, 216, 48);
		frame.getContentPane().add(btnVeggie);
		
		JButton btnMeat = new JButton("Meat 12\u20AC");
		btnMeat.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnMeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Meat :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Meat", quantity);
					RestaurantSystem.setPizzaStats("meat", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnMeat.setBounds(656, 102, 216, 48);
		frame.getContentPane().add(btnMeat);
		
		JButton btnBest = new JButton("Best 13\u20AC");
		btnBest.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnBest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Best :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Best", quantity);
					RestaurantSystem.setPizzaStats("best", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnBest.setBounds(35, 161, 216, 48);
		frame.getContentPane().add(btnBest);
		
		JButton btnChicken = new JButton("Chicken 12\u20AC");
		btnChicken.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnChicken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Chicken :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Chicken", quantity);
					RestaurantSystem.setPizzaStats("chicken", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnChicken.setBounds(347, 161, 216, 48);
		frame.getContentPane().add(btnChicken);
		
		JButton btnNewButton = new JButton("Cheese 8\u20AC");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Cheese :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Cheese", quantity);
					RestaurantSystem.setPizzaStats("cheese", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnNewButton.setBounds(656, 161, 216, 48);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnTiramisu = new JButton("Tiramisu 6\u20AC");
		btnTiramisu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnTiramisu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Tiramisu :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Tiramisu", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnTiramisu.setBounds(35, 236, 216, 48);
		frame.getContentPane().add(btnTiramisu);
		
		JButton btnIceScream = new JButton("Ice Cream 5\u20AC");
		btnIceScream.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnIceScream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Ice Cream :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Ice_Cream", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnIceScream.setBounds(347, 236, 216, 48);
		frame.getContentPane().add(btnIceScream);
		
		JButton btnApplePie = new JButton("Apple Pie 5\u20AC");
		btnApplePie.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnApplePie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Apple Pie :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Apple_Pie", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnApplePie.setBounds(656, 236, 216, 48);
		frame.getContentPane().add(btnApplePie);
		
		JButton btnCoca = new JButton("Coca 4\u20AC");
		btnCoca.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCoca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Coca :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Coca", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnCoca.setBounds(35, 312, 216, 48);
		frame.getContentPane().add(btnCoca);
		
		JButton btnFanta = new JButton("Fanta 3\u20AC");
		btnFanta.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnFanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Fanta :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Fanta", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnFanta.setBounds(656, 312, 216, 48);
		frame.getContentPane().add(btnFanta);
		
		JButton btnIceTea = new JButton("Ice Tea 3\u20AC");
		btnIceTea.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnIceTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String quantity = JOptionPane.showInputDialog(frame, "Quantity of Ice Tea :");
					if(quantity != null && quantity != "" && Integer.parseInt(quantity) >= 0) block = 0; 
					Order(answerTable, table, "Ice_Tea", quantity);
				}
				catch(Exception v)
				{
					JOptionPane.showMessageDialog(frame, "Invalid input!");
				}
			}
		});
		btnIceTea.setBounds(347, 312, 216, 48);
		frame.getContentPane().add(btnIceTea);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 220, 837, 5);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 375, 837, 2);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(35, 89, 837, 2);
		frame.getContentPane().add(separator_2);
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(block == 0) 
			{
				WaiterHome waiter = new WaiterHome(id, table);
				frame.setVisible(false);
			}
			else JOptionPane.showMessageDialog(frame, "You Have to Choose!");
			}
		});
		btnHome.setBounds(35, 396, 208, 79);
		frame.getContentPane().add(btnHome);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(block == 0) 
				{
					LoginSystem logSys = new LoginSystem(table);
					frame.setVisible(false);
				}
				else JOptionPane.showMessageDialog(frame, "You Have to Choose!");
			}
		});
		btnLogOut.setBounds(664, 396, 208, 79);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setBounds(347, 19, 216, 59);
		frame.getContentPane().add(lblMenu);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(35, 299, 837, 2);
		frame.getContentPane().add(separator_3);
	}
	
	public void Order(String answerTable, Table[] table, String choice, String quantity)
	{

		
		try
		{
			
			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
			
			ResultSet resultat = connexion.createStatement().executeQuery("SELECT " + choice + " FROM commande WHERE nb_order = " + table[Integer.parseInt(answerTable) - 1].order() + " AND day_date = (select date(now()))");
			
			resultat.next();
			
			int add = resultat.getInt(""+choice+"") + Integer.parseInt(quantity);
			
			String quantity2 = "" + add;
			
			connexion.createStatement().executeUpdate("UPDATE commande SET "+ choice + " = " + quantity2 + " WHERE nb_order = " + table[Integer.parseInt(answerTable) - 1].order() + " AND day_date = (select date(now()))");
			
			ResultSet resultat2 = connexion.createStatement().executeQuery("SELECT " + choice + " FROM commande_tmp WHERE nb_order = " + table[Integer.parseInt(answerTable) - 1].order() + " AND day_date = (select date(now()))");
			
			resultat2.next();
			
			int add2 = resultat.getInt(""+choice+"") + Integer.parseInt(quantity);
			
			String quantity3 = "" + add;
			
			connexion.createStatement().executeUpdate("UPDATE commande_tmp SET "+ choice + " = " + quantity3 + " WHERE nb_order = " + table[Integer.parseInt(answerTable) - 1].order() + " AND day_date = (select date(now()))");
																	
		}
		
		catch(Exception e) {
		}
	}
}







