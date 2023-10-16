package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.dao.BillHeadDao;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.HrPayDdoSchemeMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrgSchemeMstDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.SchedulerDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayDDOSchemeMpgVO;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadCustomMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderSubHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;
import com.tcs.sgv.eis.valueobject.OrgSchemeAndHrpayVO;
import com.tcs.sgv.eis.valueobject.OrgSchemeMstVO;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.CmnBranchlocMpg;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class SchemeServiceImpl extends ServiceImpl
{

	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	public ResultObject insertSchemeDtls(Map objectArgs)
	{
		// TODO Auto-generated method stub         schemeView
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("SchemeServiceImpl insertSchemeDtls Called");		
		    //System.out.println("SchemeServiceImpl insertSchemeDtls Called");
		    HttpSession session = request.getSession();
			Map baseLoginMap = (Map) objectArgs.get("baseLoginMap");
			Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString()); 
				//StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			//System.out.println("insertSchemeDtls pprimaryPostIdostId" + postId);
			OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(postId);
			
			//System.out.println("insertSchemeDtls loggedInPost" + postId);
			List<CmnLocationMst> deptList = new ArrayList<CmnLocationMst>();
		
			OrgSchemeMstDAOImpl orgSchemeMstDAOImpl = new OrgSchemeMstDAOImpl(OrgSchemeMstVO.class, serv.getSessionFactory());
		
			List<OrgPostDetailsRlt> locIds = orgSchemeMstDAOImpl.getLocIds(postId);
			long locId = locIds.get(0).getCmnLocationMst().getLocId();
			//System.out.println("insertSchemeDtls location id" + locId);
			
			boolean isRoleAdminFlag = false;	 
			if (isRoleAdminFlag) 
			{
				logger.info("true::");
				deptList = locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId );
				
				logger.info("deptlist.size::" + deptList.size());
			} 
			else
			{
				logger.info("false::");
				String[] critariaArugs = { "cmnLanguageMst.langId", "locId" };
				Object[] valueArgus = new Object[2];
				valueArgus[0] = langId;
				valueArgus[1] = locId;
				deptList = locationDAO.getListByColumnAndValue(critariaArugs,valueArgus);
				//System.out.println("dept list is ::::"+deptList.get(0));
				//System.out.println("location code::::"+((CmnLocationMst)deptList.get(0)).getLocationCode());
				//System.out.println("location Id is ::::"+((CmnLocationMst)deptList.get(0)).getLocId());
				logger.info("deptlist.size::" + deptList.size());
			}
			
			objectArgs.put("deptList", deptList);
			logger.info("going to jsppppp");
			//System.out.println("going to jsppppp11111111111111111111");
			resultObject.setResultValue(objectArgs);
			//System.out.println("End of view name");
			resultObject.setResultCode(0);
			resultObject.setViewName("SchemeEntryPage");
			logger.info("going to jsppppp222222222222222222222222");
		}
		catch(Exception e)
		{
			logger.info("Exception Ocuures...insertBankMasterDtls");
			 logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		return resultObject;
	}

	
	
	public ResultObject getDepartmentList(Map objectArgs) 
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		int msg=0;
		try 
		{
			HttpSession session = request.getSession();
			Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map vo = (Map)objectArgs.get("voToServiceMap");
			
			Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			
			long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			
			CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			//System.out.println("SchemeServiceImpl getDepartmentList");
			
			
			long loggedInUser = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);
			
			long loggedInpostId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);
			OrgSchemeMstDAOImpl orgSchemeMstDAOImpl = new OrgSchemeMstDAOImpl(OrgSchemeMstVO.class, serv.getSessionFactory());
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class, serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			String dt = sdf.format(currDate);
			int finYrId = finYearDAOImpl.getFinYearId(dt);
			
			String schemeCode = vo.get("schemeCode").toString();
			String schemeName = vo.get("schemeName").toString();
			String budsubhdId = vo.get("head").toString();
			

			String cmbDept = vo.get("cmbDept").toString();
			String cmbDemand = vo.get("cmbDemand").toString();
			String cmbMjrHead = vo.get("cmbMjrHead").toString();
			String cmbSubMjrHead = vo.get("cmbSubMjrHead").toString();
			String cmbMnrHead = vo.get("cmbMnrHead").toString();
			
			String SchmeType = vo.get("SchmeType").toString();
			//System.out.println("SchmeType value is ::::"+SchmeType);
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			
			
			
			
		    List<CmnLookupMst> Schemetypelist = cmnLookupMstDAOImpl.getListByColumnAndValue("lookupName", SchmeType);
		    long ChargeTypeId =   Schemetypelist.get(0).getLookupId();
		    CmnLookupMst cmnLookupMstForCharge = new CmnLookupMst();
		    CmnLookupMst cmnLookupMstForPlan =new CmnLookupMst();
		    cmnLookupMstForCharge.setLookupId(ChargeTypeId);
		    ;
		    //System.out.println("Schemetypelist value is ::::"+Schemetypelist.size());
		    //System.out.println("SchemeTypeId value is ::::"+ChargeTypeId);
		    
			
			String IsPlane = vo.get("IsPlane").toString();
			//System.out.println("IsPlane value is ::::"+IsPlane);
			List<CmnLookupMst> PlanIdList = cmnLookupMstDAOImpl.getListByColumnAndValue("lookupName", IsPlane);
			long PlanTypeId =   PlanIdList.get(0).getLookupId();
		    //System.out.println("PlanIdList value is ::::"+PlanIdList.size());
			//System.out.println("PlanTypeId value is ::::"+PlanTypeId);
			cmnLookupMstForPlan.setLookupId(PlanTypeId);
			
			/*System.out.println("schemeCode value is ::::"+schemeCode);
			System.out.println("schemeName value is ::::"+schemeName);
			System.out.println("subHead value is ::::"+budsubhdId);
			System.out.println("cmbDept value is ::::"+cmbDept);
			System.out.println("cmbDemand value is ::::"+cmbDemand);
			System.out.println("cmbMjrHead value is ::::"+cmbMjrHead);
			System.out.println("cmbSubMjrHead value is ::::"+cmbSubMjrHead);
			System.out.println("cmbMnrHead value is ::::"+cmbMnrHead);*/
			
			
			SgvaBudsubhdMst sgvaBudsubhdMst = new SgvaBudsubhdMst();
			sgvaBudsubhdMst.setBudsubhdId(Long.parseLong(budsubhdId));
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
			//List<HrPayOrderSubHeadMpg>  budSubHeadList = orgSchemeMstDAOImpl.getBudSubHdId(subHead, finYrId);
			//SgvaBudsubhdMst sgvaBudsubhdMst = (SgvaBudsubhdMst)budSubHeadList.get(0).getSgvaBudsubhdMst();
			//System.out.println("sgvaBudsubhdMst value is ::::"+sgvaBudsubhdMst);
			
			Date syDate = new Date();
			String editFromVO = StringUtility.getParameter("edit", request).toString();
			logger.info("Flag which display go in insert or update "+ editFromVO);
			//System.out.println("editFromVOeditFromVOeditFromVO" + editFromVO);
			
			List BudSubHdList = orgSchemeMstDAOImpl.getListFromBudSubHdId(budsubhdId, langId);
			//System.out.println("BudSubHdListBudSubHdListBudSubHdList" + BudSubHdList.size());
			if(BudSubHdList.size()==0)
			{
				
			if (editFromVO != null && editFromVO.equalsIgnoreCase("Y"))
			{
				
				
				//System.out.println("budsubhead......"+vo.get("budSubHeadId").toString());
				String budSubHeadId =vo.get("budSubHeadId").toString();
					//vo.get("budSubHeadId").toString();
				//System.out.println("budSubHeadIdbudSubHeadId" + budSubHeadId);
				
				
				List<OrgSchemeMstVO> SchemeIdList=orgSchemeMstDAOImpl.getSchemeIds(budSubHeadId);
				
				long SchemeId=0L;
				
				
				for(int i=0; i<SchemeIdList.size() ; i++)
				{
				 logger.info("SchemeIdList SchemeIdList SchemeIdList " +SchemeIdList.size());
				 
				 SchemeId= SchemeIdList.get(i).getSchemeId();
				
				}
				
				//System.out.println("SchemeIdSchemeIdSchemeId------------>" + SchemeId);
				List<HrPayDDOSchemeMpgVO> IdList = orgSchemeMstDAOImpl.getIdsFromHrPayDdoSchemeMpg(SchemeId);
				long Idd=0L;
				for(int i=0; i<IdList.size() ; i++)
				{
				 logger.info("IdList hrGpfBalanceDtls IdList " +IdList.size());
				 
				 Idd= IdList.get(i).getId();
				
				}
				
				//System.out.println("IdIdIdId------------>" + Idd);
				
				
				/*System.out.println("syDatesyDatesyDate" + syDate);
				System.out.println("loggedInOrgUserMst" + loggedInOrgUserMst);
				System.out.println("loggedInOrgPostMst" + loggedInOrgPostMst);*/
			//	OrgSchemeMstVO orgSchemeMstVO = new OrgSchemeMstVO();
				
				
				OrgSchemeMstVO orgSchemeMstVO	= orgSchemeMstDAOImpl.read(SchemeId);
				orgSchemeMstVO.setSchemeId(SchemeId);
				orgSchemeMstVO.setSchemeCode(Long.parseLong(schemeCode));
				orgSchemeMstVO.setSchemeName(schemeName);
				orgSchemeMstVO.setSgvaBudsubhdMst(sgvaBudsubhdMst);
				orgSchemeMstVO.setUpdatedDate(syDate);
				orgSchemeMstVO.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
				orgSchemeMstVO.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
				orgSchemeMstVO.setCmnLocationMst(cmnLocationMst);
				orgSchemeMstVO.setCmnLookupMstForCharge(cmnLookupMstForCharge);
				orgSchemeMstVO.setCmnLookupMstForPlan(cmnLookupMstForPlan);
				
				orgSchemeMstDAOImpl.update(orgSchemeMstVO);
				//System.out.println("SYsdate" + orgSchemeMstVO.getCreatedDate());
				List<OrgDdoMst> ddoList = orgSchemeMstDAOImpl.getDDoId(postId);
				if(ddoList.size()!=0)
				{
					
				HrPayDdoSchemeMpgDAOImpl hrPayDdoSchemeMpgDAOImpl =  new HrPayDdoSchemeMpgDAOImpl(HrPayDDOSchemeMpgVO.class, serv.getSessionFactory());
					
				OrgDdoMst orgDdoMst = (OrgDdoMst)ddoList.get(0);
				//System.out.println("orgDdoMstorgDdoMst" + orgDdoMst);
				HrPayDDOSchemeMpgVO  hrPayDDOSchemeMpgVO = hrPayDdoSchemeMpgDAOImpl.read(Idd);
				hrPayDDOSchemeMpgVO.setId(Idd);
				hrPayDDOSchemeMpgVO.setOrgSchemeMstVO(orgSchemeMstVO);
				hrPayDDOSchemeMpgVO.setOrgDdoMst(orgDdoMst);
				hrPayDDOSchemeMpgVO.setUpdatedDate(syDate);
				hrPayDDOSchemeMpgVO.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
				hrPayDDOSchemeMpgVO.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
				hrPayDdoSchemeMpgDAOImpl.update(hrPayDDOSchemeMpgVO);
				objectArgs.put("msg", "Updated Successfully");
				msg=1;
				}	 
				else
				{
					objectArgs.put("ddomsg", "some message");	
				}
				
			}
			
			
			else if (editFromVO != null && editFromVO.equalsIgnoreCase("N"))
			{
			
		
			
			OrgSchemeMstVO orgSchemeMstVO = new OrgSchemeMstVO();
			IdGenerator idGen = new IdGenerator();
			long schemeId = idGen.PKGeneratorWODBLOC("ORG_SCHEME_MST", objectArgs);
			//System.out.println("Primary Key schemeId" + schemeId);
			orgSchemeMstVO.setSchemeId(schemeId);
			orgSchemeMstVO.setSchemeCode(Long.parseLong(schemeCode));
			orgSchemeMstVO.setSchemeName(schemeName);
			orgSchemeMstVO.setSgvaBudsubhdMst(sgvaBudsubhdMst);
			orgSchemeMstVO.setCmnLanguageMst(cmnLanguageMst);
			orgSchemeMstVO.setCmnLocationMst(cmnLocationMst);
			orgSchemeMstVO.setCreatedDate(syDate);
			orgSchemeMstVO.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
			orgSchemeMstVO.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
			orgSchemeMstVO.setCmnLookupMstForCharge(cmnLookupMstForCharge);
			orgSchemeMstVO.setCmnLookupMstForPlan(cmnLookupMstForPlan);
			orgSchemeMstDAOImpl.create(orgSchemeMstVO);
			 logger.info("Inserted successfully................");
			List<OrgDdoMst> ddoList = orgSchemeMstDAOImpl.getDDoId(postId);
			if(ddoList.size()!=0)
			{
				
			HrPayDdoSchemeMpgDAOImpl hrPayDdoSchemeMpgDAOImpl =  new HrPayDdoSchemeMpgDAOImpl(HrPayDDOSchemeMpgVO.class, serv.getSessionFactory());
				
			OrgDdoMst orgDdoMst = (OrgDdoMst)ddoList.get(0);
			//System.out.println("orgDdoMstorgDdoMst" + orgDdoMst);
			HrPayDDOSchemeMpgVO  hrPayDDOSchemeMpgVO = new HrPayDDOSchemeMpgVO();
			long Id = idGen.PKGeneratorWODBLOC("HR_PAY_DDO_SCHEME_MPG", objectArgs);
			//System.out.println("Primary Key Id" + Id);
			hrPayDDOSchemeMpgVO.setId(Id);
			hrPayDDOSchemeMpgVO.setOrgSchemeMstVO(orgSchemeMstVO);
			hrPayDDOSchemeMpgVO.setCmnLanguageMst(cmnLanguageMst);
			hrPayDDOSchemeMpgVO.setCmnLocationMst(cmnLocationMst);
			hrPayDDOSchemeMpgVO.setCreatedDate(syDate);
			hrPayDDOSchemeMpgVO.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
			hrPayDDOSchemeMpgVO.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
			hrPayDDOSchemeMpgVO.setOrgDdoMst(orgDdoMst);
			hrPayDdoSchemeMpgDAOImpl.create(hrPayDDOSchemeMpgVO);
			
			objectArgs.put("msg", "Inserted Successfully");
			}	 
			else
			{
				objectArgs.put("msg", "some message");
			}
			}
			}
			
			else
			{
				
					objectArgs.put("msg","Mapping Already Existed");
				
					
			}
			/*Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "schemeViewListOnTheScreen");
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			objectArgs.put("redirectMap", redirectMap);	
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
			resObj.setViewName("SchemeView");
			*/
			
			ResultObject resultObject= viewSchemerDtls(objectArgs);
			resultObject.setResultValue("passList");
		
			objectArgs.put("resultObject", resultObject);
			
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "schemeViewListOnTheScreen");
			resObj.setResultCode(ErrorConstants.SUCCESS);
			logger.info("End of saveGroupManagementService method:::::::: " +objectArgs.get("msg") );
			//objectArgs.put("redirectMap", redirectMap);
			resObj.setResultValue(objectArgs);
			resObj.setViewName("SchemeView");
			
			
			
		}

		catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}
	
	public ResultObject viewSchemerDtls(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		try 
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			Log logger = LogFactory.getLog(getClass());
			logger.info("viewSchemerDtls  method calling");

			CmnLocationMstDaoImpl cmnLocationMstDao = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			
			OrgSchemeMstDAOImpl orgSchemeMstDAOImpl = new OrgSchemeMstDAOImpl(OrgSchemeMstVO.class, serv.getSessionFactory());
			List<OrgSchemeMstVO> schemeList = orgSchemeMstDAOImpl.getAllData(langId);
			//System.out.println("viewSchemerDtls schemeList " + schemeList.size());
			
			List idlIst = new ArrayList();
			long BudSubHeadId=0;
			for(int i=0; i<schemeList.size() ; i++)
			{
				BudSubHeadId= schemeList.get(0).getSgvaBudsubhdMst().getBudsubhdId();
				
				idlIst.add(BudSubHeadId);
				
			}
			List ViewList= new ArrayList();
			for(int i=0; i<schemeList.size() ; i++)
			{

			   ViewList = orgSchemeMstDAOImpl.getSchemeAndBudList(langId);
			
			}
			//System.out.println("viewSchemerDtls ViewList" + ViewList.size());
			
			long SchemeCode = 0 ;
			String SchemeName ="";
			String Budname = "";
			long BudSubId=0L;
			List<OrgSchemeAndHrpayVO> passList = new ArrayList();
			for(int i=0; i<ViewList.size(); i++)
			{
			Object[] row = ((Object[])ViewList.get(i));
			SchemeCode=Long.parseLong(row[0].toString());
			SchemeName=row[1].toString();
			 Budname=row[2].toString();
			 BudSubId=Long.parseLong(row[3].toString());
			 
			 OrgSchemeAndHrpayVO orgSchemeAndHrpayVO = new OrgSchemeAndHrpayVO();
			 orgSchemeAndHrpayVO.setBudName(Budname);
			 orgSchemeAndHrpayVO.setSchemeCode(SchemeCode);
			 orgSchemeAndHrpayVO.setSchemeName(SchemeName);
			 orgSchemeAndHrpayVO.setBudSubId(BudSubId);
		    passList.add(orgSchemeAndHrpayVO);
			}
			
			
			/*System.out.println("passList size .."+passList.size());
			System.out.println("viewSchemerDtls schemecode" + SchemeCode);
			System.out.println("viewSchemerDtls SchemeName" + SchemeName);
			System.out.println("viewSchemerDtls Budname" + Budname);
			System.out.println("viewSchemerDtls BudSubId" + BudSubId);*/
			
			objectArgs.put("ViewList", ViewList);
			objectArgs.put("BudSubHeadId", BudSubHeadId);
			objectArgs.put("passList", passList);
			objectArgs.put("listSize", passList.size());

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("SchemeView");

		}
		catch (Exception e) 
		{
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is some problem. Please Try Again.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
		}

		return resultObject;
	}
	
	
	public ResultObject updateSchemerDtls(Map objectArgs) 
	{
		logger.info("in updateSchdeularDtls");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try 
		{

			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			BillHeadDao headMasterDAO = new BillHeadDao(HrPayBillHeadCustomVO.class, serv.getSessionFactory());
			
		long budSubHeadId = Long.valueOf(StringUtility.getParameter("BudSubHeadId", request).toString());
		FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class, serv.getSessionFactory());
		Date currDate = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		String dt = sdf.format(currDate);
		int finYrId = finYearDAOImpl.getFinYearId(dt);
		
		HrPayBillHeadCustomVO ohMpg = new HrPayBillHeadCustomVO();
		List bhDataList = new ArrayList();
		bhDataList = headMasterDAO.getAllDatabybudSubHeadId(budSubHeadId,locId);
	
		CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
		List<CmnLocationMst> deptList = new ArrayList<CmnLocationMst>();
		long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
		
		OrgSchemeMstDAOImpl orgSchemeMstDAOImpl = new OrgSchemeMstDAOImpl(OrgSchemeMstVO.class, serv.getSessionFactory());
		List ViewList = orgSchemeMstDAOImpl.getSchemeList(budSubHeadId);
		long SchemeCode = 0 ;
		String SchemeName ="";
		String Budname = "";
		long schemetype=0L;
		long plantype=0L;
		List<OrgSchemeAndHrpayVO> passList = new ArrayList();
		for(int i=0; i<ViewList.size(); i++)
		{
		Object[] row = ((Object[])ViewList.get(i));
		SchemeCode=Long.parseLong(row[0].toString());
		SchemeName=row[1].toString();
		schemetype=Long.parseLong(row[3].toString());
		plantype=Long.parseLong(row[4].toString());
		}
		
		
		//System.out.println("schemetypeschemetypeschemetype--->" + schemetype);
		//System.out.println("plantypeplantypeplantypeplantype--->" + plantype);
		List<CmnLookupMst> lookidListForCharge = orgSchemeMstDAOImpl.getLookupIdForCharge(schemetype);
		List<CmnLookupMst> lookidListForPlan  = orgSchemeMstDAOImpl.getLookupIdForPlan(plantype);
		String ChargeTypeName ="";
		String PlanName ="";
		for(int i=0; i<lookidListForCharge.size() ; i++)
		{
			ChargeTypeName= lookidListForCharge.get(0).getLookupName();
			
		}
		
		for(int i=0; i<lookidListForPlan.size() ; i++)
		{
			PlanName= lookidListForPlan.get(0).getLookupName();
			
		}
		
		
		//System.out.println("ChargeTypeNameChargeTypeName--->" + ChargeTypeName);
		//System.out.println("PlanNamePlanNamePlanName--->" + PlanName);
		
		objectArgs.put("SchemeCode", SchemeCode);
		objectArgs.put("SchemeName", SchemeName);
		objectArgs.put("ChargeTypeName", ChargeTypeName);
		objectArgs.put("PlanName", PlanName);
		
		
		List<OrgPostDetailsRlt> locIds = orgSchemeMstDAOImpl.getLocIds(postId);
		//long locId = locIds.get(0).getCmnLocationMst().getLocId();
		boolean isRoleAdminFlag = false;	 
		if (isRoleAdminFlag) 
		{
			logger.info("true::");
			deptList = locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId );
			
			logger.info("deptlist.size::" + deptList.size());
		} 
		else
		{
			logger.info("false::");
			String[] critariaArugs = { "cmnLanguageMst.langId", "locId" };
			Object[] valueArgus = new Object[2];
			valueArgus[0] = langId;
			valueArgus[1] = locId;
			deptList = locationDAO.getListByColumnAndValue(critariaArugs,valueArgus);
			/*System.out.println("dept list is ::::"+deptList.get(0));
			System.out.println("location code::::"+((CmnLocationMst)deptList.get(0)).getLocationCode());
			System.out.println("location Id is ::::"+((CmnLocationMst)deptList.get(0)).getLocId());*/
			logger.info("deptlist.size::" + deptList.size());
		}
			
			
			
	logger.info("list size is in bhdatalist is :--->>"+ bhDataList.size());
		Iterator it = bhDataList.iterator();
		if (bhDataList != null && bhDataList.size() > 0) 
		{
			Object[] rowList = (Object[]) bhDataList.get(0);

			ohMpg.setBillHeadId(budSubHeadId);
			String demandNo = rowList[2] != null ? rowList[2].toString() : "";
			ohMpg.setDemandNo(demandNo);
			
			String locationCode = rowList[6] != null ? rowList[6].toString() : "0";
			logger.info("location code is:--->>" + locationCode);
			ohMpg.setLocationCode(locationCode);

			long SubHeadCode = rowList[0] != null ? Long.parseLong(rowList[0].toString()) : 0;
			ohMpg.setSubHeadCode(SubHeadCode);
			
			String subHeadId = rowList[7] != null ? rowList[7].toString()	: "";
			//long subHeadId 
			ohMpg.setSubHeadId(Long.parseLong(subHeadId));
			
			String mjrHeadCode = rowList[3] != null ? rowList[3].toString() : "";
			ohMpg.setMjrHead(mjrHeadCode);

			String subMjrHeadCode = rowList[4] != null ? rowList[4].toString() : "";
			ohMpg.setSubMjrHead(subMjrHeadCode);

			String MnrHeadCode = rowList[5] != null ? rowList[5].toString() : "";
			ohMpg.setMinorHead(MnrHeadCode);
			String SubHeadName = rowList[1] != null ? rowList[1].toString() : "";
			ohMpg.setSubHeadName(SubHeadName);
			
			PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			
			OrderHeadMpgDAOImpl orderHeadMpgDAOImpl=new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMstBh = locationDAO.read(Long.parseLong(locationCode != null && !"".equalsIgnoreCase(locationCode.trim()) ? locationCode: "0"));
			List demandNoList = payBillDAO.getDemandNoByLocId(cmnLocationMstBh.getLocationCode(), cmnLanguageMst.getLangShortName(), finYrId);
			//System.out.println("demandNoListdemandNoList" + demandNoList.size());
			List mjrHeadList = payBillDAO.getMjrHeadByDemandNo(demandNo, cmnLanguageMst.getLangShortName(),finYrId);
			//System.out.println("mjrHeadListmjrHeadListmjrHeadList" + mjrHeadList.size());
			List subMjrHeadList = payBillDAO.getSubMjrHeadByMjrHead(demandNo, cmnLanguageMst.getLangShortName(),mjrHeadCode, finYrId);
			//System.out.println("subMjrHeadListsubMjrHeadListsubMjrHeadList" + subMjrHeadList.size());
			List minorHeadList = payBillDAO.getMnrHeadByMjrHead(demandNo, mjrHeadCode, subMjrHeadCode,cmnLanguageMst.getLangShortName(), finYrId);
			//System.out.println("minorHeadListminorHeadList" + minorHeadList.size());
			List subHeadList = payBillDAO.getSubHeadByMnrHead(demandNo,mjrHeadCode, subMjrHeadCode, MnrHeadCode,cmnLanguageMst.getLangShortName(), finYrId);
			//System.out.println("subHeadListsubHeadList" + subHeadList.size());
			
			
			List mjrHeadDataList = new ArrayList();
			List subMjrHeadDataList = new ArrayList();
			List minorHeadDataList = new ArrayList();
			List subHeadDataList = new ArrayList();
			List demandNoDataList = new ArrayList();
			

			for (Iterator iter = demandNoList.iterator(); iter.hasNext();) 
			{
				Object[] row = (Object[]) iter.next();
				HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
				String demand_code = row[1] != null ? row[1].toString(): "";
				ohmpg.setDemandNo(demand_code);
				demandNoDataList.add(ohmpg);
			}
			HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();

			for (Iterator iter = mjrHeadList.iterator(); iter.hasNext();) 
			{
				ohmpg = new HrPayOrderHeadCustomMpg();
				String MjrHead = iter.next().toString();
				ohmpg.setMjrHead(MjrHead);
				mjrHeadDataList.add(ohmpg);
			}
			
			for (Iterator iter = subMjrHeadList.iterator(); iter.hasNext();) 
	       {
				ohmpg = new HrPayOrderHeadCustomMpg();
				String subMjrHead = iter.next().toString();
				ohmpg.setSubMjrHead(subMjrHead);
				subMjrHeadDataList.add(ohmpg);
	       }
			for (Iterator iter = minorHeadList.iterator(); iter.hasNext();) 
			{
				ohmpg = new HrPayOrderHeadCustomMpg();
				String minorHead = iter.next().toString();
				ohmpg.setMinorHead(minorHead);
				minorHeadDataList.add(ohmpg);
			}
			for (Iterator iter = subHeadList.iterator(); iter.hasNext();) 
			{
				Object[] row = (Object[]) iter.next();
				HrPayOrderHeadCustomMpg ohmpg1 = new HrPayOrderHeadCustomMpg();
				
				String SubHeadId = row[0] != null ? row[0].toString(): "";
				String elementcode=orderHeadMpgDAOImpl.getElementCodeFromSubHdId(Long.parseLong(SubHeadId));
				
				String subHeadCode = row[1] != null ? row[1].toString(): "";
				String subHeadName = row[2] != null ? row[2].toString(): "";
				subHeadName = "(" + subHeadCode + ")" + subHeadName;
				ohmpg1.setSubHead(subHeadCode);
				ohmpg1.setSubHeadId(Long.parseLong(SubHeadId));
				ohmpg1.setSubHeadName(subHeadName);
				subHeadDataList.add(ohmpg1);
			}
			
			
			objectArgs.put("demandNoList", demandNoDataList);
			objectArgs.put("mjrHeadList", mjrHeadDataList);
			objectArgs.put("subMjrHeadList", subMjrHeadDataList);
			objectArgs.put("minorHeadList", minorHeadDataList);
			objectArgs.put("subHeadList", subHeadDataList);
			objectArgs.put("hrPayBillHeadMpg", ohMpg);
		}
		
		
		    objectArgs.put("deptList", deptList);
		    objectArgs.put("budSubHeadId", budSubHeadId);
		
		
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("SchemeUpdatePage");
			

		
		}
		catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is some problem. Please Try Again.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
		}

		return resultObject;
	}
	
	
	
	
	
	
	
}
