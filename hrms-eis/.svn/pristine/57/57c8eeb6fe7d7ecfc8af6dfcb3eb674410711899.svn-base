package com.tcs.sgv.eis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
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
import com.tcs.sgv.common.util.reports.SortingHelper;
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

public class LoanReportDAO extends DefaultReportDataFinder 
implements ReportDataFinder
{
	ResourceBundle locStrsBundle;
	private static Logger logger = Logger.getLogger(PayrollReportsDAO.class );
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	long activateFlag = Long.parseLong(resourceBundle.getString("activate"));


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
		int finalpagesize=26;
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

		StyleVO[] centerboldStyleVO2 = new StyleVO[2];
		centerboldStyleVO2[0] = new StyleVO();
		centerboldStyleVO2[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		centerboldStyleVO2[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
		centerboldStyleVO2[1] = new StyleVO();
		centerboldStyleVO2[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		centerboldStyleVO2[1].setStyleValue("13"); 
		//Added by rahul
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
		//Ended by rahul
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

//			by manoj for HBA, Scooter,Car loan and advance report
			if(report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("20") || report.getReportCode().equals("2500020") || report.getReportCode().equals("21") || report.getReportCode().equals("2500021") || report.getReportCode().equals("22") || report.getReportCode().equals("2500022") || report.getReportCode().equals("23") || report.getReportCode().equals("2500023") || report.getReportCode().equals("24") || report.getReportCode().equals("2500024") || report.getReportCode().equals("25") || report.getReportCode().equals("2500025") || report.getReportCode().equals("26") || report.getReportCode().equals("2500026") || report.getReportCode().equals("27") || report.getReportCode().equals("2500027") || report.getReportCode().equals("28") || report.getReportCode().equals("2500028") || report.getReportCode().equals("29") || report.getReportCode().equals("2500029"))
			{          


				StringBuffer lsb = new StringBuffer(  );      
				int pageCnt=1;

				String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
				String Designation="";
				String Grade="";
				String Scale="";
				int month=0;
				int year=0;
				String loan_type="";
				String report_type="";

				String loanCol="loan9591"; // for HBA

				Grade=CheckIfNull(report.getParameterValue( "Grade" ));
				Scale=CheckIfNull(report.getParameterValue( "Scale" ));
				Designation=CheckIfNull(report.getParameterValue( "Designation" ));

				String department="";
				department=CheckIfNull(report.getParameterValue( "Department" ));
				//added by samir joshi for bill no wise report
				if(department.equals("")||department.equals("-1"))
					department=	locationId+"";	
				else
					locationId=Long.parseLong(department);
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

				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				
				//added by ravysh
				String billType=CheckIfNull(report.getParameterValue( "billTypePara" ));
				//if(billType.equals("") || billType.equals(" ") || billType.equals("-1"))
					//billType = resourceBundle.getString("paybillTypeId");
				
				
				//logger.info(" nof rec out====>"+noOfRec);
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					//logger.info("No Of Rec is********====>"+noOfRec);
					finalpagesize=Integer.parseInt(noOfRec);
				}

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
				trow5.add(new StyledData(deptName,centerboldStyleVO1));
				tblData.add(trow5);//added second row of the tabular data

				ArrayList trow3 = new ArrayList();

				//trow3.add(" ");
				//trow3.add(" ");
				//trow3.add(new StyledData(cityName,centerboldStyleVO1));

				//tblData.add(trow3);//added third row of the tabular data

				ArrayList trow6 = new ArrayList();
				trow6.add(" ");
				trow6.add(" ");
				trow6.add(new StyledData(cardexCode,centerboldStyleVO1));

				tblData.add(trow6);//added sixth row of the tabular data

				TabularData tabularData = new TabularData(tblData);//initialize tabular data
				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
				tabularData.addStyle(IReportConstants.BORDER, "No"); 

				report.setGrandTotalTemplate(tabularData);
				report.setGroupByTotalTemplate(tabularData);
				//
//				Seetting additional header using tabular data ...
				ArrayList d = new ArrayList();
				ArrayList r = new ArrayList();

				r= new ArrayList();
				r.add(new StyledData("To be Filled in by Drawing Officer",centerboldStyleVO2));
				r.add(new StyledData("To be filled in by Treasury Officer",centerboldStyleVO2));
				d.add(r);
				r= new ArrayList();
				r.add(new StyledData("District         :7  1",centerboldStyleVO2));
				r.add(new StyledData("Voucher No       :",centerboldStyleVO2));
				d.add(r);
				r= new ArrayList();
				r.add(new StyledData("Drawing Officer  : 012",centerboldStyleVO2));
				r.add(new StyledData("Month & Year     :",centerboldStyleVO2));
				d.add(r);
				r= new ArrayList();
				r.add(new StyledData("                      ",centerboldStyleVO2));
				r.add(new StyledData("Major Head       :",centerboldStyleVO2));
				d.add(r);

				TabularData td  = new TabularData(d);
				td.addStyle(IReportConstants.ADDL_HEADER_LOCATION, IReportConstants.VALUE_ADDL_HEADER_LOCATION_BELOW);
				td.addStyle(IReportConstants.BORDER, "No"); 
				// td.addStyle(boldStyleVO);
				report.setAdditionalHeader(td);

				String demandNo = "";//CheckIfNull(report.getParameterValue("demandNo")); 
				//(String)requestAttributes.get("demandNo");
				String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
				String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
				String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
				String subHead = CheckIfNull(report.getParameterValue("subHead"));
				//String dtlHead = CheckIfNull(report.getParameterValue("dtlHead"));
				String subheadflag=subHead;
				logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
				if(!CheckIfNull(report.getParameterValue( "Month" )).equals(""))
				{
					month=Integer.parseInt(CheckIfNull(report.getParameterValue( "Month" )));
				}
				if(!CheckIfNull(report.getParameterValue( "Year" )).equals(""))
				{
					year=Integer.parseInt(CheckIfNull(report.getParameterValue( "Year" )));
				}
				//by manoj for head change
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
				cal.set(Calendar.YEAR,year);
				cal.set(Calendar.MONTH,month-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				java.util.Date finYrDate = cal.getTime();
				cal.set(Calendar.YEAR,year);
				cal.set(Calendar.MONTH, month-1);
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

					Map  requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
					//ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");

					SessionFactory sessionFactory = serv.getSessionFactory();
					Session session = sessionFactory.getCurrentSession();
					genDAO.setSessionFactory(serv.getSessionFactory());
					CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
					CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.getLangVOByShortName(langName);  
					long langId = cmnLanguageMst.getLangId();
					PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());


					SgvcFinYearMst finYrMst = payDAO.getFinYrInfo(finYrDate, langId);

					String cols[] = {"demandCode","budmjrhdCode","budsubmjrhdCode","budminhdCode","budsubhdCode","langId","finYrId"};//,"finYrId"
					Object vals[] = {demandNo,mjrHead,subMjrHead,mnrHead,subHead,langName,finYrMst.getFinYearId()};//,String.valueOf(finYrMst.getFinYearId())
					List<SgvaBudsubhdMst> subHeadList = genDAO.getListByColumnAndValue(cols, vals);

					SgvaBudsubhdMst subHeadObj = subHeadList.get(0);
					subHeadId = String.valueOf(subHeadObj.getBudsubhdId()); 
					logger.info("Paybill Subhead ID from following details is " + subHeadId + " from size " + subHeadList.size());
				}

				cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH,month-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				loan_type=CheckIfNull(report.getParameterValue( "LoanType" ));
				java.util.Date date = cal.getTime();
				sdfObj = new SimpleDateFormat("MMM");

				String Month = sdfObj.format(date);

				report_type=CheckIfNull(report.getParameterValue( "ReportFor" ));
				String report_type_id="";
				String report_type_desc="";
				StringTokenizer st =new StringTokenizer(report_type,"~");
				int i=0;
				while(st.hasMoreTokens())
				{
					if(i==0)
						report_type_id=st.nextToken();
					else if(i==1)
						report_type_desc=st.nextToken();
					i++;
				}
//				Modified By Mrugesh
				if(report.getReportCode().equals("4") || report.getReportCode().equals("2500004"))
				{
					if(report_type_desc.equalsIgnoreCase("Principal"))
					{
						if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
							loanCol="loan9591";
						else if(constantsBundle.getString("mca").equalsIgnoreCase(loan_type))
							loanCol="loan9592";
						else if(constantsBundle.getString("car").equalsIgnoreCase(loan_type))
							loanCol="loan9592";
						else if(constantsBundle.getString("scooter").equalsIgnoreCase(loan_type))
							loanCol="loan9592";
						else if(constantsBundle.getString("moped").equalsIgnoreCase(loan_type))
							loanCol="loan9592";

					}	
					else
					{
						if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
							loanCol="loanInt9591";
						else if(constantsBundle.getString("mca").equalsIgnoreCase(loan_type))
							loanCol="loanInt9592";
						else if(constantsBundle.getString("car").equalsIgnoreCase(loan_type))
							loanCol="loanInt9592";
						else if(constantsBundle.getString("scooter").equalsIgnoreCase(loan_type))
							loanCol="loanInt9592";
						else if(constantsBundle.getString("moped").equalsIgnoreCase(loan_type))
							loanCol="loanInt9592";
					}
				}
				else if(report.getReportCode().equals("20") || report.getReportCode().equals("2500020"))
				{
					loan_type=constantsBundle.getString("hba");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					loanCol="loan9591";
				}
				else if(report.getReportCode().equals("21") || report.getReportCode().equals("2500021"))
				{
					loan_type=constantsBundle.getString("mca");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					loanCol="loan9592";
				}
				else if(report.getReportCode().equals("22") || report.getReportCode().equals("2500022"))
				{
					loan_type=constantsBundle.getString("car");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					loanCol="loan9592";
				}
				else if(report.getReportCode().equals("23") || report.getReportCode().equals("2500023"))
				{
					loan_type=constantsBundle.getString("scooter");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					loanCol="loan9592";
				}
				else if(report.getReportCode().equals("24") || report.getReportCode().equals("2500024"))
				{
					loan_type=constantsBundle.getString("moped");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					loanCol="loan9592";
				}
				else if(report.getReportCode().equals("25") || report.getReportCode().equals("2500025"))
				{
					loan_type=constantsBundle.getString("hba");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					loanCol="loanInt9591";
				}
				else if(report.getReportCode().equals("26") || report.getReportCode().equals("2500026"))
				{
					loan_type=constantsBundle.getString("mca");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					loanCol="loanInt9592";
				}
				else if(report.getReportCode().equals("27") || report.getReportCode().equals("2500027"))
				{
					loan_type=constantsBundle.getString("car");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					loanCol="loanInt9592";
				}
				else if(report.getReportCode().equals("28") || report.getReportCode().equals("2500028"))
				{
					loan_type=constantsBundle.getString("scooter");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					loanCol="loanInt9592";
				}
				else if(report.getReportCode().equals("29") || report.getReportCode().equals("2500029"))
				{
					loan_type=constantsBundle.getString("moped");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					loanCol="loanInt9592";
				}

//				Ended
				
				//for food and festival advance
				if(constantsBundle.getString("festival").equalsIgnoreCase(loan_type))
					loanCol="adv7057";
				else if(constantsBundle.getString("foodGrain").equalsIgnoreCase(loan_type))
					loanCol="adv7058";



//				Modified By Mrugesh & Rajan
				if(report.getReportCode().equals("4") || report.getReportCode().equals("2500004"))
				{
					// Modified by rahul Date : 4-Oct-2008
					ArrayList styleList = new ArrayList();
					ArrayList stData = new ArrayList();
					String deptHeader="";
					if(report_type_desc.equalsIgnoreCase("Principal"))
					{


						if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
						{
							//Added By Maruthi for Back button issue.		        			
							if( BillNo != null && BillNo != "" )
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;

							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
							}			        			
							//Ended By Maruthi.
						}
						else if(constantsBundle.getString("mca").equalsIgnoreCase(loan_type))
						{	
							//Added By Maruthi for Backbutton issue.
							if( BillNo != null && BillNo != "" )
							{		        				
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;		        				
							}
							else
							{		        				
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");		        				
							}	
						}

						else if(constantsBundle.getString("car").equalsIgnoreCase(loan_type))
						{	
							//Added By Maruthi.
							if( BillNo != null && BillNo != "" )//gampa
							{
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;		        			
							}
							else
							{		        				
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");		        				
							}
							//Endded By Maruthi.	        			
						}

						else if(constantsBundle.getString("scooter").equalsIgnoreCase(loan_type))
						{	

							//Added By Maruthi.

							if( BillNo != null && BillNo != "" )
							{
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;		        				
							}
							else
							{
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");		        				
							}
							//Ended by maruthi.
						}

						else if(constantsBundle.getString("moped").equalsIgnoreCase(loan_type))
						{	
							//Added By Maruthi for Backbutton issue.		        		
							if( BillNo != null && BillNo != "" )
							{		        				
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;		        				
							}
							else
							{		        				
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");		        				
							}



							//if(!BillNo.equals(""))
							//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
							//else
							//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
						}
					}	
					else
					{

						if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
						{	
							//Added By Maruthi for Back button issue.		        			
							if( BillNo != null && BillNo != "" )
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;		        				
							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
							}
						}

						else if(constantsBundle.getString("mca").equalsIgnoreCase(loan_type))
						{	
							//Added By Maruthi for Back button issue.
							if( BillNo != null && BillNo != "" )
							{
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;		        				
							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
							}
						}	
						else if(constantsBundle.getString("car").equalsIgnoreCase(loan_type))
						{	
							//Added By Maruthi for Back button issue.

							if( BillNo != null && BillNo != "" )
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
							} 
						}

						else if(constantsBundle.getString("scooter").equalsIgnoreCase(loan_type))
						{	
							//Added by Maruthi For back buton issue.		        		
							if( BillNo != null && BillNo != "" )//gampa
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
							}
						}

						else if(constantsBundle.getString("moped").equalsIgnoreCase(loan_type))
						{	

							//Added by Maruthi For back buton issue.		        			
							if( BillNo != null && BillNo != "" )
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
								deptHeader=DeptName+System.getProperty("line.separator")+"ANNEXURE 'C' (REFER PARA-2)"+System.getProperty("line.separator")+"Schedule pertaining to the credit Head:8011-Insurance Fund Pension Fund "+System.getProperty("line.separator")+"(Gujarat Goverment Employees Group Insurance Scheme 1981) " +System.getProperty("line.separator")+"for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+"N.B (In cases subscription is remained in arrears the fact should be shown in red ink as a separate footnote).";
							}
							else
							{
								//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
								deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");		        				
							}
							//Ended By Maruthi.   
						}


					}	
					
					
					//for food and festival advance
					if(constantsBundle.getString("festival").equalsIgnoreCase(loan_type))
					{	
						deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Festival Advance for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
						if(BillNo != null &&!BillNo.equals(""))
							deptHeader+=" Bill No:"+BillNo;
					}	
					else if(constantsBundle.getString("foodGrain").equalsIgnoreCase(loan_type))
					{	
						deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Food Advance for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
						if(BillNo != null &&!BillNo.equals(""))
							deptHeader+=" Bill No:"+BillNo;
					}	


					StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
					styledHeader.setColspan(2);
					stData.add(styledHeader);
					stData.add("");
					styleList.add(stData);

					ArrayList r1 = new ArrayList();

					r1.add(new StyledData("To be Filled in by Drawing Officer",leftHeader));
					r1.add(new StyledData("To be filled in by Treasury Officer",leftHeader));
					styleList.add(r1);
					ArrayList r2= new ArrayList();
					r2.add(new StyledData("District         :7  1",leftHeader));
					r2.add(new StyledData("Voucher No       :",leftHeader));
					styleList.add(r2);
					ArrayList r3= new ArrayList();
					r3.add(new StyledData("Drawing Officer  : 012",leftHeader));
					r3.add(new StyledData("Month & Year     :",leftHeader));
					styleList.add(r3);
					ArrayList r4= new ArrayList();
					r4.add(new StyledData("                      ",leftHeader));
					r4.add(new StyledData("Major Head       :",leftHeader));
					styleList.add(r4);


					TabularData tData  = new TabularData(styleList);
					//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
					//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
					//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
					tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
					tData.addStyle(IReportConstants.BORDER, "No");
					//tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
					tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
					report.setAdditionalHeader(tData);
					// Modification ended by rahul Date : 4-Oct-2008
				}
				else if(report.getReportCode().equals("20") || report.getReportCode().equals("2500020"))
				{
					loan_type=constantsBundle.getString("hba");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
//					Added by Rajan for check if bill no is selected or not !
					if(!BillNo.equals(("")))
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					else
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					// Ended by rajan
				}
				else if(report.getReportCode().equals("21") || report.getReportCode().equals("2500021"))
				{
					loan_type=constantsBundle.getString("mca");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
//					Added by Rajan for check if bill no is selected or not !
					if(!BillNo.equals(("")))
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					else
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					//Ended by rajan
				}
				else if(report.getReportCode().equals("22") || report.getReportCode().equals("2500022"))
				{
					loan_type=constantsBundle.getString("car");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
//					Added by Rajan for check if bill no is selected or not !
					if(!BillNo.equals(("")))
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					else
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					// Ended by rajan
				}
				else if(report.getReportCode().equals("23") || report.getReportCode().equals("2500023"))
				{
					loan_type=constantsBundle.getString("scooter");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
//					Added by Rajan for check if bill no is selected or not !
					if(!BillNo.equals(("")))
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					else
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					//Ended by rajan
				}
				else if(report.getReportCode().equals("24") || report.getReportCode().equals("2500024"))
				{
					loan_type=constantsBundle.getString("moped");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					//Added by Rajan for check if bill no is selected or not !
					if(!BillNo.equals(("")))
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					else
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					//Ended by rajan
				}

				else if(report.getReportCode().equals("25") || report.getReportCode().equals("2500025"))
				{
					loan_type=constantsBundle.getString("hba");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
//					Added by Rajan for check if bill no is selected or not !
					if(!BillNo.equals(("")))
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					else
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					// Ended by rajan
				}
				else if(report.getReportCode().equals("26") || report.getReportCode().equals("2500026"))
				{
					loan_type=constantsBundle.getString("mca");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					//Added by Rajan for check if bill no is selected or not !
					if(!BillNo.equals(("")))
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					else
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					// Ended by rajan
				}
				else if(report.getReportCode().equals("27") || report.getReportCode().equals("2500027"))
				{
					loan_type=constantsBundle.getString("car");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type)).
