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

public class App 
{
	public Properties propertiesExtraction() throws Exception{
		Properties prop = new Properties();
		try{
			FileInputStream in = new FileInputStream("src/main/resources/testVipAdditiontest.properties");
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
	
	public Execution modifExecution(String newName, long newTimeout, String pipelineIdentifier){
		Execution body = new Execution();
		body.setName(newName);
		body.setTimeout(newTimeout);
		body.setPipelineIdentifier(pipelineIdentifier);
		return body;
	}
		public static void main(String[] args) throws Exception{
			// TODO Auto-generated method stub

		}
}
