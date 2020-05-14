package chess;



import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Trida reprezentujici obrazovku s volbami pro novou hru.
 * Tato obrazovka se zobrazi po kliknuti na tlacitko 'Nova hra'.
 * @author Jan Janis
 *
 */
public class NewGameMenu extends Application{
	/** tlacitka pro volby barev */
	private final RadioButton whiteRB1 = new RadioButton("Bílá");
	private final RadioButton blackRB1 = new RadioButton("Èerná");
	private final RadioButton whiteRB2 = new RadioButton("Bílá");
	private final RadioButton blackRB2 = new RadioButton("Èerná");

	/**
	 * Spusti nastaveni pro novou hru
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

	/**
	 * Zobrazi menu pro novou hru
	 */
	@Override
	public void start(Stage newGameStage) throws Exception {
		newGameStage.setTitle("2DChess");


		newGameStage.setScene(getScene());
		newGameStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

		newGameStage.setMinHeight(900);
		newGameStage.setMinWidth(800);

		newGameStage.show();
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
	 * Vytvori border pane jako root
	 * @return
	 */
	private Parent getRoot() {
		BorderPane rootBP = new BorderPane();

		rootBP.setTop(getTitle());
		rootBP.setCenter(getPlayerSelection());
		rootBP.setBottom(getImage());
		rootBP.setStyle("-fx-background-color: rgb(240, 210, 150);");

		return rootBP;
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

	/**
	 * Vytvori novy border pane predstavujici moznosti pro volbu hracu
	 * @return
	 */
	private Node getPlayerSelection() {
		GridPane selectionGP = new GridPane();

		//togglegroup volby barvy
		ToggleGroup toggleColors1 = new ToggleGroup();
		ToggleGroup toggleColors2 = new ToggleGroup();

		//fonty pro pisma v gridpanu
		Font fontDescription = new Font("Impact", 32);
		Font fontOptions = new Font("Impact", 20);

		//textfieldy pro nastaveni jmena hrace
		TextField player1TF = new TextField();
		player1TF.setMaxWidth(100);
		player1TF.setMinHeight(35);
		player1TF.setPromptText("Hráè 1");
		player1TF.setFont(fontOptions);
		player1TF.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");
		TextField player2TF = new TextField();
		player2TF.setMaxWidth(100);
		player2TF.setMinHeight(35);
		player2TF.setPromptText("Hráè 2");
		player2TF.setFont(fontOptions);
		player2TF.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");

		//radiobuttons pro volbu barvy hracu
		whiteRB1.setToggleGroup(toggleColors1);
		whiteRB1.setFont(fontOptions);
		whiteRB1.setOnAction(event -> {
			blackRB2.setSelected(true);
			whiteRB2.setSelected(false);
		});
		blackRB1.setToggleGroup(toggleColors1);
		blackRB1.setFont(fontOptions);
		blackRB1.setOnAction(event -> {
			whiteRB2.setSelected(true);
			blackRB2.setSelected(false);
		});
		whiteRB2.setToggleGroup(toggleColors2);
		whiteRB2.setFont(fontOptions);
		whiteRB2.setOnAction(event -> {
			blackRB1.setSelected(true);
			whiteRB2.setSelected(false);
		});

		blackRB2.setToggleGroup(toggleColors2);
		blackRB2.setFont(fontOptions);
		blackRB2.setOnAction(event -> {
			whiteRB1.setSelected(true);
			blackRB2.setSelected(false);
		});

		//labels specifikujici menu volby
		Label name = new Label("Jméno:");
		name.setFont(fontDescription);
		Label barva = new Label("Barva:");
		barva.setFont(fontDescription);
		//nastaveni pozice labelu v bunce
		GridPane.setHalignment(barva, HPos.CENTER);	

		//pridani nodes do gridpane
		selectionGP.add(name, 0, 0);
		selectionGP.add(barva, 1, 0, 2, 1);
		selectionGP.add(whiteRB1, 1, 1);
		selectionGP.add(player1TF, 0, 1);
		selectionGP.add(player2TF, 0, 2);
		selectionGP.add(blackRB1, 2, 1);
		selectionGP.add(whiteRB2, 1, 2);
		selectionGP.add(blackRB2, 2, 2);

		//selectionGP.setGridLinesVisible(true);
		selectionGP.setHgap(30);
		selectionGP.setVgap(15);
		selectionGP.setStyle("-fx-background-color: rgb(240, 210, 150);");

		selectionGP.setAlignment(Pos.TOP_CENTER);

		return selectionGP;
	}
	/**
	 * Vytvori graficky prvek v menu s novou hrou jako stackpane
	 * @return obrazek
	 */
	private Node getImage() {
		StackPane imageSP = new StackPane();
		Image image = new Image(getClass().getResourceAsStream("players.png"));
		ImageView imageView = new ImageView(image);

		imageSP.getChildren().add(imageView);

		return imageSP;
	}
}