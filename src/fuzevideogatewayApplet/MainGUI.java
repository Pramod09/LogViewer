/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzevideogatewayApplet;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import static java.util.logging.Level.parse;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *
 * @author GS-1062
 */
public class MainGUI extends javax.swing.JFrame {
    private Object fuzevideogateway;
    private Component JFrame;
    static private AddServer addserverfrm;
    static private AddLogFile alfobj;
    
    public MainGUI() {
        initComponents();
        createDB();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("fuzelog.png")));
    }
    static void createDB(){
        String path = System.getProperty("user.name");
        File f = new File(path+"fuze.odb");
        
        if(f.exists() == false){
        try{
            Database db = DatabaseBuilder.create(Database.FileFormat.V2000, f);
            db.close();
        }
        catch(Exception e){
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Could not create database file", "Error", JOptionPane.ERROR_MESSAGE);
            f.delete();
            System.exit(0);
        }
        try{
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
            Statement s;
            s = conn.createStatement();
 
            String table1 = "CREATE TABLE SERVERS " +
                            "(SID INTEGER not NULL, " +
                            " SERVER_NAME VARCHAR(45), " + 
                            " IP VARCHAR(45), " + 
                            " USERS VARCHAR(45), " + 
                            " PASSWORD VARCHAR(45), " +
                            " GROUPID INTEGER, "+
                            " PRIMARY KEY ( IP ))"; 

            String table2 = "CREATE TABLE LOGFILES " +
                            "(LID INTEGER NOT NULL," +
                            "LOGFILENAME VARCHAR(45)," +
                            "PATH VARCHAR(45)," +
                            "SID INTEGER not NULL)";
                            
            String table3 = "CREATE TABLE LOGIN" +
                            "(ID INTEGER NOT NULL, " +
                            "USERNAME VARCHAR(45), " +
                             "PASSWORD VARCHAR(45), " +
                            "PRIMARY KEY (ID) "+
                            ")";
                
            String table4 = "CREATE TABLE GROUPS " +
                            "(GID INTEGER NOT NULL," +
                            "GROUP_NAME VARCHAR(45)," +
                            "PRIMARY KEY (GROUP_NAME) "+
                            ")";
     
            try{
                s.executeUpdate(table1);
                s.executeUpdate(table2);
                s.executeUpdate(table3);    
                s.executeUpdate(table4);
            
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Databse created.","Database",JOptionPane.INFORMATION_MESSAGE);
                
                conn.close();
            
            }
            catch(Exception e){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Problem with database Creation.","Database",JOptionPane.INFORMATION_MESSAGE);
            }
      
            }
            catch(Exception w){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Could not create database file1", "Error", JOptionPane.ERROR_MESSAGE);
                f.delete();
                System.exit(0);
            }
            IntialFrom ifrm = new IntialFrom();
            ifrm.setVisible(true);
        }
   
    }
    static void fillCombo(){
    
        String path = System.getProperty("user.name");
        File f = new File(path+"fuze.odb");
        try{
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
            Statement s = null;
            s = conn.createStatement();
            ResultSet rs;
            rs = s.executeQuery("select * from GROUPS"); 
     
            while (rs.next()) {
                frame.combo_grp.addItem(rs.getString("GROUP_NAME"));
            }
        
        }
        catch(Exception e){
            System.out.print(" "+e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        pop_connect = new javax.swing.JMenuItem();
        pop_addserver = new javax.swing.JMenuItem();
        pop_removeserver = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ip = new javax.swing.JTable();
        combo_grp = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_addserver = new javax.swing.JMenuItem();
        menu_file = new javax.swing.JMenuItem();
        menu_grp = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pop_connect.setText("Connect");
        pop_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pop_connectActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pop_connect);

        pop_addserver.setText("Add server");
        pop_addserver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pop_addserverMouseClicked(evt);
            }
        });
        pop_addserver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pop_addserverActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pop_addserver);

        pop_removeserver.setText("Remove server");
        pop_removeserver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pop_removeserverActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pop_removeserver);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("MainGUI"); // NOI18N
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tbl_ip.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Server name", "User"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_ip.setComponentPopupMenu(jPopupMenu1);
        tbl_ip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ipMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_ipMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_ipMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_ipMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_ip);

        combo_grp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combo_grpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                combo_grpFocusLost(evt);
            }
        });
        combo_grp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_grpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                combo_grpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                combo_grpMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                combo_grpMousePressed(evt);
            }
        });
        combo_grp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_grpActionPerformed(evt);
            }
        });

        jMenu1.setText("Add");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        menu_addserver.setText("New server");
        menu_addserver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_addserverActionPerformed(evt);
            }
        });
        jMenu1.add(menu_addserver);

        menu_file.setText("New file");
        menu_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_fileActionPerformed(evt);
            }
        });
        jMenu1.add(menu_file);

        menu_grp.setText("New group");
        menu_grp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_grpActionPerformed(evt);
            }
        });
        jMenu1.add(menu_grp);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Watch");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Terminal");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_grp, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(combo_grp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                        .addGap(162, 162, 162))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
        
    private void menu_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_fileActionPerformed
        alfobj = new AddLogFile();
        alfobj.setVisible(true);
    
    }//GEN-LAST:event_menu_fileActionPerformed
    int begSearch = 0;   // for start a search from beggining of the text area
    int endSearch = 0;  //  for starting a search from the end of the text area
        
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.setExtendedState(MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowActivated

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        
    }//GEN-LAST:event_formWindowStateChanged

    private void tbl_ipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ipMouseClicked

        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            String user = null,host = null,passwd = null;
            Jpanel jp1;
       
            DefaultTableModel model;
            model = (DefaultTableModel)tbl_ip.getModel();
            String ser_name = null;

            try{
                String path = System.getProperty("user.name");
                File f = new File(path+"fuze.odb");
                ser_name = model.getValueAt(tbl_ip.getSelectedRow(),0).toString();
                Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
                Statement s = null;
                s = conn.createStatement();
                ResultSet rs;
    
                String q = "select IP from SERVERS WHERE SERVER_NAME = \"" +ser_name+"\" ;";
    
                rs = s.executeQuery(q);
    
                while (rs.next()) {
                    host = rs.getString("IP");
                }
            }
            catch(Exception e){
            
            }
            if( host != "")
            {   
                String path = System.getProperty("user.name");
                File f = new File(path+"fuze.odb");
                try{
                    Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
                    Statement s = null;
                    s = conn.createStatement();
                    ResultSet rs;
    
                    String q = "select USERS,PASSWORD from SERVERS WHERE IP = \"" +host+"\" ;";
                    rs = s.executeQuery(q);
    
                    while (rs.next()){
                        user = rs.getString("USERS");
                        passwd = rs.getString("PASSWORD");
                    }
                    if(user.length() != 0 && host.length() != 0 && passwd.length() != 0){
                        jp1 = new Jpanel(user,host,passwd);
                        jTabbedPane1.addTab(ser_name,jp1);// TODO add your handling code here:
                        int i = jTabbedPane1.indexOfComponent(jp1);
                        jTabbedPane1.setSelectedIndex(i);
                    }
                }
                catch(Exception e){
                    System.out.print("Unable to connect.");
                }
            }
            else
            {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel," "+"Please STOP the log.","Server",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_tbl_ipMouseClicked

    private void tbl_ipMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ipMousePressed

    }//GEN-LAST:event_tbl_ipMousePressed

    private void tbl_ipMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ipMouseEntered
        
    }//GEN-LAST:event_tbl_ipMouseEntered

    private void menu_grpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_grpActionPerformed

        String group = JOptionPane.showInputDialog(frame, "Enter the group name : ","Group",JOptionPane.INFORMATION_MESSAGE);
        String path = System.getProperty("user.name");
        File f = new File(path+"fuze.odb");
        try{
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
            Statement s = null;
            s = conn.createStatement();
            ResultSet rs;
            rs = s.executeQuery("select * from GROUPS"); 
            int i = 0;
            while(rs.next()){
                ++i;
            }
            group.trim();
            if(group.isEmpty() == false)
            {
                String q1 ="insert into GROUPS values("+ ++i +",\""+group+"\");";
                try{ 
                    s.execute(q1);
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel,group+" "+"Group added successfully.","Group",JOptionPane.INFORMATION_MESSAGE);
                    frame.combo_grp.addItem(group);
                }
                catch(Exception e){
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel,group+" "+"Group already exites","Group",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel,"Please check","Information",JOptionPane.WARNING_MESSAGE);
            }
        }                      
        catch(Exception e){
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel,"OOps  "+e,"Information",JOptionPane.WARNING_MESSAGE);
        }
   
    }//GEN-LAST:event_menu_grpActionPerformed

    private void combo_grpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_grpMouseClicked
      
    }//GEN-LAST:event_combo_grpMouseClicked

    private void combo_grpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_grpActionPerformed
 
        sele = combo_grp.getSelectedItem().toString();
        if(isNewSeradded){
            
            DefaultTableModel model;
            String path = System.getProperty("user.name");
            File f = new File(path+"fuze.odb");
            
            model = (DefaultTableModel)tbl_ip.getModel();
            String sn = null,us = null;
            model.setRowCount(1);
            
            try{
                Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
                Statement s = null;
                s = conn.createStatement();
                ResultSet rs;
      
                rs = s.executeQuery("select * from SERVERS where GROUPID =(select GID from GROUPS WHERE GROUP_NAME=\""+sele+"\");");
                while (rs.next()){
                
                    sn = rs.getString("SERVER_NAME");
                    us = rs.getString("USERS");
                    Object[] data = {sn,us};
                    model.insertRow(1,data);
                }
            }
            catch(Exception e){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel,"OOps"+"  "+e,"Information",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_combo_grpActionPerformed

    private void combo_grpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_grpMouseEntered
        
    }//GEN-LAST:event_combo_grpMouseEntered

    private void combo_grpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_grpMousePressed
        
    }//GEN-LAST:event_combo_grpMousePressed

    private void combo_grpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combo_grpFocusLost

    }//GEN-LAST:event_combo_grpFocusLost

    private void combo_grpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combo_grpFocusGained

    }//GEN-LAST:event_combo_grpFocusGained
    static String sele = null;
    private void combo_grpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_grpMouseExited
    
    }//GEN-LAST:event_combo_grpMouseExited

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
      
    }//GEN-LAST:event_jMenu3ActionPerformed
    String i = "asdf";    
    private void tbl_ipMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ipMouseReleased
      
    }//GEN-LAST:event_tbl_ipMouseReleased

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased

    }//GEN-LAST:event_formMouseReleased

    private void pop_addserverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pop_addserverMouseClicked
        
    }//GEN-LAST:event_pop_addserverMouseClicked

    private void pop_addserverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pop_addserverActionPerformed
        addserverfrm = new AddServer();
        addserverfrm.setVisible(true);        
    }//GEN-LAST:event_pop_addserverActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
      
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void menu_addserverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_addserverActionPerformed
        addserverfrm = new AddServer();
        addserverfrm.setVisible(true);
        
    }//GEN-LAST:event_menu_addserverActionPerformed

    private void pop_removeserverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pop_removeserverActionPerformed

        
                
            DefaultTableModel model;
            model = (DefaultTableModel)tbl_ip.getModel();
            String ser_name = null;

            try{
                String path = System.getProperty("user.name");
                File f = new File(path+"fuze.odb");
                ser_name = model.getValueAt(tbl_ip.getSelectedRow(),0).toString();
                Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
                Statement s = null;
                s = conn.createStatement();
                ResultSet rs;
    
                String q1 = "select SID from SERVERS where SERVER_NAME = \"" +ser_name+"\" ;";
                rs = s.executeQuery(q1);
                int sid = 0;
                while (rs.next()) {
                    sid = rs.getInt("SID");
                }
                
                q1 = "delete * from LOGFILES where SID = "+sid+";";
    
                s.executeUpdate(q1);
                
                String q = "delete from SERVERS WHERE SERVER_NAME = \"" +ser_name+"\" ;";
    
                s.executeUpdate(q);
                
                System.out.print("server deleted");
    /*
                while (rs.next()) {
                    host = rs.getString("IP");
                }*/
            }
            catch(Exception e){
                System.out.print("server not deleted");
            }
                
        
    }//GEN-LAST:event_pop_removeserverActionPerformed

    private void pop_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pop_connectActionPerformed

            String user = null,host = null,passwd = null;
            Jpanel jp1;
       
            DefaultTableModel model;
            model = (DefaultTableModel)tbl_ip.getModel();
            String ser_name = null;

            try{
                String path = System.getProperty("user.name");
                File f = new File(path+"fuze.odb");
                ser_name = model.getValueAt(tbl_ip.getSelectedRow(),0).toString();
                Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
                Statement s = null;
                s = conn.createStatement();
                ResultSet rs;
    
                String q = "select IP from SERVERS WHERE SERVER_NAME = \"" +ser_name+"\" ;";
    
                rs = s.executeQuery(q);
    
                while (rs.next()) {
                    host = rs.getString("IP");
                }
            }
            catch(Exception e){
            
            }
            if( host != "")
            {   
                String path = System.getProperty("user.name");
                File f = new File(path+"fuze.odb");
                try{
                    Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
                    Statement s = null;
                    s = conn.createStatement();
                    ResultSet rs;
    
                    String q = "select USERS,PASSWORD from SERVERS WHERE IP = \"" +host+"\" ;";
                    rs = s.executeQuery(q);
    
                    while (rs.next()){
                        user = rs.getString("USERS");
                        passwd = rs.getString("PASSWORD");
                    }
                    if(user.length() != 0 && host.length() != 0 && passwd.length() != 0){
                        jp1 = new Jpanel(user,host,passwd);
                        jTabbedPane1.addTab(ser_name,jp1);// TODO add your handling code here:
                        int i = jTabbedPane1.indexOfComponent(jp1);
                        jTabbedPane1.setSelectedIndex(i);
                    }
                }
                catch(Exception e){
                    System.out.print("Unable to connect.");
                }
            }
            else
            {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel," "+"Please STOP the log.","Server",JOptionPane.INFORMATION_MESSAGE);
            }

        
    }//GEN-LAST:event_pop_connectActionPerformed
    static boolean menuState = true; // whether menu is enable or not
    static boolean isNewSeradded = true; // whether new server is added to the database to update the table 
    static MainGUI frame;

    static Jpanel jp0;
   
    public static void main(String args[]) {
    
        frame = new MainGUI();
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            frame.setVisible(true);
        }
        });
   
        fillCombo();
        intializeConn();         //for checking and setting intial values to the serverConn,,user name and password.
       jp0.out = new JTextAreaOutputStream (jp0.txt_area);    
  
        frame.serverConn();
     
    }


    static void intializeConn(){
        
        String path = System.getProperty("user.name");
        String user = null,host = null,passwd = null;
        File f = new File(path+"fuze.odb");
        try{
            
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
            Statement s = null;
            s = conn.createStatement();
            ResultSet rs;
    
            String q = "select USERS,PASSWORD,IP from SERVERS WHERE SID = 1;";
            rs = s.executeQuery(q);
    
            while (rs.next()) {
                user = rs.getString("USERS");
                passwd = rs.getString("PASSWORD");
                host = rs.getString("IP");
            }

         jp0 = new Jpanel(user,host,passwd);
        }
        catch(Exception e){
            System.out.print("Unable to connect.");
        }
    }

  void serverConn(){
   //Session session;
 //Channel channel;
        try{
            JSch jsch=new JSch();
            jp0.session=jsch.getSession(jp0.user, jp0.host, 22);
 
            UserInfo ui=new MyUserInfo();
            jp0.session.setUserInfo(ui);
            jp0.session.connect();
            String command = "ls";
            jp0.channel=jp0.session.openChannel("exec");
            ((ChannelExec)jp0.channel).setCommand(command);
        
            while(true){
            
                jp0.channel.setOutputStream(System.out);
            }
        }  
        catch(Exception e){
            jp0.session.disconnect();
        }
    }

    static int checkAck(InputStream in) throws IOException{
        int b=in.read();
        if(b==0) return b;
        if(b==-1) return b;
 
        if(b==1 || b==2){
            StringBuffer sb=new StringBuffer();
            int c;
            do{
                c=in.read();
                sb.append((char)c);
            }
            while(c!='\n');
            if(b==1){ // error
    //            System.out.print(sb.toString());
    //            System.out.print(sb.toString());
            }
        }
        return b;
    }
 
  public static class MyUserInfo implements UserInfo, UIKeyboardInteractive{
  public String getPassword(){ return jp0.passwd; }
  public boolean promptYesNo(String str){
        return true;
  }
 JTextField passwordField=(JTextField)new JPasswordField(20);
 
 public String getPassphrase(){ return null; }
 public boolean promptPassphrase(String message){ return true; }
 
 public boolean promptPassword(String message){
      Object[] ob={passwordField}; 
      return true;
 }
 public void showMessage(String message){
      JOptionPane.showMessageDialog(null, message);
 }
 final GridBagConstraints gbc = 
      new GridBagConstraints(0,0,1,1,1,1,
                             GridBagConstraints.NORTHWEST,
                             GridBagConstraints.NONE,
                             new Insets(0,0,0,0),0,0);
  private Container panel;
  public String[] promptKeyboardInteractive(String destination,
                                              String name,
                                              String instruction,
                                              String[] prompt,
                                              boolean[] echo){
      panel = new JPanel();
      panel.setLayout(new GridBagLayout());
 
      gbc.weightx = 1.0;
      gbc.gridwidth = GridBagConstraints.REMAINDER;
      gbc.gridx = 0;
      panel.add(new JLabel(instruction), gbc);
      gbc.gridy++;
 
      gbc.gridwidth = GridBagConstraints.RELATIVE;
 
      JTextField[] texts=new JTextField[prompt.length];
      for(int i=0; i<prompt.length; i++){
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.weightx = 1;
        panel.add(new JLabel(prompt[i]),gbc);
 
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        if(echo[i]){
          texts[i]=new JTextField(20);
        }
        else{
          texts[i]=new JPasswordField(20);
        }
        panel.add(texts[i], gbc);
        gbc.gridy++;
      }
 
      if(JOptionPane.showConfirmDialog(null, panel, 
                                       destination+": "+name,
                                       JOptionPane.OK_CANCEL_OPTION,
                                       JOptionPane.QUESTION_MESSAGE)
         ==JOptionPane.OK_OPTION){
        String[] response=new String[prompt.length];
        for(int i=0; i<prompt.length; i++){
          response[i]=texts[i].getText();
        }
	return response;
      }
      else{
        return null;
      }
    }
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JComboBox combo_grp;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    static javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem menu_addserver;
    private javax.swing.JMenuItem menu_file;
    private javax.swing.JMenuItem menu_grp;
    private javax.swing.JMenuItem pop_addserver;
    private javax.swing.JMenuItem pop_connect;
    private javax.swing.JMenuItem pop_removeserver;
    private javax.swing.JTable tbl_ip;
    // End of variables declaration//GEN-END:variables

    String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}       
