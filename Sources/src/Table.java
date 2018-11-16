
public class Table // each table on the restaurant has unique attributes
{
	int number; // the number of the table 0-9
	int size1; // number1 of person that it can host
	int size2; // number2 of person that it can host
	int size3; // number3 of person that it can host
	boolean state; // the state of the table, used or not used
	boolean stateOrder; // the state of the order linked to the table, already order or doesn't order
	int order; // the number of the order
	String waiterId;
	int delivered;
	
	public Table(int number, int size1, boolean state, boolean stateOrder, int order, String waiterId, int delivered) 
	{
		this.number = number + 1;
		this.size1 = size1 ;
		this.size2 = size1 + 1;
		this.size3 = size1 + 2;
		this.state = state;
		this.stateOrder = stateOrder;
		this.order = order;
		this.waiterId = waiterId;
		this.delivered = delivered;
	};
	
	public void changeState() // these method permit to change the state of a table
	{
		if(this.state == true) this.state = false;
		else if(this.state == false) this.state = true;
	}
	
	public void changeStateOrder() // these method permit to change the state of an order
	{
		if(this.stateOrder == true) this.stateOrder = false;
		else if(this.stateOrder == false) this.stateOrder = true;
	}
	
	public void display()
	{
		System.out.println("Order : " + this.order + "\nId : " + this.waiterId + "\nDelivered :" + this.delivered + "\nState :" +  this.state + "\nState Order :" + this.stateOrder);
	}
	
	public void displaySize()
	{
		System.out.println("Size1 : " + this.size1 + "\nSize2 : " + this.size2 + "\nSize3 :" + this.size3 + "\nState :" +  this.state);
	}
	
	public boolean state()
	{
		return this.state;
	}
	
	public int number()
	{
		return this.number;
	}
	
	public boolean stateOrder()
	{
		return this.stateOrder;
	}
	
	public void orderCreate(int order)
	{
		this.order = order;
	}
	
	public int order()
	{
		return this.order;
	}
	
	public void reset()
	{
		this.state = true;
		this.stateOrder = true;
		this.order = -1;
		this.delivered = -1;
		this.waiterId = "null";
	}
	
	public void setId(String id)
	{
		waiterId = id;
	}
	
	public String getId()
	{
		return waiterId;
	}
	
	public int size1()
	{
		return size1;
	}
	
	public int size2()
	{
		return size2;
	}
	
	public int size3()
	{
		return size3;
	}

	
	public boolean stateTable()
	{
		return this.state;
	}
	
	public int getDelivered()
	{
		return this.delivered;
	}
	
	public void changeStateDelivered() // these method permit to change the state of an order
	{
		if(this.delivered == -1) this.delivered = 0;
		else if(this.delivered == 0) this.delivered = -1;
	}
	
	
}











