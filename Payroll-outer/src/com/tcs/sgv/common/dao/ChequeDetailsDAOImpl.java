package com.tcs.sgv.common.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;

import com.tcs.sgv.billproc.cheque.valueobject.TrnChequeDtls;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;


public class ChequeDetailsDAOImpl  extends GenericDaoHibernateImpl<TrnChequeDtls,Long> implements ChequeDetailsDAO {

	Log logger = LogFactory.getLog(TrnChequeDtls.class);
	
	public ChequeDetailsDAOImpl(Class<TrnChequeDtls> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	/**
	 * This method returns cheque details value object by cheque no
	 * @param chequeNo Cheque no.
	 * @return TrnChequeDtls
	 */
	public TrnChequeDtls getChequeDtlByNo(Long chequeNo) {
		TrnChequeDtls trnChequeDtls = null;
		try {
			Session hibSession = getSession();
			List resList = hibSession.createCriteria(TrnChequeDtls.class).add(Expression.eq("chequeNo", chequeNo)).list();
			if(resList!=null && resList.size()>0){
				trnChequeDtls = (TrnChequeDtls) resList.get(0);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error("Exception occurred in ChequeDetailsDAOImpl.getChequeDtlByNo #\n"+ex);
		}
		return trnChequeDtls;
	}

		
}