package sokoban_code;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * Classe permettant de jouer de la musique
 */
public class MusicPlayer {
    private Clip clip;
    private String state;

    /**
     * Constructeur
     */
    public MusicPlayer(String fileName) {
        try {
            File file = new File(fileName);
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode permettant de jouer de la musique
     */
    public void play() {
        if (clip != null) {
            clip.start();
            state = "play";
        }
    }

    /** Methode permettant de stopper la musique */
    public void stop() {
        if (clip != null) {
            clip.stop();
            state = "stop";
        }
    }

    /** Methode permettant de mettre en pause la musique */
    public String state() {
        return state;
    }
}