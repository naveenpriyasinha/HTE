package com.tcs.sgv.eis.employeeInfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl_WF;
import com.tcs.sgv.eis.dao.EmpInfoDAO_WF;
import com.tcs.sgv.eis.employeeInfo.dao.GeneralEmpInfoDaoImpl;
import com.tcs.sgv.eis.employeeInfo.valueobject.GeneralEmpInfoVO;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModEmpRlt;
import com.tcs.sgv.eis.valueobject.HrEisEmpMstHst;
import com.tcs.sgv.ess.dao.EmpDAO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgDesignationMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpInfo;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.hod.common.dao.AddressDAO;
import com.tcs.sgv.hod.common.dao.AddressDAOImpl;
import com.tcs.sgv.hod.common.service.AddressServiceImpl;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

public class GeneralEmpInfo extends ServiceImpl{
	
	private static final Log logger = LogFactory.getLog(GeneralEmpInfo.class);
	
	
	public ResultObject ShowEmpDetail(Map objectArgs) {

	   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	   
	    /* login code */
	 Map   loginMap = (Map) objectArgs.get("baseLoginMap");
			
	 		 long userID = 0;
	 		 long langId = Long.parseLong(loginMap.get("langId").toString());
			 String stringAddress=null;
			 
	   	try
	    {
	   		/*divyesh code*/
	        if (objRes != null && objectArgs != null)
	        {
	        	if (objectArgs.get("EmpInfo_userId") != null)
	        	{
	        		userID = Long.parseLong(objectArgs.get("EmpInfo_userId").toString());
	        		objectArgs.remove("EmpInfo_userId");
	        	}
	        	else	
	        	{
	        		userID = Long.parseLong(loginMap.get("userId").toString());
	        	}
	        	
	        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	            GeneralEmpInfoDaoImpl GemEmpinfo = new GeneralEmpInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
	        	
	            GeneralEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(userID,langId);
                        
                AddressServiceImpl addressServiceImpl = new AddressServiceImpl();  
         	    AddressDAO addressDAO = new AddressDAOImpl(CmnAddressMst.class,serv.getSessionFactory());
         	            
         	    if(EmpDetailsVO != null)
         	    {
	         	   if(EmpDetailsVO.getAddressId()!=0)
	         	   {
	         		   CmnAddressMst cmnAddressMst =  addressDAO.read(EmpDetailsVO.getAddressId());
	            	   
	         		   try
	              	   {
	         			   stringAddress = addressServiceImpl.getAddressDescription(cmnAddressMst,objectArgs);
	            	        	 
	            	       logger.info("========stringAddress========"+ stringAddress);
	            	        	 
			         	   }
						catch(Exception e)
						{
							logger.info("========stringAddress========"+ e);
						}
    	        	
	         	    }else
	         	    {
		             	 stringAddress="-";
		            }
     	            /*this code is for divyesh requerment*/
     	           /*if (objectArgs.get("EmpInfo_PayFix") != null)
       	        	{
       	        		boolean payFix = Boolean.parseBoolean(objectArgs.get("EmpInfo_PayFix").toString());
       	        		if(payFix){
       	        		HrPayfixMst hrPayfixMst= GemEmpinfo.getPayDtlsofEmp(userID);
       	        		if(hrPayfixMst!=null){
       	        		EmpDetailsVO.setSalary(hrPayfixMst.getRevPay());
       	        		EmpDetailsVO.setScaleStart(hrPayfixMst.getRevPayScale().getScaleStartAmt());
       	        		EmpDetailsVO.setScaleInc(hrPayfixMst.getRevPayScale().getScaleIncrAmt());
       	        		EmpDetailsVO.setScaleEnd(hrPayfixMst.getRevPayScale().getScaleEndAmt());
       	        		}
       	        		
       	        		}
       	        		objectArgs.remove("EmpInfo_PayFix");
       	        	}*/
 	        	
     	           
             	objRes.setResultCode(ErrorConstants.SUCCESS);
             	objectArgs.put("EmpDet", EmpDetailsVO);
             	
             	objectArgs.put("address", stringAddress);
             	}else{
             		
             		logger.info("EmpDetailsVO is Empty ");
             	}
 	            
                logger.info("The objectArgs is : "+objectArgs);
                objRes.setResultValue(objectArgs);
                logger.info("View Name ");
               objRes.setViewName("EmployeeInfoDtl");
	                 
	            }
	        
	    }
		      		  
		      		  catch (Exception e)
			            {
			                
		      			  logger.info("\n Error occur in insertion of Leave details"+e);
			                 Map result = new HashMap();
			                 objRes.setResultValue(result);
			                 objRes.setThrowable(e);
			                 objRes.setResultCode(ErrorConstants.ERROR);
			                 objRes.setThrowable(e);
			                 objRes.setViewName("errorPage");
			            }

		      		return objRes;
		     	  
	            }
	
