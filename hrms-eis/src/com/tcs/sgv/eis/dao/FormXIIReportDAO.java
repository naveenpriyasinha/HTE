package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
@SuppressWarnings({"unchecked"})
public class FormXIIReportDAO extends DefaultReportDataFinder implements ReportDataFinder {

	ResourceBundle locStrsBundle;
	private static Logger logger = Logger.getLogger(PayrollReportsDAO.class );



	private  StyleVO[] selfCloseVO=null;          
	
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
		

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		String locationNameincaps=locationName.toUpperCase();
		
		
		
		ArrayList DataList = new ArrayList();      
		
		
		

		
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
	logger.info("EmployeeLoanHistroyDAO111");
		
		
		ReportColumnVO[] newReportColumns = new ReportColumnVO[3];

		newReportColumns[0] = new ReportColumnVO();

		
		newReportColumns[0].setColumnId(1); 
		newReportColumns[0].setColumnHeader("");
		newReportColumns[0].setDataType(10);
		
		newReportColumns[0].setTableName("a");
		newReportColumns[0].setColumnName("asd"+1);
		newReportColumns[0].setDisplayTotal(0); 
		newReportColumns[0].setColumnLevel(1);	
		newReportColumns[0].setColumnWidth(3);

		
		for(int i=1;i<2;i++)
		{						  
		
		newReportColumns[i] = new ReportColumnVO();

		
		newReportColumns[i].setColumnId(i+1); 
		newReportColumns[i].setColumnHeader("");
		newReportColumns[i].setDataType(10);
		
		newReportColumns[i].setTableName("a");
		newReportColumns[i].setColumnName("asd"+i);
		newReportColumns[i].setDisplayTotal(0); 
		newReportColumns[i].setColumnLevel(1);	
		newReportColumns[i].setColumnWidth(30);
		 
		}
		
		newReportColumns[2] = new ReportColumnVO();

		
		newReportColumns[2].setColumnId(3); 
		newReportColumns[2].setColumnHeader("");
		newReportColumns[2].setDataType(10);
		
		newReportColumns[2].setTableName("a");
		newReportColumns[2].setColumnName("asd"+1);
		newReportColumns[2].setDisplayTotal(0); 
		newReportColumns[2].setColumnLevel(1);	
		newReportColumns[2].setColumnWidth(8);
		
		
		
		report.setReportColumns(newReportColumns);
		report.initializeDynamicTreeModel();
		report.initializeTreeModel();
		
