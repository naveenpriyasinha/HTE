/** 
 * This report is dedicately made for Group Insurace Scheme for IAS grade/class.
 * @author - Varun Sharma
 */


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

public class DeductionAIS extends DefaultReportDataFinder 
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

		logger.info("entered findReportData() method");
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
		baseFont[0] = new StyleVO();
		baseFont[0].setStyleId( IReportConstants.STYLE_FONT_SIZE );
		baseFont[0].setStyleValue( "13" );


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

		ServiceLocator serv1 = (ServiceLocator) requestKeys.get("serviceLocator");

		PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv1.getSessionFactory());
		boolean isBillDefined = paybillDAO.isBillsDefined(locationId);

		//Added by Mrugesh/Samir
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
			logger.info("ENTERED TYY-CATCH BLOCK.. CREATING DB.CONNECTION");
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
			}//end if

			if( report.getReportCode().equals("37") || report.getReportCode().equals("2500037"))
			{
				String fname = CheckIfNull(report.getParameterValue("FName"));
				String lname = CheckIfNull(report.getParameterValue("LName"));


				// hardcoded
				long AISGradeCode=Long.parseLong(constantsBundle.getString("AISGradeCode"));
				//StringBuffer lsb = new StringBuffer();      

				String empid = CheckIfNull(report.getParameterValue( "employeeName" ));
				String Department = "";
				String Grade = "";
				String Scale = "";
				String Designation = "";
				String month = "";
				String year = "";
				String GroupBy = "";
				String ReportType = "";
				GroupBy = CheckIfNull(report.getParameterValue( "Group By" ));
				Department = CheckIfNull(report.getParameterValue( "Department" ));
				//added by ravysh
				String billType=CheckIfNull(report.getParameterValue( "billTypePara" ));
				
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

				String showIFSF="";//GAD specific
				showIFSF=CheckIfNull(report.getParameterValue( "Display seperate IF SF" ));
				boolean showIFSFFlag=false;
				if(showIFSF.equals("y"))
					showIFSFFlag=true;
				
				String BillNoinner="";//GAD specific
				BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
				StringTokenizer st1=new StringTokenizer(BillNoinner,"~");

				String subheadCode="";
				String classIds="";
				String desgnIds="";

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
						portType=st1.nextToken();
					else if(l==4)
						BillNo=st1.nextToken();
					l++;
				} 


				String DeptName=locationNameincaps;

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




				// Grade/Class - AIS is been hardcoded here.. 
				Grade	=	"100110015"; //reference: eis_Constants.properties
				/**
				 *  Report type - set to Insurace
				 *  formulae = AIS_SF + AIS_IF
				 *  implement formulae in Query
				 */
				ReportType	=	"Insurance";
				Scale			=	CheckIfNull(report.getParameterValue( "scale" ));
				Designation	=	CheckIfNull(report.getParameterValue( "Designation" ));
				month			=	CheckIfNull(report.getParameterValue( "Month" ));
				year			=	CheckIfNull(report.getParameterValue( "Year" ));
				
				
				logger.info("the selected designation is "+Designation);
				/*	    	    
	            	  logger.info("REPORT CODE = 37");
	            	  logger.info("FName: " + report.getParameterValue("FName"));
	            	  logger.info("LName: " + report.getParameterValue("LName"));
    	              logger.info("Grade: " +Grade);
	    	          logger.info("Scale: " +Scale);
	    	          logger.info("Desig.: " +Designation);
	    	          logger.info("Month: " +month);
	    	          logger.info("Year: " +year);
	    	          logger.info("Bill no inner : " +BillNoinner);
	    	          logger.info("Bill no: " +BillNo);
				 */
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



				GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(SgvaBudsubhdMst.class);
				genDAO.setSessionFactory(serv.getSessionFactory());
				CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
				CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.getLangVOByShortName(langName);  
				long langId = cmnLanguageMst.getLangId();
				logger.info("Lang ID: " + langId);
				PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				SgvcFinYearMst finYrMst = payDAO.getFinYrInfo(startMonthDate, langId);
				logger.info("Final Yr MSt: " + finYrMst);

				logger.info("*******************finYr*************"+finYrMst.getFinYearId());

				sdfObj = new SimpleDateFormat("MMM");
				String Month = sdfObj.format(startMonthDate);

				String deducType = null;
				deducType = "insurence";


				/** 
				 * REPORT HEADER TITLE
				 *  
				 **/

				//Added By Mrugesh for back button issue

				//ArrayList headList = new ArrayList();
				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();
				//Ended
				logger.info("REPORT GRADE=AIS....SETTING REPORT NAME TO ITS HEADING..");

				//Added By Maruthi for back button issue.
				if( BillNo != null && BillNo != "" )
				{

					//headList.add(DeptName+System.getProperty("line.separator")+"Statement showing the Deductions of A.I.S. officer's Group Insurance Scheme for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					//styleList.add(new StyledData(headList));
					String deptHeader=DeptName+System.getProperty("line.separator")+"Statement showing the Deductions of A.I.S. officers' Group Insurance Scheme for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
					stData.add(new StyledData (deptHeader,headerStyleVo));
					styleList.add(stData);
					//report.setReportName(DeptName+System.getProperty("line.separator")+"Statement showing the Deductions of A.I.S. officer's Group Insurance Scheme for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);

				}
				else{

					//report.setReportName(DeptName+System.getProperty("line.separator")+"Statement showing the Deductions of A.I.S. officer's Group Insurance Scheme for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
					String deptHeader=DeptName+System.getProperty("line.separator")+"Statement showing the Deductions of A.I.S. officers' Group Insurance Scheme for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator");
					stData.add(new StyledData (deptHeader,headerStyleVo));
					styleList.add(stData);

				}
				
				if(showIFSFFlag)
				{
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
					ArrayList r1 = new ArrayList();
					r1.add(new StyledData("District : "+cityName,leftHeader));
					styleList.add(r1);
					r1 = new ArrayList();
					r1.add(new StyledData("Drawing Officer : 0 1 2",leftHeader));
					styleList.add(r1);
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
				//Ended By maruthi.

				ReportColumnVO[] rptCol = report.getReportColumns(); 
				ReportParameterVO[] parVO = report.getParameters();
				ReportColumnVO clms[] = report.getLeafColumns();



				//	by manoj for head change
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

				// forming query....

//select  dtl.hrEisEmpMst.orgEmpMst.empId , pst.orgDesignationMst.dsgnShrtName ,  dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname   sum(pay.deduc9583)+ sum(pay.deduc9584)  ,pay.psrNo  from com.tcs.sgv.eis.valueobject.HrPayPaybill pay,  com.tcs.sgv.eis.valueobject.HrEisOtherDtls as dtl  left outer join dtl.hrEisSgdMpg as sgd  left outer join sgd.hrEisGdMpg as gd left outer join sgd.hrEisScaleMst scale,  com.tcs.sgv.ess.valueobject.OrgUserpostRlt           USRPST,  com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt pst  where    pay.orgPostMst.postId=pay.orgPostMst.postId and  pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId  and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId  and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId and  (USRPST.endDate is null or   (USRPST.endDate>='01-Apr-2009' and USRPST.startDate<='30-Apr-2009'      )) and pst.cmnLocationMst.locId=300022   and (pay.deduc9583!=0 and pay.deduc9584!=0)  and pay.paybillGrpId in (select distinct(phm.hrPayPaybill) from com.tcs.sgv.eis.valueobject.PaybillHeadMpg phm where   phm.month='4' and  phm.year= '2009' and   phm.approveFlag in (0,1)  and phm.billNo.billId in (select hpbsm.billId from com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg hpbsm where    pst.orgPostMst.postId = pay.orgPostMst.postId and    hpbsm.billId=19 and    hpbsm.cmnLocationMst.locId=300022   ))  group by    pay.psrNo,  pay.hrEisEmpMst.orgEmpMst.empId,pay.hrEisEmpMst.empId,dtl.hrEisEmpMst.orgEmpMst.empId,    pst.orgDesignationMst.dsgnShrtName, dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,   dtl.hrEisEmpMst.orgEmpMst.empMname,   dtl.hrEisEmpMst.orgEmpMst.empLname   order by  pay.psrNo

				String query = " select";
				query+= "  pay.hrEisEmpMst.empId ,";
				query+= " dsgn.dsgnShrtName , ";
				query+=" pay.hrEisEmpMst.orgEmpMst.empPrefix||' '|| pay.hrEisEmpMst.orgEmpMst.empFname || ' ' || pay.hrEisEmpMst.orgEmpMst.empMname || ' ' || pay.hrEisEmpMst.orgEmpMst.empLname,  ";
				//query+=" sum(pay.deduc9583)+ sum(pay.deduc9584),(select max(m.reason) from HrMiscRecoverDtls m where  m.hrEisEmpMst.orgEmpMst.empId=pay.hrEisEmpMst.orgEmpMst.empId and m.recoverDate>='"+startDate+"' and m.recoverDate<='"+endDate+"' group by pay.hrEisEmpMst.orgEmpMst.empId  ),sum(pay.surcharge) ";
				query+=" sum(pay.deduc9583),sum(pay.deduc9584)";
				if(isBillDefined)//this will always be at last so that no need to change the sequence if new column comes
				{
					query+="  ,pay.psrNo ";
				}
				query+=" from HrPayPaybill pay, ";
				//	by rahul w.r.t head change
				/*if(currentMonthBill!=1)
				{
					query+=(" PaybillHeadMpg bhm, ");
				}*/
				//by rahul
				query+=" HrEisOtherDtlsHst as dtl ";
				query+= " left outer join dtl.hrEisSgdMpg as sgd ";
				query += " left outer join sgd.hrEisGdMpg as gd ";
				query += " left outer join gd.orgDesignationMst as dsgn ";
				query += "left outer join sgd.hrEisScaleMst scale, ";
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
//select  dtl.hrEisEmpMst.orgEmpMst.empId , pst.orgDesignationMst.dsgnShrtName ,  dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname    ,pay.psrNo  from com.tcs.sgv.eis.valueobject.HrPayPaybill pay,  com.tcs.sgv.eis.valueobject.HrEisOtherDtls as dtl  left outer join dtl.hrEisSgdMpg as sgd  left outer join sgd.hrEisGdMpg as gd left outer join sgd.hrEisScaleMst scale,  com.tcs.sgv.ess.valueobject.OrgUserpostRlt           USRPST,  com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt pst  where    pay.orgPostMst.postId=pay.orgPostMst.postId and  pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId  and USRPST.orgPostMstByPostId.postId = ORDPST.postId  and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId  and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId and  (USRPST.endDate is null or   (USRPST.endDate>='01-Apr-2009' and USRPST.startDate<='30-Apr-2009'      )) and pst.cmnLocationMst.locId=300022   and (pay.deduc9583!=0 and pay.deduc9584!=0)  and pay.paybillGrpId in (select distinct(phm.hrPayPaybill) from com.tcs.sgv.eis.valueobject.PaybillHeadMpg phm where   phm.month='4' and  phm.year= '2009' and   phm.approveFlag in (0,1)  and bhm.billNo.billId in (select hpbsm.billId from com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg hpbsm where  and  pst.orgPostMst.postId = pay.orgPostMst.postId   and hpbsm.billId=19   and hpbsm.cmnLocationMst.locId=300022   ))  group by    pay.psrNo,  pay.hrEisEmpMst.orgEmpMst.empId,pay.hrEisEmpMst.empId,dtl.hrEisEmpMst.orgEmpMst.empId,    pst.orgDesignationMst.dsgnShrtName, dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,   dtl.hrEisEmpMst.orgEmpMst.empMname,   dtl.hrEisEmpMst.orgEmpMst.empLname   order by  pay.psrNo   	
				//query+=" HrPayOrderHeadMpg ORDHD, ";
				//query+=" HrPayOrderHeadPostMpg ORDPST, ";
				
				//ended by rahul
				query+=" OrgPostDetailsRlt pst ";
				//query+=" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm ,HrPayOrderSubHeadMpg hposm";

				if(isBillDefined)
				{
					//	query+="  ,HrPayPsrPostMpg psrmpg "; Updated By urvin shah
				}
				query+=" where  ";
				query+=" dtl.id.trnCounter = pay.otherTrnCntr and ";
				
				if(isBillDefined)
				{
					query+="  pay.orgPostMst.postId=pay.orgPostMst.postId and ";
				}

				query+=" pay.hrEisEmpMst.empId=dtl.hrEisEmpMst.empId ";

				//query += " and ORDHD.subheadId in (select distinct hposm.element_code from HrPayOrderSubHeadMpg hposm,PaybillHeadMpg bhm ,HrPayPaybill pay where bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId and pay.paybillGrpId = bhm.hrPayPaybill )";
				// by rahul
				//query += " and ORDPST.orderHeadId = ORDHD.id.orderHeadId ";
				/*if(currentMonthBill!=1)
				{
					query += " and ORDPST.orderHeadId = ORDHD.id.orderHeadId ";
				}
				else*/
				
				//query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
				
				//ended by rahul
				//query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
				query += " and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
				query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";

				query+="and  (USRPST.endDate is null or ";
				query+="  (USRPST.endDate>='"+startDate+"' and USRPST.startDate<='"+endDate+"'      ))";
				//query+="  and bhm.approveFlag in (0,1) ";

				if(!empid.equals("")&&!empid.equals("-1"))            	
					query+="and pay.hrEisEmpMst.empId = '"+empid+"'";

				// Added by Akshay 
				if(!lname.equals("") && !lname.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"+lname.toLowerCase()+"%'");
				}

				if(!fname.equals("") && !fname.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"+fname.toLowerCase()+"%'");
				}

				/*if(!Grade.equals("")&&!Grade.equals("-1"))  
					query+="and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  = '"+Grade+"'";
*/
				if(!Scale.equals("")&&!Scale.equals("-1"))  
					query+="and dtl.hrEisSgdMpg.hrEisScaleMst.scaleId = '"+Scale+"'";	            		            	

				if(!Designation.equals("")&&!Designation.equals("-1"))            	
					query+="and dsgn.dsgnId = '"+Designation+"'";
				//query+="and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId = '"+Designation+"'";

				if(!Department.equals("")&&!Department.equals("-1"))
					query+=" and pst.cmnLocationMst.locId="+Department+"  ";
				/*if(isBillDefined&&!BillNo.equals(""))
				{
					//query+="  and  pay.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId ) and " ;	 
					if(currentMonthBill==1)
						query+=("   ORDHD.subheadId in (select bill.subheadId from HrPayBillHeadMpg bill where bill.billId ="+BillNo+" ) and  ");
					query+=" and  pst.orgPostMst.postId = pay.orgPostMst.postId  "; 
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

					if(portType != null && !portType.equals("") && !portType.equals("-1"))
					{
						query+="  and pst.orgPostMst.postTypeLookupId in (" +portType+ ") ";
					}
				}*/
				/*if(!month.equals("")&&!month.equals("-1"))            	
					query+=" and bhm.month='"+month+"'";

				if(!year.equals("")&&!year.equals("-1"))            	
					query+="and bhm.year= '"+year+"'";*/

				query+=" and (pay.deduc9583!=0 and pay.deduc9584!=0) ";
				//query+=" and  bhm.billNo.billId=hpbsm.billId and bhm.hrPayPaybill=pay.paybillGrpId ";
				query+=" and pay.paybillGrpId in (select distinct(phm.hrPayPaybill) from PaybillHeadMpg phm where ";
				//added by ravysh for main and supplimentary bill
				if(!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
					query+="  phm.billTypeId.lookupId="+Long.parseLong(billType)+" and ";	
				
				if(!month.equals("")&&!month.equals("-1"))            	
					query+="  phm.month='"+month+"' and ";

				if(!year.equals("")&&!year.equals("-1"))            	
					query+=" phm.year= '"+year+"' and ";
				
				query+="  phm.approveFlag in (0,1) ";
				query+=" and phm.billNo.billId in (select hpbsm.billId from HrPayBillHeadMpg hpbsm where ";	
				
				if(isBillDefined&&!BillNo.equals(""))
				{
					//query+="  and  pay.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId ) and " ;	 
					/*if(currentMonthBill==1)
						query+=("   ORDHD.subheadId in (select bill.subheadId from HrPayBillHeadMpg bill where bill.billId ="+BillNo+" ) and  ");*/
					query+="   pst.orgPostMst.postId = pay.orgPostMst.postId and  "; 
					query+="  hpbsm.billId="+BillNo+" and  ";
				}
				else
				{
					/*if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
					{
						query+="  and  ORDHD.subheadId ="+subheadCode+" ";
					}*/
					/*if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
					{
						query+="  and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")";
					}*/
					if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
					{
						query+="   dsgn.dsgnId in (" +desgnIds+ ") and ";
					}

					/*if(portType != null && !portType.equals("") && !portType.equals("-1"))
					{
						query+="  and pst.orgPostMst.postTypeLookupId in (" +portType+ ") ";
					}*/
				}
				
				if(!Department.equals("")&&!Department.equals("-1"))
					query+="  hpbsm.cmnLocationMst.locId="+Department+"  ";
				query+=" )) ";
				
				//if(currentMonthBill!=1)
						  
				//query+="  and bhm.hrPayPaybill = pay.paybillGrpId and ORDHD.subheadId = hposm.element_code  and hpbsm.billHeadId = bhm.billNo.billHeadId and bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId  ";//finyear change made by chirashree
				//query+= " and bhm.orgGradeMst.gradeId=pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId "; //Date 5-Nov-08 by rahul
				query+=" group by  ";
				if(isBillDefined)
				{
					query+="  pay.psrNo, ";
				}

				query+=" pay.hrEisEmpMst.empId,   ";

				//query+=" pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, ";
				query+=" dsgn.dsgnShrtName, ";
				//query+=" pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, ";

				query+="pay.hrEisEmpMst.orgEmpMst.empPrefix||' '|| pay.hrEisEmpMst.orgEmpMst.empFname,  ";
				query+=" pay.hrEisEmpMst.orgEmpMst.empMname,  ";
				query+=" pay.hrEisEmpMst.orgEmpMst.empLname  ";

				if(isBillDefined)
				{
					query+=" order by  pay.psrNo ";
				}
				else
					query+=" order by pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.orgEmpMst.empId ";     


				//-------- query complete	

				logger.info("***Query for Deduction details**" +query);

				//Execute the query
				Query sqlQuery = hibSession.createQuery(query);	   

				//store the fetched data into dis list - to be iterated ahead..
				List RowList=sqlQuery.list();
				logger.info("*************************"+RowList.size());

				//populate data in this arry list by iterating RowList
				ArrayList dataList = new ArrayList();

				//before iterating, check for data size.. if not found then simply display "No records found"
				if(RowList.size() > 0)
				{
					logger.info("resultset size : " +RowList.size());
					int cnt		= 1;	//sr. no
					long netDeduction = 0; //total amount of deduction ( AISSF + SISSF )
					double gisTotal = 0;   //total amount of deduction ( AISSF + SISSF ), for n employees
					double AISIFTotal = 0;   //total amount of deduction ( AISSF + SISSF ), for n employees
					double AISSFTotal = 0;   //total amount of deduction ( AISSF + SISSF ), for n employees
					Iterator itr = RowList.iterator();  // iterator for iterating the database fetched values
					//start iterating..
					long AISIF = 0;
					long AISSF = 0;
					while (itr.hasNext())
					{
						Object[] rowList = (Object[]) itr.next();

						long EmpId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());

						String designation = (String)(rowList[1]!=null?rowList[1]:"");

						String Name = (String)(rowList[2]!=null?rowList[2]:"");

						//AIS_SF_1980 + AIS_IF_1980 of current iterated employee
						AISIF = Math.round(Double.parseDouble( rowList[3]!= null ? rowList[3].toString():""));
						AISSF = Math.round(Double.parseDouble( rowList[4]!= null ? rowList[4].toString():""));

						netDeduction = AISIF + AISSF ;
						
						String reason = "";//(String)(rowList[4]!=null?rowList[4]:"");

						//Total of all the employees
						gisTotal+=netDeduction;
						AISIFTotal+=AISIF;
						AISSFTotal+=AISSF;

						//list to store data. shall be used to display
						ArrayList row = new ArrayList();

						row.add(cnt);
						row.add(Name);
						row.add(designation);
						row.add(month+"/"+year);
						row.add( netDeduction);
						row.add("8658-Suspense Accounts A.I.S. Insurance Scheme");
						row.add("");
						row.add(reason);

						// These parameters are location specific cash2 
						row.add( "A");
						row.add( AISIF);
						row.add( AISSF);
						row.add( netDeduction);
						
						DataList.add(row);
						cnt++; 

						if( cnt%25==0)
						{
							row=new ArrayList();
							row.add(new PageBreak());
							row.add("Data");
							DataList.add(row); 
						}//end if

					}//end while-loop ( iterating finished )


					//adding total of all employees. Last row to show Total
					ArrayList row = new ArrayList();
					row.add("");//sr no
					row.add("");//designation short name
					dataStyle = new StyledData();
					dataStyle.setStyles(boldStyleVO);
					dataStyle.setData("TOTAL");
					row.add(dataStyle);//empname 
					row.add("");
					dataStyle = new StyledData();
					dataStyle.setStyles(boldStyleVO);
					dataStyle.setData(gisTotal);
					row.add(dataStyle);
					row.add("");
					row.add("");
					row.add("");

					// These parameters are location specific cash2 
					dataStyle = new StyledData();
					dataStyle.setStyles(boldStyleVO);
					dataStyle.setData("");
					row.add(dataStyle);//empname 
					dataStyle = new StyledData();
					dataStyle.setStyles(boldStyleVO);
					dataStyle.setData(AISIFTotal);
					row.add(dataStyle);
					dataStyle = new StyledData();
					dataStyle.setStyles(boldStyleVO);
					dataStyle.setData(AISSFTotal);
					row.add(dataStyle);//empname 
					dataStyle = new StyledData();
					dataStyle.setStyles(boldStyleVO);
					dataStyle.setData(gisTotal);
					row.add(dataStyle);
					
					
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

					dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(gisTotal))+" only.");

					dataStyle2.setColspan(6);
					row1.add(dataStyle2);

					for(int c=0;c<11;c++)
						row1.add("");


					DataList.add(row1);
					
					if(showIFSFFlag)
					{
						for(int i=3;i<8;i++)
						rptCol[i].setHidden("y");
					}
					else
					{
						for(int i=8;i<12;i++)
						rptCol[i].setHidden("y");
					}
					
					report.setReportColumns(rptCol);//report headers
					report.initializeDynamicTreeModel();
					report.initializeTreeModel(); 
					logger.info("**********************"+DataList.size());
					return DataList;

				}
				else{

					stData.add("No Record(s) Found");
					styleList.add(stData);
					tData  = new TabularData(styleList);
					tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
					tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
					tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
					tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
					tData.addStyle(IReportConstants.BORDER, "No");
					tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
					report.setAdditionalHeader(tData);
				}//end if - noOfRecords check
			}//end if - if(reportCode.equals("xx")

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

	}//end method - find report data ()


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

		if( report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("5") || report.getReportCode().equals("2500005") || report.getReportCode().equals("9") || report.getReportCode().equals("2500009")|| report.getReportCode().equals("37") || report.getReportCode().equals("2500037"))
		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			//added by ravysh
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}//end if 

		return report;
	}//end method- getUserReportVO



}//end class
