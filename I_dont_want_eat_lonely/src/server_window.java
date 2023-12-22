
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

public class server_window extends JFrame {
    static public void main(String[] arges) {
        new server_window();
    }

    // window info
    private final int window_width = 600;
    private final int window_height = 300 + 30;

    // log info
    private class communicateThread extends Thread {
        // IO buffers
        private BufferedReader in = null;
        private BufferedWriter out = null;
        private Integer threadNum;
        public communicateThread(Socket _socket, Integer num) {
            try {
                in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
                threadNum = num;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String msg = null;

            try {
                while (true) {
                    // get message
                    msg = in.readLine();
                    logs.append(msg + "\n");
                    // process
                    StringTokenizer msgToken = new StringTokenizer(msg, ";");
                    String commend = msgToken.nextToken();
                    if (commend.equals("login")) {
                        // login
                        String ID = msgToken.nextToken();
                        String PW = msgToken.nextToken();
                        if (loginManager.loginCheck(ID, PW)) {
                            out.write("LoginCorrect" + "\n");
                        } else {
                            out.write("LoginFail" + "\n");
                        }
                        out.flush();
                    }
                    if (commend.equals("newBored")) {
                        // new bored
                        String bored_name = msgToken.nextToken();
                        String host_name = msgToken.nextToken();
                        String bored_detail = msgToken.nextToken();
                        String image_addr = msgToken.nextToken();
                        boredInfoSaves.put(bored_name, new BoredInfo(bored_name, host_name, bored_detail, image_addr));
                        anounce(msg);
                    }
                    if (commend.equals("join")) {
                        // join
                        String host_name = msgToken.nextToken();
                        String bored_name = msgToken.nextToken();
                        BoredInfo ms = boredInfoSaves.get(bored_name);
                        if (ms != null) {
                            if (!ms.host_ID.equals(host_name)) {
                                if (ms.joinedGuys.get(host_name) == null) {
                                    ms.members++;
                                    ms.joinedGuys.put(host_name, 1);
                                    anounce("join;" + bored_name);
                                }
                            }
                        }
                    }
                    if (commend.equals("changeID")) {
                        String user_ID = msgToken.nextToken();
                        String user_name = msgToken.nextToken();
                        String imageAddr = msgToken.nextToken();
                        FileOutputStream sourFile = new FileOutputStream("data/" + user_ID + ".txt");
                        OutputStreamWriter sourFileStream = new OutputStreamWriter(sourFile);
                        BufferedWriter out = new BufferedWriter(sourFileStream);
                        out.write(user_name + ";" + imageAddr);
                        out.flush();
                        out.close();

                    }
                }

            } catch (IOException e) {
                logs.append("disconnect\n");
                clientWriter.remove(threadNum);
                e.printStackTrace();
                return;
            }

        }
    }

    private JTextArea logs = new JTextArea();
    private final int logs_width = window_width;
    private final int logs_height = window_height;

    // announce to every client
    private HashMap<Integer,BufferedWriter> clientWriter = new HashMap<>();

    private void anounce(String message) {
        for (Entry<Integer,BufferedWriter> out : clientWriter.entrySet()) {
            try {
                out.getValue().write(message + "\n");
                out.getValue().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // BoredInfo saves
    private class BoredInfo {
        public String bored_name;
        public String host_ID;
        public String bored_detail;
        public String image_addr;
        public int members = 0;
        public HashMap<String, Integer> joinedGuys = new HashMap<>();

        public BoredInfo(String _bored_name, String _host_ID, String _bored_detail, String _image_addr) {
            bored_name = _bored_name;
            host_ID = _host_ID;
            bored_detail = _bored_detail;
            image_addr = _image_addr;
        }
    }

    HashMap<String, BoredInfo> boredInfoSaves = new HashMap<>(); // bored_name, etc

    public server_window() {
        super("server window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(window_width, window_height);
        Container c = getContentPane();

        // set logs
        logs.setEditable(false);
        logs.setSize(logs_width, logs_height);

        c.add(new JScrollPane(logs), BorderLayout.CENTER);
        // set visable
        setVisible(true);

        // add component listener
        addComponentListener(new force_window_size(window_width, window_height));

        // connect
        ServerSocket listener = null;
        Socket socket = null;
        Integer num=0;
        try {
            listener = new ServerSocket(9999);
            while (true) {
                socket = listener.accept();
                logs.append("Connected\n");
                clientWriter.put(num,new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                ++num;
                new communicateThread(socket,num).start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // exit connect
        try {
            if (listener != null) {
                listener.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private LoginMenager loginManager = new LoginMenager();

    private class LoginMenager {
        private HashMap<String, String> DB = new HashMap<>(); // ID, PW
        private String loginDataLocation = "data/login.txt";

        public LoginMenager() {
            try {
                FileInputStream sourFile = new FileInputStream(loginDataLocation);
                InputStreamReader sourFileStream = new InputStreamReader(sourFile);
                BufferedReader in = new BufferedReader(sourFileStream);
                String msStr;

                while ((msStr = in.readLine()) != null) {
                    StringTokenizer strTok = new StringTokenizer(msStr);
                    String ID = strTok.nextToken();
                    String PW = strTok.nextToken();
                    DB.put(ID, PW);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public boolean loginCheck(String _ID, String _PW) {
            if (DB.get(_ID) == null) {
                return false;
            }
            return DB.get(_ID).equals(_PW);
        }

        public void loginSave() {
            try {
                FileOutputStream fout = new FileOutputStream(loginDataLocation);
                OutputStreamWriter out = new OutputStreamWriter(fout, "UTF-8");
                for (Entry<String, String> el : DB.entrySet()) {
                    out.write(el.getKey() + " " + el.getValue() + "\n");
                }
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean createAccount(String _ID, String _PW) {
            if (!DB.get(_ID).equals(null)) {
                return false;
            }
            DB.put(_ID, _PW);
            return true;
        }
    }
}
