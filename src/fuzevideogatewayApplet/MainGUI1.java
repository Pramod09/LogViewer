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
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
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
public class MainGUI1 extends javax.swing.JFrame {
    private Object fuzevideogateway;
    private Component JFrame;
    static private AddServer addserverfrm;
    static private AddLogFile alfobj;

    /**
     * Creates new form MainGUI
     */
    public MainGUI1() {
        initComponents();
          createDB();
         
     }
  
    static void createDB(){
        String path = System.getProperty("user.name");
        File f = new File(path+"fuze.odb");
        
        if(f.exists() == false){
            try{
              Database db = DatabaseBuilder.create(Database.FileFormat.V2000, f);
   
             // Table newTable = (Table) new TableBuilder("Servers").addColumn(new ColumnBuilder("ID").setSQLType(Types.INTEGER).setAutoNumber(true)).addColumn(new ColumnBuilder("server_name").setSQLType(Types.VARCHAR)).addColumn(new ColumnBuilder("IP").setSQLType(Types.VARCHAR)).addColumn(new ColumnBuilder("users").setSQLType(Types.VARCHAR)).addColumn(new ColumnBuilder("paaword").setSQLType(Types.VARCHAR)).toTable(db);
            //Creating Servers table
              
            //   newTable = (Table) new TableBuilder("Logfiles").addColumn(new ColumnBuilder("ID").setSQLType(Types.INTEGER).setAutoNumber(true)).addColumn(new ColumnBuilder("logfilename").setSQLType(Types.VARCHAR)).addColumn(new ColumnBuilder("path").setSQLType(Types.VARCHAR)).addColumn(new ColumnBuilder("server_id"));
       
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
                                " SERVER_NAME VARCHAR(25), " + 
                                " IP VARCHAR(25), " + 
                                " USERS VARCHAR(25), " + 
                                " PASSWORD VARCHAR(25), " +
                                " GROUPID INTEGER, "+
                                " PRIMARY KEY ( IP ))"; 

                String table2 = "CREATE TABLE LOGFILES " +
                                "(LID INTEGER NOT NULL," +
                                "LOGFILENAME VARCHAR(25)," +
                                "PATH VARCHAR(25)," +
                                "SID INTEGER not NULL)";
                
                String table3 = "CREATE TABLE LOGIN" +
                                "(ID INTEGER NOT NULL, " +
                                "USERNAME VARCHAR(25), " +
                                "PASSWORD VARCHAR(25), " +
                                "PRIMARY KEY (ID) "+
                                ")";
                
                String table4 = "CREATE TABLE GROUPS " +
                                "(GID INTEGER NOT NULL," +
                                "GROUP_NAME VARCHAR(25)," +
                                "PRIMARY KEY (GROUP_NAME) "+
                                ")";
     
                try{
                s.executeUpdate(table1);
                s.executeUpdate(table2);
                s.executeUpdate(table3);    
                s.executeUpdate(table4);
                
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Databse created.","Database",JOptionPane.INFORMATION_MESSAGE);
            //    IntialFrom ifrm = new IntialFrom();
           //     ifrm.setVisible(true);
                
                
                
                
                conn.close();
    // intializeConn();         //for checking and setting intial values to the serverConn,,user name and password.
  // serverConn();
            
                }
                catch(Exception e){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Problem with database Creation.","Database",JOptionPane.INFORMATION_MESSAGE);
                        
                }
               //  String group = JOptionPane.showInputDialog(frame, "Enter the group name : ","Group",JOptionPane.INFORMATION_MESSAGE);
         
 //       String path = System.getProperty("user.name");
 //       File f = new File(path+"fuze.odb");
     //     try{
            //    Connection 
                /*        conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
          //      Statement 
                        s = null;
                s = conn.createStatement();
     
                int i = 1;
                
               group = group.trim();
                if(group.isEmpty() == false)
                {
                      String q1 ="insert into GROUPS values("+ ++i +",\""+group+"\");";
                       try{ 
                            s.execute(q1);
                     final JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel,group+" "+"Group added successfully.","Group",JOptionPane.INFORMATION_MESSAGE);
                            frame.combo_grp.addItem(group);
                            //         MainGUI.isNewSeradded = true;
                       }
                       catch(Exception e){
                             final JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel,group+" "+"Problem with group Creation.","Group",JOptionPane.INFORMATION_MESSAGE);
                       }
                        
                 }
                 addserverfrm = new AddServer();
                 addserverfrm.setVisible(true);
              */
      
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
                
                //System.out.println("query exe");
                while (rs.next()) {
           
                     frame.combo_grp.addItem(rs.getString("GROUP_NAME"));
                }
        
        
        }  catch(Exception e){
            System.out.print("error "+e);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_area = new javax.swing.JTextArea();
        btn_start_stop = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ip = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_show = new javax.swing.JButton();
        combo_log = new javax.swing.JComboBox();
        lbl_logfile = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();
        txt_date = new javax.swing.JTextField();
        txt_from_time = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_to_time = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        txt_down = new javax.swing.JButton();
        lbl_msg = new javax.swing.JLabel();
        combo_grp = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        menu_new = new javax.swing.JMenuItem();
        menu_open = new javax.swing.JMenuItem();
        menu_save = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menu_server = new javax.swing.JMenuItem();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("MainGUI"); // NOI18N
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

        txt_area.setColumns(20);
        txt_area.setRows(5);
        jScrollPane1.setViewportView(txt_area);

        btn_start_stop.setText("START");
        btn_start_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_start_stopActionPerformed(evt);
            }
        });

