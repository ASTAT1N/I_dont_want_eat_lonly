import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class client_window extends JFrame{

    static public void main(String[] args){
        new client_window("admin");
    }
    // user info
    private user_profile user = null;

    public client_window(String _ID){
        // set user info
        user = new user_profile(_ID);

        // window setting
        setTitle(user.name);
        System.out.println(user.name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
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
                System.out.println("AAA");
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
                System.out.println("AAA");
            }
            
        });
        userMenu.add(jMenuItem);
        jMenuItem=new JMenuItem("edit");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BBB");
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
        c.add(new Bored());

        // show component
        setVisible(true);
        
    }


}
