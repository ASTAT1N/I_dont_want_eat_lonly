import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.*;

public class client_window extends JFrame {

    // user info
    private user_profile user = null;

    // window container
    Container container = null;

    // contained bored hashmap
    HashMap<String, Bored> containedBored = new HashMap<>();

    // message receiver
    private class messageReceiver extends Thread {
        private BufferedReader in = null;

        public messageReceiver(BufferedReader _in) {
            in = _in;
        }

        public void run() {
            // receiving infos
            try {
                while (true) {

                    String msg = in.readLine();
                    // process
                    StringTokenizer msgToken = new StringTokenizer(msg, ";");
                    String commend = msgToken.nextToken();
                    if (commend.equals("newBored")) {
                        // login
                        String bored_name = msgToken.nextToken();
                        String host_ID = msgToken.nextToken();
                        String bored_detail = msgToken.nextToken();
                        String image_addr = msgToken.nextToken();
                        Bored ms = new Bored(user.ID, bored_name, host_ID, bored_detail, image_addr, out);
                        containedBored.put(bored_name, ms);
                        container.add(ms);
                        revalidate();
                    }
                    if (commend.equals("join")) {
                        // join
                        String bored_name = msgToken.nextToken();
                        Bored ms = containedBored.get(bored_name);
                        if (ms != null) {
                            ms.add_extraCount();
                        }
                        revalidate();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private BufferedReader in = null;
    private BufferedWriter out = null;

    public client_window(String _ID, Socket _socket) {
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
        container.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 20));
        setSize(500, 550);

        // user profile
        show_profile showProfile = new show_profile(user);
        change_profile changeProfile = new change_profile(user, out);

        // menubar
        JMenuItem jMenuItem = null;
        JMenuBar mmb = new JMenuBar();
        JMenu boardMenu = new JMenu("board");
        jMenuItem = new JMenuItem("new");
        jMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new New_Bored(_ID, out);
            }

        });
        boardMenu.add(jMenuItem);
        mmb.add(boardMenu);
        JMenu userMenu = new JMenu("user");
        jMenuItem = new JMenuItem("view");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfile.setVisible(true);
            }

        });
        userMenu.add(jMenuItem);
        jMenuItem = new JMenuItem("edit");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeProfile.setVisible(true);
            }

        });
        userMenu.add(jMenuItem);
        mmb.add(userMenu);
        JMenu settingMenu = new JMenu("setting");
        jMenuItem = new JMenuItem("setting");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
        settingMenu.add(jMenuItem);
        mmb.add(settingMenu);

        setJMenuBar(mmb);

        // show component
        setVisible(true);

        // active messageReceiver thread
        new messageReceiver(in).start();
    }

}
