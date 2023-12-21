import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.*;

public class util {
	static public int[] setImageCut(ImageIcon image, int image_size_width, int image_size_height){
        // x1, x2, y1, y2
        int[] result = new int[4];
        int image_cut_x1;
        int image_cut_x2;
        int image_cut_y1;
        int image_cut_y2;
        // cal
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
        //return
        result[0]=image_cut_x1;
        result[1]=image_cut_x2;
        result[2]=image_cut_y1;
        result[3]=image_cut_y2;
        return result;
    }
    static public void copyToImageAddr(String sour, String dest){
        try {
			FileInputStream sourFile = new FileInputStream(sour);
            InputStreamReader sourFileStream = new InputStreamReader(sourFile);
            BufferedReader in = new BufferedReader(sourFileStream);
            FileOutputStream destFile = new FileOutputStream(dest);
            OutputStreamWriter destFileStream = new OutputStreamWriter(destFile);
            BufferedWriter out = new BufferedWriter(destFileStream);
            String line = new String();
            
            while ((line=in.readLine())!=null) {
                out.write(line);
            }
            out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
            e.printStackTrace();
        }
        
    }
}
