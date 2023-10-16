/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 28, 2011		Anjana Suvariya								
 *******************************************************************************
 */
package com.tcs.sgv.common.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;


/**
 * Class Description -
 * 
 * 
 * @author Anjana Suvariya
 * @version 0.1
 * @since JDK 5.0 Apr 28, 2011
 */
public class CommonDAOImpl implements CommonDAO {

	private Session ghibSession = null;
	private final static Logger logger = Logger.getLogger(CommonDAOImpl.class);

	private final ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionproc/PensionCaseConstants");

	public CommonDAOImpl(SessionFactory sessionFactory) {

		// super(type);
		// setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.common.dao.CommonDAO#getAllDesignation(java.lang.Long)
	 */
	public List<ComboValuesVO> getAllDesignation(Long lLngLangId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		List<ComboValuesVO> lLstDesignation = new ArrayList<ComboValuesVO>();
		List lLstResult = new ArrayList();
		ComboValuesVO lObjComboValueVO = null;
		Iterator itr;
		Object[] obj;
		try {

			lSBQuery.append(" Select dsgn.dsgnId,dsgn.dsgnName ");
			lSBQuery.append(" FROM OrgDesignationMst dsgn ");
			lSBQuery.append(" WHERE dsgn.cmnLanguageMst.langId =:langId order by dsgn.dsgnName");
			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setLong("langId", lLngLangId);
			hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
			lLstResult = hqlQuery.list();

			if (lLstResult != null && lLstResult.size() > 0) {
				itr = lLstResult.iterator();
				while (itr.hasNext()) {
					lObjComboValueVO = new ComboValuesVO();
					obj = (Object[]) itr.next();
					lObjComboValueVO.setId(obj[0].toString());
					lObjComboValueVO.setDesc(obj[1].toString().replaceAll("&", "And"));
					lLstDesignation.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			logger.error("Error is :" + e, e);
			e.printStackTrace();
		}
		return lLstDesignation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.common.dao.CommonDAO#getAllDepartment(java.lang.Long,
	 * java.lang.Long)
	 */
	public List<ComboValuesVO> getAllDepartment(Long lLngDepartmentId, Long lLngLangId) throws Exception {

		List<ComboValuesVO> lLstDepartnent = new ArrayList<ComboValuesVO>();
		StringBuilder lSBQuery = new StringBuilder();
		ComboValuesVO lObjComboValueVO = null;
		List lLstResult = new ArrayList();
		Iterator itr;
		Object[] obj;

		try {

			lSBQuery.append(" SELECT clm.locId,clm.locName FROM CmnLocationMst clm, OrgDepartmentMst odm ");
			lSBQuery.append(" WHERE odm.departmentId=:departmentId  ");
			lSBQuery.append(" AND clm.departmentId=odm.departmentId ");
			lSBQuery.append(" and clm.cmnLanguageMst.langId =:langId ");
			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setLong("langId", lLngLangId);
			hqlQuery.setLong("departmentId", lLngDepartmentId);
			hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
			lLstResult = hqlQuery.list();

			if (lLstResult != null && lLstResult.size() > 0) {

				itr = lLstResult.iterator();
				while (itr.hasNext()) {
					lObjComboValueVO = new ComboValuesVO();
					obj = (Object[]) itr.next();
					lObjComboValueVO.setId(obj[0].toString());
					lObjComboValueVO.setDesc(obj[1].toString().replaceAll("&", "And"));
					lLstDepartnent.add(lObjComboValueVO);
				}
				lObjComboValueVO = new ComboValuesVO();
				lObjComboValueVO.setId("-2");
				lObjComboValueVO.setDesc("Unknown");
				lLstDepartnent.add(lObjComboValueVO);
			}
		} catch (Exception e) {
			logger.error("Error is :" + e, e);
			throw e;
		}
		return lLstDepartnent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.common.dao.CommonDAO#getAllState(java.lang.Long)
	 */
	public List<ComboValuesVO> getAllState(Long lLngLangId) throws Exception {

		List<ComboValuesVO> lLstState = new ArrayList<ComboValuesVO>();
		StringBuilder lSBQuery = new StringBuilder();
		List lLstResult = new ArrayList();
		ComboValuesVO lObjComboValueVO = null;
		try {

			lSBQuery.append(" SELECT stateId,stateName ");
			lSBQuery.append(" FROM CmnStateMst ");
			lSBQuery.append(" WHERE cmnLanguageMst.langId =:langId ");
			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setLong("langId", lLngLangId);
			hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
			lLstResult = hqlQuery.list();

			if (lLstResult != null && lLstResult.size() > 0) {
				Iterator itr = lLstResult.iterator();

				while (itr.hasNext()) {
					lObjComboValueVO = new ComboValuesVO();
					Object[] obj = (Object[]) itr.next();
					lObjComboValueVO.setId(obj[0].toString());
					lObjComboValueVO.setDesc(obj[1].toString());
					lLstState.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			logger.error("Error is :" + e, e);
			throw e;
		}
		return lLstState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.common.dao.CommonDAO#getDistrictsOfState(java.lang.Long,
	 * java.lang.Long)
	 */
	public List<ComboValuesVO> getDistrictsOfState(Long lLngStateId, Long lLngLangId) throws Exception {

		List<ComboValuesVO> lLstDistrict = new ArrayList<ComboValuesVO>();
		StringBuilder lSBQuery = new StringBuilder();
		List lLstResult = new ArrayList();
		ComboValuesVO lObjComboValueVO = null;
		Object[] obj;
		try {
			lSBQuery.append(" Select districtCode,districtName ");
			lSBQuery.append(" FROM CmnDistrictMst ");
			lSBQuery.append(" WHERE cmnLanguageMst.langId = :langId");
			lSBQuery.append(" and cmnStateMst.stateId = :stateId  ");
			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setLong("langId", lLngLangId);
			hqlQuery.setParameter("stateId", Long.valueOf(lLngStateId.toString()));
			hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
			lLstResult = hqlQuery.list();

			lObjComboValueVO = new ComboValuesVO();
			lObjComboValueVO.setId("-1");
			lObjComboValueVO.setDesc("--Select--");
			lLstDistrict.add(lObjComboValueVO);
			if (lLstResult != null && lLstResult.size() > 0) {
				Iterator it = lLstResult.iterator();
				while (it.hasNext()) {
					lObjComboValueVO = new ComboValuesVO();
					obj = (Object[]) it.next();
					lObjComboValueVO.setId(obj[0].toString());
					lObjComboValueVO.setDesc(obj[1].toString());
					lLstDistrict.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			logger.error("Error is :" + e, e);
			throw e;
		}
		return lLstDistrict;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.common.dao.CommonDAO#getCityList(java.lang.Long)
	 */
	public List<ComboValuesVO> getCityList(Long lLngLangId) throws Exception {

		List<ComboValuesVO> lLstCity = new ArrayList<ComboValuesVO>();
		StringBuilder lSBQuery = new StringBuilder();
		List lLstResult = new ArrayList();
		ComboValuesVO lObjComboValueVO = null;
		Object[] obj;
		try {
			lSBQuery.append(" Select cityId,cityName ");
			lSBQuery.append(" FROM CmnCityMst ");
			lSBQuery.append(" WHERE cmnLanguageMst.langId = :langId");
			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setLong("langId", lLngLangId);
			hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
			lLstResult = hqlQuery.list();

			lLstCity.add(lObjComboValueVO);
			if (lLstResult != null && lLstResult.size() > 0) {
				Iterator it = lLstResult.iterator();
				while (it.hasNext()) {
					lObjComboValueVO = new ComboValuesVO();
					obj = (Object[]) it.next();
					lObjComboValueVO.setId(obj[0].toString());
					lObjComboValueVO.setDesc(obj[1].toString());
					lLstCity.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			logger.error("Error is :" + e, e);
			throw e;
		}
		return lLstCity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.common.dao.CommonDAO#getBankNames(java.util.Map,
	 * java.lang.Long)
	 */
	public List<ComboValuesVO> getBankList(Map inputMap, Long lLngLangId) throws Exception {

		List lLstResult = new ArrayList();
		List<ComboValuesVO> lLstBanks = new ArrayList<ComboValuesVO>();
		ComboValuesVO lObjComboValueVO = null;
		Iterator itr;
		Object[] obj;

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append("select mb.bankCode, mb.bankName ");
		lSBQuery.append("from MstBank mb where ");
		lSBQuery.append("mb.langId=:langId and mb.activateFlag=:activeFlag order by mb.bankName");

		try {

			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("langId", lLngLangId);
			lQuery.setParameter("activeFlag", Long.valueOf(1));

			lLstResult = lQuery.list();

			if (lLstResult != null && lLstResult.size() > 0) {
				itr = lLstResult.iterator();
				while (itr.hasNext()) {
					lObjComboValueVO = new ComboValuesVO();
					obj = (Object[]) itr.next();
					lObjComboValueVO.setId(obj[0].toString());
					lObjComboValueVO.setDesc(obj[1].toString());
					lLstBanks.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);

			throw e;
		}

		return lLstBanks;
	}

	public List<ComboValuesVO> getBranchListFromBankCode(Long lLngBankCode, String lStrLocCode, Long lLngLangId) throws Exception {

		List<ComboValuesVO> lLstBankBranch = new ArrayList<ComboValuesVO>();
		StringBuilder lSBQuery = new StringBuilder();
		List lLstResult = new ArrayList();
		ComboValuesVO lObjComboValueVO = null;
		Object[] obj;
		try {

			lSBQuery.append("SELECT branchCode,branchName ");
			lSBQuery.append("FROM RltBankBranch  WHERE ");
			lSBQuery.append("bankCode =:bankCode AND langId =:langId AND locationCode= :locationCode order by branchName");

			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setLong("langId", lLngLangId);
			hqlQuery.setLong("bankCode", lLngBankCode);
			hqlQuery.setString("locationCode", lStrLocCode);
			hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
			lLstResult = hqlQuery.list();

			lObjComboValueVO = new ComboValuesVO();
			lObjComboValueVO.setId("-1");
			lObjComboValueVO.setDesc("--Select--");
			lLstBankBranch.add(lObjComboValueVO);
			if (lLstResult != null && lLstResult.size() > 0) {
				Iterator it = lLstResult.iterator();
				while (it.hasNext()) {
					lObjComboValueVO = new ComboValuesVO();
					obj = (Object[]) it.next();
					lObjComboValueVO.setId(obj[0].toString());
					lObjComboValueVO.setDesc(obj[1].toString());
					lLstBankBranch.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {

			logger.error("Error is :" + e, e);
			throw e;
		}
		return lLstBankBranch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.common.dao.CommonDAO#getMonthList(java.lang.String)
	 */
	public List<ComboValuesVO> getMonthList(String lStrLangId) throws Exception {

		List lLstResult = new ArrayList();
		ComboValuesVO lObjComboValueVO = null;
		List<ComboValuesVO> lLstMonths = new ArrayList<ComboValuesVO>();
		Object[] obj;
		try {
			StringBuffer lSBQuery = new StringBuffer("select monthNo,monthName from SgvaMonthMst where langId = :langId order by monthId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("langId", lStrLangId);
			lLstResult = lQuery.list();

			if (lLstResult != null && !lLstResult.isEmpty()) {
				Iterator it = lLstResult.iterator();
				while (it.hasNext()) {
					lObjComboValueVO = new ComboValuesVO();
					obj = (Object[]) it.next();
					lObjComboValueVO.setId(obj[0].toString());
					lObjComboValueVO.setDesc(obj[1].toString());
					lLstMonths.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			logger.error("Error is :" + e, e);
			throw e;
		}
		return lLstMonths;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.common.dao.CommonDAO#getYearList(java.lang.String)
	 */
	public List<ComboValuesVO> getYearList(String lStrLangId) throws Exception {

		List lLstResult = new ArrayList();
		ComboValuesVO lObjComboValueVO = null;
		List<ComboValuesVO> lLstYears = new ArrayList<ComboValuesVO>();
		Object[] obj;
		try {
			StringBuffer lSBQuery = new StringBuffer("select finYearId,finYearCode from SgvcFinYearMst where langId = :lLangId order by finYearCode");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("lLangId", lStrLangId);
			lLstResult = lQuery.list();

			if (lLstResult != null && !lLstResult.isEmpty()) {
				Iterator it = lLstResult.iterator();
				while (it.hasNext()) {
					lObjComboValueVO = new ComboValuesVO();
					obj = (Object[]) it.next();
					lObjComboValueVO.setId(obj[0].toString());
					lObjComboValueVO.setDesc(obj[1].toString());
					lLstYears.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			logger.error("Exception::" + e.getMessage(), e);
			throw (e);
		}
		return lLstYears;
	}

	public List isIFSCCodeExsist(String lStrBranchCode) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		List lLstResult = new ArrayList();

		try {
			lSBQuery.append("SELECT micrCode ");
			lSBQuery.append("FROM RltBankBranch  WHERE ");
			lSBQuery.append("branchCode=:branchCode");

			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setLong("branchCode", Long.parseLong(lStrBranchCode));

			lLstResult = hqlQuery.list();
		} catch (Exception e) {
			logger.error("Error is :" + e, e);
			throw e;
		}
		return lLstResult;
	}

	public String getRoleByPost(Long lLngPostId) throws Exception {

		List lLstResultList = null;
		Query hqlQuery = null;
		String lStrToRole = "";
		StringBuilder lStrQuery = new StringBuilder();

		try {
			lStrQuery.append(" SELECT acl.aclRoleMst.roleId from AclPostroleRlt acl where acl.orgPostMst.postId=:lLngPostId ");

			hqlQuery = ghibSession.createQuery(lStrQuery.toString());
			hqlQuery.setParameter("lLngPostId", lLngPostId);
			lLstResultList = hqlQuery.list();

			logger.info("resultList Size FOR LEVEL : " + lLstResultList.size());
			if (lLstResultList.size() > 0) {
				lStrToRole = (lLstResultList.get(0).toString());

			} else {
				lStrToRole = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);
			throw e;
		}
		logger.info("getRoleByPost in Role Ids : " + lStrToRole);
		logger.info("getRoleByPost end...................");

		return lStrToRole;
	}

	public String getAuditorNameByPostId(Long lLngPostId) throws Exception {

		List lLstResultList = null;
		Query hqlQuery = null;
		String lStrAudName = "";
		StringBuilder lStrQuery = new StringBuilder();
		try {
			lStrQuery.append(" Select concat(concat(concat(concat(oem.empFname,' '),oem.empMname),' '),oem.empLname)  \n");
			lStrQuery.append(" From  \n");
			lStrQuery.append(" OrgEmpMst oem,OrgUserpostRlt upr \n");
			lStrQuery.append(" Where \n");
			lStrQuery.append(" oem.orgUserMst.userId = upr.orgUserMst.userId \n");
			lStrQuery.append(" and upr.orgPostMstByPostId.postId = :lLngPostId \n");
			lStrQuery.append(" and oem.cmnLanguageMst.langId = :lLngId \n");

			hqlQuery = ghibSession.createQuery(lStrQuery.toString());
			hqlQuery.setLong("lLngPostId", lLngPostId);
			hqlQuery.setInteger("lLngId", 1);
			lLstResultList = hqlQuery.list();

			if (lLstResultList.size() > 0) {
				lStrAudName = (lLstResultList.get(0).toString());
			}
		} catch (Exception e) {
			logger.error("Error is :" + e, e);
			throw e;
		}
		return lStrAudName;
	}

	public String getTreasuryName(Long lLngLangId, Long lLngLocationCode) throws Exception {

		// TODO Auto-generated method stub
		String lStrTreasuryName = null;
		List lLstResult = null;
		StringBuffer lSBQuery = new StringBuffer();
		try {
			lSBQuery.append("SELECT locName FROM CmnLocationMst WHERE  cmnLanguageMst.langId = :langid AND locId = :locationCode");

			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setParameter("langid", lLngLangId);
			hqlQuery.setParameter("locationCode", lLngLocationCode);
			lLstResult = hqlQuery.list();
			if (lLstResult != null && !lLstResult.isEmpty()) {
				lStrTreasuryName = lLstResult.get(0).toString();
			}

		} catch (Exception e) {
			logger.error("Exception::" + e.getMessage(), e);
			throw (e);
		}
		return lStrTreasuryName;
	}

	public String getBankNameFromBankCode(String lStrBankCode, Long lLngLandId) throws Exception {

		String lStrBankName = "";
		List lLstResult = null;
		StringBuffer lSBQuery = new StringBuffer();
		try {
			lSBQuery.append("Select bankName from MstBank where bankCode = :lBankCode and langId = :lLangId \n");
			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setString("lBankCode", lStrBankCode);
			hqlQuery.setLong("lLangId", lLngLandId);
			lLstResult = hqlQuery.list();
			if (lLstResult != null && lLstResult.size() > 0) {
				lStrBankName = (String) lLstResult.get(0);
				lStrBankName = (lStrBankName != null) ? lStrBankName : "";
			}
		} catch (Exception e) {
			logger.error("Exception::" + e.getMessage(), e);
			throw (e);
		}
		return lStrBankName;
	}

	public String getBranchNameFromBranchCode(String lStrBankCode, String lStrBranchCode, Long lLngLandId) throws Exception {

		String lStrBranchName = "";
		List lLstResult = null;
		StringBuffer lSBQuery = new StringBuffer();
		try {
			lSBQuery.append("Select branchName from RltBankBranch where bankCode = :lBankCode and branchCode = :lBranchCode and langId = :lLangId \n");
			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setString("lBankCode", lStrBankCode);
			hqlQuery.setString("lBranchCode", lStrBranchCode);
			hqlQuery.setLong("lLangId", lLngLandId);
			lLstResult = hqlQuery.list();
			if (lLstResult != null && lLstResult.size() > 0) {
				lStrBranchName = (String) lLstResult.get(0);
				lStrBranchName = (lStrBranchName != null) ? lStrBranchName : "";
			}
		} catch (Exception e) {
			logger.error("Exception::" + e.getMessage(), e);
			throw (e);
		}
		return lStrBranchName;
	}
}
