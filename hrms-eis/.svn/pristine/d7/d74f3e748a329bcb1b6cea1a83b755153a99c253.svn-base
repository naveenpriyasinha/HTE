package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrForm16BankMst;

public class HrForm16BankMstDaoImpl extends GenericDaoHibernateImpl implements HrForm16BankMstDao{

	public HrForm16BankMstDaoImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	public List<HrForm16BankMst> getBankData() {
		List<HrForm16BankMst> list = new ArrayList<HrForm16BankMst>();
		
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrForm16BankMst.class);
		
		list = crit.list();
		
		return list;
	}
}
