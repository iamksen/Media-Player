
package mplayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;

import java.io.File;
import static java.lang.Math.floor;
import javafx.animation.Timeline;
import javafx.application.Platform;
import static javafx.application.Platform.runLater;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class MPlayer extends Application {
    
    /* For Proving Animation */
    Timeline moveOut = new Timeline();
    Timeline moveIn = new Timeline();
    Timeline moveFastIn = new Timeline();
    
    /* Box which will contain all the controls Buttons and sliders */
    VBox controlBox = new VBox();
    BorderPane container = new BorderPane();
    
    /* Buttons for Controlling Media */
    MediaVolumeControl mediaVolumeControl = new MediaVolumeControl(); //for controlling volume
    MediaFileChooser mediaFileChooser = new MediaFileChooser();
    MediaControl mediaControl = new MediaControl();  //for controlling media
    MediaMessages mediaMessages = new MediaMessages();
    MediaAnimation mediaAnimation = new MediaAnimation(moveIn , moveOut, moveFastIn, controlBox);
    
    private File file;
    
    Media media;
    MediaPlayer player;
    MediaView view;
    
    //progress bar
    ProgressBar pBar = new ProgressBar(); 
    ProgressBar pVolumeBar = new ProgressBar();
    Label timeLabel = new Label();  //for displaying time
    Label remainTimeLabel = new Label();
    Label totalTimeLabel = new Label();
   
    static boolean mediaPlayerStatus; //tell if player is playing any media or not
    double divideFactor;  // for hybride of progressBar and slider
    
    //return graphic of image
    public ImageView ImageViewFunction( Image imageName ){
        ImageView image = new ImageView( imageName );
        return image;
    }
    
    void Initialize(){
        
        divideFactor = 1;
                
        pBar.setProgress(0.0);
        pBar.setMaxWidth( 1500 );
        mediaControl.moviePosition.setMax(0.0);
       
        pVolumeBar.setMaxWidth(100);
               
        /* MediaVolume progressBar + slider hybrid */
        mediaVolumeControl.volumeControl.setMaxWidth(100);
        mediaVolumeControl.volumeControl.setValue(0.5);
        pVolumeBar.setProgress(0.5);
        
        timeLabel.setText(" 00:00/00:00 ");
        //remainTimeLabel.setText(" 00:00 ");
        //totalTimeLabel.setText(" 00:00 ");
        
        
        mediaControl.SetImagetoButtons();
        
        mediaPlayerStatus = false; //initially no media file is choose to play
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        
        Initialize();       
        
        //mediaAnimation.EnableAnimation(100);
        
        //MenuBar
        MenuBar menuBar = new MenuBar();
        Menu MediaMenu = new Menu("Media");
        MenuItem openMenu = new MenuItem("Open");
        MenuItem exitMenu = new MenuItem("Exit");
        MediaMenu.getItems().addAll(openMenu, exitMenu);
        
        Menu helpMenu = new Menu("Help");
        MenuItem aboutUsMenu = new MenuItem("About Us");
        
        helpMenu.getItems().add(aboutUsMenu);
        menuBar.getMenus().addAll(MediaMenu, helpMenu);
        
        openMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opening Media");
                file = mediaFileChooser.ChooseFile(primaryStage);
                if( file == null){
                    mediaMessages.CloseMessage("File not Choosen", "Please Select Media to play!!!");
                    return;
                }
                //mediaFileChooser.PlayFile(media, player, view, file);
                
                String moviePath = file.getAbsolutePath();
                moviePath = moviePath.replace("\\","/");
                try{
                    media = new Media( new File(moviePath).toURI().toString() );
                } catch( MediaException e ) {
                    mediaMessages.CloseMessage("Media Not Supported", "Media Type is not Supported!!!");
                    return;
                }
                
                //player.stop();
                player = new MediaPlayer(media);
                
                /*
                player.setAutoPlay(true);
                view.setMediaPlayer( player );
                container.setCenter(view);
                */
                
                setPlayer(primaryStage);
                //mediaPlayerStatus = true;
            }
        });
        aboutUsMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaMessages.CloseMessage("Creater of AKA Media Player", "Created By :-\n  1.Kundan Sen\n  2.Akhil Betanabhotla\n  3.Amit Vijay\n\n");
            }
        });
        
        //Hybrid of progressBar and slider
        StackPane sliderBar = new StackPane();
        sliderBar.setPadding( new Insets(10, 5, 0, 5));
        sliderBar.getChildren().addAll(pBar , mediaControl.moviePosition);
               
        controlBox.getChildren().add( sliderBar ); // adding hybrid of progressBar and Slider
        //controlBox.setPadding( new Insets( 10, 0, 0, 0) );
        
        //Hybrid of progressBar and slider
        StackPane sliderBar1 = new StackPane();
        sliderBar1.getChildren().addAll(pVolumeBar , mediaVolumeControl.volumeControl);
        
        HBox toolBox = new HBox();
        HBox box1 = new HBox();
        HBox box2 = new HBox();
        HBox box3 = new HBox();
        HBox box4 = new HBox();
        
        //toolBox.getChildren().addAll( mediaControl.playpauseButton,  mediaControl.backwardButton, mediaControl.forwardButton, mediaControl.fullScreenButton, timeLabel, sliderBar1  );
        
        box1.getChildren().add( mediaControl.playpauseButton );
        box2.getChildren().addAll( mediaControl.backwardButton, mediaControl.forwardButton, mediaControl.fullScreenButton );
        
        box3.getChildren().add( timeLabel );
        
        
        box2.setAlignment(Pos.CENTER);
        box3.setAlignment(Pos.CENTER);
        HBox.setHgrow( box3, Priority.ALWAYS);
        box4.getChildren().add( sliderBar1 );
        
        toolBox.getChildren().addAll( box1, box2 ,box3, box4);
        
        toolBox.setPadding( new Insets(5) );
        
        
        controlBox.getChildren().add(toolBox);
        
        
        container.setTop(menuBar);
        container.setBottom( controlBox);
        container.setStyle("-fx-background-color : black");
        
        controlBox.setStyle("-fx-background-color : white");
        //vb.getChildren().addAll( menuBar , view, controlBox);
        //group.getChildren().addAll( vb1 );
        
        Scene scene = new Scene( container , 900, 500);
        scene.getStylesheets().add(getClass().getResource("PlayerCss.css").toExternalForm());
        primaryStage.setTitle("AKA Player");
        primaryStage.setScene(scene);
             
        
        mediaControl.moviePosition.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            double changeValue = 0.0;
            @Override
            public void handle(MouseEvent mouseEvent) {
                if( !mediaPlayerStatus ){
                    return;
                }
                /*
                if( player.getStatus() == MediaPlayer.Status.PLAYING){
                    moved = true;
                    System.out.println("yes value of moved " + moved );
                    player.pause();
                } 
                */
                //changeValue = mediaControl.moviePosition.getValue();
                double x = mediaControl.moviePosition.getValue();
                System.out.println("x : " + x);
                player.seek(Duration.seconds( mediaControl.moviePosition.getValue() ));
                //mediaControl.moviePosition.setValue(changeValue);
                /*
                if( moved ){
                    player.play();
                    moved = false;
                }
                */
            }
         });    
        
        mediaControl.playpauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( !mediaPlayerStatus ){
                    return;
                }
                System.out.println("calling function");
                mediaControl.PlayMedia(player);
            }
        });
        
        //forward by 10 seconds
        mediaControl.forwardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( !mediaPlayerStatus ){
                    return;
                }
                mediaControl.ForwardMedia(player);
            }
        });
        
        //backward by 10 seconds
        mediaControl.backwardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( !mediaPlayerStatus ){
                    return;
                }
                mediaControl.BackwardMedia(player);
            }
        });
        
        /* If click on full Screen Button */
        mediaControl.fullScreenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( !mediaPlayerStatus ){
                    return;
                }
                
                mediaControl.FullScreenMedia( primaryStage ,scene , view, media);
                
                if( primaryStage.isFullScreen()){
                    menuBar.setVisible(false);
                    //moveOut
                    //moveOut.play();
                    view.setOnMouseEntered( new EventHandler<MouseEvent>() {
                        @Override 
                        public void handle(MouseEvent event) {
                            menuBar.setVisible( false );
                            System.out.println("Hi again");
                            moveOut.play();
                        }
                    });
                    
                    view.setOnMouseExited( new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            menuBar.setVisible( true );
                            System.out.println("Checking out of full Screen");
                            moveIn.play();
                        }
                    });    
                } else {
                    menuBar.setVisible(true);
                    view.setOnMouseEntered( new EventHandler<MouseEvent>() {
                        @Override 
                        public void handle(MouseEvent event) {
                            System.out.println("Hi again");
                            moveFastIn.play();
                        }
                    });
                    
                    view.setOnMouseExited( new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            System.out.println("Checking out of full Screen");
                            moveFastIn.play();
                        }
                    });
                }
            }
        });
        
        
        //Slider for changing volume
        mediaVolumeControl.volumeControl.valueProperty().addListener(new ChangeListener<Number>() { 
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                
                System.out.println("Current Volume is " + new_val.doubleValue());
                double volumeValue = new_val.doubleValue();
                pVolumeBar.setProgress(volumeValue);
                if( !mediaPlayerStatus ){
                    return;
                }
                mediaVolumeControl.ChangeVolume(player, volumeValue);
                
            }
        });
        
        
        exitMenu.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        
        /*
        scene.setOnMouseClicked( (MouseEvent event) -> {
            
            if( event.getClickCount() == 1) {
                if( player.getStatus() == MediaPlayer.Status.PLAYING )
                    player.pause();
                else if ( player.getStatus() == MediaPlayer.Status.PAUSED)
                    player.play();
            }else  
            if( event.getClickCount() == 2 ){
               
                mediaControl.FullScreenMedia(primaryStage, scene, view, media);
               
                if( primaryStage.isFullScreen() ){
                    
                    view.setOnMouseEntered( new EventHandler<MouseEvent>() {
                        @Override 
                        public void handle(MouseEvent event) {
                            System.out.println(" there " );
                            moveOut.play();
                        }
                    });
                    
                    view.setOnMouseExited( new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            System.out.println("Checking out ");
                            if( primaryStage.isFullScreen() )
                                moveIn.play();
                            else
                                moveFastIn.play();
                        }
                    });
                } else {
                    moveFastIn.play();
                    view.setOnMouseEntered( new EventHandler<MouseEvent>() {
                        @Override 
                        public void handle(MouseEvent event) {
                            System.out.println(" there " );
                            moveFastIn.play();
                        }
                    });
                    
                }
            }
        });
        */
        
        /* Adding shortcut to media player*/
        final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
        final KeyCombination keyComb2 = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
        final KeyCombination ctrlRight = new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN);
        final KeyCombination ctrlLeft = new KeyCodeCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN);
        final KeyCombination ctrlUp = new KeyCodeCombination(KeyCode.UP, KeyCombination.CONTROL_DOWN);
        final KeyCombination ctrlDown = new KeyCodeCombination(KeyCode.DOWN, KeyCombination.CONTROL_DOWN);

        scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler() {
            @Override
            public void handle(Event event) {
                if ( keyComb1.match((KeyEvent) event)) {
                    System.out.println("Ctrl+O pressed");
                    System.out.println("Opening Media using shortkut");
                    
                    file = mediaFileChooser.ChooseFile(primaryStage);
                    if( file == null){
                        mediaMessages.CloseMessage("File not Choosen", "Please Selected Media to play!!!");
                        return;
                    }

                    String moviePath = file.getAbsolutePath();
                    moviePath = moviePath.replace("\\","/");
                    try{
                        media = new Media( new File(moviePath).toURI().toString() );
                    } catch( MediaException e ) {
                        mediaMessages.CloseMessage("Media Not Supported", "Media Type is not Supported!!!");
                        return;
                    }
                    player = new MediaPlayer(media);
                    setPlayer(primaryStage);
                    //mediaPlayerStatus = true;
                } else if (keyComb2.match((KeyEvent) event)) {
                    System.out.println("Ctrl+X pressed");
                    if( mediaPlayerStatus ){
                        player.stop();
                    }
                    Platform.exit();
                } else if ( ctrlRight.match((KeyEvent) event)) {
                    System.out.println("Ctrl+RightArrowKey pressed");
                    if( !mediaPlayerStatus ){
                        return;
                    }
                    mediaControl.ForwardMedia( player );
                } else if ( ctrlLeft.match((KeyEvent) event)) {
                    System.out.println("Ctrl+RightArrowKey pressed");
                    if( !mediaPlayerStatus ){
                        return;
                    }
                    mediaControl.BackwardMedia( player );
                } else if ( ctrlUp.match((KeyEvent) event)) {
                    System.out.println("Ctrl+UpArrowKey pressed");
                    double value = player.getVolume();
                    if( value  <= 0.9){
                        value += 0.1;
                        player.setVolume(value);
                        mediaVolumeControl.volumeControl.setValue(value);
                    } 
                } else if ( ctrlDown.match((KeyEvent) event)) {
                    System.out.println("Ctrl+DownArrowKey pressed");
                    double value = player.getVolume();
                    if( value  >= 0.1){
                        value -= 0.1;
                        player.setVolume(value);
                        mediaVolumeControl.volumeControl.setValue(value);
                    } 
                }
            }
        });
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent key) {
                
                if( !mediaPlayerStatus ){
                    return;
                }
                
                if( key.getCode() == KeyCode.SPACE){
                    mediaControl.PlayMedia(player);
                } else if ( key.getCode() == KeyCode.ESCAPE ) {
                    if( primaryStage.isFullScreen() ) {
                        mediaControl.FullScreenMedia(primaryStage, scene, view, media);
                    }
                }
            }
        });
        
        primaryStage.getIcons().add( mediaControl.akaLogo);
        primaryStage.show();
    }
    
    public void setPlayer(Stage primaryStage){
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                Image pauseButtonImage = new Image(getClass().getResourceAsStream("/images1/pauseButtonImage.jpg"));
                System.out.println("Player is Ready to Play");
                //int width = player.getMedia().getWidth();
                //int height = player.getMedia().getHeight();

                mediaControl.moviePosition.setMin(0.0);
                mediaControl.moviePosition.setValue(0.0);
                
                divideFactor = player.getTotalDuration().toSeconds();
                mediaControl.moviePosition.setMax( divideFactor );
                //pBar.setMaxWidth( player.getTotalDuration().toSeconds() );
                pBar.setMaxWidth( divideFactor * 100 );
                pBar.setProgress(0.0);
                //primaryStage.setMinWidth(width + 10);
                //primaryStage.setMinHeight(height + controlBox.getHeight() + 65 );
                
                //playpauseButton.setText("Pause");
                view = new MediaView( player );
                
                //view.setPreserveRatio( false );
                //view.autosize();
                
                double MaxHeight = media.getHeight();
                double MaxWidth = media.getWidth();
                
                
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                double height1 = primaryScreenBounds.getHeight();
                double width1 = primaryScreenBounds.getWidth();
                double maxX1 = primaryScreenBounds.getMaxX();
                double maxY1 = primaryScreenBounds.getMaxY();
                double minX1 = primaryScreenBounds.getMinX();
                double minY1 = primaryScreenBounds.getMinY();

                
                System.out.println("Stage MinX:" + minX1 + "   MinY:" + minY1 + "   MaxX:" + maxX1 + "   MaxY:"+ maxY1 + "    Height:"+height1 + " Width:" + width1);
                System.out.println("MediaValue Height:"+ MaxHeight + "  Width:"+MaxWidth);
                
                /*
                if( MaxHeight > maxY1 ){
                    double a,b;
                    a = MaxHeight - maxY1;
                    b = MaxWidth - maxX1;
                    media.heightProperty().subtract( MaxHeight - a );
                    media.widthProperty().subtract( MaxWidth - b );
                    System.out.println("You fool");
                    //view.minHeight( maxY1 );
                    view.maxHeight( maxY1 );
                    view.maxWidth( height1 );
                    System.out.println(" stageheight  " + primaryStage.getHeight() + " other " + maxY1 );
                    primaryStage.setMaxHeight( maxY1  );
                    System.out.println(" stageheightafter  " + primaryStage.getHeight());
                    view.maxHeight( maxY1 -100 );
                }
                */
                view.fitWidthProperty().bind( primaryStage.widthProperty() );
                view.fitHeightProperty().bind(  primaryStage.heightProperty().subtract( 200 ));
                //view.setStyle("-fx-padding : auto");
                container.setCenter(view);
                
                player.setAutoPlay(true);
                
              
                
                updateLabel();
                mediaControl.playpauseButton.setGraphic( ImageViewFunction(pauseButtonImage) );
                player.setVolume( mediaVolumeControl.volumeControl.getValue());
                
                mediaPlayerStatus = true;
                mediaAnimation.EnableAnimation( primaryStage, 100);
            }
        });
        
        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration current) {
                mediaControl.moviePosition.setValue( player.getCurrentTime().toSeconds() );
                pBar.setProgress(current.toSeconds()/divideFactor);
                updateLabel();    
            }
        });
       
        
        player.setOnEndOfMedia(new Runnable() {
            public void run() {
                player.dispose();
                Initialize();
                container.setStyle("-fx-background-color : black");
            }
        });
    }
    
    public void updateLabel(){
        if( timeLabel != null ){
            runLater( () -> {
               Duration duration = player.getTotalDuration();
               Duration currentTime = player.getCurrentTime();
               formatTime( currentTime, duration );
            });
        }
    }
    
    public void formatTime(Duration elapsed , Duration duration){
        
        int intElapsed = (int) floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        //Calculating total hours
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        if( elapsedMinutes > 0 ) intElapsed -=  elapsedMinutes * 60;
        int elapsedSeconds = intElapsed;

       
        int intDuration = (int) floor(duration.toSeconds());
        int durationHours = intDuration / (60 * 60);
        if (durationHours > 0) {
            intDuration -= durationHours * 60 * 60;
        }
        int durationMinutes = intDuration / 60;
        if( durationMinutes > 0 ) intDuration -= durationMinutes * 60;
        int durationSeconds = intDuration;// - durationHours * 60 * 60 - durationMinutes * 60;
        
        
        if( durationHours > 0 ){
            //timeLabel.setText(" " + eh + ":" + em + ":" + es + "/" + dh + ":" + dm + ":"+ ds);
            timeLabel.setText(String.format("%02d:%02d:%02d/%02d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds, durationHours, durationMinutes, durationSeconds));
        }else{
            timeLabel.setText(String.format("%02d:%02d/%02d:%02d", elapsedMinutes, elapsedSeconds, durationMinutes, durationSeconds));
            /*
            if( elapsedMinutes >= 0){
                timeLabel.setText(" " + em + ":" + es + "/" + dh + ":" + dm + ":"+ ds);
            } else {
                timeLabel.setText(" " + es + "/" + dh + ":" + dm + ":"+ ds);
            }
            */
        }
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
