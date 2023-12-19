
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.*;
import java.net.*;
import java.util.*;
public class server_window extends JFrame {
    static public void main(String[] arges){
        new server_window();
    }
    // window info
    private final int window_width=600;
    private final int window_height=300;


    // log info
    private class communicateThread extends Thread{
        // IO buffers
        private BufferedReader in = null;
        private BufferedWriter out = null;

        public communicateThread(Socket _socket){
            try {
                in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run(){
            String msg=null;
            while (true) {
                try {
                    // get message
                    msg=in.readLine();
                    logs.append(msg+"\n");
                    // process
                    StringTokenizer msgToken = new StringTokenizer(msg,";");
                    String commend = msgToken.nextToken(); 
                    if(commend.equals("login")){
                        // login
                        
                        out.write("LoginCorrect"+"\n");
                        out.flush();
                    }
                    

                } catch (IOException e) {
                    logs.append("disconnect\n");
                    System.out.println(e.getMessage());
                    return;
                }
                
            }
        }
    }
    
    private JTextArea logs=new JTextArea();
    //private final int logs_x=0;
    //private final int logs_y=0;
    private final int logs_width=window_width;
    private final int logs_height=window_height;
    
    public server_window(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(window_width,window_height);
        Container c=getContentPane();
        
        // set logs
        logs.setEditable(false);
        logs.setSize(logs_width,logs_height);

        c.add(new JScrollPane(logs),BorderLayout.CENTER);
        // set visable
        setVisible(true);

        // add component listener
        addComponentListener(new ComponentListener() {
            // force the size
			@Override
			public void componentResized(ComponentEvent e) {
				Component wind = (Component)e.getSource();
                wind.setSize(window_width,window_height);
			}

			@Override
			public void componentMoved(ComponentEvent e) {}

			@Override
			public void componentShown(ComponentEvent e) {}

			@Override
			public void componentHidden(ComponentEvent e) {}
            
        });
        // connect
        ServerSocket listener=null;
        Socket socket=null;
        try {
            listener = new ServerSocket(9999);
            while (true) {
                socket = listener.accept();
                logs.append("Connected");
                new communicateThread(socket).start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // exit connect
        try{
            if(listener!=null){
                listener.close();
            }
            if(socket!=null){
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
