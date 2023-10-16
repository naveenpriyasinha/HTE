package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;

public class ROPVOGenerator extends ServiceImpl implements VOGeneratorService
{

	    /* Global Variable for PostId */
	    Long gLngPostId = null;

	    /* Global Variable for UserId */
	    Long gLngUserId = null;

	    Log gLogger = LogFactory.getLog(getClass());

	public ResultObject generateMap(Map orgsMap) 
	{
		HttpServletRequest request = null;
        setSessionInfo(orgsMap);
        List<TrnPnsncaseRopRlt> lLstRopVo = new ArrayList<TrnPnsncaseRopRlt>();
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        request = (HttpServletRequest) orgsMap.get("requestObj");
        try
        {
        	String[]  lStrropChkBox = StringUtility.getParameterValues("ropChkBox", request);
        	String lStrPPONo = StringUtility.getParameter("hidPPONo", request);
        	for(int i=0;i<lStrropChkBox.length;i++)
        	{
        		TrnPnsncaseRopRlt lObjRopVo = new TrnPnsncaseRopRlt();
        		if(lStrropChkBox[i].equals("1986"))
        		{
        			String lstrAdaptType = StringUtility.getParameter("hidAdptedType", request);
        			String lstrAdaptFlag = StringUtility.getParameter("hidAdptedFlag", request);
        			lObjRopVo.setAdaptedType(lstrAdaptType+lstrAdaptFlag);
        		}
        		lObjRopVo.setRop(lStrropChkBox[i]);
        		lObjRopVo.setPpoNo(lStrPPONo);
        		lObjRopVo.setCreatedDate(new Date());
        		lObjRopVo.setCreatedPostId(new BigDecimal(gLngPostId));
        		lObjRopVo.setCreatedUserId(new BigDecimal(gLngUserId));
        		lLstRopVo.add(lObjRopVo);
        	}
        	orgsMap.put("ROPList", lLstRopVo);
        	resObj.setResultValue(orgsMap);
        }
        catch(Exception e)
        {
        	gLogger.error("Error is"+e,e);
        }
		return resObj;
	}
	  private void setSessionInfo(Map inputMap)
	  {
	      gLngPostId = SessionHelper.getPostId(inputMap);
	      gLngUserId = SessionHelper.getUserId(inputMap);
	 }
}
