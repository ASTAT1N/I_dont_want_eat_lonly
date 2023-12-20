import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import javax.swing.JTextField;

public class change_profile extends show_profile {

	// changeName info
	JTextField changeName = new JTextField(9);
	private final int changeName_position_x = 100;
	private final int changeName_position_y = 40;
	private final int userName_size_width = 80;
    private final int userName_size_height = 20;
	
	// imageChange


	public change_profile(user_profile _user,BufferedWriter _out) {
		super(_user);
		Container c = getContentPane();
		changeName.setSize(userName_size_width,userName_size_height);
		changeName.setLocation(changeName_position_x, changeName_position_y);
		changeName.setText(user.name);
		c.add(changeName);
	}
	

}
