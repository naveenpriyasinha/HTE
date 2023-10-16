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
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
@SuppressWarnings({"unchecked"})
public class CoHsgSocReportDAO extends DefaultReportDataFinder implements ReportDataFinder
{
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
		
		int finalpagesize=20;

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
			
			
			//logger.info("getReportCode"+report.getReportCode());
			
			
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
			
			if(report.getReportCode().equals("5000016"))
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
				
				String loan_type="";
				loan_type=CheckIfNull(report.getParameterValue( "Type" ));
				
				
				Department=CheckIfNull(report.getParameterValue( "Department" ));
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
					l++;
				} 

				//Added by abhilash				
				if (BillNoinner.trim().equals("") || BillNo.equalsIgnoreCase("") )
				{
					logger.info("inside if condition bill no is ***********"+report.getParameterValue( "Bill No" ));
					BillNo = report	.getParameterValue("Bill No") != null ? report.getParameterValue("Bill No").toString().trim() : "";
				}
				//Ended
				
				String DeptName=locationNameincaps;
				
				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
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
				
				
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				//added by roshan
				logger.info("hhii the bill number"+BillNo);
				
				locationId=payBillDAOImpl.getLocationCode(Long.parseLong(BillNo));
				logger.info("hhi the location cxod eis "+locationId);
				//ended by roshan
				DeptName=payBillDAOImpl.getOffice(locationId);
			
				
				/*List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);
				
				String ddocode ="";
				if(ddoList.size()>0)
					ddocode = ddoList.get(0).getDdoCode();*/
				String ddocode ="";
				long locactionId=Long.parseLong(baseLoginMap.get("locationId").toString());
				PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locactionId);
	    		if(ddoCodeList!=null)
	    		 logger.info("After query execution for DDO Code " + ddoCodeList.size());
	    		
	    		OrgDdoMst ddoMst = null; 
	    		if(ddoCodeList!=null && ddoCodeList.size()>0){
	    			ddoMst = ddoCodeList.get(0);
	    		}
	    		
	    		if(ddoMst!=null) {
	    			ddocode=ddoMst.getDdoCode();
	    		}
	    		logger.info("DDO Code is " + ddocode);
				
				DeptName=payBillDAOImpl.getOffice(locationId);
				
				String TresuryName ="";
				String TresuryCode ="";
				List TreasuryList = payBillDAOImpl.getTreasuryCodeAndTreasuryName(ddocode);
				
				if(TreasuryList!=null && TreasuryList.size()!=0)
				{
					for(Iterator itr=TreasuryList.iterator();itr.hasNext();)
					{

						Object[] rowList = (Object[])itr.next();

						if(rowList[0] != null)
						{
							TresuryName = rowList[0].toString().trim();
						}
						if(rowList[1] != null)
						{
							TresuryCode = rowList[1].toString().trim();
						}

					}
				}
				
				
				
				
				
				String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};        
						 
				
				//int ii = Integer.getInteger(month);
				Integer in = Integer.parseInt(month);
				
				//logger.info("value of i:: "+in);
				
				//logger.info("monthName[i-1]"+monthName[in-1]);
				
				String monthname = monthName[in-1];
				
				
				String deptHeader ="";
				String Title ="";
				String MajorHead = "";
				if(loan_type.equalsIgnoreCase("P"))
					MajorHead="6216";
				if(loan_type.equalsIgnoreCase("I"))
					MajorHead="0049";
				
				
				Title = "Schedule for the Recovery of Co.Hsg.Soc";
				
				if( BillNo != null && BillNo != "" )
				{
					
					 deptHeader=Title+System.getProperty("line.separator")+"From Major Head "+MajorHead;
					 

				}
				else
				{
					
					deptHeader=Title+System.getProperty("line.separator")+"From Major Head "+MajorHead; 

				}
				
			
				
				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();
				
				StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
				String Treasury =TresuryCode;
				styleList.add(stData);
				ArrayList r = new ArrayList();
				r.add(new StyledData("For the Month of "+monthname +" "+year,leftHeader));
				r.add(new StyledData("Treasury : "+TresuryName+"("+Treasury+")",rightHead));
				styleList.add(r);  
				
				String space =" ";
				ArrayList r3= new ArrayList();
				r3.add(new StyledData("Name of the Office : "+DeptName+"("+ddocode+")	",leftHeader));
				r3.add(new StyledData(" "+space,rightHead));
				styleList.add(r3);
				
				
				ArrayList r4= new ArrayList();
				r4.add(new StyledData("Co-Operative Society :",leftHeader));	
				r4.add(new StyledData(" ",leftHeader));	
				styleList.add(r4);
				
				
				TabularData tData  = new TabularData(styleList);
				
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");
				
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);
				
				
				cal = Calendar.getInstance();
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.set(Calendar.YEAR,Integer.parseInt( year));
				cal.set(Calendar.MONTH,Integer.parseInt( month)-1);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));

				
				
				String query = new String();
				
				if(loan_type.equals("P"))
				{
				/*query = " select distinct concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),eisemp.sevarthEmpCode,";
				query+=" dsgn.dsgnName,paybill.basic0101,paybill.basic0102,hrloan.totalAmt,loan.totalRecoveredAmt,hrloan.recoveredInst,paybill.loan5061 ";
				
				query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
				query+=" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd,";
				query+=" HrEisScaleMst scale,OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
				query+=" HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde,HrPayPaybillLoanDtls hrloan,HrLoanEmpDtls loanemp,HrLoanEmpPrinRecoverDtls loan";
				
				query+=" where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";
				query+=" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId ";
				query+=" and other.hrEisEmpMst.empId=eisemp.empId and other.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId and dsgn.dsgnId=gd.orgDesignationMst.dsgnId";
				query+=" and scale.scaleId=sgd.hrEisScaleMst.scaleId and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and userpost.orgPostMstByPostId.postId=payorder.postId";
				query+=" and orde.orderHeadId=payorder.orderHeadId and paybill.orgPostMst.postId=payorder.postId and hrloan.paybillId.id=paybill.id and hrloan.hrLoanEmpDtls.empLoanId=loanemp.empLoanId and loan.hrLoanEmpDtls.empLoanId=hrloan.hrLoanEmpDtls.empLoanId and loanemp.hrEisEmpMst.empId=eisemp.empId and hrloan.hrLoanAdvMst.loanAdvId=61  and head.billTypeId=2500337 ";
				query+=" and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
				query+=" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locationId+" and head.hrPayPaybill=paybill.paybillGrpId ";			
				*/
				
				query = " select concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),eisemp.sevarthEmpCode,";
				query+=" dsgn.dsgnName,paybill.basic0101,paybill.basic0102,hrloan.totalAmt,loan.totalRecoveredAmt,hrloan.recoveredInst,paybill.loan5061,hrloan.recoveredAmt ";
				
				query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgPostDetailsRlt postdtls,OrgDesignationMst dsgn,";
				query+=" MstDcpsBillGroup bill,PaybillHeadMpg head,HrPayPaybill paybill,";
				query+=" HrPayPaybillLoanDtls hrloan,HrLoanEmpDtls loanemp,HrLoanEmpPrinRecoverDtls loan";
				
				query+=" where emp.empId=eisemp.orgEmpMst.empId and eisemp.empId=paybill.hrEisEmpMst.empId";
				query+=" and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId and postdtls.orgPostMst.postId=paybill.orgPostMst.postId";
				query+=" and bill.dcpsDdoBillGroupId=head.billNo and head.billNo="+BillNo+" ";
				query+=" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1) ";
				query+=" and head.hrPayPaybill=paybill.paybillGrpId and hrloan.hrLoanEmpDtls.empLoanId=loanemp.empLoanId ";
				query+=" and loan.hrLoanEmpDtls.empLoanId=hrloan.hrLoanEmpDtls.empLoanId and loanemp.hrEisEmpMst.empId=eisemp.empId   ";
				query+=" and hrloan.paybillId.id=paybill.id and hrloan.hrLoanAdvMst.loanAdvId=61 ";
				//query+=" and paybill.cmnLocationMst.locId="+locationId+" ";
				
				}
				else
				{
					/*
					query = " select distinct concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),eisemp.sevarthEmpCode,";
					query+=" dsgn.dsgnName,paybill.basic0101,paybill.basic0102,hrloan.totalAmt,loan.totalRecoveredInt,hrloan.recoveredInst,paybill.loanInt5061 ";
					
					
					query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
					query+=" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd,";
					query+=" HrEisScaleMst scale,OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
					query+=" HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde,HrPayPaybillLoanDtls hrloan,HrLoanEmpDtls loanemp,HrLoanEmpIntRecoverDtls loan";
					
					query+=" where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";
					query+=" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId ";
					query+=" and other.hrEisEmpMst.empId=eisemp.empId and other.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId and dsgn.dsgnId=gd.orgDesignationMst.dsgnId";
					query+=" and scale.scaleId=sgd.hrEisScaleMst.scaleId and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and userpost.orgPostMstByPostId.postId=payorder.postId";
					query+=" and orde.orderHeadId=payorder.orderHeadId and paybill.orgPostMst.postId=payorder.postId and hrloan.paybillId.id=paybill.id and hrloan.hrLoanEmpDtls.empLoanId=loanemp.empLoanId and loan.hrLoanEmpDtls.empLoanId=hrloan.hrLoanEmpDtls.empLoanId and loanemp.hrEisEmpMst.empId=eisemp.empId and hrloan.hrLoanAdvMst.loanAdvId=74  and head.billTypeId=2500337 ";
					query+=" and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
					query+=" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locationId+" and head.hrPayPaybill=paybill.paybillGrpId ";
				*/
					
					
					query = " select concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),eisemp.sevarthEmpCode,";
					query+=" dsgn.dsgnName,paybill.basic0101,paybill.basic0102,hrloan.totalAmt,loan.totalRecoveredInt,hrloan.recoveredInst,paybill.loanInt5061 ,hrloan.recoveredAmt";
					
					query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgPostDetailsRlt postdtls,OrgDesignationMst dsgn,";
					query+=" MstDcpsBillGroup bill,PaybillHeadMpg head,HrPayPaybill paybill,";
					query+=" HrPayPaybillLoanDtls hrloan,HrLoanEmpDtls loanemp,HrLoanEmpIntRecoverDtls loan";
					
					query+=" where emp.empId=eisemp.orgEmpMst.empId and eisemp.empId=paybill.hrEisEmpMst.empId";
					query+=" and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId and postdtls.orgPostMst.postId=paybill.orgPostMst.postId";
					query+=" and bill.dcpsDdoBillGroupId=head.billNo and head.billNo="+BillNo+" ";
					query+=" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1) ";
					query+=" and head.hrPayPaybill=paybill.paybillGrpId and hrloan.hrLoanEmpDtls.empLoanId=loanemp.empLoanId ";
					query+=" and loan.hrLoanEmpDtls.empLoanId=hrloan.hrLoanEmpDtls.empLoanId and loanemp.hrEisEmpMst.empId=eisemp.empId   ";
					query+=" and hrloan.paybillId.id=paybill.id and hrloan.hrLoanAdvMst.loanAdvId=74 ";
					//query+=" and paybill.cmnLocationMst.locId="+locationId+" ";
				
				}
				
				
				logger.info("***Query for CoHsgSocReportDAO details**" +query);
				
				
				
				Query sqlQuery = hibSession.createQuery(query);
				
				List RowList=sqlQuery.list();
				
				
				if(RowList.size()!=0)
				{
					int cnt=1;
					
					Iterator itr = RowList.iterator();
					
					String EmployeeName;
					String empId;
					String dsgnName;
					String TotalName;
					double Pay=0;
					double PE=0;
					double PO =0;
					double AmountOfLoan=0;
					double TotalRecoEndOTM=0;
					double loanPrincipleEmiAmt=0;
					double Balance=0;
					String Remarks="";
					double GrandloanPrincipleEmiAmt=0;
					
					
					int pageCnt=1;

						while (itr.hasNext())
						{
							Object[] rowList = (Object[]) itr.next();
							EmployeeName = (rowList[0]!=null?rowList[0].toString():"").toString();
							empId = (rowList[1]!=null?rowList[1]:"").toString();
							dsgnName = (rowList[2]!=null?rowList[2]:"").toString();
							PE = Double.parseDouble((rowList[3]!=null?rowList[3]:"0").toString());
							PO = Double.parseDouble((rowList[4]!=null?rowList[4]:"0").toString());		
							AmountOfLoan = Double.parseDouble((rowList[5]!=null?rowList[5]:"0").toString());
							loanPrincipleEmiAmt= Double.parseDouble((rowList[8]!=null?rowList[8]:"0").toString());
							TotalRecoEndOTM= Double.parseDouble((rowList[9]!=null?rowList[9]:"0").toString());
							
							//TotalRecoEndOTM=TotalInstallMent*loanPrincipleEmiAmt;
							
							GrandloanPrincipleEmiAmt+=loanPrincipleEmiAmt;
							
							
							Balance=AmountOfLoan-TotalRecoEndOTM;
							
							ArrayList row = new ArrayList();
									
							String space1 = "        ";
							TotalName = EmployeeName +space1+dsgnName;
							String EmployeeId="("+empId+")";
							Pay= PE+PO;
							
							
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
							innerRow.add(TotalName);
							innerData.add(innerRow);
							
							innerRow = new ArrayList();
							innerRow.add(EmployeeId);
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(NewVo);
							
							
							
				row.add(cnt);
				row.add(td);
				row.add(Pay);
				row.add(AmountOfLoan);
				row.add(AmountOfLoan);
				row.add(loanPrincipleEmiAmt);
				row.add(TotalRecoEndOTM);
				row.add(Balance);
				row.add(Remarks);
				
				
			
				DataList.add(row);
				
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					//logger.info("No Of Rec is*****************====>"+noOfRec);
					finalpagesize=Integer.parseInt(noOfRec);
				}
				
				if((cnt%finalpagesize)==0) 
				{
					pageCnt++;
					row = new ArrayList();
					row.add(new PageBreak());
					row.add("Data");
					DataList.add(row);
					
					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(GrandloanPrincipleEmiAmt); 
					row.add(dataStyle1);
					
					dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(pageCnt);                   
					row.add(dataStyle1);//2
					
					
				
				}
				cnt++;
		}
						ArrayList row2 = new ArrayList();
						row2.add("");
						row2.add("");
						row2.add("");
						row2.add("");
						StyledData dataStyle1 = new StyledData();
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData("TOTAL (`)");
						dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						row2.add(dataStyle1);
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(GrandloanPrincipleEmiAmt); 
						row2.add(dataStyle1);
						row2.add("");
						row2.add("");
						row2.add("");
						
						DataList.add(row2);
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
						dataStyle2.setData("Total Deduction In Words (`):  "+ConvertNumbersToWord.convert(Math.round(GrandloanPrincipleEmiAmt))+" Only.");
						dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						dataStyle2.setColspan(9);
						row1.add(dataStyle2);
						
						
						for(int c=0;c<9;c++)
							row1.add("");
						DataList.add(row1);
			
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
		//logger.info ("***********Inside User Report VO *********************");
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
		if(  report.getReportCode().equals("5000016") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}

		if(  report.getReportCode().equals("5000016") )

		{            
			report.setParameterValue("Department",locationId+"");
		}
		return report;
	}
}
