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
		//Здесь был Саша Яворский. Проверка связи:)
		hints.add(new Hint("Ïîäñêàçêà #0","Êàòàïóëüòà íå ìîæåò ìàðøèðîâàòü","src/Assets/Art/Hint/0.PNG", 280)); //Catapults can't walk on marches
		hints.add(new Hint("Ïîäñêàçêà #1","Êîïåéùèê ïîëåçåí äëÿ ïîäñ÷åòà âðàæåñêîé êàâàëåðèè","src/Assets/Art/Hint/1.PNG", 280)); //Spearmen are useful to counter enemy cavalry
		//hints.add(new Hint("Ïîäñêàçêà #2","Íåëüçÿ çàáûâàòü ñóáñèäèðîâàòü ôàáðèêè","src/Assets/Art/Hint/2.PNG", 440));
		//hints.add(new Hint("Ïîäñêàçêà #3","Ïëàíòàöèè è êàëåíäàðü èäåàëüíû äëÿ äîáû÷è ÷óãóíà","src/Assets/Art/Hint/3.PNG", 280)); //Ëîêàëèçàöèÿ CivIV îò 1Ñ: Sugar = ÷óãóí
		hints.add(new Hint("Ïîäñêàçêà #4","Òîïîðùèêó ïðåêðàñíà ðóêà, ÷òîáû âðó÷èòü ñðàæåíèå","src/Assets/Art/Hint/4.PNG", 280)); //Axemen excel at hand to hand combat
		hints.add(new Hint("Ïîäñêàçêà #6","Ïî÷òîâûå ðûöàðè äîìèíèðóþò ïîëÿ â Ñðåäíåâåêîâüå","src/Assets/Art/Hint/6.PNG", 280)); //Mailed knights dominate fields during Medieval
		//hints.add(new Hint("Ïîäñêàçêà #7","Òàðåëêà áðîíè äàåò áóëàâîíîñöà ñ ìîùíûìè çàùèòíûìè áîíóñàìè ","src/Assets/Art/Hint/7.PNG", 280)); //Plate armor provides macemen with strong defensive bonuses
		
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
