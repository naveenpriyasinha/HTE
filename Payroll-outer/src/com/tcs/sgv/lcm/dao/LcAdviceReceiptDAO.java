package com.tcs.sgv.lcm.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcBudgetPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcChequePosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDtlPosting;

public interface LcAdviceReceiptDAO {

/*	public List getMonthDtls(int lILangId);*/
	public String getStrLangId(long lILangId);
	public LcDivisionInformationVO getDivisionInformation(String lStrDivCode,long lILangId);
	public String getParentPostId(String lLngLoggedInPostId,int lILangId);
/*	public List getClassOfExp(int lILangId);*/
	public String getLcValidityDate(String liDivId);
	public String getDivisionAccountNo(String liDivId,int lILangId);
/*	public List getFundType(int lILangId); */
/*	public List getBudgetType(int lILangId);*/
	public List getMjrHdByDemand(String lStrDemandNo,String lStrLangId);
	public boolean saveLcDtlPosting(TrnLcDtlPosting lObjLcDtlPostingVo);
	public boolean saveLcChequePosting(TrnLcChequePosting lcChequePostingVO);
	public boolean saveLcBudgetPosting(TrnLcBudgetPosting lcBudgetPostingVO);
	public Map getAllMjrHds(String lStrLangId,String lStrLocId,long liFinYr);
	public Map getAllSubMjrHds(String lStrLangId,String lStrLocId,long liFinYr);
	public Map getAllMinHds(String lStrLangId,String lStrLocId,long liFinYr);
	public Map getAllSubHds(String lStrLangId, String lStrLocId,long liFinYr);
}
