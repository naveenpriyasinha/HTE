/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Feb 1, 2011		Anjana Suvariya								
 *******************************************************************************
 */
package com.tcs.sgv.pensionproc.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.constant.Constants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.pensionproc.valueobject.SavedPensionCasesVO;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcAdvnceBal;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcAssesdDues;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcCheckList;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcPnsnCalc;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcPnsnrDtls;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcQlyServ;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcRecovery;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcRevision;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnprocAgDtls;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnprocAuthorityDtls;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnprocAvgPayCalc;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnprocEventdtls;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnprocForeignServ;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnprocPnsnrpay;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnprocPnsnrservcbreak;
import com.tcs.sgv.pensionproc.valueobject.TrnPnsnprocProvisionalPaid;

/**
 * Class Description -
 * 
 * 
 * @author Anjana Suvariya
 * @version 0.1
 * @since JDK 5.0 Feb 1, 2011
 */

public class TrnPnsnProcInwardPensionDAOImpl extends GenericDaoHibernateImpl implements TrnPnsnProcInwardPensionDAO {

	private Session ghibSession = null;
	private static final Logger gLogger = Logger.getLogger(TrnPnsnProcInwardPensionDAOImpl.class);

	private final ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionproc/PensionCaseConstants");

	public TrnPnsnProcInwardPensionDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	public List<SavedPensionCasesVO> getAllFrwdCases(String lStrCasesFrom, String gStrPostId, String lStrDraftFlag, Map displayTag,Long lLngDdoCode) {
		List<SavedPensionCasesVO> lLstPensionCases = null;
		Query lHibQry = null;
		StringBuilder lSBQuery = new StringBuilder();
		Session session = getSession();
		
		try {
			String[] columnValues = new String[] {"","inw.inwardNo", "inw.sevaarthId", "dtl.pnsnrName", "inw.pensionType", "loc.locName",
					"dtl.joiningDate", "dtl.retirementDate","inw.caseStatus" };

			lSBQuery.append("SELECT new com.tcs.sgv.pensionproc.valueobject.SavedPensionCasesVO(inw.inwardPensionId,inw.inwardNo,inw.sevaarthId,dtl.pnsnrName,inw.pensionType, ");
			lSBQuery.append("loc.locName,dtl.joiningDate,dtl.retirementDate,inw.caseStatus,inw.outwardNo,inw.outwardDate,inw.pensionCaseType,inw.pensionFlag,inw.penDdoCode) FROM TrnPnsnProcInwardPension inw , TrnPnsnProcPnsnrDtls dtl,CmnLocationMst loc");
			lSBQuery.append(" WHERE  inw.inwardPensionId=dtl.inwardPensionId ");
			lSBQuery.append("AND inw.draftFlag in (:lStrDraftFlag) " );
			lSBQuery.append(" AND dtl.departmentId = loc.locId AND inw.caseStatus IN (:caseStatus)");
			lSBQuery.append(" AND ((((inw.pensionFlag is null or inw.pensionFlag ='P1') and  inw.ddoCode = :ddoCode) or (inw.penDdoCode = :ddoCode and inw.pensionFlag in ('FP','P')))) ");
			
		
			
			//}
		/*	else{
			lSBQuery.append(" AND inw.ddoCode = :ddoCode " );
			gLogger.info("Inside else 95*******");
			}*/
			if (lStrDraftFlag.equalsIgnoreCase("D")) {
				//	lSBQuery.append(" AND inw.createdPostId=:createdPostId ");
			}
			
			String orderString = (displayTag.containsKey(Constants.KEY_SORT_ORDER) ? (String) displayTag.get(Constants.KEY_SORT_ORDER) : "desc");

			Integer orderbypara = null;

			if (displayTag.containsKey(Constants.KEY_SORT_PARA)) {
				orderbypara = (Integer) displayTag.get(Constants.KEY_SORT_PARA);
				lSBQuery.append(" ORDER BY "+columnValues[orderbypara.intValue()] + " " + orderString);
			} else {
				lSBQuery.append(" ORDER BY inw.inwardPensionId");
			}

			lHibQry = session.createQuery(lSBQuery.toString());

			lHibQry.setParameter("ddoCode",lLngDdoCode);			
			//updated by vishnu-start for separate rejected and draft cases
			//if (lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("R")) {
			if (lStrDraftFlag.equalsIgnoreCase("D")) {	
			//lHibQry.setParameter("createdPostId", Long.valueOf(gStrPostId));					
				List<String> lLstcaseStatus = new ArrayList<String>();
				lLstcaseStatus.add("D");
				//lLstcaseStatus.add("R");
				//lLstcaseStatus.add("A");
				lHibQry.setParameterList("lStrDraftFlag", lLstcaseStatus);
			}
			//Added by shraddha for deputation module
			else if ("P".equalsIgnoreCase(lStrDraftFlag)) {
				lHibQry.setParameter("caseStatus", gObjRsrcBndle.getString("PPROC.FWDTOPENSTATUSID"));
				lHibQry.setParameter("lStrDraftFlag", lStrDraftFlag);
			}
			
			
		else if( lStrDraftFlag.equalsIgnoreCase("R")){
			List<String> lLstcaseStatus = new ArrayList<String>();
			lLstcaseStatus.add("R");
			lHibQry.setParameterList("lStrDraftFlag", lLstcaseStatus);
		}
			else {
				lHibQry.setParameter("lStrDraftFlag", lStrDraftFlag);
			}
			if ((lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("F") || lStrDraftFlag.equalsIgnoreCase("R") || lStrDraftFlag.equalsIgnoreCase("A"))) {
				//if (lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("R")) {
					if (lStrDraftFlag.equalsIgnoreCase("D")) {
					//lHibQry.setParameter("createdPostId", Long.valueOf(gStrPostId));					
					List<String> lLstcaseStatus = new ArrayList<String>();
					lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.DRAFTSTATUSID"));
					/*lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYDDOSTATUSID"));
					lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.MOVEFORDDOMISTAKESTATUSID"));
					lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.MOVEFORAGQUERYSTATUSID"));*/					
					lHibQry.setParameterList("caseStatus", lLstcaseStatus);
				} else {

					if (lStrDraftFlag.equalsIgnoreCase("F")) {
						if ("V".equalsIgnoreCase(lStrCasesFrom)) {
							lHibQry.setParameter("caseStatus", gObjRsrcBndle.getString("PPROC.FWDBYDEOSTATUSID"));
						}
						if ("D".equalsIgnoreCase(lStrCasesFrom)) {
							lHibQry.setParameter("caseStatus", gObjRsrcBndle.getString("PPROC.FWDBYDEOSTATUSID"));
						}
					} else if (lStrDraftFlag.equalsIgnoreCase("R")) {
						List<String> lLstcaseStatus = new ArrayList<String>();
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYDDOSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYVERIFIERSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYAGSTATUSID"));
						lHibQry.setParameterList("caseStatus", lLstcaseStatus);
					}
					//updated by vishnu-end
					else // lStrDraftFlag.equalsIgnoreCase("A")
					{
						List<String> lLstcaseStatus = new ArrayList<String>();
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.APPROVEDBYDDOSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.SENDTOAGSTATUSID"));	
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.APPROVEDBYAGSTATUSID"));
						lHibQry.setParameterList("caseStatus", lLstcaseStatus);
					}
				}
			}

			Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO) ? (Integer) displayTag.get(Constants.KEY_PAGE_NO) : 1);
			lHibQry.setFirstResult((pageNo.intValue() - 1) * Constants.PDWL_PAGE_SIZE);
			lHibQry.setMaxResults(Constants.PDWL_PAGE_SIZE);

