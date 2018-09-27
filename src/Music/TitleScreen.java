package Music;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import javafx.embed.swing.JFXPanel;

public class TitleScreen extends JFrame implements ActionListener {

	private static String alfavit = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя     ";
	private static String alphabet = "qwertyuioplkjhgfdsazxcvbnm ";
	private static Vector<Hint> hints = new Vector<Hint>();
	private static JButton option1 = new JButton("Историческое начало");
	private static JButton option2 = new JButton("Настраиваемое начало");
	private static JButton option3 = new JButton("Руководство");
	private static JButton option4 = new JButton("Выход");
	private static JLabel head = new JLabel("ВИКТОРИЯ ТРИ");
	private static JLabel reception1 = new JLabel("<html>Самая ожидаемая глобальная стратегия тысячелетия</html>");
	private static JLabel author1 = new JLabel("<html>Всемирно известный канцлер, <br> Отто фон Леопольд Бисмарк</html>");
	private static JLabel mark1 = new JLabel("2.718284590452353602874713526624977572/3");
	private static JLabel reception2 = new JLabel("<html>Однажды утром я проснулся, почесал за ухом, оделся, умылся и сел завтракать. Сначала я откусил небольшой кусок от лежавшего прямо передо мной сочного бутерброда с колбасой, кашей и огурцами. Его вкус был воистину незабываем, я внезапно почувствовал, как все мое тело наполняет невиданный заряд энергии, способный обеспечить меня бодростью на весь последующий день. Я отодвинул стул от стола, встал со стула, после чего задвинул стул обратно под стол. Все системы моего организма работают удивительно слаженно, они привыкли к взаимодействию за долгие годы существования моего бренного тела. По пищеводу в желудок спешат-спешат частично переваренный кусок бутерброда с колбасой, кашей и огурцами и стакан с водой, там их ожидают выработанные заранее, специально к их прибытию, пищеварительные соки. Лишь одинокие мои ноги медленно совершают хилые шаги по дороге к православному храму...</html>");
	private static JLabel author2 = new JLabel("<html>Его Святейшество, кандидат юридических наук<br>патриарх Алексий I</html>");
	private static JLabel mark2 = new JLabel("2.718284590452353602874713526624977572/3");
	private static JLabel background = new JLabel();
	private static JLabel protagonist = new JLabel();
	private static JLabel stats = new JLabel();
	private static JLabel cursor = new JLabel();
	private static Timer timer_title;
	private static Timer timer_select;
	private static Timer timer_minigame0;
	private static Timer timer_minigame1;
	private static Timer timer_minigame2;
	private int score = new Integer(0);
	private int action = new Integer(0);
	private int whatsgoingon = new Integer(0);
	private int animation_status = new Integer(0);
	private int epilepsy_power = new Integer(1);
	private static JLabel warning = new JLabel("предупреждение");
	private static JLabel random1 = new JLabel("");
	private static JLabel random2 = new JLabel("");
	private static JLabel random3 = new JLabel("");
	private static JLabel words[] = new JLabel[600];
	private static JLabel background_animation[] = new JLabel[50];
	private static int misc[] = new int[150];
	private static boolean german[] = new boolean[150];
	private static boolean french[] = new boolean[150];
	private static JLabel actor[] = new JLabel[4];
	private static int trumpet = new Integer(0);
	private static int matrix[][] = new int[30][20];
	private static int posX = new Integer(0);
	private static int posY = new Integer(0);
	private static ArrayList<Point> keyXY = new ArrayList<Point>();
	private static ArrayList<Point> moveQueue = new ArrayList<Point>();
	private static boolean fishIsChasing = new Boolean(false);
	private int fishAction = new Integer(0);
	private static boolean dangerPlaying = new Boolean(false);
	private int protagonistSize = new Integer(0);
	private int difficulty = new Integer(0);
	private MP3Player title_player = new MP3Player();
	private MP3Player danger_player = new MP3Player();

