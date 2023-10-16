package com.tcs.sgv.eis.service;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;

public class BillHeadMpgVOGEN extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	
	
	public ResultObject generateMapForBillHeadMaster(Map objectArgs) throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
        
        try
        {
        	
        	
            logger.info("********************Inside Add/Update BillHeadMpgVOGEN*********************");
        	long billId = 0;
        	int counter = Integer.parseInt(((StringUtility.getParameter("dsgnLenVal",request)!=null&&!(StringUtility.getParameter("dsgnLenVal",request).equals(""))?(StringUtility.getParameter("dsgnLenVal",request)):0).toString()));
			logger.info("The value of counter is------>"+counter);
			
			int counter2 = Integer.parseInt(((StringUtility.getParameter("classLenVal",request)!=null&&!(StringUtility.getParameter("classLenVal",request).equals(""))?(StringUtility.getParameter("classLenVal",request)):0).toString()));
			logger.info("The value of counter2 is------>"+counter2);
			
			
			String[] dsgnidlist = StringUtility.getParameterValues("dsgn",request);
			String[] classidlist = StringUtility.getParameterValues("gradeId",request);
			logger.info("class list size----->"+classidlist.length);
			
			String designation = "";
			List dsgnlist =  new ArrayList();
			
			
			
			for (int x=0;x<dsgnidlist.length;x++)
			{
                    if(dsgnidlist[x]!=null && !dsgnidlist[x].trim().equals(""))
					designation+= "," +dsgnidlist[x];
			}
			
			if(designation.length()>1)
			designation=designation.substring(1);
			
			logger.info("size of designation is:-->>>"+designation.toString());
			
			
			
			String classId = "";
			
			
			for (int v=0;v<classidlist.length;v++)
			{
                if(classidlist[v]!=null && !classidlist[v].trim().equals(""))
                	classId+= "," +classidlist[v];
			}
			
			
			if(classId.length()>1)
				classId=classId.substring(1);
			
			
			logger.info("size of classId is:-->>>"+classId.toString());
			
			
			
        	if(StringUtility.getParameter("bill", request)!=null)
        		billId=Long.parseLong(StringUtility.getParameter("bill", request));
        	logger.info("bill:---> " + billId);
    		objectArgs.put("bill",billId);
        	long headId = 0;
        	if(StringUtility.getParameter("head", request)!=null)
        	{
        		headId=Long.parseLong(StringUtility.getParameter("head", request));
        	}
        	
        	logger.info("Head:---->"+ headId);
    		objectArgs.put("head",headId);
        	HttpSession session=request.getSession();		
    		//LoginDetails loginDetails=(LoginDetails)session.getAttribute("loginDetails");
    		//long langId=loginDetails.getLangId();
    		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	        HrPayBillHeadCustomVO billHeadCustomVO = new HrPayBillHeadCustomVO();
	      	objectArgs.put("designationList", designation);
	      	objectArgs.put("classId", classId);
			
    		
    		
    		HrPayBillHeadMpg billHeadMpg=new HrPayBillHeadMpg();
    		billHeadMpg.setBillId(billId);
    		billHeadMpg.setBillHeadId(headId);
    		
            String updateflag = StringUtility.getParameter("updateflag", request);
            objectArgs.put("updateflag",updateflag);
          long bhMapID=0;
            long  bhMapId =0;
            if(request.getParameter("billheadId")!=null && !request.getParameter("billheadId").equals(""))
            	bhMapId = Long.parseLong(request.getParameter("billheadId"));
            logger.info("Bill Head Mapping Id is:-->"+bhMapId);

          // Added By Urvin Shah.  
          CmnLookupMst postType = null;  
          if(request.getParameter("postTypeCmb")!=null && !request.getParameter("postTypeCmb").equals("")){        	  
        	  long postTypeLookupId = Long.parseLong(request.getParameter("postTypeCmb").toString());
        	  postType = new CmnLookupMst();
        	  postType.setLookupId(postTypeLookupId);
        
          }
          	billHeadMpg.setPostType(postType);
            billHeadMpg.setBillHeadId(bhMapId);
          // End.
            
            String headChargable = null;
            headChargable =  StringUtility.getParameter("headChargable",request)!=null&&!(StringUtility.getParameter("headChargable",request).equals(""))?(StringUtility.getParameter("headChargable",request)):"";
            billHeadMpg.setHeadChargable(headChargable);	
            
            objectArgs.put("updateflag",updateflag);
    		objectArgs.put("bhMapId",bhMapId);
    		
      		objectArgs.put("billHeadMpg",billHeadMpg);
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
}
