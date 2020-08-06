package DeleteIDs.FromDiscovery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SystemConfig {
	
	private static final String source;
	
	private SystemConfig() {
		try {
			pr.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	private static Properties pr = new Properties();
	static{
		
		try {
			pr.load(new FileInputStream("config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null != pr.getProperty("source")) {
			source = pr.getProperty("source");
		}else {
			source = "Default-Dev";
		}
		
		System.out.println("source found - "+source);
	}
	
	public static String getPropertyByName(String name) {
		return pr.getProperty(name);
	}

}
