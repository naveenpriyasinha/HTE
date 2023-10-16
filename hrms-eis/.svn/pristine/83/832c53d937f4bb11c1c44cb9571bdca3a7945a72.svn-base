package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.eis.dao.OrderMstDAO;
import com.tcs.sgv.eis.dao.OrderMstDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;



public class OrderMasterService extends ServiceImpl  {
	
	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	/* Global Variable for User Loc Map */
	static HashMap sMapUserLoc = new HashMap();

	/* Global Variable for User Location */
	String gStrUserLocation = null;

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/eis/zp/zpDDOOffice/DCPSConstantsZP");

	/*
	 * Function to save the session specific details
	 */
	private void setSessionInfo(Map inputMap)
	{

		try
		{
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gDtCurrDt = SessionHelper.getCurDate();
		}
		catch (Exception e)
		{

		}

	}

	
	private static final long orderid = 0;
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	//This method will insert and update the order data
	
	public ResultObject insertOrderMasterDtls(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			ResultObject resultObj = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
			Map resultMap = (Map) resultObj.getResultValue();
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			logger.info("Inside Insert Master Details Service");
			logger.info("Edit Mode From VOGEN "+objectArgs.get("edit"));
			HrPayOrderMst orderMst = (HrPayOrderMst)objectArgs.get("orderMst");//object of VOGEN
			HrPayOrderMst hrPayOrderMst;	// Newer Object of the HrPayOrderMst
			
			OrderMstDAOImpl ordermstDAOImpl = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());//object of DAOIMPL
			
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		//	long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());//For Language independent
			
            String editFromVO = objectArgs.get("edit").toString();//Flag for edit mode yes or no
            logger.info("Flag which display go in insert or update " + editFromVO);
            
            long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
		/*	long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);*/
			
		/*	long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	        logger.info("insertBankMasterDtls Loc Id is:-->" + dbId + " " + locId);*/
			
	        long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			
			//String locationCode = loginDetailsMap.get("locationId").toString();
		
		    String cmbgrtype = !StringUtility.getParameter("cmbgrtype",request).equalsIgnoreCase("") ? StringUtility.getParameter("cmbgrtype",request) : "";
		    logger.info("MonthSTR:::"+cmbgrtype); 
			String grtype = StringUtility.getParameter("cmbgrtype", request);
			
