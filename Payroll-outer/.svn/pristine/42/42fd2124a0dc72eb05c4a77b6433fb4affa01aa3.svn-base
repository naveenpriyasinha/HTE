/**
 * 
 */
package com.tcs.sgv.billproc.counter.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * BdgtHeadDtlsDAOImpl 
 * This class is for budget head structure which has a
 * parameterized constructor for 'TrnBillBudheadDtls' value object
 * 
 * 
 * Date of Creation : 11th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * =====================
 *  Hiral    23-Oct-2007   For making changes for code formating
 */
public class BdgtHeadDtlsDAOImpl extends
		GenericDaoHibernateImpl<TrnBillBudheadDtls, Long> implements
		BdgtHeadDtlsDAO {
	Log logger = LogFactory.getLog(getClass());

	public BdgtHeadDtlsDAOImpl(Class<TrnBillBudheadDtls> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}
}
