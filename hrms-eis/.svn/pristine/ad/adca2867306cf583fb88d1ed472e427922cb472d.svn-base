package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.PaySlipDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPayslip;
import com.tcs.sgv.pension.dao.TrnBillRegisterDAOImpl;

public class AddVoucherNoSrvc extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
public ResultObject insertVoucherNo(Map objServiceArgs)
	{
		
		logger.info("---------------inside insertVoucherNo-------------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{			
            ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
                    
            
            HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
            
            
            HttpSession session=request.getSession();		
            
    		/*Map loginDetails=(Map)objServiceArgs.get("baseLoginMap");
    		long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
            */
    		
            long billNo=(StringUtility.getParameter("billNo",request)!=null&&!(StringUtility.getParameter("billNo",request).equals(""))?Long.parseLong(StringUtility.getParameter("billNo",request)):0);
            String voucherNo = (StringUtility.getParameter("voucherNo",request)!=null&&!(StringUtility.getParameter("voucherNo",request).equals(""))?(StringUtility.getParameter("voucherNo",request)):" ");
            String voucherDate =(StringUtility.getParameter("voucherDate",request)!=null&&!(StringUtility.getParameter("voucherDate",request).equals(""))?(StringUtility.getParameter("voucherDate",request)):" ").toString();
           // logger.info("The voucherNo is-------->>>"+voucherNo);
            Date voucherDt=null;
            if(voucherDate!=null && !voucherDate.equals(" "))
    		{
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			voucherDt = sdf.parse(voucherDate);
    		}
            //.out.println("The date is----------->>>>>"+voucherDate);
            
            // Updation of the Trn_bill_register
            TrnBillRegister trnBillVO = new TrnBillRegister();
            TrnBillRegisterDAOImpl trnDao = new TrnBillRegisterDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
            
            trnBillVO = trnDao.read(billNo);
            trnBillVO.setTokenNum(Long.parseLong(voucherNo));
            trnBillVO.setUpdatedDate(voucherDt);
            
            trnDao.update(trnBillVO);
            String strBillNo = trnBillVO.getBillCntrlNo();
            //Updation of the Hr_pay_payslip
            // Added By Urvin Shah.
            logger.info("Bill No is:-"+billNo);
            logger.info("Bill No is:-"+strBillNo);
            HrPayPayslip payslipVO = new HrPayPayslip();
            PaySlipDAOImpl payslipDao = new PaySlipDAOImpl(HrPayPayslip.class,serv.getSessionFactory());
            logger.info("The Bill No is:-"+billNo);
            List<HrPayPayslip> lstPayslip = payslipDao.getListByColumnAndValue("billNo",strBillNo);
            //payslipVO = payslipDao.read(billNo);
            logger.info("The Size of the List is:-"+lstPayslip.size());
            if(lstPayslip != null && lstPayslip.size()>0) {
            	for(int i=0;i<lstPayslip.size();i++){
	            	HrPayPayslip hrPayPayslip = new HrPayPayslip();
	            	hrPayPayslip = lstPayslip.get(i);
	            	logger.info("The Payslip Id is:--"+hrPayPayslip.getPaySlipId());
	            	hrPayPayslip.setTokenNo(voucherNo);
	            	hrPayPayslip.setUpdatedDate(voucherDt);
	            	payslipDao.update(hrPayPayslip);
            	}
            }
            // Added By Urvin Shah.
            
            /*payslipVO.setTokenNo(voucherNo);
            payslipVO.setUpdatedDate(voucherDt);
            
            payslipDao.update(payslipVO);
            */
            
           
            //request.setAttribute("billStatus", "saved");
            objServiceArgs.put("MESSAGECODE",300006);
            //objServiceArgs.put("billStatus","saved");
            resultObject = serv.executeService("PAYBILL_PARA",objServiceArgs);
            
            resultObject.setResultCode(ErrorConstants.SUCCESS);
            //resultObject.setResultValue(objServiceArgs);
             //resultObject.setViewName("mySavedBills");
            
		}
		
		catch(Exception e)
		{		
			logger.error("Error is: "+ e.getMessage());		
		}
	
		return resultObject;
	}
	
	
	
	

}
