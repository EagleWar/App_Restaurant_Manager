import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

import java.util.Date;
import java.text.SimpleDateFormat;

public class RestaurantSystem 
{
	Table[] table = new Table[10]; // We create ten object of type Table
	
	static int nbOrder = 0; // number of an order its identify 
	
	static int nbPerson = 0;
	
	static String [][] pizzaStats = new String[2][6];
    String pizza[]={"marguerita", "veggie", "meat", "best", "chicken", "cheese" };
		
	Date date = new Date();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
	
	static String date1;
	
	static int caDay; // number of an order its identify 
	
	public RestaurantSystem()
	{
		int tab[] = {1,1,1,4,4,4,4,7,7,7};
		caDay = 0; // this variable represents the sales oh the day 
		
		pizzaStats[0] = pizza;
		for(int i = 0; i < 6; i++) pizzaStats[1][i] = "0";
		
		for(int i = 0; i < 10; i++) // we initialize the tables 
		{
			table[i] = new Table(i, tab[i],true, true, -1, "null", -1);
		}
		
		date1 = sdf.format(date);
		
		try
		{
		
			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
		
		
			
			ResultSet resultat3= connexion.createStatement().executeQuery("SELECT day_date FROM commande ORDER BY  nb_order DESC LIMIT 1");
			
			resultat3.next();
			
			if(resultat3.getString("day_date").equals(date1.substring(0, 10)))
			{
				
				ResultSet resultat4= connexion.createStatement().executeQuery("SELECT nb_order FROM commande ORDER BY  nb_order DESC LIMIT 1");
				
				resultat4.next();
				
				int order = resultat4.getInt("nb_order");
				
				nbOrder = order;
			}
			
			
		}
		catch(Exception l) {}

		try
		{
			
			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
		
			
			connexion.createStatement().executeUpdate("INSERT INTO stat_waiter (day_date, waiter_1, waiter_2, waiter_3, waiter_4) VALUES (now(),0,0,0,0)");
		}
	
		
		catch(Exception p)
		{
			
		}
		
		try
		{
			
			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
			try
			{

	
				{
					
					
		
					
					ResultSet resultat3= connexion.createStatement().executeQuery("SELECT ca_day FROM stats_sales WHERE day_date = (select date(now()))");
					
					resultat3.next();
										
					RestaurantSystem.setCaDay(resultat3.getInt("ca_day"));
									
					ResultSet resultat4= connexion.createStatement().executeQuery("SELECT nmb_person FROM stats_sales WHERE day_date = (select date(now()))");
					
					resultat4.next();
									
					RestaurantSystem.setNbPerson(resultat4.getInt("nmb_person"));
					
					
					
				}
				
			}
			catch(Exception b) {}
			
			connexion.createStatement().executeUpdate("INSERT INTO stats_sales (day_date, ca_day, nmb_person) VALUES (now(),0,0)");
		}
	
		
		catch(Exception p)
		{
			
		}
		
		
		try
		{
			
			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
			try
			{

	
				{
					
					
					String[] menu = {"margherita", "veggie", "meat", "best", "chicken", "cheese"};

					
					int i=0 ;
					while(i<6) {
	
						ResultSet resultat3= connexion.createStatement().executeQuery("SELECT "+menu[i]+"  FROM stats_pizza WHERE day_date = (select date(now()))");
						resultat3.next();
						
						RestaurantSystem.setPizzaStats(menu[i],resultat3.getInt(""+menu[i]+"")+"");
						
						i++;
						
					}
		
					
							
					
					
				}
				
			}
			catch(Exception b) {}
		
			
			connexion.createStatement().executeUpdate("INSERT INTO stats_pizza (day_date, margherita, veggie, meat, best, chicken, cheese) VALUES (now(),0,0,0,0,0,0)");
		
		}
	
		catch(Exception p)
		{
			
		}
		
		
		
		
		
		
		
		
		
		LoginSystem logSys = new LoginSystem(table);
	}
	
	public static void incrementNbPerson(String persons)
	{
		nbPerson += Integer.parseInt(persons);
	}
	
	public static void setNbPerson(int nmbperson)
	{
		nbPerson = nmbperson;
	}
	
	public static int getNbPerson()
	{		
		return nbPerson;
	}
	
	public static void incrementNbOrder()
	{
		nbOrder ++;
	}
	
	public static int getNbOrder()
	{
		return nbOrder;
	}
	
	public static String getDate()
	{
		return date1;
	}
	
	public static int getCaDay()
	{
		return caDay;
	}
	
	public static void setCaDay( int total)
	{
		caDay += total;
	}
	
	public static void setPizzaStats(String pizza, String quantity) {
		int tmpTotal = 0;
		for(int i=0;i<6;i++)
			if(pizza.equals(pizzaStats[0][i])) {
				
				tmpTotal = (Integer.parseInt(pizzaStats[1][i])+Integer.parseInt(quantity));
				pizzaStats[1][i] = tmpTotal+"";
			}
	}
	
	public static String getPizzaStats() {
		String tmpPizzaStats = "";
		String tmpPizzaTotal = "";
		int totalPizza = 0;
		for(int i = 0; i<6; i++) {
			tmpPizzaStats += pizzaStats[0][i]+ ": " + pizzaStats[1][i];
			tmpPizzaStats += "\n";
			totalPizza += Integer.parseInt(pizzaStats[1][i]);
		}
		
		
		return tmpPizzaStats + "\nTotal Pizza : " + totalPizza;
	}
	
	public static String [][]getPizzaStats2() {
		return pizzaStats;
	}

	
}
