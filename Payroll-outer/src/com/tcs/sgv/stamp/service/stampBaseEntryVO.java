package com.tcs.sgv.stamp.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.stamp.dao.StampCommonDAOImpl;
import com.tcs.sgv.stamp.valueobject.MstStampCategory;
import com.tcs.sgv.stamp.valueobject.RltDnmLocation;

public class stampBaseEntryVO extends ServiceImpl implements VOGeneratorService 
{
	public ResultObject generateMap(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		BigDecimal lbgdcMstCategoryId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("mst_stamp_category", objectArgs));
		//System.out.println(""+lbgdcMstCategoryId);
		MstStampCategory mstStampCategoryVO=new MstStampCategory();
		String strStampBaseGroupName=request.getParameter("txtStampBaseGroupName").toString();
		String strDescription=request.getParameter("txtDescription").toString();
		String strCategoryCode=getMaxCategoryCode(objectArgs);
		
		mstStampCategoryVO.setCategoryName(strStampBaseGroupName);
		mstStampCategoryVO.setCategoryCode(Byte.parseByte(strCategoryCode));
		mstStampCategoryVO.setCategoryDesc(strDescription);
		mstStampCategoryVO.setCategoryId(lbgdcMstCategoryId);
		
		
		objectArgs.put("mstStampCategoryVO", mstStampCategoryVO);		
		return result;
	}
	public String getMaxCategoryCode(Map objectArgs)
	{
		String strCategoryCode="";
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		Iterator itResult=commonDAO.getIterator("mst_stamp_category", "max(category_code)");
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strCategoryCode=itResult.next().toString();
				Long categoryCode=Long.parseLong(strCategoryCode);
				categoryCode=categoryCode+1;
				strCategoryCode=String.valueOf(categoryCode);
			}
		}	
		else
		{
			strCategoryCode="1";
		}
		return strCategoryCode;
		
	}
}
