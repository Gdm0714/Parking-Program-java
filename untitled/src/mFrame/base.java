package mFrame;

import Cs_Db.DB_Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class base extends JFrame {
    private JTextField id = new JTextField("id 입력");
    private JTextField pw = new JTextField("pw 입력");
    private JButton log = new JButton("로그인");
    private JButton jb = new JButton("회원가입");
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
        c.setLayout(null);
        id.setBounds(220, 300, 160, 30);
        pw.setBounds(220, 330, 160, 30);


        c.add(id);
        c.add(pw);
        lc_box.setBounds(220, 400, 160, 30);
        c.add(lc_box);

        pi = new JLabel(parking);
        pi.setBounds(200, 100, 200, 200);
        c.add(pi);
        log.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               if(db.login(lc_box.getSelectedItem().toString(), pw.getText())==true){
                   System.out.println(lc_box.getSelectedIndex());
                   System.out.println("eqweqweqweqweqweqw");
                   new Parking(lc_box.getSelectedItem().toString(),db_name[lc_box.getSelectedIndex()]);

               };

            }
        });
        jb.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //new sign_Dialog();
            }
        });
        log.setBounds(200, 360, 100, 50);
        jb.setBounds(300, 360, 100, 50);
        c.add(log);
        c.add(jb);
        setSize(600, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new base();
    }
}