			lLstPensionCases = lHibQry.list();

		} catch (Exception e) {

			gLogger.error("Error is" + e, e);

		}
		return lLstPensionCases;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#getAllFrwdCasesCount
	 * (java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */

	public Integer getAllFrwdCasesCount(String lStrCasesFrom, String gStrPostId, String lStrDraftFlag, Map displayTag,Long lLngDdoCode) {

		Integer lIntCount = null;
		Query lHibQry = null;
		StringBuffer lSBQuery = null;
		try {
			lSBQuery = new StringBuffer();

			lSBQuery.append("SELECT COUNT(*) FROM TrnPnsnProcInwardPension inw , TrnPnsnProcPnsnrDtls dtl,CmnLocationMst loc");
			lSBQuery.append(" WHERE  inw.inwardPensionId=dtl.inwardPensionId ");
			lSBQuery.append(" AND dtl.departmentId = loc.locId AND inw.caseStatus IN (:caseStatus)");
			lSBQuery.append(" AND inw.draftFlag in (:lStrDraftFlag) " );
			lSBQuery.append(" AND ((inw.pensionFlag is null and  inw.ddoCode = :ddoCode) or (inw.penDdoCode = :ddoCode and inw.pensionFlag in ('FP','P') )) ");
			
			
//			lSBQuery
//					.append("SELECT COUNT(*) FROM TrnPnsnProcInwardPension inw , TrnPnsnProcPnsnrDtls dtl,CmnLocationMst loc,CmnLookupMst look ");
//			lSBQuery.append(" WHERE  inw.inwardPensionId=dtl.inwardPensionId ");
//			lSBQuery.append(" AND  inw.draftFlag=:lStrDraftFlag");
//			lSBQuery
//					.append(" AND inw.pensionType = look.lookupId AND dtl.departmentId = loc.locId AND inw.caseStatus IN (:caseStatus)");
			if (lStrDraftFlag.equalsIgnoreCase("D")) {
				//	lSBQuery.append(" AND inw.createdPostId=:createdPostId ");
			}
			
		
			lHibQry = ghibSession.createQuery(lSBQuery.toString());
			lHibQry.setParameter("ddoCode",lLngDdoCode);
			//updated by vishnu-start for separate rejected and draft cases
			//if (lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("R")) {
			if (lStrDraftFlag.equalsIgnoreCase("D")) {	
			//lHibQry.setParameter("createdPostId", Long.valueOf(gStrPostId));					
				List<String> lLstcaseStatus = new ArrayList<String>();
				lLstcaseStatus.add("D");
				//lLstcaseStatus.add("R");
				//lLstcaseStatus.add("A");
				lHibQry.setParameterList("lStrDraftFlag", lLstcaseStatus);
			}
		else if( lStrDraftFlag.equalsIgnoreCase("R")){
			List<String> lLstcaseStatus = new ArrayList<String>();
			lLstcaseStatus.add("R");
			lHibQry.setParameterList("lStrDraftFlag", lLstcaseStatus);
		}
			
			
			
			
			else {
				lHibQry.setParameter("lStrDraftFlag", lStrDraftFlag);
			}
			if ((lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("F") || lStrDraftFlag.equalsIgnoreCase("R") || lStrDraftFlag.equalsIgnoreCase("A") || lStrDraftFlag.equalsIgnoreCase("P"))) {
				//if (lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("R")) {
					if (lStrDraftFlag.equalsIgnoreCase("D")) {
					//lHibQry.setParameter("createdPostId", Long.valueOf(gStrPostId));					
					List<String> lLstcaseStatus = new ArrayList<String>();
					lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.DRAFTSTATUSID"));
					/*lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYDDOSTATUSID"));
					lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.MOVEFORDDOMISTAKESTATUSID"));
					lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.MOVEFORAGQUERYSTATUSID"));*/					
					lHibQry.setParameterList("caseStatus", lLstcaseStatus);
				} else {

					if (lStrDraftFlag.equalsIgnoreCase("F")) {
						if ("V".equalsIgnoreCase(lStrCasesFrom)) {
							lHibQry.setParameter("caseStatus", gObjRsrcBndle.getString("PPROC.FWDBYDEOSTATUSID"));
						}
						if ("D".equalsIgnoreCase(lStrCasesFrom)) {
							lHibQry.setParameter("caseStatus", gObjRsrcBndle.getString("PPROC.FWDBYDEOSTATUSID"));
						}
						//Added by shraddha for deputation module
						else if ("P".equalsIgnoreCase(lStrDraftFlag)) {
							lHibQry.setParameter("caseStatus", gObjRsrcBndle.getString("PPROC.FWDTOPENSTATUSID"));
						}
						
						
					} else if (lStrDraftFlag.equalsIgnoreCase("R")) {
						List<String> lLstcaseStatus = new ArrayList<String>();
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYDDOSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYVERIFIERSTATUSID"));
						lHibQry.setParameterList("caseStatus", lLstcaseStatus);
					}
					//updated by vishnu-end
					else // lStrDraftFlag.equalsIgnoreCase("A")
					{
						List<String> lLstcaseStatus = new ArrayList<String>();
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.APPROVEDBYDDOSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.SENDTOAGSTATUSID"));	
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.APPROVEDBYAGSTATUSID"));
						lHibQry.setParameterList("caseStatus", lLstcaseStatus);
					}
				}
			}

			if (lHibQry.list() != null && lHibQry.list().size() > 0) {
				lIntCount = Integer.parseInt(lHibQry.list().get(0).toString());
			} else {
				lIntCount = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
		}

		return lIntCount;

	}

	public List<SavedPensionCasesVO> getPensionCaseDtls(String lStrCasesFrom, String lStrDraftFlag, String gStrPostId, Long gLngLangId, String lStrSearchby,
			String lStrSearchValue, Date lDtFrmDate, Date lDtToDate, String lStrSevaarthId, String lStrPPONo, String lStrInwardNo, Date lDtRetiredDate, String lStrName,
			Long lLngDeparmentTypeId, String lStrPensionTypeId, Map displayTag, String lStrBranchName) throws Exception {
		gLogger.info("getPensionCaseDtls start.......");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<SavedPensionCasesVO> lLstInwardCaseDtls = null;
		Query lHibQry = null;
		StringBuilder lSBQuery = new StringBuilder();
		Session session = getSession();
		Date lDtRetirementDate = null;
		Date lDtInwardDate = null;
		try {

			String[] columnValues = new String[] {"","inw.inwardNo", "inw.sevaarthId", "dtl.pnsnrName", "inw.pensionType", "loc.locName",
					"dtl.joiningDate", "dtl.retirementDate","inw.caseStatus" };
			lSBQuery.append(" SELECT new com.tcs.sgv.pensionproc.valueobject.SavedPensionCasesVO(inw.inwardPensionId,inw.inwardNo,inw.sevaarthId,dtl.pnsnrName,inw.pensionType, ");
			lSBQuery.append(" loc.locName,dtl.joiningDate,dtl.retirementDate,inw.caseStatus,inw.outwardNo,inw.outwardDate,inw.pensionCaseType,inw.pensionFlag,inw.penDdoCode) ");
			lSBQuery.append(" FROM TrnPnsnProcInwardPension inw , TrnPnsnProcPnsnrDtls dtl ,CmnLocationMst loc ");
			lSBQuery.append(" WHERE inw.inwardPensionId=dtl.inwardPensionId ");
			lSBQuery.append(" AND dtl.departmentId = loc.locId AND loc.cmnLanguageMst.langId =:gLngLangId ");
			if ((lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("F") || lStrDraftFlag.equalsIgnoreCase("R") || lStrDraftFlag.equalsIgnoreCase("A") || lStrDraftFlag.equalsIgnoreCase("P"))) {
				lSBQuery.append(" AND inw.draftFlag in (:lStrDraftFlag) ");
				lSBQuery.append(" AND inw.caseStatus IN (:caseStatus)");
			}
			if (!lStrSearchby.equals("-1") && lStrSearchValue != null) {
				if (!lStrSearchby.equals("-1") && (!lStrSearchValue.equals("") || lStrSearchValue.length() > 1)) {
					if (lStrSearchby.equals("Inward No")) {
						lSBQuery.append(" AND inw.inwardNo= :lStrSearchValue");
					}
					if (lStrSearchby.equals("Inward Date")) {

						lDtInwardDate = simpleDateFormat.parse(lStrSearchValue);
						lSBQuery.append(" AND inw.inwardDate= :lStrSearchValue");
					}
					if (lStrSearchby.equals("Inward Type")) {
						lSBQuery.append(" AND inw.caseType= :lStrSearchValue ");
					}
					if (lStrSearchby.equals("Retirement Date")) {

						lDtRetirementDate = simpleDateFormat.parse(lStrSearchValue);
						lSBQuery.append(" AND dtl.retirementDate= :lStrSearchValue ");
					}
					if (lStrSearchby.equals("Pension Type")) {
						lSBQuery.append(" AND inw.pensionType= :lStrSearchValue ");
					}
					if (lStrSearchby.equals("Bank Branch Name")) {
						lSBQuery.append(" AND dtl.bankName= :lStrSearchValue ");
						lSBQuery.append(" AND dtl.bankBranchName= :lStrBranchName ");
					}
					if (lStrSearchby.equals("PPO No")) {
						lSBQuery.append(" AND inw.ppoNo= :lStrSearchValue ");
					}
					
					if (lStrSearchby.equals("Sevaarth ID")) {
						lSBQuery.append(" AND inw.sevaarthId= :lStrSearchValue ");
						//logger.info("lStrSearchValue$$$$$$"+lStrSearchValue);
					}
					
					if (lStrSearchby.equals("Name of Pensioner")) {
						lSBQuery.append(" AND dtl.pnsnrName= :lStrSearchValue ");
					}
				}

			} else {
				if (lDtFrmDate != null && lDtToDate != null) {
					lSBQuery.append(" AND inw.inwardDate BETWEEN :lDtFrmDate AND :lDtToDate ");
				}
				if (lStrSevaarthId != null) {
					lSBQuery.append(" AND inw.sevaarthId = :lStrSevaarthId");
				}
				if (lStrInwardNo != null) {
					lSBQuery.append(" AND inw.inwardNo = :lStrInwardNo");
				}
				if (lStrPPONo != null) {
					lSBQuery.append(" AND inw.ppoNo= :lStrPPONo");
				}
				if (lDtRetiredDate != null) {
					lSBQuery.append(" AND dtl.retirementDate = :lDtRetiredDate");
				}
				if (lStrName != null && !lStrName.equals("")) {
					lSBQuery.append(" AND dtl.pnsnrName like :lStrName");
				}
				if (lLngDeparmentTypeId != null) {
					lSBQuery.append(" AND dtl.departmentId = :lLngDeparmentTypeId");
				}
				if (lStrPensionTypeId != null) {
					lSBQuery.append(" AND inw.pensionType = :lLngPensionTypeId");
				}

			}

			String orderString = (displayTag.containsKey(Constants.KEY_SORT_ORDER) ? (String) displayTag.get(Constants.KEY_SORT_ORDER) : "desc");

			Integer orderbypara = null;

			if (displayTag.containsKey(Constants.KEY_SORT_PARA)) {
				orderbypara = (Integer) displayTag.get(Constants.KEY_SORT_PARA);
				lSBQuery.append(" ORDER BY "+columnValues[orderbypara.intValue()] + " " + orderString);
			} else {
				lSBQuery.append(" ORDER BY inw.inwardPensionId");
			}

			lHibQry = session.createQuery(lSBQuery.toString());
			if (!lStrSearchby.equals("-1") && lStrSearchValue != null) {
				if (lStrSearchby.equals("Inward Date")) {
					lHibQry.setParameter("lStrSearchValue", lDtInwardDate);
				} else if (lStrSearchby.equals("Retirement Date")) {
					lHibQry.setParameter("lStrSearchValue", lDtRetirementDate);
				} else {
					lHibQry.setParameter("lStrSearchValue", lStrSearchValue.toUpperCase());
					if (lStrSearchby.equals("Bank Branch Name")) {
						lHibQry.setParameter("lStrBranchName", lStrBranchName);
					}
				}

			} else {
				if ((lDtFrmDate != null && lDtToDate != null) || lStrSevaarthId != null || lStrPPONo != null || lStrInwardNo != null || lDtRetiredDate != null || lStrName != null
						|| lLngDeparmentTypeId != null || lStrPensionTypeId != null) {
					if (lDtFrmDate != null && lDtToDate != null) {
						lHibQry.setParameter("lDtFrmDate", lDtFrmDate);
						lHibQry.setParameter("lDtToDate", lDtToDate);
					}
					if (lStrSevaarthId != null) {
						lHibQry.setParameter("lStrSevaarthId", lStrSevaarthId);
					}
					if (lStrInwardNo != null) {
						lHibQry.setParameter("lStrInwardNo", lStrInwardNo.toUpperCase());
					}
					if (lStrPPONo != null) {
						lHibQry.setParameter("lStrPPONo", lStrPPONo);
					}
					if (lDtRetiredDate != null) {
						lHibQry.setParameter("lDtRetiredDate", lDtRetiredDate);
					}
					if (lStrName != null) {
						lHibQry.setParameter("lStrName", lStrName +"%");
					}
					if (lLngDeparmentTypeId != null) {
						lHibQry.setLong("lLngDeparmentTypeId", lLngDeparmentTypeId);
					}
					if (lStrPensionTypeId != null) {
						lHibQry.setParameter("lLngPensionTypeId", lStrPensionTypeId);
					}
				}
			}
			if ((lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("F") || lStrDraftFlag.equalsIgnoreCase("R") || lStrDraftFlag.equalsIgnoreCase("A"))) {
				//updated by vishnu-start for separate rejected and draft cases
				//if (lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("R")) {
				if (lStrDraftFlag.equalsIgnoreCase("D")) {	
				//lHibQry.setParameter("createdPostId", Long.valueOf(gStrPostId));					
					List<String> draftFlag = new ArrayList<String>();
					draftFlag.add("D");
					//lLstcaseStatus.add("R");
					//lLstcaseStatus.add("A");
					lHibQry.setParameterList("lStrDraftFlag", draftFlag);
				}
			else if( lStrDraftFlag.equalsIgnoreCase("R")){
				List<String> draftFlag = new ArrayList<String>();
				draftFlag.add("R");
				lHibQry.setParameterList("lStrDraftFlag", draftFlag);
			}
				else {
					lHibQry.setParameter("lStrDraftFlag", lStrDraftFlag);
				}
				if ((lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("F") || lStrDraftFlag.equalsIgnoreCase("R") || lStrDraftFlag.equalsIgnoreCase("A"))) {
					//if (lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("R")) {
						if (lStrDraftFlag.equalsIgnoreCase("D")) {
						//lHibQry.setParameter("createdPostId", Long.valueOf(gStrPostId));					
						List<String> lLstcaseStatus = new ArrayList<String>();
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.DRAFTSTATUSID"));
						/*lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYDDOSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.MOVEFORDDOMISTAKESTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.MOVEFORAGQUERYSTATUSID"));*/					
						lHibQry.setParameterList("caseStatus", lLstcaseStatus);
					} else {

						if (lStrDraftFlag.equalsIgnoreCase("F")) {
							if ("V".equalsIgnoreCase(lStrCasesFrom)) {
								lHibQry.setParameter("caseStatus", gObjRsrcBndle.getString("PPROC.FWDBYDEOSTATUSID"));
							}
							if ("D".equalsIgnoreCase(lStrCasesFrom)) {
								lHibQry.setParameter("caseStatus", gObjRsrcBndle.getString("PPROC.FWDBYDEOSTATUSID"));
							}
						} else if (lStrDraftFlag.equalsIgnoreCase("R")) {
							List<String> lLstcaseStatus = new ArrayList<String>();
							lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYDDOSTATUSID"));
							lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYVERIFIERSTATUSID"));
							lHibQry.setParameterList("caseStatus", lLstcaseStatus);
						}
						//updated by vishnu-end
					if (lStrDraftFlag.equalsIgnoreCase("A")) {						
						List<String> lLstcaseStatus = new ArrayList<String>();
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.APPROVEDBYDDOSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.SENDTOAGSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.APPROVEDBYAGSTATUSID"));
						lHibQry.setParameterList("caseStatus", lLstcaseStatus);
					}
					//lHibQry.setParameter("lStrDraftFlag", lStrDraftFlag);
				}
				
			}

			lHibQry.setLong("gLngLangId", gLngLangId);

			Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO) ? (Integer) displayTag.get(Constants.KEY_PAGE_NO) : 1);
			lHibQry.setFirstResult((pageNo.intValue() - 1) * Constants.PDWL_PAGE_SIZE);
			lHibQry.setMaxResults(Constants.PDWL_PAGE_SIZE);

			lLstInwardCaseDtls = lHibQry.list();
			
		}
		}catch (Exception e) {

			gLogger.error("Error is" + e, e);
			throw e;
		}
		gLogger.info("getPensionCaseDtls end.......");
		return lLstInwardCaseDtls;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#
	 * getPensionCaseDtlsCount(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.Long, java.lang.String, java.lang.String,
	 * java.util.Date, java.util.Date, java.lang.Long, java.lang.Long,
	 * java.lang.String, java.util.Date, java.lang.String, java.lang.Long,
	 * java.lang.Long, java.util.Map)
	 */

	public Integer getPensionCaseDtlsCount(String lStrCasesFrom, String lStrDraftFlag, String gStrPostId, Long gLngLangId, String lStrSearchby, String lStrSearchValue,
			Date lDtFrmDate, Date lDtToDate, String lStrSevaarthId, String lStrPPONo, String lStrInwardNo, Date lDtRetiredDate, String lStrName, Long lLngDeparmentTypeId,
			String lStrPensionTypeId, Map displayTag, String lStrBranchName) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Integer lIntCount = null;
		Query lHibQry = null;
		StringBuilder lSBQuery = null;
		Date lDtRetirementDate = null;
		Date lDtInwardDate = null;
		try {
			lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT COUNT(*) FROM TrnPnsnProcInwardPension inw , TrnPnsnProcPnsnrDtls dtl ,CmnLocationMst loc ");
			lSBQuery.append("WHERE inw.inwardPensionId=dtl.inwardPensionId ");
			lSBQuery.append(" AND dtl.departmentId = loc.locId");
			lSBQuery.append(" AND loc.cmnLanguageMst.langId =:gLngLangId  ");
			if ((lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("F") || lStrDraftFlag.equalsIgnoreCase("R") || lStrDraftFlag.equalsIgnoreCase("A"))) {
				lSBQuery.append(" AND inw.draftFlag in (:lStrDraftFlag) ");
				lSBQuery.append(" AND inw.caseStatus IN (:caseStatus)");

			}
			if (!lStrSearchby.equals("-1") && lStrSearchValue != null) {
				if (!lStrSearchby.equals("-1") && (!lStrSearchValue.equals("") || lStrSearchValue.length() > 1)) {
					if (lStrSearchby.equals("Inward No")) {
						lSBQuery.append(" AND inw.inwardNo= :lStrSearchValue");
					}
					if (lStrSearchby.equals("Inward Date")) {

						lDtInwardDate = simpleDateFormat.parse(lStrSearchValue);
						lSBQuery.append(" AND inw.inwardDate= :lStrSearchValue");
					}
					if (lStrSearchby.equals("Inward Type")) {
						lSBQuery.append(" AND inw.caseType= :lStrSearchValue ");
					}
					if (lStrSearchby.equals("Retirement Date")) {

						lDtRetirementDate = simpleDateFormat.parse(lStrSearchValue);
						lSBQuery.append(" AND dtl.retirementDate= :lStrSearchValue ");
					}
					if (lStrSearchby.equals("Pension Type")) {
						lSBQuery.append(" AND inw.pensionType= :lStrSearchValue ");
					}
					if (lStrSearchby.equals("Bank Branch Name")) {
						lSBQuery.append(" AND dtl.bankName= :lStrSearchValue ");
						lSBQuery.append(" AND dtl.bankBranchName= :lStrBranchName ");
					}
					if (lStrSearchby.equals("PPO No")) {
						lSBQuery.append(" AND inw.ppoNo= :lStrSearchValue ");
					}
					
					if (lStrSearchby.equals("Sevaarth ID")) {
						lSBQuery.append(" AND inw.sevaarthId= :lStrSearchValue ");
					}
					
					if (lStrSearchby.equals("Name of Pensioner")) {
						lSBQuery.append(" AND dtl.pnsnrName= :lStrSearchValue ");
					}
				}

			} else {
				if (lDtFrmDate != null && lDtToDate != null) {
					lSBQuery.append(" AND inw.inwardDate BETWEEN :lDtFrmDate AND :lDtToDate ");
				}
				if (lStrSevaarthId != null) {
					lSBQuery.append(" AND inw.sevaarthId = :lStrSevaarthId");
				}
				if (lStrInwardNo != null) {
					lSBQuery.append(" AND inw.inwardNo = :lStrInwardNo");
				}
				if (lStrPPONo != null) {
					lSBQuery.append(" AND inw.ppoNo= :lStrPPONo");
				}
				if (lDtRetiredDate != null) {
					lSBQuery.append(" AND dtl.retirementDate = :lDtRetiredDate");
				}
				if (lStrName != null && !lStrName.equals("")) {
					lSBQuery.append(" AND dtl.pnsnrName like :lStrName");
				}
				if (lLngDeparmentTypeId != null) {
					lSBQuery.append(" AND dtl.departmentId = :lLngDeparmentTypeId");
				}
				if (lStrPensionTypeId != null) {
					lSBQuery.append(" AND inw.pensionType = :lLngPensionTypeId");
				}

			}

			lHibQry = ghibSession.createQuery(lSBQuery.toString());
			if (!lStrSearchby.equals("-1") && lStrSearchValue != null) {
				if (lStrSearchby.equals("Inward Date")) {
					lHibQry.setParameter("lStrSearchValue", lDtInwardDate);
				} else if (lStrSearchby.equals("Retirement Date")) {
					lHibQry.setParameter("lStrSearchValue", lDtRetirementDate);
				} else {
					lHibQry.setParameter("lStrSearchValue", lStrSearchValue);
					if (lStrSearchby.equals("Bank Branch Name")) {
						lHibQry.setParameter("lStrBranchName", lStrBranchName);
					}
				}

			} else {
				if ((lDtFrmDate != null && lDtToDate != null) || lStrSevaarthId != null || lStrPPONo != null || lStrInwardNo != null || lDtRetiredDate != null || lStrName != null
						|| lLngDeparmentTypeId != null || lStrPensionTypeId != null) {
					if (lDtFrmDate != null && lDtToDate != null) {
						lHibQry.setParameter("lDtFrmDate", lDtFrmDate);
						lHibQry.setParameter("lDtToDate", lDtToDate);
					}
					if (lStrSevaarthId != null) {
						lHibQry.setParameter("lStrSevaarthId", lStrSevaarthId);
					}
					if (lStrInwardNo != null) {
						lHibQry.setParameter("lStrInwardNo", lStrInwardNo);
					}
					if (lStrPPONo != null) {
						lHibQry.setParameter("lStrPPONo", lStrPPONo);
					}
					if (lDtRetiredDate != null) {
						lHibQry.setParameter("lDtRetiredDate", lDtRetiredDate);
					}
					if (lStrName != null) {
						lHibQry.setParameter("lStrName", lStrName + "%");
					}
					if (lLngDeparmentTypeId != null) {
						lHibQry.setLong("lLngDeparmentTypeId", lLngDeparmentTypeId);
					}
					if (lStrPensionTypeId != null) {
						lHibQry.setParameter("lLngPensionTypeId", lStrPensionTypeId);
					}
				}
			}
			if ((lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("F") || lStrDraftFlag.equalsIgnoreCase("R") || lStrDraftFlag.equalsIgnoreCase("A"))) {
				//updated by vishnu-start for separate rejected and draft cases
				//if (lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("R")) {
				if (lStrDraftFlag.equalsIgnoreCase("D")) {	
				//lHibQry.setParameter("createdPostId", Long.valueOf(gStrPostId));					
					List<String> lLstcaseStatus = new ArrayList<String>();
					lLstcaseStatus.add("D");
					//lLstcaseStatus.add("R");
					//lLstcaseStatus.add("A");
					lHibQry.setParameterList("lStrDraftFlag", lLstcaseStatus);
				}
			else if( lStrDraftFlag.equalsIgnoreCase("R")){
				List<String> lLstcaseStatus = new ArrayList<String>();
				lLstcaseStatus.add("R");
				lHibQry.setParameterList("lStrDraftFlag", lLstcaseStatus);
			}
				else {
					lHibQry.setParameter("lStrDraftFlag", lStrDraftFlag);
				}
				if ((lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("F") || lStrDraftFlag.equalsIgnoreCase("R") || lStrDraftFlag.equalsIgnoreCase("A"))) {
					//if (lStrDraftFlag.equalsIgnoreCase("D") || lStrDraftFlag.equalsIgnoreCase("R")) {
						if (lStrDraftFlag.equalsIgnoreCase("D")) {
						//lHibQry.setParameter("createdPostId", Long.valueOf(gStrPostId));					
						List<String> lLstcaseStatus = new ArrayList<String>();
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.DRAFTSTATUSID"));
						/*lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYDDOSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.MOVEFORDDOMISTAKESTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.MOVEFORAGQUERYSTATUSID"));*/					
						lHibQry.setParameterList("caseStatus", lLstcaseStatus);
					} else {

						if (lStrDraftFlag.equalsIgnoreCase("F")) {
							if ("V".equalsIgnoreCase(lStrCasesFrom)) {
								lHibQry.setParameter("caseStatus", gObjRsrcBndle.getString("PPROC.FWDBYDEOSTATUSID"));
							}
							if ("D".equalsIgnoreCase(lStrCasesFrom)) {
								lHibQry.setParameter("caseStatus", gObjRsrcBndle.getString("PPROC.FWDBYDEOSTATUSID"));
							}
						} else if (lStrDraftFlag.equalsIgnoreCase("R")) {
							List<String> lLstcaseStatus = new ArrayList<String>();
							lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYDDOSTATUSID"));
							lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.RJCTBYVERIFIERSTATUSID"));
							lHibQry.setParameterList("caseStatus", lLstcaseStatus);
						}
						//updated by vishnu-end
					if (lStrDraftFlag.equalsIgnoreCase("A")) {
						List<String> lLstcaseStatus = new ArrayList<String>();
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.APPROVEDBYDDOSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.SENDTOAGSTATUSID"));
						lLstcaseStatus.add(gObjRsrcBndle.getString("PPROC.APPROVEDBYAGSTATUSID"));
						lHibQry.setParameterList("caseStatus", lLstcaseStatus);
					}
					//lHibQry.setParameter("lStrDraftFlag", lStrDraftFlag);
				}
				
			}
			lHibQry.setLong("gLngLangId", gLngLangId);
			List lLstCount = lHibQry.list();
			
			if (lLstCount != null && lLstCount.size() > 0) {
				lIntCount = Integer.parseInt(lLstCount.get(0).toString());
			} else {
				lIntCount = 0;
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
		}

		return lIntCount;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#getPnsnrDtlsVO
	 * (com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcInwardPension)
	 */
	public TrnPnsnProcPnsnrDtls getPnsnrDtlsVO(Long lLngInwardPensionId) throws Exception {

		Criteria objCrt = null;
		List lLstResult = new ArrayList();
		TrnPnsnProcPnsnrDtls lObjTrnPnsnProcPnsnrDtls = new TrnPnsnProcPnsnrDtls();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnProcPnsnrDtls.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstResult = objCrt.list();
			if (!lLstResult.isEmpty()) {
				lObjTrnPnsnProcPnsnrDtls = (TrnPnsnProcPnsnrDtls) lLstResult.get(0);
			}
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPnsnrDtlsVO() : Error is :" + e, e);

		}
		return lObjTrnPnsnProcPnsnrDtls;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#
	 * getPensionCaseEventDtls
	 * (com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcInwardPension)
	 */
	public List<TrnPnsnprocEventdtls> getPensionCaseEventDtls(Long lLngInwardPensionId) throws Exception {
		Criteria objCrt = null;
		List<TrnPnsnprocEventdtls> lLstTrnPnsnprocEventdtls = new ArrayList<TrnPnsnprocEventdtls>();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnprocEventdtls.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstTrnPnsnprocEventdtls = objCrt.list();
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPensionCaseEventDtls() : Error is :" + e, e);

		}
		return lLstTrnPnsnprocEventdtls;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#
	 * getPensionCaseSrvcBrkDtls
	 * (com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcInwardPension)
	 */
	public List<TrnPnsnprocPnsnrservcbreak> getPensionCaseSrvcBrkDtls(Long lLngInwardPensionId) throws Exception {
		Criteria objCrt = null;
		List<TrnPnsnprocPnsnrservcbreak> lLstTrnPnsnprocPnsnrservcbreak = new ArrayList<TrnPnsnprocPnsnrservcbreak>();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnprocPnsnrservcbreak.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstTrnPnsnprocPnsnrservcbreak = objCrt.list();
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPensionCaseSrvcBrkDtls() : Error is :" + e, e);

		}
		return lLstTrnPnsnprocPnsnrservcbreak;
	}
	
	
	public List<TrnPnsnProcQlyServ> getPensionCaseQlyServDtls(Long lLngInwardPensionId) throws Exception {
		Criteria objCrt = null;
		List<TrnPnsnProcQlyServ> lLstTrnPnsnProcQlyServ = new ArrayList<TrnPnsnProcQlyServ>();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnProcQlyServ.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstTrnPnsnProcQlyServ = objCrt.list();
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPensionCaseQlyServDtls() : Error is :" + e, e);

		}
		return lLstTrnPnsnProcQlyServ;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#
	 * getPensionCaseAdvnceBalDtls
	 * (com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcInwardPension)
	 */
	public List<TrnPnsnProcAdvnceBal> getPensionCaseAdvnceBalDtls(Long lLngInwardPensionId) throws Exception {

		Criteria objCrt = null;
		List<TrnPnsnProcAdvnceBal> lLstTrnPnsnProcAdvnceBal = new ArrayList<TrnPnsnProcAdvnceBal>();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnProcAdvnceBal.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstTrnPnsnProcAdvnceBal = objCrt.list();
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPensionCaseAdvnceBalDtls() : Error is :" + e, e);

		}
		return lLstTrnPnsnProcAdvnceBal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#
	 * getPensionCaseAssesdDueDtls
	 * (com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcInwardPension)
	 */
	public List<TrnPnsnProcAssesdDues> getPensionCaseAssesdDueDtls(Long lLngInwardPensionId) throws Exception {

		Criteria objCrt = null;
		List<TrnPnsnProcAssesdDues> lLstTrnPnsnProcAssesdDues = new ArrayList<TrnPnsnProcAssesdDues>();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnProcAssesdDues.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstTrnPnsnProcAssesdDues = objCrt.list();
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPensionCaseAssesdDueDtls() : Error is :" + e, e);

		}
		return lLstTrnPnsnProcAssesdDues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#getPnsnrPayVO
	 * (com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcInwardPension)
	 */
	public TrnPnsnprocPnsnrpay getPnsnrPayVO(Long lLngInwardPensionId) throws Exception {

		Criteria objCrt = null;
		List lLstResult = new ArrayList();
		TrnPnsnprocPnsnrpay lObjTrnPnsnprocPnsnrpay = new TrnPnsnprocPnsnrpay();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnprocPnsnrpay.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstResult = objCrt.list();
			if (!lLstResult.isEmpty()) {
				lObjTrnPnsnprocPnsnrpay = (TrnPnsnprocPnsnrpay) lLstResult.get(0);
			}
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPnsnrPayVO() : Error is :" + e, e);
			throw (e);

		}
		return lObjTrnPnsnprocPnsnrpay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#getPnsnCalcVO
	 * (com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcInwardPension)
	 */
	public TrnPnsnProcPnsnCalc getPnsnCalcVO(Long lLngInwardPensionId) throws Exception {

		Criteria objCrt = null;
		List lLstResult = new ArrayList();
		TrnPnsnProcPnsnCalc lObjTrnPnsnProcPnsnCalc = new TrnPnsnProcPnsnCalc();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnProcPnsnCalc.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstResult = objCrt.list();
			if (!lLstResult.isEmpty()) {
				lObjTrnPnsnProcPnsnCalc = (TrnPnsnProcPnsnCalc) lLstResult.get(0);
			}
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPnsnCalcVO() : Error is :" + e, e);

		}
		return lObjTrnPnsnProcPnsnCalc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#getPnsnrRecoveryVO
	 * (com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcInwardPension)
	 */
	public TrnPnsnProcRecovery getPnsnrRecoveryVO(Long lLngInwardPensionId) throws Exception {

		Criteria objCrt = null;
		List lLstResult = new ArrayList();
		TrnPnsnProcRecovery lObjTrnPnsnProcRecovery = new TrnPnsnProcRecovery();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnProcRecovery.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstResult = objCrt.list();
			if (!lLstResult.isEmpty()) {
				lObjTrnPnsnProcRecovery = (TrnPnsnProcRecovery) lLstResult.get(0);
			}
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPnsnrRecoveryVO() : Error is :" + e, e);

		}
		return lObjTrnPnsnProcRecovery;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#getPnsnrCheklistVO
	 * (com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcInwardPension)
	 */
	public List<TrnPnsnProcCheckList> getPnsnrCheklistVO(Long lLngInwardPensionId) throws Exception {

		Criteria objCrt = null;
		List<TrnPnsnProcCheckList> lLstTrnPnsnProcCheckList = new ArrayList<TrnPnsnProcCheckList>();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnProcCheckList.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstTrnPnsnProcCheckList = objCrt.list();

		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPnsnrCheklistVO() : Error is :" + e, e);
		}
		return lLstTrnPnsnProcCheckList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#
	 * getPensionCaseAvgPayCalcDtls
	 * (com.tcs.sgv.pensionproc.valueobject.TrnPnsnProcInwardPension)
	 */
	public List<TrnPnsnprocAvgPayCalc> getPensionCaseAvgPayCalcDtls(Long lLngInwardPensionId) throws Exception {
		Criteria objCrt = null;
		List<TrnPnsnprocAvgPayCalc> lLstTrnPnsnprocAvgPayCalcVO = new ArrayList<TrnPnsnprocAvgPayCalc>();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnprocAvgPayCalc.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			objCrt.addOrder(Order.asc("fromDate"));
			lLstTrnPnsnprocAvgPayCalcVO = objCrt.list();

		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPensionCaseAvgPayCalcDtls() : Error is :" + e, e);

		}
		return lLstTrnPnsnprocAvgPayCalcVO;

	}

	public String getLowerLevelForReturn(Long PostId, Long lLngHierarchyRefId) {
		String lStrToLevel = "";
		List lLstToLevel = null;
		StringBuilder lSBQuery = null;
		Query hqlQuery = null;
		List<Long> lLstLevelStatusIDs = new ArrayList<Long>();
		try {

			lLstLevelStatusIDs.add(Long.valueOf(gObjRsrcBndle.getString("PPROC.DEOSTATUSID")));
			lLstLevelStatusIDs.add(Long.valueOf(gObjRsrcBndle.getString("PPROC.VERIFIERSTATUSID")));
			lLstLevelStatusIDs.add(Long.valueOf(gObjRsrcBndle.getString("PPROC.HOOSTATUSID")));
			lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT st.levelCode FROM  RltLevelStatus st WHERE st.levelStatusId IN (:levelStatusId)");
			lSBQuery
					.append(" AND  st.levelCode < (SELECT mpg.levelId FROM WfHierachyPostMpg mpg WHERE mpg.wfOrgPostMpgMst.postId =:PostId  AND mpg.activateFlag=1 AND mpg.hierachyRefId = :hierachyRefId)  ORDER BY st.levelCode DESC ");
			hqlQuery = ghibSession.createQuery(lSBQuery.toString());

			hqlQuery.setLong("PostId", PostId);
			hqlQuery.setLong("hierachyRefId", lLngHierarchyRefId);
			hqlQuery.setParameterList("levelStatusId", lLstLevelStatusIDs);
			lLstToLevel = hqlQuery.list();

			if (lLstToLevel.size() > 0) {
				lStrToLevel = (lLstToLevel.get(0).toString());
			}

		} catch (Exception e) {

			gLogger.error("Error is :" + e, e);

		}
		return lStrToLevel;
	}

	public List getLowerLevelUserList(String lStrToLevel, Long lLngHierRefId, Long lLngLangId) {
		List lLstLowerLevelUserList = null;
		StringBuilder lSBQuery = null;
		Query hqlQuery = null;
		List lLstUserList = new ArrayList();
		try {

			lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT OUM.empFname,OUM.empMname,OUM.empLname,RLS.levelCode,RLS.levelDesc,WPM.wfOrgPostMpgMst.postId,OUM.empPrefix ");
			lSBQuery.append(" FROM RltLevelStatus RLS,WfHierachyPostMpg WPM , OrgEmpMst OUM,OrgUserpostRlt OUPR ");
			lSBQuery.append(" WHERE RLS.category = :CATEGORY ");
			lSBQuery.append(" AND WPM.levelId = RLS.levelCode ");
			lSBQuery.append(" AND WPM.hierachyRefId =  :lLngHierRefId ");
			lSBQuery.append(" AND OUPR.orgPostMstByPostId.postId = WPM.wfOrgPostMpgMst.postId ");
			lSBQuery.append(" AND OUM.orgUserMst.userId = OUPR.orgUserMst.userId ");
			lSBQuery.append(" AND WPM.levelId = :LEVEL_CODE AND OUM.cmnLanguageMst.langId =:LANG_ID AND OUPR.activateFlag=1 AND WPM.activateFlag=1 AND OUM.activateFlag=1 ");
			lSBQuery.append(" GROUP BY WPM.wfOrgPostMpgMst.postId,OUM.empPrefix,OUM.empFname,OUM.empMname,OUM.empLname,RLS.levelCode,RLS.levelDesc");

			hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setParameter("LANG_ID", lLngLangId);
			hqlQuery.setParameter("lLngHierRefId", lLngHierRefId);
			hqlQuery.setParameter("LEVEL_CODE", Integer.parseInt(lStrToLevel));
			hqlQuery.setParameter("CATEGORY", gObjRsrcBndle.getString("PPROC.CATEGORY"));
			lLstLowerLevelUserList = hqlQuery.list();

			Iterator it = lLstLowerLevelUserList.iterator();

			int lIntLoopJ = 0;
			Object[] tuple = null;
			String[] result = null;

			while (it.hasNext()) {
				tuple = (Object[]) it.next();
				result = new String[5];
				String middleName = tuple[1] != null ? tuple[1].toString() : "";
				String name = " " + tuple[0] + " " + middleName + " " + tuple[2] + " [" + tuple[4] + "]";
				result[0] = name;
				result[1] = tuple[5].toString();
				result[2] = lStrToLevel;
				result[3] = tuple[4].toString();

				lLstUserList.add(result);

				lIntLoopJ++;
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
		}
		return lLstUserList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#
	 * dsplyPensionCaseStatus(java.util.Map)
	 */
	public List displayPensionCaseStatus(List<String> lLstStatus, Map displayTag,Long lLngDdoCode) {
		List lLstPnsnCaseStatus = null;
		Query lHibQry = null;
		StringBuilder lSBQuery = new StringBuilder();
		Session session = getSession();
		try {
			String[] columnValues = new String[] {"inw.inwardNo", "dtl.pnsnrName","inw.pensionType", "dtl.retirementDate","inw.caseStatus","inw.caseType" };
			lSBQuery.append(" SELECT inw.inwardPensionId,inw.inwardNo,dtl.pnsnrName,inw.pensionType,dtl.retirementDate,inw.caseStatus,inw.caseType ");
			lSBQuery.append(" FROM TrnPnsnProcInwardPension inw , TrnPnsnProcPnsnrDtls dtl ");
			lSBQuery.append(" WHERE inw.inwardPensionId=dtl.inwardPensionId ");
			//lSBQuery.append(" AND inw.caseStatus IN (:caseStatus) ");
			lSBQuery.append(" AND (inw.ddoCode = :ddoCode or (inw.penDdoCode = :ddoCode and inw.pensionFlag is not null)) ");

			String orderString = (displayTag.containsKey(Constants.KEY_SORT_ORDER) ? (String) displayTag.get(Constants.KEY_SORT_ORDER) : "desc");

			Integer orderbypara = null;

			if (displayTag.containsKey(Constants.KEY_SORT_PARA)) {
				orderbypara = (Integer) displayTag.get(Constants.KEY_SORT_PARA);
				lSBQuery.append(" ORDER BY "+columnValues[orderbypara.intValue()] + " " + orderString);
			} else {
				lSBQuery.append(" ORDER BY inw.inwardPensionId");
			}

			lHibQry = session.createQuery(lSBQuery.toString());
			lHibQry.setParameter("ddoCode", lLngDdoCode);			
			//lHibQry.setParameterList("caseStatus", lLstStatus);

			Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO) ? (Integer) displayTag.get(Constants.KEY_PAGE_NO) : 1);
			lHibQry.setFirstResult((pageNo.intValue() - 1) * Constants.PDWL_PAGE_SIZE);
			lHibQry.setMaxResults(Constants.PDWL_PAGE_SIZE);

			lLstPnsnCaseStatus = lHibQry.list();

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);

		}
		return lLstPnsnCaseStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#
	 * displayPensionCaseStatusCount(java.util.List)
	 */

	public Integer displayPensionCaseStatusCount(List<String> lLstStatus, Map displayTag,Long lLngDdoCode) {
		Integer lIntCount = null;
		Query lHibQry = null;
		StringBuffer lSBQuery = null;
		try {
			lSBQuery = new StringBuffer();
			lSBQuery.append(" SELECT COUNT(*) ");
			lSBQuery.append(" FROM TrnPnsnProcInwardPension inw , TrnPnsnProcPnsnrDtls dtl ");
			lSBQuery.append(" WHERE inw.inwardPensionId=dtl.inwardPensionId ");
			//lSBQuery.append(" AND inw.caseStatus IN (:caseStatus) ");
			lSBQuery.append(" AND (inw.ddoCode = :ddoCode or (inw.penDdoCode = :ddoCode and inw.pensionFlag is not null)) ");
			lHibQry = ghibSession.createQuery(lSBQuery.toString());
			lHibQry.setParameter("ddoCode", lLngDdoCode);
			//lHibQry.setParameterList("caseStatus", lLstStatus);
			if (lHibQry.list() != null && lHibQry.list().size() > 0) {
				lIntCount = Integer.parseInt(lHibQry.list().get(0).toString());
			} else {
				lIntCount = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
		}

		return lIntCount;

	}

	public String getRoleByPost(Long lLngPostId) {
		List lLstResultList = null;
		Query hqlQuery = null;
		String lStrToRole = "";
		StringBuilder lStrQuery = new StringBuilder();
		Session session = getSession();
		try {
			lStrQuery.append(" SELECT acl.aclRoleMst.roleId from AclPostroleRlt acl where acl.orgPostMst.postId=:lLngPostId ");

			hqlQuery = session.createQuery(lStrQuery.toString());
			hqlQuery.setParameter("lLngPostId", lLngPostId);
			lLstResultList = hqlQuery.list();

			gLogger.info("resultList Size FOR LEVEL : " + lLstResultList.size());
			if (lLstResultList.size() > 0) {
				for (int i = 0; i < lLstResultList.size(); i++) {
					if (lStrToRole == null || lStrToRole.equals("")) {
						lStrToRole = (lLstResultList.get(i).toString());
					} else {
						lStrToRole = lStrToRole + "," + (lLstResultList.get(i).toString());
					}
				}
			} else {
				lStrToRole = "";
			}
		} catch (Exception e) {

			gLogger.error("Error is :" + e, e);

		}
		gLogger.info("getRoleByPost in Role Ids : " + lStrToRole);
		gLogger.info("getRoleByPost end...................");

		return lStrToRole;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#wfIsExistsOrNot
	 * (java.lang.Long)
	 */

	public Boolean isWfExists(Long lLngInwardPensionId) {

		Integer lIntCnt = null;
		Query hqlQuery = null;
		StringBuilder lStrQuery = new StringBuilder();
		Session session = getSession();
		try {
			lStrQuery.append(" SELECT COUNT(*) FROM TrnPnsnProcInwardPension inw,WfJobMst job ");
			lStrQuery.append(" WHERE inw.inwardPensionId=job.jobRefId ");
			lStrQuery.append(" AND job.wfDocMst.docId=:docId AND inw.inwardPensionId=:inwardPensionId");

			hqlQuery = session.createQuery(lStrQuery.toString());
			hqlQuery.setParameter("docId", Long.valueOf(gObjRsrcBndle.getString("PPROC.DOCID")));
			hqlQuery.setParameter("inwardPensionId", lLngInwardPensionId);
			if (hqlQuery.list().size() > 0) {
				lIntCnt = Integer.valueOf(hqlQuery.list().get(0).toString());
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);

		}
		if (lIntCnt > 0) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAO#getDDOCode(java
	 * .lang.String)
	 */

	public String getLocCodeFromDDO(String lStrLocationCode) {
		String lStrLocCode = null;
		Query hqlQuery = null;
		StringBuilder lStrQuery = new StringBuilder();
		Session session = getSession();
		try {
			lStrQuery.append(" SELECT rlt.locationCode FROM RltDdoOrg rlt,OrgDdoMst mst WHERE mst.ddoCode = rlt.ddoCode AND mst.locationCode =:locationCode");
			hqlQuery = session.createQuery(lStrQuery.toString());
			hqlQuery.setParameter("locationCode", lStrLocationCode.trim());
			if (hqlQuery.list().size() > 0) {
				lStrLocCode = hqlQuery.list().get(0).toString();
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);

		}
		return lStrLocCode;
	}

	public BigDecimal getCvpRate(BigDecimal lBDAge, String lStrPayCmsn) throws Exception {
		BigDecimal lBDCvpRate = BigDecimal.ZERO;
		Query hqlQuery = null;
		StringBuilder lStrQuery = new StringBuilder();
		Session session = getSession();
		try {
			lStrQuery.append(" SELECT cvpRate FROM MstPensionCvpRate WHERE age=:age AND  payCommission =:payCommission ");
			hqlQuery = session.createQuery(lStrQuery.toString());
			hqlQuery.setParameter("age", lBDAge);
			hqlQuery.setParameter("payCommission", lStrPayCmsn.trim());
			if (hqlQuery.list().size() > 0) {
				lBDCvpRate = new BigDecimal(hqlQuery.list().get(0).toString());
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);

		}
		return lBDCvpRate;

	}

	public List<ComboValuesVO> getDesignationForPensionCase(String lStrDesignation,Long lLngLangId) throws Exception {
		List<ComboValuesVO> lLstDesignation = new ArrayList<ComboValuesVO>();
		ComboValuesVO lObjComboValueVO = null;
		List lLstResult = new ArrayList();
		String lStrDesig = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" Select dsgn.dsgnName ");
			lSBQuery.append(" FROM OrgDesignationMst dsgn ");
			lSBQuery.append(" WHERE dsgn.cmnLanguageMst.langId =:langId AND dsgn.dsgnName LIKE '" + lStrDesignation + "%' order by dsgn.dsgnName,dsgn.dsgnId");

			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setParameter("langId", lLngLangId);
			
			lLstResult = hqlQuery.list();

			if (lLstResult != null && !lLstResult.isEmpty()) {
				Iterator it = lLstResult.iterator();
				while (it.hasNext()) {
					lObjComboValueVO = new ComboValuesVO();
					lStrDesig = (String) it.next();
					lObjComboValueVO.setId(lStrDesig);
					lObjComboValueVO.setDesc(lStrDesig);
					lLstDesignation.add(lObjComboValueVO);
				}
			}
		} catch (Exception e) {
			logger.error("Error is :" + e, e);
			throw e;
		}
		return lLstDesignation;

	}

	public List<TrnPnsnprocProvisionalPaid> getPnsnprocProvisionalPaidDtls(Long lLngInwardPensionId) throws Exception {
		Criteria objCrt = null;
		List<TrnPnsnprocProvisionalPaid> lLstPnsnprocProvisionalPaid = new ArrayList<TrnPnsnprocProvisionalPaid>();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnprocProvisionalPaid.class);
			objCrt.add(Restrictions.eq("inwardPensionId", lLngInwardPensionId));
			lLstPnsnprocProvisionalPaid = objCrt.list();
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPnsnprocProvisionalPaidDtls() : Error is :" + e, e);

		}
		return lLstPnsnprocProvisionalPaid;

	}
	
	public List<TrnPnsnprocForeignServ> getForeignServDtls(Long lLngInwardPensionId) throws Exception {
		Criteria objCrt = null;
		List<TrnPnsnprocForeignServ> lLstForeignServ = new ArrayList<TrnPnsnprocForeignServ>();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnprocForeignServ.class);
			objCrt.add(Restrictions.eq("inwardPensionId", lLngInwardPensionId));
			lLstForeignServ = objCrt.list();
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getForeignServDtls() : Error is :" + e, e);

		}
		return lLstForeignServ;

	}


	public List<MstEmp> getEmpBasicDtls(String lStrSevaarthId,String lStrDdoCode) throws Exception {

		
		List<MstEmp> lLstEmpBasicDtls = new ArrayList<MstEmp>();
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append("from MstEmp where sevarthId = :sevarthId ");
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());
			lHibQry.setParameter("sevarthId", lStrSevaarthId);
			lLstEmpBasicDtls = lHibQry.list();			
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getEmpBasicDtls() : Error is :" + e, e);

		}
		
		return lLstEmpBasicDtls;
	}
	public String getDdoCodeForDDOAsst(Long lLngAsstPostId) {

		String lStrDdoCode = null;
		StringBuilder lSBQuery = new StringBuilder();
	/*	lSBQuery.append(" SELECT OD.ddoCode");
		lSBQuery.append(" FROM  OrgDdoMst OD");
		lSBQuery.append(" WHERE OD.postId = :asstPostId ");*/
		lSBQuery.append(" SELECT OD.ddoCode");
		lSBQuery.append(" FROM RltDdoAsst RD, OrgDdoMst OD");
		lSBQuery.append(" WHERE OD.postId = RD.ddoPostId AND RD.asstPostId = :asstPostId ");
		
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("asstPostId", lLngAsstPostId);

		List lLstCodeList = lQuery.list();

		if(lLstCodeList != null)
		{
			if(lLstCodeList.size() != 0)
			{
				if(lLstCodeList.get(0) != null)
				{
					lStrDdoCode = lLstCodeList.get(0).toString();
				}
			}
		}

		return lStrDdoCode;
	}

	
	public String  getDdoCode(Long gLngPostId, String gStrLocationCode){
		
		String lStrDdoCode = null;
		try {
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(" SELECT DDO_CODE FROM ORG_DDO_MST where LOCATION_CODE= :gStrLocationCode and POST_ID=:gLngPostId ");
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		lQuery.setParameter("gStrLocationCode", gStrLocationCode);
		lQuery.setParameter("gLngPostId", gLngPostId);
		List lLstDdoCodeList = lQuery.list();
		if(lLstDdoCodeList.size()!=0){
			lStrDdoCode=lLstDdoCodeList.get(0).toString();
		}
		
		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);

		}
		return lStrDdoCode;
	}
	
	
	public List getEmpGpfOrDcpsAccNo(String lStrSevaarthId) throws Exception {
	
		List lLstEmpGpfOrDcpsAccNo = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT CASE WHEN e.DCPS_OR_GPF = 'Y' THEN e.DCPS_ID  ELSE h.PF_SERIES || '/' || h.GPF_ACC_NO  END AS AccNo,e.DCPS_OR_GPF ");
			lSBQuery.append(" FROM MST_DCPS_EMP e, HR_PAY_GPF_DETAILS h,ORG_EMP_MST o,ORG_USER_MST u where ");
			lSBQuery.append(" e.SEVARTH_ID = :sevarthId ");
			lSBQuery.append(" and e.ORG_EMP_MST_ID = o.EMP_ID and o.USER_ID = u.USER_ID and u.USER_ID = h.USER_ID");
			Query lHibQry = ghibSession.createSQLQuery(lSBQuery.toString());
			lHibQry.setParameter("sevarthId", lStrSevaarthId);
			lLstEmpGpfOrDcpsAccNo = lHibQry.list();			
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getEmpGpfOrDcpsAccNo() : Error is :" + e, e);

		}
		
		return lLstEmpGpfOrDcpsAccNo;
	}

	
	public String getDeptName(Long lLngLocId) throws Exception {
		String lStrDeptName = "";
		List lLstDeptName = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT locName FROM CmnLocationMst WHERE locId = :LocId");			
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("LocId", lLngLocId);
			lLstDeptName = lQuery.list();
			if(!lLstDeptName.isEmpty())
			{
				lStrDeptName = lLstDeptName.get(0).toString();
			}			

		} catch (Exception e) {
			
			gLogger.error("Error is :" + e, e);

		}
		return lStrDeptName;
	}

	
	public List getPayScaleDescFromScaleId(Long lLngScaleId) throws Exception {
		List lLstEmpGpfOrDcpsAccNo = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT scaleDesc,hrPayCommissionMst.id FROM HrEisScaleMst WHERE scaleId = :scaleId");
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());
			lHibQry.setParameter("scaleId", lLngScaleId);
			lLstEmpGpfOrDcpsAccNo = lHibQry.list();			
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getPayScaleDescFromScaleId() : Error is :" + e, e);

		}
		
		return lLstEmpGpfOrDcpsAccNo;
	}

	
	public BigInteger getProvisionalPensionSum(Long lLngInwardId) throws Exception {
		
		BigInteger lBIProPenSum = BigInteger.ZERO;
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT NVL(SUM(PRVSNL_PNSNPAID_PRO_AMT_PAID),0) FROM TRN_PNSNPROC_PROVISIONAL_PAID where INWARD_PENSION_ID = :InwardId");
			Query lHibQry = ghibSession.createSQLQuery(lSBQuery.toString());
			lHibQry.setParameter("InwardId", lLngInwardId);
			lBIProPenSum = (BigInteger) lHibQry.list().get(0);			
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getPayScaleDescFromScaleId() : Error is :" + e, e);

		}
		
		return lBIProPenSum;
	}
	
	public String getTresuryNameFormDDOCode(String lStrDdoCode) throws Exception {
		String lStrTresuryName = "";
		List lLstTresuryName = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT l.locName FROM CmnLocationMst l,RltDdoOrg d WHERE d.ddoCode = :DdoCode and d.locationCode = l.locId");
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());
			lHibQry.setParameter("DdoCode", lStrDdoCode);
			lLstTresuryName = lHibQry.list();	
			
			if(!lLstTresuryName.isEmpty())
			{
				lStrTresuryName = lLstTresuryName.get(0).toString();
			}	
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getPayScaleDescFromScaleId() : Error is :" + e, e);

		}
		
		return lStrTresuryName;
	}

	
	public String getLookupNameFromLookupId(Long lLngLookupId) throws Exception {
		String lStrLookupName = "";
		List lLstLookupName = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT lookupName FROM CmnLookupMst WHERE lookupId = :lookupId ");
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());
			lHibQry.setParameter("lookupId", lLngLookupId);
			lLstLookupName = lHibQry.list();	
			
			if(!lLstLookupName.isEmpty())
			{
				lStrLookupName = lLstLookupName.get(0).toString();
			}	
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getLookupNameFromLookupId() : Error is :" + e, e);

		}
		
		return lStrLookupName;
	}

	
	public String getBankNameFromBankCode(String lStrBankId) throws Exception {
		String lStrBankName = "";
		List lLstBankName = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT bankName FROM MstBank WHERE bankCode = :bankCode ");
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());
			lHibQry.setParameter("bankCode", lStrBankId);
			lLstBankName = lHibQry.list();	
			
			if(!lLstBankName.isEmpty())
			{
				lStrBankName = lLstBankName.get(0).toString();
			}	
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getBankNameFromBankCode() : Error is :" + e, e);

		}
		
		return lStrBankName;
	}


	public String getBranchNameFromBrachCode(String lStrBranchId) throws Exception {
		String lStrBranchName = "";
		List lLstBranchName = new ArrayList();
		Long lLngBranchCode = 0L;
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT branchName FROM RltBankBranch WHERE branchCode = :branchCode ");
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());
			if(lStrBranchId != "" && lLngBranchCode != null)
				lLngBranchCode = Long.parseLong(lStrBranchId);
			
			lHibQry.setParameter("branchCode", lLngBranchCode);
			lLstBranchName = lHibQry.list();	
			
			if(!lLstBranchName.isEmpty())
			{
				lStrBranchName = lLstBranchName.get(0).toString();
			}	
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getBranchNameFromBrachCode() : Error is :" + e, e);

		}
		
		return lStrBranchName;
	}


	public Long getApprovedCaseForRevision(String lStrSevaarthId, Long lLngDdoCode) throws Exception {	
		
		List<Long> lLstApprovedCaseList = new ArrayList<Long>();
		Long lLngInwardPensionId = 0L;
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT inwardPensionId FROM TrnPnsnProcInwardPension WHERE sevaarthId = :sevaarthId and ddoCode = :ddoCode ");
			lSBQuery.append(" AND caseStatus = 'APRVDBYAG' AND draftFlag = 'A' ");
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());			
			lHibQry.setParameter("sevaarthId", lStrSevaarthId);
			lHibQry.setParameter("ddoCode", lLngDdoCode);
			
			lLstApprovedCaseList = lHibQry.list();	
			
			if(!lLstApprovedCaseList.isEmpty())
			{
				lLngInwardPensionId = lLstApprovedCaseList.get(0);
			}	
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getApprovedCaseForRevision() : Error is :" + e, e);

		}
		
		return lLngInwardPensionId;
	}

	//added by aditya
	public String getAvgPayFlag(String lStrSevaarthId){
		
		String flag="true";
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append("SELECT tpa.INWARD_PENSION_ID FROM TRN_PNSNPROC_AVG_PAY_CALC tpa "); 
			lSBQuery.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tpa.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
			lSBQuery.append(" where tpi.SEVAARTH_ID=:lStrSevaarthId ");
			Query query = ghibSession.createSQLQuery(lSBQuery.toString());			
			query.setParameter("lStrSevaarthId", lStrSevaarthId);
			List Avgpay=query.list();
			if(Avgpay.size()==0){
				flag="false";
			}
			else{
				flag="true";
			}
			
		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);

		}
		return flag;
		}
	//added by aditya
	
	
	public List<MstEmpNmn> getEmpNomineeDtls(Long lLngDcpsEmpId) throws Exception {
		
		List<MstEmpNmn> lLstEmpNomineeDtls = new ArrayList<MstEmpNmn>();		
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append("FROM MstEmpNmn WHERE dcpsEmpId.dcpsEmpId = :lLngDcpsEmpId ");			
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());			
			lHibQry.setParameter("lLngDcpsEmpId", lLngDcpsEmpId);
						
			lLstEmpNomineeDtls = lHibQry.list();	
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getEmpNomineeDtls() : Error is :" + e, e);

		}
		
		return lLstEmpNomineeDtls;
	}

	
	public Long isDdoOrDdoAsst(Long lLngPostId) {	
		
		Long lLngDdoOrDdoAsst = 0L;
		Long isDdoOrDdoAsst = 0L;
		List result=null;
		List lLstCodeList=null;
		String count ="";
		
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append("SELECT COUNT(*) FROM RltDdoAsst WHERE asstPostId = :PostId ");			
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());			
			lHibQry.setParameter("PostId", lLngPostId);
						
			lLngDdoOrDdoAsst = (Long) lHibQry.list().get(0);
			if(lLngDdoOrDdoAsst <= 0){
				lSBQuery = new StringBuilder();	
				lSBQuery.append("SELECT COUNT(*) FROM OrgDdoMst WHERE postId = :PostId ");			
				lHibQry = ghibSession.createQuery(lSBQuery.toString());			
				lHibQry.setParameter("PostId", lLngPostId);
				
				lLngDdoOrDdoAsst = (Long) lHibQry.list().get(0);
				if(lLngDdoOrDdoAsst > 0){
					isDdoOrDdoAsst = 1L;//DDO
					gLogger.info("Inside lLngDdoOrDdoAsst > 0%%%%");
				}
			
				//Added by shraddha for deputation module 
				else {
					StringBuilder sb = new StringBuilder();
					sb.append("SELECT COUNT(*) FROM RLT_PENSION_DDO_ASST WHERE PENSION_ASST_POST_ID = :PostId  ");
					
					Query selectQuery = ghibSession.createSQLQuery(sb.toString());
					logger.info("Query is ********"+sb.toString());
					selectQuery.setParameter("PostId",lLngPostId);
					result = selectQuery.list();
					count=result.get(0).toString();
					lLngDdoOrDdoAsst = Long.parseLong(count);
					if(lLngDdoOrDdoAsst > 0){
						isDdoOrDdoAsst = 3L;//Pen DDO ASST
					
					}	
					
				}
			
			}else{
				isDdoOrDdoAsst = 2L;//DDO Asst
				gLogger.info("Inside lLngDdoOrDdoAsst==2L%%%%");
			}
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : isDdoOrDdoAsst() : Error is :" + e, e);

		}
		
		return isDdoOrDdoAsst;
	}
	public String getDdoCodeForDDO(Long lLngPostId) {

		String lStrDdoCode = null;
		List lLstDdoDtls = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT OD.ddoCode");
			lSBQuery.append(" FROM  OrgDdoMst OD");
			lSBQuery.append(" WHERE OD.postId = :postId ");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("postId", lLngPostId);

			lLstDdoDtls = lQuery.list();

			if(lLstDdoDtls != null)
			{
				if(lLstDdoDtls.size()!= 0)
				{
					if(lLstDdoDtls.get(0) != null)
					{
						lStrDdoCode = lLstDdoDtls.get(0).toString();
					}
				}
			}

		} catch (Exception e) {			
			gLogger.error("Error is :" + e, e);

		}
		return lStrDdoCode;
	}


	public Long getReqPenddingStatus(String lStrSevaarthId, Long lLngDdoCode) throws Exception {
		List<Long> lLstReqPenddingStatus = new ArrayList<Long>();
		Long lLngInwardPensionId = 0L;
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT ddoCode FROM TrnPnsnProcInwardPension WHERE sevaarthId = :sevaarthId  ");
			lSBQuery.append(" AND caseStatus != 'APRVDBYAG' ");
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());			
			lHibQry.setParameter("sevaarthId", lStrSevaarthId);
			//lHibQry.setParameter("ddoCode", lLngDdoCode);
			
			lLstReqPenddingStatus = lHibQry.list();	
			
			if(!lLstReqPenddingStatus.isEmpty())
			{
				lLngInwardPensionId = lLstReqPenddingStatus.get(0);
			}	
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getReqPenddingStatus() : Error is :" + e, e);

		}
		
		return lLngInwardPensionId;
	}

	
	public Long getPnsnRevisionId(String lStrSevaarthId) throws Exception {
		
		List<BigInteger> lLstRevisionId = new ArrayList<BigInteger>();
		Long lLngRevisionId = 0L;
		BigInteger lBIRevisionId = BigInteger.ZERO;
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT REVISION_ID FROM  TRN_PNSNPROC_REVISION where SEVAARTH_ID = :sevaarthId and ACTIVE_FLAG = 'Y' and " );
			lSBQuery.append(" REVISION_NO = (SELECT max(REVISION_NO) FROM TRN_PNSNPROC_REVISION where SEVAARTH_ID = :sevaarthId) ");
			Query lHibQry = ghibSession.createSQLQuery(lSBQuery.toString());			
			lHibQry.setParameter("sevaarthId", lStrSevaarthId);
						
			lLstRevisionId = lHibQry.list();	
			if(!lLstRevisionId.isEmpty())
				lBIRevisionId = lLstRevisionId.get(0);
			
			lLngRevisionId = lBIRevisionId.longValue();
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getReqPenddingStatus() : Error is :" + e, e);

		}
		
		return lLngRevisionId;
	}

	
	public Long getRevisionCount(String lStrSevaarthId) throws Exception {
		
		List<Long> lLstRevisionCount = new ArrayList<Long>();
		Long lLngRevisionCount = 0L;
		StringBuilder lSBQuery = new StringBuilder();	
		try {
			lSBQuery.append(" SELECT count(inwardPensionId) FROM TrnPnsnProcRevision WHERE sevaarthId = :sevaarthId ");
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());			
			lHibQry.setParameter("sevaarthId", lStrSevaarthId);
						
			lLstRevisionCount = lHibQry.list();	
			lLngRevisionCount = lLstRevisionCount.get(0);
		
			lLngRevisionCount++;
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcPnsnrDtlsDAOImpl : getReqPenddingStatus() : Error is :" + e, e);

		}
		
		return lLngRevisionCount;
	}

	
	public List<TrnPnsnprocAuthorityDtls> getAuthorityDtls(Long lLngInwardPensionId) throws Exception {
		Criteria objCrt = null;
		List<TrnPnsnprocAuthorityDtls> lLstTrnPnsnprocAuthorityDtls = new ArrayList<TrnPnsnprocAuthorityDtls>();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnprocAuthorityDtls.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstTrnPnsnprocAuthorityDtls = objCrt.list();
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPensionCaseEventDtls() : Error is :" + e, e);

		}
		return lLstTrnPnsnprocAuthorityDtls;
	}	

	public TrnPnsnprocAgDtls getPnsnAgDtls(Long lLngInwardPensionId) throws Exception {

		Criteria objCrt = null;
		List lLstResult = new ArrayList();
		TrnPnsnprocAgDtls lObjTrnPnsnProcAgDtls = new TrnPnsnprocAgDtls();

		try {

			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(TrnPnsnprocAgDtls.class);
			objCrt.add(Restrictions.like("inwardPensionId", lLngInwardPensionId));
			lLstResult = objCrt.list();
			if (!lLstResult.isEmpty()) {
				lObjTrnPnsnProcAgDtls = (TrnPnsnprocAgDtls) lLstResult.get(0);
			}
		} catch (Exception e) {
			gLogger.error("TrnPnsnProcInwardPensionDAOImpl : getPnsnCalcVO() : Error is :" + e, e);

		}
		return lObjTrnPnsnProcAgDtls;
	}
	public boolean checkIfCommonPool(String sevaarthId)throws Exception {
	
	  boolean flag = false;

	 
	  try {
	    StringBuilder lSBQuery = new StringBuilder();
	    lSBQuery.append("SELECT * FROM MST_DCPS_EMP where SEVARTH_ID =:sevaarthId and DDO_CODE is null and BILLGROUP_ID is null ");

	    Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
	    lQuery.setParameter("sevaarthId", sevaarthId);
	    List bills = lQuery.list();
	 
	    System.out.println("bills.size()"+bills.size());
	    if (bills.size() > 0) {
	    	System.out.println("inside loop;bills.size()"+bills.size());
	    	      flag = true; 
	      }
	   
	  }
	  catch (Exception e) {
	    logger.error(" Error is : " + e, e);
	  }

	  return flag;
	}

