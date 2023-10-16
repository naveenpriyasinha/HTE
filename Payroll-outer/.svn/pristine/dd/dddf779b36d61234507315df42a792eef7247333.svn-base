/**
 * package : com.tcs.sgv.onlinebillprep.dao 
 * @verion : 1.0
 * @author : Keyur Shah, Amit Singh. 
 ** 
 */

package com.tcs.sgv.onlinebillprep.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.billproc.common.valueobject.TrnBillRemarks;
import com.tcs.sgv.billproc.counter.dao.TrnRcptDtlsDAOImpl;
import com.tcs.sgv.common.valueobject.RltBillParty;
import com.tcs.sgv.common.valueobject.TrnRcptBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.exprcpt.dao.RcptBudDtlsDAOImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnChallanParty;

/**
 * OnlineBillDAOImpl Its a DAO for Online Bill Preparation.
 * 
 * @author Keyur Shah, Amit Singh
 * @version 1.0
 */
public class OnlineBillDAOImpl implements OnlineBillDAO {

	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	/* Global Variable for Session Class */
	private Session ghibSession = null;

	public OnlineBillDAOImpl(SessionFactory sessionFactory) {
		ghibSession = sessionFactory.getCurrentSession();
	}

	/**
	 * Returns the worklist for the logged in user
	 * 
	 * @param Map
	 *            :lMapInput
	 * @return List
	 */
	public List getMySavedBills(Map lMapInput) {
        StringBuffer lSBQuery = new StringBuffer();
        List lLstSvdBills = null;
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/onlinebillprep/OnlineBillConstants");
        String lStrCurrBillStatus = lMapInput.get("StrCurrBillStatus").toString();
        String lStrBillNos = lMapInput.get("StrMyBills").toString();
        Long lLngLangId = (Long)lMapInput.get("gLngLangId");
        String lStrTxtSearch = null;
        String lStrCmbSearch = null;
        if(lMapInput.get("StrTxtSearch") != null && lMapInput.get("StrCmbSearch") != null)
        {
            lStrTxtSearch = lMapInput.get("StrTxtSearch").toString().trim();
            lStrCmbSearch = lMapInput.get("StrCmbSearch").toString().trim();
            logger.info((new StringBuilder("Status Flag is = ")).append(lStrTxtSearch).toString());
            logger.info((new StringBuilder("Rec Flag is = ")).append(lStrCmbSearch).toString());
        }
        try
        {	//N.B: BR.updatedDate is taken as token date in bill view screen- varun sharma
            lSBQuery.append(" SELECT BR.billNo, BR.billCntrlNo, BR.billDate, BR.subjectId,");
            lSBQuery.append(" BT.subjectDesc, BR.budmjrHd, BR.tokenNum, BR.updatedDate, BR.billGrossAmount, BR.currBillStatus, ");
            lSBQuery.append(" BR.ddoDeptId, BR.ddoCode,DD.ddoNo, DD.ddoName, ");
            lSBQuery.append(" (SELECT bReg.billNo from TrnBillRegister bReg where bReg.prevBillNo = BR.billNo ");
            lSBQuery.append(" and bReg.createdDate =(SELECT max(bRegSub.createdDate) from TrnBillRegister bRegSub where bRegSub.prevBillNo = BR.billNo)), ");
            lSBQuery.append(" (SELECT bReg.billCntrlNo from TrnBillRegister bReg where bReg.prevBillNo = BR.billNo ");
            lSBQuery.append(" and bReg.createdDate =(SELECT max(bRegSub.createdDate) from TrnBillRegister bRegSub where bRegSub.prevBillNo = BR.billNo)), ");
            lSBQuery.append(" (SELECT bReg.currBillStatus from TrnBillRegister bReg where bReg.prevBillNo = BR.billNo ");
            lSBQuery.append(" and bReg.createdDate =(SELECT max(bRegSub.createdDate) from TrnBillRegister bRegSub where bRegSub.prevBillNo = BR.billNo)), ");
            lSBQuery.append(" BR.tcBill, ");
            //added by ravysh
            lSBQuery.append(" (select billregMpg.billTypeId.lookupId from PaybillBillregMpg billregMpg where billregMpg.trnBillRegister.billNo=BR.billNo) as BillType ");
            lSBQuery.append(" FROM TrnBillRegister BR, OrgDdoMst DD, MstBillType  BT");
            lSBQuery.append(" WHERE BR.subjectId = BT.subjectId AND ");
            lSBQuery.append(" DD.ddoCode = BR.ddoCode AND BT.langId = DD.langId AND ");
            lSBQuery.append(" BR.currBillStatus IN (:currBillStatus) AND DD.langId = :langId  AND");
            lSBQuery.append(" BR.locationCode = :locId  ");
            if(lStrCmbSearch != null && lStrTxtSearch != null)
            {
                lSBQuery.append(" and "+(new StringBuilder(" ")).append("upper(").append(lStrCmbSearch).append(")").toString());
                if(lStrCmbSearch.equals("BR.billDate"))
                    lSBQuery.append((new StringBuilder(" like to_date('")).append(lStrTxtSearch).append("','dd/mm/yyyy')  ").toString());
                else
                if(lStrCmbSearch.equals("BR.currBillStatus"))
                {
                    if(lStrTxtSearch.equalsIgnoreCase("Created"))
                        lSBQuery.append(" like '%BCRTD%'  ");
                    else
                    if(lStrTxtSearch.equalsIgnoreCase("Approved"))
                        lSBQuery.append(" like '%BAPRVD_DDO%'  ");
                } else
                if(lStrCmbSearch.equals("BR.budmjrHd") || lStrCmbSearch.equals("BR.billGrossAmount") || lStrCmbSearch.equals("DD.ddoNo"))
                    lSBQuery.append((new StringBuilder(" like ")).append("upper('").append(lStrTxtSearch).append("%'  ").append(")").append(")").toString());
                else
                    lSBQuery.append((new StringBuilder(" like ")).append("upper( '%").append(lStrTxtSearch).append("%'  ").append(")").append(")").toString());
            }
            /*lSBQuery.append(" and BR.billNo in ");
            lSBQuery.append(lStrBillNos);*/
            lSBQuery.append(" order by BR.createdDate desc ");
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            logger.info((new StringBuilder("lStrCurrBillStatus is : ")).append(lStrCurrBillStatus).toString());
            if(lStrCurrBillStatus.equalsIgnoreCase("saved"))
            {
                String lStrParamVal[] = new String[2];
                lStrParamVal[0] = bundleConst.getString("STATUS.BillCreated");
                lStrParamVal[1] = bundleConst.getString("STATUS.BillApproved");
                lQuery.setParameterList("currBillStatus", lStrParamVal);
            } else
            {
                lQuery.setParameter("currBillStatus", lStrCurrBillStatus);
            }
            lQuery.setParameter("langId", lLngLangId);
            String locId = "";
            if(lMapInput.get("locId") != null && lMapInput.get("locId") != null)
                locId = lMapInput.get("locId").toString();
            lQuery.setParameter("locId", locId);
            logger.info((new StringBuilder("Location Code is ")).append(locId).toString());
            logger.info((new StringBuilder("Query for getting my saved bill is : ")).append(lQuery.toString()).toString());
            lLstSvdBills = lQuery.list();
        }
        catch(Exception e)
        {
            logger.error((new StringBuilder("Error in getMySavedBills method of class TABillDAOImpl : ")).append(e).toString(), e);
        }
        return lLstSvdBills;
    }

