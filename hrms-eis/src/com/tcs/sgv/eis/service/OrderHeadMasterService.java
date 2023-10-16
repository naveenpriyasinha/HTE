package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.DesigMasterDAO;
import com.tcs.sgv.eis.dao.OrderHeadMpgDAO;
import com.tcs.sgv.eis.dao.OrderHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderMstDAO;
import com.tcs.sgv.eis.dao.OrderMstDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadCustomMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;



public class OrderHeadMasterService extends ServiceImpl  {
	private static final long orderid = 0;
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	//This method will insert and update the order data
	public ResultObject insertOrderMasterDtls(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			//ResultObject resultObj = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
			Map resultMap = (Map) resultObject.getResultValue();
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
			
			/*long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	        logger.info("insertBankMasterDtls Loc Id is:-->" + dbId + " " + locId);*/
	       
	        long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			
			Date sysdate = new Date();
			long attachment_Id=0;
			/*if(resultMap.get("AttachmentId_orderId")!=null)
			 attachment_Id = (Long) resultMap.get("AttachmentId_orderId"); 
			logger.info("after inserting attachment! attach_id : " + attachment_Id);*/		
	
            
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
				
				hrPayOrderMst.setLocationCode(orderMst.getLocationCode());//Setting Order Name in vo
				hrPayOrderMst.setOrderName(orderMst.getOrderName());//Setting Order Name in vo
				hrPayOrderMst.setOrderDate(orderMst.getOrderDate());//setting Order Date in vo
				// Added By Urvin shah.
				hrPayOrderMst.setEndDate(orderMst.getEndDate());	// Setting Order end date.
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
						payOrderMst.setOrderId(orderId);//setting order id
						payOrderMst.setCmnLanguageMst(cmnLanguageMst);
						payOrderMst.setTrnCounter(new Integer(1));
						payOrderMst.setOrgPostMstByCreatedByPost(orgPostMst);
						payOrderMst.setOrgUserMstByCreatedBy(orgUserMst);
						payOrderMst.setCreatedDate(sysdate);
						
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
    		ne.printStackTrace();
    		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
    		resultObject.setResultValue(objectArgs);
    		resultObject.setViewName("errorInsert");			
    	}
    	
    	catch(Exception e){
    		
    		logger.info("Exception Ocuures...insertOuterData");
    		 e.printStackTrace();
    		 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
    		 resultObject.setResultValue(objectArgs);
    		 resultObject.setViewName("errorInsert");
    	}
    	return resultObject;
    	}
