package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.PayBillActiationStatusDAoImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPaybillActivationStatusMpg;
import com.tcs.sgv.eis.valueobject.HrUpdatePayBillStatus;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;

public class DeactivatePaybillServiceImpl extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);	

	public ResultObject deactivateEmployeePayBill(Map objectArgs)
	{
		try
		{
			
			logger.info("!!!!!!!!!-------in service by japen----------!!!!!!!!!!!");			
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		
		Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		String flag = objectArgs.get("flag").toString();
		
		String[] postIds = (String[]) objectArgs.get("postIds");
		String[] startDates =null;
		String[] endDates=null;
		
		startDates = (String[]) objectArgs.get("startDates");
		endDates = (String[]) objectArgs.get("endDates");
		
		
		
		
		//boolean duplicateFlag = false;
		
		long userId = StringUtility.convertToLong(loginDetailsMap.get(
		"userId").toString());
		
		long locId = StringUtility.convertToLong(loginDetailsMap.get(
		"locationId").toString());
		long langId = StringUtility.convertToLong(loginDetailsMap.get(
		"langId").toString());
		
		long postId;
		
		ArrayList<HrPaybillActivationStatusMpg> dataList = new ArrayList<HrPaybillActivationStatusMpg>();
		PayBillActiationStatusDAoImpl pba = new PayBillActiationStatusDAoImpl(HrPaybillActivationStatusMpg.class,serv.getSessionFactory());
		
		HrPaybillActivationStatusMpg data;
		
		int activation=1;
		Date updatedDate = null;
		IdGenerator idGenerator = new IdGenerator();
		
		for(int i=0;i<postIds.length;i++)
		{
			postId=Long.parseLong(postIds[i]);
			data = pba.searchPost(postId);
			
			if(flag.equalsIgnoreCase("D"))
			{
				activation = 0;
				
			}
			else if(flag.equalsIgnoreCase("A"))
			{
				activation = 1;
			}
			else
			{
				throw new Exception();
			}
			
			
			
			long loggedInpostId =Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			
			//OrgPostMstDaoImpl a = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst loggedInOrgPost = orgPostMstDaoImpl.read(loggedInpostId);
			OrgPostMst orgPostMpgPostId = orgPostMstDaoImpl.read(postId);
			OrgPostMst orgPostMpgUpdatededBy = null;
			OrgPostMst orgPostMpgUpdatededByPost=null;
			
			
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
			
			Date startDate=null;
			Date endDate=null;
			if(flag.equalsIgnoreCase("D"))
			{
				startDate =StringUtility.convertStringToDate(startDates[i]);
				endDate = StringUtility.convertStringToDate(endDates[i]);
			}
			
			if(data==null)
			{
				OrgPostMst loggedInOrgPostCreat =loggedInOrgPost;
				
				//logger.info("Japen ::::: generating pk");
				long activationId=idGenerator.PKGenerator("hr_paybill_activation_status",objectArgs);
				//long activationId= IDGenerateDelegate.getNextId("hr_paybill_activation_status", objectArgs);
				
				
				Date createdDate = new java.util.Date();
				updatedDate = null;
				
				data= new HrPaybillActivationStatusMpg(activationId,//// Pk
						activation, /// status 1 for active 2 for deactive
						startDate,  /// entered by user start date of deactivation
						endDate,    /// end date of deactivation
						createdDate, /// sysdate for newly created
						updatedDate, /// null for newly created
						orgPostMpgPostId, /// post id for which pay bill is to be deactivated
						loggedInOrgPostCreat, ///  created by logged in user
						orgPostMpgUpdatededBy, /// updated by null for newly created
						orgPostMpgUpdatededByPost,// updated by post null for newly created
						loggedInOrgPostCreat,// created by post --same as created by --logged in user
						cmnLocationMst); /// location at which updated
				
				dataList.add(data);
				//pba.create(data);
				
			}
			
			else
			{
				data.setActivation(activation);
				if(flag.equalsIgnoreCase("D"))
				{
					data.setEndDate(endDate);
					data.setStartDate(startDate);
				}
				data.setUpdatedDate(new java.util.Date());
				data.setOrgPostMpgUpdatededBy(loggedInOrgPost);
				data.setOrgPostMpgUpdatededByPost(loggedInOrgPost);
				data.setCmnLocationMstLocation(cmnLocationMst);
				
				dataList.add(data);
				//pba.update(data);
				
				
			}
		}
		
		
		for(int i=0;i<dataList.size();i++)
		{
			serv.getSessionFactory().getCurrentSession().saveOrUpdate(dataList.get(i));
			
		}
		
		String action="";
		String msg="" ;
		/*
		UpdatePaybillStatusServiceImpl obj = new UpdatePaybillStatusServiceImpl();
		
		ResultObject res = obj.getLocationEmployees(objectArgs);
		Map resultMap = (HashMap)res.getResultValue();
		List<HrUpdatePayBillStatus> empActData = (List<HrUpdatePayBillStatus>)resultMap.get("empActData");
		List<HrUpdatePayBillStatus> empDctData = (List<HrUpdatePayBillStatus>)resultMap.get("empDctData");
		
		
		
		
		
		objectArgs.put("empActData", empActData);
		objectArgs.put("empActDataSize", empActData.size());
		objectArgs.put("empDctData", empDctData);
		objectArgs.put("empDctDataSize", empDctData.size());
		objectArgs.put("action", action);
		objectArgs.put("msg", msg);
		logger.info();
		logger.info("Act Size>>>>>>>>>>>>>>>>>>>>>>>>"+empActData.size());
		logger.info("Dct Size>>>>>>>>>>>>>>>>>>>>>>>>"+empDctData.size());
		logger.info();
		*/
		/*PayBillActiationStatusDAoImpl dao = new PayBillActiationStatusDAoImpl(HrPaybillActivationStatusMpg.class,serv.getSessionFactory());
		List empActList =dao.getActiveData(langId, locId);
		List empDctList = dao.getDeactiveData(langId, locId);
		
		////newly added 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		
		
		Object[] dataRow = null;
		int i=0;
		List<HrUpdatePayBillStatus> empActDetails = new ArrayList<HrUpdatePayBillStatus>();
		List<HrUpdatePayBillStatus> empDctDetails = new ArrayList<HrUpdatePayBillStatus>();
		for(i=0;i<empActList.size();i++)
		{
			HrUpdatePayBillStatus emp = new HrUpdatePayBillStatus();
			dataRow=(Object[])empActList.get(i);
			long post_Id= StringUtility.convertToLong(dataRow[0].toString());
			emp.setPostId(post_Id);
			String name = dataRow[1].toString();
			emp.setEmpName(name);
			String postName = dataRow[2].toString();
			emp.setPostName(postName);
			Date strtDate = null;
			Date edDate = null;
			String startDate="";
			String endDate="";
			if(dataRow[3]!=null)
			{
				//logger.info();
				//logger.info(data[3]);
				 strtDate =sdf.parse(dataRow[3].toString());
				 startDate=sdf1.format(strtDate);
				 
			}
			if(dataRow[4]!=null)
			{
				edDate =sdf.parse(dataRow[4].toString());
				endDate= sdf1.format(edDate);
			}
				
			emp.setStartDate(strtDate);
			emp.setEndDate(edDate);
			empActDetails.add(emp);
			
		
		}
		
		for(i=0;i<empDctList.size();i++)
		{
			
			
			HrUpdatePayBillStatus emp = new HrUpdatePayBillStatus();
			dataRow=(Object[])empDctList.get(i);
			long post_Id= StringUtility.convertToLong(dataRow[0].toString());
			emp.setPostId(post_Id);
			String name = dataRow[1].toString();
			emp.setEmpName(name);
			String postName = dataRow[2].toString();
			emp.setPostName(postName);
			Date strtDate = null;
			Date edDate = null;
			String startDate="";
			String endDate="";
			if(dataRow[3]!=null)
			{
				 strtDate =sdf.parse(dataRow[3].toString());
				 startDate=sdf1.format(strtDate);
				 
			}
			if(dataRow[4]!=null)
			{
				edDate =sdf.parse(dataRow[4].toString());
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

		List billNoList = payBillDAO1.getBillNo(finYearId, locId);
		ArrayList<HrPayBillHeadCustomVO> billList = new ArrayList<HrPayBillHeadCustomVO>(); 
		for(Iterator itr=billNoList.iterator();itr.hasNext();)
		{
			HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
			Object[] row = (Object[])itr.next();
			String billNo = row[1].toString();
			String billHeadId = row[0].toString();
			billNoCustomVO.setBillId(Long.parseLong(billNo));
			billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
			billList.add(billNoCustomVO);
		}
		
		//passMap.put("empActList", empActList);
		//passMap.put("empDctList", empDctList);
		

		///ended added
		
		
		if(flag.equalsIgnoreCase("D"))
		{
			action ="a";
			msg="Records Deactivated Successfully";
		}
		else if(flag.equalsIgnoreCase("A"))
		{
			action="d";
			msg="Records Activated Successfully";
		}
		else
		{
			throw new Exception();
		}
		objectArgs.put("billNoList", billList);
		objectArgs.put("empActData", empActDetails);
		objectArgs.put("empActDataSize", empActDetails.size());
		objectArgs.put("empDctData", empDctDetails);
		objectArgs.put("empDctDataSize", empDctDetails.size());
		objectArgs.put("action", action);
		objectArgs.put("msg", msg);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		retObj.setResultValue(objectArgs);
		retObj.setViewName("updatePayBillStatus");*/
		
		if(flag.equalsIgnoreCase("D"))
		{
			action ="a";
			msg="Records Deactivated Successfully";
		}
		else if(flag.equalsIgnoreCase("A"))
		{
			action="d";
			msg="Records Activated Successfully";
		}
		else
		{
			throw new Exception();
		}
		objectArgs.put("action", action);
		objectArgs.put("msg", msg);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		retObj.setResultValue(objectArgs);
		retObj.setViewName("redirectToUpdatePayBillStatus");
		
		}
		catch(Exception e)
		{
			ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);						
			logger.info("Exception Ocuures...//**//**//**");
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setResultCode(-1);
			 resultObject.setViewName("errorInsert");		
			 logger.error("Error is: "+ e.getMessage());
			return resultObject;
		}
		logger.info("::::::::returning from deactivation::::::");
		return retObj;
	}
}
