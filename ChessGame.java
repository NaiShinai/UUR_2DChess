package chess;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Trida reprezentujici aplikaci Sachy
 * @author Jan Janis
 *
 */
public class ChessGame extends Application {
	/** hlavní stage programu */
	private Stage window;
	/** tlacitka pro volby barev hrace*/
	private final RadioButton whiteRB1 = new RadioButton("Bílá");
	private final RadioButton blackRB1 = new RadioButton("Èerná");
	private final RadioButton whiteRB2 = new RadioButton("Bílá");
	private final RadioButton blackRB2 = new RadioButton("Èerná");
	/** zvukovy soubor kliknuti na tlacitko */
	public final File click = new File("click.wav");
	
	/**
	 * Spusti aplikaci
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Zobrazi menu hry
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

		window.setTitle("2DChess");
		window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		window.setMinHeight(900);
		window.setMinWidth(800);

		window.setScene(getMainMenuScene());

		window.show();
	}


	/*********************************************************************************
	 *   Metody pro hlavni menu   *
	 *********************************************************************************

	/**
	 * Vytvori scenu hlavniho menu s jednotlivymi komponentami
	 * @return scena hlavniho menu
	 */
	private Scene getMainMenuScene() {
		Scene scene = new Scene(getMainMenuRoot(), 800, 700);
		return scene;
	}

	/**
	 * Vytvori root hlavniho menu typu border pane
	 * @return borderpane, root hlavniho menu
	 */
	private Parent getMainMenuRoot() {
		BorderPane rootBP = new BorderPane();

		rootBP.setTop(getMainMenuTitle());
		rootBP.setCenter(getMainMenuButtons());
		rootBP.setStyle("-fx-background-color: rgb(240, 210, 150);");
		return rootBP;
	}

