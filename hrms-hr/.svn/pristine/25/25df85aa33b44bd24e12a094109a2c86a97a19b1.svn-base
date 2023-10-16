package com.tcs.sgv.hr.payincrement.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModEmpRlt;
import com.tcs.sgv.hr.payincrement.valueobject.HrEisPayInc;
import com.tcs.sgv.hr.payincrement.valueobject.HrPayincTxn;

/**
 * @author 218914 dao for second stage search to fill the history table
 */
public class PayIncrementDaoImp extends GenericDaoHibernateImpl implements PayIncrementDao {
	public PayIncrementDaoImp(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	/* method to get data from transaction table */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncrementDao#displaydetails9(long)
	 */
	public List displaydetails9(long reqid) {
		Criteria crit = null;
		Session hibSession = getSession();
		crit = hibSession.createCriteria(HrPayincTxn.class);
		crit.add(Restrictions.eq("reqTranId", reqid));
	
		
		
		

		return crit.list();

	}

	/* method to get data from history table */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncrementDao#displaydetails10(long)
	 */
	public List displaydetails10(long reqid) {
		Criteria crit = null;
	
		Session hibSession = getSession();
		crit = hibSession.createCriteria(HrModEmpRlt.class);
		crit.add(Restrictions.eq("reqId", reqid));
		crit.add(Restrictions.eq("hrModMst.modId",8730l));
	
		
		

		return crit.list();
	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncrementDao#actincdate(long)
	 */
	public List actincdate(long userid) {
		Criteria crit = null;
		Session hibSession = getSession();
		crit = hibSession.createCriteria(HrEisPayInc.class);
		crit.add(Restrictions.eq("userId", userid));
	
	
		
		

		return crit.list();
	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncrementDao#actualincdate_lwp(java.util.Date, int)
	 */
	public Date actualincdate_lwp(Date date, int days) {
		int nxtdays = date.getDate();
		int nxtmont = date.getMonth();
		int nxtyear = date.getYear();
		

		return null;
	}

}