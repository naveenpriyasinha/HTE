package com.tcs.sgv.stamp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.stamp.valueobject.TrnStampIndentHdr;

public class IndentHeaderDAOImpl extends GenericDaoHibernateImpl<TrnStampIndentHdr,BigDecimal>
{
	public IndentHeaderDAOImpl(Class<TrnStampIndentHdr> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List getIndentData()
	{
		List indentData=new ArrayList();
		Session session=getSession();
		String strQuery="select indent.ind_no from trn_stamp_indent_hdr indent where indent.status='Open'";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		String strIndentID="";
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				ComboValuesVO vo=new ComboValuesVO();
				strIndentID=itResult.next().toString();
				vo.setId(strIndentID);
				vo.setDesc(strIndentID);
				indentData.add(vo);
			}
		}
		return indentData;
	}
	public List getIndentNoForIssue(String strSubTreasury)
	{
		List indentData=new ArrayList();
		Session session=getSession();
		String strQuery="select kk.ind_no from trn_stamp_indent_hdr kk where kk.status='Open' and kk.loc_code="+strSubTreasury;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		int flag=0;
		String strIndentID="";
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				if(flag==0)
				{
					ComboValuesVO voSelect=new ComboValuesVO();
					voSelect.setId("0");
					voSelect.setDesc("--select--");
					indentData.add(voSelect);
					flag=1;
				}
				ComboValuesVO vo=new ComboValuesVO();
				strIndentID=itResult.next().toString();
				vo.setId(strIndentID);
				vo.setDesc(strIndentID);
				indentData.add(vo);
			}
		}
		return indentData;
	}
	public String isIndentPresent(String strIndentID)
	{
		String indentHeaderID=null;
		Session session=getSession();
		String strQuery="select h.ind_hdr_id from trn_stamp_indent_hdr h where h.ind_no="+strIndentID;
		Query query=session.createSQLQuery(strQuery);
		List list=query.list();
		if(list.size()>0)
		{
			Iterator itResult=list.iterator();
			while(itResult.hasNext())
			{
				indentHeaderID=itResult.next().toString();
			}
		}
		return indentHeaderID;
	}
	public String getIndentValue(String strIndentNo,StampCommonDAOImpl commonDAO)
	{
		String strIndentValue=" ";
		String strTablename="trn_stamp_indent_hdr h";
		String strFieldList="h.gross_amnt";
		String strWhereCondition="h.ind_no="+strIndentNo;
		Iterator itResult=commonDAO.getIterator(strTablename,strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strIndentValue=itResult.next().toString();
			}
		}
		return strIndentValue;
	}
	
}
