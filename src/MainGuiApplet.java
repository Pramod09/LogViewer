
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;
import fuzevideogatewayApplet.MainGUI;
import java.awt.Graphics;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GS-1062
 */
public class MainGuiApplet extends javax.swing.JApplet {

     public void init() {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                String s[] = {"dfdf","dfd"};
                MainGUI.main(s);
            }
        });
    }
    public void paint(Graphics g){
        g.drawString("This is hello world",20,20);
     
    
    
      g.drawString("pro running",100,100);
    try{
      JSch jsch=new JSch();
 
      //jsch.setKnownHosts("/home/foo/.ssh/known_hosts");
 {
 /*       host=JOptionPane.showInputDialog("Enter username@hostname",
                                         System.getProperty("user.name")+
                                         "@localhost"); 
   */
        
        
    }
 String host = "206.81.183.91";
      //String user=host.substring(0, host.indexOf('@'));
  //    host=host.substring(host.indexOf('@')+1);
 
    String user = "akuma";
      Session session = null;
        try {
            session = jsch.getSession(user, host, 22);
        } catch (JSchException ex) {
            Logger.getLogger(MainGuiApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
 
      //String passwd = JOptionPane.showInputDialog("Enter password");
        String passwd="Dec_2014";
      session.setPassword(passwd);
 
      UserInfo ui = new MyUserInfo(){
        public void showMessage(String message){
          JOptionPane.showMessageDialog(null, message);
        }
        public boolean promptYesNo(String message){
          Object[] options={ "yes", "no" };
          int foo=JOptionPane.showOptionDialog(null, 
                                               message,
                                               "Warning", 
                                               JOptionPane.DEFAULT_OPTION, 
                                               JOptionPane.WARNING_MESSAGE,
                                               null, options, options[0]);
          return foo==0;
        }
 
        // If password is not given before the invocation of Session#connect(),
        // implement also following methods,
        //   * UserInfo#getPassword(),
        //   * UserInfo#promptPassword(String message) and
        //   * UIKeyboardInteractive#promptKeyboardInteractive()
 
      };
 
      session.setUserInfo(ui);
 
          try {
              // It must not be recommended, but if you want to skip host-key check,
              // invoke following,
              // session.setConfig("StrictHostKeyChecking", "no");
              
              //session.connect();
              session.connect(30000);   // making a connection with timeout.
          } catch (JSchException ex) {
              Logger.getLogger(MainGuiApplet.class.getName()).log(Level.SEVERE, null, ex);
          }
 
      Channel channel = null;
        try {
            channel = session.openChannel("shell");
        } catch (JSchException ex) {
            Logger.getLogger(MainGuiApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
 
      // Enable agent-forwarding.
      //((ChannelShell)channel).setAgentForwarding(true);
 
      channel.setInputStream(System.in);
      /*
      // a hack for MS-DOS prompt on Windows.
      channel.setInputStream(new FilterInputStream(System.in){
          public int read(byte[] b, int off, int len)throws IOException{
            return in.read(b, off, (len>1024?1024:len));
          }
        });
       */
 
      channel.setOutputStream(System.out);
 
      /*
      // Choose the pty-type "vt102".
      ((ChannelShell)channel).setPtyType("vt102");
      */
 
      /*
      // Set environment variable "LANG" as "ja_JP.eucJP".
      ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
      */
 
      //channel.connect();
      channel.connect(3*1000);
    }
    catch(Exception e){
      System.out.println(e);
    }
  
    }
  public static abstract class MyUserInfo
                          implements UserInfo, UIKeyboardInteractive{
    public String getPassword(){ return null; }
    public boolean promptYesNo(String str){ return false; }
    public String getPassphrase(){ return null; }
    public boolean promptPassphrase(String message){ return false; }
    public boolean promptPassword(String message){ return false; }
    public void showMessage(String message){ }
    public String[] promptKeyboardInteractive(String destination,
                                              String name,
                                              String instruction,
                                              String[] prompt,
                                              boolean[] echo){
      return null;
    }
  }
    
    
       
     
    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

   }
