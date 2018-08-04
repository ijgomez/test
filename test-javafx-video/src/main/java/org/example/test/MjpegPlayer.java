package org.example.test;


import java.io.File;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class MjpegPlayer extends Application {
	public static void main(String[] args) {
	    launch(args);
	  }
	  
	  @Override
	  public void start(Stage primaryStage) {
	    String workingDir = System.getProperty("user.dir");
	    final File f = new File(workingDir, "video1.mp4");
	    
	    final Media m = new Media(f.toURI().toString());
	    final MediaPlayer mp = new MediaPlayer(m);
	    final MediaView mv = new MediaView(mp);
	    
	    final DoubleProperty width = mv.fitWidthProperty();
	    final DoubleProperty height = mv.fitHeightProperty();
	    
	    width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
	    height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
	    
	    mv.setPreserveRatio(true);
	    
	    StackPane root = new StackPane();
	    root.getChildren().add(mv);
	    
	    final Scene scene = new Scene(root, 960, 540);
	    scene.setFill(Color.BLACK);
	    
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Full Screen Video Player");
	    primaryStage.setFullScreen(true);
	    primaryStage.show();
	    
	    mp.play();
	  }
}
