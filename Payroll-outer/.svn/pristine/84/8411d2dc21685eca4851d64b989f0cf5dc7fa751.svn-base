package com.tcs.sgv.lcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcBudgetPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcChequePosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDeductionPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDtlPosting;

/**
 * @author 602106
 *
 */
public class LcAdviceReceiptDAOImpl extends GenericDaoHibernateImpl implements
		LcAdviceReceiptDAO {
	public LcAdviceReceiptDAOImpl(Class<TrnLcDeductionPosting> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	final Logger glogger = Logger.getLogger(LcDivisionAccMstDAOImpl.class);

	CommonVO lCmnVO = null;

	ResourceBundle bundleConst = ResourceBundle
			.getBundle("resources/lcm/LcmConstants");

	/*public List getMonthDtls(int lILangId) {

		Session hibSession = getSession();

		ArrayList lArrMnthDtlList = new ArrayList();
		String lStrLangId = getStrLangId(lILangId);
		String lStrMnthDtlQuery = "select m.month_id, m.month_name from sgva_month_mst m where lang_id='"
				+ lStrLangId + "'";
		glogger
				.info("---------Query for getting monthList in getMonthDtls of  LcAdviceReceiptDAOImpl is ::"
						+ lStrMnthDtlQuery);
		

		Query lSQLQuery = hibSession.createSQLQuery(lStrMnthDtlQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		while (it.hasNext()) {
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[]) it.next();
			String lStrLkpID = (tuple[0].toString());
			String lStrLkpName = (tuple[1].toString());
			lCmnVO.setCommonId(lStrLkpID);
			lCmnVO.setCommonDesc(lStrLkpName);
			lArrMnthDtlList.add(lCmnVO);
		}
		
		return lArrMnthDtlList;
	}*/

	public String getStrLangId(long lILangId) {
		Session hibSession = getSession();
		String lStrLangId = "";
		String lStrLanguageQuery = "select lang_short_name from cmn_language_mst where lang_id="
				+ lILangId;
		glogger
				.info("---------Query for getting language  in getStrLangId of  LcAdviceReceiptDAOImpl is ::"
						+ lStrLanguageQuery);


		Query query = hibSession.createSQLQuery(lStrLanguageQuery);
	
		List resList = query.list();
	
		Iterator it = resList.iterator();
	
		while (it.hasNext()) {
		
			Object tuple = (Object) it.next();
			
			lStrLangId = (tuple.toString());
			
		}
		glogger.
				info("Returning from getStrLangId() of  LcAdviceReceiptDAOImpl :: lStrLangId is ::  "
						+ lStrLangId);
		return lStrLangId;
	}

	public LcDivisionInformationVO getDivisionInformation(String lStrDivCode,
			long lILangId) {

		Session hibSession = getSession();

		LcDivisionInformationVO divInformationVO = new LcDivisionInformationVO();
		String lStrLangId = getStrLangId(lILangId);

		String lStrMnthDtlQuery = "select d.lc_order_id,d.division_code, d.lc_valid_frm, d.lc_available_amt,"
				+ "cmn.loc_name,lkp.lookup_desc,dst.district_name,dst.district_code,lkp.lookup_name"
				+ " from trn_lc_distribution d,cmn_location_mst cmn,cmn_lookup_mst lkp,cmn_district_mst dst "
				+ " where d.division_code=(select loc.location_code from cmn_location_mst loc  "
				+ " where loc.location_code = '"
				+ lStrDivCode
				+ "' and loc.lang_id= '"
				+ lILangId
				+ "') and"
				+ " d.division_code= cmn.location_code and cmn.type_lookup_id = lkp.lookup_id and "
				+ " cmn.lang_id = lkp.lang_id and cmn.loc_district_id = dst.district_id and "
				+ " cmn.lang_id = dst.lang_id and d.line_cntr="
				+ " (select max(dis.line_cntr) from trn_lc_distribution dis where dis.division_code= d.division_code) ";

		glogger.
				info("---------Query for getting monthList in getMonthDtls of  LcAdviceReceiptDAOImpl is ::"
						+ lStrMnthDtlQuery);

		Query lSQLQuery = hibSession.createSQLQuery(lStrMnthDtlQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		while (it.hasNext()) {
			Object[] tuple = (Object[]) it.next();
			String lStrLcOrderId = (tuple[0].toString());
			String lStrDivId = (tuple[1].toString());
			String lStrLcValidFrom = (tuple[2].toString());
			String lStrLcAvlAmt = (tuple[3].toString());
			String lStrDivName = (tuple[4].toString());
			String lStrDeptName = (tuple[5].toString());
			String lStrDistName = (tuple[6].toString());
			String lStrDistrictCode = (tuple[7].toString());
			String lStrDeptCode = (tuple[8].toString());
			
			divInformationVO.setLc_order_id(lStrLcOrderId);
			divInformationVO.setDivisionId(lStrDivId);
			divInformationVO.setLc_valid_from(lStrLcValidFrom);
			divInformationVO.setOpening_lc(lStrLcAvlAmt);
			divInformationVO.setDivision_name(lStrDivName);
			divInformationVO.setDepartment_name(lStrDeptName);
			divInformationVO.setDistrict_name(lStrDistName);
			divInformationVO.setDistrictCode(lStrDistrictCode);
			divInformationVO.setDepartmentCode(lStrDeptCode);

		}

		return divInformationVO;
	}

/*	public List getClassOfExp(int lILangId) {
		Session hibSession = getSession();
		ArrayList lArrClassExp = new ArrayList();
		// long lLngParentLookup=100043;//<-- need to get from constants
		String lStrParentShrtNm = bundleConst
				.getString("LC.CLASS_PARENT_SHRT_NAME");

		
		 * String lStrClassExpQuery = "select lookup_id,lookup_name from
		 * cmn_lookup_mst where parent_lookup_id="+lLngParentLookup+" and
		 * lang_id ="+lILangId;
		 

		String lStrClassExpQuery = "select lookup_id,lookup_name from cmn_lookup_mst where lang_id ="
				+ lILangId
				+ " and parent_lookup_id="
				+ " (select lookup_id from cmn_lookup_mst where lookup_short_name='"
				+ lStrParentShrtNm + "')";

		Query lSQLQuery = hibSession.createSQLQuery(lStrClassExpQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		while (it.hasNext()) {
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[]) it.next();
			String lStrLkpID = (tuple[0].toString());
			String lStrLkpName = (tuple[1].toString());
			lCmnVO.setCommonId(lStrLkpID);
			lCmnVO.setCommonDesc(lStrLkpName);
			lArrClassExp.add(lCmnVO);
		}

		return lArrClassExp;
	}
*/
	public String getParentPostId(String lLngLoggedInPostId, int lILangId) {
		Session hibSession = getSession();
		String lStrTsryPostId = "";
		String lStrClassExpQuery = "select m.parent_loc_id from cmn_location_mst m where m.location_code='"
				+ lLngLoggedInPostId + "' and m.lang_id=" + lILangId;
		Query lSQLQuery = hibSession.createSQLQuery(lStrClassExpQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		while (it.hasNext()) {
			Object tuple = (Object) it.next();
			lStrTsryPostId = (tuple.toString());
		}

		return lStrTsryPostId;
	}

	public String getLcValidityDate(String liDivId) {
		Session hibSession = getSession();
		String lStrLcValidUptoDt = "";
		String lStrClassExpQuery = "select d.lc_valid_to from trn_lc_distribution d where d.division_code='"
				+ liDivId
				+ "' and d.lc_order_id="
				+ " (select max(lc_order_id) from trn_lc_distribution m where m.division_code=d.division_code) "
				+ " and sysdate <= lc_valid_to";

		Query lSQLQuery = hibSession.createSQLQuery(lStrClassExpQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		while (it.hasNext()) {
			Object tuple = (Object) it.next();
			lStrLcValidUptoDt = (tuple.toString());
		}

		return lStrLcValidUptoDt;
	}

	public String getDivisionAccountNo(String liDivId, int lILangId) {
		Session hibSession = getSession();
		String lStrDivAccNo = "";
		String lStrClassExpQuery = "select a.lc_acc_no from mst_lc_division_account a where a.division_code='"
				+ liDivId + "' and a.lang_id=" + lILangId;

		Query lSQLQuery = hibSession.createSQLQuery(lStrClassExpQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		while (it.hasNext()) {
			Object tuple = (Object) it.next();
			lStrDivAccNo = (tuple.toString());
		}

		return lStrDivAccNo;
	}

	/*
	 * public List getFundType(int lILangId) { Session hibSession =
	 * getSession(); ArrayList lArrFundTypExp = new ArrayList(); // long
	 * lLngParentLookup=100040;//<-- need to get from constants String
	 * lStrParentFundId=bundleConst.getString("LC.FUND_PARENT_SHRT_NAME"); //
	 * String lStrFundTypeQuery = "select lookup_id,lookup_name from
	 * cmn_lookup_mst where parent_lookup_id="+lLngParentLookup+" and lang_id
	 * ="+lILangId;
	 * 
	 * String lStrFundTypeQuery = "select lookup_id,lookup_name from
	 * cmn_lookup_mst where lang_id ="+lILangId+" and parent_lookup_id="+
	 * "(select lookup_id from cmn_lookup_mst where
	 * lookup_short_name='"+lStrParentFundId+"')"; Query lSQLQuery =
	 * hibSession.createSQLQuery(lStrFundTypeQuery); List lResList =
	 * lSQLQuery.list(); Iterator it = lResList.iterator(); while(it.hasNext()) {
	 * lCmnVO = new CommonVO(); Object[] tuple = (Object[])it.next(); String
	 * lStrLkpID = (tuple[0].toString()); String lStrLkpName =
	 * (tuple[1].toString()); lCmnVO.setCommonId(lStrLkpID);
	 * lCmnVO.setCommonDesc(lStrLkpName); lArrFundTypExp.add(lCmnVO); }
	 * 
	 * return lArrFundTypExp; }
	 */

	/*
	 * public List getBudgetType(int lILangId) { Session hibSession =
	 * getSession(); ArrayList lArrBudTypExp = new ArrayList(); //long
	 * lLngParentLookup=150009;//<-- need to get from constants String
	 * lStrBudType=bundleConst.getString("LC.BUDGET_PARENT_SHRT_NAME"); //
	 * String lStrFundTypeQuery = "select lookup_id,lookup_name from
	 * cmn_lookup_mst where parent_lookup_id="+lLngParentLookup+" and lang_id
	 * ="+lILangId;
	 * 
	 * String lStrFundTypeQuery = "select lookup_id,lookup_name from
	 * cmn_lookup_mst where lang_id ="+lILangId+" and parent_lookup_id="+
	 * "(select lookup_id from cmn_lookup_mst where
	 * lookup_short_name='"+lStrBudType+"')"; Query lSQLQuery =
	 * hibSession.createSQLQuery(lStrFundTypeQuery); List lResList =
	 * lSQLQuery.list(); Iterator it = lResList.iterator(); while(it.hasNext()) {
	 * lCmnVO = new CommonVO(); Object[] tuple = (Object[])it.next(); String
	 * lStrLkpID = (tuple[0].toString()); String lStrLkpName =
	 * (tuple[1].toString()); lCmnVO.setCommonId(lStrLkpID);
	 * lCmnVO.setCommonDesc(lStrLkpName); lArrBudTypExp.add(lCmnVO); } return
	 * lArrBudTypExp; }
	 */

	public List getDeductionNames(int lILangId) {
		Session hibSession = getSession();
		ArrayList lArrDeduction = new ArrayList();

		String lStrDeductionQuery = "select edp_code,edp_short_name from rlt_lc_edp_receipt where lang_id="
				+ lILangId + " order by edp_code";

		Query lSQLQuery = hibSession.createSQLQuery(lStrDeductionQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		while (it.hasNext()) {
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[]) it.next();
			String lStrEDPCode = (tuple[0].toString());
			String lStrEDPName = (tuple[1].toString() + "("
					+ tuple[0].toString() + ")");
			lCmnVO.setCommonId(lStrEDPCode);
			lCmnVO.setCommonDesc(lStrEDPName);
			lArrDeduction.add(lCmnVO);
		}
		return lArrDeduction;
	}

	public boolean saveLcDtlPosting(TrnLcDtlPosting lObjLcDtlPostingVo) {
		try {
			Session s = getSession();
			s.save(lObjLcDtlPostingVo);
			return true;
		} catch (Exception e) {
			glogger.error("Error ::  in saveLcDtlPosting "+e,e);
			return false;
		}
	}

	public boolean saveLcChequePosting(TrnLcChequePosting lcChequePostingVO) {
		try {
			Session s = getSession();
			s.save(lcChequePostingVO);
			return true;
		} catch (Exception e) {
			glogger.error("Error :: "+e,e);
			return false;
		}
	}

	public boolean saveLcBudgetPosting(TrnLcBudgetPosting lcBudgetPostingVO) {
		try {
			Session s = getSession();
			s.save(lcBudgetPostingVO);
			return true;
		} catch (Exception e) {
			glogger.error("Error :: "+e,e);
			return false;
		}
	}

	public String getStringLangId(long lLngLangId) {
		Session hibSession = getSession();
		ArrayList lArrDeduction = new ArrayList();
		String lStrLangId = "";

		String lStrDeductionQuery = "select lang_short_name from cmn_language_mst where lang_id="
				+ lLngLangId;

		Query lSQLQuery = hibSession.createSQLQuery(lStrDeductionQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		while (it.hasNext()) {
			Object tuple = (Object) it.next();
			lStrLangId = (tuple.toString());
		}
		return lStrLangId;
	}

	public List getMjrHdByDemand(String lStrDemandNo, String lStrLangId) {
		Session hibSession = getSession();
		ArrayList lArrMjrHd = new ArrayList();

		String lStrMjrHdQuery = "select budmjrhd_code,budmjrhd_id from sgva_budmjrhd_mst where  demand_code='"
				+ lStrDemandNo
				+ "' and (budmjrhdtype='E' or budmjrhdtype='R') and lang_id='"
				+ lStrLangId + "' and status='Active'";

		Query lSQLQuery = hibSession.createSQLQuery(lStrMjrHdQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		while (it.hasNext()) {
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[]) it.next();
			String lStrMjrHdCode = (tuple[0].toString());
			String lStrMjrHdId = (tuple[1].toString());
			lCmnVO.setCommonDesc(lStrMjrHdCode);
			lCmnVO.setCommonId(lStrMjrHdCode);

			lArrMjrHd.add(lCmnVO);
		}
		return lArrMjrHd;
	}

	/*---------------------------New Methods----------------------------*/

	public Map getAllMjrHds(String lStrLangId, String lStrLocId,long liFinYr) {
		Session hibSession = getSession();
		ArrayList lArrMjrHd = null;
		String lStrDemand = null;
		String lStrMjrHd = null;
		String lStrMjrQry = null;
		HashMap lHmpDemMjrHd = new HashMap();

		StringBuffer lStrDemandQry = new StringBuffer();
		
		lStrDemandQry.append("select t.demand_code, t.budmjrhd_code																");
		lStrDemandQry.append("  from sgva_budmjrhd_mst t																		");
		lStrDemandQry.append(" where (t.budmjrhdtype = 'E' or t.budmjrhdtype = 'R') and											");
		lStrDemandQry.append("       t.demand_code not in ('A') and t.budmjrhd_code not in ('B') and							");
		lStrDemandQry.append("       t.lang_id = '"+lStrLangId+"' and t.loc_id = '"+lStrLocId+"' and t.status = 'Active' and	");
		lStrDemandQry.append("       t.fin_yr_id = "+liFinYr+"																	");
		lStrDemandQry.append(" order by t.demand_code, t.budmjrhd_code															");
		

		
		glogger
				.info("----Demand query in getAllMjrHds method of LcAdviceReceiptDAOImpl is :: "
						+ lStrDemandQry);

		Query lSQLQuery = hibSession.createSQLQuery(lStrDemandQry.toString());
		List lResList = lSQLQuery.list();


		glogger
				.info("------Size of demand list in getAllMjrHds method of LcAdviceReceiptDAOImpl is :: "
						+ lResList.size());

		Iterator lResIt = lResList.iterator();
		ArrayList arr = null;
		ArrayList lArrDemandList=new ArrayList();
		String lStrPrevKey = null;
		int i=1;
		while (lResIt.hasNext())
		{
			i++;
			Object[] lObjRow = (Object[]) lResIt.next();
			lStrDemand = lObjRow[0].toString();
			
			String MjrHd = lObjRow[1].toString();
			String key = lStrDemand;
			

			if (lStrPrevKey == null) 
			{
				lStrPrevKey = key;
				arr = new ArrayList();
				arr.add(MjrHd);
			} 
			else if (lStrPrevKey.equals(key)) 
			{
				arr.add(MjrHd);
			}
			else 
			{
				lHmpDemMjrHd.put(lStrPrevKey, arr);
				lArrDemandList.add(lStrPrevKey);
			
				
				arr = new ArrayList();
				lStrPrevKey = key;
				arr.add(MjrHd);
			}

		}
		lArrDemandList.add(lStrPrevKey);
		lHmpDemMjrHd.put(lStrPrevKey, arr);
		
		
		
		HashMap lHmpDemMHd = new HashMap();
		lHmpDemMHd.put("demandlist", lArrDemandList);
		lHmpDemMHd.put("mjrmap",lHmpDemMjrHd);
		return lHmpDemMHd;
	}

	public Map getAllSubMjrHds(String lStrLangId, String lStrLocId,long liFinYr) {

		glogger
				.info("================  Inside getAllSubMjrHds of LcAdviceReceiptDAOImpl class =============== ");

		Session hibSession = getSession();
		StringBuffer lsb = new StringBuffer();
		String lStrDemand = null;
		String lStrMjrHd = null;
		String lStrHmpKey = null;
		String lStrSubMjrHd = null;
		String lStrPrevKey = null;
		HashMap lHmpDemMjrSb = new HashMap();
	
		lsb.append("select t.demand_code,  					");
		lsb.append("   	   t.budmjrhd_code, 				");
		lsb.append("   	   t.budsubmjrhd_code 				");
		lsb.append("    from sgva_budsubmjrhd_mst t 		");
		lsb.append("    where t.lang_id = '"+lStrLangId+"' 	");
		lsb.append(" 		and t.loc_id = '"+lStrLocId+"' 	");
		lsb.append(" 		and t.demand_code not in ('A')  ");
		lsb.append("    and t.status = 'Active'            ");
		lsb.append("  		and t.budmjrhd_code not in('B') ");
		lsb.append("		and t.fin_yr_id="+liFinYr+"		");
		lsb.append(" order by t.demand_code,				");
		lsb.append("		 t.budmjrhd_code 				");
	

		try {
			Query lQry = hibSession.createSQLQuery(lsb.toString());

			glogger
			.info("----Sub Mjr Hd query in getAllSubMjrHds method of LcAdviceReceiptDAOImpl is :: "
					+ lsb);

			
			List lResLst = lQry.list();

			Iterator lResIt = lResLst.iterator();

			ArrayList lArrLst = new ArrayList();

			ArrayList arr = null;
			while (lResIt.hasNext()) {
				Object[] lObjRow = (Object[]) lResIt.next();
				lStrDemand = lObjRow[0].toString();
				lStrMjrHd = lObjRow[1].toString();
				String MjrHd = lObjRow[2].toString();
				String key = lStrDemand + lStrMjrHd;

				if (lStrPrevKey == null) {
					lStrPrevKey = key;
					arr = new ArrayList();
					arr.add(MjrHd);
				} else if (lStrPrevKey.equals(key)) {
					arr.add(MjrHd);
				} else {
					lHmpDemMjrSb.put(lStrPrevKey, arr);
					arr = new ArrayList();
					lStrPrevKey = key;
					arr.add(MjrHd);
				}

			}
			lHmpDemMjrSb.put(lStrPrevKey, arr);
		}

		catch (Exception e) {
			e.printStackTrace();
			glogger.error("Error :: " + e, e);
		}

		finally {
			return lHmpDemMjrSb;
		}

	}

	public Map getAllMinHds(String lStrLangId, String lStrLocId,long liFinYr) {

		glogger
				.info("================  Inside getAllMinHds of LcAdviceReceiptDAOImpl class =============== ");

		Session hibSession = getSession();
		StringBuffer lsb = new StringBuffer();
		String lStrDemand = null;
		String lStrMjrHd = null;
		String lStrHmpKey = null;
		String lStrSubMjrHd = null;
		String lStrPrevKey = null;
		HashMap lHmpDemMjrSb = new HashMap();

		lsb.append(" select mn.demand_code, 											");
		lsb.append(" 	mn.budmjrhd_code,												");
		lsb.append(" 	mn.budsubmjrhd_code, 											");
		lsb.append(" 	mn.budminhd_code  												");
		lsb.append(" from sgva_budminhd_mst mn 											");
		lsb.append(" where mn.lang_id = '"+lStrLangId+"'  								");
		lsb.append(" 	and mn.fin_yr_id = "+liFinYr+"  								");
		lsb.append(" 	and mn.loc_id = '"+lStrLocId+"'  								");
		lsb.append("    and mn.status = 'Active'         								");
		lsb.append(" 	and mn.demand_code not in ('A') and mn.budmjrhd_code not in('B')");
		lsb.append(" order by  mn.demand_code,  										");
		lsb.append(" 	mn.budmjrhd_code,  												");
		lsb.append(" 	mn.budsubmjrhd_code,  											");
		lsb.append(" 	mn.budminhd_code  												");

		try {
			Query lQry = hibSession.createSQLQuery(lsb.toString());

			List lResLst = lQry.list();

			Iterator lResIt = lResLst.iterator();

			ArrayList lArrLst = new ArrayList();

			ArrayList arr = null;
			while (lResIt.hasNext()) {
				Object[] lObjRow = (Object[]) lResIt.next();
				lStrDemand = lObjRow[0].toString();
				lStrMjrHd = lObjRow[1].toString();
				String lStrSbMjrHd = lObjRow[2].toString();
				String lStrMinHd = lObjRow[3].toString();

				String key = lStrDemand + lStrMjrHd + lStrSbMjrHd;
				
			
				if (lStrPrevKey == null) {
					lStrPrevKey = key;
					arr = new ArrayList();
					arr.add(lStrMinHd);
				} else if (lStrPrevKey.equals(key)) {
					arr.add(lStrMinHd);
				} else {
					lHmpDemMjrSb.put(lStrPrevKey, arr);
					arr = new ArrayList();
					lStrPrevKey = key;
					arr.add(lStrMinHd);
				}

			}
			lHmpDemMjrSb.put(lStrPrevKey, arr);
		}

		catch (Exception e) {
			e.printStackTrace();
			glogger.error("Error in Inside getAllMinHds of LcAdviceReceiptDAOImpl class  :: "+e,e);
		}

		finally {
			return lHmpDemMjrSb;
		}

	}
	
	public Map getAllSubHds(String lStrLangId, String lStrLocId,long liFinYr) {

		glogger
				.info("================  Inside getAllSubHds of LcAdviceReceiptDAOImpl class =============== ");

		Session hibSession = getSession();
		StringBuffer lsb = new StringBuffer();
		String lStrDemand = null;
		String lStrMjrHd = null;
		String lStrHmpKey = null;
		String lStrSubMjrHd = null;
		String lStrPrevKey = null;
		HashMap lHmpDemMjrSb = new HashMap();


		lsb.append(" 	select sh.demand_code, 											");
		lsb.append(" 		   sh.budmjrhd_code , 										");
		lsb.append(" 		   sh.budsubmjrhd_code, 									");
		lsb.append(" 		   sh.budminhd_code, 										");
		lsb.append(" 		   sh.budsubhd_code 										");
		lsb.append(" 	from sgva_budsubhd_mst sh 										"); 
		lsb.append(" 	where sh.lang_id='"+lStrLangId+"' 								");
		lsb.append(" 		and sh.fin_yr_id="+liFinYr+" 								");
		lsb.append("		and sh.loc_id='"+lStrLocId+"'								");
		lsb.append("    and sh.status = 'Active'         								");
		lsb.append(" 	and sh.demand_code not in ('A') and sh.budmjrhd_code not in('B')");
		lsb.append("  order by sh.demand_code, 											");
		lsb.append(" 	sh.budmjrhd_code , 												");
		lsb.append(" 	sh.budsubmjrhd_code, 											");
		lsb.append(" 	sh.budminhd_code, 												");
		lsb.append(" 	sh.budsubhd_code 												");

		try {
			Query lQry = hibSession.createSQLQuery(lsb.toString());

			List lResLst = lQry.list();

			Iterator lResIt = lResLst.iterator();

			ArrayList lArrLst = new ArrayList();

			ArrayList arr = null;
			while (lResIt.hasNext()) {
				Object[] lObjRow = (Object[]) lResIt.next();
				lStrDemand = lObjRow[0].toString();
				lStrMjrHd = lObjRow[1].toString();
				String lStrSbMjrHd = lObjRow[2].toString();
				String lStrMinHd = lObjRow[3].toString();
				String lStrSubHd = lObjRow[4].toString();
				

				String key = lStrDemand + lStrMjrHd + lStrSbMjrHd+lStrMinHd;

				if (lStrPrevKey == null) {
					lStrPrevKey = key;
					arr = new ArrayList();
					arr.add(lStrSubHd);
				} else if (lStrPrevKey.equals(key)) {
					arr.add(lStrSubHd);
				} else {
					lHmpDemMjrSb.put(lStrPrevKey, arr);
					arr = new ArrayList();
					lStrPrevKey = key;
					arr.add(lStrSubHd);
				}

			}
			lHmpDemMjrSb.put(lStrPrevKey, arr);
		}

		catch (Exception e) {
			e.printStackTrace();
			glogger.error("Error in Inside getAllSubHds of LcAdviceReceiptDAOImpl class  :: "+e,e);
		}

		finally {
			return lHmpDemMjrSb;
		}

	}

	public Map getEDPBudStructure(String lStrEDPCode)
	{
		Session hibSession = getSession();
		StringBuffer lsb = new StringBuffer();
		
		lsb.append("select t.budmjr_hd, ");
		lsb.append("t.budsubmjr_hd, ");
		lsb.append("t.budmin_hd, ");
		lsb.append(" t.budsub_hd, ");
		lsb.append("t.budobj_hd, ");
		lsb.append(" t.buddtl_hd ");
		lsb.append(" from mst_edp_receipt t ");
		lsb.append(" where t.edp_code = '"+lStrEDPCode+"' ");
		HashMap lHmpBudStr = new HashMap();
		try {
			Query lQry = hibSession.createSQLQuery(lsb.toString());

			List lResLst = lQry.list();

			Iterator lResIt = lResLst.iterator();

			
			while (lResIt.hasNext()) 
			{
				Object[] lObjRow = (Object[]) lResIt.next();
				lHmpBudStr.put("mjrhd", lObjRow[0]);
				lHmpBudStr.put("sbmjrhd", lObjRow[1]);
				lHmpBudStr.put("minhd", lObjRow[2]);
				lHmpBudStr.put("sbhd", lObjRow[3]);
				lHmpBudStr.put("objhd", lObjRow[4]);
				lHmpBudStr.put("dtlhd", lObjRow[5]);
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			glogger.error("Error in Inside getAllSubHds of LcAdviceReceiptDAOImpl class  :: "+e,e);
		}
		
		return lHmpBudStr;
	}
	public String getDeptCode(String lstrDeptId,long lLngLangId)
	{
		String lBDDeptCode="";
		
		Session hibSession = getSession();
		StringBuffer lsb = new StringBuffer();
		
		lsb.append("select d.loc_code from rlt_location_department d where d.department_id='"+lstrDeptId+"' and d.lang_id="+lLngLangId);		
		try {
			Query lQry = hibSession.createSQLQuery(lsb.toString());

			List lResLst = lQry.list();

			Iterator lResIt = lResLst.iterator();

			
			while (lResIt.hasNext()) 
			{
				lBDDeptCode = (String) lResIt.next();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			glogger.error("Error in Inside getDeptCode of LcAdviceReceiptDAOImpl class  :: "+e,e);
		}
		return lBDDeptCode;
	}
	
	public String getLoggedInTsryCode(String lStrDivCode,long lILangId) 
	{
		 glogger.info("---------DIVISION CODE IN DAO----------------"+lStrDivCode);
		 
		 Connection lCon = null;
	     PreparedStatement lStmt = null;
	     ResultSet lRs = null;
	     
	     String lStrTsryDeptId=bundleConst.getString("LC.TSRY_DEPT_ID");
	     
	     String lStrDeptId="";
	     String lStrTsryCode="";
	    
	     try
	     {
	     	 lCon = DBConnection.getConnection();
	         StringBuffer lSBuff = new StringBuffer();
	         
	         lSBuff.append("select loc.department_id ");	        
	         lSBuff.append("from cmn_location_mst loc ");
	         lSBuff.append("where loc.location_code='"+lStrDivCode+"' and loc.lang_id="+lILangId);         
	         
	         glogger.info("---------QUERY 1----------------"+lSBuff.toString());
	         
	         lStmt = lCon.prepareStatement( lSBuff.toString() );
	         lRs = lStmt.executeQuery();
	        
	         if(lRs.next())
	         {         	
	        	 if(lRs.getObject("department_id") != null)
	        		 lStrDeptId = lRs.getString("department_id");	        	
	         }
	         
	         lSBuff = new StringBuffer();
	         glogger.info("---------lStrDeptId----------------"+lStrDeptId);
	         if(lStrDeptId.equals(bundleConst.getString("LC.TSRY_DEPT_ID")))
	         {
	        	 glogger.info("---------IN IF --------------");
	        	 lSBuff.append("select loc.location_code from cmn_location_mst loc where loc.lang_id="+lILangId+" and loc.location_code='"+lStrDivCode+"' ");
	        	 glogger.info("---------QUERY 2----------------"+lSBuff.toString());
	         }
	         else if(lStrDeptId.equals(bundleConst.getString("LC.DIVISION_DEPT_ID")))
	         {
	        	 glogger.info("---------IN ELSE IF --------------");
	        	 lSBuff.append("select loc.location_code from cmn_location_mst loc where loc.lang_id="+lILangId+" and loc.department_id='"+lStrTsryDeptId+"' and loc.loc_district_id=(");
	        	 lSBuff.append("select loc.loc_district_id from cmn_location_mst loc where loc.lang_id="+lILangId+" and loc.location_code='"+lStrDivCode+"')");
	        	 glogger.info("---------QUERY 2----------------"+lSBuff.toString());
	         }
	         
        	 lStmt = lCon.prepareStatement( lSBuff.toString() );
             lRs = lStmt.executeQuery(); 
            	
        	 if(lRs.next())
             {         	
            	 if(lRs.getObject("location_code") != null)
            		 lStrTsryCode = lRs.getString("location_code");
            	
             } 
	     }
	     catch( SQLException se )
	     {
	         se.printStackTrace();
	     	glogger.error( "SQLException::"+se.getMessage(), se );
	        
	     }
	     catch( Exception e )
	     {
	         e.printStackTrace();
	     	glogger.error( "Exception::"+e.getMessage(), e );
	     }
	    glogger.info("---------LOGGED IUN TSRY CODE --------------"+lStrTsryCode);
		return lStrTsryCode;
	}
	
	public boolean getDivisionCodeVerification(String lStrLocationCode,String lStrDivCode,long lILangId) 
	{
		 glogger.info("---------DIVISION CODE IN DAO----------------"+lStrDivCode);
		 
		 Connection lCon = null;
	     PreparedStatement lStmt = null;
	     ResultSet lRs = null;
	     
	     String lStrTsryDeptId=bundleConst.getString("LC.TSRY_DEPT_ID");
	     String lStrDivDeptId=bundleConst.getString("LC.DIVISION_DEPT_ID");
	     
	     String lStrDeptId="";
	     String lStrTsryCode="";
	    
	     try
	     {
	     	 lCon = DBConnection.getConnection();
	         StringBuffer lSBuff = new StringBuffer();
	         
	         lSBuff.append("select loc.department_id ");	        
	         lSBuff.append("from cmn_location_mst loc ");
	         lSBuff.append("where loc.location_code='"+lStrLocationCode+"' and loc.lang_id="+lILangId);         
	         
	         glogger.info("---------QUERY 1----------------"+lSBuff.toString());
	         
	         lStmt = lCon.prepareStatement( lSBuff.toString() );
	         lRs = lStmt.executeQuery();
	        
	         if(lRs.next())
	         {         	
	        	 if(lRs.getObject("department_id") != null)
	        		 lStrDeptId = lRs.getString("department_id");	        	
	         }
	         
	         lSBuff = new StringBuffer();
	         glogger.info("---------lStrDeptId----------------"+lStrDeptId);
	         if(lStrDeptId.equals(bundleConst.getString("LC.TSRY_DEPT_ID")))
	         {
	        	 glogger.info("---------IN IF --------------");
	        	 lSBuff.append("select loc.location_code from cmn_location_mst loc ");
	        	 lSBuff.append("where loc.location_code='"+lStrDivCode+"' and loc.lang_id = "+lILangId+" and loc.department_id ="+lStrDivDeptId+" and ");
	        	 lSBuff.append("loc.loc_district_id = (select loc.loc_district_id from cmn_location_mst loc ");
	        	 lSBuff.append("where loc.lang_id ="+lILangId+" and loc.location_code = '"+lStrLocationCode+"') ");
	        	 glogger.info("---------QUERY 2----------------"+lSBuff.toString());
	        	 
	        	 lStmt = lCon.prepareStatement( lSBuff.toString() );
	             lRs = lStmt.executeQuery(); 
	            	
	        	 if(lRs.next())
	             {         	
	            	 if(lRs.getObject("location_code") != null)
	            		 lStrTsryCode = lRs.getString("location_code");	
	             } 
	        	 
	        	 if(!lStrTsryCode.equals(""))
	        		 return true;
	        	 else 
	        		 return false;
	         }
	         else if(lStrDeptId.equals(bundleConst.getString("LC.DIVISION_DEPT_ID")))
	         {
	        	 glogger.info("---------IN ELSE IF --------------");
	        	 if(lStrLocationCode.equals(lStrDivCode))
	        		 return true;
	        	 else
	        		 return false;
	        	 
	         }
	         
	         
	     }
	     catch( SQLException se )
	     {
	         se.printStackTrace();
	     	glogger.error( "SQLException::"+se.getMessage(), se );
	        
	     }
	     catch( Exception e )
	     {
	         e.printStackTrace();
	     	glogger.error( "Exception::"+e.getMessage(), e );
	     }
	     return false;
	}
	
	public String getChequeNoVerification(String lLngChqNo,String lStrDivCode,long lILangId) 
	{
		 glogger.info("---------DIVISION CODE IN DAO----------------"+lStrDivCode);
		 glogger.info("---------lLngChqNo IN DAO----------------"+lLngChqNo);
		 
		 Connection lCon = null;
	     PreparedStatement lStmt = null;
	     ResultSet lRs = null;
	     
	     String lStrChqNo="";
	     String lStrChqSrStrtNo="";
	     String lStrReturnStr="";

	    
	     try
	     {
	     	 lCon = DBConnection.getConnection();
	         StringBuffer lSBuff = new StringBuffer();
	         
	         lSBuff.append("select chq.cheque_no from trn_lc_cheque_posting chq ");	        
	         lSBuff.append("where chq.cheque_no='"+lLngChqNo+"'");	               

	         
	         glogger.info("---------QUERY 1----------------"+lSBuff.toString());
	         
	         lStmt = lCon.prepareStatement( lSBuff.toString() );
	         lRs = lStmt.executeQuery();
	        
	         if(lRs.next())
	         {         	
	        	 if(lRs.getObject("cheque_no") != null)
	        		 lStrChqNo = lRs.getString("cheque_no");	        	

	         }
	         
	         lSBuff = new StringBuffer();
	         glogger.info("---------lStrChqNo----------------"+lStrChqNo);
	         if(lStrChqNo.equals(""))
	         {
	        	 glogger.info("---------IN IF --------------");
	        	 lSBuff.append("select bk.chq_sr_start from ");
	        	 lSBuff.append("(select bk.chq_sr_start ,bk.chq_sr_end from trn_lc_cheque_book bk  ");
	        	 lSBuff.append("where bk.lc_acc_no = (select acc.lc_acc_no from mst_lc_division_account acc where acc.division_code='"+lStrDivCode+"') ");
	        	 lSBuff.append("order by bk.chq_sr_start,bk.chq_sr_end ) bk ");
	        	 lSBuff.append("where '"+lLngChqNo+"' between bk.chq_sr_start and bk.chq_sr_end ");
	        	 
	        	 glogger.info("---------QUERY 2----------------"+lSBuff.toString());
	        	 
	        	 lStmt = lCon.prepareStatement( lSBuff.toString() );
	             lRs = lStmt.executeQuery(); 
	            	
	        	 if(lRs.next())
	             {         	
	            	 if(lRs.getObject("chq_sr_start") != null)
	            		 lStrChqSrStrtNo = lRs.getString("chq_sr_start");	
	             } 
	        	 
	        	 if(lStrChqSrStrtNo.equals(""))
	        		 lStrReturnStr="InvalidSeries";
	        	 else 
	        		 lStrReturnStr="ValidSeries";
	         }
	         else 
	         {
	        	 glogger.info("---------IN ELSE IF --------------");
	        	 lStrReturnStr="AlreadyIssued";	        	 
	         }
	         
	         
	     }
	     catch( SQLException se )
	     {
	         se.printStackTrace();
	     	glogger.error( "SQLException::"+se.getMessage(), se );
	        
	     }
	     catch( Exception e )
	     {
	         e.printStackTrace();
	     	glogger.error( "Exception::"+e.getMessage(), e );
	     }
	     //System.out.println("----------CHQ VERIFICATION RETURN STR-------------"+lStrReturnStr);
	     return lStrReturnStr;
	}
	
	public String getAdviceNoVerification(String lStrAdviceNo,String lStrDivCode,long lILangId,long lIFinYrId) 
	{
		 glogger.info("---------DIVISION CODE IN DAO----------------"+lStrDivCode);
		 glogger.info("---------lStrAdviceNo IN DAO----------------"+lStrAdviceNo);
		 
		 Connection lCon = null;
	     PreparedStatement lStmt = null;
	     ResultSet lRs = null;
	     
	     String lStrAdviceNum="";
	     String lStrChqSrStrtNo="";
	     String lStrReturnStr="";

	    
	     try
	     {
	     	 lCon = DBConnection.getConnection();
	         StringBuffer lSBuff = new StringBuffer();
	         
	         lSBuff.append("select d.advice_no from trn_lc_dtl_posting d ");	        
	         lSBuff.append("where d.division_code='"+lStrDivCode+"' and d.advice_no='"+lStrAdviceNo+"' and d.fin_year_id="+lIFinYrId );	               

	         
	         glogger.info("---------QUERY 1----------------"+lSBuff.toString());
	         
	         lStmt = lCon.prepareStatement( lSBuff.toString() );
	         lRs = lStmt.executeQuery();
	        
	         if(lRs.next())
	         {         	
	        	 if(lRs.getObject("advice_no") != null)
	        		 lStrAdviceNum = lRs.getString("advice_no");	        	

	         }
	         
	         lSBuff = new StringBuffer();
	         glogger.info("---------lStrAdviceNum----------------"+lStrAdviceNum);
	         if(lStrAdviceNum.equals(""))
	         {
	        	 glogger.info("---------IN IF  --------------");
	        	 lStrReturnStr="ValidAdviceNo";
	         }
	         else 
	         {
	        	 glogger.info("---------IN ELSE  --------------");
	        	 lStrReturnStr="AlreadyIssued";	        	 
	         }
	         
	         
	     }
	     catch( SQLException se )
	     {
	         se.printStackTrace();
	     	glogger.error( "SQLException::"+se.getMessage(), se );
	        
	     }
	     catch( Exception e )
	     {
	         e.printStackTrace();
	     	glogger.error( "Exception::"+e.getMessage(), e );
	     }
	     //System.out.println("----------ADVICE VERIFICATION RETURN STR-------------"+lStrReturnStr);
	     return lStrReturnStr;
	}
}
