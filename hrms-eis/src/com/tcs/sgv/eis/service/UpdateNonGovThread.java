package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.valueobject.HrPayPayslipNonGovt;

public class UpdateNonGovThread extends Thread{
	ServiceLocator serv = null;
	Log logger = LogFactory.getLog(getClass());
	List<HrPayPayslipNonGovt> nonGovList = new ArrayList<HrPayPayslipNonGovt>();
	public UpdateNonGovThread() {		
	}
	
	public UpdateNonGovThread(List<HrPayPayslipNonGovt> lstNonGov,ServiceLocator serv) {
		this.nonGovList = lstNonGov;
		this.serv = serv;
	}
	
	public void run()
	{
		
		   logger.info("Inside run of UpdateNonGovThread");
		   if(nonGovList!=null)
		     logger.info("size of non Gov vo list to be updated is " + nonGovList.size());
		
		
		if(serv!=null) {
			GenericDaoHibernateImpl nonGovtPayslipDao = new GenericDaoHibernateImpl(HrPayPayslipNonGovt.class);
			nonGovtPayslipDao.setSessionFactory(serv.getSessionFactory());
		    for(HrPayPayslipNonGovt newNonGovtPayslip : nonGovList) {
		    	//nonGovtPayslipDao.update(newNonGovtPayslip);
		    }
		}
	}
}
