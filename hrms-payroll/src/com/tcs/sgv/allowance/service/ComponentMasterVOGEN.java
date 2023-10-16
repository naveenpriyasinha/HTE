package com.tcs.sgv.allowance.service;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

import com.tcs.sgv.allowance.valueobject.HrPayComponentMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class ComponentMasterVOGEN extends ServiceImpl{

	Log logger = LogFactory.getLog(getClass());
	public ResultObject getComponentData(Map objectArgs) 
	{
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		
		
		
		try{
		logger.info("ComponentMasterVOGEN getComponentData Called");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpSession session=request.getSession();
		
		
		HrPayComponentMst componentMstVO = new HrPayComponentMst();
		
		String component_desc="";
		String component_exp="";
		
		
		component_desc = StringUtility.getParameter("txt_component_desc", request);
		component_exp = StringUtility.getParameter("component_exp", request);
		
		
		
		LoginDetails loginDetails=(LoginDetails)session.getAttribute("loginDetails");
		long langId=loginDetails.getLangId();
		
        
		Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
		
		long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
		
		long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
		
		long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
     
       
        langId=Long.parseLong(loginDetailsMap.get("langId").toString());
        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
		
		
		
		 long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
		 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
		 
		 Date lstrDate =new Date();
		
		
		
		 componentMstVO.setComponentDesc(component_desc);
		
		 componentMstVO.setExpression(component_exp);
		
		 componentMstVO.setCmnDatabaseMst(cmnDatabaseMst);
	
		 componentMstVO.setCmnLanguageMst(cmnLanguageMst);
		
		 componentMstVO.setCmnLocationMst(cmnLocationMst);
		
		 componentMstVO.setCreatedDate(lstrDate);
		
		 componentMstVO.setOrgPostMstByCreatedByPost(orgPostMst);
	
		 componentMstVO.setOrgPostMstByUpdatedByPost(orgPostMst);
		
		 componentMstVO.setOrgUserMstByCreatedBy(orgUserMst);
		
		 componentMstVO.setOrgUserMstByUpdatedBy(orgUserMst);
		
		 componentMstVO.setUpdatedDate(lstrDate);
		
		 
			
		
		logger.info(" ****************************componentMstVO " + componentMstVO);
		
		objectArgs.put("componentMst",componentMstVO);
		
		
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		
		}
		catch(Exception e){
			retObj.setResultCode(ErrorConstants.ERROR);
			e.printStackTrace();
			return retObj;
			
		}
		return retObj;
		
	}

	
}
