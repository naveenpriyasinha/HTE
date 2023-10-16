package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.ibm.db2.jcc.am.sf;
import com.tcs.sgv.common.dao.CmnBranchMstDao;
import com.tcs.sgv.common.dao.CmnBranchMstDaoImpl;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDao;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.RltBankBranchPay;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAO;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.eis.dao.BankDetailDAO;
import com.tcs.sgv.eis.dao.BankDetailDAOImpl;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.dao.BranchMasterDAO;
import com.tcs.sgv.eis.dao.BranchMasterDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.HrPayOfficePostMpgDAOImpl;
import com.tcs.sgv.eis.dao.NewEmployeeConfigDAO;
import com.tcs.sgv.eis.dao.NewEmployeeConfigDAOImpl;
import com.tcs.sgv.eis.dao.OrderHeadPostmpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderMstDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PsrPostMpgDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.util.NewEmployeeConfigServiceHelper;
import com.tcs.sgv.eis.valueobject.EmployeeCustomVO;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadCustomMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadPostMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.ess.dao.EmpDAO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgDesignationMstDao;
import com.tcs.sgv.ess.dao.OrgDesignationMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgGradeMstDao;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDao;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDao;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.dao.UserPostDao;
import com.tcs.sgv.ess.dao.UserPostDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDao;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDaoImpl;
import com.tcs.sgv.user.dao.UserPostDAO;
import com.tcs.sgv.user.valueobject.UserPostCustomVO;

public class NewEmployeeConfigService extends ServiceImpl{

	Log logger = LogFactory.getLog(getClass());
	int msg=0;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	ResourceBundle eisBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
	Date sysdate = new Date();

	public ResultObject fillNewEmpConfigData(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try{
			logger.info("************Inside fillNewEmpConfigData*****************");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			String dt = sdf.format(currDate);
			int finYrId = finYearDAOImpl.getFinYearId(dt);
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());	

			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			logger.info("going to check location Id:"+locId);
			CmnLocationMst cmnLocationMst= (CmnLocationMst) loginDetailsMap.get("locationVO");
			String locationCode = cmnLocationMst.getLocationCode();
			CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
			cmnLanguageMst.setLangId(langId);
			//List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
			//List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String edit = "";
			if(voToService.get("edit")!=null)
			{
				edit = (String)voToService.get("edit").toString();
				logger.info("edit "+edit);
			}

			List<CmnLookupMst> empTypeList = new ArrayList();
			List billNoList = new ArrayList();
			List arDesignationVO = new ArrayList();
			List classTypeList = new ArrayList();	        
			List bankList = new ArrayList();
			List bankBranchList = new ArrayList();
			List accountTypeList = new ArrayList();
			List branchList = new ArrayList();
			List postTypeList = new ArrayList();
			List officeList = new ArrayList();
			List prevPostList = new ArrayList();
			List empCustomVOList = new ArrayList();
			List<HrPayOrderHeadCustomMpg> sanctOrderList = new ArrayList();
			String empIdStr = (String)voToService.get("Employee_ID_UserPostSearch");
			NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
			EmployeeCustomVO empCustomVO = new EmployeeCustomVO();

			// Getting Class Type Details
			OrgGradeMstDao orgGradeMstDao = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
			classTypeList = orgGradeMstDao.getListByColumnAndValue("cmnLanguageMst", cmnLanguageMst);
			logger.info(" ClassTypelist.size::"+classTypeList.size());
			// Ends
			// Getting Designation List
			UserPostDAO userDAO =new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
			arDesignationVO = userDAO.getAllDesgMasterData(langId);	        
			logger.info(" arDesignationVOList.size::"+arDesignationVO.size());
			// Ends

			//Getting Emp Typr List
			empTypeList = (List<CmnLookupMst>) lookupDAO.getAllChildrenByLookUpNameAndLang("Emp Type", langId);
			logger.info(" empTypeList.size::"+empTypeList.size());
			// Getting Bank List
			BankMasterDAOImpl bankMaster = new BankMasterDAOImpl(MstBankPay.class,serv.getSessionFactory());
			BankDetailDAO bankDetailDAO = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
			bankList = bankMaster.getAllBankMasterData(langId);
			//
			// Getting Bank Branch List

			//
			// A/c Type List
			accountTypeList = lookupDAO.getAllChildrenByLookUpNameAndLang("Account Type", langId);
			logger.info(" accountTypeList.size::"+accountTypeList.size());
			//
			//Post Type
			postTypeList = lookupDAO.getAllChildrenByLookUpNameAndLang("PostType", 1L);
			//
			//Branch Of Loc.
			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			branchList = adminOrgPostDtlDao.getAllBranchList(1L);
			//
			// Office List
			officeList = adminOrgPostDtlDao.getAllOfficeList();
			//
			//Previous Post List
			prevPostList = adminOrgPostDtlDao.getAllPostList();
			//
			//Getting Bill No List
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());			
			locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
			boolean isBillDefined = payBillDAO.isBillsDefined(locationCode,langId);
			ArrayList billList = new ArrayList();  
			if(isBillDefined)
			{
				billNoList = newEmpConfigDAO.getBillNoData(finYrId,locId);
				logger.info("The size of billNoList is---->"+billNoList.size());

				for(Iterator itr=billNoList.iterator();itr.hasNext();)
				{
					HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
					Object[] row = (Object[])itr.next();
					String billNo = row[1].toString();
					String billHeadId = row[0].toString();
					billNoCustomVO.setBillId((billNo));
					billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
					billList.add(billNoCustomVO);
				}
			}			
			//
			//Sanction Order List			
			/*Date orderDate;
			String sanctionOrderDate;
			SimpleDateFormat sdfObj = new SimpleDateFormat("dd/MM/yyyy");
			OrderMstDAOImpl orderMstDAOImpl = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
			List orderList = orderMstDAOImpl.getOrderName();
			HrPayOrderMst hrPayOrderMst;
			StringBuffer propertyList = new StringBuffer();
			for (Iterator iter = orderList.iterator(); iter.hasNext();)  {
				HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
				hrPayOrderMst = (HrPayOrderMst)iter.next();

				orderDate = hrPayOrderMst.getOrderDate();
				sanctionOrderDate =sdfObj.format(orderDate);   		  	
				ohmpg.setOrderId(hrPayOrderMst.getOrderId());
				ohmpg.setOrderName(hrPayOrderMst.getOrderName() +  " " + "(" +" Date: "+ sanctionOrderDate + ")");
				sanctOrderList.add(ohmpg);
			}*/
			//
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.setTime(today);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			Date firstDay = new Date();
			firstDay = cal.getTime();
			logger.info("daytoday :: "+today);
			logger.info("firstDay :: "+firstDay);
			SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat par =new SimpleDateFormat("dd/MM/yyyy");
			Date day = 	par.parse(fmt.format(firstDay));		

			
			objectArgs.put("locationCode", locationCode);
			objectArgs.put("location_Id", locId);
			objectArgs.put("firstDay", fmt.format(firstDay));
			objectArgs.put("classTypeList", classTypeList);			
			objectArgs.put("empTypeList", empTypeList);
			objectArgs.put("bankList", bankList);			
			objectArgs.put("bankBranchList", bankBranchList);
			objectArgs.put("flag","insert");
			objectArgs.put("accountTypeList", accountTypeList);
			objectArgs.put("arDesignationVO", arDesignationVO);
			objectArgs.put("postTypeList", postTypeList);		
			objectArgs.put("branchList", branchList);					
			objectArgs.put("officeList", officeList);
			objectArgs.put("prevPostList", prevPostList);
			objectArgs.put("billNoList", billList);
			objectArgs.put("sanctOrderList", sanctOrderList);
			objectArgs.put("sAccType", eisBundle.getString("pay.sAccType"));
			objectArgs.put("selOfficeBranch", eisBundle.getString("pay.selOfficeBranch"));
			objectArgs.put("AISGradeCode", eisBundle.getString("AISGradeCode"));
			
			if(edit.equalsIgnoreCase("Y"))
			{

				logger.info(" Edit Flag is ::"+edit);
				String lPostname="",Psr="",Bill="",Dsgn="";
				if(voToService.get("Post")!=null)
					lPostname=(String)voToService.get("Post").toString();
				if(voToService.get("PsrNo")!=null)
					Psr=(String)voToService.get("PsrNo").toString();
				if(voToService.get("PsrNo")!=null)
					Bill=(String)voToService.get("PsrNo").toString();
				if(voToService.get("Dsgn")!=null)
					Dsgn=(String)voToService.get("Dsgn").toString();
				logger.info("Post :" + lPostname + " PsrNo : "  + Psr + "  BillNo : " + Bill +  "  Dsgn :  " + Dsgn);
				long userid = 0;
				long userPostRltId=0;
				if(voToService.get("userid")!=null)
					userid = Long.parseLong(voToService.get("userid").toString());
				if(voToService.get("userPostRltId")!=null)
					userPostRltId = Long.parseLong(voToService.get("userPostRltId").toString());


				empCustomVOList = new ArrayList();
				String PostName = "";
				String	dsgn = "";
				if(lPostname != null && !lPostname.equals(""))
					PostName = lPostname.trim();		
				if(Dsgn != null && !Dsgn.equals(""))
					dsgn = Dsgn.trim();
				sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
				//List userPostList=userPostDAO.getAllEmpDataFromUserId(langId,locationCode, PostName,Psr, Bill, dsgn);
				long proofTypeId =Long.parseLong(eisBundle.getString("proofTypeID"));
				BranchMasterDAO branchMasterDAO = new BranchMasterDAOImpl(RltBankBranchPay.class,serv.getSessionFactory());
				List empList = newEmpConfigDAO.getConfiguredEmpData(userPostRltId, langId, locId, proofTypeId);
				logger.info("The size of empList is-------------->"+empList.size());
				
				for(int i=0;i<empList.size();i++)
				{
					
					empCustomVO=new EmployeeCustomVO();
					Object[] rowList = (Object[]) empList.get(i);

					if(rowList[0] != null){
						long postId = Long.parseLong(rowList[0].toString().trim());
						empCustomVO.setPostId(postId);
					}
					if(rowList[1] != null){
						long userId = Long.parseLong(rowList[1].toString().trim());
						empCustomVO.setUserId(userId);
					}
					if(rowList[2] != null){
						long orgEmpId = Long.parseLong(rowList[2].toString().trim());
						empCustomVO.setOrgEmpId(orgEmpId);
					}
					if(rowList[3] != null){
						long hrisEmpId = Long.parseLong(rowList[3].toString().trim());
						empCustomVO.setHrEisEmpId(hrisEmpId);
					}
					if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
						String empSal = rowList[4].toString();
						empCustomVO.setEmpSal(empSal);
						logger.info("Prefix::"+empSal);
					}
					if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
						String empFname = rowList[5].toString();
						empCustomVO.setEmpFname(empFname);
						logger.info("empFname::"+empFname);
					}

