/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzevideogatewayApplet;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *
 * @author gs-1062
 */
public class Jpanel extends javax.swing.JPanel {

static Session session;
static Channel channel;
//   Session session;
// Channel channel;
JTextAreaOutputStream out;
boolean isStart = false;
    
    String host,user,passwd;
    public Jpanel(String user,String host,String passwd) {
        initComponents();
        this.host = host;
        this.user = user;
        this.passwd = passwd;
            
        try{
            
            JSch jsch=new JSch();
            session=jsch.getSession(user,host, 22);
            UserInfo ui=new MyUserInfo();
            
            out = new JTextAreaOutputStream(this.txt_area);
            System.setOut (new PrintStream (out));
            
            session.setUserInfo(ui);
            session.connect();
            channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand("date");
            fillComboLog();
        }
        catch(Exception er){
           System.out.print("excep");
        }
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grp_file_toshow = new javax.swing.ButtonGroup();
        pop_options = new javax.swing.JPopupMenu();
        pop_txtarea_clear = new javax.swing.JMenuItem();
        pop_txtarea_save = new javax.swing.JMenuItem();
        pop_txtarea_open = new javax.swing.JMenuItem();
        pop_close = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_area = new javax.swing.JTextArea();
        btn_start_stop = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        combo_log = new javax.swing.JComboBox();
        txt_user_command = new javax.swing.JTextField();
        radio_cat = new javax.swing.JRadioButton();
        radio_tail = new javax.swing.JRadioButton();
        lbl_search_res = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        pop_txtarea_clear.setText("Clear");
        pop_txtarea_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pop_txtarea_clearActionPerformed(evt);
            }
        });
        pop_options.add(pop_txtarea_clear);

        pop_txtarea_save.setText("Save");
        pop_txtarea_save.setToolTipText("");
        pop_txtarea_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pop_txtarea_saveActionPerformed(evt);
            }
        });
        pop_options.add(pop_txtarea_save);

        pop_txtarea_open.setText("open");
        pop_txtarea_open.setToolTipText("");
        pop_txtarea_open.setActionCommand("Open");
        pop_txtarea_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pop_txtarea_openActionPerformed(evt);
            }
        });
        pop_options.add(pop_txtarea_open);
        pop_options.add(pop_close);

        jMenuItem4.setText("close");
        jMenuItem4.setActionCommand("Close");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        pop_options.add(jMenuItem4);

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        txt_area.setColumns(20);
        txt_area.setRows(5);
        txt_area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_areaMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txt_area);

        btn_start_stop.setText("Start");
        btn_start_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_start_stopActionPerformed(evt);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_searchKeyTyped(evt);
            }
        });

        combo_log.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_logMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                combo_logMousePressed(evt);
            }
        });
        combo_log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_logActionPerformed(evt);
            }
        });

        txt_user_command.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_user_commandMouseClicked(evt);
            }
        });
        txt_user_command.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_user_commandKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_user_commandKeyReleased(evt);
            }
        });

        grp_file_toshow.add(radio_cat);
        radio_cat.setText("cat");
        radio_cat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio_catMouseClicked(evt);
            }
        });
        radio_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_catActionPerformed(evt);
            }
        });

        grp_file_toshow.add(radio_tail);
        radio_tail.setText("tail -f");
        radio_tail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio_tailMouseClicked(evt);
            }
        });
        radio_tail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_tailActionPerformed(evt);
            }
        });

        lbl_search_res.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbl_search_resMouseReleased(evt);
            }
        });

        jButton1.setText(">");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("<");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(combo_log, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_user_command, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_search_res, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radio_cat)
                        .addGap(36, 36, 36)
                        .addComponent(radio_tail)
                        .addGap(52, 52, 52)
                        .addComponent(btn_start_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(combo_log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_user_command, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addComponent(lbl_search_res, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_start_stop)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radio_cat)
                        .addComponent(radio_tail))))
        );
    }// </editor-fold>//GEN-END:initComponents


    
    private void btn_start_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_start_stopActionPerformed
      
    String file = null,loc = null;
    if(!isStart){
     
        btn_start_stop.setText("STOP");
        String path = System.getProperty("user.name");
        File f = new File(path+"fuze.odb");
  
        try{
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
            Statement s = null;
            s = conn.createStatement();
            ResultSet rs;
    
            String q = "select SID from SERVERS WHERE IP = \"" +host+"\" ;";
 
            rs = s.executeQuery(q);
            int sid = 0;
            while (rs.next()) {
                sid = rs.getInt("SID");
            }
        
            q = "SELECT PATH FROM LOGFILES WHERE SID = "+sid+";";
            rs = s.executeQuery(q);
            file = combo_log.getSelectedItem().toString();
            while(rs.next()){
                loc = rs.getString("PATH");
            }
        }
        catch(Exception e){
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "OOps path not found. "+e,"Connection",JOptionPane.ERROR_MESSAGE);
        }
        try {
            
            JSch jsch=new JSch();
            session=jsch.getSession(user, host, 22);
            UserInfo ui=new MyUserInfo();
            session.setUserInfo(ui);
            session.connect();
            out = new JTextAreaOutputStream(txt_area);
            System.setOut (new PrintStream (out));
            String seleusercmd = null,command = null;

    //        if(txt_user_command.getText().trim().isEmpty()){
            if(true){
                if(radio_tail.isSelected() == false && radio_cat.isSelected() == false){
                
                    radio_tail.setSelected(true);
                   
                }
                if(radio_tail.isSelected()){
                    seleusercmd = "tail -f ";
                    radio_tail.setSelected(isStart);
                }
                else{
                    seleusercmd = "cat ";
                    radio_cat.setSelected(isStart);
                } 
                command = seleusercmd+loc+file;
            }
            else{
                seleusercmd = txt_user_command.getText().trim();
                command = seleusercmd;
                radio_tail.setSelected(false);
                radio_cat.setSelected(false);
                txt_area.setText("");
            }
            channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            if(!channel.isConnected()){
                channel.connect();
            }
            txt_user_command.setEnabled(false);
            
            isStart = true;
                     
        }
        catch(JSchException j){
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Connect time out. "+j,"Connection",JOptionPane.ERROR_MESSAGE);
           session.disconnect();
            System.exit(0);
        }
        catch (Exception ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Unable to connect. "+ex,"Connection",JOptionPane.INFORMATION_MESSAGE);
            session.disconnect();
        }
    }
    else{
        btn_start_stop.setText("START");
        txt_user_command.setEnabled(true);
        try{          
            if(channel.isConnected()){
                channel.disconnect();
                isStart = false;
                radio_tail.setSelected(false);
                radio_cat.setSelected(false);
                
            }
        } 
        catch (Exception ex){
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }//GEN-LAST:event_btn_start_stopActionPerformed
    
    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
      //  begSearch = 0;
        endSearch = txt_area.getText().length();
        txt_area.getHighlighter().removeAllHighlights();        
    }//GEN-LAST:event_txt_searchKeyPressed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
      //  begSearch = 0;
        endSearch = txt_area.getText().length();
        txt_area.getHighlighter().removeAllHighlights();        
    }//GEN-LAST:event_txt_searchActionPerformed

    
    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        String turnToString2 = txt_search.getText();

        try {

            if(begSearch != -1){
                txt_area.setCaretPosition(begSearch);
                begSearch = txt_area.getText().indexOf(turnToString2,begSearch);
                lbl_search_res.setText("");
            }
            if(begSearch != -1){
                txt_area.getHighlighter().addHighlight(begSearch,begSearch + turnToString2.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
            }
            else if (begSearch <= txt_area.getText().length() && begSearch >= 0){
                txt_area.getHighlighter().addHighlight(begSearch,begSearch + turnToString2.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
            }
            if(begSearch == -1){
                lbl_search_res.setText("Can not found.");
            }
            txt_area.setCaretPosition(begSearch);                 
            //System.out.print(" "+begSearch);

        }
        catch (BadLocationException ex) {
            begSearch = -1;
        }
             
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            begSearch = begSearch + 1;
        }
        
        
        
    }//GEN-LAST:event_txt_searchKeyReleased

    private void combo_logMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_logMouseClicked
        
    }//GEN-LAST:event_combo_logMouseClicked

    private void combo_logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_logActionPerformed

    }//GEN-LAST:event_combo_logActionPerformed

    void fillComboLog(){
        combo_log.removeAllItems();
        DefaultTableModel model;
        if(host != ""){   
            
            String path = System.getProperty("user.name");
            File f = new File(path+"fuze.odb");
            
            try{
                Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
                Statement s = null;
                s = conn.createStatement();
                ResultSet rs;
    
               String q = "select USERS,PASSWORD from SERVERS WHERE IP = \"" +host+"\" ;";
               rs = s.executeQuery(q);
    
                while (rs.next()) {
                    user = rs.getString("USERS");
                    passwd = rs.getString("PASSWORD");
                }
                txt_area.setText("");
     
                q = "select SID from SERVERS WHERE IP = \"" +host+"\" ;";
    
                rs = s.executeQuery(q);
                int sid = 0;
                while (rs.next()) {
                    sid = rs.getInt("SID");
                }
                q = "SELECT LOGFILENAME FROM LOGFILES WHERE SID = "+sid+";";
                rs = s.executeQuery(q);
                while (rs.next()) {
                    combo_log.addItem(rs.getString("LOGFILENAME"));
                }
            }
            catch(Exception e){
            }
        }
        else{
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel," "+"Please STOP the log.","Server",JOptionPane.INFORMATION_MESSAGE);
        }    
    }
    
    private void combo_logMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_logMousePressed
        fillComboLog();
    }//GEN-LAST:event_combo_logMousePressed

    private void radio_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_catActionPerformed

    }//GEN-LAST:event_radio_catActionPerformed

    private void radio_tailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_tailActionPerformed
        
    }//GEN-LAST:event_radio_tailActionPerformed

    private void radio_catMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio_catMouseClicked
        txt_area.setText("");
    }//GEN-LAST:event_radio_catMouseClicked

    private void radio_tailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio_tailMouseClicked
        txt_area.setText("");
    }//GEN-LAST:event_radio_tailMouseClicked

    private void txt_user_commandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_user_commandMouseClicked
        radio_cat.setSelected(false);
        radio_tail.setSelected(false);
        
    }//GEN-LAST:event_txt_user_commandMouseClicked

    private void pop_txtarea_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pop_txtarea_clearActionPerformed
        this.txt_area.setText("");
    }//GEN-LAST:event_pop_txtarea_clearActionPerformed

    private void txt_areaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_areaMouseReleased

    }//GEN-LAST:event_txt_areaMouseReleased

    private void lbl_search_resMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_search_resMouseReleased
    
    }//GEN-LAST:event_lbl_search_resMouseReleased

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased

        if(evt.isPopupTrigger()){
            pop_options.show(this,evt.getX(),evt.getY()+25);
        }
        
    }//GEN-LAST:event_formMouseReleased
