//Copyright POWGameStd
package POWJ.SoundComponent;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;


public class Sound
{
    private Clip clip;
    private boolean bLoop;

    public Sound(float volume, boolean bLoop, String soundPath)
    {
        try
        {
            this.bLoop = bLoop;
            URL soundURL = Sound.class.getResource(soundPath);
            if (soundURL == null)
            {
                System.err.println("File not found: " + soundPath);
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dbVolume = 20f * (float) Math.log10(volume);
            volumeControl.setValue(dbVolume);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    public void Play()
    {
        if (clip == null) return;
        if (bLoop) clip.loop(Clip.LOOP_CONTINUOUSLY);
        else clip.loop(0);
        clip.start();
    }
    public void Play(boolean Loop)
    {
        if (clip == null) return;
        if (Loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
        else clip.loop(0);
        clip.start();
    }
    public void Stop()
    {
        if (clip == null || !clip.isRunning()) return;
        clip.stop();
    }
    public void Stop(boolean bClear)
    {
        if (clip == null || !clip.isRunning()) return;
        clip.stop();
        if(bClear) clip.setFramePosition(0);
    }
}


