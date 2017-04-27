package auto_test.vip;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.DeleteExecutionConfiguration;
import io.swagger.client.model.Execution;

/**
 * Hello world!
 *
 */
public class App 
{
		public static void main(String[] args) throws Exception{
			// TODO Auto-generated method stub
			ApiClient testAPiclient = new ApiClient();
			testAPiclient.setBasePath("http://vip.creatis.insa-lyon.fr/rest");
			testAPiclient.setApiKey("8l01stauurr4dv72ahnfpncj2o");
//			Configuration.setDefaultApiClient(testAPiclient);
			
			DefaultApi newDapi = new DefaultApi(testAPiclient);
			
			System.out.println("test URL /platform, method GET, action: print platform properties");
			System.out.println("result: "+newDapi.getPlatformProperties());
			System.out.println("****************************************");

			System.out.println("test URL /executions, method GET, action: print list of executions");
			System.out.println("result: "+newDapi.listExecutions());
			System.out.println("****************************************");
			
			System.out.println("test URL /executions, method POST, action: initialize an execution ==> NOT IMPLEMENTED");
//			Execution testExe = new Execution();
//			testExe.setName("test_57");
//			testExe.setPipelineIdentifier("AdditionTest/0.9");
//			Map<String,Object> testMap = new HashMap<String, Object>();
//			testMap.put("number1", new Integer(5));
//			testMap.put("number2", new Integer(56));
//			testMap.put("results-directory", "/vip/Home");
//			testExe.setInputValues(testMap);
//			System.out.println(testExe);
//			System.out.println("result: "+newDapi.initExecution(testExe));
			System.out.println("****************************************");
			
			System.out.println("test URL /executions/create-and-start, method POST, action: initialize an execution and start it ==> WORK");
//			Execution testExe = new Execution();
//			testExe.setName("test_101");
//			testExe.setPipelineIdentifier("AdditionTest/0.9");
//			Map<String,Object> testMap = new HashMap<String, Object>();
//			testMap.put("number1", new Integer(5));
//			testMap.put("number2", new Integer(56));
//			testMap.put("results-directory", "/vip/Home");
//			testExe.setInputValues(testMap);
//			System.out.println("result: "+newDapi.initAndStartExecution(testExe));
//			System.out.println("****************************************");
			
			System.out.println("test URL /executions/{execution identifier}, method GET, action: print information about the specified execution");
			System.out.println("result: "+newDapi.getExecution("workflow-ti7OOL"));
			System.out.println("****************************************");
			
			System.out.println("test URL /executions/{execution identifier}, method PUT, action: modify only name and timeout parameters");
			Execution testExe = new Execution();
			testExe.setName("test_1015");
			testExe.setPipelineIdentifier("AdditionTest/0.9");
			testExe.setTimeout(0L);
			newDapi.updateExecution("workflow-ti7OOL", testExe);
			System.out.println("****************************************");
			
			System.out.println("test URL  /executions/{executionIdentifier}/results, method GET, action: give the result file of the execution");
			System.out.println("result: "+newDapi.getExecutionResults("workflow-WR7MAl",""));
			System.out.println("****************************************");
			
			System.out.println("test URL  /executions/{executionIdentifier}/stdout, method GET, action: print execution's stdout");
			System.out.println("result: "+newDapi.getStdout("workflow-WR7MAl"));
			System.out.println("****************************************");
			
			System.out.println("test URL  /executions/{executionIdentifier}/stderr, method GET, action: print execution's stderr");
			System.out.println("result: "+newDapi.getStderr("workflow-PvlSwI"));
			System.out.println("****************************************");
			
			System.out.println("test URL  /executions/{executionIdentifier}/play, method PUT, action: play the execution ==> NOT IMPLEMENTED");
//			newDapi.playExecution("workflow-ti7OOL");
			System.out.println("****************************************");
			
			System.out.println("test URL  /executions/{executionIdentifier}/kill, method PUT, action: kill the specified execution");
			newDapi.killExecution("workflow-ti7OOL");
			System.out.println("****************************************");
			
			System.out.println("test URL  /executions/{executionIdentifier}/delete, method PUT, action: delete the specified execution, kill the underlying processes (if possible) and free all resources associated with this execution ==> THINK ABOUT IT");
			DeleteExecutionConfiguration testDeleteExe = new DeleteExecutionConfiguration();
//			newDapi.deleteExecution("workflow-nQizzu", testDeleteExe);
			System.out.println("****************************************");
			
			System.out.println("test URL  /pipelines, method GET, action: print the pipelines list");
			System.out.println(newDapi.listPipelines(""));
			System.out.println("****************************************");
			
			System.out.println("test URL  /pipelines/{pipelineIdentifier}, method GET, action: Show the definition of a given pipeline  ==> THINK ABOUT IT");
//			try{
			System.out.println(newDapi.getPipeline("AdditionTest%2F0.9"));
//			}catch(ApiException ae){
//				System.out.println(ae.getResponseBody()+ae.getCode()+ae.getResponseHeaders());
//				
//			}
			
			System.out.println("****************************************");

		}

	
}
