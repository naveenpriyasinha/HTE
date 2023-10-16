package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.DesigMasterDAO;
import com.tcs.sgv.eis.dao.GradeMasterDAO;
import com.tcs.sgv.eis.dao.getGradDesgMapDAO;
import com.tcs.sgv.eis.util.HrEisGdMpgServiceImplHelper;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class HrEisGdMpgServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());
	int msg=0;
	public ResultObject getGradDesgMap(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("**********getGradDesgMap**************");
		try
		{
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            getGradDesgMapDAO getgradDesgMapDAO = new getGradDesgMapDAO(HrEisGdMpg.class,serv.getSessionFactory());
	            GradeMasterDAO gradeMasterDAO = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
	            DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory());
	    	    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
	            HttpSession session=request.getSession();		
	            Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
	            
	            List<HrEisGdMpg>  resultSet = getgradDesgMapDAO.getAllMasterData();
	            
	            List<HrEisGdMpg> gdMpgList = new ArrayList();
	            for (int i=0;i<resultSet.size();i++)
                {
	            	HrEisGdMpg hrEisGdMpg=new HrEisGdMpg();
	            	hrEisGdMpg=resultSet.get(i);
	            	HrEisGdMpg hrEisGdMpggu=new HrEisGdMpg();
                	//gdMpgList.add(resultSet.get(i));
    				String name=hrEisGdMpg.getOrgGradeMst().getGradeName()+" "+hrEisGdMpg.getOrgDesignationMst().getDsgnName();
        			hrEisGdMpggu.setGdMapId(hrEisGdMpg.getGdMapId());
                	if(langId!=1)
            		{
            			String gradeElementCode= resultSet.get(i).getOrgGradeMst().getGradeCode();
            			
            			String desigElementCode= resultSet.get(i).getOrgDesignationMst().getDsgnCode();
            			
            			
            			List<OrgGradeMst> grade = gradeMasterDAO.getGradeName(gradeElementCode, langId);
            			List<OrgDesignationMst> desig=desigMasterDAO.getDesigName(desigElementCode, langId);
            			if(desig.size()>0)
            			{
                			hrEisGdMpggu.setOrgDesignationMst(desig.get(0));
            			}
            			else
            			{
            				hrEisGdMpggu.setOrgDesignationMst(hrEisGdMpg.getOrgDesignationMst());
            			}
            			if(grade.size()>0)
            			{
            				hrEisGdMpggu.setOrgGradeMst(grade.get(0));
            			}
            			else
            			{
            				hrEisGdMpggu.setOrgGradeMst(hrEisGdMpg.getOrgGradeMst());
            			}
            			
            			
            			
            		}
            		else
            		{
            			hrEisGdMpggu.setOrgGradeMst(hrEisGdMpg.getOrgGradeMst());
            			hrEisGdMpggu.setOrgDesignationMst(hrEisGdMpg.getOrgDesignationMst());
        			}
            		
                	gdMpgList.add(hrEisGdMpggu);			
                				
                }
	            
	            
	            
	            
	            
	            
	            objectArgs.put("resultSet", gdMpgList);
	            
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            
	            resultObject.setResultValue(objectArgs);
	    
	            resultObject.setViewName("getGradDesgMap");
	           
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
		}
	
		return resultObject;
	}
	public ResultObject GradeDesignationMaster(Map objectArgs)
	{
		logger.info("**********GradeDesignationMaster**************");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String editflag="";
		long GdMapId;
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			    HttpSession session=request.getSession();		

			    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
			    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            getGradDesgMapDAO getgradDesgMapDAO = new getGradDesgMapDAO(HrEisGdMpg.class,serv.getSessionFactory());
			    GradeMasterDAO gradeMasterDAO = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
			    DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory());
	            Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
				List graderesultSet = gradeMasterDAO.getAllGradeMasterData(cmnLanguageMst);
				List desigresultSet = desigMasterDAO.getAllDesgMasterData(langId);
				HrEisGdMpg graddesgresultSet = new HrEisGdMpg();
				  Map voToService = (Map)objectArgs.get("voToServiceMap");
				  editflag=voToService.get("edit")!=null?(String)voToService.get("edit"):"";
				if( editflag!=null && !editflag.equals(""))
				{
				
					GdMapId = voToService.get("GdMapId")!=null?Long.parseLong((String)voToService.get("GdMapId")):0;
				graddesgresultSet=(HrEisGdMpg)getgradDesgMapDAO.getGradeDesgMasterData(GdMapId);
				}
				Map map = new HashMap();
	            map.put("graderesultSet", graderesultSet);
	            map.put("desigresultSet", desigresultSet);
	            map.put("graddesgresultSet", graddesgresultSet);
	            map.put("result", "success");
	            resultObject.setResultValue(map);
	            if(!editflag.equals("Y"))
	            resultObject.setViewName("ADDGradDesgMst");
	            else
		        resultObject.setViewName("EditGradDesgMst");
	            
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
		}
	
		return resultObject;
	}
	public ResultObject AddGradeDesignationmpg(Map objectArgs)
	{
		logger.info("**********AddGradeDesignationmpg**************");
		ResultObject  resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	
		HrEisGdMpg hrEisGdMpg=	(HrEisGdMpg)objectArgs.get("hrEisGdMpg");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String updateflag="";
        Map map = new HashMap();
        int result=1;
        try
		{
    		HttpSession session=request.getSession();		
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
			 
			 GradeMasterDAO gradeMasterDAO = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
			updateflag = (String) objectArgs.get("updateflag");
			
            long  GdMapId =0;
        	GdMapId = (Long) objectArgs.get("GdMapId");
        	
    		getGradDesgMapDAO getgradDesgMapdao=new getGradDesgMapDAO(HrEisGdMpg.class,serv.getSessionFactory());
    		
    		
        	if(!updateflag.equals(""))
			{	
				hrEisGdMpg=getgradDesgMapdao.read(GdMapId);
			}
        	
			Long gradeId = null ;
			Long desigId;
			gradeId = (Long) objectArgs.get("gradeId");			
			desigId = (Long) objectArgs.get("desigId");
			
			List ExistDataList = gradeMasterDAO.getDuplicateData(gradeId, desigId);
			
			logger.info("ExistDataListExistDataListExistDataList size---------->" + ExistDataList.size());
			
			
			logger.info("gradeIdgradeIdgradeId" + gradeId);
			logger.info("desigIddesigIddesigId" + desigId);
            GenericDaoHibernateImpl gImpl = null;            
            OrgDesignationMst hrEisDesigMst=new OrgDesignationMst();
            gImpl = new GenericDaoHibernateImpl(OrgDesignationMst.class);
            gImpl.setSessionFactory(serv.getSessionFactory());
            hrEisDesigMst = (OrgDesignationMst) gImpl.read(desigId);
            
            
            OrgGradeMst hrEisGradeMst=new OrgGradeMst();            
            gImpl = new GenericDaoHibernateImpl(OrgGradeMst.class);
            gImpl.setSessionFactory(serv.getSessionFactory());
            hrEisGradeMst = (OrgGradeMst) gImpl.read(gradeId);
            logger.info("hrEisGradeMsthrEisGradeMst-------->" + hrEisGradeMst.getGradeId());
            logger.info("hrEisGradeMst.getGradeCode()----->" + hrEisGradeMst.getGradeCode());
            logger.info("hrEisDesigMst.getDsgnId()----->" + hrEisDesigMst.getDsgnId());
            
            
            
		   
		    DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory());
		    HrEisGdMpgServiceImplHelper helper = new HrEisGdMpgServiceImplHelper(serv);
		    logger.info("langIdlangId----->" + langId);
		    
			if(langId!=1)
			{
				long langID=1;
				List<OrgGradeMst> grade = gradeMasterDAO.getGradeName(hrEisGradeMst.getGradeCode(), langID);
				logger.info("grade.size()grade.size()---->" +grade.size());
				logger.info("hrEisDesigMst.getDsgnCode()------>" + hrEisDesigMst.getDsgnCode());
    			List<OrgDesignationMst> desig=desigMasterDAO.getDesigName(hrEisDesigMst.getDsgnCode(), langID);
			    if(grade.size()>0)
		            hrEisGdMpg.setOrgGradeMst(grade.get(0));
			    else
		            hrEisGdMpg.setOrgGradeMst(hrEisGradeMst);
			    if(desig.size()>0)
			    hrEisGdMpg.setOrgDesignationMst(desig.get(0));
			    else
		            hrEisGdMpg.setOrgDesignationMst(hrEisDesigMst);
    			logger.info(hrEisGdMpg.getOrgDesignationMst().getCmnLanguageMst().getLangId()+"**********"+hrEisGdMpg.getOrgGradeMst().getGradeId()+"***********"+hrEisGdMpg.getOrgDesignationMst().getDsgnId());
			}
			else
			{	
            hrEisGdMpg.setOrgGradeMst(hrEisGradeMst);
            hrEisGdMpg.setOrgDesignationMst(hrEisDesigMst);
			}
			
            hrEisGdMpg.setCmnDatabaseMst(cmnDatabaseMst);
            Date lstrDate =new Date();
            hrEisGdMpg.setUpdatedDate(lstrDate);
            hrEisGdMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
            hrEisGdMpg.setOrgUserMstByUpdatedBy(orgUserMst);
		    {
				GenericDaoHibernateImpl<HrEisGdMpg, Long> daoFortrial = new GenericDaoHibernateImpl<HrEisGdMpg, Long>(HrEisGdMpg.class);
			daoFortrial.setSessionFactory(serv.getSessionFactory());				
            long GdMapID=0;
           
            if(updateflag.equals("true"))
            {
	            logger.info("********************Inside Update");
	            helper.updateGradeDesignationMpg(hrEisGdMpg, postId, userId, GdMapID);
	            objectArgs.put("msg", "Record Updated Successfully");
	            msg=1;
            }
            else
            {
            	
            	logger.info("INSIDE CREATE insertBankMasterDtls ********************************* "+objectArgs);
            	logger.info("ExistDataList.size 22222222222------>" +ExistDataList.size());
            	
            	if(ExistDataList.size()>0)
            	{
            		logger.info("ExistDataList.size 3333333333------>" +ExistDataList.size());
            		objectArgs.put("msg", "Mapping Already Existed");
            	}
            	else
            	{
            	helper.insertGradeDesignationMpg(hrEisGdMpg, postId, userId, langId, locId);
            	
            	logger.info("else parttt--------->>>>>>>>>>");
            	objectArgs.put("msg", "Record Inserted Successfully");
            	}
            }
				
		}
        
        
		objectArgs.put("result","success");
		
		//added by Ankit Bhatt for "redirectMap"
		
		Map redirectMap = new HashMap();
		redirectMap.put("actionFlag", "getGradDesgMap");
		if(msg==1)
			objectArgs.put("MESSAGECODE",300006);
		else
			//objectArgs.put("MESSAGECODE",300005);
		objectArgs.put("redirectMap", redirectMap);			
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		
		resultObject.setResultValue(objectArgs);
		//resultObject.setViewName("redirect view");
		if(msg==1)
			resultObject.setViewName("EditGradDesgMst");
		else
			resultObject.setViewName("ADDGradDesgMst");
		
		}
		/*catch(ConstraintViolationException c)
		{
			logger.info("Mapping Already Exists");
			objectArgs.put("result","Mapping Already Exists");
			resultObject=getGradDesgMap(objectArgs);
		}*/
		    
		  
		catch(Exception e)
		{
			resultObject=getGradDesgMap(objectArgs);
			objectArgs.put("result",result);
			logger.info("this Mapping Already Exists ");
		}
		return resultObject;
		
		
	}
	
	
}
