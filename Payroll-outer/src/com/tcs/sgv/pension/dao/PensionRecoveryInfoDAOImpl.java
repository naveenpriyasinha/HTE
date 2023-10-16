package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.common.valueobject.MstEdp;

/**
 * Recovery specific DAO Implementation
 * 
 * @author Rupsa Mukherjee
 * @version 1.0
 */
public class PensionRecoveryInfoDAOImpl implements PensionRecoveryInfoDAO
{
    Session ghibSession = null;

    private Log logger = LogFactory.getLog(getClass());


    public PensionRecoveryInfoDAOImpl(SessionFactory sessionFactory)
    {
        ghibSession = sessionFactory.getCurrentSession();
    }    
}
