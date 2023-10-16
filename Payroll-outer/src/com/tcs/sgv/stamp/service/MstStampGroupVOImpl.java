package com.tcs.sgv.stamp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.stamp.valueobject.MstStampGroup;

public class MstStampGroupVOImpl extends ServiceImpl implements VOGeneratorService 
{
	public ResultObject generateMap(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		
		MstStampGroup mstStampGroupVO=new MstStampGroup();
		String strGroupCode=request.getParameter("txtStampGroupCode").toString();
		String strGroupName=request.getParameter("txtStampGroupName").toString();
		String strCategoryCode=request.getParameter("cmbMainGroupType").toString();
		String strDescription=request.getParameter("txtDescription").toString();
		
		String strMajorHead=request.getParameter("txtMajorHead").toString();
		String strSubMajorHead=request.getParameter("txtSubMajorHead").toString();
		String strMinorHead=request.getParameter("txtMinorHead").toString();
		String strSubHead=request.getParameter("txtSubHead").toString();
		
		mstStampGroupVO.setCategoryCode(Byte.parseByte(strCategoryCode));
		mstStampGroupVO.setGrpCode(Short.parseShort(strGroupCode));
		mstStampGroupVO.setGrpDesc(strDescription);
		
		mstStampGroupVO.setGrpName(strGroupName);
		mstStampGroupVO.setMinHd(Short.parseShort(strMinorHead));
		mstStampGroupVO.setMjrHd(Short.parseShort(strMajorHead));
		mstStampGroupVO.setSubHd(Short.parseShort(strSubHead));
		mstStampGroupVO.setSubMjrHd(Short.parseShort(strSubMajorHead));
		objectArgs.put("mstStampGroupVO", mstStampGroupVO);		
		return result;
	}
}
