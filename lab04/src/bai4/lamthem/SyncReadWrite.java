package bai4.lamthem;

import java.io.*;
import java.util.Random;

class FileSync {
    private final String fileName;
    private boolean isWritten = false;
    
    public FileSync(String fileName) {
        this.fileName = fileName;
    }
    
    public synchronized void write() {
        while (isWritten) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        try (FileWriter fw = new FileWriter(fileName, true)) {
            int num = new Random().nextInt(100);
            fw.write(num + "\n");
            System.out.println("Ghi vào file: " + num);
            isWritten = true;
            notifyAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void read() {
        while (!isWritten) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            String lastLine = "";
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
            System.out.println("Đọc từ file: " + lastLine);
            isWritten = false;
            notifyAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WriteFileThread extends Thread {
    private FileSync fileSync;
    public WriteFileThread(FileSync fileSync) { this.fileSync = fileSync; }
    @Override public void run() {
        for (int i = 0; i < 5; i++) {
            fileSync.write();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

class ReadFileThread extends Thread {
    private FileSync fileSync;
    public ReadFileThread(FileSync fileSync) { this.fileSync = fileSync; }
    @Override public void run() {
        for (int i = 0; i < 5; i++) {
            fileSync.read();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

public class SyncReadWrite {
    public static void main(String[] args) {
        FileSync fileSync = new FileSync("sync_data.txt");
        try { new FileWriter("sync_data.txt").close(); } catch (IOException e) {}
        
        WriteFileThread t1 = new WriteFileThread(fileSync);
        ReadFileThread t2 = new ReadFileThread(fileSync);
        t1.start();
        t2.start();
    }
}
