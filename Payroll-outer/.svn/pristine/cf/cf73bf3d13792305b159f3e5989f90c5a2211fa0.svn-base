package com.tcs.sgv.onlinebillprep.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.onlinebillprep.valueobject.ConfigOnlineBill;

public class ConfigOnlineBillUtil
{
	
    /* Global Variable for Logger Class */
    private static Log gLogger = LogFactory.getLog(ConfigOnlineBillUtil.class);

    private static ConfigOnlineBill lInstanceConfigOnlineBill = null;

    public static ConfigOnlineBill getInstance(Long lLngSubjectId, ServiceLocator serv)
    {
        if (lInstanceConfigOnlineBill == null || lInstanceConfigOnlineBill.getSubjectId() != lLngSubjectId)
        {
            populateConfigOnlineBillVO(lLngSubjectId, serv.getSessionFactory());
        }
        return lInstanceConfigOnlineBill;
    }

    private static void populateConfigOnlineBillVO(Long lLngSubjectId, SessionFactory lObjSessionFactory)
    {
        StringBuilder lObjStr = new StringBuilder();
        Session lObjSession = lObjSessionFactory.getCurrentSession();
        List lLstData = null;

        try
        {
            lObjStr.append(" from ConfigOnlineBill where  subjectId = :subjectId ");

            Query lQuery = lObjSession.createQuery(lObjStr.toString());
            lQuery.setParameter("subjectId", lLngSubjectId);

            lLstData = lQuery.list();

            if (lLstData != null && lLstData.size() > 0)
            {
                lInstanceConfigOnlineBill = (ConfigOnlineBill) lLstData.get(0);
            }
            
            gLogger.info("populateConfigOnlineBillVO called");
        }
        catch (Exception e)
        {
        	gLogger.error("Error in populateConfigOnlineBillVO. Error is : " + e, e);
        }
    }
}
