package com.tcs.sgv.eis.dao;

import java.sql.ResultSet;
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
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;


@SuppressWarnings( {  "unchecked", "static-access" })
public class EmpBasicDtlsDAO extends DefaultReportDataFinder implements ReportDataFinder
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


	public Collection findReportData( ReportVO report, Object criteria) throws ReportException
	{
		/*Map loginMap=(Map)objectArgs.get("baseLoginMap");
		long langId = Long.parseLong((loginMap.get("langId").toString()));

		logger.info("langId"+langId);*/

		String langName=report.getLangId();
		logger.info("langName"+langName);
		int finalpagesize=27;

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();

		logger.info("locationId"+locationId);
		String locationNameincaps=locationName.toUpperCase();

		ArrayList DataList = new ArrayList(  );            
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
			logger.info("getReportCode"+report.getReportCode());
			String portType="";
			String BillNo="";	    
			String desigName="";
			String deptName="";
			String cityName="";
			String cardexCode="";
			ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");	
			PayBillDAO billDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			Object[] objArry = null;
			List lstSignInfo = new ArrayList();

			if(report.getReportCode().equals("43") || report.getReportCode().equals("5000005"))
			{

				String Department="";
			    String ShowSignatureFlg=CheckIfNull(report.getParameterValue( "Show Signature" ));

				String month="";
				String year="";
				Department=CheckIfNull(report.getParameterValue( "Department" ));
				if(Department.equals("")||Department.equals("-1"))
					Department=	locationId+"";	
				else
					locationId=Long.parseLong(Department);
				lstSignInfo = billDAO.getReportSignature(locationId);
				if(lstSignInfo != null && lstSignInfo.size()>0 && lstSignInfo.get(0)!=null)
				{

					objArry =(Object[]) lstSignInfo.get(0);
					desigName=objArry[0].toString();
					deptName=objArry[1].toString();
					cardexCode=objArry[2].toString();
					cityName=objArry[3].toString();            		
				}

				String BillNoinner="";//GAD specific
				BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
				logger.info(BillNoinner);
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
					logger.info("Actual value for bill no MMMMMMMMMMMMMMMMM "+BillNo);
					l++;
				} 
				String DeptName=locationNameincaps;

				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				DeptName=payBillDAOImpl.getOffice(locationId);
				
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
				if(!ShowSignatureFlg.equals("No"))
		  		{
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
				tabularData.addStyle(IReportConstants.FONT_WEIGHT, IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_SIZE_XXLARGE);
				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
				tabularData.addStyle(IReportConstants.FONT_WEIGHT, IReportConstants.VALUE_FONT_WEIGHT_BOLD);
				tabularData.addStyle(IReportConstants.BORDER, "No"); 

				report.setGrandTotalTemplate(tabularData);
		  		}

				month=CheckIfNull(report.getParameterValue( "Month" ));
				year=CheckIfNull(report.getParameterValue( "Year" ));
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");

				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				java.util.Date startMonthDate = cal.getTime();

				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

				cal.set(Calendar.DAY_OF_MONTH, totalDays);

				java.util.Date endMonthDate = cal.getTime();


				Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
				ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");

				SessionFactory sessionFactory = serviceLocator.getSessionFactory();

				Session hibSession = sessionFactory.getCurrentSession();




				sdfObj = new SimpleDateFormat("MMM");
				String Month = sdfObj.format(startMonthDate);

				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();
	  	

				if( BillNo != null && BillNo != "" )
				{

					String deptHeader=DeptName+System.getProperty("line.separator")+"Details Of Employee for the Month of "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
					stData.add(new StyledData (deptHeader,headerStyleVo));
					styleList.add(stData);

				}
				else
				{

					String deptHeader=DeptName+System.getProperty("line.separator")+"Details Of Employee for the Month of   "+Month+". "+year;
					stData.add(new StyledData (deptHeader,headerStyleVo));
					styleList.add(stData);

				}
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



				logger.info("Manish patel here hfgsdgfs"+locationId);
				String query="";
				
				report.setParameterValue("Vacant", "WITHOUT VACANT");
				if(report.getParameterValue("Vacant").equals("WITHOUT VACANT"))
				{
					
					
				//	query="select concat(oe.emp_lname ,' ', oe.emp_mname,' ', oe.emp_fname),";
					query="select oe.emp_lname || ' ' || oe.emp_fname || ' ' || oe.emp_mname,";
					query+="dm.dsgn_name ,";
					query+="oe.emp_gpf_num ,";
					query+="Replace(Replace((select prd.proof_num from  hr_eis_proof_dtl prd where prd.user_id=oe.user_id and prd.loc_id = pb.loc_id ),' ', ''),'-',''),";
					query+="ebnk.bank_acct_no ,";
					query+="(select ebm.bank_name from mst_bank_pay ebm where ebm.bank_id = ebnk.bank_id) ,";
					query+="(select ebrm.branch_name from rlt_bank_branch ebrm where ebrm.branch_id = ebnk.bank_branch_id),oe.emp_id";
					query+=" from hr_pay_paybill pb left outer join hr_eis_emp_mst ee on ee.emp_id = pb.emp_id and ee.loc_id = pb.loc_id";
					query+=" left outer join org_emp_mst oe on oe.emp_id =  ee.emp_mpg_id and oe.lang_id = 1";
					query+=" left outer join hr_eis_bank_dtls ebnk on ebnk.bank_emp_id = pb.emp_id";
					query+=" left outer join org_userpost_rlt up on up.user_id = oe.user_id";
					query+=" left outer join org_post_details_rlt pd on pd.post_id = up.post_id and pd.loc_id = ee.loc_id and pd.lang_id = oe.lang_id";
					query+=" left outer join org_designation_mst dm on dm.dsgn_id = pd.dsgn_id and dm.lang_id = pd.lang_id";
					query+=" where  pb.loc_id ="+ locationId +" and  pb.paybill_grp_id IN ( select ph.paybill_id from paybill_head_mpg ph where";
					query+=" ph.paybill_month =" +report.getParameterValue( "Month" );
					logger.info(report.getParameterValue( "Month" ));
					query+=" and  ph.paybill_year ="+report.getParameterValue( "Year" );
					logger.info(report.getParameterValue( "Year" ));
					query+=" and ph.bill_no in";
					query+=" (select bs.BILL_GROUP_ID from mst_dcps_bill_group bs where ";
					if( BillNo != null && BillNo != "" && !BillNo.equals("-1"))
					{
						query+="bs.BILL_GROUP_ID="+BillNo+" and";
					}

					query+="  bs.loc_id = ph.loc_id)";
					query+=" and ph.loc_id = pb.loc_id and ph.approve_flag in (0, 1) and ph.loc_id = pb.loc_id) and pb.emp_id is not null  and up.activate_flag=1";

					String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
					if(!empid.equals("") && !empid.equals("-1"))
					{
						query+=" and ee.emp_id="+report.getParameterValue( "employeeName" );
					}

					/*if(!report.getParameterValue( "Order By" ).equals("-1"))
					{
						query+=" order by "+report.getParameterValue( "Order By" );

					}*/
					
				}
				else
				{
					//query="(select pb.psr_no,concat(m.emp_fname,' ', m.emp_mname,' ', m.emp_lname)  ,dsgnmst.dsgn_name from hr_pay_paybill pb";
					query="(select pb.psr_no,m.emp_lname || ' ' ||  m.emp_fname || ' ' || m.emp_mname ,dsgnmst.dsgn_name from hr_pay_paybill pb";
					query+=" left outer join hr_eis_emp_mst em on em.emp_id =  pb.emp_id and pb.loc_id = em.loc_id  and em.loc_id ="+locationId;
					query+=" left outer join org_emp_mst  m on m.emp_id = em.emp_mpg_id"; /*and m.lang_id = 1 */
					query+=" left outer join hr_eis_other_dtls od on pb.other_id = od.other_id and pb.emp_id = od.emp_id"; 
					query+=" left outer join org_post_mst p on pb.post_id = p.post_id";
					query+=" left outer join org_post_details_rlt postmst  on  p.post_id = postmst.post_id";
					query+=" left outer join org_designation_mst  dsgnmst on dsgnmst.dsgn_id = postmst.dsgn_id";  
					query+=" where pb.paybill_grp_id in (select ph.paybill_id from paybill_head_mpg ph  where";
					query+=" ph.paybill_month =" +report.getParameterValue( "Month" );
					query+=" and ph.paybill_year ="+report.getParameterValue( "Year" );
					query+=" and ph.bill_no in (select sb.BILL_GROUP_ID from mst_dcps_bill_group sb where";
					if( BillNo != null && BillNo != "" && !BillNo.equals("-1"))
					{
						query+=" sb.BILL_GROUP_ID ="+BillNo+" and";
					}
					query+=" sb.loc_id ="+locationId+") and ph.approve_flag in (0,1)";
					query+=" and ph.loc_id = "+locationId+")";
					String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
					if(!empid.equals("") && !empid.equals("-1"))
					{
						query+=" and em.emp_id="+report.getParameterValue( "employeeName" );
					}
					query+=") union (";
					//query+="select pb.psr_no,concat(m.emp_fname,' ', m.emp_mname,' ', m.emp_lname),dsgnmst.dsgn_name from hr_pay_paybill pb";
					query+="select pb.psr_no, m.emp_lname || ' ' ||  m.emp_fname || ' ' || m.emp_mname,dsgnmst.dsgn_name from hr_pay_paybill pb";
					query+=" left outer join hr_eis_emp_mst em on em.emp_id =  pb.emp_id and pb.loc_id = em.loc_id  and em.loc_id ="+locationId;
					query+=" left outer join org_emp_mst  m on m.emp_id = em.emp_mpg_id"; /*and m.lang_id = 1 */
					query+=" left outer join hr_eis_other_dtls od on pb.other_id = od.other_id and pb.emp_id = od.emp_id"; 
					query+=" left outer join org_post_mst p on pb.post_id = p.post_id";
					query+=" left outer join org_post_details_rlt postmst  on  p.post_id = postmst.post_id";
					query+=" left outer join org_designation_mst  dsgnmst on dsgnmst.dsgn_id = postmst.dsgn_id";  
					query+=" where pb.paybill_grp_id in (select ph.paybill_id from paybill_head_mpg ph where";
					query+=" ph.paybill_month =" +report.getParameterValue( "Month" );
					query+=" and ph.paybill_year = "+report.getParameterValue( "Year" );
					query+=" and ph.bill_no in (select sb.BILL_GROUP_ID from mst_dcps_bill_group sb where";
					if( BillNo != null && BillNo != "" && !BillNo.equals("-1"))
					{
						query+=" sb.BILL_GROUP_ID ="+BillNo+" and";
					}
					query+=" sb.loc_id ="+locationId+") and ph.approve_flag in (0,1)";
					query+=" and ph.loc_id = "+locationId+") and pb.emp_id is  null)";

					query+="  order by psr_no";


				}

				logger.info(query);

				Query sqlQuery = hibSession.createSQLQuery(query);	      	
				//				
				List RowList=sqlQuery.list();
				if(RowList.size()!=0)
				{
					ArrayList dataList=new ArrayList();
					int cnt=1;

					Iterator itr = RowList.iterator();
					//					

					String empName;
					String desg;
					String gpf_num;
					String panno;
					String accno;
					String bankName;
					String branchName;
					long psrNo;
					int pageCnt=1;
					if(report.getParameterValue("Vacant").equals("WITHOUT VACANT"))
					{
						
						logger.info("coming or not vacant");
						int i=0;
						while (itr.hasNext())	
						{
							Object[] rowList = (Object[]) itr.next();

							empName = (String)(rowList[0]!=null?rowList[0]:"");
							desg = (String)(rowList[1]!=null?rowList[1]:"");
							gpf_num = (String)(rowList[2]!=null?rowList[2]:"");
							panno = (String)(rowList[3]!=null?rowList[3]:"");
							accno = (String)(rowList[4]!=null?rowList[4]:"");
							bankName = (String)(rowList[5]!=null?rowList[5]:"");
							if(bankName.equalsIgnoreCase("State Bank of India"))
							{
								bankName="SBI";
							}
							if(bankName.equalsIgnoreCase("STATE BANK OF SAURASHTRA"))
							{
								bankName="SBS";
							}

							if(bankName.equalsIgnoreCase("ICICI Bank Ltd."))
							{
								bankName="ICICI";
							}
							if(bankName.equalsIgnoreCase("Development Credit Bank"))
							{
								bankName="DCB";
							}
							if(bankName.equalsIgnoreCase("HDFC BANK"))
							{
								bankName="HDFC";
							}
							if(bankName.equalsIgnoreCase("CORPO.BANK"))
							{
								bankName="CORPO.";
							}
							if(bankName.equalsIgnoreCase("VIJYA BANK"))
							{
								bankName="VIJYA";
							}
							if(bankName.equalsIgnoreCase("Punjab National Bank"))
							{
								bankName="PNB";
							}
							if(bankName.equalsIgnoreCase("Axis Bank Ltd."))
							{
								bankName="ICICI";
							}
							if(bankName.equalsIgnoreCase("ICICI Bank Ltd."))
							{
								bankName="Axis";
							}


							if(bankName.equalsIgnoreCase("Dena bank"))
							{
								bankName="Dena";
							}
							if(bankName.equalsIgnoreCase("Narmada Bank"))
							{
								bankName="Narmada";
							}
							if(bankName.equalsIgnoreCase("BANK OF INDIA"))
							{
								bankName="BOI";
							}
							if(bankName.equalsIgnoreCase("Bank Of Baroda"))
							{
								bankName="BOB";
							}
							branchName = (String)(rowList[6]!=null?rowList[6]:"");
							if(branchName.equalsIgnoreCase("New Sachivalaya Complex branch"))
							{
								branchName="NSC";
							}
							if(branchName.equalsIgnoreCase("Main Branch"))
							{
								branchName="Main";
							}
							if(branchName.equalsIgnoreCase("SBI UDHYOG BHAVAN"))
							{
								branchName="SUB";
							}
							if(branchName.equalsIgnoreCase("Vidhan sabha Branch"))
							{
								branchName="Vidhan sabha";
							}
							if(branchName.equalsIgnoreCase("CORPO.BANK UDYOGBHAVAN"))
							{
								branchName="CORPO";
							}
							if(branchName.equalsIgnoreCase("HDFC BANK-Main Branch"))
							{
								branchName="HDFC-Main";
							}

							if(branchName.equalsIgnoreCase("Cantonment Branch"))
							{
								branchName="Cantonment";
							}



							StringBuffer bankBranch=new StringBuffer();
							bankBranch.append(bankName);
							if(!branchName.equals("-") && !branchName.equals(" "))
							{
								bankBranch.append("-"+branchName);
							}

							long employeeId = Long.parseLong((rowList[7]!=null?rowList[7].toString():"0").toString());
							String gpfAcctNo = payBillDAOImpl.getGPFAcctNoFromEmployeeId(employeeId, locationId);
							String pfSeries = payBillDAOImpl.getPFSeriesFromEmployeeId(employeeId, locationId);
							logger.info("gpfAcctNo********" + gpfAcctNo);
							
							if(!pfSeries.equals(null) && !pfSeries.equals("") && !pfSeries.equalsIgnoreCase("DCPS"))
							{
							gpfAcctNo=pfSeries+"/"+gpfAcctNo;
							}
							else
							{
								gpfAcctNo=gpfAcctNo;
							}
							
							ArrayList row = new ArrayList();
							if(report.getReportCode().equals("43") || report.getReportCode().equals("5000005"))
							{							
								row.add(cnt);
								row.add(empName);
								row.add(desg);
								row.add(gpfAcctNo);
								row.add(panno);
								row.add(accno);
								row.add(bankBranch);
								row.add("-");
								row.add("-");
								row.add("-");
								
								/*String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
								logger.info(" nof rec out====>"+noOfRec);
								if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
								{
									logger.info("No Of Rec is*****************====>"+noOfRec);
									finalpagesize=Integer.parseInt(noOfRec);
								}
*/
								DataList.add(row);

								if((cnt%finalpagesize)==0) 
								{

									//pageCnt++;
									row = new ArrayList();
									row.add(new PageBreak());
									row.add("Data");
									DataList.add(row); 
									
								}
								cnt++; 
							}		

							i++;
						}
					}		
					else
					{
						
						logger.info("coming or not all vacant");
						int i=0;
						while (itr.hasNext())	
						{
							Object[] rowList = (Object[]) itr.next();
							psrNo =Long.parseLong((rowList[0]!=null?rowList[0].toString():"0").toString());
							empName= (String)(rowList[1]!=null?rowList[1]:"");
							desg = (String)(rowList[2]!=null?rowList[2]:"");

							ArrayList row = new ArrayList();

							if(report.getReportCode().equals("43") || report.getReportCode().equals("5000005"))
							{							

								row.add(psrNo);

								if(!empName.trim().equals(""))
								{
									row.add(empName);

								}
								else
								{
									row.add("VacantPost");
								}
								row.add(desg);
								row.add("");
								row.add("");
								row.add("");
								row.add("");
								row.add("");
								row.add("");
								row.add("");


								DataList.add(row);

								if((cnt%finalpagesize)==0) 
								{

									row = new ArrayList();
									row.add(new PageBreak());

									DataList.add(row);

								}
								cnt++; 
							}		


							
							i++;
						}
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

		if(  report.getReportCode().equals("43") || report.getReportCode().equals("5000005"))
		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
		}

		if(  report.getReportCode().equals("5000005") )

		{            
			report.setParameterValue("Department",locationId+"");
		}

		return report;
	}




}

