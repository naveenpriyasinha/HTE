package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPayArrearPaybill;


public class ArrearReceiptDAOImpl extends GenericDaoHibernateImpl<HrPayArrearPaybill, Long>
{
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");

	public ArrearReceiptDAOImpl(Class<HrPayArrearPaybill> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getArrearReceiptData(long salRevOrder,long billNo,long orgEmpId,long locId)
	{
		List arrearReceiptDataList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer lsb =new StringBuffer();	    

		lsb.append(" 	select ar.psr_no,	 ");
		lsb.append(" 	       max(s.rev_order_no),	 ");
		lsb.append(" 	       g.gpf_acc_no,	 ");
		lsb.append(" 	       ph.paybill_month,	 ");
		lsb.append(" 	       ph.paybill_year,	 ");
		lsb.append(" 	       max(p.proof_num),	 ");
		lsb.append(" 	       concat(o.emp_prefix,	 ");
		lsb.append(" 	              concat(' ',	 ");
		lsb.append(" 	                     concat(concat(concat(o.emp_fname, ' '),	 ");
		lsb.append(" 	                                   concat(o.emp_mname, ' ')),	 ");
		lsb.append(" 	                            concat(o.emp_lname, ' ')))),	 ");
		lsb.append(" 	       max(bh.bill_no),	 ");
		lsb.append(" 	       sum(ar.po + ar.pe),	 ");
		lsb.append(" 	       sum(ar.it),	 ");
		lsb.append(" 	       sum(ar.pt),	 ");
		lsb.append(" 	       sum(ar.da_gpf + ar.da_gpfiv + ar.gpf_c + ar.gpf_iv + ar.ais_pf +	 ");
		lsb.append(" 	           ar.cpf),	 ");
		lsb.append(" 	       sum(ar.net_total),	 ");
		lsb.append(" 	       max(d.dsgn_name),	 ");
		lsb.append(" 	       tr.token_num,	 ");
		lsb.append(" 	       tr.updated_date,	 ");
		lsb.append(" 	       round(tr.bill_net_amount)	 ");
	       
		lsb.append(" 	  from hr_pay_arrear_paybill   ar,	 ");
		lsb.append(" 	       hr_eis_emp_mst          e,	 ");
		lsb.append(" 	       org_emp_mst             o left outer join hr_eis_proof_dtl p on p.user_id = o.user_id,	 ");
		lsb.append(" 	       hr_pay_sal_rev_mst      s,	 ");
		lsb.append(" 	       hr_pay_gpf_details      g,	 ");
		lsb.append(" 	       paybill_head_mpg        ph,	 ");
		lsb.append(" 	       hr_pay_bill_subhead_mpg bh,	 ");
		lsb.append(" 	       org_post_details_rlt    pd,	 ");
		lsb.append(" 	       org_designation_mst     d,	 ");
		lsb.append(" 	       paybill_billreg_mpg     pg,	 ");
		lsb.append(" 	       trn_bill_register       tr	 ");
		lsb.append(" 	 where ar.paybill_grp_id = ph.paybill_id and	 ");
		lsb.append(" 	       ph.bill_no = bh.bill_subhead_id and ph.bill_type_id = "+payrollBundle.getString("arrearbillTypeId")+" and	 ");

		if(salRevOrder>0)
			lsb.append(" ar.sal_rev_id = "+salRevOrder+" and	 ");

		if(locId>0)
			lsb.append(" ar.loc_id = "+locId+" and ");

		lsb.append(" 	       ph.approve_flag in (0, 1) and  ");

		if(billNo>0)
			lsb.append(" bh.bill_subhead_id = "+billNo+" and	 ");

		if(orgEmpId>0)
			lsb.append(" o.emp_id = "+orgEmpId+" and	 ");

		lsb.append(" 	               ph.bill_type_id = "+payrollBundle.getString("arrearbillTypeId")+" and ph.approve_flag in (0, 1) and " );	    


		lsb.append(" 	       ph.bill_category = o.grade_id and ar.emp_id = e.emp_id and	 ");
		lsb.append(" 	       e.emp_mpg_id = o.emp_id and s.sal_rev_id = ar.sal_rev_id and	 ");
		lsb.append(" 	       g.user_id = o.user_id  ");
		lsb.append(" 	       and d.dsgn_id = pd.dsgn_id  ");
		lsb.append(" 	       and pd.post_id = ar.post_id ");		
		lsb.append(" 	       and tr.bill_no = pg.trn_bill_id ");		
		lsb.append(" 	       and pg.paybill_id = ph.paybill_id ");		
		
		lsb.append(" 	 group by ar.psr_no,	 ");
		lsb.append(" 	          concat(o.emp_prefix,	 ");
		lsb.append(" 	                 concat(' ',	 ");
		lsb.append(" 	                        concat(concat(concat(o.emp_fname, ' '),	 ");
		lsb.append(" 	                                      concat(o.emp_mname, ' ')),	 ");
		lsb.append(" 	                               concat(o.emp_lname, ' ')))),	 ");
		lsb.append(" 	          ph.paybill_month,	 ");
		lsb.append(" 	          ph.paybill_year,	 ");
		lsb.append(" 	          g.gpf_acc_no,	 ");
		lsb.append(" 	          tr.token_num,	 ");
		lsb.append(" 	          tr.updated_date,	 ");
		lsb.append(" 	          tr.bill_net_amount	 ");
		lsb.append(" 	 order by ar.psr_no	 ");

		Query query= hibSession.createSQLQuery(lsb.toString());
		arrearReceiptDataList = query.list();

		return arrearReceiptDataList;
	}

	
	 public List getSignaturebyMonthandLocId(long locId,int month,int year)
	 {
			Session hibSession = getSession();
			StringBuffer strQuery = new StringBuffer();
			
			Calendar cal = Calendar.getInstance();
			DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
		    SimpleDateFormat sdfObj = sbConf.GetDateFormat();
			
			if(month>0 && year>0)
			{	
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month-1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			}
			java.util.Date startMonthDate = cal.getTime();
			
			String startDate  = sdfObj.format(startMonthDate);
			
	        int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        
	        cal.set(Calendar.DAY_OF_MONTH, totalDays);
	        
	        java.util.Date endMonthDate = cal.getTime();

			String endDate  = sdfObj.format(endMonthDate);
			
			Query query;
			
			
			strQuery.append(" select sgn.hrEisEmpMst.empId, ");
			strQuery.append(" sgn.orgDesignationMst.dsgnId, ");
			strQuery.append(" sgn.orgDesignationMst.dsgnName, ");
			strQuery.append(" concat(concat(concat(sgn.hrEisEmpMst.orgEmpMst.empFname, concat(' ',sgn.hrEisEmpMst.orgEmpMst.empMname)),' '),sgn.hrEisEmpMst.orgEmpMst.empLname), ");
			strQuery.append(" sgn.cmnLocationMst.locName ");
			strQuery.append("  from HrPaySignatureDtls sgn ");
			strQuery.append(" where sgn.cmnLocationMst.locId = "+locId+" and ");
			strQuery.append(" (sgn.startDate <= '"+endDate+"' and (sgn.endDate >= '"+startDate+"' or  sgn.endDate is null )) ");
			query = hibSession.createQuery(strQuery.toString());
			List resultList = query.list();

			return resultList;
		}
	

}
