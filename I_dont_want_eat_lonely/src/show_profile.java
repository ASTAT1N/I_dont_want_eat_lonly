import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class show_profile extends JFrame{
    // window info
    private final int window_width = 200;
    private final int window_height = 140;

    // user image info
    private final int image_position_x = 10;
    private final int image_position_y=10;
    private final int image_size_width = 80;
    private final int image_size_height = image_size_width;
    
    // user name info
    private JLabel userName = null;
    private final int userName_position_x = image_position_x+image_size_width+10;
    private final int userName_position_y=image_position_y;
    private final int userName_size_width = 90;
    private final int userName_size_height = 20;

    // user info
    private user_profile user = null;
    public show_profile(user_profile _user){
        super(_user.name);
        user=_user;
        // set panel
        setContentPane(new show_profile_panel());
        setSize(window_width,window_height);
        // add component listener
        addComponentListener(new ComponentListener() {
            // force size
			@Override
			public void componentResized(ComponentEvent e) {
				Component wind = (Component)e.getSource();
                wind.setSize(window_width, window_height);
			}

			@Override
			public void componentMoved(ComponentEvent e) {}

			@Override
			public void componentShown(ComponentEvent e) {}

			@Override
			public void componentHidden(ComponentEvent e) {}
            
        });
        setVisible(true);
        
    }
    
    private class show_profile_panel extends JPanel{
        public show_profile_panel(){
            setLayout(null);
            userName=new JLabel(user.name);
            userName.setSize(userName_size_width,userName_size_height);
            userName.setLocation(userName_position_x, userName_position_y);
            add(userName);
        }
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            g.drawImage(user.image.getImage(), image_position_x, image_position_y, image_position_x + image_size_width, image_position_y + image_size_height,
                user.image_cut_x1,user.image_cut_y1,user.image_cut_x2,user.image_cut_y2, this);
        }
    }
    
}