public  String getGradeName(String sevaarthId){
	
	String name=null;
	List lLstResult = null;
	
	try {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ogm.GRADE_NAME FROM org_emp_mst oem ");
		sb.append("join mst_dcps_emp mde on mde.ORG_EMP_MST_ID=oem.EMP_ID ");
		sb.append("join ORG_GRADE_MST ogm on ogm.GRADE_ID=oem.GRADE_ID ");
		sb.append("where mde.SEVARTH_ID=:sevaarthId ");

		Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
		selectQuery.setParameter("sevaarthId", sevaarthId);

		lLstResult = selectQuery.list();
		if (lLstResult.size() > 0) {
			name = lLstResult.get(0).toString();

		}
	} catch (Exception e) {
		this.logger.error("Error is :" + e);
		e.printStackTrace();
	}
	return name;
	
}
public boolean forwardToAG(String sevaarthId)throws Exception{
	boolean status = false;
	try {
		StringBuilder sb = new StringBuilder();
		sb.append("update TRN_PNSNPROC_INWARDPENSION set CASE_STATUS='FRWDTOAG' ,DRAFT_FLAG='F',FRWDAG_DATE=sysdate  where SEVAARTH_ID=:sevaarthId ");

		Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
		selectQuery.setParameter("sevaarthId", sevaarthId);

	
		int insertFlag = selectQuery.executeUpdate();
		System.out.println("delete sevaarthId::::"+insertFlag);
		
		if(insertFlag>0)
			status=true;
		
	} catch (Exception e) {
		this.logger.error("Error is :" + e);
		e.printStackTrace();
	}
	
	return status;
	
}
//added by ankita 09-04-2015 (Deputation Prev DDOCODE)

