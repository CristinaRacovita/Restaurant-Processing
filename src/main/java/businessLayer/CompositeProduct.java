package businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

	private ArrayList<MenuItem> compositeProducts = new ArrayList<MenuItem>();

	public CompositeProduct(String nume,ArrayList<MenuItem> products){
		super(nume);
		this.compositeProducts=products;
		float pret = computePrice();		
		super.setPret(pret);
		
	}
	
	float computePrice() {
		float suma =0;
		for(MenuItem m : compositeProducts) {
			suma+=m.getPret();
			//System.out.println(suma+"!!!!!");
		}
		return suma;
	}

	public ArrayList<MenuItem> getCompositeProducts() {
		return compositeProducts;
	}

	public void setCompositeProducts(ArrayList<MenuItem> compositeProducts) {
		this.compositeProducts = compositeProducts;
	}


}
