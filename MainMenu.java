package chess;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Trida reprezentujici obrazovku s hlavnim menu hry.
 * Tato obrazovka se zobrazi po spusteni aplikace.
 * @author Jan Janis
 *
 */
public class MainMenu extends Application{

	/**
	 * Spusti hlavni menu.
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
		mainMenuStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		
		mainMenuStage.setMinHeight(900);
		mainMenuStage.setMinWidth(800);
		
		mainMenuStage.show();
	}
	
	/**
	 * Vytvori scenu s jednotlivymi komponentami
	 * @return scena
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
	
	/**
	 * Vytvori tlacitka s moznostmi v hlavnim menu v podobe vboxu
	 * @return Vbox s tlacitky pro novou hru, nacteni hry a ukonceni aplikace
	 */
	private Node getMenuButtons() {
		VBox menuButtonsVB = new VBox();
		
		//font pro text tlacitek
		Font font = new Font("Impact", 55);
		
		//tlacitka v hlavnim menu
		Button newGame = new Button("Nová hra");
		newGame.setMinSize(320, 100);
		newGame.setFont(font);
		newGame.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");
		Button loadGame = new Button("Naèíst hru");
		loadGame.setMinSize(320, 100);
		loadGame.setFont(font);
		loadGame.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");
		Button exit = new Button("Konec hry");
		exit.setMinSize(320, 100);
		exit.setOnAction(event -> Platform.exit());
		exit.setFont(font);
		exit.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(128, 128, 128)");
		
		//pridani tlacitek do Vboxu
		menuButtonsVB.setSpacing(25);
		menuButtonsVB.setPadding(new Insets(40));
		menuButtonsVB.getChildren().addAll(newGame, loadGame, exit);
		menuButtonsVB.setAlignment(Pos.BOTTOM_CENTER);
		
		return menuButtonsVB;
	}
	
	/**
	 * Vytvori grafiku se jmenem hry jako stack pane
	 * @return obrazek s titulkem
	 */
	private Node getTitle() {
		StackPane titleSP = new StackPane();
		Image title = new Image(getClass().getResourceAsStream("title.png"));
		ImageView titleView = new ImageView(title);
		
		titleSP.getChildren().add(titleView);
		
		return titleSP;
	}
}
