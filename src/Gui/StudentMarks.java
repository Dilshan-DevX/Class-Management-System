/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Gui;

import Model.MYSQL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class StudentMarks extends javax.swing.JDialog {

    private static HashMap<String, String> clssMap = new HashMap<>();
    private static HashMap<String, String> monthMap = new HashMap<>();

    private String email;
    private String fname;
    private String lname;

    public StudentMarks(java.awt.Frame parent, boolean modal, String email1, String fname, String lname) {
        super(parent, modal);
        initComponents();
        jLabel1.setText(email1);
//        jLabel4.setText(fname);
//        jLabel6.setText(lname);
        this.email = email1;

        loadClass();
        loadMonth();
        loadMarkTable();

    }

    private void loadClass() {

        try {

            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `student_has_class` INNER JOIN `class` ON `student_has_class`.`class_id`=`class`.`id` WHERE `student_has_class`.student_email = '" + email + "'");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("name"));
                clssMap.put(rs.getString("name"), rs.getString("id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);
            jComboBox17.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }

    }

//     ResultSet rs = MYSQL.executeSearch("SELECT * FROM `month`");
    private void loadMonth() {

        try {
            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("mname"));
                monthMap.put(rs.getString("mname"), rs.getString("id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);
            jComboBox15.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }

    }

    private void loadMarkTable() {

        try {
            String email = jLabel1.getText();

            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `marks` INNER JOIN `class` ON `marks`.`class_id` = `class`.`id`  INNER JOIN `month` ON `marks`.`month_id` = `month`.`id`  INNER JOIN `student` ON `marks`.`student_email` = `student`.`email`  WHERE  `marks`.`student_email` = '" + email + "'");

            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("class.name"));
                vector.add(resultSet.getString("mark"));
                vector.add(resultSet.getString("month.mname"));

                model.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jComboBox15 = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel17.setForeground(new java.awt.Color(255, 255, 255));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel47.setText("Student Marks");

        jTextField19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextField19.setToolTipText("");
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField19KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField19KeyTyped(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Email", "Class name", "Marks", "Month"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.getTableHeader().setReorderingAllowed(false);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable5MouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jComboBox15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name Ascending Order", "Name Descending Order", "Year Ascending Order", "Year Descending Order", "Stream Ascending Order", "Stream Descending Order" }));
        jComboBox15.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox15ItemStateChanged(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel57.setText("Month :");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel58.setText("Email :");

        jComboBox17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name Ascending Order", "Name Descending Order", "Year Ascending Order", "Year Descending Order", "Stream Ascending Order", "Stream Descending Order" }));
        jComboBox17.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox17ItemStateChanged(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel59.setText("Class :");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel62.setText("Marks :");

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton11.setText("ADD");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton12.setText("UPDATE");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton13.setText("CLEAR");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 102, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("EMAIL HERE");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 415, Short.MAX_VALUE)
                                .addComponent(jLabel47)
                                .addGap(458, 458, 458))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel62)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(33, 33, 33))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel57))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel59))
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox17ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox17ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox17ItemStateChanged

    private void jComboBox15ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox15ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox15ItemStateChanged

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        int row = jTable5.getSelectedRow();

        String Class = String.valueOf(jTable5.getValueAt(row, 2));
        jComboBox17.setSelectedItem(Class);
        jComboBox17.setEnabled(false);

        String month = String.valueOf(jTable5.getValueAt(row, 4));
        jComboBox15.setSelectedItem(month);
        jComboBox15.setEnabled(false);

        String marks = String.valueOf(jTable5.getValueAt(row, 3));
        jTextField19.setText(marks);

        jButton11.setEnabled(false);


    }//GEN-LAST:event_jTable5MouseClicked

    private void jTextField19KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19KeyReleased

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jTextField19KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        } else {

            String currentText = ((javax.swing.JTextField) evt.getSource()).getText();

            if (currentText.length() >= 3) {
                evt.consume();
                return;
            }
            String newText = currentText + c;

            try {
                // Parse the new text to an integer
                int newValue = Integer.parseInt(newText);

                // Check if the new value is within the range 0-100
                if (newValue < 0 || newValue > 100) {
                    evt.consume();
                }

            } catch (NumberFormatException e) {
                // If parsing fails, consume the event
                evt.consume();
            }

        }
    }//GEN-LAST:event_jTextField19KeyTyped

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {

            String email = jLabel1.getText();

            String stClass = String.valueOf(jComboBox17.getSelectedIndex());
            String month = String.valueOf(jComboBox15.getSelectedIndex());

            String marks = jTextField19.getText();

            if (stClass.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Class !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (month.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Month !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (marks.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Student Marks !", "warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `marks` WHERE `class_id` = '" + stClass + "' AND  `month_id` = '" + month + "' AND  `student_email` = '" + email + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Marks Allready Added! ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MYSQL.executeIUD("INSERT INTO `marks` (`mark`,`class_id`,`student_email`,`month_id`)"
                            + "VALUES ('" + marks + "','" + stClass + "','" + email + "','" + month + "')");

                    JOptionPane.showMessageDialog(this, "SuccessFully Added !", "Success", JOptionPane.INFORMATION_MESSAGE);

                    loadMarkTable();
//                    reset();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTable5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseEntered

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        try {

            String email = jLabel1.getText();
            String marks = jTextField19.getText();
            String stClass = String.valueOf(jComboBox17.getSelectedIndex());
            String month = String.valueOf(jComboBox15.getSelectedIndex());

            if (marks.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please enter  Marks !", "Warning", JOptionPane.WARNING_MESSAGE);

            } else {

                MYSQL.executeIUD(" UPDATE  `marks`  SET  `mark`= '" + marks + "' WHERE  `student_email` = '" + email + "' AND `class_id` = '" + stClass + "' AND `month_id` = '" + month + "' ");
                    
               
                JOptionPane.showMessageDialog(this, "Success !", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadMarkTable();
                
                jComboBox17.setEnabled(true);
                jComboBox15.setEnabled(true);
                jButton11.setEnabled(true);
                reset();

            }

        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }


    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
       reset();
    }//GEN-LAST:event_jButton13ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(StudentClz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(StudentClz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(StudentClz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(StudentClz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                StudentClz dialog = new StudentClz(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField19;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jComboBox17.setEnabled(true);
        jComboBox15.setEnabled(true);
        jButton11.setEnabled(true);
        
        loadMarkTable();
    }
}
