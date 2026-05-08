package Utility_Package;

import java.net.HttpURLConnection;
import java.net.URL;

public class ServerConfig {

	private static final String[] SERVERS = {
		"http://10.10.230.16:8181/lcs-finairoLending-1.0.1",
		"http://10.10.230.14:8181/lcs-finairoLending-1.0.1"
	};

	private static final int TIMEOUT = 5000; // 5 seconds
	private static String activeServer = null;

	public static String getActiveServer() {
		if (activeServer != null) return activeServer;

		System.out.println("=================================================");
		System.out.println("SERVER HEALTH CHECK - Finding Active Server...");
		System.out.println("=================================================");

		for (String server : SERVERS) {
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(server).openConnection();
				conn.setConnectTimeout(TIMEOUT);
				conn.setReadTimeout(TIMEOUT);
				conn.setRequestMethod("GET");
				int responseCode = conn.getResponseCode();
				conn.disconnect();

				if (responseCode == 200) {
					activeServer = server;
					System.out.println("  ✅ Server UP   : " + server + " (Response: " + responseCode + ")");
					System.out.println("  >> Using Server: " + server);
					System.out.println("=================================================");
					return activeServer;
				} else {
					System.out.println("  ❌ Server DOWN : " + server + " (Response: " + responseCode + ")");
				}
			} catch (Exception e) {
				System.out.println("  ❌ Server DOWN : " + server + " (Error: " + e.getMessage() + ")");
			}
		}

		// Default fallback
		activeServer = SERVERS[0];
		System.out.println("  ⚠️ No server responded - Using default: " + activeServer);
		System.out.println("=================================================");
		return activeServer;
	}
}
