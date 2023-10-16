package com.tcs.sgv.pension.dao;

import java.util.List;

import com.tcs.sgv.pension.valueobject.MstPensionerHdr;

public interface MstPensionerHdrDAO 
{
	String getName(String lStrPensionerCode) throws Exception;
	String getPANNo(String lStrPensionerCode) throws Exception;	
	Long getPensionerIdfromPensnrCode(String lStrPnsnrCode,String lStrCaseStatus) throws Exception;
	List<MstPensionerHdr> getMstPensionerHdrDiff(String lStrPnsnrCode) throws Exception;
	MstPensionerHdr getMstPensionerHdrDtls(String lStrPensionerCode) throws Exception;
	List getMRRelatedInfo(String lStrPPONO,String lStrCaseStatus,String gStrLocationCode) throws Exception;
	String getDesigName(long lStrDesignation) throws Exception;
	
}
