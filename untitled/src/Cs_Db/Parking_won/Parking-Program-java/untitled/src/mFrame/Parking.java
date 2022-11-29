package mFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Cs_Db.Surprise_Db;

public class Parking extends JFrame {
	private JButton enter, exit;
	public JLabel[] parking = new JLabel[63];
	public JLabel charge2;
	public int cnt_whole = 63;
	public int cnt_graph[] = new int[4];
	BevelBorder border;
	JComboBox<String> field_c = new JComboBox<String>();
	String text_floor[] = { "1층", "2층", "3층" };
	Surprise_Db db = new Surprise_Db();
	private JPanel center, west;
	Color pinky = new Color(236, 64, 122);
	Color shinysky = new Color(0, 188, 212);
	Color teal = new Color(220, 231, 117);
	Color purplebubble = new Color(103, 58, 183);
	Color[] colors = { pinky, shinysky, teal, purplebubble };
	String[] floorsgraph = { "1st", "2nd", "3rd", "rest" };
	JTextField floorstext[] = new JTextField[4];
	int[] angle = new int[4];
	int[] n = new int[4];
	public JLabel floor_1;
	public JLabel floor_2;
	public JLabel floor_3;
	public JLabel floor_rest;
	// Index_info info[] = new Index_info[12];
	// 전역 변수
	public LocalDateTime entertime;
	LocalDateTime[] d2 = new LocalDateTime[63];
	leftpanel2 leftpanel = new leftpanel2();
	long d3;
	String c_num;
	private ImageIcon car = new ImageIcon("images/car.png");
	JPanel content = new JPanel();
	JPanel footer = new JPanel();
	JPanel lefty = new JPanel();

	public Parking() {
		db.connect();
		// db.default_create();// 1번만 쓰고 지워요 테이블 디폴트값 설정 용도

		setTitle("Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		/*
		 * 깔끔한 코드를 위해 레이아웃을 그리드백레이아웃이라는 녀석으로 제가 했는데 레이아웃 만지고 싶은데 어케 해야 할지 모르겠으면 검색 혹은
		 * 저에게 물어봐주세용 간단히 설명 하자면 155줄 부터 레이아웃 상세 설정 해놓은 건데 비율 설정해서 화면 분할 한 거에여 분할된 화면은
		 * 내용물 크기에 따라 x축이 자동으로 설정되고 높이는 고정
		 */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		content.setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
		footer.setBorder(new TitledBorder(new LineBorder(Color.red, 5)));

		content.setSize(1250, 600);
		JPanel page_f[] = new JPanel[3];
		for (int i = 0; i < 3; i++) {
			cnt_graph[i] = 0;
			page_f[i] = new JPanel();
			field_c.addItem(text_floor[i]);
		}
		cnt_graph[3] = 63;
		field_c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> field = (JComboBox<String>) e.getSource();

				int index = field.getSelectedIndex();
				for (int i = 0; i < 3; i++) {
					if (i == index) {
						page_f[i].setVisible(true);
					} else {
						page_f[i].setVisible(false);
					}
				}
			}
		});
		enter = new JButton("입차");
		exit = new JButton("출차");
		charge2 = new JLabel("정산");
		border = new BevelBorder(BevelBorder.RAISED);
		// panel.setBounds(100, 700, 1050, 100);
		// panel.setBackground(Color.cyan);
		enter.setBounds(150, 725, 100, 50);
		enter.setBackground(Color.LIGHT_GRAY);
		exit.setBounds(570, 725, 100, 50);
		exit.setBackground(Color.LIGHT_GRAY);
		charge2.setBounds(1000, 700, 100, 100);
		charge2.setBackground(Color.WHITE);
