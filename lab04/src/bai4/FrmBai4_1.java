package bai4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmBai4_1 extends JFrame {
    private JPanel canvas;
    
    public FrmBai4_1() {
        setTitle("Single Thread Bouncing Ball");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        canvas = new JPanel();
        canvas.setBackground(Color.WHITE);
        canvas.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        add(canvas, BorderLayout.CENTER);
        
        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b1 = new Ball(canvas);
                b1.bounce();
            }
        });
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnStart);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