public  String getPrevDDOCODE(String sevaarthId){
	
	String ddocode=null;
	List lLstResult = null;
	
	try {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT hde.DDO_CODE FROM MST_DCPS_EMP mde ");
		sb.append("join HST_DCPS_EMP_DETAILS hde on mde.DCPS_EMP_ID=hde.DCPS_EMP_ID ");
		sb.append("where mde.SEVARTH_ID=:sevaarthId  ");
		sb.append("order by START_date desc ");
		sb.append("fetch first 1 rows only ");

		Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
		selectQuery.setParameter("sevaarthId", sevaarthId);

		lLstResult = selectQuery.list();
		if (lLstResult.size() > 0) {
			ddocode = lLstResult.get(0).toString();

		}
	} catch (Exception e) {
		this.logger.error("Error is :" + e);
		e.printStackTrace();
	}
	return ddocode;
	
}

//Added by shraddha for deputation module changes----------------------
public String getDesigOfCurrDdo(String prevDDOCODE){

	String currDdoDesig = "";
	List result = null;
	
	try{
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT desig.DSGN_NAME FROM org_ddo_mst odm ");
		sb.append("join ORG_POST_DETAILS_RLT opd on odm.POST_ID=opd.POST_ID ");
		sb.append("join ORG_DESIGNATION_MST desig on opd.DSGN_ID=desig.DSGN_ID ");
		sb.append("where odm.DDO_CODE=:prevDDOCODE ");
		Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
		selectQuery.setParameter("prevDDOCODE", prevDDOCODE);
		result=selectQuery.list();
		if(result.size() > 0){
			currDdoDesig= result.get(0).toString();	
		}
		
	}catch (Exception e) {
		this.logger.error("Error is :" + e);
		e.printStackTrace();
	}
 return currDdoDesig;

}

