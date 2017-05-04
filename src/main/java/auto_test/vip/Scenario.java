package auto_test.vip;

import java.util.Properties;

import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Execution;

public class Scenario {
	
	public boolean scenario1() throws Exception{
		App scenario1 = new App();		
		//properties extraction
		Properties prop = scenario1.propertiesExtraction();
		
		//Client initialization
		DefaultApi defaultApiClient1 = scenario1.initClient(prop.getProperty("viptest.additiontest.url"), prop.getProperty("viptest.additiontest.apikey"));
		
		//pipelines list
		System.out.println(defaultApiClient1.listPipelines(""));
		
		//check parameters for a specified pipeline
		System.out.println(defaultApiClient1.getPipeline(prop.getProperty("viptest.additiontest.pipelineidentifier")));
		
		//create and start an execution
		Execution result = defaultApiClient1.initAndStartExecution(scenario1.initExecution("test_335", "AdditionTest/0.9", 40, 41));
		System.out.println("result: "+result);
				
		//check the execution
		System.out.println("result: "+defaultApiClient1.getExecution(result.getIdentifier()));
		
		while(result.getStatus().toString().equals("running")){
			Thread.sleep(Long.valueOf(prop.getProperty("viptest.additiontest.timecheck")));
		
			//check the execution
			result = defaultApiClient1.getExecution(result.getIdentifier());
			System.out.println("result: "+result);
		}
		
		return result.getStatus().toString().equals("finished");
	}

	public boolean scenario2() throws Exception{
		App scenario2 = new App();		
		// properties extraction
		Properties prop = scenario2.propertiesExtraction();
		
		//Client initialization
		DefaultApi defaultApiClient2 = scenario2.initClient(prop.getProperty("viptest.additiontest.url"), prop.getProperty("viptest.additiontest.apikey"));
		
		//execution history
		System.out.println(defaultApiClient2.listExecutions());
		
		//check a particular execution
		Execution result1 = defaultApiClient2.getExecution("workflow-ulum4P");
		System.out.println(result1);
		
		//modification of name parameter of the execution
		Execution body = scenario2.modifExecution("newName6", 0L, "AdditionTest/0.9");
		defaultApiClient2.updateExecution("workflow-ulum4P", body);
		
		//check execution modification
		Execution result2 = defaultApiClient2.getExecution("workflow-ulum4P");
		System.out.println(result2);

		return !(result1.getName().equals(result2.getName())) || !(result1.getTimeout().equals(result2.getTimeout()));
	}
	
	public boolean scenario3() throws Exception{
		App scenario3 = new App();	
		// properties extraction
		Properties prop = scenario3.propertiesExtraction();
		
		//Client initialization
		DefaultApi defaultApiClient3 = scenario3.initClient(prop.getProperty("viptest.additiontest.url"), prop.getProperty("viptest.additiontest.apikey"));		
		
		//execution history
		System.out.println(defaultApiClient3.listExecutions());
		
		//kill a crashed execution
		defaultApiClient3.killExecution("workflow-y1pUxG");
		
		//check the execution
		Execution result = defaultApiClient3.getExecution("workflow-IfcK9B");
		System.out.println("result: "+result);
		
		Boolean test1 = result.getStatus().toString().equals("killed");
		
		//create and restart the execution
		Execution body = scenario3.initExecution("newScenario3", "AdditionTest/0.9", 1, 2);
		result = defaultApiClient3.initAndStartExecution(body);
		System.out.println("result: "+result);

		boolean test2 = result.getStatus().toString().equals("running");
		
		return test1 && test2;
	}
	
	public boolean scenario4() throws Exception{
		return false;
	}
}
