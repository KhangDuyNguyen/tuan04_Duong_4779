package bai5.bt6;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class frmClient extends JFrame {
    private JTextField txtDomain, txtUser, txtPass;
    private DefaultListModel<String> dm;
    private JList<String> lstClientPath;

    public frmClient() {
        setTitle("FTP Client");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel pnlTop = new JPanel(new GridLayout(3, 2));
        pnlTop.add(new JLabel("Domain:"));
        txtDomain = new JTextField("127.0.0.1");
        pnlTop.add(txtDomain);
        pnlTop.add(new JLabel("User:"));
        txtUser = new JTextField("tu");
        pnlTop.add(txtUser);
        pnlTop.add(new JLabel("Pass:"));
        txtPass = new JTextField("tu");
        pnlTop.add(txtPass);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> {
            try (Socket s = new Socket(txtDomain.getText().trim(), 10000)) {
                PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                Scanner sc = new Scanner(s.getInputStream());
                
                pw.println("DANGNHAP");
                pw.println(txtUser.getText());
                pw.println(txtPass.getText());
                
                int cmdR = sc.nextInt();
                if (cmdR == 1) {
                    JOptionPane.showMessageDialog(this, "Dang nhap thanh cong");
                    dm = new DefaultListModel<>();
                    int n = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < n; i++) {
                        dm.addElement(sc.nextLine());
                    }
                    lstClientPath.setModel(dm);
                } else {
                    JOptionPane.showMessageDialog(this, "Dang nhap khong thanh cong");
                }
                pw.println("THOAT");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Loi ket noi: " + ex.getMessage());
            }
        });
        
        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.add(pnlTop, BorderLayout.CENTER);
        pnlNorth.add(btnLogin, BorderLayout.EAST);
        add(pnlNorth, BorderLayout.NORTH);

        dm = new DefaultListModel<>();
        lstClientPath = new JList<>(dm);
        add(new JScrollPane(lstClientPath), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frmClient().setVisible(true));
    }
}
