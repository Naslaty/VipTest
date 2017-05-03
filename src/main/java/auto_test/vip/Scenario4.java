package auto_test.vip;

import java.util.Scanner;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Execution;

public class Scenario4 {
	
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
		DefaultApi defaultApiClient4 = new Scenario4().initClient("http://vip.creatis.insa-lyon.fr/rest", apiKey);				
		System.out.println("***************************************************");
		
		//execution history
		System.out.println(defaultApiClient4.listExecutions());
		System.out.println("***************************************************");
		
		//check a particular execution
		Execution result = defaultApiClient4.getExecution("workflow-ulum4P");
		System.out.println(result);
		System.out.println("***************************************************");
		
		if(result.getStatus().toString().equals("finished")){
			//to get result file name
			System.out.println(defaultApiClient4.getExecutionResults("workflow-PvlSwI", ""));
			System.out.println("***************************************************");
			
			//to get stdout
			System.out.println(defaultApiClient4.getStdout("workflow-PvlSwI"));
			System.out.println("***************************************************");
			
			//to get stderr
			System.out.println(defaultApiClient4.getStderr("workflow-PvlSwI"));
			System.out.println("***************************************************");
		}
		
		else{
			System.out.println("the execution is not finished");
		}
	}
}
