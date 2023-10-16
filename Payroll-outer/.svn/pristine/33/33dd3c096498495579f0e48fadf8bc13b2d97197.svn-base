package com.tcs.sgv.pdpla.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pdpla.valueobject.MstPdAccount;

public class DepositAccMstVOGenerator extends ServiceImpl implements VOGeneratorService
{
	Log gLogger = LogFactory.getLog(getClass());

	/**
     * It generates PD A/c Mst VO(PDAccMstVO).
     *  
     * @author Sandeep
     * @version 1.0
     * @return objRes ResultObject
     */
	
    public ResultObject generateMap(Map objArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        
        MstPdAccount lObjMstPdAcc = generatePDAccMstVO(objArgs);
        objArgs.put("MstPdAccount", lObjMstPdAcc);
        
        objRes.setResultValue(objArgs);
        return objRes;
    }
	
	private MstPdAccount generatePDAccMstVO(Map lMapInput)
	{
		HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
		MstPdAccount lMstPdVO = new MstPdAccount();
		DateFormat gObjSdFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try
		{
			lMstPdVO.setAccountNo(StringUtility.getParameter("txtPdPlaAccNo", request));
			lMstPdVO.setCardexNo(Integer.parseInt(StringUtility.getParameter("txtCardexNo", request)));
			lMstPdVO.setDeptName(StringUtility.getParameter("txtDeptName", request));
			lMstPdVO.setSanctionNo(StringUtility.getParameter("txtAgFdSenctNo", request));
			lMstPdVO.setPdMjrhd(StringUtility.getParameter("txtMajorHead", request));
			lMstPdVO.setPdMinhd(StringUtility.getParameter("txtMinorHead", request));
			lMstPdVO.setPdSubMjrhd(StringUtility.getParameter("txtSubMajorHead", request));
			lMstPdVO.setPdSubhd(StringUtility.getParameter("txtSubHead", request));
			lMstPdVO.setOpeningBl(BigDecimal.valueOf(Long.parseLong(StringUtility.getParameter("txtOpBal", request))));
			////System.out.println("############ 9 ###########");
			lMstPdVO.setGroupId(StringUtility.getParameter("txtGrp", request));
			////System.out.println("############ 10 ###########");
			
			//	to set the date
			Date dtAcStartDate = null;
			String lStrAcStartDate = null;
			
			if (StringUtility.getParameter("txtAcStartDate", request).trim().length() > 0)
			{
				lStrAcStartDate = StringUtility.getParameter("txtAcStartDate", request);
				dtAcStartDate = gObjSdFormat.parse(lStrAcStartDate);
				lMstPdVO.setStartDate(dtAcStartDate);
			}
            
			Date dtAcEndDate = null;
			String lStrAcEndDate = null;
			if (StringUtility.getParameter("txtAcEndDate", request).trim().length() > 0)
			{
				lStrAcEndDate = StringUtility.getParameter("txtAcEndDate", request);
				dtAcEndDate = gObjSdFormat.parse(lStrAcEndDate);
				lMstPdVO.setEndDate(dtAcEndDate);
			}	
			
			Date dtAgFdSenctDate = null;
			String lStrAgFdSenctDate = null;
			if (StringUtility.getParameter("txtAgFdSenctDate", request).trim().length() > 0)
			{
				lStrAgFdSenctDate = StringUtility.getParameter("txtAgFdSenctDate", request);
				dtAgFdSenctDate = gObjSdFormat.parse(lStrAgFdSenctDate);
				lMstPdVO.setSanctionDate(dtAgFdSenctDate);
			}
		}
		catch (ParseException pe)
	    {
	        gLogger.error("Error in Parsing the Date in generatePDAccMstVO. Error is : " + pe, pe);
	    }
	    catch (Exception e)
	    {
	        gLogger.error("Error in generatePDAccMstVO. Error is : " + e, e);
	    }
                
    return lMstPdVO;      
	}
}
	
