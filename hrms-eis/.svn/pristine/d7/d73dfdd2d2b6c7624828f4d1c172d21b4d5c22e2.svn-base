package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BankMasterDAO;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.BranchMasterDAO;
import com.tcs.sgv.eis.dao.BranchMasterDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.SchedulerDAO;
import com.tcs.sgv.eis.dao.SchedulerDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrInvestTypeMst;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class SchedulerServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());

	int msg = 0;

	/**
	 * This method is used to update scheduler details
	 * 
	 * @param objectArgs
	 * @return
	 */

	public ResultObject insertSchedulerDetails(Map objectArgs) 
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			boolean duplicateFlag = false;
			long userId = StringUtility.convertToLong(loginDetailsMap.get(
					"userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(
					OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId")
					.toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(
					CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			long locId = StringUtility.convertToLong(loginDetailsMap.get(
					"locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(
					CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			long postId = StringUtility.convertToLong(loginDetailsMap.get(
					"primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(
					OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			SchedulerDAOImpl schedulerDAO = new SchedulerDAOImpl(
					HrPayPaybillScheduler.class, serv.getSessionFactory());

			BillHeadMpgDAOImpl billheadMstDaoImpl = null;//new BillHeadMpgDAOImpl(	HrPayBillHeadMpg.class, serv.getSessionFactory());
			

			//System.out.println("STARTING SERVICE 1111111111111111111111");

			HrPayPaybillScheduler hrschedulerMst = (HrPayPaybillScheduler) objectArgs.get("hrpaypaybillscheduler");

			//System.out.println("hrschedulerMst ################## "+ hrschedulerMst.getSchedulerId());
			Date sysdate = new Date();

			// abhilash started for update
			SchedulerDAOImpl schedulerDao = new SchedulerDAOImpl(
					HrPayPaybillScheduler.class, serv.getSessionFactory());
			String editFromVO = objectArgs.get("edit").toString();

			
			
			
			
			// check code
			int count = schedulerDao.checkDuplicateRecord(hrschedulerMst);
			if (count == 1)
			{
				duplicateFlag = true;
				objectArgs.put("msg", "No Duplicates are Allowed");
			}
			
			
			
			//System.out.println("count ################## " + count);
			
			if (!duplicateFlag)
			{
				// check end code
				
				
				if (editFromVO != null && editFromVO.equals("Y")) 
				{
					//System.out.println(" INSIDE UPDATE **********");

					long SchedulerId = hrschedulerMst.getSchedulerId();

			//		System.out.println(" UPDATE SchedulerId &&&&&&&&&&&"							+ SchedulerId);
					HrPayPaybillScheduler SchedulerMstVO = schedulerDao.read(SchedulerId);
					/*SchedulerMstVO.setHrpaybillsubheadmpg(hrschedulerMst.getHrpaybillsubheadmpg());
					SchedulerMstVO.setCmnLocationMst(hrschedulerMst.getCmnLocationMst());*/
					SchedulerMstVO.setDay(hrschedulerMst.getDay());

					SchedulerMstVO.setUpdatedDate(sysdate);
					SchedulerMstVO.setOrgPostMstByUpdatedByPost(orgPostMst);
					SchedulerMstVO.setOrgUserMstByUpdatedBy(orgUserMst);

					schedulerDao.update(SchedulerMstVO);
					objectArgs.put("msg", "Updated Successfully");
					msg = 1;
				}
				
				// abhilash ended for update
				
				
			/*	
				else if (editFromVO != null && editFromVO.equals("N")) 
				{
					
				
						
					
					// This is for single record entry into database
					
					System.out.println("INSIDE CREATE SchedulerTesting ********************************* "+ objectArgs);
					HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
					
					
					
					IdGenerator idGen = new IdGenerator();
					long SchedulerId = idGen.PKGenerator("hr_pay_paybill_scheduler", objectArgs);
					System.out
							.println("the SchedulerId in service is ********************************"
									+ SchedulerId);

					hrschedulerMst.setSchedulerId(SchedulerId);
					hrschedulerMst.setHrpaybillsubheadmpg(hrschedulerMst.getHrpaybillsubheadmpg());
					String day = StringUtility.getParameter("Day", request);
					System.out.println("the day in service is ********************************"+ day);
					hrschedulerMst.setDay(Long.parseLong(day));
					CmnLocationMst cmnLocationSelected = null;

					if (hrschedulerMst != null)
						cmnLocationSelected = hrschedulerMst.getCmnLocationMst();
					hrschedulerMst.setCmnLanguageMst(cmnLanguageMst);
					if (cmnLocationSelected != null)
						hrschedulerMst.setCmnLocationMst(cmnLocationSelected);

					hrschedulerMst.setOrgPostMstByCreatedByPost(orgPostMst);
					hrschedulerMst.setOrgPostMstByUpdatedByPost(orgPostMst);
					hrschedulerMst.setOrgUserMstByCreatedBy(orgUserMst);
					hrschedulerMst.setOrgUserMstByUpdatedBy(orgUserMst);
					hrschedulerMst.setCreatedDate(sysdate);
					hrschedulerMst.setActivateflag(1);

					schedulerDAO.create(hrschedulerMst);
					objectArgs.put("msg", "Inserted Successfully");
					
					
				}
			    	*/
				else 
				{
					throw new NullPointerException();
				}

			}

			
			List<HrPayPaybillScheduler> schedulerList = schedulerDAO
					.getAllData(langId);

			logger.info(" In getScheduleDtls  method schedulerList "
					+ schedulerList.size());

			List deptList = cmnLanguageMstDaoImpl.getListByColumnAndValue(
					"cmnLanguageMst.langId", langId);

			objectArgs.put("schedulerList", schedulerList);
			objectArgs.put("listSize", schedulerList.size());
			objectArgs.put("deptList", deptList);

			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
			resObj.setViewName("SchedulerView");

			

			
				}
		
		
		// end for insert part
		
		catch (Exception ex) {
			resObj.setThrowable(ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}

	/**
	 * This method is used to get the list of scheduler details from database
	 * 
	 * @param objectArgs
	 * @return
	 */

	//public ResultObject listOfScheduler(Map objectArgs)
	public ResultObject listOfSchedulerDetailsForEntry(Map objectArgs) 
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());
			long locId = StringUtility.convertToLong(
					loginMap.get("locationId").toString()).longValue();
			Map voToService = (Map) objectArgs.get("voToServiceMap");

			// for department list
			CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			List deptList = locationDAO.getListByColumnAndValue(
					"cmnLanguageMst.langId", langId);

			// for billno list

			PayBillDAOImpl payBillDAO1 = new PayBillDAOImpl(HrPayPaybill.class,
					serv.getSessionFactory());
			ArrayList billList = new ArrayList();
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("resources.Payroll");
			long finYearId = Long.parseLong(resourceBundle
					.getString("finYearId"));

			List billNoList = null;//payBillDAO1.getBillNo(finYearId, locId);
			//System.out.println("billNoList" + billNoList.size());
			for (Iterator itr = billNoList.iterator(); itr.hasNext();) {
				HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
				Object[] row = (Object[]) itr.next();
				String billNo = row[1].toString();
				String billHeadId = row[0].toString();
				//billNoCustomVO.setBillId(Long.parseLong(billNo));
				billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
				billList.add(billNoCustomVO);
			}

			//System.out.println("billNoList123");

			objectArgs.put("deptList", deptList);
			objectArgs.put("billList", billList);

			resObj.setViewName("SchedulerEntry");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		}

		catch (Exception ex) {
			resObj.setThrowable(ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}

	/**
	 * This method is used to get the bill numbers from department by using AZAX
	 * 
	 * @param objectArgs
	 * @return
	 */

	//public ResultObject getBillnoFromDept(Map objectArgs) 
	public ResultObject getBillNosFromDept(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try {
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			long locCode = 0;
			if (StringUtility.getParameter("LocationCode", request) != null)
				locCode = Long.valueOf(StringUtility.getParameter("LocationCode", request).toString());
			//System.out.println("location code is ------------>>>>>***"+ locCode);

			BillHeadMpgDAOImpl billHeadMpgDAO = null;//new BillHeadMpgDAOImpl(					HrPayBillHeadMpg.class, serv.getSessionFactory());
			List<HrPayBillHeadMpg> lstHrPaybillHeadMpg = null;//billHeadMpgDAO.getBillNofromDept(locCode);

			Log logger = LogFactory.getLog(getClass());

			StringBuffer billNoStr = new StringBuffer();
			if (lstHrPaybillHeadMpg != null) 
			{
				for (int i = 0; i < lstHrPaybillHeadMpg.size(); i++) 
				{
					HrPayBillHeadMpg hrPayBillHeadMpg = lstHrPaybillHeadMpg.get(i);
					billNoStr.append("<LocationCode>");
					//System.out.println(hrPayBillHeadMpg.getBillId());
					billNoStr.append("<billHeadId>").append(hrPayBillHeadMpg.getBillHeadId()).append("</billHeadId>");
					billNoStr.append("<billId>").append(hrPayBillHeadMpg.getBillId()).append("</billId>");
					billNoStr.append("</LocationCode>");
				}
			}
			String searchBillNoList = new AjaxXmlBuilder().addItem("ajax_key",
					billNoStr.toString()).toString();
			objectArgs.put("ajaxKey", searchBillNoList);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");

		} catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	/**
	 * This method is used to display List page.
	 * 
	 * @param objectArgs
	 * @return
	 */

	//public ResultObject getSchedulerDtls(Map objectArgs)
	public ResultObject viewSchedulerDtls(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");

			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());

			Log logger = LogFactory.getLog(getClass());

			logger.info("getScheduleDtls  method calling");

			SchedulerDAOImpl schedulerDAO = new SchedulerDAOImpl(
					HrPayPaybillScheduler.class, serv.getSessionFactory());
			CmnLocationMstDaoImpl cmnLocationMstDao = new CmnLocationMstDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			BillHeadMpgDAOImpl billHeadMpgDAOImpl = null;//new BillHeadMpgDAOImpl(					HrPayBillHeadMpg.class, serv.getSessionFactory());

			List<HrPayPaybillScheduler> schedulerList = schedulerDAO
					.getAllData(langId);

			logger.info(" In getScheduleDtls  method schedulerList "
					+ schedulerList.size());

			List deptList = cmnLocationMstDao.getListByColumnAndValue(
					"cmnLanguageMst.langId", langId);

			objectArgs.put("schedulerList", schedulerList);
			objectArgs.put("listSize", schedulerList.size());
			objectArgs.put("deptList", deptList);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("SchedulerView");

		} catch (Exception e) {
			e.printStackTrace();
			objectArgs.put("msg", "There is some problem. Please Try Again.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
		}

		return resultObject;
	}

	/**
	 * This method is used to update of existing scheduler details
	 * 
	 * @param objectArgs
	 * @return
	 */

	public ResultObject updateSchdeulerDtls(Map objectArgs) {
		logger.info("in updateSchdeularDtls");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try {

			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());

			//System.out.println("objectArgs#########################"					+ objectArgs);

			long SchedulerId = 0;
			if (StringUtility.getParameter("SchedulerId", request) != null)

				SchedulerId = Long.valueOf(StringUtility.getParameter(
						"SchedulerId", request).toString());

			logger.info("SchedulerId #########################" + SchedulerId);
			Map voToService = (Map) objectArgs.get("voToServiceMap");

			/*
			 * String editFlag = (String)voToService.get("edit").toString();
			 * if(editFlag != null && editFlag.equals("Y")) {
			 */

			SchedulerDAOImpl schedulerDAO = new SchedulerDAOImpl(
					HrPayPaybillScheduler.class, serv.getSessionFactory());
			String edit = "";
			// Map voToService = (Map)objectArgs.get("voToServiceMap");

			SchedulerId = Long.valueOf(StringUtility.getParameter("SchedulerId", request).toString());
			List<HrPayPaybillScheduler> lstBillDtls = schedulerDAO
					.getBillDetails(SchedulerId);
			HrPayPaybillScheduler hrPayPaybillScheduler = null;
			long billId = 0;
			String locCode = "";
			long day = 0;
			if (lstBillDtls != null && lstBillDtls.size() > 0) {
				hrPayPaybillScheduler = lstBillDtls.get(0);
				/*billId = hrPayPaybillScheduler.getHrpaybillsubheadmpg()
						.getBillId();
				locCode = hrPayPaybillScheduler.getCmnLocationMst()
						.getLocationCode();*/
				day = hrPayPaybillScheduler.getDay();
			}

			logger.info(" In updateSchdeularDtls  method SchedulerId "
					+ SchedulerId);
			HrPayPaybillScheduler newResult = new HrPayPaybillScheduler();

			CmnLocationMstDaoImpl cmnLocationMstDao = new CmnLocationMstDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			List deptList = cmnLocationMstDao.getListByColumnAndValue(
					"cmnLanguageMst.langId", langId);

			BillHeadMpgDAOImpl billHeadMpgDAOImpl = null;//new BillHeadMpgDAOImpl(					HrPayBillHeadMpg.class, serv.getSessionFactory());
			List billList = billHeadMpgDAOImpl.getAllData();
			
			
			//System.out.println(billList.size());
			
			List<HrPayPaybillScheduler> schedulerList = schedulerDAO.getAllData(langId);

			Map map = new HashMap();
			map = objectArgs;
			map.put("lstBillDtls", lstBillDtls);
			map.put("deptList", deptList);
			map.put("billList", billList);
			map.put("day", day);
			map.put("billId", billId);
			map.put("locCode", locCode);

			map.put("schedulerList", schedulerList);
			map.put("listSize", schedulerList.size());

		//	objectArgs.put("msg", "Updated Successfully");
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(map);
			resultObject.setViewName("schedulerUpdate");
			

		}

		catch (Exception e) {
			e.printStackTrace();
			objectArgs.put("msg", "There is some problem. Please Try Again.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
		}

		return resultObject;
	}
	
	/**
	 * This method is used to insert one or more than one scheduler details into data base
	 * 
	 * @param objectArgs
	 * @return
	 */
	
	public ResultObject multipleAddScheduler(Map objectArgs) 
	{
		
		logger.info("-------------inside multipleAddLoan-------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
		HttpSession session=request.getSession();
		Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");
		
		
      try
        {
			
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
			
			
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");

			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			
			long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			SchedulerDAOImpl schedulerDAO = new SchedulerDAOImpl(HrPayPaybillScheduler.class, serv.getSessionFactory());
			HrPayPaybillScheduler hrPaybillScheduler =null;
			Date sysdate = new Date();
			String encXML[] = StringUtility.getParameterValues("encXML",request);

			
			logger.info("encXML.length: " +encXML.length);
			logger.info("encXML: XML file path is: " +encXML[0]);
			
			
			int xx=1;
			int yy=1;
			
			
				if(encXML!=null && encXML.length>0)
		    	{					
										
					ArrayList result = (ArrayList) FileUtility.xmlFilesToVoByXStream(encXML);
					java.util.Iterator iterator = result.iterator();

					logger.info("result.size(): "+result.size());
					
					while(iterator.hasNext())
					{
                        hrPaybillScheduler = new HrPayPaybillScheduler();
						
						hrPaybillScheduler = (HrPayPaybillScheduler)iterator.next();
						
						//for duplicate
						
						 boolean duplicateFlag = false;
						 SchedulerDAOImpl schedulerDao = new SchedulerDAOImpl(HrPayPaybillScheduler.class, serv.getSessionFactory());
						int count = schedulerDao.checkDuplicateRecord(hrPaybillScheduler);
						if (count == 1)
						{
							duplicateFlag = true;
							xx=0;
							//objectArgs.put("msg", "No Duplicates are Allowed");
						}
						if (!duplicateFlag)
						{										
						
						
						//System.out.println("multipleAddScheduler ********************************");
						IdGenerator idGen = new IdGenerator();
						long SchedulerId = idGen.PKGenerator("hr_pay_paybill_scheduler", objectArgs);
						hrPaybillScheduler.setSchedulerId(SchedulerId);
						hrPaybillScheduler.setCmnLanguageMst(cmnLanguageMst);
						hrPaybillScheduler.setOrgPostMstByCreatedByPost(orgPostMst);
						hrPaybillScheduler.setOrgPostMstByUpdatedByPost(orgPostMst);
						hrPaybillScheduler.setOrgUserMstByCreatedBy(orgUserMst);
						hrPaybillScheduler.setOrgUserMstByUpdatedBy(orgUserMst);
						hrPaybillScheduler.setCreatedDate(sysdate);
						hrPaybillScheduler.setActivateflag(1);
						
						//System.out.println("the day in service is ********************************");
						/*hrPaybillScheduler.getCmnLocationMst().getLocId();
						hrPaybillScheduler.getDay();
						hrPaybillScheduler.getHrpaybillsubheadmpg().getBillId();*/
						hrPaybillScheduler.getCmnLanguageMst().getLangId();
						
						logger.info("hrPaybillScheduler.getCmnLanguageMst().getLangId();" +hrPaybillScheduler.getCmnLanguageMst().getLangId());
					
						
						schedulerDAO.create(hrPaybillScheduler);
						yy=0;
						//objectArgs.put("msg", "Inserted Successfully");
						}
						
					}
				
		    	}
				
				if(xx==0 && yy==1)
				{
					objectArgs.put("msg", "No Duplicates are Allowed");
				}
				else if(xx==1 && yy==0)
				{
					objectArgs.put("msg", "Inserted Successfully");
				}
				else if(xx==0 && yy==0)
				{
					objectArgs.put("msg", "New data inserted and duplicated data is not insert ");
				}
		    	
			
			List<HrPayPaybillScheduler> schedulerList = schedulerDAO
			.getAllData(langId);

	logger.info(" In getScheduleDtls  method schedulerList "
			+ schedulerList.size());

	List deptList = cmnLanguageMstDaoImpl.getListByColumnAndValue(
			"cmnLanguageMst.langId", langId);

	objectArgs.put("schedulerList", schedulerList);
	objectArgs.put("listSize", schedulerList.size());
	objectArgs.put("deptList", deptList);

	resultObject.setResultCode(ErrorConstants.SUCCESS);
	resultObject.setResultValue(objectArgs);
	resultObject.setViewName("SchedulerView");
				
        }
      catch (Exception ex) {
    	  resultObject.setThrowable(ex);
    	  resultObject.setResultCode(-1);
		}
		return resultObject;
	}
	public ResultObject checkbill(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		try {
		
		long billId = 0;
		long locationId = 0;
		long schedulerId = 0;
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		//billId = Long.parseLong((String) voToService.get("billNoList"));
		billId = Long.valueOf(StringUtility.getParameter("billId", request).toString());
		 //System.out.println("billId billId billId is----->>" + billId);
		locationId = Long.parseLong((StringUtility.getParameter("LocationCode", request).toString()));
		 //System.out.println("locationId locationId locationId is----->>" + locationId);
		//schedulerId = Long.parseLong((String) voToService.get("schedulerId"));
		//schedulerId = Long.valueOf(StringUtility.getParameter("SchedulerId", request).toString());
		//logger.info("Bill Head Mapping Id is-------->>>" + schedulerId);

		Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
		SchedulerDAOImpl schedulerDAO = new SchedulerDAOImpl(HrPayPaybillScheduler.class, serv.getSessionFactory());
		List mpgDataList = schedulerDAO.chkBillData(locationId, billId);
		HrPayPaybillScheduler hrPaybillScheduler= new HrPayPaybillScheduler();
		//List mpgDataList = schedulerDAO.checkDuplicateRecord(hrPayPaybillScheduler);
		
		StringBuffer propertyList = new StringBuffer();
		logger.info("Mapping List Size is----->>" + mpgDataList.size());
		
		  //System.out.println("Mapping List Size is----->>" + mpgDataList.size());
		  /*  
		  
		  ArrayList allSubHeadList = new ArrayList();   
          StringBuffer sb = new StringBuffer();
          
           if(mpgDataList.size()>= 1 )
           {
          
	            sb.append("<LocationCode>");
				sb.append("<subhead>").append("true").append("</subhead>");
				sb.append("</LocationCode>");
           }
           else
           {
        	   sb.append("<LocationCode>");
				sb.append("<subhead>").append("false").append("</subhead>");
				sb.append("</LocationCode>");
          
           }
          
  			 String subHeadXML  = sb.toString();
  			 
  			
	            
  	         String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key", subHeadXML).toString();
  	         objectArgs.put("ajaxKey", resultAjaxXml);
  	         //objectArgs.put("ajaxKey", idNumberStrAjax);      
  	         resultObject.setResultValue(objectArgs);
  	         resultObject.setViewName("ajaxData");  
  	         System.out.println(resultAjaxXml);*/
		  propertyList.append("<LocationCode>");
		if (mpgDataList != null && mpgDataList.size() > 0)
			propertyList.append("<mpgFlag>").append("<![CDATA[").append(
					mpgDataList.size()).append("]]>").append("</mpgFlag>");
		else
			propertyList.append("<mpgFlag>").append("<![CDATA[").append(-1)
					.append("]]>").append("</mpgFlag>");
		propertyList.append("</LocationCode>");

		String mpgdata = new AjaxXmlBuilder().addItem("ajax_key",
				propertyList.toString()).toString();
		objectArgs.put("ajaxKey", mpgdata);

		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("ajaxData");
		logger.info("After Service Called.........\n" + mpgdata);
		return resultObject;
		} catch (Exception e) {
			resultObject.setThrowable(e);
			resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setViewName("errorPage");
		}
		

	
		 return resultObject;
	
	
	
}
}
	
	
	
	
	
	
	
	
	