public List getPenOffDtls(String penDdoCode){

	List penOffcDtlsList=null;
	
	try{
	StringBuilder sb = new StringBuilder();
	sb.append("SELECT DDO_OFFICE ||'#'|| DSGN_NAME FROM ORG_DDO_MST ");
	sb.append("where DDO_CODE=:penDdoCode ");
	Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
	selectQuery.setParameter("penDdoCode", penDdoCode);
	penOffcDtlsList=selectQuery.list();


	}
	catch (Exception e) {
		this.logger.error("Error is :" + e);
		e.printStackTrace();
	}
 return penOffcDtlsList;
}

public String getDdoCodeForPenDDOAsst(Long lLngAsstPostId) {

	String lStrDdoCode = null;
	StringBuilder lSBQuery = new StringBuilder();
	List lLstCodeList =null;
	try{

	lSBQuery.append(" SELECT odm.DDO_CODE FROM ORG_DDO_MST odm ");
	lSBQuery.append("join RLT_PENSION_DDO_ASST rpd on odm.POST_ID=rpd.DDO_POST_ID ");
	lSBQuery.append("where rpd.PENSION_ASST_POST_ID=:asstPostId ");
	
	Query lQuery = this.ghibSession.createSQLQuery(lSBQuery.toString());
	lQuery.setParameter("asstPostId", lLngAsstPostId);

	 lLstCodeList = lQuery.list();
	 if(lLstCodeList.size() > 0){
		 lStrDdoCode = lLstCodeList.get(0).toString();
	 }
	}
	catch (Exception e) {
		this.logger.error("Error is :" + e);
		e.printStackTrace();
	}
	

	return lStrDdoCode;
}


