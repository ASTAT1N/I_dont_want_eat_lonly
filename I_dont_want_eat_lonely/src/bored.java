import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Bored extends JPanel {
    // bored info
    Color bored_backColor = Color.CYAN;
    private final int bored_size_width = 200;
    private final int bored_size_height=250;
    // title info
    private JLabel title = new JLabel();
    private final int title_position_x=10;
    private final int title_position_y=10;
    private final int title_size_width=180;
    private final int title_size_height=20;
    // image info
    private ImageIcon image = new ImageIcon();
    private final int image_position_x=10;
    private final int image_position_y=40;
    private final int image_size_width = 180;
    private final int image_size_height =100;
    // bottom group info
    private final int bottomGroup_size_width = 40;
    private final int bottomGroup_size_height = 40;
    // host info

    //guest info


    // extra info
    private JLabel extras = new JLabel();
    private final int extras_position_x=155;
    private final int extras_position_y=200;
    public Bored(){ // for text
        this("AAAA");
    }
    public Bored(String _title){
        // Bored set
        setLayout(null);
        setPreferredSize(new Dimension(bored_size_width,bored_size_height));
        setVisible(true);

        // title set
        title.setText(_title);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setLocation(title_position_x,title_position_y);
        title.setSize(title_size_width,title_size_height);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        add(title);

        //extras set
        extras.setSize(bottomGroup_size_width,bottomGroup_size_height);
        extras.setHorizontalAlignment(JLabel.CENTER);
        extras.setText("+"+Integer.toString(3));
        extras.setLocation(extras_position_x, extras_position_y);
        add(extras);
        extras.setOpaque(true);
        setBackground(Color.WHITE);
        
        
        
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        // bored set
        g.setColor(bored_backColor);
        g.fillRect(0, 0, bored_size_width, bored_size_height);
        // image set
        g.drawImage(image.getImage(), image_position_x, image_position_y, image_size_width, image_size_height, this);
        // host set

        // guset set


    }
    

    private class Bored_Detail extends JFrame{
        public Bored_Detail(){
            
        }
    }
}
