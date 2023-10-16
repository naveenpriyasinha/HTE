package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;

public class TrnPensionArrearDtlsDAOImpl extends GenericDaoHibernateImpl<TrnPensionArrearDtls, Long> implements TrnPensionArrearDtlsDAO{
	
    /* Global Variable for Logger Class */
    private Log logger = LogFactory.getLog(getClass());


    /* Global Variable for Session Class */
    
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    
	public TrnPensionArrearDtlsDAOImpl(Class<TrnPensionArrearDtls> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
    
    /**
     * Getting a List of Vo's of TnrPensionArrearDtls.
     * 
     *  @author Sagar
     *  @param lPensionCode
     *  @return List
     */
    public List<TrnPensionArrearDtls> getTrnPensionArrearDtlsVo(String lStrPnsnCode) throws Exception
    {
        List <TrnPensionArrearDtls> lPensionArrearDtlsVOLst = null;
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" FROM  TrnPensionArrearDtls H WHERE H.pensionerCode = :lPnsrCode ");
            
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lPnsrCode", lStrPnsnCode);
           
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    lPensionArrearDtlsVOLst = new ArrayList<TrnPensionArrearDtls>();
                    for(int i=0;i<lLstVO.size();i++)
                    {
                        lPensionArrearDtlsVOLst.add((TrnPensionArrearDtls) lLstVO.get(i));
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lPensionArrearDtlsVOLst;
    }
    
    /**
     * Get a Arrear Amount for the Given month.
     * @author Sagar  
     */
    public Double getArrearForMonth(Integer lForMonth,String lStrPnsnCode) throws Exception
    {
        Double lArrearAmount = 0D;
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT SUM(A.differenceAmount) FROM  TrnPensionArrearDtls A");
            lSBQuery.append(" WHERE A.paymentFromYyyymm <= :lForMonth AND A.paymentToYyyymm >= :lForMonth");
            lSBQuery.append(" AND A.pensionerCode = :lPnsrCode");
            lSBQuery.append(" GROUP BY A.pensionerCode");
            
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lPnsrCode", lStrPnsnCode);
            lQuery.setParameter("lForMonth", lForMonth);
           
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    if(lLstVO.get(0) != null)
                    {
                        lArrearAmount = Double.valueOf(lLstVO.get(0).toString());
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lArrearAmount;
    }
    
    /**
     * Get a TI Arrear Amount for the Given month.
     * @author Sagar  
     */
    public Double getTIArrearForMonth(Integer lForMonth,String lStrPnsnCode) throws Exception
    {
        Double lTIArrearAmount = 0D;
        
        String lStrTI = bundleConst.getString("ARREAR.TI");
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT SUM(A.differenceAmount) FROM  TrnPensionArrearDtls A");
            lSBQuery.append(" WHERE A.paymentFromYyyymm <= :lForMonth AND A.paymentToYyyymm >= :lForMonth");
            lSBQuery.append(" AND A.pensionerCode = :lPnsrCode AND A.arrearFieldType = :lTI ");
            lSBQuery.append(" GROUP BY A.pensionerCode");
            
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lPnsrCode", lStrPnsnCode);
            lQuery.setParameter("lForMonth", lForMonth);
            lQuery.setParameter("lTI", lStrTI);
           
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    if(lLstVO.get(0) != null)
                    {
                        lTIArrearAmount = Double.valueOf(lLstVO.get(0).toString());
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lTIArrearAmount;
    }
    
    /**
     * Get a Fields vise total Arrear Amount for the Given month.
     * @author Sagar  
     */
	public ArrayList getArrearDtls(String lStrPensionerCode,Integer lStrForMonth) throws Exception
	{
		ArrayList larrArrearDtls = new ArrayList();
		
		try
		{
			Session ghibSession = getSession();
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" SELECT H.arrearFieldType,SUM(H.differenceAmount) FROM TrnPensionArrearDtls H WHERE H.pensionerCode = :lPnsrCode ");
	        lSBQuery.append(" AND H.paymentFromYyyymm <= :lForMonth AND H.paymentToYyyymm >= :lForMonth GROUP BY H.arrearFieldType ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode.toString());
	        lQuery.setParameter("lForMonth", lStrForMonth.intValue());
	       
	        List lLstVO = lQuery.list();
	        if(lLstVO != null && lLstVO.size() > 0)
            {
            	Iterator itr = lLstVO.iterator();
            	while (itr.hasNext())
            	{
            		Object tuple = (Object)itr.next();
				
            		larrArrearDtls.add(tuple);            		
				}
            }
	   }
	   catch(Exception e)
	   {
	 		  logger.error(" Error is : " + e, e);
	 		  throw(e);
	   }
		return larrArrearDtls;
	}

}
