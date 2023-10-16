package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.Date;

import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionerRivisionDtls;

public interface RevisionDAO {

	public TrnPensionRqstHdr getTrnPensionRqstHdrDtls(String lStrPPONO, String lStrStatus, String lStrCaseStatus) throws Exception;
	public ArrayList<TrnPensionerRivisionDtls> getTrnPensionerRivisionDtls(String lStrPensionerCode) throws Exception;
	public Long getCaseIdfromPpoNo(String ppoNo,String Status) throws Exception;
    public Date getLastRevisionDate(String lPnsnerCode) throws Exception;
}
