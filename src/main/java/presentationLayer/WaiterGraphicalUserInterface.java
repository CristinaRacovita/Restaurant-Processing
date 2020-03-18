package presentationLayer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;

public class WaiterGraphicalUserInterface extends JFrame {

	Restaurant restaurant = new Restaurant();
	JButton addComanda = new JButton("Adauga o comanda");
	JButton chitanta = new JButton("Chitanta");
	JTable tabel = new JTable();
	Object[] s = { "Id Comanda", "Data", "Nr. masa" };
	JTextField id = new JTextField(3);
	JTextField data = new JTextField(10);
	JTextField nrMasa = new JTextField(3);
	JLabel idLabel = new JLabel("Id comanda : ");
	JLabel dataLabel = new JLabel("Data : ");
	JLabel nrMasaLabel = new JLabel("Nr. masa : ");
	JFrame menuItems = new JFrame();
	ArrayList<JCheckBox> cutii = new ArrayList<JCheckBox>();
	ArrayList<String> ingredienteAlese = new ArrayList<String>();
	JButton done = new JButton("Gata");
	DefaultTableModel model = new DefaultTableModel();
	Order o = new Order();

	public WaiterGraphicalUserInterface(Restaurant r) {
		this.restaurant = r;
		this.setTitle("Chelner");
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);

		tabel.setRowHeight(20);

		model.setColumnIdentifiers(s);
		tabel.setModel(model);
		this.setLayout(new GridLayout(0, 1, 50, 50));

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();

		p1.add(addComanda);
		p2.add(chitanta);
		p3.add(id);
		p4.add(data);
		p5.add(nrMasa);

		JPanel p6 = new JPanel();
		p6.add(p1);
		p6.add(p2);
		p6.setLayout(new BoxLayout(p6, BoxLayout.Y_AXIS));

		JPanel p7 = new JPanel();
		p7.add(idLabel);
		p7.add(p3);
		p7.setLayout(new BoxLayout(p7, BoxLayout.Y_AXIS));

		JPanel p8 = new JPanel();
		p8.add(dataLabel);
		p8.add(p4);
		p8.setLayout(new BoxLayout(p8, BoxLayout.Y_AXIS));

		JPanel p9 = new JPanel();
		p9.add(nrMasaLabel);
		p9.add(p5);
		p9.setLayout(new BoxLayout(p9, BoxLayout.Y_AXIS));

		JPanel p10 = new JPanel();
		p10.add(p7);
		p10.add(p8);
		p10.add(p9);
		p10.setLayout(new BoxLayout(p10, BoxLayout.Y_AXIS));

		JPanel p11 = new JPanel();
		p11.add(p10);
		p11.add(p6);
		p11.setLayout(new BoxLayout(p11, BoxLayout.X_AXIS));

		JScrollPane tableScrollPane = new JScrollPane(tabel);
		JPanel p12 = new JPanel();
		p12.add(tableScrollPane);

		addComanda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addIngredienteFrame(cutii);
				for (final JCheckBox c : cutii) {
					// System.out.println(c.getText());
					c.addItemListener(new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent e) {
							if (e.getStateChange() == 1)
								ingredienteAlese.add(c.getText());
							// System.out.println(c.getText());
						}

					});

				}
				final ArrayList<MenuItem> prod = new ArrayList<MenuItem>();
				done.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						int index = 0;
						while (index < ingredienteAlese.size()) {
							for (MenuItem menu : WaiterGraphicalUserInterface.this.restaurant.getProduse()) {
								if (menu.getNume().equals(ingredienteAlese.get(index))) {
									prod.add(menu);
								}
							}
							index++;
						}
						for (MenuItem men : prod) {
							//System.out.println(men.getNume() + "AICI" + men.getPret());
						}

						SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
						String dateInString = data.getText();
						Date date;
						try {
							date = sdf.parse(dateInString);
						} catch (ParseException e) {
							date = null;
							e.printStackTrace();
						}
						Order o = new Order(Integer.parseInt(id.getText()), date, Integer.parseInt(nrMasa.getText()));
						WaiterGraphicalUserInterface.this.restaurant.createOrder(o);
						for (MenuItem menu : prod) {
							if (menu != null) {
								//System.out.println(menu.getNume() + "ALO" + menu.getPret());

								WaiterGraphicalUserInterface.this.restaurant.addProdus(menu, o);
							}
						}
						fillerTable(tabel, WaiterGraphicalUserInterface.this.model, o);
						WaiterGraphicalUserInterface.this.repaint();
						WaiterGraphicalUserInterface.this.revalidate();
						WaiterGraphicalUserInterface.this.o = o;
					}
				});

				// aici stim ce lucruri din meniu contine comanda

			}

		});
		chitanta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					//System.out.println(o.getOrderId()+" "+o.getMasa()+" "+o.getData()+"ALOALOALO");
					//System.out.println(o.getMasa() + "la");
					WaiterGraphicalUserInterface.this.restaurant.generateBill(o);
				
			}

		});

		this.setVisible(true);
		this.add(p12);
		this.add(p11);

	}

	public void addIngredienteFrame(ArrayList<JCheckBox> c) {
		menuItems.setTitle("Produse Meniu");
		menuItems.setSize(700, 700);
		menuItems.setLayout(new GridLayout(0, 1, 50, 50));
		menuItems.setVisible(true);

		for (MenuItem m : restaurant.getProduse()) {
			// System.out.println(m.getNume());
			JCheckBox checkbox = new JCheckBox(m.getNume());
			c.add(checkbox);
			// checkbox.setBounds(100, 100, 50, 50);
			menuItems.add(checkbox);
		}
		menuItems.add(done);
	}

	public void fillerTable(JTable tabel, DefaultTableModel model, Order o) {
		Object[] obj = { o.getOrderId(), o.getData(), o.getMasa() };
		model.addRow(obj);
	}

}
