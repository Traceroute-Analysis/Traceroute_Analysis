package traceRoute;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

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
	@FXML
	LineChart<String, Number> lineChart;
	@FXML
	ScrollPane scrollPane;
	@FXML
	Label ipLabel;

	ObservableList hopList = FXCollections.observableArrayList();
	ObservableList IPList = FXCollections.observableArrayList();
	ObservableList nameList = FXCollections.observableArrayList();
	ObservableList timeList = FXCollections.observableArrayList();
	ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
	XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
	ArrayList<String> listOfIP = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
		iHop.setMouseTransparent(true);
		iHop.setFocusTraversable(false);
		iIP.setMouseTransparent(true);
		iIP.setFocusTraversable(false);
		iName.setMouseTransparent(true);
		iName.setFocusTraversable(false);
		iTime.setMouseTransparent(true);
		iTime.setFocusTraversable(false);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		
	}

	private void loadData() {
		iHop.getItems().add("Hop");
		iIP.getItems().add("IP address");
		iName.getItems().add("Name");
		iTime.getItems().add("Avg.");
	}

	public void handleStartButton(ActionEvent event) throws IOException {
		String input = textField.getText().trim();
		System.out.println("start: " + input);
		TraceRoute traceRoute = new TraceRoute();
		if (input != null) {
			updateData(traceRoute.run(input));
			lineChart.getData().add(series);
			series.setName(null);
			
			for ( XYChart.Data<String, Number> data : series.getData()) {
				if(data.getXValue().equals("")) {
					data.getNode().setVisible(false);
				}
				data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if(!data.getXValue().equals("")) {
							int index = Integer.parseInt(data.getXValue()) - 1;
//							System.out.println("|"+data.getXValue()+"|: "+listOfIP.get(index));
							if( data.getXValue().equals(" ")) {
//								System.out.println("โหลลล");
//							 ipLabel.setText(" ");
								ipLabel.setText("IP: " + listOfIP.get(index));
							}else {
//								System.out.println("เข้า");
								ipLabel.setText("IP: " + listOfIP.get(index));
								Tooltip.install(data.getNode(),
										new Tooltip("IP:" + listOfIP.get(index)));
							}
						}else {
							System.out.println("------------------------------");
							ipLabel.setText("not define");
						}
					}
					
				});
			}
		}
	}

	public void updateData(ArrayList<ArrayList<String>> bigList) {
		int i = 0, j = 0;
		for (ArrayList<String> smallList : bigList) {
			listOfIP.add(smallList.get(2));
			i++;
//			System.out.println(i+" Hop: " + smallList.get(0));
			iHop.getItems().add(smallList.get(0));
			iIP.getItems().add(smallList.get(2));
			iName.getItems().add(smallList.get(1));
			iTime.getItems().add(smallList.get(3));
			if (smallList.get(3).contains("out")) {
				series.getData().add(new XYChart.Data<String, Number>(smallList.get(0), 0));
			} else {
				double time = Double.parseDouble(smallList.get(3));
				if(smallList.get(0).equals("")) {
					if( j == (i - 2)) {
						series.getData().add(new XYChart.Data<String, Number>((i-1)+"", time));
					}else {
						series.getData().add(new XYChart.Data<String, Number>((i-1)+"", time));
					}
					j = i - 1;
				}else {
					series.getData().add(new XYChart.Data<String, Number>(smallList.get(0), time));
					i = Integer.parseInt(smallList.get(0));
				}
			}
//			System.out.println(">>>"+series.getData().toString());
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
		series.getData().removeAll();
		lineChart.getData().clear();
		// IPList.removeAll(IPList);
		System.out.println("clear");
	}

}
