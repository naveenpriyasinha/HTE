package com.tcs.sgv.billproc.audit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.audit.dao.AuditorObjectionDAOImpl;
import com.tcs.sgv.billproc.common.dao.RemarksDAOImpl;
import com.tcs.sgv.billproc.common.valueobject.PrevAdtObjections;
import com.tcs.sgv.billproc.common.valueobject.TrnBillRemarks;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.RltBillObjection;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

/** AuditorObjectionServiceImpl
 *  This class is used to open inwarded bill in edit mode and save all necessary changes made in the
 *  screen by the end user. It also saves objection details entered by the user
 * 	Date of Creation : 10th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *  Vidhya M    23-Oct-2007   For making changes for code formating
 */
public class AuditorObjectionServiceImpl
	extends ServiceImpl 
	implements AuditorObjectionService
{
	
	Log logger = LogFactory.getLog(getClass());
	
	/**
	 * This method returns the ResultObject after inserting the updated values in related tables.
	 * @param objectArgs : Map
	 * 
	 * @return ResultObject
	 */	
	public ResultObject insertAdtObjectionDetails(Map objectArgs)
	{		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			logger.info("Before Inside AuditorObjectionServiceImpl, map is : " +objectArgs.toString());
			int intFlag = 0;
						
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		 				
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			AuditorObjectionDAOImpl lObjAdtObjDAOImpl = new AuditorObjectionDAOImpl(RltBillObjection.class,serv.getSessionFactory());		
			RemarksDAOImpl lObjRemarksDAOImpl = new RemarksDAOImpl(TrnBillRemarks.class,serv.getSessionFactory());
			PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(TrnBillRegister.class,serv.getSessionFactory());			
			String lStrBillNo = request.getParameter("BillNo");		
			Long lLngUserId = SessionHelper.getUserId(request);
				
			logger.info("Before - -- -- -- Value of ResultObject in AuditorObjectionServiceImpl : " +objRes.toString());
			List lLstObjList = (List)objectArgs.get("auditObjList");
			TrnBillRemarks lObjTrnBillRemarksVO =  (TrnBillRemarks) objectArgs.get("TrnBillRemarksVO");
			lObjTrnBillRemarksVO.setBillNo(Long.parseLong(lStrBillNo));
			
			objectArgs.put("TrnBillRemarksVO", lObjTrnBillRemarksVO);

			intFlag = lObjPhyBillDAOImpl.updateTrnBillReg(objectArgs, lStrBillNo);
			logger.info("Value of flag from updateTrnBillREg :: " +intFlag);
			if(intFlag > 0)
			{
				lObjAdtObjDAOImpl.updateObjection(lLstObjList, lStrBillNo, lLngUserId.toString(),objectArgs);
				lObjPhyBillDAOImpl.updateBudHeadDetails(objectArgs, lStrBillNo);	
				lObjRemarksDAOImpl.updateRemarks(objectArgs);
			}
			
			logger.info("Value of Integer Flag in Auditor Objection SErvice : " +intFlag);
			objectArgs.put("intFlag",intFlag);			
			objRes.setResultValue(objectArgs);

			logger.info("After - -- -- -- Value of ResultObject in AuditorObjectionServiceImpl : " +objRes.toString());
			logger.info("After Inside AuditorObjectionServiceImpl, map is : " +objectArgs.toString());
		}
		catch (Exception e) 
		{
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in insertAdtObjectionDetails  " + e,e);
		}
		return objRes;
	}
	
		
	/**
	 * This method returns the ResultObject and is used to get the Remarks of previous Auditors
	 * @param inputMap : Map
	 * 
	 * @return ResultObject
	 */	
	public ResultObject getPrevAudit(Map inputMap)
	{		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("Inside get PREVIOUS AUDITOR");
						
			ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");		
			
			String lStrUser = "";
			String lStrEmpName = "";
			List lLstDetails = new ArrayList();

			BptmCommonServicesDAOImpl lObjCmnSrvc = new BptmCommonServicesDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			AuditorObjectionDAOImpl lObjAdtObjDAOImpl = new AuditorObjectionDAOImpl(RltBillObjection.class,serv.getSessionFactory());		
			String lStrBillNo = request.getParameter("BillNo").toString();
			Long lLngLangId = SessionHelper.getLangId(inputMap);
			
			logger.info("Bill No ins SERvice fo Auditor Objeciotn----------------------=========== : " +lStrBillNo);		
			
			List lLstAllUsers = lObjAdtObjDAOImpl.getRmaksObjUsers(lStrBillNo, lLngLangId);
			logger.info("Value of List of Users : " +lLstAllUsers.toString());
			if(lLstAllUsers!=null && lLstAllUsers.size()>0)
			{
				Iterator it = lLstAllUsers.iterator();			
				while(it.hasNext())
				{					
					PrevAdtObjections lObjPrevAudRmrks = new PrevAdtObjections();
					lStrUser = it.next().toString();
					lStrEmpName = lObjCmnSrvc.getEmpNameFrmUserId(lStrUser, lLngLangId);
					logger.info("Employee name to be set in VO is : " +lStrEmpName);
					lObjPrevAudRmrks.setEmpName(lStrEmpName);
					
					List lLstRemarks = lObjAdtObjDAOImpl.getPrevRemarks(lStrBillNo, lStrUser);
					logger.info("Value of Remarks to be set in VO is : " +lLstRemarks.toString());
					lObjPrevAudRmrks.setRemarks(lLstRemarks);
					
					List lLstObjection = lObjAdtObjDAOImpl.getPrevObjections(lStrBillNo, lLngLangId, lStrUser);
					logger.info("Value of Objection List to be set in VO : " +lLstObjection);
					lObjPrevAudRmrks.setObjections(lLstObjection);
					lLstDetails.add(lObjPrevAudRmrks);
				}
			}
			logger.info("Size of Final list is : " +lLstDetails.size() +" and Value is : " +lLstDetails.toString());
			inputMap.put("PrevRemarksList",lLstDetails);
			
			objRes.setResultValue(inputMap);
			objRes.setSessionValues("RmkID",inputMap);
	    	objRes.setViewName("showPrevAudRemarks");	    	
		}
		catch(Exception e)
		{
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in getPrevAUdit  " + e,e);
		}
		return objRes;
	}
	/**
	 * This method returns the ResultObject and is used to get the Remarks of previous Auditors
	 * @param inputMap : Map
	 * 
	 * @return ResultObject
	 */	
	public ResultObject getAllObjection(Map inputMap)
	{		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("Inside get All Remarks");
						
			ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");		
			
			String lStrUser = "";
			String lStrEmpName = "";
			List lLstDetails = new ArrayList();

			BptmCommonServicesDAOImpl lObjCmnSrvc = new BptmCommonServicesDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			AuditorObjectionDAOImpl lObjAdtObjDAOImpl = new AuditorObjectionDAOImpl(RltBillObjection.class,serv.getSessionFactory());
			String pageFlg =(String) request.getParameter("pageFlg");
			String lStrBillNo []=null;
			if(pageFlg.equals("sb"))								// if page flg is show bill  else comes from reject case
			{
				String lStrBill = request.getParameter("BillNo").toString();
				lStrBillNo = new String[1];
				lStrBillNo[0]=lStrBill;
			}
			else
			{
				String lStrBillNumbers =null;
				//System.out.println("Chq box data is ssssssssssssssssssssssssssssss"+request.getParameterValues("chkbox"));
				if(request.getParameter("billLst")!=null)
				{
					lStrBillNumbers=request.getParameter("billLst");
					//System.out.println("Chq data is size is  :::"+lStrBillNumbers);
					lStrBillNo=lStrBillNumbers.split(",");
					
				}
				
			}
			String BillControlNo [] = lStrBillNo;
			List lStrBillControlNo = new ArrayList();
			for(int k=0;k<BillControlNo.length;k++)
			{
				BillControlNo[k]=lObjCmnSrvc.getBillCntrlNoFromBillNo(lStrBillNo[k]);
			}
			
			Long lLngLangId = SessionHelper.getLangId(inputMap);
			
			Map objMap = lObjAdtObjDAOImpl.getAllObjectionData(BillControlNo, lLngLangId);
			
			inputMap.put("objMap",objMap);
			inputMap.put("billCntrList", lStrBillControlNo);
			
			
			objRes.setResultValue(inputMap);
			//objRes.setSessionValues("RmkID",inputMap);
	    	objRes.setViewName("getAllObjections");	    	
		}
		catch(Exception e)
		{
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in getAllObjecions  " + e,e);
		}
		return objRes;
	}
}
