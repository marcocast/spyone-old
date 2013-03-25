/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */
package com.monito;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.monito.controllers.Search;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main Application. 
 */
public class MonitoApplication extends Application {

    private Stage stage;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    	
        Application.launch(MonitoApplication.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Monito");
            initSearch();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(MonitoApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private void initSearch() {
        try {
        	/*Search search = (Search)*/ replaceSceneContent("/UI/MonitoSearch.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MonitoApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MonitoApplication.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MonitoApplication.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
}