//
		int a = 0; // 주차공간 id에 접근하기위해서 임의로 값 수정 하기위해 사용한 변수
		int stack = 0;// 주차장의 층 확인용 변수

		for (int i = 0; i < 22; i++) {
			if (i == 21 && stack == 2)
				break;
			if (i > 20 && stack == 0) {
				stack = 1;
				i -= 21;
				a = 20;
			}
			if (i > 20 && stack == 1) {
				stack = 2;
				i -= 21;
				a = 41;
			}
			System.out.printf("" + stack);
			if (db.check_parking(i + a + 1) && stack + 1 == db.get_car_floor(i + a + 1)) {
				entertime = db.get_Init_Time(i + a + 1);

				System.out.println(i + a + 1);

				// + "시 " + entertime.getMinute() + "분"
				c_num = db.get_Car_Num(i + a + 1);
				parking[i + a] = new JLabel("<html><body><center>" + c_num + "<br><br>" + entertime.getHour() + "시"
						+ entertime.getMinute() + "분" + "</center></body></html>" + "eqweqweqweqweqweqweqw");
				parking[i + a].setPreferredSize(new Dimension(150, 170));
				page_f[stack].add(parking[i + a]);
				parking[i + a].setBackground(Color.pink);
				parking[i + a].setFont(new Font("Gothic", Font.PLAIN, 20));
				parking[i + a].setHorizontalAlignment(parking[i + a].CENTER);
				parking[i + a].setBorder(border);
				parking[i + a].setOpaque(true);
				if (stack == 0) {
					cnt_graph[3]--;
					cnt_graph[0]++;
				} else if (stack == 1) {
					cnt_graph[3]--;
					cnt_graph[1]++;
				} else if (stack == 2) {
					cnt_graph[3]--;
					cnt_graph[2]++;
				}
			
			} else {
				parking[i + a] = new JLabel(Integer.toString(i + 1));

				parking[i + a].setPreferredSize(new Dimension(150, 170));
				page_f[stack].add(parking[i + a]);
				parking[i + a].setBackground(Color.GREEN);
				parking[i + a].setFont(new Font("Gothic", Font.PLAIN, 20));
				parking[i + a].setHorizontalAlignment(parking[i + a].CENTER);
				parking[i + a].setBorder(border);
				parking[i + a].setOpaque(true);

			}
		}
		for (int i = 0; i < 3; i++) {
			page_f[i].setLayout(new GridLayout(3, 7, 0, 50));
			page_f[i].setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
			// content.add(page_f[i]);
			page_f[0].setVisible(true);
			page_f[1].setVisible(false);
			page_f[2].setVisible(false);
		}
		content.add(leftpanel);

		footer.add(enter);
		footer.add(exit);
		footer.add(charge2);
		c.add(field_c);
		gbc.weighty = 0.3;
		c.add(lefty);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		c.add(content);

		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = 1;

		c.add(footer);
		c.add(enter);
		setSize(1250, 900);
		setVisible(true);

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
		floor_1.setText(Integer.toString(cnt_graph[0]));
		floor_2.setText(Integer.toString(cnt_graph[1]));
		floor_3.setText(Integer.toString(cnt_graph[2]));
		floor_rest.setText(Integer.toString(cnt_graph[3]));

	}

	public class Enter extends JFrame {
		private JButton en = new JButton("입차");
		private JTextField carnum = new JTextField("car_num");
		private JTextField num = new JTextField("index_num");
		private JTextField floor = new JTextField("set_Floor");

		public Enter() {
			setTitle("입차");

			carnum.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					carnum.setText("");
				}
			});
			num.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					num.setText("");
				}
			});

			floor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					floor.setText("");
				}
			});
			Container c = getContentPane();
			c.setLayout(new GridLayout(2, 2));
			en.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					entertime = LocalDateTime.now();
					int select_index = Integer.parseInt(num.getText());
					c_num = carnum.getText();
					int fl = Integer.parseInt(floor.getText());
					//
					if (fl == 2) {
						select_index += 20;
						cnt_graph[1]++;
						cnt_graph[3]--;
					} else if (fl == 3) {
						select_index += 41;
						cnt_graph[2]++;
						cnt_graph[3]--;
					} else if(fl == 1){
						cnt_graph[0]++;
						cnt_graph[3]--;
					}
					System.out.printf(select_index + "");
					parking[select_index - 1].setText("<html><body><center>" + c_num + "<br><br>" + entertime.getHour()
							+ "시 " + entertime.getMinute() + "분" + "</center></body></html>");
					parking[select_index - 1].setBackground(Color.pink);
					// parking[select_index-1].setIcon(car);
					parking[select_index - 1].setVerticalTextPosition(SwingConstants.BOTTOM);
					System.out.println(select_index - 1 + c_num);
					db.init_car(select_index - 1, c_num, entertime, fl);
					setVisible(false);// 입력 후 창 닫기
				}
			});
			floor_1.setText(Integer.toString(cnt_graph[0]));
			floor_2.setText(Integer.toString(cnt_graph[1]));
			floor_3.setText(Integer.toString(cnt_graph[2]));
			floor_rest.setText(Integer.toString(cnt_graph[3]));
			c.add(carnum);
			c.add(num);
			c.add(floor);
			c.add(en);
			setSize(300, 300);
			setVisible(true);
			
		}
	}

	public class Exit extends JFrame {

		private JButton ex = new JButton("출차");

		private JButton back = new JButton("돌아가기");

		private JTextField num = new JTextField("");
		private JTextField select_floor = new JTextField("floor");
		public LocalDateTime exittime;
		String s;

		public Exit() {

			setTitle("출차");

			Container c = getContentPane();

			c.setLayout(new GridLayout(2, 2));

			num.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					num.setText("");
				}
			});

			select_floor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					select_floor.setText("");
				}
			});
			ex.addActionListener(new ActionListener() {

				@Override

				public void actionPerformed(ActionEvent e) {

					// TODO Auto-generated method stub

					exittime = LocalDateTime.now();

					d2[Integer.parseInt(num.getText()) - 1] = LocalDateTime.of(exittime.getYear(), exittime.getMonth(),
							exittime.getDayOfMonth(), exittime.getHour(), exittime.getMinute(), exittime.getSecond());
					setVisible(false);

					d3 = ChronoUnit.SECONDS.between(db.get_Init_Time(Integer.parseInt(num.getText())),
							d2[Integer.parseInt(num.getText()) - 1]);

					int fl = Integer.parseInt(select_floor.getText());
					int value = Integer.parseInt(num.getText());
					if (db.check_parking(value) == true) {

						if (fl == 2) {
							value += 20;
							cnt_graph[1]--;
							cnt_graph[3]++;
						} else if (fl == 3) {
							value += 41;
							cnt_graph[2]--;
							cnt_graph[3]++;
						} else {
							cnt_graph[0]--;
							cnt_graph[3]++;
						}
						if (d3 > 1800) {
							d3 -= 1800;
							d3 = d3 / 600 * 500 + 1500;
						} else if (d3 <= 1800 || d3 > 0) {
							d3 = 1500;
						}
						s = Long.toString(d3);
						charge2.setText(db.get_Car_Num(value) + "차량의 요금은 " + s + "원 입니다");
						db.Out_car(value);

						parking[value - 1].setIcon(null);
						parking[value - 1].setBackground(Color.GREEN);
						parking[value - 1].setText(num.getText());
					}
				}
			});

			back.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {

					setVisible(false);

					// end = false;

				}

			});
			floor_1.setText(Integer.toString(cnt_graph[0]));
			floor_2.setText(Integer.toString(cnt_graph[1]));
			floor_3.setText(Integer.toString(cnt_graph[2]));
			floor_rest.setText(Integer.toString(cnt_graph[3]));
			c.add(num);
			c.add(select_floor);
			c.add(ex);

			c.add(back);

			setSize(300, 300);

			setVisible(true);

		}

	}

	/*
	 * class leftpanel1 extends JPanel { public leftPanel1() {
	 * setBackground(Color.LIGHT_GRAY); for(int i = 0;i<floorsgraph.length;i++) {
	 * floorstext[i] = new JTextField(7); floorstext[i].addActionListener(new
	 * MyActionListener()); add(new JLabel(floorsgraph[i])); add(floorstext[i]); } }
	 * }
	 */
	class leftpanel2 extends JPanel {
		public void paintComponent(Graphics g) {
			int sum = 0;
			super.paintComponent(g);
			setSize(500, 500);
			setBackground(Color.yellow);

			int startAngle = 0;
			for (int i = 0; i < floorsgraph.length; i++) {
				g.setColor(colors[i]);
				g.drawString(floorsgraph[i] + " " + Math.round(angle[i] * 100 / 360) + "%", 100 + i * 100, 20);
			}
			for (int i = 0; i < floorsgraph.length; i++) {

				sum += cnt_graph[i];
			}
			for (int i = 0; i < floorsgraph.length; i++) {
				angle[i] = (int) Math.round((double) cnt_graph[i] / (double) sum * 360);

				/*
				 * repaint();s g.setColor(colors[i]);
				 * g.fillArc(150,50,200,200,startAngle,angle[i]); startAngle += angle[i];
				 */
			}
			g.setColor(pinky);
			g.fillArc(150, 150, 200, 200, 0, angle[0]);
			g.setColor(shinysky);
			g.fillArc(150, 150, 200, 200, angle[0], angle[1]);
			g.setColor(purplebubble);
			g.fillArc(150, 150, 200, 200, angle[0] + angle[1], angle[2]);
			g.setColor(Color.GRAY);
			g.fillArc(150, 150, 200, 200, angle[0] + angle[1] + angle[2], angle[3]);

		}
	}
	/*
	 * class MyActionListener implements ActionListener { public void
	 * actionPerformed(ActionEvent e) { int sum = 0; for(int i = 0; i
	 * <floorsgraph.length;i++) { n[i] = Integer.parseInt(floorstext[i].getText());
	 * sum += n[i]; } for(int i = 0; i <floorsgraph.length;i++) { angle[i] =
	 * (int)Math.round((double)n[i]/(double)sum*360); repaint(); } } }
	 */

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