package auto_test.vip;

import java.util.Scanner;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Execution;

public class Scenario2 {
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
		DefaultApi defaultApiClient2 = new DefaultApi(testAPiclient);
		
		//execution history
		System.out.println(defaultApiClient2.listExecutions());
		
		//check a particular execution
		String result = defaultApiClient2.getExecution("workflow-ulum4P").toString();
		System.out.println(result);
		System.out.println("***************************************************");
		
		System.out.println("*************TEST SPLIT 1*****************");
		String[] testSplit = result.split(" ");
		for(int i=0; i<testSplit.length; i++){
			System.out.print("tab["+i+"]: "+testSplit[i]);
		}
		
		System.out.println("old name: "+testSplit[12]+",	old timeout: "+testSplit[22]);
		
		
		System.out.println("***************************************************");
		
		//modification of name parameter of the execution
		Execution body = new Execution();
		body.setName("newTest5");
		body.setTimeout(0L);
		body.setPipelineIdentifier("AdditionTest/0.9");
		defaultApiClient2.updateExecution("workflow-ulum4P", body);
		
		//check execution modification
		result = defaultApiClient2.getExecution("workflow-ulum4P").toString();
		System.out.println(result);
		System.out.println("***************************************************");
		
		System.out.println("*************TEST SPLIT 1*****************");
		String[] newTestSplit = result.split(" ");
		for(int i=0; i<newTestSplit.length; i++){
			System.out.print("tab["+i+"]: "+newTestSplit[i]);
		}
		
		System.out.println("new name: "+newTestSplit[12]+",	new timeout: "+newTestSplit[22]);
		
		Boolean test1 = !(testSplit[12].equals(newTestSplit[12])) || !(testSplit[22].equals(newTestSplit[22])); 
		//Boolean test2 = (testSplit[22].equals(newTestSplit[22]));
		
		System.out.println("modification existance test: "+test1);
	}
}
