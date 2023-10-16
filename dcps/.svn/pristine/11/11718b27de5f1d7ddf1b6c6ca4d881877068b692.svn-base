package com.tcs.sgv.common.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * 
 * Common DAO for SELECT/DELETE/QUERIES (NAMED QURIES - written in hbm files)
 * 
 * @author Amar Patel
 *
 */

public class IFMSCommonDAOImpl extends GenericDaoHibernateImpl implements IFMSCommonDAO{
	
	private Session hibSession = null;
	
	public IFMSCommonDAOImpl(SessionFactory sessionFactory) {
		super(TrnBillRegister.class);
		setSessionFactory(sessionFactory);
	}

	public IFMSCommonDAOImpl(Session hibSession){
		super(TrnBillRegister.class);
		this.hibSession = hibSession;
	}
	
	public IFMSCommonDAOImpl(Class type, SessionFactory sessionFactory){
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	private Session getDBSession(){
		
		if(hibSession != null)
			return hibSession;
		
		return getSession();
		
	}
	
	public List findByNamedQuery(String queryName) throws Exception {
		return findByNamedQuery(queryName, null);
	}
	
	public List findByNamedQuery(String queryName, Map<String, Object> options) throws Exception {
		
		try{
			Query query = prepareNamedQuery(queryName, options);
			
			return query.list();
		
		}catch (Exception e) {
			logger.error("Error in executing query '" + queryName + "' : ", e);
			throw e;
		}

	}
	
	public int deleteOrUpdateByNamedQuery(String queryName) throws Exception {
		return deleteOrUpdateByNamedQuery(queryName, null);
	}
	
	public int deleteOrUpdateByNamedQuery(String queryName, Map<String, Object> options) throws Exception {
		
		try{

			return prepareNamedQuery(queryName, options).executeUpdate();
		
		}catch (Exception e) {
			logger.error("Error in executing query '" + queryName + "' : ", e);
			throw e;
		}
		
	}
	
	private Query prepareNamedQuery(String queryName, Map<String, Object> options) throws Exception{
		
		Session session = getDBSession();
		Query query = session.getNamedQuery(queryName);
		
		try{
			
			for(String paramName : query.getNamedParameters()) {
				
				if(options != null){
					
					Object value =  options.get(paramName);
					
					if(value == null) {
						throw new Exception("\n==========\nOne or more options are missing required for query parameters. \n" +
								"Query Name : " + queryName + " \nMissing Parameter : " + paramName + "\n==========");
					}
					
					if(value instanceof Collection)
						query.setParameterList(paramName, (Collection)value);
					else
						query.setParameter(paramName, value);
					
				}else{
					throw new Exception("One or more options are missing required for query parameters.");
				}
				
			}
			
		}catch (Exception e) {
			throw e;
		}
		
		return query;
		
	}
	public Map<String,List<CmnLookupMst>> getPartialCommonLookupMstVo(List lLstLookUpNames, Long lLangId) throws Exception 
	{
		Session ghibSession = getSession();
		Query lQuery = null;
		List lLstLookupId = new ArrayList();
		Map lMaplookupIdAndName = new HashMap();
		Map<String,List<CmnLookupMst>> FinalMap = new HashMap();
		try
		{
			if(! lLstLookUpNames.isEmpty())
			{
				StringBuffer lSBParentLookupDtls = new StringBuffer();
				lSBParentLookupDtls.append(" SELECT lookupName,lookupId from CmnLookupMst where lookupName in (:lookupNameList) " +
										   " and cmnLanguageMst.langId=:langId order by lookupId ");
				lQuery = ghibSession.createQuery(lSBParentLookupDtls.toString());
				lQuery.setCacheable(true).setCacheRegion("ecache_lookup");
				lQuery.setParameterList("lookupNameList", lLstLookUpNames);
				lQuery.setParameter("langId", lLangId);
				List lLstResTemp = lQuery.list();
				Object[] tuple = null;
				if(!lLstResTemp.isEmpty())
				{
					for(int i=0;i<lLstResTemp.size();i++)
					{
						tuple = (Object[]) lLstResTemp.get(i); 
						lLstLookupId.add(new Long(tuple[1].toString()));
						lMaplookupIdAndName.put(tuple[1].toString(), tuple[0].toString());
					}
				}
				
				if(! lLstLookupId.isEmpty())
				{
					StringBuffer lSBQuery = new StringBuffer();
					lSBQuery.append("SELECT lookupName,lookupDesc,lookupShortName,parentLookupId from CmnLookupMst where " +
									" parentLookupId in(:lLstLookupId)" +
									" order by parentLookupId,orderNo ");
					lQuery = ghibSession.createQuery(lSBQuery.toString());
					lQuery.setCacheable(true).setCacheRegion("ecache_lookup");
					lQuery.setParameterList("lLstLookupId", lLstLookupId);
					lLstResTemp =  lQuery.list();
					CmnLookupMst lObjCmnLookupMst = null;
					Long lLngTempLookupId = 0L;
					List<CmnLookupMst> lObjTempVo = null;
					if(! lLstResTemp.isEmpty())
					{
						for(int i=0;i<lLstResTemp.size();i++)
						{
							tuple = (Object[]) lLstResTemp.get(i); 
							lObjCmnLookupMst = new CmnLookupMst();
							lObjCmnLookupMst.setLookupName(tuple[0].toString());
							lObjCmnLookupMst.setLookupDesc(tuple[1].toString());
							lObjCmnLookupMst.setLookupShortName(tuple[2].toString());
							if(! lLngTempLookupId.equals(new Long(tuple[3].toString())))
							{
								if(!lLngTempLookupId.equals(0L) )
								{
									FinalMap.put((String) lMaplookupIdAndName.get(lLngTempLookupId.toString()), lObjTempVo);
								}
								lLngTempLookupId = new Long(tuple[3].toString());
								lObjTempVo = new ArrayList<CmnLookupMst>();
							}
							lObjTempVo.add(lObjCmnLookupMst);
						}
						if(!lLngTempLookupId.equals(0L) )
						{
							FinalMap.put((String) lMaplookupIdAndName.get(lLngTempLookupId.toString()), lObjTempVo);
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			logger.error("Error in  : " + e, e);
			throw e;
		}
		return FinalMap;
	}

	public List<OrgUserMst> getUserListOfLoggedinLocation(String userName, Long locId, long langId) {		
		List<OrgUserMst> userList = new ArrayList<OrgUserMst>();
		
    	String query = "select orgUserMst " +
		" from CmnLocationMst location, " +
		" OrgUserMst orgUserMst, " +
		" OrgUserpostRlt orgUserpostRlts " +
		" where orgUserMst.userName like :username  " +
		" and orgUserMst.userId = orgUserpostRlts.orgUserMst.userId " +
		" and orgUserpostRlts.activateFlag = 1" +
		" and orgUserpostRlts.orgPostMstByPostId.activateFlag = 1" +
		" and location.locationCode = orgUserpostRlts.orgPostMstByPostId.locationCode " +
		" and (location.locId = :locId OR location.parentLocId = :locId)" +
		" and location.activateFlag = 1 " +
		" and location.cmnLanguageMst.langId =:langid ";

		Session hibSession = getSession();
		Query sqlQuery= hibSession.createQuery(query);
		sqlQuery.setParameter("username", userName + "%");
		sqlQuery.setParameter("locId", locId);
		sqlQuery.setParameter("langid", langId);

		userList = sqlQuery.list();
		
		return userList;
	}
}

