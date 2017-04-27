package auto_test.vip;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Execution;

public class Scenario3 {
	
	public static void main(String args[])throws Exception{
		
		//apiKey is an keyboard input
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your Apikey: ");
		String apiKey = sc.nextLine();
		System.out.println("You entered: " + apiKey);
		
		//Client initialization
		ApiClient testAPiclient = new ApiClient();
		testAPiclient.setBasePath("http://vip.creatis.insa-lyon.fr/rest");
		testAPiclient.setApiKey(apiKey);
		DefaultApi defaultApiClient3 = new DefaultApi(testAPiclient);
		
		System.out.println("***************************************************");
		
		//execution history
		System.out.println(defaultApiClient3.listExecutions());
		System.out.println("***************************************************");
		
		//kill a crashed execution
		defaultApiClient3.killExecution("workflow-IfcK9B");
		System.out.println("***************************************************");
		
		//create and restart the execution
		Execution body = new Execution();
		body.setName("scenario3");
		body.setPipelineIdentifier("AdditionTest/0.9");
		Map<String, Object> sc3 = new HashMap<String, Object>();
		sc3.put("number1", 1);
		sc3.put("number2", 2);
		body.setInputValues(sc3);
		System.out.println(defaultApiClient3.initAndStartExecution(body));
		System.out.println("***************************************************");
	}
}
