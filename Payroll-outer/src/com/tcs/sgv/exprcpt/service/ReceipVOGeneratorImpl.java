package com.tcs.sgv.exprcpt.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnRcptBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.exprcpt.dao.ReceiptDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;

/**
*Class Name  ReceiptServiceImpl
*Description Implementation of Receipt Service  class
*Date of Creation 26 Jun 2006
*@Author Jignesh
*-----------------------------------------------------------------------------
* DATE        AUTHOR                    DESCRIPTION
* 27-jun-2007 Vimal            Challan detail posting & dss integration
* 22-Oct-2007 Vimal            Subtresury Challan Detail posting
*-----------------------------------------------------------------------------
*/

public class ReceipVOGeneratorImpl extends ServiceImpl implements VOGeneratorService 
{
  Log logger = LogFactory.getLog(getClass());

  /**
   * @param p_objServiceArgs
   * @return ResultObject
   */
  public ResultObject generateReceiptMap(Map<String,Object> p_objServiceArgs)
  {
    HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");

    ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
    try
    {
      Long receiptDetailId=null;			
      Integer isRecRev = null;
      Long tinNo = null;
      Long vendor = null;
      Integer tc = null;


      try { receiptDetailId = new Long(request.getParameter("receiptId")); } catch(Exception ex) {}
      try { isRecRev = new Integer(request.getParameter("isRecRev")); } catch(Exception ex){}
      try { tinNo = Long.parseLong(StringUtility.getParameter("txtPartyTinno", request)); } catch(Exception ex){}
      try { tc = new Integer(StringUtility.getParameter("cmbTc", request));} catch(Exception ex){}
      try { vendor = new Long(StringUtility.getParameter("txtVendorCode", request));}catch(Exception ex){}

      String receiptType=StringUtility.getParameter("cmbChallanType", request);
      String receiptDate=StringUtility.getParameter("txtDate", request);
      String rcvdByBankDate = StringUtility.getParameter("txtReceivedByBankDate", request);
      String amount=StringUtility.getParameter("txtChallanValue", request);
      String majorHead=StringUtility.getParameter("txtMajorHead1", request);
      String bankId=StringUtility.getParameter("hidtxtBank", request);
      String bankBranchId=StringUtility.getParameter("hidtxtBranch", request);
      String depositorName=StringUtility.getParameter("txtPartyName", request);
      String taxType=StringUtility.getParameter("cmbTypeOfText", request);
      String cardexNo=StringUtility.getParameter("cmbCardex", request);
      String demand = StringUtility.getParameter("cmbDemand", request);
      String budType = StringUtility.getParameter("cmbBudjet", request);
      String fund = StringUtility.getParameter("cmbFund", request);
      String clsExp=StringUtility.getParameter("cmbExpClass", request);
      String schemeNo=StringUtility.getParameter("txtScheme", request);
      String ddocode=StringUtility.getParameter("cmbDdoName", request);
      String Perticulars=StringUtility.getParameter("txtParticulars", request);
      String accNo=StringUtility.getParameter("txtAccountNo", request);
      String dedMonth =StringUtility.getParameter("txtDeductionMonth", request);
      String fromPeriod=StringUtility.getParameter("txtFromPeriod", request);
      String toPeriod =StringUtility.getParameter("txtToPeriod", request);
      String salesTaxNo=StringUtility.getParameter("txtSalesTaxNo", request);
      String lcAccCode=StringUtility.getParameter("txtLciAccCode", request);
      String receiptNo=StringUtility.getParameter("txtReceiptNo", request);
      String subTreasury=StringUtility.getParameter("cmbSubTrsy", request);


      //System.out.println("Inside map receipt id " + receiptDetailId);
      //System.out.println("Inside map rec rev " + isRecRev);

      ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
      TrnReceiptDetails rcptVO = null;
      /* if receipt detail id is then read object from the system else create new instance */
      if(receiptDetailId != null)
      {
        rcptVO = receiptDAO.read(receiptDetailId);
        rcptVO.setUpdatedUserId(SessionHelper.getUserId(request));
        rcptVO.setUpdatedPostId(SessionHelper.getPostId(p_objServiceArgs));
        rcptVO.setUpdatedDate(new Date(System.currentTimeMillis()));
      }
      else
      {				
        rcptVO = new TrnReceiptDetails();
        rcptVO.setTrnCounter(new Integer(1));
        rcptVO.setCreatedUserId(SessionHelper.getUserId(request));
        rcptVO.setCreatedPostId(SessionHelper.getPostId(p_objServiceArgs));
        rcptVO.setCreatedDate(new Date(System.currentTimeMillis()));
        BillCommonDAOImpl billDAO = new BillCommonDAOImpl(serv.getSessionFactory());
        SgvcFinYearMst finDAO = billDAO.getFinYrInfo(new Date(System.currentTimeMillis()), SessionHelper.getLangId(p_objServiceArgs));
        rcptVO.setFinYearId(finDAO.getFinYearId());  // get from some methods .. 
        //System.out.println("trn_receipt_Details : " + rcptVO.getReceiptDetailId());
      }

      rcptVO.setReceiptNo(receiptNo);			
      rcptVO.setReceiptType(receiptType);
      rcptVO.setAmount(new BigDecimal(amount));	
      rcptVO.setMajorHead(majorHead);
      rcptVO.setBankCode(bankId);
      rcptVO.setBankBranchCode(bankBranchId);
      rcptVO.setSalesTaxNo(salesTaxNo);
      rcptVO.setLcAccCode(lcAccCode);
      rcptVO.setDepositorName(depositorName);
      rcptVO.setTaxType(taxType);
      rcptVO.setVendorId(vendor);
      if(ddocode != null && !ddocode.equals("-1"))
      {
        rcptVO.setDdoCode(ddocode);
      }
      rcptVO.setPerticulars(Perticulars);
      rcptVO.setAccNo(accNo);
      if(budType != null && !budType.equals("-1"))rcptVO.setBudType(budType);//budType
      if(demand != null && !demand.equals("-1"))rcptVO.setDemand(demand);
      if(fund != null && !fund.equals("-1"))rcptVO.setFund(fund);//fund
      if(clsExp != null && !clsExp.equals("-1"))rcptVO.setClsExp(clsExp);
      if(cardexNo != null && !cardexNo.equals("-1"))rcptVO.setCardexNo(cardexNo);


      rcptVO.setTc(tc);
      rcptVO.setSchemeNo(schemeNo);
      //System.out.println("loc code in vo == " + subTreasury);
      if(subTreasury != null && subTreasury != "" && !subTreasury.equals("-1"))
      {
        rcptVO.setLocationCode(subTreasury);
      }
      else
      {
        rcptVO.setLocationCode(SessionHelper.getLocationCode(p_objServiceArgs));
      }
      rcptVO.setDbId(SessionHelper.getDbId(p_objServiceArgs));
      rcptVO.setTinNo(tinNo);
      rcptVO.setRecRev(isRecRev);

      try { rcptVO.setReceiptDate(new SimpleDateFormat("dd/MM/yyyy").parse(receiptDate)); } catch(Exception ex){}
      try { rcptVO.setRcvdByBankDate(new SimpleDateFormat("dd/MM/yyyy").parse(rcvdByBankDate)); } catch(Exception ex){}
      try { rcptVO.setDedMonth(new SimpleDateFormat("dd/MM/yyyy").parse(dedMonth)); } catch(Exception ex){}
      try { rcptVO.setFromPeriod(new SimpleDateFormat("dd/MM/yyyy").parse(fromPeriod)); } catch(Exception ex){}
      try { rcptVO.setToPeriod(new SimpleDateFormat("dd/MM/yyyy").parse(toPeriod)); } catch(Exception ex){}


      p_objServiceArgs.put("rcptVO", rcptVO);			
      retObj.setResultValue(p_objServiceArgs);

      return retObj;			
    }
    catch(Exception e)
    {			
      retObj.setThrowable(e);
      retObj.setResultCode(-1);
      retObj.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in ReceipVOGeneratorImpl.generateReceiptMap # \n"+e);
      return retObj;
    }

  }

