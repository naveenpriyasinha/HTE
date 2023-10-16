package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

/**
 * CVP & DCRG Bill Common Service Interface.
 * 
 * @author Soumya Adhikari,Aparna Kansara
 * @version 1.0
 */

public class CVPDCRGBillDAOImpl implements CVPDCRGBillDAO
{
	Session ghibSession = null;
	private Log logger = LogFactory.getLog(getClass());
	
    public CVPDCRGBillDAOImpl(SessionFactory sessionFactory)
    {
        ghibSession = sessionFactory.getCurrentSession();
    }
}
