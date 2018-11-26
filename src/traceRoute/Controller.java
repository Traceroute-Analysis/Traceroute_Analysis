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
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.chart.*;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//import javafx.util.Callback;
// 
///**
//*
//* @web http://java-buddy.blogspot.com/
//*/
//public class Controller extends Application {
//   
//   private TableView<XYChart.Data> tableView = new TableView<>();
//   
//   private ObservableList<XYChart.Data> dataList =
//           FXCollections.observableArrayList(
//               new XYChart.Data("January", 100),
//               new XYChart.Data("February", 200),
//               new XYChart.Data("March", 50),
//               new XYChart.Data("April", 75),
//               new XYChart.Data("May", 110),
//               new XYChart.Data("June", 300),
//               new XYChart.Data("July", 111),
//               new XYChart.Data("August", 30),
//               new XYChart.Data("September", 75),
//               new XYChart.Data("October", 55),
//               new XYChart.Data("November", 225),
//               new XYChart.Data("December", 99));
//       
//   /**
//    * @param args the command line arguments
//    */
//   public static void main(String[] args) {
//       launch(args);
//   }
//   
//   @Override
//   public void start(Stage primaryStage) {
//       primaryStage.setTitle("java-buddy.blogspot.com");
//       
//       Group root = new Group();
//       
//       tableView.setEditable(true);
//       Callback<TableColumn, TableCell> cellFactory =
//               new Callback<TableColumn, TableCell>() {
//           @Override
//                   public TableCell call(TableColumn p) {
//                       return new EditingCell();
//                   }
//               };
// 
//       TableColumn columnMonth = new TableColumn("Month");
//       columnMonth.setCellValueFactory(
//               new PropertyValueFactory<XYChart.Data,String>("XValue"));
// 
//       TableColumn columnValue = new TableColumn("Value");
//       columnValue.setCellValueFactory(
//               new PropertyValueFactory<XYChart.Data,Number>("YValue"));
//       
//       //--- Add for Editable Cell of Value field, in Number
//       columnValue.setCellFactory(cellFactory);
// 
//       columnValue.setOnEditCommit(
//               new EventHandler<TableColumn.CellEditEvent<XYChart.Data, Number>>() {
//                   @Override public void handle(TableColumn.CellEditEvent<XYChart.Data, Number> t) {
//                       ((XYChart.Data)t.getTableView().getItems().get(
//                               t.getTablePosition().getRow())).setYValue(t.getNewValue());
//                   }
//               });
// 
//       //---
//       
//       //--- Prepare LineChart
//       final CategoryAxis xAxis = new CategoryAxis();
//       final NumberAxis yAxis = new NumberAxis();
//       
//       xAxis.setLabel("Month");
//       yAxis.setLabel("Value");
//       
//       final LineChart<String, Number> lineChart = new LineChart<>(xAxis,yAxis);
// 
//       lineChart.setTitle("LineChart");
//       XYChart.Series series = new XYChart.Series(dataList);
//       series.setName("XYChart.Series");
//       lineChart.getData().add(series);
// 
//       
//       //--- Prepare TableView
//       
//       tableView.setItems(dataList);
//       tableView.getColumns().addAll(columnMonth, columnValue);
//       
//       //---
//       
//       HBox hBox = new HBox();
//       hBox.setSpacing(10);
//       hBox.getChildren().addAll(tableView, lineChart);
// 
//       root.getChildren().add(hBox);
//       
//       primaryStage.setScene(new Scene(root, 670, 400));
//       primaryStage.show();
//   }
//   
//   class EditingCell extends TableCell<XYChart.Data, Number> {
// 
//       private TextField textField;
//       
//       public EditingCell() {}
//       
//       @Override
//       public void startEdit() {
//           super.startEdit();
//           
//           if (textField == null) {
//               createTextField();
//           }
//           
//           setGraphic(textField);
//           setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//           textField.selectAll();
//       }
//       
//       @Override
//       public void cancelEdit() {
//           super.cancelEdit();
//           
//           setText(String.valueOf(getItem()));
//           setContentDisplay(ContentDisplay.TEXT_ONLY);
//       }
// 
//       @Override
//       public void updateItem(Number item, boolean empty) {
//           super.updateItem(item, empty);
//           
//           if (empty) {
//               setText(null);
//               setGraphic(null);
//           } else {
//               if (isEditing()) {
//                   if (textField != null) {
//                       textField.setText(getString());
//                   }
//                   setGraphic(textField);
//                   setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//               } else {
//                   setText(getString());
//                   setContentDisplay(ContentDisplay.TEXT_ONLY);
//               }
//           }
//       }
// 
//       private void createTextField() {
//           textField = new TextField(getString());
//           textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
//           textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
//               
//               @Override
//               public void handle(KeyEvent t) {
//                   if (t.getCode() == KeyCode.ENTER) {
//                       commitEdit(Double.parseDouble(textField.getText()));
//                   } else if (t.getCode() == KeyCode.ESCAPE) {
//                       cancelEdit();
//                   }
//               }
//           });
//       }
//       
//       private String getString() {
//           return getItem() == null ? "" : getItem().toString();
//       }
//   }
//  
//}
