
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
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
@SuppressWarnings({ "unchecked", "deprecation" })
public class FormBReportDAO extends DefaultReportDataFinder implements ReportDataFinder
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
		
		//logger.info("EmployeeLoanHistroyDAO");
		int finalpagesize=20;

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		
		String locationNameincaps=locationName.toUpperCase();
		
		
		ArrayList DataList = new ArrayList();       
		
		
		

		
	
	
		ReportAttributeVO ravo1 = new ReportAttributeVO();
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_LOCATION);
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_ON_EACH_PAGE);
		ravo1.setAlignment(IReportConstants.HEADER_ALIGN_CENTER);
		report.addReportAttributeItem(ravo1);
		report.setAdditionalHeader("");
		
		
		
		try   
		{
			
			ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");	
			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
			SessionFactory sessionFactory = serviceLocator.getSessionFactory();
			Session hibSession = sessionFactory.getCurrentSession();
			
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
			

			
			
			String BillNo="";	    
			String portType="";
			
			


			if(report.getReportCode().equals("5000013"))
			{
		
				

				String Department="";
				String month="";
				String year="";
				
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
				
				logger.info("bill no is ***********"+BillNo);
				logger.info("bill no is ***********"+BillNoinner);
				logger.info("bill no is ***********"+report.getParameterValue( "Bill No" ));
				
				if (BillNoinner.trim().equals("") || BillNo.equalsIgnoreCase("") )
				{
					logger.info("inside if condition bill no is ***********"+report.getParameterValue( "Bill No" ));
					BillNo = report	.getParameterValue("Bill No") != null ? report.getParameterValue("Bill No").toString().trim() : "";
				}
				//Ended
				
				String DeptName=locationNameincaps;
				
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				DeptName=payBillDAOImpl.getOffice(locationId);
			
				long loggedInpostId = Long.parseLong(baseLoginMap.get("loggedInPost").toString());
				//added by roshan
				logger.info("hhii the bill number"+BillNo);
				
				locationId=payBillDAOImpl.getLocationCode(Long.parseLong(BillNo));
				logger.info("hhi the location cxod eis "+locationId);
				//ended by roshan
				
				String dsgnName =payBillDAOImpl.getDsgnNameForFormB(locationId,loggedInpostId);
				
				
				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				
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
				Calendar cale = Calendar.getInstance(); 
				String MONTH = monthName[cale.get(Calendar.MONTH)]; 	          
				Integer in = Integer.parseInt(month);
				String monthname = monthName[in-1];
		            
				logger.info("month****"+month);
				logger.info("MONTH****"+MONTH);
				logger.info("monthname****"+monthname);
				
				
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
				
				
				
				String FormbType=CheckIfNull(report.getParameterValue( "Formb Type" ));
				String PA="PA";
				String TA="TA";
				String FA="FA";
				
				String Title = "Schedule for the Recovery of PA";
				String Title1 = "Schedule for the Recovery of TA";
				String Title2 = "Schedule for the Recovery of FA";
				
				String MajorHead="Form B";
				
				String deptHeader ="";
				if(FormbType.equals(PA) )
				{
				if( BillNo != null && BillNo != "" )
				{
					
					 deptHeader=Title+System.getProperty("line.separator")+MajorHead;
					 

				}
				}
				
				else if(FormbType.equals(TA) )
				{
					
					deptHeader=Title1+System.getProperty("line.separator")+MajorHead; 

				}
				
				else 
				{
					
					deptHeader=Title2+System.getProperty("line.separator")+MajorHead; 

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
				
				ArrayList r1= new ArrayList();
				r1.add(new StyledData("Name of the Office : "+DeptName+"("+ddocode+")",leftHeader));
				r1.add(new StyledData(" ",leftHeader));
				styleList.add(r1);
				
				
				
				TabularData tData  = new TabularData(styleList);
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);
				
				
				/* String HQL_QUERY =  "select distinct sum(paybillLoan.totalAmt),sum(paybillLoan.recoveredAmt),hrloanemp.voucherNo,hrloanemp.loanDate";
				 if(FormbType.equals(FA) )
			 		HQL_QUERY+=",sum(paybillLoan.paybillId.adv5059)";
			 	else if(FormbType.equals(PA) )
			 		HQL_QUERY+=",sum(paybillLoan.paybillId.adv5052)";
			 	else 
			 		HQL_QUERY+=",sum(paybillLoan.paybillId.adv5053)";
			 	HQL_QUERY+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
			 	HQL_QUERY+=" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd,";
			 	HQL_QUERY+=" HrEisScaleMst scale,OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
			 	HQL_QUERY+=" HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde,HrPayPaybillLoanDtls paybillLoan,";
			 	HQL_QUERY+=" HrLoanEmpDtls hrloanemp";
			 	HQL_QUERY+=" where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";
			 	HQL_QUERY+=" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId ";
			 	HQL_QUERY+=" and other.hrEisEmpMst.empId=eisemp.empId and other.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId and dsgn.dsgnId=gd.orgDesignationMst.dsgnId";
			 	HQL_QUERY+=" and scale.scaleId=sgd.hrEisScaleMst.scaleId and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and userpost.orgPostMstByPostId.postId=payorder.postId";
			 	HQL_QUERY+=" and orde.orderHeadId=payorder.orderHeadId and paybill.orgPostMst.postId=payorder.postId and paybillLoan.paybillId.id=paybill.id";
			 	HQL_QUERY+=" and hrloanemp.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and hrloanemp.hrEisEmpMst.empId=eisemp.empId";
			 	if(FormbType.equals(FA) )
			 	    HQL_QUERY+=" and paybillLoan.hrLoanAdvMst.loanAdvId=59";
			 	if(FormbType.equals(PA) )
				 	HQL_QUERY+=" and paybillLoan.hrLoanAdvMst.loanAdvId=52";
			 	if(FormbType.equals(TA) )
				 	HQL_QUERY+=" and paybillLoan.hrLoanAdvMst.loanAdvId=53";
			 	HQL_QUERY+=" and head.billTypeId=2500337 and userpost.activateFlag=1 and hrloanemp.loanActivateFlag=1 and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
			 	HQL_QUERY+=" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locationId+" and head.hrPayPaybill=paybill.paybillGrpId ";
			 	*/
			 	
			 	
				
				String HQL_QUERY =  "select distinct sum(paybillLoan.totalAmt),sum(paybillLoan.recoveredAmt)";
				 if(FormbType.equals(FA) )
			 		HQL_QUERY+=",sum(paybillLoan.paybillId.adv5059)";
			 	else if(FormbType.equals(PA) )
			 		HQL_QUERY+=",sum(paybillLoan.paybillId.adv5052)";
			 	else 
			 		HQL_QUERY+=",sum(paybillLoan.paybillId.adv5053)";
				 
			 	HQL_QUERY+=" from HrLoanEmpDtls hrloanemp,MstDcpsBillGroup bill,PaybillHeadMpg head,";
			 	HQL_QUERY+=" HrPayPaybill paybill,HrPayPaybillLoanDtls paybillLoan";
			 	
			 	HQL_QUERY+=" where paybillLoan.paybillId.id=paybill.id and hrloanemp.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId";
			 	if(FormbType.equals(FA) )
			 	    HQL_QUERY+=" and paybillLoan.hrLoanAdvMst.loanAdvId=59";
			 	if(FormbType.equals(PA) )
				 	HQL_QUERY+=" and paybillLoan.hrLoanAdvMst.loanAdvId=52";
			 	if(FormbType.equals(TA) )
				 	HQL_QUERY+=" and paybillLoan.hrLoanAdvMst.loanAdvId=53";
			 	
			 	HQL_QUERY+=" and head.hrPayPaybill=paybill.paybillGrpId ";
			 	HQL_QUERY+=" and head.billTypeId=2500337 and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
			 	HQL_QUERY+=" and bill.dcpsDdoBillGroupId=head.billNo and  head.cmnLocationMst.locId="+locationId+" and paybill.hrEisEmpMst.empId is not null";
			 	
			 	
			 	logger.info("***Query for Deduction details**" +HQL_QUERY);
			 	logger.info("***Query for Deduction details**" +HQL_QUERY);
				//logger.info("***Query for Income Tax Deduction details**" +HQL_QUERY);
				Query sqlQuery = hibSession.createQuery(HQL_QUERY);
				
				
				List RowList=sqlQuery.list();
				long totalamountofloan=0;
				long totalamountofadv=0;
				long totalamountofoutstand=0;
				long totalAmountOfAdvRecovered=0;
				
				
				String row11 =null;
				String row22 =null;
				String row33 =null;
				for(int p=0;p<RowList.size();p++)
				{
					Object[] rowList = (Object[])RowList.get(p);
					
					if(rowList[0]!=null)
					 row11 = rowList[0].toString();
					if(rowList[1]!=null)
					  row22 = rowList[1].toString();
					if(rowList[2]!=null)
					  row33 = rowList[2].toString();
				}
				
				logger.info("list one value "+ row11);
				logger.info("list two value "+row22);
				logger.info("list three value"+row33);
				
				
				if( row11!=null && row22!=null && row33!=null )
				//if(RowList.size()!=0 && !RowList.isEmpty())
				{
					int cnt=1;
					
					Iterator itr = RowList.iterator();
					String totalAmount;
					String recoveredAmt;
					long Outstand=0;
					int pageCnt=1;
					double AmountOfAdvRecovered=0;

						while (itr.hasNext())
						{
							Object[] rowList = (Object[]) itr.next();
							
							totalAmount = (rowList[0]!=null?rowList[0]:"").toString();
							recoveredAmt =(rowList[1]!=null?rowList[1]:"").toString();
							//voucherNumber = (rowList[2]!=null?rowList[2]:"0").toString();		
							//voucherDate=(rowList[3]!=null?rowList[3]:"").toString();
							//AmountOfAdvRecovered=((rowList[4]!=null?rowList[4]:"").toString());
							
							if(rowList[2]!=null)
							{
							AmountOfAdvRecovered = ((Double)(rowList[2])).longValue();
							}
							/*if(voucherDate!=null && !voucherDate.equals(""))
							{
							voucherDate=sdf.format(sdformat.parse(voucherDate));
							}
							else
							{
								voucherDate="";
							}*/
							
							//logger.info("totalAmount************"+totalAmount);
							//logger.info("recoveredAmt************"+recoveredAmt);
							
							if(rowList[0]!=null && rowList[1]!=null)
							Outstand = Long.parseLong(totalAmount) - Long.parseLong(recoveredAmt);
							
							
							ArrayList row = new ArrayList();
							
				
				row.add(cnt);
				row.add(dsgnName);
				row.add("");
				row.add("");
				row.add(totalAmount);
				row.add(AmountOfAdvRecovered);
				row.add(Outstand);
				
				
				if(rowList[0]!=null)
				totalamountofloan+=Long.parseLong(totalAmount);
				totalAmountOfAdvRecovered+=AmountOfAdvRecovered;
				if(rowList[1]!=null)
				totalamountofadv+=Long.parseLong(recoveredAmt);
				totalamountofoutstand+=Outstand;
				
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					//logger.info("No Of Rec is*****************====>"+noOfRec);
					finalpagesize=Integer.parseInt(noOfRec);
				}
			
				DataList.add(row);
				if((cnt%finalpagesize)==0) 
				{
					
					pageCnt++;
					row = new ArrayList();
					row.add(new PageBreak());
					row.add("Data");
					DataList.add(row); 
					
					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(totalamountofoutstand);
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
						StyledData dataStyle1 = new StyledData();
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData("Total (`):");
						dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						row2.add(dataStyle1);
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(totalamountofloan); 
						row2.add(dataStyle1);
						
						 
						StyledData dataStyle22 = new StyledData();
						dataStyle22 = new StyledData();
						dataStyle22.setStyles(boldStyleVO);
						dataStyle22.setData(totalAmountOfAdvRecovered); 
						row2.add(dataStyle22);
						
						
						StyledData dataStyle3 = new StyledData();
						dataStyle3 = new StyledData();
						dataStyle3.setStyles(boldStyleVO);
						dataStyle3.setData(totalamountofoutstand);  
						row2.add(dataStyle3);
						row2.add("");
						
						
						DataList.add(row2);
						
						
						ArrayList row1 = new ArrayList();

						//logger.info("TOTALLLLLLLLLLLLLLL");
						
						StyleVO[] centerboldStyleVO = new StyleVO[2];
						centerboldStyleVO[0] = new StyleVO();
						centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
						centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
						centerboldStyleVO[1] = new StyleVO();
						centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
						centerboldStyleVO[1].setStyleValue("Left"); 
						
						
						StyleVO[] leftBoldStyleVO = new StyleVO[2];
						leftBoldStyleVO[0] = new StyleVO();
						leftBoldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
						leftBoldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
						leftBoldStyleVO[1] = new StyleVO();
						leftBoldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
						leftBoldStyleVO[1].setStyleValue("Left"); 
						
						
						StyledData dataStyle2 = new StyledData();
						dataStyle2.setStyles(centerboldStyleVO);
						dataStyle2.setData("Total Deduction In Words (`):  "+ConvertNumbersToWord.convert(Math.round(totalAmountOfAdvRecovered))+" Only.");
						dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						dataStyle2.setColspan(7);
						row1.add(dataStyle2);
						
						
						for(int c=0;c<7;c++)
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
		if(  report.getReportCode().equals("5000013") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			//added by ravysh
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}

		if(  report.getReportCode().equals("5000013") )

		{            
			report.setParameterValue("Department",locationId+"");
		}
		return report;
	}
}
