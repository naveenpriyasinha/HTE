package com.tcs.sgv.pension.dao;

import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionerRivisionDtls;

/**
 * Data Access Object for Insert, Update, Delete on TrnPensionerRivisionDtls VO
 * 
 * @param type
 * @param sessionFactory
 */
public class TrnPensionerRivisionDtlsDAOImpl extends GenericDaoHibernateImpl<TrnPensionerRivisionDtls, Long> implements TrnPensionerRivisionDtlsDAO 
{

	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	/* Global Variable for Session Class */
	
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
	public TrnPensionerRivisionDtlsDAOImpl(Class<TrnPensionerRivisionDtls> type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	/**
	 * It gets the Primary Keys of Pension Specific VOs(TrnPensionerRivisionDtls).
	 * 
	 * @param long
	 *            BillNo.
	 * @return lLstRsltSet List
	 */
	public List getPKForTableRevisionDtls(String lStrPenCode) {
		StringBuffer lsb = new StringBuffer();
		List lLstRsltSet = null;
		String lStrPnsrCode = lStrPenCode;

		try 
		{
            Session ghibSession = getSession();
            lsb.append(" SELECT rev.rivisionDtlsId ");
			lsb.append(" FROM TrnPensionerRivisionDtls rev ");
			lsb.append(" WHERE rev.pensionerCode = :lPnsrCode ");
			
			Query lQuery = ghibSession.createQuery(lsb.toString());

			lQuery.setParameter("lPnsrCode", lStrPnsrCode);

			lLstRsltSet = lQuery.list();
		} 
		catch (Exception e) 
		{
			logger.error("Error is: " + e, e);
		}

		return lLstRsltSet;
	}
	public Long getPKForRevisionDtls(String lStrPenCode) {
		StringBuffer lsb = new StringBuffer();
		List lLstRsltSet = null;
		String lStrPnsrCode = lStrPenCode;
		Long rivisionDtlsId = 0L;
		try 
		{
            Session ghibSession = getSession();
            lsb.append(" SELECT rev.rivisionDtlsId ");
			lsb.append(" FROM TrnPensionerRivisionDtls rev ");
			lsb.append(" WHERE rev.pensionerCode = :lPnsrCode AND rev.activeFlag = 'Y' ");
			
			Query lQuery = ghibSession.createQuery(lsb.toString());

			lQuery.setParameter("lPnsrCode", lStrPnsrCode);

			lLstRsltSet = lQuery.list();
			if(lLstRsltSet != null && lLstRsltSet.size() > 0)
				rivisionDtlsId = (Long)lLstRsltSet.get(0);
		} 
		catch (Exception e) 
		{
			logger.error("Error is: " + e, e);
		}

		return rivisionDtlsId;
	}
	
}
