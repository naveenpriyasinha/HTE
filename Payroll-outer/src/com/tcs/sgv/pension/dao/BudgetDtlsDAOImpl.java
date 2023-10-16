package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.tcs.sgv.common.valueobject.BillEdpVO;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeChargable;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public class BudgetDtlsDAOImpl implements BudgetDtlsDAO {
	
	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory = null;

	/* Global Variable for Session Class */
	
	
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");

	public BudgetDtlsDAOImpl(SessionFactory sessionFactory) 
    {
		this.sessionFactory = sessionFactory;
	}
	
    
    public Session getSession() 
    {
        boolean allowCreate = true;
        return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }
	/**
     * get {@link TrnPensionRqstHdr} VO
     * 
     * Written By Sagar
     */
	public TrnPensionRqstHdr getTrnPensionRqstHdrDtls(String lStrPPONO, String lStrStatus) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.ppoNo = :lppoNo AND A.status = :lStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lppoNo", lStrPPONO.toString());
	        lQuery.setParameter("lStatus", lStrStatus);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lobjPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error in getTrnPensionRqstHdrDtls. Error is : " + e, e);
 		  throw(e);
	   }

		return lobjPensionRqstHdr;
	}
	
	/**
     * get {@link MstPensionerHdrDtls} VO
     * 
     * Written By Sagar
     */

	
	public RltPensionHeadcodeChargable getMstPensionHeadcodeDtls(String lStrHeadCode,String lStrBillType) throws Exception
	{
        RltPensionHeadcodeChargable lObjRltPensionHeadcodeChargable = new RltPensionHeadcodeChargable();
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM RltPensionHeadcodeChargable H WHERE H.headCode = :lHeadCode AND H.billType = :lBillType  AND H.activeFlag = 'Y' ");
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        lQuery.setParameter("lHeadCode", new Long(lStrHeadCode));
	        lQuery.setParameter("lBillType", lStrBillType);
	       
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lObjRltPensionHeadcodeChargable = (RltPensionHeadcodeChargable) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error in getMstPensionHeadcodeDtls.class BudgetDtlsDAOImpl Error is : " + e, e);
 		 throw(e);
	   }

		return lObjRltPensionHeadcodeChargable;
	}
	
	 /**
	   * Method to get all Edp details.
	   * @param lStrEdpCode
	   * @return List
	   */
	public List getExpEdpDtl(String lStrPPONO, String lStrEdpCode) throws Exception
	{
	    List<BillEdpVO> dataList = new ArrayList<BillEdpVO>();
	    try 
	    {
            Session ghibSession = getSession();
	    	StringBuffer hqlQuery = new StringBuffer();
			hqlQuery.append("select me.edpCode, me.edpDesc, me.budobjhdCode,me.receiptEdp, me.budmjrhdCode,me.budsubmjrhdCode,me.budminhdCode,me.budsubhdCode,me.buddtlhdCode,me.budobjhdCode ");
			hqlQuery.append(" from MstEdp me where me.edpCode = :lEdpCode ");
			
			Query lQuery  = ghibSession.createQuery(hqlQuery.toString());
			
			lQuery.setParameter("lEdpCode", lStrEdpCode);
			List resList = lQuery.list();
			if (resList !=null) 
			{
			  Iterator it = resList.iterator();				
			  while(it.hasNext())
			  {
			      Object[] tuple = (Object[])it.next();
			      BillEdpVO billEdpVO = new BillEdpVO();
			      billEdpVO.setEdpCode((String)tuple[0]);
			      billEdpVO.setEdpDesc((String)tuple[1]);
			      billEdpVO.setObjHdCode((String) tuple[2]);
			      billEdpVO.setReceiptEdp((String) tuple[3]);
			      billEdpVO.setBudmjrHd((String) tuple[4]);
			      billEdpVO.setBudsubmjrHd((String) tuple[5]);
			      billEdpVO.setBudminHd((String) tuple[6]);
			      billEdpVO.setBudsubHd((String) tuple[7]);
			      billEdpVO.setBuddtlHd((String) tuple[8]);
			      billEdpVO.setBudobjHd((String) tuple[9]);
			      billEdpVO.setEdpAmt(new BigDecimal(0.00));
			      
			      dataList.add(billEdpVO);
			    }
			  }
			  
		} 
	    catch(Exception e) 
	    {
	    	logger.error("Error in getExpEdpDtl.class BudgetDtlsDAOImpl Error is : " + e, e);
	    	throw(e);
		}
	return dataList;
	}
	
	
	/**
	   * Method to get Receipt Object head details By Bill Type.
	   * @param lStrPnsnerCode
	   * @param lStrBillType
	   * @return List
	   */
	  public List getRcptEdpDtlByBillType(String lStrPnsnerCode,String lStrBillType) throws Exception
	  {
		  List dataList = new ArrayList();
		  try 
		  {
              Session ghibSession = getSession();
			  StringBuffer hqlQuery = new StringBuffer();
	      
			  hqlQuery.append(" select pr.edpCode, pr.deductionType, pr.mjrhdCode,");
			  hqlQuery.append(" pr.submjrhdCode, pr.minhdCode, pr.subhdCode, pr.amount, pr.dateOfReceipt ");
			  hqlQuery.append(" from TrnPensionRecoveryDtls pr where pr.pensionerCode = :lPnsnerCode ");
			  hqlQuery.append(" And pr.recoveryFromFlag = :lBillType order by pr.edpCode ");
	      
			  Query lQuery  = ghibSession.createQuery(hqlQuery.toString());
			
			  lQuery.setParameter("lPnsnerCode", lStrPnsnerCode);
			  lQuery.setParameter("lBillType", lStrBillType.toUpperCase());
			
			  List resList = lQuery.list();
			
	      
			  if (resList !=null) 
			  {
				  Iterator it = resList.iterator();				
				  
				  while(it.hasNext())
				  {
					  Object[] tuple = (Object[])it.next();
					  BillEdpVO billEdpVO = new BillEdpVO();
					  
					  if(tuple[7] == null)
					  {
						  billEdpVO.setEdpCode((String)tuple[0]);
						  billEdpVO.setEdpCategory((String)tuple[1]);					
						  billEdpVO.setBudmjrHd((String)tuple[2]);
						  billEdpVO.setBudsubmjrHd((String)tuple[3]);
						  billEdpVO.setBudminHd((String)tuple[4]);
						  billEdpVO.setBudsubHd((String)tuple[5]);
						  if(tuple[6].toString() != null)  {
							  billEdpVO.setEdpAmt(new BigDecimal(tuple[6].toString()));							  
						  } else {
							  billEdpVO.setEdpAmt(new BigDecimal(0.00));
						  }
					  }
				  
				  dataList.add(billEdpVO);
				  }
			  }			
		  }
		  catch(Exception e) 
		    {
		    	logger.error("Error in getRcptEdpDtlByBillType. class BudgetDtlsDAOImpl Error is : " + e, e);
		    	throw(e);
			}

	return dataList;
	}
	  
	public List getRcptEdpDtlForMnthly(String lForMonth, String lStrTypeFlag,String lStrPnsnCode)	throws Exception 
	{
		List dataList = new ArrayList();
		try {
	
            Session ghibSession = getSession();
            StringBuffer hqlQuery = new StringBuffer();
	
			hqlQuery.append(" select pr.edpCode,pr.deductionType, pr.mjrhdCode, pr.submjrhdCode,pr.minhdCode,");
			hqlQuery.append(" pr.subhdCode, sum(pr.amount) from TrnPensionRecoveryDtls pr  ");
			hqlQuery.append(" where pr.recoveryFromFlag = :lStrTypeFlag and ");
			hqlQuery.append(" pr.fromMonth <= :lForMonth and pr.toMonth >= :lForMonth ");
			if(lStrPnsnCode != null)
			{
				hqlQuery.append(" and pr.pensionerCode in ("+lStrPnsnCode+")");
			}
			else
			{
				hqlQuery.append(" and 1 = 2 ");
			}
			hqlQuery.append(" group by pr.edpCode ,pr.deductionType, pr.mjrhdCode, pr.submjrhdCode,pr.minhdCode,pr.subhdCode ");
	
			Query lQuery = ghibSession.createQuery(hqlQuery.toString());
	
			lQuery.setParameter("lForMonth", Integer.parseInt(lForMonth));
			lQuery.setParameter("lStrTypeFlag", lStrTypeFlag);
			//lQuery.setParameter("lStrPnsnCode", lStrPnsnCode);
			List resList = lQuery.list();
	
			if (resList != null) {
				Iterator it = resList.iterator();
	
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					BillEdpVO billEdpVO = new BillEdpVO();
	
						billEdpVO.setEdpCode((String) tuple[0]);
						billEdpVO.setEdpCategory((String) tuple[1]);
						billEdpVO.setBudmjrHd((String) tuple[2]);
						billEdpVO.setBudsubmjrHd((String) tuple[3]);
						billEdpVO.setBudminHd((String) tuple[4]);
						billEdpVO.setBudsubHd((String) tuple[5]);
	
						if (tuple[6].toString() != null) {
							billEdpVO.setEdpAmt(new BigDecimal(tuple[6]
									.toString()));
						} else {
							billEdpVO.setEdpAmt(new BigDecimal(0.00));
						}
	
					dataList.add(billEdpVO);
				}
			}
	
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			throw (e);
		}
		return dataList;
	}	
	public Map getEdpDetails(BigDecimal headCode) throws Exception
	{
		Map resultMap = new HashMap();
		List lLst;
		Query lQuery;
		long lLngheadCode = Long.parseLong(headCode.toString());
		try
		{			
            Session ghibSession = getSession();
            String mySql = " select ph.headCode,ph.edpCode,me.budobjhdCode,me.edpDesc " +
					" from RltPensionHeadcodeChargable ph, MstEdp me " +
					" where ph.headCode=:lHeadcode and ph.edpCode=me.edpCode and ph.billType='Pension' ";
			lQuery = ghibSession.createQuery(mySql);
			lQuery.setParameter("lHeadcode", lLngheadCode);
			lLst = lQuery.list();
			if(lLst != null && !lLst.isEmpty())
			{
				Object cols[] = (Object[]) lLst.get(0);
				resultMap.put("headCode",cols[0].toString());
				resultMap.put("edpCode",cols[1].toString());
				resultMap.put("budgetCode",cols[2].toString());
				resultMap.put("edpDesc",cols[3].toString());				
			}		
		}
		catch(Exception e)
		{
			logger.error("Error is : " + e,e);
			throw(e);
		}
		return resultMap;
	}
	  
}
