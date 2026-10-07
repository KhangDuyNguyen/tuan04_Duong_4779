package bai5.bt5;

import java.net.Socket;

public class LocalPort80 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("google.com", 80)) {
            System.out.println("Thông tin Socket cục bộ:");
            System.out.println("Local Address: " + socket.getLocalAddress());
            System.out.println("Local Port: " + socket.getLocalPort());
            System.out.println("Thông tin Socket kết nối đến:");
            System.out.println("Remote Address: " + socket.getInetAddress());
            System.out.println("Remote Port: " + socket.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
