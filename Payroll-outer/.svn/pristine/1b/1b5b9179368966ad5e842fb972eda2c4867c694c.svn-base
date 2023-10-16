package com.tcs.sgv.stamp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.stamp.dao.MstStampGroupDAOImpl;
import com.tcs.sgv.stamp.dao.ReportQueryDAOImpl;
import com.tcs.sgv.stamp.valueobject.MstStampGroup;

public class ReportServiceImpl extends ServiceImpl
{
	public ResultObject generateSingleLockRegReportData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		
		String strFromDate=request.getParameter("fromDate").toString();
		String strToDate=request.getParameter("toDate").toString();
		String strGroupCode=request.getParameter("cmbGroupname").toString();
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		List[] groupCodeAndGroupname=stampGroupDAO.getGroupNameList(strGroupCode);
		List groupCode=groupCodeAndGroupname[0];
		List groupName=groupCodeAndGroupname[1];
		List singleLockData=new ArrayList();
		List headerInfo=null;
		List headerColSpan=null;
		List headerRowSpan=null;
		List headerDet=null;
		List headerWidth=null;
		List reportData=new ArrayList();
		List data=new ArrayList();
		List dataWidth=new ArrayList();
		boolean isHeaderAdd=false;
		
		if(groupCode.size()>0)
		{
			List outerList=new ArrayList();
			Iterator itGroup=groupCode.iterator();
			while(itGroup.hasNext())
			{
				headerInfo=new ArrayList();
				headerColSpan=new ArrayList();
				headerRowSpan=new ArrayList();
				headerDet=new ArrayList();
				headerWidth=new ArrayList();
				
				
				String strGroupcode=itGroup.next().toString();
				headerInfo.add("Date");
				headerColSpan.add("1");
				headerRowSpan.add("2");
				headerWidth.add("+10");
				dataWidth.add("+10");
				headerDet.add("");
				headerInfo.add("Transaction");
				headerColSpan.add("1");
				headerRowSpan.add("2");
				headerWidth.add("+20");
				headerDet.add("");
				dataWidth.add("+20");
				ReportQueryDAOImpl report=new ReportQueryDAOImpl(MstStampGroup.class,serv.getSessionFactory());
				List denominationTable=report.getDenominationCode(strGroupcode);
				if(denominationTable.size()>0)
				{
					Iterator itDenomination=denominationTable.iterator();
					while(itDenomination.hasNext())
					{
						String strDenominationCode=itDenomination.next().toString();
						headerInfo.add(strDenominationCode);
						headerColSpan.add("2");
						headerRowSpan.add("0");
						headerWidth.add("+20");
						headerDet.add("Number");
						headerDet.add("Value");
						dataWidth.add("+10");
						dataWidth.add("+10");
					}
				}
				headerInfo.add("Total");
				dataWidth.add("+10");
				headerColSpan.add("1");
				headerRowSpan.add("2");
				headerWidth.add("+20");
				
				outerList=new ArrayList();
				outerList.add(headerInfo);
				outerList.add(headerColSpan);
				outerList.add(headerRowSpan);
				outerList.add(headerDet);
				if(isHeaderAdd==false)
				{
					isHeaderAdd=true;
					reportData=printUtility(reportData,headerInfo,headerWidth);
					reportData=printUtility(reportData, headerDet, dataWidth);
				}
//				dump data
				List innerData=new ArrayList();
				innerData.add("12/12/07");
				innerData.add("TO Me again hello how are you my dear friend this line is printed");
				innerData.add("12");
				innerData.add("12");
				
				
				data.add(innerData);
				reportData=printUtility(reportData, innerData,dataWidth);
				
				
				innerData=new ArrayList();
				innerData.add("12/12/08");
				innerData.add("To Treasury hello how are you");
				innerData.add("11111111111111111111111111111111111111111111111");
				innerData.add("2");
				
				data.add(innerData);
				outerList.add(data);
				reportData=printUtility(reportData, innerData,dataWidth);
				//end dump data
				outerList.add(headerWidth);
			

				singleLockData.add(outerList);
				outerList=null;
				
			}
		}
		objectArgs.put("groupName", groupName);
		objectArgs.put("SLData", singleLockData);
		
