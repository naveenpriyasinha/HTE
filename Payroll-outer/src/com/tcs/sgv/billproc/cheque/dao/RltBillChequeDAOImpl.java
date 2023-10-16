package com.tcs.sgv.billproc.cheque.dao;

/** 
 *  com.tcs.sgv.billproc.cheque.dao.RltBillChequeDAO
 *  
 *  This is implementation class for Bill cheque relation Data access for 
 *  getting bill and cheque details
 *  
 * 	Date of Creation : 10th July 2007
 * 
 *  Author : Vidhya Mashru 
 *  
 *  Revision History 
 *  =====================
 *  Vidhya M    23-Oct-2007  Made changes for code formatting
 */
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.billproc.cheque.valueobject.BillChequeVO;
import com.tcs.sgv.billproc.cheque.valueobject.RltBillCheque;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.billproc.counter.valueobject.BillVO;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class RltBillChequeDAOImpl extends
		GenericDaoHibernateImpl<RltBillCheque, Integer> implements
		RltBillChequeDAO {
	Log logger = LogFactory.getLog(getClass());

	SessionFactory sessFac = null;

	ResourceBundle bundleConst = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	public RltBillChequeDAOImpl(Class<RltBillCheque> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		sessFac = sessionFactory;
	}
	/**
	 * Method to getting the chq for bill
	 * 
	 * @param  long     : billNo
	 * 		   
	 * @return List
	 * @author vidhya
	 */
	public java.util.List getChequesForBill(long billNo) {
		Session session = getSession();
		Query query = session
				.createQuery("FROM RltBillCheque where billNo ="
						+ billNo
						+ " and chequeId in (select chequeId from TrnChequeDtls where status != '"
						+ bundleConst.getString("STATUS.CheqCancel") + "') ");
		return query.list();
	}
	
	/**
	 * Method to getting the chq for bill
	 * 
	 * @param  long     : billNo, 
	 * 		   String[] : lStrSearch
	 * @return List
	 * @author vidhya
	 */
	public java.util.List getChequesForBill(long billNo, String[] lStrSearch) {
		Session session = getSession();
		StringBuffer queryExe = new StringBuffer(
				" FROM TrnChequeDtls chq where chq.chequeId in (select chequeId from  RltBillCheque where billNo ="
						+ billNo + ") ");

		if (lStrSearch != null && lStrSearch.length != 2) {
			if (lStrSearch[7] != null && !lStrSearch[7].equals("")) {
				queryExe.append(" and chq.chequeNo='" + lStrSearch[7] + "' ");
			}
			if (lStrSearch[0] != null && !lStrSearch[0].equals("")) {
				queryExe.append(" and chq.printDate >=to_date('"
						+ lStrSearch[0] + "','dd/mm/yyyy') ");
			}
			if (lStrSearch[1] != null && !lStrSearch[1].equals("")) {
				queryExe.append(" and chq.printDate <=to_date('"
						+ lStrSearch[1] + "','dd/mm/yyyy') ");
			}
			if (lStrSearch[2] != null && !lStrSearch[2].equals("")) {
				queryExe
						.append(" and chq.chequeAmt >='" + lStrSearch[2] + "' ");
			}
			if (lStrSearch[3] != null && !lStrSearch[3].equals("")) {
				queryExe
						.append(" and chq.chequeAmt <='" + lStrSearch[3] + "' ");
			}

		} else {
			if (lStrSearch[0] != null && lStrSearch[1] != null) {
				if (lStrSearch[0].equals("chkNo")) {
					queryExe.append(" and  chq.chequeNo  like '%"
							+ lStrSearch[1] + "%' ");
				}
			}
		}
		queryExe.append(" and status != '"
				+ bundleConst.getString("STATUS.CheqCancel") + "' ");
		//System.out.println(" Query is ::::::" + queryExe);
		Query query = session.createQuery(queryExe.toString());
		return query.list();
	}
	
	/**
	 * Method to getting Bill From Cheque
	 * 
	 * @param  long chequeId
	 * 		   
	 * @return List
	 * @author vidhya
	 */
	public java.util.List getBillFromCheque(long chequeId) {
		Session session = getSession();
		Query query = session.createQuery("FROM RltBillCheque where chequeId ="
				+ chequeId);
		return query.list();
	}

	/**
	 * Method to getting Counter Bill and Cheques
	 * 
	 * @param  String status,
	 *		   String receiveFlag,
	 *		   String billList, 
	 *		   String[] lStrSrc, 
	 *		   Long langId
	 * @return List
	 * @author vidhya
	 */
	public java.util.List getCounterBillCheques(String status,
			String receiveFlag, String billList, String[] lStrSrc, Long langId) {
		String tempStatus = status;
		Session session = getSession();
		List dataList = new java.util.ArrayList();
		if (billList != null && billList.length() > 0) {
			// special case for ddo name and chk no
			logger.info(" The passed data is  ::: lStrSrc[0]" + lStrSrc[0]
					+ " :::: lStrSrc[1]" + lStrSrc[1]);

			String strQuery = "select p.tcBill,p.billNo,p.billGrossAmount,p.inwardDt,p.ddoCode,p.budmjrHd,p.tokenNum,mb.subjectDesc,p.billCntrlNo,p.audPostId,clm.lookupName,odm.ddoName,odm.ddoNo,odm.cardexNo,p.exempted,p.phyBill,p.refNum  from CmnLookupMst  clm,TrnBillMvmnt tbm,TrnBillRegister p,MstBillType  mb, OrgDdoMst odm where p.tcBill=clm.lookupName and clm.cmnLanguageMst.langId="
					+ langId
					+ " and odm.ddoCode = p.ddoCode and mb.langId = odm.langId and ";

			if (lStrSrc.length == 2) // length of string search is 2 then it
										// is regular search otherwise (leght is
										// 9 )it is comming from custodian
										// search
			{
				if (!(lStrSrc[0] == null || lStrSrc[1] == null)) {
					if (lStrSrc[0].equals("ddoName")) {
						strQuery = strQuery + " ( odm.ddoName " + " like '%"
								+ lStrSrc[1] + "%' ) and ";
					} else {
						if (!(lStrSrc[0].equals("chkNo"))) {
							if (lStrSrc[0].equals("p.billDate")) {
								strQuery = strQuery + lStrSrc[0]
										+ " like to_date('" + lStrSrc[1]
										+ "','yyyy-mm-dd') and ";
							} else {
								strQuery = strQuery + lStrSrc[0] + " like '%"
										+ lStrSrc[1] + "%' and ";
							}
						} else {
							strQuery = strQuery
									+ " clm.lookupName='Regular' and ";
						}
					}
				}

			} else {
				if (tempStatus.equals("CUSTCON2")) {

					if (lStrSrc[4] != null && !lStrSrc[4].equals("")) {
						strQuery = strQuery + " odm.ddoNo like '%" + lStrSrc[4]
								+ "%' and  ";
					}
					if (lStrSrc[5] != null && !lStrSrc[5].equals("")) {
						strQuery = strQuery + " p.billCntrlNo like '%"
								+ lStrSrc[5] + "%' and  ";
					}
					if (lStrSrc[6] != null && !lStrSrc[6].equals("")) {
						strQuery = strQuery + " p.tokenNum like '%"
								+ lStrSrc[6] + "%' and  ";
					}
					if (lStrSrc[8] != null && !lStrSrc[8].equals("")) {
						strQuery = strQuery + " p.exempted='" + lStrSrc[8]
								+ "' and  ";
					}

					// strQuery = strQuery+ " clm.lookupName='Regular' and ";

					status = bundleConst.getString("STATUS.CheqInCustody");
				}
			}

			strQuery = strQuery
					+ " mb.subjectId=p.subjectId  and tbm.receivedFlag="
					+ receiveFlag
					+ " and  tbm.billNo=p.billNo and  p.currBillStatus in('"
					+ status
					+ "','BILL_EXP') and p.billNo IN ("
					+ billList
					+ ") and tbm.movemntId =(select max(B.movemntId) from TrnBillMvmnt B where B.billNo=p.billNo) order by p.createdDate desc";

			logger.info(" Query is :: ::: :: " + strQuery);
			Query query = session.createQuery(strQuery);

			List resList = query.list();
			if (resList != null && resList.size() > 0) {
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					BillChequeVO billChequeVO = new BillChequeVO();
					Object[] tuple = (Object[]) it.next();
					BillVO billVO = new BillVO();
					billVO.setTcBill((tuple[0].toString()));
					billVO.setBillNo(Long.parseLong(tuple[1].toString()));
					billVO.setBillGrossAmount(new BigDecimal(tuple[2]
							.toString()));
					billVO.setInwardDt((Date) tuple[3]);
					billVO.setDdoName(tuple[11].toString());
					billVO.setBudmjrHd(tuple[5].toString());
					billVO.setTokenNum(Long.parseLong(tuple[6].toString()));
					billVO.setEmployeeName(tuple[11].toString());
					billVO.setSubjectDesc(tuple[7].toString());

					billVO.setLookupName(tuple[10].toString());
					if (tuple[13] != null
							&& !tuple[13].toString().trim().equals("")) {
						billVO
								.setCardexNo(Long.parseLong(tuple[13]
										.toString()));
					}
					if (tuple[4] != null
							&& !tuple[4].toString().trim().equals("")) {
						billVO.setDdoCode(tuple[4].toString());
					}
					if (tuple[12] != null
							&& !tuple[12].toString().trim().equals("")) {
						billVO.setDdoNO(tuple[12].toString());
					}
					if (tuple[14] != null
							&& !tuple[14].toString().trim().equals("")) {
						billVO.setExempted(tuple[14].toString());
					}
					if (tuple[16] != null
							&& !tuple[16].toString().trim().equals("")) {
						billVO
								.setRefNum(Integer.parseInt(tuple[16]
										.toString()));
					}
					billVO.setBillCntrlNo(tuple[8].toString());
					billVO.setPhyBill(Long.parseLong(tuple[15].toString()));

					if (tuple[9] != null) {
						billVO
								.setAudPostId(Long.parseLong(tuple[9]
										.toString()));
						Query audQuery = session
								.createQuery("select oe.empPrefix,oe.empFname,oe.empLname,p.audPostId,oe.empMname from TrnBillRegister  p,OrgEmpMst  oe,OrgUserpostRlt our where oe.orgUserMst.userId=our.orgUserMst.userId and our.orgPostMstByPostId.postId=p.audPostId and p.billNo ="
										+ billVO.getBillNo());
						List resltList = audQuery.list();
						if (resList != null && resList.size() > 0) {
							Iterator itr = resltList.iterator();
							while (itr.hasNext()) {
								Object[] tuple1 = (Object[]) itr.next();
								billVO.setAudName(tuple1[0].toString() + " "
										+ tuple1[1].toString() + " "
										+ tuple1[4].toString() + " "
										+ tuple1[2].toString());
							}
						}
					} else {
						billVO.setAudPostId((long) -1);
						billVO.setAudName("-1");
					}
					billChequeVO.setBillVO(billVO);
					List returnList = getChequesForBill(billVO.getBillNo(),
							lStrSrc);
					billChequeVO.setChequeList(returnList);
					PhyBillDAOImpl phy = new PhyBillDAOImpl(
							TrnBillRegister.class, this.getSessionFactory());
					BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
							TrnBillRegister.class, this.getSessionFactory());
					String billCat = lObjCmnSrvcDAOImpl.getLookUpText(billVO
							.getTcBill(), langId);
					if (billCat != null && billCat.equals("Regular")) {
						if (returnList != null && returnList.size() > 0) {
							dataList.add(billChequeVO);
						}
					} else {
						dataList.add(billChequeVO);
					}
				}
			}
		}
		return dataList;
	}

	/*
	 * public int updateBillMovement(long billNo, String UserId) { Session
	 * session = getSession(); int result = session.createQuery("update
	 * TrnBillMvmnt set receivedFlag=1 where billNo="+billNo+" and
	 * hrchyUserId="+UserId ).executeUpdate(); logger.info(" Update result is
	 * ............. " + result); return result; }
	 */
}
