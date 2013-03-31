package com.spyone.profile;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

import com.spyone.model.SpyOneProfile;

@SuppressWarnings("restriction")
public class ProfileController implements Initializable {

	@FXML private TableView<SpyOneProfile> tableView;
    @FXML private TextField profileNameField;
    @FXML private TextField filePathField;
    @FXML private TextField hostField;
    @FXML private TextField userField;
    @FXML private TextField passwordField;
    
    @FXML private TableColumn profileNameColumn;
    @FXML private TableColumn filePathColumn;
    @FXML private TableColumn hostColumn;
    @FXML private TableColumn userColumn;
    @FXML private TableColumn passwordColumn;
    
    
    @FXML
    protected void addSpyOneProfile(ActionEvent event) {
        ObservableList<SpyOneProfile> data = tableView.getItems();
        data.add(new SpyOneProfile(profileNameField.getText(),
        		filePathField.getText(),
        		hostField.getText(),
        		userField.getText(),
        		passwordField.getText()
        ));
        
        profileNameField.setText("");
        filePathField.setText("");
        hostField.setText("");   
        userField.setText("");
        passwordField.setText("");
        
    }

    @SuppressWarnings("unchecked")
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	profileNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());    	
    	profileNameColumn.setOnEditCommit(
	        new EventHandler<CellEditEvent<SpyOneProfile, String>>() {	   
	            public void handle(CellEditEvent<SpyOneProfile, String> t) {	      
	                ((SpyOneProfile) t.getTableView().getItems().get(
	                    t.getTablePosition().getRow())
	                    ).setProfileName(t.getNewValue());
	            }
	        }
	    );
    	
    	filePathColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	filePathColumn.setOnEditCommit(
	        new EventHandler<CellEditEvent<SpyOneProfile, String>>() {
	            public void handle(CellEditEvent<SpyOneProfile, String> t) {
	                ((SpyOneProfile) t.getTableView().getItems().get(
	                    t.getTablePosition().getRow())
	                    ).setFilePath(t.getNewValue());
	            }
	        }
	    );

    	hostColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	hostColumn.setOnEditCommit(
	        new EventHandler<CellEditEvent<SpyOneProfile, String>>() {
	            public void handle(CellEditEvent<SpyOneProfile, String> t) {
	                ((SpyOneProfile) t.getTableView().getItems().get(
	                    t.getTablePosition().getRow())
	                    ).setHost(t.getNewValue());
	            }
	        }
	    );
    	
    	userColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	userColumn.setOnEditCommit(
	        new EventHandler<CellEditEvent<SpyOneProfile, String>>() {
	            public void handle(CellEditEvent<SpyOneProfile, String> t) {
	                ((SpyOneProfile) t.getTableView().getItems().get(
	                    t.getTablePosition().getRow())
	                    ).setUser(t.getNewValue());
	            }
	        }
	    );
    	
    	passwordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	passwordColumn.setOnEditCommit(
	        new EventHandler<CellEditEvent<SpyOneProfile, String>>() {
	            public void handle(CellEditEvent<SpyOneProfile, String> t) {
	                ((SpyOneProfile) t.getTableView().getItems().get(
	                    t.getTablePosition().getRow())
	                    ).setPassword(t.getNewValue());
	            }
	        }
	    );
		
	}
    
    
}

	
