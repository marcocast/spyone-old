package com.marco.spyone.ui;

import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.grep4j.core.result.GrepResult;
import org.grep4j.core.result.GrepResults;

import com.marco.spyone.services.GrepState;
import com.marco.spyone.services.SpyOneProfile;

public class BarChartPanel extends VBox {

	private LineChart<String, Number> lineChart;

	private final GrepState grepState;

	public BarChartPanel(GrepState grepState) {
		this.grepState = grepState;

		setPadding(new Insets(15, 12, 15, 12));
		setSpacing(10); // Gap between nodes

		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10); // Gap between nodes

		getChildren().add(hbox);

		createLineGraphPanel();
		getChildren().add(lineChart);
	}

	private void createLineGraphPanel() {

		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("FileName");
		yAxis.setLabel("Exceptions Found");
		lineChart = new LineChart<String, Number>(xAxis, yAxis);
		lineChart.setTitle("Keyword Monitoring");
	}

	public void refreshFromGrepState() {

		lineChart.getData().clear();

		for (SpyOneProfile spyOneProfile : SpyOneProfile.values()) {

			GrepResults grepResultForProfile = grepState.getState().filterOnProfile(spyOneProfile.getProfile());
			if (grepResultForProfile.totalLines() > 0) {

				XYChart.Series series = new XYChart.Series();
				series.setName(spyOneProfile.getProfile().getName());

				GrepResult[] sortedResults = reorderResults(grepResultForProfile);

				for (GrepResult result : sortedResults) {
					String fileName = result.getFileName();
					fileName = fileName.substring(fileName.lastIndexOf("/") + 1);

					series.getData().add(new XYChart.Data(fileName, result.totalLines()));
				}
				lineChart.getData().add(series);
			}
		}
	}

	private GrepResult[] reorderResults(GrepResults results) {
		GrepResult[] arrayOfResults = new GrepResult[results.size()];
		results.toArray(arrayOfResults);
		Arrays.sort(arrayOfResults, new GrepResultComparator());
		return arrayOfResults;
	}

}

class GrepResultComparator implements java.util.Comparator<GrepResult> {

	public int compare(GrepResult o1, GrepResult o2) {
		String fileName1 = o1.getFileName().substring(o1.getFileName().lastIndexOf("/") + 1);
		String fileName2 = o2.getFileName().substring(o2.getFileName().lastIndexOf("/") + 1);

		if (fileName1.equalsIgnoreCase("server.log")) {
			return 1;
		}
		if (fileName2.equalsIgnoreCase("server.log")) {
			return -1;
		}

		return fileName1.compareTo(fileName2);
	}

}
