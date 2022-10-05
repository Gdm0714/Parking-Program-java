import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Parking extends JFrame {
	private JPanel center, west;
	private JButton enter, exit, charge;
	public JLabel[] parking = new JLabel[12];
	public Parking() {
		setTitle("주차 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		center = new JPanel();
		GridLayout grid = new GridLayout(3, 4);
		grid.setVgap(50);
		grid.setHgap(50);
		enter = new JButton("입차");
		exit = new JButton("출차");
		charge = new JButton("정산");
		center.setLayout(grid);
		for (int i = 0; i < 12; i++) {
			parking[i] = new JLabel(i + 1 + "번");
			parking[i].setHorizontalAlignment(parking[i].CENTER);
			parking[i].setBackground(Color.GREEN);
			parking[i].setOpaque(true);
			center.add(parking[i]);
			parking[i].addMouseListener(new mouselistener());
		}
		
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Enter();
				setVisible(false);
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Exit();
				setVisible(false);
			}
		});
		west = new JPanel();
		west.setLayout(new GridLayout(3, 1));

		west.add(enter);
		west.add(exit);
		west.add(charge);

		c.add(west, BorderLayout.WEST);
		c.add(center, BorderLayout.CENTER);

		setSize(800, 500);
		setVisible(true);
	}
	class mouselistener extends MouseAdapter{
		public void mouseEntered(MouseEvent e) {
			JLabel jl = (JLabel)e.getSource();
			jl.setBackground(Color.GRAY);
		}

		public void mousePressed(MouseEvent e) {
			JLabel jl = (JLabel)e.getSource();
			jl.setBackground(Color.YELLOW);
		}

		public void mouseExited(MouseEvent e) {
			JLabel jl = (JLabel)e.getSource();
			jl.setBackground(Color.GREEN);
		}
	}

	public static void main(String[] args) {
		new Parking();
	}
}
