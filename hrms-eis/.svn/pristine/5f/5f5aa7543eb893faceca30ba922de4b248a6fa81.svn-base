package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDao;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BankMasterDAO;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.dao.BranchMasterDAO;
import com.tcs.sgv.eis.dao.BranchMasterDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl_WF;
import com.tcs.sgv.eis.dao.EmpInfoDAO_WF;
import com.tcs.sgv.eis.dao.EmpMiscellenousDtlDAO;
import com.tcs.sgv.eis.dao.EmpMiscellenousDtlDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrEisInscplcyDtl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class EmpMiscellenousDtlServiceImpl extends ServiceImpl implements EmpMiscellenousDtlService
{

	private static final Log logger = LogFactory.getLog(EmpMiscellenousDtlServiceImpl.class);

	public ResultObject getBnkDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		boolean blnWorkFlowEnabled = true;

		if (objectArgs.get("blnWorkFlowEnabled") != null)
		{
			blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
		}
		
		logger.info("blnWorkFlowEnabled In Service ===================="+blnWorkFlowEnabled);
		objectArgs.put("blnWorkFlowEnabled",blnWorkFlowEnabled); 

		try 
		{
			//---------------for insurance-----------------------------------
			objRes=saveInsuranceDtlInDB(objectArgs,blnWorkFlowEnabled);
			//	---------------for Bank-----------------------------------			
			objRes=saveBankDtlInDB(objectArgs,blnWorkFlowEnabled);
			//----------------for Passport-----------------------------------
			objRes=savePassportDtlInDB(objectArgs,blnWorkFlowEnabled);
			//----------------for Pan----------
			objRes=savePANDtlInDB(objectArgs,blnWorkFlowEnabled);
			//---------------for license
			objRes=saveLicenseDtlInDB(objectArgs,blnWorkFlowEnabled);


			this.getEmpMiscellenousDtls(objectArgs);

			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			if(blnWorkFlowEnabled) 
			{
				objRes.setViewName("EmpMislnusDetl");
			}
			else
			{
				objRes.setViewName("WorkFlowDisableEmpMislnusDetl");
			}
		}			
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getBnkDtls method in EmpMiscellenousDtlServiceImpl Service",e);
		}
		return objRes;
	}

	public ResultObject getEmpMiscellenousDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long loginUserId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());

				long userId = StringUtility.convertToLong(objectArgs.get("userId").toString());

				boolean blnWorkFlowEnabled = true;
				if (objectArgs.get("blnWorkFlowEnabled") != null)
				{
					blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
				}

				/*  Get Login user id */
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst = orgUserMstDaoImpl.read(loginUserId);
				/*End of the geting user id Code*/

				/*  Get Login user id */
				OrgUserMst selectedOrgUserMst=null;
				if (!blnWorkFlowEnabled)
				{
					selectedOrgUserMst = orgUserMstDaoImpl.read(userId);
				}
				/*End of the geting user id Code*/

				CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst = cmnLanguageMstDao.read(langId);

				EmpMiscellenousDtlDAOImpl daoHibernateImpl = new EmpMiscellenousDtlDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
				List listUserIdData =(List) daoHibernateImpl.getOrgEmpMstVOList(blnWorkFlowEnabled ? loginUserId : userId);

				OrgEmpMst  element_en = null;
				long empId_en= 0;

				if (listUserIdData != null && !listUserIdData.isEmpty())
				{	
					element_en= (OrgEmpMst) listUserIdData.get(0);
					empId_en=element_en.getEmpId();
				}

				logger.info("selected English Employee Id in getEmpMiscellenousDtls ======"+empId_en);
				HrEisEmpMst eisEmpMst=null;
				if(empId_en!=0)
				{ 	   
					EmpMiscellenousDtlDAOImpl hrEisEmpMstDaoImpl= new EmpMiscellenousDtlDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());	   
					List<HrEisEmpMst> hrEmpMstlst =hrEisEmpMstDaoImpl.getHrEmpMstList(empId_en);
					logger.info("empMstlst in getEmpMiscellenousDtls ============="+hrEmpMstlst.size());

					if(hrEmpMstlst!=null && !hrEmpMstlst.isEmpty())
					{
						eisEmpMst=hrEmpMstlst.get(0);
						logger.info("Employee Id PK of HR_EIS_EMP_MST in getEmpMiscellenousDtls======"+eisEmpMst.getEmpId());
					}
				}

				long hrEisEmpMst_id =0;
				if (eisEmpMst != null)
				{
					hrEisEmpMst_id = eisEmpMst.getEmpId();
				}
				//----------take data from lookup master for account type for bank------------------ 
				CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				List<CmnLookupMst> arMiscellenousInfo = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("typeofacc",langId);

				//---------take data from bank amster for bank name for bank-----------------	
				BankMasterDAOImpl bankMasterDAOImpl =null;
					// new BankMasterDAOImpl(HrEisBankMst.class, serv.getSessionFactory());
				List<HrEisBankMst> arBankNameInfo = bankMasterDAOImpl.getAllBankMasterData(langId);	

				BranchMasterDAO branchMasterDAOImpl= null;//new BranchMasterDAOImpl(HrEisBranchMst.class,serv.getSessionFactory());

				//========to retreive data from bank dtls data base table=====================
				EmpMiscellenousDtlDAO BankDAOImpl = new EmpMiscellenousDtlDAOImpl(HrEisBankDtls.class, serv.getSessionFactory());
				List<HrEisBankDtls> BankVOList = BankDAOImpl.getBankDtlVOList(hrEisEmpMst_id);

				HrEisBankDtls objBankDtl=new HrEisBankDtls();
				ArrayList xmlFileNmBank = new ArrayList();
				Map acTypeHas = new HashMap();

				for (Iterator j = BankVOList.iterator(); j.hasNext();)
				{
					objBankDtl =(HrEisBankDtls)j.next();

					/*if (objBankDtl.getHrEisBankMst() != null) 
					{	
						long lngBankCode=objBankDtl.getHrEisBankMst().getBankTypeCode();
						HrEisBankMst objBankMst = bankMasterDAOImpl.getBankMstVOByBranchCodeAndLang(String.valueOf(lngBankCode), langId);

						if(objBankMst!=null)
						{
							objBankMst.getBankTypeCode();
							objBankMst.getBankName();
							objBankDtl.setHrEisBankMst(objBankMst);
						}	
					}

					if (objBankDtl.getHrEisBranchMst() != null  && objBankDtl.getHrEisBranchMst().getBranchTypeCode()!= null) 
					{	
						long lngBranchCode=objBankDtl.getHrEisBranchMst().getBranchTypeCode();
						logger.info("================== lngBranchCode============="+ lngBranchCode);

						String[] strLocationColumns = {"cmnLanguageMst","branchTypeCode"};
						Object[] locationColumsValues ={cmnLanguageMst, (Long)lngBranchCode};

						List<HrEisBranchMst> arHrEisBranchMst =  branchMasterDAOImpl.getListByColumnAndValue(strLocationColumns, locationColumsValues);
						logger.info("======arHrEisBranchMst============="+arHrEisBranchMst.size());
						if (!arHrEisBranchMst.isEmpty())
						{	
							if (arHrEisBranchMst != null)
							{	
								objBankDtl.setHrEisBranchMst(arHrEisBranchMst.get(0));

							}	
						}
					}*/
					objBankDtl.getBankAcctNo();

					if (objBankDtl.getBankAcctType()!= 0)
					{
						CmnLookupMst cmnLookupMst = cmnLookupMstDAOImpl.getLookUpVOByLookUpIDAndLang(objBankDtl.getBankAcctType(), langId);
						cmnLookupMst.getLookupDesc();
						cmnLookupMst.getLookupDesc();
						cmnLookupMst.getLookupName();
						logger.info("cmnLookupMst.getLookupDesc()===="+cmnLookupMst.getLookupDesc());
						logger.info("cmnLookupMst.getLookupName()===="+cmnLookupMst.getLookupName());
						acTypeHas.containsValue(cmnLookupMst.getLookupDesc());
						acTypeHas.put(objBankDtl.getBankAcctType(), cmnLookupMst);
						logger.info("acTypeHas========"+acTypeHas);
					}

					String tempBank = FileUtility.voToXmlFileByXStream(objBankDtl);
					logger.info("====XML in service for bank======"+tempBank);
					xmlFileNmBank.add(tempBank);
				}

				//=========to retreive from insurance table===============
				EmpMiscellenousDtlDAO InsuranceDAOImpl = new EmpMiscellenousDtlDAOImpl(HrEisInscplcyDtl.class, serv.getSessionFactory());

				List<HrEisInscplcyDtl> InsuranceVOList = InsuranceDAOImpl.getInsuranceDtlVOList(blnWorkFlowEnabled ? loginUserId :userId);

				HrEisInscplcyDtl objInsurance = new HrEisInscplcyDtl();
				ArrayList xmlFileNm = new ArrayList();
				for (Iterator i = InsuranceVOList.iterator(); i.hasNext();)
				{
					objInsurance =(HrEisInscplcyDtl)i.next();
					objInsurance.getPolicyNumber();
					objInsurance.getNameOfPolicy();
					objInsurance.getInsrcCompanyName();
					objInsurance.getDateOfPolicy();
					if(objInsurance.getDurationMonth() != 0)
					{	
						objInsurance.getDurationMonth();
					}
					if(objInsurance.getDurationYear() != 0)
					{	
						objInsurance.getDurationYear();
					}
					if(objInsurance.getInsuredAmount() != 0)
					{	
						objInsurance.getInsuredAmount();
					}


					String temp = FileUtility.voToXmlFileByXStream(objInsurance);
					logger.info("====XML in service for insurance======"+temp);
					xmlFileNm.add(temp);
				}

				//=========to retreive from proof table for passport Dtls ===============
				EmpMiscellenousDtlDAO PassportDAOImpl = new EmpMiscellenousDtlDAOImpl(HrEisProofDtl.class, serv.getSessionFactory());

				List<HrEisProofDtl> PassportVOList = PassportDAOImpl.getPassportDtlVOList(blnWorkFlowEnabled ? loginUserId :userId, cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang("Passport_MSC", 1l).getLookupId());

				logger.info("in passport............"+ PassportVOList.size());

				HrEisProofDtl objPassport = new HrEisProofDtl();
				ArrayList xmlFileNmPassport = new ArrayList();
				for (Iterator i = PassportVOList.iterator(); i.hasNext();)
				{
					objPassport =(HrEisProofDtl)i.next();

					objPassport.getProofNum();
					objPassport.getIssueDate();
					objPassport.getExpiryDate();
					if(objPassport.getIssuePlace() != null)
					{	
						objPassport.getIssuePlace();
					}
					if(objPassport.getIssueAuthority() != null)
					{	
						objPassport.getIssueAuthority();
					}

					String temp = FileUtility.voToXmlFileByXStream(objPassport);
					logger.info("====XML in service for passport======"+temp);
					xmlFileNmPassport.add(temp);
				}

				
				//Starts=========to retreive from proof table for License Dtls ===============

				List<HrEisProofDtl> licenseVOList = PassportDAOImpl.getPassportDtlVOList(blnWorkFlowEnabled ? loginUserId :userId, cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang("License_MSC", 1l).getLookupId());
				logger.info("in licenseVOList............"+ licenseVOList.size());
				HrEisProofDtl objLicense = new HrEisProofDtl();
				ArrayList<String> xmlFileNmLicense = new ArrayList<String>();
				for (Iterator<HrEisProofDtl> i = licenseVOList.iterator(); i.hasNext();)
				{
					objLicense =(HrEisProofDtl)i.next();
					objLicense.getProofNum();
					objLicense.getIssueDate();
					objLicense.getExpiryDate();
					
					String strLicenseTypeLookupName = objLicense.getCmnLookupMstByLicenseType() != null ? objLicense.getCmnLookupMstByLicenseType().getLookupName() : "";
					
					if(!"".equals(strLicenseTypeLookupName))
					{
						objLicense.setCmnLookupMstByLicenseType(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strLicenseTypeLookupName, langId));
					}
					
					objLicense.getCmnLookupMstByLicenseType();
					if(objLicense.getIssuePlace() != null)
					{	
						objLicense.getIssuePlace();
					}
					if(objLicense.getIssueAuthority() != null)
					{	
						objLicense.getIssueAuthority();
					}
					String temp = FileUtility.voToXmlFileByXStream(objLicense);
					logger.info("====XML in service for License======"+temp);
					xmlFileNmLicense.add(temp);
				}
				List<CmnLookupMst>lstLicenseType =cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("License_Type", langId);
				objectArgs.put("xmlFileNmLicense", xmlFileNmLicense);
				objectArgs.put("licenseVOList", licenseVOList);
				objectArgs.put("lstLicenseType", lstLicenseType);
				//Ends=========to retreive from proof table for License Dtls ===============
				
				
				//=========== to retreive from proof table for pan Dtls============================
				EmpMiscellenousDtlDAO PANDAOImpl = new EmpMiscellenousDtlDAOImpl(HrEisProofDtl.class, serv.getSessionFactory());
				logger.info("in passport......pan");
				HrEisProofDtl PANVO = PANDAOImpl.getEmpPanNo(blnWorkFlowEnabled ? loginUserId :userId, cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang("PAN_MSC", 1l).getLookupId());

				String PanNo =	(PANVO != null && PANVO.getProofNum() != null)? PANVO.getProofNum() : "";
				logger.info("=====PanNo===="+PanNo);

				if (userId != 0)
				{
					if (!blnWorkFlowEnabled)
					{
						objectArgs.put("EmpInfo_userId",userId);
						objectArgs.put("EmpInfo_PayFix",true);
					}
				}

				//changed by sandip - start
				EmpInfoDAO_WF empInfoDAO_WF = new EmpInfoDAOImpl_WF(OrgEmpMst.class, serv.getSessionFactory());
				OrgEmpMst empMst = empInfoDAO_WF.getEmployee(blnWorkFlowEnabled ? orgUserMst : selectedOrgUserMst, 1l);


				if(empMst!=null)
				{
					List hrEisEmpMstLst = PassportDAOImpl.getHrEmpMstList(empMst.getEmpId());
					if(hrEisEmpMstLst!=null && !hrEisEmpMstLst.isEmpty())
					{
						objectArgs.put("empFlag", true);
					}
				}
				//changed by sandip - end

				objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data

				objectArgs.put("PanNo", PanNo);

				objectArgs.put("xmlFileNm", xmlFileNm);
				objectArgs.put("InsuranceVOList", InsuranceVOList);

				objectArgs.put("acType", acTypeHas);
				objectArgs.put("xmlFileNmBank", xmlFileNmBank);
				objectArgs.put("BankVOList", BankVOList);
				objectArgs.put("arMiscellenousInfo", arMiscellenousInfo);
				objectArgs.put("arBankNameInfo", arBankNameInfo);

				objectArgs.put("xmlFileNmPassport", xmlFileNmPassport);
				objectArgs.put("PassportVOList", PassportVOList);

				objectArgs.put("selectedUserId", userId);
				objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled);

				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);

				if(blnWorkFlowEnabled) 
				{
					objRes.setViewName("EmpMislnusDetl");
				}
				else
				{
					objRes.setViewName("WorkFlowDisableEmpMislnusDetl");
				}
			}
		} 
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getEmpMiscellenousDtls method in EmpMiscellenousDtlServiceImpl Service",e);
		}
		return objRes;
	}

	public ResultObject getBranchName(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				StringBuffer sbXML= new StringBuffer();
				String strBankCode = objectArgs.get("strBankCode").toString();


				BankMasterDAOImpl bankMasterDAOImpl = null;//new BankMasterDAOImpl(HrEisBankMst.class,serv.getSessionFactory());
				HrEisBankMst hrEisBankMst = bankMasterDAOImpl.getBankMstVOByBranchCodeAndLang(strBankCode, langId);


				long lngBankId = hrEisBankMst != null ? hrEisBankMst.getBankId() : 0;
				BranchMasterDAOImpl branchDAOImpl=null;//new BranchMasterDAOImpl(HrEisBranchMst.class,serv.getSessionFactory());

				//condition to get data according to langId and field
				List<HrEisBranchMst> arBranchNameInfo = branchDAOImpl.getBranchDetailsByCodeAndLangId(lngBankId, langId);

				logger.info("=======arBranchNameInfo====="+arBranchNameInfo);
				sbXML.append("<root>");	
				for (Iterator i = arBranchNameInfo.iterator(); i.hasNext();)
				{
					HrEisBranchMst objHrEisBranchMst = (HrEisBranchMst) i.next();
					sbXML.append("<option value=\""+ objHrEisBranchMst.getBranchTypeCode() +"\" >");
					sbXML.append("<![CDATA[" + objHrEisBranchMst.getBranchName() +"]]>");
					sbXML.append("</option>");
				} 
				sbXML.append("</root>");
				String BranchStr = new AjaxXmlBuilder().addItem("key_ajax", sbXML.toString()).toString();
				logger.info("============= Branch XML=========="+ BranchStr);
				objectArgs.put("ajaxKey", BranchStr);
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);
				objRes.setViewName("ajaxData"); 
			}

		}	
		catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getBranchName method in EmpMiscellenousDtlServiceImpl Service",e);
		}

		return objRes;
	}
	
	public ResultObject saveInsuranceDtlInDB(Map objectArgs, boolean blnWorkFlowEnabled)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long loginUserId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				//long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());	
				long userId = StringUtility.convertToLong(objectArgs.get("userId").toString());

				/*  Get Login user id */
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst = orgUserMstDaoImpl.read(loginUserId);
				/*End of the geting user id Code*/

				/*  Get Login user id */
				OrgUserMst selectedOrgUserMst=null;
				if (!blnWorkFlowEnabled)
				{
					selectedOrgUserMst = orgUserMstDaoImpl.read(userId);
				}
				/*End of the geting user id Code*/

				/*  Get The Person Post */			 			 	    	 
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    
				/*End of the geting Person Post Code*/	    	 	    		    	

				/* Getting a DB ID*/								 
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				/* End og DB Id code*/

				/* Getting a Loc ID Code */
				long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
				CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
				/* End of Loc ID */

				List<HrEisInscplcyDtl> lstHrEisEmpInsrcpolicyDtlsVO=(List<HrEisInscplcyDtl>) objectArgs.get("lstHrEisEmpInsrcpolicyDtlsVO");
				HrEisInscplcyDtl objInsPolicy = new HrEisInscplcyDtl();

				EmpMiscellenousDtlDAO objInsuranceDAOImpl=new EmpMiscellenousDtlDAOImpl(HrEisInscplcyDtl.class, serv.getSessionFactory());
				//to read updated data-----
				List updatedVOList = (List) objectArgs.get("updatedVOList");
				List deletedVOList = (List) objectArgs.get("deletedVOList");

				//code after updated for delete, save into db
				if(deletedVOList!=null)
				{
					ListIterator litr = deletedVOList.listIterator();
					while(litr.hasNext())
					{																
						HrEisInscplcyDtl obInsurance =(HrEisInscplcyDtl)litr.next();
						if(obInsurance.getEmpInsrcpolicyDtlsPk()!=0)
						{
							HrEisInscplcyDtl readobInsurance = (HrEisInscplcyDtl) objInsuranceDAOImpl.read(obInsurance.getEmpInsrcpolicyDtlsPk());
							objInsuranceDAOImpl.delete(readobInsurance);
						}
					} 
				}
				//end of delete
				//code after updated for update, save into db
				HrEisInscplcyDtl obInsurance = null;
				Date currDate = new Date();
				
				if(updatedVOList!=null)
				{				
					ListIterator litr = updatedVOList.listIterator();
					while(litr.hasNext())
					{					
						obInsurance = new HrEisInscplcyDtl();
						obInsurance =(HrEisInscplcyDtl)litr.next();
						if(obInsurance.getEmpInsrcpolicyDtlsPk()!=0)
						{
							HrEisInscplcyDtl readobInsurance = (HrEisInscplcyDtl) objInsuranceDAOImpl.read(obInsurance.getEmpInsrcpolicyDtlsPk());
							readobInsurance.setPolicyNumber(obInsurance.getPolicyNumber());
							readobInsurance.setNameOfPolicy(obInsurance.getNameOfPolicy());
							readobInsurance.setInsrcCompanyName(obInsurance.getInsrcCompanyName());
							readobInsurance.setDateOfPolicy(obInsurance.getDateOfPolicy());
							readobInsurance.setDurationMonth(obInsurance.getDurationMonth());
							readobInsurance.setDurationYear(obInsurance.getDurationYear());
							readobInsurance.setInsuredAmount(obInsurance.getInsuredAmount());
							readobInsurance.setOrgPostMstByUpdatedPost(orgPostMst);
							readobInsurance.setOrgUserMstByUpdatedBy(orgUserMst);
							readobInsurance.setUpdatedDate(currDate);
							objInsuranceDAOImpl.update(readobInsurance);
						}
					}	
				}

				//end of update
				//code start for insertion in db
				for(Iterator i = lstHrEisEmpInsrcpolicyDtlsVO.iterator(); i.hasNext();)
				{
					objInsPolicy = (HrEisInscplcyDtl)i.next();
					long InsuranceId = IDGenerateDelegate.getNextId("hr_eis_inscplcy_dtl", objectArgs);

					objInsPolicy.setEmpInsrcpolicyDtlsPk(InsuranceId);
					objInsPolicy.setCmnLocationMst(cmnLocationMst);
					objInsPolicy.setOrgPostMstByCreatedByPost(orgPostMst);						
					objInsPolicy.setOrgUserMstByCreatedBy(orgUserMst);
					objInsPolicy.setCmnDatabaseMst(cmnDatabaseMst);
					objInsPolicy.setCreatedDate(currDate);

					if (blnWorkFlowEnabled) 
					{
						objInsPolicy.setOrgUserMstByOrgUserMstUserIdFk(orgUserMst);
					}
					else
					{
						objInsPolicy.setOrgUserMstByOrgUserMstUserIdFk(selectedOrgUserMst);
					}

					objInsuranceDAOImpl.create(objInsPolicy);
				}
				//end of save
			}	
		}
		catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling saveInsuranceDtlInDB method in EmpMiscellenousDtlServiceImpl Service",e);
		}
		return objRes;
	}

	public ResultObject saveBankDtlInDB(Map objectArgs, boolean blnWorkFlowEnabled)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long loginUserId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());

				long userId = StringUtility.convertToLong(objectArgs.get("userId").toString());

				/*  Get Login user id */
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst = orgUserMstDaoImpl.read(loginUserId);
				/*End of the geting user id Code*/

				EmpMiscellenousDtlDAOImpl daoHibernateImpl = new EmpMiscellenousDtlDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
				List listUserIdData =(List) daoHibernateImpl.getOrgEmpMstVOList(blnWorkFlowEnabled ? loginUserId : userId);


				OrgEmpMst  element_en = null;
				long empId_en= 0;

				if (listUserIdData != null && !listUserIdData.isEmpty())
				{	
					element_en= (OrgEmpMst) listUserIdData.get(0);
					empId_en=element_en.getEmpId();
				}

				/**Starts Change By Sunil (01/07/08)*/

				logger.info("English Employee Id======"+empId_en);
				HrEisEmpMst eisEmpMst=null;
				if(empId_en!=0)
				{ 	   
					EmpMiscellenousDtlDAOImpl hrEisEmpMstDaoImpl= new EmpMiscellenousDtlDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());	   
					List<HrEisEmpMst> hrEmpMstlst =hrEisEmpMstDaoImpl.getHrEmpMstList(empId_en);
					logger.info("empMstlst============="+hrEmpMstlst.size());

					if(hrEmpMstlst!=null && !hrEmpMstlst.isEmpty())
					{
						eisEmpMst=hrEmpMstlst.get(0);
						logger.info("Employee Id PK of HR_EIS_EMP_MST======"+eisEmpMst.getEmpId());
					}
				}

				/**Ends Change By Sunil (01/07/08)*/

				/*  Get The Person Post */			 			 	    	 
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    
				/*End of the geting Person Post Code*/	    	 	    		    	

				/* Getting a DB ID*/								 
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				/* End og DB Id code*/

				List<HrEisBankDtls> lstHrEisBankDtlsVO=(List<HrEisBankDtls>) objectArgs.get("lstHrEisBankDtlsVO");
				HrEisBankDtls objBank= new HrEisBankDtls();

				EmpMiscellenousDtlDAO objBankDAOImpl=new EmpMiscellenousDtlDAOImpl(HrEisBankDtls.class, serv.getSessionFactory());
				BankMasterDAO objBankMasterDAO = null;//new BankMasterDAOImpl(HrEisBankMst.class,serv.getSessionFactory());
				BranchMasterDAO objBranchMasterDAO = null;//new BranchMasterDAOImpl(HrEisBranchMst.class,serv.getSessionFactory());

				//to read updated data-----
				List updatedBankVOList = (List) objectArgs.get("updatedBankVOList");
				List deletedBankVOList = (List) objectArgs.get("deletedBankVOList");

				//code after updated for delete, save into db
				if(deletedBankVOList!=null)
				{
					ListIterator litr = deletedBankVOList.listIterator();
					while(litr.hasNext())
					{	
						HrEisBankDtls obHrEisBankDtls =(HrEisBankDtls)litr.next();
						if(obHrEisBankDtls.getBankDtlId()!=0)
						{
							HrEisBankDtls readobHrEisBankDtls = (HrEisBankDtls)objBankDAOImpl.read(obHrEisBankDtls.getBankDtlId());
							objBankDAOImpl.delete(readobHrEisBankDtls);
						}
					} 
				}
				//end of delete
				//code after updated for update, save into db		
				HrEisBankDtls obHrEisBankDtls = null;
				Date currDate = new Date();
				
				if(updatedBankVOList!=null)
				{				
					ListIterator litr = updatedBankVOList.listIterator();
					while(litr.hasNext())
					{					
						obHrEisBankDtls =new HrEisBankDtls();
						obHrEisBankDtls =(HrEisBankDtls)litr.next();
						if(obHrEisBankDtls.getBankDtlId()!=0)
						{
							HrEisBankDtls readobHrEisBankDtls = (HrEisBankDtls)objBankDAOImpl.read(obHrEisBankDtls.getBankDtlId());
							readobHrEisBankDtls.setBankAcctNo(obHrEisBankDtls.getBankAcctNo());
							//readobHrEisBankDtls.setHrEisBankMst(objBankMasterDAO.getBankMstVOByBranchCodeAndLang(String.valueOf(obHrEisBankDtls.getHrEisBankMst().getBankTypeCode()), langId));
							//readobHrEisBankDtls.setCmnLookupMstByBankTypeOfAccount(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(obHrEisBankDtls.getCmnLookupMstByBankTypeOfAccount().getLookupName(),langId));
							readobHrEisBankDtls.setBankAcctType(obHrEisBankDtls.getBankAcctType());
							//readobHrEisBankDtls.setHrEisBranchMst(objBranchMasterDAO.getBranchMstVOByBranchCodeAndLang(String.valueOf(obHrEisBankDtls.getHrEisBranchMst().getBranchTypeCode()), langId));
							readobHrEisBankDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
							readobHrEisBankDtls.setBankAcctStartDt(currDate); 
							readobHrEisBankDtls.setOrgUserMstByUpdatedBy(orgUserMst);
							readobHrEisBankDtls.setUpdatedDate(currDate);
							objBankDAOImpl.update(readobHrEisBankDtls);
						}
					}	
				}
				//end of update
				//code start for insertion in db
				BankMasterDAO bankMasterDAO = null;//new BankMasterDAOImpl(HrEisBankMst.class,serv.getSessionFactory());
				BranchMasterDAO branchMasterDAO=null;//new BranchMasterDAOImpl(HrEisBranchMst.class,serv.getSessionFactory());
				HrEisBankMst objHrEisBankMst = null;
				HrEisBranchMst objHrEisBranchDtls=null;
				
				for(Iterator i = lstHrEisBankDtlsVO.iterator(); i.hasNext();)
				{
					objBank = (HrEisBankDtls)i.next();
					long bankId = IDGenerateDelegate.getNextId("hr_eis_bank_dtls", objectArgs);

					objHrEisBankMst = new HrEisBankMst();
					objHrEisBranchDtls=new HrEisBranchMst();

					/*objHrEisBankMst=objBank.getHrEisBankMst();
					objHrEisBranchDtls=objBank.getHrEisBranchMst();*/

					logger.info("==================objHrEisBranchDtls.getBranchTypeCode()========"+ objHrEisBranchDtls.getBranchTypeCode());


					if (objHrEisBranchDtls.getBranchTypeCode() != null && !objHrEisBranchDtls.getBranchTypeCode().equals(""))
					{
						HrEisBranchMst objBranchMst = branchMasterDAO.getBranchMstVOByBranchCodeAndLang(String.valueOf(objHrEisBranchDtls.getBranchTypeCode()), langId);
						logger.info("==========objBranchMst====="+ objBranchMst.getBranchId());
						//objBank.setHrEisBranchMst(objBranchMst);
					}

					objBank.setHrEisEmpMst(eisEmpMst);
					//objBank.setHrEisBankMst(bankMasterDAO.getBankMstVOByBranchCodeAndLang(String.valueOf(objHrEisBankMst.getBankTypeCode()), langId));
					objBank.setBankAcctType(objBank.getBankAcctType());
					objBank.setBankAcctStartDt(currDate);
					objBank.setBankDtlId(bankId);
					objBank.setOrgUserMstByCreatedBy(orgUserMst);
					objBank.setOrgPostMstByCreatedByPost(orgPostMst);
					objBank.setCmnDatabaseMst(cmnDatabaseMst);
					objBank.setCreatedDate(currDate);
					objBankDAOImpl.create(objBank);
				}
				//end of save
			}		
		}

		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling saveBankDtlInDB method in EmpMiscellenousDtlServiceImpl Service",e);
		}
		return objRes;
	}

	public ResultObject savePassportDtlInDB(Map objectArgs, boolean blnWorkFlowEnabled)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long loginUserId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());

				long userId = StringUtility.convertToLong(objectArgs.get("userId").toString());

				/*  Get Login user id */
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst = orgUserMstDaoImpl.read(loginUserId);
				/*End of the geting user id Code*/

				/*  Get Login user id */
				OrgUserMst selectedOrgUserMst=null;
				if (!blnWorkFlowEnabled)
				{
					selectedOrgUserMst = orgUserMstDaoImpl.read(userId);
				}
				/*End of the geting user id Code*/

				/*  Get The Person Post */			 			 	    	 
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    
				/*End of the geting Person Post Code*/	    	 	    		    	

				/* Getting a DB ID*/								 
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				/* End og DB Id code*/

				/* Getting a Loc ID Code */
				long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
				CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
				/* End of Loc ID */

				List<HrEisProofDtl> lstHrEisEmpProofDtlsVO=(List<HrEisProofDtl>) objectArgs.get("lstHrEisEmpProofDtlsVO");
				HrEisProofDtl objPassport = new HrEisProofDtl();

				EmpMiscellenousDtlDAO objPassportDAOImpl=new EmpMiscellenousDtlDAOImpl(HrEisProofDtl.class, serv.getSessionFactory());
				//to read updated data-----
				List updatedPassportVOList = (List) objectArgs.get("updatedPassportVOList");
				List deletedPassportVOList = (List) objectArgs.get("deletedPassportVOList");

				CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());

				//code after updated delete, save into db
				if(deletedPassportVOList!=null)
				{
					ListIterator litr = deletedPassportVOList.listIterator();
					while(litr.hasNext())
					{					
						HrEisProofDtl obPassport=(HrEisProofDtl)litr.next();
						if(obPassport.getSrNo()!=0)
						{
							HrEisProofDtl readobPassport=(HrEisProofDtl)objPassportDAOImpl.read(obPassport.getSrNo());
							objPassportDAOImpl.delete(readobPassport);
						}
					} 
				}
				//end of delete
				//code after updated for update, save into db
				HrEisProofDtl obPassport=null;
				Date currDate =new Date();
				if(updatedPassportVOList!=null)
				{				
					ListIterator litr = updatedPassportVOList.listIterator();
					while(litr.hasNext())
					{		
						obPassport=new HrEisProofDtl();
						obPassport=(HrEisProofDtl)litr.next();
						if(obPassport.getSrNo()!=0)
						{
							HrEisProofDtl readobPassport=(HrEisProofDtl)objPassportDAOImpl.read(obPassport.getSrNo());
							readobPassport.setCmnLookupMst(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("Passport_MSC", 1l));
							readobPassport.setProofNum(obPassport.getProofNum());
							readobPassport.setIssueDate(obPassport.getIssueDate());
							readobPassport.setExpiryDate(obPassport.getExpiryDate());
							readobPassport.setIssuePlace(obPassport.getIssuePlace());
							readobPassport.setIssueAuthority(obPassport.getIssueAuthority());
							readobPassport.setUpdatedDate(currDate);
							readobPassport.setOrgUserMstByUpdatedByPost(orgPostMst);
							readobPassport.setOrgUserMstByUpdatedBy(orgUserMst);
							objPassportDAOImpl.update(readobPassport);
						}
					}	
				}
				//end of update
				//code start for insertion in db for Passport
				for(Iterator i = lstHrEisEmpProofDtlsVO.iterator(); i.hasNext();)
				{
					objPassport = (HrEisProofDtl)i.next();
				
					long PassportId = IDGenerateDelegate.getNextId("hr_eis_proof_dtl", objectArgs);

					objPassport.setCmnLookupMst(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("Passport_MSC", 1l));
					objPassport.setSrNo(PassportId);
					objPassport.setCmnLocationMst(cmnLocationMst);
					objPassport.setOrgPostMstByCreatedByPost(orgPostMst);						
					objPassport.setOrgUserMstByCreatedBy(orgUserMst);
					objPassport.setCmnDatabaseMst(cmnDatabaseMst);
					objPassport.setCreatedDate(currDate);
					if (blnWorkFlowEnabled) 
					{
						objPassport.setOrgPostMstByUserId(orgUserMst);
					}
					else
					{
						objPassport.setOrgPostMstByUserId(selectedOrgUserMst);
					}
					objPassportDAOImpl.create(objPassport);
				} 
				//end of save
			}	
		}
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling savePassportDtlInDB method in EmpMiscellenousDtlServiceImpl Service",e);
		}
		return objRes;
	}
	
	public ResultObject saveLicenseDtlInDB(Map<String, Object> objectArgs, boolean blnWorkFlowEnabled)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long loginUserId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long userId = StringUtility.convertToLong(objectArgs.get("userId").toString());

				/*  Get Login user id */
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst = orgUserMstDaoImpl.read(loginUserId);
				/*End of the geting user id Code*/

				/*  Get Login user id */
				OrgUserMst selectedOrgUserMst=null;
				if (!blnWorkFlowEnabled)
				{
					selectedOrgUserMst = orgUserMstDaoImpl.read(userId);
				}
				/*End of the geting user id Code*/

				/*  Get The Person Post */			 			 	    	 
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    
				/*End of the geting Person Post Code*/	    	 	    		    	

				/* Getting a DB ID*/								 
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				/* End og DB Id code*/

				/* Getting a Loc ID Code */
				long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
				CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
				/* End of Loc ID */

				List<HrEisProofDtl> lstHrEisEmpProofDtls=(List<HrEisProofDtl>) objectArgs.get("lstHrEisEmpProofDtls");
				logger.info("lstHrEisEmpProofDtls=======Size==="+lstHrEisEmpProofDtls.size());
				HrEisProofDtl objLicenseProofDtls = new HrEisProofDtl();

				EmpMiscellenousDtlDAO objPassportDAOImpl=new EmpMiscellenousDtlDAOImpl(HrEisProofDtl.class, serv.getSessionFactory());
				//to read updated data-----
				List updatedLicenseVOList = (List) objectArgs.get("updatedLicenseVOList");
				List deletedLicenseVOList = (List) objectArgs.get("deletedLicenseVOList");

				CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());

				//code after updated delete, save into db
				if(deletedLicenseVOList!=null)
				{
					ListIterator litr = deletedLicenseVOList.listIterator();
					while(litr.hasNext())
					{					
						HrEisProofDtl objLicense=(HrEisProofDtl)litr.next();
						if(objLicense.getSrNo()!=0)
						{
							HrEisProofDtl readObjLicense=(HrEisProofDtl)objPassportDAOImpl.read(objLicense.getSrNo());
							objPassportDAOImpl.delete(readObjLicense);
						}
					} 
				}
				//end of delete
				//code after updated for update, save into db
				HrEisProofDtl objUpdateLicenseProofDtls=null;
				Date currDate =new Date();
				if(updatedLicenseVOList!=null)
				{				
					ListIterator litr = updatedLicenseVOList.listIterator();
					while(litr.hasNext())
					{		
						objUpdateLicenseProofDtls=new HrEisProofDtl();
						objUpdateLicenseProofDtls=(HrEisProofDtl)litr.next();
						if(objUpdateLicenseProofDtls.getSrNo()!=0)
						{
							HrEisProofDtl readobjLicenseProofDtls=(HrEisProofDtl)objPassportDAOImpl.read(objUpdateLicenseProofDtls.getSrNo());
							readobjLicenseProofDtls.setCmnLookupMst(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("License_MSC", 1l));
							readobjLicenseProofDtls.setProofNum(objUpdateLicenseProofDtls.getProofNum());
							readobjLicenseProofDtls.setIssueDate(objUpdateLicenseProofDtls.getIssueDate());
							readobjLicenseProofDtls.setExpiryDate(objUpdateLicenseProofDtls.getExpiryDate());
							readobjLicenseProofDtls.setIssuePlace(objUpdateLicenseProofDtls.getIssuePlace());
							readobjLicenseProofDtls.setIssueAuthority(objUpdateLicenseProofDtls.getIssueAuthority());
							
							if(objUpdateLicenseProofDtls.getCmnLookupMstByLicenseType()!=null && !objUpdateLicenseProofDtls.getCmnLookupMstByLicenseType().getLookupName().equals(""))
							{
								readobjLicenseProofDtls.setCmnLookupMstByLicenseType(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(objUpdateLicenseProofDtls.getCmnLookupMstByLicenseType().getLookupName(), langId));
							}

							readobjLicenseProofDtls.setOrgUserMstByUpdatedByPost(orgPostMst);
							readobjLicenseProofDtls.setOrgUserMstByUpdatedBy(orgUserMst);
							readobjLicenseProofDtls.setUpdatedDate(currDate);
							objPassportDAOImpl.update(readobjLicenseProofDtls);
						}
					}	
				}
				//end of update
				//code start for insertion in db for Passport
				for(Iterator i = lstHrEisEmpProofDtls.iterator(); i.hasNext();)
				{
					objLicenseProofDtls = (HrEisProofDtl)i.next();
					long licenseId = IDGenerateDelegate.getNextId("hr_eis_proof_dtl", objectArgs);

					objLicenseProofDtls.setCmnLookupMst(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("License_MSC", 1l));
					objLicenseProofDtls.setSrNo(licenseId);
					objLicenseProofDtls.setCmnLocationMst(cmnLocationMst);
					objLicenseProofDtls.setOrgPostMstByCreatedByPost(orgPostMst);						
					objLicenseProofDtls.setOrgUserMstByCreatedBy(orgUserMst);
					objLicenseProofDtls.setCmnDatabaseMst(cmnDatabaseMst);
					objLicenseProofDtls.setCreatedDate(currDate);
					if(objLicenseProofDtls.getCmnLookupMstByLicenseType()!=null && !objLicenseProofDtls.getCmnLookupMstByLicenseType().getLookupName().equals(""))
					{
						objLicenseProofDtls.setCmnLookupMstByLicenseType(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(objLicenseProofDtls.getCmnLookupMstByLicenseType().getLookupName(), langId));
					}
					
					if (blnWorkFlowEnabled) 
					{
						objLicenseProofDtls.setOrgPostMstByUserId(orgUserMst);
					}
					else
					{
						objLicenseProofDtls.setOrgPostMstByUserId(selectedOrgUserMst);
					}
					objPassportDAOImpl.create(objLicenseProofDtls);
				} 
				//end of save
			}	
		}
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling saveLicenseDtlInDB method in EmpMiscellenousDtlServiceImpl Service",e);
		}
		return objRes;
	}
		
	public ResultObject savePANDtlInDB(Map objectArgs, boolean blnWorkFlowEnabled)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long loginUserId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());	

				long userId = StringUtility.convertToLong(objectArgs.get("userId").toString());

				/*  Get Login user id */
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst = orgUserMstDaoImpl.read(loginUserId);
				/*End of the geting user id Code*/

				/*  Get Login user id */
				OrgUserMst selectedOrgUserMst=null;
				if (!blnWorkFlowEnabled)
				{
					selectedOrgUserMst = orgUserMstDaoImpl.read(userId);
				}
				/*End of the geting user id Code*/

				/*  Get The Person Post */			 			 	    	 
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    
				/*End of the geting Person Post Code*/	    	 	    		    	

				/* Getting a DB ID*/								 
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				/* End og DB Id code*/

				/* Getting a Loc ID Code */
				long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
				CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
				/* End of Loc ID */

				EmpMiscellenousDtlDAO objPANDAOImpl=new EmpMiscellenousDtlDAOImpl(HrEisProofDtl.class, serv.getSessionFactory());
				CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());



				EmpMiscellenousDtlDAO PANDAOImpl = new EmpMiscellenousDtlDAOImpl(HrEisProofDtl.class, serv.getSessionFactory());
				HrEisProofDtl PANVO = PANDAOImpl.getEmpPanNo(blnWorkFlowEnabled ? loginUserId : userId, cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("PAN_MSC", 1l).getLookupId());

				Date currDate = new Date();
				
				if(PANVO == null)
				{	
					//for Insert New Pan Details			
					String strPanNo = objectArgs.get("strPanNo") != null ? objectArgs.get("strPanNo").toString() : "";

					logger.info("============== strPanNo=========="+ strPanNo);
					
					if (!"".equals(strPanNo))
					{
						HrEisProofDtl objPAN = new HrEisProofDtl();
						long PANId = IDGenerateDelegate.getNextId("hr_eis_proof_dtl", objectArgs);

						objPAN.setCmnLookupMst(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("PAN_MSC", 1l));
						objPAN.setSrNo(PANId);
						objPAN.setProofNum(strPanNo);
						objPAN.setCmnLocationMst(cmnLocationMst);
						objPAN.setOrgPostMstByCreatedByPost(orgPostMst);						
						objPAN.setOrgUserMstByCreatedBy(orgUserMst);
						objPAN.setCmnDatabaseMst(cmnDatabaseMst);
						objPAN.setCreatedDate(currDate);

						if (blnWorkFlowEnabled) 
						{
							objPAN.setOrgPostMstByUserId(orgUserMst);
						}
						else
						{
							objPAN.setOrgPostMstByUserId(selectedOrgUserMst);
						}

						objPANDAOImpl.create(objPAN);
					} 
				}
				//end of insert
				else
				{
					//start of pan detl in update mode
					String strPanNo = (String) objectArgs.get("strPanNo");
					PANVO.setProofNum(strPanNo);
					PANVO.setOrgUserMstByUpdatedByPost(orgPostMst);						
					PANVO.setOrgUserMstByUpdatedBy(orgUserMst);
					PANVO.setUpdatedDate(currDate);
					if (blnWorkFlowEnabled) 
					{
						PANVO.setOrgPostMstByUserId(orgUserMst);
					}
					else
					{
						PANVO.setOrgPostMstByUserId(selectedOrgUserMst);
					}
					objPANDAOImpl.update(PANVO);
					//end of update
				}
			}
		}
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling savePanDtlInDB method in EmpMiscellenousDtlServiceImpl Service",e);
		}
		return objRes;
	}	

	public ResultObject getLookUpName(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				logger.info("inside function");
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

				long lngLookUpId=Long.parseLong(objectArgs.get("lngLookUpId").toString());
				StringBuffer sbXML= new StringBuffer();
				logger.info("lookupId========"+lngLookUpId);
				CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst objCmnLookupMst= (CmnLookupMst)cmnLookupMstDAO.read(lngLookUpId);
				sbXML.append("<root>");	
				if (objCmnLookupMst != null)
				{
					sbXML.append("<lookupName>"+ objCmnLookupMst.getLookupName() +"</lookupName>");
					sbXML.append("<lookupDesc>"+ objCmnLookupMst.getLookupDesc() +"</lookupDesc>");
				}
				sbXML.append("</root>");
				String strLookupName = new AjaxXmlBuilder().addItem("key_ajax", sbXML.toString()).toString();
				objectArgs.put("ajaxKey", strLookupName);
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);
				objRes.setViewName("ajaxData"); 
			}

		}
		catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getLookupName method in EmpMiscellenousDtlServiceImpl Service",e);
		}
		return objRes;
	}

	public ResultObject getAccLookUpName(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

			if (objRes != null && objectArgs != null) 
			{
				String  strFlag= objectArgs.get("flag").toString();

				if("BankDtls".equals(strFlag))
				{
					String  stracctype= objectArgs.get("BankAccountType").toString();

					HrEisBankDtls objHrEisBankDtls = new HrEisBankDtls();

					objHrEisBankDtls = (HrEisBankDtls) objectArgs.get("objHrEisBankDtls");

					CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					CmnLookupMst cmnLookupMstByBankTypeOfAccount=cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(stracctype, langId);
					long lngAccTypeId = cmnLookupMstByBankTypeOfAccount.getLookupId();
					objHrEisBankDtls.setBankAcctType(lngAccTypeId);

					String xmlFileId = FileUtility.voToXmlFileByXStream(objHrEisBankDtls);

					logger.info("============== xmlFileId in Service for bank========"+ xmlFileId);
					objectArgs.put("ajaxKey", xmlFileId);
					objRes.setResultCode(ErrorConstants.SUCCESS);			
					objRes.setViewName("ajaxData");
					objRes.setResultValue(objectArgs);
				}		
				else
				{
					objRes.setResultValue(objectArgs);
				}
			}
		}catch (Exception e)
		{			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Genrating Xml for Bank Dtl InService Impl",e);
		}
		return objRes;
	}	
}

