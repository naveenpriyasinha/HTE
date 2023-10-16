package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.SimpleDateFormat; 
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;

public class BatchITGpfDetailsUpdateDaoImpl extends GenericDaoHibernateImpl<HrPayDeductionDtls, Long> 
{
	
	public BatchITGpfDetailsUpdateDaoImpl(Class<HrPayDeductionDtls> type, SessionFactory sessionFactory) 
	{
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getITGpfDataforDisplay(String billNo, long locId)
	{			
		List resList=null;
		try{
			
			Calendar cd = Calendar.getInstance();  
			Date d = new Date();
			cd.setTime(d);    
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");   
			cd.set(Calendar.DAY_OF_MONTH, cd.getActualMinimum(Calendar.DAY_OF_MONTH));   
			String firstDaySysDate = sdf.format(cd.getTime()); 
			
			cd.set(Calendar.DAY_OF_MONTH, cd.getActualMaximum(Calendar.DAY_OF_MONTH));   
			String lastDaySysDate = sdf.format(cd.getTime());
		
			//String firstDaySysDate = "01/feb/2010";
			//String lastDaySysDate = "28/feb/2010"; this date was for testing purpose
		 
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append( "  SELECT concat(concat(concat(emp.emp_fname, ' '), concat(emp.emp_mname, ' ')),concat(emp.emp_lname, ' ')) empName, gm.grade_name,dsgn.dsgn_shrt_name, dedIT.Emp_Deduc_Amount, vpf.vpf_amt,  ");
			query.append( "  emp.emp_id orgEmpId, hremp.emp_id hrEmpId, psr.psr_no , dedIT.Deduc_Dtls_Code itPK, vpf.pay_vpf_id ,  hremp.emp_type,  dedPF.Emp_Deduc_Amount PF , dedPF.Deduc_Dtls_Code pfPK");
			query.append( "  FROM org_emp_mst emp, hr_pay_post_psr_mpg     psr, hr_pay_bill_subhead_mpg bhm, org_userpost_rlt  up, hr_eis_other_dtls other, org_designation_mst     dsgn,  ");
			query.append( "  org_post_details_rlt    pd,      org_grade_mst     gm, hr_eis_emp_mst    hremp left outer join hr_pay_vpf_dtls vpf on vpf.emp_id = hremp.emp_id  ");
			query.append( "  Left outer join   hr_pay_deduction_dtls   dedIT on  dedIT.emp_id = hremp.emp_id and dedIT.emp_deduc_id = 32  left outer join hr_pay_deduction_dtls dedPF on dedPF.emp_id = hremp.emp_id and dedPF.emp_deduc_id = 45 ");
			query.append( "  WHERE emp.emp_id = hremp.emp_mpg_id and up.user_id = emp.user_id and up.post_id = psr.post_id and psr.bill_no = bhm.bill_subhead_id   ");
			if(billNo != null && !billNo.equals(""))
				query.append( "  and  bhm.bill_subhead_id  = " + billNo);
			if(locId > -1 )
				query.append( "  and bhm.loc_id = " + locId);
			query.append( "  and  emp.emp_srvc_exp > '" + lastDaySysDate + "' and (up.end_date >= '" + firstDaySysDate + "' or up.end_date is null)   ");
			query.append( "  and  other.emp_id = hremp.emp_id and dsgn.dsgn_id = pd.dsgn_id and pd.post_id = up.post_id and gm.grade_id = emp.grade_id  ");
			query.append( "  order BY psr.psr_no  ");
			logger.info("Query for get  new depertment cmn master table  is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
			
		}catch(Exception ex)
		{
			logger.info("Exception occure in Parsing date formating : " + ex);
		}	
		return resList;
	}
	public List getBillListForDisplay(long locId)
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append("select s.bill_no,s.BILL_SUBHEAD_ID from hr_pay_bill_subhead_mpg s where loc_id="+locId+" order by bill_no ");
			logger.info("Query for get getBillNoData is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
				
		return resList;
	}	
	
	//added by manish
	
	public List getRecordForGpfBatchUpdate(long locId,long empId,long billNo)
	{
		Date date=new Date();
		int month=date.getMonth()+1;
		int year=date.getYear()+1900;
		Session hibSession= getSession();
		StringBuffer query=new StringBuffer();
		//query.append("select * from HR_PAY_PAYBILL r where r.PAYBILL_YEAR=2010 and r.PAYBILL_MONTH=12 and r.LOC_ID="+locId+" AND r.EMP_ID="+empId);
	
		query.append("select pb.IT,pb.GPF_C,pb.GPF_IV from hr_pay_paybill pb where pb.loc_id="+locId+" and pb.emp_id="+empId);
		query.append(" and pb.paybill_grp_id in");
		query.append(" (select ph.paybill_id from paybill_head_mpg ph where ph.bill_no in");
//		query.append(" (select mpg.bill_subhead_id from hr_pay_bill_subhead_mpg mpg where mpg.bill_no="+billNo+" and mpg.loc_id="+locId);
		query.append(" ("+billNo);
		query.append(") and ph.paybill_month="+month);
		query.append("   and ph.paybill_year="+year);
		query.append("   and ph.approve_flag=4)");
		logger.info("Query for get getRecordForGpfBatchUpdate is---->>>>"+query.toString());
		Query sqlQuery=hibSession.createSQLQuery(query.toString());
		List resList=sqlQuery.list();
		return resList; 
		
	}
	
	
	public List getITGpfDataforUpdatingHrPayPayBill(String billNo, long locId,long empId)
	{			
		List resList=null;
		try{
			
			Calendar cd = Calendar.getInstance();  
			Date d = new Date();
			cd.setTime(d);    
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");   
			cd.set(Calendar.DAY_OF_MONTH, cd.getActualMinimum(Calendar.DAY_OF_MONTH));   
			String firstDaySysDate = sdf.format(cd.getTime()); 
			
			cd.set(Calendar.DAY_OF_MONTH, cd.getActualMaximum(Calendar.DAY_OF_MONTH));   
			String lastDaySysDate = sdf.format(cd.getTime());
		
			//String firstDaySysDate = "01/feb/2010";
			//String lastDaySysDate = "28/feb/2010"; this date was for testing purpose
		 
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append( "  SELECT  distinct(dedIT.Emp_Deduc_Amount), vpf.vpf_amt,gm.GRADE_NAME, psr.psr_no ");
			//query.append( "  emp.emp_id orgEmpId, hremp.emp_id hrEmpId, psr.psr_no , dedIT.Deduc_Dtls_Code itPK, vpf.pay_vpf_id ,  hremp.emp_type,  dedPF.Emp_Deduc_Amount PF , dedPF.Deduc_Dtls_Code pfPK");
			query.append( "  FROM paybill_head_mpg ph,org_emp_mst emp, hr_pay_post_psr_mpg     psr, hr_pay_bill_subhead_mpg bhm, org_userpost_rlt  up, hr_eis_other_dtls other, org_designation_mst     dsgn,  ");
			query.append( "  org_post_details_rlt    pd,      org_grade_mst     gm, hr_eis_emp_mst    hremp left outer join hr_pay_vpf_dtls vpf on vpf.emp_id = hremp.emp_id  ");
			query.append( "  Left outer join   hr_pay_deduction_dtls   dedIT on  dedIT.emp_id = hremp.emp_id and dedIT.emp_deduc_id = 32  left outer join hr_pay_deduction_dtls dedPF on dedPF.emp_id = hremp.emp_id and dedPF.emp_deduc_id = 45 ");
			query.append( "  WHERE ph.BILL_NO=bhm.BILL_SUBHEAD_ID and emp.emp_id = hremp.emp_mpg_id and up.user_id = emp.user_id and up.post_id = psr.post_id and psr.bill_no = bhm.bill_subhead_id   ");
			if(billNo != null && !billNo.equals(""))
				query.append( "  and  bhm.bill_subhead_id  = " + billNo);
			if(locId > -1 )
				query.append( "  and bhm.loc_id = " + locId);
			query.append( "  and  emp.emp_srvc_exp > '" + lastDaySysDate + "' and (up.end_date >= '" + firstDaySysDate + "' or up.end_date is null)   ");
			query.append( "  and  other.emp_id = hremp.emp_id and dsgn.dsgn_id = pd.dsgn_id and pd.post_id = up.post_id and gm.grade_id = emp.grade_id  ");
			query.append( "  and ph.APPROVE_FLAG=4 and emp.emp_id="+empId+" order BY psr.psr_no  ");
			logger.info("Query for get  new depertment cmn master table  is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
			
		}catch(Exception ex)
		{
			logger.info("Exception occure in Parsing date formating : " + ex);
		}	
		return resList;
	}
	public List	getPKOfHrPayPaybill(long empId,long billNo)
	{
		List resList=null;
		
		
		Date date=new Date();
		int month=date.getMonth()+1;
		int year=date.getYear()+1900; 
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer(); 
		query.append("select r.id from hr_pay_paybill r where r.EMP_ID="+empId+"  and r.paybill_grp_id ");
		query.append("in  (select ph.paybill_id from paybill_head_mpg ph where ph.bill_no in "); 
		query.append("("+billNo+")"); 
		query.append(" and ph.paybill_month="+month);
		query.append(" and ph.paybill_year="+year+")");
		//query.append("and ph.approve_flag in(4))");
		
		logger.info("Query for get getPKOfHrPayPaybill is---->>>>"+query.toString());
		Query sqlQuery=hibSession.createSQLQuery(query.toString());										
		resList=sqlQuery.list();
			
	return resList;
		
	}
	public List getPostIdUserId(long empID)
	{
		List resList=null;
		
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer(); 
		query.append("select  post.post_id , post.user_id from ORG_USERPOST_RLT post,ORG_EMP_MST org where org.USER_ID=post.USER_ID and org.EMP_ID="+empID);
		logger.info("Query for get getPostId is---->>>>"+query.toString());
		Query sqlQuery=hibSession.createSQLQuery(query.toString());										
		resList=sqlQuery.list();
			
	return resList;
	}
		//ended by manish
	
	
}
