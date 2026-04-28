/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Gui;

import Model.MYSQL;
import Model.invoiceItem;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ADMIN
 */
public class timetableTeacher extends javax.swing.JFrame {

    private String nic;
    private String fname;

    private static HashMap<String, String> monthMap = new HashMap<>();
    private static HashMap<String, String> subjectMap = new HashMap<>();
    private static HashMap<String, String> classMap = new HashMap<>();
    private static HashMap<String, String> classTypeMap = new HashMap<>();

    public timetableTeacher(String nic, String fname) {
        initComponents();
        jLabel9.setText(fname);
        jLabel13.setText(nic);
        loadClass();
        loadClassType();
        loadMonth();
        loadsubject();
        loadtimetable(nic);

    }

    private void loadMonth() {

        try {
            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `month` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("mname"));
                monthMap.put(rs.getString("month.mname"), rs.getString("month.id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);

            jComboBox18.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadsubject() {

        try {
            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `subject` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("name"));
                subjectMap.put(rs.getString("subject.name"), rs.getString("subject.id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);

            jComboBox19.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadClass() {

        try {
            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `class` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("name"));
                classMap.put(rs.getString("class.name"), rs.getString("class.id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);

            jComboBox17.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadClassType() {

        try {
            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `clz_type` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("type"));
                classTypeMap.put(rs.getString("clz_type.type"), rs.getString("clz_type.id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);

            jComboBox20.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadtimetable(String nic) {

        try {

            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `timetable` INNER JOIN `month` ON `timetable`.`month_id` = `month`.`id`  INNER JOIN `subject` ON `timetable`.`subject_id` = `subject`.`id` INNER JOIN `clz_type` ON `timetable`.`clz_type_id` = `clz_type`.`id` INNER JOIN `teacher` ON `timetable`.`teacher_nic` = `teacher`.`nic` INNER JOIN `class` ON `timetable`.`class_id` = `class`.`id`  WHERE `timetable`.`teacher_nic` = '" + nic + "'  ");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("class.name"));
                vector.add(resultSet.getString("subject.name"));
                vector.add(resultSet.getString("teacher.fname"));
                vector.add(resultSet.getString("month.mname"));
                vector.add(resultSet.getString("timetable.Date"));
                vector.add(resultSet.getString("clz_type.type"));
                vector.add(resultSet.getString("timetable.from"));
                vector.add(resultSet.getString("timetable.to"));

                model.addRow(vector);
//                model2.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        jComboBox20 = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox19 = new javax.swing.JComboBox<>();
        jLabel66 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("TimeTable");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-timetable-35.png"))); // NOI18N

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel57.setText("Class Name :");

        jComboBox17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox17.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox17ItemStateChanged(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class name", "Subject", "Teacher name", "Month", "Date", "Class Type", "From", "To"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel62.setText("Month :");

        jComboBox18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox18.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox18ItemStateChanged(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel64.setText("Class Type :");

        jComboBox20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox20.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox20ItemStateChanged(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("................");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Teacher Name :");

        jComboBox19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox19.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox19ItemStateChanged(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel66.setText("Subject :");

        jCalendar1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel63.setText("Date :");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel65.setText("Time :");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("To");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-schedule-30.png"))); // NOI18N
        jButton1.setText("Schedule");
        jButton1.setIconTextGap(15);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel11.setText("Sir/Mis");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Nic :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("....................................");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-home-35.png"))); // NOI18N
        jButton2.setIconTextGap(10);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-print-35 (3).png"))); // NOI18N
        jButton3.setText("Print");
        jButton3.setIconTextGap(15);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel57)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel66)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel62)
                                        .addComponent(jLabel64))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox18, 0, 167, Short.MAX_VALUE)
                                        .addComponent(jComboBox20, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel63)
                                        .addComponent(jLabel65))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(32, 32, 32)
                                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(39, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65)
                            .addComponent(jLabel1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57)
                            .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64)
                            .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66)))
                    .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox19ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox19ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox19ItemStateChanged

    private void jComboBox20ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox20ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox20ItemStateChanged

    private void jComboBox18ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox18ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox18ItemStateChanged

    private void jComboBox17ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox17ItemStateChanged

//        try {
//
//            String Class = (String) jComboBox17.getSelectedItem();
//
//            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `class` INNER JOIN `teacher` ON `class`.`teacher_nic`=`teacher`.`nic` INNER JOIN `clz_year` ON `class`.`clz_year_id`=`clz_year`.`id` INNER JOIN `clz_fees` ON `class`.`clz_fees_id`=`clz_fees`.`id` INNER JOIN `subject` ON `class`.`subject_id`=`subject`.`id` INNER JOIN `stream` ON `class`.`stream_id`=`stream`.`id` WHERE `class`.`name` = '" + Class + "'");
//
//            while (rs.next()) {
//
//                jLabel58.setText(rs.getString("fname"));
//                jLabel60.setText(rs.getString("class.name"));
//                jLabel59.setText(rs.getString("stream.name"));
//                jLabel61.setText(rs.getString("fees"));
//                jLabel12.setText(rs.getString("fees"));
//
//            }
//
//            //                DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);
//            //
//            //                jComboBox17.setModel(dcm);
//            //                jLabel65.setText(nic);
//            //
//            //                loadMonth();
//            //                loadPaymentMethod();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jComboBox17ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            String tnic = jLabel13.getText();
            String cname = (String) jComboBox17.getSelectedItem();
            String month = (String) jComboBox18.getSelectedItem();
            String subject = (String) jComboBox19.getSelectedItem();
            String ctype = (String) jComboBox20.getSelectedItem();
            Date date = jDateChooser2.getDate();
            String from = jTextField1.getText();
            String to = jTextField2.getText();

            if (cname.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Class !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (month.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Month !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (subject.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Subject !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (ctype.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select ClassType !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (date == null) {
                JOptionPane.showMessageDialog(this, "Please select Date !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (from.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Start Time !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (to.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please End Time !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `timetable` WHERE `class_id` = '" + classMap.get(cname) + "' AND `Date` = '" + date + "'  ");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This class AllReady Schedule! ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String cdate = sdf.format(date);
//                    System.out.println(tnic);
                    MYSQL.executeIUD("INSERT INTO `timetable` (`Date`,`month_id`,`subject_id`,`teacher_nic`,`clz_type_id`,`from`,`to`,`class_id`)"
                            + "VALUES ('" + cdate + "','" + monthMap.get(month) + "','" + subjectMap.get(subject) + "','" + tnic + "','" + classTypeMap.get(ctype) + "','" + from + "','" + to + "','" + classMap.get(cname) + "')");

                    JOptionPane.showMessageDialog(this, "Class Schedule Succesfully !", "Success", JOptionPane.INFORMATION_MESSAGE);

                    loadtimetable(tnic);

                    jComboBox17.setSelectedIndex(0);
                    jComboBox18.setSelectedIndex(0);
                    jComboBox19.setSelectedIndex(0);
                    jComboBox20.setSelectedIndex(0);

                    jDateChooser2.setDate(null);
                    jTextField1.setText("");
                    jTextField2.setText("");

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        if (evt.getClickCount() == 2) {

            int row1 = jTable2.getSelectedRow();

            if (row1 == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student to delete", "Information", JOptionPane.WARNING_MESSAGE);
            } else {

                try {
                    String tnic = jLabel13.getText();
                    String date = String.valueOf(jTable2.getValueAt(row1, 4));
                    String from = String.valueOf(jTable2.getValueAt(row1, 6));
                    String to = String.valueOf(jTable2.getValueAt(row1, 7));

                    int response = JOptionPane.showConfirmDialog(
                            null,
                            "Do you want to continue to delete?",
                            "Select an Option",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (response == JOptionPane.YES_OPTION) {

                        MYSQL.executeIUD("DELETE FROM `timetable` WHERE   `Date` = '" + date + "' AND  `from` = '" + from + "' AND  `to` = '" + to + "'");
                        JOptionPane.showMessageDialog(this, "Successfully Deleted", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        loadtimetable(tnic);

                    } else if (response == JOptionPane.NO_OPTION) {
                        loadtimetable(tnic);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
                }

            }

        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {

            String tnic = jLabel13.getText();

            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `timetable` WHERE `teacher_nic` = '" + tnic + "' ");

            if (resultSet.next()) {

                String path = "src//reports//timetable.jasper";

                HashMap<String, Object> params = new HashMap<>();
                params.put("Parameter1", jLabel9.getText());
                params.put("Parameter2", jLabel13.getText());
//                params.put("Parameter2", jFormattedTextField1.getText());

                JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());

                JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, dataSource);

                JOptionPane.showMessageDialog(this, " SuccessFull !", "Success", JOptionPane.INFORMATION_MESSAGE);

                JasperViewer.viewReport(jasperPrint, false);

            } else {

                JOptionPane.showMessageDialog(this, "Not Scheduled Time Table!", "Warning", JOptionPane.WARNING_MESSAGE);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

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
//            java.util.logging.Logger.getLogger(timetableTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(timetableTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(timetableTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(timetableTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new timetableTeacher().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox20;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
