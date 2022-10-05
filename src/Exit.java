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

public class Exit extends JFrame {
	private JButton ex = new JButton("ÃâÂ÷");
	private JButton back = new JButton("µ¹¾Æ°¡±â");
	private JTextField num = new JTextField("");
	public LocalTime exittime;
	public Exit() {
		setTitle("ÃâÂ÷");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new GridLayout(2, 2));
		ex.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Parking pa = new Parking();
				exittime = LocalTime.now();
				pa.parking[Integer.parseInt(num.getText())-1].setText(num.getText() + "¹ø");
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