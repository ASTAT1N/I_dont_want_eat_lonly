import java.awt.*;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.*;

public class user_profile{
    private String image_position = "/image/default.jpg";
    public ImageIcon image = new ImageIcon(image_position);
    private String ID = null;
    private String name = null;
    public user_profile(){
        ID="admin";
    }
    private void getInfo(){
        try{
            FileInputStream fin = new FileInputStream(ID+".txt");
            InputStreamReader in=new InputStreamReader(fin,"UTF-8");
            int msC;
            String msStr="";
            while ((msC=in.read())!=-1) {
                Character c = (char)msC;
                msStr = msStr + (c.toString());
            }
            StringTokenizer strTok = new StringTokenizer(msStr,";");
            name=strTok.nextToken();
            image_position=strTok.nextToken();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
