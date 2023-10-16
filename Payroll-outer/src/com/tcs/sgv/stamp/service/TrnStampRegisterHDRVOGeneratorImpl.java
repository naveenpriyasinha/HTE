package com.tcs.sgv.stamp.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.stamp.dao.StampCommonDAOImpl;
import com.tcs.sgv.stamp.valueobject.RltDnmLocation;
import com.tcs.sgv.stamp.valueobject.TrnStampRegisterHdr;

public class TrnStampRegisterHDRVOGeneratorImpl extends ServiceImpl implements VOGeneratorService 
{
	public ResultObject generateMap(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		String strIndentNo=request.getParameter("indentNo");
		String strLocationCode=SessionHelper.getLocationCode(objectArgs);
		String strReceiptValue=request.getParameter("stampReceiptValue");
		String strTranDate=request.getParameter("txtStampReceiveDate");
		boolean isTreasury=commonDAO.isTreasury(strLocationCode);
		String strTypeCode="";
		if(isTreasury==true)
		{
			strTypeCode="01";
		}
		else
		{
			strTypeCode="04";
		}
		TrnStampRegisterHdr regHdrVO=new TrnStampRegisterHdr();
		
		regHdrVO.setTypeCode(strTypeCode);
		regHdrVO.setRefDocNo(new BigDecimal(strIndentNo));
		regHdrVO.setLocCode(Long.parseLong(strLocationCode));
		regHdrVO.setGrossAmt(new BigDecimal(strReceiptValue));
		Date date=null;
		try
		{
			SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy");
    		date=df.parse(strTranDate);

		}
		catch(Exception ex){}
		regHdrVO.setTrnDate(date);
		objectArgs.put("registerHDRVO", regHdrVO);
		result.setResultValue(objectArgs);
		return result;
	}
}
