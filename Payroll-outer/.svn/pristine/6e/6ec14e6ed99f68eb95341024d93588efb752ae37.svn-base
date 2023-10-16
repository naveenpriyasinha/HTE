package com.tcs.sgv.stamp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
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
import com.tcs.sgv.stamp.valueobject.TrnStampRegesterDtls;

public class TrnStampRegisterDTLSVOGeneratorImpl  extends ServiceImpl implements VOGeneratorService 
{
	public ResultObject generateMap(Map objectArgs)
	{
		List detailsList=new ArrayList();
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
			BigDecimal lbgdcMstGrpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_regester_dtls", objectArgs));
			String groupCode=groupDAO.getGroupCode(strGroupName[count]);
			String strCategoryCode=stockDAO.getCategoryCode(groupCode,commonDAO);
			String denominatioCode=strDenominationCode[count];
			String denominationValue=strUnitValue[count];
			String Sheets=strNoOfSheets[count];
			String Stamp=strNoOfStamp[count];
			String QtyPerSheet=stockDAO.getQtyPerSheet(denominatioCode, groupCode, commonDAO);
			long totalStamp=Long.parseLong(Sheets)*Long.parseLong(QtyPerSheet)+Long.parseLong(Stamp);
			TrnStampRegesterDtls regDetailsVO=new TrnStampRegesterDtls();
			regDetailsVO.setTrnRegDtlsId(lbgdcMstGrpId);
			regDetailsVO.setSheets(Integer.parseInt(Sheets));
			regDetailsVO.setGrpCode(Short.parseShort(groupCode));
			regDetailsVO.setDnmCode(Integer.parseInt(denominatioCode));
			regDetailsVO.setDnmValue(Integer.parseInt(denominationValue));
			regDetailsVO.setStamps(Integer.parseInt(Stamp));
			regDetailsVO.setTotalStamps((int)totalStamp);
			detailsList.add(regDetailsVO);
			regDetailsVO=null;
		}
		objectArgs.put("detailsList", detailsList);
		result.setResultValue(objectArgs);
		return result;
	}
}