			logger.info("vzavv:::::"+grtype);
			Date sysdate = new Date();
			long attachment_Id=0;
			if(resultMap.get("AttachmentId_orderId")!=null)
			 attachment_Id = (Long) resultMap.get("AttachmentId_orderId"); 
			logger.info("after inserting attachment! attach_id : " + attachment_Id);		
			
            
            if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))            	
			{
            	
            	long orderid = orderMst.getOrderId();
            	logger.info("The Order Id  inside Update Mode is------>"+orderid);
            	
       		    hrPayOrderMst = ordermstDAOImpl.read(orderid);//Reading orderid from Vo
				logger.info("INSIDE UPDATE insertOrderMasterDtls");
				logger.info("INSIDE UPDATE OrderId"+orderMst.getOrderId());
				logger.info("INSIDE UPDATE OrderName"+orderMst.getOrderName());
				logger.info("INSIDE UPDATE Order Date"+orderMst.getOrderDate());
				logger.info("INSIDE UPDATE Order Location Code"+orderMst.getLocationCode());
				String locCode = StringUtility.getParameter("cmbDept", request);
				hrPayOrderMst.setLocationCode(locCode);//Setting Order Name in vo
				hrPayOrderMst.setOrderName(orderMst.getOrderName());//Setting Order Name in vo
				hrPayOrderMst.setOrderDate(orderMst.getOrderDate());//setting Order Date in vo
				// Added By Urvin shah.
				hrPayOrderMst.setEndDate(orderMst.getEndDate());
				hrPayOrderMst.setGrtype(orderMst.getGrtype());// Setting Order end date.
				logger.info("End Date is:-"+orderMst.getGrtype());
				logger.info("End Date is:-"+orderMst.getEndDate());
				
				
				// End Urvin shah
				hrPayOrderMst.setUpdatedDate(sysdate);
				hrPayOrderMst.setOrgUserMstByUpdatedBy(orgUserMst);
				hrPayOrderMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				hrPayOrderMst.setAttachment_Id(attachment_Id);
				
				ordermstDAOImpl.update(hrPayOrderMst);//update Vo...VO ready to update
				msg=1;//for display message for update
			
        		logger.info("Updated successfully................");
			}
            
            
			else if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
			{
				
				logger.info("hii i m roshan");
				//Added By Mrugesh
				String encXML[] = StringUtility.getParameterValues("encXML",request); 
				if(encXML!=null && encXML.length>0)
		    	{
		    		int rowNumber = 0;
		    		String [] rowNumForAttachment = StringUtility.getParameterValues("encXML_rowNumber", request);
		    		long orderIdAttachment=0l;
		    							
					List result=FileUtility.xmlFilesToVoByXStream(encXML);
			    	logger.info("result size (external) is : " + result.size());
		    		
		    		Iterator it = result.iterator(); 
					while(it.hasNext())
					{
						HrPayOrderMst payOrderMst=(HrPayOrderMst) it.next();
						logger.info("INSIDE Insert Order Details");
						IdGenerator idGen = new IdGenerator();
						long orderId = idGen.PKGenerator("HR_PAY_ORDER_MST", objectArgs);
						logger.info("orderId:::"+orderId);
						
						
						payOrderMst.setOrderId(orderId);//setting order id
						payOrderMst.setCmnLanguageMst(cmnLanguageMst);
						payOrderMst.setTrnCounter(new Integer(1));
						payOrderMst.setOrgPostMstByCreatedByPost(orgPostMst);
						payOrderMst.setOrgUserMstByCreatedBy(orgUserMst);
						payOrderMst.setCreatedDate(sysdate);
						//
						logger.info("hi i m roshan for order master insertion");
						String locationCode =StringUtility.getParameter("depLoc", request);
						logger.info("locationCode:::::"+locationCode);
						payOrderMst.setLocationCode(locationCode);
						payOrderMst.setGrtype(Long.parseLong(grtype));
						String currRowNum=rowNumForAttachment[rowNumber];
						objectArgs.put("rowNumber",currRowNum);
						objectArgs.put("attachmentName","orderId");
						
						resultObject = serv.executeService("FILE_UPLOAD_VOGEN",objectArgs);
						objectArgs=(Map)resultObject.getResultValue();
						
						resultObject = serv.executeService("FILE_UPLOAD_SRVC",objectArgs);
						objectArgs=(Map)resultObject.getResultValue();
												
						 if(objectArgs.get("AttachmentId_orderId")!=null)
							{
							    orderIdAttachment = Long.parseLong(objectArgs.get("AttachmentId_orderId").toString());
								logger.info("orderIdAttachmentorderIdAttachment========" +orderIdAttachment);
								
							}
							
						payOrderMst.setAttachment_Id(orderIdAttachment);
						ordermstDAOImpl.create(payOrderMst);//ready to insert			
						logger.info("Inserted Successfully");
			
						 
						
						rowNumber++;
					}
		    	}
		    	//Ended By Mrugesh
											
			}
            
            if(msg==1)
    			objectArgs.put("MESSAGECODE",300006);//message code from frm_message_mst 300006 for Update
    		else
    			objectArgs.put("MESSAGECODE",300005);//message code from frm_message_mst 300005 for Insert
    					
    		resultObject.setResultCode(ErrorConstants.SUCCESS);
    		
    		resultObject.setResultValue(objectArgs);
    		if(msg==1)
    			resultObject.setViewName("orderMasteredit");//For Redirect from message to view jsp
    		else
    			resultObject.setViewName("orderMaster");//For Redirect from message to view jsp
    		
        logger.info("Inserted Sucssesfully and End of Insert/Update Method");
    		
    	}
    	catch(NullPointerException ne)
    	{
    		logger.info("Null Pointer Exception Ocuures...insertorderData");
    		logger.error("Error is: "+ ne.getMessage());
    		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
    		resultObject.setResultValue(objectArgs);
    		resultObject.setViewName("errorInsert");			
    	}
    	
    	catch(Exception e){
    		
    		logger.info("Exception Ocuures...insertOuterData");
    		 logger.error("Error is: "+ e.getMessage());
    		 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
    		 resultObject.setResultValue(objectArgs);
    		 resultObject.setViewName("errorInsert");
    	}
    	return resultObject;
    	}
	
