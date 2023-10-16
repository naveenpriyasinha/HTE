package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.pension.valueobject.TrnPensionItCutDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

/**
 * Cut DAO Implementation.
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */
public class CutDAOImpl implements CutDAO
{
	private Session ghibSession = null;
	private final Log logger = LogFactory.getLog(getClass());
	
    public CutDAOImpl(SessionFactory sessionFactory)
    {
        ghibSession = sessionFactory.getCurrentSession();
    }
}
