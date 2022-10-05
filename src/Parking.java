import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;

import javax.swing.*;

public class Parking extends JFrame {
	private JPanel center, west;
	private JButton enter, exit, charge;
	public JLabel[] parking = new JLabel[12];
	public Parking() {
		setTitle("���� ���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		center = new JPanel();
		GridLayout grid = new GridLayout(3, 4);
		grid.setVgap(50);
		grid.setHgap(50);
		
		enter = new JButton("����");
		exit = new JButton("����");
		charge = new JButton("����");
		center.setLayout(grid);
		for (int i = 0; i < 12; i++) {
			parking[i] = new JLabel(i + 1 + "��");
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
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Exit();
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
	public class Enter extends JFrame {
		private JButton en = new JButton("����");
		private JTextField carnum = new JTextField("");
		private JTextField num = new JTextField("");
		public LocalTime entertime;
		public Enter() {
			setTitle("����");

			Container c = getContentPane();
			c.setLayout(new GridLayout(2, 2));
			en.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					entertime = LocalTime.now();
					parking[Integer.parseInt(num.getText()) - 1].setText("<html><body><center>" + carnum.getText() + "<br><br>" + entertime.getHour() 
					+ "�� " + entertime.getMinute() + "��" + "</center></body></html>");
					setVisible(false);
				}
			});

			c.add(carnum);
			c.add(num);
			c.add(en);
			setSize(300, 300);
			setVisible(true);
		}
	}

	public class Exit extends JFrame {
		private JButton ex = new JButton("����");
		private JButton back = new JButton("���ư���");
		private JTextField num = new JTextField("");
		public LocalTime exittime;
		public Exit() {
			setTitle("����");
			Container c = getContentPane();
			c.setLayout(new GridLayout(2, 2));
			ex.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					exittime = LocalTime.now();
					parking[Integer.parseInt(num.getText())-1].setText(num.getText() + "��");
					setVisible(false);
				}
			});
			back.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new Parking();
					setVisible(false);
				}
			});
			c.add(num);
			c.add(ex);
			c.add(back);
			setSize(300, 300);
			setVisible(true);
		}
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
