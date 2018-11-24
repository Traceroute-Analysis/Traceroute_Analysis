package traceRoute;

import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable {
	
	@FXML
	TableView<Table> tableID;
	@FXML
	TableColumn<Table, Integer> iHop;
	@FXML
	TableColumn<Table, String> iIP;
	@FXML
	TableColumn<Table, String> iName;
	@FXML
	TableColumn<Table, Double> iTime;
	@FXML
	TextField textField;
	@FXML
	Button startButton;
	@FXML
	Button clearButton;
	
	private int hop = 3;
	private String ip = "102.11.1.1";
	private String name = "www.google.com";
	private double time = 34;
	
	ObservableList<Table> data;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		iHop.setCellValueFactory(new PropertyValueFactory<Table, Integer>("rhop"));
		System.out.println("เข้า");
		iIP.setCellValueFactory(new PropertyValueFactory<Table, String>("riP"));
		iName.setCellValueFactory(new PropertyValueFactory<Table, String>("rName"));
		iTime.setCellValueFactory(new PropertyValueFactory<Table, Double>("rTime"));
		
		tableID.setItems(getListItem());
		
	}
	
	public ObservableList<Table> getListItem(){
		data = FXCollections.observableArrayList();
		data.add(new Table(4,"ip","name",3));
		data.add(new Table(hop,ip,name,time));
		data.add(new Table(hop,ip,name,time));
		data.add(new Table(hop,ip,name,time));
		System.out.println(hop+" "+ip+" "+name+" "+time);
		for(Table a: data) {
			System.out.println(">"+a.getrIP());
		}
		return data;
	}
	
	public void addItemToList() {
		data.add(new Table(hop,ip,name,time));
	}
	
	public void handleInputField() {
		if( textField != null ) {
			
		}
	}
	
	public void handleStartButton(ActionEvent event) {
		String input = textField.getText().trim();
		System.out.println("start: "+input);
		if( textField != null ) {
			tableID.setItems(getListItem());
		}
	}
	
	public void handleClearButton(ActionEvent event) {
		textField.clear();
		System.out.println("clear");
	}

}
