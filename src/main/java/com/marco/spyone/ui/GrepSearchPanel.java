package com.marco.spyone.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.grep4j.core.model.Profile;

import com.marco.spyone.services.Grep4jServices;
import com.marco.spyone.services.GrepState;
import com.marco.spyone.services.SpyOneProfile;

public class GrepSearchPanel extends VBox {

	public GrepSearchPanel(final GrepState grepState) {

		HBox searchHBox = new HBox();
		searchHBox.setPadding(new Insets(15, 12, 15, 12));
		searchHBox.setSpacing(10); // Gap between nodes

		searchHBox.getChildren().add(new Label("Search For "));

		final TextField searchTerm = new TextField("1062866476");
		searchHBox.getChildren().add(searchTerm);

		CheckBox isRegExpression = new CheckBox("RE");
		searchHBox.getChildren().add(isRegExpression);

		final String[] names = new String[] { "PMU", "PP", "SB" };
		final CheckBox[] cbs = new CheckBox[names.length];
		final Separator separator = new Separator();
		final VBox vbox = new VBox();

		for (int i = 0; i < names.length; i++) {
			cbs[i] = new CheckBox(names[i]);
		}

		separator.setMaxWidth(40);

		vbox.getChildren().addAll(cbs);
		vbox.setSpacing(5);
		vbox.getChildren().add(3, separator);

		getChildren().add(searchHBox);
		getChildren().add(vbox);

		final TextArea resultsTextArea = new TextArea();

		Button searchButton = new Button("Search");
		searchButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				List<Profile> profiles = new ArrayList<Profile>();

				for (CheckBox checkBox : cbs) {
					if (checkBox.isSelected()) {
						profiles.add(SpyOneProfile.valueOf(checkBox.getText()).getProfile());
					}
				}

				grepState.setState(new Grep4jServices().keywordSearch(searchTerm.getText(), profiles));
				resultsTextArea.setText(grepState.toString());

			}
		});

		getChildren().add(searchButton);

		getChildren().add(resultsTextArea);

	}
}
