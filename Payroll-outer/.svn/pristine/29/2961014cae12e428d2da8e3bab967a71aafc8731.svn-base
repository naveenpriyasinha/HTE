package com.tcs.sgv.pdpla.dao;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChqDetail;

public class PdplaShowPaymentDataDaoImpl  extends GenericDaoHibernateImpl implements PdplaShowPaymentDataDao
{
	public PdplaShowPaymentDataDaoImpl(Class<TrnPdChqDetail> name, SessionFactory sessionFactory) 
	{
			super(name);
			setSessionFactory(sessionFactory);
	}
	public ArrayList PaymentData() 
	{
		try
		
		{
			
			////System.out.println("u r in showpayment data daoimpl" );
			Session hibSession = getSession();
			Query q = hibSession.createSQLQuery("SELECT CD.PAYMENT_DATE,CD.ACCOUNT_NO_CHQ,M.PD_MJRHD,M.PD_MINHD,CD.AMOUNT,C.CHQ_NO,C.PAYEE_NM,CD.TRANSACTION_TYPE,CD.INTERNAL_TC,CD.DETAIL_HD  FROM TRN_PD_CHQ_DETAIL CD,TRN_PD_CHQ C,MST_PD_ACCOUNT M ,MST_PD_CHQ_INVENTORY CHQ WHERE CD.ACCOUNT_NO_CHQ=M.ACCOUNT_NO AND C.CHQ_CLEARANCE_DATE=CD.PAYMENT_DATE AND CHQ.INV_CHQ_ID=C.INVENTORY_ID");//WHERE ='0'");
			ArrayList queryList = (ArrayList)q.list();
			////System.out.println("Size: " + queryList.size());
			////System.out.println("aftr createSQLQuery"+queryList);
		
		
			return queryList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
