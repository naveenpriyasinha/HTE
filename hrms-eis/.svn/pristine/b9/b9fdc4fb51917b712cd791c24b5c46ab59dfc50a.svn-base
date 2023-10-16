package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.event.reports.ReportEvent;
import com.tcs.sgv.common.event.reports.ReportEventListener;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class HBACALoanDAOSubReportDAOImpl  extends DefaultReportDataFinder 
implements ReportDataFinder,ReportEventListener 
{

	Log logger = LogFactory.getLog(getClass());
	
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		ArrayList DataList = new ArrayList();
		ArrayList row = new ArrayList();
		
		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		ServiceLocator serv = (ServiceLocator) requestKeys.get("serviceLocator");
		String deptName="";
		PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
	    deptName=payBillDAOImpl.getOffice(locationId);
	    long loggedInpostId = Long.parseLong(baseLoginMap.get("loggedInPost").toString());
		String dsgnName =payBillDAOImpl.getDsgnNameForFormB(locationId,loggedInpostId);
	    
		StyleVO[] URLStyleVO = new StyleVO[3];
		URLStyleVO[0] = new StyleVO();
		URLStyleVO[0].setStyleId(IReportConstants.BORDER);
		URLStyleVO[0].setStyleValue("NO");
		URLStyleVO[1] = new StyleVO();
		URLStyleVO[1].setStyleId(IReportConstants.SHOW_REPORT_NAME);
		URLStyleVO[1].setStyleValue("NO");
		URLStyleVO[2] = new StyleVO();
		URLStyleVO[2].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
		URLStyleVO[2].setStyleValue("No");
		
		report.setStyleList(URLStyleVO);
		
			StyleVO[] centerboldStyleVO = new StyleVO[3];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Center"); 
			centerboldStyleVO[2] = new StyleVO();
			centerboldStyleVO[2].setStyleId(IReportConstants.BORDER);
			centerboldStyleVO[2].setStyleValue("NO");
			
			
			StyleVO[] leftBoldStyleVO = new StyleVO[3];
			leftBoldStyleVO[0] = new StyleVO();
			leftBoldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			leftBoldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			leftBoldStyleVO[1] = new StyleVO();
			leftBoldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			leftBoldStyleVO[1].setStyleValue("Left"); 
			leftBoldStyleVO[2] = new StyleVO();
			leftBoldStyleVO[2].setStyleId(IReportConstants.BORDER);
			leftBoldStyleVO[2].setStyleValue("NO");
			
			StyleVO[] rightBoldStyleVO = new StyleVO[3];
			rightBoldStyleVO[0] = new StyleVO();
			rightBoldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			rightBoldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			rightBoldStyleVO[1] = new StyleVO();
			rightBoldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			rightBoldStyleVO[1].setStyleValue("Right"); 
			rightBoldStyleVO[2] = new StyleVO();
			rightBoldStyleVO[2].setStyleId(IReportConstants.BORDER);
			rightBoldStyleVO[2].setStyleValue("NO");
			
			ReportColumnVO[] newReportColumns = new ReportColumnVO[2];
			for(int i=0;i<2;i++)
			{						  
			//initialize column vo
				newReportColumns[i] = new ReportColumnVO();

			//configure column vo by setting different column properties
				newReportColumns[i].setColumnId(i+1); 
				newReportColumns[i].setColumnHeader("");
				newReportColumns[i].setDataType(20);
			//newReportColumns[i].setAlignment();
				newReportColumns[i].setTableName("a");
				newReportColumns[i].setColumnName("asd"+i);
				newReportColumns[i].setDisplayTotal(0); 
				newReportColumns[i].setColumnLevel(1);	
				newReportColumns[i].setColumnWidth(20);
			 
			//logger.info("in for "+i);
			 
			}
			
			
			Calendar cal = new GregorianCalendar();
			  int month = cal.get(Calendar.MONTH);
			  int year = cal.get(Calendar.YEAR);
			  int day = cal.get(Calendar.DAY_OF_MONTH);
			  int hour = cal.get(Calendar.HOUR);
			  int minute = cal.get(Calendar.MINUTE);
			  int second = cal.get(Calendar.SECOND);
			  int millysecond = cal.get(Calendar.MILLISECOND);
			  
			  String dated=day + "/" + (month + 1) + "/" + year;
			  String date=year + "-" + (month + 1) + "-" + day + " " + hour +":" +minute+":"+second+"."+millysecond;
			  
			  
			  
			report.setReportColumns(newReportColumns);
			report.initializeDynamicTreeModel();
			report.initializeTreeModel();
			
			row = new ArrayList();
			row.add(new PageBreak());
			//row.add("");
			
			row = new ArrayList();
			StyledData dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("CERTIFICATE");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("1.Certified that total Recoveries shown in column (8) above agree with the amount actually recovered and shown in the body of the bill.");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("2.Certified that recoveries effected have been duly posted in Register of Advance (FORM A).");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("\n");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("\n");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("Dated :"+dated);
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("\n");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("\n");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(rightBoldStyleVO);
			dataStyle.setData(dsgnName);
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(rightBoldStyleVO);
			dataStyle.setData(deptName);
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("\n");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("Portion for Treasury Office");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("Treasury Voucher No. and Date");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("Challan no. and Date");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("\n");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("\n");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(rightBoldStyleVO);
			dataStyle.setData("Treasury Officer / Pay and Accounts Officer");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			  Date now = new Date();
			  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
			  logger.info("10. "+format.format(now));
			  
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("VERIFICATION TIME :" +format.format(now));
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			return DataList;
		
	}
	
	
	
	public ReportVO exportReport(ReportVO reportVO, Object arg1, ReportEvent event) {
		
		//ReportColumnVO[] rptCol = reportVO.getReportColumns();  
		//String Print=CheckIfNull(reportVO.getParameterValue( "Print" ));
		
		//reportVO.setParameterValue("Department", locId);
		reportVO.setReportName("");
		
		 

		reportVO.setExportFormat(event.BEFORE_PRINT);
		return reportVO;
	}
	 
}
