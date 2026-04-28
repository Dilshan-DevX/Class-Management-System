package Gui;

import Model.MYSQL;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class signin extends javax.swing.JFrame {

    /**
     * Creates new form signin
     */
    public signin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        bgimg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("NIC Number");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 105, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 214, -1, -1));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 280, 37));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setText("Sign In");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 290, 50));

        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 280, 37));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel3.setText("Sign In");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        bgimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/background (2).jpg"))); // NOI18N
        jPanel1.add(bgimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 410, 480));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String Nic = jTextField1.getText();
        String password = String.valueOf(jPasswordField1.getPassword());

        if (Nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter youer NIC Number !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (Nic.length() < 11) {
            JOptionPane.showMessageDialog(this, "Invalid NIC Number !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter youer Password !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            JOptionPane.showMessageDialog(this, "Invalid Minimum eight characters, at least one letter and one number !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet rs = MYSQL.executeSearch("SELECT * FROM `staff` WHERE `nic` = '" + Nic + "' AND `password`='" + password + "' AND `type_id`='1' ");

                if (rs.next()) {

                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    String email = rs.getString("email");
                    String mobile = rs.getString("mobile");

                    Dashboard dashboard = new Dashboard(email,fname,lname,mobile);
                    dashboard.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Email OR Password OR you Are Not Valid Admin !", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        FlatMacDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new signin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgimg;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
