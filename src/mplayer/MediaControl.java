
package mplayer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.util.Duration;

import javafx.geometry.Rectangle2D;
import javafx.scene.media.Media;

public class MediaControl {
    
    //Graphics images for control Buttons
    Image playButtonImage = new Image(getClass().getResourceAsStream("/images1/playButtonImage.jpg"));
    Image pauseButtonImage = new Image(getClass().getResourceAsStream("/images1/pauseButtonImage.jpg"));
    Image forwardButtonImage = new Image(getClass().getResourceAsStream("/images1/forwardButtonImage.jpg"));
    Image backwardButtonImage = new Image(getClass().getResourceAsStream("/images1/backwardButtonImage.jpg"));
    Image fullScreenButtonImage = new Image(getClass().getResourceAsStream("/images1/fullScreenButtonImage.jpg"));
    Image exitFullScreenButtonImage = new Image(getClass().getResourceAsStream("/images1/exitFullScreenButtonImage.jpg"));
    Image speakerImage = new Image(getClass().getResourceAsStream("/images1/speaker.jpg"));
    Image akaLogo = new Image(getClass().getResourceAsStream("/images1/aka.jpg"));
    
    //Buttons to control the media player
    Button playpauseButton = new Button();
    Button forwardButton = new Button();
    Button backwardButton = new Button();
    Button fullScreenButton = new Button();
    Button muteButton = new Button();
    Slider moviePosition = new Slider(0,1000,0); //for controling movie duration
    
    //return graphic of image
    public ImageView ImageViewFunction( Image imageName ){
        ImageView image = new ImageView( imageName );
        return image;
    }
    
    //function for setting images on control buttons
    void SetImagetoButtons() {
        playpauseButton.getStyleClass().add("tranparentClass");   //making playButton transparent
        forwardButton.getStyleClass().add("tranparentClass");
        backwardButton.getStyleClass().add("tranparentClass");
        fullScreenButton.getStyleClass().add("tranparentClass");
        muteButton.getStyleClass().add("tranparentClass");
        
        playpauseButton.setGraphic( ImageViewFunction(playButtonImage) );  //Initialy making button to play
        forwardButton.setGraphic( ImageViewFunction( forwardButtonImage ) );
        backwardButton.setGraphic( ImageViewFunction( backwardButtonImage));
        fullScreenButton.setGraphic( ImageViewFunction( fullScreenButtonImage));
        muteButton.setGraphic( ImageViewFunction( speakerImage ));
        //exitFullScreenButton.setGraphic( ImageViewFunction( exitFullScreenButtonImage));   
    }
    
    void PlayMedia( MediaPlayer player ){
        if( player.getStatus() == MediaPlayer.Status.PLAYING){
               System.out.println("Pause Media using mediacontrol");
               playpauseButton.setGraphic( ImageViewFunction(playButtonImage) );
               player.pause();
        } else if( player.getStatus() == MediaPlayer.Status.PAUSED) {
               System.out.println("Playig Media using ");
               //playpauseButton.setText("Pause");
               playpauseButton.setGraphic( ImageViewFunction(pauseButtonImage) );
               player.play();
        }
    }
    
    void ForwardMedia( MediaPlayer player ) {
        System.out.println("ForwardMedia usign mediaControl");
        player.seek(Duration.seconds( moviePosition.getValue() + 10 ));
    }
    
    void BackwardMedia( MediaPlayer player){
        System.out.println("Backward Media using mediaControl");
        int x = (int) ( moviePosition.getValue() - 10);
        if( x >= 0)
            player.seek(Duration.seconds( x ));
        else
            player.seek(Duration.ZERO);
    }
    
    void FullScreenMedia(Stage primaryStage , Scene scene, MediaView view, Media media){
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        
        System.out.println("FullScreen Media Player"); 
        System.out.println("x : " + scene.getWidth() + " y : " + scene.getHeight());
        
        if( primaryStage.isFullScreen() ){
            primaryStage.setFullScreen(false);
            fullScreenButton.setGraphic( ImageViewFunction(fullScreenButtonImage) );
            
            //view.setViewport(primaryScreenBounds);
            //view.setFitHeight( primaryStage.getHeight() );
            //view.setFitWidth( primaryStage.getWidth() );
            
            /*
            fullScreenButton.setText("Full Screen");
            double x = root.getWidth();
            double y = root.getHeight();
            view.setFitWidth(x);
            view.setFitHeight(y-controlBox.getHeight());
            System.out.println("Maxwidth of primaryStage height : "+ x + " " + y);
            */
        } else { 
            primaryStage.setFullScreen(true);
          
            //Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            double x = primaryScreenBounds.getWidth();
            double y = primaryScreenBounds.getHeight();
            //double y = primaryStage.getHeight();
            
            //primaryScreenBounds.getWidth;
            
            fullScreenButton.setGraphic( ImageViewFunction(exitFullScreenButtonImage) );
            
            //scene.setOnMouseMoved( e -> System.out.println("Yes"));
            
            
            /* Extra Section of code*/
            double MaxHeight = media.getHeight();
            double MaxWidth = media.getWidth();
            double height1 = primaryScreenBounds.getHeight();
            double width1 = primaryScreenBounds.getWidth();
            double maxX1 = primaryScreenBounds.getMaxX();
            double maxY1 = primaryScreenBounds.getMaxY();
            double minX1 = primaryScreenBounds.getMinX();
            double minY1 = primaryScreenBounds.getMinY();
            /* End of extra code*/
            
            //view.autosize();
            //view.setPreserveRatio(false);
            view.setSmooth( true );
            
            //double a = MaxHeight - maxY1;
            //double b = MaxWidth - maxX1;
            view.fitWidthProperty().bind( primaryStage.widthProperty() );
            view.fitHeightProperty().bind(  primaryStage.heightProperty().subtract(200) );
            
            //view.fitHeightProperty().bind( primaryStage.getScene().heightProperty().subtract(200));
        }
    }
}
