package auto_test.vip;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Execution;

public class Scenario3 {
	
	public DefaultApi initClient(String url, String apiKey){
		ApiClient testAPiclient = new ApiClient();
		testAPiclient.setBasePath(url);
		testAPiclient.setApiKey(apiKey);
		return new DefaultApi(testAPiclient);
	}
	
	public static void main(String args[])throws Exception{
		
		//apiKey is an keyboard input
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your Apikey: ");
		String apiKey = sc.nextLine();
		System.out.println("You entered: " + apiKey);
		sc.close();
		
		//Client initialization
		DefaultApi defaultApiClient3 = new Scenario3().initClient("http://vip.creatis.insa-lyon.fr/rest", apiKey);		
		System.out.println("***************************************************");
		
		//execution history
		System.out.println(defaultApiClient3.listExecutions());
		System.out.println("***************************************************");
		
		//kill a crashed execution
		defaultApiClient3.killExecution("workflow-y1pUxG");
		System.out.println("***************************************************");
		
		//check the execution
		System.out.println("test URL /executions/{execution identifier}, method GET, action: print information about the specified execution");
		Execution result = defaultApiClient3.getExecution("workflow-IfcK9B");
		System.out.println("result: "+result);
		System.out.println("****************************************");
		
		Boolean test1 = result.getStatus().toString().equals("killed");
		
		//create and restart the execution
		Execution body = new Execution();
		body.setName("scenario3");
		body.setPipelineIdentifier("AdditionTest/0.9");
		Map<String, Object> sc3 = new HashMap<String, Object>();
		sc3.put("number1", 1);
		sc3.put("number2", 2);
		body.setInputValues(sc3);
		result = defaultApiClient3.initAndStartExecution(body);
		System.out.println("result: "+result);
		System.out.println("***************************************************");

		Boolean test2 = result.getStatus().toString().equals("running");
		
		Boolean test = test1 && test2;
		
		System.out.println("final result: "+test);
	}
}
