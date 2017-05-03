package auto_test.vip;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Execution;


public class Scenario1 {
	
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
		
		// Properties prop = new Properties("viptest.properties");
		
		//apiKey is an keyboard input
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your Apikey: ");
		String apiKey = sc.nextLine();
		System.out.println("You entered: " + apiKey);
		sc.close();
		
		//Client initialization
		DefaultApi defaultApiClient1 = new Scenario1().initClient("http://vip.creatis.insa-lyon.fr/rest", apiKey);
		
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
		Execution result = defaultApiClient1.initAndStartExecution(new Scenario1().initExecution("test_334", "AdditionTest/0.9", 40, 41));
		System.out.println("result: "+result);
		System.out.println("****************************************");
				
		//check the execution
		System.out.println("test URL /executions/{execution identifier}, method GET, action: print information about the specified execution");
		System.out.println("result: "+defaultApiClient1.getExecution(result.getIdentifier()));
		System.out.println("****************************************");
		
		Thread.sleep(6000);
		
		//check the execution
		System.out.println("test URL /executions/{execution identifier}, method GET, action: print information about the specified execution");
		result = defaultApiClient1.getExecution(result.getIdentifier());
		System.out.println("result: "+result);
		System.out.println("****************************************");
		
		Boolean test = result.getStatus().toString().equals("finished");
		
		System.out.println("final result: "+test);
	}
}
