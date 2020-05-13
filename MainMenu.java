package chess;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application{

	/**
	 * Spusti aplikaci
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Zobrazi hlavni menu hry
	 */
	@Override
	public void start(Stage mainMenuStage) throws Exception {
		mainMenuStage.setTitle("2DChess");

		mainMenuStage.setScene(getScene());
		
		mainMenuStage.setMinHeight(900);
		mainMenuStage.setMinWidth(800);
		
		mainMenuStage.show();
	}
	
	/**
	 * Vytvori scenu s jednotlivymi komponentami
	 * @return scene
	 */
	private Scene getScene() {
		Scene scene = new Scene(getRoot(), 800, 700);
		return scene;
	}
	
	/**
	 * Vytvori root typu border pane
	 * @return
	 */
	private Parent getRoot() {
		BorderPane rootBP = new BorderPane();
		
		rootBP.setTop(getTitle());
		rootBP.setCenter(getMenuButtons());
		rootBP.setStyle("-fx-background-color: rgb(240, 210, 150);");
		return rootBP;
	}
	
	private Node getMenuButtons() {
		VBox menuButtonsVB = new VBox();
		Button newGame = new Button("Nová hra");
		newGame.setMinSize(200, 100);
		newGame.setStyle(
			    "-fx-background-radius: 4em; " +
			    		"-fx-font-size: 4em; " +
			            "-fx-min-width: 200px; " +
			            "-fx-min-height: 100px; " +
			            "-fx-max-width: 400px; " +
			            "-fx-max-height: 200px; " +
			            "-fx-background-color: rgb(211, 211, 211);" +
			            "-fx-background-insets: 0px; " +
			            "-fx-padding: 0px;"
			    );
		Button loadGame = new Button("Naèíst hru");
		loadGame.setMinSize(200, 100);
		loadGame.setStyle(
			    "-fx-background-radius: 4em; " +
			    		"-fx-font-size: 4em; " +
			            "-fx-min-width: 200px; " +
			            "-fx-min-height: 100px; " +
			            "-fx-max-width: 400px; " +
			            "-fx-max-height: 200px; " +
			            "-fx-background-color: rgb(211, 211, 211);" +
			            "-fx-background-insets: 0px; " +
			            "-fx-padding: 0px;"
			    );
		Button exit = new Button("Konec hry");
		exit.setMinSize(200, 100);
		exit.setStyle(
			    "-fx-background-radius: 4em; " +
			    		"-fx-font-size: 4em; " +
			            "-fx-min-width: 200px; " +
			            "-fx-min-height: 100px; " +
			            "-fx-max-width: 400px; " +
			            "-fx-max-height: 200px; " +
			            "-fx-background-color: rgb(211, 211, 211);" +
			            "-fx-background-insets: 0px; " +
			            "-fx-padding: 0px;"
			    );
		
		menuButtonsVB.setSpacing(25);
		menuButtonsVB.setPadding(new Insets(40));
		menuButtonsVB.getChildren().addAll(newGame, loadGame, exit);
		menuButtonsVB.setAlignment(Pos.BOTTOM_CENTER);
		
		return menuButtonsVB;
	}
	
	private Node getTitle() {
		StackPane titleSP = new StackPane();
		Image title = new Image(getClass().getResourceAsStream("title.png"));
		ImageView titleView = new ImageView(title);
		
		titleSP.getChildren().add(titleView);
		
		return titleSP;
	}
}
