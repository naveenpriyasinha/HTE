package com.tcs.sgv.pension.service;

import java.util.Map;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * Recovery Service Interface.
 * 
 * @author Rupsa Mukherjee
 * @version 1.0
 */

public interface RecoveryService
{
    public ResultObject getRecoveryDtls(Map inputMap);


    public ResultObject saveRecoveryDtls(Map inputMap);


    // for AJAX use
    public ResultObject getRecoveryTypeInfo(Map inputMap);
}
