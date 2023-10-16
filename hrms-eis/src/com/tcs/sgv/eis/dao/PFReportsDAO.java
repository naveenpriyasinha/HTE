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
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;

public class PFReportsDAO extends DefaultReportDataFinder 
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


	Object description = "";
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		String langName=report.getLangId();
		int finalpagesize=27;
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

		//String upperfooter="UNDER SECRETARY TO GOVT.";
		String upperfooter="UNDER SECRETARY TO GOVT.";
		String lowerfooter="GANDHINAGAR"+System.getProperty("line.separator")+"Code No. PG3";
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
		
		long paybillTypeId=Integer.parseInt(resourceBundle.getString("paybillTypeId"));
		long arrearbillTypeId=Integer.parseInt(resourceBundle.getString("arrearbillTypeId"));
		
		ArrayList DataList = new ArrayList(  );            
		StyleVO[] boldStyleVO = new StyleVO[2];
		boldStyleVO[0] = new StyleVO();
		boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
		boldStyleVO[1] = new StyleVO();
		boldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		boldStyleVO[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
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
			logger.info("getReportCode::"+report.getReportCode());
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
			if(report.getReportCode().equals("11") || report.getReportCode().equals("2500011") || report.getReportCode().equals("40") || report.getReportCode().equals("41") || report.getReportCode().equals("42"))
			{

				String billType=CheckIfNull(report.getParameterValue( "billTypePara" ));
				//billType="arrear";
				//if(billType.equals("") || billType.equals(" ") || billType.equals("-1"))
					//billType = String.valueOf(paybillTypeId);

				// Added by Akshay
				String fname = CheckIfNull(report.getParameterValue("FName"));
				String lname = CheckIfNull(report.getParameterValue("LName"));
				// Ended by Akshay
				long GradeCode4=Long.parseLong(constantsBundle.getString("GradeCode4"));

				// hardcoded
				long GradeCodeiii=Long.parseLong(constantsBundle.getString("GradeCode3"));
				long GPF_ADV=Long.parseLong(constantsBundle.getString("GPF_ADV"));
				long GradeCodeiv=Long.parseLong(constantsBundle.getString("GradeCode4"));
				//added by samir joshi for bill no wise report
				String accountType="";

				String BillNoinner="";//GAD specific
				BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
				accountType=CheckIfNull(report.getParameterValue( "Account Type" ));

				StringTokenizer st1 = new StringTokenizer(BillNoinner,"~");
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

				//ended by samir joshi/////        
				StringBuffer lsb = new StringBuffer(  );      

				String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
				String Department="";
				String Grade="";
				String Scale="";
				String Designation="";
				String month="";
				String year="";
				String GroupBy="";
				String ClassType="";
				ClassType=CheckIfNull(report.getParameterValue( "Class Type" ));
				GroupBy=CheckIfNull(report.getParameterValue( "Group By" ));
				Department=CheckIfNull(report.getParameterValue( "Department" ));
				if(Department.equals("")||Department.equals("-1"))
					Department=	locationId+"";	
				else
					locationId=Long.parseLong(Department);
				lstSignInfo = billDAO.getReportSignature(locationId);
				if(lstSignInfo.get(0)!=null)
				{

					objArry =(Object[]) lstSignInfo.get(0);
					desigName=objArry[0].toString();
					deptName=objArry[1].toString();
					cardexCode=objArry[2].toString();
					cityName=objArry[3].toString();            		
				}
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					finalpagesize=Integer.parseInt(noOfRec);
				}
				//for report footer
				ReportAttributeVO ravo = new ReportAttributeVO();

				ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
				ravo.setLocation(IReportConstants.LOCATION_FOOTER);
				ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
				String DeptName=locationNameincaps;
				ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

				//add the attribute to the report instance
				//report.addReportAttributeItem(ravo);
				ArrayList tblData = new ArrayList();

				ArrayList trow1 = new ArrayList();

				trow1.add(" "+System.getProperty("line.separator"));				
				tblData.add(trow1);//added first row of the tabular data


				ArrayList trow2 = new ArrayList();

				trow2.add(" "+System.getProperty("line.separator"));				
				tblData.add(trow2);//added second row of the tabular data


				ArrayList trow4 = new ArrayList();

				for(int c=0;c<(20);c++)
					trow4.add(new StyledData("&nbsp;",centerboldStyleVO1));
				trow4.add(new StyledData(desigName,centerboldStyleVO1));				
				tblData.add(trow4);//added second row of the tabular data

				ArrayList trow5 = new ArrayList();

				for(int c=0;c<(20);c++)
					trow5.add(new StyledData("&nbsp;",centerboldStyleVO1));
				trow5.add(new StyledData(deptName,centerboldStyleVO1));

				tblData.add(trow5);//added second row of the tabular data

				ArrayList trow3 = new ArrayList();

				//for(int c=0;c<(20);c++)
					//trow3.add(new StyledData("&nbsp;",centerboldStyleVO1));							
				//trow3.add(new StyledData(cityName,centerboldStyleVO1));				
				//tblData.add(trow3);//added third row of the tabular data

				ArrayList trow6 = new ArrayList();

				for(int c=0;c<(20);c++)
					trow6.add(new StyledData("&nbsp;",centerboldStyleVO1));		
				trow6.add(new StyledData(cardexCode,centerboldStyleVO1));
				tblData.add(trow6);//added sixth row of the tabular data

				TabularData tabularData = new TabularData(tblData);//initialize tabular data
				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
				tabularData.addStyle(IReportConstants.ALIGNMENT,"4");
				tabularData.addStyle(IReportConstants.BORDER, "No");			    				

				//report.setGrandTotalTemplate(tabularData);
				//report.setGroupByTotalTemplate(tabularData);
				//

				Grade=CheckIfNull(report.getParameterValue( "Grade" ));
				Scale=CheckIfNull(report.getParameterValue( "Scale" ));
				Designation=CheckIfNull(report.getParameterValue( "Designation" ));
				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				
				//Added By Mrugesh for Print all reports
				if(report.getReportCode().equals("40"))
					ClassType = constantsBundle.getString("classIV");
				
				if(report.getReportCode().equals("41"))
					ClassType = constantsBundle.getString("otherThanClassIV");
				
				if(report.getReportCode().equals("42"))
					ClassType = constantsBundle.getString("classIAS");
				//Ended by Mrugesh

				//if(ClassType.equals("IAS"))
				//	Grade=	constantsBundle.getString("AISGradeCode");  

				String demandNo = "";//CheckIfNull(report.getParameterValue("demandNo")); 
				//(String)requestAttributes.get("demandNo");
				String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
				String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
				String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
				String subHead = CheckIfNull(report.getParameterValue("subHead"));
				//String dtlHead = CheckIfNull(report.getParameterValue("dtlHead"));
				String subheadflag=subHead;
				logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
				//by manoj for head change
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				java.util.Date finYrDate = cal.getTime();
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
				java.util.Date endMonthDate = cal.getTime();

				String endDate  = sdfObj.format(endMonthDate);	            

				int currentMonthBill = 0;
				Date currentDate = new Date();

				if(currentDate.after(finYrDate) && currentDate.before(endMonthDate))
					currentMonthBill = 1;
				logger.info("testing for currentMonth "+currentMonthBill+" currentDate "+currentDate+" finYrDate "+finYrDate+" endMonthDate "+endMonthDate);

				//end by manoj for head change
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
					cal = Calendar.getInstance();			

					cal.set(Calendar.YEAR, Integer.parseInt(year));
					cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
					cal.set(Calendar.DAY_OF_MONTH, 1);

					java.util.Date startMonthDate = cal.getTime();
					String startDate  = sdfObj.format(startMonthDate);
					Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
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
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				java.util.Date date = cal.getTime();
				sdfObj = new SimpleDateFormat("MMM");

				String Month = sdfObj.format(date);
				
				DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
				sdfObj= sbConf.GetDateFormat();

				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

				java.util.Date startdate = cal.getTime();

				cal.set(Calendar.DAY_OF_MONTH, totalDays);

				endMonthDate = cal.getTime();
				String startDate= sdfObj.format(startdate);
				endDate  = sdfObj.format(endMonthDate);

				List<HrPaySalRevMst> arrearData = new ArrayList();
				

				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();
				String deptHeader="";
				String deptHeader1="";
				String deptHeader2="Amount deducted from the salary for the month of  "+Month+". "+year;
				String billNoHeader="Bill No:"+BillNo;
				String deptHeader3="";
				
				if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{/*
					String arrearStartDate =Month+". "+year;
					String arrearEndDate =Month+". "+year;
					arrearData = paybillDAO.getArrearData(startDate,endDate);
					if(arrearData!=null && arrearData.size()==1)
					{
						SimpleDateFormat sdfObj1 = new SimpleDateFormat("MMM yy");
						arrearStartDate=sdfObj1.format(arrearData.get(0).getRevEffcFrmDate());
						arrearEndDate=sdfObj1.format(arrearData.get(0).getRevEffcToDate());
					}				
					
				    deptHeader2="Arrear credited in GPF for the month from "+arrearStartDate+" to "+arrearEndDate+" ";

				*/}
				

				if(ClassType.equals("ClassIV"))
					deptHeader1="Schedule of GENERAL PROVIDENT FUND Deductions:-  -GPF of Class- IV";
				else if(ClassType.equals("otherThanIV"))
					deptHeader1="Schedule of GENERAL PROVIDENT FUND Deductions:GPF of other than Class- IV";
				else if(ClassType.equals("IAS"))
					deptHeader1="Schedule of GENERAL PROVIDENT FUND Deductions:GPF of I.A.S OFFICERS ";
				else
					deptHeader1="Schedule of GENERAL PROVIDENT FUND Deductions:-";
				
				if(ClassType.equals("ClassIV"))
					deptHeader3="Name of the office maintaining accounts: DIR.OF PENSION & P.F.AHMEDABAD  ";
				else
					deptHeader3="Name of the office maintaining accounts: Accountant General RAJKOT/AHMEDABAD";
				
				deptHeader=DeptName+System.getProperty("line.separator")+deptHeader1+System.getProperty("line.separator")+deptHeader2+System.getProperty("line.separator")+deptHeader3+System.getProperty("line.separator")+System.getProperty("line.separator");			
				
				if( BillNo != null && BillNo != "" )
				deptHeader+=billNoHeader;
				
				stData.add(new StyledData (deptHeader,headerStyleVo));
				styleList.add(stData);						

				TabularData tData  = new TabularData(styleList);
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");					
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);

				boolean flag=true;
				Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);

				Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
				ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");

				SessionFactory sessionFactory = serviceLocator.getSessionFactory();
				Session session= sessionFactory.getCurrentSession();	

				cal = Calendar.getInstance();cal.set(Calendar.YEAR, 2005);
				cal.set(Calendar.MONTH,(4-1));cal.set(Calendar.DAY_OF_MONTH, 1);
				String CPFDate  = sdfObj.format(cal.getTime());		

				String IsApproved = CheckIfNull(report.getParameterValue("Is Approved")); 
				String approveDate="";
				String approveEndDate="";
				if(IsApproved.equals("no"))
				{

					int yearBeforeApprove= Integer.parseInt(year);
					int monthBeforeApprove=Integer.parseInt(month);

					if(Integer.parseInt(month)==1)
					{	
						yearBeforeApprove--;	
						monthBeforeApprove=12;
					}	
					else
						monthBeforeApprove--;

					cal.set(Calendar.YEAR, yearBeforeApprove);
					cal.set(Calendar.MONTH,monthBeforeApprove-1);
					cal.set(Calendar.DAY_OF_MONTH, 1);


					approveDate  = sdfObj.format(cal.getTime());

					cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
					approveEndDate  = sdfObj.format(cal.getTime());

				}
				Session hibSession = sessionFactory.getCurrentSession();
				String query = " select dtl.hrEisEmpMst.empId ,";
				query+=" dtl.hrEisSgdMpg.hrEisGdMpg.orgDesignationMst.dsgnShrtName ,";
				query+=" dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.gradeId ,dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.gradeName, ";
				query+="dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";
				query+=" sum(pay.adv9670+pay.advIV9670), ";//adv rev
				query+=" dtl.hrEisSgdMpg.hrEisScaleMst.scaleStartAmt || '-' || dtl.hrEisSgdMpg.hrEisScaleMst.scaleIncrAmt || '-' ||dtl.hrEisSgdMpg.hrEisScaleMst.scaleEndAmt|| '-' ||dtl.hrEisSgdMpg.hrEisScaleMst.scaleHigherIncrAmt || '-' ||dtl.hrEisSgdMpg.hrEisScaleMst.scaleHigherEndAmt,";
				query+=" sum(pay.basic0101) ,";
				query+=" sum(pay.deduc9670+pay.deduc9620),  ";//gpf  
				query+=" sum(pay.deduc9531),  ";//gpf  iv
				// hardcoded
				
				
				if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{     
					query+=" 0,0, ";				
					}
				else
				{    
					query+="  ( ";
					query+="  select hploan.totalInst  from HrPayPaybillLoanDtls hploan where hploan.paybillId.id=pay.id and hploan.hrLoanAdvMst.loanAdvId = '"+GPF_ADV+"'  ";
					query+="  ), ";


					query+="  ( ";
					query+="  select hploan.recoveredInst  from HrPayPaybillLoanDtls hploan where hploan.paybillId.id=pay.id and hploan.hrLoanAdvMst.loanAdvId = '"+GPF_ADV+"'  ";
					query+="  ), ";
			
					}
				
				
				


				query+=" sum(pay.basic0102+pay.basic0101) ";
				query+=" ,trim(gpfDtls.gpfAccNo) ";
				query+=" ,sum(pay.deduc9999+pay.deduc9998) ";
     
				
				
				if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{     
					query+=" from HrPayArrearPaybill pay, ";				
					}
				else
				{    
					query+=" from HrPayPaybill pay, ";				
					}
				
				
				
				/*//by rahul w.r.t head change
				if(currentMonthBill!=1)
				{
					query+=(" PaybillHeadMpg bhm, ");
				}
				//by rahul
*/				query+=" HrEisOtherDtls dtl,";

				query+=" OrgUserpostRlt           USRPST, ";
				query+=" HrPayOrderHeadMpg ORDHD, ";
				query+=" HrPayOrderHeadPostMpg ORDPST, ";
				/*if(currentMonthBill!=1)
				{
					query+=" HrPayOrderHeadMpgHst ORDHD, ";
					query+=" HrPayOrderHeadPostMpgHst ORDPST, ";
				}
				else
				{
					query+=" HrPayOrderHeadMpg ORDHD, ";
					query+=" HrPayOrderHeadPostMpg ORDPST, ";
				}	 */

				query+=" OrgPostDetailsRlt pst, ";
				query+=" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm ";
				query+=" ,HrPayGpfBalanceDtls gpfDtls ";
				
				
				query+=" where  ";

				query+=" USRPST.orgUserMst.userId=gpfDtls.userId and ";	
				query+=" pay.orgPostMst.postId=ORDPST.postId and ";		
				//edited by ravysh
				if(!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
				query+="  bhm.billTypeId.lookupId = "+Long.parseLong(billType)+" and " ;
				
				
				
				query+="   dtl.hrEisEmpMst.orgEmpMst.empDoj < '"+CPFDate+"' and ";
				//query += "  ORDHD.subheadId in (select distinct hposm.element_code from HrPayOrderSubHeadMpg hposm,PaybillHeadMpg bhm ,HrPayPaybill pay where bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId and pay.paybillGrpId = bhm.hrPayPaybill )";
				query += "  ORDPST.orderHeadId = ORDHD.orderHeadId ";
				/*if(currentMonthBill!=1)
				{
					query += " and ORDPST.orderHeadId = ORDHD.id.orderHeadId ";
				}
				else
				{
					query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
				}*/
				query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
				query += " and USRPST.orgUserMst.userId = dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
				query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
				/*                query+=" and (USRPST.endDate is null or ";
	                    query+="  (USRPST.endDate<='"+startDate+"' and USRPST.endDate>='"+endDate+"' ))";*/ 
				//varun
				
/*				if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{     
					query+="  and bhm.approveFlag in (0,1)   ";
}
				else
				{    
*/					query+="  and bhm.approveFlag in (0,1) and ((pay.deduc9670+pay.deduc9620+pay.deduc9998+pay.deduc9999) > 0 or (pay.deduc9531+pay.deduc9998+pay.deduc9999>0) or (pay.adv9670+pay.advIV9670+pay.deduc9998+pay.deduc9999)>0 )   ";
				
					//}
				
				if(billType.equals(String.valueOf(arrearbillTypeId)))
				{
				String arrearType="";
				arrearType=CheckIfNull(report.getParameterValue( "Arrear List" ));
				if(!arrearType.equals("")&&!arrearType.equals("-1"))
				{
					query+=" and  pay.salRevId.salRevId="+arrearType+"  ";
				}
				}				
				

				query+=" and pay.hrEisEmpMst.empId=dtl.hrEisEmpMst.empId  ";

				if(!empid.equals("")&&!empid.equals("-1"))            	
					query+=" and dtl.hrEisEmpMst.empId = '"+empid+"'";


				if(!accountType.equals("") && !accountType.equals(" "))
				{
					query+=" and lower(trim(dtl.hrEisEmpMst.orgEmpMst.empGPFnumber)) like '"+accountType.toLowerCase()+"%'";
				}
				// Added by Akshay 
				if(!lname.equals("") && !lname.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"+lname.toLowerCase()+"%'");
				}

				if(!fname.equals("") && !fname.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"+fname.toLowerCase()+"%'");
				}
				// Ended by Akshay

				if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
				{
					query+=("  and ORDHD.subheadId  = '"+subHeadId+"'");
				}

				if(!Department.equals("")&&!Department.equals("-1"))
					query+=" and pst.cmnLocationMst.locId="+Department+"   ";
				if(isBillDefined&&!BillNo.equals(""))
				{

					//query+=" and   pay.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId ) and " ;	 
					/*if(currentMonthBill==1)
						query+=("   ORDHD.subheadId in (select bill.subheadId from HrPayBillHeadMpg bill where bill.billId ="+BillNo+" ) and  ");*/

					query+=" and  pst.orgPostMst.postId = pay.orgPostMst.postId  "; 
					query+=" and hpbsm.billId="+BillNo+"  ";
				}
				else
				{
					if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
					{
						query+=" and   ORDHD.subheadId ="+subheadCode+" ";
					}
					if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
					{
						query+="  and dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")";
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
				if(ClassType.equals("ClassIV"))
				{	
					query+=" and pay.deduc9534=0 and gpfDtls.orgGradeMst.gradeId = "+constantsBundle.getString("GradeCode4")+"   ";
				}	
				else if(ClassType.equals("otherThanIV"))
				{
					query+=" and pay.deduc9534=0 and gpfDtls.orgGradeMst.gradeId not in  ("+constantsBundle.getString("AISGradeCode")+","+constantsBundle.getString("GradeCode4")+") ";
				}	
				else if(ClassType.equals("IAS"))
				{
					query+=" and pay.deduc9534=0 and gpfDtls.orgGradeMst.gradeId = "+constantsBundle.getString("AISGradeCode")+" ";
				}	
				
				
				if(!Grade.equals("")&&!Grade.equals("-1"))            	
					query+=" and dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.gradeId  = '"+Grade+"'";

				if(!Scale.equals("")&&!Scale.equals("-1"))            	
					query+=" and dtl.hrEisSgdMpg.hrEisScaleMst.scaleId = '"+Scale+"'";

				if(!Designation.equals("")&&!Designation.equals("-1"))            	
					query+=" and dtl.hrEisSgdMpg.hrEisGdMpg.orgDesignationMst.dsgnId = '"+Designation+"'";

				if(!month.equals("")&&!month.equals("-1"))            	
					query+=" and bhm.month='"+month+"'";

				if(!year.equals("")&&!year.equals("-1"))            	
					query+=" and bhm.year= '"+year+"'";
				//if(currentMonthBill!=1)
				query+="  and bhm.hrPayPaybill = pay.paybillGrpId  and hpbsm.billHeadId = bhm.billNo.billHeadId  ";
				query+= " and bhm.orgGradeMst.gradeId=dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId "; //Date 5-Nov-08 by rahul

				query+=" group by ";
				
				if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{     
					
					//only pay.id is removed for arrear bill
					query+=" dtl.hrEisEmpMst.empId,trim(gpfDtls.gpfAccNo), ";
					}
				else
				{    
					query+=" dtl.hrEisEmpMst.empId,pay.id,trim(gpfDtls.gpfAccNo), ";
					
					}
				
				
				query+=" dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.gradeId, ";
				query+=" dtl.hrEisSgdMpg.hrEisGdMpg.orgDesignationMst.dsgnId, ";
				query+=" dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.gradeName, ";
				query+=" dtl.hrEisSgdMpg.hrEisGdMpg.orgDesignationMst.dsgnShrtName, ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.empLname,  ";
				query+=" dtl.hrEisSgdMpg.hrEisScaleMst.scaleStartAmt, dtl.hrEisSgdMpg.hrEisScaleMst.scaleHigherIncrAmt, dtl.hrEisSgdMpg.hrEisScaleMst.scaleHigherEndAmt,  ";
				query+=" dtl.hrEisSgdMpg.hrEisScaleMst.scaleIncrAmt,  ";
				query+=" dtl.hrEisSgdMpg.hrEisScaleMst.scaleEndAmt,USRPST.orgUserMst.userId  ";

				query+=" order by trim(gpfDtls.gpfAccNo),dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.gradeId,dtl.hrEisEmpMst.empId ";


				logger.info("***Query for PF Deduction details**" +query);


				Query sqlQuery = hibSession.createQuery(query);	 
				//Added by Rajan for checking no of records in query
				List qryResultDataList=new ArrayList();
				qryResultDataList=sqlQuery.list();
				if(qryResultDataList.size()!=0)
				{

					List RowList=qryResultDataList;
					logger.info("*************************"+RowList.size());

					int cnt=1;
					long empId=0;

					Iterator itr = RowList.iterator();

//					Added by Rajan for B/F & C/F in d report	    	          
					double finalpayTotal=0;
					double finalmonthsubscrTotal=0;
					double finaladvrecTotal=0;
					double finaltotalAmt=0;
					double finalColTotal=0;
					double finalDAGPFTotal=0;
//					Ended by Rajan	    	            
					double payTotal=0;
					double monthsubscrTotal=0;
					double daGPFTotal=0;
					double advrecTotal=0;
					double totalAmt=0;
					double grandPayTotal=0;
					double grandMonthsubscrTotal=0;
					double grandDAGPFTotal=0;
					double grandAdvrecTotal=0;

					String gpfPrefixPrev="";
					String gpfPrefixNext="";
					int grpByGpfAcc=1;
					//int grpByGpfAccPrev=1;
					int grpbyCountno=0;
					boolean grpchange=false;
					String monthsubscrTotalInWords="";
					//String
					final int pagesizePF=15;
					long grandTotal=0;
					///Added by rahul may or may not be in use
					ArrayList indexArray = new ArrayList();					
					int prevCounter,nextCounter;
					prevCounter=0;					
					ArrayList page1RecordRow = new ArrayList();

					//Ended  by rahul
					// int grouplistsize=1;
					while (itr.hasNext())
					{
						Object[] rowList = (Object[]) itr.next();
						long EmpId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());
						String designation = (String)(rowList[1]!=null?rowList[1]:"");
						long gradeId = Long.parseLong((rowList[2]!=null?rowList[2].toString():"").toString());
						String Class = (String)(rowList[3]!=null?rowList[3]:"");
						String Name = (String)(rowList[4]!=null?rowList[4]:"");
						if(ClassType.equals("IAS"))
							Name=Name+"(I.A.S.)";	
						double adv = Double.parseDouble((rowList[5]!=null?rowList[5]:"").toString());		            
						String  scale = (rowList[6]!=null?rowList[6]:"").toString();		            
						double pay = Double.parseDouble((rowList[7]!=null?rowList[7]:"").toString());		            
						double gpf = Double.parseDouble((rowList[8]!=null?rowList[8]:"0").toString());
						double gpfiv = Double.parseDouble((rowList[9]!=null?rowList[9]:"0").toString());
						long totalPrinInst = Long.parseLong((rowList[10]!=null?rowList[10].toString():"0").toString());
						//long totalIntInst = Long.parseLong((rowList[11]!=null?rowList[11].toString():"0").toString());
						long currentPrinInst = Long.parseLong((rowList[11]!=null?rowList[11].toString():"0").toString());
						//long currentIntInst = Long.parseLong((rowList[13]!=null?rowList[13].toString():"0").toString());
						double PE = Double.parseDouble((rowList[12]!=null?rowList[12]:"").toString());	





						String gpfAccNo=(String)(rowList[13]!=null?rowList[13]:"");
						double DAGPF = Double.parseDouble((rowList[14]!=null?rowList[14]:"").toString());	

						
						if(gpfAccNo.length()>0&&gpfAccNo.lastIndexOf("/")>0)
							gpfPrefixPrev= gpfAccNo.substring(0,gpfAccNo.lastIndexOf("/")).trim();

						if(grpbyCountno==finalpagesize || grpbyCountno%finalpagesize==0)
							pageNo++;
						if(!gpfPrefixNext.equals(gpfPrefixPrev)&&cnt>1)
						{	

							if(grpbyCountno<finalpagesize)
							{
								monthsubscrTotalInWords="Rupees  "+ConvertNumbersToWord.convert(Math.round(totalAmt))+" only.";
								logger.info("***********monthsubscrTotalInWords*************"+monthsubscrTotalInWords);
								ArrayList row = new ArrayList();
								row.add("");
								row.add("");
								StyledData dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData("TOTAL");                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(payTotal));                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(monthsubscrTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								///// added for DA GPF
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(daGPFTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(advrecTotal));                  
								row.add(dataStyle1);

								row.add("");                


								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(totalAmt));                  
								row.add(dataStyle1);

								logger.info(gpfPrefixNext+"    grpByGpfAcc"+gpfPrefixPrev+"       "+grpByGpfAcc+"                             cnt  "+cnt);



								row.add(grpByGpfAcc);
								DataList.add(row);
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
								dataStyle2.setData(monthsubscrTotalInWords);
								ReportColumnVO[] rptCol = report.getColumnsToDisplay(); 
								int totallength=rptCol.length;
								ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();

								int colspan=rptCol2.length;

								dataStyle2.setColspan(colspan);
								row1.add(dataStyle2);
								logger.info(totallength+"**************************"+colspan);
								for(int c=0;c<(8);c++)
									row1.add("");
								row1.add(grpByGpfAcc);
								//row.add(new PageBreak());
								//row.add("Data");


								DataList.add(row1);
								///Start by samir joshi for footer when group changes
								//-----------------BORDER FORMAT-------------------------
								StyleVO[] borderVO1 = new StyleVO[4];
								borderVO1[0] = new StyleVO();
								borderVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
								borderVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
								borderVO1[1] = new StyleVO();
								borderVO1[1].setStyleId(IReportConstants.BORDER);
								borderVO1[1].setStyleValue("No");
								borderVO1[2] = new StyleVO();
								borderVO1[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
								borderVO1[2].setStyleValue("Right");
								borderVO1[3] = new StyleVO();
								borderVO1[3].setStyleId(IReportConstants.STYLE_FONT_SIZE);
								borderVO1[3].setStyleValue("10");


								//Modified by rahul
								StyledData stampFooter=new StyledData();
								stampFooter.setStyles(borderVO1);
								stampFooter.setData(tabularData);
								ArrayList stampRow=new ArrayList();
								stampRow.add(tabularData);
								for(int c=0;c<(8);c++)
									stampRow.add("");								
								stampRow.add(grpByGpfAcc);
								DataList.add(stampRow);
								//ended by rahul
								//ensded by samir joshi  

							}
							//row.add(new PageBreak());
							//row.add("Data");	
							//  System.out.println("grpbyCountno=====>"+grpbyCountno+"grpByGpfAcc=====>"+grpByGpfAcc);
							else if(grpbyCountno>=finalpagesize)
							{
								logger.info("grpbyCountno=====>"+grpbyCountno+"grpByGpfAcc=====>"+grpByGpfAcc);
								ArrayList row = new ArrayList();
								StyledData dataStyle1 = new StyledData();
								row = new ArrayList();
								row.add("");
								row.add("");
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData("GRAND TOTAL");                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(grandPayTotal));                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(grandMonthsubscrTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(grandDAGPFTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								
								
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(grandAdvrecTotal));                  
								row.add(dataStyle1);

								row.add("");                


								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(grandMonthsubscrTotal+grandAdvrecTotal+grandDAGPFTotal));                  
								row.add(dataStyle1);

								logger.info(gpfPrefixNext+"    grpByGpfAcc"+gpfPrefixPrev+"       "+grpByGpfAcc+"                             cnt  "+cnt);



								row.add(grpByGpfAcc);
								//row.add(new PageBreak());
								//row.add("Data");
								DataList.add(row);
								ArrayList row1 = new ArrayList();


								StyleVO[] centerboldStyleVO = new StyleVO[2];
								centerboldStyleVO[0] = new StyleVO();
								centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
								centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
								centerboldStyleVO[1] = new StyleVO();
								centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
								centerboldStyleVO[1].setStyleValue("Left"); 

								centerboldStyleVO[1] = new StyleVO();
								centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
								centerboldStyleVO[1].setStyleValue("Left");


								StyledData dataStyle2 = new StyledData();
								dataStyle2.setStyles(centerboldStyleVO);
								dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(grandMonthsubscrTotal+grandAdvrecTotal+grandDAGPFTotal))+" only.");
								ReportColumnVO[] rptCol = report.getColumnsToDisplay(); 
								int totallength=rptCol.length;
								ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();

								int colspan=rptCol2.length;

								dataStyle2.setColspan(colspan);
								row1.add(dataStyle2);
								logger.info(totallength+"**************************"+colspan);
								for(int c=0;c<(8);c++)
									row1.add("");
								row1.add(grpByGpfAcc);
								//row.add(new PageBreak());
								//row.add("Data");


								DataList.add(row1);

								///Start by samir joshi for footer when group changes
								//-----------------BORDER FORMAT-------------------------
								StyleVO[] borderVO1 = new StyleVO[4];
								borderVO1[0] = new StyleVO();
								borderVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
								borderVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
								borderVO1[1] = new StyleVO();
								borderVO1[1].setStyleId(IReportConstants.BORDER);
								borderVO1[1].setStyleValue("No");
								borderVO1[2] = new StyleVO();
								borderVO1[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
								borderVO1[2].setStyleValue("Right");
								borderVO1[3] = new StyleVO();
								borderVO1[3].setStyleId(IReportConstants.STYLE_FONT_SIZE);
								borderVO1[3].setStyleValue("10"); 

								//Modified by rahul																
								StyledData footerStyle=new StyledData();
								//footerStyle.setStyles(centerboldStyleVO);
								footerStyle.setStyles(borderVO1);
								//footerStyle.setColspan(colspan);
								//footerStyle.setData(tabularData);

								tabularData.setStyles(borderVO1); 
								//tabularData.setStyles(borderVO1); 
								/*row=new ArrayList();
								row.add(footerStyle);
								for(int c=0;c<(8);c++)
									row.add("");
								row.add(grpByGpfAcc);
								DataList.add(row);*/
								//above code is commented by rahul
								ArrayList stampRow=new ArrayList();
								stampRow.add(tabularData);
								for(int c=0;c<(8);c++)
									stampRow.add("");
								stampRow.add(grpByGpfAcc);
								DataList.add(stampRow);

								//Ended by rahul

								/*tabularData.setStyles(borderVO1);
								row=new ArrayList();

								for(int c=0;c<(8);c++)
									row.add("");

								row.add(tabularData);
								row.add(grpByGpfAcc);
								DataList.add(row);*/

								//ensded by samir joshi     
							}


							//if(grpByGpfAccPrev!=grpByGpfAcc)
							{
								ArrayList pagebreakdata= new ArrayList();
								pagebreakdata.add(new PageBreak());
								pagebreakdata.add("Data");
//								added by rahul
								for(int c=0;c<(8);c++)
									pagebreakdata.add("");
								//ended by rahul
								DataList.add(pagebreakdata);

							}
							/*          		            grandMonthsubscrTotal+=monthsubscrTotal;
	    		            grandAdvrecTotal+=advrecTotal;
	    		            grandPayTotal+=payTotal;*/

							monthsubscrTotal=0;
							advrecTotal=0;
							totalAmt=0;
							payTotal=0;
							grandMonthsubscrTotal=0;
							grandAdvrecTotal=0;
							grandPayTotal=0;
							daGPFTotal=0;
							grandDAGPFTotal=0;
						}


						if(!gpfPrefixNext.equals(gpfPrefixPrev))
						{
							grpByGpfAcc++;	
							grpbyCountno=0;
							pageNo=1;
							//Added by Rajan for B/F & C/F in d report
							finalpayTotal=0;
							finalmonthsubscrTotal=0;
							finalDAGPFTotal=0;
							finaladvrecTotal=0;
							finaltotalAmt=0;
							finalColTotal=0;

							//Ended by Rajan
						}

						grpbyCountno++;


						ArrayList row = new ArrayList();
						StyledData dataStyle1 = new StyledData();
						//if(grpbyCountno<finalpagesize)
						logger.info("grpbyCountno::"+grpbyCountno);
						logger.info("finalpagesize::"+finalpagesize);
						logger.info("grpbyCountno%finalpagesize::"+grpbyCountno%finalpagesize);
						//if(grpbyCountno==1 || grpbyCountno%finalpagesize==0 )
						if(grpbyCountno==1  )
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							row = new ArrayList();

							logger.info("Pageno::"+pageNo);
							dataStyle1.setData("Page No :"+pageNo+"/"+pageNo); 
							ReportColumnVO[] rptCol21 = report.getColumnsToDisplay();
							int colspan1=rptCol21.length;
							row.add(dataStyle1);
							page1RecordRow.add(dataStyle1);
							dataStyle1.setColspan(colspan1);
							for(int c=0;c<(8);c++)
							{
								row.add("");
								page1RecordRow.add("");
							}
							DataList.add(row);							
							page1RecordRow = row;
						}

						row = new ArrayList();
						row.add(grpbyCountno);
						row.add(gpfAccNo);
						row.add(Name);
						if(gradeId!=GradeCodeiv&&gradeId!=GradeCodeiii)
							row.add(Math.round(pay));
						else
							row.add(Math.round(PE));
						if(gradeId!=GradeCodeiv)
							row.add(Math.round(gpf+gpfiv));//one of them will be zero
						else
							row.add(Math.round(gpfiv+gpf));//one of them will be zero
						row.add(DAGPF);
						row.add(adv);
						//if(currentIntInst!=0)		            	
						//row.add(""+currentIntInst+"/"+totalIntInst+"");
						//else
						row.add(""+currentPrinInst+"/"+totalPrinInst+"");	
						row.add(Math.round(adv+gpf+gpfiv+DAGPF));

						row.add(grpByGpfAcc);
						//row.add(new PageBreak());
						//row.add("Data");

						DataList.add(row);
						/*if(grpByGpfAccPrev!=grpByGpfAcc)
	    		            {
	    		                ArrayList pagebreakdata= new ArrayList();
	    		            	pagebreakdata.add(new PageBreak());
	    		                pagebreakdata.add("Data");
	    		            	DataList.add(pagebreakdata);

	    		            }*/
						if(gradeId!=GradeCodeiv&&gradeId!=GradeCodeiii)
							payTotal+=pay;
						else
							payTotal+=PE;


						monthsubscrTotal+=gpf+gpfiv;
						
						daGPFTotal+=DAGPF;

						advrecTotal+=adv;

						totalAmt+=gpf+gpfiv+adv+DAGPF;

						grandMonthsubscrTotal+=gpf+gpfiv;
						grandDAGPFTotal+=DAGPF;
						
						
						grandAdvrecTotal+=adv;
						if(gradeId!=GradeCodeiv&&gradeId!=GradeCodeiii)
							grandPayTotal+=pay;
						else
							grandPayTotal+=PE;
						// grandPayTotal+=payTotal;

						logger.info("the counter value is=====>"+cnt+"grpbyCountno===>"+grpbyCountno+"groyup no===>"+grpByGpfAcc);
