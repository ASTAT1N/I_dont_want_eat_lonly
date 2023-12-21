import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class client_login extends JFrame{
	static public void main(String[] args){
        new client_login();
    }
    // window info
    private final int window_width = 300;
    private final int window_height = 350;

    // serverSet info
    
    JComboBox<String> serverSelect = null;
    private final String serverDBAddr = "data/server.txt";
    private final int serverSelect_width = 270;
    private final int serverSelect_height = 20;
    private final int serverSelect_x = 10;
    private final int serverSelect_y = 10;
    private Vector<String> getServerSet(){
        Vector<String> serverSet = new Vector<>();
        try {
            FileInputStream fin = new FileInputStream(serverDBAddr);
            InputStreamReader in=new InputStreamReader(fin,"UTF-8");
            int msC;
            String msStr="";
            while ((msC=in.read())!=-1) {
                Character c = (char)msC;
                if(c=='\n'){
                    
                    serverSet.add(msStr);
                    
                    msStr="";
                }
                else{
                    msStr = msStr + (c.toString());

                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverSet;
    }


    
    // IDField info
    private JLabel IDword = new JLabel("ID");
    private final int IDword_width = 60;
    private final int IDword_height = 20;
    private final int IDword_x = 10;
    private final int IDword_y = serverSelect_y+serverSelect_height+10;
    private JTextField IDField = new JTextField(30);
    private final int IDField_width = 200;
    private final int IDField_height = 20;
    private final int IDField_x = IDword_x+IDword_width+10;
    private final int IDField_y = IDword_y;

    // PWField info
    private JLabel PWword = new JLabel("Password");
    private final int PWword_x = IDword_x;
    private final int PWword_y = IDword_y+IDword_height+10;
    private JPasswordField PWField = new JPasswordField(30);
    private final int PWField_x = IDField_x;
    private final int PWField_y = PWword_y;


    // loginButton info
    private JButton loginButton = new JButton("log in");
    private final int loginButton_width = 120;
    private final int loginButton_height = 40;
    private final int loginButton_x = 160;
    private final int loginButton_y = PWword_y+IDField_height+10;
    

    // server info
    private String serverAddress= "localhost:9999";
    private BufferedReader in = null;
    private BufferedWriter out = null;

    public client_login(){
        super("Log-in");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        
        setSize(window_width,window_height);

        // add KeyListener
        LoginKeyListener loginKeyListener = new LoginKeyListener();
        addKeyListener(loginKeyListener);
        // add server select
        serverSelect = new JComboBox<>(getServerSet());
        serverSelect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> comBox = (JComboBox<String>)e.getSource();
                serverAddress = (String)comBox.getSelectedItem();
                connectToServer();
            }
            
        });
        serverSelect.setSize(serverSelect_width,serverSelect_height);
        serverSelect.setLocation(serverSelect_x, serverSelect_y);
        serverSelect.addKeyListener(loginKeyListener);
        c.add(serverSelect);

        // add ID and PW
        IDword.setSize(IDword_width,IDword_height);
        IDword.setLocation(IDword_x, IDword_y);
        c.add(IDword);
        
        IDField.setSize(IDField_width, IDField_height);
        IDField.setLocation(IDField_x, IDField_y);
        IDField.addKeyListener(loginKeyListener);
        c.add(IDField);

        PWword.setSize(IDword_width,IDword_height);
        PWword.setLocation(PWword_x,PWword_y);
        c.add(PWword);

        PWField.setSize(IDField_width, IDField_height);
        PWField.setLocation(PWField_x, PWField_y);
        PWField.setEchoChar('*');
        PWField.addKeyListener(loginKeyListener);
        c.add(PWField);


        // add loginButton
        loginButton.setSize(loginButton_width,loginButton_height);
        loginButton.setLocation(loginButton_x, loginButton_y);
        loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                try {

                    out.write("login;"+IDField.getText()+";"+String.valueOf(PWField.getPassword())+";\n");
                    out.flush();
                    String msg = in.readLine();
                    if(msg.equals("LoginCorrect")){
                        new client_window(IDField.getText(),socket);
                        setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Wrong password!","Fail",JOptionPane.ERROR_MESSAGE);
                        PWField.setText("");
                    }
                } catch (IOException er) {
                    er.printStackTrace();
                }
				
			}
            
        });
        c.add(loginButton);
        // add component listener
        addComponentListener(new force_window_size(window_width, window_height));
        
        // set focus
        setFocusable(true);
        requestFocus();

        // server connect
        connectToServer();
        setVisible(true);
        //loginButton.doClick();

    }
    // connect to server
    private Socket socket;
    private void connectToServer(){
        try{
            StringTokenizer addrTok = new StringTokenizer(serverAddress);
            addrTok = new StringTokenizer(addrTok.nextToken(),":");
            String addr = addrTok.nextToken();
            int port = Integer.valueOf(addrTok.nextToken());
            socket = new Socket(addr,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // keyBored listener
    class LoginKeyListener implements KeyListener{
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_ENTER){            
                loginButton.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}     
    }
}