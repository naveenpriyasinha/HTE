package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.admin.valueobject.OrgPostDtl;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;

public class HrEisGdDAOImpl extends GenericDaoHibernateImpl<HrEisGdMpg, Long> implements  HrEisGdDAO
{
	public HrEisGdDAOImpl(Class<HrEisGdMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getDuplicateData(long gradeId,long desigId)
	//public List getDuplicateData(long gradeId)
	{
		List duplicate = new ArrayList();
		Session hibSession = getSession();
        String hql="select * from hr_eis_gd_mpg where sgd_grade_id=gradeId and sgd_desig_id=desigId  ";
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

	
	
	
	/**
	 * author@manish khunt
	 * This method is used to get the HrEuisGdMpg Object from postId
	 * @param postId
	 * @return
	 */
	public HrEisGdMpg getGradeDesigMpg(long postId,long locId)

	{
		
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("select  gd from HrEisGdMpg gd,OrgPostDetailsRlt pd where pd.orgDesignationMst.dsgnId=gd.orgDesignationMst.dsgnId");
		strQuery.append(" and gd.cmnLocationMst.locId=");
		strQuery.append(locId);
		strQuery.append(" and pd.orgPostMst.postId=");
		strQuery.append(postId);
		logger.info("strQuery is ************"+strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		logger.info("strQuery is *****size is*******"+query.list().size());
		return (HrEisGdMpg)query.uniqueResult();
	}
}
