package com.tcs.sgv.dcps.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valuebeans.reports.URLData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PayrollReportsDAO;
import com.tcs.sgv.eis.dao.TokenNumberDAOImpl;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class AccidentalInsuranceReport extends DefaultReportDataFinder implements ReportDataFinder {
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


			if(report.getReportCode().equals("8000200"))
			{

				String BillNo="";	
				String month="";
				String year="";
			
				BillNo = report	.getParameterValue("BillNo") != null ? report.getParameterValue("BillNo").toString().trim() : "";
				logger.info("BillNo123 123aaaa--"+BillNo);

				String DeptName=locationNameincaps;
				logger.info("DeptName 123aaaa--"+DeptName);
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		
				long locactionId=Long.parseLong(baseLoginMap.get("locationId").toString());
				logger.info("locactionId**********" + locactionId);
				long locIdForBill=locactionId;
				logger.info("locIdForBill**********" + locIdForBill);
				PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				
				String ddoName="";
				String ddoDesignation="";
				DeptName=payBillDAOImpl.getOffice(locationId);
				
				StringBuilder ddoDesignationQuery=new StringBuilder();
				ddoDesignationQuery.append("SELECT ddo.DDO_NAME,design.DSGN_NAME FROM org_ddo_mst ddo  inner join ORG_POST_DETAILS_RLT post on ddo.POST_ID = post.POST_ID  ");
				ddoDesignationQuery.append(" inner join ORG_DESIGNATION_MST design on post.DSGN_ID = design.dsgn_id where ddo.LOCATION_CODE = "+locactionId+" ");
				Query ddoDesignationSqlQuery = hibSession.createSQLQuery(ddoDesignationQuery.toString());
				logger.info("ddoDesignationSqlQuery"+ddoDesignationSqlQuery);
				List ddoDesignationList=ddoDesignationSqlQuery.list();
				logger.info("ddoDesignationSqlQuery size"+ddoDesignationList.size());			
				if(ddoDesignationList!=null && ddoDesignationList.size()!=0)
				{
					for(Iterator itr=ddoDesignationList.iterator();itr.hasNext();)
					{

						Object[] rowList = (Object[])itr.next();

						if(rowList[0] != null)
						{
							ddoName = rowList[0].toString().trim();
							logger.info("ddoName**********" + ddoName);
						}
						if(rowList[1] != null)
						{
							ddoDesignation = rowList[1].toString().trim();
							logger.info("ddoDesignation**********" + ddoDesignation);
						}

					}
				}

				month=CheckIfNull(report.getParameterValue( "Month" ));
				int tempMonth = Integer.parseInt(month);
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
				Integer in = Integer.parseInt(month);
				String monthname = monthName[in-1];

				String Title = "State Government Employees Group Indivdual Accidental Insurance Scheme(8121507501)";		

				String subTitle="";
				String deptHeader ="";

				if( BillNo != null && BillNo != "" )
				{
					deptHeader=Title+System.getProperty("line.separator")+"Statment Showing deduction of GroupWise Employees from paybill";
				}
				logger.info("tempMonth**********" +tempMonth);
				//added by Diamond for renaming Service tax column name:Start
				String columnName = null;
				if(tempMonth >=8 && year.equals("2017"))
				{
					logger.info("tempMonth***in if*******" +tempMonth);
					
					ReportColumnVO[] col=report.getColumnsToDisplay();
				
				   for (int counter = 0; counter < col.length; counter++) { 
							 
							 columnName = col[4].getColumnHeader();
							 logger.info("columnName**********" +columnName);
							 col[4].setColumnHeader("Goods and Services Tax (18%)");
						}   	
						
					report.initializeTreeModel();
					report.initializeDynamicTreeModel();
					
				}
				//added by Diamond for renaming Service tax column name:End

				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();

				StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
			
				styleList.add(stData);
				ArrayList r = new ArrayList();
				r.add(new StyledData("For the Month of "+monthname +" " +year,leftHeader));				
				styleList.add(r);  

				ArrayList r2= new ArrayList();
				r2.add(new StyledData("Name of the Office : "+DeptName+"	",leftHeader));	
				r2.add(new StyledData(" ",rightHead));
				styleList.add(r2);

				ArrayList r3= new ArrayList();
				r3.add(new StyledData("DDO Name And Designation : "+ddoName+','+ddoDesignation,leftHeader));	
				r3.add(new StyledData(" ",rightHead));	
				styleList.add(r3);
				
				ArrayList r4= new ArrayList();
				r4.add(new StyledData("Name of Controlling Officer and Designation : ",leftHeader));	
				r4.add(new StyledData(" ",rightHead));	
				styleList.add(r4);
				ArrayList r5= new ArrayList();
				r5.add(new StyledData("Details Of Contribution : ",leftHeader));	
				r5.add(new StyledData(" ",rightHead));	
				styleList.add(r5);

				
				
				TabularData tData  = new TabularData(styleList);				
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);

				NewRegDdoDAOImpl regDao = new NewRegDdoDAOImpl(null,serv.getSessionFactory()); 
				String billCreationDate =  regDao.getBillCreationDate(BillNo,month,year);
				StringBuilder empCountGroupWiseQuery=new StringBuilder();
				empCountGroupWiseQuery.append(" SELECT  count(case when eis.GRADE_ID in (100001) then 1 else null end) as A,count(case when eis.GRADE_ID in (100064) then 1 else null end) as B ,count(case when eis.GRADE_ID in (100065) then 1 else null end) as Bnz ,count(case when eis.GRADE_ID in (100066) then 1 else null end) as C,  ");
				empCountGroupWiseQuery.append(" count(case when eis.GRADE_ID in (100067) then 1 else null end) as D,count(case when eis.GRADE_ID in (100115,100001) or eis.GRADE_ID is null then 1 else null end) as NA,paybill.ACC_POLICY  FROM org_emp_mst eis   ");				
				empCountGroupWiseQuery.append(" inner join HR_EIS_EMP_MST mst on mst.EMP_MPG_ID = eis.EMP_ID  ");
				empCountGroupWiseQuery.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=mst.EMP_ID  ");
				empCountGroupWiseQuery.append("inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
				empCountGroupWiseQuery.append(" inner join ORG_GRADE_MST grade on grade.GRADE_ID = eis.GRADE_ID ");
				empCountGroupWiseQuery.append("  where head.PAYBILL_MONTH = "+month+" and head.PAYBILL_YEAR = "+year+" and head.APPROVE_FLAG in (0,5,1) and BILL_NO = '"+BillNo+"' and paybill.ACC_POLICY > 0 ");
				empCountGroupWiseQuery.append(" group by paybill.ACC_POLICY ");
				Query empCountGroupWiseSQLQuery = hibSession.createSQLQuery(empCountGroupWiseQuery.toString());
				logger.info("empCountGroupWiseSQLQuery"+ddoDesignationSqlQuery);
				List empCountGroupWiseList=empCountGroupWiseSQLQuery.list();
				logger.info("empCountGroupWiseSQLQuery size"+empCountGroupWiseList.size());
				
long groupACount =0l ;
long groupBCount =0l ;
long groupBnzCount =0l;
long groupCCount = 0l;
long groupDCount = 0l;
long accPolicy = 01;
			if(empCountGroupWiseList!=null && empCountGroupWiseList.size()>0){
				for(Iterator itr=empCountGroupWiseList.iterator();itr.hasNext();)
				{

					Object[] rowList = (Object[])itr.next();

					if(Long.parseLong((String) rowList[0].toString())!= 0)
					{
						groupACount = Long.parseLong(rowList[0].toString());
					}
					if(Long.parseLong(rowList[1].toString())!= 0)
					{
						groupBCount = Long.parseLong(rowList[1].toString());
					}
					if(Long.parseLong(rowList[2].toString())!= 0)
					{
						groupBnzCount = Long.parseLong(rowList[2].toString());
					}

					if(Long.parseLong(rowList[3].toString())!= 0)
					{
						groupCCount = Long.parseLong(rowList[3].toString());
					}
					if(Long.parseLong(rowList[4].toString())!= 0 )
					{
						groupDCount = Long.parseLong(rowList[4].toString());
					}
					if(rowList[6] != null)
					{
						accPolicy = Long.parseLong(rowList[6].toString());
						logger.info("accPolicy"+accPolicy);
						
					}
				}
				
			}
			long totalEmpCount=0l;
			long totalContribution=0l;
			long totalServiceTax=0l;
			long totalAmount=0l;
			Long gradeIdB=100064l;
			Long gradeIdA=100001l;
			long gradeIdC =100066l;
			long gradeIdD =100067l;
			long gradeIdBnz =100065l;
			long contriAmt=300l;
			long contriAAmt=750l;
			long contriBAmt=600l;
			long contriCAmt=450l;
			long contriDAmt=450l;
			long serviceTax=0l;
			long serviceRate=18l;
			long totalContriServiceAmt = 0l;
			
			long contriAmtA = 0l;
			long contriAmtB = 0l;
			long contriAmtC = 0l;
			long contriAmtD = 0l;
			long contriAmtBnz = 0l;
			
			long serviceTaxA = 0l;
			long serviceTaxB = 0l;
			long serviceTaxBnz = 0l;
			long serviceTaxC = 0l;
			long serviceTaxD = 0l;
											
			long totalContriServiceAmtA = 0l;
			long totalContriServiceAmtB = 0l;
			long totalContriServiceAmtBnz = 0l;
			long totalContriServiceAmtC = 0l;
			long totalContriServiceAmtD = 0l;
					
			if(Integer.parseInt(year)<=2022) {
				if(accPolicy==354){
					serviceTax=54;
					totalContriServiceAmt=354;
					  logger.info("Inside if serviceTax"+serviceTax); 
					
				}
				else if(accPolicy==345)
				{
					serviceTax=45;
					totalContriServiceAmt=345;
					logger.info("Inside else serviceTax"+serviceTax); 
				}
				else
				{
					serviceTax=44;
				totalContriServiceAmt=344;
				}
				serviceTaxA=serviceTax;
				serviceTaxB=serviceTax;
				serviceTaxBnz=serviceTax;
				serviceTaxC=serviceTax;
				serviceTaxD=serviceTax;
				totalContriServiceAmtA=totalContriServiceAmt;
				totalContriServiceAmtB=totalContriServiceAmt;
				totalContriServiceAmtBnz=totalContriServiceAmt;
				totalContriServiceAmtC=totalContriServiceAmt;
				totalContriServiceAmtD=totalContriServiceAmt;
				logger.info("serviceTax "+serviceTax);
				logger.info("totalContriServiceAmt "+totalContriServiceAmt);
				
				contriAmtA = groupACount*contriAmt;
				contriAmtB = groupBCount*contriAmt;
				contriAmtC = groupCCount*contriAmt;
				contriAmtD = groupDCount*contriAmt;
				contriAmtBnz = groupBnzCount*contriAmt;
				
			}else if(Integer.parseInt(year)>2022) {
				// service tax calculation
				 serviceTaxA=contriAAmt*serviceRate/100;
				 serviceTaxB=contriBAmt*serviceRate/100;
				 serviceTaxBnz=contriBAmt*serviceRate/100;
				 serviceTaxC=contriCAmt*serviceRate/100;
				 serviceTaxD=contriDAmt*serviceRate/100;
					// Gross Total contribution class wise + service
				  totalContriServiceAmtA=contriAAmt+serviceTaxA;
				  totalContriServiceAmtB=contriBAmt+serviceTaxB;
				  totalContriServiceAmtBnz=contriBAmt+serviceTaxBnz;
				  totalContriServiceAmtC=contriCAmt+serviceTaxC;
				  totalContriServiceAmtD=contriDAmt+serviceTaxD;
				// Total contribution class wise only without taxt 
			    contriAmtA = groupACount*contriAAmt;
				contriAmtB = groupBCount*contriBAmt;
				contriAmtC = groupCCount*contriCAmt;
				contriAmtD = groupDCount*contriDAmt;
				contriAmtBnz = groupBnzCount*contriBAmt;
				
			 
				
			}
			   
			logger.info("year "+year);
		/*	if(year.equals("2016"))
			{
				logger.info("In if ");
				serviceTax=44l;
				totalContriServiceAmt = 344l;
			}*/
			
			  
			
			  serviceTaxA = groupACount*serviceTaxA;
			  serviceTaxB = groupBCount*serviceTaxB;
			  serviceTaxC = groupCCount*serviceTaxC;
			  serviceTaxD = groupDCount*serviceTaxD;
			  serviceTaxBnz = groupBnzCount*serviceTaxBnz;
			
			totalEmpCount = groupACount+groupBCount+groupBnzCount+groupCCount+groupDCount;
			totalContribution = contriAmtA+contriAmtB+contriAmtC+contriAmtD+contriAmtBnz;
			totalServiceTax =  serviceTaxA+serviceTaxB+serviceTaxC+serviceTaxD+serviceTaxBnz;
			totalAmount = totalContribution + totalServiceTax;
			
			ArrayList row = new ArrayList();
			row.add("1");
			row.add(new URLData("A","hrms.htm?actionFlag=reportService&reportCode=8000201&action=generateReport&DirectReport=TRUE&displayOK=FALSE&Month="+month+"&Year="+year+"&BillNo="+BillNo+"&grade="+gradeIdA));
			row.add(groupACount);
			row.add(contriAmtA);
			row.add(serviceTaxA);
			row.add(groupACount*totalContriServiceAmtA);
			DataList.add(row); 
			ArrayList row1 = new ArrayList();
			row1.add("2");
			row1.add(new URLData("B","hrms.htm?actionFlag=reportService&reportCode=8000201&action=generateReport&DirectReport=TRUE&displayOK=FALSE&Month="+month+"&Year="+year+"&BillNo="+BillNo+"&grade="+gradeIdB));
			row1.add(groupBCount);
			row1.add(contriAmtB);
			row1.add(serviceTaxB);
			row1.add(groupBCount*totalContriServiceAmtB);
			DataList.add(row1); 
			ArrayList row2 = new ArrayList();
			row2.add("3");
			row2.add(new URLData("Bnz","hrms.htm?actionFlag=reportService&reportCode=8000201&action=generateReport&DirectReport=TRUE&displayOK=FALSE&Month="+month+"&Year="+year+"&BillNo="+BillNo+"&grade="+gradeIdBnz));
			row2.add(groupBnzCount);
			row2.add(contriAmtBnz);
			row2.add(serviceTaxBnz);
			row2.add(groupBnzCount*totalContriServiceAmtBnz);
			DataList.add(row2);
			ArrayList row3 = new ArrayList();
			row3.add("4");
			row3.add(new URLData("C","hrms.htm?actionFlag=reportService&reportCode=8000201&action=generateReport&DirectReport=TRUE&displayOK=FALSE&Month="+month+"&Year="+year+"&BillNo="+BillNo+"&grade="+gradeIdC));
			row3.add(groupCCount);
			row3.add(contriAmtC);
			row3.add(serviceTaxC);
			row3.add(groupCCount*totalContriServiceAmtC);
			DataList.add(row3);
		
			ArrayList row4 = new ArrayList();
			row4.add("5");
			row4.add(new URLData("D","hrms.htm?actionFlag=reportService&reportCode=8000201&action=generateReport&DirectReport=TRUE&displayOK=FALSE&Month="+month+"&Year="+year+"&BillNo="+BillNo+"&grade="+gradeIdD));
			row4.add(groupDCount);
			row4.add(contriAmtD);
			row4.add(serviceTaxD);
			row4.add(groupDCount*totalContriServiceAmtD);
			DataList.add(row4);
			
		
			
			ArrayList row14 = new ArrayList();
			row14.add(" 6 ");
			row14.add(" Total ");
			row14.add(totalEmpCount);
			row14.add(totalContribution);
			row14.add(totalServiceTax);
			row14.add(totalAmount);
			DataList.add(row14);
			
			ArrayList row5 = new ArrayList();


			StyleVO[] centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Left"); 
			StyledData dataStyle2 = new StyledData();
			dataStyle2.setStyles(centerboldStyleVO);
			dataStyle2.setData("Sevaarth Bill No and Date:"+BillNo+" and "+billCreationDate+"");
			dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
			dataStyle2.setColspan(6);
	
			row5.add(dataStyle2);
			DataList.add(row5);	
			
			ArrayList row6 = new ArrayList();


			centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			
			centerboldStyleVO[1].setStyleValue("Right"); 
			 dataStyle2 = new StyledData();
			dataStyle2.setStyles(centerboldStyleVO);
			dataStyle2.setData("Signature:.......................................");
			//dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
			dataStyle2.setColspan(6);
			row6.add(dataStyle2);
			DataList.add(row6);	
			
			ArrayList row7 = new ArrayList();


			centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Left"); 
			 dataStyle2 = new StyledData();
			dataStyle2.setStyles(centerboldStyleVO);
			dataStyle2.setData("Voucher No. and Voucher Date:............................................");
			//dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
			dataStyle2.setColspan(6);
			row7.add(dataStyle2);
			DataList.add(row7);		
			

			ArrayList row8 = new ArrayList();


			centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Right"); 
			 dataStyle2 = new StyledData();
			dataStyle2.setStyles(centerboldStyleVO);
			dataStyle2.setData("Name OF the DDO:"+ddoName);
			//dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
			dataStyle2.setColspan(6);
			row8.add(dataStyle2);
			DataList.add(row8);	
			
			ArrayList row9 = new ArrayList();


			centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Right"); 
			 dataStyle2 = new StyledData();
			dataStyle2.setStyles(centerboldStyleVO);
			dataStyle2.setData("Designation Of the DDO:"+ddoDesignation);
			//dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
			dataStyle2.setColspan(6);
			row9.add(dataStyle2);
			DataList.add(row9);	
			
			ArrayList row10 = new ArrayList();


			centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Right"); 
			 dataStyle2 = new StyledData();
			dataStyle2.setStyles(centerboldStyleVO);
			dataStyle2.setData("Signature:............................................. ");
			//dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
			dataStyle2.setColspan(6);
			row10.add(dataStyle2);
			DataList.add(row10);
			
			ArrayList row11 = new ArrayList();
			centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Right"); 
			 dataStyle2 = new StyledData();
			dataStyle2.setStyles(centerboldStyleVO);
			dataStyle2.setData("Name of The Controlling Officer:......................................");
			//dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
			dataStyle2.setColspan(6);
			row11.add(dataStyle2);
			DataList.add(row11);
			
			ArrayList row12 = new ArrayList();
			centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Right"); 
			 dataStyle2 = new StyledData();
			dataStyle2.setStyles(centerboldStyleVO);
			dataStyle2.setData("Desination of The Controlling Officer:...........................................");
			//dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
			dataStyle2.setColspan(6);
			row12.add(dataStyle2);
			DataList.add(row12);
			
		
			
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
	
	

}
