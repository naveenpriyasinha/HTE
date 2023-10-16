package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.common.payroll.valueobject.HrPayCommissionMst;


public class ScaleMasterDAOImpl extends GenericDaoHibernateImpl<HrEisScaleMst, Long> implements ScaleMasterDAO {

	private ResourceBundle constantBundle = ResourceBundle.getBundle("../resources/Payroll");
	
	private String payBandParentLookupId = constantBundle.getString("payBandParentLookupId");
	
	
 	public ScaleMasterDAOImpl(Class<HrEisScaleMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	/**
 	 * 
 	 * @return list of pay commissions.
 	 */
 	public List<HrPayCommissionMst> getPayCommisions(long langId, long locId){
 		
 		Session session = getSession();
 		
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayCommissionMst hrPayCommissionMst 					   ");
	 		strQuery.append("		WHERE hrPayCommissionMst.cmnLanguageMst.langId="+langId	 	);
	 		//strQuery.append("			  and hrPayCommissionMst.cmnLocationMst.locId="+locId	);

	 		
 			Query query = session.createQuery(strQuery.toString());

 		//execute query
		return query.list();
 	}//end method
 	
 	/**
 	 * 
 	 * @return all available pay bands
 	 */
 	public List<CmnLookupMst> getPayBands(){
 		
 		Session session = getSession();
 		
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM CmnLookupMst cmnLookupMst 												");
	 		strQuery.append("		WHERE cmnLookupMst.parentLookupId IN 									");
	 		strQuery.append("			( SELECT cmnLookupParent.lookupId  									");
	 		strQuery.append("				FROM CmnLookupMst cmnLookupParent 								");
	 		strQuery.append("				 WHERE  cmnLookupParent.lookupId="+ payBandParentLookupId 		 );
	 		strQuery.append("			)																	");

 			Query query = session.createQuery(strQuery.toString());

 		//execute query
		return query.list();
 	}//end method
 	
 	/**
 	 * @remark: DEVELOPMENT STOPPED AS SCALE RELATED VAS IS TO BE DONE LATER
 	 * @param payCommission (e.g) commissionFive, commissionSix
 	 * @param payBand	(e.g) -1s, PB-1, PB-2
 	 * @return scales belonging to a particular payBand && payCommission
 	 */
 /*	public List getScaleList(String payCommission, String payBand)
 	{
 		Session session = getSession();

 		StringBuffer strQuery = new StringBuffer();
 		String strPayCommission = null;

 		// set the tables' attribute name as per commission 
 		strPayCommission = constantBundle.getString(payCommission);

 		logger.info("Fetching data for commission: "+strPayCommission);

 			strQuery.append("SELECT hrEisScaleMst.scaleId, hrEisScaleMst.scaleStartAmt, 			  ");
	 		strQuery.append("		hrEisScaleMst.scaleIncrAmt,hrEisScaleMst.scaleEndAmt,			  ");
	 		strQuery.append("		hrEisScaleMst.scaleHigherIncrAmt,hrEisScaleMst.scaleHigherEndAmt  ");
	 		strQuery.append("	FROM HrEisScaleMst hrEisScaleMst 									  ");
	 		strQuery.append("		WHERE hrEisScaleMst.scaleId IN 									  ");
	 		strQuery.append("			( SELECT scaleCommissionMpg."+strPayCommission+".scaleId  	  ");
	 		strQuery.append("				FROM ScaleCommissionMpg scaleCommissionMpg	 			  ");
	 		strQuery.append("			)															  ");

	 		Query query = session.createQuery(strQuery.toString());

	 	//execute query
 		return query.list();

 	}//end method
*/
 	public List getAllScaleData()
    {
       Criteria objCrt = null;
      
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrEisScaleMst.class);
           // objCrt.addOrder(Order.asc("scaleStartAmt"));
            //objCrt.addOrder(Order.asc("scaleEndAmt"));
            objCrt.addOrder(Order.desc("createdDate"));
            return objCrt.list();
 		
 		
    }
 	
 	
 	public HrEisScaleMst getScaleData(String sid, Long langid)
    {
 		HrEisScaleMst hrEmpInfo = new HrEisScaleMst();
       /* try
        {*/
            Session hibSession = getSession();
            String query1 = "from HrEisScaleMst as empLookup where empLookup.scaleId = "
                    + sid + " and cmnLanguageMst.langId=" + langid;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrEmpInfo = (HrEisScaleMst)sqlQuery1.uniqueResult();
            logger.info("Scale Id is-------"+sid);
            logger.info("query is----"+sqlQuery1);

        /*}
        catch (Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }*/
        return hrEmpInfo;
    }
 	
