package com.tcs.sgv.lcm.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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
import com.tcs.sgv.lcm.dao.LcDistributionMstDAOImpl;
import com.tcs.sgv.lcm.valueobject.TrnLcDistribution;
import com.tcs.sgv.lcm.valueobject.TrnLcDistributionBudHd;

//LC Distribution Vo Generator for Get the value from JSP file.
public class LcDistributionMstVOGenImpl
extends ServiceImpl 
implements VOGeneratorService
{
    Log logger = LogFactory.getLog(getClass());
	
	public ResultObject generateMap(Map p_objServiceArgs) 
	{
		logger.info("\n------------ Inside generateMap of LcDivisionAccMstVOGenImpl----------- ");
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");		
		SimpleDateFormat sdf = null;		
		
		//getting all common default value from SessionHelper
		Long createdUserId=SessionHelper.getUserId(request);
		Long createdPostId=SessionHelper.getPostId(p_objServiceArgs);
		HttpSession session = request.getSession();
		Long lLngLocId = SessionHelper.getLocationId(p_objServiceArgs);
		Long lLngDbId = SessionHelper.getDbId(p_objServiceArgs);
		String lStrLocCode=SessionHelper.getLocationCode(p_objServiceArgs);
		
		LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
		
		try
		{
			String lStrLcOrderNo="";
			String lStrDivCode="";
			String lStrLcIssueDt="";
			String lStrEntryType="";
			String lStrLcValidFrom="";
			String lStrLcValidUpto="";
			String lStrLcInwardDt="";
			String lStrInwardNo="";
			String lStrPartyRefNo="";
			String lStrPartyRefDt="";
			String lStrGrntOrdNo="";
			String lStrGrntOrdDt="";
			Double lDblLcAmt=0.0;
			Double lDblProgAmt=0.0;
			
			logger.info("\n------------ Inside TRY/CATCH of generateMap of VOGEN----------- ");
			
			//getting value from LcDistributionMst.jsp
			
			if(!StringUtility.getParameter("txtLcNo", request).equals(""))
			    lStrLcOrderNo = StringUtility.getParameter("txtLcNo", request);		
			logger.info("\n------------ lStrLcOrderNo----------- "+lStrLcOrderNo);
			if(!StringUtility.getParameter("txtDivisionId", request).equals(""))
				lStrDivCode = (String)StringUtility.getParameter("txtDivisionId", request);	
			logger.info("\n------------ lStrDivCode----------- "+lStrDivCode);
			if(!StringUtility.getParameter("txtLcIssueDate", request).equals(""))
				lStrLcIssueDt = StringUtility.getParameter("txtLcIssueDate", request);			
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date lcIssueDt = sdf.parse(lStrLcIssueDt);
            logger.info("\n------------ lcIssueDt----------- "+lcIssueDt);
            if(!StringUtility.getParameter("cmbEntryType", request).equals(""))
            	lStrEntryType = (String)StringUtility.getParameter("cmbEntryType", request);	
            logger.info("\n------------ lStrEntryType----------- "+lStrEntryType);
            if(!StringUtility.getParameter("txtLcValidFrom", request).equals(""))
            	lStrLcValidFrom = StringUtility.getParameter("txtLcValidFrom", request);			
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date lcValidFromDt = sdf.parse(lStrLcValidFrom);
            logger.info("\n------------ lcValidFromDt----------- "+lcValidFromDt);
            if(!StringUtility.getParameter("txtLcValidUpto", request).equals(""))
            	lStrLcValidUpto = StringUtility.getParameter("txtLcValidUpto", request);			
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date lcValidUptoDt = sdf.parse(lStrLcValidUpto);
            
            if(!StringUtility.getParameter("txtLcInwardDt", request).equals(""))
            	lStrLcInwardDt = StringUtility.getParameter("txtLcInwardDt", request);			
            logger.info("------INWARD DATE----------"+lStrLcInwardDt);
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date lcInwardDt = sdf.parse(lStrLcInwardDt);
            logger.info("------INWARD DATE----------"+lcInwardDt);
            
            if(!StringUtility.getParameter("txtInwardNo", request).equals(""))
            	lStrInwardNo = (String)StringUtility.getParameter("txtInwardNo", request);
            logger.info("\n------------ lStrInwardNo----------- "+lStrInwardNo);
            if(!StringUtility.getParameter("txtPartyRefNo", request).equals(""))
            	lStrPartyRefNo = StringUtility.getParameter("txtPartyRefNo", request);		
            logger.info("\n------------ lStrPartyRefNo----------- "+lStrPartyRefNo);
            if(!StringUtility.getParameter("txtPartyRefDt", request).equals(""))
            	lStrPartyRefDt = StringUtility.getParameter("txtPartyRefDt", request);			
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date lcPartyRefDt = sdf.parse(lStrPartyRefDt);
            
            if(!StringUtility.getParameter("txtGrntOrdNo", request).equals(""))
            	lStrGrntOrdNo = StringUtility.getParameter("txtGrntOrdNo", request);
            logger.info("\n------------ lStrGrntOrdNo----------- "+lStrGrntOrdNo);
            if(!StringUtility.getParameter("txtGrntOrdDt", request).equals(""))
            	lStrGrntOrdDt = StringUtility.getParameter("txtGrntOrdDt", request);			
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date lcGrntOrdDt = sdf.parse(lStrGrntOrdDt);
            
            if(!StringUtility.getParameter("txtLcAmt", request).equals(""))
            	lDblLcAmt = Double.parseDouble((String)StringUtility.getParameter("txtLcAmt", request));
            logger.info("\n------------ lDblLcAmt----------- "+lDblLcAmt);
            if(!StringUtility.getParameter("txtProgressiveAmt", request).equals(""))
            	lDblProgAmt = Double.parseDouble((String)StringUtility.getParameter("txtProgressiveAmt", request));
            logger.info("\n------------ lDblProgAmt----------- "+lDblProgAmt);
            
            logger.info("\n------------ in TRY/CATCH After getting All Data----------- ");			
						
			
			FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			
			/*LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
			long lLongLcOrdId=(long)lLcDistributionDaoImpl.generateSequence();*/
			
			BptmCommonServiceImpl serviceImpl=new BptmCommonServiceImpl();
			//SeqNumServiceImpl serviceImpl = new SeqNumServiceImpl();
			long lLongLcOrdId = serviceImpl.getNextSeqNum("trn_lc_distribution", p_objServiceArgs);
			
			/*long lLongLcOrdId=Long.parseLong(session.getAttribute("AutoGenId").toString());
			//System.out.println("------Auto generate ID------"+lLongLcOrdId);*/
			
			String lStrLineCntr="";
			long lLngLineCntr=1;
			lStrLineCntr=lLcDistributionDaoImpl.getMaximumLineCntr();
			if(!lStrLineCntr.equals(""))
			{
			     lLngLineCntr=Long.parseLong(lStrLineCntr);
			     lLngLineCntr=lLngLineCntr+1;
			}
			char lCharActive = '0';
			int lILtstCntr=1;
			long lIFinYrId=(long)lObjFinYearDAOImpl.getFinYearIdByCurDate();
			Date crtDate = new Date();
			
			TrnLcDistribution lObjLcdistributionVo = new TrnLcDistribution();//LcDistribution VO
			
			logger.info("------Auto generate ID------"+lLongLcOrdId);
			lObjLcdistributionVo.setLcOrderId(new BigDecimal(lLongLcOrdId));
			lObjLcdistributionVo.setLineCntr(new BigDecimal(lLngLineCntr));
			lObjLcdistributionVo.setLcOrderNo(lStrLcOrderNo);
			lObjLcdistributionVo.setDivisionCode(lStrDivCode);
			lObjLcdistributionVo.setLcIssueDt(lcIssueDt);
			lObjLcdistributionVo.setEntryTypeCode(lStrEntryType);
			lObjLcdistributionVo.setLcValidFrm(lcValidFromDt);
			lObjLcdistributionVo.setLcValidTo(lcValidUptoDt);
			lObjLcdistributionVo.setInwardDt(lcInwardDt);
			lObjLcdistributionVo.setInwardNo(new BigDecimal(lStrInwardNo));
			lObjLcdistributionVo.setPartyRefNo(lStrPartyRefNo);
			lObjLcdistributionVo.setPartyRefDt(lcPartyRefDt);
			lObjLcdistributionVo.setGrntOrdNo(lStrGrntOrdNo);
			lObjLcdistributionVo.setGrntOrdDt(lcGrntOrdDt);
			lObjLcdistributionVo.setLcAmt(new BigDecimal(lDblLcAmt));
			lObjLcdistributionVo.setLcAvailableAmt(new BigDecimal(lDblProgAmt));
			
			lObjLcdistributionVo.setActive(lCharActive);
			lObjLcdistributionVo.setLtstCntr((byte)lILtstCntr);
			lObjLcdistributionVo.setFinYearId(lIFinYrId);
			lObjLcdistributionVo.setCreatedUserId(new BigDecimal(createdUserId));
			lObjLcdistributionVo.setCreatedPostId(new BigDecimal(createdPostId));
			lObjLcdistributionVo.setCreatedDate(crtDate);
			lObjLcdistributionVo.setLocCode(lStrLocCode);
			lObjLcdistributionVo.setDbId(new BigDecimal(lLngDbId));
			
			
			//Code for Getting Data from Distribution Head table
			long lLngTotRow=0;
			String lStrBudType="";
			String lStrDemandNo="";
			String lStrMjrHd="";
			String lStrSubMjrHd="";
			String lStrMinHd="";
			String lStrSubHd="";
			String lStrDtlHd="";
			String lStrObjHd="";
			int lLngSchemeNo=0;
			
			double lDblHdAmt=0.0;
			
			TrnLcDistributionBudHd lcDistributionBudHdVO =null;//LcDistributionBudHd VO
			ArrayList lArrDistributionBudHd=new ArrayList();
			
			if(!StringUtility.getParameter("totalHdRow", request).equals(""))
				lLngTotRow=Long.parseLong((String)StringUtility.getParameter("totalHdRow", request));
			logger.info("\n------------ lLngTotRow----------- "+lLngTotRow);
			
			for(int i=1;i<=lLngTotRow ;i++)
			{
				if(!StringUtility.getParameter("budgetType"+i, request).equals(""))
					lStrBudType=(String)StringUtility.getParameter("budgetType"+i, request);
				logger.info("\n------------ lStrBudType----------- "+lStrBudType);
				if(!StringUtility.getParameter("demandNo"+i, request).equals(""))
					lStrDemandNo=(String)StringUtility.getParameter("demandNo"+i, request);
				logger.info("\n------------ lStrDemandNo----------- "+lStrDemandNo);
				
				if(!StringUtility.getParameter("mjrhd"+i, request).equals(""))
					lStrMjrHd=(String)StringUtility.getParameter("mjrhd"+i, request);
				logger.info("\n------------ lStrMjrHd----------- "+lStrMjrHd);
				if(!StringUtility.getParameter("submjrhd"+i, request).equals(""))
					lStrSubMjrHd=(String)StringUtility.getParameter("submjrhd"+i, request);
				logger.info("\n------------ lStrSubMjrHd----------- "+lStrSubMjrHd);
				if(!StringUtility.getParameter("minhd"+i, request).equals(""))
					lStrMinHd=(String)StringUtility.getParameter("minhd"+i, request);
				logger.info("\n------------ lStrMinHd----------- "+lStrMinHd);
				if(!StringUtility.getParameter("subhd"+i, request).equals(""))
					lStrSubHd=(String)StringUtility.getParameter("subhd"+i, request);
				logger.info("\n------------ lStrSubHd----------- "+lStrSubHd);
				
				if(!StringUtility.getParameter("dtlHd"+i, request).equals(""))
					lStrDtlHd=(String)StringUtility.getParameter("dtlHd"+i, request);
				logger.info("\n------------ lStrDtlHd----------- "+lStrDtlHd);
				if(!StringUtility.getParameter("objHd"+i, request).equals(""))
					lStrObjHd=(String)StringUtility.getParameter("objHd"+i, request);
				logger.info("\n------------ lStrObjHd----------- "+lStrObjHd);
				if(!StringUtility.getParameter("schemeNo"+i, request).equals(""))
					lLngSchemeNo=Integer.parseInt((String)StringUtility.getParameter("schemeNo"+i, request));
				logger.info("\n------------ lLngSchemeNo----------- "+lLngSchemeNo);
				
				if(!StringUtility.getParameter("hdamt"+i, request).equals(""))
					lDblHdAmt=Double.parseDouble((String)StringUtility.getParameter("hdamt"+i, request));
				logger.info("\n------------ lDblHdAmt----------- "+lDblHdAmt);
				
				lcDistributionBudHdVO = new TrnLcDistributionBudHd();
				
				//Code for Getting Auto Generated Id from cmn_table_seq_mst table
				BptmCommonServiceImpl serviceImplBudHd=new BptmCommonServiceImpl();
				//SeqNumServiceImpl serviceImplBudHd = new SeqNumServiceImpl();
				long lLongLcOrdBudId = serviceImplBudHd.getNextSeqNum("trn_lc_distribution_bud_hd", p_objServiceArgs);
				logger.info("------Auto generate LC ORDER BUD ID------"+lLongLcOrdBudId);
				
				//setting the value in DistributionBudHd VO
				lcDistributionBudHdVO.setLcOrderBudId(new BigDecimal(lLongLcOrdBudId));
				lcDistributionBudHdVO.setLcOrderId(new BigDecimal(lLongLcOrdId));
				
				lcDistributionBudHdVO.setBudgetType(lStrBudType);
				lcDistributionBudHdVO.setDemandNo(lStrDemandNo);
				lcDistributionBudHdVO.setMjrHd(lStrMjrHd);
				lcDistributionBudHdVO.setSubMjrHd(lStrSubMjrHd);
				lcDistributionBudHdVO.setMinHd(lStrMinHd);
				lcDistributionBudHdVO.setSubHd(lStrSubHd);
				lcDistributionBudHdVO.setDtlHd(lStrDtlHd);
				lcDistributionBudHdVO.setObjHd(lStrObjHd);
				lcDistributionBudHdVO.setSchemeNo(lLngSchemeNo);
				
				lcDistributionBudHdVO.setAmount(new BigDecimal(lDblHdAmt));
				
				lcDistributionBudHdVO.setFinYearId(lIFinYrId);
				lcDistributionBudHdVO.setCreatedUserId(new BigDecimal(createdUserId));
				lcDistributionBudHdVO.setCreatedPostId(new BigDecimal(createdPostId));
				lcDistributionBudHdVO.setCreatedDate(crtDate);
				lcDistributionBudHdVO.setLocCode(lStrLocCode);
				lcDistributionBudHdVO.setLdbId(new BigDecimal(lLngDbId));
				
				lArrDistributionBudHd.add(lcDistributionBudHdVO);
				
			}
			//..................................................
			logger.info("\n------------ END OF DISTRIBUTION VOGEN ----------- ");
			logger.info("\n------------ SIZE OF ARRLIST ----------- "+lArrDistributionBudHd.size());
			
			p_objServiceArgs.put("LcdistributionVo",lObjLcdistributionVo);
			p_objServiceArgs.put("lArrDistributionBudHd",lArrDistributionBudHd);
			
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
