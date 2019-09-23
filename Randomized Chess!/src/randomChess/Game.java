//Srikar Indrakanti, 112265102
package randomChess;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//Run this file!
public class Game extends Application {
	
	public void start(Stage primaryStage){		
		try {			
			primaryStage.setTitle("Randomized Chess!");
			FileInputStream imageLoc = new FileInputStream("src/randomChess/resources/images/wr.png");
			primaryStage.getIcons().add(new Image(imageLoc));
			
			Board chess = new Board();
			BorderPane borderPane = new BorderPane();
			borderPane.setCenter(chess);
			Scene scene = new Scene(borderPane);
			
			//Creating MenuBar
			MenuBar menuBar = new MenuBar();
			Menu menu= new Menu("Game");
			MenuItem newGame = new MenuItem("New Game");
	        newGame.setOnAction(e -> chess.newGame());
	        menu.getItems().add(newGame);
	        MenuItem rules = new MenuItem("Rules");
	        rules.setOnAction(e -> showRules());
	        menu.getItems().add(rules);
	        menuBar.getMenus().add(menu);
	        borderPane.setTop(menuBar);
	        
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			System.out.println("ayylmao");
		}
	}
	
	public void showRules(){
		Alert rules = new Alert(AlertType.INFORMATION);
        rules.setTitle("Randomized Chess! Rules");
        rules.setHeaderText("Rules:"); 
        rules.setContentText(
        	"The rules of Randomized Chess! are very close to those of regular chess. The differences are:\n\n" +
            "	*All the starting squares have a weighted random chance to have each piece, except the kings' squares. The pieces are mirrored except the queens' squares\n\n" +
            "	*NO Castling or En Passant! These moves just dont make sense.\n\n" +
            "	*NO checks or checkmate: the enemy king must be captured to win.\n\n" +
            "Made by Srikar Indrakanti. Enjoy!");
        rules.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


