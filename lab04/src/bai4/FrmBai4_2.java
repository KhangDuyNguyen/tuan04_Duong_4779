package bai4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmBai4_2 extends JFrame {
    private JPanel canvas;
    
    public FrmBai4_2() {
        setTitle("Multi Thread Bouncing Balls");
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
                Balls b = new Balls(canvas);
                b.start();
            }
        });
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnStart);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
