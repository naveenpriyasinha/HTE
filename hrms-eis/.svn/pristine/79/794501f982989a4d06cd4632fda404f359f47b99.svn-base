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
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.util.reports.SortingHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.DefaultComboItem;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstOffice;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillLoanDtls;
import com.tcs.sgv.loan.dao.LoanAdvMstDAOImpl;
import com.tcs.sgv.reports.dao.ReportParameterDAO;

public class RemarksReportDAO extends DefaultReportDataFinder implements ReportDataFinder
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
		logger.info("REMARKS REPORT DAO");
		
		
		
		
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

			if(report.getReportCode().equals("5000015"))
			{

				StringBuffer lsb = new StringBuffer();      
				
				
				String Department="";
				String month="";
				String year="";
				
				
				
				Department=CheckIfNull(report.getParameterValue( "Department" ));
				if(Department.equals("")||Department.equals("-1"))
					Department=	locationId+"";	
				else
					locationId=Long.parseLong(Department);
				
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				

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
					logger.info("BillNoBillNoBillNo" +BillNo);
					l++;
				} 
				
				String DeptName=locationNameincaps;
				
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				DeptName=payBillDAOImpl.getOffice(locationId);
				String TresuryCode =payBillDAOImpl.getTreasuryCode(locationId);
				
				
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

				
				
				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
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
						 
				
				Integer in = Integer.parseInt(month);
				
				String monthname = monthName[in-1];
				
				String deptHeader ="";
				String Title ="";
				
				Title = "Bill for Directorate of Accounts and Treasuries.-A,B,B N Gz,C,D-Both Permanent";
				
				if( BillNo != null && BillNo != "" )
				{
					
					 deptHeader=Title;
					 

				}
				else
				{
					
					deptHeader=Title; 

				}
				
			
				
				
				
				StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
				String Treasury =TresuryCode;
				styleList.add(stData);
				ArrayList r = new ArrayList();
				r.add(new StyledData("Bill Id("+BillNo+")"+"For the Month of "+monthname +year,leftHeader));
				styleList.add(r);  
				
				
				ArrayList r3= new ArrayList();
				r3.add(new StyledData("Name of the Office : "+DeptName,leftHeader));	
				styleList.add(r3);
				
				
				
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
				
				int currentMonthBill = 0;
				Date currentDate = new Date();

				if(currentDate.after(finYrDate) && currentDate.before(endMonthDate))
					currentMonthBill = 1;
				
				
				String query = new String();
				
				
				query = " select distinct emp.empId, Concat(emp.empFname,' ',emp.empMname,' ',emp.empLname),lookup.lookupName,remarks.remarks ";

				query+=" from OrgEmpMst emp,MstEmp mst,HrPayRemarksMst remarks,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
				query+=" OrgPostDetailsRlt postdtls,CmnLookupMst lookup,HrPayGpfBalanceDtls gpf,OrgDesignationMst dsgn,HrEisOtherDtls other,";
				query+=" HrPayPaybill paybill,MstDcpsBillGroup bill,PaybillHeadMpg head";
				
				
				query+=" where emp.empId=eisemp.orgEmpMst.empId and  emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";
				
				query+=" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId  ";
				
				query+=" and lookup.lookupId=remarks.cmnLookupMstForShortRemarks.lookupId and remarks.hrEisEmpMst.empId=eisemp.empId and postdtls.cmnLocationMst.locId="+locationId+" ";
				
				
				query+=" and gpf.userId=userpost.orgUserMst.userId and postdtls.orgDesignationMst.dsgnId=dsgn.dsgnId and other.hrEisEmpMst.empId=eisemp.empId ";
				query+=" and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and bill.dcpsDdoBillGroupId=head.billNo and head.hrPayPaybill=paybill.paybillGrpId ";
				query+=" and head.billTypeId=2500337 and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
				
				
				
				
				
				
				
				logger.info("***Query for Deduction details**" +query);
				
				logger.info("***Query for Remarks Report DAO details**" +query);
				
			
				
				
				Query sqlQuery = hibSession.createQuery(query);
				
				List RowList=sqlQuery.list();
				
				
				
				if(RowList.size()!=0)
				{
					ArrayList dataList=new ArrayList();
					int cnt=1;
					
					Iterator itr = RowList.iterator();
					
					String EmployeeId;
					String EmployeeName;
					String ShortRemarks;
					String Description;
					
					int pageCnt=1;

						while (itr.hasNext())
						{
							Object[] rowList = (Object[]) itr.next();
							EmployeeId = (rowList[0]!=null?rowList[0].toString():"").toString();
							EmployeeName = (rowList[1]!=null?rowList[1].toString():"").toString();
							ShortRemarks = (rowList[2]!=null?rowList[2]:"").toString();
							Description = (rowList[3]!=null?rowList[3]:"0").toString();
						
							ArrayList row = new ArrayList();
									
							String empidandemployeename = EmployeeName +" ("+EmployeeId+") ";
							
							
							logger.info("EmployeeName********* " + EmployeeName );
							logger.info("ShortRemarks********" + ShortRemarks );
							logger.info("Description*******" + Description );
							
							
							//StyleVO[] newStyles = new StyleVO[2];
							StyleVO[] NewVo = new StyleVO[3];
							NewVo[0] = new StyleVO();
							NewVo[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
							NewVo[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
							NewVo[1] = new StyleVO();
							NewVo[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
							NewVo[1].setStyleValue("Left"); 
							NewVo[2] = new StyleVO();
							NewVo[2].setStyleId(IReportConstants.BORDER);
							NewVo[2].setStyleValue("NO"); 
			    	        
			    	        
							ArrayList innerData = new ArrayList();
							ArrayList innerRow = new ArrayList();
							
							innerRow = new ArrayList();
							innerRow.add(EmployeeName);
							innerData.add(innerRow);
							
							innerRow = new ArrayList();
							innerRow.add(EmployeeId);
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(NewVo);
							
							
							
				row.add(td);
				row.add(ShortRemarks);
				row.add(Description);
				
				
			
				DataList.add(row);
				if((cnt%finalpagesize)==0) 
				{
					
					row = new ArrayList();
					row.add(new PageBreak());
					
					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(Description); 
					row.add(dataStyle1);
					
					dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(pageCnt);                   
					row.add(dataStyle1);//2
					
					
				
				}
				cnt++;
		}
						
			
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
		if(  report.getReportCode().equals("5000015") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}

		if(  report.getReportCode().equals("5000014") )

		{            
			report.setParameterValue("Department",locationId+"");
		}
		return report;
	}
}
