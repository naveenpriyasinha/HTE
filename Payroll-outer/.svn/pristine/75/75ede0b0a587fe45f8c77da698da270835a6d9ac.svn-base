/**
 * package : com.tcs.sgv.onlinebillprep.service 
 * @verion : 1.0
 * @author : Keyur Shah, Nirav Bumia 
 ** 
 */

package com.tcs.sgv.onlinebillprep.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAO;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;
import com.tcs.sgv.onlinebillprep.util.OnlineBillUtil;

/**
 * Its a VO Generator for setting values from entered Searched Criteria.
 * 
 * @author Keyur Shah, Nirav Bumia
 * @version 1.0
 */
public class BillRequestServiceVOGenerator extends ServiceImpl implements VOGeneratorService
{
	 /* Global Variable for Logger Class */
	Log gLogger = LogFactory.getLog(getClass());


	 /**
     * It generates the Map of the entered values by the user in the Search Approved Reaquest.
     * 
     * @param objectArgs
     *            ServiceMap
     * @return objRes ResultObject.
     */
	public ResultObject generateMap(Map objArgs)
    {
        gLogger.info("Going into generateMap of BillRequestServiceVOGenerator");

        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator servLoc = (ServiceLocator) objArgs.get("serviceLocator");

        HttpServletRequest request = (HttpServletRequest) objArgs.get("requestObj");

        Map<String, Object> lMapRqstData = new HashMap<String, Object>();

        String lStrFrmDt = OnlineBillUtil.requestSetter("txtFrmDt", request);
        String lStrToDt = OnlineBillUtil.requestSetter("txtToDt", request);
        Long lLngBillType = Long.parseLong(OnlineBillUtil.requestSetter("cmbBillType", request));
        Long lLngDept = Long.parseLong(OnlineBillUtil.requestSetter("cmbDept", request));
        
        String lStrEmpDesgn = OnlineBillUtil.requestSetter("txtEmpDsgn", request);
        String lStrEmpType = OnlineBillUtil.requestSetter("cmbEmpType", request);
        
        if(lStrEmpDesgn.equals("")){
            lStrEmpDesgn = null;
            
        }        
        if(lStrEmpType.equals("-1"))
        {
            lStrEmpType = null;  
        }
            
        SimpleDateFormat lObjSdFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date lDtFrmDt = null;
        Date lDtToDt = null;

        BillCommonDAO lObjBillDAO = new BillCommonDAOImpl(servLoc.getSessionFactory());
        SgvcFinYearMst lObjFinYr = new SgvcFinYearMst();

        try
        {
            if (lStrFrmDt.length() == 0)
            {
                lObjFinYr = lObjBillDAO
                        .getFinYrInfo(Calendar.getInstance().getTime(), SessionHelper.getLangId(objArgs));

                lDtFrmDt = lObjFinYr.getFromDate();
            }
            else
            {
                lDtFrmDt = lObjSdFormat.parse(lStrFrmDt);
            }

            if (lStrToDt.length() == 0)
            {
                lDtToDt = lObjSdFormat.parse(lObjSdFormat.format(Calendar.getInstance().getTime()));
            }
            else
            {
                lDtToDt = lObjSdFormat.parse(lStrToDt);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error in generateMap. Error is : " + e, e);
        }

        lMapRqstData.put("FrmDt", lDtFrmDt);
        lMapRqstData.put("ToDt", lDtToDt);
        lMapRqstData.put("BillType", lLngBillType);
        lMapRqstData.put("Dept", lLngDept);
        lMapRqstData.put("EmpType",lStrEmpType);
        lMapRqstData.put("EmpDesgn",lStrEmpDesgn );
        
        objArgs.put("ResultMap", lMapRqstData);
        objRes.setResultValue(objArgs);

        return objRes;
    }
}
