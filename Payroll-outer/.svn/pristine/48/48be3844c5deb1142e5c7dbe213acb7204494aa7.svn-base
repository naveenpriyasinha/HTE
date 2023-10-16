package com.tcs.sgv.stamp.dao;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.stamp.valueobject.RltDnmLocation;
import com.tcs.sgv.stamp.valueobject.StampAutosequenceGen;

public class StampCommonDAOImpl extends GenericDaoHibernateImpl<RltDnmLocation,Long>
{
	public StampCommonDAOImpl(Class<RltDnmLocation> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public boolean isPresent(String strTablename,Hashtable fieldList)
	{
		boolean result=false;
		Session session=getSession();
		String strQuery="select * from "+strTablename +" where";
		String strWhereCondition="";
		Enumeration ie=fieldList.keys();
		int count=0;
		int size=fieldList.size();
		size=size-1;
		while(ie.hasMoreElements())
		{
			count++;
			String strFieldName=ie.nextElement().toString();
			String strFieldValue=fieldList.get(strFieldName).toString();
			strWhereCondition=strWhereCondition+strFieldName+"="+strFieldValue;
			if(count<size)
			{
				strWhereCondition=strWhereCondition+" and ";
			}
		}
		strQuery=strQuery+" "+strWhereCondition;
		Query query=session.createSQLQuery(strQuery);
		List list=query.list();
		if(list.size()>0)
		{
			return true;
		}
		return result;
	}
	public String isPresent(String strTablename,Hashtable fieldList,String strPrimaryKeyField)
	{
		String result="";
		Session session=getSession();
		String strQuery="select "+ strPrimaryKeyField +" from "+strTablename +" where ";
		String strWhereCondition="";
		Enumeration ie=fieldList.keys();
		int count=0;
		int size=fieldList.size();
		while(ie.hasMoreElements())
		{
			count++;
			String strFieldName=ie.nextElement().toString();
			String strFieldValue=fieldList.get(strFieldName).toString();
			strWhereCondition=strWhereCondition+strFieldName+"="+strFieldValue;
			if(count<size)
			{
				strWhereCondition=strWhereCondition+" and ";
			}
		}
		strQuery=strQuery+" "+strWhereCondition;
		Query query=session.createSQLQuery(strQuery);
		List list=query.list();
		if(list.size()>0)
		{
			Iterator itList=list.iterator();
			while(itList.hasNext())
			{
				result=itList.next().toString();
				break;
			}
		}
		return result;
	}
	public List selectRecord(String strTablename,String strFieldList,String strWhereCondition)
	{
		List result=null;
		Session session=getSession();
		String strQuery="select " +strFieldList+" from "+strTablename+" where "+strWhereCondition;
		Query query=session.createSQLQuery(strQuery);
		result=query.list();
		return result;
	}
	public  Iterator getIterator(String strTablename,String strFieldList,String strWhereCondition)
	{
		List result=null;
		Session session=getSession();
		String strQuery="select " +strFieldList+" from "+strTablename+" where "+strWhereCondition;
		Query query=session.createSQLQuery(strQuery);
		result=query.list();
		Iterator itResult=null;
		if(result.size()>0)
		{
			itResult=result.iterator();
		}
		return itResult; 
	}
	public  Iterator getIterator(String strTablename,String strFieldList)
	{
		List result=null;
		Session session=getSession();
		String strQuery="select " +strFieldList+" from "+strTablename;
		Query query=session.createSQLQuery(strQuery);
		result=query.list();
		Iterator itResult=null;
		if(result.size()>0)
		{
			itResult=result.iterator();
		}
		return itResult; 
	}
	public List selectRecord(String strTablename,String strFieldList)
	{
		List result=null;
		Session session=getSession();
		String strQuery="select " +strFieldList+" from "+strTablename;
		Query query=session.createSQLQuery(strQuery);
		result=query.list();
		return result;
	}
	public boolean isTreasury(String strLocationCode)
	{
		boolean result=false;
		String strTablename="cmn_location_mst m";
		String strFieldList="m.parent_loc_id";
		String strWhereConditon="m.type_lookup_id=100056 and m.loc_id in ("+strLocationCode+")";
		Iterator itResult=getIterator(strTablename, strFieldList,strWhereConditon);
		if(itResult!=null)
		{
			return true;	
		}
		return result;
	}
	public String getParentTreasuryCodeForSubTreasury(String strLocationCode)
	{
		String strTreasuryCode="";
		String strTablename="cmn_location_mst mm";
		String strFieldList="mm.parent_loc_id";
		String strWhereCondition="mm.loc_id="+strLocationCode;
		Iterator itResult=this.getIterator(strTablename, strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strTreasuryCode=itResult.next().toString();
			}
		}
		return strTreasuryCode;
	}
	public String getUniqueNoForIndentPreparation(String strLocationCode,ServiceLocator serv)
	{
		String strUniqueNo="";
		String strTablename="stamp_autosequence_gen";
		StampAutosequenceGenDAOImpl autoSequenceDAO=new StampAutosequenceGenDAOImpl(StampAutosequenceGen.class,serv.getSessionFactory());
		Hashtable fieldList=new Hashtable();
		fieldList.put("LOC_CODE", strLocationCode);
		boolean result=isPresent(strTablename, fieldList);
		if(result==true)
		{
			StampAutosequenceGen autoSequenceVO=autoSequenceDAO.read(new BigDecimal(strLocationCode));
			Long uniqueNo=autoSequenceVO.getIndentNo();
			if(uniqueNo==null)
			{
				uniqueNo=new Long("1");
			}
			else
			{
				uniqueNo=uniqueNo+1;
			}			
			autoSequenceVO.setLocCode(new BigDecimal(strLocationCode));
			autoSequenceVO.setIndentNo(uniqueNo);
			//autoSequenceDAO.update(autoSequenceVO);		
			strUniqueNo=String.valueOf(uniqueNo);
		}
		else
		{
			StampAutosequenceGen autoSequenceVO=new StampAutosequenceGen();
			autoSequenceVO.setLocCode(new BigDecimal(strLocationCode));
			autoSequenceVO.setIndentNo(new Long(1));
			//autoSequenceDAO.create(autoSequenceVO);
			strUniqueNo="1";
		}
		return strUniqueNo;
	}
	public String getUniqueNoForChallan(String strLocationCode,ServiceLocator serv)
	{
		String strUniqueNo="";
		String strTablename="stamp_autosequence_gen";
		StampAutosequenceGenDAOImpl autoSequenceDAO=new StampAutosequenceGenDAOImpl(StampAutosequenceGen.class,serv.getSessionFactory());
		Hashtable fieldList=new Hashtable();
		fieldList.put("LOC_CODE", strLocationCode);
		boolean result=isPresent(strTablename, fieldList);
		if(result==true)
		{
			StampAutosequenceGen autoSequenceVO=autoSequenceDAO.read(new BigDecimal(strLocationCode));
			Long uniqueNo=autoSequenceVO.getChallanNo(); 
			//System.out.println("1");
			if(uniqueNo==null)
			{
				uniqueNo=new Long("1");
			}
			else
			{
				uniqueNo=uniqueNo+1;
			}			
			autoSequenceVO.setLocCode(new BigDecimal(strLocationCode));
			autoSequenceVO.setChallanNo(uniqueNo);
			//autoSequenceDAO.update(autoSequenceVO);
			//System.out.println(""+uniqueNo);
			strUniqueNo=String.valueOf(uniqueNo);
		}
		else
		{
			StampAutosequenceGen autoSequenceVO=new StampAutosequenceGen();
			autoSequenceVO.setLocCode(new BigDecimal(strLocationCode));
			autoSequenceVO.setChallanNo(new Long(1));
			//autoSequenceDAO.create(autoSequenceVO);
			strUniqueNo="1";
		}
		return strUniqueNo;
	}

}
