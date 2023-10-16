package com.tcs.sgv.onlinebillprep.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablAmtDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablHdr;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablRqst;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablTrvlDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTarqstExpsumm;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTarqstHdr;

/**
*	TABillDAOImpl
*	Its a DAO for Methods Common to all Bills..
*	
*	@author Nirav Bumia
*	@version 1.0
*/
public class TABillDAOImpl implements TABillDAO 
{
    Log gLogger = LogFactory.getLog(getClass());
    Session ghibSession = null;

    public TABillDAOImpl(SessionFactory sessionFactory)
    {
        ghibSession = sessionFactory.getCurrentSession();
    }
    
    /**
     * Method for get a Travel bill Details from request.
     */
	public List getTABillRqstDtls(Long lLngAprvdRqstId,ServiceLocator serv)
	{
		TrnTarqstHdr lObjTrnTaRqstHdr = null;
        TrnTablRqst lObjTrnTaBlRqst = null;
        TrnTarqstExpsumm lObjTrnTaRqstExpSum = null;    
        TrnTarqstHdrDAO lObjTrnTaRqstHdrDao = null;
        TrnTablRqstDAO lObjTrnTaBlRqstDao = null;
        TrnTarqstExpsummDAO lObjTrnTaRqstExpSumDao = null;
        
		OnlineBillDAO lObjOnlineBillDao = null;
		List lLstVOs = new ArrayList();	
        List lLstTrnTablRqst = new ArrayList();        
        StringBuffer lsb = new StringBuffer();
       
        List lLstRsltSet = null;  
        Object [] lObjArr = null;
        
        lsb.append(" SELECT a.trnTarqstHdrId, b.trnTablRqstId, c.trnTarqstExpsummId");
        lsb.append(" FROM TrnTarqstHdr a, TrnTablRqst b, TrnTarqstExpsumm c");
        lsb.append(" WHERE a.trnTarqstHdrId = b.trnTarqstHdrId "); 
        lsb.append(" AND a.trnTarqstHdrId = c.trnTarqstHdrId ");
        lsb.append(" AND a.trnAprvdRqstId = :aprvdRqstId " );
 
        Query lQuery = ghibSession.createQuery(lsb.toString());
        lQuery.setParameter("aprvdRqstId", lLngAprvdRqstId);
        
        gLogger.info("Query to fetch PK of TA Bill Rqst Tables is - "+ lQuery);
        
        lLstRsltSet = lQuery.list();        
        
        gLogger.info("Size is - "+ lLstRsltSet.size());        
        
        if(lLstRsltSet != null & lLstRsltSet.size() > 0)
        {
            lObjArr = (Object [])lLstRsltSet.get(0);
            lObjTrnTaRqstHdrDao = new TrnTarqstHdrDAOImpl(TrnTarqstHdr.class, serv.getSessionFactory());
            lObjTrnTaRqstHdr = (TrnTarqstHdr) lObjTrnTaRqstHdrDao.read((Long)lObjArr[0]);
             
            lObjTrnTaRqstExpSumDao = new TrnTarqstExpsummDAOImpl(TrnTarqstExpsumm.class, serv.getSessionFactory());
            lObjTrnTaRqstExpSum = (TrnTarqstExpsumm) lObjTrnTaRqstExpSumDao.read((Long)lObjArr[2]);
            
            //This logic is used because for every entry in header table, there may be more than one entry
            //in detail table. For instance, one travel request can consist of more than one journeys.
            for(int iCntJrnys = 0; iCntJrnys < lLstRsltSet.size() ; iCntJrnys++)
            {
                lObjArr = (Object [])lLstRsltSet.get(iCntJrnys);            
                lObjTrnTaBlRqstDao = new TrnTablRqstDAOImpl(TrnTablRqst.class, serv.getSessionFactory());
                lObjTrnTaBlRqst = (TrnTablRqst) lObjTrnTaBlRqstDao.read((Long)lObjArr[1]);
                lLstTrnTablRqst.add(lObjTrnTaBlRqst);
            }
        }
        
        lLstVOs.add(lObjTrnTaRqstHdr);
        lLstVOs.add(lObjTrnTaRqstExpSum); 
        lLstVOs.add(lLstTrnTablRqst);        
		return lLstVOs;
	}
	