	/**
	 * It gets the Primary Keys of VOs(TrnBillRegister,TrnBillBudheadDtls)
	 * common to all Bills.
	 * 
	 * @param long
	 *            BillNo.
	 * @return lLstRsltSet List
	 */
	public List getPKForTable(Long lLngBillNo) {
		StringBuffer lsb = new StringBuffer();
		List lLstRsltSet = null;

		try {
			lsb.append(" SELECT tbr.billNo ,tbh.billBudId ");
			lsb.append(" FROM TrnBillRegister tbr,TrnBillBudheadDtls tbh ");
			lsb
					.append(" WHERE tbr.billNo = tbh.billNo AND tbr.billNo = :billNo");

			Query lQuery = ghibSession.createQuery(lsb.toString());

			lQuery.setParameter("billNo", lLngBillNo);

			lLstRsltSet = lQuery.list();
		} catch (Exception e) {
			logger.error("Error in getPKForTable. Error is : " + e, e);
		}

		return lLstRsltSet;
	}

	/**
	 * Returns TrnBillRemarks VO from the Specified billNo and userId.
	 * 
	 * @param Long,Long
	 *            lLngBillNo,lLngUserId.
	 * @return lVOTrnBillRemarks TrnBillRemarks
	 */
	public TrnBillRemarks getRmrksForCurrUser(Long lLngBillNo, Long lLngUserId) {
		StringBuffer lsb = new StringBuffer();
		TrnBillRemarks lVOTrnBillRemarks = null;
		List lLstRsltSet = null;

		try {
			lsb.append(" FROM TrnBillRemarks tbr");
			lsb.append(" Where tbr.billNo = :billNo and tbr.userId = :userId");

			Query lQuery = ghibSession.createQuery(lsb.toString());

			lQuery.setParameter("billNo", lLngBillNo);
			lQuery.setParameter("userId", lLngUserId);

			lLstRsltSet = lQuery.list();

			if (lLstRsltSet != null && lLstRsltSet.size() > 0) {
				lVOTrnBillRemarks = (TrnBillRemarks) lLstRsltSet.get(0);
			}
		} catch (Exception e) {
			logger.error("Error in getRmrksForCurrUser. Error is : " + e, e);
		}

		return lVOTrnBillRemarks;
	}

