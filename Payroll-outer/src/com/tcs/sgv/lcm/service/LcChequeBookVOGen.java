package com.tcs.sgv.lcm.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.tcs.sgv.lcm.dao.LcChequeBookDAOImpl;
import com.tcs.sgv.lcm.valueobject.TrnLcChequeBook;

public class LcChequeBookVOGen extends ServiceImpl 
implements VOGeneratorService
{
    Log logger = LogFactory.getLog(getClass());
	
	public ResultObject generateMap(Map p_objServiceArgs) 
	{
		logger.info("\n------------ Inside generateMap of LcChequeBookVOGen----------- ");		
		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");		
		
		SimpleDateFormat sdf = null;		
		
		//getting all common default value from SessionHelper
		Long createdUserId=SessionHelper.getUserId(request);
		Long createdPostId=SessionHelper.getPostId(p_objServiceArgs);
		
		Long lLngLocId = SessionHelper.getLocationId(p_objServiceArgs);
		Long lLngDbId = SessionHelper.getDbId(p_objServiceArgs);	
		
		String lStrLocCode=SessionHelper.getLocationCode(p_objServiceArgs);
		
		LcChequeBookDAOImpl lcChequeBookDao=new LcChequeBookDAOImpl(TrnLcChequeBook.class,serv.getSessionFactory());
		try
		{
			long lILcAccNo=0;
			String lStrChqBookIssueDt="";
			long lIChqSrStart=0;
			long lIChqSrEnd=0;
		    
			logger.info("\n------------ Inside TRY/CATCH of generateMap of VOGEN----------- ");
			
			//getting value from LcDistributionMst.jsp
			if(!StringUtility.getParameter("txtLcDivAccNo", request).equals(""))
				lILcAccNo = Long.parseLong((String)StringUtility.getParameter("txtLcDivAccNo", request));	
			logger.info("\n------------ lILcAccNo----------- "+lILcAccNo);
			
			if(!StringUtility.getParameter("txtIssueDate", request).equals(""))
				lStrChqBookIssueDt = StringUtility.getParameter("txtIssueDate", request);			
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date lcChqBookIssueDt = sdf.parse(lStrChqBookIssueDt);
            logger.info("\n------------ lcChqBookIssueDt----------- "+lcChqBookIssueDt);
            
            if(!StringUtility.getParameter("txtStartChkSeries", request).equals(""))
            	lIChqSrStart = Long.parseLong((String)StringUtility.getParameter("txtStartChkSeries", request));	
            logger.info("\n------------ lIentryType----------- "+lIChqSrStart);
            
            if(!StringUtility.getParameter("txtEndChkSeries", request).equals(""))
            	lIChqSrEnd = Long.parseLong((String)StringUtility.getParameter("txtEndChkSeries", request));	
            logger.info("\n------------ lIentryType----------- "+lIChqSrEnd);
			
            logger.info("\n------------ in TRY/CATCH After getting All Data----------- ");	
            
            FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			
            BptmCommonServiceImpl serviceImpl=new BptmCommonServiceImpl();
           // SeqNumServiceImpl serviceImpl = new SeqNumServiceImpl();
			long lLongChqBookId = serviceImpl.getNextSeqNum("trn_lc_cheque_book", p_objServiceArgs);
			logger.info("------Auto generate ID------"+lLongChqBookId);			
			
			long lIFinYrId=(long)lObjFinYearDAOImpl.getFinYearIdByCurDate();
			Date crtDate = new Date();
			
			TrnLcChequeBook lcChequeBookVo = null;//TrnLcChequeBook VO
            
			String lStrResult = lcChequeBookDao.getChequeSeriesValidation(lIChqSrStart, lIChqSrEnd);
			logger.info("\n------------ lStrResult VOGEN ----------- "+lStrResult);	
			if(lStrResult.equals(""))
			{
				lcChequeBookVo = new TrnLcChequeBook();
				
				lcChequeBookVo.setChqBookId(new BigDecimal(lLongChqBookId));
				lcChequeBookVo.setLcAccNo(new BigDecimal(lILcAccNo));
				lcChequeBookVo.setIssueDt(lcChqBookIssueDt);
				lcChequeBookVo.setChqSrStart(new BigDecimal(lIChqSrStart));
				lcChequeBookVo.setChqSrEnd(new BigDecimal(lIChqSrEnd));
				
				lcChequeBookVo.setFinYearId(lIFinYrId);
				lcChequeBookVo.setCreatedUserId(new BigDecimal(createdUserId));
				lcChequeBookVo.setCreatedPostId(new BigDecimal(createdPostId));
				lcChequeBookVo.setCreatedDate(crtDate);
				lcChequeBookVo.setLocCode(lStrLocCode);
				lcChequeBookVo.setDbId(new BigDecimal(lLngDbId));
			}			
			logger.info("\n------------ END OF CHEQUE BOOK VOGEN ----------- ");			
			
			p_objServiceArgs.put("lcChequeBookVo",lcChequeBookVo);	
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
