package businessLayer;

import java.util.ArrayList;

public class BaseProduct extends MenuItem {

	public BaseProduct(String nume, float price) {
		super(nume);
		super.setPret(price);
	}

	@Override
	float computePrice() {
		// TODO Auto-generated method stub
		return super.getPret();
	}

	@Override
	public ArrayList<MenuItem> getCompositeProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