//This method will fetch all hr_pay_order_mst data for display in the View, Insert and Edit Mode.
	
	public ResultObject getOrderData(Map objectArgs)
	{
		logger.info("getOrderData IN Order Master Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try {
			
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
			logger.info("locId roshan  "+locId);
			
		   ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	       Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
	       // By Urvin
	       //long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
	       CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
   		   String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
   			// End.
		   Map voToService = (Map)objectArgs.get("voToServiceMap");
		   String editFlag = (String)voToService.get("edit");//edit flag passing
		   logger.info("Flag Name "+editFlag );
		   long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		   CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
           
		   OrderMstDAO orderMasterDAO = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());//daoimpl object
		   HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		   HttpSession session=request.getSession();
	       //by rahul
		   PaybillHeadMpgDAOImpl paybillheadmpgdao = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
	       boolean isRoleAdminFlag = paybillheadmpgdao.isLoggedInUserAdmin(loginDetailsMap);
	       CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());	
	       List<CmnLookupMst> GRTYPEList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("GRTYPE", langId);
	       List grSubTypeList = orderMasterDAO.getSubGRType(10001198149L);
	       List locationList = null;
	       
	       
	       
	       //code to find ddo code by roshan
	       	Map loginMapR = (Map) (Map) objectArgs.get("baseLoginMap");
	   		long locIdR = StringUtility.convertToLong(loginMapR.get("locationId").toString()).longValue();
	   		logger.info("locId roshan  "+locIdR);
	   		PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
	   		List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locIdR);
	   		OrgDdoMst ddoMst  = null;
	   		if(ddoList!=null && ddoList.size()>0) {
			 ddoMst = ddoList.get(0);
	   		}
		
	   		String ddoCode = null;
	   		if(ddoMst!=null){
	   			ddoCode = ddoMst.getDdoCode();
	   		}
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
	   		String districtID=orderMasterDAO.districtName(ddoCode);
	   		logger.info("district id found is"+districtID);
	   		//code to find the taluka
	   		List talukaList=orderMasterDAO.allTaluka(districtID);
	   		//code to find the user type by roshan
	   		
	   		long countOfDDOCode=orderMasterDAO.findUsertype(ddoCode);
	   		//code to find the level by roshan
	   		long level=orderMasterDAO.findLevel(ddoCode);
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
	   		String displayAddNewEntry=null;
	   	
	       //end by roshan
	       
	       
	       
	       
	       if(isRoleAdminFlag)
			{
				logger.info("true::");
				locationList = locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId);
				logger.info("deptlist.size::"+ locationList.size());
			}
			else
			{
				logger.info("false::");
				String[] critariaArugs={"cmnLanguageMst.langId","locationCode"};
				Object[] valueArgus=new Object[2];
				valueArgus[0] = langId;
				valueArgus[1] = locationCode;
				locationList = locationDAO.getListByColumnAndValue(critariaArugs, valueArgus);
				
				
				logger.info("deptlist.size::"+ locationList.size());
			}
	       
	       //ended by rahul
	       objectArgs.put("locationCode", locationCode);
	       // Added By Urvin Shah for the Order Search
	       
	       String strSearchFlag = (voToService.get("orderSearchFlag")!=null && !voToService.get("orderSearchFlag").toString().equals(""))?voToService.get("orderSearchFlag").toString():"";	// OrderSearchFlag
	       logger.info("The Value of the SearchOrder Flag is:-"+strSearchFlag);
	       // if Flag's Value is 'y' then go for searching.
	       if(!strSearchFlag.equals("") && (strSearchFlag.equals("y") || strSearchFlag.equals("Y"))){
	    	   String strOrderName = (voToService.get("orderName")!=null && !voToService.get("orderName").toString().equals(""))?voToService.get("orderName").toString():"";
	    	   String strStartDate = (voToService.get("startDate")!=null && !voToService.get("startDate").toString().equals(""))?voToService.get("startDate").toString():"";
	    	   String strEndDate = (voToService.get("endDate")!=null && !voToService.get("endDate").toString().equals(""))?voToService.get("endDate").toString():"";
	    	   List<HrPayOrderMst> lstSearchOrders = orderMasterDAO.getSearchOrderData(strOrderName, strStartDate, strEndDate,locationCode);
	    	   objectArgs.put("actionList", lstSearchOrders);
			   resultObject.setResultCode(ErrorConstants.SUCCESS);
	           resultObject.setResultValue(objectArgs);
			   resultObject.setViewName("orderMasterView");//view Add Order Mode jsp
	       }
	       // End.
	       
	     // Start Added By Urvin shah for fetching the Location List In the Insert mode of Order Master.
	       else if(editFlag != null && editFlag.equals("N")) {		    	     
			    
	    	   String talukaId=null;
	    	   String ddoSelected=null;
	    	   
	    	   
	    	   if((StringUtility.getParameter("taluka", request)!=null)&&(StringUtility.getParameter("taluka", request)!="")&&(Long.parseLong(StringUtility.getParameter("taluka", request))!=-1)){
	    	   talukaId= StringUtility.getParameter("taluka", request);
	    	   }
	    	   
	    	   if((StringUtility.getParameter("ddoCode", request)!=null)&&(StringUtility.getParameter("ddoCode", request)!="")){
	    	   ddoSelected= StringUtility.getParameter("ddoCode", request);
	    	   }
	    	   String loggedInPost= loginDetailsMap.get("loggedInPost").toString();
	    	   logger.info("Inside my function----- "+loggedInPost);
	    	   locationList=orderMasterDAO.getSubDDOs(loggedInPost,talukaId,ddoSelected);
	    	   
	    	   	//added by roshan
	    	   
	    	   objectArgs.put("displayAddNewEntry", displayAddNewEntry);
	    	   objectArgs.put("talukaList", talukaList);
	    	   objectArgs.put("talukaId", talukaId);
	    	   objectArgs.put("ddoSelected", ddoSelected);
	    	   //end by roshan
	    	   
	    	   
	    	   
	    	   
	    	   objectArgs.put("deptList", locationList);
			   objectArgs.put("GRTYPEList", GRTYPEList);
			   
			   objectArgs.put("grSubTypeList", grSubTypeList);
			   resultObject.setResultCode(ErrorConstants.SUCCESS);
	           resultObject.setResultValue(objectArgs);
			   resultObject.setViewName("orderMaster");//view Add Order Mode jsp
		   }
		   // Ended By Urvin Shah.
		   else if(editFlag != null && editFlag.equals("Y")) {
			   
			   logger.info("hi i m here 3rd if");
			   logger.info("Inside Update Mode ");
			   long Orderid =Long.parseLong((String)voToService.get("orderid"));
			   logger.info("Order Id after parsing"+Orderid);
			   // Start By Urvin shah.	            
			   objectArgs.put("deptList", locationList);
			   objectArgs.put("GRTYPEList", GRTYPEList);
			   objectArgs.put("grSubTypeList", grSubTypeList);
			   
			   // End By Urvin shah
			   HrPayOrderMst actionList = (HrPayOrderMst)orderMasterDAO.getOrderMstDataByid(Orderid);//action list	            		     	   
			   logger.info("Order Name from HrPayOrderMstVO " + actionList.getOrderName());
			   logger.info("Order Name from HrPayOrderMstVO " + actionList.getAttachment_Id());
			   Long attach_id = actionList.getAttachment_Id();
			   logger.info("Attach_id : " + attach_id);
			   if(attach_id!=null)
			   {
				   CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());

				   CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(attach_id);

				   objectArgs.put("orderId",cmnAttachmentMst);
				   logger.info("cmnattachmentmst : " + cmnAttachmentMst);
			   }



			   //CmnLocationMst cmnLocationMst = locationDAO.getLocationVOByLocationCodeAndLangId(actionList.getLocationCode(), langId);
			   objectArgs.put("actionList", actionList);
			   objectArgs.put("cmnLocationMst", cmnLocationMst);
			   resultObject.setResultCode(ErrorConstants.SUCCESS);
			   resultObject.setResultValue(objectArgs);//passing map to result value for get data in jsp		      
			   resultObject.setViewName("orderMasteredit");//view edit jsp
		   }
		   else {  
			   //CmnLocationMst cmnLocationMst = locationDAO.read(locId);
			  
			   
			   logger.info("hi i m here 4th if");
			  
			   PayBillDAOImpl payDAOR = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				List<OrgDdoMst> ddoListR = payDAOR.getDDOCodeByLoggedInlocId(locId);
				OrgDdoMst ddoMstR  = null;
				if(ddoListR!=null && ddoListR.size()>0) {
					 ddoMstR = ddoListR.get(0);
				}
				
				String ddoCodeR = null;
				if(ddoMstR!=null)
				 ddoCodeR = ddoMstR.getDdoCode();
				
				
				
				logger.info("hi i am checking DDO CODE whethre it is of ZP institute type" +ddoCode);
				String TypeR =ddoCode.substring(0,2);
				logger.info("TypeOfSchoolbefore"+TypeR);
				Long TypeOfSchoolR=Long.valueOf(Type);
				
				logger.info("TypeOfSchool"+TypeOfSchoolR);
				
				//end by roshan
				
				/*if(TypeOfSchool !=2)
				{*/
					
					logger.info("helooooo");
	    	   String loggedInPost= loginDetailsMap.get("loggedInPost").toString();
	    	   locationList=orderMasterDAO.getSubLocationDDOs(loggedInPost);
	    	   String locationcodeArray="";
	    	   if(locationList!=null && locationList.size()>0)
	    		for(int i=0;i< locationList.size();i++){
	    			if(i==0)
	    				locationcodeArray+=locationList.get(i).toString();
	    			else
	    				locationcodeArray+=","+locationList.get(i).toString();
	    			logger.info("locationcodeArray :::::::"+locationcodeArray);
	    		}
	    	   logger.info("::::::::::::::::Before ActionList ::::::::");
	    	   //added by abhishek
	    	   
	    	   
	    	   
	    	   String ddoSelected=null;
	    	   DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
	    	   String lStrDdoCode = null;
	    	   String flag="N";
	    	   String ddo=null;
	    	   lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
	    	   List filterDdoCode = orderMasterDAO.getFilterDdoCode(locationcodeArray);
	    	   //logger.info("filterDdoCode :- "+filterDdoCode.size());
	    	   flag = StringUtility.getParameter("flag", request);
	    	   logger.info("flag :- "+flag);
	    	   logger.info("filterDdoCode :- "+filterDdoCode);
	    	  
	    	   logger.info("filterDdoCode :- "+filterDdoCode.size());
	    	   objectArgs.put("filterDdoCode", filterDdoCode);
	    	   if(flag.equals("Y")){
	    		   
	    		   logger.info("inside if condition  ");
	    		   ddo = StringUtility.getParameter("ddoCode", request);
	    		   logger.info("ddo :- "+ddo);
	    		   objectArgs.put("ddo", ddo);
	    		 
	    		 List actionList = orderMasterDAO.getAllData(ddo,locationcodeArray);
	    		 logger.info("actionList************Size"+actionList.size());
	    		 objectArgs.put("actionList", actionList);
	    	   }else{
	    	   //end by abhishek 
	    		   logger.info("inside else condition  ");
			  List actionList = orderMasterDAO.getAllData(ddo,locationcodeArray);//list will collect all data from Vo
			  logger.info("::::::::::::::::After ActionList ::::::::");
			  //ended by sunitha
	    	  // List <HrEisEmpMst> actionList = orderMasterDAO.getSubLocationDDOs(cmnLocationMst.getLocationCode());//list will collect all data from Vo
			  logger.info("actionList************Size"+actionList.size());
			   objectArgs.put("actionList", actionList);
	    	   }
	    	 
			   resultObject.setResultCode(ErrorConstants.SUCCESS);
			   resultObject.setResultValue(objectArgs);//passing map to jsp
			   resultObject.setViewName("orderMasterView");//view jsp
				
				/*else
				{
					
					logger.info(a)
					resultObject.setViewName("MessagePageAuthorisationZPPurpose");
				}*/
		   }			            			
		}
		catch(PropertyValueException pe) {
			logger.info("Null Pointer Exception Ocuures...getOrderData1");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
			/*
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			resultObject.setResultValue(result);
			resultObject.setThrowable(pe);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			plogger.error("Error is: "+ e.getMessage());*/
		}
		catch(Exception e)
		{	
			logger.info("Null Pointer Exception Ocuures...getOrderData2");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
			/*
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			resultObject.setResultValue(result);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());*/
		}	
		return resultObject;
	}		
