

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Parking extends JFrame {

	private JPanel center, west;

	private JButton enter, exit;

	public JLabel charge2;

	boolean end;

	LocalDateTime []d1 = new LocalDateTime[12];

	LocalDateTime []d2 = new LocalDateTime[12];

	long d3;

	String s;

	public JLabel[] parking = new JLabel[12];
	public LocalDateTime entertime;
	public LocalDateTime exittime;

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

		charge2 = new JLabel("정산");

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

				Enter enter = new Enter();

			}

		});

		exit.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				Exit exit = new Exit();


			}

		});

		west = new JPanel();

		west.setLayout(new GridLayout(3, 1));

		west.add(enter);

		west.add(exit);

		west.add(charge2);

		c.add(west, BorderLayout.WEST);

		c.add(center, BorderLayout.CENTER);

		setSize(800, 500);

		setVisible(true);

	}

	public class Enter extends JFrame {

		private JButton en = new JButton("입차");

		private JTextField carnum = new JTextField("");

		private JTextField num = new JTextField("");

		public Enter() {

			setTitle("입차");

			Container c = getContentPane();

			c.setLayout(new GridLayout(2, 2));

			en.addActionListener(new ActionListener() {

				@Override

				public void actionPerformed(ActionEvent e) {

					// TODO Auto-generated method stub

					entertime = LocalDateTime.now();
					parking[Integer.parseInt(num.getText()) - 1]
							.setText("<html><body><center>" + carnum.getText() + "<br><br>" + entertime.getHour()

									+ "시 " + entertime.getMinute() + "분" + "</center></body></html>");
					d1[Integer.parseInt(num.getText()) - 1] = LocalDateTime.of(entertime.getYear(), entertime.getMonth(), entertime.getDayOfMonth(),
							entertime.getHour(), entertime.getMinute(), entertime.getSecond());
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

		private JButton ex = new JButton("출차");

		private JButton back = new JButton("돌아가기");

		private JTextField num = new JTextField("");


		public Exit() {

			setTitle("출차");

			Container c = getContentPane();

			c.setLayout(new GridLayout(2, 2));



			ex.addActionListener(new ActionListener() {

				@Override

				public void actionPerformed(ActionEvent e) {

					// TODO Auto-generated method stub

					exittime = LocalDateTime.now();
					parking[Integer.parseInt(num.getText()) - 1].setText(num.getText() + "번");
					d2[Integer.parseInt(num.getText()) - 1] = LocalDateTime.of(exittime.getYear(), exittime.getMonth(), exittime.getDayOfMonth(),
							exittime.getHour(), exittime.getMinute(), exittime.getSecond());
					setVisible(false);

					d3 = ChronoUnit.SECONDS.between(d1[Integer.parseInt(num.getText()) - 1], d2[Integer.parseInt(num.getText()) - 1]);
					s = Long.toString(d3);
					charge2.setText(s + "초");
				}

			});

			back.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {

					new Parking();

					setVisible(false);

					// end = false;

				}

			});

			c.add(num);

			c.add(ex);

			c.add(back);

			setSize(300, 300);

			setVisible(true);

		}

	}

	class mouselistener extends MouseAdapter {

		public void mouseEntered(MouseEvent e) {

			JLabel jl = (JLabel) e.getSource();

			jl.setBackground(Color.GRAY);

		}

		public void mousePressed(MouseEvent e) {

			JLabel jl = (JLabel) e.getSource();

			jl.setBackground(Color.YELLOW);

		}

		public void mouseExited(MouseEvent e) {

			JLabel jl = (JLabel) e.getSource();

			jl.setBackground(Color.GREEN);

		}

	}

	public static void main(String[] args) {

		new Parking();

	}

}