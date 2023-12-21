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
    private String image_position = "image/default.jpg";
    public ImageIcon image = new ImageIcon(image_position);
    private String ID = null;
    public String name = null;

    // profile image cut
    private final int image_width = 100;
    private final int image_height = 100;
    public int image_cut_x1 =0;
    public int image_cut_x2 = 100;
    public int image_cut_y1 = 0;
    public int image_cut_y2 = 100;
    private void setImageCut(){
        int sourceImage_x=image.getIconWidth();
        int sourceImage_y=image.getIconHeight();
        System.out.println(sourceImage_x+" A "+sourceImage_y);
        if((float)sourceImage_x/sourceImage_y>(float)image_width/image_height){ // x is more large
            // set y
            image_cut_y1=0;
            image_cut_y2=sourceImage_y;
            // set x
            int change_x = (int)sourceImage_y/image_height*image_width;
            image_cut_x1=(sourceImage_x-change_x)/2;
            image_cut_x2=image_cut_x1+change_x;
        }
        else{ // y is more large
            // set x
            image_cut_x1=0;
            image_cut_x2=sourceImage_x;
            // set y
            int change_y = (int)sourceImage_x/image_width*image_height;
            image_cut_y1=(sourceImage_y-change_y)/2;
            image_cut_y2=image_cut_y1+change_y;
        }
    }
    
    public user_profile(String _ID){
        ID=_ID;
        getInfo();
        setImageCut();
    }
    private void getInfo(){
        try{
            FileInputStream sourFile = new FileInputStream("data/"+ID+".txt");
            InputStreamReader sourFileStream = new InputStreamReader(sourFile);
            BufferedReader in = new BufferedReader(sourFileStream);
            String msStr= in.readLine();
            in.close();
            StringTokenizer strTok = new StringTokenizer(msStr,";");
            name=strTok.nextToken();
            image_position=strTok.nextToken();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
