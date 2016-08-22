package mplayer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MediaAnimation {    
   
    Timeline moveIn;
    Timeline moveOut;
    Timeline moveFastIn;
    VBox vbox;

    MediaAnimation( Timeline moveIn, Timeline moveOut, Timeline moveFastIn, VBox controlBox){
        this.moveIn = moveIn;
        this.moveOut = moveOut;
        this.moveFastIn = moveFastIn;
        this.vbox = controlBox;
    }

    void EnableAnimation(Stage primaryStage, int h) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        
        double total = primaryScreenBounds.getHeight();
        
        System.out.println("Hi" + total + " Stagesize " + primaryStage.getHeight());
        
        double height = primaryScreenBounds.getHeight();
        double width = primaryScreenBounds.getWidth();
        double maxX = primaryScreenBounds.getMaxX();
        double maxY = primaryScreenBounds.getMaxY();
        double minX = primaryScreenBounds.getMinX();
        double minY = primaryScreenBounds.getMinY();
        
        //System.out.println("MinX:" + minX + "   MinY:" + minY + "   MaxX:" + maxX + "   MaxY:"+ maxX + "    Height:"+height + " Width:" + width);
        moveOut.getKeyFrames().addAll(
                new KeyFrame(new Duration(0),
                        new KeyValue(vbox.translateYProperty(), h-100),
                        new KeyValue(vbox.opacityProperty(), 0.9)
                        
                ),
                new KeyFrame(new Duration(150),
                        new KeyValue(vbox.translateYProperty(), h-50),
                        new KeyValue(vbox.opacityProperty(), 0.5)
                        
                ),
                new KeyFrame(new Duration(300),
                        new KeyValue(vbox.translateYProperty(), h),
                        new KeyValue(vbox.opacityProperty(), 0.0)
                        
                )
        );
        moveIn.getKeyFrames().addAll(
                new KeyFrame(new Duration(0),
                        new KeyValue(vbox.translateYProperty(), h),
                        new KeyValue(vbox.opacityProperty(), 0.0)
                ),
                new KeyFrame(new Duration(150),
                        new KeyValue(vbox.translateYProperty(), h-50),
                        new KeyValue(vbox.opacityProperty(), 0.5)
                ),
                new KeyFrame(new Duration(300),
                        new KeyValue(vbox.translateYProperty(), h-100),
                        new KeyValue(vbox.opacityProperty(), 0.9)
                )
        );
        
        moveFastIn.getKeyFrames().addAll(
                new KeyFrame(new Duration(0),
                        new KeyValue(vbox.translateYProperty(), 0),
                        new KeyValue(vbox.opacityProperty(), 0.0)
                )
        );
    }    
}