	private Action ActW = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			switch (whatsgoingon) {
			case 2:
				if (posY > 0 && action == 0) {
					if (matrix[posX][posY - 1] != 0) {
						posY--;
						action = difficulty;
						checkKeys(matrix);
						labRevealOnGo(matrix);
					}
				}
				break;
			}
		}
	};

	private Action ActA = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			switch (whatsgoingon) {
			case 2:
				if (posX > 0 && action == 0) {
					if (matrix[posX - 1][posY] != 0) {
						posX--;
						action = difficulty;
						checkKeys(matrix);
						labRevealOnGo(matrix);
					}
				}
				break;
			}
		}
	};

	private Action ActX = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			switch (whatsgoingon) {
			case 2:
				if (posY < 19 && action == 0) {
					if (matrix[posX][posY + 1] != 0) {
						posY++;
						action = difficulty;
						checkKeys(matrix);
						labRevealOnGo(matrix);
					}
				}
				break;
			}
		}
	};

	private Action ActD = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			switch (whatsgoingon) {
			case 2:
				if (posX < 29 && action == 0) {
					if (matrix[posX + 1][posY] != 0) {
						posX++;
						action = difficulty;
						checkKeys(matrix);
						labRevealOnGo(matrix);
					}
				}
				break;
			}
		}
	};

	private Action ActS = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			switch (whatsgoingon) {
			case 2:
				System.out.println("suicide!");
				danger_player.close();
				dangerPlaying = false;
				animation_status = -121;
				break;
			case 3:
				action++;
				action %= 2;
				break;
			}
		}
	};

	private void checkKeys(int[][] matrix) {
		if (matrix[posX][posY] == 3) {
			matrix[posX][posY] = 1;
			int randPick = rand(0, 8);
			words[posY + posX * 20]
					.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame0/Way" + randPick + ".PNG")));
			keyXY.remove(keyXY.indexOf(new Point(posX, posY)));
			System.out.println(keyXY.size());
			checkSize();
		}
	}

	private void checkSize() {
		resizeImage(protagonist, 1, 1);
		resizeImage(actor[0], 1, 1);
		protagonistSize++;
	}

	protected void labRevealOnGo(int[][] matrix) {
		for (int j = posY; j <= 19; j++) {
			if (matrix[posX][j] != 0) {
				words[j + 20 * posX].setVisible(true);
			} else {
				break;
			}
		}
		for (int j = posY; j >= 0; j--) {
			if (matrix[posX][j] != 0) {
				words[j + 20 * posX].setVisible(true);
			} else {
				break;
			}
		}
		for (int i = posX; i <= 29; i++) {
			if (matrix[i][posY] != 0) {
				words[posY + 20 * i].setVisible(true);
			} else {
				break;
			}
		}
		for (int i = posX; i >= 0; i--) {
			if (matrix[i][posY] != 0) {
				words[posY + 20 * i].setVisible(true);
			} else {
				break;
			}
		}
	}

	public static BufferedImage resize(BufferedImage image, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
		Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		g.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return scaledBI;
	}

	public static void negative(JLabel jlabel) {
		ImageIcon icon = new ImageIcon();
		icon = (ImageIcon) jlabel.getIcon();
		BufferedImage image = new BufferedImage(990, 660, BufferedImage.TYPE_INT_ARGB);
		image = (BufferedImage) icon.getImage();
		int w = image.getWidth();
		int h = image.getHeight();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int p = image.getRGB(i, j);
				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;
				r = 255 - r;
				g = 255 - g;
				b = 255 - b;
				p = (a << 24) | (r << 16) | (g << 8) | b;
				image.setRGB(i, j, p);
			}
		}
		jlabel.setIcon(new ImageIcon(image));
	}

	public static void mutateRGB(JLabel jlabel) {
		ImageIcon icon = new ImageIcon();
		icon = (ImageIcon) jlabel.getIcon();
		BufferedImage image = new BufferedImage(990, 660, BufferedImage.TYPE_INT_ARGB);
		image = (BufferedImage) icon.getImage();
		int w = image.getWidth();
		int h = image.getHeight();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int p = image.getRGB(i, j);
				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;
				r += rand(-2, 3);
				r %= 255;
				g += rand(-2, 3);
				g %= 255;
				b += rand(-2, 3);
				b %= 255;
				p = (a << 24) | (r << 16) | (g << 8) | b;
				image.setRGB(i, j, p);
			}
		}
		jlabel.setIcon(new ImageIcon(image));
	}

	TitleScreen(Vector<Hint> _hints, int _epilepsy_power) {
		JFXPanel panel = new JFXPanel();
		this.setResizable(false);
		hints.addAll(_hints);
		epilepsy_power = _epilepsy_power;
		setTitle("ВИКТОРИЯ ТРИ");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			File file = new File("src/Assets/Art/Title.PNG");
			BufferedImage image = ImageIO.read(file);
			background = new JLabel(new ImageIcon(image));

			getContentPane().add(cursor);
			getContentPane().add(head);
			getContentPane().add(reception1);
			getContentPane().add(author1);
			getContentPane().add(mark1);
			getContentPane().add(reception2);
			getContentPane().add(author2);
			getContentPane().add(mark2);
			getContentPane().add(option1);
			getContentPane().add(option2);
			getContentPane().add(option3);
			getContentPane().add(option4);
			getContentPane().add(warning);
			getContentPane().add(random1);
			getContentPane().add(random2);
			getContentPane().add(random3);
			getContentPane().add(stats);
			for (int i = 0; i < 3; i++) {
				actor[i] = new JLabel();
				getContentPane().add(actor[i]);
			}
			getContentPane().add(protagonist);
			for (int i = 0; i < 600; i++) {
				words[i] = new JLabel();
				getContentPane().add(words[i]);
			}
			getContentPane().add(background);
			for (int i = 3; i < 4; i++) {
				actor[i] = new JLabel();
				getContentPane().add(actor[i]);
			}
			for (int i = 0; i < 50; i++) {
				background_animation[i] = new JLabel();
				getContentPane().add(background_animation[i]);
			}
			option1.addActionListener(this);
			option2.addActionListener(this);
			option3.addActionListener(this);
			option4.addActionListener(this);
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
		setVisible(true);
		setLocation(60, 60);
		setSize(990, 660);

		timer_title = new Timer(100, this);
		timer_title.start();

		protagonist.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "Wpressed");
		protagonist.getActionMap().put("Wpressed", ActW);

		protagonist.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "Apressed");
		protagonist.getActionMap().put("Apressed", ActA);

		protagonist.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("X"), "Xpressed");
		protagonist.getActionMap().put("Xpressed", ActX);

		protagonist.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "Dpressed");
		protagonist.getActionMap().put("Dpressed", ActD);

		protagonist.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "Spressed");
		protagonist.getActionMap().put("Spressed", ActS);
	}

	public void hideAll() {
		cursor.setVisible(false);
		head.setVisible(false);
		reception1.setVisible(false);
		author1.setVisible(false);
		mark1.setVisible(false);
		reception2.setVisible(false);
		author2.setVisible(false);
		mark2.setVisible(false);
		option1.setVisible(false);
		option2.setVisible(false);
		option3.setVisible(false);
		option4.setVisible(false);
		warning.setVisible(false);
		random1.setVisible(false);
		random2.setVisible(false);
		random3.setVisible(false);
		stats.setVisible(false);
		for (int i = 0; i < 3; i++) {
			actor[i].setVisible(false);
		}
		protagonist.setVisible(false);
		for (int i = 0; i < 600; i++) {
			words[i].setText(null);
			words[i].setIcon(null);
			words[i].setVisible(false);
		}
		background.setVisible(false);
		for (int i = 3; i < 4; i++) {
			actor[i].setVisible(false);
		}
		for (int i = 0; i < 50; i++) {
			background_animation[i].setVisible(false);
		}
	}

	public void hideMap() {
		for (int i = 0; i < 600; i++) {
			words[i].setVisible(false);
		}
	}

	public void actionPerformed(ActionEvent e) {
		switch (whatsgoingon) {
		case 0:
			if (e.getSource() == option1) {
				System.out.println("hstart");
				timer_title.stop();
				timer_select = new Timer(100, this);
				timer_select.start();
				animation_status = 0;
				whatsgoingon = 1;
			} else if (e.getSource() == option2) {
				System.out.println("cstart");
			} else if (e.getSource() == option3) {
				System.out.println("tutorial");
			} else if (e.getSource() == option4) {
				System.out.println("exit");
			}
			break;
		case 1:
			if (e.getSource() == option1) {
				System.out.println("hstart");
				timer_select.stop();
				timer_minigame0 = new Timer(100, this);
				timer_minigame0.start();
				animation_status = 0;
				whatsgoingon = 2;
			} else if (e.getSource() == option2) {
				System.out.println("cstart");
				timer_select.stop();
				timer_minigame1 = new Timer(100, this);
				timer_minigame1.start();
				animation_status = 0;
				action = 1;
				whatsgoingon = 3;
			} else if (e.getSource() == option3) {
				System.out.println("tutorial");
				timer_select.stop();
				timer_minigame2 = new Timer(100, this);
				timer_minigame2.start();
				animation_status = 0;
				whatsgoingon = 4;
			} else if (e.getSource() == option4) {
				System.out.println("exit");
			}
			break;
		case 2:
			break;
		case 3:
			break;
		}
		if (e.getSource() == timer_title) {
			animation_status++;
			if (animation_status == 1) {
				hideAll();
				System.out.println("music " + animation_status);
				playMusic(title_player, "src/Assets/Music/Title.mp3", 50);
				timer_title.setDelay(15); // 15
			} else if (animation_status < 41) {
				System.out.println("starting " + animation_status);
				int df = new Integer(rand(0, 33));
				warning.setVisible(true);
				warning.setFont(new Font("Serif", Font.ITALIC | Font.ROMAN_BASELINE, 104 + df));
				warning.setBounds(84 - 3 * df, rand(-60, 48), 1320, 660);
				warning.setForeground(new Color(rand(160, 256), rand(160, 256), rand(160, 256)));
				getContentPane().setBackground(new Color(rand(0, 48), rand(0, 48), rand(0, 48)));
			} else if (animation_status == 41) {
				timer_title.setDelay(45); // 45
			} else if (animation_status < 61) {
				System.out.println("starting " + animation_status);
				int df = new Integer(rand(0, 33));
				warning.setVisible(true);
				warning.setFont(new Font("Serif", Font.ITALIC | Font.CENTER_BASELINE, 104 + df));
				warning.setBounds(84 - 3 * df, rand(-60, 48), 1320, 660);
				warning.setForeground(new Color(rand(160, 256), rand(160, 256), rand(160, 256)));
				getContentPane().setBackground(new Color(rand(0, 48), rand(0, 48), rand(0, 48)));
			} else if (animation_status == 61) {
				int determinator = new Integer(rand(0, hints.size()));
				System.out.println(determinator);
				warning.setVisible(false);
				random1.setVisible(true);
				random2.setVisible(true);
				random3.setVisible(true);
				random1.setFont(new Font("Bank Gothic", Font.ITALIC | Font.BOLD, rand(40, 49)));
				random1.setBounds(350, -45, 420, 140);
				random1.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				random1.setText(hints.get(determinator).getHead());
				random2.setFont(new Font("Bank Gothic", Font.ITALIC | Font.BOLD, rand(16, 21)));
				random2.setBounds(250 + rand(-150, 151), 75 + hints.get(determinator).getSize() + rand(-50, 1), 840,
						140);
				random2.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				random2.setText(hints.get(determinator).getDesc());
				File file = new File(hints.get(determinator).getPath());
				try {
					BufferedImage image = ImageIO.read(file);
					random3.setIcon(new ImageIcon(image));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				random3.setBounds(200 + rand(-150, 151), 95, hints.get(determinator).getSize() * 3 / 2,
						hints.get(determinator).getSize());
				getContentPane().setBackground(new Color(rand(0, 48), rand(0, 48), rand(0, 48)));
				timer_title.setDelay(50); // 50
			} else if (animation_status < 121) {
				random1.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				random2.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
			} else if (animation_status == 121) {
				System.out.println("initializing");
				head.setVisible(true);
				reception1.setVisible(true);
				author1.setVisible(true);
				mark1.setVisible(true);
				reception2.setVisible(true);
				author2.setVisible(true);
				mark2.setVisible(true);
				option1.setVisible(true);
				option2.setVisible(true);
				option3.setVisible(true);
				option4.setVisible(true);
				warning.setVisible(false);
				random1.setVisible(false);
				random2.setVisible(false);
				random3.setVisible(false);
				background.setVisible(true);
				getContentPane().setBackground(new Color(255, 255, 255));
				timer_title.setDelay(100);
			} else if (animation_status % epilepsy_power != 1) {
				int dx = new Integer(rand(-9, 10));
				int dy = new Integer(rand(-5, 6));
				reception1.setBounds(300 + dx, dy, 360, 30);
				author1.setBounds(425 + dx, 35 + dy, 360, 30);
				mark1.setBounds(325 + dx, 15 + dy, 360, 30);
				reception2.setBounds(360 + dx, 65 + dy, 360, 270);
				author2.setBounds(405 + dx, 350 + dy, 360, 30);
				mark2.setBounds(425 + dx, 330 + dy, 360, 30);
				head.setFont(new Font("Times New Roman", 1, rand(30, 35)));
				head.setBounds(705 + rand(-5, 11), 30 + rand(-5, 6), 300, 60);
				head.setForeground(new Color(rand(208, 248), rand(208, 248), rand(208, 248)));
				option1.setForeground(new Color(rand(0, 129), rand(0, 129), rand(0, 129)));
				option2.setForeground(new Color(rand(0, 129), rand(0, 129), rand(0, 129)));
				option3.setForeground(new Color(rand(0, 129), rand(0, 129), rand(0, 129)));
				option4.setForeground(new Color(rand(0, 129), rand(0, 129), rand(0, 129)));
				option1.setBounds(740 + rand(0, 21), 118 + rand(0, 5), 210, 45);
				option2.setBounds(740 + rand(0, 21), 178 + rand(0, 5), 210, 45);
				option3.setBounds(740 + rand(0, 21), 238 + rand(0, 5), 210, 45);
				option4.setBounds(740 + rand(0, 21), 538 + rand(0, 5), 210, 45);
				reception1.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				author1.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				mark1.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				reception2.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				author2.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				mark2.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				option1.setFont(new Font("Arial", 1, rand(14, 16)));
				option2.setFont(new Font("Arial", 1, rand(14, 16)));
				option3.setFont(new Font("Arial", 1, rand(14, 16)));
				option4.setFont(new Font("Arial", 1, rand(14, 16)));
				background.setBounds(rand(-6, -3), rand(-6, -3), 990 + rand(8, 13), 660 + rand(8, 13));
			} else {
				System.out.println("epliepsy time");
			}
		}
		if (e.getSource() == timer_select) {
			animation_status++;
			if (animation_status == 1) {
				hideAll();
				setTitle("ЭКРАН ВЫБОРА");
				background.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/Select.PNG")));
				background.setVisible(true);
				head.setText("<html>Стартовое Событие Начала</html>");
				head.setBounds(0, -30, 180, 150);
				head.setVisible(true);
				option1.setForeground(new Color(0, 0, 0));
				option2.setForeground(new Color(0, 0, 0));
				option3.setForeground(new Color(0, 0, 0));
				option4.setForeground(new Color(0, 0, 0));
				option1.setBounds(180, 0, 210, 45);
				option2.setBounds(180, 45, 210, 45);
				option3.setBounds(180, 90, 210, 45);
				option4.setBounds(180, 135, 210, 45);
				option1.setText("Мощная рыба 2");
				option2.setText("Космическое пузо");
				option3.setText("Мощная рыба");
				option4.setText("Пожиратель мух");
				option1.setVisible(true);
				option2.setVisible(true);
				option3.setVisible(true);
				option4.setVisible(true);
				timer_select.setDelay(80);
			} else if (animation_status % 2 == 0) {
				System.out.println(animation_status);
				head.setFont(new Font("Times New Roman", 1, rand(24, 29)));
				option1.setBounds(180, rand(-5, 6), 210, 35);
				option2.setBounds(180, rand(40, 51), 210, 35);
				option3.setBounds(180, rand(85, 96), 210, 35);
				option4.setBounds(180, rand(130, 141), 210, 35);
			} else if (animation_status % 2 == 1) {
				System.out.println(animation_status);
				head.setFont(new Font("Arial", 1, rand(24, 29)));
				head.setForeground(new Color(rand(208, 248), rand(208, 248), rand(0, 40)));
				option1.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				option2.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				option3.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
				option4.setForeground(new Color(rand(0, 256), rand(0, 256), rand(0, 256)));
			}
		}
		if (e.getSource() == timer_minigame0) {
			animation_status++;
			if (animation_status == 1) {
				hideAll();
				setTitle("СПРЯЧЬ ПИСЬМО ЛЕОПОЛЬДА ОТ НАПОЛЕОНА");
				timer_minigame0.setDelay(50);
				playMusic(danger_player, "src/Assets/Music/Danger0.mp3", 40);
				danger_player.close();
				initMiniGame0();
			} else if (animation_status == -319) {
				hideAll();
				title_player.close();
				playMusic(title_player, "src/Assets/Music/Victory0.mp3", 75);
				actor[0].setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/Random/3.PNG")));
				actor[0].setBounds(0, 0, 810, 540);
				actor[0].setVisible(true);
			} else if (animation_status < -221) {
				head.setBounds(rand(0, 100), 100, 600, 600);
				head.setText("ПОБЕДА");
				head.setFont(new Font("Times New Roman", 1, rand(80, 117)));
				head.setForeground(new Color(0, rand(0, 255), rand(0, 255)));
				head.setVisible(true);
				actor[0].setLocation(rand(0, 180), rand(0, 120));
			} else if (animation_status == -219) {
				hideAll();
				actor[0].setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/Random/1.PNG")));
				actor[0].setBounds(0, 0, 590, 590);
				actor[0].setVisible(true);
				title_player.close();
				playMusic(title_player, "src/Assets/Music/Nile.mp3", 40);
			} else if (animation_status == -221) {
				initMiniGame1();
				System.out.println("cstart");
				timer_select.stop();
				timer_minigame1 = new Timer(100, this);
				timer_minigame1.start();
				animation_status = 0;
				action = 1;
				whatsgoingon = 3;
			} else if (animation_status < -121) {
				head.setBounds(rand(0, 100), 100, 900, 100);
				head.setText("Ваша экспедиция обнаружила исток Нила");
				head.setFont(new Font("Times New Roman", 1, rand(24, 31)));
				head.setForeground(new Color(rand(0, 255), rand(0, 255), 0));
				head.setVisible(true);
				reception1.setBounds(rand(0, 100), 300, 900, 100);
				reception1.setText("Вы получаете 100 перстижа");
				reception1.setFont(new Font("Comic Sans", 1, rand(20, 29)));
				reception1.setForeground(new Color(rand(0, 255), 0, rand(0, 255)));
				reception1.setVisible(true);
				actor[0].setLocation(rand(0, 400), rand(0, 70));
			} else if (animation_status == -119) {
				title_player.close();
				playMusic(title_player, "src/Assets/Music/GameOver0.mp3", 40);
				hideAll();
			} else if (animation_status == 0 || animation_status == -121) {
				initMiniGame0();
			} else if (animation_status < 0) {

			} else if (animation_status == 1295) {
				animation_status = -121;
				danger_player.close();
				dangerPlaying = false;
			} else {
				getContentPane()
						.setBackground(new Color(rand(0, animation_status / 6 + 1), rand(29, 70), rand(63, 104)));
				protagonist.setLocation(42 + 30 * posX - protagonistSize + rand(-2, 3),
						16 + 30 * posY - protagonistSize + rand(-2, 3));
				processMiniGame0();
			}
		}
		if (e.getSource() == timer_minigame1) {
			animation_status++;
			if (animation_status == 1) {
				hideAll();
				setTitle("ПОМОГИ БИСМАРКУ ОРГАНИЗОВАТЬ ФРАНКО-ПРУССКУЮ ВОЙНУ");
				background.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame1/Background.PNG")));
				background.setVisible(true);
				background.setBounds(0, 0, 990, 660);
				protagonist.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame1/Bismarck.PNG")));
				protagonist.setBounds(-60, 110, 400, 550);
				protagonist.setVisible(true);
				stats.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame1/Poem.PNG")));
				stats.setBounds(270, 110, 200, 330);
				stats.setVisible(true);
				cursor.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame1/Hand.PNG")));
				cursor.setBounds(0, 0, 0, 0);
				cursor.setVisible(true);
				timer_minigame1.setDelay(80);
				initMiniGame1();
			} else if (MiniGame1IsActive() && animation_status < 999999) {
				processMiniGame1();
			} else if (animation_status == 2000000) {
				hideAll();
				head.setBounds(400, 300, 600, 100);
				head.setText("Победа!");
				head.setFont(new Font("Times New Roman", 1, rand(40, 57)));
				head.setForeground(new Color(0, 255, 255));
				head.setVisible(true);
			} else if (score == 20) {
				animation_status = 1999999;
			} else if (animation_status < 1000000) {
				animation_status = 999999;
			} else if (animation_status == 1000000) {
				hideAll();
				head.setBounds(100, 100, 600, 100);
				head.setText("Спокойной ночи!");
				head.setFont(new Font("Times New Roman", 1, rand(40, 57)));
				head.setForeground(new Color(255, 255, 0));
				head.setVisible(true);
			} else if (animation_status == 1000033) {
				animation_status = 0;
				initMiniGame1();
			}
		}

		if (e.getSource() == timer_minigame2) {
			animation_status++;
			if (animation_status == 1) {
				hideAll();
				setTitle("ВМЕСТЕ С ВИЛЬГЕЛЬМОМ ПОЗОВИ НЕМЦЕВ ВОЕВАТЬ");
				background.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame3/Neuron.PNG")));
				background.setVisible(true);
				background.setBounds(0, 0, 990, 660);
				protagonist.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame3/Willy.PNG")));
				protagonist.setBounds(100, 100, 1480, 2030);
				resizeImage(protagonist, -1280, -1730);
				protagonist.setVisible(true);
				stats.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame3/TrafficLight.PNG")));
				stats.setBounds(270, 110, 200, 330);
				stats.setVisible(true);
				cursor.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame3/Monument.PNG")));
				cursor.setBounds(0, 0, 0, 0);
				cursor.setVisible(true);
				timer_minigame2.setDelay(25);
			} else {
				if (rand(0, 8) == 0) {
					resizeandreplaceImage(protagonist, "src/Assets/Art/MiniGame3/Willy.PNG", 4, -1); // 12.5%
																										// probability
				} else {
					resizeImage(protagonist, 4, 1);
				}
				if (rand(0, 10) == 0) {
					mutateRGB(background); // 10% probability
				}
			}
		}
	}

	private void resizeandreplaceImage(JLabel label, String path, int dx, int dy) {
		ImageIcon icon = new ImageIcon();
		icon = (ImageIcon) label.getIcon();
		BufferedImage image = new BufferedImage(990, 660, BufferedImage.TYPE_INT_ARGB);
		BufferedImage imageOLD = new BufferedImage(990, 660, BufferedImage.TYPE_INT_ARGB);
		image = getImagefromFile(path);
		imageOLD = (BufferedImage) icon.getImage();
		image = resize(image, Math.max(4, imageOLD.getWidth() + dx), Math.max(4, imageOLD.getHeight() + dy), false);
		label.setIcon(new ImageIcon(image));
		label.setBounds(label.getBounds().x, label.getBounds().y, label.getBounds().width + dx,
				label.getBounds().height + dy);
	}

	private void processMiniGame0() {
		if (action > 0) {
			action--;
		}
		// fish
		// check for chasing player
		if (!fishIsChasing) {
			if (fishAction > 2) {
				fishMove();
				fishCheckDirections();
				// check if starting chasing player
				if (fishPlayerInSight(matrix)) {
					// Takes time to locate player, depending on his size
					fishAction = -9 + protagonistSize;
					moveQueue.add(1, new Point(posX, posY));
					moveQueue.remove(2);
					fishIsChasing = true;
				} else if (moveQueue.get(0).x == moveQueue.get(1).x && moveQueue.get(0).y == moveQueue.get(1).y) {
					moveQueue.remove(0);
					moveQueue.add(fishRandPath(matrix));
					System.out.println(moveQueue.get(0));
				}
				if (fishAction > 0) {
					fishAction = 0;
				}
			} else {
				fishAction++;
			}
		} else {
			if (fishAction > 1) { // faster when chasing
				fishMove();
				// no directions check
				// check for escaping player
				if (fishPlayerInSight(matrix)) {
					moveQueue.add(1, new Point(posX, posY));
					moveQueue.remove(2);
					fishIsChasing = true;
				} else {
					fishIsChasing = false;
					if (moveQueue.get(0).x == moveQueue.get(1).x && moveQueue.get(0).y == moveQueue.get(1).y) {
						moveQueue.remove(0);
						moveQueue.add(fishRandPath(matrix));
						System.out.println(moveQueue.get(0));
					}
				}
				if (fishAction > 0) {
					fishAction = 0;
				}
			} else {
				fishAction++;
			}
		}
		actor[0].setLocation(moveQueue.get(0).x * 30 + 42 - protagonistSize,
				moveQueue.get(0).y * 30 + 16 - protagonistSize);
		// end fish

		// check if the fish is visible
		if (labFishIsVisible() || true) {
			actor[0].setVisible(true);
		} else {
			actor[0].setVisible(false);
			fishAction++;
		}
		// check if danger
		int fishDistance = new Integer((int) (Math.pow(Math.abs(posX - moveQueue.get(0).x), 2)
				+ Math.pow(Math.abs(posX - moveQueue.get(0).x), 2)));
		if (fishDistance <= 25 || actor[0].isVisible()) {
			if (!dangerPlaying) {
				danger_player.close();
				playMusic(danger_player, "src/Assets/Music/Danger0.mp3", 40);
				dangerPlaying = true;
				background.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/Random/2.PNG")));
				background.setVisible(true);
			}
			background.setBounds(rand(-60, 120), rand(-30, 60), 836, 600);
			danger_player.setVolume((float) (-2 * Math.sqrt(fishDistance) + 100));
		} else {
			background.setVisible(false);
			danger_player.close();
			dangerPlaying = false;
		}
		title_player.setVolume((float) (100 - 3 * (5 - Math.min(Math.sqrt(fishDistance), 5))));
		// check if lost
		if (posX == moveQueue.get(0).x && posY == moveQueue.get(0).y && matrix[posX][posY] != 2) {
			animation_status = -121;
			danger_player.close();
			dangerPlaying = false;
		}
		// check for victory
		if (matrix[posX][posY] == 4) {
			if (keyXY.size() > 0) {
				// expedition to Nile
				animation_status = -220;
				danger_player.close();
				dangerPlaying = false;
			} else {
				// win
				animation_status = -320;
				danger_player.close();
				dangerPlaying = false;
			}
		}
		System.out.println(animation_status);

		// random things
		int i = new Integer(rand(0, 30));
		int j = new Integer(rand(0, 20));
		// 920 - end 1295
		for (int k = 0; k < 2 + animation_status / 160 + Math.max(0, (animation_status - 880) / 60); k++) {
			while (matrix[i][j] != 1) {
				i = new Integer(rand(0, 30));
				j = new Integer(rand(0, 20));
			}
			int dw = new Integer(rand(-1, 2));
			int dh = new Integer(rand(-1, 2));
			int dx = new Integer(rand(-3, 4));
			int dy = new Integer(rand(-3, 4));
			resizeImage(words[j + i * 20], dw, dh);
			words[j + i * 20].setLocation(i * 30 + 42 + dx, j * 30 + 16 + dy);
		}
		i = new Integer(rand(0, 30));
		j = new Integer(rand(0, 20));
		for (int k = 0; k < Math.max(0, (animation_status - 880) / 40); k++) {
			while (matrix[i][j] != 1) {
				i = new Integer(rand(0, 30));
				j = new Integer(rand(0, 20));
			}
			negative(words[j + i * 20]);
		}
	}

	private boolean labFishIsVisible() {
		if (fishIsChasing) {
			return true;
		} else if (!words[moveQueue.get(0).x * 20 + moveQueue.get(0).y].isVisible()) {
			return false;
		} else {
			boolean isVisible = new Boolean(true);
			if (Math.abs(posY - moveQueue.get(0).y) < 2) {
				for (int i = Math.min(posX, moveQueue.get(0).x); i < Math.max(posX, moveQueue.get(0).x); i++) {
					if (matrix[i][posY] == 0) {
						isVisible = false;
						break;
					}
				}
			} else {
				isVisible = false;
			}
			if (isVisible == true) {
				return true;
			}
			isVisible = true;
			if (Math.abs(posX - moveQueue.get(0).x) < 2) {
				for (int j = Math.min(posY, moveQueue.get(0).y); j < Math.max(posY, moveQueue.get(0).y); j++) {
					if (matrix[posX][j] == 0) {
						isVisible = false;
						break;
					}
				}
			} else {
				isVisible = false;
			}
			if (isVisible == true) {
				return true;
			}
			return false;
		}
	}

	private void fishMove() {
		if (moveQueue.get(0).x > moveQueue.get(1).x) {
			moveQueue.add(0, new Point(moveQueue.get(0).x - 1, moveQueue.get(0).y));
			moveQueue.remove(1);
		}
		if (moveQueue.get(0).x < moveQueue.get(1).x) {
			moveQueue.add(0, new Point(moveQueue.get(0).x + 1, moveQueue.get(0).y));
			moveQueue.remove(1);
		}
		if (moveQueue.get(0).y > moveQueue.get(1).y) {
			moveQueue.add(0, new Point(moveQueue.get(0).x, moveQueue.get(0).y - 1));
			moveQueue.remove(1);
		}
		if (moveQueue.get(0).y < moveQueue.get(1).y) {
			moveQueue.add(0, new Point(moveQueue.get(0).x, moveQueue.get(0).y + 1));
			moveQueue.remove(1);
		}
	}

	private boolean fishPlayerInSight(int[][] matrix) {
		Boolean PlayerinSight = new Boolean(false);
		int i = new Integer(0);
		int j = new Integer(0);
		i = moveQueue.get(0).x;
		j = moveQueue.get(0).y;
		for (; j < 19; j++) {
			if (matrix[i][j + 1] == 0) {
				break;
			} else {
				if (posX == i && posY == j + 1 && matrix[posX][posY] != 2) {
					PlayerinSight = true;
				}
			}
		}
		i = moveQueue.get(0).x;
		j = moveQueue.get(0).y;
		for (; j > 0; j--) {
			if (matrix[i][j - 1] == 0) {
				break;
			} else {
				if (posX == i && posY == j - 1 && matrix[posX][posY] != 2) {
					PlayerinSight = true;
				}
			}
		}
		i = moveQueue.get(0).x;
		j = moveQueue.get(0).y;
		for (; i < 29; i++) {
			if (matrix[i + 1][j] == 0) {
				break;
			} else {
				if (posX == i + 1 && posY == j && matrix[posX][posY] != 2) {
					PlayerinSight = true;
				}
			}
		}
		i = moveQueue.get(0).x;
		j = moveQueue.get(0).y;
		for (; i > 0; i--) {
			if (matrix[i - 1][j] == 0) {
				break;
			} else {
				if (posX == i - 1 && posY == j && matrix[posX][posY] != 2) {
					PlayerinSight = true;
				}
			}
		}
		return PlayerinSight;
	}

	private void fishCheckDirections() {
		if (moveQueue.get(0).x != moveQueue.get(1).x) {
			ArrayList<Point> randPick = new ArrayList<Point>();
			int i = new Integer(0);
			int j = new Integer(0);
			i = moveQueue.get(0).x;
			j = moveQueue.get(0).y;
			for (; j < 19; j++) {
				if (matrix[i][j + 1] == 0) {
					break;
				}
			}
			if (j != moveQueue.get(0).y) {
				randPick.add(new Point(i, j));
			}
			i = moveQueue.get(0).x;
			j = moveQueue.get(0).y;
			for (; j > 0; j--) {
				if (matrix[i][j - 1] == 0) {
					break;
				}
			}
			if (j != moveQueue.get(0).y) {
				randPick.add(new Point(i, j));
			}
			int reluctance = new Integer(rand(0, 2));
			if (reluctance == 0 && randPick.size() > 0) {
				int rand = new Integer(rand(0, randPick.size()));
				moveQueue.add(1, randPick.get(rand));
				moveQueue.remove(2);
			}
		}
		if (moveQueue.get(0).y != moveQueue.get(1).y) {
			ArrayList<Point> randPick = new ArrayList<Point>();
			int i = new Integer(0);
			int j = new Integer(0);
			i = moveQueue.get(0).x;
			j = moveQueue.get(0).y;
			for (; i < 29; i++) {
				if (matrix[i + 1][j] == 0) {
					break;
				}
			}
			if (i != moveQueue.get(0).x) {
				randPick.add(new Point(i, j));
			}
			i = moveQueue.get(0).x;
			j = moveQueue.get(0).y;
			for (; i > 0; i--) {
				if (matrix[i - 1][j] == 0) {
					break;
				}
			}
			if (i != moveQueue.get(0).x) {
				randPick.add(new Point(i, j));
			}
			int reluctance = new Integer(rand(0, 3));
			if (reluctance == 0 && randPick.size() > 0) {
				int rand = new Integer(rand(0, randPick.size()));
				moveQueue.add(1, randPick.get(rand));
				moveQueue.remove(2);
			}
		}
	}

	private Point fishRandPath(int[][] matrix) {
		int i = new Integer(0);
		int j = new Integer(0);
		ArrayList<Point> randPick = new ArrayList<Point>();
		i = moveQueue.get(0).x;
		j = moveQueue.get(0).y;
		for (; j < 19; j++) {
			if (matrix[i][j + 1] == 0) {
				break;
			}
		}
		if (j != moveQueue.get(0).y) {
			randPick.add(new Point(i, j));
		}
		i = moveQueue.get(0).x;
		j = moveQueue.get(0).y;
		for (; j > 0; j--) {
			if (matrix[i][j - 1] == 0) {
				break;
			}
		}
		if (j != moveQueue.get(0).y) {
			randPick.add(new Point(i, j));
		}
		i = moveQueue.get(0).x;
		j = moveQueue.get(0).y;
		for (; i < 29; i++) {
			if (matrix[i + 1][j] == 0) {
				break;
			}
		}
		if (i != moveQueue.get(0).x) {
			randPick.add(new Point(i, j));
		}
		i = moveQueue.get(0).x;
		j = moveQueue.get(0).y;
		for (; i > 0; i--) {
			if (matrix[i - 1][j] == 0) {
				break;
			}
		}
		if (i != moveQueue.get(0).x) {
			randPick.add(new Point(i, j));
		}
		int rand = new Integer(rand(0, randPick.size()));
		return randPick.get(rand);
	}

	private void initMiniGame0() {
		score = 0;
		posX = 29;
		posY = 9;
		action = 0;
		animation_status = 1;
		//
		difficulty = 3; // 3 //4
		//
		hideAll();
		title_player.close();
		playMusic(title_player, "src/Assets/Music/MiniGame0.mp3", 40);
		protagonist.setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame0/Poem.PNG")));
		protagonist.setBounds(0, 0, 30, 30);
		protagonist.setVisible(true);
		protagonistSize = 0;
		// initialize maze
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 20; j++) {
				matrix[i][j] = 0;
			}
		}
		// generate maze
		while (countOfPaths(matrix) < 150 || Lab_exit(matrix, 12)) {
			for (int i = 0; i < 30; i++) {
				for (int j = 0; j < 20; j++) {
					matrix[i][j] = 0;
				}
			}
			Lab_go(matrix, 29, 9, 1, 4096, -1);
		}
		placeHides(matrix, 5);
		placeKeys(matrix, 7); // 7
		// fish - spawns near finish
		fishIsChasing = false;
		moveQueue.clear();
		if (getExit(matrix) == null) {
			moveQueue.add(new Point(29, 9));
			moveQueue.add(new Point(29, 9));
		} else {
			moveQueue.add(new Point(getExit(matrix)));
			moveQueue.add(new Point(getExit(matrix)));
		}
		try {
			File file = new File("src/Assets/Art/MiniGame0/Fish.PNG");
			BufferedImage image = ImageIO.read(file);
			actor[0].setIcon(new ImageIcon(image));
		} catch (IOException e1) {
			System.out.println("Error: " + e1);
		}
		actor[0].setBounds(0, 0, 30, 30);
		actor[0].setVisible(true);
		checkSize();
		// draw maze
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 20; j++) {
				switch (matrix[i][j]) {
				case 0:
					words[j + i * 20].setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame0/Wall.PNG")));
					break;
				case 1:
					int randPick = rand(0, 8);
					words[j + i * 20].setIcon(
							new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame0/Way" + randPick + ".PNG")));
					break;
				case 2:
					words[j + i * 20].setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame0/Hide.PNG")));
					break;
				case 4:
					words[j + i * 20].setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame0/Exit.PNG")));
					break;
				}
				words[j + i * 20].setBounds(i * 30 + 42, j * 30 + 16, 30, 30);
				words[j + i * 20].setVisible(true);
			}
		}
		hideMap();
		labRevealOnGo(matrix);
		protagonist.setVisible(true);
	}

	private BufferedImage getImagefromFile(String string) {
		File file = new File(string);
		BufferedImage image = new BufferedImage(990, 660, BufferedImage.TYPE_INT_ARGB);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	private Point getExit(int[][] matrix) {
		for (int j = 0; j < 20; j++) {
			if (matrix[0][j] != 0) {
				matrix[0][j] = 4;
				return new Point(0, j);
			}
		}
		return null;
	}

	private void placeKeys(int[][] matrix, int count) {
		keyXY.clear();
		while (count > 0) {
			int x = rand(0, 30);
			int y = rand(0, 20);
			if (x != 29 && y != 9 && x != 0) {
				// Not starting position nor exit
				if (matrix[x][y] == 1) {
					count--;
					matrix[x][y] = 3;
					keyXY.add(new Point(x, y));
					int randPick = rand(0, 5);
					words[y + x * 20].setIcon(
							new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame0/Key" + randPick + ".PNG")));
				}
			}
		}
	}

	private void placeHides(int[][] matrix, int count) {
		ArrayList<Rectangle> longestCorridors = getLongestCorridors(matrix, count);
		for (int i = 0; i < longestCorridors.size(); i++) {
			matrix[rand(longestCorridors.get(i).x, longestCorridors.get(i).width + 1)][rand(longestCorridors.get(i).y,
					longestCorridors.get(i).height + 1)] = 2;
		}
	}

	private ArrayList<Rectangle> getLongestCorridors(int[][] matrix, int count) {
		ArrayList<Rectangle> corridors = new ArrayList<Rectangle>();
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		// Vertical corridors
		for (int i = 0; i < 30; i++) {
			int length = new Integer(0);
			for (int j = 0; j < 20; j++) {
				if (matrix[i][j] == 1) {
					length++;
				} else if (length != 0) {
					corridors.add(new Rectangle(i, j - length, i, j - 1));
					lengths.add(length);
					length = 0;
				}
			}
		}
		// Horizontal corridors
		for (int j = 0; j < 20; j++) {
			int length = new Integer(0);
			for (int i = 0; i < 30; i++) {
				if (matrix[i][j] == 1) {
					length++;
				} else if (length != 0) {
					corridors.add(new Rectangle(i - length, j, i - 1, j));
					lengths.add(length);
					length = 0;
				}
			}
		}
		while (corridors.size() > count) {
			int minLength = new Integer(21);
			int minIndex = new Integer(0);
			for (int i = 0; i < lengths.size(); i++) {
				if (minLength > lengths.get(i)) {
					minLength = lengths.get(i);
					minIndex = i;
				}
			}
			corridors.remove(minIndex);
			lengths.remove(minIndex);
		}
		System.out.println(corridors);
		System.out.println(lengths);
		return corridors;
	}

	private boolean Lab_exit(int[][] matrix, int maxLength) {
		boolean hasExit = new Boolean(false);
		boolean hasMultipleExits = new Boolean(false);
		for (int j = 0; j < 20; j++) {
			if (matrix[0][j] == 1) {
				if (hasExit = true) {
					hasMultipleExits = true;
				}
				hasExit = true;
			}
		}
		if (hasMultipleExits) {
			return true;
		}
		if (!hasExit) {
			int length = new Integer(0);
			ArrayList<Integer> possibleEnds = new ArrayList<Integer>();
			for (int j = 0; j < 20; j++) {
				if (roadExists(matrix, j, 12)) {
					possibleEnds.add(j);
				}
			}
			if (possibleEnds.size() == 0) {
				return true;
			}
			int randPick = new Integer(rand(0, possibleEnds.size()));
			createExit(matrix, possibleEnds.get(randPick));
		}
		return false;
	}

	private void createExit(int[][] matrix, int startY) {
		for (int i = 0; i < 30; i++) {
			if (matrix[i][startY] != 1) {
				matrix[i][startY] = 1;
			} else {
				break;
			}
		}
	}

	private boolean roadExists(int[][] matrix, int startY, int length) {
		boolean roadExists = new Boolean(false);
		for (int i = 0; i < 30 || length > 0; i++) {
			if (matrix[i][startY] == 1) {
				roadExists = true;
				break;
			}
			if (startY > 0) {
				if (matrix[i][startY - 1] == 1) {
					roadExists = false;
					break;
				}
			}
			if (startY < 19) {
				if (matrix[i][startY + 1] == 1) {
					roadExists = false;
					break;
				}
			}
			length--;
		}
		return roadExists;
	}

	private int countOfPaths(int matrix[][]) {
		int countOfPaths = new Integer(0);
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 20; j++) {
				countOfPaths += matrix[i][j];
			}
		}
		return countOfPaths;
	}

	private void Lab_go(int[][] matrix, int startX, int startY, int direction, int strength, int tiredness) {
		int depth = new Integer(1);
		switch (direction) {
		case 0: // Right
			for (int i = startX; i < 30; i++) {
				matrix[i][startY] = 1;
				if (i < 28) {
					if (matrix[i + 2][startY] == 1) {
						System.out.println("Avoided");
						break;
					}
				}
				int strengthCheck = new Integer(rand(0, strength + 1));
				if (i > 26) {
					strengthCheck -= strength / 2;
				}
				if (strengthCheck < depth) {
					if (strength > 1) {
						int whereto = new Integer(rand(1, 4));
						if (tiredness > 2) {
							whereto = rand(0, 4);
						}
						if (depth == 1) {
							whereto = 0;
						}
						switch (whereto) {
						case 0:
							System.out.println(i + " " + startY + " Unlucky");
							break;
						case 1:
							if (isDigging(matrix, i, startY, 2)) {
								Lab_go(matrix, i, startY, 2, strength / 2, tiredness + 1);
							} else {
								System.out.println(i + " " + startY + " Risks");
							}
							break;
						case 2:
							if (isDigging(matrix, i, startY, 3)) {
								Lab_go(matrix, i, startY, 3, strength / 2, tiredness + 1);
							} else {
								System.out.println(i + " " + startY + " Risks");
							}
							break;
						case 3:
							if (isDigging(matrix, i, startY, 2)) {
								Lab_go(matrix, i, startY, 2, strength / 2, tiredness + 1);
							} else {
								System.out.println(i + " " + startY + " Risks");
							}
							if (isDigging(matrix, i, startY, 3)) {
								Lab_go(matrix, i, startY, 3, strength / 2, tiredness + 1);
							} else {
								System.out.println(i + " " + startY + " Risks");
							}
							break;
						}
						System.out.println(i + " " + startY + " Going");
					} else {
						System.out.println(i + " " + startY + " Dead");
					}
					break;
				}
				depth += depth;
			}
			//
			//
			//
		case 1: // Left
			for (int i = startX; i >= 0; i--) {
				matrix[i][startY] = 1;
				if (i > 1) {
					if (matrix[i - 2][startY] == 1) {
						break;
					}
				}
				int strengthCheck = new Integer(rand(0, strength + 1));
				if (i < 3) {
					strengthCheck -= strength / 2;
				}
				if (strengthCheck < depth) {
					if (strength > 1) {
						int whereto = new Integer(rand(1, 4));
						if (tiredness > 2) {
							whereto = rand(0, 4);
						}
						if (depth == 1) {
							whereto = 0;
						}
						switch (whereto) {
						case 0:
							System.out.println(i + " " + startY + " Unlucky");
							break;
						case 1:
							if (isDigging(matrix, i, startY, 2)) {
								Lab_go(matrix, i, startY, 2, strength / 2, tiredness + 1);
							} else {
								System.out.println(i + " " + startY + " Risks");
							}
							break;
						case 2:
							if (isDigging(matrix, i, startY, 3)) {
								Lab_go(matrix, i, startY, 3, strength / 2, tiredness + 1);
							} else {
								System.out.println(i + " " + startY + " Risks");
							}
							break;
						case 3:
							if (isDigging(matrix, i, startY, 2)) {
								Lab_go(matrix, i, startY, 2, strength / 2, tiredness + 1);
							} else {
								System.out.println(i + " " + startY + " Risks");
							}
							if (isDigging(matrix, i, startY, 3)) {
								Lab_go(matrix, i, startY, 3, strength / 2, tiredness + 1);
							} else {
								System.out.println(i + " " + startY + " Risks");
							}
							break;
						}
						System.out.println(i + " " + startY + " Going");
					} else {
						System.out.println(i + " " + startY + " Dead");
					}
					break;
				}
				depth += depth;
			}
			//
			//
			//
		case 2: // Up
			for (int j = startY; j >= 0; j--) {
				matrix[startX][j] = 1;
				if (j > 1) {
					if (matrix[startX][j - 2] == 1) {
						break;
					}
				}
				int strengthCheck = new Integer(rand(0, strength + 1));
				if (j < 3) {
					strengthCheck -= strength / 2;
				}
				if (strengthCheck < depth) {
					if (strength > 1) {
						int whereto = new Integer(rand(1, 4));
						if (tiredness > 2) {
							whereto = rand(0, 4);
						}
						if (depth == 1) {
							whereto = 0;
						}
						switch (whereto) {
						case 0:
							System.out.println(startX + " " + j + " Unlucky");
							break;
						case 1:
							if (isDigging(matrix, startX, j, 0)) {
								Lab_go(matrix, startX, j, 0, strength / 2, tiredness + 1);
							} else {
								System.out.println(startX + " " + j + " Risks");
							}
							break;
						case 2:
							if (isDigging(matrix, startX, j, 1)) {
								Lab_go(matrix, startX, j, 1, strength / 2, tiredness + 1);
							} else {
								System.out.println(startX + " " + j + " Risks");
							}
							break;
						case 3:
							if (isDigging(matrix, startX, j, 0)) {
								Lab_go(matrix, startX, j, 0, strength / 2, tiredness + 1);
							} else {
								System.out.println(startX + " " + j + " Risks");
							}
							if (isDigging(matrix, startX, j, 1)) {
								Lab_go(matrix, startX, j, 1, strength / 2, tiredness + 1);
							} else {
								System.out.println(startX + " " + j + " Risks");
							}
							break;
						}
						System.out.println(startX + " " + j + " Going");
					} else {
						System.out.println(startX + " " + j + " Dead");
					}
					break;
				}
				depth += depth;
			}
			//
			//
			//
		case 3: // Down
			for (int j = startY; j < 20; j++) {
				matrix[startX][j] = 1;
				if (j < 18) {
					if (matrix[startX][j + 2] == 1) {
						break;
					}
				}
				int strengthCheck = new Integer(rand(0, strength + 1));
				if (j > 16) {
					strengthCheck -= strength / 2;
				}
				if (strengthCheck < depth) {
					if (strength > 1) {
						int whereto = new Integer(rand(1, 4));
						if (tiredness > 2) {
							whereto = rand(0, 4);
						}
						if (depth == 1) {
							whereto = 0;
						}
						switch (whereto) {
						case 0:
							System.out.println(startX + " " + j + " Unlucky");
							break;
						case 1:
							if (isDigging(matrix, startX, j, 0)) {
								Lab_go(matrix, startX, j, 0, strength / 2, tiredness + 1);
							} else {
								System.out.println(startX + " " + j + " Risks");
							}
							break;
						case 2:
							if (isDigging(matrix, startX, j, 1)) {
								Lab_go(matrix, startX, j, 1, strength / 2, tiredness + 1);
							} else {
								System.out.println(startX + " " + j + " Risks");
							}
							break;
						case 3:
							if (isDigging(matrix, startX, j, 0)) {
								Lab_go(matrix, startX, j, 0, strength / 2, tiredness + 1);
							} else {
								System.out.println(startX + " " + j + " Risks");
							}
							if (isDigging(matrix, startX, j, 1)) {
								Lab_go(matrix, startX, j, 1, strength / 2, tiredness + 1);
							} else {
								System.out.println(startX + " " + j + " Risks");
							}
							break;
						}
						System.out.println(startX + " " + j + " Going");
					} else {
						System.out.println(startX + " " + j + " Dead");
					}
					break;
				}
				depth += depth;
			}
			//
			//
			//
		}
	}

	private boolean isDigging(int[][] matrix, int x, int y, int direction) {
		boolean isDigging = new Boolean(true);
		switch (direction) {
		case 0:
			if (x < 29) {
				if (y > 0) {
					if (matrix[x + 1][y - 1] == 1) {
						isDigging = false;
					}
				}
				if (y < 19) {
					if (matrix[x + 1][y + 1] == 1) {
						isDigging = false;
					}
				}
			} else {
				isDigging = false;
			}
			return isDigging;
		case 1:
			if (x > 0) {
				if (y > 0) {
					if (matrix[x - 1][y - 1] == 1) {
						isDigging = false;
					}
				}
				if (y < 19) {
					if (matrix[x - 1][y + 1] == 1) {
						isDigging = false;
					}
				}
			} else {
				isDigging = false;
			}
			return isDigging;
		case 2:
			if (y > 0) {
				if (x > 0) {
					if (matrix[x - 1][y - 1] == 1) {
						isDigging = false;
					}
				}
				if (x < 29) {
					if (matrix[x + 1][y - 1] == 1) {
						isDigging = false;
					}
				}
			} else {
				isDigging = false;
			}
			return isDigging;
		case 3:
			if (y < 19) {
				if (x > 0) {
					if (matrix[x - 1][y + 1] == 1) {
						isDigging = false;
					}
				}
				if (x < 29) {
					if (matrix[x + 1][y + 1] == 1) {
						isDigging = false;
					}
				}
			} else {
				isDigging = false;
			}
			return isDigging;
		default:
			return false;
		}
	}

	private void initMiniGame1() {
		score = 0;
		trumpet = 0;
		title_player.close();
		playMusic(title_player, "src/Assets/Music/MiniGame1.mp3", 40);
		for (int i = 0; i < 150; i++) {
			String string = new String();
			if (i < 30) {
				words[i].setBounds(1000 + i * 120, rand(270, 331), 126, 18);
				words[i].setForeground(new Color(rand(0, 16), rand(224, 256), rand(0, 16)));
				string += (alfavit.charAt(rand(0, 33)));
				for (int j = 0; j < 9; j++) {
					string += (alfavit.charAt(rand(0, 39)));
				}
			} else if (i < 90) {
				words[i].setBounds(2800 + i * 60, rand(255 + (i % 2) * 90, 331 + (i % 2) * 90), 200, 30);
				words[i].setForeground(new Color(rand(0, 16), rand(224, 256), rand(0, 16)));
				string += (alfavit.charAt(rand(0, 33)));
				for (int j = 0; j < 9; j++) {
					string += (alfavit.charAt(rand(0, 39)));
				}
			} else {
				words[i].setBounds(4600 + i * 40, rand(210 + (i % 3) * 75, 361 + (i % 3) * 75), 200, 30);
				words[i].setForeground(new Color(rand(224, 256), rand(0, 16), rand(0, 16)));
				string += (alphabet.charAt(rand(0, 26)));
				for (int j = 0; j < 9; j++) {
					string += (alphabet.charAt(rand(0, 32)));
				}
			}
			words[i].setText(string);
			misc[i] = rand(0, 4);
			words[i].setFont(new Font("Arial", 1, 18));
			words[i].setVisible(true);
		}

		actor[0].setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame1/Willhelm.PNG")));
		actor[0].setBounds(456, 365, 176, 260);
		actor[0].setVisible(true);

		actor[1].setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame1/Napoleon.PNG")));
		actor[1].setBounds(632, 355, 200, 270);
		actor[1].setVisible(true);

		actor[2].setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame1/Alexander.PNG")));
		actor[2].setBounds(790, 330, 200, 305);

		actor[3].setIcon(new ImageIcon(getImagefromFile("src/Assets/Art/MiniGame1/Victoria.PNG")));
		actor[3].setBounds(400, 10, 500, 450);

		int GermanLeft = new Integer(10);
		int FrenchLeft = new Integer(10);
		for (int i = 0; i < 150; i++) {
			german[i] = false;
			french[i] = false;
		}
		while (GermanLeft > 0) {
			int number = rand(0, 150);
			if (!german[number]) {
				german[number] = true;
				words[number].setForeground(new Color(rand(0, 16), rand(0, 16), rand(0, 16)));
				GermanLeft--;

				String string = new String();
				string += (alphabet.charAt(rand(0, 26)));
				for (int j = 0; j < 9; j++) {
					string += (alphabet.charAt(rand(0, 32)));
				}
				words[number].setText(string);
			}
		}
		while (FrenchLeft > 0) {
			int number = rand(0, 150);
			if (!french[number] && !german[number]) {
				french[number] = true;
				words[number].setForeground(new Color(rand(0, 16), rand(0, 16), rand(224, 256)));
				FrenchLeft--;

				String string = new String();
				string += (alphabet.charAt(rand(0, 26)));
				for (int j = 0; j < 9; j++) {
					string += (alphabet.charAt(rand(0, 32)));
				}
				words[number].setText(string);
			}
		}

		for (int i = 0; i < 50; i++) {
			try {
				File file = new File("src/Assets/Art/MiniGame1/Cloud.PNG");
				BufferedImage image = ImageIO.read(file);
				background_animation[i].setIcon(new ImageIcon(image));
			} catch (IOException e1) {
				System.out.println("Error: " + e1);
			}
			background_animation[i].setBounds(rand(0, 991), rand(0, 221), 105, 60);
			background_animation[i].setVisible(true);
		}
		System.out.println("");
	}

	private void playMusic(MP3Player player, String music, int volume) {
		// player.stop();
		try {
			player.play(music);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
		player.setVolume(volume);
	}

	private void processMiniGame1() {
		int dt = new Integer(animation_status % 20);
		int dx = new Integer(10);
		if (words[18].getBounds().x < 1100) {
			dx += 1;
		}
		if (words[33].getBounds().x < 1100) {
			dx -= 1;
		}
		if (words[48].getBounds().x < 1100) {
			dx -= 1;
		}
		if (words[58].getBounds().x < 1100) {
			dx -= 1;
		}
		if (words[83].getBounds().x < 1100) {
			dx -= 1;
		}
		if (words[93].getBounds().x < 1100) {
			dx += 1;
		}
		if (words[123].getBounds().x < 1100) {
			dx += 1;
		}
		if (animation_status > 1100) {
			dx += (animation_status - 1100) / 10;
		}
		stats.setBounds(270, 110 + (dt - 5) * (dt - 15), 200, 330);

		if (animation_status > 804) {
			actor[3].setVisible(true);
			actor[3].setLocation(rand(420, 481), rand(-11, 11));
			if (animation_status % 2 == 0) {
				//
				ImageIcon icon = new ImageIcon();
				icon = (ImageIcon) actor[3].getIcon();
				BufferedImage image = new BufferedImage(990, 660, BufferedImage.TYPE_INT_ARGB);
				image = (BufferedImage) icon.getImage();
				int trumpetPower = new Integer(rand(0, 6));
				image = resize(image, 500 + 10 * trumpetPower, 450 + 9 * trumpetPower, false);
				actor[3].setSize(500 + 10 * trumpetPower, 450 + 9 * trumpetPower);
				actor[3].setIcon(new ImageIcon(image));
				//
			}
		}
		if (animation_status > 804) {
			// Victorian Alexander
			if (trumpet % 10 == 0) {
				trumpet = 10 * rand(1, 5) - 1;
				actor[2].setVisible(true);
			} else if (trumpet % 10 != 0) {
				trumpet--;
				if (trumpet % 5 == 0) {
					//
					ImageIcon icon = new ImageIcon();
					icon = (ImageIcon) actor[2].getIcon();
					BufferedImage image = new BufferedImage(990, 660, BufferedImage.TYPE_INT_ARGB);
					image = (BufferedImage) icon.getImage();
					int trumpetPower = new Integer(rand(0, 11));
					image = resize(image, 200 + 3 * trumpetPower, 305 + 7 * trumpetPower, false);
					actor[2].setSize(200 + 3 * trumpetPower, 305 + 7 * trumpetPower);
					actor[2].setIcon(new ImageIcon(image));
					//
				}
			}
			switch (trumpet / 10) {
			case 0:
				actor[2].setLocation(rand(760, 811), rand(310, 341));
				break;
			case 1:
				actor[2].setLocation(rand(-10, 41), rand(-10, 21));
				break;
			case 2:
				actor[2].setLocation(rand(640, 691), rand(110, 141));
				break;
			case 3:
				actor[2].setLocation(rand(440, 491), rand(220, 251));
				break;
			}
		} else if (animation_status > 165) {
			// pre_Victorian Alexander
			if (trumpet % 20 == 0) {
				trumpet = 20 * rand(1, 5) - 1;
				actor[2].setVisible(true);
			} else if (trumpet % 20 != 0) {
				trumpet--;
				if (trumpet % 10 == 0) {
					//
					ImageIcon icon = new ImageIcon();
					icon = (ImageIcon) actor[2].getIcon();
					BufferedImage image = new BufferedImage(990, 660, BufferedImage.TYPE_INT_ARGB);
					image = (BufferedImage) icon.getImage();
					int trumpetPower = new Integer(rand(0, 11));
					image = resize(image, 200 + 3 * trumpetPower, 305 + 7 * trumpetPower, false);
					actor[2].setSize(200 + 3 * trumpetPower, 305 + 7 * trumpetPower);
					actor[2].setIcon(new ImageIcon(image));
					//
				}
			}
			switch (trumpet / 20) {
			case 0:
				actor[2].setLocation(rand(770, 801), rand(320, 331));
				break;
			case 1:
				actor[2].setLocation(rand(0, 31), rand(0, 11));
				break;
			case 2:
				actor[2].setLocation(rand(650, 681), rand(120, 131));
				break;
			case 3:
				actor[2].setLocation(rand(450, 481), rand(230, 241));
				break;
			}
		}

		for (int i = 0; i < 150; i++) {
			words[i].setBounds(words[i].getBounds().x - dx * (1 - (misc[i] / 4) * 2),
					words[i].getBounds().y + (dt - 9) * (misc[i] % 4 - 2), words[i].getBounds().width,
					words[i].getBounds().height);
			if (dt > 9) {
				words[i].setBounds(words[i].getBounds().x, words[i].getBounds().y - 1 * (misc[i] % 4 - 2),
						words[i].getBounds().width, words[i].getBounds().height);
			}
			if (words[i].getBounds().x < 440 && words[i].isVisible()) {
				words[i].setVisible(false);
				score++;
				System.out.println(score);
				if (german[i]) {
					resizeImage(actor[0], 4, 6);
				} else if (french[i]) {
					resizeImage(actor[1], 3, 5);
				} else {
					animation_status = 999999;
				}
			}
		}
		dt %= 10;
		protagonist.setBounds(-60 - 400 * dt, 110, 400 * (dt + 1), 550);
		if (MouseInfo.getPointerInfo().getLocation().x < 1350 && MouseInfo.getPointerInfo().getLocation().y < 900) {
			cursor.setBounds(MouseInfo.getPointerInfo().getLocation().x - this.getLocation().x - 1385 - 1484 * action,
					MouseInfo.getPointerInfo().getLocation().y - this.getLocation().y - 140, 1484 * (action + 1), 990);
		}
		if (animation_status < 1490) {
			getContentPane().setBackground(new Color(rand(120 + animation_status / 135, 151 + animation_status / 135),
					rand(0, 1), rand(0, 43)));
		}
		for (int i = 0; i < 50; i++) {
			background_animation[i].setBounds(background_animation[i].getBounds().x - 45,
					background_animation[i].getBounds().y + rand(-4, 5), background_animation[i].getBounds().width,
					background_animation[i].getBounds().height);
			if (background_animation[i].getBounds().x < -105) {
				background_animation[i].setBounds(background_animation[i].getBounds().x + rand(1095, 1305),
						rand(0, 221), background_animation[i].getBounds().width,
						background_animation[i].getBounds().height);
			}
		}
		// Gameplay
		for (int i = 0; i < 150; i++) {
			if (MouseInfo.getPointerInfo().getLocation().x - this.getLocation().x > words[i].getBounds().x + 12
					&& MouseInfo.getPointerInfo().getLocation().x - this.getLocation().x < words[i].getBounds().x
							+ words[i].getBounds().width - 12
					&& MouseInfo.getPointerInfo().getLocation().y - this.getLocation().y > words[i].getBounds().y + 18
					&& MouseInfo.getPointerInfo().getLocation().y - this.getLocation().y < words[i].getBounds().y
							+ 54) {
				misc[i] %= 4;
				if (action == 0) {
					misc[i] += 4;
				}
			}
		}
	}

	private void resizeImage(JLabel jLabel, int dx, int dy) {
		ImageIcon icon = new ImageIcon();
		icon = (ImageIcon) jLabel.getIcon();
		BufferedImage image = new BufferedImage(990, 660, BufferedImage.TYPE_INT_ARGB);
		image = (BufferedImage) icon.getImage();
		image = resize(image, Math.max(4, image.getWidth() + dx), Math.max(4, image.getHeight() + dy), false);
		jLabel.setIcon(new ImageIcon(image));
		jLabel.setBounds(jLabel.getBounds().x, jLabel.getBounds().y, jLabel.getBounds().width + dx,
				jLabel.getBounds().height + dy);
	}

	private static int rand(int i, int j) {
		return ThreadLocalRandom.current().nextInt(i, j);
	}

	private boolean MiniGame1IsActive() {
		boolean isActive = false;
		for (int i = 0; i < 150; i++) {
			if (words[i].isVisible() && words[i].getBounds().x < 1320) {
				isActive = true;
			}
		}
		return isActive;
	}
}