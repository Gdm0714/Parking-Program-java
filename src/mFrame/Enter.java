import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Enter extends JFrame {
	private JButton en = new JButton("입차");
	private JButton back = new JButton("돌아가기");
	private JTextField carnum = new JTextField("");
	private JTextField num = new JTextField("");
	public LocalTime entertime;

	public Enter() {
		setTitle("입차");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new GridLayout(2, 2));
		en.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Parking pa = new Parking();
				entertime = LocalTime.now();
				pa.parking[Integer.parseInt(num.getText()) - 1].setText("<html><body><center>" + carnum.getText() + "<br><br>" + entertime.getHour() 
				+ "시 " + entertime.getMinute() + "분" + "</center></body></html>");
			}
		});
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Parking();
				setVisible(false);
			}
		});
		c.add(carnum);
		c.add(num);
		c.add(en);
		c.add(back);
		setSize(300, 300);
		setVisible(true);
	}
}
