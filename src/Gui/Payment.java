/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Gui;

import Model.MYSQL;
import Model.invoiceItem;
import java.awt.Color;
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

public class Payment extends javax.swing.JFrame {

    private String nic;

    private static HashMap<String, String> yearMap = new HashMap<>();
    private static HashMap<String, String> streamMap = new HashMap<>();
    private static HashMap<String, String> classMap = new HashMap<>();
    private static HashMap<String, String> monthMap = new HashMap<>();
    private static HashMap<String, String> paymentMethodMap = new HashMap<>();
    private static HashMap<String, invoiceItem> invoiceItemMap = new HashMap<>();

    public Payment(String nic) {

        initComponents();

        this.nic = nic;

        loadYear();
        loadStream();
        loadMonth();
        loadPaymentMethod();
        jComboBox17.setEnabled(false);
        jComboBox18.setEnabled(false);
//        jComboBox19.setEnabled(false);
        jComboBox20.setEnabled(false);
        jButton11.setEnabled(false);
        jButton1.setEnabled(false);

    }

    private void loadYear() {

        try {
            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `clz_year` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("year"));
                yearMap.put(rs.getString("year"), rs.getString("id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);

            jComboBox14.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }

    }

    private void loadStream() {

        try {
            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `stream` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("name"));
                streamMap.put(rs.getString("name"), rs.getString("id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);

            jComboBox13.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }

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

    private void loadPaymentMethod() {

        try {
            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `payment_method` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("name"));
                paymentMethodMap.put(rs.getString("name"), rs.getString("id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);

            jComboBox20.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }

    }

    private void loadStudentMng(String column, String orderby, String email, String year, String stream) {

        try {

            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `student` INNER JOIN `clz_year` ON `student`.`clz_year_id` = `clz_year`.`id`  INNER JOIN `stream` ON `student`.`stream_id` = `stream`.`id`  WHERE `email` LIKE '" + email + "%' AND  `clz_year_id` = '" + yearMap.get(year) + "' AND `stream_id` = '" + streamMap.get(stream) + "' ORDER BY `" + column + "`  " + orderby + "");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("fname"));
                vector.add(resultSet.getString("lname"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("clz_year.year"));
                vector.add(resultSet.getString("stream.name"));

                model.addRow(vector);
//                model2.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }

    }

    private void seacrch() {

        int sort = jComboBox12.getSelectedIndex();
        String email = jTextField20.getText();
        String year = String.valueOf(jComboBox14.getSelectedItem());
        String stream = String.valueOf(jComboBox13.getSelectedItem());

        if (year.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please seletct year !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            loadStudentMng("fname", "ASC", email, year, stream);
        }

        if (stream.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please seletct Stream !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            loadStudentMng("fname", "ASC", email, year, stream);
        }

        if (sort == 0) {
            loadStudentMng("fname", "ASC", email, year, stream);
        } else if (sort == 1) {
            loadStudentMng("fname", "DESC", email, year, stream);
        } else if (sort == 2) {
            loadStudentMng("clz_year` . `year", "ASC", email, year, stream);
        } else if (sort == 3) {
            loadStudentMng("clz_year` . `year", "DESC", email, year, stream);
        } else if (sort == 4) {
            loadStudentMng("stream` . `name", "ASC", email, year, stream);
        } else if (sort == 5) {
            loadStudentMng("stream` . `name", "DESC", email, year, stream);
        }

    }

    private void loadInvoicetems() {

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        for (invoiceItem invitem : invoiceItemMap.values()) {

            Vector<String> vector = new Vector<>();

            vector.add(invitem.getcName());
            vector.add(invitem.getMonth());
            vector.add(invitem.getStudentEmail());
            vector.add(invitem.getStaffNic());
            vector.add(invitem.getPaymentMethod());
            vector.add(String.valueOf(invitem.getPayamount()));

//            total += itemtotal;
//            vector.add(String.valueOf(itemtotal));
            model.addRow(vector);

        }

//        jLabel19.setText(String.valueOf(total));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jTextField20 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jComboBox20 = new javax.swing.JComboBox<>();
        jLabel65 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Payments");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-payment-35.png"))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel56.setText("Year :");

        jComboBox14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name Ascending Order", "Name Descending Order", "Year Ascending Order", "Year Descending Order", "Stream Ascending Order", "Stream Descending Order" }));
        jComboBox14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox14ItemStateChanged(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel55.setText("Stream :");

        jComboBox13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name Ascending Order", "Name Descending Order", "Year Ascending Order", "Year Descending Order", "Stream Ascending Order", "Stream Descending Order" }));
        jComboBox13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox13ItemStateChanged(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel54.setText("Sort :");

        jComboBox12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name Ascending Order", "Name Descending Order", "Year Ascending Order", "Year Descending Order", "Stream Ascending Order", "Stream Descending Order" }));
        jComboBox12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox12ItemStateChanged(evt);
            }
        });

        jTextField20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextField20.setToolTipText("");
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });
        jTextField20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField20KeyReleased(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton10.setText("Search");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search-31.png"))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Email", "Mobile", "Year", "Stream"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-home-35.png"))); // NOI18N
        jButton2.setText("HOME ");
        jButton2.setIconTextGap(10);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1378, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)))))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel56))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel55))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel54))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("To Pay");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-pay-35.png"))); // NOI18N

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel57.setText("Class Name :");

        jComboBox17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox17.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox17ItemStateChanged(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton11.setText("ADD INVOICE");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class name", "Month", "Student Email", "Staff NIC", "Payment Method", "Paid Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Teacher Name :");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel58.setText("....................................................");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Stream :");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel59.setText("....................................................");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Subject Name :");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel60.setText("....................................................");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Class Fees :");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel61.setText("....................................................");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel62.setText("Month :");

        jComboBox18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox18.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox18ItemStateChanged(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel63.setText("Staff Nic :");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel64.setText("Payment Method :");

        jComboBox20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox20.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox20ItemStateChanged(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel65.setText("...........................................................");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText(".....................................................");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Student Email :");

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel11.setText("Total Payable :");

        jLabel12.setBackground(new java.awt.Color(255, 0, 51));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("0.00");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel13.setText("Balance :");

        jLabel14.setBackground(new java.awt.Color(255, 0, 51));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("0.00");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-print-30.png"))); // NOI18N
        jButton1.setText("Print Invoice");
        jButton1.setIconTextGap(10);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setText("Payment :");

        jFormattedTextField1.setForeground(new java.awt.Color(255, 0, 51));
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane2)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel57)
                                            .addComponent(jLabel63))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBox17, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel62)
                                            .addComponent(jLabel64))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBox18, 0, 167, Short.MAX_VALUE)
                                            .addComponent(jComboBox20, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(166, 166, 166)
                                                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGap(26, 26, 26)
                                                        .addComponent(jLabel8)
                                                        .addGap(6, 6, 6)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                        .addGap(82, 82, 82))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel58)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel59))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel57)
                                            .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel62))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel63)
                                                .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel64))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel60)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel61))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel8))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox14ItemStateChanged

    }//GEN-LAST:event_jComboBox14ItemStateChanged

    private void jComboBox13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox13ItemStateChanged

    }//GEN-LAST:event_jComboBox13ItemStateChanged

    private void jComboBox12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox12ItemStateChanged
        seacrch();
    }//GEN-LAST:event_jComboBox12ItemStateChanged

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jTextField20KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField20KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20KeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        seacrch();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jComboBox17ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox17ItemStateChanged

        try {

            String Class = (String) jComboBox17.getSelectedItem();

            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `class` INNER JOIN `teacher` ON `class`.`teacher_nic`=`teacher`.`nic` INNER JOIN `clz_year` ON `class`.`clz_year_id`=`clz_year`.`id` INNER JOIN `clz_fees` ON `class`.`clz_fees_id`=`clz_fees`.`id` INNER JOIN `subject` ON `class`.`subject_id`=`subject`.`id` INNER JOIN `stream` ON `class`.`stream_id`=`stream`.`id` WHERE `class`.`name` = '" + Class + "'");

            while (rs.next()) {

                jLabel58.setText(rs.getString("fname"));
                jLabel60.setText(rs.getString("class.name"));
                jLabel59.setText(rs.getString("stream.name"));
                jLabel61.setText(rs.getString("fees"));
                jLabel12.setText(rs.getString("fees"));

            }

//                DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);
//
//                jComboBox17.setModel(dcm);
//                jLabel65.setText(nic);
//               
//                loadMonth();
//                loadPaymentMethod();
        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }

    }//GEN-LAST:event_jComboBox17ItemStateChanged

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
  try {
        String name = (String) jComboBox17.getSelectedItem();
        String month = (String) jComboBox18.getSelectedItem();
        String studentEmail = jLabel9.getText();
        String staffnic = jLabel65.getText();
        String pm = (String) jComboBox20.getSelectedItem();
        String paidAmount = jLabel61.getText();

        if (name.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select Class !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (month.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select Month !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (pm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter PaymentMethod !", "warning", JOptionPane.WARNING_MESSAGE);
        } else {

            invoiceItem invitem = new invoiceItem();
            invitem.setcName(name);
            invitem.setMonth(month);
            invitem.setStudentEmail(studentEmail);
            invitem.setStaffNic(staffnic);
            invitem.setPaymentMethod(pm);
            invitem.setPayamount(paidAmount);

            invoiceItemMap.put(studentEmail, invitem);
            loadInvoicetems();
            jButton1.setEnabled(true);
            
            //            String pm = jLabel61.getText();
//            String lname = jTextField2.getText();
//            String mobile1 = jTextField3.getText();
//            String email = jTextField4.getText();
//            String line1 = jTextField6.getText();
//            String line2 = jTextField7.getText();
//            String city = String.valueOf(jComboBox1.getSelectedItem());
//            String year = String.valueOf(jComboBox2.getSelectedItem());
//            String stream = String.valueOf(jComboBox3.getSelectedItem());
//            String gender = String.valueOf(jComboBox4.getSelectedItem());

            if (paidAmount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter youer payment !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (month.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select Month !", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (staffnic.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Class Not selected !", "Warning", JOptionPane.WARNING_MESSAGE);
            }  else if (studentEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Student in First table !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (name.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Class !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (pm.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Payment Method !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `invoice` WHERE `student_email` = '" + studentEmail + "' AND `month_id` = '"+monthMap.get(month)+"' AND `class_id` = '"+ classMap.get(name)+"' ");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Student Allready Payed! ", "Warning", JOptionPane.WARNING_MESSAGE);
                    jButton1.setEnabled(false);
                } else {

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    MYSQL.executeIUD("INSERT INTO `invoice` (`date`,`paid_amount`,`month_id`,`staff_nic`,`student_email`,`class_id`,`payment_method_id`)"
                            + "VALUES ('" + sdf.format(date) + "','" + paidAmount + "','"+monthMap.get(month)+"','" + staffnic + "','" + studentEmail + "','"+ classMap.get(name)+"','" + paymentMethodMap.get(pm) + "')");

                    JOptionPane.showMessageDialog(this, "Payment Saved !", "Success", JOptionPane.INFORMATION_MESSAGE);
                    jButton11.setEnabled(false);

//                    DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
//                    model.removeRow(0);
                    
//                    jComboBox17.setSelectedIndex(0);
//                    jComboBox18.setSelectedIndex(0);
//                    jComboBox20.setSelectedIndex(0);
//                    
//                    jLabel58.setText("....................................................");
//                    jLabel59.setText("....................................................");
//                    jLabel60.setText("....................................................");
//                    jLabel61.setText("....................................................");
//                    jLabel65.setText("....................................................");
//                    jLabel9.setText(".....................................................");
                    
                    jComboBox17.setEnabled(false);
                    jComboBox18.setEnabled(false);
                    jComboBox20.setEnabled(false);
                    
                    jLabel58.setEnabled(false);
                    jLabel59.setEnabled(false);
                    jLabel60.setEnabled(false);
                    jLabel61.setEnabled(false);
                    jLabel65.setEnabled(false);
                    jLabel9.setEnabled(false);

                }

            }
            
        }
        
        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }


    }//GEN-LAST:event_jButton11ActionPerformed

    private void jComboBox18ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox18ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox18ItemStateChanged

    private void jComboBox20ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox20ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox20ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {

            jComboBox17.setEnabled(true);
            jComboBox18.setEnabled(true);
//            jComboBox19.setEnabled(true);
            jComboBox20.setEnabled(true);
            jButton11.setEnabled(true);

            int row1 = jTable1.getSelectedRow();

            try {

                String email = String.valueOf(jTable1.getValueAt(row1, 2));

                ResultSet rs = MYSQL.executeSearch("SELECT * FROM `student_has_class` INNER JOIN `class` ON `student_has_class`.`class_id` = `class`.`id` WHERE `student_has_class`.`student_email` = '" + email + "' ");

                Vector<String> vector = new Vector<>();
                vector.add("Select");

                while (rs.next()) {
                    vector.add(rs.getString("class.name"));
                    classMap.put(rs.getString("class.name"), rs.getString("class.id"));
                }

                DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);

                jComboBox17.setModel(dcm);
                jLabel65.setText(nic);
                jLabel9.setText(email);

                loadMonth();
                loadPaymentMethod();

            } catch (Exception e) {
                e.printStackTrace();
                Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
            }

//            StudentClz studentClz = new StudentClz(this,true,email1,fname,lname);
//            studentClz.setVisible(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jFormattedTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyReleased
         String total = jLabel12.getText();
        String payment = jFormattedTextField1.getText();

        if (payment.isEmpty()) {

            payment = "0";

        } else if (payment.matches("^ (0 | [1-9] \\d*) ? (\\.\\d+) ? (?<=\\d) $")) {
            jLabel14.setText("INVALID"); 
            jLabel14.setForeground(Color.RED);
        } else {

            double balence = Double.parseDouble(payment) - Double.parseDouble(total);
            jLabel14.setText(String.valueOf(balence));
            jLabel14.setForeground(Color.WHITE);

        }
    }//GEN-LAST:event_jFormattedTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String payment = jFormattedTextField1.getText();
       
        if (payment.isEmpty()) {
            
                JOptionPane.showMessageDialog(this, "Please Complete the payment !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
        
        
        try {
            
          if (payment.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Complete the payment !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else{
                String path = "src//reports//InvoiceSiksa.jasper";
                
                HashMap<String,Object> params = new HashMap<>();
                params.put("Parameter1", jLabel12.getText());
                params.put("Parameter2", jFormattedTextField1.getText());
                params.put("Parameter3", jLabel14.getText());
                
                JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());
                
                JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, dataSource);
                
                JasperViewer.viewReport(jasperPrint,false);
                
                    JOptionPane.showMessageDialog(this, "Payment SuccessFull !", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                    DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                    model.removeRow(0);
          
                    jLabel12.setText("0.00");
                    jFormattedTextField1.setText("0.00");
                    jLabel14.setText("0.00");
                    jButton1.setEnabled(false);
                    jButton11.setEnabled(true);
                    
                  
          }
        } catch (Exception e) {
            e.printStackTrace();
            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                    Home home = new Home();
                    home.setVisible(true);
                    this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

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
//            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Payment().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField20;
    // End of variables declaration//GEN-END:variables
}
