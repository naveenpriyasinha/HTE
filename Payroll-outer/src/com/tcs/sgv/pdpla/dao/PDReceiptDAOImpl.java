package com.tcs.sgv.pdpla.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChallanDetail;

public class PDReceiptDAOImpl extends GenericDaoHibernateImpl<TrnPdChallanDetail,Long> implements PDReceiptDAO
{
	Log gLogger = LogFactory.getLog(getClass());
	//Session ghibSession = null;
	
	public PDReceiptDAOImpl(Class<TrnPdChallanDetail> type, SessionFactory sessionFactory)
    {
		super(type);
        setSessionFactory(sessionFactory);
    }
	
	public Map getSequence(String lPdPlaAccountNo)
	{
		List qList = null;
		Map HeadListMap=null;
		
		try
        {
            Session hibSession = getSession();
 			Query sqlQueryCount = hibSession.createQuery("SELECT COUNT(*) FROM TrnPdChallanDetail A WHERE MONTHS_BETWEEN A.ReceiptDate AND "+lPdPlaAccountNo+"= 0");
            qList = (List)sqlQueryCount.list();
            if ( qList!=null &&  qList.size()>0) 
			{
            	Query sqlQueryId = hibSession.createQuery("SELECT MAX(A.ReceipId)FROM TrnPdChallanDetail A");
            	qList = (List)sqlQueryId.list();
                if ( qList!=null &&  qList.size()>0) 
    			{
                	
                	Object[] objList = (Object[]) qList.get(0);
                	int x = new Integer(objList[0].toString()).intValue();
                	//System.out.println("@@@@@@@@@@@" + x);
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
