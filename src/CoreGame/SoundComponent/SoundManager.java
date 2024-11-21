package CoreGame.SoundComponent;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundManager
{
    public static Sound SpawnSound(float volume, boolean bLoop, String soundPath)
    {
        return new Sound(volume, bLoop, soundPath);
    }
    public static void playSound(float volume, boolean bLoop, String soundPath)
    {
        try {
            URL soundURL = SoundManager.class.getResource(soundPath);
            if (soundURL == null)
            {
                System.err.println("File not found: " + soundPath);
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dbVolume = 20f * (float) Math.log10(volume);
            volumeControl.setValue(dbVolume);

            if (bLoop) clip.loop(Clip.LOOP_CONTINUOUSLY);
            else clip.loop(0);

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
