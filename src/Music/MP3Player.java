package Music;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MP3Player {
     
	Media audioInputStream;
	MediaPlayer play;
	
	MP3Player() {
	}

	public void playLoop(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	    File in = new File(path);
		audioInputStream = new Media(in.toURI().toString());
		play = new MediaPlayer(audioInputStream);
		play.setCycleCount(-1);
	    play.play();
		System.out.println("media SUCCESS");
	}

	public void play(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	    File in = new File(path);
		audioInputStream = new Media(in.toURI().toString());
		play = new MediaPlayer(audioInputStream);
	    play.play();
		System.out.println("media SUCCESS");
	}
	
	public void setVolume(float _volume) {
		play.setVolume(_volume);
	}
	
	public void stop() {
	    play.pause();
		System.out.println("stopper SUCCESS");
	}
	
	public void close() {
	    play.stop();
		System.out.println("stopper SUCCESS");
	}
}
