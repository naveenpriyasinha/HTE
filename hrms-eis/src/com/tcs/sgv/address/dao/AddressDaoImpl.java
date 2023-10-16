package com.tcs.sgv.address.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

public class AddressDaoImpl extends GenericDaoHibernateImpl implements AddressDao{
	public final String BIRTH_PLACE_ADDRESS="birthPlaceAddress";
	public final String NATIVE_PLACE_ADDRESS="nativePlaceAddress";
	public final String PERMANENT_PLACE_ADDRESS="permanentPlaceAddress";
	public final String CURRENT_PLACE_ADDRESS="currentPlaceAddress";
	
	private static final Log logger = LogFactory.getLog( AddressDaoImpl.class);
	public AddressDaoImpl(Class T ,SessionFactory sessionFactory) {
		super(T);
		setSessionFactory(sessionFactory);
	}
	public List getUsers(long  userId){
		String queryString ="select org from OrgEmpMst org where org.orgUserMst.userId=:userId order by org.cmnLanguageMst.langId";
		Session session=getSession();
		Query query=session.createQuery(queryString).setParameter("userId", userId);
		List list=query.list();
		return list;
		
	}
	
	public List<HrEisEmpMst> getUser(long userId){
		List<HrEisEmpMst> result=new ArrayList<HrEisEmpMst>();
		Session hibSession=getSession();
 	    String queryString="select hr from HrEisEmpMst hr where hr.orgEmpMst.orgUserMst.userId =:userId";
 	    Query query=hibSession.createQuery(queryString).setParameter("userId",userId );
 	    result= query.list();
 	    logger.info("the Number of  User ARE :::>>"+result.size());
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map  getAllAddress(long userId)
	{
		logger.info("INSIDE THE getAllAddress METHOD OF AddressDaoImpl");
		List result=null;
		List listOfAddressId=new ArrayList();
		CmnAddressMst addressMst=null;
		Map mapOfList=new HashMap();
		Map mapOfAddress=new HashMap();
 		Session hibSession = getSession();
     
 	   String queryString="select hr from HrEisEmpMst hr where hr.orgEmpMst.orgUserMst.userId =:userId";
 	   
	    Query query=hibSession.createQuery(queryString).setParameter("userId",userId );
 	    result= query.list();
 	   
        for (Iterator iter = result.iterator(); iter.hasNext();)
        {
        	HrEisEmpMst element=(HrEisEmpMst)iter.next();
        	
			if(!(mapOfAddress.containsKey(BIRTH_PLACE_ADDRESS))){
			if(element.getCmnAddressMstByEmpBirthPlaceAddressId() !=null){
				logger.info("Inside the Birth Address in DAO");
				addressMst=element.getCmnAddressMstByEmpBirthPlaceAddressId();
				mapOfAddress.put(BIRTH_PLACE_ADDRESS, addressMst.getAddressId());
				listOfAddressId.add(addressMst.getAddressId());
			}
			}
			if(!(mapOfAddress.containsKey(CURRENT_PLACE_ADDRESS))){
			if(element.getCmnAddressMstByEmpCurrentAddressId() !=null){
				logger.info("Inside the Current Address in DAO");
				addressMst=element.getCmnAddressMstByEmpCurrentAddressId();
				mapOfAddress.put(CURRENT_PLACE_ADDRESS, addressMst.getAddressId());
				listOfAddressId.add(addressMst.getAddressId());
			}
			}
			if(!(mapOfAddress.containsKey(NATIVE_PLACE_ADDRESS) )){
			if(element.getCmnAddressMstByEmpNativePlaceAddressId() !=null){
				logger.info("Inside the Native Address in DAO");
				addressMst=element.getCmnAddressMstByEmpNativePlaceAddressId();
				mapOfAddress.put(NATIVE_PLACE_ADDRESS, addressMst.getAddressId());
				listOfAddressId.add(addressMst.getAddressId());
			}
			}
			if (!(mapOfAddress.containsKey(PERMANENT_PLACE_ADDRESS))) {
				if (element.getCmnAddressMstByEmpPermanentAddressId() != null) {
					logger.info("Inside the Permanent Address in DAO");
					addressMst = element.getCmnAddressMstByEmpPermanentAddressId();
					mapOfAddress.put(PERMANENT_PLACE_ADDRESS, addressMst.getAddressId());
					listOfAddressId.add(addressMst.getAddressId());
				}
			}
		}
        mapOfList.put("resultList", result);
        mapOfList.put("listOfAddress", mapOfAddress);
		return mapOfList;
		
	}
	public OrgEmpMst getOrgEmpMstVO(long empid) {
		List list =new ArrayList();
 		Session hibSession = getSession();	    
 		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
 	    crit.add(Restrictions.like("empId", empid)); 	 	    
 	    list= crit.list();
 		OrgEmpMst orgEmpMst= (OrgEmpMst)list.get(0);
		return orgEmpMst;
	}
	public void saveCmnAddressMst(CmnAddressMst addressMst){
		Session session=getSession();
		session.save(addressMst);
		logger.info("After saving ADDRESS MASTER");
	}
	
	public static long getLangId(ServiceLocator serv, Map objectArgs) 
	{

		Map loginMap = (Map) objectArgs.get("baseLoginMap");

		/* Get The Person Post */
		long langId = Long.parseLong(loginMap.get("langId").toString());

		/* End of the geting Person Post Code */
		return langId;
	}	
}
