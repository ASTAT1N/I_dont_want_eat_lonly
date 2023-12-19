import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class client_window extends JFrame{
    // client_window set
    private final int window_width = 500;
    private final int window_height = 550;

    static public void main(String[] args){
        new client_window("AAAA");
    }
    public client_window(String titleName){
        // window setting
        super(titleName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEFT,30,20));
        setSize(500,550);
        
        // compoenet add
        c.add(new Bored());
    

        // show component
        setVisible(true);
        
    }
    

}
