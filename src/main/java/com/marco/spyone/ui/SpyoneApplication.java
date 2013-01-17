package com.marco.spyone.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import com.marco.spyone.services.GrepState;

public class SpyoneApplication extends Application {

	private final GrepState grepState = new GrepState();

	private final BorderPane mainPane = new BorderPane();

	private final BarChartPanel barChartPanel = new BarChartPanel(grepState);

	private final GrepSearchPanel grepSearchPanel = new GrepSearchPanel(grepState);

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(SpyoneApplication.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		mainPane.setTop(getTopButtons(barChartPanel));
		mainPane.setCenter(grepSearchPanel);

		Scene scene = new Scene(mainPane);
		stage.setScene(scene);
		stage.setTitle("Spyone");
		stage.show();
	}

	private Node getTopButtons(final BarChartPanel chart) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10); // Gap between nodes
		hbox.setStyle("-fx-background-color: #336699;");

		Button buttonCharts = new Button("Bar Chart");
		buttonCharts.setPrefSize(150, 20);
		buttonCharts.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				mainPane.setCenter(barChartPanel);
				barChartPanel.refreshFromGrepState();

			}
		});

		Button serverSettingButton = new Button("Server Setting");
		serverSettingButton.setPrefSize(150, 20);
		serverSettingButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				mainPane.setCenter(grepSearchPanel);
			}
		});

		hbox.getChildren().addAll(serverSettingButton, buttonCharts);

		return hbox;
	}

}