	/**
     * Method for get a PKs of TA Bill Tables.
     */
    public List getPKForTable(long lLngBillNo)
    {
        StringBuffer lsb = new StringBuffer();
        List lLstRsltSet = null;
        
        Long lLngObjBillNo = new Long(lLngBillNo);
                
        lsb.append(" SELECT tthdr.trnTablHdrId ,ttad.trnTablAmtDtlsId ,tttd.trnTablTrvlDtlsId "); 
        lsb.append(" FROM TrnTablHdr tthdr, TrnTablAmtDtls ttad, TrnTablTrvlDtls tttd ");
        lsb.append(" WHERE tthdr.trnTablHdrId = ttad.trnTablHdrId AND ttad.trnTablHdrId = tttd.trnTablHdrId ");
        lsb.append(" And tthdr.billNo = :billNo ");
        
        Query lQuery = ghibSession.createQuery(lsb.toString());
        
        lQuery.setParameter("billNo",lLngObjBillNo);
        
        lLstRsltSet = lQuery.list();
               
        return lLstRsltSet;
    }	
	
    /**
     * Method for get a TA Bill Detail.
     */
	public Map getTABillData(Long billNo, ServiceLocator serv){
		
		Object[] lObjArrPK = null;
		List lLstPK = null;
		
		Map lMapTABillData = new HashMap();
		
		TrnTablHdr lObjTrnTablHdr = null;
		TrnTablAmtDtls lObjTrnTablAmtDtls = null;
		TrnTablTrvlDtls lObjTrnTablTrvlDtls = null;
		
		TrnTablHdrDAO lObjTrnTablHdrDAO = null;
		TrnTablAmtDtlsDAO lObjTrnTablAmtDtlsDAO = null;
		TrnTablTrvlDtlsDAO lObjTrnTablTrvlDtlsDAO = null;
		
		List<TrnTablTrvlDtls> lLstTrnTablTrvlDtls = new ArrayList<TrnTablTrvlDtls>();
		
		lLstPK = getPKForTable(billNo);
		
		if(lLstPK != null & lLstPK.size()!= 0){
			
			lObjArrPK = (Object[]) lLstPK.get(0);
			
			lObjTrnTablHdrDAO = new TrnTablHdrDAOImpl(TrnTablHdr.class, serv.getSessionFactory());
			lObjTrnTablHdr = (TrnTablHdr)lObjTrnTablHdrDAO.read((Long) lObjArrPK[0]);
			lMapTABillData.put("TrnTablHdr" , lObjTrnTablHdr );
			
			
			lObjTrnTablAmtDtlsDAO = new TrnTablAmtDtlsDAOImpl(TrnTablAmtDtls.class, serv.getSessionFactory());
			lObjTrnTablAmtDtls = (TrnTablAmtDtls)lObjTrnTablAmtDtlsDAO.read((Long) lObjArrPK[1]);
			lMapTABillData.put("TrnTablAmtDtls" , lObjTrnTablAmtDtls );
			
			for(int i=0; i<lLstPK.size(); i++){
				
				lObjArrPK = (Object [])lLstPK.get(i);
				
				lObjTrnTablTrvlDtlsDAO = new TrnTablTrvlDtlsDAOImpl(TrnTablTrvlDtls.class, serv.getSessionFactory());
				lObjTrnTablTrvlDtls = (TrnTablTrvlDtls)lObjTrnTablTrvlDtlsDAO.read((Long) lObjArrPK[2]);
				
				lLstTrnTablTrvlDtls.add(lObjTrnTablTrvlDtls);
			}
			
			lMapTABillData.put("TrnTablTrvlDtlsLst" , lLstTrnTablTrvlDtls);
			
		}
		
		return lMapTABillData;
	}
}