package com.tcs.sgv.exprcpt.helper;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.valueobject.MstEdp;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillEdpDtls;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.dss.valueobject.RptExpEdpDtls;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;
import com.tcs.sgv.exprcpt.valueobject.TrnRcptMvmnt;
import com.tcs.sgv.exprcpt.valueobject.TrnVoucherDetails;

/**
 * ClassName DSSHelper
 * 
 * Description :- For create or update DSS valueObject.
 * Date of Creation 5 Sep 2007
 * @author 602409
 *
 */
public class DSSHelper {

  public static Log logger = LogFactory.getLog(DSSHelper.class);
  public static String EXP_TYPE_BILL = "Bill";
  public static String RCPT_TYPE_BILL = "Bill";
  public static String CHALLAN_CATE_CODE = "Others";
  
  
  /**
   * This method to update RptExpenditureDtls valueobject
   * @param rptExpVO
   * @param billRegVO
   * @param billBudVO
   * @param vouchVO
   */
  public static void updateRptExpVO(RptExpenditureDtls rptExpVO, TrnBillRegister billRegVO, TrnBillBudheadDtls billBudVO,TrnVoucherDetails vouchVO) {
    try 
    {

      rptExpVO.setExpStatusCode(DBConstants.ST_DTL_PSTNG_DONE);
      rptExpVO.setExpStatusDt(new Date());
      rptExpVO.setClsExpCode(billBudVO.getClsExp());
      rptExpVO.setFundTypeCode(billBudVO.getFund());
      rptExpVO.setBudTypeCode(billBudVO.getBudType());
      rptExpVO.setDemandNo(billBudVO.getDmndNo());
      rptExpVO.setScheme(Integer.parseInt(billBudVO.getSchemeNo().toString()));
      rptExpVO.setMjrHd(billBudVO.getBudMjrHd());
      rptExpVO.setSubMjrHd(billBudVO.getBudSubmjrHd());
      rptExpVO.setMinHd(billBudVO.getBudMinHd());
      rptExpVO.setSubHd(billBudVO.getBudSubHd());
      rptExpVO.setDtlHd(billBudVO.getBudDtlHd());
      rptExpVO.setFinYrId(new BigDecimal(billBudVO.getFinYearId()));
      if(vouchVO!=null)
        rptExpVO.setExpDt(vouchVO.getVoucherDate());
      //System.out.println("Voucher vo is :--"+vouchVO);
      //System.out.println("Voucher Date in Helper is :--"+vouchVO.getVoucherDate());
      rptExpVO.setDeduction(new BigDecimal(0));
      //System.out.println("exp id is :--"+rptExpVO.getExpId());
      //rptExpVO.setBillGrpCode(billGrpCode);			
    } catch(Exception ex){
      ex.printStackTrace();
      logger.error("Exception occurred DSSHelper.updateRptExpVO: \n" + ex);
    }
  }

  /**
   * This method to create RptExpEdpDtls valueObject
   * @param billEdpVO
   * @param edp
   * @return
   */
  public static RptExpEdpDtls createRptExpEdpVO(TrnBillEdpDtls billEdpVO,MstEdp edp) 
  {
    RptExpEdpDtls rptExpEdpVO = new RptExpEdpDtls();
    try  
    {
      rptExpEdpVO.setTrnExpEdpId(new BigDecimal(String.valueOf(billEdpVO.getBillEdpId())));
      rptExpEdpVO.setEdpHd(billEdpVO.getEdpCode());
      rptExpEdpVO.setObjHd(edp.getBudobjhdCode());
      if(billEdpVO.getExpRcpRec().equalsIgnoreCase("EXP"))
      {
        rptExpEdpVO.setEdpType('0');
      }
      if(billEdpVO.getExpRcpRec().equalsIgnoreCase("REC"))
      {
        rptExpEdpVO.setEdpType('1');
      }
      rptExpEdpVO.setAmount(billEdpVO.getEdpAmt());
      //System.out.println("Vo in DSS Helper:-"+rptExpEdpVO.toString() + " " + rptExpEdpVO.getEdpType() + " --  " + rptExpEdpVO.getExpEdpId());
    } catch(Exception ex) 
    {
      rptExpEdpVO = null;
      ex.printStackTrace();
      logger.error("Exception occurred DSSHelper.createRptExpEdpVO: \n" + ex);
    }
    return rptExpEdpVO;
  }

