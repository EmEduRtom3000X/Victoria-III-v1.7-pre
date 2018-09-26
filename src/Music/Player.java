package Music;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	private static int x;
	private static int y;
	private static int g;
	private static int moveDirection;
	private static final int maxSpeed = 3;
	Animation animation;
	
	public Player(String file, int TileX, int TileY, int Width, int Height) {
		x = 0;
		y = 0;
		g = 0;
		moveDirection = 0;
		animation = new Animation(file, TileX, TileY, Width, Height);
	}
	
	public void addSpeedX(int X) {
		if(moveDirection == 0) {
			animation.cancelAnimation(0);
		}
		moveDirection += X;
		if(moveDirection > maxSpeed) {
			moveDirection = maxSpeed;
		}
		if(moveDirection < -maxSpeed) {
			moveDirection = -maxSpeed;
		}
	}
	
	public void setSpeedX(int X) {
		moveDirection = X;
		animation.cancelAnimation(1);
	}
	
	public BufferedImage getSprite() {
		animation.nextFrame();
		if(animation.IsEmpty()) {
			if(moveDirection > 0) {
				animation.addAnimation(1, 2);
				System.out.println("asd");
			} else {
				animation.addDefault();
				System.out.println("fgh");
			}
		}
		return animation.getFrame();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void updateGravity() {
		if(y < 360) {
			g += 5;
			y += g;
			if(y > 360) {
				y = 360;
			}
		} else if (g >= 0) {
			g = 0;
		} else {
			y += g;
		}
		
		x += moveDirection * 4;
	}

	public void jump(int Jump) {
		g = - 5 * Jump;
	}
}
