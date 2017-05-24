package auto_test.vip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scenario4 {

	private static Logger logger = LoggerFactory.getLogger(Scenario4.class);

	public static void main(String[] args) throws Exception{
		
		String apikey = System.getProperty("apikey");
		logger.debug("Launch scenario 4");
		Scenario scenarioTest4 = new Scenario();
		boolean result = scenarioTest4.scenario4(apikey);
		logger.debug("Scenario 4 result: {}",result);
	}
}
