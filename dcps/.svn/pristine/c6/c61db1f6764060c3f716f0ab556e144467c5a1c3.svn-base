package com.tcs.sgv.dcps.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valuebeans.reports.URLData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PayrollReportsDAO;
import com.tcs.sgv.eis.dao.TokenNumberDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class AccidentalInsuranceEmpWiseReport extends DefaultReportDataFinder implements ReportDataFinder {
	ResourceBundle locStrsBundle;
	private static Logger logger = Logger.getLogger(PayrollReportsDAO.class );



	private  StyleVO[] selfCloseVO=null;          

	
	final String CheckIfNull(Object lStra)
	{
		String lStrb="";
		if( lStra != null )
		{
			lStrb = (((String)lStra).trim());

		}
		return lStrb;
	}
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		logger.info("hiiiiiiiiiiiii in empwise report");
		//String langName=report.getLangId();
		int finalpagesize=20;
		//String locId=report.getLocId(); 
		//Statement lStmt = null;

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		String locationNameincaps=locationName.toUpperCase();

		//ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");


		ArrayList DataList = new ArrayList();   

		try   
		{
			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
			SessionFactory sessionFactory = serviceLocator.getSessionFactory();
			Session hibSession = sessionFactory.getCurrentSession();
			ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");

			StyleVO[] boldStyleVO = new StyleVO[1];
			boldStyleVO[0] = new StyleVO();
			boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			//StyledData dataStyle = null;

			StyleVO[] colorStyleVO = new StyleVO[1];
			colorStyleVO[0] = new StyleVO();
			colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			colorStyleVO[0].setStyleValue("blue");
			selfCloseVO = new StyleVO[1];
			selfCloseVO[0] = new StyleVO();
			selfCloseVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
			selfCloseVO[0].setStyleValue("javascript:self.close()"); 

			StyleVO[] leftHeader = new StyleVO[3];
			leftHeader[0] = new StyleVO();
			leftHeader[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			leftHeader[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD); 
			leftHeader[1] = new StyleVO();
			leftHeader[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			leftHeader[1].setStyleValue("11"); 
			leftHeader[2] = new StyleVO();
			leftHeader[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			leftHeader[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);

			StyleVO[] rightHead = new StyleVO[3];
			rightHead[0] = new StyleVO();
			rightHead[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			rightHead[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD); 
			rightHead[1] = new StyleVO();
			rightHead[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			rightHead[1].setStyleValue("11"); 
			rightHead[2] = new StyleVO();
			rightHead[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			rightHead[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);


			StyleVO[] headerStyleVo = new StyleVO[4];
			headerStyleVo[0] = new StyleVO();
			headerStyleVo[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			headerStyleVo[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			headerStyleVo[1] = new StyleVO();
			headerStyleVo[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			headerStyleVo[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			headerStyleVo[2] = new StyleVO();
			headerStyleVo[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			headerStyleVo[2].setStyleValue("14");
			headerStyleVo[3] = new StyleVO();
			headerStyleVo[3].setStyleId(IReportConstants.STYLE_FONT_FAMILY);
			headerStyleVo[3].setStyleValue(IReportConstants.VALUE_FONT_FAMILY_ARIAL);


			if(report.getReportCode().equals("8000201"))
			{
				String BillNo="";	
				String month="";
				String year="";

				BillNo = report	.getParameterValue("BillNo") != null ? report.getParameterValue("BillNo").toString().trim() : "";
				logger.info("BillNo123 123aaaa--"+BillNo);
			
				long locactionId=Long.parseLong(baseLoginMap.get("locationId").toString());
				logger.info("locactionId**********" + locactionId);
				logger.info("BillNo**********" + BillNo);

				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				String gradeName = CheckIfNull(report.getParameterValue( "grade" ));
				logger.info("gradeName " + gradeName);
				
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				cal.set(Calendar.DAY_OF_MONTH, totalDays);
				String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};        
				Integer in = Integer.parseInt(month);

				String Title = "State Government Employees Group Indivdual Accidental Insurance Scheme(8121507501)";		

				String deptHeader ="";

				if( BillNo != null && BillNo != "" )
				{
					deptHeader=Title+System.getProperty("line.separator")+"Statment Showing deduction of GroupWise Employees from paybill";
				}

				 //added by Diamond for renaming Service tax column name to 'Goods and Services Tax (18%)':Start
				String columnName = null;
				int tempMonth = Integer.parseInt(month);
				logger.info("tempMonth  " +tempMonth);
				
				if(tempMonth >=8 && year.equals("2017"))
				{
					logger.info("tempMonth**********" +tempMonth);
					
					ReportColumnVO[] col=report.getColumnsToDisplay();
				
				   for (int counter = 0; counter < col.length; counter++) { 
							 
							 columnName = col[4].getColumnHeader();
							 logger.info("columnName**********" +columnName);
							 col[4].setColumnHeader("Goods and Services Tax (18%)");
						}   	
						
					report.initializeTreeModel();
					report.initializeDynamicTreeModel();
					
				}
				 //added by Diamond for renaming Service tax column name to 'Goods and Services Tax (18%)':End
				
				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();

				StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");			
				
				
				TabularData tData  = new TabularData(styleList);				
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);

				NewRegDdoDAOImpl regDao = new NewRegDdoDAOImpl(null,serv.getSessionFactory()); 
				String billCreationDate =  regDao.getBillCreationDate(BillNo,month,year);
				StringBuilder empCountGroupWiseQuery=new StringBuilder();
				empCountGroupWiseQuery.append(" SELECT emp.SEVARTH_ID,emp.EMP_NAME,emp.DDO_CODE,paybill.ACC_POLICY FROM mst_dcps_emp emp inner join org_emp_mst eis on emp.ORG_EMP_MST_ID = eis.EMP_ID ");
				empCountGroupWiseQuery.append(" inner join HR_EIS_EMP_MST mst on mst.EMP_MPG_ID = eis.EMP_ID   ");
				empCountGroupWiseQuery.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=mst.EMP_ID   ");
				empCountGroupWiseQuery.append("inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
				empCountGroupWiseQuery.append("inner join ORG_GRADE_MST grade on grade.GRADE_ID = eis.GRADE_ID  ");
				empCountGroupWiseQuery.append(" where head.PAYBILL_MONTH = "+month+" and head.PAYBILL_YEAR ="+year+" and head.APPROVE_FLAG in (0,5,1) and BILL_NO = "+BillNo+"  and paybill.ACC_POLICY > 0 and eis.GRADE_ID = "+gradeName+" ");
				
				Query empCountGroupWiseSQLQuery = hibSession.createSQLQuery(empCountGroupWiseQuery.toString());
				logger.info("empCountGroupWiseSQLQuery"+empCountGroupWiseQuery);
				List empCountGroupWiseList=empCountGroupWiseSQLQuery.list();
				logger.info("empCountGroupWiseSQLQuery size"+empCountGroupWiseList.size());
				String  empname= "";
				String  sevaarthId= "";
				String  ddoCode= "";
				long totalAmount = 0l;
			if(empCountGroupWiseList!=null && empCountGroupWiseList.size()>0){
				for(Iterator itr=empCountGroupWiseList.iterator();itr.hasNext();)
				{

					Object[] rowList = (Object[])itr.next();

					if(rowList[0] != null)
					{
						sevaarthId = rowList[0].toString();
					}
					if(rowList[1] != null)
					{
						empname = (rowList[1].toString());
					}
					if(rowList[2] != null)
					{
						ddoCode = (rowList[2].toString());
					}

					if(rowList[3] != null)
					{
						totalAmount = Long.parseLong(rowList[3].toString());
					}
					
					
					ArrayList row = new ArrayList();
					row.add(sevaarthId);
					row.add(empname);
					row.add(ddoCode);
					
					long contriAAmt=750l;
					long contriBAmt=600l;
					long contriCAmt=450l;
					long contriDAmt=450l;
					long serviceRate=18l;
					long serviceTaxA =0l;
					long serviceTaxB =0l;
					long serviceTaxC =0l;
					long serviceTaxD =0l;
					
					
					
					if(totalAmount==345) {
						row.add("300");
						row.add("45");
					} else if(totalAmount==354) {
						row.add("300");
						row.add("54");
				    }else if(totalAmount==344) {
				    	row.add("300");
				    	row.add("44");
				    }else if(totalAmount==885) {
				    	row.add("750");
						row.add("135");
					}else if(totalAmount==708) {
						row.add("600");
						row.add("108");
					}else if(totalAmount==531) {
						row.add("450");
						row.add("81");
					}else {
						row.add("300");
						row.add("44");
					}
					
					row.add(totalAmount);
					DataList.add(row); 
				}
				
			}

			}
			
		}
		catch(Exception e)
		{
			logger.error("Error in ResourceMoniteringDAO" + e.getMessage());
			logger.error("Printing StackTrace");
			logger.error("Error is: "+ e.getMessage());
		}

		return DataList;

	}

}
