package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrForm16Dtls;
import com.tcs.sgv.eis.valueobject.HrForm16TaxDeducDtls;

public class HrForm16TaxDeducDaoImpl extends GenericDaoHibernateImpl implements HrForm16TaxDeducDao {

	public HrForm16TaxDeducDaoImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);

	}
	public List<HrForm16TaxDeducDtls> getDeducDtls(HrForm16Dtls form16Dtls){
		
		List<HrForm16TaxDeducDtls> dtls = new ArrayList<HrForm16TaxDeducDtls>();
		
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrForm16TaxDeducDtls.class);
		
		crit.add(Restrictions.eq("form16DtlId", form16Dtls));
		
		dtls =  crit.list();
		
		return dtls;
		
	}
}
