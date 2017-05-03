package auto_test.vip;

import java.util.Scanner;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Execution;

public class Scenario2 {
	
	public DefaultApi initClient(String url, String apiKey){
		ApiClient testAPiclient = new ApiClient();
		testAPiclient.setBasePath(url);
		testAPiclient.setApiKey(apiKey);
		return new DefaultApi(testAPiclient);
	}
	
	public static void main(String[] args) throws Exception{
		
		//apiKey is an keyboard input
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your Apikey: ");
		String apiKey = sc.nextLine();
		System.out.println("You entered: " + apiKey);
		sc.close();
		
		//Client initialization
		DefaultApi defaultApiClient2 = new Scenario2().initClient("http://vip.creatis.insa-lyon.fr/rest", apiKey);
		
		//execution history
		System.out.println(defaultApiClient2.listExecutions());
		
		//check a particular execution
		Execution result1 = defaultApiClient2.getExecution("workflow-ulum4P");
		System.out.println(result1);
		System.out.println("***************************************************");
		
		System.out.println("old name: "+result1.getName()+",	old timeout: "+result1.getTimeout());
		
		
		System.out.println("***************************************************");
		
		//modification of name parameter of the execution
		Execution body = new Execution();
		body.setName("newTest5");
		body.setTimeout(0L);
		body.setPipelineIdentifier("AdditionTest/0.9");
		defaultApiClient2.updateExecution("workflow-ulum4P", body);
		
		//check execution modification
		Execution result2 = defaultApiClient2.getExecution("workflow-ulum4P");
		System.out.println(result2);
		System.out.println("***************************************************");
		
		System.out.println("new name: "+result2.getName()+",	new timeout: "+result2.getTimeout());
		
		Boolean test = !(result1.getName().equals(result2.getName())) || !(result1.getTimeout().equals(result2.getTimeout())); 
		
		System.out.println("modification existance test: "+test);
	}
}
