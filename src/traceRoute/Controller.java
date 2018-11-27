package traceRoute;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
	ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
		hopList.removeAll(hopList);
		IPList.removeAll(IPList);
		nameList.removeAll(nameList);
		timeList.removeAll(timeList);
	}

	private void loadData() {
		iHop.getItems().add("HOP");
		iIP.getItems().add("IP ADDRESS");
		iName.getItems().add("NAME");
		iTime.getItems().add("AVERAGE RTT (ms)");
		// iHop.getItems().addAll(hopList);
		// iIP.getItems().addAll(hopList);
		// iName.getItems().addAll(hopList);
		// iTime.getItems().addAll(hopList);
	}

	public void handleStartButton(ActionEvent event) throws IOException {
		String input = textField.getText().trim();
		System.out.println("start: " + input);
		TraceRoute traceRoute = new TraceRoute();
		if (input != null) {
			updateData(traceRoute.run(input));
		}
	}

	public void updateData(ArrayList<ArrayList<String>> bigList) {
		for (ArrayList<String> smallList : bigList) {
			iHop.getItems().add(smallList.get(0));
			iIP.getItems().add(smallList.get(2));
			iName.getItems().add(smallList.get(1));
			iTime.getItems().add(smallList.get(3));
		}
	}

	public void handleClearButton(ActionEvent event) {
		textField.clear();
		iHop.getItems().clear();
		iIP.getItems().clear();
		iName.getItems().clear();
		iTime.getItems().clear();
		iHop.getItems().add("HOP");
		iIP.getItems().add("IP ADDRESS");
		iName.getItems().add("NAME");
		iTime.getItems().add("AVERAGE RTT (ms)");
		hopList.clear();
//		IPList.removeAll(IPList);
		System.out.println("clear");
	}

}
