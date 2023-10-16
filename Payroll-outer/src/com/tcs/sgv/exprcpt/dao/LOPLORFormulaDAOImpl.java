package com.tcs.sgv.exprcpt.dao;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.exprcpt.valueobject.MstListPayRcpt;

public class LOPLORFormulaDAOImpl extends GenericDaoHibernateImpl<MstListPayRcpt,BigDecimal>
{
		Log logger = LogFactory.getLog(getClass());
		
		public LOPLORFormulaDAOImpl(Class<MstListPayRcpt> type, SessionFactory sessionFactory)
	    {
	        super(type);
	        setSessionFactory(sessionFactory);
	    }

}
