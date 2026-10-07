package bai5.bt2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private Scanner in = null;
    private PrintWriter out = null;
    private Socket socket;
    private String name;

    public ServerThread(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.name = name;
        this.in = new Scanner(this.socket.getInputStream());
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        new Thread(this).start();
    }

    public void run() {
        try {
            while(true) {
                String chuoi = in.nextLine().trim();
                try (Scanner sc = new Scanner(chuoi)) {
                    sc.useDelimiter("@");
                    int so1 = sc.nextInt();
                    String pheptoan = sc.next();
                    int so2 = sc.nextInt();

                    if (pheptoan.equals("+")) out.println((long) so1 + so2);
                    else if (pheptoan.equals("-")) out.println((long) so1 - so2);
                    else if (pheptoan.equals("*")) out.println((long) so1 * so2);
                    else if (pheptoan.equals("/")) {
                        if (so2 == 0) out.println("Loi: chia cho 0");
                        else out.println((double) so1 / so2);
                    } else out.println("Loi: phep toan khong hop le");
                } catch (java.util.NoSuchElementException e) {
                    out.println("Loi: du lieu khong hop le");
                }
            }
        } catch(Exception e) {
            System.out.println(name + " has departed");
        } finally {
            try { socket.close(); } catch(IOException e) {}
        }
    }
}
