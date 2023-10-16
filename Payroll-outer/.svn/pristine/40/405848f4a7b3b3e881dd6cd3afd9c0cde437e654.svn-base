package com.tcs.sgv.pension.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.common.valueobject.MstEdp;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

/**
 *  TrnPensionRqstHdr specific Service Interface
 * 
 * @author Rupsa Mukherjee
 * @version 1.0
 */

public interface TrnPensionRqstHdrDAO extends GenericDao<TrnPensionRqstHdr, Long>
{	
	Long getPensionRqstHdrIdByPnsnrCode(String pensionerCode) throws Exception;
	List getPksForTrnPensionRqstHdr(String lStrStatus,String lStrPpoNo) throws Exception ;
	String getPPONo(String lStrPensionerCode) throws Exception;
	TrnPensionRqstHdr getTrnPensionRqstHdrVO(long lBDPenReqId,String lStrPenCode) throws Exception;
	String validatePPONumberForScheme(String PPONO) throws Exception;
	String validatePPONumberForBank(String PPONO,String branchCode) throws Exception;
	Map getPaymentDetails(String PPONO) throws Exception;
	List getPensionRqstHdrCodeId(String lStrPPONo,String lStrStatus) throws Exception;
	TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPPONo(String lStrPPONO, String lStrStatus, String lStrCaseStatus) throws Exception;
	TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPnsnerCode(String lStrPnsrCode, String lStrStatus, String lStrCaseStatus) throws Exception;
	TrnPensionRqstHdr getTrnPensionRqstHdrVO(String lStrPPONo) throws Exception;
	MstEdp getMstEdpVO(String lStrEdpCode, Long gLngLangId) throws Exception;
	Map getActualPaymentDetails(long lLngPensionReqId,String lStrPensionerCode)throws Exception;
	TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPnsnerCode(String lStrPnsrCode,String lStrCaseStatus) throws Exception;
	TrnPensionRqstHdr getTrnPensionRqstHdrVOforApproved(String lStrPPONo,String lStrCaseStatus) throws Exception;
	
}
