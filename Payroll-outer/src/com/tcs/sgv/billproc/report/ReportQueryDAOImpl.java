package com.tcs.sgv.billproc.report;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.valueobject.BillTransitVO;
import com.tcs.sgv.common.valueobject.BillVitoVO;
import com.tcs.sgv.common.valueobject.ChequeVitoVO;
import com.tcs.sgv.common.valueobject.ChequeVitoVOString;
import com.tcs.sgv.common.valueobject.DDODetailsVO;
import com.tcs.sgv.common.valueobject.MstBillType;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

;
/**
 * com.tcs.sgv.billproc.reprot.ReportQueryDAOImpl
 * 
 * This is Implementation class for reporting framework reports to be generated
 * 
 * Date of Creation : 10th July 2007
 * 
 * Author : Vidhya Mashru
 * 
 * Revision History 
 * ===================== 
 * Vidhya M 23-Oct-2007 Made changes for code formatting
 */
public class ReportQueryDAOImpl extends
		GenericDaoHibernateImpl<DDODetailsVO, Integer> implements
		ReportQueryDAO {
	private static final Logger glogger = Logger
			.getLogger(ReportQueryDAOImpl.class);

	public ReportQueryDAOImpl(Class<DDODetailsVO> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	public ReportQueryDAOImpl() {
		super(DDODetailsVO.class);
	}
	 /**
	 * Method to getting the Auditor List
	 * 
	 * @param  Long userId, 
	 * 		Long locId,
	 * 		Long langID,
	 *		String strLocationCode
	 * @return List
	 * @author vidhya
	 */	
	
	public List getAuditorList(Long userId, Long locId, Long langID,
			String strLocationCode) {
		List auditorList = null;

		try {
			String audit = "AUDIT BRANCH";
			Session hibSession = getSession();
			Query sqlQuery = hibSession
					.createSQLQuery("SELECT PM.POST_ID,EM.EMP_FNAME,EM.EMP_MNAME,EM.EMP_LNAME "
							+ " FROM ORG_EMP_MST EM,ORG_USERPOST_RLT PM,ACL_ROLE_MST RM,ACL_POSTROLE_RLT RL,ORG_POST_MST P , ACL_ROLE_DETAILS_RLT RD "
							+ " WHERE RL.POST_ID=PM.POST_ID AND PM.USER_ID=EM.USER_ID AND RL.ROLE_ID=RM.ROLE_ID  AND RM.ROLE_ID = RD.ROLE_ID "
							+ " AND RL.POST_ID = P.POST_ID AND P.LOCATION_CODE = '"
							+ locId + "' AND RD.ROLE_SHORT_NAME ='AUDIT BRANCH' AND RD.LANG_ID ="
							+ langID);
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				auditorList = new ArrayList();
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ComboValuesVO VO = new ComboValuesVO();
					VO.setId(tuple[0].toString());
					VO.setDesc(tuple[1] + " " + tuple[2] + " " + tuple[3]);
					auditorList.add(VO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"
							+ e);
		}
		return auditorList;
	}
	 /**
	 * Method to getting Bill Type List
	 * 
	 * @param  Long userId,
	 * 		   Long locId, 
	 *  	   String locale,
	 *		   String strLocationCode,
	 *		   Long langID
	 * @return List
	 * @author vidhya
	 */
	public List getBillTypeList(Long userId, Long locId, String locale,
			String strLocationCode, Long langID) {
		List billTypeList = null;
		try {
			Session hibSession = getSession();
			// Query sqlQuery = hibSession.createQuery("select
			// subjectId,subjectDesc,billTypeCode from MstBillType where
			// activateFlag=1 and langId="+langID);
			Query sqlQuery = hibSession
					.createQuery("select subjectId,shortName,billTypeCode from MstBillType where activateFlag=1 and langId="
							+ langID);
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				billTypeList = new ArrayList();
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					MstBillType VO = new MstBillType();
					VO.setSubjectId(Long.parseLong(tuple[0].toString()));
					VO.setSubjectDesc((String) tuple[1]);
					try {
						VO.setBillTypeCode(tuple[2].toString());
					} catch (Exception ex) {
						VO.setBillTypeCode("N/A");
					}
					billTypeList.add(VO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"
							+ e);
		}
		return billTypeList;
	}
	 /**
	 * Method to getting the Exp Major Head
	 * 
	 * @param  Long userId,
	 *      Long locId,
	 *		String strLocationCode, 
	 *		Long langID
	 * @return List
	 * @author vidhya
	 */
	public List getExpMajorHead(Long userId, Long locId,
			String strLocationCode, Long langID) {
		List majorHeadList = null;
		System.out
				.println("Inside The Get Major Head DAO Impl and locationCode="
						+ strLocationCode);
		try {
			Session hibSession = getSession();
			// Query sqlQuery = hibSession.createSQLQuery( "select distinct
			// BUDMJRHD_CODE from sgva_budmjrhd_mst where BUDMJRHDTYPE='E' and
			// LANG_ID='en_US'and LOC_ID='LC1' order by BUDMJRHD_CODE");
			// Query sqlQuery = hibSession.createSQLQuery( "select distinct
			// BUDMJRHD_CODE from sgva_budmjrhd_mst where LANG_ID='en_US' and
			// LOC_ID='"+locId+"' order by BUDMJRHD_CODE");
			Query sqlQuery = hibSession
					.createSQLQuery("SELECT DISTINCT BUDMJRHD_CODE FROM SGVA_BUDMJRHD_MST WHERE  LANG_ID='en_US' ORDER BY BUDMJRHD_CODE");
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				majorHeadList = new ArrayList();
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					Object tuple = (Object) it.next();
					ComboValuesVO VO = new ComboValuesVO();
					VO.setId((String) tuple);
					VO.setDesc((String) tuple);
					majorHeadList.add(VO);
					//System.out.println("--" + tuple);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getExpMajorHead # \n"
							+ e);
		}
		return majorHeadList;
	}
	 /**
	 * Method to getting the Bill List By Daily Vito
	 * 
	 * @param  Long audPostId,
	 *         String vitoType,
	 *		   Long locId,
	 *		   String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getBillListByDailyVito(Long audPostId, String vitoType,
			Long locId, String strLocationCode) {
		List dataList = null;

		try {
			Session hibSession = getSession();
			StringBuffer sql = new StringBuffer();

			sql
					.append("select br.billNo,br.locationCode from TrnBillRegister br"
							+ " where br.billNo NOT IN(select vr.billNo from BillVitoRegister vr where vr.vitoType='"
							+ vitoType
							+ "') and br.audPostId="
							+ audPostId
							+ " and br.locationCode='" + strLocationCode + "'");

			Query sqlQuery = hibSession.createQuery(sql.toString());
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					TrnBillRegister vo = new TrnBillRegister();
					Object[] tuple = (Object[]) it.next();
					vo.setBillNo(Long.parseLong(tuple[0].toString()));
					vo.setLocationCode(tuple[1].toString());
					dataList.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getBillListByDailyVito # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting the Bill List By Auditor
	 * 
	 * @param  Long audPostId,
	 *         String vitoType,
	 *		   Long locId,
	 *		   String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getBillListByAuditor(Long audPostId, String vitoType,
			Long locId, String strLocationCode) {
		List dataList = null;

		try {

			Session hibSession = getSession();
			StringBuffer sql = new StringBuffer();

			sql
					.append("select br.billNo,br.locationCode from TrnBillRegister br"
							+ " where br.billNo NOT IN(select distinct billNo from TrnBillMvmnt where mvmntStatus IN('BAPPRV_AUD','BRJCT_AUD'))"
							+ " and br.billNo NOT IN(select vr.billNo from BillVitoRegister vr where vr.vitoType='"
							+ vitoType
							+ "') and br.audPostId="
							+ audPostId
							+ " and br.locationCode='" + strLocationCode + "'");

			Query sqlQuery = hibSession.createQuery(sql.toString());
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					TrnBillRegister vo = new TrnBillRegister();
					Object[] tuple = (Object[]) it.next();
					vo.setBillNo(Long.parseLong(tuple[0].toString()));
					vo.setLocationCode(tuple[1].toString());
					dataList.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getUndistributedVouchers # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting the Bill List By Status
	 * 
	 * @param  Long audPostId,
	 *         String vitoType,
	 *		   Long locId,
	 *		   String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getBillLstByStatus(Long audPostId, String vitoType,
			String status, Long locId, String strLocationCode) {
		List dataList = null;

		try {

			Session hibSession = getSession();
			StringBuffer sql = new StringBuffer();

			sql
					.append("select br.billNo,br.locationCode from TrnBillRegister br"
							+ " where br.billNo IN(select distinct billNo from TrnBillMvmnt where mvmntStatus IN ");
			if (!status.equalsIgnoreCase("-1")) {
				sql.append("('" + status + "'))");
			} else {
				sql.append("('BAPPRV_AUD','BRJCT_AUD'))");
			}
			sql
					.append("and br.billNo NOT IN(select vr.billNo from BillVitoRegister vr where vr.vitoType='"
							+ vitoType
							+ "') and br.audPostId="
							+ audPostId
							+ " and br.locationCode='" + strLocationCode + "'");

			Query sqlQuery = hibSession.createQuery(sql.toString());
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					TrnBillRegister vo = new TrnBillRegister();
					Object[] tuple = (Object[]) it.next();
					vo.setBillNo(Long.parseLong(tuple[0].toString()));
					vo.setLocationCode(tuple[1].toString());
					dataList.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getUndistributedVouchers # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting the Bill List By Bill Type
	 * 
	 * @param  Long audPostId,
	 *         String vitoType,
	 *		   Long locId,
	 *		   String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getBillListByBillType(Long audPostId, String vitoType,
			Long billType, Long locId, String strLocationCode) {
		List dataList = null;

		try {
			Session hibSession = getSession();
			StringBuffer sql = new StringBuffer();
			sql
					.append("SELECT br.billNo,br.locationCode from TrnBillRegister br"
							+ " WHERE br.billNo NOT IN(SELECT vr.billNo FROM BillVitoRegister vr WHERE vr.vitoType='"
							+ vitoType
							+ "') "
							+ " and br.audPostId="
							+ audPostId);
			if (billType != -1) {
				sql.append(" and br.subjectId=" + billType);
			}
			sql.append(" and br.locationCode = '" + strLocationCode + "'");
			Query sqlQuery = hibSession.createQuery(sql.toString());
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					TrnBillRegister vo = new TrnBillRegister();
					Object[] tuple = (Object[]) it.next();
					vo.setBillNo(Long.parseLong(tuple[0].toString()));
					vo.setLocationCode(tuple[1].toString());
					dataList.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getUndistributedVouchers # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting the Audit Wise Daily Vito
	 * 
	 * @param  Long userId, Long locId,
	 *		String audPostId[], 
	 *		String vitoType,
	 *		List vitoStringList,
	 *		String strLocationCode, 
	 *		Long langID
	 * @return List
	 * @author vidhya
	 */
	public List genAudWiseDailyVito(Long userId, Long locId,
			String audPostId[], String vitoType, List vitoStringList,
			String strLocationCode, Long langID) {
		List dataList = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query
					.append("select max(em.empFname),max(em.empMname),max(em.empLname),count(br.billNo),sum(br.billNetAmount),br.audPostId "
							+ " FROM OrgEmpMst em,TrnBillRegister br,OrgUserpostRlt pm"
							+ " WHERE em.orgUserMst.userId=pm.orgUserMst.userId and pm.orgPostMstByPostId.postId = br.audPostId "
							+ " and br.billNo NOT IN(select vr.billNo from BillVitoRegister vr where vr.vitoType='"
							+ vitoType + "')");

			if (audPostId.length > 0) {
				if (audPostId.length == 1
						&& !audPostId[0].equalsIgnoreCase("-1")) {
					query.append(" and br.audPostId in (" + audPostId[0] + ")");
				} else if (audPostId.length >= 2) {
					query.append(" and br.audPostId in(");
					for (int i = 0; i < audPostId.length; i++)
						if (!audPostId[i].equalsIgnoreCase("-1")) {
							if (i != (audPostId.length - 1)) {
								query.append(audPostId[i] + ",");
							} else {
								query.append(audPostId[i]);
							}
						}
					query.append(")");
				}
				// query.append(" and br.ddoUserId="+ddoUserId);
			}
			query.append(" and br.locationCode = '" + strLocationCode + "'"
					+ " and em.cmnLanguageMst.langId=" + langID);
			query.append(" group by br.audPostId order by 1,2,3");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			List vitoList = null;
			int billCount = 0;
			double total = 0.0;
			if (resList != null && resList.size() > 0) {
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;

				while (it.hasNext()) {
					BillVitoVO vo = new BillVitoVO();
					vitoList = new ArrayList();
					Object[] tuple = (Object[]) it.next();
					vo.setSrNo(count);
					vo.setAuditorName(tuple[0] + " " + tuple[1] + " "
							+ tuple[2]);
					vo.setNoOfBills(Long.parseLong(tuple[3].toString()));
					vo.setTotalAmount((BigDecimal) tuple[4]);
					vo.setAudUserId(Long.parseLong(tuple[5].toString()));
					dataList.add(vo);

					vitoList.add(count);
					vitoList.add(tuple[0].toString() + tuple[1].toString()
							+ tuple[2].toString());
					vitoList.add(tuple[3].toString());
					vitoList.add(tuple[4].toString());
					vitoStringList.add(vitoList);
					vitoList = null;
					billCount = billCount
							+ Integer.parseInt(tuple[3].toString());
					total = total + Double.parseDouble(tuple[4].toString());
					count++;
				}
			}
			if (vitoStringList.size() > 0 && billCount > 0) {
				vitoList = new ArrayList();
				vitoList.add("Total");
				vitoList.add("No of Bills:");
				vitoList.add(billCount);
				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				String strAmount = dFormat.format(total);
				vitoList.add(strAmount);
				vitoStringList.add(vitoList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getVouchersForDist # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting the Chque Advice Detail
	 * 
	 * @param  Long userId, 
	 *         Long locId,
	 *         List vitoStringList,
     *		   String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getChqAdviceDtls(Long userId, Long locId, List vitoStringList,
			String strLocationCode) {
		List dataList = null;
		try {
			Session hibSession = getSession();
			Query sqlQuery = null;
			StringBuffer query = new StringBuffer();
			ResourceBundle bundle = ResourceBundle
					.getBundle("resources/billproc/billproc_en_US");
			String strStatus = bundle.getString("CANCELSTATUS");
			query
					.append("select cd.adviceNo ,cd.chequeNo,cd.partyName,cd.chequeAmt "
							+ " from TrnChequeDtls cd where cd.status!='"
							+ strStatus
							+ "' and cd.locationCode='"
							+ strLocationCode
							+ "' and cd.adviceNo IS NOT NULL and cd.chequeNo NOT IN(select vr.chequeNo from ChequeVitoRegister vr where vr.vitoType=72)");
			query.append(" order by cd.adviceNo ");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;
				List vitoList = null;
				double total = 0.0;
				while (it.hasNext()) {
					ChequeVitoVO vo = new ChequeVitoVO();
					Object[] tuple = (Object[]) it.next();
					if (tuple[0] != null) {
						vo.setSrNo(count);
						vo.setAdivceNo(Long.parseLong(tuple[0].toString()));
						vo.setChequeNo(Long.parseLong(tuple[1].toString()));
						vo.setPayeeName((String) tuple[2]);
						vo.setTotalAmount((BigDecimal) tuple[3]);
						dataList.add(vo);
						vitoList = new ArrayList();
						vitoList.add(count);
						vitoList.add(tuple[0].toString());
						vitoList.add(tuple[1].toString());
						vitoList.add(tuple[2].toString());
						vitoList.add(tuple[3].toString());
						vitoStringList.add(vitoList);
						total = total + Double.parseDouble(tuple[3].toString());
						vitoList = null;
						count++;
					}

				}
				if (vitoStringList.size() > 0 && total > 0) {
					vitoList = new ArrayList();
					vitoList.add("Total");
					vitoList.add("No  of  ");
					vitoList.add("Cheques :");
					vitoList.add(count - 1);

					DecimalFormat dFormat = new DecimalFormat();
					dFormat.applyPattern("#0.00");
					String strAmount = dFormat.format(total);

					vitoList.add(strAmount);

					vitoStringList.add(vitoList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in VoucherDAOImpl.getVouchersForDist # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting the Audit Wise Bill Type Vito
	 * 
	 * @param  Long userId, 
	 *         Long locId, 
	 *         Long subjectId,
	 *		   Long audPostId, 
	 *		   String vitoType, 
	 *		   List vitoStringList,
	 *		   String strLocationCode, 
	 *		   Long langID
	 * @return List
	 * @author vidhya
	 */
	// For Auditor Wise Bill Type Vito
	public List genAudWiseBillTypeVito(Long userId, Long locId, Long subjectId,
			Long audPostId, String vitoType, List vitoStringList,
			String strLocationCode, Long langID) {
		List dataList = null;
		//System.out.println("Subject ID" + subjectId);
		//System.out.println("Aud post ID :" + audPostId);
		//System.out.println("VitoType" + vitoType);

		try {
			Session hibSession = getSession();
			Query sqlQuery = null;
			StringBuffer query = new StringBuffer();
			query
					.append("select max(bt.subjectDesc),count(br.billNo),sum(br.billGrossAmount),max(br.audPostId),max(em.empFname) ,max(em.empMname),max(em.empLname) "
							+ " FROM TrnBillRegister br,MstBillType bt,OrgEmpMst em, OrgUserpostRlt pm"
							+ " WHERE em.orgUserMst.userId = pm.orgUserMst.userId and pm.orgPostMstByPostId.postId = br.audPostId and  br.subjectId=bt.subjectId and br.audPostId="
							+ audPostId
							+ " "
							+ " and  br.billNo NOT IN(select vr.billNo from BillVitoRegister vr where vr.vitoType='"
							+ vitoType + "')");
			if (subjectId != -1) {
				query.append(" and br.subjectId=" + subjectId);
			}
			query.append(" and br.locationCode = '" + strLocationCode + "'"
					+ " and bt.langId=" + langID
					+ " and em.cmnLanguageMst.langId=" + langID);
			query.append(" group by br.subjectId order by 1");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			double total = 0.0;
			int billTotal = 0;
			List vitoList = new ArrayList();
			if (resList != null && resList.size() > 0) {
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;
				while (it.hasNext()) {
					BillVitoVO vo = new BillVitoVO();
					Object[] tuple = (Object[]) it.next();
					vo.setSrNo(count);
					vo.setBillType((String) tuple[0]);
					vo.setNoOfBills(Long.parseLong(tuple[1].toString()));
					vo.setTotalAmount((BigDecimal) tuple[2]);
					vo.setAudUserId(Long.parseLong(tuple[3].toString()));
					vo.setAuditorName(tuple[4] + " " + tuple[5] + " "
							+ tuple[6]);
					dataList.add(vo);
					//System.out.println(tuple[0] + " - " + tuple[1] + " - "+ tuple[2]);
					vitoList = new ArrayList();
					if (tuple[0] != null) {
						vitoList.add(count);
						vitoList.add(tuple[0].toString());
						vitoList.add(tuple[1].toString());
						vitoList.add(tuple[2].toString());
						vitoStringList.add(vitoList);
						vitoList = null;
						total = total + Double.parseDouble(tuple[2].toString());
						billTotal = billTotal
								+ Integer.parseInt(tuple[1].toString());
						count++;
					}
				}
			}
			if (vitoStringList.size() > 0 && billTotal > 0) {
				vitoList = new ArrayList();
				vitoList.add("Total");
				vitoList.add("No of Bills");
				vitoList.add(billTotal);

				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				String strAmount = dFormat.format(total);
				vitoList.add(strAmount);
				vitoStringList.add(vitoList);

				vitoList = new ArrayList();
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");
				vitoStringList.add(vitoList);

				vitoList = new ArrayList();
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");
				vitoStringList.add(vitoList);

			}

		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getVouchersForDist # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting the Bill Transit Register For Auditor
	 * 
	 * @param  Long userId, 
	 * 		Long locId, 
	 * 		Long audPostId,
	 *		String vitoType, 
	 *		List vitoStringList, 
	 *		String strLocationCode,
	 *		Long langID
	 * 		   
	 * @return List
	 * @author vidhya
	 */
	// For Bill Transit Register for audit
	public List genBillTranRegForAudit(Long userId, Long locId, Long audPostId,
			String vitoType, List vitoStringList, String strLocationCode,
			Long langID) {
		List dataList = null;
		try {
			Session hibSession = getSession();
			Query sqlQuery = null;
			StringBuffer query = new StringBuffer();

			query
					.append("select br.billCntrlNo,br.tokenNum,br.budmjrHd,bt.shortName,br.billNetAmount,dm.cardexNo,dm.shortName,em.empFname,em.empMname,em.empLname,br.audPostId,br.refNum "
							+ " from TrnBillRegister br,MstBillType bt,OrgEmpMst em, OrgDdoMst dm,OrgUserpostRlt pm "
							+ " where em.orgUserMst.userId = pm.orgUserMst.userId and pm.orgPostMstByPostId.postId = br.audPostId "
							+ " and br.ddoCode = dm.ddoCode "
							+ " and br.subjectId=bt.subjectId and br.tokenNum is not null  and br.billNo in (select distinct billNo from TrnBillMvmnt where  mvmntStatus IN ('BAUD')) and    br.billNo NOT IN(select distinct billNo from TrnBillMvmnt where  mvmntStatus IN ('BAPPRV_AUD','BRJCT_AUD'))"
							+ " and br.billNo NOT IN(select vr.billNo from BillVitoRegister vr where vr.vitoType='"
							+ vitoType + "')");

			if (audPostId != null) {
				query.append(" and br.audPostId=" + audPostId);
			}
			query.append(" and br.locationCode = '" + strLocationCode + "'"
					+ " and bt.langId=" + langID
					+ " and em.cmnLanguageMst.langId=" + langID
					+ " and dm.langId=" + langID);
			query.append(" order by br.refNum asc");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			List vitoList = null;
			double total = 0.0;
			long totalBill = 0;
			if (resList != null && resList.size() > 0) {
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					BillTransitVO vo = new BillTransitVO();
					Object[] tuple = (Object[]) it.next();
					vo.setBillCntrlNo((String) tuple[0]);
					try {
						vo.setTokenNo(Long.parseLong(tuple[1].toString()));
					} catch (Exception Ex) {
						if (tuple[1] == null) {
							System.out
									.println("tuple[1] Contain null values ...........................................");
						}
					}
					vo.setMajorHead((String) tuple[2]);
					vo.setBillType((String) tuple[3]);
					vo.setBillGrossAmount((BigDecimal) tuple[4]);
					vo.setCardexNo((String) tuple[5]);
					vo.setDdoName((String) tuple[6]);
					vo.setAuditorName(tuple[7] + " " + tuple[8] + " "
							+ tuple[9]);
					vo.setAudUserId(Long.parseLong(tuple[10].toString()));
					vo.setRefNum(tuple[11] != null ? tuple[11].toString() : "");

					dataList.add(vo);
					vitoList = new ArrayList();
					vitoList.add(tuple[11].toString());
					vitoList.add(tuple[0].toString());
					if (tuple[1] != null) {
						vitoList.add(tuple[1].toString());
					} else {
						vitoList.add(" ");
					}
					vitoList.add(tuple[2].toString());
					vitoList.add((String) tuple[3]);
					vitoList.add(tuple[5].toString());
					vitoList.add((String) tuple[6]);
					vitoList.add(tuple[4].toString());

					vitoStringList.add(vitoList);
					total = total + Double.parseDouble(tuple[4].toString());
					totalBill++;
					//System.out.println(tuple[0] + " - " + tuple[1] + " - "+ tuple[2] + " - " + tuple[3] + " - " + tuple[4]);
				}
			}
			if (vitoStringList.size() > 0 && totalBill > 0) {
				vitoList = new ArrayList();
				vitoList.add("Total No Of Bills:");
				vitoList.add("");
				vitoList.add(totalBill);
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");

				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				String strAmount = dFormat.format(total);

				vitoList.add(strAmount);
				vitoStringList.add(vitoList);

				vitoList = new ArrayList();
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");
				vitoStringList.add(vitoList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.genBillTranRegForAudit # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting the Bill Tran RegisterAfter Audit
	 * 
	 * @param  Long userId, 
	 * 		Long locId, 
	 * 		Long audPostId,
	 *		String vitoType, 
	 *		List vitoStringList, 
	 *		String strLocationCode,
	 *		Long langID
	 * @return List
	 * @author vidhya
	 */
	// For Bill Transit Register for audit
	public List genBillTranRegAfterAudit(Long userId, Long locId,
			Long audPostId, String status, String vitoType,
			List vitoStringList, String strLocationCode, Long langID) {
		List dataList = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append("select br.billCntrlNo,br.tokenNum,br.budmjrHd,bt.shortName,br.billNetAmount,dm.cardexNo,dm.shortName,em.empFname,em.empMname,em.empLname,br.audPostId,br.billNo "
							+ " from TrnBillRegister br,MstBillType bt,OrgEmpMst em, OrgDdoMst dm,OrgUserpostRlt pm "
							+ " where em.orgUserMst.userId = pm.orgUserMst.userId and pm.orgPostMstByPostId.postId = br.audPostId "
							+ " and br.ddoCode = dm.ddoCode "
							+ " and  br.subjectId=bt.subjectId and br.tokenNum is not null  and br.billNo IN(select distinct billNo from TrnBillMvmnt "
							+ " where mvmntStatus IN ");
			if (!status.equalsIgnoreCase("-1")) {
				query.append("('" + status + "'))");
			} else {
				query.append("('BAPPRV_AUD','BRJCT_AUD'))");
			}
			query
					.append(" and br.billNo NOT IN(select vr.billNo from BillVitoRegister vr where vr.vitoType='"
							+ vitoType + "')");

			if (audPostId != null) {
				query.append(" and br.audPostId=" + audPostId);
			}
			query.append(" and br.locationCode = '" + strLocationCode + "'"
					+ " and bt.langId=" + langID
					+ " and em.cmnLanguageMst.langId=" + langID
					+ " and dm.langId=" + langID);
			query.append(" order by br.tokenNum ");
			sqlQuery = hibSession.createQuery(query.toString());
			List vitoList = new ArrayList();
			long totalBill = 0;
			double total = 0.0;
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					BillTransitVO vo = new BillTransitVO();
					Object[] tuple = (Object[]) it.next();
					vo.setBillCntrlNo((String) tuple[0]);
					vo.setTokenNo(Long.parseLong(tuple[1].toString()));
					vo.setMajorHead((String) tuple[2]);
					vo.setBillType((String) tuple[3]);
					vo.setBillGrossAmount((BigDecimal) tuple[4]);
					vo.setCardexNo((String) tuple[5]);
					vo.setDdoName((String) tuple[6]);
					vo.setAuditorName(tuple[7] + " " + tuple[8] + " "
							+ tuple[9]);
					vo.setAudUserId(Long.parseLong(tuple[10].toString()));
					String strStatus = getAftAudStatus(Long.parseLong(tuple[11]
							.toString()), strLocationCode);
					vo.setStatus(strStatus);
					dataList.add(vo);

					vitoList = new ArrayList();
					vitoList.add(tuple[0].toString());
					vitoList.add(tuple[1].toString());
					vitoList.add(tuple[2].toString());
					vitoList.add(tuple[3].toString());
					vitoList.add(tuple[5].toString());
					vitoList.add(tuple[6].toString());
					vitoList.add(tuple[4].toString());
					vitoList.add(strStatus);
					vitoStringList.add(vitoList);
					total = total + Double.parseDouble(tuple[4].toString());
					totalBill++;
				}
			}
			if (vitoStringList.size() > 0 && totalBill > 0) {
				vitoList = new ArrayList();
				vitoList.add("Total No Of Bills:");
				vitoList.add("");
				vitoList.add(totalBill);
				vitoList.add("");
				vitoList.add("");
				vitoList.add("");

				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				String strAmount = dFormat.format(total);

				vitoList.add(strAmount);
				vitoStringList.add(vitoList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.genBillTranRegForAudit # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting the After Audit Status
	 * 
	 * @param  Long billNo,
	 * 		   String strLocationCode
	 * @return String
	 * @author vidhya
	 */
	public String getAftAudStatus(Long billNo, String strLocationCode) {
		String strStatus = null;
		Session hibSession = getSession();
		Query sqlQuery = null;
		StringBuffer query = new StringBuffer();
		try {
			query
					.append("select bm.mvmntStatus from TrnBillMvmnt bm where bm.billNo="
							+ billNo
							+ " and bm.mvmntStatus in('BAPPRV_AUD','BRJCT_AUD') and bm.locationCode='"
							+ strLocationCode + "'");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList.size() > 0) {
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					Object tuple = (Object) it.next();
					if (tuple.toString().equalsIgnoreCase("BAPPRV_AUD")) {
						strStatus = "Approved";
					} else if (tuple.toString().equalsIgnoreCase("BRJCT_AUD")) {
						strStatus = "Rejected";
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strStatus;
	}
	 /**
	 * Method to getting Chque Drawn Register Report
	 * 
	 * @param  Long userId, 
	 * 		   Long locId,
	 * 		   List vitoStringList,
	 *		   String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getChqDrawnRegRpt(Long userId, Long locId, List vitoStringList,
			String strLocationCode) {
		List dataList = null;
		Query sqlQuery = null;
		ResourceBundle bundle = ResourceBundle
				.getBundle("resources/billproc/BillprocConstants");
		String strStatus = bundle.getString("STATUS.CheqCancel");
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append("select distinct cd.chequeNo,br.tokenNum,cd.printDate,cd.partyName,br.billNetAmount,cd.chequeAmt "
							+ " from TrnChequeDtls cd,TrnBillRegister br,RltBillCheque bc "
							+ " where cd.chequeId=bc.chequeId and bc.billNo=br.billNo and cd.status!='"
							+ strStatus
							+ "' and cd.locationCode = '"
							+ strLocationCode
							+ "' and cd.printDate IS NOT NULL and br.tokenNum is not null and "
							+ " cd.chequeNo NOT IN(select vr.chequeNo from ChequeVitoRegister vr where vr.vitoType=76) ");

			query.append(" order by cd.chequeNo,br.tokenNum asc");
			sqlQuery = hibSession.createQuery(query.toString());

			List resList = sqlQuery.list();
			List vitoList = null;
			Integer count = 1;
			double total = 0.0;

			// new code Started
			List chequeList = new ArrayList();
			if (resList != null && resList.size() > 0) {
				Iterator chequeIt = resList.iterator();
				while (chequeIt.hasNext()) {
					Object[] data = (Object[]) chequeIt.next();
					String strChequeNo = data[0].toString();
					if (chequeList.contains(strChequeNo) == false) {
						chequeList.add(strChequeNo);
					}
				}
			}
			dataList = new ArrayList();

			for (int c = 0; c < chequeList.size(); c++) {
				String strChequeNo = chequeList.get(c).toString();
				String strTokenNumber = "";
				String strChequeDate = "";
				String strPayeeName = "";
				String strBillAmount = "";
				String strChequeAmount = "";
				String strTokenForPrint = "";
				String strBillAmountForPrint = "";
				if (resList != null && resList.size() > 0) {
					Iterator it = resList.iterator();
					while (it.hasNext()) {

						Object[] tuple = (Object[]) it.next();
						if (tuple[0] != null
								&& strChequeNo.equalsIgnoreCase(tuple[0]
										.toString()) == true) {
							strTokenNumber = strTokenNumber
									+ tuple[1].toString() + "-";
							strChequeDate = tuple[2].toString();
							strPayeeName = tuple[3].toString();
							strBillAmount = strBillAmount
									+ tuple[4].toString().trim() + "-";
							strChequeAmount = tuple[5].toString();
							strTokenForPrint = strTokenForPrint
									+ tuple[1].toString() + ",";
							strBillAmountForPrint = strBillAmountForPrint
									+ tuple[4].toString().trim() + ",";
						}
					}

				}
				ChequeVitoVOString vo = new ChequeVitoVOString();
				vo.setSrNo(c + 1);
				vo.setChequeNo(strChequeNo);
				vo.setTokenNo(strTokenNumber);
				String printDate = new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(strChequeDate));
				vo.setChequeDate(printDate);
				vo.setPayeeName(strPayeeName);
				vo.setBillAmount(strBillAmount);
				vo.setChequeAmount(strChequeAmount);
				dataList.add(vo);

				strTokenForPrint = strTokenForPrint.substring(0,
						strTokenForPrint.lastIndexOf(","));
				strBillAmountForPrint = strBillAmountForPrint.substring(0,
						strBillAmountForPrint.lastIndexOf(","));
				vitoList = new ArrayList();
				vitoList.add(count);
				vitoList.add(strChequeNo);
				vitoList.add(strTokenForPrint);
				vitoList.add(printDate);
				vitoList.add(strPayeeName);
				vitoList.add(strBillAmountForPrint);
				vitoList.add(strChequeAmount);
				vitoStringList.add(vitoList);
				total = total + Double.parseDouble(strChequeAmount);
				count++;
			}
			if (vitoStringList.size() > 0 && total > 0.0) {
				vitoList = new ArrayList();
				vitoList.add("Total");
				vitoList.add("No Of ");
				vitoList.add("Cheque");
				vitoList.add(count - 1);
				vitoList.add("");
				vitoList.add("");

				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				String strAmount = dFormat.format(total);

				vitoList.add(strAmount);
				vitoStringList.add(vitoList);
			}
			return dataList;

			/*
			 * if (resList != null && resList.size() > 0) { dataList = new
			 * ArrayList(); Iterator it = resList.iterator();
			 * 
			 * while(it.hasNext()) { ChequeVitoVO vo = new ChequeVitoVO();
			 * Object[] tuple = (Object[])it.next(); if(tuple[0] != null) {
			 * 
			 * 
			 * vo.setSrNo(count);
			 * vo.setChequeNo(Long.parseLong(tuple[0].toString()));
			 * vo.setTokenNo(Long.parseLong(tuple[1].toString())); String
			 * printDate=new SimpleDateFormat("dd/MM/yyyy").format(new
			 * SimpleDateFormat("yyyy-MM-dd").parse(tuple[2].toString()));
			 * vo.setChequeDate(printDate); vo.setPayeeName((String) tuple[3]);
			 * vo.setBillAmount((BigDecimal) tuple[4]);
			 * vo.setChequeAmount((BigDecimal) tuple[5]); dataList.add(vo);
			 * vitoList=new ArrayList(); vitoList.add(count);
			 * vitoList.add(tuple[0].toString());
			 * vitoList.add(tuple[1].toString()); vitoList.add(printDate);
			 * vitoList.add(tuple[3].toString());
			 * vitoList.add(tuple[4].toString());
			 * vitoList.add(tuple[5].toString()); vitoStringList.add(vitoList);
			 * total=total+Double.parseDouble(tuple[5].toString()); count++; } } }
			 * if(vitoStringList.size()>0 && total>0.0) { vitoList=new
			 * ArrayList(); vitoList.add("Total"); vitoList.add("No Of ");
			 * vitoList.add("Cheque"); vitoList.add(count-1); vitoList.add("");
			 * vitoList.add("");
			 * 
			 * DecimalFormat dFormat=new DecimalFormat();
			 * dFormat.applyPattern("#0.00"); String
			 * strAmount=dFormat.format(total);
			 * 
			 * 
			 * vitoList.add(strAmount); vitoStringList.add(vitoList); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in VoucherDAOImpl.getVouchersForDist # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting Deli Acc Details
	 * 
	 * @param  Long userId, 
	 * 		   Long locId,
	 *         String majorhead[],
	 *		   List vitoStringList,
	 *		   String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getDeliAccDtls(Long userId, Long locId, String majorhead[],
			List vitoStringList, String strLocationCode) {
		List dataList = null;
		Query sqlQuery = null;
		ResourceBundle bundle = ResourceBundle
				.getBundle("resources/billproc/billproc_en_US");
		String strStatus = bundle.getString("CANCELSTATUS");
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			List resList = null;

			query
					.append("select  vd.majorHead,count(distinct vd.billNo),count(cd.chequeId),sum(cd.chequeAmt)"
							+ "from TrnVoucherDetails vd,TrnChequeDtls cd,RltBillCheque bc where  vd.billNo=bc.billNo and bc.chequeId=cd.chequeId and cd.adviceNo IS NOT NULL and cd.status!='"
							+ strStatus
							+ "' and cd.chequeNo NOT IN(select vr.chequeNo from ChequeVitoRegister vr where vr.vitoType=77)");

			if (majorhead != null) {
				if (majorhead.length == 1
						&& !majorhead[0].equalsIgnoreCase("-1")) {
					query.append(" and vd.majorHead in (" + majorhead[0] + ")");
				} else if (majorhead.length >= 2) {
					query.append(" and vd.majorHead in(");
					for (int i = 0; i < majorhead.length; i++)
						if (!majorhead[i].equalsIgnoreCase("-1")) {
							if (i != (majorhead.length - 1)) {
								query.append(majorhead[i] + ",");
							} else {
								query.append(majorhead[i]);
							}
						}
					query.append(")");
				}
			}
			query.append(" and cd.locationCode = '" + strLocationCode + "'");
			query.append(" group by vd.majorHead");
			sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			List vitoList = null;
			double total = 0.0;
			long totalVoucher = 0;
			long totalCheque = 0;
			if (resList != null && resList.size() > 0) {
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;

				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ChequeVitoVO vo = new ChequeVitoVO();
					vo.setSrNo(count);
					vo.setMajorHead(tuple[0].toString());
					vo.setTotalVoucher(Long.parseLong(tuple[1].toString()));
					vo.setTotalcheques(Long.parseLong(tuple[2].toString()));
					vo.setTotalAmount((BigDecimal) tuple[3]);
					dataList.add(vo);
					vitoList = new ArrayList();
					vitoList.add(count);
					vitoList.add(tuple[0].toString());
					vitoList.add(tuple[1].toString());
					vitoList.add(tuple[2].toString());
					vitoList.add(tuple[3].toString());
					vitoStringList.add(vitoList);
					total = total + Double.parseDouble(tuple[3].toString());
					totalVoucher = totalVoucher
							+ Long.parseLong(tuple[1].toString());
					totalCheque = totalCheque
							+ Long.parseLong(tuple[2].toString());
					count++;
				}
			}
			if (vitoStringList.size() > 0 && total > 0.0) {
				vitoList = new ArrayList();
				vitoList.add("Total:");
				vitoList.add("");
				vitoList.add(totalVoucher);
				vitoList.add(totalCheque);

				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				String strAmount = dFormat.format(total);
				vitoList.add(strAmount);
				vitoStringList.add(vitoList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getDeliaccDtls # \n"
							+ e);
		}
		return dataList;
	}
	 /**
	 * Method to getting Major  Dtls
	 * 
	 * @param  Long userId, 
	 * 		   Long locId,
	 *         String majorhead[],
	 *		   String strLocationCode
	 *		   Long langID
	 * @return List
	 * @author vidhya
	 */
	public List getMajorDtls(Long userId, Long locId, String majorhead,
			String strLocationCode, Long langID) {
		List majorHeadList = null;

		try {
			Session hibSession = getSession();
			Query sqlQuery = null;

			StringBuffer query = new StringBuffer();
			query
					.append("select cd.adviceNo,cd.chequeNo,cd.partyName,bt.shortName,br.tokenNum,cd.chequeAmt from "
							+ "TrnBillRegister br,TrnChequeDtls cd,RltBillCheque bc, MstBillType bt"
							+ " where cd.chequeId=bc.chequeId and bc.billNo=br.billNo and br.subjectId=bt.subjectId and br.budmjrHd='"
							+ majorhead + "'and cd.adviceNo IS NOT NULL");

			query.append(" and cd.locationCode = '" + strLocationCode + "'"
					+ " and bt.langId=" + langID);
			sqlQuery = hibSession.createQuery(query.toString());
			Integer count = 1;
			List resList = sqlQuery.list();
			// new code Start Here

			List chequeList = new ArrayList();
			if (resList != null && resList.size() > 0) {
				Iterator chequeIt = resList.iterator();
				while (chequeIt.hasNext()) {
					Object[] data = (Object[]) chequeIt.next();
					String strChequeNo = data[1].toString();
					if (chequeList.contains(strChequeNo) == false) {
						chequeList.add(strChequeNo);
					}
				}
			}
			majorHeadList = new ArrayList();

			for (int c = 0; c < chequeList.size(); c++) {
				String strChequeNo = chequeList.get(c).toString();
				String strTokenNumber = "";
				String strChequeDate = "";
				String strPayeeName = "";
				String strBillAmount = "";
				String strChequeAmount = "";
				String strTokenForPrint = "";
				String strBillAmountForPrint = "";
				String strBillType = "";
				String strAdviceNo = "";
				if (resList != null && resList.size() > 0) {
					Iterator it = resList.iterator();
					while (it.hasNext()) {

						Object[] tuple = (Object[]) it.next();
						if (tuple[0] != null
								&& strChequeNo.equalsIgnoreCase(tuple[1]
										.toString()) == true) {
							strAdviceNo = tuple[0].toString();
							strPayeeName = tuple[2].toString() + "-";
							strBillType = strBillType + tuple[3].toString()
									+ "-";
							strTokenNumber = strTokenNumber
									+ tuple[4].toString() + "-";
							strChequeAmount = tuple[5].toString();
						}
					}

				}
				ChequeVitoVOString vo = new ChequeVitoVOString();
				vo.setSrNo(c + 1);
				vo.setAdivceNo(strAdviceNo);
				vo.setChequeNo(strChequeNo);
				vo.setPayeeName(strPayeeName);
				vo.setBillType(strBillType);
				vo.setTokenNo(strTokenNumber);
				vo.setChequeAmount(strChequeAmount);
				majorHeadList.add(vo);
			}
			/*
			 * if (resList!=null && resList.size()>0) { majorHeadList=new
			 * ArrayList(); Iterator it = resList.iterator();
			 * while(it.hasNext()) { Object[] tuple = (Object[])it.next();
			 * ChequeVitoVO vo=new ChequeVitoVO(); vo.setSrNo(count);
			 * vo.setAdivceNo(Long.parseLong(tuple[0].toString()));
			 * vo.setChequeNo(Long.parseLong(tuple[1].toString()));
			 * vo.setPayeeName((String)tuple[2]);
			 * vo.setBillType(tuple[3].toString());
			 * vo.setTokenNo(Long.parseLong(tuple[4].toString()));
			 * vo.setChequeAmount((BigDecimal)(tuple[5]));
			 * majorHeadList.add(vo); count++; } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getDeliaccDtls # \n"
							+ e);
		}
		return majorHeadList;
	}
	 /**
	 * Method to getting the Bill Type
	 * 
	 * @param  String lStrLangId, String lstrLocId
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getBillType(String lStrLangId, String lstrLocId) {
		ArrayList arrBillType = new ArrayList();
		Connection lCon = null;
		PreparedStatement lStmt = null;
		ResultSet lRs = null;
		String subjectId = null;
		String subjectDesc = null;

		try {
			lCon = DBConnection.getConnection();
			StringBuffer lsb = new StringBuffer();
			lsb = new StringBuffer(
					"select subject_id,subject_desc from mst_bill_type where activate_flag=1 ");
			lStmt = lCon.prepareStatement(lsb.toString());
			lRs = lStmt.executeQuery();
			while (lRs.next()) {
				ComboValuesVO vo = new ComboValuesVO();
				subjectId = lRs.getString("subject_id");
				subjectDesc = lRs.getString("subject_desc");
				vo.setId(subjectId);
				vo.setDesc(subjectDesc);
				arrBillType.add(vo);
			}
		} catch (SQLException se) {
			glogger.error("SQLException::" + se.getMessage(), se);

		} catch (Exception e) {
			glogger.error("Exception::" + e.getMessage(), e);
		}
		return arrBillType;
	}
	 /**
	 * Method to getting the Major Head
	 * 
	 * @param  String lStrLangId, String lstrLocId
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getMajorHead(String lStrLangId, String lstrLocId) {
		ArrayList arrMjrHead = new ArrayList();
		Connection lCon = null;
		PreparedStatement lStmt = null;
		ResultSet lRs = null;
		String mjrCode = null;

		try {
			lCon = DBConnection.getConnection();
			StringBuffer lsb = new StringBuffer();
			lsb = new StringBuffer(
					"select distinct BUDMJRHD_CODE from sgva_budmjrhd_mst where  LANG_ID='"
							+ lStrLangId + "' and LOC_ID='" + lstrLocId
							+ "' order by BUDMJRHD_CODE");
			lStmt = lCon.prepareStatement(lsb.toString());
			lRs = lStmt.executeQuery();
			while (lRs.next()) {
				ComboValuesVO vo = new ComboValuesVO();
				mjrCode = lRs.getString("BUDMJRHD_CODE");
				vo.setId(mjrCode);
				vo.setDesc(mjrCode);
				arrMjrHead.add(vo);
			}
		} catch (SQLException se) {
			glogger.error("SQLException::" + se.getMessage(), se);
		} catch (Exception e) {
			glogger.error("Exception::" + e.getMessage(), e);
		}
		return arrMjrHead;
	}
	 /**
	 * Method to getting the DDO Name
	 * 
	 * @param  Hashtable hashtable, String lStrLangId,String lstrLocId
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getDDOName(Hashtable hashtable, String lStrLangId,String lstrLocId) {
		ArrayList arrDDOName = new ArrayList();
		Connection lCon = null;
		PreparedStatement lStmt = null;
		ResultSet lRs = null;
		String ddoCode = null;
		String cardexNo = null;
		try {
			LoginDetails loginVO = (LoginDetails) hashtable.get("loginDetails");
			String locID = loginVO.getLocation().getLocationCode();
			//System.out.println("Hay i am getting now location id like" + locID);
			lCon = DBConnection.getConnection();
			StringBuffer lsb = new StringBuffer();
			//System.out.println("Location ID=" + lstrLocId);
			lsb = new StringBuffer(
					"SELECT D.DDO_CODE,CARDEX_NO FROM org_ddo_mst D , RLT_DDO_ORG R WHERE D.DDO_CODE = R.DDO_CODE  AND R.OFFICE_CODE = '"
							+ locID + "' ORDER BY 2");
			// lsb = new StringBuffer("SELECT DDO_CODE,CARDEX_NO FROM
			// org_ddo_mst order by CARDEX_NO");
			lStmt = lCon.prepareStatement(lsb.toString());
			lRs = lStmt.executeQuery();
			while (lRs.next()) {
				ComboValuesVO vo = new ComboValuesVO();
				ddoCode = lRs.getString("DDO_CODE");
				cardexNo = lRs.getString("CARDEX_NO");
				vo.setId(ddoCode);
				vo.setDesc(cardexNo);
				arrDDOName.add(vo);
			}
		} catch (SQLException se) {
			glogger.error("SQLException::" + se.getMessage(), se);
		} catch (Exception e) {
			glogger.error("Exception::" + e.getMessage(), e);
		}
		return arrDDOName;
	}
	 /**
	 * Method to getting the Auditor Name
	 * 
	 * @param  Hashtable hashtable, String lStrLangId,String lstrLocId
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getAuditorName(Hashtable hashtable, String lStrLangId,
			String lstrLocId) {
		ArrayList arrAudName = new ArrayList();
		Connection lCon = null;
		PreparedStatement lStmt = null;
		ResultSet lRs = null;
		String postId = null;
		String fName = null;
		String mName = null;
		String lName = null;

		try {
			String audit = "AUDIT BRANCH";
			LoginDetails loginVO = (LoginDetails) hashtable.get("loginDetails");
			long locID = loginVO.getLocation().getLocId();
			lCon = DBConnection.getConnection();
			StringBuffer lsb = new StringBuffer();

			lsb = new StringBuffer(
					"select pm.post_id,em.emp_fname,em.Emp_mname,em.emp_lname "
							+ " FROM Org_emp_mst em,org_userpost_rlt pm,acl_role_mst rm,acl_postrole_rlt rl,org_post_mst p , acl_role_details_rlt rd "
							+ "	where rl.post_id=pm.post_id and pm.user_id=em.user_id and rl.role_id=rm.role_id  and rm.role_id = rd.role_id "
							+ "	and rl.post_id = p.post_id and p.location_code = "
							+ locID
							+ " and rd.ROLE_SHORT_NAME ='AUDIT BRANCH' and rd.lang_id = 1");
			lStmt = lCon.prepareStatement(lsb.toString());
			lRs = lStmt.executeQuery();
			while (lRs.next()) {
				ComboValuesVO vo = new ComboValuesVO();
				postId = lRs.getString("post_id");
				fName = lRs.getString("emp_fname");
				mName = lRs.getString("Emp_mname");
				lName = lRs.getString("emp_lname");
				vo.setId(postId);
				vo.setDesc(fName + " " + mName + " " + lName);
				arrAudName.add(vo);
			}
		} catch (SQLException se) {
			glogger.error("SQLException::" + se.getMessage(), se);

		} catch (Exception e) {
			glogger.error("Exception::" + e.getMessage(), e);
		}
		return arrAudName;
	}
	 /**
	 * Method to getting the Bill Mode
	 * 
	 * @param  String lStrLangId, String lstrLocId
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getBillMode(String lStrLangId, String lstrLocId) {
		ArrayList arrBillMode = new ArrayList();

		try {
			ComboValuesVO vo = new ComboValuesVO();
			String modeId = "0";
			String modeName = "On Line";
			vo.setId(modeId);
			vo.setDesc(modeName);
			arrBillMode.add(vo);
			ComboValuesVO vo1 = new ComboValuesVO();
			String modeId1 = "1";
			String modeName1 = "Physical";
			vo1.setId(modeId1);
			vo1.setDesc(modeName1);
			arrBillMode.add(vo1);
		} catch (Exception e) {
			glogger.error("Exception::" + e.getMessage(), e);
		}
		return arrBillMode;
	}
	 /**
	 * Method to getting the Audit Status
	 * 
	 * @param  String lStrLangId, String lstrLocId
	 * @return ArrayList
	 * @author vidhya
	 */
	// Get audit Status
	public ArrayList getAuditStatus(String lStrLangId, String lstrLocId) {
		ArrayList arrAuditStatus = new ArrayList();
		try {
			ComboValuesVO vo = new ComboValuesVO();
			String auditstatuscode = "BAPPRV_AUD";
			String auditstatusname = "Approved";
			vo.setId(auditstatuscode);
			vo.setDesc(auditstatusname);
			arrAuditStatus.add(vo);

			ComboValuesVO vo1 = new ComboValuesVO();
			String auditstatuscode1 = "BRJCT_AUD";
			String auditstatusname1 = "Rejected";
			vo1.setId(auditstatuscode1);
			vo1.setDesc(auditstatusname1);
			arrAuditStatus.add(vo1);

			ComboValuesVO vo3 = new ComboValuesVO();
			String auditstatuscode2 = "3";
			String auditstatusname2 = "Pending";
			vo3.setId(auditstatuscode2);
			vo3.setDesc(auditstatusname2);
			arrAuditStatus.add(vo3);
		} catch (Exception e) {
			glogger.error("Exception::" + e.getMessage(), e);
		}
		return arrAuditStatus;
	}
	 /**
	 * Method to getting After Audit Status
	 * 
	 * @param String lStrLangId, String lstrLocId
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getAfterAuditStatus(String lStrLangId, String lstrLocId) {
		ArrayList arrAuditStatus = new ArrayList();

		try {
			ComboValuesVO vo = new ComboValuesVO();
			String auditstatuscode = "1";
			String auditstatusname = "Approved";
			vo.setId(auditstatuscode);
			vo.setDesc(auditstatusname);
			arrAuditStatus.add(vo);

			ComboValuesVO vo1 = new ComboValuesVO();
			String auditstatuscode1 = "2";
			String auditstatusname1 = "Rejected";
			vo1.setId(auditstatuscode1);
			vo1.setDesc(auditstatusname1);
			arrAuditStatus.add(vo1);

		} catch (Exception e) {
			glogger.error("Exception::" + e.getMessage(), e);
		}
		return arrAuditStatus;
	}
	 /**
	 * Method to getting the chq Status
	 * 
	 * @param  String lStrLangId, String lstrLocId
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getStatus(String lStrLangId, String lstrLocId) {
		ArrayList arrStatus = new ArrayList();

		try {
			ComboValuesVO vo = new ComboValuesVO();
			String statuscode = "CHQ_WRTN";
			String statusname = "Written";
			vo.setId(statuscode);
			vo.setDesc(statusname);
			arrStatus.add(vo);

			ComboValuesVO vo1 = new ComboValuesVO();
			String statuscode1 = "CHQ_PRNT";
			String statusname1 = "Printed";
			vo1.setId(statuscode1);
			vo1.setDesc(statusname1);
			arrStatus.add(vo1);

			ComboValuesVO vo3 = new ComboValuesVO();
			String statuscode3 = "CHQ_CSTDN";
			String statusname3 = "Custody";
			vo3.setId(statuscode3);
			vo3.setDesc(statusname3);
			arrStatus.add(vo3);

			ComboValuesVO vo2 = new ComboValuesVO();
			String statuscode2 = "CHQ_CNTR";
			String statusname2 = "Dispatched";
			vo2.setId(statuscode2);
			vo2.setDesc(statusname2);
			arrStatus.add(vo2);

			ComboValuesVO vo4 = new ComboValuesVO();
			String statuscode4 = "CHQ_DSPTCH_DDO";
			String statusname4 = "Delivered";
			vo4.setId(statuscode4);
			vo4.setDesc(statusname4);
			arrStatus.add(vo4);

			ComboValuesVO vo5 = new ComboValuesVO();
			String statuscode5 = "CANCELLED";
			String statusname5 = "Cancelled";
			vo5.setId(statuscode5);
			vo5.setDesc(statusname5);
			arrStatus.add(vo5);
		} catch (Exception e) {
			glogger.error("Exception::" + e.getMessage(), e);
		}
		return arrStatus;
	}
	 /**
	 * Method to getting the Inwarded Report
	 * 
	 * @param  	Long subjectId, String fromDate,
	 *			String toDate, Long modeId, String mjrHead, String ddoCode[],
	 *			String audPostId[], Long locId, String strLocationCode, Long langID
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getInwardedReport(Long subjectId, String fromDate,
			String toDate, Long modeId, String mjrHead, String ddoCode[],
			String audPostId[], Long locId, String strLocationCode, Long langID) {
		ArrayList arrOuter = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append("select br.billCntrlNo, br.billNetAmount, bt.shortName ,br.billDate ,dm.cardexNo, dm.shortName, br.inwardDt, br.budmjrHd ,br.tokenNum,br.phyBill "
							+ " from TrnBillRegister br,MstBillType bt, OrgDdoMst dm "
							+ " where br.subjectId = bt.subjectId and br.ddoCode = dm.ddoCode ");
			if (fromDate != null && toDate != null) {
				query.append(" and to_char(br.inwardDt,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(br.inwardDt,'yyyy-mm-dd')<='" + toDate
						+ "'");
			}
			if (subjectId != -1) {
				query.append(" and br.subjectId=" + subjectId);
			}
			if (modeId != -1) {
				query.append(" and br.phyBill=" + modeId);
			}
			if (!mjrHead.equalsIgnoreCase("-1")) {
				query.append(" and br.budmjrHd ='" + mjrHead + "'");
			}
			if (ddoCode.length > 0) {
				if (ddoCode.length == 1 && !ddoCode[0].equalsIgnoreCase("-1")) {
					query.append(" and br.ddoCode in ('" + ddoCode[0] + "')");
				} else if (ddoCode.length >= 2) {
					query.append(" and br.ddoCode in(");
					for (int i = 0; i < ddoCode.length; i++)
						if (!ddoCode[i].equalsIgnoreCase("-1")) {
							if (i != (ddoCode.length - 1)) {
								query.append("'" + ddoCode[i] + "',");
							} else {
								query.append("'" + ddoCode[i] + "'");
							}
						}
					query.append(")");
				}
			}
			if (audPostId.length > 0) {
				if (audPostId.length == 1
						&& !audPostId[0].equalsIgnoreCase("-1")) {
					query.append(" and br.audPostId in (" + audPostId[0] + ")");
				} else if (audPostId.length >= 2) {
					query.append(" and br.audPostId in(");
					for (int i = 0; i < audPostId.length; i++)
						if (!audPostId[i].equalsIgnoreCase("-1")) {
							if (i != (audPostId.length - 1))
								query.append(audPostId[i] + ",");
							else
								query.append(audPostId[i]);
						}
					query.append(")");
				}
			}
			query.append(" and br.tsryOfficeCode = '" + strLocationCode + "'"
					+ " and bt.langId=" + langID + " and  dm.langId=" + langID
					+ " and br.tokenNum is not null ");
			// query.append(" order by br.createdDate desc");
			query.append(" order by br.tokenNum asc");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList != null) {
				arrOuter = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arrInner = new ArrayList();

					arrInner.add(count);
					arrInner.add(tuple[0]);
					DecimalFormat dFormat = new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount = Double.parseDouble(tuple[1].toString());
					String strAmount = dFormat.format(dAmount);
					arrInner.add(strAmount);
					arrInner.add(tuple[2] != null ? tuple[2] : "");
					String billDate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd")
									.parse(tuple[3].toString()));
					arrInner.add(billDate);
					arrInner.add(tuple[4]);
					arrInner.add(tuple[5] != null ? tuple[5] : "");
					String inwardDate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd")
									.parse(tuple[6].toString()));
					arrInner.add(inwardDate);
					arrInner.add(tuple[7]);
					arrInner.add(tuple[8]);
					//System.out.println(tuple[0] + " " + tuple[1] + " "+ tuple[2] + " " + tuple[3] + " " + tuple[4] + " "+ tuple[5]);
					if (Long.parseLong((tuple[9].toString())) == 0) {
						arrInner.add("On line");
					} else {
						arrInner.add("Physical");
					}
					arrOuter.add(arrInner);
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.setDistributedFlag # \n"
							+ e);
		}
		return arrOuter;
	}
	 /**
	 * Method to getting the Bill Dtl By BTra Report
	 * 
	 * @param 	Long subjectId, String fromDate,
	 *			String toDate, String mjrHead, String ddoCode[], Long tokenNo,
	 *			Long locId, String strLocationCode, Long langID
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getBillDtlByBTraRpt(Long subjectId, String fromDate,
			String toDate, String mjrHead, String ddoCode[], Long tokenNo,
			Long locId, String strLocationCode, Long langID) {
		ArrayList arrOuter = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query
					.append("select br.billCntrlNo, br.billNetAmount, bt.shortName ,br.billDate ,dm.cardexNo ,dm.shortName,"
							+ " br.inwardDt, br.budmjrHd ,br.tokenNum,br.billNo "
							+ " FROM TrnBillRegister br,MstBillType bt,OrgDdoMst dm "
							+ " WHERE br.subjectId = bt.subjectId  and br.ddoCode = dm.ddoCode");
			// " and em.orgUserMst.userId = pm.orgUserMst.userId");

			// " WHERE br.subjectId = bt.subjectId and
			// pm.orgPostMstByPostId.postId = br.audPostId and br.ddoCode =
			// dm.ddoCode " +
			if (fromDate != null && toDate != null) {
				query.append(" and to_char(br.inwardDt,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(br.inwardDt,'yyyy-mm-dd') <= '"
						+ toDate + "'");
			}
			if (subjectId != -1) {
				query.append(" and br.subjectId=" + subjectId);
			}
			if (!mjrHead.equalsIgnoreCase("-1")) {
				query.append(" and br.budmjrHd ='" + mjrHead + "'");
			}
			if (ddoCode.length > 0) {
				if (ddoCode.length == 1 && !ddoCode[0].equalsIgnoreCase("-1")) {
					query.append(" and br.ddoCode in ('" + ddoCode[0] + "')");
				} else if (ddoCode.length >= 2) {
					query.append(" and br.ddoCode in(");
					for (int i = 0; i < ddoCode.length; i++)
						if (!ddoCode[i].equalsIgnoreCase("-1")) {
							if (i != (ddoCode.length - 1)) {
								query.append("'" + ddoCode[i] + "',");
							} else {
								query.append("'" + ddoCode[i] + "'");
							}
						}
					query.append(")");
				}
			}
			if (tokenNo != null) {
				query.append(" and br.tokenNum =" + tokenNo);
			}
			query.append(" and br.tsryOfficeCode = '" + strLocationCode + "'"
					+ " and bt.langId=" + langID + " and  dm.langId=" + langID);
			// query.append(" order by br.createdDate desc");
			query.append(" order by br.tokenNum asc");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList != null) {
				arrOuter = new ArrayList();
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arrInner = new ArrayList();
					arrInner.add(tuple[0]);
					DecimalFormat dFormat = new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount = Double.parseDouble(tuple[1].toString());
					String strAmount = dFormat.format(dAmount);
					arrInner.add(strAmount);

					arrInner.add(tuple[2] != null ? tuple[2] : "");
					String billDate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd")
									.parse(tuple[3].toString()));
					arrInner.add(billDate);
					arrInner.add(tuple[4]);
					arrInner.add(tuple[5] != null ? tuple[5] : "");
					String inwardDate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd")
									.parse(tuple[6].toString()));
					arrInner.add(inwardDate);
					arrInner.add(tuple[7]);
					arrInner.add(tuple[8] != null ? tuple[8] : "-");
					String strStatus = getBillStatus(Long.parseLong(tuple[9]
							.toString()), strLocationCode);
					arrInner.add(strStatus);
					int mvmntId = getMaxMvmntId(Long.parseLong(tuple[9]
							.toString()), strLocationCode);
					arrInner.add(getLyingWith(Long.parseLong(tuple[9]
							.toString()), mvmntId, strLocationCode, langID));
					arrInner.add(tuple[9]);
					arrOuter.add(arrInner);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getBillDtlByBTraRpt # \n"
							+ e);
		}
		return arrOuter;
	}
	 /**
	 * Method to getting the Lying With
	 * 
	 * @param   Long billNo, int mvmntId,
     *			String strLocationCode, Long langID
	 * @return String
	 * @author vidhya
	 */
	public String getLyingWith(Long billNo, int mvmntId,
			String strLocationCode, Long langID) {
		String strLyingWith = "-";
		Query sqlQuery = null;
		StringBuffer query = new StringBuffer();

		try {
			Session hibSession = getSession();
			query
					.append("select em.empFname,em.empMname,em.empLname from TrnBillMvmnt bm,OrgEmpMst em"
							+ " where em.orgUserMst.userId=bm.statusUpdtUserid and bm.billNo="
							+ billNo
							+ " and bm.movemntId="
							+ mvmntId
							+ " and bm.locationCode='"
							+ strLocationCode
							+ "'"
							+ " and em.cmnLanguageMst.langId=" + langID);
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList.size() > 0) {
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					strLyingWith = tuple[0] + " " + tuple[1] + " " + tuple[2];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strLyingWith;
	}
	 /**
	 * Method to getting the Bill Status
	 * 
	 * @param  Long billNo, String strLocationCode
	 * @return String
	 * @author vidhya
	 */
	public String getBillStatus(Long billNo, String strLocationCode) {
		String strStatus = null;
		Query sqlQuery = null;
		StringBuffer query = new StringBuffer();
		try {
			Session hibSession = getSession();
			query
					.append("select bm.mvmntStatus from TrnBillMvmnt bm where bm.billNo="
							+ billNo
							+ " and bm.mvmntStatus in('BAPPRV_AUD','BRJCT_AUD') and bm.locationCode='"
							+ strLocationCode + "'");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList.size() > 0) {
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					Object tuple = (Object) it.next();
					if (tuple.toString().equalsIgnoreCase("BAPPRV_AUD")) {
						strStatus = "Approved";
					} else if (tuple.toString().equalsIgnoreCase("BRJCT_AUD")) {
						strStatus = "Rejected";
					}
				}
			} else {
				strStatus = "Pending";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strStatus;
	}
	 /**
	 * Method to getting the Chque Detail Report
	 * 
	 * @param  	Long subjectId, String fromDate,
	 *			String toDate, Long modeId, String ddoCode[], String audPostId[],
	 *			Long chequeNo, Double chequeAmt, Long locId,
	 *			String strLocationCode, Long langID
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getChqDetailByDelRpt(Long subjectId, String fromDate,
			String toDate, Long modeId, String ddoCode[], String audPostId[],
			Long chequeNo, Double chequeAmt, Long locId,
			String strLocationCode, Long langID) {
		ArrayList arrOuter = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query
					.append("select  distinct cd.fromDt,cd.adviceNo,cd.chequeNo,cd.partyName,cd.chequeAmt,cd.createdDate "
							+ " from TrnBillRegister br,TrnChequeDtls cd,RltBillCheque bc "
							+ " where bc.billNo = br.billNo and bc.chequeId = cd.chequeId and br.chequeDispDt IS NOT NULL");

			if (fromDate != null && toDate != null) {
				query.append(" and to_char(br.inwardDt,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(br.inwardDt,'yyyy-mm-dd') <= '"
						+ toDate + "'");
			}
			if (subjectId != -1) {
				query.append(" and br.subjectId=" + subjectId);
			}
			if (modeId != -1) {
				query.append(" and br.phyBill=" + modeId);
			}
			if (ddoCode.length > 0) {
				if (ddoCode.length == 1 && !ddoCode[0].equalsIgnoreCase("-1")) {
					query.append(" and br.ddoCode in ('" + ddoCode[0] + "')");
				} else if (ddoCode.length >= 2) {
					query.append(" and br.ddoCode in(");
					for (int i = 0; i < ddoCode.length; i++)
						if (!ddoCode[i].equalsIgnoreCase("-1")) {
							if (i != (ddoCode.length - 1)) {
								query.append("'" + ddoCode[i] + "',");
							} else {
								query.append("'" + ddoCode[i] + "'");
							}
						}
					query.append(")");
				}
			}
			if (audPostId.length > 0) {
				if (audPostId.length == 1
						&& !audPostId[0].equalsIgnoreCase("-1")) {
					query.append(" and br.audPostId in (" + audPostId[0] + ")");
				} else if (audPostId.length >= 2) {
					query.append(" and br.audPostId in(");
					for (int i = 0; i < audPostId.length; i++)
						if (!audPostId[i].equalsIgnoreCase("-1")) {
							if (i != (audPostId.length - 1))
								query.append(audPostId[i] + ",");
							else
								query.append(audPostId[i]);
						}
					query.append(")");
				}
			}
			if (chequeNo != null) {
				query.append(" and cd.chequeNo =" + chequeNo);
			}
			if (chequeAmt != null) {
				query.append(" and cd.chequeAmt =" + chequeAmt);
			}
			query.append(" and cd.locationCode = '" + strLocationCode + "'");
			query.append(" order by cd.chequeNo ");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList != null) {
				arrOuter = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arrInner = new ArrayList();
					arrInner.add(count);
					// chanegd to get token number for that cheque number
					arrInner.add(getTokensForCheque(tuple[2].toString()));
					String issueDate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd")
									.parse(tuple[0].toString()));
					arrInner.add(issueDate);
					arrInner.add(tuple[1]);
					arrInner.add(tuple[2]);
					arrInner.add(tuple[3]);

					DecimalFormat dFormat = new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount = Double.parseDouble(tuple[4].toString());
					String strAmount = dFormat.format(dAmount);
					arrInner.add(strAmount);
					arrOuter.add(arrInner);
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getBillDtlByBTraRpt # \n"
							+ e);
		}
		return arrOuter;
	}
	 /**
	 * Method to getting the Tokens For Cheque
	 * 
	 * @param  String chequeNum
	 * @return String
	 * @author vidhya
	 */
	public String getTokensForCheque(String chequeNum) {
		Session hibSession = getSession();
		Query sqlQuery = hibSession
				.createSQLQuery(" SELECT TOKEN_NUM FROM TRN_BILL_REGISTER R, TRN_CHEQUE_DTLS C, RLT_BILL_CHEQUE BC WHERE BC.BILL_NO = R.BILL_NO "
						+ " 	AND C.CHEQUE_ID = BC.CHEQUE_ID AND C.CHEQUE_NO = "
						+ chequeNum);

		List result = sqlQuery.list();
		String tokens = "";
		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size() - 1; i++) {
				tokens = tokens + result.get(i).toString() + ",";
			}
			tokens = tokens + result.get(result.size() - 1);
		}
		return tokens;
	}
	 /**
	 * Method to getting the Bill Tracking detail
	 * 
	 * @param  Long billno, String strLocationCode, 
	 *		   Long langID
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getBillTrackDtls(Long billno, String strLocationCode,
			Long langID) {
		ArrayList arrOuter = null;

		try {
			Session hibSession = getSession();
			Query sqlQuery = hibSession
					.createQuery("SELECT EM.EMPFNAME ,EM.EMPMNAME,"
							+ " EM.EMPLNAME,BM.MVMNTSTATUS,BM.RECEIVEDDATE,BM.MOVEMNTID "
							+ " FROM TRNBILLMVMNT BM, TRNBILLREGISTER BR,ORGEMPMST EM,ORGDDOMST DM "
							+ " WHERE BM.BILLNO = BR.BILLNO AND BR.DDOCODE = DM.DDOCODE AND EM.ORGUSERMST.USERID=BM.STATUSUPDTUSERID AND BR.BILLNO="
							+ billno + " AND BM.LOCATIONCODE='"
							+ strLocationCode + "'"
							+ " AND EM.CMNLANGUAGEMST.LANGID=" + langID
							+ " AND DM.LANGID=" + langID
							+ " ORDER BY BM.MOVEMNTID");
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				arrOuter = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arrInner = new ArrayList();
					arrInner.add(count);

					arrInner.add(tuple[0] + " " + tuple[1] + " " + tuple[2]);
					String strBranchname = getBranchname(billno,
							strLocationCode, langID);
					if (strBranchname == null) {
						strBranchname = "-";
					}
					arrInner.add(strBranchname);
					String receivedDate = "-";
					if (tuple[4] != null) {
						receivedDate = new SimpleDateFormat("dd/MM/yyyy")
								.format(new SimpleDateFormat("yyyy-MM-dd")
										.parse(tuple[4].toString()));
					}
					arrInner.add(receivedDate);
					int mvmntId = getMaxMvmntId(billno, strLocationCode);
					if (tuple[5] == null) {
						//System.out.println("Data is nullllllllllllllllllll");
						arrInner.add("-");
					} else {
						if (Integer.parseInt(tuple[5].toString()) < mvmntId) {
							arrInner.add(getSendDate(billno, Integer
									.parseInt(tuple[5].toString()),
									strLocationCode));
						} else {
							arrInner.add("-");
						}
					}
					arrOuter.add(arrInner);
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"
							+ e);
		}
		return arrOuter;
	}
	 /**
	 * Method to getting the Branch name
	 * 
	 * @param  Long billno, String strLocationCode, Long langID
	 * @return String
	 * @author vidhya
	 */
	public String getBranchname(Long billno, String strLocationCode, Long langID) {
		String strPostID = null;
		String strBranchname = null;
		Session hibSession = getSession();
		String strQuery = "SELECT STATUS_UPDT_POSTID   FROM TRN_BILL_MVMNT WHERE BILL_NO="
				+ billno + " AND LOCATION_CODE='" + strLocationCode + "'";
		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList != null && resList.size() > 0) {
			Iterator it = resList.iterator();
			while (it.hasNext()) {
				try {
					strPostID = it.next().toString();
				} catch (Exception ex) {
					strPostID = null;
				}
			}
		}
		strQuery = "SELECT BRANCH_CODE FROM ORG_POST_MST WHERE POST_ID="
				+ strPostID + " AND LOCATION_CODE='" + strLocationCode + "'";
		sqlQuery = hibSession.createSQLQuery(strQuery);
		resList = sqlQuery.list();
		String strBranchCode = null;
		if (resList.size() > 0) {
			Iterator it = resList.iterator();
			while (it.hasNext()) {
				try {
					strBranchCode = it.next().toString();
				} catch (Exception ex) {
					strBranchCode = null;
				}
			}
		}
		strQuery = "SELECT B.BRANCH_NAME  FROM CMN_BRANCH_MST B WHERE B.BRANCH_CODE ='"
				+ strBranchCode + "' AND B.LANG_ID=" + langID;
		sqlQuery = hibSession.createSQLQuery(strQuery);
		resList = sqlQuery.list();
		if (resList.size() > 0) {
			Iterator it = resList.iterator();
			while (it.hasNext()) {
				try {
					strBranchname = it.next().toString();
				} catch (Exception ex) {
					strBranchname = "-";
				}
			}
		}
		return strBranchname;
	}
	 /**
	 * Method to getting Send Date
	 * 
	 * @param  	Long billNo, 
	 * 			int mvmntId, 
	 * 			String strLocationCode
	 * @return String
	 * @author vidhya
	 */
	public String getSendDate(Long billNo, int mvmntId, String strLocationCode) {
		String strSendDate = "-";
		Query sqlQuery = null;
		mvmntId++;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query
					.append("select bm.statusUpdtDate from TrnBillMvmnt bm where bm.billNo="
							+ billNo
							+ " and bm.movemntId="
							+ mvmntId
							+ " and bm.locationCode=" + strLocationCode);
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList.size() > 0) {
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					Object tuple = (Object) it.next();
					if (tuple != null) {
						strSendDate = new SimpleDateFormat("dd/MM/yyyy")
								.format(new SimpleDateFormat("yyyy-MM-dd")
										.parse(tuple.toString()));
					} else {
						strSendDate = "-";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strSendDate;
	}
	 /**
	 * Method to getting Maximum MvmntId
	 * 
	 * @param  	Long billNo, 
	 * 			String strLocationCode
	 * @return int
	 * @author vidhya
	 */
	public int getMaxMvmntId(Long billNo, String strLocationCode) {
		int mvmntId = 0;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query
					.append("select max(bm.movemntId) from TrnBillMvmnt bm where bm.billNo="
							+ billNo
							+ " and bm.locationCode='"
							+ strLocationCode + "'");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList.size() > 0) {
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					Object tuple = (Object) it.next();
					mvmntId = Integer.parseInt(tuple.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mvmntId;
	}

	/***************************************************************************
	 * code for audit tracking report
	 * 
	 */
	 /**
	 * Method to getting Audit Tracking Report
	 * 
	 * @param 	String fromDate, 
	 * 			String toDate,
	 *			Long subjectId, 
	 *			String mjrHead, 
	 *			String ddoUserId[], 
	 *			Long tokenNo,
	 *			String auditstatus, 
	 *			Long locId, 
	 *			String strLocationCode, 
	 *			Long langID
	 *			
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getAuditTrackingRpt(String fromDate, String toDate,
			Long subjectId, String mjrHead, String ddoUserId[], Long tokenNo,
			String auditstatus, Long locId, String strLocationCode, Long langID) {
		ArrayList arrOuter = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append("select br.billCntrlNo, br.billNetAmount, bt.shortName ,br.billDate ,dm.cardexNo ,dm.shortName ,br.inwardDt, br.budmjrHd ,br.tokenNum,br.billNo "
							+ " from TrnBillRegister br,MstBillType bt,OrgDdoMst dm "
							+ " where br.subjectId=bt.subjectId and br.ddoCode = dm.ddoCode ");
			if (fromDate != null && toDate != null) {
				query.append(" and br.inwardDt between to_date('" + fromDate
						+ "','yyyy-mm-dd') and  to_date('" + toDate
						+ "','yyyy-mm-dd') ");
			}
			if (subjectId != -1) {
				query.append(" and br.subjectId=" + subjectId);
			}
			if (!mjrHead.equalsIgnoreCase("-1")) {
				query.append(" and br.budmjrHd ='" + mjrHead + "'");
			}
			if (ddoUserId.length > 0) {
				if (ddoUserId.length == 1
						&& !ddoUserId[0].equalsIgnoreCase("-1")) {
					query.append(" and br.ddoCode in ('" + ddoUserId[0] + "')");
				} else if (ddoUserId.length >= 2) {
					query.append(" and br.ddoCode in(");
					for (int i = 0; i < ddoUserId.length; i++) {
						if (!ddoUserId[i].equalsIgnoreCase("-1")) {
							if (i != (ddoUserId.length - 1)) {
								query.append("'" + ddoUserId[i] + "',");
							} else {
								query.append("'" + ddoUserId[i] + "'");
							}

						}
					}
					query.append(")");
				}
			}
			if (tokenNo != null) {
				query.append(" and br.tokenNum =" + tokenNo);
			}
			// if(!auditstatus.equalsIgnoreCase("-1"))
			// {
			// query.append(" and br.audApprvStat ='"+auditstatus+"'");
			// }
			query.append(" and br.tsryOfficeCode = '" + strLocationCode + "'"
					+ " and bt.langId=" + langID + " and dm.langId=" + langID);
			query.append(" order by br.tokenNum asc");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList != null) {
				arrOuter = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arrInner = new ArrayList();
					arrInner.add(count);
					arrInner.add(tuple[0]);

					DecimalFormat dFormat = new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount = Double.parseDouble(tuple[1].toString());
					String strAmount = dFormat.format(dAmount);
					arrInner.add(strAmount);

					arrInner.add(tuple[2]);
					String billDate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd")
									.parse(tuple[3].toString()));
					arrInner.add(billDate);
					arrInner.add(tuple[4]);
					arrInner.add(tuple[5]);
					String inwardDate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd")
									.parse(tuple[6].toString()));
					arrInner.add(inwardDate);
					arrInner.add(tuple[7]);
					arrInner.add(tuple[8]);
					String strStatus = getBillStatus(Long.parseLong(tuple[9]
							.toString()), strLocationCode);
					arrInner.add(strStatus);
					arrInner.add(tuple[9]);
					if (auditstatus.equalsIgnoreCase("-1") == true) {
						arrOuter.add(arrInner);
						count++;
					} else if (auditstatus.equalsIgnoreCase("-1") == false) {
						if (auditstatus.equalsIgnoreCase("BAPPRV_AUD") == true
								&& strStatus.equalsIgnoreCase("Approved") == true) {
							arrOuter.add(arrInner);
							count++;
						} else if (auditstatus.equalsIgnoreCase("BRJCT_AUD") == true
								&& strStatus.equalsIgnoreCase("Rejected") == true) {
							arrOuter.add(arrInner);
							count++;
						} else if (auditstatus.equalsIgnoreCase("3") == true
								&& strStatus.equalsIgnoreCase("Pending") == true) {
							arrOuter.add(arrInner);
							count++;
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getAuditTrackingReportRpt # \n"
							+ e);
		}
		return arrOuter;
	}

	 /**
	 * Method to getting Undelivered Cheques Details
	 * 
	 * @param   String fromDate,
	 *			String toDate, 
	 *			Long locId, 
	 *			String strLocationCode
	 *			
	 * @return ArrayList
	 * @author vidhya
	 */
	// Undelivered Cheque Report
	public ArrayList getUndeliveredChequesDetails(String fromDate,
			String toDate, Long locId, String strLocationCode) {
		ArrayList arraylistOuter = null;
		Query sqlQuery = null;
		ResourceBundle bundle = ResourceBundle
		.getBundle("resources/billproc/BillprocConstants");
		String strStatus = bundle.getString("STATUS.CheqCancel");

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append("select distinct cd.chequeNo, cd.printDate, cd.partyName, cd.chequeAmt from TrnChequeDtls cd,TrnBillRegister br,RltBillCheque bc "
							+ " where cd.chequeId=bc.chequeId and bc.billNo=br.billNo and br.chequeDispDt IS NULL and status != '"+strStatus+"'");
			if (fromDate != null && toDate != null) {
				query.append(" and to_char(cd.printDate,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(cd.printDate,'yyyy-mm-dd') <= '"
						+ toDate + "'");
			}
			query.append(" and cd.locationCode = '" + strLocationCode + "'");
			query.append(" order by cd.chequeNo");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList != null) {
				Iterator it = resList.iterator();
				arraylistOuter = new ArrayList();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arraylistInner = new ArrayList();
					arraylistInner.add(count++);
					arraylistInner.add(tuple[0]);
					String printDate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd")
									.parse(tuple[1].toString()));
					arraylistInner.add(printDate);
					arraylistInner.add(tuple[2]);
					DecimalFormat dFormat = new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount = Double.parseDouble(tuple[3].toString());
					String strAmount = dFormat.format(dAmount);
					arraylistInner.add(strAmount);
					arraylistOuter.add(arraylistInner);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getBillTypeRecord # \n"
							+ e);
		}
		return (arraylistOuter);
	}
	 /**
	 * Method to getting Unpaid Cheque Detail
	 * 
	 * @param  	String fromDate, 
	 * 			String toDate,
	 *			Long locId, 
	 *			String strLocationCode
	 *
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getUnpaidChequeDetail(String fromDate, String toDate,
			Long locId, String strLocationCode) {
		ArrayList arraylistOuter = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append("select distinct br.chequeDispDt, cd.adviceNo, cd.chequeNo, cd.chequeAmt from "
							+ "TrnBillRegister br,RltBillCheque bc,TrnChequeDtls cd where cd.chequeId=bc.chequeId "
							+ "and bc.billNo=br.billNo and cd.clearedDt is null and br.chequeDispDt is not null");

			if (fromDate != null && toDate != null) {
				query.append(" and br.chequeDispDt between to_date('"
						+ fromDate + "','yyyy-mm-dd') and to_date('" + toDate
						+ "','yyyy-mm-dd') ");
			}
			query.append(" and cd.locationCode = '" + strLocationCode + "'");
			query.append(" order by cd.chequeNo asc");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();

			if (resList != null) {
				Iterator it = resList.iterator();
				arraylistOuter = new ArrayList();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arraylistInner = new ArrayList();

					arraylistInner.add(count++);
					String dispDate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd")
									.parse(tuple[0].toString()));
					arraylistInner.add(dispDate);
					arraylistInner.add(tuple[1]);
					arraylistInner.add(tuple[2]);
					DecimalFormat dFormat = new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount = Double.parseDouble(tuple[3].toString());
					String strAmount = dFormat.format(dAmount);
					arraylistInner.add(strAmount);

					arraylistOuter.add(arraylistInner);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getUnpaidChequeDetail # \n"
							+ e);
		}
		return (arraylistOuter);
	}
	 /**
	 * Method to getting Cheque Status
	 * 
	 * @param  	String fromDate, 
	 * 			String toDate,
	 *			String status, 
	 *			Long locId, 
	 *			String strLocationCode, 
	 *			Long langID
	 *
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getChequeStatus(String fromDate, String toDate,
			String status, Long locId, String strLocationCode, Long langID) {
		ArrayList arrOuter = null;
		Query sqlQuery = null;
		
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append(" select distinct cd.statusDate,cd.chequeNo,cd.chequeAmt,cd.status  "
							+ "from TrnBillRegister br,TrnChequeDtls cd,RltBillCheque bc "
							+ "where br.billNo=bc.billNo and bc.chequeId=cd.chequeId");

			if (fromDate != null && toDate != null) {
				query.append(" and to_char(br.inwardDt,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(br.inwardDt,'yyyy-mm-dd')<='" + toDate
						+ "'");
			}
			if (!status.equalsIgnoreCase("-1")) {
				query.append(" and cd.status = '" + status + "'");
			}
			query.append(" and cd.locationCode = '" + strLocationCode + "'");
			query.append(" order by cd.chequeNo asc");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList != null) {
				arrOuter = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arrInner = new ArrayList();
					arrInner.add(count);
					if (tuple[0] != null) {
						String dispDate = new SimpleDateFormat("dd/MM/yyyy")
								.format(new SimpleDateFormat("yyyy-MM-dd")
										.parse(tuple[0].toString()));
						arrInner.add(dispDate);
					} else {
						arrInner.add("-");
					}
					if (tuple[1] != null) {
						arrInner.add(tuple[1]);
					} else {
						arrInner.add("-");
					}
					// arrInner.add(tuple[2]);
					arrInner.add(getTokensForCheque(tuple[1].toString()));
					DecimalFormat dFormat = new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount = Double.parseDouble(tuple[2].toString());
					String strAmount = dFormat.format(dAmount);
					arrInner.add(strAmount);
					if (tuple[3].toString().equalsIgnoreCase("CHQ_WRTN")) {
						arrInner.add("Written");
					} else if (tuple[3].toString().equalsIgnoreCase("CHQ_PRNT")) {
						arrInner.add("Printed");
					} else if (tuple[3].toString()
							.equalsIgnoreCase("CHQ_CSTDN")) {
						arrInner.add("In Custody");
					} else if (tuple[3].toString().equalsIgnoreCase("CHQ_CNTR")) {
						arrInner.add("Dispatched to Counter");
					} else if (tuple[3].toString().equalsIgnoreCase(
							"CHQ_DSPTCH_DDO")) {
						arrInner.add("Delivered to DDO");
					} else if (tuple[3].toString()
							.equalsIgnoreCase("CANCELLED")) {
						arrInner.add("Cancelled");
					} else if (tuple[3].toString().equalsIgnoreCase("CHQ_DUP")) {
						arrInner.add("Duplicated");
					} else if (tuple[3].toString().equalsIgnoreCase("CHQ_RNM")) {
						arrInner.add("Renamed");
					} else if (tuple[3].toString().equalsIgnoreCase("CHQ_RVLD")) {
						arrInner.add("Revalidated");
					} else {
						arrInner.add("Other");
					}
					arrOuter.add(arrInner);
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getChequeStatus # \n"
							+ e);
		}
		return arrOuter;
	}

	// Added by Milind Vijay Shah
	 /**
	 * Method to getting Status For Token
	 * 
	 * @param  String lStrLangId, String lstrLocId
	 * @return List
	 * @author vidhya
	 */
	public List getStatusForToken(String lStrLangId, String lstrLocId) {
		List statusList = new ArrayList();
		ComboValuesVO vo1 = new ComboValuesVO();
		vo1.setId("A");
		vo1.setDesc("Available");
		statusList.add(vo1);
		ComboValuesVO vo2 = new ComboValuesVO();

		vo2.setId("U");
		vo2.setDesc("Used");
		statusList.add(vo2);
		ComboValuesVO vo3 = new ComboValuesVO();

		vo3.setId("L");
		vo3.setDesc("Lost");
		statusList.add(vo3);
		return statusList;
	}
	 /**
	 * Method to getting Revalidation Data
	 * 
	 * @param  	String strFromDate, 
	 * 			String strToDate,
	 *			String strLocID, 
	 *			String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getRevalidationData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode) {
		List chequeData = new ArrayList();
		Session hibSession = getSession();
		String strQuery = "SELECT CHEQUEDET.CHEQUE_NO,BILLREG.TOKEN_NUM,BILLREG.CHEQUE_DISP_DT, "
				+ " CHEQUEDET.STATUS_DATE,CHEQUEDET.TO_DT,CHEQUEDET.CHEQUE_AMT "
				+ " FROM TRN_CHEQUE_DTLS CHEQUEDET,RLT_BILL_CHEQUE BILLCHEQUE,TRN_BILL_REGISTER BILLREG "
				+ " WHERE CHEQUEDET.CHEQUE_ID=BILLCHEQUE.CHEQUE_ID AND  BILLCHEQUE.BILL_NO=BILLREG.BILL_NO AND "
				+ "  CHEQUEDET.STATUS='CHQ_RVLD' AND ( TO_CHAR(STATUS_DATE,'DD/MM/YYYY')>='"
				+ strFromDate
				+ "' AND TO_CHAR(STATUS_DATE,'DD/MM/YYYY')<='"
				+ strToDate
				+ "' )  "
				+ " AND CHEQUEDET.LOCATION_CODE='"
				+ strLocationCode + "'  ORDER BY CHEQUE_NO";

		// old condition : ((to_char(to_dt,'MM') -
		// (to_char(status_date,'MM')+1))+1)>3 andcheque_disp_dt

		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();
		int Counter = 0;
		//System.out.println("While before");
		while (it.hasNext()) {
			//System.out.println(Counter);
			Object[] tuple = (Object[]) it.next();
			List innerList = new ArrayList();
			Counter++;
			innerList.add(Counter);
			try {
				innerList.add(tuple[0].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[1].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[2].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[3].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[4].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				double dAmount = Double.parseDouble(tuple[5].toString());
				String strAmount = dFormat.format(dAmount);
				innerList.add(strAmount);
			} catch (Exception Ex) {
				innerList.add("-");
			}
			chequeData.add(innerList);
		}
		return chequeData;
	}
	 /**
	 * Method to getting Renamed Cheque Data
	 * 
	 * @param  	String strFromDate, 
	 * 			String strToDate,
	 *			String strLocID, 
	 *			String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getRenamedChequeData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode) {
		List chequeData = new ArrayList();
		Session hibSession = getSession();
		String strQuery = "SELECT CHEQUEDET.CHEQUE_NO,BILLREG.TOKEN_NUM,BILLREG.CHEQUE_DISP_DT, "
				+ " BILLCHEQUE.CREATED_DATE,CHEQUEDET.CHEQUE_AMT "
				+ " FROM TRN_CHEQUE_DTLS CHEQUEDET,RLT_BILL_CHEQUE BILLCHEQUE,TRN_BILL_REGISTER BILLREG  "
				+ " WHERE CHEQUEDET.CHEQUE_ID=BILLCHEQUE.CHEQUE_ID AND BILLCHEQUE.BILL_NO=BILLREG.BILL_NO AND "
				+ " STATUS='CHQ_RNM' AND ( TO_CHAR(STATUS_DATE,'DD/MM/YYYY')>='"
				+ strFromDate
				+ "' AND TO_CHAR(STATUS_DATE,'DD/MM/YYYY')<='"
				+ strToDate
				+ "') "
				+ " AND CHEQUEDET.LOCATION_CODE='"
				+ strLocationCode + "' ORDER BY CHEQUE_NO";

		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();
		int Counter = 0;
		//System.out.println("While before");
		while (it.hasNext()) {
			//System.out.println(Counter);
			Object[] tuple = (Object[]) it.next();
			List innerList = new ArrayList();
			Counter++;
			innerList.add(Counter);
			try {
				innerList.add(tuple[0].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[1].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[2].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[3].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				double dAmount = Double.parseDouble(tuple[4].toString());
				String strAmount = dFormat.format(dAmount);
				innerList.add(strAmount);
			} catch (Exception Ex) {
				innerList.add("-");
			}
			chequeData.add(innerList);
		}
		return chequeData;
	}
	 /**
	 * Method to getting Cardex Wise Summary
	 * 
	 * @param  	String strFromDate, 
	 * 			String strToDate,
	 *			String strLocID, 
	 *			String strLocationCode, 
	 *			Long langId
	 * @return List
	 * @author vidhya
	 */
	public List getCardexWiseSummary(String strFromDate, String strToDate,
			String strLocID, String strLocationCode, Long langId) {
		List cardexWiseSummary = new ArrayList();
		Session hibSession = getSession();

		String strQuery = "SELECT D.CARDEX_NO, D.DDO_NAME, D.DDO_CODE FROM RLT_DDO_ORG R, ORG_DDO_MST D WHERE R.DDO_CODE = D.DDO_CODE AND R.OFFICE_CODE= '"
				+ strLocationCode
				+ "' AND D.LANG_ID ="
				+ langId
				+ " ORDER BY D.CARDEX_NO";
		//System.out.println("query bhavik rpt " + strQuery);
		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();

		int Counter = 0;
		//System.out.println("While before");
		while (it.hasNext()) {
			Object[] tuple = (Object[]) it.next();
			List innerList = new ArrayList();
			try {
				innerList.add(tuple[0].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[1].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				String strQuery1 = "SELECT COUNT(*) FROM TRN_BILL_REGISTER R , TRN_VOUCHER_DETAILS V WHERE R.BILL_NO = V.BILL_NO AND R.DDO_CODE = '"
						+ tuple[2].toString()
						+ "'"
						+ " AND ( TO_CHAR(V.VOUCHER_DATE,'DD/MM/YYYY')>='"
						+ strFromDate
						+ "' AND TO_CHAR(V.VOUCHER_DATE,'DD/MM/YYYY')<='"
						+ strToDate + "') ";

				//System.out.println("query bhavik rpt " + strQuery1);
				Query sqlQuery1 = hibSession.createSQLQuery(strQuery1);
				List resList1 = sqlQuery1.list();
				//System.out.println("inner list size is :::" + resList1.size());
				if (resList1.size() == 0) {
					//System.out.println("in query zero");
					innerList.add(new BigDecimal(0));
				} else {
					Iterator it1 = resList1.iterator();
					BigDecimal tuple1 = (BigDecimal) it1.next();
					innerList.add(tuple1);
				}
			} catch (Exception Ex) {
				Ex.printStackTrace();
				//System.out.println("in exception");
				innerList.add(new BigDecimal(0));
			}
			try {
				innerList.add(" ");
			} catch (Exception Ex) {
				innerList.add("-");
			}
			cardexWiseSummary.add(innerList);
		}

		return cardexWiseSummary;
	}
	 /**
	 * Method to getting Cancelled Cheque Data
	 * 
	 * @param  	String strFromDate,
	 *			String strToDate,
	 *			String strLocID, 
	 *			String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getCancelledChequeData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode) {
		List chequeData = new ArrayList();
		Session hibSession = getSession();
		String strQuery = "SELECT CHEQUE_NO,PARTY_NAME,CHEQUE_AMT,REMARKS FROM TRN_CHEQUE_DTLS "
				+ " WHERE STATUS='CANCELLED'	AND "
				+ "  (TO_CHAR(STATUS_DATE,'DD/MM/YYYY')>='"
				+ strFromDate
				+ "'"
				+ " AND TO_CHAR(STATUS_DATE,'DD/MM/YYYY')<='"
				+ strToDate
				+ "'"
				+ ")"
				+ " AND LOCATION_CODE='"
				+ strLocationCode
				+ "'"
				+ " ORDER BY CHEQUE_NO";

		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();
		int Counter = 0;
		//System.out.println("While before");
		while (it.hasNext()) {
			//System.out.println(Counter);
			Object[] tuple = (Object[]) it.next();
			List innerList = new ArrayList();
			Counter++;
			innerList.add(Counter);
			try {
				innerList.add(tuple[0].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[1].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {

				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				double dAmount = Double.parseDouble(tuple[2].toString());
				String strAmount = dFormat.format(dAmount);
				innerList.add(strAmount);

			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[3].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}

			chequeData.add(innerList);
		}
		return chequeData;
	}
	 /**
	 * Method to getting Token Details Data
	 * 
	 * @param  	String strTokenStatus, 
	 * 			String strLocID,
	 *			String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getTokenDetailsData(String strTokenStatus, String strLocID,
			String strLocationCode) {
		List tokenData = new ArrayList();
		String strStatus = "";
		String strQuery = "";
		Session hibSession = getSession();
		if (strTokenStatus.equalsIgnoreCase("Available") == true) {
			strStatus = "A";
		} else if (strTokenStatus.equalsIgnoreCase("Used") == true) {
			strStatus = "U";
		} else if (strTokenStatus.equalsIgnoreCase("Lost") == true) {
			strStatus = "L";
		} else if (strTokenStatus.equalsIgnoreCase("Total") == true) {
			for (int intCount = 0; intCount <= 2; intCount++) {
				//System.out.println("Inside for loop");
				if (intCount == 0) {
					strStatus = "A";
				} else if (intCount == 1) {
					strStatus = "U";
				} else if (intCount == 2) {
					strStatus = "L";
				}
				strQuery = "SELECT  TOKEN_NO FROM ORG_TOKEN_STATUS WHERE TOKEN_STATUS="
						+ "'"
						+ strStatus
						+ "'"
						+ " AND "
						+ "LOCATION_CODE='"
						+ strLocationCode + "' ORDER BY TOKEN_NO ASC";
				Query sqlQuery = hibSession.createSQLQuery(strQuery);
				List resList = sqlQuery.list();
				if (resList.size() > 0) {

					Iterator it = resList.iterator();
					while (it.hasNext()) {
						String strTokenNumber = it.next().toString();
						System.out
								.println("Token number is :" + strTokenNumber);
						List tokenDetail = getTokenDetail(strTokenNumber,
								strStatus, strLocID, strLocationCode);
						if (tokenDetail != null) {
							tokenData.add(tokenDetail);
						}
						tokenDetail = null;
					}
				}
				resList = null;
			}
			return tokenData;
		}
		strQuery = "SELECT TOKEN_NO FROM ORG_TOKEN_STATUS WHERE TOKEN_STATUS="
				+ "'" + strStatus + "'" + " AND " + " LOCATION_CODE='"
				+ strLocationCode + "' ORDER BY TOKEN_NO ASC";
		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();
		while (it.hasNext()) {
			String strTokenNumber = it.next().toString();
			List tokenDetail = getTokenDetail(strTokenNumber, strStatus,
					strLocID, strLocationCode);
			if (tokenDetail != null) {
				tokenData.add(tokenDetail);
			}
		}
		return tokenData;
	}
	 /**
	 * Method to getting Token Detail
	 * 
	 * @param  	String strTokenNumber, 
	 * 			String strStatus,
	 *			String strLocID, 
	 *			String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getTokenDetail(String strTokenNumber, String strStatus,
			String strLocID, String strLocationCode) {
		List tokenDetail = new ArrayList();
		Session hibSession = getSession();
		int maxTrnCounter = getMaxTokenTrnCounter(strTokenNumber, strLocID,
				strLocationCode);
		// //System.out.println("Max="+maxTrnCounter);
		if (maxTrnCounter == 0) {
			tokenDetail.add(strTokenNumber);
			tokenDetail.add("-");
			tokenDetail.add("-");
			tokenDetail.add("-");
			return tokenDetail;
		}
		String strQuery = "";
		if (strStatus.equalsIgnoreCase("A") == true) {
			strQuery = "SELECT TO_CHAR(ISSUE_DATE,'DD-MM-YYYY') AS ISSUEDATE,BILL_CNTRL_NO FROM HST_ORG_TOKEN_STATUS INNER JOIN TRN_BILL_REGISTER ON HST_ORG_TOKEN_STATUS.BILL_NO=TRN_BILL_REGISTER.BILL_NO "
					+ " WHERE HST_ORG_TOKEN_STATUS.TRN_COUNTER="
					+ (maxTrnCounter - 1)
					+ " AND HST_ORG_TOKEN_STATUS.TOKEN_NO=" + strTokenNumber;
		} else if (strStatus.equalsIgnoreCase("U") == true
				|| strStatus.equals("L") == true) {
			strQuery = "SELECT TO_CHAR(ISSUE_DATE,'DD-MM-YYYY') AS ISSUEDATE,BILL_CNTRL_NO FROM HST_ORG_TOKEN_STATUS INNER JOIN TRN_BILL_REGISTER ON HST_ORG_TOKEN_STATUS.BILL_NO=TRN_BILL_REGISTER.BILL_NO "
					+ " WHERE HST_ORG_TOKEN_STATUS.TRN_COUNTER="
					+ (maxTrnCounter)
					+ " AND HST_ORG_TOKEN_STATUS.TOKEN_NO="
					+ strTokenNumber;
		}
		strQuery = strQuery + " " + " AND TRN_BILL_REGISTER.TSRY_OFFICE_CODE='"
				+ strLocationCode + "'";
		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resIssueAndBillno = sqlQuery.list();
		if (strStatus.equalsIgnoreCase("A") == true) {
			strQuery = "SELECT TO_CHAR(REEDEMED_DATE,'DD-MM-YYYY') AS REEDEMEDDATE FROM HST_ORG_TOKEN_STATUS WHERE TRN_COUNTER="
					+ maxTrnCounter + " AND TOKEN_NO=" + strTokenNumber;
		} else if (strStatus.equalsIgnoreCase("U") == true
				|| strStatus.equals("L") == true) {
			strQuery = "SELECT TO_CHAR(REEDEMED_DATE,'DD-MM-YYYY') AS REEDEMEDDATE FROM HST_ORG_TOKEN_STATUS WHERE TRN_COUNTER="
					+ maxTrnCounter + " AND TOKEN_NO=" + strTokenNumber;
		}
		strQuery = strQuery + " " + " AND  LOCATION_CODE='" + strLocationCode
				+ "'";
		sqlQuery = hibSession.createSQLQuery(strQuery);
		List ReedemedList = sqlQuery.list();
		if (resIssueAndBillno.size() == 0) {
			tokenDetail.add(strTokenNumber);
			tokenDetail.add("-");
			tokenDetail.add("-");
			tokenDetail.add("-");
			return tokenDetail;
		}
		if (ReedemedList.size() == 0) {
			tokenDetail.add(strTokenNumber);
			tokenDetail.add("-");
			tokenDetail.add("-");
			tokenDetail.add("-");
			return tokenDetail;
		}

		Iterator it = resIssueAndBillno.iterator();
		while (it.hasNext()) {
			Object[] tuple = (Object[]) it.next();
			tokenDetail.add(strTokenNumber);
			try {
				tokenDetail.add(tuple[0].toString());
			} catch (Exception ex) {
				tokenDetail.add("-");
			}
			Iterator inner = ReedemedList.iterator();
			String strReedemed = "";
			while (inner.hasNext()) {
				try {
					strReedemed = inner.next().toString();
				} catch (Exception ex) {
					strReedemed = "-";
				}
				tokenDetail.add(strReedemed);
			}
			try {
				tokenDetail.add(tuple[1].toString());
			} catch (Exception ex) {
				tokenDetail.add("-");
			}
		}

		return tokenDetail;
	}
	 /**
	 * Method to getting Maximum Token for Counter
	 * 
	 * @param 	String strTokenNumber, 
	 * 			String strLocID,
	 *			String strLocationCode
	 * @return int
	 * @author vidhya
	 */
	public int getMaxTokenTrnCounter(String strTokenNumber, String strLocID,
			String strLocationCode) {
		Session hibSession = getSession();
		Query sqlQuery = hibSession
				.createSQLQuery("SELECT MAX(TRN_COUNTER) AS MAXCOUNTER FROM HST_ORG_TOKEN_STATUS WHERE TOKEN_NO="
						+ strTokenNumber
						+ " "
						+ " AND LOCATION_CODE='"
						+ strLocationCode + "'");
		List maxCounterList = sqlQuery.list();
		String strMaxNumber = "";
		int maxCounter = 0;
		if (maxCounterList.size() == 0) {
			return 0;
		}
		Iterator it = maxCounterList.iterator();
		while (it.hasNext()) {
			try {
				strMaxNumber = it.next().toString();
			} catch (Exception ex) {
				strMaxNumber = "0";
			}
		}
		maxCounter = Integer.parseInt(strMaxNumber);
		return maxCounter;
	}
	 /**
	 * Method to getting TokenReportDetails
	 * 
	 * @param  	String strTokenStatus, 
	 * 			String strLocID,
	 *			String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getTokenReportDetails(String strTokenStatus, String strLocID,
			String strLocationCode) {
		List statusList = new ArrayList();
		Session hibSession = getSession();
		String strQuery = "SELECT DISTINCT (SELECT COUNT(TOKEN_STATUS) FROM ORG_TOKEN_STATUS WHERE TOKEN_STATUS='A'"
				+ " AND "
				+ "LOCATION_CODE='"
				+ strLocationCode
				+ "') AS AVAILABLE,(SELECT COUNT(TOKEN_STATUS) FROM ORG_TOKEN_STATUS WHERE TOKEN_STATUS='U'"
				+ " AND "
				+ "LOCATION_CODE='"
				+ strLocationCode
				+ "') AS USED,(SELECT COUNT(TOKEN_STATUS) FROM ORG_TOKEN_STATUS WHERE TOKEN_STATUS='L'"
				+ " AND "
				+ "LOCATION_CODE='"
				+ strLocationCode
				+ "') AS LOST FROM ORG_TOKEN_STATUS";

		//System.out.println("Status = " + strTokenStatus);
		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();

		while (it.hasNext()) {
			//System.out.println("Inside while");
			Object[] tuple = (Object[]) it.next();
			List innerList = null;

			if (strTokenStatus.equals("A") == true
					|| strTokenStatus.equals("-1") == true) {
				//System.out.println("Inside A");
				innerList = new ArrayList();
				innerList.add("Available");
				innerList.add(tuple[0].toString());
				statusList.add(innerList);
			}
			if (strTokenStatus.equals("U") == true
					|| strTokenStatus.equals("-1") == true) {
				innerList = new ArrayList();
				innerList.add("Used");
				innerList.add(tuple[1].toString());
				statusList.add(innerList);
			}
			if (strTokenStatus.equals("L") == true
					|| strTokenStatus.equals("-1") == true) {
				innerList = new ArrayList();
				innerList.add("Lost");
				innerList.add(tuple[2].toString());
				statusList.add(innerList);
			}
			if (strTokenStatus.equals("-1") == true) {
				innerList = new ArrayList();
				innerList.add("Total");
				int total = Integer.parseInt(tuple[0].toString())
						+ Integer.parseInt(tuple[1].toString())
						+ Integer.parseInt(tuple[2].toString());
				innerList.add(total);
				statusList.add(innerList);
			}
		}
		return statusList;
	}
	 /**
	 * Method to getting Cheque Duplication Data
	 * 
	 * @param  	String strFromDate, 
	 * 			String strToDate,
	 *			String strLocID, 
	 *			String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getChequeDuplicationData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode) {
		List chequeData = new ArrayList();
		Session hibSession = getSession();
		String strQuery = "SELECT CHEQUEDET.ADVICE_NO,"
				+ "CHEQUEDET.PREV_CHEQUE_ID,"
				+ "BILLREG.CHEQUE_DISP_DT,"
				+ "CHEQUEDET.CHEQUE_NO,"
				+ "CHEQUEDET.PARTY_NAME,"
				+ "CHEQUEDET.CHEQUE_AMT"
				+ " FROM TRN_CHEQUE_DTLS CHEQUEDET,RLT_BILL_CHEQUE BILLCHEQUE,TRN_BILL_REGISTER BILLREG"
				+ " WHERE CHEQUEDET.CHEQUE_ID=BILLCHEQUE.CHEQUE_ID AND BILLCHEQUE.BILL_NO=BILLREG.BILL_NO AND STATUS='CHQ_DUP'"
				+ " AND ( TO_CHAR(STATUS_DATE,'DD/MM/YYYY')>='" + strFromDate
				+ "' AND TO_CHAR(STATUS_DATE,'DD/MM/YYYY')<='" + strToDate
				+ "') " + " AND CHEQUEDET.LOCATION_CODE='" + strLocationCode
				+ "' ORDER BY CHEQUE_NO";

		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();
		int Counter = 0;
		//System.out.println("While before");
		while (it.hasNext()) {
			//System.out.println(Counter);
			Object[] tuple = (Object[]) it.next();
			List innerList = new ArrayList();
			Counter++;
			innerList.add(Counter);
			try {
				innerList.add(tuple[0].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				String strChequeID = tuple[1].toString();
				String strChequeNumber = getChequeNumber(strChequeID,
						strLocationCode);
				innerList.add(strChequeNumber);
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[2].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[3].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[4].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				double dAmount = Double.parseDouble(tuple[5].toString());
				String strAmount = dFormat.format(dAmount);
				innerList.add(strAmount);
			} catch (Exception ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[6].toString());
			} catch (Exception ex) {
				innerList.add("-");
			}

			chequeData.add(innerList);
		}
		return chequeData;
	}
	 /**
	 * Method to getting Cheque Number
	 * 
	 * @param  String strChequeID, 
	 * 		   String strLocationCode
	 * @return String
	 * @author vidhya
	 */
	public String getChequeNumber(String strChequeID, String strLocationCode) {
		String strChequeNumber = "";
		Session hibSession = getSession();
		String strQuery = "SELECT CHEQUE_NO FROM TRN_CHEQUE_DTLS WHERE CHEQUE_ID="
				+ strChequeID + " AND LOCATION_CODE='" + strLocationCode + "'";
		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		Iterator it = resList.iterator();
		while (it.hasNext()) {
			try {
				strChequeNumber = it.next().toString();
			} catch (Exception Ex) {
				strChequeNumber = "-";
			}
		}
		return strChequeNumber;
	}
	 /**
	 * Method to getting Cheque Advice Cancellation Data
	 * 
	 * @param  	String strFromDate,
	 *			String strToDate, 
	 *			String strLocID, 
	 *			String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getChequeAdviceCancellationData(String strFromDate,
			String strToDate, String strLocID, String strLocationCode) {
		List chequeData = new ArrayList();
		Session hibSession = getSession();
		String strQuery = "SELECT DISTINCT CHEQUEDET.CHEQUE_NO,BILLREG.CHEQUE_DISP_DT,"
				+ " CHEQUEDET.STATUS_DATE,CHEQUEDET.CHEQUE_AMT"
				+ " FROM TRN_CHEQUE_DTLS CHEQUEDET,RLT_BILL_CHEQUE BILLCHEQUE,TRN_BILL_REGISTER BILLREG"
				+ " WHERE CHEQUEDET.CHEQUE_ID=BILLCHEQUE.CHEQUE_ID AND BILLCHEQUE.BILL_NO=BILLREG.BILL_NO AND STATUS='CANCELLED'	AND "
				+ "  (TO_CHAR(STATUS_DATE,'DD/MM/YYYY')>='"
				+ strFromDate
				+ "'"
				+ " AND TO_CHAR(STATUS_DATE,'DD/MM/YYYY')<='"
				+ strToDate
				+ "'"
				+ ")"
				+ " AND CHEQUEDET.LOCATION_CODE='"
				+ strLocationCode
				+ "'" + " ORDER BY ADVICE_NO";

		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();
		int Counter = 0;
		//System.out.println("While before");
		while (it.hasNext()) {
			//System.out.println(Counter);
			Object[] tuple = (Object[]) it.next();
			List innerList = new ArrayList();
			Counter++;
			innerList.add(Counter);
			try {
				innerList.add(tuple[0].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				// innerList.add(tuple[1].toString()); /* changed for token list
				// */
				innerList.add(getTokensForCheque(tuple[0].toString()));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[1].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[2].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[3].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				double dAmount = Double.parseDouble(tuple[4].toString());
				String strAmount = dFormat.format(dAmount);
				innerList.add(strAmount);
			} catch (Exception Ex) {
				innerList.add("-");
			}
			chequeData.add(innerList);
		}
		return chequeData;
	}
	 /**
	 * Method to getting Time Barred Data
	 * 
	 * @param  	String strFromDate, 
	 * 			String strToDate,
	 *			String strLocID, 
	 *			String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getTimeBarredData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode) {
		List chequeData = new ArrayList();
		Session hibSession = getSession();
		String strQuery = "SELECT CHEQUEDET.CHEQUE_NO,BILLREG.CHEQUE_DISP_DT,VOUCHERDET.VOUCHER_NO,VOUCHERDET.VOUCHER_DATE,VOUCHERDET.MAJOR_HEAD,CHEQUEDET.CHEQUE_AMT"
				+ " FROM TRN_CHEQUE_DTLS CHEQUEDET,RLT_BILL_CHEQUE BILLCHEQUE,TRN_BILL_REGISTER BILLREG,TRN_VOUCHER_DETAILS VOUCHERDET "
				+ "  WHERE CHEQUEDET.CHEQUE_ID=BILLCHEQUE.CHEQUE_ID AND BILLCHEQUE.BILL_NO=BILLREG.BILL_NO AND BILLCHEQUE.BILL_NO=VOUCHERDET.BILL_NO AND "
				+ " ((to_char(to_dt,'MM') - (to_char(status_date,'MM')+1))+1)>3 and CHEQUEDET.STATUS!='CHQ_RVLD' AND CLEARED_DT IS NULL AND ( to_char(STATUS_DATE,'dd/mm/yyyy')>='"
				+ strFromDate
				+ "' AND to_char(status_date,'dd/mm/yyyy')<='"
				+ strToDate
				+ "' )  "
				+ " AND CHEQUEDET.LOCATION_CODE='"
				+ strLocationCode + "' ORDER BY CHEQUE_NO";
		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();
		int Counter = 0;
		//System.out.println("While before");
		while (it.hasNext()) {
			//System.out.println(Counter);
			Object[] tuple = (Object[]) it.next();
			List innerList = new ArrayList();
			Counter++;
			innerList.add(Counter);
			try {
				innerList.add(tuple[0].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[1].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[2].toString());

			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[3].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[4].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[5].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				double dAmount = Double.parseDouble(tuple[6].toString());
				String strAmount = dFormat.format(dAmount);
				innerList.add(strAmount);
			} catch (Exception Ex) {
				innerList.add("-");
			}
			chequeData.add(innerList);
		}
		return chequeData;
	}
	 /**
	 * Method to getting OutSide Cheque Data
	 * 
	 * @param  	String strFromDate, 
	 * 			String strToDate,
	 *			String strLocID, 
	 *			String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getOutSideChequeData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode) {
		List chequeData = new ArrayList();
		Session hibSession = getSession();
		String strQuery = "SELECT CHEQUEDET.CHEQUE_NO,BILLREG.CHEQUE_DISP_DT,CHEQUEDET.CHEQUE_AMT"
				+ " FROM TRN_CHEQUE_DTLS CHEQUEDET, RLT_BILL_CHEQUE BILLCHEQUE,TRN_BILL_REGISTER BILLREG "
				+ "  WHERE CHEQUEDET.CHEQUE_ID=BILLCHEQUE.CHEQUE_ID AND BILLCHEQUE.BILL_NO=BILLREG.BILL_NO AND "
				+ " ((to_char(to_dt,'MM') - (to_char(status_date,'MM')+1))+1)<=3 AND CLEARED_DT IS NULL"
				+ " AND ( to_char(status_date,'dd/mm/yyyy')>='"
				+ strFromDate
				+ "' AND to_char(status_date,'dd/mm/yyyy')<='"
				+ strToDate
				+ "' ) "
				+ " AND CHEQUEDET.LOCATION_CODE='"
				+ strLocationCode
				+ "' order by cheque_no";
		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();
		int Counter = 0;
		//System.out.println("While before");
		while (it.hasNext()) {
			//System.out.println(Counter);
			Object[] tuple = (Object[]) it.next();
			List innerList = new ArrayList();
			Counter++;
			innerList.add(Counter);
			try {
				innerList.add(tuple[0].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[1].toString())));
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				double dAmount = Double.parseDouble(tuple[2].toString());
				String strAmount = dFormat.format(dAmount);
				innerList.add(strAmount);

			} catch (Exception Ex) {
				innerList.add("-");
			}
			chequeData.add(innerList);
		}
		return chequeData;
	}
	 /**
	 * Method to getting Cheque Data
	 * 
	 * @param  String strChequeData, String strLocationCode
	 * @return List
	 * @author vidhya
	 */
	public List getChequeData(String strChequeData, String strLocationCode) {
		//System.out.println("Hi I am here for getting each cheque details for "+ strChequeData);
		List chequeData = new ArrayList();
		Session hibSession = getSession();
		String strQuery = "SELECT CHEQUEDET.FROM_DT,CHEQUEDET.PARTY_NAME,BILLREG.BILL_NO,VOUCHERDET.MAJOR_HEAD,CHEQUEDET.CHEQUE_AMT"
				+ " FROM TRN_CHEQUE_DTLS CHEQUEDET,RLT_BILL_CHEQUE BILLCHEQUE,TRN_BILL_REGISTER BILLREG,TRN_VOUCHER_DETAILS VOUCHERDET "
				+ " WHERE CHEQUEDET.CHEQUE_ID=BILLCHEQUE.CHEQUE_ID AND BILLCHEQUE.BILL_NO=BILLREG.BILL_NO AND BILLCHEQUE.BILL_NO=VOUCHERDET.BILL_NO AND CHEQUEDET.CHEQUE_NO="
				+ strChequeData
				+ " AND CHEQUEDET.LOCATION_CODE='"
				+ strLocationCode + "'";
		Query sqlQuery = hibSession.createSQLQuery(strQuery);
		List resList = sqlQuery.list();
		if (resList.size() == 0) {
			return null;
		}
		Iterator it = resList.iterator();
		int Counter = 0;
		//System.out.println("While before");
		while (it.hasNext()) {
			//System.out.println(Counter);
			Object[] tuple = (Object[]) it.next();
			List innerList = new ArrayList();
			Counter++;
			innerList.add(Counter);
			try {
				innerList.add(new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(tuple[0].toString())));

			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[1].toString());
			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[2].toString());

			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[3].toString());

			} catch (Exception Ex) {
				innerList.add("-");
			}
			try {
				innerList.add(tuple[4].toString());

			} catch (Exception Ex) {
				innerList.add("-");
			}
			chequeData.add(innerList);
		}
		return chequeData;
	}
	 /**
	 * Method to getting Auditor Wise Daily Report
	 * 
	 * @param  	String fromDate, 
	 * 			String toDate,
	 *			Long audPostId, 
	 *			Long vitoCode, 
	 *			Long locId, 
	 *			String strLocationCode,
	 *			Long langID
	 * @return ArrayList
	 * @author vidhya
	 */
	// Auditor Wise Daily Vito Report
	public ArrayList getAudWiseDailyRptDtls(String fromDate, String toDate,
			Long audPostId, Long vitoCode, Long locId, String strLocationCode,
			Long langID) {
		ArrayList arrOuter = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append("select max(em.empFname) ,max(em.empMname),max(em.empLname),count(br.billNo),sum(br.billNetAmount),br.audPostId "
							+ " from OrgEmpMst em,TrnBillRegister br,OrgUserpostRlt pm "
							+ " where em.orgUserMst.userId = pm.orgUserMst.userId and pm.orgPostMstByPostId.postId = br.audPostId ");

			if (fromDate != null && toDate != null) {
				query.append(" and to_char(br.inwardDt,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(br.inwardDt,'yyyy-mm-dd')<='" + toDate
						+ "'");

			}
			if (audPostId != -1) {
				query.append(" and br.audPostId=" + audPostId);
			}
			if (vitoCode != null) {
				query
						.append(" and br.billNo in(select vr.billNo from BillVitoRegister vr where vr.vitoCode ="
								+ vitoCode + " and vr.vitoType=71)");
			}
			query.append(" and br.locationCode = '" + strLocationCode + "'"
					+ " and em.cmnLanguageMst.langId=" + langID);
			query.append(" group by br.audPostId order by 1,2,3");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				arrOuter = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arrInner = new ArrayList();
					arrInner.add(count);
					arrInner.add(tuple[0] + " " + tuple[1] + " " + tuple[2]);
					arrInner.add(tuple[3]);
					DecimalFormat dFormat = new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount = Double.parseDouble(tuple[4].toString());
					String strAmount = dFormat.format(dAmount);
					arrInner.add(strAmount);
					arrOuter.add(arrInner);
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getAudWiseDailyRptDtls # \n"
							+ e);
		}
		return arrOuter;
	}
	 /**
	 * Method to getting  Cheque Advice Report
	 * 
	 * @param 	String fromDate, 
	 * 			String toDate,
	 *			Long vitoCode,
	 *			Long locId, 
	 *			String strLocationCode
	 * @return ArrayList
	 * @author vidhya
	 */
	// Cheque Advice Vito Report
	public ArrayList getChequeAdviceRpt(String fromDate, String toDate,
			Long vitoCode, Long locId, String strLocationCode) {
		ArrayList arrOuter = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query
					.append("select cd.adviceNo ,cd.chequeNo,cd.partyName,cd.chequeAmt "
							+ " from TrnChequeDtls cd where cd.adviceNo is NOT NULL ");

			if (fromDate != null && toDate != null) {
				query.append(" and to_char(cd.printDate,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(cd.printDate,'yyyy-mm-dd')<='"
						+ toDate + "'");
			}
			if (vitoCode != null) {
				query
						.append(" and cd.chequeNo in(select vr.chequeNo from ChequeVitoRegister vr where vr.vitoCode ="
								+ vitoCode + " and vr.vitoType=72)");
			}
			query.append(" and cd.locationCode = '" + strLocationCode + "'");
			query.append(" order by cd.adviceNo asc");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList != null && resList.size() > 0) {
				arrOuter = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arrInner = new ArrayList();
					if (tuple[0] != null) {
						arrInner.add(count);
						arrInner.add(tuple[0]);
						arrInner.add(tuple[1]);
						arrInner.add(tuple[2]);
						DecimalFormat dFormat = new DecimalFormat();
						dFormat.applyPattern("#0.00");
						double dAmount = Double
								.parseDouble(tuple[3].toString());
						String strAmount = dFormat.format(dAmount);
						arrInner.add(strAmount);
						arrOuter.add(arrInner);
						count++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getChequeAdviceRpt # \n"
							+ e);
		}
		return arrOuter;
	}
	 /**
	 * Method to getting Auditor Wise Bill Detail
	 * 
	 * @param  	String fromDate, 
	 * 			String toDate,
	 *			Long billType, 
	 *			String[] audPostId, 
	 *			Long vitoCode, Long locId,
	 *			String strLocationCode, 
	 *			Long langID
	 * @return ArrayList
	 * @author vidhya
	 */
	// Auditor Wise Bill Type Vito Report
	public ArrayList getAuditorWiseBillDetail(String fromDate, String toDate,
			Long billType, String[] audPostId, Long vitoCode, Long locId,
			String strLocationCode, Long langID) {
		ArrayList arraylistOuter = null;
		try {
			Session hibSession = getSession();
			Query sqlQuery = null;
			StringBuffer query = new StringBuffer();

			query
					.append("SELECT br.subjectId, max(bt.subjectDesc), count(br.billNo), sum(br.billNetAmount),max(em.empFname) ,max(em.empMname),max(em.empLname) "
							+ " FROM TrnBillRegister br, MstBillType bt,OrgEmpMst em,OrgUserpostRlt pm "
							+ " WHERE br.subjectId = bt.subjectId and em.orgUserMst.userId = pm.orgUserMst.userId and pm.orgPostMstByPostId.postId = br.audPostId ");

			if (fromDate != null && toDate != null) {
				query.append(" and to_char(br.inwardDt,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(br.inwardDt,'yyyy-mm-dd')<='" + toDate
						+ "'");
			}
			if (vitoCode != null) {
				query
						.append(" and br.billNo in(select vr.billNo from BillVitoRegister vr where vr.vitoCode ="
								+ vitoCode + " and vr.vitoType=73)");
			}
			if (billType != -1) {
				query.append(" and bt.subjectId =" + billType);
			}
			if (audPostId.length > 0) {

				if (audPostId.length == 1
						&& !audPostId[0].equalsIgnoreCase("-1")) {
					query.append(" and br.audPostId in (" + audPostId[0] + ")");
				} else if (audPostId.length >= 2) {
					query.append(" and br.audPostId in(");
					for (int i = 0; i < audPostId.length; i++)
						if (!audPostId[i].equalsIgnoreCase("-1")) {
							if (i != (audPostId.length - 1)) {
								query.append(audPostId[i] + ",");
							} else {
								query.append(audPostId[i]);
							}
						}
					query.append(")");
				}
			}
			query.append(" and br.locationCode = '" + strLocationCode + "'"
					+ " and bt.langId=" + langID
					+ " and em.cmnLanguageMst.langId=" + langID);
			query.append(" group by br.audPostId, br.subjectId");
			query.append(" order by 2");

			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();

			if (resList != null) {
				Iterator it = resList.iterator();
				arraylistOuter = new ArrayList();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arraylistInner = new ArrayList();
					arraylistInner.add(count++);
					arraylistInner.add(tuple[1]);
					arraylistInner.add(tuple[2]);
					DecimalFormat dFormat = new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount = Double.parseDouble(tuple[3].toString());
					String strAmount = dFormat.format(dAmount);
					arraylistInner.add(strAmount);
					arraylistInner.add(tuple[4] + " " + tuple[5] + " "
							+ tuple[6]);
					arraylistOuter.add(arraylistInner);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getAuditorWiseBillTypeRecord # \n"
							+ e);
		}
		return (arraylistOuter);
	}
	 /**
	 * Method to getting Bill Tran Register For Auditor Detail
	 * 
	 * @param  	String fromDate, 
	 * 			String toDate,
	 *			String[] audPostId, 
	 *			Long vitoCode, 
	 *			Long locId,
	 *			String strLocationCode, 
	 *			Long langID
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getBillTranRegForAudDtls(String fromDate, String toDate,
			String[] audPostId, Long vitoCode, Long locId,
			String strLocationCode, Long langID) {
		ArrayList arraylistOuter = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append("SELECT br.billCntrlNo, br.tokenNum, br.budmjrHd, bt.shortName, dm.cardexNo ,dm.shortName, br.billNetAmount, em.empFname,em.empMname,em.empLname, br.refNum"
							+ " FROM TrnBillRegister br, MstBillType bt,OrgEmpMst em, OrgDdoMst dm,OrgUserpostRlt pm"
							+ " WHERE em.orgUserMst.userId = pm.orgUserMst.userId and pm.orgPostMstByPostId.postId = br.audPostId "
							+ " and br.ddoCode = dm.ddoCode and br.subjectId = bt.subjectId and br.billNo NOT IN(select distinct billNo from TrnBillMvmnt "
							+ " where mvmntStatus IN('BAPPRV_AUD','BRJCT_AUD'))");

			if (fromDate != null && toDate != null) {
				query.append(" and to_char(br.inwardDt,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(br.inwardDt,'yyyy-mm-dd')<='" + toDate
						+ "'");
			}
			if (vitoCode != null) {
				query
						.append(" and br.billNo in(select vr.billNo from BillVitoRegister vr where vr.vitoCode ="
								+ vitoCode + " and vr.vitoType=74)");
			}
			if (audPostId.length > 0) {
				if (audPostId.length == 1
						&& !audPostId[0].equalsIgnoreCase("-1")) {
					query.append(" and br.audPostId in (" + audPostId[0] + ")");
				} else if (audPostId.length >= 2) {
					query.append(" and br.audPostId in(");
					for (int i = 0; i < audPostId.length; i++)
						if (!audPostId[i].equalsIgnoreCase("-1")) {
							if (i != (audPostId.length - 1)) {
								query.append(audPostId[i] + ",");
							} else {
								query.append(audPostId[i]);
							}
						}
					query.append(")");
				}
			}
			query.append(" and br.locationCode = '" + strLocationCode + "'"
					+ " and bt.langId=" + langID
					+ " and em.cmnLanguageMst.langId=" + langID
					+ " and dm.langId=" + langID);
			query.append(" order by 7,8,9,10");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();

			if (resList != null) {
				Iterator it = resList.iterator();
				arraylistOuter = new ArrayList();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arraylistInner = new ArrayList();
					arraylistInner.add(tuple[0]);
					arraylistInner.add(tuple[1]);
					arraylistInner.add(tuple[2]);
					arraylistInner.add(tuple[3]);
					arraylistInner.add(tuple[4]);
					arraylistInner.add(tuple[5]);
					arraylistInner.add(tuple[6]);
					arraylistInner.add(tuple[7] + " " + tuple[8] + " "
							+ tuple[9]);
					arraylistInner.add(tuple[10] != null ? tuple[10] : "");
					arraylistOuter.add(arraylistInner);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getBillTypeRecord # \n"
							+ e);
		}
		return (arraylistOuter);
	}
	 /**
	 * Method to getting Bill Tran Register After Auditor Detail
	 * 
	 * @param 	String fromDate,
	 * 			String toDate,
	 *			String[] audPostId, 
	 *			String status, Long vitoCode, Long locId,
	 *			String strLocationCode, Long langID
	 * @return ArrayList
	 * @author vidhya
	 */
	// Bill Transit Register Vito After Audit Report
	public ArrayList getBillTranRegAfterAudDtls(String fromDate, String toDate,
			String[] audPostId, String status, Long vitoCode, Long locId,
			String strLocationCode, Long langID) {
		ArrayList arraylistOuter = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query
					.append("SELECT br.billCntrlNo, br.tokenNum, br.budmjrHd, bt.shortName, dm.cardexNo ,dm.shortName ,br.billNetAmount, em.empFname,em.empMname, em.empLname,br.billNo"
							+ " FROM TrnBillRegister br, MstBillType bt, OrgEmpMst em, OrgDdoMst dm,OrgUserpostRlt pm"
							+ " where em.orgUserMst.userId = pm.orgUserMst.userId and pm.orgPostMstByPostId.postId = br.audPostId "
							+ " and br.ddoCode = dm.ddoCode "
							+ " and  br.subjectId=bt.subjectId and br.billNo IN(select distinct billNo from TrnBillMvmnt "
							+ " where mvmntStatus IN ");
			if (!status.equalsIgnoreCase("-1")) {
				query.append("('" + status + "'))");
			} else {
				query.append("('BAPPRV_AUD','BRJCT_AUD'))");
			}
			if (fromDate != null && toDate != null) {
				query.append(" and to_char(br.inwardDt,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(br.inwardDt,'yyyy-mm-dd')<='" + toDate
						+ "'");
			}
			if (vitoCode != null) {
				query
						.append(" and br.billNo in(select vr.billNo from BillVitoRegister vr where vr.vitoCode ="
								+ vitoCode + " and vr.vitoType=74)");
			}
			if (!status.equalsIgnoreCase("-1")) {
				query.append(" and br.audApprvStat=" + status);
			}
			if (audPostId.length > 0) {

				if (audPostId.length == 1
						&& !audPostId[0].equalsIgnoreCase("-1")) {
					query.append(" and br.audPostId in (" + audPostId[0] + ")");
				} else if (audPostId.length >= 2) {
					query.append(" and br.audPostId in(");
					for (int i = 0; i < audPostId.length; i++)
						if (!audPostId[i].equalsIgnoreCase("-1")) {
							if (i != (audPostId.length - 1)) {
								query.append(audPostId[i] + ",");
							} else {
								query.append(audPostId[i]);
							}
						}
					query.append(")");
				}
			}
			query.append(" and br.locationCode = '" + strLocationCode + "'"
					+ " and bt.langId=" + langID
					+ " and em.cmnLanguageMst.langId=" + langID
					+ " and dm.langId=" + langID);
			query.append(" order by 8,9,10");
			// query.append(" order by br.createdDate desc");
			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();

			if (resList != null) {
				Iterator it = resList.iterator();
				arraylistOuter = new ArrayList();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arraylistInner = new ArrayList();
					arraylistInner.add(tuple[0]);
					arraylistInner.add(tuple[1]);
					arraylistInner.add(tuple[2]);
					arraylistInner.add(tuple[3]);
					arraylistInner.add(tuple[4]);
					arraylistInner.add(tuple[5]);
					arraylistInner.add(tuple[6]);
					String strStatus = getAftAudStatus(Long.parseLong(tuple[10]
							.toString()), strLocationCode);
					arraylistInner.add(strStatus);
					arraylistInner.add(tuple[7] + " " + tuple[8] + " "
							+ tuple[9]);
					arraylistOuter.add(arraylistInner);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getBillTypeRecord # \n"
							+ e);
		}
		return (arraylistOuter);
	}
	 /**
	 * Method to getting Cheque Drawn Register Report
	 * 
	 * @param  	String fromDate, 
	 * 			String toDate,
	 *			Long vitoCode,
	 *			Long locId, 
	 *			String strLocationCode, 
	 *			Long langID
	 * @return ArrayList
	 * @author vidhya
	 */
	public ArrayList getChequeDrawnRegisterRpt(String fromDate, String toDate,
			Long vitoCode, Long locId, String strLocationCode, Long langID) {
		ArrayList arrOuter = null;
		Query sqlQuery = null;
		ResourceBundle bundle = ResourceBundle
		.getBundle("resources/billproc/BillprocConstants");
		String strStatus = bundle.getString("STATUS.CheqCancel");
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append("select distinct cd.chequeNo,br.tokenNum,cd.printDate,cd.partyName,br.billNetAmount,cd.chequeAmt,cd.groupId "
							+ "from TrnChequeDtls cd,TrnBillRegister br,RltBillCheque bc "
							+ "where cd.chequeId=bc.chequeId and bc.billNo=br.billNo and status != '"+strStatus+"' and cd.printDate IS NOT NULL");

			if (fromDate != null && toDate != null) {
				query.append(" and to_char(cd.printDate,'yyyy-mm-dd') >= '"
						+ fromDate
						+ "' and to_char(cd.printDate,'yyyy-mm-dd')<='"
						+ toDate + "'");
			}
			if (vitoCode != null) {
				query
						.append(" and cd.chequeNo in(select vr.chequeNo from ChequeVitoRegister vr where vr.vitoCode ="
								+ vitoCode + " and vr.vitoType=76)");
			}
			query.append(" and cd.locationCode = '" + strLocationCode + "'");
			query.append(" order by br.tokenNum,cd.chequeNo asc");

			sqlQuery = hibSession.createQuery(query.toString());
			List resList = sqlQuery.list();

			List chequeList = new ArrayList();
			int firstIndex = 0;
			String initialGroup = "";
			String lStrGroupId = "";
			if (resList != null && resList.size() > 0) {
				Iterator chequeIt = resList.iterator();
				while (chequeIt.hasNext()) {
					Object[] data = (Object[]) chequeIt.next();
					String strChequeNo = data[0].toString();
					if (chequeList.contains(strChequeNo) == false) {
						chequeList.add(strChequeNo);
					}
					if (firstIndex == 0) {
						initialGroup = data[6].toString();
						firstIndex++;
					}

				}
			}
			long count = 1;
			arrOuter = new ArrayList();
			for (int c = 0; c < chequeList.size(); c++) {
				String strChequeNo = chequeList.get(c).toString();
				String strTokenNumber = "";
				String strChequeDate = "";
				String strPayeeName = "";
				String strBillAmount = "";
				String strChequeAmount = "";
				String strTokenForPrint = "";
				String strBillAmountForPrint = "";

				String strLastChequeNo = "";
				String lStrMulCheqAmt = "";
				float i = 0;
				int j = 0;
				if (resList != null && resList.size() > 0) {
					Iterator it = resList.iterator();
					int counts = 0;
					while (it.hasNext()) {

						Object[] tuple = (Object[]) it.next();
						if (counts == c + 1) {
							lStrGroupId = tuple[6].toString();
							//System.out.println("group id is :::" + lStrGroupId+ "count is " + counts);
						}

						if (tuple[0] != null
								&& strChequeNo.equalsIgnoreCase(tuple[0]
										.toString()) == true) {
							strTokenNumber = strTokenNumber
									+ tuple[1].toString() + "-";
							strChequeDate = tuple[2].toString();
							strPayeeName = tuple[3].toString();
							strBillAmount = strBillAmount
									+ tuple[4].toString().trim() + "-";
							strChequeAmount = tuple[5].toString();
							strTokenForPrint = strTokenForPrint
									+ tuple[1].toString() + ",";
							strBillAmountForPrint = strBillAmountForPrint
									+ tuple[4].toString().trim() + ",";
							if (lStrGroupId.equals(initialGroup)) {
								lStrMulCheqAmt = "Show";
							} else {
								lStrMulCheqAmt = "";
							}
						}
						counts++;
					}

				}

				strTokenForPrint = strTokenForPrint.substring(0,
						strTokenForPrint.lastIndexOf(","));
				strBillAmountForPrint = strBillAmountForPrint.substring(0,
						strBillAmountForPrint.lastIndexOf(","));
				ArrayList arrInner = new ArrayList();
				arrInner.add(count);
				arrInner.add(strChequeNo);
				arrInner.add(strTokenForPrint);
				String printDate = new SimpleDateFormat("dd/MM/yyyy")
						.format(new SimpleDateFormat("yyyy-MM-dd")
								.parse(strChequeDate));
				arrInner.add(printDate);
				arrInner.add(strPayeeName);

				arrInner.add(strBillAmountForPrint);
				DecimalFormat dFormat = new DecimalFormat();
				dFormat.applyPattern("#0.00");
				Double dAmount = Double.parseDouble(strChequeAmount);
				String strAmount = dFormat.format(dAmount);
				arrInner.add(strAmount);

				String lArrBillAmt[] = strBillAmountForPrint.split(",");
				Double sumAmount = new Double("0");
				for (int k = 0; k < lArrBillAmt.length; k++) {
					sumAmount = sumAmount + Double.parseDouble(lArrBillAmt[k]);
				}
				String strSumOfBillAmountForPrint = dFormat.format(sumAmount);

				if (initialGroup.equals(lStrGroupId)) {
					if (c == chequeList.size() - 1) {
						arrInner.add(strSumOfBillAmountForPrint);
					} else {
						arrInner.add(" ");
					}

				} else {
					arrInner.add(strSumOfBillAmountForPrint);
				}
				initialGroup = lStrGroupId;
				arrOuter.add(arrInner);

				count++;
				//System.out.println("initialGroup " + initialGroup+ " lStrGroupId" + lStrGroupId);

			}
		}
		/*
		 * if (resList != null) { arrOuter = new ArrayList(); Iterator it =
		 * resList.iterator(); Integer count = 1; while(it.hasNext()) { Object[]
		 * tuple = (Object[])it.next(); ArrayList arrInner = new ArrayList();
		 * arrInner.add(count); arrInner.add(tuple[0]); arrInner.add(tuple[1]);
		 * arrInner.add(tuple[2]); arrInner.add(tuple[3]); DecimalFormat
		 * dFormat=new DecimalFormat(); dFormat.applyPattern("#0.00"); double
		 * dAmount=Double.parseDouble(tuple[4].toString()); String
		 * strAmount=dFormat.format(dAmount); arrInner.add(strAmount);
		 * dFormat=new DecimalFormat(); dFormat.applyPattern("#0.00");
		 * dAmount=Double.parseDouble(tuple[5].toString());
		 * strAmount=dFormat.format(dAmount); arrInner.add(strAmount);
		 * arrOuter.add(arrInner); count++; } } }
		 */
		catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"
							+ e);
		}
		return arrOuter;
	}
	 /**
	 * Method to getting Date Wise Receipt Report
	 * 
	 * @param  	String fMjrHead, 
	 * 			String tMjrHead,
	 *			String fromDate,
	 *			String toDate,
	 *			Long locId
	 * @return ArrayList
	 * @author vidhya
	 */
	
	public ArrayList getDateWiseReceiptRpt(String fMjrHead, String tMjrHead,
			String fromDate, String toDate, Long locId) {
		ArrayList arrOuter = null;
		Query sqlQuery = null;

		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			query
					.append("SELECT TO_CHAR(T1.BS_DATE,'DD/MM/YYYY'), COUNT(T2.*), SUM(T2.AMOUNT) FROM TRN_BANK_SCROLL_DETAILS T1, TRN_BS_RCPT_ENTRIES T2 WHERE");

			if (fromDate != null && toDate != null) {
				query.append("  TO_CHAR(T1.BS_DATE,'DD/MM/YYYY') >= '"
						+ fromDate
						+ "' AND TO_CHARTO_CHAR(T1.BS_DATE,'DD/MM/YYYY')<= '"
						+ toDate + "'");
			}
			/*
			 * if(vitoCode != null) { query.append(" and cd.chequeNo in(select
			 * vr.chequeNo from ChequeVitoRegister vr where vr.vitoCode
			 * ="+vitoCode+" and vr.vitoType=76)"); }
			 */
			query.append(" AND T2.MAJOR_HEAD >= '" + fMjrHead
					+ "' AND T2.MAJOR_HEAD <= '" + tMjrHead + "'");
			query.append(" AND T2.LOC_ID = " + locId);
			query.append(" AND T1.BS_DETAILS_ID = T2.BS_DETAIL_ID ");
			query.append(" GROUP BY TO_CHAR(T1.BS_DATE,'DD/MM/YYYY')");

			//System.out.println("SQL QUERY : " + query.toString());

			sqlQuery = hibSession.createSQLQuery(query.toString());
			List resList = sqlQuery.list();
			if (resList != null) {
				arrOuter = new ArrayList();
				Iterator it = resList.iterator();
				Integer count = 1;
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					ArrayList arrInner = new ArrayList();
					arrInner.add(count);
					arrInner.add(tuple[0]);
					arrInner.add(tuple[1]);
					arrInner.add(tuple[2]);
					// arrInner.add(tuple[3]);
					// arrInner.add(tuple[4]);
					// arrInner.add(tuple[5]);
					arrOuter.add(arrInner);
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			glogger
					.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"
							+ e);
		}
		return arrOuter;
	}

}
