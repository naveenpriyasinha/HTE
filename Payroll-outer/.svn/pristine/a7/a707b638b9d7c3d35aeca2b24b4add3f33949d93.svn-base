package com.tcs.sgv.common.dao;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.ChequeVitoRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * com.tcs.sgv.common.dao.ChequeVitoDAOImpl
 *  
 * @author 157045 (Vidhya M Mashru)
 * @version 1.0
 * @see This class is used for Cheque related Vito reports
 */
public class ChequeVitoDAOImpl extends
		GenericDaoHibernateImpl<ChequeVitoRegister, Integer> implements
		ChequeVitoDAO {

	public ChequeVitoDAOImpl(Class<ChequeVitoRegister> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);

	}
/**
 * This is to get Maximum Vito Code
 * 
 * @param vitoType
 * @return
 */
	public long getMaxVitoCode(String vitoType) {
		//System.out.println(" came in ........ dao for BillVitoDAOImpl");
		Session hibSession = getSession();
		Query sqlQuery = hibSession
				.createSQLQuery(" SELECT MAX(VR.VITO_CODE)  FROM CHEQUE_VITO_REGISTER VR WHERE VR.VITO_TYPE='"
						+ vitoType + "'");
		Iterator iterator = sqlQuery.list().iterator();
		long maxVitoId = 0;
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj != null)
				maxVitoId = Long.parseLong(obj.toString());
		}
		maxVitoId = maxVitoId + 1;
		//System.out.println(" \n Returning ID " + maxVitoId);
		return maxVitoId;
	}
}
