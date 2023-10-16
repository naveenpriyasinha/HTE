package com.tcs.sgv.stamp.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.stamp.dao.StampCommonDAOImpl;
import com.tcs.sgv.stamp.valueobject.RltDnmLocation;
import com.tcs.sgv.stamp.valueobject.TrnStampChallanHdr;



public class trnStampChallanHdrVO extends ServiceImpl implements VOGeneratorService 
{
	public ResultObject generateMap(Map objectArgs)
	{
		//System.out.println("I m morpheous");
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		
		String strEnfreshmentNo=request.getParameter("txtEnfreshmentNo");
		String strLocationCode=SessionHelper.getLocationCode(objectArgs);
		String strIndentDate=request.getParameter("txtIndentDate");
		String strVenderCode=request.getParameter("txtVenderCode");
		if (strVenderCode==null || strVenderCode.length()==0)
		{
			strVenderCode="";
		}
		//System.out.println("I m morpheous");
		String strVenderName=request.getParameter("txtVenderName");
		if (strVenderName==null || strVenderName.length()==0)
		{
			strVenderName="";
		}
		//System.out.println("I m morpheous");
		String strPartyName=request.getParameter("txtPartyName");
		if (strPartyName==null || strPartyName.length()==0)
		{
			strPartyName="";
		}
		//System.out.println("I m morpheous");
		String strPartyAddress=request.getParameter("txtPartyAddress");
		if (strPartyAddress==null || strPartyAddress.length()==0)
		{
			strPartyAddress="";
		}
		//System.out.println("I m morpheous");
		String[] strNet=(String[])request.getParameterValues("td_txtNet");
		
		double total=0;
		
		for(int count=0;count<strNet.length;count++)
		{
		
			//System.out.println(""+strNet[count]);
			
		   total = total + Double.parseDouble(strNet[count].toString());
		   //System.out.println(""+total);
		}
		
		TrnStampChallanHdr challanHdrVO= new TrnStampChallanHdr();
		BigDecimal lbgdcMstChallanId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_challan_hdr", objectArgs));
		//System.out.println(""+lbgdcMstChallanId);
		challanHdrVO.setChallanId(lbgdcMstChallanId);
		
		//System.out.println("I m morpheous");
		challanHdrVO.setEnfreshmentNo(new BigDecimal(strEnfreshmentNo));
		Date date=null;
		try
		{
			SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy");
    		date=df.parse(strIndentDate);

		}
		catch(Exception ex){}
		challanHdrVO.setChallanDate(date);
		challanHdrVO.setLocCode(Long.parseLong(strLocationCode));
		challanHdrVO.setPartyAddress(strPartyAddress);
		challanHdrVO.setPrintingStatus("Not Printed");
		challanHdrVO.setStatus("Pending");
		if(strVenderCode!=null && strVenderCode.length()>0)
		{
			challanHdrVO.setVendorCode(Short.parseShort(strVenderCode));
		}
		if (strVenderName!=null && strVenderName.length()>0)
		{
			challanHdrVO.setVendorPartyName(strVenderName);
		}
		else
		{
			challanHdrVO.setVendorPartyName(strPartyName);	
		}
		//System.out.println("I m morpheous");
		challanHdrVO.setChallanValue(new BigDecimal(total));
		objectArgs.put("challanHdrVO", challanHdrVO);
		result.setResultValue(objectArgs);
		return result;
	}
	
}