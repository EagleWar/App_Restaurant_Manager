import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.sql.*;
import com.mysql.jdbc.Connection;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JSeparator;


public class MangerHome {

	private JFrame frame;

	public MangerHome(String id, Table[] table) {
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
	
	public String salesDay(String day) {
		/*This methods print Sales we did in a particular day with the number of persons*/
		
		Scanner sales = null;
		try {
			
			sales = new Scanner(new FileInputStream("stats.txt"));
			
			while(sales.hasNext()) {
				if(day.equals(sales.next() )) {
					return "day : " + day + "\nSales : " + sales.next() + "€\nNumber of persons : " + sales.next();
				}	
			}
			
		}catch(Exception e) {}
		sales.close();
		return "The date that you have entered is incorrect";
	}
	
	public String salesDay2(String day) {
		
		String stats = "";
		
		String pilote = "com.mysql.jdbc.Driver";
        try {
            Class.forName(pilote);
            Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
            ResultSet resultat = ((java.sql.Statement) connexion.createStatement()).executeQuery("SELECT * FROM stats_sales WHERE day_date = '"+day+"'");
            while(resultat.next()){
            	stats +="Date :  "+resultat.getString("day_date");
            	stats += "\n";
                stats += "Sales : "+resultat.getInt("ca_day");
                stats += " €\n";
                stats += "Number of Person: "+resultat.getInt("nmb_person");
            }
        }

        catch (Exception e) {
                System.out.println("echec pilote : "+e);

        }
        return stats;
	}
	

public int salesDay3(String day) {
	
	int stats = 0;
		
	String pilote = "com.mysql.jdbc.Driver";
    try {
    	Class.forName(pilote);
        Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
        ResultSet resultat = ((java.sql.Statement) connexion.createStatement()).executeQuery("SELECT * FROM stats_sales WHERE day_date = '"+day+"'");
            while(resultat.next()){
            
                stats += resultat.getInt("ca_day");
              
            }
        }

        catch (Exception e) {
                System.out.println("echec pilote : "+e);

        }
       return stats;
}

public int [][] salesMonth(int month, int year) {
	
	
	String stats = "";
	String date = year + "-"+month;
	
	
	int totalSales[][] = new int [2][31];
	
	/*for(int j=0; j<31; j++) {
		
		System.out.println(	totalSales[0][j] = j);
		System.out.println(	totalSales[1][j] = 0);
	}*/
	int tmp = 0;
	for(int j=0; j<31; j++) {
		tmp = j+1;
		totalSales[1][j]  = salesDay3(date+"-"+tmp);
	}
	

   
    return totalSales ;
   
	
	
}

public int [] statsPizzaMonth(String month, String year) {
	
	int statsPizzaMonth [] = new int[6];
	String date1 = year + "-"+month+"-"+"01";
	String date2 = year + "-"+month+"-"+"31";
	String pilote = "com.mysql.jdbc.Driver";
    try {
        Class.forName(pilote);
        Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
        ResultSet resultat = ((java.sql.Statement) connexion.createStatement()).executeQuery("SELECT * FROM restaurant.stats_pizza\r\n" + 
        		" where( day_date between  '"+ date1 + "' AND'"+date2+"')");
        while(resultat.next()){
        
        	statsPizzaMonth[0]+= resultat.getInt("margherita");
        	statsPizzaMonth[1]+= resultat.getInt("veggie");
        	statsPizzaMonth[2]+= resultat.getInt("meat");
        	statsPizzaMonth[3]+= resultat.getInt("best");
        	statsPizzaMonth[4]+= resultat.getInt("chicken");
        	statsPizzaMonth[5]+= resultat.getInt("cheese");
        }
    }

    catch (Exception e) {
            System.out.println("echec pilote : "+e);

    }
    
   
   return statsPizzaMonth;
}

public int [] statsPizzaMonth2(String month, String year) {
	
	int statsPizzaMonth [] = new int[6];
	String date1 = year + "-"+month+"-"+"01";
	String date2 = year + "-"+month+"-"+"31";
	String pilote = "com.mysql.jdbc.Driver";
    try {
        Class.forName(pilote);
        Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
        ResultSet resultat = ((java.sql.Statement) connexion.createStatement()).executeQuery("SELECT * FROM restaurant.stats_pizza\r\n" + 
        		" where( day_date between  '"+ date1 + "' AND'"+date2+"')");
        while(resultat.next()){
        
        	statsPizzaMonth[0]+= resultat.getInt("margherita");
        	statsPizzaMonth[1]+= resultat.getInt("veggie");
        	statsPizzaMonth[2]+= resultat.getInt("meat");
        	statsPizzaMonth[3]+= resultat.getInt("best");
        	statsPizzaMonth[4]+= resultat.getInt("chicken");
        	statsPizzaMonth[5]+= resultat.getInt("cheese");
        }
    }

    catch (Exception e) {
            System.out.println("echec pilote : "+e);

    }
    
   
   return statsPizzaMonth;
}

public int [] statsWaiterMonth2(String month, String year) {
	
	int statsWaiterMonth [] = new int[4];
	String date1 = year + "-"+month+"-"+"01";
	String date2 = year + "-"+month+"-"+"31";
	String pilote = "com.mysql.jdbc.Driver";
    try {
        Class.forName(pilote);
        Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
        ResultSet resultat = ((java.sql.Statement) connexion.createStatement()).executeQuery("SELECT * FROM restaurant.stat_waiter\r\n" + 
        		" where( day_date between  '"+ date1 + "' AND'"+date2+"')");
        while(resultat.next()){
        
        	statsWaiterMonth[0]+= resultat.getInt("waiter_1");
        	statsWaiterMonth[1]+= resultat.getInt("waiter_2");
        	statsWaiterMonth[2]+= resultat.getInt("waiter_3");
        	statsWaiterMonth[3]+= resultat.getInt("waiter_4");
       
        }
    }

    catch (Exception e) {
            System.out.println("echec pilote : "+e);

    }
    
   
   return statsWaiterMonth;
}

public int salesMonth2(int month, int year) {
	
	
	String stats = "";
	String date1 = year + "-"+month+"-"+"01";
	String date2 = year + "-"+month+"-"+"31";
	
	int totalSales = 0;
	int totalPerson = 0;
	
	String pilote = "com.mysql.jdbc.Driver";
    try {
        Class.forName(pilote);
        Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
        ResultSet resultat = ((java.sql.Statement) connexion.createStatement()).executeQuery("SELECT * FROM restaurant.stats_sales\r\n" + 
        		" where( day_date between  '"+ date1 + "' AND'"+date2+"')" );
        while(resultat.next()){
        	stats +="Date :  "+resultat.getString("day_date");
        	stats += "\n\n";
            totalSales += resultat.getInt("ca_day");
            stats += "Sales :  "+resultat.getString("ca_day");
            stats += " €\n";
            totalPerson += resultat.getInt("nmb_person");
            stats += "number of person :  "+resultat.getString("nmb_person") + "\n\n";
        }
        
    }
    catch (Exception e) {
            System.out.println("echec pilote : "+e);
    }
    String temp = "\nMonth: "+month
    		+ "\nYear: "+year+"\nTotal Sales : " 
    		+ totalSales + " Total Person : " + totalPerson; 
   //System.out.println(temp);
    return totalSales ;
   
	
	
}

public int [] statsPizzaYear( String year) {
	
	int statsPizzaYear[] = new int[6];
	int tmp[][] = new int[12][6];
	for(int i= 0; i<12; i++) {
	
		tmp[i] = statsPizzaMonth(i+1+"",year);
	
	} 
	
	
	
	for(int i = 0 ;i<6;i++) {
		for(int j=0; j<12;j++) {
			statsPizzaYear[i] += tmp[j][i]; 
			
		}
		//System.out.println(statsPizzaYear[i]);
	}
	
	return statsPizzaYear;
}



public int [] statsWaiterMonth(String month, String year) {
	
	int statsWaiterMonth [] = new int[4];
	String date1 = year + "-"+month+"-"+"01";
	String date2 = year + "-"+month+"-"+"31";
	String pilote = "com.mysql.jdbc.Driver";
    try {
        Class.forName(pilote);
        Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
        ResultSet resultat = ((java.sql.Statement) connexion.createStatement()).executeQuery("SELECT * FROM stat_waiter\r\n" + 
        		" where( day_date between  '"+ date1 + "' AND'"+date2+"')");
        while(resultat.next()){
        
        	statsWaiterMonth[0]+= resultat.getInt("waiter_1");
        	statsWaiterMonth[1]+= resultat.getInt("waiter_2");
        	statsWaiterMonth[2]+= resultat.getInt("waiter_3");
        	statsWaiterMonth[3]+= resultat.getInt("waiter_4");

        }
    }

    catch (Exception e) {
            System.out.println("echec pilote : "+e);

    }
    
   
   return statsWaiterMonth;
}



public int [] statsWaiterYear( String year) {
	
	int statsPizzaYear[] = new int[4];
	int tmp[][] = new int[12][4];
	for(int i= 0; i<12; i++) {
	
		tmp[i] = statsWaiterMonth(i+1+"",year);
	
	} 
	
	
	
	for(int i = 0 ;i<4;i++) {
		for(int j=0; j<12;j++) {
			statsPizzaYear[i] += tmp[j][i]; 
			
		}
		//System.out.println(statsPizzaYear[i]);
	}
	
	return statsPizzaYear;
}


public int [][] salesYear(int year) {
	
	int stats[][] = new int[2][12];
    for(int i = 0; i < 12; i++) stats[0][i] = i;
    for(int i = 0; i < 12; i++) stats[1][i] += salesMonth2(i+1,year);
	return stats;

}
	
	
	public void closeRestaurant(int caDay, String date, int nbPerson, String pizzaStats[][] ) { // this method write the data about the sales and statistics on the database
	
		int marguerita = Integer.parseInt(pizzaStats[1][0]);
		int veggie = Integer.parseInt(pizzaStats[1][1]);
		int meat = Integer.parseInt(pizzaStats[1][2]);
		int best = Integer.parseInt(pizzaStats[1][3]);
		int chicken = Integer.parseInt(pizzaStats[1][4]);
		int cheese = Integer.parseInt(pizzaStats[1][5]);
		String salesDay = "";
	
		
		try {
        	
			//System.out.println("TEST CLOSE RESTAURANT");
        	Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
        
        	connexion.createStatement().executeUpdate("UPDATE restaurant.stats_pizza SET margherita = "+marguerita+", veggie="+veggie+", meat="+meat+", best="+best+", chicken='"+chicken+", cheese="+cheese+" WHERE day_date = date(now()))" );
        	connexion.createStatement().executeUpdate("UPDATE restaurant.stats_sales SET ca_day = "+caDay+", nmb_person="+nbPerson+" WHERE day_date = date(now()))");
        	

        /*	connexion.createStatement().executeUpdate("INSERT INTO stats_sales (day_date, ca_day, nmb_person) VALUES (DATE(NOW()), "+caDay+", "+nbPerson+")");
        	connexion.createStatement().executeUpdate("INSERT INTO `restaurant`.`stats_pizza` (`day_date`, `margherita`, `veggie`, `meat`, `best`, `chicken`, `cheese`) VALUES (DATE(NOW()), "+marguerita+", "+veggie+",  "+meat+",  "+best+",  "+chicken+", "+cheese+")");
     */
        	//System.out.println("TEST CLOSE RESTAURANT 1");
		}

        catch (Exception e){

                System.out.println("echec pilote : "+e);
                System.out.println("TEST CLOSE RESTAURANT bug");

        }
		salesDay += "Restaurant is close"+ "\n" + "Total Sales: " + caDay + "\n"+"Number of persons : " + nbPerson;
		JOptionPane.showMessageDialog(frame, salesDay);
		
	}
	
	public int [] statsPizzaDay(String day) { // get  the statistics about the pizza by reading the database
		
		int statsPizzaDay [] = new int[6];
		String pilote = "com.mysql.jdbc.Driver";
        try {
            Class.forName(pilote);
            Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
            ResultSet resultat = ((java.sql.Statement) connexion.createStatement()).executeQuery("SELECT * FROM stats_pizza WHERE day_date = '"+day+"'");
            while(resultat.next()){
            
                statsPizzaDay[0]+= resultat.getInt("margherita");
                statsPizzaDay[1] += resultat.getInt("veggie");
                statsPizzaDay[2]+= resultat.getInt("meat");
                statsPizzaDay[3]+= resultat.getInt("best");
                statsPizzaDay[4]+= resultat.getInt("chicken");
                statsPizzaDay[5]+= resultat.getInt("cheese");
            }
        }

        catch (Exception e) {
                System.out.println("echec pilote : "+e);
        }
        
       
       return statsPizzaDay;
	}
	
	
public int [] statsWaiterDay(String day) { // get  the statistics about the pizza by reading the database
		
		int statsWaiterDay [] = new int[4];
		String pilote = "com.mysql.jdbc.Driver";
        try {
            Class.forName(pilote);
            Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
            ResultSet resultat = ((java.sql.Statement) connexion.createStatement()).executeQuery("SELECT * FROM stat_waiter WHERE day_date = '"+day+"'");
            while(resultat.next()){
            
            	statsWaiterDay[0]+= resultat.getInt("waiter_1");
            	statsWaiterDay[1] += resultat.getInt("waiter_2");
            	statsWaiterDay[2]+= resultat.getInt("waiter_3");
            	statsWaiterDay[3]+= resultat.getInt("waiter_4");
            }
        }

        catch (Exception e) {
                System.out.println("echec pilote : "+e);
        }
        
       
       return statsWaiterDay;
	}

	
	private void initialize(String id, Table[] table) {
		frame = new JFrame();
		frame.setBounds(500, 250, 931, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginSystem logSys = new LoginSystem(table);
				
				frame.dispose();
			}
		});
		btnLogOut.setBounds(664, 396, 208, 79);
		frame.getContentPane().add(btnLogOut);
		
