package auto_test.vip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scenario3 {
	
	private static Logger logger = LoggerFactory.getLogger(Scenario3.class);

	public static void main(String args[])throws Exception{
		
		String apikey = System.getProperty("apikey");
		logger.debug("Launch scenario 3");
		logger.info("waited result: true");
		Scenario scenarioTest3 = new Scenario();
		logger.info("Scenario 3 result: {}", scenarioTest3.scenario3(apikey));
	}
}