 	public List checkScaleNameAvailability(long scaleStartAmt,long scaleIncAmt,long scaleEndAmt,long scaleHigherIncrAmt,long scaleHigherEndAmt,long langId, long payBand, long payCommission, long scaleGradePay)
 	{ 
 		logger.info("inside new avalilablity paycommision: "+payCommission+"payband: "+payBand);
 		List scaleList = null;
        /* try
         {*/
             Session hibSession = getSession();
             
             String query1 = "from HrEisScaleMst as scaleMst where scaleMst.scaleStartAmt = "
                     + scaleStartAmt + " and scaleMst.scaleEndAmt=" + scaleEndAmt +
                     " and scaleMst.scaleIncrAmt=" + scaleIncAmt +
                     " and scaleMst.scaleHigherIncrAmt=" + scaleHigherIncrAmt +
                     " and scaleMst.scaleHigherEndAmt=" + scaleHigherEndAmt +
                     " and scaleMst.cmnLanguageMst.langId=" + langId +
             		 " and scaleMst.hrPayCommissionMst.id=" + payCommission +
             		 " and scaleMst.payBandId.lookupId="+payBand +
             		 " and scaleMst.scaleGradePay="+scaleGradePay;
             Query sqlQuery1 = hibSession.createQuery(query1);
             scaleList = sqlQuery1.list();
            
             logger.info("query is----"+sqlQuery1);

         /*}
         catch (Exception e)
         {
             logger.error("Error is: "+ e.getMessage());
         }*/
         return scaleList;
 	}
 	
 	
 	public List checkDuplicateEntry(long scaleStartAmt,long scaleIncAmt,long scaleEndAmt,long scaleHigherIncrAmt,long scaleHigherEndAmt,long langId, long payBand, long payCommission, long scaleGradePay,long payScaleType)
 	{ 
 		logger.info("in checkDuplicateEntry...........");
		List list = new ArrayList();
		Session hibSession = getSession();
		
		
			StringBuffer query = new StringBuffer();
			logger.info("checkDuplicateEntry");
			
			query.append(" from HrEisScaleMst as scaleMst where scaleMst.scaleStartAmt = "+ scaleStartAmt + " and scaleMst.scaleEndAmt=" + scaleEndAmt +" and scaleMst.payScaleType=" + payScaleType +" "); 
			
			if(scaleIncAmt>=0)
			{
				query.append(" and scaleMst.scaleIncrAmt=" + scaleIncAmt +" ");
			}
			
			if(payCommission>=0)
			{
				query.append(" and scaleMst.hrPayCommissionMst.id=" + payCommission +"  "); 
			}
			
			
			
			if(scaleHigherIncrAmt>=0)
			{
				query.append(" and scaleMst.scaleHigherIncrAmt=" + scaleHigherIncrAmt +" ");
			}
			
			if(scaleHigherEndAmt>=0)
			{
				query.append(" and scaleMst.scaleHigherEndAmt=" + scaleHigherEndAmt +" ");
			}
			
			
			if(payBand>0)
			{
				query.append(" and scaleMst.payBandId.lookupId="+payBand +" "); 
			}
			
			if(scaleGradePay>0)
			{
				query.append(" and scaleMst.scaleGradePay="+scaleGradePay+" ");
			}

			query.append(" and scaleMst.cmnLanguageMst.langId=" + langId +" ");
			
			
			
			
			Query sqlQuery = hibSession.createQuery(query.toString());
			
			list= sqlQuery.list();	 
			logger.info("Query to be executed is " + sqlQuery.toString());
			
			
	        logger.info("==> checkDuplicateEntry query :: "+sqlQuery);
	        logger.info("==> checkDuplicateEntry query**********8 :: "+sqlQuery.toString());
	 
		logger.info("==> checkDuplicateEntry query :: "+sqlQuery.toString());
		 
		   	    	 			 			 		
        
 		return list;
 	}
 	
 	
	public List getScaleDataByLangId(Long langid)
    {
 		List lstScaleData = new ArrayList();
        try
        {
        	Session hibSession = getSession();
    		Criteria crit = hibSession.createCriteria(HrEisScaleMst.class);
    		crit.add(Restrictions.eq("cmnLanguageMst.langId",langid));
    		lstScaleData = crit.list();
        }
        catch (Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return lstScaleData;
    }
 
	//added bu Muneendra for Payscale Type
	@SuppressWarnings("unchecked")
	public List<CmnLookupMst> getPayscaleType(){
			List payScaleTypeList = null;
 			Session session = getSession();
 		
 			StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM CmnLookupMst cmnLookupMst WHERE cmnLookupMst.parentLookupId =  10001198131");
	 		Query query = session.createQuery(strQuery.toString());
	 		payScaleTypeList = query.list(); 
	 		logger.info("payScaleTypeList:::::::::::::::::::::::"+payScaleTypeList.size());

	 		return payScaleTypeList;
			
	 	}//end method

	//Added by abhilash
	
	public List getDuplicateDataFromScaleId(String sid)
    {
		logger.info("inside getDuplicateDataFromScaleId sid is************ "+sid);
		
 		List scaleDuplicateList = null;
        
             Session hibSession = getSession();
             String query1 = "from HrEisSgdMpg sgdmpg where sgdmpg.hrEisScaleMst.scaleId in(select scaleMst.scaleId from HrEisScaleMst scaleMst where scaleMst.scaleId="+sid+") ";
                     
             Query sqlQuery1 = hibSession.createQuery(query1);
             scaleDuplicateList = sqlQuery1.list();
            
             logger.info("getDuplicateDataFromScaleId query is----"+sqlQuery1);
        
         return scaleDuplicateList;
    }
	
	//Ended by abhilash
	
}
