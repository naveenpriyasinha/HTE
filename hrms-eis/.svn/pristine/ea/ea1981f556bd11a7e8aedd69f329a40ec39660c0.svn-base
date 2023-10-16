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

public class PayrollReportsDAO extends DefaultReportDataFinder 
implements ReportDataFinder
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

	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
	public final long arrearbillTypeId=Integer.parseInt(resourceBundle.getString("arrearbillTypeId"));


	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		String langName=report.getLangId();
		int finalpagesize=22;
		String locId=report.getLocId(); 
		Connection lCon = null;
		Statement lStmt = null;
		
		DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
		
		String getNVL=sbConf.getNVL();

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");

		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		String locationNameincaps=locationName.toUpperCase();




		//String upperfooter=cityName;
		// lowerfooter=cityName+System.getProperty("line.separator")+cardexCode;
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

			if(report.getReportCode().equals("8") || report.getReportCode().equals("2500008"))
			{

				StringBuffer lsb = new StringBuffer(  );      

				String month="";
				String year="";
				String bank="";
				String Department="";
				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				Department=CheckIfNull(report.getParameterValue( "Department" ));
				//added by ravysh
				String billType=CheckIfNull(report.getParameterValue( "billTypePara" ));
				//for report footer
				//added by samir joshi for bill no wise report
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

				//ended by samir joshi/////
				ReportAttributeVO ravo = new ReportAttributeVO();

				ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
				ravo.setLocation(IReportConstants.LOCATION_FOOTER);
				ravo.setAlignment(IReportConstants.ALIGN_RIGHT);

				String DeptName=locationNameincaps;
				long   ifnototal=0;
				long ifsfnototal=0;
				long totalemp=0;
				//add the attribute to the report instance
				//report.addReportAttributeItem(ravo);

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

				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");

				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				java.util.Date startMonthDate = cal.getTime();
				String startDate  = sdfObj.format(startMonthDate);
				sdfObj = new SimpleDateFormat("MMM");
				String Month = sdfObj.format(startMonthDate);
				// Added by Rajan for check whether bill no is selected or not
				//Added by Maruthi For back buton issue.
				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();
				if( BillNo != null && BillNo != "" )
				{
					//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					String deptHeader=DeptName+System.getProperty("line.separator")+"ANNEXURE 'C' (REFER PARA-2)"+System.getProperty("line.separator")+"Schedule pertaining to the credit Head:8011-Insurance Fund Pension Fund "+System.getProperty("line.separator")+"(Gujarat Goverment Employees Group Insurance Scheme 1981) " +System.getProperty("line.separator")+"for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+"N.B. (In case subscription is remained in arrears the fact should be shown in red ink as a separate footnote)."+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
					stData.add(new StyledData (deptHeader,headerStyleVo));
					styleList.add(stData);

				}
				else
				{
					//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
					String deptHeader=DeptName+System.getProperty("line.separator")+"ANNEXURE 'C' (REFER PARA-2)"+System.getProperty("line.separator")+"Schedule pertaining to the credit Head:8011-Insurance Fund Pension Fund "+System.getProperty("line.separator")+"(Gujarat Goverment Employees Group Insurance Scheme 1981) " +System.getProperty("line.separator")+"for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+"N.B. (In case subscription is remained in arrears the fact should be shown in red ink as a separate footnote).";
					stData.add(new StyledData (deptHeader,headerStyleVo));
					styleList.add(stData);	    				 
				}
				TabularData tData  = new TabularData(styleList);
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");
				//tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);
				//Ended by Maruthi For back buton issue.  

				boolean flag=true;
				Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);

				Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
				ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");

				SessionFactory sessionFactory = serviceLocator.getSessionFactory();
				Session session= sessionFactory.getCurrentSession();	           

				Session hibSession = sessionFactory.getCurrentSession();
				//	  	 	by manoj for head change
				cal = Calendar.getInstance();
				sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				java.util.Date finYrDate = cal.getTime();
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
				java.util.Date endMonthDate = cal.getTime();           


				int currentMonthBill = 0;
				Date currentDate = new Date();

				if(currentDate.after(finYrDate) && currentDate.before(endMonthDate))
					currentMonthBill = 1;
				logger.info("testing for currentMonth "+currentMonthBill+" currentDate "+currentDate+" finYrDate "+finYrDate+" endMonthDate "+endMonthDate);

				//end by manoj for head change
				// For Grp A

				StringBuffer query = new StringBuffer();
				query.append(" select  ");
				query.append(" case when  a.deduc9581!=0 and a.deduc9582=0 then count(*)  end , ");
				query.append(" case when  a.deduc9581!=0 and a.deduc9582!=0 then  count(*)  end ,  ");
				query.append(" case when  a.deduc9581!=0 and a.deduc9582!=0 then sum(a.deduc9581) end ,  ");
				query.append(" case when  a.deduc9582!=0  then sum(a.deduc9582) end ,  ");
				query.append(" case when  a.deduc9581!=0 and a.deduc9582=0 then sum(a.deduc9581) end   ");
				query.append(" from HrPayPaybill a,  ");
				//query.append(" HrEisOtherDtlsHst b ,OrgPostDetailsRlt pt,");
				query.append(" HrEisOtherDtls b ,OrgPostDetailsRlt pt,");
				
				query.append(" HrPayOrderHeadMpg ORDHD, ");
				query.append(" HrPayOrderHeadPostMpg ORDPST, ");
				//query.append(" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm ,HrPayOrderSubHeadMpg hposm ");
				query.append(" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm  ");
				
				//ended by rahul
				query.append(" where ");
				// Added By Urvin.
				//query.append(" b.id.trnCounter in (select max(hst.id.trnCounter) from HrEisOtherDtlsHst hst where hst.hrEisEmpMst.empId=b.hrEisEmpMst.empId and (hst.updatedDate <='31-Mar-2009' or hst.createdDate>='31-Mar-2009') group by hst.hrEisEmpMst.empId) and ");
				// End
				//added by ravysh for main and supplimentary bill
				if(!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
					query.append("   bhm.billTypeId.lookupId="+Long.parseLong(billType)+" and ");	
				
				query.append(" bhm.approveFlag in (0,1)  and a.hrEisEmpMst.orgEmpMst.empId=b.hrEisEmpMst.orgEmpMst.empId ");
				//query.append("  and  ORDHD.subheadId in (select  hposm.element_code from HrPayOrderSubHeadMpg hposm where bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId  )");						
				
				query.append(" and ORDPST.orderHeadId = ORDHD.orderHeadId ");
				
				//ended by rahul
				query.append("  and pt.orgPostMst.postId = ORDPST.postId ");
				query.append("  and a.orgPostMst.postId = ORDPST.postId ");
				if(!month.equals("")&&!month.equals("-1"))            	
					query.append("and  bhm.month='"+month+"'");

				if(!year.equals("")&&!year.equals("-1"))            	
					query.append("and bhm.year= '"+year+"'");

				if(!Department.equals("")&&!Department.equals("-1"))            	
					query.append("and pt.orgPostMst.postId= a.orgPostMst.postId and pt.cmnLocationMst.locId=" + Department + "  ");
				if(isBillDefined&&!BillNo.equals(""))
				{
					query.append(" and   pt.orgPostMst.postId = a.orgPostMst.postId  "); 
					query.append(" and hpbsm.billId="+BillNo+"  ");
				}
				else
				{
					if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
					{
						query.append("  and  ORDHD.subheadId ="+subheadCode+" ");
					}
					if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
					{
						query.append("  and a.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")");
					}
					if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
					{
						query.append("  and pt.orgDesignationMst.dsgnId in (" +desgnIds+ ") ");
					}
				}
				//if(currentMonthBill!=1)
				//query.append("  and bhm.hrPayPaybill = a.paybillGrpId and ORDHD.subheadId = hposm.element_code  and hpbsm.billHeadId = bhm.billNo.billHeadId and bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId  "); //finyrid change done by chirashree
				query.append("  and bhm.hrPayPaybill = a.paybillGrpId and hpbsm.billHeadId = bhm.billNo.billHeadId   "); //finyrid change done by chirashree
				query.append(" and bhm.orgGradeMst.gradeId=a.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId "); //Date 5-Nov-08 by rahul
				
				StringBuffer query1 = new StringBuffer();
				
				//Grouping condition changed from scale TO SIS IF amount
				//query1.append(" and b.hrEisSgdMpg.hrEisScaleMst.scaleStartAmt >= 8000 ");
				query1.append(" and a.deduc9581 = 120 ");
				
				query1.append(" group by a.deduc9581,a.deduc9582  ");


				logger.info("***Query for GIS Report details A**" +query);


				Query sqlQuery = hibSession.createQuery(query.toString()+query1.toString());	      	
				ArrayList dataList=new ArrayList();
				List RowList=sqlQuery.list();
				int cnt=1;
				long empId=0;

				Iterator itr = RowList.iterator();  
				long ifno = 0;
				long ifsfno = 0;
				long ifamt = 0;
				long sfamt = 0;
				long ifamtonly = 0;
				long ifamtTotal=0;
				long sfamtTotal=0;
				long ifamtonlyTotal=0;
				long GrandTotal=0;
				while (itr.hasNext())
				{
					Object[] rowList = (Object[]) itr.next();
					ifno += Long.parseLong((rowList[0]!=null?rowList[0].toString():"0").toString());
					ifsfno += Long.parseLong((rowList[1]!=null?rowList[1].toString():"0").toString());
					ifamt += Math.round(Double.parseDouble((rowList[2]!=null?rowList[2].toString():"0").toString()));
					sfamt += Math.round(Double.parseDouble((rowList[3]!=null?rowList[3].toString():"0").toString()));
					ifamtonly += Math.round(Double.parseDouble((rowList[4]!=null?rowList[4].toString():"0").toString()));

					cnt++; 
				}

				ifnototal=ifnototal+ifno;
				ifsfnototal=ifsfnototal+ifsfno;
				// totalemp+=ifnototal+ifsfnototal;
				ArrayList row = new ArrayList();
				ifamtTotal+=ifamt;
				sfamtTotal+=sfamt;
				ifamtonlyTotal+=ifamtonly;
				GrandTotal+=ifamt+sfamt+ifamtonly;

				row.add("Group A (120-280)");
				row.add(ifno);
				row.add(ifsfno);
				row.add(ifno+ifsfno);
				row.add(Math.round(ifamt));
				row.add(Math.round(sfamt));
				row.add(Math.round(ifamtonly));
				row.add(Math.round(ifamt+sfamt+ifamtonly));
				row.add(" ");
				DataList.add(row);

				// For Grp B

				StringBuffer query2 = new StringBuffer();
				//Grouping condition changed from scale TO SIS IF amount
				//query2.append(" and b.hrEisSgdMpg.hrEisScaleMst.scaleStartAmt between 5500 and 7999   ");
				query2.append(" and  a.deduc9581 = 60  ");
				
				query2.append(" group by a.deduc9581,a.deduc9582  ");


				logger.info("***Query for GIS Repotrt details B**" +query.toString()+query2.toString());


				Query sqlQuery1 = hibSession.createQuery(query.toString()+query2.toString());	      	

				RowList=sqlQuery1.list();
				cnt=1;
				empId=0;

				itr = RowList.iterator();  
				ifno = 0;
				ifsfno = 0;
				ifamt = 0;
				sfamt = 0;
				ifamtonly = 0;
				while (itr.hasNext())
				{
					Object[] rowList = (Object[]) itr.next();
					ifno += Long.parseLong((rowList[0]!=null?rowList[0].toString():"0").toString());
					ifsfno += Long.parseLong((rowList[1]!=null?rowList[1].toString():"0").toString());
					ifamt += Math.round(Double.parseDouble((rowList[2]!=null?rowList[2].toString():"0").toString()));
					sfamt += Math.round(Double.parseDouble((rowList[3]!=null?rowList[3].toString():"0").toString()));
					ifamtonly += Math.round(Double.parseDouble((rowList[4]!=null?rowList[4].toString():"0").toString()));

					cnt++; 
				}
				row = new ArrayList();
				ifnototal=ifnototal+ifno;
				ifsfnototal=ifsfnototal+ifsfno;
				// totalemp+=ifnototal+ifsfnototal;
				ifamtTotal+=ifamt;
				sfamtTotal+=sfamt;
				ifamtonlyTotal+=ifamtonly;
				GrandTotal+=ifamt+sfamt+ifamtonly;


				row.add("Group B (60-140)");
				row.add(ifno);
				row.add(ifsfno);
				row.add(ifno+ifsfno);
				row.add(Math.round(ifamt));
				row.add(Math.round(sfamt));
				row.add(Math.round(ifamtonly));
				row.add(Math.round(ifamt+sfamt+ifamtonly));
				row.add(" ");
				DataList.add(row);

				// For Grp C

				StringBuffer query3 = new StringBuffer();
				//Grouping condition changed from scale TO SIS IF amount
				//query3.append(" and b.hrEisSgdMpg.hrEisScaleMst.scaleStartAmt between 3050 and 5499  ");
				query3.append(" and a.deduc9581 = 30 ");
				
				query3.append(" group by a.deduc9581,a.deduc9582  ");


				logger.info("***Query for GIS Repotrt details C**" +query.toString()+query3.toString());


				Query sqlQuery2 = hibSession.createQuery(query.toString()+query3.toString());	      	

				RowList=sqlQuery2.list();
				cnt=1;
				empId=0;

				itr = RowList.iterator();  
				ifno = 0;
				ifsfno = 0;
				ifamt = 0;
				sfamt = 0;
				ifamtonly = 0;
				while (itr.hasNext())
				{
					Object[] rowList = (Object[]) itr.next();
					ifno += Long.parseLong((rowList[0]!=null?rowList[0].toString():"0").toString());
					ifsfno += Long.parseLong((rowList[1]!=null?rowList[1].toString():"0").toString());
					ifamt += Math.round(Double.parseDouble((rowList[2]!=null?rowList[2].toString():"0").toString()));
					sfamt += Math.round(Double.parseDouble((rowList[3]!=null?rowList[3].toString():"0").toString()));
					ifamtonly += Math.round(Double.parseDouble((rowList[4]!=null?rowList[4].toString():"0").toString()));

					cnt++; 
				}
				row = new ArrayList();
				ifnototal=ifnototal+ifno;
				ifsfnototal=ifsfnototal+ifsfno;
				// totalemp+=ifnototal+ifsfnototal;
				ifamtTotal+=ifamt;
				sfamtTotal+=sfamt;
				ifamtonlyTotal+=ifamtonly;
				GrandTotal+=ifamt+sfamt+ifamtonly;


				row.add("Group C (30-70)");
				row.add(ifno);
				row.add(ifsfno);
				row.add(ifno+ifsfno);
				row.add(Math.round(ifamt));
				row.add(Math.round(sfamt));
				row.add(Math.round(ifamtonly));
				row.add(Math.round(ifamt+sfamt+ifamtonly));
				row.add(" ");
				DataList.add(row);

				// For Grp D
				StringBuffer query4 = new StringBuffer();
				//Grouping condition changed from scale TO SIS IF amount
				//query4.append(" and b.hrEisSgdMpg.hrEisScaleMst.scaleStartAmt < 3050    ");
				query4.append(" and a.deduc9581 = 15 ");
				
				query4.append(" group by a.deduc9581,a.deduc9582  ");


				logger.info("***Query for GIS Repotrt details Grp D**" +query.toString()+query4.toString());


				Query sqlQuery3 = hibSession.createQuery(query.toString()+query4.toString());	      	

				RowList=sqlQuery3.list();
				cnt=1;
				empId=0;

				itr = RowList.iterator();  
				ifno = 0;
				ifsfno = 0;
				ifamt = 0;
				sfamt = 0;
				ifamtonly = 0;

				while (itr.hasNext())
				{
					Object[] rowList = (Object[]) itr.next();
					ifno += Long.parseLong((rowList[0]!=null?rowList[0].toString():"0").toString());
					ifsfno += Long.parseLong((rowList[1]!=null?rowList[1].toString():"0").toString());
					ifamt += Math.round(Double.parseDouble((rowList[2]!=null?rowList[2].toString():"0").toString()));
					sfamt += Math.round(Double.parseDouble((rowList[3]!=null?rowList[3].toString():"0").toString()));
					ifamtonly += Math.round(Double.parseDouble((rowList[4]!=null?rowList[4].toString():"0").toString()));

					cnt++; 
				}
				row = new ArrayList();


				ifnototal=ifnototal+ifno;
				ifsfnototal=ifsfnototal+ifsfno;
				totalemp+=ifnototal+ifsfnototal;
				ifamtTotal+=ifamt;
				sfamtTotal+=sfamt;
				ifamtonlyTotal+=ifamtonly;
				GrandTotal+=ifamt+sfamt+ifamtonly;

				row.add("Group D (15-35)");
				row.add(ifno);
				row.add(ifsfno);
				row.add(ifno+ifsfno);
				row.add(Math.round(ifamt));
				row.add(Math.round(sfamt));
				row.add(Math.round(ifamtonly));
				row.add(Math.round(ifamt+sfamt+ifamtonly));

				row.add(" ");
				DataList.add(row);

				row = new ArrayList();
				StyledData dataStyle1 = new StyledData();

				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData("TOTAL");  
				row.add(dataStyle1);



				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(""+ifnototal);  
				row.add(dataStyle1);

				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(""+ifsfnototal);  
				row.add(dataStyle1);

				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(""+totalemp);  
				row.add(dataStyle1);

				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(ifamtTotal);  
				row.add(dataStyle1);

				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(sfamtTotal);  
				row.add(dataStyle1);

				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(ifamtonlyTotal);  
				row.add(dataStyle1);


				// row.add(GrandTotal/*+System.getProperty("line.separator")+TotalInWords*/);
				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(GrandTotal);  
				row.add(dataStyle1);

				row.add(" ");
				DataList.add(row);
				ArrayList row1 = new ArrayList();

				String TotalInWords="Rupees  "+ConvertNumbersToWord.convert(Math.round(GrandTotal))+" only.";

				StyleVO[] centerboldStyleVO = new StyleVO[2];
				centerboldStyleVO[0] = new StyleVO();
				centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
				centerboldStyleVO[1] = new StyleVO();
				centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				centerboldStyleVO[1].setStyleValue("Left"); 
				StyledData dataStyle2 = new StyledData();
				dataStyle2.setStyles(centerboldStyleVO);
				dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(GrandTotal))+" only.");
				dataStyle2.setColspan(9);
				row1.add(dataStyle2);

				for(int c=0;c<9;c++)
					row1.add("");


				DataList.add(row1);
				//}
				logger.info("The size is------>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+GrandTotal);
				if(GrandTotal!=0){}
				else
					DataList=new ArrayList();
				return DataList;
			}


			if(report.getReportCode().equals("12") || report.getReportCode().equals("2500012"))
			{



				StringBuffer lsb = new StringBuffer(  );      
				// Added by Akshay
				String fnamescale = CheckIfNull(report.getParameterValue("FName"));
				String lnamescale = CheckIfNull(report.getParameterValue("LName"));
				// Ended by Akshay
				String empid=CheckIfNull(report.getParameterValue( "employeeName" ));	            
				String Department="";
				String Grade="";
				String Scale="";
				String Designation="";
				String month="";
				String year="";
				String GroupBy="";
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				//logger.info(" nof rec out====>"+noOfRec);
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					//logger.info("No Of Rec is********====>"+noOfRec);
					finalpagesize=Integer.parseInt(noOfRec);
				}


				Department=CheckIfNull(report.getParameterValue( "Department" ));
				//added by samir joshi for bill no wise report
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
				//added by samir joshi for new footer

				//AmountInWords amtInWords = new AmountInWords();
				//amtInWords.setColumnName("Amount");
				//amtInWords.setSuffix("Rupees Only");
				//amtInWords.setTotalAmount(new BigDecimal("1000"));

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

				//report.setGrandTotalTemplate(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);
				Grade=CheckIfNull(report.getParameterValue( "Grade" ));
				Scale=CheckIfNull(report.getParameterValue( "Scale" ));
				Designation=CheckIfNull(report.getParameterValue( "Designation" ));
				String Quarter=CheckIfNull(report.getParameterValue( "Quarter" ));
				//String DeptName=locationNameincaps;
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
				logger.info("the location id from report====>"+locationId);
				// Added by Rajan for check whether bill no is selected or not
				//Added By Maruthi For Back button issue.
				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();
				if( BillNo != null && BillNo != "" )//gampa
				{
					//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
					String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
					stData.add(new StyledData (deptHeader,headerStyleVo));
					styleList.add(stData);				 
				}
				else
				{
					//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
					String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator");
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
				boolean flag=true;
				Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);

				Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
				ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");

				SessionFactory sessionFactory = serviceLocator.getSessionFactory();
				Session session= sessionFactory.getCurrentSession();	           

				Session hibSession = sessionFactory.getCurrentSession();
				String query = " select dtl.hrEisEmpMst.empId ,";
				query+=" pst.orgDesignationMst.dsgnShrtName ,";
				query+=" dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId ,dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, ";
				query+="dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";
				query+=" max(dtl.updatedDate), ";
				query+=" scale.scaleStartAmt || '-' || scale.scaleIncrAmt || '-' ||scale.scaleEndAmt|| '-' ||scale.scaleHigherIncrAmt || '-' ||scale.scaleHigherEndAmt,";
				query+=" dtl.otherCurrentBasic ,dtl.hrEisSgdMpg.sgdMapId,";
				query+=" scale.scaleEffFromDt,scale.scaleEffToDt  ";//hrr	            
				query+=" from HrEisOtherDtlsHst dtl left outer join dtl.hrEisSgdMpg as sgd left outer join sgd.hrEisGdMpg as gd  left outer join sgd.hrEisScaleMst scale, ";
				query+=" OrgUserpostRlt           USRPST, ";
				query+=" OrgPostDetailsRlt pst, ";
				query+="  HrPayOrderHeadMpg ORDHD,  ";
				query+="  HrPayOrderHeadPostMpg ORDPST  ";


				query += " where USRPST.orgUserMst.userId = dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
				query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
				query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
				query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";

				if(!empid.equals("")&&!empid.equals("-1"))            	
					query+=" and dtl.hrEisEmpMst.empId = '"+empid+"'";

				//Added by Akshay 
				if(!lnamescale.equals("") || !lnamescale.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"+lnamescale.toLowerCase()+"%'");
				}

				if(!fnamescale.equals("") || !fnamescale.equals(" "))
				{
					query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"+fnamescale.toLowerCase()+"%'");
				}
				//Ended by Akshay



				if(!Grade.equals("")&&!Grade.equals("-1"))            	
					query+=" and dtl.hrEisSgdMpg.hrEisGdMpg.orgGradeMst.gradeId  = '"+Grade+"'";

				if(!Scale.equals("")&&!Scale.equals("-1"))            	
					query+=" and dtl.hrEisSgdMpg.hrEisScaleMst.scaleId = '"+Scale+"'";

				if(!Designation.equals("")&&!Designation.equals("-1"))            	
					query+=" and pst.orgDesignationMst.dsgnId = '"+Designation+"'";

				if(!Department.equals("")&&!Department.equals("-1"))
					query+=" and pst.cmnLocationMst.locId="+Department+"  ";
				if(isBillDefined&&!BillNo.equals(""))
				{

					query+="   and pst.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "+BillNo+" ) )  and "; 
					query+="   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="+BillNo+" )  "/*"and pst.orgPostMst.postId = pay.orgPostMst.postId  "*/; 
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
				query+=" group by  ";
				query+=" dtl.hrEisEmpMst.empId,dtl.hrEisSgdMpg.sgdMapId,dtl.hrEisEmpMst.orgEmpMst.empPrefix,  ";

				query+=" dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, ";
				query+=" pst.orgDesignationMst.dsgnId,  ";
				query+=" pst.orgDesignationMst.dsgnShrtName,  ";
				query+=" dtl.otherCurrentBasic,scale.scaleEffFromDt,scale.scaleEffToDt, ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
				query+=" dtl.hrEisEmpMst.orgEmpMst.empLname,  ";
				query+=" scale.scaleStartAmt,  ";
				query+=" scale.scaleIncrAmt, scale.scaleHigherIncrAmt, scale.scaleHigherEndAmt,  ";
				query+=" scale.scaleEndAmt  ";
				query+=" order by dtl.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.orgEmpMst.empFname,dtl.hrEisEmpMst.orgEmpMst.empMname,dtl.hrEisEmpMst.orgEmpMst.empLname,max(dtl.updatedDate) desc ";


				logger.info("***Query for Scale details**" +query);


				Query sqlQuery = hibSession.createQuery(query);	      	
				ArrayList dataList=new ArrayList();
				List RowList=sqlQuery.list();
				logger.info("*************************"+RowList.size());
				int cnt=1;


				long empId=0;
				SimpleDateFormat df2 = new SimpleDateFormat( "dd/MM/yyyy" );
				Iterator itr = RowList.iterator();  
				while (itr.hasNext())
				{

					Object[] rowList = (Object[]) itr.next();
					long EmpId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());
					String designation = (String)(rowList[1]!=null?rowList[1]:"");
					long gradeId = Long.parseLong((rowList[2]!=null?rowList[2].toString():"").toString());
					String Class = (String)(rowList[3]!=null?rowList[3]:"");
					String Name = (String)(rowList[4]!=null?rowList[4]:"");

					String Updateddate = df2.format((Object)( (rowList[5]!=null?rowList[5]:"")));		            

					String  scale = (rowList[6]!=null?rowList[6]:"").toString();		            
					long basic = Long.parseLong((rowList[7]!=null?rowList[7]:"").toString());		            
					//double hrr = Double.parseDouble((rowList[8]!=null?rowList[8]:"").toString());		            

					if(scale.equals("---0-0")||scale.equals("----"))
					{
						scale = basic+"";
					}
					scale=scale.replaceAll("-0-0", ""); 
					ArrayList row = new ArrayList();
					/*if( cnt%30==0) // added by samir joshi fro header on each page 
	        			{
	        				row.add(new PageBreak());
	        				row.add("Data");
	        			}*/
					//else
					{
						row.add(cnt);
						row.add(Class);
						row.add(designation);
						row.add(Name);
						row.add(scale);		            
						row.add(Updateddate);
						row.add(basic);

					}
					cnt++; 
					DataList.add(row);

					if( cnt%finalpagesize==0)
					{

						row=new ArrayList();
						row.add(new PageBreak());
						row.add("Data");
						for (int i=0;i<5;i++)
						{
							row.add("");
						}
						DataList.add(row); 
					}


				}


				return DataList;


			}
			
			//added by ravysh
			if(report.getReportCode().equals("106"))
			{   
				
				ReportColumnVO[] rptCol = report.getReportColumns();
				
				
				ServiceLocator serviceLocator = (ServiceLocator) requestKeys.get("serviceLocator");

				SessionFactory sessionFactory = serviceLocator.getSessionFactory();
				
				Session hibSession = sessionFactory.getCurrentSession();
				
				String month=CheckIfNull(report.getParameterValue( "Month" ));
				String year=CheckIfNull(report.getParameterValue( "Year" ));
				String billNo=CheckIfNull(report.getParameterValue( "BillNo_Frm_Paybill" ));
				String Report_billNo=CheckIfNull(report.getParameterValue( "Bill No" ));
				
				String frmParamPage = CheckIfNull(report.getParameterValue("backBtn"));
				
				if(frmParamPage.trim().equals("") || frmParamPage==null)
					frmParamPage="1";
				
				
				long trnbillNo=0;
				String Query_BillNo="";
				 portType="";
				String subheadCode="";
				String classIds="";
				String desgnIds="";
				
				if(!billNo.equalsIgnoreCase("") && !billNo.equalsIgnoreCase("-1"))
				{

					trnbillNo=Long.parseLong(billNo);
					Query_BillNo=paybillDAO.getBillnoFormTrnBillReg(trnbillNo, locationId);
					
				}
				
				if(!Report_billNo.equalsIgnoreCase("") && !Report_billNo.equalsIgnoreCase("-1"))
				{
					StringTokenizer st1=new StringTokenizer(Report_billNo,"~");
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

							Query_BillNo=st1.nextToken();
						l++;
					} 
				}
				
				
				int PREV_MONTH_NO=Integer.parseInt(month)-1;
				int CURR_MONTH_NO=Integer.parseInt(month);
				
				int PREV_YEAR=Integer.parseInt(year);
				int CURR_YEAR=Integer.parseInt(year);
				if(CURR_MONTH_NO==1)
				{
					PREV_MONTH_NO=12;
					PREV_YEAR=CURR_YEAR-1;
				}
				
				String[] months = {"January", "February",
			            "March", "April", "May", "June", "July",
			            "August", "September", "October", "November",
			            "December"};

				
				/*	String Prev_Month=months[Integer.parseInt(month)-2];
				String Curr_Month=months[Integer.parseInt(month)-1];*///commented by rahul and added below two lines
				
				String Prev_Month=months[PREV_MONTH_NO-1];
				String Curr_Month=months[CURR_MONTH_NO-1];
				
				logger.info("++++++++++"+month);
				
				logger.info("++++++++++"+year);
				logger.info("++++++++++"+billNo);
				
				StringBuffer query = new StringBuffer();
				
                query.append(" select "+getNVL+"(paybill_1.emp_id,0) id1, "+getNVL+"(paybill_2.emp_id,0) id2, (select tt.emp_prefix ||' '|| tt.emp_fname || ' '||tt.emp_mname||' ' || tt.emp_lname from org_emp_mst tt,hr_eis_emp_mst m ");
                query.append(" where tt.emp_id = m.emp_mpg_id and m.emp_id=paybill_1.emp_id) emp_name_prev,");
                query.append("paybill_1.id id_1,paybill_1.emp_id emp_id_1,paybill_1.spl_pay spl_pay_1,paybill_1.po po_1,paybill_1.ls ls_1,paybill_1.d_pay d_pay_1,paybill_1.da da_1,");
                query.append("paybill_1.hra hra_1,paybill_1.cla cla_1,paybill_1.ma ma_1,paybill_1.wa wa_1,paybill_1.trans_all trans_all_1,paybill_1.fes_adv fes_adv_1,");			
                query.append(" paybill_1.food_adv food_adv_1,paybill_1.pay_recover pay_recover_1,paybill_1.gross_amt gross_amt_1,paybill_1.slo slo_1,paybill_1.it it_1,paybill_1.hrr hrr_1,");
                query.append(" paybill_1.pli pli_1,paybill_1.pt pt_1,paybill_1.sis_gis_1979 sis_gis_1979_1,paybill_1.sis_if_1981 sis_if_1981_1,paybill_1.sis_sf_1981 sis_sf_1981_1,paybill_1.ais_if_1980 ais_if_1980_1, ");
                query.append(" paybill_1.ais_sf_1980 ais_sf_1980_1,paybill_1.gpf_c gpf_c_1,paybill_1.car_sct_moped_adv car_sct_moped_adv_1,paybill_1.oca_cycle_adv oca_cycle_adv_1,paybill_1.hba hba_1, ");
                query.append(" paybill_1.fan_adv fan_adv_1,paybill_1.jeep_r jeep_r_1,paybill_1.gpf_iv gpf_iv_1,paybill_1.total_ded total_ded_1,paybill_1.net_total net_total_1, ");
                query.append(" paybill_1.created_by created_by_1,paybill_1.created_by_post created_by_post_1,paybill_1.created_date created_date_1,paybill_1.db_id db_id_1,paybill_1.loc_id loc_id_1,");
                query.append(" paybill_1.per_pay per_pay_1,paybill_1.pe pe_1,paybill_1.le le_1,paybill_1.ltc ltc_1,paybill_1.other_allow other_allow_1,paybill_1.bonus bonus_1,paybill_1.other_chrgs other_chrgs_1, ");
                query.append(" paybill_1.surcharge surcharge_1,paybill_1.rent_b rent_b_1,paybill_1.bli bli_1,paybill_1.gpf_adv gpf_adv_1,paybill_1.misc_recov misc_recov_1,paybill_1.trn_counter trn_counter_1, ");
                query.append(" paybill_1.dp_gazzeted dp_gazzeted_1,paybill_1.hba_int hba_int_1,paybill_1.car_sct_moped_int car_sct_moped_int_1,paybill_1.fan_int fan_int_1,paybill_1.oca_cycle_int oca_cycle_int_1,paybill_1.paybill_grp_id paybill_grp_id_1, ");
                query.append(" paybill_1.gpf_iv_adv gpf_iv_adv_1,paybill_1.post_id post_id_1,paybill_1.approve_reject_date approve_reject_date_1,paybill_1.cpf cpf_1, ");
                query.append(" paybill_1.ais_pf ais_pf_1,paybill_1.psr_no psr_no_1,paybill_1.da_gpf da_gpf_1,paybill_1.da_gpfiv da_gpfiv_1,  ");
                
                
                query.append("  (select tt.emp_prefix ||' '|| tt.emp_fname || ' '||tt.emp_mname||' ' || tt.emp_lname from org_emp_mst tt,hr_eis_emp_mst m ");
                query.append(" where tt.emp_id = m.emp_mpg_id and m.emp_id=paybill_2.emp_id) emp_name_current,");
                
                query.append("paybill_2.id id_2,paybill_2.emp_id emp_id_2,paybill_2.spl_pay spl_pay_2,paybill_2.po po_2,paybill_2.ls ls_2,paybill_2.d_pay d_pay_2,paybill_2.da da_2,");
                query.append("paybill_2.hra hra_2,paybill_2.cla cla_2,paybill_2.ma ma_2,paybill_2.wa wa_2,paybill_2.trans_all trans_all_2,paybill_2.fes_adv fes_adv_2,");			
                query.append(" paybill_2.food_adv food_adv_2,paybill_2.pay_recover pay_recover_2,paybill_2.gross_amt gross_amt_2,paybill_2.slo slo_2,paybill_2.it it_2,paybill_2.hrr hrr_2,");
                query.append(" paybill_2.pli pli_2,paybill_2.pt pt_2,paybill_2.sis_gis_1979 sis_gis_1979_2,paybill_2.sis_if_1981 sis_if_1981_2,paybill_2.sis_sf_1981 sis_sf_1981_2,paybill_2.ais_if_1980 ais_if_1980_2, ");
                query.append(" paybill_2.ais_sf_1980 ais_sf_1980_2,paybill_2.gpf_c gpf_c_2,paybill_2.car_sct_moped_adv car_sct_moped_adv_2,paybill_2.oca_cycle_adv oca_cycle_adv_2,paybill_2.hba hba_2, ");
                query.append(" paybill_2.fan_adv fan_adv_2,paybill_2.jeep_r jeep_r_2,paybill_2.gpf_iv gpf_iv_2,paybill_2.total_ded total_ded_2,paybill_2.net_total net_total_2, ");
                query.append(" paybill_2.created_by created_by_2,paybill_2.created_by_post created_by_post_2,paybill_2.created_date created_date_2,paybill_2.db_id db_id_2,paybill_2.loc_id loc_id_2,");
                query.append(" paybill_2.per_pay per_pay_2,paybill_2.pe pe_2,paybill_2.le le_2,paybill_2.ltc ltc_2,paybill_2.other_allow other_allow_2,paybill_2.bonus bonus_2,paybill_2.other_chrgs other_chrgs_2, ");
                query.append(" paybill_2.surcharge surcharge_2,paybill_2.rent_b rent_b_2,paybill_2.bli bli_2,paybill_2.gpf_adv gpf_adv_2,paybill_2.misc_recov misc_recov_2,paybill_2.trn_counter trn_counter_2, ");
                query.append(" paybill_2.dp_gazzeted dp_gazzeted_2,paybill_2.hba_int hba_int_2,paybill_2.car_sct_moped_int car_sct_moped_int_2,paybill_2.fan_int fan_int_2,paybill_2.oca_cycle_int oca_cycle_int_2,paybill_2.paybill_grp_id paybill_grp_id_2, ");
                query.append(" paybill_2.gpf_iv_adv gpf_iv_adv_2,paybill_2.post_id post_id_2,paybill_2.approve_reject_date approve_reject_date_2,paybill_2.cpf cpf_2, ");
                query.append(" paybill_2.ais_pf ais_pf_2,paybill_2.psr_no psr_no_2,paybill_2.da_gpf da_gpf_2,paybill_2.da_gpfiv da_gpfiv_2,  ");
               
                query.append(" (select ot.other_current_basic from hr_eis_other_dtls ot where ot.emp_id=paybill_1.emp_id) basic_1,  ");
                query.append(" (select ot.other_current_basic from hr_eis_other_dtls ot where ot.emp_id=paybill_2.emp_id) basic_2, ");
                
                query.append(" (select omt.grade_id from org_emp_mst omt,hr_eis_emp_mst emt  where emt.emp_id=paybill_1.emp_id and emt.emp_mpg_id=omt.emp_id) Grade_1,  ");
                query.append(" (select omt.grade_id from org_emp_mst omt,hr_eis_emp_mst emt  where emt.emp_id=paybill_2.emp_id and emt.emp_mpg_id=omt.emp_id) Grade_2, ");
                
                query.append(" (select sm.scale_grade_pay from hr_eis_other_dtls ot, hr_eis_scale_mst sm,hr_eis_sgd_mpg mpg   ");
                query.append("  where ot.emp_id=paybill_1.emp_id and mpg.sgd_map_id=ot.emp_sgd_id and mpg.sgd_scale_id=sm.scale_id) Gpay_1, ");
                
                query.append(" (select sm.scale_grade_pay from hr_eis_other_dtls ot, hr_eis_scale_mst sm,hr_eis_sgd_mpg mpg   ");
                query.append("  where ot.emp_id=paybill_2.emp_id and mpg.sgd_map_id=ot.emp_sgd_id and mpg.sgd_scale_id=sm.scale_id) Gpay_2 ");
                
                query.append(" from (select * from hr_pay_paybill pb1,(select  distinct ph.paybill_id from paybill_head_mpg ph ");
                query.append(" where ph.paybill_month = "+PREV_MONTH_NO+" and ph.approve_flag in (0,1) and ph.paybill_year = "+PREV_YEAR+" and ph.loc_id = "+locationId);
                
                if(!Query_BillNo.equals(""))	
                {
                	query.append(" and ph.bill_no = (select bsm.bill_subhead_id from hr_pay_bill_subhead_mpg bsm where bsm.bill_no='"+Query_BillNo+"' and bsm.loc_id="+locationId+")" );
                }
                
                query.append(") paybill_prev " +
        		" where pb1.paybill_grp_id = paybill_prev.paybill_id) paybill_1 ");
