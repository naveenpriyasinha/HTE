package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrItSectionMst;


public class SectionMasterDAOImpl extends GenericDaoHibernateImpl<HrItSectionMst, Long> implements SectionMasterDAO {

	Log logger = LogFactory.getLog(getClass());
 	public SectionMasterDAOImpl(Class<HrItSectionMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	public List getAllSectionData()
    {
        Criteria objCrt = null;
        
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrItSectionMst.class);
        
        
        return objCrt.list();
    }
 	
 	public HrItSectionMst getSectionDataBySectId(String sectid)
    {
 		HrItSectionMst hrItSectionMst = new HrItSectionMst();
        
            Session hibSession = getSession();
            String query1 = "from HrItSectionMst as a where a.sectId = "
                    + sectid;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrItSectionMst = (HrItSectionMst)sqlQuery1.uniqueResult();
            logger.info("The query is----->"+sqlQuery1);
            
        
        
        return hrItSectionMst;
    }
 	
 	public HrItSectionMst getSectionDataByActivateFlag(String statusid)
    {
 		HrItSectionMst hrItSectionMst = new HrItSectionMst();
        
            Session hibSession = getSession();
            String query1 = "from HrItSectionMst as a where a.activateFlag = "
                    + statusid;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrItSectionMst = (HrItSectionMst)sqlQuery1.uniqueResult();
            logger.info("The query is----->"+sqlQuery1);
            
        
        return hrItSectionMst;
    }
 	
 	public List getSectionDataByLangId(long langId)
    {
 		List list = null;
        
            Session hibSession = getSession();
            String query1 = "from HrItSectionMst as a where cmnLanguageMst.langId = "
                    + langId+" order by a.sectCode";
            Query sqlQuery1 = hibSession.createQuery(query1);
            list = sqlQuery1.list();
            logger.info("The query is----->"+sqlQuery1);
            
       
        
        return list;
    }
}