import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class New_Bored extends JFrame{
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
    

    // detailInfo_info
    private JTextArea detailInfo = new JTextArea();
    private final int detailInfo_x=10;
    private final int detailInfo_y=120;
    private final int detailInfo_width = 280;
    private final int detailInfo_height = 60;

    public New_Bored(){
        super("new Bored");
        setContentPane(new Bored_Detail_panel());
        setSize(bored_detail_width+10,bored_detail_height+10);
        addComponentListener(new force_window_size(bored_detail_width, bored_detail_height));
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
