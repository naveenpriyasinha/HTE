package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

@SuppressWarnings( {  "unchecked", "static-access" })
public class BankdtlReportDAO extends DefaultReportDataFinder implements ReportDataFinder
{

	private static Logger logger = Logger.getLogger(BankdtlReportDAO.class);

	private StyleVO[] selfCloseVO = null;

	final String CheckIfNull(Object lStra)
	{
		String lStrb = "";
		if (lStra != null)
		{
			lStrb = (((String) lStra).trim());

		}
		return lStrb;
	}

	public Collection findReportData(ReportVO report, Object criteria) throws ReportException
	{
		int finalpagesize = 50;

		Map requestKeys = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map) requestKeys.get("serviceMap");
		Map baseLoginMap = (Map) serviceMap.get("baseLoginMap");
		CmnLocationMst locationVO = (CmnLocationMst) baseLoginMap.get("locationVO");
		String locationName = locationVO.getLocName();
		long locationId = locationVO.getLocId();
		String locationNameincaps = locationName.toUpperCase();

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");

		ArrayList DataList = new ArrayList();

		try
		{

			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
			SessionFactory sessionFactory = serviceLocator.getSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			Session hibSession = sessionFactory.getCurrentSession();
			ServiceLocator serv1 = (ServiceLocator) requestKeys.get("serviceLocator");

			StyleVO[] colorStyleVO1 = new StyleVO[1];
			colorStyleVO1[0] = new StyleVO();
			colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			colorStyleVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);

			StyleVO[] colorStyleVO11 = new StyleVO[1];
			colorStyleVO11[0] = new StyleVO();
			colorStyleVO11[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			colorStyleVO11[0].setStyleValue("blue");
			selfCloseVO = new StyleVO[2];
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

			StyleVO[] centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Left");

			PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv1.getSessionFactory());
			boolean isBillDefined = paybillDAO.isBillsDefined(locationId);

			final long arrearbillTypeId = Integer.parseInt(resourceBundle.getString("arrearbillTypeId"));
			final String ICICI_BANK_ID = resourceBundle.getString("ICICI_BANK_ID");

			String portType = "";
			String BillNo = "";
			String desigName = "";
			String deptName = "";
			String cardexCode = "";
			ServiceLocator serv = (ServiceLocator) requestKeys.get("serviceLocator");

