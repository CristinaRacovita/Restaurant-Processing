package presentationLayer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;

public class AdministratorGraphicalUserInterface extends JFrame {

	Restaurant restaurant = new Restaurant();
	JButton newMenuItem = new JButton("Adauga Produs");
	JButton deleteMenuItem = new JButton("Sterge Produs");
	JButton editMenuItem = new JButton("Editeaza Produs");
	DefaultTableModel model = new DefaultTableModel();
	JTextField nume = new JTextField(14);
	JTextField pret = new JTextField(14);
	JTextField ingrediente = new JTextField(14);
	JLabel numeLabel = new JLabel("Nume produs : ");
	JLabel ingredienteLabel = new JLabel("Ingrediente : ");
	JLabel pretLabel = new JLabel("Pret produs : ");
	// Object[] s = { "Nume", "Pret", "Ingrediente" };
	JTable tabel = new JTable();
	// Object[] row = { "lapte", 1, "-" };

	public AdministratorGraphicalUserInterface(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.setTitle("Admin");
		tableFiller(tabel, this.restaurant.getProduse(), model);
		tabel.setModel(model);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel p9 = new JPanel();
		JPanel p10 = new JPanel();
		JPanel p11 = new JPanel();
		JPanel p12 = new JPanel();
		JPanel p13 = new JPanel();

		p9.add(nume);
		p10.add(pret);
		p11.add(ingrediente);

		p12.add(ingredienteLabel);
		p12.add(p11);

		// tabel = new JTable(coloane(restaurant),s);
		p1.add(newMenuItem);
		p2.add(deleteMenuItem);
		p3.add(editMenuItem);

		tabel.setRowHeight(20);
		JScrollPane tableScrollPane = new JScrollPane(tabel);
		p4.add(tableScrollPane);
		JPanel p5 = new JPanel();
		p5.add(p1);
		p5.add(p2);
		p5.add(p3);
		p6.add(numeLabel);
		p6.add(p9);
		p6.setLayout(new BoxLayout(p6, BoxLayout.Y_AXIS));
		p7.add(pretLabel);
		p7.add(p10);
		p7.setLayout(new BoxLayout(p7, BoxLayout.Y_AXIS));
		p8.add(p6);
		p8.add(p7);
		p8.setLayout(new BoxLayout(p8, BoxLayout.Y_AXIS));
		p5.setLayout(new BoxLayout(p5, BoxLayout.X_AXIS));
		p5.add(p8);
		p13.add(p5);
		p13.add(p12);
		p13.setLayout(new BoxLayout(p13, BoxLayout.Y_AXIS));

		this.setLayout(new GridLayout(0, 1, 50, 50));
		this.setSize(600, 700);
		this.add(p4);
		this.add(p13);
		this.setVisible(true);

		newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = nume.getText();
				// System.out.println(pret.getText()+"salut ola");
				if (!pret.getText().equals("")) {
					float p = Float.parseFloat(pret.getText());
					MenuItem m = new BaseProduct(name, p);
					AdministratorGraphicalUserInterface.this.restaurant.createMenuItem(m);
					Object obj[] = { name, p, "-" };
					AdministratorGraphicalUserInterface.this.model.addRow(obj);
					AdministratorGraphicalUserInterface.this.tabel.setModel(model);
				} else {
					ArrayList<MenuItem> prod = new ArrayList<MenuItem>();
					float prett = 0;
					String ingr = ingrediente.getText();
					for (String i : ingr.split("\\, ")) {
						String numeProdus = i;
						// System.out.print(i);
						for (MenuItem it : AdministratorGraphicalUserInterface.this.restaurant.getProduse()) {
							if (numeProdus.equals(it.getNume())) {
								MenuItem menu = new BaseProduct(i, it.getPret());
								prod.add(menu);
								prett += it.getPret();
							}
						}
					}
					MenuItem add = new CompositeProduct(name, prod);
					AdministratorGraphicalUserInterface.this.restaurant.createMenuItem(add);
					Object[] obj = { name, prett, ingr };
					AdministratorGraphicalUserInterface.this.model.addRow(obj);
					AdministratorGraphicalUserInterface.this.tabel.setModel(model);
				}
				RestaurantSerializator serializator = new RestaurantSerializator("restaurant.txt");
				serializator.writeData(AdministratorGraphicalUserInterface.this.restaurant);
				AdministratorGraphicalUserInterface.this.repaint();
				AdministratorGraphicalUserInterface.this.revalidate();

			}
		});
		deleteMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = nume.getText();
				// System.out.println("Numele este "+name);
				int index = -1;

				for (MenuItem it : AdministratorGraphicalUserInterface.this.restaurant.getProduse()) {
					if (it.getNume().equals(name)) {
						AdministratorGraphicalUserInterface.this.restaurant.deleteMenuItem(it);
						break;
					}
				}

				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}

				tableFiller(tabel, AdministratorGraphicalUserInterface.this.restaurant.getProduse(), model);

				// AdministratorGraphicalUserInterface.this.model.removeRow(index);
				AdministratorGraphicalUserInterface.this.tabel.setModel(model);
				RestaurantSerializator serializator = new RestaurantSerializator("restaurant.txt");
				serializator.writeData(AdministratorGraphicalUserInterface.this.restaurant);
			}

		});
		editMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = nume.getText();
				// System.out.println(pret.getText()+"salut ola");
				if (!pret.getText().equals("")) { // produs basic
					float p = Float.parseFloat(pret.getText());
					MenuItem m = new BaseProduct(name, p);
					int index = -1;
					for (MenuItem item : AdministratorGraphicalUserInterface.this.restaurant.getProduse()) {
						index++;
						if (name.equals(item.getNume())) {
							break;
						}
					}
					AdministratorGraphicalUserInterface.this.restaurant.editMenuItem(m, index);
					int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					tableFiller(tabel, AdministratorGraphicalUserInterface.this.restaurant.getProduse(), model);
					AdministratorGraphicalUserInterface.this.tabel.setModel(model);
				} else {
					ArrayList<MenuItem> prod = new ArrayList<MenuItem>();
					int index = -1;
					for (MenuItem item : AdministratorGraphicalUserInterface.this.restaurant.getProduse()) {
						index++;
						if (name.equals(item.getNume())) {
							break;
						}
					}
					String ingr = ingrediente.getText();
					for (String i : ingr.split("\\, ")) {
						String numeProdus = i;
						// System.out.print(i);
						for (MenuItem it : AdministratorGraphicalUserInterface.this.restaurant.getProduse()) {
							if (numeProdus.equals(it.getNume())) {
								MenuItem menu = new BaseProduct(i, it.getPret());
								prod.add(menu);
							}
						}
					}
					MenuItem add = new CompositeProduct(name, prod);
					AdministratorGraphicalUserInterface.this.restaurant.editMenuItem(add, index);
					int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					tableFiller(tabel, AdministratorGraphicalUserInterface.this.restaurant.getProduse(), model);
					AdministratorGraphicalUserInterface.this.tabel.setModel(model);
				}
				RestaurantSerializator serializator = new RestaurantSerializator("restaurant.txt");
				serializator.writeData(AdministratorGraphicalUserInterface.this.restaurant);
				AdministratorGraphicalUserInterface.this.repaint();
				AdministratorGraphicalUserInterface.this.revalidate();

			}
		});
		this.setLocationRelativeTo(null);
		this.setLocation(this.getX()-600,this.getY());

	}

	void tableFiller(JTable tabel, ArrayList<MenuItem> m, DefaultTableModel modelTabel) {
		modelTabel.setColumnCount(3);
		String[] coloane = new String[3];
		coloane[0] = "Nume";
		coloane[1] = "Pret";
		coloane[2] = "Ingrediente";
		modelTabel.setColumnIdentifiers(coloane);
		for (MenuItem m1 : m) {
			if (m1 instanceof BaseProduct) {
				Object[] obj = { m1.getNume(), m1.getPret(), "-" };
				modelTabel.addRow(obj);
			} else if (m1 instanceof CompositeProduct) {
				Object[] obj = new Object[4];
				obj[0] = m1.getNume();
				// System.out.println(m1.getNume()+"m1");
				obj[1] = m1.getPret();
				obj[2] = "";
				for (MenuItem m2 : m1.getCompositeProducts()) {
					if (!m2.getNume().equals(m1.getNume())) {
						// System.out.println(m2.getNume()+"kaka");
						obj[2] += m2.getNume() + " ";
					}
				}
				modelTabel.addRow(obj);
			}
		}
		tabel.setModel(modelTabel);
	}

}
