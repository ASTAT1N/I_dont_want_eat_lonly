import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class New_Bored extends JFrame {
    // bored_Detail_info
    private final int bored_detail_width = 310;
    private final int bored_detail_height = 300;

    // title image info
    private String imageAddr = new String("image/default.jpg");
    private ImageIcon image = new ImageIcon(imageAddr);
    private final int image_position_x = 10;
    private final int image_position_y = 10;
    private final int image_size_width = 280;
    private final int image_size_height = 100;
    private int image_cut_x1;
    private int image_cut_x2;
    private int image_cut_y1;
    private int image_cut_y2;

    // boredName info
    private JTextField boredName = new JTextField(50);
    private final int boredName_position_x = 10;
    private final int boredName_position_y = image_position_y + image_size_height + 10;
    private final int boredName_size_width = image_size_width;
    private final int boredName_size_height = 20;

    // detailInfo info
    private JTextArea detailInfo = new JTextArea();
    private final int detailInfo_x = 10;
    private final int detailInfo_y = boredName_position_y + boredName_size_height + 10;
    private final int detailInfo_width = image_size_width;
    private final int detailInfo_height = 60;

    // chooseImage info
    private String sendImageAddr = "test.png";
    private JButton imageChoose = new JButton("change image");
    private final int imageChoose_position_x = 10;
    private final int imageChoose_position_y = detailInfo_y + detailInfo_height + 10;
    private final int imageChoose_size_width = 80;
    private final int imageChoose_size_height = 20;
    // sendBuffton info
    private JButton send = new JButton("send");
    private final int send_position_x = 200;
    private final int send_position_y = imageChoose_position_y;
    private final int send_size_width = 80;
    private final int send_size_height = 20;

    // hostID
    private String host_ID = "admin";

    // send
    BufferedWriter out = null;

    public New_Bored(String _host_ID, BufferedWriter _out) {
        super("new Bored");
        setContentPane(new Bored_Detail_panel());
        setSize(bored_detail_width, bored_detail_height);
        addComponentListener(new force_window_size(bored_detail_width, bored_detail_height));
        host_ID = _host_ID;
        out = _out;
        // add action listener for close
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boredName.getText().equals("")  || detailInfo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "please fill all field", "Fail", JOptionPane.ERROR_MESSAGE);
                } else {
                    String msg = "newBored;" + boredName.getText() + ";" + host_ID + ";" + detailInfo.getText() + ";"
                            + sendImageAddr + ";\n";
                    try {
                        out.write(msg);
                        out.flush();
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                    setVisible(false);
                }

            }

        });
        add(send);
        setVisible(true);
    }

    private class Bored_Detail_panel extends JPanel {
        public Bored_Detail_panel() {
            setPreferredSize(new Dimension(bored_detail_width, bored_detail_height));
            setLayout(null);

            // bored name
            boredName.setSize(boredName_size_width, boredName_size_height);
            boredName.setLocation(boredName_position_x, boredName_position_y);
            add(boredName);

            // detail describe
            JScrollPane scrollDetailInfo = new JScrollPane(detailInfo);
            scrollDetailInfo.setLocation(detailInfo_x, detailInfo_y);
            scrollDetailInfo.setSize(detailInfo_width, detailInfo_height);
            add(scrollDetailInfo);

            // image Change
            imageChoose.setSize(imageChoose_size_width, imageChoose_size_height);
            imageChoose.setLocation(imageChoose_position_x, imageChoose_position_y);
            imageChoose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser choo = new JFileChooser();
                    FileNameExtensionFilter chooFil = new FileNameExtensionFilter("jpg & PNG", "jpg", "png");
                    choo.setFileFilter(chooFil);
                    int res = choo.showOpenDialog(null);
                    if (res == JFileChooser.APPROVE_OPTION) {
                        String sour = choo.getSelectedFile().getPath();
                        String dest = choo.getSelectedFile().getName();
                        sendImageAddr = dest;
                        dest = "image/" + dest;
                        util.copyToImageAddr(sour, dest);
                        imageAddr = dest;
                        image = new ImageIcon(imageAddr);
                        repaint();
                    }

                }

            });
            add(imageChoose);

            // send button
            send.setSize(send_size_width, send_size_height);
            send.setLocation(send_position_x, send_position_y);

        }

        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            int[] ms = util.setImageCut(image, image_size_width, image_size_height);
            image_cut_x1 = ms[0];
            image_cut_x2 = ms[1];
            image_cut_y1 = ms[2];
            image_cut_y2 = ms[3];
            // g.drawImage(image.getImage(), image_position_x, image_position_y,
            // image_size_width, image_size_height, this);
            g.drawImage(image.getImage(), image_position_x, image_position_y, image_position_x + image_size_width,
                    image_position_y + image_size_height,
                    image_cut_x1, image_cut_y1, image_cut_x2, image_cut_y2, this);
        }
    }
}
