package com.tcs.sgv.eis.dao;

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

import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
public class getGradDesgMapDAO extends GenericDaoHibernateImpl<HrEisGdMpg, Long>  {
	Log logger = LogFactory.getLog(getClass());
	
 	public getGradDesgMapDAO(Class<HrEisGdMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getAllMasterData()
    {
        Criteria objCrt = null;
        try {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrEisGdMpg.class);
            
            objCrt.setFetchMode("orgGradeMst",FetchMode.JOIN);
            objCrt.createAlias("orgGradeMst","orgGrade");
            
            objCrt.setFetchMode("orgDesignationMst",FetchMode.JOIN);
            objCrt.createAlias("orgDesignationMst","orgDesig");
            
            objCrt.addOrder(Order.asc("orgGrade.gradeName"));
            objCrt.addOrder(Order.asc("orgDesig.dsgnName"));
       }
        catch(Exception e) {
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
	
	public HrEisGdMpg getGDMapFromGD(OrgGradeMst orgGradeMst,OrgDesignationMst orgDesignationMst,CmnLocationMst cmnLocationMst) {
        Criteria objCrt = null;
        HrEisGdMpg hrEisGdMpg = new HrEisGdMpg();
        hrEisGdMpg.setGdMapId(0);
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrEisGdMpg.class);
        objCrt.add(Restrictions.eq("orgGradeMst", orgGradeMst));
        objCrt.add(Restrictions.eq("orgDesignationMst", orgDesignationMst));
        objCrt.add(Restrictions.eq("cmnLocationMst", cmnLocationMst));
        if(objCrt.list()!=null&&objCrt.list().size()>0)
        hrEisGdMpg = (HrEisGdMpg)objCrt.uniqueResult();       
        return hrEisGdMpg;
    }
	
 	public HrEisGdMpg getGradeDesgMasterData(Long gdMapId)
    {
 		HrEisGdMpg hrEisGdMpg = new HrEisGdMpg();
        try
        {
            Session hibSession = getSession();
            String query1 = "from HrEisGdMpg as a where a.gdMapId ="+gdMapId;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrEisGdMpg = (HrEisGdMpg)sqlQuery1.uniqueResult();
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return hrEisGdMpg;
    }
}

