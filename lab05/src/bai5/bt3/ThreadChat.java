package bai5.bt3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadChat implements Runnable {
    private Scanner in = null;
    private Socket socket = null;
    private ServerSocket server = null;
    public frmClient chat;

    public ThreadChat() {
        try {
            server = new ServerSocket(1234);
        } catch (Exception e) {
            System.err.println("Không mở được cổng 1234 để nhận tin nhắn (đang bị dùng?): " + e.getMessage());
        }
    }

    public void run() {
        if (server == null) return;
        try {
            while (!server.isClosed()) {
                socket = server.accept();
                in = new Scanner(socket.getInputStream());
                if (in.hasNextLine()) {
                    String chuoi = in.nextLine().trim();
                    chat.Hienthi(chuoi + "\n");
                }
                socket.close();
            }
        } catch (Exception e) {
            // server bị đóng khi cửa sổ đóng
        }
    }

    public void stop() {
        try {
            if (server != null) server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
