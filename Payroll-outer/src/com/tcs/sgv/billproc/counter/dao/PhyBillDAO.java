package com.tcs.sgv.billproc.counter.dao;

import java.util.Map;

import com.tcs.sgv.common.valueobject.TrnBillRegister;


/**
 * 
 * 
 * This is interface PhyBillDAO
 * 
 * Date of Creation : 10th July 2007
 * 
 * Author : Vidhya Mashru
 * 
 * Revision History 
 * ===================== 
 * Bhavik 23-Oct-2007 Made changes for  code formatting
 */

public interface PhyBillDAO 
{	
	public int updateTrnBillReg(Map inputMap, String lStrBillNo);
//	public double getNetAmount(String billNo);
	public void updateBudHeadDetails(Map inputMap,String billNo);
	public void updateRptExpDtls(Map inputMap, Long lLngExpNo, TrnBillRegister lObjTrnBillRegsiter);
	public void updateRptRcptDtls(Map inputMap, Long lLngRcptNo, Long lLngRcptId, TrnBillRegister lObjTrnBillRegsiter);
}
