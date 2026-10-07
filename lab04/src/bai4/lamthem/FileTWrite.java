package bai4.lamthem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileTWrite extends Thread {
    private String fileName;

    public FileTWrite(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (FileWriter fw = new FileWriter(fileName)) {
            Random rand = new Random();
            for (int i = 0; i < 10; i++) {
                int num = rand.nextInt(100);
                fw.write(num + " ");
                fw.flush();
                System.out.println(Thread.currentThread().getName() + " viết: " + num + " vào " + fileName);
                Thread.sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        FileTWrite t1 = new FileTWrite("file1.txt");
        FileTWrite t2 = new FileTWrite("file2.txt");
        FileTWrite t3 = new FileTWrite("file3.txt");

        t1.start();
        t2.start();
        t3.start();

        // Chờ ghi xong để luồng đọc không đọc file đang dở dang
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
