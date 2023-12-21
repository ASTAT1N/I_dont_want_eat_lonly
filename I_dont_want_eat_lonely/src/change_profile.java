import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class change_profile extends JFrame {
	// window info
	private final int window_width = 300;
	private final int window_height = 140;

	// user image info
	private final int image_position_x = 10;
	private final int image_position_y = 10;
	private final int image_size_width = 80;
	private final int image_size_height = image_size_width;

	// user name info
	private JLabel userName = null;
	private final int userName_position_x = image_position_x + image_size_width + 10;
	private final int userName_position_y = image_position_y;
	private final int userName_size_width = 80;
	private final int userName_size_height = 20;

	// user info
	protected user_profile user = null;

	// changeName info
	JTextField changeName = new JTextField(9);
	private final int changeName_position_x = 100;
	private final int changeName_position_y = 40;
	private final int changeName_size_width = 80;
	private final int changeName_size_height = 20;

	// imageChange info
	private String imageAddr = null;
	JButton imageChange = new JButton("change image");
	private final int imageChange_position_x = changeName_position_x;
	private final int imageChange_position_y = changeName_position_y + changeName_size_height + 10;
	private final int imageChange_size_width = 180;
	private final int imageChange_size_height = 20;

	// send button info
	JButton sendButton = new JButton("send");
	private final int sendButton_postion_x = changeName_position_x + changeName_size_width + 10;
	private final int sendButton_postion_y = 10;
	private final int sendButton_size_width = 90;
	private final int sendButton_size_height = 50;

	// send
	BufferedWriter out;

	private class show_profile_panel extends JPanel {
		public show_profile_panel() {
			setLayout(null);
			userName = new JLabel(user.name);
			userName.setSize(userName_size_width, userName_size_height);
			userName.setLocation(userName_position_x, userName_position_y);
			add(userName);

			// changeName
			changeName.setSize(changeName_size_width, changeName_size_height);
			changeName.setLocation(changeName_position_x, changeName_position_y);
			changeName.setText(user.name);
			add(changeName);
			// imageChange
			imageChange.setSize(imageChange_size_width, imageChange_size_height);
			imageChange.setLocation(imageChange_position_x, imageChange_position_y);
			imageChange.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser choo = new JFileChooser();
					FileNameExtensionFilter chooFil = new FileNameExtensionFilter("jpg & PNG", "jpg", "png");
					choo.setFileFilter(chooFil);
					int res = choo.showOpenDialog(null);
					if (res == JFileChooser.APPROVE_OPTION) {
						String sour = choo.getSelectedFile().getPath();
						String dest = choo.getSelectedFile().getName();
						dest = "image/" + dest;
						imageAddr = dest;
						util.copyToImageAddr(sour, dest);
						user.changeImage(dest);

						repaint();
					}

				}

			});
			add(imageChange);

			// sendButton
			sendButton.setSize(sendButton_size_width, sendButton_size_height);
			sendButton.setLocation(sendButton_postion_x, sendButton_postion_y);
			sendButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						out.write("changeID;" + user.ID + ";" + changeName.getText() + ";" + imageAddr + "\n");
						out.flush();
					} catch (IOException er) {
						er.printStackTrace();
					}
				}

			});
			add(sendButton);
		}

		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.drawImage(user.image.getImage(), image_position_x, image_position_y, image_position_x + image_size_width,
					image_position_y + image_size_height,
					user.image_cut_x1, user.image_cut_y1, user.image_cut_x2, user.image_cut_y2, this);
		}
	}

	public change_profile(user_profile _user, BufferedWriter _out) {
		super(_user.name);
		user = _user;
		imageAddr = user.image_position;
		out = _out;
		// set panel
		setContentPane(new show_profile_panel());
		setSize(window_width, window_height);
		// add component listener
		addComponentListener(new force_window_size(window_width, window_height));

	}

}
