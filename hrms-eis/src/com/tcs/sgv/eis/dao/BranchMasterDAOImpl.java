package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.CmnCountryMst;
import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.common.valueobject.RltBankBranchPay;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;


public class BranchMasterDAOImpl extends GenericDaoHibernateImpl<RltBankBranchPay, Long> implements BranchMasterDAO 
{
	
 	public BranchMasterDAOImpl(Class<RltBankBranchPay> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getAllBranchMasterData(long langId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            
            String strQuery = "select rltBankBranchPay from RltBankBranchPay rltBankBranchPay where rltBankBranchPay.langId ="  + langId+" and rltBankBranchPay.bankCode != 0 order by rltBankBranchPay.bankCode";
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	public List getAllBranchs(long bankId,long langId)
	{
		  List branchList=null;
	        
          Session hibSession = getSession();
          String strBranchQuery = "from HrEisBranchMst as BranchMst where BranchMst.hrEisBankMst.bankId=" 
        	  + bankId + " and BranchMst.cmnLanguageMst.langId=" + langId+" order by branchName";
          
          Query branchQuery = hibSession.createQuery(strBranchQuery);
          
          branchList = branchQuery.list();
      
      return branchList;
		
	}
	public RltBankBranchPay getBranchIdData(String branchId)
    {
		RltBankBranchPay hrBranchInfo = new RltBankBranchPay();
        
            Session hibSession = getSession();
            String query1 = "from RltBankBranchPay as branchLookup where branchLookup.branchId = "+ branchId;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrBranchInfo = (RltBankBranchPay)sqlQuery1.uniqueResult();
            logger.info("Branch size is:::::::::"+hrBranchInfo.getBranchName() );

        
        return hrBranchInfo;
    }
	
	public List getBranchIdFromBankCode(String branchId)
    {
		 List branchList=null;
		 
		 RltBankBranchPay hrBranchInfo = new RltBankBranchPay();
        
            Session hibSession = getSession();
            String query1 = "from RltBankBranchPay as branchLookup where branchLookup.branchId = "+ branchId;
            Query sqlQuery1 = hibSession.createQuery(query1);
            //hrBranchInfo = (RltBankBranch)sqlQuery1.uniqueResult();
            branchList = sqlQuery1.list();
            
            logger.info("getBranchIdFromBankCode size is:::::::::"+hrBranchInfo.getBranchName() );

        
        return branchList;
    }
	
	public MstBankPay getBankCode(long bankId)
    {
 		MstBankPay hrBranchInfo = new MstBankPay();
        
            Session hibSession = getSession();
            String query1 = "from MstBankPay as branchLookup where branchLookup.bankId = "+ bankId;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrBranchInfo = (MstBankPay)sqlQuery1.uniqueResult();
            logger.info("Branch size is:::::::::"+hrBranchInfo.getBankCode() );

        
        return hrBranchInfo;
    }
	
	public List getAllCountries(long langId)
	{
		CmnCountryMst cmnCountryInfo = new CmnCountryMst();
		  List countryList=null;
        
            Session hibSession = getSession();
            String query1 = "from CmnCountryMst  where  cmnLanguageMst.langId ="+ langId;
            
            Query sqlQuery1 = hibSession.createQuery(query1);
            
            //sqlQuery1.setParameter("param",langId);
            countryList = sqlQuery1.list();
        
        return countryList;
	}
	
	public List checkBranchName(String branchName,long bank_id,long langId)
	{
		List branchNameList=null;
        
        Session hibSession = getSession();
        String query1 = "from HrEisBranchMst as branchLookup where lower(branchLookup.branchName) = lower('"
                + branchName + "') and branchLookup.hrEisBankMst.bankId=" + bank_id+" and cmnLanguageMst.langId ="+ langId;
        
        Query sqlQuery1 = hibSession.createQuery(query1);
        
        //sqlQuery1.setParameter("param",langId);
        branchNameList = sqlQuery1.list();
    
    return branchNameList;
	}
	public HrEisBranchMst getDataFromTypeCode(long BranchTypeCode,long langId)
    {
		HrEisBranchMst hrBranchInfo = new HrEisBranchMst();
        
            Session hibSession = getSession();
            String query1 = "from HrEisBranchMst as branchkLookup where branchkLookup.branchTypeCode = "
                    + BranchTypeCode+" and cmnLanguageMst.langId ="  + langId;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrBranchInfo = (HrEisBranchMst)sqlQuery1.uniqueResult();

        
        return hrBranchInfo;
    }
	public List getStateFromCountry(long countryId,long langId)
	{		
		  List stateList=null;
        
            Session hibSession = getSession();
            String query1 = "from CmnStateMst  stateMst where  stateMst.cmnCountryMst.countryId = " +countryId + 
            " and  stateMst.cmnLanguageMst.langId =" + langId+"order by stateMst.stateName";
            
            Query sqlQuery1 = hibSession.createQuery(query1);
            
            //sqlQuery1.setParameter("param",langId);
            stateList = sqlQuery1.list();
        
        return stateList;
	}
	
	public List getDistrictsFromState(long stateId,long langId)
	{		
		  List distList=null;
        
            Session hibSession = getSession();
            String query1 = "from CmnDistrictMst  distMst where  distMst.cmnStateMst.stateId = " + stateId + 
            " and  distMst.cmnLanguageMst.langId =" + langId+" order by distMst.districtName";
            
            Query sqlQuery1 = hibSession.createQuery(query1);
            
            //sqlQuery1.setParameter("param",langId);
            distList = sqlQuery1.list();
            logger.info("District List size in DAO is:::::::::" + distList.size());
        
        return distList;
	}
	
	public List getTalukasFromDistrict(long distId,long langId)
	{		
		  List talukaList=null;
        
            Session hibSession = getSession();
            String query1 = "from CmnTalukaMst  talukaMst where  talukaMst.cmnDistrictMst.districtId = " + distId + 
            " and  talukaMst.cmnLanguageMst.langId =" + langId+" order by talukaMst.talukaName";
            
            Query sqlQuery1 = hibSession.createQuery(query1);
            
            //sqlQuery1.setParameter("param",langId);
            talukaList = sqlQuery1.list();
            logger.info("Taluka List size in DAO is:::::::::" + talukaList.size());
        
        return talukaList;
	}
	
	public List getVillagesFromTaluka(long talukaId,long langId)
	{		
		  List villageList=null;
        
            Session hibSession = getSession();
            String query1 = "from CmnVillageMst  villageMst where  villageMst.cmnTalukaMst.talukaId = " + talukaId + 
            " and  villageMst.cmnLanguageMst.langId =" + langId+" order by villageMst.villageName";
            
            Query sqlQuery1 = hibSession.createQuery(query1);
            
            //sqlQuery1.setParameter("param",langId);
            villageList = sqlQuery1.list();
            logger.info("Village List size in DAO is:::::::::" + villageList.size());
        
        return villageList;
	}
//	 Added by Shilpi
	public List getBranchDetailsByCodeAndLangId(long BankId,long langId)
    {
 			List branchNameList=null;
            Session hibSession = getSession();
            String query = "from HrEisBranchMst as bankLookup where bankLookup.hrEisBankMst.bankId = "
            	             + BankId +" and bankLookup.cmnLanguageMst.langId=" + langId;
            Query sqlQuery = hibSession.createQuery(query);
            branchNameList = sqlQuery.list();
            return branchNameList;
    }
	
	//	 Added by Shilpi
	public HrEisBranchMst getBranchMstVOByBranchCodeAndLang(String strBranchCode, long langId)
	{
		HrEisBranchMst hrEisBranchMst = null;
		
        Session hibSession = getSession();
        
        String query1 = "from HrEisBranchMst where branchTypeCode = '" + strBranchCode + "' and cmnLanguageMst.langId = '"+ langId +"'";
        
        Query sqlQuery1 = hibSession.createQuery(query1);
        
        List<HrEisBranchMst> hrEisBranchMstList =  sqlQuery1.list();
        
        if (hrEisBranchMstList.size() > 0)
        {
        	hrEisBranchMst = hrEisBranchMstList.get(0);
        }
        
        return hrEisBranchMst;
	}
	public Long getNextSeqNoLoc() {
		Session hibSession = getSession();
		Long seqId=0l;
		Long nextSeqId=0l;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select GENERATED_ID from  CMN_TABLE_SEQ_MST where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'RLT_BANK_BRANCH'");
		Query lQuery = hibSession.createSQLQuery(lSBQuery.toString());
		seqId = Long.valueOf(lQuery.uniqueResult().toString());
		nextSeqId=seqId+1;
		logger.info("getNextSeqNoLoc in DAO is:::::::::" + nextSeqId);
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE CMN_TABLE_SEQ_MST SET GENERATED_ID="+nextSeqId+" where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'RLT_BANK_BRANCH'");
		Query query = hibSession.createSQLQuery(sb.toString());
		query.executeUpdate();
		hibSession.flush();
		return seqId;
	}
	
}
