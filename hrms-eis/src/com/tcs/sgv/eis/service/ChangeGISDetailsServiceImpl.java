package com.tcs.sgv.eis.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.ChangeGISDetailsDAO;
import com.tcs.sgv.eis.dao.ChangeGISDetailsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OrderMstDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisGISDtls;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class ChangeGISDetailsServiceImpl extends ServiceImpl {
	

	public ChangeGISDetailsServiceImpl()
	{
	
		logger.info("inside constructior");
	
	}
	
	
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle constantBundle = ResourceBundle.getBundle("../resources/Payroll");
	
	@SuppressWarnings("unchecked")
	public ResultObject fillChangeGISComponents(Map objectArgs){
		ResultObject resultObject = new ResultObject();
		
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		
		ChangeGISDetailsDAO changeGISDetailsDAOImpl = new ChangeGISDetailsDAOImpl(HrEisGISDtls.class,serv.getSessionFactory());
		
		Map loginDetailsMap=(Map)objectArgs.get("baseLoginMap");
		
		List GISGradeDataList 	= null;
		List GISOrderDataList 	= null; 
			
		try{
			
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			
			GISGradeDataList = changeGISDetailsDAOImpl.getGISGradeData(langId);
				
			GISOrderDataList = 	changeGISDetailsDAOImpl.getGISOrderData(locId);
			

			objectArgs.put("GISGradeDataList", GISGradeDataList);
			objectArgs.put("GISOrderDataList", GISOrderDataList);

		}catch( Exception e){
			
		}
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("ChangeGISDetails");

		return resultObject;
	}
	
	
	@SuppressWarnings("unchecked")
	public ResultObject fillEmpChangeGISDtls(Map objectArgs){
		
		ResultObject resultObject = new ResultObject();
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		
		ChangeGISDetailsDAO changeGISDetailsDAOImpl = new ChangeGISDetailsDAOImpl(HrEisGISDtls.class,serv.getSessionFactory());
	
		Map loginDetailsMap=(Map)objectArgs.get("baseLoginMap");

		
		try{
			
			
			long empId = (StringUtility.getParameter("empId",request)!=null&&!(StringUtility.getParameter("empId",request).equals(""))?Long.parseLong(StringUtility.getParameter("empId",request)):0);
			
			List GISempDataList = changeGISDetailsDAOImpl.getEmpGISData(empId);

			List getEmpData = changeGISDetailsDAOImpl.getEmpData(empId);
			
			HrEisGISDtls hrEisGISDtls = new HrEisGISDtls();
			hrEisGISDtls =(HrEisGISDtls) getEmpData.get(0);
			logger.info("hrEisGISDtls----Kishan"+hrEisGISDtls.getHrPayDeducTypeMst().getDeducName());
			
			String sevarthId = hrEisGISDtls.getHrEisEmpMst().getSevarthEmpCode();
			
			List empDOJList  = changeGISDetailsDAOImpl.getEmpDOJ(empId);
			Date empDOJ = (Date) empDOJList.get(0);
			logger.info("empDOJ:::::::::::::::::::::: " + empDOJ);

			DeducTypeMasterDAOImpl hrPayDeducTypeMstDaoImpl = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class, serv.getSessionFactory()); 			
			ArrayList<HrPayDeducTypeMst> GISDeducDataList = new ArrayList();
			
			List GISDeductionDataList = changeGISDetailsDAOImpl.getGISDeducData(empId);
			logger.info("GISDeductionDataList size::::::::::::::::::::::::::::::::::: "+GISDeductionDataList.size());
			long deducId = 0;
			
			if(GISDeductionDataList!=null && GISDeductionDataList.size()!=0)
			{
				logger.info("Inside if:::::::::");
				for(int i =0; i< GISDeductionDataList.size(); i++)
				{
					logger.info("Inside for loop :::::::::");
					logger.info("GISDeductionDataList index of "+i+" ::::" + GISDeductionDataList.get(i));

					deducId = Long.parseLong(GISDeductionDataList.get(i).toString());
					
					logger.info("deducId :::::::::"+ deducId);

					GISDeducDataList.add(hrPayDeducTypeMstDaoImpl.read(deducId));
					
					}
			}
			
			logger.info("GISDeducDataList size::::::::::::::: "+GISDeducDataList.size());
			

		String employeeName="";
		long employeeId=0;
			if(GISempDataList!=null && GISempDataList.size()!=0)
			{
				for(Iterator itr=GISempDataList.iterator();itr.hasNext();)
				{

					Object[] rowList = (Object[])itr.next();

					if(rowList[0] != null)
					{
						employeeName = rowList[0].toString().trim();
					}
					if(rowList[1] != null)
					{
						employeeId = Long.parseLong(rowList[1].toString().trim());
					}

				}
			}
			
			objectArgs.put("employeeName", employeeName);
			objectArgs.put("employeeId", employeeId);
			objectArgs.put("GISempData", GISempDataList);
			objectArgs.put("getEmpData", hrEisGISDtls);
			objectArgs.put("empDOJ", empDOJ);
			objectArgs.put("sevarthId", sevarthId);
			objectArgs.put("GISDeducDataList", GISDeducDataList);
			
		}catch( Exception e){
			
		}
		List GISGradeDataList 	= null;
		List GISOrderDataList 		= null; 
			
		try{
			
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			
			GISGradeDataList = changeGISDetailsDAOImpl.getGISGradeData(langId);
				
			GISOrderDataList = 	changeGISDetailsDAOImpl.getGISOrderData(locId);

			objectArgs.put("GISGradeDataList", GISGradeDataList);
			objectArgs.put("GISOrderDataList", GISOrderDataList);

		}catch( Exception e){
			
		}
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("ChangeGISDetails");
		
		return resultObject;
	}
	
	@SuppressWarnings("unchecked")
	public ResultObject getGISOrderDate(Map objectArgs) throws ParseException
	{
		long orderId=0;
		logger.info("*********getGISOrderDate*********");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		Map voToService = (Map) (Map) objectArgs.get("voToServiceMap");
		orderId = Long.parseLong((String) voToService.get("orderId"));
		
		ChangeGISDetailsDAO changeGISDetailsDAOImpl = new ChangeGISDetailsDAOImpl(HrEisGISDtls.class,serv.getSessionFactory());
		List getGISOrderDate = changeGISDetailsDAOImpl.getGISOrderDate(orderId);
		
		Date orderDate = (Date) getGISOrderDate.get(0);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String newDate = sdf.format(orderDate);
		logger.info("newDate:::::::::::::: "+newDate);
		StringBuffer getDate=new StringBuffer();
		
		getDate.append("<getOldOrderDate>");
		getDate.append("<orderDate>").append(newDate).append("</orderDate>");
		//getDate.append("<orderDateNew>").append(orderDate).append("</orderDateNew>");
		getDate.append("</getOldOrderDate>");   
		
    	Map map = new HashMap();
        String getOldOrderDate = new AjaxXmlBuilder().addItem("ajax_key", getDate.toString()).toString();
        map.put("ajaxKey", getOldOrderDate);
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		resultObject.setResultValue(map);
		resultObject.setViewName("ajaxData");
		logger.info(" SERVICE COMPLETE :");
		return resultObject;
	
	}
	
	@SuppressWarnings("unchecked")
	public ResultObject updateEmpChangedGISDataService(Map objectArgs){
		ResultObject resultObject = new ResultObject();
		
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		logger.info("Inside updateEmpChangedGISDataService::::::::");
		Map loginDetailsMap=(Map)objectArgs.get("baseLoginMap");
		
		try{
				
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			
			long empGISId = Long.parseLong(objectArgs.get("empGISId").toString());
			
			long gisRevApplicableId = Long.parseLong(objectArgs.get("gisRevApplicableId").toString());
			
			long gisGroupRevId = Long.parseLong(objectArgs.get("gisGroupRevId").toString());
			
			String revMemshipdate = objectArgs.get("revMemshipdate").toString();
			
			String ordername = objectArgs.get("ordername").toString();
			
			String orderDate = objectArgs.get("orderDate").toString();
			
			String remarks = objectArgs.get("remarks").toString();
			
			Date ordDate = null;
			Date revMemDate = null;
			if(revMemshipdate!=null && !revMemshipdate.equals(" ") && !revMemshipdate.equals(""))
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				revMemDate = sdf.parse(revMemshipdate);
				
			}
			
			if(orderDate!=null && !orderDate.equals(" "))
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				ordDate = sdf.parse(orderDate);
			}
			
			
			Date sysDate = new Date();
			
			OrgGradeMstDaoImpl  orgGradeMstdaoimpl = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
			OrgGradeMst orgGradeMst= orgGradeMstdaoimpl.read(gisGroupRevId);
			
			/*OrderMstDAOImpl orderMstDAOImpl = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
			HrPayOrderMst hrPayOrderMst = orderMstDAOImpl.read(orderId);*/
			
			DeducTypeMasterDAOImpl deducTypeMasterDAOImpl  = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			HrPayDeducTypeMst hrPayDeducTypeMst = deducTypeMasterDAOImpl.read(gisRevApplicableId);
			
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			
			ChangeGISDetailsDAO changeGISDetailsDAOImpl = new ChangeGISDetailsDAOImpl(HrEisGISDtls.class,serv.getSessionFactory());
			HrEisGISDtls hrEisGISDtls = changeGISDetailsDAOImpl.read(empGISId);
			
			
			hrEisGISDtls.setGISOrderDate(ordDate);
			hrEisGISDtls.setOrgGradeMst(orgGradeMst);
			hrEisGISDtls.setMembershipDate(revMemDate);
			hrEisGISDtls.setHrPayOrderMst(ordername);
			hrEisGISDtls.setHrPayDeducTypeMst(hrPayDeducTypeMst);
			hrEisGISDtls.setRemarks(remarks);
			hrEisGISDtls.setUpdatedByDate(sysDate);
			hrEisGISDtls.setOrgUserMstByUpdatedByUser(orgUserMst);
			hrEisGISDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
			
			changeGISDetailsDAOImpl.update(hrEisGISDtls);
			
			objectArgs.put("msg", "Record Updated Successfully");
			 logger.info("In ChangeGISDetailsServiceImpl Record Updated Successfully");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ChangeGISDetails");
			

		}catch( Exception e){
			
		}
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("ChangeGISDetails");

		return resultObject;
	}
	
	
	@SuppressWarnings("unchecked")
	public ResultObject insertEmpChangedGISDataService(Map objectArgs) throws Exception{
		ResultObject resultObject = new ResultObject();
		
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		logger.info("Inside insertEmpChangedGISDataService::::::::");
		Map loginDetailsMap=(Map)objectArgs.get("baseLoginMap");
		
			long locId  = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			
			long dcpsEmpId = Long.parseLong(objectArgs.get("dcpsEmpId").toString());
			logger.info("dcpsEmpId::::::::: "+ dcpsEmpId);
			ChangeGISDetailsDAO changeGISDetailsDAOImpl = new ChangeGISDetailsDAOImpl(HrEisGISDtls.class,serv.getSessionFactory());

			List GISDataFromDCPS = changeGISDetailsDAOImpl.getGISDataFromDCPS(dcpsEmpId);
			
			
			long GISApplicable = 0;
			long GISGroup = 0;
			String memshipdate = null;
			long empId  = 0;
				if(GISDataFromDCPS!=null && GISDataFromDCPS.size()!=0)
				{
					for(Iterator itr=GISDataFromDCPS.iterator();itr.hasNext();)
					{

						Object[] rowList = (Object[])itr.next();

						if(rowList[0] != null)
						{
							GISApplicable = Long.parseLong(rowList[0].toString().trim());
							if(GISApplicable == 700212 || GISApplicable == -1 || GISApplicable ==0){
								GISApplicable = -1;
							}
							logger.info("GISApplicable::::::::: "+ GISApplicable);
						}
						if(rowList[1] != null)
						{
							GISGroup = Long.parseLong(rowList[1].toString().trim());
							logger.info("GISGroup::::::::: "+ GISGroup);
							if(GISGroup == 700220 || GISGroup == -1 || GISGroup == 0)
								GISGroup = 100115;
							
						}
						if(rowList[2] != null)
						{
							memshipdate = rowList[2].toString();
							logger.info("memshipdate::::::::: "+ memshipdate);
						}
						if(rowList[3] != null)
						{
							empId = Long.parseLong(rowList[3].toString().trim());
							logger.info("empId::::::::: "+ empId);
						}
						

					}
				}
			
			Date memDate = null;
			if(memshipdate!=null && !memshipdate.equals(" ") && !memshipdate.equals(""))
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
				memDate = sdf.parse(sdf.format(sdfParse.parse(memshipdate)));	
				
			}
			
			Date sysDate = new Date();
			
		
			PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			//long eisEmpId= payBillDAOImpl.getEmpIdFromHreisempMst(empId);
			long orgEmpId= (objectArgs.get("orgEmpMstId").toString())!= null ? Long.parseLong(objectArgs.get("orgEmpMstId").toString()):0;
			logger.info("org emop id is "+orgEmpId);
			long eisEmpId= payBillDAOImpl.getEmpIdFromHreisempMst(orgEmpId);
			logger.info("eis empId is "+eisEmpId);
			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			HrEisEmpMst hrEisEmpMst = empInfoDAOImpl.read(eisEmpId); 
			
			ResourceBundle rs = ResourceBundle.getBundle("resources.Payroll");
			
			OrgGradeMstDaoImpl  orgGradeMstdaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
			
			long gradeId = Long.parseLong(rs.getString(String.valueOf(GISGroup)));
			logger.info("Grade id according to gis grp is " + gradeId);
			OrgGradeMst orgGradeMst= orgGradeMstdaoImpl.read(gradeId);
			long deducId = 0;
			if(GISApplicable != -1)
				deducId = Long.parseLong(rs.getString(String.valueOf(GISApplicable)));
			logger.info("deducId::::::::: "+ deducId);
			
			
			DeducTypeMasterDAOImpl deducTypeMasterDAOImpl  = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			HrPayDeducTypeMst hrPayDeducTypeMst = deducTypeMasterDAOImpl.read(deducId);
			
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);

			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
			
			HrEisGISDtls hrEisGISDtls = new HrEisGISDtls();
			
			IdGenerator idgen=new IdGenerator();
			Long empGISId =idgen.PKGeneratorWebService("HR_EIS_GIS_DTLS", serv, userId, langId, locId);
			
			hrEisGISDtls.setEmpGISId(empGISId);
			hrEisGISDtls.setHrEisEmpMst(hrEisEmpMst);
			hrEisGISDtls.setOrgGradeMst(orgGradeMst);
			hrEisGISDtls.setMembershipDate(memDate);
			hrEisGISDtls.setHrPayDeducTypeMst(hrPayDeducTypeMst);
			hrEisGISDtls.setCreatedByDate(sysDate);
			hrEisGISDtls.setOrgUserMstByCreatedByUser(orgUserMst);
			hrEisGISDtls.setOrgPostMstByCreatedByPost(orgPostMst);
			hrEisGISDtls.setCmnDatabaseMst(cmnDatabaseMst);
			hrEisGISDtls.setCmnLocationMst(cmnLocationMst);
			
			long gisCreated= changeGISDetailsDAOImpl.create(hrEisGISDtls);
			logger.info("hr_eis_gis_dtls created  "+gisCreated);
			objectArgs.put("msg", "Record Inserted Successfully");
			 logger.info("In ChangeGISDetailsServiceImpl Record Inserted Successfully");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ChangeGISDetails");
			

		
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("ChangeGISDetails");
			

		return resultObject;
	}
	
}
