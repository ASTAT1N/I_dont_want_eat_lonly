import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class New_Bored extends JFrame{
    // bored_Detail_info
    private final int bored_detail_width=310;
    private final int bored_detail_height=250;

    // title image info
    private String imageAddr = new String("image/default.jpg");
    private ImageIcon image = new ImageIcon(imageAddr);
    private final int image_position_x=10;
    private final int image_position_y=10;
    private final int image_size_width = 280;
    private final int image_size_height =100;
    private int image_cut_x1;
    private int image_cut_x2;
    private int image_cut_y1;
    private int image_cut_y2;


    // detailInfo info
    private JTextArea detailInfo = new JTextArea();
    private final int detailInfo_x=10;
    private final int detailInfo_y=120;
    private final int detailInfo_width = 280;
    private final int detailInfo_height = 60;

    // sendBuffton info
    private JButton send = new JButton("send");
    private final int send_position_x = 200;
    private final int send_position_y=detailInfo_y+detailInfo_height+10;
    private final int send_size_width = 80;
    private final int send_size_height = 20;




    public New_Bored(){
        super("new Bored");
        setContentPane(new Bored_Detail_panel());
        setSize(bored_detail_width,bored_detail_height);
        addComponentListener(new force_window_size(bored_detail_width, bored_detail_height));
        
        setVisible(true);
    }
    private class Bored_Detail_panel extends JPanel{
        public Bored_Detail_panel(){
            setPreferredSize(new Dimension(bored_detail_width,bored_detail_height));
            setLayout(null);
            // detail describe
            
            detailInfo.setText("AAA");
            JScrollPane scrollDetailInfo = new JScrollPane(detailInfo);
            scrollDetailInfo.setLocation(detailInfo_x, detailInfo_y);
            scrollDetailInfo.setSize(detailInfo_width,detailInfo_height);
            add(scrollDetailInfo);
            send.setSize(send_size_width,send_size_height);
            send.setLocation(send_position_x, send_position_y);
            add(send);

        }
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            int[] ms = util.setImageCut(image,image_size_width,image_size_height);
            image_cut_x1=ms[0];
            image_cut_x2=ms[1];
            image_cut_y1=ms[2];
            image_cut_y2=ms[3];
            //g.drawImage(image.getImage(), image_position_x, image_position_y, image_size_width, image_size_height, this);
            g.drawImage(image.getImage(), image_position_x, image_position_y, image_position_x + image_size_width, image_position_y + image_size_height,
                image_cut_x1,image_cut_y1,image_cut_x2,image_cut_y2, this);
        }
    }
}
