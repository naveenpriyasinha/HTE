package com.tcs.sgv.eis.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.util.HttpServletRequestProxy;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillRegMpgDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PaybillBillregMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class GenerateOuterService extends ServiceImpl{
	Log logger = LogFactory.getLog(getClass());
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	
	/**
	 * This method will generate the Outer, based on the 
	 * Pay bill generated. It will take Pay bill number, Month and
	 * year as input parameters.
	 * @param objectArgs
	 * @return
	 */
	public ResultObject generateOuter(Map objectArgs) {
		logger.info("Inside outer service - Start");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		long billTypeId = Long.parseLong(resourceBundle.getString("paybillTypeId"));
		long multipleMonthBillTypeId = Long.parseLong(resourceBundle.getString("multipleMonthSupplBill"));
		 String billTypeCmb=(String)objectArgs.get("billTypeCmb");
		double txtExpenditure = 0;
		double txtRecovery = 0;
		double txtReceipt = 0;
		double deducA = 0; // Deduction A
		double deducB = 0; // Deduction B
		try {
		TrnBillRegister trnBillRegister = null;
		IdGenerator idGen = new IdGenerator();
		long trnBillNo = 0; // Bill No Generated in trn_bill_register for this
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");		
		int monthGiven = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString()) : -1;
		int yearGiven = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : -1;		
		long paybillNo = objectArgs.get("paybillNo")!=null?Long.valueOf(objectArgs.get("paybillNo").toString()):0;
		logger.info("Inside Outer generation service..Month and year is  " + monthGiven + " " + yearGiven + " bill No is " + paybillNo);
		
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());

		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

		long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

		long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
		CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);		
		
		PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		List lstPaybillGrpId = payDao.getPaybillGrouopId(paybillNo, monthGiven, yearGiven);		
		long paybillGrpId = 0l;
		if(lstPaybillGrpId!=null && lstPaybillGrpId.size()>0) {
			Object rowObject = (Object)lstPaybillGrpId.get(0);						
			  paybillGrpId = rowObject!=null?Long.parseLong(rowObject.toString()):0l;
			
		}
		logger.info("Paybill Group id for bill No " + paybillNo + " is " + paybillGrpId);
		 
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpServletRequestProxy proxyReq = new HttpServletRequestProxy(request);
		
		
		PaybillRegMpgDAOImpl paybillRegMpgDAOImpl = new PaybillRegMpgDAOImpl(PaybillBillregMpg.class, serv.getSessionFactory());
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				
		long lSupplBillNo=payDao.getSupplBillNo(monthGiven,yearGiven,paybillNo,locId);
		if(lSupplBillNo!=0 && lSupplBillNo==billTypeId)
			billTypeId=billTypeId+2;
		else if(lSupplBillNo!=0 && lSupplBillNo>=billTypeId+2)
			billTypeId=lSupplBillNo+1;

		if(billTypeCmb.equals("multiplemonthpaybill"))
			billTypeId=multipleMonthBillTypeId;
		
		//Temporary testing code - Need to be checked
		billTypeId = Long.parseLong(resourceBundle.getString("paybillTypeId"));
		//Validation for duplicate outer generation required.
		//temporary code ended
		
		CmnLookupMst cmnLookupMst=cmnLookupMstDAOImpl.read(billTypeId);

		HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class, serv.getSessionFactory());

		List<HrPayEdpCompoMpg> edpList = new ArrayList();

		if (objectArgs.get("trnBillReg") != null) {
			trnBillRegister = (TrnBillRegister) objectArgs.get("trnBillReg");
			trnBillNo = trnBillRegister.getBillNo();
			// logger.info("tenbillReg is " + trnBillRegister);
		}
		

		logger.info("trnBillNo is " + trnBillNo);

		long paybill_billreg_pk = 0;
		synchronized (idGen) {
			paybill_billreg_pk = idGen.PKGenerator("PAYBILL_BILLREG_MPG", objectArgs); // PK
			// for
			// "PAYBILL_BILLREG_MPG"
		}
		PaybillBillregMpg paybillBillregMpgInsert = new PaybillBillregMpg();
		paybillBillregMpgInsert.setId(paybill_billreg_pk);		
		
		paybillBillregMpgInsert.setHrPayPaybill(paybillGrpId);
		paybillBillregMpgInsert.setBillTypeId(cmnLookupMst);

		paybillBillregMpgInsert.setTrnBillRegister(trnBillRegister);
		paybillBillregMpgInsert.setCmnLocationMst(cmnLocationMst);
		paybillBillregMpgInsert.setCmnDatabaseMst(cmnDatabaseMst);
		paybillBillregMpgInsert.setCreatedDate(new Date());
		paybillBillregMpgInsert.setOrgUserMstByCreatedBy(orgUserMst);
		paybillBillregMpgInsert.setOrgPostMstByCreatedByPost(orgPostMst);
		if (trnBillRegister != null)
			paybillRegMpgDAOImpl.create(paybillBillregMpgInsert);
		
		edpList = hrEdp.getAllData();
		objectArgs.put("month", monthGiven);
		objectArgs.put("yearGiven", yearGiven);
		objectArgs.put("paybillNo", paybillNo);
		Map outerMap = new GenerateBillServiceHelper().getOuterValues(objectArgs);
		if (outerMap != null && outerMap.size() > 0)
			for (int i = 0; i < edpList.size(); i++) {
				HrPayEdpCompoMpg edpComp = new HrPayEdpCompoMpg();
				edpComp = edpList.get(i);
				String edpCode = String.valueOf(edpComp.getRltBillTypeEdp().getEdpCode());
				String edpCat = edpComp.getRltBillTypeEdp().getEdpCategory();
				String exp_rec_rec = edpComp.getRltBillTypeEdp().getExpRcpRec();
				String keyName = "";
				if(exp_rec_rec!=null && !exp_rec_rec.equals(""))
				  keyName = edpCode + "~" + exp_rec_rec.toUpperCase();
				String strTypeEdpId = String.valueOf(edpComp.getRltBillTypeEdp().getTypeEdpId());
				String addedFlag = edpComp.getRltBillTypeEdp().getAddDedFlag();
				logger.info("Edp code and category is " + edpCode + "--" + edpCat);
				

				long edpCodeValue = 0;
				if (exp_rec_rec != null && !exp_rec_rec.equals("") && exp_rec_rec.equals("EXP")) {
					proxyReq.setParameter("hdPayEdpId", strTypeEdpId);
					proxyReq.setParameter("hdPayAddDed", addedFlag);
					proxyReq.setParameter("hdPayEdpCate", edpCat);
					proxyReq.setParameter("hdPayEdpCode", edpCode);
					proxyReq.removeParameter("txtAmt" + edpCode);
					if (outerMap.containsKey(keyName))
						edpCodeValue = Long.valueOf(outerMap.get(keyName).toString());
					logger.info("Edp code is " + edpCode + " value is " + edpCodeValue);
					txtExpenditure += edpCodeValue;

					String paramName = "txtAmt" + edpCode;
					proxyReq.setParameter(paramName, String.valueOf(edpCodeValue));
				} else if (exp_rec_rec != null && !exp_rec_rec.equals("") && exp_rec_rec.equals("RCP")) {
					proxyReq.removeParameter("txtAmt" + edpCode);
					  proxyReq.setParameter("hdRcptEdpId", strTypeEdpId);
					  proxyReq.setParameter("hdRcptAddDed", addedFlag);
					  proxyReq.setParameter("hdRcptEdpCate", edpCat);
					  proxyReq.setParameter("hdRcptEdpCode", edpCode);
					if (outerMap.containsKey(keyName))
						edpCodeValue = Long.valueOf(outerMap.get(keyName).toString());
					logger.info("Edp code is " + edpCode + " value is " + edpCodeValue);
					txtReceipt += edpCodeValue;
					String paramName = "txtAmt" + edpCode;
					proxyReq.setParameter(paramName, String.valueOf(edpCodeValue));
					if (edpCat.equals("A")) {
						deducA += edpCodeValue;
					} else if (edpCat.equals("B")) {
						deducB += edpCodeValue;
					}
				} else if (exp_rec_rec != null && !exp_rec_rec.equals("") && exp_rec_rec.equals("REC")) {
					proxyReq.removeParameter("txtRecAmt" + edpCode);
					  proxyReq.setParameter("hdRecEdpId", strTypeEdpId);
					  proxyReq.setParameter("hdRecAddDed", addedFlag);
					  proxyReq.setParameter("hdRecEdpCate", edpCat);
					  proxyReq.setParameter("hdRecEdpCode", edpCode);	
					if (outerMap.containsKey(keyName))
						edpCodeValue = Long.valueOf(outerMap.get(keyName).toString());
					txtRecovery += edpCodeValue;
					String paramName = "txtRecAmt" + edpCode;
					proxyReq.setParameter(paramName, String.valueOf(edpCodeValue));
				}
			}
		proxyReq.removeParameter("txtExpenditure");
		proxyReq.setParameter("txtExpenditure", String.valueOf(txtExpenditure));
		// for Total Recovery
		proxyReq.removeParameter("txtRecovery");
		proxyReq.setParameter("txtRecovery", String.valueOf(txtRecovery));

		// for Total of DeductionA
		proxyReq.removeParameter("DeductionA");
		proxyReq.setParameter("DeductionA", String.valueOf(deducA));

		// for Total of DeductionA
		proxyReq.removeParameter("DeductionB");
		proxyReq.setParameter("DeductionB", String.valueOf(deducB));
		
		logger.info("Total Expenditure is " + String.valueOf(txtExpenditure));
		logger.info("Total Recovery is " + txtRecovery);
		logger.info("Total DeducA is " + deducA);
		logger.info("Total DeducB is " + deducB);
		objectArgs.put("requestObj", proxyReq);
		objectArgs.put("paybillGenerated", "1");
		resultObject.setResultValue(objectArgs);
		}catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
}
