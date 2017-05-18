package auto_test.vip;

import java.util.List;
import java.util.Iterator;
import java.util.Properties;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.DeleteExecutionConfiguration;
import io.swagger.client.model.Execution;


public class Scenario {
	private App appScenario = new App();
	Properties prop = appScenario.propertiesExtraction();
	
	//tries to launch an execution an waits the end of it
	public boolean scenario1(String key) throws Exception{	
		//Client initialization
		DefaultApi defaultApiClient1 = appScenario.initClient(prop.getProperty("viptest.additiontest.url"), key);
		
		//pipelines list
		defaultApiClient1.listPipelines("");
		
		//check parameters for a specified pipeline
		defaultApiClient1.getPipeline(prop.getProperty("viptest.additiontest.pipelineidentifier"));
		
		//create and start an execution
		Execution result = defaultApiClient1.initAndStartExecution(appScenario.initExecution("test_335", "AdditionTest/0.9", 40, 41));
				
		//keep the identifier
		String exeId = result.getIdentifier();
		
		while(result.getStatus().toString().equals("running")){
			Thread.sleep(Long.valueOf(prop.getProperty("viptest.additiontest.timecheck")));
			//check the execution
			result = defaultApiClient1.getExecution(exeId);
		}
		
		return result.getStatus().toString().equals("finished");
	}

	//tries to modify an execution by changing name 
	public boolean scenario2(String key) throws Exception{	
		//Client initialization
		DefaultApi defaultApiClient2 = appScenario.initClient(prop.getProperty("viptest.additiontest.url"), key);
		
		//execution history
		String exeId = defaultApiClient2.listExecutions().iterator().next().getIdentifier();
		
		//check a particular execution
		Execution result1 = defaultApiClient2.getExecution(exeId);
		
		//modification of name parameter of the execution
		String newName = appScenario.randomSelection();
		Execution body = appScenario.modifExecution(newName, 0L, "AdditionTest/0.9");
		defaultApiClient2.updateExecution(exeId, body);
		
		//check execution modification
		Execution result2 = defaultApiClient2.getExecution(exeId);

		return !(result1.getName().equals(result2.getName())) || !(result1.getTimeout().equals(result2.getTimeout()));
	}
	
	//tries kill a bugged execution end restart it
	public boolean scenario3(String key) throws Exception{
		//Client initialization
		DefaultApi defaultApiClient3 = appScenario.initClient(prop.getProperty("viptest.additiontest.url"), key);		
		
		//create and start the execution
		Execution body = appScenario.initExecution("newScenarioKo", "AdditionTest/0.9", 1, 2);
		Execution result = defaultApiClient3.initAndStartExecution(body);
		String resId = result.getIdentifier();
				
		//execution history
		defaultApiClient3.listExecutions();

		//kill the bugged execution
		defaultApiClient3.killExecution(resId);
		
		//check the status execution
		result = defaultApiClient3.getExecution(resId);
		
		Boolean test1 = result.getStatus().toString().equals("killed");
		
		//create and restart the execution
		body = appScenario.initExecution("newScenario3", "AdditionTest/0.9", 1, 2);
		result = defaultApiClient3.initAndStartExecution(body);
		resId = result.getIdentifier();
		defaultApiClient3.killExecution(resId);

		boolean test2 = result.getStatus().toString().equals("running");
		return test1 && test2;
	}
	
	public boolean scenario4(String key) throws Exception{
		//Client initialization
		DefaultApi defaultApiClient4 = appScenario.initClient(prop.getProperty("viptest.additiontest.url"), key);
		Execution[] tab = new Execution[10];
		//System.out.println("Step 1");
		//execution history
		List<Execution> list = defaultApiClient4.listExecutions();
		//System.out.println("Step 2");
		int i=0;
		Iterator<Execution> it = list.iterator();
		//System.out.println("Step 3");
		while(it.hasNext() && i<10){
			//System.out.println("Step 4."+i);
			Execution e= (Execution) it.next();
			//System.out.println(e.getStatus()+"	"+e.getStatus().equals("finished")+" 	"+e.getStatus().toString().equals("finished"));
			if(e.getStatus().toString().equals("finished")){ // A TRAVAILLER
				tab[i] = e;
				i++;
			}
		}		
		//System.out.println(defaultApiClient4.getExecution(tab[5].getIdentifier()));
		DeleteExecutionConfiguration body = new DeleteExecutionConfiguration();
		body.setDeleteFiles(true);
		defaultApiClient4.deleteExecution(tab[5].getIdentifier(), body);
		//System.out.println(defaultApiClient4.getExecution(tab[5].getIdentifier()));
		return false;
	}
	
	//tries to modify an execution but put the same name and the same timeout so NO modification
	public boolean scenario5(String key) throws Exception{	
		//Client initialization
		DefaultApi defaultApiClient5 = appScenario.initClient(prop.getProperty("viptest.additiontest.url"), key);
		
		//execution history
		String exeId = defaultApiClient5.listExecutions().iterator().next().getIdentifier();
		System.out.println(defaultApiClient5.listExecutions());
		
		//check a particular execution
		Execution result1 = defaultApiClient5.getExecution(exeId);
		
		//modification of name parameter of the execution
		Execution body = appScenario.modifExecution(result1.getName(), result1.getTimeout(), "AdditionTest/0.9");
		defaultApiClient5.updateExecution(exeId, body);
		
		//check execution modification
		Execution result2 = defaultApiClient5.getExecution(exeId);

		return !(result1.getName().equals(result2.getName()) || result1.getTimeout().equals(result2.getTimeout()));
	}
	
	//tries to launch two Execution without good right
	public boolean scenario6(String key) throws Exception{	
		//Client initialization
		DefaultApi defaultApiClient6 = appScenario.initClient(prop.getProperty("viptest.additiontest.url"), key);
		
		//create and start an execution
		Execution body = appScenario.initExecution("newScenario3", "AdditionTest/0.9", 1, 2);
		Execution result = defaultApiClient6.initAndStartExecution(body);
		String resId = result.getIdentifier(); 
		//create and start another execution
		try{
		body = appScenario.initExecution("newScenario3", "AdditionTest/0.9", 1, 2);
		result = defaultApiClient6.initAndStartExecution(body);
		}catch(ApiException ae){
			defaultApiClient6.killExecution(resId);
			return false;
		}
		defaultApiClient6.killExecution(resId);
		return true;
	}
}
