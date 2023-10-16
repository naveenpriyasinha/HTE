package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public class TreasuryTransferCaseDAOImpl implements TreasuryTransferCaseDAO {
	
	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory = null;

    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");

	public TreasuryTransferCaseDAOImpl(SessionFactory sessionFactory) 
    {
		this.sessionFactory = sessionFactory;
	}
    
     public Session getSession() 
     {
          boolean allowCreate = true;
          return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
     }
	/**
     * It Gets the Pensioner Data like name and old trsury 
     * call when PPO No is entred.
     * 
     * @param inputMap
     * @return objRes ResultObject
     */
	public List getPensionerDtl(String lPPONO, Long lLngLangId,String lStrLocCode) throws Exception
	{
		List lPensionerInfo = null;
		String lStrStatus = null;
		
        lStrStatus = bundleConst.getString("STATUS.CONTINUE");
        
		try{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();

			lSBQuery.append(" select mph.firstName || ' ' || mph.middleName || ' ' || mph.lastName, " );
			lSBQuery.append(" cs.stateName, clm.locName , clm.parentLocId,");
			lSBQuery.append(" (SELECT CL.locName FROM CmnLocationMst CL WHERE CL.locationCode = clm.parentLocId)");
			lSBQuery.append(" from TrnPensionRqstHdr tph, MstPensionerHdr mph, CmnLocationMst clm, CmnStateMst cs ");
			lSBQuery.append(" where mph.pensionerCode = tph.pensionerCode  ");
			lSBQuery.append(" and clm.locationCode = tph.locationCode and tph.ppoNo = :lPPONO ");
			lSBQuery.append(" and cs.stateCode = mph.stateCode");
			lSBQuery.append(" and tph.status = :lStatus ");
			lSBQuery.append(" and tph.locationCode = :lLocID ");
            lSBQuery.append(" and cs.cmnLanguageMst.langId = :lLangId and clm.cmnLanguageMst.langId = :lLangId ");
            
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			lQuery.setParameter("lPPONO", lPPONO);
			lQuery.setParameter("lStatus", lStrStatus);
			lQuery.setParameter("lLocID", lStrLocCode);
            lQuery.setParameter("lLangId", lLngLangId);

			lPensionerInfo = (List) lQuery.list();
			
		}
        catch (Exception e) 
        {
			logger.error("Error is : " + e, e);
            throw(e);
		}
		return lPensionerInfo;
	}
	
	/**
	 * Return List of Sended Transfer Cases
	 * 
	 * @param Map :lMapInput
	 * @return List
	 */
	public List getTrasferCases(Map inputMap) throws Exception
    {
		StringBuffer lSBQuery = new StringBuffer();
		       
        String lStrStatus = bundleConst.getString("STATUS.CASETRNFGUJ");
		
		List lLstSendedCases = null;
        
        Long lLngLangId = SessionHelper.getLangId(inputMap);

		String lStrLocCode = SessionHelper.getLocationCode(inputMap);
				
		try
        {
            Session ghibSession = getSession();
            lSBQuery.append(" select TP.ppoNo, MP.firstName || ' ' || MP.middleName || ' ' || MP.lastName, " );
			lSBQuery.append(" CM.locName, CM.parentLocId,");
			lSBQuery.append(" (SELECT CL.locName FROM CmnLocationMst CL WHERE CL.locationCode = CM.parentLocId)");
			lSBQuery.append(" from TrnPensionRqstHdr TP, MstPensionerHdr MP, CmnLocationMst CM ");
			lSBQuery.append(" where MP.pensionerCode = TP.pensionerCode  ");
			lSBQuery.append(" and CM.locationCode = MP.locationCode ");
			lSBQuery.append(" and TP.locationCode = :lStrLocCode ");
			lSBQuery.append(" and TP.status = :lStatus ");
            lSBQuery.append(" and CM.cmnLanguageMst.langId = :lLangId ");
			
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("lStatus", lStrStatus);
			lQuery.setParameter("lStrLocCode", lStrLocCode);
            lQuery.setParameter("lLangId", lLngLangId);
            
            List lRstLst = lQuery.list();
            
            if(lRstLst != null && lRstLst.size() > 0)
            {
                lLstSendedCases = lRstLst;
            }
		} 
        catch (Exception e) 
        {
			logger.error("Error is : " + e, e);
            throw(e);
		}

		return lLstSendedCases;
	}
	
	/**
     * get TresuryList for transfer case...
     * 
     * Written By Sagar
     */
	public List getAllTreasuryLst() throws Exception
    {
		List<CommonVO> lListReturn = new ArrayList<CommonVO>();
		List restLst = null;		
		try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();

			lSBQuery.append(" select CLM.locId, CLM.locName " );
			lSBQuery.append(" from CmnLocationMst CLM ");
			lSBQuery.append(" where CLM.parentLocId = 100051 order by CLM.locName ");
			
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			restLst = lQuery.list();
			
			if (restLst != null)
            {
                Iterator lItr = restLst.iterator();
                Object[] lObj = null;
                CommonVO lComVo = null;

                while (lItr.hasNext())
                {
                    lObj = (Object[]) lItr.next();
                    
                    if (lObj != null)
                    {
                        lComVo = new CommonVO();
                        lComVo.setCommonId(lObj[0].toString());
                        lComVo.setCommonDesc(lObj[1].toString());
                        lListReturn.add(lComVo);
                    }
                }
            }
			
		}catch (Exception e) {
			logger.error("Error is : " + e, e);
            throw(e);
		}
		
		return lListReturn ;
	}
	
	
	/**
     * get All State List for transfer case...
     * 
     * Written By Sagar
     */
	public List getAllStateLst( Long lLngLangId) throws Exception
    {
		
		List<CommonVO> lListReturn = new ArrayList<CommonVO>();
		List restLst = null;		
		
		try{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();

			lSBQuery.append(" select csm.stateId, csm.stateName " );
			lSBQuery.append(" from CmnStateMst csm ");
			lSBQuery.append(" where csm.cmnLanguageMst.langId = :langID order by csm.stateName ");
			
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			lQuery.setParameter("langID", lLngLangId);
			
			restLst = lQuery.list();
			
			if (restLst != null)
            {
                Iterator lItr = restLst.iterator();
                Object[] lObj = null;
                CommonVO lComVo = null;

                while (lItr.hasNext())
                {
                    lObj = (Object[]) lItr.next();
                    
                    if (lObj != null)
                    {
                        lComVo = new CommonVO();
                        lComVo.setCommonId(lObj[0].toString());
                        lComVo.setCommonDesc(lObj[1].toString());
                        lListReturn.add(lComVo);
                    }
                }
            }
			
		}catch (Exception e) {
			logger.error("Error is : " + e, e);
            throw(e);
		}
		
		return lListReturn ;
	}
	
	/**
     * get {@link TrnPensionRqstHdr} VO
     * 
     * Written By Sagar
     */

	public TrnPensionRqstHdr getTrnPensionRqstHdrForReceive(String lStrPPONO,String lStrLocCode) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		String lStrStatus = null;
				
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
			lStrStatus = bundleConst.getString("STATUS.CASETRNFGUJ");
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.ppoNo = :lppoNo AND A.status = :lStatus AND A.locationCode = :locCode ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lppoNo", lStrPPONO.toString());
	        lQuery.setParameter("lStatus", lStrStatus);
	        lQuery.setParameter("locCode", lStrLocCode);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lobjPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lobjPensionRqstHdr;
	}	
	
	/**
     * get {@link getMstPensionerDtls} VO
     * 
     * Written By Sagar
     */

	
	public MstPensionerDtls getMstPensionerDtls(String lStrPensionerCode) throws Exception
	{
		MstPensionerDtls lobjMstPensionerDtls = new MstPensionerDtls();
		String lStrActvFlag = null;
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
	
			lStrActvFlag = bundleConst.getString("ACTV.YES");	
			
	        lSBQuery.append(" FROM MstPensionerDtls A WHERE A.pensionerCode = :lPensionerCode AND A.activeFlag = :lactvFlag ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lPensionerCode", lStrPensionerCode.toString());
	        lQuery.setParameter("lactvFlag", lStrActvFlag);       
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lobjMstPensionerDtls = (MstPensionerDtls) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lobjMstPensionerDtls;
	}
	
	/**
     * get {@link getRejectedTransferCasePK} VO
     * 
     * Written By Sagar
     */

	
	public Long getRejectedTransferCasePK(String lStrPPONo,String lStrLocCode) throws Exception
	{
		Long lTrnPensionRqstHdrPK = null;
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" SELECT PRH.pensionRequestId FROM TrnPensionRqstHdr PRH ");
	        lSBQuery.append(" WHERE PRH.ppoNo = :lPPONO AND PRH.locationCode = :lCurLocID ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lPPONO", lStrPPONo);
	        lQuery.setParameter("lCurLocID", lStrLocCode);
	       
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lTrnPensionRqstHdrPK = new Long(lLstVO.get(0).toString());
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
          throw(e);
	   }

		return lTrnPensionRqstHdrPK;
	}    
}
