package businessLayer;

import java.util.Date;

public class Order {
	private int orderId;
	private Date data;
	private int masa;

	public Order(int id,Date data,int masa) {
		this.orderId=id;
		this.data=data;
		this.masa=masa;
	}
	
	public Order() {
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getMasa() {
		return masa;
	}

	public void setMasa(int masa) {
		this.masa = masa;
	}
	
	@Override
	public boolean equals(Object obj) {
		Order o = (Order)obj;
		if(this.orderId == o.orderId) {
			if(this.data.equals(o.data)) {
				if(this.masa == o.masa) {
					return true;
				}
			}
		}
		return false;
	}

	public int hashCode(){
		return ((orderId+masa)*99)/13;
	}
}
