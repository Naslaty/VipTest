package auto_test.vip;

import java.util.Scanner;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;

public class Scenario4 {
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
		DefaultApi defaultApiClient4 = new DefaultApi(testAPiclient);
				
		System.out.println("***************************************************");
		
		//execution history
		System.out.println(defaultApiClient4.listExecutions());
		System.out.println("***************************************************");
		
		//check a particular execution
		String result = defaultApiClient4.getExecution("workflow-ulum4P").toString();
		System.out.println(result);
		System.out.println("***************************************************");
		
		System.out.println("*************TEST SPLIT 1*****************");
		String[] testSplit = result.split(" ");
		for(int i=0; i<testSplit.length; i++){
			System.out.print("tab["+i+"]: "+testSplit[i]);
		}
		
		System.out.print("tab[27]:"+testSplit[27]);
		
		if(testSplit[27].equals("finished\n")){
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
			System.out.println("the execution is running");
		}
	}
}
