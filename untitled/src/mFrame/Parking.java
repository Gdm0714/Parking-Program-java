package mFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Cs_Db.Surprise_Db;

public class Parking extends JFrame {
    private JButton enter, exit;

    public JLabel[] parking = new JLabel[63];

    public JLabel charge2;

    BevelBorder border;

    JComboBox<String> field_c = new JComboBox<String>();

    String text_floor[] = {"1층", "2층", "3층"};

    Surprise_Db db;

    //Index_info info[] = new Index_info[12];
    //전역 변수
    public LocalDateTime entertime;

    LocalDateTime[] d2 = new LocalDateTime[63];

    long d3;

    String c_num;

    private ImageIcon car = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\car.png");

    private ImageIcon logo1 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\logo1.jpg");

    private ImageIcon logo2 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\logo2.png");

    private ImageIcon logo3 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\logo.png");

    private ImageIcon place = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\images.png");

    private ImageIcon conBack = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\img.png");

    private showThread st;

    private JLabel enterNotice = new JLabel();

    private JLabel exitNotice = new JLabel();
    JPanel logo = new JPanel() {
        Image img = logo1.getImage();

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, this);
        }
    };


    JPanel header = new JPanel();

    JPanel side = new JPanel();

    JPanel content = new JPanel();

    JPanel sp1 = new JPanel();

    JPanel sp2 = new JPanel();

    JPanel sp3 = new JPanel();

    JPanel sp4 = new JPanel();

    JPanel sp5 = new JPanel();

    private JLabel logoLabel = new JLabel();

    private Container c;

    private JLabel space;

    int firstNum = 0;

    int secondNum = 0;

    int thirdNum = 0;

    public Parking(String table_name,String lc_text) {
        db= new Surprise_Db(lc_text);
        db.connect();
        //db.default_create();// 1번만 쓰고 지워요 테이블 디폴트값 설정 용도
        setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = getContentPane();
        c.setLayout(new GridBagLayout());
        /*깔끔한 코드를 위해 레이아웃을 그리드백레이아웃이라는 녀석으로 제가 했는데
          레이아웃 만지고 싶은데 어케 해야 할지 모르겠으면 검색 혹은 저에게 물어봐주세용
          간단히 설명 하자면 155줄 부터 레이아웃 상세 설정 해놓은 건데
          비율 설정해서 화면 분할 한 거에여
          분할된 화면은 내용물 크기에 따라 x축이 자동으로 설정되고 높이는 고정
        */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        field_c.setPreferredSize(new Dimension(100, 50));

        content.setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
        side.setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
        logo.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 5)));
        header.setBorder(new TitledBorder(new LineBorder(Color.ORANGE, 5)));
        side.setLayout(new GridLayout(6, 1));


        space = new JLabel("<html><body><center>" + "1층에" + firstNum + "대 주차되어있습니다.<br>2층에" + secondNum +
                "대 주차되어있습니다.<br>3층에" + thirdNum + "대 주차되어있습니다.</center></body></html>");
        space.setFont(new Font("Gothic", Font.PLAIN, 20));
        space.setForeground(Color.BLUE);

        side.add(sp1);
        sp1.setBackground(Color.GRAY);
        side.add(sp2);
        sp2.setBackground(Color.YELLOW);
        side.add(sp3);
        sp3.setBackground(Color.GRAY);
        side.add(sp4);
        sp4.setBackground(Color.GRAY);
        side.add(sp5);
        sp5.setBackground(Color.GRAY);

        JPanel page_f[] = new JPanel[3];
        for (int i = 0; i < 3; i++) {
            page_f[i] = new JPanel();
            page_f[i].setBackground(Color.BLACK);
            field_c.addItem(text_floor[i]);
        }
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

        charge2.setFont(new Font("Gothic", Font.PLAIN, 40));
        border = new BevelBorder(BevelBorder.RAISED);
        enter.setBounds(150, 725, 100, 50);
        enter.setBackground(Color.LIGHT_GRAY);
        exit.setBounds(570, 725, 100, 50);
        exit.setBackground(Color.LIGHT_GRAY);
        charge2.setBounds(1000, 700, 100, 100);
        charge2.setBackground(Color.WHITE);
        int a = 0; // 주차공간 id에 접근하기위해서 임의로 값 수정 하기위해 사용한 변수
        int stack = 0;// 주차장의 층 확인용 변수

        for (int i = 0; i < 22; i++) {
            if (i == 21 && stack == 2) break;

            if (i > 20 && stack == 0) {
                stack = 1;
                i -= 21;
                a = 21;
            }

            if (i > 20 && stack == 1) {
                stack = 2;
                i -= 21;
                a = 42;
            }
            System.out.printf("" + stack);

            if (db.check_parking(i + a + 1) && stack + 1 == db.get_car_floor(i + a + 1)) {
                entertime = db.get_Init_Time(i + a + 1);

                System.out.println(i + a + 1);

                //+ "시 " + entertime.getMinute() + "분"
                c_num = db.get_Car_Num(i + a + 1);
                parking[i + a] = new JLabel();
                parking[i + a].setIcon(car);
                parking[i + a].setPreferredSize(new Dimension(150, 170));
                page_f[stack].add(parking[i + a]);
                parking[i + a].setBackground(Color.pink);
                parking[i + a].setFont(new Font("Gothic", Font.PLAIN, 20));
                parking[i + a].setHorizontalAlignment(parking[i + a].CENTER);
                parking[i + a].setBorder(border);
                parking[i + a].setOpaque(true);

            } else {
                parking[i + a] = new JLabel(Integer.toString(i + 1));
                parking[i + a].setForeground(Color.WHITE);
                parking[i + a].setPreferredSize(new Dimension(150, 170));
                page_f[stack].add(parking[i + a]);
                parking[i + a].setBackground(Color.BLACK);
                parking[i + a].setBorder(new LineBorder(Color.WHITE, 5));
                parking[i + a].setFont(new Font("Gothic", Font.PLAIN, 20));
                parking[i + a].setHorizontalAlignment(parking[i + a].CENTER);

                parking[i + a].setOpaque(true);

            }
        }

        for (int i = 0; i < 3; i++) {
            page_f[i].setLayout(new GridLayout(3, 7, 0, 50));
            page_f[i].setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
            content.add(page_f[i]);
            page_f[0].setVisible(true);
            page_f[1].setVisible(false);
            page_f[2].setVisible(false);
        }

        sp1.add(enter);
        sp1.add(exit);
        sp2.add(charge2);
        sp3.add(field_c);
        sp4.add(space);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        c.add(logo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        c.add(header, gbc);
        //c.add(field_c);
        gbc.weighty = 0.2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 8;
        c.add(side, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 8;
        gbc.gridwidth = 3;
        c.add(content, gbc);

        setSize(1432, 922);
        setVisible(true);

        logo.add(logoLabel);
        for (int i = 0; i < 62; i++) {
            parking[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    if (label.getText() == "") {
                        for (int j = 0; j < 62; j++) {
                            label.setIcon(null);
                            label.setText("<html><body><center>"
                                    + c_num + "<br><br>" + entertime.getHour()
                                    + "시 " + entertime.getMinute() + "분" + "</center></body></html>");
                            label.setBackground(Color.pink);
                        }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    if (label.getText().length() > 4) {
                        label.setIcon(car);
                        label.setText("");
                    }
                }
            });
        }
        content.setBackground(Color.BLACK);
        side.setBackground(Color.GRAY);
        header.setBackground(Color.GRAY);


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
    }

    public class Enter extends JFrame {
        private JButton en = new JButton("입차");

        private JTextField carnum = new JTextField("car_num");

        private JTextField num = new JTextField("index_num");

        private JTextField floor = new JTextField("floor");

        public Enter() {
            setTitle("입차");
            Container c = getContentPane();
            c.setLayout(new GridLayout(2, 2));
            en.setBackground(Color.gray);
            carnum.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (carnum.getText().equals("car_num"))
                        carnum.setText("");
                }
            });
            num.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (num.getText().equals("index_num"))
                        num.setText("");
                }
            });

            floor.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (floor.getText().equals("floor"))
                        floor.setText("");
                }
            });
            en.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    entertime = LocalDateTime.now();
                    int select_index = Integer.parseInt(num.getText());
                    c_num = carnum.getText();
                    int fl = Integer.parseInt(floor.getText());
                    if (fl == 2) {
                        select_index += 20;
                        secondNum++;
                    } else if (fl == 3) {
                        select_index += 41;
                        thirdNum++;
                    } else if (fl == 1) {
                        firstNum++;
                    }
                    System.out.printf(select_index + "");
                    parking[select_index - 1].setText("");
                    parking[select_index - 1].setIcon(car);
                    parking[select_index - 1].setVerticalTextPosition(SwingConstants.BOTTOM);
                    System.out.println(select_index - 1 + c_num);
                    db.init_car(select_index - 1, c_num, entertime, fl);

                    setVisible(false);//입력 후 창 닫기
                }
            });

            en.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entertime = LocalDateTime.now();
                    int select_index = Integer.parseInt(num.getText());
                    c_num = carnum.getText();
                    int fl = Integer.parseInt(floor.getText());
                    if (fl == 2) {
                        select_index += 20;
                    } else if (fl == 3) {
                        select_index += 41;
                    }
                    enterNotice.setFont(new Font("Serif", Font.BOLD, 30));
                    enterNotice.setForeground(Color.BLUE);
                    enterNotice.setText("<html><body><center>" + +entertime.getHour() + "시" + entertime.getMinute() + "분<br>"
                            + Integer.toString(fl) + "층" + Integer.toString(select_index) + "번 자리에 " + c_num + "차량 입차되었습니다</center></body></html>");
                    st = new showThread(enterNotice, header, 0, e.getY());
                    st.start();
                    revalidate();
                }
            });


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

        private JTextField num = new JTextField("index_num");

        private JTextField select_floor = new JTextField("floor");

        public LocalDateTime exittime;
        String s;

        public Exit() {

            setTitle("출차");

            Container c = getContentPane();

            c.setLayout(new GridLayout(2, 2));
            ex.setBackground(Color.gray);
            back.setBackground(Color.gray);
            num.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (num.getText().equals("index_num"))
                        num.setText("");
                }
            });

            select_floor.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (select_floor.getText().equals("floor"))
                        select_floor.setText("");
                }
            });

            ex.addActionListener(new ActionListener() {

                @Override

                public void actionPerformed(ActionEvent e) {

                    // TODO Auto-generated method stub

                    exittime = LocalDateTime.now();

                    d2[Integer.parseInt(num.getText()) - 1] = LocalDateTime.of(exittime.getYear(), exittime.getMonth(), exittime.getDayOfMonth(),
                            exittime.getHour(), exittime.getMinute(), exittime.getSecond());
                    setVisible(false);

                    d3 = ChronoUnit.SECONDS.between(db.get_Init_Time(Integer.parseInt(num.getText())), d2[Integer.parseInt(num.getText()) - 1]);
                    s = Long.toString(d3);
                    if (d3 > 60) {
                        charge2.setText(Long.toString(d3 / 60) + "분" + Long.toString(d3 % 60) + "초");
                    } else {
                        charge2.setText(s + "초");
                    }
                    int fl = Integer.parseInt(select_floor.getText());
                    int value = Integer.parseInt(num.getText());
                    if (fl == 2) {
                        value += 20;
                    } else if (fl == 3) {
                        value += 41;
                    }
                    db.Out_car(value);
                    parking[value - 1].setIcon(null);
                    parking[value - 1].setForeground(Color.WHITE);
                    parking[value - 1].setBackground(Color.BLACK);
                    parking[value - 1].setBorder(new LineBorder(Color.WHITE, 5));
                    parking[value - 1].setText(num.getText());
                }

            });

            ex.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    exittime = LocalDateTime.now();
                    int select_index = Integer.parseInt(num.getText());
                    int fl = Integer.parseInt(select_floor.getText());
                    if (fl == 2) {
                        select_index += 20;
                    } else if (fl == 3) {
                        select_index += 41;
                    }
                    exitNotice.setFont(new Font("Serif", Font.BOLD, 30));
                    exitNotice.setForeground(Color.BLUE);
                    exitNotice.setText("<html><body><center>" + Integer.toString(fl) + "층 " + Integer.toString(select_index) + "번 자리에 " + exittime.getHour() + "시 " + exittime.getMinute() + "분 출차되었습니다</center></body></html>");
                    st = new showThread(exitNotice, header, 0, e.getY());
                    st.start();
                    revalidate();
                }
            });

            back.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {

                    setVisible(false);

                    // end = false;

                }

            });

            c.add(num);
            c.add(select_floor);
            c.add(ex);

            c.add(back);

            setSize(300, 300);

            setVisible(true);

        }

    }

    public static void main(String[] args) {

    }
}