        tbl_ip.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Server name", "IP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        });
        jScrollPane2.setViewportView(tbl_ip);

        btn_show.setText("Show");
        btn_show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_showActionPerformed(evt);
            }
        });

        combo_log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_logActionPerformed(evt);
            }
        });

        lbl_logfile.setText("Show File");
        lbl_logfile.setName("lbl_log"); // NOI18N

        lbl_date.setText("Date");
        lbl_date.setName("lbl_date"); // NOI18N

        lbl_time.setText("Time ");
        lbl_time.setName("lbl_time"); // NOI18N

        txt_date.setText("YYYY-MM-DD");
        txt_date.setName("txt_date"); // NOI18N
        txt_date.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_dateMouseClicked(evt);
            }
        });

        txt_from_time.setName("txt_time_from"); // NOI18N

        jLabel4.setText("To");
        jLabel4.setName("lbl_to"); // NOI18N
        jLabel4.setOpaque(true);

        txt_to_time.setToolTipText("");
        txt_to_time.setName("txt_to"); // NOI18N

        jLabel1.setText("From");

        txt_search.setText("Search text");
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_searchMouseClicked(evt);
            }
        });
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
        });

        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        txt_down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_downActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbl_logfile, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(combo_log, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbl_date)
                        .addGap(68, 68, 68)
                        .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(386, 386, 386)
                        .addComponent(lbl_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbl_time)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(txt_from_time, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)
                        .addComponent(txt_to_time, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btn_show, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_down, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lbl_logfile))
                    .addComponent(combo_log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbl_date))
                    .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lbl_time))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_from_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_to_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_show)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_down, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(lbl_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        jMenu4.setText("File");

        menu_new.setText("New");
        menu_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_newActionPerformed(evt);
            }
        });
        jMenu4.add(menu_new);

        menu_open.setText("Open");
        menu_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_openActionPerformed(evt);
            }
        });
        jMenu4.add(menu_open);

        menu_save.setText("Save");
        menu_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_saveActionPerformed(evt);
            }
        });
        jMenu4.add(menu_save);

        jMenuItem6.setText("jMenuItem6");
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        jMenu1.setText("Add");

        menu_server.setText("Server");
        menu_server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_serverActionPerformed(evt);
            }
        });
        jMenu1.add(menu_server);

        menu_file.setText("File");
        menu_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_fileActionPerformed(evt);
            }
        });
        jMenu1.add(menu_file);

        menu_grp.setText("Group");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(btn_start_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(combo_grp, 0, 245, Short.MAX_VALUE))
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(56, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(combo_grp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btn_start_stop)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_serverActionPerformed
        // TODO add your handling code here:
    addserverfrm = new AddServer();
    addserverfrm.setVisible(true);
//    addserverfrm.setDefaultCloseOperation(addserverfrm.EXIT_ON_CLOSE);
   
    }//GEN-LAST:event_menu_serverActionPerformed
        
        
    private void menu_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_fileActionPerformed

        alfobj = new AddLogFile();
        alfobj.setVisible(true);
        //alfobj.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
    }//GEN-LAST:event_menu_fileActionPerformed
    int begSearch = 0;   // for start a search from beggining of the text area
    int endSearch = 0;  //  for starting a search from the end of the text area
        
    private void menu_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_openActionPerformed
     
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Log Files", "txt","log","txt.bz2");
        chooser.addChoosableFileFilter(filter);
        Component parent = null;
       
        String absfilepath = new File("").getAbsolutePath();
     
        int returnVal = chooser.showOpenDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
               absfilepath =  chooser.getSelectedFile().getAbsolutePath();
              
             try{
                txt_area.setText("");
                InputStream ips=new FileInputStream(absfilepath); 
                InputStreamReader ipsr=new InputStreamReader(ips);
                BufferedReader br;
                br = new BufferedReader(ipsr);
                String line = null;
                while((line = (br.readLine()))!= null){
            //       String a = txt_area.getText();
          //         txt_area.setText(a+line+"\n");
                   
                   txt_area.append(line+"\n");
                }
                br.close(); 
            }       
            catch (Exception e){
                System.out.println(e.toString());
            }
        } 
        else{
            chooser.cancelSelection();
        } 
  
    }//GEN-LAST:event_menu_openActionPerformed
    private void menu_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_saveActionPerformed
        
        int answer;
        JFileChooser fileChooser;
  /*   answer = JOptionPane.showConfirmDialog(null,
                "Do you really want to save the your name?",
                "Save", JOptionPane.YES_NO_OPTION);

            if(answer == JOptionPane.YES_OPTION)*/
        {
                fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(null);
                OutputStream out = null;

                try{
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    out = new FileOutputStream(file);
                    for(byte b : txt_area.getText().getBytes())
                        out.write(b);
                }
                catch(IOException e1){
                    e1.printStackTrace();
                }
                catch(Exception e){}
                finally{
                    if(out != null){
                        try{
                            out.close();
                            out = null;
                        }
                        catch(IOException e2){
                            e2.printStackTrace();
                        }
                    }       
                }
            }
        
    }//GEN-LAST:event_menu_saveActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    
        this.setExtendedState(MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowActivated

    static boolean isCellSelected = false;
    static String user=null;
    static String passwd  = null;
    static String host= null;
    static String file = null,loc = null;
 
    private void btn_start_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_start_stopActionPerformed
        // TODO add your handling code here:
    
        if(menuState && tbl_ip.isRowSelected(0) == false && isCellSelected ){
                menu_open.setEnabled(false);
                menu_save.setEnabled(false);
                menuState = false;
                btn_start_stop.setText("STOP");
                
                ////////////////////////////////////////////////
                
                String path = System.getProperty("user.name");
                File f = new File(path+"fuze.odb");
     try{
        Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
        Statement s = null;
        s = conn.createStatement();
        ResultSet rs;
    //String q = "select USERS,PASSWORD from SERVERS WHERE IP = \"+host+\";";
        
    
        String q = "select SID from SERVERS WHERE IP = \"" +host+"\" ;";
    
 //   System.out.printf(q+"\n\n\n");
        rs = s.executeQuery(q);
        int sid = 0;
        while (rs.next()) {
            sid = rs.getInt("SID");
     }
        
        q = "SELECT PATH FROM LOGFILES WHERE SID = "+sid+";";
        rs = s.executeQuery(q);
        file = combo_log.getSelectedItem().toString();
        while(rs.next()){
           // file = rs.getString("LOGFILENAME");
            loc = rs.getString("PATH");
        }
        
        
        System.out.print("sid : "+sid+"file : : "+file+"\t loc : "+loc);
     }
     catch(Exception e){
         System.out.print("Unable to connect.");
     }
     
     ////////////////////////////////////////////////
                
               
                  try {
                    
                        JSch jsch=new JSch();
                        //Session 
                        session=jsch.getSession(user, host, 22);
                        
                        // username and password will be given via UserInfo interface.
                        UserInfo ui=new MyUserInfo();
                        session.setUserInfo(ui);
                        session.connect();
                        String outs = null;
                        new PrintStream(outs);
                        System.setOut (new PrintStream (outs));
                        // exec 'scp -f rfile' remotely
                 //      String command1 = "tail -f /opt/WMP/log/VideoGateway.log";//"scp -f "+rfile;
                        
                        String command = "tail -f "+loc+file;    //"scp -f "+rfile;
                        //String command = "cat "+loc+file;    //"scp -f "+rfile;
                      System.out.print(outs);
                     // System.out.print(command1+command2);
                      //    Channel
                        channel=session.openChannel("exec");
                        //Channel channel=session.openChannel("exec");
                        ((ChannelExec)channel).setCommand(command);
                        if(!channel.isConnected()){
                            channel.connect();
                        }
                        frame.repaint();
                }
                catch(JSchException j){
                        final JPanel panel = new JPanel();
                        JOptionPane.showMessageDialog(panel, "Connect time out.","Connection",JOptionPane.ERROR_MESSAGE);
                        session.disconnect();
                        System.exit(0);
                }
                catch (Exception ex) {
                        Logger.getLogger(MainGUI1.class.getName()).log(Level.SEVERE, null, ex);
                         final JPanel panel = new JPanel();
                         JOptionPane.showMessageDialog(panel, "Unable to connect.","Connection",JOptionPane.INFORMATION_MESSAGE);
                        session.disconnect();
                }
                frame.repaint();
        }
        else{
                menu_open.setEnabled(true);
                menu_save.setEnabled(true);
                menuState = true;
                btn_start_stop.setText("START");
                
                try {
                    if(channel.isConnected()){
                        channel.disconnect();
                    }
                } 
                catch (Exception ex) {
                    Logger.getLogger(MainGUI1.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    }//GEN-LAST:event_btn_start_stopActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_formWindowStateChanged

    private void tbl_ipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ipMouseClicked
    
        combo_log.removeAllItems();
 
    DefaultTableModel model;
    model = (DefaultTableModel)tbl_ip.getModel();
     

    //if((tbl_ip.getSelectedRow(),1).toString()){
    try{
    host = model.getValueAt(tbl_ip.getSelectedRow(),1).toString();
    }
    catch(Exception e){}
    
     if(menuState && host != ""){   
     String path = System.getProperty("user.name");
     File f = new File(path+"fuze.odb");
     try{
    Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
    Statement s = null;
    s = conn.createStatement();
    ResultSet rs;
    //String q = "select USERS,PASSWORD from SERVERS WHERE IP = \"+host+\";";
    
    String q = "select USERS,PASSWORD from SERVERS WHERE IP = \"" +host+"\" ;";
    
 //   System.out.printf(q+"\n\n\n");
    rs = s.executeQuery(q);
    
     while (rs.next()) {
        user = rs.getString("USERS");
        passwd = rs.getString("PASSWORD");
     }
     txt_area.setText("");
     isCellSelected = true;
     
     
     q = "select SID from SERVERS WHERE IP = \"" +host+"\" ;";
    
        rs = s.executeQuery(q);
        int sid = 0;
        while (rs.next()) {
            sid = rs.getInt("SID");
        }
         q = "SELECT LOGFILENAME FROM LOGFILES WHERE SID = "+sid+";";
        rs = s.executeQuery(q);
               while (rs.next()) {
           
                    frame.combo_log.addItem(rs.getString("LOGFILENAME"));
                    
                }
     
     }
     catch(Exception e){
         System.out.print("Unable to connect.");
     }
     }
     //}
     else
     {
        final JPanel panel = new JPanel();
        JOptionPane.showMessageDialog(panel," "+"Please STOP the log.","Server",JOptionPane.INFORMATION_MESSAGE);
     }
//host = String.valueOf(model.getValueAt(tbl, begSearch));
     
     
     //////////////////
     
      
     try{
        
                }
               catch(Exception e){
            System.out.printf("\n\t Exception in table "+e);
        }
            
            
     ///////////////////////
     
     
     
     
      
    }//GEN-LAST:event_tbl_ipMouseClicked

    private void tbl_ipMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ipMousePressed
        // TODO add your handling code here:
        
         
        
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
                while(rs.next()){++i;}
                
               group.trim();
                
                
                if(group.isEmpty() == false)
                {
                      String q1 ="insert into GROUPS values("+ ++i +",\""+group+"\");";
                       try{ 
                            s.execute(q1);
                            final JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel,group+" "+"Group added successfully.","Group",JOptionPane.INFORMATION_MESSAGE);
                            frame.combo_grp.addItem(group);
                            //         MainGUI.isNewSeradded = true;
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
                System.out.print("error "+e);
        }
   
        ////////////////////////////////////////
        
        /*
    System.out.print("action performed ,,,,");
        frame.combo_grp.removeAllItems();
    
//        String path = System.getProperty("user.name");
  //      File f = new File(path+"fuze.odb");
        try{
                Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
                Statement s = null;
                s = conn.createStatement();
     
                ResultSet rs;
                rs = s.executeQuery("select * from GROUPS"); 
                
                //System.out.println("query exe");
                while (rs.next()) {
           
                     frame.combo_grp.addItem(rs.getString("GROUP_NAME"));
                }
        
        
        }  catch(Exception e){
            System.out.print("error "+e);
        }       
           
        
        //////////////////////////////////////////

        */
        
    }//GEN-LAST:event_menu_grpActionPerformed

    private void combo_grpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_grpMouseClicked
 
  
              
  
        
    }//GEN-LAST:event_combo_grpMouseClicked

    private void combo_grpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_grpActionPerformed
      sele = combo_grp.getSelectedItem().toString();
      System.out.print(sele);
        
          //    if(isNewSeradded){
            DefaultTableModel model;
           String path = System.getProperty("user.name");
            File f = new File(path+"fuze.odb");
             model = (DefaultTableModel)tbl_ip.getModel();
            String sn = null,ip = null;
            model.setRowCount(1);
            try{
                Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
                Statement s = null;
                s = conn.createStatement();
                ResultSet rs;
      //          String sele = combo_grp.getSelectedItem().toString();
              //  int sele  = combo_grp.getSelectedIndex();
                System.out.print("Item selected ..: "+sele);
                
                //rs = s.executeQuery("select * from SERVERS where GROUPID ="+sele);
               rs = s.executeQuery("select * from SERVERS where GROUPID =(select GID from GROUPS WHERE GROUP_NAME=\""+sele+"\");");
           System.out.print("qa exe");
                while (rs.next()) {
           
                    //System.out.print(rs.getString(0));
                   // System.out.print(rs.getString("SID")+"\t ");
                    sn = rs.getString("SERVER_NAME");
                    ip = rs.getString("IP");
                //    System.out.print(rs.getString("USERS")+"\t");
                  //  System.out.println(rs.getString("PASSWORD")+"\t");
               
                Object[] data = {sn,ip};
                model.insertRow(1,data);
                }
        }
        catch(Exception e){
            System.out.printf("\n\t Exception in table "+e);
        }
            combo_log.removeAllItems();
            ///////////////////////////////////////////////////
            
            
            
            
            
            
            
            
            /////////////////////////////////////////////////
            
            
            
    
       
     //   isNewSeradded = false;
       // }         // TODO add your handling code here:

        
    }//GEN-LAST:event_combo_grpActionPerformed

    private void combo_grpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_grpMouseEntered
        
    }//GEN-LAST:event_combo_grpMouseEntered

    private void combo_grpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_grpMousePressed
          
       
    }//GEN-LAST:event_combo_grpMousePressed

    private void combo_grpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combo_grpFocusLost

      
    }//GEN-LAST:event_combo_grpFocusLost

    private void combo_grpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combo_grpFocusGained
 /*    System.out.print("action performed ,,,,");
   */           
         

    }//GEN-LAST:event_combo_grpFocusGained
