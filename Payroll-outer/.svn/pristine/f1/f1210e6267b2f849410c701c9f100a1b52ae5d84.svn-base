package com.tcs.sgv.lcm.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.lcm.dao.LcAdviceReceiptDAOImpl;
import com.tcs.sgv.lcm.valueobject.TrnLcBudgetPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcChequePosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDeductionPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDtlPosting;

public class LcAdviceReceivedVOGen 
extends ServiceImpl 
implements VOGeneratorService
{
    Log logger = LogFactory.getLog(getClass());
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	public ResultObject generateMap(Map p_objServiceArgs) 
	{
		logger.info("\n------------ Inside generateMap of LcAdviceReceivedVOGen----------- ");
		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");
		HttpSession session = request.getSession();
		
		SimpleDateFormat sdf = null;		
		
		//getting all common default value from SessionHelper
		Long createdUserId=SessionHelper.getUserId(request);
		Long createdPostId=SessionHelper.getPostId(p_objServiceArgs);
		Long updatedUserId=SessionHelper.getUserId(request);
		Long updatedPostId=SessionHelper.getPostId(p_objServiceArgs);
		Long langId=SessionHelper.getLangId(p_objServiceArgs);
		String lStrLangId = langId.toString();
		int lILangId = Integer.parseInt(lStrLangId);
		
		String locCode = SessionHelper.getLocationCode(p_objServiceArgs);
		Long dbId = SessionHelper.getDbId(p_objServiceArgs);
		
		String lStrDivisionCode="";
		long lILcOrdId=0;
		String lStrAdviceNo="";
		String lStrAdviceDate="";
		String lStrMonth="";
		double lDblOpeningLc=0.0;
		String lStrLcValidFrom="";
		String lStrDivOff="";
		String lStrDrwOff="";
		String lStrTsryOff="";
		String lStrDistCode="";
		String lStrDeptCode="";
		double lDblClosingLc=0.0;
		String lStrBankCode="";	
		Date lcValidFromDt = null,adviceDt = null;
		
		try
		{		
			logger.info("\n------------ Inside TRY/CATCH of generateMap of AdviceReceived VOGEN----------- ");
			
			//code for getting value from LcAdviceReceived.jsp			
			
			if(!StringUtility.getParameter("txtDivisionId", request).equals(""))
				lStrDivisionCode = (String)StringUtility.getParameter("txtDivisionId", request);	
			logger.info("\n------------ lStrDivisionCode----------- "+lStrDivisionCode);
			if(!StringUtility.getParameter("txtLcOrderId", request).equals(""))
				lILcOrdId = Long.parseLong((String)StringUtility.getParameter("txtLcOrderId", request));	
			logger.info("\n------------ lILcOrdId----------- "+lILcOrdId);
			if(!StringUtility.getParameter("txtAdviceNo", request).equals(""))
				lStrAdviceNo = (String)StringUtility.getParameter("txtAdviceNo", request);	
			logger.info("\n------------ lStrAdviceNo----------- "+lStrAdviceNo);
			
			if(!StringUtility.getParameter("hidDistCode", request).equals(""))
				lStrDistCode = StringUtility.getParameter("hidDistCode", request);	
			logger.info("\n------------ hidDistCode----------- "+lStrDistCode);
			
			if(!StringUtility.getParameter("hidDeptCode", request).equals(""))
				lStrDeptCode = StringUtility.getParameter("hidDeptCode", request);	
			logger.info("\n------------ hidDeptCode----------- "+lStrDeptCode);
			
			p_objServiceArgs.put("distcode", lStrDistCode);
			p_objServiceArgs.put("deptcode", lStrDeptCode);
			if(!StringUtility.getParameter("txtAdviceDate", request).equals(""))
            {
            	lStrAdviceDate = StringUtility.getParameter("txtAdviceDate", request);			
	            sdf = new SimpleDateFormat("dd/MM/yyyy");
	            adviceDt = sdf.parse(lStrAdviceDate);
	            logger.info("\n------------ adviceDt----------- "+adviceDt);
            }
            
            if(!StringUtility.getParameter("id_month", request).equals("-1"))
            	lStrMonth = (String)StringUtility.getParameter("id_month", request);	
			logger.info("\n------------ lStrMonth----------- "+lStrMonth);
			if(!StringUtility.getParameter("txtOpeningLcBal", request).equals(""))
				lDblOpeningLc = Double.parseDouble((String)StringUtility.getParameter("txtOpeningLcBal", request));	
			logger.info("\n------------ lDblOpeningLc----------- "+lDblOpeningLc);
			
			if(!StringUtility.getParameter("txtLcValidFrom", request).equals(""))
			{
				lStrLcValidFrom = StringUtility.getParameter("txtLcValidFrom", request);			
	            sdf = new SimpleDateFormat("yyyy-MM-dd");
	            lcValidFromDt = sdf.parse(lStrLcValidFrom);
	            logger.info("\n------------ lcValidFromDt----------- "+lcValidFromDt);
			}
			
            if(!StringUtility.getParameter("txtDivOff", request).equals(""))
            	lStrDivOff = (String)StringUtility.getParameter("txtDivOff", request);	
			logger.info("\n------------ lStrDivOff----------- "+lStrDivOff);
			if(!StringUtility.getParameter("txtDrwOff", request).equals(""))
				lStrDrwOff = (String)StringUtility.getParameter("txtDrwOff", request);	
			logger.info("\n------------ lStrDrwOff----------- "+lStrDrwOff);
			if(!StringUtility.getParameter("txtTsryOff", request).equals(""))
				lStrTsryOff = (String)StringUtility.getParameter("txtTsryOff", request);	
			logger.info("\n------------ lStrTsryOff----------- "+lStrTsryOff);
			
			if(!StringUtility.getParameter("txtLcBalance", request).equals(""))
				lDblClosingLc = Double.parseDouble((String)StringUtility.getParameter("txtLcBalance", request));	
			logger.info("\n------------ lDblClosingLc----------- "+lDblClosingLc);
			
			if(!StringUtility.getParameter("id_Bank", request).equals("-1"))
				lStrBankCode = (String)StringUtility.getParameter("id_Bank", request);	
			logger.info("\n------------ lStrBankCode----------- "+lStrBankCode);
			
			
            logger.info("\n------------ in TRY/CATCH After getting All Data----------- ");			
						
			
			FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			
			
			//getting value from other than JSP
			
			//Generating LcExpId as PK
			BptmCommonServiceImpl serviceImpl=new BptmCommonServiceImpl();
			//SeqNumServiceImpl serviceImpl = new SeqNumServiceImpl();
			long lLongLcExpId = serviceImpl.getNextSeqNum("trn_lc_dtl_posting", p_objServiceArgs);
			logger.info("------Auto generate lLongLcExpId ID------"+lLongLcExpId);
			
			//Getting Treasury LocationID for Know Who Is Logged In Treasury or Division
			LcAdviceReceiptDAOImpl adviceReceiptDao = new  LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			String lStrTsryLocId=(String)adviceReceiptDao.getParentPostId(locCode,lILangId);
			
			//Code to Get LC Validity Date
			String lStrLcValidTo=(String)adviceReceiptDao.getLcValidityDate(lStrDivisionCode);
			
			Character lCharActive = '0';
			int lILtstCntr=1;
			long lIFinYrId=(long)lObjFinYearDAOImpl.getFinYearIdByCurDate();
			logger.info("------FINYEAR ID---------"+lIFinYrId);
			Date crtDate = new Date();
			
			TrnLcDtlPosting lObjLcDtlPostingVo = new TrnLcDtlPosting();//TrnLcDtlPosting VO
			
			//Setting the Value in TrnLcDtlPosting VO
			logger.info("------LcDtlPosting Vo Setter Method Start------");
			lObjLcDtlPostingVo.setLcExpId(new BigDecimal(lLongLcExpId));
			lObjLcDtlPostingVo.setDivisionCode(lStrDivisionCode);
			lObjLcDtlPostingVo.setLcOrderId(new BigDecimal(lILcOrdId));
			lObjLcDtlPostingVo.setAdviceNo(new BigDecimal(lStrAdviceNo));
			lObjLcDtlPostingVo.setAdviceDt(adviceDt);
			
			if(lStrTsryLocId.equals(bundleConst.getString("LC.TSRY_POST_ID")))// 100051 is LocId (PostId) of Treasury in cmn_location_mst table
			{
				lObjLcDtlPostingVo.setAdviceApproved('0');
				lObjLcDtlPostingVo.setAdviceApprovedDt(crtDate);
				logger.info("-----inside if to advice approved----------");
			}
			else
			{
				lObjLcDtlPostingVo.setAdviceApproved('1');
				logger.info("-----inside else to advice approved----------");
			}			
			
			lObjLcDtlPostingVo.setMonthCode(lStrMonth);
			lObjLcDtlPostingVo.setOpeningLc(new BigDecimal(lDblOpeningLc));
			//we are Not Setting NewLc Field Of Trn_lc_Dtl_Posting Table
			lObjLcDtlPostingVo.setLcValidFrm(lcValidFromDt);
			lObjLcDtlPostingVo.setDivOff(lStrDivOff);
			lObjLcDtlPostingVo.setDrwOff(lStrDrwOff);
			lObjLcDtlPostingVo.setTsryOff(lStrTsryOff);
			lObjLcDtlPostingVo.setClosingLc(new BigDecimal(lDblClosingLc));
			
			if((!lStrLcValidTo.equals("")) || lStrLcValidTo != null)
			{
				lObjLcDtlPostingVo.setEntryOnValidLc('0');
			}
			else
			{
				lObjLcDtlPostingVo.setEntryOnValidLc('1');
			}
			
			lObjLcDtlPostingVo.setBankCode(lStrBankCode);
			
			lObjLcDtlPostingVo.setLtstCntr((byte)lILtstCntr);
			lObjLcDtlPostingVo.setActive(lCharActive);
			lObjLcDtlPostingVo.setFinYearId(lIFinYrId);
			
			lObjLcDtlPostingVo.setCreatedUserId(new BigDecimal(createdUserId));
			lObjLcDtlPostingVo.setCreatedPostId(new BigDecimal(createdPostId));
			lObjLcDtlPostingVo.setCreatedDate(crtDate);
			lObjLcDtlPostingVo.setLocCode(locCode);
			lObjLcDtlPostingVo.setDbId(new BigDecimal(dbId));
			
		    logger.info("============================================ ");
			
			
			
			logger.info("------LcDtlPosting Vo Setter Method End------");
			
			//Code for Getting Data from Cheque table
			int lITotChqRow=0;
			int lIChequeNo=0;
			String lStrChequeDate="";
			String lStrPartyName="";
			double lDblChequeAmt=0.0;
			
			TrnLcChequePosting lcChequePostingVO =null;//LcChequePosting VO
			ArrayList lArrChequePosting=new ArrayList();
			
			if(!StringUtility.getParameter("totalChqRow", request).equals(""))
				lITotChqRow=Integer.parseInt((String)StringUtility.getParameter("totalChqRow", request));
			logger.info("\n------------ lITotChqRow----------- "+lITotChqRow);
			
			//Getting Lc Account No 
			String lStrDivAccNo=(String)adviceReceiptDao.getDivisionAccountNo(lStrDivisionCode,lILangId);
			
			long llngDivAccNo=0;
			if(!lStrDivAccNo.equals(""))
			      llngDivAccNo=Long.parseLong(lStrDivAccNo);
			
			for(int i=1;i<=lITotChqRow ;i++)
			{
				if(!StringUtility.getParameter("chkDt"+i, request).equals(""))
				{
					logger.info("--------INSIDE IF------------------"+i);
					if(!StringUtility.getParameter("chkDt"+i, request).equals(""))
						lStrChequeDate=(String)StringUtility.getParameter("chkDt"+i, request);
					sdf = new SimpleDateFormat("dd/MM/yyyy");
			        Date lcChequeDt = sdf.parse(lStrChequeDate);				
					logger.info("\n------------ lcChequeDt----------- "+lcChequeDt);
					
					if(!StringUtility.getParameter("chkNo"+i, request).equals(""))
						lIChequeNo=Integer.parseInt((String)StringUtility.getParameter("chkNo"+i, request));
					logger.info("\n------------ lIChequeNo----------- "+lIChequeNo);
					if(!StringUtility.getParameter("chkAmt"+i, request).equals(""))
						lDblChequeAmt=Double.parseDouble((String)StringUtility.getParameter("chkAmt"+i, request));
					logger.info("\n------------ lDblChequeAmt----------- "+lDblChequeAmt);
					if(!StringUtility.getParameter("chkPartyNm"+i, request).equals(""))
						lStrPartyName=(String)StringUtility.getParameter("chkPartyNm"+i, request);
					logger.info("\n------------ lISubHd----------- "+lStrPartyName);
					
					
					lcChequePostingVO = new TrnLcChequePosting();
					
					//Code for Getting Auto Generated Cheque Id from cmn_table_seq_mst table
					BptmCommonServiceImpl serviceImplBudHd=new BptmCommonServiceImpl();
					//SeqNumServiceImpl serviceImplBudHd = new SeqNumServiceImpl();
					long lLngChequeId = serviceImplBudHd.getNextSeqNum("trn_lc_cheque_posting", p_objServiceArgs);
					logger.info("------Auto generate LC CHEQUE ID------"+lLngChequeId);
					
					//setting the value in ChequePosting VO
					lcChequePostingVO.setLcChqId(new BigDecimal(lLngChequeId));
					lcChequePostingVO.setLcExpId(new BigDecimal(lLongLcExpId));
					lcChequePostingVO.setChequeNo(new BigDecimal(lIChequeNo));
					lcChequePostingVO.setChequeDt(lcChequeDt);
					lcChequePostingVO.setChequeAmt(new BigDecimal(lDblChequeAmt));
					lcChequePostingVO.setPartyName(lStrPartyName);				
					//CHQ_CLR_DT not Saved Here
					lcChequePostingVO.setLcAccNo(new BigDecimal(llngDivAccNo));
					//CHQ_CANCEL_DT not Saved Here
					//CHQ_CANCEL_REASON not Saved Here
					
					lcChequePostingVO.setLtstCntr((byte)lILtstCntr);
					lcChequePostingVO.setActive(lCharActive);
					lcChequePostingVO.setFinYearId(lIFinYrId);				
					
					lcChequePostingVO.setCreatedUserId(new BigDecimal(createdUserId));
					lcChequePostingVO.setCreatedPostId(new BigDecimal(createdPostId));
					lcChequePostingVO.setCreatedDate(crtDate);
					lcChequePostingVO.setLocCode(locCode);
					lcChequePostingVO.setDbId(new BigDecimal(dbId));
					
					lArrChequePosting.add(lcChequePostingVO);
				}
			}
			logger.info("---------SIZE OF lArrChequePosting--------------"+lArrChequePosting.size());
			//..........................................................................
			
			//Code For Getting Data From Budget Table
			int lITotBudRow=0;
			String lStrClassOfExp="";
			String lStrFund="";
			String lStrDrwingOff="";
			String lStrDemandNo="";
			String lStrBudgetType="";
			long lLngSchemeNo=0;
			String lStrMjrHd="";
			String lStrSubMjrhd="";
			String lStrMinHd="";
			String lStrSubHd="";
			String lStrDtlHd="";
			String lStrObjHd="";
			double ldblExpAmt=0.0;
			
			TrnLcBudgetPosting lcBudgetPostingVO =null;//LcChequePosting VO
			ArrayList lArrBudgetPosting=new ArrayList();
			
			if(!StringUtility.getParameter("totalBudRow", request).equals(""))
				lITotBudRow=Integer.parseInt((String)StringUtility.getParameter("totalBudRow", request));
			logger.info("\n------------ lITotBudRow----------- "+lITotBudRow);
			
			for(int i=1;i<=lITotBudRow ;i++)
			{	
				if(!StringUtility.getParameter("cmbClassOfExp"+i, request).equals("-1"))
				{	
					logger.info("---------INSIDE BUD IF---------------"+i);
					if(!StringUtility.getParameter("cmbClassOfExp"+i, request).equals("-1"))
						lStrClassOfExp=(String)StringUtility.getParameter("cmbClassOfExp"+i, request);
					logger.info("\n------------ lStrClassOfExp----------- "+lStrClassOfExp);
					if(!StringUtility.getParameter("cmbFund"+i, request).equals("-1"))
						lStrFund=(String)StringUtility.getParameter("cmbFund"+i, request);
					logger.info("\n------------ lStrFund----------- "+lStrFund);
					if(!StringUtility.getParameter("txtDrwOff"+i, request).equals(""))
						lStrDrwingOff=(String)StringUtility.getParameter("txtDrwOff"+i, request);
					logger.info("\n------------ lStrDrwingOff----------- "+lStrDrwingOff);
					if(!StringUtility.getParameter("cmbDemandNo"+i, request).equals("-1"))
						lStrDemandNo=(String)StringUtility.getParameter("cmbDemandNo"+i, request);
					logger.info("\n------------ lStrDemandNo----------- "+lStrDemandNo);
					if(!StringUtility.getParameter("cmbBudgetType"+i, request).equals("-1"))
						lStrBudgetType=(String)StringUtility.getParameter("cmbBudgetType"+i, request);
					logger.info("\n------------ lStrBudgetType----------- "+lStrBudgetType);
					if(!StringUtility.getParameter("txtSchemeNo"+i, request).equals(""))
						lLngSchemeNo=Long.parseLong((String)StringUtility.getParameter("txtSchemeNo"+i, request));
					logger.info("\n------------ lLngSchemeNo----------- "+lLngSchemeNo);
					if(!StringUtility.getParameter("cmbMjrHd"+i, request).equals("-1"))
						lStrMjrHd=(String)StringUtility.getParameter("cmbMjrHd"+i, request);
					logger.info("\n------------ lStrMjrHd----------- "+lStrMjrHd);
					if(!StringUtility.getParameter("cmbSubMjrHd"+i, request).equals("-1"))
						lStrSubMjrhd=(String)StringUtility.getParameter("cmbSubMjrHd"+i, request);
					logger.info("\n------------ lStrSubMjrhd----------- "+lStrSubMjrhd);
					if(!StringUtility.getParameter("cmbMinHd"+i, request).equals("-1"))
						lStrMinHd=(String)StringUtility.getParameter("cmbMinHd"+i, request);
					logger.info("\n------------ lStrMinHd----------- "+lStrMinHd);
					if(!StringUtility.getParameter("cmbSubHd"+i, request).equals("-1"))
						lStrSubHd=(String)StringUtility.getParameter("cmbSubHd"+i, request);
					logger.info("\n------------ lStrSubHd----------- "+lStrSubHd);
					if(!StringUtility.getParameter("txtDtlHd"+i, request).equals(""))
						lStrDtlHd=(String)StringUtility.getParameter("txtDtlHd"+i, request);
					logger.info("\n------------ lStrDtlHd----------- "+lStrDtlHd);
					if(!StringUtility.getParameter("txtObjHd"+i, request).equals(""))
						lStrObjHd=(String)StringUtility.getParameter("txtObjHd"+i, request);
					logger.info("\n------------ lStrObjHd----------- "+lStrObjHd);
					if(!StringUtility.getParameter("txtExpAmt"+i, request).equals(""))
						ldblExpAmt=Double.parseDouble((String)StringUtility.getParameter("txtExpAmt"+i, request));
					logger.info("\n------------ ldblExpAmt----------- "+ldblExpAmt);	
					
					
					lcBudgetPostingVO = new TrnLcBudgetPosting();
					
					//Code for Getting Auto Generated Lc Budget Id from cmn_table_seq_mst table
					BptmCommonServiceImpl serviceImplBudHd=new BptmCommonServiceImpl();
					//SeqNumServiceImpl serviceImplBudHd = new SeqNumServiceImpl();
					long lLngBudId = serviceImplBudHd.getNextSeqNum("trn_lc_budget_posting", p_objServiceArgs);
					logger.info("------Auto generate LC BUDGET ID------"+lLngBudId);
					
					//setting the value in ChequePosting VO
					lcBudgetPostingVO.setLcBudId(new BigDecimal(lLngBudId));
					lcBudgetPostingVO.setLcExpId(new BigDecimal(lLongLcExpId));
					lcBudgetPostingVO.setClassOfExp(lStrClassOfExp);
					lcBudgetPostingVO.setFund(lStrFund);
					lcBudgetPostingVO.setDrwOff(lStrDrwingOff);
					lcBudgetPostingVO.setDemandNo(lStrDemandNo);
					lcBudgetPostingVO.setBudgetType(lStrBudgetType);
					lcBudgetPostingVO.setSchemeNo((int)lLngSchemeNo);
					lcBudgetPostingVO.setMjrHd(lStrMjrHd);
					lcBudgetPostingVO.setSubMjrHd(lStrSubMjrhd);
					lcBudgetPostingVO.setMinHd(lStrMinHd);
					lcBudgetPostingVO.setSubHd(lStrSubHd);
					lcBudgetPostingVO.setDtlHd(lStrDtlHd);
					lcBudgetPostingVO.setObjHd(lStrObjHd);
					lcBudgetPostingVO.setExpAmt(new BigDecimal(ldblExpAmt));
					
					lcBudgetPostingVO.setLtstCntr((byte)lILtstCntr);
					lcBudgetPostingVO.setActive(lCharActive);
					lcBudgetPostingVO.setFinYearId(lIFinYrId);				
					
					lcBudgetPostingVO.setCreatedUserId(new BigDecimal(createdUserId));
					lcBudgetPostingVO.setCreatedPostId(new BigDecimal(createdPostId));
					lcBudgetPostingVO.setCreatedDate(crtDate);
					lcBudgetPostingVO.setLocCode(locCode);
					lcBudgetPostingVO.setDbId(new BigDecimal(dbId));
					
					lArrBudgetPosting.add(lcBudgetPostingVO);
				}
			}
			//..........................................................................
			
			//Code For Getting Data From Deduction Table
			
			int lITotDeductionRow=0;
			int lIDeductionId=0;
			String lStrEdpCode="";
			double lDblDedAmt=0.0;
			ArrayList lArrDeductionPosting=new ArrayList();
			TrnLcDeductionPosting lcDeductionVo=null;
			
			if(!StringUtility.getParameter("dedTotalRow", request).equals(""))
				lITotDeductionRow=Integer.parseInt((String)StringUtility.getParameter("dedTotalRow", request));
			logger.info("\n------------ lITotDeductionRow----------- "+lITotDeductionRow);
			
			for(int i=1;i<=lITotDeductionRow ;i++)
			{
				//Code for Getting Auto Generated Lc Deduction Id from cmn_table_seq_mst table
				BptmCommonServiceImpl serviceImplBudHd=new BptmCommonServiceImpl();
				//SeqNumServiceImpl serviceImplBudHd = new SeqNumServiceImpl();
				long lLngDedId = serviceImplBudHd.getNextSeqNum("trn_lc_deduction_posting", p_objServiceArgs);
				logger.info("------Auto generate LC DEDUCTION ID------"+lLngDedId);
				
				if(!StringUtility.getParameter("txtDedCode"+i, request).equals(""))
					lStrEdpCode=(String)StringUtility.getParameter("txtDedCode"+i, request);
				logger.info("\n------------ lStrEdpCode----------- "+lStrEdpCode);
				
				if(!StringUtility.getParameter("txtDedAmt"+i, request).equals(""))
					lDblDedAmt=Double.parseDouble((String)StringUtility.getParameter("txtDedAmt"+i, request));
				logger.info("\n------------ lDblDedAmt----------- "+lDblDedAmt);	
				
				lcDeductionVo=new TrnLcDeductionPosting();			

				
				//Setting the value in deduction VO
				lcDeductionVo.setLcDedId(new BigDecimal(lLngDedId));
				lcDeductionVo.setLcExpId(new BigDecimal(lLongLcExpId));
				lcDeductionVo.setEdpCode(lStrEdpCode);
				lcDeductionVo.setAmount(new BigDecimal(lDblDedAmt));
				
				lcDeductionVo.setLtstCntr((byte)lILtstCntr);
				lcDeductionVo.setActive(lCharActive);
				lcDeductionVo.setFinYearId(lIFinYrId);
				
				lcDeductionVo.setCreatedUserId(new BigDecimal(createdUserId));
				lcDeductionVo.setCreatedPostId(new BigDecimal(createdPostId));
				lcDeductionVo.setCreatedDate(crtDate);
				lcDeductionVo.setLocCode(locCode);
				lcDeductionVo.setDbId(new BigDecimal(dbId));
				
				lArrDeductionPosting.add(lcDeductionVo);
			}
			logger.info("------SIZE OF DED POSTING ARR--------"+lArrDeductionPosting.size());
			//...........................................................................
			
			logger.info("\n------------ END OF ADVICE RECEIVED VOGEN ----------- ");			
			
			p_objServiceArgs.put("lObjLcDtlPostingVo",lObjLcDtlPostingVo);
			p_objServiceArgs.put("lArrChequePosting",lArrChequePosting);
			p_objServiceArgs.put("lArrBudgetPosting",lArrBudgetPosting);
			p_objServiceArgs.put("lArrDeductionPosting",lArrDeductionPosting);
			
			retObj.setResultValue(p_objServiceArgs);
			return retObj;
		}
		catch(Exception e)
		{			
			e.printStackTrace();
			return retObj;
		}	
	}
}
