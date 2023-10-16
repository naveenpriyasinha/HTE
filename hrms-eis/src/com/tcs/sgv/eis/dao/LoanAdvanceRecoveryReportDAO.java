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
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.reports.dao.ReportParameterDAO;

public class LoanAdvanceRecoveryReportDAO extends DefaultReportDataFinder implements ReportDataFinder
{
	private static final StringTokenizer StringTokenizer = null;
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
		logger.info("Location id is-------------"+ locationId);
		String locationNameincaps=locationName.toUpperCase();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");

		List DataList = new ArrayList();   
		try   
		{
			Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
			SessionFactory sessionFactory = serviceLocator.getSessionFactory();
			Session session= sessionFactory.getCurrentSession();	           
			Session hibSession = sessionFactory.getCurrentSession();
			ServiceLocator serv1 = (ServiceLocator) requestKeys.get("serviceLocator");
			ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");

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
			logger.info("loan recovery report");
			lCon = DBConnection.getConnection(  );
			lStmt = lCon.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
			headerStyleVo[2].setStyleValue("18");
			headerStyleVo[3] = new StyleVO();
			headerStyleVo[3].setStyleId(IReportConstants.STYLE_FONT_FAMILY);
			headerStyleVo[3].setStyleValue(IReportConstants.VALUE_FONT_FAMILY_ARIAL);
			PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv1.getSessionFactory());

			if(report.getReportCode().equals("5000032"))
			{
				//StringBuffer lsb = new StringBuffer(  );      
				//String portType="";
				String BillNo="";	
				String Department="";
				String month="";
				String year="";
				String loanType="";
				String ReportType="";
				String BillNoinner="";
				String employeeid="";
				String[] temp;
				String str="";


				//employeeid=CheckIfNull(report.getParameterValue( "employeeName" ));
				Department=CheckIfNull(report.getParameterValue( "Department" ));
			logger.info("Department-----------------------"+Department);
				if(Department.equals(" ")||Department.equals("-1"))
					Department=	locationId+"";	
				else
					locationId=Long.parseLong(Department);

				BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
			logger.info("Bill no-----------------------"+BillNoinner);
				StringTokenizer st1=new StringTokenizer(BillNoinner, "~");
				String temp1="";
				while(st1.hasMoreTokens())
					temp1=st1.nextToken();
			logger.info("------StringToken---------"+temp1);
				/*StringTokenizer st1 = new StringTokenizer(BillNoinner, "~");
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
				} */
				String DeptName=locationNameincaps;
				long loggedInpostId = Long.parseLong(baseLoginMap.get("loggedInPost").toString());
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);
				String ddocode ="";
				if(ddoList.size()>0)
					ddocode = ddoList.get(0).getDdoCode();
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

				String billType="2500337";
				long arrearbillTypeId=Integer.parseInt(resourceBundle.getString("arrearbillTypeId"));
				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				loanType=CheckIfNull(report.getParameterValue( "Loan Type" ));
				ReportType = CheckIfNull(report.getParameterValue( "ReportFor" ));
			logger.info("Loan Type*********"+loanType);   
			logger.info("report type*********"+ReportType); 
				str = ReportType;
				String delimiter = "~";
				temp = str.split(delimiter);
				for(int i =0; i < temp.length ; i++);   
				String var=temp[0];
				String REPORTTYPE="300429";

