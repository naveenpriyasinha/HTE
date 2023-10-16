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
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class IncomeTaxReportAO extends DefaultReportDataFinder implements ReportDataFinder
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
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		
		//String langName=report.getLangId();
		int finalpagesize=20;
		//String locId=report.getLocId(); 
		//Statement lStmt = null;

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		String locationNameincaps=locationName.toUpperCase();
		
		//ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
		
		
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
			//StyledData dataStyle = null;
			
			StyleVO[] colorStyleVO = new StyleVO[1];
			colorStyleVO[0] = new StyleVO();
			colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			colorStyleVO[0].setStyleValue("blue");
			selfCloseVO = new StyleVO[1];
			selfCloseVO[0] = new StyleVO();
			selfCloseVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
			selfCloseVO[0].setStyleValue("javascript:self.close()"); 

		
			//lStmt = lCon.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
			
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
			
			
			//PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv1.getSessionFactory());

			if(report.getReportCode().equals("5000002"))
			{

				
				String portType="";
				String BillNo="";	
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
				if (BillNoinner.trim().equals("") || BillNo.equalsIgnoreCase("") )
				{
					//System.out.println("inside if condition bill no is ***********"+report.getParameterValue( "Bill No" ));
					BillNo = report	.getParameterValue("Bill No") != null ? report.getParameterValue("Bill No").toString().trim() : "";
				}
				//Ended
				
				String DeptName=locationNameincaps;
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				/*List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);
				String ddocode ="";
				String TanNo="";
				if(ddoList.size()>0)
					ddocode = ddoList.get(0).getDdoCode();
				if(ddoList.size()>0)
					TanNo = ddoList.get(0).getTanNo();*/
				
				
				String ddocode ="";
				String TanNo="";
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
	    			TanNo=ddoMst.getTanNo();
	    		}
	    		logger.info("logged in DDO Code is " + ddocode);
	    		
	    		//added by roshan
				logger.info("hhii the bill number"+BillNo);
				ddocode=payBillDAOImpl.getddoCodeForBillId(BillNo);
				logger.info("ddocode is for bill number "+ddocode);
				locationId=payBillDAOImpl.getLocationCode(Long.parseLong(BillNo));
				logger.info("hhi the location cxod eis "+locationId);
				//ended by roshan
	    		
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
				Integer in = Integer.parseInt(month);
				String monthname = monthName[in-1];
				
				String Title = "Schedule for the Recovery of Income Tax";
				String MajorHead="8658";
				
				String Tan=TanNo;
				String deptHeader ="";
				
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
				r.add(new StyledData("For the Month of "+monthname +" " +year,leftHeader));
				r.add(new StyledData("Treasury : "+TresuryName+"("+Treasury+")",rightHead));
				styleList.add(r);  
				
				ArrayList r2= new ArrayList();
				r2.add(new StyledData("Name of the Office : "+DeptName+"("+ddocode+")	",leftHeader));	
				r2.add(new StyledData(" ",rightHead));
				styleList.add(r2);
				
				ArrayList r3= new ArrayList();
				r3.add(new StyledData("TAN NO. : "+Tan,leftHeader));	
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

				
				/*String query = new String();
				query = " select distinct eisemp.sevarthEmpCode, concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),";
				query+=" dsgn.dsgnName,pr.proofNum,paybill.grossAmt,other.incomeTax,paybill.deduc9510";
				
				query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
				query+=" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd,";
				query+=" HrEisScaleMst scale,OrgDesignationMst dsgn,HrEisProofDtl pr,MstDcpsBillGroup bill,PaybillHeadMpg head,";
				query+=" HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde";
				
				query+=" where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";
				query+=" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId";
				query+=" and pr.orgPostMstByUserId.userId=userr.userId and pr.orgPostMstByUserId.userId=emp.orgUserMst.userId and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId ";
				query+=" and other.hrEisEmpMst.empId=eisemp.empId and other.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and dsgn.dsgnId=gd.orgDesignationMst.dsgnId";
				query+=" and scale.scaleId=sgd.hrEisScaleMst.scaleId and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and userpost.orgPostMstByPostId.postId=payorder.postId";
				query+=" and orde.orderHeadId=payorder.orderHeadId and paybill.orgPostMst.postId=payorder.postId  and head.billTypeId=2500337 ";
				query+=" and paybill.deduc9510>0 and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
				query+=" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locationId+" and head.hrPayPaybill=paybill.paybillGrpId ";
				*/
				
				String query = new String();
				
				query = " select eisemp.sevarthEmpCode, concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),";
				query+=" dsgn.dsgnName,pr.proofNum,paybill.grossAmt,paybill.deduc9570,paybill.deduc9510";
				
				query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,HrEisProofDtl pr,OrgPostDetailsRlt postdtls,OrgDesignationMst dsgn,";
				query+=" PaybillHeadMpg head,HrPayPaybill paybill";
				
				query+=" where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=pr.orgPostMstByUserId.userId ";
				query+=" and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId and postdtls.orgPostMst.postId = paybill.orgPostMst.postId";
				query+=" and eisemp.empId=paybill.hrEisEmpMst.empId ";
				query+=" and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1) and head.billTypeId=2500337";
				query+=" and head.hrPayPaybill=paybill.paybillGrpId and paybill.cmnLocationMst.locId="+locationId+" and paybill.deduc9510>0 order by concat(emp.empLname,' ',emp.empFname,' ',emp.empMname)";
				
				
				//System.out.println("***Query for Deduction details**" +query);
				Query sqlQuery = hibSession.createQuery(query);	      	
				List RowList=sqlQuery.list();
				long totalIncomeTax=0;
				if(RowList.size()!=0)
				{
					int cnt=1;
					
					Iterator itr = RowList.iterator();
					String employeeName;
					String designationName;
					String proofNumber;
					double grossAmount;
					int pageCnt=1;

						while (itr.hasNext())
						{
							Object[] rowList = (Object[]) itr.next();
							//long employeeId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"0").toString());
							String employeeId = (rowList[0]!=null?rowList[0]:"").toString();
							employeeName = (rowList[1]!=null?rowList[1]:"").toString();
							designationName =(rowList[2]!=null?rowList[2]:"").toString();
							proofNumber = (rowList[3]!=null?rowList[3]:"0").toString();		
							grossAmount=Double.parseDouble((rowList[4]!=null?rowList[4]:"0").toString());
							double incomeTax = Double.parseDouble((rowList[6]!=null?rowList[6]:"0").toString());
							
							ArrayList row = new ArrayList();
							
							String empIdEmpNameDesigName = "("+employeeId+")-"+employeeName+"("+designationName+")"; 
				
				row.add(cnt);
				row.add(empIdEmpNameDesigName);
				if(proofNumber.equalsIgnoreCase("0"))
					proofNumber = "-";
				row.add(proofNumber);
				row.add(grossAmount);
				row.add(incomeTax);
				
				totalIncomeTax+=incomeTax;
				
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
					dataStyle1.setData(totalIncomeTax);
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
						dataStyle1.setData(totalIncomeTax);                  
						row2.add(dataStyle1);
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
						dataStyle2.setData("Total Deduction In Words (`):  "+ConvertNumbersToWord.convert(Math.round(totalIncomeTax))+" only.");
						dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						dataStyle2.setColspan(4);
						row1.add(dataStyle2);
						
						
						for(int c=0;c<4;c++)
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
		if(  report.getReportCode().equals("5000002") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}

		if(  report.getReportCode().equals("5000002") )

		{            
			report.setParameterValue("Department",locationId+"");
		}
		return report;
	}
}