//                query.append(" ph.bill_no = (select bsm.bill_subhead_id from hr_pay_bill_subhead_mpg bsm where bsm.bill_no='"+Query_BillNo+"' and bsm.loc_id="+locationId+")) paybill_prev " +
//                		" where pb1.paybill_grp_id = paybill_prev.paybill_id) paybill_1 ");
                
                query.append(" full outer join ");
                
                query.append("  (select * from hr_pay_paybill pb2,(select  distinct ph.paybill_id from paybill_head_mpg ph ");
                query.append(" where ph.paybill_month = "+CURR_MONTH_NO+" and ph.approve_flag in (0,1) and ph.paybill_year = "+CURR_YEAR+" and ph.loc_id = "+locationId );
                	
                if(!Query_BillNo.equals(""))	
                {
                	
                query.append(" and ph.bill_no = (select bsm.bill_subhead_id from hr_pay_bill_subhead_mpg bsm where bsm.bill_no='"+Query_BillNo+"' and bsm.loc_id="+locationId+")" );
                }		
                query.append(") paybill_current " +
                		" where pb2.paybill_grp_id = paybill_current.paybill_id) paybill_2 ");
                
               // query.append(" on  paybill_2.post_id = paybill_1.post_id and  paybill_2.emp_id = paybill_1.emp_id  ");
                query.append(" on    paybill_2.emp_id = paybill_1.emp_id  ");
                query.append(" where ");
               
                
                query.append(" (paybill_1.spl_pay <> paybill_2.spl_pay or paybill_1.po <> paybill_2.po or paybill_1.ls <> paybill_2.ls or paybill_1.d_pay <> paybill_2.d_pay or paybill_1.da <> paybill_2.da or paybill_1.hra <> paybill_2.hra or paybill_1.cla <> paybill_2.cla  ");
                query.append("  or paybill_1.ma <> paybill_2.ma or paybill_1.wa <> paybill_2.wa or paybill_1.trans_all <> paybill_2.trans_all or paybill_1.fes_adv <> paybill_2.fes_adv or paybill_1.food_adv <> paybill_2.food_adv  ");
                
                query.append(" or paybill_1.pay_recover <> paybill_2.pay_recover or paybill_1.gross_amt <> paybill_2.gross_amt or paybill_1.slo <> paybill_2.slo or paybill_1.it <> paybill_2.it or paybill_1.hrr <> paybill_2.hrr or paybill_1.pli <> paybill_2.pli  ");
                query.append(" or paybill_1.pt <> paybill_2.pt or paybill_1.sis_gis_1979 <> paybill_2.sis_gis_1979 or paybill_1.sis_if_1981 <> paybill_2.sis_if_1981 or paybill_1.sis_sf_1981 <> paybill_2.sis_sf_1981  ");
                query.append(" or paybill_1.ais_if_1980 <> paybill_2.ais_if_1980 or paybill_1.ais_sf_1980 <> paybill_2.ais_sf_1980 or paybill_1.gpf_c <> paybill_2.gpf_c or paybill_1.car_sct_moped_adv <> paybill_2.car_sct_moped_adv  ");
                
                query.append(" or paybill_1.oca_cycle_adv <> paybill_2.oca_cycle_adv or paybill_1.hba <> paybill_2.hba or paybill_1.fan_adv <> paybill_2.fan_adv or paybill_1.jeep_r <> paybill_2.jeep_r or paybill_1.gpf_iv <> paybill_2.gpf_iv  ");
                query.append(" or paybill_1.total_ded <> paybill_2.total_ded or paybill_1.net_total <> paybill_2.net_total or paybill_1.per_pay <> paybill_2.per_pay or paybill_1.pe <> paybill_2.pe or paybill_1.le <> paybill_2.le or paybill_1.ltc <> paybill_2.ltc  ");
                query.append(" or paybill_1.other_allow <> paybill_2.other_allow or paybill_1.bonus <> paybill_2.bonus or paybill_1.other_chrgs <> paybill_2.other_chrgs or paybill_1.surcharge <> paybill_2.surcharge or paybill_1.rent_b <> paybill_2.rent_b  ");
                
                query.append(" or paybill_1.bli <> paybill_2.bli or paybill_1.misc_recov <> paybill_2.misc_recov or paybill_1.hba_int <> paybill_2.hba_int or paybill_1.car_sct_moped_int <> paybill_2.car_sct_moped_int or paybill_1.fan_int <> paybill_2.fan_int  ");
                query.append(" or paybill_1.oca_cycle_int <> paybill_2.oca_cycle_int or paybill_1.gpf_iv_adv <> paybill_2.gpf_iv_adv or paybill_1.cpf <> paybill_2.cpf or paybill_1.ais_pf <> paybill_2.ais_pf or paybill_1.da_gpf <> paybill_2.da_gpf  ");
                query.append(" or paybill_1.da_gpfiv <> paybill_2.da_gpfiv or "+getNVL+"(paybill_2.emp_id,0)<>"+getNVL+"(paybill_1.emp_id,0)) ");
                
               // query.append(" order by paybill_1.psr_no ");
				
				Query sqlquery = hibSession.createSQLQuery(query.toString());
                List RowList=sqlquery.list();
				Iterator itr1 = RowList.iterator(); 
				
				
				long PREV_MONTH_PER_PAY=0;
				long PREV_MONTH_SPL_PAY=0;
				long CURR_MONTH_PER_PAY=0;
				long CURR_MONTH_SPL_PAY=0;
				
				long PREV_MONTH_PO=0;
				long PREV_MONTH_PE=0;
				long CURR_MONTH_PO=0;
				long CURR_MONTH_PE=0;
				
				long PREV_MONTH_GP=0;
				long PREV_MONTH_BASIC=0;
				long CURR_MONTH_GP=0;
				long CURR_MONTH_BASIC=0;
				long PREV_MONTH_GPAY=0;
				long CURR_MONTH_GPAY=0;
				
				long PREV_MONTH_LS=0;
				long PREV_MONTH_LE=0;
				long CURR_MONTH_LS=0;
				long CURR_MONTH_LE=0;
				
				long PREV_MONTH_DPAY=0;
				long PREV_MONTH_DPAY_GAZ=0;
				long CURR_MONTH_DPAY=0;
				long CURR_MONTH_DPAY_GAZ=0;
				
				long PREV_MONTH_DA=0;
				long CURR_MONTH_DA=0;
				
				long PREV_MONTH_HRA=0;
				long PREV_MONTH_LTC=0;
				long CURR_MONTH_HRA=0;
				long CURR_MONTH_LTC=0;
				
				long PREV_MONTH_CLA=0;
				long PREV_MONTH_OA=0;
				long CURR_MONTH_CLA=0;
				long CURR_MONTH_OA=0;
				
				long PREV_MONTH_MA=0;
				long PREV_MONTH_BONUS=0;
				long CURR_MONTH_MA=0;
				long CURR_MONTH_BONUS=0;
				
				long PREV_MONTH_WA=0;
				long PREV_MONTH_OCH=0;
				long CURR_MONTH_WA=0;
				long CURR_MONTH_OCH=0;
				
				long PREV_MONTH_TA=0;
				long CURR_MONTH_TA=0;
				
				long PREV_MONTH_FESADV=0;
				long CURR_MONTH_FESADV=0;
				
				long PREV_MONTH_FOODADV=0;
				long CURR_MONTH_FOODADV=0;
				
				long PREV_MONTH_PAYREC=0;
				long CURR_MONTH_PAYREC=0;
				
				long PREV_MONTH_GROSS=0;
				long CURR_MONTH_GROSS=0;
				
				long PREV_MONTH_IT=0;
				long PREV_MONTH_SURCHG=0;
				long CURR_MONTH_IT=0;
				long CURR_MONTH_SURCHG=0;
				
				long PREV_MONTH_HRR=0;
				long PREV_MONTH_RENTB=0;
				long CURR_MONTH_HRR=0;
				long CURR_MONTH_RENTB=0;
				
				long PREV_MONTH_PLI=0;
				long PREV_MONTH_BLI=0;
				long CURR_MONTH_PLI=0;
				long CURR_MONTH_BLI=0;
				
				long PREV_MONTH_PT=0;
				long CURR_MONTH_PT=0;
				
				long PREV_MONTH_SIS_1979=0;
				long CURR_MONTH_SIS_1979=0;
				
				long PREV_MONTH_SIS_IF_1981=0;
				long CURR_MONTH_SIS_IF_1981=0;
				
				long PREV_MONTH_SIS_SF_1981=0;
				long CURR_MONTH_SIS_SF_1981=0;
				
				long PREV_MONTH_AIS_IF_1980=0;
				long CURR_MONTH_AIS_IF_1980=0;
				
				long PREV_MONTH_AIS_SF_1980=0;
				long CURR_MONTH_AIS_SF_1980=0;
				
				long PREV_MONTH_GPF_C=0;
				long PREV_MONTH_CPF=0;
				long PREV_MONTH_AIS_PF=0;					
				long PREV_MONTH_GPF_ADV=0;
				long PREV_MONTH_DA_GPF=0;
				long CURR_MONTH_GPF_C=0;
				long CURR_MONTH_CPF=0;
				long CURR_MONTH_AIS_PF=0;
				long CURR_MONTH_GPF_ADV=0;
				long CURR_MONTH_DA_GPF=0;
				
				long PREV_MONTH_CAR_ADV=0;
				long PREV_MONTH_CAR_INT=0;
				long CURR_MONTH_CAR_ADV=0;
				long CURR_MONTH_CAR_INT=0;
				
				long PREV_MONTH_OCA_CYCLE_ADV=0;
				long PREV_MONTH_OCA_CYCLE_INT=0;
				long CURR_MONTH_OCA_CYCLE_ADV=0;
				long CURR_MONTH_OCA_CYCLE_INT=0;
				
				long PREV_MONTH_HBA=0;
				long PREV_MONTH_HBA_INT=0;
				long CURR_MONTH_HBA=0;
				long CURR_MONTH_HBA_INT=0;
				
				long PREV_MONTH_FAN=0;
				long PREV_MONTH_FAN_INT=0;
				long CURR_MONTH_FAN=0;
				long CURR_MONTH_FAN_INT=0;
				
				long PREV_MONTH_JEEP_RENT=0;
				long PREV_MONTH_MISC_RECV=0;
				long CURR_MONTH_JEEP_RENT=0;
				long CURR_MONTH_MISC_RECV=0;
				
				long PREV_MONTH_GPF_IV=0;
				long PREV_MONTH_GPF_IV_ADV=0;
				long PREV_MONTH_DA_GPF_IV=0;
				long CURR_MONTH_GPF_IV=0;
				long CURR_MONTH_GPF_IV_ADV=0;
				long CURR_MONTH_DA_GPF_IV=0;
				
				long PREV_MONTH_TOTAL_DEDUC=0;
				long CURR_MONTH_TOTAL_DEDUC=0;
				
				long PREV_MONTH_NET_AMT=0;
				long CURR_MONTH_NET_AMT=0;
				
				long EMP_ID_1=0;
				long EMP_ID_2=0;
				
				
				long PREV_MONTH_CLASS=0;
				long CURR_MONTH_CLASS=0;
				
				String PREV_MONTH_PSR="";
				String CURR_MONTH_PSR="";
				
				long PREV_PSR=0;
				long CURR_PSR=0;
				
				ArrayList tblData = new ArrayList();
				ArrayList trow = new ArrayList();
				
				while (itr1.hasNext())
				{
					Object[] rowList1 = (Object[]) itr1.next();
					ArrayList rowList11=new ArrayList();
					ArrayList rowList22=new ArrayList();
					
					PREV_PSR=Long.parseLong((rowList1[67]!=null?rowList1[67]:"0").toString());
					CURR_PSR=Long.parseLong((rowList1[135]!=null?rowList1[135]:"0").toString());
					
					PREV_MONTH_PSR=String.valueOf(PREV_PSR);
					CURR_MONTH_PSR=String.valueOf(CURR_PSR);
					

					
					rowList11.add(PREV_MONTH_PSR+" "+((String)(rowList1[2]!=null?rowList1[2]:"")));
					rowList22.add(CURR_MONTH_PSR+" "+((String)(rowList1[70]!=null?rowList1[70]:"")));//emp name
					
					EMP_ID_1=Long.parseLong(rowList1[0].toString());
					EMP_ID_2=Long.parseLong(rowList1[1].toString());
					
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add("<b>"+Prev_Month+"</b>");

					tblData.add(trow);
					TabularData td = new TabularData(tblData);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);
					
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add("<b>"+Curr_Month+"</b>");
					tblData.add(trow);
					 td = new TabularData(tblData);
					 td.addStyle(IReportConstants.BORDER, "No"); 
					 if(EMP_ID_1==0)
					 {
						 td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					 }

					 rowList22.add(td);// Month name
					
					
					 tblData = new ArrayList();
					 trow = new ArrayList();
					
					
					 PREV_MONTH_PER_PAY=Math.round(Math.round(Double.parseDouble((rowList1[43]!=null?rowList1[43]:"0").toString())));
					 PREV_MONTH_SPL_PAY=Math.round(Double.parseDouble((rowList1[5]!=null?rowList1[5]:"0").toString()));
					 CURR_MONTH_PER_PAY=Math.round(Math.round(Double.parseDouble((rowList1[111]!=null?rowList1[111]:"0").toString())));
					 CURR_MONTH_SPL_PAY=Math.round(Double.parseDouble((rowList1[73]!=null?rowList1[73]:"0").toString()));
					 
					 if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_PER_PAY==PREV_MONTH_PER_PAY?CURR_MONTH_PER_PAY:(CURR_MONTH_PER_PAY)+"<font color=red><b>("+(CURR_MONTH_PER_PAY-PREV_MONTH_PER_PAY)+")</b></font>")+
					System.getProperty("line.separator")+(CURR_MONTH_SPL_PAY==PREV_MONTH_SPL_PAY?CURR_MONTH_SPL_PAY:CURR_MONTH_SPL_PAY+"<font color=red><b>("+(CURR_MONTH_SPL_PAY-PREV_MONTH_SPL_PAY)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_PER_PAY+System.getProperty("line.separator")+CURR_MONTH_SPL_PAY); 
					 }
					tblData.add(trow);
					 td = new TabularData(tblData);
                    td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					
					rowList22.add(td); 
					
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_PER_PAY+System.getProperty("line.separator")+PREV_MONTH_SPL_PAY);
					tblData.add(trow);
					td = new TabularData(tblData);
                    td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); // spl pay column
					
					
					
					PREV_MONTH_PO=Math.round(Math.round(Double.parseDouble((rowList1[6]!=null?rowList1[6]:"0").toString())));
					PREV_MONTH_PE=Math.round(Double.parseDouble((rowList1[44]!=null?rowList1[44]:"0").toString()));
					CURR_MONTH_PO=Math.round(Math.round(Double.parseDouble((rowList1[74]!=null?rowList1[74]:"0").toString())));
					CURR_MONTH_PE=Math.round(Double.parseDouble((rowList1[112]!=null?rowList1[112]:"0").toString()));


					PREV_MONTH_LS=Math.round(Math.round(Double.parseDouble((rowList1[7]!=null?rowList1[7]:"0").toString())));
					PREV_MONTH_LE=Math.round(Double.parseDouble((rowList1[45]!=null?rowList1[45]:"0").toString()));
					CURR_MONTH_LS=Math.round(Math.round(Double.parseDouble((rowList1[75]!=null?rowList1[75]:"0").toString())));
					CURR_MONTH_LE=Math.round(Double.parseDouble((rowList1[113]!=null?rowList1[113]:"0").toString()));

					PREV_MONTH_BASIC=Math.round(Math.round(Double.parseDouble((rowList1[138]!=null?rowList1[138]:"0").toString())));
					CURR_MONTH_BASIC=Math.round(Math.round(Double.parseDouble((rowList1[139]!=null?rowList1[139]:"0").toString())));
					PREV_MONTH_GPAY=Math.round(Math.round(Double.parseDouble((rowList1[142]!=null?rowList1[142]:"0").toString())));
					CURR_MONTH_GPAY=Math.round(Math.round(Double.parseDouble((rowList1[143]!=null?rowList1[143]:"0").toString())));
					
					
					// getting class of employee in both months
					PREV_MONTH_CLASS=Long.parseLong((rowList1[140]!=null?rowList1[140]:"0").toString());
					CURR_MONTH_CLASS=Long.parseLong((rowList1[141]!=null?rowList1[141]:"0").toString());
					

					if(PREV_MONTH_BASIC>0)
						PREV_MONTH_GP = PREV_MONTH_GPAY*(PREV_MONTH_LS+PREV_MONTH_PO+PREV_MONTH_PE)/PREV_MONTH_BASIC;
					  
					if(CURR_MONTH_BASIC>0)
						CURR_MONTH_GP = CURR_MONTH_GPAY*(CURR_MONTH_LS+CURR_MONTH_PO+CURR_MONTH_PE)/CURR_MONTH_BASIC;
					   
					if(PREV_MONTH_PO>0)
						PREV_MONTH_PO-=PREV_MONTH_GP;
					else if(PREV_MONTH_PE>0)
						PREV_MONTH_PE-=PREV_MONTH_GP;
					     
					
					if(CURR_MONTH_PO>0)
						CURR_MONTH_PO-=CURR_MONTH_GP;
					else if(CURR_MONTH_PE>0)
						CURR_MONTH_PE-=CURR_MONTH_GP;
					
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_PO==PREV_MONTH_PO?CURR_MONTH_PO:(CURR_MONTH_PO)+"<font color=red><b>("+(CURR_MONTH_PO-PREV_MONTH_PO)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_PE==PREV_MONTH_PE?CURR_MONTH_PE:CURR_MONTH_PE+"<font color=red><b>("+(CURR_MONTH_PE-PREV_MONTH_PE)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_PO+System.getProperty("line.separator")+CURR_MONTH_PE); 
					 }
					
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_PO+System.getProperty("line.separator")+PREV_MONTH_PE);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);// PO and PE pay column
					
					
						tblData = new ArrayList();
						trow = new ArrayList();
						if((PREV_MONTH_CLASS==100110015) || (PREV_MONTH_CLASS==100110011) || (PREV_MONTH_CLASS==100110012))
						trow.add(PREV_MONTH_GP+System.getProperty("line.separator")+0);
						
						else if((PREV_MONTH_CLASS==100110013) || (PREV_MONTH_CLASS==100110014))
							trow.add(0+System.getProperty("line.separator")+PREV_MONTH_GP);
						
						
						tblData.add(trow);
						td = new TabularData(tblData);
						td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
						td.addStyle(IReportConstants.BORDER, "No"); 
						if(EMP_ID_2==0)
						{
							td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
						}
						rowList11.add(td);
						
						tblData = new ArrayList();
						trow = new ArrayList();
						
						if(EMP_ID_1!=0)
						 {
							if((CURR_MONTH_CLASS==100110015) || (CURR_MONTH_CLASS==100110011) || (CURR_MONTH_CLASS==100110012))
						trow.add((CURR_MONTH_GP==PREV_MONTH_GP?CURR_MONTH_GP:(CURR_MONTH_GP)+"<font color=red><b>("+(CURR_MONTH_GP-PREV_MONTH_GP)+")</b></font>")+
								System.getProperty("line.separator")+0);
							else if((CURR_MONTH_CLASS==100110013) || (CURR_MONTH_CLASS==100110014) )
								trow.add(0+
										System.getProperty("line.separator")+(CURR_MONTH_GP==PREV_MONTH_GP?CURR_MONTH_GP:(CURR_MONTH_GP)+"<font color=red><b>("+(CURR_MONTH_GP-PREV_MONTH_GP)+")</b></font>"));	
						 }
						 else
						 {
							 if((CURR_MONTH_CLASS==100110015) || (CURR_MONTH_CLASS==100110011) || (CURR_MONTH_CLASS==100110012))
							 trow.add(CURR_MONTH_GP+System.getProperty("line.separator")+0); 
							 else if((CURR_MONTH_CLASS==100110013) || (CURR_MONTH_CLASS==100110014) )
								 trow.add(0+System.getProperty("line.separator")+CURR_MONTH_GP);  
						 }
						
						tblData.add(trow);
						td = new TabularData(tblData);
						td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
						td.addStyle(IReportConstants.BORDER, "No"); 
						if(EMP_ID_1==0)
						{
							td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
						}
						rowList22.add(td); // Grade Pay column
					 
					 
					
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_LS+System.getProperty("line.separator")+PREV_MONTH_LE);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);
					
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_LS==PREV_MONTH_LS?CURR_MONTH_LS:(CURR_MONTH_LS)+"<font color=red><b>("+(CURR_MONTH_LS-PREV_MONTH_LS)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_LE==PREV_MONTH_LE?CURR_MONTH_LE:CURR_MONTH_LE+"<font color=red><b>("+(CURR_MONTH_LE-PREV_MONTH_LE)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_LS+System.getProperty("line.separator")+CURR_MONTH_LE); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); // L.S and L.E column
				
					
					
					 PREV_MONTH_DPAY=Math.round(Math.round(Double.parseDouble((rowList1[8]!=null?rowList1[8]:"0").toString())));
					 PREV_MONTH_DPAY_GAZ=Math.round(Double.parseDouble((rowList1[56]!=null?rowList1[56]:"0").toString()));
				     CURR_MONTH_DPAY=Math.round(Math.round(Double.parseDouble((rowList1[76]!=null?rowList1[76]:"0").toString())));
					 CURR_MONTH_DPAY_GAZ=Math.round(Double.parseDouble((rowList1[124]!=null?rowList1[124]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_DPAY_GAZ+System.getProperty("line.separator")+PREV_MONTH_DPAY);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);
					tblData = new ArrayList();
					trow = new ArrayList();
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_DPAY_GAZ==PREV_MONTH_DPAY_GAZ?CURR_MONTH_DPAY_GAZ:(CURR_MONTH_DPAY_GAZ)+"<font color=red><b>("+(CURR_MONTH_DPAY_GAZ-PREV_MONTH_DPAY_GAZ)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_DPAY==PREV_MONTH_DPAY?CURR_MONTH_DPAY:CURR_MONTH_DPAY+"<font color=red><b>("+(CURR_MONTH_DPAY-PREV_MONTH_DPAY)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_DPAY_GAZ+System.getProperty("line.separator")+CURR_MONTH_DPAY); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td);// DPAY column
					
					
					
					 PREV_MONTH_DA=Math.round(Math.round(Double.parseDouble((rowList1[9]!=null?rowList1[9]:"0").toString())));
					
				     CURR_MONTH_DA=Math.round(Math.round(Double.parseDouble((rowList1[77]!=null?rowList1[77]:"0").toString())));
					
					tblData = new ArrayList();
					trow = new ArrayList();
					if(PREV_MONTH_CLASS==100110015||PREV_MONTH_CLASS==100110011||PREV_MONTH_CLASS==100110012)
					trow.add(PREV_MONTH_DA+System.getProperty("line.separator")+0);
					else if(PREV_MONTH_CLASS==100110013||PREV_MONTH_CLASS==100110014)
						trow.add(0+System.getProperty("line.separator")+PREV_MONTH_DA);	
						
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);
					tblData = new ArrayList();
					trow = new ArrayList();
					if(EMP_ID_1!=0)
					 {
						if(CURR_MONTH_CLASS==100110015||CURR_MONTH_CLASS==100110011||CURR_MONTH_CLASS==100110012)
					trow.add((CURR_MONTH_DA==PREV_MONTH_DA?CURR_MONTH_DA:(CURR_MONTH_DA)+"<font color=red><b>("+(CURR_MONTH_DA-PREV_MONTH_DA)+")</b></font>")+
							System.getProperty("line.separator")+0);
						else if(PREV_MONTH_CLASS==100110013||PREV_MONTH_CLASS==100110014)
							trow.add(0+
									System.getProperty("line.separator")+(CURR_MONTH_DA==PREV_MONTH_DA?CURR_MONTH_DA:(CURR_MONTH_DA)+"<font color=red><b>("+(CURR_MONTH_DA-PREV_MONTH_DA)+")</b></font>"));	
						
					 }
					 else
					 {
						 if(CURR_MONTH_CLASS==100110015||CURR_MONTH_CLASS==100110011||CURR_MONTH_CLASS==100110012)
						 trow.add(CURR_MONTH_DA+System.getProperty("line.separator")+0); 
						 else if(PREV_MONTH_CLASS==100110013||PREV_MONTH_CLASS==100110014)
							 trow.add(0+System.getProperty("line.separator")+CURR_MONTH_DA); 
						 
					 }
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); // DA column
				
				
				
					 PREV_MONTH_HRA=Math.round(Math.round(Double.parseDouble((rowList1[10]!=null?rowList1[10]:"0").toString())));
					 PREV_MONTH_LTC=Math.round(Double.parseDouble((rowList1[46]!=null?rowList1[46]:"0").toString()));
					 CURR_MONTH_HRA=Math.round(Math.round(Double.parseDouble((rowList1[78]!=null?rowList1[78]:"0").toString())));
					 CURR_MONTH_LTC=Math.round(Double.parseDouble((rowList1[114]!=null?rowList1[114]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_HRA+System.getProperty("line.separator")+PREV_MONTH_LTC);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_HRA==PREV_MONTH_HRA?CURR_MONTH_HRA:(CURR_MONTH_HRA)+"<font color=red><b>("+(CURR_MONTH_HRA-PREV_MONTH_HRA)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_LTC==PREV_MONTH_LTC?CURR_MONTH_LTC:CURR_MONTH_LTC+"<font color=red><b>("+(CURR_MONTH_LTC-PREV_MONTH_LTC)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_HRA+System.getProperty("line.separator")+CURR_MONTH_LTC); 
					 }
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //HRA column
				
				
					
					 PREV_MONTH_CLA=Math.round(Math.round(Double.parseDouble((rowList1[11]!=null?rowList1[11]:"0").toString())));
					 PREV_MONTH_OA=Math.round(Double.parseDouble((rowList1[47]!=null?rowList1[47]:"0").toString()));
				     CURR_MONTH_CLA=Math.round(Math.round(Double.parseDouble((rowList1[79]!=null?rowList1[79]:"0").toString())));
					 CURR_MONTH_OA=Math.round(Double.parseDouble((rowList1[115]!=null?rowList1[115]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_CLA+System.getProperty("line.separator")+PREV_MONTH_OA);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_CLA==PREV_MONTH_CLA?CURR_MONTH_CLA:(CURR_MONTH_CLA)+"<font color=red><b>("+(CURR_MONTH_CLA-PREV_MONTH_CLA)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_OA==PREV_MONTH_OA?CURR_MONTH_OA:CURR_MONTH_OA+"<font color=red><b>("+(CURR_MONTH_OA-PREV_MONTH_OA)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_CLA+System.getProperty("line.separator")+CURR_MONTH_OA); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); // CLA and OA column
					
					
					
					 PREV_MONTH_MA=Math.round(Math.round(Double.parseDouble((rowList1[12]!=null?rowList1[12]:"0").toString())));
					 PREV_MONTH_BONUS=Math.round(Double.parseDouble((rowList1[48]!=null?rowList1[48]:"0").toString()));
				     CURR_MONTH_MA=Math.round(Math.round(Double.parseDouble((rowList1[80]!=null?rowList1[80]:"0").toString())));
					 CURR_MONTH_BONUS=Math.round(Double.parseDouble((rowList1[116]!=null?rowList1[116]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_MA+System.getProperty("line.separator")+PREV_MONTH_BONUS);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_MA==PREV_MONTH_MA?CURR_MONTH_MA:(CURR_MONTH_MA)+"<font color=red><b>("+(CURR_MONTH_MA-PREV_MONTH_MA)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_BONUS==PREV_MONTH_BONUS?CURR_MONTH_BONUS:CURR_MONTH_BONUS+"<font color=red><b>("+(CURR_MONTH_BONUS-PREV_MONTH_BONUS)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_MA+System.getProperty("line.separator")+CURR_MONTH_BONUS); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); // MA and BONUS column
					
					
					
					 PREV_MONTH_WA=Math.round(Math.round(Double.parseDouble((rowList1[13]!=null?rowList1[13]:"0").toString())));
					 PREV_MONTH_OCH=Math.round(Double.parseDouble((rowList1[49]!=null?rowList1[49]:"0").toString()));
				     CURR_MONTH_WA=Math.round(Math.round(Double.parseDouble((rowList1[81]!=null?rowList1[81]:"0").toString())));
					 CURR_MONTH_OCH=Math.round(Double.parseDouble((rowList1[117]!=null?rowList1[117]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_WA+System.getProperty("line.separator")+PREV_MONTH_OCH);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No");
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_WA==PREV_MONTH_WA?CURR_MONTH_WA:(CURR_MONTH_WA)+"<font color=red><b>("+(CURR_MONTH_WA-PREV_MONTH_WA)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_OCH==PREV_MONTH_OCH?CURR_MONTH_OCH:CURR_MONTH_OCH+"<font color=red><b>("+(CURR_MONTH_OCH-PREV_MONTH_OCH)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_WA+System.getProperty("line.separator")+CURR_MONTH_OCH); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); // WA and Other Charges column
					
					
					
					 PREV_MONTH_TA=Math.round(Math.round(Double.parseDouble((rowList1[14]!=null?rowList1[14]:"0").toString())));
					 CURR_MONTH_TA=Math.round(Math.round(Double.parseDouble((rowList1[82]!=null?rowList1[82]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_TA);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_TA==PREV_MONTH_TA?CURR_MONTH_TA:(CURR_MONTH_TA)+"<font color=red><b>("+(CURR_MONTH_TA-PREV_MONTH_TA)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_TA); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); // TA column
				
					
					
					 PREV_MONTH_FESADV=Math.round(Math.round(Double.parseDouble((rowList1[15]!=null?rowList1[15]:"0").toString())));
					 CURR_MONTH_FESADV=Math.round(Math.round(Double.parseDouble((rowList1[83]!=null?rowList1[83]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_FESADV);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_FESADV==PREV_MONTH_FESADV?CURR_MONTH_FESADV:(CURR_MONTH_FESADV)+"<font color=red><b>("+(CURR_MONTH_FESADV-PREV_MONTH_FESADV)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_FESADV); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td);// FES ADV column
				
				
					
					 PREV_MONTH_FOODADV=Math.round(Math.round(Double.parseDouble((rowList1[16]!=null?rowList1[16]:"0").toString())));
					 CURR_MONTH_FOODADV=Math.round(Math.round(Double.parseDouble((rowList1[84]!=null?rowList1[84]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_FOODADV);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_FOODADV==PREV_MONTH_FOODADV?CURR_MONTH_FOODADV:(CURR_MONTH_FOODADV)+"<font color=red><b>("+(CURR_MONTH_FOODADV-PREV_MONTH_FOODADV)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_FOODADV); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); // FOOD ADV column
				
					
					
					 PREV_MONTH_PAYREC=Math.round(Math.round(Double.parseDouble((rowList1[17]!=null?rowList1[17]:"0").toString())));
					 CURR_MONTH_PAYREC=Math.round(Math.round(Double.parseDouble((rowList1[85]!=null?rowList1[85]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_PAYREC);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_PAYREC==PREV_MONTH_PAYREC?CURR_MONTH_PAYREC:(CURR_MONTH_PAYREC)+"<font color=red><b>("+(CURR_MONTH_PAYREC-PREV_MONTH_PAYREC)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_PAYREC); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); // PAY RECOVERY column
				
					
					
					 PREV_MONTH_GROSS=Math.round(Math.round(Double.parseDouble((rowList1[18]!=null?rowList1[18]:"0").toString())));
					 CURR_MONTH_GROSS=Math.round(Math.round(Double.parseDouble((rowList1[86]!=null?rowList1[86]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_GROSS);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_GROSS==PREV_MONTH_GROSS?CURR_MONTH_GROSS:(CURR_MONTH_GROSS)+"<font color=red><b>("+(CURR_MONTH_GROSS-PREV_MONTH_GROSS)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_GROSS); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); // GROSS column
					
					
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(" ");
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					else if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList11.add(td);
					rowList22.add(td);//SLO column
					
					
					 PREV_MONTH_IT=Math.round(Math.round(Double.parseDouble((rowList1[20]!=null?rowList1[20]:"0").toString())));
					 PREV_MONTH_SURCHG=Math.round(Double.parseDouble((rowList1[50]!=null?rowList1[50]:"0").toString()));
				     CURR_MONTH_IT=Math.round(Math.round(Double.parseDouble((rowList1[88]!=null?rowList1[88]:"0").toString())));
					 CURR_MONTH_SURCHG=Math.round(Double.parseDouble((rowList1[118]!=null?rowList1[118]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_IT+System.getProperty("line.separator")+PREV_MONTH_SURCHG);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_IT==PREV_MONTH_IT?CURR_MONTH_IT:(CURR_MONTH_IT)+"<font color=red><b>("+(CURR_MONTH_IT-PREV_MONTH_IT)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_SURCHG==PREV_MONTH_SURCHG?CURR_MONTH_SURCHG:CURR_MONTH_SURCHG+"<font color=red><b>("+(CURR_MONTH_SURCHG-PREV_MONTH_SURCHG)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_IT+System.getProperty("line.separator")+CURR_MONTH_SURCHG); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //IT and Surcharge column
					
					
					 PREV_MONTH_HRR=Math.round(Math.round(Double.parseDouble((rowList1[21]!=null?rowList1[21]:"0").toString())));
					 PREV_MONTH_RENTB=Math.round(Double.parseDouble((rowList1[51]!=null?rowList1[51]:"0").toString()));
				     CURR_MONTH_HRR=Math.round(Math.round(Double.parseDouble((rowList1[89]!=null?rowList1[89]:"0").toString())));
					 CURR_MONTH_RENTB=Math.round(Double.parseDouble((rowList1[119]!=null?rowList1[119]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_HRR+System.getProperty("line.separator")+PREV_MONTH_RENTB);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_HRR==PREV_MONTH_HRR?CURR_MONTH_HRR:(CURR_MONTH_HRR)+"<font color=red><b>("+(CURR_MONTH_HRR-PREV_MONTH_HRR)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_RENTB==PREV_MONTH_RENTB?CURR_MONTH_RENTB:CURR_MONTH_RENTB+"<font color=red><b>("+(CURR_MONTH_RENTB-PREV_MONTH_RENTB)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_HRR+System.getProperty("line.separator")+CURR_MONTH_RENTB); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //HRR and RentB column
				
					
					
					 PREV_MONTH_PLI=Math.round(Math.round(Double.parseDouble((rowList1[22]!=null?rowList1[22]:"0").toString())));
					 PREV_MONTH_BLI=Math.round(Double.parseDouble((rowList1[52]!=null?rowList1[52]:"0").toString()));
				     CURR_MONTH_PLI=Math.round(Math.round(Double.parseDouble((rowList1[90]!=null?rowList1[90]:"0").toString())));
					 CURR_MONTH_BLI=Math.round(Double.parseDouble((rowList1[120]!=null?rowList1[120]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_PLI+System.getProperty("line.separator")+PREV_MONTH_BLI);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_PLI==PREV_MONTH_PLI?CURR_MONTH_PLI:(CURR_MONTH_HRR)+"<font color=red><b>("+(CURR_MONTH_PLI-PREV_MONTH_PLI)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_BLI==PREV_MONTH_BLI?CURR_MONTH_BLI:CURR_MONTH_BLI+"<font color=red><b>("+(CURR_MONTH_BLI-PREV_MONTH_BLI)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_PLI+System.getProperty("line.separator")+CURR_MONTH_BLI); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //PLI and BLI column
				
					
					
					 PREV_MONTH_PT=Math.round(Math.round(Double.parseDouble((rowList1[23]!=null?rowList1[23]:"0").toString())));
					 CURR_MONTH_PT=Math.round(Math.round(Double.parseDouble((rowList1[91]!=null?rowList1[91]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_PT);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_PT==PREV_MONTH_PT?CURR_MONTH_PT:(CURR_MONTH_PT)+"<font color=red><b>("+(CURR_MONTH_PT-PREV_MONTH_PT)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_PT); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //PT column
					
					
					
					 PREV_MONTH_SIS_1979=Math.round(Math.round(Double.parseDouble((rowList1[24]!=null?rowList1[24]:"0").toString())));
					 CURR_MONTH_SIS_1979=Math.round(Math.round(Double.parseDouble((rowList1[92]!=null?rowList1[92]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_SIS_1979);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_SIS_1979==PREV_MONTH_SIS_1979?CURR_MONTH_SIS_1979:(CURR_MONTH_SIS_1979)+"<font color=red><b>("+(CURR_MONTH_SIS_1979-PREV_MONTH_SIS_1979)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_SIS_1979); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //SIS_1979 column
					
					
					
					 PREV_MONTH_SIS_IF_1981=Math.round(Math.round(Double.parseDouble((rowList1[25]!=null?rowList1[25]:"0").toString())));
					 CURR_MONTH_SIS_IF_1981=Math.round(Math.round(Double.parseDouble((rowList1[93]!=null?rowList1[93]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_SIS_IF_1981);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_SIS_IF_1981==PREV_MONTH_SIS_IF_1981?CURR_MONTH_SIS_IF_1981:(CURR_MONTH_SIS_IF_1981)+"<font color=red><b>("+(CURR_MONTH_SIS_IF_1981-PREV_MONTH_SIS_IF_1981)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_SIS_IF_1981); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //SIS_IF_1981 column	
				
					
					
					 PREV_MONTH_SIS_SF_1981=Math.round(Math.round(Double.parseDouble((rowList1[26]!=null?rowList1[26]:"0").toString())));
					 CURR_MONTH_SIS_SF_1981=Math.round(Math.round(Double.parseDouble((rowList1[94]!=null?rowList1[94]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_SIS_SF_1981);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_SIS_SF_1981==PREV_MONTH_SIS_SF_1981?CURR_MONTH_SIS_SF_1981:(CURR_MONTH_SIS_SF_1981)+"<font color=red><b>("+(CURR_MONTH_SIS_SF_1981-PREV_MONTH_SIS_SF_1981)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_SIS_SF_1981); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //SIS_SF_1981 column	
				
					
					
					 PREV_MONTH_AIS_IF_1980=Math.round(Math.round(Double.parseDouble((rowList1[27]!=null?rowList1[27]:"0").toString())));
					 CURR_MONTH_AIS_IF_1980=Math.round(Math.round(Double.parseDouble((rowList1[95]!=null?rowList1[95]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_AIS_IF_1980);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_AIS_IF_1980==PREV_MONTH_AIS_IF_1980?CURR_MONTH_AIS_IF_1980:(CURR_MONTH_AIS_IF_1980)+"<font color=red><b>("+(CURR_MONTH_AIS_IF_1980-PREV_MONTH_AIS_IF_1980)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_AIS_IF_1980); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //AIS_IF_1980 column		
					
				
					
					 PREV_MONTH_AIS_SF_1980=Math.round(Math.round(Double.parseDouble((rowList1[28]!=null?rowList1[28]:"0").toString())));
					 CURR_MONTH_AIS_SF_1980=Math.round(Math.round(Double.parseDouble((rowList1[96]!=null?rowList1[96]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_AIS_SF_1980);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_AIS_SF_1980==PREV_MONTH_AIS_SF_1980?CURR_MONTH_AIS_SF_1980:(CURR_MONTH_AIS_SF_1980)+"<font color=red><b>("+(CURR_MONTH_AIS_SF_1980-PREV_MONTH_AIS_SF_1980)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_AIS_SF_1980); 
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td);//AIS_SF_1980 column		
				
				
					
					
					 PREV_MONTH_GPF_C=Math.round(Math.round(Double.parseDouble((rowList1[29]!=null?rowList1[29]:"0").toString())));
					 PREV_MONTH_CPF=Math.round(Double.parseDouble((rowList1[65]!=null?rowList1[65]:"0").toString()));
					 PREV_MONTH_AIS_PF=Math.round(Double.parseDouble((rowList1[66]!=null?rowList1[66]:"0").toString()));					
					 PREV_MONTH_GPF_ADV=Math.round(Double.parseDouble((rowList1[53]!=null?rowList1[53]:"0").toString()));
					 PREV_MONTH_DA_GPF=Math.round(Double.parseDouble((rowList1[68]!=null?rowList1[68]:"0").toString()));
					 CURR_MONTH_GPF_C=Math.round(Math.round(Double.parseDouble((rowList1[97]!=null?rowList1[97]:"0").toString())));
					 CURR_MONTH_CPF=Math.round(Double.parseDouble((rowList1[133]!=null?rowList1[133]:"0").toString()));
					 CURR_MONTH_AIS_PF=Math.round(Math.round(Double.parseDouble((rowList1[134]!=null?rowList1[134]:"0").toString())));
					 CURR_MONTH_GPF_ADV=Math.round(Double.parseDouble((rowList1[121]!=null?rowList1[121]:"0").toString()));
					 CURR_MONTH_DA_GPF=Math.round(Double.parseDouble((rowList1[136]!=null?rowList1[136]:"0").toString()));
					
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_GPF_C+System.getProperty("line.separator")+PREV_MONTH_CPF+System.getProperty("line.separator")+PREV_MONTH_AIS_PF+System.getProperty("line.separator")+PREV_MONTH_GPF_ADV+System.getProperty("line.separator")+PREV_MONTH_DA_GPF);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);

					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_GPF_C==PREV_MONTH_GPF_C?CURR_MONTH_GPF_C:(CURR_MONTH_GPF_C)+"<font color=red><b>("+(CURR_MONTH_GPF_C-PREV_MONTH_GPF_C)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_CPF==PREV_MONTH_CPF?CURR_MONTH_CPF:(CURR_MONTH_CPF)+"<font color=red><b>("+(CURR_MONTH_CPF-PREV_MONTH_CPF)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_AIS_PF==PREV_MONTH_AIS_PF?CURR_MONTH_AIS_PF:(CURR_MONTH_AIS_PF)+"<font color=red><b>("+(CURR_MONTH_AIS_PF-PREV_MONTH_AIS_PF)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_GPF_ADV==PREV_MONTH_GPF_ADV?CURR_MONTH_GPF_ADV:(CURR_MONTH_GPF_ADV)+"<font color=red><b>("+(CURR_MONTH_GPF_ADV-PREV_MONTH_GPF_ADV)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_DA_GPF==PREV_MONTH_DA_GPF?CURR_MONTH_DA_GPF:(CURR_MONTH_DA_GPF)+"<font color=red><b>("+(CURR_MONTH_DA_GPF-PREV_MONTH_DA_GPF)+")</b></font>"));
						
					 }
					 else
					 {
						 trow.add(CURR_MONTH_GPF_C+
									System.getProperty("line.separator")+CURR_MONTH_CPF+
									System.getProperty("line.separator")+CURR_MONTH_AIS_PF+
									System.getProperty("line.separator")+CURR_MONTH_GPF_ADV+
									System.getProperty("line.separator")+CURR_MONTH_DA_GPF);
					 }
					
                    tblData.add(trow);
                    td = new TabularData(tblData);
                    td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
                    td.addStyle(IReportConstants.BORDER, "No"); 
                    if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
                    rowList22.add(td);//GPF, CPS, etc. column
					
				
				
					 PREV_MONTH_CAR_ADV=Math.round(Math.round(Double.parseDouble((rowList1[30]!=null?rowList1[30]:"0").toString())));
					 PREV_MONTH_CAR_INT=Math.round(Double.parseDouble((rowList1[58]!=null?rowList1[58]:"0").toString()));
					 CURR_MONTH_CAR_ADV=Math.round(Math.round(Double.parseDouble((rowList1[98]!=null?rowList1[98]:"0").toString())));
					 CURR_MONTH_CAR_INT=Math.round(Double.parseDouble((rowList1[126]!=null?rowList1[126]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_CAR_ADV+System.getProperty("line.separator")+PREV_MONTH_CAR_INT);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_CAR_ADV==PREV_MONTH_CAR_ADV?CURR_MONTH_CAR_ADV:(CURR_MONTH_CAR_ADV)+"<font color=red><b>("+(CURR_MONTH_CAR_ADV-PREV_MONTH_CAR_ADV)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_CAR_INT==PREV_MONTH_CAR_INT?CURR_MONTH_CAR_INT:CURR_MONTH_CAR_INT+"<font color=red><b>("+(CURR_MONTH_CAR_INT-PREV_MONTH_CAR_INT)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_CAR_ADV+
									System.getProperty("line.separator")+CURR_MONTH_CAR_INT);
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td);  //Car Moped Loan  column
				
				
					
					 PREV_MONTH_OCA_CYCLE_ADV=Math.round(Math.round(Double.parseDouble((rowList1[31]!=null?rowList1[31]:"0").toString())));
					 PREV_MONTH_OCA_CYCLE_INT=Math.round(Double.parseDouble((rowList1[60]!=null?rowList1[60]:"0").toString()));
					 CURR_MONTH_OCA_CYCLE_ADV=Math.round(Math.round(Double.parseDouble((rowList1[99]!=null?rowList1[99]:"0").toString())));
					 CURR_MONTH_OCA_CYCLE_INT=Math.round(Double.parseDouble((rowList1[128]!=null?rowList1[128]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_OCA_CYCLE_ADV+System.getProperty("line.separator")+PREV_MONTH_OCA_CYCLE_INT);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_OCA_CYCLE_ADV==PREV_MONTH_OCA_CYCLE_ADV?CURR_MONTH_OCA_CYCLE_ADV:(CURR_MONTH_OCA_CYCLE_ADV)+"<font color=red><b>("+(CURR_MONTH_OCA_CYCLE_ADV-PREV_MONTH_OCA_CYCLE_ADV)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_OCA_CYCLE_INT==PREV_MONTH_OCA_CYCLE_INT?CURR_MONTH_OCA_CYCLE_INT:CURR_MONTH_OCA_CYCLE_INT+"<font color=red><b>("+(CURR_MONTH_OCA_CYCLE_INT-PREV_MONTH_OCA_CYCLE_INT)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_OCA_CYCLE_ADV+
									System.getProperty("line.separator")+CURR_MONTH_OCA_CYCLE_INT);
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No");
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //OCA_CYCLE_ADV  column
				
				

					 PREV_MONTH_HBA=Math.round(Math.round(Double.parseDouble((rowList1[32]!=null?rowList1[32]:"0").toString())));
					 PREV_MONTH_HBA_INT=Math.round(Double.parseDouble((rowList1[57]!=null?rowList1[57]:"0").toString()));
					 CURR_MONTH_HBA=Math.round(Math.round(Double.parseDouble((rowList1[100]!=null?rowList1[100]:"0").toString())));
					 CURR_MONTH_HBA_INT=Math.round(Double.parseDouble((rowList1[125]!=null?rowList1[125]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_HBA+System.getProperty("line.separator")+PREV_MONTH_HBA_INT);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_HBA==PREV_MONTH_HBA?CURR_MONTH_HBA:(CURR_MONTH_HBA)+"<font color=red><b>("+(CURR_MONTH_HBA-PREV_MONTH_HBA)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_HBA_INT==PREV_MONTH_HBA_INT?CURR_MONTH_HBA_INT:CURR_MONTH_HBA_INT+"<font color=red><b>("+(CURR_MONTH_HBA_INT-PREV_MONTH_HBA_INT)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_HBA+
									System.getProperty("line.separator")+CURR_MONTH_HBA_INT);
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //HBA  column
					
					
					
					 PREV_MONTH_FAN=Math.round(Math.round(Double.parseDouble((rowList1[33]!=null?rowList1[33]:"0").toString())));
					 PREV_MONTH_FAN_INT=Math.round(Double.parseDouble((rowList1[59]!=null?rowList1[59]:"0").toString()));
					 CURR_MONTH_FAN=Math.round(Math.round(Double.parseDouble((rowList1[101]!=null?rowList1[101]:"0").toString())));
					 CURR_MONTH_FAN_INT=Math.round(Double.parseDouble((rowList1[127]!=null?rowList1[127]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_FAN+System.getProperty("line.separator")+PREV_MONTH_FAN_INT);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_FAN==PREV_MONTH_FAN?CURR_MONTH_FAN:(CURR_MONTH_FAN)+"<font color=red><b>("+(CURR_MONTH_FAN-PREV_MONTH_FAN)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_FAN_INT==PREV_MONTH_FAN_INT?CURR_MONTH_FAN_INT:CURR_MONTH_FAN_INT+"<font color=red><b>("+(CURR_MONTH_FAN_INT-PREV_MONTH_FAN_INT)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_FAN+
									System.getProperty("line.separator")+CURR_MONTH_FAN_INT);
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //FAN  column
					
					

					 PREV_MONTH_JEEP_RENT=Math.round(Math.round(Double.parseDouble((rowList1[34]!=null?rowList1[34]:"0").toString())));
					 PREV_MONTH_MISC_RECV=Math.round(Double.parseDouble((rowList1[54]!=null?rowList1[54]:"0").toString()));
					 CURR_MONTH_JEEP_RENT=Math.round(Math.round(Double.parseDouble((rowList1[102]!=null?rowList1[102]:"0").toString())));
					 CURR_MONTH_MISC_RECV=Math.round(Double.parseDouble((rowList1[122]!=null?rowList1[122]:"0").toString()));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_JEEP_RENT+System.getProperty("line.separator")+PREV_MONTH_MISC_RECV);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td); 
					tblData = new ArrayList();
					trow = new ArrayList();
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_JEEP_RENT==PREV_MONTH_JEEP_RENT?CURR_MONTH_JEEP_RENT:(CURR_MONTH_JEEP_RENT)+"<font color=red><b>("+(CURR_MONTH_JEEP_RENT-PREV_MONTH_JEEP_RENT)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_MISC_RECV==PREV_MONTH_MISC_RECV?CURR_MONTH_MISC_RECV:CURR_MONTH_MISC_RECV+"<font color=red><b>("+(CURR_MONTH_MISC_RECV-PREV_MONTH_MISC_RECV)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_JEEP_RENT+
									System.getProperty("line.separator")+CURR_MONTH_MISC_RECV);
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //JEEP RENT  column
					

					
					 PREV_MONTH_GPF_IV=Math.round(Math.round(Double.parseDouble((rowList1[35]!=null?rowList1[35]:"0").toString())));
					 PREV_MONTH_GPF_IV_ADV=Math.round(Double.parseDouble((rowList1[62]!=null?rowList1[62]:"0").toString()));
					 PREV_MONTH_DA_GPF_IV=Math.round(Double.parseDouble((rowList1[69]!=null?rowList1[69]:"0").toString()));
					 CURR_MONTH_GPF_IV=Math.round(Math.round(Double.parseDouble((rowList1[103]!=null?rowList1[103]:"0").toString())));
					 CURR_MONTH_GPF_IV_ADV=Math.round(Double.parseDouble((rowList1[130]!=null?rowList1[130]:"0").toString()));
					 CURR_MONTH_DA_GPF_IV=Math.round(Double.parseDouble((rowList1[137]!=null?rowList1[137]:"0").toString()));
					
					tblData = new ArrayList();
					trow = new ArrayList();
					
					trow.add(PREV_MONTH_GPF_IV+System.getProperty("line.separator")+PREV_MONTH_GPF_IV_ADV+System.getProperty("line.separator")+PREV_MONTH_DA_GPF_IV);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);
					
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_GPF_IV==PREV_MONTH_GPF_IV?CURR_MONTH_GPF_IV:(CURR_MONTH_GPF_IV)+"<font color=red><b>("+(CURR_MONTH_GPF_IV-PREV_MONTH_GPF_IV)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_GPF_IV_ADV==PREV_MONTH_GPF_IV_ADV?CURR_MONTH_GPF_IV_ADV:(CURR_MONTH_GPF_IV_ADV)+"<font color=red><b>("+(CURR_MONTH_GPF_IV_ADV-PREV_MONTH_GPF_IV_ADV)+")</b></font>")+
							System.getProperty("line.separator")+(CURR_MONTH_DA_GPF_IV==PREV_MONTH_DA_GPF_IV?CURR_MONTH_DA_GPF_IV:(CURR_MONTH_DA_GPF_IV)+"<font color=red><b>("+(CURR_MONTH_DA_GPF_IV-PREV_MONTH_DA_GPF_IV)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_GPF_IV+
									System.getProperty("line.separator")+CURR_MONTH_GPF_IV_ADV+System.getProperty("line.separator")+CURR_MONTH_DA_GPF_IV);
					 }
					
                    tblData.add(trow);
					
					 td = new TabularData(tblData);
					 td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					 td.addStyle(IReportConstants.BORDER, "No"); 
					 if(EMP_ID_1==0)
						{
							td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
						}
					 rowList22.add(td);//GPF_IV  column

					 

					 PREV_MONTH_TOTAL_DEDUC=Math.round(Math.round(Double.parseDouble((rowList1[36]!=null?rowList1[36]:"0").toString())));
					 CURR_MONTH_TOTAL_DEDUC=Math.round(Math.round(Double.parseDouble((rowList1[104]!=null?rowList1[104]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_TOTAL_DEDUC);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);
					 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_TOTAL_DEDUC==PREV_MONTH_TOTAL_DEDUC?CURR_MONTH_TOTAL_DEDUC:(CURR_MONTH_TOTAL_DEDUC)+"<font color=red><b>("+(CURR_MONTH_TOTAL_DEDUC-PREV_MONTH_TOTAL_DEDUC)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_TOTAL_DEDUC);
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td);//TOTAL_DEDUC column		
					
					
					
					 PREV_MONTH_NET_AMT=Math.round(Math.round(Double.parseDouble((rowList1[37]!=null?rowList1[37]:"0").toString())));
					 CURR_MONTH_NET_AMT=Math.round(Math.round(Double.parseDouble((rowList1[105]!=null?rowList1[105]:"0").toString())));
					tblData = new ArrayList();
					trow = new ArrayList();
					trow.add(PREV_MONTH_NET_AMT);
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_2==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_YELLOW);
					}
					rowList11.add(td);
					 
					tblData = new ArrayList();
					trow = new ArrayList();
					
					if(EMP_ID_1!=0)
					 {
					trow.add((CURR_MONTH_NET_AMT==PREV_MONTH_NET_AMT?CURR_MONTH_NET_AMT:(CURR_MONTH_NET_AMT)+"<font color=red><b>("+(CURR_MONTH_NET_AMT-PREV_MONTH_NET_AMT)+")</b></font>"));
					 }
					 else
					 {
						 trow.add(CURR_MONTH_NET_AMT);
					 }
					
					tblData.add(trow);
					td = new TabularData(tblData);
					td.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					td.addStyle(IReportConstants.BORDER, "No"); 
					if(EMP_ID_1==0)
					{
						td.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_CYAN);
					}
					rowList22.add(td); //NET_AMNT column			
					
					
					
					if(EMP_ID_1!=0)
					DataList.add(rowList11);
					if(EMP_ID_2!=0)
					DataList.add(rowList22);
					
					
				}
				
				StyleVO[] URLStyleVO=null;
				URLStyleVO = new StyleVO[2];
				URLStyleVO[0] = new StyleVO();
				URLStyleVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
				URLStyleVO[1] = new StyleVO();
				URLStyleVO[1].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_TEXT);
				if(frmParamPage.equals("0"))
				{
					URLStyleVO[0].setStyleValue("javascript:self.close()");
					URLStyleVO[1].setStyleValue("Close");
				}
				else
				{
					URLStyleVO[0].setStyleValue("hrms.htm?actionFlag=reportService&reportCode=106&action=parameterPage&dynamicReport=true");
					URLStyleVO[1].setStyleValue("Ok");
				}
				report.setStyleList(URLStyleVO);
				
				report.setReportColumns(rptCol);
				report.initializeDynamicTreeModel();
				report.initializeTreeModel(); 

				return DataList;
			
			}
			
			
			
			

			/////////////  for CPF report
			if(report.getReportCode().equals("13") || report.getReportCode().equals("2500013"))
			{

				//for report footer
				ReportAttributeVO ravo = new ReportAttributeVO();

				ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
				ravo.setLocation(IReportConstants.LOCATION_FOOTER);
				ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
				String DeptName=locationNameincaps;
				// ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

				//add the attribute to the report instance
				//report.addReportAttributeItem(ravo);
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

				return getCPFData(report,criteria);	              
			}

		

			/////////////  for increment report
			if(report.getReportCode().equals("107") )
			{	
			ReportAttributeVO ravo = new ReportAttributeVO();

			ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
			ravo.setLocation(IReportConstants.LOCATION_FOOTER);
			ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
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
			
			report.setReportName("ANNEXURE-1"); 
			report.initializeDynamicTreeModel();
			report.initializeTreeModel(); 

			return getIncrementRptData(report,criteria);	              
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
		String billType=CheckIfNull(report.getParameterValue( "billTypePara" ));

		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
		//added by samir joshi for bill no wise report
		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
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

		//ended by samir joshi/////
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
			ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
			genDAO.setSessionFactory(serv.getSessionFactory());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 

			String langName=report.getLangId();
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

		//String locationNameincaps=locationName.toUpperCase();
		String DeptName=locationNameincaps;
		// Added by Rajan for check whether bill no is selected or not

		//Added by Maruthi For back buton issue.
		ArrayList styleList = new ArrayList();
		ArrayList stData = new ArrayList();
		String deptHeader="";
		if( BillNo != null && BillNo != "" )
		{
			//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
			deptHeader=DeptName+System.getProperty("line.separator")+"ANNEXURE 'C' (REFER PARA-2)"+System.getProperty("line.separator")+"Schedule pertaining to the credit Head:8011-Insurance Fund Pension Fund "+System.getProperty("line.separator")+"(Gujarat Goverment Employees Group Insurance Scheme 1981) " +System.getProperty("line.separator")+"for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+"N.B (In cases subscription is remained in arrears the fact should be shown in red ink as a separate footnote)."+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
			stData.add(new StyledData (deptHeader,headerStyleVo));
			styleList.add(stData);				 
		}
		else
		{
			//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
			deptHeader =DeptName+System.getProperty("line.separator")+"STATEMENT SHOWING THE DETAILS OF NEW CONTRIBUTORY PENSION SCHEME:TIER-1 FUND"+System.getProperty("line.separator")+"Amount deducted from the salary for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"); 
			stData.add(new StyledData (deptHeader,headerStyleVo));
			styleList.add(stData);

		}	
		TabularData tData  = new TabularData(styleList);
		//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
		//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
		//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
		tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
		tData.addStyle(IReportConstants.BORDER, "No");
		tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
		report.setAdditionalHeader(tData);
		//End By Maruthi.
		//if(!BillNo.equals(""))
		//report.setReportName(DeptName+System.getProperty("line.separator")+"STATEMENT SHOWING THE DETAILS OF NEW CONTRIBUTORY PENSION SCHEME:TIER-1 FUND"+System.getProperty("line.separator")+"Amount deducted from the salary for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
		//else
		//report.setReportName(DeptName+System.getProperty("line.separator")+"STATEMENT SHOWING THE DETAILS OF NEW CONTRIBUTORY PENSION SCHEME:TIER-1 FUND"+System.getProperty("line.separator")+"Amount deducted from the salary for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator"));
		//Ended by Rajan

//		by manoj for head change
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
		lsb.append(" sum(pay.deduc9534) ");//CPF
		
		lsb.append("  ,trim(pay.hrEisEmpMst.orgEmpMst.empGPFnumber) ");//GPF ACC NO
		lsb.append("  ,scale.scaleGradePay ");// GRADE PAY
		lsb.append("  ,sum(pay.deduc9999+pay.deduc9998) ");// DA GPF
		
		if(billType.equals(String.valueOf(arrearbillTypeId)))
		lsb.append("  from HrPayArrearPaybill pay,  ");
		else
			lsb.append("  from HrPayPaybill pay,  ");
			
//		by rahul w.r.t head change
		/*if(currentMonthBill!=1)
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
		lsb.append(" PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm, ");
		/* lsb.append("  HrPayOrderHeadMpgHst ORDHD,  ");
            lsb.append("  HrPayOrderHeadPostMpgHst ORDPST,  ");*/
		/*if(currentMonthBill!=1)
		{
			lsb.append(" HrPayOrderHeadMpgHst ORDHD, ");
			lsb.append(" HrPayOrderHeadPostMpgHst ORDPST, ");
		}
		else*/
		
		lsb.append(" HrPayOrderHeadMpg ORDHD, ");
		lsb.append(" HrPayOrderHeadPostMpg ORDPST, ");
		

		lsb.append("  OrgPostDetailsRlt pst  ");


		lsb.append("  where pay.hrEisEmpMst.orgEmpMst.empDoj >='"+CPFDate+"' and pay.deduc9534>0 and  ");
		lsb.append("   dtl.id.trnCounter = pay.otherTrnCntr and  ");
		lsb.append("  pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId  ");
		
		
		if(billType.equals(String.valueOf(arrearbillTypeId)))
		{
		String arrearType="";
		arrearType=CheckIfNull(report.getParameterValue( "Arrear List" ));
		if(!arrearType.equals("")&&!arrearType.equals("-1"))
		{
			lsb.append(" and  pay.salRevId.salRevId="+arrearType+" ");
		}
		}				
		


		//lsb.append("  and ORDPST.orderHeadId = ORDHD.id.orderHeadId  ");
		/*if(currentMonthBill!=1)
		{
			lsb.append(" and ORDPST.orderHeadId = ORDHD.id.orderHeadId ");
		}
		else*/
		
		lsb.append(" and ORDPST.orderHeadId = ORDHD.orderHeadId ");
		
		
		if(!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
			lsb.append(" and  bhm.billTypeId.lookupId="+Long.parseLong(billType)+" ");	


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

		if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
		{
			lsb.append("   and ORDHD.subheadId  = '"+subHeadId+"'");
		}

		if(!Grade.equals("")&&!Grade.equals("-1"))  
			lsb.append(" and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  = '"+Grade+"' "); 		            		

		if(!Scale.equals("")&&!Scale.equals("-1"))  
			lsb.append(" and dtl.hrEisSgdMpg.hrEisScaleMst.scaleId = '"+Scale+"' ");	            		            	


		if(!Designation.equals("")&&!Designation.equals("-1"))            	
			lsb.append(" and pst.orgDesignationMst.dsgnId = '"+Designation+"'  ");

		if(!Department.equals("")&&!Department.equals("-1"))
			lsb.append("  and pst.cmnLocationMst.locId="+Department+"  ");
		if(isBillDefined&&!BillNo.equals(""))
		{

			//lsb.append(" and   pay.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId ) and ") ; 
			/*if(currentMonthBill==1)
				lsb.append("   ORDHD.subheadId in (select bill.subheadId from HrPayBillHeadMpg bill where bill.billId ="+BillNo+" ) and  ");*/
			lsb.append(" and pst.orgPostMst.postId = pay.orgPostMst.postId  "); 
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
		lsb.append("  and bhm.hrPayPaybill = pay.paybillGrpId   and hpbsm.billHeadId = bhm.billNo.billHeadId  ");//finyrid change done by chirashree
		lsb.append(" and bhm.orgGradeMst.gradeId=pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId "); //Date 5-Nov-08 by rahul
		lsb.append("  group by   ");
		lsb.append("  trim(pay.hrEisEmpMst.orgEmpMst.empGPFnumber),pay.hrEisEmpMst.orgEmpMst.empId,pay.hrEisEmpMst.empId,dtl.hrEisEmpMst.orgEmpMst.empId,    ");

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
		lsb.append("  ,scale.scaleGradePay  "); 
		
		lsb.append("  order by trim(pay.hrEisEmpMst.orgEmpMst.empGPFnumber),pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.orgEmpMst.empId ");
		logger.info("***Query for CPF details**"+ lsb.toString());


		Query sqlQuery = hibSession.createQuery( lsb.toString());	      	
		List RowList=sqlQuery.list();
		logger.info("*************************"+RowList.size());
		int cnt=1;
		long empId=0;
		long CPFTotal=0;
		long GrandTotal=0;
		long DAGPFTotal = 0;
		long tier1total = 0;
		long tier2total = 0;
		//Added By Mrugesh. If no record in report then not show any data in report.
		if(sqlQuery.list().size()!=0){
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
				double CPF=Double.parseDouble((rowList[9]!=null?rowList[9]:"0").toString());
				String gpfAccNo=(String)(rowList[10]!=null?rowList[10]:"");
				long gradePay = Long.parseLong((rowList[11]!=null?rowList[11]:"0").toString());	
				long DAGPF=Math.round(Double.parseDouble((rowList[12]!=null?rowList[12]:"0").toString()));

				long total = basic+Math.round(DP)+Math.round(DA);
				long tier1=(long)Math.round(total/10.0);
				long tier2=(long)CPF-tier1;
				DAGPFTotal+=DAGPF;
				tier1total+=tier1;
				tier2total+=tier2;
				ArrayList row = new ArrayList();
				row.add(gpfAccNo);
				row.add(Name);
				row.add(designation);
				row.add((basic-gradePay)+"+"+gradePay+"="+basic);
				row.add(Math.round(DP));
				row.add(Math.round(DA));
				row.add(total);
				row.add(Math.round(DAGPF));
				row.add(Math.round(tier1));
				if(Math.round(tier2)<0)
					row.add("");
				else
					row.add("");
				row.add(tier1+tier2);
				
				row.add(tier1+tier2);// last total
				row.add("");

				DataList.add(row);
				cnt++; 
				CPFTotal+=CPF;
				GrandTotal+=total;
			}
			ArrayList row = new ArrayList();

			StyleVO[] boldStyleVO = new StyleVO[1];
			boldStyleVO[0] = new StyleVO();
			boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 

			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");

			StyledData dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData("TOTAL");  
			row.add(dataStyle1);

			dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData(Math.round(DAGPFTotal));  
			row.add(dataStyle1);


			dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData(Math.round(tier1total));                  
			row.add(dataStyle1);


			dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData("");                  
			row.add(dataStyle1);


			dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData(Math.round(tier1total+tier2total));                  
			row.add(dataStyle1);
			
			dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData(Math.round(tier1total+tier2total)); //// last total                 
			row.add(dataStyle1);
			
			row.add("");
			
			DataList.add(row);

//			for last page total
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
			dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(tier1total+DAGPFTotal))+" only.");

			ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
			ReportColumnVO[] rptCol = report.getColumnsToDisplay();
			int totallength=rptCol.length;
			int colspan=rptCol2.length;                

			dataStyle2.setColspan(colspan);
			row.add(dataStyle2);



			//for(int c=0;c<(totallength-colspan);c++)
			for(int c=0;c<12;c++)
				row.add("");

			//logger.info(totallength+"******"+row.size()+"******"+colspan);
			DataList.add(row); 

		}

		return DataList;
	}


	public ArrayList getIncrementRptData(ReportVO report,Object criteria		 )
	{
		ArrayList DataList=new ArrayList();
		StringBuffer lsb = new StringBuffer(  );      
		String Department="";
		String Grade="";
		String Scale="";
		String Designation="";
		String month="";
		String year="";
		String fname = "";
		String lname = "";
		String portType = "";
		final int pagesize=22;
		
		fname=CheckIfNull(report.getParameterValue("FName"));
		lname=CheckIfNull(report.getParameterValue("LName"));
		Grade=CheckIfNull(report.getParameterValue( "Grade" ));
		Scale=CheckIfNull(report.getParameterValue( "Scale" ));
		Designation=CheckIfNull(report.getParameterValue( "Designation" ));
		month=CheckIfNull(report.getParameterValue( "Month" ));
		year=CheckIfNull(report.getParameterValue( "Year" ));
		
		String incrementMonth=resourceBundle.getString("INCREMENT_MONTH");
		if(!month.equals(""))
			month=incrementMonth;
				
		Department=CheckIfNull(report.getParameterValue( "Department" ));
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");

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



		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		ServiceLocator serv1 = (ServiceLocator) requestKeys.get("serviceLocator");

		PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv1.getSessionFactory());

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

		boolean flag=true;
		Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);

		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");

		SessionFactory sessionFactory = serviceLocator.getSessionFactory();

		Session hibSession = sessionFactory.getCurrentSession();

		sdfObj = new SimpleDateFormat("MMM");
		String Month = sdfObj.format(startMonthDate);

		String DeptName=locationNameincaps;

		ArrayList styleList = new ArrayList();
		ArrayList stData = new ArrayList();
		String deptHeader="";

		deptHeader ="Annexure to Finance Department Circular No. PGR - 1009 - 26 -PayCell (M) ,Dated - 22-"+incrementMonth+"-"+year+")"
				+System.getProperty("line.separator")+
                "INCREMENT CERTIFICATE TO BE ATTACHED WITH  "+Month+". "+year+" PAYBILL"+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No: "+BillNo; 
		stData.add(new StyledData (deptHeader,headerStyleVo));
		styleList.add(stData);

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
		java.util.Date startingDate = cal.getTime();
		cal.set(Calendar.YEAR,Integer.parseInt( year));
		cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
		
		lsb.append(" 	select pb.emp_id,	 ");//0
		lsb.append(" 	       concat(concat(concat(o.emp_prefix, ' '), concat(o.emp_fname, ' ')),concat(concat(o.emp_mname, ' '), o.emp_lname)),	 ");//1
		lsb.append(" 	       pfix.nxt_incr_date, 	 ");//2
		lsb.append(" 	       pfix.pay_fix_date,	 ");//3
		lsb.append(" 	       pfix.prev_pay_scale,	 ");//4
		lsb.append(" 	       pfix.rev_pay,	 ");//5
		lsb.append(" 	       pfix.prev_pay,	 ");//6
		lsb.append(" 	       pfix.rev_pay,	 ");//7
		lsb.append(" 	       pfix.nxt_incr_date,	 ");//8
		lsb.append(" 	       scale.scale_grade_pay,	 ");//9
		lsb.append(" 	       scale.scale_start_amt,	 ");//10
		lsb.append(" 	       scale.scale_incr_amt,	 ");//11
		lsb.append(" 	       scale.scale_end_amt,	 ");//12
		/*lsb.append(" 	       (select l.leave_from_date	 ");//
		lsb.append(" 	          from hr_leave_emp_dtls l	 ");
		lsb.append(" 	         where l.emp_id = e.emp_id and l.leave_type = 14 and	 ");
		lsb.append(" 	               l.is_deleted = 0 and ((l.leave_from_date >= '1-July-2009' and	 ");
		lsb.append(" 	               l.leave_from_date <= '31-July-2009') or	 ");
		lsb.append(" 	               (l.leave_to_date >= '1-July-2009' and	 ");
		lsb.append(" 	               l.leave_to_date <= '31-July-2009') or	 ");
		lsb.append(" 	               (l.leave_from_date < '1-July-2009' and	 ");
		lsb.append(" 	               leave_to_date > '31-July-2009'))),	 ");
		lsb.append(" 	       (select l.leave_to_date	 ");//14
		lsb.append(" 	          from hr_leave_emp_dtls l	 ");
		lsb.append(" 	         where l.emp_id = e.emp_id and l.leave_type = 14 and	 ");
		lsb.append(" 	               l.is_deleted = 0 and ((l.leave_from_date >= '1-July-2009' and	 ");
		lsb.append(" 	               l.leave_from_date <= '31-July-2009') or	 ");
		lsb.append(" 	               (l.leave_to_date >= '1-July-2009' and	 ");
		lsb.append(" 	               l.leave_to_date <= '31-July-2009') or	 ");
		lsb.append(" 	               (l.leave_from_date < '1-July-2009' and	 ");
		lsb.append(" 	               leave_to_date > '31-July-2009'))),	 ");*/
		lsb.append(" 	       (ot.other_current_basic),	 ");//13 
		lsb.append(" 	       (pb.psr_No)	 ");//14
		lsb.append(" 	  from hr_payfix_mst        pfix,	 ");
		lsb.append(" 	       org_emp_mst          o,	 ");
		lsb.append(" 	       hr_eis_emp_mst       e,	 ");
		lsb.append(" 	       hr_eis_other_dtls    ot,	 ");
		lsb.append(" 	       org_userpost_rlt     up,	 ");
		lsb.append(" 	       org_post_details_rlt pd,	 ");
		lsb.append(" 	       hr_eis_sgd_mpg       sgd,	 ");
		lsb.append(" 	       hr_eis_scale_mst     scale,	 ");
		lsb.append(" 	       org_designation_mst  dsgn,	 ");
		lsb.append(" 	       hr_pay_paybill       pb,	 ");
		lsb.append(" 	       paybill_head_mpg     ph,	 ");
		lsb.append(" 	       hr_pay_bill_subhead_mpg     bh	 ");
		lsb.append(" 	 where pfix.user_id = o.user_id and o.lang_id = 1 and	 ");
		lsb.append(" 	       o.emp_id = e.emp_mpg_id and e.emp_id = ot.emp_id and	 ");
		lsb.append(" 	       up.user_id = o.user_id and pd.post_id= up.post_id and	 ");
		lsb.append(" 	       pfix.pay_fix_date >= '"+startDate+"' and	 ");
		lsb.append(" 	       pfix.pay_fix_date <= '"+endDate+"' and 	 ");
		lsb.append(" 	       ot.emp_sgd_id = sgd.sgd_map_id and sgd.sgd_scale_id = scale.scale_id and	 ");
		lsb.append(" 	       dsgn.dsgn_id = pd.dsgn_id	 ");
		lsb.append(" 	       and pb.emp_id = e.emp_id and ph.paybill_month = "+month+" and	 ");
		lsb.append(" 	       ph.paybill_year = "+year+" and ph.approve_flag in (0, 1) and	 ");
		lsb.append(" 	       ph.paybill_id = pb.paybill_grp_id and ph.bill_category = o.grade_id	 ");
		lsb.append(" 	       and (up.end_date is null or up.end_date> '"+startDate+"') and up.start_date <= '"+endDate+"'	 ");
		lsb.append(" 	       and pd.loc_id = "+locationId+" ") ;
		lsb.append(" 	       and bh.bill_subhead_id = ph.bill_no ") ;
		
		if(!BillNo.equals(""))
		lsb.append(" 	       and  bh.bill_no = "+BillNo+"       	 ");
		
		if(!lname.trim().equals("") )
			lsb.append("  and lower(o.emp_lname) Like '%"+lname.toLowerCase()+"%'");
		
		if(!fname.trim().equals("") )
			lsb.append("  and lower(o.EMP_fname) Like '%"+fname.toLowerCase()+"%'");
		
		if(!Grade.equals("")&&!Grade.equals("-1"))            	
			lsb.append("  and o.grade_id  in ('"+Grade+"')");
		
		if(!Scale.equals("")&&!Scale.equals("-1"))            	
			lsb.append("  and scale.scale_id = '"+Scale+"'");
		
		if(!Designation.equals("")&&!Designation.equals("-1"))            	
			lsb.append("  and pd.dsgn_id = '"+Designation+"'");

		lsb.append(" 	      order by pb.psr_No ") ;

		
		Query sqlQuery = hibSession.createSQLQuery( lsb.toString());	      	
		List RowList=sqlQuery.list();
		logger.info("*************************"+RowList.size());
		int cnt=1;

		if(sqlQuery.list().size()!=0){
			Iterator itr = RowList.iterator();  
			while (itr.hasNext())
			{
				
				if( cnt%pagesize==0 && cnt!=(RowList.size()))
				{
					ArrayList pagebreakdata= new ArrayList();
					pagebreakdata.add(new PageBreak());
					pagebreakdata.add("Data");
					for(int c=0;c<(14);c++)
						pagebreakdata.add("");
					DataList.add(pagebreakdata);

				}
				
				Object[] rowList = (Object[]) itr.next();
				ArrayList row = new ArrayList();
				
				long presentBasicPay = Long.parseLong((rowList[6]!=null?rowList[6]:"0").toString());
				long PayAfterIncrement  = Long.parseLong((rowList[7]!=null?rowList[7]:"0").toString());
				long gradePay=Long.parseLong((rowList[9]!=null?rowList[9]:"0").toString());				
				
				if(locationId==Long.parseLong(resourceBundle.getString("cash2Admin"))) 
					row.add(cnt);//Psr No
				else
				row.add((rowList[14]!=null?rowList[14]:"0"));//Psr No			
								
				row.add((rowList[1]!=null?rowList[1]:""));//Name of incumbent 
				row.add("Officiating");//Whether substantive or officiating 
				
				//row.add((rowList[10]!=null?rowList[10]:"0")+"-"+(rowList[12]!=null?rowList[12]:"0"));//Present pay Band Pay 
				row.add(presentBasicPay-gradePay);//Present pay Band Pay
				
				row.add((rowList[9]!=null?rowList[9]:"0"));//Present pay Grade Pay

				row.add(presentBasicPay);//Present pay Basic Pay
				row.add(resourceBundle.getString("PRESENT_PAY_DRAWN_DATE"));//Date from which the present pay is drawn 
				row.add(PayAfterIncrement-presentBasicPay);//Amount of present increment (Rs.) 
				row.add((rowList[3]!=null?rowList[3]:"0"));//Date of present increment 
				
				//row.add((rowList[10]!=null?rowList[10]:"0")+"-"+(rowList[12]!=null?rowList[12]:"0"));//Pay after present increment Band Pay 
				row.add(PayAfterIncrement-gradePay);//Pay after present increment Band Pay
				
				row.add(gradePay);//Pay after present increment  Grade Pay
				row.add(PayAfterIncrement);//Pay after present increment  Basic Pay
				row.add("-");//Suspend for misconduct from
				row.add("-");//Suspend for misconduct to
				row.add("-");//Leave without pay from 
				row.add("-");//Leave without pay to 
				
				DataList.add(row);
				cnt++; 
				
				
			}
			
			StyleVO[] centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Left"); 
			
			StyledData dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			String leftFooter="Certified that all the Government servants for whom increments have been claimed in the bill have rendered the required period of approved service entitling to the increased pay drawn in the bill";
			dataStyle.setData(leftFooter);
			dataStyle.setColspan(15);
			
			ArrayList footerleft= new ArrayList();
			footerleft.add(dataStyle);
			//DataList.add(footerleft); 

		}

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

		if( report.getReportCode().equals("3") || report.getReportCode().equals("2500003")||report.getReportCode().equals("6")|| report.getReportCode().equals("2500006") || report.getReportCode().equals("7") || report.getReportCode().equals("2500007") || report.getReportCode().equals("8") || report.getReportCode().equals("2500008")|| report.getReportCode().equals("10") || report.getReportCode().equals("2500010")|| report.getReportCode().equals("11") || report.getReportCode().equals("2500011")|| report.getReportCode().equals("13") || report.getReportCode().equals("2500013") || report.getReportCode().equals("2500012") || report.getReportCode().equals("12"))
		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			//added by ravysh
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}
		if( report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("5") || report.getReportCode().equals("2500005")|| report.getReportCode().equals("9") || report.getReportCode().equals("2500009"))
		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
		} 
		/* if( report.getReportCode().equals("3") || report.getReportCode().equals("4") || report.getReportCode().equals("5") || report.getReportCode().equals("6") || report.getReportCode().equals("7") || report.getReportCode().equals("8") || report.getReportCode().equals("9") || report.getReportCode().equals("10")|| report.getReportCode().equals("11")|| report.getReportCode().equals("13"))
     {            
          report.setParameterValue("Department",locationId+"");
     } */

//added by ravysh
		if(report.getReportCode().equals("106"))
		{   
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("Month",month);
			report.setParameterValue("Year",yr);
		} 
		if(report.getReportCode().equals("107"))
		{   
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("Year",yr);
		} 
		
		
		return report;
	}









}
