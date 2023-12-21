import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.*;

public class user_profile {
    // user info
    public String image_position = "image/default.jpg";
    public ImageIcon image = new ImageIcon(image_position);
    public String ID = null;
    public String name = null;

    // profile image cut
    private final int image_width = 100;
    private final int image_height = 100;
    public int image_cut_x1 = 0;
    public int image_cut_x2 = 100;
    public int image_cut_y1 = 0;
    public int image_cut_y2 = 100;

    public user_profile(String _ID) {
        ID = _ID;
        getInfo();
        image = new ImageIcon(image_position);
        int[] ms = util.setImageCut(image, image_width, image_height);
        image_cut_x1 = ms[0];
        image_cut_x2 = ms[1];
        image_cut_y1 = ms[2];
        image_cut_y2 = ms[3];
    }

    public void changeImage(String Addr) {
        image_position = Addr;
        image = new ImageIcon(image_position);
        int[] ms = util.setImageCut(image, image_width, image_height);
        image_cut_x1 = ms[0];
        image_cut_x2 = ms[1];
        image_cut_y1 = ms[2];
        image_cut_y2 = ms[3];
    }

    private void getInfo() {
        try {
            FileInputStream sourFile = new FileInputStream("data/" + ID + ".txt");
            InputStreamReader sourFileStream = new InputStreamReader(sourFile);
            BufferedReader in = new BufferedReader(sourFileStream);
            String msStr = in.readLine();
            in.close();
            StringTokenizer strTok = new StringTokenizer(msStr, ";");
            name = strTok.nextToken();
            image_position = strTok.nextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
