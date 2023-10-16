package com.tcs.sgv.pensionpay.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


public interface AdminRateMstDAO {

	List getDARateDetails(String lStrDARateType, String lStrHeadCodeType, String lStrForPension);

	String chkDateIsOverLapOrNot(String lStrDARateType, String lStrForPension, String lStrHeadCodeType, Date lDtFromDate, Date lDtToDate, Date lDtEffctvFromDate, Date lDtEffctvToDate);

	Integer getDARateConfigForStateCount(Long gLngLangId, String gStrLocCode, Map displayTag) throws Exception;

	List getDARateConfigForStateDtls(Long gLngLangId, String gStrLocCode, Map displayTag) throws Exception;

	Long getMaxStateCode(Long lLngLangId, Long lLngLocationCode);

	List getAllStateDept(Long gLngLangId) throws Exception;
	
	BigDecimal getMaxRevision(Long lLngHeadCode, String lStrFieldType) throws Exception;
}
