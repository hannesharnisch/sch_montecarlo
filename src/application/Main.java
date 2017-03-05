package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Main extends Application {
	private static KeyCombination kombostart = new KeyCodeCombination(KeyCode.ENTER);
	private static KeyCombination komboreset = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
	private Label description = new Label("Anazahl der Regentropfen: ");
	private TextField inputraindrops = new TextField();
	private Button start = new Button("Start");
	private Button reset = new Button("Reset");
	private Label pilabel = new Label("Annäherung an pi: ");

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Monte Carlo");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 600, 600);
			root.setBackground(null);
			scene.setFill(Color.DARKGREY);
			System.out.println(scene.getHeight()-100);
			HBox hbox = new HBox();
			HBox hboxbot = new HBox();
			HBox hboxbot1 = new HBox();
			HBox hboxbot2 = new HBox();
			hbox.getChildren().addAll(description, inputraindrops, start);
			Group groupcenter = new Group();
			hboxbot1.getChildren().add(pilabel);
			hboxbot2.getChildren().add(reset);
			hboxbot1.setAlignment(Pos.CENTER_RIGHT);
			hboxbot2.setAlignment(Pos.BOTTOM_LEFT);
			addtooltips();
			// hboxbot.setAlignment(Pos.CENTER);
			hboxbot.getChildren().addAll(hboxbot2, hboxbot1);
			setStyles(hbox);
			addlayout(groupcenter, scene);
			root.setTop(hbox);
			root.setCenter(groupcenter);
			root.setBottom(hboxbot);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			start.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					MonteCarlo montecarlo = new MonteCarlo();
					try {
						pilabel.setTextFill(Color.BLACK);
						montecarlo.init(Integer.parseInt(inputraindrops.getText()));
						double pi = montecarlo.calculate(groupcenter, scene);
						pilabel.setText("Annäherung an pi: " + pi);
					} catch (Exception e) {
						pilabel.setText("Keine Zahl");
						pilabel.setTextFill(Color.RED);
					}
				}
			});
			reset.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					start(primaryStage);
				}
			});
			scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (kombostart.match(event)) {
						MonteCarlo montecarlo = new MonteCarlo();
						try {
							pilabel.setTextFill(Color.BLACK);
							montecarlo.init(Integer.parseInt(inputraindrops.getText()));
							double pi = montecarlo.calculate(groupcenter, scene);
							pilabel.setText("Annäherung an pi: " + pi);
						} catch (Exception e) {
							pilabel.setText("Keine Zahl");
							pilabel.setTextFill(Color.RED);
						}
					} else if (komboreset.match(event)) {
						start(primaryStage);
					}
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addtooltips() {
		// TODO Auto-generated method stub
		Tooltip tbstart = new Tooltip("Simmuliere die Anzahl von Regentropfen  Shortcut: ENTER");
		Tooltip.install(start, tbstart);
		Tooltip tbreset = new Tooltip("Lösche alle Regentropfen  Shortcut: CONTROL+R");
		Tooltip.install(reset, tbreset);
		Tooltip tbpi = new Tooltip("Ausgerechnete Annäherung an pi nach dem Monte Carlo Prinzip");
		Tooltip.install(pilabel, tbpi);
	}

	private void addlayout(Group groupcenter, Scene scene) {
		// TODO Auto-generated method stub
		Arc c = new Arc(50, 50, scene.getWidth() - 100, scene.getHeight() - 100, 0, 90);
		c.setFill(null);
		c.setStroke(Color.BLACK);
		c.setStrokeWidth(2);
		c.getTransforms().add(new Translate(0, scene.getHeight() - 100));
		Rectangle rect = new Rectangle(50, 50, scene.getWidth()-100, scene.getHeight()-100);
		rect.setFill(Color.WHITE);
		rect.setStrokeWidth(2);
		rect.setStroke(Color.BLACK);
		groupcenter.getChildren().addAll(rect,c);
	}

	private void setStyles(HBox hbox) {
		// TODO Auto-generated method stub
		hbox.setAlignment(Pos.CENTER);
		description.setFont(Font.font(16));
		pilabel.setFont(Font.font(16));
		inputraindrops.setPromptText("ganze Zahl");
		start.setStyle(
				"-fx-text-fill: BLUE;-fx-skin: com.sun.javafx.scene.control.skin.ButtonSkin;-fx-background-color: transparent;-fx-background-insets: 0 0 0 0, 0, 0, 0;-fx-background-radius: 0 0 0 0, 0, 0, 0; -fx-border-width: 0 0 0 0, 0, 0, 0;");
		reset.setStyle(
				"-fx-skin: com.sun.javafx.scene.control.skin.ButtonSkin;-fx-background-color: transparent;-fx-background-insets: 0 0 0 0, 0, 0, 0;-fx-background-radius: 0 0 0 0, 0, 0, 0; -fx-border-width: 0 0 0 0, 0, 0, 0;");
		

	}

	public static void main(String[] args) {
		launch(args);
	}
}
