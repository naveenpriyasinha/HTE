package com.tcs.sgv.eis.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnCityMstDAO;
import com.tcs.sgv.common.dao.CmnCityMstDAOImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDao;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstOffice;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDao;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgDesignationMstDao;
import com.tcs.sgv.ess.dao.OrgDesignationMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgGradeMstDao;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.eis.dao.HrEisScaleMstDao;
import com.tcs.sgv.eis.dao.HrEisScaleMstDaoImpl;
import com.tcs.sgv.eis.dao.PayComponentCommissionRltDAO;
import com.tcs.sgv.eis.dao.PayComponentCommissionRltDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentMasterDAO;
import com.tcs.sgv.eis.dao.PayComponentMasterDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentParamMstDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupMstDAO;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupMstDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupParamRltDAO;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupParamRltDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayAllowDedCommissionRlt;
import com.tcs.sgv.eis.valueobject.HrPayAllowDedMst;
import com.tcs.sgv.eis.valueobject.HrPayCompRuleCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayCompViewRuleCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpMst;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpParamRlt;
import com.tcs.sgv.eis.valueobject.HrPayRuleParamMst;
import com.tcs.sgv.payroll.util.PayrollConstants;
import com.tcs.sgv.eis.service.PayrollInboundIntegrationServiceImpl;
/**
 * This class consists of methods to add, modify or delete payroll components
 * 
 * @class name : PayComponentMasterServiceImpl
 * @author : Ravysh Tiwari
 * @version : 1
 */

public class PayComponentMasterServiceImpl extends ServiceImpl {
//implements PayComponentMasterService {
	Log logger = LogFactory.getLog(getClass());