		try   
		{
			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
			SessionFactory sessionFactory = serviceLocator.getSessionFactory();
			Session hibSession = sessionFactory.getCurrentSession();
			ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");	
			
			StyleVO[] boldStyleVO = new StyleVO[1];
			boldStyleVO[0] = new StyleVO();
			boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			StyleVO[] colorStyleVO = new StyleVO[1];
			colorStyleVO[0] = new StyleVO();
			colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			colorStyleVO[0].setStyleValue("blue");
			selfCloseVO = new StyleVO[1];
			selfCloseVO[0] = new StyleVO();
			selfCloseVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
			selfCloseVO[0].setStyleValue("javascript:self.close()"); 
			
			logger.info("getReportCode"+report.getReportCode());
			

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
			
			if(report.getReportCode().equals("5000026"))
			{


				StringBuffer lsb = new StringBuffer(  );      


				String portType="";
				String BillNo="";	    
				String desigName="";
				String deptName="";
				String Department="";
				String month="";
				String year="";
				String ReportType="";


				Department=CheckIfNull(report.getParameterValue( "Department" ));
				logger.info("DepartmentDepartmentDepartmentDepartment" + Department);
				if(Department.equals("")||Department.equals("-1"))
					Department=	locationId+"";	
				else
					locationId=Long.parseLong(Department);

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

				//Added by abhilash				
				if (BillNoinner.trim().equals("") || BillNo.equalsIgnoreCase("") )
				{
					logger.info("inside if condition bill no is ***********"+report.getParameterValue( "Bill No" ));
					BillNo = report	.getParameterValue("Bill No") != null ? report.getParameterValue("Bill No").toString().trim() : "";
				}
				//Ended
				//added by roshan
				logger.info("hhii the bill number"+BillNo);
				PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				locationId=payBillDAO.getLocationCode(Long.parseLong(BillNo));
				logger.info("hhi the location cxod eis "+locationId);
				//ended by roshan
				
				String DeptName=locationNameincaps;

				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				DeptName=payBillDAOImpl.getOffice(locationId);




				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				logger.info("month is ******"+month);
				String nextYear="";
				if(month.equals("12"))
					nextYear=(String.valueOf(Long.valueOf(year)+1));
				else
					nextYear=year;
				ReportType=CheckIfNull(report.getParameterValue( "Report Type" ));
				Calendar cal = Calendar.getInstance();

				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				cal.set(Calendar.DAY_OF_MONTH, totalDays);



				String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};        

				/*logger.info("MONTHMONTHMONTHMONTHMONTH" +MONTH);
				logger.info("monthmonthmonthmonth" +month);
				logger.info("curYearcurYearcurYearcurYearcurYear" +curYear);*/

				//int ii = Integer.getInteger(month);
				Integer in = Integer.parseInt(month);

				String monthname = monthName[in-1];
				logger.info("Value of IN is "+in);
				if(in == 12)
				{
					in = 0;
				}
				String nextMonth=monthName[in];



				String deptHeader =" FORM XII";

				//String Title ="";
				String Space=" ";

				//	Title = "Certificate Attached with the Paybill in respect of class IV Govt. Servants for the Month of "+month+Space+year;


				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();

				StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
				styleList.add(stData);
				//manish

				cal = Calendar.getInstance();
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));


				String query = new String();



				/*	logger.info("Inside ClassIV**********");
				query = " select distinct (gpf.gpfAccNo), concat(emp.empFname,' ',emp.empMname,' ',emp.empLname,'(',emp.empId,')'),";

				query+=" paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9583,paybill.allow1149,paybill.adv5055,hrloan.totalInst,hrloan.recoveredInst,paybill.allow1149 ";


				query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
				query+=" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd,";
				query+=" HrEisScaleMst scale,OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
				query+=" HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde,HrPayPaybillLoanDtls hrloan";

				query+=" where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";

				query+=" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId ";



				query+=" and other.hrEisEmpMst.empId=eisemp.empId and other.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and dsgn.dsgnId=gd.orgDesignationMst.dsgnId";

				query+=" and scale.scaleId=sgd.hrEisScaleMst.scaleId and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and userpost.orgPostMstByPostId.postId=payorder.postId";

				query+=" and orde.orderHeadId=payorder.orderHeadId and paybill.orgPostMst.postId=payorder.postId and hrloan.paybillId.id=paybill.id and hrloan.hrLoanAdvMst.loanAdvId=55 and head.billTypeId=2500337 ";

				query+=" and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";

				query+=" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locationId+" and head.hrPayPaybill=paybill.paybillGrpId ";
				 */



				query = " select sum(paybill.deduc9583),sum(paybill.adv5055)";
				query+=" from MstDcpsBillGroup bill,PaybillHeadMpg head,";
				query+=" HrPayPaybill paybill";
				query+=" where head.hrPayPaybill=paybill.paybillGrpId ";
				query+=" and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
				query+=" and bill.dcpsDdoBillGroupId=head.billNo and head.cmnLocationMst.locId="+locationId+" and paybill.hrEisEmpMst.empId is not null ";



				logger.info("***Query for FormXII is********" +query);

				Query sqlQuery = hibSession.createQuery(query);

				List RowList=sqlQuery.list();

				logger.info("***Query for row list size is*************" +RowList.size());



				if(RowList.size()!=0)
				{
					int cnt=1;

					Iterator itr = RowList.iterator();
					double RefundAmount=0;
					double SubScribe=0;
					double GrandSubScribe=0;
					double GrandRefundAmount=0;
					double totalArrear=0;

					while (itr.hasNext())
					{
						Object[] rowList = (Object[]) itr.next();
						/*GPFActNo = (rowList[0]!=null?rowList[0].toString():"").toString();
							employeeName = (rowList[1]!=null?rowList[1]:"").toString();
							PO = Double.parseDouble((rowList[2]!=null?rowList[2]:"").toString());
							PE = Double.parseDouble((rowList[3]!=null?rowList[3]:"0").toString());
							double DP= Double.parseDouble((rowList[4]!=null?rowList[4]:"").toString());
							SubScribe = Double.parseDouble((rowList[5]!=null?rowList[5]:"").toString());
							RefundAmount = Double.parseDouble((rowList[7]!=null?rowList[7]:"").toString());
							String TotalInst = (rowList[8]!=null?rowList[8]:"").toString();
							String CurrInst = (rowList[9]!=null?rowList[9]:"").toString();
							arrear=Double.parseDouble((rowList[10]!=null?rowList[10]:"").toString());
							logger.info("PE**********"+(rowList[2]!=null?rowList[2]:"0").toString());
							logger.info("P0**********"+(rowList[3]!=null?rowList[3]:"").toString());
							Pay= PE+PO;
							long PAY = (new Double(Pay)).longValue();
							long dp = (new Double(DP)).longValue();
							logger.info("PAY********"+PAY);
							Total=Pay+SubScribe+DP+PayDAArrMerge+GPFArr+RefundAmount;
							String Remarks="";
							String space="    ";
							StringBuffer stringBuffer = new StringBuffer();
							stringBuffer.append(PAY);
							stringBuffer.append(space);
							stringBuffer.append(dp);
							logger.info("stringBuffer******" +stringBuffer);
							logger.info("Total********* " + Total );
							GrandSubScribe+=SubScribe;
							GrandRefundAmount+=RefundAmount;
							GrandTotal+=Total;
							totalArrear+=arrear;*/

						SubScribe = Double.parseDouble((rowList[0]!=null?rowList[0]:"").toString());
						RefundAmount = Double.parseDouble((rowList[1]!=null?rowList[1]:"").toString());

						GrandSubScribe+=SubScribe;
						GrandRefundAmount+=RefundAmount;



						cnt++;
					}

					double total=GrandSubScribe+GrandRefundAmount+totalArrear;

					StyleVO[] styleVO = new StyleVO[2];
					styleVO[0] = new StyleVO();
					styleVO[0].setStyleId(IReportConstants.STYLE_FONT_FAMILY);
					styleVO[0].setStyleValue("Rupee Foradian");
					
					ArrayList r = new ArrayList();
					r.add(new StyledData("Certificate Attached with the Paybill in respect of class IV Govt. Servants for the Month of "+monthname +Space+year,leftHeader));
					r.add(new StyledData(" ",rightHead));
					styleList.add(r);  

					ArrayList r1= new ArrayList();
					r1.add(new StyledData(System.getProperty("line.separator")+"Paid in "+nextMonth + Space+nextYear +" for the office of "+DeptName,leftHeader));
					r1.add(new StyledData(" ",rightHead));
					styleList.add(r1);


					ArrayList r2= new ArrayList();
					r2.add(new StyledData(System.getProperty("line.separator")+"Certified that the sum of (`) "+Math.round(total) +" has been deducted from the salary for the month of "+monthname +Space+year,leftHeader));
					//r2.add(styleVO);
					r2.add(new StyledData(" ",rightHead));
					styleList.add(r2);


					TabularData tData  = new TabularData(styleList);
					tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
					tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
					tData.addStyle(IReportConstants.BORDER, "No");
					tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
					report.setAdditionalHeader(tData);

					ArrayList row= new ArrayList();
					StyledData sd = new StyledData();
					sd.addStyle(IReportConstants.BORDER,"NO");
					sd.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					sd.setColspan(3);
					sd.setData("1. Subscription...................... :"+Math.round(GrandSubScribe));

					row.add(sd);
					DataList.add(row);

					row= new ArrayList();


					sd = new StyledData();
					sd.addStyle(IReportConstants.BORDER,"NO");
					sd.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					sd.setColspan(3);
					sd.setData("2. Refund of Withdrawal........:"+Math.round(GrandRefundAmount));

					row.add(sd);
					DataList.add(row);

					row= new ArrayList();




					sd = new StyledData();
					sd.addStyle(IReportConstants.BORDER,"NO");
					sd.addStyle(IReportConstants.WITHOUT_BORDER_STYLES_STRING,"YES");
					sd.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					sd.setColspan(3);
					sd.setData("3. DA Arrears.........................:"+Math.round(totalArrear));

					row.add(sd);
					DataList.add(row);


					row= new ArrayList();



					sd = new StyledData();
					sd.addStyle(IReportConstants.BORDER,"NO");
					sd.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					sd.setColspan(3);
					sd.setData("Total (`).................................:"+Math.round(total));
					sd.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
					row.add(sd);
					DataList.add(row);


					row= new ArrayList();

					sd = new StyledData();
					sd.addStyle(IReportConstants.BORDER,"NO");
					sd.addStyle(IReportConstants.WITHOUT_BORDER_STYLES_STRING,"YES");

					sd.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					sd.setStyles(boldStyleVO);
					sd.setColspan(3);
					sd.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
					sd.setData("Total Deduction In Words (`) :"+ConvertNumbersToWord.convert(Math.round(total))+" Only.");

					row.add(sd);
					DataList.add(row);


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
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat fmt1 =new SimpleDateFormat("yyyy");
		String yr = fmt1.format(today);
		fmt1 =new SimpleDateFormat("MM");
		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		long locationId=locationVO.getLocId();
		String month = fmt1.format(today);

		if(month.charAt(0)=='0')
		{
			month=month.substring(1,2);
		}
		if(  report.getReportCode().equals("5000026") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}

		if(  report.getReportCode().equals("5000026") )

		{            
			report.setParameterValue("Department",locationId+"");
		}
		return report;
}
}
