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
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrEisInscplcyDtl;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;


public class EmpMiscellenousDtlVOGENImpl extends ServiceImpl
{
	private static final Log logger = LogFactory.getLog(EmpMiscellenousDtlVOGENImpl.class);

	public ResultObject generateEmpMiscBnkMap(Map objServiceArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");

			String strFlag= request.getParameter("flag") != null ? StringUtility.getParameter("flag", request) : "";

			if("InsuranceDtls".equals(strFlag))
			{
				objRes=setInsuranceDtlVO(objServiceArgs);
			}
			else if("BankDtls".equals(strFlag))
			{
				objRes=setBankDtlVO(objServiceArgs);
			}
			else if("PassportDtls".equals(strFlag))
			{
				objRes=setPassportDtlVO(objServiceArgs);
			}
			else if("getBranchData".equals(strFlag))
			{
				String strBankCode	= request.getParameter("BranchValue") != null ? StringUtility.getParameter("BranchValue", request) : "0";
				objServiceArgs.put("strBankCode", strBankCode);
			}
			else if("LicenseDtls".equals(strFlag))
			{
				objRes=setLicenseDtlVO(objServiceArgs);
			}

			objServiceArgs.put("flag", strFlag);
			objRes.setResultValue(objServiceArgs);
		}		
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while Calling generateEmpMiscBnkMap in EmpMiscellenousDtlVOGENImpl Service",e);
		}
		return objRes;
	}

	public ResultObject genEmpMiscBnkMap(Map objServiceArgs)
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

			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;

			//----------for add in DB for insurance details------------------
			String[] txnInsuranceXML = StringUtility.getParameterValues("encXMLIns", request);
			List<HrEisInscplcyDtl> lstHrEisEmpInsrcpolicyDtlsVO = FileUtility.xmlFilesToVoByXStream(txnInsuranceXML);


			String updateRecordXMLFileA[] = StringUtility.getParameterValues("encXMLInsurance", request);
			Map vOMap = FileUtility.getUpdatedDeletedVOListFromXML(updateRecordXMLFileA);

			List updatedVOList = (List) vOMap.get("upDatedVOList");
			List deletedVOList = (List) vOMap.get("deletedVOList");
			List notModifiedVOList = (List) vOMap.get("notModifiedVOList");

			objServiceArgs.put("updatedVOList", updatedVOList);
			objServiceArgs.put("deletedVOList", deletedVOList);
			objServiceArgs.put("notModifiedVOList", notModifiedVOList);

			objServiceArgs.put("lstHrEisEmpInsrcpolicyDtlsVO", lstHrEisEmpInsrcpolicyDtlsVO);


			//		----------for add in DB for bank details------------------
			String[] txnBankXML = StringUtility.getParameterValues("encXMLBnk", request);
			List<HrEisBankDtls> lstHrEisBankDtlsVO = FileUtility.xmlFilesToVoByXStream(txnBankXML);

			String updateBankRecordXMLFileA[] = StringUtility.getParameterValues("encXMLBank", request);
			Map vOMapBank = FileUtility.getUpdatedDeletedVOListFromXML(updateBankRecordXMLFileA);

			List updatedBankVOList = (List) vOMapBank.get("upDatedVOList");
			List deletedBankVOList = (List) vOMapBank.get("deletedVOList");
			List notModifiedBankVOList = (List) vOMapBank.get("notModifiedVOList");

			objServiceArgs.put("updatedBankVOList", updatedBankVOList);
			objServiceArgs.put("deletedBankVOList", deletedBankVOList);
			objServiceArgs.put("notModifiedBankVOList", notModifiedBankVOList);

			objServiceArgs.put("lstHrEisBankDtlsVO", lstHrEisBankDtlsVO);
			logger.info("======lstHrEisBankDtlsVO======="+lstHrEisBankDtlsVO);

			//-------------------------------for add in DB passport details-----------------

			String[] txnPassportXML = StringUtility.getParameterValues("encXMLPasprt", request);
			List<HrEisProofDtl> lstHrEisEmpProofDtlsVO = FileUtility.xmlFilesToVoByXStream(txnPassportXML);

			String updatePassportXMLFileA[] = StringUtility.getParameterValues("encXMLPassport", request);
			Map vOMapPassport = FileUtility.getUpdatedDeletedVOListFromXML(updatePassportXMLFileA);

			List updatedPassportVOList = (List) vOMapPassport.get("upDatedVOList");
			List deletedPassportVOList = (List) vOMapPassport.get("deletedVOList");
			List notModifiedPassportVOList = (List) vOMapPassport.get("notModifiedVOList");

			objServiceArgs.put("updatedPassportVOList", updatedPassportVOList);
			objServiceArgs.put("deletedPassportVOList", deletedPassportVOList);
			objServiceArgs.put("notModifiedPassportVOList", notModifiedPassportVOList);

			objServiceArgs.put("lstHrEisEmpProofDtlsVO", lstHrEisEmpProofDtlsVO);

			logger.info("=========== lstHrEisEmpProofDtlsVO ==========="+lstHrEisEmpProofDtlsVO.size() );



			//Starts----------------------------for add in DB License details-----------------

			String[] txnLicenseXML = StringUtility.getParameterValues("encXMLLicnes", request);
			List<HrEisProofDtl> lstHrEisEmpProofDtls = FileUtility.xmlFilesToVoByXStream(txnLicenseXML);
																			   
			String updateLicenseXMLFileA[] = StringUtility.getParameterValues("encXMLLicense", request);
			Map vOMapLicense = FileUtility.getUpdatedDeletedVOListFromXML(updateLicenseXMLFileA);

			List updatedLicenseVOList = (List) vOMapLicense.get("upDatedVOList");
			List deletedLicenseVOList = (List) vOMapLicense.get("deletedVOList");
			List notModifiedLicenseVOList = (List) vOMapLicense.get("notModifiedVOList");

			objServiceArgs.put("updatedLicenseVOList", updatedLicenseVOList);
			objServiceArgs.put("deletedLicenseVOList", deletedLicenseVOList);
			objServiceArgs.put("notModifiedLicenseVOList", notModifiedLicenseVOList);

			objServiceArgs.put("lstHrEisEmpProofDtls", lstHrEisEmpProofDtls);

			logger.info("=========== lstHrEisEmpProofDtlsVO ==========="+lstHrEisEmpProofDtls.size() );

			//Ends-----------------------------for add in DB License details-----------------


			// --------------------------------for Pan Details--------------------------------------------------
			String strPanNo =  request.getParameter("txtPanNo") != null ? StringUtility.getParameter("txtPanNo", request) : "";

			objServiceArgs.put("strPanNo", strPanNo);
			objServiceArgs.put("userId", iUserId);
			objServiceArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled);

			objRes.setResultValue(objServiceArgs);
		}

		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while Calling genEmpMiscBnkMap method in EmpMiscellenousDtlVOGEN Service",e);
		}
		return objRes;
	}

	private ResultObject setInsuranceDtlVO (Map<String, Object> objServiceArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{		
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");	

			//---------for insurance details----------------	
			long lngInsuranceId=0;
			if (StringUtility.getParameter("hdnInsuranceId", request) != null && !StringUtility.getParameter("hdnInsuranceId", request).equals(""))
			{
				lngInsuranceId =  Long.parseLong(StringUtility.getParameter("hdnInsuranceId", request));
			}

			HrEisInscplcyDtl objDtlsInsrcpolicyDtls = new HrEisInscplcyDtl();
			objDtlsInsrcpolicyDtls.setEmpInsrcpolicyDtlsPk(lngInsuranceId);

			String strPolicyNo =  request.getParameter("txtPolcyNo") != null ? StringUtility.getParameter("txtPolcyNo", request):"";
			String strNameFPolicy =  request.getParameter("txtNameOfPolcy") != null ? StringUtility.getParameter("txtNameOfPolcy", request):"";
			String strCompName = request.getParameter("txtCmpName") != null ? StringUtility.getParameter("txtCmpName", request) : "";
			Date dtDatFPolicy =  request.getParameter("dtDtOfPolcy") != null ? StringUtility.convertStringToDate(StringUtility.getParameter("dtDtOfPolcy", request)): null;
			long lngPolDurationYrs = (request.getParameter("txtDurationYrs") != null && !request.getParameter("txtDurationYrs").equals(""))? StringUtility.convertToLong(StringUtility.getParameter("txtDurationYrs", request)) : 0;
			long lngPolDurationMonths =(request.getParameter("txtDurationMonths") != null && !request.getParameter("txtDurationMonths").equals("")) ? StringUtility.convertToLong(StringUtility.getParameter("txtDurationMonths", request)) : 0;
			long lngInsuredAmt = (request.getParameter("txtInsuredAmt") != null && !request.getParameter("txtInsuredAmt").equals(""))? StringUtility.convertToLong(StringUtility.getParameter("txtInsuredAmt", request)) : 0;

			//setting the values-----------		
			objDtlsInsrcpolicyDtls.setPolicyNumber(strPolicyNo);
			objDtlsInsrcpolicyDtls.setNameOfPolicy(strNameFPolicy);
			objDtlsInsrcpolicyDtls.setInsrcCompanyName(strCompName);
			objDtlsInsrcpolicyDtls.setDateOfPolicy(dtDatFPolicy);
			objDtlsInsrcpolicyDtls.setDurationYear(lngPolDurationYrs);
			objDtlsInsrcpolicyDtls.setDurationMonth(lngPolDurationMonths);
			objDtlsInsrcpolicyDtls.setInsuredAmount(lngInsuredAmt);

			String xmlFileId = FileUtility.voToXmlFileByXStream(objDtlsInsrcpolicyDtls);
			logger.info("=============== xmlFileId in vogen for insurance=========="+xmlFileId);
			objServiceArgs.put("ajaxKey", xmlFileId);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(objServiceArgs);
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while setting Co Curricular Vo in VOGEN ",e);
		}
		return objRes;
	}

	private ResultObject setBankDtlVO (Map<String, Object> objServiceArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{		

			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");		

			//--------for bank details---------------------------
			long lngBankId=0;
			if (StringUtility.getParameter("hdnBankId", request) != null && !StringUtility.getParameter("hdnBankId", request).equals(""))
			{
				lngBankId =  Long.parseLong(StringUtility.getParameter("hdnBankId", request));
			}
			HrEisBankDtls objHrEisBankDtls = new HrEisBankDtls();
			objHrEisBankDtls.setBankDtlId(lngBankId); 

			logger.info("bank name===="+StringUtility.getParameter("drodnBankName", request));
			long lngBranchCode = (request.getParameter("drodnBankName")!= null && !request.getParameter("drodnBankName").equals(""))? Long.parseLong(StringUtility.getParameter("drodnBankName", request)):0;
			logger.info("branch name===="+StringUtility.getParameter("drodnBranchName", request));
			long lBranchTypeCode = request.getParameter("drodnBranchName")!= null && !request.getParameter("drodnBranchName").equals("")  ? Long.parseLong(StringUtility.getParameter("drodnBranchName", request)):0;
			String lngaccountno =(request.getParameter("txtAccNo")!= null && !request.getParameter("txtAccNo").equals(""))? StringUtility.getParameter("txtAccNo", request):"0";
			String stracctype = request.getParameter("drodnTypeOfAcc")!= null ?StringUtility.getParameter("drodnTypeOfAcc", request):"";



			HrEisBankMst HrEisBankMstByBankName=new HrEisBankMst();
			HrEisBankMstByBankName.setBankTypeCode(lngBranchCode);

			HrEisBranchMst HrEisBranchMstByBranchName=new HrEisBranchMst();
			HrEisBranchMstByBranchName.setBranchTypeCode(lBranchTypeCode);


			//CmnLookupMst cmnLookupMstByBankTypeOfAccount=new CmnLookupMst();
			//cmnLookupMstByBankTypeOfAccount.setLookupName(stracctype);
			//objHrEisBankDtls.setCmnLookupMstByBankTypeOfAccount(cmnLookupMstByBankTypeOfAccount);

			/*objHrEisBankDtls.setHrEisBankMst(HrEisBankMstByBankName);
			objHrEisBankDtls.setHrEisBranchMst(HrEisBranchMstByBranchName);*/
			objHrEisBankDtls.setBankAcctNo(lngaccountno);
			//objHrEisBankDtls.setBankAcctType(lngAcctype);

			objServiceArgs.put("objHrEisBankDtls",objHrEisBankDtls);

			objServiceArgs.put("BankAccountType",stracctype);
			/*String xmlFileId = FileUtility.voToXmlFileByXStream(objHrEisBankDtls);

			logger.info("============== xmlFileId in vogen for bank========"+ xmlFileId);
			objServiceArgs.put("ajaxKey", xmlFileId);
			objRes.setViewName("ajaxData");*/

			objRes.setResultValue(objServiceArgs);
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while setting Co Curricular Vo in VOGEN ",e);
		}
		return objRes;
	}

	private ResultObject setPassportDtlVO (Map<String, Object> objServiceArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{		
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");		

			//--------for bank details---------------------------
			long lngPassportId=0;
			if (StringUtility.getParameter("hdnPassportId", request) != null && !StringUtility.getParameter("hdnPassportId", request).equals(""))
			{
				lngPassportId =  Long.parseLong(StringUtility.getParameter("hdnPassportId", request));
			}
			HrEisProofDtl objHrEisEmpProofDtls = new HrEisProofDtl();
			objHrEisEmpProofDtls.setSrNo(lngPassportId);

			String strPassportNo = request.getParameter("txtPasprtNo")!= null ? StringUtility.getParameter("txtPasprtNo", request):"";
			Date dtPassportIssueDt =request.getParameter("dtPasprtIsueDt")!= null ? StringUtility.convertStringToDate(StringUtility.getParameter("dtPasprtIsueDt", request)):null;
			Date dtPassportExpiryDt =request.getParameter("dtPasprtExpDt")!= null ? StringUtility.convertStringToDate(StringUtility.getParameter("dtPasprtExpDt", request)): null;
			String strPlaceOfIssue =request.getParameter("txtPlaceOfIssue")!= null ? StringUtility.getParameter("txtPlaceOfIssue", request):"";
			String strIssuingAuth =request.getParameter("txtIsungAuth")!= null ? StringUtility.getParameter("txtIsungAuth", request):"";

			//setting the values-----------		
			objHrEisEmpProofDtls.setProofNum(strPassportNo);
			objHrEisEmpProofDtls.setIssueDate(dtPassportIssueDt);
			objHrEisEmpProofDtls.setExpiryDate(dtPassportExpiryDt);
			objHrEisEmpProofDtls.setIssuePlace(strPlaceOfIssue);
			objHrEisEmpProofDtls.setIssueAuthority(strIssuingAuth);

			String xmlFileId = FileUtility.voToXmlFileByXStream(objHrEisEmpProofDtls);
			logger.info("============== xmlFileId in vogen for passport========"+ xmlFileId);
			objServiceArgs.put("ajaxKey", xmlFileId);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(objServiceArgs);
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while calling setPassportDtlVO Method in EmpMiscellenousDtlVOGENImpl VOGEN ",e);
		}
		return objRes;
	}

	private ResultObject setLicenseDtlVO (Map<String, Object> objServiceArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{		
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");		

			//--------for bank details---------------------------
			long lngLicenseId=0;
			if (StringUtility.getParameter("hdnLicenseId", request) != null && !StringUtility.getParameter("hdnLicenseId", request).equals(""))
			{
				lngLicenseId =  Long.parseLong(StringUtility.getParameter("hdnLicenseId", request));
			}
			HrEisProofDtl objHrEisEmpProofDtls = new HrEisProofDtl();
			objHrEisEmpProofDtls.setSrNo(lngLicenseId);

			
			String strDrodnLicenseType = (request.getParameter("drodnLicenseType")!= null && !request.getParameter("drodnLicenseType").equals(""))? StringUtility.getParameter("drodnLicenseType", request):"0";
			logger.info("strDrodnLicenseType===="+strDrodnLicenseType);
			String strLicenseNo = request.getParameter("txtLicenseNo")!= null ? StringUtility.getParameter("txtLicenseNo", request):"";
			Date dtLicenseIssueDt =request.getParameter("dtLicenseIsueDt")!= null ? StringUtility.convertStringToDate(StringUtility.getParameter("dtLicenseIsueDt", request)):null;
			Date dtLicenseExpiryDt =request.getParameter("dtLicenseExpDt")!= null ? StringUtility.convertStringToDate(StringUtility.getParameter("dtLicenseExpDt", request)): null;
			String strPlaceOfIssue =request.getParameter("txtPlaceOfIssueLicense")!= null ? StringUtility.getParameter("txtPlaceOfIssueLicense", request):"";
			String strIssuingAuth =request.getParameter("txtLicenseIsungAuth")!= null ? StringUtility.getParameter("txtLicenseIsungAuth", request):"";

			//setting the values-----------		
			objHrEisEmpProofDtls.setProofNum(strLicenseNo);
			objHrEisEmpProofDtls.setIssueDate(dtLicenseIssueDt);
			objHrEisEmpProofDtls.setExpiryDate(dtLicenseExpiryDt);
			objHrEisEmpProofDtls.setIssuePlace(strPlaceOfIssue);
			objHrEisEmpProofDtls.setIssueAuthority(strIssuingAuth);
			
			CmnLookupMst cmnLookupMstByLicenseType=new CmnLookupMst();
			cmnLookupMstByLicenseType.setLookupName(strDrodnLicenseType);
			objHrEisEmpProofDtls.setCmnLookupMstByLicenseType(cmnLookupMstByLicenseType);

			String xmlFileId = FileUtility.voToXmlFileByXStream(objHrEisEmpProofDtls);
			logger.info("============== xmlFileId in vogen for License========"+ xmlFileId);
			objServiceArgs.put("ajaxKey", xmlFileId);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(objServiceArgs);
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while calling setLicenseDtlVO Method in EmpMiscellenousDtlVOGENImpl VOGEN ",e);
		}
		return objRes;
	}

	public ResultObject getLookUpNameAsperId(Map objServiceArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{	HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
		logger.info("inside vogen===========");
		long lngLookUpId = (request.getParameter("argument") != null && !request.getParameter("argument").equals("")) ? Long.parseLong(StringUtility.getParameter("argument",request)) : 0;
		logger.info("===========lngLookUpId in vogen of misc=============="+lngLookUpId);

		objServiceArgs.put("lngLookUpId", lngLookUpId);

		objRes.setResultValue(objServiceArgs);
		}
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while Calling getLookUpNameAsperId method in EmpMiscellenousDtlVOGEN Service",e);
		}
		return objRes;
	}
}


