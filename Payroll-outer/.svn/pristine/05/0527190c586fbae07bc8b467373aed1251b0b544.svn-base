package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.LifeCertificateVO;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;

public class MstPensionerDtlsDAOImpl extends GenericDaoHibernateImpl<MstPensionerDtls, Long> implements MstPensionerDtlsDAO{

    Log gLogger = LogFactory.getLog(getClass());
    public MstPensionerDtlsDAOImpl(Class<MstPensionerDtls> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
    public ArrayList<LifeCertificateVO> getLifeCertificateVOList(String lStrBankCode,String lStrBranchCode,String lStrPKList,BigDecimal lBDHeadCode,String lStrLocCode) throws Exception
    {
    	List resultList;
    	Iterator itr;
        Object[] tuple;
        ArrayList<LifeCertificateVO> lObjLifeCertificateVOList = new ArrayList<LifeCertificateVO>();
                
		StringBuffer lSBQuery = new StringBuffer(3000);		
		Query lQuery =null;
		
		try 
		{
            Session hiSession = getSession();
            if(lStrPKList!= null &&  lStrPKList.length() > 0)
			{
				lSBQuery.append(" SELECT distinct(tprh.ppoNo),mph.firstName||' '||mph.middleName||' '||mph.lastName,mpd.acountNo,mb.bankName,rbb.branchName ");
				lSBQuery.append(" FROM MstPensionerHdr mph,MstPensionerDtls mpd,TrnPensionRqstHdr tprh,MstBank mb,RltBankBranch rbb ");
				lSBQuery.append(" WHERE mph.pensionerCode = tprh.pensionerCode ");
				lSBQuery.append(" AND tprh.pensionerCode = mpd.pensionerCode ");
				lSBQuery.append(" AND mpd.bankCode = mb.bankCode ");
				lSBQuery.append(" AND mb.bankCode = rbb.bankCode ");
				lSBQuery.append(" AND mpd.branchCode = rbb.branchCode ");
				lSBQuery.append(" AND mph.caseStatus = 'APPROVED' ");
				lSBQuery.append(" AND mpd.bankCode = :bankCode ");
				lSBQuery.append(" AND mpd.branchCode = :branchCode ");
				lSBQuery.append(" AND tprh.headCode = :headCode ");
				lSBQuery.append(" AND tprh.locationCode = :locationCode");
				
				lQuery = hiSession.createQuery(lSBQuery.toString());
				
				lQuery.setParameter("bankCode",lStrBankCode);
				lQuery.setParameter("branchCode",lStrBranchCode);
				lQuery.setParameter("headCode",lBDHeadCode);
				lQuery.setParameter("locationCode",lStrLocCode);
				
				resultList = lQuery.list();	
				
				if (resultList != null && resultList.size() > 0)
	            {
	                itr = resultList.iterator();
	                
	                while (itr.hasNext())
	                {
	                	LifeCertificateVO lObjLifeCertificateVO = new LifeCertificateVO();
	                	
	                    tuple = (Object[]) itr.next();
	                    lObjLifeCertificateVO.setPpoNo((String) tuple[0]);
	                    lObjLifeCertificateVO.setName((String) tuple[1]);
	                    lObjLifeCertificateVO.setAcountNo((String) tuple[2]);
	                    lObjLifeCertificateVO.setBankName((String) tuple[3]);
	                    lObjLifeCertificateVO.setBranchName((String) tuple[4]);
	                    
	                    lObjLifeCertificateVOList.add(lObjLifeCertificateVO);
	                }	                
	            }
			}
		} 
		catch (Exception e) 
		{
			gLogger.info("Error is : "+e,e);
			throw (e);
		}
		return lObjLifeCertificateVOList;
    }
	public Long getPnsionerDtlsIdFromPensionerCode(String pensionerCode)
	{
		Long pnsnDtlsId = null;
        StringBuilder query = new StringBuilder();
		try
		{
            Session hiSession = getSession();
			query.append(" SELECT d.pensionerDtlsId from MstPensionerDtls d ");
            query.append(" WHERE d.pensionerCode = :pensionerCode ");
            query.append(" AND d.activeFlag='Y' ");
          
			Query hqlQuery = hiSession.createQuery(query.toString());
            hqlQuery.setParameter("pensionerCode", pensionerCode);
            
            //hqlQuery.setString("pensionerCode", pensionerCode.toString());
            
			List list = hqlQuery.list();
			if(list != null && list.size()>0)
			{
				Iterator itr = list.iterator();
				while(itr.hasNext())
				{
					pnsnDtlsId = (Long)(itr.next());
				}
				
			}
		}catch(Exception e)
		{
            gLogger.error("Error is :"+e,e);
		}
		return pnsnDtlsId;
	}
	public Long getPnsionerDtlsIdFromPensionerCode(String pnsnCode,String lStrStatus)
	{
		Long pnsnDtlsId = 0L;
        StringBuilder query = new StringBuilder();
		try
		{
            Session hiSession = getSession();
			query.append(" SELECT d.pensionerDtlsId from MstPensionerDtls d ");
            query.append(" WHERE d.pensionerCode = :pensionerCode ");
            query.append(" AND d.activeFlag='Y' AND caseStatus =:caseStatus ");
          
			Query hqlQuery = hiSession.createQuery(query.toString());
            hqlQuery.setParameter("pensionerCode", pnsnCode);
            hqlQuery.setParameter("caseStatus",lStrStatus);
            //hqlQuery.setString("pensionerCode", pensionerCode.toString());
            
			List list = hqlQuery.list();
			if(list != null && list.size()>0)
			{
				Iterator itr = list.iterator();
				while(itr.hasNext())
				{
					pnsnDtlsId = (Long)(itr.next());
				}
				
			}
		}catch(Exception e)
		{
            gLogger.error("Error is :"+e,e);
		}
		return pnsnDtlsId;
	}
	public List<MstPensionerDtls> getMstPensionerDtlsDiff(String lStrPnsnrCode) throws Exception
	{
		StringBuffer lSBQuery = new StringBuffer();
        Query lQuery = null;
        List<MstPensionerDtls> lLstResLst = new ArrayList<MstPensionerDtls>();
        Session ghibSession = getSession();
        try
        {
     	   lSBQuery.append(" FROM MstPensionerDtls D WHERE D.pensionerCode  = '"+ lStrPnsnrCode +"' AND ");
     	   lSBQuery.append(" (D.caseStatus = 'NEW' OR D.caseStatus = 'APPROVED' ) ");
     	   lQuery = ghibSession.createQuery(lSBQuery.toString());

     	   lLstResLst = (List<MstPensionerDtls>)lQuery.list();
        }
        catch(Exception e)
        {
     	   throw e;
        }
 	 return lLstResLst;
	}
	public String getACCNo(String lStrBankCode,String lStrBranchCode,String lStrPensionerCode) throws Exception
	{
		String lStrACCNo = null;
		
		List<String> lLstResult = null;
		
		StringBuffer lSBQuery = new StringBuffer(200);
		
		Query lQuery =null;
				
		try 
		{
            Session hiSession = getSession();
            lSBQuery.append(" SELECT acountNo FROM MstPensionerDtls WHERE bankCode = :bankCode AND branchCode = :branchCode AND pensionerCode = :pensionerCode ");
			
			lQuery = hiSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("bankCode",lStrBankCode);
			lQuery.setParameter("branchCode",lStrBranchCode);
			lQuery.setParameter("pensionerCode",lStrPensionerCode);
			
			lLstResult = (List<String>) lQuery.list();	
			
			if(lLstResult != null &&! lLstResult.isEmpty())
			{
				lStrACCNo = (String) lLstResult.get(0);
			}			
		} 
		catch (Exception e) 
		{
			gLogger.info("Error is : "+e,e);
			throw (e);
		}
		return lStrACCNo;
	}
	public String getACCNo(String lStrPensionerCode) throws Exception
	{
		String lStrACCNo = null;
		
		List<String> lLstResult = null;
		
		StringBuffer lSBQuery = new StringBuffer(200);
		
		Query lQuery =null;
				
		try 
		{
            Session hiSession = getSession();
            lSBQuery.append(" SELECT acountNo FROM MstPensionerDtls WHERE pensionerCode = :pensionerCode ");
			
			lQuery = hiSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("pensionerCode",lStrPensionerCode);
			
			lLstResult = (List<String>) lQuery.list();	
			
			if(lLstResult != null &&! lLstResult.isEmpty())
			{
				lStrACCNo = (String) lLstResult.get(0);
			}			
		} 
		catch (Exception e) 
		{
			gLogger.info("Error is : "+e,e);
			throw (e);
		}
		return lStrACCNo;
	}
	public List getAuditorAddress(String gStrLocCode,Long gLngLangId) throws Exception 
	{
		ArrayList arrAuditorAddress = new ArrayList();	
		StringBuffer lSBQuery = new StringBuffer(500);
		List resultList;
		Iterator itr;
		Object[] tuple;
		
		try 
		{
            Session ghibSession = getSession();
            lSBQuery.append(" SELECT L.locName,L.locAddr1,L.locAddr2,C.cityName,D.districtName,S.stateName FROM  CmnLocationMst L,CmnCityMst C,CmnDistrictMst D,CmnStateMst  S WHERE  L.locCityId = C.cityId AND  L.locDistrictId = D.districtId AND  L.locStateId = S.stateId AND L.locationCode = :locationCode AND L.cmnLanguageMst.langId= :langId ");
			
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			lQuery.setParameter("locationCode", gStrLocCode);
			lQuery.setParameter("langId", gLngLangId);

			resultList = lQuery.list();
			
			if (resultList != null &&! resultList.isEmpty()) 
			{
				itr = resultList.iterator();
				if (itr.hasNext()) 
				{
					tuple = (Object[]) itr.next();
					if (tuple[0] != null)
					{
						arrAuditorAddress.add((String) tuple[0]);
					}
					if (tuple[1] != null)
					{
						arrAuditorAddress.add((String) tuple[1]);
					}
					if (tuple[2] != null)
					{
						arrAuditorAddress.add((String) tuple[2]);
					}
					if (tuple[3] != null)
					{
						arrAuditorAddress.add((String) tuple[3]);
					}
					if (tuple[4] != null)
					{
						arrAuditorAddress.add((String) tuple[4]);
					}
					if (tuple[5] != null)
					{
						arrAuditorAddress.add((String) tuple[5]);
					}						
				}
			}
		} 
		catch (Exception e) 
		{
			logger.info("Error is : "+ e, e);
			throw (e);
		}
		return arrAuditorAddress;
	}
	public String getBranchName(String lStrBranchCode,String lStrAuditorBankCode) throws Exception 
	{
		List<String> lLstResult = null;

		StringBuffer lSBQuery = new StringBuffer(150);

		String lStrReturn = null;

		Long lLngBankCode = null;
		Long lLngBranchCode = null;

		try {
            Session ghibSession = getSession();
            lLngBankCode = Long.parseLong(lStrAuditorBankCode);
			lLngBranchCode = Long.parseLong(lStrBranchCode);

			lSBQuery.append(" SELECT branchName FROM RltBankBranch WHERE bankCode = :bankCode AND branchCode = :branchCode ");
			
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			lQuery.setParameter("bankCode", lLngBankCode);
			lQuery.setParameter("branchCode", lLngBranchCode);

			lLstResult = (List<String>) lQuery.list();

			if (lLstResult != null &&! lLstResult.isEmpty()) {
				lStrReturn = (String) lLstResult.get(0);
			}
		} catch (Exception e) {
			logger.info(
					"Error is : "
							+ e, e);
			throw (e);
		}
		return lStrReturn;
	}
}
