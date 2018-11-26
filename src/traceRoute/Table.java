package traceRoute;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Table {
	
	private SimpleIntegerProperty rHop;
	private SimpleStringProperty rIP;
	private SimpleStringProperty rName;
	private SimpleDoubleProperty rTime;
	
	public Table( int sHop, String sIP, String sName, double sTime) {
		this.rHop = new SimpleIntegerProperty(sHop);
		this.rIP = new SimpleStringProperty(sIP);
		this.rName = new SimpleStringProperty(sName);
		this.rTime = new SimpleDoubleProperty(sTime);
	}

	public Integer getrHop() {
		return rHop.get();
	}

	public String getrIP() {
		return rIP.get();
	}
	
	public String getrName() {
		return rName.get();
	}

	public double getrTime() {
		return rTime.get();
	}

	public void setrHop(SimpleIntegerProperty rHop) {
		this.rHop = rHop;
	}

	public void setrIP(SimpleStringProperty rIP) {
		this.rIP = rIP;
	}

	public void setrName(SimpleStringProperty rName) {
		this.rName = rName;
	}

	public void setrTime(SimpleDoubleProperty rTime) {
		this.rTime = rTime;
	}

//	public void setrHop(Integer r) {
//		this.rHop.set(r);
//	}
//
//	public void setrIP(String rIP) {
//		this.rIP.set(rIP);;
//	}
//
//	public void setrName(String rName) {
//		this.rName.set(rName);;
//	}
//
//	public void setrTime(double rTime) {
//		this.rTime.set(rTime);;
//	}
//		
}
