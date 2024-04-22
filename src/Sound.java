//AR 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public class Sound
{
    Clip clip; //use to open audio files 
    URL soundURL[] = new URL[30]; 

    public sound()
    {
        //winning screen 
        soundURL[0] = getClass.getResource("assets/zapsplat_multimedia_game_sound_plucked_harp_warm_success_tone_109652.wav");  
        //placing block 
        soundURL[1] = getClass.getResource("assets/zapsplat_leisure_draughts_game_piece_place_down_onto_board_square_002_17366.wav");  
        //metal clink 
        soundURL[2] = getClass.getResource("assets/zapsplat_multimedia_game_sound_put_down_object_metal_clink_delicate_109653.wav"); 
    }

    public void setFile()
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip(); 
            clip.open(ais);
        }catch(Exception e){}
    }

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