	/**
	 * Vytvori tlacitka s moznostmi v hlavnim menu v podobe vboxu
	 * @return Vbox s tlacitky pro novou hru, nacteni hry a ukonceni aplikace
	 */
	private Node getMainMenuButtons() {
		VBox menuButtonsVB = new VBox();
		//font pro text tlacitek
		Font font = new Font("Impact", 55);

		// ***** tlacitka v hlavnim menu
		// tlacitko pro novou hru - prenese uzivatele na obrazovku pro novou hru
		Button newGameBT = new Button("Nová hra");
		newGameBT.setMinSize(320, 100);
		newGameBT.setFont(font);
		newGameBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");
		//nastavi scenu pro menu nove hry
		newGameBT.setOnAction(event -> { 
			//prehraje zvuk po kliknuti
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(click));
				clip.start();
			}
			catch(Exception e) {
			}
			//prepne scenu
			window.hide();
			window.setScene(getNewGameMenuScene());
			window.show();
		});
		//uprava grafiky tlacitka
		newGameBT.setOnMouseEntered(event -> {
			newGameBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(210,105,30);"
					+ "-fx-border-width: 5");
		});
		newGameBT.setOnMouseExited(event -> {
			newGameBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(128, 128, 128)");
		});

		// tlacitko pro nacteni hry
		Button loadGameBT = new Button("Naèíst hru");
		loadGameBT.setMinSize(320, 100);
		loadGameBT.setFont(font);
		loadGameBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");
		//uprava grafiky tlacitka
		loadGameBT.setOnMouseEntered(event -> {
			loadGameBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(210,105,30);"
					+ "-fx-border-width: 5");
		});
		loadGameBT.setOnMouseExited(event -> {
			loadGameBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(128, 128, 128)");
		});
		
		// tlacitko pro ukonceni hry
		Button exitBT = new Button("Konec hry");
		exitBT.setMinSize(320, 100);
		exitBT.setFont(font);
		exitBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");
		//ukonci aplikaci a zobrazi uzivateli dialogove okno s potvrzenim ano/ne
		exitBT.setOnAction(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();

			ButtonType yesBT = new ButtonType("Ano");
			ButtonType noBT = new ButtonType("Ne");

			alert.setTitle("Upozornìní");
			alert.setHeaderText("Opravdu chete ukonèit aplikaci?");
			alert.getButtonTypes().clear();
			alert.getButtonTypes().addAll(yesBT, noBT);
			alertStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
			alertStage.showAndWait();

			//pokud uzivatel zvoli ano - ukonci aplikaci
			if(alert.getResult() == yesBT) {
				//prehraje zvuk po kliknuti
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(click));
					clip.start();
				}
				catch(Exception e) {
				}
				Platform.exit();
			}
			alert.close();
		});
		//uprava grafiky tlacitka
		exitBT.setOnMouseEntered(event -> {
			exitBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(210,105,30);"
					+ "-fx-border-width: 5");
		});
		exitBT.setOnMouseExited(event -> {
			exitBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(128, 128, 128)");
		});

		//pridani tlacitek do Vboxu
		menuButtonsVB.setSpacing(25);
		menuButtonsVB.setPadding(new Insets(40));
		menuButtonsVB.getChildren().addAll(newGameBT, loadGameBT, exitBT);
		menuButtonsVB.setAlignment(Pos.BOTTOM_CENTER);

		return menuButtonsVB;
	}

	/**
	 * Vytvori grafiku hlavniho menu se jmenem hry jako stack pane
	 * @return obrazek s titulkem
	 */
	private Node getMainMenuTitle() {
		StackPane titleSP = new StackPane();
		Image title = new Image(getClass().getResourceAsStream("title.png"));
		ImageView titleView = new ImageView(title);

		titleSP.getChildren().add(titleView);

		return titleSP;
	}

	/*********************************************************************************
	 *   Metody pro menu nove hry   *
	 *********************************************************************************

	/**
	 * Vytvori scenu menu nove hry s jednotlivymi komponentami
	 * @return scena
	 */
	private Scene getNewGameMenuScene() {
		Scene scene = new Scene(getNewGameMenuRoot(), 800, 700);
		return scene;
	}

	/**
	 * Vytvori border pane menu nove hry, jako root
	 * @return
	 */
	private Parent getNewGameMenuRoot() {
		BorderPane rootBP = new BorderPane();

		rootBP.setTop(getNewGameMenuTitle());
		rootBP.setCenter(getPlayerSelection());
		rootBP.setBottom(getNewGameMenuImage());
		rootBP.setStyle("-fx-background-color: rgb(240, 210, 150);");

		return rootBP;
	}

	/**
	 * Vytvori grafiku v menu nove hry se jmenem hry jako stack pane
	 * @return obrazek s titulkem
	 */
	private Node getNewGameMenuTitle() {
		StackPane titleSP = new StackPane();
		Image title = new Image(getClass().getResourceAsStream("title.png"));
		ImageView titleView = new ImageView(title);

		titleSP.getChildren().add(titleView);

		return titleSP;
	}

	/**
	 * Vytvori grid pane predstavujici moznosti pro nastaveni hracu
	 * @return gridpane - nastaveni hracu pred hrou
	 */
	private Node getPlayerSelection() {
		GridPane selectionGP = new GridPane();

		// togglegroup volby barvy
		ToggleGroup toggleColors1 = new ToggleGroup();
		ToggleGroup toggleColors2 = new ToggleGroup();
		// togglegroup pro vyber typu hry
		ToggleGroup toggleGame = new ToggleGroup();

		//fonty pro pisma v gridpanu
		Font fontDescription = new Font("Impact", 32);
		Font fontOptions = new Font("Impact", 20);

		// ***** textfieldy pro nastaveni jmena hrace
		// hrac 1
		TextField player1TF = new TextField();
		player1TF.setMaxWidth(100);
		player1TF.setMinHeight(35);
		player1TF.setPromptText("Hráè 1");
		player1TF.setFont(fontOptions);
		player1TF.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");
		
		// hrac 2
		TextField player2TF = new TextField();
		player2TF.setMaxWidth(100);
		player2TF.setMinHeight(35);
		player2TF.setPromptText("Hráè 2");
		player2TF.setFont(fontOptions);
		player2TF.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");

		// ***** radiobuttons pro volbu barvy hracu
		// bila barva hrac 1
		whiteRB1.setToggleGroup(toggleColors1);
		whiteRB1.setFont(fontOptions);
		//pokud je vybrana bila barva 1, automaticky vybere cernou barvu 2
		whiteRB1.setOnAction(event -> {
			blackRB2.setSelected(true);
			whiteRB2.setSelected(false);
		});
		
		// cerna barva hrac 1
		blackRB1.setToggleGroup(toggleColors1);
		blackRB1.setFont(fontOptions);
		// pokud je vybrana cerna barva 1, automaticky vybere bilou barvu 2
		blackRB1.setOnAction(event -> {
			whiteRB2.setSelected(true);
			blackRB2.setSelected(false);
		});
		
		// bila barva hrac 2
		whiteRB2.setToggleGroup(toggleColors2);
		whiteRB2.setFont(fontOptions);
		// pokud je vybrana bila barva 2, automaticky vybere cernou barvu 1
		whiteRB2.setOnAction(event -> {
			blackRB1.setSelected(true);
			whiteRB1.setSelected(false);
		});
		
		// cerna barva hrac 2
		blackRB2.setToggleGroup(toggleColors2);
		blackRB2.setFont(fontOptions);
		// pokud je vybrana cerna barva 2, automaticky vybere bilou barvu 1
		blackRB2.setOnAction(event -> {
			whiteRB1.setSelected(true);
			blackRB1.setSelected(false);
		});
		
		// ***** radiobuttons pro vyber typu hry
		// klasicka hra (na cas)
		RadioButton classicRB = new RadioButton("Klasická");
		classicRB.setToggleGroup(toggleGame);
		classicRB.setFont(fontOptions);
		// neomezena hra
		RadioButton unlimitedRB = new RadioButton("Neomezená");
		unlimitedRB.setToggleGroup(toggleGame);
		unlimitedRB.setFont(fontOptions);
		
		// ***** labels specifikujici menu volby
		// jmeno hracu
		Label nameLB = new Label("Jméno:");
		nameLB.setFont(fontDescription);
		
		// barva hracu
		Label barvaLB = new Label("Barva:");
		barvaLB.setFont(fontDescription);
		
		// styl hry (klasicka (na cas) / neomezena
		Label gameLB = new Label("Styl hry:");
		gameLB.setFont(fontDescription);

		// ***** tlacitka v menu pro novou hru
		// tlacitko pro navrat do hlavniho menu
		Button backBT = new Button("Zpìt");
		backBT.setMinHeight(65);
		backBT.setFont(fontDescription);
		backBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");
		GridPane.setHalignment(backBT, HPos.CENTER);
		// vrati uzivatele do hlavniho menu a zobrazi mu dialogove okno s potvrzenim ano/ne
		backBT.setOnAction(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
			ButtonType yesBT = new ButtonType("Ano");
			ButtonType noBT = new ButtonType("Ne");

			alert.setTitle("Upozornìní");
			alert.setHeaderText("Chcete se vrátit do hlavního menu?\nNastavení pro novou hru nebudou uložena");
			alert.getButtonTypes().clear();
			alert.getButtonTypes().addAll(yesBT, noBT);
			alertStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
			alertStage.showAndWait();
			
			// pokud uzivatel zvoli ano - vrati se do hlavniho menu, jinak zavre alert
			if(alert.getResult() == yesBT) {
				//prehraje zvuk po kliknuti
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(click));
					clip.start();
				}
				catch(Exception e) {
				}
				window.hide();
				window.setScene(getMainMenuScene());
				window.show();
			}
			alert.close();
		});
		// uprava grafiky tlacitka
		backBT.setOnMouseEntered(event -> {
			backBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(210,105,30);"
					+ "-fx-border-width: 5");
		});
		backBT.setOnMouseExited(event -> {
			backBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(128, 128, 128)");
		});

		// tlacitko pro pokracovani do hry
		Button continueBT = new Button("Pokraèovat");
		continueBT.setFont(fontDescription);
		continueBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
				+ "-fx-border-color: rgb(128, 128, 128)");
		GridPane.setHalignment(continueBT, HPos.CENTER);
		// uprava grafiky tlacitka
		continueBT.setOnMouseEntered(event -> {
			continueBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(210,105,30);"
					+ "-fx-border-width: 5");
		});
		continueBT.setOnMouseExited(event -> {
			continueBT.setStyle("-fx-background-color: rgb(211, 211, 211);"
					+ "-fx-border-color: rgb(128, 128, 128)");
		});

		// pridani nodes do gridpane
		selectionGP.add(nameLB, 0, 0);
		selectionGP.add(barvaLB, 1, 0, 2, 1);
		selectionGP.add(gameLB, 3, 0, 2, 1);
		selectionGP.add(whiteRB1, 1, 1);//nejdrive pridan jeden radiobutton z duvodu automatickeho vyberu prvniho pridaneho prvku 
		selectionGP.add(player1TF, 0, 1);
		selectionGP.add(player2TF, 0, 2);
		selectionGP.add(blackRB1, 2, 1);
		selectionGP.add(whiteRB2, 1, 2);
		selectionGP.add(blackRB2, 2, 2);
		selectionGP.add(classicRB, 3, 1);
		selectionGP.add(unlimitedRB, 3, 2);
		selectionGP.add(backBT, 2, 4, 2, 1);
		selectionGP.add(continueBT, 0, 4, 2, 1);

		selectionGP.setHgap(40);
		selectionGP.setVgap(15);
		selectionGP.setStyle("-fx-background-color: rgb(240, 210, 150);");

		selectionGP.setGridLinesVisible(false);
		selectionGP.setAlignment(Pos.TOP_CENTER);

		return selectionGP;
	}
	/**
	 * Vytvori graficky prvek v menu s novou hrou jako stackpane
	 * @return obrazek
	 */
	private Node getNewGameMenuImage() {
		StackPane imageSP = new StackPane();
		Image image = new Image(getClass().getResourceAsStream("players.png"));
		ImageView imageView = new ImageView(image);

		imageSP.getChildren().add(imageView);
		imageSP.setAlignment(Pos.CENTER);

		return imageSP;
	}

}
