package com.tcs.sgv.stamp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.stamp.valueobject.RltDnmLocation;
import com.tcs.sgv.stamp.valueobject.TrnStampChallanHdr;

public class TrnStampChallanHdrDAOImpl extends GenericDaoHibernateImpl<TrnStampChallanHdr,BigDecimal>
{
	public TrnStampChallanHdrDAOImpl(Class<TrnStampChallanHdr> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List getEnfreshmentNo()
	{
		List EnfreshmentList=new ArrayList();
		Session session=getSession();
		String strQuery="select stamp.enfreshment_no from trn_stamp_challan_hdr stamp";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				String strEnfreshment=itResult.next().toString();
				//System.out.println(""+strEnfreshment);
				ComboValuesVO vo=new ComboValuesVO();
				vo.setId(strEnfreshment);
				vo.setDesc(strEnfreshment);
				EnfreshmentList.add(vo);
			}
		}
		return EnfreshmentList;
	}
	public String getChallanAmount(String strEnfreshmentNo)
	{
		String strChallanAmount="";
		Session session=getSession();
		String strQuery="select stamp.challan_value from trn_stamp_challan_hdr stamp where stamp.enfreshment_no="+strEnfreshmentNo;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				strChallanAmount=itResult.next().toString();
			}
		}
		return strChallanAmount;
	}
	public String getVendorCode(String strEnfreshmentNo)
	{
		String strVendorCode="";
		Session session=getSession();
		String strQuery="select stamp.vendor_code from trn_stamp_challan_hdr stamp where stamp.enfreshment_no="+strEnfreshmentNo;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				strVendorCode=itResult.next().toString();
			}
		}
		return strVendorCode;
	}
	public String getVendorName(String strEnfreshmentNo)
	{
		String strVendorName="";
		Session session=getSession();
		String strQuery="select stamp.vendor_party_name from trn_stamp_challan_hdr stamp where stamp.enfreshment_no="+strEnfreshmentNo;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				strVendorName=itResult.next().toString();
			}
		}
		return strVendorName;
	}
	
	public String getChallanID(Map objectArgs)
	{
		String strChallanID="";
		String strLocationCode=SessionHelper.getLocationCode(objectArgs);
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		
		String strUniqueNo=commonDAO.getUniqueNoForChallan(strLocationCode, serv);
		strChallanID=strUniqueNo;
		
		return strChallanID;
	}
	public String getPrimaryKey(String strEnfreshmentNo)
	{
		String strPrimaryKey="";
		Session session=getSession();
		String strQuery="select stamp.challan_id from trn_stamp_challan_hdr stamp where stamp.enfreshment_no="+strEnfreshmentNo;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				strPrimaryKey=itResult.next().toString();
			}
		}
		//System.out.println(""+strPrimaryKey);
		return strPrimaryKey;
	}
	public List getApprovedEnfreshmentNo()
	{
		List EnfreshmentList=new ArrayList();
		Session session=getSession();
		String strQuery="select stamp.enfreshment_no from trn_stamp_challan_hdr stamp where stamp.status='Approved'";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				String strEnfreshment=itResult.next().toString();
				//System.out.println(""+strEnfreshment);
				ComboValuesVO vo=new ComboValuesVO();
				vo.setId(strEnfreshment);
				vo.setDesc(strEnfreshment);
				EnfreshmentList.add(vo);
			}
		}
		return EnfreshmentList;
	}
	public List getAllSubTreasuryName(String strLocationCode)
	{
		List subTresuryName=new ArrayList();
		Session session=getSession();
		String strQuery1="select g.loc_id from cmn_location_mst g where g.type_lookup_id=100056";
		Query query1=session.createSQLQuery(strQuery1);
		List result=query1.list();
		//System.out.println(""+result.size());
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				ComboValuesVO vo=new ComboValuesVO();
				Object[] tuple=(Object[])itResult.next();
				String strTresuryCode=tuple[0].toString();
				String strTresuryName=tuple[1].toString();
				vo.setId(strTresuryCode);
				vo.setDesc(strTresuryName);
				subTresuryName.add(vo);
			}
		}
		return subTresuryName;
	}
}
