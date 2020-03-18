package businessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import dataLayer.FileWriter;

public class Restaurant extends Observable implements IRestaurantProcessing { // are o lista de observable

	private ArrayList<MenuItem> produse = new ArrayList<MenuItem>();
	private HashMap<Order, ArrayList<MenuItem>> comenzi = new HashMap<Order, ArrayList<MenuItem>>();

	/*
	 * @inv produse!=null
	 * @pre m!=null
	 */
	public void createMenuItem(MenuItem m) {
		assert m!=null : "No menuItem";
		// System.out.println(m.getPret()+"la");
		produse.add(m);
	}

	/*
	 * @pre m!=null
	 */
	public void deleteMenuItem(MenuItem m) {
		assert m!=null : "No menuItem";
		produse.remove(m);
	}

	/*
	 * @inv produse != null
	 * @pre m!=null
	 * @pre index<0
	 */
	public void editMenuItem(MenuItem m, int index) {
		assert m!=null : "No menuItem";
		assert index<0 : "No index";
		produse.remove(index);
		produse.add(m);
	}

	/*
	 * @inv comenzi != null
	 * @pre o!= null
	 * @post lista != null
	 */
	public void createOrder(Order o) {
		assert o!=null : "No order";
		assert comenzi!=null : "nu sunt comenzi";

		ArrayList<MenuItem> lista = new ArrayList<MenuItem>();
		if (!comenzi.containsKey(o)) {
			comenzi.put(o, lista);
		}
	}

	/*
	 *@pre m!=null
	 *@pre o!=null
	 *@post items!=null 
	 */
	public void addProdus(MenuItem m, Order o) {
		
		assert o!=null : "No order";
		assert m!=null : "No menuItem";

		
		ArrayList<MenuItem> items = comenzi.get(o); // contine o referinta la acest array , deci tot la comenzi se a

		for (MenuItem menu : items) {
			// System.out.println(menu.getNume()+"ASTEA SUNT");
		}

		// adauga
		if (items == null) {
			System.out.println("lol");
		} else
			items.add(m);
		StringBuilder s = new StringBuilder();
		s.append(o.getOrderId() + " " + o.getMasa() + " " + o.getData() + "\n" + "comanda : ");
		for (MenuItem item : items) {
			if (item instanceof CompositeProduct)
				s.append(item.getNume() + " ");
		}
		if (m instanceof CompositeProduct) {
			// ca sa stie seful ca trebuie sa gateasca
			// System.out.println("LOLUn produs composite");
			setChanged();
			notifyObservers(s.toString());
		}

	}

	/*
	 * @pre o!=null
	 */
	public float computePriceOrder(Order o) {
		assert o!=null:"No order";
		float price = 0;
		ArrayList<MenuItem> ingrediente = comenzi.get(o);
		for (MenuItem p : ingrediente) {
			price += p.computePrice();
		}
		return price;
	}

	/*
	 * @pre o!=null
	 * @post ingrediente!=null
	 */
	public void generateBill(Order o) {
		assert o!=null : "No order";

		ArrayList<MenuItem> ingrediente = comenzi.get(o);
		StringBuilder s = new StringBuilder();
		FileWriter f = new FileWriter();
		// System.out.println(o.getOrderId()+"PRINT");
		s.append("Comanda : " + o.getOrderId() + "\n");
		s.append("Masa : " + o.getMasa() + "\n");
		s.append("Data : " + o.getData() + "\n");
		s.append("Ingrediente : " + "\n");
		for (MenuItem m : ingrediente) {
			s.append(m.getNume() + " ");
		}
		s.append("\n");
		s.append("Pret : " + computePriceOrder(o) + "\n");
		f.writeBill("Chitanta.txt", s.toString());

	}

	public ArrayList<MenuItem> getProduse() {
		return produse;
	}

	public void setProduse(ArrayList<MenuItem> produse) {
		this.produse = produse;
	}

	public HashMap<Order, ArrayList<MenuItem>> getComenzi() {
		return comenzi;
	}

	public void setComenzi(HashMap<Order, ArrayList<MenuItem>> comenzi) {
		this.comenzi = comenzi;
	}
}
