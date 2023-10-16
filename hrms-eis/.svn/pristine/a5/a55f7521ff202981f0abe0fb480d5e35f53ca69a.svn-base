package com.tcs.sgv.eis.dao;

import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Hashtable;
import org.hibernate.Query;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import org.hibernate.SessionFactory;
import com.tcs.sgv.eis.util.DBConnection;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportParameterVO;

public class ITReportDAO extends DefaultReportDataFinder 
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
		isBillDefined = true; 

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
			String cardexCode="";
			ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");	
			PayBillDAO billDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			Object[] objArry = null;
			List lstSignInfo = new ArrayList();

			if(report.getReportCode().equals("2500982"))
			{

				String fname = CheckIfNull(report.getParameterValue("FName"));
				String lname = CheckIfNull(report.getParameterValue("LName"));

				String billType="";


				billType=CheckIfNull(report.getParameterValue( "billTypePara" ));
				long AISGradeCode=Long.parseLong(constantsBundle.getString("AISGradeCode"));


				String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
				String Department="";
				String Grade="";
				String Scale="";
				String Designation="";
				String month="";
				String year="";
				String ReportType="";
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
				}
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
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

				ArrayList trow6 = new ArrayList();
				trow6.add(" ");
				trow6.add(" ");
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
				Grade=CheckIfNull(report.getParameterValue( "Grade" ));
				Scale=CheckIfNull(report.getParameterValue( "Scale" ));
				Designation=CheckIfNull(report.getParameterValue( "Designation" ));
				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				ReportType="IT";
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
				String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
				String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
				String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
				String subHead = CheckIfNull(report.getParameterValue("subHead"));
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
				logger.info("The report type is------>>>>"+ReportType);

				if(report.getReportCode().equals("110"))
				{
					if(ReportType.equals("")||ReportType.equals("-1")) 
					{
						if( BillNo != null && BillNo != "" )
						{
							String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Total Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
							stData.add(new StyledData (deptHeader,headerStyleVo));
							styleList.add(stData);

						}
						else
						{
							String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Total Deductions for the month of  "+Month+". "+year;
							stData.add(new StyledData (deptHeader,headerStyleVo));
							styleList.add(stData);

						}
						TabularData tData  = new TabularData(styleList);
						tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
						tData.addStyle(IReportConstants.BORDER, "No");
						tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
						report.setAdditionalHeader(tData);
					}
				}
				String deducType = null;
				deducType = constantsBundle.getString("incomeTax"); 

				if(!ReportType.equals("")&&!ReportType.equals("-1"))
				{
					if(ReportType.equals("IT"))
					{
						String TANno= paybillDAO.getTANDtlsbyMonthLocId(Integer.parseInt(month),Integer.parseInt(year),locationId);
						if(!ReportType.equals("")||ReportType.equals("-1")) 
						{
							if( BillNo != null && BillNo != "" )
							{
								String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Income TAX Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+"TAN : "+TANno+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
								stData.add(new StyledData (deptHeader,headerStyleVo));							             
								styleList.add(stData);

							}
							else
							{
								String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Income TAX Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+"TAN : "+TANno+System.getProperty("line.separator")+System.getProperty("line.separator");
								stData.add(new StyledData (deptHeader,headerStyleVo));
								styleList.add(stData);

							}
							TabularData tData  = new TabularData(styleList);
							tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
							tData.addStyle(IReportConstants.BORDER, "No");
							tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
							report.setAdditionalHeader(tData);
						}
					}
					
				}
				ReportColumnVO[] rptCol = report.getReportColumns(); 
				ReportParameterVO[] parVO = report.getParameters();
				ReportColumnVO clms[] = report.getLeafColumns();

				int x=0;
				int y=0;
				x=clms.length;
				y=x;
				int o=0;
				ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
				int colspan=rptCol2.length;
				for(int t =0;t<rptCol2.length;t++)
				{
							if(rptCol2[t].getColumnId()==5||rptCol2[t].getColumnId()==12||rptCol2[t].getColumnId()==23||rptCol2[t].getColumnId()==13||rptCol2[t].getColumnId()==14||rptCol2[t].getColumnId()==15||rptCol2[t].getColumnId()==16||rptCol2[t].getColumnId()==17||rptCol2[t].getColumnId()==18||rptCol2[t].getColumnId()==19)
								colspan--;
				}
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

				String query = " select";
				query+=" (select proof.proofNum from HrEisProofDtl proof where  proof.orgPostMstByUserId.userId = dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId),  ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";
				
				//query+= " pst.orgDesignationMst.dsgnShrtName , ";
				query+= "  case when max(dsgnMst.dsgnShrtName) is null then pst.orgDesignationMst.dsgnShrtName else max(dsgnMst.dsgnShrtName) end , ";
				
				query+=" sum(pay.it), ";
				query+=" sum(pay.surcharge), ";
				query+=" sum(pay.grossAmt+pay.adv7057+pay.adv7058+pay.deduc0101)";

				if(!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{     
					query+=" from HrPayArrearPaybill pay, ";
				}
				else
				{    
					query+=" from HrPayPaybill pay, ";
				}
				query+=" HrEisOtherDtlsHst as dtl ";
				query+= " left outer join dtl.hrEisSgdMpg as sgd ";
				query += " left outer join sgd.hrEisGdMpg as gd ";
				query += " left outer join sgd.hrEisScaleMst scale ";
				query += " left outer join  gd.orgDesignationMst as dsgnMst , ";
				
			//	query+=" OrgUserpostRlt           USRPST, ";
			//	query+=" HrPayOrderHeadMpg ORDHD, ";
			//	query+=" HrPayOrderHeadPostMpg ORDPST, ";
				query+=" OrgPostDetailsRlt pst, ";
				query+=" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm  ";
				query+=" where  ";
				query+="  dtl.id.trnCounter = pay.otherTrnCntr and  ";
				if(!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
				query+="  bhm.billTypeId.lookupId = "+Long.parseLong(billType)+" and  ";
				query+=" pay.hrEisEmpMst.empId=dtl.hrEisEmpMst.empId ";
			/*	query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
				query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
				query += " and USRPST.orgUserMst.userId = dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
				query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
				query+="and  (USRPST.endDate is null or ";
				query+="  (USRPST.endDate>='"+startDate+"' and USRPST.startDate<='"+endDate+"'      ))";*/
				query+="  and bhm.approveFlag in (0,1) ";

				if(!empid.equals("")&&!empid.equals("-1"))            	
					query+="and dtl.hrEisEmpMst.empId = '"+empid+"'";

				if(!lname.equals("") && !lname.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"+lname.toLowerCase()+"%'");
				}

				if(!fname.equals("") && !fname.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"+fname.toLowerCase()+"%'");
				}
				/*if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
				{
					query+=("  and ORDHD.subheadId  = '"+subHeadId+"'");
				}*/

				if(!Grade.equals("")&&!Grade.equals("-1"))  
					query+="and dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  = '"+Grade+"'"; 		            		
				if(!Scale.equals("")&&!Scale.equals("-1"))  
					query+="and dtl.hrEisSgdMpg.hrEisScaleMst.scaleId = '"+Scale+"'";	            		            	

				if(!Designation.equals("")&&!Designation.equals("-1"))            	
					query+="and pst.orgDesignationMst.dsgnId = '"+Designation+"'";

				if(!Department.equals("")&&!Department.equals("-1"))
					query+=" and pst.cmnLocationMst.locId="+Department+"  ";
				if(isBillDefined&&!BillNo.equals(""))
				{
					query+=" and hpbsm.billId="+BillNo+"  ";
				}
				else
				{
					/*if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
					{
						query+="  and  ORDHD.subheadId ="+subheadCode+" ";
					}*/
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
				if(!month.equals("")&&!month.equals("-1"))            	
					query+=" and bhm.month='"+month+"'";

				if(!year.equals("")&&!year.equals("-1"))            	
					query+="and bhm.year= '"+year+"'";

				if(!ReportType.equals("")&&!ReportType.equals("-1"))
				{
					if(ReportType.equals("IT"))
					{
						query+=" and pay.it!=0 ";
					}
				}
				query+="  and bhm.hrPayPaybill = pay.paybillGrpId  and hpbsm.billHeadId = bhm.billNo.billHeadId  ";
				query+= " and bhm.orgGradeMst.gradeId=dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId ";
				query+=" and  pst.orgPostMst.postId = pay.orgPostMst.postId  ";
				query+=" group by  ";
				if(isBillDefined)
				{
					query+="  pay.psrNo, ";
				}
				query+=" dtl.hrEisEmpMst.empId,dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId,   ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, ";
				query+=" pst.orgDesignationMst.dsgnShrtName, ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, ";
				query+="dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.empLname,  ";
				query+=" scale.scaleStartAmt,scale.scaleHigherIncrAmt,scale.scaleHigherEndAmt,  ";
				query+=" scale.scaleIncrAmt,  ";
				query+=" scale.scaleEndAmt,dtl.phyChallenged,dtl.otherCurrentBasic  ";

				if(isBillDefined)
				{
					query+=" order by  pay.psrNo ";
				}
				else
					query+=" order by dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.empId ";
				logger.info("***Query for Deduction details**" +query);

				Query sqlQuery = hibSession.createQuery(query);	      	
				List RowList=sqlQuery.list();
				if(RowList.size()!=0)
				{
					ArrayList dataList=new ArrayList();
				
					logger.info("*************************"+RowList.size());
					int cnt=1;
					Iterator itr = RowList.iterator();  
					double ITTotal=0;
					double surchargeTotal=0;
					double grossAmtTotal=0;
					double nettotalTotal=0;
					
					String panNo="";
					double nettotal = 0;
					while (itr.hasNext())
					{
						Object[] rowList = (Object[]) itr.next();
						//panNo=(rowList[0]!=null?rowList[0]:"").toString();
						panNo=(rowList[0]!=null?rowList[0]:"").toString().replace(" ","").replace("-","");
						String Name = (String)(rowList[1]!=null?rowList[1]:"");
						String designation = (String)(rowList[2]!=null?rowList[2]:"");
						double IT = Double.parseDouble((rowList[3]!=null?rowList[3]:"").toString());		            
						double surcharge = Double.parseDouble((rowList[4]!=null?rowList[4]:"").toString());
						double grossAmt = Double.parseDouble((rowList[5]!=null?rowList[5]:"").toString());		            
						nettotal = surcharge + IT;
						
						logger.info(":::::::adi ReportType:::::"+ReportType);
						logger.info(":::::::adi IT:::::"+IT);

						if((ReportType.equals("IT") && IT!=0)||(!ReportType.equals("IT")))
						{
							logger.info(":::::::3:::::");
						ArrayList row = new ArrayList();

							row.add(cnt);
							row.add(panNo);
							row.add(Name);
							row.add(designation);
							row.add(Math.round(grossAmt));
							row.add(Math.round(IT));
							row.add(Math.round(surcharge));
							row.add(Math.round(nettotal));
				//			row.add((cnt-1)/12+1);
						DataList.add(row);

						ITTotal+=IT;
						surchargeTotal+=surcharge;
						grossAmtTotal+=grossAmt;
						nettotalTotal+=nettotal;
						
						logger.info("net IT             : " + ITTotal);
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
							
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(grossAmtTotal);
							row.add(dataStyle1);
							
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(ITTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(surchargeTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(nettotalTotal);
							row.add(dataStyle1);
							
							DataList.add(row);
							row = new ArrayList();
							row.add(new PageBreak());
							row.add("Data");
							for (int j = 0; j < 10; j++)
								row.add("");
							
							DataList.add(row);
							row = new ArrayList();
							row.add("");
							row.add("");   
							row.add("");
							
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData("C/F");                  
							row.add(dataStyle1);
							
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(grossAmtTotal);//grossAmtTotal
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(ITTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(surchargeTotal);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(nettotalTotal);
							row.add(dataStyle1);
							
							DataList.add(row);
						}
						cnt++; 
					}
					}
					logger.info("net surchargeTotal : " + surchargeTotal);
					logger.info("net grossAmtTotal  : " + grossAmtTotal);
					logger.info("net nettotalTotal  : " + nettotalTotal);
					ArrayList row = new ArrayList();
					row.add("");
					row.add("");
					row.add("");

					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData("Total"); 

					row.add(dataStyle1);
					
					dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(Math.round(grossAmtTotal));
					row.add(dataStyle1);
					
					dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(Math.round(ITTotal));
					row.add(dataStyle1);

					dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(Math.round(surchargeTotal));
					row.add(dataStyle1);

					dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(Math.round(nettotalTotal));
					row.add(dataStyle1);
					
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
						dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(nettotalTotal))+" only.");

					dataStyle2.setColspan(colspan);
					row1.add(dataStyle2);
					for(int c=0;c<10;c++)
						row1.add("");

					DataList.add(row1);
					report.setReportColumns(rptCol);
					report.initializeDynamicTreeModel();
					report.initializeTreeModel(); 
					logger.info("**********************"+DataList.size());
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

		if(  report.getReportCode().equals("2500982") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}
		return report;
	}
}

