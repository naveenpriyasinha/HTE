package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tcs.sgv.pension.valueobject.LifeCertificateVO;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;

public interface MstPensionerDtlsDAO 
{
	ArrayList<LifeCertificateVO> getLifeCertificateVOList(String lStrBankCode,String lStrBranchCode,String lStrPKList,BigDecimal lBDHeadCode,String lStrLocCode) throws Exception;
	Long getPnsionerDtlsIdFromPensionerCode(String pensionerCode);
	Long getPnsionerDtlsIdFromPensionerCode(String pnsnCode,String lStrStatus);
	List<MstPensionerDtls> getMstPensionerDtlsDiff(String lStrPnsnrCode) throws Exception;
	String getACCNo(String lStrBankCode,String lStrBranchCode,String lStrPensionerCode) throws Exception;
	String getACCNo(String lStrPensionerCode) throws Exception;
	List getAuditorAddress(String gStrLocCode,Long gLngLangId) throws Exception;
	String getBranchName(String lStrBranchCode,String lStrAuditorBankCode) throws Exception;
}
