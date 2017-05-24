package auto_test.vip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scenario1 {
	
	private static Logger logger = LoggerFactory.getLogger(Scenario1.class);
	
	public static void main(String[] args) throws Exception{
		
		String apikey = System.getProperty("apikey");
		logger.debug("Launch scenario 1");
		Scenario scenarioTest1 = new Scenario();
		boolean result = scenarioTest1.scenario1(apikey);
		logger.debug("Scenario 1 result: {}",result);
	}
}
