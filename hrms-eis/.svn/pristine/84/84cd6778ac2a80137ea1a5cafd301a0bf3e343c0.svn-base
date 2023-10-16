package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;


public class DesigMasterDAO extends GenericDaoHibernateImpl<OrgDesignationMst, Long>  {
	Log logger = LogFactory.getLog(getClass());
	public DesigMasterDAO(Class<OrgDesignationMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getAllDesgMasterData()
    {
		List desig_mst=new ArrayList();
        Session hibSession = getSession();
        String hql="from OrgDesignationMst a where  a.activateFlag=1 ";
        Query myQuery=hibSession.createQuery(hql);
        desig_mst=myQuery.list();
        return desig_mst;
    }
	
	public List getDesigName(String ElementCode,long langId)
    {
        Criteria objCrt = null;
		try
        {
            Session hibSession = getSession();
            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
            objCrt = hibSession.createCriteria(OrgDesignationMst.class);	
			objCrt.add(Restrictions.eq("dsgnCode",ElementCode));
			objCrt.add(Restrictions.eq("cmnLanguageMst",cmnLanguageMst));
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
	
	public List getAllDesgMasterData(long langId)
    {
		List desig_mst=new ArrayList();
		Session hibSession = getSession();
        String hql="from OrgDesignationMst a where a.activateFlag=1 and a.cmnLanguageMst.langId="+langId + "order by a.dsgnName";
        Query myQuery=hibSession.createQuery(hql);
        desig_mst=myQuery.list();
        return desig_mst;
    }
	//by manoj for vacant post issue
	public List getAllDesgMasterData(long langId,long deptId)
    {
		List desig_mst=new ArrayList();
		Session hibSession = getSession();
        String hql="from OrgDesignationMst a where  a.activateFlag=1 and a.cmnLanguageMst.langId="+langId + "order by a.dsgnName";
        Query myQuery=hibSession.createQuery(hql);
        desig_mst=myQuery.list();
        return desig_mst;
    }
	public OrgDesignationMst getDesigIdData(long dsgnId)
    {
		OrgDesignationMst DesigMst = new OrgDesignationMst();
        Session hibSession = getSession();
        String query1 = "from OrgDesignationMst as DesigLookup where DesigLookup.dsgnId = "
                    + dsgnId + " ) ";
        Query sqlQuery1 = hibSession.createQuery(query1);
        DesigMst = (OrgDesignationMst)sqlQuery1.uniqueResult();
        return DesigMst;
    }
	public List<OrgDesignationMst> getAllDesigDescData(String dname)
    {
		List DesigMst=new ArrayList();
        Session hibSession = getSession();
        String query1 = "from OrgDesignationMst as DesigLookup where lower(DesigLookup.dsgnName) = lower('"
                    + dname + "') ) ";
        Query sqlQuery1 = hibSession.createQuery(query1);
        DesigMst = sqlQuery1.list();
        return DesigMst;
    }
	
}