	public PayComponentMasterServiceImpl() {

	}
	@SuppressWarnings("unchecked")
	public ResultObject viewPayComponent(Map<String, Object> objectArgs) {
			
			logger.info("PayComponentMasterServiceImpl--->viewPayComponent method called");
			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
			try {
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
				PayComponentMasterDAO payCompDAO = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class, serv.getSessionFactory());
				Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
				int langId = Integer.parseInt(loginMap.get("langId").toString());
				
				List<HrPayAllowDedMst> payComponentList = payCompDAO.getPayComponentsForViewList(langId);
				
				objectArgs.put("payComponentList",payComponentList);
				resObj.setViewName("adminViewPayComponent");
				resObj.setResultCode(ErrorConstants.SUCCESS);
				resObj.setResultValue(objectArgs);
			} catch (Exception ex) {
				logger.error("Error is: "+ ex.getMessage());
				resObj.setThrowable(ex);
				logger.error("Admin Screen view Pay Component Error in PayComponentMasterServiceImpl--->viewPayComponent", ex);
				resObj.setResultCode(ErrorConstants.ERROR);
			}
			return resObj;
		}
	//@SuppressWarnings("unchecked")
	public ResultObject addPayComponent(Map<String, Object> objectArgs) {
		
		logger.info("PayComponentMasterServiceImpl--->addPayComponent method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			//Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			//int langId = Integer.parseInt(loginMap.get("langId").toString());
			
			String editFlag=(String) objectArgs.get("editFlag");
			long reqPayComponentCode=Long.parseLong(objectArgs.get("reqPayComponentCode").toString());
			
			PayComponentMasterDAO payCompDAO = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class, serv.getSessionFactory());
			PayComponentCommissionRltDAO commissionRltDAO = new PayComponentCommissionRltDAOImpl(HrPayAllowDedCommissionRlt.class,serv.getSessionFactory());
			
			HrPayAllowDedMst hrPayCompMst = new HrPayAllowDedMst();
			HrPayAllowDedMst hrPayCompMstSecLang = new HrPayAllowDedMst();
			if(editFlag.equals("Y") && reqPayComponentCode!=0)
			{
				SimpleDateFormat df= new SimpleDateFormat("dd/MM/yyyy");
				
				String[] AllowDedCmprCol = {"allwDedCode","langId"};
				Object[] AllowDedColValues = {reqPayComponentCode,1};
				Object[] AllowDedColValuesSecLang = {reqPayComponentCode,1};
				
				hrPayCompMst = payCompDAO.getListByColumnAndValue(AllowDedCmprCol, AllowDedColValues).get(0);
				hrPayCompMstSecLang = payCompDAO.getListByColumnAndValue(AllowDedCmprCol, AllowDedColValuesSecLang).get(0);
				
				String[] commssionCmprCol = {"allwDedCode","status"};
				Object[] commssionColValues = {reqPayComponentCode,1};
				
				List<HrPayAllowDedCommissionRlt> commissionRltLst = commissionRltDAO.getListByColumnAndValue(commssionCmprCol, commssionColValues);
				 
				String commission = "";
				for(int i=0;i<commissionRltLst.size();i++)
				{
					if(i==0)
					commission = String.valueOf(commissionRltLst.get(i).getCommissionId());
					else
						commission=commission+","+commissionRltLst.get(i).getCommissionId();
				}
				
				
				String payCompStartDate = df.format(hrPayCompMst.getStartDate());
				
				objectArgs.put("hrPayCompMst", hrPayCompMst);
				objectArgs.put("editFlag", editFlag);
				objectArgs.put("payCompStartDate", payCompStartDate);
				objectArgs.put("reqPayComponentCode", reqPayComponentCode);
				objectArgs.put("payCompShortNameSecLang", hrPayCompMstSecLang.getAllowDedShrtName());
				objectArgs.put("payCompNameSecLang", hrPayCompMstSecLang.getAllowDedName());
				objectArgs.put("commissionRltLst", commission);
			}
			
			//List payCommissionList = payCompDAO.getPayCommisions(langId,0);
			
			//objectArgs.put("payCommissionList", payCommissionList);
			resObj.setResultValue(objectArgs);
			resObj.setViewName("adminAddPayComponent");
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen add or update Pay Component Error in PayComponentMasterServiceImpl--->addPayComponent", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	

@SuppressWarnings("unchecked")
public ResultObject submitPayComponent(Map<String, Object> objectArgs) {
	
	logger.info("PayComponentMasterServiceImpl--->submitPayComponent method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
		
		int langId = Integer.parseInt(loginMap.get("langId").toString());
		//CommonUserVO commonUserVO = (CommonUserVO)loginMap.get("commonUserVO");	
		
		//long langId = commonUserVO.getLangId();
		
		ResourceBundle resourceBundle;
		if(langId==2)
		resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_hi");
		else
			resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_en_US");
		
		objectArgs.put("MESSAGE",resourceBundle.getString("admin.record_saved_msg"));
		
		//long loggedInUserId=commonUserVO.getUserId();
		//long loggedInPostId=commonUserVO.getLoggedInPostVO().getPostCode();
		
		long loggedInUserId = Long.parseLong(loginMap.get("userId").toString());
		long loggedInPostId = Long.parseLong(loginMap.get("loggedInPost").toString());
		
		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		OrgUserMst orgUserMstLoggedin = (OrgUserMst) orgUserMstDaoImpl.read(Long.valueOf(loggedInUserId));
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgPostMst orgPostMstLoggedIn = (OrgPostMst) orgPostMstDaoImpl.read(Long.valueOf(loggedInPostId));
				
		String payComponentShortName=(String) objectArgs.get("payComponentShortName");
		String payComponentShortNameSecLang = (String)objectArgs.get("payComponentShortNameSecLang");
		String payComponentName=(String) objectArgs.get("payComponentName");
		String payComponentNameSecLang=(String) objectArgs.get("payComponentNameSecLang");
		int componentType=Integer.parseInt(objectArgs.get("componentType").toString());
		String[] payComponentCommissionList = (String[]) objectArgs.get("payComponentCommissionList");
		int otherAllowRecovPartFlag=Integer.parseInt(objectArgs.get("otherAllowRecovPartFlag").toString());
		String componentHead=(String) objectArgs.get("componentHead");
		int displayOrder=Integer.parseInt(objectArgs.get("displayOrder").toString());
		int displaySubOrder=Integer.parseInt(objectArgs.get("displaySubOrder").toString());
		int mergeColumnFlag=Integer.parseInt(objectArgs.get("mergeColumnFlag").toString());
		int changeReportOrder=Integer.parseInt(objectArgs.get("changeReportOrder").toString());
		int taxExemptedFlag=Integer.parseInt(objectArgs.get("taxExemptedFlag").toString());
		int taxExemptionSection=Integer.parseInt(objectArgs.get("taxExemptionSection").toString());
		int ruleFlag=Integer.parseInt(objectArgs.get("ruleFlag").toString());
		int deductionType=Integer.parseInt(objectArgs.get("deductionType").toString());
		String startDate=(String) objectArgs.get("startDate");
		int componentStatus=Integer.parseInt(objectArgs.get("componentStatus").toString());

		String editFlag=(String) objectArgs.get("editFlag");
		long reqPayComponentCode=Long.parseLong(objectArgs.get("reqPayComponentCode").toString());
		int rdoComponentUsedInFormula=Integer.parseInt(objectArgs.get("rdoComponentUsedInFormula").toString());
		
		HrPayAllowDedMst hrPayAllowDedMst = new HrPayAllowDedMst();
		HrPayAllowDedMst hrPayAllowDedMstSecLang = new HrPayAllowDedMst();

		PayComponentMasterDAO payCompDAO = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class, serv.getSessionFactory());
		PayComponentCommissionRltDAO commissionRltDAO = new PayComponentCommissionRltDAOImpl(HrPayAllowDedCommissionRlt.class,serv.getSessionFactory());
		HrPayAllowDedCommissionRlt commissionRlt = null;
		
		
		if((otherAllowRecovPartFlag==1) && (componentType==PayrollConstants.PAYROLL_ALLOWANCE_TYPE))
		{
			HrPayAllowDedMst otherAllwMst = payCompDAO.read(PayrollConstants.PAYROLL_OTHER_ALLOWANCE_CODE);
			displayOrder = otherAllwMst.getDisplayOrder();
			displaySubOrder = otherAllwMst.getDisplaySubOrder();
			mergeColumnFlag=-1;
		}
		else if((otherAllowRecovPartFlag==1) && (componentType==PayrollConstants.PAYROLL_DEDUCTION_TYPE))
		{
			HrPayAllowDedMst otherRecovMst = payCompDAO.read(PayrollConstants.PAYROLL_OTHER_RECOVERIES_CODE);
			displayOrder = otherRecovMst.getDisplayOrder();
			displaySubOrder = otherRecovMst.getDisplaySubOrder();
			mergeColumnFlag=-1;
		}
		
		long rltId=0;
		
		if(!editFlag.equals("Y"))
		{
			int updFlag=0;
			if(mergeColumnFlag==0)
			{
				updFlag=payCompDAO.chkVacantDisplayOrder(displayOrder, 0,0);
				if(updFlag!=0)
				payCompDAO.updateDisplayOrder(displayOrder);
			}
			else if(mergeColumnFlag==1)
			{
				updFlag=payCompDAO.chkVacantDisplayOrder(displayOrder, displaySubOrder,0);
				if(updFlag!=0)
				payCompDAO.updateDisplaySubOrder(displayOrder, displaySubOrder);
			}
			
			
		long allowDedId=0;
		long allowDedIdSecLang=0;
		long allowDedCode=0;
		allowDedId= IDGenerateDelegate.getNextIdWoDbidLocationIdByDefaultLocation("HR_PAY_ALLOW_DED_MST", objectArgs);
		
		allowDedCode=allowDedId;
		hrPayAllowDedMst.setAllowDedId(allowDedId);
		hrPayAllowDedMst.setAllowDedName(payComponentName);
		hrPayAllowDedMst.setAllowDedShrtName(payComponentShortName);
		hrPayAllowDedMst.setAllwDedCode(allowDedCode);
		hrPayAllowDedMst.setAgTresry(deductionType);
		hrPayAllowDedMst.setType(componentType);
		hrPayAllowDedMst.setDisplayOrder(displayOrder);
		hrPayAllowDedMst.setDisplaySubOrder(displaySubOrder);
		hrPayAllowDedMst.setTaxExemptedFlag(taxExemptedFlag);
		hrPayAllowDedMst.setTaxExemptionSection(taxExemptionSection);
		hrPayAllowDedMst.setCrtdDate(new Date());
		hrPayAllowDedMst.setStartDate(StringUtility.convertStringToDate(startDate));
		
		hrPayAllowDedMst.setLangId(1);
		hrPayAllowDedMst.setStatus(componentStatus);
		hrPayAllowDedMst.setTrnCounter(new Integer(1));
		
		hrPayAllowDedMst.setComponentHead(componentHead);
		hrPayAllowDedMst.setRuleBasedFlag(ruleFlag);
		hrPayAllowDedMst.setOtherAllowRecov(otherAllowRecovPartFlag);
		
		hrPayAllowDedMst.setOrgUserMstBycrtdBy(orgUserMstLoggedin);
		hrPayAllowDedMst.setOrgPostMstBycrtdByPost(orgPostMstLoggedIn);
		hrPayAllowDedMst.setOrgUserMstByupdatedBy(orgUserMstLoggedin);
		hrPayAllowDedMst.setUsedInOtherCalculation(rdoComponentUsedInFormula);
		hrPayAllowDedMst.setOrgPostMstByupdatedByPost(orgPostMstLoggedIn);
		
		payCompDAO.create(hrPayAllowDedMst);
		
		allowDedIdSecLang= IDGenerateDelegate.getNextIdWoDbidLocationIdByDefaultLocation("HR_PAY_ALLOW_DED_MST", objectArgs);
		
		hrPayAllowDedMstSecLang.setAllowDedId(allowDedIdSecLang);
		hrPayAllowDedMstSecLang.setAllowDedShrtName(payComponentShortNameSecLang);
		hrPayAllowDedMstSecLang.setAllowDedName(payComponentNameSecLang);
		
		hrPayAllowDedMstSecLang.setAllwDedCode(allowDedCode);
		hrPayAllowDedMstSecLang.setAgTresry(deductionType);
		hrPayAllowDedMstSecLang.setType(componentType);
		hrPayAllowDedMstSecLang.setDisplayOrder(displayOrder);
		hrPayAllowDedMstSecLang.setDisplaySubOrder(displaySubOrder);
		hrPayAllowDedMstSecLang.setTaxExemptedFlag(taxExemptedFlag);
		hrPayAllowDedMstSecLang.setTaxExemptionSection(taxExemptionSection);
		hrPayAllowDedMstSecLang.setCrtdDate(new Date());
		
		hrPayAllowDedMstSecLang.setStartDate(StringUtility.convertStringToDate(startDate));
		
		hrPayAllowDedMstSecLang.setLangId(2);
		hrPayAllowDedMstSecLang.setStatus(componentStatus);
		hrPayAllowDedMstSecLang.setTrnCounter(new Integer(1));
		
		hrPayAllowDedMstSecLang.setComponentHead(componentHead);
		hrPayAllowDedMstSecLang.setRuleBasedFlag(ruleFlag);
		hrPayAllowDedMstSecLang.setOtherAllowRecov(otherAllowRecovPartFlag);
		
		hrPayAllowDedMstSecLang.setOrgUserMstBycrtdBy(orgUserMstLoggedin);
		hrPayAllowDedMstSecLang.setOrgPostMstBycrtdByPost(orgPostMstLoggedIn);
		hrPayAllowDedMstSecLang.setOrgUserMstByupdatedBy(orgUserMstLoggedin);
		hrPayAllowDedMstSecLang.setOrgPostMstByupdatedByPost(orgPostMstLoggedIn);
		
		
		payCompDAO.create(hrPayAllowDedMstSecLang);
		
		
		for(int cnt=0;cnt<payComponentCommissionList.length;cnt++)
		{
			commissionRlt = new HrPayAllowDedCommissionRlt();
			rltId= IDGenerateDelegate.getNextIdWoDbidLocationIdByDefaultLocation("HR_PAY_ALLOWDED_COMSN_RLT", objectArgs);
			
			commissionRlt.setRltId(rltId);
			commissionRlt.setCommissionId(Long.parseLong(payComponentCommissionList[cnt]));
			commissionRlt.setAllwDedCode(allowDedCode);
			commissionRlt.setStatus(1);
			commissionRlt.setCrtdDate(new Date());
			commissionRlt.setUpdDate(new Date());
			commissionRlt.setOrgPostMstBycrtdByPost(orgPostMstLoggedIn);
			commissionRlt.setOrgPostMstByupdatedByPost(orgPostMstLoggedIn);
			commissionRlt.setOrgUserMstBycrtdBy(orgUserMstLoggedin);
			commissionRlt.setOrgUserMstByupdatedBy(orgUserMstLoggedin);
			commissionRlt.setTrnCounter(new Integer(1));
			
			commissionRltDAO.create(commissionRlt);
		}
		objectArgs.put("MESSAGECODE",300005);
		}
		else if(editFlag.equals("Y"))
		{
			int updFlag=0;
			if(changeReportOrder==1)
			{
				
				if(mergeColumnFlag==0)
				{
					updFlag=payCompDAO.chkVacantDisplayOrder(displayOrder, 0,reqPayComponentCode);
					if(updFlag!=0)
					payCompDAO.updateDisplayOrder(displayOrder);
				}
				else if(mergeColumnFlag==1)
				{
					updFlag=payCompDAO.chkVacantDisplayOrder(displayOrder, displaySubOrder,reqPayComponentCode);
					if(updFlag!=0)
					payCompDAO.updateDisplaySubOrder(displayOrder, displaySubOrder);
				}
			}
			
			String[] AllowDedCmprCol = {"allwDedCode","langId"};
			Object[] AllowDedColValues = {reqPayComponentCode,1};
			Object[] AllowDedColValuesSecLang = {reqPayComponentCode,2};
			
			hrPayAllowDedMst = payCompDAO.getListByColumnAndValue(AllowDedCmprCol, AllowDedColValues).get(0);
			hrPayAllowDedMstSecLang = payCompDAO.getListByColumnAndValue(AllowDedCmprCol, AllowDedColValuesSecLang).get(0);

			hrPayAllowDedMst.setAllowDedName(payComponentName);
			hrPayAllowDedMst.setAllowDedShrtName(payComponentShortName);
			hrPayAllowDedMst.setAgTresry(deductionType);
			hrPayAllowDedMst.setType(componentType);
			hrPayAllowDedMst.setDisplayOrder(displayOrder);
			hrPayAllowDedMst.setDisplaySubOrder(displaySubOrder);
			hrPayAllowDedMst.setTaxExemptedFlag(taxExemptedFlag);
			hrPayAllowDedMst.setTaxExemptionSection(taxExemptionSection);
			hrPayAllowDedMst.setStartDate(StringUtility.convertStringToDate(startDate));
			hrPayAllowDedMst.setStatus(componentStatus);
			hrPayAllowDedMst.setComponentHead(componentHead);
			hrPayAllowDedMst.setRuleBasedFlag(ruleFlag);
			hrPayAllowDedMst.setOtherAllowRecov(otherAllowRecovPartFlag);
			hrPayAllowDedMst.setOrgUserMstByupdatedBy(orgUserMstLoggedin);
			hrPayAllowDedMst.setOrgPostMstByupdatedByPost(orgPostMstLoggedIn);
			hrPayAllowDedMst.setUsedInOtherCalculation(rdoComponentUsedInFormula);
			hrPayAllowDedMst.setUpdDate(new Date());
			
			hrPayAllowDedMstSecLang.setAllowDedName(payComponentNameSecLang);
			hrPayAllowDedMstSecLang.setAllowDedShrtName(payComponentShortNameSecLang);
			hrPayAllowDedMstSecLang.setAgTresry(deductionType);
			hrPayAllowDedMstSecLang.setType(componentType);
			hrPayAllowDedMstSecLang.setDisplayOrder(displayOrder);
			hrPayAllowDedMstSecLang.setDisplaySubOrder(displaySubOrder);
			hrPayAllowDedMstSecLang.setTaxExemptedFlag(taxExemptedFlag);
			hrPayAllowDedMstSecLang.setTaxExemptionSection(taxExemptionSection);
			hrPayAllowDedMstSecLang.setStartDate(StringUtility.convertStringToDate(startDate));
			hrPayAllowDedMstSecLang.setStatus(componentStatus);
			hrPayAllowDedMstSecLang.setComponentHead(componentHead);
			hrPayAllowDedMstSecLang.setRuleBasedFlag(ruleFlag);
			hrPayAllowDedMstSecLang.setOtherAllowRecov(otherAllowRecovPartFlag);
			hrPayAllowDedMstSecLang.setOrgUserMstByupdatedBy(orgUserMstLoggedin);
			hrPayAllowDedMstSecLang.setOrgPostMstByupdatedByPost(orgPostMstLoggedIn);
			hrPayAllowDedMstSecLang.setUpdDate(new Date());

			
			payCompDAO.update(hrPayAllowDedMst);
			payCompDAO.update(hrPayAllowDedMstSecLang);
			
			//update commission mapping
			
			List<HrPayAllowDedCommissionRlt> commissionRltMapdLst = commissionRltDAO.getListByColumnAndValue("allwDedCode", reqPayComponentCode);
			
			int temp1=0;
			String[] temp2=new String[payComponentCommissionList.length];
			for(int j=0;j<commissionRltMapdLst.size();j++)
			{
				temp1=0;
				commissionRlt = new HrPayAllowDedCommissionRlt();
				commissionRlt = commissionRltMapdLst.get(j);
				for(int k=0;k<payComponentCommissionList.length;k++)
				{
					if(commissionRlt.getCommissionId()==Long.parseLong(payComponentCommissionList[k]))
					{
						temp1=1;
						temp2[k]=payComponentCommissionList[k];
					}
				}
				if(temp1==1 && commissionRlt.getStatus()==0)
				{
					commissionRlt.setStatus(1);
					commissionRltDAO.update(commissionRlt);
				}
				if(temp1==0 && commissionRlt.getStatus()==1)
				{
					commissionRlt.setStatus(0);
					commissionRltDAO.update(commissionRlt);
				}
			}
			
			int temp3=0;
			for(int i=0;i<payComponentCommissionList.length;i++)
			{
				temp3=0;
				for(int x=0;x<temp2.length;x++)
				{
					if(temp2[x]==payComponentCommissionList[i])
					{
						temp3=1;
					}
				}
				if(temp3==0)
				{
					commissionRlt = new HrPayAllowDedCommissionRlt();
					rltId= IDGenerateDelegate.getNextIdWoDbidLocationIdByDefaultLocation("HR_PAY_ALLOWDED_COMSN_RLT", objectArgs);
						
					commissionRlt.setRltId(rltId);
					commissionRlt.setCommissionId(Long.parseLong(payComponentCommissionList[i]));
					commissionRlt.setAllwDedCode(reqPayComponentCode);
					commissionRlt.setStatus(1);
					commissionRlt.setCrtdDate(new Date());
					commissionRlt.setUpdDate(new Date());
					commissionRlt.setOrgPostMstBycrtdByPost(orgPostMstLoggedIn);
					commissionRlt.setOrgPostMstByupdatedByPost(orgPostMstLoggedIn);
					commissionRlt.setOrgUserMstBycrtdBy(orgUserMstLoggedin);
					commissionRlt.setOrgUserMstByupdatedBy(orgUserMstLoggedin);
					commissionRlt.setTrnCounter(new Integer(1));
						
					commissionRltDAO.create(commissionRlt);
				}
			}

			objectArgs.put("MESSAGECODE",300006);
		}
		
		resObj.setResultValue(objectArgs);
		resObj.setViewName("adminAddPayComponent");
		resObj.setResultCode(ErrorConstants.SUCCESS);
		resObj.setResultValue(objectArgs);
	} catch (Exception ex) {
		logger.error("Error is: "+ ex.getMessage());
		resObj.setThrowable(ex);
		logger.error("Admin Screen Submitting Pay Component Error in PayComponentMasterServiceImpl--->submitPayComponent", ex);
		resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
  }
@SuppressWarnings("unchecked")
public ResultObject retrieveRuleBasedPayCompList(Map<String, Object> objectArgs) {
		
		logger.info("PayComponentMasterServiceImpl--->retrieveRuleBasedPayCompList method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			PayComponentMasterDAO payCompDAO = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class, serv.getSessionFactory());
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			int langId = Integer.parseInt(loginMap.get("langId").toString());
			List<HrPayAllowDedMst> ruleBasedpayCompList = payCompDAO.getRuleBasedPayCompForViewList(langId);
			
			objectArgs.put("ruleBasedpayCompList",ruleBasedpayCompList);
			resObj.setViewName("adminViewRuleBasedPayComponent");
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen view Pay Component Error in PayComponentMasterServiceImpl--->retrieveRuleBasedPayCompList", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
@SuppressWarnings("unchecked")
public ResultObject retrievePayCompRuleList(Map<String, Object> objectArgs) {
		
		logger.info("PayComponentMasterServiceImpl--->retrievePayCompRuleList method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			
			PayComponentMasterDAO payCompDAO = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class, serv.getSessionFactory());
			PayComponentRuleGroupMstDAO ruleGrpMstDao =  new PayComponentRuleGroupMstDAOImpl(HrPayRuleGrpMst.class,serv.getSessionFactory());
			PayComponentRuleGroupParamRltDAO ruleGrpParamRltDao =  new PayComponentRuleGroupParamRltDAOImpl(HrPayRuleGrpParamRlt.class,serv.getSessionFactory());
			
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			//CommonUserVO commonUserVO = (CommonUserVO)loginMap.get("commonUserVO");
			//int langId = Integer.parseInt(commonUserVO.getLangId().toString());
			int langId = Integer.parseInt(loginMap.get("langId").toString());
			long reqPayComponentCode=Long.parseLong(objectArgs.get("reqPayComponentCode").toString());
			
			String[] AllowDedCmprCol = {"allwDedCode","langId"};
			Object[] AllowDedColValues = {reqPayComponentCode,langId};
			HrPayAllowDedMst payComp = payCompDAO.getListByColumnAndValue(AllowDedCmprCol, AllowDedColValues).get(0);
			
			String[] ruleGrpMstCmprCol = {"allwDedCode","ruleStatus"};
			Object[] ruleGrpMstColValues = {reqPayComponentCode,1};
			
			
			List<HrPayRuleGrpMst> payCompRuleGrpLst = ruleGrpMstDao.getListByColumnAndValue(ruleGrpMstCmprCol, ruleGrpMstColValues);
			List<HrPayRuleGrpParamRlt> ruleGrpParamRltLst = ruleGrpParamRltDao.getPayCompRuleParamMpgList(reqPayComponentCode);
			
			List<HrPayCompViewRuleCustomVO> payCompRuleList =getRuleViewDtls(payCompRuleGrpLst, ruleGrpParamRltLst, serv, langId);

			
			objectArgs.put("payCompRuleList",payCompRuleList);
			objectArgs.put("reqPayComponentCode",reqPayComponentCode);
			objectArgs.put("payComp",payComp);
			resObj.setViewName("adminViewPayComponentRuleList");
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen view Pay Component Error in PayComponentMasterServiceImpl--->retrievePayCompRuleList", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
@SuppressWarnings("unchecked")
public List<HrPayCompViewRuleCustomVO> getRuleViewDtls(List<HrPayRuleGrpMst> payCompRuleGrpLst,List<HrPayRuleGrpParamRlt> ruleGrpParamRltLst,ServiceLocator serv,long langId)
{
	
	List<HrPayCompViewRuleCustomVO> ruleViewList = new ArrayList();
	try{
		
		ResourceBundle resourceBundle;
		if(langId==2)
		resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_hi");
		else
			resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_en_US");
			
		HrPayCompViewRuleCustomVO ruleViewVO= null;
		
		
		PayComponentMasterDAO payCompDAO = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class, serv.getSessionFactory());
		
		//CmnCityMstDAO cityMstDao = new CmnCityMstDAOImpl(CmnCityMst.class,serv.getSessionFactory());
		CmnLookupMstDAOImpl cityMstDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		OrgDepartmentMstDao deptMstDAO = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class, serv.getSessionFactory());
		OrgGradeMstDao gradeMstDAO = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
		//EmpCtgryObjRltDAOImpl empCtgryDao = new EmpCtgryObjRltDAOImpl(HrPayEmpCtgryObjRlt.class,serv.getSessionFactory());
		CmnLocationMstDao locMstDao = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
		OrgDesignationMstDao desgnDao = new OrgDesignationMstDaoImpl(OrgDesignationMst.class,serv.getSessionFactory());
		CmnLookupMstDAO lookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		HrEisScaleMstDao scaleMstDAO = new HrEisScaleMstDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());
	
		OrgDepartmentMst deptMst =  new OrgDepartmentMst();
		//CmnCityMst cityMst = new CmnCityMst();
		CmnLookupMst cityMst = new CmnLookupMst();
		OrgGradeMst gradeMst = new OrgGradeMst();
		//HrPayEmpCtgryObjRlt empCtgryRlt = new HrPayEmpCtgryObjRlt();
		CmnLocationMst locMst = new CmnLocationMst();
		OrgDesignationMst desgnMst = new OrgDesignationMst();
		CmnLookupMst lookupMst =  new CmnLookupMst();
		
		String[] deptCmprCol = {"depCode","langId"};
		String[] locCmprCol = {"locationCode","cmnLanguageMst.langId"};
		String[] gradeCmprCol = {"gradeCode","cmnLanguageMst.langId"};
		//String[] empCtgryCmprCol = {"ctgryCode","langId"};
		String[] desgnCmprCol = {"dsgnCode","cmnLanguageMst.langId"};
		String[] cityCmprCol = {"lookupId","cmnLanguageMst.langId"};
		String[] quarterCmprCol = {"lookupCode","cmnLanguageMst.langId"};
		//String[] scaleCmprCol = {"scaleCode","cmnLanguageMst.langId"};
		String[] scaleCmprCol = {"scaleId","cmnLanguageMst.langId"};
		String[] payCompCmprCol = {"allwDedCode","langId"};
		
		HrEisScaleMst scaleMst = new HrEisScaleMst();
	
		for(HrPayRuleGrpMst ruleGrpMst:payCompRuleGrpLst)
		{
		
			
		ruleViewVO= new HrPayCompViewRuleCustomVO();
		ruleViewVO.setDeptName(resourceBundle.getString("admin.select_all"));
		ruleViewVO.setLocationName(resourceBundle.getString("admin.select_all"));
		ruleViewVO.setClassName(resourceBundle.getString("admin.select_all"));
		//ruleViewVO.setEmpCatgryName(resourceBundle.getString("admin.select_all"));
		ruleViewVO.setGenderName(resourceBundle.getString("admin.select_all"));
		ruleViewVO.setDesgnName(resourceBundle.getString("admin.select_all"));
		ruleViewVO.setPostTypeName(resourceBundle.getString("admin.select_all"));
		ruleViewVO.setCityName(resourceBundle.getString("admin.select_all"));
		ruleViewVO.setQuarterTypeName(resourceBundle.getString("admin.select_all"));
		ruleViewVO.setBasicLowLmtVal("--");
		ruleViewVO.setBasicUprLmtVal("--");
		ruleViewVO.setGpLowLmtVal("--");
		ruleViewVO.setGpUprLmtVal("--");
		ruleViewVO.setRuleAmount("--");
		ruleViewVO.setScaleLowLmtVal("--");
		ruleViewVO.setScaleUprLmtVal("--");
		ruleViewVO.setRuleFormulaDisplay("--");
		
		ruleViewVO.setGrossLowLmtVal("--");
		ruleViewVO.setGrossUprLmtVal("--");
		
		ruleViewVO.setDeptCode("-1");
		ruleViewVO.setLocationCode("-1");
		ruleViewVO.setClassCode("-1");
		//ruleViewVO.setEmpCatgryCode(-1);
		ruleViewVO.setGenderCode(-1);
		ruleViewVO.setDesgnCode("-1");
		ruleViewVO.setPostTypeCode(-1);
		ruleViewVO.setPhyChallengedCode(-1);
		ruleViewVO.setPhyChallenged(resourceBundle.getString("admin.no"));
		ruleViewVO.setCityCode("-1");
		ruleViewVO.setQuarterTypeCode(-1);
		
		for(HrPayRuleGrpParamRlt ruleGrpRlt:ruleGrpParamRltLst)
		{
			if(ruleGrpRlt.getRuleGrpId().getRuleGrpId()==ruleGrpMst.getRuleGrpId())
			{
				//to add individual rule row starts
				Object[] colValues={ruleGrpRlt.getParamValue(),langId};
				logger.info(" ruleGrpRlt.getParamValue() "+ruleGrpRlt.getParamValue()+ " lang Id is "+langId);
				
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_DOJ_YEAR_ID)
				{
					ruleViewVO.setDojYearId(ruleGrpRlt.getParamId().getParamId());
					ruleViewVO.setDojYear(ruleGrpRlt.getParamValue());
				}
				
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_DEPT)
				{
					deptMst=deptMstDAO.getListByColumnAndValue(deptCmprCol, colValues).get(0);
					ruleViewVO.setDeptName(deptMst.getDepName());
					ruleViewVO.setDeptCode(deptMst.getDepCode());
				}
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_OFFICE)
				{
					locMst=locMstDao.getListByColumnAndValue(locCmprCol, colValues).get(0);
					ruleViewVO.setLocationName(locMst.getLocName());
					ruleViewVO.setLocationCode(locMst.getLocationCode());
				}
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_GRADE)
				{
					gradeMst=gradeMstDAO.getListByColumnAndValue(gradeCmprCol, colValues).get(0);
					ruleViewVO.setClassName(gradeMst.getGradeName());
					ruleViewVO.setClassCode(gradeMst.getGradeCode());
				}
				/*if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_PAYROLLEMPCTGRY)
				{
					Object[] empCtgryColValues={Integer.parseInt(ruleGrpRlt.getParamValue()),(int)langId};
					empCtgryRlt=empCtgryDao.getListByColumnAndValue(empCtgryCmprCol, empCtgryColValues).get(0);
					ruleViewVO.setEmpCatgryName(empCtgryRlt.getCtgryDispName());
					ruleViewVO.setEmpCatgryCode(empCtgryRlt.getCtgryCode());
				}*/
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_GENDER)
				{
					if(ruleGrpRlt.getParamValue().equals(String.valueOf(PayrollConstants.PAYROLL_GENDER_MALE)))
					{
						ruleViewVO.setGenderName(resourceBundle.getString("admin.male"));
						ruleViewVO.setGenderCode(PayrollConstants.PAYROLL_GENDER_MALE);
					}
					else if(ruleGrpRlt.getParamValue().equals(String.valueOf(PayrollConstants.PAYROLL_GENDER_FEMALE)))
					{
						ruleViewVO.setGenderName(resourceBundle.getString("admin.female"));
						ruleViewVO.setGenderCode(PayrollConstants.PAYROLL_GENDER_FEMALE);
					}
					
					
				}
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_DESGN)
				{
					desgnMst=desgnDao.getListByColumnAndValue(desgnCmprCol, colValues).get(0);
					ruleViewVO.setDesgnName(desgnMst.getDsgnName());
					ruleViewVO.setDesgnCode(desgnMst.getDsgnCode());
				}
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_POSTTYPE)
				{
					if(ruleGrpRlt.getParamValue().equals(String.valueOf(PayrollConstants.PAYROLL_PERMANENT)))
					{
						ruleViewVO.setPostTypeName(resourceBundle.getString("admin.permanent"));
						ruleViewVO.setPostTypeCode(PayrollConstants.PAYROLL_PERMANENT);
					}
					else if(ruleGrpRlt.getParamValue().equals(String.valueOf(PayrollConstants.PAYROLL_TEMPORARY)))
					{
						ruleViewVO.setPostTypeName(resourceBundle.getString("admin.temporary"));
						ruleViewVO.setPostTypeCode(PayrollConstants.PAYROLL_TEMPORARY);
					}
				}
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_PHYCHALLENGED)
				{
//					if(ruleGrpRlt.getParamValue().equals(String.valueOf(PayrollConstants.PAYROLL_PHYCHALLENGED_NO)))
//					{
//						ruleViewVO.setPhyChallenged(resourceBundle.getString("admin.no"));
//						ruleViewVO.setPhyChallengedCode(PayrollConstants.PAYROLL_PHYCHALLENGED_NO);
//					}
//					else if(ruleGrpRlt.getParamValue().equals(String.valueOf(PayrollConstants.PAYROLL_PHYCHALLENGED_YES)))
					if(ruleGrpRlt.getParamValue().equals(String.valueOf(PayrollConstants.PAYROLL_PHYCHALLENGED_YES)))
					{
						ruleViewVO.setPhyChallenged(resourceBundle.getString("admin.yes"));
						ruleViewVO.setPhyChallengedCode(PayrollConstants.PAYROLL_PHYCHALLENGED_YES);
					}
				}
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_CITY)
				{
					//cityMst=cityMstDao.getListByColumnAndValue(cityCmprCol, colValues).get(0);
					//cityMst=cityMstDao.getListByColumnAndValue("lookupId", ruleGrpRlt.getParamValue()).get(0);
					cityMst=cityMstDao.read(Long.valueOf(ruleGrpRlt.getParamValue()));
					//lookupId
					ruleViewVO.setCityName(cityMst.getLookupName());
					ruleViewVO.setCityCode( new Long(cityMst.getLookupId()).toString());
				}
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_QUARTERTYPE)
				{
					Object[] quarterColValues={Long.parseLong(ruleGrpRlt.getParamValue()),langId};
					lookupMst=lookupDao.getListByColumnAndValue(quarterCmprCol, quarterColValues).get(0);
					ruleViewVO.setQuarterTypeName(lookupMst.getLookupName());
					ruleViewVO.setQuarterTypeCode(lookupMst.getLookupId());
				}
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_BASICPAY)
				{
					ruleViewVO.setBasicLowLmtVal(ruleGrpRlt.getParamLowerValue().toString());
					ruleViewVO.setBasicUprLmtVal((ruleGrpRlt.getParamUpperValue().longValue()==-1?"--":ruleGrpRlt.getParamUpperValue().toString()));
				}
				
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_GROSSPAY)
				{
					ruleViewVO.setGrossLowLmtVal(ruleGrpRlt.getParamLowerValue().toString());
					ruleViewVO.setGrossUprLmtVal((ruleGrpRlt.getParamUpperValue().longValue()==-1?"--":ruleGrpRlt.getParamUpperValue().toString()));
				}
				
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_GRADEPAY)
				{
					ruleViewVO.setGpLowLmtVal(ruleGrpRlt.getParamLowerValue().toString());
					ruleViewVO.setGpUprLmtVal((ruleGrpRlt.getParamUpperValue().longValue()==-1?"--":ruleGrpRlt.getParamUpperValue().toString()));
				}
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_PAYSCALE)
				{
					if(ruleGrpRlt.getParamLowerValue().longValue()!=0)
					{
						Object[] scaleLowerColValues={ruleGrpRlt.getParamLowerValue().longValue(),langId};
						scaleMst=scaleMstDAO.getListByColumnAndValue(scaleCmprCol, scaleLowerColValues).get(0);
						if(scaleMst.getHrPayCommissionMst().getId()==PayrollConstants.PAYROLL_MP_SIXTHCOMMMISSION_LOOKUPID)
					ruleViewVO.setScaleLowLmtVal(scaleMst.getScaleStartAmt()+"-"+scaleMst.getScaleEndAmt()+"-"+scaleMst.getScaleGradePay());
						else
							ruleViewVO.setScaleLowLmtVal(scaleMst.getScaleStartAmt()+"-"+scaleMst.getScaleEndAmt()+"-"+scaleMst.getScaleIncrAmt());
					}
					if(ruleGrpRlt.getParamUpperValue().longValue()!=-1)
					{
						Object[] scaleUpperColValues={ruleGrpRlt.getParamUpperValue().longValue(),langId};
						scaleMst=scaleMstDAO.getListByColumnAndValue(scaleCmprCol, scaleUpperColValues).get(0);
						if(scaleMst.getHrPayCommissionMst().getId()==PayrollConstants.PAYROLL_MP_SIXTHCOMMMISSION_LOOKUPID)
					ruleViewVO.setScaleUprLmtVal(scaleMst.getScaleStartAmt()+"-"+scaleMst.getScaleEndAmt()+"-"+scaleMst.getScaleGradePay());
						else
							ruleViewVO.setScaleUprLmtVal(scaleMst.getScaleStartAmt()+"-"+scaleMst.getScaleEndAmt()+"-"+scaleMst.getScaleIncrAmt());
					
					}
				}
				if(ruleGrpRlt.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_PAYCOMSN)
				{
					ruleViewVO.setPayCommissionName(ruleGrpRlt.getParamValue()+"th");
					ruleViewVO.setPayCommissionValue(Integer.parseInt(ruleGrpRlt.getParamValue()));
				}
			}
			
		}
		if(ruleGrpMst.getReturnType()==PayrollConstants.PAYROLL_RETURNTYPE_FIXED)
		ruleViewVO.setRuleAmount(ruleGrpMst.getReturnValue().toString());
		if(ruleGrpMst.getReturnType()==PayrollConstants.PAYROLL_RETURNTYPE_FORMULA)
		{
			String formula = ruleGrpMst.getReturnFormula();
			String[] formulaComp = formula.split("P");
			String formulaDisplay="";
			
			for(int i=0;i<formulaComp.length;i++)
			{
				
			
				Object[] formulaColValues={Long.parseLong(formulaComp[i]),(int)langId};
				if(i==0)
				formulaDisplay=(payCompDAO.getRuleBasedPayCompForViewListForMaha(langId, Long.parseLong(formulaComp[i]))).get(0).getAllowDedName();
				else
					formulaDisplay=formulaDisplay+"+"+(payCompDAO.getRuleBasedPayCompForViewListForMaha(langId, Long.parseLong(formulaComp[i]))).get(0).getAllowDedName();
				
			}
			formulaDisplay=ruleGrpMst.getReturnValue()+"*("+formulaDisplay+")/100";
			ruleViewVO.setRuleFormulaDisplay(formulaDisplay);
		}
		
		ruleViewVO.setRulePercentage(ruleGrpMst.getReturnValue().toString());
		ruleViewVO.setStatus(ruleGrpMst.getRuleStatus());
		ruleViewVO.setRuleGrpId(ruleGrpMst.getRuleGrpId());
		ruleViewVO.setReturnType(ruleGrpMst.getReturnType());
		
		ruleViewList.add(ruleViewVO);
	}
	
	}
	catch(Exception ex){
		logger.error("Error is: "+ ex.getMessage());
		logger.error("Admin Screen Error in PayComponentMasterServiceImpl--->getRuleViewDtls", ex);
	}
	
	
	return ruleViewList;
}
@SuppressWarnings("unchecked")
public ResultObject addPayComponentRule(Map<String, Object> objectArgs) {
	
	logger.info("PayComponentMasterServiceImpl--->addPayComponentRule method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		
		Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
		int langId = Integer.parseInt(loginMap.get("langId").toString());
		long reqPayComponentCode=Long.parseLong(objectArgs.get("reqPayComponentCode").toString());
		
		PayrollInboundIntegrationServiceImpl inBoundIntegration = new PayrollInboundIntegrationServiceImpl();
		
		resObj = inBoundIntegration.retrieveDeptList(objectArgs);
		objectArgs = (Map<String, Object>) resObj.getResultValue();
		
		resObj = inBoundIntegration.retrieveGradeList(objectArgs);
		objectArgs = (Map<String, Object>) resObj.getResultValue();
		
		CmnCityMstDAO cityMstDao = new CmnCityMstDAOImpl(CmnCityMst.class,serv.getSessionFactory());
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		
		List<CmnLookupMst> cityList= cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("City Category", langId);
	//	List<CmnCityMst> cityList = cityMstDao.getListByColumnAndValue("cmnLanguageMst.langId", Long.valueOf(String.valueOf(langId)));
		
		//EmpCtgryObjRltDAOImpl empCtgryDao = new EmpCtgryObjRltDAOImpl(HrPayEmpCtgryObjRlt.class,serv.getSessionFactory());
		PayComponentMasterDAO payCompDAO = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class, serv.getSessionFactory());
		
		//List<HrPayEmpCtgryObjRlt> empCtgryList = empCtgryDao.getListByColumnAndValue("langId", langId);
		
		String[] AllowDedCmprCol = {"allwDedCode","langId"};
		Object[] AllowDedColValues = {reqPayComponentCode,langId};
		HrPayAllowDedMst payComp = payCompDAO.getListByColumnAndValue(AllowDedCmprCol, AllowDedColValues).get(0);
		
		String[] comsnCmprCol = {"allwDedCode","status"};
		Object[] comsnColValues = {reqPayComponentCode,1};
		PayComponentCommissionRltDAO commissionRltDAO = new PayComponentCommissionRltDAOImpl(HrPayAllowDedCommissionRlt.class,serv.getSessionFactory());
		List<HrPayAllowDedCommissionRlt> commissionMgLst = commissionRltDAO.getListByColumnAndValue(comsnCmprCol, comsnColValues);
		
	/*	CmnLookupMstDAO lookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		List<CmnLookupMst> quarterTypeLst = lookupDao.getAllChildrenByLookUpNameAndLang("Quarter type", langId);
	*/	
		PayComponentRuleGroupMstDAO ruleGrpMstDao =  new PayComponentRuleGroupMstDAOImpl(HrPayRuleGrpMst.class,serv.getSessionFactory());
		
		logger.info("see this "+reqPayComponentCode);
		List<HrPayRuleGrpMst> payCompRuleGrpLst = ruleGrpMstDao.getListByColumnAndValue("allwDedCode", reqPayComponentCode);
		
		//objectArgs.put("quarterTypeLst",quarterTypeLst);
		//if(!payCompRuleGrpLst.isEmpty())
		

		String[] yearPar = {"lookupName","cmnLanguageMst.langId"};
		Object[] yearParValues = {reqPayComponentCode,1};
		List<CmnLookupMst> yearList =cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Year", langId);
		List<CmnLookupMst>  yrList=new ArrayList<CmnLookupMst>();
		for(int r=0;r<yearList.size();r++)
		{
			if(Integer.parseInt(yearList.get(r).getLookupName())== (new Date().getYear()+1900))
			{
				yrList.add(yearList.get(r));
			}
		}
		yearList =cmnLookupMstDAOImpl.getListByColumnAndValue("lookupName", "1");
		yrList.add(yearList.get(0));
		objectArgs.put("savedRuleListSize",payCompRuleGrpLst.size());
		objectArgs.put("commissionMgLst",commissionMgLst);
		objectArgs.put("payComp",payComp);
		//objectArgs.put("empCtgryList",empCtgryList);
		objectArgs.put("reqPayComponentCode",reqPayComponentCode);
		objectArgs.put("cityList",cityList);
		objectArgs.put("yearList",yrList);
		resObj.setResultValue(objectArgs);
		resObj.setViewName("adminAddPayComponentRule");
		resObj.setResultCode(ErrorConstants.SUCCESS);
		resObj.setResultValue(objectArgs);
	} catch (Exception ex) {
		logger.error("Error is: "+ ex.getMessage());
		resObj.setThrowable(ex);
		logger.error("Admin Screen Error in PayComponentMasterServiceImpl--->addPayComponentRule", ex);
		resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
@SuppressWarnings("unchecked")
public ResultObject getOfficeFromDept(Map<String, Object> objectArgs)
{
	logger.info("PayComponentMasterServiceImpl--->getOfficeFromDept method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

try {
	PayrollInboundIntegrationServiceImpl inBoundIntegration = new PayrollInboundIntegrationServiceImpl();
	resObj = inBoundIntegration.retrieveLocListFrmDept(objectArgs);
	objectArgs = (Map<String, Object>) resObj.getResultValue();
	
	List<DdoOffice> locMstList = (List<DdoOffice>) objectArgs.get("locMstList");
	StringBuffer locFrmDeptStr = new StringBuffer();
	
	if(locMstList!=null)
	{
		for (DdoOffice locMstVO:locMstList ) {

			locFrmDeptStr.append("<OfficeFrmDept>");
			locFrmDeptStr.append("<locCode>").append("<![CDATA[").append(locMstVO.getDcpsDdoOfficeIdPk()).append("]]>").append("</locCode>");
			locFrmDeptStr.append("<locName>").append("<![CDATA[").append(locMstVO.getDcpsDdoOfficeName()).append("]]>").append("</locName>");
			locFrmDeptStr.append("</OfficeFrmDept>");
		}
	}
	
	String locFrmDeptCode = new AjaxXmlBuilder().addItem("ajax_key",locFrmDeptStr.toString()).toString();
	logger.info("OfficeFrmDept::::::::::::: " +locFrmDeptCode);
	objectArgs.put("ajaxKey", locFrmDeptCode);
	resObj.setResultValue(objectArgs);
	resObj.setViewName("ajaxData");
	resObj.setResultCode(ErrorConstants.SUCCESS);
	}
	catch (Exception ex) {
	logger.error("Error is: "+ ex.getMessage());
	resObj.setThrowable(ex);
	logger.error("Admin Screen Error in PayComponentMasterServiceImpl--->getOfficeFromDept", ex);
	resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
@SuppressWarnings("unchecked")
public ResultObject getDesgnFromGrade(Map<String, Object> objectArgs)
{
	logger.info("PayComponentMasterServiceImpl--->getDesgnFromGrade method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

try {
	
	PayrollInboundIntegrationServiceImpl inBoundIntegration = new PayrollInboundIntegrationServiceImpl();
	resObj = inBoundIntegration.retrieveDesgnListFrmGrade(objectArgs);
	objectArgs = (Map<String, Object>) resObj.getResultValue();
	
	List<OrgDesignationMst> desgnMstList = (List<OrgDesignationMst>) objectArgs.get("desgnMstList");
	StringBuffer desgnFrmGradeStr = new StringBuffer();
	
	if(desgnMstList!=null)
	{
		for (OrgDesignationMst desgnMstVO:desgnMstList) {

			desgnFrmGradeStr.append("<DesgnFrmGrade>");
			desgnFrmGradeStr.append("<desgnCode>").append("<![CDATA[").append(desgnMstVO.getDsgnCode()).append("]]>").append("</desgnCode>");
			desgnFrmGradeStr.append("<desgnName>").append("<![CDATA[").append(desgnMstVO.getDsgnName()).append("]]>").append("</desgnName>");
			desgnFrmGradeStr.append("</DesgnFrmGrade>");
		}
	}
	
	String desgnFrmGradeCode = new AjaxXmlBuilder().addItem("ajax_key",desgnFrmGradeStr.toString()).toString();
	logger.info("desgnFrmGradeCode::::::::::::: " +desgnFrmGradeCode);
	objectArgs.put("ajaxKey", desgnFrmGradeCode);
	resObj.setResultValue(objectArgs);
	resObj.setViewName("ajaxData");
	resObj.setResultCode(ErrorConstants.SUCCESS);
	}
	catch (Exception ex) {
	logger.error("Error is: "+ ex.getMessage());
	resObj.setThrowable(ex);
	logger.error("Admin Screen Error in PayComponentMasterServiceImpl--->getDesgnFromGrade", ex);
	resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
@SuppressWarnings("unchecked")
public ResultObject getScaleFromCommission(Map<String, Object> objectArgs)
{
	logger.info("PayComponentMasterServiceImpl--->getScaleFromCommission method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

try {
	
	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
	
	Long langId = Long.valueOf(loginMap.get("langId").toString());
	PayrollInboundIntegrationServiceImpl inBoundIntegration = new PayrollInboundIntegrationServiceImpl();
	resObj = inBoundIntegration.retrieveScaleListFrmPayComsn(objectArgs);
	objectArgs = (Map<String, Object>) resObj.getResultValue();
	
	List<HrEisScaleMst> scaleMstList = (List<HrEisScaleMst>) objectArgs.get("scaleMstList");
	StringBuffer scaleFrmPayComsnStr = new StringBuffer();
	
	int reqPayComponentType = Integer.parseInt(objectArgs.get("reqPayComponentType").toString());
	int payrollComsnId=Integer.parseInt(objectArgs.get("payrollComsnId").toString());
	int payComponentUsedInFormula=Integer.parseInt(objectArgs.get("payComponentUsedInFormula").toString());
	
	//fetch pay components used in formula
	if(payComponentUsedInFormula==0)
	{
	PayComponentMasterDAO payCompDao = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class,serv.getSessionFactory());
	List<HrPayAllowDedMst> payCompUsedInFormula = payCompDao.getPayCompUsedInFormula(reqPayComponentType, payrollComsnId,langId);
	
		for(HrPayAllowDedMst payComp:payCompUsedInFormula)
		{
			scaleFrmPayComsnStr.append("<FormulaPayCompFrmPayComsn>");
			scaleFrmPayComsnStr.append("<payCompCode>").append("<![CDATA[").append(payComp.getAllwDedCode()).append("]]>").append("</payCompCode>");
			scaleFrmPayComsnStr.append("<payCompName>").append("<![CDATA[").append(payComp.getAllowDedName()).append("]]>").append("</payCompName>");
			scaleFrmPayComsnStr.append("</FormulaPayCompFrmPayComsn>");
		}
	}
	//End of fetching pay components used in formula
	
	if(scaleMstList!=null)
	{
		for (HrEisScaleMst scaleMstVO:scaleMstList) {
			
			scaleFrmPayComsnStr.append("<ScaleFrmPayComsn>");
		//	scaleFrmPayComsnStr.append("<scaleCode>").append("<![CDATA[").append(scaleMstVO.getScaleCode()).append("]]>").append("</scaleCode>");
			scaleFrmPayComsnStr.append("<scaleCode>").append("<![CDATA[").append(scaleMstVO.getScaleId()).append("]]>").append("</scaleCode>");
			scaleFrmPayComsnStr.append("<scaleStartAmnt>").append("<![CDATA[").append(scaleMstVO.getScaleStartAmt()).append("]]>").append("</scaleStartAmnt>");
			scaleFrmPayComsnStr.append("<scaleEndAmnt>").append("<![CDATA[").append(scaleMstVO.getScaleEndAmt()).append("]]>").append("</scaleEndAmnt>");
			scaleFrmPayComsnStr.append("<scaleGradePay>").append("<![CDATA[").append(scaleMstVO.getScaleGradePay()).append("]]>").append("</scaleGradePay>");
			scaleFrmPayComsnStr.append("<scaleIncrAmnt>").append("<![CDATA[").append(scaleMstVO.getScaleIncrAmt()).append("]]>").append("</scaleIncrAmnt>");
			scaleFrmPayComsnStr.append("</ScaleFrmPayComsn>");
		}
	}
	
	String scaleFrmPayCommission = new AjaxXmlBuilder().addItem("ajax_key",scaleFrmPayComsnStr.toString()).toString();
	logger.info("scaleFrmPayCommission::::::::::::: " +scaleFrmPayCommission);
	objectArgs.put("ajaxKey", scaleFrmPayCommission);
	resObj.setResultValue(objectArgs);
	resObj.setViewName("ajaxData");
	resObj.setResultCode(ErrorConstants.SUCCESS);
	}
	catch (Exception ex) {
	logger.error("Error is: "+ ex.getMessage());
	resObj.setThrowable(ex);
	logger.error("Admin Screen Error in PayComponentMasterServiceImpl--->getScaleFromCommission", ex);
	resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
@SuppressWarnings("unchecked")
public ResultObject compareTosavedRules(Map<String, Object> objectArgs)
{
	logger.info("PayComponentMasterServiceImpl--->compareTosavedRules method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

try {
	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			
	//CommonUserVO commonUserVO = (CommonUserVO)loginMap.get("commonUserVO");
	long langId = Long.parseLong(loginMap.get("langId").toString());
	
	ResourceBundle resourceBundle;
	if(langId==2)
	resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_hi");
	else
		resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_en_US");
	
	long reqPayComponentCode=Long.parseLong(objectArgs.get("reqPayComponentCode").toString());
	HrPayCompRuleCustomVO payCompCustomVO=(HrPayCompRuleCustomVO)objectArgs.get("payCompCustomVO");
	//int totalParamCount = Integer.parseInt(objectArgs.get("totalParamCount").toString());
	
	PayComponentRuleGroupMstDAO ruleMstDao = new PayComponentRuleGroupMstDAOImpl(HrPayRuleGrpMst.class,serv.getSessionFactory());
	PayComponentRuleGroupParamRltDAO ruleParamRltDao = new PayComponentRuleGroupParamRltDAOImpl(HrPayRuleGrpParamRlt.class,serv.getSessionFactory());
	//PayComponentRuleParamRltDAO payCompParamDao = new PayComponentRuleParamRltDAOImpl(HrPayRuleParamRlt.class,serv.getSessionFactory());
	
	//List<HrPayRuleParamRlt> ruleParamMpgList = payCompParamDao.getListByColumnAndValue("allwDedCode", reqPayComponentCode);
	List<HrPayRuleGrpParamRlt> ruleParamMpgList = ruleParamRltDao.getPayCompRuleParamMpgList(reqPayComponentCode);
	
	boolean basicPayFlag=false;
	boolean gradePayFlag=false;
	boolean payScaleFlag=false;
	boolean grossPayFlag=false;
	
	for(HrPayRuleGrpParamRlt ruleParamMpg : ruleParamMpgList)
	{
		if(ruleParamMpg.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_BASICPAY)
			basicPayFlag=true; 
		if(ruleParamMpg.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_GRADEPAY)
			gradePayFlag=true; 
		if(ruleParamMpg.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_PAYSCALE)
			payScaleFlag=true; 
		if(ruleParamMpg.getParamId().getParamId()==PayrollConstants.PAYROLL_PARAMID_GROSSPAY)
			grossPayFlag=true; 
	}
	
	
	HrEisScaleMstDao scaleMstDAO = new HrEisScaleMstDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());
	String[] scaleCmprCol = {"scaleCode","cmnLanguageMst.langId"};
	HrEisScaleMst scaleMstCustom = new HrEisScaleMst();
	
	int compareFlag=1;
	String returnMsg=resourceBundle.getString("admin.compare_msg");
	long ruleGrpId=0;
	
	String[] ruleGrpMstCmprCol = {"allwDedCode","ruleStatus"};
	Object[] ruleGrpMstColValues = {reqPayComponentCode,1};
	
	
	List<HrPayRuleGrpMst> payCompRuleGrpLst = ruleMstDao.getListByColumnAndValue(ruleGrpMstCmprCol, ruleGrpMstColValues);
	//List<HrPayRuleGrpMst> payCompRuleGrpLst = ruleMstDao.getListByColumnAndValue("allwDedCode", reqPayComponentCode);
	//List<HrPayRuleGrpParamRlt> ruleGrpParamRltLst = ruleParamRltDao.getPayCompRuleParamMpgList(reqPayComponentCode);
	
	List<HrPayCompViewRuleCustomVO> payCompRuleList =getRuleViewDtls(payCompRuleGrpLst, ruleParamMpgList, serv, langId);
	
	long scaleStartAmnt=0;
	//long scaleEndAmnt=10000000;
	long scaleGradePay=0;
	long scaleIncrAmnt=0;
	long scaleUpperStartAmnt=0;
	//long scaleUpperEndAmnt=10000000;
	long scaleUpperGradePay=10000000;
	long scaleUpperIncrAmnt=0;
	
	double basicLowVal=0;
	double basicUprVal=-1;
	double gpLowVal=0;
	double gpUprVal=-1;
	double grossLowVal=0;
	double grossUprVal=-1;
	
	long customScaleLowerStartAmnt=0;
	//long customScaleLowerEndAmnt=10000000;
	long customScaleLowerGradePay=0;
	long customScaleLowerIncrAmnt=0;
	long customScaleUpperStartAmnt=0;
	//long customScaleUpperEndAmnt=10000000;
	long customScaleUpperGradePay=10000000;
	long customScaleUpperIncrAmnt=0;
	
	
//	if(totalParamCount>ruleParamMpgList.size()){
//		compareFlag=1;
//		
//	}
//	else {
		
	for(HrPayCompViewRuleCustomVO ruleView:payCompRuleList)
	{
		
			compareFlag=0;

		if(!ruleView.getDojYear().equalsIgnoreCase(payCompCustomVO.getDojYear()))
				compareFlag=1;
		if(!ruleView.getDeptCode().equalsIgnoreCase(payCompCustomVO.getDeptCode()))
			compareFlag=1;
		
		if(!ruleView.getLocationCode().equalsIgnoreCase(payCompCustomVO.getLocationCode()))
			compareFlag=1;
		
		if(!ruleView.getClassCode().equalsIgnoreCase(payCompCustomVO.getClassCode()))
			compareFlag=1;
		
		if(!ruleView.getDesgnCode().equalsIgnoreCase(payCompCustomVO.getDesgnCode()))
			compareFlag=1;
		
		if(!ruleView.getCityCode().equalsIgnoreCase(payCompCustomVO.getCityType()))
			compareFlag=1;
		
	/*	if(ruleView.getEmpCatgryCode()!=payCompCustomVO.getEmpCatgryCode())
			compareFlag=1;*/
		
		if(ruleView.getGenderCode()!=payCompCustomVO.getGender())
			compareFlag=1;
		
		if(ruleView.getPostTypeCode()!=payCompCustomVO.getPostType())
			compareFlag=1;
		
		if(ruleView.getPhyChallengedCode()!=payCompCustomVO.getPhyChallengedFlag())
			compareFlag=1;
		
		if(ruleView.getQuarterTypeCode()!=payCompCustomVO.getQuarterType())
			compareFlag=1;
		
		if(ruleView.getPayCommissionValue()!=payCompCustomVO.getPayCommission())
			compareFlag=1;
		
		
		if(basicPayFlag==true)
		{
			if((!payCompCustomVO.getBasicLowLmt().equals("0")) || (!payCompCustomVO.getBasicUprLmt().equals("-1")))
			{
			if(!ruleView.getBasicLowLmtVal().equals("--"))
				basicLowVal=Double.parseDouble(ruleView.getBasicLowLmtVal());
			if(!ruleView.getBasicUprLmtVal().equals("--"))
				basicUprVal=Double.parseDouble(ruleView.getBasicUprLmtVal());
			
			if((((Double.parseDouble(payCompCustomVO.getBasicLowLmt())>=basicLowVal)&&(Double.parseDouble(payCompCustomVO.getBasicLowLmt())<=basicUprVal))||(((Double.parseDouble(payCompCustomVO.getBasicUprLmt())>=basicLowVal)&&(Double.parseDouble(payCompCustomVO.getBasicUprLmt())<=basicUprVal))))==false)
			compareFlag=1;
			else
				returnMsg=resourceBundle.getString("admin.compare_basicpay_msg");
			}
			
		}
		
		if(grossPayFlag==true)
		{
			if((!payCompCustomVO.getGrossLowLmt().equals("0")) || (!payCompCustomVO.getGrossUprLmt().equals("-1")))
			{
			if(!ruleView.getGrossLowLmtVal().equals("--"))
				grossLowVal=Double.parseDouble(ruleView.getGrossLowLmtVal());
			if(!ruleView.getGrossUprLmtVal().equals("--"))
				grossUprVal=Double.parseDouble(ruleView.getGrossUprLmtVal());
			
			if((((Double.parseDouble(payCompCustomVO.getGrossLowLmt())>=grossLowVal)&&(Double.parseDouble(payCompCustomVO.getGrossLowLmt())<=grossUprVal))||(((Double.parseDouble(payCompCustomVO.getGrossUprLmt())>=grossLowVal)&&(Double.parseDouble(payCompCustomVO.getGrossUprLmt())<=grossUprVal))))==false)
			compareFlag=1;
			else
				returnMsg=resourceBundle.getString("admin.compare_grosspay_msg");
			}
			
		}
		
		if(gradePayFlag==true)
		{
			if((!payCompCustomVO.getGpLowLmt().equals("0")) || (!payCompCustomVO.getGpUprLmt().equals("-1")))
			{
			if(!ruleView.getGpLowLmtVal().equals("--"))
				gpLowVal=Double.parseDouble(ruleView.getGpLowLmtVal());
			if(!ruleView.getGpUprLmtVal().equals("--"))
				gpUprVal=Double.parseDouble(ruleView.getGpUprLmtVal());
			
			if((((Double.parseDouble(payCompCustomVO.getGpLowLmt())>=gpLowVal)&&(Double.parseDouble(payCompCustomVO.getGpLowLmt())<=gpUprVal))||((Double.parseDouble(payCompCustomVO.getGpUprLmt())>=gpLowVal)&&(Double.parseDouble(payCompCustomVO.getGpUprLmt())<=gpUprVal)))==false)
			compareFlag=1;
			else
				returnMsg=resourceBundle.getString("admin.compare_gradepay_msg");
			}
		}
		
		if(payScaleFlag==true)
		{
			if(!ruleView.getScaleLowLmtVal().equals("--"))
			{
				 scaleStartAmnt=Long.parseLong(ruleView.getScaleLowLmtVal().split("-")[0]);
				// scaleEndAmnt=Long.parseLong(ruleView.getScaleLowLmtVal().split("-")[1]);
				 scaleGradePay=Long.parseLong(ruleView.getScaleLowLmtVal().split("-")[2]);
				 scaleIncrAmnt=Long.parseLong(ruleView.getScaleLowLmtVal().split("-")[2]);
			}
			if(!ruleView.getScaleUprLmtVal().equals("--"))
			{
				scaleUpperStartAmnt=Long.parseLong(ruleView.getScaleUprLmtVal().split("-")[0]);
				// scaleUpperEndAmnt=Long.parseLong(ruleView.getScaleUprLmtVal().split("-")[1]);
				 scaleUpperGradePay=Long.parseLong(ruleView.getScaleUprLmtVal().split("-")[2]);
				 scaleUpperIncrAmnt=Long.parseLong(ruleView.getScaleUprLmtVal().split("-")[2]);
			}	
		
			if(payCompCustomVO.getScaleLowLmt()!=-1)
			{
				Object[] scaleLowerColValues={payCompCustomVO.getScaleLowLmt(),langId};
				scaleMstCustom=scaleMstDAO.getListByColumnAndValue(scaleCmprCol, scaleLowerColValues).get(0);
				
				customScaleLowerStartAmnt=scaleMstCustom.getScaleStartAmt();
				//customScaleLowerEndAmnt=scaleMstCustom.getScaleEndAmt();
				customScaleLowerGradePay=scaleMstCustom.getScaleGradePay();
				customScaleLowerIncrAmnt=scaleMstCustom.getScaleIncrAmt();
			}
			if(payCompCustomVO.getScaleUprLmt()!=-1)
			{
				Object[] scaleUpperColValues={payCompCustomVO.getScaleUprLmt(),langId};
				scaleMstCustom=scaleMstDAO.getListByColumnAndValue(scaleCmprCol, scaleUpperColValues).get(0);
				
				customScaleUpperStartAmnt=scaleMstCustom.getScaleStartAmt();
				//customScaleUpperEndAmnt=scaleMstCustom.getScaleEndAmt();
				customScaleUpperGradePay=scaleMstCustom.getScaleGradePay();
				customScaleUpperIncrAmnt=scaleMstCustom.getScaleIncrAmt();
			}
			if(ruleView.getPayCommissionValue()==PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)
			{
				if((((customScaleLowerStartAmnt>=scaleStartAmnt)&&(customScaleLowerStartAmnt<=scaleUpperStartAmnt))||((customScaleUpperStartAmnt>=scaleStartAmnt)&&(customScaleUpperStartAmnt<=scaleUpperStartAmnt)))==false)
				{
					compareFlag=1;
					
				}
				else
					 returnMsg=resourceBundle.getString("admin.compare_payscale_msg"); 
				
				if((customScaleLowerStartAmnt==scaleStartAmnt)||(customScaleLowerStartAmnt==scaleUpperStartAmnt))
					{
					if(((customScaleLowerGradePay>=scaleGradePay)&&(customScaleLowerGradePay<=scaleUpperGradePay))==false)
					{
					compareFlag=1;
					
					
					}
					
					else
						 returnMsg=resourceBundle.getString("admin.compare_payscale_msg");
					
					if(((customScaleUpperGradePay>=scaleGradePay)&&(customScaleUpperGradePay<=scaleUpperGradePay))==false)
					{
					compareFlag=1;
					
					}
					else
						 returnMsg=resourceBundle.getString("admin.compare_payscale_msg");
					}
			}	
			if(ruleView.getPayCommissionValue()==PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION)
			{
				if((((customScaleLowerStartAmnt>=scaleStartAmnt)&&(customScaleLowerStartAmnt<=scaleUpperStartAmnt))||((customScaleUpperStartAmnt>=scaleStartAmnt)&&(customScaleUpperStartAmnt<=scaleUpperStartAmnt)))==false)
				{
					compareFlag=1;
					
				}
				else
					 returnMsg=resourceBundle.getString("admin.compare_payscale_msg"); 
				
				if((customScaleLowerStartAmnt==scaleStartAmnt)||(customScaleLowerStartAmnt==scaleUpperStartAmnt))
					{
					if(((customScaleLowerIncrAmnt>=scaleIncrAmnt)&&(customScaleLowerIncrAmnt<=scaleUpperIncrAmnt))==false)
					{
					compareFlag=1;
					}
					
					else
						 returnMsg=resourceBundle.getString("admin.compare_payscale_msg");
					
					if(((customScaleUpperIncrAmnt>=scaleIncrAmnt)&&(customScaleUpperIncrAmnt<=scaleUpperIncrAmnt))==false)
					{
					compareFlag=1;
					}
					else
						 returnMsg=resourceBundle.getString("admin.compare_payscale_msg");
					}
			}
		}
		
		if(compareFlag==0){
			ruleGrpId=ruleView.getRuleGrpId();
		break;
		}
	
	//}
	
}
	
	StringBuffer paramCMprStr = new StringBuffer();;
	
	paramCMprStr.append("<RuleCmprVal>");
	paramCMprStr.append("<returnMsg>").append("<![CDATA[").append(returnMsg).append("]]>").append("</returnMsg>");
	paramCMprStr.append("<compareFlag>").append("<![CDATA[").append(compareFlag).append("]]>").append("</compareFlag>");
	paramCMprStr.append("<ruleGrpId>").append("<![CDATA[").append(ruleGrpId).append("]]>").append("</ruleGrpId>");
	paramCMprStr.append("<reqPayComponentCode>").append("<![CDATA[").append(reqPayComponentCode).append("]]>").append("</reqPayComponentCode>");
	paramCMprStr.append("</RuleCmprVal>");


	String paramCmprVal = new AjaxXmlBuilder().addItem("ajax_key",paramCMprStr.toString()).toString();
	logger.info("paramCmprVal::::::::::::: " +paramCmprVal);
	objectArgs.put("ajaxKey", paramCmprVal);
	resObj.setResultValue(objectArgs);
	resObj.setViewName("ajaxData");
	resObj.setResultCode(ErrorConstants.SUCCESS);
	}
	catch (Exception ex) {
	logger.error("Error is: "+ ex.getMessage());
	resObj.setThrowable(ex);
	logger.error("Admin Screen Error in PayComponentMasterServiceImpl--->compareTosavedRules", ex);
	resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
@SuppressWarnings("unchecked")
public ResultObject insertPayComponentRuleDetails(Map<String, Object> objectArgs)
{
	logger.info("PayComponentMasterServiceImpl--->insertPayComponentRuleDetails method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

try {
	
	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	
	String encXML[] =(String[])objectArgs.get("encXML");
	for(int op= 0 ;op<encXML.length;op++)
	{
	logger.info(encXML[op]);
	}
	logger.info("encXML.length: " +encXML.length);
	
	Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
	//CommonUserVO commonUserVO = (CommonUserVO)loginMap.get("commonUserVO");	
	
	int langId = Integer.parseInt(loginMap.get("langId").toString());
	long loggedInUserId = Long.parseLong(loginMap.get("userId").toString());
	long loggedInPostId = Long.parseLong(loginMap.get("loggedInPost").toString());
	
	/*long loggedInUserId=commonUserVO.getUserId();
	long loggedInPostId=commonUserVO.getLoggedInPostVO().getPostCode();
	
	long langId = commonUserVO.getLangId();
	*/
	ResourceBundle resourceBundle;
	if(langId==2)
	resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_hi");
	else
		resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_en_US");
	
	objectArgs.put("MESSAGE",resourceBundle.getString("admin.record_saved_msg"));
	
	OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
	OrgUserMst orgUserMstLoggedIn = (OrgUserMst) orgUserMstDaoImpl.read(Long.valueOf(loggedInUserId));
	OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
	OrgPostMst orgPostMstLoggedIn = (OrgPostMst) orgPostMstDaoImpl.read(Long.valueOf(loggedInPostId));
	
	long reqPayComponentCode=Long.parseLong(objectArgs.get("reqPayComponentCode").toString());
			
	logger.info("encXML  "+encXML);
	if(encXML!=null && encXML.length>0)
	{
		ArrayList payCompRule = (ArrayList) FileUtility.xmlFilesToVoByXStream(encXML);
		java.util.Iterator ruleIterator = payCompRule.iterator();
		logger.info("result.size(): "+payCompRule.size());
		
		PayComponentRuleGroupMstDAO ruleGrpMstDao =  new PayComponentRuleGroupMstDAOImpl(HrPayRuleGrpMst.class,serv.getSessionFactory());
		PayComponentRuleGroupParamRltDAO ruleGrpParamRlt = new PayComponentRuleGroupParamRltDAOImpl(HrPayRuleGrpParamRlt.class,serv.getSessionFactory());
		
		PayComponentParamMstDAOImpl paramMstDao = new PayComponentParamMstDAOImpl(HrPayRuleParamMst.class,serv.getSessionFactory());
		List<HrPayRuleParamMst> paramMstLst = paramMstDao.getRuleParameters();
		
		
		HrPayRuleGrpParamRlt rulePayCompParamGrpRlt = null;
		HrPayRuleGrpMst ruleGrpMst=null;
		
		HrPayCompRuleCustomVO ruleCustomVO = new HrPayCompRuleCustomVO();
		
		String paramFieldValue;
		String paramFieldUpperValue;
		Date crtdDate = new Date();
		Field paramField;
		Field paramUpperField;
		
		while(ruleIterator.hasNext())
		  {
			ruleCustomVO = (HrPayCompRuleCustomVO)ruleIterator.next();
			Class ruleCustomCls = ruleCustomVO.getClass();
			
			ruleGrpMst = new HrPayRuleGrpMst();
			ruleGrpMst.setRuleGrpId(IDGenerateDelegate.getNextId("HR_PAY_RULE_GRP_MST", objectArgs));
			ruleGrpMst.setAllwDedCode(reqPayComponentCode);
			ruleGrpMst.setCrtdDate(crtdDate);
			ruleGrpMst.setOrgPostMstBycrtdByPost(orgPostMstLoggedIn);
			ruleGrpMst.setOrgUserMstBycrtdBy(orgUserMstLoggedIn);
			ruleGrpMst.setTrnCounter(1);
			ruleGrpMst.setRuleStatus(ruleCustomVO.getStatus());
			ruleGrpMst.setReturnType(ruleCustomVO.getReturnType());
			
			if(ruleCustomVO.getReturnType()==PayrollConstants.PAYROLL_RETURNTYPE_FIXED)
			{
				ruleGrpMst.setReturnValue(new BigDecimal(ruleCustomVO.getRuleAmount()));
			}
			else if(ruleCustomVO.getReturnType()==PayrollConstants.PAYROLL_RETURNTYPE_FORMULA)
			{
				ruleGrpMst.setReturnValue(new BigDecimal(ruleCustomVO.getRulePercentage()));
				ruleGrpMst.setReturnFormula(ruleCustomVO.getRuleFormula());
			}
			
			ruleGrpMstDao.create(ruleGrpMst);
			
			logger.info("===> paramMstLst.size() :: "+paramMstLst.size());
			logger.info("===> ruleCustomVO.getDesgnCode() :: "+ruleCustomVO.getDesgnCode());
			
			for( HrPayRuleParamMst ruleParam :paramMstLst)
			{
				paramFieldValue="";
				paramFieldUpperValue="";
				
				logger.info("ruleParam.getPropertyValName() "+ruleParam.getPropertyValName());
				
				paramField = ruleCustomCls.getDeclaredField(ruleParam.getPropertyValName());
				paramField.setAccessible(true);
				paramFieldValue=paramField.get(ruleCustomVO).toString();				
			
				logger.info("==> before  if :: "+ruleParam.getParamType());
				
				if(ruleParam.getParamType()==PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE)
				{
					logger.info("==> first if :: "+ruleParam.getPropertyUpperValName());
					paramUpperField = ruleCustomCls.getDeclaredField(ruleParam.getPropertyUpperValName());
					paramUpperField.setAccessible(true);
					paramFieldUpperValue=paramUpperField.get(ruleCustomVO).toString();
				}
				
					// insertion in pay component's mapped parameters' values insertion begins
				if((ruleParam.getParamType()==PayrollConstants.PAYROLL_PARAMETER_FIXED_TYPE)&&(!paramFieldValue.equals("-1")))
				{
					rulePayCompParamGrpRlt = new HrPayRuleGrpParamRlt();
				
					rulePayCompParamGrpRlt.setGrpRltId(IDGenerateDelegate.getNextId("HR_PAY_RULEGRP_PARAM_RLT", objectArgs));
				
					logger.info("==> second if :: "+ruleParam.getPropertyUpperValName());
					rulePayCompParamGrpRlt.setRuleGrpId(ruleGrpMst);
					rulePayCompParamGrpRlt.setCrtdDate(crtdDate);
					rulePayCompParamGrpRlt.setParamId(ruleParam);
					rulePayCompParamGrpRlt.setParamValue(paramFieldValue);
					rulePayCompParamGrpRlt.setOrgUserMstBycrtdBy(orgUserMstLoggedIn);
					rulePayCompParamGrpRlt.setOrgPostMstBycrtdByPost(orgPostMstLoggedIn);
					rulePayCompParamGrpRlt.setTrnCounter(1);
					
					ruleGrpParamRlt.create(rulePayCompParamGrpRlt);
				}
				//insertion for parameter type-2 i.e. parameters having range e.g. scale, basic pay, grade pay
				if((ruleParam.getParamType()==PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE)&&((!paramFieldValue.equals("-1"))||(!paramFieldUpperValue.equals("-1"))))
				{
					if(paramFieldValue.equals("-1"))
						paramFieldValue="0";
//					if(paramFieldUpperValue.equals("-1"))
//						paramFieldUpperValue="0";
					
					rulePayCompParamGrpRlt = new HrPayRuleGrpParamRlt();
					
					
					rulePayCompParamGrpRlt.setGrpRltId(IDGenerateDelegate.getNextId("HR_PAY_RULEGRP_PARAM_RLT", objectArgs));
					
					logger.info("==> second if :: "+ruleParam.getPropertyUpperValName());
					rulePayCompParamGrpRlt.setRuleGrpId(ruleGrpMst);
					rulePayCompParamGrpRlt.setCrtdDate(crtdDate);
					rulePayCompParamGrpRlt.setParamId(ruleParam);
					rulePayCompParamGrpRlt.setParamLowerValue(new BigDecimal(paramFieldValue));
					rulePayCompParamGrpRlt.setParamUpperValue(new BigDecimal(paramFieldUpperValue));
					
					rulePayCompParamGrpRlt.setOrgUserMstBycrtdBy(orgUserMstLoggedIn);
					rulePayCompParamGrpRlt.setOrgPostMstBycrtdByPost(orgPostMstLoggedIn);
					rulePayCompParamGrpRlt.setTrnCounter(1);
					
					ruleGrpParamRlt.create(rulePayCompParamGrpRlt);
						
				}
			
			}
			
			
		
			
		}//every table to be inserted in each iteration of ruleCustomVO
	}
	
	objectArgs.put("reqPayComponentCode",reqPayComponentCode);
	resObj.setResultValue(objectArgs);
	resObj.setViewName("adminAddPayComponentRule");
	resObj.setResultCode(ErrorConstants.SUCCESS);
	}
	catch (Exception ex) {
	logger.error("Error is: "+ ex.getMessage());
	resObj.setThrowable(ex);
	logger.error("Admin Screen Error in PayComponentMasterServiceImpl--->insertPayComponentRuleDetails", ex);
	resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
@SuppressWarnings("unchecked")
public ResultObject updatePayComponentRule(Map<String, Object> objectArgs)
{
	logger.info("PayComponentMasterServiceImpl--->updatePayComponentRule method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

try {
	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
	//CommonUserVO commonUserVO = (CommonUserVO)loginMap.get("commonUserVO");
	
	int langId = Integer.parseInt(loginMap.get("langId").toString());
	
	long reqPayCompRuleGrpId = Long.parseLong(objectArgs.get("reqPayCompRuleGrpId").toString());
	long reqPayComponentCode=Long.parseLong(objectArgs.get("reqPayComponentCode").toString());
	
	PayComponentMasterDAO payCompDAO = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class, serv.getSessionFactory());
	PayComponentRuleGroupMstDAO ruleGrpMstDao =  new PayComponentRuleGroupMstDAOImpl(HrPayRuleGrpMst.class,serv.getSessionFactory());
	PayComponentRuleGroupParamRltDAO ruleGrpParamRltDao =  new PayComponentRuleGroupParamRltDAOImpl(HrPayRuleGrpParamRlt.class,serv.getSessionFactory());
	
	String[] AllowDedCmprCol = {"allwDedCode","langId"};
	Object[] AllowDedColValues = {reqPayComponentCode,langId};
	HrPayAllowDedMst payComp = payCompDAO.getListByColumnAndValue(AllowDedCmprCol, AllowDedColValues).get(0);
	
	
	List<HrPayRuleGrpMst> payCompRuleGrpLst = ruleGrpMstDao.getListByColumnAndValue("ruleGrpId", reqPayCompRuleGrpId);
	List<HrPayRuleGrpParamRlt> ruleGrpParamRltLst = ruleGrpParamRltDao.getListByColumnAndValue("ruleGrpId.ruleGrpId", reqPayCompRuleGrpId);
	
	HrPayCompViewRuleCustomVO payCompViewRuleCustomVO =getRuleViewDtls(payCompRuleGrpLst, ruleGrpParamRltLst, serv, langId).get(0);
	
	objectArgs.put("payCompViewRuleCustomVO",payCompViewRuleCustomVO);
	objectArgs.put("reqPayComponentCode",reqPayComponentCode);
	objectArgs.put("payComp",payComp);

	resObj.setResultValue(objectArgs);
	resObj.setViewName("adminUpdatePayComponentRule");
	resObj.setResultCode(ErrorConstants.SUCCESS);
	}
	catch (Exception ex) {
	logger.error("Error is: "+ ex.getMessage());
	resObj.setThrowable(ex);
	logger.error("Admin Screen Error in PayComponentMasterServiceImpl--->updatePayComponentRule", ex);
	resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
@SuppressWarnings("unchecked")
public ResultObject updatePayComponentRuleValue(Map<String, Object> objectArgs)
{
	logger.info("PayComponentMasterServiceImpl--->updatePayComponentRuleValue method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

try {
	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			
	long langId = Long.parseLong(loginMap.get("langId").toString());
	long loggedInUserId = Long.parseLong(loginMap.get("userId").toString());
	long loggedInPostId = Long.parseLong(loginMap.get("loggedInPost").toString());
	
	ResourceBundle resourceBundle;
	if(langId==2)
	resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_hi");
	else
		resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_en_US");
	objectArgs.put("MESSAGE",resourceBundle.getString("admin.record_updated_msg"));
	
	
	
	OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
	OrgUserMst orgUserMstLoggedin = (OrgUserMst) orgUserMstDaoImpl.read(Long.valueOf(loggedInUserId));
	OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
	OrgPostMst orgPostMstLoggedIn = (OrgPostMst) orgPostMstDaoImpl.read(Long.valueOf(loggedInPostId));
	
	long reqPayCompRuleGrpId = Long.parseLong(objectArgs.get("reqPayCompRuleGrpId").toString());
	int ruleStatus = Integer.parseInt(objectArgs.get("ruleStatus").toString());
	long reqPayComponentCode=Long.parseLong(objectArgs.get("reqPayComponentCode").toString());
	int reqPayCompReturnType=Integer.parseInt(objectArgs.get("reqPayCompReturnType").toString());
	String rulePercentage=objectArgs.get("rulePercentage").toString();
	String ruleAmount=objectArgs.get("ruleAmount").toString();
	
	PayComponentRuleGroupMstDAO ruleMstDao = new PayComponentRuleGroupMstDAOImpl(HrPayRuleGrpMst.class,serv.getSessionFactory());
	
	HrPayRuleGrpMst ruleMst = ruleMstDao.getListByColumnAndValue("ruleGrpId", reqPayCompRuleGrpId).get(0);
	ruleMst.setRuleStatus(ruleStatus);
	ruleMst.setOrgPostMstByupdatedByPost(orgPostMstLoggedIn);
	ruleMst.setOrgUserMstByupdatedBy(orgUserMstLoggedin);
	
	if(reqPayCompReturnType==PayrollConstants.PAYROLL_RETURNTYPE_FIXED)
		ruleMst.setReturnValue(new BigDecimal(ruleAmount));
	else if(reqPayCompReturnType==PayrollConstants.PAYROLL_RETURNTYPE_FORMULA)
		ruleMst.setReturnValue(new BigDecimal(rulePercentage));
	
	ruleMstDao.update(ruleMst);
	
	objectArgs.put("reqPayComponentCode",reqPayComponentCode);
	
	resObj.setResultValue(objectArgs);
	resObj.setViewName("adminViewPayComponentRuleList");
	resObj.setResultCode(ErrorConstants.SUCCESS);
	}
	catch (Exception ex) {
	logger.error("Error is: "+ ex.getMessage());
	resObj.setThrowable(ex);
	logger.error("Admin Screen Error in PayComponentMasterServiceImpl--->updatePayComponentRuleValue", ex);
	resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
@SuppressWarnings("unchecked")
public ResultObject updatePayComponentRuleStatus(Map<String, Object> objectArgs)
{
	logger.info("PayComponentMasterServiceImpl--->updatePayComponentRuleStatus method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

try {
	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			
	long langId = Long.parseLong(loginMap.get("langId").toString());
	long loggedInUserId = Long.parseLong(loginMap.get("userId").toString());
	long loggedInPostId = Long.parseLong(loginMap.get("loggedInPost").toString());
	
	ResourceBundle resourceBundle;
	if(langId==2)
	resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_hi");
	else
		resourceBundle = ResourceBundle.getBundle("resources.payrollAdminLabels_en_US");
	objectArgs.put("MESSAGE",resourceBundle.getString("admin.record_updated_msg"));
	
	
	OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
	OrgUserMst orgUserMstLoggedin = (OrgUserMst) orgUserMstDaoImpl.read(Long.valueOf(loggedInUserId));
	OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
	OrgPostMst orgPostMstLoggedIn = (OrgPostMst) orgPostMstDaoImpl.read(Long.valueOf(loggedInPostId));
	
	long reqPayCompRuleGrpId = Long.parseLong(objectArgs.get("reqPayCompRuleGrpId").toString());
	int reqdStatus = Integer.parseInt(objectArgs.get("reqdStatus").toString());
	long reqPayComponentCode=Long.parseLong(objectArgs.get("reqPayComponentCode").toString());
	
	PayComponentRuleGroupMstDAO ruleMstDao = new PayComponentRuleGroupMstDAOImpl(HrPayRuleGrpMst.class,serv.getSessionFactory());
	
	HrPayRuleGrpMst ruleMst = ruleMstDao.getListByColumnAndValue("ruleGrpId", reqPayCompRuleGrpId).get(0);
	ruleMst.setRuleStatus(reqdStatus);
	ruleMst.setOrgPostMstByupdatedByPost(orgPostMstLoggedIn);
	ruleMst.setOrgUserMstByupdatedBy(orgUserMstLoggedin);
	
	ruleMstDao.update(ruleMst);
	
	objectArgs.put("reqPayComponentCode",reqPayComponentCode);
	
	resObj.setResultValue(objectArgs);
	resObj.setViewName("adminViewPayComponentRuleList");
	resObj.setResultCode(ErrorConstants.SUCCESS);
	}
	catch (Exception ex) {
	logger.error("Error is: "+ ex.getMessage());
	resObj.setThrowable(ex);
	logger.error("Admin Screen Error in PayComponentMasterServiceImpl--->updatePayComponentRuleStatus", ex);
	resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
}