//For AJAX :- Checking Duplicate Order Name	
public ResultObject checkOrderAvailability(Map objectArgs){
	 	
	
		logger.info("Inside AJAX Service");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
        StringBuffer orderNameBf=new StringBuffer();
        String check; 
		//long langId=Long.parseLong(loginDetailsMap.get("langId").toString());//For Language Independent
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			OrderMstDAO orderMasterDAO = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
            String newOrderName = voToService.get("newOrderName").toString();
            String dept=voToService.get("dept").toString();
            String ordDate=voToService.get("date").toString();
            
            
           
        	Date dtTmp = new SimpleDateFormat("dd.MM.yyyy").parse(ordDate.replace("/","."));
        	String strOutDt = new SimpleDateFormat("dd.MMM.yyyy").format(dtTmp);
        	
            
            logger.info("The parsed date is---------->>>>>>>>>"+dtTmp);
            logger.info("Before The Changed OrderName is:-"+newOrderName);
            newOrderName=newOrderName.toLowerCase();
            logger.info("After The Changed OrderName is:-"+newOrderName);
            //List <HrPayOrderMst> actionList = orderMasterDAO.checkOrderNameAvailability(newOrderName);
            List <HrPayOrderMst> actionList = orderMasterDAO.getOrderDataFromPara(newOrderName,dept,dtTmp);
            logger.info("The Size of List is:-"+actionList.size());
            
            if(actionList.size()==0){
            	check="false";
            	orderNameBf.append("<orderNameMapping>");
            	orderNameBf.append("<availability>").append(check).append("</availability>");
            	orderNameBf.append("</orderNameMapping>");            	
            }
            else {
            	check="true";
            	orderNameBf.append("<orderNameMapping>");
            	orderNameBf.append("<availability>").append(check).append("</availability>");
            	orderNameBf.append("</orderNameMapping>");
            }           
            String orderNameMapping = new AjaxXmlBuilder().addItem("ajax_key", orderNameBf.toString()).toString();
	         
            logger.info(" the string buffer is :"+orderNameMapping);
            objectArgs.put("ajaxKey", orderNameMapping);
	        resultObject.setResultCode(ErrorConstants.SUCCESS); 
	        resultObject.setResultValue(objectArgs);
	        resultObject.setViewName("ajaxData");
		}
		catch(Exception e){
			logger.error("Exception while generating VO to XML ", e);
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			retObj.setViewName("ajaxError");
			objectArgs.put("ajaxKey", "ERROR");
			retObj.setResultValue(objectArgs);
			retObj.setThrowable(e);
			return retObj;

		}
		return resultObject;
	}
	
	