	public ResultObject ShowInboxEmpDetail(Map objectArgs)
	{
		Long modId = 0l;
		Long requestId= 0l;
		Long forUserId = 0l;
		
		HrModEmpRlt hrModEmpRlt = null;
		OrgDesignationMst designationMst = null;
		GeneralEmpInfoVO empInfoVO = new GeneralEmpInfoVO();
		OrgEmpInfo orgEmpInfo =null;
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
			
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		long langId = Long.parseLong(loginMap.get("langId").toString());
		try 
		{
			if (objRes != null && objectArgs != null)
			{
				modId = (Long) objectArgs.get("modId");
				requestId = (Long) objectArgs.get("requestId");
				forUserId = (Long) objectArgs.get("forUserId");
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
				
				GeneralEmpInfoDaoImpl GemEmpinfo = new GeneralEmpInfoDaoImpl(OrgEmpMst.class, serv.getSessionFactory());
				OrgDesignationMstDaoImpl designationMstDaoImpl = new OrgDesignationMstDaoImpl(OrgDesignationMst.class,serv.getSessionFactory());
				OrgPostDetailsRltDaoImpl orgPostMstDaoImpl = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
				
				hrModEmpRlt =GemEmpinfo.getEmpData(modId, requestId, forUserId);
				
				if(hrModEmpRlt != null)
				{
					/* Set - Pay Scale */
					if (hrModEmpRlt.getScaleId() != null)
					{
						empInfoVO.setScaleStart(hrModEmpRlt.getScaleId().getScaleStartAmt());
						empInfoVO.setScaleInc(hrModEmpRlt.getScaleId().getScaleIncrAmt());
						empInfoVO.setScaleEnd(hrModEmpRlt.getScaleId().getScaleEndAmt());
						empInfoVO.setScaleId(hrModEmpRlt.getScaleId().getScaleId());
					}

					/* Set - Basic Pay */
					empInfoVO.setSalary(hrModEmpRlt.getBasicSal());
					
					/* Set - Employee Name, DOJ */
					EmpDAO empDAO = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
					orgEmpInfo = empDAO.getEmpMstRecordByUserIdandLangIdandCrtdDate(forUserId, langId, hrModEmpRlt.getCreatedDate());
					
					if (orgEmpInfo != null)
					{
						String strEmpFullName = "";
						
						if (orgEmpInfo.getEmpMname() != null && !orgEmpInfo.getEmpMname().equals(""))
						{
							strEmpFullName = orgEmpInfo.getEmpFname()+" "+ orgEmpInfo.getEmpMname()+" "+orgEmpInfo.getEmpLname();
						}
						else
						{
							strEmpFullName =  orgEmpInfo.getEmpFname()+" "+orgEmpInfo.getEmpLname();
						}
						
						empInfoVO.setEmpName(strEmpFullName);
						empInfoVO.setDob(orgEmpInfo.getEmpDob());
						empInfoVO.setDoj(orgEmpInfo.getEmpDoj());
						empInfoVO.setDor(orgEmpInfo.getEmpSrvcExp());
						//empInfoVO.setEmpid(orgEmpInfo.getEmp);
					}
					
					/* Set - Post Name */
					OrgPostDetailsRlt orgPostDetailsRlt=orgPostMstDaoImpl.getPostDetailsRltByPostIdAndLangId(hrModEmpRlt.getPostId(), langId);
					
					if (orgPostDetailsRlt != null)
					{
						empInfoVO.setPostId(orgPostDetailsRlt.getOrgPostMst().getPostId());
						empInfoVO.setPostName(orgPostDetailsRlt.getPostName());
					}
					
					/* Set - Designation Name */
					if (hrModEmpRlt.getDsgnId() != null)
					{	
						designationMst=designationMstDaoImpl.getDesignationVOByDsgnCodeAndLangId(hrModEmpRlt.getDsgnId().getDsgnCode(), langId);
						
						if (designationMst != null)
						{
							empInfoVO.setDesigCode(designationMst.getDsgnCode());
							empInfoVO.setDesigid(designationMst.getDsgnId());
							empInfoVO.setDesignation(designationMst.getDsgnName());
						}
					}
					
					/* Set - Employee current address */
					EmpInfoDAO_WF empInfoDAO_WF = new EmpInfoDAOImpl_WF(HrEisEmpMstHst.class,serv.getSessionFactory());
					OrgEmpMst orgEmpMst = null;
					
					List arOrgEmpMst = empInfoDAO_WF.getOrgEmpMstVOList(forUserId,1l);
					if (arOrgEmpMst != null && !arOrgEmpMst.isEmpty())
					{
						orgEmpMst = (OrgEmpMst) arOrgEmpMst.get(0);
					}
					
					if (orgEmpMst != null)
					{
						HrEisEmpMstHst eisEmpMstHst = empInfoDAO_WF.getHrEisEmpHstDataByEmpIdandDate(orgEmpMst.getEmpId(),hrModEmpRlt.getCreatedDate());
						
						if (eisEmpMstHst != null && eisEmpMstHst.getCmnAddressMstByEmpCurrentAddressId() != null)
						{
							empInfoVO.setAddressId(eisEmpMstHst.getCmnAddressMstByEmpCurrentAddressId().getAddressId());
						}
						/*else
						{
							HrEisEmpMst eisEmpMst= empInfoDAO_WF.getHrEisEmpMstDtls(orgEmpMst.getEmpId());
							
							if (eisEmpMst != null && eisEmpMst.getCmnAddressMstByEmpCurrentAddressId() != null)
								empInfoVO.setAddressId(eisEmpMst.getCmnAddressMstByEmpCurrentAddressId().getAddressId());
						}*/
						
						try
					    {
				        	if(empInfoVO !=null && empInfoVO.getAddressId()!=0)
							{
					            AddressServiceImpl addressServiceImpl = new AddressServiceImpl();  
					            AddressDAO addressDAO = new AddressDAOImpl(CmnAddressMst.class,serv.getSessionFactory());
					            CmnAddressMst cmnAddressMst =  addressDAO.read(empInfoVO.getAddressId());
				            	String stringAddress = addressServiceImpl.getAddressDescription(cmnAddressMst,objectArgs);
				            	objectArgs.put("address", stringAddress);
							}
				        	else
				        	{
				        		objectArgs.put("address", "-");
				        	}
						}
						catch(Exception e)
						{
							objectArgs.put("address", "-");
							logger.error("====Error in getting comma separeted address description ======"+e.getMessage());
						}
					}
				}

				objRes.setResultCode(ErrorConstants.SUCCESS);
				objectArgs.put("EmpDet", empInfoVO);
				objectArgs.put("orgEmpInfo", orgEmpInfo);
				objRes.setResultValue(objectArgs);
				objRes.setViewName("EmployeeInfoDtl");
			}
		} catch (Exception e) {
			logger.error("\n Error occur in showEmpDtlOnRequestTime",e);
			Map result = new HashMap();
			objRes.setResultValue(result);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}
}




