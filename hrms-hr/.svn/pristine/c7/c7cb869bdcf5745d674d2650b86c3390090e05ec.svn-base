package com.tcs.sgv.hr.payfixation.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;

public interface PayFixationDao extends GenericDao{

	/**
	 * to obtain details from HrEisScaleMst for given empid
	 * @param empId
	 * @param langId
	 * @return
	 */
	public  List Scale(long empId, long langId);

	

	/**
	 * to obtain details from HrEssTranPayFix where payfixdate between startdate and enddate for given desig id
	 * @param startdate
	 * @param enddate
	 * @param desigid
	 * @return
	 */

	public  List SearchByPayFixDate(String startdate, String enddate);

	/**
	 * to obtain details from HrEssTranPayFix where incrementdate between startdate and enddate for given desig id
	 * @param startdate
	 * @param enddate
	 * @param desigid
	 * @return
	 */
	public  List SearchByIncrementDate(String startdate, String enddate);


	
	
	/**
	 * to obtain details from HrEssTranPayFix for given empid
	 * @param empId
	 * @param langId
	 * @return
	 */
	public  List Fixation(long userID, long langId);

	/**
	 * to obtain details from CmnLookupMst for given lookupid
	 * @param reaspayfixid
	 * @return
	 */
	public  List ReasonPayFix(long reaspayfixid);

	/**
	 * to obtain details from HrEisScaleMst for given scaleId
	 * @param revisedscaleid
	 * @return
	 */
	public  List Amount(long revisedscaleid);

	/**
	 * for updation of column in HrEssTranPayFix for given payfixid.
	 * @param payfixid
	 */
	public  void UpdateRulepayincrdate(long payfixid);

	/**
	 * for updation of column in HrEssTranPayFix for given payfixid.
	 * @param payfixid
	 */
	public  void UpdateRulepayfixdate(long payfixid);

	/**
	 * to obtain all details from OrgDesignationMst.
	 * @return
	 */
	public  List getAllDesgMasterData();

	/**
	 * to obtain all details from HrEssTranPayFix for given payfixid
	 * @param payFixId
	 * @return
	 */
	public  List Getalldatafromhrtrans(long payFixId);

	public  List Getalldatafromhrpayreqdtls(long reqId);

	/**
	 * to obtain all details from HrEisPayInc for given empId
	 * @param empId
	 * @return
	 */
	public  List Getalldatafromhrpayincr(long userid);

	/**
	 * for updation of column in HrEssPayfixReqDtls during approval when syscomp.
	 * 
	 */

	public  void Updateapprovalcomp(long reqid);

	/**
	 * for updation of column in HrEssPayfixReqDtls during approval when usercomp.
	 * 
	 */
	public  void Updateapprovalusercomp(long payfixed, Date nxtdate,
			String expl, long reqid);

	/**
	 * for updation of column in HrEisPayInc for given empId.
	 * 
	 */

	public  void UpdateIncrdateinHrPayIncr(long userId,
			Date incrementdate);

	/**
	 * to find whether same empid exists in HrPayfixMst.
	 * 
	 */

	public  List Resultwhetherempexists(long userid);

	public  void Updatereject(long reqid);

	
	public  void Updateforwardusercomp(long payfixed, Date nxtdate,
			String expl, long reqid);

	public  List getScaleName(long scalestrtamt);

	public  long Getalldatafromorgemp(long empId);

	public  List Getorgempbyuserid(long userId, long langId);

	public  long Getgujemp(long userid);

	public  List Getalldatafromhrmodemprlt(long reqId);
	
	public long getHigherHierachyPostId(String postId, long docId);

	/* Added by Sandip - Starts. */
	public HrPayfixMst getPayFixListByUserIdandActiveFlag(long usreId,char flag);
	public List<HrPayfixMst> getPayFixDtlVOList(long userId);
	public List<HrEisScaleMst> getPayScaleListByCrntScale(long startAmt);
	/* Added by Sandip - Ends. */
}