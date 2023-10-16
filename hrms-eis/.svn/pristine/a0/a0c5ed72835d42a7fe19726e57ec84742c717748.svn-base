package com.tcs.sgv.eis.dao;
//Comment By Maruthi For import Organisation.
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEmpPunishmentDtls;


public class empPunishmentDAOImpl extends GenericDaoHibernateImpl<HrEmpPunishmentDtls, Long>  {

	Log logger = LogFactory.getLog(getClass());
 	public empPunishmentDAOImpl(Class<HrEmpPunishmentDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	
 	public List getAllPunishmentData(String locId)
    {
        Criteria objCrt = null;
       
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrEmpPunishmentDtls.class);
            objCrt.createAlias("cmnLocationMst", "cmnLocationMst");
            objCrt.add(Restrictions.like("cmnLocationMst.locationCode",locId));
                  
        return objCrt.list();
    }
 	
 	public HrEmpPunishmentDtls getPunishmentData(Long pmtId)
    {
 		HrEmpPunishmentDtls pmtInfo = new HrEmpPunishmentDtls();
      
            Session hibSession = getSession();
            String query1 = "from HrEmpPunishmentDtls p where p.punishmentId = "
                    + pmtId;
            Query sqlQuery1 = hibSession.createQuery(query1);
            pmtInfo = (HrEmpPunishmentDtls)sqlQuery1.uniqueResult();
            logger.info("query is----"+sqlQuery1);

       
        return pmtInfo;
    }
 	
 	
 	//added by Ankit Bhatt for checking Emp is Punished or not
 	public boolean isEmpPunished(long empId)
 	{

 		Session hibSession = getSession();
        String query1 = "from HrEmpPunishmentDtls p where p.hrEisEmpMst.empId = "
                + empId;
        Query sqlQuery1 = hibSession.createQuery(query1);
        List pmtInfo = sqlQuery1.list();
        if(pmtInfo!=null && pmtInfo.size()>0 && pmtInfo.get(0)!=null)
        	return true;
        else
        	return false;
 	}
 	//ended
 	
 	public List getAllPunishmentDataByEmpId(long empId)
 	{
 		Session hibSession = getSession();
        String query1 = "from HrEmpPunishmentDtls p where p.hrEisEmpMst.empId = "
                + empId;
        Query sqlQuery1 = hibSession.createQuery(query1);
        List pmtInfo = sqlQuery1.list();
 	
        return pmtInfo;
 	}
 	//added by manish
	public List getPostIdUserId(long empID)
	{
		List resList=null;
		
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer(); 
		query.append("select  post.post_id,post.user_id from ORG_USERPOST_RLT post,ORG_EMP_MST org where org.USER_ID=post.USER_ID and org.EMP_ID="+empID);
		logger.info("Query for get getPostId is---->>>>"+query.toString());
		Query sqlQuery=hibSession.createSQLQuery(query.toString());										
		resList=sqlQuery.list();
			
	return resList;
	}
	//ended by manish
}