  /**
   * This method to update RptExpEdpDtls valueobject
   * @param rptExpEdpVO
   * @param billEdpVO
   */
  public static void updateRptExpEdpVO(RptExpEdpDtls rptExpEdpVO, TrnBillEdpDtls billEdpVO) 
  {
    try  
    {
      rptExpEdpVO.setEdpHd(billEdpVO.getEdpCode());
      rptExpEdpVO.setAmount(billEdpVO.getEdpAmt());
      logger.info(" the updateed vo is "+rptExpEdpVO.getEdpHd());
    }
    catch(Exception ex) 
    {
      ex.printStackTrace();
      logger.error("Exception occurred DSSHelper.updateRptExpEdpVO: \n" + ex);
    }		
  }

  /**
   * This method to create RptReceiptDtls valueobject
   * @param billEdpVO
   * @param edp
   * @param billRegVO
   * @param vouchVO
   * @return
   */
  public static RptReceiptDtls createRptReceiptVO(TrnBillEdpDtls billEdpVO, MstEdp edp,TrnBillRegister billRegVO,TrnVoucherDetails vouchVO) 
  {
    RptReceiptDtls rptReceiptVO = new RptReceiptDtls();
    try 
    {
      //rptReceiptVO.setDistrictCode("100001");
      rptReceiptVO.setTsryCode(billRegVO.getLocationCode());
      rptReceiptVO.setDdoCode(billRegVO.getDdoCode());

      rptReceiptVO.setRcptNo(new BigDecimal(String.valueOf(billEdpVO.getBillNo())));//RecpNo==BillNo or Not
      rptReceiptVO.setRcptTypeCode(DSSHelper.RCPT_TYPE_BILL); // Type = "Bill"
      rptReceiptVO.setChallanCatgCode(DSSHelper.CHALLAN_CATE_CODE);//Challan Category =Other

      rptReceiptVO.setMjrHd(edp.getBudmjrhdCode());
      rptReceiptVO.setSubMjrHd(edp.getBudsubmjrhdCode());
      rptReceiptVO.setMinHd(edp.getBudminhdCode());
      rptReceiptVO.setSubHd(edp.getBudsubhdCode());

      rptReceiptVO.setRcptStatusCode("Approved");
      if(vouchVO!=null)
        rptReceiptVO.setRevenueDt(vouchVO.getVoucherDate());

      rptReceiptVO.setFinYrId(new BigDecimal(billRegVO.getFinYearId()));
      rptReceiptVO.setAmount(billEdpVO.getEdpAmt());
      rptReceiptVO.setDedctnType(billEdpVO.getEdpCategory().charAt(0));
      rptReceiptVO.setEdpCode(billEdpVO.getEdpCode());
      rptReceiptVO.setTrnReceiptId(new BigDecimal(billEdpVO.getBillEdpId()));
    }
    catch(Exception ex) 
    {
      ex.printStackTrace();
      logger.error("Exception occurred DSSHelper.createRptReceiptVO: \n" + ex);
    }
    return rptReceiptVO;
  }

