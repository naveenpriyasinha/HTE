/**
 * 
 */
package com.tcs.sgv.common.service;

import java.util.Map;

import com.tcs.sgv.core.service.Service;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * @author 157045
 *
 */
public interface IFMSCommonService  extends Service{
	
	public ResultObject seeUserManual(Map inputMap);	
	
}