		HttpSession session=request.getSession();
		session.setAttribute("reportData", reportData);		
		result.setResultValue(objectArgs);
		result.setViewName("displaySLReport");
		return result;
	}
	public List printUtility(List reportData,List data,List headerWidth)
	{
		String strReportData="";
		String strData="";
		String strWidth="";
		String strAlign="";
		String strLeftData="";
		String[] widthDetails=new String[headerWidth.size()];
		String[] alignDetails=new String[headerWidth.size()];
		int width=0;
		SortedMap leftData=new TreeMap();
		if(data.size()>0)
		{
			Iterator itData=data.iterator();
			Iterator itWidth=headerWidth.iterator();
			int count=0;
			while(itData.hasNext())
			{
				strData=itData.next().toString();
				strWidth=itWidth.next().toString();
				strAlign=strWidth.substring(0,1);
				strWidth=strWidth.substring(1);
				width=Integer.parseInt(strWidth);
				widthDetails[count]=strWidth;
				alignDetails[count]=strAlign;
				if(strData.length()<=width)
				{
					strReportData=strReportData+" "+getString(strData, width,strAlign);
				}
				else
				{
					strLeftData=strData.substring(width);
					strData=strData.substring(0,width);
					strReportData=strReportData+" "+getString(strData, width,strAlign);
					leftData.put(count, strLeftData);
				}
				count++;			
			}
			
		}
		if(leftData.size()>0)
		{
			while(leftData.size()>0)
			{
				reportData.add(strReportData);
				strReportData="";
			    for(int i=0;i<widthDetails.length;i++)
			    {
					boolean present=leftData.containsKey(i);
					int len=0;
					try
					{
					 len=Integer.parseInt(widthDetails[i].toString());
					 
					}
					catch(Exception ex)
					{
						//System.out.println("Error at "+i);
						continue;
					}
					
					String strInnerWidth=widthDetails[i].toString();
					width=Integer.parseInt(strInnerWidth);
					strAlign=alignDetails[i].toString();
					if(present==true)
					{
					    strData=leftData.get(i).toString();
					    if(strData.length()>len)
					    {
							strLeftData=strData.substring(len);
							try
							{
								strData=strData.substring(0,len);//change
							}
							catch(Exception ex)
							{
							    strData=strData.substring(0);
							}
							leftData.remove(i);
							
							leftData.put(i,strLeftData);
							strReportData=strReportData+" "+getString(strData, width,strAlign);						
					    }
					    else
					    {
					    	strReportData=strReportData+" "+getString(strData, width,strAlign);						
							leftData.remove(i);
					    }
					}
					else
					{
					    strData=getSpace(len);
					    strReportData=strReportData+" "+getString(strData, width,strAlign);
					}
			    }
									    
			}
		}
		reportData.add(strReportData);		
		return reportData;
	}
	public String getString(String strData,int width,String strAlign)
	{
		String strFinalString="";
		String strSpace="";
		int len=0;
		if(strAlign.equals("+")==true)
		{
			strFinalString=strData;
			len=width-strData.length();
			strSpace=getSpace(len);
			strFinalString=strData+strSpace;
		}
		else
		{
			len=width-strData.length();
			strSpace=getSpace(len);
			strFinalString=strSpace+strData;
		}
		return strFinalString;
	}
	public String getSpace(int len)
	{
		String strSpace="";
		for(int i=0;i<len;i++)
		{
			strSpace=strSpace+" ";
		}
		return strSpace;
	}
	public String printLine(int len)
	{
		String strLine="";
		for(int i=0;i<len;i++)
		{
			strLine=strLine+"-";
		}
		return strLine;
	}
	public ResultObject printReports(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		List reportData=(List)session.getAttribute("reportData");
		objectArgs.put("reportData", reportData);
		result.setResultValue(objectArgs);
		result.setViewName("printReport");
		return result;
	}
}
