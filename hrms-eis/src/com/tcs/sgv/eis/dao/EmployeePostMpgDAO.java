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
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.DefaultComboItem;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.reports.dao.ReportParameterDAO;

public class EmployeePostMpgDAO extends DefaultReportDataFinder implements ReportDataFinder
{
	ResourceBundle locStrsBundle;
	private static Logger logger = Logger.getLogger(PayrollReportsDAO.class );



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

		//logger.info("EmployeeLoanHistroyDAO");
		String langName=report.getLangId();
		int finalpagesize=22;
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
		String lowerfooter="GANDHINAGAR"+System.getProperty("line.separator")+"Code No. PG3";
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");


		ArrayList DataList = new ArrayList(  );            
		StyleVO[] boldStyleVO = new StyleVO[1];
		boldStyleVO[0] = new StyleVO();
		boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
		StyledData dataStyle = null;
		StyleVO[] colorStyleVO = new StyleVO[1];
		colorStyleVO[0] = new StyleVO();
		colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
		colorStyleVO[0].setStyleValue("blue");
		selfCloseVO = new StyleVO[1];
		selfCloseVO[0] = new StyleVO();
		selfCloseVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
		selfCloseVO[0].setStyleValue("javascript:self.close()"); 


		StyleVO[] baseFont = new StyleVO[1];
		baseFont[0] = new StyleVO(  );
		baseFont[0].setStyleId( IReportConstants.STYLE_FONT_SIZE );
		baseFont[0].setStyleValue( "13" );


		ReportTemplate rt = new ReportTemplate();
		rt.put(IReportConstants.TMPLT_COLUMN_HEADER,baseFont);
		rt.put(IReportConstants.TMPLT_BASE_FONT,baseFont);
		report.setReportTemplate( rt );


