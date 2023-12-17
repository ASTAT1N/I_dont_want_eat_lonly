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
    // server info
    private String serverAddress="localhost";
    private BufferedReader in = null;
    private BufferedWriter out = null;

    public client_login(){
        super("Log-in");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        setSize(300,350);

        JButton toWindow = new JButton("log in");

        // server connect
        connectToServer();
        try {
            out.write("HELLO\n");
            out.flush();
        } catch (IOException e) {
            // TODO: handle exception
        }
        

        toWindow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new client_window("AAAAA");
                setVisible(false);
			}
            
        });
        c.add(toWindow);
        setVisible(true);
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