//					Added by Rajan for check if bill no is selected or not !
					if(!BillNo.equals(("")))
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					else
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					// Ended by rajan
				}
				else if(report.getReportCode().equals("28") || report.getReportCode().equals("2500028"))
				{
					loan_type=constantsBundle.getString("scooter");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					// Added by Rajan for check if bill no is selected or not !
					if(!BillNo.equals(("")))
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					else
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					// Ended by Rajan
				}
				else if(report.getReportCode().equals("29") || report.getReportCode().equals("2500029"))
				{
					loan_type=constantsBundle.getString("moped");
					//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
					// Added by rajan for check if bill no is selected or not !
					if(!BillNo.equals(("")))
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					else
						report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					// Ended by rajan
				}
//				Ended	
				cal = Calendar.getInstance();
				sdfObj = new SimpleDateFormat("dd-MMM-yyyy");

				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH,month-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				java.util.Date startMonthDate = cal.getTime();
				String startDate  = sdfObj.format(startMonthDate);

				String IsApproved = CheckIfNull(report.getParameterValue("Is Approved")); 
				String approveDate="";
				String approveEndDate="";
				if(IsApproved.equals("no"))
				{

					int yearBeforeApprove=year;
					int monthBeforeApprove=month;

					if(month==1)
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

				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH,month-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

				cal.set(Calendar.DAY_OF_MONTH, totalDays);

				endMonthDate = cal.getTime();

				endDate  = sdfObj.format(endMonthDate);


				Map  requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
//				ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");

				SessionFactory sessionFactory = serv.getSessionFactory();
				Session session = sessionFactory.getCurrentSession();


				lsb.append("select  hploan.hrLoanAdvMst.loanAdvName,");
				lsb.append(" hploan.paybillId.hrEisEmpMst.orgEmpMst.empPrefix||' '||hploan.paybillId.hrEisEmpMst.orgEmpMst.empFname||' '||hploan.paybillId.hrEisEmpMst.orgEmpMst.empMname||' '||hploan.paybillId.hrEisEmpMst.orgEmpMst.empLname,");
				lsb.append("emp.loanAccountNo,");
				lsb.append("emp.loanDate,");
//				Modified By Mrugesh
//				if(report.getReportCode().equals("4"))
				{

					lsb.append("hploan.totalAmt,");
					lsb.append("hploan.totalInst,");
					lsb.append(" bill."+loanCol+" ,");
					lsb.append("hploan.recoveredInst,");
					lsb.append("hploan.recoveredAmt");


				}


				lsb.append(" from ");
				lsb.append(" HrPayPaybill bill,HrPayPaybillLoanDtls hploan, ");
				lsb.append(" HrLoanEmpDtls emp, ");
				lsb.append(" HrEisOtherDtls dtl, ");
				/*	            lsb.append(" HrLoanEmpPrinRecoverDtls pdtls, ");
	            lsb.append(" HrLoanEmpIntRecoverDtls idtls, ");*/

				lsb.append(" OrgUserpostRlt           USRPST, ");
				/*if(currentMonthBill!=1)
				{
					lsb.append(" PaybillHeadMpg bhm, ");
				}
					            lsb.append(" HrPayOrderHeadMpgHst ORDHD, ");
	            lsb.append(" HrPayOrderHeadPostMpgHst ORDPST, ");
				if(currentMonthBill!=1)
				{
					lsb.append(" HrPayOrderHeadMpgHst ORDHD, ");
					lsb.append(" HrPayOrderHeadPostMpgHst ORDPST, ");
				}
				else*/

				lsb.append(" HrPayOrderHeadMpg ORDHD, ");
				lsb.append(" HrPayOrderHeadPostMpg ORDPST, ");


				lsb.append(" OrgPostDetailsRlt pst, ");
				lsb.append(" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm   ");


				lsb.append(" where  bill.hrEisEmpMst.empId = emp.hrEisEmpMst.empId and ");
				lsb.append(" emp.hrEisEmpMst.empId = dtl.hrEisEmpMst.empId and ");
				/*	            lsb.append(" pdtls.hrLoanEmpDtls.empLoanId =  emp.empLoanId and ");
	            lsb.append(" idtls.hrLoanEmpDtls.empLoanId =  emp.empLoanId and ");*/
				lsb.append("  hploan.paybillId.id=  bill.id and ");
				lsb.append("  hploan.paybillId.hrEisEmpMst.empId=  emp.hrEisEmpMst.empId and ");

//				lsb.append(" and ORDPST.orderHeadId = ORDHD.id.orderHeadId ");
				/*if(currentMonthBill!=1)
				{
					lsb.append(" and ORDPST.orderHeadId = ORDHD.id.orderHeadId ");
				}
				else*/

				lsb.append(" ORDPST.orderHeadId = ORDHD.orderHeadId ");


				lsb.append(" and USRPST.orgPostMstByPostId.postId = ORDPST.postId ");
				lsb.append(" and USRPST.orgUserMst.userId = bill.hrEisEmpMst.orgEmpMst.orgUserMst.userId ");
				lsb.append(" and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId and ");
				lsb.append("   (USRPST.endDate is null or ");
				lsb.append("   (USRPST.endDate>='"+startDate+"' and USRPST.startDate<='"+endDate+"'   )) ");
				lsb.append("   and bhm.approveFlag in (0,1) and ");

				lsb.append("  bill."+loanCol+"!=0 and ");  
				
				lsb.append("  emp.empLoanId = hploan.hrLoanEmpDtls.empLoanId and ");
				//lsb.append("  emp.empLoanId in (select max(l.empLoanId) from HrLoanEmpDtls l where l.hrEisEmpMst.empId = emp.hrEisEmpMst.empId and l.hrLoanAdvMst.loanAdvId = emp.hrLoanAdvMst.loanAdvId and l.loanDate<='"+startDate+"' and l.loanActivateFlag='"+activateFlag+"' )  and ");                        


				lsb.append("   dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.cmnLanguageMst.langId = 1 and ");
				lsb.append(" emp.hrLoanAdvMst.cmnLanguageMst.langId = 1 and ");
				lsb.append(" emp.hrLoanAdvMst.loanAdvId in ( "+loan_type+")  ");

				if(!empid.equals("")&&!empid.equals("-1"))            	
					lsb.append(" and emp.hrEisEmpMst.empId = '"+empid+"'");

				if(!Grade.equals("")&&!Grade.equals("-1"))          	
					lsb.append(" and dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.gradeId = '"+Grade+"'");

				if(!Scale.equals("")&&!Scale.equals("-1"))            	
					lsb.append(" and dtl.hrEisSgdMpg.hrEisScaleMst.scaleId = '"+Scale+"'");

				if(!Designation.equals("")&&!Designation.equals("-1"))            	
					lsb.append(" and dtl.hrEisSgdMpg.hrEisGdMpg.orgDesignationMst.dsgnId = '"+Designation+"'");

				lsb.append(" and bhm.month ='"+month+"'");

				lsb.append(" and bhm.year = '"+year+"'");

				lsb.append(" and hploan.hrLoanAdvMst.loanAdvId in ( "+loan_type+")  ");

				if(!report_type_id.equals("")&&!report_type_id.equals("-1"))     
					lsb.append(" and hploan.recoveryType = '"+report_type_id+"'");
				else if(report.getReportCode().equals("20") || report.getReportCode().equals("2500020")|| report.getReportCode().equals("21") || report.getReportCode().equals("2500021") || report.getReportCode().equals("22") || report.getReportCode().equals("2500022") || report.getReportCode().equals("23") || report.getReportCode().equals("2500023") || report.getReportCode().equals("24") || report.getReportCode().equals("2500024"))
				{
					//report_type=constantsBundle.getString("principal");

					lsb.append(" and hploan.recoveryType = '"+300428+"'");




				}
				else if(report.getReportCode().equals("25") || report.getReportCode().equals("2500025")|| report.getReportCode().equals("26") || report.getReportCode().equals("2500026")|| report.getReportCode().equals("27") || report.getReportCode().equals("2500027")|| report.getReportCode().equals("28") || report.getReportCode().equals("2500028")|| report.getReportCode().equals("29") || report.getReportCode().equals("2500029"))
				{
					//report_type=constantsBundle.getString("interest");
					lsb.append(" and hploan.recoveryType = '"+300429+"'");

				}

				if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
				{
					lsb.append(" and ORDHD.subheadId  = '"+subHeadId+"'");
				}

				if(!department.equals("")&&!department.equals("-1"))
					lsb.append(" and pst.cmnLocationMst.locId="+department+"  ");
				if(isBillDefined&&!BillNo.equals(""))
				{

					//lsb.append("  and  bill.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId )  ") ; 
					//if(currentMonthBill==1)
					//lsb.append("   ORDHD.subheadId in (select bill.subheadId from HrPayBillHeadMpg bill where bill.billId ="+BillNo+" ) and  ");

					lsb.append(" and pst.orgPostMst.postId = bill.orgPostMst.postId  ");  
					lsb.append(" and hpbsm.billId="+BillNo+"  ");
				}
				else
				{
					if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
					{
						lsb.append("  and  ORDHD.subheadId ="+subheadCode+" ");
					}
					if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
					{
						lsb.append("  and bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")");
					}
					if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
					{
						lsb.append("  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ");
					}
				}
				//if(currentMonthBill!=1)
				lsb.append("  and bhm.hrPayPaybill = bill.paybillGrpId  and hpbsm.billHeadId = bhm.billNo.billHeadId  ");
				lsb.append(" and bhm.orgGradeMst.gradeId=bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId "); //Date 5-Nov-08 by rahul
				
				//added by ravysh for main and supplimentary bill
				if(!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
					lsb.append("  and bhm.billTypeId.lookupId="+Long.parseLong(billType));	
				
				lsb.append("  order by emp.loanAccountNo ");

				String strQuery = lsb.toString(); 

				logger.info("the query is "+strQuery);
				Query query = session.createQuery(strQuery);

				int cnt=1;
				long empId=0;

				ArrayList rowList=new ArrayList();
				long emiTotal=0;


				if(query.list().size()!=0){
					for(Iterator it=query.iterate();it.hasNext();)
					{
						Object[] row = (Object[]) it.next();
						long zero = 0;
						String loanName 		= (String)(row[0]!=null?row[0]:"");//lmst.loanAdvName
						String empName 			= (String)(row[1]!=null?row[1]:"");//orgEmp.empPrefix||' '||orgEmp.empFname||' '||orgEmp.empMname||' '||orgEmp.empLname
						String loanAccountNo 	= (String)(row[2]!=null?row[2]:"");//loanAccountNo

						//String loanMonth 		= (String)(row[3]!=null?row[3]:"");//Month
						String loanMonth = "";
						if(row[3]!=null)
						{
							sdfObj = new SimpleDateFormat("MMMM-yyyy");
							Timestamp loanTime = (Timestamp)row[3];
							long time = loanTime.getTime();
							Date loanDate = new Date();
							loanDate.setTime(time);
							loanMonth = sdfObj.format(loanDate);
						}

						Long loanAmt 			= (Long)(row[4]!=null?row[4]:zero);//loanPrinAmt/loanInterestAmt
						Long loanInstNo 		= (Long)(row[5]!=null?row[5]:zero);//loanPrinInstNo/loanIntInstNo
						//Long loanEmiAmt 		= (Long)(row[10]!=null?row[10]:(row[6]!=null?row[6]:zero));//loanPrinEmiAmt/loanIntEmiAmt

						Long loanEmiAmt 		= Math.round((Double)(row[6]!=null?row[6]:(zero)));//loanPrinEmiAmt/loanIntEmiAmt


						Long totalRecoveredInst = (Long)(row[7]!=null?row[7]:zero);//totalRecoveredInst/totalRecoveredIntInst
						Long totalRecoveredAmt 	= (Long)(row[8]!=null?row[8]:zero);//totalRecoveredAmt/totalRecoveredInt

						if(IsApproved.equals("no"))
							totalRecoveredAmt+=loanEmiAmt;

						Long balOutStanding 	= new Long(0);//(Long)(row[9]!=null?row[9]:zero);//emp.loanPrinAmt-pdtls.totalRecoveredAmt/emp.loanInterestAmt-idtls.totalRecoveredInt

						balOutStanding=loanAmt-totalRecoveredAmt;
						rowList = new ArrayList();
						/*if( cnt%20==0)
  			{
    				rowList.add(new PageBreak());
    				rowList.add("Data");
  			}*/
						//else
						{

							rowList.add(new Integer(cnt));
							rowList.add(empName);
							rowList.add(loanAccountNo);
							rowList.add("");
							rowList.add(loanAmt);
							rowList.add(loanInstNo);
							rowList.add(loanEmiAmt);
							rowList.add(totalRecoveredInst);
							rowList.add(totalRecoveredAmt);
							rowList.add(balOutStanding);
							emiTotal+=loanEmiAmt;
						}
						DataList.add(rowList); 
						// Added by Rajan for adding B/F & C/F in d report...

						//if(cnt==(finalpagesize-1)||(cnt%(finalpagesize-2)==1&&cnt>(finalpagesize-1)))
						if((cnt%finalpagesize)==0) 
						{
							rowList = new ArrayList();
							rowList.add("");

							StyledData dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData("B/F");                  
							rowList.add(dataStyle1);
							rowList.add("");
							rowList.add("");
							rowList.add("");
							rowList.add("");
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(emiTotal);                  
							rowList.add(dataStyle1);
							// rowList.add(emiTotal);
							rowList.add("");
							rowList.add("");
							rowList.add("");
							DataList.add(rowList);


							rowList = new ArrayList();
							rowList.add(new PageBreak());
							rowList.add("Data");
							for (int j=0;j<8;j++)
							{
								rowList.add("");
							}
							DataList.add(rowList);


							rowList = new ArrayList();
							rowList.add("");
							StyledData dataStyle2 = new StyledData();
							dataStyle2.setStyles(boldStyleVO);
							dataStyle2.setData("C/F");                  
							rowList.add(dataStyle2);
							rowList.add("");
							rowList.add("");
							rowList.add("");
							rowList.add("");

							dataStyle2 = new StyledData();
							dataStyle2.setStyles(boldStyleVO);
							dataStyle2.setData(emiTotal);                  
							rowList.add(dataStyle2);

							//rowList.add(emiTotal);
							rowList.add("");
							rowList.add("");
							rowList.add("");
							DataList.add(rowList);


						}
						// ended by RAJAN

						cnt++;
						/*	              	  
	    	            if( cnt%finalpagesize==0)
	        			{
	      	            	rowList=new ArrayList();
	      	            	rowList.add(new PageBreak());
	          				rowList.add("Data");
	        	            DataList.add(rowList); 
	        			}*/


					} 

					rowList = new ArrayList();

					rowList.add("");
					rowList.add("");
					rowList.add("");
					rowList.add("");
					rowList.add("");
					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData("TOTAL");                  
					rowList.add(dataStyle1);
					// String emiTotalInWords=ConvertNumbersToWord.convert(Math.round(emiTotal));
					dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(emiTotal/*+System.getProperty("line.separator")+emiTotalInWords*/);                  
					rowList.add(dataStyle1);	              
					rowList.add("");
					rowList.add("");
					rowList.add("");
					DataList.add(rowList); 
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
					dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(emiTotal))+" only.");
					dataStyle2.setColspan(10);
					row1.add(dataStyle2);

					for(int c=0;c<10;c++)
						row1.add("");


					DataList.add(row1);
					ReportColumnVO[] rptCol = report.getReportColumns();                  
					SortingHelper Helper = new SortingHelper(DataList);

					report.setReportColumns(rptCol);
					report.initializeDynamicTreeModel();
					report.initializeTreeModel(); 

				}

				return DataList;
			} 

