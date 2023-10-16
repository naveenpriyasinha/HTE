package com.tcs.sgv.eis.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import net.sf.hibernate.Criteria;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.DemoViewVO;

public class DemoViewDAOImpl extends GenericDaoHibernateImpl{
	
	public DemoViewDAOImpl(Class<DemoViewVO> type,SessionFactory sessionFactory) {
		super(type);
		// TODO Auto-generated constructor stub
		setSessionFactory(sessionFactory);
	}
	Log logger = LogFactory.getLog( getClass() );

	public List getDemoDisplayValues()
	{
		logger.info("Within the DemoViewDAOImpl");
		List list = null;
		Query query;
		String strquery = "SELECT DISTRICT_ID,DISTRICT_NAME FROM CMN_DISTRICT_MST where STATE_ID=15";
		
		Session hibsession = getSession();
		query = hibsession.createSQLQuery(strquery);
		logger.info("The SQL query :"+query);
		list = query.list();
		
		return list;
 	}
	
	

}
