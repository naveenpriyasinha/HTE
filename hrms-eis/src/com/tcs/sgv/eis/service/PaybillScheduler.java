package com.tcs.sgv.eis.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;



public class PaybillScheduler  implements Job {
	public static final long LANG_ID = 1;
	public static final long USER_ID = 1;
	//public static final long LOC_ID = 300022;
	public static final long DB_ID = 99;
	public static final long EMP_ID = 1;
	public static final String DOC_ID = "";
	public static final long SYSTEM_USER_ID = 1;
	public static final long SYSTEM_POST_ID = 1;
	
	private static final Log logger = LogFactory.getLog(PaybillScheduler.class);
	
	public void test() {
		System.out.println("schedular sucess Naveen");
		
		
		
		for(int i=0;i<10;i++) {
			System.out.println("schedular sucess Naveen");	
		}
	}
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		//Transaction transaction = null;
		//Session hibSession= null;
		String threadId = "1";
		try
		{ 
			logger.info("Inside  Scheduler::::::::::::::::::wow.......");
			//System.out.println("Inside  Scheduler::::::::::::::::::wow.......");
			if(ServiceLocator.getServiceLocator()!=null && ServiceLocator.getServiceLocator().getSessionFactory()!=null)
			{	
				if( jobContext.getJobDetail() != null  && jobContext.getJobDetail().getJobDataMap().containsKey("threadId")){
					 threadId = (String)jobContext.getJobDetail().getJobDataMap().get("threadId");
				}
				
				
				Map objectArgs = new HashMap();
				Map previousBaseLoginMap = null;
				
				ServiceLocator serv = ServiceLocator.getServiceLocator();
				
				objectArgs.put("serviceLocator", serv);
				
				
				// baseLoginMap ENDS 
					Map map = new HashMap();
					
					map.put("userId", USER_ID);
					map.put("langId", LANG_ID);
					map.put("loggedInPost", SYSTEM_POST_ID);
				//	map.put("locationId", LOC_ID);
					map.put("primaryPostId",SYSTEM_POST_ID);
					map.put("dbId", DB_ID);
					map.put("empId",EMP_ID);
					
					if(objectArgs.get("baseLoginMap")!=null){
						previousBaseLoginMap = (Map) objectArgs.get("baseLoginMap");
					}
					objectArgs.put("baseLoginMap",map);
				// baseLoginMap ENDS 
					
					objectArgs.put("threadId", threadId);
					new GenerateBillServiceScheduler().getMethod(objectArgs);
					//hibSession.flush();
		
	}
			else
				throw new Exception("Exception from Schedular Code");
	
	
	
	
 }
		catch(RuntimeException e){
			
			logger.error("========= Excepton occured in running PaybillScheduler scheduler ===="+DBUtility.getCurrentDateFromDB()+e.getMessage());
			logger.error("Error is: "+ e.getMessage());
		}
		catch(Exception e) {
			//transaction.rollback();
			logger.error("Error is: "+ e.getMessage());
		}
		finally{
			/*try{
				hibSession.close();
				
			}
			catch(Exception e){
				logger.info("Error in closing connection "+e);
			}*/
			logger.error("Thread ID " + threadId + " Finished Execution :  Varshil");
		}
	}
}