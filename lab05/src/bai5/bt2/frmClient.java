package bai5.bt2;

import javax.swing.*;
import java.awt.GridLayout;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class frmClient extends JFrame {
    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;

    private JTextField txtso1, txtso2, txtketqua;
    private JComboBox<String> cbopheptoan;

    public frmClient() {
        setTitle("Máy tính TCP");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Số 1:"));
        txtso1 = new JTextField();
        add(txtso1);

        add(new JLabel("Phép toán:"));
        cbopheptoan = new JComboBox<>(new String[]{"+", "-", "*", "/"});
        add(cbopheptoan);

        add(new JLabel("Số 2:"));
        txtso2 = new JTextField();
        add(txtso2);

        add(new JLabel("Kết quả:"));
        txtketqua = new JTextField();
        txtketqua.setEditable(false);
        add(txtketqua);

        JButton btnTinh = new JButton("Tính");
        btnTinh.addActionListener(e -> {
            try {
                int so1 = Integer.parseInt(txtso1.getText().trim());
                int so2 = Integer.parseInt(txtso2.getText().trim());
                String pheptoan = cbopheptoan.getSelectedItem().toString();
                String chuoi = so1 + "@" + pheptoan + "@" + so2;
                
                socket = new Socket("127.0.0.1", 1235);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new Scanner(socket.getInputStream());
                
                out.println(chuoi);
                String ketqua = in.nextLine().trim();
                txtketqua.setText(ketqua);
                socket.close();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên hợp lệ ở Số 1 và Số 2");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Không kết nối được tới server: " + ex.getMessage());
                try { if (socket != null) socket.close(); } catch (Exception ex2) {}
            }
        });
        add(btnTinh);

        JButton btnThoat = new JButton("Thoát");
        btnThoat.addActionListener(e -> dispose());
        add(btnThoat);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frmClient().setVisible(true));
    }
}
