package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class BillGroupPostMpgVOGEN extends ServiceImpl{
	Log logger = LogFactory.getLog(getClass());
	
		public ResultObject generateMapForBillPost(Map objectArgs) 
		{
			//System.out.println("====> generateMapForBillPost.. in VOGEN.....");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			ResultObject resultObj = new ResultObject(ErrorConstants.SUCCESS);
			
			try
			{
				String[] MappedPostIdList = StringUtility.getParameterValues("MappedPostId",request);
				String MappedPostId = "";
				
				///System.out.println("====>inVOGEN MappedPostIdList :: "+MappedPostIdList.length);
				long sizeofMappedPostList = MappedPostIdList.length;
				
				for (int x=0;x<MappedPostIdList.length;x++)
				{
	                    if(MappedPostIdList[x]!=null && !MappedPostIdList[x].trim().equals(""))
	                    MappedPostId+= "," +MappedPostIdList[x];
				}
				
				if(MappedPostId.length()>1)
					MappedPostId=MappedPostId.substring(1);
					
				//System.out.println("===> in vogen size of MappedPostId is :: "+MappedPostId.toString());
				
				
				long cmbBillNo=0;
				if(StringUtility.getParameter("cmbBillNo", request)!=null)
					cmbBillNo=Long.parseLong(StringUtility.getParameter("cmbBillNo", request));
	        	//System.out.println("===> in VOGEN Bill No :: "+cmbBillNo);
	        	
	        	objectArgs.put("sizeofMappedPostList", sizeofMappedPostList);
	        	
	    		objectArgs.put("cmbBillNo",cmbBillNo);
	    		objectArgs.put("MappedPostId", MappedPostId);
	    		
				//r.info("size of MappedPostId is:-->>>"+MappedPostId.toString());
				resultObj.setResultValue(objectArgs);
				resultObj.setResultCode(ErrorConstants.SUCCESS);
			}
			catch(Exception e)
			{
				logger.error("Error is: "+ e.getMessage());
				 logger.error("Exception in VOGeneratorImpl.generateMap " + e, e);
		         ResultObject retObject = new ResultObject(ErrorConstants.ERROR);
		         resultObj.setThrowable(e);
			}
			return resultObj;
		}
	

}
