package mplayer;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MediaFileChooser {
    public File ChooseFile(Stage primaryStage){
        File file;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Please Select a Media File");
        file = fileChooser.showOpenDialog( primaryStage );
        if( file == null) return null;
        return file;
    }
    public void PlayFile(Media media, MediaPlayer player , MediaView view, File file){
        String moviePath = file.getAbsolutePath();
        moviePath = moviePath.replace("\\","/");
        media = new Media( new File(moviePath).toURI().toString() );
        player.stop();
        player = new MediaPlayer(media);
        player.setAutoPlay(true);
        view.setMediaPlayer( player );
    }
}
