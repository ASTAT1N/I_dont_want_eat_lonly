import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.*;

public class client_window extends JFrame{

    static public void main(String[] args){
        new client_window("admin",null);
    }
    // user info
    private user_profile user = null;

    // window container
    Container container =null;

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
                    
                    String msg=in.readLine();
                    // process
                    StringTokenizer msgToken = new StringTokenizer(msg,";");
                    String commend = msgToken.nextToken(); 
                    if(commend.equals("newBored")){
                        // login
                        String bored_name=msgToken.nextToken();
                        String host_name=msgToken.nextToken();
                        String bored_detail=msgToken.nextToken();
                        container.add(new Bored());
                    
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
    private BufferedReader in =null;
    private BufferedWriter out = null;
    public client_window(String _ID, Socket _socket){
        // set user info
        user = new user_profile(_ID);
        
        try {
            in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // window setting
        setTitle(user.name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.LEFT,30,20));
        setSize(500,550);

        // menubar
        JMenuItem jMenuItem = null;
        JMenuBar mmb = new JMenuBar();
        JMenu boardMenu = new JMenu("board");
        jMenuItem=new JMenuItem("new");
        jMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new New_Bored(_ID,out);
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
                new change_profile(user,out);
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
        new messageReceiver(in).start();
    }


}
