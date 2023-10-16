package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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

public class HBACertyDAOImpl extends DefaultReportDataFinder 
implements ReportDataFinder,ReportEventListener 
{

	Log logger = LogFactory.getLog(getClass());
	
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		ArrayList DataList = new ArrayList();
		ArrayList row = new ArrayList();
		
		StyleVO[] URLStyleVO = new StyleVO[1];
		URLStyleVO[0] = new StyleVO();
		URLStyleVO[0].setStyleId(IReportConstants.BORDER);
		URLStyleVO[0].setStyleValue("NO");
		
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
			rightBoldStyleVO[1].setStyleValue("Left"); 
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
				newReportColumns[i].setDataType(10);
			//newReportColumns[i].setAlignment();
				newReportColumns[i].setTableName("a");
				newReportColumns[i].setColumnName("asd"+i);
				newReportColumns[i].setDisplayTotal(0); 
				newReportColumns[i].setColumnLevel(1);	
				newReportColumns[i].setColumnWidth(12);
			 
			//logger.info("in for "+i);
			 
			}
			
			report.setReportColumns(newReportColumns);
			report.initializeDynamicTreeModel();
			report.initializeTreeModel();
			
			row = new ArrayList();
			row.add(new PageBreak());
			row.add("");
			
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
			dataStyle.setData("1.Certified that total Recoveries shown in column <8> above agrees with the amount actually recovered and shown in the body of the bill");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("Dated :"+new Date());
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("&nbsp;");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("&nbsp;");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			ArrayList innerData = new ArrayList();
			ArrayList innerRow = new ArrayList();
			innerRow.add("Portion for Treasury Office");
			innerData.add(innerRow);
			innerRow = new ArrayList();
			innerRow.add("Treasury Voucher No. and Date");
			innerData.add(innerRow);
			innerRow = new ArrayList();
			innerRow.add("Challan no. and Date");
			innerData.add(innerRow);
			TabularData td = new TabularData(innerData);
			td.setStyles(leftBoldStyleVO);
			row.add(td);
			
			
			
			
			innerData = new ArrayList();
			innerRow = new ArrayList();
			innerRow.add("Treasury Officer");
			innerData.add(innerRow);
			innerRow = new ArrayList();
			innerRow.add("District Treasury Office, Pune");
			innerData.add(innerRow);
			 
			 td = new TabularData(innerData);
			td.setStyles(rightBoldStyleVO);
			row.add(td);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("&nbsp;");
			dataStyle.setColspan(2);
			row.add(dataStyle);
			DataList.add(row);
			
			row = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(leftBoldStyleVO);
			dataStyle.setData("&nbsp;");
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
