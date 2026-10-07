package bai5.bt3;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class frmClient extends JFrame {
    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;
    public JTextArea txtchat;
    private JTextField txtHost, txtNick, txtsend;
    public ThreadChat obj;

    public frmClient() {
        setTitle("Chat Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel pnlTop = new JPanel(new GridLayout(2, 2));
        pnlTop.add(new JLabel("Nhập IP máy chủ:"));
        txtHost = new JTextField("127.0.0.1");
        pnlTop.add(txtHost);
        pnlTop.add(new JLabel("Nhập Nick:"));
        txtNick = new JTextField("User");
        pnlTop.add(txtNick);
        add(pnlTop, BorderLayout.NORTH);

        txtchat = new JTextArea();
        txtchat.setEditable(false);
        add(new JScrollPane(txtchat), BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel(new BorderLayout());
        txtsend = new JTextField();
        pnlBottom.add(txtsend, BorderLayout.CENTER);
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(e -> {
            try {
                socket = new Socket(txtHost.getText(), 1234);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new Scanner(socket.getInputStream());
                out.println(txtNick.getText() + ": " + txtsend.getText());
                txtchat.append(txtNick.getText() + ": " + txtsend.getText() + "\n");
                txtsend.setText("");
                socket.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Không gửi được tin nhắn: " + ex.getMessage());
                try { if (socket != null) socket.close(); } catch (Exception ex2) {}
            }
        });
        pnlBottom.add(btnSend, BorderLayout.EAST);
        add(pnlBottom, BorderLayout.SOUTH);

        // Khởi động ThreadChat lắng nghe tin nhắn đến
        obj = new ThreadChat();
        obj.chat = this;
        Thread listener = new Thread(obj);
        listener.setDaemon(true);
        listener.start();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                obj.stop();
            }
        });
    }

    public void Hienthi(String str) {
        SwingUtilities.invokeLater(() -> txtchat.append(str));
    }
}
