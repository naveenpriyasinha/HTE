package com.tcs.sgv.eis.dao;
//Comment By Maruthi For import Organisation.
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
import com.tcs.sgv.eis.valueobject.HrEisDeptMst;
public class AddDeptDAOImpl extends GenericDaoHibernateImpl<HrEisDeptMst, Long> implements AddDeptDAO {
	Log logger = LogFactory.getLog(getClass());
	
 	public AddDeptDAOImpl(Class<HrEisDeptMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getAllDeptMasterData()
    {
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrEisDeptMst.class);
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
	public List getAllDeptMasterData(CmnLanguageMst cmnLanguageMst)
    {
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrEisDeptMst.class);
            objCrt.add(Restrictions.eq("cmnLanguageMst",cmnLanguageMst));
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
	
	public List<HrEisDeptMst> getDeptMasterDatabyname(String dname)
    {
		List deptMst=new ArrayList();
        try
        {
            Session hibSession = getSession();
            String query1 = "from HrEisDeptMst as a where lower(a.deptName) like '"
                    + dname + "' ) ";
            Query sqlQuery1 = hibSession.createQuery(query1);
            deptMst = sqlQuery1.list();
           

        }
        catch (Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
		logger.info("I am in chk=1"+deptMst);
        return deptMst;
    }
	public HrEisDeptMst getDeptMasterData(Long deptId,Long langId)
    {
		HrEisDeptMst hrEisDeptMst = new HrEisDeptMst();
        try
        {
            Session hibSession = getSession();
            String query1 = "from HrEisDeptMst as deptLookup where deptLookup.deptId = "
                    + deptId + " and deptLookup.cmnLanguageMst="+langId;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrEisDeptMst = (HrEisDeptMst)sqlQuery1.uniqueResult();
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return hrEisDeptMst;
    }
	public HrEisDeptMst updateDeptMasterData(HrEisDeptMst hrEisDeptMst)
    {
        try
        {
            Session hibSession = getSession();
            String query1 = "from HrEisDeptMst as deptLookup where empLookup.empId = "
                    + hrEisDeptMst.getDeptId() + " and deptLookup.langId="+hrEisDeptMst.getCmnLanguageMst().getLangId();
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrEisDeptMst = (HrEisDeptMst)sqlQuery1.uniqueResult();
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return hrEisDeptMst;
    }
	public HrEisDeptMst nextId()
    {
		HrEisDeptMst hrEisDeptMst=new HrEisDeptMst();
		try
        {
            Session hibSession = getSession();
            String query1 = "  from  HrEisDeptMst as a where a.deptId=(select  max(b.deptId) from HrEisDeptMst as b) ";
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrEisDeptMst = (HrEisDeptMst)sqlQuery1.uniqueResult();
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return hrEisDeptMst;
    }
	
	
	public List getDeptName()
    {
 		List hrEmpInfo = null;
        try
        {
        	Session hibSession = getSession();
            String query1 = "from HrEisDeptMst";
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrEmpInfo = sqlQuery1.list();
            

        }
        catch (Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return hrEmpInfo;
    }
	public List getDeptName(long ElementCode,long langId)
    {
        Criteria objCrt = null;
		try
        {
            Session hibSession = getSession();
            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
            logger.info("***********************"+ElementCode);
            objCrt = hibSession.createCriteria(HrEisDeptMst.class);	
			objCrt.add(Restrictions.eq("elementCode",ElementCode));
			objCrt.add(Restrictions.eq("cmnLanguageMst",cmnLanguageMst));
			logger.info("returning"+cmnLanguageMst.getLangId());
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
}

