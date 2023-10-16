package com.tcs.sgv.lcm.reports;

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
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.util.DAOFactory;
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

public class LCAdviceReceivedUpdateVOGen 
extends ServiceImpl 
implements VOGeneratorService
{
	    Log glogger = LogFactory.getLog(getClass());
	    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
		public ResultObject generateMap(Map p_objServiceArgs) 
		{
			glogger.info("---- Inside generateMap of LCAdviceReceivedUpdateVOGen------- ");
			
			ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
			HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
			ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");
			HttpSession session = request.getSession();
			
			 
			SessionFactory lObjSessionFactory = serv.getSessionFactory();
			SimpleDateFormat sdf = null;		
			
			//getting all common default value from SessionHelper
			Long createdUserId=SessionHelper.getUserId(request);
			Long createdPostId=SessionHelper.getPostId(p_objServiceArgs);
			Long updatedUserId=SessionHelper.getUserId(request);
			Long updatedPostId=SessionHelper.getPostId(p_objServiceArgs);
			Long langId=SessionHelper.getLangId(p_objServiceArgs);
			String lStrLangId = langId.toString();
			int lILangId = Integer.parseInt(lStrLangId);
			
			Long locId = SessionHelper.getLocationId(p_objServiceArgs);
			Long dbId = SessionHelper.getDbId(p_objServiceArgs);
			
			String lStrLocCode=SessionHelper.getLocationCode(p_objServiceArgs);
			
			LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
			lObjRptDAO.setSessionFactory(lObjSessionFactory);
			
			LcAdviceReceiptDAOImpl adviceReceiptDao = new  LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			
			ArrayList lArrUpdateChequePosting=new ArrayList();
			ArrayList lArrInsertChequePosting=new ArrayList();
			ArrayList lArrDeleteChequePosting=new ArrayList();
			
			
			try
			{
				long lILcExpId=0;
				String lStrDivCode="";				
				int lIAdviceNo=0;
				String lStrAdviceDate="";
				String lStrMonthCode="";
				double lDblOpeningLc=0.0;
				String lStrLcValidFrom="";
				String lStrDivOff="";
				String lStrDrwOff="";
				String lStrTsryOff="";
				double lDblClosingLc=0.0;
				String lStrBankcode="";	
				String lStrAdvApproved="";
				
				glogger.info("---Inside TRY/CATCH of generateMap of AdviceReceivedUpdate VOGEN----- ");
				
				//code for getting value from LcAdviceReceivedUpdate.jsp			
				
				if(!StringUtility.getParameter("txtLcExpId", request).equals(""))
					lILcExpId = Long.parseLong((String)StringUtility.getParameter("txtLcExpId", request));	
				glogger.info("------------ lILcExpId----------- "+lILcExpId);
				
				if(!StringUtility.getParameter("txtDivisionId", request).equals(""))
					lStrDivCode = (String)StringUtility.getParameter("txtDivisionId", request);	
				glogger.info("------------ lStrDivCode----------- "+lStrDivCode);
				
				if(!StringUtility.getParameter("txtAdviceNo", request).equals(""))
					lIAdviceNo = Integer.parseInt((String)StringUtility.getParameter("txtAdviceNo", request));	
				glogger.info("------------ lIAdviceNo----------- "+lIAdviceNo);
				
	            if(!StringUtility.getParameter("txtAdviceDate", request).equals(""))
	            	lStrAdviceDate = StringUtility.getParameter("txtAdviceDate", request);			
	            
	            if(lStrAdviceDate.charAt(4)=='-')
                {
                  String [] lStrDateTemp = lStrAdviceDate.split("-");
                  for(int d=0 ; d<lStrDateTemp.length ; d++)
                	  lStrAdviceDate = lStrDateTemp[2] + "/" + lStrDateTemp[1] + "/" +lStrDateTemp[0];
                }
				
	            sdf = new SimpleDateFormat("dd/MM/yyyy");
	            Date adviceDt1 = sdf.parse(lStrAdviceDate);	 	           
	            java.sql.Date adviceDt=new java.sql.Date(adviceDt1.getTime());
	            
	            glogger.info("\n------------ adviceDt----------- "+adviceDt.toString());
	            
	            if(!StringUtility.getParameter("id_month", request).equals("-1"))
	            	lStrMonthCode = (String)StringUtility.getParameter("id_month", request);	
				glogger.info("\n------------ lStrMonthCode----------- "+lStrMonthCode);
				if(!StringUtility.getParameter("txtOpeningLcBal", request).equals(""))
					lDblOpeningLc = Double.parseDouble((String)StringUtility.getParameter("txtOpeningLcBal", request));	
				glogger.info("\n------------ lDblOpeningLc----------- "+lDblOpeningLc);
				
				if(!StringUtility.getParameter("txtLcValidFrom", request).equals(""))
					lStrLcValidFrom = StringUtility.getParameter("txtLcValidFrom", request);	
				
				if(lStrLcValidFrom.charAt(4)=='-')
                {
                  String [] lStrDateTemp = lStrLcValidFrom.split("-");
                  for(int d=0 ; d<lStrDateTemp.length ; d++)
                	  lStrLcValidFrom = lStrDateTemp[2] + "/" + lStrDateTemp[1] + "/" +lStrDateTemp[0];
                }				
				
	            sdf = new SimpleDateFormat("dd/MM/yyyy");
	            Date lcValidFromDt1 = sdf.parse(lStrLcValidFrom);	           			
	            java.sql.Date lcValidFromDt=new java.sql.Date(lcValidFromDt1.getTime());	            
	            glogger.info("------------ lcValidFromDt----------- "+lcValidFromDt);
				
	            if(!StringUtility.getParameter("txtDivOff", request).equals(""))
	            	lStrDivOff = (String)StringUtility.getParameter("txtDivOff", request);	
				glogger.info("------------ lStrDivOff----------- "+lStrDivOff);
				if(!StringUtility.getParameter("txtDrwOff", request).equals(""))
					lStrDrwOff = (String)StringUtility.getParameter("txtDrwOff", request);	
				glogger.info("------------ lStrDrwOff----------- "+lStrDrwOff);
				if(!StringUtility.getParameter("txtTsryOff", request).equals(""))
					lStrTsryOff = (String)StringUtility.getParameter("txtTsryOff", request);	
				glogger.info("------------ lStrTsryOff----------- "+lStrTsryOff);
				
				if(!StringUtility.getParameter("txtLcBalance", request).equals(""))
					lDblClosingLc = Double.parseDouble((String)StringUtility.getParameter("txtLcBalance", request));	
				glogger.info("------------ lDblClosingLc----------- "+lDblClosingLc);
				
				if(!StringUtility.getParameter("id_Bank", request).equals("-1"))
					lStrBankcode = (String)StringUtility.getParameter("id_Bank", request);	
				glogger.info("\n------------ lStrBankcode----------- "+lStrBankcode);				
				
				if(!StringUtility.getParameter("txtAdvApproved", request).equals(""))
					lStrAdvApproved = (String)StringUtility.getParameter("txtAdvApproved", request);	
				char cha[] = (char[])lStrAdvApproved.toCharArray();
				glogger.info("------------ lStrAdvApproved----------- "+lStrAdvApproved+"---"+cha);
				
	            glogger.info("------------ in TRY/CATCH After getting All Data----------- ");
				
	            String lStrDistCode="";
	            String lStrDeptCode="";
	            
	            if(!StringUtility.getParameter("hidDistCode", request).equals(""))
					lStrDistCode = StringUtility.getParameter("hidDistCode", request);	
				glogger.info("\n------------ hidDistCode----------- "+lStrDistCode);
				
				if(!StringUtility.getParameter("hidDeptCode", request).equals(""))
					lStrDeptCode = StringUtility.getParameter("hidDeptCode", request);	
				glogger.info("\n------------ hidDeptCode----------- "+lStrDeptCode);
				
				p_objServiceArgs.put("distcode", lStrDistCode);
				p_objServiceArgs.put("deptcode", lStrDeptCode);
	            
	            
				FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
				long lIFinYrId=(long)lObjFinYearDAOImpl.getFinYearIdByCurDate();
				glogger.info("------FINYEAR ID---------"+lIFinYrId);	
	            
				Date crtDate = new Date();
				
				TrnLcDtlPosting lObjLcDtlPostingVo = new TrnLcDtlPosting();//TrnLcDtlPosting VO
				
				//Setting the Value in TrnLcDtlPosting VO
				glogger.info("------LcDtlPosting Vo Setter Method Start------");
				lObjLcDtlPostingVo.setLcExpId(new BigDecimal(lILcExpId));
				lObjLcDtlPostingVo.setDivisionCode(lStrDivCode);
				//lObjLcDtlPostingVo.setLcOrderId((long)lILcOrdId);
				lObjLcDtlPostingVo.setAdviceNo(new BigDecimal(lIAdviceNo));
				lObjLcDtlPostingVo.setAdviceDt(adviceDt);
				
				lObjLcDtlPostingVo.setMonthCode(lStrMonthCode);
				lObjLcDtlPostingVo.setOpeningLc(new BigDecimal(lDblOpeningLc));
				//we are Not Setting NewLc Field Of Trn_lc_Dtl_Posting Table
				lObjLcDtlPostingVo.setLcValidFrm(lcValidFromDt);
				lObjLcDtlPostingVo.setDivOff(lStrDivOff);
				lObjLcDtlPostingVo.setDrwOff(lStrDrwOff);
				lObjLcDtlPostingVo.setTsryOff(lStrTsryOff);
				lObjLcDtlPostingVo.setClosingLc(new BigDecimal(lDblClosingLc));
				
				lObjLcDtlPostingVo.setBankCode(lStrBankcode);
				lObjLcDtlPostingVo.setAdviceApproved(cha[0]);
				//lObjLcDtlPostingVo.setLtstCntr((byte)lILtstCntr);				
				//lObjLcDtlPostingVo.setFinYearId(lIFinYrId);				
				
				lObjLcDtlPostingVo.setUpdatedUserId(new BigDecimal(createdUserId));
				lObjLcDtlPostingVo.setUpdatedPostId(new BigDecimal(createdPostId));
				lObjLcDtlPostingVo.setUpdatedDate(crtDate);			
				
				
				glogger.info("------LcDtlPosting Vo Setter Method End------");
				
				Character lCharActive ='0';
				int lILtstCntr=1;
				
				
				//Code for Getting Data from Cheque table
				int lITotChqRow=0;
				long lIChequeNo=0;
				String lStrChequeDate="";
				String lStrPartyName="";
				double lDblChequeAmt=0.0;
				
				TrnLcChequePosting lcChequePostingVO =null;//LcChequePosting VO
				
				
				if(!StringUtility.getParameter("totalChqRow", request).equals(""))
					lITotChqRow=Integer.parseInt((String)StringUtility.getParameter("totalChqRow", request));
				glogger.info("------------ lITotChqRow----------- "+lITotChqRow);				
				
				//Getting Lc Account No 
				String lStrDivAccNo=(String)adviceReceiptDao.getDivisionAccountNo(lStrDivCode,lILangId);
				glogger.info("------DIV ACC NO------"+lStrDivAccNo);
				long llngDivAccNo=0;
				if(!lStrDivAccNo.equals(""))
				      llngDivAccNo=Long.parseLong(lStrDivAccNo);
				
				for(int i=0;i<lITotChqRow ;i++)
				{
					glogger.info("-----INSIDE CHQ LOOP -----------"+i);
					if(!StringUtility.getParameter("chkDt"+i, request).equals(""))
					{
						glogger.info("------INSIDE CHQ IF----------"+i);							
						
						if(!StringUtility.getParameter("chkDt"+i, request).equals(""))
							lStrChequeDate=(String)StringUtility.getParameter("chkDt"+i, request);								
						if(lStrChequeDate.charAt(4)=='-')
		                {
		                  String [] lStrDateTemp = lStrChequeDate.split("-");
		                  for(int d=0 ; d<lStrDateTemp.length ; d++)
		                	  lStrChequeDate = lStrDateTemp[2] + "/" + lStrDateTemp[1] + "/" +lStrDateTemp[0];
		                }	
						
						sdf = new SimpleDateFormat("dd/MM/yyyy");
				        Date lcChequeDt1 = sdf.parse(lStrChequeDate);
				        java.sql.Date lcChequeDt=new java.sql.Date(lcChequeDt1.getTime());
				        
						glogger.info("------------ lcChequeDt----------- "+lcChequeDt);
						
						if(!StringUtility.getParameter("chkNo"+i, request).equals(""))
							lIChequeNo=Integer.parseInt((String)StringUtility.getParameter("chkNo"+i, request));
						glogger.info("------------ lIChequeNo----------- "+lIChequeNo);
						if(!StringUtility.getParameter("chkAmt"+i, request).equals(""))
							lDblChequeAmt=Double.parseDouble((String)StringUtility.getParameter("chkAmt"+i, request));
						glogger.info("------------ lDblChequeAmt----------- "+lDblChequeAmt);
						if(!StringUtility.getParameter("chkPartyNm"+i, request).equals(""))
							lStrPartyName=(String)StringUtility.getParameter("chkPartyNm"+i, request);
						glogger.info("------------ lStrPartyName----------- "+lStrPartyName);						
						
						lcChequePostingVO = new TrnLcChequePosting();
						
						//setting the value in ChequePosting VO
						
						lcChequePostingVO.setLcExpId(new BigDecimal(lILcExpId));
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
						lcChequePostingVO.setUpdatedUserId(new BigDecimal(updatedUserId));
						lcChequePostingVO.setUpdatedPostId(new BigDecimal(updatedPostId));
						lcChequePostingVO.setUpdatedDate(crtDate);			
						
						lcChequePostingVO.setLocCode(lStrLocCode);
						lcChequePostingVO.setDbId(new BigDecimal(dbId));						
						
						//Logic for Insert/Update	
							
						long lILcChqId=0;
						if(StringUtility.getParameter("LcChqId"+i, request) !=null  && !StringUtility.getParameter("LcChqId"+i, request).equals(""))
							lILcChqId=Long.parseLong((String)StringUtility.getParameter("LcChqId"+i, request));
						glogger.info("-----INSIDE LOOP lILcChqId-----------"+lILcChqId);
						
						if(StringUtility.getParameter("chk"+i, request) !=null  && !StringUtility.getParameter("chk"+i, request).equals(""))
						{
							glogger.info("-----INSIDE DELETE----------"+i);
							lcChequePostingVO.setLcChqId(new BigDecimal(lILcChqId));								
							lArrDeleteChequePosting.add(lcChequePostingVO);
							
						}								
						else if(lILcChqId != 0)
						{									
							glogger.info("-----INSIDE UPDATE----------");
							lcChequePostingVO.setLcChqId(new BigDecimal(lILcChqId));
							lArrUpdateChequePosting.add(lcChequePostingVO);
							
						}
						else
						{
							//Code for Getting Auto Generated Cheque Id from cmn_table_seq_mst table
							glogger.info("-----INSIDE INSERT-----------");
							BptmCommonServiceImpl serviceImplBudHd=new BptmCommonServiceImpl();
							//SeqNumServiceImpl serviceImplBudHd = new SeqNumServiceImpl();
							long lLngChequeId = serviceImplBudHd.getNextSeqNum("trn_lc_cheque_posting", p_objServiceArgs);
							glogger.info("------Auto generate LC CHEQUE ID------"+lLngChequeId);									
														
							lcChequePostingVO.setLcChqId(new BigDecimal(lLngChequeId));									
							lArrInsertChequePosting.add(lcChequePostingVO);
						}								
												
				   }					
				}
				
				glogger.info("---------SIZE OF DELETE CHQ LST----------------"+lArrDeleteChequePosting.size());
				glogger.info("---------SIZE OF UPDATE CHQ LST----------------"+lArrUpdateChequePosting.size());
				glogger.info("---------SIZE OF INSERT CHQ LST----------------"+lArrInsertChequePosting.size());
				
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
				ArrayList lArrDeleteBudgetPosting=new ArrayList();
				ArrayList lArrUpdateBudgetPosting=new ArrayList();
				ArrayList lArrInsertBudgetPosting=new ArrayList();
				
				if(!StringUtility.getParameter("totalBudRow", request).equals(""))
					lITotBudRow=Integer.parseInt((String)StringUtility.getParameter("totalBudRow", request));
				glogger.info("\n------------ lITotBudRow----------- "+lITotBudRow);
				
				for(int i=0;i<lITotBudRow ;i++)
				{	
					if(!StringUtility.getParameter("cmbClassOfExp"+i, request).equals("-1") && !StringUtility.getParameter("cmbClassOfExp"+i, request).equals(""))
					{
						if(!StringUtility.getParameter("cmbClassOfExp"+i, request).equals("-1"))
							lStrClassOfExp=(String)StringUtility.getParameter("cmbClassOfExp"+i, request);
						glogger.info("\n------------ lStrClassOfExp----------- "+lStrClassOfExp);
						if(!StringUtility.getParameter("cmbFund"+i, request).equals("-1"))
							lStrFund=(String)StringUtility.getParameter("cmbFund"+i, request);
						glogger.info("\n------------ lStrFund----------- "+lStrFund);
						if(!StringUtility.getParameter("txtDrwOff"+i, request).equals(""))
							lStrDrwingOff=(String)StringUtility.getParameter("txtDrwOff"+i, request);
						glogger.info("\n------------ lStrDrwingOff----------- "+lStrDrwingOff);
						if(!StringUtility.getParameter("cmbDemandNo"+i, request).equals("-1"))
							lStrDemandNo=(String)StringUtility.getParameter("cmbDemandNo"+i, request);
						glogger.info("\n------------ lStrDemandNo----------- "+lStrDemandNo);
						if(!StringUtility.getParameter("cmbBudgetType"+i, request).equals("-1"))
							lStrBudgetType=(String)StringUtility.getParameter("cmbBudgetType"+i, request);
						glogger.info("\n------------ lStrBudgetType----------- "+lStrBudgetType);
						if(!StringUtility.getParameter("txtSchemeNo"+i, request).equals(""))
							lLngSchemeNo=Long.parseLong((String)StringUtility.getParameter("txtSchemeNo"+i, request));
						glogger.info("\n------------ lLngSchemeNo----------- "+lLngSchemeNo);
						if(!StringUtility.getParameter("cmbMjrHd"+i, request).equals("-1"))
							lStrMjrHd=(String)StringUtility.getParameter("cmbMjrHd"+i, request);
						glogger.info("\n------------ lStrMjrHd----------- "+lStrMjrHd);
						if(!StringUtility.getParameter("cmbSubMjrHd"+i, request).equals("-1"))
							lStrSubMjrhd=(String)StringUtility.getParameter("cmbSubMjrHd"+i, request);
						glogger.info("\n------------ lStrSubMjrhd----------- "+lStrSubMjrhd);
						if(!StringUtility.getParameter("cmbMinHd"+i, request).equals("-1"))
							lStrMinHd=(String)StringUtility.getParameter("cmbMinHd"+i, request);
						glogger.info("\n------------ lStrMinHd----------- "+lStrMinHd);
						if(!StringUtility.getParameter("cmbSubHd"+i, request).equals("-1"))
							lStrSubHd=(String)StringUtility.getParameter("cmbSubHd"+i, request);
						glogger.info("\n------------ lStrSubHd----------- "+lStrSubHd);
						if(!StringUtility.getParameter("txtDtlHd"+i, request).equals(""))
							lStrDtlHd=(String)StringUtility.getParameter("txtDtlHd"+i, request);
						glogger.info("\n------------ lStrDtlHd----------- "+lStrDtlHd);
						if(!StringUtility.getParameter("txtObjHd"+i, request).equals(""))
							lStrObjHd=(String)StringUtility.getParameter("txtObjHd"+i, request);
						glogger.info("\n------------ lStrObjHd----------- "+lStrObjHd);
						if(!StringUtility.getParameter("txtExpAmt"+i, request).equals(""))
							ldblExpAmt=Double.parseDouble((String)StringUtility.getParameter("txtExpAmt"+i, request));
						glogger.info("\n------------ ldblExpAmt----------- "+ldblExpAmt);	
						
						
						lcBudgetPostingVO = new TrnLcBudgetPosting();
						
						
						//setting the value in ChequePosting VO
						
						lcBudgetPostingVO.setLcExpId(new BigDecimal(lILcExpId));
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
						lcBudgetPostingVO.setUpdatedUserId(new BigDecimal(updatedUserId));
						lcBudgetPostingVO.setUpdatedPostId(new BigDecimal(updatedPostId));
						lcBudgetPostingVO.setUpdatedDate(crtDate);
						
						lcBudgetPostingVO.setLocCode(lStrLocCode);
						lcBudgetPostingVO.setDbId(new BigDecimal(dbId));
						
						//Logic for Insert/Update	
						
						long lILcBudId=0;
						if(StringUtility.getParameter("LcBudId"+i, request) !=null  && !StringUtility.getParameter("LcBudId"+i, request).equals(""))
							lILcBudId=Long.parseLong((String)StringUtility.getParameter("LcBudId"+i, request));
						glogger.info("-----INSIDE LOOP LcBudId-----------"+lILcBudId);
						
						if(StringUtility.getParameter("chkDtlPost"+i, request) !=null  && !StringUtility.getParameter("chkDtlPost"+i, request).equals(""))
						{
							glogger.info("-----INSIDE DELETE----------"+i);
							lcBudgetPostingVO.setLcBudId(new BigDecimal(lILcBudId));								
							lArrDeleteBudgetPosting.add(lcBudgetPostingVO);
							
						}								
						else if(lILcBudId != 0)
						{									
							glogger.info("-----INSIDE UPDATE----------");
							lcBudgetPostingVO.setLcBudId(new BigDecimal(lILcBudId));
							lArrUpdateBudgetPosting.add(lcBudgetPostingVO);
							
						}
						else
						{
							glogger.info("-----INSIDE INSERT----------");
							//Code for Getting Auto Generated Lc Budget Id from cmn_table_seq_mst table
							BptmCommonServiceImpl serviceImplBudHd=new BptmCommonServiceImpl();
							//SeqNumServiceImpl serviceImplBudHd = new SeqNumServiceImpl();
							long lLngBudId = serviceImplBudHd.getNextSeqNum("trn_lc_budget_posting", p_objServiceArgs);
							glogger.info("------Auto generate LC BUDGET ID------"+lLngBudId);															
														
							lcBudgetPostingVO.setLcBudId(new BigDecimal(lLngBudId))	;							
							lArrInsertBudgetPosting.add(lcBudgetPostingVO);
						}								
					}
				}
				
				glogger.info("---------SIZE OF DELETE BUD LST----------------"+lArrDeleteBudgetPosting.size());
				glogger.info("---------SIZE OF UPDATE BUD LST----------------"+lArrUpdateBudgetPosting.size());
				glogger.info("---------SIZE OF INSERT BUD LST----------------"+lArrInsertBudgetPosting.size());
				//..........................................................................
				
				//Code For Getting Data From Deduction Table
				
				int lITotDeductionRow=0;
				String lStrDedId="";
				String lStrEdpCode="";
				double lDblDedAmt=0.0;
				ArrayList lArrDeductionPosting=new ArrayList();
				TrnLcDeductionPosting lcDeductionVo=null;
				
				if(!StringUtility.getParameter("dedTotalRow", request).equals(""))
					lITotDeductionRow=Integer.parseInt((String)StringUtility.getParameter("dedTotalRow", request));
				glogger.info("\n------------ lITotDeductionRow----------- "+lITotDeductionRow);
				
				for(int i=1;i<=lITotDeductionRow ;i++)
				{
					
					if(!StringUtility.getParameter("txtDedId"+i, request).equals(""))
						lStrDedId=(String)StringUtility.getParameter("txtDedId"+i, request);
					glogger.info("\n------------ lStrDedId----------- "+lStrDedId);
					
					if(!StringUtility.getParameter("txtDedCode"+i, request).equals(""))
						lStrEdpCode=(String)StringUtility.getParameter("txtDedCode"+i, request);
					glogger.info("\n------------ lStrEdpCode----------- "+lStrEdpCode);
					
					if(!StringUtility.getParameter("txtDedAmt"+i, request).equals(""))
						lDblDedAmt=Double.parseDouble((String)StringUtility.getParameter("txtDedAmt"+i, request));
					glogger.info("\n------------ lDblDedAmt----------- "+lDblDedAmt);	
					
					lcDeductionVo=new TrnLcDeductionPosting();
					
					//Setting the value in deduction VO	
					lcDeductionVo.setLcDedId(new BigDecimal(lStrDedId));
					lcDeductionVo.setLcExpId(new BigDecimal(lILcExpId));
					lcDeductionVo.setEdpCode(lStrEdpCode);
					lcDeductionVo.setAmount(new BigDecimal(lDblDedAmt));
					
					//lcDeductionVo.setLtstCntr((byte)lILtstCntr);					
					
					lcDeductionVo.setCreatedUserId(new BigDecimal(createdUserId));
					lcDeductionVo.setCreatedPostId(new BigDecimal(createdPostId));
					lcDeductionVo.setCreatedDate(crtDate);
					lcDeductionVo.setUpdatedUserId(new BigDecimal(updatedUserId));
					lcDeductionVo.setUpdatedPostId(new BigDecimal(updatedPostId));
					lcDeductionVo.setUpdatedDate(crtDate);
					
					lcDeductionVo.setLocCode(lStrLocCode);
					lcDeductionVo.setDbId(new BigDecimal(dbId));
					
					lArrDeductionPosting.add(lcDeductionVo);
				}
				glogger.info("---------SIZE OF DEDUCTION LST----------------"+lArrDeductionPosting.size());
				//...........................................................................
				
				glogger.info("\n------------ END OF ADVICE RECEIVED UPDATE VOGEN ----------- ");			
				
				p_objServiceArgs.put("lObjLcDtlPostingVo",lObjLcDtlPostingVo);
				
				p_objServiceArgs.put("lArrDeleteChequePosting",lArrDeleteChequePosting);
				p_objServiceArgs.put("lArrUpdateChequePosting",lArrUpdateChequePosting);
				p_objServiceArgs.put("lArrInsertChequePosting",lArrInsertChequePosting);
				
				p_objServiceArgs.put("lArrDeleteBudgetPosting",lArrDeleteBudgetPosting);
				p_objServiceArgs.put("lArrUpdateBudgetPosting",lArrUpdateBudgetPosting);
				p_objServiceArgs.put("lArrInsertBudgetPosting",lArrInsertBudgetPosting);
				
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
