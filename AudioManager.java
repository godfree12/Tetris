import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AudioManager {

    private static AudioManager instance;

    private AudioManager() {
        // Emp�cher l'instanciation directe depuis l'ext�rieur de la classe
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void playSound(String filePath) {
        try {
            URL soundUrl = getClass().getResource(filePath);
            if (soundUrl == null) {
                System.out.println("Le fichier audio n'a pas �t� trouv� : " + filePath);
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
