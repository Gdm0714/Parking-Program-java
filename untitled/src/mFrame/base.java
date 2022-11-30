package mFrame;

import Cs_Db.DB_Connection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class base extends JFrame {
    private JPanel header = new JPanel();
    private Line content = new Line();
    private JPanel footer = new JPanel();

    private JTextField id = new JTextField("id 입력");
    private JTextField pw = new JTextField("pw 입력");
    private JButton log = new JButton("로그인");
    private JLabel pi;
    private ImageIcon parking = new ImageIcon("images/parkingicon.png");
    JComboBox <String> lc_box = new JComboBox<String>();
    String location[] = {"장영실관","하현관","늘빛관"};
    String db_name[]={"car_info","car_info_b","car_info_c"};
    DB_Connection db = new DB_Connection();

    public String get_table_name(){
        return "";
    }
    public base() {

       db.connect();
        setTitle("메인 화면");
       for(int i =0;  i<3;i++){
           lc_box.addItem(location[i]);
       }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new GridBagLayout());
        c.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;



//

        pi = new JLabel(parking);
        log.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               if(db.login(lc_box.getSelectedItem().toString(), pw.getText())==true){
                   System.out.println(lc_box.getSelectedIndex());
                   System.out.println("eqweqweqweqweqweqw");
                   new Parking(lc_box.getSelectedItem().toString(),db_name[lc_box.getSelectedIndex()]);

               };

            }
        });


        gbc.ipady =0;
        gbc.ipadx =0;
        gbc.weighty = 0.25;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth =1;
        gbc.gridheight =2;
        c.add(header,gbc);
        header.setBackground(Color.WHITE);

        header.setLayout(null);
        JLabel title = new JLabel("  Welcome The Parking System.");
        title.setBounds(100,c.getHeight()/10,600,100);
        Font title_font = new Font("배민도현체",Font.BOLD,25);
        title.setFont(title_font);

        header.add(title);

        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth =1;
        gbc.gridheight =3;
        c.add(content,gbc);
        content.setBackground(Color.WHITE);

        content.setLayout(null);
        JLabel content_text = new JLabel("Hello SuperVisor");
        content_text.setBounds(180,c.getHeight()/8,600,100);
        content_text.setFont(title_font);
        content.add(content_text);
        JLabel Password_t = new JLabel("Password");
        Password_t.setBounds(180,c.getHeight()/8+90,200,50);
        //반투명 설정?Color test = new Color(0,0,0,122);
        Password_t.setFont(new Font("고딕",Font.PLAIN,10));
        Password_t.setForeground(new Color(216,216,216));
        pw.setBounds(180,c.getHeight()/8+125,200,50);
        content.add(Password_t);
        content.add(pw);
        lc_box.setBounds(180,c.getHeight()/8+175,200,30);
        lc_box.setBackground(Color.white);
        content.add(lc_box);


        gbc.weighty = 0.25;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth =1;
        gbc.gridheight =2;
        c.add(footer,gbc);
       footer.setBackground(Color.WHITE);

        footer.setLayout(null);
        log.setBounds(170,c.getHeight()/6,200,50);
        log.setForeground(Color.white);
        log.setBackground(Color.black);
        footer.add(log);
        setSize(600, 600);
        setVisible(true);
    }
    class Line extends JPanel{

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(new Color(208,208,208));
            g.drawLine(70,0,530,0);

        }
    }
    public static void main(String[] args) {
        new base();
    }
}