			if (report.getReportCode().equals("6") || report.getReportCode().equals("2500006"))
			{

				String month = "";
				String year = "";
				String bank = "";
				String Department = "";
				month = CheckIfNull(report.getParameterValue("Month"));
				year = CheckIfNull(report.getParameterValue("Year"));
				bank = CheckIfNull(report.getParameterValue("bank"));
				Department = CheckIfNull(report.getParameterValue("Department"));

				//added by ravysh
				String billType = CheckIfNull(report.getParameterValue("billTypePara"));
				//added by samir joshi for bill no wise report
				if (Department.equals("") || Department.equals("-1"))
					Department = locationId + "";
				else
					locationId = Long.parseLong(Department);

				String BillNoinner = "";//GAD specific
				BillNoinner = CheckIfNull(report.getParameterValue("Bill No"));
				StringTokenizer st1 = new StringTokenizer(BillNoinner, "~");
				int l = 0;
				String subheadCode = "";
				String classIds = "";
				String desgnIds = "";
				while (st1.hasMoreTokens())
				{
					if (l == 0)
						subheadCode = st1.nextToken();
					else if (l == 1)
						classIds = st1.nextToken();
					else if (l == 2)
						desgnIds = st1.nextToken();
					else if (l == 3)
						portType = st1.nextToken();
					else if (l == 4)
						BillNo = st1.nextToken();
					l++;
				}
				BillNo = portType;
				String DeptName = locationNameincaps;

				PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				DeptName = payBillDAOImpl.getOffice(locationId);

				//ended by samir joshi/////
				//for report footer
				/*          ReportAttributeVO ravo = new ReportAttributeVO();

				ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
				ravo.setLocation(IReportConstants.LOCATION_FOOTER);
				ravo.setAlignment(IReportConstants.ALIGN_RIGHT);

				ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

				  //add the attribute to the report instance
				report.addReportAttributeItem(ravo);*/
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
				ArrayList tblData = new ArrayList();

				ArrayList trow1 = new ArrayList();

				trow1.add(" ");
				trow1.add(" ");
				trow1.add(" " + System.getProperty("line.separator"));
				tblData.add(trow1);//added first row of the tabular data

				ArrayList trow2 = new ArrayList();

				trow2.add(" ");
				trow2.add(" ");
				trow2.add(" " + System.getProperty("line.separator"));
				tblData.add(trow2);//added second row of the tabular data

				ArrayList trow4 = new ArrayList();

				trow4.add(" ");
				trow4.add(" ");
				trow4.add(new StyledData(desigName, centerboldStyleVO1));
				tblData.add(trow4);//added second row of the tabular data

				ArrayList trow5 = new ArrayList();

				trow5.add(" ");
				trow5.add(" ");
				trow5.add(new StyledData(deptName, centerboldStyleVO1));
				tblData.add(trow5);//added second row of the tabular data


				//trow3.add(" ");
				//trow3.add(" ");
				//trow3.add(new StyledData(cityName,centerboldStyleVO1));

				//tblData.add(trow3);//added third row of the tabular data

				ArrayList trow6 = new ArrayList();
				trow6.add(" ");
				trow6.add(" ");
				trow6.add(new StyledData(cardexCode, centerboldStyleVO1));

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
				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
				// sdfObj = new SimpleDateFormat("MMM");
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				sdfObj = new SimpleDateFormat("MMM");
				java.util.Date startMonthDate = cal.getTime();
				sdfObj = new SimpleDateFormat("MMM");
				String Month = sdfObj.format(startMonthDate);

				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();
				if (BillNo != null && BillNo != "")//gampa
				{

					String deptHeader = DeptName + System.getProperty("line.separator") + Month + ". " + year + System.getProperty("line.separator") + "CROSSED CHEQUES MAY PLEASE BE ISSUED AS UNDER  " + System.getProperty("line.separator") + System.getProperty("line.separator") + "Bill No:" + BillNo;
					stData.add(new StyledData(deptHeader, headerStyleVo));
					styleList.add(stData);
					TabularData tData = new TabularData(styleList);

					tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
					tData.addStyle(IReportConstants.BORDER, "No");
					tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
					report.setAdditionalHeader(tData);
				}
				else
				{
					//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
					// stData.add(DeptName+System.getProperty("line.separator")+Month+". "+year+System.getProperty("line.separator")+"CROSSED CHEQUES MAY PLEASE BE ISSUED AS UNDER  "+System.getProperty("line.separator")+System.getProperty("line.separator"));
					String deptHeader = DeptName + System.getProperty("line.separator") + Month + ". " + year + System.getProperty("line.separator") + "CROSSED CHEQUES MAY PLEASE BE ISSUED AS UNDER  " + System.getProperty("line.separator") + System.getProperty("line.separator");
					stData.add(new StyledData(deptHeader, headerStyleVo));
					styleList.add(stData);
					TabularData tData = new TabularData(styleList);
					//tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
					//tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
					//tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
					tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
					tData.addStyle(IReportConstants.BORDER, "No");
					tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
					report.setAdditionalHeader(tData);
				}
				//if(!BillNo.equals(""))
				//report.setReportName(DeptName+System.getProperty("line.separator")+Month+". "+year+System.getProperty("line.separator")+"CROSSED CHEQUES MAY PLEASE BE ISSUED AS UNDER  "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
				//else
				//report.setReportName(DeptName+System.getProperty("line.separator")+Month+". "+year+System.getProperty("line.separator")+"CROSSED CHEQUES MAY PLEASE BE ISSUED AS UNDER  "+System.getProperty("line.separator")+System.getProperty("line.separator"));

				// comented by samir joshi for sql query in sum function
				//by manoj for head change
				cal = Calendar.getInstance();
				sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				java.util.Date finYrDate = cal.getTime();
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
				java.util.Date endMonthDate = cal.getTime();


				int currentMonthBill = 0;
				Date currentDate = new Date();

				if (currentDate.after(finYrDate) && currentDate.before(endMonthDate))
					currentMonthBill = 1;
				logger.info("testing for currentMonth " + currentMonthBill + " currentDate " + currentDate + " finYrDate " + finYrDate + " endMonthDate " + endMonthDate);

				//end by manoj for head change
				String query = " select b.hrEisBankMst.bankId, ";

				query += " concat(concat(b.hrEisBankMst.bankName,','),b.hrEisBranchMst.branchName), ";

				if (!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{
					query += "  sum(a.netTotal),0 ";//non govt deduction 0 for arears
				}
				else
				{
					query += "  sum(a.netTotal),sum(non.totalNonGovtDeduc) ";
				}
				if (!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{
					query += " from HrPayArrearPaybill a,  ";
				}
				else
				{
					query += " from HrPayPaybill a,  ";
				}

				/*if(currentMonthBill!=1)
				{
					query+=" PaybillHeadMpg bhm, ";
				}
				*/
				query += " HrEisBankDtls b ,OrgPostDetailsRlt pt, ";
				query += " OrgUserpostRlt           USRPST, ";
				query += " HrPayOrderHeadMpg ORDHD, ";
				query += " HrPayOrderHeadPostMpg ORDPST, ";

				if (!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{
					// HrPayPayslipNonGovt  not for arrears
				}
				else
				{
					query += " HrPayPayslipNonGovt non, ";
				}

				query += " PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm ";
				query += " where ";

				if (!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{
					query += "  a.hrEisEmpMst = b.hrEisEmpMst.empId  ";
					String arrearType = "";
					arrearType = CheckIfNull(report.getParameterValue("Arrear List"));
					if (!arrearType.equals("") && !arrearType.equals("-1"))
					{
						query += "  and a.salRevId.salRevId=" + arrearType + " ";
					}
				}
				else
				{
					query += " non.paybillID=a.id and a.hrEisEmpMst.empId = b.hrEisEmpMst.empId  ";
				}
				//query += " and ORDPST.orderHeadId = ORDHD.id.orderHeadId  ";
				/*if(currentMonthBill!=1)
				{
					query += " and ORDPST.orderHeadId = ORDHD.id.orderHeadId ";
				}
				else*/
				//added by ravysh for main and supplimentary bill
				if (!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
					query += "  and bhm.billTypeId.lookupId=" + Long.parseLong(billType);

				query += " and ORDPST.orderHeadId = ORDHD.orderHeadId and a.netTotal>0 ";

				query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
				query += " and USRPST.orgUserMst.userId = a.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
				query += " and pt.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";

				if (!month.equals("") && !month.equals("-1"))
					query += "and  bhm.month='" + month + "'";

				if (!year.equals("") && !year.equals("-1"))
					query += "and bhm.year= '" + year + "'";

				if (!bank.equals("") && !bank.equals("-1"))
					query += "and b.hrEisBankMst.bankId= '" + bank + "'";

				if (!Department.equals("") && !Department.equals("-1"))
					query += "and pt.orgPostMst.postId= a.orgPostMst.postId and pt.cmnLocationMst.locId=" + Department;

				if (isBillDefined && !BillNo.equals(""))
				{
					// Updated By Urvin shah
					//query+="  and  a.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId ) and " ;	
					/*if(currentMonthBill==1)
						query+="   ORDHD.subheadId in (select bill.subheadId from HrPayBillHeadMpg bill where bill.billId ="+BillNo+" ) and  ";*/
					query += "  and pt.orgPostMst.postId = a.orgPostMst.postId  ";
					//End.
					query += " and hpbsm.billId=" + BillNo + "  ";
				}
				else
				{

					if (subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
					{
						query += " and   ORDHD.subheadId =" + subheadCode + " ";
					}
					if (classIds != null && !classIds.equals("") && !classIds.equals("-1"))
					{
						query += "  and a.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in (" + classIds + ")";
					}
					if (desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
					{
						query += "  and pt.orgDesignationMst.dsgnId in (" + desgnIds + ")  ";
					}
				}
				query += "   and bhm.approveFlag in(0,1) ";
				//if(currentMonthBill!=1)
				//change made by chirashree
				query += "  and bhm.hrPayPaybill = a.paybillGrpId   and hpbsm.billHeadId = bhm.billNo.billHeadId ";
				//ended by chirashree
				query += " and bhm.orgGradeMst.gradeId=a.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId ";//Date 5-Nov-08 by rahul
				query += " group by b.hrEisBankMst.bankId,b.hrEisBankMst.bankName,b.hrEisBranchMst.branchId,b.hrEisBranchMst.branchName  ";
				query += " order by  b.hrEisBankMst.bankId,b.hrEisBranchMst.branchName ";

				//sql query for in inner query sum not work proper in hql 

				/*String query="";
				  query = " select hreisbankd2_.BANK_ID as col_0_0_,       hreisbankm7_.BANK_NAME as col_1_0_,       sum(hrpaypaybi0_.NET_TOTAL) as col_2_0_,       sum((select sum(hrpaynongo8_.NON_GOV_DEDUC_AMOUNT)            from HR_PAY_NON_GOV_DEDUCTION hrpaynongo8_,  ";
				  query+="hr_eis_emp_mst           hreisempms9_,                  hr_eis_emp_mst           hreisempms10_            where hrpaynongo8_.EMP_ID = hreisempms9_.emp_id and  hrpaypaysl1_.EMP_ID = hreisempms10_.emp_id and               hreisempms9_.emp_mpg_id = hreisempms10_.emp_mpg_id and                  hrpaynongo8_.NON_GOV_DEDUC_EFFT_START_DT <= '01-Jul-2008' and                  hrpaynongo8_.NON_GOV_DEDUC_EFFT_END_DT >= '01-Jul-2008')) as col_3_0_ ";
				  query+="  from HR_PAY_PAYBILL             hrpaypaybi0_,       HR_PAY_PAYSLIP             hrpaypaysl1_,       HR_EIS_BANK_DTLS           hreisbankd2_,       hr_eis_bank_mst            hreisbankm7_,       org_post_details_rlt       orgpostdet3_,       org_userpost_rlt           orguserpos4_,      HR_PAY_ORDER_HEAD_MPG      hrpayorder5_,       HR_PAY_ORDER_HEAD_POST_MPG hrpayorder6_,       hr_eis_emp_mst             hreisempms11_,       org_emp_mst                orgempmst12_,       cmn_location_mst           cmnlocatio13_  ";
				  query+="where hreisbankd2_.BANK_ID = hreisbankm7_.BANK_ID and       hrpaypaybi0_.EMP_ID = hreisempms11_.emp_id and       hreisempms11_.emp_mpg_id = orgempmst12_.emp_id and       orgpostdet3_.loc_id = cmnlocatio13_.loc_id and       hrpaypaybi0_.EMP_ID = hreisbankd2_.BANK_EMP_ID and       hrpayorder6_.ORDER_HEAD_ID = hrpayorder5_.ORDER_HEAD_ID and       orguserpos4_.post_id = hrpayorder6_.POST_ID and       orguserpos4_.user_id = orgempmst12_.user_id and       orgpostdet3_.post_id = orguserpos4_.post_id and       hrpaypaybi0_.ID = hrpaypaysl1_.PAYBILL_ID ";

				  if(!month.equals("")&&!month.equals("-1"))            	
						query+="and  hrpaypaybi0_.PAYBILL_MONTH='"+month+"'";

				  if(!year.equals("")&&!year.equals("-1"))            	
						query+="and hrpaypaybi0_.PAYBILL_YEAR = '"+year+"'";

				  if(!bank.equals("")&&!bank.equals("-1"))            	
						query+="and hreisbankd2_.BANK_ID= '"+bank+"'";

				  if(!Department.equals("")&&!Department.equals("-1"))            	
						query+="and orgpostdet3_.post_id= hrpaypaybi0_.post_id and cmnlocatio13_.LOCATION_CODE=" + Department + "  ";

				  if(isBillDefined&&!BillNo.equals(""))
				  {

				  	query+="  and  hrpaypaybi0_.post_id in (select p.post_id from hr_pay_post_psr_mpg p where p.bill_no in (select bill.bill_subhead_id from  hr_pay_bill_subhead_mpg bill where bill.bill_no = "+BillNo+" ) )  and "; 
				  	query+="   hrpayorder5_.subhead_id in (select bill.subhead_code from hr_pay_bill_subhead_mpg bill where bill.bill_no ="+BillNo+" )  and orgpostdet3_.post_id = hrpaypaybi0_.post_id  "; 
				  }
				  else
				  {
				    if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
				  {
				  	query+="  and  hrpayorder5_.SUBHEAD_ID ="+subheadCode+" ";
				    }
				    if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
				  {
				    	query+="  and orgempmst12_.grade_id  in ("+classIds+")";
				    }
				    if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
				  {
				    	query+="  and orgpostdet3_.dsgn_id  in (" +desgnIds+ ")  ";
				    }
				  }
				    query+="   and hrpaypaybi0_.approve_flag in (0,1) ";
				  query+="  group by hreisbankd2_.BANK_ID, hreisbankm7_.BANK_NAME ";	    
				 */
				logger.info("***Query for bank details**" + query);

				Query sqlQuery = hibSession.createQuery(query);
				// Added by Rajan if no record found in query
				/* if(sqlQuery.list().size()!=0)
				  {*/
				List RowList = sqlQuery.list();

				logger.info("*************************" + RowList.size());
				int cnt = 1;

				Iterator itr = RowList.iterator();

				double AmountTotal = 0;
				while (itr.hasNext())
				{
					Object[] rowList = (Object[]) itr.next();
					String bankName = (String) (rowList[1] != null ? rowList[1] : "");
					double Amount = Double.parseDouble((rowList[2] != null ? rowList[2].toString() : 0).toString());
					double nongovdeduct = Double.parseDouble((rowList[3] != null ? rowList[3].toString() : 0).toString());
					Amount = Amount - nongovdeduct;
					AmountTotal += Amount;
					ArrayList row = new ArrayList();
					row.add(cnt);
					row.add(bankName + ",Ghandhinagar");
					row.add(Math.round(Amount));
					DataList.add(row);
					cnt++;
				}

				StyleVO[] centerboldStyleVO2 = new StyleVO[2];
				centerboldStyleVO2[0] = new StyleVO();
				centerboldStyleVO2[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				centerboldStyleVO2[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				centerboldStyleVO2[1] = new StyleVO();
				centerboldStyleVO2[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				centerboldStyleVO2[1].setStyleValue("Center");

				query = " SELECT sum(n.lic) as lic, sum(n.societyOld) as old, sum(n.societyNew) as  new_soc, ";
				query += " sum(n.karamChariBank) as karam, sum(n.nagrikBank) as nag, ";
				query += " sum(n.postOfficeRecurringDeposit) as por, sum(n.chiefMinisterReliefFund) as cmrf , ";
				query += " sum(n.salarySavingFund) as ssf,sum(n.underSecGAD) ";

				query += " From HrPayPayslipNonGovt n ";

				query += " WHERE n.paybillID.id in ";

				query += "  ( SELECT p.id FROM HrPayPaybill p WHERE p.paybillGrpId in  ( SELECT pb.hrPayPaybill FROM PaybillHeadMpg pb WHERE";
				//added by ravysh
				if (!billType.equals("") && !billType.equals(" ") && !billType.equals("-1"))
					query += "   pb.billTypeId.lookupId=" + Long.parseLong(billType) + " and ";

				if (!month.equals("") && !month.equals("-1"))
					query += " pb.month='" + month + "' AND ";

				if (!year.equals("") && !year.equals("-1"))
					query += " pb.year= '" + year + "' AND ";

				query += " pb.approveFlag IN (0, 1) ";
				if (isBillDefined && !BillNo.equals(""))
				{
					/*query+=" AND  p.orgPostMst.postId IN (SELECT p.postId FROM HrPayPsrPostMpg p WHERE p.billNo IN  ";
					query+="  ( SELECT bill.billHeadId FROM HrPayBillHeadMpg bill  WHERE ";   
					query+="  bill.billId = "+BillNo+" )) ";*/

					query += " and pb.billNo.billHeadId in ( SELECT bill.billHeadId FROM HrPayBillHeadMpg bill  WHERE ";
					query += "  bill.billId = " + BillNo + " ) ";
				}
				query += "))";

				if (!Department.equals("") && !Department.equals("-1"))
					query += " and n.paybillID.cmnLocationMst.locId=" + Department;

				/*
				 * 				query = " select sum(n.lic) as lic,sum(n.society_old) as old ,sum(n.society_new) as  new_soc , ";
				query+=" sum(n.karamchari_bank) as karam,sum(n.nagrik_bank) as nag, ";
				query+=" sum(n.post_office_recurring_deposit) as por,sum(n.chief_minister_relief_fund) as cmrf , ";
				query+=" sum(n.sal_saving_fund) as ssf ";

				query+=" from hr_pay_payslip_non_govt n ";

				query+=" where n.paybill_id in "; 
				query+="  (select p.paybill_id from paybill_head_mpg p where ";

				if(!month.equals("")&&!month.equals("-1"))            	
					query+=" p.paybill_month='"+month+"' and ";

				if(!year.equals("")&&!year.equals("-1"))            	
					query+=" p.paybill_year= '"+year+"' and ";

				if(isBillDefined&&!BillNo.equals(""))
				{
					query+="  and p.paybill_id in (select pb.id from HR_PAY_PAYBILL pb where  pb.post_id in (select p.post_id from hr_pay_post_psr_mpg p where p.bill_no in  ";
					query+="  (select bill.bill_subhead_id from hr_pay_bill_subhead_mpg bill  where ";   
					query+="  bill.bill_no = "+BillNo+"))) and "; 
				}

				query+="  p.approve_flag in (0, 1)) ";    
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 


				  query = " select  ";
				  query+=" b.hrPayDeducTypeMst.deducCode,sum(non.totalNonGovtDeduc) ";
				  query+=" from HrPayPaybill a, HrPayPayslipNonGovt non, ";
				  query+=" HrPayNonGovDeduction b,OrgPostDetailsRlt pt, ";
				  query+=" OrgUserpostRlt           USRPST, ";
				query+=" HrPayOrderHeadMpg ORDHD, ";
				query+=" HrPayOrderHeadPostMpg ORDPST ";
				  query+=" where ";
				  query+=" a.hrEisEmpMst.empId = b.hrEisEmpMst.empId and non.paybillID=a.id    ";
				  query+=" and  b.nonGovDeducEfftStartDt <= a.createdDate "+
					" and b.nonGovDeducEfftEndDt >=a.createdDate  ";
				  query += " and ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where a.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )";
				  query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
				  query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
				  query += " and USRPST.orgUserMst.userId = a.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
				  query += " and pt.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";

				  if(!month.equals("")&&!month.equals("-1"))            	
						query+=" and  a.month='"+month+"'";

				  if(!year.equals("")&&!year.equals("-1"))            	
						query+=" and a.year= '"+year+"'";

				  if(!bank.equals("")&&!bank.equals("-1"))            	
				  	query+=" and b.hrPayDeducTypeMst.deducCode= '"+bank+"'";

				  if(!Department.equals("")&&!Department.equals("-1"))            	
						query+=" and pt.orgPostMst.postId= a.orgPostMst.postId and pt.cmnLocationMst.locationCode=" + Department +"  ";
				  if(isBillDefined&&!BillNo.equals(""))
				  {

				  	query+="  and  a.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "+BillNo+" ) )  and "; 
				  	query+="   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="+BillNo+" )  and pt.orgPostMst.postId = a.orgPostMst.postId  "; 
				  }
				  else
				  {	

				    if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
				  {
				  	query+=" and   ORDHD.subheadId ="+subheadCode+" ";
				    }
				    if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
				  {
				    	query+="  and a.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")";
				    }
				    if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
				  {
				    	query+="  and pt.orgDesignationMst.dsgnId in (" +desgnIds+ ")  ";
				    }
				  }
				    query+="   and a.approveFlag in(0,1) ";
				  query+=" group by b.hrPayDeducTypeMst.deducCode  ";*/

				logger.info("***Query for bank details**" + query);

				sqlQuery = hibSession.createQuery(query);

				RowList = sqlQuery.list();
				logger.info("*************************" + RowList.size());

				if (RowList != null && RowList.size() > 0)
				{

				}

				ArrayList rowdata = new ArrayList();
				StyledData dataStyle1 = new StyledData();
				dataStyle1.setStyles(centerboldStyleVO2);
				//dataStyle1.set
				dataStyle1.setData("*****Non government Deduction Cheques*****");
				dataStyle1.setColspan(3);
				rowdata.add(dataStyle1);
				rowdata.add("");
				rowdata.add("");
				rowdata.add("");
				if (!billType.equals("") && billType.equals(String.valueOf(arrearbillTypeId)))
				{
					// No need of label	
				}
				else
					DataList.add(rowdata);

				ArrayList row = new ArrayList();
				itr = RowList.iterator();
				while (itr.hasNext())
				{
					Object[] rowList = (Object[]) itr.next();
					//long bankId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());

					long lic = (Long.parseLong((rowList[0] != null ? rowList[0] : 0).toString()));
					long old = (Long.parseLong((rowList[1] != null ? rowList[1] : 0).toString()));
					long new_soc = (Long.parseLong((rowList[2] != null ? rowList[2] : 0).toString()));
					long karam = (Long.parseLong((rowList[3] != null ? rowList[3] : 0).toString()));
					long nag = (Long.parseLong((rowList[4] != null ? rowList[4] : 0).toString()));
					long por = (Long.parseLong((rowList[5] != null ? rowList[5] : 0).toString()));
					long cmrf = (Long.parseLong((rowList[6] != null ? rowList[6] : 0).toString()));
					long ssf = (Long.parseLong((rowList[7] != null ? rowList[7] : 0).toString()));
					long underSecGAD = (Long.parseLong((rowList[8] != null ? rowList[8] : 0).toString()));

					if (new_soc != 0)
					{
						row = new ArrayList();
						row.add(cnt++);
						row.add("The Gujrat Sachivalaya Employee CO-Op. New Credit Society Ltd. ");
						row.add(new_soc);
						AmountTotal += new_soc;
						DataList.add(row);

					}
					if (old != 0)
					{
						row = new ArrayList();
						row.add(cnt++);
						row.add("The Gujrat Sachivalaya Employee CO-Op. Credit Society Ltd. ");
						row.add(old);
						AmountTotal += old;
						DataList.add(row);

					}
					if (lic != 0)
					{
						row = new ArrayList();
						row.add(cnt++);
						row.add("L.I.C. of India, Gandhinagar.");
						row.add(lic);
						AmountTotal += lic;
						DataList.add(row);

					}
					if (karam != 0)
					{
						row = new ArrayList();
						row.add(cnt++);
						row.add("The Karmachari Co-Op. Bank Ltd., Ahmedabad ");
						row.add(karam);
						AmountTotal += karam;
						DataList.add(row);

					}
					if (nag != 0)
					{
						row = new ArrayList();
						row.add(cnt++);
						row.add("The Gandhinagar Nagrik Co-Op.Bank Ltd., Gandhinagar ");
						row.add(nag);
						AmountTotal += nag;
						DataList.add(row);

					}
					if (por != 0)
					{
						row = new ArrayList();
						row.add(cnt++);
						row.add("Post Office Recurring Deposit. ");
						row.add(por);
						AmountTotal += por;
						DataList.add(row);

					}
					if (cmrf != 0)
					{
						row = new ArrayList();
						row.add(cnt++);
						row.add("Chief Minister Relief Fund. ");
						row.add(cmrf);
						AmountTotal += cmrf;
						DataList.add(row);

					}
					if (ssf != 0)
					{
						row = new ArrayList();
						row.add(cnt++);
						row.add("Salary Saving Fund. ");
						row.add(ssf);
						AmountTotal += ssf;
						DataList.add(row);

					}
					if (underSecGAD != 0)
					{
						row = new ArrayList();
						row.add(cnt++);
						row.add("U.S. GAD ");
						row.add(underSecGAD);
						AmountTotal += underSecGAD;
						DataList.add(row);

					}

				}

				row = new ArrayList();
				row.add("");
				dataStyle1 = new StyledData();
				dataStyle1.setStyles(colorStyleVO1);
				dataStyle1.setData("GRAND TOTAL");
				row.add(dataStyle1);
				dataStyle1 = new StyledData();
				dataStyle1.setStyles(colorStyleVO1);
				dataStyle1.setData(Math.round(AmountTotal)/*+System.getProperty("line.separator")+AmountTotalInWords*/);
				row.add(dataStyle1);
				DataList.add(row);
				ArrayList row1 = new ArrayList();

				StyledData dataStyle2 = new StyledData();
				dataStyle2.setStyles(centerboldStyleVO);
				dataStyle2.setData("Rupees  " + ConvertNumbersToWord.convert(Math.round(AmountTotal)) + " only.");
				dataStyle2.setColspan(3);
				row1.add(dataStyle2);

				for (int c = 0; c < 3; c++)
					row1.add("");

				DataList.add(row1);
				logger.info("**********************" + DataList.size());
				/*}
				  else
				  {
					   report.setReportName("No Record Found");
				  	   ArrayList d = new ArrayList();
					   ArrayList r = new ArrayList();
					   r.add("");
					   d.add(r);
				  	   TabularData td  = new TabularData(d);
				  	   td.addStyle(IReportConstants.ADDL_HEADER_LOCATION, IReportConstants.VALUE_ADDL_HEADER_LOCATION_BELOW);
				  	   td.addStyle(IReportConstants.BORDER, "No"); 
				  	   report.setAdditionalHeader(td);
				  }*/
				return DataList;

			}

			//	-----------------------------------------------------------------------------------------------------------------------------------

			if (report.getReportCode().equals("7") || report.getReportCode().equals("5000004"))
			{

				StyleVO[] boldStyleVO1 = new StyleVO[1];
				boldStyleVO1[0] = new StyleVO();
				boldStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				boldStyleVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);

				colorStyleVO1 = new StyleVO[1];
				colorStyleVO1[0] = new StyleVO();
				colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
				colorStyleVO1[0].setStyleValue("blue");
				selfCloseVO = new StyleVO[1];
				selfCloseVO[0] = new StyleVO();
				selfCloseVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
				selfCloseVO[0].setStyleValue("javascript:self.close()");

				StyleVO[] headerStyleVo1 = new StyleVO[4];
				headerStyleVo1[0] = new StyleVO();
				headerStyleVo1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				headerStyleVo1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				headerStyleVo1[1] = new StyleVO();
				headerStyleVo1[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				headerStyleVo1[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				headerStyleVo1[2] = new StyleVO();
				headerStyleVo1[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
				headerStyleVo1[2].setStyleValue("18");
				headerStyleVo1[3] = new StyleVO();
				headerStyleVo1[3].setStyleId(IReportConstants.STYLE_FONT_FAMILY);
				headerStyleVo1[3].setStyleValue(IReportConstants.VALUE_FONT_FAMILY_ARIAL);


				String empid = CheckIfNull(report.getParameterValue("employeeName"));
				String Department = "";


				String month = "";
				String year = "";
				Department = CheckIfNull(report.getParameterValue("Department"));
				if (Department.equals("") || Department.equals("-1"))
					Department = locationId + "";
				else
					locationId = Long.parseLong(Department);

				String BillNoinner = "";//GAD specific
				BillNoinner = CheckIfNull(report.getParameterValue("Bill No"));
				StringTokenizer st1 = new StringTokenizer(BillNoinner, "~");
				int l = 0;
				String subheadCode = "";
				String classIds = "";
				String desgnIds = "";
				while (st1.hasMoreTokens())
				{
					if (l == 0)
						subheadCode = st1.nextToken();
					else if (l == 1)
						classIds = st1.nextToken();
					else if (l == 2)
						desgnIds = st1.nextToken();
					else if (l == 3)
						portType = st1.nextToken();
					else if (l == 4)
						BillNo = st1.nextToken();
					l++;
				}

				//Added by abhilash	

				logger.info("bill no is ***********" + BillNo);
				logger.info("bill no is ***********" + BillNoinner);
				logger.info("bill no is ***********" + report.getParameterValue("Bill No"));

				if (BillNoinner.trim().equals("") || BillNo.equalsIgnoreCase(""))
				{
					logger.info("inside if condition bill no is ***********" + report.getParameterValue("Bill No"));
					BillNo = report.getParameterValue("Bill No") != null ? report.getParameterValue("Bill No").toString().trim() : "";
				}
				//Ended

				String DeptName = locationNameincaps;

				String Grade = "";
				String Designation = "";
				Grade = CheckIfNull(report.getParameterValue("Grade"));
				Designation = CheckIfNull(report.getParameterValue("Designation"));

				ArrayList tblData = new ArrayList();
				ArrayList trow1 = new ArrayList();
				trow1.add(System.getProperty("line.separator"));
				tblData.add(trow1);//added first row of the tabular data
				ArrayList trow2 = new ArrayList();
				trow2.add(System.getProperty("line.separator"));
				tblData.add(trow2);//added second row of the tabular data

				ArrayList trow4 = new ArrayList();
				StyledData sd2 = new StyledData();
				String footertxt = desigName + System.getProperty("line.separator") + deptName + System.getProperty("line.separator") + cardexCode;
				sd2.setData(footertxt);
				sd2.addStyle(IReportConstants.STYLE_FONT_WEIGHT, IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				sd2.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				sd2.addStyle(IReportConstants.STYLE_FONT_SIZE, "14");
				trow4.add("");
				trow4.add(sd2);
				tblData.add(trow4);//added second row of the tabular data

				TabularData tabularData = new TabularData(tblData);//initialize tabular data
				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
				tabularData.addStyle(IReportConstants.BORDER, "No");

				report.setGrandTotalTemplate(tabularData);
				month = CheckIfNull(report.getParameterValue("Month"));
				year = CheckIfNull(report.getParameterValue("Year"));

				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				java.util.Date date = cal.getTime();
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
				String[] monthName = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
				Integer in = Integer.parseInt(month);
				String monthname = monthName[in - 1];

				PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				DeptName = payBillDAOImpl.getOffice(locationId);
				/*List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);
				
				String ddocode ="";
				if(ddoList.size()>0)
					ddocode = ddoList.get(0).getDdoCode();*/
				String ddocode = "";
				long locactionId = Long.parseLong(baseLoginMap.get("locationId").toString());
				PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locactionId);
				if (ddoCodeList != null)
					logger.info("After query execution for DDO Code " + ddoCodeList.size());

				OrgDdoMst ddoMst = null;
				if (ddoCodeList != null && ddoCodeList.size() > 0)
				{
					ddoMst = ddoCodeList.get(0);
				}

				if (ddoMst != null)
				{
					ddocode = ddoMst.getDdoCode();
				}
				logger.info("DDO Code is " + ddocode);

				String TresuryCode = "";
				List TreasuryList = payBillDAOImpl.getTreasuryCodeAndTreasuryName(ddocode);

				if (TreasuryList != null && TreasuryList.size() != 0)
				{
					for (Iterator itr = TreasuryList.iterator(); itr.hasNext();)
					{

						Object[] rowList = (Object[]) itr.next();

						if (rowList[0] != null)
						{
						}
						if (rowList[1] != null)
						{
							TresuryCode = rowList[1].toString().trim();
						}

					}
				}

				sdfObj = new SimpleDateFormat("MMM");

				ArrayList stData = new ArrayList();
				ArrayList styleList = new ArrayList();
				String deptHeader = "";

				if (!BillNo.equals(""))
				{
					deptHeader = "Bank Statement For DDO Bank";
				}

				String Treasury = TresuryCode;
				StyledData styledHeader = new StyledData(deptHeader, headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
				styleList.add(stData);
				ArrayList r = new ArrayList();
				r.add(new StyledData("Month :" + monthname + " " + year, leftHeader));
				r.add(new StyledData(" ", rightHead));
				styleList.add(r);
				ArrayList r2 = new ArrayList();
				r2.add(new StyledData("Name of the Office :" + DeptName, leftHeader));
				r2.add(new StyledData("Treasury : " + Treasury + "  " + "DDO:" + ddocode, rightHead));
				styleList.add(r2);
				TabularData tData = new TabularData(styleList);
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);

				long nonGovDeducType = Long.parseLong(resourceBundle.getString("nonGovDeducType"));//Kishan
				List nonGovDeducCode = getDistinctDeducCode(locactionId, nonGovDeducType, session, Integer.valueOf(month), Integer.valueOf(year), BillNo);
				long mappedCompoSize = nonGovDeducCode.size();
				String mappedCompId = "";
				if (mappedCompoSize > 0)
				{
					for (int i = 0; i < mappedCompoSize; i++)
					{
						mappedCompId = mappedCompId + nonGovDeducCode.get(i) + ",";
					}
					mappedCompId = mappedCompId.substring(0, mappedCompId.lastIndexOf(","));
				}

				/*String query = "select eisother.EMP_ID ,desiii.dsgn_shrt_name ,orge.grade_id ,gradddd.Grade_Name , orge.emp_LNAME || ' ' || orge.emp_FNAME || ' ' || orge.emp_MNAME,";

				query += " eisother.OTHER_CURRENT_BASIC ,eisscale.scale_start_amt || ' ' || eisscale.scale_incr_amt || ' ' || eisscale.scale_end_amt || ' ' || eisscale.scale_higher_incr_amt || ' ' || eisscale.scale_higher_end_amt ,";

				query += " sum(paybill.TOTAL_DED) ,paybill.NET_TOTAL ,";

				if (mappedCompoSize > 0)
					query += " sum(paay.NON_GOV_DEDUC_AMOUNT) , ";
				else
					query += " 0 as NON_GOV_DEDUC_AMOUNT , ";

				query += " sum(paybill.GROSS_AMT) ,eisbankmst.BANK_NAME ,";

				query += " eisbank.BANK_ACCT_NO ,eisbank.BANK_ID,BRANCHpAY.MICR_CODE,BRANCHpAY.BRANCH_NAME ";
				//changes started				
				query+=" from  HR_PAY_PAYBILL paybill  left outer join   HR_EIS_OTHER_DTLS eisother   	on paybill.EMP_ID = eisother.EMP_ID  left outer join    HR_EIS_SGD_MPG eissgdmp  on eisother.EMP_SGD_ID=eissgdmp.SGD_MAP_ID  "+
						" left outer join   HR_EIS_BANK_DTLS eisbank   on eisbank.BANK_EMP_ID=eisother.EMP_ID  left outer join   mst_bank_pay eisbankmst   on eisbank.BANK_ID=eisbankmst.BANK_ID LEFT OUTER JOIN RLT_BANK_BRANCH_PAY BRANCHpAY ON BRANCHpAY.BRANCH_Id=eisbank.BANK_BRANCH_ID  left outer join   HR_EIS_GD_MPG eissgdd   on eissgdmp.SGD_GD_ID=eissgdd.GD_MAP_ID"+
						" left outer join  hr_eis_scale_mst eisscale    on eissgdmp.SGD_SCALE_ID=eisscale.scale_id    left outer join   hr_eis_emp_mst eisempmsttt   	on  eisother.EMP_ID=eisempmsttt.EMP_ID    left outer join    	org_emp_mst orge " + 
						"on     eisempmsttt.emp_mpg_id=orge.EMP_ID   left outer join 	org_grade_mst gradddd   on   eissgdd.SGD_GRADE_ID =gradddd.Grade_Id     left outer join   	org_designation_mst desiii " +
						"	 on  eissgdd.SGD_DESIG_ID =desiii.DSGN_ID ";
				if (mappedCompoSize > 0)
					query += " left outer join  	HR_PAY_payslip_non_govt paay   on	paay.paybill_id=paybill.ID ,";
				else
					query+=",";
				query+="   PAYBILL_HEAD_MPG headd,    mst_dcps_bill_group pbill " ;
				
				//changes starte
				
				query += " where 	headd.approve_flag in (0 , 1)    and headd.PAYBILL_MONTH=" + month + "      and headd.PAYBILL_YEAR=" + year + "     and paybill.NET_TOTAL>0    "+ 
		        		" and pbill.BILL_GROUP_ID= " + BillNo + "      and headd.PAYBILL_ID=paybill.PAYBILL_GRP_ID     and pbill.BILL_GROUP_ID=headd.bill_no " ;  
				if (!Designation.equals("") && !Designation.equals("-1"))
					query += " and desiii.dsgn_id=" + Designation + " ";

				if (!Grade.equals("") && !Grade.equals("-1"))
					query += " and gradddd.grade_id=" + Grade + " ";
				if (!empid.equals("") && !empid.equals("-1"))
					query += "and eisother.EMP_ID =  '" + empid + "' ";
				if (mappedCompoSize > 0)
				{
					query += " and paay.NON_GOV_DEDUC_CODE in ( " + mappedCompId + " ) ";
				}
				query += " group by eisother.EMP_ID ,paybill.EMP_ID ,orge.grade_id ,desiii.dsgn_shrt_name ,gradddd.Grade_Name ,orge.emp_fname ,orge.emp_mname ,orge.emp_lname ,eisscale.scale_start_amt ,eisscale.scale_higher_incr_amt ,eisscale.scale_higher_end_amt ,paybill.NET_TOTAL,";
				query += " eisscale.scale_incr_amt ,eisscale.scale_end_amt ,eisbankmst.BANK_NAME ,eisbank.BANK_ACCT_NO ,eisother.OTHER_CURRENT_BASIC ,paybill.ID ,eisbank.BANK_ID,BRANCHpAY.MICR_CODE,BRANCHpAY.BRANCH_NAME";
				query += " order by orge.emp_LNAME || ' ' || orge.emp_FNAME || ' ' || orge.emp_MNAME";*/
				
				StringBuffer query= new StringBuffer();
				
				query.append("select DISTINCT eis.EMP_ID ,dsgn.DSGN_SHRT_NAME,grade1.GRADE_ID,grade1.GRADE_NAME, dcp.EMP_NAME ,other.OTHER_CURRENT_BASIC , ");
				query.append("scale.scale_start_amt || ' ' || scale.scale_incr_amt || ' ' || scale.scale_end_amt || ' ' || scale.scale_higher_incr_amt || ' ' || scale.scale_higher_end_amt ");
				query.append(", sum(paybill.TOTAL_DED) , paybill.NET_TOTAL , ");
				if (mappedCompoSize > 0)
					query.append("sum(paay.NON_GOV_DEDUC_AMOUNT) , ");
				else
					query.append("0 as NON_GOV_DEDUC_AMOUNT ,");
				query.append(" sum(paybill.GROSS_AMT), bank.BANK_NAME||'' AS bANK_id,dcp.BANK_ACNT_NO,dcp.BANK_NAME, bnkBrnch.MICR_CODE,bnkBrnch.BRANCH_NAME  from hr_Eis_emp_mst eis  ");
				query.append("left outer join org_emp_mst org on eis.EMP_MPG_ID = org.EMP_ID  ");
				query.append("left outer join MST_DCPS_EMP dcp on org.EMP_ID = dcp.ORG_EMP_MST_ID ");
				query.append("left outer join ORG_USERPOST_RLT up on up.USER_ID = org.USER_ID and up.ACTIVATE_FLAG = 1  ");
				query.append("left outer join ORG_POST_MST post on post.POST_ID = up.POST_ID and post.ACTIVATE_FLAG = 1  ");
				query.append("left outer join ORG_POST_DETAILS_RLT rlt on post.POST_ID = rlt.POST_ID and rlt.LOC_ID = post.LOCATION_CODE ");
				query.append("left outer join HR_PAY_POST_PSR_MPG psr on psr.POST_ID = post.POST_ID and psr.LOC_ID = post.LOCATION_CODE ");
				query.append("left outer join HR_PAY_GPF_DETAILS gpf on gpf.USER_ID = up.USER_ID  ");
				query.append("left outer join HR_EIS_GIS_DTLS gis on gis.EMP_ID = eis.EMP_ID ");
				query.append("left outer join HR_EIS_OTHER_DTLS other on other.EMP_ID = eis.EMP_ID ");
				query.append("left outer join HR_EIS_SGD_MPG sgd on other.EMP_SGD_ID = sgd.SGD_MAP_ID and sgd.LOC_ID = post.LOCATION_CODE ");
				query.append("left outer join HR_EIS_SCALE_MST scale on scale.SCALE_ID = sgd.SGD_SCALE_ID  ");
				query.append("left outer join ORG_GRADE_MST grade1 on grade1.GRADE_ID = gis.GIS_GROUP_GRADE_ID ");
				query.append("left outer join org_ddo_mst ddo on ddo.LOCATION_CODE = post.LOCATION_CODE ");
				query.append("left outer join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
				query.append("left outer join PAYBILL_HEAD_MPG headd on headd.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
				query.append("left outer join mst_dcps_bill_group pbill on pbill.BILL_GROUP_ID=headd.bill_no ");
				query.append("left outer join MST_BANK_PAY BANK ON BANK.BANK_CODE=DCP.BANK_NAME ");
				query.append("left outer join RLT_BANK_BRANCH_PAY bnkBrnch on bnkBrnch.BRANCH_CODE=DCP.BRANCH_NAME  ");
				query.append("left outer join  HR_PAY_payslip_non_govt paay   on	paay.paybill_id=paybill.ID ");
				query.append("left outer join org_designation_mst dsgn on dsgn.DSGN_ID =dcp.DESIGNATION ");
				query.append("where  headd.approve_flag in (0 , 1)    and headd.PAYBILL_MONTH=" + month + " and headd.PAYBILL_YEAR=" + year + " and paybill.NET_TOTAL>0 ");
				query.append(" and pbill.BILL_GROUP_ID= '" + BillNo + "' ");
				if (!Designation.equals("") && !Designation.equals("-1"))
					query.append(" and dsgn.dsgn_id=" + Designation + " ");
				if (!Grade.equals("") && !Grade.equals("-1"))
					query.append(" and grade1.grade_id=" + Grade + " ");
				if (!empid.equals("") && !empid.equals("-1"))
					query.append(" and other.EMP_ID =  '" + empid + "' ");
				if (mappedCompoSize > 0)
				{
					query.append(" and paay.NON_GOV_DEDUC_CODE in ( " + mappedCompId + " ) ");
				}
				query.append("group by eis.EMP_ID ,grade1.grade_id,dcp.BANK_NAME ");
				query.append(",grade1.Grade_Name , dcp.EMP_NAME ,dsgn.DSGN_SHRT_NAME,other.OTHER_CURRENT_BASIC,scale.scale_start_amt ,scale.scale_higher_incr_amt ");
				query.append(",scale.scale_higher_end_amt ,scale.scale_incr_amt ,scale.scale_end_amt , paybill.NET_TOTAL ,bank.BANK_NAME ,dcp.BANK_ACNT_NO ");
				query.append(",bnkBrnch.MICR_CODE,bnkBrnch.BRANCH_NAME  order by dcp.EMP_NAME ");
				logger.info("***Query for Bank details payment stmt**" + query.toString());
				Query sqlQuery = hibSession.createSQLQuery(query.toString());
				List RowList = sqlQuery.list();

				double netAmountTotal = 0;
				String AmountTotal = "";
				double payorderTotal = 0;
				double checkAmountTotal = 0;

				int cnt = 1;
				int pageCnt = 1;
				Iterator itr = RowList.iterator();
				if (RowList.size() != 0)
				{

					while (itr.hasNext())
					{
						Object[] rowList = (Object[]) itr.next();
						String designation = (String) (rowList[1] != null ? rowList[1] : "");
						String Name = (String) (rowList[4] != null ? rowList[4] : "");
						logger.info("Name = " + Name);
						double nettotal = Double.parseDouble((rowList[8] != null ? rowList[8] : "0").toString());
						double nongovtotal = Double.parseDouble((rowList[9] != null ? rowList[9] : "0").toString());
						String acct = (rowList[12] != null ? rowList[12] : "").toString();
						String bankId = (rowList[13] != null ? rowList[13] : "").toString();
						String ifscCode = (rowList[14] != null ? rowList[14] : "").toString();
						logger.info("ifscCode = " + ifscCode);
						String bankName=(rowList[11] != null ? rowList[11] : "").toString();
						String branchName=(rowList[15] != null ? rowList[15] : "").toString();
						if (ICICI_BANK_ID.equals(bankId))
						{
							String paddingZeros = "";
							for (int x = 0; x < (12 - acct.length()); x++)
								paddingZeros += "0";
							acct = paddingZeros + acct;
						}

						if (acct != null && !acct.equals("") && !acct.equals("-1"))
						{
							nettotal = nettotal - nongovtotal;
							netAmountTotal += nettotal;
						}
						else
						{
							nettotal = nettotal - nongovtotal;
							payorderTotal += nettotal;
						}

						//AmountTotal+=netAmountTotal+payorderTotal;

						checkAmountTotal = netAmountTotal + payorderTotal;

						ArrayList row = new ArrayList();
						StyledData dataStyle1 = new StyledData();
						StyleVO[] colorStyleVO = new StyleVO[2];
						if (nettotal < 0)
						{
							colorStyleVO[0] = new StyleVO();
							colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
							colorStyleVO[0].setStyleValue("Black");
							colorStyleVO[1] = new StyleVO();
							colorStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
							colorStyleVO[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
						}
						else
						{
							colorStyleVO[0] = new StyleVO();
							colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
							colorStyleVO[0].setStyleValue("Black");
							colorStyleVO[1] = new StyleVO();
							colorStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
							colorStyleVO[1].setStyleValue(IReportConstants.VALUE_FONT_SIZE_SMALL);

						}

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO);
						dataStyle1.setData(cnt);
						row.add(dataStyle1);

						if (acct != null && !acct.equals("") && !acct.equals("-1"))
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO);
							dataStyle1.setData(acct);
							row.add(dataStyle1);
						}
						else
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO);
							dataStyle1.setData("-");
							row.add(dataStyle1);

						}
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO);
						dataStyle1.setData(bankName);
						row.add(dataStyle1);
						
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO);
						dataStyle1.setData(branchName);
						row.add(dataStyle1);
						
						
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO);
						dataStyle1.setData(designation);
						row.add(dataStyle1);

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO);
						dataStyle1.setData(Name);
						row.add(dataStyle1);

						if (acct != null && !acct.equals("") && !acct.equals("-1"))
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO);
							dataStyle1.setData(nettotal);
							row.add(dataStyle1);
						}
						else
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO);
							dataStyle1.setData(nettotal);
							row.add(dataStyle1);
						}
						if (acct != null && !acct.equals("") && !acct.equals("-1"))
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO);
							dataStyle1.setData(ifscCode);
							row.add(dataStyle1);
						}
						else
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO);
							dataStyle1.setData("Pay Order");
							row.add(dataStyle1);
						}

						//Start For ifsc code

						if (!ifscCode.equals("") && ifscCode != null)
						{
							logger.info("ifscCode"+ifscCode);
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO);
							dataStyle1.setData(ifscCode);
							row.add(dataStyle1);//2
						}
						else
						{
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO);
							dataStyle1.setData("2111121212");
							row.add(dataStyle1);//2
						}

						//End for ifsc code

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO1);
						dataStyle1.setData(pageCnt);
						row.add(dataStyle1);//2

						String noOfRec = CheckIfNull(report.getParameterValue("No of Records"));
						if (!noOfRec.equalsIgnoreCase("") && !noOfRec.equalsIgnoreCase("-1"))
						{
							//logger.info("No Of Rec is*****************====>"+noOfRec);
							finalpagesize = Integer.parseInt(noOfRec);
						}
						//logger.info("No Of Rec out side is*****************====>"+noOfRec);	
						//logger.info("cnt%finalpagesize*****************====>"+cnt%finalpagesize);
						//logger.info("((cnt%finalpagesize)==0)*****************====>"+((cnt%finalpagesize)==0));
						DataList.add(row);
						if ((cnt % finalpagesize) == 0)
						{
							//logger.info("Inside page break::::::::::");
							pageCnt++;
							row = new ArrayList();
							row.add(new PageBreak());
							row.add("Data");
							DataList.add(row);
							/*dataStyle1 = new StyledData();
							dataStyle1.setStyles(boldStyleVO);
							dataStyle1.setData("");                  
							row.add(dataStyle1);*/
						}
						cnt++;

					}

					StyledData dataStyle1 = new StyledData();

					ArrayList row = new ArrayList();
					row.add("");
					row.add("");
					row.add("");
					

					StyleVO[] NewVo = new StyleVO[4];
					NewVo[0] = new StyleVO();
					NewVo[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					NewVo[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
					NewVo[1] = new StyleVO();
					NewVo[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					NewVo[1].setStyleValue("Left");
					NewVo[2] = new StyleVO();
					NewVo[2].setStyleId(IReportConstants.BORDER);
					NewVo[2].setStyleValue("NO");
					NewVo[3] = new StyleVO();
					NewVo[3].setStyleId(IReportConstants.STYLE_FONT_FAMILY);
					NewVo[3].setStyleValue("Rupee Foradian");

					ArrayList innerrow = new ArrayList();
					ArrayList innerdata = new ArrayList();

					innerrow = new ArrayList();
					innerrow.add("Total Amount by Pay Order (`):");
					innerrow.add(Math.round(payorderTotal));
					innerdata.add(innerrow);

					innerrow = new ArrayList();
					innerrow.add("Total Amount To Be Credited In Employee Account (`):");
					innerrow.add(Math.round(netAmountTotal));
					innerdata.add(innerrow);

					innerrow = new ArrayList();
					innerrow.add("Cheque Amount (`):");
					innerrow.add(Math.round(checkAmountTotal));
					innerdata.add(innerrow);
					TabularData data = new TabularData(innerdata);
					
					data.setStyles(NewVo);

					//dataStyle1 = new StyledData();
					//dataStyle1.setStyles(boldStyleVO);
					//	dataStyle1.setData("Total Amount by Pay Order:"+Math.round(payorderTotal)+System.getProperty("line.separator")+"Total Amount To Be Credited In Employee Account :"+Math.round(netAmountTotal)+System.getProperty("line.separator")+"Cheque Amount:"+Math.round(checkAmountTotal));
					row.add(data);
					//String AmountTotalInWords="Rupees  "+ConvertNumbersToWord.convert(Math.round(AmountTotal))+" only.";
					dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO1);
					dataStyle1.setData(AmountTotal/*+System.getProperty("line.separator")+AmountTotalInWords*/);
					row.add(dataStyle1);
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					DataList.add(row);

				}

				return DataList;

			}
			//			-------------------Kishan Shaha-----------------------------------------------------------------------------------------------------------------------------------------------------
			//			if (report.getReportCode().equals("5000009") || report.getReportCode().equals("2500009") || report.getReportCode().equals("15") || report.getReportCode().equals("2500015"))
			//			{
			//				finalpagesize = 8;
			//
			//				StringBuffer lsb = new StringBuffer();
			//
			//				String noOfRec = CheckIfNull(report.getParameterValue("No of Records"));
			//				if (!noOfRec.equalsIgnoreCase("") && !noOfRec.equalsIgnoreCase("-1"))
			//					finalpagesize = Integer.parseInt(noOfRec);
			//				String empid = CheckIfNull(report.getParameterValue("employeeName"));
			//				int month = 0;
			//
			//				int year = 0;
			//				String Department = "";
			//				String Grade = "";
			//				String Designation = "";
			//				Department = CheckIfNull(report.getParameterValue("Department"));
			//				Grade = CheckIfNull(report.getParameterValue("Grade"));
			//				Designation = CheckIfNull(report.getParameterValue("Designation"));
			//
			//				String BillNoinner = "";
			//				BillNoinner = CheckIfNull(report.getParameterValue("Bill No"));
			//				StringTokenizer st1 = new StringTokenizer(BillNoinner, "~");
			//				int l = 0;
			//				String subheadCode = "";
			//				String classIds = "";
			//				String desgnIds = "";
			//				while (st1.hasMoreTokens())
			//				{
			//					if (l == 0)
			//						subheadCode = st1.nextToken();
			//					else if (l == 1)
			//						classIds = st1.nextToken();
			//					else if (l == 2)
			//						desgnIds = st1.nextToken();
			//					else if (l == 3)
			//						portType = st1.nextToken();
			//					else if (l == 4)
			//						BillNo = st1.nextToken();
			//					l++;
			//				}
			//
			//				//Added by abhilash	
			//
			//				logger.info("bill no is ***********" + BillNo);
			//				logger.info("bill no is ***********" + BillNoinner);
			//				logger.info("bill no is ***********" + report.getParameterValue("Bill No"));
			//
			//				if (BillNoinner.trim().equals("") || BillNo.equalsIgnoreCase(""))
			//				{
			//					logger.info("inside if condition bill no is ***********" + report.getParameterValue("Bill No"));
			//					BillNo = report.getParameterValue("Bill No") != null ? report.getParameterValue("Bill No").toString().trim() : "";
			//				}
			//				//Ended
			//
			//				if (!CheckIfNull(report.getParameterValue("Month")).equals(""))
			//				{
			//					month = Integer.parseInt(CheckIfNull(report.getParameterValue("Month")));
			//				}
			//				if (!CheckIfNull(report.getParameterValue("Year")).equals(""))
			//				{
			//					year = Integer.parseInt(CheckIfNull(report.getParameterValue("Year")));
			//				}
			//				String DeptName = locationNameincaps;
			//
			//				PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			//				DeptName = payBillDAOImpl.getOffice(locationId);
			//
			//				String bankName = "";
			//				if (!CheckIfNull(report.getParameterValue("bankName")).equals(""))
			//				{
			//					bankName = (CheckIfNull(report.getParameterValue("bankName")));
			//					if (bankName.equals("-1")) //checkifnull doesnt takes care of this
			//						bankName = "";
			//				}
			//
			//				StyleVO[] centerboldStyleVO1 = new StyleVO[3];
			//				centerboldStyleVO1[0] = new StyleVO();
			//				centerboldStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			//				centerboldStyleVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			//				centerboldStyleVO1[1] = new StyleVO();
			//				centerboldStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			//				centerboldStyleVO1[1].setStyleValue("Center");
			//				centerboldStyleVO1[2] = new StyleVO();
			//				centerboldStyleVO1[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			//				centerboldStyleVO1[2].setStyleValue("14");
			//
			//				Calendar cal = Calendar.getInstance();
			//				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
			//
			//				cal.set(Calendar.YEAR, year);
			//				cal.set(Calendar.MONTH, month - 1);
			//				cal.set(Calendar.DAY_OF_MONTH, 1);
			//
			//				java.util.Date date = cal.getTime();
			//				String selDate = sdfObj.format(date);
			//
			//				cal.set(Calendar.YEAR, year);
			//				cal.set(Calendar.MONTH, month - 1);
			//				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
			//				java.util.Date endMonthDate = cal.getTime();
			//
			//				String endDate = sdfObj.format(endMonthDate);
			//				sdfObj = new SimpleDateFormat("MMM");
			//				String Month = sdfObj.format(date);
			//
			//				ArrayList styleList = new ArrayList();
			//				ArrayList stData = new ArrayList();
			//
			//				String deptHeader = "";
			//
			//				String[] monthName = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
			//				Calendar cale = Calendar.getInstance();
			//				String MONTH = monthName[cale.get(Calendar.MONTH)];
			//				java.util.Calendar calendar = java.util.Calendar.getInstance();
			//				int curYear = calendar.get(calendar.YEAR);
			//
			//				Integer in = month;
			//
			//				String monthname = monthName[in - 1];
			//
			//				long loggedInpostId = Long.parseLong(baseLoginMap.get("loggedInPost").toString());
			//				//List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);
			//
			//				/*String ddocode ="";
			//				if(ddoList.size()>0)
			//					ddocode = ddoList.get(0).getDdoCode();*/
			//				String ddocode = "";
			//				String TanNo = "";
			//				long locactionId = Long.parseLong(baseLoginMap.get("locationId").toString());
			//				PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			//				List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locactionId);
			//				if (ddoCodeList != null)
			//					logger.info("After query execution for DDO Code " + ddoCodeList.size());
			//
			//				OrgDdoMst ddoMst = null;
			//				if (ddoCodeList != null && ddoCodeList.size() > 0)
			//				{
			//					ddoMst = ddoCodeList.get(0);
			//				}
			//
			//				if (ddoMst != null)
			//				{
			//					ddocode = ddoMst.getDdoCode();
			//					TanNo = ddoMst.getTanNo();
			//				}
			//				logger.info("DDO Code is " + ddocode);
			//
			//				String TresuryName = "";
			//				String TresuryCode = "";
			//
			//				List TreasuryList = payBillDAOImpl.getTreasuryCodeAndTreasuryName(ddocode);
			//
			//				if (TreasuryList != null && TreasuryList.size() != 0)
			//				{
			//					for (Iterator itr = TreasuryList.iterator(); itr.hasNext();)
			//					{
			//
			//						Object[] rowList = (Object[]) itr.next();
			//
			//						if (rowList[0] != null)
			//						{
			//							TresuryName = rowList[0].toString().trim();
			//						}
			//						if (rowList[1] != null)
			//						{
			//							TresuryCode = rowList[1].toString().trim();
			//						}
			//
			//					}
			//				}
			//
			//				long langId = StringUtility.convertToLong(baseLoginMap.get("langId").toString());
			//
			//				String gradeName = "";
			//
			//				if (!Grade.equals("") && !Grade.equals("-1"))
			//					gradeName = payBillDAOImpl.getGradenameFromGradeId(Long.parseLong(Grade), langId);
			//
			//				String DsgnName = "";
			//				if (!Designation.equals("") && !Designation.equals("-1"))
			//					DsgnName = payBillDAOImpl.getDsgnNameFromDsgnId(Long.parseLong(Designation), langId);
			//
			//				System.out.println("locactionId is***********" + locactionId);
			//				GenericDaoHibernateImpl gen = new GenericDaoHibernateImpl(CmnLocationMst.class);
			//				gen.setSessionFactory(serv.getSessionFactory());
			//				CmnLocationMst cmnLocMst = (CmnLocationMst) gen.read(locactionId);
			//				long parentLocId = cmnLocMst.getParentLocId();
			//
			//				logger.info("GRADE ID***********" + gradeName);
			//				logger.info("dsgnName***********" + DsgnName);
			//				logger.info("parentLocId***********" + parentLocId);
			//
			//				String Treasury = TresuryCode;
			//				String space = "&";
			//				String Section = "All Employees";
			//				String Section1 = DsgnName;
			//				String Section2 = gradeName;
			//				String Section3 = DsgnName + space + gradeName;
			//
			//				String dsgnSection = "Designation -";
			//				String group = "Group -";
			//				String dsgnId = "";
			//				String gradeId = "";
			//
			//				ArrayList rr = new ArrayList();
			//				rr.add(new StyledData("For the Month of " + monthname + " " + year, leftHeader));
			//
			//				if (!BillNo.equals("") && !BillNo.equals("-1") && !Grade.equals("") && !Grade.equals("-1") && !Designation.equals("") && !Designation.equals("-1"))
			//				{
			//					rr.add(new StyledData("Section :Designation&Group -" + Section3, rightHead));
			//				}
			//				else if (!BillNo.equals("") && !BillNo.equals("-1") && !Designation.equals("") && !Designation.equals("-1"))
			//				{
			//					rr.add(new StyledData("Section :Designation -" + Section1, rightHead));
			//				}
			//				else if (!BillNo.equals("") && !BillNo.equals("-1") && !Grade.equals("") && !Grade.equals("-1"))
			//				{
			//					rr.add(new StyledData("Section :Group -" + Section2, rightHead));
			//				}
			//				else
			//				{
			//					rr.add(new StyledData("Section :" + Section, rightHead));
			//				}
			//
			//				StyledData styledHeader = new StyledData(deptHeader, headerStyleVo);
			//				styledHeader.setColspan(2);
			//				stData.add(styledHeader);
			//				stData.add("");
			//				styleList.add(stData);
			//				styleList.add(rr);
			//				ArrayList r2 = new ArrayList();
			//				r2.add(new StyledData("Name of the Office : " + DeptName, leftHeader));
			//				r2.add(new StyledData("Treasury : " + Treasury + "  " + "DDO:" + ddocode, rightHead));
			//				styleList.add(r2);
			//
			//				TabularData ttData = new TabularData(styleList);
			//				ttData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			//				ttData.addStyle(IReportConstants.BORDER, "No");
			//				ttData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
			//				report.setAdditionalHeader(ttData);
			//
			//				cal = Calendar.getInstance();
			//				sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
			//				cal.set(Calendar.YEAR, year);
			//				cal.set(Calendar.MONTH, month - 1);
			//				cal.set(Calendar.DAY_OF_MONTH, 1);
			//				java.util.Date finYrDate = cal.getTime();
			//				cal.set(Calendar.YEAR, year);
			//				cal.set(Calendar.MONTH, month - 1);
			//				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
			//				endMonthDate = cal.getTime();
			//				endDate = sdfObj.format(endMonthDate);
			//
			//				/*lsb.append("select ");
			//				lsb.append(" bill.hrEisEmpMst.orgEmpMst.empPrefix||' '||bill.hrEisEmpMst.orgEmpMst.empLname||' '||bill.hrEisEmpMst.orgEmpMst.empFname||' '||bill.hrEisEmpMst.orgEmpMst.empMname,");
			//				lsb.append(" bill.netTotal, ");
			//
			//				lsb.append(" max(non.lic) ");//lic
			//
			//				lsb.append(", max(non.coopbank) ");//coopbank
			//
			//				lsb.append(", max(non.mrtCoOpSoc) ");//mrtCoOpSoc
			//
			//				lsb.append(", max(non.recurrdeposit) ");//recurrdeposit
			//
			//				lsb.append(", max(non.misc) ");//misc
			//
			//				lsb.append(", max(non.otherRecovery) ");//otherRecovery
			//
			//				lsb.append(", max(non.otherDeduction) ");//otherDeduction
			//
			//				lsb.append(", count(bill.hrEisEmpMst.orgEmpMst.empId) ");//Count Employees
			//				
			//				lsb.append(", bill.hrEisEmpMst.sevarthEmpCode "); //For sevarthEmpCode
			//				
			//				lsb.append(", dsgn.dsgnName"); //For Designation Name
			//				
			//				lsb.append(", max(non.mantralaya_bank)"); //For Mantralaya Bank
			//				
			//				lsb.append(", max(non.credit_soc)"); //For CREDIT SOC.
			//				
			//				lsb.append(", max(non.con_store)"); //For CON.STORE
			//				
			//				lsb.append(", max(non.mis)"); //For MIS
			//
			//				lsb.append(" from ");
			//				lsb.append(" HrPayPaybill bill,HrPayPayslipNonGovt non ");
			//
			//				lsb.append("  ,OrgUserpostRlt           USRPST, ");
			//
			//				lsb.append(" HrPayOrderHeadMpg ORDHD, ");
			//				lsb.append(" HrPayOrderHeadPostMpg ORDPST, ");
			//				lsb.append("  OrgPostDetailsRlt pst, ");
			//				lsb.append(" PaybillHeadMpg bhm,MstDcpsBillGroup hpbsm,OrgDesignationMst dsgn");
			//				
			//				if(!Grade.equals("") && !Grade.equals("-1"))
			//					lsb.append(" ,OrgGradeMst gr  ");
			//				
			//				
			//				lsb.append("  where  ");
			//				lsb.append("  non.paybillID=bill.id and " );
			//				
			//
			//				lsb.append("  USRPST.orgPostMstByPostId.postId = ORDPST.postId ");
			//
			//				lsb.append("  and USRPST.orgUserMst.userId = bill.hrEisEmpMst.orgEmpMst.orgUserMst.userId ");
			//				lsb.append("  and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId and pst.orgDesignationMst.dsgnId=dsgn.dsgnId");
			//				
			//				//lsb.append(" and gr.gradeId=bill.hrEisEmpMst.orgEmpMst.empId ");
			//
			//
			//				if(!Designation.equals("") && !Designation.equals("-1"))
			//					lsb.append(" and dsgn.dsgnId="+Designation+" ");
			//
			//				if(!Grade.equals("") && !Grade.equals("-1"))
			//					lsb.append(" and gr.gradeId=bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  and gr.gradeId="+Grade+" ");
			//
			//
			//				lsb.append(" and (USRPST.endDate is null or ");
			//
			//				lsb.append("   (USRPST.endDate>='"+selDate+"' and USRPST.startDate<='"+endDate+"'    ))");
			//				lsb.append("   and bhm.approveFlag in (0,1) ");
			//
			//				lsb.append(" and ");	            
			//				if(!empid.equals("") && !empid.equals("-1"))
			//					lsb.append(" bill.hrEisEmpMst.empId = '"+empid+"' and ");
			//
			//				lsb.append(" bhm.month ='"+month+"' and ");
			//
			//				lsb.append(" bhm.year = '"+year+"'");
			//
			//
			//				if(!Department.equals("")&&!Department.equals("-1"))            	
			//					lsb.append(" and pst.cmnLocationMst.locationCode=" + Department);
			//				if(isBillDefined&&!BillNo.equals(""))
			//				{
			//
			//					
			//					lsb.append(" and pst.orgPostMst.postId = bill.orgPostMst.postId  "); 
			//					
			//					lsb.append(" and hpbsm.dcpsDdoBillGroupId="+BillNo+"  ");
			//				}
			//				else
			//				{
			//					if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
			//					{
			//						lsb.append("   and ORDHD.subheadId ="+subheadCode+" ");
			//					}
			//					if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
			//					{
			//						lsb.append("  and bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")");
			//					}
			//					if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
			//					{
			//						lsb.append("  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ");
			//					}
			//				}
			//				lsb.append("  and bhm.hrPayPaybill = bill.paybillGrpId  and hpbsm.dcpsDdoBillGroupId = bhm.billNo.dcpsDdoBillGroupId  ");
			//				lsb.append(" group by  ");
			//				lsb.append(" bill.hrEisEmpMst.empId,bill.hrEisEmpMst.orgEmpMst.empPrefix, ");
			//				lsb.append(" bill.hrEisEmpMst.orgEmpMst.empLname, ");
			//				lsb.append(" bill.hrEisEmpMst.orgEmpMst.empFname, ");
			//				lsb.append(" bill.hrEisEmpMst.orgEmpMst.empMname, ");
			//				lsb.append(" bill.hrEisEmpMst.sevarthEmpCode,");
			//				lsb.append(" dsgn.dsgnName,");
			//				lsb.append(" bill.netTotal");
			//				
			//				String strQuery = lsb.toString(); 
			//
			//				logger.info("the query is "+strQuery);
			//				Query query = session.createQuery(strQuery);
			//				logger.info("===> Query of Non Govt Deduct ::"+query);*/
			//
			//				lsb.append("select distinct ");
			//				lsb.append(" emp.emp_lname||' '||emp.emp_fname||' '||emp.emp_mname as col_0_0_,");
			//				lsb.append(" bill.NET_TOTAL, ");
			//
			//				lsb.append("  non.LIC ");//lic
			//
			//				lsb.append(",  non.CO_OP_Bank ");//coopbank
			//
			//				lsb.append(",  non.MRT_Co_OP_Soc ");//mrtCoOpSoc
			//
			//				lsb.append(",  non.Recurring_Deposit ");//recurrdeposit
			//
			//				lsb.append(",  non.MISC ");//misc
			//
			//				lsb.append(",  non.Other_Recovery ");//otherRecovery
			//
			//				lsb.append(",  non.Other_Deduction ");//otherDeduction
			//
			//				lsb.append(", eis.emp_mpg_id ");//Count Employees
			//
			//				lsb.append(", eis.SEVARTH_EMP_CD "); //For sevarthEmpCode
			//
			//				lsb.append(", dsgn.dsgn_name"); //For Designation Name
			//
			//				lsb.append(",  non.MANTRALAYA_BANK"); //For Mantralaya Bank
			//
			//				lsb.append(",  non.CREDIT_SOC"); //For CREDIT SOC.
			//
			//				lsb.append(",  non.CON_STORE"); //For CON.STORE
			//
			//				lsb.append(",  non.MIS"); //For MIS
			//
			//				lsb.append(", common.POST_TYPE"); //For POST_TYPE
			//
			//				lsb.append(", rltdcps.DCPS_CLASS_GROUP"); //For DCPS_CLASS_GROUP
			//
			//				lsb.append(", common.dsgn_code"); //For dsgn_code
			//
			//				lsb.append(", emp.emp_lname"); //For emp_lname
			//
			//				lsb.append(" from ");
			//
			//				lsb.append(" HR_PAY_PAYBILL bill,HR_PAY_PAYSLIP_NON_GOVT non, ");
			//				lsb.append(" hr_eis_emp_mst eis,org_emp_mst emp,ORG_GRADE_MST grade, ");
			//				lsb.append(" org_post_details_rlt postrlt,org_designation_mst dsgn, ");
			//				lsb.append(" PAYBILL_HEAD_MPG head, MST_DCPS_BILL_GROUP dcps, ");
			//				lsb.append(" RLT_DCPS_BILLGROUP_CLASSGROUP rltdcps,COMMMON_POST_DESIG common, ");
			//				lsb.append(" HR_EIS_GD_MPG gd,HR_EIS_SGD_MPG sgd ");
			//
			//				lsb.append(" where ");
			//
			//				lsb.append(" eis.emp_mpg_id=emp.emp_id and bill.EMP_ID=eis.emp_id ");
			//				lsb.append(" and head.PAYBILL_ID= bill.PAYBILL_GRP_ID and non.paybill_id=bill.ID ");
			//				lsb.append(" and common.POST_ID=bill.POST_ID and postrlt.post_id=bill.post_id ");
			//				lsb.append(" and common.DSGN_ID=postrlt.DSGN_ID and postrlt.dsgn_id=dsgn.dsgn_id ");
			//				lsb.append(" and grade.GRADE_NAME=rltdcps.DCPS_CLASS_GROUP ");
			//				lsb.append(" and gd.SGD_GRADE_ID = grade.GRADE_ID and gd.SGD_DESIG_ID = common.DSGN_ID ");
			//
			//				if (!Designation.equals("") && !Designation.equals("-1"))
			//					lsb.append(" and dsgn.dsgn_Id=" + Designation + " ");
			//
			//				if (!Grade.equals("") && !Grade.equals("-1"))
			//					lsb.append(" and grade.grade_Id=" + Grade + " ");
			//
			//				lsb.append(" and gd.LOC_ID =" + Department + " and sgd.SGD_GD_ID = gd.GD_MAP_ID ");
			//				lsb.append(" and postrlt.loc_id= " + Department + " and head.approve_flag in (0,1) ");
			//				lsb.append(" and head.PAYBILL_MONTH='" + month + "' and head.PAYBILL_YEAR='" + year + "' ");
			//				lsb.append(" and dcps.BILL_GROUP_ID=  " + BillNo + " and rltdcps.DCPS_BILLGROUP_ID=  " + BillNo + " ");
			//				lsb.append(" and common.FIELD_DEPTID=" + parentLocId + " and dcps.BILL_GROUP_ID=head.bill_no  ");
			//
			//				lsb.append(" order by  ");
			//
			//				lsb.append(" common.POST_TYPE,  ");
			//				lsb.append(" rltdcps.DCPS_CLASS_GROUP,  ");
			//				lsb.append(" common.dsgn_code,  ");
			//				lsb.append(" emp.emp_lname  ");
			//
			//				String strQuery = lsb.toString();
			//
			//				logger.info("the query is " + strQuery);
			//				Query query = session.createSQLQuery(strQuery);
			//				logger.info("===> Query of Non Govt Deduct ::" + query);
			//				System.out.println("Aquittance Query of Non Govt Deduct ::" + query);
			//				System.out.println("Aquittance tostring Query of Non Govt Deduct ::" + query.toString());
			//				if (query.list().size() != 0)
			//				{
			//
			//					int cnt = 1;
			//					long empId = 0;
			//
			//					ArrayList rowList = new ArrayList();
			//
			//					long netAmttotal = 0;
			//
			//					int pageCnt = 1;
			//
			//					long netPaytotal = 0;
			//					long totalDeductotal = 0;
			//
			//					long licNewtotal = 0;
			//					long coopbanktotal = 0;
			//
			//					long mantralayatotal = 0;
			//					long creditSoctotal = 0;
			//					long conStoretotal = 0;
			//					long mistotal = 0;
			//
			//					long mrtCoOpSoctotal = 0;
			//					long recurrdeposittotal = 0;
			//					long misctotal = 0;
			//					long otherRecoverytotal = 0;
			//					long otherDeductiontotal = 0;
			//
			//					long cntNetAmountTotal = 0;
			//					long cntNetPayTotal = 0;
			//					long cntMrtCoOpSoctotal = 0;
			//					long cntCoOpBanktotal = 0;
			//
			//					long cntMantralayatotal = 0;
			//					long cntCreditSoctotal = 0;
			//					long cntConStoretotal = 0;
			//					long cntMistotal = 0;
			//
			//					long cntreCurrdeposittotal = 0;
			//					long cntLicNewtotal = 0;
			//					long cntMisctotal = 0;
			//					long cntOtherRecoverytotal = 0;
			//					long cntOtherDeductiontotal = 0;
			//					long cntTotalDeductotal = 0;
			//
			//					long GrandNetAmountTotal = 0;
			//					long GrandNepPayTotal = 0;
			//					long cntGrandNetAmountTotal = 0;
			//					long cntGrandNetPayTotal = 0;
			//					long GrandcntNetAmountTotal = 0;
			//					long GrandcntNetPayAmountTotal = 0;
			//
			//					long GrandmrtCoOpSoctotal = 0;
			//
			//					long GrandCoOpBanktotal = 0;
			//
			//					long GrandMantralayatotal = 0;
			//					long GrandCreditSoctotal = 0;
			//					long GrandConStoretotal = 0;
			//					long GrandMistotal = 0;
			//
			//					long GrandcntreCurrdeposittotal = 0;
			//					long GrandcntLicNewtotal = 0;
			//					long GrandcntMisctotal = 0;
			//					long GrandcntOtherRecoverytotal = 0;
			//					long GrandcntOtherDeductiontotal = 0;
			//					long GrandcntTotalDeductotal = 0;
			//
			//					//long abhi=0;
			//
			//					List RowList = query.list();
			//					Iterator it = RowList.iterator();
			//
			//					while (it.hasNext())
			//					{
			//						Object[] row = (Object[]) it.next();
			//						Double zero = new Double(0);
			//						String empName = (String) (row[0] != null ? row[0] : zero);//orgEmp.empPrefix||' '||orgEmp.empFname||' '||orgEmp.empMname||' '||orgEmp.empLname
			//						//	Long netAmt 			= Math.round((Double)(row[1]!=null?row[1]:0));//netAmt
			//						double netAmt = Double.parseDouble((row[1] != null ? row[1] : "0").toString());
			//						Long lic = new Long(0);
			//						Long coopbank = new Long(0);
			//						Long mrtCoOpSoc = new Long(0);
			//						Long recurrdeposit = new Long(0);
			//						Long misc = new Long(0);
			//
			//						Long mantralayaBank = new Long(0);
			//						Long creditSoc = new Long(0);
			//						Long conStore = new Long(0);
			//						Long mis = new Long(0);
			//
			//						long otherRecovery = 0;
			//						long otherDeduction = 0;
			//						long countEmployeesRec;
			//						countEmployeesRec = Long.parseLong(row[9] != null ? row[9].toString() : "0");
			//
			//						String employeeId = (row[10] != null ? row[10].toString() : "").toString();
			//						String dsgnName = (row[11] != null ? row[11].toString() : "").toString();
			//
			//						employeeId = "(" + employeeId + ")";
			//						dsgnName = "(" + dsgnName + ")";
			//
			//						StyleVO[] NewVo = new StyleVO[4];
			//						NewVo[0] = new StyleVO();
			//						NewVo[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			//						NewVo[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			//						NewVo[1] = new StyleVO();
			//						NewVo[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			//						NewVo[1].setStyleValue("Left");
			//						NewVo[2] = new StyleVO();
			//						NewVo[2].setStyleId(IReportConstants.BORDER);
			//						NewVo[2].setStyleValue("NO");
			//
			//						String fullName = empName + employeeId + dsgnName;
			//						ArrayList innerData = new ArrayList();
			//						ArrayList innerRow = new ArrayList();
			//
			//						innerRow = new ArrayList();
			//						innerRow.add(empName);
			//						innerData.add(innerRow);
			//
			//						innerRow = new ArrayList();
			//						innerRow.add(employeeId);
			//						innerData.add(innerRow);
			//
			//						innerRow = new ArrayList();
			//						innerRow.add(dsgnName);
			//						innerData.add(innerRow);
			//
			//						TabularData td = new TabularData(innerData);
			//						td.setStyles(NewVo);
			//
			//						StyledData dataStyle12 = new StyledData();
			//						dataStyle12 = new StyledData();
			//						dataStyle12.setStyles(boldStyleVO);
			//						dataStyle12.setData(td);
			//
			//						if (row[2] != null)
			//						{
			//							lic = Long.parseLong(row[2].toString());//LIC
			//						}
			//						if (row[3] != null)
			//						{
			//							coopbank = Long.parseLong(row[3].toString());//Co Op Bank
			//						}
			//						if (row[4] != null)
			//						{
			//							mrtCoOpSoc = Long.parseLong(row[4].toString());//MRT Co Op Bank
			//						}
			//						if (row[5] != null)
			//						{
			//							recurrdeposit = Long.parseLong(row[5].toString());//Recurring Deposit
			//						}
			//						if (row[6] != null)
			//						{
			//							misc = Long.parseLong(row[6].toString());//Mics
			//						}
			//						if (row[7] != null)
			//						{
			//							otherRecovery = Long.parseLong(row[7].toString());//otherRecovery
			//						}
			//						if (row[8] != null)
			//						{
			//							otherDeduction = Long.parseLong(row[8].toString());//otherRecovery
			//						}
			//						if (row[12] != null)
			//						{
			//							mantralayaBank = Long.parseLong(row[12].toString());//mantralayaBank
			//						}
			//						if (row[13] != null)
			//						{
			//							creditSoc = Long.parseLong(row[13].toString());//creditSoc
			//						}
			//						if (row[14] != null)
			//						{
			//							conStore = Long.parseLong(row[14].toString());//conStore
			//						}
			//						if (row[15] != null)
			//						{
			//							mis = Long.parseLong(row[15].toString());//mis
			//						}
			//
			//						long totalDeduc = lic + mrtCoOpSoc + recurrdeposit + misc + otherRecovery + otherDeduction + coopbank + mantralayaBank + creditSoc + conStore + mis;
			//						double netPay = netAmt - totalDeduc;
			//						StyleVO[] colorStyleVO1 = new StyleVO[2];
			//						if (netPay < 0)
			//						{
			//							colorStyleVO1[0] = new StyleVO();
			//							colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			//							colorStyleVO1[0].setStyleValue("Black");
			//							colorStyleVO1[1] = new StyleVO();
			//							colorStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			//							colorStyleVO1[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			//						}
			//						else if (netPay == 0)
			//						{
			//							colorStyleVO1[0] = new StyleVO();
			//							colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			//							colorStyleVO1[0].setStyleValue("Black");
			//							colorStyleVO1[1] = new StyleVO();
			//							colorStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			//							colorStyleVO1[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			//						}
			//
			//						else if (countEmployeesRec > 1)
			//						{
			//							colorStyleVO1[0] = new StyleVO();
			//							colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			//							colorStyleVO1[0].setStyleValue("Black");
			//							colorStyleVO1[1] = new StyleVO();
			//							colorStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			//							colorStyleVO1[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			//
			//						}
			//						else
			//						{
			//							colorStyleVO1[0] = new StyleVO();
			//							colorStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			//							colorStyleVO1[0].setStyleValue("Black");
			//							colorStyleVO1[1] = new StyleVO();
			//							colorStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			//							colorStyleVO1[1].setStyleValue(IReportConstants.VALUE_FONT_SIZE_SMALL);
			//
			//						}
			//						
			//
			//						rowList = new ArrayList();
			//						StyledData dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(new Integer(cnt));
			//						rowList.add(dataStyle1);//1
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(fullName);
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(Math.round(netAmt));
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(" ");
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(mrtCoOpSoc);
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(recurrdeposit);
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(lic);
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(misc);
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(otherRecovery);
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(otherDeduction);
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(coopbank);
			//						rowList.add(dataStyle1);//2
			//
			//						//Started
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(mantralayaBank);
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(creditSoc);
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(conStore);
			//						rowList.add(dataStyle1);//2
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(mis);
			//						rowList.add(dataStyle1);//2
			//
			//						//Ended
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(totalDeduc);
			//						rowList.add(dataStyle1);//2
			//
			//						/*dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(coopbank);                   
			//						rowList.add(dataStyle1);//2
			//						*/
			//
			//						dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(Math.round(netPay));
			//						rowList.add(dataStyle1);//2
			//
			//						/*dataStyle1 = new StyledData();
			//						dataStyle1.setStyles(colorStyleVO1);
			//						dataStyle1.setData(pageCnt);                   
			//						rowList.add(dataStyle1);//2
			//						*/
			//						netAmttotal += netAmt;
			//						licNewtotal += lic;
			//						coopbanktotal += coopbank;
			//
			//						mantralayatotal += mantralayaBank;
			//						creditSoctotal += creditSoc;
			//						conStoretotal += conStore;
			//						mistotal += mis;
			//
			//						mrtCoOpSoctotal += mrtCoOpSoc;
			//						recurrdeposittotal += recurrdeposit;
			//						misctotal += misc;
			//						otherRecoverytotal += otherRecovery;
			//						otherDeductiontotal += otherDeduction;
			//
			//						totalDeductotal += totalDeduc;
			//						netPaytotal += netPay;
			//
			//						DataList.add(rowList);
			//
			//						if ((cnt % finalpagesize) == 0)
			//						{
			//
			//							cntNetAmountTotal += netAmttotal;
			//							cntNetPayTotal += netPaytotal;
			//
			//							cntCoOpBanktotal += coopbanktotal;
			//
			//							cntMantralayatotal += mantralayatotal;
			//							cntCreditSoctotal += creditSoctotal;
			//							cntConStoretotal += conStoretotal;
			//							cntMistotal += mistotal;
			//
			//							cntMrtCoOpSoctotal += mrtCoOpSoctotal;
			//							cntreCurrdeposittotal += recurrdeposittotal;
			//							cntLicNewtotal += licNewtotal;
			//							cntMisctotal += misctotal;
			//							cntOtherRecoverytotal += otherRecoverytotal;
			//							cntOtherDeductiontotal += otherDeductiontotal;
			//							cntTotalDeductotal += totalDeductotal;
			//
			//							rowList = new ArrayList();
			//							rowList.add("");
			//							StyledData dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							dataStyle3.setData("Rupees:");
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							dataStyle3.setData(cntNetAmountTotal);
			//							rowList.add(dataStyle3);
			//
			//							rowList.add("");
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntMrtCoOpSoctotal > 0)
			//							{
			//								dataStyle3.setData(cntMrtCoOpSoctotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntreCurrdeposittotal > 0)
			//							{
			//								dataStyle3.setData(cntreCurrdeposittotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntLicNewtotal > 0)
			//							{
			//								dataStyle3.setData(cntLicNewtotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntMisctotal > 0)
			//							{
			//								dataStyle3.setData(cntMisctotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntOtherRecoverytotal > 0)
			//							{
			//								dataStyle3.setData(cntOtherRecoverytotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntOtherDeductiontotal > 0)
			//							{
			//								dataStyle3.setData(cntOtherDeductiontotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntCoOpBanktotal > 0)
			//							{
			//								dataStyle3.setData(cntCoOpBanktotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							//Started for new one
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntMantralayatotal > 0)
			//							{
			//								dataStyle3.setData(cntMantralayatotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntCreditSoctotal > 0)
			//							{
			//								dataStyle3.setData(cntCreditSoctotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntConStoretotal > 0)
			//							{
			//								dataStyle3.setData(cntConStoretotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntMistotal > 0)
			//							{
			//								dataStyle3.setData(cntMistotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							//End for new one
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							if (cntTotalDeductotal > 0)
			//							{
			//								dataStyle3.setData(cntTotalDeductotal);
			//							}
			//							else
			//							{
			//								dataStyle3.setData("");
			//							}
			//							rowList.add(dataStyle3);
			//
			//							dataStyle3 = new StyledData();
			//							dataStyle3.setStyles(boldStyleVO);
			//							dataStyle3.setData(Math.round(cntNetPayTotal));
			//							rowList.add(dataStyle3);
			//
			//							DataList.add(rowList);
			//
			//							pageCnt++;
			//							rowList = new ArrayList();
			//							rowList.add(new PageBreak());
			//							rowList.add("Data");
			//							DataList.add(rowList);
			//
			//							//GrandNetAmountTotal+=cntNetAmountTotal;
			//							//GrandNepPayTotal+=cntNetPayTotal;
			//
			//							netAmttotal = 0;
			//							netPaytotal = 0;
			//
			//							mrtCoOpSoctotal = 0;
			//
			//							coopbanktotal = 0;
			//
			//							mantralayatotal = 0;
			//							creditSoctotal = 0;
			//							conStoretotal = 0;
			//							mistotal = 0;
			//
			//							recurrdeposittotal = 0;
			//							licNewtotal = 0;
			//							misctotal = 0;
			//							otherRecoverytotal = 0;
			//							otherDeductiontotal = 0;
			//							totalDeductotal = 0;
			//
			//						}
			//						cntGrandNetAmountTotal = cntNetAmountTotal;
			//						cntGrandNetPayTotal = cntNetAmountTotal;
			//						GrandNetAmountTotal += cntNetAmountTotal;
			//						GrandNepPayTotal += cntGrandNetPayTotal;
			//
			//						GrandmrtCoOpSoctotal += cntMrtCoOpSoctotal;
			//
			//						GrandCoOpBanktotal += cntCoOpBanktotal;
			//
			//						GrandMantralayatotal += cntMantralayatotal;
			//						GrandCreditSoctotal += cntCreditSoctotal;
			//						GrandConStoretotal += cntConStoretotal;
			//						GrandMistotal += cntMistotal;
			//
			//						GrandcntreCurrdeposittotal += cntreCurrdeposittotal;
			//						GrandcntLicNewtotal += cntLicNewtotal;
			//						GrandcntMisctotal += cntMisctotal;
			//						GrandcntOtherRecoverytotal += cntOtherRecoverytotal;
			//						GrandcntOtherDeductiontotal += cntOtherDeductiontotal;
			//						GrandcntTotalDeductotal += cntTotalDeductotal;
			//
			//						cntNetAmountTotal = 0;
			//						cntNetPayTotal = 0;
			//
			//						cntMrtCoOpSoctotal = 0;
			//						cntCoOpBanktotal = 0;
			//
			//						cntMantralayatotal = 0;
			//						cntCreditSoctotal = 0;
			//						cntConStoretotal = 0;
			//						cntMistotal = 0;
			//
			//						cntreCurrdeposittotal = 0;
			//						cntLicNewtotal = 0;
			//						cntMisctotal = 0;
			//						cntOtherRecoverytotal = 0;
			//						cntOtherDeductiontotal = 0;
			//						cntTotalDeductotal = 0;
			//
			//						cnt++;
			//
			//					}
			//					//System.out.println("cont value is ***********"+cnt);
			//					//	GrandNetAmountTotal=cntGrandNetAmountTotal+netAmttotal;
			//					//GrandNepPayTotal=cntGrandNetPayTotal+netPaytotal;
			//
			//					if (netAmttotal != 0)
			//					{
			//
			//						GrandNetAmountTotal = GrandNetAmountTotal + netAmttotal;
			//						GrandNepPayTotal = GrandNepPayTotal + netPaytotal;
			//
			//						GrandmrtCoOpSoctotal = GrandmrtCoOpSoctotal + mrtCoOpSoctotal;
			//
			//						GrandCoOpBanktotal = GrandCoOpBanktotal + coopbanktotal;
			//
			//						GrandMantralayatotal = GrandMantralayatotal + mantralayatotal;
			//						GrandCreditSoctotal = GrandCreditSoctotal + creditSoctotal;
			//						GrandConStoretotal = GrandConStoretotal + conStoretotal;
			//						GrandMistotal = GrandMistotal + mistotal;
			//
			//						GrandcntreCurrdeposittotal = GrandcntreCurrdeposittotal + recurrdeposittotal;
			//						GrandcntLicNewtotal = GrandcntLicNewtotal + licNewtotal;
			//						GrandcntMisctotal = GrandcntMisctotal + misctotal;
			//						GrandcntOtherRecoverytotal = GrandcntOtherRecoverytotal + otherRecoverytotal;
			//						GrandcntOtherDeductiontotal = GrandcntOtherDeductiontotal + otherDeductiontotal;
			//						GrandcntTotalDeductotal = GrandcntTotalDeductotal + totalDeductotal;
			//
			//						rowList = new ArrayList();
			//						rowList.add("");
			//						StyledData dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						dataStyle3.setData("Rupees:");
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						dataStyle3.setData(netAmttotal);
			//						rowList.add(dataStyle3);
			//
			//						rowList.add("");
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (mrtCoOpSoctotal > 0)
			//						{
			//							dataStyle3.setData(mrtCoOpSoctotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (recurrdeposittotal > 0)
			//						{
			//							dataStyle3.setData(recurrdeposittotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (licNewtotal > 0)
			//						{
			//							dataStyle3.setData(licNewtotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (misctotal > 0)
			//						{
			//							dataStyle3.setData(misctotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (otherRecoverytotal > 0)
			//						{
			//							dataStyle3.setData(otherRecoverytotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (otherDeductiontotal > 0)
			//						{
			//							dataStyle3.setData(otherDeductiontotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (coopbanktotal > 0)
			//						{
			//							dataStyle3.setData(coopbanktotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//
			//						rowList.add(dataStyle3);
			//
			//						//Start new one
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (mantralayatotal > 0)
			//						{
			//							dataStyle3.setData(mantralayatotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (creditSoctotal > 0)
			//						{
			//							dataStyle3.setData(creditSoctotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (conStoretotal > 0)
			//						{
			//							dataStyle3.setData(conStoretotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (mistotal > 0)
			//						{
			//							dataStyle3.setData(mistotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//
			//						rowList.add(dataStyle3);
			//
			//						//End new one
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						if (totalDeductotal > 0)
			//						{
			//							dataStyle3.setData(totalDeductotal);
			//						}
			//						else
			//						{
			//							dataStyle3.setData("");
			//						}
			//						rowList.add(dataStyle3);
			//
			//						dataStyle3 = new StyledData();
			//						dataStyle3.setStyles(boldStyleVO);
			//						dataStyle3.setData(Math.round(netPaytotal));
			//						rowList.add(dataStyle3);
			//
			//						DataList.add(rowList);
			//
			//						pageCnt++;
			//						rowList = new ArrayList();
			//						rowList.add(new PageBreak());
			//						rowList.add("Data");
			//						DataList.add(rowList);
			//
			//						rowList = new ArrayList();
			//						StyledData dataStyle4 = new StyledData();
			//						rowList.add("");
			//
			//						dataStyle4.setStyles(boldStyleVO);
			//						dataStyle4.setData("Grand Total:");
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						dataStyle4.setData(GrandNetAmountTotal);
			//						rowList.add(dataStyle4);
			//
			//						rowList.add("");
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandmrtCoOpSoctotal > 0)
			//						{
			//							dataStyle4.setData(GrandmrtCoOpSoctotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntreCurrdeposittotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntreCurrdeposittotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntLicNewtotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntLicNewtotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntMisctotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntMisctotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntOtherRecoverytotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntOtherRecoverytotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntOtherDeductiontotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntOtherDeductiontotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandCoOpBanktotal > 0)
			//						{
			//							dataStyle4.setData(GrandCoOpBanktotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						// Start for new one
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandMantralayatotal > 0)
			//						{
			//							dataStyle4.setData(GrandMantralayatotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandCreditSoctotal > 0)
			//						{
			//							dataStyle4.setData(GrandCreditSoctotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandConStoretotal > 0)
			//						{
			//							dataStyle4.setData(GrandConStoretotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandMistotal > 0)
			//						{
			//							dataStyle4.setData(GrandMistotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						//End for new one
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntTotalDeductotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntTotalDeductotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						//	dataStyle4.setData(Math.round(GrandNepPayTotal));                  
			//						dataStyle4.setData(Math.round(GrandNetAmountTotal - GrandcntTotalDeductotal));
			//						rowList.add(dataStyle4);
			//
			//						DataList.add(rowList);
			//
			//						rowList = new ArrayList();
			//
			//						StyledData dataStyle2 = new StyledData();
			//						dataStyle2.setStyles(centerboldStyleVO);
			//						dataStyle2.setData("GrandTotal Rupees:  " + ConvertNumbersToWord.convert(Math.round(GrandNetAmountTotal - GrandcntTotalDeductotal)) + " Only.");
			//
			//						ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
			//						ReportColumnVO[] rptCol = report.getColumnsToDisplay();
			//						int totallength = rptCol.length;
			//						int colspan = rptCol2.length;
			//						if (!bankName.equals("") && !bankName.equals("-1"))
			//							if (bankName.equalsIgnoreCase("Society - New") || bankName.equalsIgnoreCase("Society - Old"))
			//								dataStyle2.setColspan(colspan - 8);
			//							else
			//								dataStyle2.setColspan(colspan - 9);
			//						else
			//							dataStyle2.setColspan(colspan);
			//						rowList.add(dataStyle2);
			//
			//						if (!bankName.equals("") && !bankName.equals("-1"))
			//							if (bankName.equalsIgnoreCase("Society - New") || bankName.equalsIgnoreCase("Society - Old"))
			//								for (int c = 0; c < (totallength - colspan - 8); c++)
			//									rowList.add("");
			//							else
			//								for (int c = 0; c < (totallength - colspan - 9); c++)
			//									rowList.add("");
			//						else
			//							for (int c = 0; c < (totallength - colspan); c++)
			//								rowList.add("");
			//						rowList.add(pageCnt);
			//
			//						DataList.add(rowList);
			//						ReportColumnVO[] rptCol5 = report.getReportColumns();
			//
			//						report.setReportColumns(rptCol5);
			//						report.initializeDynamicTreeModel();
			//						report.initializeTreeModel();
			//
			//						StyleVO[] URLStyleVO = null;
			//						URLStyleVO = new StyleVO[2];
			//
			//						URLStyleVO[0] = new StyleVO();
			//						URLStyleVO[0].setStyleId(IReportConstants.ROWS_PER_PAGE);
			//						URLStyleVO[0].setStyleValue(finalpagesize + "");
			//
			//						URLStyleVO[1] = new StyleVO();
			//						URLStyleVO[1].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_GROUPBY);
			//						URLStyleVO[1].setStyleValue("yes");
			//
			//						report.addReportStyles(URLStyleVO);
			//
			//						SortingHelper Helper = new SortingHelper(DataList);
			//
			//						report.setReportColumns(rptCol);
			//
			//						report.initializeDynamicTreeModel();
			//						report.initializeTreeModel();
			//					}
			//
			//					else
			//					{
			//
			//						GrandNetAmountTotal = GrandNetAmountTotal + netAmttotal;
			//						GrandNepPayTotal = GrandNepPayTotal + netPaytotal;
			//
			//						GrandmrtCoOpSoctotal = GrandmrtCoOpSoctotal + mrtCoOpSoctotal;
			//
			//						GrandCoOpBanktotal = GrandCoOpBanktotal + coopbanktotal;
			//
			//						GrandMantralayatotal = GrandMantralayatotal + mantralayatotal;
			//						GrandCreditSoctotal = GrandCreditSoctotal + creditSoctotal;
			//						GrandConStoretotal = GrandConStoretotal + conStoretotal;
			//						GrandMistotal = GrandMistotal + mistotal;
			//
			//						GrandcntreCurrdeposittotal = GrandcntreCurrdeposittotal + recurrdeposittotal;
			//						GrandcntLicNewtotal = GrandcntLicNewtotal + licNewtotal;
			//						GrandcntMisctotal = GrandcntMisctotal + misctotal;
			//						GrandcntOtherRecoverytotal = GrandcntOtherRecoverytotal + otherRecoverytotal;
			//						GrandcntOtherDeductiontotal = GrandcntOtherDeductiontotal + otherDeductiontotal;
			//						GrandcntTotalDeductotal = GrandcntTotalDeductotal + totalDeductotal;
			//
			//						rowList = new ArrayList();
			//						StyledData dataStyle4 = new StyledData();
			//						rowList.add("");
			//
			//						dataStyle4.setStyles(boldStyleVO);
			//						dataStyle4.setData("Grand Total:");
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						dataStyle4.setData(GrandNetAmountTotal);
			//						rowList.add(dataStyle4);
			//
			//						rowList.add("");
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandmrtCoOpSoctotal > 0)
			//						{
			//							dataStyle4.setData(GrandmrtCoOpSoctotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntreCurrdeposittotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntreCurrdeposittotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntLicNewtotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntLicNewtotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntMisctotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntMisctotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntOtherRecoverytotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntOtherRecoverytotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntOtherDeductiontotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntOtherDeductiontotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandCoOpBanktotal > 0)
			//						{
			//							dataStyle4.setData(GrandCoOpBanktotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						//Start new one
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandMantralayatotal > 0)
			//						{
			//							dataStyle4.setData(GrandMantralayatotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandCreditSoctotal > 0)
			//						{
			//							dataStyle4.setData(GrandCreditSoctotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandConStoretotal > 0)
			//						{
			//							dataStyle4.setData(GrandConStoretotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandMistotal > 0)
			//						{
			//							dataStyle4.setData(GrandMistotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						//End new one
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						if (GrandcntTotalDeductotal > 0)
			//						{
			//							dataStyle4.setData(GrandcntTotalDeductotal);
			//						}
			//						else
			//						{
			//							dataStyle4.setData("");
			//						}
			//						rowList.add(dataStyle4);
			//
			//						dataStyle4 = new StyledData();
			//						dataStyle4.setStyles(boldStyleVO);
			//						dataStyle4.setData(Math.round(GrandNepPayTotal - GrandcntTotalDeductotal));
			//						rowList.add(dataStyle4);
			//
			//						DataList.add(rowList);
			//						rowList = new ArrayList();
			//
			//						StyledData dataStyle2 = new StyledData();
			//						dataStyle2.setStyles(centerboldStyleVO);
			//						dataStyle2.setData("GrandTotal Rupees:  " + ConvertNumbersToWord.convert(Math.round(GrandNepPayTotal - GrandcntTotalDeductotal)) + " Only.");
			//
			//						ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
			//						ReportColumnVO[] rptCol = report.getColumnsToDisplay();
			//						int totallength = rptCol.length;
			//						int colspan = rptCol2.length;
			//						if (!bankName.equals("") && !bankName.equals("-1"))
			//							if (bankName.equalsIgnoreCase("Society - New") || bankName.equalsIgnoreCase("Society - Old"))
			//								dataStyle2.setColspan(colspan - 8);
			//							else
			//								dataStyle2.setColspan(colspan - 9);
			//						else
			//							dataStyle2.setColspan(colspan);
			//						rowList.add(dataStyle2);
			//
			//						if (!bankName.equals("") && !bankName.equals("-1"))
			//							if (bankName.equalsIgnoreCase("Society - New") || bankName.equalsIgnoreCase("Society - Old"))
			//								for (int c = 0; c < (totallength - colspan - 8); c++)
			//									rowList.add("");
			//							else
			//								for (int c = 0; c < (totallength - colspan - 9); c++)
			//									rowList.add("");
			//						else
			//							for (int c = 0; c < (totallength - colspan); c++)
			//								rowList.add("");
			//						rowList.add(pageCnt);
			//
			//						DataList.add(rowList);
			//						ReportColumnVO[] rptCol5 = report.getReportColumns();
			//
			//						report.setReportColumns(rptCol5);
			//						report.initializeDynamicTreeModel();
			//						report.initializeTreeModel();
			//
			//						StyleVO[] URLStyleVO = null;
			//						URLStyleVO = new StyleVO[2];
			//
			//						URLStyleVO[0] = new StyleVO();
			//						URLStyleVO[0].setStyleId(IReportConstants.ROWS_PER_PAGE);
			//						URLStyleVO[0].setStyleValue(finalpagesize + "");
			//
			//						URLStyleVO[1] = new StyleVO();
			//						URLStyleVO[1].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_GROUPBY);
			//						URLStyleVO[1].setStyleValue("yes");
			//
			//						report.addReportStyles(URLStyleVO);
			//
			//						SortingHelper Helper = new SortingHelper(DataList);
			//
			//						report.setReportColumns(rptCol);
			//
			//						report.initializeDynamicTreeModel();
			//						report.initializeTreeModel();
			//					}
			//				}
			//
			//				// ended by rajan
			//
			//				return DataList;
			//			}
			if (report.getReportCode().equals("5000009"))
			{
				logger.info("In side new report generation method");
				finalpagesize = 8;
				long locactionId = Long.parseLong(baseLoginMap.get("locationId").toString());
				
				
				long langId = StringUtility.convertToLong(baseLoginMap.get("langId").toString());
				long nonGovDeducType = Long.parseLong(resourceBundle.getString("nonGovDeducType"));

				String Department = CheckIfNull(report.getParameterValue("Department"));
				String Grade = CheckIfNull(report.getParameterValue("Grade"));
				String Designation = CheckIfNull(report.getParameterValue("Designation"));
				String BillNoinner = CheckIfNull(report.getParameterValue("Bill No"));
				//ADDED BY ROSHAN FOR REPORTS AT ALL LEVEL
				
				if ((BillNoinner !=null) && (BillNoinner !="")){
					TokenNumberDAOImpl TokenDAO = new TokenNumberDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
					locactionId=TokenDAO.getLocationCode(BillNoinner);
					logger.info("hii i m in mahapaybillinnerdao for inner.");
					
				} 
				//ended BY ROSHAN FOR REPORTS AT ALL LEVEL
				StringTokenizer st1 = new StringTokenizer(BillNoinner, "~");
				int l = 0;
				String subheadCode = "";
				String classIds = "";
				String desgnIds = "";
				int month = 0;
				int year = 0;
				String bankName = "";
				String DeptName = "";
				String ddocode = "";
				String TresuryCode = "";
				String gradeName = "";
				String DsgnName = "";
				String deptHeader = "";

				OrgDdoMst ddoMst = null;

				while (st1.hasMoreTokens())
				{
					if (l == 0)
						subheadCode = st1.nextToken();
					else if (l == 1)
						classIds = st1.nextToken();
					else if (l == 2)
						desgnIds = st1.nextToken();
					else if (l == 3)
						portType = st1.nextToken();
					else if (l == 4)
						BillNo = st1.nextToken();
					l++;
				}

				logger.info("bill no is ***********" + BillNo);
				logger.info("bill no is ***********" + BillNoinner);
				logger.info("bill no is ***********" + report.getParameterValue("Bill No"));

				if (BillNoinner.trim().equals("") || BillNo.equalsIgnoreCase(""))
				{
					logger.info("inside if condition bill no is ***********" + report.getParameterValue("Bill No"));
					BillNo = report.getParameterValue("Bill No") != null ? report.getParameterValue("Bill No").toString().trim() : "";
				}

				if (!CheckIfNull(report.getParameterValue("Month")).equals(""))
				{
					month = Integer.parseInt(CheckIfNull(report.getParameterValue("Month")));
				}
				if (!CheckIfNull(report.getParameterValue("Year")).equals(""))
				{
					year = Integer.parseInt(CheckIfNull(report.getParameterValue("Year")));
				}
				PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				DeptName = payBillDAOImpl.getOffice(locationId);

				if (!CheckIfNull(report.getParameterValue("bankName")).equals(""))
				{
					bankName = (CheckIfNull(report.getParameterValue("bankName")));
					if (bankName.equals("-1")) //check if null doesnt takes care of this
						bankName = "";
				}

				PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locactionId);
				if (ddoCodeList != null)
					logger.info("After query execution for DDO Code " + ddoCodeList.size());

				if (ddoCodeList != null && ddoCodeList.size() > 0)
				{
					ddoMst = ddoCodeList.get(0);
				}
				if (ddoMst != null)
				{
					ddocode = ddoMst.getDdoCode();
				}
				logger.info("DDO Code is " + ddocode);

				List TreasuryList = payBillDAOImpl.getTreasuryCodeAndTreasuryName(ddocode);
				if (TreasuryList != null && TreasuryList.size() != 0)
				{
					for (Iterator itr = TreasuryList.iterator(); itr.hasNext();)
					{
						Object[] rowList = (Object[]) itr.next();
						if (rowList[0] != null)
						{
						}
						if (rowList[1] != null)
						{
							TresuryCode = rowList[1].toString().trim();
						}
					}
				}

				if (!Grade.equals("") && !Grade.equals("-1"))
					gradeName = payBillDAOImpl.getGradenameFromGradeId(Long.parseLong(Grade), langId);

				if (!Designation.equals("") && !Designation.equals("-1"))
					DsgnName = payBillDAOImpl.getDsgnNameFromDsgnId(Long.parseLong(Designation), langId);

				logger.info("locactionId is***********" + locactionId);
				GenericDaoHibernateImpl gen = new GenericDaoHibernateImpl(CmnLocationMst.class);
				gen.setSessionFactory(serv.getSessionFactory());
				CmnLocationMst cmnLocMst = (CmnLocationMst) gen.read(locactionId);
				long parentLocId = cmnLocMst.getParentLocId();

				logger.info("GRADE ID***********" + gradeName);
				logger.info("dsgnName***********" + DsgnName);
				logger.info("parentLocId***********" + parentLocId);

				String Treasury = TresuryCode;
				String space = "&";
				String Section = "All Employees";
				String Section1 = DsgnName;
				String Section2 = gradeName;
				String Section3 = DsgnName + space + gradeName;


				ArrayList HeaderDataList = new ArrayList();
				String[] monthNameArr = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
				String monthName = monthNameArr[month - 1];
				HeaderDataList.add(new StyledData("For the Month of " + monthName + " " + year, leftHeader));

				if (!BillNo.equals("") && !BillNo.equals("-1") && !Grade.equals("") && !Grade.equals("-1") && !Designation.equals("") && !Designation.equals("-1"))
				{
					HeaderDataList.add(new StyledData("Section :Designation&Group -" + Section3, rightHead));
				}
				else if (!BillNo.equals("") && !BillNo.equals("-1") && !Designation.equals("") && !Designation.equals("-1"))
				{
					HeaderDataList.add(new StyledData("Section :Designation -" + Section1, rightHead));
				}
				else if (!BillNo.equals("") && !BillNo.equals("-1") && !Grade.equals("") && !Grade.equals("-1"))
				{
					HeaderDataList.add(new StyledData("Section :Group -" + Section2, rightHead));
				}
				else
				{
					HeaderDataList.add(new StyledData("Section :" + Section, rightHead));
				}

				ArrayList stData = new ArrayList();
				ArrayList styleList = new ArrayList();
				StyledData styledHeader = new StyledData(deptHeader, headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
				styleList.add(stData);
				styleList.add(HeaderDataList);
				ArrayList r2 = new ArrayList();
				r2.add(new StyledData("Name of the Office : " + DeptName, leftHeader));
				r2.add(new StyledData("Treasury : " + Treasury + "  " + "DDO:" + ddocode, rightHead));
				styleList.add(r2);

				TabularData ttData = new TabularData(styleList);
				ttData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				ttData.addStyle(IReportConstants.BORDER, "No");
				ttData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(ttData);

				//Geting List of Deduc Type which map with Dept.
				List nonGovDeducCode = getDistinctDeducCode(locactionId, nonGovDeducType, session, month, year, BillNo);
				ReportColumnVO[] rptCol = report.getReportColumns();
				String deducCodes = "";
				int noColForColspan = 5;
				for (int j = 4; j < rptCol.length - 2; j++)
				{
					rptCol[j].setHidden("Y");
				}
				for (int i = 0; i < nonGovDeducCode.size(); i++)
				{
					deducCodes = deducCodes + nonGovDeducCode.get(i).toString() + ",";
					for (int j = 4; j < rptCol.length - 2; j++)
					{
						if (rptCol[j].getColumnName().equalsIgnoreCase(nonGovDeducCode.get(i).toString()))
						{
							rptCol[j].setHidden("N");
							noColForColspan++;
							break;
						}
					}
				}
				if (deducCodes.length() > 0)
					deducCodes = deducCodes.substring(0, deducCodes.lastIndexOf(","));

				List aquiDataList = getAquiData(Designation, Grade, locactionId, month, year, BillNo, parentLocId, deducCodes, session);
				List nonGovDeducCodeList = getAllNonGovDeducCode(session);
				//List DistinctEmpIdList = getDistinctEmpId(Designation, Grade, locactionId, month, year, BillNo, parentLocId, deducCodes, session);
				List<Object[]> DistinctEmpIdList = new ArrayList<Object[]>();
				
				Map<Long, Object> aquiDataMap = new HashMap<Long, Object>();
				long previousEmpId = 0;
				long aquiDataListSize = aquiDataList.size();
				for (int i = 0; i < aquiDataListSize; i++)
				{
					Object[] objArrFromDb = (Object[]) aquiDataList.get(i);
					String empLname="",empFname="",empMname="";
					if(objArrFromDb[0] != null )
						empLname = objArrFromDb[1].toString();
					
					if(objArrFromDb[11] != null )
						empFname = objArrFromDb[11].toString();
					
					if(objArrFromDb[12] != null )
						empMname = objArrFromDb[12].toString();
					
					String empFullName = "";
					if(empLname != null && !empLname.equals(""))
						empFullName = empFullName + empLname + " ";
					
					if(empFname != null && !empFname.equals(""))
						empFullName = empFullName + empFname + " ";
					
					if(empMname != null && !empMname.equals(""))
						empFullName = empFullName + empMname;
					
					double netTotal = Double.parseDouble(objArrFromDb[2].toString());
					//line to be changed
					long nonGovCode = Long.parseLong(objArrFromDb[3].toString());
					double amount =0.0;
					if((objArrFromDb[4]!=null)&&(!objArrFromDb[4].equals(""))){
					amount = Double.parseDouble(objArrFromDb[4].toString());
					}
					logger.info("amount***"+amount);
					//added by vaibhav tyagi: start
					String nonGovType =null;
					if((objArrFromDb[13]!=null)&&(!objArrFromDb[13].equals(""))){
						logger.info("Inside if");
					nonGovType = objArrFromDb[13].toString();
					}
					else{
						logger.info("Inside else");
						nonGovType =" ";
					}
					logger.info("nonGovType***"+nonGovType);
					//added by vaibhav tyagi: end
					long empId = Long.parseLong(objArrFromDb[0].toString());
					String empSevartId = objArrFromDb[9].toString();
					String dsgnName = objArrFromDb[10].toString();
					long currentEmpId = empId;
					if (currentEmpId != previousEmpId)
					{
						Map<Long, Double> amountMap = new HashMap<Long, Double>();
						//added by vaibhav tyagi: start
						Map<Long, String> nonGovTypeMap = new HashMap<Long, String>();
						//added by vaibhav tyagi: end
						Object[] obj = {currentEmpId,empFullName};
						DistinctEmpIdList.add(obj);
						for (int j = 0; j < nonGovDeducCodeList.size(); j++)
						{
							if (Long.parseLong(nonGovDeducCodeList.get(j).toString()) == nonGovCode){
								amountMap.put(nonGovCode, amount);
								//added by vaibhav tyagi: start
								nonGovTypeMap.put(nonGovCode, nonGovType);
								//added by vaibhav tyagi: end
							}
							else{
								amountMap.put(Long.parseLong(nonGovDeducCodeList.get(j).toString()), 0.0);
								//added by vaibhav tyagi: start
								nonGovTypeMap.put(Long.parseLong(nonGovDeducCodeList.get(j).toString()), " ");
								//added by vaibhav tyagi: end
							}
						}
						Object[] dataForPut = { empFullName, netTotal, amountMap, empSevartId, dsgnName , nonGovTypeMap};
						aquiDataMap.put(currentEmpId, dataForPut);
					}
					else
					{
						Object[] dataForPut = (Object[]) aquiDataMap.get(currentEmpId);
						Map<Long, Double> amountMap = (Map) dataForPut[2];
						//added by vaibhav tyagi: start
						Map<Long, String> nonGovTypeMap = (Map) dataForPut[5];
						//added by vaibhav tyagi: end
						amountMap.put(nonGovCode, amount);
						//added by vaibhav tyagi: start
						nonGovTypeMap.put(nonGovCode, nonGovType);
						//added by vaibhav tyagi: end
						Object[] newDataForPut = { empFullName, netTotal, amountMap, empSevartId, dsgnName ,nonGovTypeMap};
						aquiDataMap.put(currentEmpId, newDataForPut);
					}
					
					previousEmpId = currentEmpId;
				}

				ArrayList rowList = new ArrayList();
				int srNo = 1;
				double nonGovDeducTotal = 0;
				double nonGovDeducNetAmount = 0;

				//Column Total Variables
				double columnTotal_netTotal = 0;
				Map<Long, Double> columnTotal_nonGovAmount = new HashMap<Long, Double>();
				double columnTotal_nonGovDeducTotal = 0;
				double columnTotal_nonGovDeducNetAmount = 0;

				double grandTotal_netTotal = 0;
				Map<Long, Double> grandTotal_nonGovAmount = new HashMap<Long, Double>();
				for (int j = 4; j < rptCol.length - 2; j++)
				{
					grandTotal_nonGovAmount.put(Long.parseLong(rptCol[j].getColumnName()), 0.0);
				}
				double grandTotal_nonGovDeducTotal = 0;
				double grandTotal_nonGovDeducNetAmount = 0;

				StyleVO[] colorStyleVO2 = new StyleVO[2];
				colorStyleVO2[0] = new StyleVO();
				colorStyleVO2[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
				colorStyleVO2[0].setStyleValue("Black");
				colorStyleVO2[1] = new StyleVO();
				colorStyleVO2[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				colorStyleVO2[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);

				StyleVO[] nameVo = new StyleVO[1];
				nameVo[0] = new StyleVO();
				nameVo[0].setStyleId(IReportConstants.BORDER);
				nameVo[0].setStyleValue("NO");

				if (DistinctEmpIdList.size() > 0)
				{
					Iterator DistinctEmpIdIterator = DistinctEmpIdList.iterator();
					while (DistinctEmpIdIterator.hasNext())
					{
						Object[] row = (Object[]) DistinctEmpIdIterator.next();
						nonGovDeducTotal = 0;
						nonGovDeducNetAmount = 0;
						long empId = Long.parseLong((row[0] != null ? row[0] : "0").toString());//emp ID
						Object[] rowData = (Object[]) aquiDataMap.get(empId);
						String empLname="",empFname="",empMname="";
						/*if(row[0] != null )
							empLname = row[1].toString();
						
						if(row[9] != null )
							empFname = row[9].toString();//13
						
						if(row[10] != null )
							empMname = row[10].toString();//14*/
						
						String empFullName = row[1].toString();
						/*if(empLname != null && !empLname.equals(""))
							empFullName = empFullName + empLname + " ";
						
						if(empFname != null && !empFname.equals(""))
							empFullName = empFullName + empFname + " ";
						
						if(empMname != null && !empMname.equals(""))
							empFullName = empFullName + empMname;*/
						
						double netTotal = Double.parseDouble((rowData[1] != null ? rowData[1] : "0").toString());
						columnTotal_netTotal += netTotal;
						Map<Long, Double> nonGovAmount = (Map) rowData[2];
						String sevarth_Id = "(" + rowData[3].toString() + ")";
						String dsgn_Name = "(" + rowData[4].toString() + ")";
						//added by vaibhav tyagi: start
						Map<Long, String> nonGovTypeValueMap = (Map) rowData[5];
						//added by vaibhav tyagi: end

						rowList = new ArrayList();
						StyledData dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO2);
						dataStyle1.setData(new Integer(srNo)); // Sr. No.
						rowList.add(dataStyle1);

						List innerData = new ArrayList();
						List innerRow = new ArrayList();
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO2);
						dataStyle1.setData(empFullName);
						innerRow.add(dataStyle1);
						innerData.add(innerRow);

						innerRow = new ArrayList();
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO2);
						dataStyle1.setData(sevarth_Id);
						innerRow.add(dataStyle1);
						innerData.add(innerRow);

						innerRow = new ArrayList();
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO2);
						dataStyle1.setData(dsgn_Name);
						innerRow.add(dataStyle1);
						innerData.add(innerRow);

						TabularData td = new TabularData(innerData);
						td.setStyles(nameVo);
						rowList.add(td);

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO2);
						dataStyle1.setData(netTotal); // Net Total 
						rowList.add(dataStyle1);

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO2);
						dataStyle1.setData(""); // Date with Sign
						rowList.add(dataStyle1);

						for (int i = 0; i < nonGovDeducCode.size(); i++)
						{
							nonGovDeducTotal += Double.parseDouble(nonGovAmount.get(Long.parseLong(nonGovDeducCode.get(i).toString())).toString());
							//nonGovDeducTotal=0.0;
						}

						for (int j = 4; j < rptCol.length - 2; j++)
						{
							if (nonGovAmount.containsKey(Long.parseLong(rptCol[j].getColumnName())))
							{
								long nonGovCd = Long.parseLong(rptCol[j].getColumnName());
								double amount = nonGovAmount.get(Long.parseLong(rptCol[j].getColumnName()));//vaibhav make change here
								//added by vaibhav tyagi : start
								String nonGovTypeValueFromMap= nonGovTypeValueMap.get(Long.parseLong(rptCol[j].getColumnName()));//vaibhav make change here
								String nonGovTypeValueFromMapFinal= nonGovTypeValueFromMap.replace(";", "<br>");
								//added by vaibhav tyagi : end
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(colorStyleVO2);
								//commented by vaibhav tyagi
								//dataStyle1.setData(amount); // Non Gov deduc amount
								//added by vaibhav tyagi : start
								if(nonGovCd==96 &&amount!=0){
								dataStyle1.setData(amount+"<br>"+"(<i>Division : <br>"+nonGovTypeValueFromMapFinal+"</i>)"); // Non Gov deduc amount
								}
								else
									dataStyle1.setData(amount); // Non Gov deduc amount
								//added by vaibhav tyagi : end
								if (columnTotal_nonGovAmount.containsKey(nonGovCd))
								{
									double previousAmount = columnTotal_nonGovAmount.get(nonGovCd);
									previousAmount += amount;
									columnTotal_nonGovAmount.put(nonGovCd, previousAmount);
								}
								else
								{
									columnTotal_nonGovAmount.put(nonGovCd, amount);
								}
								rowList.add(dataStyle1);
							}
						}

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO2);
						dataStyle1.setData(nonGovDeducTotal); // Non Gov Deduc Total
						rowList.add(dataStyle1);

						columnTotal_nonGovDeducTotal += nonGovDeducTotal; //Displaying total of Total Ng. Deduc

						nonGovDeducNetAmount = netTotal - nonGovDeducTotal;
						columnTotal_nonGovDeducNetAmount += nonGovDeducNetAmount; //Displaying total of Net Amount
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(colorStyleVO2);
						dataStyle1.setData(nonGovDeducNetAmount); // Net Amount
						rowList.add(dataStyle1);

						DataList.add(rowList);

						if ((srNo % (finalpagesize - 1)) == 0)
						{
							rowList = new ArrayList();
							rowList.add("");
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO2);
							dataStyle1.setData("Total (`):");
							dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
							rowList.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO2);
							dataStyle1.setData(columnTotal_netTotal);
							rowList.add(dataStyle1);

							grandTotal_netTotal += columnTotal_netTotal;

							rowList.add("");

							for (int j = 4; j < rptCol.length - 2; j++)
							{
								if (columnTotal_nonGovAmount.containsKey(Long.parseLong(rptCol[j].getColumnName())))
								{
									double total = columnTotal_nonGovAmount.get(Long.parseLong(rptCol[j].getColumnName()));
									if (total > 0)
									{
										dataStyle1 = new StyledData();
										dataStyle1.setStyles(colorStyleVO2);
										dataStyle1.setData(total);
										rowList.add(dataStyle1);
									}
									else
									{
										dataStyle1 = new StyledData();
										dataStyle1.setStyles(colorStyleVO2);
										dataStyle1.setData("");
										rowList.add(dataStyle1);
									}
									if (grandTotal_nonGovAmount.containsKey(Long.parseLong(rptCol[j].getColumnName())))
									{
										double grandTotal = grandTotal_nonGovAmount.get(Long.parseLong(rptCol[j].getColumnName()));
										grandTotal += total;
										grandTotal_nonGovAmount.put(Long.parseLong(rptCol[j].getColumnName()), grandTotal);
									}
								}
							}

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO2);
							dataStyle1.setData(columnTotal_nonGovDeducTotal);
							rowList.add(dataStyle1);

							grandTotal_nonGovDeducTotal += columnTotal_nonGovDeducTotal;

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO2);
							dataStyle1.setData(columnTotal_nonGovDeducNetAmount);
							rowList.add(dataStyle1);
							DataList.add(rowList);

							grandTotal_nonGovDeducNetAmount += columnTotal_nonGovDeducNetAmount;

							

							rowList = new ArrayList();
							rowList.add(new PageBreak());
							rowList.add("Data");
							DataList.add(rowList);
						}
						if (srNo == DistinctEmpIdList.size())
						{
							if((srNo % (finalpagesize - 1)) != 0)
							{
								rowList = new ArrayList();
								rowList.add("");
								dataStyle1 = new StyledData();
								dataStyle1.setStyles(colorStyleVO2);
								dataStyle1.setData("Total (`):");
								dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
								rowList.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(colorStyleVO2);
								dataStyle1.setData(columnTotal_netTotal);
								rowList.add(dataStyle1);

								grandTotal_netTotal += columnTotal_netTotal;

								rowList.add("");

								for (int j = 4; j < rptCol.length - 2; j++)
								{
									if (columnTotal_nonGovAmount.containsKey(Long.parseLong(rptCol[j].getColumnName())))
									{
										double total = columnTotal_nonGovAmount.get(Long.parseLong(rptCol[j].getColumnName()));
										if (total > 0)
										{
											dataStyle1 = new StyledData();
											dataStyle1.setStyles(colorStyleVO2);
											dataStyle1.setData(total);
											rowList.add(dataStyle1);
										}
										else
										{
											dataStyle1 = new StyledData();
											dataStyle1.setStyles(colorStyleVO2);
											dataStyle1.setData("");
											rowList.add(dataStyle1);
										}
										if (grandTotal_nonGovAmount.containsKey(Long.parseLong(rptCol[j].getColumnName())))
										{
											double grandTotal = grandTotal_nonGovAmount.get(Long.parseLong(rptCol[j].getColumnName()));
											grandTotal += total;
											grandTotal_nonGovAmount.put(Long.parseLong(rptCol[j].getColumnName()), grandTotal);
										}
									}
								}

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(colorStyleVO2);
								dataStyle1.setData(columnTotal_nonGovDeducTotal);
								rowList.add(dataStyle1);

								grandTotal_nonGovDeducTotal += columnTotal_nonGovDeducTotal;

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(colorStyleVO2);
								dataStyle1.setData(columnTotal_nonGovDeducNetAmount);
								rowList.add(dataStyle1);
								DataList.add(rowList);

								grandTotal_nonGovDeducNetAmount += columnTotal_nonGovDeducNetAmount;
							}
							
							//For Grand Total
							/*rowList = new ArrayList();
							rowList.add(new PageBreak());
							rowList.add("Data");
							DataList.add(rowList);*/

							rowList = new ArrayList();
							rowList.add("");
							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO2);
							dataStyle1.setData("Grand Total (`):");
							dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
							rowList.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO2);
							dataStyle1.setData(grandTotal_netTotal);
							rowList.add(dataStyle1);

							rowList.add("");

							for (int j = 4; j < rptCol.length - 2; j++)
							{
								if (grandTotal_nonGovAmount.containsKey(Long.parseLong(rptCol[j].getColumnName())))
								{
									double Grand_total = grandTotal_nonGovAmount.get(Long.parseLong(rptCol[j].getColumnName()));
									if (Grand_total > 0)
									{
										dataStyle1 = new StyledData();
										dataStyle1.setStyles(colorStyleVO2);
										dataStyle1.setData(Grand_total);
										rowList.add(dataStyle1);
									}
									else
									{
										dataStyle1 = new StyledData();
										dataStyle1.setStyles(colorStyleVO2);
										dataStyle1.setData("");
										rowList.add(dataStyle1);
									}
								}
							}

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO2);
							dataStyle1.setData(grandTotal_nonGovDeducTotal);
							rowList.add(dataStyle1);

							dataStyle1 = new StyledData();
							dataStyle1.setStyles(colorStyleVO2);
							dataStyle1.setData(grandTotal_nonGovDeducNetAmount);
							rowList.add(dataStyle1);
							DataList.add(rowList);

							/*rowList = new ArrayList();
							rowList.add(new PageBreak());
							rowList.add("Data");
							DataList.add(rowList);*/

							rowList = new ArrayList();
							for (int i = 0; i < 17; i++)
							{
								if (i == 1)
								{
									dataStyle1 = new StyledData();
									dataStyle1.setStyles(colorStyleVO2);
									dataStyle1.setColspan(noColForColspan);
									dataStyle1.setData("Grand Total in Words (`):" + ConvertNumbersToWord.convert(Math.round(grandTotal_nonGovDeducNetAmount)) + " Only.");
									dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
									rowList.add(dataStyle1);
								}
								else
								{
									dataStyle1 = new StyledData();
									dataStyle1.setStyles(colorStyleVO2);
									dataStyle1.setData("");
									rowList.add(dataStyle1);
								}
							}
							DataList.add(rowList);
						}
						if(srNo == DistinctEmpIdList.size() || (srNo % (finalpagesize - 1)) == 0)
						{
							columnTotal_netTotal = 0;
							columnTotal_nonGovAmount = new HashMap<Long, Double>();
							columnTotal_nonGovDeducTotal = 0;
							columnTotal_nonGovDeducNetAmount = 0;
						}
						srNo++;
					}
				}

				StyleVO[] URLStyleVO = null;
				URLStyleVO = new StyleVO[2];

				URLStyleVO[0] = new StyleVO();
				URLStyleVO[0].setStyleId(IReportConstants.ROWS_PER_PAGE);
				URLStyleVO[0].setStyleValue(finalpagesize + "");

				URLStyleVO[1] = new StyleVO();
				URLStyleVO[1].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_GROUPBY);
				URLStyleVO[1].setStyleValue("yes");

				report.addReportStyles(URLStyleVO);
				report.setReportColumns(rptCol);
				report.initializeDynamicTreeModel();
				report.initializeTreeModel();
				return DataList;
			}

		}
		catch (Exception e)
		{
			logger.error("Error in ResourceMoniteringDAO" + e.getMessage());
			logger.error("Printing StackTrace");
			logger.error("Error is: "+ e.getMessage());
		}
		return DataList;
	}

	//end by manoj for Festival and foodgrain advance report
	//	by manoj for Non Govt Deduction report

	

	public ReportVO getUserReportVO(ReportVO report, Object criteria) throws ReportException
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		logger.info("***********Inside User Report VO *********************");
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy");
		String yr = fmt1.format(today);
		fmt1 = new SimpleDateFormat("MM");

		Map requestKeys = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map) requestKeys.get("serviceMap");
		Map baseLoginMap = (Map) serviceMap.get("baseLoginMap");
		CmnLocationMst locationVO = (CmnLocationMst) baseLoginMap.get("locationVO");
		long locationId = locationVO.getLocId();
		String month = fmt1.format(today);

		if (month.charAt(0) == '0')
		{

			month = month.substring(1, 2);

		}

		//if(  report.getReportCode().equals("3") || report.getReportCode().equals("2500003")||report.getReportCode().equals("6") || report.getReportCode().equals("2500006")|| report.getReportCode().equals("7") || report.getReportCode().equals("2500007")|| report.getReportCode().equals("8") || report.getReportCode().equals("2500008") || report.getReportCode().equals("10") || report.getReportCode().equals("2500010")|| report.getReportCode().equals("11") || report.getReportCode().equals("2500011")|| report.getReportCode().equals("13") || report.getReportCode().equals("2500013")|| report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("5") || report.getReportCode().equals("2500005") || report.getReportCode().equals("9") || report.getReportCode().equals("2500009")|| report.getReportCode().equals("15") || report.getReportCode().equals("2500015"))
		if (report.getReportCode().equals("3") || report.getReportCode().equals("5000004") || report.getReportCode().equals("6") || report.getReportCode().equals("2500006") || report.getReportCode().equals("7") || report.getReportCode().equals("2500007") || report.getReportCode().equals("8") || report.getReportCode().equals("2500008") || report.getReportCode().equals("10") || report.getReportCode().equals("2500010") || report.getReportCode().equals("11") || report.getReportCode().equals("2500011") || report.getReportCode().equals("13") || report.getReportCode().equals("2500013") || report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("5") || report.getReportCode().equals("2500005") || report.getReportCode().equals("5000009") || report.getReportCode().equals("2500009") || report.getReportCode().equals("15") || report.getReportCode().equals("2500015"))

		{
			report.setParameterValue("Year", yr);
			report.setParameterValue("Month", month);
			report.setParameterValue("Department", locationId + "");
			//added by ravysh
			report.setParameterValue("billTypePara", resourceBundle.getString("OnlyPaybill"));
		}

		if (report.getReportCode().equals("3r") || report.getReportCode().equals("5000004") || report.getReportCode().equals("5") || report.getReportCode().equals("6") || report.getReportCode().equals("7") || report.getReportCode().equals("8") || report.getReportCode().equals("9") || report.getReportCode().equals("10") || report.getReportCode().equals("11") || report.getReportCode().equals("13"))
		{
			report.setParameterValue("Department", locationId + "");
		}

		return report;
	}

	//Added By Kishan
	//getMappedDeducCode method is used for getting deducCode which are mapped with Department
	/*public List getMappedDeducCode(long locId, long deducType, Session session)
	{
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("select hrPayDeducTypeMst.deducCode from HrPayLocComMpg hrpaylocmpg,HrPayCompGrpMst hrpaycompgrpmst,HrPayDeducTypeMst hrPayDeducTypeMst ");
		queryBuffer.append("where hrpaycompgrpmst.cmnLocationMst.locId = " + locId + " ");
		queryBuffer.append("and hrpaylocmpg.cmnLookupMst.lookupId = 2500135 and hrpaylocmpg.isactive = 1 ");
		queryBuffer.append("and hrpaylocmpg.hrpaycompgrpmst.compoGrpId=hrpaycompgrpmst.compoGrpId ");
		queryBuffer.append("and hrpaycompgrpmst.isactive = 1 ");
		queryBuffer.append("and hrPayDeducTypeMst.deducType = " + deducType + " ");
		queryBuffer.append("and hrpaylocmpg.compId = hrPayDeducTypeMst.deducCode ");
		Query query = session.createQuery(queryBuffer.toString());
		return query.list();
	}*/

	//getDistinctDeducCode method is used for geting distinct deduc code from the department and hr_pay_payslip_non_govt table
	public List getDistinctDeducCode(long locId, long deducType, Session session, int month, int year, String BillNo)
	{
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT distinct deduc.DEDUC_CODE ");
		queryBuffer.append("FROM HR_PAY_LOC_COMPONENT_MPG compo, HR_PAY_COMPONENT_GRP_MST grp, HR_PAY_DEDUC_TYPE_MST deduc, ");
		queryBuffer.append("HR_PAY_PAYSLIP_NON_GOVT payslip, HR_PAY_PAYBILL paybill, PAYBILL_HEAD_MPG head ");
		queryBuffer.append("where grp.LOC_ID = " + locId + " ");
		queryBuffer.append("and compo.COMPO_TYPE = 2500135 ");
		queryBuffer.append("and compo.ISACTIVE = 1 ");
		queryBuffer.append("and compo.COMPO_GRP_ID = grp.COMPO_GROUP_ID ");
		queryBuffer.append("and grp.IS_ACTIVE = 1 ");
		queryBuffer.append("and deduc.DEDUC_TYPE = " + deducType + " ");
		queryBuffer.append("and compo.COMPO_ID = deduc.DEDUC_CODE ");
		queryBuffer.append("and head.BILL_NO = " + BillNo + " ");
		queryBuffer.append("and head.PAYBILL_MONTH = " + month + " ");
		queryBuffer.append("and head.PAYBILL_YEAR = " + year + " ");
		queryBuffer.append("and head.APPROVE_FLAG in (0,1) ");
		queryBuffer.append("and paybill.PAYBILL_GRP_ID = head.PAYBILL_ID ");
		queryBuffer.append("and payslip.PAYBILL_ID = paybill.ID ");
		queryBuffer.append("and payslip.NON_GOV_DEDUC_CODE = deduc.DEDUC_CODE ");
		Query query = session.createSQLQuery(queryBuffer.toString());
		
		logger.info("Query for getting Non Gov Deduc Code "+queryBuffer.toString());
		return query.list();
	}

	public List getAquiData(String Designation, String Grade, long locId, int month, int year, String BillNo, long parentLocId, String deducCodes, Session session)
	{
		StringBuffer lsb = new StringBuffer();
		lsb.append("select ");
		lsb.append("distinct eis.emp_mpg_id, ");//0
		lsb.append("emp.emp_lname, ");//1
		lsb.append("bill.NET_TOTAL, ");//2
		if (!deducCodes.equals(""))
		{
			lsb.append("non.NON_GOV_DEDUC_CODE, ");//3
			lsb.append("non.NON_GOV_DEDUC_AMOUNT, ");//4
		}
		else
		{
			lsb.append("0 as deduc_code, ");//3
			lsb.append("0 as deduc_amount, ");//4
		}
		lsb.append("lookup.LOOKUP_NAME, ");//5
		lsb.append("class.DCPS_CLASS_GROUP, ");//6
		lsb.append("mst.DSGN_CODE, ");//7
		lsb.append("emp.emp_lname, ");//8
		lsb.append("eis.SEVARTH_EMP_CD, ");//9
		lsb.append("desig.dsgn_name, ");//10
		lsb.append("emp.emp_fname, ");//11
		lsb.append("emp.emp_mname, ");//12
		if(!deducCodes.equals(""))
		{
		lsb.append("non.NON_GOV_TYPE ");//13
		}else{
			lsb.append("'NA' ");
		}
		lsb.append("from  PAYBILL_HEAD_MPG head inner join HR_PAY_PAYBILL bill on head.PAYBILL_ID = bill.PAYBILL_GRP_ID  ");
		if (!deducCodes.equals(""))
			lsb.append("inner join HR_PAY_PAYSLIP_NON_GOVT non on non.PAYBILL_ID = bill.ID  and non.NON_GOV_DEDUC_CODE in ("+deducCodes+") ");
		lsb.append("inner join hr_eis_emp_mst eis on eis.EMP_ID = bill.EMP_ID ");
		lsb.append("inner join org_emp_mst emp on emp.EMP_ID = eis.EMP_MPG_ID ");
		lsb.append("inner join ORG_POST_DETAILS_RLT rlt on rlt.POST_ID = bill.POST_ID ");
		lsb.append("inner join org_post_mst post on post.POST_ID = rlt.POST_ID and post.LOCATION_CODE = rlt.LOC_ID and post.POST_ID = bill.POST_ID ");
		lsb.append("inner join ORG_DESIGNATION_MST desig on desig.DSGN_ID = rlt.DSGN_ID ");
		lsb.append("inner join ORG_GRADE_MST grade on grade.GRADE_ID = emp.GRADE_ID ");
		lsb.append("inner join RLT_DCPS_BILLGROUP_CLASSGROUP class on class.DCPS_CLASS_GROUP = grade.GRADE_NAME and class.DCPS_BILLGROUP_ID = head.BILL_NO ");
		lsb.append("inner join CMN_LOCATION_MST loc on loc.LOC_ID = rlt.LOC_ID ");
		lsb.append("inner join MST_PAYROLL_DESIGNATION mst on mst.FIELD_DEPT_ID = loc.PARENT_LOC_ID and mst.ORG_DESIGNATION_ID = desig.DSGN_ID "); 
		lsb.append("inner join CMN_LOOKUP_MST lookup on lookup.LOOKUP_ID = post.POST_TYPE_LOOKUP_ID ");
		lsb.append("where ");
		lsb.append("head.BILL_NO = "+BillNo+" ");
		lsb.append("and head.APPROVE_FLAG in (0,1) ");
		lsb.append("and head.PAYBILL_MONTH = "+month+" ");
		lsb.append("and head.PAYBILL_YEAR = "+year+" ");
		
		if (!Designation.equals("") && !Designation.equals("-1"))
			lsb.append("and desig.DSGN_ID = "+Designation+" ");
		if (!Grade.equals("") && !Grade.equals("-1"))
			lsb.append("and grade.grade_Id = " + Grade + " ");
		
		lsb.append("order by "); 
		lsb.append("lookup.LOOKUP_NAME, ");
		lsb.append("class.DCPS_CLASS_GROUP, ");
		lsb.append("mst.DSGN_CODE, ");
		lsb.append("emp.emp_lname, ");
		lsb.append("emp.emp_fname, ");
		lsb.append("emp.emp_mname, ");
		lsb.append("eis.emp_mpg_id ");
		
		
		/*lsb.append("select ");
		lsb.append("emp.emp_lname, ");
		lsb.append("bill.NET_TOTAL, ");
		if (!deducCodes.equals(""))
		{
			lsb.append("non.NON_GOV_DEDUC_CODE, ");
			lsb.append("non.NON_GOV_DEDUC_AMOUNT, ");
		}
		else
		{
			lsb.append("0 as deduc_code, ");
			lsb.append("0 as deduc_amount, ");
		}
		lsb.append("eis.emp_mpg_id, ");//Count Employees
		//		lsb.append("eis.SEVARTH_EMP_CD, "); //For sevarthEmpCode
		//		lsb.append("dsgn.dsgn_name, "); //For Designation Name
		lsb.append("common.POST_TYPE, "); //For POST_TYPE
		lsb.append("rltdcps.DCPS_CLASS_GROUP, "); //For DCPS_CLASS_GROUP
		lsb.append("common.dsgn_code, "); //For dsgn_code
		lsb.append("emp.emp_lname, "); //For emp_lname
		lsb.append("eis.SEVARTH_EMP_CD, "); //For sevarth id
		lsb.append("dsgn.dsgn_name, "); //For dsgn name
		lsb.append("emp.emp_fname, "); //For emp first name
		lsb.append("emp.emp_mname "); //For emp mname
		lsb.append("from ");
		lsb.append("HR_PAY_PAYBILL bill, ");
		if (!deducCodes.equals(""))
			lsb.append("HR_PAY_PAYSLIP_NON_GOVT non, ");
		lsb.append("hr_eis_emp_mst eis, ");
		lsb.append("org_emp_mst emp,ORG_GRADE_MST grade,org_post_details_rlt postrlt, ");
		lsb.append("org_designation_mst dsgn,PAYBILL_HEAD_MPG head, ");
		lsb.append("MST_DCPS_BILL_GROUP dcps,RLT_DCPS_BILLGROUP_CLASSGROUP rltdcps, ");
		lsb.append("COMMMON_POST_DESIG common,HR_EIS_GD_MPG gd,HR_EIS_SGD_MPG sgd ");
		lsb.append("where ");
		lsb.append("eis.emp_mpg_id = emp.emp_id ");
		lsb.append("and bill.EMP_ID = eis.emp_id ");
		lsb.append("and head.PAYBILL_ID = bill.PAYBILL_GRP_ID ");
		if (!deducCodes.equals(""))
			lsb.append("and non.paybill_id = bill.ID ");
		lsb.append("and common.POST_ID = bill.POST_ID ");
		lsb.append("and postrlt.post_id = bill.post_id ");
		lsb.append("and common.DSGN_ID = postrlt.DSGN_ID ");
		lsb.append("and postrlt.dsgn_id = dsgn.dsgn_id ");
		lsb.append("and grade.GRADE_NAME = rltdcps.DCPS_CLASS_GROUP ");
		lsb.append("and gd.SGD_GRADE_ID = grade.GRADE_ID ");
		lsb.append("and gd.SGD_DESIG_ID = common.DSGN_ID ");

		if (!Designation.equals("") && !Designation.equals("-1"))
			lsb.append("and dsgn.dsgn_Id = " + Designation + " ");

		if (!Grade.equals("") && !Grade.equals("-1"))
			lsb.append("and grade.grade_Id = " + Grade + " ");

		lsb.append("and gd.LOC_ID = " + locId + " ");
		lsb.append("and sgd.SGD_GD_ID = gd.GD_MAP_ID ");
		lsb.append("and postrlt.loc_id = " + locId + " ");
		lsb.append("and head.approve_flag in (0,1) ");
		lsb.append("and head.PAYBILL_MONTH = " + month + " ");
		lsb.append("and head.PAYBILL_YEAR = " + year + " ");
		lsb.append("and dcps.BILL_GROUP_ID = " + BillNo + " ");
		lsb.append("and rltdcps.DCPS_BILLGROUP_ID = " + BillNo + " ");
		lsb.append("and common.FIELD_DEPTID = " + parentLocId + " ");
		lsb.append("and dcps.BILL_GROUP_ID = head.bill_no ");
		if (!deducCodes.equals(""))
			lsb.append("and non.NON_GOV_DEDUC_CODE in (" + deducCodes + ") ");
		lsb.append("order by ");
		lsb.append("common.POST_TYPE, ");
		lsb.append("rltdcps.DCPS_CLASS_GROUP, ");
		lsb.append("common.dsgn_code, ");
		lsb.append("emp.emp_lname,emp.emp_fname,emp.emp_mname,eis.emp_mpg_id ");*/
		Query query = session.createSQLQuery(lsb.toString());
		logger.info("Query is***"+lsb.toString());
		return query.list();
	}

	public List getAllNonGovDeducCode(Session session)
	{
		StringBuffer lsb = new StringBuffer();
		lsb.append("SELECT mst.DEDUC_CODE FROM HR_PAY_DEDUC_TYPE_MST mst where mst.DEDUC_TYPE = 300160 ");
		Query query = session.createSQLQuery(lsb.toString());
		return query.list();
	}

	public List getDistinctEmpId(String Designation, String Grade, long locId, int month, int year, String BillNo, long parentLocId, String deducCodes, Session session)
	{
		StringBuffer lsb = new StringBuffer();
		lsb.append("select ");
		lsb.append("distinct eis.emp_mpg_id,");
		lsb.append("emp.emp_lname, "); //For emp lname
		lsb.append("bill.NET_TOTAL, ");
		lsb.append("common.POST_TYPE, "); //For POST_TYPE
		lsb.append("rltdcps.DCPS_CLASS_GROUP, "); //For DCPS_CLASS_GROUP
		lsb.append("common.dsgn_code, "); //For dsgn_code
		lsb.append("emp.emp_lname, "); //For emp_lname
		lsb.append("eis.SEVARTH_EMP_CD, "); //For emp_sevarth Id
		lsb.append("dsgn.dsgn_name, "); //For dsgn name
		lsb.append("emp.emp_fname, "); //For emp first name
		lsb.append("emp.emp_mname "); //For emp mname
		lsb.append("from ");
		lsb.append("HR_PAY_PAYBILL bill, ");
		if (!deducCodes.equals(""))
			lsb.append("HR_PAY_PAYSLIP_NON_GOVT non, ");
		lsb.append("hr_eis_emp_mst eis, ");
		lsb.append("org_emp_mst emp,ORG_GRADE_MST grade,org_post_details_rlt postrlt, ");
		lsb.append("org_designation_mst dsgn,PAYBILL_HEAD_MPG head, ");
		lsb.append("MST_DCPS_BILL_GROUP dcps,RLT_DCPS_BILLGROUP_CLASSGROUP rltdcps, ");
		lsb.append("COMMMON_POST_DESIG common,HR_EIS_GD_MPG gd,HR_EIS_SGD_MPG sgd ");
		lsb.append("where ");
		lsb.append("eis.emp_mpg_id = emp.emp_id ");
		lsb.append("and bill.EMP_ID = eis.emp_id ");
		lsb.append("and head.PAYBILL_ID = bill.PAYBILL_GRP_ID ");
		if (!deducCodes.equals(""))
			lsb.append("and non.paybill_id = bill.ID ");
		lsb.append("and common.POST_ID = bill.POST_ID ");
		lsb.append("and postrlt.post_id = bill.post_id ");
		lsb.append("and common.DSGN_ID = postrlt.DSGN_ID ");
		lsb.append("and postrlt.dsgn_id = dsgn.dsgn_id ");
		lsb.append("and grade.GRADE_NAME = rltdcps.DCPS_CLASS_GROUP ");
		lsb.append("and gd.SGD_GRADE_ID = grade.GRADE_ID ");
		lsb.append("and gd.SGD_DESIG_ID = common.DSGN_ID ");

		if (!Designation.equals("") && !Designation.equals("-1"))
			lsb.append("and dsgn.dsgn_Id = " + Designation + " ");

		if (!Grade.equals("") && !Grade.equals("-1"))
			lsb.append("and grade.grade_Id = " + Grade + " ");

		lsb.append("and gd.LOC_ID = " + locId + " ");
		lsb.append("and sgd.SGD_GD_ID = gd.GD_MAP_ID ");
		lsb.append("and postrlt.loc_id = " + locId + " ");
		lsb.append("and head.approve_flag in (0,1) ");
		lsb.append("and head.PAYBILL_MONTH = " + month + " ");
		lsb.append("and head.PAYBILL_YEAR = " + year + " ");
		lsb.append("and dcps.BILL_GROUP_ID = " + BillNo + " ");
		lsb.append("and rltdcps.DCPS_BILLGROUP_ID = " + BillNo + " ");
		lsb.append("and common.FIELD_DEPTID = " + parentLocId + " ");
		lsb.append("and dcps.BILL_GROUP_ID = head.bill_no ");
		if (!deducCodes.equals(""))
			lsb.append("and non.NON_GOV_DEDUC_CODE in (" + deducCodes + ") ");
		lsb.append("order by ");
		lsb.append("common.POST_TYPE, ");
		lsb.append("rltdcps.DCPS_CLASS_GROUP, ");
		lsb.append("common.dsgn_code, ");
		lsb.append("emp.emp_lname,emp.emp_fname,emp.emp_mname,eis.emp_mpg_id ");
		Query query = session.createSQLQuery(lsb.toString());
		return query.list();
	}
	//Ended By Kishan

}
