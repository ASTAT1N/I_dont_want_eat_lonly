import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class client_window extends JFrame{
    public client_window(String titleName){
        super(titleName);
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEFT,30,20));
        c.add(new Bored());
        c.add(new Bored());
        setSize(500,550);
        setVisible(true);
    }
}
