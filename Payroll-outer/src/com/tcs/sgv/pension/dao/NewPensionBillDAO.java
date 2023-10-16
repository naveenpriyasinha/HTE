package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeRate;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeSpecial;
import com.tcs.sgv.pension.valueobject.RltPensionRevisedPayment;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionItCutDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;

public interface NewPensionBillDAO {	
	
	public TrnPensionRqstHdr getPPONoFromPnsnerCode(String lStrPnsnerCode, String lStrStatus) throws Exception;
	public Double getTrnPensionRecoveryDtls(String lStrPensionerCode, String lStrStatus) throws Exception;
    public Double getTotalRecoveryForMonth(String lStrPensionerCode,int ForMonth) throws Exception;
    public ArrayList<TrnPensionItCutDtls> getTrnPensionItCutDtls(String lStrPensionerCode) throws Exception;
    public Double getTotalPensionCutForMonth(String lStrPensionerCode,int ForMonth) throws Exception;
    public Double getTotalOtherBenefitsForMonth(String lStrPensionerCode,int ForMonth) throws Exception;
    public Double getTotalSpecialCutForMonth(String lStrPensionerCode,int ForMonth) throws Exception;
    public Double getTotalITCutForMonth(String lStrPensionerCode,int ForMonth) throws Exception;
    public RltPensioncaseBill getRltPensioncaseBillPK(String lStrPPONO,String lStrBillType,String lStrStatus) throws Exception;
    public TrnPensionBillDtls getTrnPensionBillDtls(Long TrnPensionBillHdrPK) throws Exception;
    public List<TrnPensionArrearDtls> getTrnPensionArrearDtls(String lStrPensionerCode,String lStrBillNo) throws Exception;
    
    public List<TrnPnsncaseRopRlt> getROPAppliedToPensner(String lStrPPONO) throws Exception;
    public Double getNewBasicFromROPMst(String lStrROP, String lStrColumnNo, Double lStrOldBasic) throws Exception;
    public RltPensionHeadcodeRate getRateFromHeadcodeRateRlt(Long lHeadcode, String lStrFieldType, Double lStrPnsnBasic, Date lForDate) throws Exception;
    public RltPensionHeadcodeSpecial getBasicFromHeadcodeSpecialRlt(Long lHeadcode, Double lStrOldPnsnBasic, Date lForDate) throws Exception;
    public Long getPKOfPnsncaseROPRlt(String lStrPPONO,String lStrROP) throws Exception;
    public RltPensionRevisedPayment getPaymentMnthDtls(long lPensionRatePk,String lStrFieldType,String lStrForDate) throws Exception;
}