//						Added by rajan for adding G/F n C/F in d report
						if(grpbyCountno%finalpagesize==0 || cnt==RowList.size() )
						{
							if( cnt!=(RowList.size()))
							{
								//monthsubscrTotalInWords=ConvertNumbersToWord.convert(Math.round(monthsubscrTotal));
								monthsubscrTotalInWords="Rupees  "+ConvertNumbersToWord.convert(Math.round(totalAmt))+" only.";
								logger.info("***********monthsubscrTotalInWords*************"+monthsubscrTotalInWords);
								row = new ArrayList();
								row.add("");
								row.add("");
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData("B/F");                  
								row.add(dataStyle1);

								finalpayTotal+=payTotal;

								finalmonthsubscrTotal+=monthsubscrTotal;
								finalDAGPFTotal+=daGPFTotal;
								

								finaladvrecTotal+=advrecTotal;
								finaltotalAmt+=totalAmt;

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(finalpayTotal)); 

								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(finalmonthsubscrTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(finalDAGPFTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								//row.add("");                

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(finaladvrecTotal));                  
								row.add(dataStyle1);

								row.add("");                

								finalColTotal = finalColTotal + (monthsubscrTotal+advrecTotal+daGPFTotal);
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(finalColTotal));                  
								row.add(dataStyle1);

								logger.info(gpfPrefixNext+"    grpByGpfAcc"+gpfPrefixPrev+"       "+grpByGpfAcc+"                             cnt  "+cnt);



								row.add(grpByGpfAcc);

								DataList.add(row);



								if( cnt!=(RowList.size()))
								{
									ArrayList pagebreakdata= new ArrayList();
									pagebreakdata.add(new PageBreak());
									pagebreakdata.add("Data");
//									added by rahul
									for(int c=0;c<(8);c++)
										pagebreakdata.add("");
									//ended by rahul
									DataList.add(pagebreakdata);

								}
								//Added by rahul
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
								logger.info("First indexOf(page1RecordRow: 1/1)::"+DataList.lastIndexOf(page1RecordRow));
								indexArray.add(DataList.lastIndexOf(page1RecordRow));
								// Ended code to find last index of PageNo: 1/1
								int index = DataList.lastIndexOf(pageNoRow);
								indexArray.add(index);								
								logger.info("First indexOf(pageNoRow)::"+DataList.indexOf(pageNoRow));
								logger.info("DataList.indexOf(row) last Index::"+index);
								logger.info("Row::"+DataList.get(index));
								ArrayList tempArray = new ArrayList();
								tempArray = (ArrayList)DataList.get(index);
								logger.info("Row from tempArray::"+tempArray.get(0).toString());
								String strPageNo = "";
								logger.info("indexArray.size::"+indexArray.size());
								logger.info("tempPageCount::"+tempPageCount);
								logger.info("prevCounter::"+prevCounter);
								logger.info("nextCounter::"+nextCounter);


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
									dataStyle1.setStyles(boldStyleVO);
									dataStyle1.setData(strPageNo);
									dataStyle1.setColspan(colspan1);
									tempArray.set(0, dataStyle1);
									DataList.set(tempIndex, tempArray);
								}
								//Code to show pageno ended by rahul
								//Ended by rahul

								row = new ArrayList();
								row.add("");
								row.add("");
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData("C/F");                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(finalpayTotal));                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(finalmonthsubscrTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(finalDAGPFTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								//row.add("");                

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(finaladvrecTotal));                  
								row.add(dataStyle1);

								row.add("");                


								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(finalColTotal));                  
								row.add(dataStyle1);

								logger.info(gpfPrefixNext+"    grpByGpfAcc"+gpfPrefixPrev+"       "+grpByGpfAcc+"                             cnt  "+cnt);



								row.add(grpByGpfAcc);

								DataList.add(row);
							}

							else if(accountType.equalsIgnoreCase("")==true || (grpbyCountno>=1 && grpbyCountno<finalpagesize))
							{

								monthsubscrTotalInWords="Rupees  "+ConvertNumbersToWord.convert(Math.round(totalAmt))+" only.";
								logger.info("***********monthsubscrTotalInWords*************"+monthsubscrTotalInWords);
								row = new ArrayList();
								row.add("");
								row.add("");
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData("TOTAL");                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(payTotal));                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(monthsubscrTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(daGPFTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								//row.add("");                

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(advrecTotal));                  
								row.add(dataStyle1);

								row.add("");                


								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(totalAmt));                  
								row.add(dataStyle1);

								logger.info(gpfPrefixNext+"    grpByGpfAcc"+gpfPrefixPrev+"       "+grpByGpfAcc+"                             cnt  "+cnt);



								row.add(grpByGpfAcc);
								DataList.add(row);
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
								dataStyle2.setData(monthsubscrTotalInWords);
								ReportColumnVO[] rptCol = report.getColumnsToDisplay(); 
								int totallength=rptCol.length;
								ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();

								int colspan=rptCol2.length;

								dataStyle2.setColspan(colspan);
								row1.add(dataStyle2);
								logger.info(totallength+"**************************"+colspan);
								for(int c=0;c<(8);c++)
									row1.add("");
								row1.add(grpByGpfAcc);
								//row.add(new PageBreak());
								//row.add("Data");


								DataList.add(row1);
								///Start by samir joshi for footer when group changes
								//-----------------BORDER FORMAT-------------------------
								StyleVO[] borderVO1 = new StyleVO[4];
								borderVO1[0] = new StyleVO();
								borderVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
								borderVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
								borderVO1[1] = new StyleVO();
								borderVO1[1].setStyleId(IReportConstants.BORDER);
								borderVO1[1].setStyleValue("No");
								borderVO1[2] = new StyleVO();
								borderVO1[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
								borderVO1[2].setStyleValue("Right");
								borderVO1[3] = new StyleVO();
								borderVO1[3].setStyleId(IReportConstants.STYLE_FONT_SIZE);
								borderVO1[3].setStyleValue("10"); 

								StyledData footerStyle=new StyledData();
								footerStyle.setColspan(colspan);

								tabularData.setColspan(colspan);
								tabularData.setStyles(borderVO1);
								row=new ArrayList();

								row.add(tabularData);
								for(int c=0;c<(8);c++)
									row.add("");


								row.add(grpByGpfAcc);
								DataList.add(row);

								//ensded by samir joshi   
								logger.info("11111grpbyCountno=====>"+grpbyCountno+"grpByGpfAcc=====>"+grpByGpfAcc);


							}

