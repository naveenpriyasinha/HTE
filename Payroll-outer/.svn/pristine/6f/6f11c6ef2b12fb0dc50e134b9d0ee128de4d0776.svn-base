package com.tcs.sgv.stamp.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.stamp.valueobject.MstStampStock;

public class MstStampStockDAOImpl extends GenericDaoHibernateImpl<MstStampStock,BigDecimal>
{
	public MstStampStockDAOImpl(Class<MstStampStock> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	public String getCategoryCode(String strGroupCode,StampCommonDAOImpl commonDAO)
	{
		String strCategorycode="";
		String strTablename="mst_stamp_group";
		String strFieldList="category_code";
		String strWhereCondition="grp_code="+strGroupCode;
		Iterator itResult=commonDAO.getIterator(strTablename,strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strCategorycode=itResult.next().toString();
			}
		}
		return strCategorycode;
	}
	public String getQtyPerSheet(String strDenominationCode,String strGroupCode,StampCommonDAOImpl commonDAO)
	{
		String strQty="";
		String strTablename="mst_stamp_denomination";
		String strFieldList="label_per_sheet";
		String strWhereCondition="grp_code="+strGroupCode+" and dnm_code="+strDenominationCode;
		Iterator itResult=commonDAO.getIterator(strTablename, strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strQty=itResult.next().toString();
			}
		}
		return strQty;
	}
	public String getStockId(String grpCd, String dnmCd, String locCd)
	{
		String strQuery="select stock_id from mst_stamp_stock " +
		"where grp_code = '" + grpCd + "' and dnm_code = '" + dnmCd + "' and loc_code = '" + locCd + "'";
		Session session=getSession();
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		String stkId = "";
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				stkId = itResult.next().toString();
			}
		}
		return stkId;
	}
}
