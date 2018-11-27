package traceRoute;
import org.apache.commons.lang3.SystemUtils;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.net.*;

public class TraceRoute {
	
	//	Ping
	/**
	 * @param ipAddress The internet protocol address to ping
	 * @return True if the address is responsive, false otherwise
	 */
	public static boolean isReachable(String ipAddress) throws IOException {
		List<String> command = buildCommand(ipAddress);
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		Process process = processBuilder.start();

		try (BufferedReader standardOutput = new BufferedReader(new InputStreamReader(process.getInputStream()))){
			String outputLine;
			while ((outputLine = standardOutput.readLine()) != null)
				// Picks up Windows and Unix unreachable hosts
				if (outputLine.toLowerCase().contains("destination host unreachable")) return false;
		}
		return true;
	}

	private static List<String> buildCommand(String ipAddress){
		List<String> command = new ArrayList<>();
		command.add("ping");
		if (SystemUtils.IS_OS_WINDOWS) command.add("-n");
		else if (SystemUtils.IS_OS_UNIX) command.add("-c");
		else throw new UnsupportedOperationException("Unsupported operating system");
		command.add("1");
		command.add(ipAddress);
		System.out.println("command: "+command.toString());
		return command;
	}
	
	public ArrayList<ArrayList<String>> run(String ip) throws IOException{
//		String ip = "facebook.com";

		InetAddress inet = InetAddress.getByName(ip);
		System.out.println(isReachable(ip));
		BufferedReader in;

		ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
		try {
			Runtime r   =   Runtime.getRuntime();
			Process p   =   r.exec("traceroute " + ip);

			in  =   new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			if(p == null) System.out.println("could not connect");
			while((line = in.readLine()) != null){
				ArrayList<String> singleList = new ArrayList<String>();
				
				System.out.println(line);
				if(line.contains("* * *")) {
					System.out.println("Hop = " + findHop(line));
					singleList.add(findHop(line));
					singleList.add("- run out of time -");
					singleList.add("- run out of time -");
					singleList.add("- run out of time -");
					System.out.println("- run out of time -");
				} else {
					System.out.println("Hop = " + findHop(line));
					System.out.println("IP = " + findIP(line));
					System.out.println("Name = " + findName(line));
					System.out.println("Average = " + findAverage(line));
					singleList.add(findHop(line));
					singleList.add(findName(line));
					singleList.add(findIP(line));
					singleList.add(findAverage(line));
				}
				System.out.println("------------");
				listOLists.add(singleList);
			}
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		
		return listOLists;
	}

//	public static void main(String args[]) throws IOException {
//		String ip = "facebook.com";
//
//		InetAddress inet = InetAddress.getByName(ip);
//		System.out.println(isReachable(ip));
//		BufferedReader in;
//
//		ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
//		try {
//			Runtime r   =   Runtime.getRuntime();
//			Process p   =   r.exec("traceroute " + ip);
//
//			in  =   new BufferedReader(new InputStreamReader(p.getInputStream()));
//			String line;
//			if(p == null) System.out.println("could not connect");
//			while((line = in.readLine()) != null){
//				ArrayList<String> singleList = new ArrayList<String>();
//				System.out.println(line);
//				if(line.contains("* * *")) {
//					System.out.println("Hop = " + findHop(line));
//					singleList.add(findHop(line));
//					singleList.add("- run out of time -");
//					System.out.println("- run out of time -");
//				} else {
//					System.out.println("Hop = " + findHop(line));
//					System.out.println("IP = " + findIP(line));
//					System.out.println("Name = " + findName(line));
//					System.out.println("Average = " + findAverage(line));
//					singleList.add(findHop(line));
//					singleList.add(findName(line));
//					singleList.add(findIP(line));
//					singleList.add(findAverage(line));
//				}
//				System.out.println("------------");
//				listOLists.add(singleList);
//			}
//		} catch(IOException e) {
//			System.out.println(e.toString());
//		}
//	}

	public static String findHop(String line){
		return line.substring(0, 4).trim();
	}

	public static String findName(String line){
		String reverseLine = reverse(line);
		String name = "";
		int indexOfParenthesis, indexOfSpace = line.length();
		if(reverseLine.contains("(")){
			indexOfParenthesis = reverseLine.indexOf("(");
			name = reverseLine.substring(indexOfParenthesis + 1, indexOfSpace);
		}

		name =  reverse(name);
		int xxx = 0;
		xxx = name.substring(3).indexOf(" ");
		name = name.substring(xxx + 3);
		if(name.contains("* *")){
			name = name.substring(xxx + 5);
		}
		else if(name.contains("*")){
			name = name.substring(xxx + 3);
		}
		return name.trim();
	}

	public static String reverse(String line){
		String reverse = "";
		for(int i = line.length() - 1; i >= 0; i--) reverse = reverse + line.charAt(i);
		return reverse;
	}

	public static String findIP(String line){
		int indexOfStart, indexOfEnd;
		String sum = "";
		if(line.contains("(") && line.contains(")")){
			indexOfStart = line.indexOf("(");
			indexOfEnd = line.indexOf(")");
			sum = line.substring(indexOfStart + 1, indexOfEnd);
			return sum;
		} else return "";
	}

	public static String findAverage(String line) {
		int indexOfMs, from = 0;
		DecimalFormat deFormat = new DecimalFormat(".##");
		double x = 0, y = 0, z = 0;
		for(int i = 1 ; i <= 3 ; i++){
			if(line.substring(from).contains("ms")){
				indexOfMs = line.substring(from).indexOf("ms") + from;
//				System.out.println(line.substring(indexOfMs-7, indexOfMs-1));

				if(i == 1) x = Double.parseDouble( line.substring(indexOfMs-7, indexOfMs-1) );
				else if(i == 2) y = Double.parseDouble( line.substring(indexOfMs-7, indexOfMs-1) ); 
				else if(i == 3) z = Double.parseDouble( line.substring(indexOfMs-7, indexOfMs-1) );
				from = indexOfMs + 1;
			}
//			else System.out.println("no");
		}
		return deFormat.format(calAverage(x, y, z)) + "";
	}

	public static boolean isStar(String input) {
		if(input == "*") return true;
		else return false;
	}

	public static double calAverage(double x, double y, double z) {
		if(x != 0 && y != 0 && z != 0) return (x + y + z) / 3;
		else if(x != 0 && y != 0 && z == 0) return (x + y + z) / 2;
		else if(x != 0 && y == 0 && z != 0) return (x + y + z) / 2;
		else if(x == 0 && y != 0 && z != 0) return (x + y + z) / 2;
		else if(x != 0 && y == 0 && z == 0) return (x + y + z);
		else if(x == 0 && y != 0 && z == 0) return (x + y + z);
		else if(x == 0 && y == 0 && z != 0) return (x + y + z);
		else return 0;
	}
}