//							Ended by rajan	    		       

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
							ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();

							int colspan=rptCol2.length;

							dataStyle2.setColspan(colspan);
							row1.add(dataStyle2);
							logger.info(totallength+"**************************"+colspan);
							for(int c=0;c<(7);c++)
								row1.add("");
							row1.add(grpByGpfAcc);
							//row.add(new PageBreak());
							//row.add("Data");


							DataList.add(row1);
							/*if( cnt!=(RowList.size()))
	    		            {
	    		                pagebreakdata= new ArrayList();
	    		            	pagebreakdata.add(new PageBreak());
	    		                pagebreakdata.add("Data");
	    		            	DataList.add(pagebreakdata);

	    		            }*/
							/*         		          if(!accountType.equals("") && !accountType.equals(" "))
	    		          {
	      		            grandMonthsubscrTotal+=gpf+gpfiv;
	        		            grandAdvrecTotal+=adv;
	        		            if(gradeId!=GradeCodeiv&&gradeId!=GradeCodeiii)
	        		            	grandPayTotal+=pay;
	        			            else
	        			            	grandPayTotal+=PE;
	    		          }
	    		            grandMonthsubscrTotal+=monthsubscrTotal;
	    		            grandAdvrecTotal+=advrecTotal;
	    		            grandPayTotal+=payTotal;*/
							monthsubscrTotal=0;
							advrecTotal=0;
							totalAmt=0;
							daGPFTotal=0;

							payTotal=0;

						}

						if(grpbyCountno%finalpagesize==0)
						{
							grpByGpfAcc++;


						}
						if(!accountType.equals("") && !accountType.equals(" ") && cnt>=finalpagesize)
						{
							if(cnt==RowList.size())	
							{

								row = new ArrayList();
								dataStyle1 = new StyledData();
								row = new ArrayList();

								row.add("");
								row.add("");
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData("GRAND TOTAL");                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(grandPayTotal));                  
								row.add(dataStyle1);




								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(grandMonthsubscrTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(grandDAGPFTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
								row.add(dataStyle1);

								//row.add("");                

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(grandAdvrecTotal));                  
								row.add(dataStyle1);

								row.add("");                


								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(Math.round(grandMonthsubscrTotal+grandAdvrecTotal+grandDAGPFTotal));                  
								row.add(dataStyle1);




								row.add(grpByGpfAcc);

								DataList.add(row);
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
								dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(grandMonthsubscrTotal+grandAdvrecTotal))+" only.");
								ReportColumnVO[] rptCol = report.getColumnsToDisplay(); 
								int totallength=rptCol.length;
								ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
								int colspan=rptCol2.length;

								dataStyle2.setColspan(colspan);
								row1.add(dataStyle2);
								for(int c=0;c<(8);c++)
									row1.add("");
								row1.add(grpByGpfAcc);
								//row.add(new PageBreak());
								//row.add("Data");


								DataList.add(row1);
							}
						}

						cnt++;
						gpfPrefixNext=gpfPrefixPrev.trim();

					}



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
				}				

				return DataList;



			}


		}
		catch(Exception e)
		{
			logger.error("Error in ResourceMoniteringDAO" + e.getMessage());
			logger.error("Printing StackTrace");
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

		if(  report.getReportCode().equals("3")|| report.getReportCode().equals("2500003") ||report.getReportCode().equals("6")|| report.getReportCode().equals("2500006") || report.getReportCode().equals("7") || report.getReportCode().equals("2500007") || report.getReportCode().equals("8") || report.getReportCode().equals("2500008") || report.getReportCode().equals("10") || report.getReportCode().equals("2500010")|| report.getReportCode().equals("11") || report.getReportCode().equals("2500011") || report.getReportCode().equals("13") || report.getReportCode().equals("2500013")|| report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("5") || report.getReportCode().equals("2500005") || report.getReportCode().equals("9") || report.getReportCode().equals("2500009"))
		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
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
