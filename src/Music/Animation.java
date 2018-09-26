package Music;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	private static int width;
	private static int height;
	private static Texture texture;
	private static ArrayList<Point> nextFrames = new ArrayList<>();
	
	public Animation(String file, int TileX, int TileY, int Width, int Height) {
        width = Width;
        height = Height;
        texture = new Texture(file, TileX, TileY);
        nextFrames.add(new Point(0,0));
	}
	
	public static BufferedImage getFrame() {
		return texture.getSprite(nextFrames.get(0));
	}
 
	public void nextFrame() {
		nextFrames.remove(0);
	}
	
	public void addAnimation(int j, int num) {
		for(int i = 0; i < width; i++) {
			for(int ii = 0; ii < num; ii++) {
				nextFrames.add(new Point(i, j));
			}
		}
	}

	private int getAnimation() {
		return nextFrames.get(0).y;
	}

	public void cancelAnimation(int j) {
		for(int n = 0; n < nextFrames.size(); n++) {
			if(nextFrames.get(n).y == j && nextFrames.size() > 1) {
				nextFrames.remove(n);
			}
		}
	}

	public boolean IsEmpty() {
		return nextFrames.isEmpty();
	}

	public void addDefault() {
		for(int i = 0; i < width; i++) {
			nextFrames.add(new Point(i, 0));
		}
		for(int i = width - 1; i > 0; i--) {
			nextFrames.add(new Point(i, 0));
		}
	}
}
 