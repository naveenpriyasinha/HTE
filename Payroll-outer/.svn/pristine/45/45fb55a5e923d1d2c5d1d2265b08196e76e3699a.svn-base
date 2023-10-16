package com.tcs.sgv.stamp.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.stamp.valueobject.MstStampDenomination;

public class MstStampDnmVOGeneratorImpl extends ServiceImpl implements VOGeneratorService 
{
	public ResultObject generateMap(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		
		String strGroupCode=request.getParameter("cmbStampGroupCode");
		String strGroupName=request.getParameter("txtStampGroupName");
		String strGroupDescription=request.getParameter("txtDescription");
		String strDenominationCode=request.getParameter("txtDenominationCode");
		String strDenominationUnitValue=request.getParameter("txtUnitValue");
		String strLabelPerSheet=request.getParameter("txtLablePerSheet");
		String strDiscount=request.getParameter("txtDiscount");
		
		String strDenominationDescription=request.getParameter("txtDenominationDescription");
		
		MstStampDenomination stampDenominationVO=new MstStampDenomination();
		stampDenominationVO.setDnmCode(Integer.parseInt(strDenominationCode));
		stampDenominationVO.setDnmDesc(strDenominationDescription);
		
		stampDenominationVO.setDnmValue(Integer.parseInt(strDenominationUnitValue));
		stampDenominationVO.setGrpCode(Short.parseShort(strGroupCode));
		try
		{
			stampDenominationVO.setLabelPerSheet(Short.parseShort(strLabelPerSheet));
		}
		catch(Exception ex)
		{
			stampDenominationVO.setLabelPerSheet(Short.parseShort("0"));
		}
		try
		{
			stampDenominationVO.setDiscount(new BigDecimal(strDiscount));
		}
		catch(Exception ex)
		{
			stampDenominationVO.setDiscount(BigDecimal.valueOf(Long.parseLong("0")));
		}
		
		objectArgs.put("stampDenominationVO", stampDenominationVO);
		result.setResultValue(objectArgs);
		return result;
	}
}
