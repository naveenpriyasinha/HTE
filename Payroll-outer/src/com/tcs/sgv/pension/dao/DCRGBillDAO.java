package com.tcs.sgv.pension.dao;

import java.util.ArrayList;

import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;


public interface DCRGBillDAO 
{
	public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPPONo(String lStrPPONO, String lStrStatus) throws Exception;
	public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPnsnerCode(String lStrPnsrCode, String lStrStatus) throws Exception;
	public MstPensionerHdr getMstPensionerHdrDtls(String lStrPensionerCode) throws Exception;
	public ArrayList<TrnPensionRecoveryDtls> getTrnPensionRecoveryDtls(String lStrPensionerCode, String lStrStatus) throws Exception;
	public TrnPensionBillDtls getTrnPensionBillDtls(String lStrBillNo) throws Exception;
	
}
