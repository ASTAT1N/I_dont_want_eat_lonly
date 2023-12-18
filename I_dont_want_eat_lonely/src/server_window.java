import javax.sound.midi.Receiver;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.*;
import java.net.*;
import java.util.*;

public class server_window extends JFrame {
    static public void main(String[] arges){
        new server_window();
    }

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
                    
                    if(msg.equals("Hello")){
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
    private final int logs_x=0;
    private final int logs_y=0;
    private final int logs_width=600;
    private final int logs_height=300;
    
    public server_window(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);
        Container c=getContentPane();
        
        // set logs
        logs.setEditable(false);
        c.add(new JScrollPane(logs),BorderLayout.CENTER);
        // set visable
        setVisible(true);

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
