package com.tcs.sgv.pdpla.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pdpla.valueobject.TrnPdChallanDetail;

public class PDReceiptVOGenerator extends ServiceImpl implements VOGeneratorService
{
	Log gLogger = LogFactory.getLog(getClass());

	/**
     * It generates PD Receipt VO (PDReceiptVO).
     *  
     * @author Sandeep
     * @version 1.0
     * @return objRes ResultObject
     */
	
	public ResultObject generateMap(Map objArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        
        // VO for TrnPdChallanDetail
        TrnPdChallanDetail lObjPdChDetl = generatePDReceiptVO(objArgs);
        
        objArgs.put("TrnPdChallanDetail", lObjPdChDetl);
        
        objRes.setResultValue(objArgs);
        return objRes;
    }
	
	private TrnPdChallanDetail generatePDReceiptVO(Map lMapInput)
	{
		HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
		TrnPdChallanDetail lPdChDetlVO = new TrnPdChallanDetail();
		DateFormat gObjSdFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try
		{
			lPdChDetlVO.setTransactionType(StringUtility.getParameter("txtTransType",request));
			lPdChDetlVO.setInternalTc(StringUtility.getParameter("txtIntTc", request));
			lPdChDetlVO.setDetailHd(StringUtility.getParameter("txtDetHead", request));
			lPdChDetlVO.setFdAgCode(StringUtility.getParameter("txtFdAgCode", request));
			lPdChDetlVO.setAccountNoChln(StringUtility.getParameter("txtPdPlaAccNo", request));
			lPdChDetlVO.setNarration(StringUtility.getParameter("txtNarr", request));
			lPdChDetlVO.setAmount(BigDecimal.valueOf(Long.parseLong(StringUtility.getParameter("txtAmount", request))));
			
            // to set the date
			Date dtDateRcpt = null;
			String lStrDateRcpt = null;
			
			if (StringUtility.getParameter("txtDateRcpt", request).trim().length() > 0)
			{
				lStrDateRcpt = StringUtility.getParameter("txtDateRcpt", request);
				dtDateRcpt = gObjSdFormat.parse(lStrDateRcpt);
				lPdChDetlVO.setReceiptDate(dtDateRcpt);
			}
		}
		catch (ParseException pe)
	    {
	        gLogger.error("Error in Parsing the Date in generatePDReceiptVO. Error is : " + pe, pe);
	    }
	    catch (Exception e)
	    {
	        gLogger.error("Error in generatePDReceiptVO. Error is : " + e, e);
	    }
                
    return lPdChDetlVO;      
	}
}
