/**
 * package : com.tcs.sgv.onlinebillprep.service 
 * @verion : 1.0
 * @author : Keyur Shah, Amit Singh
 ** 
 */

package com.tcs.sgv.onlinebillprep.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.valueobject.SgvoDeptMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAO;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;

/**
 * Its a service class for Methods Common to all Bills.
 * 
 * @author Keyur Shah, Amit Singh
 * @version 1.0
 */
public class BillCommonServiceImpl extends ServiceImpl implements BillCommonService
{
	 /* Global Variable for Logger Class */
	Log gLogger = LogFactory.getLog(getClass());


    /**
     * It gets the Department Details by Department Id and Lang Id.
     * 
     * @param inputMap
     *            InputMap.
     * @return objRes ResultObject
     */
    public ResultObject getDeptDtls(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

            String lStrDeptId = (String) inputMap.get("DeptId");
            Long lLngLangId = (Long) inputMap.get("LangId");

            BillCommonDAO lBillComDAO = new BillCommonDAOImpl(serv.getSessionFactory());
            SgvoDeptMst lDeptMstVO = lBillComDAO.getDeptDtls(lStrDeptId, lLngLangId);

            Map lMap = new HashMap();
            lMap.put("DeptMstVO", lDeptMstVO);

            inputMap.put("ResultMap", lMap);

            objRes.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("Error in getDeptDtls and Error is : " + e, e);
        }
        return objRes;
    }

    /**
     * It gets the Month Details by  Lang Id.
     * 
     * @param inputMap
     *            InputMap.
     * @return objRes ResultObject
     */
    public ResultObject getMonthDtls(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

            String lStrDeptId = (String) inputMap.get("DeptId");
            Long lLngLangId = (Long) inputMap.get("LangId");

            BillCommonDAO lBillComDAO = new BillCommonDAOImpl(serv.getSessionFactory());
            List lMonList = lBillComDAO.getMonthDtls(lLngLangId);

            Map lMap = new HashMap();
            lMap.put("MonthList", lMonList);

            inputMap.put("ResultMap", lMap);

            objRes.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("Error in getMonthDtls and Error is : " + e, e);
        }

        return objRes;
    }
}
