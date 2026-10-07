import tcp.frmClient;
import tcp.TCPServer;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private boolean bt1Started, bt2Started, bt6Started;

    public Main() {
        setTitle("BÀI 5: TCP Socket, FTP - Full Exercises");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton btnBt1 = new JButton("Bài 5.3.1: Gửi chuỗi in hoa");
        btnBt1.addActionListener(e -> {
            if (!bt1Started) {
                bt1Started = true;
                new Thread(() -> TCPServer.main(new String[]{})).start();
            }
            new tcp.frmClient().setVisible(true);
        });
        
        JButton btnBt2 = new JButton("Bài 5.3.2: Máy tính");
        btnBt2.addActionListener(e -> {
            if (!bt2Started) {
                bt2Started = true;
                new Thread(() -> bai5.bt2.TCPServer.main(new String[]{})).start();
            }
            new bai5.bt2.frmClient().setVisible(true);
        });
        
        JButton btnBt3 = new JButton("Bài 5.3.3: Chat");
        btnBt3.addActionListener(e -> new bai5.bt3.frmClient().setVisible(true));
        
        JButton btnBt4 = new JButton("Bài 5.3.4: Quét cổng");
        btnBt4.addActionListener(e -> new bai5.bt4.frmClient().setVisible(true));
        
        JButton btnBt5 = new JButton("Bài 5.3.5: Local Port 80 (Console)");
        btnBt5.addActionListener(e -> {
            new Thread(() -> bai5.bt5.LocalPort80.main(new String[]{})).start();
            JOptionPane.showMessageDialog(this, "Đã chạy, xem kết quả trong Console.");
        });
        
        JButton btnBt6 = new JButton("Bài 5.3.6: FTP Simulation");
        btnBt6.addActionListener(e -> {
            if (!bt6Started) {
                bt6Started = true;
                new Thread(() -> bai5.bt6.Main.main(new String[]{})).start();
            }
            new bai5.bt6.frmClient().setVisible(true);
        });
        
        panel.add(btnBt1);
        panel.add(btnBt2);
        panel.add(btnBt3);
        panel.add(btnBt4);
        panel.add(btnBt5);
        panel.add(btnBt6);
        
        add(panel);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
