
package com.tcs.sgv.eis.service;

import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;

import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.valueobject.HrPayPayBillSchdlrDtls;

public class PayBillCronScheduler
{
	Logger glogger = Logger.getLogger(PayBillCronScheduler.class);
	JobDetail jobDetail = null;
	Session hibernateSession = null;

	public void run(SchedulerFactory sf) {
		try {/*
			
			Scheduler sched = sf.getScheduler();
			
			glogger.error("Inside run method of schedular");
			glogger.info("Entering In run Method Of schedular  : - Milliseconds " + new Date().getTime());
			sched.start();

			//fetch number of Thread from DB
			HrPayPayBillSchdlrDtls lookup = new HrPayPayBillSchdlrDtls ();
			ServiceLocator serv = ServiceLocator.getServiceLocator();
			Session hibSession=serv.getSessionFactory().openSession();
			hibSession.getTransaction().begin();
			
			Statement  lockStatement = hibSession.connection().createStatement();
			try{
				hibSession.connection().setAutoCommit(false);
				lockStatement.execute("LOCK TABLE HR_PAY_PAYBILLSCHDLR_DTLS IN EXCLUSIVE mode");
			}catch(Exception e){
				glogger.error("Error is: "+ e.getMessage());
			}
			String query = "from HrPayPayBillSchdlrDtls dtls ";
			Query q = hibSession.createQuery(query);
			lookup = (HrPayPayBillSchdlrDtls)q.uniqueResult();
			
			
			String cronExpr = "";
			long startThread = 1;
			long endThread = 50;
			long threadRange = 50;
			
			cronExpr = lookup.getCronTime() != null ? lookup.getCronTime() : "";
			startThread = Long.valueOf(lookup.getStartThread()).intValue();
			endThread = Long.valueOf(lookup.getEndThread()).intValue();
			threadRange = Long.valueOf(lookup.getThreadRange()).intValue();
				
			//System.out.println("Cron " + cronExpr + " SThread " +startThread + " EThread " + endThread + " Counter " + lookup.getCounter());
			
			String cronExprHrs = cronExpr.split("~")[1];
			String cronExprMins = cronExpr.split("~")[0];
				
			cronExprMins = String.valueOf( (Integer.parseInt(cronExprMins)) + 5 );
			if( (Integer.parseInt(cronExprMins)) > 59 ){
				cronExprMins = String.valueOf((Integer.parseInt(cronExprMins)) - 60);
				cronExprHrs = String.valueOf( (Integer.parseInt(cronExprHrs)) + 1 );
			}
			if(Integer.parseInt(cronExprHrs) > 23){
				cronExprHrs = cronExpr.split("~")[1];
				cronExprMins = cronExpr.split("~")[0];
			}
			
			
			lookup.setCounter((lookup.getCounter() + 1));
			lookup.setStartThread(endThread + 1);
			lookup.setEndThread(threadRange + endThread);
			lookup.setCronTime(cronExprMins + "~" + cronExprHrs);
			
			hibSession.saveOrUpdate(lookup);
			lockStatement.close();
			hibSession.connection().commit();
			hibSession.getTransaction().commit();
			hibSession.close();
			//fetch number of Thread from DB
			
			 cronExprHrs = cronExpr.split("~")[1];
			 cronExprMins = cronExpr.split("~")[0];
			
			
			for(long i = startThread ;i <= endThread ; i++){
				jobDetail = sched.getJobDetail("PaybillSchedulerJob_"+i, "GROUP_"+i);
				
				
				if (jobDetail == null) {
					jobDetail = new JobDetail("PaybillSchedulerJob_"+i, "GROUP_"+i,PaybillScheduler.class);
					
					Map<String,String> dataMap = new HashMap<String,String>();
					dataMap.put("threadId", String.valueOf(i));
					JobDataMap jobDataMap = new JobDataMap(dataMap);
					jobDetail.setJobDataMap(jobDataMap);
					
					CronTrigger trigger = new CronTrigger("mytrigger_"+i,"GROUP_"+i);
					//String cronExpression = "0 15 21 * * ?"; //cron job expression from db
					
					String expr = cronExprMins + " " + cronExprHrs;
					CronExpression cronExp = new CronExpression("0 "+expr+" * * ?");
					
					cronExprMins = String.valueOf( (Integer.parseInt(cronExprMins)) + 1 );
					if( (Integer.parseInt(cronExprMins)) > 59 ){
						cronExprMins = String.valueOf(0);
						cronExprHrs = String.valueOf( (Integer.parseInt(cronExprHrs)) + 1 );
					}
					if(Integer.parseInt(cronExprHrs) > 23){
						cronExprHrs = cronExpr.split("~")[1];
						cronExprMins = cronExpr.split("~")[0];
					}
					
					trigger.setCronExpression(cronExp);
					sched.scheduleJob(jobDetail, trigger);
					glogger.error("Seeking For Job Details for" + new Date());
				} else {
					glogger.error("Job Details For Scheduler already found for " + "PaybillSchedulerJob_"+i);
				}
			}
			

		*/} catch (Exception e) {
			glogger.error("Exception while scheduling a batch job"
					+ e.getMessage(), e);
		}

	}
}