		ReportAttributeVO ravo1 = new ReportAttributeVO();
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_LOCATION);
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_ON_EACH_PAGE);
		ravo1.setAlignment(IReportConstants.HEADER_ALIGN_CENTER);
		report.addReportAttributeItem(ravo1);
		report.setAdditionalHeader("");

		ServiceLocator serv1 = (ServiceLocator) requestKeys.get("serviceLocator");

		PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv1.getSessionFactory());
		boolean isBillDefined = paybillDAO.isBillsDefined(locationId);

		StyleVO[] styleVOPgBrk=null;
		styleVOPgBrk = new StyleVO[2];



		styleVOPgBrk[0] = new StyleVO();
		styleVOPgBrk[0].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
		styleVOPgBrk[0].setStyleValue("yes");
		styleVOPgBrk[0] = new StyleVO();


		styleVOPgBrk[1] = new StyleVO();
		styleVOPgBrk[1].setStyleId(IReportConstants.SHOW_REPORT_WHEN_NO_DATA);
		styleVOPgBrk[1].setStyleValue("yes");

		report.addReportStyles(styleVOPgBrk);
		//logger.info("EmployeeLoanHistroyDAO111");

		try   
		{
			lCon = DBConnection.getConnection(  );
			lStmt = lCon.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//logger.info("getReportCode"+report.getReportCode());
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

			if(report.getReportCode().equals("3000004"))
			{

				//logger.info("EmployeeLoanHistroyDAO3000004");


				StringBuffer lsb = new StringBuffer(  );      

				String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
				//if(empid!="")
				String Department="";
				String month="";
				String year="";
				String ReportType="";

				String loan_type="";
				loan_type=CheckIfNull(report.getParameterValue( "LoanType" ));
				//logger.info("loan_typeloan_typeloan_typeloan_type" + loan_type);
				boolean name = constantsBundle.getString("hba").equalsIgnoreCase(loan_type);
				//logger.info("namenamenamenamename" + name);

				String employeeid=CheckIfNull(report.getParameterValue( "employeeName" ));
				//logger.info("employeeidemployeeidemployeeidemployeeidemployeeid" + employeeid);

				Department=CheckIfNull(report.getParameterValue( "Department" ));
				//logger.info("DepartmentDepartmentDepartmentDepartment" + Department);
				if(Department.equals("")||Department.equals("-1"))
					Department=	locationId+"";	
				else
					locationId=Long.parseLong(Department);
				//logger.info("locationIdlocationIdlocationIdlocationIdlocationId" + locationId);
				

				String ShowSignatureFlg=CheckIfNull(report.getParameterValue( "Show Signature" ));

				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					//logger.info("No Of Rec is********====>"+noOfRec);
					finalpagesize=Integer.parseInt(noOfRec);
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
					//logger.info("BillNoBillNoBillNo" +BillNo);
					l++;
				} 
				String DeptName=locationNameincaps;
				String billType=CheckIfNull(report.getParameterValue( "billTypePara" ));

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

				//Ended by rahul

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

				//added by abhi
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


				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				ReportType=CheckIfNull(report.getParameterValue( "Report Type" ));
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

				Session hibSession = sessionFactory.getCurrentSession();


				//logger.info("EmployeeLoanHistroyDAO333");

				sdfObj = new SimpleDateFormat("MMM");
				String Month = sdfObj.format(startMonthDate);

				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();

				java.util.Calendar calendar = java.util.Calendar.getInstance(); 
				int curYear=calendar.get(calendar.YEAR); 
				int curMonth=calendar.get(Calendar.MONTH);
				String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};        
				Calendar cale = Calendar.getInstance(); 
				String MONTH = monthName[cale.get(Calendar.MONTH)]; 	          


				/*logger.info("MONTHMONTHMONTHMONTHMONTH" +MONTH);
				logger.info("monthmonthmonthmonth" +month);
				logger.info("curYearcurYearcurYearcurYearcurYear" +curYear);*/

				
				
				
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				DeptName=payBillDAOImpl.getOffice(locationId);
			
				//logger.info("Hum Office is "+DeptName);	
				
				
				//int ii = Integer.getInteger(month);
				String monthname="";

				if(month != null && !month.equals("") &&!month.equals("-1"))
				{
					Integer in = Integer.parseInt(month);

					//logger.info("value of i:: "+in);
					//logger.info("value of i:: "+in);

					//logger.info("monthName[i-1]"+monthName[in-1]);
					//logger.info("monthName[i-1]"+monthName[in-1]);

					monthname = monthName[in-1];
				}
				String deptHeader ="";

				String EmployeeName = billDAO.getEmployeeNameFromEmployeeId(employeeid);
				//logger.info("EmployeeNameEmployeeNameEmployeeName" +EmployeeName);


				String Title = "Schedule for the Employee-Pay Items Mappings";
				String MajorHead="0028";

				if(month != null && !month.equals("") &&!month.equals("-1"))
				{

					deptHeader=DeptName+System.getProperty("line.separator")+Title;
				//	String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule for the Recovery of Profession Tax of the Month of  "+MONTH+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
				}
				else
				{
					deptHeader=Title;

				}
				StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
				String space = " ";
				String Treasury ="PUNE(2201)";
				styleList.add(stData);
				String DTO="District Treasury Office,Pune(003268)";
				ArrayList r = new ArrayList();
				if(month != null && !month.equals("") &&!month.equals("-1"))
				{
					r.add(new StyledData("For the Month of "+monthname +space+year,leftHeader));
				}
				else
				{
					r.add("");
				}
				r.add(new StyledData("Employee ID : "+employeeid,rightHead));
				styleList.add(r);  

				ArrayList r2= new ArrayList();
				r2.add(new StyledData("Employee Name : "+EmployeeName,leftHeader));
				styleList.add(r2);


				TabularData tData  = new TabularData(styleList);

				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");

				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);


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


				int allowId=2500135;
				int currentMonthBill = 0;
				Date currentDate = new Date();

				if(currentDate.after(finYrDate) && currentDate.before(endMonthDate))
					currentMonthBill = 1;

				/*logger.info("employee id for checking**********" + employeeid);
				logger.info("month for checking**********" + month);
				logger.info("year for checking**********" + year);*/





				/*	String query = " SELECT * FROM HR_EIS_EMP_COMPONENT_MPG loc,HR_PAY_ALLOW_TYPE_MST allow where loc.COMPO_GROUP_ID=(SELECT max(a.EMP_COMPO_GRP_ID) FROM HR_EIS_EMP_COMPONENT_GRP_MST a ";
				query+= " where (a.GRP_MONTH=(select max(b.grp_month) from HR_EIS_EMP_COMPONENT_GRP_MST b where b.GRP_MONTH<="+month+" and b.grp_year="+year+") and a.grp_year="+year+") ";
				query+= "  or   ( a.grp_year=(select max(b.grp_year) from HR_EIS_EMP_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+" ) ";
				query+= "   and a.grp_month = (select max(c.grp_month) from HR_EIS_EMP_COMPONENT_GRP_MST c where c.grp_year= (select max(b.grp_year) from HR_EIS_EMP_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+"))) ";
				query+= "  and a.EMP_ID="+employeeid+") and loc.IS_ACTIVE =1 and loc.COMPO_TYPE = 2500134  and loc.COMPO_ID=allow.ALLOW_CODE  ";
				 */
				String query = " SELECT compo.COMPO_ID,allow.ALLOW_CODE ,allow.ALLOW_NAME,compo.START_DATE,compo.END_DATE,grp.REMARKS,compo.IS_ACTIVE,grp.WEF_DATE,grp.EMP_COMPO_GRP_ID FROM HR_EIS_EMP_COMPONENT_MPG compo,HR_EIS_EMP_COMPONENT_GRP_MST grp,HR_PAY_ALLOW_TYPE_MST allow ";
				query+= "   where grp.EMP_ID="+employeeid+"  and compo.COMPO_GROUP_ID=grp.EMP_COMPO_GRP_ID and compo.COMPO_ID=allow.ALLOW_CODE and compo.COMPO_TYPE=2500134 ";
				//query+= "  and  grp.GRP_MONTH="+month+" and grp.GRP_YEAR="+year+"  order by allow.ALLOW_NAME,grp.GRP_MONTH desc ";

				if(month != null && !month.equals("") &&!month.equals("-1"))
					query+= "  and  grp.GRP_MONTH="+month+" ";

				if(year != null && !year.equals("") &&!year.equals("-1"))
					query+= "  and   grp.GRP_YEAR="+year+"";

				query+= " order by grp.EMP_COMPO_GRP_ID desc ";


				Query sqlQuery = hibSession.createSQLQuery(query);	
				
				logger.info("***Query for Deduction details**" +query);
				//logger.info("***Query for Income Tax Deduction details**" +query);
				List RowList=sqlQuery.list();


				/*	String query1 = " SELECT loc.COMPO_ID,dedu.DEDUC_NAME FROM HR_EIS_EMP_COMPONENT_MPG loc,HR_PAY_DEDUC_TYPE_MST dedu where loc.COMPO_GROUP_ID=(SELECT max(a.EMP_COMPO_GRP_ID) FROM HR_EIS_EMP_COMPONENT_GRP_MST a ";
				query1+= " where (a.GRP_MONTH=(select max(b.grp_month) from HR_EIS_EMP_COMPONENT_GRP_MST b where b.GRP_MONTH<="+month+"and b.grp_year="+year+") and a.grp_year="+year+") ";
				query1+= "  or   ( a.grp_year=(select max(b.grp_year) from HR_EIS_EMP_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+" ) ";
				query1+= "   and a.grp_month = (select max(c.grp_month) from HR_EIS_EMP_COMPONENT_GRP_MST c where c.grp_year= (select max(b.grp_year) from HR_EIS_EMP_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+"))) ";
				query1+= "  and a.EMP_ID="+employeeid+") and loc.IS_ACTIVE =1 and loc.COMPO_TYPE = 2500135  and loc.COMPO_ID=dedu.DEDUC_CODE ";*/

				String query1 = " SELECT compo.COMPO_ID,dedu.DEDUC_CODE,dedu.DEDUC_NAME,compo.START_DATE,compo.END_DATE,grp.REMARKS,compo.IS_ACTIVE,grp.WEF_DATE,grp.EMP_COMPO_GRP_ID FROM HR_EIS_EMP_COMPONENT_MPG compo,HR_EIS_EMP_COMPONENT_GRP_MST grp,HR_PAY_DEDUC_TYPE_MST dedu ";
				query1+= "  where grp.EMP_ID="+employeeid+"  and compo.COMPO_GROUP_ID=grp.EMP_COMPO_GRP_ID and compo.COMPO_ID=dedu.DEDUC_CODE and compo.COMPO_TYPE=2500135 ";
				//query1+= "  and  grp.GRP_MONTH="+month+" and grp.GRP_YEAR="+year+"  order by dedu.DEDUC_NAME,grp.GRP_MONTH desc ";

				if(month != null && !month.equals("") &&!month.equals("-1"))

					query1+= "  and  grp.GRP_MONTH="+month+" ";
				//and grp.GRP_YEAR="+year+"  order by dedu.DEDUC_NAME,grp.GRP_MONTH desc ";

				if(year != null && !year.equals("") &&!year.equals("-1"))

					query1+= "   and grp.GRP_YEAR="+year+" ";


				query1+= "   order by grp.EMP_COMPO_GRP_ID desc ";


				Query sqlQuery1 = hibSession.createSQLQuery(query1);	      	

				List RowList1=sqlQuery1.list();




				String query3 = " SELECT compo.COMPO_ID,dedu.DEDUC_NAME,gr.GRP_MONTH ,gr.GRP_YEAR,compo.START_DATE,compo.END_DATE,gr.REMARKS FROM HR_EIS_EMP_COMPONENT_GRP_MST gr,HR_EIS_EMP_COMPONENT_MPG compo,HR_PAY_DEDUC_TYPE_MST dedu ";
				query3+= "   where  compo.COMPO_GROUP_ID=gr.EMP_COMPO_GRP_ID ";
				query3+= "  and compo.COMPO_ID=dedu.DEDUC_CODE ";
				//query3+= "  and compo.COMPO_ID=dedu.DEDUC_CODE and  gr.GRP_YEAR="+year+" and gr.EMP_ID="+employeeid+" order by gr.grp_year,gr.grp_month desc ";
				if(month != null && !month.equals("") &&!month.equals("-1"))
					query3+= "  and gr.GRP_MONTH="+month+"  ";

				if(year != null && !year.equals("") &&!year.equals("-1"))
					query3+= "  and gr.GRP_YEAR="+year+" ";

				query3+= "   and gr.EMP_ID="+employeeid+" order by gr.grp_year,gr.grp_month desc ";



				Query sqlQuery3 = hibSession.createSQLQuery(query3);	      	
				List RowList3=sqlQuery3.list();

				String query2 = " SELECT compo.COMPO_ID,allow.ALLOW_NAME,gr.GRP_MONTH ,gr.GRP_YEAR,compo.START_DATE,compo.END_DATE,gr.REMARKS FROM HR_EIS_EMP_COMPONENT_GRP_MST gr,HR_EIS_EMP_COMPONENT_MPG compo,HR_PAY_ALLOW_TYPE_MST allow ";
				query2+= "   where  compo.COMPO_GROUP_ID=gr.EMP_COMPO_GRP_ID  ";
				query2+= "  and compo.COMPO_ID=allow.ALLOW_CODE ";

				if(month != null && !month.equals("") &&!month.equals("-1"))
					query2+= "  and gr.GRP_MONTH="+month+" ";

				if(year != null && !year.equals("") &&!year.equals("-1"))
					query2+= " and gr.GRP_YEAR="+year+" ";

				query2+= "  and gr.EMP_ID="+employeeid+" order by gr.grp_year,gr.grp_month desc ";

				Query sqlQuery2 = hibSession.createSQLQuery(query2);	      	
				List RowList2=sqlQuery2.list();


				/*logger.info("***Query for allowance details**" +query);
				logger.info("***Query for Deduction details111111**" +query1);
				logger.info("***Query for allowance details222222**" +query2);
				logger.info("***Query for Deduction details333333**" +query3);



				logger.info("RowList size ************"+RowList.size());
				logger.info("RowList1.size() for 1111....."+RowList1.size());*/


				if(RowList.size()>0 || RowList1.size()>0 )
				{
					/*int count=0;
					if(RowList.size()>RowList1.size())
						count =RowList.size();
					else
						count =RowList1.size();*/
					int count = RowList.size()>RowList1.size()?RowList.size():RowList1.size();

					int cnt=1;

					Iterator itr = RowList.iterator();

					String allowName;
					String allowStartDate;
					String dednStartDate;

					String allowEndDate;
					String dednEndDate;

					long IsActive;
					long isActive=0;


					long prevGrpId =0;

					Iterator itr1 = RowList1.iterator();
					String deductionName;

					Object[] rowList=null,rowList1=null;
					ArrayList row=null;
					for (int i=0,allowCount=0,deducCount=0;allowCount<count&&deducCount<count;i++,allowCount++,deducCount++)
					{
						rowList=null;
						rowList1=null;
						long currGrpId =0;
						//logger.info("allowCount is "+allowCount+" deduc count is "+deducCount);
						
						if(allowCount<RowList.size())
							rowList = (Object[]) RowList.get(allowCount);
						if(deducCount<RowList1.size())
							rowList1 = (Object[]) RowList1.get(deducCount);
						if(rowList!=null || rowList1!=null)
						{
							
							long allowCurrGrp =0;
							long deducCurrGrp =0;
						
							if(rowList!=null)
							allowCurrGrp = Long.parseLong(rowList[8].toString());
							
							if(rowList1!=null)
							deducCurrGrp =  Long.parseLong(rowList1[8].toString());
							
							if(allowCurrGrp!=deducCurrGrp)
							{
								
								
								//logger.info("abhi111111111");
								if(allowCurrGrp!= prevGrpId && deducCurrGrp == prevGrpId)
								{
									rowList = null;
									allowCount--;
									currGrpId = deducCurrGrp;
								}
								else if(allowCurrGrp == prevGrpId && deducCurrGrp != prevGrpId)
								{
									rowList1 = null;
									deducCount--;
									currGrpId = allowCurrGrp;
								}
								else
								{
									logger.info("should not come here");
								}
							}
							else
							{
								
								//logger.info("abhi2222222");
								currGrpId=allowCurrGrp;
							}
						}
						else
						{
							if(rowList==null)
							{
								currGrpId = Long.parseLong(rowList1[8].toString());
							}
							else if(rowList1 == null)
							{
								currGrpId = Long.parseLong(rowList[8].toString());
							}
							else
							{
								logger.info("should not come here");
							}
						}

						      String Remarks="";
						      String WED ="";
						    if(rowList!=null)
							Remarks =rowList[5].toString();
						    
						    if(rowList!=null)
							WED = rowList[7].toString();

							if(currGrpId!=prevGrpId)
							{

								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
								WED=sdf.format(sdformat.parse(WED));


								ArrayList orderdataList = new ArrayList();
								StyleVO[] centerboldStyleVO = new StyleVO[2];
								centerboldStyleVO[0] = new StyleVO();
								centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
								centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
								centerboldStyleVO[1] = new StyleVO();
								centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
								centerboldStyleVO[1].setStyleValue("Left"); 
								dataStyle = new StyledData();
								dataStyle.setStyles(centerboldStyleVO);
								dataStyle.setColspan(5);
								dataStyle.setData("Date: "+WED+space+"Remarks:"+Remarks);
								orderdataList.add(dataStyle);
								DataList.add(orderdataList);
								cnt=1;
							}

							
						
							
							
							row= new ArrayList();
							row.add(cnt);
							if(rowList!=null)
							{
								allowName = (rowList[2]!=null?rowList[2]:"0").toString();
								allowStartDate = (rowList[3]!=null?rowList[3]:"0").toString();
							
								allowEndDate = (rowList[4]!=null?rowList[4]:"-").toString();

								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

								if(rowList[3]!=null)
								{
									allowStartDate=sdf.format(sdformat.parse(allowStartDate));
								}



								if(rowList[4]!=null)
								{
									allowEndDate=sdf.format(sdformat.parse(allowEndDate));
								}


								IsActive = Long.parseLong((rowList[6]!=null?rowList[6]:"0").toString());

								row.add(allowName);
							
								//logger.info("IsActive" + IsActive);

								if(isActive!=IsActive)
								{



									row.add(allowStartDate);
								}
								else
								{
									StyleVO[] colorStyleVO1 = new StyleVO[2];
	
									//logger.info("red color is coming or not");
									colorStyleVO1[0] = new StyleVO();
									colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
									colorStyleVO1[0].setStyleValue("Black");
									colorStyleVO1[1] = new StyleVO();
									colorStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
									colorStyleVO1[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
	
	
									StyledData dataStyle1 = new StyledData();
									dataStyle1.setStyles(colorStyleVO1);
									dataStyle1.setData(allowEndDate);                   
									row.add(dataStyle1);


								//row.add(allowEndDate);
								}

							//row.add(allowStartDate);
							}
						else
						{
							row.add("-");
							row.add("-");
						}

						if(rowList1!=null)
						{
							deductionName = (rowList1[2]!=null?rowList1[2]:"0").toString();
							dednStartDate = (rowList1[3]!=null?rowList1[3]:"0").toString();

							dednEndDate = (rowList1[4]!=null?rowList1[4]:"-").toString();

							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

							if(rowList1[3]!=null)
							{
								dednStartDate=sdf.format(sdformat.parse(dednStartDate));
							}


							if(rowList1[4]!=null)
							{
								dednEndDate=sdf.format(sdformat.parse(dednEndDate));
							}


							IsActive = Long.parseLong((rowList1[6]!=null?rowList1[6]:"0").toString());

							row.add(deductionName);

							//logger.info("IsActive" + IsActive);


							if(isActive!=IsActive)
							{
								row.add(dednStartDate);	
							}
							else
							{


								StyleVO[] colorStyleVO1 = new StyleVO[2];

								colorStyleVO1[0] = new StyleVO();
								colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
								colorStyleVO1[0].setStyleValue("Black");
								colorStyleVO1[1] = new StyleVO();
								colorStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
								colorStyleVO1[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 


								StyledData dataStyle1 = new StyledData();
								dataStyle1.setStyles(colorStyleVO1);
								dataStyle1.setData(dednEndDate);                   
								row.add(dataStyle1);

								//row.add(dednEndDate); 

							}

						}
						else
						{
							row.add("-");
							row.add("-");
						}

						DataList.add(row);
						if((cnt%finalpagesize)==0) 
						{

							row = new ArrayList();
							row.add(new PageBreak());
							DataList.add(row);

						}
						cnt++;

						prevGrpId = currGrpId;
					}


					return DataList;
				}

				/*else if(RowList2.size()>0)
				{


					int count = RowList2.size()>RowList3.size()?RowList2.size():RowList3.size();

					logger.info("***Query for Deduction details**" +query2);
					logger.info("locationIdlocationIdlocationId" + locationId);

					logger.info("RowList2.size()....."+RowList2.size());
					logger.info("RowList3.size()....."+RowList3.size());
					int cnt=1;

					Iterator itr = RowList2.iterator();
					logger.info("RowList3.size()....."+RowList3.size());
					String allowName;
					String  MOnth ;
					String  YEar;
					String prevMonth="",prevYear="";
					Iterator itr1 = RowList3.iterator();
					String deductionName;

					String allowStartDate;
					String dednStartDate;

					String allowEndDate;
					String dednEndDate;

					String IsActive;
					String isActive="0";

					Object[] rowList2=null,rowList3=null;
					ArrayList row=null;
					for (int i=0;i<count;i++)
					{
						rowList2=null;
						rowList3=null;
						if(i<RowList2.size())
							rowList2 = (Object[]) RowList2.get(i);

						if(i<RowList3.size())
							rowList3 = (Object[]) RowList3.get(i);


						MOnth = (rowList2[2]!=null?rowList2[2]:"0").toString();
						YEar = (rowList2[3]!=null?rowList2[3]:"0").toString();


						logger.info("monthhhhhh**** " + MOnth);
						logger.info("YEarrrrrrrrr**** " + YEar);

						row= new ArrayList();
						if(!MOnth.equals(prevMonth) || !YEar.equals(prevYear))
						{
							row.add("Month: "+MOnth+"Year: "+YEar);

							//DataList.add(row);
						}
						row= new ArrayList();
						row.add(cnt);
						if(rowList2!=null)
						{
							allowName = (rowList2[1]!=null?rowList2[1]:"0").toString();

							allowStartDate = (rowList2[4]!=null?rowList2[4]:"0").toString();

							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
							allowStartDate=sdf.format(sdformat.parse(allowStartDate));

							IsActive = (rowList3[7]!=null?rowList3[7]:"0").toString();

							row.add(allowName);

							if(isActive==IsActive)
							{
								colorStyleVO[0] = new StyleVO();
								colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
								colorStyleVO[0].setStyleValue("Red");
								colorStyleVO[1] = new StyleVO();
								colorStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
								colorStyleVO[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
							}
							else
							{
								row.add(allowStartDate);
							}

						}
						else
						{
							row.add("-");
							row.add("-");
						}

						if(rowList3!=null)
						{
							deductionName = (rowList3[1]!=null?rowList3[1]:"0").toString();

							dednStartDate = (rowList3[4]!=null?rowList3[4]:"0").toString();

							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
							dednStartDate=sdf.format(sdformat.parse(dednStartDate));


							IsActive = (rowList3[7]!=null?rowList3[7]:"0").toString();


							row.add(deductionName);

							if(isActive==IsActive)
							{
								colorStyleVO[0] = new StyleVO();
								colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
								colorStyleVO[0].setStyleValue("Red");
								colorStyleVO[1] = new StyleVO();
								colorStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
								colorStyleVO[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
							}
							else
							{
								row.add(dednStartDate);
							}
						}
						else
						{
							row.add("-");
							row.add("-");
						}

						DataList.add(row);

						prevMonth= MOnth;
						prevYear = YEar;
						if((cnt%finalpagesize)==0) 
						{

							row = new ArrayList();
							row.add(new PageBreak());
							DataList.add(row);

						}
						cnt++;
					}


					

				}*/
				/*else
				{
					report.setReportName("No Record Found");
					ArrayList d = new ArrayList();
					ArrayList rr = new ArrayList();
					rr.add("");
					d.add(rr);
					TabularData td  = new TabularData(d);
					td.addStyle(IReportConstants.ADDL_HEADER_LOCATION, IReportConstants.VALUE_ADDL_HEADER_LOCATION_BELOW);
					td.addStyle(IReportConstants.BORDER, "No"); 
					report.setAdditionalHeader(td);
				}*/
				
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

	public ReportVO getUserReportVO( ReportVO report, Object criteria )
	throws ReportException
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
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

		if(  report.getReportCode().equals("3000004") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}

		if(  report.getReportCode().equals("3000004") )

		{            
			report.setParameterValue("Department",locationId+"");
		}
		return report;
	}
}

