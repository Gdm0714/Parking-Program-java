import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Parking extends JFrame {
	private JPanel center, west;
	private JButton enter, exit, charge;
	private JLabel[] parking = new JLabel[12];

	public Parking() {
		setTitle("���� ���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		center = new JPanel();
		GridLayout grid = new GridLayout(3, 4);
		grid.setVgap(50);
		grid.setHgap(50);
		center.setLayout(grid);
		for (int i = 0; i < 12; i++) {
			parking[i] = new JLabel(i + 1 + "��");
			parking[i].setHorizontalAlignment(parking[i].CENTER);
			parking[i].setBackground(Color.GREEN);
			parking[i].setOpaque(true);
			center.add(parking[i]);
		}
		
		west = new JPanel();
		west.setLayout(new GridLayout(3, 1));
		enter = new JButton("����");
		exit = new JButton("����");
		charge = new JButton("����");
		west.add(enter);
		west.add(exit);
		west.add(charge);

		c.add(west, BorderLayout.WEST);
		c.add(center, BorderLayout.CENTER);

		setSize(800, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Parking();
	}
}
