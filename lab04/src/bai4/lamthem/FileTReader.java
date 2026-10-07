package bai4.lamthem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileTReader extends Thread {
    private String fileName;

    public FileTReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(Thread.currentThread().getName() + " đọc từ " + fileName + ": " + line);
                Thread.sleep(100);
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file " + fileName + " (Có thể file chưa được tạo): " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        FileTReader t1 = new FileTReader("file1.txt");
        FileTReader t2 = new FileTReader("file2.txt");
        FileTReader t3 = new FileTReader("file3.txt");

        t1.start();
        t2.start();
        t3.start();
    }
}
