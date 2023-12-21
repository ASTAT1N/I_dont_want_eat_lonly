import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.*;

public class client_window extends JFrame{

    static public void main(String[] args){
        new client_window("admin",null,null);
    }
    // user info
    private user_profile user = null;

    // window container
    Container c =null;

    // message receiver
    private class messageReceiver extends Thread{
        private BufferedReader in=null;
        public messageReceiver(BufferedReader _in){
            in=_in;
        }
        public void run(){
            // receiving infos
            try {
                while (true) {
                    c.add(new Bored());
                    String msg=in.readLine();
                    // process
                    StringTokenizer msgToken = new StringTokenizer(msg,";");
                    String commend = msgToken.nextToken(); 
                    if(commend.equals("login")){
                        // login
                        String ID = msgToken.nextToken();
                        String PW = msgToken.nextToken();
                    
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
    public client_window(String _ID, BufferedReader _in, BufferedWriter _out){
        // set user info
        user = new user_profile(_ID);

        // window setting
        setTitle(user.name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEFT,30,20));
        setSize(500,550);

        // menubar
        JMenuItem jMenuItem = null;
        JMenuBar mmb = new JMenuBar();
        JMenu boardMenu = new JMenu("board");
        jMenuItem=new JMenuItem("new");
        jMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new New_Bored();
            }
            
        });
        boardMenu.add(jMenuItem);
        mmb.add(boardMenu);
        JMenu userMenu = new JMenu("user");
        jMenuItem=new JMenuItem("view");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new show_profile(user);
            }
            
        });
        userMenu.add(jMenuItem);
        jMenuItem=new JMenuItem("edit");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new change_profile(user,_out);
            }
            
        });
        userMenu.add(jMenuItem);
        mmb.add(userMenu);
        JMenu settingMenu = new JMenu("setting");
        jMenuItem =new JMenuItem("setting");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
            
        });
        settingMenu.add(jMenuItem);
        mmb.add(settingMenu);

        setJMenuBar(mmb);

        // compoenet add


        // show component
        setVisible(true);
        
        // active messageReceiver thread
        new messageReceiver(_in).start();
    }


}
