package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.hod.common.valueobject.CmnOrganizationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisPreEmplDtl;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;



public class EmpPreEmplDtlsVOGENImpl extends ServiceImpl
{
	private static final Log logger = LogFactory.getLog(EmpPreEmplDtlsVOGENImpl.class);
	
	public ResultObject generatePreEmplMap(Map objServiceArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			try 
			{
				if (objRes == null || objServiceArgs == null)
				{
					objRes.setResultCode(-1);
					return objRes;
				}
				
				long lngProfessionId=0;
				
				if (request.getParameter("hdnProfessionId") != null && !StringUtility.getParameter("hdnProfessionId", request).equals(""))
				{
					lngProfessionId =  Long.parseLong(StringUtility.getParameter("hdnProfessionId", request));
				}
				
				long lOrganizationId = 0;
				
				if (request.getParameter("hdnOrganizationId") != null && !StringUtility.getParameter("hdnOrganizationId", request).equals(""))
				{	
					lOrganizationId =  Long.parseLong(StringUtility.getParameter("hdnOrganizationId", request));
				}
				
				HrEisPreEmplDtl objHrEisEmpPreEmplDtls=new HrEisPreEmplDtl();
				
				objHrEisEmpPreEmplDtls.setEmpPreemplPk(lngProfessionId);
				String strOrgName=request.getParameter("txtOrgNm") != null ? StringUtility.getParameter("txtOrgNm", request) : "";
				String strTypeOfOrg = request.getParameter("drodnTypOfOrg")!= null ? StringUtility.getParameter("drodnTypOfOrg", request):"";
				Date dtDtOfJoin =request.getParameter("dtDtOfJoin")!= null ? StringUtility.convertStringToDate(StringUtility.getParameter("dtDtOfJoin", request)):null;
				Date dtDtOfRelvng =request.getParameter("dtDtOfRelevng")!= null ? StringUtility.convertStringToDate(StringUtility.getParameter("dtDtOfRelevng", request)):null;
				String strDsgAtTimeFRel=request.getParameter("txtDsgAtTimeOfRelvng") != null ? StringUtility.getParameter("txtDsgAtTimeOfRelvng", request) : "";
				String strJobProfile=request.getParameter("txtareaJobProfCarrerSkls") != null ? StringUtility.getParameter("txtareaJobProfCarrerSkls", request) : "";
				String strRemarks= request.getParameter("txtareaRemarks") != null ? StringUtility.getParameter("txtareaRemarks", request):"";
				
				/** change starts by sunil on 31/05/08*/

				String strIsCountinue = request.getParameter("IsContinue")!= null ? StringUtility.getParameter("IsContinue", request):null;
				
				Long lngDurationYears=(request.getParameter("txtDurationYrs") != null && !request.getParameter("txtDurationYrs").equals(""))? StringUtility.convertToLong(StringUtility.getParameter("txtDurationYrs", request)) : null;
				Long lngDurationMonths=(request.getParameter("txtDurationMonths") != null && !request.getParameter("txtDurationMonths").equals(""))? StringUtility.convertToLong(StringUtility.getParameter("txtDurationMonths", request)) :null;
				Long lngDurationDays=(request.getParameter("txtDurationDays") != null && !request.getParameter("txtDurationDays").equals(""))? StringUtility.convertToLong(StringUtility.getParameter("txtDurationDays", request)): null;
				
				CmnLookupMst cmnLookupMstByIsContinue=new CmnLookupMst();
				cmnLookupMstByIsContinue.setLookupName(strIsCountinue);
				objHrEisEmpPreEmplDtls.setCmnLookupMstByIsContinue(cmnLookupMstByIsContinue);

				objHrEisEmpPreEmplDtls.setDurationYears(lngDurationYears);
				objHrEisEmpPreEmplDtls.setDurationMonths(lngDurationMonths);
				objHrEisEmpPreEmplDtls.setDurationDays(lngDurationDays);

				/** change Ends by sunil on 31/05/08*/	
				
				CmnLookupMst cmnLookupMstByTypeOfOrg=new CmnLookupMst();
				cmnLookupMstByTypeOfOrg.setLookupName(strTypeOfOrg);
				
				CmnOrganizationMst cmnOrganizationMstOrg=new CmnOrganizationMst();
				//start to save address in db
				ServiceLocator servLoc = (ServiceLocator) objServiceArgs.get("serviceLocator");	
				FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, servLoc.getSessionFactory());
				FrmServiceMst servDetails = servDetailsImpl.readService("ADDRESS_VOGENERATOR");				
				objRes=servLoc.executeService(servDetails, objServiceArgs);
				
				if(objServiceArgs.containsKey("preEmployerAddress"))
				{	
					CmnAddressMst cmnAddressMst=(CmnAddressMst)objServiceArgs.get("preEmployerAddress");
					cmnOrganizationMstOrg.setCmnAddressMst(cmnAddressMst);		
				}
				//end of address save in db
				cmnOrganizationMstOrg.setOrganizationId(lOrganizationId);
				cmnOrganizationMstOrg.setOrganizationName(strOrgName);
				cmnOrganizationMstOrg.setCmnLookupMst(cmnLookupMstByTypeOfOrg);
				cmnOrganizationMstOrg.setOrganizationShortName(strOrgName);
					
				objHrEisEmpPreEmplDtls.setCmnOrganizationMst(cmnOrganizationMstOrg);
				
				objHrEisEmpPreEmplDtls.setDateOfJoining(dtDtOfJoin);
				objHrEisEmpPreEmplDtls.setDateOfReleving(dtDtOfRelvng);
				objHrEisEmpPreEmplDtls.setDesignation(strDsgAtTimeFRel);
				objHrEisEmpPreEmplDtls.setJobProfile(strJobProfile);
				objHrEisEmpPreEmplDtls.setRemarks(strRemarks);

				String xmlFileId = FileUtility.voToXmlFileByXStream(objHrEisEmpPreEmplDtls);
				
				logger.info("==========objHrEisEmpPreEmplDtls xmlFileId=========="+xmlFileId);
				
				objServiceArgs.put("ajaxKey", xmlFileId);
				objRes.setViewName("ajaxData");
				objRes.setResultValue(objServiceArgs);
				}	
			catch (Exception e) 
			{
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setResultValue(objServiceArgs);
				logger.error("Exception while Calling generatePreEmplMap method in EmpPreEmplDetlsVOGENImpl Service",e);
			}
		}
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while Calling generatePreEmplMap method in EmpPreEmplDetlsVOGENImpl Service",e);
		}
		return objRes;
	}
	
	
	
	public ResultObject SaveInDBEmpPreEmplDtlsVO(Map objServiceArgs)
	 {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			
			boolean blnWorkFlowEnabled = true;
			
			if (request.getParameter("workFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(StringUtility.getParameter("workFlowEnabled",request));
			}
			
			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;//for multiple users
			
			String[] txnPreEmplXML = StringUtility.getParameterValues("encXMLPreEmpl", request);
			List<HrEisPreEmplDtl> lstHrEisEmpPreEmplDtlsVO=FileUtility.xmlFilesToVoByXStream(txnPreEmplXML);
			
		
			String updateRecordXMLFileA[] = StringUtility.getParameterValues("encXMLPreviousEmpl", request);
			Map vOMapPreEmpl = FileUtility.getUpdatedDeletedVOListFromXML(updateRecordXMLFileA);
			
			List updatedPreEmplVOList = (List) vOMapPreEmpl.get("upDatedVOList");
			List deletedPreEmplVOList = (List) vOMapPreEmpl.get("deletedVOList");
			List notModifiedPreEmplVOList = (List) vOMapPreEmpl.get("notModifiedVOList");
			
			objServiceArgs.put("updatedPreEmplVOList", updatedPreEmplVOList);
			objServiceArgs.put("deletedPreEmplVOList", deletedPreEmplVOList);
			objServiceArgs.put("notModifiedPreEmplVOList", notModifiedPreEmplVOList);
			
			
			objServiceArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled);
			objServiceArgs.put("userId", iUserId);//for multiple users
			objServiceArgs.put("lstHrEisEmpPreEmplDtlsVO", lstHrEisEmpPreEmplDtlsVO);
			objServiceArgs.put("encXMLPreEmpl", txnPreEmplXML);
			objRes.setResultValue(objServiceArgs);
		}
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while Calling SaveInDBEmpPreEmplDtlsVO method in EmpPreEmplDetlsVOGENImpl Service",e);
		}
		return objRes;
	 }
}	
