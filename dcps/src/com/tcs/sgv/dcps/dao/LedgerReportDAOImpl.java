package com.tcs.sgv.dcps.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class LedgerReportDAOImpl extends GenericDaoHibernateImpl{
	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;
	
	public LedgerReportDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);

		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}
	  public List ddologin(String gStrLocationCode)
	  {
	    StringBuilder lSBQuery = new StringBuilder();
	    List ddoList = null;
	    
	    lSBQuery.append(" SELECT DDO_CODE,DDO_NAME FROM ORG_DDO_MST where LOCATION_CODE=" + gStrLocationCode + " ");
	    
	    Query lQuery = this.ghibSession.createSQLQuery(lSBQuery.toString());
	    
	    this.logger.info("lQuery****** " + lQuery);
	    
	    ddoList = lQuery.list();
	    
	    return ddoList;
	  }
	  
	public List searchEmpsForLedger(String lStrSevarthId, String lStrName) {

		StringBuilder lSBQuery = new StringBuilder();
		List EmployeeList = null;

		lStrSevarthId = lStrSevarthId.toUpperCase();
		lStrName = lStrName.toUpperCase();

		lSBQuery.append(" SELECT EM.ORG_EMP_MST_ID, EM.emp_name,EM.dcps_Id, EM.gender, EM.dob, nvl(DO.off_name,''),nvl(OD.DSGN_NAME,''),EM.sevarth_Id,EM.DDO_CODE,nvl(DO.off_name,''),EM.dcps_emp_id");
		lSBQuery.append(" from mst_dcps_Emp EM ");
		lSBQuery.append(" left join mst_dcps_ddo_office DO on DO.DCPS_DDO_OFFICE_MST_ID = EM.CURR_OFF ");
		lSBQuery.append(" left join org_designation_mst OD on OD.DSGN_ID = EM.DESIGNATION");
		lSBQuery.append(" WHERE ");

		if (lStrSevarthId != null && !"".equals(lStrSevarthId)) {
			lSBQuery.append(" UPPER(EM.SEVARTH_ID) = :sevarthId ");
		}
		if (lStrName != null && !"".equals(lStrName)) {
			if (lStrSevarthId != null && !"".equals(lStrSevarthId)) {
				lSBQuery.append(" AND ");
			}
			lSBQuery.append(" UPPER(EM.emp_name) = :empName ");
		}

		lSBQuery.append(" AND EM.REG_STATUS IN (1,2) ");

		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		if (lStrSevarthId != null && !"".equals(lStrSevarthId)) {
			lQuery.setParameter("sevarthId", lStrSevarthId.trim().toUpperCase());
		}
		if (lStrName != null && !"".equals(lStrName)) {
			lQuery.setParameter("empName", lStrName.trim().toUpperCase());
		}

		EmployeeList = lQuery.list();

		return EmployeeList;
	}
	
	public List getNameForAutoComplete(String searchKey,long locId)
	{
		
		logger.error("Inside getEMPNameForAutoComplete****** ");

		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;
		StringBuilder sb = new StringBuilder();
		//StringBuilder lSBQuery = new StringBuilder();
		Query selectQuery = null;
		Date lDtCurrDate = SessionHelper.getCurDate();

		sb.append("select name,name from MstEmp where UPPER(name) LIKE :searchKey and regStatus in (1,2) ");
		sb.append(" and ( servEndDate is null or servEndDate  >= :currentDate ) ");
		/*lSBQuery.append("SELECT empMst.* FROM ORG_POST_MST postMst , ORG_USERPOST_RLT postRlt, ORG_EMP_MST empMst ");
		lSBQuery.append("where postMst.POST_ID = postRlt.POST_ID ");
		lSBQuery.append("and postMst.LOCATION_CODE = "+locId+" ");
		lSBQuery.append("and empMst.USER_ID = postRlt.USER_ID ");
		lSBQuery.append("and postMst.ACTIVATE_FLAG = 1 ");
		lSBQuery.append("and postRlt.ACTIVATE_FLAG = 1 ");
		lSBQuery.append("and UPPER(CONCAT(CONCAT(CONCAT(CONCAT(emp_fname,' '),emp_mname),' '),emp_lname)) LIKE '%" + searchKey + "%'");
		*/

		selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("searchKey", '%' + searchKey + '%');
		selectQuery.setDate("currentDate", lDtCurrDate);

		

		List resultList = selectQuery.list();

		cmbVO = new ComboValuesVO();

		if (resultList != null && resultList.size() > 0) {
			Iterator it = resultList.iterator();
			while (it.hasNext()) {
				cmbVO = new ComboValuesVO();
				obj = (Object[]) it.next();
				logger.error("Inside getEmpNameForAutoComplete List results are--->"+obj[0].toString());
				cmbVO.setId(obj[0].toString());
				
				cmbVO.setDesc(obj[1].toString());
				
				finalList.add(cmbVO);
			}
		}

		return finalList;
	}
	 public String getGPFAcNoDetails(long UserID){
			
			Session hibSession = getSession();
			
			StringBuilder newQuery = new StringBuilder();


	         String GPFNo="";
			
	         newQuery.append("SELECT concat(concat(PF_SERIES, '/'), GPF_ACC_NO) FROM HR_PAY_GPF_DETAILS where USER_ID ="+UserID);
	         Query query=hibSession.createSQLQuery(newQuery.toString());
	         GPFNo=query.uniqueResult().toString();
			
			return GPFNo;
	}
	
	public List getEmpLastMnthBillData(long empId,String lStrDdoCode,String month,int year)
	{
		logger.error("empId "+empId+" lStrDdoCode "+lStrDdoCode+" month "+month+" year "+year);
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		//
		sb.append(" SELECT pb ,head.voucherNumber,head.voucherDate,bg.dcpsDdoSchemeCode "); 
		sb.append(" FROM PaybillHeadMpg head,HrPayPaybill pb,MstEmp dcps,HrEisEmpMst eis,OrgEmpMst emp,MstDcpsBillGroup bg "); 
		sb.append(" WHERE dcps.orgEmpMstId = emp.empId and emp.empId = eis.orgEmpMst.empId and eis.empId = pb.hrEisEmpMst.empId and pb.paybillGrpId = head.hrPayPaybill "); 
		sb.append(" and head.month in ("+month+") and head.year = :year and head.approveFlag = 1 and dcps.orgEmpMstId = :empId and bg.dcpsDdoBillGroupId = head.billNo.dcpsDdoBillGroupId "); 
		sb.append(" and bg.dcpsDdoCode = :ddoCode ");
		Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		query.setDouble("year", new Double(year));
		query.setLong("empId", new Long(empId));
		query.setString("ddoCode", lStrDdoCode);
		logger.error("Query in getEmpLastMnthBillData is " + query.toString());
		logger.error("sql query for last month bill data is"+sql1query.toString());
		
		return query.list();
	}
	

	  public List getEmpLastMnthBillData1(long empId, String month, int year)
	  {
	    this.logger.error("empId " + empId + "  month " + month + " year " + year);
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    
	    sb.append(" SELECT pb ,head.voucherNumber,head.voucherDate,bg.dcpsDdoSchemeCode ");
	    sb.append(" FROM PaybillHeadMpg head,HrPayPaybill pb,MstEmp dcps,HrEisEmpMst eis,OrgEmpMst emp,MstDcpsBillGroup bg ");
	    sb.append(" WHERE dcps.orgEmpMstId = emp.empId and emp.empId = eis.orgEmpMst.empId and eis.empId = pb.hrEisEmpMst.empId and pb.paybillGrpId = head.hrPayPaybill ");
	    sb.append(" and head.month in (" + month + ") and head.year = :year and head.approveFlag = 1 and dcps.orgEmpMstId = :empId and bg.dcpsDdoBillGroupId = head.billNo.dcpsDdoBillGroupId ");
	    
	    Query query = hibSession.createQuery(sb.toString());
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    query.setDouble("year", new Double(year).doubleValue());
	    query.setLong("empId", new Long(empId).longValue());
	    
	    this.logger.error("Query in getEmpLastMnthBillData is " + query.toString());
	    this.logger.error("sql query for last month bill data is" + sql1query.toString());
	    
	    return query.list();
	  }
	  
	public List getBrokenPeriodfrYr(long empId,String month,long year)
	{
		logger.error("empId "+empId+" month "+month+" year "+year);
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		//
		
		
		
		
		  sb.append("SELECT distinct mbpp.broken_period_id,mde.EMP_NAME,mde.SEVARTH_ID,mbpp.MONTH_ID,mbpp.YEAR_ID,mbpp.FROM_DATE,mbpp.TO_DATE,");
		    sb.append("mbpp.NO_OF_DAYS,mbpp.BASIC_PAY,clm.LOOKUP_NAME,mbpp.REMARKS,mbpp.NET_PAY FROM MST_DCPS_BROKEN_PERIOD_PAY mbpp ");
		    sb.append("inner join hr_eis_emp_mst eis on eis.emp_id=mbpp.EIS_EMP_ID ");
		    sb.append("inner join MST_DCPS_EMP mde on mde.ORG_EMP_MST_ID=eis.EMP_MPG_ID ");
		    sb.append("join CMN_LOOKUP_MST clm on clm.LOOKUP_ID=mbpp.REASON ");
		    sb.append("where mbpp.MONTH_ID in(" + month + ") and mbpp.YEAR_ID =" + year + " and eis.EMP_MPG_ID =" + empId);
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for last month broken data is"+sql1query.toString());
		
		return sql1query.list();
	}
	
	
	public List getEmpCompoFrmBill1 (long payPayBillId,long compoType)
	{
		Session hibSession = getSession();
		String lStrCom = null;
		List payCompColmMpgLst = null;
		StringBuffer sb = new StringBuffer();
		//sb.append(" select ecm.compoId from PaybillEmpCompMpg ecm where ecm.paybillHeadMpg = "+payBillHeadMpgId );
		//sb.append(" and ecm.paybillID = "+payPayBillId+" and ecm.compoType = "+compoType );
		sb.append(" select ecm.compoId from PaybillEmpCompMpg ecm where ecm.paybillID = "+payPayBillId );
		sb.append(" and ecm.compoType = "+compoType );
		Query query = hibSession.createQuery(sb.toString());
		Query sqlquery=hibSession.createSQLQuery(sb.toString());
		logger.error("Query in getEmpCompoFrmBill1 is " + query.toString());
		logger.error("sql query for empcompo-------"+sqlquery.toString());
		List compoLst = query.list();
		if(compoLst != null && !compoLst.isEmpty())
		{
			lStrCom = compoLst.get(0).toString();
			logger.error("lStrCom ::: "+lStrCom);
			StringBuffer hqlQuery = new StringBuffer();
			hqlQuery.append(" SELECT ccm FROM HrPayCompoColumnMpg ccm "); 
			hqlQuery.append(" where ccm.id.compoId in ( ");
			hqlQuery.append(lStrCom);
			hqlQuery.append(" ) and ccm.id.compoType = "+compoType);
			query = hibSession.createQuery(hqlQuery.toString());
		//	logger.error("query in getEmpCompoFrmBill is " + query.toString());
			
			payCompColmMpgLst = new ArrayList();
			payCompColmMpgLst = query.list();
		}
		return payCompColmMpgLst;

	}
	
	public List getEmpDeductionFrmBill1 (long payPayBillId,long compoType)
	{
		Session hibSession = getSession();
		String lStrCom = null;
		List payDedColmMpgLst = null;
		StringBuffer sb = new StringBuffer();
		//sb.append(" select ecm.compoId from PaybillEmpCompMpg ecm where ecm.paybillHeadMpg = "+payBillHeadMpgId );
		//sb.append(" and ecm.paybillID = "+payPayBillId+" and ecm.compoType = "+compoType );
		sb.append(" select ecm.compoId from PaybillEmpCompMpg ecm where ecm.paybillID = "+payPayBillId );
		sb.append(" and ecm.compoType = "+compoType );
		Query query = hibSession.createQuery(sb.toString());
		Query sqlquery=hibSession.createSQLQuery(sb.toString());
		logger.error("Query in getEmpCompoFrmBill1 is " + query.toString());
		logger.error("sql query for empcompo-------"+sqlquery.toString());
		List compoLst = query.list();
		if(compoLst != null && !compoLst.isEmpty())
		{
			lStrCom = compoLst.get(0).toString();
			logger.error("lStrCom ::: "+lStrCom);
			StringBuffer hqlQuery = new StringBuffer();
			hqlQuery.append(" SELECT ccm FROM HrPayCompoColumnMpg ccm "); 
			hqlQuery.append(" where ccm.id.compoId in ( ");
			hqlQuery.append(lStrCom);
			hqlQuery.append(" ) and ccm.id.compoType = "+compoType);
			query = hibSession.createQuery(hqlQuery.toString());
		//	logger.error("query in getEmpCompoFrmBill is " + query.toString());
			
			payDedColmMpgLst = new ArrayList();
			payDedColmMpgLst = query.list();
		}
		return payDedColmMpgLst;

	}
	
	
	public String getJoiningDate(Long empId){
		Session hibSession = getSession();
		String joinDate="";
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT  to_char(START_DATE,'dd/mm/yyyy') FROM HST_DCPS_EMP_DETAILS ");
		sb.append("where DCPS_EMP_ID="+empId+" order by START_DATE desc");
		Query query = hibSession.createSQLQuery(sb.toString());
		logger.error("joining date query " + query.toString());
		List joiningDates= query .list();
		joinDate=joiningDates.get(0).toString().substring(0, 10);
		
		return joinDate;
	}
	
	public List getHrLoanAdvMst(){
		List<HrLoanAdvMst> advMstList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" from HrLoanAdvMst loan");
	    Query query1 = hibSession.createQuery(query.toString());
	    if(query1 != null){
	    	advMstList = query1.list();
	    }
	    return advMstList;
	}
	
	
	public List getEmpDetails(String sevarthId)
	{
		
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT emp.EMP_NAME, to_char(emp.DOB, 'dd/MM/yyyy'),scale.SCALE_DESC,emp.PAY_IN_PAY_BAND,emp.GRADE_PAY,emp.BASIC_PAY,to_char(payfix.NXT_INCR_DATE, 'dd/MM/yyyy'),to_char(rlt.CURR_POST_JOINING_DATE, 'dd/MM/yyyy'),qtr.QUARTER_NAME,to_char(qtr.ALLOCATION_START_DATE, 'dd/MM/yyyy'),to_char(qtr.ALLOCATION_END_DATE, 'dd/MM/yyyy'),emp.ADDRESS_BUILDING||','||emp.ADDRESS_STREET||','||emp.LANDMARK||','||emp.LOCALITY||','||emp.DISTRICT||','||emp.PINCODE FROM mst_dcps_emp emp inner join org_emp_mst mst on mst.EMP_ID = emp.ORG_EMP_MST_ID ");
		sb.append("left	outer join  HR_PAYFIX_MST payfix on payfix.USER_ID = mst.USER_ID ");
		sb.append("inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID = emp.ORG_EMP_MST_ID ");
		sb.append("inner join RLT_DCPS_PAYROLL_EMP rlt on rlt.DCPS_EMP_ID = emp.DCPS_EMP_ID ");
		sb.append("inner join hr_eis_scale_mst scale on scale.scale_id = emp.payscale ");
		sb.append("left outer join HR_EIS_QTR_EMP_MPG qtr on qtr.ALLOCATED_TO = eis.EMP_ID where emp.SEVARTH_ID = '"+sevarthId+"'");
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getEmpDetails broken data is"+sql1query.toString());
		
		return sql1query.list();
	}
	public List getLoanDetails(String sevarthId, long finYrId) {
		// TODO Auto-generated method stub
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();		
		
		sb.append("SELECT distinct loan.LOAN_TYPE_ID,loan.LOAN_PRIN_AMT,loan.LOAN_PRIN_EMI_AMT,loan.VOUCHER_NO,to_char(loan.VOUCHER_DATE,'dd/MM/yyyy'),loan.LOAN_PRIN_INST_NO,adv.LOAN_ADV_NAME,trim(nvl(loan.LOAN_SANC_ORDER_NO,'NA')),to_char(loan.LOAN_SANC_ORDER_DATE,'dd/MM/yyyy') FROM HR_LOAN_EMP_DTLS loan inner join   ");
		sb.append("HR_EIS_EMP_MST eis on loan.EMP_ID  = eis.EMP_ID ");
		sb.append("inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
		sb.append("inner join HR_PAY_PAYBILL pay on pay.EMP_ID = eis.EMP_ID ");
		sb.append("inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
		sb.append("inner join HR_LOAN_ADV_MST adv on adv.LOAN_ADV_ID = loan.LOAN_TYPE_ID ");
		sb.append("where emp.SEVARTH_ID = '"+sevarthId+"' and loan.LOAN_ACTIVATE_FLAG = 1 " );
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getLoanDetails data is"+sql1query.toString());
		
		return sql1query.list();
	}
	public List getLoanDetailsMonthWise(String sevarthId, long finYrIdLower,long finYrIdUpper ) {
		// TODO Auto-generated method stub
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT pay.PAYBILL_MONTH,'67~'|| pay.HBA_HOUSE,'51~'|| pay.HBA_LAND,'54~'|| pay.GPF_ADV_GRP_ABC,  ");
		sb.append("'55~'|| pay.GPF_ADV_GRP_D,'56~'|| pay.MCA_LAND,'57~'|| pay.OTHER_VEH_ADV,'58~'|| pay.COMPUTER_ADV, '59~'|| pay.FESTIVAL_ADVANCE, ");
		sb.append("'60~'|| pay.OTHER_ADV,'61~'|| pay.CO_HSG_SOC,'63~'|| pay.GPF_IAS_LOAN,'108~'||TOTAL_DED ");
		sb.append("FROM mst_dcps_emp emp inner join hr_eis_emp_mst eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
		sb.append("inner join hr_pay_paybill pay on eis.EMP_ID = pay.EMP_ID ");
		sb.append("inner join PAYBILL_HEAD_MPG mpg on pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID ");
		sb.append("where emp.SEVARTH_ID ='"+sevarthId+"' and mpg.APPROVE_FLAG = 1 and mpg.BILL_CATEGORY = 2 and ((pay.PAYBILL_YEAR ="+finYrIdUpper+" and pay.PAYBILL_MONTH<4) or (pay.PAYBILL_YEAR ="+finYrIdLower+" and pay.PAYBILL_MONTH>3)) " );
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getLoanDetails data is"+sql1query.toString());
		
		return sql1query.list();
	}
	

	  
	  public List getLoanDetailsMonthWise1(String sevarthId, long finYrIdLower, long finYrIdUpper)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    
	    sb.append("SELECT pay.PAYBILL_MONTH,'67~'|| pay.HBA_HOUSE,'51~'|| pay.HBA_LAND,'54~'|| pay.GPF_ADV_GRP_ABC,  ");
	    sb.append("'55~'|| pay.GPF_ADV_GRP_D,'56~'|| pay.MCA_LAND,'57~'|| pay.OTHER_VEH_ADV,'58~'|| pay.COMPUTER_ADV, '59~'|| pay.FESTIVAL_ADVANCE, ");
	    sb.append("'60~'|| pay.OTHER_ADV,'61~'|| pay.CO_HSG_SOC,'63~'|| pay.GPF_IAS_LOAN,'108~'||TOTAL_DED ");
	    sb.append("FROM mst_dcps_emp emp inner join hr_eis_emp_mst eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
	    sb.append("inner join hr_pay_paybill pay on eis.EMP_ID = pay.EMP_ID ");
	    sb.append("inner join PAYBILL_HEAD_MPG mpg on pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID ");
	    sb.append("where emp.SEVARTH_ID ='" + sevarthId + "' and mpg.APPROVE_FLAG = 1 and mpg.BILL_CATEGORY = 2 and pay.PAYBILL_YEAR =" + finYrIdLower + " ");
	    
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getLoanDetails data is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getLoanDetailsMonthWise2(String sevarthId, long finYrIdLower, long finYrIdUpper)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    
	    sb.append("SELECT pay.PAYBILL_MONTH,'67~'|| pay.HBA_HOUSE,'51~'|| pay.HBA_LAND,'54~'|| pay.GPF_ADV_GRP_ABC,  ");
	    sb.append("'55~'|| pay.GPF_ADV_GRP_D,'56~'|| pay.MCA_LAND,'57~'|| pay.OTHER_VEH_ADV,'58~'|| pay.COMPUTER_ADV, '59~'|| pay.FESTIVAL_ADVANCE, ");
	    sb.append("'60~'|| pay.OTHER_ADV,'61~'|| pay.CO_HSG_SOC,'63~'|| pay.GPF_IAS_LOAN,'108~'||TOTAL_DED ");
	    sb.append("FROM mst_dcps_emp emp inner join hr_eis_emp_mst eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
	    sb.append("inner join hr_pay_paybill pay on eis.EMP_ID = pay.EMP_ID ");
	    sb.append("inner join PAYBILL_HEAD_MPG mpg on pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID ");
	    sb.append("where emp.SEVARTH_ID ='" + sevarthId + "' and mpg.APPROVE_FLAG = 1 and mpg.BILL_CATEGORY in (3,15)  and pay.PAYBILL_YEAR =" + finYrIdLower + " ");
	    
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getLoanDetails data is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	 
	public String getFinYear(long finYrId) {
		// TODO Auto-generated method stub
		Session hibSession = getSession();
		String finYear="";
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT FIN_YEAR_CODE FROM SGVC_FIN_YEAR_MST where FIN_YEAR_ID ="+finYrId);
		
		Query query = hibSession.createSQLQuery(sb.toString());
		logger.error("joining date query " + query.toString());
		List joiningDates= query .list();
		finYear=joiningDates.get(0).toString();
		
		return finYear;
	}
	 
	public List getGpfDetails(String sevarthId, long finYrId) {
		// TODO Auto-generated method stub
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append("SELECT gpfmst.GPF_ACC_NO,gpfyr.CLOSING_BALANCE,gpfyr.OPENING_BALANCE FROM MST_emp_GPF_ACC gpfmst inner join mst_gpf_yearly gpfyr on gpfmst.GPF_ACC_NO=gpfyr.GPF_ACC_NO ");
		sb.append("where gpfmst.SEVAARTH_ID ='"+sevarthId+"' and gpfyr.FIN_YEAR_ID ="+finYrId );
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getLoanDetails data is"+sql1query.toString());
		
		return sql1query.list();
	}

	
	public List getGISDetails(String sevarthId, long finYrIdLower,long finYrIdUpper ) {
		// TODO Auto-generated method stub
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT distinct to_char(gis.MEMBERSHIP_DATE, 'dd/MM/yyyy'),org.GRADE_NAME,pay.GIS,pay.PAYBILL_MONTH FROM HR_EIS_GIS_DTLS gis inner join HR_EIS_EMP_MST eis on eis.EMP_ID = gis.EMP_ID  ");
		sb.append("inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
		sb.append("inner join ORG_GRADE_MST org on org.GRADE_ID = gis.GIS_GROUP_GRADE_ID ");
		sb.append("inner join HR_PAY_PAYBILL pay on pay.EMP_ID = eis.EMP_ID ");	
		//sb.append("inner join HST_DCPS_EMP_DETAILS hst on emp.DCPS_EMP_ID = hst.DCPS_EMP_ID ");	
		sb.append("inner join PAYBILL_HEAD_MPG mpg on pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID ");
		sb.append("where emp.SEVARTH_ID = '"+sevarthId+"' and org.LANG_ID = 1 and mpg.APPROVE_FLAG =1 and  ((pay.PAYBILL_YEAR ="+finYrIdUpper+" and pay.PAYBILL_MONTH<4) or (pay.PAYBILL_YEAR ="+finYrIdLower+" and pay.PAYBILL_MONTH>3)) order by  pay.PAYBILL_MONTH desc ");
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getLoanDetails data is"+sql1query.toString());
		
		return sql1query.list();
	}
	 

	  public List getGISDetails1(String sevarthId, long finYrIdLower, long finYrIdUpper)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    
	    sb.append("SELECT distinct to_char(gis.MEMBERSHIP_DATE, 'dd/MM/yyyy'),org.GRADE_NAME,pay.GIS,pay.PAYBILL_MONTH FROM HR_EIS_GIS_DTLS gis inner join HR_EIS_EMP_MST eis on eis.EMP_ID = gis.EMP_ID  ");
	    sb.append("inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
	    sb.append("inner join ORG_GRADE_MST org on org.GRADE_ID = gis.GIS_GROUP_GRADE_ID ");
	    sb.append("inner join HR_PAY_PAYBILL pay on pay.EMP_ID = eis.EMP_ID ");
	    
	    sb.append("inner join PAYBILL_HEAD_MPG mpg on pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID ");
	    sb.append("where emp.SEVARTH_ID = '" + sevarthId + "' and org.LANG_ID = 1 and mpg.APPROVE_FLAG =1 and pay.PAYBILL_YEAR =" + finYrIdLower + " ");
	    
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getLoanDetails data is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	public Long getYearlyContribution(String sevarthID,long finYrId) {
		// TODO Auto-generated method stub
		Session hibSession = getSession();
		Long dcpsYearlyCon=0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT dcps.CONTRIB_EMPLOYEE FROM MST_DCPS_CONTRIBUTION_YEARLY dcps inner join MST_DCPS_EMP emp on emp.DCPS_ID = dcps.DCPS_ID where emp.SEVARTH_ID ='"+sevarthID+"' and dcps.YEAR_ID ="+finYrId);
		
		Query query = hibSession.createSQLQuery(sb.toString());
		logger.error("joining date query " + query.toString());
		List joiningDates= query .list();
		if(joiningDates!=null && !joiningDates.isEmpty()){
			if(joiningDates.get(0)!=null){			
			
			dcpsYearlyCon=Long.parseLong(joiningDates.get(0).toString());
			}
		}
		
		return dcpsYearlyCon;
	}
	
	
	
	
	
	public List getPostDetails(String sevarthId) {
		// TODO Auto-generated method stub
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT distinct desg.DESIG_DESC,post.POST_NAME,postmst.POST_TYPE_LOOKUP_ID FROM ORG_USER_MST user inner join ORG_USERPOST_RLT userpost on  user.USER_ID= userpost.USER_ID ");
		sb.append("inner join ORG_POST_DETAILS_RLT post on post.POST_ID = userpost.POST_ID ");
		sb.append("inner join ORG_POST_MST postmst on postmst.POST_ID =  post.POST_ID ");
		sb.append("inner join MST_PAYROLL_DESIGNATION desg on desg.ORG_DESIGNATION_ID = post.DSGN_ID where user.USER_NAME = '"+sevarthId+"' and postmst.ACTIVATE_FLAG = 1 and userpost.ACTIVATE_FLAG = 1 ");		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getLoanDetails data is"+sql1query.toString());
		
		return sql1query.list();
	}
	

	  
	  public List getPostDetails1(String sevarthId)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    
	    sb.append("SELECT distinct desg.DESIG_DESC,post.POST_NAME,postmst.POST_TYPE_LOOKUP_ID FROM mst_dcps_emp emp INNER JOIN ORG_EMP_MST em on emp.ORG_EMP_MST_ID=em.EMP_ID INNER JOIN ORG_USER_MST user on em.USER_ID=user.USER_ID ");
	    sb.append("inner join ORG_USERPOST_RLT userpost on  user.USER_ID= userpost.USER_ID inner join ORG_POST_DETAILS_RLT post on post.POST_ID = userpost.POST_ID inner join ORG_POST_MST postmst on postmst.POST_ID =  post.POST_ID ");
	    sb.append("inner join MST_PAYROLL_DESIGNATION desg on desg.ORG_DESIGNATION_ID = post.DSGN_ID ");
	    sb.append("where emp.SEVARTH_ID = 'DATPJDM8501' and postmst.ACTIVATE_FLAG = 1 and userpost.ACTIVATE_FLAG = 1  ");
	    
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getLoanDetails data is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	public List getTotalDedandGrass(String sevarthId, long finYrIdLower,long finYrIdUpper) {
		// TODO Auto-generated method stub
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT pay.PAYBILL_MONTH,pay.PAYBILL_MONTH ||'~'|| pay.GROSS_AMT,pay.PAYBILL_MONTH ||'~'|| pay.TOTAL_DED,pay.PAYBILL_MONTH ||'~'||(pay.GROSS_AMT-pay.PO),pay.PAYBILL_MONTH ||'~'||mpg.BILL_NO, pay.PAYBILL_MONTH ||'~'||to_char(mpg.CREATED_DATE,'dd/mm/yyyy')  ");
		sb.append("FROM mst_dcps_emp emp inner join hr_eis_emp_mst eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
		sb.append("inner join hr_pay_paybill pay on eis.EMP_ID = pay.EMP_ID ");
		sb.append("inner join PAYBILL_HEAD_MPG mpg on pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID ");
		sb.append("where emp.SEVARTH_ID ='"+sevarthId+"' and mpg.APPROVE_FLAG = 1 and ((pay.PAYBILL_YEAR ="+finYrIdUpper+" and pay.PAYBILL_MONTH<4) or (pay.PAYBILL_YEAR ="+finYrIdLower+" and pay.PAYBILL_MONTH>3)) order by  pay.PAYBILL_MONTH ");		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getLoanDetails data is"+sql1query.toString());
		
		return sql1query.list();
	}
	

	  public List getTotalDedandGrass1(String sevarthId, long finYrIdLower, long finYrIdUpper)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    
	    sb.append("SELECT pay.PAYBILL_MONTH,pay.PAYBILL_MONTH ||'~'|| pay.GROSS_AMT,pay.PAYBILL_MONTH ||'~'|| pay.TOTAL_DED,pay.PAYBILL_MONTH ||'~'||(pay.GROSS_AMT-pay.PO),pay.PAYBILL_MONTH ||'~'||mpg.BILL_NO, pay.PAYBILL_MONTH ||'~'||to_char(mpg.CREATED_DATE,'dd/mm/yyyy')  ");
	    sb.append("FROM mst_dcps_emp emp inner join hr_eis_emp_mst eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
	    sb.append("inner join hr_pay_paybill pay on eis.EMP_ID = pay.EMP_ID ");
	    sb.append("inner join PAYBILL_HEAD_MPG mpg on pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID ");
	    sb.append("where emp.SEVARTH_ID ='" + sevarthId + "' and mpg.APPROVE_FLAG = 1 and pay.PAYBILL_YEAR =" + finYrIdLower + " ");
	    
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getLoanDetails data is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	public List getBrokenPeriodData(String sevarthId, long finYrId,String component) {
		// TODO Auto-generated method stub
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		if(component.equals("Basic"))
			sb.append("SELECT mbpp.BASIC_PAY  ||'~'|| month(mbpp.FROM_DATE) ");
			if(component.equals("netPay"))
				sb.append("SELECT mbpp.NET_PAY ||'~'|| month(mbpp.FROM_DATE) ");
			if(component.equals("noOfDays"))
				sb.append("SELECT mbpp.NO_OF_DAYS  ||'~'|| month(mbpp.FROM_DATE) ");
			if(component.equals("reason"))
				sb.append("SELECT clm.LOOKUP_NAME ||'~'|| month(mbpp.FROM_DATE) ");
			if(component.equals("remarks"))
				sb.append("SELECT mbpp.REMARKS  ||'~'|| month(mbpp.FROM_DATE) ");
			if(component.equals("fromDate"))
				sb.append("SELECT to_char(mbpp.FROM_DATE,'dd/MM/yyyy') ||'~'|| month(mbpp.FROM_DATE) ");
			if(component.equals("toDate"))
				sb.append("SELECT to_char(mbpp.TO_DATE,'dd/MM/yyyy') ||'~'|| month(mbpp.FROM_DATE) ");
			if(component.equals("voucherNo"))
				sb.append("SELECT head.VOUCHER_NO  ||'~'|| month(mbpp.FROM_DATE) ");
			if(component.equals("voucherDate"))
				sb.append("SELECT  to_char(head.VOUCHER_DATE,'dd/MM/yyyy')  ||'~'|| month(mbpp.FROM_DATE) ");
			if(component.equals("billNo"))
				sb.append("SELECT head.BILL_NO  ||'~'|| month(mbpp.FROM_DATE) ");
			if(component.equals("BillDate"))
				sb.append("SELECT head.CREATED_DATE  ||'~'|| month(mbpp.FROM_DATE) ");
		sb.append(" FROM PAYBILL_HEAD_MPG   head ");
		sb.append(" inner join HR_PAY_PAYBILL hpp on hpp.PAYBILL_GRP_ID=head.PAYBILL_ID ");
		sb.append(" inner join MST_DCPS_BROKEN_PERIOD_PAY mbpp on mbpp.EIS_EMP_ID= hpp.EMP_ID ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.emp_id=hpp.emp_id ");
		sb.append(" inner join MST_DCPS_EMP mde on mde.ORG_EMP_MST_ID=eis.emp_mpg_id");
		sb.append(" join CMN_LOOKUP_MST clm on clm.LOOKUP_ID=mbpp.REASON ");		
		sb.append(" and mbpp.YEAR_ID="+finYrId+" and head.approve_flag  = 1 and mde.SEVARTH_ID='"+sevarthId+"' ");
		sb.append(" and  head.BILL_CATEGORY=3 ");
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getbrokenperioddata data is"+sql1query.toString());
		
		return sql1query.list();
	}
	public List getBrokenPeriodAllowData(Long brokenPeriodId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT patm.ALLOW_NAME as allowName,dbpa.RLT_BROKEN_PERIOD_ID as BrokenId FROM RLT_DCPS_BROKEN_PERIOD_ALLOW dbpa ");
		sb.append("join HR_PAY_ALLOW_TYPE_MST patm on dbpa.ALLOW_CODE=patm.ALLOW_CODE where dbpa.RLT_BROKEN_PERIOD_ID in ("+brokenPeriodId+")");
		
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getbrokenperioddallowata data is"+sql1query.toString());
		
		return sql1query.list();
		
	}
	public List getBrokenPeriodDeducData(Long brokenPeriodId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT pdtm.DEDUC_NAME as deductName,dbpd.RLT_BROKEN_PERIOD_ID as BrokenId FROM RLT_DCPS_BROKEN_PERIOD_DEDUC dbpd ");
		sb.append("join HR_PAY_DEDUC_TYPE_MST pdtm on dbpd.DEDUC_CODE=pdtm.DEDUC_CODE where dbpd.RLT_BROKEN_PERIOD_ID in ("+brokenPeriodId+")");
		
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getbrokenperioddallowata data is"+sql1query.toString());
		
		return sql1query.list();
		
		
	}
	public List getBroenPeriodId(String sevarthId, long finYrId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT mbpp.BROKEN_PERIOD_ID	 FROM PAYBILL_HEAD_MPG   head ");
		sb.append(" inner join HR_PAY_PAYBILL hpp on hpp.PAYBILL_GRP_ID=head.PAYBILL_ID ");
		sb.append(" inner join MST_DCPS_BROKEN_PERIOD_PAY mbpp on mbpp.EIS_EMP_ID= hpp.EMP_ID and mbpp.MONTH_ID=head.PAYBILL_MONTH  ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.emp_id=hpp.emp_id ");
		sb.append(" inner join MST_DCPS_EMP mde on mde.ORG_EMP_MST_ID=eis.emp_mpg_id ");		
		sb.append(" and mbpp.YEAR_ID="+finYrId+" and head.approve_flag  = 1  and mde.SEVARTH_ID='"+sevarthId+"' ");
		sb.append(" and  head.BILL_CATEGORY=3 ");
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getbrokenperiodID data is"+sql1query.toString());
		
		return sql1query.list();
	}
	public List getBrokenAllow( String brokenPeriodId,Long allowId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT allow.allow_name,bpa.ALLOW_VALUE || '~' || month(bpp.FROM_DATE) FROM RLT_DCPS_BROKEN_PERIOD_ALLOW bpa inner join MST_DCPS_BROKEN_PERIOD_PAY bpp on bpp.BROKEN_PERIOD_ID = bpa.RLT_BROKEN_PERIOD_ID ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = bpp.EIS_EMP_ID ");
		sb.append(" inner join MST_DCPS_EMP emp on emp.ORG_EMP_MST_ID= eis.EMP_MPG_ID ");
		sb.append(" inner join HR_PAY_ALLOW_TYPE_MST allow on allow.ALLOW_CODE=bpa.ALLOW_CODE ");
		sb.append(" where bpa.RLT_BROKEN_PERIOD_ID in ( "+brokenPeriodId+") and  bpa.ALLOW_CODE = "+allowId);
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getbrokenperioddallowata data is"+sql1query.toString());
		
		return sql1query.list();
		
	}
	public List getMaxallowList(String sevarthId, Long finYrId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT distinct rlt.ALLOW_CODE FROM RLT_DCPS_BROKEN_PERIOD_ALLOW rlt inner join MST_DCPS_BROKEN_PERIOD_PAY  pay on rlt.RLT_BROKEN_PERIOD_ID=pay.BROKEN_PERIOD_ID  ");
		sb.append(" inner join HR_PAY_ALLOW_TYPE_MST allow on allow.ALLOW_CODE=rlt.ALLOW_CODE ");
		sb.append(" inner join hr_eis_emp_mst eis on eis.EMP_ID=pay.EIS_EMP_ID ");
		sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID=eis.EMP_MPG_ID ");
		sb.append(" where emp.SEVARTH_ID = '"+sevarthId+"' and pay.YEAR_ID  = "+finYrId);
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getbrokenperioddallowata data is"+sql1query.toString());
		
		return sql1query.list();
		
	}
	public List getMaxDeducList(String sevarthId, Long finYrId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT distinct rlt.DEDUC_CODE FROM RLT_DCPS_BROKEN_PERIOD_DEDUC rlt inner join MST_DCPS_BROKEN_PERIOD_PAY  pay on rlt.RLT_BROKEN_PERIOD_ID=pay.BROKEN_PERIOD_ID  ");
		sb.append(" inner join HR_PAY_DEDUC_TYPE_MST dedc on dedc.DEDUC_CODE=rlt.DEDUC_CODE ");
		sb.append(" inner join hr_eis_emp_mst eis on eis.EMP_ID=pay.EIS_EMP_ID ");
		sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID=eis.EMP_MPG_ID ");
		sb.append(" where emp.SEVARTH_ID = '"+sevarthId+"' and pay.YEAR_ID  = "+finYrId);
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getbrokenperioddallowata data is"+sql1query.toString());
		
		return sql1query.list();
		
	}
	public List getBrokenDeducValue(String strBrokenIdList, Long deducId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT deduc.DEDUC_NAME,bpa.DEDUC_VALUE || '~' || month(bpp.FROM_DATE) FROM RLT_DCPS_BROKEN_PERIOD_DEDUC bpa inner join MST_DCPS_BROKEN_PERIOD_PAY bpp on bpp.BROKEN_PERIOD_ID = bpa.RLT_BROKEN_PERIOD_ID  ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = bpp.EIS_EMP_ID  ");
		sb.append(" inner join MST_DCPS_EMP emp on emp.ORG_EMP_MST_ID= eis.EMP_MPG_ID ");
		sb.append(" inner join HR_PAY_DEDUC_TYPE_MST deduc on deduc.DEDUC_CODE=bpa.DEDUC_CODE ");
		sb.append(" where bpa.RLT_BROKEN_PERIOD_ID in ( "+strBrokenIdList+") and  bpa.DEDUC_CODE = "+deducId);
		
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBrokenDeducValue data is"+sql1query.toString());
		
		return sql1query.list();
		
	}
	public List getTotalAllowDeducValue(String strBrokenIdList, String component) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		if(component.equals("totalAllowance")){
		sb.append("SELECT cast(sum(bpa.ALLOW_VALUE )as varchar(10)) ||'~'|| cast(month(mbpp.FROM_DATE )as varchar(2)) ");
		sb.append(" FROM RLT_DCPS_BROKEN_PERIOD_ALLOW bpa inner join MST_DCPS_BROKEN_PERIOD_PAY mbpp on bpa.RLT_BROKEN_PERIOD_ID=mbpp.BROKEN_PERIOD_ID  ");	
		
		sb.append(" where bpa.RLT_BROKEN_PERIOD_ID in ( "+strBrokenIdList+") group by month(mbpp.FROM_DATE )");
		}
		else if(component.equals("totalDeduction")){
			sb.append("SELECT cast(sum(bpa.DEDUC_VALUE )as varchar(10)) ||'~'|| cast(month(mbpp.FROM_DATE )as varchar(2)) ");
			sb.append(" FROM RLT_DCPS_BROKEN_PERIOD_DEDUC bpa inner join MST_DCPS_BROKEN_PERIOD_PAY mbpp on bpa.RLT_BROKEN_PERIOD_ID=mbpp.BROKEN_PERIOD_ID   ");
			sb.append(" where bpa.RLT_BROKEN_PERIOD_ID in ( "+strBrokenIdList+") group by month(mbpp.FROM_DATE )");
			
		}
		//Query query = hibSession.createQuery(sb.toString());
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBrokenDeducValue data is"+sql1query.toString());
		
		return sql1query.list();
	}
	public List getallowListIdNormal(String sevarthId, Long finYearLower,Long finYearUpper,Long compoId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT paycomp.compo_id FROM HR_PAY_PAYBILL paybill ");
		sb.append("  INNER JOIN HR_EIS_EMP_MST hremp on paybill.EMP_ID=hremp.EMP_ID ");
		sb.append(" inner JOIN MST_DCPS_EMP mstemp on hremp.EMP_MPG_ID=mstemp.ORG_EMP_MST_ID ");
		sb.append(" INNER JOIN  PAYBILL_HEAD_MPG payhead on payhead.PAYBILL_ID=paybill.PAYBILL_GRP_ID ");
		sb.append(" LEFT OUTER JOIN HR_PAY_PAYBILL_EMP_COMP_MPG paycomp on paycomp.HR_PAY_PAYBILL_ID = paybill.ID ");
		sb.append("  INNER JOIN MST_DCPS_BILL_GROUP billgrp on billgrp.BILL_GROUP_ID=payhead.BILL_NO ");
		sb.append(" INNER JOIN CMN_LOOKUP_MST lookup on paycomp.COMPO_TYPE=lookup.LOOKUP_ID ");
		sb.append("  where hremp.SEVARTH_EMP_CD='"+sevarthId+"'  ");
		sb.append("  and payhead.APPROVE_FLAG =1  and ((paybill.PAYBILL_YEAR ="+finYearUpper+" and paybill.PAYBILL_MONTH<4) or (paybill.PAYBILL_YEAR ="+finYearLower+" and paybill.PAYBILL_MONTH>3)) and paycomp.COMPO_TYPE= "+compoId);
		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBrokenDeducValue data is"+sql1query.toString());
		
		return sql1query.list();
		
	}

	  public List getallowListIdNormal1(String sevarthId, Long finYearLower, Long finYearUpper, Long compoId)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append("SELECT paycomp.compo_id FROM HR_PAY_PAYBILL paybill ");
	    sb.append("  INNER JOIN HR_EIS_EMP_MST hremp on paybill.EMP_ID=hremp.EMP_ID ");
	    sb.append(" inner JOIN MST_DCPS_EMP mstemp on hremp.EMP_MPG_ID=mstemp.ORG_EMP_MST_ID ");
	    sb.append(" INNER JOIN  PAYBILL_HEAD_MPG payhead on payhead.PAYBILL_ID=paybill.PAYBILL_GRP_ID ");
	    sb.append(" LEFT OUTER JOIN HR_PAY_PAYBILL_EMP_COMP_MPG paycomp on paycomp.HR_PAY_PAYBILL_ID = paybill.ID ");
	    sb.append("  INNER JOIN MST_DCPS_BILL_GROUP billgrp on billgrp.BILL_GROUP_ID=payhead.BILL_NO ");
	    sb.append(" INNER JOIN CMN_LOOKUP_MST lookup on paycomp.COMPO_TYPE=lookup.LOOKUP_ID ");
	    sb.append("  where hremp.SEVARTH_EMP_CD='" + sevarthId + "'  ");
	    sb.append("  and payhead.APPROVE_FLAG =1  and paybill.PAYBILL_YEAR =" + finYearLower + " and paycomp.COMPO_TYPE= " + compoId);
	    
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getBrokenDeducValue data is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	public List getallowNames(String strAllowValues,Long compoType) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT PAYBILL_COLUMN FROM HR_PAY_COMPO_COLUMN_MPG where COMPO_ID in ("+strAllowValues+") and COMPO_TYPE= "+compoType);
		
		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBrokenDeducValue data is"+sql1query.toString());
		
		return sql1query.list();
		
	}
	public List getAllowValues(String sevarthId, Long finYearLower,
			Long finYearUpper, String allowName) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT "+allowName+" ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
		sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
		sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='"+sevarthId+"' and   ");
		sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY = 2 and  ((pay.PAYBILL_YEAR =" + finYearUpper + " and pay.PAYBILL_MONTH<4) or (pay.PAYBILL_YEAR = " + finYearLower + " and pay.PAYBILL_MONTH>3)) ");		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getAllowValues is"+sql1query.toString());
		
		return sql1query.list();
	}

	  public List getAllowValues1(String sevarthId, Long finYearLower, Long finYearUpper, String allowName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT " + allowName + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY = 2 and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getAllowValues2(String sevarthId, Long finYearLower, Long finYearUpper, String allowName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT " + allowName + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (3,15) and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getGradepayValues1(String sevarthId, Long finYearLower, Long finYearUpper, String grade)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT " + grade + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY = 2 and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getGradepayValues2(String sevarthId, Long finYearLower, Long finYearUpper, String grade)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT " + grade + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (3,15) and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getpayinpaybandValues1(String sevarthId, Long finYearLower, Long finYearUpper, String grade)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT " + grade + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY = 2 and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getpayinpaybandValues2(String sevarthId, Long finYearLower, Long finYearUpper, String grade)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT " + grade + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (3,15) and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getGroassValues1(String sevarthId, Long finYearLower, Long finYearUpper, String allowName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT " + allowName + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY = 2 and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getGroassValues2(String sevarthId, Long finYearLower, Long finYearUpper, String allowName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT " + allowName + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (3,15) and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getDDO(String sevarthId, Long finYearLower, Long finYearUpper, String allowName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT bill." + allowName + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append(" inner join MST_DCPS_BILL_GROUP bill on mpg.BILL_NO = bill.BILL_GROUP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY = 2  and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getDDO1(String sevarthId, Long finYearLower, Long finYearUpper, String allowName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT bill." + allowName + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append(" inner join MST_DCPS_BILL_GROUP bill on mpg.BILL_NO = bill.BILL_GROUP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (3,15)  and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getBillType(String sevarthId, Long finYearLower, Long finYearUpper, String allowName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT bill." + allowName + " ||'~'|| pay.PAYBILL_MONTH from hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append(" inner join MST_DCPS_BILL_GROUP bill on mpg.BILL_NO = bill.BILL_GROUP_ID ");
	    sb.append("  inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp.SEVARTH_ID='" + sevarthId + "' and   ");
	    sb.append("  mpg.APPROVE_FLAG=1 and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	public List getOtherComponentValues(String sevarthId, Long finYearLower,Long finYearUpper, String compoName) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		if(compoName.equals("TotalAllowance"))
			sb.append("SELECT pay.PAYBILL_MONTH ||'~'||(pay.GROSS_AMT-pay.PO) ");
		if(compoName.equals("TotalDeduction"))
			sb.append("SELECT pay.PAYBILL_MONTH ||'~'|| pay.TOTAL_DED ");
		if(compoName.equals("NetPay"))
			sb.append("SELECT pay.PAYBILL_MONTH ||'~'||pay.net_total ");
		if(compoName.equals("BillNo"))
			sb.append("SELECT pay.PAYBILL_MONTH ||'~'||mpg.BILL_NO ");
		if(compoName.equals("BillDate"))
			sb.append(" SELECT pay.PAYBILL_MONTH ||'~'||to_char(mpg.CREATED_DATE,'dd/mm/yyyy') ");
		if(compoName.equals("VoucherNo"))
			sb.append(" SELECT pay.PAYBILL_MONTH ||'~'|| mpg.VOUCHER_NO ");
		if(compoName.equals("VoucherDate"))
		sb.append(" SELECT pay.PAYBILL_MONTH ||'~'|| to_char(mpg.VOUCHER_DATE,'dd/mm/yyyy') ");		 
		sb.append(" FROM mst_dcps_emp emp inner join hr_eis_emp_mst eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
		sb.append(" inner join hr_pay_paybill pay on eis.EMP_ID = pay.EMP_ID  ");
		sb.append(" inner join PAYBILL_HEAD_MPG mpg on pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID  ");
		sb.append(" where emp.SEVARTH_ID ='"+sevarthId+"' and mpg.APPROVE_FLAG = 1 and mpg.BILL_CATEGORY = 2 and ((pay.PAYBILL_YEAR ="+finYearUpper+" and pay.PAYBILL_MONTH<4) or (pay.PAYBILL_YEAR ="+finYearLower+" and pay.PAYBILL_MONTH>3)) order by  pay.PAYBILL_MONTH ");
		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getOtherComponentValues is"+sql1query.toString());
		
		return sql1query.list();
		
	}
	

	  
	  public List getOtherComponentValues1(String sevarthId, Long finYearLower, Long finYearUpper, String compoName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    if (compoName.equals("TotalAllowance")) {
	      sb.append("SELECT pay.PAYBILL_MONTH ||'~'||(pay.GROSS_AMT-pay.PO) ");
	    }
	    if (compoName.equals("TotalDeduction")) {
	      sb.append("SELECT pay.PAYBILL_MONTH ||'~'|| pay.TOTAL_DED ");
	    }
	    if (compoName.equals("NetPay")) {
	      sb.append("SELECT pay.PAYBILL_MONTH ||'~'||pay.net_total ");
	    }
	    if (compoName.equals("BillNo")) {
	      sb.append("SELECT pay.PAYBILL_MONTH ||'~'||mpg.BILL_NO ");
	    }
	    if (compoName.equals("BillDate")) {
	      sb.append(" SELECT pay.PAYBILL_MONTH ||'~'||to_char(mpg.CREATED_DATE,'dd/mm/yyyy') ");
	    }
	    if (compoName.equals("VoucherNo")) {
	      sb.append(" SELECT pay.PAYBILL_MONTH ||'~'|| mpg.VOUCHER_NO ");
	    }
	    if (compoName.equals("VoucherDate")) {
	      sb.append(" SELECT pay.PAYBILL_MONTH ||'~'|| to_char(mpg.VOUCHER_DATE,'dd/mm/yyyy') ");
	    }
	    sb.append(" FROM mst_dcps_emp emp inner join hr_eis_emp_mst eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
	    sb.append(" inner join hr_pay_paybill pay on eis.EMP_ID = pay.EMP_ID  ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID  ");
	    sb.append(" inner join MST_DCPS_BILL_GROUP billg on mpg.BILL_NO = billg.BILL_GROUP_ID ");
	    sb.append(" where emp.SEVARTH_ID ='" + sevarthId + "' and mpg.APPROVE_FLAG = 1 and mpg.BILL_CATEGORY = 2 and pay.PAYBILL_YEAR =" + finYearLower + " ");
	    
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getOtherComponentValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getOtherComponentValues2(String sevarthId, Long finYearLower, Long finYearUpper, String compoName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    if (compoName.equals("TotalAllowance")) {
	      sb.append("SELECT pay.PAYBILL_MONTH ||'~'||(pay.GROSS_AMT-pay.PO) ");
	    }
	    if (compoName.equals("TotalDeduction")) {
	      sb.append("SELECT pay.PAYBILL_MONTH ||'~'|| pay.TOTAL_DED ");
	    }
	    if (compoName.equals("NetPay")) {
	      sb.append("SELECT pay.PAYBILL_MONTH ||'~'||pay.net_total ");
	    }
	    if (compoName.equals("BillNo")) {
	      sb.append("SELECT pay.PAYBILL_MONTH ||'~'||mpg.BILL_NO ");
	    }
	    if (compoName.equals("BillDate")) {
	      sb.append(" SELECT pay.PAYBILL_MONTH ||'~'||to_char(mpg.CREATED_DATE,'dd/mm/yyyy') ");
	    }
	    if (compoName.equals("VoucherNo")) {
	      sb.append(" SELECT pay.PAYBILL_MONTH ||'~'|| mpg.VOUCHER_NO ");
	    }
	    if (compoName.equals("VoucherDate")) {
	      sb.append(" SELECT pay.PAYBILL_MONTH ||'~'|| to_char(mpg.VOUCHER_DATE,'dd/mm/yyyy') ");
	    }
	    sb.append(" FROM mst_dcps_emp emp inner join hr_eis_emp_mst eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
	    sb.append(" inner join hr_pay_paybill pay on eis.EMP_ID = pay.EMP_ID  ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID  ");
	    sb.append(" inner join MST_DCPS_BILL_GROUP billg on mpg.BILL_NO = billg.BILL_GROUP_ID ");
	    sb.append(" where emp.SEVARTH_ID ='" + sevarthId + "' and mpg.APPROVE_FLAG = 1 and mpg.BILL_CATEGORY in (3,15) and pay.PAYBILL_YEAR =" + finYearLower + " ");
	    
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getOtherComponentValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  
	public List getInstallRecValues(String sevarthId, Long finYearLower,	Long finYearUpper, String allowName) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT distinct "+allowName+" || '~'|| loan.RECOVERED_INST || '~' || loan.TOTAL_INST || '~' || mpg.PAYBILL_MONTH FROM HR_PAY_PAYBILL_LOAN_DTLS loan inner join PAYBILL_HEAD_MPG mpg on loan.PAYBILL_ID = mpg.PAYBILL_ID ");
		sb.append(" inner join HR_LOAN_EMP_DTLS emploan on emploan.EMP_LOAN_ID=loan.EMP_LOAN_ID ");
		sb.append(" inner join HR_PAY_COMPO_COLUMN_MPG compo on compo.COMPO_Id=loan.LOAN_TYPE_ID ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = emploan.EMP_ID  ");
		sb.append(" inner join HR_PAY_PAYBILL pay on pay.EMP_ID = eis.EMP_ID and  pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID ");
		sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID=eis.EMP_MPG_ID where emp.SEVARTH_ID = '"+sevarthId+"' ");
		sb.append(" and mpg.APPROVE_FLAG = 1 and mpg.BILL_CATEGORY=2 and compo.PAYBILL_COLUMN='"+allowName+"' and ((mpg.PAYBILL_YEAR ="+finYearUpper+" and mpg.PAYBILL_MONTH<4) or (mpg.PAYBILL_YEAR ="+finYearLower+" and mpg.PAYBILL_MONTH>3)) ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getAllowValues is"+sql1query.toString());
		
		return sql1query.list();
	}

	  public List getInstallRecValues1(String sevarthId, Long finYearLower, Long finYearUpper, String allowName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append("SELECT distinct " + allowName + " || '~'|| loan.RECOVERED_INST || '~' || loan.TOTAL_INST || '~' || mpg.PAYBILL_MONTH FROM HR_PAY_PAYBILL_LOAN_DTLS loan inner join PAYBILL_HEAD_MPG mpg on loan.PAYBILL_ID = mpg.PAYBILL_ID ");
	    sb.append(" inner join HR_LOAN_EMP_DTLS emploan on emploan.EMP_LOAN_ID=loan.EMP_LOAN_ID ");
	    sb.append(" inner join HR_PAY_COMPO_COLUMN_MPG compo on compo.COMPO_Id=loan.LOAN_TYPE_ID ");
	    sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = emploan.EMP_ID  ");
	    sb.append(" inner join HR_PAY_PAYBILL pay on pay.EMP_ID = eis.EMP_ID and  pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID ");
	    sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID=eis.EMP_MPG_ID where emp.SEVARTH_ID = '" + sevarthId + "' ");
	    sb.append(" and mpg.APPROVE_FLAG = 1 and mpg.BILL_CATEGORY=2 and compo.PAYBILL_COLUMN='" + allowName + "' and mpg.PAYBILL_YEAR =" + finYearLower + "  ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getInstallRecValues2(String sevarthId, Long finYearLower, Long finYearUpper, String allowName)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append("SELECT distinct " + allowName + " || '~'|| loan.RECOVERED_INST || '~' || loan.TOTAL_INST || '~' || mpg.PAYBILL_MONTH FROM HR_PAY_PAYBILL_LOAN_DTLS loan inner join PAYBILL_HEAD_MPG mpg on loan.PAYBILL_ID = mpg.PAYBILL_ID ");
	    sb.append(" inner join HR_LOAN_EMP_DTLS emploan on emploan.EMP_LOAN_ID=loan.EMP_LOAN_ID ");
	    sb.append(" inner join HR_PAY_COMPO_COLUMN_MPG compo on compo.COMPO_Id=loan.LOAN_TYPE_ID ");
	    sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = emploan.EMP_ID  ");
	    sb.append(" inner join HR_PAY_PAYBILL pay on pay.EMP_ID = eis.EMP_ID and  pay.PAYBILL_GRP_ID = mpg.PAYBILL_ID ");
	    sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID=eis.EMP_MPG_ID where emp.SEVARTH_ID = '" + sevarthId + "' ");
	    sb.append(" and mpg.APPROVE_FLAG = 1 and mpg.BILL_CATEGORY=2 and compo.PAYBILL_COLUMN='" + allowName + "' and mpg.PAYBILL_YEAR =" + finYearLower + "  ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getAllowValues is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  
	public List getLpcIdDate(String sevarthId, Long finYearLower,Long finYearUpper) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" select hst.HST_DCPS_ID,to_char(hst.END_DATE, 'dd/MM/yyyy'),mpg.PAYBILL_MONTH from mst_dcps_emp emp inner join HST_DCPS_EMP_DETAILS hst on emp.DCPS_EMP_ID = hst.DCPS_EMP_ID ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID = emp.ORG_EMP_MST_ID ");
		sb.append(" inner join HR_PAY_PAYBILL pay on pay.EMP_ID = eis.EMP_ID ");
		sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
		sb.append(" where emp.SEVARTH_ID =  '"+sevarthId+"' and mpg.APPROVE_FLAG =1 and mpg.BILL_CATEGORY = 2 and  ((pay.PAYBILL_YEAR = "+finYearUpper+" and pay.PAYBILL_MONTH<4) or (pay.PAYBILL_YEAR = "+finYearLower+" and pay.PAYBILL_MONTH>3)) order by  pay.PAYBILL_MONTH desc ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getLpcIdDate is"+sql1query.toString());
		
		return sql1query.list();
		
	}

	  public List getLpcIdDate1(String sevarthId, Long finYearLower, Long finYearUpper)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" select hst.HST_DCPS_ID,to_char(hst.END_DATE, 'dd/MM/yyyy'),mpg.PAYBILL_MONTH from mst_dcps_emp emp inner join HST_DCPS_EMP_DETAILS hst on emp.DCPS_EMP_ID = hst.DCPS_EMP_ID ");
	    sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID = emp.ORG_EMP_MST_ID ");
	    sb.append(" inner join HR_PAY_PAYBILL pay on pay.EMP_ID = eis.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append(" where emp.SEVARTH_ID =  '" + sevarthId + "' and mpg.APPROVE_FLAG =1  and mpg.BILL_CATEGORY = 2 and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getLpcIdDate is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  public List getLpcIdDate2(String sevarthId, Long finYearLower, Long finYearUpper)
	  {
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(" select hst.HST_DCPS_ID,to_char(hst.END_DATE, 'dd/MM/yyyy'),mpg.PAYBILL_MONTH from mst_dcps_emp emp inner join HST_DCPS_EMP_DETAILS hst on emp.DCPS_EMP_ID = hst.DCPS_EMP_ID ");
	    sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID = emp.ORG_EMP_MST_ID ");
	    sb.append(" inner join HR_PAY_PAYBILL pay on pay.EMP_ID = eis.EMP_ID ");
	    sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID ");
	    sb.append(" where emp.SEVARTH_ID =  '" + sevarthId + "' and mpg.APPROVE_FLAG =1 and mpg.BILL_CATEGORY in (3,15) and pay.PAYBILL_YEAR = " + finYearLower + " ");
	    Query sql1query = hibSession.createSQLQuery(sb.toString());
	    
	    this.logger.error("sql query for getLpcIdDate is" + sql1query.toString());
	    
	    return sql1query.list();
	  }
	  
	  
	public List getFinyear() {
		String query = "select finYearCode,finYearCode from SgvcFinYearMst where finYearCode between '2007' and '2018' order by finYearCode ASC";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
		List lLstResult = selectQuery.list();
		ComboValuesVO lObjComboValuesVO = null;

		if (lLstResult != null && lLstResult.size() != 0) {
			lLstReturnList = new ArrayList<Object>();
			Object obj[];
			for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
				obj = (Object[]) lLstResult.get(liCtr);
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId(obj[0].toString());
				lObjComboValuesVO.setDesc(obj[1].toString());
				lLstReturnList.add(lObjComboValuesVO);
			}
		} else {
			lLstReturnList = new ArrayList<Object>();
			lObjComboValuesVO = new ComboValuesVO();
			lObjComboValuesVO.setId("-1");
			lObjComboValuesVO.setDesc("--Select--");
			lLstReturnList.add(lObjComboValuesVO);
		}
	    this.logger.info("getFinyear in legder report:" + lLstResult.size());
		return lLstReturnList;
	}
	public List getBilldetailsList(Long yearId, Long monthId) {
		// TODO Auto-generated method stub
		return null;
	}
	public List getBilldetailsList(Long yearId, Long monthId, long locationId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		/*sb.append(" SELECT distinct head.bill_no,head.AUTH_NO,head.BILL_GROSS_AMT,head.BILL_NET_AMOUNT,pay.PAY_BY_DATE FROM PAYBILL_HEAD_MPG head inner join HR_PAY_CMP_RECORD_MST pay on head.AUTH_NO = trim(pay.AUTH_NO)  ");
		sb.append(" where head.PAYBILL_MONTH = "+monthId+" and head.PAYBILL_YEAR = "+yearId+" and head.CMP_FILE_FLAG is not null and head.APPROVE_FLAG in (1,5) and head.loc_id = "+locationId+" ");
		*/
		sb.append("SELECT  head.bill_no,head.AUTH_NO,head.BILL_GROSS_AMT,head.BILL_NET_AMOUNT,pay.PAY_BY_DATE FROM PAYBILL_HEAD_MPG head  ");
		sb.append(" inner join HR_PAY_CMP_RECORD_MST pay on head.AUTH_NO = trim(pay.AUTH_NO)   where head.PAYBILL_YEAR = "+yearId+" ");
		sb.append(" and head.PAYBILL_MONTH = "+monthId+" and head.CMP_FILE_FLAG is not null and head.APPROVE_FLAG in (1 ,5 ) and head.loc_id = "+locationId+"  ");
		sb.append(" and head.auth_no is not null group by head.bill_no,head.AUTH_NO,head.BILL_GROSS_AMT,head.BILL_NET_AMOUNT,pay.PAY_BY_DATE ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBilldetailsList is"+sql1query.toString());
		
		return sql1query.list();
		
	}
	public List getNPSEmpMpgList(long eisEmpId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM HR_EIS_EMP_COMPONENT_GRP_MST mst inner join HR_EIS_EMP_COMPONENT_MPG emp on mst.EMP_COMPO_GRP_ID = emp.COMPO_GROUP_ID  ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = mst.EMP_ID ");
		sb.append(" inner join mst_dcps_emp dcps  on dcps.ORG_EMP_MST_ID = eis.EMP_MPG_ID and dcps.REG_STATUS = 1  ");
		sb.append(" where mst.EMP_ID = "+eisEmpId+" and mst.IS_ACTIVE = 1  ");
		sb.append(" and emp.IS_ACTIVE = 1 and emp.COMPO_ID in (555,666)  ");
		//sb.append(" and emp.IS_ACTIVE = 1 and (emp.COMPO_ID = 555 and emp.COMPO_TYPE= 2500134) and (emp.COMPO_TYPE =2500135 and  emp.COMPO_ID =666) "); commented for payslip issue for ded adjust and gross adjust
		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBilldetailsList is"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public String getEmpNameFromEmpIdForNPS(long eisEmpId) {
		String empName="";
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT distinct dcps.emp_name || '(' || dcps.SEVARTH_ID || ')' FROM MST_DCPS_EMP dcps ");
		queryBuffer.append("inner join HR_EIS_EMP_MST eis on dcps.ORG_EMP_MST_ID=eis.EMP_MPG_ID ");
		queryBuffer.append("where eis.emp_id="+eisEmpId+" ");
		queryBuffer.append("and dcps.reg_status = 1 ");
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query is :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
		empName = query.uniqueResult().toString();
		}
		return empName;
	}
	public Long getDcpsEmp(long eisEmpId) {
		logger.error("hii i m in finding checkEmpCount");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT emp.REG_STATUS FROM mst_dcps_emp emp inner join HR_EIS_EMP_MST eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp_id = "+eisEmpId+" ");
		Query query = session.createSQLQuery(sb.toString());
		logger.error("query is *************"+query.toString());
		String status = query.uniqueResult().toString();
		Long regStatus = Long.valueOf(Long.parseLong(status));
		logger.error("regStatus" + regStatus);
		return regStatus.longValue();
	}
	
	//added by siddharth for getting bank name & account no
	public List getBankNameandAccountNo(long eisEmpId) {
		//Long userPostTypeLookUpId = null;
		List  list = null;
		try {
			logger.error("hii i m in finding...");
			Session session = getSession();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT emp.BANK_NAME,emp.BANK_ACNT_NO,emp.sevarth_id FROM mst_dcps_emp emp inner join HR_EIS_EMP_MST eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where emp_id = "+eisEmpId+" ");
	        logger.error("Query to get  : "+sb.toString());
	        Query query = session.createSQLQuery(sb.toString());
	        if(query != null)
	        {
	        	list = query.list();
	        }
	        logger.error("list size is..." + list.size());
	        
	        //userPostTypeLookUpId =Long.parseLong(query.uniqueResult().toString());
	        //logger.error("value is**** " + userPostTypeLookUpId);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//end
	
	//added by siddharth for get count of BankNameandAccountNo
	
	public int getCountOfBankNameandAccountNo(String bank_name, String bank_account_no) {
		
		int count = 0;
		try
		 {
	            Session hibSession = getSession();
	            StringBuilder strQuery =new StringBuilder(); 
	            strQuery.append(" SELECT count(1) FROM mst_dcps_emp where BANK_NAME = '"+bank_name+"' and BANK_ACNT_NO = '"+bank_account_no+"' and (ddo_code <> '1111222222' or ddo_code is null) ");
	            
	            logger.error("Query for count of bank_name and bank_account_no combo: "+strQuery.toString());
	            
	            Query query = hibSession.createSQLQuery(strQuery.toString());
	            count=Integer.parseInt(query.uniqueResult().toString());
	     
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	       return count;
	}
	
	//end
	
	//added by siddharth for get EmpName and DdoCode
	
	public List getEmpNameandDdoCode(String bank_name, String bank_account_no) {
		//Long userPostTypeLookUpId = null;
		List  list = null;
		try {
			logger.error("hii i m in finding checkEmpCount");
			Session session = getSession();
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT EMP_NAME, nvl(ddo_code,'commonpool'),sevarth_id FROM mst_dcps_emp where BANK_NAME = '"+bank_name+"' and BANK_ACNT_NO = '"+bank_account_no+"' and (ddo_code <> '1111222222' or ddo_code is null)   ");
	        logger.error("Query to get  : "+sb.toString());
	        Query query = session.createSQLQuery(sb.toString());
	        if(query != null)
	        {
	        	list = query.list();
	        }
	        logger.error("list size is..." + list.size());
	        
	        //userPostTypeLookUpId =Long.parseLong(query.uniqueResult().toString());
	        //logger.error("value is**** " + userPostTypeLookUpId);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//end
	
	
	//added by siddharth for getting PF series & account no
	public List getPFSeriesandAccountNo(long eisEmpId) {
		//Long userPostTypeLookUpId = null;
		List  list = null;
		try {
			logger.error("hii i m in finding...");
			Session session = getSession();
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT gpf.PF_SERIES,gpf.GPF_ACC_NO,emp.sevarth_id,emp.REG_STATUS FROM mst_dcps_emp emp inner join HR_EIS_EMP_MST eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
			sb.append(" inner join ORG_EMP_MST mst on mst.EMP_ID = emp.ORG_EMP_MST_ID inner join HR_PAY_GPF_DETAILS gpf on gpf.USER_ID = mst.USER_ID where eis.emp_id = "+eisEmpId+" ");
			logger.error("Query to get  : "+sb.toString());
	        Query query = session.createSQLQuery(sb.toString());
	        if(query != null)
	        {
	        	list = query.list();
	        }
	        logger.error("list size is..." + list.size());
	        
	        //userPostTypeLookUpId =Long.parseLong(query.uniqueResult().toString());
	        //logger.error("value is**** " + userPostTypeLookUpId);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//end
	
	//added by siddharth for get count of PFSeriesandAccountNo
	
	public int getCountOfPFSeriesandAccountNo(String pf_series, String gpf_account_no, String reg_status) {
		
		int count = 0;
		try
		 {
	            Session hibSession = getSession();
	            StringBuilder strQuery =new StringBuilder(); 
	            strQuery.append(" SELECT count(1) FROM HR_PAY_GPF_DETAILS gpf ");
	            strQuery.append(" inner join ORG_EMP_MST mst on mst.USER_ID = gpf.USER_ID inner join MST_DCPS_EMP emp on emp.ORG_EMP_MST_ID = mst.EMP_ID ");
	            strQuery.append(" where gpf.PF_SERIES = '"+pf_series+"' and gpf.GPF_ACC_NO = '"+gpf_account_no+"' and emp.REG_STATUS = '"+reg_status+"' and (emp.ddo_code <> '1111222222' or emp.ddo_code is null) and gpf.PF_SERIES <> 'NA' ");
	            
	            logger.error("Query for count of pf_series and gpf_account_no and reg_status ::triple combo: "+strQuery.toString());
	            
	            Query query = hibSession.createSQLQuery(strQuery.toString());
	            count=Integer.parseInt(query.uniqueResult().toString());
	     
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	       return count;
	}
	
	//end
	
	//added by siddharth for get EmpName and DdoCode for GPF validation
	
	public List getEmpNameandDdoCodeforGpf(String pf_series, String gpf_account_no) {
		//Long userPostTypeLookUpId = null;
		List  list = null;
		try {
			logger.error("hii i m in finding checkEmpCount");
			Session session = getSession();
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT mst.EMP_NAME, nvl(mst.ddo_code,'commonpool'), mst.sevarth_id FROM mst_dcps_emp mst inner join org_emp_mst emp on mst.org_emp_mst_id =  emp.emp_id ");
			sb.append(" inner join HR_PAY_GPF_DETAILS gpf on gpf.user_id = emp.user_id  where gpf.PF_SERIES = '"+pf_series+"' and gpf.GPF_ACC_NO = '"+gpf_account_no+"' and (mst.ddo_code <> '1111222222' or mst.ddo_code is null) and gpf.PF_SERIES <> 'NA'  ");
			
			logger.error("Query to get  : "+sb.toString());
	        Query query = session.createSQLQuery(sb.toString());
	        if(query != null)
	        {
	        	list = query.list();
	        }
	        logger.error("list size is..." + list.size());
	        
	        //userPostTypeLookUpId =Long.parseLong(query.uniqueResult().toString());
	        //logger.error("value is**** " + userPostTypeLookUpId);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//end
	//Get GPF Employee Name
	public List getEmpNmandDdoCodeforGpf(String empId, String ddoCode) {
		//Long userPostTypeLookUpId = null;
		List  list = null;
		try {
			logger.error("hii i m in finding checkEmpCount GPF");
			Session session = getSession();
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT mst.EMP_NAME, nvl(mst.ddo_code,'commonpool'), mst.sevarth_id FROM mst_dcps_emp mst inner join org_emp_mst emp on mst.org_emp_mst_id =  emp.emp_id ");
			sb.append(" inner join HR_PAY_GPF_DETAILS gpf on gpf.user_id = emp.user_id  where emp.EMP_ID in ("+empId+") and mst.DDO_CODE ='"+ddoCode+"' and gpf.PF_SERIES <> 'NA'  ");
			
			logger.error("Query to get  : "+sb.toString());
	        Query query = session.createSQLQuery(sb.toString());
	        if(query != null)
	        {
	        	list = query.list();
	        }
	        logger.error("list size is..." + list.size());
	        
	        //userPostTypeLookUpId =Long.parseLong(query.uniqueResult().toString());
	        //logger.error("value is**** " + userPostTypeLookUpId);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List getGisApplicableForReport(long empId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
	     sb.append(" SELECT dcps.GIS_APPLICABLE,grade.GRADE_NAME FROM mst_dcps_emp emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID = emp.ORG_EMP_MST_ID ");
	     sb.append(" inner join RLT_DCPS_PAYROLL_EMP dcps on dcps.DCPS_EMP_ID = emp.DCPS_EMP_ID ");
	     sb.append(" inner join HR_EIS_GIS_DTLS gis on gis.EMP_ID = eis.EMP_ID ");
	     sb.append(" inner join ORG_GRADE_MST grade on grade.GRADE_ID = gis.GIS_GROUP_GRADE_ID ");
	     sb.append(" where eis.EMP_ID = "+empId+" ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBilldetailsList is"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public Long getGISArr(long locId, long paybillGroupid, int month, int year,long gisApplicable, long empId) {
		logger.error("hii getGISArr");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT sum(pay.GPF_IPS_ARR_MR) FROM hr_pay_paybill pay inner join HR_EIS_EMP_MST eis on pay.EMP_ID = eis.EMP_ID ");
		sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
		sb.append(" inner join RLT_DCPS_PAYROLL_EMP dcps on dcps.DCPS_EMP_ID = emp.DCPS_EMP_ID where  pay.PAYBILL_GRP_ID = "+paybillGroupid+" and pay.PAYBILL_MONTH = "+month+" and pay.PAYBILL_YEAR = "+year+" and  dcps.GIS_APPLICABLE = "+gisApplicable+"  ");
		sb.append(" and eis.EMP_ID = "+empId+" ");
		Query query = session.createSQLQuery(sb.toString());
		logger.error("query is getGISArr*************"+query.toString());
		String status = query.uniqueResult().toString();
		Long regStatus = Long.valueOf(Long.parseLong(status));
		logger.error("regStatus" + regStatus);
		return regStatus.longValue();
	}
	public List getAdminList() {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
	     sb.append(" SELECT loc_id FROM CMN_LOCATION_MST  where DEPARTMENT_ID = 100001 ");	    
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getAdminList is"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public int getcuntOFTemp(Long adminDept) {
		logger.error("hii getcuntOFTemp");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT count(1) FROM HR_PAY_VOUCHER_DTLS_TEMP temp inner join PAYBILL_HEAD_MPG head on temp.AUTHORIZATION_NO = head.AUTH_NO ");
		sb.append(" inner join org_ddo_mst org on org.LOCATION_CODE = head.LOC_ID where org.DEPT_LOC_CODE = "+adminDept+" and month(temp.SCROLLING_DATE) = 9 and year(temp.SCROLLING_DATE) = 2015 ");
		
		Query query = session.createSQLQuery(sb.toString());
		logger.error("query is getcuntOFTemp*************"+query.toString());
		String status = query.uniqueResult().toString();
		int getcuntOFTemp = Integer.parseInt(status);
		logger.error("regStatus" + getcuntOFTemp);
		return getcuntOFTemp;
	}
	public int countOfLockedBills(Long adminDept) {
		logger.error("hii getcuntOFTemp");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT count(1) FROM PAYBILL_HEAD_MPG head inner join org_ddo_mst ddo on ddo.LOCATION_CODE = head.LOC_ID ");
		sb.append(" inner join CMN_LOCATION_MST cmn on cmn.LOC_ID = ddo.DEPT_LOC_CODE ");
		sb.append(" inner join HR_PAY_VOUCHER_DTLS_TEMP temp on temp.AUTHORIZATION_NO = head.AUTH_NO ");
		sb.append(" where cmn.LOC_ID = "+adminDept+" and head.PAYBILL_MONTH = 9 and paybill_year = 2015 and approve_flag in (1) ");
		
		Query query = session.createSQLQuery(sb.toString());
		logger.error("query is getcuntOFTemp*************"+query.toString());
		String status = query.uniqueResult().toString();
		int getcuntOFLockedBills = Integer.parseInt(status);
		logger.error("getcuntOFLockedBills" + getcuntOFLockedBills);
		return getcuntOFLockedBills;
	}
	public List getGPFAccNo(long eisEmpId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT gpf.PF_SERIES,gpf.GPF_ACC_NO FROM HR_PAY_GPF_DETAILS gpf inner join org_emp_mst emp on gpf.USER_ID = emp.USER_ID ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID = emp.EMP_ID  ");
	     sb.append(" where eis.EMP_ID = "+eisEmpId+"  and (gpf.PF_SERIES <> 'NA' or gpf.PF_SERIES is null) ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBilldetailsList is"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}

	public String getEmpNameFromEmpIdForGPFAccSeriesChk(long eisEmpId) {
		String empName="";
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT distinct dcps.emp_name || '(' || dcps.SEVARTH_ID || ')' FROM MST_DCPS_EMP dcps ");
		queryBuffer.append("inner join HR_EIS_EMP_MST eis on dcps.ORG_EMP_MST_ID=eis.EMP_MPG_ID ");
		queryBuffer.append("where eis.emp_id="+eisEmpId+" ");
		queryBuffer.append("and dcps.reg_status = 2 ");
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query is :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
		empName = query.uniqueResult().toString();
		}
		return empName;
	}
	public List getGPFDetailsEMp(String empCode) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT emp.EMP_NAME,emp.SEVARTH_ID,emp.DDO_CODE,emp.DOJ,emp.EMP_SERVEND_DT,gpf.GPF_ACC_NO,gpf.PF_SERIES FROM mst_dcps_emp emp   ");
		sb.append("inner join org_emp_mst org on org.EMP_ID = emp.ORG_EMP_MST_ID ");
		sb.append(" inner join HR_PAY_GPF_DETAILS gpf on gpf.USER_ID = org.user_id ");
		sb.append(" where emp.SEVARTH_ID = '"+empCode+"'  and emp.REG_STATUS =2 ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBilldetailsList is"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public List getUserIdDcpsEmpId(String empSevarthId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT org.USER_ID,emp.DCPS_EMP_ID FROM mst_dcps_emp emp  ");
		sb.append(" inner join org_emp_mst org on org.EMP_ID = emp.ORG_EMP_MST_ID  ");
		sb.append(" where emp.SEVARTH_ID = '"+empSevarthId+"'  and emp.REG_STATUS = 2  ");	
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBilldetailsList is"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public void updateEmpGPFDetails(Long userId, Long dcpsEmpId, String remarks,String empSevarthId) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		
		str.append("update HR_PAY_GPF_DETAILS set GPF_ACC_NO = null,PF_SERIES = null,UPDATED_DATE = sysdate,REMARKS = '"+remarks+"' where USER_ID = "+userId+" ");
		Query query1 = hibSession.createSQLQuery(str.toString());
		query1.executeUpdate();
		
		StringBuffer str1 = new StringBuffer();
		str1.append("update RLT_DCPS_PAYROLL_EMP set PF_SERIES = null, PF_SERIES_DESC = null, PF_ACNO = null,UPDATED_DATE = sysdate where DCPS_EMP_ID = "+dcpsEmpId+" ");
		logger.error("updateEmpGPFDetails------"+str1.toString());
		Query query2 = hibSession.createSQLQuery(str1.toString());
		query2.executeUpdate();
		
		//Added by Pooja
		List DupliGPFEmpList = getGPFDuplEmpList(empSevarthId);
		logger.error("DupliGPFEmpList------"+DupliGPFEmpList.size());
		if(DupliGPFEmpList != null && !DupliGPFEmpList.isEmpty()){
			logger.error("in if------"+DupliGPFEmpList.size());
			StringBuffer str2 = new StringBuffer();
			str2.append("update MST_DCPS_EMP_DUPLI_GPF set PF_SERIES = null, GPF_ACC_NO = null where SEVARTH_ID = '"+empSevarthId+"'");
			logger.error("updateEmpGPFDetails------"+str2.toString());
			Query query3 = hibSession.createSQLQuery(str2.toString());
			query3.executeUpdate();
		}
		
	}
	//Added by Pooja
	public void updateGPFDuplEmpDetails(String empSevarthId,String pfAcctNo,String PfSeries) {
	Session hibSession =getSession();
	StringBuffer str2 = new StringBuffer();
	str2.append("update MST_DCPS_EMP_DUPLI_GPF set PF_SERIES = '"+PfSeries+"', GPF_ACC_NO ='"+pfAcctNo+"' where SEVARTH_ID = '"+empSevarthId+"'");
	logger.error("updateEmpGPFDetails------"+str2.toString());
	Query query3 = hibSession.createSQLQuery(str2.toString());
	query3.executeUpdate();
	
	}
	//Added by Pooja
	public List getGPFDuplEmpList(String empSevarthId) {
		Session hibSession =getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT * FROM MST_DCPS_EMP_DUPLI_GPF emp ");
		sb.append(" where emp.SEVARTH_ID = '"+empSevarthId+"'");	
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		logger.error("sql query for getGPFDuplEmpList is"+sql1query.toString());

		return sql1query.list();
	}
	public boolean checkBillType(String billNo) {

		String billType = "";
		Session hibSession = getSession();

		StringBuffer strQuery = new StringBuffer();
		
			strQuery.append(" SELECT TYPE_OF_BILL_GROUP FROM MST_DCPS_BILL_GROUP where BILL_GROUP_ID = " + billNo);

		logger.error("Query in isBillsDefined is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		//billType = query.uniqueResult().toString();
		//logger.error("Paybill data is " + billType);
		//logger.error("Size is " + billType);
		if (query.uniqueResult()!=null)
			return false;
		else
			return true;
	}
	public String getDdoCode(long locId) {
		String ddoCode="";
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT ddo_code FROM org_ddo_mst where LOCATION_CODE =" + locId);	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query is :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
			ddoCode = query.uniqueResult().toString();
		}
		return ddoCode;
	}
	public List getbillGroupList(String ddoCode) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT BILL_GROUP_ID,DESCRIPTION FROM MST_DCPS_BILL_GROUP where ddo_code = '"+ddoCode+"' and TYPE_OF_BILL_GROUP is null and (BILL_DELETED <> 'Y' or BILL_DELETED is  null) ");
		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBilldetailsList is"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public int updateBillgroup(Long billNo, Long billType) {
		Session hibSession =getSession();
	StringBuffer str = new StringBuffer();	
	if(billType == 2)
	str.append("update MST_DCPS_BILL_GROUP set TYPE_OF_BILL_GROUP = 'N',UPDATED_DATE = sysdate where BILL_GROUP_ID = "+billNo+"");
	else 
		str.append("update MST_DCPS_BILL_GROUP set TYPE_OF_BILL_GROUP = 'S',UPDATED_DATE = sysdate where BILL_GROUP_ID = "+billNo+"");	
	Query query1 = hibSession.createSQLQuery(str.toString());
	query1.executeUpdate();
	return 0;
	}
	public int updateAutoLockStatus(long paybillId, int autoLockStatus) {
		// TODO Auto-generated method stub
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		
		
		if(autoLockStatus==7 || autoLockStatus==3){
			StringBuffer str1 = new StringBuffer();	
			str1.append("SELECT count(1) FROM HR_PAY_PAYBILL where paybill_grp_id= "+paybillId+"  and emp_id is not null  ");	
			Query query = hibSession.createSQLQuery(str1.toString());
			logger.error("query is getcuntOFTemp*************"+query.toString());
			String count1 = query.uniqueResult().toString();
			int hrpaypaybillCount = Integer.parseInt(count1);
			///
			StringBuffer str2 = new StringBuffer();	
			str2.append(" SELECT count(1) FROM HR_PAY_PAYSLIP where PAYBILL_ID in (SELECT id FROM HR_PAY_PAYBILL where paybill_grp_id= "+paybillId+" and emp_id is not null) ");	
			Query query2 = hibSession.createSQLQuery(str2.toString());
			logger.error("query is getcuntOFTemp*************"+query2.toString());
			String count2 = query2.uniqueResult().toString();
			int hrpaypaypayslipCount = Integer.parseInt(count2);
			if(hrpaypaybillCount== hrpaypaypayslipCount)//code fix for autolock status 7 entries in payslip but updating woring status as 7
				str.append("update PAYBILL_HEAD_MPG set AUTOLOCKSTATUS = 0 where PAYBILL_ID = "+paybillId+"  ");
			else
				str.append("update PAYBILL_HEAD_MPG set AUTOLOCKSTATUS = "+autoLockStatus+" where PAYBILL_ID = "+paybillId+"  ");
		}
		else
		str.append("update PAYBILL_HEAD_MPG set AUTOLOCKSTATUS = "+autoLockStatus+" where PAYBILL_ID = "+paybillId+"  ");	
		
		Query query1 = hibSession.createSQLQuery(str.toString());
		logger.error("query1 ----"+query1);
		query1.executeUpdate();
		try {
			hibSession.connection().commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public List getbillDetails(String authNo) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT PAYBILL_MONTH,PAYBILL_YEAR,BILL_NO,APPROVE_FLAG FROM PAYBILL_HEAD_MPG  where AUTH_NO = '"+authNo+"'  ");
		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBilldetailsList is"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public void updateAutoReverseIssue(String authNo) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();		
		str.append("update PAYBILL_HEAD_MPG set AUTOLOCKSTATUS = 13  where AUTH_NO = "+authNo+" and approve_flag in (3,4) ");	
		
		Query query1 = hibSession.createSQLQuery(str.toString());
		logger.error("query1 ----"+query1);
		query1.executeUpdate();
		try {
			hibSession.connection().commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public int checkAutolockStatus(String authNo) {
		logger.error("hii getcuntOFTemp");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT AUTOLOCKSTATUS FROM PAYBILL_HEAD_MPG where AUTH_NO = '"+authNo+"' ");		
		Query query = session.createSQLQuery(sb.toString());
		logger.error("query is getcuntOFTemp*************"+query.toString());
		String status = query.uniqueResult().toString();
		int autoLockStatus = Integer.parseInt(status);
		logger.error("getcuntOFLockedBills" + autoLockStatus);
		return autoLockStatus;
	}
	public int getDuplicatePayBillId(long paybillId) {
		// TODO Auto-generated method stub
		
		logger.error("hii getDuplicatePayslipId");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from HR_PAY_PAYSLIP where paybill_id in (SELECT paybill_id FROM HR_PAY_PAYSLIP where PAYBILL_ID in (SELECT id FROM HR_PAY_PAYBILL where PAYBILL_GRP_ID="+paybillId+")) ");		
		Query query = session.createSQLQuery(sb.toString());
		logger.error("query is getcuntOFTemp*************"+query.toString());
		String status = query.uniqueResult().toString();
		int duplicatePaybillId = Integer.parseInt(status);
		logger.error("duplicatePaybillId" + duplicatePaybillId);
		return duplicatePaybillId;
	}
	public int getPayslipIDCount(long pkSeqPayslipId, long paySlipId) {
		logger.error("hii getDuplicatePayslipId");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT count(1) FROM hr_pay_payslip where PAYSLIP_ID between "+pkSeqPayslipId+" and "+paySlipId+" ");		
		Query query = session.createSQLQuery(sb.toString());
		logger.error("query is getcuntOFTemp*************"+query.toString());
		String status = query.uniqueResult().toString();
		int duplicatePayslipIDCount = Integer.parseInt(status);
		logger.error("duplicatePayslipIDCount" + duplicatePayslipIDCount);
		return duplicatePayslipIDCount;
	}
	public String getBillCreationDate(String billNo, String month, String year) {
		String ddoCode="";
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT to_char(CREATED_DATE,'dd/mm/yyyy') FROM PAYBILL_HEAD_MPG where BILL_NO = '"+billNo+"'and PAYBILL_MONTH = "+month+" and PAYBILL_YEAR= "+year+" and APPROVE_FLAG in (0,1,5)");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query is :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
			ddoCode = query.uniqueResult().toString();
		}
		return ddoCode;
	}
	public List getDcpsPranList(long locId, int i) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		if(i==0){
			sb.append(" SELECT COUNT(1) FROM mst_dcps_emp emp inner join org_ddo_mst ddo on ddo.DDO_CODE = emp.DDO_CODE ");
					sb.append(" where ddo.LOCATION_CODE = "+locId+" and emp.doj< '2015-04-01' and emp.emp_servend_dt >sysdate and emp.AC_DCPS_MAINTAINED_BY in (700174,700240,700241,700242) and emp.pran_no is null and emp.REG_STATUS = 1 and emp.PAY_COMMISSION not in ('700337') ");

		}else if(i==1)
		{
			sb.append(" SELECT  emp.emp_name||' ('||emp.sevarth_id||')'  FROM mst_dcps_emp emp inner join org_ddo_mst ddo on ddo.DDO_CODE = emp.DDO_CODE ");
					sb.append(" where ddo.LOCATION_CODE = "+locId+" and emp.doj< '2015-04-01' and emp.emp_servend_dt >sysdate and emp.AC_DCPS_MAINTAINED_BY in (700174,700240,700241,700242) and emp.pran_no is null and emp.REG_STATUS = 1 and emp.PAY_COMMISSION not in ('700337') ");

		}
		
		/*sb.append(" SELECT count(1) FROM mst_dcps_emp emp  ");
		sb.append(" inner join org_emp_mst org on org.emp_id=emp.org_emp_mst_id  ");
		sb.append(" inner join hr_eis_emp_mst eis on eis.emp_mpg_id=org.emp_id  ");
		sb.append(" where emp.ddo_code=(SELECT ddo_code FROM mst_dcps_emp where ORG_EMP_MST_ID='"+eisEmpId+"') ");
		sb.append(" and (emp.AC_DCPS_MAINTAINED_BY in (700174,700240,700241,700242) and emp.pran_no is null) ");
		*/
		
	/*	sb.append(" SELECT emp.pran_no FROM mst_dcps_emp emp ");
		sb.append(" inner join org_emp_mst org on org.emp_id=emp.org_emp_mst_id ");
		sb.append(" inner join hr_eis_emp_mst eis on eis.emp_mpg_id=org.emp_id ");
		sb.append(" where eis.emp_id='"+eisEmpId+"'");
		sb.append(" and  1=(CASE  WHEN (emp.AC_DCPS_MAINTAINED_BY in (700174,700240,700241,700242) and emp.pran_no is null) THEN '0' ELSE '1' END) ");
*/
	
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getDcpsPranList akshay:"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public List getPLIComponentList(long empid) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT MONTHLY_PREMIUM,MONTHLY_PREMIUM_SEC,SERVICE_TAX,PRI_EDU_CESS,SEC_EDU_CESS,KRISH_KAL_CESS,SWACHH_BHARAT_CESS,PLI_POLICY_NO,EMP_DEDUC_AMOUNT FROM HR_PAY_DEDUCTION_DTLS where emp_id = " + empid + " and EMP_DEDUC_ID = 45 ");	
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getBilldetailsList is"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public Long getFinYearId(String year) {
		Long finId=0l;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT FIN_YEAR_ID FROM SGVC_FIN_YEAR_MST where FIN_YEAR_CODE = "+year+"");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query is :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
			finId = Long.parseLong(query.uniqueResult().toString());
		}
		return finId;
	}
	//added by sunitha for multi recover install 23-08-2016
	public List getEmpMultiRecovery(long emp_loan_id, int monthGiven,
			int yearGiven) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("SELECT * FROM HR_LOAN_MULTIPLE_INST_DTLS where EMP_LOAN_ID = "+emp_loan_id+" and PAYBILL_MONTH = "+monthGiven+" and PAYBILL_YEAR = "+yearGiven+" and LOAN_APPROVE_FLAG = 0");	
	
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getEmpMultiRecovery is"+sql1query.toString());
		logger.error("getEmpMultiRecovery sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public void updateEmpMulitiApproveFlag(long emp_loan_id, int monthGiven,
			int yearGiven) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();		
		str.append("update HR_LOAN_MULTIPLE_INST_DTLS set PAYBILL_APPROVE_FLAG = 1,LOAN_APPROVE_FLAG = 1 where EMP_LOAN_ID = "+emp_loan_id+" and PAYBILL_MONTH = "+monthGiven+" and PAYBILL_YEAR = "+yearGiven+" and LOAN_APPROVE_FLAG = 0  ");	
		
		Query query1 = hibSession.createSQLQuery(str.toString());
		logger.error("updateEmpMulitiApproveFlag ----"+query1);
		query1.executeUpdate();
		
	}
	public long getOrgEmpEmpid(long empId) {
		Long finId=0l;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT emp_id FROM ORG_EMP_MST where user_id = "+empId+"");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query is :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
			finId = Long.parseLong(query.uniqueResult().toString());
		}
		return finId;
	}
	public List getHRRSchemWiseAmount(long billNo, int month, int year,	long locId,String selDate,String endDate) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT   cusmst.SCHEME_CODE,sum(pay.HRR),sum(pay.SERVICE_CHARGE),sum(pay.HRR_ARR),sum(pay.SERVICE_CHRGE_AREAR),count(pay.EMP_ID) FROM HR_CUSTODIAN_TYPE_MST cusmst inner join HR_EIS_QTR_EMP_MPG empqtr on empqtr.CUSTODIAN_ID = cusmst.CUSTODIAN_ID ");	
		sb.append(" inner join ORG_EMP_MST emp on emp.USER_ID = empqtr.ALLOCATED_TO  inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID = emp.EMP_ID ");		
		sb.append(" inner join HR_PAY_PAYBILL pay on pay.EMP_ID = eis.EMP_ID  inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID = pay.PAYBILL_GRP_ID   ");		
		sb.append(" where head.APPROVE_FLAG in (0,1,5) and head.PAYBILL_YEAR = "+year+" and head.PAYBILL_MONTH = "+month+" and head.BILL_NO = "+billNo+" ");	
		//sb.append(" and head.loc_id = "+locId+"  and (pay.HRR > 0 or pay.SERVICE_CHARGE > 0 or pay.HRR_ARR > 0 or pay.SERVICE_CHRGE_AREAR > 0)  and ((ALLOCATION_END_DATE > head.CREATED_DATE  or empqtr.ALLOCATION_END_DATE is null))  group by cusmst.SCHEME_CODE ");
		sb.append(" and head.loc_id = "+locId+"  and (pay.HRR > 0 or pay.SERVICE_CHARGE > 0 or pay.HRR_ARR > 0 or pay.SERVICE_CHRGE_AREAR > 0)   ");
		sb.append(" and  (empqtr.allocation_end_date>='" + selDate + "' or empqtr.allocation_end_date is null) and (empqtr.allocation_start_date<='" + endDate + "' or empqtr.allocation_start_date is null) group by cusmst.SCHEME_CODE  ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getHRRSchemWiseAmount is"+sql1query.toString());
		logger.error("getEmpMultiRecovery sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public List getSevenPCEmpDetails(String strEmpName, long locId, String flag) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT des.DSGN_NAME,cad.CADRE_NAME,grade.GRADE_NAME,eis.SCALE_NAME,emp.PAY_IN_PAY_BAND,emp.GRADE_PAY,emp.BASIC_PAY,eis.SCALE_START_AMT,eis.SCALE_END_AMT,emp.SEVARTH_ID,emp.doj,emp.dob,emp.PAY_COMMISSION FROM MST_DCPS_EMP emp inner join ORG_DESIGNATION_MST des on des.DSGN_ID = emp.DESIGNATION ");	
		sb.append(" inner join MST_DCPS_CADRE cad on cad.CADRE_ID = emp.CADRE ");	
		sb.append(" inner join org_emp_mst org on org.EMP_ID = emp.ORG_EMP_MST_ID ");	
		sb.append(" inner join ORG_GRADE_MST grade on grade.GRADE_ID = org.GRADE_ID ");	
		sb.append(" inner join HR_EIS_SCALE_MST eis on eis.SCALE_ID = emp.PAYSCALE  ");	
		sb.append("inner join org_ddo_mst ddo on ddo.DDO_CODE = emp.DDO_CODE ");
		sb.append("inner join CMN_LOOKUP_MST cmn on cmn.lookup_id = emp.PAY_COMMISSION ");
		sb.append("inner join RLT_DCPS_PAYROLL_EMP payroll on payroll.dcps_emp_id = emp.dcps_emp_id ");
		//pooja 16-11-2018
		if(flag.equals("Yes"))
		//sb.append(" where emp.SEVARTH_ID = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" and payroll.GIS_APPLICABLE in (700213,700214,700215,700216) "); 
			//sb.append(" where emp.SEVARTH_ID = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId);
		//else if(flag.equals("changePay")){
			sb.append(" where emp.SEVARTH_ID = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and payroll.GIS_APPLICABLE in (700213,700214,700215,700216) ");	
		//}
		else
		//	sb.append(" where emp.emp_name = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" and payroll.GIS_APPLICABLE in (700213,700214,700215,700216)  ");
			sb.append(" where emp.emp_name = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and payroll.GIS_APPLICABLE in (700213,700214,700215,700216) ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getSevenPCEmpDetails is"+sql1query.toString());
		logger.error("getSevenPCEmpDetails sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	
	//adde by pooja in Change pay post Details - 30-11-2018
	public List getSevenPCEmployeeDetails(String strEmpName, long locId, String flag) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT des.DSGN_NAME,cad.CADRE_NAME,grade.GRADE_NAME,eis.SCALE_NAME,emp.PAY_IN_PAY_BAND,emp.GRADE_PAY,emp.BASIC_PAY,eis.SCALE_START_AMT,eis.SCALE_END_AMT,emp.SEVARTH_ID,emp.doj,emp.dob FROM MST_DCPS_EMP emp inner join ORG_DESIGNATION_MST des on des.DSGN_ID = emp.DESIGNATION ");	
		sb.append(" inner join MST_DCPS_CADRE cad on cad.CADRE_ID = emp.CADRE ");	
		sb.append(" inner join org_emp_mst org on org.EMP_ID = emp.ORG_EMP_MST_ID ");	
		sb.append(" inner join ORG_GRADE_MST grade on grade.GRADE_ID = org.GRADE_ID ");	
		sb.append(" inner join HR_EIS_SCALE_MST eis on eis.SCALE_ID = emp.PAYSCALE  ");	
		sb.append("inner join org_ddo_mst ddo on ddo.DDO_CODE = emp.DDO_CODE ");
		sb.append("inner join CMN_LOOKUP_MST cmn on cmn.lookup_id = emp.PAY_COMMISSION ");
		sb.append("inner join RLT_DCPS_PAYROLL_EMP payroll on payroll.dcps_emp_id = emp.dcps_emp_id ");
		sb.append(" where emp.emp_name = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId);
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		logger.error("sql query for getSevenPCEmpDetails is new--"+sql1query.toString());
	
		return sql1query.list();
	}
	
	public List getgradeIdDetailsList(String payInPAyBand, String gradePay) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
			
		sb.append(" SELECT id,level FROM rlt_payband_gp_7pc where pay_in_payband = '"+payInPAyBand+"' and grade_pay = '"+gradePay+"' ");	
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getSevenPCEmpDetails is"+sql1query.toString());
		logger.error("getSevenPCEmpDetails sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	//added by pooja - 02-01-2019
	public List getStateGradeIdDetailsList(String payInPAyBand, String gradePay) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
			
		sb.append(" SELECT id,level FROM RLT_PAYBAND_GP_state_7pc where pay_in_payband = '"+payInPAyBand+"' and grade_pay = '"+gradePay+"' ");	
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getStategradeIdDetailsList is"+sql1query.toString());
		return sql1query.list();
	}
	
	//added by pooja - 02-01-2019
	public long getEmpDetailsList(String sevaarthId) {
		Session hibSession = getSession();
		Long gisAppl = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT cmn.LOOKUP_ID FROM MST_DCPS_EMP mst,RLT_DCPS_PAYROLL_EMP rlt,CMN_LOOKUP_MST cmn ");
		sb.append(" where mst.DCPS_EMP_ID = rlt.DCPS_EMP_ID and rlt.GIS_APPLICABLE = cmn.LOOKUP_ID and mst.SEVARTH_ID =  '"+sevaarthId+"'");	
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		if(sql1query.uniqueResult()!=null){
			gisAppl = Long.parseLong(sql1query.uniqueResult().toString());
		}
		logger.error("sql query for getSevenPCEmpDetails is"+sql1query.toString());
	
		return gisAppl;
	}
	//added by pooja 03-01-2019
	public List getStateNewBasicAsPerMAtrix(String gradeId, Long newBasicAccToCal) {
		List newBasicAsPErMat=null;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT "+gradeId+",cell FROM MST_state_MATRIX_7THPAY where  "+gradeId+" >= "+newBasicAccToCal+" and "+gradeId+" > 0 order by S_1 ");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query for getStateNewBasicAsPerMAtrix :: "+queryBuffer.toString());
		 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
			//newBasicAsPErMat = (List) query.list().get(0);
			 newBasicAsPErMat = query.list();
		}
		return newBasicAsPErMat;
	}
	
	//ended by pooja
	public List getNEwBasicAsPerMAtrix(String gradeId, Long newBasicAccToCal) {
		List newBasicAsPErMat=null;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT "+gradeId+",cell FROM MST_MATRIX_7THPAY where  "+gradeId+" >= "+newBasicAccToCal+" and "+gradeId+" > 0 order by GRADE_1 ");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query for getNEwBasicAsPerMAtrix :: "+queryBuffer.toString());
		 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
			//newBasicAsPErMat = (List) query.list().get(0);
			 newBasicAsPErMat = query.list();
		}
		return newBasicAsPErMat;
	}
	public void updateNewBasicPay(String strSevarthId, Long newbasicPaySvnPC,Date effectDate,String levelId) {		
		
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		String PayCommission = "700005";
		str.append("update mst_dcps_emp set SEVEN_PC_BASIC = "+newbasicPaySvnPC+",PAY_COMMISSION = '"+PayCommission+"',SEVEN_PC_LEVEL='"+levelId+"' where SEVARTH_ID = '"+strSevarthId+"' ");
		logger.info("updateEmpGPFDetails------"+str.toString());
		Query query1 = hibSession.createSQLQuery(str.toString());
		query1.executeUpdate();
		
		StringBuffer str1 = new StringBuffer();
		//str1.append(" update HR_EIS_OTHER_DTLS set other_svn_pc_basic = "+newbasicPaySvnPC+",SVN_PC_BASIC_EFFECT_DT ='"+effectDate+"' where emp_id =(SELECT emp_id FROM HR_EIS_EMP_MST where EMP_MPG_ID =(SELECT org_emp_mst_id FROM MST_DCPS_EMP where SEVARTH_ID = '"+strSevarthId+"'))");
		str1.append(" update HR_EIS_OTHER_DTLS set other_svn_pc_basic =:newbasicPaySvnPC ,SVN_PC_BASIC_EFFECT_DT =:effectDate where emp_id =(SELECT emp_id FROM HR_EIS_EMP_MST where EMP_MPG_ID =(SELECT org_emp_mst_id FROM MST_DCPS_EMP where SEVARTH_ID =:strSevarthId ))");

		logger.info("updateEmpGPFDetails------"+str1.toString());
		Query query2 = hibSession.createSQLQuery(str1.toString());
		query2.setLong("newbasicPaySvnPC", newbasicPaySvnPC);
		query2.setDate("effectDate", effectDate);
		query2.setString("strSevarthId", strSevarthId);
		query2.executeUpdate();
		
	}
	public Long getRevisedSvnPCBasic(long empId) {
		Long revised7PCBAsic=0l;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT OTHER_SVN_PC_BASIC FROM HR_EIS_OTHER_DTLS where emp_id = "+empId+" ");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query to get getRevisedSvnPCBasic :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
			revised7PCBAsic = Long.parseLong(query.uniqueResult().toString());
		}
		return revised7PCBAsic;
	}
	//
	public Long getRevisedSixPCBasic(long empId) {
		Long revised7PCBAsic=0l;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT OTHER_CURRENT_BASIC FROM HR_EIS_OTHER_DTLS where emp_id = "+empId+" ");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query to get getRevisedSixPCBasic :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
			revised7PCBAsic = Long.parseLong(query.uniqueResult().toString());
		}
		return revised7PCBAsic;
	}
	public int chkPostAndSerExp(String strSevarthId, long locId,String flag) {
		
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append(" SELECT count(emp.dcps_emp_id) FROM mst_dcps_emp emp inner join ORG_EMP_MST org on org.EMP_ID = emp.ORG_EMP_MST_ID ");	
		queryBuffer.append(" inner join ORG_USERPOST_RLT user on user.USER_ID = org.USER_ID and user.ACTIVATE_FLAG = 1 ");	
		if(flag.equals("Yes"))
		queryBuffer.append(" inner join ORG_DDO_MST ddo on ddo.DDO_CODE = emp.DDO_CODE where ddo.LOCATION_CODE = "+locId+" and emp.SEVARTH_ID = '"+strSevarthId+"' and (emp.EMP_SERVEND_DT >= sysdate or emp.EMP_SERVEND_DT is null) ");	
		else
		queryBuffer.append(" inner join ORG_DDO_MST ddo on ddo.DDO_CODE = emp.DDO_CODE where ddo.LOCATION_CODE = "+locId+" and emp.emp_name = '"+strSevarthId+"' and (emp.EMP_SERVEND_DT >= sysdate or emp.EMP_SERVEND_DT is null) ");
		
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("query is getcuntOFTemp*************"+query.toString());
		String status = query.uniqueResult().toString();
		int duplicatePayslipIDCount = Integer.parseInt(status);
		logger.error("duplicatePayslipIDCount" + duplicatePayslipIDCount);
		return duplicatePayslipIDCount;
	}
	public int countOfEmpRevisedBasic(String strSevarthId) {
		logger.error("hii getDuplicatePayslipId");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT count(1) FROM mst_dcps_emp where SEVARTH_ID = '"+strSevarthId+"' and SEVEN_PC_BASIC > 0 ");		
		Query query = session.createSQLQuery(sb.toString());
		logger.error("query is getcuntOFTemp*************"+query.toString());
		String status = query.uniqueResult().toString();
		int duplicatePayslipIDCount = Integer.parseInt(status);
		logger.error("duplicatePayslipIDCount" + duplicatePayslipIDCount);
		return duplicatePayslipIDCount;
	}
	public Long getExisintNEwBasic(String strSevarthId) {
		Long revised7PCBAsic=0l;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT SEVEN_PC_BASIC FROM mst_dcps_emp where SEVARTH_ID = '"+strSevarthId+"' ");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query to get getRevisedSvnPCBasic :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
			revised7PCBAsic = Long.parseLong(query.uniqueResult().toString());
		}
		return revised7PCBAsic;
	}
	public void insertOldSvnPCbasicDetails(String strSevarthId, long userId,long existSvnBasic) {
		try{
			StringBuffer str = new StringBuffer();
			Session hibSession = getSession();
			logger.info("ddoCode daoimpl"+strSevarthId);
			logger.info("activateFlag daoimpl"+userId);
			str.append("insert into HST_SEVENPC_BASIC_DTS(SEVARTH_ID,PRE_SVNPAY_BASIC,UPDATE_DATE,UDAPTED_BY)values('"+strSevarthId+"',"+existSvnBasic+",sysdate,"+userId+")");
			Query query = hibSession.createSQLQuery(str.toString());
			logger.info("insertOldSvnPCbasicDetails------"+str.toString());

			query.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();

		}
		
	}
	public List getNEwBasicAsPerMAtrixForBunching(String gradeId,
			Long newBasicAccToCal) {
		List temp=null;
		List lLstReturnList=null;
		Session session = getSession();
		
		//hibSession = getSession();
		try
		{		
			String branchQuery = "SELECT "+gradeId+",cell FROM MST_MATRIX_7THPAY where  "+gradeId+" >= "+newBasicAccToCal+" and  "+gradeId+" > 0 order by GRADE_1";
			Query sqlQuery= session.createSQLQuery(branchQuery);
			logger.error("sqlQuery Size"+sqlQuery.toString());
			temp= sqlQuery.list();
			
			ComboValuesVO lObjComboValuesVO = null;
			if (temp != null && temp.size() != 0) {
				lLstReturnList = new ArrayList<Object>();
				Object obj[];
				lLstReturnList = new ArrayList<Object>();
				lObjComboValuesVO = new ComboValuesVO();
				//lObjComboValuesVO.setId("----Select-----");
			//	lObjComboValuesVO.setDesc("-1");
			//	lLstReturnList.add(lObjComboValuesVO);
				for (int liCtr = 0; liCtr < temp.size(); liCtr++) {
					obj = (Object[]) temp.get(liCtr);
					lObjComboValuesVO = new ComboValuesVO();
					lObjComboValuesVO.setId(obj[1].toString());
					lObjComboValuesVO.setDesc(obj[0].toString());
					lLstReturnList.add(lObjComboValuesVO);
				}
			} else {
				lLstReturnList = new ArrayList<Object>();
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId("-----Select-----");
				lObjComboValuesVO.setDesc("-1");
				lLstReturnList.add(lObjComboValuesVO);
			}
			
			logger.error("List Size"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return lLstReturnList;
	}
	
	//added by pooja - 04-01-2018
	public List getStateNEwBasicAsPerMAtrixForBunching(String gradeId,
			Long newBasicAccToCal) {
		List temp=null;
		List lLstReturnList=null;
		Session session = getSession();
		
		//hibSession = getSession();
		try
		{		
			String branchQuery = "SELECT "+gradeId+",cell FROM MST_state_MATRIX_7THPAY where  "+gradeId+" >= "+newBasicAccToCal+" and  "+gradeId+" > 0 order by S_1";
			Query sqlQuery= session.createSQLQuery(branchQuery);
			logger.error("sqlQuery Size"+sqlQuery.toString());
			temp= sqlQuery.list();
			
			ComboValuesVO lObjComboValuesVO = null;
			if (temp != null && temp.size() != 0) {
				lLstReturnList = new ArrayList<Object>();
				Object obj[];
				lLstReturnList = new ArrayList<Object>();
				lObjComboValuesVO = new ComboValuesVO();
				//lObjComboValuesVO.setId("----Select-----");
			//	lObjComboValuesVO.setDesc("-1");
			//	lLstReturnList.add(lObjComboValuesVO);
				for (int liCtr = 0; liCtr < temp.size(); liCtr++) {
					obj = (Object[]) temp.get(liCtr);
					lObjComboValuesVO = new ComboValuesVO();
					lObjComboValuesVO.setId(obj[1].toString());
					lObjComboValuesVO.setDesc(obj[0].toString());
					lLstReturnList.add(lObjComboValuesVO);
				}
			} else {
				lLstReturnList = new ArrayList<Object>();
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId("-----Select-----");
				lObjComboValuesVO.setDesc("-1");
				lLstReturnList.add(lObjComboValuesVO);
			}
			
			logger.error("List Size"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return lLstReturnList;
	}
	
	public List CheckDuplicateBankAct(long BillNo)
	{
		List newBasicAsPErMat=null;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		//queryBuffer.append("SELECT "+gradeId+",cell FROM MST_MATRIX_7THPAY where  "+gradeId+" >= "+newBasicAccToCal+" order by GRADE_1 ");
		queryBuffer.append(" SELECT bank.BANK_NAME,bank.BANK_ACNT_NO FROM MST_DCPS_EMP_DUPLI_BANK bank ");
		queryBuffer.append(" inner join (SELECT bank_name,BANK_ACNT_NO  FROM  mst_dcps_emp  where BILLGROUP_ID="+BillNo+") tmp ");
		queryBuffer.append(" on bank.BANK_NAME=tmp.bank_name and bank.BANK_ACNT_NO=tmp.BANK_ACNT_NO ");
		
		
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query for CheckDuplicateBankAct :: "+queryBuffer.toString());
		 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
			//newBasicAsPErMat = (List) query.list().get(0);
			 newBasicAsPErMat = query.list();
		
		
	}
		 return newBasicAsPErMat;
	}
	//Added by Pooja 24-08-2018
	//Check Correct GPF Series
	
	public List CheckCorrectPFSeriesAndActNumber(long BillNo, String ddoCode)
	  {
	    List correctPFSeriesFLag = null;
	    Session hibSession = getSession();
	    StringBuffer queryBuffer = new StringBuffer();
	    
	    /*queryBuffer.append(" SELECT  d.SEVARTH_ID,d.EMP_NAME,b.PF_SERIES FROM ");
	    queryBuffer.append(" HR_PAY_GPF_DETAILS b, ORG_EMP_MST c ,mst_dcps_emp d,RLT_DCPS_PAYROLL_EMP a ");
	    queryBuffer.append(" where b.USER_ID = c.USER_ID and c.EMP_ID = d.ORG_EMP_MST_ID and a.DCPS_EMP_ID = d.DCPS_EMP_ID ");
	    queryBuffer.append(" and d.BILLGROUP_ID = " + BillNo + " and d.DDO_CODE='" + ddoCode + "' and b.NEW_PF_SERIES is null and b.PF_SERIES <> 'DCPS' and b.PF_SERIES <> 'NA' and c.GRADE_ID <> 100067 " );
	    queryBuffer.append(" and (c.EMP_SRVC_EXP>=sysdate or c.EMP_SRVC_EXP is null) ");*/
	    
	    queryBuffer.append(" select  gpf.PF_SERIES ,count(org.EMP_ID) ");
	    queryBuffer.append(" from hr_Eis_emp_mst eis inner join org_emp_mst org on eis.EMP_MPG_ID = org.EMP_ID ");
	    queryBuffer.append(" inner join ORG_USERPOST_RLT up on up.USER_ID = org.USER_ID and up.ACTIVATE_FLAG = 1  inner join ORG_POST_MST post ");
	    queryBuffer.append(" on post.POST_ID = up.POST_ID and post.ACTIVATE_FLAG = 1  inner join ORG_POST_DETAILS_RLT rlt on post.POST_ID = rlt.POST_ID ");
	    queryBuffer.append(" and rlt.LOC_ID = post.LOCATION_CODE  inner join HR_PAY_POST_PSR_MPG psr on psr.POST_ID = post.POST_ID and ");
	    queryBuffer.append(" psr.LOC_ID = post.LOCATION_CODE  inner join HR_PAY_GPF_DETAILS gpf on gpf.USER_ID = up.USER_ID ");
	    queryBuffer.append(" inner join org_ddo_mst ddo on ddo.LOCATION_CODE = post.LOCATION_CODE  ");
	    queryBuffer.append(" inner join MST_DCPS_EMP mde on mde.ORG_EMP_MST_ID = org.EMP_ID ");
	    queryBuffer.append(" inner join CMN_LOOKUP_MST cmn on cmn.LOOKUP_NAME = gpf.PF_SERIES ");
	    queryBuffer.append(" inner join mst_gpf_mapping mgm on mgm.LOOKUP_ID = cmn.LOOKUP_ID and cmn.PARENT_LOOKUP_ID = 700098 ");
	    queryBuffer.append(" inner join CMN_LOOKUP_MST mgm1 on mgm1.LOOKUP_ID = mgm.CORRECT_LOOKUP_ID and mgm1.PARENT_LOOKUP_ID = mgm.correct_parent_lookup_id ");
	    queryBuffer.append(" where ddo.DDO_CODE ="+ddoCode+" and  mde.BILLGROUP_ID = "+BillNo);
	    queryBuffer.append(" and gpf.PF_SERIES <> 'DCPS' and gpf.PF_SERIES <> 'NA' and ");
	    queryBuffer.append(" (org.EMP_SRVC_EXP>=sysdate or org.EMP_SRVC_EXP is null) and org.GRADE_ID <> 100067 ");
	    queryBuffer.append(" group by  gpf.PF_SERIES ");
	    
	    Query query = hibSession.createSQLQuery(queryBuffer.toString());
	    this.logger.error("Query for CheckCorrectPFSeriesAndActNumber :: " + queryBuffer.toString());
	    if ((query.list() != null) && (query.list().size() > 0) && (query.list().get(0) != null)) {
	      correctPFSeriesFLag = query.list();
	    }
	    return correctPFSeriesFLag;
	  }
	  
	
	public List CheckDuplicatePFSeriesAndActNumber(long BillNo)
	{
		List newBasicAsPErMat=null;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		//queryBuffer.append("SELECT "+gradeId+",cell FROM MST_MATRIX_7THPAY where  "+gradeId+" >= "+newBasicAccToCal+" order by GRADE_1 ");
		queryBuffer.append(" SELECT hrpay.PF_SERIES,hrpay.GPF_ACC_NO  FROM   MST_DCPS_EMP_DUPLI_GPF hrpay inner join   ");
		queryBuffer.append(" (SELECT hrpay.GPF_ACC_NO,hrpay.PF_SERIES FROM  HR_PAY_GPF_DETAILS hrpay  ");
		queryBuffer.append(" inner join ORG_EMP_MST emp on emp.USER_ID=hrpay.USER_ID ");
		queryBuffer.append(" inner join mst_dcps_emp mst on mst.ORG_EMP_MST_ID=emp.EMP_ID  ");
		queryBuffer.append(" where mst.BILLGROUP_ID="+BillNo+")tmp on tmp.GPF_ACC_NO = hrpay.GPF_ACC_NO and tmp.PF_SERIES = hrpay.PF_SERIES ");
		/*queryBuffer.append(" select rlt1.PF_SERIES_DESC,rlt1.PF_ACNO from (SELECT rlt.PF_SERIES_DESC,rlt.PF_ACNO,COUNT(org.EMP_ID) FROM RLT_DCPS_PAYROLL_EMP rlt ");
		queryBuffer.append(" inner join MST_DCPS_EMP mst on mst.DCPS_EMP_ID=rlt.DCPS_EMP_ID ");
		queryBuffer.append(" inner join ORG_EMP_MST  org on org.EMP_ID = mst.ORG_EMP_MST_ID ");
		queryBuffer.append(" inner join CMN_LOOKUP_MST cmn on cmn.LOOKUP_NAME = rlt.PF_SERIES_DESC and cmn.parent_lookup_id in (700400,700181) and cmn.actv=1");
		queryBuffer.append(" where mst.DDO_CODE <> '1111222222' and ");
		queryBuffer.append(" (mst.SUPER_ANN_DATE >= sysdate or mst.SUPER_ANN_DATE is null) and org.GRADE_ID <> 100067 ");
		queryBuffer.append(" and rlt.PF_SERIES_DESC <> 'NA' and  rlt.PF_SERIES_DESC <> 'DCPS' ");
		queryBuffer.append(" GROUP by rlt.PF_SERIES_DESC,rlt.PF_ACNO HAVING COUNT(*) > 1) as temp ");
		queryBuffer.append(" inner join RLT_DCPS_PAYROLL_EMP rlt1 on temp.PF_SERIES_DESC=rlt1.PF_SERIES_DESC and temp.PF_ACNO=rlt1.PF_ACNO ");
		queryBuffer.append(" inner join MST_DCPS_EMP mst1 on mst1.DCPS_EMP_ID=rlt1.DCPS_EMP_ID  ");
		queryBuffer.append(" inner join ORG_EMP_MST  org1 on org1.EMP_ID = mst1.ORG_EMP_MST_ID ");
		queryBuffer.append(" inner join ORG_DDO_MST ddo on ddo.DDO_CODE = mst1.DDO_CODE ");
		queryBuffer.append(" where mst1.DDO_CODE <> '1111222222' and ");
		queryBuffer.append(" (mst1.SUPER_ANN_DATE >= sysdate or mst1.SUPER_ANN_DATE is null) and org1.GRADE_ID <> 100067 ");
		queryBuffer.append(" and rlt1.PF_SERIES_DESC <> 'NA' and  rlt1.PF_SERIES_DESC <> 'DCPS' and mst1.BILLGROUP_ID ="+BillNo);*/
		//queryBuffer.append(" group by hrpay.GPF_ACC_NO,hrpay.PF_SERIES having count(*)>1  ");
		
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query for CheckDuplicatePFSeriesAndActNumber :: "+queryBuffer.toString());
		 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
			//newBasicAsPErMat = (List) query.list().get(0);
			 newBasicAsPErMat = query.list();
		
		
	}
		 return newBasicAsPErMat;
	}
	
	
	
	public List getNEwBasicAsPerMAtrixForBunchingForChangePaypost(
			String gradeId, Long newBasicAccToCal) {
		List newBasicAsPErMat=null;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT "+gradeId+",cell FROM MST_MATRIX_7THPAY where  "+gradeId+" >= "+newBasicAccToCal+" and  "+gradeId+" > 0 order by GRADE_1 ");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query for getNEwBasicAsPerMAtrix :: "+queryBuffer.toString());
		 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
			//newBasicAsPErMat = (List) query.list().get(0);
			 newBasicAsPErMat = query.list();
		}
		return newBasicAsPErMat;
	}
	public int getPayslipcntAftInsrt(long paybillId) {
		logger.error("hii getPayslipcntAftInsrt");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(1) FROM hr_pay_paybill pay inner join HR_PAY_PAYSLIP slip on pay.ID = slip.PAYBILL_ID where pay.PAYBILL_GRP_ID = "+paybillId+"  ");		
		Query query = session.createSQLQuery(sb.toString());
		logger.error("query is getPayslipcntAftInsrt*************"+query.toString());
		String status = query.uniqueResult().toString();
		int PayslipcntAftInsrt = Integer.parseInt(status);
		logger.error("PayslipcntAftInsrt:" + PayslipcntAftInsrt);
		return PayslipcntAftInsrt;
	}
	
	public List getcountofRentFreeEmployeesList(long billNo, int month,
			int year, long locId,String selDate,String endDate) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" select hrcustodia3_.SCHEME_CODE,count(hrpaypaybi2_.EMP_ID)  from org_emp_mst orgempmst0_, org_post_details_rlt orgpostdet1_,  ");	
		sb.append(" HR_PAY_PAYBILL hrpaypaybi2_, hr_eis_emp_mst hreisempms6_, HR_CUSTODIAN_TYPE_MST hrcustodia3_, hr_eis_qtr_emp_mpg hreisqtrms4_, ");	
		sb.append(" org_emp_mst orgempmst9_ where  hrpaypaybi2_.EMP_ID=hreisempms6_.emp_id and hreisempms6_.emp_mpg_id=orgempmst9_.emp_id  ");	
		sb.append(" and hreisempms6_.emp_mpg_id=orgempmst0_.emp_id and orgpostdet1_.post_id=hrpaypaybi2_.post_id  ");	
		sb.append(" and hreisqtrms4_.allocated_to=orgempmst9_.user_id ");	
		sb.append(" and hrcustodia3_.CUSTODIAN_ID=hreisqtrms4_.custodian_id and (hrpaypaybi2_.PAYBILL_GRP_ID in ");	
		sb.append(" (select paybillhea10_.PAYBILL_ID from PAYBILL_HEAD_MPG paybillhea10_ ");	
		sb.append(" where (paybillhea10_.bill_no in (select mstdcpsbil11_.BILL_GROUP_ID from MST_DCPS_BILL_GROUP mstdcpsbil11_ where mstdcpsbil11_.BILL_GROUP_ID="+billNo+" and mstdcpsbil11_.LOC_ID="+locId+"))  ");	
		sb.append(" and  (hreisqtrms4_.allocation_end_date>='" + selDate + "' or hreisqtrms4_.allocation_end_date is null) and (hreisqtrms4_.allocation_start_date<='" + endDate + "' or hreisqtrms4_.allocation_start_date is null) and hreisqtrms4_.QUARTERTYPE_LOOKUPID = 1 ");	
		sb.append(" and paybillhea10_.PAYBILL_MONTH="+month+" and paybillhea10_.PAYBILL_YEAR="+year+"  ");	
		sb.append(" and (paybillhea10_.approve_flag in (0 , 1 , 5)))) and (hrpaypaybi2_.HRR =0 and hrpaypaybi2_.SERVICE_CHARGE = 0 and hrpaypaybi2_.HRR_ARR = 0 and hrpaypaybi2_.SERVICE_CHRGE_AREAR = 0)  group by hrcustodia3_.SCHEME_CODE ");	
		
		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getcountofRentFreeEmployeesList is"+sql1query.toString());
		logger.error("getcountofRentFreeEmployeesList sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public List getPbListForHeadmpg(Long month, Long year, Long billType,Long treasuryCode) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		//sb.append(" SELECT PAYBILL_ID FROM PAYBILL_HEAD_MPG where PAYBILL_YEAR = "+year+" and PAYBILL_MONTH = "+month+" and BILL_CATEGORY = "+billType+" and approve_flag in (1,5) ");
		/*sb.append(" SELECT head.PAYBILL_ID FROM PAYBILL_HEAD_MPG head inner join ORG_DDO_MST ddo on head.LOC_ID = ddo.LOCATION_CODE  ");
		sb.append(" where head.PAYBILL_YEAR = "+year+" and head.PAYBILL_MONTH = "+month+" and head.APPROVE_FLAG in (1,5) and head.BILL_CATEGORY = "+billType+" and substr(DDO_CODE,1,2)= "+treasuryCode+"  ");*/
		sb.append(" SELECT PAYBILL_ID FROM PAYBILL_HEAD_MPG where PAYBILL_YEAR = "+year+" and PAYBILL_MONTH = "+month+" and APPROVE_FLAG in (0,1,5) and HEAD_GROSS_NEW = 0  and BILL_CATEGORY = "+billType+" ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getPbListForHeadmpg is"+sql1query.toString());
		logger.error("getPbListForHeadmpg sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	
	}
	public int getPaybillList(Long month, Long year, Long billType, List paybillList, List brknPerdEmpReglrList) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		
		str.append("update hr_pay_paybill set GROSS_NEW = GROSS_AMT+ DED_ADJUST, TOTAL_DED_NEW = TOTAL_DED + (2*DED_ADJUST) where PAYBILL_GRP_ID in (:paybillList) AND EMP_ID NOT IN (:brknPerdEmpReglrList) ");
		

		logger.info("getPaybillList------"+str.toString());
		Query query1 = hibSession.createSQLQuery(str.toString());
		query1.setParameterList("paybillList", paybillList); 
		query1.setParameterList("brknPerdEmpReglrList", brknPerdEmpReglrList);
		return query1.executeUpdate();
		
	}
	
	public List getdedAdjustGrossUp(List paybillList)
	{
	    List empDCPSdetails = null;
	    Double dedDetails=0d;
	    Session hibSession = getSession();
	    // intr
	    StringBuffer strQuery = new StringBuffer();
	    
	    strQuery
	            .append("SELECT head.PAYBILL_ID,SUM(paybill.DED_ADJUST) FROM HR_PAY_PAYBILL paybill  ");	    
	    strQuery.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID ");
	    //strQuery.append(" inner join CMN_LOOKUP_MST look on look.LOOKUP_ID =mst.AC_DCPS_MAINTAINED_BY ");
	   // strQuery.append(" inner join MST_HEAD_AC_CODE headmst on headmst.AC_TYPE=look.LOOKUP_ID ");
	    strQuery.append(" where head.paybill_id in (:paybillList) and head.APPROVE_FLAG in (0,1,5) group by head.PAYBILL_ID ");        
	    
	   
	  
	    
	    logger.info("Query in getdedAdjustGrossUp------>>>>>> " + strQuery.toString());
	    Query query = hibSession.createSQLQuery(strQuery.toString());
	    query.setParameterList("paybillList", paybillList); 
	  /*  if (query != null && query.list() != null && query.list().size() > 0)
	    {
	    	dedDetails = Double.parseDouble(query.list().get(0).toString());
	    }
	    return dedDetails;*/
	    return query.list();
	}
	
	public void updateHeadMpg(Long paybillId, Long dedAdjust) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		
		str.append("update PAYBILL_HEAD_MPG set Head_GROSS_NEW = BILL_GROSS_AMT + "+dedAdjust+" where PAYBILL_ID = "+paybillId+" ");
		Query query1 = hibSession.createSQLQuery(str.toString());
		logger.error("sql query for getPbListForHeadmpg is"+query1.toString());
		query1.executeUpdate();
	}
	public List getSupplimentDtlsList(Long month, Long year, Long finYrId, Long billType) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		/*sb.append(" SELECT head.PAYBILL_ID,pay.emp_id,sum(ded.DEDUC_VALUE)+sum(pay.DCPS_DELAY)+sum(pay.DCPS_PAY)+sum(pay.DCPS_DA) FROM MST_DCPS_BROKEN_PERIOD_PAY mst inner join HR_PAY_PAYBILL pay on pay.EMP_ID = mst.EIS_EMP_ID ");	
		sb.append(" inner join RLT_DCPS_BROKEN_PERIOD_DEDUC ded on ded.RLT_BROKEN_PERIOD_ID = mst.BROKEN_PERIOD_ID and ded.DEDUC_CODE = 666 ");	
		sb.append(" inner join PAYBILL_HEAD_MPG head on head.paybill_id = pay.paybill_grp_id  ");	
		sb.append(" where pay.PAYBILL_MONTH = "+month+" and pay.PAYBILL_YEAR = "+year+" and head.BILL_CATEGORY = 3 and mst.MONTH_ID = "+month+" and mst.YEAR_ID = "+finYrId+" and mst.TYPE_OF_BILL = 'S' and APPROVE_FLAG in (0,1,5) group by head.PAYBILL_ID,pay.emp_id ");	
	*/
		sb.append(" SELECT head.PAYBILL_ID,pay.emp_id,sum(pay.DCPS)+sum(pay.DCPS_DELAY)+sum(pay.DCPS_PAY)+sum(pay.DCPS_DA) FROM  HR_PAY_PAYBILL pay   ");
		sb.append(" inner join PAYBILL_HEAD_MPG head on pay.PAYBILL_YEAR = head.PAYBILL_YEAR and pay.PAYBILL_MONTH = head.PAYBILL_MONTH and head.paybill_id = pay.paybill_grp_id  ");
		if(billType == 3)
		sb.append(" where pay.PAYBILL_MONTH = "+month+" and pay.PAYBILL_YEAR = "+year+" and head.BILL_CATEGORY = 3  ");
		else
			sb.append(" where  pay.PAYBILL_YEAR = "+year+" and head.BILL_CATEGORY = "+billType+"  ");
		sb.append(" and APPROVE_FLAG in (0,1,5) and head.HEAD_GROSS_NEW = 0 group by head.PAYBILL_ID,pay.emp_id  ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getSupplimentDtlsList is"+sql1query.toString());
		logger.error("getSupplimentDtlsList sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public int updateSupHrpaypaybill(Long paybillId, Long empId,
			Long deducadjustSup) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		
		str.append("update HR_PAY_PAYBILL set GROSS_NEW = GROSS_AMT + "+deducadjustSup+",TOTAL_DED_NEW = TOTAL_DED + DED_ADJUST where PAYBILL_GRP_ID  = "+paybillId+" and EMP_ID = "+empId+" ");
		Query query1 = hibSession.createSQLQuery(str.toString());
		logger.error("sql query for getPbListForHeadmpg is"+query1.toString());
		return query1.executeUpdate();
		
	}
	public List paybillWiseData(Long month, Long year, Long finYrId, Long billType) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		/*sb.append(" SELECT head.PAYBILL_ID,sum(ded.DEDUC_VALUE)+sum(pay.DCPS_DELAY)+sum(pay.DCPS_PAY)+sum(pay.DCPS_DA) FROM MST_DCPS_BROKEN_PERIOD_PAY mst inner join HR_PAY_PAYBILL pay on pay.EMP_ID = mst.EIS_EMP_ID ");	
		sb.append(" inner join RLT_DCPS_BROKEN_PERIOD_DEDUC ded on ded.RLT_BROKEN_PERIOD_ID = mst.BROKEN_PERIOD_ID and ded.DEDUC_CODE = 666 ");
		sb.append(" inner join PAYBILL_HEAD_MPG head on head.paybill_id = pay.paybill_grp_id  ");
		sb.append(" where pay.PAYBILL_MONTH = "+month+" and pay.PAYBILL_YEAR = "+year+" and mst.MONTH_ID = "+month+" and mst.YEAR_ID = "+finYrId+" and mst.TYPE_OF_BILL = 'S' and APPROVE_FLAG in (0,1,5) group by head.PAYBILL_ID ");*/
		sb.append("SELECT head.PAYBILL_ID,sum(pay.dcps)+sum(pay.DCPS_DELAY)+sum(pay.DCPS_PAY)+sum(pay.DCPS_DA) FROM  HR_PAY_PAYBILL pay ");
		sb.append(" inner join PAYBILL_HEAD_MPG head on head.paybill_id = pay.paybill_grp_id and pay.PAYBILL_MONTH = head.PAYBILL_MONTH and head.PAYBILL_YEAR = pay.PAYBILL_YEAR ");
		if(billType == 3)
		sb.append("where  pay.PAYBILL_YEAR = "+year+" and pay.PAYBILL_MONTH = "+month+" and head.BILL_CATEGORY = 3  and APPROVE_FLAG in (0,1,5) group by head.PAYBILL_ID");
		else
			sb.append("where  pay.PAYBILL_YEAR = "+year+" and head.BILL_CATEGORY = "+billType+"  and APPROVE_FLAG in (0,1,5) group by head.PAYBILL_ID");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getPbListForHeadmpg is"+sql1query.toString());
		logger.error("getPbListForHeadmpg sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public void updateSupPaybillHdMpg(Long paybillId, Long deducadjustSup) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		
		str.append("update PAYBILL_HEAD_MPG set Head_GROSS_NEW = BILL_GROSS_AMT + "+deducadjustSup+" where PAYBILL_ID = "+paybillId+" ");
		Query query1 = hibSession.createSQLQuery(str.toString());
		logger.error("sql query for getPbListForHeadmpg is"+query1.toString());
		query1.executeUpdate();
	}
	public List getbrknPerdEmpReglrList(Long month, Long year, Long finYrId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT pay.EMP_ID FROM MST_DCPS_BROKEN_PERIOD_PAY mst inner join HR_PAY_PAYBILL pay on pay.EMP_ID = mst.EIS_EMP_ID ");	
		sb.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_MONTH = pay.PAYBILL_MONTH and head.PAYBILL_YEAR = pay.PAYBILL_YEAR and head.PAYBILL_ID = pay.PAYBILL_GRP_ID where head.PAYBILL_MONTH = "+month+" and head.PAYBILL_YEAR = "+year+" ");
		sb.append(" and head.APPROVE_FLAG in (0,1,5) and head.BILL_CATEGORY = 2 and mst.TYPE_OF_BILL = 'N' and mst.MONTH_ID = "+month+" and mst.YEAR_ID = "+finYrId+" ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getPbListForHeadmpg is"+sql1query.toString());
		logger.error("getPbListForHeadmpg sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public int updateBrokenRegular(Long month, Long year, Long billType,
			List paybillList, List brknPerdEmpReglrList) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		
		str.append("update hr_pay_paybill set GROSS_NEW = GROSS_AMT+ DED_ADJUST, TOTAL_DED_NEW = TOTAL_DED + DED_ADJUST where paybill_year = "+year+" and paybill_month = "+month+" and PAYBILL_GRP_ID in (:paybillList) AND EMP_ID  IN (:brknPerdEmpReglrList) ");
		 

		logger.info("getPaybillList------"+str.toString());
		Query query1 = hibSession.createSQLQuery(str.toString());
		query1.setParameterList("paybillList", paybillList); 
		query1.setParameterList("brknPerdEmpReglrList", brknPerdEmpReglrList);
		return query1.executeUpdate();
		
		
	}
	public int updateRegularBill(Long month, Long year, Long billType,
			List paybillList) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		
		str.append("update hr_pay_paybill set GROSS_NEW = GROSS_AMT+ DED_ADJUST, TOTAL_DED_NEW = TOTAL_DED + (2*DED_ADJUST) where paybill_year = "+year+" and paybill_month = "+month+" and PAYBILL_GRP_ID in (:paybillList) ");
		 ; 

		logger.info("updateRegularBill------"+str.toString());
		Query query1 = hibSession.createSQLQuery(str.toString());
		query1.setParameterList("paybillList", paybillList); 
		
		return query1.executeUpdate();
		
		
	}
	public List getBasicList(Long empId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT OLD_BASIC FROM HR_PAYFIX_MST where USER_ID ="+empId+" ");		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getPbListForHeadmpg is"+sql1query.toString());
		logger.error("getPbListForHeadmpg sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public List getEmpNames(String empIdString) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT USER_ID ||'~'|| USER_NAME FROM ORG_USER_MST where USER_ID in ("+empIdString+")  ");		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getPbListForHeadmpg is"+sql1query.toString());
		logger.error("getPbListForHeadmpg sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public Long getGISDetailsOfEMP(Long orgEmpId) {
		Long Gisdetails=0l;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT rlt.GIS_APPLICABLE FROM mst_dcps_emp emp inner join RLT_DCPS_PAYROLL_EMP rlt on emp.DCPS_EMP_ID = rlt.DCPS_EMP_ID  where emp.ORG_EMP_MST_ID =  "+orgEmpId+" ");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query to get getGISDetailsOfEMP :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
			Gisdetails = Long.parseLong(query.uniqueResult().toString());
		}
		return Gisdetails;
	}
	
	
	public List getNEwBasicAsPerMAtrixForBunchPayPost(String gradeId) {
		List temp=null;
		List lLstReturnList=null;
		Session session = getSession();
		
		//hibSession = getSession();
		try
		{		
			String branchQuery = "SELECT "+gradeId+",cell FROM MST_MATRIX_7THPAY where   "+gradeId+" > 0 order by GRADE_1";
			Query sqlQuery= session.createSQLQuery(branchQuery);
			logger.error("sqlQuery Size"+sqlQuery.toString());
			temp= sqlQuery.list();
			
			ComboValuesVO lObjComboValuesVO = null;
			if (temp != null && temp.size() != 0) {
				lLstReturnList = new ArrayList<Object>();
				Object obj[];
				lLstReturnList = new ArrayList<Object>();
				lObjComboValuesVO = new ComboValuesVO();
				//lObjComboValuesVO.setId("----Select-----");
			//	lObjComboValuesVO.setDesc("-1");
			//	lLstReturnList.add(lObjComboValuesVO);
				for (int liCtr = 0; liCtr < temp.size(); liCtr++) {
					obj = (Object[]) temp.get(liCtr);
					lObjComboValuesVO = new ComboValuesVO();
					lObjComboValuesVO.setId(obj[1].toString());
					lObjComboValuesVO.setDesc(obj[0].toString());
					lLstReturnList.add(lObjComboValuesVO);
				}
			} else {
				lLstReturnList = new ArrayList<Object>();
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId("-----Select-----");
				lObjComboValuesVO.setDesc("-1");
				lLstReturnList.add(lObjComboValuesVO);
			}
			
			logger.error("List Size"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return lLstReturnList;
	}
	
	
	
	
	
	
	//added by pooja -03-01-2019
	public List getNEwStateBasicAsPerMAtrixForBunchPayPost(String gradeId) {
		List temp=null;
		List lLstReturnList=null;
		Session session = getSession();
		
		//hibSession = getSession();
		try
		{		
			String branchQuery = "SELECT "+gradeId+",cell FROM MST_state_MATRIX_7THPAY where   "+gradeId+" > 0 order by S_1";
			Query sqlQuery= session.createSQLQuery(branchQuery);
			logger.error("sqlQuery getNEwStateBasicAsPerMAtrixForBunchPayPost"+sqlQuery.toString());
			temp= sqlQuery.list();
			
			ComboValuesVO lObjComboValuesVO = null;
			if (temp != null && temp.size() != 0) {
				lLstReturnList = new ArrayList<Object>();
				Object obj[];
				lLstReturnList = new ArrayList<Object>();
				lObjComboValuesVO = new ComboValuesVO();
				//lObjComboValuesVO.setId("----Select-----");
			//	lObjComboValuesVO.setDesc("-1");
			//	lLstReturnList.add(lObjComboValuesVO);
				for (int liCtr = 0; liCtr < temp.size(); liCtr++) {
					obj = (Object[]) temp.get(liCtr);
					lObjComboValuesVO = new ComboValuesVO();
					lObjComboValuesVO.setId(obj[1].toString());
					lObjComboValuesVO.setDesc(obj[0].toString());
					lLstReturnList.add(lObjComboValuesVO);
				}
			} else {
				lLstReturnList = new ArrayList<Object>();
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId("-----Select-----");
				lObjComboValuesVO.setDesc("-1");
				lLstReturnList.add(lObjComboValuesVO);
			}
			
			logger.error("List Size"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return lLstReturnList;
	}
	
	
	
	
	public List getCRTPostDetails(Long locId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" select emp.SEVARTH_ID,emp.EMP_NAME,pd.POST_NAME,emp.EMP_SERVEND_DT,org.POST_ID,org.CRT_FLAG,org.POST_TYPE_LOOKUP_ID from org_post_details_rlt pd ");
		sb.append(" inner join org_post_mst org on  pd.post_id=org.post_id ");
		sb.append(" left join  org_userpost_rlt up on   up.post_id = pd.post_id  and up.end_date is null and up.activate_flag = 1 ");
		sb.append(" left join org_emp_mst o on o.user_id = up.user_id and o.lang_id = 1 ");
		sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = o.EMP_ID ");
		sb.append(" where org.activate_flag=1   ");
		sb.append("and (org.END_DATE > sysdate or org.END_DATE is null) ");
		sb.append(" and org.POST_TYPE_LOOKUP_ID in ('10001198129','10001198130')   ");
		sb.append(" and  pd.loc_id = '"+locId+"'");
		/*sb.append(" SELECT mst.SEVARTH_ID,mst.EMP_NAME,pstdtls.POST_NAME,mst.EMP_SERVEND_DT,post.POST_ID,post.CRT_FLAG,post.POST_TYPE_LOOKUP_ID,post.end_date FROM ORG_POST_MST post  ");	
		sb.append(" inner join ORG_POST_DETAILS_RLT pstdtls on post.POST_ID = pstdtls.POST_ID ");
		sb.append(" inner join ORG_USERPOST_RLT userpost on userpost.POST_ID = post.POST_ID and userpost.ACTIVATE_FLAG = 1 ");
		sb.append(" inner join ORG_EMP_MST emp on emp.USER_ID = userpost.USER_ID ");
		sb.append(" inner join MST_DCPS_EMP mst on mst.ORG_EMP_MST_ID = emp.EMP_ID ");
		sb.append("where DDO_CODE = '"+strDDOCode+"' and post.POST_TYPE_LOOKUP_ID in ('10001198130','10001198129') and  (mst.EMP_SERVEND_DT >sysdate or mst.EMP_SERVEND_DT is null)  ");*/
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getPbListForHeadmpg is"+sql1query.toString());
		logger.error("getPbListForHeadmpg sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public void updateCRTpostMPg(Long lpostId, String format) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		str.append("update ORG_POST_MST set END_DATE ='"+format+"',CRT_FLAG = 2,UPDATED_DATE = sysdate where POST_ID = "+lpostId+" ");
		Query query1 = hibSession.createSQLQuery(str.toString());
		query1.executeUpdate();
		
		StringBuffer str1 = new StringBuffer();
		str1.append("update ORG_USERPOST_RLT set END_DATE ='"+format+"' ,UPDATED_DATE = sysdate where POST_ID ="+lpostId+" and ACTIVATE_FLAG = 1 ");
		logger.error("updateEmpGPFDetails------"+str1.toString());
		Query query2 = hibSession.createSQLQuery(str1.toString());
		query2.executeUpdate();
		
		StringBuffer str2 = new StringBuffer();
		str2.append("update CRT_POST_MAPPING set POST_END_DATE = '"+format+"',CRT_FLAG = 2,UPDATED_DATE= sysdate where POST_ID = "+lpostId+" ");
		logger.error("updateEmpGPFDetails------"+str2.toString());
		Query query3 = hibSession.createSQLQuery(str2.toString());
		query3.executeUpdate();
		
		
		
	}
	public List checkCRTPostEligible(Long locId, String result) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT post.POST_ID,org.POST_NAME FROM ORG_POST_MST post inner join ORG_USERPOST_RLT userpost on post.POST_ID = userpost.POST_ID and userpost.ACTIVATE_FLAG = 1 ");	
		sb.append(" inner join ORG_POST_DETAILS_RLT org on org.POST_ID = post.POST_ID ");
		sb.append(" where post.LOCATION_CODE = '"+locId+"' and post.CRT_FLAG = 0 and post.POST_TYPE_LOOKUP_ID in ('10001198130','10001198129') and post.POST_ID in ("+result+")");

		Query sql1query=hibSession.createSQLQuery(sb.toString());
		//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
		logger.error("sql query for getPbListForHeadmpg is"+sql1query.toString());
		logger.error("getPbListForHeadmpg sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	public List<ComboValuesVO> getFieldDeptFromAdminDeptCode() {

		ArrayList<ComboValuesVO> lLstReturnList = new ArrayList<ComboValuesVO>();
		StringBuilder lStrQuery = new StringBuilder();		
		lStrQuery.append(" SELECT LOC_ID,LOC_NAME FROM CMN_LOCATION_MST where DEPARTMENT_ID = 100011 and LOC_ID in (10595,10590,10548) ");		
		Query selectQuery = ghibSession.createSQLQuery(lStrQuery.toString());		
		List lLstResult = selectQuery.list();
		ComboValuesVO lObjComboValuesVO = null;
		lObjComboValuesVO = new ComboValuesVO();
		lObjComboValuesVO.setId("-1");
		lObjComboValuesVO.setDesc("-- Select --");
		lLstReturnList.add(lObjComboValuesVO);
		if (lLstResult != null && lLstResult.size() != 0) {			
			Object obj[];
			for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
				obj = (Object[]) lLstResult.get(liCtr);
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId(obj[0].toString());				
				lObjComboValuesVO.setDesc(obj[1].toString());
				lLstReturnList.add(lObjComboValuesVO);
			}
		} 
		return lLstReturnList;
	
	}


	//made by Pooja 
	
	//get Emp DCPS Flag
	public String getDCPSStoppedFlag(long eisEmpId) {
		String dcpsStoppedFlag = "N";
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		//queryBuffer.append(" SELECT em.EMP_ELIGIBILITY_FLAG FROM hr_eis_emp_mst eis inner join MST_DCPS_EMP em on em.ORG_EMP_MST_ID=eis.EMP_MPG_ID ");
		queryBuffer.append(" SELECT a.EMP_ELIGIBILITY_FLAG FROM MST_DCPS_EMP a,hr_eis_emp_mst c where a.ORG_EMP_MST_ID = c.EMP_MPG_ID ");
		queryBuffer.append(" and a.REG_STATUS = 1 ");
		queryBuffer.append(" and (a.ddo_code <> '1111222222' or a.ddo_code is null)");
		queryBuffer.append(" and c.emp_id="+eisEmpId);
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query is :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
			dcpsStoppedFlag = query.uniqueResult().toString();
		}
		return dcpsStoppedFlag;
	}
	
	//Get All Emp Component
	public List getEmpCompMpgList(long eisEmpId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM HR_EIS_EMP_COMPONENT_GRP_MST mst inner join HR_EIS_EMP_COMPONENT_MPG emp on mst.EMP_COMPO_GRP_ID = emp.COMPO_GROUP_ID  ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = mst.EMP_ID ");
		sb.append(" inner join mst_dcps_emp dcps  on dcps.ORG_EMP_MST_ID = eis.EMP_MPG_ID and dcps.REG_STATUS = 1  ");
		sb.append(" where mst.EMP_ID = "+eisEmpId+" and mst.IS_ACTIVE = 1  ");
		sb.append(" and emp.IS_ACTIVE = 1 and emp.COMPO_ID in (59,108,122,120,121)");
		
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		logger.error("sql query for getBilldetailsList is"+sql1query.toString());
		logger.error("sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	//check LNJ Dept added by pooja - 14-07-2018
	public String getLNJDeptDDO(String ddoCode) {
		String checkLNJDeptDDO = "N";
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT * FROM ORG_DDO_MST where DEPT_LOC_CODE = 10010 and DDO_CODE ='"+ddoCode+"'");
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query is :: "+queryBuffer.toString());
		if(query.uniqueResult()!=null){
			checkLNJDeptDDO = query.uniqueResult().toString();
			checkLNJDeptDDO = "Y";
		}
		return checkLNJDeptDDO;
	}
	//Added by pooja for GPF Series - 29-08-2018
	public String getCorrectPFSeriesAndActNumber(String empIdsStr,String ddoCode) {
		List newPFSeriesUpdated = null;
		String Flag = "N";
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT * FROM HR_PAY_gpf_birth_updation a where a.EMP_ID in ("+empIdsStr+") and a.DDO_CODE ='"+ddoCode+"'");
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query getCorrectPFSeriesAndActNumber is :: "+queryBuffer.toString());
		 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
			newPFSeriesUpdated = query.list();
			 Flag = "Y";
		}
		return Flag;
	}
	
	//Added by pooja for GPF Series - 15-09-2018
		public String getCheckPFSeries(String newGPFSeries) {
			List newPFSeriesUpdated = null;
			String Flag = "N";
			if(newGPFSeries.equals("AGMH") || newGPFSeries.equals("AGCMH") || newGPFSeries.equals("AGVMH") ||
					newGPFSeries.equals("AJMH") || newGPFSeries.equals("EDMH") || newGPFSeries.equals("EXCMH") ||
					newGPFSeries.equals("GAMH") || newGPFSeries.equals("IASMH") || newGPFSeries.equals("IFSBN") ||
					newGPFSeries.equals("IFSMH") || newGPFSeries.equals("INDMH") || newGPFSeries.equals("IPSMH") ||
					newGPFSeries.equals("JMH") || newGPFSeries.equals("MMH") || newGPFSeries.equals("MISMH") || newGPFSeries.equals("OTMH") ||
					newGPFSeries.equals("PBMH") || newGPFSeries.equals("PCMH") || newGPFSeries.equals("PHMH") || newGPFSeries.equals("PNMH") ||
					newGPFSeries.equals("PSMH") || newGPFSeries.equals("PWMH") || newGPFSeries.equals("RMH") || newGPFSeries.equals("SMH") ||
					newGPFSeries.equals("SCIMH") || newGPFSeries.equals("STYMH") || newGPFSeries.equals("FAMH")){
				
				 Flag = "Y";
				
			}
		
			return Flag;
		}
	//Added by pooja for GPF Series - 07-09-2018
		public List getDCPSSeries(long billNo,String ddoCode) {
			List newPFSeriesUpdated = null;
			//String Flag = "N";
			Session hibSession = getSession();
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer.append("SELECT b.PF_SERIES FROM MST_DCPS_EMP a,HR_PAY_GPF_DETAILS b,ORG_EMP_MST c,CMN_LOOKUP_MST d where ");
			queryBuffer.append(" a.ORG_EMP_MST_ID = c.EMP_ID and d.LOOKUP_NAME = b.PF_SERIES and ");
			queryBuffer.append(" c.USER_ID = b.USER_ID and a.DDO_CODE ='"+ddoCode+"' and a.BILLGROUP_ID = "+billNo+" and b.PF_SERIES <> 'DCPS' and d.PARENT_LOOKUP_ID = 700098");
			Query query = hibSession.createSQLQuery(queryBuffer.toString());
			logger.error("Query getDCPSSeries is :: "+queryBuffer.toString());
			 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
				newPFSeriesUpdated = query.list();
				// Flag = "Y";
			}
			return newPFSeriesUpdated;
		}

		//Added by pooja for GPF Series - 11-09-2018
			public String getDCPSSeriesForEmp(String empId,String ddoCode) {
				List newPFSeriesUpdated = null;
				String Flag = "N";
				Session hibSession = getSession();
				StringBuffer queryBuffer = new StringBuffer();
				queryBuffer.append("SELECT * FROM MST_DCPS_EMP a,HR_PAY_GPF_DETAILS b,ORG_EMP_MST c where ");
				queryBuffer.append(" a.ORG_EMP_MST_ID = c.EMP_ID and " );
				queryBuffer.append(" c.USER_ID = b.USER_ID and a.DDO_CODE ='"+ddoCode+"' and a.ORG_EMP_MST_ID in ("+empId+") and b.PF_SERIES = 'DCPS'");
				Query query = hibSession.createSQLQuery(queryBuffer.toString());
				logger.error("Query getDCPSSeriesForEmp is :: "+queryBuffer.toString());
				 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
					newPFSeriesUpdated = query.list();
					 Flag = "Y";
				}
				return Flag;
			}
	public List getGPFEmployeeName(String orgEmpIdsStr, String ddoCode) {
		List empList = null;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT SEVARTH_ID , EMP_NAME FROM MST_DCPS_EMP where ORG_EMP_MST_ID in ("+orgEmpIdsStr+") and DDO_CODE ='"+ddoCode+"'");
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query is :: "+queryBuffer.toString());
		 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
			 empList = query.list();
		}
		return empList;
	}
	
	public List getStateNewBasicAsPerMAtrix(String gradeId) {
		logger.info("getStateNewBasicAsPerMAtrix Method Start &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;
		List resultList=null;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT cell ,"+gradeId+" FROM MST_state_MATRIX_7THPAY where  "+gradeId+" > 0 order by S_1 ");	
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.error("Query for getStateNewBasicAsPerMAtrix :: "+queryBuffer.toString());
		 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
			//newBasicAsPErMat = (List) query.list().get(0);
			 resultList = query.list();
		}
		 
		 cmbVO = new ComboValuesVO();
			
			if (resultList != null && resultList.size() > 0) {
				cmbVO = new ComboValuesVO();
				cmbVO.setId("-1");
				cmbVO.setDesc("-- Select --");
				finalList.add(cmbVO);
				Iterator it = resultList.iterator();
				while (it.hasNext()) {
					
					cmbVO = new ComboValuesVO();
					obj = (Object[]) it.next();
					logger.info("obj[0].toString()="+obj[0].toString());
					logger.info("obj[1].toString()="+obj[1].toString());
					cmbVO.setId(obj[0].toString());
					cmbVO.setDesc(obj[1].toString());
					finalList.add(cmbVO);
				}
			}
		return finalList;
	}
	// added by lekhchand 	 TA 2022 
		public Long getSvnPCLevel(long empId) {
			Long svnPCLevel = 0l;
			Session hibSession = getSession();
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer
					.append("SELECT mstdcpsemp.SEVEN_PC_LEVEL FROM MST_DCPS_EMP mstdcpsemp inner JOIN  HR_EIS_EMP_MST hreisemp ON  mstdcpsemp.ORG_EMP_MST_ID  =hreisemp.EMP_MPG_ID where hreisemp.EMP_ID= "
							+ empId + " ");
			Query query = hibSession.createSQLQuery(queryBuffer.toString());
			logger.error("Query check level  to get getSvnPCLevel :: "
					+ queryBuffer.toString());
			if (query.uniqueResult() != null) {
				svnPCLevel = Long.parseLong(query.uniqueResult().toString());
			}
			return svnPCLevel;
		}

		public Long getSvnPayCommission(long empId) {
			Long paycommissionId = 0l;
			Session hibSession = getSession();
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer
					.append("SELECT mstdcpsemp.PAY_COMMISSION  FROM MST_DCPS_EMP mstdcpsemp inner JOIN  HR_EIS_EMP_MST hreisemp ON  mstdcpsemp.ORG_EMP_MST_ID  =hreisemp.EMP_MPG_ID where hreisemp.EMP_ID= "
							+ empId + " ");
			Query query = hibSession.createSQLQuery(queryBuffer.toString());
			logger.error("Query check level  to get getSvnPayCommissionId :: "
					+ queryBuffer.toString());
			if (query.uniqueResult() != null) {
				paycommissionId = Long.parseLong(query.uniqueResult().toString());
			}
			return paycommissionId;
		}
		
		public Long getSvnPCLevel2(long empId) {
			Long svnPCLevel = 0l;
			Session hibSession = getSession();
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer
					.append("SELECT rltgrade.LEVEL_ID  FROM MST_DCPS_EMP mstdcpsemp inner JOIN  HR_EIS_EMP_MST hreisemp ON  mstdcpsemp.ORG_EMP_MST_ID =hreisemp.EMP_MPG_ID  inner join RLT_PAYBAND_GP_STATE_7PC rltgrade ON mstdcpsemp.GRADE_PAY=rltgrade.GRADE_PAY   where hreisemp.EMP_ID= "
							+ empId + " ORDER by rltgrade.LEVEL FETCH FIRST 1 ROWS ONLY");
			Query query = hibSession.createSQLQuery(queryBuffer.toString());
			logger.error("Query check level  to get getSvnPayCommissionId :: "
					+ queryBuffer.toString());
			if (query.uniqueResult() != null) {
				svnPCLevel = Long.parseLong(query.uniqueResult().toString());
			}
			return svnPCLevel;
		}
		
		// endded  by lekhchand  TA 2022 

	
}
