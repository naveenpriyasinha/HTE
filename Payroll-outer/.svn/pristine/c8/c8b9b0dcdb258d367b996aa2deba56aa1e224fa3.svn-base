package com.tcs.sgv.stamp.service;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.stamp.valueobject.MstVendor;

public class MstVenderVOGeneratorImpl extends ServiceImpl implements VOGeneratorService 
{
	public ResultObject generateMap(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		
		
		String strVenderName=request.getParameter("txtVenderName");
		String strVenderCode=request.getParameter("txtVenderCode");
		String strVenderAddress=request.getParameter("txtVenderAddress");
		String strVenderRegNo=request.getParameter("txtVenderRegNo");
		String strPlaceOfBussiness=request.getParameter("txtPlaceOfBusiness");
		String strActive=request.getParameter("chkActive");
		char cActive;
		if(strActive==null)
		{
			cActive='N';
		}
		else
		{
			cActive='Y';
		}
		String strDiscountAllowed=request.getParameter("rdYes");
		char cDiscount;
		if(strDiscountAllowed.equals("0")==true)
		{
			cDiscount='Y';
		}
		else
		{
			cDiscount='N';
		}
		String strVenderShipStartDate=request.getParameter("txtFromDate");
		String strVenderShipEndDate=request.getParameter("txtEndDate");
		
		MstVendor venderVO=new MstVendor();
		venderVO.setActive(cActive);
		venderVO.setDiscAllowed(cDiscount);
		try
		{
			venderVO.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(strVenderShipEndDate));
		}
		catch(Exception ex){}
		try
		{
			venderVO.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(strVenderShipStartDate));
		}
		catch(Exception ex){}
		if(strPlaceOfBussiness!=null)
		{
			venderVO.setPlaceOfBusiness(strPlaceOfBussiness);
		}
		else
		{
			venderVO.setPlaceOfBusiness("");
		}
		if(strVenderAddress!=null)
		{
			venderVO.setVenAdd(strVenderAddress);
		}
		else
		{
			venderVO.setVenAdd("");
		}
		
		venderVO.setVenCode(Short.parseShort(strVenderCode));
		
		venderVO.setVenRegNo(strVenderRegNo);
		venderVO.setVenName(strVenderName);
		objectArgs.put("venderVO", venderVO);
		result.setResultValue(objectArgs);		
		return result;
	}
}
