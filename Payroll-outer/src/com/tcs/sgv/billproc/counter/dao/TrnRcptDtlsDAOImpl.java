/**
 * 
 */
package com.tcs.sgv.billproc.counter.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * BdgtHeadDtlsDAOImpl
 * This class is for receipt details which has parameterized constructor for 'TrnReceiptDetails' Value Object.
 * 
 * 
 * Date of Creation : 11th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * =====================
 *  Hiral    23-Oct-2007   For making changes for code formating
 *
 */
public class TrnRcptDtlsDAOImpl
	extends GenericDaoHibernateImpl<TrnReceiptDetails,Long> implements TrnRcptDtlsDAO  
{
	public TrnRcptDtlsDAOImpl(Class<TrnReceiptDetails> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
}
