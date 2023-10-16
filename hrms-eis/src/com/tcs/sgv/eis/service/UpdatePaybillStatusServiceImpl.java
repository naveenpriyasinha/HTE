package com.tcs.sgv.eis.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PayBillActiationStatusDAoImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
//import com.tcs.sgv.eis.dao.SchedulerDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
//import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;
import com.tcs.sgv.eis.valueobject.HrPaybillActivationStatusMpg;
import com.tcs.sgv.eis.valueobject.HrUpdatePayBillStatus;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class UpdatePaybillStatusServiceImpl extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	int msg=0;
	
	public ResultObject getLocationEmployees(Map objectArgs)
	{
		logger.info("into service method of update pay bill status..");
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
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			String action = StringUtility.getParameter("action",request);
			String msg = StringUtility.getParameter("msg", request);
			if(msg.equals("1"))
			{
				msg = "Records Dectivated Successfully";
			}
			else if(msg.equals("2"))
			{
				msg="Records Activated Successfully";
			}
			else
			{
				msg="";
			}
			//System.out.println(msg);
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
			//SchedulerDAOImpl schedulerDAO = new SchedulerDAOImpl(
					//HrPayPaybillScheduler.class, serv.getSessionFactory());

			Map passMap = new HashMap();
			passMap.put("loginDetailsMap", loginDetailsMap);
			
			PayBillActiationStatusDAoImpl payBillActivationDAO = new PayBillActiationStatusDAoImpl(HrPaybillActivationStatusMpg.class,serv.getSessionFactory());
			List empActList =payBillActivationDAO.getActiveData(langId, locId);
			List empDctList = payBillActivationDAO.getDeactiveData(langId, locId);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			
			
			Object[] data = null;
			int i=0;
			List<HrUpdatePayBillStatus> empActDetails = new ArrayList<HrUpdatePayBillStatus>();
			List<HrUpdatePayBillStatus> empDctDetails = new ArrayList<HrUpdatePayBillStatus>();
			for(i=0;i<empActList.size();i++)
			{
				HrUpdatePayBillStatus emp = new HrUpdatePayBillStatus();
				data=(Object[])empActList.get(i);
				long post_Id= StringUtility.convertToLong(data[0].toString());
				emp.setPostId(post_Id);
				String name = data[1].toString();
				emp.setEmpName(name);
				String postName = data[2].toString();
				emp.setPostName(postName);
				Date strtDate = null;
				Date edDate = null;
				String startDate="";
				String endDate="";
				if(data[3]!=null)
				{
					//System.out.println();
					//System.out.println(data[3]);
					 strtDate =sdf.parse(data[3].toString());
					 startDate=sdf1.format(strtDate);
					 
				}
				if(data[4]!=null)
				{
					edDate =sdf.parse(data[4].toString());
					endDate= sdf1.format(edDate);
				}
					
				emp.setStartDate(strtDate);
				emp.setEndDate(edDate);
				empActDetails.add(emp);
				
			
			}
			
			for(i=0;i<empDctList.size();i++)
			{
				
				
				HrUpdatePayBillStatus emp = new HrUpdatePayBillStatus();
				data=(Object[])empDctList.get(i);
				long post_Id= StringUtility.convertToLong(data[0].toString());
				emp.setPostId(post_Id);
				String name = data[1].toString();
				emp.setEmpName(name);
				String postName = data[2].toString();
				emp.setPostName(postName);
				Date strtDate = null;
				Date edDate = null;
				String startDate="";
				String endDate="";
				if(data[3]!=null)
				{
					 strtDate =sdf.parse(data[3].toString());
					 startDate=sdf1.format(strtDate);
					 
				}
				if(data[4]!=null)
				{
					edDate =sdf.parse(data[4].toString());
					endDate= sdf1.format(edDate);
				}
					
				emp.setStartDate(strtDate);
				emp.setEndDate(edDate);
				empDctDetails.add(emp);
				
			
			}
			
			PayBillDAOImpl payBillDAO1 = new PayBillDAOImpl(HrPayPaybill.class,
					serv.getSessionFactory());
			//ArrayList billList = new ArrayList();
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("resources.Payroll");
			long finYearId = Long.parseLong(resourceBundle
					.getString("finYearId"));
			String ddo_code=null;
    		
    		List<OrgDdoMst> ddoCodeList = payBillDAO1.getDDOCodeByLoggedInPost(postId);
    		if(ddoCodeList!=null)
    		 logger.info("After query execution for DDO Code " + ddoCodeList.size());
    		
    		OrgDdoMst ddoMst = null; 
    		if(ddoCodeList!=null && ddoCodeList.size()>0){
    			ddoMst = ddoCodeList.get(0);
    		}
    		
    		if(ddoMst!=null) {
    			ddo_code=ddoMst.getDdoCode();
    		}
    		logger.info("DDO Code is " + ddo_code);

			List billNoList = payBillDAO1.getBillNo(finYearId, locId,ddo_code);
			ArrayList<HrPayBillHeadCustomVO> billList = new ArrayList<HrPayBillHeadCustomVO>(); 
			for(Iterator itr=billNoList.iterator();itr.hasNext();)
    		{
    			HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
    			Object[] row = (Object[])itr.next();
    			String billNo = row[1].toString();
    			String billHeadId = row[0].toString();
    			billNoCustomVO.setBillId(billNo);
    			billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
    			billList.add(billNoCustomVO);
    		}
			
			//passMap.put("empActList", empActList);
			//passMap.put("empDctList", empDctList);
			objectArgs.put("billNoList", billList);
			objectArgs.put("empActData", empActDetails);
			objectArgs.put("empActDataSize", empActDetails.size());
			objectArgs.put("empDctData", empDctDetails);
			objectArgs.put("empDctDataSize", empDctDetails.size());
			objectArgs.put("action", action);
			objectArgs.put("msg", msg);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
			resObj.setViewName("updatePayBillStatus");
			//System.out.println("Returning >>>>>>>>>>>>>>> screen " );
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			resObj.setResultCode(-1);
			resObj.setThrowable(e);
			resObj.setViewName("errorPage");
			
		}
		return resObj;
	}
	
	
	public ResultObject searchEmps(Map objectArgs)
	{
		logger.info("into service method of update pay bill status..");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			boolean duplicateFlag = false;
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			
						
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
			
			
			Map passMap = new HashMap();
			passMap.put("loginDetailsMap", loginDetailsMap);
			
			PayBillActiationStatusDAoImpl payBillActivationDAO = new PayBillActiationStatusDAoImpl(HrPaybillActivationStatusMpg.class,serv.getSessionFactory());
			
			String empName = "%";
			if(!empName.equals("") ||empName!=null )
			{
				 empName= objectArgs.get("name").toString();
			}
			long bill=-1;
			String billNum = objectArgs.get("bill").toString();
			/*System.out.println("bill number is "+billNum);
			System.out.println("bill number is "+billNum);
			System.out.println("bill number is "+billNum);
			System.out.println("bill number is "+billNum);
			System.out.println("bill number is "+billNum);
			System.out.println("bill number is "+billNum);
			System.out.println("bill number is "+billNum);
				System.out.println("bill number is "+billNum);*/
				bill = Long.parseLong(billNum);
			
			String action = objectArgs.get("action").toString();
			
			
			List empActList=payBillActivationDAO.getSpecificDataAct(empName, bill, langId, locId);
			List empDctList=payBillActivationDAO.getSpecificDataDct(empName, bill, langId, locId);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			
			
			Object[] data = null;
			int i=0;
			List<HrUpdatePayBillStatus> empActDetails = new ArrayList<HrUpdatePayBillStatus>();
			List<HrUpdatePayBillStatus> empDctDetails = new ArrayList<HrUpdatePayBillStatus>();
			for(i=0;i<empActList.size();i++)
			{
				HrUpdatePayBillStatus emp = new HrUpdatePayBillStatus();
				data=(Object[])empActList.get(i);
				long post_Id= StringUtility.convertToLong(data[0].toString());
				emp.setPostId(post_Id);
				String name = data[1].toString();
				emp.setEmpName(name);
				String postName = data[2].toString();
				emp.setPostName(postName);
				Date strtDate = null;
				Date edDate = null;
				String startDate="";
				String endDate="";
				if(data[3]!=null)
				{
					//System.out.println();
					//System.out.println(data[3]);
					 strtDate =sdf.parse(data[3].toString());
					 startDate=sdf1.format(strtDate);
					 
				}
				if(data[4]!=null)
				{
					edDate =sdf.parse(data[4].toString());
					endDate= sdf1.format(edDate);
				}
					
				emp.setStartDate(strtDate);
				emp.setEndDate(edDate);
				empActDetails.add(emp);
				
			
			}
			
			for(i=0;i<empDctList.size();i++)
			{
				
				
				HrUpdatePayBillStatus emp = new HrUpdatePayBillStatus();
				data=(Object[])empDctList.get(i);
				long post_Id= StringUtility.convertToLong(data[0].toString());
				emp.setPostId(post_Id);
				String name = data[1].toString();
				emp.setEmpName(name);
				String postName = data[2].toString();
				emp.setPostName(postName);
				Date strtDate = null;
				Date edDate = null;
				String startDate="";
				String endDate="";
				if(data[3]!=null)
				{
					 strtDate =sdf.parse(data[3].toString());
					 startDate=sdf1.format(strtDate);
					 
				}
				if(data[4]!=null)
				{
					edDate =sdf.parse(data[4].toString());
					endDate= sdf1.format(edDate);
				}
					
				emp.setStartDate(strtDate);
				emp.setEndDate(edDate);
				empDctDetails.add(emp);
				
			
			}
			
			PayBillDAOImpl payBillDAO1 = new PayBillDAOImpl(HrPayPaybill.class,
					serv.getSessionFactory());
			//ArrayList billList = new ArrayList();
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("resources.Payroll");
			long finYearId = Long.parseLong(resourceBundle
					.getString("finYearId"));
			
			String ddo_code=null;
    		
    		List<OrgDdoMst> ddoCodeList = payBillDAO1.getDDOCodeByLoggedInPost(postId);
    		if(ddoCodeList!=null)
    		 logger.info("After query execution for DDO Code " + ddoCodeList.size());
    		
    		OrgDdoMst ddoMst = null; 
    		if(ddoCodeList!=null && ddoCodeList.size()>0){
    			ddoMst = ddoCodeList.get(0);
    		}
    		
    		if(ddoMst!=null) {
    			ddo_code=ddoMst.getDdoCode();
    		}
    		logger.info("DDO Code is " + ddo_code);

			List billNoList = payBillDAO1.getBillNo(finYearId, locId, ddo_code);
			ArrayList<HrPayBillHeadCustomVO> billList = new ArrayList<HrPayBillHeadCustomVO>(); 
			for(Iterator itr=billNoList.iterator();itr.hasNext();)
    		{
    			HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
    			Object[] row = (Object[])itr.next();
    			String billNo = row[1].toString();
    			String billHeadId = row[0].toString();
    			billNoCustomVO.setBillId(billNo);
    			billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
    			billList.add(billNoCustomVO);
    		}
			
			//passMap.put("empActList", empActList);
			//passMap.put("empDctList", empDctList);
			objectArgs.put("billNoList", billList);
			objectArgs.put("empActData", empActDetails);
			objectArgs.put("empActDataSize", empActDetails.size());
			objectArgs.put("empDctData", empDctDetails);
			objectArgs.put("empDctDataSize", empDctDetails.size());
			objectArgs.put("action", action);
			objectArgs.put("msg", msg);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
			resObj.setViewName("updatePayBillStatus");
			//System.out.println("Returning >>>>>>>>>>>>>>> screen " );

			
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
			resObj.setResultCode(-1);
			resObj.setThrowable(e);
			resObj.setViewName("errorPage");
		}
		return resObj;
		
	}

}
