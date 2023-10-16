package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.AttachmentHelper;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class MapEmpWithArrearServiceVOGEN extends ServiceImpl implements VOGeneratorService{

	Log logger = LogFactory.getLog(getClass());
	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.Payroll");

	public final int DEPT_ID = Integer.parseInt(constantsBundle.getString("GAD"));

	public ResultObject genMapEmpWithArrearVOGEN(Map objServiceArgs) 
	{
		Calendar calTime = Calendar.getInstance();
		logger.info("The Inintial time for the MapEmpWithArrearServiceVOGEN is:-"+calTime.getTimeInMillis());		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			
			SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat parseDate =new SimpleDateFormat("yyyy-MM-dd");
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");		
			Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
			long loggedInUser = Long.parseLong(loginDetailsMap.get("userId").toString());			
			OrgUserMst loggedInOrgUserMst = new OrgUserMst();
			loggedInOrgUserMst.setUserId(loggedInUser);
			
			long loggedInPost = Long.parseLong(loginDetailsMap.get("primaryPostId").toString());			
			OrgPostMst loggedInOrgPostMst=new OrgPostMst();
			loggedInOrgPostMst.setPostId(loggedInPost);	
			 
			//Getting groupBill No
			String groupBillNo="";
			if(StringUtility.getParameter("groupBillNo", request)!=null&&!StringUtility.getParameter("groupBillNo", request).equals(""))
				groupBillNo= StringUtility.getParameter("groupBillNo", request);			
			logger.info("groupbillno:"+groupBillNo);
			//Getting emp Counter
			long empCounter=0;
			if(StringUtility.getParameter("empCounter", request)!=null&&!StringUtility.getParameter("empCounter", request).equals(""))
				empCounter= StringUtility.convertToLong(StringUtility.getParameter("empCounter", request));			
			logger.info("empCounter:"+empCounter);
			//Getting salRevOrder
			long salRevOrder=0;
			if(StringUtility.getParameter("salRevOrder", request)!=null&&!StringUtility.getParameter("salRevOrder", request).equals(""))
				salRevOrder= StringUtility.convertToLong(StringUtility.getParameter("salRevOrder", request));			
			logger.info("salRevOrder:"+salRevOrder);
			
			String orderEffDate="";
			String orderEffEndDate="";
			String strBillSubHeadId="";
			if(StringUtility.getParameter("effFromDate", request)!=null && !StringUtility.getParameter("effFromDate", request).equals(""))
				orderEffDate=StringUtility.getParameter("effFromDate", request).toString();
			if(StringUtility.getParameter("effToDate", request)!=null && !StringUtility.getParameter("effToDate", request).equals(""))
				orderEffEndDate=StringUtility.getParameter("effToDate", request).toString();
			if(StringUtility.getParameter("lBillSubHeadId", request)!=null && !StringUtility.getParameter("lBillSubHeadId", request).equals(""))
				strBillSubHeadId=StringUtility.getParameter("lBillSubHeadId", request).toString();
			objServiceArgs.put("lBillSubHeadId", strBillSubHeadId);
			objServiceArgs.put("orderEffDate", orderEffDate);
			objServiceArgs.put("orderEffEndDate", orderEffEndDate);
			String staticAmtOrNot="";
			if(StringUtility.getParameter("staticAmtOrNot", request)!=null&&!StringUtility.getParameter("staticAmtOrNot", request).equals(""))
				staticAmtOrNot= StringUtility.getParameter("staticAmtOrNot", request);			
			logger.info("staticAmtOrNot:"+staticAmtOrNot);
			String rdoBtnSearchFlg="1";
			if(StringUtility.getParameter("rdoBtnSearchFlg", request)!=null&&!StringUtility.getParameter("rdoBtnSearchFlg", request).equals(""))
				rdoBtnSearchFlg=StringUtility.getParameter("rdoBtnSearchFlg", request).toString();
			logger.info("rdoBtnSearchFlg:"+rdoBtnSearchFlg);
			//Getting arrearData of employee			
			String empArrear="";
			List<String> empArrearDataLst=new ArrayList();
			StringTokenizer strTokenizer = null;
			for (int i=1;i<=empCounter;i++)
			{

				if(StringUtility.getParameter("empArrear"+i+"chk", request)!=null&&!StringUtility.getParameter("empArrear"+i+"chk", request).equals(""))
				{
					empArrear= StringUtility.getParameter("empArrear"+i+"chk", request);					
					empArrearDataLst.add(empArrear);
					strTokenizer = new StringTokenizer(empArrear,"Z");
				}

				logger.info("empArrear:"+empArrear);
			}
			
			logger.info("empArrearDataLst.size:"+empArrearDataLst.size());
			
			long lOrderId=0;		
			String flag="N";			
			if(StringUtility.getParameter("lOrderId",request)!=null &&!StringUtility.getParameter("lOrderId", request).equals(""))					
				lOrderId=Long.parseLong(StringUtility.getParameter("lOrderId", request).toString());
			logger.info("lOrderId from Jsp:"+lOrderId);
			if(StringUtility.getParameter("flag",request)!=null &&!StringUtility.getParameter("flag", request).equals(""))					
				flag=StringUtility.getParameter("flag", request);
			
			int compoCount=0;
			int cmpValue=0;
			StringBuffer compoValueList=new StringBuffer();
			if(StringUtility.getParameter("compoCount",request)!=null &&!StringUtility.getParameter("compoCount", request).equals(""))					
				compoCount=Integer.parseInt(StringUtility.getParameter("compoCount", request).toString());
			
			for (int i=0;i<compoCount;i++)
			{
				if(StringUtility.getParameter("compoValue"+i,request)!=null &&!StringUtility.getParameter("compoValue"+i, request).equals(""))					
					cmpValue=Integer.parseInt(StringUtility.getParameter("compoValue"+i, request).toString());
				compoValueList.append(cmpValue).append(",");
				logger.info("cmpValue::"+cmpValue);
			}

			
			objServiceArgs.put("rdoBtnSearchFlg",rdoBtnSearchFlg);
			objServiceArgs.put("lOrderId",lOrderId);
			objServiceArgs.put("compoValueList",compoValueList);
			objServiceArgs.put("staticAmtOrNot",staticAmtOrNot);
			objServiceArgs.put("salRevOrder",salRevOrder);
			objServiceArgs.put("empCounter",empCounter);
			objServiceArgs.put("groupBillNo",groupBillNo);
			objServiceArgs.put("empArrearDataLst",empArrearDataLst);
			objServiceArgs.put("loggedInOrgPostMst",loggedInOrgPostMst);
  	        objServiceArgs.put("loggedInOrgUserMst",loggedInOrgUserMst);
  	        
  	        //Getting Excel Sheet data
  	        String uploadExcelFlag="N";  	        
  	        AttachmentHelper attachmentHelper = new AttachmentHelper();			 
  	        Map fileItemArrayListMap = attachmentHelper.fileItemArrayListMap;

  	        logger.info("generate map method fileItemArrayListMap"+fileItemArrayListMap);
  	        String key = (String) request.getSession().getAttribute("name") +"arrearData" + request.getSession().getId();
  	        logger.info("generate map method key:"+key);

  	        ArrayList attachmentList = (ArrayList) fileItemArrayListMap.get(key);
  	        
  	        if(attachmentList!=null && attachmentList.size()>0)
  	        {
  	        	logger.info("The size of the attechment is:-"+attachmentList.size());
  	        	uploadExcelFlag="Y";
  	        }
  	      
  	        //removing attachmentList
  	        fileItemArrayListMap.clear();
  	        //UploadedFile file = new UploadedFile(); 
  	        //String type = request.getContentType();

  	        objServiceArgs.put("attachmentList", attachmentList);
  	        objServiceArgs.put("uploadExcelFlag", uploadExcelFlag);
  	        //Getting Excel Sheet data ends
  	        
  	        retObj.setResultValue(objServiceArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			Calendar endCalTime = Calendar.getInstance();
			logger.info("The End time time for the MapEmpWithArrearServiceVOGEN is:-"+endCalTime.getTimeInMillis());
		}
		catch(Exception e)
		{			
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;
		}
		return retObj;
	}

	public ResultObject generateMap(Map arg0) {
		return null;
	}	
	
	public ResultObject updateCmpAmtDataVOGEN(Map objServiceArgs) 
	{
		Calendar calTime = Calendar.getInstance();
		logger.info("The Inintial time for the updateCmpAmtDataVOGEN is:-"+calTime.getTimeInMillis());		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			
			SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat parseDate =new SimpleDateFormat("yyyy-MM-dd");
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");		
			Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
			long loggedInUser = Long.parseLong(loginDetailsMap.get("userId").toString());			
			OrgUserMst loggedInOrgUserMst = new OrgUserMst();
			loggedInOrgUserMst.setUserId(loggedInUser);
			
			long loggedInPost = Long.parseLong(loginDetailsMap.get("primaryPostId").toString());			
			OrgPostMst loggedInOrgPostMst=new OrgPostMst();
			loggedInOrgPostMst.setPostId(loggedInPost);	
			 
			//Getting strCmpEmpDataList
			String strCmpAmtArrList="";			
			int count=0;
			if(StringUtility.getParameter("count", request)!=null&&!StringUtility.getParameter("count", request).equals(""))
				count= Integer.parseInt(StringUtility.getParameter("count", request));			
			logger.info("count:"+count);
			String cmpAmtMpgData="";
			List cmpAmtMpgList = new ArrayList();
			for (int i=0;i<count;i++)
			{
				if(StringUtility.getParameter("cmpAmtMpgData"+i, request)!=null&&!StringUtility.getParameter("cmpAmtMpgData"+i, request).equals(""))
					cmpAmtMpgData= StringUtility.getParameter("cmpAmtMpgData"+i, request);
				logger.info("cmpAmtMpgData:::"+cmpAmtMpgData);
				cmpAmtMpgList.add(cmpAmtMpgData);
			}
						
			long salRevId=0;
			long billSubheadId=0;
			
			if(StringUtility.getParameter("salRevId", request)!=null&&!StringUtility.getParameter("salRevId", request).equals(""))
				salRevId= Long.parseLong(StringUtility.getParameter("salRevId", request));	
			if(StringUtility.getParameter("billSubheadId", request)!=null&&!StringUtility.getParameter("billSubheadId", request).equals(""))
				billSubheadId= Long.parseLong(StringUtility.getParameter("billSubheadId", request));
						
			objServiceArgs.put("cmpAmtMpgList",cmpAmtMpgList);
			objServiceArgs.put("billSubheadId",billSubheadId);
			objServiceArgs.put("salRevId",salRevId);
			
  	       
  	        retObj.setResultValue(objServiceArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			Calendar endCalTime = Calendar.getInstance();
			logger.info("The End time time for the updateCmpAmtDataVOGEN is:-"+endCalTime.getTimeInMillis());
		}
		catch(Exception e)
		{			
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;
		}
		return retObj;
	}	
}
