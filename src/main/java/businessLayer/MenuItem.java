package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class MenuItem implements Serializable {
	private String nume;
	private float pret;

	public MenuItem(String nume) {
		this.nume=nume;
	}
	
	abstract float computePrice();
	
	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public float getPret() {
		return pret;
	}

	public void setPret(float pret) {
		this.pret = pret;
	}

	public abstract ArrayList<MenuItem> getCompositeProducts();
}
