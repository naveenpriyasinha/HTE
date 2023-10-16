package com.tcs.sgv.user.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.net.TNSAddress.Description;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDao;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoInfoDAO;
import com.tcs.sgv.dcps.dao.DdoInfoDAOImpl;
import com.tcs.sgv.dcps.dao.DdoOfficeDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstDcpsDesignation;
import com.tcs.sgv.eis.dao.CmnlookupMstDAOImpl;
import com.tcs.sgv.eis.dao.GradDesgScaleMapDAO;
import com.tcs.sgv.eis.dao.GradeMasterDAO;
import com.tcs.sgv.eis.dao.HrPayOfficePostMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderHeadPostmpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderMstDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PsrPostMpgDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadPostMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.eis.valueobject.MstPayrollDesignationMst;
import com.tcs.sgv.ess.dao.OrgDesignationMstDao;
import com.tcs.sgv.ess.dao.OrgDesignationMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDao;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDao;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDao;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDaoImpl;
import com.tcs.sgv.user.dao.UserPostDAO;
import com.tcs.sgv.user.valueobject.UserPostCustomVO;
import com.tcs.sgv.user.valueobject.FrmSubPostidMpg;

// Referenced classes of package com.tcs.sgv.ess.service:
//            AdminOrgPostDtlServ

