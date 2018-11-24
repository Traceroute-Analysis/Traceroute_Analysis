package traceRoute;

import com.chilkatsoft.CkJsonObject;
import com.chilkatsoft.CkRest;

public class ChilkatExample {

	  static {
	    try {
	        System.loadLibrary("chilkat");
	    } catch (UnsatisfiedLinkError e) {
	      System.err.println("Native code library failed to load.\n" + e);
	      System.exit(1);
	    }
	  }

	  public static void main(String argv[])
	  {
	    //  This example requires the Chilkat API to have been previously unlocked.
	    //  See Global Unlock Sample for sample code.

	    //  The IP address used in this example is 104.40.211.35
	    String ipAddress = "104.40.211.35";

	    //  First we'll try the service at freegeoip.net.
	    //  They have a limit of 10,000 queries per hour, and also
	    //  provide free source code to run your own server.

	    CkRest rest = new CkRest();

	    //  Connect to freegeoip.net
	    boolean bTls = false;
	    int port = 80;
	    boolean bAutoReconnect = true;
	    boolean success = rest.Connect("freegeoip.net",port,bTls,bAutoReconnect);
	    if (success == false) {
	        System.out.println(rest.lastErrorText());
	        return;
	        }

	    //  Query the IP address to return JSON.
	    String responseJson = rest.fullRequestNoBody("GET","/json/104.40.211.35");
	    if (rest.get_LastMethodSuccess() != true) {
	        System.out.println(rest.lastErrorText());
	        return;
	        }

	    //  Just in case we are still connected..
	    int maxWaitMs = 10;
	    rest.Disconnect(maxWaitMs);

	    CkJsonObject json = new CkJsonObject();
	    json.Load(responseJson);
	    json.put_EmitCompact(false);

	    System.out.println(json.emit());

	    //  The JSON we get back looks like this:
	    //  {
	//   "ip": "104.40.211.35",
	    //    "country_code": "US",
	    //    "country_name": "United States",
	    //    "region_code": "WA",
	    //    "region_name": "Washington",
	    //    "city": "Redmond",
	    //    "zip_code": "98052",
	    //    "time_zone": "America/Los_Angeles",
	    //    "latitude": 47.6801,
	    //    "longitude": -122.1206,
	    //    "metro_code": 819
	    //  }

	    //  Examine a few bits of information:
	    System.out.println("country name = " + json.stringOf("country_name"));
	    System.out.println("country code = " + json.stringOf("country_code"));

	    //  -----------------------------------------------------
	    //  Now to use ip-api.com, which is mostly the same..

	    success = rest.Connect("ip-api.com",port,bTls,bAutoReconnect);
	    if (success == false) {
	        System.out.println(rest.lastErrorText());
	        return;
	        }

	    //  Query the IP address to return JSON.
	    responseJson = rest.fullRequestNoBody("GET","/json/104.40.211.35");
	    if (rest.get_LastMethodSuccess() != true) {
	        System.out.println(rest.lastErrorText());
	        return;
	        }

	    //  Just in case we are still connected..
	    rest.Disconnect(maxWaitMs);

	    json.Load(responseJson);
	    json.put_EmitCompact(false);

	    System.out.println(json.emit());

	    //  The JSON we get back looks like this:
	    //  This is very strange, because the two services don't agree.
	    //  {
	//   "as": "AS8075 Microsoft Corporation",
	    //    "city": "Amsterdam",
	    //    "country": "Netherlands",
	    //    "countryCode": "NL",
	    //    "isp": "Microsoft Corporation",
	    //    "lat": 52.35,
	    //    "lon": 4.9167,
	    //    "org": "Microsoft Azure",
	    //    "query": "104.40.211.35",
	    //    "region": "NH",
	    //    "regionName": "North Holland",
	    //    "status": "success",
	    //    "timezone": "Europe/Amsterdam",
	    //    "zip": "1091"
	    //  }

	    //  Examine a few bits of information:
	    System.out.println("country name = " + json.stringOf("country"));
	    System.out.println("country code = " + json.stringOf("countryCode"));
	  }
	}