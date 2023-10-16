package com.tcs.sgv.eis.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.DDOInfoDAO;
import com.tcs.sgv.eis.dao.DDOInfoDAOImpl;
import com.tcs.sgv.eis.dao.TokenNumberDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.TokenNumberCustomVO;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;


public class DDOInfoServiceImpl<HrOrgSchemeMst> extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());

	
public ResultObject getddoinfo(Map<String, List<String>> objectArgs)
{
	logger.info("...................in DDOServiceImpl...................");
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	
	try{
		List<String> adminList = new ArrayList<String>();
		List<String> deptList = new ArrayList<String>();
		List<String> desigList = new ArrayList<String>();
		List<String> dateList = new ArrayList<String>();
		
		
		adminList.add("a");
		adminList.add("b");
		adminList.add("c");
		adminList.add("d");
		adminList.add("e");
		
		logger.info(".............adminList.................. "+adminList);
		deptList.add("a");
		deptList.add("b");
		deptList.add("c");
		deptList.add("d");
		deptList.add("e");
		 
		logger.info(".............deptList.................."+deptList);
		desigList.add("a");
		desigList.add("b");
		desigList.add("c");
		desigList.add("d");
		desigList.add("e");
		
		dateList.add("a");
		dateList.add("b");
		dateList.add("c");
		dateList.add("d");
		dateList.add("e");
				 
		objectArgs.put("adminList", adminList);
		objectArgs.put("deptList", deptList);
		objectArgs.put("desigList", desigList);
		objectArgs.put("dateList", dateList);
		logger.info("............. objectArgs.................."+ objectArgs);
					
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		resultObject.setResultValue(objectArgs);
		logger.info("..........after objectArgs............");
		resultObject.setViewName("ddoinformation");	
		
	}
	catch(Exception ex)
	{
		logger.info("There is some problem in DDOInfo******");
		ex.printStackTrace();
	}
	return resultObject;
}


public ResultObject gettemppost(Map<String, List> objectArgs)
{
	logger.info("...................in DDOServiceImpl temp...................");
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	
	
	
	
	try
	{
		 ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
         DDOInfoDAO ddoinfodao = new DDOInfoDAOImpl(serv.getSessionFactory());       
         List actionList = ddoinfodao.gettempdtls();	            	           
         Map<String, List> map = new HashMap<String, List>();
         map=objectArgs;
         map.put("actionList", actionList);
         logger.info("..........after map in temp............" +map);
         resultObject.setResultCode(ErrorConstants.SUCCESS);
         resultObject.setResultValue(map);
 		logger.info("..........after map in temp............");
         resultObject.setViewName("entryfortemppost");
                 
	}
	catch(Exception e)
	{
		logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
		e.printStackTrace();
		 
		resultObject.setViewName("errorInsert");	
	}

	return resultObject;	
}


public ResultObject getperpost(Map<String, List> objectArgs)
{
	
	logger.info("...................in DDOServiceImpl...................");
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	
	
	
	try
	{
		 ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
         DDOInfoDAO ddoinfodao = new DDOInfoDAOImpl(serv.getSessionFactory());       
         List actionList = ddoinfodao.getperdtls();	     
         logger.info("...................in actionList................... " +actionList);
         Map<String, List> map = new HashMap<String, List>();
         map=objectArgs;
         map.put("actionList", actionList);
         logger.info("...................in map.in per.................. " +map);
         resultObject.setResultCode(ErrorConstants.SUCCESS);
         resultObject.setResultValue(map);
     	logger.info("..........after map in per............");
         resultObject.setViewName("entryforperpost");
                
	}
	catch(Exception e)
	{
		logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
		e.printStackTrace();
        
		resultObject.setViewName("errorInsert");	
	}

	return resultObject;	
}



