package Music;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MiniGame2 extends JPanel implements ActionListener, KeyListener
{
	private Player Moltke;
    private static Timer timerAnimation;
	
	MiniGame2() {
		//setFocusable(true);
		setBackground(Color.ORANGE);
		setSize(990, 660);
		Moltke = new Player("src/Assets/Art/MiniGame2/Moltke.PNG", 220, 240, 5, 1);
		timerAnimation = new Timer(50, this);
		timerAnimation.start();
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Toolkit.getDefaultToolkit().sync();
		g.drawImage(Moltke.getSprite(), Moltke.getX(), Moltke.getY(), this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		    if (e.getSource() == timerAnimation) {
			    repaint();
			    Moltke.updateGravity();
		    } 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			Moltke.jump(6);
		}
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			Moltke.addSpeedX(-1);
		}
		if(e.getKeyCode() == KeyEvent.VK_S)
		{
			Moltke.setSpeedX(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			Moltke.addSpeedX(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}