public boolean checkDdoCode(String penDdoCode)throws Exception {
	
	  boolean flag = false;

	 
	  try {
	    StringBuilder lSBQuery = new StringBuilder();
	    lSBQuery.append("SELECT * FROM org_ddo_mst where ddo_Code=:penDdoCode ");

	    Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
	    lQuery.setParameter("penDdoCode", penDdoCode);
	    List bills = lQuery.list();
	 
	    System.out.println("bills.size()"+bills.size());
	    if (bills.size() > 0) {
	    	System.out.println("inside loop;bills.size()"+bills.size());
	    	      flag = true; 
	      }
	   
	  }
	  catch (Exception e) {
	    logger.error(" Error is : " + e, e);
	  }
	  logger.info("flag&&&&&&"+flag);
	  return flag;
	}

public boolean checkPenAsst (String penDdoCode)throws Exception {
	
	  boolean flag = false;

	 
	  try {
	    StringBuilder lSBQuery = new StringBuilder();
	    lSBQuery.append("SELECT pen.PENSION_ASST_POST_ID FROM org_ddo_mst ddo ");
	    lSBQuery.append("join RLT_PENSION_DDO_ASST pen on ddo.POST_ID=pen.DDO_POST_ID ");
	    lSBQuery.append("WHERE ddo.DDO_CODE=:penDdoCode ");

	    Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
	    lQuery.setParameter("penDdoCode", penDdoCode);
	    List bills = lQuery.list();
	 
	    System.out.println("bills.size()"+bills.size());
	    if (bills.size() > 0) {
	    	System.out.println("inside loop;bills.size()"+bills.size());
	    	      flag = true; 
	      }
	   
	  }
	  catch (Exception e) {
	    logger.error(" Error is : " + e, e);
	  }
	  logger.info("flag&&&&&&"+flag);
	  return flag;
	}


