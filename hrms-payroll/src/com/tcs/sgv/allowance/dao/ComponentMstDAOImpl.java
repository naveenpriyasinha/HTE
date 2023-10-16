package com.tcs.sgv.allowance.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.allowance.valueobject.HrPayComponentMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;



public class ComponentMstDAOImpl extends GenericDaoHibernateImpl<HrPayComponentMst, Long> implements ComponentMstDAO {

	public ComponentMstDAOImpl(Class <HrPayComponentMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getAllComponentMasterData()
    {
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrPayComponentMst.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return objCrt.list();
    }
	
	public HrPayComponentMst getAllComponentIdData(long componentId)
    {
		HrPayComponentMst ComponentMst = new HrPayComponentMst();
        try
        {
            Session hibSession = getSession();
            String query1 = "from HrPayComponentMst as DesigLookup where DesigLookup.componentCode = "
                    + componentId + " ) ";
            Query sqlQuery1 = hibSession.createQuery(query1);
            ComponentMst = (HrPayComponentMst)sqlQuery1.uniqueResult();
            //System.out.println("componentId Info size is:::::::::" );

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ComponentMst;
    }
	public List<HrPayComponentMst> getAllComponentDescData(String desc)
    {
		List ComponentMst=new ArrayList();
        try
        {
            Session hibSession = getSession();
            String query1 = "from HrPayComponentMst as DesigLookup where DesigLookup.componentDesc = '"
                    + desc + "' ) ";
            //System.out.println("from dao query is "+query1);
            Query sqlQuery1 = hibSession.createQuery(query1);
            ComponentMst = sqlQuery1.list();
            //System.out.println("from dao size of list is "+ComponentMst.size());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ComponentMst;
    }


	
	
	
}
