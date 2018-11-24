package traceRoute;
import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import java.net.*;


public class traceRoute {
	
//	Ping
	/**
     * @param ipAddress The internet protocol address to ping
     * @return True if the address is responsive, false otherwise
     */
    public static boolean isReachable(String ipAddress) throws IOException
    {
        List<String> command = buildCommand(ipAddress);
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();

        try (BufferedReader standardOutput = new BufferedReader(new InputStreamReader(process.getInputStream())))
        {
            String outputLine;

            while ((outputLine = standardOutput.readLine()) != null)
            {
                // Picks up Windows and Unix unreachable hosts
                if (outputLine.toLowerCase().contains("destination host unreachable"))
                {
                    return false;
                }
            }
        }

        return true;
    }

    private static List<String> buildCommand(String ipAddress)
    {
        List<String> command = new ArrayList<>();
        command.add("ping");

        if (SystemUtils.IS_OS_WINDOWS)
        {
            command.add("-n");
        } else if (SystemUtils.IS_OS_UNIX)
        {
            command.add("-c");
        } else
        {
            throw new UnsupportedOperationException("Unsupported operating system");
        }

        command.add("1");
        command.add(ipAddress);
        System.out.println("command: "+command.toString());

        return command;
    }
	
	public static void main(String args[]) throws IOException{
		String ip = "157.240.24.35";
		  
		InetAddress inet = InetAddress.getByName(ip);
		System.out.println(isReachable(ip));
	    BufferedReader in;

	        try{
	            Runtime r   =   Runtime.getRuntime();
	            Process p   =   r.exec("traceroute " + ip);

	            in  =   new BufferedReader(new InputStreamReader(p.getInputStream()));

	            String line;
	            if(p == null)
	                System.out.println("could not connect");

	            while((line = in.readLine()) != null){
	                System.out.println(line);
	                System.out.println("average = " + findAverage(line));
	                //in.close();
	            }

	        }catch(IOException e){

	        System.out.println(e.toString());

	        }

	}
	
	public static double findAverage(String line)  {
		int indexOfMs, from = 0;
		double x = 0, y = 0, z = 0;
    	for(int i = 1 ; i <= 3 ; i++){
    		if(line.substring(from).contains("ms")){
            	indexOfMs = line.substring(from).indexOf("ms") + from;
            	System.out.println(line.substring(indexOfMs-7, indexOfMs-1));
            	if(i == 1 && isStar(line.substring(indexOfMs-7, indexOfMs-1))) x = 0;
            	else if(i == 1) x = Double.parseDouble( line.substring(indexOfMs-7, indexOfMs-1) );

            	else if(i == 2 && isStar(line.substring(indexOfMs-7, indexOfMs-1))) y = 0; 
            	else if(i == 2) y = Double.parseDouble( line.substring(indexOfMs-7, indexOfMs-1) ); 

            	else if(i == 3 && isStar(line.substring(indexOfMs-7, indexOfMs-1))) z = 0; 
            	else if(i == 3) z = Double.parseDouble( line.substring(indexOfMs-7, indexOfMs-1) );
            	 
            	from = indexOfMs + 1;
            }
            else System.out.println("no");
    	}
    	
    	return calAverage(x, y, z);
	}
	
	public static boolean isStar(String input){
		if(input == "*") return true;
		else return false;
	}
	
	public static double calAverage(double x, double y, double z){
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