//-----------------------end-----------------------------------------

//Added by shraddha for SMS 

public String getMobileNo(String sevaarthId){
	
	String mobileNo = "";
	List result=null;
	
	try{
		StringBuilder lSBQuery = new StringBuilder();
		
		  lSBQuery.append("SELECT tpp.PNSNR_ADDR_MOBILENO FROM TRN_PNSNPROC_INWARDPENSION inw ");
		  lSBQuery.append("join TRN_PNSNPROC_PNSNRDTLS tpp on inw.INWARD_PENSION_ID=tpp.INWARD_PENSION_ID ");
		  lSBQuery.append("where inw.SEVAARTH_ID=:sevaarthId ");
		 
		  Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		    lQuery.setParameter("sevaarthId", sevaarthId);
		     result = lQuery.list();
		     
		     if (result.size() > 0){
		    	 mobileNo=  result.get(0).toString();
		     }
		}
	catch (Exception e) {
	    logger.error(" Error is : " + e, e);
	  }
	
	return mobileNo;
	
}

public String getName(String sevaarthId){
	
	String name = "";
	List result=null;
	
	try{
		StringBuilder lSBQuery = new StringBuilder();
		
		  lSBQuery.append("SELECT nvl(tpp.PNSNR_NAME,'') FROM TRN_PNSNPROC_INWARDPENSION inw ");
		  lSBQuery.append("join TRN_PNSNPROC_PNSNRDTLS tpp on inw.INWARD_PENSION_ID=tpp.INWARD_PENSION_ID ");
		  lSBQuery.append("where inw.SEVAARTH_ID=:sevaarthId ");
		 
		  Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		    lQuery.setParameter("sevaarthId", sevaarthId);
		     result = lQuery.list();
		     
		     if (result.size() > 0){
		    	 name=  result.get(0).toString();
		     }
		}
	catch (Exception e) {
	    logger.error(" Error is : " + e, e);
	  }
	
	return name;
	
}

