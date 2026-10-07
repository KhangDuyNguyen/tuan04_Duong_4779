package tcp;

import javax.swing.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class frmClient extends JFrame {
    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;
    
    private JTextField txtchuoi;
    private JTextField txtketqua;
    private JButton btnTruyenChuoi;
    private JButton btnThoat;
    
    public frmClient() {
        initComponents();
        txtchuoi.requestFocus();
    }
    
    private void initComponents() {
        setTitle("TCP Client");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel lblChuoi = new JLabel("Nhập chuỗi");
        lblChuoi.setBounds(30, 30, 80, 25);
        add(lblChuoi);
        
        txtchuoi = new JTextField();
        txtchuoi.setBounds(120, 30, 230, 25);
        add(txtchuoi);
        
        JLabel lblKetQua = new JLabel("Kết quả");
        lblKetQua.setBounds(30, 70, 80, 25);
        add(lblKetQua);
        
        txtketqua = new JTextField();
        txtketqua.setBounds(120, 70, 230, 25);
        txtketqua.setBackground(new java.awt.Color(204, 204, 204));
        txtketqua.setEditable(false);
        add(txtketqua);
        
        btnTruyenChuoi = new JButton("Truyền chuỗi");
        btnTruyenChuoi.setBounds(80, 120, 120, 30);
        btnTruyenChuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntruyenchuoiActionPerformed(evt);
            }
        });
        add(btnTruyenChuoi);
        
        btnThoat = new JButton("Thoát");
        btnThoat.setBounds(220, 120, 80, 30);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(btnThoat);
    }

    private void btntruyenchuoiActionPerformed(java.awt.event.ActionEvent evt) {
        String chuoi=txtchuoi.getText(); //Lấy chuỗi
        String ketqua="";
        try{
            //Socket nhận tham số là địa chỉ Host và port
            socket=new Socket("127.0.0.1",1234);
            out=new PrintWriter(socket.getOutputStream(),true);
            in=new Scanner(socket.getInputStream());
            out.println(chuoi);//truyền chuỗi lên server để xử lý
            ketqua=in.nextLine().trim();//nhận chuỗi kết quả từ server
            txtketqua.setText(ketqua);//Hiển thị chuỗi nhận được từ server lên TextField
            socket.close();//Đóng Socket
        }catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Không kết nối được tới server: " + e.getMessage());
            try{if(socket!=null) socket.close();}catch(Exception ex){ex.printStackTrace();}
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmClient().setVisible(true);
            }
        });
    }
}