		JButton btnNewButton_4 = new JButton("Waiter");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					String[] choices = {"Day", "Month", "Year"};
					
					String answerTable = null;
					
				    answerTable = (String) JOptionPane.showInputDialog(frame, "Choose the Format Date :",null, JOptionPane.QUESTION_MESSAGE, null,choices, null);
				    
				    if(answerTable.equals("Day"))
				    {
				    	try {
							String  date = JOptionPane.showInputDialog(null, "Enter a date");
							int stats[] = statsWaiterDay(date); 
						
							DefaultCategoryDataset dataset = new DefaultCategoryDataset();
							
							String tmp [] = {"Waiter 1","Waiter 2","Waiter 3","Waiter 4"};
						
							for(int i =0; i < 4; i++) {
								dataset.setValue(stats[i],"Stats",tmp[i]);	
							}
			
							
							JFreeChart chart = ChartFactory.createBarChart("Stats ","","",dataset, PlotOrientation.VERTICAL,false,false,false);
							CategoryPlot catPlot = chart.getCategoryPlot();
							catPlot.setRangeGridlinePaint(Color.BLACK);
							ChartPanel charPanel = new ChartPanel(chart);
							
							JPanel panel = new JPanel();
							panel.add(charPanel, BorderLayout.CENTER);
							panel.validate();
							
							JOptionPane.showMessageDialog(frame, panel);
						}catch(Exception s) {}
				    }
				    
