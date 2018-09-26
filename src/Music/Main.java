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
		hints.add(new Hint("Подсказка #0","Катапульта не может маршировать","src/Assets/Art/Hint/0.PNG", 280)); //Catapults can't walk on marches
		hints.add(new Hint("Подсказка #1","Копейщик полезен для подсчета вражеской кавалерии","src/Assets/Art/Hint/1.PNG", 280)); //Spearmen are useful to counter enemy cavalry
		//hints.add(new Hint("Подсказка #2","Нельзя забывать субсидировать фабрики","src/Assets/Art/Hint/2.PNG", 440));
		//hints.add(new Hint("Подсказка #3","Плантации и календарь идеальны для добычи чугуна","src/Assets/Art/Hint/3.PNG", 280)); //Локализация CivIV от 1С: Sugar = чугун
		hints.add(new Hint("Подсказка #4","Топорщику прекрасна рука, чтобы вручить сражение","src/Assets/Art/Hint/4.PNG", 280)); //Axemen excel at hand to hand combat
		hints.add(new Hint("Подсказка #6","Почтовые рыцари доминируют поля в Средневековье","src/Assets/Art/Hint/6.PNG", 280)); //Mailed knights dominate fields during Medieval
		//hints.add(new Hint("Подсказка #7","Тарелка брони дает булавоносца с мощными защитными бонусами ","src/Assets/Art/Hint/7.PNG", 280)); //Plate armor provides macemen with strong defensive bonuses
		
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
