
package com.tcs.sgv.eis.service;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;


public class CommonSchedulerServlet extends HttpServlet{
	Log logger = LogFactory.getLog(getClass());

	SchedulerFactory sf = null;
	public void init(ServletConfig config) throws ServletException
	  {
	    	super.init(config);
	    	sf = new StdSchedulerFactory();
		    PayBillCronScheduler billCronScheduler = new PayBillCronScheduler(); 
		    try {
				 if(!sf.getScheduler().isShutdown())
					 sf.getScheduler().shutdown();
				 billCronScheduler.run(sf);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				logger.error("Error is: "+ e.getMessage());
			}
	  }
	
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
     throws ServletException, IOException
     {
		 doPost(req, resp);
     }
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp)
     throws ServletException, IOException
     {
			 PayBillCronScheduler billCronScheduler = new PayBillCronScheduler(); 
			 try {
				 if(!sf.getScheduler().isShutdown())
					 sf.getScheduler().shutdown();
				 billCronScheduler.run(sf);
			 } catch (SchedulerException e) {
				// TODO Auto-generated catch block
				logger.error("Error is: "+ e.getMessage());
			 }
     }
	 
}
