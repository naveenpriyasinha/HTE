package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
public class HrEisGdMpgDAOImpl extends GenericDaoHibernateImpl<HrEisGdMpg, Long>{
	Log logger = LogFactory.getLog(getClass());
	public HrEisGdMpgDAOImpl(Class<HrEisGdMpg> type, SessionFactory sessionFactory) 
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	
	public List getDuplicateData(long gradeId,long desigId)
	{
		logger.info("DesidId is:::::::::::: " + desigId + "gradeId is:::::: " + gradeId);
		List duplicate = new ArrayList();
		Session hibSession = getSession();
		String hql="from HrEisGdMpg as ab where ab.orgGradeMst.gradeId=" + gradeId +" and ab.orgDesignationMst.dsgnId=" +
		desigId;
        Query myQuery=hibSession.createQuery(hql);
                                
        duplicate = myQuery.list();
        logger.info("Duplicate size:::::::::::::::::::: " + duplicate.size());
        return duplicate;                        
	}

	public List getdesigsfromgrades(long gradeId)
	//public List getDuplicateData(long gradeId)
	{
		List<HrEisGdMpg> listDepts = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"from HrEisGdMpg bhm where bhm.orgGradeMst.gradeId="
						+ gradeId + " Order by bhm.gdMapId" );
		logger.info("Query in getAllDataFromBillId is------>>>>>> "
				+ strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		listDepts = query.list();
		return listDepts;
        
	}
	
	
}
