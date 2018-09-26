package Music;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	private static BufferedImage spriteSheet;
	private static int tileX;
	private static int tileY;
	
	public Texture(String file, int TileX, int TileY) {
		try {
			spriteSheet = ImageIO.read(new File(file));
		} catch (IOException e) {
		  	e.printStackTrace();
		}
		tileX = TileX;
		tileY = TileY;
	}
	
	public static BufferedImage getSprite(Point point) {
		int gridX = new Integer(point.x);
		int gridY = new Integer(point.y);
		return spriteSheet.getSubimage(gridX * tileX, gridY * tileY, tileX, tileY);
	}
}
