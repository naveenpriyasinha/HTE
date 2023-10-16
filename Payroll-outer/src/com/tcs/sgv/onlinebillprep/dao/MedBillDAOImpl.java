/**
 * package : com.tcs.sgv.onlinebillprep.dao
 * @verion : 1.0
 * @author Keyur Shah, Amit Singh. 
 *
 */
package com.tcs.sgv.onlinebillprep.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblAprvdDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblHdr;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblRqst;

/**
 * MedBillDAOImpl Its a Medical Bill Specific DAO.
 * 
 * @author Keyur Shah, Amit Singh
 * @version 1.0
 */
public class MedBillDAOImpl implements MedBillDAO {
	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	/* Global Variable for Session Class */
	Session ghibSession = null;

	public MedBillDAOImpl(SessionFactory sessionFactory) {
		ghibSession = sessionFactory.getCurrentSession();
	}

	/**
	 * It gets the TrnMedblRqst VO for the Specified lLngRqstIdtrnAprvdRqstId).
	 * 
	 * @param Long
	 *            lLngRqstId.
	 * @return lReturnObj TrnMedblRqst.
	 */
	public TrnMedblRqst getMedBillDtls(Long lLngRqstId) {
		TrnMedblRqst lReturnObj = null;

		try {
			StringBuffer lSBQuery = new StringBuffer();

			lSBQuery.append(" FROM  TrnMedblRqst A WHERE ");
			lSBQuery.append(" A.trnAprvdRqstId = :trnAprvdRqstId ");

			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			lQuery.setParameter("trnAprvdRqstId", lLngRqstId);

			logger.info("Query for getMedBillDtls : " + lQuery.toString());
			logger.info("And Parameter is " + lLngRqstId);

			List lList = lQuery.list();

			if (lList != null && lList.size() > 0) {
				lReturnObj = (TrnMedblRqst) lList.get(0);
			}
		} catch (Exception e) {
			logger.error("Error in getMedBillDtls is : " + e, e);
		}

		return lReturnObj;
	}

	/**
	 * It gets the Primary Keys of Medical Bill Specific
	 * VOs(TrnMedblHdr,TrnMedblDtls,TrnMedblAprvdDtls).
	 * 
	 * @param long
	 *            BillNo.
	 * @return lLstRsltSet List
	 */
	public List getPKForTable(long lLngBillNo) {
		StringBuffer lsb = new StringBuffer();
		List lLstRsltSet = null;
		Long lLngObjBillNo = null;

		lLngObjBillNo = new Long(lLngBillNo);

		try {
			lsb
					.append(" SELECT tmhr.trnMedblHdrId ,tmds.trnMedblDtlsId ,tmapd.trnMedblAprvdDtlsId ");
			lsb
					.append(" FROM TrnMedblHdr tmhr, TrnMedblDtls tmds, TrnMedblAprvdDtls tmapd ");
			lsb
					.append(" WHERE tmhr.trnMedblHdrId = tmds.trnMedblHdrId AND tmds.trnMedblHdrId = tmapd.trnMedblHdrId ");
			lsb.append(" And tmhr.billNo = :billNo ");

			Query lQuery = ghibSession.createQuery(lsb.toString());

			lQuery.setParameter("billNo", lLngObjBillNo);

			lLstRsltSet = lQuery.list();

		}

		catch (Exception e) {
			logger.error("Error in getPKForTable is : " + e, e);
		}

		return lLstRsltSet;
	}

	/**
	 * It gets the Primary Keys of Medical Bill Specific
	 * VOs(TrnMedblHdr,TrnMedblDtls,TrnMedblAprvdDtls).
	 * 
	 * @param long
	 *            BillNo.
	 * @return lLstRsltSet List
	 */
	public List getPKForTableTrnMedBlDtls(long lLngBillNo) {
		StringBuffer lsb = new StringBuffer();
		List lLstRsltSet = null;
		Long lLngObjBillNo = null;

		lLngObjBillNo = new Long(lLngBillNo);

		try {
			lsb.append(" SELECT tmhr.trnMedblHdrId, tmds.trnMedblDtlsId ");
			lsb.append(" FROM TrnMedblHdr tmhr, TrnMedblDtls tmds ");
			lsb.append(" WHERE tmds.trnMedblHdrId = tmhr.trnMedblHdrId ");
			lsb.append(" AND tmhr.billNo = :billNo ");

			Query lQuery = ghibSession.createQuery(lsb.toString());

			lQuery.setParameter("billNo", lLngObjBillNo);

			lLstRsltSet = lQuery.list();
		} catch (Exception e) {
			logger.error("Error in getPKForTableTrnMedBlDtls is : " + e, e);
		}

		return lLstRsltSet;
	}

