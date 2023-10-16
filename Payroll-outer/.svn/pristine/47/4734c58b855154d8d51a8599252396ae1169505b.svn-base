package com.tcs.sgv.lcm.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.lcm.valueobject.TrnLcChequePosting;


public class LcChequePostingDAOImpl 
extends GenericDaoHibernateImpl implements LcChequePostingDAO 
{
	private static Log logger = LogFactory.getLog(LcChequePostingDAO.class);
	public LcChequePostingDAOImpl(Class<TrnLcChequePosting> type,SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory); 	 		
	}
	
	public TrnLcChequePosting getChequeByNo(BigDecimal chequeNo) {
		TrnLcChequePosting chqDtls = null;
		try {
			Session hibSession = getSession();
			logger.info("Cheque NO: " + chequeNo);
			
			List resList = hibSession.createCriteria(TrnLcChequePosting.class).add(Expression.eq("chequeNo", chequeNo)).list();
			logger.info("xxxxxxxxxxxx " + resList.size());
			if (resList!=null && resList.size()>0) {
				chqDtls = (TrnLcChequePosting) resList.get(0);
				logger.info("sssssssssssssss-------"+chqDtls.getChequeAmt());
			}
		} catch(Exception ex) {
				ex.printStackTrace();
			logger.error("Exception occured :\n"+ex);
		}
		return chqDtls;
	}
}
