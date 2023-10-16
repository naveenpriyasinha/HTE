package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrForm16Dtls;

public class HrForm16DtlsDaoImpl extends GenericDaoHibernateImpl implements HrForm16DtlsDao {

	public HrForm16DtlsDaoImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		
	}
	public List<HrForm16Dtls> getForm16DetailsFromDB(long locId){
		 List<HrForm16Dtls> list = new ArrayList<HrForm16Dtls>();
		 
		 Session hibSession = getSession();
			Criteria crit = hibSession.createCriteria(HrForm16Dtls.class);
			
			//crit.add(Restrictions.eq("hrEisEmpMst.cmnLocationMst.locId", locId));
			if(locId==300022)
				crit.add(Restrictions.eq("createdBy.userId", new Long(302333)));
	            else if(locId==300024)
	            	crit.add(Restrictions.eq("createdBy.userId", new Long(320000)));
			
			list = crit.list();
		 
		 return list;
	}
	public List<HrForm16Dtls> getForm16DetailsFromDB(){
		 List<HrForm16Dtls> list = new ArrayList<HrForm16Dtls>();
		 
		 Session hibSession = getSession();
			Criteria crit = hibSession.createCriteria(HrForm16Dtls.class);
			
			list = crit.list();
		 
		 return list;
	}
	public HrForm16Dtls getForm16Rec(String year,long empId){
		logger.info("Inside DaoImpl");
		HrForm16Dtls dtls = new HrForm16Dtls();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrForm16Dtls.class);
		
		crit.add(Restrictions.ilike("finYrId", year));
		crit.add(Restrictions.eq("hrEisEmpMst.empId", empId));
		
		dtls = (HrForm16Dtls) crit.uniqueResult();
		
		return dtls;
		
	}
	public List getEmpData(long empId,int year){
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select sum(pp.gross_amt) gross, " +
				"sum(pp.gpf_c+pp.gpf_iv+pp.gpf_adv+pp.gpf_iv_adv+pp.cpf+pp.ais_pf+pp.da_gpf+pp.da_gpfiv) gpf " +
				"from hr_pay_paybill pp where pp.paybill_grp_id in  " +
				"(select h.paybill_id from paybill_head_mpg h " +
				"where ((h.paybill_year=:Year and h.paybill_month >= 3) or (h.paybill_year=:Nextyear and h.paybill_month <= 2)) " +
				"and h.approve_flag in (0,1) and h.bill_type_id=2500337) and pp.emp_id=:EmpId");
		
		Session session=getSession();
		Query query=session.createSQLQuery(buffer.toString());
		
		query.setParameter("Year", year);
		query.setParameter("Nextyear", (year+1));
		query.setParameter("EmpId", empId);
		
		List list=query.list();
		
		return list;
		
		
	}
	
}