	/**
	 * It gets the Primary Keys of Medical Bill Specific VOs(TrnMedblAprvdDtls).
	 * 
	 * @param long
	 *            BillNo.
	 * @return lLstRsltSet List
	 */
	public List getPKForTableMedBlAprvdDtls(long lLngBillNo) {
		StringBuffer lsb = new StringBuffer();
		List lLstRsltSet = null;
		Long lLngObjBillNo = null;

		lLngObjBillNo = new Long(lLngBillNo);

		try {
			lsb.append(" SELECT tmapd.trnMedblAprvdDtlsId ");
			lsb.append(" FROM TrnMedblHdr tmhr, TrnMedblAprvdDtls tmapd ");
			lsb.append(" WHERE tmapd.trnMedblHdrId = tmhr.trnMedblHdrId ");
			lsb.append(" And tmhr.billNo = :billNo ");

			Query lQuery = ghibSession.createQuery(lsb.toString());

			lQuery.setParameter("billNo", lLngObjBillNo);

			lLstRsltSet = lQuery.list();
		} catch (Exception e) {
			logger.error("Error in getPKForTableMedBlAprvdDtls is : " + e, e);
		}

		return lLstRsltSet;
	}

	/**
	 * It gets the Medical Bill(TrnMedblHdr,TrnMedblDtls,TrnMedblAprvdDtls)
	 * Specific VOs.
	 * 
	 * @param long,ServiceLocator
	 *            BillNo,serv.
	 * @return lMapMedData Map.
	 */
	public Map getMedBillData(Long lLngBillNo, ServiceLocator serv) {
		OnlineBillDAO lObjOnlineBillDao = null;
		TrnMedblHdr lObjMedBlHdr = null;
		TrnMedblDtls lObjMedBlDtls = null;
		TrnMedblAprvdDtls lObjMedBlAprvdDtls = null;
		TrnMedblHdrDAO lObjMedBlHdrDAO = null;
		TrnMedblDtlsDAO lObjMedBlDtlsDAO = null;
		TrnMedblAprvdDtlsDAO lObjMedBlAprvdDtlsDAO = null;

		List lLstPK = null;
		List<TrnMedblDtls> lLstTrnMedblDtlsVO = new ArrayList<TrnMedblDtls>();
		List<TrnMedblAprvdDtls> lLstTrnMedblAprvdDtlsVO = new ArrayList<TrnMedblAprvdDtls>();

		// MedBillDAOImpl lObjMedBillDAOImpl = new
		// MedBillDAOImpl(TrnMedblHdr.class, serv.getSessionFactory());
		lLstPK = getPKForTableTrnMedBlDtls(lLngBillNo);

		Map lMapMedData = new HashMap();

		if (lLstPK != null && lLstPK.size() != 0) {
			Object[] lObjArrPK = (Object[]) lLstPK.get(0);

			lObjMedBlHdrDAO = new TrnMedblHdrDAOImpl(TrnMedblHdr.class, serv
					.getSessionFactory());
			lObjMedBlHdr = (TrnMedblHdr) lObjMedBlHdrDAO
					.read((Long) lObjArrPK[0]);

			lMapMedData.put("TrnMedblHdr", lObjMedBlHdr);

			for (int iMedBillDtls = 0; iMedBillDtls < lLstPK.size(); iMedBillDtls++) {
				lObjArrPK = (Object[]) lLstPK.get(iMedBillDtls);
				lObjMedBlDtlsDAO = new TrnMedblDtlsDAOImpl(TrnMedblDtls.class,
						serv.getSessionFactory());
				lObjMedBlDtls = (TrnMedblDtls) lObjMedBlDtlsDAO
						.read((Long) lObjArrPK[1]);
				lLstTrnMedblDtlsVO.add(lObjMedBlDtls);
			}

			lMapMedData.put("TrnMedblDtls", lLstTrnMedblDtlsVO);
		}

		lLstPK = getPKForTableMedBlAprvdDtls(lLngBillNo);
		if (lLstPK != null && lLstPK.size() != 0) {
			for (int iMedBlAprvdDtls = 0; iMedBlAprvdDtls < lLstPK.size(); iMedBlAprvdDtls++) {
				lObjMedBlAprvdDtlsDAO = new TrnMedblAprvdDtlsDAOImpl(
						TrnMedblAprvdDtls.class, serv.getSessionFactory());
				lObjMedBlAprvdDtls = (TrnMedblAprvdDtls) lObjMedBlAprvdDtlsDAO
						.read((Long) lLstPK.get(iMedBlAprvdDtls));
				lLstTrnMedblAprvdDtlsVO.add(lObjMedBlAprvdDtls);
			}

			lMapMedData.put("TrnMedblAprvdDtls", lLstTrnMedblAprvdDtlsVO);
		}

		return lMapMedData;
	}
}
