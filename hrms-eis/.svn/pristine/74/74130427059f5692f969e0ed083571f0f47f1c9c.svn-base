package com.tcs.sgv.eis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import com.tcs.sgv.common.event.reports.ReportEvent;
import com.tcs.sgv.common.event.reports.ReportEventListener;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.util.reports.ReportData;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.DBConnection;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class PayBillReportDAO extends DefaultReportDataFinder 
implements ReportDataFinder,ReportEventListener
{
	private static Logger logger = Logger.getLogger(PayBillReportDAO.class );

	int pagesize=12;


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
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	public final long paybillTypeId=Integer.parseInt(resourceBundle.getString("paybillTypeId"));
	public final long arrearbillTypeId=Integer.parseInt(resourceBundle.getString("arrearbillTypeId"));
	//added by ravysh for multiple month bill
	public final long supplBill_Multiple_Month=Integer.parseInt(resourceBundle.getString("multipleMonthSupplBill"));

	long parentlocationId=Long.parseLong(resourceBundle.getString("PARENTID"));

	int paybillmonth=Integer.parseInt(resourceBundle.getString("gradePayMonth"));
	int paybillyear=Integer.parseInt(resourceBundle.getString("gradePayYear"));

	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		String langName=report.getLangId();

		Connection lCon = null;
		Statement lStmt = null;

		DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
		String getNCHAR=sbConf.getNCHAR();
		
		
		long trnbillNo=0;

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();

		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");

		PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		boolean isBillDefined = paybillDAO.isBillsDefined(locationId);


		String upperfooter="UNDER SECRETARY TO GOVT.";
		String lowerfooter="GANDHINAGAR"+System.getProperty("line.separator")+"Code No. PG3";

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
		colorStyleVO[0].setStyleValue("black");
		selfCloseVO = new StyleVO[1];
		selfCloseVO[0] = new StyleVO();
		selfCloseVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
		selfCloseVO[0].setStyleValue("javascript:self.close()"); 

		//Added by Urvin for setting up font size
		StyleVO[] baseFont = new StyleVO[1];
		baseFont[0] = new StyleVO(  );
		baseFont[0].setStyleId( IReportConstants.STYLE_FONT_SIZE );
		baseFont[0].setStyleValue( "10" );

		/*baseFont[1] = new StyleVO(  );
	        baseFont[1].setStyleId( IReportConstants.STYLE_FONT_COLOR );
	        baseFont[1].setStyleValue( IReportConstants.VALUE_FONT_COLOR_DARKGRAY );*/
		//report.setStyleList(baseFont);
		ReportTemplate rt = new ReportTemplate();
		rt.put(IReportConstants.TMPLT_COLUMN_HEADER,baseFont);
		rt.put(IReportConstants.TMPLT_BASE_FONT,baseFont);
		report.setReportTemplate( rt );
		//Added by Ravish for setting up font size

		//added by samir joshi fro header in all pages
		//added by Samir Joshi fro each page Header
		ReportAttributeVO ravo1 = new ReportAttributeVO();
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_LOCATION);
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_ON_EACH_PAGE);
		ravo1.setAlignment(IReportConstants.HEADER_ALIGN_CENTER);
		report.addReportAttributeItem(ravo1);
		report.setAdditionalHeader("");
		//ended by samir joshi
		//

		try   
		{
			lCon = DBConnection.getConnection(  );
			lStmt = lCon.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			logger.info("getReportCode"+report.getReportCode());
			dataStyle = new StyledData();
			String desigName="";
			String deptName="";
			String cityName="";
			String cardexCode="";
			//ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");	
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

			if(report.getReportCode().equals("2") || report.getReportCode().equals("2500002"))
			{  
				logger.info("ReportCode :::"+report.getReportCode());
				long AISGradeCode=Long.parseLong(constantsBundle.getString("AISGradeCode"));
				long GradeCode3=Long.parseLong(constantsBundle.getString("GradeCode3"));
				long GradeCode4=Long.parseLong(constantsBundle.getString("GradeCode4"));
				long GradeCode1=Long.parseLong(constantsBundle.getString("GradeCode1"));
				long GradeCode2=Long.parseLong(constantsBundle.getString("GradeCode2"));


				String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
				String Grade="";
				String Scale="";
				String month="";
				String year="";
				String category="";
				String BillNoinner="";//GAD specific
				String portType="";
				String subheadCode="";
				String classIds="";
				String desgnIds="";
				String BillNo="";
				String classtoprint = "";
				String TokenFlag="";// give you flag come from token number screen

				BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
				classtoprint=CheckIfNull(report.getParameterValue( "classtoprint" ));
				
				logger.info("BillNoinner fromperametest======>"+BillNoinner);
				String billNo = CheckIfNull(report.getParameterValue( "billNo" ));
				logger.info("from outer billNo=====>"+billNo);
				TokenFlag = CheckIfNull(report.getParameterValue( "Flag" ));
				if(!BillNoinner.equalsIgnoreCase("") && !BillNoinner.equalsIgnoreCase("-1"))
				{
					logger.info("the innser bill no is "+BillNoinner);
					StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
					int l=0;


					while(st1.hasMoreTokens())
					{
						if(l==0)
							subheadCode=st1.nextToken();
						else if(l==1)
							classIds=st1.nextToken();
						else if(l==2)
							desgnIds=st1.nextToken();
						else if(l==3)
							portType=st1.nextToken().trim();
						else if(l==4)

							BillNo=st1.nextToken();



						l++;
					} 
					logger.info("BillNo from inner=====>"+BillNo);
				}
				else if(!billNo.equalsIgnoreCase("") && !billNo.equalsIgnoreCase("-1"))
				{

					trnbillNo=Long.parseLong(billNo);
					BillNo=paybillDAO.getBillnoFormTrnBillReg(trnbillNo, locationId);
					logger.info("BillNo from outer=====>"+BillNo);
				}
				String before=" ";

				CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
				CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.getLangVOByShortName(langName);  
				long langId = cmnLanguageMst.getLangId();


				//Added by Akshay
				String fname = CheckIfNull(report.getParameterValue("FName"));
				String lname = CheckIfNull(report.getParameterValue("LName"));
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				logger.info(" nof rec out====>"+noOfRec);
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					logger.info(" nof rec in====>"+noOfRec);
					pagesize=Integer.parseInt(noOfRec);
				}


				//Ended by Akshay

				//Added by Paurav for integrating Head wise Report

				String deptNo = CheckIfNull(report.getParameterValue("Department"));

				if(!deptNo.equals("")&&!deptNo.equals("-1"))
					locationId=Long.parseLong(deptNo);	

				String demandNo = "";//CheckIfNull(report.getParameterValue("demandNo")); 
				//(String)requestAttributes.get("demandNo");
				String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
				String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
				String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
				String subHead = CheckIfNull(report.getParameterValue("subHead"));
				//String dtlHead = CheckIfNull(report.getParameterValue("dtlHead"));
				String subheadflag=subHead;
				logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
				PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				//SgvcFinYearMst finYrMst = null;
				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdfObj;
				if(report.getReportCode().equals("2500002"))
					sdfObj = new SimpleDateFormat("yyyy-MM-dd");
				else
					sdfObj = new SimpleDateFormat("dd-MMM-yyyy");

				
				
				

				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				java.util.Date finYrDate = cal.getTime();
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
				java.util.Date endMonthDate = cal.getTime();

				String endDate  = sdfObj.format(endMonthDate);
				String startMonthDate  = sdfObj.format(finYrDate);



				//by manoj for head change
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
					logger.info(i+"        sdgdfhgfHead Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
					GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(SgvaBudsubhdMst.class);
					genDAO.setSessionFactory(serv.getSessionFactory());
					String cols[] = {"demandCode","budmjrhdCode","budsubmjrhdCode","budminhdCode","budsubhdCode","langId","finYrId"};//,"finYrId"
					SgvcFinYearMst finYrMst = payDAO.getFinYrInfo(finYrDate, langId);
					Object vals[] = {demandNo,mjrHead,subMjrHead,mnrHead,subHead,langName,finYrMst.getFinYearId()};//,String.valueOf(finYrMst.getFinYearId())
					List<SgvaBudsubhdMst> subHeadList = genDAO.getListByColumnAndValue(cols, vals);
					logger.info("*******************finYr*************"+finYrMst.getFinYearId());
					SgvaBudsubhdMst subHeadObj = subHeadList.get(0);
					subHeadId = String.valueOf(subHeadObj.getBudsubhdId()); 
					logger.info("Paybill Subhead ID from following details is " + subHeadId + " from size " + subHeadList.size());
					//String subHeadId = "68112"; //hard coded. was just used for testing
				}
				sdfObj = new SimpleDateFormat("MMM");
				String Month = sdfObj.format(finYrDate);
				// report.setReportName(locationNameincaps+System.getProperty("line.separator")+"PAYBILL for  the month of "+Month+". "+year+System.getProperty("line.separator")+"Bill No:"); 

				ReportColumnVO[] rptCol = report.getReportColumns();  
				//Ended By Paurav

				String billType="";


				billType=CheckIfNull(report.getParameterValue( "billTypePara" ));
				//billType="arrear";
				if(billType.equals("") || billType.equals(" ") || billType.equals("-1"))
					billType = String.valueOf(paybillTypeId);


				Grade=CheckIfNull(report.getParameterValue( "Grade" ));
				Scale=CheckIfNull(report.getParameterValue( "Scale" ));
				String [] lstrDesgnIdlst=(String [])report.getParameterValue("Designation");
				//Added By Paurav for passing comma seperated designations for payslip generation
				String dsgnIds = "";
				//Ended By Paurav
				boolean bFlag =false;

				// This is to check whether the Employee List is selected or not
				for(int i=0;i<lstrDesgnIdlst.length && bFlag==false;i++)
				{

					if(!lstrDesgnIdlst[i].equalsIgnoreCase("-1"))
						bFlag=true;
				} 
				//Designation=CheckIfNull(report.getParameterValue( "Designation" ));

				category=CheckIfNull(report.getParameterValue( "Category" ));

				//Added By Paurav for getting IFMS Bill Register no





				//Ended By Paurav

				//Changed By Paurav. mpg.PAYBILL_COL added by Paurav for taking paybill column names

				String sqlQuery = "select mpg.column_no colNo,mpg.viewflag, mpg.COLUMN_ORDER_NUMBER officer,r.edp_code edp_code, r.edp_short_name sname,mpg.PAYBILL_COL from rlt_edp_code_mpg mpg,RLT_BILL_TYPE_EDP r where r.type_edp_id=mpg.type_edp_id ";

				if(locationId!=1000)
					sqlQuery+="and mpg.loc_id = "+locationId;
				sqlQuery+=" order by mpg.column_no,mpg.COLUMN_ORDER_NUMBER   ";
				logger.info("the query for the column is "+sqlQuery);
				lRs1 = lStmt.executeQuery(sqlQuery);
				ArrayList rowList=new ArrayList();
				ArrayList headerList=new ArrayList();
				colorStyleVO[0].setStyleValue("black");
				dataStyle = new StyledData();
				dataStyle.setData("Sr. No.");                                    
				dataStyle.setStyles(colorStyleVO);                  
				headerList.add(dataStyle);
				dataStyle = new StyledData();
				String summaryPageNo="   Summary Page No.   ";
				String lindemup="   ";
				String lindemdw="   ";
				dataStyle.setData("Pay Scale &"+System.getProperty("line.separator")+"Employee Name"+System.getProperty("line.separator")+"OR"+System.getProperty("line.separator")+summaryPageNo.replaceAll(" ",constantsBundle.getString("blankSpace")));                                    
				dataStyle.setStyles(colorStyleVO);                  
				headerList.add(dataStyle);

				//Added by Paurav to set column header dynamically
				rptCol[0].setColumnHeader("Sr. No.");
				rptCol[1].setColumnHeader(""+lindemdw.replaceAll(" ",constantsBundle.getString("blankSpace")+System.getProperty("line.separator"))+"Pay Scale &"+System.getProperty("line.separator")+"Employee Name"+System.getProperty("line.separator")+"OR"+System.getProperty("line.separator")+summaryPageNo.replaceAll(" ",constantsBundle.getString("blankSpace"))+lindemup.replaceAll(" ",constantsBundle.getString("blankSpace")+System.getProperty("line.separator")));
				//Ended By Paurav

				//rowList.add("Psr. No.");
				//rowList.add("Pay Scale"+System.getProperty("line.separator")+"Emp Code"+System.getProperty("line.separator")+"Emp Name.");
				int prevCol=2;
				int curCol;
				int i=2;

				Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);


				ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");

				SessionFactory sessionFactory = serviceLocator.getSessionFactory();
				Session session= sessionFactory.getCurrentSession();	           

				Session hibSession = sessionFactory.getCurrentSession();


				//logger.info("***Query for Paybill details**" +query);



				ArrayList dataList=new ArrayList();


				int count=1;
				long empId=0;

				int viewflag=0;
				while(lRs1.next())
				{
					StringBuffer sbVal=new StringBuffer(  );
					curCol=Integer.parseInt(lRs1.getString("colNo"));

					sbVal.append(lRs1.getString("edp_code")+System.getProperty("line.separator")+lRs1.getString("sname"));
					//rowList.add(lRs1.getString("edp_code")+System.getProperty("line.separator")+lRs1.getString("sname"));
					prevCol=curCol;
					while(lRs1.next())
					{	
						logger.info("the view flag is "+viewflag);
						curCol=Integer.parseInt(lRs1.getString("colNo"));
						if(curCol ==prevCol)
						{
							viewflag=0;	            		
							logger.info("inside if and prev value is "+prevCol+" and cur value is"+curCol+" Pay Bill Col is " + lRs1.getString("PAYBILL_COL"));

							viewflag=Integer.parseInt(lRs1.getString("viewflag"));
							if(viewflag==0)
								sbVal.append(System.getProperty("line.separator")+lRs1.getString("edp_code")+System.getProperty("line.separator")+lRs1.getString("sname"));
							else if(viewflag==1)
								sbVal.append(System.getProperty("line.separator")+lRs1.getString("edp_code"));
							else if(viewflag==2)
								sbVal.append(System.getProperty("line.separator")+lRs1.getString("sname"));
							else if(viewflag==3)
								sbVal.append(System.getProperty("line.separator"));

							prevCol=curCol;
						}
						else
						{
							lRs1.previous();
							break;
						}
					}
					dataStyle = new StyledData();
					logger.info("******dn*****"+sbVal.toString());
					dataStyle.setData(sbVal.toString());                                    
					dataStyle.setStyles(colorStyleVO);                  
					headerList.add(dataStyle);
					//rowList.add(sbVal.toString());

					//Added by Paurav to dynamically set Column Header
					rptCol[i].setColumnHeader(sbVal.toString());
					//Ended By Paurav
					i++; 
				}
				headerList.add(" ");
				headerList.add(" page");
				
				
				

				rowList=new ArrayList();
				int cnt=0;
				int displayCnt=1;
				int pagecnt=1;


				colorStyleVO[0].setStyleValue("black");
				dataStyle = new StyledData();
				StringBuffer query = new StringBuffer();
				
				long trnBillNo= 0;
				if(!BillNo.equals(""))	
				{	
				if(TokenFlag != null && TokenFlag.equals("YES"))
					trnBillNo = Long.parseLong(CheckIfNull(report.getParameterValue( "billNo" ).toString()));
				else 
					trnBillNo = paybillDAO.getTrnBillNoFrombillNoMonthYear(Long.parseLong(BillNo),Integer.parseInt( month),Integer.parseInt( year), locationId,Long.parseLong(billType), CheckIfNull(report.getParameterValue( "Arrear List" )));
				if(trnBillNo>0)
				{	
				String outerLinks="<a target='_blank' href='hrms.htm?actionFlag=printOuter&classtoprint="+classtoprint+"&billNo=" +trnBillNo+ " '> <u> Print Outer </u></a>"
				+System.getProperty("line.separator")
				+"<a target='_blank' href='hrms.htm?actionFlag=printCertificate&billNo=" +trnBillNo+ " '> <u> Print Certificate</u></a>";

				if(!sbConf.isMySQL())
				{		
					outerLinks+=System.getProperty("line.separator")
				    +"<a target='_blank' href='hrms.htm?actionFlag=reportService&reportCode=106&action=generateReport&FromParaPage=TRUE&backBtn=0&Month="+month+"&Year="+year+"&BillNo_Frm_Paybill=" +trnBillNo+ " '> <u>Salary Difference Report</u></a>";
				}
				
				if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{
					outerLinks+=System.getProperty("line.separator")
				    +"<a target='_blank' href='ifms.htm?actionFlag=OuterArrearPrint&billNo=" +trnBillNo+ " '> <u>Print Arrear Report</u></a>";
					outerLinks+=System.getProperty("line.separator")
					+"<a target='_blank' href='ifms.htm?actionFlag=OuterGPDiffArrearPrint&billNo=" + trnBillNo + "&billTypeId=" + billType + "&billMonth=" + month +"&billYear=" + year +" '> <u>Print GP Difference Arrear Report</u></a>";
				}
				
				
				report.setReportName(outerLinks); 
				}
				} 
				query.append("  select hreisother1_.EMP_ID,  ");
				
				//query.append("  orgdesigna9_.dsgn_shrt_name,  ");
				
				query.append("  case when max(dsgn.dsgn_shrt_name) is null  ");
				query.append("  then  ");
				query.append("  orgdesigna9_.dsgn_shrt_name  ");
				query.append("  else max(dsgn.dsgn_shrt_name) end dsgn_name ,  ");
				
				query.append("  orgempmst11_.grade_id,  ");
				query.append("  orggradems14_.Grade_Name,  ");
				query.append("  concat(orgempmst11_.emp_prefix, concat(' ', orgempmst11_.emp_fname)),  ");
				query.append("  hreisother1_.OTHER_CURRENT_BASIC,  ");
				query.append("  hreisscale4_.scale_start_amt,  ");
				query.append("  sum(hrpaypaybi0_.SPL_PAY),  ");
				query.append("  sum(hrpaypaybi0_.PER_PAY),  ");
				query.append("  sum(hrpaypaybi0_.PO) as col_9_0_,  ");
				query.append("  sum(hrpaypaybi0_.PE) as col_10_0_,  ");
				query.append("  sum(hrpaypaybi0_.LS) as col_11_0_,  ");
				query.append("  sum(hrpaypaybi0_.LE) as col_12_0_,  ");
				query.append("  sum(hrpaypaybi0_.D_PAY) as col_13_0_,  ");
				query.append("  sum(hrpaypaybi0_.DA) as col_14_0_,  ");
				query.append("  sum(hrpaypaybi0_.HRA) as col_15_0_,  ");
				query.append("  sum(hrpaypaybi0_.LTC) as col_16_0_,  ");
				query.append("  sum(hrpaypaybi0_.CLA) as col_17_0_,  ");
				query.append("  sum(hrpaypaybi0_.OTHER_ALLOW) as col_18_0_,  ");
				query.append("  sum(hrpaypaybi0_.MA) as col_19_0_,  ");
				query.append("  sum(hrpaypaybi0_.BONUS) as col_20_0_,  ");
				query.append("  sum(hrpaypaybi0_.WA) as col_21_0_,  ");
				query.append("  sum(hrpaypaybi0_.OTHER_CHRGS) as col_22_0_,  ");
				query.append("  sum(hrpaypaybi0_.TRANS_ALL) as col_23_0_,  ");
				query.append("  sum(hrpaypaybi0_.FES_ADV) as col_24_0_,  ");
				query.append("  sum(hrpaypaybi0_.FOOD_ADV) as col_25_0_,  ");
				query.append("  sum(hrpaypaybi0_.PAY_RECOVER) as col_26_0_,  ");
				query.append("  sum(hrpaypaybi0_.GROSS_AMT) as col_27_0_,  ");
				query.append("  sum(hrpaypaybi0_.SLO) as col_28_0_,  ");
				query.append("  sum(hrpaypaybi0_.IT) as col_29_0_,  ");
				query.append("  sum(hrpaypaybi0_.SURCHARGE) as col_30_0_,  ");
				query.append("  sum(hrpaypaybi0_.HRR) as col_31_0_,  ");
				query.append("  sum(hrpaypaybi0_.RENT_B) as col_32_0_,  ");
				query.append("  sum(hrpaypaybi0_.PLI) as col_33_0_,  ");
				query.append("  sum(hrpaypaybi0_.BLI) as col_34_0_,  ");
				query.append("  sum(hrpaypaybi0_.PT) as col_35_0_,  ");
				query.append("  sum(hrpaypaybi0_.SIS_GIS_1979) as col_36_0_,  ");
				query.append("  sum(hrpaypaybi0_.SIS_IF_1981) as col_37_0_,  ");
				query.append("  sum(hrpaypaybi0_.SIS_SF_1981) as col_38_0_,  ");
				query.append("  sum(hrpaypaybi0_.AIS_IF_1980) as col_39_0_,  ");
				query.append("  sum(hrpaypaybi0_.AIS_SF_1980) as col_40_0_,  ");
				query.append("  sum(hrpaypaybi0_.GPF_C) as col_41_0_,  ");
				query.append("  sum(hrpaypaybi0_.GPF_ADV) as col_42_0_,  ");
				query.append("  sum(hrpaypaybi0_.CAR_SCT_MOPED_ADV) as col_43_0_,  ");
				query.append("  sum(hrpaypaybi0_.OCA_CYCLE_ADV) as col_44_0_,  ");
				query.append("  sum(hrpaypaybi0_.HBA) as col_45_0_,  ");
				query.append("  sum(hrpaypaybi0_.FAN_ADV) as col_46_0_,  ");
				query.append("  sum(hrpaypaybi0_.JEEP_R) as col_47_0_,  ");
				query.append("  sum(hrpaypaybi0_.MISC_RECOV) as col_48_0_,  ");
				query.append("  sum(hrpaypaybi0_.GPF_IV) as col_49_0_,  ");
				query.append("  sum(hrpaypaybi0_.TOTAL_DED) as col_50_0_,  ");
				query.append("  sum(hrpaypaybi0_.NET_TOTAL) as col_51_0_,  ");
				query.append("  sum(hrpaypaybi0_.DP_GAZZETED) as col_52_0_,  ");
				query.append("  hreisother1_.PHY_CHALLENGED as col_53_0_,  ");
				query.append("  sum(hrpaypaybi0_.CAR_SCT_MOPED_INT) as col_54_0_,  ");
				query.append("  sum(hrpaypaybi0_.OCA_CYCLE_INT) as col_55_0_,  ");
				query.append("  sum(hrpaypaybi0_.HBA_INT) as col_56_0_,  ");
				query.append("  sum(hrpaypaybi0_.FAN_INT) as col_57_0_,  ");
				query.append("  max(orgempmst11_.emp_gpf_num) as col_58_0_,  ");
				query.append("  sum(hrpaypaybi0_.GPF_IV_ADV) as col_59_0_,  ");
				query.append("  (select hrpayorder24_.ORDER_NAME  ");
				query.append("  from HR_PAY_ORDER_MST hrpayorder24_  ");
				query.append("  where hrpayorder24_.ORDER_ID = hrpayorder6_.ORDER_ID) as col_60_0_,  ");
				query.append("  hrpayorder6_.ORDER_ID as orderId,  ");
				query.append("  hreisempms10_.EMP_TYPE as emptype,  ");
				query.append("  (select hrpayorder24_.order_date ");
				query.append("  from HR_PAY_ORDER_MST hrpayorder24_");
				query.append("  where hrpayorder24_.ORDER_ID = hrpayorder6_.ORDER_ID) as orderdate,  ");
				query.append("  orgdesigna9_.dsgn_id dsgnId,  ");
				query.append("  sum(hrpaypaybi0_.CPF) as CPF,  ");
				query.append("  sum(hrpaypaybi0_.AIS_PF) as AISPF,  ");
				query.append("  orgempmst11_.emp_mname,  ");
				query.append("  orgempmst11_.emp_lname, ");
				query.append("  hreisscale4_.scale_end_amt,  ");
				query.append("  hreisscale4_.scale_higher_end_amt,  ");
				
				if(isBillDefined)
				{
					
					query.append("   max(hrpaypaybi0_.psr_no) psr, ");		// Added By Urvin
				// Commented By Urvin Shah		
				/*query.append("  (select max(psr.psr_no) ");
				query.append("  from hr_pay_post_psr_mpg psr ");
				query.append("  where psr.post_id = orgpostdet8_.post_id) psr,  ");*/
				}
				else
				{
					query.append(" orgpostdet8_.post_id, ");
				}
				query.append("  (select payfix.rev_pay - payfix.prev_pay ");
				query.append("  from hr_payfix_mst payfix ");
				query.append("  where payfix.user_id = orgempmst11_.user_id and payfix.lang_id = "+langId+" and ");
				query.append("  payfix.pay_fix_date between '"+startMonthDate+"' and '"+endDate+"' and ");
				query.append("  payfix.active_flag = 'Y' and payfix.is_defined = 'N') payIncr,  ");
				query.append("  (select lm.lookup_id ");
				query.append("  from cmn_lookup_mst lm ");
				query.append("  where lm.lookup_id = orgpostmst10_.post_type_lookup_id) posttype,  ");
				query.append("  orgpostmst10_.end_date,count(hreisother1_.EMP_ID),");
				
				

				if(billType.equals(String.valueOf(arrearbillTypeId)) )
					query.append(" sum(hrpaypaybi0_.gpay) scale_grade_pay,");// added by rahul
				else
					query.append(" hreisscale4_.scale_grade_pay scale_grade_pay,");
				query.append(" sum(hrpaypaybi0_.DA_GPF),sum(hrpaypaybi0_.DA_GPFIV) "); 
				
				
				query.append("  ,(select concat(office.office_no, concat('.',office.office_name))  from hr_pay_office_mst office,hr_pay_officepost_mpg ompg where ompg.post_id = hrpaypaybi0_.post_id and ompg.office_id = office.office_id " +
						" and (ompg.end_date is null or (ompg.start_date <='"+endDate+"' and ompg.end_date  >='"+startMonthDate+"')) ) ");
				query.append("  ,(select ompg.remarks from hr_pay_office_mst office,hr_pay_officepost_mpg ompg where ompg.post_id = hrpaypaybi0_.post_id and ompg.office_id = office.office_id " +
						" and (ompg.end_date is null or (ompg.start_date <='"+endDate+"' and ompg.end_date  >='"+startMonthDate+"')) ) ");

				//added by ravysh for multiple month bill
				if(Long.parseLong(billType)==supplBill_Multiple_Month){
				query.append(",hrpaypaybi0_.paybill_month,hrpaypaybi0_.paybill_year");
				query.append(",(select count(hh.emp_id) from HR_PAY_PAYBILL hh where hh.emp_id=hrpaypaybi0_.emp_id and hh.paybill_grp_id=hrpaypaybi0_.paybill_grp_id) emp_count");
				}
				if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{     
					logger.info("getting value from arrear paybill table");
					query.append(" from Hr_Pay_Arrear_Paybill             hrpaypaybi0_ ");
				}
				else
				{    
					logger.info("getting value from paybill paybill table");
					query.append(" from HR_PAY_PAYBILL             hrpaypaybi0_ "); 
				}


				query.append("  left outer join hr_eis_emp_mst hreisempms10_ on hrpaypaybi0_.EMP_ID = hreisempms10_.EMP_ID  ");
				query.append("  left outer join org_emp_mst orgempmst11_ on hreisempms10_.emp_mpg_ID = orgempmst11_.EMP_ID  and orgempmst11_.lang_id= "+langId+" ");
				query.append("  left outer join org_grade_mst orggradems14_ on orgempmst11_.grade_id = orggradems14_.Grade_Id  and orggradems14_.lang_id= "+langId+" ");
				
				//query.append("  left outer join HR_EIS_OTHER_DTLS hreisother1_ on hrpaypaybi0_.EMP_ID = hreisother1_.EMP_ID  ");
				query.append("  left outer join HR_EIS_OTHER_DTLS_HST hreisother1_ on hrpaypaybi0_.EMP_ID = hreisother1_.EMP_ID and hreisother1_.trn_counter = hrpaypaybi0_.other_trn_cntr ");
				
				query.append("  left outer join HR_EIS_SGD_MPG hreissgdmp2_ on hreisother1_.EMP_SGD_ID = hreissgdmp2_.SGD_MAP_ID  ");
				query.append("  left outer join HR_EIS_GD_MPG hreisgdmpg3_ on hreissgdmp2_.SGD_GD_ID = hreisgdmpg3_.GD_MAP_ID  ");
				query.append("  left outer join hr_eis_scale_mst hreisscale4_ on hreissgdmp2_.SGD_SCALE_ID = hreisscale4_.scale_id and hreisscale4_.lang_id= "+langId+ " ");
				//rahul
				//query.append("  left outer join org_userpost_rlt orguserpos5_ on hrpaypaybi0_.post_id = orguserpos5_.post_id  and orguserpos5_.activate_flag=1");
				query.append("  left outer join HR_PAY_ORDER_HEAD_POST_MPG hrpayorder7_ on hrpaypaybi0_.post_id = hrpayorder7_.post_id  ");
				query.append("  left outer join HR_PAY_ORDER_HEAD_MPG hrpayorder6_ on hrpayorder7_.ORDER_HEAD_ID = hrpayorder6_.ORDER_HEAD_ID  "); 
				query.append("  left outer join org_post_mst orgpostmst10_ on hrpaypaybi0_.post_id = orgpostmst10_.post_id  ");
				query.append("  left outer join org_post_details_rlt orgpostdet8_ on orgpostmst10_.post_id = orgpostdet8_.post_id and orgpostdet8_.lang_id= "+langId+" ");
				query.append("  left outer join org_designation_mst     orgdesigna9_ on orgpostdet8_.dsgn_id = orgdesigna9_.dsgn_id and orgdesigna9_.lang_id= "+langId+ " ");
				
				
				query.append("  left outer join org_designation_mst dsgn on hreisgdmpg3_.sgd_desig_id = dsgn.dsgn_id and dsgn.lang_id = 1 "); 
				
				//query.append("  join paybill_head_mpg        bhm on hrpaypaybi0_.paybill_grp_id =bhm.paybill_id  and  bhm.bill_category = orgempmst11_.grade_id  ");
				
				//and  and( bhm.bill_category = orgempmst11_.grade_id or hrpaypaybi0_.emp_id is null) added for vacant post
				//rahul
				//query.append("  join paybill_head_mpg        bhm on hrpaypaybi0_.paybill_grp_id =bhm.paybill_id  and( bhm.bill_category = orgempmst11_.grade_id or hrpaypaybi0_.emp_id is null)  ");
				
				
				//query.append("  join hr_pay_bill_subhead_mpg hpbsm on bhm.bill_no =hpbsm.BILL_SUBHEAD_ID and  bhm.subhead_id=hrpayorder6_.subhead_id  ");
				//Midified by Mrugesh for financial year issue
				//query.append("  join hr_pay_bill_subhead_mpg hpbsm on bhm.bill_no =hpbsm.BILL_SUBHEAD_ID join Hr_Pay_Order_Subhead_Mpg sn on bhm.subhead_id = sn.budsubhd_id  and sn.element_code = hrpayorder6_.subhead_id  ");
				//rahul
				//query.append("  join hr_pay_bill_subhead_mpg hpbsm on bhm.bill_no =hpbsm.BILL_SUBHEAD_ID   ");

				//Ended by Mrugesh for financial year issue
				if(trnbillNo!=0)
				{
					query.append( " ,paybill_billreg_mpg pbm,trn_bill_register trb");
				}
				query.append("  where  ");
				if(isBillDefined)
				{
				/*query.append("  hrpaypaybi0_.post_id in ");
				query.append("  (select p.post_id ");
				query.append("  from hr_pay_post_psr_mpg p ");
				query.append("  where p.bill_no = bhm.bill_no) and ");*/
				}
				//rahul
				//query.append("  bhm.approve_flag in (0, 1) and ");
				query.append("  orgpostdet8_.loc_id = "+ locationId +" and orgpostdet8_.loc_id=hrpaypaybi0_.loc_id " );
				//rahul
				//query.append("  bhm.bill_Type_Id = "+Long.parseLong(billType)+"  ");
				if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{	
					query.append("  and (hrpaypaybi0_.NET_TOTAL<>0 or hrpaypaybi0_.gross_amt<>0)");
					String arrearType="";
					arrearType=CheckIfNull(report.getParameterValue( "Arrear List" ));
					if(!arrearType.equals("")&&!arrearType.equals("-1"))
					{
						query.append("  and hrpaypaybi0_.SAL_REV_ID="+arrearType+" ");
					}
				}	

				if(trnbillNo!=0)
				{
					if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
					{     
						logger.info("getting value from arrear paybill table");
						query.append(" and pbm.trn_bill_id=trb.bill_no and pbm.paybill_id=hrpaypaybi0_.paybill_grp_id and pbm.bill_type_id="+arrearbillTypeId );
						query.append("  and trb.bill_no="+trnbillNo  );
					}
					else
					{    
						logger.info("getting value from paybill paybill table");
						query.append(" and pbm.trn_bill_id=trb.bill_no and pbm.paybill_id=hrpaypaybi0_.paybill_grp_id and pbm.bill_type_id="+Long.parseLong(billType) );//changed by ravysh for supplimentary bill
						query.append("  and trb.bill_no="+trnbillNo  );
					}



				}
				//rahul
				/*if(isBillDefined && !BillNo.equals(""))
				{
					query.append(" and hpbsm.BILL_NO =  "+BillNo );
				}
				if(!month.equals("")&&!month.equals("-1"))            	
					query.append("  and  bhm.paybill_month='"+month+"'");

				if(!year.equals("")&&!year.equals("-1"))            	
					query.append("   and bhm.paybill_year= '"+year+"' ");*/
				
				//Code block regarding grade and bill no change of employee
				
				query.append("   and hrpaypaybi0_.paybill_grp_id in\n" );
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
				query.append("      ("+strPayBillGrpIdLst+") ");
				//
				
				if(!isBillDefined)
				{	
					if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
					{
						query.append("  and  hrpayorder6_.SUBHEAD_ID ="+subheadCode+" ");
					}
					if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
					{
						query.append("  and orgempmst11_.grade_id  in ("+classIds+")");
					}
					if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
					{
						query.append("  and orgpostdet8_.dsgn_id in (" +desgnIds+ ") ");
					}
					if(portType != null && !portType.equals("") && !portType.equals("-1"))
					{
						query.append("  and orgpostmst10_.post_type_lookup_id in (" +portType+ ") ");
					}
				}

				
				/*                 //query.append(" orguserpos5_.activate_flag = 1  ");
                 query.append(" (orguserpos5_.end_date is null or ");
                 query.append(" (orguserpos5_.end_date>='"+startMonthDate+"' and orguserpos5_.start_date<='"+endDate+"'  ))");*/

				if(!deptNo.equals("")&&!deptNo.equals("-1")) 
				{
					locationId=Long.parseLong(deptNo);	
					query.append(" and orgpostdet8_.loc_id = "+locationId+" ");	            	
				}

				if(!empid.equals("")&&!empid.equals("-1"))            	
					query.append("  and hreisother1_.EMP_ID = '"+empid+"'");

				//Added by Akshay 
				if(!lname.equals("") && !lname.equals(" "))
				{
					query.append("  and lower(orgempmst11_.EMP_LNAME) Like '"+lname.toLowerCase()+"%'");
				}

				if(!fname.equals("") && !fname.equals(" "))
				{
					query.append("  and lower(orgempmst11_.EMP_FNAME) Like '"+fname.toLowerCase()+"%'");
				}
				//Ended by Akshay  
				if(category.equals("AIS"))
					Grade=AISGradeCode+"";
				else if(category.equals("Gadgeted"))
					Grade=GradeCode1+"','"+GradeCode2;
				else if(category.equals("Non-Gadgeted"))
					Grade=GradeCode3+"','"+GradeCode4;

				if(!Grade.equals("")&&!Grade.equals("-1")&&!category.equals("Custom"))            	
					query.append("  and orgempmst11_.grade_id  in ('"+Grade+"')");

				if(!Scale.equals("")&&!Scale.equals("-1"))            	
					query.append("  and hreisscale4_.scale_id = '"+Scale+"'");

				if(bFlag)
				{

					//query+=" and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId in ( ";
					query.append(" and orgdesigna9_.dsgn_Id in ( ");
					int j =0;
					for(j=0;j<lstrDesgnIdlst.length;j++)
					{
						if(!lstrDesgnIdlst[j].equalsIgnoreCase("-1"))
						{
							if(j==lstrDesgnIdlst.length-1){
								query.append(" '"+((String)lstrDesgnIdlst[j]).trim()+"' ");
								dsgnIds += ((String)lstrDesgnIdlst[j]).trim();
							}
							else{
								query.append(" '"+((String)lstrDesgnIdlst[j]).trim()+"', ");
								dsgnIds += ((String)lstrDesgnIdlst[j]).trim() + ",";
							}

						}
					}
					//query+="'"+((String)lstrDesgnIdlst[j]).trim()+"' ";
					query.append(" )  ");
				}		            

				if(subHeadId != null && !subHeadId.equals(""))
				{
					query.append("  and  sn.budsubhd_id ="+subHeadId+" ");
				}
				//Ended By Paurav

				//if bill No is selected directly from outer paybil report
				/*   if(BillNoinner != null && !BillNoinner.equals("") && !BillNoinner.equals("-1"))
		            {
		            	query.append("  and  hrpayorder6_.SUBHEAD_ID ="+BillNoinner+" ");
			        }*/
				query.append("  group by hreisother1_.EMP_ID, ");
				query.append("  hreisempms10_.EMP_TYPE, ");
				query.append("  orgempmst11_.grade_id, ");
				query.append("  orgdesigna9_.dsgn_shrt_name, ");
				query.append("  orggradems14_.Grade_Name, ");
				query.append("  orgempmst11_.emp_fname,  ");
				query.append("  orgempmst11_.emp_mname,  ");
				query.append("  orgempmst11_.emp_lname,  ");
				query.append("  hreisscale4_.scale_start_amt,  ");
				query.append("  hreisscale4_.scale_higher_incr_amt,  ");
				query.append("  hreisscale4_.scale_higher_end_amt,  ");
				query.append("  hreisscale4_.scale_incr_amt,  ");
				query.append("  hreisscale4_.scale_end_amt,  ");
				query.append("  hreisother1_.PHY_CHALLENGED,  ");
				query.append("  hreisother1_.OTHER_CURRENT_BASIC,  ");
				query.append("  orgempmst11_.emp_prefix,  ");
				query.append("  orgempmst11_.user_id,  ");
				query.append("  orgdesigna9_.dsgn_id,  ");
				query.append("  orgpostdet8_.post_id,  ");
				query.append("  orgempmst11_.user_id,  ");
				query.append("  orgpostmst10_.post_type_lookup_id,  ");
				query.append("  hrpayorder6_.ORDER_ID,  ");
				query.append("  orgpostmst10_.end_date, ");	        
				query.append("  scale_grade_pay,hrpaypaybi0_.post_id ");	
				//added by ravysh for multiple month bill
				if(Long.parseLong(billType)==supplBill_Multiple_Month)
					query.append(",hrpaypaybi0_.paybill_month,hrpaypaybi0_.paybill_year,hrpaypaybi0_.emp_id, hrpaypaybi0_.paybill_grp_id ");	
				
				// Union query removed for optimization after this part found redundant due to addition of table paybill subhead mapping and hrpaypostpsrMPg
				// AddUnionQuery must be set to to true to add this union query which was used for vacant post issue
				//boolean AddUnionQuery=false;
				boolean AddUnionQuery=false;
				if ((sbConf.isMySQL()))
			    {
				 AddUnionQuery=true;//for integration with hdiits
			    }
				if(AddUnionQuery && !billType.equals("") && billType.equals(String.valueOf(paybillTypeId))) 
				{
					query.append("  Union ");	   


					query.append("  select hreisother1_.EMP_ID,  ");
					query.append("  orgdesigna9_.dsgn_shrt_name,  ");
					query.append("  orgempmst11_.grade_id,  ");
					query.append("  orggradems14_.Grade_Name,  ");
					query.append("  concat(orgempmst11_.emp_prefix, concat(' ', orgempmst11_.emp_fname)),  ");
					query.append("  hreisother1_.OTHER_CURRENT_BASIC,  ");
					query.append("  hreisscale4_.scale_start_amt,  ");
					query.append("  sum(hrpaypaybi0_.SPL_PAY),  ");
					query.append("  sum(hrpaypaybi0_.PER_PAY),  ");
					query.append("  sum(hrpaypaybi0_.PO) as col_9_0_,  ");
					query.append("  sum(hrpaypaybi0_.PE) as col_10_0_,  ");
					query.append("  sum(hrpaypaybi0_.LS) as col_11_0_,  ");
					query.append("  sum(hrpaypaybi0_.LE) as col_12_0_,  ");
					query.append("  sum(hrpaypaybi0_.D_PAY) as col_13_0_,  ");
					query.append("  sum(hrpaypaybi0_.DA) as col_14_0_,  ");
					query.append("  sum(hrpaypaybi0_.HRA) as col_15_0_,  ");
					query.append("  sum(hrpaypaybi0_.LTC) as col_16_0_,  ");
					query.append("  sum(hrpaypaybi0_.CLA) as col_17_0_,  ");
					query.append("  sum(hrpaypaybi0_.OTHER_ALLOW) as col_18_0_,  ");
					query.append("  sum(hrpaypaybi0_.MA) as col_19_0_,  ");
					query.append("  sum(hrpaypaybi0_.BONUS) as col_20_0_,  ");
					query.append("  sum(hrpaypaybi0_.WA) as col_21_0_,  ");
					query.append("  sum(hrpaypaybi0_.OTHER_CHRGS) as col_22_0_,  ");
					query.append("  sum(hrpaypaybi0_.TRANS_ALL) as col_23_0_,  ");
					query.append("  sum(hrpaypaybi0_.FES_ADV) as col_24_0_,  ");
					query.append("  sum(hrpaypaybi0_.FOOD_ADV) as col_25_0_,  ");
					query.append("  sum(hrpaypaybi0_.PAY_RECOVER) as col_26_0_,  ");
					query.append("  sum(hrpaypaybi0_.GROSS_AMT) as col_27_0_,  ");
					query.append("  sum(hrpaypaybi0_.SLO) as col_28_0_,  ");
					query.append("  sum(hrpaypaybi0_.IT) as col_29_0_,  ");
					query.append("  sum(hrpaypaybi0_.SURCHARGE) as col_30_0_,  ");
					query.append("  sum(hrpaypaybi0_.HRR) as col_31_0_,  ");
					query.append("  sum(hrpaypaybi0_.RENT_B) as col_32_0_,  ");
					query.append("  sum(hrpaypaybi0_.PLI) as col_33_0_,  ");
					query.append("  sum(hrpaypaybi0_.BLI) as col_34_0_,  ");
					query.append("  sum(hrpaypaybi0_.PT) as col_35_0_,  ");
					query.append("  sum(hrpaypaybi0_.SIS_GIS_1979) as col_36_0_,  ");
					query.append("  sum(hrpaypaybi0_.SIS_IF_1981) as col_37_0_,  ");
					query.append("  sum(hrpaypaybi0_.SIS_SF_1981) as col_38_0_,  ");
					query.append("  sum(hrpaypaybi0_.AIS_IF_1980) as col_39_0_,  ");
					query.append("  sum(hrpaypaybi0_.AIS_SF_1980) as col_40_0_,  ");
					query.append("  sum(hrpaypaybi0_.GPF_C) as col_41_0_,  ");
					query.append("  sum(hrpaypaybi0_.GPF_ADV) as col_42_0_,  ");
					query.append("  sum(hrpaypaybi0_.CAR_SCT_MOPED_ADV) as col_43_0_,  ");
					query.append("  sum(hrpaypaybi0_.OCA_CYCLE_ADV) as col_44_0_,  ");
					query.append("  sum(hrpaypaybi0_.HBA) as col_45_0_,  ");
					query.append("  sum(hrpaypaybi0_.FAN_ADV) as col_46_0_,  ");
					query.append("  sum(hrpaypaybi0_.JEEP_R) as col_47_0_,  ");
					query.append("  sum(hrpaypaybi0_.MISC_RECOV) as col_48_0_,  ");
					query.append("  sum(hrpaypaybi0_.GPF_IV) as col_49_0_,  ");
					query.append("  sum(hrpaypaybi0_.TOTAL_DED) as col_50_0_,  ");
					query.append("  sum(hrpaypaybi0_.NET_TOTAL) as col_51_0_,  ");
					query.append("  sum(hrpaypaybi0_.DP_GAZZETED) as col_52_0_,  ");
					query.append("  hreisother1_.PHY_CHALLENGED as col_53_0_,  ");
					query.append("  sum(hrpaypaybi0_.CAR_SCT_MOPED_INT) as col_54_0_,  ");
					query.append("  sum(hrpaypaybi0_.OCA_CYCLE_INT) as col_55_0_,  ");
					query.append("  sum(hrpaypaybi0_.HBA_INT) as col_56_0_,  ");
					query.append("  sum(hrpaypaybi0_.FAN_INT) as col_57_0_,  ");
					query.append("  max(orgempmst11_.emp_gpf_num) as col_58_0_,  ");
					query.append("  sum(hrpaypaybi0_.GPF_IV_ADV) as col_59_0_,  ");
					query.append("  (select hrpayorder24_.ORDER_NAME  ");
					query.append("  from HR_PAY_ORDER_MST hrpayorder24_  ");
					query.append("  where hrpayorder24_.ORDER_ID = hrpayorder6_.ORDER_ID) as col_60_0_,  ");
					query.append("  hrpayorder6_.ORDER_ID as orderId,  ");
					query.append("  hreisempms10_.EMP_TYPE as emptype,  ");
					query.append("  (select hrpayorder24_.order_date ");
					query.append("  from HR_PAY_ORDER_MST hrpayorder24_");
					query.append("  where hrpayorder24_.ORDER_ID = hrpayorder6_.ORDER_ID) as orderdate,  ");
					query.append("  orgdesigna9_.dsgn_id dsgnId,  ");
					query.append("  sum(hrpaypaybi0_.CPF) as CPF,  ");
					query.append("  sum(hrpaypaybi0_.AIS_PF) as AISPF,  ");
					query.append("  orgempmst11_.emp_mname,  ");
					query.append("  orgempmst11_.emp_lname, ");
					query.append("  hreisscale4_.scale_end_amt,  ");
					query.append("  hreisscale4_.scale_higher_end_amt,  ");
					if(isBillDefined)
					{
						query.append(" max(hrpaypaybi0_.psr_no) psr,");
					/*query.append("  (select max(psr.psr_no) ");
					query.append("  from hr_pay_post_psr_mpg psr ");
					query.append("  where psr.post_id = orgpostdet8_.post_id) psr,  ");*/
					}
					else
					{
					query.append(" orgpostdet8_.post_id, ");
					}
					query.append("  (select payfix.rev_pay - payfix.prev_pay ");
					query.append("  from hr_payfix_mst payfix ");
					query.append("  where payfix.user_id = orgempmst11_.user_id and payfix.lang_id = "+langId+" and ");
					query.append("  payfix.pay_fix_date between '"+startMonthDate+"' and '"+endDate+"' and ");
					query.append("  payfix.active_flag = 'Y' and payfix.is_defined = 'N') payIncr,  ");
					query.append("  (select lm.lookup_id ");
					query.append("  from cmn_lookup_mst lm ");
					query.append("  where lm.lookup_id = orgpostmst10_.post_type_lookup_id) posttype,  ");

					query.append("  orgpostmst10_.end_date,count(hreisother1_.EMP_ID),");

					if(billType.equals(String.valueOf(arrearbillTypeId)) )
						query.append(" sum(hrpaypaybi0_.gpay) scale_grade_pay,");// added by rahul
					else
						query.append(" hreisscale4_.scale_grade_pay scale_grade_pay,");
					query.append(" sum(hrpaypaybi0_.DA_GPF),sum(hrpaypaybi0_.DA_GPFIV) "); 
					
					
					query.append("  ,(select concat(office.office_no, concat('.',office.office_name)) from hr_pay_office_mst office,hr_pay_officepost_mpg ompg where ompg.post_id = hrpaypaybi0_.post_id and ompg.office_id = office.office_id " +
							" and (ompg.end_date is null or (ompg.start_date <='"+endDate+"' and ompg.end_date  >='"+startMonthDate+"')) ) ");
					query.append("  ,(select ompg.remarks from hr_pay_office_mst office,hr_pay_officepost_mpg ompg where ompg.post_id = hrpaypaybi0_.post_id and ompg.office_id = office.office_id " +
							" and (ompg.end_date is null or (ompg.start_date <='"+endDate+"' and ompg.end_date  >='"+startMonthDate+"')) ) ");
					
					
					/*	





					 */                 if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
					 {     
						 logger.info("getting value from arrear paybill table");
						 query.append(" from Hr_Pay_Arrear_Paybill             hrpaypaybi0_ ");
					 }
					 else
					 {    
						 logger.info("getting value from paybill paybill table");
						 query.append(" from HR_PAY_PAYBILL             hrpaypaybi0_ "); 
					 }


					 query.append("  left outer join hr_eis_emp_mst hreisempms10_ on hrpaypaybi0_.EMP_ID = hreisempms10_.EMP_ID  ");
					 query.append("  left outer join org_emp_mst orgempmst11_ on hrpaypaybi0_.EMP_ID = orgempmst11_.EMP_ID  and orgempmst11_.lang_id= "+langId+" ");
					 query.append("  left outer join org_grade_mst orggradems14_ on orgempmst11_.grade_id = orggradems14_.Grade_Id  and orggradems14_.lang_id= "+langId+" ");
					 query.append("  left outer join HR_EIS_OTHER_DTLS hreisother1_ on hrpaypaybi0_.EMP_ID = hreisother1_.EMP_ID  ");
					 query.append("  left outer join HR_EIS_SGD_MPG hreissgdmp2_ on hreisother1_.EMP_SGD_ID = hreissgdmp2_.SGD_MAP_ID  ");
					 query.append("  left outer join HR_EIS_GD_MPG hreisgdmpg3_ on hreissgdmp2_.SGD_GD_ID = hreisgdmpg3_.GD_MAP_ID  ");
					 query.append("  left outer join hr_eis_scale_mst hreisscale4_ on hreissgdmp2_.SGD_SCALE_ID = hreisscale4_.scale_id and hreisscale4_.lang_id= "+langId+ " ");
					 //rahul
					 //query.append("  left outer join org_userpost_rlt orguserpos5_ on hrpaypaybi0_.post_id = orguserpos5_.post_id  and orguserpos5_.activate_flag=1");
					
					 query.append("  left outer join HR_PAY_ORDER_HEAD_POST_MPG hrpayorder7_ on hrpaypaybi0_.post_id = hrpayorder7_.post_id  ");
					 query.append("  left outer join HR_PAY_ORDER_HEAD_MPG hrpayorder6_ on hrpayorder7_.ORDER_HEAD_ID = hrpayorder6_.ORDER_HEAD_ID  "); 
					 query.append("  left outer join org_post_mst orgpostmst10_ on hrpaypaybi0_.post_id = orgpostmst10_.post_id  ");
					 query.append("  left outer join org_post_details_rlt orgpostdet8_ on orgpostmst10_.post_id = orgpostdet8_.post_id and orgpostdet8_.lang_id= "+langId+" ");
					 query.append("  left outer join org_designation_mst     orgdesigna9_ on orgpostdet8_.dsgn_id = orgdesigna9_.dsgn_id and orgdesigna9_.lang_id= "+langId+ " ");
					//rahul
					// query.append("  join paybill_head_mpg        bhm on hrpaypaybi0_.paybill_grp_id =bhm.paybill_id   ");
					 //Midified by Mrugesh for financial year issue
					 //query.append("  join hr_pay_bill_subhead_mpg hpbsm on bhm.bill_no =hpbsm.BILL_SUBHEAD_ID and  bhm.subhead_id=hrpayorder6_.subhead_id  ");
					 //query.append("  join hr_pay_bill_subhead_mpg hpbsm on bhm.bill_no =hpbsm.BILL_SUBHEAD_ID join Hr_Pay_Order_Subhead_Mpg sn on bhm.subhead_id = sn.budsubhd_id  and sn.element_code = hrpayorder6_.subhead_id  ");
					 //Ended by Mrugesh for financial year issue
					 if(trnbillNo!=0)
						{
							query.append( " ,paybill_billreg_mpg pbm,trn_bill_register trb ");
						}
					 
					 query.append("  where  ");
					 if(isBillDefined)
						{
					 /*query.append("  hrpaypaybi0_.post_id in ");
					 query.append("  (select p.post_id ");
					 query.append("  from hr_pay_post_psr_mpg p ");
					 query.append("  where p.bill_no = bhm.bill_no) and ");*/
						}
					 query.append("  hrpaypaybi0_.EMP_ID is null and ");
					 //rahul
					 //query.append("  bhm.approve_flag in (0, 1) and ");
					 query.append("  orgpostdet8_.loc_id = "+ locationId +" and orgpostdet8_.loc_id=hrpaypaybi0_.loc_id " );
					 //rahul
					 //query.append("  bhm.bill_Type_Id = "+Long.parseLong(billType)+"  ");
					 
					 if(trnbillNo!=0)
						{
							if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
							{     
								logger.info("getting value from arrear paybill table");
								//rahul
								query.append(" and pbm.trn_bill_id=trb.bill_no and pbm.paybill_id=hrpaypaybi0_.paybill_grp_id and pbm.bill_type_id="+arrearbillTypeId );
								query.append("  and trb.bill_no="+trnbillNo  );
							}
							else
							{    
								logger.info("getting value from paybill paybill table");
								//rahul
								query.append(" and pbm.trn_bill_id=trb.bill_no and pbm.paybill_id=hrpaypaybi0_.paybill_grp_id and pbm.bill_type_id="+paybillTypeId );
								query.append("  and trb.bill_no="+trnbillNo  );
							}



						}
					 //rahul
					 /*if(isBillDefined && !BillNo.equals(""))
					 {
						 query.append(" and hpbsm.BILL_NO =  "+BillNo );
					 }
					 if(!month.equals("")&&!month.equals("-1"))            	
						 query.append("  and  bhm.paybill_month='"+month+"'");

					 if(!year.equals("")&&!year.equals("-1"))            	
						 query.append("   and bhm.paybill_year= '"+year+"' ");*/
					
						//Code block regarding grade and bill no change of employee
						
						query.append("   and hrpaypaybi0_.paybill_grp_id in\n" );
						pblGrpIdList = new ArrayList(); 
						pblGrpIdList =paybillDAO.getPayBillGrpIDFromBillNo(Long.parseLong(billType),month,year,BillNo);
						strBufPayBillGrpIds=new StringBuffer();
						strPayBillGrpIdLst="0";
						if(pblGrpIdList!=null && pblGrpIdList.size()>0)
						{
							for (int pblIdIncrVar=0;pblIdIncrVar<pblGrpIdList.size();pblIdIncrVar++)
							{
								strBufPayBillGrpIds.append(pblGrpIdList.get(pblIdIncrVar).toString()).append(",");
							}
							strPayBillGrpIdLst = strBufPayBillGrpIds.deleteCharAt(strBufPayBillGrpIds.lastIndexOf(",")).toString();
							strBufPayBillGrpIds=null;
						}
						query.append("      ("+strPayBillGrpIdLst+") ");
						//
						
						
					 
					 if(!isBillDefined)
					 {	
						 if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
						 {
							 query.append("  and  hrpayorder6_.SUBHEAD_ID ="+subheadCode+" ");
						 }
						 if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
						 {
							 query.append("  and orgempmst11_.grade_id  in ("+classIds+")");
						 }
						 if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
						 {
							 query.append("  and orgpostdet8_.dsgn_id in (" +desgnIds+ ") ");
						 }
						 if(portType != null && !portType.equals("") && !portType.equals("-1"))
						 {
							 query.append("  and orgpostmst10_.post_type_lookup_id in (" +portType+ ") ");
						 }
					 }

					
					 /*                 //query.append(" orguserpos5_.activate_flag = 1  ");
	                 query.append(" (orguserpos5_.end_date is null or ");
	                 query.append(" (orguserpos5_.end_date>='"+startMonthDate+"' and orguserpos5_.start_date<='"+endDate+"'  ))");*/

					 if(!deptNo.equals("")&&!deptNo.equals("-1")) 
					 {
						 locationId=Long.parseLong(deptNo);	
						 query.append(" and orgpostdet8_.loc_id = "+locationId+" ");	            	
					 }

					 if(!empid.equals("")&&!empid.equals("-1"))            	
						 query.append("  and hreisother1_.EMP_ID = '"+empid+"'");

					 //Added by Akshay 
					 if(!lname.equals("") && !lname.equals(" "))
					 {
						 query.append("  and lower(orgempmst11_.EMP_LNAME) Like '"+lname.toLowerCase()+"%'");
					 }

					 if(!fname.equals("") && !fname.equals(" "))
					 {
						 query.append("  and lower(orgempmst11_.EMP_FNAME) Like '"+fname.toLowerCase()+"%'");
					 }
					 //Ended by Akshay  
					 if(category.equals("AIS"))
						 Grade=AISGradeCode+"";
					 else if(category.equals("Gadgeted"))
						 Grade=GradeCode1+"','"+GradeCode2;
					 else if(category.equals("Non-Gadgeted"))
						 Grade=GradeCode3+"','"+GradeCode4;

					 if(!Grade.equals("")&&!Grade.equals("-1")&&!category.equals("Custom"))            	
						 query.append("  and orgempmst11_.grade_id  in ('"+Grade+"')");

					 if(!Scale.equals("")&&!Scale.equals("-1"))            	
						 query.append("  and hreisscale4_.scale_id = '"+Scale+"'");

					 if(bFlag)
					 {

						 //query+=" and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId in ( ";
						 query.append(" and orgdesigna9_.dsgn_Id in ( ");
						 int j =0;
						 for(j=0;j<lstrDesgnIdlst.length;j++)
						 {
							 if(!lstrDesgnIdlst[j].equalsIgnoreCase("-1"))
							 {
								 if(j==lstrDesgnIdlst.length-1){
									 query.append(" '"+((String)lstrDesgnIdlst[j]).trim()+"' ");
									 dsgnIds += ((String)lstrDesgnIdlst[j]).trim();
								 }
								 else{
									 query.append(" '"+((String)lstrDesgnIdlst[j]).trim()+"', ");
									 dsgnIds += ((String)lstrDesgnIdlst[j]).trim() + ",";
								 }

							 }
						 }
						 //query+="'"+((String)lstrDesgnIdlst[j]).trim()+"' ";
						 query.append(" )  ");
					 }		            

					 if(subHeadId != null && !subHeadId.equals(""))
					 {
						 query.append("  and  sn.budsubhd_id ="+subHeadId+" ");
					 }
					 //Ended By Paurav

					 //if bill No is selected directly from outer paybil report
					 /*   if(BillNoinner != null && !BillNoinner.equals("") && !BillNoinner.equals("-1"))
			            {
			            	query.append("  and  hrpayorder6_.SUBHEAD_ID ="+BillNoinner+" ");
				        }*/

					 query.append("  group by hreisother1_.EMP_ID, ");
					 query.append("  hreisempms10_.EMP_TYPE, ");
					 query.append("  orgempmst11_.grade_id, ");
					 query.append("  orgdesigna9_.dsgn_shrt_name, ");
					 query.append("  orggradems14_.Grade_Name, ");
					 query.append("  orgempmst11_.emp_fname,  ");
					 query.append("  orgempmst11_.emp_mname,  ");
					 query.append("  orgempmst11_.emp_lname,  ");
					 query.append("  hreisscale4_.scale_start_amt,  ");
					 query.append("  hreisscale4_.scale_higher_incr_amt,  ");
					 query.append("  hreisscale4_.scale_higher_end_amt,  ");
					 query.append("  hreisscale4_.scale_incr_amt,  ");
					 query.append("  hreisscale4_.scale_end_amt,  ");
					 query.append("  hreisother1_.PHY_CHALLENGED,  ");
					 query.append("  hreisother1_.OTHER_CURRENT_BASIC,  ");
					 query.append("  orgempmst11_.emp_prefix,  ");
					 query.append("  orgempmst11_.user_id,  ");
					 query.append("  orgdesigna9_.dsgn_id,  ");
					 query.append("  orgpostdet8_.post_id,  ");
					 query.append("  orgempmst11_.user_id,  ");
					 query.append("  orgpostmst10_.post_type_lookup_id,  ");
					 query.append("  hrpayorder6_.ORDER_ID,  ");
					 query.append("  orgpostmst10_.end_date, ");	
					 query.append("  scale_grade_pay,hrpaypaybi0_.post_id ");	
				}

				query.append("  order by ");
				
				logger.info("locationId:::"+locationId);
				String orderBy=null;
				String psr = "";
				String supplyBillRequiredString = "";
				