	public List<RltBillParty> getBillPartyRltInfo(Long lLngBillNo) {
		StringBuffer lsb = new StringBuffer();
		List lLstRsltSet = null;

		try {
			lsb.append(" FROM RltBillParty tbr");
			lsb.append(" Where tbr.billNo = :billNo ");

			Query lQuery = ghibSession.createQuery(lsb.toString());

			lQuery.setParameter("billNo", lLngBillNo);

			lLstRsltSet = lQuery.list();
		} catch (Exception e) {
			logger.error("Error in getBillPartyRltInfo. Error is : " + e, e);
		}
		return lLstRsltSet;
	}

	public List getBillsSentToTO(Long lLngPostId) {
		StringBuffer lsb = new StringBuffer();
		List lLstRsltSet = null;

		try {
			lsb
					.append(" Select m.billNo, m.mvmntStatus, r.currBillStatus from TrnBillMvmnt m, TrnBillRegister r ");
			lsb
					.append(" Where m.billNo = r.billNo and m.mvmntStatus = 'BSNT_TO' and m.createdPostId = :postId ");

			Query lQuery = ghibSession.createQuery(lsb.toString());

			lQuery.setParameter("postId", lLngPostId);

			lLstRsltSet = lQuery.list();

			logger.info("Query for fetching Sent To TO Bills - " + lQuery);
		} catch (Exception e) {
			logger.error("Error in getBillsSentToTO. Error is : " + e, e);
		}

		return lLstRsltSet;
	}

	// Foll. code added by Jay for OnlineBillPrep

	// Following method is called to get all d PKs' of TrnRcptBudHdDtls those
	// need
	// to be deleted while updating TrnRcptBudHdDtls

	public List getPKToUpdateBudHd(long lLngBillNo) {
		List lLstResult = null;
		StringBuffer sb = new StringBuffer();
		try {
			sb
					.append(" select a.receiptDetailId, b.rcptBudId, c.billChallanId  ");

			sb
					.append(" from TrnReceiptDetails a, TrnRcptBudheadDtls b, RltBillChallan c ");

			sb.append(" where a.receiptDetailId = b.receiptDetailId  ");

			sb.append(" and a.receiptDetailId = c.challanNo  ");

			sb.append(" and c.billNo = :billNo ");

			Query lObjQuery = ghibSession.createQuery(sb.toString());

			lObjQuery.setParameter("billNo", new Long(lLngBillNo));

			lLstResult = lObjQuery.list();

		} catch (Exception e) {
			logger
					.error(
							"Error inside getPKToUpdateBudHd() of OnlineBillDAOImpl",
							e);
		}

		return lLstResult;
	}

	public List getPKToUpdateChallanParty(long lLngBillNo) {
		List lLstRes = null;
		StringBuffer sb = new StringBuffer();
		try {
			sb
					.append("  select x.receiptDetailId, y.trnChallanPartyId, z.billChallanId   ");

			sb
					.append("  from TrnReceiptDetails x, TrnChallanParty y, RltBillChallan z  ");

			sb.append("  where x.receiptDetailId = y.challanId  ");

			sb.append("  and x.receiptDetailId = z.challanNo ");

			sb.append("  and z.billNo =:billNo ");

			Query lObjQuery = ghibSession.createQuery(sb.toString());

			lObjQuery.setParameter("billNo", new Long(lLngBillNo));

			//System.out.println("\n Query to getPKToUpdateChallanParty() : "					+ sb.toString() + " \n");

			lLstRes = lObjQuery.list();
		} catch (Exception e) {
			logger.error("Error inside getPKToUpdateChallanParty() : ", e);
		}

		return lLstRes;
	}

