package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrPayUpdatebillDtls;


public class HrPayUpdatebillDtlsDAO extends GenericDaoHibernateImpl<HrPayUpdatebillDtls, Long>  {
	Log logger = LogFactory.getLog( getClass() );
 	public HrPayUpdatebillDtlsDAO(Class<HrPayUpdatebillDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	String sdate=sbConf.getSysdate();

	
}
