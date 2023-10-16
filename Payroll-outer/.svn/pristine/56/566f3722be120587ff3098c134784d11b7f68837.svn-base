package com.tcs.sgv.stamp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.tcs.sgv.stamp.valueobject.TrnStampChallanDtls;

public class trnStampChallanDtls  extends ServiceImpl implements VOGeneratorService 
{
	public ResultObject generateMap(Map objectArgs)
	{
		//System.out.println("I am trinity");
		List detailsList=new ArrayList();
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		MstStampGroupDAOImpl groupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());	
		MstStampStockDAOImpl stockDAO=new MstStampStockDAOImpl(MstStampStock.class,serv.getSessionFactory());
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		
		String strEnfreshmentNo=request.getParameter("txtEnfreshmentNo");
		String[] strGroupName=(String[])request.getParameterValues("td_txtGroupname");
		String[] strDenominationCode=(String[])request.getParameterValues("td_txtDenominationCode");
		String[] strNoOfSheets=(String[])request.getParameterValues("td_txtNoOfSheet");
		String[] strNoOfStamp=(String[])request.getParameterValues("td_txtNoOfStamp");
		String[] strUnitValue=(String[])request.getParameterValues("td_txtUnitValue");
		String[] strDiscount=(String[])request.getParameterValues("td_txtDiscount");
		String[] strTotal=(String[])request.getParameterValues("td_txtTotal");
		
		
		
		
		for(int count=0;count<strGroupName.length;count++)
		{
			BigDecimal lbgdcMstChallanId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_challan_dtls", objectArgs));
			String groupCode=groupDAO.getGroupCode(strGroupName[count]);
			String denominatioCode=strDenominationCode[count];
			String denominationValue=strUnitValue[count];
			String Sheets=strNoOfSheets[count];
			String Stamps=strNoOfStamp[count];
			String Total=strTotal[count];
			String Discount=strDiscount[count];
			String QtyPerSheet=stockDAO.getQtyPerSheet(denominatioCode, groupCode, commonDAO);
			long totalStamp=Long.parseLong(Sheets)*Long.parseLong(QtyPerSheet)+Long.parseLong(Stamps);
			
			TrnStampChallanDtls challanDetailsVO=new TrnStampChallanDtls();
			challanDetailsVO.setChallanDtlsId(lbgdcMstChallanId);
			challanDetailsVO.setSheets(Integer.parseInt(Sheets));
			challanDetailsVO.setGrpCode(Short.parseShort(groupCode));
			challanDetailsVO.setDnmCode(Integer.parseInt(denominatioCode));
			challanDetailsVO.setTotalAmnt(new BigDecimal(Total));
			challanDetailsVO.setDiscAmnt(new BigDecimal(Discount));
			challanDetailsVO.setStamps(Integer.parseInt(Stamps));
			challanDetailsVO.setTotalStamps((int)totalStamp);
			challanDetailsVO.setEnfreshmentNo(new BigDecimal(strEnfreshmentNo));
			detailsList.add(challanDetailsVO);
			challanDetailsVO=null;
		}
		objectArgs.put("detailsList", detailsList);
		result.setResultValue(objectArgs);
		return result;
	}
}