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

@SuppressWarnings("restriction")
public class FXMLTableViewController implements Initializable {

	@FXML private TableView<Person> tableView;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TableColumn firstnameColumn;
    @FXML private TableColumn lastnameColumn;
    @FXML private TableColumn emailColumn;
    
    @FXML
    protected void addPerson(ActionEvent event) {
        ObservableList<Person> data = tableView.getItems();
        data.add(new Person(firstNameField.getText(),
            lastNameField.getText(),
            emailField.getText()
        ));
        
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");   
    }

    public void initialize(URL arg0, ResourceBundle arg1) {
    	firstnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	firstnameColumn.setOnEditCommit(
	        new EventHandler<CellEditEvent<Person, String>>() {	   
	            public void handle(CellEditEvent<Person, String> t) {	      
	                ((Person) t.getTableView().getItems().get(
	                    t.getTablePosition().getRow())
	                    ).setFirstName(t.getNewValue());
	            }
	        }
	    );

    	lastnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	lastnameColumn.setOnEditCommit(
	        new EventHandler<CellEditEvent<Person, String>>() {
	            public void handle(CellEditEvent<Person, String> t) {
	                ((Person) t.getTableView().getItems().get(
	                    t.getTablePosition().getRow())
	                    ).setLastName(t.getNewValue());
	            }
	        }
	    );

    	emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	emailColumn.setOnEditCommit(
	        new EventHandler<CellEditEvent<Person, String>>() {
	            public void handle(CellEditEvent<Person, String> t) {
	                ((Person) t.getTableView().getItems().get(
	                    t.getTablePosition().getRow())
	                    ).setEmail(t.getNewValue());
	            }
	        }
	    );
		
	}
    
    
}

	