  /**
   * This method to update RptReceiptDtls valueobject
   * @param rptReceiptVO
   * @param billEdpVO
   * @param edp
   * @param vouchVO
   */
  public static void updateRptReceiptVO(RptReceiptDtls rptReceiptVO, TrnBillEdpDtls billEdpVO, MstEdp edp,TrnVoucherDetails vouchVO) 
  {		
    try 
    {
      rptReceiptVO.setRcptNo(new BigDecimal(String.valueOf(billEdpVO.getBillNo())));
      rptReceiptVO.setRcptTypeCode(DSSHelper.RCPT_TYPE_BILL);
      rptReceiptVO.setMjrHd(edp.getBudmjrhdCode());
      rptReceiptVO.setSubMjrHd(edp.getBudsubmjrhdCode());
      rptReceiptVO.setMinHd(edp.getBudminhdCode());
      rptReceiptVO.setSubHd(edp.getBudsubhdCode());	
      rptReceiptVO.setAmount(billEdpVO.getEdpAmt());
      if(vouchVO!=null)
        rptReceiptVO.setRevenueDt(vouchVO.getVoucherDate());

    } catch(Exception ex) 
    {
      ex.printStackTrace();
      logger.error("Exception occurred DSSHelper.updateRptReceiptVO: \n" + ex);
    }
  }
  /**
   * This method to update RptReceiptDtls valueobject in case of Demand No 999 
   * @param rptReceiptVO
   * @param vouchVO
   * @param budHeaVO
   */
  public static void updateRptExpDemReceiptVO(RptReceiptDtls rptReceiptVO,TrnVoucherDetails vouchVO,TrnBillBudheadDtls budHeaVO) 
  {		
    try 
    {
      rptReceiptVO.setMjrHd(budHeaVO.getBudMjrHd());
      rptReceiptVO.setSubMjrHd(budHeaVO.getBudSubmjrHd());
      rptReceiptVO.setMinHd(budHeaVO.getBudMinHd());
      rptReceiptVO.setSubHd(budHeaVO.getBudSubHd());
      rptReceiptVO.setDtlHd(budHeaVO.getBudDtlHd());
      rptReceiptVO.setFundTypeCode(budHeaVO.getFund());
      rptReceiptVO.setRcptStatusCode("Approved");
      rptReceiptVO.setRcptStatusDate(new Date());
      if(vouchVO!=null)
        rptReceiptVO.setRevenueDt(vouchVO.getVoucherDate());

    } catch(Exception ex) 
    {
      ex.printStackTrace();
      logger.error("Exception occurred DSSHelper.updateRptExpDemReceiptVO: \n" + ex);
    }
  }
  /**
   * This method to create RptReceiptDtls valueobject in case of Demand No 999
   * @param vouchVO
   * @param budHeaVO
   * @param billRegVO
   * @return
   */
  public static RptReceiptDtls createRptExpDemReceiptVO(TrnVoucherDetails vouchVO,TrnBillBudheadDtls budHeaVO,TrnBillRegister billRegVO) 
  {   
    RptReceiptDtls rptReceiptVO = new RptReceiptDtls();
    try 
    {
      rptReceiptVO.setTsryCode(billRegVO.getLocationCode());
      rptReceiptVO.setDdoCode(billRegVO.getDdoCode());

      rptReceiptVO.setRcptNo(new BigDecimal(-1));//RecpNo==BillNo or Not
      rptReceiptVO.setRcptTypeCode(DSSHelper.RCPT_TYPE_BILL); // Type = "Bill"
      rptReceiptVO.setChallanCatgCode(DSSHelper.CHALLAN_CATE_CODE);//Challan Category =Other

      rptReceiptVO.setMjrHd(budHeaVO.getBudMjrHd());
      rptReceiptVO.setSubMjrHd(budHeaVO.getBudSubmjrHd());
      rptReceiptVO.setMinHd(budHeaVO.getBudMinHd());
      rptReceiptVO.setSubHd(budHeaVO.getBudSubHd());
      rptReceiptVO.setDtlHd(budHeaVO.getBudDtlHd());
      rptReceiptVO.setFundTypeCode(budHeaVO.getFund());
      rptReceiptVO.setRcptStatusCode("Approved");
      rptReceiptVO.setRcptStatusDate(new Date());
      if(vouchVO!=null)
        rptReceiptVO.setRevenueDt(vouchVO.getVoucherDate());
      rptReceiptVO.setFinYrId(new BigDecimal(billRegVO.getFinYearId()));
      rptReceiptVO.setAmount(billRegVO.getBillGrossAmount());
      rptReceiptVO.setTrnReceiptId(new BigDecimal(billRegVO.getBillNo()));

    } 
    catch(Exception ex) 
    {
      ex.printStackTrace();
      logger.error("Exception occurred DSSHelper.createRptExpDemReceiptVO: \n" + ex);
    }
    return rptReceiptVO;
  }
  /**
   * This method to update RptReceiptDtls valueobject
   * @param rptReceiptVO
   * @param edpVo
   */
  public static void updateRptEdpCodeReceiptVO(RptReceiptDtls rptReceiptVO,TrnBillEdpDtls edpVo) 
  {		
    try 
    {
      rptReceiptVO.setEdpCode(edpVo.getEdpCode());

    } catch(Exception ex) 
    {
      ex.printStackTrace();
      logger.error("Exception occurred DSSHelper.updateRptEdpCodeReceiptVO: \n" + ex);
    }
  }
  public static void main(String[] args) {
    TrnRcptMvmnt rcpt = new TrnRcptMvmnt();
    //System.out.println("rcpt " + rcpt);
    RptReceiptDtls trn = new RptReceiptDtls(); 
    trn.setActive('Y');
    //System.out.println("trn " + trn.toString());
  }
}