//			end by manoj for loan and advance report
//			by manoj for Festival and FoodGrain advance report
			if(report.getReportCode().equals("5") || report.getReportCode().equals("2500005") || report.getReportCode().equals("18") || report.getReportCode().equals("2500018") || report.getReportCode().equals("19") || report.getReportCode().equals("2500019"))
			{      


				StringBuffer lsb = new StringBuffer(  );      

				String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
				String Designation="";
				String Grade="";
				String Scale="";
				int month=0;
				int year=0;
				String loan_type="";
				String department="";

				String loanCol="adv7057"; // for fest

				department=CheckIfNull(report.getParameterValue( "Department" ));
				//added by samir joshi for bill no wise report
				if(department.equals("")||department.equals("-1"))
					department=	locationId+"";	
				else
					locationId=Long.parseLong(department);
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
				String festivalVal = constantsBundle.getString("festival");
				String foodVal = constantsBundle.getString("foodGrain");
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
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					//logger.info("No Of Rec is********====>"+noOfRec);
					finalpagesize=Integer.parseInt(noOfRec);
				}

				/*	    		StyleVO[] centerboldStyleVO1 = new StyleVO[3];
    	        centerboldStyleVO1[0] = new StyleVO();
    	        centerboldStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
    	        centerboldStyleVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
    	        centerboldStyleVO1[1] = new StyleVO();
    	        centerboldStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
    	        centerboldStyleVO1[1].setStyleValue("Center"); 
    	        centerboldStyleVO1[2] = new StyleVO();
    	        centerboldStyleVO1[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
    	        centerboldStyleVO1[2].setStyleValue("22"); */
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
				//trow4.add(new StyledData(desigName,centerboldStyleVO1));
				tblData.add(trow4);//added second row of the tabular data


				ArrayList trow5 = new ArrayList();

				trow5.add(" ");
				trow5.add(" ");
				trow5.add(new StyledData(deptName,centerboldStyleVO1));
				tblData.add(trow5);//added second row of the tabular data

				ArrayList trow3 = new ArrayList();

				trow3.add(" ");
				trow3.add(" ");
				trow3.add(new StyledData(cityName,centerboldStyleVO1));


				tblData.add(trow3);//added third row of the tabular data

				ArrayList trow6 = new ArrayList();
				trow6.add(" ");
				trow6.add(" ");
				trow6.add(new StyledData(cardexCode,centerboldStyleVO1));
				tblData.add(trow6);//added sixth row of the tabular data

				TabularData tabularData = new TabularData(tblData);//initialize tabular data
				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tabularData.addStyle(IReportConstants.BORDER, "No"); 

				report.setGrandTotalTemplate(tabularData);
				report.setGroupByTotalTemplate(tabularData);
				//

				ArrayList d = new ArrayList();
				ArrayList r = new ArrayList();

				r= new ArrayList();
				r.add(new StyledData("To be Filled in by Drawing Officer",centerboldStyleVO1));
				r.add(new StyledData("To be filled in by Treasury Officer",centerboldStyleVO1));
				d.add(r);
				r= new ArrayList();
				r.add(new StyledData("District         :7  1",centerboldStyleVO1));
				r.add(new StyledData("Voucher No       :",centerboldStyleVO1));
				d.add(r);
				r= new ArrayList();
				r.add(new StyledData("Drawing Officer  : 012",centerboldStyleVO1));
				r.add(new StyledData("Month & Year     :",centerboldStyleVO1));
				d.add(r);
				r= new ArrayList();
				r.add(new StyledData("                      ",centerboldStyleVO1));
				r.add(new StyledData("Major Head       :",centerboldStyleVO1));
				d.add(r);

				TabularData td  = new TabularData(d);
				td.addStyle(IReportConstants.ADDL_HEADER_LOCATION, IReportConstants.VALUE_ADDL_HEADER_LOCATION_BELOW);
				td.addStyle(IReportConstants.BORDER, "No"); 
				report.setAdditionalHeader(td);
				Grade=CheckIfNull(report.getParameterValue( "Grade" ));
				Scale=CheckIfNull(report.getParameterValue( "Scale" ));
				Designation=CheckIfNull(report.getParameterValue( "Designation" ));
				if(!CheckIfNull(report.getParameterValue( "Month" )).equals(""))
				{
					month=Integer.parseInt(CheckIfNull(report.getParameterValue( "Month" )));
				}
				if(!CheckIfNull(report.getParameterValue( "Year" )).equals(""))
				{
					year=Integer.parseInt(CheckIfNull(report.getParameterValue( "Year" )));
				}
				loan_type=CheckIfNull(report.getParameterValue( "AdvanceType" ));

				logger.info(constantsBundle.getString("Food_Advance")+"  loan_type      "+loan_type);

				if(constantsBundle.getString("festival").equalsIgnoreCase(loan_type))
					loanCol="adv7057";
				else if(constantsBundle.getString("foodGrain").equalsIgnoreCase(loan_type))
					loanCol="adv7058";

				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
				SimpleDateFormat sdfObj1 = new SimpleDateFormat("MMM");
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH,month-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				java.util.Date startMonthDate = cal.getTime();
				String startDate  = sdfObj.format(startMonthDate);
				String Month  = sdfObj1.format(startMonthDate);
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH,month-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

				cal.set(Calendar.DAY_OF_MONTH, totalDays);

				java.util.Date endMonthDate = cal.getTime();

				String endDate  = sdfObj.format(endMonthDate);

				Map  requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
				//ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");

				SessionFactory sessionFactory = serv.getSessionFactory();
				Session session = sessionFactory.getCurrentSession();


				String demandNo = "";//CheckIfNull(report.getParameterValue("demandNo")); 
				//(String)requestAttributes.get("demandNo");
				String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
				String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
				String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
				String subHead = CheckIfNull(report.getParameterValue("subHead"));
				//String dtlHead = CheckIfNull(report.getParameterValue("dtlHead"));
				String subheadflag=subHead;
				logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
