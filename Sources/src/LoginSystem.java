import java.awt.EventQueue;
import java.sql.*;
import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Connection;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class LoginSystem {

	private JFrame frame;
	private JFrame frame2;
	private JTextField textId;
	private JPasswordField textPassword;
	private String id;
	private String password;

	/**
	 * Launch and Create the application.
	 */
	
	LoginSystem(Table[] table)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize(table);
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
	
	
	private void initialize(Table[] table) {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.control);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setBounds(500,250 , 931, 552);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login System");
		lblLogin.setBackground(Color.WHITE);
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(347, 19, 216, 59);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(311, 165, 43, 26);
		frame.getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(209, 269, 145, 26);
		frame.getContentPane().add(lblPassword);
		
		textId = new JTextField();
		textId.setBounds(374, 165, 185, 26);
		frame.getContentPane().add(textId);
		textId.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(378, 269, 185, 26);
		frame.getContentPane().add(textPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Scanner readFile = null;
				int end = 1;
				String checkId, checkPassword;
				id = textId.getText();
				password = textPassword.getText();
							
				try
				{
					
					Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
			 
					ResultSet resultat = connexion.createStatement().executeQuery("SELECT password FROM log WHERE id = '" + id + "'");
					
					resultat.next();
						
					if(resultat.getString("password").equals(password) && Integer.parseInt(id) < 5)
					{
						WaiterHome waiter = new WaiterHome(id, table);
						frame.setVisible(false);
					}
					else if(resultat.getString("password").equals(password) && Integer.parseInt(id) == 100)
					{
						KitchenHome kitchen = new KitchenHome(id, table);
						frame.setVisible(false);
					}
					else if(resultat.getString("password").equals(password) && Integer.parseInt(id) == 5)
					{
						MangerHome manager = new MangerHome(id, table);
						frame.setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Invalid Password !");
						textId.setText(null);
						textPassword.setText(null);
						end = 0;
					}

				}
				
				catch(Exception e) {
					
					JOptionPane.showMessageDialog(frame, "Invalid Identification !");
					textId.setText(null);
					textPassword.setText(null);
					
				}			
			}
		});
		btnLogin.setBounds(35, 396, 208, 79);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textId.setText(null);
				textPassword.setText(null);
			}
		});
		btnReset.setBounds(351, 396, 208, 79);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2 = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame,"Confirm if you want to exit", "Windows", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) System.exit(0);
				
			}
		});
		btnExit.setBounds(664, 396, 208, 79);
		frame.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 89, 837, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 375, 837, 2);
		frame.getContentPane().add(separator_1);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblLogin, lblId, lblPassword, textId, textPassword, btnLogin, btnReset, btnExit}));
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getPassword()
	{
		return password;
	}
}





















