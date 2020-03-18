package Main;

import businessLayer.BaseProduct;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;
import presentationLayer.AdministratorGraphicalUserInterface;
import presentationLayer.ChefGraphicalUserInterface;
import presentationLayer.WaiterGraphicalUserInterface;

public class MainClass {

	public static void main(String[] args) {
		Restaurant r = new Restaurant();/*
	    r.createMenuItem(new BaseProduct("lapte",4));
	    r.createMenuItem(new BaseProduct("miere",2));
	    r.createMenuItem(new BaseProduct("frisca",5));
	    r.createMenuItem(new BaseProduct("banana",3));
	    r.createMenuItem(new BaseProduct("capsuni",10));
	    r.createMenuItem(new BaseProduct("inghetata",15));*/
	    RestaurantSerializator serializator = new RestaurantSerializator("restaurant.txt");
	    serializator.readData(r);
	    serializator.writeData(r);
	    

		// view
		ChefGraphicalUserInterface chef = new ChefGraphicalUserInterface(r);
		AdministratorGraphicalUserInterface admin = new AdministratorGraphicalUserInterface(r);
		WaiterGraphicalUserInterface waiter = new WaiterGraphicalUserInterface(r);
		r.addObserver(chef);
	}

}