					if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
						String empMname = rowList[6].toString();
						empCustomVO.setEmpMname(empMname);
						logger.info("empMname::"+empMname);
					}

					if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
						String empLname = rowList[7].toString();
						empCustomVO.setEmpLname(empLname);
						logger.info("empLname::"+empLname);
					}
					if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
						char gender = rowList[8].toString().charAt(0);
						empCustomVO.setGender(gender);
					}
					if(rowList[9] != null  && !(rowList[9].toString().trim()).equals("")){
						String orgGradeId = rowList[9].toString();
						empCustomVO.setUsrGradeId(orgGradeId);
					}
					if(rowList[10] != null && !(rowList[10].toString().trim()).equals("")){	
						String empDOB = rowList[10].toString();			            
						empCustomVO.setEmpDOB(sdf.format(sdfParse.parse(empDOB)));
					}
					if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
						String empDOJ = rowList[11].toString();			            
						empCustomVO.setEmpDOJ(sdf.format(sdfParse.parse(empDOJ)));
					}
					if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){	
						String empDOR = rowList[12].toString();			            
						empCustomVO.setEmpDOR(sdf.format(sdfParse.parse(empDOR)));
					}
					if(rowList[13] != null && !(rowList[13].toString().trim()).equals("")){	
						String gpfAccNo = rowList[13].toString();			            
						empCustomVO.setGpfAccNo(gpfAccNo);
					}
					if(rowList[14] != null ){
						long gpfGradeId = Long.parseLong(rowList[14].toString().trim());
						empCustomVO.setGpfGradeId(gpfGradeId);
					}
					if(rowList[15] != null && !(rowList[15].toString()).equals("")){	
						String panNo = rowList[15].toString();			            
						empCustomVO.setPanNo(panNo);
					}
					if(rowList[16] != null){
						long empTypeId = Long.parseLong(rowList[16].toString().trim());
						empCustomVO.setEmpType(empTypeId);
					}
					if(rowList[17] != null && !(rowList[17].toString().trim()).equals("")){	
						String email = rowList[17].toString();			            
						empCustomVO.setEmail(email);
					}
					if(rowList[18] != null){
						long bankId = Long.parseLong(rowList[18].toString().trim());
						empCustomVO.setBankId(bankId);
						objectArgs.put("bankBranchList",branchMasterDAO.getAllBranchs(bankId,langId));
					}
					if(rowList[19] != null){
						long bankBranchId = Long.parseLong(rowList[19].toString().trim());
						empCustomVO.setBankBranchId(bankBranchId);
						objectArgs.put("bankBranchId", bankBranchId);
					}
					if(rowList[20] != null){
						long bankAccType = Long.parseLong(rowList[20].toString().trim());
						empCustomVO.setBankAccType(bankAccType);
					}
					if(rowList[21] != null){
						String bankAccNo = rowList[21].toString().trim();
						empCustomVO.setBankAccNo(bankAccNo);
					}
					if(rowList[22] != null && !(rowList[22].toString().trim()).equals("")){	
						String bankStartDate = rowList[22].toString();			            
						empCustomVO.setBankAccStartDt(sdf.format(sdfParse.parse(bankStartDate)));
					}
					if(rowList[23] != null){
						long dsgnId = Long.parseLong(rowList[23].toString().trim());
						empCustomVO.setDsgnId(dsgnId);
					}
					if(rowList[24] != null){
						long psrNo = Long.parseLong(rowList[24].toString().trim());
						empCustomVO.setPsrNo(psrNo);
					}
					if(rowList[25] != null && !(rowList[25].toString().trim()).equals("")){	
						String postName = rowList[25].toString();			            
						empCustomVO.setPostName(postName);
					}
					if(rowList[26] != null && !(rowList[26].toString().trim()).equals("")){	
						String postShrtName = rowList[26].toString();			            
						empCustomVO.setPostShrtName(postShrtName);
					}
					if(rowList[27] != null && !(rowList[27].toString().trim()).equals("")){
						String postTypeId = rowList[27].toString();
						empCustomVO.setPostType(postTypeId);
					}
					if(rowList[28] != null){
						long postBranchId = Long.parseLong(rowList[28].toString().trim());
						empCustomVO.setPostBranchId(postBranchId);
					}
					if(rowList[29] != null){
						long officeId = Long.parseLong(rowList[29].toString().trim());
						empCustomVO.setOfficeId(officeId);
					}
					if(rowList[30] != null){
						long parentPostId = Long.parseLong(rowList[30].toString().trim());
						empCustomVO.setParentPostId(parentPostId);
					}
					if(rowList[31] != null && !(rowList[31].toString().trim()).equals("")){	
						String remarks = rowList[31].toString();			            
						empCustomVO.setRemarks(remarks);
					}
					if(rowList[32] != null && !(rowList[32].toString().trim()).equals("")){	
						String startDate = rowList[32].toString();			            
						empCustomVO.setPostStartDate(sdf.format(sdfParse.parse(startDate)));
					}
					if(rowList[33] != null && !(rowList[33].toString().trim()).equals("")){	
						String endDate = rowList[33].toString();			            
						empCustomVO.setPostEndDate(sdf.format(sdfParse.parse(endDate)));
					}
					if(rowList[34] != null){
						boolean activateFlag = Boolean.parseBoolean(rowList[34].toString());
						empCustomVO.setActivateFlag(activateFlag);
					}
					if(rowList[35] != null){
						long billSubHeadId = Long.parseLong(rowList[35].toString());
						empCustomVO.setBillSubheadId(billSubHeadId);
						//This code block is used for setting orderNamesList in updateMode 
						String orderHeadId = "";
						long ohId = 0;
						String orderName= "";
						String orderDate= "";
						ArrayList dtlsList = new ArrayList();
						fmt =new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat parseDate =new SimpleDateFormat("yyyy-MM-dd");
						Object[] objDtlsLst = null; 
						dtlsList = newEmpConfigDAO.getOrderNameList(billSubHeadId, locId);
						logger.info("dtlsList size in getOrderNameList service is:::::::::"+dtlsList.size());
						HrPayOrderMst hrPayOrderMst;
						sanctOrderList = new ArrayList();		
						Date sDate = new Date();
						if(dtlsList!=null && dtlsList.size()!=0)
						{
							for (int k=0;k<dtlsList.size();k++)
							{						
								HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
								objDtlsLst = (Object[]) dtlsList.get(k);
								orderHeadId = objDtlsLst[0]!= null ? objDtlsLst[0].toString() : "";
								ohId = Long.parseLong(orderHeadId);
								orderName = objDtlsLst[1]!= null ? objDtlsLst[1].toString() : "";												
								orderDate = objDtlsLst[2]!= null ? objDtlsLst[2].toString() : "";
								if(!orderDate.equals(""))
								{							
									sDate = parseDate.parse(orderDate);
									orderDate = fmt.format(sDate);
								}		
								orderName= orderName +  " " + "(" +"Date: "+ orderDate + ")";
								ohmpg.setOrderHeadId(ohId);
								ohmpg.setOrderName(orderName);						
								sanctOrderList.add(ohmpg);
							}							
						}
						objectArgs.put("sanctOrderList", sanctOrderList);
					}
					if(rowList[36] != null){
						long orderId = Long.parseLong(rowList[36].toString());
						empCustomVO.setSanctionOrderId(orderId);
					}	
					if(rowList[37] != null){
						long psrpostId = Long.parseLong(rowList[37].toString());
						empCustomVO.setPsrpostId(psrpostId);
					}
					if(rowList[38] != null){
						long lUsrPstId = Long.parseLong(rowList[38].toString());
						empCustomVO.setUsrPostId(lUsrPstId);
					}
					if(rowList[39] != null){
						long bankDtlsId = Long.parseLong(rowList[39].toString());
						empCustomVO.setBankDtlsId(bankDtlsId);
					}
					if(rowList[40] != null){
						long ohpId = Long.parseLong(rowList[40].toString());
						empCustomVO.setOhpId(ohpId);
					}
					if(rowList[41] != null){
						String lStrOrderName = rowList[41].toString();		
						objectArgs.put("lStrOrderName", lStrOrderName);
					}					
					if(rowList[42] != null){								
						Date sDate = sdfParse.parse(rowList[42].toString());
						String lStrOrderStartDate  = fmt.format(sDate);			
						objectArgs.put("lStrOrderStartDate", lStrOrderStartDate);
					}
					if(rowList[43] != null){
						Date eDate = sdfParse.parse(rowList[43].toString());
						String lStrOrderEndDate =fmt.format(eDate);				
						objectArgs.put("lStrOrderEndDate", lStrOrderEndDate);
					}
					if(rowList[44] != null){
						long orderMstId = Long.parseLong(rowList[44].toString());						
						objectArgs.put("orderMstId", orderMstId);
					}

					empCustomVOList.add(empCustomVO);

				}
				objectArgs.put("empCustomVOList", empCustomVOList);
				objectArgs.put("empCustomVO", empCustomVO);
				objectArgs.put("editFlag", "Y");    

			}
			else
			{
				objectArgs.put("editFlag", "N");    
			}
			logger.info("empCustomVO::"+empCustomVO);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("newEmployeeConfig");
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in fillNewEmpConfigData service******");
			logger.error("Error In fillNewEmpConfigData service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}
		logger.info("fillNewEmpConfigData ended***********************");
		return resultObject;
	}
	
	
	
	
	/*	public ResultObject insertNewEmployeeConfigDtls(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside insertNewEmployeeConfigDtls*****************");
			//TODO
			
			if (resultObject != null && objServiceArgs != null) 
			{	
				//Saving Basing Emp Info
				logger.info("In side saveEmpBasic Info*********** ");
				ServiceLocator serv = (ServiceLocator) objServiceArgs.get("serviceLocator");		
				FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
				Date currDate = Calendar.getInstance().getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
				String dt = sdf.format(currDate);
				int finYrId = finYearDAOImpl.getFinYearId(dt);
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				logger.info("langIdlangIdlangIdlangId" + langId);
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
				CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
				cmnLanguageMst.setLangId(langId);
				HrEisBankDtls bankDtls = (HrEisBankDtls)objServiceArgs.get("bankDtls");
				 Getting a DB ID
				long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				logger.info("userIduserIduserId" + userId);
				long dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());				
				CmnDatabaseMst cmnDatabaseMst= new CmnDatabaseMst();
				cmnDatabaseMst.setDbId(dbId);
				 End of DB Id code
				//Getting code for created By
				long loggedInUser = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				logger.info("loggedInUserloggedInUserloggedInUserloggedInUser" + loggedInUser);
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);
				String editFromVO = objServiceArgs.get("edit").toString();
				 Get The Person Post 
				 long loggedInpostId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
				OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);
				 End of the geting Person Post Code 
				long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				 Getting a Lang Id Code for English
				long langIdEng=1;
				CmnLanguageMst cmnLangMstEng = new CmnLanguageMst();
				cmnLangMstEng.setLangId(langIdEng);
				 End of Lang ID Code 

				//Getting a Lang Id Code for Gujarati
				long langIdGuj=2;
				CmnLanguageMst cmnLangMstGuj = new CmnLanguageMst();
				cmnLangMstGuj.setLangId(langIdGuj);

				String actionFlag ="" ;
				if(actionFlag!=null)
				{
					actionFlag=objServiceArgs.get("flag").toString();
				}
				long userID = Long.parseLong(objServiceArgs.get("userID").toString());
				//Getting a Grade Id code
				OrgGradeMst obGradeMst = new OrgGradeMst();
				long srFlag = 1;

				OrgEmpMst orgEmpMstEng = (OrgEmpMst)objServiceArgs.get("empInfoEng");				
				OrgEmpMst orgEmpMstGuj = (OrgEmpMst)objServiceArgs.get("empInfoGuj");
				
				NewEmployeeConfigServiceHelper helper = new NewEmployeeConfigServiceHelper(serv);

				orgEmpMstEng.setCmnLanguageMst(cmnLangMstEng);
				orgEmpMstGuj.setCmnLanguageMst(cmnLangMstGuj);

				OrgUserMst orgUserMst = orgEmpMstEng.getOrgUserMst(); 

				CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());					
				CmnLookupMst cmnLookupMst=cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("ACTIVE STATUS", 1);
				orgUserMst.setCmnLookupMst(cmnLookupMst);	
				
				// Creation of User - Starts
				logger.info("Saving User Info*********** ");
				if (orgUserMst != null && orgUserMst.getUserId() == 0 && actionFlag.equals("add"))
				{					
				//	orgUserMst = helper.insertOrgUserMstForUserCreation(loggedInUser, langId, locId, orgUserMst);
				}
				// Creation of User - Ends
				// THIS IS IMPORTANT PART
				if(orgUserMst != null)
				{
					orgEmpMstEng.setOrgUserMst(orgUserMst);
					orgEmpMstGuj.setOrgUserMst(orgUserMst);
				}
				// END
				logger.info("Saving Employee Info*********** ");
				IdGenerator idgen = new IdGenerator();
				long reqIdEng = idgen.PKGeneratorWebService("org_emp_mst", serv, loggedInUser, langId, locId);
				logger.info("reqIdEngreqIdEngreqIdEngreqIdEngreqIdEng" + reqIdEng);
				//orgEmpMstEng = helper.insertOrgEmpMst(orgEmpMstEng,reqIdEng, orgUserMst, cmnLangMstGuj, cmnLangMstEng, obGradeMst, orgEmpMstGuj, loggedInUser, langIdGuj, locId);
				 long empid = orgEmpMstEng.getEmpId();
				 logger.info("empidempidempidempidempid" + empid);
				//Saving Basic Emp Info Code ends
				
				
				
				
				// Saving Details of employee regarding GPF Details, PAN No etc.
				HrEisEmpMst empMst = (HrEisEmpMst) objServiceArgs.get("hrEisEmpMst");
				OrgEmpMst gpfNoOrgEmpMst = (OrgEmpMst)objServiceArgs.get("gpfNoOrgEmpMst");
				logger.info("gpfNoOrgEmpMstgpfNoOrgEmpMst ------- >>>>>>>>>"+gpfNoOrgEmpMst.getEmpGPFnumber());
				HrEisEmpMst hrEisEmpMst = null;
				Date sysdate = new Date();
				EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				EmpDAOImpl orgEmpDAOImpl = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());

				// reading grade Mst and setting it into org emp mst vo and hrGpfBalanceDtls vo
				OrgGradeMst orgGradeMst=null;
				long gradeId=0;
				if(!objServiceArgs.get("gradeId").toString().equals(""))
				{
					gradeId=Long.parseLong(objServiceArgs.get("gradeId").toString());
					logger.info(" the value of the gradeId is ::"+gradeId);
					OrgGradeMstDaoImpl orgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
					orgGradeMst =orgGradeMstDaoImpl.read(gradeId);
				}			
				// reading grade mst ends 

				
				
				
				//saving pan no dtls - proof dtls
				GenericDaoHibernateImpl generateDao = new GenericDaoHibernateImpl(HrEisProofDtl.class);
				generateDao.setSessionFactory(serv.getSessionFactory());
				HrEisProofDtl hrEisProofDtl = new HrEisProofDtl();
				// Added By Varun For GPF A/C Date:-31-07-2008
				GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(HrPayGpfBalanceDtls.class);
				genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
				HrPayGpfBalanceDtls hrGpfBalanceDtls = new HrPayGpfBalanceDtls();
				//userID = 0;
				// Ended By Varun For GPF A/C Date:-31-07-2008

				if (objServiceArgs.get("hrEisProofDtl") != null) 
					hrEisProofDtl = (HrEisProofDtl) objServiceArgs.get("hrEisProofDtl");
				IdGenerator IdGen = new IdGenerator();
				// End.
				// inserting proof dtls
				 long proofDtlId = idgen.PKGenerator("hr_eis_proof_dtl", objServiceArgs);
				 logger.info("proofDtlIdproofDtlId---->>>>>>>>>>>" + proofDtlId);
				 helper.insertHrEisProofDtls(dbId, proofDtlId,loggedInpostId, loggedInUser, orgUserMst, langIdGuj, locId, hrEisProofDtl, cmnLocationMst);
				//Setting and inserting proof dtls ends	
				
				//Setting and inserting hr_eis_emp_mst vo
				 logger.info("reqIdEngreqIdEngreqIdEngreqIdEngreqIdEng111111---->>>>>>>>>>" + reqIdEng);
				 logger.info("gpfNoOrgEmpMstgpfNoOrgEmpMst ------- >>>>>>>>>"+gpfNoOrgEmpMst.getEmpGPFnumber());
				 helper.insertHrEisEmpDtls(gpfNoOrgEmpMst,reqIdEng, loggedInOrgUserMst, loggedInOrgPostMst, orgEmpMstEng, empMst, cmnDatabaseMst, cmnLocationMst);

				logger.info("The Record is Inserted in to the Emp_mst");
				
				// Setting and inserting HrGpfBalanceDtls VO 
				helper.insertHrGpfBalanceDtls(gpfNoOrgEmpMst, orgUserMst, loggedInOrgUserMst, loggedInOrgPostMst, cmnDatabaseMst, cmnLocationMst, orgGradeMst);		
				// end of saving emp master and gpf dtls insertion / updation
			
				// Saving Bank Dtls
				logger.info("Saving bank Info*********** ");				
				
				BankDetailDAOImpl bankDetailDAO = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
				
				logger.info("editFromVO insertBankDtlsData" + editFromVO);
				long bankId=bankDtls.getHrEisBankMst().getBankId();
				long branchId = bankDtls.getHrEisBranchMst().getBranchId();
				long accountId=bankDtls.getBankAcctType();
				OrgUserMst orgUsrMst= new OrgUserMst();
				OrgEmpMst orgEmpMst = new OrgEmpMst();
				
			//	logger.info("the emp id is "+empId);				
				if(actionFlag.equalsIgnoreCase("add"))
				{	IdGenerator idGen = new IdGenerator();
					logger.info("INSIDE CREATE insertBankMasterDtls");
					long bankDtlsId = idGen.PKGenerator("hr_eis_bank_dtls", objServiceArgs);
					logger.info("bankDtlsIdbankDtlsId ------- >>>>>>>>>" + bankDtlsId);
					helper.insertHrEisBankDtls(bankDtls,bankDtlsId, loggedInUser, langIdGuj, locId, loggedInOrgUserMst, cmnDatabaseMst, hrEisEmpMst, loggedInOrgPostMst);
					
					objServiceArgs.put("msg", "Record Inserted Successfully");
				}
				BankMasterDAOImpl bankMaster = new BankMasterDAOImpl(HrEisBankMst.class,serv.getSessionFactory());
				List bankList = bankMaster.getAllBankMasterData(langId);
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				List accTypes = lookupDAO.getAllChildrenByLookUpNameAndLang("Account Type", langId);
				List empList = new ArrayList();
				objServiceArgs.put("bankList", bankList);
				objServiceArgs.put("accTypes", accTypes);

				// Saving Bank Dtls ends
				//--------------------------------------------------------------------
				// Saving info regarding New Post and its mapping with new User
				OrgPostDetailsRlt orgPostDetailsRlt_gu = (OrgPostDetailsRlt) objServiceArgs.get("orgPostDetailsRlt_gu");
				OrgPostDetailsRlt orgPostDetailsRlt_en = (OrgPostDetailsRlt) objServiceArgs.get("orgPostDetailsRlt_en");
				HrPayPsrPostMpg hrPayPsrPostMpg = new HrPayPsrPostMpg();
				PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class, serv.getSessionFactory());
				long psrId = 0L;
				long psrpostId = 0L;		
				hrPayPsrPostMpg = (HrPayPsrPostMpg) objServiceArgs.get("hrPayPsrPostMpg");
				psrId = hrPayPsrPostMpg.getPsrId();
				psrpostId = hrPayPsrPostMpg.getPsrPostId();
				logger.info((new StringBuilder("Psr Id is:-->>")).append(psrId).toString());
				logger.info((new StringBuilder("psrpostId is:-->>")).append(psrpostId).toString());
				OrgPostMst orgPostMst = (OrgPostMst) objServiceArgs.get("orgPostMst");
				OrgUserpostRlt orgUserpostRltVoGen= (OrgUserpostRlt)objServiceArgs.get("orgUserpostRlt");				
				long postIdForPsr = orgUserpostRltVoGen.getOrgPostMstByPostId().getPostId();
				logger.info("postIdForPsr::"+postIdForPsr);
				CmnBranchMst cmnBranchMst = null;
				CmnBranchMst objCmnBranchMst_en = new CmnBranchMst();
				CmnBranchMst objCmnBranchMst_gu = new CmnBranchMst();
				OrgDesignationMst orgDesignationMst = (OrgDesignationMst) objServiceArgs.get("orgDesignationMst");

				OrgPostMstDao orgPostMstDao = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
				OrgPostDetailsRltDao orgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
				OrgDesignationMstDao orgDesignationMstDao = new OrgDesignationMstDaoImpl(OrgDesignationMst.class, serv.getSessionFactory());
				CmnBranchMstDao cmnBranchMstDao = new CmnBranchMstDaoImpl(CmnBranchMst.class, serv.getSessionFactory());
				if (objServiceArgs.containsKey("cmnBranchMst"))
					cmnBranchMst = (CmnBranchMst) objServiceArgs.get("cmnBranchMst");
				if (cmnBranchMst != null) 
				{
					cmnLanguageMst = new CmnLanguageMst();
					cmnLanguageMst.setLangId(1L);
					String strCmnBranchMstColumns_en[] = { "cmnLanguageMst","branchCode" };
					Object cmnBranchMstValues_en[] = { cmnLanguageMst,cmnBranchMst.getBranchCode() };
					logger.info("abhitesting" + cmnBranchMst.getBranchCode());
					List arCmnBranchMst = cmnBranchMstDao.getListByColumnAndValue(strCmnBranchMstColumns_en, cmnBranchMstValues_en);
					if (arCmnBranchMst!=null && arCmnBranchMst.size() == 1)
						objCmnBranchMst_en = (CmnBranchMst) arCmnBranchMst.get(0);
					cmnLanguageMst.setLangId(2L);
					String strCmnBranchMstColumns_gu[] = { "cmnLanguageMst","branchCode" };
					Object cmnBranchMstValues_gu[] = { cmnLanguageMst,cmnBranchMst.getBranchCode() };
					arCmnBranchMst = cmnBranchMstDao.getListByColumnAndValue(strCmnBranchMstColumns_gu, cmnBranchMstValues_gu);
					if (arCmnBranchMst.size() == 1)
						objCmnBranchMst_gu = (CmnBranchMst) arCmnBranchMst.get(0);
				}
				OrgDesignationMst orgDesignationMst_en = orgDesignationMstDao.getDesignationVOByDsgnCodeAndLangId(orgDesignationMst.getDsgnCode(), Long.valueOf(1L));
				
				OrgDesignationMst orgDesignationMst_gu = orgDesignationMstDao.getDesignationVOByDsgnCodeAndLangId(orgDesignationMst.getDsgnCode(), Long.valueOf(2L));

				logger.info("Saving Post and user mapping Info*********** ");
				long selPostId = 0;
				if(objServiceArgs.get("selPostId")!=null )
					selPostId= Long.parseLong(objServiceArgs.get("selPostId").toString());
				logger.info("selPostId::"+selPostId);
				if(selPostId!=0)
					orgPostMst = (OrgPostMst) orgPostMstDao.read(Long.valueOf(selPostId));
				
				if (orgPostMst.getPostTypeLookupId() != null) 
				{
					logger.info("In the If condition");					
					CmnLookupMst postTypeLookupId = orgPostMst.getPostTypeLookupId();
					cmnLookupMst = cmnLookupMstDAO.getLookUpVOByLookUpIDAndLang(13L, 1L);
					logger.info((new StringBuilder("Lookup Desc.")).append(cmnLookupMst.getLookupDesc()).toString());
					postTypeLookupId = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(postTypeLookupId.getLookupName(), 1L);
					logger.info((new StringBuilder("PostTypeLookup Desc.")).append(postTypeLookupId.getLookupDesc()).toString());
					orgPostMst.setPostTypeLookupId(postTypeLookupId);
					orgPostMst.setCmnLookupMst(cmnLookupMst);
				}		
				long setPostId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
				OrgPostMst setOrgPostMst = (OrgPostMst) orgPostMstDao.read(Long.valueOf(setPostId));
				dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
				cmnDatabaseMst = (CmnDatabaseMst) cmnDatabaseMstDaoImpl.read(Long.valueOf(dbId));
				CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst_en = (CmnLanguageMst) cmnLanguageMstDao.read(Long.valueOf(1L));
				CmnLanguageMst cmnLanguageMst_gu = (CmnLanguageMst) cmnLanguageMstDao.read(Long.valueOf(2L));
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
				CmnLocationMst cmnLocationMst_en = cmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId(cmnLocationMst.getLocationCode(), Long.valueOf(1L));
				CmnLocationMst cmnLocationMst_gu = cmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId(cmnLocationMst.getLocationCode(), Long.valueOf(2L));
				OrgEmpMst orgEmpMst = new OrgEmpMst();
				orgEmpMst.setEmpId(Long.parseLong(loginDetailsMap.get("empId").toString()));
				// TODO This Block will update the old user post mapping. end date will be updated.
				// if changePostCheck is not null and changePostCheck value is Y
				
				long lOhpId = 0;
				if(objServiceArgs.get("lOhpId")!=null )
					lOhpId= Long.parseLong(objServiceArgs.get("lOhpId").toString());
				logger.info("lOhpId::"+lOhpId);
				String lStrOhpId = "0";
				if(objServiceArgs.get("ohpId")!=null)
					lStrOhpId = objServiceArgs.get("ohpId").toString();			
				logger.info("lStrOhpId::"+lStrOhpId);
				String selPostFlag="0";
				if(objServiceArgs.get("selPostFlag")!=null)
					selPostFlag = objServiceArgs.get("selPostFlag").toString();
				long newPostId=0;
				String changePostCheck="",prevPostEndDate="";
				changePostCheck = objServiceArgs.get("changePostCheck")!=null ?objServiceArgs.get("changePostCheck").toString():"";
				prevPostEndDate = objServiceArgs.get("prevPostEndDate")!=null ?objServiceArgs.get("prevPostEndDate").toString():"";
				logger.info("changePostCheck:::"+changePostCheck);
				logger.info("prevPostEndDate:::"+prevPostEndDate);
				logger.info("actionFlag:::"+actionFlag);
				
				if(actionFlag.equalsIgnoreCase("add") && !selPostFlag.equals("0"))
					actionFlag = "edit";			
				// TODO
				if ( actionFlag.equalsIgnoreCase("add")) 
				{
					    newPostId =idgen.PKGenerator("org_post_mst",objServiceArgs); 
					    logger.info("newPostIdnewPostId ------- >>>>>>>>>" + newPostId);
					 newPostId =helper.insertOrgPostMst(loggedInOrgPostMst, newPostId,loggedInUser, langIdGuj, locId, loggedInOrgUserMst, orgPostMst, setOrgPostMst, objCmnBranchMst_gu, orgDesignationMst_en, cmnLocationMst_en);
					logger.info(" New Post Created!!!!!!!");
					
					
					
					
					//Creating org Post Details entry
					IdGenerator IdGEn = new IdGenerator();
				    long orgPostDtlId_en = IdGEn.PKGenerator("org_post_details_rlt",objServiceArgs);
				    logger.info("orgPostDtlId_enorgPostDtlId_en ------- >>>>>>>>>" + orgPostDtlId_en);
					helper.insertOrgPostDetailsRlt(loggedInOrgPostMst, orgPostDtlId_en,setOrgPostMst, newPostId, loggedInOrgUserMst, orgPostDetailsRlt_en, objCmnBranchMst_en, cmnLanguageMst_en,  cmnLocationMst_en, orgDesignationMst_en, langIdGuj, userID, locId);
					logger.info(" New Post Details entry Created!!!!!!!");
					
					
					
					
					
					//Creating HrPayPsrPostMpg entry
					long psrPostId = IdGEn.PKGenerator("hr_pay_post_psr_mpg", objServiceArgs);
				    logger.info("psrPostIdpsrPostId ------- >>>>>>>>>" + psrPostId);
					helper.insertHrPayPsrPostMpg(newPostId,psrPostId, cmnLocationMst_en, psrId, hrPayPsrPostMpg, setOrgPostMst, userID, langIdGuj, locId);
					
					
					//added by ravysh for office post mapping 
					if(locId==Long.parseLong(resourceBundle.getString("cash2Admin")))
					{
						HrPayOfficepostMpg hrOfficePostMpg = (HrPayOfficepostMpg) objServiceArgs.get("hrOfficePostMpg");

						helper.insertHrPayOfficepostMpg(hrOfficePostMpg, setOrgPostMst, newPostId, cmnLocationMst_en, loggedInOrgUserMst,  userID, langIdGuj, locId);
						
						if(hrOfficePostMpg.getOrgPostMstParentPostId()!=null)
						{
							
							helper.updateHrPayOfficepostMpg(hrOfficePostMpg,  setOrgPostMst);
						
						}
					}
					//ended by ravysh
				} 
				else 
				{
					logger.info("Edit mode::::");
					logger.info((new StringBuilder("Upadte Post Id is : ")).append(orgPostMst.getPostId()).toString());
					logger.info((new StringBuilder("Upadte Org Post Dtl en_Id is : ")).append(orgPostDetailsRlt_en.getPostDetailId()).toString());
					OrgPostMst updateOrgPostMst = (OrgPostMst) orgPostMstDao.read(Long.valueOf(orgPostMst.getPostId()));
					if (updateOrgPostMst != null) 
					{
						OrgPostDetailsRlt updateOrgPostDetailsRlt_gu = orgPostDetailsRltDao.getPostDetailsRltByPostIdAndLangId(Long.valueOf(orgPostMst.getPostId()), Long.valueOf(2L));
						OrgPostDetailsRlt updateOrgPostDetailsRlt_en = orgPostDetailsRltDao.getPostDetailsRltByPostIdAndLangId(Long.valueOf(orgPostMst.getPostId()), Long.valueOf(1L));
						helper.updateOrgPostMst( updateOrgPostMst, loggedInOrgUserMst, setOrgPostMst, objCmnBranchMst_gu, cmnLocationMst_en, updateOrgPostMst, orgDesignationMst_en);
						
						if (updateOrgPostDetailsRlt_en != null) 
						{
							helper.updateOrgPostDetailsRlt(orgPostDetailsRlt_en, objCmnBranchMst_en, setOrgPostMst, updateOrgPostDetailsRlt_en, loggedInOrgUserMst, cmnLanguageMst_en, orgDesignationMst_en, cmnLocationMst_en);
						}
						if (updateOrgPostDetailsRlt_gu != null) 
						{
							updateOrgPostDetailsRlt_gu.setOrgDesignationMst(orgDesignationMst_gu);
							updateOrgPostDetailsRlt_gu.setCmnLocationMst(cmnLocationMst_gu);
							updateOrgPostDetailsRlt_gu.setCmnLanguageMst(cmnLanguageMst_gu);
							updateOrgPostDetailsRlt_gu.setUpdatedDate(sysdate);
							updateOrgPostDetailsRlt_gu.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
							updateOrgPostDetailsRlt_gu.setOrgPostMstByUpdatedByPost(setOrgPostMst);
							updateOrgPostDetailsRlt_gu.setCmnBranchMst(objCmnBranchMst_gu);
							updateOrgPostDetailsRlt_gu.setPostName(orgPostDetailsRlt_gu.getPostName());
							updateOrgPostDetailsRlt_gu.setPostShortName(orgPostDetailsRlt_gu.getPostShortName());
						}
						
						
						HrPayPsrPostMpg payPsrPostMpg = (HrPayPsrPostMpg) psrPostMpgDAOImpl.read(Long.valueOf(psrpostId));
						payPsrPostMpg.setPsrId(psrId);
						psrPostMpgDAOImpl.update(payPsrPostMpg);
				if (updateOrgPostDetailsRlt_en != null)
							orgPostDetailsRltDao.update(updateOrgPostDetailsRlt_en);

						//added by ravysh
						if(locId==Long.parseLong(resourceBundle.getString("cash2Admin")))
						{							
							HrPayOfficepostMpg hrOfficePostMpg = (HrPayOfficepostMpg) objServiceArgs.get("hrOfficePostMpg");
							HrPayOfficePostMpgDAOImpl officePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());
							List<HrPayOfficepostMpg> hrOfficePostMpgList = officePostMpgDAOImpl.getListByColumnAndValue("orgPostMstByPostId",orgPostMst);

							hrOfficePostMpg.setCreatedDate(sysdate);
							if(hrOfficePostMpgList.size()==0)
							{
								helper.insertHrPayOfficepostMpgForCash2AdminWhenListZero(postId, hrOfficePostMpg, setOrgPostMst, loggedInUser, langIdGuj, locId, cmnLocationMst_en, loggedInOrgUserMst);
								
							}
							else
							{
								helper.insertHrPayOfficepostMpgForCash2AdminWhenListNotZero(hrOfficePostMpgList, hrOfficePostMpg);
							}
							if(hrOfficePostMpg.getOrgPostMstParentPostId()!=null)
							{
								OrgPostMst parentOrgPostMst=orgPostMstDao.read(hrOfficePostMpg.getOrgPostMstParentPostId().getPostId());
								if(parentOrgPostMst.getEndDate()==null)
								{
									helper.checkParentPostIdIsNullOrNot(parentOrgPostMst, hrOfficePostMpg);
								}

							}
						}	
					}
					msg = 1;
				}
				// Saving info regarding New Post and its mapping with new User ends

				// Saving User Post Mapping Details Starts		
				logger.info("User and post mapping starts -post which if it is selected from selection combo not new");
				CmnLookupMstDAOImpl cmnLookupMstDao  = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				cmnLookupMst = cmnLookupMstDao.getLookUpVOByLookUpNameAndLang("Primary_Post",langId);

				String strDesgCode=objServiceArgs.get("selDesg").toString();
				String strDepartmentCode=objServiceArgs.get("strOfficeType").toString();
				String strOfficeName=objServiceArgs.get("strOfficeName").toString();
				long lUserPostRltId=Long.parseLong(objServiceArgs.get("lUserPostRltId").toString());
				logger.info("strDesgCode========="+strDesgCode);
				logger.info("strDepartmentCode========="+strDepartmentCode);
				logger.info("strOfficeName========="+strOfficeName);
				logger.info("lUserPostRltId========="+lUserPostRltId);
				
				UserPostDao userPostDao  = new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());			
				OrgUserpostRlt orgUserpostRlt=new OrgUserpostRlt();
				
				if(actionFlag.equalsIgnoreCase("edit") && !selPostFlag.equals("0"))
					actionFlag = "add";	
				
				if(actionFlag.equals("add") )
				{
					logger.info("Inside lUserPostRltId ==0 Block================= ");
					//long orgUserpostRltPk = 0;
					//IdGenerator idGen = new IdGenerator();
					long orgUserpostRltPk =IDGenerateDelegate.getNextId("org_userpost_rlt", objServiceArgs);
					logger.info("orgUserpostRltPkorgUserpostRltPkorgUserpostRltPk" + orgUserpostRltPk);
					logger.info("Inserting known orgUserpostRltPk PK================= " + orgUserpostRltPk);
					logger.info("newPostId:::"+newPostId);
					logger.info("UserIdUserId------------->>>>>>>>>>>>>" + orgUserMst.getUserId());
					
					if(orgUserpostRltPk!=0)
					{
						helper.insertOrgUserpostRlt(newPostId, orgUserpostRltPk, loggedInOrgPostMst, newPostId, loggedInOrgUserMst, orgUserpostRltVoGen, orgUserMst, loggedInUser, langIdGuj, locId);
						
					}
				}
				else
				{
					logger.info("Inside lUserPostRltId !=0 Block================= "+lUserPostRltId);
					logger.info("Activate Flag is :--->>>"+orgUserpostRltVoGen.getActivateFlag());					
					helper.updateOrgUserpostRlt(loggedInOrgPostMst, langIdGuj, loggedInOrgUserMst, lUserPostRltId, orgUserMst, orgUserpostRltVoGen);
					logger.info("Inside Updation Block================= ");
				}
				
				logger.info("User and post mapping ends -post which if it is selected from selection combo not new ");
				
				//Saving User Post Mapping Details ends	
				
				
				
				// Saving info related to Order head post mapping
				logger.info("Saving order head post mapping Info*********** ");
				//long headId = 0;
				long orderheadId=0;
				long headPostId = 0;
				OrderHeadPostmpgDAOImpl ohpMpgDAO = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());//object of DAOIMPL
				long orderId=0;
				if(objServiceArgs.get("order")!=null)
					orderId=Long.parseLong(objServiceArgs.get("order").toString());
				logger.info("orderIdorderIdorderIdorderIdorderId" + orderId);
				if(objServiceArgs.get("order")!=null)
					orderheadId=Long.parseLong(objServiceArgs.get("order").toString());
				logger.info("orderheadIdorderheadIdorderheadIdorderheadId" + orderheadId);
				long billNo =Long.parseLong(objServiceArgs.get("billNo").toString());
				
				//getting  head Id from  order

				List <HrPayOrderHeadPostMpg> headList = (List)ohpMpgDAO.getheadsfromorders(orderId,finYrId);
				StringBuffer propertyList = new StringBuffer();
				
				logger.info("changePostCheck 2:::"+changePostCheck);
				logger.info("prevPostEndDate 2:::"+prevPostEndDate);
				logger.info("actionFlag 2:::"+actionFlag);
				logger.info("orderheadId:::"+orderheadId);
				
				if(actionFlag.equalsIgnoreCase("add") && !selPostFlag.equals("0"))
					actionFlag = "edit";	
				
				// Insert order Details
//TODO
				if (actionFlag.equalsIgnoreCase("add")  && orderheadId==0) 
				{
						logger.info("Inserting new order which does not exist in system...");
						HrPayOrderMst hrPayOrderMstVo = (HrPayOrderMst)objServiceArgs.get("hrPayOrderMstVo");
						long lLongBillHeadId=Long.parseLong(objServiceArgs.get("lStrBillHeadId").toString());
						
						
						//Inserting into hr_pay_order_mst
						long newOrderMstId = helper.insertHrPayOrderMst(hrPayOrderMstVo, cmnLangMstEng, userId, langIdGuj, locId);
						//End
						
						
						// inserting mapping of order head
						 orderheadId =  helper.insertHrPayOrderHeadMpg(loggedInOrgPostMst, orderheadId, lLongBillHeadId, loggedInOrgUserMst, newOrderMstId, loggedInUser, langIdGuj, locId);
						
				}
						// Insert order Details ends
				
				
				IdGenerator	idGen = new IdGenerator();
				
				if (actionFlag.equalsIgnoreCase("add") || lOhpId==0) 
				{
					long orderHeadPostId =IDGenerateDelegate.getNextId("hr_pay_order_head_post_mpg",objServiceArgs);
					logger.info("orderHeadPostIdorderHeadPostIdorderHeadPostIdorderHeadPostId" + orderHeadPostId);
					HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg=helper.insertHrPayOrderHeadPostMpg(newPostId,orderHeadPostId, loggedInOrgUserMst, loggedInOrgPostMst,loggedInUser, orderheadId, langIdGuj, locId);
				}  	        
				else
				{
					logger.info("lOhpId in service update mode::"+lOhpId);
				//	IdGenerator IDGEN = new IdGenerator();
					
					helper.updateHrPayOrderHeadPostMpg(lStrOhpId, orderheadId, userId, langIdGuj, locId, loggedInOrgUserMst, loggedInOrgPostMst, lOhpId, orgUserpostRltVoGen);
					
				}
				logger.info("Saving psr post mapping Info*********** ");
				HrPayPsrPostMpg psrPostMpg = new HrPayPsrPostMpg();
				//added by manoj for psr relatd issue for home
				psrId=1;
				long psrPostId=0;
				List psrList = psrPostMpgDAOImpl.getMaxPsrForLoc(locId);
				logger.info("psrList be added is "+psrList);
				if(psrList!=null && psrList.size()>0)
				{
					Object[] row = (Object[])psrList.get(0); 
					logger.info("row added is "+row.length);
					if(row[0]!=null)
						psrId = Long.parseLong(row[0].toString());  	        
				}
				logger.info("psr no to be added is "+psrId);
				String[] cols = new String[]{"postId","loc_id"};
				Object[] vals = null;
				logger.info("newPostId::"+newPostId);
				logger.info("postIdForPsr::"+postIdForPsr);
				if(newPostId!=0)
					vals = new Object[]{newPostId,locId};				
				else
					vals = new Object[]{postIdForPsr,locId};				
				List hrPayPsrPostMpgList = null;
				hrPayPsrPostMpgList = psrPostMpgDAOImpl.getListByColumnAndValue(cols, vals);
				if(hrPayPsrPostMpgList!=null && hrPayPsrPostMpgList.size()>0)
				{
					helper.HrPayPsrPostMpgListNotZero(hrPayPsrPostMpg, hrPayPsrPostMpgList, billNo);
				}
				else
				{
					
					helper.HrPayPsrPostMpgLisIsZero(psrPostId, hrPayPsrPostMpg, psrId, billNo, locId);
					psrId++;
				}
				logger.info("changePostCheck 3:::"+changePostCheck);
				logger.info("prevPostEndDate 3:::"+prevPostEndDate);
				logger.info("actionFlag 3:::"+actionFlag);				
				// Saving info related to Order head post mapping ends
				
				//To set viewEmployee List page after insertion or updation
				//added by manish khunt
	        	
    			Map mapForChangedRecords=objServiceArgs;
    			logger.info("Setting map to objectArgs");
    			mapForChangedRecords.put("changedPostId",orgPostMst.getPostId());
    			mapForChangedRecords.put("locId",locId);
    			mapForChangedRecords.put("changedEmpId",objServiceArgs.get("empId"));
    			logger.info("empId:::::::::::::::"+objServiceArgs.get("empId"));
    			mapForChangedRecords.put("serviceLocator",serv);
    			mapForChangedRecords.put("OrgPostMst",orgPostMst);
    			mapForChangedRecords.put("OrgUserMst",orgUserMst);
    			mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
    			GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
    			long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
    			logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
    			logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
    		//ended by manish khunt
				
				
				
				
				
				
				objServiceArgs.put("MESSAGECODE",300005);				
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("newEmployeeConfig");
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in insertNewEmployeeConfigDtls service******");
			logger.error("Error In insertNewEmployeeConfigDtls service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}

		logger.info("insertNewEmployeeConfigDtls ended***********************");
		return resultObject;
	}*/
	
	
	
	
	
	public ResultObject getSelectedPostDtls(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside getSelectedPostDtls*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				Map voToService = (Map)objServiceArgs.get("voToServiceMap");
				String editFlag = (String)voToService.get("edit");				
				long selectedPostId = 0;
				if(voToService.get("selectedPostId")!=null)
					selectedPostId=Long.parseLong(voToService.get("selectedPostId").toString());
				String psrNo = "";
				String postName= "";
				String postShortName= "";
				String postTypeId= "";
				String branchId= "";
				String officeId= "";
				String remarks= "";
				String startDate= "";
				String endDate= "";
				String activateFlag= "";
				String ohpId= "";
				String orderId= "";
				String billNo= "";
				String psrPostId="";
				StringBuffer propertyList = new StringBuffer();
				ArrayList dtlsList = new ArrayList();
				SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat parseDate =new SimpleDateFormat("yyyy-MM-dd");
				Object[] postDtlsLst = null; 
				dtlsList = newEmpConfigDAO.getSelectedPosts(selectedPostId,langId,locId);
				if(dtlsList!=null && dtlsList.size()!=0)
				{
					if(dtlsList.get(0)!=null)
					{
						postDtlsLst = (Object[]) dtlsList.get(0);
						psrNo = postDtlsLst[0]!= null ? postDtlsLst[0].toString() : "";
						postName = postDtlsLst[1]!= null ? postDtlsLst[1].toString() : "";
						postShortName = postDtlsLst[2]!= null ? postDtlsLst[2].toString() : "";
						postTypeId = postDtlsLst[3]!= null ? postDtlsLst[3].toString() : "";
						branchId = postDtlsLst[4]!= null ? postDtlsLst[4].toString() : "";
						officeId = postDtlsLst[5]!= null ? postDtlsLst[5].toString() : "";
						remarks = postDtlsLst[6]!= null ? postDtlsLst[6].toString() : "";
						startDate = postDtlsLst[7]!= null ? postDtlsLst[7].toString() : "";
						if(!startDate.equals(""))
						{
							Date sDate = new Date();
							sDate = parseDate.parse(startDate);
							startDate = fmt.format(sDate);
						}
						endDate = postDtlsLst[8]!= null ? postDtlsLst[8].toString() : "";
						if(!endDate.equals(""))
						{
							Date eDate = new Date();
							eDate = parseDate.parse(endDate);
							endDate = fmt.format(eDate);
						}
						activateFlag = postDtlsLst[9]!= null ? postDtlsLst[9].toString() : "";
						ohpId = postDtlsLst[10]!= null ? postDtlsLst[10].toString() : "0";
						orderId = postDtlsLst[11]!= null ? postDtlsLst[11].toString() : "0";
						billNo = postDtlsLst[12]!= null ? postDtlsLst[12].toString() : "0";
						psrPostId= postDtlsLst[13]!= null ? postDtlsLst[13].toString() : "0";
					}
				}
				String postTypeName="";
				if(postTypeId!=null && !postTypeId.equals(""))
				{
					lupMst = lookupDAO.getLookUpVOByLookUpIDAndLang(Long.parseLong(postTypeId), langId);
					postTypeName = lupMst.getLookupName();
				}
				propertyList.append("<postDtls>");
				propertyList.append("<psrNo>").append("<![CDATA[").append(psrNo).append("]]>").append("</psrNo>");
				propertyList.append("<postName>").append("<![CDATA[").append(postName).append("]]>").append("</postName>");
				propertyList.append("<postShortName>").append("<![CDATA[").append(postShortName).append("]]>").append("</postShortName>");
				propertyList.append("<postTypeId>").append("<![CDATA[").append(postTypeName).append("]]>").append("</postTypeId>");
				propertyList.append("<branchId>").append("<![CDATA[").append(branchId).append("]]>").append("</branchId>");
				propertyList.append("<officeId>").append("<![CDATA[").append(officeId).append("]]>").append("</officeId>");
				propertyList.append("<remarks>").append("<![CDATA[").append(remarks).append("]]>").append("</remarks>");
				propertyList.append("<startDate>").append("<![CDATA[").append(startDate).append("]]>").append("</startDate>");
				propertyList.append("<endDate>").append("<![CDATA[").append(endDate).append("]]>").append("</endDate>");
				propertyList.append("<activateFlag>").append("<![CDATA[").append(activateFlag).append("]]>").append("</activateFlag>");
				propertyList.append("<ohpId>").append("<![CDATA[").append(ohpId).append("]]>").append("</ohpId>");
				propertyList.append("<orderId>").append("<![CDATA[").append(orderId).append("]]>").append("</orderId>");
				propertyList.append("<billNo>").append("<![CDATA[").append(billNo).append("]]>").append("</billNo>");
				propertyList.append("<psrPostId>").append("<![CDATA[").append(psrPostId).append("]]>").append("</psrPostId>");
				propertyList.append("</postDtls>");

				String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
				objServiceArgs.put("ajaxKey", stateNameIdStr);

				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("ajaxData");        
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getSelectedPostDtls service******");
			logger.error("Error In getSelectedPostDtls service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}

		logger.info("getSelectedPostDtls ended***********************");
		return resultObject;
	}
	public ResultObject getViewEmployeeList(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try{
			logger.info("************Inside getViewEmployeeList*****************");
			if (resultObject != null && objServiceArgs != null) 
			{
				logger.info("Coming into the Service method getViewEmployeeList");		
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				CmnLocationMst cmnLocationMst= (CmnLocationMst) loginDetailsMap.get("locationVO");
				String locationCode = cmnLocationMst.getLocationCode();
				long locId = cmnLocationMst.getLocId();
				List userpostList = new ArrayList();
				UserPostDAO userPostDAO =new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
				Map voToService = (Map)objServiceArgs.get("voToServiceMap");
				
				String employeeName="";
				if((voToService.get("Employee_srchNameText_EmployeeSearch")!=null&&!voToService.get("Employee_srchNameText_EmployeeSearch").equals("")))
					employeeName=(String)voToService.get("Employee_srchNameText_EmployeeSearch").toString();
				String edit = "";
				if(voToService.get("edit")!=null)
				{
					edit = (String)voToService.get("edit").toString();

				}
				logger.info("edit "+edit);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
				String lPostname="",Psr="",Bill="",Dsgn="";
				if(voToService.get("Post")!=null)
					lPostname=(String)voToService.get("Post").toString();
				if(voToService.get("PsrNo")!=null)
					Psr=(String)voToService.get("PsrNo").toString();
				if(voToService.get("BillNo")!=null)
					Bill=(String)voToService.get("BillNo").toString();
				if(voToService.get("Dsgn")!=null)
					Dsgn=(String)voToService.get("Dsgn").toString();
				logger.info("Post :" + lPostname + " PsrNo : "  + Psr + "  BillNo : " + Bill +  "  Dsgn :  " + Dsgn);

				List postList = new ArrayList();
				List postList2 = new ArrayList();

				String empIdStr = (String)voToService.get("Employee_ID_EmployeeSearch");
				logger.info("the emp id from search employee "+empIdStr);

				long empId=0;
				if(empIdStr!=null && !"".equals(empIdStr))
				{
					empId = Long.parseLong(empIdStr);
				}
				String empFullName = "";
				String PsrNo = " X ";
				String BillNo = " X ";
				UserPostCustomVO userpostcustomVO = new UserPostCustomVO();
				postList = new ArrayList();
				String PostName = "";
				String	dsgn = "";
				if(lPostname != null && !lPostname.equals(""))
					PostName = lPostname.trim();		
				if(Dsgn != null && !Dsgn.equals(""))
					dsgn = Dsgn.trim();
				String searchFlg="";
				if(voToService.get("searchFlg")!=null)
					searchFlg=(String)voToService.get("searchFlg").toString();
				List userPostList = new ArrayList();
				if(!"".equals(searchFlg) && "Y".equals(searchFlg))
					userPostList=userPostDAO.getAllEmpDataFromUserId(langId,locationCode, PostName,Psr, Bill, dsgn,empId);
				else
					userPostList=userPostDAO.getConfigEmpData(langId,locId, PostName,Psr, Bill, dsgn,empId);

				logger.info("The size of userPostList is-------------->"+userPostList.size());
				for(int i=0;i<userPostList.size();i++)
				{
					userpostcustomVO=new UserPostCustomVO();
					Object[] rowList = (Object[]) userPostList.get(i);

					if(rowList[0] != null){
						empId = Long.parseLong(rowList[0].toString());
						userpostcustomVO.setEmpId(empId);
					}
					if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
						empFullName = rowList[1].toString();
						userpostcustomVO.setEmpFullName(empFullName);
					}
					if(rowList[2] != null){ 	
						long userPostId =Long.parseLong(rowList[2].toString()) ;
						userpostcustomVO.setUserPostId(userPostId);
					}

					if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
						String postName = rowList[3].toString();
						userpostcustomVO.setPostname(postName);
					}

					if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
						String dsgnName = rowList[6].toString();
						userpostcustomVO.setDsgnname(dsgnName);
					}
					if(rowList[7] != null){
						long userId =Long.parseLong(rowList[7].toString()) ;
						userpostcustomVO.setUserId(userId);
					}
					if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){
						PsrNo =rowList[8].toString() ;
						userpostcustomVO.setPsrNo(PsrNo);
					}
					if(rowList[9] != null && !(rowList[8].toString().trim()).equals("")){
						BillNo =rowList[9].toString() ;
						userpostcustomVO.setBillNo(BillNo);
					}
					if(rowList[10] != null && !(rowList[10].toString().trim()).equals("")){	
						String StartDate = rowList[10].toString();
						userpostcustomVO.setStartDate(sdf.format(sdfParse.parse(StartDate)));
					}
					if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
						String EndDate = rowList[11].toString();
						userpostcustomVO.setEndDate(sdf.format(sdfParse.parse(EndDate)));
					}

					postList.add(userpostcustomVO);
				}
				objServiceArgs.put("empName",employeeName);
				objServiceArgs.put("userPostList", postList);
				objServiceArgs.put("postListSize", postList.size());
				
			}
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objServiceArgs);
			resultObject.setViewName("viewEmployeeList");        
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getViewEmployeeList service******");
			logger.error("Error In getViewEmployeeList service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}
		logger.info("getViewEmployeeList ended***********************");
		return resultObject;
	}
	
	public ResultObject getOrderNamesList(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside getOrderNamesList*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				Map voToService = (Map)objServiceArgs.get("voToServiceMap");
				String editFlag = (String)voToService.get("edit");				

				String orderHeadId = "";
				long ohId = 0;
				String orderName= "";
				String orderDate= "";				
				StringBuffer propertyList = new StringBuffer();
				ArrayList dtlsList = new ArrayList();
				SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat parseDate =new SimpleDateFormat("yyyy-MM-dd");
				Object[] objDtlsLst = null; 
				String billSubHeadId = "";
				long lBillSubHeadId = 0;
				if(voToService.get("billSubHeadId")!=null)
					lBillSubHeadId=Long.parseLong(voToService.get("billSubHeadId").toString());
				dtlsList = newEmpConfigDAO.getOrderNameList(lBillSubHeadId, locId);
				logger.info("dtlsList size in getOrderNameList service is:::::::::"+dtlsList.size());
				HrPayOrderMst hrPayOrderMst;
				List<HrPayOrderHeadCustomMpg> sanctOrderList = new ArrayList();		
				Date sDate = new Date();
				if(dtlsList!=null && dtlsList.size()!=0)
				{
					for (int i=0;i<dtlsList.size();i++)
					{						
						HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
						objDtlsLst = (Object[]) dtlsList.get(i);
						orderHeadId = objDtlsLst[0]!= null ? objDtlsLst[0].toString() : "";
						ohId = Long.parseLong(orderHeadId);
						orderName = objDtlsLst[1]!= null ? objDtlsLst[1].toString() : "";												
						orderDate = objDtlsLst[2]!= null ? objDtlsLst[2].toString() : "";
						if(!orderDate.equals(""))
						{							
							sDate = parseDate.parse(orderDate);
							orderDate = fmt.format(sDate);
						}		
						orderName= orderName +  " " + "(" +" Date: "+ orderDate + ")";
						ohmpg.setOrderHeadId(ohId);
						ohmpg.setOrderName(orderName);
						propertyList.append("<orderNames>");   	
						propertyList.append("<orderHeadId>").append("<![CDATA[").append(orderHeadId).append("]]>").append("</orderHeadId>");
						propertyList.append("<orderName>").append("<![CDATA[").append(orderName).append("]]>").append("</orderName>"); 
						propertyList.append("</orderNames>");
						//sanctOrderList.add(ohmpg);
					}
				}
				logger.info("propertyList::"+propertyList);
				
				String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
				objServiceArgs.put("ajaxKey", stateNameIdStr);

				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("ajaxData");        
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getOrderNamesList service******");
			logger.error("Error In getOrderNamesList service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}
		logger.info("getOrderNamesList ended***********************");
		return resultObject;
	}

	
	public ResultObject updateNewEmployeeConfigDtls(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside updateNewEmployeeConfigDtls*****************");
			//TODO
			
			if (resultObject != null && objServiceArgs != null) 
			{	
				
				//Saving Basing Emp Info
				logger.info("In side updateNewEmployeeConfigDtls Info*********** ");
				ServiceLocator serv = (ServiceLocator) objServiceArgs.get("serviceLocator");		
				FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
				Date currDate = Calendar.getInstance().getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
				String dt = sdf.format(currDate);
				int finYrId = finYearDAOImpl.getFinYearId(dt);
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
				CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
				cmnLanguageMst.setLangId(langId);
				

				/* Getting a DB ID*/								 
				long dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());				
				CmnDatabaseMst cmnDatabaseMst= new CmnDatabaseMst();
				cmnDatabaseMst.setDbId(dbId);
				/* End of DB Id code*/
				//Getting code for created By
				long loggedInUser = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);

				/* Get The Person Post */
				long loggedInpostId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
				OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);
				/* End of the geting Person Post Code */

				/* Getting a Lang Id Code for English*/
				long langIdEng=1;
				CmnLanguageMst cmnLangMstEng = new CmnLanguageMst();
				cmnLangMstEng.setLangId(langIdEng);
				/* End of Lang ID Code */

				//Getting a Lang Id Code for Gujarati
				long langIdGuj=2;
				CmnLanguageMst cmnLangMstGuj = new CmnLanguageMst();
				cmnLangMstGuj.setLangId(langIdGuj);
				
				String actionFlag ="" ;
				if(actionFlag!=null)
				{
					actionFlag=objServiceArgs.get("flag").toString();
				}
				long userID = Long.parseLong(objServiceArgs.get("userID").toString());
				CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());					
				CmnLookupMst cmnLookupMst=cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("ACTIVE STATUS", 1);
				//Getting a Grade Id code
				OrgGradeMst obGradeMst = new OrgGradeMst();
				long srFlag = 1;

				OrgEmpMst orgEmpMstEng = (OrgEmpMst)objServiceArgs.get("empInfoEng");				
				OrgEmpMst orgEmpMstGuj = (OrgEmpMst)objServiceArgs.get("empInfoGuj");

				orgEmpMstEng.setCmnLanguageMst(cmnLangMstEng);
				orgEmpMstGuj.setCmnLanguageMst(cmnLangMstGuj);

				// Updation of User - Starts
				logger.info("Update User Info*********** ");
				OrgUserMst orgUserMst = orgEmpMstEng.getOrgUserMst();				
				orgUserMst.setCmnLookupMst(cmnLookupMst);							
				logger.info(" User ID to be update ****** : "+orgUserMst.getUserId());				
				orgUserMst.setUserId(userID);
				orgUserMstDaoImpl.update(orgUserMst);
				
				// Creation of User - Ends
				// THIS IS IMPORTANT PART
				if(orgUserMst != null)
				{
					orgEmpMstEng.setOrgUserMst(orgUserMst);
					orgEmpMstGuj.setOrgUserMst(orgUserMst);
				}
				// END
				logger.info("Update Employee Info*********** ");
				long gradeId = orgEmpMstEng.getOrgGradeMst().getGradeId();
				logger.info("gradeId::"+gradeId);
				OrgGradeMstDao gradeMstDao = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
				obGradeMst = gradeMstDao.read(gradeId);

				String[] strOrgEmpMstColumns = {"cmnLanguageMst","orgUserMst"};
				Object[] orgEmpMstValuesEng ={cmnLangMstEng, orgUserMst};
				Object[] orgEmpMstValuesGuj ={cmnLangMstGuj, orgUserMst};

				EmpDAO orgEmpDAO = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());

				List empInfoDtlsEng = orgEmpDAO.getListByColumnAndValue(strOrgEmpMstColumns, orgEmpMstValuesEng);
				List empInfoDtlsGuj = orgEmpDAO.getListByColumnAndValue(strOrgEmpMstColumns, orgEmpMstValuesGuj);

		
				logger.info(" Updation Into Employee :::");
				OrgEmpMst orgEmpMstEngpk = null;
				if(empInfoDtlsEng!=null && !empInfoDtlsEng.isEmpty()){
					orgEmpMstEngpk = (OrgEmpMst)empInfoDtlsEng.get(0);
				}
				OrgEmpMst objOrgempMstEng = null;
				if(orgEmpMstEngpk != null && orgEmpMstEngpk.getEmpId() != 0)
				{
					long reqIdEng = orgEmpMstEngpk.getEmpId();
					objOrgempMstEng =(OrgEmpMst)orgEmpDAO.read(reqIdEng);
				}
				if(objOrgempMstEng!=null)
				{						
					objOrgempMstEng.setEmpPrefix(orgEmpMstEng.getEmpPrefix());
					objOrgempMstEng.setEmpFname(orgEmpMstEng.getEmpFname());
					objOrgempMstEng.setEmpMname(orgEmpMstEng.getEmpMname());
					objOrgempMstEng.setEmpLname(orgEmpMstEng.getEmpLname());
					objOrgempMstEng.setEmpDob(orgEmpMstEng.getEmpDob());
					objOrgempMstEng.setEmpDoj(orgEmpMstEng.getEmpDoj());
					objOrgempMstEng.setEmpSrvcExp(orgEmpMstEng.getEmpSrvcExp());
					objOrgempMstEng.setOrgGradeMst(obGradeMst);
					objOrgempMstEng.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
					objOrgempMstEng.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
					objOrgempMstEng.setUpdatedDate(sysdate);
					orgEmpDAO.update(objOrgempMstEng);
					
				}
				logger.info(" Updation Into Employee for Guj entry:::");
				OrgEmpMst orgEmpMstGujpk=null;
				if(empInfoDtlsGuj!=null && !empInfoDtlsGuj.isEmpty())
					orgEmpMstGujpk = (OrgEmpMst)empInfoDtlsGuj.get(0);
				OrgEmpMst objOrgempMstGuj = null;
				if(orgEmpMstGujpk != null && orgEmpMstGujpk.getEmpId() != 0)
				{
					long reqIdGuj = orgEmpMstGujpk.getEmpId();
					objOrgempMstGuj =(OrgEmpMst)orgEmpDAO.read(reqIdGuj);
				}
				if(objOrgempMstGuj!=null)
				{	
					objOrgempMstGuj.setEmpPrefix(orgEmpMstGuj.getEmpPrefix());
					objOrgempMstGuj.setEmpFname(orgEmpMstGuj.getEmpFname());
					objOrgempMstGuj.setEmpMname(orgEmpMstGuj.getEmpMname());
					objOrgempMstGuj.setEmpLname(orgEmpMstGuj.getEmpLname());
					objOrgempMstGuj.setEmpDob(orgEmpMstGuj.getEmpDob());
					objOrgempMstGuj.setEmpDoj(orgEmpMstGuj.getEmpDoj());
					objOrgempMstGuj.setEmpSrvcExp(orgEmpMstGuj.getEmpSrvcExp());
					objOrgempMstGuj.setOrgGradeMst(obGradeMst);
					objOrgempMstGuj.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
					objOrgempMstGuj.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
					objOrgempMstGuj.setUpdatedDate(sysdate);
					orgEmpDAO.update(objOrgempMstGuj);
				}

				//Update Basic Emp Info Code ends
				
				// Update Details of employee regarding GPF Details, PAN No etc.
				logger.info(" Updating emp details....");
				HrEisEmpMst empMst = (HrEisEmpMst) objServiceArgs.get("hrEisEmpMst");
				OrgEmpMst gpfNoOrgEmpMst = (OrgEmpMst)objServiceArgs.get("gpfNoOrgEmpMst");
				logger.info("gpfNoOrgEmpMst.getgpd no:::"+gpfNoOrgEmpMst.getEmpGPFnumber());
				
				HrEisEmpMst hrEisEmpMst = null;
				Date sysdate = new Date();
				EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				EmpDAOImpl orgEmpDAOImpl = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());

				// reading grade Mst and setting it into org emp mst vo and hrGpfBalanceDtls vo
				OrgGradeMst orgGradeMst=null;
				gradeId=0;
				if(!objServiceArgs.get("gradeId").toString().equals(""))
				{
					gradeId=Long.parseLong(objServiceArgs.get("gradeId").toString());
					logger.info(" the value of the gradeId is ::"+gradeId);
					OrgGradeMstDaoImpl orgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
					orgGradeMst =orgGradeMstDaoImpl.read(gradeId);
				}			
				// reading grade mst ends 

				//Update pan no dtls - proof dtls
				GenericDaoHibernateImpl generateDao = new GenericDaoHibernateImpl(HrEisProofDtl.class);
				generateDao.setSessionFactory(serv.getSessionFactory());
				HrEisProofDtl hrEisProofDtl = new HrEisProofDtl();
				// Added By Varun For GPF A/C Date:-31-07-2008
				GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(HrPayGpfBalanceDtls.class);
				genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
				HrPayGpfBalanceDtls hrGpfBalanceDtls = new HrPayGpfBalanceDtls();
				//userID = 0;
				// Ended By Varun For GPF A/C Date:-31-07-2008

				if (objServiceArgs.get("hrEisProofDtl") != null) 
					hrEisProofDtl = (HrEisProofDtl) objServiceArgs.get("hrEisProofDtl");
				IdGenerator IdGen = new IdGenerator();
				// End.
				long empId = Long.parseLong(objServiceArgs.get("empId").toString());

				// if edit flag is Y
				if (actionFlag.equalsIgnoreCase("edit")) 
				{
					logger.info(" updating data related to emp master and gpf details edit flag is Y-------------");
					logger.info(" empId is :: " + empId);
					hrEisEmpMst = empinfodao.read(empId);
					// Added By Varun For GPF A/C Date:-31-07-2008
					userID = hrEisEmpMst.getOrgEmpMst().getOrgUserMst().getUserId();
					logger.info("the value of the userID is ::"+userID);
					hrGpfBalanceDtls = (HrPayGpfBalanceDtls) genericDaoHibernateImpl.read(userID);
					if (hrGpfBalanceDtls != null) 
					{
						logger.info("Updating gpf details:::");
						logger.info("GPF NUMBER IS:->"+ hrGpfBalanceDtls.getGpfAccNo()+" with org gpf number "+ gpfNoOrgEmpMst.getEmpGPFnumber()+" end");
						if(gpfNoOrgEmpMst.getEmpGPFnumber()!=null && !gpfNoOrgEmpMst.getEmpGPFnumber().trim().equalsIgnoreCase(""))
						{
							logger.info("inside if");
							hrGpfBalanceDtls.setGpfAccNo(gpfNoOrgEmpMst.getEmpGPFnumber());
						}
						else
						{
							logger.info("inside else");
							hrGpfBalanceDtls.setGpfAccNo(" ");
						}
						logger.info("after GPF NUMBER IS:->"+ hrGpfBalanceDtls.getGpfAccNo());
						hrGpfBalanceDtls.setOrgGradeMst(orgGradeMst);
						genericDaoHibernateImpl.update(hrGpfBalanceDtls);
					} 
					else 
					{
						logger.info(" gpf dtls insertion");
						HrPayGpfBalanceDtls gpfBalanceDtls = new HrPayGpfBalanceDtls();
						gpfBalanceDtls.setGpfAccNo(gpfNoOrgEmpMst.getEmpGPFnumber());
						gpfBalanceDtls.setUserId(userID);
						gpfBalanceDtls.setOrgGradeMst(orgGradeMst);
						gpfBalanceDtls.setTrnCounter(1);
						gpfBalanceDtls.setCmnDatabaseMst(cmnDatabaseMst);
						gpfBalanceDtls.setCmnLocationMst(cmnLocationMst);
						gpfBalanceDtls.setCreatedDate(sysdate);
						gpfBalanceDtls.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
						gpfBalanceDtls.setOrgUserMstByCreatedByUser(loggedInOrgUserMst);
						genericDaoHibernateImpl.create(gpfBalanceDtls);
					}
					
					// For Mst
					logger.info("Update Eis Other Details Info*********** ");
					OrgEmpMst updOrgEmpMst = new OrgEmpMst();
					
					updOrgEmpMst = orgEmpDAOImpl.read(hrEisEmpMst.getOrgEmpMst()
							.getEmpId());
					hrEisEmpMst.setEmail(empMst.getEmail());
					hrEisEmpMst.setOrgEmpMst(updOrgEmpMst);
					hrEisEmpMst.setEmpGender(empMst.getEmpGender());
					hrEisEmpMst.setEmpApptLtrDt(empMst.getEmpApptLtrDt());
					hrEisEmpMst.setEmpConfDueDt(empMst.getEmpConfDueDt());
					hrEisEmpMst.setEmpHobby(empMst.getEmpHobby());
					hrEisEmpMst.setEmpType(empMst.getEmpType());				
					hrEisEmpMst.setOrgPostMst(loggedInOrgPostMst);
					hrEisEmpMst.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);					
					hrEisEmpMst.setUpdatedDate(sysdate);
					hrEisEmpMst.setContactNo(empMst.getContactNo());
					empinfodao.update(hrEisEmpMst);

					HrEisProofDtl eisProofDtl = new HrEisProofDtl();
					List lstProof = generateDao.getListByColumnAndValue("orgPostMstByUserId", updOrgEmpMst.getOrgUserMst());
					if (lstProof != null && lstProof.size() > 0	&& lstProof.get(0) != null) 
					{	
						eisProofDtl = (HrEisProofDtl) lstProof.get(0);
					}
					if (eisProofDtl != null && eisProofDtl.getOrgPostMstByUserId() != null) 
					{
						if (hrEisProofDtl.getProofNum().equals("")|| hrEisProofDtl.getProofNum() == null)
							eisProofDtl.setProofNum(" ");
						else
							eisProofDtl.setProofNum(hrEisProofDtl.getProofNum());
						eisProofDtl.setUpdatedDate(sysdate);
						eisProofDtl.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
						eisProofDtl.setOrgUserMstByUpdatedByPost(loggedInOrgPostMst);
						generateDao.update(eisProofDtl);
					} 
					else 
					{				
						CmnLookupMst proofLookupId = new CmnLookupMst();
						long proofId = 300166;
						long proofDtlId = IdGen.PKGenerator("HR_EIS_PROOF_DTL",objServiceArgs);
						CmnLookupMstDAO cmnLookupDao = new CmnLookupMstDAOImpl(	CmnLookupMst.class, serv.getSessionFactory());
						hrEisProofDtl.setSrNo(proofDtlId);					
						proofLookupId = cmnLookupDao.read(proofId);
						hrEisProofDtl.setCmnLookupMst(proofLookupId);
						hrEisProofDtl.setCmnDatabaseMst(cmnDatabaseMst);
						hrEisProofDtl.setCmnLocationMst(cmnLocationMst);
						hrEisProofDtl.setCreatedDate(sysdate);
						hrEisProofDtl.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
						hrEisProofDtl.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
						hrEisProofDtl.setOrgPostMstByUserId(orgUserMst);
						logger.info("Sr. No is:-" + hrEisProofDtl.getSrNo());
						logger.info("Proof No is:-" + hrEisProofDtl.getProofNum());
						logger.info("Database Id is:-"+ hrEisProofDtl.getCmnDatabaseMst().getDbId());
						logger.info("ProofType Name is:-"+ hrEisProofDtl.getCmnLookupMst().getLookupName());
						generateDao.create(hrEisProofDtl);
					}	
				}		
				// end of Update emp master and gpf dtls insertion / updation
				// Update Bank Dtls
				logger.info("Update bank Info*********** ");				
				HrEisBankDtls bankDtls = (HrEisBankDtls)objServiceArgs.get("bankDtls");
				BankDetailDAOImpl bankDetailDAO = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
				
				long bankId=bankDtls.getMstBank().getBankId();
				long branchId = bankDtls.getRltBankBranch().getBranchId();
				long accountId=bankDtls.getBankAcctType();
				//long bEmpId=bankDtls.getHrEisEmpMst().getEmpId();
				OrgUserMst orgUsrMst= new OrgUserMst();
				OrgEmpMst orgEmpMst = new OrgEmpMst();
				IdGenerator idGen=null;
				logger.info("the emp id is "+empId);				
				if (actionFlag.equalsIgnoreCase("edit")) 
				{         	

					HrEisBankDtls bankDtlsVO = new HrEisBankDtls();
					logger.info("empMst.getEmpId:::"+empMst.getEmpId());
					logger.info("bankDtls.getBankAcctNo():::"+bankDtls.getBankAcctNo());					
					long bankDtlId = bankDtls.getBankDtlId();
					logger.info("bankDtlId::"+bankDtlId);
					if(bankDtlId!=0)
					{	
						bankDtlsVO = bankDetailDAO.read(bankDtlId);
						logger.info("INSIDE UPDATE insertBankMasterDtls");
						bankDtlsVO.setBankAcctNo(bankDtls.getBankAcctNo());
						bankDtlsVO.setBankAcctStartDt(bankDtls.getBankAcctStartDt());
						bankDtlsVO.setBankAcctType(bankDtls.getBankAcctType());
						bankDtlsVO.setMstBank(bankDtls.getMstBank());
						bankDtlsVO.setRltBankBranch(bankDtls.getRltBankBranch());
						bankDtlsVO.setUpdatedDate(sysdate);	
						bankDtlsVO.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
						bankDtlsVO.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);	
						bankDetailDAO.update(bankDtlsVO);
						objServiceArgs.put("msg", "Record Updated Successfully");
					}
					else
					{
						idGen = new IdGenerator();
						long bankDtlsId = idGen.PKGenerator("HR_EIS_BANK_DTLS", objServiceArgs);
						bankDtlsVO.setBankDtlId(bankDtlsId);
						bankDtlsVO.setBankAcctNo(bankDtls.getBankAcctNo());
						bankDtlsVO.setBankAcctStartDt(bankDtls.getBankAcctStartDt());
						bankDtlsVO.setBankAcctType(bankDtls.getBankAcctType());
						bankDtlsVO.setMstBank(bankDtls.getMstBank());
						bankDtlsVO.setRltBankBranch(bankDtls.getRltBankBranch());
						bankDtlsVO.setHrEisEmpMst(empMst);
						bankDtlsVO.setCmnDatabaseMst(cmnDatabaseMst);
						bankDtlsVO.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
						bankDtlsVO.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
						bankDtlsVO.setCreatedDate(sysdate);			
						bankDetailDAO.create(bankDtlsVO);
					}
					msg=1;
				}
				BankMasterDAOImpl bankMaster = new BankMasterDAOImpl(MstBankPay.class,serv.getSessionFactory());
				List bankList = bankMaster.getAllBankMasterData(langId);

				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				List accTypes = lookupDAO.getAllChildrenByLookUpNameAndLang("Account Type", langId);

				List empList = new ArrayList();

				objServiceArgs.put("bankList", bankList);
				objServiceArgs.put("accTypes", accTypes);

				// Update Bank Dtls ends
				
				//--------------------------------------------------------------------
				// Update info regarding New Post and its mapping with new User
				OrgPostDetailsRlt orgPostDetailsRlt_gu = (OrgPostDetailsRlt) objServiceArgs.get("orgPostDetailsRlt_gu");
				OrgPostDetailsRlt orgPostDetailsRlt_en = (OrgPostDetailsRlt) objServiceArgs.get("orgPostDetailsRlt_en");
				HrPayPsrPostMpg hrPayPsrPostMpg = new HrPayPsrPostMpg();
				PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(
						HrPayPsrPostMpg.class, serv.getSessionFactory());
				long psrId = 0L;
				long psrpostId = 0L;		
				hrPayPsrPostMpg = (HrPayPsrPostMpg) objServiceArgs.get("hrPayPsrPostMpg");
				psrId = hrPayPsrPostMpg.getPsrId();
				psrpostId = hrPayPsrPostMpg.getPsrPostId();
				logger.info((new StringBuilder("Psr Id is:-->>")).append(psrId).toString());
				logger.info((new StringBuilder("psrpostId is:-->>")).append(psrpostId).toString());
				OrgPostMst orgPostMst = (OrgPostMst) objServiceArgs.get("orgPostMst");
				OrgUserpostRlt orgUserpostRltVoGen= (OrgUserpostRlt)objServiceArgs.get("orgUserpostRlt");				
				long postIdForPsr = orgUserpostRltVoGen.getOrgPostMstByPostId().getPostId();
				logger.info("postIdForPsr::"+postIdForPsr);
				CmnBranchMst cmnBranchMst = null;
				CmnBranchMst objCmnBranchMst_en = new CmnBranchMst();
				CmnBranchMst objCmnBranchMst_gu = new CmnBranchMst();
				OrgDesignationMst orgDesignationMst = (OrgDesignationMst) objServiceArgs.get("orgDesignationMst");

				OrgPostMstDao orgPostMstDao = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
				OrgPostDetailsRltDao orgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
				OrgDesignationMstDao orgDesignationMstDao = new OrgDesignationMstDaoImpl(OrgDesignationMst.class, serv.getSessionFactory());
				CmnBranchMstDao cmnBranchMstDao = new CmnBranchMstDaoImpl(CmnBranchMst.class, serv.getSessionFactory());
				if (objServiceArgs.containsKey("cmnBranchMst"))
					cmnBranchMst = (CmnBranchMst) objServiceArgs.get("cmnBranchMst");
				if (cmnBranchMst != null) 
				{
					cmnLanguageMst = new CmnLanguageMst();
					cmnLanguageMst.setLangId(1L);
					String strCmnBranchMstColumns_en[] = { "cmnLanguageMst","branchCode" };
					Object cmnBranchMstValues_en[] = { cmnLanguageMst,cmnBranchMst.getBranchCode() };
					List arCmnBranchMst = cmnBranchMstDao.getListByColumnAndValue(strCmnBranchMstColumns_en, cmnBranchMstValues_en);
					if (arCmnBranchMst!=null && arCmnBranchMst.size() == 1)
						objCmnBranchMst_en = (CmnBranchMst) arCmnBranchMst.get(0);
					cmnLanguageMst.setLangId(2L);
					String strCmnBranchMstColumns_gu[] = { "cmnLanguageMst","branchCode" };
					Object cmnBranchMstValues_gu[] = { cmnLanguageMst,cmnBranchMst.getBranchCode() };
					arCmnBranchMst = cmnBranchMstDao.getListByColumnAndValue(strCmnBranchMstColumns_gu, cmnBranchMstValues_gu);
					if (arCmnBranchMst.size() == 1)
						objCmnBranchMst_gu = (CmnBranchMst) arCmnBranchMst.get(0);
				}
				OrgDesignationMst orgDesignationMst_en = orgDesignationMstDao.getDesignationVOByDsgnCodeAndLangId(orgDesignationMst.getDsgnCode(), Long.valueOf(1L));
				OrgDesignationMst orgDesignationMst_gu = orgDesignationMstDao.getDesignationVOByDsgnCodeAndLangId(orgDesignationMst.getDsgnCode(), Long.valueOf(2L));

				logger.info("Update Post and user mapping Info*********** ");
				String changePostCheck="",prevPostEndDate="";
				changePostCheck = objServiceArgs.get("changePostCheck")!=null ?objServiceArgs.get("changePostCheck").toString():"";
				prevPostEndDate = objServiceArgs.get("prevPostEndDate")!=null ?objServiceArgs.get("prevPostEndDate").toString():"";
				
				String selPostFlag="0";
				if(objServiceArgs.get("selPostFlag")!=null)
					selPostFlag = objServiceArgs.get("selPostFlag").toString();
				long selPostId = 0;
				if(objServiceArgs.get("selPostId")!=null )
					selPostId= Long.parseLong(objServiceArgs.get("selPostId").toString());
				if("Y".equalsIgnoreCase(changePostCheck) && selPostFlag.equals("0"))
					selPostId = 0;
				logger.info("selPostId::"+selPostId);
				if(selPostId!=0)
					orgPostMst = (OrgPostMst) orgPostMstDao.read(Long.valueOf(selPostId));
				
				if (orgPostMst.getPostTypeLookupId() != null) 
				{
					logger.info("In the If condition");					
					CmnLookupMst postTypeLookupId = orgPostMst.getPostTypeLookupId();
					cmnLookupMst = cmnLookupMstDAO.getLookUpVOByLookUpIDAndLang(13L, 1L);
					logger.info((new StringBuilder("Lookup Desc.")).append(cmnLookupMst.getLookupDesc()).toString());
					postTypeLookupId = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(postTypeLookupId.getLookupName(), 1L);
					logger.info((new StringBuilder("PostTypeLookup Desc.")).append(postTypeLookupId.getLookupDesc()).toString());
					orgPostMst.setPostTypeLookupId(postTypeLookupId);
					orgPostMst.setCmnLookupMst(cmnLookupMst);
				}		
				long setPostId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
				OrgPostMst setOrgPostMst = (OrgPostMst) orgPostMstDao.read(Long.valueOf(setPostId));
				dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
				cmnDatabaseMst = (CmnDatabaseMst) cmnDatabaseMstDaoImpl.read(Long.valueOf(dbId));
				CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst_en = (CmnLanguageMst) cmnLanguageMstDao.read(Long.valueOf(1L));
				CmnLanguageMst cmnLanguageMst_gu = (CmnLanguageMst) cmnLanguageMstDao.read(Long.valueOf(2L));
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
				CmnLocationMst cmnLocationMst_en = cmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId(cmnLocationMst.getLocationCode(), Long.valueOf(1L));
				CmnLocationMst cmnLocationMst_gu = cmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId(cmnLocationMst.getLocationCode(), Long.valueOf(2L));
				/*OrgEmpMst orgEmpMst = new OrgEmpMst();
				orgEmpMst.setEmpId(Long.parseLong(loginDetailsMap.get("empId").toString()));*/
				// TODO This Block will update the old user post mapping. end date will be updated.
				// if changePostCheck is not null and changePostCheck value is Y
				
				long lOhpId = 0;
				if(objServiceArgs.get("lOhpId")!=null )
					lOhpId= Long.parseLong(objServiceArgs.get("lOhpId").toString());
				logger.info("lOhpId::"+lOhpId);
				String lStrOhpId = "0";
				if(objServiceArgs.get("ohpId")!=null)
					lStrOhpId = objServiceArgs.get("ohpId").toString();			
				logger.info("lStrOhpId::"+lStrOhpId);
				
				long newPostId=0;
				logger.info("changePostCheck:::"+changePostCheck);
				logger.info("prevPostEndDate:::"+prevPostEndDate);
				logger.info("actionFlag:::"+actionFlag);
				
				long oldPostId = 0;
				if("Y".equalsIgnoreCase(changePostCheck) && !"".equals(prevPostEndDate) &&  actionFlag.equalsIgnoreCase("edit"))
				{
					logger.info(" Updating Old user post mapping....making end date");
					oldPostId = orgPostMst.getPostId();
					long lUserPostId=Long.parseLong(objServiceArgs.get("lUserPostRltId").toString());
					logger.info("updating old user post mapping --lUserPostId:::"+lUserPostId);
					UserPostDao userPostDao  = new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());	
					OrgUserpostRlt userPostRltVO = new OrgUserpostRlt();
					userPostRltVO = userPostDao.read(lUserPostId);
					logger.info("userPostRltVO.getEmpPostId:::"+userPostRltVO.getEmpPostId());
					userPostRltVO.setEndDate(StringUtility.convertStringToDate(prevPostEndDate));
					userPostRltVO.setActivateFlag(0L);
					userPostDao.update(userPostRltVO);
				}
				
				if("Y".equalsIgnoreCase(changePostCheck) && selPostFlag.equals("0"))
					actionFlag = "add";
				// TODO
				if ( actionFlag.equalsIgnoreCase("add")) 
				{
					newPostId = IDGenerateDelegate.getNextId("ORG_POST_MST",objServiceArgs);
					long orgPostDtlId_en = IDGenerateDelegate.getNextId("ORG_POST_DETAILS_RLT", objServiceArgs);
					long orgPostDtlId_gu = IDGenerateDelegate.getNextId("ORG_POST_DETAILS_RLT", objServiceArgs);
					logger.info("newPostId:::"+newPostId);					
					//Creating New Post
					orgPostMst.setPostId(newPostId);
					orgPostMst.setDsgnCode(orgDesignationMst_en.getDsgnCode());
					if (cmnBranchMst != null)
						orgPostMst.setBranchCode(cmnBranchMst.getBranchCode());
					orgPostMst.setLocationCode(cmnLocationMst_en.getLocationCode());
					orgPostMst.setCreatedDate(sysdate);
					orgPostMst.setOrgPostMstByCreatedByPost(setOrgPostMst);
					orgPostMst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					orgPostMst.getPostTypeLookupId();
					orgPostMstDao.create(orgPostMst);
					logger.info(" New Post Created!!!!!!!");
					//Creating org Post Details entry
					orgPostDetailsRlt_en.setOrgDesignationMst(orgDesignationMst_en);
					orgPostDetailsRlt_en.setCmnLocationMst(cmnLocationMst_en);
					orgPostDetailsRlt_en.setCmnLanguageMst(cmnLanguageMst_en);
					orgPostDetailsRlt_en.setCmnBranchMst(objCmnBranchMst_en);					
					orgPostDetailsRlt_en.setCreatedDate(sysdate);					
					orgPostDetailsRlt_en.setOrgPostMstByCreatedByPost(setOrgPostMst);					
					orgPostDetailsRlt_en.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					orgPostDetailsRlt_en.setPostDetailId(orgPostDtlId_en);
					orgPostDetailsRlt_en.setOrgPostMst(orgPostMst);
					orgPostDetailsRltDao.create(orgPostDetailsRlt_en);
					logger.info(" New Post Details entry Created!!!!!!!");
					//Creating HrPayPsrPostMpg entry
					long psrPostId = IDGenerateDelegate.getNextId("HR_PAY_POST_PSR_MPG", objServiceArgs);
					logger.info((new StringBuilder("Admin Generated hr_pay_post_psr_mpg is : ")).append(psrPostId).toString());
					hrPayPsrPostMpg.setPostId(newPostId);
					hrPayPsrPostMpg.setPsrId(psrId);
					hrPayPsrPostMpg.setLoc_id(Long.parseLong(cmnLocationMst_en.getLocationCode()));
					hrPayPsrPostMpg.setPsrPostId(psrPostId);
					psrPostMpgDAOImpl.create(hrPayPsrPostMpg);
					//added by ravysh for office post mapping 
					if(locId==Long.parseLong(resourceBundle.getString("cash2Admin")))
					{
						HrPayOfficepostMpg hrOfficePostMpg = (HrPayOfficepostMpg) objServiceArgs.get("hrOfficePostMpg");

						long officePostId = IDGenerateDelegate.getNextId("HR_PAY_OFFICEPOST_MPG",objServiceArgs);
						logger.info("generated officePostId is ===>"+officePostId);
						hrOfficePostMpg.setOfficePostId(officePostId);
						hrOfficePostMpg.setCmnLocationMst(cmnLocationMst_en);
						hrOfficePostMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
						hrOfficePostMpg.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
						hrOfficePostMpg.setOrgPostMstByCreatedByPost(setOrgPostMst);
						hrOfficePostMpg.setOrgPostMstByUpdatedByPost(setOrgPostMst);
						hrOfficePostMpg.setOrgPostMstByPostId(orgPostMst);
						HrPayOfficePostMpgDAOImpl officePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());
						officePostMpgDAOImpl.create(hrOfficePostMpg);

						/*if(hrOfficePostMpg.getOrgPostMstParentPostId()!=null)
						{
							orgPostMst=orgPostMstDao.read(hrOfficePostMpg.getOrgPostMstParentPostId().getPostId());
							if(orgPostMst.getEndDate()==null)
							{
								orgPostMst.setEndDate(sysdate);
								orgPostMstDao.update(orgPostMst);
								UserPostDao userPostDao = new UserPostDaoImpl(OrgUserpostRlt.class, serv.getSessionFactory());
								List<OrgUserpostRlt> orgUserpostRltList=userPostDao.getListByColumnAndValue("orgPostMstByPostId", hrOfficePostMpg.getOrgPostMstParentPostId());
								for(int i=0;i<orgUserpostRltList.size();i++)
								{
									OrgUserpostRlt orgUserpostRlt=orgUserpostRltList.get(i);
									if(orgUserpostRlt.getEndDate()==null)
									{
										orgUserpostRlt.setEndDate(sysdate);
										userPostDao.update(orgUserpostRlt);
									}
								}
								List<HrPayOfficepostMpg> hrOfficePostMpgList = officePostMpgDAOImpl.getListByColumnAndValue("orgPostMstByPostId", hrOfficePostMpg.getOrgPostMstParentPostId());

								hrOfficePostMpg=hrOfficePostMpgList.get(0);
								hrOfficePostMpg.setEndDate(sysdate);
								officePostMpgDAOImpl.update(hrOfficePostMpg);
							}

						}*/
					}
					//ended by ravysh
				} 
				else 
				{
					logger.info("Edit mode::::");
					logger.info((new StringBuilder("Upadte Post Id is : ")).append(orgPostMst.getPostId()).toString());
					logger.info((new StringBuilder("Upadte Org Post Dtl en_Id is : ")).append(orgPostDetailsRlt_en.getPostDetailId()).toString());
					OrgPostMst updateOrgPostMst = (OrgPostMst) orgPostMstDao.read(Long.valueOf(orgPostMst.getPostId()));
					if (updateOrgPostMst != null) 
					{
						OrgPostDetailsRlt updateOrgPostDetailsRlt_gu = orgPostDetailsRltDao.getPostDetailsRltByPostIdAndLangId(Long.valueOf(orgPostMst.getPostId()), Long.valueOf(2L));
						OrgPostDetailsRlt updateOrgPostDetailsRlt_en = orgPostDetailsRltDao.getPostDetailsRltByPostIdAndLangId(Long.valueOf(orgPostMst.getPostId()), Long.valueOf(1L));
						updateOrgPostMst.setPostLevelId(orgPostMst.getPostLevelId());
						updateOrgPostMst.setStartDate(orgPostMst.getStartDate());
						updateOrgPostMst.setEndDate(orgPostMst.getEndDate());
						updateOrgPostMst.setParentPostId(orgPostMst.getParentPostId());
						updateOrgPostMst.setDsgnCode(orgDesignationMst_en.getDsgnCode());
						updateOrgPostMst.setActivateFlag(orgPostMst.getActivateFlag());
						if (cmnBranchMst != null)
							updateOrgPostMst.setBranchCode(cmnBranchMst.getBranchCode());
						else
							updateOrgPostMst.setBranchCode(null);
						updateOrgPostMst.setLocationCode(cmnLocationMst_en.getLocationCode());
						if (updateOrgPostDetailsRlt_en != null) 
						{
							updateOrgPostDetailsRlt_en.setOrgDesignationMst(orgDesignationMst_en);
							updateOrgPostDetailsRlt_en.setCmnLocationMst(cmnLocationMst_en);
							updateOrgPostDetailsRlt_en.setCmnLanguageMst(cmnLanguageMst_en);
							updateOrgPostDetailsRlt_en.setUpdatedDate(sysdate);
							updateOrgPostDetailsRlt_en.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
							updateOrgPostDetailsRlt_en.setOrgPostMstByUpdatedByPost(setOrgPostMst);
							updateOrgPostDetailsRlt_en.setCmnBranchMst(objCmnBranchMst_en);
							updateOrgPostDetailsRlt_en.setPostName(orgPostDetailsRlt_en.getPostName());
							updateOrgPostDetailsRlt_en.setPostShortName(orgPostDetailsRlt_en.getPostShortName());
						}
						if (updateOrgPostDetailsRlt_gu != null) 
						{
							updateOrgPostDetailsRlt_gu.setOrgDesignationMst(orgDesignationMst_gu);
							updateOrgPostDetailsRlt_gu.setCmnLocationMst(cmnLocationMst_gu);
							updateOrgPostDetailsRlt_gu.setCmnLanguageMst(cmnLanguageMst_gu);
							updateOrgPostDetailsRlt_gu.setUpdatedDate(sysdate);
							updateOrgPostDetailsRlt_gu.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
							updateOrgPostDetailsRlt_gu.setOrgPostMstByUpdatedByPost(setOrgPostMst);
							updateOrgPostDetailsRlt_gu.setCmnBranchMst(objCmnBranchMst_gu);
							updateOrgPostDetailsRlt_gu.setPostName(orgPostDetailsRlt_gu.getPostName());
							updateOrgPostDetailsRlt_gu.setPostShortName(orgPostDetailsRlt_gu.getPostShortName());
						}
						updateOrgPostMst.setActivateFlag(orgPostMst.getActivateFlag());
						if (orgPostMst.getPostTypeLookupId() != null)
							updateOrgPostMst.setPostTypeLookupId(orgPostMst.getPostTypeLookupId());
						else
							updateOrgPostMst.setPostTypeLookupId(null);
						updateOrgPostMst.setCmnLookupMst(orgPostMst.getCmnLookupMst());
						updateOrgPostMst.setUpdatedDate(sysdate);
						updateOrgPostMst.setOrgPostMstByUpdatedByPost(setOrgPostMst);
						updateOrgPostMst.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
						orgPostMstDao.update(updateOrgPostMst);
						
						HrPayPsrPostMpg payPsrPostMpg = (HrPayPsrPostMpg) psrPostMpgDAOImpl.read(Long.valueOf(psrpostId));
						payPsrPostMpg.setPsrId(psrId);
						psrPostMpgDAOImpl.update(payPsrPostMpg);
						if (updateOrgPostDetailsRlt_en != null)
							orgPostDetailsRltDao.update(updateOrgPostDetailsRlt_en);

						//added by ravysh
						if(locId==Long.parseLong(resourceBundle.getString("cash2Admin")))
						{							
							HrPayOfficepostMpg hrOfficePostMpg = (HrPayOfficepostMpg) objServiceArgs.get("hrOfficePostMpg");
							HrPayOfficePostMpgDAOImpl officePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());
							List<HrPayOfficepostMpg> hrOfficePostMpgList = officePostMpgDAOImpl.getListByColumnAndValue("orgPostMstByPostId",orgPostMst);


							if(hrOfficePostMpgList.size()==0)
							{
								long officePostId = IDGenerateDelegate.getNextId("HR_PAY_OFFICEPOST_MPG",objServiceArgs);
								logger.info("generated officePostId is ===>"+officePostId);
								hrOfficePostMpg.setOfficePostId(officePostId);
								hrOfficePostMpg.setCmnLocationMst(cmnLocationMst_en);
								hrOfficePostMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
								hrOfficePostMpg.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
								hrOfficePostMpg.setOrgPostMstByCreatedByPost(setOrgPostMst);
								hrOfficePostMpg.setOrgPostMstByUpdatedByPost(setOrgPostMst);
								hrOfficePostMpg.setOrgPostMstByPostId(orgPostMst);
								officePostMpgDAOImpl.create(hrOfficePostMpg);
							}
							else
							{
								/*hrOfficePostMpgList.get(0).setHrPayOfficeMst(hrOfficePostMpg.getHrPayOfficeMst());
								hrOfficePostMpgList.get(0).setOrgPostMstParentPostId(hrOfficePostMpg.getOrgPostMstParentPostId());
								hrOfficePostMpgList.get(0).setRemarks(hrOfficePostMpg.getRemarks());
								officePostMpgDAOImpl.update(hrOfficePostMpgList.get(0));*/
							}
							/*if(hrOfficePostMpg.getOrgPostMstParentPostId()!=null)
							{
								OrgPostMst parentOrgPostMst=orgPostMstDao.read(hrOfficePostMpg.getOrgPostMstParentPostId().getPostId());
								if(parentOrgPostMst.getEndDate()==null)
								{
									parentOrgPostMst.setEndDate(sysdate);
									orgPostMstDao.update(parentOrgPostMst);
									UserPostDao userPostDao = new UserPostDaoImpl(OrgUserpostRlt.class, serv.getSessionFactory());
									List<OrgUserpostRlt> orgUserpostRltList=userPostDao.getListByColumnAndValue("orgPostMstByPostId", hrOfficePostMpg.getOrgPostMstParentPostId());

									for(int i=0;i<orgUserpostRltList.size();i++)
									{
										OrgUserpostRlt orgUserpostRlt=orgUserpostRltList.get(i);
										if(orgUserpostRlt.getEndDate()==null)
										{
											orgUserpostRlt.setEndDate(sysdate);
											userPostDao.update(orgUserpostRlt);
										}
									}

									List<HrPayOfficepostMpg> officePostMpgList = officePostMpgDAOImpl.getListByColumnAndValue("orgPostMstByPostId", hrOfficePostMpg.getOrgPostMstParentPostId());

									hrOfficePostMpg=officePostMpgList.get(0);
									hrOfficePostMpg.setEndDate(sysdate);
									officePostMpgDAOImpl.update(hrOfficePostMpg);
								}

							}*/
						}	
					}
					msg = 1;
				}
				// Update info regarding New Post and its mapping with new User ends

				// Update User Post Mapping Details Starts		
				logger.info("User and post mapping starts -post which if it is selected from selection combo not new");
				CmnLookupMstDAOImpl cmnLookupMstDao  = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				cmnLookupMst = cmnLookupMstDao.getLookUpVOByLookUpNameAndLang("Primary_Post",langId);

				String strDesgCode=objServiceArgs.get("selDesg").toString();
				String strDepartmentCode=objServiceArgs.get("strOfficeType").toString();
				String strOfficeName=objServiceArgs.get("strOfficeName").toString();
				long lUserPostRltId=Long.parseLong(objServiceArgs.get("lUserPostRltId").toString());
				logger.info("strDesgCode========="+strDesgCode);
				logger.info("strDepartmentCode========="+strDepartmentCode);
				logger.info("strOfficeName========="+strOfficeName);
				logger.info("lUserPostRltId========="+lUserPostRltId);
				
				UserPostDao userPostDao  = new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());			
				OrgUserpostRlt orgUserpostRlt=new OrgUserpostRlt();
				
				if("Y".equalsIgnoreCase(changePostCheck) &&  actionFlag.equalsIgnoreCase("edit"))
				{	
						actionFlag = "add"; // so that new user post mapping can be added
				}
				if(actionFlag.equals("add"))
				{
					logger.info("Inside lUserPostRltId ==0 Block================= ");
					long orgUserpostRltPk = 0;
					idGen = new IdGenerator();
					orgUserpostRltPk = idGen.PKGenerator("ORG_USERPOST_RLT", objServiceArgs);

					logger.info("Inserting known orgUserpostRltPk PK================= " + orgUserpostRltPk);
					logger.info("newPostId:::"+newPostId);
					if(orgUserpostRltPk!=0)
					{	 
						orgUserpostRlt.setEmpPostId(orgUserpostRltPk);
						orgUserpostRlt.setOrgPostMstByPostId(orgUserpostRltVoGen.getOrgPostMstByPostId());
						orgUserpostRlt.setOrgUserMst(orgUserMst);						
						orgUserpostRlt.setActivateFlag(1);
						orgUserpostRlt.setCreatedDate(new java.util.Date());
						orgUserpostRlt.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
						orgUserpostRlt.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
						orgUserpostRlt.setCmnLookupUserPostType(cmnLookupMst);
						if(newPostId!=0)
							orgUserpostRlt.setOrgPostMstByPostId(orgPostMst);
						else
							orgUserpostRlt.setOrgPostMstByPostId(orgUserpostRltVoGen.getOrgPostMstByPostId());
						orgUserpostRlt.setStartDate(orgUserpostRltVoGen.getStartDate());
						orgUserpostRlt.setEndDate(orgUserpostRltVoGen.getEndDate());
						userPostDao.create(orgUserpostRlt);
					}
				}
				else
				{
					logger.info("Inside lUserPostRltId !=0 Block================= "+lUserPostRltId);
					logger.info("Activate Flag is :--->>>"+orgUserpostRltVoGen.getActivateFlag());					
					long aFlag = orgUserpostRltVoGen.getActivateFlag();
					orgUserpostRlt = userPostDao.read(lUserPostRltId);
					logger.info("orgUserpostRlt getEmpPostId:::"+orgUserpostRlt.getEmpPostId());
					orgUserpostRlt.setUpdatedDate(new java.util.Date());
					orgUserpostRlt.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
					orgUserpostRlt.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
					orgUserpostRlt.setOrgPostMstByPostId(orgUserpostRltVoGen.getOrgPostMstByPostId());
					orgUserpostRlt.setOrgUserMst(orgUserMst);
					orgUserpostRlt.setStartDate(orgUserpostRltVoGen.getStartDate());
					orgUserpostRlt.setEndDate(orgUserpostRltVoGen.getEndDate());
					orgUserpostRlt.setCmnLookupUserPostType(cmnLookupMst);
					orgUserpostRlt.setActivateFlag(aFlag);
					userPostDao.update(orgUserpostRlt);

					logger.info("Inside Updation Block================= ");
				}
				logger.info("User and post mapping ends -post which if it is selected from selection combo not new ");
				//Update User Post Mapping Details ends				
				// Update info related to Order head post mapping
				logger.info("Update order head post mapping Info*********** ");
				//long headId = 0;
				long orderheadId=0;
				long headPostId = 0;
				OrderHeadPostmpgDAOImpl ohpMpgDAO = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());//object of DAOIMPL
				long orderId=0;
				if(objServiceArgs.get("order")!=null)
					orderId=Long.parseLong(objServiceArgs.get("order").toString());
				if(objServiceArgs.get("order")!=null)
					orderheadId=Long.parseLong(objServiceArgs.get("order").toString());
				long billNo =Long.parseLong(objServiceArgs.get("billNo").toString());
				
				//getting  head Id from  order

				List <HrPayOrderHeadPostMpg> headList = (List)ohpMpgDAO.getheadsfromorders(orderId,finYrId);
				StringBuffer propertyList = new StringBuffer();
				
				logger.info("changePostCheck 2:::"+changePostCheck);
				logger.info("prevPostEndDate 2:::"+prevPostEndDate);
				logger.info("actionFlag 2:::"+actionFlag);
					
				if("Y".equalsIgnoreCase(changePostCheck) && actionFlag.equalsIgnoreCase("add") && !selPostFlag.equals("0"))
				{	
						actionFlag="edit";
				}
			
				HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg=new HrPayOrderHeadPostMpg();
				idGen = new IdGenerator();
				logger.info("lOhpId in service ::"+lOhpId);
				if (actionFlag.equalsIgnoreCase("add")) 
				{
					//orderheadId=Long.parseLong(ohpMpgDAO.getorderHeadId(orderId, headId,finYrId).get(0).toString());

					long orderHeadPostId = idGen.PKGenerator("HR_PAY_ORDER_HEAD_POST_MPG", objServiceArgs);
					hrPayOrderHeadPostMpg.setOrderHeadPostId(orderHeadPostId);
					hrPayOrderHeadPostMpg.setOrderHeadId(orderheadId);
					hrPayOrderHeadPostMpg.setPostId(newPostId);
					hrPayOrderHeadPostMpg.setTrnCounter(new Integer(1));
					hrPayOrderHeadPostMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
					hrPayOrderHeadPostMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					hrPayOrderHeadPostMpg.setCreatedDate(sysdate);	
					ohpMpgDAO.create(hrPayOrderHeadPostMpg);//ready to insert
				}  	        
				else
				{	
					//orderheadId=Long.parseLong(ohpMpgDAO.getorderHeadId(orderId, headId,finYrId).get(0).toString());
					hrPayOrderHeadPostMpg.setOrderHeadId(orderheadId);
					logger.info("orderheadId in service update mode::"+orderheadId);
					logger.info("orgUserpostRltVoGen.getOrgPostMstByPostId().getPostId():::"+orgUserpostRltVoGen.getOrgPostMstByPostId().getPostId());
					hrPayOrderHeadPostMpg.setPostId(orgUserpostRltVoGen.getOrgPostMstByPostId().getPostId());
					hrPayOrderHeadPostMpg.setTrnCounter(new Integer(1));
					hrPayOrderHeadPostMpg.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
					hrPayOrderHeadPostMpg.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
					hrPayOrderHeadPostMpg.setUpdatedDate(sysdate);	
					//TODO
					logger.info("lOhpId::"+lOhpId);
					logger.info("lStrOhpId::"+lStrOhpId);

					if((!"Y".equalsIgnoreCase(changePostCheck) ) || (lOhpId!=0 && !lStrOhpId.equals("0")))
					{
						logger.info("ohpid is not 0 in edit mode simply update::");
						if(lOhpId!=0)							
							hrPayOrderHeadPostMpg = ohpMpgDAO.read(lOhpId);
						else
							hrPayOrderHeadPostMpg = ohpMpgDAO.read(Long.parseLong(lStrOhpId));							
						hrPayOrderHeadPostMpg.setOrderHeadId(orderheadId);
						logger.info(" pOst id to be update::"+orgUserpostRltVoGen.getOrgPostMstByPostId().getPostId());						
						ohpMpgDAO.update(hrPayOrderHeadPostMpg);
					}
					else
					{
						logger.info("ohpid is o in edit mode so insert new entry::");
						long orderHeadPostId = idGen.PKGenerator("HR_PAY_ORDER_HEAD_POST_MPG", objServiceArgs);
						logger.info (" orderHeadPostId:: "+orderHeadPostId);
						hrPayOrderHeadPostMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
						hrPayOrderHeadPostMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
						hrPayOrderHeadPostMpg.setCreatedDate(sysdate);	
						hrPayOrderHeadPostMpg.setOrderHeadPostId(orderHeadPostId);						
						ohpMpgDAO.create(hrPayOrderHeadPostMpg);//ready to insert
					}

				}
				logger.info("Update psr post mapping Info*********** ");
				HrPayPsrPostMpg psrPostMpg = new HrPayPsrPostMpg();
				//added by manoj for psr relatd issue for home
				psrId=1;
				long psrPostId=0;
				List psrList = psrPostMpgDAOImpl.getMaxPsrForLoc(locId);
				logger.info("psrList be added is "+psrList);
				if(psrList!=null && psrList.size()>0)
				{
					Object[] row = (Object[])psrList.get(0); 
					logger.info("row added is "+row.length);
					if(row[0]!=null)
						psrId = (Long)row[0];  	        
				}
				logger.info("psr no to be added is "+psrId);
				String[] cols = new String[]{"postId","loc_id"};
				Object[] vals = null;
				logger.info("newPostId::"+newPostId);
				logger.info("postIdForPsr::"+postIdForPsr);
				if(newPostId!=0)
					vals = new Object[]{newPostId,locId};				
				else
					vals = new Object[]{postIdForPsr,locId};				
				List hrPayPsrPostMpgList = null;
				hrPayPsrPostMpgList = psrPostMpgDAOImpl.getListByColumnAndValue(cols, vals);
				if(hrPayPsrPostMpgList!=null && hrPayPsrPostMpgList.size()>0)
				{
					hrPayPsrPostMpg = (HrPayPsrPostMpg)hrPayPsrPostMpgList.get(0);
					hrPayPsrPostMpg.setBillNo(billNo);
					psrPostMpgDAOImpl.update(hrPayPsrPostMpg);
				}
				else
				{
					psrPostId = idGen.PKGenerator("HR_PAY_POST_PSR_MPG", objServiceArgs);
					hrPayPsrPostMpg.setPsrId(psrId);//psr no
					hrPayPsrPostMpg.setBillNo(billNo);
					hrPayPsrPostMpg.setLoc_id(locId);
					hrPayPsrPostMpg.setPostId(hrPayOrderHeadPostMpg.getPostId());
					hrPayPsrPostMpg.setPsrPostId(psrPostId);//pk
					psrPostMpgDAOImpl.create(hrPayPsrPostMpg);
					psrId++;
				}
				logger.info("changePostCheck 3:::"+changePostCheck);
				logger.info("prevPostEndDate 3:::"+prevPostEndDate);
				logger.info("actionFlag 3:::"+actionFlag);				
				// Update info related to Order head post mapping ends

				// Updating Order details Starts				
				HrPayOrderMst hrPayOrderMstVo = (HrPayOrderMst)objServiceArgs.get("hrPayOrderMstVo");
				HrPayOrderMst orderMstVO = new HrPayOrderMst();
				long orderMstId = (Long)objServiceArgs.get("lOrderId");//Getting order id which will be going to update
				logger.info("order id to be update is ::"+orderMstId);
				OrderMstDAOImpl ordermstDAOImpl = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
				orderMstVO = ordermstDAOImpl.read(orderMstId);								
				orderMstVO.setOrderName(hrPayOrderMstVo.getOrderName());	  	        
				orderMstVO.setOrderDate(hrPayOrderMstVo.getOrderDate());
				orderMstVO.setEndDate(hrPayOrderMstVo.getEndDate());	  	        
				ordermstDAOImpl.update(orderMstVO);				
				// Updating Order details ends 
				
				//To set viewEmployee List page after insertion or updation
		

				//added by manish khunt
    			Map mapForChangedRecords= objServiceArgs;
    			logger.info("Setting map to objectArgs");
    			mapForChangedRecords.put("changedPostId",orgPostMst.getPostId());
    			mapForChangedRecords.put("serviceLocator",serv);
    			mapForChangedRecords.put("OrgPostMst",orgPostMst);
    			mapForChangedRecords.put("OrgUserMst",orgUserMst);
    			mapForChangedRecords.put("locId",locId);
    			mapForChangedRecords.put("changedEmpId",objServiceArgs.get("empId"));
    			logger.info("empId:::::::::::::::"+objServiceArgs.get("empId"));
    			mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
    			GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
    			long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
    			logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
    			logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
    		    //ended by manish khunt
				
				objServiceArgs.put("MESSAGECODE",300006);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("newEmployeeConfig");
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in updateNewEmployeeConfigDtls service******");
			logger.error("Error In updateNewEmployeeConfigDtls service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}

		logger.info("updateNewEmployeeConfigDtls ended***********************");
		return resultObject;
	}

	
	public ResultObject getOrderDetails(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside getOrderDetails*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				Map voToService = (Map)objServiceArgs.get("voToServiceMap");					
				long lOrderId=0;		
				String orderName= "";
				String orderStartDate= "";				
				String orderEndDate= "";
				StringBuffer propertyList = new StringBuffer();
				ArrayList dtlsList = new ArrayList();
				SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat parseDate =new SimpleDateFormat("yyyy-MM-dd");
				Object[] objDtlsLst = null; 
				String billSubHeadId = "";
				long lOrderHeadId = 0;
				if(voToService.get("lOrderHeadId")!=null)
					lOrderHeadId=Long.parseLong(voToService.get("lOrderHeadId").toString());
				dtlsList = newEmpConfigDAO.getOrderDetails(lOrderHeadId, locId);
				logger.info("dtlsList size in getOrderNameList service is:::::::::"+dtlsList.size());
				HrPayOrderMst hrPayOrderMst;
				List<HrPayOrderHeadCustomMpg> sanctOrderList = new ArrayList();		
				Date sDate = new Date();
				Date eDate = new Date();
				if(dtlsList!=null && dtlsList.size()!=0)
				{
					for (int i=0;i<dtlsList.size();i++)
					{						
						HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
						objDtlsLst = (Object[]) dtlsList.get(i);
						lOrderId = objDtlsLst[0]!= null ? Long.parseLong(objDtlsLst[0].toString()) : Long.parseLong("0");						
						orderName = objDtlsLst[1]!= null ? objDtlsLst[1].toString() : "";												
						orderStartDate = objDtlsLst[2]!= null ? objDtlsLst[2].toString() : "";
						orderEndDate = objDtlsLst[3]!= null ? objDtlsLst[3].toString() : "";
						if(!orderStartDate.equals(""))
						{							
							sDate = parseDate.parse(orderStartDate);
							orderStartDate = fmt.format(sDate);
						}	
						if(!orderEndDate.equals(""))
						{							
							eDate = parseDate.parse(orderEndDate);
							orderEndDate = fmt.format(eDate);
						}						
						propertyList.append("<orderDetails>");   	
						propertyList.append("<orderId>").append("<![CDATA[").append(lOrderId).append("]]>").append("</orderId>");
						propertyList.append("<orderName>").append("<![CDATA[").append(orderName).append("]]>").append("</orderName>"); 
						propertyList.append("<orderStartDate>").append("<![CDATA[").append(orderStartDate).append("]]>").append("</orderStartDate>");
						propertyList.append("<orderEndDate>").append("<![CDATA[").append(orderEndDate).append("]]>").append("</orderEndDate>");
						propertyList.append("</orderDetails>");
						
					}
				}
				logger.info("propertyList::"+propertyList);
				
				String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
				objServiceArgs.put("ajaxKey", stateNameIdStr);

				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("ajaxData");        
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getOrderDetails service******");
			logger.error("Error In getOrderDetails service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}
		logger.info("getOrderDetails ended***********************");
		return resultObject;
	}
	
	
	
/*	public ResultObject uploadNewEmployeeDataByExcel(Map objectArgs)
	{		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		logger.info("inside Update uploadNewEmployeeDataByExcel------------>");
		logger.info("inside Update uploadNewEmployeeDataByExcel------------>");
		Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			
			
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
				EmpDAO orgEmpDAO = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
				
				long dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());				
				CmnDatabaseMst cmnDatabaseMst= new CmnDatabaseMst();
				cmnDatabaseMst.setDbId(dbId);
				
				CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
				
				//Getting code for created By
				long loggedInUser = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);

				 Get The Person Post 
				long loggedInpostId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
				OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);
				

				CmnLookupMst proofLookupId = new CmnLookupMst();
				CmnLookupMstDAO cmnLookupDao = new CmnLookupMstDAOImpl(	CmnLookupMst.class, serv.getSessionFactory());
				
				
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				static
				{
					ResourceBundle constResourceBundle = ResourceBundle.getBundle("resources.Payroll");
				 String sdrFilePath = constResourceBundle.getString("EMPLSTEXL.scanPath");
				}	
			
			ResourceBundle rsPayroll = ResourceBundle.getBundle("resources.Payroll");
			String rs = rsPayroll.getString("EMPLSTEXL.scanPath");
			rs.getBytes();
			logger.info("bytes >>>>>>>>>>>> " + rs.getBytes());
			String fileName="";
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			ArrayList attachmentList = (ArrayList)objectArgs.get("attachmentList");
			FileItem fileItem = null;
			ReadExcelFile excelData = new ReadExcelFile();
			Workbook workBook; 
			if(attachmentList!=null && attachmentList.size()>0)
	        {
				logger.info("attachment list is not zero"+attachmentList.size());
	        	Iterator attachmentListIterator = attachmentList.listIterator();
	        	while (attachmentListIterator.hasNext())
	            {
        		fileItem = (FileItem) attachmentListIterator.next();
                logger.info("details here "+fileItem.get());
                workBook = Workbook.getWorkbook(fileItem.getInputStream());
                logger.info("iterating while loop.....check workbook and file iterms "+ workBook.getNumberOfSheets());
                Sheet sheet = workBook.getSheet(0);
				for(int x=1;x<sheet.getRows();x++)
				{
					List EmployeeDetails = new ArrayList();
					Cell salutation = sheet.getCell(0,x); 
					Cell firstName = sheet.getCell(1,x);
					Cell lastName = sheet.getCell(2,x);
					Cell className = sheet.getCell(3,x);
					Cell dateofBirth = sheet.getCell(4,x);
					Cell dateofJoining = sheet.getCell(5,x);
					Cell employeeType = sheet.getCell(6,x);
					Cell bankName = sheet.getCell(7,x);
					Cell branchName = sheet.getCell(8,x);
					Cell accountType = sheet.getCell(9,x);
					Cell startDate = sheet.getCell(10,x);
					Cell designation = sheet.getCell(11,x);
					Cell psrNo = sheet.getCell(12,x);
					Cell postShortName = sheet.getCell(13,x);
					Cell postName = sheet.getCell(14,x);
					Cell branch = sheet.getCell(15,x);
					Cell startDateofEmployeePostMapping = sheet.getCell(16,x);
					Cell billNo = sheet.getCell(17,x);
					Cell acctNo = sheet.getCell(18,x);
					Cell OfficeName = sheet.getCell(19,x);
					Cell GPFActNO = sheet.getCell(20,x);
					Cell Sanctionorderno = sheet.getCell(21,x);
					Cell orderdate = sheet.getCell(22,x);
					Cell orderenddate = sheet.getCell(23,x);
					Cell retirementdate = sheet.getCell(24,x);
					
					//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
					String Salutation = salutation.getContents(); 
					String FirstName = firstName.getContents();
					String LastName = lastName.getContents();
					String ClassName = className.getContents();
					String DateofBirth = dateofBirth.getContents();
					logger.info("uploadNewEmployeeDataByExcel DateofBirth " + DateofBirth);
					String DateofJoining = dateofJoining.getContents();
					logger.info("uploadNewEmployeeDataByExcel DateofJoining " + DateofJoining);
					Date DOB = StringUtility.convertStringToDate(DateofBirth);
					Date DOJ = StringUtility.convertStringToDate(DateofJoining);
					String EmployeeType =employeeType.getContents();		
					
					HrEisBankDtls bankDtlsVO = new HrEisBankDtls();
					String BankName = bankName.getContents();
					String BranchName = branchName.getContents();
					String AccountType = accountType.getContents();
					String AccountNumber = acctNo.getContents();
					String officeName = OfficeName.getContents();
					String gpfAcNumber = GPFActNO.getContents();
					String sanctionOrderNo = Sanctionorderno.getContents();
					String orderDate = orderdate.getContents();
					String orderendDate = orderenddate.getContents();
					String Retirementdate = retirementdate.getContents();
					String StartDate = startDate.getContents();
					logger.info("uploadNewEmployeeDataByExcel StartDate " + StartDate);
					//String SD = sdf.format(StartDate);
					Date SD =sdf.parse(StartDate);
					Date OD = sdf.parse(orderDate);
					Date ED = sdf.parse(orderendDate);
					Date RD = sdf.parse(Retirementdate);
					String ssd =StartDate.substring(0,10);
				String ssd1=	ssd.replace(' ', '/');
					logger.info("uploadNewEmployeeDataByExcel ssd" + ssd);
					Date SD = StringUtility.convertStringToDate(ssd1);
					logger.info("uploadNewEmployeeDataByExcel ssd1 " + ssd1);
					Date OD = StringUtility.convertStringToDate(orderDate);
					Date ED = StringUtility.convertStringToDate(orderendDate);
					Date RD = StringUtility.convertStringToDate(Retirementdate);
					
					bankDtlsVO.setBankAcctStartDt(SD);
					String Designation = designation.getContents();
					String PsrNo = psrNo.getContents();
					String PostShortName = postShortName.getContents();
					String PostName = postName.getContents();
					String Branch = branch.getContents();
					String startDateofEmployeePostMap = startDateofEmployeePostMapping.getContents();
					Date StartDateofEmployeePostMapping = StringUtility.convertStringToDate(startDateofEmployeePostMap);
					String BillNo = billNo.getContents();
					long billno = Long.parseLong(BillNo);
					logger.info("uploadNewEmployeeDataByExcel FirstName " + FirstName);
					logger.info("uploadNewEmployeeDataByExcel LastName " + LastName);
					logger.info("uploadNewEmployeeDataByExcel ClassName " + ClassName);
					logger.info("uploadNewEmployeeDataByExcel DateofBirth " + DOB);
					logger.info("uploadNewEmployeeDataByExcel DateofJoining " + DOJ);
					logger.info("uploadNewEmployeeDataByExcel EmployeeType " + EmployeeType);
					logger.info("uploadNewEmployeeDataByExcel StartDate " + SD);
					logger.info("uploadNewEmployeeDataByExcel AccountType " + AccountType);
					logger.info("uploadNewEmployeeDataByExcel BankName " + BankName);
					logger.info("uploadNewEmployeeDataByExcel BranchName " + BranchName);
					logger.info("uploadNewEmployeeDataByExcel Designation " + Designation);
					logger.info("uploadNewEmployeeDataByExcel PsrNo " + PsrNo);
					logger.info("uploadNewEmployeeDataByExcel PostShortName " + PostShortName);
					logger.info("uploadNewEmployeeDataByExcel PostName " + PostName);
					logger.info("uploadNewEmployeeDataByExcel Branch " + Branch);
					logger.info("uploadNewEmployeeDataByExcel StartDateofEmployeePostMapping " + StartDateofEmployeePostMapping);
					logger.info("uploadNewEmployeeDataByExcel billno " + billno);
					logger.info("uploadNewEmployeeDataByExcel officeName " + officeName);
					logger.info("uploadNewEmployeeDataByExcel gpfAcNumber " + gpfAcNumber);
					logger.info("uploadNewEmployeeDataByExcel sanctionOrderNo " + sanctionOrderNo);
					logger.info("uploadNewEmployeeDataByExcel orderDate " + OD);
					logger.info("uploadNewEmployeeDataByExcel orderendDate " + ED);
					logger.info("uploadNewEmployeeDataByExcel Retirementdate " + RD);
					//Setting OrgUserMst
					CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());					
					CmnLookupMst cmnLookupMst=cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("ACTIVE STATUS", 1);
					SimpleDateFormat Dateformat=new SimpleDateFormat("ddMMMyyyy");
					String BirthDate=Dateformat.format(DOB);
					String userName = FirstName.substring(0,1)+LastName+BirthDate;
					if(userName.length()>18)
						userName= userName.substring(0, 18);
					userName = userName.replaceAll("/", "");
					String PassWord="a";
					String firstLogin = "N";
					long userrId=IDGenerateDelegate.getNextId("org_user_mst", objectArgs);
                    OrgUserMst orgUserMst = new OrgUserMst();
					logger.info("uploadNewEmployeeDataByExcel userrId " + userrId);
					orgUserMst.setUserId(userrId);
					orgUserMst.setUserName(userName);
					orgUserMst.setPassword(PassWord);
					orgUserMst.setActivateFlag(1);			
					orgUserMst.setStartDate(DOJ);			
					orgUserMst.setCreatedDate(new Date());
					orgUserMst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					orgUserMst.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);			
					orgUserMst.setFirstlogin(firstLogin);
					orgUserMst.setCmnLookupMst(cmnLookupMst);
					orgUserMst.setSecretAnswer(userName);
					orgUserMst.setPwdchangedDate(new Date());
					orgUserMst.setSecretQueCode("Secret_Other");
					orgUserMst.setSecretQueOther("Enter your Username");
					orgUserMstDaoImpl.create(orgUserMst);
					objectArgs.put("orgUserMstFROMExcel",orgUserMst);
					ResultObject r2 = serv.executeService("insertNewEmployeeConfigDtls", objectArgs);
					
					//end Orgusermst
					
					
					
					//Setting OrgEmpMst
					OrgGradeMstDao gradeMstDao = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
					List<OrgGradeMst> gradeIdList =gradeMstDao.getListByColumnAndValue("gradeName",ClassName);
					logger.info("uploadNewEmployeeDataByExcel gradeIdList size"+gradeIdList.size());
					logger.info("uploadNewEmployeeDataByExcel gradeIdList" + gradeIdList.get(0).getGradeId());
					long gradeId = 0;
					if( gradeIdList!=null &&  gradeIdList.size()>0)
					  gradeId = gradeIdList.get(0).getGradeId();
					OrgGradeMst orggrademst = new OrgGradeMst();
					if(gradeId!=0)
					 orggrademst.setGradeId(gradeId);
					OrgEmpMst orgEmpMstEng = new OrgEmpMst();
					OrgEmpMst orgEmpMstGuj = new OrgEmpMst();
					
					//Getting a Lang Id Code for England
					long langIdEng=1;
					CmnLanguageMst cmnLangMstEng = new CmnLanguageMst();
					cmnLangMstEng.setLangId(langIdEng);

					//Getting a Lang Id Code for Gujarati
					long langIdGuj=2;
					CmnLanguageMst cmnLangMstGuj = new CmnLanguageMst();
					cmnLangMstGuj.setLangId(langIdGuj);
					logger.info("uploadNewEmployeeDataByExcel gradeId " + gradeId);
					long empId = IDGenerateDelegate.getNextId("org_emp_mst", objectArgs);
					
					orgEmpMstEng.setEmpId(empId);
					orgEmpMstEng.setEmpPrefix(Salutation);
					orgEmpMstEng.setEmpFname(FirstName);
					orgEmpMstEng.setEmpLname(LastName);
					orgEmpMstEng.setOrgGradeMst(orggrademst);
					orgEmpMstEng.setOrgUserMst(orgUserMst);			
					orgEmpMstEng.setEmpDob(DOB);
					orgEmpMstEng.setEmpDoj(DOJ);
					orgEmpMstEng.setStartDate(SD);
					orgEmpMstEng.setActivateFlag(1);
					orgEmpMstEng.setCmnLanguageMst(cmnLangMstEng);
					orgEmpMstEng.setEmpSrvcFlag(1);
					orgEmpMstEng.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
					orgEmpMstEng.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					orgEmpMstEng.setCreatedDate(new Date());
					orgEmpMstEng.setEmpSrvcExp(RD);
					orgEmpDAO.create(orgEmpMstEng);
					
					orgEmpMstGuj.setEmpId(empId);
					logger.info("uploadNewEmployeeDataByExcel empId " + empId);
					orgEmpMstGuj.setEmpPrefix(Salutation);
					orgEmpMstGuj.setEmpFname(FirstName);
					orgEmpMstGuj.setEmpLname(LastName);	
					orgEmpMstGuj.setOrgGradeMst(orggrademst);
					orgEmpMstGuj.setOrgUserMst(orgUserMst);
					orgEmpMstGuj.setEmpDob(DOB);
					orgEmpMstGuj.setEmpDoj(DOJ);
					orgEmpMstGuj.setStartDate(SD);
					orgEmpMstGuj.setActivateFlag(1);
					orgEmpMstEng.setCmnLanguageMst(cmnLangMstGuj);
					orgEmpMstGuj.setEmpSrvcFlag(1);
					orgEmpMstGuj.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
					orgEmpMstGuj.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					orgEmpMstGuj.setCreatedDate(new Date());
					orgEmpDAO.create(orgEmpMstGuj);
					
					logger.info("uploadNewEmployeeDataByExcel OrgEmpMst empId " + empId);
					//ending orgempmst
					
					
					
					//Setting orgUserpostRlt
					
					long empPostId = IDGenerateDelegate.getNextId("org_userpost_rlt", objectArgs);
					logger.info("uploadNewEmployeeDataByExcel empPostId " + empPostId);
					UserPostDao userPostDao  = new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());	
					OrgPostDetailsRlt orgPostDetailsRlt = new OrgPostDetailsRlt();
					OrgPostDetailsRltDaoImpl orgPostDetailsRltDaoImpl = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
					List<OrgPostDetailsRlt> postList = orgPostDetailsRltDaoImpl.getListByColumnAndValue("postName",PostName);
					logger.info("uploadNewEmployeeDataByExcel postList " + postList.size());
					cmnLookupMst = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("Primary_Post",langId);
					long postId = postList.get(0).getOrgPostMst().getPostId();
					
					
					logger.info("uploadNewEmployeeDataByExcel postId" + postId);
					
					
					EmployeeTypeDAOImpl CmnlookupMstDAOImpl = new EmployeeTypeDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
					
					int X=1;
					List VacantPostList= CmnlookupMstDAOImpl.getVacantPostList(locId);
					logger.info("uploadNewEmployeeDataByExcel VacantPostList" + VacantPostList.size());
					for(int i=0; i<VacantPostList.size(); i++)
					{
					Object[] row = ((Object[])VacantPostList.get(i));
					long PostId =Long.parseLong(row[1].toString());
					if(PostId==postId)
					{
						logger.info("uploadNewEmployeeDataByExcel PostId11" + PostId);
						logger.info("uploadNewEmployeeDataByExcel postId22" + postId);
						objectArgs.put("msg", "Please dont enter existing Post name ");
						X=2;
					}
					}
				    OrgPostMst orgPostMst = postList.get(0).getOrgPostMst();
					OrgUserpostRlt orgUserpostRlt = new OrgUserpostRlt();
					orgUserpostRlt.setEmpPostId(empPostId);
					orgUserpostRlt.setOrgUserMst(orgUserMst);
					orgUserpostRlt.setOrgPostMstByPostId(orgPostMst);
					orgUserpostRlt.setStartDate(StartDateofEmployeePostMapping);
					orgUserpostRlt.setActivateFlag(1);
					orgUserpostRlt.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					orgUserpostRlt.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
					orgUserpostRlt.setCreatedDate(sysdate);
					orgUserpostRlt.setCmnLookupUserPostType(cmnLookupMst);
					//orgUserpostRlt.setActivateFlag(1);
					userPostDao.create(orgUserpostRlt);
					logger.info("uploadNewEmployeeDataByExcel  orgUserpostRlt empPostId " + empPostId);
					//ending orguserpostrlt
					
					
					
					//Setting HrEisProofDtl
					GenericDaoHibernateImpl generateDao = new GenericDaoHibernateImpl(HrEisProofDtl.class);
					
					generateDao.setSessionFactory(serv.getSessionFactory());
					HrEisProofDtl hrEisProofDtl = new HrEisProofDtl();
					long srNO=IDGenerateDelegate.getNextId("hr_eis_proof_dtl", objectArgs);
					
					long proofId = 300166;
					String Proof="";
					proofLookupId = cmnLookupDao.read(proofId);
					hrEisProofDtl.setSrNo(srNO);
					hrEisProofDtl.setOrgPostMstByUserId(orgUserMst);
					hrEisProofDtl.setCmnDatabaseMst(cmnDatabaseMst);
					hrEisProofDtl.setCmnLocationMst(cmnLocationMst);
					hrEisProofDtl.setCmnLookupMst(proofLookupId);
					hrEisProofDtl.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					hrEisProofDtl.setCreatedDate(sysdate);
					hrEisProofDtl.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
					hrEisProofDtl.setProofNum(Proof);
					generateDao.create(hrEisProofDtl);
					logger.info("uploadNewEmployeeDataByExcel HrEisProofDtl srNO " + srNO);
					//end hreisproofdtls
					
					
					
					// Saving Details of employee regarding GPF Details, PAN No etc.
					EmpDAOImpl orgEmpDAOImpl = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
					logger.info(" inserting data related to emp master and gpf details edit flag is not Y-------------");
					
					//Setting and inserting hr_eis_emp_mst vo
					
					CmnLookupMstDAO cmnLookupMstDao = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
					List<CmnLookupMst> empTypeList = cmnLookupMstDao.getListByColumnAndValue("lookupName", EmployeeType);
					logger.info(" uploadNewEmployeeDataByExcel empTypeList-------------" +empTypeList.size());
					CmnLookupMst cmnLookupMST =empTypeList.get(0);
					long empTypeId = empTypeList.get(0).getLookupId();
					logger.info(" uploadNewEmployeeDataByExcel CmnLookupMst empTypeId-------------" +empTypeId);
					
					// correct code
					EmployeeTypeDAOImpl cmnlookupMstDAOImpl = new EmployeeTypeDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
					List<CmnLookupMst> empTypeList = cmnlookupMstDAOImpl.getAllEmployyTypeData(EmployeeType);
					CmnLookupMst cmnlookupMst =empTypeList.get(0);
					long EmployeeTypeId = empTypeList.get(0).getLookupId();
					logger.info("uploadNewEmployeeDataByExcel CmnLookupMst EmployeeTypeId " + EmployeeTypeId);
					logger.info("uploadNewEmployeeDataByExcel CmnLookupMst EmployeeTypeId " + EmployeeTypeId);
					
					HrEisEmpMst empMstEng = new HrEisEmpMst();					
					long eisEmpMstId=IDGenerateDelegate.getNextId("hr_eis_emp_mst", objectArgs);
					empMstEng.setEmpId(eisEmpMstId);
					EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
					long employeeId = orgEmpMstEng.getEmpId();					
					logger.info("employeeId in new emp config service: "+ employeeId);
					OrgEmpMst lOrgEmpMst = new OrgEmpMst();
					lOrgEmpMst = orgEmpDAOImpl.read(employeeId);
					empMstEng.setOrgEmpMst(orgEmpMstEng);					
					empMstEng.setCmnDatabaseMst(cmnDatabaseMst);
					empMstEng.setCmnLocationMst(cmnLocationMst);
					empMstEng.setOrgUserMstByCreatedBy(loggedInOrgUserMst);				
					empMstEng.setCreatedDate(sysdate);
					empMstEng.setTrnCounter(new Integer(1));
					empMstEng.setEmpType(EmployeeTypeId);
					//empMstEng.setEmpType();
					//empinfodao.create(empMstEng);
					
					logger.info("uploadNewEmployeeDataByExcel HrEisEmpMst eisEmpMstId " + eisEmpMstId);
					// ending hreisempmst
					
					// Setting and inserting HrGpfBalanceDtls VO 
					HrPayGpfBalanceDtls hrGpfBalanceDtls = new HrPayGpfBalanceDtls();
					GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(HrPayGpfBalanceDtls.class);
					genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
					long userID = empMstEng.getOrgEmpMst().getOrgUserMst().getUserId();
					
					logger.info("uploadNewEmployeeDataByExcel hrGpfBalanceDtls userID " +userID);
					
					EmployeeTypeDAOImpl employeeTypeDAOImpl = new EmployeeTypeDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
					
						int Y=1;
					List<HrPayGpfBalanceDtls> psrNoList = employeeTypeDAOImpl.getPsrNoList(locId);
					for(int i=0; i<psrNoList.size() ; i++)
					{
					 logger.info("uploadNewEmployeeDataByExcel hrGpfBalanceDtls psrNoList " +psrNoList.size());
					 
					 String GpfAcNumber = psrNoList.get(i).getGpfAccNo();
					 if(GpfAcNumber==gpfAcNumber)
					 {
						 objectArgs.put("msg", "Please dont enter existing gpfAcNumber ");
						 Y=2;
					 }
					}
					//hrGpfBalanceDtls.setUserId(orgUserMst.getUserId());
					hrGpfBalanceDtls.setUserId(userID);
					hrGpfBalanceDtls.setGpfAccNo(gpfAcNumber);
					hrGpfBalanceDtls.setTrnCounter(1);
					hrGpfBalanceDtls.setCmnDatabaseMst(cmnDatabaseMst);
					hrGpfBalanceDtls.setCmnLocationMst(cmnLocationMst);
					hrGpfBalanceDtls.setOrgGradeMst(orggrademst);
					hrGpfBalanceDtls.setCreatedDate(sysdate);
					hrGpfBalanceDtls.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
					hrGpfBalanceDtls.setOrgUserMstByCreatedByUser(loggedInOrgUserMst);
					genericDaoHibernateImpl.create(hrGpfBalanceDtls);	
					
					logger.info("uploadNewEmployeeDataByExcel hrGpfBalanceDtls userid " + hrGpfBalanceDtls.getUserId());
					logger.info("uploadNewEmployeeDataByExcel hrGpfBalanceDtls gradeId " + hrGpfBalanceDtls.getGpfAccNo());
					//ending hrgpfbalancedtls
					
					
					// Saving Bank Dtls
					logger.info("Saving bank Info*********** ");	
					BankMasterDAOImpl bankMasterDAO = new BankMasterDAOImpl(HrEisBankMst.class,serv.getSessionFactory());
					List<HrEisBankMst> BankList =bankMasterDAO.getListByColumnAndValue("bankName",BankName);
					logger.info("uploadNewEmployeeDataByExcel BankList "+ BankList.size());
					HrEisBankMst hrEisBankMst = BankList.get(0);
					long bankId = BankList.get(0).getBankId();
					logger.info("uploadNewEmployeeDataByExcel bankId " + bankId);
					BranchMasterDAOImpl branchMasterDAO =new BranchMasterDAOImpl(HrEisBranchMst.class,serv.getSessionFactory());
					List<HrEisBranchMst> BranchList =branchMasterDAO.getListByColumnAndValue("branchName",BranchName);
					logger.info("uploadNewEmployeeDataByExcel BranchList "+ BranchList.size());
					HrEisBranchMst hrEisBranchMst = BranchList.get(0);
					long branchId =BranchList.get(0).getBranchId();
					logger.info("uploadNewEmployeeDataByExcel branchId "+ branchId);
					HrEisBankDtls bankDtls = new HrEisBankDtls();
					BankDetailDAOImpl bankDetailDAO = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
					CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					List accTypes = lookupDAO.getAllChildrenByLookUpNameAndLang("Account Type", langId);
					long bankAcctType = 0;
					CmnLookupMst cmnNLookupMst = new CmnLookupMst();
					for(Iterator itr=accTypes.iterator();itr.hasNext();)
					{
						cmnNLookupMst=(CmnLookupMst) itr.next();
						if((cmnNLookupMst.getLookupName()).equals(AccountType))
						{
							bankDtls.setBankAcctType(cmnNLookupMst.getLookupId());
						}

					}
					logger.info("uploadNewEmployeeDataByExcel accountTypeList " + accTypes.size());
					long bankDtlsId =IDGenerateDelegate.getNextId("hr_eis_bank_dtls", objectArgs);
					
					
					int xx=1;
					List<HrEisBankDtls> bankAccNoList = employeeTypeDAOImpl.getBankAccountNoList();
					logger.info("uploadNewEmployeeDataByExcel bankAccNoList " + bankAccNoList.size());
					for(int i=0; i<bankAccNoList.size(); i++)
					{
						String bankAccotNo= bankAccNoList.get(i).getBankAcctNo();
			
						if(bankAccotNo==bankAccotNo)
						{
							objectArgs.put("msg", "Please dont enter existing bankAccotNo ");
							xx=2;
						}
					}
					
					
					bankDtls.setBankDtlId(bankDtlsId);
					bankDtls.setCmnDatabaseMst(cmnDatabaseMst);
					bankDtls.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
					bankDtls.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
					bankDtls.setHrEisEmpMst(empMstEng);
					bankDtls.setHrEisBankMst(hrEisBankMst);
					bankDtls.setHrEisBranchMst(hrEisBranchMst);
					bankDtls.setBankAcctNo(AccountNumber);
					bankDtls.setBankAcctStartDt(SD);
					bankDtls.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
					bankDtls.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					bankDtls.setCreatedDate(sysdate);
					logger.info("Before going to create bank object");
					logger.info("uploadNewEmployeeDataByExcel HrEisBankDtls bankDtlsId " + bankDtlsId);
					bankDetailDAO.create(bankDtls);
					logger.info("Before going to create emp object");
					serv.getSessionFactory().getCurrentSession().saveOrUpdate(empMstEng);

					
				//end bank details
					
					
					// setting for org_post_details_rlt
				
					OrgDesignationMstDaoImpl orgDesignationMstDaoImpl = new OrgDesignationMstDaoImpl(OrgDesignationMst.class,serv.getSessionFactory());
					//List dsgnList = orgDesignationMstDaoImpl.getDsgnListByLangAndDsgntName(langId, Designation);
					List<OrgDesignationMst> desigList = orgDesignationMstDaoImpl.getListByColumnAndValue("dsgnName",Designation);
					logger.info("uploadNewEmployeeDataByExcel desigList size " +desigList.size());
					long desigId = desigList.get(0).getDsgnId();
					logger.info("uploadNewEmployeeDataByExcel desigId" + desigId);
					OrgDesignationMst orgDesignationMst = desigList.get(0);
					OrgPostMstDaoImpl orgpPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
					
					
				long postDtlsId = IDGenerateDelegate.getNextId("org_post_details_rlt", objectArgs);
				
				
				
				CmnBranchMstDaoImpl cmnBranchMstDaoImpl = new CmnBranchMstDaoImpl(CmnBranchMst.class,serv.getSessionFactory());
				List<CmnBranchMst> branchList = cmnBranchMstDaoImpl.getListByColumnAndValue("branchName",Branch);
				logger.info("uploadNewEmployeeDataByExcel branchList" + branchList.size());
				long BranchId = branchList.get(0).getBranchId();
				CmnBranchMst cmnBranchMst = branchList.get(0);
				//cmnBranchMst.setBranchId(BranchId);
				
				 Temporary comment
				orgPostDetailsRlt.setPostDetailId(postDtlsId);
				orgPostDetailsRlt.setPostName(PostName);
				orgPostDetailsRlt.setPostShortName(PostShortName);
				orgPostDetailsRlt.setCmnLanguageMst(cmnLangMstEng);
				//orgPostDetailsRlt.setCmnLanguageMst(cmnLangMstGuj);
				orgPostDetailsRlt.setCmnLocationMst(cmnLocationMst);
				orgPostDetailsRlt.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				orgPostDetailsRlt.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				orgPostDetailsRlt.setCreatedDate(sysdate);
				orgPostDetailsRlt.setOrgDesignationMst(orgDesignationMst);
				orgPostDetailsRlt.setOrgPostMst(orgPostMst);
				orgPostDetailsRlt.setCmnBranchMst(cmnBranchMst);
				orgPostDetailsRltDaoImpl.create(orgPostDetailsRlt);
				logger.info("uploadNewEmployeeDataByExcel orgPostDetailsRlt postDtlsId " + postDtlsId);
					
				//ending
				// setting for orgpostmst
				
				OrgPostMst orgpostmst = new OrgPostMst();
				orgpostmst.setPostId(postId);
				logger.info("uploadNewEmployeeDataByExcel orgPostDetailsRlt postDtlsId " + );
			//	orgpostmst.setOrgPostDetailsRlt(orgPostMst.getPostId());
				orgpostmst.setCmnLookupMst(cmnLookupMst);
				orgpostmst.setActivateFlag(1);
				orgpostmst.setCreatedDate(sysdate);
				orgpostmst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				orgpostmst.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				orgpostmst.setDsgnCode(orgDesignationMst.getDsgnCode());
				orgpostmst.setLocationCode(cmnLocationMst.getLocationCode());
				orgpostmst.setParentPostId(orgPostMst.getParentPostId());
				orgpostmst.setPostLevelId(orgPostMst.getPostLevelId());
				
				// ending for orgpostmst
				
				// setting for hr_pay_post_psr_mpg
				
				HrPayBillHeadMpg hrBillHeadMpg = new HrPayBillHeadMpg();
				BillHeadMpgDAOImpl billHeadMpgDAOImpl = new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class,serv.getSessionFactory());
				List<HrPayBillHeadMpg> billNoList = billHeadMpgDAOImpl.getListByColumnAndValue("billId", billno);
				logger.info("uploadNewEmployeeDataByExcel HrPayBillHeadMpg billNoList " + billNoList.size());
				long BillId = billNoList.get(0).getBillHeadId();
				logger.info("uploadNewEmployeeDataByExcel HrPayBillHeadMpg BillId " + BillId);
				//HrPayBillHeadMpg hrBillHeadMpg = billNoList.get(0);
		 	    HrPayPsrPostMpg hrPayPsrPostMpg = new HrPayPsrPostMpg();
				PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class, serv.getSessionFactory());
				
				int Z=1;
				List<HrPayPsrPostMpg> psrList= psrPostMpgDAOImpl.getListByColumnAndValue("loc_id", locId);
				logger.info("uploadNewEmployeeDataByExcel psrList psrList " + psrList.size());
				for(int i=0; i<psrList.size(); i++)
				{
					long Psrno = psrList.get(i).getPsrId();
			
				if(Psrno==(Long.parseLong(PsrNo)));
				{
					objectArgs.put("msg", "Please dont enter existing PSR Number ");
					Z=2;
				}
				}
				
				int z=1;
				List<HrPayBillHeadMpg> BillNoList = billHeadMpgDAOImpl.getAllData();
				for(int i=0; i<BillNoList.size(); i++)
				{
					long billId=BillNoList.get(i).getBillId();
					if(billId!=BillId)
					{
						objectArgs.put("msg", "Please enter existing BILL Number ");
						z=2;
					}
				}
				
				long Id = IDGenerateDelegate.getNextId("hr_pay_post_psr_mpg", objectArgs);
				hrPayPsrPostMpg.setPostId(postId);
				hrPayPsrPostMpg.setPsrId(Long.parseLong(PsrNo));
				hrPayPsrPostMpg.setLoc_id(Long.parseLong(cmnLocationMst.getLocationCode()));
				hrPayPsrPostMpg.setPsrPostId(Id);
				hrPayPsrPostMpg.setBillNo(BillId);				
				//psrPostMpgDAOImpl.create(hrPayPsrPostMpg);
				serv.getSessionFactory().getCurrentSession().saveOrUpdate(hrPayPsrPostMpg);
				logger.info("uploadNewEmployeeDataByExcel HrPayPsrPostMpg Id " + Id);
				//ending for hr_pay_post_psr_mpg
				
				

				//starting for hr_pay_bill_subhead_mpg
				long billSubheadId = IDGenerateDelegate.getNextId("hr_pay_bill_subhead_mpg",objectArgs);
				hrBillHeadMpg.setBillHeadId(billSubheadId);
				hrBillHeadMpg.setBillId(Long.parseLong(BillNo));
				hrBillHeadMpg.setClass_Id(String.valueOf(orggrademst.getGradeId()));
				hrBillHeadMpg.setDsgn_Id(String.valueOf(orgDesignationMst.getDsgnId()));
				hrBillHeadMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				hrBillHeadMpg.setCreatedDate(sysdate);
				hrBillHeadMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				hrBillHeadMpg.setCmnLocationMst(cmnLocationMst);
				billHeadMpgDAOImpl.create(hrBillHeadMpg);
				logger.info(" uploadNewEmployeeDataByExcel billSubheadId is ::"+billSubheadId);
				//ending for hr_pay_bill_subhead_mpg;
				
				
				
				//setting for hr_pay_office_mst
			//	HrPayOfficeMst hrPayOfficeMst = new HrPayOfficeMst();
				uploadNewEmployeeConfigarationDAOImpl upEmployeeConfigarationDAOImpl = new uploadNewEmployeeConfigarationDAOImpl(HrPayOfficeMst.class, serv.getSessionFactory());
				List<HrPayOfficeMst> officeList = upEmployeeConfigarationDAOImpl.getListByColumnAndValue("officeName",officeName);	
				logger.info("uploadNewEmployeeDataByExcel officeList " + officeList.size());
				
				long officeId = officeList.get(0).getOfficeId();
				logger.info("uploadNewEmployeeDataByExcel officeId " + officeId);
				
				HrPayOfficeMst hrPayOfficeMst =officeList.get(0);
				
				//ending for hr_pay_office_mst
				
				
				//setting for hr_pay_officepost_mpg
				HrPayOfficepostMpg hrOfficePostMpg = new HrPayOfficepostMpg();
				HrPayOfficePostMpgDAOImpl officePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());
				//long officePostId=IDGenerateDelegate.getNextId("hr_pay_officepost_mpg",objectArgs);
				long officePostId = IDGenerateDelegate.getNextId("hr_pay_officepost_mpg",objectArgs);
				hrOfficePostMpg.setOfficePostId(officePostId);
				hrOfficePostMpg.setHrPayOfficeMst(hrPayOfficeMst);
				hrOfficePostMpg.setCmnLocationMst(cmnLocationMst);
				hrOfficePostMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				hrOfficePostMpg.setCreatedDate(sysdate);
				hrOfficePostMpg.setStartDate(SD);
				hrOfficePostMpg.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
				hrOfficePostMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				hrOfficePostMpg.setOrgPostMstByPostId(orgPostMst);	
				
				officePostMpgDAOImpl.create(hrOfficePostMpg);
				logger.info("uploadNewEmployeeDataByExcel officePostId " + officePostId);
				
				//setting for hr_pay_order_mst
				HrPayOrderMst hrPayOrderMst = new HrPayOrderMst();
				OrderMstDAOImpl ordermstDAOImpl = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
				List<HrPayOrderMst> orderNameList = ordermstDAOImpl.getListByColumnAndValue("orderName", sanctionOrderNo); 
				logger.info("uploadNewEmployeeDataByExcel orderNameList " + orderNameList.size());
				long orderId = orderNameList.get(0).getOrderId();
				logger.info("uploadNewEmployeeDataByExcel HrPayOrderMst orderId " + orderId);
				//long orderId =IDGenerateDelegate.getNextId("hr_pay_order_mst",objectArgs);
				hrPayOrderMst.setOrderId(orderId);
				hrPayOrderMst.setCmnLanguageMst(cmnLangMstEng);
				hrPayOrderMst.setCreatedDate(sysdate);
				hrPayOrderMst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				hrPayOrderMst.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				hrPayOrderMst.setLocationCode(cmnLocationMst.getLocationCode());
				ordermstDAOImpl.create(hrPayOrderMst);
				logger.info("uploadNewEmployeeDataByExcel orderId11 is ::"+orderId);
				//ending for hr_pay_order_mst
		

				//setting for hr_pay_order_head_mpg
				
				logger.info("uploadNewEmployeeDataByExcel subHeadId is ::"+billNoList.get(0).getBillHeadId());
				long subHeadId = billNoList.get(0).getBillHeadId();
				HrPayOrderHeadMpg hrPayOrderHeadMpg= new HrPayOrderHeadMpg();
				OrderHeadMpgDAOImpl orderHeadMpgDAOImp = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
				long newOrderHeadId = IDGenerateDelegate.getNextId("hr_pay_order_head_mpg", objectArgs);
				hrPayOrderHeadMpg.setOrderHeadId(newOrderHeadId);
				
				int XX=1;
				List<HrPayOrderHeadMpg> orderIdList = orderHeadMpgDAOImp.getOrderData();
				for(int i=0; i<orderIdList.size(); i++)
				{
					
					long OrderId =orderIdList.get(i).getOrderId();
					logger.info("uploadNewEmployeeDataByExcel OrderIdOrderId OrderId22 is ::"+OrderId);
					//long OrderId =Long.parseLong(row[0].toString());
					//long OrderId=orderIdList.get(i).getOrderId();
					if(OrderId!=orderId)
					{
						logger.info("OrderId"+OrderId);
						logger.info("orderId"+orderId);
						objectArgs.put("msg", "Please enter existing ordername ");
						XX=2;
					}
					
				}
				
				hrPayOrderHeadMpg.setOrderId(orderId);
				hrPayOrderHeadMpg.setSubheadId(subHeadId);
				hrPayOrderHeadMpg.setCreatedDate(sysdate);
				hrPayOrderHeadMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				hrPayOrderHeadMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				hrPayOrderHeadMpgVO.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);						
				hrPayOrderHeadMpgVO.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
				hrPayOrderHeadMpg.setTrnCounter(1);
				orderHeadMpgDAOImp.create(hrPayOrderHeadMpg);
				logger.info("uploadNewEmployeeDataByExcel HrPayOrderHeadMpg newOrderHeadId " + newOrderHeadId);
				
				//ending for hr_pay_order_head_mpg

				//setting for hr_pay_order_head_post_mpg
				
				HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg = new HrPayOrderHeadPostMpg();
				OrderHeadPostmpgDAOImpl orderHeadPostmpgDAOImpl = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
				long orderHeadPostId = IDGenerateDelegate.getNextId("hr_pay_order_head_post_mpg",objectArgs);
				long ppostId = orgPostMst.getPostId();
				logger.info("uploadNewEmployeeDataByExcel ppostId "+ppostId);
				hrPayOrderHeadPostMpg.setOrderHeadPostId(orderHeadPostId);
				hrPayOrderHeadPostMpg.setOrderHeadId(newOrderHeadId);
				hrPayOrderHeadPostMpg.setPostId(orgPostMst.getPostId());
			    hrPayOrderHeadPostMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
			    hrPayOrderHeadPostMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
			    hrPayOrderHeadPostMpg.setCreatedDate(sysdate);
			    hrPayOrderHeadPostMpg.setTrnCounter(1);
			    logger.info("uploadNewEmployeeDataByExcel HrPayOrderHeadPostMpg orderHeadPostId " + orderHeadPostId);
				
			    orderHeadPostmpgDAOImpl.create(hrPayOrderHeadPostMpg);
				//ending for hr_pay_order_head_post_mpg
			    
			    
			    
			    //setting for HR_EMP_TRA_MPG
			    
			   HrEmpTraMpg hrEmpTraMpg = new HrEmpTraMpg();
			    long hrEmpTraMpgId = IDGenerateDelegate.getNextId("HR_EMP_TRA_MPG",objectArgs);
			    
			    HrEmpTraMpgDaoImpl hrEmpTraMpgDaoImpl = new HrEmpTraMpgDaoImpl(HrEmpTraMpg.class,serv.getSessionFactory());
			    hrEmpTraMpg.setId(hrEmpTraMpgId);
			    hrEmpTraMpg.setCmnDatabaseMst(cmnDatabaseMst);
			    hrEmpTraMpg.setCmnLocationMst(cmnLocationMst);
			    hrEmpTraMpg.setCreatedDate(sysdate);
			    //long EMPID = empMstEng.getOrgEmpMst().getEmpId();
			    hrEmpTraMpg.setHrEisEmpMst(empMstEng);
			    hrEmpTraMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
			    hrEmpTraMpg.setTrnCounter(1);
			    hrEmpTraMpg.setDistance(1);
			    hrEmpTraMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
			    hrEmpTraMpgDaoImpl.create(hrEmpTraMpg);
			    logger.info("uploadNewEmployeeDataByExcel HrEmpTraMpg hrEmpTraMpgId " + hrEmpTraMpgId);
			    logger.info("uploadNewEmployeeDataByExcel HrEmpTraMpg hrEmpTraMpg HrEisEmpMst" + hrEmpTraMpg.getHrEisEmpMst());
			    
			    
			    //ending for HR_EMP_TRA_MPG
				
				}	
	            }
	        }
					
			          //objectArgs.put("rs", rs);
					resultObject.setResultValue(objectArgs);
					resultObject.setViewName("uploadNewConfiguration");
					//resultObject.setViewName("viewEmployeeList");
					resultObject.setResultCode(ErrorConstants.SUCCESS);
				
            
		}
		catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
			
	}

	*/
	
	
	
	
	public ResultObject createDCPSEmployee(Map objectArgs)
	{
		logger.info("createDCPSEmployee called::::::");
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			
			long dcpsEmpId = (Long)objectArgs.get("dcpsEmpId");
			ServiceLocator serv  =(ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			 //long loggedInpostId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			 long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			 logger.info("post id from framework is "+postId);
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
				OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(postId);
				logger.info("PostMst object read is loggedInOrgPostMst"+loggedInOrgPostMst);
			NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(
					MstEmp.class, serv.getSessionFactory());
			MstEmp objDcpsEmpMst = (MstEmp) lObjNewRegTreasuryDAO.read(dcpsEmpId);
			
			logger.info("dcps emp mst is "+objDcpsEmpMst.getDcpsEmpId());
			
			long loggedInUser = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			logger.info("loggedInUserloggedInUserloggedInUserloggedInUser" + loggedInUser);
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);
			long dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());	
			
			logger.info("loc id is "+loginDetailsMap.get("locationId")+" db id is "+dbId);
			
			CmnDatabaseMst cmnDatabaseMst= new CmnDatabaseMst();
			cmnDatabaseMst.setDbId(dbId);
			
			GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(RltDcpsPayrollEmp.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			List<RltDcpsPayrollEmp> payrollEmpList = genDao.getListByColumnAndValue("dcpsEmpId", dcpsEmpId);
			RltDcpsPayrollEmp empData =null;
			
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl languageDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst  cmnLanguageMst = (CmnLanguageMst)genDao.read(langId);
			
			
			if(payrollEmpList != null && payrollEmpList.size() >0)
			{
				empData = payrollEmpList.get(0);
			}
			
			NewEmployeeConfigServiceHelper helper = new NewEmployeeConfigServiceHelper(serv);
			IdGenerator idGen = new IdGenerator();
			
			CmnLocationMstDaoImpl locDao = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst =locDao.read(empData.getLocId());
			
			if(empData != null && objDcpsEmpMst !=null)
			{
				
				String sevarthEmpCode="";
				String ddoCode ="";
				ddoCode = (String)objectArgs.get("ddoCode").toString();
	    		logger.info("DDO Code is " + ddoCode);
	    		
	    		
	    		OrgUserMst orgUserMst = new OrgUserMst();
				long UserId=idGen.PKGeneratorWODBLOC("ORG_USER_MST", objectArgs);
				orgUserMst.setUserId(UserId);
				orgUserMst.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				orgUserMst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				helper.insertOrgUserMstForUserCreation (orgUserMst);
				
				OrgEmpMst orgEmpMst = new OrgEmpMst();
				long empId = idGen.PKGeneratorWODBLOC("ORG_EMP_MST", objectArgs);
				orgEmpMst.setEmpId(empId);
				orgEmpMst.setOrgUserMst(orgUserMst);
				orgEmpMst.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				orgEmpMst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				orgEmpMst.setCmnLanguageMst(cmnLanguageMst);
				helper.insertOrgEmpMst(orgEmpMst,empData,objDcpsEmpMst,objectArgs);
				
				//sevarthEmpCode=this.generateSeevarthId(serv,ddoCode,orgEmpMst);
				Map sevaarthGenerationMap = new HashMap();
				sevaarthGenerationMap.put("dcpsEmpId", dcpsEmpId);
				sevaarthGenerationMap.put("serviceLocator", serv);
				sevarthEmpCode=this.generateSeevarthIdNewLogic(sevaarthGenerationMap);
				logger.info("sevarthEmpCode"+sevarthEmpCode);
				orgUserMst = new OrgUserMst();
				orgUserMst=orgUserMstDaoImpl.read(UserId);
				orgUserMst.setUserName(sevarthEmpCode);
				orgUserMstDaoImpl.update(orgUserMst);
				
				CmnLookupMstDAOImpl lookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				//added by khushal for emp sevarth code
				HrEisEmpMst hreisEmpMst = new HrEisEmpMst();
		/*		char firstName;
				char lastName;
				char gender;
				long var=01;
				long count;
				long temp;
				String officeName="";
				String empPrefix ;
				String tempSevarthEmpCode;
				String sevarthEmpCode="";
				int yearOfBirth1;*/
				
				//get DDO Code from Logged in Location id				
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				//String ddoCode ="";
				/*PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
	    		if(ddoCodeList!=null)
	    		 logger.info("After query execution for DDO Code " + ddoCodeList.size());
	    		
	    		OrgDdoMst ddoMst = null; 
	    		if(ddoCodeList!=null && ddoCodeList.size()>0){
	    			ddoMst = ddoCodeList.get(0);
	    		}
	    		
	    		if(ddoMst!=null) {
	    			ddoCode=ddoMst.getDdoCode();
	    		}
				ddoCode = (String)objectArgs.get("ddoCode").toString();
	    		logger.info("DDO Code is " + ddoCode);*/
	    		
				//ended	
	    		
	    		//sevarthEmpCode=this.generateSeevarthId(empId,serv,ddoCode);
	    		
				/*OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
				firstName = otherDtlsDao.getOrgEmpMst(empId).getEmpFname().toUpperCase().charAt(0);
				String middleName=""+otherDtlsDao.getOrgEmpMst(empId).getEmpMname().toUpperCase().charAt(0);
			    lastName=otherDtlsDao.getOrgEmpMst(empId).getEmpLname().toUpperCase().charAt(0);
				if(!middleName.equalsIgnoreCase(" "))
				{
					 middleName=""+otherDtlsDao.getOrgEmpMst(empId).getEmpMname().toUpperCase().charAt(0);
				}
				else
				{
					middleName=".";
				}
				 
				 yearOfBirth1=otherDtlsDao.getOrgEmpMst(empId).getEmpDob().getYear();
				 String yearOfBirth=String.format("%2s", yearOfBirth1).replace(' ', '0');
				 gender='T';
				 empPrefix = otherDtlsDao.getOrgEmpMst(empId).getEmpPrefix();
				 if(empPrefix.equalsIgnoreCase("Shri."))
				 {
					gender='M';
				 }
				 if(empPrefix.equalsIgnoreCase("Smt.")|| empPrefix.equalsIgnoreCase("Ms"))
				{
					gender='F';
				}
								
				
				CmnLocationMst locShortName = null;
				locShortName = otherDtlsDao.getCmnLocationMst(ddoCode);
				if(locShortName!=null && locShortName.getLocShortName()!=null)
					officeName=locShortName.getLocShortName().substring(0,3).toUpperCase();
				else
					logger.error("Location short name is null for DDO Code " + ddoCode);
							
				
				
				//officeName=otherDtlsDao.getCmnLocationMst(locId).getLocShortName().substring(0,3).toUpperCase();
				tempSevarthEmpCode=officeName+firstName+middleName+lastName+gender+yearOfBirth;
			    count=otherDtlsDao.getCount(tempSevarthEmpCode);
				temp=var+count;
				String tempVar=String.format("%2s", temp).replace(' ', '0');
				if(count==0)
				{
					sevarthEmpCode=sevarthEmpCode+var;
				}
				sevarthEmpCode=tempSevarthEmpCode+tempVar;*/
				// ended by khushal
				logger.info("going to check exceptional error");
				long hrEisEmpId = idGen.PKGeneratorWODBLOC("HR_EIS_EMP_MST", objectArgs);
				logger.info("hrEisEmpId pk generated:::"+hrEisEmpId);
				hreisEmpMst.setEmpId(hrEisEmpId);
				//hreisEmpMst.setMstEmp(objDcpsEmpMst);
				hreisEmpMst.setOrgEmpMst(orgEmpMst);
				hreisEmpMst.setCmnDatabaseMst(cmnDatabaseMst);
				hreisEmpMst.setCmnLocationMst(cmnLocationMst);
				hreisEmpMst.setSevarthEmpCode(sevarthEmpCode);
				hreisEmpMst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);				
				hreisEmpMst.setEmpType(Long.valueOf(resourceBundle.getString("permanent")));
				orgEmpMst.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				logger.info("going to check exceptional error");
				helper.insertHrEisEmpDtl(hreisEmpMst);
				logger.info("going to check exceptional error");
				
				 
				HrPayGpfBalanceDtls hrPayGpfBalanceDtls = new HrPayGpfBalanceDtls();
				hrPayGpfBalanceDtls.setCmnDatabaseMst(cmnDatabaseMst);
				hrPayGpfBalanceDtls.setCmnLocationMst(cmnLocationMst);
				hrPayGpfBalanceDtls.setCreatedDate(sysdate);
				hrPayGpfBalanceDtls.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				hrPayGpfBalanceDtls.setOrgUserMstByCreatedByUser(loggedInOrgUserMst);
				hrPayGpfBalanceDtls.setUserId(UserId);
			//	hrPayGpfBalanceDtls.setPfSeries(empData.getPfSeries());//by khushal 
				hrPayGpfBalanceDtls.setPfSeries(empData.getPfSeriesDesc());//by khushal 
				String dcpsAcc = objectArgs.get("DCPSId")!=null&& !objectArgs.get("DCPSId").toString().equals("")?objectArgs.get("DCPSId").toString():"";
				helper.insertHrGpfBalanceDtls(hrPayGpfBalanceDtls,empData,objDcpsEmpMst,dcpsAcc);
				
				HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
				long otherId = idGen.PKGeneratorWODBLOC("HR_EIS_OTHER_DTLS", objectArgs);
				hrEisOtherDtls.setOtherId(otherId);
				hrEisOtherDtls.setCmnDatabaseMst(cmnDatabaseMst);
				hrEisOtherDtls.setHrEisEmpMst(hreisEmpMst);
				hrEisOtherDtls.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				hrEisOtherDtls.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				hrEisOtherDtls.setTrnCounter(new Integer(1));
				helper.insertOtherDtls(  hrEisOtherDtls,empData,objDcpsEmpMst,objectArgs );
				
				HrEisBankDtls hrEisBankDtls = new HrEisBankDtls();
				long bankId = idGen.PKGeneratorWODBLOC("HR_EIS_BANK_DTLS", objectArgs);
				hrEisBankDtls.setBankDtlId( bankId);
				hrEisBankDtls.setCmnDatabaseMst(cmnDatabaseMst);
				hrEisBankDtls.setHrEisEmpMst(hreisEmpMst);
				hrEisBankDtls.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				hrEisBankDtls.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				helper.insertHrEisBankDtls(  hrEisBankDtls,empData,objDcpsEmpMst );
				
				
				HrEisProofDtl hrEisProofDtl = new HrEisProofDtl();
				long srNo = idGen.PKGeneratorWODBLOC("HR_EIS_PROOF_DTL", objectArgs);
				hrEisProofDtl.setCmnDatabaseMst(cmnDatabaseMst);
				hrEisProofDtl.setCmnLocationMst(cmnLocationMst);
				hrEisProofDtl.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				hrEisProofDtl.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				hrEisProofDtl.setOrgPostMstByUserId(orgUserMst);
				hrEisProofDtl.setSrNo(srNo);
				helper.insertHrEisProofDtls(  hrEisProofDtl,empData,objDcpsEmpMst );
				
				OrgUserpostRlt orgUserpostRlt = new OrgUserpostRlt();
				long id =  idGen.PKGeneratorWODBLOC("ORG_USERPOST_RLT", objectArgs);
				logger.info("Post id to be fetched ");
				logger.info("Post id to be fetched 11111111");
				long postIdTemp =  empData.getPostId() ;			
				logger.info("Post id in empData is "+postIdTemp);
				//OrgPostMst orgPostMst = orgPostMstDaoImpl.read(empData.getPostId());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postIdTemp);
				orgUserpostRlt.setOrgPostMstByPostId(orgPostMst);
				orgUserpostRlt.setOrgUserMst(orgUserMst);
				orgUserpostRlt.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				orgUserpostRlt.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				orgUserpostRlt.setActivateFlag(1);
				orgUserpostRlt.setEmpPostId(id);
				helper.insertOrgUserpostRlt(orgUserpostRlt, empData, objDcpsEmpMst);
				
				
				
				PsrPostMpgDAOImpl	psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
				if(psrPostMpgDAOImpl.getBillNoByPost(postIdTemp) != 0){
				long billNo = psrPostMpgDAOImpl.getBillNoByPost(postIdTemp);
				logger.info("bill no is seting on approval of employee is "+billNo);
				objectArgs.put("billNo", billNo);
				}
				
				objectArgs.put("orgEmpMstId", empId);
				objectArgs.put("sevarthId", sevarthEmpCode);
				ChangeGISDetailsServiceImpl gisDtls = new ChangeGISDetailsServiceImpl();
				logger.info("Going 2 call insertEmpChangedGISDataService:::::::::::::::::::");
				gisDtls.insertEmpChangedGISDataService(objectArgs);
				
				resultObject.setResultValue(objectArgs);
				
				
				
			}
			
			resultObject.setResultCode(1);
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
			logger.error("Error is Approval:"+e);
			resultObject.setResultValue(null);
			resultObject.setResultCode(-1);
			resultObject.setThrowable(e);
			resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setViewName("errorPage");
			return resultObject;
			
		}
		
		return resultObject;
	}
	
	
	public String generateSeevarthId(ServiceLocator serv,String ddoCode,OrgEmpMst orgEmpMst)
	{
		logger.info("inside the generate seevarth id method");
		char gender;
		String officeName="";
		String empPrefix ;
		String tempSevarthEmpCode;
		String sevarthEmpCode="";
		long count;
		long temp;
		long var=01;
		
		OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		char firstName = orgEmpMst.getEmpFname().toUpperCase().charAt(0);
		String middleName=""+orgEmpMst.getEmpMname().toUpperCase().charAt(0);
	    char lastName=orgEmpMst.getEmpLname().toUpperCase().charAt(0);
		if(!middleName.equalsIgnoreCase(" "))
		{
			 middleName=""+orgEmpMst.getEmpMname().toUpperCase().charAt(0);
		}
		else
		{
			middleName=".";
		}
		 
		int yearOfBirth1=orgEmpMst.getEmpDob().getYear();
		 String yearOfBirth=String.format("%2s", yearOfBirth1).replace(' ', '0');
		 gender='T';
		 empPrefix = orgEmpMst.getEmpPrefix();
		 if(empPrefix.equalsIgnoreCase("Shri."))
		 {
			gender='M';
		 }
		 if(empPrefix.equalsIgnoreCase("Smt.")|| empPrefix.equalsIgnoreCase("Ms"))
		{
			gender='F';
		}
						
		
		CmnLocationMst locShortName = null;
		locShortName = otherDtlsDao.getCmnLocationMst(ddoCode);
		if(locShortName!=null && locShortName.getLocShortName()!=null)
			officeName=locShortName.getLocShortName().substring(0,3).toUpperCase();
		else
			logger.error("Location short name is null for DDO Code " + ddoCode);
					
		
		
		//officeName=otherDtlsDao.getCmnLocationMst(locId).getLocShortName().substring(0,3).toUpperCase();
		tempSevarthEmpCode=officeName+firstName+middleName+lastName+gender+yearOfBirth;
	    count=otherDtlsDao.getCount(tempSevarthEmpCode);
		temp=var+count;
		String tempVar=String.format("%2s", temp).replace(' ', '0');
		if(count==0)
		{
			sevarthEmpCode=sevarthEmpCode+var;
		}
		sevarthEmpCode=tempSevarthEmpCode+tempVar;
		
		logger.info("end of  generate seevarth id method with seevarth Id value:"+sevarthEmpCode);
		return sevarthEmpCode;
	}
	
	public String generateSeevarthIdNewLogic(Map inputMap)
	{
		String lStrSevarthEmpCode = "";
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		MstEmp lObjEmpData = null;
		
		try {
		
			ServiceLocator serv  =(ServiceLocator) inputMap.get("serviceLocator");
			NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(MstEmp.class, serv.getSessionFactory());
			OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = null;
			
			Long lLngEmpId = Long.parseLong(String.valueOf(inputMap.get("dcpsEmpId")));
			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			lObjEmpData = new MstEmp();
			lObjEmpData = (MstEmp) lObjNewRegTreasuryDAO.read(lLngEmpId);
			
			String lStrEmpFname = lObjEmpData.getName();
			Character lCharGender = lObjEmpData.getGender();
			
			Date lDateBirthDate = lObjEmpData.getDob();
			lObjEmpData.getDesignation();
			lObjEmpData.getParentDept();
			String lStrBirthDate = lObjDateFormat.format(lDateBirthDate);
			
			lStrEmpFname = lStrEmpFname.replace('.', ' ');
			lStrEmpFname = lStrEmpFname.replaceAll("\\s+", " ");

			String Names[] = lStrEmpFname.split(" ");
			String Fname = null;
			String Mname = null;
			String Lname = null;

			if (Names.length >= 3) {
				Fname = Names[0].substring(0, 1);
				Mname = Names[1].substring(0, 1);
				Lname = Names[2].substring(0, 1);

			}

			else if (Names.length == 2) {
				Fname = Names[0].substring(0, 1);
				Mname = Names[1].substring(0, 1);
				//Lname = ".";
				
				Lname = Names[1].substring(1, 2);

			}

			else if (Names.length == 1) {
				Fname = Names[0].substring(0, 1);
				//Mname = ".";
				//Lname = ".";
				
				Mname = Names[0].substring(1,2);
				Lname = Names[0].substring(2,3);
			}
			
			cmnLocationMst = otherDtlsDao.getCmnLocationMst(lObjEmpData.getDdoCode());
			String lStrOfficeName = cmnLocationMst.getLocShortName().substring(0,3).toUpperCase();
			//changes for ZP
			//to add first 2 digits of Admin
			String adminOfficeMst=lObjEmpData.getDdoCode();
			adminOfficeMst=adminOfficeMst.substring(0,2);	
			String lStrTempSevarthCode=adminOfficeMst+lStrOfficeName+Fname+Mname+Lname+lCharGender.toString()+lStrBirthDate.substring(8, 10);;
			long curentIdCount=otherDtlsDao.getCount(lStrTempSevarthCode);
			long tempIdCount=curentIdCount+01;
			String tempCountVar=String.format("%2s", tempIdCount).replace(' ', '0');
			if(curentIdCount==0)
			{
				lStrSevarthEmpCode=lStrSevarthEmpCode+"01";
			}
			lStrSevarthEmpCode=lStrTempSevarthCode+tempCountVar;
			
			logger.info("end of  generate seevarth id method with seevarth Id value:"+lStrSevarthEmpCode);
		}catch (Exception ex) {
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			logger.error("Error is: "+ ex.getMessage());
		}
		
		
		return lStrSevarthEmpCode;
	}

}

