package com.tcs.sgv.eis.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;
import com.tcs.sgv.reports.common.dao.CmnLookupMstDAO;
import java.util.Enumeration;
import java.util.Set;


public class SchedulerVOGenImpl extends ServiceImpl

{
	

	Log logger = LogFactory.getLog(getClass());
	public ResultObject insertSchedulerVogen(Map objectArgs)
	{
		try{
			logger.info("TestCaseVOGenImpl:::::::::::::::::");
			//System.out.println("This is Vogeniml::::::::::::::::::::::");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");			
		
		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		//Abhilash started for update
		HrPayPaybillScheduler hrpaypaybillscheduler;
		String editMode = StringUtility.getParameter("edit",request);
		
		long SchedulerId = 0; 
		if(editMode.equalsIgnoreCase("N")) 
		{	
			 //System.out.println("This is VOGEN editMode::::::::::::::::::::::" + editMode);
			hrpaypaybillscheduler = new HrPayPaybillScheduler();
			objectArgs.put("edit","N");
		}
        else
        {       	 
		 String schedulerId = StringUtility.getParameter("txtSchedulerID", request);
		 //System.out.println("This is VOGEN schedulerId::::::::::::::::::::::" + schedulerId);
		 logger.info("This is VOGEN schedulerId::::::::::::::::::::::" + schedulerId);
		 if( schedulerId!=null ){
			 SchedulerId = Long.parseLong(schedulerId);
		  }
		 hrpaypaybillscheduler = new HrPayPaybillScheduler();
		 objectArgs.put("edit","Y");		 
        }
		String cmbDept =StringUtility.getParameter("cmbDept", request);
		CmnLocationMst cmnlocation = new CmnLocationMst();
		CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
		cmnlocation = locationDAO.read(Long.parseLong(cmbDept));
		
		//System.out.println("This is Vogeniml::::::::::::::::::::::" +  cmnlocation.getLocId());
		
		String billNoList =StringUtility.getParameter("billNoList", request);
		HrPayBillHeadMpg hrpaybillheadmpg =new HrPayBillHeadMpg();
		BillHeadMpgDAOImpl billheadDAO =null;//new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class,serv.getSessionFactory());
		//hrpaybillheadmpg = billheadDAO.read(Long.parseLong(billNoList));
		
		String day =StringUtility.getParameter("Day", request);
		if(day!=null && !day.equals(""))
		hrpaypaybillscheduler.setDay(Long.valueOf(day));
		//System.out.println("This is Vogeniml:::::::1111111111111:::::::::::::::");				
		//HrPayPaybillScheduler hrpaypaybillscheduler = new HrPayPaybillScheduler();
		/*hrpaypaybillscheduler.setCmnLocationMst(cmnlocation);
		hrpaypaybillscheduler.setHrpaybillsubheadmpg(hrpaybillheadmpg);*/
		hrpaypaybillscheduler.setSchedulerId(SchedulerId);		
		//System.out.println("This is Vogeniml:::::::::::::schedulerMstVO:::::::::" +  hrpaypaybillscheduler.getSchedulerId());
		String Day =StringUtility.getParameter("Day", request);	
		
		//Abhilash ended for update
		
	    	
		
		
      
		
	    objectArgs.put("hrpaypaybillscheduler",hrpaypaybillscheduler);
		resultObject.setResultValue(objectArgs);
		
		
		
		
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		return resultObject;
		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			Map errorMap = new HashMap();
			errorMap.put("msg", "There is some problem. Please Try Again Later.");
			retObj.setResultCode(-1);
			retObj.setResultValue(errorMap);
			retObj.setViewName("errorInsert");
			e.printStackTrace();
			return retObj;
			
		}
		
	}
	

	/*
	 * @purpose: 	- voToXml.
	 * 				- To insert multiple data.
	 * 
	 */
	
public ResultObject multipleData(Map objectArgs)
{
		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
           logger.info(" VOGEN multipleData() method called....");
           //System.out.println("VOGEN multipleData() method called....");
			logger.info("objectArgs in multipleData: "+objectArgs.toString());
			//System.out.println("objectArgs in multipleData: "+objectArgs.toString());
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			HrPayPaybillScheduler hrPaybillScheduler = new HrPayPaybillScheduler();
			
			
			//System.out.println("abhilash multipleData 1111111111");
			/*long deptId=0;
			long billId=0;
			long day=0;*/
			
			String cmbDept =StringUtility.getParameter("cmbDept", request);
			String billNoList =StringUtility.getParameter("billNoList", request);
			String day =StringUtility.getParameter("Day", request);
			
				
			CmnLocationMst cmnlocation = new CmnLocationMst();
			CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			cmnlocation = locationDAO.read(Long.parseLong(cmbDept));
			
			HrPayBillHeadMpg hrpaybillheadmpg =new HrPayBillHeadMpg();
			BillHeadMpgDAOImpl billheadDAO =null;//new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class,serv.getSessionFactory());
			/*hrpaybillheadmpg = billheadDAO.read(Long.parseLong(billNoList));
			
			hrPaybillScheduler.setCmnLocationMst(cmnlocation);
			hrPaybillScheduler.setHrpaybillsubheadmpg(hrpaybillheadmpg);*/
			hrPaybillScheduler.setDay(Long.valueOf(day));
			//System.out.println("abhilash multipleData 222222222222222");
			
			String xmlFileId = FileUtility.voToXmlFileByXStream(hrPaybillScheduler);
	       	objectArgs.put("ajaxKey", xmlFileId);
	       	logger.info("VO successfully created into XML..");
	       	logger.info("XML file PATH:::"+xmlFileId);


			retObj.setViewName("ajaxData");
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			logger.info("abhilash multipleData U are out of VOGEN");
			//System.out.println("abhilash multipleData U are out of VOGEN");
		}
		catch(Exception e)
		{
			logger.info("U r in Exception + VOGEN");
			logger.info("Exception occured: " +e.toString());
			e.printStackTrace();
			Map result = new HashMap();
			result.put("MESSAGECODE",3001);
			retObj.setResultValue(result);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;			
		}
		
		return retObj;
			
}

}
