package com.tcs.sgv.common.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;

import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class BudHeadDAOImpl extends GenericDaoHibernateImpl<TrnBillBudheadDtls,Long> implements BudHeadDAO 
{
	Log logger = LogFactory.getLog(getClass());
	public BudHeadDAOImpl(Class<TrnBillBudheadDtls> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	} 
	
	/**
	 * This method returns value object of TrnBillBudheadDtls by bill no
	 * @param billNo
	 * @return TrnBillBudheadDtls
	 */
	public TrnBillBudheadDtls getBudHdDtlVOByBillNo(long billNo) {
		TrnBillBudheadDtls budHdDtl = null;
		try{
			Session hibSession = getSession();
			List resList = hibSession.createCriteria(TrnBillBudheadDtls.class).add(Expression.eq("billNo", billNo)).list();
			if (resList!=null && resList.size()>0) {
				budHdDtl = (TrnBillBudheadDtls) resList.get(0);	
			}
		} catch(Exception e) {
			logger.error("Exception occured in BudHeadDAOImpl.getBudHdDtlVOByBillNo #\n"+e);
		}
		return budHdDtl;
	}
}
