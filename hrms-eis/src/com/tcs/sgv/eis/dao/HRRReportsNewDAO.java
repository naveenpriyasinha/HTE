package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.event.reports.ReportEvent;
import com.tcs.sgv.common.event.reports.ReportEventListener;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class HRRReportsNewDAO extends DefaultReportDataFinder 
implements ReportDataFinder,ReportEventListener
{
	private Log logger = LogFactory.getLog(getClass());
	
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		logger.info("==> in HRRReportsNewDAO findReportData...");
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
		int finalpagesize=20;
		long billNo=0;
		long month=0;
		long year=0;
		long TotalAmt=0;
		
		StyleVO[] boldStyleVO = new StyleVO[1];
		boldStyleVO[0] = new StyleVO();
		boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
		
		
		ArrayList DataList = new ArrayList();
		String ShowSignatureFlg=CheckIfNull(report.getParameterValue( "Show Signature" ));
		
		logger.info("==== ==> ShowSignatureFlg : "+ShowSignatureFlg);
		try{
			 logger.info("==> in try...");
			 month = StringUtility.convertToLong(report.getParameterValue("Month")!=null?report.getParameterValue("Month").toString():"-1");
			 year = StringUtility.convertToLong(report.getParameterValue("Year")!=null?report.getParameterValue("Year").toString():"0");
			 
			 String BillNoinner="";
			 int l=0;
			 String subheadCode="";
			 String classIds="";
			 String desgnIds="";
			 String portType="";
			 String BillNo="";	    
			 
			 BillNoinner=CheckIfNull(report.getParameterValue( "BillNo" ));
			 logger.info(BillNoinner);
			 StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
			 while(st1.hasMoreTokens())
				{
					if(l==0)
						subheadCode=st1.nextToken();
					else if(l==1)
						classIds=st1.nextToken();
					else if(l==2)
						desgnIds=st1.nextToken();
					else if(l==3)
						portType=st1.nextToken();
					else if(l==4)
						BillNo=st1.nextToken();
					//logger.info("Actual value for bill no  "+BillNo);
					l++;
				} 
			 logger.info("==> in try...billNo ::"+BillNo);
			 logger.info("==> in try... month::"+month);
			 logger.info("==> in try...year ::"+year);
			 List actionList = new ArrayList();
			 PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			 actionList = payDao.getHRRList(Long.valueOf(BillNo),month,year);
			 ArrayList rowList = null;
			 
			// ArrayList DataList = new ArrayList();
			 String row0="";
			 String row1="";
			 String row2="";
			 long row3=0;
			 long row4=0;
			 long Outstand=0;
			 String NameDesgId="";
			 String Address="";
			 
			 for (int i = 0; i <actionList.size(); i++) 
			 {
				 	int cnt=1;
				 	int pageCnt=1;
				 	rowList = new ArrayList();
					Object[] row = (Object[])actionList.get(i);
					
					logger.info("====> row 0 :: "+(row[0].toString()));
					logger.info("====> row 1 :: "+row[1].toString());
					logger.info("====> row 2 :: "+row[2].toString());
					logger.info("====> row 3 :: "+((Double)(row[3])).longValue());
					//logger.info("====> row 4:: "+((Long)(row[4])).longValue());
					logger.info("====> row 4 :: "+(row[4].toString()));
					logger.info("====> row 5 :: "+(row[5].toString()));
					logger.info("====> row 6 :: "+(row[6].toString()));
					logger.info("====> row 7 :: "+(row[7].toString()));
					
					row0 = row[0].toString();
					row1 = row[1].toString();
					row2 = row[2].toString();
					row3 = ((Double)(row[3])).longValue();
					
					
					NameDesgId = row0 +" ("+row1+") "+"("+row2+")";
					Address = (row[4].toString())+", "+(row[5].toString())+", "+(row[6].toString())+", "+(row[7].toString());
					TotalAmt+= row3;
					logger.info("===> Total Amt :: "+TotalAmt);
					rowList.add(String.valueOf(i+1));//1
					rowList.add(NameDesgId);//2
					rowList.add(Address);//3
					rowList.add(String.valueOf(row3));//4
					rowList.add("0");//5
					rowList.add("0");//6
					rowList.add("0");//7
					rowList.add(String.valueOf(row3));//8
					rowList.add("0");//9
					rowList.add("0");//10
					rowList.add("0");//11
					DataList.add(rowList);
					
				/*	if((cnt%finalpagesize)==0) 
					{
						
						//dataStyle1.setData("Total"); 
						rowList = new ArrayList();
						rowList.add(new PageBreak());
						
						StyledData dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(totalIncomeTax);
						row.add(dataStyle1);
						
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(pageCnt);                   
						rowList.add(dataStyle1);//2
						
					}
					cnt++;*/
			}
			 
			 	
			 	/*ArrayList rowForinRuppe = new ArrayList();

				logger.info("TOTALLLLLLLLLLLLLLL");
				
				StyleVO[] centerboldStyleVO = new StyleVO[2];
				centerboldStyleVO[0] = new StyleVO();
				centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
				centerboldStyleVO[1] = new StyleVO();
				centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				centerboldStyleVO[1].setStyleValue("Left"); 
				StyledData dataStyle2 = new StyledData();
				dataStyle2.setStyles(centerboldStyleVO);
				dataStyle2.setData("Total Ruppes In Words:  "+ConvertNumbersToWord.convert(Math.round(TotalAmt))+" only.");
				dataStyle2.setColspan(10);
				rowForinRuppe.add(dataStyle2);
				
				
				for(int c=0;c<10;c++)
					rowForinRuppe.add("");
				
				DataList.add(rowForinRuppe);*/
			 
			 	String reName="Schedule for House Rent Recovery"+System.getProperty("line.separator")+"From Major Head 0216";
				report.setReportName(reName);
				
				String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};        
				Calendar cale = Calendar.getInstance(); 
				String MONTH = monthName[cale.get(Calendar.MONTH)]; 	          
						 
				String lstrmonth = String.valueOf(month);
				Integer in = Integer.parseInt(lstrmonth);
				
				String monthname = monthName[in-1];
				
				/*logger.info("monthName[i-1]"+monthName[in-1]);
				logger.info("MONTHMONTHMONTHMONTHMONTH" +MONTH);
				logger.info("monthnamemonthname" +monthname);*/
				//logger.info("curYearcurYearcurYearcurYearcurYear" +curYear);
				
				
				
			/*	StyledData Head1 = new StyledData();
				Head1.addStyle(IReportConstants.BORDER,"NO");
				Head1.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				Head1.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLD);
				Head1.setData("From Major Head");
				headerColList=new ArrayList();
				headerColList.add(Head1);
				headerRowList.add(headerColList);*/
				
				List headerRowList=new ArrayList();
				List headerColList=new ArrayList();
				
				StyledData Head2 = new StyledData();
				Head2.addStyle(IReportConstants.BORDER,"NO");
				Head2.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
				Head2.setData("For the Month of"+" "+monthname+" "+year);
				headerColList=new ArrayList();
				headerColList.add(Head2);
				headerRowList.add(headerColList);
				
				
				StyledData Head3 = new StyledData();
				StyledData Head4 = new StyledData();
				Head3.addStyle(IReportConstants.BORDER,"NO");
				Head3.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
				Head3.setData("Name of office : District Treasury office, Pune (003268)");
				
				Head4.addStyle(IReportConstants.BORDER,"NO");
				Head4.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
				Head4.setData("Treasury : PUNE (2201)");
				
				headerColList=new ArrayList();
				headerColList.add(Head3);
				headerColList.add(Head4);
				//headerRowList.add(headerColList);
				
				headerRowList.add(headerColList);
				
				
				StyledData Head5 = new StyledData();
				Head5.addStyle(IReportConstants.BORDER,"NO");
				Head5.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
				Head5.setData("Custodian Division Code : 2250 - (P.W.D. PUNE)");
				headerColList=new ArrayList();
				headerColList.add(Head5);
				headerRowList.add(headerColList);
				
				/*StyledData HeadBelow = new StyledData();
				HeadBelow.addStyle(IReportConstants.BORDER,"NO");
				HeadBelow.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
				HeadBelow.addStyle(IReportConstants.ADDL_HEADER_LOCATION,IReportConstants.VALUE_ADDL_HEADER_LOCATION_BELOW);
				HeadBelow.setData("Custodian Division Code : 2250 - (P.W.D. PUNE)");
				headerColList=new ArrayList();
				headerColList.add(HeadBelow);
				headerRowList.add(headerColList);*/
				
				
				TabularData headerTable=new TabularData(headerRowList);
				headerTable.addStyle(IReportConstants.BORDER, IReportConstants.VALUE_NO);
				headerTable.addStyle(IReportConstants.ALIGN_CENTER, "YES");
				report.setAdditionalHeader(headerTable);
				
				/*logger.info("=====> ShowSignatureFlg :: "+ShowSignatureFlg);
				if(!ShowSignatureFlg.equals("No"))
		  		{
					List headerRowListForSign=new ArrayList();
					List headerColListForSign=new ArrayList();
					
					StyledData Head2ForSign = new StyledData();
					Head2ForSign.addStyle(IReportConstants.BORDER,"NO");
					Head2ForSign.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					Head2ForSign.setData("For the Month of"+" "+monthname+" "+year);
					headerColListForSign=new ArrayList();
					headerColListForSign.add(Head2ForSign);
					headerRowListForSign.add(headerColListForSign);
					
					
					StyledData Head3ForSign = new StyledData();
					StyledData Head4ForSign = new StyledData();
					Head3ForSign.addStyle(IReportConstants.BORDER,"NO");
					Head3ForSign.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					Head3ForSign.setData("Name of office : District Treasury office, Pune (003268)");
					
					Head4ForSign.addStyle(IReportConstants.BORDER,"NO");
					Head4ForSign.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					Head4ForSign.setData("Treasury : PUNE (2201)");
					
					headerColListForSign=new ArrayList();
					headerColListForSign.add(Head3ForSign);
					headerColListForSign.add(Head4ForSign);
					//headerRowListForSign.add(headerColListForSign);
					
					headerRowListForSign.add(headerColListForSign);
					
					
					StyledData Head5ForSign = new StyledData();
					Head5ForSign.addStyle(IReportConstants.BORDER,"NO");
					Head5ForSign.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					Head5ForSign.setData("Custodian Division Code : 2250 - (P.W.D. PUNE)");
					headerColListForSign=new ArrayList();
					headerColListForSign.add(Head5ForSign);
					headerRowListForSign.add(headerColListForSign);
					
					
					TabularData headerTableForSign=new TabularData(headerRowListForSign);
					headerTableForSign.addStyle(IReportConstants.FONT_WEIGHT, IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
					headerTableForSign.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
					headerTableForSign.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_SIZE_XXLARGE);
					headerTableForSign.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
					headerTableForSign.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					headerTableForSign.addStyle(IReportConstants.FONT_WEIGHT, IReportConstants.VALUE_FONT_WEIGHT_BOLD);
					headerTableForSign.addStyle(IReportConstants.ALIGN_BASELINE, IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					
					report.setGrandTotalTemplate(headerTableForSign);
					}
					*/
				
		  	
				
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		
		return DataList;
	}
	final String CheckIfNull(Object lStra)
	{
		String lStrb="";
		if( lStra != null )
		{
			lStrb = (((String)lStra).trim());

		}
		return lStrb;
	}
	
	public ReportVO exportReport(ReportVO reportVO, Object arg1, ReportEvent event) {
		
		//ReportColumnVO[] rptCol = reportVO.getReportColumns();  
		//String Print=CheckIfNull(reportVO.getParameterValue( "Print" ));
		
		
		reportVO.setReportName("");
		
		 

		reportVO.setExportFormat(event.BEFORE_PRINT);
		return reportVO;
	}
	
	public ReportVO getUserReportVO( ReportVO report, Object criteria )
	{
		return report;
	}

	
}
