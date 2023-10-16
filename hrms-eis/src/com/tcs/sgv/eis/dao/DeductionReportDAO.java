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
import com.tcs.sgv.common.valuebeans.reports.ReportParameterVO;
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

public class DeductionReportDAO extends DefaultReportDataFinder 
implements ReportDataFinder{


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

		String upperfooter="UNDER SECRETARY TO GOVT.";
		String lowerfooter="GANDHINAGAR"+System.getProperty("line.separator")+"Code No. PG3";
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
		long paybillTypeId=Integer.parseInt(resourceBundle.getString("paybillTypeId"));
		long arrearbillTypeId=Integer.parseInt(resourceBundle.getString("arrearbillTypeId"));
		long multipleMonthSupplBill=Integer.parseInt(resourceBundle.getString("multipleMonthSupplBill"));

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

		//Added by Urvin for setting up font size
		StyleVO[] baseFont = new StyleVO[1];
		baseFont[0] = new StyleVO(  );
		baseFont[0].setStyleId( IReportConstants.STYLE_FONT_SIZE );
		baseFont[0].setStyleValue( "13" );


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
		isBillDefined = true; 
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

			if(report.getReportCode().equals("3") || report.getReportCode().equals("2500003") || report.getReportCode().equals("30") || report.getReportCode().equals("2500030") || report.getReportCode().equals("31") || report.getReportCode().equals("2500031") || report.getReportCode().equals("32") || report.getReportCode().equals("2500032") || report.getReportCode().equals("33") || report.getReportCode().equals("2500033") || report.getReportCode().equals("34") || report.getReportCode().equals("2500034")|| report.getReportCode().equals("38") || report.getReportCode().equals("2500038")|| report.getReportCode().equals("39") || report.getReportCode().equals("2500039"))
			{


				// Added by Akshay
				String fname = CheckIfNull(report.getParameterValue("FName"));
				String lname = CheckIfNull(report.getParameterValue("LName"));
				//  Ended by Akshay

				String billType="";


				billType=CheckIfNull(report.getParameterValue( "billTypePara" ));
				//billType="arrear";
				//if(billType.equals("") || billType.equals(" ") || billType.equals("-1"))
				//	billType = String.valueOf(paybillTypeId);
				
				// hardcoded
				long AISGradeCode=Long.parseLong(constantsBundle.getString("AISGradeCode"));

				StringBuffer lsb = new StringBuffer(  );      

				String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
				String Department="";
				String Grade="";
				String Scale="";
				String Designation="";
				String month="";
				String year="";
				String GroupBy="";
				String ReportType="";
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
				//added by samir joshi for bill no wise report
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				logger.info(" nof rec out====>"+noOfRec);
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					logger.info("No Of Rec is*****************====>"+noOfRec);
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
					l++;
				} 
				String DeptName=locationNameincaps;
				//ended by samir joshi/////
				//for report footer
				/*	            ReportAttributeVO ravo = new ReportAttributeVO();

	    	    		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
	    	    		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
	    	    		ravo.setAlignment(IReportConstants.ALIGN_RIGHT);

	    	    		ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

	                    //add the attribute to the report instance
	    	    		report.addReportAttributeItem(ravo);*/
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

				ArrayList tblData = new ArrayList();

				ArrayList trow1 = new ArrayList();

				trow1.add(" ");
				trow1.add(" ");
				trow1.add(" "+System.getProperty("line.separator"));
				tblData.add(trow1);//added first row of the tabular data


				ArrayList trow2 = new ArrayList();

				trow2.add(" ");
				trow2.add(" ");
				trow2.add(" "+System.getProperty("line.separator"));
				tblData.add(trow2);//added second row of the tabular data


				ArrayList trow4 = new ArrayList();

				trow4.add(" ");
				trow4.add(" ");
				trow4.add(new StyledData(desigName,centerboldStyleVO1));
				tblData.add(trow4);//added second row of the tabular data


				ArrayList trow5 = new ArrayList();

				trow5.add(" ");
				trow5.add(" ");
				//trow5.add(new StyledData(deptName,centerboldStyleVO1));
				trow5.add(new StyledData(deptName,centerboldStyleVO1));
				tblData.add(trow5);//added second row of the tabular data

				ArrayList trow3 = new ArrayList();

				//trow3.add(" ");
				//trow3.add(" ");
				//trow3.add(new StyledData(cityName,centerboldStyleVO1));
				//trow3.add(new StyledData(cityName,centerboldStyleVO1));
				//tblData.add(trow3);//added third row of the tabular data

				ArrayList trow6 = new ArrayList();
				trow6.add(" ");
				trow6.add(" ");
				//trow6.add(new StyledData(cardexCode,centerboldStyleVO1));
				trow6.add(new StyledData(cardexCode,centerboldStyleVO1));
				tblData.add(trow6);//added sixth row of the tabular data

				TabularData tabularData = new TabularData(tblData);//initialize tabular data
				tabularData.addStyle(IReportConstants.FONT_WEIGHT, IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_SIZE_XXLARGE);
				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
				tabularData.addStyle(IReportConstants.FONT_WEIGHT, IReportConstants.VALUE_FONT_WEIGHT_BOLD);
				tabularData.addStyle(IReportConstants.BORDER, "No"); 

				report.setGrandTotalTemplate(tabularData);
				report.setGroupByTotalTemplate(tabularData);
				//
				Grade=CheckIfNull(report.getParameterValue( "Grade" ));
				Scale=CheckIfNull(report.getParameterValue( "Scale" ));
				Designation=CheckIfNull(report.getParameterValue( "Designation" ));
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

				String demandNo = "";//CheckIfNull(report.getParameterValue("demandNo")); 
				//(String)requestAttributes.get("demandNo");
				String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
				String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
				String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
				String subHead = CheckIfNull(report.getParameterValue("subHead"));
				//String dtlHead = CheckIfNull(report.getParameterValue("dtlHead"));
				String subheadflag=subHead;
				logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);

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
				sdfObj = new SimpleDateFormat("MMM");
				String Month = sdfObj.format(startMonthDate);

				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();
				//ArrayList styleList = new ArrayList();
				//ArrayList stData = new ArrayList();
				logger.info("The report type is------>>>>"+ReportType);

				//  Added By  Maruthi for Back button issue.
				if(report.getReportCode().equals("3") || report.getReportCode().equals("2500003")){
					if(ReportType.equals("")||ReportType.equals("-1")) 
					{
						if( BillNo != null && BillNo != "" )
						{
							//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
							String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Total Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
							stData.add(new StyledData (deptHeader,headerStyleVo));
							styleList.add(stData);

						}
						else
						{
							//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
							String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Total Deductions for the month of  "+Month+". "+year;
							stData.add(new StyledData (deptHeader,headerStyleVo));
							styleList.add(stData);

						}
						TabularData tData  = new TabularData(styleList);
						//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
						//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
						//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGE);
						tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
						tData.addStyle(IReportConstants.BORDER, "No");
						//tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
						tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
						report.setAdditionalHeader(tData);
					}
				}
				//ENDED BY MARUTHI.

				//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Total Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo); 
				//Added By Mrugesh
				String deducType = null;
				if(report.getReportCode().equals("30") || report.getReportCode().equals("2500030"))
					deducType = constantsBundle.getString("miscellaneous");

				if(report.getReportCode().equals("31") || report.getReportCode().equals("2500031"))
					deducType = constantsBundle.getString("insurence");

				if(report.getReportCode().equals("32") || report.getReportCode().equals("2500032"))
					deducType = constantsBundle.getString("profTax");

				if(report.getReportCode().equals("33") || report.getReportCode().equals("2500033"))
					deducType = constantsBundle.getString("deduction");

				if(report.getReportCode().equals("34") || report.getReportCode().equals("2500034"))
					deducType = constantsBundle.getString("incomeTax"); 

				if(report.getReportCode().equals("38") || report.getReportCode().equals("2500038"))
					deducType = constantsBundle.getString("sis1979");

				if(report.getReportCode().equals("39") || report.getReportCode().equals("2500039"))
					deducType = constantsBundle.getString("PayOfRecovery");

				if(deducType!=null && deducType!="")
					ReportType = deducType;

				//Ended
				if(!ReportType.equals("")&&!ReportType.equals("-1"))
				{
					if(ReportType.equals("Misc") || ReportType.equals("PayRec"))
					{

						//Added By  Maruthi for Back button issue.

						if(!ReportType.equals("")||ReportType.equals("-1")) 
						{
							if( BillNo != null && BillNo != "" )
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
								String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of MISCELLANEOUS Recovery for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
								stData.add(new StyledData (deptHeader,headerStyleVo));
								styleList.add(stData);

							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of MISCELLANEOUS Recovery for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
								stData.add(new StyledData (deptHeader,headerStyleVo));
								styleList.add(stData);

							}
							TabularData tData  = new TabularData(styleList);
							//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
							//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
							//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGE);
							tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
							tData.addStyle(IReportConstants.BORDER, "No");
							//tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
							tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
							report.setAdditionalHeader(tData);
						}  

						//Ended By Maruthi.   
						//if(!BillNo.equals(""))	
						// report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of MISCELLANEOUS Recovery for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
						// else
						//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of MISCELLANEOUS Recovery for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));  
					}
					else if(ReportType.equals("Insurance"))
					{

						logger.info(Grade+"equals("+AISGradeCode);
						if(Long.parseLong(Grade)==AISGradeCode)
						{
//							Added By  Maruthi for Back button issue.
							if(!ReportType.equals("")||ReportType.equals("-1")) 
							{

								if( BillNo != null && BillNo != "" )
								{
									//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
									String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Total Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
									stData.add(new StyledData (deptHeader,headerStyleVo));
									styleList.add(stData);

								}
								else
								{
									//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
									String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Total Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
									stData.add(new StyledData (deptHeader,headerStyleVo));
									styleList.add(stData);

								}
								TabularData tData  = new TabularData(styleList);
								//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
								//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
								//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGE);
								tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
								tData.addStyle(IReportConstants.BORDER, "No");
								//tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
								tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
								report.setAdditionalHeader(tData);
							} 
							//Ended By Maruthi.	 
							//if(!BillNo.equals(""))
							// report.setReportName(DeptName+System.getProperty("line.separator")+"Statement showing the Deductions of A.I.S. officer's Group Insurance Scheme for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);	
							//else
							// report.setReportName(DeptName+System.getProperty("line.separator")+"Statement showing the Deductions of A.I.S. officer's Group Insurance Scheme for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
						}
						else
						{


							//Added By  Maruthi for Back button issue.
							if(!ReportType.equals("")||ReportType.equals("-1")) 
							{
								if( BillNo != null && BillNo != "" )
								{
									//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
									String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Insurance Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
									stData.add(new StyledData (deptHeader,headerStyleVo));
									styleList.add(stData);

								}
								else
								{
									//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
									String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Insurance Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
									stData.add(new StyledData (deptHeader,headerStyleVo));
									styleList.add(stData);

								}
								TabularData tData  = new TabularData(styleList);
								//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
								//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
								//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGE);
								tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
								tData.addStyle(IReportConstants.BORDER, "No");
								//tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
								tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
								report.setAdditionalHeader(tData);
							}	 
							//Ended By Maruthi.	 

							//if(!BillNo.equals(""))
							// report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Insurance Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
							//else
							// report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Insurance Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));	 
						}
					}
					else if(ReportType.equals("PT"))
					{

						//	Added By  Maruthi for Back button issue.
						if(!ReportType.equals("")||ReportType.equals("-1")) 
						{

							if( BillNo != null && BillNo != "" )
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
								String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of PROFESSIONAL TAX Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
								stData.add(new StyledData (deptHeader,headerStyleVo));
								styleList.add(stData);

							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of PROFESSIONAL TAX Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
								stData.add(new StyledData (deptHeader,headerStyleVo));
								styleList.add(stData);

							}
							TabularData tData  = new TabularData(styleList);
							//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
							//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
							//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGE);
							tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
							tData.addStyle(IReportConstants.BORDER, "No");
							//tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
							tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
							report.setAdditionalHeader(tData);
						}
						//Ended By Maruthi.
						// if(!BillNo.equals(""))
						// report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of PROFESSIONAL TAX Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
						//else
						// report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of PROFESSIONAL TAX Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));	 
					}
					else if(ReportType.equals("Deduction"))
					{
						//Added By  Maruthi for Back button issue.
						if(!ReportType.equals("")||ReportType.equals("-1")) 
						{ 
							if( BillNo != null && BillNo != "" )
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
								String deptHeader=DeptName+System.getProperty("line.separator")+"CHECKING List for the month of "+Month+". "+year+System.getProperty("line.separator")+"****Not to be attached with paybill****"+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
								stData.add(new StyledData (deptHeader,headerStyleVo));							             
								styleList.add(stData);

							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								String deptHeader=DeptName+System.getProperty("line.separator")+"CHECKING List for the month of "+Month+". "+year+System.getProperty("line.separator")+"****Not to be attached with paybill****"+System.getProperty("line.separator")+System.getProperty("line.separator");
								stData.add(new StyledData (deptHeader,headerStyleVo));
								styleList.add(stData);
							}
							TabularData tData  = new TabularData(styleList);
							//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
							//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
							//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGE);
							tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
							tData.addStyle(IReportConstants.BORDER, "No");
							//tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
							tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
							report.setAdditionalHeader(tData);
						}
						//Ended By Maruthi.
						//if(!BillNo.equals(""))
						// report.setReportName(DeptName+System.getProperty("line.separator")+"CHECKING List for the month of "+Month+". "+year+System.getProperty("line.separator")+"****Not to be attached with paybill****"+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
						//else
						// report.setReportName(DeptName+System.getProperty("line.separator")+"CHECKING List for the month of "+Month+". "+year+System.getProperty("line.separator")+"****Not to be attached with paybill****"+System.getProperty("line.separator")+System.getProperty("line.separator"));	 
					}
					else if(ReportType.equals("IT"))
					{

//						//Added By  Maruthi for Back button issue.

						
						String TANno= paybillDAO.getTANDtlsbyMonthLocId(Integer.parseInt(month),Integer.parseInt(year),locationId);
						
						if(!ReportType.equals("")||ReportType.equals("-1")) 
						{
							if( BillNo != null && BillNo != "" )
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
								String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Income TAX Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+"TAN : "+TANno+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
								stData.add(new StyledData (deptHeader,headerStyleVo));							             
								styleList.add(stData);

							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Income TAX Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+"TAN : "+TANno+System.getProperty("line.separator")+System.getProperty("line.separator");
								stData.add(new StyledData (deptHeader,headerStyleVo));
								styleList.add(stData);

							}
							TabularData tData  = new TabularData(styleList);
							//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
							//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
							//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
							tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
							tData.addStyle(IReportConstants.BORDER, "No");
							//tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
							tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
							report.setAdditionalHeader(tData);
						}
					}
					else if(ReportType.equals("SIS1979"))
					{

//						//Added By  Maruthi for Back button issue.

						if(!ReportType.equals("")||ReportType.equals("-1")) 
						{
							if( BillNo != null && BillNo != "" )
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
								String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Insurance Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
								stData.add(new StyledData (deptHeader,headerStyleVo));							             
								styleList.add(stData);

							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Insurance Deductions for the month of  "+Month+". "+year;
								stData.add(new StyledData (deptHeader,headerStyleVo));
								styleList.add(stData);							    				 
							}
							TabularData tData  = new TabularData(styleList);
							//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
							//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
							//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGE);
							tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
							tData.addStyle(IReportConstants.BORDER, "No");
							//tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
							tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
							report.setAdditionalHeader(tData);
						}
						//Ended By Maruthi.
						//if(!BillNo.equals(""))
						//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Income TAX Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+"TAN:"+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
						//else
						// report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Income TAX Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+"TAN:"+System.getProperty("line.separator")+System.getProperty("line.separator"));	 
					}	
				}
				ReportColumnVO[] rptCol = report.getReportColumns(); 
				ReportParameterVO[] parVO = report.getParameters();
				ReportColumnVO clms[] = report.getLeafColumns();

				int x=0;
				int y=0;
				x=clms.length;
				logger.info("column length is=====>"+clms.length);
				y=x;
				int o=0;
				/*  for(int col=0;col<x;col++)
	    	            {
	    	            	if(clms[col].getHidden().equalsIgnoreCase("y"))
	    	            		o++;
	    	            		y--;
	    	            logger.info(clms[col]+"************inside************************************************"+col+"hide column list===>"+o);
	    	            }*/

				ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
				logger.info(y+"**********"+rptCol2.length);
				int colspan=rptCol2.length;
				for(int t =0;t<rptCol2.length;t++)
				{

					logger.info(t+"**********"+rptCol2[t].getColumnId()+"*****"+rptCol2[t].getHidden());


					if(!ReportType.equals("")&&!ReportType.equals("-1"))
					{
						if(ReportType.equals("Misc") || ReportType.equals("PayRec"))
						{
							if(rptCol2[t].getColumnId()==13||rptCol2[t].getColumnId()==1||rptCol2[t].getColumnId()==14||rptCol2[t].getColumnId()==15||rptCol2[t].getColumnId()==16||rptCol2[t].getColumnId()==17||rptCol2[t].getColumnId()==19||rptCol2[t].getColumnId()==20||rptCol2[t].getColumnId()==21)
								colspan--;
						}
						else if(ReportType.equals("Insurance"))
						{



							if(rptCol2[t].getColumnId()==13||rptCol2[t].getColumnId()==1||rptCol2[t].getColumnId()==14||rptCol2[t].getColumnId()==15||rptCol2[t].getColumnId()==18||rptCol2[t].getColumnId()==19)
								colspan--;
						}
						else if(ReportType.equals("SIS1979"))
						{
							if(rptCol2[t].getColumnId()==18||rptCol2[t].getColumnId()==13||rptCol2[t].getColumnId()==1||rptCol2[t].getColumnId()==14||rptCol2[t].getColumnId()==15||rptCol2[t].getColumnId()==16||rptCol2[t].getColumnId()==17||rptCol2[t].getColumnId()==19||rptCol2[t].getColumnId()==20||rptCol2[t].getColumnId()==21)
								colspan--;

						}
						else if(ReportType.equals("PT"))
						{
							if(rptCol2[t].getColumnId()==13||rptCol2[t].getColumnId()==21||rptCol2[t].getColumnId()==22||rptCol2[t].getColumnId()==1||rptCol2[t].getColumnId()==14||rptCol2[t].getColumnId()==16||rptCol2[t].getColumnId()==17||rptCol2[t].getColumnId()==18||rptCol2[t].getColumnId()==19)
								colspan--;
						}
						else if(ReportType.equals("Deduction"))
						{
							if(rptCol2[t].getColumnId()==13||rptCol2[t].getColumnId()==1||rptCol2[t].getColumnId()==15||rptCol2[t].getColumnId()==14||rptCol2[t].getColumnId()==16||rptCol2[t].getColumnId()==17||rptCol2[t].getColumnId()==18||rptCol2[t].getColumnId()==19)
								colspan--;
						}
						else if(ReportType.equals("IT"))
						{
							if(rptCol2[t].getColumnId()==5||rptCol2[t].getColumnId()==12||rptCol2[t].getColumnId()==23||rptCol2[t].getColumnId()==13||rptCol2[t].getColumnId()==14||rptCol2[t].getColumnId()==15||rptCol2[t].getColumnId()==16||rptCol2[t].getColumnId()==17||rptCol2[t].getColumnId()==18||rptCol2[t].getColumnId()==19)
								colspan--;

						}

					}



				}


				//SortingHelper Helper = new SortingHelper(DataList);
				if(!ReportType.equals("")&&!ReportType.equals("-1"))
				{
					logger.info("the 2nd column value on start if===>"+rptCol[1].getHidden()); 
					if(ReportType.equals("Misc") || ReportType.equals("PayRec"))
					{
						rptCol[12].setHidden("y");
						rptCol[13].setHidden("y");
						rptCol[16].setHidden("y");
						rptCol[14].setHidden("y");
						rptCol[15].setHidden("y");
						rptCol[1].setHidden("y");
						rptCol[19].setHidden("y");
						rptCol[20].setHidden("y");
						rptCol[21].setHidden("y");
					  //rptCol[24].setHidden("y");

					}
					else if(ReportType.equals("SIS1979"))
					{
						//row.add(17,Math.round(sis1979));
						rptCol[12].setHidden("y");
						rptCol[13].setHidden("y");
						rptCol[16].setHidden("y");
						rptCol[14].setHidden("y");
						rptCol[15].setHidden("y");
						rptCol[1].setHidden("y");
						rptCol[19].setHidden("y");
						rptCol[20].setHidden("y");
						rptCol[21].setHidden("y");
						rptCol[18].setHidden("y");
						rptCol[17].setColumnHeader("SIS/GIS 1979");
					//	rptCol[24].setHidden("y");
					}
					else if(ReportType.equals("Insurance"))
					{
						rptCol[12].setHidden("y");
						rptCol[13].setHidden("y");
						rptCol[18].setHidden("y");
						rptCol[14].setHidden("y");
						rptCol[17].setHidden("y");
						rptCol[1].setHidden("y");
						rptCol[24].setHidden("y");
						if(Long.parseLong(Grade)==AISGradeCode)
						{
							rptCol[15].setColumnHeader("Month");
							rptCol[16].setColumnHeader("Amount of Dedu.");
							rptCol[19].setColumnHeader("Salary Budget Head Vr. No./Date");
							rptCol[20].setColumnHeader("Accounting Budget Head");
							rptCol[21].setColumnHeader("Remarks");
						}



					}
					else if(ReportType.equals("PT"))
					{
						rptCol[12].setHidden("y");
						rptCol[13].setHidden("y");
						rptCol[18].setHidden("y");
						rptCol[15].setHidden("y");
						rptCol[16].setHidden("y");
						rptCol[17].setHidden("y");
						rptCol[20].setHidden("y");
						rptCol[21].setHidden("y");
						rptCol[1].setHidden("y");
						if(!billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
						{
							rptCol[24].setHidden("n");
						}
						/*else
							rptCol[24].setHidden("y");*/

					}
					else if(ReportType.equals("Deduction"))
					{
						rptCol[13].setHidden("y");
						rptCol[14].setHidden("y");
						rptCol[15].setHidden("y");
						rptCol[16].setHidden("y");
						rptCol[17].setHidden("y");
						rptCol[12].setHidden("y");
						rptCol[18].setHidden("y");
						rptCol[1].setHidden("y");
						rptCol[24].setHidden("y");

					}
					else if(ReportType.equals("IT"))
					{
						//rptCol[13].setHidden("y");
						rptCol[5].setHidden("y");
						rptCol[14].setHidden("y");
						rptCol[18].setHidden("y");
						rptCol[15].setHidden("y");
						rptCol[16].setHidden("y");
						rptCol[17].setHidden("y");
						rptCol[19].setHidden("y");
						rptCol[20].setHidden("y");
						rptCol[21].setHidden("y");
						rptCol[22].setHidden("y");
						rptCol[23].setHidden("n");
						if(!billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
						{
							rptCol[24].setHidden("n");
						}
						else
							rptCol[24].setHidden("y");


					}
					logger.info("the 2nd column value on end if===>"+rptCol[1].getHidden()); 

				}
				logger.info("the 2nd column value on end if===>"+rptCol[1].getHidden()); 


				int totallength=rptCol.length;//23
				int collength=rptCol.length;//23
				/*for(int col=0;col<totallength;col++)
	    	            {
	    	            if(rptCol[col].getHidden().equalsIgnoreCase("y"))
	    	            		collength--;
	    	            logger.info(collength+"************inside************************************************"+totallength);
	    	            }*/
				logger.info(collength+"************************************************************"+totallength);

				//by manoj for head change
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

				//end by manoj for head change


				
				String query = " select";
				query+= "  dtl.hrEisEmpMst.empId ,";
				//query+="dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName ,";
				//query+="dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId ,dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName, ";

				//query+= " pst.orgDesignationMst.dsgnShrtName , ";
				query+= "  case when " ;
				if((ReportType.equals("IT") || ReportType.equals("PT")) && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
					query+=	" dsgnMst.dsgnShrtName is null then pst.orgDesignationMst.dsgnShrtName else " ;
				else		
					query+=	" max(dsgnMst.dsgnShrtName) is null then pst.orgDesignationMst.dsgnShrtName else " ;
				if((ReportType.equals("IT") || ReportType.equals("PT")) && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
					query+=	" dsgnMst.dsgnShrtName end , ";
				else
					query+=	"max(dsgnMst.dsgnShrtName) end , ";

				query+= " dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, " ;

				query+=" dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";
				query+=" dtl.otherCurrentBasic ,";
				//query+="dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt || '-' || dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt || '-' ||dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt ,";
				query+="  scale.scaleStartAmt || '-' || scale.scaleIncrAmt || '-' ||scale.scaleEndAmt || '-' ||scale.scaleHigherIncrAmt || '-' ||scale.scaleHigherEndAmt, ";
				if((ReportType.equals("IT") || ReportType.equals("PT")) && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
				{
					query+=" pay.totalDed+pay.adv7057+pay.adv7058+pay.deduc0101  ,";
					query+=" pay.netTotal , ";
					query+=" pay.grossAmt+pay.adv7057+pay.adv7058+pay.deduc0101 , ";
					query+=" pay.it, pay.deduc9550, ";
					query+=" pay.deduc9570";
				}	
				else					
				{
					query+=" sum(pay.totalDed+pay.adv7057+pay.adv7058+pay.deduc0101) ,";
					query+=" sum(pay.netTotal) , ";
					query+=" sum(pay.grossAmt+pay.adv7057+pay.adv7058+pay.deduc0101) , ";
					query+=" sum(pay.it),sum(pay.deduc9550), ";
					query+=" sum(pay.deduc9570)";
				}
				if(!ReportType.equals("")&&!ReportType.equals("-1"))
				{
					if(ReportType.equals("Misc"))
					{
						query+=",sum(pay.deduc9910), ";
					}
					else if(ReportType.equals("PayRec"))
					{
						query+=",sum(pay.deduc0101), ";
					}
					else
					{
						if((ReportType.equals("IT") || ReportType.equals("PT")) && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
							query+=", pay.deduc9910 , ";
						else
							query+=",sum(pay.deduc9910), ";
					}
				}
				else
				{
					if((ReportType.equals("IT") || ReportType.equals("PT")) && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
						query+=", pay.deduc9910 , ";
					else						
						query+=",sum(pay.deduc9910), ";
				}

				if((ReportType.equals("IT") || ReportType.equals("PT")) && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
					query+=" pay.deduc9581 , pay.deduc9582, pay.deduc9583 ,  pay.deduc9584,(select max(m.reason) from HrMiscRecoverDtls m where  m.hrEisEmpMst.empId=dtl.hrEisEmpMst.empId and m.recoverDate>='"+startDate+"' and m.recoverDate<='"+endDate+"' group by dtl.hrEisEmpMst.empId  ), pay.surcharge ";
				else
					query+="sum(pay.deduc9581),sum(pay.deduc9582),sum(pay.deduc9583),sum(pay.deduc9584),(select max(m.reason) from HrMiscRecoverDtls m where  m.hrEisEmpMst.empId=dtl.hrEisEmpMst.empId and m.recoverDate>='"+startDate+"' and m.recoverDate<='"+endDate+"' group by dtl.hrEisEmpMst.empId  ),sum(pay.surcharge) ";

				if(ReportType.equals("IT"))
				{
					query+=" ,(select proof.proofNum from HrEisProofDtl proof where  proof.orgPostMstByUserId.userId = dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId) ";
				}

				if(isBillDefined)//this will always be at last so that no need to change the sequence if new column comes
				{
					query+="  ,pay.psrNo ";
				}
				//				Added By Mrugesh for sis-1979	 
				if(ReportType.equals("SIS1979"))
				{
					query+=" ,sum(pay.deduc9580) ";
				}
				//Ended
				//				Added By hemant for suppilementry bill displaying month/year
				if((ReportType.equals("IT") || ReportType.equals("PT")) && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
					query+="  ,pay.month , pay.year  ";
				
				if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{     
					query+=" from HrPayArrearPaybill pay, ";
				}
				else
				{    
					query+=" from HrPayPaybill pay, ";
				}
				/*// 	by rahul w.r.t head change
				if(currentMonthBill!=1)
				{
					query+=(" PaybillHeadMpg bhm, ");
				}*/
				//by rahul
				query+=" HrEisOtherDtlsHst as dtl ";
				query+= " left outer join dtl.hrEisSgdMpg as sgd ";
				query += " left outer join sgd.hrEisGdMpg as gd ";
				query += " left outer join sgd.hrEisScaleMst scale ";
				query += " left outer join  gd.orgDesignationMst as dsgnMst , ";

				query+=" OrgUserpostRlt           USRPST, ";
				// by rahul
				/*query+=" HrPayOrderHeadMpgHst ORDHD, ";
	                	query+=" HrPayOrderHeadPostMpgHst ORDPST, ";*/
				/*if(currentMonthBill!=1)
				{
					query+=" HrPayOrderHeadMpgHst ORDHD, ";
					query+=" HrPayOrderHeadPostMpgHst ORDPST, ";
				}
				else*/

				query+=" HrPayOrderHeadMpg ORDHD, ";
				query+=" HrPayOrderHeadPostMpg ORDPST, ";

				//ended by rahul
				query+=" OrgPostDetailsRlt pst ";
				//query+=" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm ,HrPayOrderSubHeadMpg hposm ";
				//query+=" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm  ";
				if(isBillDefined)
				{
					//query+="  ,HrPayPsrPostMpg psrmpg ";
				}
				query+=" where  ";

				if(billType.equals(String.valueOf(arrearbillTypeId)))
				{
					String arrearType="";
					arrearType=CheckIfNull(report.getParameterValue( "Arrear List" ));
					if(!arrearType.equals("")&&!arrearType.equals("-1"))
					{
						query+="   pay.salRevId.salRevId="+arrearType+" and ";
					}
				}				

				query+="  dtl.id.trnCounter = pay.otherTrnCntr and  ";				

				//edited by ravysh

				/*if(!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
					query+="  bhm.billTypeId.lookupId = "+Long.parseLong(billType)+" and  ";*/ 				//commented regarding  w.r.t. issue grade Change

				if(isBillDefined)
				{
					//query+="  psrmpg.postId=pay.orgPostMst.postId and "; commented by urvin shah
				}

				query+=" pay.hrEisEmpMst.empId=dtl.hrEisEmpMst.empId ";

				//query += " and ORDHD.subheadId in (select  hposm.element_code from HrPayOrderSubHeadMpg hposm where bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId  )";
				//by rahul
				//query += " and ORDPST.orderHeadId = ORDHD.id.orderHeadId ";
				/*if(currentMonthBill!=1)
				{
					query += " and ORDPST.orderHeadId = ORDHD.id.orderHeadId ";
				}
				else*/

				query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";

				//ended by rahul
				query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
				query += " and USRPST.orgUserMst.userId = dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
				query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
				//query += " and USRPST.activateFlag=1 ";

				/*if(!billType.equals(String.valueOf(arrearbillTypeId)))
				{
					query+="and  (USRPST.endDate is null or ";
					query+="  (USRPST.endDate>='"+startDate+"' and USRPST.startDate<='"+endDate+"'      ))";
				}*/ //Commented by rahul w.r.t. 

			//	query+="  and bhm.approveFlag in (0,1) ";  //commented regarding  w.r.t. issue grade Change 


				if(!empid.equals("")&&!empid.equals("-1"))            	
					query+="and dtl.hrEisEmpMst.empId = '"+empid+"'";

				// Added by Akshay 
				if(!lname.equals("") && !lname.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"+lname.toLowerCase()+"%'");
				}

				if(!fname.equals("") && !fname.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"+fname.toLowerCase()+"%'");
				}
				//Ended by Akshay
				if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
				{
					query+=("  and ORDHD.subheadId  = '"+subHeadId+"'");
				}

				if(!Grade.equals("")&&!Grade.equals("-1"))  
					query+="and dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  = '"+Grade+"'"; 		            		
				//query+="and dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId  = '"+Grade+"'";

				if(!Scale.equals("")&&!Scale.equals("-1"))  
					query+="and dtl.hrEisSgdMpg.hrEisScaleMst.scaleId = '"+Scale+"'";	            		            	


				if(!Designation.equals("")&&!Designation.equals("-1"))            	
					query+="and pst.orgDesignationMst.dsgnId = '"+Designation+"'";
				//query+="and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId = '"+Designation+"'";

				if(!Department.equals("")&&!Department.equals("-1"))
					query+=" and pst.cmnLocationMst.locId="+Department+"  ";
				query+=" and  pst.orgPostMst.postId = pay.orgPostMst.postId  ";
				if(isBillDefined&&!BillNo.equals(""))
				{

					//query+="  and  pay.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId ) and " ;	 
					/*if(currentMonthBill==1)
						query+=("   ORDHD.subheadId in (select bill.subheadId from HrPayBillHeadMpg bill where bill.billId ="+BillNo+" ) and  ");*/
					//Commented by rahul w.r.t. issue of emp grade change
					//query+=" and hpbsm.billId="+BillNo+"  ";
				}
				else
				{
					if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
					{
						query+="  and  ORDHD.subheadId ="+subheadCode+" ";
					}
					if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
					{
						query+="  and dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")";
					}
					if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
					{
						query+="  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ";
					}

					if(portType != null && !portType.equals("") && !portType.equals("-1"))
					{
						query+="  and pst.orgPostMst.postTypeLookupId in (" +portType+ ") ";
					}
				}
				//Commented w.r.t issue regarding grade and bill no change of employee
				/*if(!month.equals("")&&!month.equals("-1"))            	
					query+=" and bhm.month='"+month+"'";

				if(!year.equals("")&&!year.equals("-1"))            	
					query+="and bhm.year= '"+year+"'";*/

				if(!ReportType.equals("")&&!ReportType.equals("-1"))
				{
					if(ReportType.equals("Misc"))
					{
						query+="and pay.deduc9910!=0 ";
					}
					else if(ReportType.equals("PayRec"))
					{
						query+="and pay.deduc0101!=0 ";
					}
					else if(ReportType.equals("Insurance"))
					{
						query+=" and (((pay.deduc9583!=0 and pay.deduc9584!=0)) ";
						query+=" or ((pay.deduc9581!=0 and pay.deduc9582!=0)) )";
					}
					else if(ReportType.equals("PT"))
					{
						query+=" and pay.deduc9570!=0 ";
					}
					else if(ReportType.equals("IT"))
					{
						query+=" and pay.it!=0 ";
					}
					//Added By Mrugesh for sis-1979	 
					if(ReportType.equals("SIS1979"))
					{
						query+=" and pay.deduc9580!=0 ";
					}
					//Ended
				}
				//if(currentMonthBill!=1)
				//query+="  and bhm.hrPayPaybill = pay.paybillGrpId and ORDHD.subheadId = hposm.element_code  and hpbsm.billHeadId = bhm.billNo.billHeadId and bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId ";

				//Commented w.r.t issue regarding grade and bill no change of employee
				//query+="  and bhm.hrPayPaybill = pay.paybillGrpId  and hpbsm.billHeadId = bhm.billNo.billHeadId  ";
				
				//Code block regarding grade and bill no change of employee
				
				query+="   and pay.paybillGrpId in\n" ;
				List pblGrpIdList = new ArrayList(); 
				pblGrpIdList =paybillDAO.getPayBillGrpIDFromBillNo(Long.parseLong(billType),month,year,BillNo);
				StringBuffer strBufPayBillGrpIds=new StringBuffer();
				String strPayBillGrpIdLst="0";
				if(pblGrpIdList!=null && pblGrpIdList.size()>0)
				{
					for (int pblIdIncrVar=0;pblIdIncrVar<pblGrpIdList.size();pblIdIncrVar++)
					{
						strBufPayBillGrpIds.append(pblGrpIdList.get(pblIdIncrVar).toString()).append(",");
					}
					strPayBillGrpIdLst = strBufPayBillGrpIds.deleteCharAt(strBufPayBillGrpIds.lastIndexOf(",")).toString();
					strBufPayBillGrpIds=null;
				}
				query+="      ("+strPayBillGrpIdLst+") ";
				//
				//Commented w.r.t issue regarding grade and bill no change of employee
				//query+= " and bhm.orgGradeMst.gradeId=dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId ";
				if((ReportType.equals("IT") || ReportType.equals("PT")) && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
					query+=" order by  dtl.hrEisEmpMst.empId, pay.year, pay.month  ";
				else
				{
					query+=" group by  ";
					if(isBillDefined)
					{
						query+="  pay.psrNo, ";
					}


					query+=" dtl.hrEisEmpMst.empId,dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId,   ";
					//query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId,  ";
					//query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName,  ";
					//query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName,  ";

					query+=" dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, ";
					query+=" pst.orgDesignationMst.dsgnShrtName, ";
					query+=" dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, ";

					query+="dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
					query+=" dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
					query+=" dtl.hrEisEmpMst.orgEmpMst.empLname,  ";

					query+=" scale.scaleStartAmt,scale.scaleHigherIncrAmt,scale.scaleHigherEndAmt,  ";
					query+=" scale.scaleIncrAmt,  ";
					query+=" scale.scaleEndAmt,dtl.phyChallenged,dtl.otherCurrentBasic  ";

					//query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt,  ";
					//query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt,  ";
					//query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt,dtl.otherCurrentBasic  ";
					if(isBillDefined)
					{
						query+=" order by  pay.psrNo ";
					}
					else
						query+=" order by dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.empId ";
				}
				
				logger.info("***Query for Deduction details**" +query);


				Query sqlQuery = hibSession.createQuery(query);	      	
//				Added By Rajan for checking No Record Found in reports...
				List RowList=sqlQuery.list();
				if(RowList.size()!=0)
				{
					ArrayList dataList=new ArrayList();
				
					logger.info("*************************"+RowList.size());
					int cnt=1;
					long empId=0;
					double I_TaxTotal=0;
					double P_TaxTotal=0;
					double I_FTotal=0;
					double S_FTotal=0;
					double Misc_RecoveryTotal=0;
					double Gross_AmountTotal=0;
					double Total_DeductionTotal=0;
					double Net_AmountTotal=0;
					Iterator itr = RowList.iterator();  

					double ITTotal=0;
					double surchargeTotal=0;
					double PTTotal=0;
					double IFTotal=0;
					double SFTotal=0;
					double miscRecTotal=0;
					double grossAmtTotal=0;
					double totaldedTotal=0;
					double nettotalTotal=0;
					double sis1979Total=0;
					double sis1979=0;

					while (itr.hasNext())
					{
						Object[] rowList = (Object[]) itr.next();
						long EmpId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());
						String designation = (String)(rowList[1]!=null?rowList[1]:"");
						long gradeId = Long.parseLong((rowList[2]!=null?rowList[2].toString():"").toString());
						String Class = (String)(rowList[3]!=null?rowList[3]:"");
						String Name = (String)(rowList[4]!=null?rowList[4]:"");
						long basic = Long.parseLong((rowList[5]!=null?rowList[5]:"").toString());		            
						String  scale = (rowList[6]!=null?rowList[6]:"").toString();		            
						double totalded = Double.parseDouble((rowList[7]!=null?rowList[7]:"").toString());		            
						double nettotal = Double.parseDouble((rowList[8]!=null?rowList[8]:"").toString());		            
						double grossAmt = Double.parseDouble((rowList[9]!=null?rowList[9]:"").toString());		            
						double IT = Double.parseDouble((rowList[10]!=null?rowList[10]:"").toString());		            
						double PF = Double.parseDouble((rowList[11]!=null?rowList[11]:"").toString());		            
						double PT = Double.parseDouble((rowList[12]!=null?rowList[12]:"").toString());		            
						double miscRec = Double.parseDouble((rowList[13]!=null?rowList[13]:"").toString());		            
						double SISIF = Double.parseDouble((rowList[14]!=null?rowList[14]:"").toString());		            
						double SISSF = Double.parseDouble((rowList[15]!=null?rowList[15]:"").toString());		            
						double AISIF = Double.parseDouble((rowList[16]!=null?rowList[16]:"").toString());	
						double AISSF = Double.parseDouble((rowList[17]!=null?rowList[17]:"").toString());		            
						String  reason = (rowList[18]!=null?rowList[18]:"").toString();	
						double surcharge = Double.parseDouble((rowList[19]!=null?rowList[19]:"").toString());
						String bill_Month="";
						String bill_Year="";	
						String RecoveryMonthYear="";
						String panNo="";
						double itTotal=0;  
						
						logger.info(":::::::adi ReportType:::::"+ReportType);
						logger.info(":::::::adi IT:::::"+IT);
						
						//double PTTotal=0;
						if((ReportType.equals("IT") || ReportType.equals("PT")) && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
						{
							bill_Month=(rowList[rowList.length-2]!=null?rowList[rowList.length-2]:"").toString();;
							bill_Year=(rowList[rowList.length-1]!=null?rowList[rowList.length-1]:"").toString();;
							RecoveryMonthYear = Float.valueOf(bill_Month).intValue()+"/"+ Float.valueOf(bill_Year).intValue();
							
							//RecoveryMonthYear = ( rowList[rowList.length-2] != null && !rowList[rowList.length-2].equals("") ? (Integer.parseInt(rowList[rowList.length-2].toString())): "") + "/" +  ( rowList[rowList.length-1] != null && !rowList[rowList.length-1].equals("") ? Integer.parseInt(rowList[rowList.length-1].toString()): "");
						}
						
						if((ReportType.equals("IT") && IT!=0)||(!ReportType.equals("IT")))
						{
							logger.info(":::::::3:::::");
						
						
						
							
						if(Long.parseLong(Grade)==AISGradeCode)
						{
							rptCol[15].setColumnHeader("Month");
							rptCol[16].setColumnHeader("Amount of Dedu.");
							rptCol[19].setColumnHeader("Salary Budget Head Vr. No./Date");
							rptCol[20].setColumnHeader("Accounting Budget Head");
							rptCol[21].setColumnHeader("Remarks");
						}

						if(ReportType.equals("IT"))
						{
							panNo=(rowList[20]!=null?rowList[20]:"").toString();
							nettotal=surcharge+IT;
						}
						else if(ReportType.equals("PT"))
						{
							//PTTotal+=PT;
						}
						else
							panNo=(rowList[20]!=null?rowList[20]:"").toString();	
						//Added By Mrugesh for sis-1979	 
						if(ReportType.equals("SIS1979"))
						{
							sis1979 = Double.parseDouble((rowList[21]!=null?rowList[21]:"").toString());
						}
						//Ended
						
						ArrayList row = new ArrayList();
						/*if(cnt%25==0)
	    		            {
	            				row.add(new PageBreak());
	            				row.add("Data");

	    		            }*/
						//else
						{
							row.add(cnt);
							row.add(panNo);
							row.add(Name);
							row.add(designation);
							//row.add(gradeId);
							row.add(Class);
							// for fixed pay
							if(scale.equals("---0-0")||scale.equals("----"))
							{
								scale = ((Long)basic).toString();
							}
							scale=scale.replaceAll("-0-0", "");
							row.add(scale);
							row.add(panNo);
							row.add("");
							row.add("");
							row.add("");
							row.add("");
							row.add("");
							row.add(Math.round(IT));
							row.add(Math.round(surcharge));
							if(ReportType.equals("PT"))
							{
								row.add(Math.round(grossAmt));
							}
							else
								row.add(Math.round(PT));
							//row.add(PF);
							if(gradeId!=AISGradeCode)
							{	
								row.add(Math.round(SISIF));
								row.add(Math.round(SISSF));
							}
							else
							{

								row.add(month+"/"+year);
								row.add(Math.round(AISIF+AISSF));
							}
							row.add(Math.round(miscRec));
							row.add(reason);
							if(Long.parseLong(Grade)==AISGradeCode)
							{
								row.add("8685-Suspense Accounts A.I.S. Insurance Scheme");
								row.add("");
							}
							else
							{
								if(ReportType.equals("PT"))
								{
									row.add(Math.round(PT));
									rptCol[14].setColumnHeader("Gross Amount");
									rptCol[19].setColumnHeader("P. Tax");
								}
								else
									row.add(Math.round(grossAmt));
								row.add(Math.round(totalded));

							}

							if(ReportType.equals("IT"))
							{
								row.add("");	
								row.add("");
								row.add(Math.round(nettotal));
							}
							else
							{
								if(Long.parseLong(Grade)==AISGradeCode)
								{
									row.add("");
								}
								else
								{
									row.add(Math.round(nettotal));

								}
							}
							//Added By Mrugesh for sis-1979	 
							if(ReportType.equals("SIS1979"))
							{


								row.add(17,Math.round(sis1979));
							}
							else if(ReportType.equals("PT") && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
								{
								row.add("");row.add("");
								}
							//Ended	

							if((ReportType.equals("IT") || ReportType.equals("PT")) && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
							{
								row.add( ReportType + " Amount Recevored for " + RecoveryMonthYear);
							}
							else
								row.add("");
							row.add((cnt-1)/12+1);
						}
						DataList.add(row);

						ITTotal+=IT;
						surchargeTotal+=surcharge;
						PTTotal+=PT;
						IFTotal+=SISIF+AISIF;
						SFTotal+=SISSF+AISSF;
						miscRecTotal+=miscRec;
						grossAmtTotal+=grossAmt;
						totaldedTotal+=totalded;
						nettotalTotal+=nettotal;
						sis1979Total+=sis1979;

//						Added By Rajan for B/F and C/F in the report		 

//						if(cnt==(finalpagesize-1)||(cnt%(finalpagesize-2)==1&&cnt>(finalpagesize-1)))
						if((cnt%finalpagesize)==0) 
						{
							row = new ArrayList();
							row.add("");
							row.add("");   
							row.add("");
							StyledData dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData("B/F");                  
							row.add(dataStyle1);

							row.add("");
							row.add("");
							row.add("");
							row.add("");
							row.add("");
							row.add("");
							row.add("");
							row.add("");

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(ITTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(surchargeTotal);
							row.add(dataStyle1);

							if(ReportType.equals("PT"))
							{
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(grossAmtTotal);//PTTotal
								row.add(dataStyle1);
							}
							else
							{
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(PTTotal);//PTTotal
								row.add(dataStyle1);
							}

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(IFTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(SFTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							if(ReportType.equals("SIS1979"))
								dataStyle1.setData(sis1979Total);
							else
								dataStyle1.setData(miscRecTotal);
							row.add(dataStyle1);

							//Added By Mrugesh for sis1979
							/* dataStyle1 = new StyledData();
	  	              	   dataStyle1.setStyles(boldStyleVO);
	  	              	   dataStyle1.setData(sis1979Total);
	  	              	   row.add(dataStyle1);
							 */ 
							row.add("");

							if(ReportType.equals("PT"))
							{
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(PTTotal);//grossAmtTotal
								row.add(dataStyle1);
							}
							else
							{

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(grossAmtTotal);//grossAmtTotal
								row.add(dataStyle1);

							}



							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(totaldedTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(nettotalTotal);
							row.add(dataStyle1);

							row.add("");
							row.add("");

							row.add("");// this is for col. No 25 on sgvc_report_cols Remarks, print B/F under Remarks col 25th
							
							DataList.add(row);
							row=new ArrayList();
							row.add(new PageBreak());
							row.add("Data");
							for (int j = 0; j < 26; j++)
								row.add("");
							// ended by rahul
							
							DataList.add(row);


							row = new ArrayList();
							row.add("");
							row.add("");   
							row.add("");

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData("C/F");                  
							row.add(dataStyle1);

							row.add("");
							row.add("");
							row.add("");
							row.add("");
							row.add("");
							row.add("");
							row.add("");
							row.add("");


							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(ITTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(surchargeTotal);
							row.add(dataStyle1);

							if(ReportType.equals("PT"))
							{
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(grossAmtTotal);//PTTotal
								row.add(dataStyle1);
							}
							else
							{
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(PTTotal);//PTTotal
								row.add(dataStyle1);
							}

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(IFTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(SFTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(miscRecTotal);
							row.add(dataStyle1);

//							Added By Mrugesh for sis1979
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(sis1979Total);
							row.add(dataStyle1);

							row.add("");

							if(ReportType.equals("PT"))
							{
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(PTTotal);//grossAmtTotal
								row.add(dataStyle1);
							}
							else
							{
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(grossAmtTotal);//grossAmtTotal
								row.add(dataStyle1);
							}



							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(totaldedTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(nettotalTotal);
							row.add(dataStyle1);

							row.add("");
							row.add("");// this is for col. No 25 on sgvc_report_cols Remarks, print C/F under Remarks col 25th 

							row.add("");
							
							DataList.add(row);
						}
						// ended by RAJAN


						cnt++; 

						/*    if( cnt%25==0)
	    	    			{
	    	  	            	row=new ArrayList();
	    	  	            	row.add(new PageBreak());
	    	      				row.add("Data");
	    	    	            DataList.add(row); 
	    	    			}*/
					}
					}

					ArrayList row = new ArrayList();
					row.add("");
					row.add("");
					row.add("");

					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData("Total"); 

					row.add(dataStyle1);

					//row.add("Total");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");

					if(Long.parseLong(Grade)==AISGradeCode)
					{
						row.add("");//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(ITTotal)));
						row.add("");//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(surchargeTotal)));
						row.add("");//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(PTTotal)));
						row.add("");//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(IFTotal)));

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(Math.round(IFTotal+SFTotal));
						row.add(dataStyle1);


						// row.add(Math.round(IFTotal+SFTotal));//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(SFTotal)));
						row.add("");//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(miscRecTotal)));
					}
					else
					{
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(Math.round(ITTotal));
						row.add(dataStyle1);


						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(Math.round(surchargeTotal));
						row.add(dataStyle1);


						//row.add(Math.round(ITTotal));//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(ITTotal)));
						//row.add(Math.round(surchargeTotal));//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(surchargeTotal)));
						if(ReportType.equals("PT"))
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(Math.round(grossAmtTotal));
							row.add(dataStyle1);
							//row.add("");//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(PTTotal)));
						}
						else
						{

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(Math.round(PTTotal));
							row.add(dataStyle1);

						}
						// row.add(Math.round(PTTotal));	
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(Math.round(IFTotal));
						row.add(dataStyle1);


						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(Math.round(SFTotal));
						row.add(dataStyle1);




						//Added By Mrugesh
						if(ReportType.equals("SIS1979"))
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(sis1979Total);
							row.add(dataStyle1);
						}

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(Math.round(miscRecTotal));
						row.add(dataStyle1);

						/* row.add(Math.round(IFTotal));//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(IFTotal)));
	    	            row.add(Math.round(SFTotal));//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(SFTotal)));
	    	            row.add(Math.round(miscRecTotal));//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(miscRecTotal)));
						 */
					}
					row.add("");
					if(!ReportType.equals("PT"))
					{	
						if(Long.parseLong(Grade)==AISGradeCode)
						{
							row.add("");
						}
						else
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(Math.round(grossAmtTotal));
							row.add(dataStyle1);
							//row.add(Math.round(grossAmtTotal));//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(grossAmtTotal)));	
						}

					}
					else
					{
						if(ReportType.equals("PT"))
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(Math.round(PTTotal));
							row.add(dataStyle1);
							//row.add(Math.round(PTTotal));
						}
						else
							row.add("");
					}

					if(Long.parseLong(Grade)==AISGradeCode)
					{
						row.add("");
					}
					else
					{
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(Math.round(totaldedTotal));
						row.add(dataStyle1);
						//row.add(Math.round(totaldedTotal));//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(totaldedTotal)));	
					}

					if(ReportType.equals("IT"))
					{
						row.add("");	
						row.add("");
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(Math.round(nettotalTotal));
						row.add(dataStyle1);
						//row.add(Math.round(nettotalTotal));//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(nettotalTotal)));
					}

					else
					{
						if(Long.parseLong(Grade)==AISGradeCode)
						{
							row.add("");
						}
						else
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(Math.round(nettotalTotal));
							row.add(dataStyle1);
							//row.add(Math.round(nettotalTotal));//+System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(nettotalTotal)));	
						}	
					}


//					logger.info("the cnt value is------->>>>>"+cnt);
					row.add("");// // this is for col. No 25 on sgvc_report_cols Remarks, Under the col for last grand total
					row.add((cnt-1)/12+1);
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
					if(ReportType.equals("IT"))
						dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(nettotalTotal))+" only.");
					else if(ReportType.equals("PT"))
					{
						dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(PTTotal))+" only.");
					}
					else if(ReportType.equals("Misc") || ReportType.equals("PayRec"))
					{
						dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(miscRecTotal))+" only.");
					}

					else if(ReportType.equals("SIS1979"))
					{
						dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(sis1979Total))+" only.");
					}

					else
					{
						if(Long.parseLong(Grade)==AISGradeCode)
						{
							dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(IFTotal+SFTotal))+" only.");
						}
						else
						{
							dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(nettotalTotal))+" only.");	
						}	


					}

					dataStyle2.setColspan(colspan);
					row1.add(dataStyle2);
					if(ReportType.equals("IT"))
					{
						for(int c=0;c<25;c++)
							row1.add("");
					}
					else
					{
						for(int c=0;c<(totallength-colspan);c++) 
							row1.add("");
					}
					if(ReportType.equals("PT") && !billType.equals("") && billType.equals(String.valueOf(multipleMonthSupplBill)))
					{
						for(int i = 0 ; i < 4 ; i++) // 4 because  :  totallength-colspan = 25-5 = 20 and total number of col  = 25 and start wid 0
						row1.add("");
					}
					row.add("");// this is for col. No 25 on sgvc_report_cols Remarks, Under the col for last grand total
					DataList.add(row1);
					report.setReportColumns(rptCol);
					report.initializeDynamicTreeModel();
					report.initializeTreeModel(); 
					logger.info("**********************"+DataList.size());
//					added By Rajan for checking No Record Found in reports
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

		if(  report.getReportCode().equals("3") || report.getReportCode().equals("2500003") ||report.getReportCode().equals("6") || report.getReportCode().equals("2500006")|| report.getReportCode().equals("7") || report.getReportCode().equals("2500007")|| report.getReportCode().equals("8") || report.getReportCode().equals("2500008")|| report.getReportCode().equals("10") || report.getReportCode().equals("2500010")|| report.getReportCode().equals("11") || report.getReportCode().equals("2500011") || report.getReportCode().equals("13") || report.getReportCode().equals("2500013")|| report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("5") || report.getReportCode().equals("2500005") || report.getReportCode().equals("9") || report.getReportCode().equals("2500009"))

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			//added by ravysh
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}



		return report;
	}



}
