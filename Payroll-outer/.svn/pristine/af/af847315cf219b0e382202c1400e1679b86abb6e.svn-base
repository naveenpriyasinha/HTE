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
import com.tcs.sgv.pdpla.valueobject.TrnPdChqDetail;

public class PDPaymentVOGenerator extends ServiceImpl implements VOGeneratorService
{
	Log gLogger = LogFactory.getLog(getClass());

	/**
     * It generates PD Payment VO (PDPaymentVO).
     *  
     * @author Sandeep
     * @version 1.0
     * @return objRes ResultObject
     */
	
	public ResultObject generateMap(Map objArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        
        // VO for TrnPdChqDetail
        TrnPdChqDetail lObjPdChqDetl = generatePDPaymentVO(objArgs);
        
        objArgs.put("TrnPdChqDetail", lObjPdChqDetl);
        
        objRes.setResultValue(objArgs);
        return objRes;
    }
	
	private TrnPdChqDetail generatePDPaymentVO(Map lMapInput)
	{
		HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
		TrnPdChqDetail lPdChqDetlVO = new TrnPdChqDetail();
		DateFormat gObjSdFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try
		{
			lPdChqDetlVO.setTransactionType(StringUtility.getParameter("txtTransType",request));
			lPdChqDetlVO.setInternalTc(StringUtility.getParameter("txtIntTc", request));
			lPdChqDetlVO.setDetailHd(StringUtility.getParameter("txtDetHead", request));
			lPdChqDetlVO.setFdAgCode(StringUtility.getParameter("txtFdAgCode", request));
			lPdChqDetlVO.setAccountNoChq(StringUtility.getParameter("txtPdPlaAccNo", request));
			lPdChqDetlVO.setNarration(StringUtility.getParameter("txtNarr", request));
			lPdChqDetlVO.setAmount(BigDecimal.valueOf(Long.parseLong(StringUtility.getParameter("txtAmount", request))));
			lPdChqDetlVO.setChqNo(Integer.parseInt(StringUtility.getParameter("txtCheque", request)));
//			to set the date
			Date dtDatePymt = null;
			String lStrDatePymt = null;
			
			if (StringUtility.getParameter("txtDatePymt", request).trim().length() > 0)
			{
				lStrDatePymt = StringUtility.getParameter("txtDatePymt", request);
				dtDatePymt = gObjSdFormat.parse(lStrDatePymt);
				lPdChqDetlVO.setPaymentDate(dtDatePymt);
			}
		}
		catch (ParseException pe)
	    {
	        gLogger.error("Error in Parsing the Date in generatePDPaymentVO. Error is : " + pe);
	    }
	    catch (Exception e)
	    {
	        gLogger.error("Error in generatePDPaymentVO. Error is : " + e);
	    }
                
    return lPdChqDetlVO;      
	}
}
