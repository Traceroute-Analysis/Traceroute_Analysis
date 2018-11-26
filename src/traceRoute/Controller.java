package traceRoute;

import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable {

	@FXML
	ListView<String> iHop;
	@FXML
	ListView<String> iIP;
	@FXML
	ListView<String> iName;
	@FXML
	ListView<String> iTime;
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
	ObservableList hopList = FXCollections.observableArrayList();
	ObservableList IPList = FXCollections.observableArrayList();
	ObservableList nameList = FXCollections.observableArrayList();
	ObservableList timeList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
		hopList.removeAll(hopList);
		IPList.removeAll(IPList);
		nameList.removeAll(nameList);
		timeList.removeAll(timeList);
	}
	
	private void loadData() {
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		hopList.addAll(a,b,c,d);
		iHop.getItems().add("Hop");
		iIP.getItems().add("IP address");
		iName.getItems().add("Name");
		iTime.getItems().add("Avg.");
		iHop.getItems().addAll(hopList);
		iIP.getItems().addAll(hopList);
		iName.getItems().addAll(hopList);
		iTime.getItems().addAll(hopList);
		
	}
	
	
	
//	public void addItemToList() {
//		data.add(new Table(hop,ip,name,time));
//	}
//	
//	public void handleInputField() {
//		if( textField != null ) {
//			
//		}
//	}
//	

	public void handleStartButton(ActionEvent event) {
		String input = textField.getText().trim();
		System.out.println("start: "+input);
		if( textField != null ) {
			
		}
	}

	public void handleClearButton(ActionEvent event) {
		textField.clear();
		System.out.println("clear");
	}

}