static String sele = null;
    private void combo_grpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_grpMouseExited
  
      
    }//GEN-LAST:event_combo_grpMouseExited

    private void menu_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_newActionPerformed
txt_area.setText("");
        
    }//GEN-LAST:event_menu_newActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

       
        
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void txt_downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_downActionPerformed
        // TODO add your handling code here:
        String turnToString2 = txt_search.getText();
        try {

            if(endSearch != -1){
                txt_area.setCaretPosition(endSearch);
                endSearch = txt_area.getText().lastIndexOf(turnToString2,(endSearch-1));
                lbl_msg.setText("");
            }
            if(endSearch != -1){
                txt_area.getHighlighter().addHighlight(endSearch,endSearch + turnToString2.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.green));
            }
            else if (endSearch <= txt_area.getText().length() && endSearch >= 0){
                txt_area.getHighlighter().addHighlight(endSearch,endSearch + turnToString2.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.green));
            }
            if(endSearch == -1){
                lbl_msg.setText("Can not find.");
            }
            //    System.out.print(" "+endSearch);
        }
        catch (BadLocationException ex) {
            endSearch = -1;
            //    System.out.printf("error");
            Logger.getLogger(MainGUI1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txt_downActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        String turnToString2 = txt_search.getText();

        try {

            if(begSearch != -1){
                txt_area.setCaretPosition(begSearch);
                begSearch = txt_area.getText().indexOf(turnToString2,begSearch);
                lbl_msg.setText("");
            }
            if(begSearch != -1){
                txt_area.getHighlighter().addHighlight(begSearch,begSearch + turnToString2.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
            }
            else if (begSearch <= txt_area.getText().length() && begSearch >= 0){
                txt_area.getHighlighter().addHighlight(begSearch,begSearch + turnToString2.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
            }
            if(begSearch == -1){
                lbl_msg.setText("Can not Found.");
            }
            begSearch = begSearch + 1;

            //System.out.print(" "+begSearch);

        }
        catch (BadLocationException ex) {
            begSearch = -1;
            //    System.out.printf("error");
            Logger.getLogger(MainGUI1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_searchActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        begSearch = 0;
        endSearch = txt_area.getText().length();
        txt_area.getHighlighter().removeAllHighlights();
    }//GEN-LAST:event_txt_searchKeyPressed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
        begSearch = 0;
        endSearch = txt_area.getText().length();

    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseClicked
        begSearch = 0;
        endSearch = txt_area.getText().length();
        //txt_search.setText("");
    }//GEN-LAST:event_txt_searchMouseClicked

    private void txt_dateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_dateMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_dateMouseClicked

    private void combo_logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_logActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_logActionPerformed

    private void btn_showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_showActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_showActionPerformed
static boolean menuState = true; // whether menu is enable or not
static boolean isNewSeradded = true; // whether new server is added to the database to update the table 
static MainGUI1 frame;

static Session session;
static Channel channel;
static  JTextAreaOutputStream out;
    /**
     * @param args the command line arguments
     */
public static void main(String args[]) {
       // MainGUI 
    frame = new MainGUI1 ();
      
     //frame.setExtendedState(frame.MAXIMIZED_BOTH);
    java.awt.EventQueue.invokeLater(new Runnable() {
   public void run() {
       frame.setVisible(true);
       // serverConn();
   }
    });
   
    
  ////////////////////////////////////////////////
    fillCombo();
  //   frame.combo_grp.removeAllItems();
    ///////////////////////////////////////////
   frame.txt_area.setEditable (false);
   out = new JTextAreaOutputStream (frame.txt_area);
   
   intializeConn();         //for checking and setting intial values to the serverConn,,user name and password.
  serverConn();
 
          
}

static void intializeConn()
{
     String path = System.getProperty("user.name");
     File f = new File(path+"fuze.odb");
     try{
        Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
        Statement s = null;
        s = conn.createStatement();
        ResultSet rs;
    
        String q = "select USERS,PASSWORD,IP from SERVERS WHERE SID = 1;";
        rs = s.executeQuery(q);
  //     System.out.print("exec    user name pass host");
    
        while (rs.next()) {
            user = rs.getString("USERS");
            passwd = rs.getString("PASSWORD");
            host = rs.getString("IP");
           
        }
      System.out.print("ID : 1  - > user "+user+" name "+passwd+"pass host "+host);
     }
     catch(Exception e){
         System.out.print("Unable to connect.");
         
     }
}


static void serverConn(){
   
    try{
 
      JSch jsch=new JSch();
      //Session 
      session=jsch.getSession(user, host, 22);
 
      // username and password will be given via UserInfo interface.
       UserInfo ui=new MyUserInfo();
       session.setUserInfo(ui);
       session.connect();
       System.setOut (new PrintStream (out));
      // exec 'scp -f rfile' remotely
      // String command = "tail -f /opt/WMP/log/VideoGateway.log";//"scp -f "+rfile;
        //    Channel
        String command = "ls";//"scp -f "+rfile;
       channel=session.openChannel("exec");
      //Channel channel=session.openChannel("exec");
      ((ChannelExec)channel).setCommand(command);
        
       while(true){
          channel.setOutputStream(out);
      }
   }  
   catch(Exception e){
     
 //   final JPanel panel = new JPanel();
  //  JOptionPane.showMessageDialog(panel, "Unable to connect.","Connection",JOptionPane.INFORMATION_MESSAGE);
  session.disconnect();
    }
  }

static int checkAck(InputStream in) throws IOException{
    int b=in.read();
    // b may be 0 for success,
    //          1 for error,
    //          2 for fatal error,
    //          -1
    if(b==0) return b;
    if(b==-1) return b;
 
    if(b==1 || b==2){
      StringBuffer sb=new StringBuffer();
      int c;
      do {
	c=in.read();
	sb.append((char)c);
      }
      while(c!='\n');
      if(b==1){ // error
	System.out.print(sb.toString());
	System.out.print(sb.toString());
      }
    }
    return b;
  }
 
  public static class MyUserInfo implements UserInfo, UIKeyboardInteractive{
  public String getPassword(){ return passwd; }
  public boolean promptYesNo(String str){
     // Object[] options={ "yes", "no" };
   //   int foo=JOptionPane.showOptionDialog(null,str,"Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null, options, options[0]);
     //  return foo==0;
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
        return null;  // cancel
      }
    }
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_show;
    private javax.swing.JButton btn_start_stop;
    javax.swing.JComboBox combo_grp;
    private javax.swing.JComboBox combo_log;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_logfile;
    private javax.swing.JLabel lbl_msg;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JMenuItem menu_file;
    private javax.swing.JMenuItem menu_grp;
    private javax.swing.JMenuItem menu_new;
    private javax.swing.JMenuItem menu_open;
    private javax.swing.JMenuItem menu_save;
    private javax.swing.JMenuItem menu_server;
    private javax.swing.JTable tbl_ip;
    private javax.swing.JTextArea txt_area;
    private javax.swing.JTextField txt_date;
    private javax.swing.JButton txt_down;
    private javax.swing.JTextField txt_from_time;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_to_time;
    // End of variables declaration//GEN-END:variables

    String string;

    /**
     * Get the value of string
     *
     * @return the value of string
     */
    public String getString() {
        return string;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setString(String string) {
        this.string = string;
    }
}       
