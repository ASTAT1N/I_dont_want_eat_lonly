import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import javax.swing.JButton;
import javax.swing.JTextField;

public class change_profile extends show_profile {

	// changeName info
	JTextField changeName = new JTextField(9);
	private final int changeName_position_x = 100;
	private final int changeName_position_y = 40;
	private final int changeName_size_width = 80;
    private final int changeName_size_height = 20;
	
	// imageChange info
	JButton imageChange = new JButton("change image");
	private final int imageChange_position_x = changeName_position_x;
	private final int imageChange_position_y = changeName_position_y+changeName_size_height+10;
	private final int imageChange_size_width = 180;
	private final int imageChange_size_height = 20;

	// send button info
	JButton sendButton = new JButton("send");
	private final int sendButton_postion_x = changeName_position_x+changeName_size_width+10;
	private final int sendButton_postion_y=10;
	private final int sendButton_size_width = 90;
	private final int sendButton_size_height=50;
	public change_profile(user_profile _user,BufferedWriter _out) {
		super(_user);
		Container c = getContentPane();
		// changeName
		changeName.setSize(changeName_size_width,changeName_size_height);
		changeName.setLocation(changeName_position_x, changeName_position_y);
		changeName.setText(user.name);
		c.add(changeName);
		// imageChange
		imageChange.setSize(imageChange_size_width, imageChange_size_height);
		imageChange.setLocation(imageChange_position_x, imageChange_position_y);
		c.add(imageChange);

		// sendButton
		sendButton.setSize(sendButton_size_width,sendButton_size_height);
		sendButton.setLocation(sendButton_postion_x, sendButton_postion_y);
		c.add(sendButton);
	}
	

}
