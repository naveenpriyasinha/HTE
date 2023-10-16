package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;



public class GradeMasterDAO extends GenericDaoHibernateImpl<OrgGradeMst, Long>{
	Log logger = LogFactory.getLog(getClass());
	public GradeMasterDAO(Class<OrgGradeMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getAllGradeMasterData()
    {
		List grade_mst=new ArrayList();
        Session hibSession = getSession();
        String hql="from OrgGradeMst";
        Query myQuery=hibSession.createQuery(hql);
        grade_mst=myQuery.list();
        return grade_mst;
    }
	
	public List getGradeName(String ElementCode,long langId)
    {
        Criteria objCrt = null;
		try
        {
            Session hibSession = getSession();
            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
            logger.info("***********************"+ElementCode);
            objCrt = hibSession.createCriteria(OrgGradeMst.class);	
			objCrt.add(Restrictions.eq("gradeCode",ElementCode));
			objCrt.add(Restrictions.eq("cmnLanguageMst",cmnLanguageMst));
			logger.info("returning"+cmnLanguageMst.getLangId());
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }

	public List getAllGradeMasterData(CmnLanguageMst cmnLanguageMst)
    {
        Criteria objCrt = null;
        Session hibSession = getSession();        
        objCrt = hibSession.createCriteria(OrgGradeMst.class);
        objCrt.add(Restrictions.eq("cmnLanguageMst",cmnLanguageMst));       
        objCrt.addOrder(Property.forName("gradeName").asc());
        return objCrt.list();
    }
	
	public OrgGradeMst getGradeIdData(long gradeId)
    {
		OrgGradeMst GradeMst = new OrgGradeMst();
        Session hibSession = getSession();
        String query1 = "from OrgGradeMst as GradeLookup where GradeLookup.gradeId = "
                    + gradeId + " ) ";
        Query sqlQuery1 = hibSession.createQuery(query1);
        GradeMst = (OrgGradeMst)sqlQuery1.uniqueResult();
        return GradeMst;
    }

	public List<OrgGradeMst> getAllGradeDescData(String gname)
    {
		List GradeMst=new ArrayList();
        Session hibSession = getSession();
        String query1 = "from OrgGradeMst as GradeLookup where lower(GradeLookup.gradeName) like lower('"
                    + gname + "') ) ";
        Query sqlQuery1 = hibSession.createQuery(query1);
        GradeMst = sqlQuery1.list();
        return GradeMst;
    }
	
	//Added by Abhilash for duplicate msg purpose
	public List getDuplicateData(Long gradeId,Long desigId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from HrEisGdMpg gd where gd.orgGradeMst.gradeId ="  + gradeId + "  and gd.orgDesignationMst.dsgnId=" + desigId ;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("GradeMasterDAO  getBudSubHdId queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	//added by manish for making Gd Mpg location Specific
	public List getDuplicateData(Long gradeId,Long desigId,long locId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from HrEisGdMpg gd where gd.orgGradeMst.gradeId ="  + gradeId + "  and gd.orgDesignationMst.dsgnId=" + desigId +" and gd.cmnLocationMst.locId="+locId;
            Query query = hibSession.createQuery(strQuery);
           logger.info("roshan query is ****************"+strQuery.toString());
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("GradeMasterDAO  getBudSubHdId queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	//ended by manish 
	
	/**
	 * author@manis khunt 
	 * for finding gd mpg
	 * @param desigId
	 * @param locId
	 * @return
	 */
	public List getGDMpgByDesigLoc(Long desigId,long locId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
            String strQuery = "from HrEisGdMpg gd where  gd.orgDesignationMst.dsgnId=" + desigId +" and gd.cmnLocationMst.locId="+locId;
            Query query = hibSession.createQuery(strQuery);
            list = query.list();
        return list;
    }
}
