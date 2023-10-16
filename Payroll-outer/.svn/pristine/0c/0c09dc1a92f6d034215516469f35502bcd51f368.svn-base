package com.tcs.sgv.pension.dao;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionBillArrearDtls;

public class TrnPensionBillArrearDtlsDAOImpl extends GenericDaoHibernateImpl<TrnPensionBillArrearDtls, Long> implements TrnPensionBillArrearDtlsDAO{
	
    /* Global Variable for Logger Class */
    private Log logger = LogFactory.getLog(getClass());


    /* Global Variable for Session Class */
    
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    
	public TrnPensionBillArrearDtlsDAOImpl(Class<TrnPensionBillArrearDtls> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}  

}
