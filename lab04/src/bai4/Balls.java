package bai4;

import javax.swing.*;
import java.awt.*;

public class Balls extends Thread {
    private JPanel box;
    private static final int XSIZE = 10;
    private static final int YSIZE = 10;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;

    public Balls(JPanel p) {
        box = p;
    }

    public void draw() {
        Graphics g = box.getGraphics();
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    public void move() {
        Graphics g = box.getGraphics();
        g.setXORMode(Color.GREEN);
        g.fillOval(x, y, XSIZE, YSIZE);
        
        x += dx;
        y += dy;
        Dimension d = box.getSize();
        
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= d.getWidth()) {
            x = d.width - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= d.getHeight()) {
            y = d.height - YSIZE;
            dy = -dy;
        }
        
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    public void run() {
        draw();
        for (int i = 0; i < 5000; i++) {
            move();
            try {
                sleep(5);
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, ex.toString(), "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
