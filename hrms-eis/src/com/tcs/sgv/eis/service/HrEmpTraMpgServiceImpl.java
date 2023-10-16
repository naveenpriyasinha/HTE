package com.tcs.sgv.eis.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.HrEmpTraMpgDaoImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEmpTraMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class HrEmpTraMpgServiceImpl extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	
	public ResultObject insertHrEmpTraData(Map objectArgs)
	{
		Calendar calTime = Calendar.getInstance();
		logger.info("The Inintial time for the Transport Service is:-"+calTime.getTimeInMillis());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("**********insertHrEmpTraData**************");
		try
		{
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	            	            
	            HrEmpTraMpg hrEmpTraMpg= (HrEmpTraMpg)objectArgs.get("hrEmpTraMpgVo");
	            HrEmpTraMpgDaoImpl hrEmpTraMpgDao = new HrEmpTraMpgDaoImpl(HrEmpTraMpg.class,serv.getSessionFactory());				
	            HrEisOtherDtls hrEisOtherDtls= (HrEisOtherDtls)objectArgs.get("empOtherVO");	            
	            String editModeTraMapping = (String)objectArgs.get("editModeForTraMapping");
	           
	            //logger.info("the value of objectArgs.get(traMpgId) is "+objectArgs.get("traMpgId"));
	            long traMapId = Long.parseLong(objectArgs.get("traMpgId").toString());
	            
	            Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				
	            /*long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);*/
	            long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				OrgUserMst orgUserMst = null;
				if(loginDetailsMap.get("loggedInUserMst")!=null)
				{
					orgUserMst = (OrgUserMst)loginDetailsMap.get("loggedInUserMst");
				}
				else
				{
					OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
					orgUserMst =orgUserMstDaoImpl.read(userId);
					loginDetailsMap.put("loggedInUserMst",orgUserMst);
				}
							
				/*long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
		        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);*/
				long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				CmnDatabaseMst cmnDatabaseMst = null;
				if(loginDetailsMap.get("loggedInDBMst")!=null)
				{
					cmnDatabaseMst = (CmnDatabaseMst)loginDetailsMap.get("loggedInDBMst");
				}
				else
				{
					CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
					cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
					loginDetailsMap.put("loggedInDBMst",cmnDatabaseMst);
				}
				
						
				/*long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);*/
				
				CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
		     
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long languageId=1;
		        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(languageId);
				
				/*long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);*/
				long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				OrgPostMst orgPostMst = null;
				if(loginDetailsMap.get("primaryPostMst")!=null)
				{
					orgPostMst = (OrgPostMst)loginDetailsMap.get("primaryPostMst");
				}
				else
				{
					OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
					orgPostMst = orgPostMstDaoImpl.read(postId);
					loginDetailsMap.put("primaryPostMst",orgPostMst);
				}
				
				Date sysDate = new Date();
	            
	            if(editModeTraMapping!=null && editModeTraMapping.equalsIgnoreCase("n"))
	            {
		            IdGenerator idGen = new IdGenerator();
					long traId = idGen.PKGenerator("HR_EMP_TRA_MPG", objectArgs);
		            logger.info("the id generated for HR_EMP_TRA_MPG from insertHrEmpTraData of HrEmpTraMpgServiceImpl is "+traId);
		            hrEmpTraMpg.setId(traId);//hardcoded
		            
		            HrEisEmpMst hrEisEmpMst = null;
		            hrEisEmpMst=hrEisOtherDtls.getHrEisEmpMst();	            
		            hrEmpTraMpg.setHrEisEmpMst(hrEisEmpMst);
		            
		            hrEmpTraMpg.setCmnDatabaseMst(cmnDatabaseMst);
		            hrEmpTraMpg.setCmnLocationMst(cmnLocationMst);
		            hrEmpTraMpg.setOrgPostMstByCreatedByPost(orgPostMst);
		            hrEmpTraMpg.setOrgUserMstByCreatedBy(orgUserMst);
		            hrEmpTraMpg.setCreatedDate(sysDate);
		            hrEmpTraMpg.setTrnCounter(new Integer(1));
		            hrEmpTraMpgDao.create(hrEmpTraMpg);
	            
	            }
	            else
	            {
	            	if(traMapId==0)
	            	{
	            		logger.info("in edit block, going to insert the record. as no record is found.");
	            		
	            		IdGenerator idGen = new IdGenerator();
	 					long traId = idGen.PKGenerator("HR_EMP_TRA_MPG", objectArgs);
	 		            logger.info("the id generated for HR_EMP_TRA_MPG from insertHrEmpTraData of HrEmpTraMpgServiceImpl is "+traId);
	 		            hrEmpTraMpg.setId(traId);//hardcoded
	 		            
	 		            HrEisEmpMst hrEisEmpMst = null;
	 		            hrEisEmpMst=hrEisOtherDtls.getHrEisEmpMst();	            
	 		            hrEmpTraMpg.setHrEisEmpMst(hrEisEmpMst);
	 		            
	 		            hrEmpTraMpg.setCmnDatabaseMst(cmnDatabaseMst);
	 		            hrEmpTraMpg.setCmnLocationMst(cmnLocationMst);
	 		            hrEmpTraMpg.setOrgPostMstByCreatedByPost(orgPostMst);
	 		            hrEmpTraMpg.setOrgUserMstByCreatedBy(orgUserMst);
	 		            hrEmpTraMpg.setCreatedDate(sysDate);
	 		            hrEmpTraMpg.setTrnCounter(new Integer(1));
	 		            hrEmpTraMpgDao.create(hrEmpTraMpg);
	            	}
	            	else
	            	{
	            		logger.info("in edit block, going to update the record.");
	            		HrEmpTraMpg hrEmpTraMpgUpdate = hrEmpTraMpgDao.read(traMapId);
		            	
		            	hrEmpTraMpgUpdate.setDistance(hrEmpTraMpg.getDistance());
		            	//hrEmpTraMpgUpdate.setHrEisEmpMst(hrEmpTraMpg.getHrEisEmpMst());
		            	hrEmpTraMpgUpdate.setVehicalAvailDate(hrEmpTraMpg.getVehicalAvailDate());
		            	hrEmpTraMpgUpdate.setVehicalAvailed(hrEmpTraMpg.getVehicalAvailed());
		            	hrEmpTraMpgUpdate.setVehicalLeaveDate(hrEmpTraMpg.getVehicalLeaveDate());
		            	hrEmpTraMpgUpdate.setVehicalNo(hrEmpTraMpg.getVehicalNo());
		            	hrEmpTraMpgUpdate.setVehicalType(hrEmpTraMpg.getVehicalType());
		            	
		            	hrEmpTraMpgUpdate.setUpdatedDate(sysDate);
		            	hrEmpTraMpgUpdate.setOrgPostMstByUpdatedByPost(orgPostMst);
		            	hrEmpTraMpgUpdate.setOrgUserMstByUpdatedBy(orgUserMst);
		            	
		            	hrEmpTraMpgDao.update(hrEmpTraMpgUpdate);
	            	}
	            	
	            }
	            objectArgs.put("resultSet", resultObject);
	            
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            
	            resultObject.setResultValue(objectArgs);
	            
	            
	           //resultObject.setViewName("hrEmpTraMpgMaster");
	            Calendar endCalTime = Calendar.getInstance();
	    		logger.info("The Inintial time for the Transport VOGEN is:-"+endCalTime.getTimeInMillis());
	           
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
		}
	
		return resultObject;
	}
	
	public ResultObject getHrEmpTraData(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("**********getHrEmpTraData**************");
		try
		{
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            	            
	            HrEmpTraMpg hrEmpTraMpg= new HrEmpTraMpg();
	            
	            long empId = (Long)objectArgs.get("empId");
	            
	            HrEmpTraMpgDaoImpl hrEmpTraMpgDao = new HrEmpTraMpgDaoImpl(HrEmpTraMpg.class,serv.getSessionFactory());
				
	            List hrEmpTraMpgList = hrEmpTraMpgDao.getListByColumnAndValue("hrEisEmpMst.empId", empId);
	            
	            if(hrEmpTraMpgList!=null && hrEmpTraMpgList.size()>0)
	            {
	            	hrEmpTraMpg = (HrEmpTraMpg)hrEmpTraMpgList.get(0);
	            }
	            else
	            {
	            	hrEmpTraMpg.setId(0);
	            }
	            
	            objectArgs.put("hrEmpTraMpg", hrEmpTraMpg);
	            
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            
	            resultObject.setResultValue(objectArgs);
	    
	           
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
		}
	
		return resultObject;
	}
}
