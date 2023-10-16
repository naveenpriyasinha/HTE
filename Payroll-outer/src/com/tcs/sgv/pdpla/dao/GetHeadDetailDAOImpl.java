package com.tcs.sgv.pdpla.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pdpla.valueobject.MstPdAccount;



public  class GetHeadDetailDAOImpl extends GenericDaoHibernateImpl<MstPdAccount, BigDecimal> implements GetHeadDetailDAO 
{
	Log gLogger = LogFactory.getLog(getClass());	
	Session ghibSession = null;
	
	public GetHeadDetailDAOImpl(Class<MstPdAccount> type, SessionFactory sessionFactory)
    {
		super(type);
        setSessionFactory(sessionFactory);
    }
	
	public Map getMajorHead(String lPdPlaAccountNo)
    {
		List qList = null;
		Map HeadListMap=null;
        try
        {
            Session hibSession = getSession();
 			Query sqlQuery=hibSession.createQuery("SELECT A.pdMjrhd,A.pdMinhd,A.deptName FROM MstPdAccount A WHERE A.accountNo ='"+lPdPlaAccountNo+"'");
            qList = (List)sqlQuery.list();
            if ( qList!=null &&  qList.size()>0) 
			{
            	HeadListMap=new HashMap();
				Iterator it =  qList.iterator();
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					HeadListMap.put("mjrhead", tuple[0]);
					HeadListMap.put("mnrhead", tuple[1]);
					HeadListMap.put("deptName", tuple[2]);
				}
			}
        }
        catch (Exception e)
        {
        	gLogger.error("Error in getGrantAmountForDDO and Error is : " + e,e);
        }
        return HeadListMap;
    }

	
}
