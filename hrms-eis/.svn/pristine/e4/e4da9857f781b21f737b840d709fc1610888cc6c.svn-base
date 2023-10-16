package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import net.sf.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.AddDeptDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisDeptMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class AddDeptServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());
	
	public ResultObject getAllDeptMasterData(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	    	    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
	            HttpSession session=request.getSession();		
	            Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	            Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
		        
		        AddDeptDAOImpl addDeptDAOImpl = new AddDeptDAOImpl(HrEisDeptMst.class,serv.getSessionFactory());
				StringBuffer data=new StringBuffer();
				Map voToService = (Map)objectArgs.get("voToServiceMap");
                String chk="";
                
                	
                if(voToService!=null&&voToService.get("chk")!=null)
                chk=(String) voToService.get("chk");
				if(chk!=null && chk.equals("1"))
				{					
					String dname = "";
	                if(voToService!=null&&voToService.get("dname")!=null)
					dname=((String) voToService.get("dname")).toLowerCase();
					String editname="";
	                if(voToService!=null&&voToService.get("editname")!=null)
					editname=((String) voToService.get("editname")).toLowerCase();
					logger.info(dname+"********dname*******"+editname) ;           				
            		if(!dname.equals(editname))
            		{
            			
            			List<HrEisDeptMst> actionList = addDeptDAOImpl.getDeptMasterDatabyname(dname);
	            		if(actionList.size()!=0)
						{
	            			
	            			data.append("<deptdetails>");
							data.append("<DeptName>").append("<![CDATA[").append(actionList.get(0).getDeptName()).append("]]>").append("</DeptName>");
							data.append("</deptdetails>");
						}
						else
						{
							
							data.append("<deptdetails>");
							data.append("<DeptName>"+null+"</DeptName>");
							data.append("</deptdetails>");
						}
            		}
            		else
            		{
            			
            			data.append("<deptdetails>");
						data.append("<DeptName>"+null+"</DeptName>");
						data.append("</deptdetails>");
						logger.info("I am in else");
            		}
	            	Map result = new HashMap();
					String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", data.toString()).toString();
					result.put("ajaxKey", stateNameIdStr);
			        resultObject.setResultCode(ErrorConstants.SUCCESS); 
			        resultObject.setResultValue(result);
			        resultObject.setViewName("ajaxData");    
			        logger.info(" the string buffer is :"+stateNameIdStr);
		        }
				else
				{	
				List <HrEisDeptMst> resultSet = addDeptDAOImpl.getAllDeptMasterData(cmnLanguageMst);
	            Map map = new HashMap();
	            map.put("resultSet", resultSet);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(map);
	            resultObject.setViewName("DeptMasterView");
				}
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
		}
	
		return resultObject;
	}
	public ResultObject getDeptData(Map objectArgs)
	{
	    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			    HttpSession session=request.getSession();		

			    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
			    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			    AddDeptDAOImpl addDeptDAOImpl = new AddDeptDAOImpl(HrEisDeptMst.class,serv.getSessionFactory());
	            
				Map voToService = (Map)objectArgs.get("voToServiceMap");
				Long deptId = Long.parseLong( (String) voToService.get("deptId"));
	            Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
				HrEisDeptMst resultSet = addDeptDAOImpl.getDeptMasterData(deptId,cmnLanguageMst.getLangId());
	            Map map = new HashMap();
	            map.put("resultSet", resultSet);
	            map.put("result", "success");
	            resultObject.setResultValue(map);
	            resultObject.setViewName("DeptMasterEdit");
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
		}
	
		return resultObject;
	}
	public ResultObject AddDept(Map objectArgs) throws ConstraintViolationException
	{
		
		ResultObject  resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	
		HrEisDeptMst EisDeptMst=	(HrEisDeptMst)objectArgs.get("AddDept");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String updateflag="";
        Map map = new HashMap();
        try
		{
			logger.info("\n\n\n Method AddDept in class AddDeptServiceImpl start.....");
			HttpSession session=request.getSession();		
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
	        
	       
	        long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			
			 long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
			 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);		
			updateflag = (String)objectArgs.get("updateflag");
		
		if(EisDeptMst!=null)
		{
			GenericDaoHibernateImpl<HrEisDeptMst, Integer> daoFortrial = new GenericDaoHibernateImpl<HrEisDeptMst, Integer>(HrEisDeptMst.class);
			daoFortrial.setSessionFactory(serv.getSessionFactory());				
			long  deptId =0;
            Date lstrDate =new Date();
            EisDeptMst.setCmnLanguageMst(cmnLanguageMst);
            EisDeptMst.setCmnDatabaseMst(cmnDatabaseMst);
            EisDeptMst.setCmnLocationMst(cmnLocationMst);
            
            if(updateflag.equals("true"))
            {
    			long  deptIdupdate =0;
                deptIdupdate = Long.parseLong((String)(objectArgs.get("deptId")));
            	logger.info("********************Inside Update"+deptIdupdate);
	            EisDeptMst.setDeptId(deptIdupdate);
                EisDeptMst.setOrgUserMstByUpdatedBy(orgUserMst);
                EisDeptMst.setUpdatedDate(lstrDate);
                EisDeptMst.setOrgPostMstByUpdatedByPost(orgPostMst);
                daoFortrial.update(EisDeptMst);	
            }
            else
            {
                EisDeptMst.setOrgUserMstByCreatedBy(orgUserMst);
                EisDeptMst.setCreatedDate(lstrDate);
                EisDeptMst.setOrgPostMstByCreatedByPost(orgPostMst);
            	IdGenerator idGen = new IdGenerator();
            	deptId = idGen.PKGeneratorWODBLOC("hr_eis_dept_mst", objectArgs);
            	EisDeptMst.setDeptId(deptId);
				daoFortrial.create(EisDeptMst);
            }
		
		}
		
		AddDeptDAOImpl addDeptDAOImpl=new AddDeptDAOImpl(HrEisDeptMst.class,serv.getSessionFactory());
		List <HrEisDeptMst> resultSet= (ArrayList)addDeptDAOImpl.getAllDeptMasterData();
        map.put("resultSet", resultSet);
		objectArgs.put("result","success");
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		resultObject.setResultValue(objectArgs);
		resultObject.setResultValue(map);
		resultObject.setViewName("DeptMasterView");
		//return resultObject;
		}
		/*catch(ConstraintViolationException c)
		{
			logger.info("Mapping Already Exists");
			objectArgs.put("result","This Department name Already Exists");
			resultObject=getAllDeptMasterData(objectArgs);
		}*/
		catch(Exception e)
		{
			resultObject=getAllDeptMasterData(objectArgs);
			objectArgs.put("result","This Department name Already Exists");
			logger.info("This Department name Already Exists ");
		}
		return resultObject;
	}
}