				    else if(answerTable.equals("Month"))
				    {
				    	try {
							
				    		String  year = JOptionPane.showInputDialog(null, "Enter a Year :");
				    		String  month = JOptionPane.showInputDialog(null, "Enter a Month :");
							
							
							int stats[] = statsWaiterMonth2(month, year); 
						
							DefaultCategoryDataset dataset = new DefaultCategoryDataset();
							
							String tmp [] = {"Waiter 1","Waiter 2","Waiter 3","Waiter 4"};
						
							for(int i =0; i < 4; i++) {
								dataset.setValue(stats[i],"Stats",tmp[i]);	
							}
			
							
							JFreeChart chart = ChartFactory.createBarChart("Stats ","","",dataset, PlotOrientation.VERTICAL,false,false,false);
							CategoryPlot catPlot = chart.getCategoryPlot();
							catPlot.setRangeGridlinePaint(Color.BLACK);
							ChartPanel charPanel = new ChartPanel(chart);
							JPanel panel = new JPanel();
							panel.add(charPanel, BorderLayout.CENTER);
							panel.validate();
							
							JOptionPane.showMessageDialog(frame, panel);
						}catch(Exception s) {}
				    }
				    
				    else if(answerTable.equals("Year"))
				    {
				    	try {
				    		
							String  year = JOptionPane.showInputDialog(null, "Enter a Year :");
							
							int stats[] = statsWaiterYear(year); 
							
							DefaultCategoryDataset dataset = new DefaultCategoryDataset();
							
							
							
							String tmp [] = {"Waiter 1","Waiter 2","Waiter 3","Waiter 4"};
						
							for(int i =0; i < 4; i++) {
								
								//System.out.println(stats[i]);
								dataset.setValue(stats[i],"Stats",tmp[i]);	
							}
			
							
							JFreeChart chart = ChartFactory.createBarChart("Stats ","","",dataset, PlotOrientation.VERTICAL,false,false,false);
							CategoryPlot catPlot = chart.getCategoryPlot();
							catPlot.setRangeGridlinePaint(Color.BLACK);
							ChartPanel charPanel = new ChartPanel(chart);
							JPanel panel = new JPanel();
							panel.add(charPanel, BorderLayout.CENTER);
							panel.validate();
							
							JOptionPane.showMessageDialog(frame, panel);
						}catch(Exception s) {}
				    }
				}
				    
				    catch(Exception l)
				    {
				    }
			}
		});
		btnNewButton_4.setBounds(35, 241, 837, 59);
		frame.getContentPane().add(btnNewButton_4);
			
		
		JButton btnSales = new JButton("Sales");
		btnSales.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Sales sales = new Sales(id,table);
				frame.dispose();*/
				try
				{
					String[] choices = {"Day", "Month", "Year"};
					
					String answerTable = null;
					
				    answerTable = (String) JOptionPane.showInputDialog(frame, "Choose the Format Date :",null, JOptionPane.QUESTION_MESSAGE, null,choices, null);
				    
				    if(answerTable.equals("Day"))
				    {
				    	try {
							String day;
							day = JOptionPane.showInputDialog(null, "Enter a Date :");
							String tmpSalesDay = salesDay2(day);
							

							JTextArea textArea = new JTextArea(6, 25);
						    textArea.setText(tmpSalesDay);
						    textArea.setEditable(false);
							
							JOptionPane.showMessageDialog(frame, textArea);
							
						}catch(Exception s) {}
				    }
				    
				    else if(answerTable.equals("Month"))
				    {
				    	String year = JOptionPane.showInputDialog(null, "Enter a Year :");
						String month = JOptionPane.showInputDialog(null, "Enter a Month :");
				    	
				    	try {
							
							int sales[][] = salesMonth(Integer.parseInt(month),Integer.parseInt(year)); 
							
							/*for(int i =0; i < 31; i++) {
								System.out.println(sales[0][i]);
								System.out.println(sales[1][i]);
								System.out.println();
							}*/
						
							DefaultCategoryDataset dataset = new DefaultCategoryDataset();
							
							for(int i =0; i < 31; i++) {
								int tmp = sales[0][i] + 1;
								dataset.setValue(sales[1][i], "Sales", tmp+"");	
							}
							
							JFreeChart chart = ChartFactory.createBarChart("Sales "+month+"/"+year,"","",dataset, PlotOrientation.VERTICAL,false,false,false);
							CategoryPlot catPlot = chart.getCategoryPlot();
							catPlot.setRangeGridlinePaint(Color.BLACK);
							ChartPanel charPanel = new ChartPanel(chart);
												
							JPanel panel = new JPanel();
							panel.add(charPanel, BorderLayout.CENTER);
							panel.validate();
							
							JOptionPane.showMessageDialog(frame, panel);
							
						}catch(Exception s) {}
				    }
				    
				    else if(answerTable.equals("Year"))
				    {
				    	try {
							String year = JOptionPane.showInputDialog(null, "Enter a Year :");
							int sales[][] = salesYear(Integer.parseInt(year)); 
							
							/*for(int i =0; i < 12; i++) {
								System.out.println(sales[0][i]);
								System.out.println(sales[1][i]);
								System.out.println();
							}*/
						
							DefaultCategoryDataset dataset = new DefaultCategoryDataset();
							
							for(int i =0; i < 12; i++) {
								int tmp = sales[0][i] + 1;
								dataset.setValue(sales[1][i], "Sales", tmp+"");	
							}
							
							JFreeChart chart = ChartFactory.createBarChart("Sales "+year,"","",dataset, PlotOrientation.VERTICAL,false,false,false);
							CategoryPlot catPlot = chart.getCategoryPlot();
							catPlot.setRangeGridlinePaint(Color.BLACK);
							ChartPanel charPanel = new ChartPanel(chart);
							
							JPanel panel = new JPanel();
							panel.add(charPanel, BorderLayout.CENTER);
							panel.validate();
							
							JOptionPane.showMessageDialog(frame, panel);
							
						}catch(Exception s) {}
				    }
				}
				
				catch(Exception s) {}
				
			
			    
			
			}
		});
		btnSales.setBounds(35, 171, 837, 59);
		frame.getContentPane().add(btnSales);
		
		JButton btnNewButton_1 = new JButton("Statistics");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					String[] choices = {"Day", "Month", "Year"};
					
					String answerTable = null;
					
				    answerTable = (String) JOptionPane.showInputDialog(frame, "Choose the Format Date :",null, JOptionPane.QUESTION_MESSAGE, null,choices, null);
				    
				    if(answerTable.equals("Day"))
				    {
				    	try {
							String  date = JOptionPane.showInputDialog(null, "Enter a Date :");
							int stats[] = statsPizzaDay(date); 
						
							DefaultCategoryDataset dataset = new DefaultCategoryDataset();
							
							String tmp [] = {"Marguerita","Veggie","Meat","Best","Chicken", "Cheese"};
						
							for(int i =0; i < 6; i++) {
								dataset.setValue(stats[i],"Stats",tmp[i]);	
							}
			
							
							JFreeChart chart = ChartFactory.createBarChart("Stats ","","",dataset, PlotOrientation.VERTICAL,false,false,false);
							CategoryPlot catPlot = chart.getCategoryPlot();
							catPlot.setRangeGridlinePaint(Color.BLACK);
							ChartPanel charPanel = new ChartPanel(chart);
							
							JPanel panel = new JPanel();
							panel.add(charPanel, BorderLayout.CENTER);
							panel.validate();
							
							JOptionPane.showMessageDialog(frame, panel);
						}catch(Exception s) {}
				    }
				    
				    else if(answerTable.equals("Month"))
				    {
				    	try {
							
				    		String  year = JOptionPane.showInputDialog(null, "Enter a Year :");
				    		String  month = JOptionPane.showInputDialog(null, "Enter a Month:");
							
							
							int stats[] = statsPizzaMonth2(month, year); 
						
							DefaultCategoryDataset dataset = new DefaultCategoryDataset();
							
							String tmp [] = {"Marguerita","Veggie","Meat","Best","Chicken", "Cheese"};
						
							for(int i =0; i < 6; i++) {
								dataset.setValue(stats[i],"Stats",tmp[i]);	
							}
			
							
							JFreeChart chart = ChartFactory.createBarChart("Stats ","","",dataset, PlotOrientation.VERTICAL,false,false,false);
							CategoryPlot catPlot = chart.getCategoryPlot();
							catPlot.setRangeGridlinePaint(Color.BLACK);
							ChartPanel charPanel = new ChartPanel(chart);
							JPanel panel = new JPanel();
							panel.add(charPanel, BorderLayout.CENTER);
							panel.validate();
							
							JOptionPane.showMessageDialog(frame, panel);
						}catch(Exception s) {}
				    }
				    
				    else if(answerTable.equals("Year"))
				    {
				    	try {
							
							String  year = JOptionPane.showInputDialog(null, "Enter a Year:");
							
							int stats[] = statsPizzaYear(year); 
						
							DefaultCategoryDataset dataset = new DefaultCategoryDataset();
							
							String tmp [] = {"Marguerita","Veggie","Meat","Best","Chicken", "Cheese"};
						
							for(int i =0; i < 6; i++) {
								dataset.setValue(stats[i],"Stats",tmp[i]);	
							}
			
							
							JFreeChart chart = ChartFactory.createBarChart("Stats ","","",dataset, PlotOrientation.VERTICAL,false,false,false);
							CategoryPlot catPlot = chart.getCategoryPlot();
							catPlot.setRangeGridlinePaint(Color.BLACK);
							ChartPanel charPanel = new ChartPanel(chart);
							JPanel panel = new JPanel();
							panel.add(charPanel, BorderLayout.CENTER);
							panel.validate();
							
							JOptionPane.showMessageDialog(frame, panel);
						}catch(Exception s) {}
				    }
				}
				
				catch(Exception n) {}
				
				
		
			}
		});
		btnNewButton_1.setBounds(35, 101, 837, 59);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblManager = new JLabel("Manager Menu");
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblManager.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblManager.setBounds(347, 19, 216, 59);
		frame.getContentPane().add(lblManager);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 381, 837, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 89, 837, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnSeeBill = new JButton("See Bill");
		btnSeeBill.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSeeBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try
				{
						
					String  date = JOptionPane.showInputDialog(null, "Enter a Date :");
					
		    		Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
					
					ResultSet resultat2 = connexion.createStatement().executeQuery("SELECT nb_order From commande WHERE day_date = '" + date +"'");
				
				
					ArrayList list = new ArrayList();
					
					while(resultat2.next())
					{
						int tmp = resultat2.getInt("nb_order");
						String tmp2 = "" + tmp;
						list.add(tmp2);
					}
				
					Object[] objectList = list.toArray();
					
					String[] choices = Arrays.copyOf(objectList, objectList.length, String[].class);
						
					String answerOrder = null;
					
					//UIManager.put("OptionPane.minimumSize",new Dimension(500,500)); 
				
					
					answerOrder = (String) JOptionPane.showInputDialog(frame, "Choose the Order :", null, JOptionPane.QUESTION_MESSAGE, null, choices, null);
					
					if(answerOrder != null)
				    {
				    	int total = 0;
				    	int tva_20 = 0;
				    	int tva_10 = 0;
				    	
				    	String bill = "\n\n\t                 --------------------------\n\t                        PIZZA PINOT\n\t                 --------------------------\n\t"
				    			+ "                       RESTAURANT\n\n\t                85, Boulevard Voltaire\n\t                        75011 PARIS\n"
				    			+ "\t                 TEL : 01.08.68.27.69\n\n\n                Order n ° : " + answerOrder + " \n\n";
				    	
			    		//Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
						
						ResultSet resultat = connexion.createStatement().executeQuery("SELECT margherita, veggie, meat, best, chicken, cheese, tiramisu, ice_cream, apple_pie, coca, fanta, ice_tea FROM commande WHERE nb_order = " + Integer.parseInt(answerOrder));
						
						String[] menu = {"Margherita", "Veggie", "Meat", "Best", "Chicken", "Cheese", "Tiramisu", "Ice_Cream", "Apple_Pie", "Coca", "Fanta", "Ice_Tea"};
						
						resultat.next();
						
						
						for(int i = 0; i < 12; i++)
						{
							if(resultat.getInt(menu[i]) != 0) 
							{
								
								
								ResultSet resultat3 = connexion.createStatement().executeQuery("SELECT price From menu WHERE plate = '" + menu[i] +"'");
								
								resultat3.next();
								
								int price = resultat3.getInt("price") * resultat.getInt(menu[i]);
								
								total += price;
								
								if(menu[i].equals("Margherita") || menu[i].equals("Veggie")|| menu[i].equals("Meat")|| menu[i].equals("Best")|| menu[i].equals("Chicken")
									|| menu[i].equals("Cheese")|| menu[i].equals("Tiramisu")|| menu[i].equals("Ice_Cream")|| menu[i].equals("Apple_Pie")) tva_20 += price;
								
								else tva_10 += price;
								
								bill += "\t " + resultat.getInt(menu[i]) + "   " + menu[i] + "\t\t"+ price + "\n";
							}
							
						}
							
						ResultSet resultat4 = connexion.createStatement().executeQuery("SELECT now()");
						
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
								+ "               TVA 20%                    " + df.format(tva_incl_20) + "               " + df.format(TVA_20) + "             " + df.format(TTC_20) + "\n"
								+ "               TVA 10%                    " + df.format(tva_incl_10) + "                " + df.format(TVA_10) + "             " + df.format(TTC_10) + "\n"
								+ "               Service 14%              " + df.format(service) + "\n"
								+ "       -------------------------------------------------------------------------------------\n                TOTAL\t\t\t  "
								+ total + "€\n\n                 Date of Payment : " + date + "\n\n\t               THANKS TO VISIT US\n\t"
										+ "                    SEE YOU SOON\n";
						
						
						JTextArea textArea = new JTextArea(6, 25);
					    textArea.setText(bill);
					    textArea.setEditable(false);
					    JScrollPane scrollPane = new JScrollPane(textArea);
					    scrollPane.setPreferredSize(new Dimension(400,680));
					    JOptionPane.showMessageDialog(frame, scrollPane);
					  
														    	
				    }
					
				
			
						
						
				}catch(Exception s) {}
				 
			}
		});
		btnSeeBill.setBounds(35, 311, 837, 59);
		frame.getContentPane().add(btnSeeBill);
	}
}
