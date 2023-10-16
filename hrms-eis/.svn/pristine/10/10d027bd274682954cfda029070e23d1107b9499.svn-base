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
public class GPFAbstractReportDAO extends DefaultReportDataFinder implements ReportDataFinder
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
	
	@SuppressWarnings("static-access")
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		
		logger.info("EmployeeLoanHistroyDAO");
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
		logger.info("EmployeeLoanHistroyDAO111");
		
		
		
		
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
			
			
			  
			
			
			PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			
			
			if(report.getReportCode().equals("5000014"))
			{
		

				String portType="";
				String BillNo="";	  
				String Department="";
				String month="";
				String year="";
				String ReportType="";
				
				
				
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
			
				
			

				
				
				String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};        
						 
				Integer in = Integer.parseInt(month);
				String monthname = monthName[in-1];
				String Space=" ";
		            
				//List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);
				DeptName=payBillDAOImpl.getOffice(locationId);
				
				/*String ddocode ="";
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
	    		//added by roshan
				logger.info("hhii the bill number"+BillNo);
				ddocode=payBillDAOImpl.getddoCodeForBillId(BillNo);
				logger.info("ddocode is for bill number "+ddocode);
				locationId=payBillDAOImpl.getLocationCode(Long.parseLong(BillNo));
				logger.info("hhi the location cxod eis "+locationId);
				//ended by roshan
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
				
				
				String deptHeader ="";
				String Title ="";
				String MajorHead="8009";
				
				
				Title = "GPF Abastract for the Subscriptions and Refund of the  following Government Servants";
				
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
				r.add(new StyledData("For the Month of "+monthname +Space+year,leftHeader));
				r.add(new StyledData("Treasury : "+TresuryName+"("+Treasury+")",rightHead));
				styleList.add(r);  
				
				
				ArrayList r2= new ArrayList();
				r2.add(new StyledData("AG Office : "+"A. G. Mumbai",leftHeader));	
				r2.add(new StyledData(" ",rightHead));	
				styleList.add(r2);
				
				ArrayList r3= new ArrayList();
				r3.add(new StyledData("Name of the Office : "+DeptName+"("+ddocode+")	",leftHeader));	
				r3.add(new StyledData(" ",rightHead));	
				styleList.add(r3);
				
				
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
				
				/*query = " select gpf.pfSeries,count(gpf.userId), ";
				query+=" sum(paybill.basic0101),sum(paybill.basic0102),sum(paybill.allow0120),sum(paybill.deduc9780+paybill.deduc1038+paybill.deduc1037+paybill.deduc1039+paybill.deduc1040),sum(paybill.allow1149),sum(paybill.adv5054)";
				
				query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
				query+=" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,";
				query+=" OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
				query+=" HrPayPaybill paybill";
				
				query+=" where  emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";
				query+=" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId ";
				query+=" and other.hrEisEmpMst.empId=eisemp.empId";
				query+=" and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and postdtls.orgDesignationMst.dsgnId=dsgn.dsgnId and userpost.activateFlag=1";
				//query+=" and  head.billTypeId=2500337 and paybill.deduc9780>0";
				query+=" and emp.empDoj<'2005-11-01' and  head.billTypeId=2500337";
				query+=" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
				query+=" and bill.dcpsDdoBillGroupId=head.billNo and bill.dcpsDdoBillGroupId="+BillNo+" and paybill.cmnLocationMst.locId="+locationId+" ";
				query+=" and head.hrPayPaybill=paybill.paybillGrpId and  emp.orgGradeMst.gradeId <> 100067 group by gpf.pfSeries order by gpf.pfSeries";
				*/
				
				query = " select gpf.pfSeries,"; //0
				query+= " count(gpf.userId), "; //1
				query+= " sum(paybill.basic0101), "; //2
				query+= " sum(paybill.basic0102), "; //3
				query+= " sum(paybill.allow0120), "; //4
				query+= " sum(paybill.deduc9780+paybill.deduc1038+paybill.deduc1037+paybill.deduc1039+paybill.deduc1040), "; //5
				query+= " sum(paybill.allow1149), "; //6
				query+= " sum(paybill.adv5054), "; //7
				query+= " sum(paybill.deduc9702+paybill.deduc9704+paybill.deduc9705) "; //8
				
				query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,MstEmp dcps,";
				query+=" OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,HrPayPaybill paybill";
				
				
				query+=" where emp.empId=eisemp.orgEmpMst.empId and gpf.userId=emp.orgUserMst.userId ";
				query+=" and postdtls.orgDesignationMst.dsgnId=dsgn.dsgnId and eisemp.empId=paybill.hrEisEmpMst.empId ";
				query+=" and postdtls.orgPostMst.postId=paybill.orgPostMst.postId and dcps.orgEmpMstId = emp.empId and dcps.dcpsOrGpf='N' and emp.orgGradeMst.gradeId <> 100067";
				query+=" and head.billTypeId=2500337 and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1) ";
				query+=" and bill.dcpsDdoBillGroupId=head.billNo and bill.dcpsDdoBillGroupId="+BillNo+" and paybill.cmnLocationMst.locId="+locationId+" ";
				query+=" and head.hrPayPaybill=paybill.paybillGrpId group by gpf.pfSeries order by gpf.pfSeries";
				
				Query sqlQuery = hibSession.createQuery(query);
				
				/*System.out.println("GPFAbstractReportDAO query*********" +sqlQuery);
				System.out.println("GPFAbstractReportDAO query*********" +sqlQuery.toString());*/
				 
				List RowList=sqlQuery.list();
				
				
				if(RowList.size()!=0)
				{
					int cnt=1;
					
					Iterator itr = RowList.iterator();
					
					String SeriesName;
					long NoOfGovtServent=0;
					
					double SubScribe=0;
					double PayDAArrMerge=0;
					long GPFArr=0;
					double RefundAmount=0;
					double Total=0;
					
					
					double GrandSubScribe=0;
					double GrandRefundAmount=0;
					double GrandPayDAArrMerge = 0;
					double GrandTotal=0;
					
					
					int pageCnt=1;
						while (itr.hasNext())
						{
							Object[] rowList = (Object[]) itr.next();
							SeriesName = (rowList[0]!=null?rowList[0].toString():"").toString();
							NoOfGovtServent = Long.parseLong((rowList[1]!=null?rowList[1]:"0").toString());
							double DP= Double.parseDouble((rowList[4]!=null?rowList[4]:"0").toString());
							SubScribe = Double.parseDouble((rowList[5]!=null?rowList[5]:"0").toString());
							RefundAmount = Double.parseDouble((rowList[7]!=null?rowList[7]:"0").toString());
							PayDAArrMerge = Double.parseDouble((rowList[8]!=null?rowList[8]:"0").toString());
							
							ArrayList row = new ArrayList();
							
							Total=SubScribe+DP+PayDAArrMerge+GPFArr+RefundAmount;
							
							logger.info("NoOfGovtServent*******" + NoOfGovtServent );
							logger.info("NoOfGovtServent111111111111*******" + rowList[1] );
							
							
							logger.info("Total********* " + Total );
				
							GrandSubScribe+=SubScribe;
							GrandRefundAmount+=RefundAmount;
							GrandTotal+=Total;
							GrandPayDAArrMerge+=PayDAArrMerge;
							
						
							
							
				 logger.info("grand total*********" +GrandTotal);
				 logger.info("grand GrandSubScribe*********" +GrandSubScribe);
				 logger.info("grand GrandRefundAmount*********" +GrandRefundAmount);
				 
				row.add(cnt);
				row.add(SeriesName);
				row.add(NoOfGovtServent);
				row.add("");
				row.add(SubScribe);
				row.add(PayDAArrMerge);
				row.add(GPFArr);
				row.add(RefundAmount);
				row.add(Total);
				
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					//logger.info("No Of Rec is*****************====>"+noOfRec);
					finalpagesize=Integer.parseInt(noOfRec);
				}
			
				DataList.add(row);
				if((cnt%finalpagesize)==0) 
				{
					logger.info("pageCnt in GPF Abstract report:::::::::"+pageCnt);
					
					pageCnt++;
					
					row = new ArrayList();
					row.add(new PageBreak());
					row.add("Data");
					DataList.add(row); 
					
					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(GrandTotal); 
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
						dataStyle1.setData("TOTAL (`)");    
						dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						row2.add(dataStyle1);
						
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(GrandSubScribe); 
						row2.add(dataStyle1);
						
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(GrandPayDAArrMerge); 
						row2.add(dataStyle1);
						row2.add("");
						 
						StyledData dataStyle22 = new StyledData();
						dataStyle22 = new StyledData();
						dataStyle22.setStyles(boldStyleVO);
						dataStyle22.setData(GrandRefundAmount); 
						row2.add(dataStyle22);
						
						
						StyledData dataStyle3 = new StyledData();
						dataStyle3 = new StyledData();
						dataStyle3.setStyles(boldStyleVO);
						dataStyle3.setData(GrandTotal);  
						row2.add(dataStyle3);
						row2.add("");
						
						
						DataList.add(row2);
						ArrayList row1 = new ArrayList();

						logger.info("TOTALLLLLLLLLLLLLLL");
						
						StyleVO[] centerboldStyleVO = new StyleVO[2];
						centerboldStyleVO[0] = new StyleVO();
						centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
						centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
						centerboldStyleVO[1] = new StyleVO();
						centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
						centerboldStyleVO[1].setStyleValue("Left"); 
						StyledData dataStyle2 = new StyledData();
						dataStyle2.setStyles(centerboldStyleVO);
						dataStyle2.setData("Total Deduction In Words (`) :  "+ConvertNumbersToWord.convert(Math.round(GrandTotal))+" Only.");
						dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						dataStyle2.setColspan(9);
						row1.add(dataStyle2);
						
						
						for(int c=0;c<9;c++)
							row1.add("");
						
						DataList.add(row1);
			
	}
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
		if(  report.getReportCode().equals("5000014") )

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