//added by roshan for getting office in a taluka

public ResultObject retriveOfcForTaluka(Map objectArgs) throws Exception
{
			logger.info("Entering into load office in a taluka  by roshan");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
			ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
			OrderMstDAO orderMasterDAO = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());//daoimpl object
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			Long talukaId=Long.valueOf(StringUtility.getParameter("cmbTaluka", request));
			
			logger.info("hii roshan talukaId code"+talukaId);
			 List cmnOfficeList = null;
			 
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
			
			cmnOfficeList = orderMasterDAO.getOfficeInATaluka(talukaId,ddoCode);
			
			 List<ComboValuesVO> cmbDept = new ArrayList<ComboValuesVO>();
			 ComboValuesVO cmbVO = new ComboValuesVO();
			 cmbVO.setId("-1");
			  cmbVO.setDesc("Select");
			  cmbDept.add(cmbVO);
			  
			 if(cmnOfficeList!= null && cmnOfficeList.size() > 0){
				  Iterator IT = cmnOfficeList.iterator();
				  
				  cmbVO = new ComboValuesVO();
				  Object[] lObj = null;
				  while(IT.hasNext()){
					  cmbVO = new ComboValuesVO();
					  lObj = (Object[]) IT.next();
					  cmbVO.setId(lObj[0].toString());
					  cmbVO.setDesc(lObj[1].toString());
					  cmbDept.add(cmbVO);
				  }
			  }
			 Map result = new HashMap();
			 String AjaxResult = new AjaxXmlBuilder().addItems(cmbDept, "desc", "id").toString();
			 logger.info("-----------------------"+AjaxResult);
			 result.put("ajaxKey", AjaxResult);
			 objRes.setResultCode(ErrorConstants.SUCCESS);
			 objRes.setResultValue(result);
			 objRes.setViewName("ajaxData");
			 			
				
	return objRes;
}
	
}
