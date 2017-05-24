package auto_test.vip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scenario6 {

	private static Logger logger = LoggerFactory.getLogger(Scenario5.class);

	public static void main(String[] args) throws Exception {

		String apikey = System.getProperty("apikey");
		logger.debug("Launch scenario 6");
		Scenario scenarioTest6 = new Scenario();
		boolean result = scenarioTest6.scenario6(apikey);
		logger.debug("Scenario 6 result: {}",result);
	}

}