static String defaultPath  = "";
    private void pop_txtarea_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pop_txtarea_saveActionPerformed
        int answer;
        JFileChooser fileChooser;
        {
            fileChooser = new JFileChooser(defaultPath);
            fileChooser.showSaveDialog(null);
            OutputStream out = null;
            try{
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                defaultPath = fileChooser.getSelectedFile().getAbsolutePath();
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
    }//GEN-LAST:event_pop_txtarea_saveActionPerformed

    private void pop_txtarea_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pop_txtarea_openActionPerformed

        JFileChooser chooser = new JFileChooser(defaultPath);
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
                    String a = txt_area.getText();
                    txt_area.setText(a+line+"\n");
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

    }//GEN-LAST:event_pop_txtarea_openActionPerformed
    int begSearch = 0;   // for start a search from beggining of the text area
    int endSearch = 0;  //  for starting a search from the end of the text area
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String turnToString2 = txt_search.getText();

        try {

            if(begSearch != -1){
                txt_area.setCaretPosition(begSearch);
                begSearch = txt_area.getText().indexOf(turnToString2,begSearch);
                lbl_search_res.setText("");
            }
            if(begSearch != -1){
                txt_area.getHighlighter().addHighlight(begSearch,begSearch + turnToString2.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
            }
            else if (begSearch <= txt_area.getText().length() && begSearch >= 0){
                txt_area.getHighlighter().addHighlight(begSearch,begSearch + turnToString2.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
            }
            if(begSearch == -1){
                lbl_search_res.setText("Can not found.");
            }
            begSearch = begSearch + 1;

            //System.out.print(" "+begSearch);

        }
        catch (BadLocationException ex) {
            begSearch = -1;
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

          String turnToString2 = txt_search.getText();
        try {

            if(endSearch != -1){
                txt_area.setCaretPosition(endSearch);
                endSearch = txt_area.getText().lastIndexOf(turnToString2,(endSearch-1));
                lbl_search_res.setText("");
            }
            if(endSearch != -1){
                txt_area.getHighlighter().addHighlight(endSearch,endSearch + turnToString2.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.green));
            }
            else if (endSearch <= txt_area.getText().length() && endSearch >= 0){
                txt_area.getHighlighter().addHighlight(endSearch,endSearch + turnToString2.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.green));
            }
            if(endSearch == -1){
                lbl_search_res.setText("Can not found.");
            }
            //    System.out.print(" "+endSearch);
        }
        catch (BadLocationException ex) {
            endSearch = -1;
            //    System.out.printf("error");
            Logger.getLogger(MainGUI1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed
        
    private void txt_user_commandKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_user_commandKeyPressed
         
        
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){

            try {
            JSch jsch=new JSch();
            session=jsch.getSession(user, host, 22);
            UserInfo ui=new MyUserInfo();
            session.setUserInfo(ui);
            session.connect();
            out = new JTextAreaOutputStream(txt_area);
            System.setOut (new PrintStream (out));
            String command = txt_user_command.getText();

                txt_area.setText("");
            
            channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            if(!channel.isConnected()){
                channel.connect();
            }
                     
            isStart = true;
                     
        }
        catch(JSchException j){
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Connect time out. "+j,"Connection",JOptionPane.ERROR_MESSAGE);
            session.disconnect();
            System.exit(0);
        }
        catch (Exception ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Unable to connect. "+ex,"Connection",JOptionPane.INFORMATION_MESSAGE);
            session.disconnect();
        }
   
        }
    
        
    }//GEN-LAST:event_txt_user_commandKeyPressed

    private void txt_user_commandKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_user_commandKeyReleased

       
        
    }//GEN-LAST:event_txt_user_commandKeyReleased

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void txt_searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyTyped

        
        
    }//GEN-LAST:event_txt_searchKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_start_stop;
    private javax.swing.JComboBox combo_log;
    private javax.swing.ButtonGroup grp_file_toshow;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_search_res;
    private javax.swing.JPopupMenu.Separator pop_close;
    private javax.swing.JPopupMenu pop_options;
    private javax.swing.JMenuItem pop_txtarea_clear;
    private javax.swing.JMenuItem pop_txtarea_open;
    private javax.swing.JMenuItem pop_txtarea_save;
    private javax.swing.JRadioButton radio_cat;
    private javax.swing.JRadioButton radio_tail;
    javax.swing.JTextArea txt_area;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_user_command;
    // End of variables declaration//GEN-END:variables
}

