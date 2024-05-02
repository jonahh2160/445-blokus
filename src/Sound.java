//AR 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public class Sound
{
    Clip clip; //use to open audio files 
   

    public void play()
    {
        clip.start();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop()
    {
        clip.stop(); 
    }
}
