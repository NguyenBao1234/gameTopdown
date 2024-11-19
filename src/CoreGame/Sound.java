package CoreGame;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundUrl[] = new URL[30];

    public Sound() {
        soundUrl[0] = getClass().getResource("/Resource/Sound/BlueBoyAdventure.wav");
        soundUrl[1] = getClass().getResource("/Resource/Sound/coin.wav");
        soundUrl[2] = getClass().getResource("/Resource/Sound/powerup.wav");
        soundUrl[3] = getClass().getResource("/Resource/Sound/unlock.wav");
        soundUrl[4] = getClass().getResource("/Resource/Sound/fanfare.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }



}
