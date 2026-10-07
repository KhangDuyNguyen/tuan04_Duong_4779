package bai5.bt6;

import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static final int DANGNHAP = 1;
    public static final int KHONGLALENH = 0;
    public static final int THOAT = 2;
    public static final int UPLOAD = 3;
    public static final int DOWNLOAD = 4;

    public static int laLenh(String cmd) {
        if(cmd.equals("DANGNHAP")) return DANGNHAP;
        if(cmd.equals("UPLOAD")) return UPLOAD;
        if(cmd.equals("DOWNLOAD")) return DOWNLOAD;
        if(cmd.equals("THOAT")) return THOAT;
        return KHONGLALENH;
    }

    public static final int PORT = 10000;

    public static void main(String[] args) {
        String userA = "tu";
        String passA = "tu";
        String path = System.getProperty("java.io.tmpdir");
        try (ServerSocket s = new ServerSocket(PORT)) {
            System.out.println("FTP Server started on port " + PORT + "...");
            while (true) {
                try (Socket new_s = s.accept()) {
                    // Chỉ tạo Scanner/PrintWriter MỘT lần cho mỗi kết nối (Scanner có buffer riêng)
                    Scanner sc = new Scanner(new_s.getInputStream());
                    PrintWriter pw = new PrintWriter(new_s.getOutputStream(), true);
                    boolean lap = true;
                    while (lap && sc.hasNextLine()) {
                        String cmd = sc.nextLine().trim();
                        switch (laLenh(cmd)) {
                            case DANGNHAP:
                                String user = sc.nextLine();
                                String pass = sc.nextLine();
                                if (user.equals(userA) && pass.equals(passA)) {
                                    pw.println(1);
                                    File[] dsFile = new File(path).listFiles();
                                    if (dsFile != null) {
                                        pw.println(dsFile.length);
                                        for (File f : dsFile) {
                                            pw.println(f.getName());
                                        }
                                    } else {
                                        pw.println(0);
                                    }
                                } else {
                                    pw.println(0);
                                }
                                break;
                            case THOAT:
                                lap = false;
                                break;
                            default:
                                break;
                        }
                    }
                } catch (Exception e) {
                    // lỗi của một client không được làm sập server
                    System.out.println("Client error: " + e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