//				Added by Rajan for checking if Bill no is selected or not..!		    
				if(!BillNo.equals(""))
					report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of Food/Festival Advance  for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
				else
					report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of Food/Festival Advance  for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
//				Ended by Rajan
				//by manoj for head change
				cal = Calendar.getInstance();
				sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
				cal.set(Calendar.YEAR,year);
				cal.set(Calendar.MONTH,month-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				java.util.Date finYrDate = cal.getTime();
				cal.set(Calendar.YEAR,year);
				cal.set(Calendar.MONTH, month-1);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
				endMonthDate = cal.getTime();	        
				endDate  = sdfObj.format(endMonthDate);	            

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

					genDAO.setSessionFactory(serv.getSessionFactory());
					CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
					CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.getLangVOByShortName(langName);  
					long langId = cmnLanguageMst.getLangId();
					PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
					SgvcFinYearMst finYrMst = payDAO.getFinYrInfo(startMonthDate, langId);

					String cols[] = {"demandCode","budmjrhdCode","budsubmjrhdCode","budminhdCode","budsubhdCode","langId","finYrId"};//,"finYrId"
					Object vals[] = {demandNo,mjrHead,subMjrHead,mnrHead,subHead,langName,finYrMst.getFinYearId()};//,String.valueOf(finYrMst.getFinYearId())
					List<SgvaBudsubhdMst> subHeadList = genDAO.getListByColumnAndValue(cols, vals);

					logger.info("*******************finYr*************"+finYrMst.getFinYearId());
					SgvaBudsubhdMst subHeadObj = subHeadList.get(0);
					subHeadId = String.valueOf(subHeadObj.getBudsubhdId()); 
					logger.info("Paybill Subhead ID from following details is " + subHeadId + " from size " + subHeadList.size());
				}


				lsb.append("select  emp.hrLoanAdvMst.loanAdvName,");
				lsb.append(" emp.hrEisEmpMst.orgEmpMst.empPrefix||' '||emp.hrEisEmpMst.orgEmpMst.empFname||' '||emp.hrEisEmpMst.orgEmpMst.empMname||' '||emp.hrEisEmpMst.orgEmpMst.empLname,");
				lsb.append("emp.loanAccountNo,");
				lsb.append("emp.loanDate,");

				lsb.append("emp.loanPrinAmt,");
				lsb.append("emp.loanPrinInstNo,");
				lsb.append("emp.loanPrinEmiAmt,");
				lsb.append("(select phst.totalRecoveredInst from ");
				lsb.append(" HrLoanEmpPrinRecoverHst phst ");

				lsb.append(" where phst.updatedDate >='"+startDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId 	 and phst1.updatedDate >= '"+startDate+"' and phst1.updatedDate <= '"+endDate+"') and ");
				lsb.append(" phst.updatedDate <='"+endDate+"' and ");
				lsb.append(" phst.totalRecoveredAmt !=0 and ");
				lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");

				lsb.append(" ( select phst.totalRecoveredAmt from ");
				lsb.append(" HrLoanEmpPrinRecoverHst phst ");
				lsb.append(" where phst.updatedDate >='"+startDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId 	 and phst1.updatedDate >= '"+startDate+"' and phst1.updatedDate <= '"+endDate+"') and ");
				lsb.append(" phst.updatedDate <='"+endDate+"' and ");
				lsb.append(" phst.totalRecoveredAmt !=0 and ");
				lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");
				lsb.append(" bill."+loanCol+" ");
				/*lsb.append("emp.loanPrinAmt-phst.totalRecoveredAmt ");

	            lsb.append(" ,( ");
	            lsb.append(" select phst.totalRecoveredAmt -hstprevPrin.totalRecoveredAmt ");
	            lsb.append(" from HrLoanEmpPrinRecoverHst hstprevPrin  ");
	            lsb.append(" where hstprevPrin.id.trnCounter = phst.id.trnCounter-1 ");
	            lsb.append(" and hstprevPrin.hrLoanEmpDtls.empLoanId = phst.hrLoanEmpDtls.empLoanId ");
	            lsb.append(" ) ");*/

				lsb.append(" from ");
				lsb.append(" HrPayPaybill bill, ");
				lsb.append(" HrLoanEmpDtls emp, ");
				/*//	          by rahul w.r.t head change
				if(currentMonthBill!=1)
				{
					lsb.append(" PaybillHeadMpg bhm, ");
				}*/
				//by rahul
				lsb.append(" HrEisOtherDtls dtl, ");
				lsb.append(" HrLoanEmpPrinRecoverDtls pdtls, ");

				lsb.append(" OrgUserpostRlt           USRPST, ");
				/*lsb.append(" HrPayOrderHeadMpgHst ORDHD, ");
	            lsb.append(" HrPayOrderHeadPostMpgHst ORDPST, ");*/
				/*if(currentMonthBill!=1)
				{
					lsb.append(" HrPayOrderHeadMpgHst ORDHD, ");
					lsb.append(" HrPayOrderHeadPostMpgHst ORDPST, ");
				}
				else*/
				
				lsb.append(" HrPayOrderHeadMpg ORDHD, ");
				lsb.append(" HrPayOrderHeadPostMpg ORDPST, ");
				

				lsb.append(" OrgPostDetailsRlt pst, ");
				lsb.append(" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm  ");

				lsb.append(" where bill.hrEisEmpMst.empId = emp.hrEisEmpMst.empId and ");

				lsb.append("  bill."+loanCol+"!=0 and ");            
				lsb.append("  emp.empLoanId in (select max(l.empLoanId) from HrLoanEmpDtls l where l.hrEisEmpMst.empId = emp.hrEisEmpMst.empId and l.hrLoanAdvMst.loanAdvId = emp.hrLoanAdvMst.loanAdvId  and l.loanDate<='"+startDate+"' ) and ");            

				// lsb.append(" and ORDPST.orderHeadId = ORDHD.id.orderHeadId ");
				/*if(currentMonthBill!=1)
				{
					lsb.append(" and ORDPST.orderHeadId = ORDHD.id.orderHeadId ");
				}
				else*/
				
				lsb.append(" ORDPST.orderHeadId = ORDHD.orderHeadId ");
				

				lsb.append(" and USRPST.orgPostMstByPostId.postId = ORDPST.postId ");
				lsb.append(" and USRPST.orgUserMst.userId = bill.hrEisEmpMst.orgEmpMst.orgUserMst.userId ");
				lsb.append(" and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId and ");
				lsb.append("   (USRPST.endDate is null or ");
				lsb.append("   (USRPST.endDate>='"+startDate+"' and USRPST.startDate<='"+endDate+"'    )) ");
				lsb.append("   and bhm.approveFlag in (0,1) and ");	            
				lsb.append(" emp.hrEisEmpMst.empId = dtl.hrEisEmpMst.empId and ");
				lsb.append(" pdtls.hrLoanEmpDtls.empLoanId =  emp.empLoanId and ");

				/*lsb.append(" pdtls.totalRecoveredAmt != 0 and ");// for checking recoverd principal if adv emi started or not
	            lsb.append("  (emp.loanPrinAmt >= pdtls.totalRecoveredAmt or ");//for recover principal should be less than total adv
	            lsb.append("(emp.loanPrinAmt - pdtls.totalRecoveredAmt) != 0) and ");*/



				lsb.append("   dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.cmnLanguageMst.langId = 1 and ");
				lsb.append(" emp.hrLoanAdvMst.cmnLanguageMst.langId = 1 and ");
				//modified by mrugesh
				//lsb.append(" emp.hrLoanAdvMst.loanAdvId in ( "+loan_type+") ");

				if(loan_type==null || loan_type=="")
				{
					//logger.info("inside if*****");

					if(report.getReportCode().equals("18") || report.getReportCode().equals("2500018"))
						lsb.append(" emp.hrLoanAdvMst.loanAdvId in ( "+foodVal+")");
					if(report.getReportCode().equals("19") || report.getReportCode().equals("2500019"))
						lsb.append(" emp.hrLoanAdvMst.loanAdvId in ( "+festivalVal+")");
				}
				else
				{
					//logger.info("inside else*****");
					lsb.append(" emp.hrLoanAdvMst.loanAdvId in ( "+loan_type+") ");
				}
				//ended

				if(!empid.equals("")&&!empid.equals("-1"))            	
					lsb.append(" and emp.hrEisEmpMst.empId = '"+empid+"'");


				if(!Grade.equals("")&&!Grade.equals("-1"))          	
					lsb.append(" and dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.gradeId = '"+Grade+"'");

				if(!Scale.equals("")&&!Scale.equals("-1"))            	
					lsb.append(" and dtl.hrEisSgdMpg.hrEisScaleMst.scaleId = '"+Scale+"'");

				if(!Designation.equals("")&&!Designation.equals("-1"))            	
					lsb.append(" and dtl.hrEisSgdMpg.hrEisGdMpg.orgDesignationMst.dsgnId = '"+Designation+"'");

				lsb.append(" and bhm.month ='"+month+"'");

				lsb.append(" and bhm.year = '"+year+"'");

				if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
				{
					lsb.append(" and ORDHD.subheadId  = '"+subHeadId+"'");
				}

				if(!department.equals("")&&!department.equals("-1"))
					lsb.append(" and pst.cmnLocationMst.locId="+department+"  ");

				lsb.append(" and pst.orgPostMst.postId = bill.orgPostMst.postId  ");
				if(isBillDefined&&!BillNo.equals(""))
				{

					//lsb.append("  and  bill.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId ) and ") ; 
					//if(currentMonthBill==1)
						//lsb.append("   ORDHD.subheadId in (select bill.subheadId from HrPayBillHeadMpg bill where bill.billId ="+BillNo+" ) and  ");
					lsb.append(" and hpbsm.billId="+BillNo+"  ");
				}
				else
				{
					if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
					{
						lsb.append(" and   ORDHD.subheadId ="+subheadCode+" ");
					}
					if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
					{
						lsb.append("  and bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")");
					}
					if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
					{
						lsb.append("  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ");
					}
				}
				//if(currentMonthBill!=1)
				lsb.append("  and bhm.hrPayPaybill = bill.paybillGrpId  and hpbsm.billHeadId = bhm.billNo.billHeadId  ");
				lsb.append(" and bhm.orgGradeMst.gradeId=bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId "); //Date 5-Nov-08 by rahul
				lsb.append("  order by emp.loanAccountNo ");


				String strQuery = lsb.toString(); 

				logger.info("the query is "+strQuery);
				Query query = session.createQuery(strQuery);

				int cnt=1;
				long empId=0;

				ArrayList rowList=new ArrayList();
				int emiTotal=0;
				for(Iterator it=query.iterate();it.hasNext();)
				{

					Object[] row = (Object[]) it.next();
					long zero = 0;
					String loanName 		= (String)(row[0]!=null?row[0]:"");//lmst.loanAdvName
					String empName 			= (String)(row[1]!=null?row[1]:"");//orgEmp.empPrefix||' '||orgEmp.empFname||' '||orgEmp.empMname||' '||orgEmp.empLname
					String loanAccountNo 	= (String)(row[2]!=null?row[2]:"");//loanAccountNo
					//String loanMonth 		= (String)(row[3]!=null?row[3]:"");//Month
					if(row[3]!=null)
					{
						sdfObj = new SimpleDateFormat("MMMM-yyyy");
						Timestamp loanTime = (Timestamp)row[3];
						long time = loanTime.getTime();
						Date loanDate = new Date();
						loanDate.setTime(time);
						String loanMonth = sdfObj.format(loanDate);
					}

					Long loanAmt 			= (Long)(row[4]!=null?row[4]:zero);//loanPrinAmt
					Long loanInstNo 		= (Long)(row[5]!=null?row[5]:zero);//loanPrinInstNo
					Long loanEmiAmt 		= Math.round((Double)(row[9]!=null?row[9]:(zero)));//loanPrinEmiAmt
					Long totalRecoveredInst = (Long)(row[7]!=null?row[7]:zero);//totalRecoveredInst
					Long totalRecoveredAmt 	= (Long)(row[8]!=null?row[8]:zero);//totalRecoveredAmt
					Long balOutStanding 	= new Long(0);//(Long)(row[9]!=null?row[9]:zero);//emp.loanPrinAmt-pdtls.totalRecoveredAmt
					balOutStanding=loanAmt-totalRecoveredAmt;
					rowList = new ArrayList();
					/*if( cnt%20==0)
  			{
    				rowList.add(new PageBreak());
    				rowList.add("Data");
  			}*/
					//else
					{
						rowList.add(new Integer(cnt));
						rowList.add(empName);
						rowList.add(loanEmiAmt);
						rowList.add(totalRecoveredInst+"/"+loanInstNo);
						rowList.add(loanAmt);
						rowList.add(totalRecoveredAmt);
						rowList.add(balOutStanding);
						emiTotal+=loanEmiAmt;
					}
					DataList.add(rowList); 
					// Added by Rajan for B/F & C/F

					// if(cnt==(finalpagesize-1)||(cnt%(finalpagesize-2)==1&&cnt>(finalpagesize-1)))
					if((cnt%finalpagesize)==0) 

					{
						rowList = new ArrayList();
						rowList.add("");

						StyledData dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData("B/F");                  
						rowList.add(dataStyle1);

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(emiTotal);
						rowList.add(dataStyle1);

						//rowList.add(emiTotal);
						rowList.add("");
						rowList.add("");
						rowList.add("");
						rowList.add("");
						DataList.add(rowList);


						rowList = new ArrayList();
						rowList.add(new PageBreak());
						rowList.add("Data");
						DataList.add(rowList);


						rowList = new ArrayList();
						rowList.add("");
						StyledData dataStyle2 = new StyledData();
						dataStyle2.setStyles(boldStyleVO);
						dataStyle2.setData("C/F");                  
						rowList.add(dataStyle2);

						dataStyle2 = new StyledData();
						dataStyle2.setStyles(boldStyleVO);
						dataStyle2.setData(emiTotal);
						rowList.add(dataStyle2);
						//rowList.add(emiTotal);
						rowList.add("");
						rowList.add("");
						rowList.add("");
						rowList.add("");
						DataList.add(rowList);


					}

					//ended by Rajan

					cnt++;

					/*  if( cnt%finalpagesize==0)
  			{
	            	rowList=new ArrayList();
	            	rowList.add(new PageBreak());
    				rowList.add("Data");
  	            DataList.add(rowList); 
  			}*/

				} 

				rowList = new ArrayList();
				rowList.add("");
				StyledData dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData("TOTAL");                  
				rowList.add(dataStyle1);
				//  String emiTotalInWords=ConvertNumbersToWord.convert(Math.round(emiTotal));
				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);

				dataStyle1.setData(emiTotal/*+System.getProperty("line.separator")+emiTotalInWords*/);                  
				rowList.add(dataStyle1);	              


				rowList.add("");
				rowList.add("");
				rowList.add("");
				rowList.add("");

				DataList.add(rowList); 
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
				dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(emiTotal))+" only.");
				dataStyle2.setColspan(7);
				row1.add(dataStyle2);

				for(int c=0;c<6;c++)
					row1.add("");


				DataList.add(row1);


				ReportColumnVO[] rptCol = report.getReportColumns();                  
				SortingHelper Helper = new SortingHelper(DataList);

				report.setReportColumns(rptCol);
				report.initializeDynamicTreeModel();
				report.initializeTreeModel(); 


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


		if( report.getReportCode().equals("3") || report.getReportCode().equals("2500003") || report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("5") || report.getReportCode().equals("2500005") || report.getReportCode().equals("6") || report.getReportCode().equals("2500006") || report.getReportCode().equals("7") || report.getReportCode().equals("2500007") || report.getReportCode().equals("8") || report.getReportCode().equals("2500008") || report.getReportCode().equals("9") || report.getReportCode().equals("2500009") || report.getReportCode().equals("10") || report.getReportCode().equals("2500010")|| report.getReportCode().equals("11") || report.getReportCode().equals("2500011")|| report.getReportCode().equals("13") || report.getReportCode().equals("2500013"))
		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("LoanType",37+"");
			report.setParameterValue("ReportFor",300428+"~Principal");
			//added by ravysh
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		} 



		return report;
	}

}








