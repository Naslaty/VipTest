package auto_test.vip;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.DeleteExecutionConfiguration;
import io.swagger.client.model.Execution;


public class Scenario1 {
	public static void main(String[] args) throws Exception{
		
		//apiKey is an keyboard input
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your Apikey: ");
		String apiKey = sc.nextLine();
		System.out.println("You entered: " + apiKey);
		
		//Client initialization
		ApiClient testAPiclient = new ApiClient();
		testAPiclient.setBasePath("http://vip.creatis.insa-lyon.fr/rest");
		testAPiclient.setApiKey(apiKey);	
		DefaultApi defaultApiClient1 = new DefaultApi(testAPiclient);
		
		System.out.println("test URL  /pipelines, method GET, action: print the pipelines list");
		System.out.println(defaultApiClient1.listPipelines(""));
		System.out.println("****************************************");
		
		System.out.println("test URL  /pipelines/{pipelineIdentifier}, method GET, action: Show the definition of a given pipeline  ==> THINK ABOUT IT");
		System.out.println(defaultApiClient1.getPipeline("AdditionTest%2F0.9"));
		System.out.println("****************************************");
		
		System.out.println("test URL /executions/create-and-start, method POST, action: initialize an execution and start it ==> WORK");
		Execution testExe = new Execution();
		testExe.setName("test_101350");
		testExe.setPipelineIdentifier("AdditionTest/0.9");
		Map<String,Object> testMap = new HashMap<String, Object>();
		testMap.put("number1", new Integer(51));
		testMap.put("number2", new Integer(48));
		testMap.put("results-directory", "/vip/Home");
		testExe.setInputValues(testMap);
		Execution result = defaultApiClient1.initAndStartExecution(testExe);
		System.out.println("result: "+result);
		System.out.println("****************************************");
		
		String[] executionInitStart = result.toString().split("\\{");
		String[] executionInitStart2 = executionInitStart[1].split(":"); 
		String[] executionInitStart3 = executionInitStart2[1].split("\n");
		String[] executionInitStart4 = executionInitStart3[0].split(" ");
//		for(int i=0; i<executionInitStart4.length; i++){
//			System.out.println(executionInitStart4[i]);
//		}
		
		
		System.out.println("test URL /executions/{execution identifier}, method GET, action: print information about the specified execution");
		System.out.println("result: "+defaultApiClient1.getExecution(executionInitStart4[1]));
		System.out.println("****************************************");
		
		Thread.sleep(6000);
		
		System.out.println("test URL /executions/{execution identifier}, method GET, action: print information about the specified execution");
		result = defaultApiClient1.getExecution(executionInitStart4[1]);
		System.out.println("result: "+result);
		System.out.println("****************************************");
		
		String[] chaine = result.toString().split("\n");
		String reComposed = "";
		
	
		
		for(int i=0; i<chaine.length; i++){
			reComposed+=chaine[i];
		}
		System.out.println(reComposed);
		
		executionInitStart = result.toString().split(":");
		executionInitStart2 = executionInitStart[5].split("\n");
		executionInitStart3 = executionInitStart2[0].split(" "); 
		
//		for(int i=0; i<executionInitStart3.length; i++){
//			System.out.println(executionInitStart3[i]);
//		}
		
//		System.out.println("*************************************************"+executionInitStart3[1]);
		
//		for(int i=0; i<executionInitStart2.length; i++){
//			System.out.println(executionInitStart2[i]);
//		}
		
		Boolean test = executionInitStart3[1].equals("finished");
		
		System.out.println("final result: "+test);
	}
}
