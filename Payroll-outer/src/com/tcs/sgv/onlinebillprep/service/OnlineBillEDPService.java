package com.tcs.sgv.onlinebillprep.service;

import java.util.Map;


/**
 * 
 * @author: Vipul Patel(602399)
 * 
 */

public interface OnlineBillEDPService
{
	 public void getEdpDtlsByBillType(Map objectArgs) throws Exception;
	 public void generateEdpDtlsMap(Map p_objServiceArgs) throws Exception;
	 public void insertEdpDtls(Map objectArgs) throws Exception;
	 public void deleteExtAddEdpCode(Map objectArgs) throws Exception;
}
