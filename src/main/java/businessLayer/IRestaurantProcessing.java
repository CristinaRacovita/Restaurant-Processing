package businessLayer;

import java.io.PrintWriter;
import java.util.ArrayList;

public interface IRestaurantProcessing {

	//admin
	void createMenuItem(MenuItem m);
	void deleteMenuItem(MenuItem m);
	void editMenuItem(MenuItem m,int index);
	//waiter
	void createOrder(Order o);
	float computePriceOrder(Order o);
	void generateBill(Order o);
	
	ArrayList<MenuItem> getProduse();
	void setProduse(ArrayList<MenuItem> m);
}
