import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    private String image_position="image/default.jpg";//"I_dont_want_eat_lonely/image/test.png";
    private ImageIcon image = new ImageIcon(image_position);
    private final int image_position_x=10;
    private final int image_position_y=40;
    private final int image_size_width = 180;
    private final int image_size_height =100;
    private int image_cut_x1;
    private int image_cut_x2;
    private int image_cut_y1;
    private int image_cut_y2;
    public void setImageCut(){
        int sourceImage_x=image.getIconWidth();
        int sourceImage_y=image.getIconHeight();
        if((float)sourceImage_x/sourceImage_y>(float)image_size_width/image_size_height){ // x is more large
            // set y
            image_cut_y1=0;
            image_cut_y2=sourceImage_y;
            // set x
            int change_x = (int)(sourceImage_y/image_size_height*image_size_width);
            image_cut_x1=(sourceImage_x-change_x)/2;
            image_cut_x2=image_cut_x1+change_x;
        }
        else{ // y is more large
            // set x
            image_cut_x1=0;
            image_cut_x2=sourceImage_x;
            // set y
            int change_y = (int)(sourceImage_x/image_size_width*image_size_height);
            image_cut_y1=(sourceImage_y-change_y)/2;
            image_cut_y2=image_cut_y1+change_y;
        }
    }
    public void setImage(String _position){
        image_position=_position;
        image=new ImageIcon(image_position);
        setImageCut();
        revalidate();
        repaint();
    }
    // bottom group info
    private final int bottomGroup_size_width = 40;
    private final int bottomGroup_size_height = 40;
    // host info

    //guest info


    // extraGuest info
    private JLabel extras = new JLabel();
    private final int extraGuests_position_x=155;
    private final int extraGuests_position_y=200;
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
        extras.setLocation(extraGuests_position_x, extraGuests_position_y);
        extras.setOpaque(true);
        extras.setBackground(Color.WHITE);
        add(extras);
        
        // image set
        setImageCut();


        // detail bored
        Bored_Detail bored_detail = new Bored_Detail();


        // click event
        addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				bored_detail.setVisible(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
            
        });
        
        
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        
        // bored set
        g.setColor(bored_backColor);
        g.fillRect(0, 0, bored_size_width, bored_size_height);
        // image set
        
        g.drawImage(image.getImage(), image_position_x, image_position_y, image_position_x + image_size_width, image_position_y + image_size_height,
            image_cut_x1,image_cut_y1,image_cut_x2,image_cut_y2, this);
        
        //g.drawImage(image.getImage(), image_position_x, image_position_y, image_size_width, image_size_height,image_cut_x1,image_cut_y2,image_cut_x2,image_cut_y2, this);
        //image_cut_x1,image_cut_y1,image_cut_x2,image_cut_y2,
        

        // host set

        // guset set


    }
    

    private class Bored_Detail extends JFrame{
        // bored_Detail_info
        private final int bored_detail_width=300;
        private final int bored_detail_height=250;

        // title image info
        private final int image_position_x=10;
        private final int image_position_y=10;
        private final int image_size_width = 280;
        private final int image_size_height =100;
        private int image_cut_x1;
        private int image_cut_x2;
        private int image_cut_y1;
        private int image_cut_y2;
        private void setImageCut(){
            int sourceImage_x=image.getIconWidth();
            int sourceImage_y=image.getIconHeight();
            if((float)sourceImage_x/sourceImage_y>(float)image_size_width/image_size_height){ // x is more large
                // set y
                image_cut_y1=0;
                image_cut_y2=sourceImage_y;
                // set x
                int change_x = (int)sourceImage_y/image_size_height*image_size_width;
                image_cut_x1=(sourceImage_x-change_x)/2;
                image_cut_x2=image_cut_x1+change_x;
            }
            else{ // y is more large
                // set x
                image_cut_x1=0;
                image_cut_x2=sourceImage_x;
                // set y
                int change_y = (int)sourceImage_x/image_size_width*image_size_height;
                image_cut_y1=(sourceImage_y-change_y)/2;
                image_cut_y2=image_cut_y1+change_y;
            }
        }
        // menu info
        

        // detailInfo_info
        private JTextArea detailInfo = new JTextArea();
        private final int detailInfo_x=10;
        private final int detailInfo_y=120;
        private final int detailInfo_width = 280;
        private final int detailInfo_height = 60;

        public Bored_Detail(){
            super(title.getText());
            setContentPane(new Bored_Detail_panel());
            setSize(bored_detail_width+10,bored_detail_height+10);
            Container c =getContentPane();
        }
        private class Bored_Detail_panel extends JPanel{
            public Bored_Detail_panel(){
                setPreferredSize(new Dimension(bored_detail_width,bored_detail_height));
                setLayout(null);
                // detail describe
                detailInfo.setLocation(detailInfo_x, detailInfo_y);
                detailInfo.setSize(detailInfo_width,detailInfo_height);
                detailInfo.setText("AAA");
                
                //add(detailInfo);
                //add(new JScrollPane(detailInfo));
                JScrollPane scrollDetailInfo = new JScrollPane(detailInfo);
                scrollDetailInfo.setLocation(detailInfo_x, detailInfo_y);
                scrollDetailInfo.setSize(detailInfo_width,detailInfo_height);
                add(scrollDetailInfo);
            }
            public void paintComponent(Graphics g) {
                super.paintComponents(g);
                setImageCut();
                //g.drawImage(image.getImage(), image_position_x, image_position_y, image_size_width, image_size_height, this);
                g.drawImage(image.getImage(), image_position_x, image_position_y, image_position_x + image_size_width, image_position_y + image_size_height,
                    image_cut_x1,image_cut_y1,image_cut_x2,image_cut_y2, this);
            }
        }
        
    }
}
