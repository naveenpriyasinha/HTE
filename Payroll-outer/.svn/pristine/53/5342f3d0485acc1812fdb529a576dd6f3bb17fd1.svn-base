package com.tcs.sgv.pdpla.service;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pdpla.valueobject.MstPdChqInventory;

public class PDChqInventoryVOGenerator extends ServiceImpl implements VOGeneratorService
{
	Log glogger = LogFactory.getLog(getClass());

	/**
     * It generates Cheque Inventory VO(PDChqInvtVO).
     *  
     * @author Sandeep
     * @version 1.0
     * @return objRes ResultObject
     */
	
	public ResultObject generateMap(Map objArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

       // setSessionInfo(objArgs);

        MstPdChqInventory lObjMstPdChqInvt = generatePDchqInvtMstVO(objArgs);
        objArgs.put("MstPDChqInventory", lObjMstPdChqInvt);
        
        objRes.setResultValue(objArgs);
        return objRes;
    }
	
	private MstPdChqInventory generatePDchqInvtMstVO(Map lMapInput)
	{
		
		MstPdChqInventory lMstPdVO = new MstPdChqInventory();	
	
		try{
		
		HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
		ServiceLocator servLoc = (ServiceLocator)lMapInput.get("serviceLocator"); 
		
		
		// DAO object to be created .............
		
		
		//setters
		
		//to set the date we write the following code:
		/**Date dtBillPassedDt = null;
            String lStrBillPassedDt = null;
            DateFormat lObjSdFormat = new SimpleDateFormat("dd/MM/yyyy");

            if (requestSetter("txtBillPassedDate", request).trim().length() > 0)
            {
                lStrBillPassedDt = requestSetter("txtBillPassedDate", request);
                dtBillPassedDt = lObjSdFormat.parse(lStrBillPassedDt);

                lObjHdrVO.setBillPassedDate(dtBillPassedDt);
            }**/
	}
	
    catch (Exception e)
    {
    	glogger.error("Error in generatePDchqInvtMstVO. Error is : " + e, e);
    }

    return lMstPdVO;
}
}
