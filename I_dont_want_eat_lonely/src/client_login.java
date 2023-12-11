import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class client_login extends JFrame{
	static public void main(String[] args){
        new client_login();
    }
    public client_login(){
        super("Log-in");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        setSize(300,350);

        JButton toWindow = new JButton("log in");
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
}