public ResultObject getpayslip(Map objectArgs)
{
	
	logger.info("...................in DDOServiceImpl getpayslip...................");

	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	
	try{
				
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
		Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
		
		long locId=Long.parseLong(loginMap.get("locationId").toString());
		logger.info("-----------Loc Id is:---------------------->"  + locId);

		DDOInfoDAOImpl ddoinfodao = new DDOInfoDAOImpl(serv.getSessionFactory());  
	
		List DeduceListItr; 
		List deduceList = new ArrayList();	
		List allowList = new ArrayList();	
		double sumdeduce=0.0;		
		DeduceListItr = ddoinfodao.getpaydtls(locId);
		
		for(Iterator itr=DeduceListItr.iterator();itr.hasNext();)
		{    			
			Object[] row = (Object[])itr.next();	
			
			logger.info("-----------Double.parseDouble(row[0].toString())------------------>"  + Double.parseDouble(row[0].toString()));
			logger.info("-----------row[1].toString()------------------>"  +row[1].toString());
			sumdeduce=(sumdeduce+(Double.parseDouble(row[0].toString())));
			CustomDeduction deduction =new CustomDeduction();
			//deduction.setDeductionName(row[1].toString());
			deduction.setAmount(Double.parseDouble(row[0].toString()));
			
			deduceList.add(deduction);
			
		}
		
		
		List allowanceListItr; 
		
		allowanceListItr = ddoinfodao.getpaydtls1(locId);
		double sumallow=0.0;	
		for(Iterator itr=allowanceListItr.iterator();itr.hasNext();)
		{    			
			Object[] row = (Object[])itr.next();
			
			logger.info("----------Double.parseDouble(row[0].toString())------------------>"  + Double.parseDouble(row[0].toString()));
			logger.info("-----------row[1].toString()------------------>"  +row[1].toString());
			sumallow=(sumallow+(Double.parseDouble(row[0].toString())));
			CustomAllowance allowance = new CustomAllowance();
			allowance.setAllowanceName(row[1].toString());
			allowance.setAmount(Double.parseDouble(row[0].toString()));
			
			allowList.add(allowance);
			
		}   
		 
      
		logger.info(" DeduceList::::::::::::::::::::" + deduceList);
		logger.info(" allowList:::::::::::::::::::" + allowList);
		
		logger.info(" DeduceList::::::::::::::::::::" + deduceList);
		logger.info(" allowList:::::::::::::::::::" + allowList);
		
		float totalsum = (float) (sumallow+sumdeduce);
		logger.info(" totalsum:::::::::::::::::::" + totalsum);
		
		objectArgs.put("deduceList", deduceList);
		objectArgs.put("allowList",allowList);
		objectArgs.put("sumdeduce", sumdeduce);
		objectArgs.put("sumallow",sumallow);
		objectArgs.put("totalsum",totalsum);
		
		resObj.setViewName("GeneratePaySlip");
		resObj.setResultCode(0);
		resObj.setResultValue(objectArgs);
	} catch (Exception ex) {
		resObj.setThrowable(ex);
		logger.error("Token Number Screen Showing Error", ex);
		resObj.setResultCode(-1);
	}
	return resObj;
}


public ResultObject getcommondtls(Map objectArgs)
{
	
	logger.info("...................in DDOServiceImpl...................");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	
	
	
	try
	{
		Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		long postId=Long.parseLong(loginMap.get("primaryPostId").toString());
		logger.info("-----------postId is:---------------------->"  + postId);

		DDOInfoDAOImpl ddoinfodao = new DDOInfoDAOImpl(serv.getSessionFactory());  
         List actionListItr = ddoinfodao.getcommon(postId);	     
         logger.info("...................in actionList................... " +actionListItr);
         
         List actionList = new ArrayList();	
         
         
         for(Iterator itr=actionListItr.iterator();itr.hasNext();)
 		{    			
 			Object[] row = (Object[])itr.next();	
 			
 			logger.info("----------row[0].toString()------------------>"  + row[0].toString());
			logger.info("-----------row[1].toString()------------------>"  +row[1].toString());
			logger.info("-----------row[2].toString()------------------>"  +row[2].toString());
			logger.info("-----------row[3].toString()------------------>"  +row[3].toString());
			logger.info("-----------row[4].toString()------------------>"  +row[4].toString());
			logger.info("-----------Double.parseDouble(row[5].toString())------------------>"  +Double.parseDouble(row[5].toString()));
			logger.info("-----------Double.parseDoublerow[6].toString()------------------>"  +Double.parseDouble(row[6].toString()));
			logger.info("-----------row[7].toString()------------------>"  +row[7].toString());
			logger.info("-----------Long.parseDoublerow[8].toString()------------------>"  +Long.parseLong(row[8].toString()));
 			
 		//	a.ddoCode,a.ddoName,b.empFname,b.empMname,b.empLname,b.cadre,d.dsgnCode,e.dsgnName,tr.groupId
 			
 			CustomCommon common1 =new CustomCommon();
 			common1.setDdoCode(row[0].toString());
 			common1.setDdoName(row[1].toString());
 			common1.setEmpFname(row[2].toString());
 			common1.setEmpMname(row[3].toString());
 			common1.setEmpLname(row[4].toString());
 			common1.setCadre(Double.parseDouble(row[5].toString()));
 			common1.setDsgnCode(Double.parseDouble(row[6].toString()));
 			common1.setDsgnName(row[7].toString());
 			common1.setGroupId(Long.parseLong(row[8].toString()));
 			
 			actionList.add(common1);
 			
 		}

         objectArgs.put("actionList", actionList);
         logger.info("...................in objectArgs.in per.................. " +objectArgs);
        
     	logger.info("..........after objectArgs in per............");
         	
 		resObj.setViewName("common");
 		resObj.setResultCode(0);
 		resObj.setResultValue(objectArgs);
 		
 	} catch (Exception ex) {
 		resObj.setThrowable(ex);
 		logger.error("Token Number Screen Showing Error", ex);
 		resObj.setResultCode(-1);
 	}
 	return resObj;
}



}




