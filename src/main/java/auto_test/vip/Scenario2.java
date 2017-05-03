package auto_test.vip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Execution;

public class Scenario2 {
	
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
	
	public DefaultApi initClient(String url, String apiKey){
		ApiClient testAPiclient = new ApiClient();
		testAPiclient.setBasePath(url);
		testAPiclient.setApiKey(apiKey);
		return new DefaultApi(testAPiclient);
	}
	
	public static void main(String[] args) throws Exception{
		
		Scenario2 scenario2 = new Scenario2();
				
		// properties extraction
		Properties prop = scenario2.propertiesExtraction();
		
		//Client initialization
		DefaultApi defaultApiClient2 = scenario2.initClient(prop.getProperty("url"), prop.getProperty("apiKey"));
		
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
