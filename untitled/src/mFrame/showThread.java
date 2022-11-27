package mFrame;

import javax.swing.*;

public class showThread extends Thread {
    private JLabel label;
    private JPanel p;
    int x, y;

    public showThread(JLabel label, JPanel p, int x, int y) {
        this.label = label;
        this.p = p;
        this.x = x;
        this.y = y;

    }

    public void run() {
        label.setLocation(x, y);
        p.add(label);
        while (true) {
            try {
                if (x <= 1300) {
                    x += 5;
                    label.setLocation(x, y);
                    sleep(50);
                } else if (x > 1300) {
                    p.remove(label);
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}