	// added by Jay for Challan

	public Map getChallanData(long challanId, Map lMapInput) {
		logger.info("Inside getChallanData() of OnlineBillDAOImpl : start");

		ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");

		List lObjArrPK = null;
		Object[] lArrObj = null;
		List<TrnRcptBudheadDtls> lLstRcptBudhdDtls = null;
		List<TrnChallanParty> lLstChallanParty = null;

		TrnReceiptDetails lObjTrnReceiptDetails = null;
		TrnRcptBudheadDtls lObjRcptBudheadDtls = null;
		TrnChallanParty lObjTrnChallanParty = null;

		TrnRcptDtlsDAOImpl rDAO = null;
		RcptBudDtlsDAOImpl rBudhdDAO = null;
		TrnChallanPartyDAOImpl cpDAO = null;

		try {
			lObjArrPK = getPKforChallan(challanId);

			if (lObjArrPK != null & lObjArrPK.size() != 0) {
				// case of trn_receipt_details

				lArrObj = (Object[]) lObjArrPK.get(0);

				rDAO = new TrnRcptDtlsDAOImpl(TrnReceiptDetails.class, serv
						.getSessionFactory());

				lObjTrnReceiptDetails = rDAO.read((Long) lArrObj[0]);

				// case of trn_rcpt_budhd_details - there may be multiple
				// records

				lLstRcptBudhdDtls = new ArrayList<TrnRcptBudheadDtls>();
				rBudhdDAO = new RcptBudDtlsDAOImpl(TrnRcptBudheadDtls.class,
						serv.getSessionFactory());

				for (int i = 0; i < lObjArrPK.size(); i++) {
					lArrObj = (Object[]) lObjArrPK.get(i);

					lObjRcptBudheadDtls = rBudhdDAO.read((Long) lArrObj[1]);

					lLstRcptBudhdDtls.add(lObjRcptBudheadDtls);
				}

				// case of trn_challan_party - there may be multiple records

				lLstChallanParty = new ArrayList<TrnChallanParty>();
				cpDAO = new TrnChallanPartyDAOImpl(TrnChallanParty.class, serv
						.getSessionFactory());

				for (int i = 0; i < lObjArrPK.size(); i++) {
					lArrObj = (Object[]) lObjArrPK.get(i);

					lObjTrnChallanParty = cpDAO.read((Long) lArrObj[2]);

					lLstChallanParty.add(lObjTrnChallanParty);
				}

				lMapInput.put("challanReceiptVO", lObjTrnReceiptDetails);
				lMapInput.put("challanBudhdVOLst", lLstRcptBudhdDtls);
				lMapInput.put("challanPartyLst", lLstChallanParty);
			}

		} catch (Exception e) {
			logger.error("Error inside getChallanData() of OnlineBillDAOImpl ",
					e);
		}

		logger.info("Inside getChallanData() of OnlineBillDAOImpl : end");

		return lMapInput;
	}

	private List getPKforChallan(long challanId) {
		logger.info("Inside getPKforChallan() of OnlineBillDAOImpl : start");
		List resList = null;
		StringBuffer sb = new StringBuffer();
		Long lObjChallanId = new Long(challanId);

		try {
			sb
					.append(" select a.receiptDetailId  , b.rcptBudId  , c.trnChallanPartyId ");

			sb
					.append(" from TrnReceiptDetails a, TrnRcptBudheadDtls b, TrnChallanParty c ");

			sb.append(" where a.receiptDetailId = b.receiptDetailId ");

			sb.append(" and a.receiptDetailId = c.challanId ");

			sb.append(" and a.receiptDetailId = :receiptId");

			Query hibQuery = ghibSession.createQuery(sb.toString());

			hibQuery.setParameter("receiptId", lObjChallanId);

			resList = hibQuery.list();
		} catch (Exception e) {
			logger.error("Inside getPKforChallan() of OnlineBillDAOImpl : end",
					e);
		}

		logger.info("Inside getPKforChallan() of OnlineBillDAOImpl : end");

		return resList;
	}

}