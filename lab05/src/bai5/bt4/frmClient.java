package bai5.bt4;

import javax.swing.*;
import java.awt.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class frmClient extends JFrame {
    private static final int TIMEOUT_MS = 200;
    private static final int THREADS = 100;

    private JTextField txtDomain;
    private JTextArea txtPorts;
    private JButton btnKiemTra;

    public frmClient() {
        setTitle("Quét Cổng");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Tên miền / IP:"));
        txtDomain = new JTextField("127.0.0.1", 15);
        top.add(txtDomain);
        btnKiemTra = new JButton("Kiểm tra");
        btnKiemTra.addActionListener(e -> scan());
        top.add(btnKiemTra);
        add(top, BorderLayout.NORTH);

        txtPorts = new JTextArea();
        txtPorts.setEditable(false);
        add(new JScrollPane(txtPorts), BorderLayout.CENTER);
    }

    private void append(String s) {
        SwingUtilities.invokeLater(() -> txtPorts.append(s));
    }

    private void scan() {
        final String host = txtDomain.getText().trim();
        txtPorts.setText("");
        btnKiemTra.setEnabled(false);

        new Thread(() -> {
            ExecutorService pool = Executors.newFixedThreadPool(THREADS);
            try {
                java.net.InetAddress addr = java.net.InetAddress.getByName(host);
                for (int p = 1024; p < 65536; p++) {
                    final int port = p;
                    pool.execute(() -> {
                        try (Socket s = new Socket()) {
                            s.connect(new InetSocketAddress(addr, port), TIMEOUT_MS);
                            append("Port mở: " + port + "\n");
                        } catch (Exception ex) {
                            // cổng đóng
                        }
                    });
                }
                pool.shutdown();
                pool.awaitTermination(10, TimeUnit.MINUTES);
                append("Hoàn tất quét.\n");
            } catch (UnknownHostException ex) {
                pool.shutdownNow();
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(this, "Không tìm thấy host: " + ex.getMessage()));
            } catch (InterruptedException ex) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            } finally {
                SwingUtilities.invokeLater(() -> btnKiemTra.setEnabled(true));
            }
        }).start();
    }
}
