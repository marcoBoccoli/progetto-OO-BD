package src;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class getImage {
	public Image pic;
	public getImage(String path) {
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File(path));//"src/image/logo.png"
			pic = myPicture.getScaledInstance(100*(1237/217), 100, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
