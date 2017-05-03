package auto_test.vip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Execution;

public class Scenario1 {
	
	public Properties propertiesExtraction() throws Exception{
		Properties prop = new Properties();
		try{
			FileInputStream in = new FileInputStream("src/main/resources/testVip.properties");
			try{
				prop.load(in);
				in.close();
			}catch(IOException ioe){
				System.out.println(ioe.getMessage());
			}
		}catch(FileNotFoundException fnfe){
			System.out.println(fnfe.getMessage());
		}
		return prop;
	}
	
	public Execution initExecution(String name, String pipelineIdentifier, int n1, int n2){
		Execution testExe = new Execution();
		testExe.setName(name);
		testExe.setPipelineIdentifier(pipelineIdentifier);
		Map<String,Object> testMap = new HashMap<String, Object>();
		testMap.put("number1", n1);
		testMap.put("number2", n2);
		testMap.put("results-directory", "/vip/Home");
		testExe.setInputValues(testMap);
		return testExe;
	}
	
	public DefaultApi initClient(String url, String apiKey){
		ApiClient testAPiclient = new ApiClient();
		testAPiclient.setBasePath(url);
		testAPiclient.setApiKey(apiKey);
		return new DefaultApi(testAPiclient);
	}
	
	public static void main(String[] args) throws Exception{
		
		Scenario1 scenario1 = new Scenario1();
		
		//properties extraction
		Properties prop = scenario1.propertiesExtraction();
		
		//Client initialization
		DefaultApi defaultApiClient1 = scenario1.initClient(prop.getProperty("url"), prop.getProperty("apiKey"));
		
		//pipelines list
		System.out.println("test URL  /pipelines, method GET, action: print the pipelines list");
		System.out.println(defaultApiClient1.listPipelines(""));
		System.out.println("****************************************");
		
		//check parameters for a specified pipeline
		System.out.println("test URL  /pipelines/{pipelineIdentifier}, method GET, action: Show the definition of a given pipeline  ==> THINK ABOUT IT");
		System.out.println(defaultApiClient1.getPipeline("AdditionTest%2F0.9"));
		System.out.println("****************************************");
		
		//create and start an execution
		System.out.println("test URL /executions/create-and-start, method POST, action: initialize an execution and start it ==> WORK");
		Execution result = defaultApiClient1.initAndStartExecution(scenario1.initExecution("test_335", "AdditionTest/0.9", 40, 41));
		System.out.println("result: "+result);
		System.out.println("****************************************");
				
		//check the execution
		System.out.println("test URL /executions/{execution identifier}, method GET, action: print information about the specified execution");
		System.out.println("result: "+defaultApiClient1.getExecution(result.getIdentifier()));
		System.out.println("****************************************");
		
		while(result.getStatus().toString().equals("running")){
			Thread.sleep(60000);
		
			//check the execution
			System.out.println("test URL /executions/{execution identifier}, method GET, action: print information about the specified execution");
			result = defaultApiClient1.getExecution(result.getIdentifier());
			System.out.println("result: "+result);
			System.out.println("****************************************");
		}
		
		Boolean test = result.getStatus().toString().equals("finished");
		
		System.out.println("final result: "+test);
	}
}