public String getDdoMobile(String sevaarthId){
	
	String ddoMob = "";
	List result=null;
	
	try{
		StringBuilder lSBQuery = new StringBuilder();
		
		  lSBQuery.append("SELECT substr (mdo.MOBILE_NO_NEW,3,12) FROM TRN_PNSNPROC_INWARDPENSION tpi ");
		  lSBQuery.append("join ORG_DDO_MST odm on tpi.LOCATION_CODE=odm.LOCATION_CODE ");
		  lSBQuery.append("join MST_DCPS_DDO_OFFICE mdo on odm.DDO_CODE=mdo.DDO_CODE and mdo.DDO_OFFICE='Yes' ");
		  lSBQuery.append("where tpi.SEVAARTH_ID=:sevaarthId ");
		 
		  Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		    lQuery.setParameter("sevaarthId", sevaarthId);
		     result = lQuery.list();
		     
		     if (result.size() > 0){
		    	 ddoMob=  result.get(0).toString();
		     }
		}
	catch (Exception e) {
	    logger.error(" Error is : " + e, e);
	  }
	
	return ddoMob;
	
}
//Added by KINJAL
public void deleteAllPensionDtls(Long lLongInwardId) {

	
	StringBuilder lSBQuery3 = new StringBuilder();
	lSBQuery3.append(" DELETE FROM Trn_Pnsnproc_Pnsnrpay where INWARD_PENSION_ID = :inwardId");
	Query lQuery3 = ghibSession.createSQLQuery(lSBQuery3.toString());
	logger.info("lQuery1-------"+lQuery3);
	lQuery3.setParameter("inwardId", lLongInwardId);
	lQuery3.executeUpdate();
	
	StringBuilder lSBQuery4 = new StringBuilder();
	lSBQuery4.append(" DELETE FROM Trn_Pnsnproc_Avg_Pay_Calc where INWARD_PENSION_ID = :inwardId");
	Query lQuery4 = ghibSession.createSQLQuery(lSBQuery4.toString());
	lQuery4.setParameter("inwardId", lLongInwardId);
	lQuery4.executeUpdate();
	
	StringBuilder lSBQuery5 = new StringBuilder();
	lSBQuery5.append(" DELETE FROM TRN_PNSNPROC_ADVNCEBAL where INWARD_PENSION_ID = :inwardId");
	Query lQuery5 = ghibSession.createSQLQuery(lSBQuery5.toString());
	lQuery5.setParameter("inwardId", lLongInwardId);
	lQuery5.executeUpdate();
	
	StringBuilder lSBQuery6 = new StringBuilder();
	lSBQuery6.append(" DELETE FROM TRN_PNSNPROC_CHECKLIST where INWARD_PENSION_ID = :inwardId");
	Query lQuery6 = ghibSession.createSQLQuery(lSBQuery6.toString());
	lQuery6.setParameter("inwardId", lLongInwardId);
	lQuery6.executeUpdate();
	
	StringBuilder lSBQuery7 = new StringBuilder();
	lSBQuery7.append(" DELETE FROM TRN_PNSNPROC_PNSNRSERVCBREAK where INWARD_PENSION_ID = :inwardId");
	Query lQuery7 = ghibSession.createSQLQuery(lSBQuery7.toString());
	lQuery7.setParameter("inwardId", lLongInwardId);
	lQuery7.executeUpdate();
	
	StringBuilder lSBQuery8 = new StringBuilder();
	lSBQuery8.append(" DELETE FROM TRN_PNSNPROC_EVENTDTLS where INWARD_PENSION_ID = :inwardId");
	Query lQuery8 = ghibSession.createSQLQuery(lSBQuery8.toString());
	lQuery8.setParameter("inwardId", lLongInwardId);
	lQuery8.executeUpdate();
	
	StringBuilder lSBQuery9 = new StringBuilder();
	lSBQuery9.append(" DELETE FROM ifms.TRN_PNSNPROC_PROVISIONAL_PAID where INWARD_PENSION_ID = :inwardId");
	Query lQuery9 = ghibSession.createSQLQuery(lSBQuery9.toString());
	lQuery9.setParameter("inwardId", lLongInwardId);
	lQuery9.executeUpdate();
	
	StringBuilder lSBQuery10 = new StringBuilder();
	lSBQuery10.append(" DELETE FROM ifms.TRN_PNSNPROC_FOREIGN_SERV where INWARD_PENSION_ID = :inwardId");
	Query lQuery10 = ghibSession.createSQLQuery(lSBQuery10.toString());
	lQuery10.setParameter("inwardId", lLongInwardId);
	lQuery10.executeUpdate();
	
	StringBuilder lSBQuery11 = new StringBuilder();
	lSBQuery11.append(" DELETE FROM TRN_PNSNPROC_FAMILYDTLS where INWARD_PENSION_ID = :inwardId");
	Query lQuery11 = ghibSession.createSQLQuery(lSBQuery11.toString());
	lQuery11.setParameter("inwardId", lLongInwardId);
	lQuery11.executeUpdate();
	
	StringBuilder lSBQuery12 = new StringBuilder();
	lSBQuery12.append(" DELETE FROM TRN_PNSNPROC_NOMINEEDTLS where INWARD_PENSION_ID = :inwardId");
	Query lQuery12 = ghibSession.createSQLQuery(lSBQuery12.toString());
	lQuery12.setParameter("inwardId", lLongInwardId);
	lQuery12.executeUpdate();
	
	StringBuilder lSBQuery13 = new StringBuilder();
	lSBQuery13.append(" DELETE FROM TRN_PNSNPROC_PNSNCALC where INWARD_PENSION_ID = :inwardId");
	Query lQuery13 = ghibSession.createSQLQuery(lSBQuery13.toString());
	lQuery13.setParameter("inwardId", lLongInwardId);
	lQuery13.executeUpdate();
	
	StringBuilder lSBQuery14 = new StringBuilder();
	lSBQuery14.append(" DELETE FROM TRN_PNSNPROC_ASSESSED_DUES where INWARD_PENSION_ID = :inwardId");
	Query lQuery14 = ghibSession.createSQLQuery(lSBQuery14.toString());
	lQuery14.setParameter("inwardId", lLongInwardId);
	lQuery14.executeUpdate();
	
	StringBuilder lSBQuery15 = new StringBuilder();
	lSBQuery15.append(" DELETE FROM TRN_PNSNPROC_RECOVERY where INWARD_PENSION_ID = :inwardId");
	Query lQuery15 = ghibSession.createSQLQuery(lSBQuery15.toString());
	lQuery15.setParameter("inwardId", lLongInwardId);
	lQuery15.executeUpdate();
	
	StringBuilder lSBQuery2 = new StringBuilder();
	lSBQuery2.append(" DELETE FROM TRN_PNSNPROC_PNSNRDTLS where INWARD_PENSION_ID = :inwardId");
	Query lQuery2 = ghibSession.createSQLQuery(lSBQuery2.toString());
	logger.info("lQuery1-------"+lQuery2);
	lQuery2.setParameter("inwardId", lLongInwardId);
	lQuery2.executeUpdate();
	
	StringBuilder lSBQuery1 = new StringBuilder();
	lSBQuery1.append(" DELETE FROM TRN_PNSNPROC_INWARDPENSION where INWARD_PENSION_ID = :inwardId");
	Query lQuery1 = ghibSession.createSQLQuery(lSBQuery1.toString());
	logger.info("lQuery1-------"+lQuery1);
	lQuery1.setParameter("inwardId", lLongInwardId);
	lQuery1.executeUpdate();

}

  public boolean rejectPensionCaseByAG(String sevaarthId) {

	int check2=0;
	boolean checkFlag= false;
	StringBuilder lSBQuery = new StringBuilder();
	lSBQuery.append(" update TRN_PNSNPROC_INWARDPENSION set case_status='RJCTBYAG',draft_flag='R' where SEVAARTH_ID = :sevaarthId");
	Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
	logger.info("lQuery1-------"+lQuery);
	lQuery.setParameter("sevaarthId", sevaarthId);
	check2 = lQuery.executeUpdate();
	gLogger.info("check2----"+check2);
	
	
	if(check2>0)
	{
		checkFlag = true;
	}
	return checkFlag;
}
  
  
  public Long checkPayComm(String paycomm,String sevarthId){
	  
	  List result=null;
	  Long sevenPayBasic=0l;
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			
			  lSBQuery.append("SELECT mde.SEVEN_PC_BASIC FROM MST_DCPS_EMP mde ");
			 // lSBQuery.append("join TRN_PNSNPROC_INWARDPENSION tpi on mde.SEVARTH_ID=tpi.SEVAARTH_ID ");
			  lSBQuery.append("where mde.SEVEN_PC_BASIC > 0 and mde.SEVARTH_ID=:sevarthId ");
			  
			  
			  Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			    lQuery.setParameter("sevarthId", sevarthId);
			     result = lQuery.list();
			     
			     if (result.size() > 0){
			    	 sevenPayBasic=  Long.parseLong(result.get(0).toString());
			    	 logger.info("sevenPayBasic**"+sevenPayBasic);
			     }
			}
		catch (Exception e) {
		    logger.error(" Error is : " + e, e);
		  }
		
		return sevenPayBasic;
	  
	}
  
  public String getPayCommission(String sevarthId){
	  
	  List result=null;
	  String payCom="";
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			
			  lSBQuery.append("SELECT PAY_COMMISSION FROM TRN_PNSNPROC_INWARDPENSION  ");
			  lSBQuery.append("where SEVAARTH_ID=:sevarthId ");
			  
			  
			  Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			    lQuery.setParameter("sevarthId", sevarthId);
			     result = lQuery.list();
			     
			     if (result.size() > 0){
			    	 payCom=  result.get(0).toString();
			    	 logger.info("payCom**"+payCom);
			     }
			}
		catch (Exception e) {
		    logger.error(" Error is : " + e, e);
		  }
		
		return payCom;
	  
	}
	  
	  
	  
	  
	  
  

}
