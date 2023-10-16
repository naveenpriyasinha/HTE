package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.ScaleCommissionMpg;

public class ScaleCommissionMpgDAOImpl extends GenericDaoHibernateImpl<ScaleCommissionMpg, Long> implements ScaleCommissionMpgDAO {

	public ScaleCommissionMpgDAOImpl(Class<ScaleCommissionMpg> type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}//end constr
	public ScaleCommissionMpg getNewScaleFromOldScale(long scaleId)
	{
		List hrPaySalRevMst = null;
        Session hibSession = getSession();
        ScaleCommissionMpg scaleCommissionMpg= new ScaleCommissionMpg();
        String query1 = "from ScaleCommissionMpg  scaleCommissionMpg where scaleCommissionMpg.commissionFive = "+ scaleId ;
        logger.info("Query is +++"+query1.toString());
        Query sqlQuery1 = hibSession.createQuery(query1);
        List list = sqlQuery1.list();
        if(list!= null && list.size()>0)
        	scaleCommissionMpg =(ScaleCommissionMpg)list.get(0);
        
        logger.info("query is----"+sqlQuery1);
        return scaleCommissionMpg;
 

	}
	public ScaleCommissionMpg getOldScaleFromNewScale(long scaleId)
	{
		List hrPaySalRevMst = null;
        Session hibSession = getSession();
        ScaleCommissionMpg scaleCommissionMpg= new ScaleCommissionMpg();
        String query1 = "from ScaleCommissionMpg  scaleCommissionMpg where scaleCommissionMpg.commissionSix = "+ scaleId +
        "and scaleCommissionMpg.commissionSix in (select scaleId from HrEisScaleMst where scaleId="+scaleId+" )";
        logger.info("Query is +++"+query1.toString());
        Query sqlQuery1 = hibSession.createQuery(query1);
        scaleCommissionMpg =(ScaleCommissionMpg)sqlQuery1.list().get(0);
        
        logger.info("query is----"+sqlQuery1);
        return scaleCommissionMpg;
 

	}

}//end class