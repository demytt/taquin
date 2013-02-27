package ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class SplitPicture {
	static BufferedImage[] bufferArray;
	
	public static String splitImage(int size) {
		File fileImage = selectImage();
		if (fileImage==null) return null;
		split(size, fileImage);
		return fileImage.getAbsolutePath();
	}
	
	public static void splitImage(int size, String imagePath) {
		split(size, new File(imagePath));
	}
	
	private static void split(int size, File file) {
		bufferArray = new BufferedImage[size*size];
		try{		
			BufferedImage img = ImageIO.read(file);
			int height = img.getHeight();
			int width = img.getWidth();
			

			for(int i=0; i<size*size; i++){
				int h = height/size;
				int w = width/size;	
				int a = w*(i%size);
				int b = h*(i/size);
				BufferedImage splittedImg = img.getSubimage(a, b, w, h);
				bufferArray[i] = splittedImg;
			}	
			bufferArray[0] = (BufferedImage) new ImageIcon().getImage();
			img.flush();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	
    private static File selectImage() {
    	JFileChooser dialogue = new JFileChooser(new File("."));
    	File fichier = null;	
    	if (dialogue.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) fichier = dialogue.getSelectedFile();
    	return fichier;    	
    }    
}
