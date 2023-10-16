package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;




/**
 * This class is to Set the VO to fetch and
 * insert the data into GPF Master table
 */
public class EmpGpfDtlsVOGen extends ServiceImpl 
{
	
	Log logger = LogFactory.getLog(getClass());
	//static int i;
	
	
	public ResultObject insertGpfDtlsData(Map objServiceArgs) 
	{
		logger.info("Inside EmpGpfDtlsVOGen------");
		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		try{
		HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
						
		long empId=0;
		String gpfAcctNo="";
		long userId=0;
		
		//added by Ankit Bhatt for Class (Grade)
		long gradeId = 0;
		gradeId=Long.parseLong(((StringUtility.getParameter("gradeNo",request)!=null&&!(StringUtility.getParameter("gradeNo",request).equals(""))?(StringUtility.getParameter("gradeNo",request)):0).toString()));
		//ended
		
		String editMode = StringUtility.getParameter("edit",request);
		if(editMode!=null&&!editMode.equals(""))
		{
		empId=Long.parseLong(((StringUtility.getParameter("empName",request)!=null&&!(StringUtility.getParameter("empName",request).equals(""))?(StringUtility.getParameter("empName",request)):0).toString()));
		gpfAcctNo=(StringUtility.getParameter("gpfAcctNo",request)!=null&&!(StringUtility.getParameter("gpfAcctNo",request).equals(""))?(StringUtility.getParameter("gpfAcctNo",request)):0).toString();
		
		GenericDaoHibernateImpl gImpl = null;
		
		OrgEmpMst orgEmpMst=new OrgEmpMst();            
        gImpl = new GenericDaoHibernateImpl(OrgEmpMst.class);
        gImpl.setSessionFactory(serv.getSessionFactory());
        orgEmpMst = (OrgEmpMst)gImpl.read(empId);
        
		
        HrPayGpfBalanceDtls GpfDtls = new HrPayGpfBalanceDtls();
			
		logger.info("The value of Editmode in VO is------->"+editMode);
		
		Date sysdate=new Date();
		if(editMode.equalsIgnoreCase("N")) 
		{		
			objServiceArgs.put("edit","N");
			long num=0;
		/*	GpfDtls.setAccStartDt(sysdate);
			GpfDtls.setAdvBalanceOutstanding(num);
			GpfDtls.setAdvCurrYr(num);
			GpfDtls.setClosingBalance((double)num);
			GpfDtls.setCreditAmt(num);
			GpfDtls.setFinancialYear("");
			GpfDtls.setHigherPayAmt(num);
			GpfDtls.setInstallNo(0);
			GpfDtls.setInterest((double)num);
			GpfDtls.setMonth("");
			GpfDtls.setNetBalance((double)num);
			GpfDtls.setRefundAmt(num);
			GpfDtls.setRefundCurrYr(num);
			GpfDtls.setSubRate((double)num);
			GpfDtls.setSubsCurr((double)num);
			GpfDtls.setWithCurrYr(num);
			GpfDtls.setWithdrawal(num); */
        	GpfDtls.setUserId(orgEmpMst.getOrgUserMst().getUserId()); 
		}
		else if(editMode.equalsIgnoreCase("Y"))
		{
			logger.info("***********"+StringUtility.getParameter("gpfId", request));
			String gpfId=(StringUtility.getParameter("gpfId", request)!=null&&!(StringUtility.getParameter("gpfId", request).equals(""))?(StringUtility.getParameter("gpfId", request)):"").toString();

	        
			long gpfID=0;
			if(gpfId!=null)
        	{
				gpfID=Long.parseLong(gpfId);
        	}
			
			gImpl = new GenericDaoHibernateImpl(HrPayGpfBalanceDtls.class);
	        gImpl.setSessionFactory(serv.getSessionFactory());
	        GpfDtls = (HrPayGpfBalanceDtls) gImpl.read(gpfID);
			
        	objServiceArgs.put("edit","Y");
		}
		//added by Ankit Bhatt
		objServiceArgs.put("gradeId",gradeId);
		//ended
		GpfDtls.setGpfAccNo(gpfAcctNo);
		objServiceArgs.put("GpfDtls",GpfDtls);
		}
		retObj.setResultValue(objServiceArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		
		}catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;
		}
		return retObj;
	}
	
	public ResultObject generateMap(Map arg0) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	 
}
