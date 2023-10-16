package com.tcs.sgv.exprcpt.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.exprcpt.valueobject.WebChallanDetails;


public class WebChallanDtlsDAO extends GenericDaoHibernateImpl<WebChallanDetails, String> 
{

    public WebChallanDtlsDAO(Class<WebChallanDetails> type, SessionFactory sessionFactory) 
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
    
}