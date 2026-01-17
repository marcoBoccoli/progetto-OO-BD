package src;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class getImage {
	public Image pic;
	public getImage(String path) {
		try {
			BufferedImage myPicture = ImageIO.read(new File(path));
			pic = myPicture.getScaledInstance(100*(1237/217), 100, Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}
	}
}
