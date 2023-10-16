package com.tcs.sgv.stamp.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.stamp.valueobject.TrnStampChallanDtls;

public class MstStampChallanDtlsVO extends ServiceImpl implements VOGeneratorService 
{
	public ResultObject generateMap(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		BigDecimal lbgdcMstGrpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_challan_dtls", objectArgs));
		TrnStampChallanDtls trnStampChallanDtlsVO =new TrnStampChallanDtls();
		String strEnfreshmentNo=request.getParameter("txtEnfreshmentNo").toString();
		String strDenominationCode=request.getParameter("denominationCode").toString();
		String strNoOfSheets=request.getParameter("txtNoOfSheets").toString();
		String strNoOfStamps=request.getParameter("txtNoOfStamps").toString();
		
		String strMajorHead=request.getParameter("txtMajorHead").toString();
		String strSubMajorHead=request.getParameter("txtSubMajorHead").toString();
		String strMinorHead=request.getParameter("txtMinorHead").toString();
		String strSubHead=request.getParameter("txtSubHead").toString();
		
		/*trnStampChallanDtlsVO.setCategoryCode(Byte.parseByte(strCategoryCode));
		trnStampChallanDtlsVO.setGrpCode(Short.parseShort(strGroupCode));
		trnStampChallanDtlsVO.setGrpDesc(strDescription);
		trnStampChallanDtlsVO.setGrpId(lbgdcMstGrpId);
		trnStampChallanDtlsVO.setGrpName(strGroupName);
		trnStampChallanDtlsVO.setMinHd(Short.parseShort(strMinorHead));
		trnStampChallanDtlsVO.setMjrHd(Short.parseShort(strMajorHead));
		trnStampChallanDtlsVO.setSubHd(Short.parseShort(strSubHead));
		trnStampChallanDtlsVO.setSubMjrHd(Short.parseShort(strSubMajorHead));*/
		objectArgs.put("trnStampChallanDtlsVO", trnStampChallanDtlsVO);		
		return result;
	}
}