//				Added for supplimentry bill  to get paybill in accordence with month , and finential year
				if(!billType.equals("") && billType.equals(String.valueOf(supplBill_Multiple_Month)))
					supplyBillRequiredString = " hreisother1_.EMP_ID, hrpaypaybi0_.paybill_year, hrpaypaybi0_.paybill_month, hrpaypaybi0_.paybill_year || hrpaypaybi0_.paybill_month , "; 

				if(isBillDefined)
				 {	
					//added for multiple month bill
					psr = "psr, ";
					if(Long.parseLong(billType)==supplBill_Multiple_Month)
				orderBy = " posttype,orderId,  empType, grade_id,dsgnId, hrpaypaybi0_.paybill_month,hrpaypaybi0_.paybill_year ";//resourceBundle.getString("OrderBy"+locationId);
					else
						orderBy = "  posttype,orderId,  empType, grade_id,dsgnId ";	
				 }
				else
				{
				orderBy = "  posttype,orderId,  empType, grade_id,dsgnId ";
				}
				logger.info(" portion fetching from payroll properties::::::"+orderBy);
				query.append(" ").append(psr).append(supplyBillRequiredString).append(orderBy);
				/*if(resourceBundle.getString("GAD").equals(locationId+"")) 
                	query.append("   order by psr,posttype,orderId,  empType, grade_id,dsgnId ");	

                else
                	query.append("   order by posttype,orderId,  empType, grade_id,dsgnId ");*/


				//ended by rahul
				// sql query end
				logger.info("Now trying to generate paybill report Dynamically "+query.toString()); 

				Query sqlquery = hibSession.createSQLQuery(query.toString());



				List RowList=sqlquery.list();
				Iterator itr1 = RowList.iterator(); 
				ArrayList pageTotalList=new ArrayList();//List of total row in one page
				long GRAND_PER_PAY_TOTAL =0;
				long GRAND_SPL_PAY_TOTAL =0;
				long GRAND_PO_TOTAL =0;
				long GRAND_PE_TOTAL =0;

				long GRAND_GPGAZ_TOTAL=0;
				long GRAND_GPNONGAZ_TOTAL=0;

				long GRAND_LS_TOTAL =0;
				long GRAND_LE_TOTAL =0;
				long GRAND_DP_GAZZETED_TOTAL =0;
				long GRAND_D_PAY_TOTAL =0;
				long GRAND_DA_TOTAL =0;
				long GRAND_HRA_TOTAL =0;
				long GRAND_LTC_TOTAL =0;
				long GRAND_CLA_TOTAL =0;
				long GRAND_OTHER_ALLOW_TOTAL =0;
				long GRAND_MA_TOTAL =0;
				long GRAND_BONUS_TOTAL =0;
				long GRAND_WA_TOTAL =0;
				long GRAND_OTHER_CHRGS_TOTAL =0;
				long GRAND_TRANS_ALL_TOTAL =0;
				long GRAND_FES_ADV_TOTAL =0;
				long GRAND_FOOD_ADV_TOTAL =0;
				long GRAND_PAY_RECOVER_TOTAL =0;
				long GRAND_GROSS_AMT_TOTAL =0;
				long GRAND_SLO_TOTAL =0;
				long GRAND_IT_TOTAL =0;
				long GRAND_SURCHARGE_TOTAL =0;
				long GRAND_HRR_TOTAL =0;
				long GRAND_RENT_B_TOTAL =0;
				long GRAND_PLI_TOTAL =0;
				long GRAND_BLI_TOTAL =0;
				long GRAND_PT_TOTAL =0;
				long GRAND_SIS_GIS_1979_TOTAL =0;
				long GRAND_SIS_IF_1981_TOTAL =0;
				long GRAND_SIS_SF_1981_TOTAL =0;
				long GRAND_AIS_IF_1980_TOTAL =0;
				long GRAND_AIS_SF_1980_TOTAL =0;
				long GRAND_GPF_C_TOTAL =0;
				long GRAND_CPF_TOTAL =0;
				long GRAND_AIS_PF_TOTAL =0;
				long GRAND_GPF_ADV_TOTAL =0;
				long GRAND_CAR_SCT_MOPED_ADV_TOTAL =0;
				long GRAND_OCA_CYCLE_ADV_TOTAL =0;
				long GRAND_HBA_TOTAL =0;
				long GRAND_FAN_ADV_TOTAL =0;
				long GRAND_CAR_SCT_MOPED_INT_TOTAL =0;
				long GRAND_OCA_CYCLE_INT_TOTAL =0;
				long GRAND_HBA_INT_TOTAL =0;
				long GRAND_FAN_INT_TOTAL =0;
				long GRAND_JEEP_R_TOTAL =0;
				long GRAND_MISC_RECOV_TOTAL =0;
				long GRAND_GPF_IV_TOTAL =0;
				long GRAND_DA_GPF_TOTAL =0;
				long GRAND_DA_GPFIV_TOTAL =0;
				long GRAND_GPF_IV_ADV_TOTAL=0;
				long GRAND_TOTAL_DED_TOTAL =0;
				long GRAND_NET_TOTAL_TOTAL =0;



				long PER_PAY_TOTAL=0;
				long SPL_PAY_TOTAL=0;
				long PO_TOTAL=0;
				long PE_TOTAL=0;
				
				long GPGAZ_TOTAL=0;
				long GPNONGAZ_TOTAL=0;
				
				long LS_TOTAL=0;
				long LE_TOTAL=0;
				long D_PAY_TOTAL=0;
				long DP_GAZZETED_TOTAL=0;
				long DA_TOTAL=0;
				
				long HRA_TOTAL=0;
				long LTC_TOTAL=0;
				long CLA_TOTAL=0;
				long OTHER_ALLOW_TOTAL=0;
				long MA_TOTAL=0;
				long BONUS_TOTAL=0;
				long WA_TOTAL=0;
				long OTHER_CHRGS_TOTAL=0;
				long TRANS_ALL_TOTAL=0;
				long FES_ADV_TOTAL=0;
				long FOOD_ADV_TOTAL=0;
				long PAY_RECOVER_TOTAL=0;
				long GROSS_AMT_TOTAL=0;
				long SLO_TOTAL=0;
				long IT_TOTAL=0;
				long SURCHARGE_TOTAL=0;
				long HRR_TOTAL=0;
				long RENT_B_TOTAL=0;
				long PLI_TOTAL=0;
				long BLI_TOTAL=0;
				long PT_TOTAL=0;
				long SIS_GIS_1979_TOTAL=0;
				long SIS_IF_1981_TOTAL=0;
				long SIS_SF_1981_TOTAL=0;
				long AIS_IF_1980_TOTAL=0;
				long AIS_SF_1980_TOTAL=0;
				long GPF_C_TOTAL=0;
				long DA_GPF_TOTAL=0;
				long DA_GPFIV_TOTAL=0;
				long CPF_TOTAL=0;
				long AIS_PF_TOTAL=0;
				long GPF_ADV_TOTAL=0;
				long CAR_SCT_MOPED_ADV_TOTAL=0;
				long OCA_CYCLE_ADV_TOTAL=0;
				long HBA_TOTAL=0;
				long FAN_ADV_TOTAL=0;
				long CAR_SCT_MOPED_INT_TOTAL=0;
				long OCA_CYCLE_INT_TOTAL=0;
				long HBA_INT_TOTAL=0;
				long FAN_INT_TOTAL=0;
				long JEEP_R_TOTAL=0;
				long MISC_RECOV_TOTAL=0;
				long GPF_IV_TOTAL=0;
				long GPF_IV_ADV_TOTAL=0;
				long TOTAL_DED_TOTAL=0;
				long NET_TOTAL_TOTAL=0;
				long listSize =  RowList.size();
				String oldOrderNo="";
				String oldOrderDate="";
				String oldOrderDsgn="";
				String oldPosttype="";
				String oldOfficeName="";
				String oldOfficeRemarks="";
				String oldMonthYearInfo="";
				int sameorderdsgns=0;
				int oldorderindex=0;
				int dsgncnt=0;
				//added by ravysh for multiple month suppl bill
				int row_Count=0;
				int emp_Count=0;
				int curr_Emp_Count=0;
				int prev_Emp_Count=0;
				while (itr1.hasNext())
				{
					
					Object[] rowList1 = (Object[]) itr1.next();
					long EmpId = Long.parseLong((rowList1[0]!=null?rowList1[0].toString():"0").toString());
					
					String designation = (String)(rowList1[1]!=null?rowList1[1]:"");
					long gradeId = Long.parseLong((rowList1[2]!=null?rowList1[2].toString():"0").toString());
					String Class = (String)(rowList1[3]!=null?rowList1[3]:"");
					String Name = (String)(rowList1[4]!=null?rowList1[4]:"") +" "+  (String)(rowList1[67]!=null?rowList1[67]:"")+" "+ (String)(rowList1[68]!=null?rowList1[68]:"");
					long basic = Long.parseLong((rowList1[5]!=null?rowList1[5]:"0").toString());		            
					String  scale = "";
					
					if(rowList1[6]!=null&&!rowList1[6].toString().equals(""))
					{
						if(rowList1[70]!=null&&!rowList1[70].toString().equals("0"))
							scale="["+rowList1[6].toString()+"-"+rowList1[70].toString()+"("+rowList1[76].toString()+")"+"]";
						else
							scale="["+rowList1[6].toString()+"-"+rowList1[69].toString()+"("+rowList1[76].toString()+")"+"]";
					}
					else
					{
						scale = ((Long)basic).toString();
					}


					if(EmpId==0)// this is emp ID from paybill whic is 0 for vacant post
						scale="";	
					if(EmpId==0)// this is emp ID from paybill whic is 0 for vacant post
						Name="Vacant Post";	

					long PsrNo=Long.parseLong((rowList1[71]!=null?rowList1[71]:"0").toString());
					long IncrementAmt=Long.parseLong((rowList1[72]!=null?rowList1[72]:"0").toString());
					
					String officeName=(rowList1[79]!=null?rowList1[79]:"").toString();
					String officeRemarks=(rowList1[80]!=null?rowList1[80]:"").toString();

					
					//added by ravysh
					String bill_Month="";
					String bill_Year="";				
					String monthYearInfo="";
					//added by ravysh for supplementary bill
					if(Long.parseLong(billType)==supplBill_Multiple_Month)
					{
					bill_Month=(rowList1[81]!=null?rowList1[81]:"").toString();;
					bill_Year=(rowList1[82]!=null?rowList1[82]:"").toString();;
					
					monthYearInfo = "Pay-"+bill_Month+"/"+bill_Year;
					emp_Count=Integer.parseInt((rowList1[83]!=null?rowList1[83]:"0").toString());
					
					if((curr_Emp_Count!=emp_Count) || (row_Count==1))
						{
							prev_Emp_Count=curr_Emp_Count;
							curr_Emp_Count=emp_Count;
						}
					}
					
					
					
					String posttype=(rowList1[73]!=null?rowList1[73]:"").toString();
					
					if(!officeName.equals(""))
						posttype="";
					
					String ORDER_DATE=(rowList1[63]!=null?rowList1[63]:"").toString();

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					//SimpleDateFormat offence_sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					//SimpleDateFormat dbformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
					if(!ORDER_DATE.equals(""))
					ORDER_DATE=sdf.format(sdformat.parse(ORDER_DATE));

					String postDate=(rowList1[74]!=null?rowList1[74]:"").toString();

					if(!postDate.equals(""))
						postDate=sdf.format(sdformat.parse(postDate));

					if(!posttype.equals(resourceBundle.getString("PermanentPost")))
					{	
						ORDER_DATE+=" UPTO Date:"+postDate;
					}


					if(posttype.equals(resourceBundle.getString("PermanentPost")))
						posttype="PERMANENT POST";
					else if(posttype.equals(resourceBundle.getString("TemporaryPost"))) 
						posttype="TEMPORARY POST";
					else
						posttype="";

					//////// GPAY /////////////
					long GPay = Long.parseLong((rowList1[76]!=null?rowList1[76]:"0").toString());
					long gradePayDisplay = GPay;					
					
					long PER_PAY=Math.round(Math.round(Double.parseDouble((rowList1[8]!=null?rowList1[8]:"0").toString())));
					long SPL_PAY=Math.round(Double.parseDouble((rowList1[7]!=null?rowList1[7]:"0").toString()));
					long PO=Math.round(Double.parseDouble((rowList1[9]!=null?rowList1[9]:"0").toString()));
					long PE=Math.round(Double.parseDouble((rowList1[10]!=null?rowList1[10]:"0").toString()));
					long LS=Math.round(Double.parseDouble((rowList1[11]!=null?rowList1[11]:"0").toString()));
					
					//calculate grade pay on prorata basis
					
					
					if(basic>0 && (PO+PE+LS)>0)
						GPay = Math.round(GPay*(PO+PE+LS)/(basic*1.0));

					if(Integer.parseInt( year)< paybillyear ||(Integer.parseInt( year)== paybillyear  && Integer.parseInt( month)<paybillmonth))
					{
						GPay = 0;
					}
					
					if(billType.equals(String.valueOf(arrearbillTypeId)) || (PO+PE+LS) ==0 )    // added by rahul
						GPay = Long.parseLong((rowList1[76]!=null?rowList1[76]:"0").toString()); 

					
					if((PO-GPay)>=0 && !billType.equals(String.valueOf(arrearbillTypeId)))
						PO-=GPay;
					else if((PE-GPay)>=0 && !billType.equals(String.valueOf(arrearbillTypeId)))
						PE-=GPay;
					else if((LS-GPay)>=0 && !billType.equals(String.valueOf(arrearbillTypeId)))
						LS-=GPay;
					
					long LE=Math.round(Double.parseDouble((rowList1[12]!=null?rowList1[12]:"0").toString()));
					long D_PAY=Math.round(Double.parseDouble((rowList1[13]!=null?rowList1[13]:"0").toString()));
					long DA=Math.round(Double.parseDouble((rowList1[14]!=null?rowList1[14]:"0").toString()));
					long HRA=Math.round(Double.parseDouble((rowList1[15]!=null?rowList1[15]:"0").toString()));
					long LTC=Math.round(Double.parseDouble((rowList1[16]!=null?rowList1[16]:"0").toString()));
					long CLA=Math.round(Double.parseDouble((rowList1[17]!=null?rowList1[17]:"0").toString()));
					long OTHER_ALLOW=Math.round(Double.parseDouble((rowList1[18]!=null?rowList1[18]:"0").toString()));
					long MA=Math.round(Double.parseDouble((rowList1[19]!=null?rowList1[19]:"0").toString()));
					long BONUS=Math.round(Double.parseDouble((rowList1[20]!=null?rowList1[20]:"0").toString()));
					long WA=Math.round(Double.parseDouble((rowList1[21]!=null?rowList1[21]:"0").toString()));
					long OTHER_CHRGS=Math.round(Double.parseDouble((rowList1[22]!=null?rowList1[22]:"0").toString()));
					long TRANS_ALL=Math.round(Double.parseDouble((rowList1[23]!=null?rowList1[23]:"0").toString()));
					long FES_ADV=Math.round(Double.parseDouble((rowList1[24]!=null?rowList1[24]:"0").toString()));
					long FOOD_ADV=Math.round(Double.parseDouble((rowList1[25]!=null?rowList1[25]:"0").toString()));
					long PAY_RECOVER=Math.round(Double.parseDouble((rowList1[26]!=null?rowList1[26]:"0").toString()));
					long GROSS_AMT=Math.round(Double.parseDouble((rowList1[27]!=null?rowList1[27]:"0").toString()));
					long SLO=Math.round(Double.parseDouble((rowList1[28]!=null?rowList1[28]:"0").toString()));
					long IT=Math.round(Double.parseDouble((rowList1[29]!=null?rowList1[29]:"0").toString()));
					long SURCHARGE=Math.round(Double.parseDouble((rowList1[30]!=null?rowList1[30]:"0").toString()));
					long HRR=Math.round(Double.parseDouble((rowList1[31]!=null?rowList1[31]:"0").toString()));
					long RENT_B=Math.round(Double.parseDouble((rowList1[32]!=null?rowList1[32]:"0").toString()));
					long PLI=Math.round(Double.parseDouble((rowList1[33]!=null?rowList1[33]:"0").toString()));
					long BLI=Math.round(Double.parseDouble((rowList1[34]!=null?rowList1[34]:"0").toString()));
					long PT=Math.round(Double.parseDouble((rowList1[35]!=null?rowList1[35]:"0").toString()));
					long SIS_GIS_1979=Math.round(Double.parseDouble((rowList1[36]!=null?rowList1[36]:"0").toString()));
					long SIS_IF_1981=Math.round(Double.parseDouble((rowList1[37]!=null?rowList1[37]:"0").toString()));
					long SIS_SF_1981=Math.round(Double.parseDouble((rowList1[38]!=null?rowList1[38]:"0").toString()));
					long AIS_IF_1980=Math.round(Double.parseDouble((rowList1[39]!=null?rowList1[39]:"0").toString()));
					long AIS_SF_1980=Math.round(Double.parseDouble((rowList1[40]!=null?rowList1[40]:"0").toString()));
					long GPF_C=Math.round(Double.parseDouble((rowList1[41]!=null?rowList1[41]:"0").toString()));
					long GPF_ADV=Math.round(Double.parseDouble((rowList1[42]!=null?rowList1[42]:"0").toString()));
					long CAR_SCT_MOPED_ADV=Math.round(Double.parseDouble((rowList1[43]!=null?rowList1[43]:"0").toString()));
					long CAR_SCT_MOPED_INT=Math.round(Double.parseDouble((rowList1[54]!=null?rowList1[54]:"0").toString()));
					long OCA_CYCLE_ADV=Math.round(Double.parseDouble((rowList1[44]!=null?rowList1[44]:"0").toString()));
					long OCA_CYCLE_INT=Math.round(Double.parseDouble((rowList1[55]!=null?rowList1[55]:"0").toString()));
					long HBA=Math.round(Double.parseDouble((rowList1[45]!=null?rowList1[45]:"0").toString()));
					long HBA_INT=Math.round(Double.parseDouble((rowList1[56]!=null?rowList1[56]:"0").toString()));
					long FAN_ADV=Math.round(Double.parseDouble((rowList1[46]!=null?rowList1[46]:"0").toString()));
					long FAN_INT=Math.round(Double.parseDouble((rowList1[57]!=null?rowList1[57]:"0").toString()));
					long JEEP_R=Math.round(Double.parseDouble((rowList1[47]!=null?rowList1[47]:"0").toString()));
					long MISC_RECOV=Math.round(Double.parseDouble((rowList1[48]!=null?rowList1[48]:"0").toString()));
					long GPF_IV=Math.round(Double.parseDouble((rowList1[49]!=null?rowList1[49]:"0").toString()));
					long TOTAL_DED=Math.round(Double.parseDouble((rowList1[50]!=null?rowList1[50]:"0").toString()));
					long NET_TOTAL=Math.round(Double.parseDouble((rowList1[51]!=null?rowList1[51]:"0").toString()));
					int countEmployeesRec=Integer.parseInt((rowList1[75]!=null?rowList1[75]:"0").toString());
					long DP_GAZZETED=Math.round(Double.parseDouble((rowList1[52]!=null?rowList1[52]:"0").toString()));
					String PHY_CHALLENGED=(rowList1[53]!=null?rowList1[53]:"").toString();
					String gpfAccNo=(rowList1[58]!=null?rowList1[58]:"").toString();
					long daGpf =Math.round(Double.parseDouble((rowList1[77]!=null?rowList1[77]:"").toString()));
					long daGpfIv =Math.round(Double.parseDouble((rowList1[78]!=null?rowList1[78]:"").toString()));
					if(EmpId==0)
						gpfAccNo=" ";	
					//added by Samir Joshi For Highlit Negative Values
					logger.info("NET_TOTAL"+NET_TOTAL);
					StyleVO[] colorStyleVO1 = new StyleVO[2];
					if(NET_TOTAL<0)
					{
						colorStyleVO1[0] = new StyleVO();
						colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
						colorStyleVO1[0].setStyleValue("Red");
						colorStyleVO1[1] = new StyleVO();
						colorStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
						colorStyleVO1[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
					}
					else if(countEmployeesRec>1 && !billType.equals(String.valueOf(arrearbillTypeId)))
					{
						colorStyleVO1[0] = new StyleVO();
						colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
						colorStyleVO1[0].setStyleValue("Blue");
						colorStyleVO1[1] = new StyleVO();
						colorStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
						colorStyleVO1[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 

					}
					else
					{
						colorStyleVO1[0] = new StyleVO();
						colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
						colorStyleVO1[0].setStyleValue("Black");
						colorStyleVO1[1] = new StyleVO();
						colorStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
						colorStyleVO1[1].setStyleValue(IReportConstants.VALUE_FONT_SIZE_SMALL); 

					}
					//ended By Samir Joshi

					ArrayList rowList11 =  new ArrayList();
					long GPF_IV_ADV=0;
					GPF_IV_ADV=Math.round(Double.parseDouble((rowList1[59]!=null?rowList1[59]:"0").toString()));
					String ORDER_NO=(rowList1[60]!=null?rowList1[60]:"").toString();

					String dsgnId=(rowList1[64]!=null?rowList1[64]:"").toString();

					long CPF=Math.round(Double.parseDouble((rowList1[65]!=null?rowList1[65]:"0").toString()));
					long AISPF=Math.round(Double.parseDouble((rowList1[66]!=null?rowList1[66]:"0").toString()));

					HashMap ht = new HashMap();

					ht.put("CPF",	(CPF)	);
					ht.put("AIS_PF",	(AISPF)	);
					ht.put("DA_GPF",	(daGpf)	);
					ht.put("DA_GPFIV",	(daGpfIv)	);
					ht.put("PER_PAY",	(PER_PAY)	);
					ht.put("SPL_PAY",	(SPL_PAY)	);
					ht.put("PO",	(PO)	);
					ht.put("PE",	(PE)	);
					

					ht.put("Grade Pay Gazzeted",	((gradeId!=GradeCode3&&gradeId!=GradeCode4)?GPay:0));
					ht.put("Grade Pay Non Gazzeted",((gradeId==GradeCode3||gradeId==GradeCode4)?GPay:0));
					
					
					ht.put("LS",	(LS)	);
					ht.put("LE",	(LE)	);


					ht.put("DP_GAZZETED",(DP_GAZZETED));
					ht.put("D_PAY",	(D_PAY));

					ht.put("DA",	(DA)	);
					ht.put("HRA",	(HRA)	);
					ht.put("LTC",	(LTC)	);
					ht.put("CLA",	(CLA)	);
					ht.put("OTHER_ALLOW",	(OTHER_ALLOW)	);
					ht.put("MA",	(MA	));
					ht.put("BONUS",	(BONUS	));
					ht.put("WA",	(WA	));
					ht.put("OTHER_CHRGS",	(OTHER_CHRGS	));
					String blankspace="                    ";
					String blankspace1="                                            ";
					ht.put("TRANS_ALL",(TRANS_ALL));
					ht.put("FES_ADV",	(FES_ADV	));
					ht.put("FOOD_ADV",	(FOOD_ADV	));
					ht.put("PAY_RECOVER",	(PAY_RECOVER	));
					// In order to show gross total without Fest Adv and food adv and PAY_RECOVER
					long dedtotal=(FES_ADV	)+(FOOD_ADV	)+(PAY_RECOVER	);
					if(dedtotal>0)
						ht.put("GROSS_AMT","("+(dedtotal+(GROSS_AMT	))+")"+System.getProperty("line.separator")+(GROSS_AMT	));
					else
						ht.put("GROSS_AMT",(GROSS_AMT	));



					ht.put("SLO",	blankspace.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+blankspace.replaceAll(" ",constantsBundle.getString("blankSpace")));
					ht.put("IT",	(IT	));
					ht.put("SURCHARGE",	(SURCHARGE	));
					ht.put("HRR",	(HRR	));
					ht.put("RENT_B",	(RENT_B	));
					ht.put("PLI",	(PLI	));
					ht.put("BLI",	(BLI	));


					if(PHY_CHALLENGED.equals("TRUE")) 
					{	
						ht.put("PT","P.H.");
					}
					else
					{
						ht.put("PT",	(PT	));			            	
					}

					ht.put("SIS_GIS_1979",	(SIS_GIS_1979	));
					ht.put("SIS_IF_1981",	(SIS_IF_1981	));
					ht.put("SIS_SF_1981",	(SIS_SF_1981	));
					ht.put("AIS_IF_1980",	(AIS_IF_1980	));
					ht.put("AIS_SF_1980",	(AIS_SF_1980	));
					ht.put("GPF_C",	(GPF_C));
					ht.put("GPF_ADV",	(GPF_ADV));
					ht.put("CAR_SCT_MOPED_ADV",(CAR_SCT_MOPED_ADV)	);
					ht.put("OCA_CYCLE_ADV",	(OCA_CYCLE_ADV	));
					ht.put("HBA",	(HBA	));
					ht.put("FAN_ADV",	(FAN_ADV	));
					ht.put("CAR_SCT_MOPED_INT",(CAR_SCT_MOPED_INT)	);
					ht.put("OCA_CYCLE_INT",	(OCA_CYCLE_INT	));
					ht.put("HBA_INT",	(HBA_INT	));
					ht.put("FAN_INT",	(FAN_INT	));
					ht.put("JEEP_R",	(JEEP_R	));
					ht.put("MISC_RECOV",	(MISC_RECOV	));
					ht.put("GPF_IV",	(GPF_IV)	);
					ht.put("GPF_IV_ADV",	(GPF_IV_ADV)	);
					if(dedtotal>0)
						ht.put("TOTAL_DED","("+((TOTAL_DED	)+dedtotal)+")"+System.getProperty("line.separator")+((TOTAL_DED	)));
					else
						ht.put("TOTAL_DED",(TOTAL_DED	));
					ht.put("NET_TOTAL",	(NET_TOTAL)	);

					
					String oldOrdercombination="";
					String newOrdercombination="";
                    if(oldOfficeName.equals(""))//// code for normal order  display functionality
                    {	
					 oldOrdercombination=oldOrderNo+oldOrderDate+oldOrderDsgn+oldPosttype+oldMonthYearInfo;
					 newOrdercombination=ORDER_NO+ORDER_DATE+designation+posttype+monthYearInfo;
					 
                    }
                    else//// code for office display functionality
                    {	
    				 oldOrdercombination=oldOrderDsgn+oldPosttype+oldOfficeName+oldOfficeRemarks+oldMonthYearInfo;
    				 newOrdercombination=designation+posttype+officeName+officeRemarks+monthYearInfo;
    				 
                    }

                    long breakPoint=0;
					if(listSize<(pagesize-1)&&RowList.size()==displayCnt-1)//counter has been already incremented so -1
					{ 	  
						breakPoint=1;
						
					}	  
					else
						breakPoint=(pagesize-1);
					if(!oldOrdercombination.equals(newOrdercombination))
					{
						
						logger.info(pagecnt+"******"+oldOrdercombination+"******"+newOrdercombination+"***********"+cnt);  
						if((cnt+1)%breakPoint==0)
						{
							cnt++;	
							// Insteda of showing new order show blank data
							// Pagecnt can be known at this stage only so last column of blandatalist added here
							ArrayList BlankDataList = new ArrayList();
							for(int rowData=0;rowData<36;rowData++)
								BlankDataList.add("");	
							BlankDataList.add(pagecnt);
							DataList.add(BlankDataList);
							
							listSize-=(pagesize-1);
							rowList11=new ArrayList();	
							ArrayList rowTotal=new ArrayList();
							rowList11.add("");//1
							rowTotal.add(pagecnt);

							StyledData dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(before.replaceAll(" ",constantsBundle.getString("blankSpace")+
									System.getProperty("line.separator"))+
									before.replaceAll(" ",constantsBundle.getString("blankSpace")+
											System.getProperty("line.separator"))+
											"Total"+before.replaceAll(" ",constantsBundle.getString("blankSpace")+System.getProperty("line.separator"))+before.replaceAll(" ",constantsBundle.getString("blankSpace")+System.getProperty("line.separator")));                   
							rowList11.add(dataStyle1);//2
							rowTotal.add("");				                

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(before.replaceAll(" ",constantsBundle.getString("blankSpace"))+
									System.getProperty("line.separator")+
									before.replaceAll(" ",constantsBundle.getString("blankSpace")+	
									System.getProperty("line.separator")+
									(PER_PAY_TOTAL)+
									System.getProperty("line.separator")+(SPL_PAY_TOTAL)+
									System.getProperty("line.separator")+
							before.replaceAll(" ",constantsBundle.getString("blankSpace")))			
							); 
							rowList11.add(dataStyle1);//3
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((PO_TOTAL)+System.getProperty("line.separator")+(PE_TOTAL));                  
							rowList11.add(dataStyle1);//4
							rowTotal.add(dataStyle1);
							
							///////////// for GP /////////
							
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GPGAZ_TOTAL)+System.getProperty("line.separator")+(GPNONGAZ_TOTAL));                  
							rowList11.add(dataStyle1);//4
							rowTotal.add(dataStyle1);
							
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((LS_TOTAL)+System.getProperty("line.separator")+(LE_TOTAL));                  
							rowList11.add(dataStyle1);//5
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((DP_GAZZETED_TOTAL)+System.getProperty("line.separator")+(D_PAY_TOTAL ));                  
							rowList11.add(dataStyle1);//6
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((DA_TOTAL));                  
							rowList11.add(dataStyle1);//7
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((HRA_TOTAL)+System.getProperty("line.separator")+(LTC_TOTAL));                  
							rowList11.add(dataStyle1);//8
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((CLA_TOTAL)+System.getProperty("line.separator")+(OTHER_ALLOW_TOTAL));                  
							rowList11.add(dataStyle1);//9
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((MA_TOTAL)+System.getProperty("line.separator")+(BONUS_TOTAL));                  
							rowList11.add(dataStyle1);//10
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((WA_TOTAL)+System.getProperty("line.separator")+(OTHER_CHRGS_TOTAL));                  
							rowList11.add(dataStyle1);//12
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((TRANS_ALL_TOTAL));                  
							rowList11.add(dataStyle1);//13
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((FES_ADV_TOTAL));                  
							rowList11.add(dataStyle1);//14
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((FOOD_ADV_TOTAL));                  
							rowList11.add(dataStyle1);//15
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((PAY_RECOVER_TOTAL));                  
							rowList11.add(dataStyle1);//16
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GROSS_AMT_TOTAL));                  
							rowList11.add(dataStyle1);//17
							rowTotal.add(dataStyle1);	

							rowList11.add("");//18
							rowTotal.add("");	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((IT_TOTAL)+System.getProperty("line.separator")+(SURCHARGE_TOTAL));                  
							rowList11.add(dataStyle1);//19
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((HRR_TOTAL)+System.getProperty("line.separator")+(RENT_B_TOTAL));                  
							rowList11.add(dataStyle1);//20
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((PLI_TOTAL)+System.getProperty("line.separator")+(BLI_TOTAL));                  
							rowList11.add(dataStyle1);//21
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((PT_TOTAL));                  
							rowList11.add(dataStyle1);//22
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((SIS_GIS_1979_TOTAL));                  
							rowList11.add(dataStyle1);//23
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((SIS_IF_1981_TOTAL));                  
							rowList11.add(dataStyle1);//24
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((SIS_SF_1981_TOTAL));                  
							rowList11.add(dataStyle1);//25
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((AIS_IF_1980_TOTAL));                  
							rowList11.add(dataStyle1);//26
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((AIS_SF_1980_TOTAL));                  
							rowList11.add(dataStyle1);//27
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GPF_C_TOTAL)+System.getProperty("line.separator")+(CPF_TOTAL)+System.getProperty("line.separator")+(AIS_PF_TOTAL)+System.getProperty("line.separator")+(GPF_ADV_TOTAL)+System.getProperty("line.separator")+(DA_GPF_TOTAL));                  
							rowList11.add(dataStyle1);//28
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((CAR_SCT_MOPED_ADV_TOTAL)+System.getProperty("line.separator")+(CAR_SCT_MOPED_INT_TOTAL));                  
							rowList11.add(dataStyle1);//29
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((OCA_CYCLE_ADV_TOTAL)+System.getProperty("line.separator")+(OCA_CYCLE_INT_TOTAL));                  
							rowList11.add(dataStyle1);//30
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((HBA_TOTAL)+System.getProperty("line.separator")+(HBA_INT_TOTAL));                  
							rowList11.add(dataStyle1);//31
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((FAN_ADV_TOTAL)+System.getProperty("line.separator")+(FAN_INT_TOTAL));                  
							rowList11.add(dataStyle1);//32
							rowTotal.add(dataStyle1);	



							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((JEEP_R_TOTAL)+System.getProperty("line.separator")+(MISC_RECOV_TOTAL));                  
							rowList11.add(dataStyle1);//33
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GPF_IV_TOTAL)+System.getProperty("line.separator")+(GPF_IV_ADV_TOTAL)+System.getProperty("line.separator")+(DA_GPFIV_TOTAL));                  
							rowList11.add(dataStyle1);//34
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((TOTAL_DED_TOTAL));                  
							rowList11.add(dataStyle1);//35
							rowTotal.add(dataStyle1);	

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((NET_TOTAL_TOTAL));                  
							rowList11.add(dataStyle1);//36
							rowTotal.add(dataStyle1);	

							pageTotalList.add(rowTotal);//Total row added in pagetotal list
							// rowList11.add("");
							rowList11.add(pagecnt);//37
							pagecnt++;
							
							DataList.add(rowList11);



							GRAND_PER_PAY_TOTAL	+= PER_PAY_TOTAL ;
							GRAND_SPL_PAY_TOTAL	+= SPL_PAY_TOTAL ;
							GRAND_PO_TOTAL	+= PO_TOTAL ;
							GRAND_PE_TOTAL	+= PE_TOTAL ;
							GRAND_GPGAZ_TOTAL	+= GPGAZ_TOTAL ;
							GRAND_GPNONGAZ_TOTAL	+= GPNONGAZ_TOTAL ;
							GRAND_LS_TOTAL	+= LS_TOTAL ;
							GRAND_LE_TOTAL	+= LE_TOTAL ;
							GRAND_DP_GAZZETED_TOTAL	+= DP_GAZZETED_TOTAL ;
							GRAND_D_PAY_TOTAL	+= D_PAY_TOTAL ;
							GRAND_DA_TOTAL	+= DA_TOTAL ;
							GRAND_HRA_TOTAL	+= HRA_TOTAL ;
							GRAND_LTC_TOTAL	+= LTC_TOTAL ;
							GRAND_CLA_TOTAL	+= CLA_TOTAL ;
							GRAND_OTHER_ALLOW_TOTAL	+= OTHER_ALLOW_TOTAL ;
							GRAND_MA_TOTAL	+= MA_TOTAL ;
							GRAND_BONUS_TOTAL	+= BONUS_TOTAL ;
							GRAND_WA_TOTAL	+= WA_TOTAL ;
							GRAND_OTHER_CHRGS_TOTAL	+= OTHER_CHRGS_TOTAL ;
							GRAND_TRANS_ALL_TOTAL	+= TRANS_ALL_TOTAL ;
							GRAND_FES_ADV_TOTAL	+= FES_ADV_TOTAL ;
							GRAND_FOOD_ADV_TOTAL	+= FOOD_ADV_TOTAL ;
							GRAND_PAY_RECOVER_TOTAL	+= PAY_RECOVER_TOTAL ;
							GRAND_GROSS_AMT_TOTAL	+= GROSS_AMT_TOTAL ;
							GRAND_SLO_TOTAL	+= SLO_TOTAL ;
							GRAND_IT_TOTAL	+= IT_TOTAL ;
							GRAND_SURCHARGE_TOTAL += SURCHARGE_TOTAL ;
							GRAND_HRR_TOTAL += HRR_TOTAL ;
							GRAND_RENT_B_TOTAL += RENT_B_TOTAL ;
							GRAND_PLI_TOTAL += PLI_TOTAL ;
							GRAND_BLI_TOTAL += BLI_TOTAL ;
							GRAND_PT_TOTAL += PT_TOTAL ;
							GRAND_SIS_GIS_1979_TOTAL += SIS_GIS_1979_TOTAL ;
							GRAND_SIS_IF_1981_TOTAL += SIS_IF_1981_TOTAL ;
							GRAND_SIS_SF_1981_TOTAL += SIS_SF_1981_TOTAL ;
							GRAND_AIS_IF_1980_TOTAL += AIS_IF_1980_TOTAL ;
							GRAND_AIS_SF_1980_TOTAL += AIS_SF_1980_TOTAL ;
							GRAND_GPF_C_TOTAL += GPF_C_TOTAL ;
							GRAND_DA_GPF_TOTAL += DA_GPF_TOTAL;
							GRAND_DA_GPFIV_TOTAL += DA_GPFIV_TOTAL;
							GRAND_CPF_TOTAL += CPF_TOTAL;
							GRAND_AIS_PF_TOTAL += AIS_PF_TOTAL;
							GRAND_GPF_ADV_TOTAL += GPF_ADV_TOTAL ;
							GRAND_CAR_SCT_MOPED_ADV_TOTAL += CAR_SCT_MOPED_ADV_TOTAL ;
							GRAND_OCA_CYCLE_ADV_TOTAL += OCA_CYCLE_ADV_TOTAL ;
							GRAND_HBA_TOTAL += HBA_TOTAL ;
							GRAND_FAN_ADV_TOTAL += FAN_ADV_TOTAL ;
							GRAND_CAR_SCT_MOPED_INT_TOTAL += CAR_SCT_MOPED_INT_TOTAL ;
							GRAND_OCA_CYCLE_INT_TOTAL += OCA_CYCLE_INT_TOTAL ;
							GRAND_HBA_INT_TOTAL += HBA_INT_TOTAL ;
							GRAND_FAN_INT_TOTAL += FAN_INT_TOTAL ;
							GRAND_JEEP_R_TOTAL += JEEP_R_TOTAL ;
							GRAND_MISC_RECOV_TOTAL += MISC_RECOV_TOTAL ;
							GRAND_GPF_IV_TOTAL += GPF_IV_TOTAL ;
							GRAND_GPF_IV_ADV_TOTAL += GPF_IV_ADV_TOTAL ;
							GRAND_TOTAL_DED_TOTAL += TOTAL_DED_TOTAL ;
							GRAND_NET_TOTAL_TOTAL += NET_TOTAL_TOTAL ;



							PER_PAY_TOTAL=0;
							SPL_PAY_TOTAL=0;
							PO_TOTAL=0;
							PE_TOTAL=0;
							GPGAZ_TOTAL=0;
							GPNONGAZ_TOTAL=0;
							LS_TOTAL=0;
							LE_TOTAL=0;
							DP_GAZZETED_TOTAL=0;
							D_PAY_TOTAL=0;
							DA_TOTAL=0;
							HRA_TOTAL=0;
							LTC_TOTAL=0;
							CLA_TOTAL=0;
							OTHER_ALLOW_TOTAL=0;
							MA_TOTAL=0;
							BONUS_TOTAL=0;
							WA_TOTAL=0;
							OTHER_CHRGS_TOTAL=0;
							TRANS_ALL_TOTAL=0;
							FES_ADV_TOTAL=0;
							FOOD_ADV_TOTAL=0;
							PAY_RECOVER_TOTAL=0;
							GROSS_AMT_TOTAL=0;
							SLO_TOTAL=0;
							IT_TOTAL=0;
							SURCHARGE_TOTAL=0;
							HRR_TOTAL=0;
							RENT_B_TOTAL=0;
							PLI_TOTAL=0;
							BLI_TOTAL=0;
							PT_TOTAL=0;
							SIS_GIS_1979_TOTAL=0;
							SIS_IF_1981_TOTAL=0;
							SIS_SF_1981_TOTAL=0;
							AIS_IF_1980_TOTAL=0;
							AIS_SF_1980_TOTAL=0;
							GPF_C_TOTAL=0;
							DA_GPF_TOTAL=0;
							DA_GPFIV_TOTAL=0;
							CPF_TOTAL=0;
							AIS_PF_TOTAL=0;
							GPF_ADV_TOTAL=0;
							CAR_SCT_MOPED_ADV_TOTAL=0;
							OCA_CYCLE_ADV_TOTAL=0;
							HBA_TOTAL=0;
							FAN_ADV_TOTAL=0;
							CAR_SCT_MOPED_INT_TOTAL=0;
							OCA_CYCLE_INT_TOTAL=0;
							HBA_INT_TOTAL=0;
							FAN_INT_TOTAL=0;
							JEEP_R_TOTAL=0;
							MISC_RECOV_TOTAL=0;
							GPF_IV_TOTAL=0;
							GPF_IV_ADV_TOTAL=0;
							TOTAL_DED_TOTAL=0;
							NET_TOTAL_TOTAL=0;

						}		          	      
						

						dsgncnt=0;//reset dsgn count for new order designation
						ArrayList orderdataList = new ArrayList();
						/*orderdataList.add(new Integer(displayCnt));//1
		          	    	for(int x=0;x<15;x++)
		          	    		orderdataList.add("");	
		          	    		orderdataList.add(ORDER_NO+" "+ORDER_DATE);	
		          	    	for(int x=0;x<18;x++)
		          	    		orderdataList.add("");	*/

						/*StyledData dataStyle1 = new StyledData();
		          	    	styles[0] = new StyleVO();
		          			styles[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		          			styles[0].setStyleValue("Center");*/

						StyleVO[] centerboldStyleVO = new StyleVO[2];
						centerboldStyleVO[0] = new StyleVO();
						centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
						centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
						centerboldStyleVO[1] = new StyleVO();
						centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
						centerboldStyleVO[1].setStyleValue("Left"); 
						StyledData dataStyle1 = new StyledData();
						dataStyle1.setStyles(centerboldStyleVO);
						blankspace="                                                                                     ";
						if(officeName.equals(""))
						{
							if(!oldOrderNo.equals(ORDER_NO))
						dataStyle1.setData("                                       "+"Sanction Order No."+" "+ORDER_NO+" Date: "+ORDER_DATE); 
							else
								dataStyle1.setData("                                       ");	
						}
						else
						{
							
							if(!oldOfficeName.equals(officeName))
							dataStyle1.setData("                                       "+officeName);
							else
								dataStyle1.setData("                                       ");
						}	
						StyledData dataStyle2 = new StyledData();
						dataStyle2.setStyles(centerboldStyleVO);
						dataStyle2.setData(" ");
						dataStyle2.setColspan(3);
						StyledData dataStyle3 = new StyledData();
						dataStyle3.setStyles(centerboldStyleVO);
						String space=" ";
						dataStyle3.setData(space.replaceAll(" ",constantsBundle.getString("blankSpace")));
						dataStyle3.setColspan(15);
						
						{
							orderdataList.add(dataStyle2);
							orderdataList.add("");
							orderdataList.add("");
							dataStyle1.setColspan(16);
							orderdataList.add(dataStyle1);
							for(int x=0;x<15;x++)
								orderdataList.add("");
							orderdataList.add("");
							orderdataList.add("");
							orderdataList.add(dataStyle3);
							for(int x=0;x<14;x++)
								orderdataList.add("");
							
						}
						
						blankspace="                                                                                     ";
						orderdataList.add(pagecnt);	
						DataList.add(orderdataList);
						cnt++;
						//displayCnt++; we wont add dipaly cnt as no need to add order row count

						if(DataList.size()>1)//display desig conter of last order
						{
							
							ArrayList oldorderdataList=new ArrayList();
							oldorderdataList=(ArrayList)DataList.get(oldorderindex);
							StyledData st=(StyledData)oldorderdataList.get(3);
							String blankspace2="    ";
							if(oldOfficeRemarks.equals(""))//// code for normal order functionality
							{
							if(oldOfficeName.equals(""))
							{
								if((Long.parseLong(billType)==supplBill_Multiple_Month)&& (row_Count%prev_Emp_Count!=1) )
								{
									
									st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+" "+oldMonthYearInfo);		
								}
								else
							st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+(sameorderdsgns+1)+" "+oldOrderDsgn+" "+oldPosttype+" "+oldMonthYearInfo);
							}
							else{
								
								if((Long.parseLong(billType)==supplBill_Multiple_Month) && (row_Count%prev_Emp_Count!=1))
								{
									st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+oldMonthYearInfo);		
								}
								else
									st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+(sameorderdsgns+1)+":POST "+oldOrderDsgn+" "+oldPosttype+" "+oldMonthYearInfo);	
							}
							
							}
							else//// code for office display functionality
							{
								if((Long.parseLong(billType)==supplBill_Multiple_Month) && (row_Count%prev_Emp_Count!=1) )
								{
									 st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+oldMonthYearInfo);		
								}
								else
							  st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+(sameorderdsgns+1)+":POST "+oldOrderDsgn+oldPosttype+"("+oldOfficeRemarks+")"+" "+oldMonthYearInfo);
								
							} 
							oldorderdataList.set(3,st); 
							//oldorderdataList.set(16, oldorderdataList.get(16)+" "+(sameorderdsgns+1)+" "+oldOrderDsgn);// settiong counter of desigs in old order
							DataList.set(oldorderindex, oldorderdataList);
						}

						oldorderindex=DataList.size()-1;
						sameorderdsgns=0;//reset for diff order 
					}
					else
					{
						
						sameorderdsgns++;
					}
					oldOrderNo=ORDER_NO;
					oldOrderDate=ORDER_DATE;
					oldOrderDsgn=designation;
					oldPosttype=posttype;
					oldOfficeName=officeName;
					oldOfficeRemarks=officeRemarks;
					oldMonthYearInfo=monthYearInfo;
					
					
					PER_PAY_TOTAL+=PER_PAY;
					SPL_PAY_TOTAL+=SPL_PAY;
					PO_TOTAL+=PO;
					PE_TOTAL+=PE;
					if(PO+LS>0)
					GPGAZ_TOTAL+=GPay;
					else if(PE+LS>0)
					GPNONGAZ_TOTAL+=GPay;
					LS_TOTAL+=LS;
					LE_TOTAL+=LE;
					D_PAY_TOTAL+=D_PAY;
					DP_GAZZETED_TOTAL+=DP_GAZZETED;
					DA_TOTAL+=DA;
					HRA_TOTAL+=HRA;
					LTC_TOTAL+=LTC;
					CLA_TOTAL+=CLA;
					OTHER_ALLOW_TOTAL+=OTHER_ALLOW;
					MA_TOTAL+=MA;
					BONUS_TOTAL+=BONUS;
					WA_TOTAL+=WA;
					OTHER_CHRGS_TOTAL+=OTHER_CHRGS;
					TRANS_ALL_TOTAL+=TRANS_ALL;
					FES_ADV_TOTAL+=FES_ADV;
					FOOD_ADV_TOTAL+=FOOD_ADV;
					PAY_RECOVER_TOTAL+=PAY_RECOVER;
					GROSS_AMT_TOTAL+=GROSS_AMT;
					SLO_TOTAL+=SLO;
					IT_TOTAL+=IT;
					SURCHARGE_TOTAL+=SURCHARGE;
					HRR_TOTAL+=HRR;
					RENT_B_TOTAL+=RENT_B;
					PLI_TOTAL+=PLI;
					BLI_TOTAL+=BLI;
					PT_TOTAL+=PT;
					SIS_GIS_1979_TOTAL+=SIS_GIS_1979;
					SIS_IF_1981_TOTAL+=SIS_IF_1981;
					SIS_SF_1981_TOTAL+=SIS_SF_1981;
					AIS_IF_1980_TOTAL+=AIS_IF_1980;
					AIS_SF_1980_TOTAL+=AIS_SF_1980;
					GPF_C_TOTAL+=GPF_C;
					DA_GPF_TOTAL+=daGpf;
					DA_GPFIV_TOTAL+=daGpfIv;
					CPF_TOTAL+=CPF;
					AIS_PF_TOTAL+=AISPF;
					GPF_ADV_TOTAL+=GPF_ADV;
					CAR_SCT_MOPED_ADV_TOTAL+=CAR_SCT_MOPED_ADV;
					OCA_CYCLE_ADV_TOTAL+=OCA_CYCLE_ADV;
					HBA_TOTAL+=HBA;
					FAN_ADV_TOTAL+=FAN_ADV;
					CAR_SCT_MOPED_INT_TOTAL+=CAR_SCT_MOPED_INT;
					OCA_CYCLE_INT_TOTAL+=OCA_CYCLE_INT;
					HBA_INT_TOTAL+=HBA_INT;
					FAN_INT_TOTAL+=FAN_INT;
					JEEP_R_TOTAL+=JEEP_R;
					MISC_RECOV_TOTAL+=MISC_RECOV;
					GPF_IV_TOTAL+=GPF_IV;
					GPF_IV_ADV_TOTAL+=GPF_IV_ADV;
					TOTAL_DED_TOTAL+=TOTAL_DED;
					NET_TOTAL_TOTAL+=NET_TOTAL;


					lRs1.beforeFirst();
					rowList = new ArrayList();
					if(resourceBundle.getString("GAD").equalsIgnoreCase(locationId+""))
					{	

						StyledData dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO1);
						dataStyle1.setData(PsrNo);                   
						rowList.add(dataStyle1);//2
						logger.info("in if in gad");
						
					}	
					else
					{
						logger.info("in else in fd");
						StyledData dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO1);
						dataStyle1.setData(new Integer(displayCnt));                   
						rowList.add(dataStyle1);//2
						//rowList.add(new Integer(displayCnt));//1

					}
					dsgncnt++;
					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(colorStyleVO1);
					
					String gPayDisplay = System.getProperty("line.separator")+before.replaceAll(" ",constantsBundle.getString("blankSpace"));
					
					if(billType.equals(String.valueOf(arrearbillTypeId)))
					{	
					gPayDisplay = System.getProperty("line.separator")+"Basic "+basic;
					
					if(gradePayDisplay>0)
						gPayDisplay+=" GP("+gradePayDisplay+")";
					}
					
					if((Long.parseLong(billType)==supplBill_Multiple_Month))
					{
					 dataStyle1.setData("  "+scale+System.getProperty("line.separator")+Name+System.getProperty("line.separator")+
							 gpfAccNo+(IncrementAmt!=0?(System.getProperty("line.separator")+"Incr. Amt. "+IncrementAmt):"")+
								System.getProperty("line.separator")+
								before.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")
								+before.replaceAll(" ",constantsBundle.getString("blankSpace"))); 
					}
					else{
						 dataStyle1.setData(dsgncnt+"   "+designation+"  "+scale+System.getProperty("line.separator")+Name+System.getProperty("line.separator")+
							
							gpfAccNo+(IncrementAmt!=0?(System.getProperty("line.separator")+"Incr. Amt. "+IncrementAmt):"")+
							gPayDisplay
							+System.getProperty("line.separator")
							+before.replaceAll(" ",constantsBundle.getString("blankSpace")));        
					}
						 rowList.add(dataStyle1);//2

					//rowList.add(dsgncnt+"   "+designation+"  "+scale+System.getProperty("line.separator")+Name+System.getProperty("line.separator")+gpfAccNo+(IncrementAmt!=0?(System.getProperty("line.separator")+"Incr. Amt. "+IncrementAmt):"") );//2
					//String spaceBuffer=" ";
					//rowList.add(dsgncnt+"   "+designation+"  "+scale+System.getProperty("line.separator")+Name+System.getProperty("line.separator")+gpfAccNo+(IncrementAmt!=0?(System.getProperty("line.separator")+"Incr. Amt. "+IncrementAmt):(System.getProperty("line.separator")+spaceBuffer.replaceAll(" ",constantsBundle.getString("blankSpace")))) );//2
					//rowList.add(new Integer(cnt+1));
					//rowList.add(ORDER_NO+System.getProperty("line.separator"));
					StringBuffer data = new StringBuffer();
					int prevorderno = -1;
					int itr = 0;
					while(lRs1.next())
					{
						
						int orderno = lRs1.getInt("colNo"); // Order No. This is the column Order no which specifies 
						// in a particular column how data will be displayed 
						// For example in a single Col. HBA & HBA Int is shown.
						if(prevorderno == orderno)
						{
							data.append(System.getProperty("line.separator"));
							if(lRs1.getString("PAYBILL_COL")!=null && !lRs1.getString("PAYBILL_COL").equals(""))
								data.append(ht.get(lRs1.getString("PAYBILL_COL")));
							else data.append("0");
						}
						else
						{
							if(prevorderno!=-1)
							{
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(colorStyleVO1);
								dataStyle1.setData(data.toString());                   
								rowList.add(dataStyle1);//3
								data = new StringBuffer();
							}
							if(lRs1.getString("PAYBILL_COL")!=null && !lRs1.getString("PAYBILL_COL").equals(""))
								data.append(ht.get(lRs1.getString("PAYBILL_COL")));
							else data.append("0");
						}
						if(lRs1.isLast()) 
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO1);
							dataStyle1.setData(data.toString());                   
							rowList.add(dataStyle1);//2


						}
						prevorderno = orderno;
					}


					rowList.add(pagecnt);//4

					DataList.add(rowList); 
					cnt++;
					displayCnt++;// here data is for actual employee and NOT for mere order so increment display counter
					//long breakPoint=0;
					if(listSize<(pagesize-1)&&RowList.size()==displayCnt-1)//counter has been already incremented so -1
					{ 	  
						breakPoint=1;
						
					}	  
					else
						breakPoint=(pagesize-1);
					logger.info("********lower*******"+cnt);
					if(cnt%breakPoint==0)
					{
						listSize-=(pagesize-1);
						rowList11=new ArrayList();	
						ArrayList rowTotal=new ArrayList();
						rowList11.add("");//1
						rowTotal.add(pagecnt);
						
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(before.replaceAll(" ",constantsBundle.getString("blankSpace")+System.getProperty("line.separator"))+before.replaceAll(" ",constantsBundle.getString("blankSpace")+System.getProperty("line.separator"))+"Total"+before.replaceAll(" ",constantsBundle.getString("blankSpace")+System.getProperty("line.separator"))+before.replaceAll(" ",constantsBundle.getString("blankSpace")+System.getProperty("line.separator"))+System.getProperty("line.separator")
								+before.replaceAll(" ",constantsBundle.getString("blankSpace")));                  
						rowList11.add(dataStyle1);//2
						rowTotal.add("");				                

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						//dataStyle1.setData((PER_PAY_TOTAL)+System.getProperty("line.separator")+(SPL_PAY_TOTAL));
						
						dataStyle1.setData(before.replaceAll(" ",constantsBundle.getString("blankSpace"))+
								System.getProperty("line.separator")+
								before.replaceAll(" ",constantsBundle.getString("blankSpace")+	
								System.getProperty("line.separator")+
								(PER_PAY_TOTAL)+
								System.getProperty("line.separator")+(SPL_PAY_TOTAL)+
								System.getProperty("line.separator")+
						before.replaceAll(" ",constantsBundle.getString("blankSpace")))			
						); 
						
						/*dataStyle1.setData(before.replaceAll(" ",constantsBundle.getString("blankSpace")+
								
										System.getProperty("line.separator"))+(PER_PAY_TOTAL)+
										System.getProperty("line.separator")+(SPL_PAY_TOTAL)+
										System.getProperty("line.separator"))
						+before.replaceAll(" ",constantsBundle.getString("blankSpace")	)					
						);*/    
						
						rowList11.add(dataStyle1);//3
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((PO_TOTAL)+System.getProperty("line.separator")+(PE_TOTAL));                  
						rowList11.add(dataStyle1);//4
						rowTotal.add(dataStyle1);	

						///////////// for GP /////////
						
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((GPGAZ_TOTAL)+System.getProperty("line.separator")+(GPNONGAZ_TOTAL));                  
						rowList11.add(dataStyle1);//4
						rowTotal.add(dataStyle1);
						
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((LS_TOTAL)+System.getProperty("line.separator")+(LE_TOTAL));                  
						rowList11.add(dataStyle1);//5
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((DP_GAZZETED_TOTAL)+System.getProperty("line.separator")+(D_PAY_TOTAL ));                  
						rowList11.add(dataStyle1);//6
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((DA_TOTAL));                  
						rowList11.add(dataStyle1);//7
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((HRA_TOTAL)+System.getProperty("line.separator")+(LTC_TOTAL));                  
						rowList11.add(dataStyle1);//8
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((CLA_TOTAL)+System.getProperty("line.separator")+(OTHER_ALLOW_TOTAL));                  
						rowList11.add(dataStyle1);//9
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((MA_TOTAL)+System.getProperty("line.separator")+(BONUS_TOTAL));                  
						rowList11.add(dataStyle1);//10
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((WA_TOTAL)+System.getProperty("line.separator")+(OTHER_CHRGS_TOTAL));                  
						rowList11.add(dataStyle1);//12
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((TRANS_ALL_TOTAL));                  
						rowList11.add(dataStyle1);//13
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((FES_ADV_TOTAL));                  
						rowList11.add(dataStyle1);//14
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((FOOD_ADV_TOTAL));                  
						rowList11.add(dataStyle1);//15
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((PAY_RECOVER_TOTAL));                  
						rowList11.add(dataStyle1);//16
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((GROSS_AMT_TOTAL));                  
						rowList11.add(dataStyle1);//17
						rowTotal.add(dataStyle1);	

						String AuditSpace=" ";
						rowList11.add(AuditSpace);//18
						rowTotal.add(AuditSpace);//18
						//rowTotal.add(AuditSpace.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")+AuditSpace.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")+AuditSpace.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")+AuditSpace.replaceAll(" ",constantsBundle.getString("blankSpace")));//18

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((IT_TOTAL)+System.getProperty("line.separator")+(SURCHARGE_TOTAL));                  
						rowList11.add(dataStyle1);//19
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((HRR_TOTAL)+System.getProperty("line.separator")+(RENT_B_TOTAL));                  
						rowList11.add(dataStyle1);//20
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((PLI_TOTAL)+System.getProperty("line.separator")+(BLI_TOTAL));                  
						rowList11.add(dataStyle1);//21
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((PT_TOTAL));                  
						rowList11.add(dataStyle1);//22
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((SIS_GIS_1979_TOTAL));                  
						rowList11.add(dataStyle1);//23
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((SIS_IF_1981_TOTAL));                  
						rowList11.add(dataStyle1);//24
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((SIS_SF_1981_TOTAL));                  
						rowList11.add(dataStyle1);//25
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((AIS_IF_1980_TOTAL));                  
						rowList11.add(dataStyle1);//26
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((AIS_SF_1980_TOTAL));                  
						rowList11.add(dataStyle1);//27
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((GPF_C_TOTAL)+System.getProperty("line.separator")+(CPF_TOTAL)+System.getProperty("line.separator")+(AIS_PF_TOTAL)+System.getProperty("line.separator")+(GPF_ADV_TOTAL)+System.getProperty("line.separator")+(DA_GPF_TOTAL));                  
						rowList11.add(dataStyle1);//28
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((CAR_SCT_MOPED_ADV_TOTAL)+System.getProperty("line.separator")+(CAR_SCT_MOPED_INT_TOTAL));                  
						rowList11.add(dataStyle1);//29
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((OCA_CYCLE_ADV_TOTAL)+System.getProperty("line.separator")+(OCA_CYCLE_INT_TOTAL));                  
						rowList11.add(dataStyle1);//30
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((HBA_TOTAL)+System.getProperty("line.separator")+(HBA_INT_TOTAL));                  
						rowList11.add(dataStyle1);//31
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((FAN_ADV_TOTAL)+System.getProperty("line.separator")+(FAN_INT_TOTAL));                  
						rowList11.add(dataStyle1);//32
						rowTotal.add(dataStyle1);	



						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((JEEP_R_TOTAL)+System.getProperty("line.separator")+(MISC_RECOV_TOTAL));                  
						rowList11.add(dataStyle1);//33
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((GPF_IV_TOTAL)+System.getProperty("line.separator")+(GPF_IV_ADV_TOTAL)+System.getProperty("line.separator")+(DA_GPFIV_TOTAL));                  
						rowList11.add(dataStyle1);//34
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((TOTAL_DED_TOTAL));                  
						rowList11.add(dataStyle1);//35
						rowTotal.add(dataStyle1);	

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData((NET_TOTAL_TOTAL));                  
						rowList11.add(dataStyle1);//36
						rowTotal.add(dataStyle1);	

						pageTotalList.add(rowTotal);//Total row added in pagetotal list
						// rowList11.add("");
						rowList11.add(pagecnt);//37
						pagecnt++;
						DataList.add(rowList11);



						GRAND_PER_PAY_TOTAL	+= PER_PAY_TOTAL ;
						GRAND_SPL_PAY_TOTAL	+= SPL_PAY_TOTAL ;
						GRAND_PO_TOTAL	+= PO_TOTAL ;
						GRAND_PE_TOTAL	+= PE_TOTAL ;
						GRAND_GPGAZ_TOTAL	+= GPGAZ_TOTAL ;
						GRAND_GPNONGAZ_TOTAL	+= GPNONGAZ_TOTAL ;
						GRAND_LS_TOTAL	+= LS_TOTAL ;
						GRAND_LE_TOTAL	+= LE_TOTAL ;
						GRAND_DP_GAZZETED_TOTAL	+= DP_GAZZETED_TOTAL ;
						GRAND_D_PAY_TOTAL	+= D_PAY_TOTAL ;
						GRAND_DA_TOTAL	+= DA_TOTAL ;
						GRAND_HRA_TOTAL	+= HRA_TOTAL ;
						GRAND_LTC_TOTAL	+= LTC_TOTAL ;
						GRAND_CLA_TOTAL	+= CLA_TOTAL ;
						GRAND_OTHER_ALLOW_TOTAL	+= OTHER_ALLOW_TOTAL ;
						GRAND_MA_TOTAL	+= MA_TOTAL ;
						GRAND_BONUS_TOTAL	+= BONUS_TOTAL ;
						GRAND_WA_TOTAL	+= WA_TOTAL ;
						GRAND_OTHER_CHRGS_TOTAL	+= OTHER_CHRGS_TOTAL ;
						GRAND_TRANS_ALL_TOTAL	+= TRANS_ALL_TOTAL ;
						GRAND_FES_ADV_TOTAL	+= FES_ADV_TOTAL ;
						GRAND_FOOD_ADV_TOTAL	+= FOOD_ADV_TOTAL ;
						GRAND_PAY_RECOVER_TOTAL	+= PAY_RECOVER_TOTAL ;
						GRAND_GROSS_AMT_TOTAL	+= GROSS_AMT_TOTAL ;
						GRAND_SLO_TOTAL	+= SLO_TOTAL ;
						GRAND_IT_TOTAL	+= IT_TOTAL ;
						GRAND_SURCHARGE_TOTAL += SURCHARGE_TOTAL ;
						GRAND_HRR_TOTAL += HRR_TOTAL ;
						GRAND_RENT_B_TOTAL += RENT_B_TOTAL ;
						GRAND_PLI_TOTAL += PLI_TOTAL ;
						GRAND_BLI_TOTAL += BLI_TOTAL ;
						GRAND_PT_TOTAL += PT_TOTAL ;
						GRAND_SIS_GIS_1979_TOTAL += SIS_GIS_1979_TOTAL ;
						GRAND_SIS_IF_1981_TOTAL += SIS_IF_1981_TOTAL ;
						GRAND_SIS_SF_1981_TOTAL += SIS_SF_1981_TOTAL ;
						GRAND_AIS_IF_1980_TOTAL += AIS_IF_1980_TOTAL ;
						GRAND_AIS_SF_1980_TOTAL += AIS_SF_1980_TOTAL ;
						GRAND_GPF_C_TOTAL += GPF_C_TOTAL ;
						GRAND_DA_GPF_TOTAL += DA_GPF_TOTAL ;
						GRAND_DA_GPFIV_TOTAL += DA_GPFIV_TOTAL ;
						GRAND_CPF_TOTAL += CPF_TOTAL ;
						GRAND_AIS_PF_TOTAL += AIS_PF_TOTAL ;
						GRAND_GPF_ADV_TOTAL += GPF_ADV_TOTAL ;
						GRAND_CAR_SCT_MOPED_ADV_TOTAL += CAR_SCT_MOPED_ADV_TOTAL ;
						GRAND_OCA_CYCLE_ADV_TOTAL += OCA_CYCLE_ADV_TOTAL ;
						GRAND_HBA_TOTAL += HBA_TOTAL ;
						GRAND_FAN_ADV_TOTAL += FAN_ADV_TOTAL ;
						GRAND_CAR_SCT_MOPED_INT_TOTAL += CAR_SCT_MOPED_INT_TOTAL ;
						GRAND_OCA_CYCLE_INT_TOTAL += OCA_CYCLE_INT_TOTAL ;
						GRAND_HBA_INT_TOTAL += HBA_INT_TOTAL ;
						GRAND_FAN_INT_TOTAL += FAN_INT_TOTAL ;
						GRAND_JEEP_R_TOTAL += JEEP_R_TOTAL ;
						GRAND_MISC_RECOV_TOTAL += MISC_RECOV_TOTAL ;
						GRAND_GPF_IV_TOTAL += GPF_IV_TOTAL ;
						GRAND_GPF_IV_ADV_TOTAL += GPF_IV_ADV_TOTAL ;
						GRAND_TOTAL_DED_TOTAL += TOTAL_DED_TOTAL ;
						GRAND_NET_TOTAL_TOTAL += NET_TOTAL_TOTAL ;



						PER_PAY_TOTAL=0;
						SPL_PAY_TOTAL=0;
						PO_TOTAL=0;
						PE_TOTAL=0;
						GPGAZ_TOTAL=0;
						GPNONGAZ_TOTAL=0;
						LS_TOTAL=0;
						LE_TOTAL=0;
						DP_GAZZETED_TOTAL=0;
						D_PAY_TOTAL=0;
						DA_TOTAL=0;
						HRA_TOTAL=0;
						LTC_TOTAL=0;
						CLA_TOTAL=0;
						OTHER_ALLOW_TOTAL=0;
						MA_TOTAL=0;
						BONUS_TOTAL=0;
						WA_TOTAL=0;
						OTHER_CHRGS_TOTAL=0;
						TRANS_ALL_TOTAL=0;
						FES_ADV_TOTAL=0;
						FOOD_ADV_TOTAL=0;
						PAY_RECOVER_TOTAL=0;
						GROSS_AMT_TOTAL=0;
						SLO_TOTAL=0;
						IT_TOTAL=0;
						SURCHARGE_TOTAL=0;
						HRR_TOTAL=0;
						RENT_B_TOTAL=0;
						PLI_TOTAL=0;
						BLI_TOTAL=0;
						PT_TOTAL=0;
						SIS_GIS_1979_TOTAL=0;
						SIS_IF_1981_TOTAL=0;
						SIS_SF_1981_TOTAL=0;
						AIS_IF_1980_TOTAL=0;
						AIS_SF_1980_TOTAL=0;
						GPF_C_TOTAL=0;
						DA_GPF_TOTAL=0;
						DA_GPFIV_TOTAL=0;
						CPF_TOTAL=0;
						AIS_PF_TOTAL=0;
						GPF_ADV_TOTAL=0;
						CAR_SCT_MOPED_ADV_TOTAL=0;
						OCA_CYCLE_ADV_TOTAL=0;
						HBA_TOTAL=0;
						FAN_ADV_TOTAL=0;
						CAR_SCT_MOPED_INT_TOTAL=0;
						OCA_CYCLE_INT_TOTAL=0;
						HBA_INT_TOTAL=0;
						FAN_INT_TOTAL=0;
						JEEP_R_TOTAL=0;
						MISC_RECOV_TOTAL=0;
						GPF_IV_TOTAL=0;
						GPF_IV_ADV_TOTAL=0;
						TOTAL_DED_TOTAL=0;
						NET_TOTAL_TOTAL=0;




					}
					int listtotalSize=0;



					if(RowList.size()==displayCnt-1)//counter has been already incremented so -1
					{
						
						if(DataList.size()>0)//display desig conter of last order
						{
							ArrayList oldorderdataList=new ArrayList();
							oldorderdataList=(ArrayList)DataList.get(oldorderindex);
							StyledData st=(StyledData)oldorderdataList.get(3);
							String blankspace2="    ";
							
							if(oldOfficeRemarks.equals(""))//// code for normal order functionality
							{
								if(oldOfficeName.equals(""))
								{
									
									if((Long.parseLong(billType)==supplBill_Multiple_Month)&& (row_Count%prev_Emp_Count!=1))
										st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+oldMonthYearInfo);
									else
										st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+(sameorderdsgns+1)+" "+oldOrderDsgn+" "+oldPosttype+" "+oldMonthYearInfo);		
								}
								else
								{
									
									if((Long.parseLong(billType)==supplBill_Multiple_Month)&& (row_Count%prev_Emp_Count!=1))
										st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+oldMonthYearInfo);
									else
										st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+(sameorderdsgns+1)+":POST "+oldOrderDsgn+" "+oldPosttype+" "+oldMonthYearInfo);	
									//commented by ravysh -- st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+"1:POST "+oldOrderDsgn+" "+oldPosttype+" "+oldMonthYearInfo);
								}
								}
							else//// code for office display functionality
							{	
								
								
								if((Long.parseLong(billType)==supplBill_Multiple_Month)&& (row_Count%prev_Emp_Count!=1))
								st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+oldMonthYearInfo);
								else
									st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+(sameorderdsgns+1)+":POST "+oldOrderDsgn+oldPosttype+"("+oldOfficeRemarks+")"+" "+oldMonthYearInfo);	
								//commented by ravysh -- st.setData(st.getData()+" "+blankspace2.replaceAll(" ",constantsBundle.getString("blankSpace"))+"1:POST "+oldOrderDsgn+oldPosttype+"("+oldOfficeRemarks+")"+" "+oldMonthYearInfo);
							} 
							
							oldorderdataList.set(3,st); 
							//oldorderdataList.set(16, oldorderdataList.get(16)+" "+(sameorderdsgns+1)+" "+oldOrderDsgn);// settiong counter of desigs in old order
							DataList.set(oldorderindex, oldorderdataList);
						}

						int totalrow=0;
						totalrow++;


						if(pageTotalList.size()>1)// displays summary total ig page size more than 1
						{	
							//Dispalying Summary Page pagecnt and taoatlrow incremented as it willsatrt from new page
							ArrayList summaryData = new ArrayList();
							StyledData dataSummary = new StyledData();

							StyleVO[] centerboldStyleVO = new StyleVO[2];
							centerboldStyleVO[0] = new StyleVO();
							centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
							centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
							centerboldStyleVO[1] = new StyleVO();
							centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
							centerboldStyleVO[1].setStyleValue("Center"); 

							dataSummary.setStyles(centerboldStyleVO);

							{
								dataSummary.setData("Summary report"); 
								dataSummary.setColspan(36);
								summaryData.add(dataSummary);//2
								for(int x=0;x<35;x++)
									summaryData.add("");
							}				                

							summaryData.add(pagecnt);	
							DataList.add(summaryData);			            	    

							for(int pagetotal=0;pagetotal<pageTotalList.size();pagetotal++)
							{
								totalrow++;
								ArrayList pagetotalrow=new ArrayList();
								pagetotalrow=(ArrayList)pageTotalList.get(pagetotal);

								pagetotalrow.add(pagecnt);
								if(totalrow%pagesize==0)
									pagecnt++;	
								DataList.add(pagetotalrow);	// All pagetotal added in datalist
							}



							rowList11=new ArrayList();	
							rowList11.add("");

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(before.replaceAll(" ",constantsBundle.getString("blankSpace")+
									System.getProperty("line.separator"))+before.replaceAll(" ",constantsBundle.getString("blankSpace")+
									System.getProperty("line.separator"))+"Grand Total"+
									before.replaceAll(" ",constantsBundle.getString("blankSpace")+
									System.getProperty("line.separator"))+
									before.replaceAll(" ",constantsBundle.getString("blankSpace")+
									System.getProperty("line.separator"))+before.replaceAll(" ",constantsBundle.getString("blankSpace")));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_PER_PAY_TOTAL)+System.getProperty("line.separator")+(GRAND_SPL_PAY_TOTAL));
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_PO_TOTAL)+System.getProperty("line.separator")+(GRAND_PE_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_GPGAZ_TOTAL)+System.getProperty("line.separator")+(GRAND_GPNONGAZ_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_LS_TOTAL)+System.getProperty("line.separator")+(GRAND_LE_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_DP_GAZZETED_TOTAL)+System.getProperty("line.separator")+(GRAND_D_PAY_TOTAL ));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_DA_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_HRA_TOTAL)+System.getProperty("line.separator")+(GRAND_LTC_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_CLA_TOTAL)+System.getProperty("line.separator")+(GRAND_OTHER_ALLOW_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_MA_TOTAL)+System.getProperty("line.separator")+(GRAND_BONUS_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_WA_TOTAL)+System.getProperty("line.separator")+(GRAND_OTHER_CHRGS_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_TRANS_ALL_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_FES_ADV_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_FOOD_ADV_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_PAY_RECOVER_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_GROSS_AMT_TOTAL));                  
							rowList11.add(dataStyle1);

							rowList11.add("");

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_IT_TOTAL)+System.getProperty("line.separator")+(GRAND_SURCHARGE_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_HRR_TOTAL)+System.getProperty("line.separator")+(GRAND_RENT_B_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_PLI_TOTAL)+System.getProperty("line.separator")+(GRAND_BLI_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_PT_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_SIS_GIS_1979_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_SIS_IF_1981_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_SIS_SF_1981_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_AIS_IF_1980_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_AIS_SF_1980_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO); 
							dataStyle1.setData((GRAND_GPF_C_TOTAL)+System.getProperty("line.separator")+(GRAND_CPF_TOTAL)+System.getProperty("line.separator")+(GRAND_AIS_PF_TOTAL)+System.getProperty("line.separator")+(GRAND_GPF_ADV_TOTAL)+System.getProperty("line.separator")+(GRAND_DA_GPF_TOTAL));                                    
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_CAR_SCT_MOPED_ADV_TOTAL+GRAND_CAR_SCT_MOPED_INT_TOTAL)+System.getProperty("line.separator")+(GRAND_CAR_SCT_MOPED_ADV_TOTAL)+System.getProperty("line.separator")+(GRAND_CAR_SCT_MOPED_INT_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_OCA_CYCLE_ADV_TOTAL+GRAND_OCA_CYCLE_INT_TOTAL)+System.getProperty("line.separator")+(GRAND_OCA_CYCLE_ADV_TOTAL)+"(Pri.)"+System.getProperty("line.separator")+(GRAND_OCA_CYCLE_INT_TOTAL)+"(Int.)");                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_HBA_TOTAL+GRAND_HBA_INT_TOTAL)+System.getProperty("line.separator")+(GRAND_HBA_TOTAL)+System.getProperty("line.separator")+(GRAND_HBA_INT_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_FAN_ADV_TOTAL+GRAND_FAN_INT_TOTAL)+System.getProperty("line.separator")+(GRAND_FAN_ADV_TOTAL)+System.getProperty("line.separator")+(GRAND_FAN_INT_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_JEEP_R_TOTAL)+System.getProperty("line.separator")+(GRAND_MISC_RECOV_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_GPF_IV_TOTAL)+System.getProperty("line.separator")+(GRAND_GPF_IV_ADV_TOTAL)+System.getProperty("line.separator")+(GRAND_DA_GPFIV_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_TOTAL_DED_TOTAL));                  
							rowList11.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData((GRAND_NET_TOTAL_TOTAL));                  
							rowList11.add(dataStyle1);

							rowList11.add(pagecnt);

							DataList.add(rowList11);

						}
					}
					
				row_Count++;
				}



				
				//		            added by Ankit Bhatt.
				String isBack = CheckIfNull(report.getParameterValue("backBtn"));
				StyleVO[] URLStyleVO=null;
				if(isBack.trim().equals("") || isBack==null)
					isBack="1";
				//if(isBack.equals("1"))
				// URLStyleVO = new StyleVO[5];
				//else
				URLStyleVO = new StyleVO[6];
				URLStyleVO[4] = new StyleVO();
				URLStyleVO[4].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_TEXT);
				URLStyleVO[0] = new StyleVO();
				URLStyleVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
				if(TokenFlag != null && TokenFlag.equals("YES"))
				{  
					URLStyleVO[0].setStyleValue("./hdiits.htm?actionFlag=showTokenNumber&billtype=" + report.getParameterValue( "billTypePara" ) + "&Month=" + report.getParameterValue("Month") + "&Year=" + report.getParameterValue("Year"));
					URLStyleVO[4].setStyleValue("  BACK  ");
				}
				else if(isBack.equals("1"))
				{

					if(report.getReportCode().equals("2500002"))
					{
						URLStyleVO[0].setStyleValue("./hrms.htm?actionFlag=approvePayBill&month=" + report.getParameterValue("Month") + "&year=" + report.getParameterValue("Year")+"&category=" + report.getParameterValue("Category")+"&Grade=" + classIds+"&dsgnId=" + desgnIds+"&subHeadId=" + subheadCode+"&billNo="+BillNo+"&reportcode="+2500002);
						URLStyleVO[4].setStyleValue("Approve");
					}
					else
						URLStyleVO[0].setStyleValue("./hrms.htm?actionFlag=reportService&reportCode=2&action=parameterPage&dynamicReport=true");	  

				}
				else
				{
					if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
					{
						URLStyleVO[0].setStyleValue("./hrms.htm?actionFlag=approveArrearBill&month=" + report.getParameterValue("Month") + "&year=" + report.getParameterValue("Year")+"&category=" + report.getParameterValue("Category")+"&Grade=" + report.getParameterValue("Grade")+"&dsgnId=" + dsgnIds+"&subHeadId=" + subHeadId+"&billNo="+billNo);
						URLStyleVO[4].setStyleValue("Approve");
					}
					else
					{
						URLStyleVO[0].setStyleValue("./hrms.htm?actionFlag=approvePayBill&month=" + report.getParameterValue("Month") + "&year=" + report.getParameterValue("Year")+"&category=" + report.getParameterValue("Category")+"&Grade=" + report.getParameterValue("Grade")+"&dsgnId=" + dsgnIds+"&subHeadId=" + subHeadId+"&billNo="+billNo);
						URLStyleVO[4].setStyleValue("Approve");
					}
				}

				//  URLStyleVO[0].setStyleValue("./hrms.htm?actionFlag=approvePayBill&month=" + report.getParameterValue("Month") + "&year=" + report.getParameterValue("Year")+"&category=" + report.getParameterValue("Category")+"&Grade=" + report.getParameterValue("Grade")+"&dsgnId=" + dsgnIds+"&subHeadId=" + subHeadId+"&billNo="+tmpBillNo);
				//logger.info("the url is  + ./hrms.htm?actionFlag=approvePayBill&month=" + report.getParameterValue("Month") + "&year=" + report.getParameterValue("Year")+"&category=" + report.getParameterValue("Category")+"&Grade=" + report.getParameterValue("Grade")+"&dsgnId=" + dsgnIds+"&subHeadId=" + subHeadId+"&billNo="+tmpBillNo);

				logger.info("isBack is "  + isBack);

				URLStyleVO[1] = new StyleVO();
				URLStyleVO[1].setStyleId(IReportConstants.ROWS_PER_PAGE);
				URLStyleVO[1].setStyleValue(pagesize+"");

				URLStyleVO[2] = new StyleVO();
				URLStyleVO[2].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_GROUPBY);
				URLStyleVO[2].setStyleValue("yes");

				URLStyleVO[3] = new StyleVO();
				URLStyleVO[3].setStyleId(IReportConstants.DISPLAY_SUB_REPORTS_IN_JSP);
				URLStyleVO[3].setStyleValue("NO");

				URLStyleVO[5] = new StyleVO();
				URLStyleVO[5].setStyleId(IReportConstants.REPORT_PAGE_BACK_BTN);

				if(report.getReportCode().equals("2500002"))
					URLStyleVO[5].setStyleValue("yes");
				else
					URLStyleVO[5].setStyleValue("no");

				//URLStyleVO[4].setStyleValue("Approve");
				logger.info("isBack is " + isBack);


				report.setStyleList(URLStyleVO);
				//ended by Ankit Bhatt


				StyleVO pageBreakVO=new StyleVO();
				
				
				
				
				report.setReportColumns(rptCol);
				report.initializeDynamicTreeModel();
				report.initializeTreeModel(); 

				return DataList;
			}


			if(report.getReportCode().equals("14") || report.getReportCode().equals("2500014"))
			{

				/*              //for report footer
              ReportAttributeVO ravo = new ReportAttributeVO();

	      	  ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
	      	  ravo.setLocation(IReportConstants.LOCATION_FOOTER);
	      	  ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
	      	  String DeptName=locationNameincaps;
	      	  ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

              //add the attribute to the report instance
      		  report.addReportAttributeItem(ravo);*/
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
				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
				tabularData.addStyle(IReportConstants.BORDER, "No"); 

				report.setGrandTotalTemplate(tabularData);
				report.setGroupByTotalTemplate(tabularData);
				//
				return getBillSubReportData(report,criteria);	              

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

		}
		return DataList;
	}
	public ArrayList getBillSubReportData(ReportVO report,Object criteria) throws SQLException, ParseException
	{
		ArrayList DataList=new ArrayList();

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map requestKeys1 = (Map)((Map) criteria).get(IReportConstants.SESSION_KEYS);

		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");
		logger.info("serviceMap"+serviceMap);
		logger.info("requestKeys1"+requestKeys1);
		ReportData reportData=null;

		/*
		 * reportData2 - This one's for Oracle (i.e GAD) where paybill report's report-code is eq to 2
		 * reportData2500002 - This one's for MySQL on HOME side, where paybill report's report-code is eq to 2500002
		 */
		if((ReportData)requestKeys1.get("reportData2")!=null)
			reportData = (ReportData)requestKeys1.get("reportData2");
		else 
			reportData = (ReportData)requestKeys1.get("reportData2500002");

		logger.info("reportData========>"+reportData.toString());
		CmnLocationMst locationVO =(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName =locationVO.getLocName();
		long locationId = locationVO.getLocId();

		ReportColumnVO[] rptCol = report.getReportColumns();  
		//Ended By Paurav

		String Print="";
		String deptNo="";
		Print=CheckIfNull(report.getParameterValue( "Print" )); 

		deptNo = CheckIfNull(report.getParameterValue("Department"));

		if(!deptNo.equals("")&&!deptNo.equals("-1")&&!deptNo.trim().equalsIgnoreCase(""))
		{
			logger.info("From outer==========>"+deptNo);
			locationId=Long.parseLong(deptNo);	
		}



		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
		String sqlQuery ="";	            
		//Changed By Paurav. mpg.PAYBILL_COL added by Paurav for taking paybill column names

		sqlQuery = "select mpg.column_no colNo,mpg.viewflag, mpg.COLUMN_ORDER_NUMBER officer,r.edp_code edp_code, r.edp_short_name sname,mpg.PAYBILL_COL from rlt_edp_code_mpg mpg,RLT_BILL_TYPE_EDP r where r.type_edp_id=mpg.type_edp_id  ";
		if(locationId!=parentlocationId)
			sqlQuery+= "and mpg.loc_id = "+locationId;
		sqlQuery+= " order by mpg.column_no,mpg.COLUMN_ORDER_NUMBER";
		logger.info("the query for the column is "+sqlQuery);

		Connection lCon = DBConnection.getConnection(  );
		Statement lStmt = lCon.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

		lRs1 = lStmt.executeQuery(sqlQuery);
		ArrayList rowList=new ArrayList();
		ArrayList headerList=new ArrayList();

		StyleVO[] colorStyleVO = new StyleVO[1];
		colorStyleVO[0] = new StyleVO();
		colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
		colorStyleVO[0].setStyleValue("black");


		colorStyleVO[0].setStyleValue("black");
		StyledData dataStyle = new StyledData();
		dataStyle.setData("Sr. No.");                                    
		dataStyle.setStyles(colorStyleVO);                  
		headerList.add(dataStyle);
		dataStyle = new StyledData();
		String summaryPageNo="   Summary Page No.   ";
		String headerspace=" ";
		dataStyle.setData("Pay Scale &"+System.getProperty("line.separator")+"Employee Name"+System.getProperty("line.separator")+"OR"+System.getProperty("line.separator")+summaryPageNo.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+ headerspace.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")+ headerspace.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")+ headerspace.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator"));                                    
		dataStyle.setStyles(colorStyleVO);                  
		headerList.add(dataStyle);

		//Added by Paurav to set column header dynamically
		rptCol[0].setColumnHeader("Sr. No."); 
		rptCol[1].setColumnHeader("Pay Scale &"+System.getProperty("line.separator")+"Employee Name"+System.getProperty("line.separator")+"OR"+System.getProperty("line.separator")+summaryPageNo.replaceAll(" ",constantsBundle.getString("blankSpace")));
		//Ended By Paurav

		//rowList.add("Psr. No.");
		//rowList.add("Pay Scale"+System.getProperty("line.separator")+"Emp Code"+System.getProperty("line.separator")+"Emp Name.");
		int prevCol=2;
		int curCol;
		int i=2;

		Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);






		ArrayList dataList=new ArrayList();


		int count=1;
		long empId=0;

		int viewflag=0;
		while(lRs1.next())
		{
			StringBuffer sbVal=new StringBuffer(  );
			curCol=Integer.parseInt(lRs1.getString("colNo"));

			sbVal.append(lRs1.getString("edp_code")+System.getProperty("line.separator")+lRs1.getString("sname"));
			//rowList.add(lRs1.getString("edp_code")+System.getProperty("line.separator")+lRs1.getString("sname"));
			prevCol=curCol;
			while(lRs1.next())
			{	
				curCol=Integer.parseInt(lRs1.getString("colNo"));
				if(curCol ==prevCol)
				{
					viewflag=0;	            		
					logger.info("inside if and prev value is "+prevCol+" and cur value is"+curCol+" Pay Bill Col is " + lRs1.getString("PAYBILL_COL"));

					viewflag=Integer.parseInt(lRs1.getString("viewflag"));
					if(viewflag==0)
						sbVal.append(System.getProperty("line.separator")+lRs1.getString("edp_code")+System.getProperty("line.separator")+lRs1.getString("sname"));
					else if(viewflag==1)
						sbVal.append(System.getProperty("line.separator")+lRs1.getString("edp_code"));
					else if(viewflag==2)
						sbVal.append(System.getProperty("line.separator")+lRs1.getString("sname"));
					else if(viewflag==3)
						sbVal.append(System.getProperty("line.separator"));

					prevCol=curCol;
				}
				else
				{
					lRs1.previous();
					break;
				}
			}
			dataStyle = new StyledData();
			dataStyle.setData(sbVal.toString());                                    
			dataStyle.setStyles(colorStyleVO);                  
			headerList.add(dataStyle);
			//rowList.add(sbVal.toString());

			//Added by Paurav to dynamically set Column Header
			rptCol[i].setColumnHeader(sbVal.toString());
			//Ended By Paurav
			i++; 
		}
		headerList.add(" ");
		headerList.add(" page");
		logger.info("*******test paybill dao***********1");
		rowList=new ArrayList();
		int cnt=0;
		int displayCnt=1;
		int pagecnt=1;


		colorStyleVO[0].setStyleValue("black");
		dataStyle = new StyledData();



		StyleVO[] URLStyleVO=null;
		URLStyleVO = new StyleVO[3];


		URLStyleVO[0] = new StyleVO();
		URLStyleVO[0].setStyleId(IReportConstants.ROWS_PER_PAGE);
		URLStyleVO[0].setStyleValue(pagesize+"");

		URLStyleVO[1] = new StyleVO();
		URLStyleVO[1].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_GROUPBY);
		URLStyleVO[1].setStyleValue("yes");

		URLStyleVO[2] = new StyleVO();
		URLStyleVO[2].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
		URLStyleVO[2].setStyleValue("yes");

		// hide allowances for sub report 

		for(int j=1;j<=18;j++)
			rptCol[j].setHidden("y");

		report.setStyleList(URLStyleVO);

		report.setReportColumns(rptCol);
		report.initializeDynamicTreeModel();
		report.initializeTreeModel(); 

		DataList=(ArrayList)(ArrayList)reportData.getRows();

		return DataList;
	}


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

		if( report.getReportCode().equals("2500002") || report.getReportCode().equals("2"))
		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
		}

		return report;
	}

	public ReportVO exportReport(ReportVO reportVO, Object arg1, ReportEvent event) {
		ReportColumnVO[] rptCol = reportVO.getReportColumns();  
		String Print=CheckIfNull(reportVO.getParameterValue( "Print" ));

		StyleVO[] URLStyleVO=null;

		URLStyleVO = reportVO.getStyleList();

		// Shoe the sub report in print
		URLStyleVO[3].setStyleId(IReportConstants.DISPLAY_SUB_REPORTS_IN_JSP);
		URLStyleVO[3].setStyleValue("YES");


		reportVO.setStyleList(URLStyleVO);

		//Always hide deductions
		{
			for(int j=19;j<=35;j++)
				rptCol[j].setHidden("y");

		}
		reportVO.setReportName("");
		reportVO.setReportColumns(rptCol);
		reportVO.initializeDynamicTreeModel();
		reportVO.initializeTreeModel(); 

		reportVO.setExportFormat(event.BEFORE_PRINT);
		return reportVO;
	}


}
/* hql  old query before vacant post funda in payroll system        
 *        String query = " select dtl.hrEisEmpMst.empId ,";//0
//query+="  dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName ,";//1
query+= " pst.orgDesignationMst.dsgnShrtName , ";

//query+="  dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId ,dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName, ";//2,3
query+= " pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, " ;

query+="  dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";//4
query+="  dtl.otherCurrentBasic ,";//5

//query+="  dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt || '-' || dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt || '-' ||dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt ,";//6
//query+= " case when dtl.hrEisEmpMst.empType <> 300018 and dtl.hrEisEmpMst.empType <> 300225 then ";
query+="  scale.scaleStartAmt || '-' || scale.scaleIncrAmt || '-' ||scale.scaleEndAmt || '-' ||scale.scaleHigherIncrAmt || '-' ||scale.scaleHigherEndAmt, ";//6
// query+=" else dtl.otherCurrentBasic end, ";
//query+= " ' ', ";

query+="  sum(pay.allow0101),   ";//PER_PAY 7
query+="  sum(pay.allow0102),   ";//SPLPAY 8 
query+="  sum(pay.basic0101),   ";//po 9
query+="  sum(pay.basic0102),   ";//PE 10 
query+="  sum(pay.ls),   "; //Ls 11
query+="  sum(pay.le),   ";////////LE 12
query+="  sum(pay.allow0119),   ";//D_PAY 13
query+="  sum(pay.allow0103),   ";//DA 14
query+="  sum(pay.allow0110),   ";//HRA 15
query+="  sum(pay.allow0105),   ";//LTC 16
query+="  sum(pay.allow0111),   ";//CLA 17
query+="  sum(pay.allow0104),   ";//OTHER_ALLOW 18
query+="  sum(pay.allow0107),   ";//MA 19
query+="  sum(pay.allow0108),   ";//bonus 20
query+="  sum(pay.allow1301),   ";//WA 21
query+="  sum(pay.allow5006),   ";//OTHER_CHRGS 22
query+="  sum(pay.allow0113),   ";//TRANS_ALL 23
query+="  sum(pay.adv5701),   ";//FES_ADV 24
query+="  sum(pay.adv5801),   ";//FOOD_ADV 25
query+="  sum(pay.adv0101),   ";//PAY_RECOVER 26
query+="  sum(pay.grossAmt),   ";// gross amt 27
query+="  sum(pay.slo),   ";//slo 28
query+="  sum(pay.it),   ";//it 29
query+="  sum(pay.surcharge),   ";//surcharge 30
query+="  sum(pay.deduc9550),   ";//HRR 31
query+="  sum(pay.deduc9560),   ";//RENT_B 32
query+="  sum(pay.deduc9530),   ";//PLI 33
query+="  sum(pay.deduc9540),   ";//BLI 34
query+="  sum(pay.deduc9570),   ";//PT 35
query+="  sum(pay.deduc9580),   ";//SIS_GIS_1979 36
query+="  sum(pay.deduc9581),   ";//SIS_IF_1981 37
query+="  sum(pay.deduc9582),   ";//SIS_SF_1981 38
query+="  sum(pay.deduc9583),   ";//AIS_IF_1981 39
query+="  sum(pay.deduc9584),   ";//AIS_SF_1981 40
query+="  sum(pay.deduc9670),   ";//GPF_C 41
query+="  sum(pay.adv9670),   ";//GPF_ADV 42 
query+="  sum(pay.loan9592),   ";//CAR_SCT_MOPED_ADV 43
query+="  sum(pay.loan9740),   ";//OCA_CYCLE_ADV 44
query+="  sum(pay.loan9591),   ";//HBA 45
query+="  sum(pay.loan9720),   ";//FAN_ADV 46
query+="  sum(pay.loan9780),   ";//JEEP_R 47
query+="  sum(pay.deduc9910),   ";//MISC_RECOV 48
query+="  sum(pay.deduc9531),   ";///GPF_IV	 49           
query+="  sum(pay.totalDed),   ";//TOTAL_DED 50
query+="  sum(pay.netTotal)   ";//NET_TOTAL 51

query+="  ,sum(pay.allow0120)   ";//DP_GAZZETED 52 
query+="  ,dtl.phyChallenged   ";//phyChallenged 53
query+="  ,sum(pay.loanInt9592)   ";//CAR_SCT_MOPED_int 54
query+="  ,sum(pay.loanInt9740)   ";//OCA_CYCLE_Int 55
query+="  ,sum(pay.loanInt9591)   ";//HBA_INT 56
query+="  ,sum(pay.loanInt9720)   ";//FAN_int 57
query+="  ,(select gpfDtls.gpfAccNo from HrGpfBalanceDtls gpfDtls where gpfDtls.userId = dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId )   ";//gpfAccNo 58
query+="  ,sum(pay.advIV9670)   ";//advIV9670 59

//Added by Paurav for getting Order Name to which employee belongs
if(subHeadId != null && !subHeadId.equals(""))
{
	query+="  ,(select ordmst.orderName ";
    query+="   from HrPayOrderMst ordmst ";
    query+="  where ordmst.orderId = ORDHD.orderId)  ";//ORDER_NAME  //wrong 58
}


//ORDHD.SUBHEAD_ID = '0' AND
query+=" from HrPayPaybill pay, ";

//Added By Paurav for joining with IFMS Bill Register
//query+=" PaybillBillregMpg bill, ";
//Ended By Paurav

query+=" HrEisOtherDtls as dtl ";

//Added By Paurav for outer join
query+= " left outer join dtl.HrEisSgdMpg as sgd ";
query += " left outer join sgd.HrEisGdMpg as gd ";
query += "left outer join sgd.HrEisScaleMst scale, ";
//Ended By Paurav

//query+=" ,HrGpfBalanceDtls gpfDtls";


//Added by Paurav for generating report head wise
//if(subHeadId != null && !subHeadId.equals(""))
//{
    query+=" OrgUserpostRlt           USRPST, ";
	query+=" HrPayOrderHeadMpg ORDHD, ";
	query+=" HrPayOrderHeadPostMpg ORDPST, ";
	//query+=" PaybillHeadMpg pbhd, ";
	query+=" OrgPostDetailsRlt pst ";
//}    
//Ended By Paurav for head wise report



query+=" where ";     

//added By Paurav for displaying paybill records of particular paybill
if(billNo!=null && !billNo.equals(""))
{
	query += " pay.paybillGrpId in (select bill.hrPayPaybill.paybillGrpId from PaybillBillregMpg bill where bill.trnBillRegister.billNo = "+ billNo + ") and ";
}
//Ended By Paurav	

query+="  pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId ";

if(!empid.equals("")&&!empid.equals("-1"))            	
	query+=" and dtl.hrEisEmpMst.empId = '"+empid+"'";


    if(category.equals("AIS"))
    Grade=AISGradeCode+"";
    else if(category.equals("Gadgeted"))
        Grade=GradeCode1+"','"+GradeCode2;
    else if(category.equals("Non-Gadgeted"))
        Grade=GradeCode3+"','"+GradeCode4;

    if(!Grade.equals("")&&!Grade.equals("-1")&&!category.equals("Custom"))            	
	query+=" and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ('"+Grade+"')";

    if(!Scale.equals("")&&!Scale.equals("-1"))            	
	query+=" and dtl.HrEisSgdMpg.HrEisScaleMst.scaleId = '"+Scale+"'";

    if(bFlag)
    {

    	//query+=" and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId in ( ";
    	query+=" and pst.orgDesignationMst.dsgnId in ( ";
      int j =0;
      for(j=0;j<lstrDesgnIdlst.length;j++)
      {
          if(!lstrDesgnIdlst[j].equalsIgnoreCase("-1"))
          {
        	  if(j==lstrDesgnIdlst.length-1){
        		  query+="'"+((String)lstrDesgnIdlst[j]).trim()+"' ";
        		  dsgnIds += ((String)lstrDesgnIdlst[j]).trim();
        	  }
        	  else{
        		  query+="'"+((String)lstrDesgnIdlst[j]).trim()+"', ";
        		  dsgnIds += ((String)lstrDesgnIdlst[j]).trim() + ",";
        	  }

          }
      }
      //query+="'"+((String)lstrDesgnIdlst[j]).trim()+"' ";
      query+=" )  ";
    }		            
    if(!Designation.equals("")&&!Designation.equals("-1"))            	
	query+=" and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId = '"+Designation+"'";

    //Added By Paurav
    //query += " and pay.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId ";
    //query += " and pbhd.sgvaBudsubhdMst.budsubhdId = ORDHD.subheadId ";
    query += " and ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where pay.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )";
    query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
    query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
    query += " and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
    query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
    //Ended by Paurav

    query += " and USRPST.activateFlag=1 ";

    if(!month.equals("")&&!month.equals("-1"))            	
  		query+=" and pay.month='"+month+"'";

    if(!year.equals("")&&!year.equals("-1"))            	
  		query+=" and pay.year= '"+year+"'";

    //query+="  and gpfDtls.userId = dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
//  Added by Paurav for generating Head Wise report
    if(subHeadId != null && !subHeadId.equals(""))
    {
        query+=" and  dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId = USRPST.orgUserMst.userId  ";
    	query+=" and ORDHD.subheadId="+subHeadId+" ";
        //query+=" ORDPST.orderHeadId = ORDHD.orderHeadId and ";
        //query+=" USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
    }
    //Ended By Paurav

    query+=" group by ";
    query+=" dtl.hrEisEmpMst.empId,  ";
    query+=" dtl.hrEisEmpMst.empType,  ";
    //query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId,  ";
    query+=" pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, ";
    //query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName,  ";pst.orgDesignationMst.dsgnShrtName
    query+=" pst.orgDesignationMst.dsgnShrtName, ";
    //query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId ,dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName,  ";
    query+=" pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, ";
    query+=" dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
    query+=" dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
    query+=" dtl.hrEisEmpMst.orgEmpMst.empLname,  ";
    query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt,  ";
    query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt,  ";
    query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt,dtl.phyChallenged,dtl.otherCurrentBasic  ";
    query+=" scale.scaleStartAmt,scale.scaleHigherIncrAmt,scale.scaleHigherEndAmt,  ";
    query+=" scale.scaleIncrAmt,  ";
    query+=" scale.scaleEndAmt,dtl.phyChallenged,dtl.otherCurrentBasic  ";
    //query+=" ,gpfDtls.gpfAccNo ";
    query+=" ,dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
    if(subHeadId != null && !subHeadId.equals(""))
    {
    	query+=",ORDHD.orderId,ORDPST.orderHeadId ";
    }		            
    query+=" order by ";

    if(subHeadId != null && !subHeadId.equals(""))
    {
    	query+="ORDHD.orderId,";
    }		



//query+="dtl.hrEisEmpMst.empType,dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId,dtl.hrEisEmpMst.empId ";
query+="dtl.hrEisEmpMst.empType,pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.empId ";
 */	             