//This method will fetch all hr_pay_order_mst data for display in view jsp	
	public ResultObject getOrderHeadData(Map objectArgs)
	{
		logger.info("getOrderHeadData IN Order Master Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
		   ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	       Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		   Map voToService = (Map)objectArgs.get("voToServiceMap");
		   String editFlag = (String)voToService.get("edit");//edit flag passing
		   logger.info("Flag Name "+editFlag );
		   List ohMpgList = new ArrayList();  
		   //String locId=loginDetailsMap.get("locationId").toString(); 
		   CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
   		String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
			OrderHeadMpgDAO orderheadMasterDAO = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());//daoimpl object
		    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		    HttpSession session=request.getSession();		
	        OrderMstDAO ordermstdao = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
	        OrderHeadMpgDAO subheaddao = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
	         String searchOrderFlag = (voToService.get("orderSearchFlag")!=null && !voToService.get("orderSearchFlag").toString().equals(""))?voToService.get("orderSearchFlag").toString():"";
	         
	         //Added By Mrugesh for financial year issue
  	        FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
  	        Date currDate = Calendar.getInstance().getTime();
  	       /* SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
  	        String dt = sdf.format(currDate);
  	        int finYrId = finYearDAOImpl.getFinYearId(dt);*/
  	        int finYrId = finYearDAOImpl.getFinYearId(currDate);
  	        //Ended By Mrugesh
  	        
	         if(!searchOrderFlag.equals("")){
	        	 String strOrderName = (voToService.get("orderName")!=null && !voToService.get("orderName").toString().equals(""))?voToService.get("orderName").toString():"";
	        	 String strStartDate = (voToService.get("startDate")!=null && !voToService.get("startDate").toString().equals(""))?voToService.get("startDate").toString():"";
	        	 String strEndDate = (voToService.get("endDate")!=null && !voToService.get("endDate").toString().equals(""))?voToService.get("endDate").toString():"";
	        	 CmnLocationMst locMstVo= new CmnLocationMst();
	             locMstVo = (CmnLocationMst)loginDetailsMap.get("locationVO");	                       
	             String locCode = locMstVo.getLocationCode();
	            
	             List resultSet = orderheadMasterDAO.searchOrderHeadMst(locCode, strOrderName, strStartDate, strEndDate,finYrId);
	             HrPayOrderHeadCustomMpg ohMpg = new HrPayOrderHeadCustomMpg();
         		
         		Iterator  it = resultSet.iterator();
         		
         		
 	        	for(int i=0;i<resultSet.size();i++)
         		 {	    
 	        		ohMpg = new HrPayOrderHeadCustomMpg();
 		            Object[] rowList = (Object[]) resultSet.get(i);
 		            long orderhdid = Long.parseLong(rowList[0].toString());
 		            ohMpg.setOrderHeadId(orderhdid);
 		            
 		            String ordername = rowList[1].toString();
 		            ohMpg.setOrderName(ordername);
 		            
 		            String headname = rowList[2].toString();
 		           
 		            ohMpg.setSubHeadName(headname);
 		            
 		            ArrayList row = new ArrayList();
 		            row.add(orderhdid);
 		            row.add(ordername);
 		            row.add(headname);
 		            
 		            ohMpgList.add(ohMpg);
         		 }
 	        	objectArgs.put("resultSet", ohMpgList);
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        resultObject.setResultValue(objectArgs);//passing map to result value for get data in jsp
		        resultObject.setViewName("orderheadMasterView");
	         }
	         else if(editFlag != null && editFlag.equals("Y") )
	            {
	            		logger.info("Inside Update Mode ");
	            		long OrderHeadid =Long.parseLong((String)voToService.get("orderheadid"));
	            		long orderId =Long.parseLong((String)voToService.get("orderid"));
	            		long budsubhd_id =Long.parseLong((String)voToService.get("subheadId"));
	            		logger.info("OrderHead Id after parsing"+OrderHeadid);
	            		logger.info("orderId  after parsing"+orderId);
	            		logger.info("orderheadId Id after parsing"+budsubhd_id);
	            		List<HrPayOrderHeadMpg> resultSet = orderheadMasterDAO.getOrderHeadMstDataByid();//Result Set
	            		//List<HrPayOrderHeadMpg> ohMpgList = new ArrayList();
	            		 CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
	            		String ddo ="";
	            		for (int i=0;i<resultSet.size();i++)
	            		{
	            			 HrPayOrderHeadMpg hrPayorderHeadMpg = new HrPayOrderHeadMpg();
	            			 hrPayorderHeadMpg=resultSet.get(i);
	            			 List<HrPayOrderMst> ordername = ordermstdao.getAllData(ddo,locationCode);
	            			 List<HrPayOrderHeadMpg> headname = subheaddao.getAllSubhdData();
	            		 }
	            		
	            		//logger.info("Order Name from HrPayOrderMstVO " + resultset.getOrderName());
	            		
	            		
	            		Map map = new HashMap();
	            	    
	            	    map=objectArgs;
				        map.put("resultSet", ohMpgList);
				        resultObject.setResultCode(ErrorConstants.SUCCESS);
				        resultObject.setResultValue(map);//passing map to result value for get data in jsp
				      
				        resultObject.setViewName("orderheadedit");//view edit jsp
	            }
	           else
	            {       
	        	        logger.info("Inside Insert Mode");
	        	      //  long OrderHeadid =Long.parseLong((String)voToService.get("orderheadid"));
	            		//long orderId =Long.parseLong((String)voToService.get("orderid"));
	            		//long budsubhd_id =Long.parseLong((String)voToService.get("subheadId"));
	            	//	logger.info("OrderHead Id after parsing"+OrderHeadid);
	            		//logger.info("orderId  after parsing"+orderId);
	            		//logger.info("orderheadId Id after parsing"+budsubhd_id);
	        	       
	            		List resultSet = orderheadMasterDAO.getOrderHeadMstDataByid(locationCode,finYrId);
	            		HrPayOrderHeadCustomMpg ohMpg = new HrPayOrderHeadCustomMpg();
	            		
	            		Iterator  it = resultSet.iterator();
	            		
	            		
	    	        	for(int i=0;i<resultSet.size();i++)
	            		 {	    
	    	        		ohMpg = new HrPayOrderHeadCustomMpg();
	    		            Object[] rowList = (Object[]) resultSet.get(i);
	    		            long orderhdid = Long.parseLong(rowList[0].toString());
	    		           // logger.info("+++++ Order Head Id+++++"+orderhdid);
	    		            ohMpg.setOrderHeadId(orderhdid);
	    		            
	    		            String ordername = rowList[1].toString();
	    		           // logger.info("+++++ Order Name+++++"+ordername);
	    		            ohMpg.setOrderName(ordername);
	    		            
	    		            String headname = rowList[2].toString();
	    		           // logger.info("+++++ Head Name+++++"+headname);
	    		            ohMpg.setSubHeadName(headname);
	    		            
	    		            ArrayList row = new ArrayList();
	    		            row.add(orderhdid);
	    		            row.add(ordername);
	    		            row.add(headname);
	    		            
	    		            ohMpgList.add(ohMpg);
	    		            
	    		            
	            		 }
	    		            
	    		            
	    	        	
	    		            
	    		            
	    		           
	    	        		
	    	        		
	    	        		
	    	        		/*logger.info("Order Head Id"+resultSet.get(i));
	            			 long orderhdid = Long.parseLong(resultSet.get(i).toString());
	            			 ohMpg.setOrderHeadId(orderhdid);
	            			 String  ordername = resultSet.get(++i).toString();
	            			 ohMpg.setOrderName(ordername);
	            			 String  headname = resultSet.get(++i).toString();
	            			 ohMpg.setSubHeadName(headname);
	            			 ohMpgList.add(ohMpg);*/
	            			
	            	
	            			 
	            			 // hrPayorderHeadcustomMpg=resultSet.get(i);
                  		//	 String OrderName = (String)it.next();
	            		//	 String SubhdName = (String)it.next();
	            			
	            		// logger.info("Printing ordername"+OrderName);
	            	// logger.info("Printing subhd name"+SubhdName);
	            			// List<HrPayOrderMst> ordername = ordermstdao.getAllData();
	            			 //List<HrPayOrderHeadMpg> headname = subheaddao.getAllSubhdData();
	            		
	            		 
	            		 
	            		 
	            		
	            		//logger.info("Order Name from HrPayOrderMstVO " + resultset.getOrderName());
	            		
	            		logger.info("outside itreator");
	            		//Map map = new HashMap();
	            	    
	            	    //map=objectArgs;
	            		objectArgs.put("resultSet", ohMpgList);
				        resultObject.setResultCode(ErrorConstants.SUCCESS);
				        resultObject.setResultValue(objectArgs);//passing map to result value for get data in jsp
				        resultObject.setViewName("orderheadMasterView");
	        	        
			          /*  List <HrPayOrderHeadMpg> actionList = orderMasterDAO.getAllData();//list will collect all data from Vo
			            Map map = new HashMap();
			            map=objectArgs;
			            map.put("actionList", actionList);
			            resultObject.setResultCode(ErrorConstants.SUCCESS);
			            resultObject.setResultValue(map);//passing map to jsp
			            resultObject.setViewName("orderMasterView");//view jsp*/
	            }			            			
		}
		catch(PropertyValueException pe)
		{
			logger.info("Null Pointer Exception Ocuures...insertOrderMasterDtls");
			pe.printStackTrace();
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
			pe.printStackTrace();*/

		}
		catch(Exception e)
		{	
			logger.info("Null Pointer Exception Ocuures...insertOrderMasterDtls");
			e.printStackTrace();
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
			e.printStackTrace();*/
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
            logger.info("Before The Changed OrderName is:-"+newOrderName);
            newOrderName=newOrderName.toLowerCase();
            logger.info("After The Changed OrderName is:-"+newOrderName);
            List <HrPayOrderMst> actionList = orderMasterDAO.checkOrderNameAvailability(newOrderName);
            
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
		public ResultObject orderheadMaster(Map objectArgs)
		{
			logger.info("**********Inside orderheadMaster**************");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String editflag="";
			long OhMapId;
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			try
			{
				    HttpSession session=request.getSession();		

				    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
				    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				    OrderHeadMpgDAOImpl orderHeadMpgDAO = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
				    OrderHeadMpgDAO headMasterDAO = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
				    OrderMstDAO orderMstDAO = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
				    Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
				    //By Urvin shah.
				    //long locationId=Long.parseLong(loginDetailsMap.get("locationId").toString());
				    CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
		    		String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
		            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
					CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
				    CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				    String ddo ="";
					List orderresultSet = orderMstDAO.getAllData(ddo,locationCode);
					List headresultSet = headMasterDAO.getAllSubhdData();
					//List deptList = orderHeadMpgDAO.getAlldeptData();
					//added by rahul			    
					Map baseLoginMap = (Map) objectArgs.get("baseLoginMap");				
					//long locId = Long.parseLong(baseLoginMap.get("locationId").toString());
					//ended by rahul
					  //Added By Mrugesh for financial year issue
		  	        FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
		  	        Date currDate = Calendar.getInstance().getTime();
		  	        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		  	        String dt = sdf.format(currDate);
		  	        int finYrId = finYearDAOImpl.getFinYearId(dt);*/
		  	      int finYrId = finYearDAOImpl.getFinYearId(currDate);
		  	      logger.info("the finYrID is ::"+finYrId);
		  	        //Ended By Mrugesh
					//Start Added By Urvin shah.
					List deptList = null;
					//modified by rahul
					 //by rahul
					   PaybillHeadMpgDAOImpl paybillheadmpgdao = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
				       boolean isRoleAdminFlag = paybillheadmpgdao.isLoggedInUserAdmin(loginDetailsMap);				       
				       if(isRoleAdminFlag)
						{
							logger.info("true::");
							deptList = locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId);
							logger.info("deptlist.size::"+ deptList.size());
						}
						else
						{
							logger.info("false::");
							String[] critariaArugs={"cmnLanguageMst.langId","locationCode"};
							Object[] valueArgus=new Object[2];
							valueArgus[0] = langId;
							valueArgus[1] = locationCode;
							deptList = locationDAO.getListByColumnAndValue(critariaArugs, valueArgus);
							logger.info("deptlist.size::"+ deptList.size());
						}
				       //ended by rahul
					//ended by rahul
					//End by Urvin shah.
					
					HrPayOrderHeadMpg hrPayOrderHeadMpg = new HrPayOrderHeadMpg();
					  Map voToService = (Map)objectArgs.get("voToServiceMap");
					  editflag=voToService.get("updateflag")!=null?(String)voToService.get("updateflag"):"";
					if( editflag!=null && editflag.equals("Y"))
					{
						HrPayOrderHeadCustomMpg ohMpg = new HrPayOrderHeadCustomMpg();
						OhMapId = voToService.get("ohMapId")!=null?Long.parseLong((String)voToService.get("ohMapId")):0;
						List ohDataList =new ArrayList();
						ohDataList=orderHeadMpgDAO.getOrderHeadDataById(OhMapId,finYrId);
						Iterator  it = ohDataList.iterator();
	    	        	 if(ohDataList!=null&&ohDataList.size()>0)
	            		 {	    
	    		            Object[] rowList = (Object[]) ohDataList.get(0);
	    		            
	    		            ohMpg.setOrderHeadId(OhMapId);
	    		            String demandNo=rowList[2]!=null?rowList[2].toString():"";
	    		            
	    		            ohMpg.setDemandNo(demandNo);
	    		            long orderId=rowList[6]!=null?Long.parseLong(rowList[6].toString()):0;
	    		            
	    		            String locCode=rowList[7]!=null?rowList[7].toString():"0";
	    		            ohMpg.setLocationCode(locCode);
	    		           
	    		            ohMpg.setOrderId(orderId);
	    		            Long SubHeadId=rowList[0]!=null?Long.parseLong(rowList[0].toString()):0;	    	            
	    		            ohMpg.setSubHeadId(SubHeadId);
	    		            
	    		            
	    		            String SubHead=rowList[1]!=null?rowList[1].toString():"";
	    		            ohMpg.setSubHead(SubHead);
	    		            
	    		            String mjrHeadCode=rowList[3]!=null?rowList[3].toString():"";
	    		            ohMpg.setMjrHead(mjrHeadCode);
	    		            
	    		            String subMjrHeadCode=rowList[4]!=null?rowList[4].toString():"";
	    		            ohMpg.setSubMjrHead(subMjrHeadCode);
	    		            
	    		            String MnrHeadCode=rowList[5]!=null?rowList[5].toString():"";
	    		            ohMpg.setMinorHead(MnrHeadCode);
	    		            
	    		            PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
	    		            List mjrHeadDataList = new ArrayList();
	    		            List subMjrHeadDataList = new ArrayList();
	    		            List minorHeadDataList = new ArrayList();
	    		            List subHeadDataList = new ArrayList();
	    		            List demandNoDataList = new ArrayList();
	    		            List demandNoList = payBillDAO.getDemandNoByLocId(locationCode, cmnLanguageMst.getLangShortName(),finYrId);
	    		            List mjrHeadList = payBillDAO.getMjrHeadByDemandNo(demandNo, cmnLanguageMst.getLangShortName(),finYrId);
	    		            List subMjrHeadList = payBillDAO.getSubMjrHeadByMjrHead(demandNo, cmnLanguageMst.getLangShortName(),mjrHeadCode,finYrId);
	    		            List minorHeadList = payBillDAO.getMnrHeadByMjrHead(demandNo,mjrHeadCode,subMjrHeadCode,cmnLanguageMst.getLangShortName(),finYrId);
	    		            List subHeadList = payBillDAO.getSubHeadByMnrHead(demandNo,mjrHeadCode,subMjrHeadCode,MnrHeadCode,cmnLanguageMst.getLangShortName(),finYrId);
	    		            for (Iterator iter = demandNoList.iterator(); iter.hasNext();)
	    		            {			 
	    		   		     Object[] row = (Object[])iter.next();
	    		   		     HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
	    		   	   		 String demand_code = row[1]!=null?row[1].toString():"";
	    		   	   	     ohmpg.setDemandNo(demand_code);
	    		   	   	     demandNoDataList.add(ohmpg);
	    		            }
	    		            for (Iterator iter = mjrHeadList.iterator(); iter.hasNext();)
	    		            {			 
	    		   		     //Object[] row = (Object[])iter.next();
	    		   		     HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
	    		   	   		 String MjrHead =iter.next().toString();
	    		   	   	     ohmpg.setMjrHead(MjrHead);
	    		   	     	 mjrHeadDataList.add(ohmpg);
	    		            }
	    		            for (Iterator iter = subMjrHeadList.iterator(); iter.hasNext();)
	    		            {			 
	    		   		     //Object[] row = (Object[])iter.next();
	    		   		     HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
	    		   	   		 String subMjrHead = iter.next().toString();
	    		   	   	     ohmpg.setSubMjrHead(subMjrHead);
	    		   	   	     subMjrHeadDataList.add(ohmpg);
	    		            }
	    		            for (Iterator iter = minorHeadList.iterator(); iter.hasNext();)
	    		            {			 
	    		   		     //Object[] row = (Object[])iter.next();
	    		   		     HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
	    		   	   		 String minorHead = iter.next().toString();
	    		   	   	     ohmpg.setMinorHead(minorHead);
	    		   	   	     minorHeadDataList.add(ohmpg);
	    		            }
	    		            for (Iterator iter = subHeadList.iterator(); iter.hasNext();)
	    		            {			 
	    		   		     Object[] row = (Object[])iter.next();
	    		   		     HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
	    		   		     String subHeadId = row[0]!=null?row[0].toString():"";
	    		   		     String subHeadCode = row[1]!=null?row[1].toString():"";
	    		   	   		 String subHeadName = row[2]!=null?row[2].toString():"";
	    		   	   	     subHeadName="("+subHeadCode+")"+subHeadName;
	    		   	   	     ohmpg.setSubHead(subHeadCode);
	    		   	   	     ohmpg.setSubHeadId(Long.parseLong(subHeadId));
	    		   	   	     ohmpg.setSubHeadName(subHeadName);
	    		   	   	     subHeadDataList.add(ohmpg);
	    		            }
	    		            objectArgs.put("demandNoList", demandNoDataList);
                            objectArgs.put("mjrHeadList", mjrHeadDataList);
                            objectArgs.put("subMjrHeadList", subMjrHeadDataList);
                            objectArgs.put("minorHeadList", minorHeadDataList);
                            objectArgs.put("subHeadList", subHeadDataList);
                            objectArgs.put("hrPayOrderHeadMpg", ohMpg);
	            		 }
	    	        	 objectArgs.put("hrPayOrderHeadMpg", ohMpg);
					}
					
					objectArgs.put("orderresultSet", orderresultSet);
					objectArgs.put("headresultSet", headresultSet);
					objectArgs.put("deptList", deptList);
					objectArgs.put("result", "success");
		            resultObject.setResultValue(objectArgs);
		            if(!editflag.equals("Y"))
		            resultObject.setViewName("orderheadMaster");
		            else
			        resultObject.setViewName("orderheadedit");
		            
			}
			catch(Exception e)
			{
				
				e.printStackTrace();
			}
		
			return resultObject;
		}
		public ResultObject insertOrderHeadMasterDtls(Map objectArgs) {
			// TODO Auto-generated method stub
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			try{
				
				
				logger.info("Inside Insert Master Details Service");
				logger.info("Edit Mode From VOGEN "+objectArgs.get("updateflag"));
				HrPayOrderHeadMpg hrpayorderheadmpg = (HrPayOrderHeadMpg)objectArgs.get("hrPayOrderHeadMpg");//object of VO
				logger.info( "Order ID is " + hrpayorderheadmpg.getOrderId());
				logger.info( "Head ID is " + hrpayorderheadmpg.getSubheadId());
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				OrderHeadMpgDAOImpl ordermstDAOImpl = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());//object of DAOIMPL
				
				
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			//	long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());//For Language independent
				
	            String editFromVO = objectArgs.get("updateflag").toString();//Flag for edit mode yes or no
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
		       
		     /*   langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);*/
				
				 long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
				 
				 Date sysdate = new Date();
	            
	            if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))            	
				{
	            	
	            	long orderheadid = hrpayorderheadmpg.getOrderHeadId();

	       		    HrPayOrderHeadMpg orderheadmpgVO = ordermstDAOImpl.read(orderheadid);//Reading orderid from Vo
					logger.info("INSIDE UPDATE insertOrderHeadMapping");
					//Added by Mrugesh for getting element from subhead id
					String element_Code = null;//ordermstDAOImpl.getElementCodeFromSubHdId(hrpayorderheadmpg.getSubheadId());
					//Ended by Mrugesh
					orderheadmpgVO.setOrderId(hrpayorderheadmpg.getOrderId());//Setting Order Name in vo
					//orderheadmpgVO.setSubheadId(Long.parseLong(element_Code));//setting Order Date in vo
					orderheadmpgVO.setUpdatedDate(sysdate);
					orderheadmpgVO.setOrgUserMstByUpdatedBy(orgUserMst);
					orderheadmpgVO.setOrgPostMstByUpdatedByPost(orgPostMst);
						 
					ordermstDAOImpl.update(orderheadmpgVO);//update Vo...VO ready to update
					msg=1;//for display message for update
				
	        		logger.info("Updated successfully................");
				}
	            
	            
				else if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
				{
					logger.info("INSIDE Insert Order Details");
					IdGenerator idGen = new IdGenerator();
					long orderheadId = idGen.PKGenerator("hr_pay_order_head_mpg", objectArgs);
					HrPayOrderHeadMpg hrPayOrderHeadMpg = new HrPayOrderHeadMpg();
					hrPayOrderHeadMpg.setOrderHeadId(orderheadId);
					hrPayOrderHeadMpg.setOrderId(hrpayorderheadmpg.getOrderId());//setting order id
					hrPayOrderHeadMpg.setSubheadId(hrpayorderheadmpg.getSubheadId());//setting order id
					hrPayOrderHeadMpg.setTrnCounter(new Integer(1));
					hrPayOrderHeadMpg.setOrgPostMstByCreatedByPost(orgPostMst);
					hrPayOrderHeadMpg.setOrgUserMstByCreatedBy(orgUserMst);
					hrPayOrderHeadMpg.setCreatedDate(sysdate);				 				 							
					ordermstDAOImpl.create(hrPayOrderHeadMpg);//ready to insert 
				
					logger.info("Inserted Successfully");
								
				}
	            
	            if(msg==1)
	    			objectArgs.put("MESSAGECODE",300006);//message code from frm_message_mst 300006 for Update
	    		else
	    			objectArgs.put("MESSAGECODE",300005);//message code from frm_message_mst 300005 for Insert
	    					
	    		resultObject.setResultCode(ErrorConstants.SUCCESS);
	    		
	    		resultObject.setResultValue(objectArgs);
	    		//if(msg==1)
	    			resultObject.setViewName("orderheadMaster");//For Redirect from message to view jsp
	    		//else
	    			//resultObject.setViewName("orderheadMaster");//For Redirect from message to view jsp
	    		
	        logger.info("Inserted Sucssesfully and End of Insert/Update Method");
	    		
	    	}
	    	catch(NullPointerException ne)
	    	{
	    		logger.info("Null Pointer Exception Ocuures...insertorderData");
	    		ne.printStackTrace();
	    		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
	    		resultObject.setResultValue(objectArgs);
	    		resultObject.setViewName("errorInsert");			
	    	}
	    	
	    	catch(Exception e){
	    		
	    		logger.info("Exception Ocuures...insertOuterData");
	    		 e.printStackTrace();
	    		 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
	    		 resultObject.setResultValue(objectArgs);
	    		 resultObject.setViewName("errorInsert");
	    	}
	    	return resultObject;
	    	}
		
		/**
		 * 
		 * @param objectArgs
		 * @return
		 */
		
		
		public ResultObject getOrderAndDemandList(Map objectArgs) {
			// TODO Auto-generated method stub
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			try{
				logger.info("You are in the Order Head Mapping");		
				 ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				 
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
	            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
	            CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   
	            long orderId;
	            String orderName;
	            Date orderDate;
	            String sanctionOrderDate;
	            SimpleDateFormat sdfObj = new SimpleDateFormat("dd/MM/yyyy");
	            Map voToService = (Map)objectArgs.get("voToServiceMap");
	            //String demand_no = voToService.get("demand_no").toString();
	            OrderMstDAOImpl orderMstDAOImpl = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
	            String locationCode = voToService.get("cmbDept").toString();
	            List orderList = orderMstDAOImpl.getOrderName();
	            HrPayOrderMst hrPayOrderMst;
	            StringBuffer propertyList = new StringBuffer();
	   		    for (Iterator iter = orderList.iterator(); iter.hasNext();)  {			 
	   		    	hrPayOrderMst = (HrPayOrderMst)iter.next();		 
	   	   		  	orderId = hrPayOrderMst.getOrderId();
	   	   		  	orderName = hrPayOrderMst.getOrderName();
	   	   		  	orderDate = hrPayOrderMst.getOrderDate();
	   	   		sanctionOrderDate =sdfObj.format(orderDate);   		  	
	   	   		orderName = orderName +  " " + "(" +" Date: "+ sanctionOrderDate + ")";
	   	   		//logger.info("Order Name with Order Date"+orderName);
	   	   		  	logger.info("Major Head Name in service iterator " + locationCode);
	      		    propertyList.append("<order-mapping>");   	
	                propertyList.append("<orderId>").append(orderId).append("</orderId>");                            
	                propertyList.append("<orderName>").append("<![CDATA[").append(orderName).append("]]>").append("</orderName>");
	                propertyList.append("</order-mapping>");
	            }
	      	      
	            String orderListStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
	            logger.info("Major Head list key in service is "  + orderListStr);	          
	            objectArgs.put("ajaxKey", orderListStr);	               
	            resultObject.setResultValue(objectArgs);
	            resultObject.setViewName("ajaxData");
			}
	       catch(Exception e){
				
				logger.info("Exception Ocuures...insertBankMasterDtls");
				 e.printStackTrace();
				 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
				 resultObject.setResultValue(objectArgs);
				 resultObject.setViewName("errorInsert");			
			}
			return resultObject;
		}
		
		public ResultObject chkorderHeadunique(Map objectArgs)
		{
			logger.info("*********chkorderHeadunique*********");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			HttpSession session=request.getSession();		
		    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
		    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory());
			long orderId = 0;
			long headId = 0;
			long ohMapId=0;
			
		    Map voToService = (Map)objectArgs.get("voToServiceMap");
		    orderId =Long.parseLong((String) voToService.get("order"));
		    headId =Long.parseLong((String) voToService.get("head"));
		    ohMapId =Long.parseLong((String) voToService.get("ohMapId"));
		    
	        Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	        OrderHeadMpgDAOImpl orderHeadMpgDAOImpl = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
	        List mpgDataList=orderHeadMpgDAOImpl.chkOrderHeadDataById(orderId, headId, ohMapId);
		     StringBuffer propertyList = new StringBuffer();

	   		  propertyList.append("<oh-mapping>"); 
              if(mpgDataList!=null&&mpgDataList.size()>0)	
	          propertyList.append("<mpgFlag>").append("<![CDATA[").append(mpgDataList.size()).append("]]>").append("</mpgFlag>");
              else
    	          propertyList.append("<mpgFlag>").append("<![CDATA[").append(-1).append("]]>").append("</mpgFlag>");
              propertyList.append("</oh-mapping>");

	          String mpgdata = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
	         objectArgs.put("ajaxKey", mpgdata);
	            
	         resultObject.setResultValue(objectArgs);
	         resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
	        logger.info("After Service Called.........\n"+mpgdata);
	        return resultObject;
			
		}
		
}