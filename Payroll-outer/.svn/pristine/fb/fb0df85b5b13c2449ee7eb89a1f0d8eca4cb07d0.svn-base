package com.tcs.sgv.stamp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.stamp.dao.MstStampGroupDAOImpl;
import com.tcs.sgv.stamp.dao.MstStampStockDAOImpl;
import com.tcs.sgv.stamp.dao.StampCommonDAOImpl;
import com.tcs.sgv.stamp.valueobject.MstStampGroup;
import com.tcs.sgv.stamp.valueobject.MstStampStock;
import com.tcs.sgv.stamp.valueobject.RltDnmLocation;

public class MstStampStockVOGeneratorImpl extends ServiceImpl implements VOGeneratorService 
{
	public ResultObject generateMap(Map objectArgs)
	{
		List voList=new ArrayList();
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		MstStampGroupDAOImpl groupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		MstStampStockDAOImpl stockDAO=new MstStampStockDAOImpl(MstStampStock.class,serv.getSessionFactory());
		String[] strGroupName=(String[])request.getParameterValues("td_txtGroupname");
		String[] strDenominationCode=(String[])request.getParameterValues("td_txtDenominationCode");
		String[] strNoOfSheets=(String[])request.getParameterValues("td_txtNoOfSheet");
		String[] strNoOfStamp=(String[])request.getParameterValues("td_txtNoOfStamp");
		String[] strUnitValue=(String[])request.getParameterValues("td_txtUnitValue");
		String[] strAmount=(String[])request.getParameterValues("td_txtAmount");
		String[] strFromSeries=(String[])request.getParameterValues("td_txtFromSeries");
		String[] strToSeries=(String[])request.getParameterValues("td_txtToSeries");
		String strLocationCode=SessionHelper.getLocationCode(objectArgs);
		for(int count=0;count<strGroupName.length;count++)
		{
			String groupCode=groupDAO.getGroupCode(strGroupName[count]);
			String strCategoryCode=stockDAO.getCategoryCode(groupCode,commonDAO);
			String denominatioCode=strDenominationCode[count];
			String denominationValue=strUnitValue[count];
			String Sheets=strNoOfSheets[count];
			String Stamp=strNoOfStamp[count];
			String QtyPerSheet=stockDAO.getQtyPerSheet(denominatioCode, groupCode, commonDAO);
			long totalStamp=Long.parseLong(Sheets)*Long.parseLong(QtyPerSheet)+Long.parseLong(Stamp);
			MstStampStock stockVO=new MstStampStock();
			
			stockVO.setCategoryCde(Byte.parseByte(strCategoryCode));
			stockVO.setGrpCode(Short.parseShort(groupCode));
			stockVO.setDnmCode(Integer.parseInt(denominatioCode));
			stockVO.setDnmValue(Integer.parseInt(denominationValue));
			stockVO.setSheetsDl(Integer.parseInt(Sheets));
			stockVO.setStampsDl(Integer.parseInt(Stamp));
			stockVO.setTotalStampsDl((int)totalStamp);
			voList.add(stockVO);
			stockVO=null;
		}
		objectArgs.put("voList", voList);
		result.setResultValue(objectArgs);
		return result;
	}
}