			logger.info("ReportType***********" +ReportType);
			logger.info("var***********" +var);

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
				sdfObj = new SimpleDateFormat("MMM");
				String Month = sdfObj.format(startMonthDate);
				java.util.Calendar calendar = java.util.Calendar.getInstance(); 
				int curYear=calendar.get(calendar.YEAR); 
				int curMonth=calendar.get(Calendar.MONTH);
				String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};        
				Calendar cale = Calendar.getInstance(); 
				String MONTH = monthName[cale.get(Calendar.MONTH)]; 	          
				Integer in = Integer.parseInt(month);
				String monthname = monthName[in-1];
				String Title = "Schedule for the Recovery of Loan/Advance";
				if(var.equals(REPORTTYPE))
				{
					if(loanType.equals("56"))
					{
						Title="Schedule for the Recovery of MCA (INTEREST)";
					}
					else if(loanType.equals("67"))
					{
						Title="Schedule for the Recovery of HBA FOR HOUSE (INTEREST)";
					}
					else if(loanType.equals("51"))
					{
						Title="Schedule for the Recovery of HBA FOR LAND (INTEREST)";
					}
					else if(loanType.equals("59"))
					{
						Title="Schedule for the Recovery of FESTIVAL ADVANCE (INTEREST)";
					}
				}
				else
				{
					if(loanType.equals("56"))
					{
						Title="Schedule for the Recovery of MCA (PRINCIPAL)";
					}
					else if(loanType.equals("67"))
					{
						Title="Schedule for the Recovery of HBA FOR HOUSE(PRINCIPAL)";
					}
					else if(loanType.equals("51"))
					{
						Title="Schedule for the Recovery of HBA FOR LAND (PRINCIPAL)";
					}
					else if(loanType.equals("59"))
					{
						Title="Schedule for the Recovery of FESTIVAL ADVANCE (PRINCIPAL)";
					}
				}
				String MajorHead="8658";
				String Tan="MUMD08599D";
				String deptHeader ="";

				if( BillNo != null && BillNo != "" )
				{
					deptHeader=Title;
				}
				else
				{
					deptHeader=Title; 
				}
				List styleList = new ArrayList();
				List stData = new ArrayList();
				StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
				String Treasury =TresuryCode;
				styleList.add(stData);
				List r = new ArrayList();
				r.add(new StyledData("For the Month of "+monthname +" "+year,leftHeader));
				r.add(new StyledData("Treasury : "+TresuryName+"("+Treasury+")",rightHead));
				styleList.add(r);  
				List r2= new ArrayList();
				r2.add(new StyledData("Name of the Office : "+DeptName+"("+ddocode+")	",leftHeader));	
				styleList.add(r2);

				/*List r3= new ArrayList();
				r3.add(new StyledData("TAN NO. : "+Tan,leftHeader));	
				styleList.add(r3);*/
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

				/*String query = " select concat(orgempmst22_.emp_fname,' ',orgempmst22_.emp_mname,' ',orgempmst22_.emp_lname),";
				query+= " hrloanempd18_.LOAN_ACCOUNT_NO,hrloanempd18_.LOAN_DATE,hrpaypaybi17_.TOTAL_AMT,";
				query+= " hrpaypaybi17_.TOTAL_INST," ;
				if(var.equals(REPORTTYPE))
				{
					if(loanType.equals("56"))
					{
						query+= " hrpaypaybi14_.MCA_LAND_INT,";
					}
					else if(loanType.equals("67"))
					{
						query+= " hrpaypaybi14_.HBA_HOUSE_INT,";
					}
					else if(loanType.equals("51"))
					{
						query+= " hrpaypaybi14_.HBA_LAND_INT,";
					}
					else if(loanType.equals("59"))
					{
						query+= " hrpaypaybi14_.FESTIVAL_ADVANCE,";
					}


				}
				else
				{
					if(loanType.equals("56"))
					{
						query+= " hrpaypaybi14_.MCA_LAND,";
					}
					else if(loanType.equals("67"))
					{
						query+= " hrpaypaybi14_.HBA_HOUSE,";
					}
					else if(loanType.equals("51"))
					{
						query+= " hrpaypaybi14_.HBA_LAND,";
					}
					else if(loanType.equals("59"))
					{
						query+= " hrpaypaybi14_.FESTIVAL_ADVANCE,";
					}
				}

				query+= "hrpaypaybi17_.RECOVERED_INST,hrpaypaybi17_.RECOVERED_AMT ";

				query+= " from org_emp_mst orgempmst0_, hr_eis_emp_mst hreisempms1_, org_user_mst orgusermst2_,";
				query+= "org_userpost_rlt orguserpos3_, org_post_mst orgpostmst4_, org_post_details_rlt orgpostdet5_,";
				query+= "hr_pay_gpf_details hrpaygpfba6_,HR_EIS_OTHER_DTLS hreisother7_,HR_EIS_SGD_MPG hreissgdmp8_,";
				query+= "HR_EIS_GD_MPG hreisgdmpg9_,hr_eis_scale_mst hreisscale10_,org_designation_mst orgdesigna11_,";
				query+= " MST_DCPS_BILL_GROUP mstdcpsbil12_,PAYBILL_HEAD_MPG paybillhea13_,HR_PAY_PAYBILL hrpaypaybi14_,";
				query+= " HR_PAY_ORDER_HEAD_POST_MPG hrpayorder15_, HR_PAY_ORDER_HEAD_MPG hrpayorder16_, HR_PAY_PAYBILL_LOAN_DTLS hrpaypaybi17_,";
				query+= " HR_LOAN_EMP_DTLS hrloanempd20_,hr_eis_emp_mst hreisempms21_,org_emp_mst orgempmst22_,";
				query+= " HR_PAY_PAYBILL hrpaypaybi31_, HR_LOAN_EMP_DTLS hrloanempd18_," ;
					if(var.equals(REPORTTYPE))	
					{
				query+= "HR_LOAN_EMP_INT_RECOVER_DTLS hrloanempi19_ ";
					}
					else
						{
						query+= "hr_loan_emp_prin_recover_dtls hrloanempi19_";
						}
				query+= " where hrpaypaybi17_.EMP_LOAN_ID=hrloanempd20_.EMP_LOAN_ID and hrloanempd20_.EMP_ID=hreisempms21_.emp_id ";
				query+= " and hreisempms21_.emp_mpg_id=orgempmst22_.emp_id and hrpaypaybi17_.paybill_id=hrpaypaybi31_.ID";
				query+= " and orgempmst0_.emp_id=hreisempms1_.emp_mpg_id  and orgempmst0_.user_id=orgusermst2_.user_id ";
				query+= " and orgusermst2_.user_id=orguserpos3_.user_id and orguserpos3_.post_id=orgpostmst4_.post_id ";
				query+= " and orguserpos3_.post_id=orgpostdet5_.post_id and orguserpos3_.activate_flag=1  and orgusermst2_.user_id=hrpaygpfba6_.USER_ID ";
				query+= " and hreisother7_.EMP_ID=hreisempms1_.emp_id and hreisother7_.EMP_SGD_ID=hreissgdmp8_.SGD_MAP_ID  ";
				//query+= " and orgdesigna11_.dsgn_id=orgpostdet5_.dsgn_id and orgdesigna11_.dsgn_id=hreisgdmpg9_.SGD_DESIG_ID ";
				query+= " and hreisscale10_.scale_id=hreissgdmp8_.SGD_SCALE_ID and hreisother7_.EMP_ID=hrpaypaybi14_.EMP_ID  ";
				query+= " and orguserpos3_.post_id=hrpayorder15_.POST_ID and hrpayorder16_.ORDER_HEAD_ID=hrpayorder15_.ORDER_HEAD_ID ";
				query+= " and hrpaypaybi14_.post_id=hrpayorder15_.POST_ID and hrpaypaybi17_.paybill_id=hrpaypaybi14_.ID ";
				query+= " and hrloanempd18_.EMP_LOAN_ID=hrpaypaybi17_.EMP_LOAN_ID and hrloanempd18_.LOAN_ACTIVATE_FLAG=1 and hrloanempd18_.EMP_ID=hreisempms1_.emp_id ";
				query+= " and hrpaypaybi17_.LOAN_TYPE_ID="+loanType+" and hrloanempi19_.EMP_LOAN_ID=hrpaypaybi17_.EMP_LOAN_ID ";
				if(var.equals(REPORTTYPE))
				{
				query+= " and hrpaypaybi17_.recovery_type=300429 " ;
				}
				else
					{
					query+= " and hrpaypaybi17_.recovery_type=300428 " ;
					}
			    query+=	"and paybillhea13_.bill_type_id=2500337 ";
				query+= " and paybillhea13_.bill_no="+temp1+" and paybillhea13_.PAYBILL_MONTH="+month+" ";
				query+= " and paybillhea13_.PAYBILL_YEAR="+year+" and ( paybillhea13_.approve_flag in(0,1))";
				//query+= " and mstdcpsbil12_.BILL_GROUP_ID=paybillhea13_.bill_no and orgpostdet5_.loc_id="+Department+"";
				query+= " and paybillhea13_.PAYBILL_ID=hrpaypaybi14_.PAYBILL_GRP_ID";

				Query sqlQuery = hibSession.createSQLQuery(query);	      	

				logger.info("***Query for Deduction details**" +sqlQuery);*/

				StringBuffer lsb = new StringBuffer(  );      

				lsb.append("select concat(oem.empFname,' ',oem.empMname,' ',oem.empLname),");
				lsb.append("hled.loanAccountNo,hled.loanDate,hppld.totalAmt,hppld.totalInst,");

				if(var.equals(REPORTTYPE))
				{ 
					if(loanType.equals("56"))
					{
						lsb.append("hpp.loanInt5056,");
					}
					else if(loanType.equals("67"))
					{
						lsb.append("hpp.loanInt5067,");
					}
					else if(loanType.equals("51"))
					{
						lsb.append("hpp.loanInt5051,");
					}
					else if(loanType.equals("59"))
					{
						lsb.append("hpp.adv5059,");
					}
				}
				else 
				{
					if(loanType.equals("56"))
					{
						lsb.append("hpp.loan5056,");
					}
					else if(loanType.equals("67"))
					{
						lsb.append("hpp.loan5067,");
					}

					else if(loanType.equals("51"))
					{
						lsb.append("hpp.loan5051,");
					}

					else if(loanType.equals("59"))
					{
						lsb.append("hpp.adv5059,");
					}
				}

				lsb.append("hppld.recoveredInst,hppld.recoveredAmt ");

				lsb.append("from "); 
				
				lsb.append("OrgEmpMst oem,HrEisEmpMst heem,OrgUserMst oum,");
				lsb.append("OrgUserpostRlt our,OrgPostMst opm,OrgPostDetailsRlt opdr,") ;
				lsb.append("HrLoanEmpDtls hled,HrPayPaybillLoanDtls hppld,HrPayPaybill hpp,");
				lsb.append("HrPayGpfBalanceDtls hpgbd,HrEisOtherDtls heod,HrEisSgdMpg hesm,");
				lsb.append("HrEisGdMpg hegm,HrEisScaleMst hescm,OrgDesignationMst odm,");
				lsb.append("MstDcpsBillGroup mdbg,PaybillHeadMpg phm,HrPayOrderHeadPostMpg hpohpm,");
				lsb.append("HrPayOrderHeadMpg hpohm ,");

				if (var.equals(REPORTTYPE))
				{
					lsb.append("HrLoanEmpIntRecoverDtls hleird ");
				}
				else
				{
					lsb.append("HrLoanEmpPrinRecoverDtls hleird ");
				}
				lsb.append("where ");

				lsb.append("hppld.hrLoanEmpDtls.empLoanId  =  hled.empLoanId and hled.hrEisEmpMst.empId = heem.empId  ");
				lsb.append("and heem.orgEmpMst.empId = oem.empId and hppld.paybillId.id = hpp.id ");
				lsb.append("and oem.empId = heem.orgEmpMst.empId and oem.orgUserMst.userId = oum.userId ");
				lsb.append("and oum.userId = our.orgUserMst.userId and our.orgPostMstByPostId.postId = opm.postId ");
				lsb.append("and our.orgPostMstByPostId.postId = opdr.orgPostMst.postId and our.activateFlag = 1 and oum.userId = hpgbd.userId ");
				lsb.append("and heod.hrEisEmpMst.empId = heem.empId and heod.hrEisSgdMpg.sgdMapId = hesm.sgdMapId "); 
				lsb.append("and odm.dsgnId = opdr.orgDesignationMst.dsgnId and odm.dsgnId = hegm.orgDesignationMst.dsgnId ");
				lsb.append("and hescm.scaleId = hesm.hrEisScaleMst.scaleId and heod.hrEisEmpMst.empId = hpp.hrEisEmpMst.empId ");
				lsb.append("and our.orgPostMstByPostId.postId  =  hpohpm.postId and hpohm.orderHeadId = hpohpm.orderHeadId ");
				lsb.append("and hpp.orgPostMst.postId = hpohpm.postId and hppld.paybillId.id = hpp.id ");
				lsb.append("and hled.empLoanId = hppld.hrLoanEmpDtls.empLoanId and hled.loanActivateFlag = 1 and hled.hrEisEmpMst.empId = heem.empId ");
				lsb.append("and hppld.hrLoanAdvMst.loanAdvId = "+loanType+" and hleird.hrLoanEmpDtls.empLoanId = hppld.hrLoanEmpDtls.empLoanId ");
				if(var.equals(REPORTTYPE))
				{
					lsb.append( "and hppld.recoveryType = 300429 ") ;
				}
				else
				{
					lsb.append ("and hppld.recoveryType = 300428 ") ;
				}
				lsb.append("and phm.billTypeId= 2500337 ");
				lsb.append ("and phm.billNo.dcpsDdoBillGroupId = "+temp1+" and phm.month= "+month+" ");
				lsb.append("and phm.year = "+year+" and ( phm.approveFlag in(0,1)) ");
				lsb.append("and mdbg.dcpsDdoBillGroupId = phm.billNo and opdr.cmnLocationMst.locId = "+Department+" ");
				lsb.append("and phm.hrPayPaybill = hpp.paybillGrpId ");

				String query = lsb.toString(); 
				Query sqlQuery = hibSession.createQuery(query);	      	
				logger.info("***Query for Deduction details**" +sqlQuery);
				
				List RowList=sqlQuery.list();
				String employeeName;
				String accountNumber="";
				String loanDate;
				long totalAmount=0;
				long totalInst=0;
				long amountOfInst=0;
				long instNo=0;
				long totalRecovery=0;
				long outStandingAmount=0;
				long total=0;
				long finaltotalRecovery=0;

				if(RowList.size()!=0)
				{
					List dataList=new ArrayList();
					int cnt=1;
					Iterator itr = RowList.iterator();
					int pageCnt=1;

					while (itr.hasNext())
					{
						Object[] rowList = (Object[]) itr.next();
						employeeName = (rowList[0]!=null?rowList[0]:"").toString();
						accountNumber = (rowList[1]!=null?rowList[1]:"").toString();
						loanDate = (rowList[2]!=null?rowList[2]:"0").toString();
						totalAmount = Long.parseLong((rowList[3]!=null?rowList[3]:"0").toString());
						totalInst = Long.parseLong((rowList[4]!=null?rowList[4]:"0").toString());
						logger.info("Checking value rowList[5] " + rowList[5]);
						logger.info("Checking value (rowList[5]!=null?rowList[5]:0.toString()" + (rowList[5]!=null?rowList[5]:"0").toString());
						//amountOfInst = Long.parseLong((rowList[5]!=null?rowList[5]:"0").toString());
						amountOfInst = (long) Float.parseFloat((rowList[5].toString()));
						logger.info("amountOfInst::::::::::::::::::::::::: " +amountOfInst);
						instNo = Long.parseLong((rowList[6]!=null?rowList[6]:"0").toString());
						totalRecovery = Long.parseLong((rowList[7]!=null?rowList[7]:"0").toString());
						total=total+amountOfInst;
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
						loanDate=sdf.format(sdformat.parse(loanDate));
						outStandingAmount=totalAmount-totalRecovery;
						finaltotalRecovery+=totalRecovery;
						List row = new ArrayList();
						row.add(cnt);
						row.add(employeeName);
						row.add(accountNumber);
						row.add(loanDate);
						row.add(totalAmount);
						row.add(totalInst);
						row.add(amountOfInst);
						row.add(instNo);
						row.add(totalRecovery);
						row.add(outStandingAmount);
						DataList.add(row);
						if((cnt%finalpagesize)==0) 
						{
							row = new ArrayList();
							row.add(new PageBreak());
							StyledData dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(finaltotalRecovery);
							row.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData(pageCnt);                   
							row.add(dataStyle1);//2
						}
						cnt++;
					}
					List row2 = new ArrayList();
					row2.add("");
					row2.add("");
					row2.add("");
					row2.add("");
					row2.add("");
					row2.add("");
					row2.add("");
					StyledData dataStyle1 = new StyledData();
					dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData("TOTAL");                  
					row2.add(dataStyle1);
					dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(finaltotalRecovery);                  
					row2.add(dataStyle1);
					row2.add("");
					DataList.add(row2); 

					List row1 = new ArrayList();
					StyleVO[] centerboldStyleVO = new StyleVO[2];
					centerboldStyleVO[0] = new StyleVO();
					centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
					centerboldStyleVO[1] = new StyleVO();
					centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					centerboldStyleVO[1].setStyleValue("Left"); 
					StyledData dataStyle2 = new StyledData();
					dataStyle2.setStyles(centerboldStyleVO);
					dataStyle2.setData("Total Deduction In Words: "+ConvertNumbersToWord.convert(Math.round(finaltotalRecovery))+" only.");
					dataStyle2.setColspan(10);
					row1.add(dataStyle2);

					for(int c=0;c<10;c++)
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
		if(  report.getReportCode().equals("5000032") )
		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
		}
		return report;
	}
}
