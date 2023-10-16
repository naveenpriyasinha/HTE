package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.valueobject.TrnPensionRevalidationDtls;

public class RevalidationServiceVogenerator extends ServiceImpl implements VOGeneratorService 
{

    /* Global Variable for PostId */
    Long gLngPostId = null;

    /* Global Variable for UserId */
    Long gLngUserId = null;

    Log gLogger = LogFactory.getLog(getClass());
    
    
	public ResultObject generateMap(Map orgsMap) 
	{
		 HttpServletRequest request = null;
         SimpleDateFormat lObjSmplDtFmt = new SimpleDateFormat("dd/MM/yyyy");
         TrnPensionRevalidationDtls lObjRevalidVo = null;
         ArrayList<TrnPensionRevalidationDtls> lLstRevalidVo = new ArrayList<TrnPensionRevalidationDtls>();
         ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		  try
		  {
			 setSessionInfo(orgsMap);
			 request = (HttpServletRequest) orgsMap.get("requestObj");
             String[] lStrrevalidRemarks = StringUtility.getParameterValues("txtRevalidRemarks", request);
             String[] lStrName = StringUtility.getParameterValues("txtRevalidName", request);
             String[] lStrAuth = StringUtility.getParameterValues("cmbRevalidAuth", request);
             String[] lStrRevalidDate = StringUtility.getParameterValues("txtRevalidDt", request);
             String[] lStrOutWardDate = StringUtility.getParameterValues("txtOutwrdDt", request);
             String[] lStrInwrdDate = StringUtility.getParameterValues("txtInwrdDt", request);
             for(int i=0;i<lStrrevalidRemarks.length;i++)
             {
            	 if(lStrRevalidDate[i] != null && lStrRevalidDate[i].length() > 0)
            	 {
	        		 lObjRevalidVo = new TrnPensionRevalidationDtls();
	        		 lObjRevalidVo.setRevalidatonDate(lObjSmplDtFmt.parse(lStrRevalidDate[i]));
	        		 lObjRevalidVo.setRemarks(lStrrevalidRemarks[i]);
	        		 if(lStrAuth[i] != null && lStrAuth[i].length() > 0)
	        		 {
	        			 lObjRevalidVo.setRevalidationAuth(Long.parseLong(lStrAuth[i]));
	        		 }
	        		 lObjRevalidVo.setRevalidatorName(lStrName[i]);
	        		 lObjRevalidVo.setCreatedUserId(new BigDecimal(gLngUserId));
	        		 lObjRevalidVo.setCreatedPostId(new BigDecimal(gLngPostId));
	        		 lObjRevalidVo.setCreatedDate(new Date());
	        		 lObjRevalidVo.setTrnCounter(1);
	        		 lObjRevalidVo.setRevalidCount(i+1);
	        		 if(lStrInwrdDate[i] != null && lStrInwrdDate.length > 0)
	        		 {
	        			 lObjRevalidVo.setInwardDate(lObjSmplDtFmt.parse(lStrInwrdDate[i]));
	        		 }
	        		 if(lStrOutWardDate[i] != null && lStrOutWardDate[i].length() > 0)
	        		 {
	        			 lObjRevalidVo.setOutwardDate(lObjSmplDtFmt.parse(lStrOutWardDate[i]));
	        		 }
	        		 lLstRevalidVo.add(lObjRevalidVo);            	 
        		 }
             }
             orgsMap.put("RevalidVoList", lLstRevalidVo);
             resObj.setResultValue(orgsMap);
		  }
		  catch(Exception e)
		  {
			  gLogger.error("Error is "+e,e);
		  }
		return resObj;
	}
	  private void setSessionInfo(Map inputMap)
	  {
	      gLngPostId = SessionHelper.getPostId(inputMap);
	      gLngUserId = SessionHelper.getUserId(inputMap);
	 }
}