  /**
   * @param p_objServiceArgs
   * @return ResultObject
   */
  public ResultObject generateReceiptBudHdMap(Map<String,Object> p_objServiceArgs)
  {
    HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");
    ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
    try
    {
      TrnReceiptDetails rcptVO = (TrnReceiptDetails) p_objServiceArgs.get("rcptVO");
      //System.out.println("trn_receipt_Details : 3" + rcptVO.getReceiptDetailId());
      Long receiptId=null;			
      try { receiptId = rcptVO.getReceiptDetailId(); } catch(Exception ex) {}
      Integer isRecRev = null;
      try{isRecRev = rcptVO.getRecRev();}catch(Exception ex){}
      //System.out.println("Inside bud head rec rev= "+ isRecRev);
      String[] majorHeads=StringUtility.getParameterValues("txtMajorHead", request);
      String[] subMajorHeads=StringUtility.getParameterValues("txtSubMajorHead", request);
      String[] minorHeads=StringUtility.getParameterValues("txtMinorHead", request);
      String[] subHeads=StringUtility.getParameterValues("txtSubHead", request);
      String[] sbHdAmt=StringUtility.getParameterValues("txtAmount", request);
      String detailHead[] = null;
      String objectCode[] = null;
      String edpCode[] = null;
      if(isRecRev != null && isRecRev == 0)
      {
        detailHead = StringUtility.getParameterValues("txtDetailHead", request);
        objectCode = StringUtility.getParameterValues("txtObjectCode", request);
        edpCode = StringUtility.getParameterValues("txtEdpCode", request);
      }
      List<TrnRcptBudheadDtls> rcptBudDtls = new ArrayList<TrnRcptBudheadDtls>();
      for(int i=0; i< majorHeads.length; i++)
      {
        TrnRcptBudheadDtls rcptbudVO = new TrnRcptBudheadDtls();
        rcptbudVO.setReceiptDetailId(receiptId);
        rcptbudVO.setBudMajorHead(majorHeads[i]);
        rcptbudVO.setBudMinHead(minorHeads[i]);
        rcptbudVO.setBudSubmjrHead(subMajorHeads[i]);
        rcptbudVO.setBudSubHead(subHeads[i]);
        try
        {
          rcptbudVO.setBudDetailHead(detailHead[i]);
          rcptbudVO.setBudObjectCode(objectCode[i]);
          rcptbudVO.setBudEdpCode(edpCode[i]);
        }catch(Exception ex){}
        try{rcptbudVO.setAmount(new BigDecimal(sbHdAmt[i]));}catch(Exception ex){}
        rcptbudVO.setCreatedUserId(SessionHelper.getUserId(request));
        rcptbudVO.setCreatedPostId(SessionHelper.getPostId(p_objServiceArgs));
        rcptbudVO.setCreatedDate(new Date(System.currentTimeMillis()));
        rcptbudVO.setLocationCode(SessionHelper.getLocationCode(p_objServiceArgs));
        rcptbudVO.setDbId(SessionHelper.getDbId(p_objServiceArgs));
        BillCommonDAOImpl billDAO = new BillCommonDAOImpl(serv.getSessionFactory());
        SgvcFinYearMst finDAO = billDAO.getFinYrInfo(new Date(System.currentTimeMillis()), SessionHelper.getLangId(p_objServiceArgs));
        rcptbudVO.setFinYearId(finDAO.getFinYearId());
        rcptbudVO.setUpdatedUserId(SessionHelper.getUserId(request));
        rcptbudVO.setUpdatedPostId(SessionHelper.getPostId(p_objServiceArgs));
        rcptbudVO.setUpdatedDate(new Date(System.currentTimeMillis()));
        rcptBudDtls.add(rcptbudVO);
      }
      //System.out.println("End of vo");
      //System.out.println("trn_receipt_Details : 4" + rcptVO.getReceiptDetailId());
      p_objServiceArgs.put("rcptBudDtls", rcptBudDtls);
      resObj.setResultValue(p_objServiceArgs);
    }
    catch(Exception ex)
    {
      resObj = new ResultObject(ErrorConstants.ERROR);
      resObj.setThrowable(ex);
      resObj.setResultCode(-1);
      resObj.setViewName("errorPage");
      ex.printStackTrace();
      logger.error("Exception occured in ReceipVOGeneratorImpl.generateReceiptBudHdMap # \n"+ex);
      return resObj;
    }
    return resObj;
  }

  public ResultObject generateMap(Map arg0)
  {
    // TODO Auto-generated method stub
    return null;
  }


}
