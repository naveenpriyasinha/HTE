package com.tcs.sgv.eis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.util.DBConnection;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class HrrReportDAO extends DefaultReportDataFinder 
implements ReportDataFinder{



	ResourceBundle locStrsBundle;
	private static Logger logger = Logger.getLogger(PayrollReportsDAO.class );


	int pageNo=1;
	private  StyleVO[] selfCloseVO=null;          
	private ResultSet lRs1 = null; 

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
		String langName=report.getLangId();
		int finalpagesize=20;
		String locId=report.getLocId(); 
		Connection lCon = null;
		Statement lStmt = null;

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		String locationNameincaps=locationName.toUpperCase();

		String upperfooter="UNDER SECRETARY TO GOVT.";
		String lowerfooter="MUMBAI"+System.getProperty("line.separator")+"Code No. PG3";
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
		long multipleMonthSupplBill=Integer.parseInt(resourceBundle.getString("multipleMonthSupplBill"));
		ArrayList DataList = new ArrayList(  );            
		StyleVO[] boldStyleVO = new StyleVO[1];
		boldStyleVO[0] = new StyleVO();
		boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
		/*boldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		boldStyleVO[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT)*/

		StyleVO[] pageNoStyleVO = new StyleVO[2];
		pageNoStyleVO[0] = new StyleVO();
		pageNoStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		pageNoStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
		pageNoStyleVO[1] = new StyleVO();
		pageNoStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		pageNoStyleVO[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);

		StyledData dataStyle = null;
		StyleVO[] colorStyleVO = new StyleVO[1];
		colorStyleVO[0] = new StyleVO();
		colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
		colorStyleVO[0].setStyleValue("blue");
		selfCloseVO = new StyleVO[1];
		selfCloseVO[0] = new StyleVO();
		selfCloseVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
		selfCloseVO[0].setStyleValue("javascript:self.close()"); 

		//Added by rahul for header style vo Date: 07-Oct-08
		StyleVO[] headerStyleVo = new StyleVO[4];
		headerStyleVo[0] = new StyleVO();
		headerStyleVo[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		headerStyleVo[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
		headerStyleVo[1] = new StyleVO();
		headerStyleVo[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		headerStyleVo[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
		headerStyleVo[2] = new StyleVO();
		headerStyleVo[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		headerStyleVo[2].setStyleValue("18");
		headerStyleVo[3] = new StyleVO();
		headerStyleVo[3].setStyleId(IReportConstants.STYLE_FONT_FAMILY);
		headerStyleVo[3].setStyleValue(IReportConstants.VALUE_FONT_FAMILY_ARIAL);

		//Ended by rahul

		//Added by Urvin for setting up font size
		StyleVO[] baseFont = new StyleVO[1];
		baseFont[0] = new StyleVO(  );
		baseFont[0].setStyleId( IReportConstants.STYLE_FONT_SIZE );
		baseFont[0].setStyleValue( "13" );

		StyleVO[] centerboldStyleVO1 = new StyleVO[3];
		centerboldStyleVO1[0] = new StyleVO();
		centerboldStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		centerboldStyleVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
		centerboldStyleVO1[1] = new StyleVO();
		centerboldStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		centerboldStyleVO1[1].setStyleValue("Center"); 
		centerboldStyleVO1[2] = new StyleVO();
		centerboldStyleVO1[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		centerboldStyleVO1[2].setStyleValue("14"); 

		/*baseFont[1] = new StyleVO(  );
		        baseFont[1].setStyleId( IReportConstants.STYLE_FONT_COLOR );
		        baseFont[1].setStyleValue( IReportConstants.VALUE_FONT_COLOR_DARKGRAY );*/
		//report.addReportStyles(baseFont);
		ReportTemplate rt = new ReportTemplate();
		rt.put(IReportConstants.TMPLT_COLUMN_HEADER,baseFont);
		rt.put(IReportConstants.TMPLT_BASE_FONT,baseFont);
		report.setReportTemplate( rt );
		//Added by Ravish for setting up font size

		//added by Samir Joshi fro each page Header
		ReportAttributeVO ravo1 = new ReportAttributeVO();
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_LOCATION);
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_ON_EACH_PAGE);
		ravo1.setAlignment(IReportConstants.HEADER_ALIGN_CENTER);
		report.addReportAttributeItem(ravo1);
		report.setAdditionalHeader("");
		//ended by samir joshi
		// Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serv1 = (ServiceLocator) requestKeys.get("serviceLocator");

		PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv1.getSessionFactory());
		boolean isBillDefined = paybillDAO.isBillsDefined(locationId);


		//		Added by Mrugesh/Samir

		StyleVO[] styleVOPgBrk=null;
		styleVOPgBrk = new StyleVO[2];



		styleVOPgBrk[0] = new StyleVO();
		styleVOPgBrk[0].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
		styleVOPgBrk[0].setStyleValue("yes");
		styleVOPgBrk[0] = new StyleVO();


		styleVOPgBrk[1] = new StyleVO();
		styleVOPgBrk[1].setStyleId(IReportConstants.SHOW_REPORT_WHEN_NO_DATA);
		styleVOPgBrk[1].setStyleValue("no");

		report.addReportStyles(styleVOPgBrk);


		//Ended
		try   
		{
			lCon = DBConnection.getConnection(  );
			lStmt = lCon.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			logger.info("getReportCode"+report.getReportCode());
			dataStyle = new StyledData();
			String portType="";
			String BillNo="";	
			String desigName="";
			String deptName="";
			String cityName="";
			String cardexCode="";
			OrgDdoMst orgDdo = new OrgDdoMst();
			ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");	
			PayBillDAO billDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			Object[] objArry = null;
			List lstSignInfo = new ArrayList();
			lstSignInfo = billDAO.getReportSignature(locationId);

			if(lstSignInfo.get(0)!=null)
			{

				objArry =(Object[]) lstSignInfo.get(0);
				desigName=objArry[0].toString();
				deptName=objArry[1].toString();
				cardexCode=objArry[2].toString();
				cityName=objArry[3].toString();            		
			}
			if(report.getReportCode().equals("10") || report.getReportCode().equals("2500010"))
			{
				//				Added by Akshay
				String fname = CheckIfNull(report.getParameterValue("FName"));
				String lname = CheckIfNull(report.getParameterValue("LName"));
				//				Ended by Akshay


				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				//				logger.info(" nof rec out====>"+noOfRec);
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					//logger.info("No Of Rec is********====>"+noOfRec);
					finalpagesize=Integer.parseInt(noOfRec);
				}

				//				hardcoded
				long AISGradeCode=Long.parseLong(constantsBundle.getString("AISGradeCode"));

				StringBuffer lsb = new StringBuffer(  );      

				String empid=CheckIfNull(report.getParameterValue( "employeeName" ));

				//cityId
				String City=CheckIfNull(report.getParameterValue( "City" ));

				logger.info("city is: " +City);
				//city name selected in report para
				String citySelectedInReport = "";
				if(!City.equals("-1") && City!= null & !City.equals("")){
					//fetch the city name
					citySelectedInReport = billDAO.getCityName(City);
				}

				String Department="";
				String Grade="";
				String Scale="";
				String Designation="";
				String month="";
				String year="";
				String GroupBy="";

				logger.info("HrrReportDAO report.getParameterValue(City)....... City is: "+City);
				logger.info("city name is:  "+citySelectedInReport);

				GroupBy=CheckIfNull(report.getParameterValue( "Group By" ));
				Department=CheckIfNull(report.getParameterValue( "Department" ));
				//				added by samir joshi for bill no wise report

				if(Department.equals("")||Department.equals("-1"))
					Department=	locationId+"";	
				else
					locationId=Long.parseLong(Department);

				logger.info("locationId: " +locationId);
				lstSignInfo = billDAO.getReportSignature(locationId);
				if(lstSignInfo.get(0)!=null)
				{

					objArry =(Object[]) lstSignInfo.get(0);
					desigName=objArry[0].toString();
					deptName=objArry[1].toString();
					cardexCode=objArry[2].toString();
					cityName=objArry[3].toString();            		
				}



				String BillNoinner="";//GAD specific
				BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
				StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
				int l=0;
				String subheadCode="";
				String classIds="";
				String desgnIds="";
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
					l++;
				} 

				//				ended by samir joshi/////
				//				for report footer
				ReportAttributeVO ravo = new ReportAttributeVO();

				ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
				ravo.setLocation(IReportConstants.LOCATION_FOOTER);
				ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
				String DeptName=locationNameincaps;



				ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

				//				add the attribute to the report instance
				//				report.addReportAttributeItem(ravo);
				ArrayList tblData = new ArrayList();



				ArrayList trow1 = new ArrayList();

				trow1.add(" "+System.getProperty("line.separator"));				
				tblData.add(trow1);//added first row of the tabular data


				ArrayList trow2 = new ArrayList();

				trow2.add(" "+System.getProperty("line.separator"));				
				tblData.add(trow2);//added second row of the tabular data


				ArrayList trow4 = new ArrayList();

				for(int c=0;c<(30);c++)
					trow4.add(new StyledData("&nbsp;",centerboldStyleVO1));
				trow4.add(new StyledData(desigName,centerboldStyleVO1));				
				tblData.add(trow4);//added second row of the tabular data

				ArrayList trow5 = new ArrayList();

				for(int c=0;c<(30);c++)
					trow5.add(new StyledData("&nbsp;",centerboldStyleVO1));
				trow5.add(new StyledData(deptName,centerboldStyleVO1));

				tblData.add(trow5);//added second row of the tabular data

				/*ArrayList trow3 = new ArrayList();

				for(int c=0;c<(30);c++)
					trow3.add(new StyledData("&nbsp;",centerboldStyleVO1));							
				trow3.add(new StyledData(cityName,centerboldStyleVO1));				
				tblData.add(trow3);*///added third row of the tabular data

				ArrayList trow6 = new ArrayList();
				/*trow6.add(" ");
				trow6.add(" ");
				trow6.add(new StyledData(cardexCode,centerboldStyleVO1));

				tblData.add(trow6);*///added sixth row of the tabular data

				for(int c=0;c<(30);c++)
					trow6.add(new StyledData("&nbsp;",centerboldStyleVO1));		
				trow6.add(new StyledData(cardexCode,centerboldStyleVO1));
				tblData.add(trow6);
				//-----------------------------------------------------------//
				ArrayList tb2Data = new ArrayList();
				ArrayList trowd1 = new ArrayList();

				trowd1.add(" ");
				trowd1.add(" ");
				trowd1.add(" "+System.getProperty("line.separator"));
				tb2Data.add(trowd1);//added first row of the tabular data


				ArrayList trowd2 = new ArrayList();

				trowd2.add(" ");
				trowd2.add(" ");
				trowd2.add(" "+System.getProperty("line.separator"));
				tb2Data.add(trowd2);//added second row of the tabular data


				ArrayList trowd4 = new ArrayList();

				trowd4.add(" ");
				trowd4.add(" ");
				trowd4.add(new StyledData(desigName,centerboldStyleVO1));
				tb2Data.add(trowd4);//added second row of the tabular data


				ArrayList trowd5 = new ArrayList();

				trowd5.add(" ");
				trowd5.add(" ");
				trowd5.add(new StyledData(deptName,centerboldStyleVO1));

				tb2Data.add(trowd5);//added second row of the tabular data

				ArrayList trowd6 = new ArrayList();

				/*trowd6.add(" ");
				trowd6.add(" ");
				trowd6.add(new StyledData(cityName,centerboldStyleVO1));

				tb2Data.add(trowd6);*///added third row of the tabular data

				ArrayList trowd7 = new ArrayList();

				trowd7.add(" ");
				trowd7.add(" ");
				trowd7.add(new StyledData(cardexCode,centerboldStyleVO1));

				tb2Data.add(trowd7);
				//initialize tabular data
				TabularData tabularDatareport= new TabularData(tb2Data);
				TabularData tabularData = new TabularData(tblData);	
				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
				tabularData.addStyle(IReportConstants.BORDER, "NO"); 


				tabularDatareport.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
				tabularDatareport.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
				tabularDatareport.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
				tabularDatareport.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
				tabularDatareport.addStyle(IReportConstants.BORDER, "NO"); 

				report.setGrandTotalTemplate(tabularDatareport);
				//report.setGroupByTotalTemplate(tabularData);


				Grade=CheckIfNull(report.getParameterValue( "Grade" ));
				Scale=CheckIfNull(report.getParameterValue( "Scale" ));
				Designation=CheckIfNull(report.getParameterValue( "Designation" ));
				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				String Quarter=CheckIfNull(report.getParameterValue( "Quarter" ));

				//added by ravysh
				String billType=CheckIfNull(report.getParameterValue( "billTypePara" ));
				//if(billType.equals("") || billType.equals(" ") || billType.equals("-1"))
				//	billType = resourceBundle.getString("paybillTypeId");

				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");

				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				java.util.Date startMonthDate = cal.getTime();
				String startDate  = sdfObj.format(startMonthDate);	

				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

				cal.set(Calendar.DAY_OF_MONTH, totalDays);

				java.util.Date endMonthDate = cal.getTime();

				String endDate  = sdfObj.format(endMonthDate);

				boolean flag=true;
				Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);

				Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
				ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");

				SessionFactory sessionFactory = serviceLocator.getSessionFactory();
				Session session= sessionFactory.getCurrentSession();	           

				String demandNo = "";//CheckIfNull(report.getParameterValue("demandNo")); 
				//				(String)requestAttributes.get("demandNo");
				String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
				String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
				String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
				String subHead = CheckIfNull(report.getParameterValue("subHead"));
				//				String dtlHead = CheckIfNull(report.getParameterValue("dtlHead"));
				String subheadflag=subHead;
				logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
				SimpleDateFormat sdfObj1 = new SimpleDateFormat("MMM");
				String Month = sdfObj1.format(startMonthDate);
				//				Added by Rajan checking bill no is available or not !
				//				Added by Maruthi For back buton issue.
				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();

				// Added by Hemant for Suppilementry Bill fro displaying Remarks so added  	 
				ReportColumnVO[] rpt_Col = report.getColumnsToDisplay();
				logger.info(">>>>>>>>>>>>>>>>>>  rpt_Col.length = " + rpt_Col.length);
				logger.info(">>>>>>>>>>>>>>>>>>  billType       = " + billType);
				logger.info(">>>>>>>>>>>>>>>>>>  report column  = " + rpt_Col.toString());
				logger.info(">>>>>>>>>>>>>>>>>>  billType = " + billType + "::::::::::: multipleMonthSupplBill = " + multipleMonthSupplBill);
				if(!billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
				{
					rpt_Col[rpt_Col.length-1].setHidden("n");
				}
				else
					rpt_Col[rpt_Col.length-1].setHidden("y");

				if( BillNo != null && BillNo != "" )
				{
					//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of "+ citySelectedInReport +" HOUSE RENT Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
					stData.add(new StyledData (deptHeader,headerStyleVo));
					styleList.add(stData);
				}
				else
				{
					//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
					String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of "+ citySelectedInReport +" HOUSE RENT Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
					stData.add(new StyledData (deptHeader,headerStyleVo));		    				 
					styleList.add(stData);

				}	
				TabularData tData  = new TabularData(styleList);
				//				tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
				//				tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
				//				tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGE);
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "NO");
				//				tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);

				//				Ended By Maruthi.
				//				if(!BillNo.equals(""))
				//				report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of GANDHINAGAR HOUSE RENT Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
				//				else
				//				report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of GANDHINAGAR HOUSE RENT Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
				//				Ended by Rajan
				//				by manoj for head change
				cal = Calendar.getInstance();
				sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				java.util.Date finYrDate = cal.getTime();
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
				endMonthDate = cal.getTime();

				endDate  = sdfObj.format(endMonthDate);	            

				int currentMonthBill = 0;
				Date currentDate = new Date();

				if(currentDate.after(finYrDate) && currentDate.before(endMonthDate))
					currentMonthBill = 1;
				logger.info("testing for currentMonth "+currentMonthBill+" currentDate "+currentDate+" finYrDate "+finYrDate+" endMonthDate "+endMonthDate);

				//				end by manoj for head change
				String subHeadId = "";
				if(subHead!=null && !subHead.equals("")&& !subHead.equals("-1"))
				{

					StringTokenizer st=new StringTokenizer(subHead,"~");
					int i=0;
					while(st.hasMoreTokens())
					{
						if(i==0)
							demandNo=st.nextToken();
						else if(i==1)
							mjrHead=st.nextToken();
						else if(i==2)
							subMjrHead=st.nextToken();
						else if(i==3)
							mnrHead=st.nextToken();
						else
							subHead=st.nextToken();	
						i++;
					} 
					if(subheadflag.indexOf("~")<=0)
					{
						demandNo = CheckIfNull(report.getParameterValue("demandNo")); 
						mjrHead = CheckIfNull(report.getParameterValue("mjrHead"));
						subMjrHead =  CheckIfNull(report.getParameterValue("subMjrHead"));
						mnrHead = CheckIfNull(report.getParameterValue("mnrHead"));
						logger.info("  sdfhdfgthfr Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
					}
					logger.info("        sdgdfhgfHead Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
					GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(SgvaBudsubhdMst.class);

					//ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
					genDAO.setSessionFactory(serv.getSessionFactory());
					CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
					CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.getLangVOByShortName(langName);  
					long langId = cmnLanguageMst.getLangId();
					PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
					SgvcFinYearMst finYrMst = payDAO.getFinYrInfo(startMonthDate, langId);

					String cols[] = {"demandCode","budmjrhdCode","budsubmjrhdCode","budminhdCode","budsubhdCode","langId","finYrId"};//,"finYrId"
					Object vals[] = {demandNo,mjrHead,subMjrHead,mnrHead,subHead,langName,finYrMst.getFinYearId()};//,String.valueOf(finYrMst.getFinYearId())
					List<SgvaBudsubhdMst> subHeadList = genDAO.getListByColumnAndValue(cols, vals);
					month=CheckIfNull(report.getParameterValue( "Month" ));
					year=CheckIfNull(report.getParameterValue( "Year" ));

					logger.info("*******************finYr*************"+finYrMst.getFinYearId());
					SgvaBudsubhdMst subHeadObj = subHeadList.get(0);
					subHeadId = String.valueOf(subHeadObj.getBudsubhdId()); 
					logger.info("Paybill Subhead ID from following details is " + subHeadId + " from size " + subHeadList.size());
				}


				Session hibSession = sessionFactory.getCurrentSession();
				String query = " select dtl.hrEisEmpMst.empId ,";
				query+=" qtrdtls.cmnAddressMst.cmnCityMst.cityId as cityid ,";
				//query+=" pst.orgDesignationMst.dsgnShrtName ,";
				if(!billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
					query+= " case when dsgnMst.dsgnShrtName is null then pst.orgDesignationMst.dsgnShrtName else dsgnMst.dsgnShrtName end , ";
				else
					query+= " case when max(dsgnMst.dsgnShrtName) is null then pst.orgDesignationMst.dsgnShrtName else max(dsgnMst.dsgnShrtName) end , ";

				query+=" pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName , ";
				query+="dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";

				query+=" qtrdtls.hrQuaterTypeMst.quaType,";
				query+=" scale.scaleStartAmt || '-' || scale.scaleIncrAmt || '-' ||scale.scaleEndAmt|| '-' ||scale.scaleHigherIncrAmt || '-' ||scale.scaleHigherEndAmt,";
				if(!billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
				{
					query+=" pay.deduc9550 ,  ";// rentB
					query+=" pay.deduc9560 ,  ";//hrr	            
				}
				else
				{
					query+=" sum(pay.deduc9550) , ";// rentB
					query+=" sum(pay.deduc9560) ,  ";//hrr
				}

				query+=" (select cmnAddress.street from CmnAddressMst cmnAddress where  cmnAddress.addressId=qtrdtls.cmnAddressMst.addressId ) ,";
				query+="  (select cmnAddress.area from CmnAddressMst cmnAddress where cmnAddress.addressId=qtrdtls.cmnAddressMst.addressId ) ";

				if(isBillDefined)//this will always be at last so that no need to change the sequence if new column comes
				{
					query+="  ,pay.psrNo ";
				}
				if(!billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
				{
					query+= " , pay.month , pay.year ";
				}
				query+=" from HrPayPaybill pay, ";

				query+=" HrEisQtrMst qtrdtls, ";
				if(isBillDefined)
				{
					//query+="  HrPayPsrPostMpg psrmpg, ";   commented by Urvin
				}
				query+=" HrEisOtherDtlsHst dtl";
				query+= " left outer join dtl.hrEisSgdMpg as sgd ";
				query += " left outer join sgd.hrEisGdMpg as gd ";
				query += "left outer join sgd.hrEisScaleMst scale ";
				query +=" left outer join  gd.orgDesignationMst as dsgnMst , ";

				query+=" OrgUserpostRlt           USRPST, ";

				query+=" HrPayOrderHeadMpg ORDHD, ";
				query+=" HrPayOrderHeadPostMpg ORDPST, ";

				query+=" OrgPostDetailsRlt pst, ";
				//query+=" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm ,HrPayOrderSubHeadMpg hposm";
				query+=" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm ";

				query+=" where  ";
				query+=" dtl.id.trnCounter = pay.otherTrnCntr and ";

				/*
				if(isBillDefined)
				{
					query+="  pay.orgPostMst.postId=pay.orgPostMst.postId and ";		// Updated by Urvin
				}*/

				//query += "  ORDHD.subheadId in (select distinct hposm.element_code from HrPayOrderSubHeadMpg hposm,PaybillHeadMpg bhm ,HrPayPaybill pay where bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId and pay.paybillGrpId = bhm.hrPayPaybill )";


				query += "  ORDPST.orderHeadId = ORDHD.orderHeadId ";


				query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
				query += " and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
				query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
				query+=" and (USRPST.endDate is null or ";
				query+="  (USRPST.endDate>='"+startDate+"' and USRPST.startDate<='"+endDate+"'    ))";
				query+="  and bhm.approveFlag in (0,1) ";


				query+=" and pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId and (pay.deduc9550>0 or pay.deduc9560>0) ";
				query+=" and qtrdtls.orgUserMstByAllocatedTo = dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId and ((qtrdtls.allocationEndDate >='"+startDate+"' and qtrdtls.allocationEndDate <='"+endDate+"') or qtrdtls.allocationEndDate is null)  ";

				if(!empid.equals("")&&!empid.equals("-1"))            	
					query+=" and dtl.hrEisEmpMst.empId = '"+empid+"'";

				//				Added by Akshay 
				if(!lname.equals("") && !lname.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"+lname.toLowerCase()+"%'");
				}

				if(!fname.equals("") && !fname.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"+fname.toLowerCase()+"%'");
				}
				//				Ended by Akshay 

				if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
				{
					query+=("  and ORDHD.subheadId  = '"+subHeadId+"'");
				}

				if(!Department.equals("")&&!Department.equals("-1"))
					query+=" and pst.cmnLocationMst.locId="+Department+"   ";
				query+=" and pst.orgPostMst.postId = pay.orgPostMst.postId  ";
				if(isBillDefined&&!BillNo.equals(""))
				{
					// updated by Urvin shah
					//query+=" and   pay.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId ) and " ;	
					// end.
					query+=" and hpbsm.billId="+BillNo+"  ";
				}
				else
				{
					if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
					{
						query+="  and  ORDHD.subheadId ="+subheadCode+" ";
					}
					if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
					{
						query+="  and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")";
					}
					if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
					{
						query+="  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ";
					}   
					if(portType != null && !portType.trim().equals("") && !portType.equals("-1"))
					{
						query+="  and pst.orgPostMst.postTypeLookupId in (" +portType+ ") ";
					}
				}
				if(!Grade.equals("")&&!Grade.equals("-1"))            	
					query+=" and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId    = '"+Grade+"'";

				if(!Scale.equals("")&&!Scale.equals("-1"))            	
					query+=" and dtl.hrEisSgdMpg.hrEisScaleMst.scaleId = '"+Scale+"'";

				if(!Designation.equals("")&&!Designation.equals("-1"))            	
					query+=" and pst.orgDesignationMst.dsgnId = '"+Designation+"'";

				if(!month.equals("")&&!month.equals("-1"))            	
					query+=" and bhm.month='"+month+"'";

				if(!year.equals("")&&!year.equals("-1"))            	
					query+=" and bhm.year= '"+year+"'";

				if(!Quarter.equals("")&&!Quarter.equals("-1"))            	
					query+=" and qtrdtls.hrQuaterTypeMst.quaId= '"+Quarter+"'";//HrQuaterTypeMst


				logger.info("checking city.. city is: " +City);
				if(!City.equals("") && !City.equals("-1")){
					logger.info("city wise report..");
					query+=" and qtrdtls.cmnAddressMst.cmnCityMst.cityId= '"+City+"'";
				}

				//if(currentMonthBill!=1)
				//query+="  and bhm.hrPayPaybill = pay.paybillGrpId and ORDHD.subheadId = hposm.element_code  and hpbsm.billHeadId = bhm.billNo.billHeadId and bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId ";            
				query+="  and bhm.hrPayPaybill = pay.paybillGrpId   and hpbsm.billHeadId = bhm.billNo.billHeadId  ";            
				query+= " and bhm.orgGradeMst.gradeId=pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId "; //Date 5-Nov-08 by rahul modified by ravysh
				if(!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
					query+="  and bhm.billTypeId.lookupId="+Long.parseLong(billType);	
				if(!billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
				{
					query+= " order by dtl.hrEisEmpMst.empId , pay.month, pay.year ";
				}
				else
				{
					query+=" group by  ";
					if(isBillDefined)
					{
						query+="  pay.psrNo, ";
					}
					query+=" dtl.hrEisEmpMst.empId,dtl.hrQuaterTypeMst.quaId,  ";
					query+="  pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,pst.orgDesignationMst.dsgnId,  ";
					query+="  pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName,  ";
					query+=" pst.orgDesignationMst.dsgnShrtName,  ";
					query+="qtrdtls.hrQuaterTypeMst.quaType, ";

					query+="dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
					query+=" dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
					query+=" dtl.hrEisEmpMst.orgEmpMst.empLname,  ";
					query+=" scale.scaleStartAmt,  ";
					query+=" scale.scaleIncrAmt, scale.scaleHigherIncrAmt, scale.scaleHigherEndAmt,  ";
					query+=" scale.scaleEndAmt,qtrdtls.cmnAddressMst.addressId,  ";
					query+=" qtrdtls.cmnAddressMst.cmnCityMst.cityId";
					if(isBillDefined)
					{
						//query+="  order by  psrmpg.psrId,qtrdtls.cmnAddressMst.cmnCityMst.cityId ";
						query+="  order by  qtrdtls.cmnAddressMst.cmnCityMst.cityId,pay.psrNo ";
					}
					else
						query+=" order by pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.empId,qtrdtls.cmnAddressMst.cmnCityMst.cityId ";

				}
				logger.info("***Query for Rent Deduction details**" +query);
				logger.info("the value of the query is ::---->"+query.toString());

				Query sqlQuery = hibSession.createQuery(query);
				//				Added by Rajan for check no of records in query
				if(sqlQuery.list().size()!=0)
				{
					ArrayList dataList=new ArrayList();

					List RowList=sqlQuery.list();
					logger.info("*************************"+RowList.size());
					int cnt=1;
					long empId=0;
					long total=0;
					String prevCityId="";
					int grpbyCountno=0;
					int grpByCityId=1;
					String cityId="";
					Iterator itr = RowList.iterator();  
					ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
					int colspan=rptCol2.length;
					ArrayList pageRecordRow = new ArrayList();
					ArrayList page1RecordRow = new ArrayList();
					int prevCounter,nextCounter;
					prevCounter=0;
					ArrayList indexArray = new ArrayList();
					while (itr.hasNext())
					{
						Object[] rowList = (Object[]) itr.next();
						long EmpId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());
						cityId=(String)(rowList[1]!=null?rowList[1].toString():"").toString();
						logger.info("the value of the city id is ::"+cityId);
						//Long.parseLong((rowList[1]!=null?rowList[1].toString():"").toString());
						String designation = (String)(rowList[2]!=null?rowList[2]:"");
						long gradeId = Long.parseLong((rowList[3]!=null?rowList[3].toString():"").toString());
						String Class = (String)(rowList[4]!=null?rowList[4]:"");
						String Name = (String)(rowList[5]!=null?rowList[5]:"");
						String QuarterType = (rowList[6]!=null?rowList[6]:"").toString();		            
						String  scale = (rowList[7]!=null?rowList[7]:"").toString();		            
						double rentB = Math.round(Double.parseDouble((rowList[8]!=null?rowList[8]:"").toString()));		            
						double hrr = Math.round(Double.parseDouble((rowList[9]!=null?rowList[9]:"").toString()));		            
						String sector = (rowList[11]!=null?rowList[11]:"").toString();		            
						String block = (rowList[10]!=null?rowList[10]:"").toString();		            
						//total=Math.round(rentB)+Math.round(hrr);
						ArrayList row = new ArrayList();


						/*if( cnt%20==0)
						 * 
	            			{
	            				row.add(new PageBreak());
	            				row.add("Data");
	            			}*/
						//else
						//TabularData tData  = new TabularData(styleList);
						String bill_Month="";
						String bill_Year="";	
						String RecoveryMonthYear="";

						if(!billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
						{
							bill_Month=(rowList[rowList.length-2]!=null?rowList[rowList.length-2]:"").toString();;
							bill_Year=(rowList[rowList.length-1]!=null?rowList[rowList.length-1]:"").toString();;
							RecoveryMonthYear = Float.valueOf(bill_Month).intValue()+"/"+ Float.valueOf(bill_Year).intValue();
							
							//RecoveryMonthYear = ( rowList[rowList.length-2] != null && !rowList[rowList.length-2].equals("") ? (Integer.parseInt(rowList[rowList.length-2].toString())): "") + "/" +  ( rowList[rowList.length-1] != null && !rowList[rowList.length-1].equals("") ? Integer.parseInt(rowList[rowList.length-1].toString()): "");
						}

						if(grpbyCountno==finalpagesize || grpbyCountno%finalpagesize==0)
							pageNo++;
						if(!prevCityId.equals(cityId)&&cnt>1)
						{	
							if(grpbyCountno<finalpagesize)
							{

								ArrayList	 row1 = new ArrayList();
								row1.add("");
								row1.add("");
								row1.add("");
								row1.add("");
								row1.add("");
								StyledData dataStyle3 = new StyledData();
								dataStyle3.setStyles(boldStyleVO);
								dataStyle3.setData("TOTAL");                  
								row1.add(dataStyle3);


								//  row.add("Total");
								dataStyle3 = new StyledData();
								dataStyle3.setStyles(boldStyleVO);
								dataStyle3.setData(total);
								row1.add(dataStyle3);
								row1.add(""); // added for remarks column on report id 300010
								row1.add(grpByCityId)	;
								DataList.add(row1);
								ArrayList row2 = new ArrayList();

								StyleVO[] centerboldStyleVO = new StyleVO[2];
								centerboldStyleVO[0] = new StyleVO();
								centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
								centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
								centerboldStyleVO[1] = new StyleVO();
								centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
								centerboldStyleVO[1].setStyleValue("Left"); 
								StyledData dataStyle2 = new StyledData();
								dataStyle2.setStyles(centerboldStyleVO);

								//ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
								ReportColumnVO[] rptCol = report.getColumnsToDisplay();
								int totallength=rptCol.length;


								dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(total))+" only.");
								dataStyle2.setColspan(colspan);
								row2.add(dataStyle2);

								for(int c=0;c<(totallength-colspan);c++)
									row2.add("");
								row2.add(grpByCityId);
								row2.add(""); // added for remarks column on report id 300010
								DataList.add(row2);	

								StyleVO[] borderVO1 = new StyleVO[4];
								borderVO1[0] = new StyleVO();
								borderVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
								borderVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
								borderVO1[1] = new StyleVO();
								borderVO1[1].setStyleId(IReportConstants.BORDER);
								borderVO1[1].setStyleValue("NO");
								borderVO1[2] = new StyleVO();
								borderVO1[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
								borderVO1[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
								borderVO1[3] = new StyleVO();
								borderVO1[3].setStyleId(IReportConstants.STYLE_FONT_SIZE);
								borderVO1[3].setStyleValue("10");


								//Modified by rahul
								StyledData stampFooter=new StyledData();
								stampFooter.setStyles(borderVO1);
								stampFooter.setData(tabularData);
								ArrayList stampRow=new ArrayList();
								//stampRow.add(tabularData);

								tabularData.setStyles(borderVO1);

								tabularData.setColspan(colspan);


								stampRow.add(tabularData);
								for(int c=0;c<(7);c++)
									stampRow.add("");	
								//tabularData.setStyles(borderVO1);
								stampRow.add(grpByCityId);
								stampRow.add(""); // added for remarks column on report id 300010
								DataList.add(stampRow);
							}	

							/*else if(grpbyCountno>=finalpagesize)
								{*/

							ArrayList pagebreakdata= new ArrayList();
							pagebreakdata.add(new PageBreak());
							//pagebreakdata.add(tData);
							pagebreakdata.add("Data");
							//								added by rahul
							for(int c=0;c<(7);c++)
								pagebreakdata.add("");
							//ended by rahul
							pagebreakdata.add(""); // added for remarks column on report id 300010
							DataList.add(pagebreakdata);
						}

						if(!prevCityId.equals(cityId))
						{
							grpByCityId++;	
							grpbyCountno=0;
							pageNo=1;
						}


						grpbyCountno++;	
						row = new ArrayList();
						StyledData dataStyle1 = new StyledData();
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////								


						if(grpbyCountno==1  )
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(pageNoStyleVO);
							pageRecordRow = new ArrayList();

							logger.info("Pageno::"+pageNo);
							dataStyle1.setData("Page No :"+pageNo+"/"+pageNo); 
							ReportColumnVO[] rptCol21 = report.getColumnsToDisplay();
							int colspan1=rptCol21.length;
							pageRecordRow.add(dataStyle1);
							page1RecordRow.add(dataStyle1);
							dataStyle1.setColspan(colspan1);
							for(int c=0;c<(8);c++)
							{
								pageRecordRow.add("");
								page1RecordRow.add("");
							}
							pageRecordRow.add(""); // added for remarks column on report id 300010
							page1RecordRow.add(""); // added for remarks column on report id 300010
							DataList.add(pageRecordRow);							
							page1RecordRow = pageRecordRow;
							logger.info("*****************************************page1RecordRow::::::::::::::"+page1RecordRow);
							logger.info("*****************************************row::::pageRecordRow::::::::::"+pageRecordRow);
							logger.info("*****************************************DataList::::::::::::::"+DataList);
						}
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////
						if(!prevCityId.equals(cityId))
						{
							ArrayList rowCity =new ArrayList();
							citySelectedInReport = billDAO.getCityName(cityId);
							String  subheader="City -"+citySelectedInReport;
							ReportColumnVO[] rptCol21 = report.getColumnsToDisplay();
							int colspan1=rptCol21.length;

							StyledData dataStyle3 = new StyledData();
							dataStyle3.setStyles(centerboldStyleVO1);
							//	dataStyle3.setData(tData);
							dataStyle3.setData("City -"+citySelectedInReport);
							dataStyle3.setColspan(colspan1);
							//rowCity.add(tData);

							rowCity.add(dataStyle3);
							for(int c=0;c<(7);c++)
								rowCity.add("");	
							rowCity.add(grpByCityId);
							rowCity.add(""); // added for remarks column on report id 300010
							DataList.add(rowCity);
						}
						/*if(!prevCityId.equals(cityId))
								{
								citySelectedInReport = billDAO.getCityName(cityId);
								if( BillNo != null && BillNo != "" )
								{
									//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
									String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of "+ citySelectedInReport +" HOUSE RENT Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
									stData.add(new StyledData (deptHeader,headerStyleVo));
									styleList.add(stData);
								}
								else
								{
									//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
									String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of "+ citySelectedInReport +" HOUSE RENT Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
									stData.add(new StyledData (deptHeader,headerStyleVo));		    				 
									styleList.add(stData);

								}	
								TabularData tData  = new TabularData(styleList);
//								tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
//								tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
//								tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGE);
								tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
								tData.addStyle(IReportConstants.BORDER, "NO");
//								tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
								tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
								tData.setColspan(colspan);*/

						/*ArrayList stampheader= new ArrayList();
								stampheader.add(tData);
								for(int c=0;c<(7);c++)
									stampheader.add("");
								DataList.add(stampheader);*/


						row = new ArrayList();
						row.add(grpbyCountno);
						row.add(sector);
						row.add(block);
						row.add(QuarterType);
						row.add(Name);
						row.add(designation);
						if(gradeId==AISGradeCode)
							row.add(Math.round(hrr+rentB));
						else
							row.add(Math.round(hrr+rentB));	

						if (!prevCityId.equals(cityId))
							total=Math.round(rentB)+Math.round(hrr);
						else
							total+=Math.round(rentB)+Math.round(hrr);
						if(!billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
						{
							row.add( " HRR Amount Recevored for " + RecoveryMonthYear);
						}
						else							
						row.add(""); // added for remarks column on report id 300010
						DataList.add(row);

						if(grpbyCountno%finalpagesize==0 || cnt==RowList.size() )
						{
							if( cnt!=(RowList.size()))
							{

								row =new ArrayList();
								row.add("");

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData("B/F");                  
								row.add(dataStyle1);
								row.add("");
								row.add("");
								row.add("");
								row.add("");
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(total);
								row.add(dataStyle1);
								//row.add(total);
								row.add(""); // added for remarks column on report id 300010
								DataList.add(row);


								/*row = new ArrayList();
											row.add(new PageBreak());
											row.add("Data");
											DataList.add(row);*/




								if( cnt!=(RowList.size()))
								{
									ArrayList pagebreakdata= new ArrayList();
									pagebreakdata.add(new PageBreak());
									//pagebreakdata.add(tData);
									pagebreakdata.add("Data");
									//												added by rahul
									for(int c=0;c<(7);c++)
										pagebreakdata.add("");
									//ended by rahul
									pagebreakdata.add(""); // added for remarks column on report id 300010
									DataList.add(pagebreakdata);

								}	
								logger.info(" Datalist before generating pageno ::::"+DataList);

								//											Added by rahul
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								ArrayList pageNoRow = new ArrayList();
								logger.info("Pageno before c/f::"+pageNo);
								int tempPageNo = pageNo+1;
								int tempPageCount = tempPageNo;
								int tempPageCounter = tempPageCount;							

								nextCounter = tempPageCounter;
								logger.info("tempPageNoCount:"+tempPageCount);
								dataStyle1.setData("Page No :"+tempPageNo+"/"+tempPageCount); 
								ReportColumnVO[] rptCol21 = report.getColumnsToDisplay();
								int colspan1=rptCol21.length;
								pageNoRow.add(dataStyle1);
								dataStyle1.setColspan(colspan1);
								for(int c=0;c<(8);c++)
									pageNoRow.add("");
								pageNoRow.add(""); // added for remarks column on report id 300010
								DataList.add(pageNoRow);

								//Code to show pageno on report:: Page No.: 1/4,2/4,3/4,4/4 type of record
								if(prevCounter<nextCounter)
								{
									logger.info("Yes");
								}
								else
								{
									logger.info("No");										
									indexArray = new ArrayList();
								}					
								prevCounter = nextCounter;
								//Code added to find last Index of PageNo: 1/1								
								logger.info("page1RecordRow:::"+page1RecordRow);
								logger.info("First indexOf(page1RecordRow: 1/1)::"+DataList.lastIndexOf(page1RecordRow));

								indexArray.add(DataList.lastIndexOf(page1RecordRow));
								// Ended code to find last index of PageNo: 1/1
								int index = DataList.lastIndexOf(pageNoRow);
								indexArray.add(index);								
								logger.info("First indexOf(pageNoRow)::"+DataList.indexOf(pageNoRow));
								logger.info("DataList.indexOf(row) last Index::"+index);
								logger.info("DataRow from DataList::"+DataList.get(index));
								ArrayList tempArray = new ArrayList();
								tempArray = (ArrayList)DataList.get(index);
								logger.info("Row from tempArray::"+tempArray.get(0).toString());
								String strPageNo = "";
								logger.info("indexArray.size::"+indexArray.size());
								logger.info("tempPageCount::"+tempPageCount);
								logger.info("prevCounter::"+prevCounter);
								logger.info("nextCounter::"+nextCounter);
								logger.info("Index array data::"+indexArray);

								for (int i=0;i<indexArray.size();i++)
								{									
									int tempIndex = Integer.parseInt(indexArray.get(i).toString());
									logger.info("tempIndex:"+tempIndex);
									tempArray = (ArrayList)DataList.get(tempIndex);
									strPageNo = tempArray.get(0).toString();
									String tempStrPageNo = strPageNo;
									logger.info("strPageNo:"+strPageNo);								
									strPageNo = strPageNo.subSequence(0, strPageNo.indexOf("/")).toString();								
									strPageNo = strPageNo +"/"+ tempPageCount;									
									logger.info("strPageNo with tempPageCount:"+strPageNo);
									dataStyle1 = new StyledData();
									dataStyle1.setStyles(pageNoStyleVO);
									dataStyle1.setData(strPageNo);
									dataStyle1.setColspan(colspan1);
									tempArray.set(0, dataStyle1);
									DataList.set(tempIndex, tempArray);
								}
								//Code to show pageno ended by rahul
								//Ended by rahul
								row =new ArrayList();
								row.add("");

								StyledData	 dataStyle2 = new StyledData();
								dataStyle2.setStyles(boldStyleVO);
								dataStyle2.setData("C/F");                  
								row.add(dataStyle2);
								row.add("");
								row.add("");
								row.add("");
								row.add("");
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(total);
								row.add(dataStyle1);
								//row.add(total);
								row.add(grpByCityId);
								row.add(""); // added for remarks column on report id 300010
								DataList.add(row);
								//following block commented by rahul Date : 08-06-2009
								///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								/*row =new ArrayList();
											row.add("");

											 dataStyle1 = new StyledData();
											dataStyle1.setStyles(boldStyleVO);
											dataStyle1.setData("B/F");                  
											row.add(dataStyle1);
											row.add("");
											row.add("");
											row.add("");
											row.add("");
											dataStyle1 = new StyledData();
											dataStyle1.setStyles(boldStyleVO);
											dataStyle1.setData(total);
											row.add(dataStyle1);
											//row.add(total);
											DataList.add(row);


											row = new ArrayList();
											row.add(new PageBreak());
											row.add("Data");
											DataList.add(row);

											row =new ArrayList();
											row.add("");

												 dataStyle2 = new StyledData();
											dataStyle2.setStyles(boldStyleVO);
											dataStyle2.setData("C/F");                  
											row.add(dataStyle2);
											row.add("");
											row.add("");
											row.add("");
											row.add("");
											dataStyle1 = new StyledData();
											dataStyle1.setStyles(boldStyleVO);
											dataStyle1.setData(total);
											row.add(dataStyle1);
											//row.add(total);

											row.add(grpByCityId);
											DataList.add(row);*/
							}
							/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////										
							/*else if((grpbyCountno>=1 && grpbyCountno<finalpagesize))
										{
											ArrayList	 row1 = new ArrayList();
											row1.add("");
											row1.add("");
											row1.add("");
											row1.add("");
											row1.add("");
											StyledData dataStyle3 = new StyledData();
											dataStyle3.setStyles(boldStyleVO);
											dataStyle3.setData("TOTAL");                  
											row1.add(dataStyle3);


											//  row.add("Total");
											dataStyle3 = new StyledData();
											dataStyle3.setStyles(boldStyleVO);
											dataStyle3.setData(total);
											row1.add(dataStyle3);
											row1.add(grpByCityId)	;
											DataList.add(row1);
											ArrayList row2 = new ArrayList();

											StyleVO[] centerboldStyleVO = new StyleVO[2];
											centerboldStyleVO[0] = new StyleVO();
											centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
											centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
											centerboldStyleVO[1] = new StyleVO();
											centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
											centerboldStyleVO[1].setStyleValue("Left"); 
											StyledData dataStyle2 = new StyledData();
											dataStyle2.setStyles(centerboldStyleVO);

											ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
											ReportColumnVO[] rptCol = report.getColumnsToDisplay();
											int totallength=rptCol.length;
											int colspan=rptCol2.length;                    	        

											dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(total))+" only.");
											dataStyle2.setColspan(colspan);
											row2.add(dataStyle2);

											for(int c=0;c<(totallength-colspan);c++)
												row2.add("");
											row2.add(grpByCityId);
											DataList.add(row2);	

											StyleVO[] borderVO1 = new StyleVO[4];
											borderVO1[0] = new StyleVO();
											borderVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
											borderVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
											borderVO1[1] = new StyleVO();
											borderVO1[1].setStyleId(IReportConstants.BORDER);
											borderVO1[1].setStyleValue("NO");
											borderVO1[2] = new StyleVO();
											borderVO1[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
											borderVO1[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
											borderVO1[3] = new StyleVO();
											borderVO1[3].setStyleId(IReportConstants.STYLE_FONT_SIZE);
											borderVO1[3].setStyleValue("10");


											//Modified by rahul
											StyledData stampFooter=new StyledData();
											stampFooter.setStyles(borderVO1);
											stampFooter.setData(tabularData);
											ArrayList stampRow=new ArrayList();
											//stampRow.add(tabularData);

											tabularData.setStyles(borderVO1);
											for(int c=0;c<(6);c++)
												stampRow.add("");	
											//tabularData.setStyles(borderVO1);
											stampRow.add(tabularData);
											stampRow.add(grpByCityId);
											DataList.add(stampRow);

										}*/




							ArrayList row1 = new ArrayList();


							StyleVO[] centerboldStyleVO = new StyleVO[2];
							centerboldStyleVO[0] = new StyleVO();
							centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
							centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
							centerboldStyleVO[1] = new StyleVO();
							centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
							centerboldStyleVO[1].setStyleValue("Left"); 
							StyledData dataStyle2 = new StyledData();
							dataStyle2.setStyles(centerboldStyleVO);
							// dataStyle2.setData(monthsubscrTotalInWords);
							ReportColumnVO[] rptCol = report.getColumnsToDisplay(); 
							int totallength=rptCol.length;
							//ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();

							//int colspan=rptCol2.length;

							dataStyle2.setColspan(colspan);
							row1.add(dataStyle2);
							//logger.info(totallength+"**************************"+colspan);
							for(int c=0;c<(6);c++)
								row1.add("");
							row1.add(grpByCityId);
							//row.add(new PageBreak());
							//row.add("Data");

							row1.add(""); // added for remarks column on report id 300010
							DataList.add(row1);	
						}
						if(grpbyCountno%finalpagesize==0)
						{
							grpByCityId++;


						}	



						cnt++;
						prevCityId=cityId;	


					}



					ArrayList row = new ArrayList();
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					StyledData dataStyle3 = new StyledData();
					dataStyle3.setStyles(boldStyleVO);
					dataStyle3.setData("TOTAL");                  
					row.add(dataStyle3);
					logger.info("in line number 869");
					//  row.add("Total");
					dataStyle3 = new StyledData();
					dataStyle3.setStyles(boldStyleVO);
					dataStyle3.setData(total);
					row.add(dataStyle3);
					// row.add(total/*+System.getProperty("line.separator")+TotalInWords*/);	
					row.add(""); // added for remarks column on report id 300010
					DataList.add(row);
					ArrayList row1 = new ArrayList();

					logger.info("in line number 879");
					StyleVO[] centerboldStyleVO = new StyleVO[2];
					centerboldStyleVO[0] = new StyleVO();
					centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
					centerboldStyleVO[1] = new StyleVO();
					centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					centerboldStyleVO[1].setStyleValue("Left"); 
					StyledData dataStyle2 = new StyledData();
					dataStyle2.setStyles(centerboldStyleVO);

					//ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
					ReportColumnVO[] rptCol = report.getColumnsToDisplay();
					int totallength=rptCol.length;


					dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(total))+" only.");
					dataStyle2.setColspan(colspan);
					row1.add(dataStyle2);

					for(int c=0;c<(totallength-colspan);c++)
						row1.add("");
					row1.add(""); // added for remarks column on report id 300010
					logger.info("in line number 902");
					DataList.add(row1);
				}
				else
				{
					report.setReportName("No Record Found");
					ArrayList d = new ArrayList();
					ArrayList r = new ArrayList();
					r.add("");
					d.add(r);
					TabularData td  = new TabularData(d);
					td.addStyle(IReportConstants.ADDL_HEADER_LOCATION, IReportConstants.VALUE_ADDL_HEADER_LOCATION_BELOW);
					td.addStyle(IReportConstants.BORDER, "No"); 
					report.setAdditionalHeader(td);
				}// ended by Rajan
				logger.info("rg datalist::::"+DataList);
				return DataList;

			}
		}








		catch(Exception e)
		{
			logger.error("Printing StackTrace");
			logger.error("Error in ResourceMoniteringDAO" ,e);			
			logger.error("Error is: "+ e.getMessage());
		}
		finally
		{
			DBConnection.closeResultSet(lRs1);
			DBConnection.closeStatement(lStmt);           
			DBConnection.closeConnection(lCon);
		}
		return DataList;
	}
	//end by manoj for Festival and foodgrain advance report
	//	by manoj for Non Govt Deduction report


	public ReportVO getUserReportVO( ReportVO report, Object criteria )
	throws ReportException
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		long multipleMonthSupplBill=Integer.parseInt(resourceBundle.getString("multipleMonthSupplBill"));
		logger.info ("***********Inside User Report VO *********************");
		Hashtable sessionKeys = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.SESSION_KEYS );
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
		String date = fmt.format(today);
		SimpleDateFormat fmt1 =new SimpleDateFormat("yyyy");
		String yr = fmt1.format(today);
		fmt1 =new SimpleDateFormat("MM");

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		String month = fmt1.format(today);

		if(month.charAt(0)=='0')
		{

			month=month.substring(1,2);

		}

		//For default parameters
		if(  report.getReportCode().equals("3") || report.getReportCode().equals("2500003")||report.getReportCode().equals("6") || report.getReportCode().equals("2500006")|| report.getReportCode().equals("7") || report.getReportCode().equals("2500007")|| report.getReportCode().equals("8") || report.getReportCode().equals("2500008")|| report.getReportCode().equals("10") || report.getReportCode().equals("2500010") || report.getReportCode().equals("11") || report.getReportCode().equals("2500011")|| report.getReportCode().equals("13") || report.getReportCode().equals("2500013") || report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("5") || report.getReportCode().equals("2500005") || report.getReportCode().equals("9") || report.getReportCode().equals("2500009"))
		{            
			//set default to current year
			report.setParameterValue("Year",yr);
			//set default to current month
			report.setParameterValue("Month",month);
			//set department as dept of current logged in user
			report.setParameterValue("Department",locationId+"");
			//added by ravysh
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}

		/* if( report.getReportCode().equals("3") || report.getReportCode().equals("4") || report.getReportCode().equals("5") || report.getReportCode().equals("6") || report.getReportCode().equals("7") || report.getReportCode().equals("8") || report.getReportCode().equals("9") || report.getReportCode().equals("10")|| report.getReportCode().equals("11")|| report.getReportCode().equals("13"))
	     {            
	          report.setParameterValue("Department",locationId+"");
	     } */


		return report;
	}




}
