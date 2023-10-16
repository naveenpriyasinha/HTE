/**
 * @author Ravish Tank 601344
 * 
 */
package com.tcs.sgv.eis.service;

/*Import of the Packages*/
import java.util.Map;
import com.tcs.sgv.core.valueobject.ResultObject;

public interface BankMasterService {
	
	public ResultObject insertBankMasterDtls(Map objectArgs);
	public ResultObject getBankMasterDtls(Map objectArgs);
	public ResultObject checkBankName(Map objectArgs);
}
