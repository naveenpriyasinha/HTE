package com.tcs.sgv.eis.service;


//Comment By Maruthi For import Organisation.

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.OrderHeadPostmpgDAOImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadPostMpg;

public class OrderHeadPostMpgVOGEN extends ServiceImpl 
{

	Log logger = LogFactory.getLog(getClass());

	
	
	public ResultObject generateMapForOrderHeadPostMaster(Map objectArgs) throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
        
        try
        {   
        	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        	OrderHeadPostmpgDAOImpl orderheadpostdao =new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
        	
        	/*
        	Enumeration paraNames = request.getParameterNames();
        	while(paraNames.hasMoreElements())
            {
           	String paraName = (String)paraNames.nextElement();
           	String value = StringUtility.getParameter(paraName,request);
           
           	logger.info("Value in Map is from vo to service method" + paraName + ":--->" + value);
           }
   		 */
   		 
            logger.info("********************Inside Add/Update OrderHead*********************");
            //logger.info("******************1111111111111111************");
        	long orderId = 0;
        	
        	/*String[] postidlist = StringUtility.getParameterValues("post",request);
        	logger.info("length is--------->>>>>>"+postidlist.length);
        	int counter = Integer.parseInt(((StringUtility.getParameter("postLenVal",request)!=null&&!(StringUtility.getParameter("postLenVal",request).equals(""))?(StringUtility.getParameter("postLenVal",request)):0).toString()));
        	logger.info("The value of counter is------->>>>"+counter);
        	for(int i=0;i<counter;i++)
        	{
        		logger.info("The post list is-------->>>>>>"+postidlist[i]);
        	}*/
        	
        	if(StringUtility.getParameter("order", request)!=null)
        		orderId=Long.parseLong(StringUtility.getParameter("order", request));
        	logger.info("order:---> " + orderId);
    		objectArgs.put("order",orderId);
        	long headId = 0;
        	if(StringUtility.getParameter("head", request)!=null)
        		headId=Long.parseLong(StringUtility.getParameter("head", request));
        	logger.info("head:---> " + headId);
    		objectArgs.put("head",headId);
    		long postId = 0;
        	if(StringUtility.getParameter("post", request)!=null)
        		postId=Long.parseLong(StringUtility.getParameter("post", request));
        	logger.info("post:---> " + postId);
    		objectArgs.put("post",postId);
        	
    		//added by Ankit Bhatt for getting Bill No
    		long billNo = 0;
    		String postSearchFlag="n";
    		
        	if(StringUtility.getParameter("billNo", request)!=null)
        		billNo=Long.parseLong(StringUtility.getParameter("billNo", request));
        	logger.info("billNo---> " + billNo);
        	
        	if(StringUtility.getParameter("postSearch", request)!=null)
        		postSearchFlag = StringUtility.getParameter("postSearch", request);
    		objectArgs.put("postSearchFlag",postSearchFlag);
    		objectArgs.put("billNo",billNo);
    		//ended by Ankit Bhatt.
    		
    		HttpSession session=request.getSession();		
    		//LoginDetails loginDetails=(LoginDetails)session.getAttribute("loginDetails");
    		//long langId=loginDetails.getLangId();
//			Added By Mrugesh for financial year issue
  	        FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
  	        Date currDate = Calendar.getInstance().getTime();
  	        DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	        SimpleDateFormat sdf = sbConf.GetDateFormat();
	        String dt = sdf.format(currDate);
	        int finYrId = finYearDAOImpl.getFinYearId(dt);
  	        //Ended By Mrugesh
	        
    		HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg=new HrPayOrderHeadPostMpg();
    		long orderheadId=0;
    		List orderHeadIdList  = orderheadpostdao.getorderHeadId(orderId, headId,finYrId);
    		if(orderHeadIdList!=null && orderHeadIdList.size()> 0 )
    		{
    		    Object[] row = (Object[])orderHeadIdList.get(0);
    		    orderheadId=Long.parseLong(row[0].toString());
    		}
    		logger.info("OrderHeadId"+orderheadId);
			hrPayOrderHeadPostMpg.setOrderHeadId(orderheadId);
    		hrPayOrderHeadPostMpg.setPostId(postId);
    		
            String updateflag = StringUtility.getParameter("updateflag", request);
            long ohpMapID=0;
            long  ohpMapId =0;
            if(request.getParameter("orderHeadPostId")!=null && !request.getParameter("orderHeadPostId").equals(""))
            	ohpMapId = Long.parseLong(request.getParameter("orderHeadPostId"));
    		objectArgs.put("updateflag",updateflag);
    		objectArgs.put("ohpMapId",ohpMapId);

      		objectArgs.put("hrPayOrderHeadPostMpg",hrPayOrderHeadPostMpg);
    		retObj.setResultCode(ErrorConstants.SUCCESS);
            retObj.setResultValue(objectArgs);
            

        }
        catch (Exception e)
        {
            logger.error("Exception in VOGeneratorImpl.generateMap " + e, e);
            ResultObject retObject = new ResultObject(ErrorConstants.ERROR);
            retObj.setThrowable(e);
            

        }
        return retObj;
        
    
	
}
	
	
	
	public ResultObject multipleAddOrderHeadPostData(Map objectArgs)
	{
			 logger.info("********************Inside multipleAddOrderHeadPostData***************************");
			 ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
			 ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			 try 
			 {
						
				HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
				
				OrderHeadPostmpgDAOImpl orderheadpostdao =new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
				int counter = Integer.parseInt(((StringUtility.getParameter("postLenVal",request)!=null&&!(StringUtility.getParameter("postLenVal",request).equals(""))?(StringUtility.getParameter("postLenVal",request)):0).toString()));
				logger.info("The value of counter is------>"+counter);
				long orderId = 0;
				long headId = 0;
				//long postId = 0;
				long orderheadId=0;
				
				String[] postidlist = StringUtility.getParameterValues("post",request);
				for(int i=0;i<counter;i++)
	        	{
	        		logger.info("The post list is-------->>>>>>"+postidlist[i]);
	        	}
				HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg=new HrPayOrderHeadPostMpg();
				List ohpList = new ArrayList();
				
				if(StringUtility.getParameter("order", request)!=null)
	        		orderId=Long.parseLong(StringUtility.getParameter("order", request));
	        	logger.info("order:---> " + orderId);
	    		objectArgs.put("order",orderId);
	        	if(StringUtility.getParameter("head", request)!=null)
	        		headId=Long.parseLong(StringUtility.getParameter("head", request));
	        	logger.info("head:---> " + headId);
	    		objectArgs.put("head",headId);
//				Added By Mrugesh for financial year issue
	  	        FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
	  	        Date currDate = Calendar.getInstance().getTime();
	  	        DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
		        SimpleDateFormat sdf = sbConf.GetDateFormat();
		        String dt = sdf.format(currDate);
	  	        int finYrId = finYearDAOImpl.getFinYearId(dt);
	  	        //Ended By Mrugesh
			    for(int i=0;i<counter;i++)
				{	
		        	logger.info("post:---> " + postidlist[i]);
		    		objectArgs.put("post",postidlist[i]);
		    		
		    		List orderHeadIdList  = orderheadpostdao.getorderHeadId(orderId, headId,finYrId);
		    		if(orderHeadIdList!=null && orderHeadIdList.size()> 0 )
		    		{
		    		    Object[] row = (Object[])orderHeadIdList.get(0);
		    		    orderheadId=Long.parseLong(row[0].toString());
		    		}
		    		
		    		//orderheadId=Long.parseLong(orderheadpostdao.getorderHeadId(orderId, headId,finYrId).get(0).toString());
		    		hrPayOrderHeadPostMpg.setOrderHeadId(orderheadId);
		    		hrPayOrderHeadPostMpg.setPostId(Long.parseLong(postidlist[i]));
		    		ohpList.add(i,hrPayOrderHeadPostMpg);
		    		objectArgs.put("ohpList", ohpList);
				}
			    
//			  added by Ankit Bhatt for getting Bill No
	    		long billNo = 0;
	    		String postSearchFlag="n";
	    		
	        	if(StringUtility.getParameter("billNo", request)!=null)
	        		billNo=Long.parseLong(StringUtility.getParameter("billNo", request));
	        	logger.info("billNo in multiple method---> " + billNo);
	        	
	        	if(StringUtility.getParameter("postSearch", request)!=null)
	        		postSearchFlag = StringUtility.getParameter("postSearch", request);
	    		objectArgs.put("postSearchFlag",postSearchFlag);
	    		objectArgs.put("billNo",billNo);
	    		//ended by Ankit Bhatt.
	    		
			    retObj.setResultCode(ErrorConstants.SUCCESS);
	            retObj.setResultValue(objectArgs);
	            logger.info("*****U R Out of VOGen**************");
			 }
				catch(Exception e){
					logger.info("U r in Exception + empLeaveVOGEN");
					logger.error("Error is: "+ e.getMessage());
					Map result = new HashMap();
					result.put("MESSAGECODE",3001);
					retObj.setResultValue(result);
					retObj.setResultCode(-1);
					retObj.setViewName("errorPage");
					return retObj;			
				}	
				return retObj;
			}
	
	}
