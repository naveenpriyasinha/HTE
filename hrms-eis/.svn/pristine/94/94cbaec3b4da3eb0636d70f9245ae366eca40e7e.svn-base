package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.AllowTypeMasterDAO;
import com.tcs.sgv.allowance.dao.AllowTypeMasterDAOImpl;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.CadreMasterDAOImpl;
import com.tcs.sgv.eis.dao.GroupManagementDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisCadreMst;
import com.tcs.sgv.eis.valueobject.HrEisGrpMangMst;
import com.tcs.sgv.ess.dao.OrgGradeMstDao;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;

import common.Logger;

public class CadreMasterServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog( getClass() );
		

public 	ResultObject showCadreMaster(Map objectArgs)
		{
	
	
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);	
	Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
	logger.info("Inside showCadreMaster:::::::::::::::::::::::::: ");
	
	try{
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        resultObject.setResultCode(ErrorConstants.SUCCESS);
        resultObject.setResultValue(objectArgs);
        logger.info("Inside showCadreMaster try block :::::::::::::::::::::::::: ");
        resultObject.setViewName("UpdateCadreMaster");	
        logger.info("Diverting 2 show CadreMaster jsp:::::::::::::::::::::::::: ");
	
        long cadreId = Long.parseLong(StringUtility.getParameter("cadreId", request).toString());
		logger.info("cadreId #########################" + cadreId);
		String edit = StringUtility.getParameter("edit", request).toString();
		List actionList =  new ArrayList();
		
		CadreMasterDAOImpl cadreMasterDAOImpl = new CadreMasterDAOImpl(HrEisCadreMst.class, serv.getSessionFactory());
		HrEisCadreMst hrEisCadreMst = cadreMasterDAOImpl.read(cadreId);
		logger.info(" ClassTypelist.size::" + actionList.size());
		
		/*String cadreName = hrEisCadreMst.get;
		String cadreCode = actionList.get(1).toString();
		String cadreDesc = actionList.get(11).toString();*/
		
		logger.info("cadreName:::::::::::::: " + hrEisCadreMst.getCadreName());
		logger.info("cadreCode:::::::::::::: " + hrEisCadreMst.getCadreCode());
		logger.info("cadreDesc:::::::::::::: " + hrEisCadreMst.getCadreDesc());
		logger.info("edit flag:::::::::::::: " + edit);
		
		Map map = new HashMap();
		map = objectArgs;
		map.put("edit", edit);
		map.put("actionList",hrEisCadreMst );
		map.put("cadreId", hrEisCadreMst.getCadreId());
		map.put("cadreName", hrEisCadreMst.getCadreName());
		map.put("cadreCode", hrEisCadreMst.getCadreCode());
		map.put("cadreDesc", hrEisCadreMst.getCadreDesc());
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		resultObject.setResultValue(map);
		resultObject.setViewName("UpdateCadreMaster");
		logger.info("Redirecting 2 JSP#####################################");

		
	
	
	}
	catch(Exception e){
		//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
		logger.error("Error is: "+ e.getMessage());
	}
	return resultObject;
}
	
	
public ResultObject getCadreMasterDtls(Map objectArgs){
		logger.info("Muni is here:::::::::::::::::::::::");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);	
		logger.info("Muni001 is here:::::::::::::::::::::::");
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
		logger.info("Muni002 is here:::::::::::::::::::::::");
		long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
		logger.info("landId::::::::::::::::::::" + langId);
		long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
		logger.info("locId::::::::::::::::::::" + locId);
		logger.info("Muni003 is here:::::::::::::::::::::::");
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			CadreMasterDAOImpl cadreMasterDAOImpl = new CadreMasterDAOImpl(HrEisCadreMst.class,serv.getSessionFactory()); 
			List<HrEisCadreMst> cadreList = cadreMasterDAOImpl.getCadreData();
			/*logger.info("Cadre Name::::::::::::::::::::::: "+cadreList.get(1).getCadreDesc());  
			logger.info("Cadre Name::::::::::::::::::::::: "+cadreList.get(2).getCadreDesc());
			logger.info("Cadre Name::::::::::::::::::::::: "+cadreList.get(3).getCadreDesc());
			logger.info("Cadre Name::::::::::::::::::::::: "+cadreList.get(4).getCadreDesc());
			logger.info("Cadre Name::::::::::::::::::::::: "+cadreList.get(5).getCadreDesc());
			logger.info("Cadre Name::::::::::::::::::::::: "+cadreList.get(0).getCadreDesc());*/
			
            logger.info("The Size of List is:-"+cadreList.size());            
            objectArgs.put("cadreList", cadreList);
            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);
            
            resultObject.setViewName("ViewCadreMaster");			
		}
		catch(Exception e){
			//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}


public 	ResultObject updateCadreMaster(Map objectArgs)
		{


			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);	
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
			logger.info("Inside showCadreMaster:::::::::::::::::::::::::: ");

			try{
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

				HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
				/*resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);*/
				
				long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
		        logger.info("Loc Id is:-->"+ locId); 
		        
				long cadreId = Long.parseLong(objectArgs.get("cadreId").toString());
				
				
				CadreMasterDAOImpl cadreMasterDAOImpl = new CadreMasterDAOImpl(HrEisCadreMst.class,serv.getSessionFactory()); 
				HrEisCadreMst hrEisCadreMst = cadreMasterDAOImpl.read(cadreId);
				hrEisCadreMst.setCadreId(cadreId);
				
				String cadreName = objectArgs.get("cadreName").toString();
				hrEisCadreMst.setCadreName(cadreName);
				
				String cadreCode = objectArgs.get("cadreCode").toString();
				hrEisCadreMst.setCadreCode(cadreCode);
				
				String cadreDesc = objectArgs.get("cadreDesc").toString();	
				hrEisCadreMst.setCadreDesc(cadreDesc);
				
				hrEisCadreMst.setCmnLocationMst(cmnLocationMst);
				

				resultObject= getCadreMasterDtls(objectArgs);
				resultObject.setResultValue("cadreList");
			
				objectArgs.put("resultObject", resultObject);
				
				logger.info("cadreName in update:::::::::::::: " + cadreName);
				logger.info("cadreCode in update:::::::::::::: " + cadreCode);
				logger.info("cadreDesc in update:::::::::::::: " + cadreDesc);

				cadreMasterDAOImpl.update(hrEisCadreMst);
				objectArgs.put("msg", "Record Updated Successfully");
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ViewCadreMaster");
				
				logger.info("End of updateCadreMaster method02:::::::: " +objectArgs.get("msg") );
			}
			
		catch(Exception e){
			logger.info("Exception Ocuures...CadreMaster Service");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg","There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
							}
		return resultObject;
	}
	
	
	
	
	
}
