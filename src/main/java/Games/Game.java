package Games;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.List;

import Snakes.Snake;
import Sockets.ServerSocketThread;
import Utils.Keyboard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public abstract class Game extends Application implements Runnable {


	public static boolean gameSnake1 = true;
	public static boolean gameSnake2 = true;

	public static List<String> choices;
	public static final String CONTROLLED_SNAKE = "Controlled Snake (Multiplayer) ";
	public static final String RANDOM_SNAKE="Random Snake";
	public static final String SMART_SNAKE = "Smart Snake";
	public static final String VERY_SMART_SNAKE = "Very Smart Snake";
	public static final String MULTIPLAYER_SERVER = "Server";
	public static final String MULTIPLAYER_CLIENT = "Client";

	public static String gameType;
	public static boolean server = false;
	public static boolean client = false;

	public static ServerSocket serverSocket;



	/** Speed of the game */
	static public final long DELAY_MS = 100L;

	/** Graphical window title */
	static public String TITLE = "Games.Game";

	/** Background Color */
	static public final Color BG_COLOR = Color.WHITE;

	/** The size of all the elements building up the grid */
	static public final double ELEMENT_SIZE = 32;
	
	/** The number of lines/columns in the grid */
	static public final int GRID_SIZE = 24;



	/** Graphical window size (square) */
	static public final double WINDOW_SIZE = GRID_SIZE*ELEMENT_SIZE;
	
	/** Root of the Java FX scene graph containing all the elements to display */
	private Group root;
	
	/** The timer for scheduling next game step */
	protected Timer timer;



	@Override
	public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
		// Create the root of the graph scene, and all its children 
		root = new Group();


		// create the scene graph
		Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE, BG_COLOR);

		scene.setFill(Color.WHITESMOKE);


		//scene.getStylesheets().add(Game.class.getResource("style.css").toExternalForm());


		// add a KeyEvent listener to the scene
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> Keyboard.storeLastKeyCode(event.getCode()));
		
		// terminate the Application when the window is closed
		primaryStage.setOnCloseRequest(event -> { Platform.exit(); System.exit(0); } );

		// Create the timer
		timer = new Timer();
		
		// Show a graphical window with all the graph scene content
		primaryStage.setScene(scene);
		primaryStage.setTitle(TITLE);
		primaryStage.setResizable(false);
		primaryStage.show();

		choices = new ArrayList<>();
		choices.add(CONTROLLED_SNAKE);
		choices.add(RANDOM_SNAKE);
		choices.add(SMART_SNAKE);
		choices.add(VERY_SMART_SNAKE);

		gameType = CONTROLLED_SNAKE;

		ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
		dialog.setTitle("Game type");
		dialog.setHeaderText("Choose your game difficulty");
		dialog.setContentText("Choose your Game Type:");

		Optional<String> result  =dialog.showAndWait();
		System.out.println("RESULT ::" + result.get());
		if(result.get().equals(CONTROLLED_SNAKE)){

			gameType = CONTROLLED_SNAKE;
			//timer.schedule(new GameTimerTask(this), DELAY_MS);
			System.out.println("In Contolled");

			ChoiceDialog<String> dialogMultiPlayer = new ChoiceDialog<>("", Arrays.asList(MULTIPLAYER_CLIENT, MULTIPLAYER_SERVER));
			dialogMultiPlayer.setTitle("Multiplayer Configuration");
			dialogMultiPlayer.setHeaderText("Choose your machine type");
			dialogMultiPlayer.setContentText("Choose your machine type:");
			Optional<String> resultMultiPlayer  =dialogMultiPlayer.showAndWait();
			if(resultMultiPlayer.get().equals(MULTIPLAYER_CLIENT)){
				System.out.println("THIS IS A CLIENT");
				server = false;
				client = true;
			}
			if(resultMultiPlayer.get().equals(MULTIPLAYER_SERVER)){
				System.out.println("THIS IS A SERVER");
				server = true;
				client = false;



			}
		}

		if(result.get().equals(RANDOM_SNAKE)){
			gameType = RANDOM_SNAKE;
			//System.out.println("RAAAANDOMMMMM");
			//timer.schedule(new GameTimerTask(this), DELAY_MS);
		}

		if(result.get().equals(SMART_SNAKE)){
			gameType = SMART_SNAKE;
			//timer.schedule(new GameTimerTask(this), DELAY_MS);
		}

		if(result.get().equals(VERY_SMART_SNAKE)){
			gameType = VERY_SMART_SNAKE;
			//timer.schedule(new GameTimerTask(this), DELAY_MS);
		}




		timer.schedule(new GameTimerTask(this), DELAY_MS);






	}	
	

	private class GameTimerTask extends TimerTask {
		private Runnable gameStep;
		
		public GameTimerTask(Runnable gameStep) { this.gameStep = gameStep; }


		public void run() {

			Platform.runLater(gameStep); }

	}


	final public void run() {
		root.getChildren().setAll(gameStep());
		//timer.schedule(new GameTimerTask(this), DELAY_MS);
		if(gameSnake1 && gameSnake2){
			timer.schedule(new GameTimerTask(this), DELAY_MS);
		}

		else if (!gameSnake1  && gameSnake2) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Victory");
			alert.setContentText("Congrats !! You won");
			alert.showAndWait();
		}

		else if (!gameSnake2 && gameSnake1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Game Over !");
			alert.setContentText("You Lost, Try Again !!");
			alert.showAndWait();
		}

		else if (!gameSnake1 && !gameSnake2){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Equality");
			alert.setContentText("Equality !!");
			alert.showAndWait();
		}
		else {

		}


	}
	

	public abstract Collection<Node> gameStep();


	public void initServerSocket() throws IOException {
		serverSocket = new ServerSocket(9090);

	}
}
