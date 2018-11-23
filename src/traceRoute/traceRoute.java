package traceRoute;
import org.apache.commons.lang3.SystemUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import java.io.*;
import java.net.*;
import java.lang.*;


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
//    
//    public static void main(String[] arg) throws IOException {
//    	String ip = "127.0.0.0";
//    	Timer t = new Timer();
//    	InetAddress inet = InetAddress.getByName(ip);
//       	System.out.println(isReachable(ip));
//    	System.out.println(inet.isLoopbackAddress()+" "+inet.isAnyLocalAddress());
//    }
	
	
	public static void main(String args[]) throws IOException{
		String ip = "157.240.24.35";
		  
		InetAddress inet = InetAddress.getByName(ip);
		System.out.println(isReachable(ip));
	    BufferedReader in;

	        try{
	            Runtime r   =   Runtime.getRuntime();
	            Process p   =   r.exec("traceroute "+ip);

	            in  =   new BufferedReader(new InputStreamReader(p.getInputStream()));

	            String line;

	            if(p==null)
	                System.out.println("could not connect");

	            while((line=in.readLine())!=null){

	                System.out.println(line);

	                //in.close();
	            }

	        }catch(IOException e){

	        System.out.println(e.toString());

	        }

	}
}
