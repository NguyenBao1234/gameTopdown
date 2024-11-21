package CoreGame;

import java.net.URL;

import javax.sound.sampled.*;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    FloatControl fc;
    int volumeScale = 3;
    float volume;


    public void playSound(String soundPath)
    {
        try {
            // Open an audio input stream.
            URL url = Sound.class.getResource(soundPath);
            if (url == null) {
                throw new RuntimeException("File not found: " + soundPath);
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
//            clip.start();

//        } catch (UnsupportedAudioFileException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
        }
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }

        fc.setValue(volume);
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
    public void playBackgroundMusic(String music) {
        playSound(music);
        play();
        loop();
    }

    public void stopMusic() {
        stop();
    }

    public void playSoundEffect(String SE) {
        playSound(SE);
        play();
    }



 /*  Ryi Snow
    URL[] soundUrl = new URL[30];

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
    public void playMusic(int i) {
        setFile(i);
        play();
        loop();
    }

    public void stopMusic() {
        stop();
    }

    public void playSE(int i) {
        setFile(i);
        play();
    }*/

}


