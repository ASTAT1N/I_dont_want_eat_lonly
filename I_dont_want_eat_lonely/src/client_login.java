import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class client_login extends JFrame{
	static public void main(String[] args){
        new client_login();
    }
    // window info
    private final int window_width = 300;
    private final int window_height = 350;

    // IDField info
    private JLabel IDword = new JLabel("ID");
    private final int IDword_width = 60;
    private final int IDword_height = 20;
    private final int IDword_x = 10;
    private final int IDword_y = 10;
    private JTextField IDField = new JTextField(30);
    private final int IDField_width = 200;
    private final int IDField_height = 20;
    private final int IDField_x = 80;
    private final int IDField_y = IDword_y;

    // PWField info
    private JLabel PWword = new JLabel("Password");
    private final int PWword_x = IDword_x;
    private final int PWword_y = 40;
    private JPasswordField PWField = new JPasswordField(30);
    private final int PWField_x = IDField_x;
    private final int PWField_y = PWword_y;


    // loginButton info
    private JButton loginButton = new JButton("log in");
    private final int loginButton_width = 120;
    private final int loginButton_height = 40;
    private final int loginButton_x = 160;
    private final int loginButton_y = 70;
    

    // server info
    private String serverAddress="localhost";
    private BufferedReader in = null;
    private BufferedWriter out = null;

    public client_login(){
        super("Log-in");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        
        setSize(window_width,window_height);

        // add ID and PW
        IDword.setSize(IDword_width,IDword_height);
        IDword.setLocation(IDword_x, IDword_y);
        c.add(IDword);
        
        IDField.setSize(IDField_width, IDField_height);
        IDField.setLocation(IDField_x, IDField_y);
        c.add(IDField);

        PWword.setSize(IDword_width,IDword_height);
        PWword.setLocation(PWword_x,PWword_y);
        c.add(PWword);

        PWField.setSize(IDField_width, IDField_height);
        PWField.setLocation(PWField_x, PWField_y);
        PWField.setEchoChar('*');
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
                        new client_window("AAAAA");
                        setVisible(false);
                    }

                } catch (IOException er) {
                    er.printStackTrace();
                    // TODO: handle exception
                }
				
			}
            
        });
        c.add(loginButton);


        // add component listener
        addComponentListener(new ComponentListener() {
            // force size
			@Override
			public void componentResized(ComponentEvent e) {
				Component wind = (Component)e.getSource();
                wind.setSize(window_width, window_height);
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {}

			@Override
			public void componentShown(ComponentEvent e) {}

			@Override
			public void componentHidden(ComponentEvent e) {}
            
        });
        
        // server connect
        connectToServer();
        setVisible(true);
        //loginButton.doClick();

    }
    // connect to server
    
    private void connectToServer(){
        Socket socket;
        try{
            socket = new Socket(serverAddress,9999);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}