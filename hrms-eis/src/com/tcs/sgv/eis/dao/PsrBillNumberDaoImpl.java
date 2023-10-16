package com.tcs.sgv.eis.dao;

import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;

	public class PsrBillNumberDaoImpl extends GenericDaoHibernateImpl<HrPayPsrPostMpg, Long> 
	{
		
		public PsrBillNumberDaoImpl(Class<HrPayPsrPostMpg> type, SessionFactory sessionFactory) 
		{
	        super(type);
	        setSessionFactory(sessionFactory);
	    }
		DBsysdateConfiguration queryConf=new DBsysdateConfiguration();
		String sdate=queryConf.getSysdate();
		ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
		
		public List getDetailListForDisplay(long locId,long langId, String Bill, String Psr, String Dsgn)
		{			
			List resList=null;
			
				Session hibSession = getSession();
				StringBuffer query = new StringBuffer(); 
				query.append("  select (select concat(concat(concat(o.emp_fname, ' '), concat(o.emp_mname, ' ')),concat( o.emp_lname,' ')) from org_emp_mst o, org_userpost_rlt up ");
				query.append("  where  o.lang_id = 1 and o.user_id = up.user_id and up.post_id = pd.post_id and up.end_date is null and up.activate_flag = 1),ds.dsgn_name , P.PSR_NO ");
				if(Bill != null && !(Bill.trim()).equals(""))
					query.append("  , mp.bill_no, p.id from org_post_details_rlt pd, org_designation_mst ds, HR_PAY_POST_PSR_MPG p left outer join hr_pay_bill_subhead_mpg mp on p.bill_no = mp.bill_subhead_id ");        
				else
					query.append("  , (select mp.bill_no  from hr_pay_bill_subhead_mpg mp where  p.bill_no = mp.bill_subhead_id) BillNo , p.id from org_post_details_rlt pd, org_designation_mst ds , HR_PAY_POST_PSR_MPG p  " );
				query.append("  where pd.loc_id = '" + locId +"' and P.POST_ID = PD.POST_ID and  pd.dsgn_id = ds.dsgn_id and ds.lang_id = " + langId  );
				if(Psr != null && !(Psr.trim()).equals(""))
					query.append("  and p.psr_no = " + Psr );
				else if(Bill != null && !(Bill.trim()).equals(""))
					query.append("  and mp.bill_no  = " + Bill );
				else if(Dsgn != null && !(Dsgn.trim()).equals(""))
					query.append("  and  upper(ds.dsgn_name) like  upper('%" + Dsgn+ "%')  ");
				query.append("   order by  p.psr_no ");
				logger.info("Query for get getBillNoData is---->>>>"+query.toString());
				Query sqlQuery=hibSession.createSQLQuery(query.toString());										
				resList=sqlQuery.list();
					
			return resList;
		}
		public List getEmpDtls(long locId,String empId, long langId)
		{			
			List resList=null;
			
				Session hibSession = getSession();
				StringBuffer query = new StringBuffer(); 
				query.append(" select concat(concat(concat(o.emp_fname, ' '), concat(o.emp_mname, ' ')), concat(o.emp_lname, ' ')), ds.dsgn_name, p.psr_no, (select mp.bill_no  from hr_pay_bill_subhead_mpg mp  ");
				query.append(" where  p.bill_no = mp.bill_subhead_id) BillNo , p.id from org_emp_mst o,  org_userpost_rlt  up,  HR_PAY_POST_PSR_MPG p , org_post_details_rlt    pd, org_designation_mst     ds  where o.lang_id = 1 and o.user_id = up.user_id  ");
				query.append(" and up.post_id = pd.post_id and pd.post_id = p.post_id and  pd.loc_id = p.loc_id ");
				query.append(" and o.emp_id = "+ empId +" and  pd.loc_id = " +locId + " and pd.dsgn_id = ds.dsgn_id and ds.lang_id = " + langId + " order by pd.post_name ");
				logger.info("Query for get getBillNoData is---->>>>"+query.toString());
				Query sqlQuery=hibSession.createSQLQuery(query.toString());										
				resList=sqlQuery.list();
					
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
		public List checkPsrNumber (String NewPsrNumber, long locId)
	    {
			List resList=null;
        	logger.info("inside checkPsrNumber ---------------");
        	Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append("select * from org_post_details_rlt pd,HR_PAY_POST_PSR_MPG  pp  where pp.post_id = pd.post_id and pp.loc_id = pd.loc_id  and pp.loc_id = " + locId + " and pp.psr_no = " + NewPsrNumber );
			logger.info("Query for get getBillNoData is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
			return resList;
	    }
		public List getPayslipData() {
			// TODO Auto-generated method stub
			return null;
		}


		
		
		
	}