/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzevideogatewayApplet;

import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author GS-1062
 */
public class IntialFrom extends javax.swing.JFrame {

    /**
     * Creates new form IntialFrom
     */
    public IntialFrom() {
        initComponents();
      setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("fuzelog.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_grpname = new javax.swing.JTextField();
        txt_sername = new javax.swing.JTextField();
        txt_ip = new javax.swing.JTextField();
        txt_login = new javax.swing.JTextField();
        txt_passlogin = new javax.swing.JPasswordField();
        btn_save = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setText("Group Name : ");

        jLabel2.setText("Server Name : ");

        jLabel3.setText("IP :  ");

        jLabel4.setText(" Log In : ");

        jLabel5.setText("Password : ");

        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_ip, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(txt_sername, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_grpname, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_passlogin, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_login))
                .addContainerGap(134, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_grpname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2))
                    .addComponent(txt_sername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txt_passlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btn_save)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed

        String password = new String(txt_passlogin.getPassword());
        if((txt_grpname.getText().trim().isEmpty() == false && txt_sername.getText().trim().isEmpty() == false && txt_ip.getText().trim().isEmpty() == false && txt_login.getText().trim().isEmpty() == false  && password.isEmpty() == false)){
         
            String path = System.getProperty("user.name");
            File f = new File(path+"fuze.odb");
       
            try{
                Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+f);
                Statement s = null;
                s = conn.createStatement();
              
                String server_name = txt_sername.getText().trim();
                String IP = txt_ip.getText().trim();
                String user = txt_login.getText().trim();
               
                int gid = 1,i = 1;
                String gname = txt_grpname.getText().trim();
                
                if((server_name.isEmpty() == false && IP.isEmpty() == false && user.isEmpty() == false && password.isEmpty() == false))
                {
                    if(true)
                    {
                        String q1 ="insert into SERVERS values("+i+",\""+server_name+"\",\""+IP+"\",\""+user+"\",\""+password+"\","+gid+");";
                        String q2 ="insert into GROUPS values("+i+",\""+txt_grpname.getText().trim()+"\");";
                        try{ 
                            s.execute(q2);
                            s.execute(q1);
                            final JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel,server_name+" "+"Server added successfully to the group "+gname,"Server",JOptionPane.INFORMATION_MESSAGE);
                            conn.close();
                            System.exit(0);
                        }
                       catch(Exception e){
                             final JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel,IP+" "+"Server already exites","Server",JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                    }
                    else{
                        final JPanel panel = new JPanel();
                        JOptionPane.showMessageDialog(panel, "Check IP address","IP address",JOptionPane.WARNING_MESSAGE);
                    }
              
                    conn.close();
                }
                else{
                        final JPanel panel = new JPanel();
                        JOptionPane.showMessageDialog(panel,"Please check","Information",JOptionPane.WARNING_MESSAGE);
                }
      
            }
            catch(Exception e){
                System.out.print("error "+e);
            }
        }
        else{
     //   System.out.print("\n\t\t Check the contents ");
        }
        
    }//GEN-LAST:event_btn_saveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txt_grpname;
    private javax.swing.JTextField txt_ip;
    private javax.swing.JTextField txt_login;
    private javax.swing.JPasswordField txt_passlogin;
    private javax.swing.JTextField txt_sername;
    // End of variables declaration//GEN-END:variables
}
