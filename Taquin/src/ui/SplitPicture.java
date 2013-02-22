package ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class SplitPicture {
	public static String splitImage(int size) {
		File fileImage = selectImage();

		String fileBase = "C:/Users/demytt/Desktop/temp/img";
		try{			
			BufferedImage img = ImageIO.read(fileImage);
			int height = img.getHeight();
			int width = img.getWidth();
			
			for(int i=1; i<=size*size; i++){
				int h = height/size;
				int w = width/size;
			
				int a = w*((i-1)%size);
				int b = h*((i-1)/size);
				BufferedImage splittedImg = img.getSubimage(a, b, w, h);
				File f = new File(fileBase + Integer.toString(i-1) + ".png");
				ImageIO.write(splittedImg, "png", f);			
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileBase;
	}
	
    private static File selectImage() {
    	JFileChooser dialogue = new JFileChooser(new File("."));
    	File fichier = null;
    	
    	if (dialogue.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
    		fichier = dialogue.getSelectedFile();
    	
    	return fichier;    	
    }    
}