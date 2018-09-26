package Music;


import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Main {
	public static Vector<Hint> hints = new Vector<Hint>();
	public static void main(String[] why) {
		hints.add(new Hint("��������� #0","���������� �� ����� �����������","src/Assets/Art/Hint/0.PNG", 280)); //Catapults can't walk on marches
		hints.add(new Hint("��������� #1","�������� ������� ��� �������� ��������� ���������","src/Assets/Art/Hint/1.PNG", 280)); //Spearmen are useful to counter enemy cavalry
		//hints.add(new Hint("��������� #2","������ �������� ������������� �������","src/Assets/Art/Hint/2.PNG", 440));
		//hints.add(new Hint("��������� #3","��������� � ��������� �������� ��� ������ ������","src/Assets/Art/Hint/3.PNG", 280)); //����������� CivIV �� 1�: Sugar = �����
		hints.add(new Hint("��������� #4","��������� ��������� ����, ����� ������� ��������","src/Assets/Art/Hint/4.PNG", 280)); //Axemen excel at hand to hand combat
		hints.add(new Hint("��������� #6","�������� ������ ���������� ���� � �������������","src/Assets/Art/Hint/6.PNG", 280)); //Mailed knights dominate fields during Medieval
		//hints.add(new Hint("��������� #7","������� ����� ���� ����������� � ������� ��������� �������� ","src/Assets/Art/Hint/7.PNG", 280)); //Plate armor provides macemen with strong defensive bonuses
		
		TitleScreen titlescreen = new TitleScreen(hints, 1);
		MiniGame2 p = new MiniGame2();

		JFrame window = new JFrame();
		window.setBounds(0, 0, 990, 660);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		window.getContentPane().add(p);
		window.addKeyListener(p);
		//window.setVisible(true);
	}
}
