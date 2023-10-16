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

public class PayrollOtherReportsDAO extends DefaultReportDataFinder 
implements ReportDataFinder
{
	ResourceBundle locStrsBundle;
	private static Logger logger = Logger.getLogger(PayrollOtherReportsDAO.class );



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


	String portType="";
	String BillNo="";	         
	String desigName="";
	String deptName="";
	String cityName="";
	String cardexCode="";
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		String langName=report.getLangId();

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
		// Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
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
 	         styleVOPgBrk[1].setStyleValue("no");

 	         report.addReportStyles(styleVOPgBrk);


		//Ended
		try   
		{
			lCon = DBConnection.getConnection(  );
			lStmt = lCon.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			logger.info("getReportCode"+report.getReportCode());
			dataStyle = new StyledData();

			OrgDdoMst orgDdo = new OrgDdoMst();
			ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");	
			PayBillDAO billDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			Object[] objArry = null;
			List lstSignInfo = new ArrayList();
		/*	lstSignInfo = billDAO.getReportSignature(locationId);
			if(lstSignInfo.get(0)!=null)
			{

				objArry =(Object[]) lstSignInfo.get(0);
				desigName=objArry[0].toString();
				deptName=objArry[1].toString();
				cardexCode=objArry[2].toString();
				cityName=objArry[3].toString();            		
			}*/
			
			
			lstSignInfo = billDAO.getReportSignature(locationId);
			if(lstSignInfo.get(0)!=null)
			{

				objArry =(Object[]) lstSignInfo.get(0);
				desigName=objArry[0].toString();
				deptName=objArry[1].toString();
				cardexCode=objArry[2].toString();
				cityName=objArry[3].toString();            		
			}
			
			
			

			if(report.getReportCode().equals("35") || report.getReportCode().equals("2500035"))
			{

				//for report footer
				/*              ReportAttributeVO ravo = new ReportAttributeVO();

	      	  ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
	      	  ravo.setLocation(IReportConstants.LOCATION_FOOTER);
	      	  ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
	      	  String DeptName=locationNameincaps;
	      	  ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

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
				return getCPFData(report,criteria);	              
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
	public ArrayList getCPFData(ReportVO report,Object criteria		 )
	{
		//ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		
		
		ArrayList DataList=new ArrayList();
		StringBuffer lsb = new StringBuffer(  );      
		String Department="";
		String Grade="";
		String Scale="";
		String Designation="";
		String month="";
		String year="";
		String GroupBy="";
		String fname = "";
		String lname = "";
		String empid="";
		String portType="";
		empid=CheckIfNull(report.getParameterValue( "employeeName" ));
		fname=CheckIfNull(report.getParameterValue("FName"));
		lname=CheckIfNull(report.getParameterValue("LName"));
		Grade=CheckIfNull(report.getParameterValue( "Grade" ));
		Scale=CheckIfNull(report.getParameterValue( "Scale" ));
		Designation=CheckIfNull(report.getParameterValue( "Designation" ));
		month=CheckIfNull(report.getParameterValue( "Month" ));
		year=CheckIfNull(report.getParameterValue( "Year" ));
		GroupBy=CheckIfNull(report.getParameterValue( "Group By" ));
		Department=CheckIfNull(report.getParameterValue( "Department" ));
		
		//added by ravysh
		String billType=CheckIfNull(report.getParameterValue( "billTypePara" ));
		//if(billType.equals("") || billType.equals(" ") || billType.equals("-1"))
			//billType = resourceBundle.getString("paybillTypeId");
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");

		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		ServiceLocator serv1 = (ServiceLocator) requestKeys.get("serviceLocator");

		PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv1.getSessionFactory());
		boolean isBillDefined = paybillDAO.isBillsDefined(locationId);
		String locationNameincaps=locationName.toUpperCase();  
		if(Department.equals("")||Department.equals("-1"))
			Department=	locationId+"";

		String BillNoinner="";//GAD specific
		BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
		StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
		int l=0;
		String subheadCode="";
		String classIds="";
		String desgnIds="";
		String BillNo="";
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

		cal = Calendar.getInstance();cal.set(Calendar.YEAR, 2005);
		cal.set(Calendar.MONTH,(4-1));cal.set(Calendar.DAY_OF_MONTH, 1);
		String CPFDate  = sdfObj.format(cal.getTime());		


		boolean flag=true;
		Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);

		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");

		SessionFactory sessionFactory = serviceLocator.getSessionFactory();
		Session session= sessionFactory.getCurrentSession();	           

		Session hibSession = sessionFactory.getCurrentSession();

		GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(SgvaBudsubhdMst.class);
		ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
		genDAO.setSessionFactory(serv.getSessionFactory());
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 

		String langName=report.getLangId();
		CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.getLangVOByShortName(langName);  
		long langId = cmnLanguageMst.getLangId();
		PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		SgvcFinYearMst finYrMst = payDAO.getFinYrInfo(startMonthDate, langId);
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

		sdfObj = new SimpleDateFormat("MMM");
		String Month = sdfObj.format(startMonthDate);

		//String locationNameincaps=locationName.toUpperCase();
		String DeptName=locationNameincaps;

		//  Added By  Maruthi for Back button issue.
		ArrayList styleList = new ArrayList();
		ArrayList stData = new ArrayList();
		if( BillNo != null && BillNo != "" )
		{
			String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule  OF JEEP/CAR RENT Deductions for the month of "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
			stData.add(new StyledData (deptHeader,headerStyleVo)); 
			styleList.add(stData);				 
		}
		else
		{

			String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule  OF JEEP/CAR RENT Deductions for the month of "+Month+". "+year;
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
		//Ended By Maruthi.

		//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule  OF JEEP/CAR RENT Deductions for the month of "+Month+". "+year);  
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


		lsb.append("  select dtl.hrEisEmpMst.orgEmpMst.empId , ");
		//lsb.append("  pst.orgDesignationMst.dsgnShrtName ,  ");
		lsb.append("  case when max(dsgnMst.dsgnShrtName) is null then pst.orgDesignationMst.dsgnShrtName else max(dsgnMst.dsgnShrtName) end , ");
		lsb.append("  pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName,  ");

		lsb.append(" dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname ,  ");
		lsb.append("  dtl.otherCurrentBasic , ");
		lsb.append("   scale.scaleStartAmt || '-' || scale.scaleIncrAmt || '-' ||scale.scaleEndAmt || '-' ||scale.scaleHigherIncrAmt || '-' ||scale.scaleHigherEndAmt,  ");

		lsb.append(" sum(pay.allow0119+pay.allow0120), ");//DP
		lsb.append(" sum(pay.allow0103), ");//DA
		lsb.append(" sum(pay.deduc9780) ");//CPF
		//GPF ACC NO
		//lsb.append("  ,(select gpfAccNo from HrGpfBalanceDtls gpfB where gpfB.userId=USRPST.orgUserMst.userId ) ");
		lsb.append("  ,(select gpfAccNo from HrPayGpfBalanceDtls gpfB where gpfB.userId=USRPST.orgUserMst.userId ) ");
		lsb.append("  from HrPayPaybill pay,  ");
		/*//by rahul w.r.t head change
		if(currentMonthBill!=1)
		{
			lsb.append(" PaybillHeadMpg bhm, ");
		}*/
		//by rahul
		lsb.append("  HrEisOtherDtlsHst as dtl  ");
		lsb.append("  left outer join dtl.hrEisSgdMpg as sgd  ");
		lsb.append("  left outer join sgd.hrEisGdMpg as gd  ");
		lsb.append(" left outer join sgd.hrEisScaleMst scale  ");
		lsb.append(" left outer join  gd.orgDesignationMst as dsgnMst , ");
		
		lsb.append("  OrgUserpostRlt           USRPST,  ");
		//by rahul
		/*lsb.append("  HrPayOrderHeadMpgHst ORDHD,  ");
            lsb.append("  HrPayOrderHeadPostMpgHst ORDPST,  ");*/
		/*if(currentMonthBill!=1)
		{
			lsb.append(" HrPayOrderHeadMpgHst ORDHD, ");
			lsb.append(" HrPayOrderHeadPostMpgHst ORDPST, ");
		}
		else*/
		//{
		lsb.append(" HrPayOrderHeadMpg ORDHD, ");
		lsb.append(" HrPayOrderHeadPostMpg ORDPST, ");
		//}
		//ended by rahul
		lsb.append("  OrgPostDetailsRlt pst,  ");
		//lsb.append(" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm ,HrPayOrderSubHeadMpg hposm ");
		lsb.append(" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm  ");

		lsb.append("  where pay.deduc9780 > 0  and  ");
		lsb.append("   dtl.id.trnCounter = pay.otherTrnCntr and  ");
		
		lsb.append("  pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId  ");

		//lsb.append("  and ORDHD.subheadId in (select distinct hposm.element_code from HrPayOrderSubHeadMpg hposm,PaybillHeadMpg bhm ,HrPayPaybill pay where bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId and pay.paybillGrpId = bhm.hrPayPaybill )");
		//by rahul
		//lsb.append("  and ORDPST.orderHeadId = ORDHD.id.orderHeadId  ");
		/*if(currentMonthBill!=1)
		{
			lsb.append(" and ORDPST.orderHeadId = ORDHD.id.orderHeadId ");
		}
		else*/
		
		lsb.append(" and ORDPST.orderHeadId = ORDHD.orderHeadId ");
		
		//ended by rahul
		lsb.append("  and USRPST.orgPostMstByPostId.postId = ORDPST.postId  ");
		lsb.append("  and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId  ");
		lsb.append("  and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId  ");

		lsb.append(" and  (USRPST.endDate is null or  ");
		lsb.append("   (USRPST.endDate>='"+startDate+"' and USRPST.startDate<='"+endDate+"'   )) ");
		lsb.append("   and bhm.approveFlag in (0,1)  ");

		if(!empid.equals("")&&!empid.equals("-1"))            	
			lsb.append(" and dtl.hrEisEmpMst.empId = '"+empid+"' ");

		if(!lname.equals("") && !lname.equals(" "))
		{
			lsb.append("   and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"+lname.toLowerCase()+"%'");
		}

		if(!fname.equals("") && !fname.equals(" "))
		{
			lsb.append("   and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"+fname.toLowerCase()+"%'");
		}


		if(!Grade.equals("")&&!Grade.equals("-1"))  
			lsb.append(" and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  = '"+Grade+"' "); 		            		



		if(!Designation.equals("")&&!Designation.equals("-1"))            	
			lsb.append(" and pst.orgDesignationMst.dsgnId = '"+Designation+"'  ");

		if(!Department.equals("")&&!Department.equals("-1"))
			lsb.append("  and pst.cmnLocationMst.locId="+Department+"  ");
		if(isBillDefined&&!BillNo.equals(""))
		{

			//lsb.append(" and   pay.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId ) and ") ; 
			/*if(currentMonthBill==1)
				lsb.append("   ORDHD.subheadId in (select bill.subheadId from HrPayBillHeadMpg bill where bill.billId ="+BillNo+" ) and  ");*/

			lsb.append(" and  pst.orgPostMst.postId = pay.orgPostMst.postId  "); 
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
				lsb.append("  and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")");
			}
			if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
			{
				lsb.append("  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ");
			}
		}
		if(!month.equals("")&&!month.equals("-1"))            	
			lsb.append(" and bhm.month='"+month+"' ");

		if(!year.equals("")&&!year.equals("-1"))            	
			lsb.append(" and bhm.year= '"+year+"' ");
		//if(currentMonthBill!=1)
		//lsb.append("  and bhm.hrPayPaybill = pay.paybillGrpId and ORDHD.subheadId = hposm.element_code  and hpbsm.billHeadId = bhm.billNo.billHeadId and bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId  ");
		lsb.append("  and bhm.hrPayPaybill = pay.paybillGrpId  and hpbsm.billHeadId = bhm.billNo.billHeadId   ");

		lsb.append(" and bhm.orgGradeMst.gradeId=pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId "); //Date 5-Nov-08 by rahul edited by ravysh on 13-9-09 for suppl. bills

		//added by ravysh
		if(!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
		lsb.append("and bhm.billTypeId.lookupId="+Long.parseLong(billType));
		
		lsb.append("  group by   ");
		lsb.append("  pay.hrEisEmpMst.orgEmpMst.empId,pay.hrEisEmpMst.empId,dtl.hrEisEmpMst.orgEmpMst.empId,    ");

		lsb.append("  pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,  ");
		lsb.append("  pst.orgDesignationMst.dsgnShrtName,  ");
		lsb.append("  pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName,  ");

		lsb.append(" dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,   ");
		lsb.append("  dtl.hrEisEmpMst.orgEmpMst.empMname,   ");
		lsb.append("  dtl.hrEisEmpMst.orgEmpMst.empLname,   ");

		lsb.append("  scale.scaleStartAmt,scale.scaleHigherIncrAmt,scale.scaleHigherEndAmt,   ");
		lsb.append("  scale.scaleIncrAmt,  ");
		lsb.append("  scale.scaleEndAmt,dtl.phyChallenged,dtl.otherCurrentBasic   ");
		lsb.append("  ,USRPST.orgUserMst.userId "); 

		lsb.append("  order by pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.orgEmpMst.empId ");
		
		logger.info("the wuery is ::"+lsb.toString());
		logger.info("***Query for CPF details**"+ lsb.toString());


		Query sqlQuery = hibSession.createQuery( lsb.toString());	      	
		List RowList=sqlQuery.list();
		logger.info("*************************"+RowList.size());
		int cnt=1;
		long empId=0;
		long jeepRentTotal=0;
		logger.info("in line number 627 ::"+RowList.size());
		
		
		if( RowList.size() > 0)
		{
			Iterator itr = RowList.iterator();  
			while (itr.hasNext())
			{
				Object[] rowList = (Object[]) itr.next();
				long EmpId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"0").toString());
				String designation = (String)(rowList[1]!=null?rowList[1]:"");
				long gradeId = Long.parseLong((rowList[2]!=null?rowList[2].toString():"0").toString());
				String Class = (String)(rowList[3]!=null?rowList[3]:"");
				String Name = (String)(rowList[4]!=null?rowList[4]:"");
				long basic = Long.parseLong((rowList[5]!=null?rowList[5]:"0").toString());		            
				String  scale = (rowList[6]!=null?rowList[6]:"").toString();		            
				double DP=Double.parseDouble((rowList[7]!=null?rowList[7]:"0").toString());
				double DA=Double.parseDouble((rowList[8]!=null?rowList[8]:"0").toString());
				double jeepRent=Double.parseDouble((rowList[9]!=null?rowList[9]:"0").toString());
				String gpfAccNo=(String)(rowList[10]!=null?rowList[10]:"");
				long total = basic+Math.round(DP)+Math.round(DA);
				ArrayList row = new ArrayList();
				row.add(cnt);
				row.add(Name);
				row.add(designation);
				row.add(jeepRent);
				row.add(total);
				row.add("");
	
				DataList.add(row);
				cnt++; 
				jeepRentTotal+=jeepRent;
			}
			ArrayList row = new ArrayList();
	
			StyleVO[] boldStyleVO = new StyleVO[1];
			boldStyleVO[0] = new StyleVO();
			boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
	
			row.add("");
			row.add("");
	
			StyledData dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData("TOTAL");  
			row.add(dataStyle1);
	
	
			dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData(Math.round(jeepRentTotal));                  
			row.add(dataStyle1);
	
	
			dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData("");                  
			row.add(dataStyle1);
	
	
	
			DataList.add(row);
		
			//		for last page total
			row = new ArrayList();
			StyleVO[] centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Left"); 
			StyledData dataStyle2 = new StyledData();
			dataStyle2.setStyles(centerboldStyleVO);
			dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(jeepRentTotal))+" only.");
			
			ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
			ReportColumnVO[] rptCol = report.getColumnsToDisplay();
			int totallength=rptCol.length;
			int colspan=rptCol2.length;                
			
			dataStyle2.setColspan(colspan);
			row.add(dataStyle2);
			
			
			
			for(int c=0;c<(totallength-colspan);c++)
				row.add("");
			
			//logger.info(totallength+"******"+row.size()+"******"+colspan);
			DataList.add(row); 
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

		if(  report.getReportCode().equals("35") || report.getReportCode().equals("2500035"))
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
