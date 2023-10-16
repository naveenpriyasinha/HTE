package com.tcs.sgv.address.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpDtlTxn;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;

public class AddressApproveDaoImpl extends GenericDaoHibernateImpl implements AddressApproveDao{
public final String REQ_ID="reqId";
	
	private static final Log logger = LogFactory.getLog(AddressApproveDaoImpl.class);
	public AddressApproveDaoImpl(Class T ,SessionFactory sessionFactory) {
		super(T);
		setSessionFactory(sessionFactory);
	}
	
	
	public Map getAddressToApprove(long reqId){
		logger.info("INSIDE THE GET ADDRESS TO APPROVE METHOD IN DAO");
		List<HrEisEmpDtlTxn> list=null;
		List<HrEisEmpDtlTxn> listfullObject =new ArrayList<HrEisEmpDtlTxn>();
		List listOfEmpId=null;
		List listOfMstObj=null;
		Map<String,Object> mapOfFinal=new HashMap<String, Object>();
		Session session= getSession();				
		String queryHrEisEmpTrnString="select hrTrn.orgEmpMst.empId from HrEisEmpDtlTxn hrTrn where hrTrn.id.reqId=:reqId and hrTrn.actionFlag='P' and hrTrn.cmnLanguageMstByLangId.langId=1";
		Query queryHrEisEmpTrn=session.createQuery(queryHrEisEmpTrnString).setParameter(REQ_ID, reqId);
		listOfEmpId=queryHrEisEmpTrn.list();
		
		logger.info("listOfEmpId  :  " + listOfEmpId.size());
		if(listOfEmpId!=null && !(listOfEmpId.isEmpty()))
		{
			long id=Long.parseLong(listOfEmpId.get(0).toString());					
			String queryHrEisEmpMstString="select hrMst from HrEisEmpMst hrMst where hrMst.orgEmpMst.empId =:id";
			Query queryHrEisEmpMst=session.createQuery(queryHrEisEmpMstString).setParameter("id",id);			
			listOfMstObj=queryHrEisEmpMst.list();
			
			if(!(listOfMstObj.isEmpty())){
				mapOfFinal.put("HrEisEmpMst", listOfMstObj.get(0));
				}
			else
			{
				logger.info("listOfMstObj is empty");
			}
		}
		
		
		
		String queryString="select hr from HrEisEmpDtlTxn hr where hr.id.reqId=:reqId";
		Query query=session.createQuery(queryString).setParameter(REQ_ID, reqId);
		list=query.list();
		
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			HrEisEmpDtlTxn element = (HrEisEmpDtlTxn) iter.next();
			element.getCmnAddressMstByEmpBirthPlaceAddressId();
			element.getCmnAddressMstByEmpCurrentAddressId();
			element.getCmnAddressMstByEmpPermanentAddressId();
			element.getCmnAddressMstByEmpNativePlaceAddressId();
			
			listfullObject.add(element);
		}
		mapOfFinal.put("HrEisEmpDtlTxn", listfullObject);
		logger.info("The List of the request is ::>>"+list.size());
		return mapOfFinal;
	}
	
	public List<HrEisEmpDtlTxn> getHrEisEmPMst (long reqId){
		logger.info("**********Inside the getHrEisEmPMst method*********"+reqId);
		List<HrEisEmpDtlTxn>  list=null;
		Session session=getSession();
		String queryStr="select hr from HrEisEmpDtlTxn hr where hr.id.reqId=:reqId ";
		Query query=session.createQuery(queryStr).setParameter("reqId",reqId);
		list=query.list();
		return list;
	}
	public List<HrEisEmpMst> getObjectForHrEisEmpMst (List listOfId)
	{
		List<HrEisEmpMst> list=null;
		Session  session=getSession();
		for (Iterator iter = listOfId.iterator(); iter.hasNext();) 
		{
			Long element = (Long) iter.next();
			logger.info("ELEMENT :::"+element);
		}
		String queryStr="select hr from HrEisEmpMst hr where hr.orgEmpMst.empId in (:listOfId)";
		Query query=session.createQuery(queryStr).setParameterList("listOfId",listOfId);
		list=query.list();
		return list;
	}
	
	public void updateHrEisEmpTrn(long reqId){
		List<HrEisEmpDtlTxn> list=null;
		Session  session=getSession();
		String updateQuery="update HrEisEmpDtlTxn hr set hr.actionFlag = A where hr.id.reqId=:reqId";
		String queryString="select hr from HrEisEmpDtlTxn hr where hr.id.reqId=:reqId";
		Query query=session.createQuery(queryString).setParameter("reqId",reqId);
		Query query2=null;
		list=query.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			HrEisEmpDtlTxn element = (HrEisEmpDtlTxn) iter.next();
			query2=session.createQuery(updateQuery).setParameter("reqId",element.getId().getReqId());
			query2.executeUpdate();
		}
	}
	public List<HrEisEmpDtlTxn> getHrEisEmpTrnObjectByRequestId(long reqId){
		List<HrEisEmpDtlTxn> listTRNObject=null;
		Session session=getSession();
		String queryStr="select hr from HrEisEmpDtlTxn hr where hr.id.reqId=:reqId";
		Query query=session.createQuery(queryStr).setParameter("reqId",reqId);
		listTRNObject=query.list();
		for (Iterator iter = listTRNObject.iterator(); iter.hasNext();) {
			HrEisEmpDtlTxn element = (HrEisEmpDtlTxn) iter.next();
			logger.info("THE ELEMENT ACTION FLAG IS ::>>>"+element.getActionFlag());
			element.setActionFlag("R");
			logger.info("THE ELEMENT ACTION FLAG IS ::RRR>>>"+element.getActionFlag());
			session.saveOrUpdate(element);
		}
		return listTRNObject;
	}	
	
	
}
