import bai4.FrmBai4_1;
import bai4.FrmBai4_2;
import bai4.lamthem.FileTWrite;
import bai4.lamthem.FileTReader;
import bai4.lamthem.SyncReadWrite;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        setTitle("BÀI 4: XỬ LÝ TIẾN TRÌNH");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton btnBai1 = new JButton("Bài 4.2.1: SingleThread (Bóng)");
        btnBai1.addActionListener(e -> new FrmBai4_1().setVisible(true));
        
        JButton btnBai2 = new JButton("Bài 4.2.2: MultiThread (Bóng)");
        btnBai2.addActionListener(e -> new FrmBai4_2().setVisible(true));
        
        JButton btnLT1 = new JButton("Bài 4.3.1 & 4.3.2: Ghi/Đọc File");
        btnLT1.addActionListener(e -> {
            // Ghi xong (FileTWrite.main chờ join) rồi mới đọc
            new Thread(() -> {
                FileTWrite.main(new String[]{});
                FileTReader.main(new String[]{});
            }).start();
            JOptionPane.showMessageDialog(this, "Đang chạy 3 luồng ghi rồi đọc file1, file2, file3...\nVui lòng kiểm tra Console.");
        });
        
        JButton btnLT3 = new JButton("Bài 4.3.3: Đồng bộ Ghi/Đọc");
        btnLT3.addActionListener(e -> {
            new Thread(() -> SyncReadWrite.main(new String[]{})).start();
            JOptionPane.showMessageDialog(this, "Đang chạy luồng đồng bộ ghi/đọc.\nVui lòng kiểm tra Console.");
        });
        
        panel.add(btnBai1);
        panel.add(btnBai2);
        panel.add(btnLT1);
        panel.add(btnLT3);
        
        add(panel);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
