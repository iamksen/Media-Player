package mplayer;

import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

public class MediaVolumeControl {
    
    Slider volumeControl = new Slider(0,1,0.0); //for controling volume
    
    //function for changing volume of player
    void ChangeVolume( MediaPlayer player , double volumeValue ){
        player.setVolume(volumeValue);
    }
    
    //function for mute
    void MuteVolume( MediaPlayer player ) {
        player.setVolume(0.0);
    }
}
