package mplayer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MediaMessages {
    public void OkCancelMessage(String title , String message){
        Stage alertBox = new Stage();
        alertBox.initModality(Modality.APPLICATION_MODAL );
        alertBox.setTitle(title);
        alertBox.setMinWidth(200);
        alertBox.setMinHeight(200);
        
        Button okButton = new Button("Ok");
        Button cancelButton = new Button("Cancel");
        
        Label displayText = new Label();
        displayText.setText( message );
        
        HBox buttonSet = new HBox();
        buttonSet.setPadding( new Insets(20,10,20,10));
        buttonSet.getChildren().addAll( okButton, cancelButton );
        buttonSet.setAlignment(Pos.CENTER);
        VBox layout = new VBox();
        
        layout.getChildren().addAll( displayText, buttonSet );
        
        Scene scene = new Scene( layout );
        alertBox.setScene(scene);
        alertBox.showAndWait();
    }
    
    public void CloseMessage(String title , String message){
        Stage alertBox = new Stage();
        alertBox.initModality(Modality.APPLICATION_MODAL );
        alertBox.setTitle(title);
        alertBox.setMinWidth(450);
        alertBox.setMinHeight(200);
        
        Button closeButton = new Button("Close");
        
        Label displayText = new Label();
        displayText.setText( message );
        
        HBox buttonSet = new HBox();
        buttonSet.getChildren().add( closeButton );
        buttonSet.setAlignment(Pos.CENTER);
        buttonSet.setPadding( new Insets(20,10,20,10));
        
        closeButton.setOnAction( e -> alertBox.close());
        VBox layout = new VBox();
        
        layout.getChildren().addAll( displayText, buttonSet );
        
        Scene scene = new Scene( layout);
        alertBox.setScene(scene);
        alertBox.showAndWait();
    }
}
