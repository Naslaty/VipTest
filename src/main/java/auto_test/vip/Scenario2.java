package auto_test.vip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scenario2 {
	
	private static Logger logger = LoggerFactory.getLogger(Scenario2.class);
	
	public static void main(String[] args) throws Exception{
		
		String apikey = System.getProperty("apikey");
		logger.debug("Launch scenario 2");
		logger.info("waited result: true");
		Scenario scenarioTest2 = new Scenario();
		logger.info("Scenario 2 result: {}",scenarioTest2.scenario2(apikey));
	}
}