public class AdminOrgPostDtlServImpl extends ServiceImpl implements
AdminOrgPostDtlServ {

	public AdminOrgPostDtlServImpl() {
		constantsBundle = ResourceBundle.getBundle("resources.Payroll");
		DEPT_ID = Integer.parseInt(constantsBundle.getString("GAD"));
		logger = LogFactory.getLog(getClass());
		msg = 0;
	}

	public ResultObject showAdminOrgPostDtl(Map objectArgs) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");

			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			HttpSession session=request.getSession();
			
			String loggedInPost= loginMap.get("loggedInPost").toString();
			long langId = Long.parseLong(loginMap.get("langId").toString());
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
			logger.info("locId "+locId);
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			List fieldCaptionList = new ArrayList();
			List tempList = new ArrayList();
			tempList.add("Post Name");
			fieldCaptionList.add(tempList);
			List locationList = null;
			UserPostDAO postDAO = new UserPostDAO(UserPostCustomVO.class, serv
					.getSessionFactory());
			String empName = "";
			if(voToService != null)
			{

				if((voToService.get("Employee_srchNameText_viewPostSearch")!=null&&!voToService.get("Employee_srchNameText_viewPostSearch").equals("")))
					empName=(String)voToService.get("Employee_srchNameText_viewPostSearch").toString();
				logger.info("employee name : " + empName);
			}
			// modified for search by post and search by name by hemant(307727)	
			String lPostname="";
			String Psr="";
			String Bill="";
			String Dsgn="";
			String empId = "";
			if(voToService != null)
			{
				if(voToService.get("Post")!=null)
					lPostname=(String)voToService.get("Post").toString();
				if(voToService.get("PsrNo")!=null)
					Psr=(String)voToService.get("PsrNo").toString();
				if(voToService.get("BillNo")!=null)
					Bill=(String)voToService.get("BillNo").toString();
				if(voToService.get("Dsgn")!=null)
					Dsgn=(String)voToService.get("Dsgn").toString();
				if(voToService.get("empId")!=null)
					empId=(String)voToService.get("empId").toString();
			}
			logger.info("Post :" + lPostname + " PsrNo : "  + Psr + "  BillNo : " + Bill +  "  Dsgn :  " + Dsgn + " EmpId " + empId);

			UserPostCustomVO customVO = new UserPostCustomVO();	
			String PostName = "";
			String	dsgn = "";
			if(lPostname != null && !lPostname.equals(""))
				PostName = lPostname.trim();		
			if(Dsgn != null && !Dsgn.equals(""))
				dsgn = Dsgn.trim();
			List postNameList = new ArrayList();

			//String loggedInPost= loginDetailsMap.get("loggedInPost").toString();
			locationList=postDAO.getSubLocationDDOs(loggedInPost);
			String locationcodeArray="";
			if(locationList!=null && locationList.size()>0)
				for(int i=0;i< locationList.size();i++){
					if(i==0)
						locationcodeArray+=locationList.get(i).toString();
					else
					{
						locationcodeArray+=","+locationList.get(i).toString();
						logger.info("locationcodeArray "+locationcodeArray);
					}

				}

			//added by abhishek 



			//add end



			//List <HrEisEmpMst> actionList = orderMasterDAO.getAllData(locationcodeArray);//list will collect all data from Vo
			
			String flag="N";
			String ddo=null;

			flag = StringUtility.getParameter("flag", request);
	    	logger.info("flag :- "+flag);
	    	
	    	if(flag.equals("Y")){
	    		   
	    		   ddo = StringUtility.getParameter("ddoCode", request);
	    		   logger.info("ddo :- "+ddo);
	    		   objectArgs.put("ddo", ddo);
			}

			if(empId != null && !empId.equals("")){
				//postNameList = postDAO.getPostNameForDisplayThruEmpId(locId, empId, langId);
				postNameList = postDAO.getPostNameForDisplayThruEmpId(locationcodeArray, empId, langId);
			}else
			{
				//postNameList = postDAO.getPostNameForDisplay(locId,PostName,Psr, Bill, dsgn);
				postNameList = postDAO.getPostNameForDisplay(locationcodeArray,PostName,Psr,Bill,dsgn,ddo);	
			}

			List post = new ArrayList();			
			logger.info("postNameList size is: "+postNameList.size());
			String  empFullName = "";
			String postType="";
			String subjectName="";
			String postLookupId="";
			String permenantlookupId="10001198129";
			String temparerylookupId="10001198130";
			String statutorylookupId="10001198155";

			if( postNameList!=null )
			{
				for (int i = 0; i < postNameList.size(); i++) 
				{
					customVO = new UserPostCustomVO();

					Object rowList[] = (Object[]) postNameList.get(i);

					String postName = rowList[0].toString();
					//customVO.setPostname(postName);

					if(rowList[6] != null && !(rowList[6].toString().trim()).equals(""))
					{	
						postLookupId = rowList[6].toString();
					}
					if(postLookupId.equals("10001198129"))
					{
						postName =postName.concat("P");
					}
					else if(postLookupId.equals("10001198130"))
					{
						postName =postName.concat("T");
					}
					else if(postLookupId.equals("10001198155"))
					{
						postName =postName.concat("S");
					}
					else
					{
						postName =postName;
					}
					customVO.setPostname(postName);

					long postId = Long.parseLong(rowList[1].toString());
					customVO.setPostId(postId);	
					// by khushal

					/*dminOrgPostDtlDaoImpl adminOrgPostDtlDaoImpl=new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv
							.getSessionFactory());
					String postType = "";	
					postType = adminOrgPostDtlDaoImpl.getPostType(postId);*/
					//end by khushal
					if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
						empFullName = rowList[2].toString();
					} else{
						empFullName = "VACANT";
					}
					customVO.setEmpFullName(empFullName);

					String dsgnName = rowList[3].toString();
					if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){
						customVO.setDsgnname(dsgnName);
					}
					String BillNo = " ";
					String PsrNo = " ";

					if(rowList[4]!= null){
						PsrNo = rowList[4].toString();
					}
					customVO.setPsrNo(PsrNo);

					if(rowList[5] != null){
						BillNo = rowList[5].toString();
					}
					customVO.setBillNo(BillNo);	

					//logger.info("===> BillNo :: "+BillNo);
					if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
						postType = rowList[7].toString();
					}else{
						postType = "VACANT";
					}
					
					customVO.setPostType(postType);
					
					/*if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
						subjectName = rowList[8].toString();
					}else{
						subjectName = "VACANT";
					}
					customVO.setSubjectName(subjectName);*/
					
					post.add(customVO);
				}
			}


			logger.info("The List size is:-"+post.size());
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			SimpleDateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
			String TodaysDate = fmt.format(today);
			//logger.info("====> TodaysDate :: "+TodaysDate);

			
	    	   
			OrderMstDAOImpl ordermstDaoImpl = new OrderMstDAOImpl(HrPayOrderMst.class, serv
					.getSessionFactory());
			List filterDdoCode = ordermstDaoImpl.getFilterDdoCode(locationcodeArray);
			logger.info("filterDdoCode :- "+filterDdoCode.size());
			
			
			
			List orderList = ordermstDaoImpl.getAllOrderData(locId,TodaysDate);
			//logger.info("==>1 orderList.size() :: "+orderList.size());
			//ordermstDaoImpl.getSubDDOs();

			objectArgs.put("orderList", orderList);
			objectArgs.put("empId", empId);
			objectArgs.put("empName", empName);

			objectArgs.put("recordList", post);
			objectArgs.put("filterDdoCode", filterDdoCode);

			//added by abhilash

			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			long loggedInPostId = Long.parseLong(loginMap.get("primaryPostId").toString());
			DcpsCommonDAOImpl commonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			//code changed by Ankit Bhatt for DDO Code
			PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
			OrgDdoMst ddoMst  = null;
			if(ddoList!=null && ddoList.size()>0) {
				ddoMst = ddoList.get(0);
			}
			//ended
			String ddoCode = null;
			if(ddoMst!=null)
				ddoCode = ddoMst.getDdoCode();



			//DdoInfoDAO ddoInfoDAO = new DdoInfoDAOImpl(null, serv.getSessionFactory());
			//OrgDdoMst lObjOrgDdoMst = ddoInfoDAO.getDdoInformation(ddoCode);
			Long lLngFieldDept = Long.parseLong(ddoMst.getHodLocCode());
			AdminOrgPostDtlDaoImpl adminDao = new AdminOrgPostDtlDaoImpl(MstDcpsDesignation.class, serv.getSessionFactory());
			List<OrgDesignationMst> desgList = adminDao.getActiveDesig(lLngFieldDept);
			//List<OrgDesignationMst> desgList = adminDao.getActiveDsgnListOfFieldDept(locId);
			logger.info("desgList size **************" +desgList.size() );
			objectArgs.put("Designation", desgList);
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			logger.info("billList checking abhilash size **************" +billList.size() );

			//ended by abhilash

			objectArgs.put("fieldCaptionList", fieldCaptionList);
			resObj.setViewName("showAdminPostDtls");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			logger.error("Admin Screen Showing all Post Error", ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}
	
	
	public ResultObject subjectPostMapping(Map objectArgs) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");

			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			HttpSession session=request.getSession();
			
			String loggedInPost= loginMap.get("loggedInPost").toString();
			long langId = Long.parseLong(loginMap.get("langId").toString());
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
			logger.info("locId "+locId);
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			List fieldCaptionList = new ArrayList();
			List tempList = new ArrayList();
			tempList.add("Post Name");
			fieldCaptionList.add(tempList);
			List locationList = null;
			UserPostDAO postDAO = new UserPostDAO(UserPostCustomVO.class, serv
					.getSessionFactory());
			String empName = "";
			if(voToService != null)
			{

				if((voToService.get("Employee_srchNameText_viewPostSearch")!=null&&!voToService.get("Employee_srchNameText_viewPostSearch").equals("")))
					empName=(String)voToService.get("Employee_srchNameText_viewPostSearch").toString();
				logger.info("employee name : " + empName);
			}
			// modified for search by post and search by name by hemant(307727)	
			String lPostname="";
			String Psr="";
			String Bill="";
			String Dsgn="";
			String empId = "";
			if(voToService != null)
			{
				if(voToService.get("Post")!=null)
					lPostname=(String)voToService.get("Post").toString();
				if(voToService.get("PsrNo")!=null)
					Psr=(String)voToService.get("PsrNo").toString();
				if(voToService.get("BillNo")!=null)
					Bill=(String)voToService.get("BillNo").toString();
				if(voToService.get("Dsgn")!=null)
					Dsgn=(String)voToService.get("Dsgn").toString();
				if(voToService.get("empId")!=null)
					empId=(String)voToService.get("empId").toString();
			}
			logger.info("Post :" + lPostname + " PsrNo : "  + Psr + "  BillNo : " + Bill +  "  Dsgn :  " + Dsgn + " EmpId " + empId);

			UserPostCustomVO customVO = new UserPostCustomVO();	
			String PostName = "";
			String	dsgn = "";
			if(lPostname != null && !lPostname.equals(""))
				PostName = lPostname.trim();		
			if(Dsgn != null && !Dsgn.equals(""))
				dsgn = Dsgn.trim();
			List postNameList = new ArrayList();

			//String loggedInPost= loginDetailsMap.get("loggedInPost").toString();
			locationList=postDAO.getSubLocationDDOs(loggedInPost);
			String locationcodeArray="";
			if(locationList!=null && locationList.size()>0)
				for(int i=0;i< locationList.size();i++){
					if(i==0)
						locationcodeArray+=locationList.get(i).toString();
					else
					{
						locationcodeArray+=","+locationList.get(i).toString();
						logger.info("locationcodeArray "+locationcodeArray);
					}

				}

			//added by abhishek 



			//add end



			//List <HrEisEmpMst> actionList = orderMasterDAO.getAllData(locationcodeArray);//list will collect all data from Vo
			
			
			String flag="N";
			String ddo=null;

			flag = StringUtility.getParameter("flag", request);
	    	logger.info("flag :- "+flag);
	    	
	    	if(flag.equals("Y")){
	    		   
	    		   ddo = StringUtility.getParameter("ddoCode", request);
	    		   logger.info("ddo :- "+ddo);
	    		   objectArgs.put("ddo", ddo);
			}

			if(ddo != null){
				
				//postNameList = postDAO.getPostNameForDisplayThruEmpId(locationcodeArray, empId, langId);
				  postNameList = postDAO.getPostNameForDisplay(locationcodeArray,PostName,Psr,Bill,dsgn,ddo);
			}else
			{
				//postNameList = postDAO.getPostNameForDisplay(locationcodeArray,PostName,Psr,Bill,dsgn,ddo);	
			}

			List post = new ArrayList();			
			logger.info("postNameList size is: "+postNameList.size());
			String  empFullName = "";
			String postType="";
			String subjectName="";
			String postLookupId="";
			String permenantlookupId="10001198129";
			String temparerylookupId="10001198130";
			String statutorylookupId="10001198155";

			if( postNameList!=null )
			{
				for (int i = 0; i < postNameList.size(); i++) 
				{
					customVO = new UserPostCustomVO();

					Object rowList[] = (Object[]) postNameList.get(i);

					String postName = rowList[0].toString();
					//customVO.setPostname(postName);

					if(rowList[6] != null && !(rowList[6].toString().trim()).equals(""))
					{	
						postLookupId = rowList[6].toString();
					}
					if(postLookupId.equals("10001198129"))
					{
						postName =postName.concat("P");
					}
					else if(postLookupId.equals("10001198130"))
					{
						postName =postName.concat("T");
					}
					else if(postLookupId.equals("10001198155"))
					{
						postName =postName.concat("S");
					}
					else
					{
						postName =postName;
					}
					customVO.setPostname(postName);

					long postId = Long.parseLong(rowList[1].toString());
					customVO.setPostId(postId);	
					// by khushal

					/*dminOrgPostDtlDaoImpl adminOrgPostDtlDaoImpl=new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv
							.getSessionFactory());
					String postType = "";	
					postType = adminOrgPostDtlDaoImpl.getPostType(postId);*/
					//end by khushal
					if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
						empFullName = rowList[2].toString();
					} else{
						empFullName = "VACANT";
					}
					customVO.setEmpFullName(empFullName);

					String dsgnName = rowList[3].toString();
					if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){
						customVO.setDsgnname(dsgnName);
					}
					String BillNo = " ";
					String PsrNo = " ";

					if(rowList[4]!= null){
						PsrNo = rowList[4].toString();
					}
					customVO.setPsrNo(PsrNo);

					if(rowList[5] != null){
						BillNo = rowList[5].toString();
					}
					customVO.setBillNo(BillNo);	

					//logger.info("===> BillNo :: "+BillNo);
					if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
						postType = rowList[7].toString();
					}else{
						postType = "VACANT";
					}
					
					customVO.setPostType(postType);
					if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
						subjectName = rowList[8].toString();
					}else{
						subjectName = "VACANT";
					}
					//logger.info("===> postType :: "+postType);
					customVO.setSubjectName(subjectName);
					
					post.add(customVO);
				}//end for
			}//end if


			logger.info("The List size is:-"+post.size());
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			SimpleDateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
			String TodaysDate = fmt.format(today);
			//logger.info("====> TodaysDate :: "+TodaysDate);

			
	    	   
			OrderMstDAOImpl ordermstDaoImpl = new OrderMstDAOImpl(HrPayOrderMst.class, serv
					.getSessionFactory());
			AdminOrgPostDtlDaoImpl adminDao = new AdminOrgPostDtlDaoImpl(MstDcpsDesignation.class, serv.getSessionFactory());
			List filterDdoCode = ordermstDaoImpl.getFilterDdoCode(locationcodeArray);
			logger.info("filterDdoCode :- "+filterDdoCode.size());
			List subList = adminDao.getSubjectListForMapping();
			objectArgs.put("SubjectList", subList);
			logger.info("SubjectList :- "+subList.size());
			
			
			List orderList = ordermstDaoImpl.getAllOrderData(locId,TodaysDate);
			//logger.info("==>1 orderList.size() :: "+orderList.size());
			//ordermstDaoImpl.getSubDDOs();

			objectArgs.put("orderList", orderList);
			objectArgs.put("empId", empId);
			objectArgs.put("empName", empName);

			objectArgs.put("recordList", post);
			objectArgs.put("filterDdoCode", filterDdoCode);

			//added by abhilash

			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			long loggedInPostId = Long.parseLong(loginMap.get("primaryPostId").toString());
			DcpsCommonDAOImpl commonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			//code changed by Ankit Bhatt for DDO Code
			PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
			OrgDdoMst ddoMst  = null;
			if(ddoList!=null && ddoList.size()>0) {
				ddoMst = ddoList.get(0);
			}
			//ended
			String ddoCode = null;
			if(ddoMst!=null)
				ddoCode = ddoMst.getDdoCode();



			//DdoInfoDAO ddoInfoDAO = new DdoInfoDAOImpl(null, serv.getSessionFactory());
			//OrgDdoMst lObjOrgDdoMst = ddoInfoDAO.getDdoInformation(ddoCode);
			Long lLngFieldDept = Long.parseLong(ddoMst.getHodLocCode());
			AdminOrgPostDtlDaoImpl adminDao1 = new AdminOrgPostDtlDaoImpl(MstDcpsDesignation.class, serv.getSessionFactory());
			List<OrgDesignationMst> desgList = adminDao1.getActiveDesig(lLngFieldDept);
			//List<OrgDesignationMst> desgList = adminDao.getActiveDsgnListOfFieldDept(locId);
			logger.info("desgList size **************" +desgList.size() );
			objectArgs.put("Designation", desgList);
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			logger.info("billList checking abhilash size **************" +billList.size() );

			//ended by abhilash

			objectArgs.put("fieldCaptionList", fieldCaptionList);
			resObj.setViewName("subjectpostMapping");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			logger.error("Admin Screen Showing all Post Error", ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}
	

	private Object rowList(int i) {
		return null;
	}

	public ResultObject addAdminOrgPostDtl(Map objectArgs) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");

			logger
			.info("===========addAdminOrgPostDtl=====called=============starts====");

			OrgDesignationMstDao orgDesignationMstDao = new OrgDesignationMstDaoImpl(
					OrgDesignationMst.class, serv.getSessionFactory());
			AdminOrgPostDtlDaoImpl adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			long langId = Long.parseLong(loginMap.get("langId").toString());
			long locId = Long.parseLong(loginMap.get("locationId").toString());
			long loggedInPostId = Long.parseLong(loginMap.get("primaryPostId")
					.toString());

			logger.info("==========LocationId is =====" + locId
					+ "============starts====");

			long activeflag = 1L;

			DcpsCommonDAOImpl commonDao = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			// String ddoCode = commonDao.getDdoCodeForDDO(loggedInPostId);

			// ADDED by abhilash
			String ddoCode = "";
			PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class,
					serv.getSessionFactory());
			List<OrgDdoMst> ddoCodeList = payBillDAO
			.getDDOCodeByLoggedInlocId(locId);
			if (ddoCodeList != null)
				logger.info("After query execution for DDO Code "
						+ ddoCodeList.size());

			OrgDdoMst ddoMst = null;
			if (ddoCodeList != null && ddoCodeList.size() > 0) {
				ddoMst = ddoCodeList.get(0);
			}

			if (ddoMst != null) {
				ddoCode = ddoMst.getDdoCode();
			}
			logger.info("DDO Code is " + ddoCode);
			// ended by abhilash

			// DdoInfoDAO ddoInfoDAO = new DdoInfoDAOImpl(null,
			// serv.getSessionFactory());
			// OrgDdoMst lObjOrgDdoMst = ddoInfoDAO.getDdoInformation(ddoCode);

			Long lLngFieldDept = Long.parseLong(ddoMst.getHodLocCode());

			AdminOrgPostDtlDaoImpl adminDao = new AdminOrgPostDtlDaoImpl(
					MstDcpsDesignation.class, serv.getSessionFactory());
			logger.info("lLngFieldDept:::::::::: " + lLngFieldDept);
			logger.info("locId Muni:::::::::: " + locId);
			List<OrgDesignationMst> desgList = adminDao.getActiveDsgnListOfFieldDept(lLngFieldDept);
			logger.info("desgList size **************" + desgList.size());
			objectArgs.put("Designation", desgList);
			List branchList_en = adminOrgPostDtlDao.getAllBranchList(1L);
			objectArgs.put("Branch", branchList_en);

			OrderMstDAOImpl ordermstDaoImpl = new OrderMstDAOImpl(
					HrPayOrderMst.class, serv.getSessionFactory());

			// added by khushal
			/*
			 * List<CmnLookupMst> postType=ordermstDaoImpl.getPostType();
			 * logger.info("----khushal-postType-"+postType);
			 * objectArgs.put("PostType", postType);
			 * 
			 * List postLookupId=ordermstDaoImpl.getLookUpId();
			 * logger.info("----khushal-postType-"+postLookupId);
			 * objectArgs.put("PostLookUpId", postLookupId);
			 */

			// end by khushal
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			String TodaysDate = fmt.format(today);
			logger.info("====> TodaysDate :: " + TodaysDate);

			List orderList = ordermstDaoImpl.getAllOrderData(locId, TodaysDate);
			// logger.info("==> orderList.size() :: "+orderList.size());
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			List officeList = adminOrgPostDtlDao.getAllOfficesFromDDO(ddoCode);
			// added by Demolisher
			long lpostId = SessionHelper.getPostId(objectArgs);
			logger.info("Post ID is ::::::::::::::::" + lpostId);
			List subOfficeList = adminOrgPostDtlDao
			.getSubOfficesFromDDONew(lpostId);
			AdminOrgPostDtlDaoImpl postdao = new AdminOrgPostDtlDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			//added by roshan for Filterrrrrrr------------------- 
			//code to find the district
			String districtID=postdao.districtName(ddoCode);
			logger.info("district id found is"+districtID);
			//code to find the taluka
			List talukaList=postdao.allTaluka(districtID);
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			HttpSession session=request.getSession();
			String talukaId=null;
			String ddoSelected=null;

			if((StringUtility.getParameter("taluka", request)!=null)&&(StringUtility.getParameter("taluka", request)!="")&&(Long.parseLong(StringUtility.getParameter("taluka", request))!=-1)){
				talukaId= StringUtility.getParameter("taluka", request);
				logger.info("talukaId*********"+talukaId);
			}

			if((StringUtility.getParameter("ddoCodeForFilter", request)!=null)&&(StringUtility.getParameter("ddoCodeForFilter", request)!="")){
				ddoSelected= StringUtility.getParameter("ddoCodeForFilter", request);
				logger.info("ddoSelected******"+ddoSelected);

				logger.info("StringUtility.getParameter(ddoCode, request)"+StringUtility.getParameter("ddoCodeForFilter", request));
			}


			List DDOdtls=postdao.getSubDDOsOffc(loggedInPostId,talukaId,ddoSelected);

			////
			//	List DDOdtls = postdao.getSubDDOsOffc(loggedInPostId);
			logger.info("SUb DDO LIST **************" + DDOdtls.size());
			int i = 0;
			while (i < DDOdtls.size()) {
				logger.info("SUb DDO LIST**************" + DDOdtls.get(i));
				i++;
			}
			objectArgs.put("DDOlist", DDOdtls);
			/*
			 * logger .info("===========addolist===="+ddolist.get(0)); logger
			 * .info("===========addolist===="+ddolist.get(1));
			 */
			// added by vaibhav tyagi
			List subList = adminDao.getSubjectList();
			objectArgs.put("SubjectList", subList);
			logger.info("Subject List Size: " + subList.size());
			// added by vaibhav tyagi
			objectArgs.put("subOfficeList", subOfficeList);
			objectArgs.put("orderList", orderList);
			objectArgs.put("billList", billList);
			objectArgs.put("langId", Long.valueOf(langId));
			objectArgs.put("officeList", officeList);
			objectArgs.put("flag", "add");
			//added by roshan
			objectArgs.put("talukaList", talukaList);
			objectArgs.put("talukaId", talukaId);
			objectArgs.put("ddoSelected", ddoSelected);
			//end by roshan
			resObj.setViewName("adminCreatePostDtls");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger.error("Admin Screen Creating A Post Error", ex);
			resObj.setResultCode(-1);
		}

		logger
		.info("===========addAdminOrgPostDtl=====called=============ends====");
		return resObj;
	}

	@SuppressWarnings("null")
	public ResultObject submitAdminOrgPostDtl(Map objectArgs) {
		// ResultObject resObj = new ResultObject(0);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			logger.info("===========submitAdminOrgPostDtl=====called=================");

			// logger.info("inside entry of post screen");
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
			long userID = Long.parseLong(loginMap.get("userId").toString());
			long setPostId = Long.parseLong(loginMap.get("loggedInPost").toString());
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String ddoCode = StringUtility.getParameter("ddoCode", request);
			logger.info("====> Permenant in Service :: " + ddoCode);
			if (!ddoCode.equals("-1") && !ddoCode.equals("") && ddoCode != null) 
			{
				AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
				logger.info("==========ddoCode is =====" + ddoCode+ "============starts====");
				List DDOdtls = adminOrgPostDtlDao.getDDODtls(ddoCode);
				logger.info("==========DDOdtls is =====" + DDOdtls.size()+ "============starts====");
				String strLngFieldDept = "";
				Iterator IT = DDOdtls.iterator();
				Object o[] = null;
				String locIdStr = "";
				String strPostId = "";
				while (IT.hasNext()) 
				{
					o = (Object[]) IT.next();
					locIdStr = o[0].toString();
					strLngFieldDept = o[1].toString();
					strPostId = o[2].toString();

				}
				// String locIdStr= o.toString();
				locId = Long.parseLong(locIdStr);
				setPostId = Long.parseLong(strPostId);

			}
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMstLoggedin = (OrgUserMst) orgUserMstDaoImpl.read(Long.valueOf(userID));
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMstLoggedIn = (OrgPostMst) orgPostMstDaoImpl.read(Long.valueOf(setPostId));
			AdminOrgPostDtlDaoImpl adminOrgPostDtlDaoImpl1 = new AdminOrgPostDtlDaoImpl(FrmSubPostidMpg.class, serv.getSessionFactory());
			long officeId = 0;
			long billId = 0;
			long noOfPost = 0;
			long orderId = 0;
			String desgnCode = "";
			long desgnId = 0;
			String startDate = "";
			String endDate = "";
			// added by khushal
			String tempEndDate = "";
			String orderDate = "";
			String postType = "";
			String remarks = "";
			String oldOrderDate = "";
			String oldOrderCmb = "";
			String newOrderDate = "";
			String newOrderId = "";
			String tempTypePost = "";
			String Permenant = "";
			String subPostTypeTemp = "";
			String subPostTypePerm = "";
			String subjectSel = "";
			//added by Demolisher
			long subFieldDeptId = 0;

			if (objectArgs.get("officeCmb") != null) {
				officeId = Long.parseLong(objectArgs.get("officeCmb").toString());
			}
			if (objectArgs.get("billCmb") != null) {
				billId = Long.parseLong(objectArgs.get("billCmb").toString());
			}
			if (objectArgs.get("noofpost") != null) {
				noOfPost = Long.parseLong(objectArgs.get("noofpost").toString());
			}

			if (objectArgs.get("orderCmb") != null) {
				orderId = Long.parseLong(objectArgs.get("orderCmb").toString());
			}

			if (objectArgs.get("designationCmb") != null && !objectArgs.get("designationCmb").toString().equals("")) {
				desgnCode = objectArgs.get("designationCmb").toString();
				desgnId = Long.parseLong(desgnCode);
			}
			if (objectArgs.get("startDate") != null && !objectArgs.get("startDate").toString().equals("")) {
				startDate = objectArgs.get("startDate").toString();

			}

			if (objectArgs.get("endDate") != null&& !objectArgs.get("endDate").toString().equals("")) {
				endDate = objectArgs.get("endDate").toString();

			}
			if (objectArgs.get("tempEndDate") != null && !objectArgs.get("tempEndDate").toString().equals("")) {
				tempEndDate = objectArgs.get("tempEndDate").toString();

			}
			if (objectArgs.get("postType") != null) {
				postType = objectArgs.get("postType").toString();

			}
			if (objectArgs.get("remarks") != null
					&& !objectArgs.get("remarks").toString().equals("")) {
				remarks = objectArgs.get("remarks").toString();

			}

			if (objectArgs.get("oldOrderDate") != null
					&& !objectArgs.get("oldOrderDate").toString().equals("")) {
				oldOrderDate = objectArgs.get("oldOrderDate").toString();

			}
			if (objectArgs.get("oldOrderCmb") != null
					&& !objectArgs.get("oldOrderCmb").toString().equals("")) {
				oldOrderCmb = objectArgs.get("oldOrderCmb").toString();

			}
			if (objectArgs.get("newDate") != null
					&& !objectArgs.get("newDate").toString().equals("")) {
				newOrderDate = objectArgs.get("newDate").toString();

			}
			if (objectArgs.get("newOrderCmb") != null
					&& !objectArgs.get("newOrderCmb").toString().equals("")) {
				newOrderId = objectArgs.get("newOrderCmb").toString();

			}
			if (objectArgs.get("tempTypePost") != null
					&& !objectArgs.get("tempTypePost").toString().equals("")) {
				tempTypePost = objectArgs.get("tempTypePost").toString();

			}
			if (objectArgs.get("orderDate") != null
					&& !objectArgs.get("orderDate").toString().equals("")) {
				orderDate = objectArgs.get("orderDate").toString();
			}

			if (objectArgs.get("Permenant") != null
					&& !objectArgs.get("Permenant").toString().equals("")) {
				Permenant = objectArgs.get("Permenant").toString();
			}
			// added by vaibhav tyagi:start
			if (StringUtility.getParameter("subjectCmb", request) != null
					&& !StringUtility.getParameter("subjectCmb", request)
					.toString().equals("")) {
				subjectSel = StringUtility.getParameter("subjectCmb", request)
				.toString();
			}
			logger.info("====> subjectSel in Service :: " + subjectSel);
			// added by vaibhav tyagi:end

			//added by Demolisher
			if (objectArgs.get("subFieldDeptId") != null) {
				subFieldDeptId = Long.parseLong(objectArgs.get("subFieldDeptId")
						.toString());
				logger.info("Sub Field Department :::::::"+subFieldDeptId);
			}


			// added by shailesh
			// subPostTypeTemp =
			// StringUtility.getParameter("postSubTypeCmbBoxTemp", request);
			// subPostTypePerm =
			// StringUtility.getParameter("postSubTypeCmbBoxPerm", request);
			logger.info("====> Permenant in Service :: " + Permenant);

			logger.info("====> officeCmb in Service :: " + officeId);
			logger.info("====> schemecmb :: " + billId);
			logger.info("====> noofpost :: " + noOfPost);
			logger.info("====> KhushalsorderId :: " + orderId);
			logger.info("====> desgnId:: " + desgnId);
			logger.info("====> orderDate :: " + orderDate);
			logger.info("====> tempTypePost :: " + tempTypePost);
			logger.info("====> newOrderCmb :: " + newOrderId);
			logger.info("====> newDate :: " + newOrderDate);
			logger.info("====> start data :: " + startDate);
			logger.info("====> end date :: " + endDate);
			logger.info("====> remarks in service:: " + remarks);
			logger.info("====> postType :: " + postType);
			logger.info("====> Khushal Pokar :: " + orderDate);
			logger.info("====> subPostType " + subPostTypeTemp);
			logger.info("====> subPostType " + subPostTypePerm);
			logger.info("====> subFieldDeptId " + subFieldDeptId);
			// ************************

			CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

			CmnLookupMst cmnLookupPostStatus = cmnLookupMstDAO.read(13L);

			OrderHeadPostmpgDAOImpl orderHeadPostMpgDAO = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class, serv.getSessionFactory());

			CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());

			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());

			OrgDesignationMstDao orgDesignationMstDao = new OrgDesignationMstDaoImpl(OrgDesignationMst.class, serv.getSessionFactory());

			PsrPostMpgDAOImpl postPsrMpgDao = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class, serv.getSessionFactory());

			OrgPostDetailsRltDao orgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());

			OrgDesignationMst desgnMst = orgDesignationMstDao.read(desgnId);

			logger.info("designation Name-----" + desgnMst.getDsgnName());

			CmnLanguageMst cmnLangMst = cmnLanguageMstDao.read(1L);

			CmnLocationMst cmnLocMst = cmnLocationMstDaoImpl.read(locId);

			OrderHeadMpgDAOImpl orderHeadDAO = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class, serv.getSessionFactory());

			// String billScheme=orderHeadDAO.getBillScheme(billId);
			// logger.info("----Bill Scheme"+billScheme);

			// String cmprColumn[]={"orderId","subheadId"};
			// Object cmprValues[]={orderId,billScheme};
			String cmprColumn[] = { "orderId" };
			Object cmprValues[] = { orderId };

			List orderHeadList = orderHeadDAO.getListByColumnAndValue(cmprColumn, cmprValues);

			HrPayOrderHeadMpg orderHeadMpg = new HrPayOrderHeadMpg();
			long orderHeadId = 0;
			IdGenerator idGen = new IdGenerator();
			logger.info("testing orderHeadList size--" + orderHeadList.size());

			if (orderHeadList.size() == 0) {
				logger.info("inside hrpayorderheadpostpg table");
				logger.info("::::::::::::::::::::::::");
				orderHeadMpg = new HrPayOrderHeadMpg();
				orderHeadId = idGen.PKGeneratorWODBLOC("HR_PAY_ORDER_HEAD_MPG",objectArgs);
				orderHeadMpg.setOrderHeadId(orderHeadId);
				orderHeadMpg.setOrderId(orderId);
				// orderHeadMpg.setSubheadId(billScheme);
				orderHeadMpg.setSubheadId(null);
				orderHeadMpg.setCreatedDate(new Date());
				orderHeadMpg.setOrgUserMstByCreatedBy(orgUserMstLoggedin);
				orderHeadMpg.setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
				orderHeadMpg.setTrnCounter(1);
				orderHeadDAO.create(orderHeadMpg);
				logger.info("data inserted ");
			} else {
				logger.info("Inside Else");

				orderHeadMpg = (HrPayOrderHeadMpg) orderHeadList.get(0);
				orderHeadId = orderHeadMpg.getOrderHeadId();
			}
			long postId = 0;
			long postDtlId = 0;
			long nextPsr = postPsrMpgDao.getNextPsrNo();
			logger.info("Next PSR No---" + nextPsr);
			long psrPostId = 0;
			long orderHeadPostId = 0;
			HrPayOrderMst hrPayOrderMst = null;
			OrgPostMst orgPostMaster = null;
			OrgPostDetailsRlt orgPostDtlRlt = null;
			HrPayPsrPostMpg postPsrMpg = null;
			HrPayOrderHeadPostMpg orderHeadPostmpg = null;

			CmnLookupMst cmnLookupMst = null;
			String CmnPId = "10001198129";
			String CmnTId = "10001198130";
			String CmnSId = "10001198155";

			/*
			 * //added by shailesh
			 * 
			 * String cmnProtectedId ="10001198159"; String cmnSurplusId
			 * ="10001198156";
			 * 
			 * String cmnAdochId ="10001198157"; String cmnStatutoryId
			 * ="10001198155"; String cmnHonoraryId ="10001198154"; String
			 * cmnContractId ="10001198153";
			 */
			logger.info("designation id is -----"+desgnMst.getDsgnCode());
			if (postType.equals(CmnPId) || postType.equals(CmnSId)) {
				logger.info("CmnPId---" + CmnPId);
				logger.info("CmnSId---" + CmnSId);

				for (int postCount = 1; postCount <= noOfPost; postCount++) {

					CmnlookupMstDAOImpl cmnlookupMstDAOImpl = new CmnlookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

					// added by shailesh
					cmnLookupMst = cmnlookupMstDAOImpl.read(Long.parseLong(postType));
					logger.info("cmnLookupMst ::" + cmnLookupMst);
					// cmnLookupMst=cmnlookupMstDAOImpl.read(Long.parseLong(subPostTypePerm));

					orgPostMaster = new OrgPostMst();
					postId = idGen.PKGeneratorWODBLOC("org_post_mst",objectArgs);
					// added by vaibhav tyagi:start
					FrmSubPostidMpg subjectPostMpg = new FrmSubPostidMpg();
					// added by vaibhav tyagi:end
					logger.info("postId in org_post_mst ::::: " + postId);

					orgPostMaster.setPostId(postId);
					orgPostMaster.setParentPostId(-1);
					orgPostMaster.setPostLevelId(1);
					orgPostMaster.setStartDate(StringUtility.convertStringToDate(startDate));
					logger.info("#########################END DATE IN AdminOrgPostDtlServiceImpl#########################: "+endDate);
					if (endDate != "") {
						orgPostMaster.setEndDate(StringUtility.convertStringToDate(endDate));
					}
					orgPostMaster.setCmnLookupMst(cmnLookupPostStatus);
					orgPostMaster.setDsgnCode(desgnCode);
					orgPostMaster.setActivateFlag(1);
					orgPostMaster.setCreatedDate(new Date());
					orgPostMaster.setOrgUserMstByCreatedBy(orgUserMstLoggedin);
					orgPostMaster.setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
					orgPostMaster.setLocationCode(String.valueOf(locId));

					// added by khushal
					orgPostMaster.setPostTypeLookupId(cmnLookupMst);
					// ended by khushal

					orgPostMstDaoImpl.create(orgPostMaster);

					// added by vaibhav tyagi:start
					subjectPostMpg.setPostID(postId);
					subjectPostMpg.setSubjectName(subjectSel);
					logger.info("subjectPostMpg.getSubjectName "+ subjectSel);
					logger.info("subjectPostMpg.getSubjectName "
							+ subjectPostMpg.getSubjectName());
					// orgPostMstDaoImpl.create(subjectPostMpg);
					adminOrgPostDtlDaoImpl1.submitSubject(subjectPostMpg);
					// added by vaibhav tyagi:end

					// #########################
					postPsrMpg = new HrPayPsrPostMpg();
					psrPostId = idGen.PKGeneratorWODBLOC("HR_PAY_POST_PSR_MPG",objectArgs);
					postPsrMpg.setPsrPostId(psrPostId);
					logger.info("psrPostId:::::::::::: "+ postPsrMpg.getPsrPostId());
					postPsrMpg.setPsrId(nextPsr);
					logger.info("nextPsr:::::::::::: " + postPsrMpg.getPsrId());
					postPsrMpg.setPostId(postId);
					logger.info("postId:::::::::::: " + postPsrMpg.getPostId());
					if (billId <= 0)
						postPsrMpg.setBillNo(null);
					else
						postPsrMpg.setBillNo(billId);
					postPsrMpg.setLoc_id(locId);
					postPsrMpgDao.create(postPsrMpg);

					// ##########################nextPsr
					orgPostDtlRlt = new OrgPostDetailsRlt();
					postDtlId = idGen.PKGeneratorWODBLOC("org_post_details_rlt", objectArgs);
					logger.info("PK For org_post_details_rlt******"+ postDtlId);
					orgPostDtlRlt.setPostDetailId(postDtlId);
					orgPostDtlRlt.setOrgPostMst(orgPostMaster);
					orgPostDtlRlt.setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
					orgPostDtlRlt.setOrgUserMstByCreatedBy(orgUserMstLoggedin);

					orgPostDtlRlt.setCmnLanguageMst(cmnLangMst);
					orgPostDtlRlt.setOrgDesignationMst(desgnMst);
					orgPostDtlRlt.setCreatedDate(new Date());
					orgPostDtlRlt.setPostName(desgnMst.getDsgnName().concat(String.valueOf(nextPsr)));
					orgPostDtlRlt.setPostShortName(desgnMst.getDsgnShrtName().concat(String.valueOf(nextPsr)));
					//orgPostDtlRlt.setSubFieldDeptId(subFieldDeptId);
					orgPostDtlRlt.setCmnLocationMst(cmnLocMst);
					// added by shailesh:start
					/*
					 * cmnLookupMst=cmnlookupMstDAOImpl.read(Long.parseLong(subPostTypePerm
					 * )); orgPostDtlRlt.setPostCategory(cmnLookupMst);
					 */// added by shailesh:end
					orgPostDetailsRltDao.create(orgPostDtlRlt);

					logger.info("PostDetailsRLT DAO Created");

					// ##################

					orderHeadPostmpg = new HrPayOrderHeadPostMpg();
					orderHeadPostId = idGen.PKGeneratorWODBLOC("HR_PAY_ORDER_HEAD_POST_MPG", objectArgs);
					orderHeadPostmpg.setOrderHeadPostId(orderHeadPostId);
					orderHeadPostmpg.setOrderHeadId(orderHeadId);
					orderHeadPostmpg.setOrgUserMstByCreatedBy(orgUserMstLoggedin);
					orderHeadPostmpg.setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
					orderHeadPostmpg.setPostId(postId);
					orderHeadPostmpg.setCreatedDate(new Date());

					orderHeadPostMpgDAO.create(orderHeadPostmpg);

					logger.info("orderHeadPostmpg DAO Created");

					HrPayOfficepostMpg hrOfficePostMpg = new HrPayOfficepostMpg();

					HrPayOfficePostMpgDAOImpl officePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());

					DdoOffice ddoOffice = new DdoOffice();
					DdoOfficeDAOImpl ddoOfficeDAOImpl = new DdoOfficeDAOImpl(DdoOffice.class, serv.getSessionFactory());
					ddoOffice = (DdoOffice) ddoOfficeDAOImpl.read(officeId);

					long officePostId = IDGenerateDelegate.getNextId("HR_PAY_OFFICEPOST_MPG", objectArgs);
					logger.info("generated officePostId is ===>"+ officePostId);
					logger.info("post id*****" + orgPostMaster.getPostId());
					logger.info("office id********* "+ ddoOffice.getDcpsDdoOfficeIdPk());

					hrOfficePostMpg.setOfficePostId(officePostId);
					hrOfficePostMpg.setDdoOffice(ddoOffice);
					hrOfficePostMpg.setOrgPostMstByPostId(orgPostMaster);

					hrOfficePostMpg.setStartDate(StringUtility.convertStringToDate(startDate));
					hrOfficePostMpg.setOrgUserMstByCreatedBy(orgUserMstLoggedin);
					hrOfficePostMpg.setCreatedDate(new Date());
					hrOfficePostMpg.setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
					officePostMpgDAOImpl.create(hrOfficePostMpg);

					logger.info("officePostMpg DAO Created");

					/*
					 * Long remarkId=0L; OrgPostMst orgPostMstPostId =
					 * (OrgPostMst) orgPostMstDaoImpl.read(postId); //Long
					 * remarkId=0L; HrPayRemarksMst hrPayRemarksMst = new
					 * HrPayRemarksMst(); remarkId =
					 * idGen.PKGeneratorWODBLOC("hr_pay_remarks_mst"
					 * ,objectArgs); hrPayRemarksMst.setRemarkId(remarkId);
					 * hrPayRemarksMst.setRemarksDate(new Date());
					 * hrPayRemarksMst.setRemarks(remarks);
					 * hrPayRemarksMst.setOrgPostMst(orgPostMstPostId);
					 * hrPayRemarksMst
					 * .setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
					 * hrPayRemarksMst
					 * .setOrgUserMstByCreatedBy(orgUserMstLoggedin) ;
					 * hrPayRemarksMst.setCreatedDate(new Date());
					 * 
					 * HrPayRemarksMstDAOImpl hrPayRemarksMstDAOImpl=new
					 * HrPayRemarksMstDAOImpl
					 * (HrPayRemarksMst.class,serv.getSessionFactory());
					 * hrPayRemarksMstDAOImpl.create(hrPayRemarksMst);
					 */

					nextPsr++;

				}
				msg = 0;
			} else if (postType.equals(CmnTId)) {
				logger.info("CmnPId---" + CmnPId);

				if (tempTypePost.equals("3")) {
					logger.info("tempTypePost==3" + tempTypePost);

					for (int postCount = 1; postCount <= noOfPost; postCount++) {

						CmnlookupMstDAOImpl cmnlookupMstDAOImpl = new CmnlookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
						// edited by shailesh
						cmnLookupMst = cmnlookupMstDAOImpl.read(Long.parseLong(postType));
						// cmnLookupMst=cmnlookupMstDAOImpl.read(Long.parseLong(subPostTypeTemp));

						orgPostMaster = new OrgPostMst();
						postId = idGen.PKGeneratorWODBLOC("org_post_mst",objectArgs);
						
						//added by abhishek 
						FrmSubPostidMpg subjectPostMpg = new FrmSubPostidMpg();
						logger.info("postId in org_post_mst ::::: " + postId);
						
						orgPostMaster.setPostId(postId);
						orgPostMaster.setParentPostId(-1);
						orgPostMaster.setPostLevelId(1);
						orgPostMaster.setStartDate(StringUtility.convertStringToDate(startDate));
						/*commented and edited by samadhan to save end date of temp posts
						 *orgPostMaster.setEndDate(StringUtility.convertStringToDate(tempEndDate)); 
						 */
						orgPostMaster.setEndDate(StringUtility.convertStringToDate(endDate));
						orgPostMaster.setCmnLookupMst(cmnLookupPostStatus);
						orgPostMaster.setDsgnCode(desgnCode);
						orgPostMaster.setActivateFlag(1);
						orgPostMaster.setCreatedDate(new Date());
						orgPostMaster.setOrgUserMstByCreatedBy(orgUserMstLoggedin);
						orgPostMaster.setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
						orgPostMaster.setLocationCode(String.valueOf(locId));
						orgPostMaster.setPostTypeLookupId(cmnLookupMst);

						orgPostMstDaoImpl.create(orgPostMaster);

						logger.info("orgPostMaster dao created"+ orgPostMaster.getPostId() + "--"+ orgPostMaster.getDsgnCode().toString());
						
						//added by abhishek 
						subjectPostMpg.setPostID(postId);
						subjectPostMpg.setSubjectName(subjectSel);
						logger.info("subjectPostMpg.getSubjectName "+ subjectSel);
						logger.info("subjectPostMpg.getSubjectName "
								+ subjectPostMpg.getSubjectName());
						// orgPostMstDaoImpl.create(subjectPostMpg);
						adminOrgPostDtlDaoImpl1.submitSubject(subjectPostMpg);
						
						// #########################
						postPsrMpg = new HrPayPsrPostMpg();
						psrPostId = idGen.PKGeneratorWODBLOC("HR_PAY_POST_PSR_MPG", objectArgs);
						postPsrMpg.setPsrPostId(psrPostId);
						postPsrMpg.setPsrId(nextPsr);
						postPsrMpg.setPostId(postId);
						if (billId <= 0)
							postPsrMpg.setBillNo(null);
						else
							postPsrMpg.setBillNo(billId);
						postPsrMpg.setLoc_id(locId);
						postPsrMpgDao.create(postPsrMpg);

						logger.info("HR_PAY_POST_PSR_MPG dao created");

						// ##########################nextPsr
						orgPostDtlRlt = new OrgPostDetailsRlt();
						postDtlId = idGen.PKGeneratorWODBLOC("org_post_details_rlt", objectArgs);
						orgPostDtlRlt.setPostDetailId(postDtlId);
						orgPostDtlRlt.setOrgPostMst(orgPostMaster);
						orgPostDtlRlt.setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
						orgPostDtlRlt.setOrgUserMstByCreatedBy(orgUserMstLoggedin);

						orgPostDtlRlt.setCmnLanguageMst(cmnLangMst);
						orgPostDtlRlt.setOrgDesignationMst(desgnMst);
						orgPostDtlRlt.setCreatedDate(new Date());
						orgPostDtlRlt.setPostName(desgnMst.getDsgnName().concat(String.valueOf(nextPsr)));
						orgPostDtlRlt.setPostShortName(desgnMst.getDsgnShrtName().concat(String.valueOf(nextPsr)));

						// edited by shailesh
						/*
						 * cmnLookupMst=cmnlookupMstDAOImpl.read(Long.parseLong(subPostTypeTemp
						 * )); orgPostDtlRlt.setPostCategory(cmnLookupMst);
						 */// edited by shailesh:end
						orgPostDtlRlt.setCmnLocationMst(cmnLocMst);

						orgPostDetailsRltDao.create(orgPostDtlRlt);

						logger.info("org_post_details_rlt dao created");

						// ##################

						orderHeadPostmpg = new HrPayOrderHeadPostMpg();
						orderHeadPostId = idGen.PKGeneratorWODBLOC("HR_PAY_ORDER_HEAD_POST_MPG", objectArgs);
						orderHeadPostmpg.setOrderHeadPostId(orderHeadPostId);
						orderHeadPostmpg.setOrderHeadId(orderHeadId);
						orderHeadPostmpg.setOrgUserMstByCreatedBy(orgUserMstLoggedin);
						orderHeadPostmpg.setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
						orderHeadPostmpg.setPostId(postId);
						orderHeadPostmpg.setCreatedDate(new Date());

						orderHeadPostMpgDAO.create(orderHeadPostmpg);

						logger.info("HR_PAY_ORDER_HEAD_POST_MPG dao created");

						HrPayOfficepostMpg hrOfficePostMpg = new HrPayOfficepostMpg();

						HrPayOfficePostMpgDAOImpl officePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());

						DdoOffice ddoOffice = new DdoOffice();
						DdoOfficeDAOImpl ddoOfficeDAOImpl = new DdoOfficeDAOImpl(DdoOffice.class, serv.getSessionFactory());
						ddoOffice = (DdoOffice) ddoOfficeDAOImpl.read(officeId);

						long officePostId = IDGenerateDelegate.getNextId("HR_PAY_OFFICEPOST_MPG", objectArgs);
						logger.info("generated officePostId is ===>"+ officePostId);
						logger.info("post id*****" + orgPostMaster.getPostId());
						logger.info("office id********* "+ ddoOffice.getDcpsDdoOfficeIdPk());

						hrOfficePostMpg.setOfficePostId(officePostId);
						hrOfficePostMpg.setDdoOffice(ddoOffice);
						hrOfficePostMpg.setOrgPostMstByPostId(orgPostMaster);
						hrOfficePostMpg.setStartDate(StringUtility.convertStringToDate(startDate));
						hrOfficePostMpg.setOrgUserMstByCreatedBy(orgUserMstLoggedin);
						hrOfficePostMpg.setCreatedDate(new Date());
						hrOfficePostMpg.setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
						officePostMpgDAOImpl.create(hrOfficePostMpg);

						logger.info("HR_PAY_OFFICEPOST_MPG dao created");
						// OrgPostMstDaoImpl orgPostMstDaoImpl = new
						// OrgPostMstDaoImpl(OrgPostMst.class,
						// serv.getSessionFactory());
						OrgPostMst orgPostMstPostId = (OrgPostMst) orgPostMstDaoImpl.read(postId);

						/*
						 * Long remarkId=0L; HrPayRemarksMst hrPayRemarksMst =
						 * new HrPayRemarksMst(); remarkId =
						 * idGen.PKGeneratorWODBLOC
						 * ("hr_pay_remarks_mst",objectArgs);
						 * hrPayRemarksMst.setRemarkId(remarkId);
						 * hrPayRemarksMst.setRemarksDate(new Date());
						 * hrPayRemarksMst.setRemarks(remarks);
						 * hrPayRemarksMst.setOrgPostMst(orgPostMstPostId);
						 * hrPayRemarksMst
						 * .setOrgPostMstByCreatedByPost(orgPostMstLoggedIn);
						 * hrPayRemarksMst
						 * .setOrgUserMstByCreatedBy(orgUserMstLoggedin) ;
						 * hrPayRemarksMst.setCreatedDate(new Date());
						 * 
						 * HrPayRemarksMstDAOImpl hrPayRemarksMstDAOImpl=new
						 * HrPayRemarksMstDAOImpl
						 * (HrPayRemarksMst.class,serv.getSessionFactory());
						 * hrPayRemarksMstDAOImpl.create(hrPayRemarksMst);
						 */

						nextPsr++;

					}
					msg = 0;
				} else if (tempTypePost.equals("4")) {

					logger.info("Inside tempTypePost==4" + noOfPost);
					for (int postCount = 1; postCount <= noOfPost; postCount++) {

						OrderMstDAOImpl ordermstDaoImpl = new OrderMstDAOImpl(HrPayOrderMst.class, serv.getSessionFactory());
						hrPayOrderMst = ordermstDaoImpl.read(Long.parseLong(newOrderId));
						hrPayOrderMst.setOrderDate(StringUtility.convertStringToDate(newOrderDate));
						hrPayOrderMst.setUpdatedDate(new Date());
						hrPayOrderMst.setOrgPostMstByUpdatedByPost(orgPostMstLoggedIn);
						hrPayOrderMst.setOrgUserMstByUpdatedBy(orgUserMstLoggedin);
						ordermstDaoImpl.update(hrPayOrderMst);
					}
					msg = 1;
				}

			}

			// started by manish
			long scaleId = 1;
			GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(HrEisScaleMst.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			HrEisScaleMst hrEisScaleMst = (HrEisScaleMst) genDao.read(scaleId);
			CmnLookupMstDAOImpl cmnDao = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			GradeMasterDAO gradeDao = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
			//CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");
			//long parentLocId = cmnLocationMst.getParentLocId();

			/*CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap
			.get("locationVO");*/
			//added by vaibhav tyagi : start
			DdoOffice ddoOffice=new DdoOffice();
			DdoOfficeDAOImpl ddoOfficeDAOImpl = new DdoOfficeDAOImpl(DdoOffice.class,serv.getSessionFactory());
			ddoOffice =(DdoOffice) ddoOfficeDAOImpl.read(officeId);
			CmnLocationMstDaoImpl cmnLocMstLevelOne = new CmnLocationMstDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocMstLevelOne.read(ddoOffice.getLocId());
			//added by vaibhav tyagi : end
			long parentLocId = cmnLocationMst.getParentLocId();
			logger.info("designation id is -----"+desgnMst.getDsgnCode());
			logger.info("parentLocId--" + parentLocId);

			AdminOrgPostDtlDaoImpl adminOrgPostDtlDaoImpl = new AdminOrgPostDtlDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			List<MstPayrollDesignationMst> lst = adminOrgPostDtlDaoImpl.getMstDcpsDsgnObject(parentLocId, desgnMst.getDsgnId());
			logger.info("Size of MstPayrollDesignationMst List --"+ lst.size());
			MstPayrollDesignationMst mst = new MstPayrollDesignationMst();
			if (lst != null && lst.size() > 0)
				mst = lst.get(0);

			logger.info("Size of MstPayrollDesignationMst List--"+ mst.getDesigId() + "--cadreTypeId--"+ mst.getCadreTypeId());
			long cadreTypeId = mst.getCadreTypeId();
			long grpName = Long.parseLong(new Long(cadreTypeId).toString());
			logger.info("grpName--" + grpName);
			CmnLookupMst loonkupGrd = cmnDao.read(grpName);
			genDao = new GenericDaoHibernateImpl(OrgGradeMst.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			logger.info("designation id is -----"+desgnMst.getDsgnCode());
			long gradeId = Long.parseLong(loonkupGrd.getLookupShortName().toString());

			logger.info("gradeId--" + gradeId);
			OrgGradeMst orgGradeMst = (OrgGradeMst) genDao.read(gradeId);
			GradDesgScaleMapDAO sgdDao = new GradDesgScaleMapDAO(HrEisSgdMpg.class, serv.getSessionFactory());
			long dbId = Long.parseLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = (CmnDatabaseMst) cmnDatabaseMstDaoImpl.read(Long.valueOf(dbId));
			long loggedInpostId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);
			long loggedInUser = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMst loggedInOrgUserMst = orgUserMstDaoImpl.read(loggedInUser);

			logger.info("loggedInpostId--" + loggedInpostId);
			logger.info("loggedInUser--" + loggedInUser);
			logger.info("designation id is -----"+desgnMst.getDsgnCode());
			HrEisGdMpg gdMpg = null;
			HrEisSgdMpg sgdMpg = null;
			List<HrEisGdMpg> gdList = gradeDao.getDuplicateData(gradeId,desgnMst.getDsgnId(), cmnLocationMst.getLocId());
			if (gdList != null && gdList.size() > 0) {
				gdMpg = gdList.get(0);

				long gdId = gdMpg.getGdMapId();
				logger.info("gdId--" + gdId);

				// List<HrEisSgdMpg> sgdMpglist =
				// sgdDao.getScaleGradeDesgMasterData( gdId,
				// scaleId,cmnLocationMst.getLocId());
				// List<HrEisSgdMpg> sgdMpglist =
				// sgdDao.getScaleGradeDesgMasterData( gdId, scaleId);
				List<HrEisSgdMpg> sgdMpglist = sgdDao.getScaleGradeDesgMasterData(gdId, scaleId,cmnLocationMst.getLocId());
				logger.info("designation id is -----"+desgnMst.getDsgnCode());
				if (sgdMpglist != null && sgdMpglist.size() > 0) {
					sgdMpg = sgdMpglist.get(0);
					logger.info("sgdMpg Exissts-----" + sgdMpg.getSgdMapId());

				} else {
					// insert in sgd mapping
					sgdMpg = new HrEisSgdMpg();
					long sgdId = idGen.PKGenerator("HR_EIS_SGD_MPG", objectArgs);
					sgdMpg.setSgdMapId(sgdId);
					sgdMpg.setCmnDatabaseMst(cmnDatabaseMst);
					// sgdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
					sgdMpg.setCmnLocationMst(cmnLocationMst);
					sgdMpg.setCreatedDate(new java.util.Date());
					sgdMpg.setHrEisGdMpg(gdMpg);
					sgdMpg.setHrEisScaleMst(hrEisScaleMst);
					sgdMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
					sgdMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					long sgdMpgCreated = sgdDao.create(sgdMpg);
					logger.info(" HR_EIS_SGD_MPG created " + sgdMpgCreated);
				}

			} else {
				// insert gdmpg
				gdMpg = new HrEisGdMpg();
				long gdId = idGen.PKGenerator("HR_EIS_GD_MPG", objectArgs);
				gdMpg.setCmnDatabaseMst(cmnDatabaseMst);
				// gdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
				gdMpg.setCmnLocationMst(cmnLocationMst);

				gdMpg.setCreatedDate(new java.util.Date());
				gdMpg.setGdMapId(gdId);
				logger.info("designation id is -----"+desgnMst.getDsgnCode());
				gdMpg.setOrgDesignationMst(desgnMst);
				gdMpg.setOrgGradeMst(orgGradeMst);
				gdMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				gdMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				genDao = new GenericDaoHibernateImpl(HrEisGdMpg.class);
				genDao.setSessionFactory(serv.getSessionFactory());
				genDao.create(gdMpg);
				logger.info("gd Created " + genDao.create(gdMpg)+" desig is is ****************"+gdMpg.getOrgDesignationMst().getDsgnCode());

				// insert in sgd mapping

				sgdMpg = new HrEisSgdMpg();
				long sgdId = idGen.PKGenerator("HR_EIS_SGD_MPG", objectArgs);
				sgdMpg.setSgdMapId(sgdId);
				sgdMpg.setCmnDatabaseMst(cmnDatabaseMst);
				// sgdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
				sgdMpg.setCmnLocationMst(cmnLocationMst);
				sgdMpg.setCreatedDate(new java.util.Date());
				sgdMpg.setHrEisGdMpg(gdMpg);
				sgdMpg.setHrEisScaleMst(hrEisScaleMst);
				sgdMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				sgdMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				long sgdMpgCreatedTwo = sgdDao.create(sgdMpg);
				logger.info(" HR_EIS_SGD_MPG created " + sgdMpgCreatedTwo);
			}

			// ended by manish

			if (msg == 1)
				objectArgs.put("MESSAGECODE", Integer.valueOf(0x493e6));
			else
				objectArgs.put("MESSAGECODE", Integer.valueOf(0x493e5));

			resObj = addAdminOrgPostDtl(objectArgs);
			resObj.setViewName("adminCreatePostDtls");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			logger.error("Admin Screen Submitting A Post Error", ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}

	public ResultObject editAdminOrgPostDtl(Map objectArgs) {
		// ResultObject resObj = new ResultObject(0);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			System.out
			.println("===========editAdminOrgPostDtl=====called=================");
			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());
			OrgPostMstDao orgPostMstDao = new OrgPostMstDaoImpl(
					OrgPostMst.class, serv.getSessionFactory());
			OrgPostDetailsRltDao orgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(
					OrgPostDetailsRlt.class, serv.getSessionFactory());
			long locId = StringUtility.convertToLong(
					loginMap.get("locationId").toString()).longValue();
			resObj = addAdminOrgPostDtl(objectArgs);
			Long reqId = Long.valueOf(objectArgs.get("reqId").toString());
			logger.info((new StringBuilder("Post Id in Edit Mode is:---->>>>"))
					.append(reqId).toString());
			PsrPostMpgDAOImpl postMpgDAOImpl = new PsrPostMpgDAOImpl(
					HrPayPsrPostMpg.class, serv.getSessionFactory());
			if (reqId != null) {
				List psrList = postMpgDAOImpl
				.getPsrIdbyLocId(reqId.longValue());
				HrPayPsrPostMpg hrPayPsrPostmpg = new HrPayPsrPostMpg();
				for (int i = 0; i < psrList.size(); i++) {
					hrPayPsrPostmpg = new HrPayPsrPostMpg();
					Object rowList[] = (Object[]) psrList.get(i);
					long psrId = Long.parseLong(rowList[0].toString());
					hrPayPsrPostmpg.setPsrId(psrId);
					long psrpostmpgId = Long.parseLong(rowList[1].toString());
					hrPayPsrPostmpg.setPsrPostId(psrpostmpgId);
				}

				objectArgs.put("hrPayPsrPostMpg", hrPayPsrPostmpg);
			}
			if (reqId != null) {
				List lstParentPost = new ArrayList();
				OrgPostMst orgPostMst = (OrgPostMst) orgPostMstDao.read(reqId);
				OrgPostDetailsRlt orgPostDetailsRlt_en = orgPostDetailsRltDao
				.getPostDetailsRltByPostIdAndLangId(reqId, Long
						.valueOf(1L));
				List lstParentDesg = orgPostDetailsRltDao
				.getListByColumnAndValue("orgPostMst.postId", Long
						.valueOf(orgPostMst.getParentPostId()));
				OrgPostDetailsRlt orgPostDetailsRlt = new OrgPostDetailsRlt();
				if (lstParentDesg != null && lstParentDesg.size() > 0) {
					orgPostDetailsRlt = (OrgPostDetailsRlt) lstParentDesg
					.get(0);
					lstParentPost = orgPostDetailsRltDao
					.getListByColumnAndValue(
							"orgDesignationMst.dsgnId", Long
							.valueOf(orgPostDetailsRlt
									.getOrgDesignationMst()
									.getDsgnId()));
				}
				if (orgPostDetailsRlt_en.getCmnBranchMst() != null)
					logger.info((new StringBuilder(
					"orgPostDetailsRlt_en============")).append(
							orgPostDetailsRlt_en.getCmnBranchMst()
							.getBranchId()).toString());
				// added by ravysh
				// HrPayOfficePostMpgDAOImpl officePostMpgDAOImpl = new
				// HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class,
				// serv.getSessionFactory());
				//   
				// List<HrPayOfficepostMpg> hrOfficePostMpgList =
				// officePostMpgDAOImpl.getListByColumnAndValue("orgPostMstByPostId.postId",
				// reqId);
				// if(hrOfficePostMpgList.size()>0)
				// objectArgs.put("officePostMpg", hrOfficePostMpgList.get(0));
				// ended by ravysh
				objectArgs.put("parentPostDetailsRlt", orgPostDetailsRlt);
				objectArgs.put("lstParentPost", lstParentPost);
				objectArgs.put("orgPostMst", orgPostMst);
				objectArgs.put("orgPostDetailsRlt_en", orgPostDetailsRlt_en);
			}
			objectArgs.put("langId", Long.valueOf(langId));
			objectArgs.put("flag", "edit");
			resObj.setViewName("adminCreatePostDtls");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger
			.error(
					"Admin Screen Editing A Post Error In AdminOrgPOstDtlSrvcImpl",
					ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}

	public ResultObject deleteAdminOrgPostDtl(Map objectArgs) {
		// ResultObject resObj = new ResultObject(0);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			OrgPostMstDao orgPostMstDao = new OrgPostMstDaoImpl(
					OrgPostMst.class, serv.getSessionFactory());
			OrgPostDetailsRltDao orgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(
					OrgPostDetailsRlt.class, serv.getSessionFactory());
			String deleteScreenIdArray[] = (String[]) objectArgs
			.get("deletedata");
			if (deleteScreenIdArray != null) {
				for (int i = 0; i < deleteScreenIdArray.length; i++) {
					logger
					.info((new StringBuilder(
							"deleteScreenIdArray[i] : ")).append(
									deleteScreenIdArray[i]).toString());
					OrgPostMst orgPostMst = (OrgPostMst) orgPostMstDao
					.read(Long.valueOf(deleteScreenIdArray[i]));
					if (orgPostMst != null) {
						OrgPostDetailsRlt orgPostDetailsRlt_en = orgPostDetailsRltDao
						.getPostDetailsRltByPostIdAndLangId(Long
								.valueOf(orgPostMst.getPostId()), Long
								.valueOf(1L));
						orgPostMst.setActivateFlag(0L);
						orgPostMstDao.update(orgPostMst);
					}
				}

			}
			resObj = showAdminOrgPostDtl(objectArgs);
			resObj.setViewName("showAdminPostDtls");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger
			.error(
					"Admin Screen Deleteing A Post Error In AdminOrgPOstDtlSrvcImpl",
					ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}

	public ResultObject getParentPostList(Map objectArgs) {
		// ResultObject resObj = new ResultObject(0);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			logger.info("You are in fetch Parent Post:-");
			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			UserPostDAO userPostDAO = new UserPostDAO(UserPostCustomVO.class,
					serv.getSessionFactory());
			Map voToSrvcMap = (Map) (Map) objectArgs.get("voToServiceMap");
			logger.info((new StringBuilder("The Designation Id is:-")).append(
					voToSrvcMap.get("designationId").toString()).toString());
			long dsgnId = Long.parseLong(voToSrvcMap.get("designationId")
					.toString());
			StringBuffer sbParentPost = new StringBuffer();
			List lstParentPost = userPostDAO.getParentPosts(dsgnId);
			if (lstParentPost != null || lstParentPost.size() > 0) {
				logger.info((new StringBuilder("Parent Post List Size is:-"))
						.append(lstParentPost.size()).toString());
				for (Iterator iterator = lstParentPost.iterator(); iterator
				.hasNext(); sbParentPost.append("</parentPostMapping>")) {
					OrgPostDetailsRlt orgPostDetailsRlt = (OrgPostDetailsRlt) iterator
					.next();
					sbParentPost.append("<parentPostMapping>");
					sbParentPost.append("<parentPostId>").append(
							orgPostDetailsRlt.getOrgPostMst().getPostId())
							.append("</parentPostId>");
					sbParentPost.append("<parentPostName>").append(
							orgPostDetailsRlt.getPostName()).append(
							"</parentPostName>");
				}

			}
			String parentPostMapping = (new AjaxXmlBuilder()).addItem(
					"ajax_key", sbParentPost.toString()).toString();
			logger.info((new StringBuilder("The ParentPostMappin String is:-"))
					.append(parentPostMapping).toString());
			objectArgs.put("ajaxKey", parentPostMapping);
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
			resObj.setViewName("ajaxData");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resObj;
	}

	public ResultObject GetPostfromOrder(Map objectArgs) {
		logger.info("*********GetPostfromOrder*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs
		.get("requestObj");
		HttpSession session = request.getSession();
		Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		// ResultObject resultObject = new ResultObject(0);
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map voToService = (Map) (Map) objectArgs.get("voToServiceMap");
		long orderId = 0L;
		orderId = Long.parseLong((String) voToService.get("orderId"));
		String editFlag = (String) voToService.get("editFlag");
		Long langId = Long.valueOf(Long.parseLong(loginDetailsMap.get("langId")
				.toString()));
		List userpostrltSet = new ArrayList();
		OrderHeadPostmpgDAOImpl orderHeadPostMpgDAO = new OrderHeadPostmpgDAOImpl(
				HrPayOrderHeadPostMpg.class, serv.getSessionFactory());
		long userId = Long.parseLong(loginDetailsMap.get("userId").toString());
		long loc_id = Long.parseLong(orderHeadPostMpgDAO.getLocationCode(
				userId, langId).get(0).toString());
		OrderHeadMpgDAOImpl headMasterDAO = new OrderHeadMpgDAOImpl(
				HrPayOrderHeadMpg.class, serv.getSessionFactory());
		userpostrltSet = orderHeadPostMpgDAO.getAllUserPostRltDatabyOrder(
				loc_id, orderId);
		Iterator it = userpostrltSet.iterator();
		StringBuffer propertyList = new StringBuffer();
		for (int i = 0; i < userpostrltSet.size(); i++) {
			Object rowList[] = (Object[]) userpostrltSet.get(i);
			long postId = Long.parseLong(rowList[0].toString());
			String post = rowList[1].toString();
			propertyList.append("<post-mapping>");
			propertyList.append("<postId>").append(postId).append("</postId>");
			propertyList.append("<post>").append("<![CDATA[").append(post)
			.append("]]>").append("</post>");
			propertyList.append("</post-mapping>");
		}

		Map result = new HashMap();
		String postData = (new AjaxXmlBuilder()).addItem("ajax_key",
				propertyList.toString()).toString();
		result.put("ajaxKey", postData);
		resultObject.setResultValue(result);
		resultObject.setViewName("ajaxData");
		logger.info((new StringBuilder("After Service Called.........\n"))
				.append(postData).toString());
		return resultObject;
	}

	public ResultObject chkpsrLocationunique(Map objectArgs) {
		logger.info("*********chkpsrLocationunique*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs
		.get("requestObj");
		HttpSession session = request.getSession();
		Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		long psrId = 0L;
		long location_Id = 0L;
		long ppMapId = 0L;
		Map voToService = (Map) (Map) objectArgs.get("voToServiceMap");
		psrId = Long.parseLong((String) voToService.get("psr"));
		location_Id = Long.parseLong((String) voToService.get("location_Id"));
		ppMapId = Long.parseLong((String) voToService.get("ppMapId"));
		logger.info((new StringBuilder("Bill Head Mapping Id is-------->>>"))
				.append(ppMapId).toString());
		Long langId = Long.valueOf(Long.parseLong(loginDetailsMap.get("langId")
				.toString()));
		PsrPostMpgDAOImpl postMpgDAOImpl = new PsrPostMpgDAOImpl(
				HrPayPsrPostMpg.class, serv.getSessionFactory());
		List mpgDataList = postMpgDAOImpl.chkpsrLocationunique(psrId,
				location_Id, ppMapId);
		StringBuffer propertyList = new StringBuffer();
		logger.info((new StringBuilder("Mapping List Size is----->>")).append(
				mpgDataList.size()).toString());
		propertyList.append("<pp-mapping>");
		if (mpgDataList != null && mpgDataList.size() > 0)
			propertyList.append("<mpgFlag>").append("<![CDATA[").append(
					mpgDataList.size()).append("]]>").append("</mpgFlag>");
		else
			propertyList.append("<mpgFlag>").append("<![CDATA[").append(-1)
			.append("]]>").append("</mpgFlag>");
		propertyList.append("</pp-mapping>");
		String mpgdata = (new AjaxXmlBuilder()).addItem("ajax_key",
				propertyList.toString()).toString();
		objectArgs.put("ajaxKey", mpgdata);
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("ajaxData");
		logger.info((new StringBuilder("After Service Called.........\n"))
				.append(mpgdata).toString());
		return resultObject;
	}

	// added by khushal

	public ResultObject getOrderDate(Map objectArgs) {
		long orderId = 0;
		logger.info("*********getOrderDate*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs
		.get("requestObj");
		HttpSession session = request.getSession();
		Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		Map voToService = (Map) (Map) objectArgs.get("voToServiceMap");
		orderId = Long.parseLong((String) voToService.get("orderId"));
		logger.info("Khushal orderId is" + orderId);
		OrderMstDAOImpl ordermstDaoImpl = new OrderMstDAOImpl(
				HrPayOrderMst.class, serv.getSessionFactory());
		Date orderDate = (Date) ordermstDaoImpl.getOrderDate(orderId).get(0);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String newDate = sdf.format(orderDate);

		StringBuffer getDate = new StringBuffer();
		getDate.append("<getOldOrderDate>");
		getDate.append("<orderDate>").append(newDate).append("</orderDate>");
		getDate.append("</getOldOrderDate>");
		Map map = new HashMap();
		String getOldOrderDate = new AjaxXmlBuilder().addItem("ajax_key",
				getDate.toString()).toString();
		map.put("ajaxKey", getOldOrderDate);
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		resultObject.setResultValue(map);
		resultObject.setViewName("ajaxData");
		logger.info(" SERVICE COMPLETE :");
		return resultObject;
	}

	// end by khushal

	// added by abhilash for post search

	public ResultObject fillPostDtls(Map objectArgs) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs
		.get("requestObj");
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");

			logger
			.info("===========addAdminOrgPostDtl=====called=============starts====");

			OrgDesignationMstDao orgDesignationMstDao = new OrgDesignationMstDaoImpl(
					OrgDesignationMst.class, serv.getSessionFactory());
			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			long langId = Long.parseLong(loginMap.get("langId").toString());
			long locId = Long.parseLong(loginMap.get("locationId").toString());
			long loggedInPostId = Long.parseLong(loginMap.get("primaryPostId")
					.toString());

			long designationId = (StringUtility.getParameter("dsgnId", request) != null
					&& !(StringUtility.getParameter("dsgnId", request)
							.equals("")) ? Long.parseLong(StringUtility
									.getParameter("dsgnId", request)) : 0);
			logger.info("for post details search dsgnId is ====="
					+ designationId);

			long billId = 0;
			String lPostname = "";
			String Psr = "";
			String Bill = "";
			String Dsgn = "";
			String empId = "";
			String PostName = "";
			String empFullName = "";
			String postType = "";
			String dsgn = "";

			if (!StringUtility.getParameter("billGrpId", request).equals("")
					&& StringUtility.getParameter("billGrpId", request) != null)
				billId = Long.valueOf(StringUtility.getParameter("billGrpId",
						request).toString());
			logger.info("for post details search billId is =====" + billId);

			DcpsCommonDAOImpl commonDao = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			// code changed by Ankit Bhatt for DDO Code
			PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, serv
					.getSessionFactory());
			List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
			OrgDdoMst ddoMst = null;
			if (ddoList != null && ddoList.size() > 0) {
				ddoMst = ddoList.get(0);
			}
			// ended
			String ddoCode = null;
			if (ddoMst != null)
				ddoCode = ddoMst.getDdoCode();

			DdoInfoDAO ddoInfoDAO = new DdoInfoDAOImpl(null, serv
					.getSessionFactory());
			OrgDdoMst lObjOrgDdoMst = ddoInfoDAO.getDdoInformation(ddoCode);
			Long lLngFieldDept = Long.parseLong(lObjOrgDdoMst.getHodLocCode());
			AdminOrgPostDtlDaoImpl adminDao = new AdminOrgPostDtlDaoImpl(
					MstDcpsDesignation.class, serv.getSessionFactory());
			// List<OrgDesignationMst> desgList = adminDao.getActiveDesig(
			// lLngFieldDept);

			List<OrgDesignationMst> desgList = adminDao
			.getActiveDsgnList(locId);
			logger.info("desgList size in fillPostDtls **************"
					+ desgList.size());
			objectArgs.put("Designation", desgList);

			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			logger.info("billList size in fillPostDtls **************"
					+ billList.size());

			OrderMstDAOImpl ordermstDaoImpl = new OrderMstDAOImpl(
					HrPayOrderMst.class, serv.getSessionFactory());
			// List postNameListWithIds =
			// adminOrgPostDtlDao.getEmployeeDetailsListFromDsgnIdForPostDtls(designationId,locId,billId);

			List postNameListWithIds = adminOrgPostDtlDao
			.getPostNameForDisplayWithSearch(locId, PostName, Psr,
					Bill, dsgn, designationId, billId);
			UserPostCustomVO customVO = new UserPostCustomVO();
			List post = new ArrayList();

			Map voToService = (Map) objectArgs.get("voToServiceMap");
			logger.info("voToService in fillPostDtls **************"
					+ voToService);

			UserPostDAO postDAO = new UserPostDAO(UserPostCustomVO.class, serv
					.getSessionFactory());
			List postNameList = new ArrayList();
			postNameList = postDAO.getPostNameForDisplayWithSearch(locId,
					PostName, Psr, Bill, dsgn);

			logger.info("postNameList size is in fillPostDtls: "
					+ postNameList.size());

			String postLookupId = "";
			String permenantlookupId = "10001198129";
			String temparerylookupId = "10001198130";
			String statutorylookupId = "10001198155";

			if (designationId > 0 || billId > 0) {

				if (postNameListWithIds != null) {
					for (int i = 0; i < postNameListWithIds.size(); i++) {
						customVO = new UserPostCustomVO();

						Object rowList[] = (Object[]) postNameListWithIds
						.get(i);

						String postName = rowList[0].toString();
						// customVO.setPostname(postName);

						if (rowList[6] != null
								&& !(rowList[6].toString().trim()).equals("")) {
							postLookupId = rowList[6].toString();
						}
						if (postLookupId.equals("10001198129")) {
							postName = postName.concat("P");
						} else if (postLookupId.equals("10001198130")) {
							postName = postName.concat("T");
						} else if (postLookupId.equals("10001198155")) {
							postName = postName.concat("S");
						} else {
							postName = postName;
						}
						customVO.setPostname(postName);

						long postId = Long.parseLong(rowList[1].toString());
						customVO.setPostId(postId);

						if (rowList[2] != null
								&& !(rowList[2].toString().trim()).equals("")) {
							empFullName = rowList[2].toString();
						} else {
							empFullName = "VACANT";
						}
						customVO.setEmpFullName(empFullName);

						String dsgnName = rowList[3].toString();
						if (rowList[3] != null
								&& !(rowList[3].toString().trim()).equals("")) {
							customVO.setDsgnname(dsgnName);
						}
						String BillNo = " ";
						String PsrNo = " ";

						if (rowList[4] != null) {
							PsrNo = rowList[4].toString();
						}
						customVO.setPsrNo(PsrNo);

						if (rowList[5] != null) {
							BillNo = rowList[5].toString();
						}
						customVO.setBillNo(BillNo);

						logger.info("===> BillNo :: " + BillNo);
						if (rowList[7] != null
								&& !(rowList[7].toString().trim()).equals("")) {
							postType = rowList[7].toString();
						} else {
							postType = "VACANT";
						}
						logger.info("===> postType :: " + postType);
						customVO.setPostType(postType);
						post.add(customVO);
						logger.info("post in if:::::::: " + post.size());
					}// end for
				}// end if
				/*
				 * if( postNameListWithIds!=null ) { for (int i = 0; i <
				 * postNameListWithIds.size(); i++) { customVO = new
				 * UserPostCustomVO();
				 * 
				 * Object rowList[] = (Object[]) postNameListWithIds.get(i);
				 * 
				 * String postName = rowList[0].toString();
				 * customVO.setPostname(postName);
				 * 
				 * long postId = Long.parseLong(rowList[1].toString());
				 * customVO.setPostId(postId);
				 * 
				 * if(rowList[2] != null &&
				 * !(rowList[2].toString().trim()).equals("")){ empFullName =
				 * rowList[2].toString(); } else{ empFullName = "VACANT"; }
				 * customVO.setEmpFullName(empFullName);
				 * 
				 * String dsgnName = rowList[3].toString(); if(rowList[3] !=
				 * null && !(rowList[3].toString().trim()).equals("")){
				 * customVO.setDsgnname(dsgnName); } String BillNo = " "; String
				 * PsrNo = " ";
				 * 
				 * if(rowList[4]!= null){ PsrNo = rowList[4].toString(); }
				 * customVO.setPsrNo(PsrNo);
				 * 
				 * if(rowList[5] != null){ BillNo = rowList[5].toString(); }
				 * customVO.setBillNo(BillNo);
				 * 
				 * logger.info("===> BillNo :: "+BillNo); if(rowList[6] != null
				 * && !(rowList[6].toString().trim()).equals("")){ postType =
				 * rowList[6].toString(); } else{ postType = "VACANT"; }
				 * logger.info("===> postType :: "+postType);
				 * customVO.setPostType(postType); post.add(customVO); }//end
				 * for }//end if
				 */} else {
					 if (postNameList != null) {
						 for (int i = 0; i < postNameList.size(); i++) {
							 customVO = new UserPostCustomVO();

							 Object rowList[] = (Object[]) postNameList.get(i);

							 String postName = rowList[0].toString();
							 // customVO.setPostname(postName);

							 if (rowList[6] != null
									 && !(rowList[6].toString().trim()).equals("")) {
								 postLookupId = rowList[6].toString();
							 }
							 if (postLookupId.equals("10001198129")) {
								 postName = postName.concat("P");
							 } else if (postLookupId.equals("10001198130")) {
								 postName = postName.concat("T");
							 } else if (postLookupId.equals("10001198155")) {
								 postName = postName.concat("S");
							 } else {
								 postName = postName;
							 }
							 customVO.setPostname(postName);

							 long postId = Long.parseLong(rowList[1].toString());
							 customVO.setPostId(postId);

							 if (rowList[2] != null
									 && !(rowList[2].toString().trim()).equals("")) {
								 empFullName = rowList[2].toString();
							 } else {
								 empFullName = "VACANT";
							 }
							 customVO.setEmpFullName(empFullName);

							 String dsgnName = rowList[3].toString();
							 if (rowList[3] != null
									 && !(rowList[3].toString().trim()).equals("")) {
								 customVO.setDsgnname(dsgnName);
							 }
							 String BillNo = " ";
							 String PsrNo = " ";

							 if (rowList[4] != null) {
								 PsrNo = rowList[4].toString();
							 }
							 customVO.setPsrNo(PsrNo);

							 if (rowList[5] != null) {
								 BillNo = rowList[5].toString();
							 }
							 customVO.setBillNo(BillNo);

							 logger.info("===> BillNo :: " + BillNo);
							 if (rowList[7] != null
									 && !(rowList[7].toString().trim()).equals("")) {
								 postType = rowList[7].toString();
							 } else {
								 postType = "VACANT";
							 }
							 logger.info("===> postType :: " + postType);
							 customVO.setPostType(postType);

							 post.add(customVO);
							 logger.info("post in else:::::::: " + post.size());
						 }// end for
					 }// end if
				 }

			logger.info("post size out of if else:::::::: " + post.size());

			objectArgs.put("recordList", post);

			resObj.setViewName("showAdminPostDtls");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger.error("Admin Screen Creating A Post Error", ex);
			resObj.setResultCode(-1);
		}

		return resObj;
	}

	// ended by abhilash
	/**
	 * {@link Description} This Method is used to change the order , Start Date
	 * and End Date of Post
	 * 
	 * @author 366673 Amish
	 * @param Map
	 */
	public ResultObject loadRenewalOfPost(Map objectArgs) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs
		.get("requestObj");
		msg = 0;

		try {
			logger
			.info("===========loadRenewalOfPost=====called=============starts====");

			Enumeration paraNames = request.getParameterNames();
			while (paraNames.hasMoreElements()) {
				String paraName = (String) paraNames.nextElement();
				String value = StringUtility.getParameter(paraName, request);
				logger.info("Value in Map is from vo to service method"
						+ paraName + ":--->" + value);
			}

			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(
					OrgPostMst.class, serv.getSessionFactory());
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			long postId = StringUtility.convertToLong(loginMap.get(
			"primaryPostId").toString());
			OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(postId);
			long loggedInUser = StringUtility.convertToLong(loginMap.get(
			"userId").toString());
			logger.info("loggedInUserloggedInUserloggedInUserloggedInUser"
					+ loggedInUser);
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(
					OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst loggedInOrgUserMst = orgUserMstDaoImpl
			.read(loggedInUser);

			long oldOrderId = 0;
			long newOrderId = 0;
			List orderList = null;
			List postList = null;
			String lStrPostIdsToBeAttached = "";
			String lStrRenewalStartDate = "";
			String lStrRenewalEndDate = "";
			String lStrRenewalPostStartDate = "";
			Date lRenewalStartDate = null;
			Date lRenewalEndDate = null;
			Date lRenewalPostStartDate = null;
			String fetchDateFlag = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			OrgPostMst orgPostMst = null;
			GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(
					OrgPostMst.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			OrderMstDAOImpl ordermstDaoImpl = new OrderMstDAOImpl(
					HrPayOrderMst.class, serv.getSessionFactory());
			AdminOrgPostDtlDaoImpl orgPostDtlDaoImpl = new AdminOrgPostDtlDaoImpl(
					AdminOrgPostDtlServImpl.class, serv.getSessionFactory());
			long locId = Long.parseLong(loginMap.get("locationId").toString());

			if (StringUtility.getParameter("oldOrderId", request) != null)
				oldOrderId = (StringUtility.getParameter("oldOrderId", request) != null
						&& !(StringUtility.getParameter("oldOrderId", request)
								.equals("")) ? Long.parseLong(StringUtility
										.getParameter("oldOrderId", request)) : 0);

			if (StringUtility.getParameter("newOrderId", request) != null)
				newOrderId = (StringUtility.getParameter("newOrderId", request) != null
						&& !(StringUtility.getParameter("newOrderId", request)
								.equals("")) ? Long.parseLong(StringUtility
										.getParameter("newOrderId", request)) : 0);

			if (StringUtility.getParameter("postIdsToBeAttached", request) != null)
				lStrPostIdsToBeAttached = (StringUtility.getParameter(
						"postIdsToBeAttached", request) != null
						&& !(StringUtility.getParameter("postIdsToBeAttached",
								request).equals("")) ? StringUtility
										.getParameter("postIdsToBeAttached", request) : " ");

			// The Below Flag will be true in case of Ajax getting called to
			// fetch order date for selected renewal order

			if (StringUtility.getParameter("fetchDateFlag", request) != null)
				fetchDateFlag = (StringUtility.getParameter("fetchDateFlag",
						request) != null
						&& !(StringUtility.getParameter("fetchDateFlag",
								request).equals("")) ? StringUtility
										.getParameter("fetchDateFlag", request) : " ");

			logger.info("oldOrderId" + oldOrderId);
			logger.info("newOrderId" + newOrderId);
			logger.info("lStrPostIdsToBeAttached" + lStrPostIdsToBeAttached);
			lStrRenewalStartDate = (StringUtility.getParameter(
					"renewalStartDate", request) != null
					&& !(StringUtility
							.getParameter("renewalStartDate", request)
							.equals("")) ? (StringUtility.getParameter(
									"renewalStartDate", request)) : " ").toString();
			lStrRenewalEndDate = (StringUtility.getParameter("renewalEndDate",
					request) != null
					&& !(StringUtility.getParameter("renewalEndDate", request)
							.equals("")) ? (StringUtility.getParameter(
									"renewalEndDate", request)) : " ").toString();
			lStrRenewalPostStartDate = (StringUtility.getParameter(
					"renewalPostStartDate", request) != null
					&& !(StringUtility.getParameter("renewalPostStartDate",
							request).equals("")) ? (StringUtility.getParameter(
									"renewalPostStartDate", request)) : " ").toString();
			logger.info("lStrRenewalPostStartDate is "
					+ lStrRenewalPostStartDate);
			if (lStrRenewalStartDate != null && lStrRenewalStartDate != " "
				&& lStrRenewalStartDate != "")
				lRenewalStartDate = sdf.parse(lStrRenewalStartDate);
			if (lStrRenewalEndDate != null && lStrRenewalEndDate != " "
				&& lStrRenewalEndDate != "")
				lRenewalEndDate = sdf.parse(lStrRenewalEndDate);
			if (lStrRenewalPostStartDate != null
					&& lStrRenewalPostStartDate != " "
						&& lStrRenewalPostStartDate != "")
				lRenewalPostStartDate = sdf.parse(lStrRenewalPostStartDate);

			if (fetchDateFlag.equalsIgnoreCase("true")) {
				if (newOrderId != 0) {
					logger.info("New Order Id of Posts is-->" + newOrderId);
					HrPayOrderMst hrPayOrderMst = new OrderMstDAOImpl(
							HrPayOrderMst.class, serv.getSessionFactory())
					.getOrderMstDataByid(newOrderId);
					Date currentOrderDate = hrPayOrderMst.getOrderDate();
					String currentOrderDateStr = sdf.format(currentOrderDate);
					String currentOrderDateArr[] = currentOrderDateStr
					.split(" ");
					StringBuilder lStrBldXML = new StringBuilder();
					lStrBldXML.append("<XMLDOC>");
					lStrBldXML.append("<Flag>");
					lStrBldXML.append(currentOrderDateArr[0].toString());
					lStrBldXML.append("</Flag>");
					lStrBldXML.append("</XMLDOC>");
					String lStrResult = new AjaxXmlBuilder().addItem(
							"ajax_key", lStrBldXML.toString()).toString();
					objectArgs.put("ajaxKey", lStrResult);
					resObj.setViewName("ajaxData");
					resObj.setResultValue(objectArgs);
				}
				if (oldOrderId != 0) {
					logger.info("New Order Id of Posts is-->" + oldOrderId);
					HrPayOrderMst hrPayOrderMst = new OrderMstDAOImpl(
							HrPayOrderMst.class, serv.getSessionFactory())
					.getOrderMstDataByid(oldOrderId);
					Date currentOrderDate = hrPayOrderMst.getOrderDate();
					String currentOrderDateStr = sdf.format(currentOrderDate);
					// String currentOrderDateStr =
					// hrPayOrderMst.getOrderDate().toString();
					String currentOrderDateArr[] = currentOrderDateStr
					.split(" ");
					StringBuilder lStrBldXML = new StringBuilder();
					lStrBldXML.append("<XMLDOC>");
					lStrBldXML.append("<Flag>");
					lStrBldXML.append(currentOrderDateArr[0].toString());
					lStrBldXML.append("</Flag>");
					lStrBldXML.append("</XMLDOC>");
					String lStrResult = new AjaxXmlBuilder().addItem(
							"ajax_key", lStrBldXML.toString()).toString();
					objectArgs.put("ajaxKey", lStrResult);
					resObj.setViewName("ajaxData");
					resObj.setResultValue(objectArgs);
				}
			} else {
				if (oldOrderId != 0) {
					logger.info("Order Id selected is-->" + oldOrderId);
					List customizedPostList = new ArrayList();
					String regex = "(?<=[\\D])(?=\\d)";
					postList = orgPostDtlDaoImpl.fetchAllPostsUnderOrder(
							oldOrderId, locId);
					Integer totalRecords = postList.size();
					logger.info("totalRecords-->" + totalRecords);
					HrPayOrderMst hrPayOrderMst = new OrderMstDAOImpl(
							HrPayOrderMst.class, serv.getSessionFactory())
					.getOrderMstDataByid(oldOrderId);
					Date currentOrderDate = hrPayOrderMst.getOrderDate();
					String currentOrderDateStr = sdf.format(currentOrderDate);
					String currentOrderDateArr[] = currentOrderDateStr
					.split(" ");
					objectArgs.put("currentOrderDate", currentOrderDateArr[0]
					                                                       .toString());
					String postStartDate = null;

					for (int i = 0; i < totalRecords; i++) {
						OrgPostDetailsRlt postDetailsRlt = new OrgPostDetailsRlt();
						OrgPostDetailsRlt postDetailsRltNonPer = new OrgPostDetailsRlt();
						postDetailsRlt = (OrgPostDetailsRlt) postList.get(i);

						String postName = postDetailsRlt.getPostName();

						if (postName.indexOf("_") != -1) {
							String postNameArr[] = postName.split("_");
							postDetailsRltNonPer.setPostName(postNameArr[0]
							                                             .toString()
							                                             + "	(" + postNameArr[1].toString() + ")");
							logger.info("Modofied Post Name in underscore ="
									+ (postNameArr[0].toString() + "	" + "("
											+ postNameArr[1].toString() + ")")
											.toString());
						} else {
							String postNameArr[] = postName.split(regex);
							postDetailsRltNonPer.setPostName(postNameArr[0]
							                                             .toString()
							                                             + "	(" + postNameArr[1].toString() + ")");
							logger
							.info("Modofied Post Name without underscore="
									+ (postNameArr[0].toString() + "		"
											+ "("
											+ postNameArr[1].toString() + ")")
											.toString());
						}

						postDetailsRltNonPer.setPostDetailId(postDetailsRlt
								.getPostDetailId());
						postDetailsRltNonPer.setPostShortName(postDetailsRlt
								.getPostShortName());
						postDetailsRltNonPer
						.setOrgDesignationMst(postDetailsRlt
								.getOrgDesignationMst());
						postDetailsRltNonPer.setOrgPostMst(postDetailsRlt
								.getOrgPostMst());

						postDetailsRltNonPer.setCmnBranchMst(postDetailsRlt
								.getCmnBranchMst());
						postDetailsRltNonPer.setCmnLanguageMst(postDetailsRlt
								.getCmnLanguageMst());
						postDetailsRltNonPer.setCmnLocationMst(postDetailsRlt
								.getCmnLocationMst());
						postDetailsRltNonPer.setCreatedDate(postDetailsRlt
								.getCreatedDate());
						// postDetailsRltNonPer.setInterceptFieldCallback(postDetailsRlt.getInterceptFieldCallback());
						postDetailsRltNonPer
						.setOrgPostMstByCreatedByPost(postDetailsRlt
								.getOrgPostMstByCreatedByPost());
						postDetailsRltNonPer
						.setOrgPostMstByUpdatedByPost(postDetailsRlt
								.getOrgPostMstByUpdatedByPost());
						postDetailsRltNonPer
						.setOrgUserMstByCreatedBy(postDetailsRlt
								.getOrgUserMstByCreatedBy());
						postDetailsRltNonPer
						.setOrgUserMstByUpdatedBy(postDetailsRlt
								.getOrgUserMstByUpdatedBy());
						postDetailsRltNonPer.setPostCategory(postDetailsRlt
								.getPostCategory());
						postDetailsRltNonPer.setUpdatedDate(postDetailsRlt
								.getUpdatedDate());

						customizedPostList.add(postDetailsRltNonPer);
						if (i == 0) {
							postStartDate = sdf.format(postDetailsRlt
									.getOrgPostMst().getStartDate());
						}
					}

					objectArgs.put("currentOrderName", hrPayOrderMst
							.getOrderName());
					objectArgs.put("postList", customizedPostList);
					objectArgs.put("oldOrderId", oldOrderId);
					objectArgs.put("totalRecords", totalRecords);
					objectArgs.put("postStartDate", postStartDate);
					logger.info("Number of Posts Selected are :"
							+ totalRecords.intValue());
				}
				if (newOrderId != 0) {
					logger.info("New Order Id of Posts is-->" + newOrderId);
					if (lStrRenewalStartDate != null
							&& lStrRenewalStartDate != " "
								&& lStrRenewalStartDate != "")
						logger.info("Start Date is-->" + lStrRenewalStartDate);
					if (lStrRenewalEndDate != null && lStrRenewalEndDate != " "
						&& lStrRenewalEndDate != "")
						logger.info("End Date is-->" + lStrRenewalEndDate);
					if (lStrPostIdsToBeAttached != " ") {
						logger.info("Post Ids to be Updated are-->"
								+ lStrPostIdsToBeAttached);
						String[] lStrArrPostIdsToBeAttached = lStrPostIdsToBeAttached
						.split("~");
						Long[] lLongArrPostIdsToBeAttached = new Long[lStrArrPostIdsToBeAttached.length];
						for (Integer lInt = 0; lInt < lStrArrPostIdsToBeAttached.length; lInt++) {
							if (lStrArrPostIdsToBeAttached[lInt] != "") {
								OrderHeadPostmpgDAOImpl orderHeadPostMpgDAO = new OrderHeadPostmpgDAOImpl(
										HrPayOrderHeadPostMpg.class, serv
										.getSessionFactory());
								String headId = orderHeadPostMpgDAO
								.fetchSubHeadOfPost(Long
										.parseLong(lStrArrPostIdsToBeAttached[lInt]));
								long newOrderHeadPK = orderHeadPostMpgDAO
								.isOrderHeadMpgExist(newOrderId, headId);
								if (newOrderHeadPK == 0) {
									OrderHeadMpgDAOImpl orderHeadMpgDAOImpl = new OrderHeadMpgDAOImpl(
											HrPayOrderHeadMpg.class, serv
											.getSessionFactory());
									IdGenerator idGen = new IdGenerator();
									HrPayOrderHeadMpg hrPayOrderHeadMpgVO = new HrPayOrderHeadMpg();
									newOrderHeadPK = idGen
									.PKGeneratorWODBLOC(
											"HR_PAY_ORDER_HEAD_MPG",
											objectArgs);
									hrPayOrderHeadMpgVO
									.setOrderHeadId(newOrderHeadPK);
									hrPayOrderHeadMpgVO.setOrderId(newOrderId);
									hrPayOrderHeadMpgVO.setSubheadId(headId);
									hrPayOrderHeadMpgVO
									.setCreatedDate(new Date());
									hrPayOrderHeadMpgVO
									.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
									hrPayOrderHeadMpgVO
									.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
									hrPayOrderHeadMpgVO.setTrnCounter(1);
									orderHeadMpgDAOImpl
									.create(hrPayOrderHeadMpgVO);
								}
								lLongArrPostIdsToBeAttached[lInt] = Long
								.valueOf(lStrArrPostIdsToBeAttached[lInt]);
								logger.info("Updation Query Gets Called");
								orgPostDtlDaoImpl.updateOrderOfPost(
										lLongArrPostIdsToBeAttached[lInt],
										newOrderHeadPK);

								if (lRenewalPostStartDate != null
										&& lStrRenewalPostStartDate != " "
											&& lStrRenewalPostStartDate != "") {
									orgPostMst = (OrgPostMst) genDao
									.read(lLongArrPostIdsToBeAttached[lInt]);
									orgPostMst
									.setStartDate(lRenewalPostStartDate);
									genDao.update(orgPostMst);
								}
								if (lStrRenewalEndDate != null
										&& lStrRenewalEndDate != " "
											&& lStrRenewalEndDate != "") {
									orgPostMst = (OrgPostMst) genDao
									.read(lLongArrPostIdsToBeAttached[lInt]);
									orgPostMst.setEndDate(lRenewalEndDate);
									genDao.update(orgPostMst);
								}
							}
						}
						objectArgs.put("msg", "Record(s) Updated Successfully");
						logger.info("Successfully Updated Order Details");
					}
				}
				Calendar cal = Calendar.getInstance();
				Date today = cal.getTime();
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				String TodaysDate = fmt.format(today);
				logger.info("====> TodaysDate :: " + TodaysDate);


				orderList = ordermstDaoImpl.getAllOrderData(locId, TodaysDate);
				objectArgs.put("orderList", orderList);
				resObj.setViewName("RenewPostDtls");

				// Added By Mayuresh
				List postExpiryList = ordermstDaoImpl.getExpiryData(locId);
				logger.info("postExpiryList ############### "+postExpiryList);
				objectArgs.put("postExpiryList", postExpiryList);
				// Ended By Mayuresh

				resObj.setResultCode(0);
				resObj.setResultValue(objectArgs);
			}
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger.error("Error in loadRenewal Of Post " + ex.getMessage());
			ex.printStackTrace();
			resObj.setResultCode(-1);
		}
		return resObj;
	}

	// Ended By Amish

	ResourceBundle constantsBundle;
	public final int DEPT_ID;
	Log logger;
	int msg;
	public static final String adminCreatePostDtlViewName = "adminCreatePostDtls";
	public static final String showAdminPostDtlViewName = "showAdminPostDtls";
	public static final String postTypeLookupName = "PostType";
	public static final long engLangId = 1L;
	public static final long guLangId = 2L;

	// Added By Vivek For Level2DDO
	public ResultObject addSubDDOPostDtl(Map objectArgs) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");

			logger
			.info("===========addSubDDOPostDtl=====called=============starts====");

			OrgDesignationMstDao orgDesignationMstDao = new OrgDesignationMstDaoImpl(
					OrgDesignationMst.class, serv.getSessionFactory());
			AdminOrgPostDtlDaoImpl adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			long langId = Long.parseLong(loginMap.get("langId").toString());
			HttpServletRequest request = (HttpServletRequest) objectArgs
			.get("requestObj");
			String ddoCode = StringUtility.getParameter("ddoCode", request)
			.trim();
			logger.info("==========ddoCode is =====" + ddoCode
					+ "============starts====");
			List DDOdtls = adminOrgPostDtlDao.getDDODtls(ddoCode);
			logger.info("==========DDOdtls is =====" + DDOdtls.size()
					+ "============starts====");
			String strLngFieldDept = "";
			Iterator IT = DDOdtls.iterator();
			Object o[] = null;
			String locIdStr = "";
			while (IT.hasNext()) {
				o = (Object[]) IT.next();
				locIdStr = o[0].toString();
				strLngFieldDept = o[1].toString();

			}
			// String locIdStr= o.toString();
			long locId = Long.parseLong(locIdStr);
			// long loggedInPostId =
			// Long.parseLong(loginMap.get("primaryPostId").toString());

			logger.info("==========LocationId is =====" + locId
					+ "============starts====");

			long activeflag = 1L;

			Long lLngFieldDept = Long.parseLong(strLngFieldDept);
			AdminOrgPostDtlDaoImpl adminDao = new AdminOrgPostDtlDaoImpl(
					MstDcpsDesignation.class, serv.getSessionFactory());
			logger.info("lLngFieldDept:::::::::: " + lLngFieldDept);
			logger.info("locId Muni:::::::::: " + locId);
			List<OrgDesignationMst> desgList = adminDao
			.getActiveDsgnListOfFieldDept(lLngFieldDept);
			logger.info("desgList size **************" + desgList.size());
			objectArgs.put("Designation", desgList);
			List branchList_en = adminOrgPostDtlDao.getAllBranchList(1L);
			objectArgs.put("Branch", branchList_en);
			OrderMstDAOImpl ordermstDaoImpl = new OrderMstDAOImpl(
					HrPayOrderMst.class, serv.getSessionFactory());
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			String TodaysDate = fmt.format(today);
			logger.info("====> TodaysDate :: " + TodaysDate);
			List orderList = ordermstDaoImpl.getAllOrderData(locId, TodaysDate);
			// logger.info("==> orderList.size() :: "+orderList.size());
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			List officeList = adminOrgPostDtlDao.getAllOfficesFromDDO(ddoCode);

			// objectArgs.put("orderList", orderList);
			// objectArgs.put("billList", billList);
			// objectArgs.put("langId", Long.valueOf(langId));
			// objectArgs.put("officeList", officeList);
			// objectArgs.put("flag", "add");
			// //String lSBStatus =
			// getResponseXMLDocSaveData(orderList,billList,officeList).toString();
			// String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
			// lSBStatus).toString();
			// logger.info("********************************************"+lStrResult);
			// objectArgs.put("ajaxKey", lStrResult);
			// added by vaibhav tyagi
			List subList = adminDao.getSubjectList();
			objectArgs.put("SubjectList", subList);
			// added by vaibhav tyagi

			// added by Demolisher
			long lpostId = SessionHelper.getPostId(objectArgs);
			logger.info("Post ID is ::::::::::::::::" + lpostId);
			List subOfficeList = adminOrgPostDtlDao.getSubOfficesFromDDONew(lpostId);
			objectArgs.put("subOfficeList", subOfficeList);
			// added by Demolisher
			objectArgs.put("orderList", orderList);
			objectArgs.put("orderList", orderList);
			objectArgs.put("billList", billList);
			objectArgs.put("langId", Long.valueOf(langId));
			objectArgs.put("officeList", officeList);
			objectArgs.put("ddoCode", ddoCode);
			objectArgs.put("flag", "add");
			objectArgs.put("lvl2", "yes");
			resObj.setViewName("adminCreatePostDtls");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger.error("Admin Screen Creating A Post Error", ex);
			resObj.setResultCode(-1);
		}

		logger
		.info("===========addSubDDOPostDtl=====called=============ends====");
		return resObj;
	}

	private StringBuilder getResponseXMLDocSaveData(List orderList,
			List billList, List officeList) {

		StringBuilder lStrBldXML = new StringBuilder();
		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<orderList>");
		lStrBldXML.append(orderList);
		lStrBldXML.append("</orderList>");
		lStrBldXML.append("<billList>");
		lStrBldXML.append(billList);
		lStrBldXML.append("</billList>");
		lStrBldXML.append("<officeList>");
		lStrBldXML.append(officeList);
		lStrBldXML.append("</officeList>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public ResultObject loadSubDDOs(Map objectArgs) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			logger
			.info("===========addSubDDOPostDtl=====called=============starts====");
			ArrayList DDOlist = new ArrayList();

			objectArgs.put("DDOlist", DDOlist);
			// resObj.setViewName("loadSubDDOs");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger.error("Admin Screen Creating A Post Error", ex);
			resObj.setResultCode(-1);
		}

		logger
		.info("===========addSubDDOPostDtl=====called=============ends====");
		return resObj;

	}

	// created by shailesh
	public ResultObject generatePostReport(Map objectArgs) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
			.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			HttpServletRequest request = (HttpServletRequest) objectArgs
			.get("requestObj");
			long langId = Long.parseLong(loginMap.get("langId").toString());
			long locId = StringUtility.convertToLong(
					loginMap.get("locationId").toString()).longValue();

			String loggedInPost= loginMap.get("loggedInPost").toString();

			List locationList = null;

			Map voToService = (Map) objectArgs.get("voToServiceMap");
			List fieldCaptionList = new ArrayList();
			List tempList = new ArrayList();
			tempList.add("Post Name");
			fieldCaptionList.add(tempList);

			UserPostDAO postDAO = new UserPostDAO(UserPostCustomVO.class, serv
					.getSessionFactory());
			String empName = "";
			if (voToService != null) {

				if ((voToService.get("Employee_srchNameText_viewPostSearch") != null && !voToService
						.get("Employee_srchNameText_viewPostSearch").equals("")))
					empName = (String) voToService.get(
					"Employee_srchNameText_viewPostSearch").toString();
				logger.info("employee name : " + empName);
			}
			// modified for search by post and search by name by hemant(307727)
			String lPostname = "";
			String Psr = "";
			String Bill = "";
			String Dsgn = "";
			String empId = "";
			if (voToService != null) {
				if (voToService.get("Post") != null)
					lPostname = (String) voToService.get("Post").toString();
				if (voToService.get("PsrNo") != null)
					Psr = (String) voToService.get("PsrNo").toString();
				if (voToService.get("BillNo") != null)
					Bill = (String) voToService.get("BillNo").toString();
				if (voToService.get("Dsgn") != null)
					Dsgn = (String) voToService.get("Dsgn").toString();
				if (voToService.get("empId") != null)
					empId = (String) voToService.get("empId").toString();
			}
			logger.info("Post :" + lPostname + " PsrNo : " + Psr
					+ "  BillNo : " + Bill + "  Dsgn :  " + Dsgn + " EmpId "
					+ empId);

			UserPostCustomVO customVO = new UserPostCustomVO();
			String PostName = "";
			String dsgn = "";
			if (lPostname != null && !lPostname.equals(""))
				PostName = lPostname.trim();
			if (Dsgn != null && !Dsgn.equals(""))
				dsgn = Dsgn.trim();
			List postNameList = new ArrayList();
			// List sevaarthIdList = new ArrayList();

			locationList=postDAO.getSubLocationDDOs(loggedInPost);
			String locationcodeArray="";
			if(locationList!=null && locationList.size()>0)
				for(int i=0;i< locationList.size();i++){
					if(i==0)
						locationcodeArray+=locationList.get(i).toString();
					else
					{
						locationcodeArray+=","+locationList.get(i).toString();
						logger.info("locationcodeArray "+locationcodeArray);
					}

				}

			if (empId != null && !empId.equals("")) {
				postNameList = postDAO.getPostNameForDisplayThruEmpId(locationcodeArray,
						empId, langId);
				// sevaarthIdList = postDAO.getSevaarthIdList(empId);

			} else {
				//postNameList = postDAO.getPostNameForDisplay(locationcodeArray, PostName,
						//Psr, Bill, dsgn);
			}
			List post = new ArrayList();
			logger.info("postNameList size is: " + postNameList.size());
			String empFullName = "";
			String postType = "";
			String postLookupId = "";
			// String permenantlookupId="10001198129";
			// String temparerylookupId="10001198130";
			// String sevaarthId = "";

			String subPostTemp = "";
			String subPostPerm = "";
			String net_salary = "";
			long totalNetSalary = 0;
			int vacantPost = 0;
			int filledPost = 0;
			int totalNoOfPost = 0;

			subPostTemp = StringUtility.getParameter("subPostTemp", request);
			subPostPerm = StringUtility.getParameter("subPostPerm", request);
			logger.info("subPostPerm " + subPostPerm);
			logger.info("subPostPerm " + subPostTemp);

			logger.info("subPostTemp " + subPostTemp);
			logger.info("subPostPerm " + subPostPerm);
			if (postNameList != null) {
				for (int i = 0; i < postNameList.size(); i++) {
					customVO = new UserPostCustomVO();

					Object rowList[] = (Object[]) postNameList.get(i);

					// customVO.setPostname(postName);

					if (rowList[6] != null
							&& !(rowList[6].toString().trim()).equals("")) {
						postLookupId = rowList[6].toString();
						logger.info("postLookupId ::" + postLookupId);
					}

					if (subPostTemp != null && !subPostTemp.equals("")) {
						if (subPostTemp.equals(postLookupId)) {
							String postName = rowList[0].toString();
							postName = postName
							.concat(appendShortName(postLookupId));

							customVO.setPostname(postName);

							long postId = Long.parseLong(rowList[1]
							                                     .toString());
							customVO.setPostId(postId);

							if (rowList[2] != null
									&& !(rowList[2].toString().trim())
									.equals("")) {
								empFullName = rowList[2].toString();
								filledPost = filledPost + 1;
							} else {
								empFullName = "VACANT";
								vacantPost = vacantPost + 1;
							}
							customVO.setEmpFullName(empFullName);

							String dsgnName = rowList[3].toString();
							if (rowList[3] != null
									&& !(rowList[3].toString().trim())
									.equals("")) {
								customVO.setDsgnname(dsgnName);
							}
							String BillNo = " ";
							String PsrNo = " ";

							if (rowList[4] != null) {
								PsrNo = rowList[4].toString();
							}
							customVO.setPsrNo(PsrNo);

							if (rowList[5] != null) {
								BillNo = rowList[5].toString();
							}
							customVO.setBillNo(BillNo);

							if (rowList[8] != null) {
								customVO.setSevaarthId(rowList[8]
								                               .toString());
								net_salary = postDAO
								.getNetSalary(rowList[8].toString());
								if (!net_salary.equals(""))
									totalNetSalary = totalNetSalary
									+ Long.parseLong(net_salary);
								logger.info("rowList[8].toString() "
										+ rowList[8].toString()
										+ " net_salary " + net_salary);
								customVO.setNetSalary(net_salary);
							} else {
								customVO.setSevaarthId("");
								customVO.setNetSalary("");
							}

							// logger.info("===> BillNo :: "+BillNo);
							if (rowList[7] != null
									&& !(rowList[7].toString().trim())
									.equals("")) {
								postType = rowList[7].toString();
							} else {
								postType = "VACANT";
							}
							// logger.info("===> postType :: "+postType);
							customVO.setPostType(postType);
							post.add(customVO);
						}
					} else if (subPostPerm != null
							&& !subPostPerm.equals("")) {
						if (subPostPerm.equals(postLookupId)) {
							String postName = rowList[0].toString();
							postName = postName
							.concat(appendShortName(postLookupId));

							customVO.setPostname(postName);

							long postId = Long.parseLong(rowList[1]
							                                     .toString());
							customVO.setPostId(postId);

							if (rowList[2] != null
									&& !(rowList[2].toString().trim())
									.equals("")) {
								empFullName = rowList[2].toString();
								filledPost = filledPost + 1;
							} else {
								empFullName = "VACANT";
								vacantPost = vacantPost + 1;
							}
							customVO.setEmpFullName(empFullName);

							String dsgnName = rowList[3].toString();
							if (rowList[3] != null
									&& !(rowList[3].toString().trim())
									.equals("")) {
								customVO.setDsgnname(dsgnName);
							}
							String BillNo = " ";
							String PsrNo = " ";

							if (rowList[4] != null) {
								PsrNo = rowList[4].toString();
							}
							customVO.setPsrNo(PsrNo);

							if (rowList[5] != null) {
								BillNo = rowList[5].toString();
							}
							customVO.setBillNo(BillNo);

							// logger.info("===> BillNo :: "+BillNo);
							if (rowList[7] != null
									&& !(rowList[7].toString().trim())
									.equals("")) {
								postType = rowList[7].toString();
							} else {
								postType = "VACANT";
							}
							if (rowList[8] != null) {
								customVO.setSevaarthId(rowList[8]
								                               .toString());
								net_salary = postDAO
								.getNetSalary(rowList[8].toString());
								if (!net_salary.equals(""))
									totalNetSalary = totalNetSalary
									+ Long.parseLong(net_salary);
								logger.info("rowList[8].toString() "
										+ rowList[8].toString()
										+ " net_salary " + net_salary);
								customVO.setNetSalary(net_salary);
							} else {
								customVO.setSevaarthId("");
								customVO.setNetSalary("");
							}
							// logger.info("===> postType :: "+postType);
							customVO.setPostType(postType);
							post.add(customVO);
						}
					}

				}// end for
			}// end if


			logger.info("The List size is:-" + post.size());
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			String TodaysDate = fmt.format(today);
			// logger.info("====> TodaysDate :: "+TodaysDate);

			OrderMstDAOImpl ordermstDaoImpl = new OrderMstDAOImpl(
					HrPayOrderMst.class, serv.getSessionFactory());
			List orderList = ordermstDaoImpl.getAllOrderData(locId, TodaysDate);
			// logger.info("==>1 orderList.size() :: "+orderList.size());
			// ordermstDaoImpl.getSubDDOs();

			objectArgs.put("orderList", orderList);
			objectArgs.put("empId", empId);
			objectArgs.put("empName", empName);
			objectArgs.put("totalNoOfPost",
					new Integer(vacantPost + filledPost));
			objectArgs.put("recordList", post);
			objectArgs.put("vacantPost", new Integer(vacantPost));
			objectArgs.put("filledPost", new Integer(filledPost));
			objectArgs.put("totalNetSalary", new Long(totalNetSalary));
			// added by abhilash

			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			long loggedInPostId = Long.parseLong(loginMap.get("primaryPostId")
					.toString());
			DcpsCommonDAOImpl commonDao = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			// code changed by Ankit Bhatt for DDO Code
			PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, serv
					.getSessionFactory());
			List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
			OrgDdoMst ddoMst = null;
			if (ddoList != null && ddoList.size() > 0) {
				ddoMst = ddoList.get(0);
			}
			// ended
			String ddoCode = null;
			if (ddoMst != null)
				ddoCode = ddoMst.getDdoCode();

			// DdoInfoDAO ddoInfoDAO = new DdoInfoDAOImpl(null,
			// serv.getSessionFactory());
			// OrgDdoMst lObjOrgDdoMst = ddoInfoDAO.getDdoInformation(ddoCode);
			Long lLngFieldDept = Long.parseLong(ddoMst.getHodLocCode());
			AdminOrgPostDtlDaoImpl adminDao = new AdminOrgPostDtlDaoImpl(
					MstDcpsDesignation.class, serv.getSessionFactory());
			List<OrgDesignationMst> desgList = adminDao
			.getActiveDesig(lLngFieldDept);
			// List<OrgDesignationMst> desgList =
			// adminDao.getActiveDsgnListOfFieldDept(locId);
			logger.info("desgList size **************" + desgList.size());
			objectArgs.put("Designation", desgList);
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			logger.info("billList checking abhilash size **************"
					+ billList.size());

			// ended by abhilash
			logger.info("in show post reports ");
			objectArgs.put("fieldCaptionList", fieldCaptionList);
			resObj.setViewName("showPostReports");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			logger.error("Admin Screen Showing all Post Error", ex);
			resObj.setResultCode(-1);
		}
		return resObj;

	}

	public String appendShortName(String lookUpId) {
		if (lookUpId.equals("10001198159")) {
			return "P_Pro";
		} else if (lookUpId.equals("10001198156")) {
			return "P_Sur";
		} else if (lookUpId.equals("10001198174")) {
			return "P_Reg";
		}

		else if (lookUpId.equals("10001198157")) {
			return "T_Adh";
		} else if (lookUpId.equals("10001198155")) {
			return "T_Sta";
		} else if (lookUpId.equals("10001198154")) {
			return "T_Hon";
		} else if (lookUpId.equals("10001198153")) {
			return "T_Con";
		} else if (lookUpId.equals("10001198175")) {
			return "T_CHB";
		} else
			return "";

	}

	public ResultObject generateStastics(Map objectArgs) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs
		.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		AdminOrgPostDtlDaoImpl adminDao = new AdminOrgPostDtlDaoImpl(
				MstDcpsDesignation.class, serv.getSessionFactory());

		try {
			logger
			.info("===========loadRenewalOfPost=====called=============starts====");
			String flag = StringUtility.getParameter("flag", request);
			logger.info("flag" + flag);
			String query = StringUtility.getParameter("query", request);
			List result = null;
			if (flag.equals("run")) {
				logger.info("in if loop...");
				result = adminDao.getStastics(query);
				if (result == null) {
					logger.info("result is null.........");
				}
				objectArgs.put("resultlist", result);
			}
			if (result == null) {
				logger.info("result is null.........");
			}
			// objectArgs.put("fieldCaptionList", result);
			resObj.setViewName("showStatstics"); // showPostReports
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			logger.error("Admin Screen Showing all Post Error", ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}
}