package presentationLayer;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import businessLayer.Restaurant;

public class ChefGraphicalUserInterface extends JFrame implements Observer  {

	public void update(Observable arg0, Object arg1) {
		//System.out.println("lala");
		comanda.removeAll();
		JOptionPane.showMessageDialog(null, "Ati primit o noua comanda", "COMANDA!", JOptionPane.INFORMATION_MESSAGE);
		String s = (String)arg1;
		//System.out.println(s);
		comanda.append(s);
		this.repaint();
		this.revalidate();
      //se modifica frame ul		
	}
	
	JTextArea comanda = new JTextArea();
	
	public ChefGraphicalUserInterface(Restaurant restaurant) {
		this.setTitle("Bucatar");
	    comanda.setPreferredSize(new Dimension(300,400));
		this.setSize(500, 600);
		JPanel panel1 = new JPanel();
		panel1.add(comanda);
		this.add(panel1);
		this.setLocationRelativeTo(null);
		this.setLocation(this.getX()+600, this.getY());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	
}
