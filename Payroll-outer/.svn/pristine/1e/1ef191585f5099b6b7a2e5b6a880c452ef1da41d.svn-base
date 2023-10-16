package com.tcs.sgv.pension.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.service.OnlineBillVOGenerator;

public class PensionSpecificBillVOGenerator extends ServiceImpl implements VOGeneratorService
{
    public ResultObject generateMap(Map lMapInput)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        Log Logger = LogFactory.getLog(getClass());
        TrnBillRegister lObjBillRegister = null;
        try
        {
        	OnlineBillVOGenerator objOnlineBillVOGenerator = new OnlineBillVOGenerator();
        	objOnlineBillVOGenerator.generateMap(lMapInput);
        	HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        	lObjBillRegister = (TrnBillRegister)lMapInput.get("BillRegVO");
        	String lPnsnTokenNo =StringUtility.getParameter("txtPnsnTokenNo", request).trim();
        	String lStrCardexNo = StringUtility.getParameter("hidCardexNo", request);
        	if (lPnsnTokenNo.length() > 0)
            {
                lObjBillRegister.setTokenNum(Long.parseLong(lPnsnTokenNo));
            }
        	String lStrPPONO = StringUtility.getParameter("hidDPPONO", request).trim();
        	/*if (lStrPPONO.length() > 0)
            {
                lObjBillRegister.setPpoNo(lStrPPONO);
            }
        	lObjBillRegister.setPhyBill(DBConstants.CMN_PensionBillType);	
        	//Deep
            if((lObjBillRegister != null )&&(0 == lObjBillRegister.getBillNo()))
        	{
        		lObjBillRegister.setCurrBillStatus(DBConstants.ST_BAPRVD_DDO);
        	}
            lObjBillRegister.setExempted("Y");
        	lObjBillRegister.setReceivedFlag(new Short("1"));
        	lObjBillRegister.setTsryOfficeCode(SessionHelper.getLocationCode(lMapInput));
        	lObjBillRegister.setInwardedPost(SessionHelper.getPostId(lMapInput));
        	if(lStrCardexNo != null && lStrCardexNo.length() > 0)
        	{
        		lObjBillRegister.setCardexNo(Long.valueOf(lStrCardexNo));
        	}*/
        }
        catch (Exception e)
        {
        	Logger.error("Error is : " + e, e);
        }
        return objRes;
    }
}