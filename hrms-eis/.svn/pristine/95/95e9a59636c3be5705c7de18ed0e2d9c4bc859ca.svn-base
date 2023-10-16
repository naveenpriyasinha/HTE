package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.DeptCompMPGDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
import com.tcs.sgv.eis.dao.EmpCompMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderMstDAO;
import com.tcs.sgv.eis.dao.OrderMstDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompGrpMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrPayCompGrpMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import java.util.Iterator;

public class DeptCompMPGServiceImpl extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());

	int msg = 0;

	@SuppressWarnings("unchecked")
	public ResultObject getDeptCompMPGDtlsService(Map objectArgs)
	{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		long applyCmbValue = -1;
		try
		{

			logger.info("DeptCompMPGServiceImpl insertDeptCompMPGDtls Called");
			HttpSession session = request.getSession();
			String flag= "no";
			Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			String lstrFlag=StringUtility.getParameter("flag",request);
			System.out.println("lstrFlag "+lstrFlag);
			if(lstrFlag.equals("") || lstrFlag.equals(null)){
				flag="no";
				System.out.println("in if loop...");
			}
			else flag = "yes";
			
			logger.info("insertDeptCompMPGDtls pprimaryPostIdostId" + postId);

			
			
			if (request.getParameter("applyCmbValue") != null)
			{
				applyCmbValue = StringUtility.convertToLong(request.getParameter("applyCmbValue").toString());
			}

			/*//Started For Deptlist

			List deptList = null;
			//	BillHeadDao billHeadMpgDAOImpl_new = new BillHeadDao(HrPayBillHeadCustomVO.class, serv.getSessionFactory());
			

			List<OrgPostDetailsRlt> locIds = deptcomoMpgDAOImpl.getLocIds(loggedInpostId);
			long locId = locIds.get(0).getCmnLocationMst().getLocId();
			logger.info("insertSchemeDtls location id" + locId);

			boolean isRoleAdminFlag = false;
			if (isRoleAdminFlag)
			{
				logger.info("true::");
				deptList = locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId);

				logger.info("deptlist.size::" + deptList.size());
			}
			else
			{
				logger.info("false::");
				String[] critariaArugs =
				{ "cmnLanguageMst.langId", "locId" };
				Object[] valueArgus = new Object[2];
				valueArgus[0] = langId;
				valueArgus[1] = locId;
				deptList = locationDAO.getListByColumnAndValue(critariaArugs, valueArgus);
				logger.info("dept list is ::::" + deptList.get(0));
				logger.info("location code::::" + ((CmnLocationMst) deptList.get(0)).getLocationCode());
				logger.info("location Id is ::::" + ((CmnLocationMst) deptList.get(0)).getLocId());

				logger.info("deptlist.size::" + deptList.size());
			}
			//Ended For Dept list
*/			
			//for getting office list
			DeptCompMPGDAOImpl deptcomoMpgDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());
			//code to find ddo code by roshan
	       	Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
	   		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
	   		logger.info("locId roshan  "+locId);
	   		PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
	   		List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
	   		OrgDdoMst ddoMst  = null;
	   		if(ddoList!=null && ddoList.size()>0) {
			 ddoMst = ddoList.get(0);
	   		}
		
	   		String ddoCode = null;
	   		if(ddoMst!=null){
	   			ddoCode = ddoMst.getDdoCode();
	   		}
	   		logger.info("ddo code is found as "+ddoCode);
	   		//code to find the district
	   		String districtID=deptcomoMpgDAOImpl.districtName(ddoCode);
	   		logger.info("district id found is"+districtID);
	   		//code to find the taluka
	   		List talukaList=deptcomoMpgDAOImpl.allTaluka(districtID);
	   		
	      
	   		String talukaId=null;
	   		String ddoSelected=null;
	   		if((StringUtility.getParameter("taluka", request)!=null)&&(StringUtility.getParameter("taluka", request)!="")&&(Long.parseLong(StringUtility.getParameter("taluka", request))!=-1)){
	   			talukaId= StringUtility.getParameter("taluka", request);
	   		}
  	   
	   		if((StringUtility.getParameter("ddoCode", request)!=null)&&(StringUtility.getParameter("ddoCode", request)!="")){
	   			ddoSelected= StringUtility.getParameter("ddoCode", request);
	   		}
			List officeList = null;  
			officeList =  deptcomoMpgDAOImpl.getSubDDOs(postId,talukaId,ddoSelected);
			logger.info("in sev impl size "+officeList.size());
			String officeCode = "";
			 //end by roshan
			//	long locId = -1;
			long loggedInpostId ;
			System.out.println("flag "+flag);
			System.out.println("lstrFlag "+lstrFlag);
			String lStrDDOCode= "";
			if(flag.equalsIgnoreCase("yes"))
			{
				System.out.println("in flag yes loop...");
				lStrDDOCode = StringUtility.getParameter("cmbDept", request);
				System.out.println("lstrddo code.."+lStrDDOCode);
				if(lStrDDOCode == null)
					System.out.println("lstrddo code..1");
				
			List officeDetails = deptcomoMpgDAOImpl.getDDODtls(lStrDDOCode);
			
			logger.info("officeDetails*********"+officeDetails);
			
			//long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			
			logger.info("before type casting..");
			logger.info("officeDetails********"+officeDetails);
			
			
			
			Iterator it = officeDetails.iterator();
			Object officeArray[] = null;
			int k =0;
			while(it.hasNext()){
				officeArray = (Object[])it.next();				//k++;
			}
			
			logger.info("Iterator********");
			logger.info("officeArray[0]******"+officeArray[0]);
			
			locId = Long.parseLong(officeArray[0].toString());
			logger.info("locId********");
			logger.info("locID for selected office "+locId);
			postId = Long.parseLong(officeArray[1].toString());
			
			
			logger.info("insertDeptCompMPGDtls loggedInPost" + postId);
			
			
			logger.info("insertDeptCompMPGDtls pprimaryPostIdostId" + postId);
			logger.info("postID for selected office "+postId);
			}
			else {
				loggedInpostId=Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			}
			//for getting office list end		
			
			
			//For Getting Allowances List

			EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class, serv.getSessionFactory());
			//List allowList = empAllwMapDAO.getAllAllowanceType();
			List<HrPayAllowTypeMst> allowList = empAllwMapDAO.getAllAllowanceTypeForDept();
			logger.info("==> in insertDeptCompMPGDtls allowList.size::" + allowList.size());

			//For Getting Deduction List
			DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class, serv.getSessionFactory());
			//List deducActionList = empDuducDtlsDAO.getDeductionType();
			List<HrPayDeducTypeMst> deducActionList = empDuducDtlsDAO.getDeductionTypeForDept();
			logger.info("==> in insertDeptCompMPGDtls deducActionList.size::" + deducActionList.size());
			List<HrPayDeducTypeMst> NonGovdeducActionList = empDuducDtlsDAO.getNonGovDeductionTypeForDept();
			logger.info("==> in insertDeptCompMPGDtls NonGovdeducActionList.size::" + NonGovdeducActionList.size());
			
			

			/*	List LoanAdvList = deptcomoMpgDAOImpl.getLoanAdvList();
				logger.info("==> LoandAdvList :: "+LoanAdvList.size());


				List<HrLoanAdvMst> LoanAndAdvList = new ArrayList();

				for (int i = 0; i < LoanAdvList.size(); i++) 
				{
					HrLoanAdvMst hrLoanAdvMstVO = new HrLoanAdvMst();
					Object[] row = (Object[])LoanAdvList.get(i);

					//hrLoanAdvMstVO.setLoanAdvId(((Long)(row[0])).longValue());
					hrLoanAdvMstVO.setLoanAdvId(((BigDecimal)(row[0])).longValue());
					hrLoanAdvMstVO.setLoanAdvName(row[1].toString());
					logger.info("=====> Loand id ::"+hrLoanAdvMstVO.getLoanAdvId()+"===> Name ::"+hrLoanAdvMstVO.getLoanAdvName());
					LoanAndAdvList.add(hrLoanAdvMstVO);
				}*/
			List<HrPayEdpCompoMpg> LoanAdvList = deptcomoMpgDAOImpl.getLoanAdvList();
			if (LoanAdvList != null && LoanAdvList.size() > 0)
				for (HrPayEdpCompoMpg hrPayEdpCompoMpg : LoanAdvList)
				{
					logger.info("To avoid lazy " + hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpShortName());
				}
			logger.info("===> in sevice size of LoanAdvList :: " + LoanAdvList.size());

			Calendar cal = Calendar.getInstance();
			java.util.Date today = cal.getTime();
			logger.info("===> today :: " + today);
			Date WEFDATE1 = null;
			DeptCompMPGDAOImpl deptCompMPGDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());

			List WefDate = deptCompMPGDAOImpl.getWefDate(locId);
			if (WefDate != null && WefDate.size() > 0)
			{
				WEFDATE1 = (Date) deptCompMPGDAOImpl.getWefDate(locId).get(0);
			}
			//added by roshan
	
	   		logger.info("ddo code is found as "+ddoCode);
	   		//code to find the office type
	   		logger.info("hi i am checking DDO CODE whethre it is of ZP institute type" +ddoCode);
			String Type =ddoCode.substring(0,2);
			logger.info("TypeOfSchoolbefore"+Type);
			Long TypeOfSchool=Long.valueOf(Type);
			String typeOfOffice=null;
			if(TypeOfSchool !=2)
	   		{
	   			typeOfOffice="otherThanZp";
	   		}
	   		else 
	   		 {
	   			typeOfOffice="ZP";
	   		}
			logger.info("typeOfOffice"+typeOfOffice);
			logger.info("TypeOfSchool"+TypeOfSchool);
	   		//code to find the district
			OrderMstDAO orderMasterDAO = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
	   		long countOfDDOCode=orderMasterDAO.findUsertype(ddoCode);
	   		String userType=null;
	   		if(countOfDDOCode !=0)
	   		{
	   			userType="reportingDDO";
	   		}
	   		else 
	   		 {
	   			userType="finalDDO";
	   		}
	   		logger.info("userType"+userType);
	   		// code To Check whethre new entry is disbale or not
	   	//code to find the level by roshan
	   		long level=orderMasterDAO.findLevel(ddoCode);
	   		String displayAddNewEntry=null;
	   		
	   		if(((typeOfOffice.equalsIgnoreCase("otherThanZp")) && (userType.equalsIgnoreCase("reportingDDO")))||(level==4)&&(userType.equalsIgnoreCase("finalDDO"))) 
	   		{
	   			displayAddNewEntry="true";
	   		}
	   		else
	   		{
	   			displayAddNewEntry="false";
	   		}
			logger.info("displayAddNewEntry"+displayAddNewEntry);
	       //end by roshan
			
			
			//end by roshan
			//String WEFDATE1 = (String) deptCompMPGDAOImpl.getWefDate().get(0);
			//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//Date WEFDATE = sdf.parse(WEFDATE1);

			String remarks = deptCompMPGDAOImpl.getRemarks(locId);
			if(lStrDDOCode.equalsIgnoreCase(""))
				lStrDDOCode = String.valueOf(locId);
			logger.info(" WEFDATE1 " + WEFDATE1);
			logger.info("remarks.."+remarks);
			//  objectArgs.put("LoanAndAdvList", LoanAndAdvList);
			objectArgs.put("DeptlocId", locId);
			objectArgs.put("DeptId", lStrDDOCode);
			objectArgs.put("LoanAdvList", LoanAdvList);
			objectArgs.put("LoanAdvListSize", LoanAdvList.size());
			objectArgs.put("AllowSize", allowList.size());
			objectArgs.put("DeductionSize", deducActionList.size());
			objectArgs.put("deducActionList", deducActionList);
			objectArgs.put("NonGovDeducSize", NonGovdeducActionList.size());
			objectArgs.put("NonGovDeducList", NonGovdeducActionList);
			objectArgs.put("allowList", allowList);
			//objectArgs.put("deptList", deptList);
			objectArgs.put("result", "success");
			objectArgs.put("current_date", today);
			objectArgs.put("WEFDATE", WEFDATE1);
			objectArgs.put("remarks", remarks);
			objectArgs.put("flag", flag);	
			 //added by roshan
			objectArgs.put("displayAddNewEntry", displayAddNewEntry);
			objectArgs.put("talukaList", talukaList);
	    	objectArgs.put("talukaId", talukaId);
	    	objectArgs.put("ddoSelected", ddoSelected);
	    	   //end by roshan
			objectArgs.put("officeList", officeList);
			objectArgs.put("applyCmbValue", applyCmbValue);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("DeptCompMpg");

		}
		catch (Exception e)
		{

			logger.info("Exception Ocuures...getDeptCompMPGDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
		}
		return resultObject;
	}

	@SuppressWarnings("unchecked")
	public ResultObject InsertDeptCompMPGDtlsService(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		System.out.println("serviceLocator  "+serv.toString());
		
		try
		{
			logger.info("Coming into the insert Service method insertDeptCompMPGDtls");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String lStrDDOCode = StringUtility.getParameter("cmbDept", request);
			logger.info("selceted ddo code "+lStrDDOCode);
			
			HrPayLocComMpg hrpayloccompmpgVO = null;

			//List lArrallowList=new ArrayList();
			//List lArrDeductList=new ArrayList();

			String[] lArrallowList = null;
			String[] lArrDeductList = null;
			String[] lArrLoanList = null;

			long cmbDept = 0;
			long hdncheckedvalueofAllow = 0;
			long hdncheckedvalueofdeduct = 0;
			long hdncheckedSizeofLoan = 0;
			long applyCmbValue = -1;

			/*long hdnAllowList=0;
					long hdnDeductList=0;*/

			if (objectArgs.get("cmbDept") != null)
			{
				cmbDept = Long.parseLong(objectArgs.get("cmbDept").toString());
				logger.info("cmbDept" + cmbDept);
			}
			if (objectArgs.get("lArrallowList") != null)
			{
				lArrallowList = (String[]) (objectArgs.get("lArrallowList"));
				logger.info("lArrallowList" + lArrallowList);
			}
			if (objectArgs.get("lArrDeductList") != null)
			{
				lArrDeductList = (String[]) (objectArgs.get("lArrDeductList"));
				logger.info("lArrDeductList" + lArrDeductList);
			}
			if (objectArgs.get("lArrLoanList") != null)
			{
				lArrLoanList = (String[]) (objectArgs.get("lArrLoanList"));
				logger.info("lArrLoanList" + lArrLoanList);
			}
			/*if(objectArgs.get("hdnAllowList")!=null)
					{
						hdnAllowList = Long.parseLong(objectArgs.get("hdnAllowList").toString());
					}
					if(objectArgs.get("hdnDeductList")!=null)
					{
						hdnDeductList = Long.parseLong(objectArgs.get("hdnDeductList").toString());
					}*/
			if (objectArgs.get("hdncheckedvalueofAllow") != null)
			{
				hdncheckedvalueofAllow = Long.parseLong(objectArgs.get("hdncheckedvalueofAllow").toString());
			}
			if (objectArgs.get("hdncheckedvalueofdeduct") != null)
			{
				hdncheckedvalueofdeduct = Long.parseLong(objectArgs.get("hdncheckedvalueofdeduct").toString());
			}
			if (objectArgs.get("hdncheckedSizeofLoan") != null)
			{
				hdncheckedSizeofLoan = Long.parseLong(objectArgs.get("hdncheckedSizeofLoan").toString());
			}

			List<Long> newMappingAllow = new ArrayList<Long>();
			List<Long> newMappingDeduc = new ArrayList<Long>();
			if (!lArrallowList.equals("") && lArrallowList != null && lArrallowList.length > 1)
			{
				for (int i = 0; i < lArrallowList.length; i++)
				{
					newMappingAllow.add(Long.parseLong(lArrallowList[i]));
				}
			}
			logger.info("lArrDeductList size "+lArrDeductList.length);
			if (!lArrDeductList.equals("") && lArrDeductList != null && lArrDeductList.length > 1)
			{
				for (int i = 0; i < lArrDeductList.length; i++)
				{
					newMappingDeduc.add(Long.parseLong(lArrDeductList[i]));
				}
			}
			logger.info("===> in sercvice hdncheckedSizeofLoan :: " + hdncheckedSizeofLoan);
			logger.info("===> in sercvice lArrLoanList :: " + lArrLoanList.length);
			
			
			//inserting office data
			DeptCompMPGDAOImpl deptcomoMpgDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());
			List officeDetails = deptcomoMpgDAOImpl.getDDODtls(lStrDDOCode);
			
			//long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			
			logger.info("before type casting..");
			
			Iterator it = officeDetails.iterator();
			Object officeArray[] = null;
			int k =0;
			while(it.hasNext()){
				officeArray = (Object[])it.next();
				
				//k++;
			}
			long locId = Long.parseLong(officeArray[0].toString());
			logger.info("locID for selected office "+locId);
			long postId = Long.parseLong(officeArray[1].toString());
			logger.info("postID for selected office "+postId);
			
			Date WEFDATE = null;
			long Month = 0;
			long Year = 0;
			String Remarks = "";
			String lStrWEFDATE = "";

			if (objectArgs.get("lStrWEFDATE") != null)
			{
				lStrWEFDATE = (objectArgs.get("lStrWEFDATE").toString());
			}

			if (objectArgs.get("Remarks") != null)
			{
				Remarks = (objectArgs.get("Remarks").toString());
			}
			if (objectArgs.get("WEFDATE") != null)
			{
				WEFDATE = StringUtility.convertStringToDate(lStrWEFDATE);
			}
			if (objectArgs.get("applyCmbValue") != null)
			{
				applyCmbValue = StringUtility.convertToLong(objectArgs.get("applyCmbValue").toString());
			}

			Month = Long.valueOf(lStrWEFDATE.substring(3, 5));
			Year = Long.valueOf(lStrWEFDATE.substring(6, 10));
			logger.info("====> Month :: " + Month + "==> Year :: " + Year);
			logger.info("====> in service Remarks :: " + Remarks);
			logger.info("====> in service WEFDATE :: " + WEFDATE);
			logger.info("====> in service cmbDept :: " + cmbDept);
			logger.info("====> in service lArrallowList :: " + lArrallowList);
			logger.info("====> in service lArrallowList length :: " + lArrallowList.length);
			logger.info("====> in service lArrDeductList :: " + lArrDeductList);
			//logger.info("====> in service lArrallowList :: "+lArrallowList[0]);
			/*logger.info("====> in service hdnAllowList :: "+hdnAllowList);
					logger.info("====> in service hdnDeductList :: "+hdnDeductList);*/
			logger.info("====> in service hdncheckedvalueofAllow :: " + hdncheckedvalueofAllow);
			logger.info("====> in service hdncheckedvalueofdeduct :: " + hdncheckedvalueofdeduct);

			//long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			logger.info("cmnLocationMst:::::::::" + cmnLocationMst);

			long AllownID = 2500134;
			long DeductID = 2500135;

			GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(CmnLookupMst.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			CmnLookupMst cmnLookupMstAllowID = (CmnLookupMst) genDao.read(AllownID);
			CmnLookupMst cmnLookupMstDeductID = (CmnLookupMst) genDao.read(DeductID);
			cmnLocationMst.getLocId();
			cmnLookupMstAllowID.getLookupId();

			//DeptCompMPGDAOImpl deptcomoMpgDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());
			logger.info("====> value of cheked Allowance :: " + hdncheckedvalueofAllow);

			//long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			List<HrPayCompGrpMst> ListHrPayCompGrpMst = null;
			ListHrPayCompGrpMst = (List<HrPayCompGrpMst>) deptcomoMpgDAOImpl.getMstDataIsPresent(Month, Year, locId);

			List<Long> previousDeducList = deptcomoMpgDAOImpl.getActiveDeducComponents(locId);
			List<Long> previousAllowList = deptcomoMpgDAOImpl.getActiveAllComponents(locId);

			List ListhrPayComFromLoc = null;
			ListhrPayComFromLoc = (List) deptcomoMpgDAOImpl.getMstDataFromLocID(locId);
			HrPayCompGrpMst ForUpdateCompGrpMst = null;

			logger.info("==> in service ListHrPayCompGrpMst :: " + ListHrPayCompGrpMst.size());
			HrPayCompGrpMst OldMstVo = null;
			long grpId = 0;

			if (ListHrPayCompGrpMst != null && ListHrPayCompGrpMst.size() > 0)
			{
				grpId = ListHrPayCompGrpMst.get(0).getCompoGrpId();
				OldMstVo = ListHrPayCompGrpMst.get(0);
			}

			logger.info("==> grpId :: " + grpId);
			//logger.info("=====> after getting VO getCompoGrpId :: "+OldMstVo.getCompoGrpId());

			HrPayCompGrpMst NewhrpaycompgrpMst = new HrPayCompGrpMst();
			//HrPayCompGrpMst ForIDVO = null;

			if (ListHrPayCompGrpMst.size() == 0)
			{
				List<HrPayLocComMpg> list = new ArrayList<HrPayLocComMpg>();

				for (int i = 0; i < ListhrPayComFromLoc.size(); i++)
				{
					logger.info("===> in ListhrPayComFromLoc save and update method :: " + i);
					ForUpdateCompGrpMst = (HrPayCompGrpMst) ListhrPayComFromLoc.get(i);
					logger.info("====> id in for :: " + ForUpdateCompGrpMst.getCompoGrpId());
					ForUpdateCompGrpMst.setIsactive(0);
					serv.getSessionFactory().getCurrentSession().saveOrUpdate(ForUpdateCompGrpMst);

				}

				IdGenerator idGen = new IdGenerator();
				Long id = idGen.PKGenerator("HR_PAY_COMPONENT_GRP_MST", objectArgs);
				//Long id= IDGenerateDelegate.getNextId("HR_PAY_COMPONENT_GRP_MST",objectArgs);
				logger.info(" HR_PAY_COMPONENT_GRP_MST ******************the id generated is " + id);
				NewhrpaycompgrpMst.setCompoGrpId(id);
				NewhrpaycompgrpMst.setMonth(Month);
				NewhrpaycompgrpMst.setYear(Year);
				NewhrpaycompgrpMst.setCmnLocationMst(cmnLocationMst);
				NewhrpaycompgrpMst.setIsactive(1);
				NewhrpaycompgrpMst.setWefDate(WEFDATE);
				
				NewhrpaycompgrpMst.setRemarks(Remarks);
				logger.info("===> value of h Remarks :: " + Remarks);
				NewhrpaycompgrpMst.setCreatedBy(orgPostMst);
				NewhrpaycompgrpMst.setCreatedDate(new Date());
				NewhrpaycompgrpMst.setUpdatedBy(orgPostMst);
				logger.info("===> value of h Remarks :: " + NewhrpaycompgrpMst.getRemarks());
				//ForIDVO = NewhrpaycompgrpMst;
				serv.getSessionFactory().getCurrentSession().saveOrUpdate(NewhrpaycompgrpMst);

				//Allowance Insert
				for (int h = 0; h < lArrallowList.length; h++)
				{
					hrpayloccompmpgVO = new HrPayLocComMpg();
					logger.info("===> value of h AllowList :: " + h);
					Long id1 = idGen.PKGenerator("HR_PAY_LOC_COMPONENT_MPG", objectArgs);
					//Long id1= IDGenerateDelegate.getNextId("HR_PAY_LOC_COMPONENT_MPG",objectArgs);
					logger.info("****************************the id generated is " + id);
					hrpayloccompmpgVO.setId(id1);
					logger.info("****************************the id1111 generated is " + id1);
					logger.info("****************************the comp id is " +lArrallowList[h]);
					hrpayloccompmpgVO.setCompId(Long.valueOf(lArrallowList[h]));
					
					hrpayloccompmpgVO.setCmnLookupMst(cmnLookupMstAllowID);
					logger.info("****************************the cmnlookupmst is " +cmnLookupMstAllowID);
					hrpayloccompmpgVO.setHrpaycompgrpmst(NewhrpaycompgrpMst);
					logger.info("****************************the Hrpaycompgrpms is " +NewhrpaycompgrpMst);
					hrpayloccompmpgVO.setIsactive(1);
					hrpayloccompmpgVO.setUpdatedByPost(postId);
					hrpayloccompmpgVO.setUpdatedDate(new Date());
					list.add(hrpayloccompmpgVO);
					//serv.getSessionFactory().getCurrentSession().save(hrpayloccompmpgVO);
					logger.info("====> hrpayloccompmpgVO.getId() :: " + hrpayloccompmpgVO.getId());

				}
				logger.info("====> 1 list.size :: " + list.size());
				for (int i = 0; i < list.size(); i++)
				{
					logger.info("===> in save and update method :: " + i);
					HrPayLocComMpg hep = (HrPayLocComMpg) list.get(i);
					logger.info("====> id in for :: " + hep.getId());
					serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep);
					/*// added by khushal
								long userIdFromVogen=Long.valueOf(objectArgs.get("UserId").toString());
								OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
								OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);	    
								CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
								Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
								long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
								CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);					
								Set set=orgUserMstNew.getOrgUserpostRlts();
								OrgUserpostRlt tempObj = (OrgUserpostRlt)set.iterator().next();

								Map mapForChangedRecords=objectArgs;

								mapForChangedRecords.put("changedPostId",tempObj.getOrgPostMstByPostId().getPostId());
								//mapForChangedRecords.put("changedEmpId",empID);
								mapForChangedRecords.put("serviceLocator",serv);
								mapForChangedRecords.put("locId", locId);
								mapForChangedRecords.put("OrgPostMst",tempObj.getOrgPostMstByPostId());
								mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
								mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
								GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
								long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
								System.out.println("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
								logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);*/

					//Ended by khushal
					objectArgs.put("msg", "Records Inserted Successfully");
				}
				// End of Allowance Insert

				// Deduction Insert
				List<HrPayLocComMpg> listDeduct = new ArrayList<HrPayLocComMpg>();
				for (int h = 0; h < lArrDeductList.length; h++)
				{
					hrpayloccompmpgVO = new HrPayLocComMpg();
					logger.info("===> value of h AllowList :: " + h);
					Long id2 = idGen.PKGenerator("HR_PAY_LOC_COMPONENT_MPG", objectArgs);
					//Long id2=IDGenerateDelegate.getNextId("HR_PAY_LOC_COMPONENT_MPG",objectArgs);
					logger.info("****************************the id generated is " + id2);
					hrpayloccompmpgVO.setId(id2);
					logger.info("The vALue of compo id is as follows:"+lArrDeductList[h]);
					hrpayloccompmpgVO.setCompId(Long.valueOf(lArrDeductList[h]));
					hrpayloccompmpgVO.setCmnLookupMst(cmnLookupMstDeductID);
					hrpayloccompmpgVO.setHrpaycompgrpmst(NewhrpaycompgrpMst);
					hrpayloccompmpgVO.setIsactive(1);
					hrpayloccompmpgVO.setUpdatedByPost(postId);
					hrpayloccompmpgVO.setUpdatedDate(new Date());
					//serv.getSessionFactory().getCurrentSession().save(hrpayloccompmpgVODeduct);
					logger.info("====> hrpayloccompmpgVODeduct.getId() :: " + hrpayloccompmpgVO.getId());
					listDeduct.add(hrpayloccompmpgVO);

				}
				GenericDaoHibernateImpl genDao1 = new GenericDaoHibernateImpl(HrPayLocComMpg.class);
				genDao1.setSessionFactory(serv.getSessionFactory());
				HrPayLocComMpg hepdedu = null;
				logger.info("====> 2 listDeduct.size :: " + listDeduct.size());
				for (int i = 0; i < listDeduct.size(); i++)
				{
					logger.info("===> in save and update method :: " + i);
					hepdedu = (HrPayLocComMpg) listDeduct.get(i);
					logger.info("====> id in for :: " + hepdedu.getId());
					genDao1.create(hepdedu);
					/*//added by khushal
								long userIdFromVogen=Long.valueOf(objectArgs.get("UserId").toString());
								OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
								OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);	    
								CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
								Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
								long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
								CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);					
								Set set=orgUserMstNew.getOrgUserpostRlts();
								OrgUserpostRlt tempObj = (OrgUserpostRlt)set.iterator().next();

								Map mapForChangedRecords=objectArgs;

								mapForChangedRecords.put("changedPostId",tempObj.getOrgPostMstByPostId().getPostId());
								//mapForChangedRecords.put("changedEmpId",empID);
								mapForChangedRecords.put("serviceLocator",serv);
								mapForChangedRecords.put("locId", locId);
								mapForChangedRecords.put("OrgPostMst",tempObj.getOrgPostMstByPostId());
								mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
								mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
								GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
								long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
								System.out.println("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
								logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);*/

					//ended by khushal
					objectArgs.put("msg", "Records Inserted Successfully");
				}
				// End Deduction Insert

				// Loan Insert
				logger.info("==> Loan Insert Startd...............");
				long cmnLookupMstIdFromLoanId = 0;
				if ((lArrLoanList != null)&&(hdncheckedSizeofLoan!=0))
				{
					logger.info("==> LoanList != null ");
					List<HrPayLocComMpg> listLoan = new ArrayList<HrPayLocComMpg>();
					for (int h = 0; h < lArrLoanList.length; h++)
					{

						hrpayloccompmpgVO = new HrPayLocComMpg();
						logger.info("===> value of h AllowList :: " + h);
						IdGenerator idGen3 = new IdGenerator();
						Long id3 = idGen3.PKGenerator("HR_PAY_LOC_COMPONENT_MPG", objectArgs);
						//Long id3= IDGenerateDelegate.getNextId("HR_PAY_LOC_COMPONENT_MPG",objectArgs);
						logger.info("****************************the id generated is LoanList :: " + id3);
						hrpayloccompmpgVO.setId(id3);
						hrpayloccompmpgVO.setCompId(Long.valueOf(lArrLoanList[h]));
						//	Added For CmnLookupMst
						long LoanId = StringUtility.convertToLong(lArrLoanList[h]);
						String lStrLoanId = "";
						lStrLoanId = String.valueOf(LoanId);
						cmnLookupMstIdFromLoanId = deptcomoMpgDAOImpl.getCmnLookUpIdFromLoanId(lStrLoanId);
						logger.info("==> in service cmnLookupMstIdFromLoanId :: " + cmnLookupMstIdFromLoanId);

						CmnLookupMst cmnLookupMstFromLoanIDVO = (CmnLookupMst) genDao.read(cmnLookupMstIdFromLoanId);
						//	End of For CmnLookupMst
						hrpayloccompmpgVO.setCmnLookupMst(cmnLookupMstFromLoanIDVO);
						hrpayloccompmpgVO.setHrpaycompgrpmst(NewhrpaycompgrpMst);
						hrpayloccompmpgVO.setIsactive(1);
						hrpayloccompmpgVO.setUpdatedByPost(postId);
						hrpayloccompmpgVO.setUpdatedDate(new Date());
						//serv.getSessionFactory().getCurrentSession().save(hrpayloccompmpgVODeduct);
						logger.info("====> LoanList hrpayloccompmpgVODeduct.getId() :: " + hrpayloccompmpgVO.getId());
						listLoan.add(hrpayloccompmpgVO);
					}
					GenericDaoHibernateImpl genDao2 = new GenericDaoHibernateImpl(HrPayLocComMpg.class);
					genDao2.setSessionFactory(serv.getSessionFactory());
					HrPayLocComMpg heploan = null;
					logger.info("====> 3 LoanList.size :: " + listLoan.size());
					for (int i = 0; i < listLoan.size(); i++)
					{
						logger.info("===> in save and update method LoanList :: " + i);
						heploan = (HrPayLocComMpg) listLoan.get(i);
						logger.info("====> id in for LoanList :: " + heploan.getId());
						genDao2.create(heploan);

					}
				}
				// End Loan Insert
				// added by khushal

				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());

				long userIdFromVogen = Long.valueOf(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
				OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);
				CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
				Set set = orgUserMstNew.getOrgUserpostRlts();
				OrgUserpostRlt tempObj = (OrgUserpostRlt) set.iterator().next();

				Map mapForChangedRecords = objectArgs;

				mapForChangedRecords.put("changedPostId", tempObj.getOrgPostMstByPostId().getPostId());
				//mapForChangedRecords.put("changedEmpId",empID);
				mapForChangedRecords.put("serviceLocator", serv);
				mapForChangedRecords.put("locId", locId);
				mapForChangedRecords.put("OrgPostMst", tempObj.getOrgPostMstByPostId());
				mapForChangedRecords.put("OrgUserMst", orgUserMstNew);
				mapForChangedRecords.put("cmnDatabaseMst", cmnDatabaseMst);
				GenerateBillServiceHelper generateBillServiceHelper = new GenerateBillServiceHelper();
				long changedRecordPK = generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is" + changedRecordPK);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is" + changedRecordPK);
				//ended by khushal
				objectArgs.put("msg", "Records Inserted Successfully");

			}
			else
			{
				List hrPayComponentGrpMstList = null;
				
				//hrPayComponentGrpMstList = (List) deptcomoMpgDAOImpl.getCompGrpIDisPresent(cmbDept, Month, Year, 1);
				hrPayComponentGrpMstList = (List) deptcomoMpgDAOImpl.getCompGrpIDisPresent(locId, Month, Year, 1);
				if (hrPayComponentGrpMstList != null && hrPayComponentGrpMstList.size() != 0)
				{
					logger.info("===> HrPayLocMpgList :: " + hrPayComponentGrpMstList.size());
					for (int i = 0; i < hrPayComponentGrpMstList.size(); i++)
					{
						logger.info("===> in getDataIDisPresent save and update method :: " + i);
						HrPayCompGrpMst hep = (HrPayCompGrpMst) hrPayComponentGrpMstList.get(i);
						logger.info("====> id in for :: " + hep.getCompoGrpId());
						//hep.setIsactive(1);
						hep.setWefDate(WEFDATE);
						logger.info("in else WEFDATE "+WEFDATE);
						hep.setRemarks(Remarks);
						logger.info("in else Remarks "+Remarks);
						hep.setUpdatedDate(new Date());
						serv.getSessionFactory().getCurrentSession().update(hep);
						logger.info("====> update isactive 0 ");
					}
				}

				/*IdGenerator idGen1 = new IdGenerator();
				Long id1= idGen1.PKGenerator("HR_PAY_COMPONENT_GRP_MST",objectArgs);
				logger.info(" HR_PAY_COMPONENT_GRP_MST ******************the id generated is "+id1);
				NewhrpaycompgrpMst.setCompoGrpId(id1);
				NewhrpaycompgrpMst.setMonth(Month);
				NewhrpaycompgrpMst.setYear(Year);
				NewhrpaycompgrpMst.setCmnLocationMst(cmnLocationMst);
				NewhrpaycompgrpMst.setIsactive(1);
				NewhrpaycompgrpMst.setWefDate(WEFDATE);
				NewhrpaycompgrpMst.setRemarks(Remarks);
				NewhrpaycompgrpMst.setCreatedBy(orgPostMst);
				NewhrpaycompgrpMst.setCreatedDate(new Date());
				NewhrpaycompgrpMst.setUpdatedBy(orgPostMst);
				serv.getSessionFactory().getCurrentSession().saveOrUpdate(NewhrpaycompgrpMst);*/
				//by khushal

				logger.info("====> In else  ");

				//ended by khushal
				//ForIDVO = NewhrpaycompgrpMst;

				List<HrPayLocComMpg> list = new ArrayList<HrPayLocComMpg>();

				//For Allowance Insert.......................
				HrPayLocComMpg hrPayLocComMpg = null;

				for (int i = 0; i < hdncheckedvalueofAllow; i++)
				{
					long allowId = StringUtility.convertToLong(lArrallowList[i]);
					hrPayLocComMpg = deptcomoMpgDAOImpl.getDataIDisPresent(locId, allowId, grpId, AllownID);

					if (hrPayLocComMpg == null)
					{
						hrpayloccompmpgVO = new HrPayLocComMpg();
						logger.info("===in if........");
						logger.info(" i " + i);
						IdGenerator idGen = new IdGenerator();
						Long id = idGen.PKGenerator("HR_PAY_LOC_COMPONENT_MPG", objectArgs);
						//Long id=IDGenerateDelegate.getNextId("HR_PAY_LOC_COMPONENT_MPG",objectArgs);
						logger.info("****************************the id generated is " + id);
						hrpayloccompmpgVO.setId(id);
						hrpayloccompmpgVO.setCompId(Long.valueOf(lArrallowList[i]));
						hrpayloccompmpgVO.setCmnLookupMst(cmnLookupMstAllowID);
						//hrpayloccompmpgVO.setCmnLocationMst(cmnLocationMst);
						//hrpayloccompmpgVO.getHrpaycomgrpmst().getCmnLocationMst();
						hrpayloccompmpgVO.setIsactive(1);
						hrpayloccompmpgVO.setUpdatedByPost(postId);
						hrpayloccompmpgVO.setUpdatedDate(new Date());
						hrpayloccompmpgVO.setHrpaycompgrpmst(OldMstVo);
						logger.info("====> hrpayloccompmpgVO.getId() :: " + hrpayloccompmpgVO.getHrpaycompgrpmst().getCompoGrpId());
						serv.getSessionFactory().getCurrentSession().save(hrpayloccompmpgVO);
						list.add(hrpayloccompmpgVO);
					}
					else
					//If Data is Already Exists as 0 the Going to Active Data as 1
					{
						logger.info("===in else........");
						hrPayLocComMpg.setCmnLookupMst(cmnLookupMstAllowID);
						//hrPayLocComMpg.setCmnLocationMst(cmnLocationMst);
						//hrPayLocComMpg.getHrpaycomgrpmst().getCmnLocationMst();
						hrPayLocComMpg.setHrpaycompgrpmst(OldMstVo);
						hrPayLocComMpg.setIsactive(1);
						hrPayLocComMpg.setUpdatedByPost(postId);
						hrPayLocComMpg.setUpdatedDate(new Date());
						serv.getSessionFactory().getCurrentSession().update(hrPayLocComMpg);
						list.add(hrPayLocComMpg);
					}
				}

				logger.info("==> list size before for :: " + list.size());
//				for (int i = 0; i < list.size(); i++)
//				{
//					logger.info("===> in save and update method :: " + i);
//					HrPayLocComMpg hep = (HrPayLocComMpg) list.get(i);
//					logger.info("====> id in for :: " + hep.getId());
//					serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep);
//
//				}
				//Allowance Insert End...........................

				//Deduction Inser Start...................
				List<HrPayLocComMpg> listDeduct = new ArrayList<HrPayLocComMpg>();
				for (int i = 0; i < hdncheckedvalueofdeduct; i++)
				{
					long DeductId = StringUtility.convertToLong(lArrDeductList[i]);
					hrPayLocComMpg = deptcomoMpgDAOImpl.getDataIDisPresent(locId, DeductId, grpId, DeductID);

					if (hrPayLocComMpg == null)
					{
						hrpayloccompmpgVO = new HrPayLocComMpg();
						logger.info("===in if........");
						logger.info("Deduc i " + i);
						IdGenerator idGen = new IdGenerator();
						Long id = idGen.PKGenerator("HR_PAY_LOC_COMPONENT_MPG", objectArgs);
						//Long id=IDGenerateDelegate.getNextId("HR_PAY_LOC_COMPONENT_MPG",objectArgs);
						logger.info("****************************the id generated is " + id);
						hrpayloccompmpgVO.setId(id);
						hrpayloccompmpgVO.setCompId(Long.valueOf(lArrDeductList[i]));
						hrpayloccompmpgVO.setCmnLookupMst(cmnLookupMstDeductID);
						//hrpayloccompmpgVO.setCmnLocationMst(cmnLocationMst);
						//hrpayloccompmpgVO.getHrpaycomgrpmst().getCmnLocationMst();
						hrpayloccompmpgVO.setHrpaycompgrpmst(OldMstVo);
						hrpayloccompmpgVO.setIsactive(1);
						hrpayloccompmpgVO.setUpdatedByPost(postId);
						hrpayloccompmpgVO.setUpdatedDate(new Date());
						logger.info("====> hrpayloccompmpgVO.getId() :: " + hrpayloccompmpgVO.getId());
						listDeduct.add(hrpayloccompmpgVO);
					}
					else
					//If Data is Already Exists as 0 the Going to Active Data as 1
					{
						logger.info("===in else........");
						hrPayLocComMpg.setCmnLookupMst(cmnLookupMstDeductID);
						//hrPayLocComMpg.setCmnLocationMst(cmnLocationMst);
						//hrPayLocComMpg.getHrpaycomgrpmst().getCmnLocationMst();
						hrPayLocComMpg.setHrpaycompgrpmst(OldMstVo);
						hrPayLocComMpg.setIsactive(1);
						hrPayLocComMpg.setUpdatedByPost(postId);
						hrPayLocComMpg.setUpdatedDate(new Date());
						listDeduct.add(hrPayLocComMpg);
					}
				}

				logger.info("==> list size before for :: " + listDeduct.size());
				for (int i = 0; i < listDeduct.size(); i++)
				{
					logger.info("===> in save and update method :: " + i);
					HrPayLocComMpg hep = (HrPayLocComMpg) listDeduct.get(i);
					logger.info("====> id in for :: " + hep.getId());
					serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep);

					/*// added by khushal
								long userIdFromVogen=Long.valueOf(objectArgs.get("UserId").toString());
								OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
								OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);	    
								CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
								Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
								long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
								CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);					
								Set set=orgUserMstNew.getOrgUserpostRlts();
								OrgUserpostRlt tempObj = (OrgUserpostRlt)set.iterator().next();

								Map mapForChangedRecords=objectArgs;

								mapForChangedRecords.put("changedPostId",tempObj.getOrgPostMstByPostId().getPostId());
								//mapForChangedRecords.put("changedEmpId",empID);
								mapForChangedRecords.put("serviceLocator",serv);
								mapForChangedRecords.put("locId", locId);
								mapForChangedRecords.put("OrgPostMst",tempObj.getOrgPostMstByPostId());
								mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
								mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
								GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
								long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
								System.out.println("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
								logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
								//ended by khushal
					 */

					objectArgs.put("msg", "Records Updated Successfully");
				}
				//Deduction Insert End........................

				//Loan and Adv Insert Start...................
				logger.info("==> in else update or Insert before Loan Insert ");
				long cmnLookupMstIdFromLoanId = 0;
				List<HrPayLocComMpg> listLoan = new ArrayList<HrPayLocComMpg>();
				for (int i = 0; i < hdncheckedSizeofLoan; i++)
				{
					logger.info("==> in else update or Insert Loan Insert ");
					long LoanId = StringUtility.convertToLong(lArrLoanList[i]);
					logger.info("===> lArrLoanList[i] :: " + lArrLoanList[i]);
					String lStrLoanId = "";
					lStrLoanId = String.valueOf(LoanId);
					cmnLookupMstIdFromLoanId = deptcomoMpgDAOImpl.getCmnLookUpIdFromLoanId(lStrLoanId);
					logger.info("==> in service cmnLookupMstIdFromLoanId :: " + cmnLookupMstIdFromLoanId);

					hrPayLocComMpg = deptcomoMpgDAOImpl.getDataIDisPresent(locId, LoanId, grpId, cmnLookupMstIdFromLoanId);

					CmnLookupMst cmnLookupMstFromLoanIDVO = (CmnLookupMst) genDao.read(cmnLookupMstIdFromLoanId);

					if (hrPayLocComMpg == null)
					{
						logger.info("===in loan if insert ");
						hrpayloccompmpgVO = new HrPayLocComMpg();
						logger.info("LoanList i " + i);
						IdGenerator idGen = new IdGenerator();
						Long id = idGen.PKGenerator("HR_PAY_LOC_COMPONENT_MPG", objectArgs);
						//Long id=IDGenerateDelegate.getNextId("HR_PAY_LOC_COMPONENT_MPG",objectArgs);
						logger.info("****************************the id generated is Loan ::" + id);
						hrpayloccompmpgVO.setId(id);
						hrpayloccompmpgVO.setCompId(Long.valueOf(lArrLoanList[i]));
						hrpayloccompmpgVO.setCmnLookupMst(cmnLookupMstFromLoanIDVO);
						//hrpayloccompmpgVO.setCmnLocationMst(cmnLocationMst);
						//hrpayloccompmpgVO.getHrpaycomgrpmst().getCmnLocationMst();
						hrpayloccompmpgVO.setHrpaycompgrpmst(OldMstVo);
						hrpayloccompmpgVO.setIsactive(1);
						hrpayloccompmpgVO.setUpdatedByPost(postId);
						hrpayloccompmpgVO.setUpdatedDate(new Date());
						logger.info("====> hrpayloccompmpgVO.getId() :: " + hrpayloccompmpgVO.getId());
						listLoan.add(hrpayloccompmpgVO);
					}
					else
					//If Data is Already Exists as 0 the Going to Active Data as 1
					{
						logger.info("===in else loan update ........");
						hrPayLocComMpg.setCmnLookupMst(cmnLookupMstFromLoanIDVO);
						//hrPayLocComMpg.setCmnLocationMst(cmnLocationMst);
						//hrPayLocComMpg.getHrpaycomgrpmst().getCmnLocationMst();
						hrPayLocComMpg.setHrpaycompgrpmst(OldMstVo);
						hrPayLocComMpg.setIsactive(1);
						hrPayLocComMpg.setUpdatedByPost(postId);
						hrPayLocComMpg.setUpdatedDate(new Date());
						listLoan.add(hrPayLocComMpg);
					}
				}

				logger.info("==> list LoanList size before for :: " + listLoan.size());
				for (int i = 0; i < listLoan.size(); i++)
				{
					logger.info("===> in save and update method :: " + i);
					HrPayLocComMpg hep = (HrPayLocComMpg) listLoan.get(i);
					logger.info("====> id in for :: " + hep.getId());
					serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep);
					/*// added by khushal
								long userIdFromVogen=Long.valueOf(objectArgs.get("UserId").toString());
								OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
								OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);	    
								CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
								Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
								long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
								CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);					
								Set set=orgUserMstNew.getOrgUserpostRlts();
								OrgUserpostRlt tempObj = (OrgUserpostRlt)set.iterator().next();

								Map mapForChangedRecords=objectArgs;

								mapForChangedRecords.put("changedPostId",tempObj.getOrgPostMstByPostId().getPostId());
								//mapForChangedRecords.put("changedEmpId",empID);
								mapForChangedRecords.put("serviceLocator",serv);
								mapForChangedRecords.put("locId", locId);
								mapForChangedRecords.put("OrgPostMst",tempObj.getOrgPostMstByPostId());
								mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
								mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
								GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
								long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
								System.out.println("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
								logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
								//ended by khushal
					 */objectArgs.put("msg", "Records Updated Successfully");
				}
				//End of Loan and Adv Insert ........................

				// For Getting data ISActive or Not??
				List HrPayLocMpgList = null;

				//		HrPayLocMpgList=(List) deptcomoMpgDAOImpl.getDataIDisPresent(lArrallowList,lArrDeductList,lArrLoanList,hdncheckedvalueofAllow,hdncheckedvalueofdeduct,hdncheckedSizeofLoan,grpId ) ;
				//Added By Amish
				if(lArrallowList.length > 1 && lArrallowList != null)
					HrPayLocMpgList = (List) deptcomoMpgDAOImpl.getDataIDisPresentForAllow(lArrallowList, hdncheckedvalueofAllow, grpId);

				if (HrPayLocMpgList != null && HrPayLocMpgList.size() != 0)
				{
					logger.info("===> HrPayLocMpgList :: " + HrPayLocMpgList.size());
					for (int i = 0; i < HrPayLocMpgList.size(); i++)
					{
						logger.info("===> in getDataIDisPresent save and update method :: " + i);
						HrPayLocComMpg hep = (HrPayLocComMpg) HrPayLocMpgList.get(i);
						logger.info("====> id in for :: " + hep.getId());
						hep.setUpdatedByPost(postId);
						hep.setUpdatedDate(new Date());
						hep.setIsactive(0);
						serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep);
					}
				}
				if(lArrDeductList.length > 1 && lArrDeductList != null)
					HrPayLocMpgList = (List) deptcomoMpgDAOImpl.getDataIDisPresentForDed(lArrDeductList, hdncheckedvalueofdeduct, grpId);

				if (HrPayLocMpgList != null &&  HrPayLocMpgList.size() != 0 )
				{
					logger.info("===> HrPayLocMpgList :: " + HrPayLocMpgList.size());
					for (int i = 0; i < HrPayLocMpgList.size(); i++)
					{
						logger.info("===> in getDataIDisPresent save and update method :: " + i);
						HrPayLocComMpg hep = (HrPayLocComMpg) HrPayLocMpgList.get(i);
						logger.info("====> id in for :: " + hep.getId());
						hep.setUpdatedByPost(postId);
						hep.setUpdatedDate(new Date());
						hep.setIsactive(0);
						serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep);
					}
				}
				if(lArrLoanList.length > 1 && lArrLoanList != null)
					HrPayLocMpgList = (List) deptcomoMpgDAOImpl.getDataIDisPresentForLoan(lArrLoanList, hdncheckedSizeofLoan, grpId);

				if (HrPayLocMpgList != null &&  HrPayLocMpgList.size() != 0 )
				{
					logger.info("===> HrPayLocMpgList :: " + HrPayLocMpgList.size());
					for (int i = 0; i < HrPayLocMpgList.size(); i++)
					{
						logger.info("===> in getDataIDisPresent save and update method :: " + i);
						HrPayLocComMpg hep = (HrPayLocComMpg) HrPayLocMpgList.get(i);
						logger.info("====> id in for :: " + hep.getId());
						hep.setIsactive(0);
						hep.setUpdatedByPost(postId);
						hep.setUpdatedDate(new Date());
						serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep);
					}
				}
				//ended by Amish
				// added by khushal

				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());

				long userIdFromVogen = Long.valueOf(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
				OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);
				CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
				Set set = orgUserMstNew.getOrgUserpostRlts();
				OrgUserpostRlt tempObj = (OrgUserpostRlt) set.iterator().next();

				Map mapForChangedRecords = objectArgs;

				mapForChangedRecords.put("changedPostId", tempObj.getOrgPostMstByPostId().getPostId());
				//mapForChangedRecords.put("changedEmpId",empID);
				mapForChangedRecords.put("serviceLocator", serv);
				mapForChangedRecords.put("locId", locId);
				mapForChangedRecords.put("OrgPostMst", tempObj.getOrgPostMstByPostId());
				mapForChangedRecords.put("OrgUserMst", orgUserMstNew);
				mapForChangedRecords.put("cmnDatabaseMst", cmnDatabaseMst);
				GenerateBillServiceHelper generateBillServiceHelper = new GenerateBillServiceHelper();
				long changedRecordPK = generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is" + changedRecordPK);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is" + changedRecordPK);
				//ended by khushal

				objectArgs.put("msg", "Records Updated Successfully");

			}
			

			//added by japen
			EmpCompMpgDAOImpl empCompoDao = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class, serv.getSessionFactory());
			if(applyCmbValue != 2) //2 = for all employee
			{
				previousAllowList.removeAll(newMappingAllow);
				previousDeducList.removeAll(newMappingDeduc);
				logger.info("Size of previousAllowList--->" + previousAllowList.size());
				logger.info("Size of previousDeducList--->" + previousDeducList.size());
				
				if (previousAllowList.size() > 0)
				{
					StringBuffer allowCompoList = new StringBuffer();
					for (int i = 0; i < previousAllowList.size(); i++)
					{
						allowCompoList.append(previousAllowList.get(i));
						if (i != previousAllowList.size() - 1)
						{
							allowCompoList.append(",");
						}
					}
	
					List<HrEisEmpCompMpg> deactiveAllowList = empCompoDao.getSpecificEmpActiveList(allowCompoList.toString(), 2500134, locId);
					for (int i = 0; i < deactiveAllowList.size(); i++)
					{
						deactiveAllowList.get(i).setIsactive(0);
						deactiveAllowList.get(i).setUpdatedByPost(postId);
						deactiveAllowList.get(i).setUpdatedDate(new Date());
						empCompoDao.update(deactiveAllowList.get(i));
					}
					logger.info("allowCompoList--->" + allowCompoList.toString());
				}
				if (previousDeducList.size() > 0)
				{
					StringBuffer deducCompoList = new StringBuffer();
					for (int i = 0; i < previousDeducList.size(); i++)
					{
						deducCompoList.append(previousDeducList.get(i));
						if (i != previousDeducList.size() - 1)
						{
							deducCompoList.append(",");
						}
					}
	
					List<HrEisEmpCompMpg> deactiveDeducList = empCompoDao.getSpecificEmpActiveList(deducCompoList.toString(), 2500135, locId);
					for (int i = 0; i < deactiveDeducList.size(); i++)
					{
						deactiveDeducList.get(i).setIsactive(0);
						deactiveDeducList.get(i).setUpdatedByPost(postId);
						deactiveDeducList.get(i).setUpdatedDate(new Date());
						empCompoDao.update(deactiveDeducList.get(i));
						/*// added by khushal
									long userIdFromVogen=Long.valueOf(objectArgs.get("UserId").toString());
									OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
									OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);	    
									CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
									Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
									long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
									CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);					
									Set set=orgUserMstNew.getOrgUserpostRlts();
									OrgUserpostRlt tempObj = (OrgUserpostRlt)set.iterator().next();
	
									Map mapForChangedRecords=objectArgs;
	
									mapForChangedRecords.put("changedPostId",tempObj.getOrgPostMstByPostId().getPostId());
									//mapForChangedRecords.put("changedEmpId",empID);
									mapForChangedRecords.put("serviceLocator",serv);
									mapForChangedRecords.put("locId", locId);
									mapForChangedRecords.put("OrgPostMst",tempObj.getOrgPostMstByPostId());
									mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
									mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
									GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
									long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
									System.out.println("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
									logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
									//ended by khushal
						 */}
					logger.info("deducCompoList--->" + deducCompoList.toString());
				}
			}
			//ended by japen
			
			//Added by Kishan
			if(applyCmbValue == 2) //2 = for all employee
			{
				logger.info("Kishan shah method start");
				ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
				String[] allNonGovDeducTypes = resourceBundle.getString("NonGovDeducID").split(",");
				Map nonGovDeducTypeMap = new HashMap();
				for(int i=0;i<allNonGovDeducTypes.length;i++)
				{
					nonGovDeducTypeMap.put(allNonGovDeducTypes[i], allNonGovDeducTypes[i]);
				}
				List allEmployee = empCompoDao.getAllEmployeeByLocId(locId);
				Map duplicateDataAllow = null;
				Map duplicateDataDeduc = null;
				logger.info("allEmployee size = "+allEmployee.size());
				String hdnAllowList = objectArgs.get("hdnallowForAllEmp")!=null ?objectArgs.get("hdnallowForAllEmp").toString():null;
				String hdnDeductList = objectArgs.get("hdndeductForAllEmp")!=null ?objectArgs.get("hdndeductForAllEmp").toString():null;
				logger.info("hdnallowForAllEmp->"+hdnAllowList);
				logger.info("hdnDeductList->"+hdnDeductList);
				newMappingAllow = new ArrayList();
				newMappingDeduc = new ArrayList();
				if ( hdnAllowList != null && !hdnAllowList.equals(""))
				{
					String[] allow = hdnAllowList.split(",");
					for (int i = 0; i < allow.length; i++)
					{
						newMappingAllow.add(Long.parseLong(allow[i]));
					}
					if(hdnAllowList.lastIndexOf(",") == hdnAllowList.length()-1)
					{
						hdnAllowList = hdnAllowList.substring(0, hdnAllowList.lastIndexOf(","));
					}
					duplicateDataAllow = empCompoDao.getDuplicateDataFromCompoMpg(locId,AllownID,hdnAllowList);
				}
				if (hdnDeductList != null && !hdnDeductList.equals(""))
				{
					String[] deduc = hdnDeductList.split(",");
					for (int i = 0; i < deduc.length; i++)
					{
						if(!nonGovDeducTypeMap.containsKey(deduc[i]))
							newMappingDeduc.add(Long.parseLong(deduc[i]));
					}
					if(hdnDeductList.lastIndexOf(",") == hdnDeductList.length()-1)
					{
						hdnDeductList = hdnDeductList.substring(0, hdnDeductList.lastIndexOf(","));
					}
					duplicateDataDeduc = empCompoDao.getDuplicateDataFromCompoMpg(locId,DeductID,hdnDeductList);
				}
				
				logger.info("newMappingAllow size = "+newMappingAllow.size());
				logger.info("newMappingDeduc size = "+newMappingDeduc.size());
				
				IdGenerator idGen = new IdGenerator();
				HrEisEmpCompMpg hrEisEmpCompMpg = null;
				
				List<HrEisEmpCompMpg> updateAlwn  = new ArrayList<HrEisEmpCompMpg>();
				List<HrEisEmpCompMpg> updateDedc = new ArrayList<HrEisEmpCompMpg>();
				List<HrEisEmpCompMpg> saveAlwn  = new ArrayList<HrEisEmpCompMpg>();
				List<HrEisEmpCompMpg> saveDedc = new ArrayList<HrEisEmpCompMpg>();
				
				for(int emp=0; emp<allEmployee.size();emp++)
				{
					HrEisEmpCompGrpMst hrEisEmpCompGrpMst = (HrEisEmpCompGrpMst) allEmployee.get(emp);
					if ( hdnAllowList != null && !hdnAllowList.equals(""))
					{
						for (int i = 0; i < newMappingAllow.size(); i++)
						{
							String compareKey = ""+hrEisEmpCompGrpMst.getEmpCompGrpId()+newMappingAllow.get(i);
							if(duplicateDataAllow != null && duplicateDataAllow.containsKey(compareKey))
							{
								hrEisEmpCompMpg = new HrEisEmpCompMpg();
								hrEisEmpCompMpg = (HrEisEmpCompMpg) duplicateDataAllow.get(compareKey);
								hrEisEmpCompMpg.setIsactive(1);
								updateAlwn.add(hrEisEmpCompMpg);
								//serv.getSessionFactory().getCurrentSession().update(hrEisEmpCompMpg);
							}
							else
							{
								hrEisEmpCompMpg = new HrEisEmpCompMpg();
								Long id = idGen.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", objectArgs);
								hrEisEmpCompMpg.setEmpCompId(id);
								hrEisEmpCompMpg.setCmnLookupMst(cmnLookupMstAllowID);
								hrEisEmpCompMpg.setCompId(Long.parseLong(newMappingAllow.get(i).toString()));
								hrEisEmpCompMpg.setIsactive(1);
								hrEisEmpCompMpg.setHrEisEmpCompGrpMst(hrEisEmpCompGrpMst);
								hrEisEmpCompMpg.setStartDate(WEFDATE);
								hrEisEmpCompMpg.setRemarks("done");
								saveAlwn.add(hrEisEmpCompMpg);
								//serv.getSessionFactory().getCurrentSession().save(hrEisEmpCompMpg);
							}
						}
					}
					if (hdnDeductList != null && !hdnDeductList.equals(""))
					{
						for (int j = 0; j < newMappingDeduc.size(); j++)
						{
							String compareKey = ""+hrEisEmpCompGrpMst.getEmpCompGrpId()+newMappingDeduc.get(j);
							if(duplicateDataDeduc.containsKey(compareKey))
							{
								hrEisEmpCompMpg = new HrEisEmpCompMpg();
								hrEisEmpCompMpg = (HrEisEmpCompMpg) duplicateDataDeduc.get(compareKey);
								hrEisEmpCompMpg.setIsactive(1);
								updateDedc.add(hrEisEmpCompMpg);
								//serv.getSessionFactory().getCurrentSession().update(hrEisEmpCompMpg);
							}
							else
							{
								hrEisEmpCompMpg = new HrEisEmpCompMpg();
								Long id = idGen.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", objectArgs);
								hrEisEmpCompMpg.setEmpCompId(id);
								hrEisEmpCompMpg.setCmnLookupMst(cmnLookupMstDeductID);
								hrEisEmpCompMpg.setCompId(Long.parseLong(newMappingDeduc.get(j).toString()));
								hrEisEmpCompMpg.setIsactive(1);
								hrEisEmpCompMpg.setHrEisEmpCompGrpMst(hrEisEmpCompGrpMst);
								hrEisEmpCompMpg.setStartDate(WEFDATE);
								hrEisEmpCompMpg.setRemarks("done");
								saveDedc.add(hrEisEmpCompMpg);
								//serv.getSessionFactory().getCurrentSession().save(hrEisEmpCompMpg);
							}
						}
					}
				}
				for(HrEisEmpCompMpg  updtAlwn:updateAlwn)
				{
					serv.getSessionFactory().getCurrentSession().update(updtAlwn);
				}
				for(HrEisEmpCompMpg  saveAlw:saveAlwn)
				{
					serv.getSessionFactory().getCurrentSession().save(saveAlw);
				}
				for(HrEisEmpCompMpg  updtddct:updateDedc)
				{
					serv.getSessionFactory().getCurrentSession().update(updtddct);
				}
				for(HrEisEmpCompMpg  saveDdct:saveDedc)
				{
					serv.getSessionFactory().getCurrentSession().save(saveDdct);
				}
				logger.info("Kishan shah method end");
				objectArgs.put("msg", "Records Inserted Successfully");
			}
			//Ended by Kishan

			logger.info("===>completed... :: ");

			ResultObject reso = this.getDeptCompMPGDtlsService(objectArgs);

			Map passValue = (Map) reso.getResultValue();
			objectArgs.put("AllowSize", passValue.get("AllowSize"));
			objectArgs.put("DeductionSize", passValue.get("DeductionSize"));
			objectArgs.put("deducActionList", passValue.get("deducActionList"));
			objectArgs.put("allowList", passValue.get("allowList"));
			objectArgs.put("deptList", passValue.get("deptList"));
			objectArgs.put("result", "success");
			objectArgs.put("firstTime", "false");
			objectArgs.put("applyCmbValue", applyCmbValue);
			objectArgs.put("NonGovDeducSize", passValue.get("NonGovDeducSize"));
			objectArgs.put("NonGovDeducList", passValue.get("NonGovDeducList"));

			resultObject.setViewName("DeptCompMpg");
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);

			logger.info("INSETED SUCCESSFULLY");

		}
		catch (Exception e)
		{
			logger.error(e);
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}


	@SuppressWarnings("unchecked")
	public ResultObject getAllowDeductIDFromDept(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try
		{
			//Map voToService = (Map) objectArgs.get("voToServiceMap");
			long locCode = 0;
			if (StringUtility.getParameter("LocationCode", request) != null)
				locCode = Long.valueOf(StringUtility.getParameter("LocationCode", request).toString());
			logger.info("location code is ------------>>>>>***" + locCode);
			Log logger = LogFactory.getLog(getClass());

			/*	BillHeadMpgDAOImpl billHeadMpgDAO = new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class, serv.getSessionFactory());
			List<HrPayBillHeadMpg> lstHrPaybillHeadMpg = billHeadMpgDAO.getBillNofromDept(locCode);
			 */
			DeptCompMPGDAOImpl deptcomoMpgDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());
			List<HrPayLocComMpg> listHrPayDeptCompAllow = deptcomoMpgDAOImpl.getDataAllowChcked(locCode);
			List<HrPayLocComMpg> listHrPayDeptCompDeduct = deptcomoMpgDAOImpl.getDataDeductChcked(locCode);
			List<HrPayLocComMpg> listHrPayDeptCompLoan = deptcomoMpgDAOImpl.getDataLoanChcked(locCode);

			logger.info("=====> getAllowDeductIDFromDept listHrPayDeptCompAllow :: " + listHrPayDeptCompAllow.size());
			logger.info("=====> getAllowDeductIDFromDept listHrPayDeptCompDeduct :: " + listHrPayDeptCompDeduct.size());
			logger.info("=====> getAllowDeductIDFromDept listHrPayDeptCompLoan :: " + listHrPayDeptCompLoan.size());

			StringBuffer StrBufAllow = new StringBuffer();
			//StrBufAllow.append("<MainHead>");
			if (listHrPayDeptCompAllow != null)
			{
				StrBufAllow.append("<AllowHead>");
				for (int i = 0; i < listHrPayDeptCompAllow.size(); i++)
				{
					HrPayLocComMpg hrPayLocComMpg = listHrPayDeptCompAllow.get(i);

					logger.info(hrPayLocComMpg.getCompId());
					StrBufAllow.append("<AllowID>").append(hrPayLocComMpg.getCompId()).append("</AllowID>");

				}
				StrBufAllow.append("</AllowHead>");
			}
			if (listHrPayDeptCompDeduct != null)
			{
				StrBufAllow.append("<DeductHead>");
				for (int i = 0; i < listHrPayDeptCompDeduct.size(); i++)
				{
					HrPayLocComMpg hrPayLocComMpg = listHrPayDeptCompDeduct.get(i);

					logger.info(hrPayLocComMpg.getCompId());
					StrBufAllow.append("<DeductID>").append(hrPayLocComMpg.getCompId()).append("</DeductID>");

				}
				StrBufAllow.append("</DeductHead>");
			}
			if (listHrPayDeptCompLoan != null)
			{
				StrBufAllow.append("<LoanHead>");
				for (int i = 0; i < listHrPayDeptCompLoan.size(); i++)
				{
					HrPayLocComMpg hrPayLocComMpg = listHrPayDeptCompLoan.get(i);

					logger.info(hrPayLocComMpg.getCompId());
					StrBufAllow.append("<LoanID>").append(hrPayLocComMpg.getCompId()).append("</LoanID>");

				}
				StrBufAllow.append("</LoanHead>");
			}
			//StrBufAllow.append("</MainHead>");
			logger.info("====> " + StrBufAllow.toString());
			logger.info("====> StrBufAllow :: " + StrBufAllow.toString());
			String AllowDeductCheckedList = new AjaxXmlBuilder().addItem("ajax_key", StrBufAllow.toString()).toString();
			objectArgs.put("ajaxKey", AllowDeductCheckedList);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");

		}
		catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	//START added by poonam for  SanctionLeave
    public ResultObject SanctionLeave(Map objectArgs)
    {
            HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
            ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
            String talukaId=null;
            String ddoSelected=null;
            List officeList = null;  

            try{
                    logger.info("inside SanctionLeave");
                    ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
                    Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
                    DeptCompMPGDAOImpl deptcomoMpgDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());
                    long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
                    long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
                    logger.info("locId roshan  "+locId);
                    PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
                    List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
                    OrgDdoMst ddoMst  = null;
                    if(ddoList!=null && ddoList.size()>0) {
                            ddoMst = ddoList.get(0);
                    }

                    String ddoCode = null;
                    if(ddoMst!=null){
                            ddoCode = ddoMst.getDdoCode();
                    }

                    talukaId= StringUtility.getParameter("taluka", request);
                    logger.info("ddo code is found as "+ddoCode);
                    String districtID=deptcomoMpgDAOImpl.districtName(ddoCode);
                    logger.info("district id found is"+districtID);
                    List talukaList=deptcomoMpgDAOImpl.allTaluka(districtID);
                    logger.info("talukaList.size()"+talukaList.size());
                    Date date=new Date();
                    //hardcoded for 2015....need to change.
                    //int currYear=2016;
                    int currYear = Calendar.getInstance().get(Calendar.YEAR);
                    logger.info("Year is ***************"+currYear);
                    String locIds="";
                    String frmdts="";
                    String todts="";    
                    //      String locIds="";
                    String sanctionLeaveType=null;
                    logger.info(StringUtility.getParameter("frmdts", request));     
                    sanctionLeaveType=StringUtility.getParameter("hdnLeaveType", request);
                    //wefDate=StringUtility.getParameter("wEffectDate", request);
                    logger.info("sanctionLeaveType: "+sanctionLeaveType);
                    if((StringUtility.getParameter("flag", request)!=null)&&(StringUtility.getParameter("flag", request)!="")&&(Long.parseLong(StringUtility.getParameter("flag", request))==1)){
                            locIds= StringUtility.getParameter("locIds", request);
                            frmdts= StringUtility.getParameter("frmdts", request);  
                            todts=StringUtility.getParameter("todts", request);
                    

                            
                            String[] lstrIds = locIds.split("~");
                            String[] frmdtsArr = frmdts.split("~");
                            String[] toDtsArr=todts.split("~");


                            String[] lstrLocIDs = new String[lstrIds.length];
                            String[] lstrfrmDtsArr = new String[frmdtsArr.length];
                            String[] lstrtoDtsArr= new String[toDtsArr.length];

                            String frmDT=null;
                            String toDT=null;
                            for (Integer lInt = 0; lInt < lstrIds.length; lInt++)
                            {
                                    if (lstrIds[lInt] != null && !"".equals(lstrIds[lInt]))
                                    {
                                            lstrLocIDs[lInt] = lstrIds[lInt];
                                            lstrfrmDtsArr[lInt] = frmdtsArr[lInt];
                                            lstrtoDtsArr[lInt]=toDtsArr[lInt];

                                            logger.info("hii********** "+lstrLocIDs[lInt]);
                                            logger.info("hii********** "+lstrfrmDtsArr[lInt]);
                                            logger.info("hii********** "+lstrtoDtsArr[lInt]);
                                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                                            try {
                                                    if (lstrfrmDtsArr[lInt]!=null || lstrfrmDtsArr[lInt]!=""){
                                                            frmDT = sdf2.format(sdf1.parse(lstrfrmDtsArr[lInt]));
                                                    }
                                                    if (lstrtoDtsArr[lInt]!=null || lstrtoDtsArr[lInt]!=""){
                                                            toDT = sdf2.format(sdf1.parse(lstrtoDtsArr[lInt]));
                                                    }
                                            } catch (Exception e) {
                                                    e.printStackTrace();
                                            }
                                    }
                            }

                            for (Integer lInt = 0; lInt < lstrIds.length; lInt++)
                            {
                                    deptcomoMpgDAOImpl.updateLeaveDetails(lstrLocIDs[lInt],frmDT,toDT,currYear,sanctionLeaveType);

                            }
                    }


                    
                    if(sanctionLeaveType!=null && sanctionLeaveType!="")
                    {
                            officeList =  deptcomoMpgDAOImpl.getDDOLeavDtls(ddoCode,talukaId,ddoSelected,currYear,sanctionLeaveType);
                    }
                    objectArgs.put("officeList", officeList);
                    objectArgs.put("talukaList", talukaList);
                    objectArgs.put("talukaId", talukaId);
                    objectArgs.put("currYear", currYear);
                    objectArgs.put("sanctionLeaveType", sanctionLeaveType);
                    resultObject.setResultValue(objectArgs);
                    resultObject.setViewName("leaveSanctionView");
            }
            catch (Exception e)
            {

                    logger.info("Exception Ocuures...getDeptCompMPGDtls");
                    logger.error("Error is: "+ e.getMessage());
                    objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
                    resultObject.setResultValue(objectArgs);
                    resultObject.setViewName("DEMOJSP");
            }
            return resultObject;

    }

    //END added by poonam  SanctionLeave
	
	

}






